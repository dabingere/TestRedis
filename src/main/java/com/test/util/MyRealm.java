package com.test.util;

import com.test.mapper.UserMapper;
import com.test.model.User;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

public class MyRealm extends AuthorizingRealm {
@Autowired
private UserMapper userMapper;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        return null;
    }

    /**
     * 身份认证
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        Object username = authenticationToken.getPrincipal();//获取身份（账号）
        Object password = authenticationToken.getCredentials();//获取凭证（密码）
        User user = new User();
        user.setUsername(username.toString());
        user.setPassword(password.toString());
        User u = userMapper.Login(user);
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(
            u.getUsername(),u.getPassword(),""
        );
        return info;
    }
}
