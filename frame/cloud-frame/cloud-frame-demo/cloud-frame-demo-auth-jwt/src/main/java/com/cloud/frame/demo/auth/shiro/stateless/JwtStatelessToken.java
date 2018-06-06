package com.cloud.frame.demo.auth.shiro.stateless;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.shiro.authc.AuthenticationToken;

import java.util.Map;

/**
 * Created by wd on 2018/5/25.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class JwtStatelessToken implements AuthenticationToken {

    private String appid;
    private Map<String, ?> params;
    private String jwt;// json web token
    private String host;// 客户端IP

    @Override
    public Object getPrincipal() {
        return this.appid;
    }

    @Override
    public Object getCredentials() {
        return this.jwt;
    }

    public JwtStatelessToken(String jwt, String host) {
//        this.username = username;
        this.jwt = jwt;
        this.host = host;
    }
}