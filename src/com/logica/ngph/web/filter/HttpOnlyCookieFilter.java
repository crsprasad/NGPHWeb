package com.logica.ngph.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HttpOnlyCookieFilter implements Filter{

	public void destroy() {
		
		
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		 HttpServletRequest httpRequest = (HttpServletRequest) request;
		 HttpServletResponse httpResponse = (HttpServletResponse) response;

		 if(httpResponse !=null){
			 httpResponse.setHeader("Cache-Control", "no-cache, no-store");
			 httpResponse.setHeader("Pragma", "public , no-cache"); 
			 httpResponse.setDateHeader("Expires", -1);

//				Cookie cookies[] = httpRequest.getCookies();
//				if (cookies != null) {
//					for (int a=0; a< cookies.length; a++) {										
//						if(cookies[a].getName().equals("JSESSIONID")){
//							String sessionid = cookies[a].getValue();
//							String contextPath = httpRequest.getContextPath();
//							String secure = "";
//							if (httpRequest.isSecure()) {
//								secure = "; Secure"; 
//							}
//							httpResponse.setHeader("Set-Cookie", "JSESSIONID=" + sessionid + "; Path=" + contextPath + "; HttpOnly" + secure);
//						}
//					}
						
						
//					}

		 }
		chain.doFilter(request,response);
		
	}

	public void init(FilterConfig arg0) throws ServletException {
		
		
	}

}
