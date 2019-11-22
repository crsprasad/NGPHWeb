package com.logica.ngph.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import com.logica.ngph.web.wrapper.CustomErrorResponseWrapper;

public class CustomErrorResponseFilter implements Filter {

	private FilterConfig filterConfig = null;

	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig = filterConfig;
	}

	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
		CustomErrorResponseWrapper wrapper = new CustomErrorResponseWrapper((HttpServletResponse) resp);
		HttpServletResponse response = (HttpServletResponse) resp;
		chain.doFilter(req, wrapper);
	}

	public void destroy() {
		this.filterConfig = null;
	}

}
