package com.springboot.app.zuul.server.filters;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

@Component
public class postElapsedTimeFilter extends ZuulFilter {
	
	private static Logger log = LoggerFactory.getLogger(postElapsedTimeFilter.class);

	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public Object run() throws ZuulException {
		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletRequest request = ctx.getRequest();
		
		log.info("Entering post filter");
		
		Long startTime = (Long) request.getAttribute("startTime");
		Long endTime = System.currentTimeMillis();
		Long elapsedTime = endTime - startTime;
		log.info(String.format("Elapsed time in seconds %s s", elapsedTime.doubleValue()/1000.00));
		log.info(String.format("Elapsed time in milliseconds %s ms", elapsedTime));
		return null;
	}

	@Override
	public String filterType() {
		return "post";
	}

	@Override
	public int filterOrder() {
		return 1;
	}

}
