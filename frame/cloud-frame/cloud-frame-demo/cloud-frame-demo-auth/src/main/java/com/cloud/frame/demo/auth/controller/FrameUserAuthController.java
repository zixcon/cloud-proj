package com.cloud.frame.demo.auth.controller;

import com.cloud.frame.demo.auth.handler.FrameAuthenticationFailureHandler;
import com.cloud.frame.demo.auth.handler.FrameAuthenticationSuccessHandler;
import com.cloud.frame.demo.base.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by wd on 2018/3/30.
 */
@RestController
public class FrameUserAuthController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private RequestCache requestCache;
    @Autowired
    private RedirectStrategy redirectStrategy;
    @Autowired
    private SecurityProperties securityProperties;

    @Autowired
    private FrameAuthenticationSuccessHandler frameAuthenticationSuccessHandler;
    @Autowired
    private FrameAuthenticationFailureHandler frameAuthenticationFailureHandler;

    /**
     * 当需要身份认证是跳转到这里
     */
    @RequestMapping("/authentication/require")
    @ResponseStatus(code = HttpStatus.UNAUTHORIZED)
    public Result requireAuthentication(HttpServletRequest request, HttpServletResponse response) throws IOException {
        SavedRequest savedRequest = requestCache.getRequest(request, response);
        if (savedRequest != null) {
            String targeUrl = savedRequest.getRedirectUrl();
            logger.info("引发的请求是:" + targeUrl);
            //如果是html请求，跳转到标准登录页，否则往下走返回json字符串
            if (StringUtils.endsWithIgnoreCase(targeUrl, ".html")) {
                redirectStrategy.sendRedirect(request, response, securityProperties.getBrowser().getLoginPage());
            }
        }
        Result result = Result.build();
        result.setMessage("访问的服务需要身份认证，请引导用户到登录页");
        return result;
    }
}
