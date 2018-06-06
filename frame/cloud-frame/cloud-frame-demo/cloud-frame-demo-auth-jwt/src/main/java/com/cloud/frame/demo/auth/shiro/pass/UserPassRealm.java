package com.cloud.frame.demo.auth.shiro.pass;

import com.cloud.frame.demo.auth.dao.entity.AccountInfoEntity;
import com.cloud.frame.demo.auth.service.AccountInfoService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by wd on 2018/5/17.
 */
@Component
public class UserPassRealm extends AuthorizingRealm implements InitializingBean {

    @Autowired
    private AccountInfoService accountInfoService;

    @Override
    public void afterPropertiesSet() throws Exception {
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        //散列算法:这里使用MD5算法;
        hashedCredentialsMatcher.setHashAlgorithmName("md5");
        //散列的次数，比如散列两次，相当于 md5(md5(""));
        hashedCredentialsMatcher.setHashIterations(3);
        this.setCredentialsMatcher(hashedCredentialsMatcher);
    }

    /**
     * 授权
     *
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        String username = (String) principalCollection.getPrimaryPrincipal();
        //在数据库中查询用户拥有的角色/权限
//        authorizationInfo.setRoles(accountInfoService.findRoles(username));
//        authorizationInfo.setStringPermissions(accountInfoService.findPermissions(username));
        return authorizationInfo;
    }

    /**
     * 验证
     *
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UserPassToken userPassToken = (UserPassToken) token;
        String username = (String) userPassToken.getPrincipal();
        AccountInfoEntity user = accountInfoService.getEntity(username);
        if (user == null) {
            throw new UnknownAccountException(); //没找到账号
        }

        if (Boolean.TRUE.equals(user.getLocked())) {
            throw new LockedAccountException(); //账号被锁定
        }

        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                user.getUserName(),
                user.getPassword(),
                ByteSource.Util.bytes(username + user.getSalt()), //salt = username+salt
                getName());


        return authenticationInfo;
    }


}
