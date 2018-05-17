package com.cloud.frame.demo.auth.interceptor;

import com.cloud.frame.demo.auth.config.JwtConfig;
import com.cloud.frame.demo.auth.service.AuthorizationService;
import com.cloud.frame.demo.auth.util.JwtUtils;
import com.cloud.frame.demo.base.Result;
import com.cloud.frame.demo.constant.AuthCodeConstant;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by wd on 2018/5/3.
 */
@Component
@Slf4j
public class AuthInterceptor implements HandlerInterceptor {

    @Autowired
    private JwtConfig jwtConfig;

    @Autowired
    private AuthorizationService authorizationService;


    private static final Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd HH:mm:ss")
            .create();

    private static final Logger logger = LoggerFactory.getLogger(AuthInterceptor.class);

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        String url = request.getRequestURI();
        log.info("请求地址：{}", url);
        if (handler instanceof HandlerMethod) {
            return this.handleToken(request, response);
        } else {
            return true;
        }
    }

    public void print(HttpServletResponse response, Object message) {
        try {
            response.setStatus(HttpStatus.OK.value());
            response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
            response.setHeader("Cache-Control", "no-cache, must-revalidate");
            PrintWriter writer = response.getWriter();
            writer.write(gson.toJson(message));
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
//                           ModelAndView modelAndView) throws Exception {
//        if (response.getStatus() == 500) {
//            modelAndView.setViewName("/error/500");
//        } else if (response.getStatus() == 404) {
//            modelAndView.setViewName("/error/404");
//        }
//    }

    /**
     * 该方法也是需要当前对应的Interceptor的preHandle方法的返回值为true时才会执行。该方法将在整个请求完成之后，也就是DispatcherServlet渲染了视图执行，
     * 这个方法的主要作用是用于清理资源的，当然这个方法也只能在当前这个Interceptor的preHandle方法的返回值为true时才会执行。
     */
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
    }

    private boolean handleToken(HttpServletRequest request, HttpServletResponse response) {
        Result<Void> result;
        String token;
        try {
            token = request.getHeader(jwtConfig.getHeader());
        } catch (Exception e) {
            logger.error("验证失败", e);
            result = Result.build();
            result.setSuccess(false);
            result.setCode(AuthCodeConstant.JWT_ERRCODE_NULL + "");
            result.setMessage("token 获取异常");
            print(response, result);
            return false;
        }
        result = authorizationService.authorization(token);
        if (result.getSuccess()) {
            return true;
        } else {
            print(response, result);
            return false;
        }
    }
}
