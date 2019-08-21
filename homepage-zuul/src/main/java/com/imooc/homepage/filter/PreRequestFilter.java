package com.imooc.homepage.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

/**
 * 在PreRequest过滤器中存储客户端发起请求的时间戳
 */
@Component
public class PreRequestFilter extends ZuulFilter {
    /**
     * 指定过滤器的类型
     * @return
     */
    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    /**
     * 过滤器优先级，数值越小优先级越高
     * @return
     */
    @Override
    public int filterOrder() {
        return 0;
    }

    /**
     * 是否启用当前过滤器，true启动，默认为false
     * @return
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        // RequestContext用于在过滤器之间传递消息
        RequestContext ctx = RequestContext.getCurrentContext();
        // 设置请求开始时间 startTime
        ctx.set("startTime", System.currentTimeMillis());
        return null;
    }
}
