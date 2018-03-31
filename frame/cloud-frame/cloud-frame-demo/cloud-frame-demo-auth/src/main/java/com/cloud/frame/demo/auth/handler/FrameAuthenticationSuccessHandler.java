package com.cloud.frame.demo.auth.handler;

import com.cloud.frame.demo.base.Result;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by wd on 2018/3/30.
 */
//@Component
//public class FrameAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
//
//    @Override
//    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
//        logger.info("登录成功");
//        if ("json".equals(securityProperties.getBrowser().getLoginType())) {
//            response.setContentType("application/json;charset=utf-8");
//            Result result = Result.build();
//            result.setBody(authentication);
//            response.getWriter().write(result.toString());
//        } else {
//            super.onAuthenticationSuccess(request, response, authentication);
//        }
//    }
//}
