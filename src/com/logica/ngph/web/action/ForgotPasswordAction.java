/**
 * 
 */
package com.logica.ngph.web.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.context.ApplicationContextException;

import com.logica.ngph.common.NGPHException;
import com.logica.ngph.dtos.PasswordSecurityPolicyDto;
import com.logica.ngph.dtos.SecurityQuestionsDTO;
import com.logica.ngph.dtos.UserPasswordsDTO;
import com.logica.ngph.service.ChangePwdService;
import com.logica.ngph.service.PasswordSecurityPolicyService;
import com.logica.ngph.service.UserLoginService;
import com.logica.ngph.service.UserMaintenanceService;
import com.logica.ngph.utils.StringEncrypter;
import com.logica.ngph.web.utils.ApplicationContextProvider;
import com.logica.ngph.web.utils.WebConstants;
import com.opensymphony.xwork2.ActionSupport;

/**
 * @author chakkar
 *
 */
public class ForgotPasswordAction extends ActionSupport implements SessionAware {

	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(FreeFormatPaymentAction.class);
	private Map<String, Object> session;
	private String user;
	
	private String securityQuestion;
	private String securityAnswer;
	
	private String newPwd;
	private String confPwd;
	
	
	
	private List<String> securityQuestionDropDown = new ArrayList<String>();
	
	public List<String> getSecurityQuestionDropDown() {
		return securityQuestionDropDown;
	}
	public void setSecurityQuestionDropDown(
			List<String> securityQuestionDropDown) {
		this.securityQuestionDropDown = securityQuestionDropDown;
		this.session.put("sequrityQuestionDropDownList", securityQuestionDropDown);
	}
	

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	public Map<String, Object> getSession() {
		return session;
	}
	
		
	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}
	
	public String getSecurityQuestion() {
		return securityQuestion;
	}

	public void setSecurityQuestion(String securityQuestion) {
		this.securityQuestion = securityQuestion;
	}

	public String getSecurityAnswer() {
		return securityAnswer;
	}

	public void setSecurityAnswer(String securityAnswer) {
		this.securityAnswer = securityAnswer;
	}

	
	
	public String getNewPwd() {
		return newPwd;
	}
	public void setNewPwd(String newPwd) {
		this.newPwd = newPwd;
	}
	public String getConfPwd() {
		return confPwd;
	}
	public void setConfPwd(String confPwd) {
		this.confPwd = confPwd;
	}



	static
	{
		try 
		{
			ApplicationContextProvider.initializeContextProvider();
			
		}
		catch (Exception e) 
		{
			System.out.println("Error in Intializing Conetxt Object");
			e.printStackTrace();
		}
	
	}
	
	public String forgotPassword() throws NGPHException
	{
		try{
			System.out.println("Inside forgotPassword "+getUser());
		
			return "success";
		}
		
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return "input";
	}
	
	public String validateSecAnswer()throws NGPHException
	{
		try {
			PasswordSecurityPolicyService passwordSecurityPolicyService = (PasswordSecurityPolicyService)ApplicationContextProvider.
			getBean("passwordSecurityPolicyService");
			SecurityQuestionsDTO securityQuestionsDTO = passwordSecurityPolicyService.getSecurityQuestionDetails(getUser());
		
			if (getSecurityAnswer().equals(securityQuestionsDTO.getSecurityAnswer())) {
				this.session.put(WebConstants.CONSTANT_USER_NAME, getUser());
				return "success";
			}
			else
			{
				addActionError("Security Answer is Incorrect");
				return "input";
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "input";
	}
	
	public String fetchSecurityQuestion()throws NGPHException
	{
		try 
		{
			if(StringUtils.isNotEmpty(getUser()) && StringUtils.isNotBlank(getUser()) && !getUser().equalsIgnoreCase("SA1") && !getUser().equalsIgnoreCase("SA2"))
			{
				
				UserLoginService userLoginService = (UserLoginService)ApplicationContextProvider.getBean("userLoginService");
				boolean isvalidUser =	userLoginService.isValidUser(getUser().toUpperCase());
				if(isvalidUser)
				{
					PasswordSecurityPolicyService passwordSecurityPolicyService = (PasswordSecurityPolicyService) ApplicationContextProvider.getBean("passwordSecurityPolicyService");
					SecurityQuestionsDTO securityQuestionsDTO = passwordSecurityPolicyService.getSecurityQuestionDetails(getUser());
					if(StringUtils.isNotEmpty(securityQuestionsDTO.getSecurityQuesion()) && StringUtils.isNotBlank(securityQuestionsDTO.getSecurityQuesion())){
					setSecurityQuestion(securityQuestionsDTO.getSecurityQuesion());
					return "success";
					}
					else
					{
						addActionError( " Unable to fetch Security Question for "+getUser()+" User");
						return "input";
					}
					
				}
				else 
				{
					addActionError( "Invalid User name! Please Enter Valid Username.");
				}
			}
			
			else
			{
				addActionError("Please Enter Valid Username!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		return "input";
	}
	
	public UserMaintenanceService getApplicationContext()
	{
		UserMaintenanceService userMaintenanceService = (UserMaintenanceService)ApplicationContextProvider.getBean("userMaintenanceService");
		return userMaintenanceService;
	}
	
	
	public String changePwd()throws NGPHException
	{
		try{
			String userId = (String)session.get(WebConstants.CONSTANT_USER_NAME);
			ChangePwdService changePwdService  = (ChangePwdService) ApplicationContextProvider.getBean("changePwdService");
			
			//get all old password from TA_USER_PASSWORDS table
			UserPasswordsDTO userPasswordsDTO = new UserPasswordsDTO();
			userPasswordsDTO = changePwdService.getOldPwds(userId);
			String isPassPolicyEnable =changePwdService.getPassPolicyIsReq("ISPASSPOLICYENABLE");
						
			
			boolean isValidPassword = false;
				
			
				if(getNewPwd().equals(getConfPwd()))
				{
					
					if(isPassPolicyEnable.equalsIgnoreCase("Y"))
					{
					//Check If new password is matches with new password Policy
						isValidPassword = validatePassword(getConfPwd(), userId);
					}
					
					if(isValidPassword || isPassPolicyEnable.equalsIgnoreCase("N"))
					{
						//Check 
						String isValid = isOldPwdsMatches(userId, getNewPwd(), userPasswordsDTO);
						if(isValid.equalsIgnoreCase("valid"))
						{
							changePwdService.changePwd(userId,getNewPwd(),userPasswordsDTO);
							addActionError("The password has been changed successfully.");
							return "success";
						}
						else
						{
							addFieldError("confPwd", "New Password should not match with last 5 passwords");
							return "input";
						}
					}
					else
					{
						addFieldError("confPwd", "New Password should not match with Password Security Policy.");
					}
				}
				else
				{
					addFieldError("oldPwd", "New Password And Confirm Password Doesn't Match");
					return "input";
				}
			
		
			
		}catch (NullPointerException  nullPointerException) {
			AuditServiceUtil.logNullPointerException(nullPointerException, logger);
		}
		catch (ApplicationContextException applicationContextException) {
			AuditServiceUtil.logApplicationException(applicationContextException, logger);
		}
		catch (ClassCastException classCastException) {
			AuditServiceUtil.logClassCastException(classCastException, logger)	;
		}
		catch (Exception exception) {
			AuditServiceUtil.logException(exception,logger);
		}
		addActionError("Unable To Perfrom Action");
		return "input";
	}
	
	
	//Mizuho :: start :: Added 
	public boolean validatePassword(String newPassword, String userId) throws NGPHException
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
			
			if(StringUtils.isNotEmpty(newPassword) && !StringUtils.isBlank(newPassword) 
					&& StringUtils.isNotEmpty(userId) && StringUtils.isNotBlank(userId) && (!userId.equalsIgnoreCase("SA1") || !userId.equalsIgnoreCase("SA2")))
			{
				//check min and max characters in password 
				if(newPassword.length() > maxChars ||  newPassword.length() < minChars)
				{
					isValid = false;
				}
				
				for(int i=0; i<newPassword.length(); i++)
				{
					//check count digits 
					if(Character.isDigit(newPassword.charAt(i)))
					{
						isNoofDigits++;
					}
					
					//Check count of noof Special characters
					if( newPassword.charAt(i) == '$' ) 
					{
						 isNoofSpecialChars++;
					} 
					if( newPassword.charAt(i) == '@' ) 
					{
						 isNoofSpecialChars++;
					} 
					if( newPassword.charAt(i) == '#' ) 
					{
						 isNoofSpecialChars++;
					}
					if( newPassword.charAt(i) == '%' ) 
					{
						 isNoofSpecialChars++;
					}
					
					//check count upper case characters
					if(Character.isUpperCase(newPassword.charAt(i)))
					{
						isNoofUpperChars++;
					}
	
					//check count lower case characters
					if(Character.isLowerCase(newPassword.charAt(i)))
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
					 addActionError("According to the new policy, the maximum length is "+maxChars +" characters minimum length is "+minChars+" characters no of uppercase characters is "+noofUpperChars+
							 " no of lowercase characters is "+noofLowerChars + " no of special characters is "+noofSpecialChars + " and no of digits is "+noofDigits);
				 }
			}
		
			return isValid;
		}
		catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

	
	public String isOldPwdsMatches(String userId, String newPwd, UserPasswordsDTO userPasswordsDTO)
	{
		
		String oldPwdEncrypted = null;
		String oldPwd1Encrypted = null;
		String oldPwd2Encrypted = null;
		String oldPwd3Encrypted = null;
		String oldPwd4Encrypted = null;
		
		
		try
		{
			//Encrypting new password 
			String newPwdEncrypt=StringEncrypter.encryptURL(userId, newPwd);
			newPwdEncrypt = new String(new sun.misc.BASE64Encoder().encode(newPwdEncrypt.getBytes()));
			
					
			//Check if new Encrypted password is matches with old 5 old Encrypted passwords
			oldPwdEncrypted = userPasswordsDTO.getUserPassword();
			oldPwd1Encrypted = userPasswordsDTO.getUserPassword1();
			oldPwd2Encrypted = userPasswordsDTO.getUserPassword2();
			oldPwd3Encrypted = userPasswordsDTO.getUserPassword3();
			oldPwd4Encrypted = userPasswordsDTO.getUserPassword4();
			
			if(StringUtils.isNotBlank(oldPwdEncrypted) && StringUtils.isNotEmpty(oldPwdEncrypted) )
			{
				if(!newPwdEncrypt.equalsIgnoreCase(oldPwdEncrypted) )
				{
					if(StringUtils.isNotBlank(oldPwd1Encrypted) && StringUtils.isNotEmpty(oldPwd1Encrypted))
					{
						if(!newPwdEncrypt.equalsIgnoreCase(oldPwd1Encrypted))
						{
							if(StringUtils.isNotBlank(oldPwd2Encrypted) && StringUtils.isNotEmpty(oldPwd2Encrypted))
							{
								if(!newPwdEncrypt.equalsIgnoreCase(oldPwd2Encrypted))
								{
									if(StringUtils.isNotBlank(oldPwd3Encrypted) && StringUtils.isNotEmpty(oldPwd3Encrypted))
									{
										if(!newPwdEncrypt.equalsIgnoreCase(oldPwd3Encrypted))
										{
											if(StringUtils.isNotBlank(oldPwd4Encrypted) && StringUtils.isNotEmpty(oldPwd4Encrypted))
											{
												if(!newPwdEncrypt.equalsIgnoreCase(oldPwd4Encrypted))
												{
													return "valid";
												}
												else
												{
													return "invalid";
												}
											}
											else
											{
												return "valid";
											}
										}
										else
										{
											return "invalid";
										}
									}
									else
									{
										return "valid";
									}
								}
								else
								{
									return "invalid";
								}
							}
							else
							{
								return "valid";
							}					
						}
						else
						{
							return "invalid";
						}
					}
					else
					{
						return "valid";
					}
				}
			}
		
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return null;
	}
	
}
