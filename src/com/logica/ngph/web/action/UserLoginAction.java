package com.logica.ngph.web.action;

import java.sql.Timestamp;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.interceptor.validation.SkipValidation;

import com.logica.ngph.Entity.Functions;
import com.logica.ngph.common.NGPHException;
import com.logica.ngph.common.dtos.UserMaintenanceDTO;
import com.logica.ngph.common.utils.NGPHUtil;
import com.logica.ngph.dtos.PasswordSecurityPolicyDto;
import com.logica.ngph.service.ChangePwdService;
import com.logica.ngph.service.PasswordSecurityPolicyService;
import com.logica.ngph.service.RoleMaintenanceService;
import com.logica.ngph.service.UserLoginService;
import com.logica.ngph.service.UserMaintenanceService;
import com.logica.ngph.web.utils.ApplicationContextProvider;
import com.logica.ngph.web.utils.WebConstants;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
/**
 * 
 * @author guptast
 *
 */
public class UserLoginAction extends ActionSupport implements SessionAware{
	
	private static final long serialVersionUID = 2018543487869631025L;
	private Map<String, Object> session = ActionContext.getContext().getSession();
	private static ServletContext context = ServletActionContext.getServletContext();
	private String user;
	private String userPassword;
	private String remembermeChkBox;
	private String signOnFlag;
	/*private String tempPWD;
	public String getTempPWD() {
		return tempPWD;
	}
	public void setTempPWD(String tempPWD) {
		this.tempPWD = tempPWD;
	}*/
	public String getSignOnFlag() {
		return signOnFlag;
	}
	public void setSignOnFlag(String signOnFlag) {
		this.signOnFlag = signOnFlag;
	}
	private static final String CONSTANT_USER_MAP = "UserMap"; 
	private static final String CONSTANT_USER_NAME = "UserName"; 
	private static final String CONSTANT_LOGGEDIN_USER = "loggedInUser"; 
	
	// This counter value will help in loading Application context only once.
	private static int counter=0;
	
/**
 * This is a static block to load the initialize the application context
 */
	static
	{
		try 
		{
			System.out.println("Application loaded value : " + counter);
			if(counter==0)
			{
				ApplicationContextProvider.initializeContextProvider();
				if(context.getAttribute(CONSTANT_USER_MAP)== null){
					Map<String, Object> userMap = new HashMap<String, Object>();
					context.setAttribute(CONSTANT_USER_MAP, userMap);
				}
			}
			else
			{
				System.out.println("Application Context has already been initailized, no need to initialize it again");
			}
			
		}
		catch (Exception e) 
		{
			System.out.println("Error in Intializing Conetxt Object");
			e.printStackTrace();
		}
		// Incrementing the counter
		counter++;
	
	}
	/**
	 * @return the remembermeChkBox
	 */
	public String getRemembermeChkBox() {
		return remembermeChkBox;
	}
	/**
	 * @param remembermeChkBox the remembermeChkBox to set
	 */
	public void setRemembermeChkBox(String remembermeChkBox) {
		this.remembermeChkBox = remembermeChkBox;
	}
	
	public String validateUser() {
		String loginForward = "input";
		Timestamp policyLastModDateTime = null;
		Timestamp lastPassModDateTime = null;
		PasswordSecurityPolicyDto passwordSecurityPolicyDto = new PasswordSecurityPolicyDto();
		boolean isvalid = false;
		try 
		{
			PasswordSecurityPolicyService passwordSecurityPolicyService = (PasswordSecurityPolicyService)ApplicationContextProvider.getBean("passwordSecurityPolicyService");
			UserMaintenanceService userMaintenanceService = (UserMaintenanceService)ApplicationContextProvider.getBean("userMaintenanceService");
			UserLoginService userLoginService = (UserLoginService)ApplicationContextProvider.getBean("userLoginService");
			ChangePwdService changePwdService  = (ChangePwdService) ApplicationContextProvider.getBean("changePwdService");
			
			passwordSecurityPolicyDto = passwordSecurityPolicyService.getSecurityPolicy();
			policyLastModDateTime = passwordSecurityPolicyDto.getLastPassPolicyChanges(); 
			if(StringUtils.isNotEmpty(getUser()) && StringUtils.isNotBlank(getUser()) && !getUser().equalsIgnoreCase("SA1") && !getUser().equalsIgnoreCase("SA2"))
			{
				lastPassModDateTime = userMaintenanceService.getLastModPassByUser(getUser());
			}
			String isPassPolicyEnable =changePwdService.getPassPolicyIsReq("ISPASSPOLICYENABLE");
			
			if(isPassPolicyEnable.equalsIgnoreCase("Y"))
			{
				isvalid = validatePassword();
			}
			if (isvalid && !getUser().equalsIgnoreCase("SA1") && !getUser().equalsIgnoreCase("SA2"))
			{
				if(policyLastModDateTime.before(lastPassModDateTime))
				{
					loginForward = isValidateUser();
				}	
				else
				{
					if(StringUtils.isNotEmpty(getUser()) && StringUtils.isNotBlank(getUser()) && (!getUser().equalsIgnoreCase("SA1") || !getUser().equalsIgnoreCase("SA2")))
					{
						UserMaintenanceDTO userDto = userLoginService.getLogInTimeDetails(getUser());
						setUserDtoToSession(userDto);
						session.put(CONSTANT_USER_NAME, getUser());
						ServletActionContext.getRequest().getSession().setAttribute(CONSTANT_LOGGEDIN_USER, getUser().toString());
						loginForward = "getChangePwd";
					}
				}
			}
			else if(isPassPolicyEnable.equalsIgnoreCase("N") && !(getUser().equalsIgnoreCase("SA1") && !(getUser().equalsIgnoreCase("SA2"))))
			{
				loginForward = isValidateUser();
			}
			else if(StringUtils.isNotEmpty(getUser()) && StringUtils.isNotBlank(getUser()) && (getUser().equalsIgnoreCase("SA1") || getUser().equalsIgnoreCase("SA2")))
			{
				loginForward = isValidateUser();
			}
			else if(policyLastModDateTime.after(lastPassModDateTime))
			{
				if(StringUtils.isNotEmpty(getUser()) && StringUtils.isNotBlank(getUser()) && (!getUser().equalsIgnoreCase("SA1") || !getUser().equalsIgnoreCase("SA2")))
				{
					UserMaintenanceDTO userDto = userLoginService.getLogInTimeDetails(getUser());
					setUserDtoToSession(userDto);
					session.put(CONSTANT_USER_NAME, getUser().toUpperCase());
					ServletActionContext.getRequest().getSession().setAttribute(CONSTANT_LOGGEDIN_USER, getUser().toString());
					loginForward = "getChangePwd";
				}
			}
		}	
		catch (NGPHException e) {
				e.printStackTrace();
				loginForward = "input";
				addActionError("Invalid Username or PassWord");
		} catch (Exception e) {
			e.printStackTrace();
			loginForward = "input";
			addActionError("Invalid Username or PassWord");
		}
		return loginForward;
	}
	
	public String validateUserForSSO(){
		String loginForward = "input";
		String userId = ServletActionContext.getRequest().getHeader("uid");
		System.out.println("UID-"+ServletActionContext.getRequest().getHeader("uid"));
		session.put(CONSTANT_USER_NAME,userId);
		UserLoginService userLoginService = (UserLoginService)ApplicationContextProvider.getBean("userLoginService");
		boolean isvalidUser =	userLoginService.validateUserId(userId);
		if(isvalidUser){
			UserMaintenanceDTO userDto = userLoginService.getLogInTimeDetails(userId);
			setUserDtoToSession(userDto);
			Map<String, Object> userMap = (Map<String, Object>)context.getAttribute(CONSTANT_USER_MAP);
			for(String userName : userMap.keySet()){
				if(userId.equals(userName)){
					addActionError("Your last session was terminated incorrectly or is currently active . Please try logging in again after some time.");
					return loginForward;
				}
			}				
			ServletActionContext.getRequest().getSession().setAttribute(CONSTANT_LOGGEDIN_USER, userId);
			userMap.put(userId, "");
			getUserRestrictedFunctions(userId);
			loginForward = "userSuccess";	
		}else{
			addActionError("Invalid Username or PassWord");
		}
		return loginForward;
	}
	
	
	public String reLogin()
	{
		//logoutAction();
		//session = ActionContext.getContext().getSession();
		try{
		Map<String, Object> userMap = (Map<String, Object>)context.getAttribute(CONSTANT_USER_MAP);
		if(userMap!=null)
		{
			Logout((HttpSession)userMap.get(getUser()));
		}
		
		
		setSignOnFlag("0");
		addActionError("Old Session Get Deleted. Please Login Again");
		((Map<String, Object>)context.getAttribute(CONSTANT_USER_MAP)).remove(getUser());
		}
		catch (Exception e) {
			addActionError("Unable To Delete Old Session.Please Try Again Later");
			setSignOnFlag("0");
		}
		return "success";
		
	}
	
	public void Logout(HttpSession  httpSession)
	{
		Map<String, Object> userMap = (Map<String, Object>)context.getAttribute(CONSTANT_USER_MAP);
		
		userMap.remove(getUser());
		httpSession.invalidate();
		/*try{
			UserLoginService userLoginService = (UserLoginService)ApplicationContextProvider.getBean("userLoginService");
			userLoginService.changeLogginStatus(getUser(),"0");
			
		}catch (Exception e) {
			e.printStackTrace();		}*/
		
	}
	@SkipValidation
	public String welComeForward(){
		return "welcomeForward";
	}
	
	public String forgotPassWord(){
		return "forgotPassword";
	}
	
	@SuppressWarnings({ "unchecked", "unchecked" })
	@SkipValidation
	public String logoutAction(){
		Map<String, Object> userMap = (Map<String, Object>)context.getAttribute(CONSTANT_USER_MAP);
		userMap.remove(ServletActionContext.getRequest().getSession().getAttribute(CONSTANT_LOGGEDIN_USER));
		ServletActionContext.getRequest().getSession().invalidate();
		/*try{
			UserLoginService userLoginService = (UserLoginService)ApplicationContextProvider.getBean("userLoginService");
			userLoginService.changeLogginStatus(getUser(),"0");
			
		}catch (Exception e) {
			e.printStackTrace();		}*/
		return "userSuccess";
	}
	
	public void validate(){
		
	}
	
	private void getUserRestrictedFunctions(String userId){
		System.out.println("Inside Login Action <getUserRestrictedFunctions>");
		RoleMaintenanceService roleMaintenanceService = (RoleMaintenanceService)ApplicationContextProvider.getBean("roleMaintenanceService");
		List<Functions> allFunctionsList= roleMaintenanceService.getFunctions();
		List<String> accessibleFunctionsList = roleMaintenanceService.getAccessibleFunctions(userId);
		List<Functions> restrictedFunctionsList = new ArrayList<Functions>();
		for(Functions function : allFunctionsList ){
			if(function != null && !NGPHUtil.isElementInList(accessibleFunctionsList, function.getFunctionId())) {
				restrictedFunctionsList.add(function);
			}
		}
		System.out.println("Inside Login Action <getUserRestrictedFunctions>: The  restricted funcitns list size:"+restrictedFunctionsList.size());
		session.put("restrictedFunctionsList", restrictedFunctionsList);
	}
	
	public void setUserDtoToSession(UserMaintenanceDTO userDto){
		String currentLoginTime  =  convertTimestampToString(userDto.getCurrentLogin());
		String lastLoginTime = convertTimestampToString(userDto.getLastLogin());
		//String businessDay = convertTimestamp(userDto.getBusinessDay());
		Date date = new Date();
		String businessDay = convertTimestamp(new java.sql.Timestamp(date.getTime()));
		this.session.put("currentLoginTime", currentLoginTime);
		this.session.put("lastLoginTime", lastLoginTime);
		this.session.put("businessDay", businessDay);
	}
	private String getSessionID()
	{
		
		Cookie cookies[] = ServletActionContext.getRequest().getCookies();
		if (cookies != null) {
			for (int a=0; a< cookies.length; a++) {										
				if(cookies[a].getName().equals("JSESSIONID")){
					String sessionid = cookies[a].getValue();
					System.out.println(sessionid);
					System.out.println(ServletActionContext.getRequest().isSecure());
				}
			}
		}
		System.out.println();
		return null;
	}
	private String convertTimestampToString(Timestamp date) {
		if (date != null) {
			try {
				Format formatter = new SimpleDateFormat("dd-MMM-yy HH.mm.ss a");
				String dateTime = formatter.format(date);
				return dateTime;

			} catch (Exception ex) {
				ex.printStackTrace();
				return "";
			}
		} else {
			return "";
		}
	}
	
	private String convertTimestamp(Timestamp date) {
		if (date != null) {
			try {
				Format formatter = new SimpleDateFormat("dd-MMM-yy");
				String dateTime = formatter.format(date);
				return dateTime;

			} catch (Exception ex) {
				ex.printStackTrace();
				return "";
			}
		} else {
			return "";
		}
	}
	
	
	/**
	 * @return the user
	 */
	public String getUser() {
		return user;
	}
	/**
	 * @param user the user to set
	 */
	public void setUser(String user) {
		this.user = user;
	}
	/**
	 * @return the userPassword
	 */
	public String getUserPassword() {
		return userPassword;
	}
	/**
	 * @param userPassword the userPassword to set
	 */
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	/**
	 * @return the session
	 */
	public Map<String, Object> getSession() {
		return session;
	}
	public void setSession(Map<String, Object> session) {
		this.session = 	session;
	}

	//Mizuho :: start :: Added 
	public boolean validatePassword() throws NGPHException
	{
		try
		{
			PasswordSecurityPolicyDto passwordSecurityPolicyDto = new PasswordSecurityPolicyDto();
			boolean isValid = true;
			int minChars;
			int maxChars;
			int noofDigits;
			int noofSpecialChars;
			int noofUpperChars;
			int noofLowerChars;
			
			int isNoofDigits=0;
			int isNoofSpecialChars=0;
			int isNoofUpperChars=0;
			int isNoofLowerChars=0;
			
			PasswordSecurityPolicyService passwordSecurityPolicyService = (PasswordSecurityPolicyService)ApplicationContextProvider.getBean("passwordSecurityPolicyService");
			passwordSecurityPolicyDto = passwordSecurityPolicyService.getSecurityPolicy();
			
			minChars = passwordSecurityPolicyDto.getMinChars();
			maxChars = passwordSecurityPolicyDto.getMaxChars();
			noofDigits = passwordSecurityPolicyDto.getNoofDigits();
			noofSpecialChars = passwordSecurityPolicyDto.getNoofSpecialChars();
			noofUpperChars = passwordSecurityPolicyDto.getNoofUpperChars();
			noofLowerChars = passwordSecurityPolicyDto.getNoofLowerChars();
			
			if(StringUtils.isNotEmpty(getUserPassword()) && !StringUtils.isBlank(getUserPassword()) 
					&& StringUtils.isNotEmpty(getUser().toUpperCase()) && StringUtils.isNotBlank(getUser()) && (!getUser().equalsIgnoreCase("SA1") && !getUser().equalsIgnoreCase("SA2")))
			{	
				//check min and max characters in password 
				if(getUserPassword().length() > maxChars ||  getUserPassword().length() < minChars)
				{
					isValid = false;
				}
				
				for(int i=0; i<getUserPassword().length(); i++)
				{
					//check count digits 
					if(Character.isDigit(getUserPassword().charAt(i)))
					{
						isNoofDigits++;
					}
					
					//Check count of noof Special characters
					if( getUserPassword().charAt(i) == '$' ) 
					{
						 isNoofSpecialChars++;
					} 
					if( getUserPassword().charAt(i) == '@' ) 
					{
						 isNoofSpecialChars++;
					} 
					if( getUserPassword().charAt(i) == '#' ) 
					{
						 isNoofSpecialChars++;
					}
					if( getUserPassword().charAt(i) == '%' ) 
					{
						 isNoofSpecialChars++;
					}
					
					//check count upper case characters
					if(Character.isUpperCase(getUserPassword().charAt(i)))
					{
						isNoofUpperChars++;
					}
	
					//check count lower case characters
					if(Character.isLowerCase(getUserPassword().charAt(i)))
					{
						isNoofLowerChars++;
					}
					
				}
				
				if(noofDigits > isNoofDigits)
				{
					isValid = false;
				}
				if(noofSpecialChars > isNoofSpecialChars)
				{
					isValid = false;
				}
				if(noofUpperChars > isNoofUpperChars)
				{
					isValid = false;
				}
				if(noofLowerChars > isNoofLowerChars)
				{
					isValid = false;
				}
				
				 
				 if(!isValid)
				 {
					 addActionError("According to the policy, the maximum length is "+maxChars +" characters minimum length is "+minChars+" characters the no of uppercase characters is "+noofUpperChars+
							 " no of lowercase characters is "+ noofLowerChars+ " no of special characters is "+noofSpecialChars + " and no of digits is "+noofDigits);
				 }
			}
		
			return isValid;
		}
		catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	public String isValidateUser()
	{
		String loginForward = "input";
		
		try{
			session.put(CONSTANT_USER_NAME, getUser().toUpperCase());
		UserLoginService userLoginService = (UserLoginService)ApplicationContextProvider.getBean("userLoginService");
		boolean isvalidUser =	userLoginService.getValidateUser(getUser().toUpperCase(), getUserPassword());
		if(isvalidUser){
			UserMaintenanceDTO userDto = userLoginService.getLogInTimeDetails(getUser());
			setUserDtoToSession(userDto);
			/*Map<String, Object> userMap = (Map<String, Object>)context.getAttribute(CONSTANT_USER_MAP);
			for(String userName : userMap.keySet()){
				if(getUser().equals(userName)){
					addActionError("Your last session was terminated incorrectly or is currently active . Please try logging in again after some time.");
					setSignOnFlag("1");
					
					return loginForward;
				}
			}*/
			ServletActionContext.getRequest().getSession().setAttribute(CONSTANT_LOGGEDIN_USER, getUser());
			//userMap.put(getUser(), ServletActionContext.getRequest().getSession());
			loginForward = "userSuccess";
			//userLoginService.changeLogginStatus(getUser(),"1");
			getUserRestrictedFunctions(getUser());
		}else{
			addActionError("Invalid Username or PassWord");
		}
		}catch (Exception e) {
			e.printStackTrace();
			loginForward = "input";
			addActionError("Unable To Process");
		}
	
		return loginForward;
	}
	//Mizuho :: End :: Added
	
	public String callIdleSessionTimeout()
	{
		System.out.println("calling callIdleSessionTimeout()");
		return "success";
	}
	
}
