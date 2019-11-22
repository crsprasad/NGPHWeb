/**
 * 
 */
package com.logica.ngph.web.action;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.jboss.util.Base64;
import org.springframework.context.ApplicationContextException;

import com.logica.ngph.common.ConstantUtil;
import com.logica.ngph.dtos.CreateBankGuaranteeDto;
import com.logica.ngph.dtos.PasswordSecurityPolicyDto;
import com.logica.ngph.service.CreateBankGuaranteeService;
import com.logica.ngph.service.PasswordSecurityPolicyService;
import com.logica.ngph.service.PaymentMessageService;
import com.logica.ngph.service.PendingAuthorizationService;
import com.logica.ngph.web.utils.ApplicationContextProvider;
import com.logica.ngph.web.utils.EventLogging;
import com.logica.ngph.web.utils.WebConstants;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * @author chakkar
 *
 */
public class PasswordPolicyAction extends ActionSupport implements ModelDriven<PasswordSecurityPolicyDto>,SessionAware {

	private static Logger logger = Logger.getLogger(UserMaintenanceAction.class);
	private PasswordSecurityPolicyDto passwordSecurityPolicyDto = new PasswordSecurityPolicyDto();
	private Map<String, Object> session;
	private String UserId;
	private String saveAction;
	private String hiddenTxnRef;
	private String txnRef;
	private String checkForSubmit;
	private boolean validUserToApprove;
	
	@Override
	public PasswordSecurityPolicyDto getModel() {
		return passwordSecurityPolicyDto;
	}

	public Map<String, Object> getSession() {
		return session;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	@SkipValidation
	public String getSecurityPolicy() {
		try 
		{
			PasswordSecurityPolicyService passwordSecurityPolicyService = (PasswordSecurityPolicyService) ApplicationContextProvider.getBean("passwordSecurityPolicyService");
			PasswordSecurityPolicyDto passwordSecurityPolicyDto = passwordSecurityPolicyService.getSecurityPolicy();
			setALLValueTODTO(passwordSecurityPolicyDto);
			return "success";
			
		} catch (Exception exception) {
			exception.printStackTrace();
			addFieldError("paymentMessageType", ConstantUtil.ERRORMESSAGE);
			return "input";
		}
	}

	public PasswordSecurityPolicyDto getPasswordSecurityPolicyDto() {
		return passwordSecurityPolicyDto;
	}

	public void setPasswordSecurityPolicyDto(
			PasswordSecurityPolicyDto passwordSecurityPolicyDto) {
		this.passwordSecurityPolicyDto = passwordSecurityPolicyDto;
	}

	public String getUserId() {
		return UserId;
	}

	public void setUserId(String userId) {
		UserId = userId;
	}

	public String getSaveAction() {
		return saveAction;
	}

	public void setSaveAction(String saveAction) {
		this.saveAction = saveAction;
	}

	public String getHiddenTxnRef() {
		return hiddenTxnRef;
	}

	public void setHiddenTxnRef(String hiddenTxnRef) {
		this.hiddenTxnRef = hiddenTxnRef;
	}

	public String getTxnRef() {
		return txnRef;
	}

	public void setTxnRef(String txnRef) {
		this.txnRef = txnRef;
	}

	public String getCheckForSubmit() {
		return checkForSubmit;
	}

	public void setCheckForSubmit(String checkForSubmit) {
		this.checkForSubmit = checkForSubmit;
	}

	public boolean isValidUserToApprove() {
		return validUserToApprove;
	}

	public void setValidUserToApprove(boolean validUserToApprove) {
		this.validUserToApprove = validUserToApprove;
	}

	
	public String passwordPolicyApproval() {
		try {			
			String txnKey="";
			PendingAuthorizationService pendingAuthorizationService = (PendingAuthorizationService)ApplicationContextProvider.getBean("pendingAuthorizationService");
			EventLogging eventLogging = (EventLogging)ApplicationContextProvider.getBean("eventLogging");
			String userId = (String)session.get(WebConstants.CONSTANT_USER_NAME);
			if(!getHiddenTxnRef().isEmpty())
			{
				pendingAuthorizationService.updateRejectStatusToPending(getHiddenTxnRef());
			}else
			{
				 txnKey = pendingAuthorizationService.getTranRef(serializeObject(),"Password Security Policy",userId);
			}
		    session.put("screenRef", txnKey);
			return "success";
		

		} catch (NullPointerException nullPointerException) {
			AuditServiceUtil.logNullPointerException(nullPointerException,
					logger);
		} catch (ApplicationContextException applicationContextException) {
			AuditServiceUtil.logApplicationException(
					applicationContextException, logger);
		} catch (ClassCastException classCastException) {
			AuditServiceUtil.logClassCastException(classCastException, logger);
		} catch (Exception exception) {
			AuditServiceUtil.logException(exception, logger);
		}
		
		addActionError("Unable to perform the operation. Please try again");
		return "input";
	}
	
	
	private String serializeObject(){
		PasswordSecurityPolicyDto passwordSecurityPolicyDTO = new PasswordSecurityPolicyDto();
		try{
			String userId = (String)session.get(WebConstants.CONSTANT_USER_NAME);
			
			passwordSecurityPolicyDTO = passwordSecurityPolicyDto;
			System.out.print("MaxChars :"+passwordSecurityPolicyDTO.getMaxChars());
			String fileName ="serial_"+userId+".ser";
		FileOutputStream fos = new FileOutputStream(fileName);
        OutputStream buffer = new BufferedOutputStream( fos );
        ObjectOutputStream oos = new ObjectOutputStream(buffer);
        oos.writeObject(passwordSecurityPolicyDTO);
        oos.flush();
        oos.close();
        File file = new File(fileName);
        byte[] byteArray = new byte[(int) file.length()];
        FileInputStream fis = new FileInputStream(file); 
        fis.read(byteArray);
        String objectString = Base64.encodeBytes(byteArray);
        System.out.print("Object String :"+objectString);
        
		return objectString;
		
       
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	private PasswordSecurityPolicyDto getSerializedObject(String objectString) {

		try {

			byte[] decoded = Base64.decode(objectString);

			FileOutputStream foss = new FileOutputStream("targetUserObject.ser");
			foss.write(decoded);
			foss.close();
			PasswordSecurityPolicyDto testDTO = null;

			FileInputStream fiss = new FileInputStream("targetUserObject.ser");
			BufferedInputStream bufferee = new BufferedInputStream(fiss);
			ObjectInputStream oiss = new ObjectInputStream(bufferee);
			testDTO = (PasswordSecurityPolicyDto) oiss.readObject();
			oiss.close();
			System.out.println("object2: " + testDTO);
			return testDTO;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	public void validate(){
		
		
		

	}
	
		
	@SkipValidation
	public String getPassPolicyPolicysAuthorization()
	{
		String tempScreenString =null;
		PendingAuthorizationService pendingAuthorizationService = (PendingAuthorizationService)ApplicationContextProvider.getBean("pendingAuthorizationService");
	try{
		if(getTxnRef()!=null)
		{
		setCheckForSubmit("Display_Approve_Reject");
		String userId = pendingAuthorizationService.getCreatedUser(getTxnRef());
		String userRole = pendingAuthorizationService.getUserType((String)session.get(WebConstants.CONSTANT_USER_NAME));
		if((((String)session.get(WebConstants.CONSTANT_USER_NAME)).equals(userId) || userRole.equals("T"))){
			setValidUserToApprove(false);
		} else{
			setValidUserToApprove(true);
		}

		tempScreenString =pendingAuthorizationService.getScreenReturn(getTxnRef());
		}
		PasswordSecurityPolicyDto temp= getSerializedObject(tempScreenString);
		passwordSecurityPolicyDto.setMinChars(temp.getMinChars());
		passwordSecurityPolicyDto.setMaxChars(temp.getMaxChars());
		passwordSecurityPolicyDto.setNoofDigits(temp.getNoofDigits());
		passwordSecurityPolicyDto.setNoofSpecialChars(temp.getNoofSpecialChars());
		passwordSecurityPolicyDto.setNoofUpperChars(temp.getNoofUpperChars());
		passwordSecurityPolicyDto.setNoofLowerChars(temp.getNoofLowerChars());
		passwordSecurityPolicyDto.setPasswordExpDays(temp.getPasswordExpDays());
		passwordSecurityPolicyDto.setMaxNoofPassChangesDay(temp.getMaxNoofPassChangesDay());
		setHiddenTxnRef(getTxnRef());
				
		return "success";
	}	
	catch (NullPointerException  nullPointerException) {
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

	addActionError("Unable to perform the operation. Please try again");
	return "input";
	}
	

	
	
	private void setALLValueTODTO(PasswordSecurityPolicyDto obj)
	{
		PasswordSecurityPolicyDto temp = obj;
		
		passwordSecurityPolicyDto.setMinChars(temp.getMinChars());
		passwordSecurityPolicyDto.setMaxChars(temp.getMaxChars());
		passwordSecurityPolicyDto.setNoofDigits(temp.getNoofDigits());
		passwordSecurityPolicyDto.setNoofSpecialChars(temp.getNoofSpecialChars());
		passwordSecurityPolicyDto.setNoofUpperChars(temp.getNoofUpperChars());
		passwordSecurityPolicyDto.setNoofLowerChars(temp.getNoofLowerChars());
		passwordSecurityPolicyDto.setPasswordExpDays(temp.getPasswordExpDays());
		passwordSecurityPolicyDto.setMaxNoofPassChangesDay(temp.getMaxNoofPassChangesDay());
        session.put("ScreenData", passwordSecurityPolicyDto);
	}
	
	
	public String savePassPolicy()
	{
		try{
			String userId = (String)session.get(WebConstants.CONSTANT_USER_NAME);
			PendingAuthorizationService pendingAuthorizationService = (PendingAuthorizationService)ApplicationContextProvider.getBean("pendingAuthorizationService");
			PasswordSecurityPolicyService passwordSecurityPolicyService = (PasswordSecurityPolicyService) ApplicationContextProvider.getBean("passwordSecurityPolicyService");
			if(getSaveAction().equalsIgnoreCase("Approve")){
				passwordSecurityPolicyService.updateSecutiyPolicy(passwordSecurityPolicyDto);
				pendingAuthorizationService.changeStatus(getSaveAction(), getHiddenTxnRef());
			}
			else if(getSaveAction().equalsIgnoreCase("Reject"))
			{
					pendingAuthorizationService.changeStatus(getSaveAction(), getHiddenTxnRef());
			}
			else
			{
					pendingAuthorizationService.changeStatus(getSaveAction(), getHiddenTxnRef());
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
	
		addActionError("Unable to perform the operation. Please try again");
		return "input";
	}
	
	
	public String policyReset()
	{
		try 
		{
			PasswordSecurityPolicyService passwordSecurityPolicyService = (PasswordSecurityPolicyService) ApplicationContextProvider.getBean("passwordSecurityPolicyService");
			PasswordSecurityPolicyDto passwordSecurityPolicyDto = passwordSecurityPolicyService.getSecurityPolicy();
			setALLValueTODTO(passwordSecurityPolicyDto);
			return "success";
			
		} catch (Exception exception) {
			exception.printStackTrace();
			addFieldError("paymentMessageType", ConstantUtil.ERRORMESSAGE);
			return "input";
		}
	}
	
}
