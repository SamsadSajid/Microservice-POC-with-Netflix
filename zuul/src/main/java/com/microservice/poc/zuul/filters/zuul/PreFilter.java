package com.microservice.poc.zuul.filters.zuul;

import com.microservice.poc.zuul.utils.Utility;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

import java.time.Instant;

import static com.netflix.zuul.context.RequestContext.getCurrentContext;

public class PreFilter extends ZuulFilter {

    @Override
    public Object run() {
        RequestContext ctx = getCurrentContext();

        ctx.set(Utility.STARTTIME, Instant.now());

        return null;
    }

    @Override
    public String filterType() {

        return Utility.PRE;
    }

    @Override
    public int filterOrder() {

        return 1;
    }

    @Override
    public boolean shouldFilter() {

        return true;
    }

}
