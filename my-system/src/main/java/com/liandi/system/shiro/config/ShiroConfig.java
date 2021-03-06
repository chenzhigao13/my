package com.liandi.system.shiro.config;

import java.util.Map;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.liandi.system.shiro.filter.CustomAuthenticationFilter;
import com.liandi.system.shiro.filter.KickoutSessionFilter;
import com.liandi.system.shiro.manager.CustomSessionDao;
import com.liandi.system.shiro.manager.CustomSessionManager;
import com.liandi.system.shiro.manager.RedisCacheManager;
import com.liandi.system.shiro.realm.CustomRealm;

/**
 * shiro 配置类
 * 
 * @author czg
 * @date 2019/9/18 9:04
 */
@Configuration
public class ShiroConfig {

    @Bean
    public SessionDAO sessionDAO() {
        CustomSessionDao customSessionDao = new CustomSessionDao();
        return customSessionDao;
    }

    @Bean
    public SessionManager sessionManager() {
        CustomSessionManager customSessionManager = new CustomSessionManager();
        customSessionManager.setSessionDAO(sessionDAO());
        return customSessionManager;
    }

    @Bean
    public CacheManager cacheManager() {
        RedisCacheManager redisCacheManager = new RedisCacheManager();
        return redisCacheManager;
    }

    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher() {

        // 告诉shiro如何根据获取到的用户信息中的密码和盐值来校验密码
        // 设置用于匹配密码的CredentialsMatcher
        HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
        // 加密方式
        credentialsMatcher.setHashAlgorithmName(Md5Hash.ALGORITHM_NAME);
        // 存储凭证十六进制编码
        credentialsMatcher.setStoredCredentialsHexEncoded(true);
        // 加密次数
        credentialsMatcher.setHashIterations(1024);

        return credentialsMatcher;
    }

    @Bean
    public Realm realm() {
        CustomRealm customRealm = new CustomRealm();
        customRealm.setCredentialsMatcher(hashedCredentialsMatcher());
        return customRealm;
    }

    @Bean
    public DefaultWebSecurityManager defaultWebSecurityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        // 自定义Realm.
        securityManager.setRealm(realm());
        // 自定义缓存管理器 可以使用redis
        securityManager.setCacheManager(cacheManager());
        // 自定义Session管理 可以使用redis
        securityManager.setSessionManager(sessionManager());
        // Cookie记住我管理器
        // securityManager.setRememberMeManager(rememberMeManager());

        return securityManager;
    }

    @Bean
    public ShiroFilterFactoryBean shirFilter(DefaultWebSecurityManager securityManager) {

        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();

        // 设置 SecurityManager
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        // 添加kickout踢出过滤器
        shiroFilterFactoryBean.getFilters().put("kickout", newKickoutSessionFilter());
        // 添加authc自定义过滤器
        shiroFilterFactoryBean.getFilters().put("authc", new CustomAuthenticationFilter());

        /*
         * anon：匿名用户可访问
         * authc：认证用户可访问
         * user：使用rememberMe可访问
         * perms：对应权限可访问
         * role：对应角色权限可访问
         */
        Map<String, String> filterMap = shiroFilterFactoryBean.getFilterChainDefinitionMap();

        // TODO 所有都匿名访问
        // filterMap.put("/**", "anon");

        // /sys/user/login 登陆接口可以匿名访问
        filterMap.put("/sys/user/login", "anon");
        // /admin/** 接口登陆的用户须有admin角色
        filterMap.put("/admin/**", "authc, roles[admin]");
        // /docs/** 接口登陆的用户须有document:read权限
        filterMap.put("/docs/**", "authc, perms[document:read]");
        // 剩余的所有接口须用户登陆
        filterMap.put("/**", "authc,kickout");
        // /sys/user/logout 登出接口CustomRealm
        filterMap.put("/sys/user/logout", "logout");

        return shiroFilterFactoryBean;
    }

    @Bean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();

        // 开启Shiro的注解(如@RequiresRoles,@RequiresPermissions)
        defaultAdvisorAutoProxyCreator.setProxyTargetClass(true);

        return defaultAdvisorAutoProxyCreator;
    }

    private KickoutSessionFilter newKickoutSessionFilter() {
        KickoutSessionFilter kickoutSessionFilter = new KickoutSessionFilter();
        kickoutSessionFilter.setSessionManager(sessionManager());
        kickoutSessionFilter.setCacheManager(cacheManager());

        return kickoutSessionFilter;
    }

}
