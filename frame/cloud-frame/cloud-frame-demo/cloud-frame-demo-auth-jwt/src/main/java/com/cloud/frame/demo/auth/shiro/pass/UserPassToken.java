package com.cloud.frame.demo.auth.shiro.pass;

import org.apache.shiro.authc.UsernamePasswordToken;

/**
 * Created by wd on 2018/5/31.
 */
public class UserPassToken extends UsernamePasswordToken {
    public UserPassToken(String username, String password) {
        super(username, password);
    }

    //    private Map<String, ?> params;
//    private String jwt;// json web token
//    private String host;// 客户端IP
//
//    @Override
//    public Object getPrincipal() {
//        return super.getPrincipal();
//    }
//
//    @Override
//    public Object getCredentials() {
//        return this.jwt;
//    }
//
//    public UserPassToken(String username, String password, String jwt, String host) {
//        super(username, password);
//        this.jwt = jwt;
//        this.host = host;
//    }
}
