package  com.logica.ngph.web.interceptor;
	 
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.struts2.StrutsStatics;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
 
public class LoginInterceptor extends AbstractInterceptor implements StrutsStatics {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6896713502192685232L;
	private static final String USER_HANDLE = "loggedInUser";
	private static final String LOGIN_ATTEMPT = "loginAttempt";
	public void init() {}
	public void destroy() {}
	public String intercept(ActionInvocation invocation) throws Exception {
		final ActionContext context = invocation.getInvocationContext();
		HttpServletRequest request = (HttpServletRequest) context.get(HTTP_REQUEST);
		HttpSession session = request.getSession(true);
		// Is there a "user" object stored in the user's HttpSession?
		Object user = session.getAttribute(USER_HANDLE);
		//String idleSessionTimeout = callIdleSessionTimeOut(session);
		//System.out.println("maxInactiveTime "+idleSessionTimeout);
		if (user == null) {			
			// The user has not logged in yet.
			// Is the user attempting to log in right now?
			String loginAttempt = request.getParameter(LOGIN_ATTEMPT);
			/* The user is attempting to log in. */
			if (loginAttempt != null && !loginAttempt.equals("")) {
				return invocation.invoke();
			}
			return "login";
		} else {
			return invocation.invoke();
		}
	}
	
	public String callIdleSessionTimeOut(HttpSession session){
		String idleSessionTimeout =null;
		
		
		
		idleSessionTimeout = callTimer(session);
	
		
		
		return idleSessionTimeout;
	}
	
		public String  callTimer(HttpSession session)
		{
			String idleSessionTimeout =null;
			int maxInactiveTime =session.getMaxInactiveInterval();
			int minits = maxInactiveTime/60;
			int seconds = 60;
			Date currentDate = new Date();
			if(seconds==0)
			{
				seconds = seconds-1;
				idleSessionTimeout = minits+" Minutes ," + seconds+" Seconds";
				//String  tim = setTimeout("f2()", 1000);
			}
			else
			{
				if(seconds==0)
				{
				
				}
			}
		return 	idleSessionTimeout;
		}

}