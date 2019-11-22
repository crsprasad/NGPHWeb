package com.logica.ngph.web.interceptor;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class JsessionIdAvoiderFilter implements Filter {

	public void destroy() {
		// TODO Auto-generated method stub

	}

	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		boolean allowFilterChain = redirectToAvoidJsessionId(
				(HttpServletRequest) req, (HttpServletResponse) res); 
		
		if (allowFilterChain)
			chain.doFilter(req, res);
	}

	public static boolean redirectToAvoidJsessionId(HttpServletRequest req,HttpServletResponse res) 
	{
		HttpSession s = req.getSession();
		if (s.isNew()) {
			// i'm not quite sure whether this is safe. A user without cookies
			// should never be allowed on this application in the first place,
			// but a endless loop at this place could lead to a 'pants down' of
			// several important applications
			if (!(req.isRequestedSessionIdFromCookie() && req
					.isRequestedSessionIdFromURL())) {
				// yeah we have request parameters actually on that request.
				String qs = req.getQueryString();
				String requestURI = req.getRequestURI();
				try {
					if(qs==null){
						qs="";
					}
					res.sendRedirect(requestURI + "" + qs);
					return false;
				} catch (IOException e) {
					System.out.println("Error sending redirect. "
							+ e.getMessage());
				}
			}
		}
		return true;
	}

	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
