package com.liandi.system.shiro.filter;

import java.io.Serializable;
import java.util.ArrayDeque;
import java.util.Deque;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.DefaultSessionKey;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;

import com.liandi.system.service.dto.UserDTO;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * 踢出session过滤
 * 
 * @author czg
 * @date 2019/9/18 18:18
 */
@Slf4j
public class KickoutSessionFilter extends AccessControlFilter {

    /**
     * 踢出之前登录的/之后登录的用户 默认false踢出之前登录的用户
     */
    @Setter
    private boolean kickoutAfter = false;

    /**
     * 同一个帐号最大会话数 默认1
     */
    @Setter
    private int maxSession = 1;

    @Setter
    private SessionManager sessionManager;

    /**
     * 被踢出后重定向到的地址
     */
    @Setter
    private String kickoutUrl;

    private Cache<String, Deque<Serializable>> cache;

    // 设置Cache的key的前缀
    public void setCacheManager(CacheManager cacheManager) {
        // 必须和ehcache缓存配置中的缓存name一致
        this.cache = cacheManager.getCache("shiro-activeSessionCache");
    }

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        return false;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) {

        Subject subject = getSubject(request, response);
        // 没有登录授权 且没有记住我
        if (!subject.isAuthenticated() && !subject.isRemembered()) {
            // 如果没有登录，直接进行之后的流程
            return true;
        }

        Session session = subject.getSession();
        // 当前用户
        UserDTO user = (UserDTO)subject.getPrincipal();
        String loginName = user.getLoginName();
        Serializable sessionId = session.getId();
        // 读取缓存用户 没有就存入
        Deque<Serializable> deque = cache.get(loginName);
        if (log.isDebugEnabled()) {
            log.debug("===========session时间设置：{}{}", session.getTimeout(), "===========");
            log.debug("===========当前用户loginName：{}{}", loginName, "===========");
            log.debug("===========当前用户sessionId：{}{}", sessionId, "===========");
            log.debug("===========当前用户deque：{}{}", deque, "===========");
        }

        if (deque == null) {
            deque = new ArrayDeque<>();
        }

        // 如果队列里没有此sessionId，且用户没有被踢出；放入队列
        if (!deque.contains(sessionId) && session.getAttribute("kickout") == null) {
            // 将sessionId存入队列
            deque.push(sessionId);
            // 将用户的sessionId队列缓存
            cache.put(loginName, deque);
        }

        // 如果队列里的sessionId数超出最大会话数，开始踢人
        while (deque.size() > maxSession) {
            if (log.isDebugEnabled()) {
                log.debug("===========当前用户deque队列长度：{}{}", deque.size(), "===========");
            }
            Serializable kickoutSessionId;
            // 是否踢出后来登录的，默认是false；即后者登录的用户踢出前者登录的用户；
            // 如果踢出后者
            if (kickoutAfter) {
                kickoutSessionId = deque.removeFirst();
            } else {
                // 否则踢出前者
                kickoutSessionId = deque.removeLast();
            }
            // 踢出后再更新下缓存队列
            cache.put(loginName, deque);
            // 获取被踢出的sessionId的session对象
            Session kickoutSession = sessionManager.getSession(new DefaultSessionKey(kickoutSessionId));
            if (kickoutSession != null) {
                // 设置会话的kickout属性表示踢出了
                kickoutSession.setAttribute("kickout", true);
            }
        }

        // 如果被踢出了，(前者或后者)直接退出，重定向到踢出后的地址
        if (session.getAttribute("kickout") != null && (Boolean)session.getAttribute("kickout")) {
            // 会话被踢出了
            // 退出登录
            subject.logout();
            // 重定向
            // saveRequest(request);
            // WebUtils.issueRedirect(request, response, kickoutUrl);
            return false;
        }
        return true;
    }

}
