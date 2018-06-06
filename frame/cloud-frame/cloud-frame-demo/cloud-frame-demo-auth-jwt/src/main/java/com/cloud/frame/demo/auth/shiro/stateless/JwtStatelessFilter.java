package com.cloud.frame.demo.auth.shiro.stateless;

import com.cloud.frame.demo.auth.config.JwtConfig;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by wd on 2018/5/30.
 */
@Component
@Slf4j
public class JwtStatelessFilter extends AccessControlFilter {

    @Autowired
    private JwtConfig jwtConfig;


    @Override
    protected boolean isAccessAllowed(ServletRequest servletRequest, ServletResponse servletResponse, Object o) throws Exception {
        Subject subject = getSubject(servletRequest, servletResponse);
        if (null != subject && subject.isAuthenticated()) {
            return true;//已经认证过直接放行
        }
        return false;//转到拒绝访问处理逻辑
    }

    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        if (isJwtSubmission(servletRequest)) {
            AuthenticationToken token = createToken(servletRequest, servletResponse);
            try {
                Subject subject = getSubject(servletRequest, servletResponse);
                subject.login(token);
            } catch (AuthenticationException e) {
                log.error(e.getMessage(), e);
                WebUtils.toHttp(servletResponse).sendError(HttpServletResponse.SC_UNAUTHORIZED, e.getMessage());
                return false;
            }
        }
        return true;
    }

    protected AuthenticationToken createToken(ServletRequest request, ServletResponse response) {
        String jwt = ((HttpServletRequest)request).getHeader(jwtConfig.getHeader());
        String host = request.getRemoteHost();
        log.info("authenticate jwt token:" + jwt);
        return new JwtStatelessToken(jwt, host);
    }

    protected boolean isJwtSubmission(ServletRequest request) {
        String jwt = ((HttpServletRequest)request).getHeader(jwtConfig.getHeader());
        return (request instanceof HttpServletRequest)
                && !StringUtils.isEmpty(jwt);
    }
}
