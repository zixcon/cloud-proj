package com.cloud.frame.demo.consumer.config;

import com.cloud.frame.demo.consumer.interceptor.FeignRequestInterceptor;
import feign.RequestInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by wd on 2018/5/14.
 */
@Configuration
@Slf4j
public class FeignConfig {

    /**
     * cloud feign 调用微服务转发不了request请求头和参数问题解决方案
     */
    @Bean
    public RequestInterceptor requestInterceptor() {
        return new FeignRequestInterceptor();

//        return (requestTemplate) -> {
//            // 头
//            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
//            if (null != attributes) {
//                HttpServletRequest request = attributes.getRequest();
//                if (null != request) {
//                    Enumeration<String> headerNames = request.getHeaderNames();
//                    if (headerNames != null) {
//                        while (headerNames.hasMoreElements()) {
//                            String name = headerNames.nextElement();
//                            String values = request.getHeader(name);
//                            requestTemplate.header(name, values);
//                        }
//                    }
//                }
//            }
        // 参数
//            Enumeration<String> bodyNames = request.getParameterNames();
//            StringBuffer body = new StringBuffer();
//            if (bodyNames != null) {
//                while (bodyNames.hasMoreElements()) {
//                    String name = bodyNames.nextElement();
//                    String values = request.getParameter(name);
//                    body.append(name).append("=").append(values).append("&");
//                }
//            }
//            if (body.length() != 0) {
//                body.deleteCharAt(body.length() - 1);
//                requestTemplate.body(body.toString());
//                log.info("feign interceptor body:{}", body.toString());
//            }
//        };
    }
}
