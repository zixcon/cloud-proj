package com.cloud.frame.demo.auth.shiro;

import com.cloud.frame.demo.auth.config.JwtConfig;
import com.cloud.frame.demo.auth.service.AccountInfoService;
import com.cloud.frame.demo.auth.shiro.pass.UserPassRealm;
import com.cloud.frame.demo.auth.shiro.stateless.JwtStatelessRealm;
import org.apache.shiro.realm.Realm;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by wd on 2018/5/25.
 */
@Component
public class RealmManager implements InitializingBean {

    @Autowired
    private AccountInfoService accountInfoService;

    @Autowired
    private JwtConfig jwtConfig;

    @Autowired
    private JwtStatelessRealm jwtStatelessRealm;

    @Autowired
    private UserPassRealm userPassRealm;

    private List<Realm> realms;

    @Override
    public void afterPropertiesSet() throws Exception {
        List<Realm> realmList = new LinkedList<>();
        realmList.add(jwtStatelessRealm);
        realmList.add(userPassRealm);
        realms = new ArrayList<>(realmList.size());
        realms.addAll(Collections.unmodifiableList(realmList));
    }

    public List<Realm> getRealms() {
        return realms;
    }

//    /**
//     * 多个realm实现
//     */
//    @Override
//    protected AuthenticationInfo doMultiRealmAuthentication(Collection<Realm> realms, AuthenticationToken token) {
//        return super.doMultiRealmAuthentication(realms, token);
//    }
//
//    /**
//     * 调用单个realm执行操作
//     */
//    @Override
//    protected AuthenticationInfo doSingleRealmAuthentication(Realm realm, AuthenticationToken token) {
//
//        // 如果该realms不支持(不能验证)当前token
//        if (!realm.supports(token)) {
//            throw new ShiroException("token错误!");
//        }
//        AuthenticationInfo info = null;
//        try {
//            info = realm.getAuthenticationInfo(token);
//
//            if (info == null) {
//                throw new ShiroException("token不存在!");
//            }
//        } catch (Exception e) {
//            throw new ShiroException("用户名或者密码错误!");
//        }
//        return info;
//    }
//
//    /**
//     * 判断登录类型执行操作
//     */
//    @Override
//    protected AuthenticationInfo doAuthenticate(AuthenticationToken authenticationToken) throws AuthenticationException {
//        this.assertRealmsConfigured();
//
//        Realm realm = null;
//
//        AuthenticationToken token = authenticationToken;
//        try {
//            JwtUtils.parseJWT((String) token.getCredentials(), jwtConfig.getAccessSecret());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
////        DefaultUsernamepasswordToken token = (DefaultUsernamepasswordToken) authenticationToken;
////        if (token.getLoginType().equals("huimai")) {
////            realm = (Realm) this.definedRealms.get("defaultJdbcRealm");
////        }
////        if (token.getLoginType().equals("shiro")) {
////            realm = (Realm) this.definedRealms.get("shiroDbRealm");
////        }
//        if (realm == null) {
//            return null;
//        }
//
//        return this.doSingleRealmAuthentication(realm, authenticationToken);
//    }
//
//    /**
//     * 判断realm是否为空
//     */
//    @Override
//    protected void assertRealmsConfigured() throws IllegalStateException {
//        if (CollectionUtils.isEmpty(this.definedRealms)) {
//            throw new ShiroException("值传递错误!");
//        }
//    }
}