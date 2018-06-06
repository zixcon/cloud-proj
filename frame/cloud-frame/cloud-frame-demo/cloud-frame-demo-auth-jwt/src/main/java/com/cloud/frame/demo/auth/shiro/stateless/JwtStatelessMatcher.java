package com.cloud.frame.demo.auth.shiro.stateless;

import com.cloud.frame.demo.auth.config.JwtConfig;
import com.cloud.frame.demo.auth.util.JwtUtils;
import io.jsonwebtoken.*;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by wd on 2018/5/30.
 */
@Component
public class JwtStatelessMatcher implements CredentialsMatcher {

    @Autowired
    private JwtConfig jwtConfig;

    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        boolean result = false;
        try {
            String jwt = (String) info.getCredentials();
            Claims claims = JwtUtils.parseJWT(jwt, jwtConfig.getAccessSecret());
            result = true;
        } catch (ExpiredJwtException e) {
            throw new AuthenticationException("JWT 令牌过期:" + e.getMessage());
        } catch (UnsupportedJwtException e) {
            throw new AuthenticationException("JWT 令牌无效:" + e.getMessage());
        } catch (MalformedJwtException e) {
            throw new AuthenticationException("JWT 令牌格式错误:" + e.getMessage());
        } catch (SignatureException e) {
            throw new AuthenticationException("JWT 令牌签名无效:" + e.getMessage());
        } catch (IllegalArgumentException e) {
            throw new AuthenticationException("JWT 令牌参数异常:" + e.getMessage());
        } catch (Exception e) {
            throw new AuthenticationException("JWT 令牌错误:" + e.getMessage());
        }
        return result;
    }
}
