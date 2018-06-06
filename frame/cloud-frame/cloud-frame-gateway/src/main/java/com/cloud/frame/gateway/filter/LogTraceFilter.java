package com.cloud.frame.gateway.filter;

import com.cloud.frame.demo.constant.LogTraceConstant;
import com.google.common.base.Strings;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.http.HttpStatus;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_DECORATION_FILTER_ORDER;

/**
 * Created by wd on 2018/5/14.
 */
public class LogTraceFilter extends ZuulFilter {

    private static Logger log = LoggerFactory.getLogger(LogTraceFilter.class);

    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return PRE_DECORATION_FILTER_ORDER;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        String traceId = ctx.getZuulRequestHeaders().get(LogTraceConstant.TRACEID);
        if (Strings.isNullOrEmpty(traceId)) {
            traceId = UUID.randomUUID().toString();
        }
        ctx.addZuulRequestHeader(LogTraceConstant.TRACEID, traceId);
        String token = request.getHeader("Authorization");
        ctx.addZuulRequestHeader(LogTraceConstant.TRACEID, traceId);
        ctx.addZuulRequestHeader("Authorization", token);
        RequestLocalThread.set(ctx);
        // 对该请求进行路由
        ctx.setSendZuulResponse(true);
        ctx.setResponseStatusCode(HttpStatus.OK.value());
        log.info("设置请求追踪日志：{}", traceId);
        return null;
    }
}
