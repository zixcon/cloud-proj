package com.cloud.frame.gateway.config;

import com.cloud.frame.gateway.filter.RequestLocalFilter;
import com.cloud.frame.gateway.filter.RequestLocalThread;
import com.netflix.zuul.context.RequestContext;
import feign.RequestInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

/**
 * Created by wd on 2018/5/14.
 */
@Configuration
@Slf4j
public class FeignConfig {

    @Bean
    public RequestLocalFilter requestLocalFilter() {
        return new RequestLocalFilter();
    }

    /**
     * cloud feign 调用微服务转发不了request请求头和参数问题解决方案
     */
    @Bean
    public RequestInterceptor requestInterceptor() {

        return (requestTemplate) -> {
            RequestContext ctx = RequestLocalThread.get();
            if (null != ctx) {
                Map<String, String> headers = ctx.getZuulRequestHeaders();
                if (null != headers) {
                    for (Map.Entry<String, String> header : headers.entrySet()) {
                        requestTemplate.header(header.getKey(), header.getValue());
                    }
                }
            }

//            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
//            HttpServletRequest request = attributes.getRequest();
//            Enumeration<String> headerNames = request.getHeaderNames();
//            if (headerNames != null) {
//                while (headerNames.hasMoreElements()) {
//                    String name = headerNames.nextElement();
//                    String values = request.getHeader(name);
//                    requestTemplate.header(name, values);
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
        };
    }
}
