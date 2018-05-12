package com.cloud.frame.gateway.filter;

import com.cloud.frame.demo.base.Result;
import com.cloud.frame.gateway.client.AuthorizationClient;
import com.google.common.base.Strings;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by wd on 2018/5/10.
 */
public class AuthorizationFilter extends ZuulFilter {

    private static Logger log = LoggerFactory.getLogger(AuthorizationFilter.class);

    @Autowired
    private AuthorizationClient authorizationClient;

    /**
     * pre：可以在请求被路由之前调用。
     * routing：在路由请求时候被调用。
     * post：在routing和error过滤器之后被调用。
     * error：处理请求时发生错误时被调用。
     *
     * @return
     */
    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        //优先级，数字越大，优先级越低
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        log.info("request url: {}", request.getRequestURI());
        String authHeader = null;
        try {
            authHeader = request.getHeader("Authorization");
        } catch (Exception e) {
        }
        if (Strings.isNullOrEmpty(authHeader)) {
            ctx.setSendZuulResponse(true);
            ctx.setResponseStatusCode(200);
//            ctx.setSendZuulResponse(false);
//            ctx.setResponseStatusCode(401);
//            ctx.setResponseBody("{\"success\": false,\"code\": \"4001\",\"message\": \"签名认证失败\"}");
        } else {
            Result<Void> result = authorizationClient.verify(authHeader);
            if (result.getSuccess()) {
                ctx.setSendZuulResponse(true);
                ctx.setResponseStatusCode(200);
            } else {
                ctx.setSendZuulResponse(false);
                ctx.setResponseStatusCode(200);
                ctx.setResponseBody("{\"success\": false,\"code\": \"4002\",\"message\": \"签名认证失败\"}");
            }
        }
        return null;
    }
}
