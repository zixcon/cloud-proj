package com.cloud.frame.demo.auth.shiro.stateless;

import com.cloud.frame.demo.auth.config.JwtConfig;
import com.cloud.frame.demo.auth.service.AccountInfoService;
import com.cloud.frame.demo.auth.util.JwtUtils;
import com.google.common.collect.Sets;
import io.jsonwebtoken.*;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.xml.bind.DatatypeConverter;
import java.util.Set;

/**
 * Created by wd on 2018/5/30.
 */
@Component
public class JwtStatelessRealm extends AuthorizingRealm implements InitializingBean {

    @Autowired
    private JwtConfig jwtConfig;

    @Autowired
    private AccountInfoService accountInfoService;

    @Autowired
    private JwtStatelessMatcher jwtStatelessMatcher;

    @Override
    public void afterPropertiesSet() throws Exception {
        this.setCredentialsMatcher(jwtStatelessMatcher);
        this.setAuthenticationTokenClass(JwtStatelessToken.class);
    }


    @Override
    public boolean supports(AuthenticationToken token) {
        //仅支持 JwtStatelessToken 类型的Token
        return token instanceof JwtStatelessToken;
    }

    /**
     * 认证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token)
            throws AuthenticationException {
        JwtStatelessToken jwtToken = (JwtStatelessToken) token;
        String jwt = (String) jwtToken.getCredentials();
//        JwtPlayload jwtPlayload;
        Claims claims;
        try {
            claims = JwtUtils.parseJWT(jwt, jwtConfig.getAccessSecret());
//            claims = Jwts.parser()
//                    .setSigningKey(DatatypeConverter.parseBase64Binary(jwtConfig.getAccessSecret()))
//                    .parseClaimsJws(jwt)
//                    .getBody();
//            jwtPlayload = new JwtPlayload();
//            jwtPlayload.setId(claims.getId());
//            jwtPlayload.setUserId(claims.getSubject());// 用户名
//            jwtPlayload.setIssuer(claims.getIssuer());// 签发者
//            jwtPlayload.setIssuedAt(claims.getIssuedAt());// 签发时间
//            jwtPlayload.setAudience(claims.getAudience());// 接收方
//            jwtPlayload.setRoles(claims.get("roles", String.class));// 访问主张-角色
//            jwtPlayload.setPerms(claims.get("perms", String.class));// 访问主张-权限
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
        // 如果要使token只能使用一次，此处可以过滤并缓存jwtPlayload.getId()
        // 可以做签发方验证
        // 可以做接收方验证
//        return new SimpleAuthenticationInfo(jwtPlayload, Boolean.TRUE, getName());
        return new SimpleAuthenticationInfo(claims, jwt, getName());
    }

    /**
     * 授权,JWT已包含访问主张只需要解析其中的主张定义就行了
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        Claims claims = (Claims) principals.getPrimaryPrincipal();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        // 解析角色并设置
        Set<String> roles = Sets.newHashSet(StringUtils.split(claims.get("roles", String.class), ","));
        info.setRoles(roles);
        // 解析权限并设置
        Set<String> permissions = Sets.newHashSet(StringUtils.split(claims.get("perms", String.class), ","));
        info.setStringPermissions(permissions);
        return info;
//        JwtPlayload jwtPlayload = (JwtPlayload) principals.getPrimaryPrincipal();
//        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
//        // 解析角色并设置
//        Set<String> roles = Sets.newHashSet(StringUtils.split(jwtPlayload.getRoles(), ","));
//        info.setRoles(roles);
//        // 解析权限并设置
//        Set<String> permissions = Sets.newHashSet(StringUtils.split(jwtPlayload.getPerms(), ","));
//        info.setStringPermissions(permissions);
//        return info;
    }


}