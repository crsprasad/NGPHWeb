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
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.jboss.util.Base64;
import org.springframework.context.ApplicationContextException;

import com.logica.ngph.common.NGPHException;
import com.logica.ngph.dtos.CustomerDetailsDto;
import com.logica.ngph.service.PendingAuthorizationService;
import com.logica.ngph.web.utils.ApplicationContextProvider;
import com.logica.ngph.web.utils.WebConstants;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;


/**
 * @author chakkar
 *
 */
public class CustomerDetailsAction extends ActionSupport implements ModelDriven<CustomerDetailsDto>,SessionAware {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static Logger logger = Logger.getLogger(AccountMaintenanceAction.class);
	CustomerDetailsDto customerDetailsDto = new CustomerDetailsDto();
	private Map<String, Object> session;
	private String hiddenTxnRef;
	private String txnRef;
	private String saveAction;
	private String checkForSubmit;
	private boolean validUserToApprove;

	
	
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

	public String getSaveAction() {
		return saveAction;
	}

	public void setSaveAction(String saveAction) {
		this.saveAction = saveAction;
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

	public Map<String, Object> getSession() {
		return session;
	}
	
	public void setSession(Map<String, Object> session) {
		
		this.session = session;
	}
	
	@SkipValidation
	public String displayCustomerDetails() throws NGPHException
	{
		logger.info("Inside AccountMaintanence<displayAccountMantenance>");
		
		logger.info("End AccountMaintanence<displayAccountMantenance>");
		return "success";
	}
	
	/*
	 * Action to create/modify an user
	 */
	public String performcustomerDetailsForApproval() {
		try {	
			System.out.println("Calling performcustomerDetailsForApproval method");
			String txnKey="";
			PendingAuthorizationService pendingAuthorizationService = (PendingAuthorizationService)ApplicationContextProvider.getBean("pendingAuthorizationService");
			String userId = (String)session.get(WebConstants.CONSTANT_USER_NAME);
				  
				  if(!getHiddenTxnRef().isEmpty())
					{
						pendingAuthorizationService.updateRejectStatusToPending(getHiddenTxnRef());
					}else
					{
						 txnKey = pendingAuthorizationService.getTranRef(serializeObject(),"Customer Maintenance",userId);
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
		CustomerDetailsDto customerDetailsDTO = new CustomerDetailsDto();
		try{
			String userId = (String)session.get(WebConstants.CONSTANT_USER_NAME);
			customerDetailsDTO = customerDetailsDto;
			System.out.print("Customer First Name :"+customerDetailsDTO.getCustFirstName());
			String fileName ="serial_"+userId+".ser";
		FileOutputStream fos = new FileOutputStream(fileName);
        OutputStream buffer = new BufferedOutputStream( fos );
        ObjectOutputStream oos = new ObjectOutputStream(buffer);
        oos.writeObject(customerDetailsDTO);
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
	@SkipValidation
	public String getCustomerDetailsAuthorization()
	{
		String tempScreenString = null;
		PendingAuthorizationService pendingAuthorizationService = (PendingAuthorizationService)ApplicationContextProvider.getBean("pendingAuthorizationService");
	try{
		if(getTxnRef()!=null)
		{
		setCheckForSubmit("Display_Approve_Reject");
		String userId = pendingAuthorizationService.getCreatedUser(getTxnRef());
		String userRole = pendingAuthorizationService.getUserType((String)session.get(WebConstants.CONSTANT_USER_NAME));
		if((((String)session.get(WebConstants.CONSTANT_USER_NAME)).equals(userId) || userRole.equals("T"))){
			setValidUserToApprove(false);
		} else {
			setValidUserToApprove(true);
		}
		 tempScreenString =pendingAuthorizationService.getScreenReturn(getTxnRef());
		}
		
		CustomerDetailsDto temp= getSerializedObject(tempScreenString);
		customerDetailsDto.setCustFirstName(temp.getCustFirstName());
		customerDetailsDto.setCustLastName(temp.getCustLastName());
		customerDetailsDto.setCustAddress(temp.getCustAddress());
		customerDetailsDto.setPhoneNo(temp.getPhoneNo());
		customerDetailsDto.setCustEmail(temp.getCustEmail());
		customerDetailsDto.setCustAction(temp.getCustAction());
		customerDetailsDto.setCustActive(temp.getCustActive());
			
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
	
	private CustomerDetailsDto getSerializedObject(String objectString)
	{
		try
		{	
				byte[] decoded = Base64.decode(objectString);
		        FileOutputStream foss = new FileOutputStream("targetUserObject.ser");
		        foss.write(decoded);
		        foss.close();
		        CustomerDetailsDto testDTO = null;
		        FileInputStream fiss = new FileInputStream("targetUserObject.ser");
		        BufferedInputStream bufferee = new BufferedInputStream( fiss );
		        ObjectInputStream oiss = new ObjectInputStream(bufferee);
		        testDTO = (CustomerDetailsDto)oiss.readObject();
		        oiss.close();
		        System.out.println("object2: " + testDTO); 
		        System.out.print("User(testDTO) :"+testDTO.getCustLastName());
		        return testDTO;
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public CustomerDetailsDto getModel() {
		return null;
	}

	

	public CustomerDetailsDto getCustomerDetailsDto() {
		return customerDetailsDto;
	}

	public void setCustomerDetailsDto(
			CustomerDetailsDto customerDetailsDto) {
		this.customerDetailsDto = customerDetailsDto;
	}
}
