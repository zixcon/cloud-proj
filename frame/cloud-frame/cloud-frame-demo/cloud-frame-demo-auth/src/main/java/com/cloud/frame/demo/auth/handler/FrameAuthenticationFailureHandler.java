//package com.cloud.frame.demo.auth.handler;
//
//import com.cloud.frame.demo.base.Result;
//import org.springframework.http.HttpStatus;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
//import org.springframework.stereotype.Component;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
///**
// * Created by wd on 2018/3/30.
// */
//@Component
//public class FrameAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {
//
//    @Override
//    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
//        if ("json".equals(securityProperties.getBrowser().getLoginType())) {
//            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
//            response.setContentType("application/json;charset=utf-8");
//            Result result = Result.build();
//            result.setResult(false);
//            result.setMessage("auth failed");
//            response.getWriter().write(result.toString());
//        } else {
//            super.onAuthenticationFailure(request, response, exception);
//        }
//    }
//}
