package com.logica.ngph.web.action;

import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.springframework.context.ApplicationContextException;

import com.logica.ngph.common.NGPHException;
import com.logica.ngph.dtos.PasswordSecurityPolicyDto;
import com.logica.ngph.dtos.UserPasswordsDTO;
import com.logica.ngph.service.ChangePwdService;
import com.logica.ngph.service.PasswordSecurityPolicyService;
import com.logica.ngph.utils.StringEncrypter;
import com.logica.ngph.web.utils.ApplicationContextProvider;
import com.logica.ngph.web.utils.WebConstants;
import com.opensymphony.xwork2.ActionSupport;

public class ChangePasswordAction extends ActionSupport implements SessionAware{
	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(ChangePasswordAction.class);
	private Map<String, Object> session;
	private String oldPwd;
	private String newPwd;
	private String confPwd;
	
	public String getOldPwd() {
		return oldPwd;
	}

	public void setOldPwd(String oldPwd) {
		this.oldPwd = oldPwd;
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

	public Map<String, Object> getSession() {
		return session;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
	@SkipValidation
	public String getChangePwd()
	{
		try{
			PasswordSecurityPolicyService passwordSecurityPolicyService = (PasswordSecurityPolicyService)ApplicationContextProvider.getBean("passwordSecurityPolicyService");
			ChangePwdService changePwdService  = (ChangePwdService) ApplicationContextProvider.getBean("changePwdService");
			PasswordSecurityPolicyDto passwordSecurityPolicyDto = passwordSecurityPolicyService.getSecurityPolicy();
			
			int minChars;
			int maxChars;
			int noofDigits;
			int noofSpecialChars;
			int noofUpperChars;
			int noofLowerChars;
			
			minChars = passwordSecurityPolicyDto.getMinChars();
			maxChars = passwordSecurityPolicyDto.getMaxChars();
			noofDigits = passwordSecurityPolicyDto.getNoofDigits();
			noofSpecialChars = passwordSecurityPolicyDto.getNoofSpecialChars();
			noofUpperChars = passwordSecurityPolicyDto.getNoofUpperChars();
			noofLowerChars = passwordSecurityPolicyDto.getNoofLowerChars();
			
			setOldPwd("");
			setNewPwd("");
			setConfPwd("");
			String isPassPolicyEnable =changePwdService.getPassPolicyIsReq("ISPASSPOLICYENABLE");
			if(isPassPolicyEnable.equalsIgnoreCase("Y"))
			{
				addActionMessage("Password Security Password was changed. Please Change New Password according to new Policy!");
				addActionMessage("According to the new policy, the maximum length is "+maxChars +" characters, minimum length is "+minChars+"  characters, no of uppercase characters is "+noofUpperChars+
						 ", no of lowercase characters is "+noofLowerChars + ", no of special characters is "+noofSpecialChars + " and no of digits is "+noofDigits);
			}
			return "success";
			
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
	public String changePwd()
	{
		try{
			String userId = (String)session.get(WebConstants.CONSTANT_USER_NAME);
			ChangePwdService changePwdService  = (ChangePwdService) ApplicationContextProvider.getBean("changePwdService");
			
			//get all old password from TA_USER_PASSWORDS table
			UserPasswordsDTO userPasswordsDTO = new UserPasswordsDTO();
			userPasswordsDTO = changePwdService.getOldPwds(userId);
			String isPassPolicyEnable =changePwdService.getPassPolicyIsReq("ISPASSPOLICYENABLE");
						
			//Check if old password is valid or not by user 
			Boolean check= changePwdService.isOldPwdMatches(userId,getOldPwd());
			boolean isValidPassword = false;
				
			if(check)
			{
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
			}
			else
			{
				addFieldError("oldPwd", "Old Password Is Wrong");
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

}
