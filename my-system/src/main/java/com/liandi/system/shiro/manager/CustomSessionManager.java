package com.liandi.system.shiro.manager;

import java.io.Serializable;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.web.servlet.ShiroHttpServletRequest;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.apache.shiro.web.util.WebUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * 自定session管理器
 * 
 * @author czg
 * @date 2019/9/21 16:29
 */
@Slf4j
public class CustomSessionManager extends DefaultWebSessionManager {

    private final String sessionHeaderKey = "my-sessionId";

    private final String referencedSessionIdSource = "Stateless request";

    public CustomSessionManager() {
        super();
    }

    @Override
    protected Serializable getSessionId(ServletRequest request, ServletResponse response) {
        // 如果请求头中有 SESSION_HEADER_KEY 则其值为sessionId
        String sessionId = WebUtils.toHttp(request).getHeader(sessionHeaderKey);
        if (StringUtils.isNoneBlank(sessionId)) {
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_SOURCE, referencedSessionIdSource);
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID, sessionId);
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_IS_VALID, Boolean.TRUE);
            return sessionId;
        } else {
            // 否则按默认规则从cookie取sessionId
            return super.getSessionId(request, response);
        }
    }

}
