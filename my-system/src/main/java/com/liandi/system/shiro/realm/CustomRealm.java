package com.liandi.system.shiro.realm;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.liandi.system.service.UserService;
import com.liandi.system.service.dto.UserDTO;

import lombok.extern.slf4j.Slf4j;

/**
 * shiro的自定义Realm
 * 
 * @author czg
 * @date 2019/9/18 9:48
 */
@Slf4j
public class CustomRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    @Value("${shiro.salt}")
    private String salt;

    /**
     * 获取用户的角色和权限的逻辑，给shiro做权限判断
     * 
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

        if (log.isDebugEnabled()) {
            log.debug("==============用户开始授权==============");
        }

        if (principals == null || principals.isEmpty()) {
            log.warn("==============用户没有任何权限==============");
            // throw new AuthorizationException("PrincipalCollection method argument cannot be null");
            return null;
        }

        UserDTO user = (UserDTO)getAvailablePrincipal(principals);

        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();

        if (log.isDebugEnabled()) {
            log.debug("获取角色信息：{}", user.getRoleSet());
            log.debug("获取权限信息：{}", user.getPowerSet());
        }

        simpleAuthorizationInfo.setRoles(user.getRoleSet());
        simpleAuthorizationInfo.setStringPermissions(user.getPowerSet());

        return simpleAuthorizationInfo;
    }

    /**
     * 获取用户信息的业务逻辑，shiro登录认证
     * 
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        if (log.isDebugEnabled()) {
            log.debug("==============用户开始认证==============");
        }

        UsernamePasswordToken upToken = (UsernamePasswordToken)token;
        String loginName = upToken.getUsername();

        if (StringUtils.isBlank(loginName)) {
            throw new AccountException("登陆名不能为空");
        }

        UserDTO user = userService.getUserByLoginName(loginName);

        if (user == null) {
            throw new UnknownAccountException("登陆名和密码不正确");
        }

        return new SimpleAuthenticationInfo(user, user.getPswd(), ByteSource.Util.bytes(salt), getName());
    }

}