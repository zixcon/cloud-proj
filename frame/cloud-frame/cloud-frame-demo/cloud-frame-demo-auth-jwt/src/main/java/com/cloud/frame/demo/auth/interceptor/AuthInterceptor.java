package com.cloud.frame.demo.auth.interceptor;

import com.cloud.frame.demo.auth.config.JwtConfig;
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
        Result<Void> result = Result.build();
        result.setSuccess(false);
        String authHeader;
        try {
            authHeader = request.getHeader(jwtConfig.getHeader());
        } catch (Exception e) {
            logger.error("验证失败", e);
            result = Result.build();
            result.setSuccess(false);
            result.setCode(AuthCodeConstant.JWT_ERRCODE_NULL + "");
            result.setMessage("token 获取异常");
            print(response, result);
            return false;
        }
        if (StringUtils.isEmpty(authHeader)) {
            logger.info("验证失败");
            result.setSuccess(false);
            result.setCode(AuthCodeConstant.JWT_ERRCODE_NULL + "");
            result.setMessage("签名验证不存在");
            print(response, result);
            return false;
        } else {
            //验证JWT的签名，返回CheckResult对象
            Result<Claims> checkResult = JwtUtils.validateJWT(authHeader, jwtConfig.getSecret());
            if (checkResult.getSuccess()) {
                return true;
            } else {
                int code = Integer.valueOf(checkResult.getCode());
                switch (code) {
                    // 签名验证不通过
                    case AuthCodeConstant.JWT_ERRCODE_FAIL:
                        logger.info("签名验证不通过");
                        result.setSuccess(false);
                        result.setCode(checkResult.getCode() + "");
                        result.setMessage("签名验证不通过");
                        print(response, result);
                        break;
                    // 签名过期，返回过期提示码
                    case AuthCodeConstant.JWT_ERRCODE_EXPIRE:
                        logger.info("签名过期");
                        result.setSuccess(false);
                        result.setCode(checkResult.getCode() + "");
                        result.setMessage("签名过期");
                        print(response, result);
                        break;
                    default:
                        break;
                }
                return false;
            }
        }
    }
}
