package com.cloud.frame.demo.auth.interceptor;

import com.cloud.frame.demo.base.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Created by wd on 2018/5/9.
 */
@RestControllerAdvice
public class GlobalExceptionInterceptor {


    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result<String> handleException(Exception e) {
        Result<String> result = new Result<>();
        result.setSuccess(false);
        result.setMessage("服务器异常：" + e.getMessage());
        return result;
    }
}
