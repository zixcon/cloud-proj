package com.cloud.frame.gateway.filter;

import com.cloud.frame.demo.base.Result;
import com.cloud.frame.demo.constant.LogTraceConstant;
import com.cloud.frame.demo.util.GsonUtil;
import com.cloud.frame.gateway.client.AuthorizationClient;
import com.google.common.base.Strings;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.http.MediaType;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_DECORATION_FILTER_ORDER;

/**
 * Created by wd on 2018/5/10.
 */
public class AuthorizationFilter extends ZuulFilter {

    private static Logger log = LoggerFactory.getLogger(AuthorizationFilter.class);

    @Value("${zuul.routes.ucenter.ignored-patterns}")
    private String ignorePatterns;

    private static final PathMatcher pathMatcher = new AntPathMatcher();

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
        return PRE_DECORATION_FILTER_ORDER + 1;
    }

    @Override
    public boolean shouldFilter() {
        if (!Strings.isNullOrEmpty(ignorePatterns)) {
            RequestContext ctx = RequestContext.getCurrentContext();
            String[] ignoreArr = ignorePatterns.split(",");
            String uri = ctx.getRequest().getRequestURI();
            for (String pattern : ignoreArr) {
                if(this.pathMatcher.match(pattern, uri)) {
                    return false;
                }
            }

        }
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext ctx = RequestContext.getCurrentContext();
        String traceId = ctx.getZuulRequestHeaders().get(LogTraceConstant.TRACEID);
        String authHeader = null;
        try {
            HttpServletRequest request = ctx.getRequest();
            authHeader = request.getHeader("Authorization");
            log.info("request url: {}, traceId:{}", request.getRequestURI(), traceId);
        } catch (Exception e) {
        }
        if (Strings.isNullOrEmpty(authHeader)) {
            //MediaType.APPLICATION_JSON_UTF8_VALUE
            ctx.getResponse().setContentType("text/html;charset=UTF-8");
            ctx.setSendZuulResponse(false);
            ctx.setResponseStatusCode(200);
            Map<String, Object> res = new HashMap<>();
            res.put("success", true);
            res.put("code", 4001);
            res.put("message", "无token签名");
            ctx.setResponseBody(GsonUtil.getInstance().toJson(res));
        } else {
            Result<Void> result = authorizationClient.verify(authHeader);
            if (result.getSuccess()) {
                ctx.setSendZuulResponse(true);
                ctx.setResponseStatusCode(200);
            } else {
                ctx.getResponse().setContentType("text/html;charset=UTF-8");
                ctx.setSendZuulResponse(false);
                ctx.setResponseStatusCode(200);
                Map<String, Object> res = new HashMap<>();
                res.put("success", true);
                res.put("code", 4002);
                res.put("message", "签名认证失败");
                ctx.setResponseBody(GsonUtil.getInstance().toJson(res));
            }
        }
        return null;
    }
}
