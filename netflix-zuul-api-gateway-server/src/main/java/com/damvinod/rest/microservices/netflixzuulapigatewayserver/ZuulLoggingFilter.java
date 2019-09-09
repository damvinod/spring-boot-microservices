package com.damvinod.rest.microservices.netflixzuulapigatewayserver;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

@Component
public class ZuulLoggingFilter extends ZuulFilter {

	private Logger log = LoggerFactory.getLogger(this.getClass());
			
	@Override
	public Object run() {
		
		HttpServletRequest request = RequestContext.getCurrentContext().getRequest();
		
		log.info("request -> {} request uri -> {}", request, request.getRequestURI());
		
		return null;
	}

	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public int filterOrder() {
		return 1;
	}

	@Override
	public String filterType() {
		return "pre";//"post","error"
	}

	
	
}
