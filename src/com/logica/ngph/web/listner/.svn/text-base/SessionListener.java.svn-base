package com.logica.ngph.web.listner;
 
import java.util.Map;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

 
public class SessionListener implements HttpSessionListener {
 
	private static final String CONSTANT_USER_MAP = "UserMap"; 
	private static final String CONSTANT_LOGGEDIN_USER = "loggedInUser"; 
 

  public void sessionCreated(HttpSessionEvent arg0) {
	 System.out.println("session created");
  }

  public void sessionDestroyed(HttpSessionEvent arg0) {
	  System.out.println("before session delete");
	  Map<String, Object> userMap = (Map<String, Object>)arg0.getSession().getServletContext().getAttribute(CONSTANT_USER_MAP);
	  userMap.remove(arg0.getSession().getAttribute(CONSTANT_LOGGEDIN_USER));
	
  }
 
  
}