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

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.jboss.util.Base64;
import org.springframework.context.ApplicationContextException;

import com.logica.ngph.dtos.PartyDTO;
import com.logica.ngph.service.LetterOfCreditService;
import com.logica.ngph.service.PendingAuthorizationService;
import com.logica.ngph.web.utils.ApplicationContextProvider;
import com.logica.ngph.web.utils.WebConstants;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class IFSCMaintainAction extends ActionSupport implements ModelDriven<PartyDTO>,SessionAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(LcOpenAction.class);
	private Map<String, Object> session;
	private List searchList;
	private String code;
	private String txnRef;
	private String saveAction;
	private String checkForSubmit;
	private boolean validUserToApprove;
	private String hiddenTxnRef;
	private List searchIFSCList;
	private PartyDTO partyDTO = new PartyDTO(); 
	private String ifscCode;

	
	

	public String getIfscCode() {
		return ifscCode;
	}
	public void setIfscCode(String ifscCode) {
		this.ifscCode = ifscCode;
	}
	/**
	 * @return the searchIFSCList
	 */
	public List getSearchIFSCList() {
		return searchIFSCList;
	}
	/**
	 * @param searchIFSCList the searchIFSCList to set
	 */
	public void setSearchIFSCList(List searchIFSCList) {
		this.searchIFSCList = searchIFSCList;
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

	
	public PartyDTO getModel() {
		
		return partyDTO;
	}
	public List getSearchList() {
		return searchList;
	}
	public void setSearchList(List searchList) {
		this.searchList = searchList;
		
	}
	public Map<String, Object> getSession() {
		return session;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	@SkipValidation
	public String getIFSCMaintain()
	{
		partyDTO.setActionPerform("ADD");
		return SUCCESS;
	}
	
	
	public String sendForApproval()
	{
		try{
			String txnKey="";
			PendingAuthorizationService pendingAuthorizationService = (PendingAuthorizationService)ApplicationContextProvider.getBean("pendingAuthorizationService");
			String userId = (String)session.get(WebConstants.CONSTANT_USER_NAME);
			if(!getHiddenTxnRef().isEmpty())
			{
				pendingAuthorizationService.updateRejectStatusToPending(getHiddenTxnRef());
			}else
			{
				txnKey = pendingAuthorizationService.getTranRef(serializeObject(),"Maintain IFSC",userId);
			}
			
			session.put("screenRef", txnKey);
			return SUCCESS;

		
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
	@SkipValidation
	public String getIFSCDateSentForApproval()
	{
		try{
			PendingAuthorizationService pendingAuthorizationService = (PendingAuthorizationService)ApplicationContextProvider.getBean("pendingAuthorizationService");
		
			setCheckForSubmit("Display_Approve_Reject");
			String userId = pendingAuthorizationService.getCreatedUser(getTxnRef());
			String userRole = pendingAuthorizationService.getUserType((String)session.get(WebConstants.CONSTANT_USER_NAME));
			if((((String)session.get(WebConstants.CONSTANT_USER_NAME)).equals(userId) || userRole.equals("T"))){
				setValidUserToApprove(false);
			} else {
				setValidUserToApprove(true);
			}
			String tempScreenString =pendingAuthorizationService.getScreenReturn(getTxnRef());
			PartyDTO temp= getSerializedObject(tempScreenString);
			partyDTO.setActionPerform(temp.getActionPerform());
			partyDTO.setIfscCode(temp.getIfscCode());
			partyDTO.setPartyName(temp.getPartyName());
			partyDTO.setBranchName(temp.getBranchName());
			partyDTO.setPartyAddress(temp.getPartyAddress());
			partyDTO.setStateName(temp.getStateName());
			partyDTO.setDistricName(temp.getDistricName());
			partyDTO.setCityName(temp.getCityName());
			partyDTO.setMicrCode(temp.getMicrCode());
			partyDTO.setIfscType(temp.getIfscType());
			setHiddenTxnRef(getTxnRef());
			return SUCCESS;
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
	public String getIFSCApproval()
	{
		try{
			PendingAuthorizationService pendingAuthorizationService = (PendingAuthorizationService)ApplicationContextProvider.getBean("pendingAuthorizationService");
			
			if(getSaveAction().equals("Reject"))
			{
				pendingAuthorizationService.changeStatus(getSaveAction(), getHiddenTxnRef());
				return "rejected";
			}
			else
			{
				LetterOfCreditService letterOfCreditService =  (LetterOfCreditService)  ApplicationContextProvider.getBean("letterOfCreditService");
				letterOfCreditService.saveIFSC(partyDTO);
				pendingAuthorizationService.changeStatus(getSaveAction(), getHiddenTxnRef());
				return SUCCESS;
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

		addActionError("Unable to perform the operation. Please try again");
		return "input";
	}
	public void validate()
{
	LetterOfCreditService letterOfCreditService =  (LetterOfCreditService)  ApplicationContextProvider.getBean("letterOfCreditService");
	boolean ifscCheck = letterOfCreditService.checkPartyIFSC(partyDTO.getIfscCode());
	
	if(partyDTO.getActionPerform().equals("ADD"))
	{
		if(ifscCheck==false)
		{
			addFieldError("ifscCode", "IFSC Already Exist");
		}
		
	}
	else if(partyDTO.getActionPerform().equals("DELETE"))
	{
		if(ifscCheck==true)
		{
			addFieldError("ifscCode", "IFSC Is Not Available");
		}
	}
}
	
	@SkipValidation
	public String displayIFSCData()
	{
		try{
			String ifscCode = partyDTO.getIfscCode();
			PartyDTO patryDto = new PartyDTO();
				LetterOfCreditService letterOfCreditService =  (LetterOfCreditService)  ApplicationContextProvider.getBean("letterOfCreditService");
				patryDto = letterOfCreditService.displayIFSCData(ifscCode);
				partyDTO.setIfscCode(patryDto.getIfscCode());
				partyDTO.setPartyName(patryDto.getPartyName());
				partyDTO.setBranchName(patryDto.getBranchName());
				partyDTO.setPartyAddress(patryDto.getPartyAddress());
				partyDTO.setStateName(patryDto.getStateName());
				partyDTO.setDistricName(patryDto.getDistricName());
				partyDTO.setCityName(patryDto.getCityName());
				partyDTO.setMicrCode(patryDto.getMicrCode());
				partyDTO.setIfscType(patryDto.getIfscType());
				
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
			return "success";
	}
	
	@SkipValidation
	public String resetIFSCMaintain() 
	{
		try{
			partyDTO.setIfscCode("");
			partyDTO.setPartyName("");
			partyDTO.setBranchName("");
			partyDTO.setPartyAddress("");
			partyDTO.setStateName("");
			partyDTO.setDistricName("");
			partyDTO.setCityName("");
			partyDTO.setMicrCode(0);
			partyDTO.setIfscType("");
		
			return "success";
		}catch (Exception e) {
			return "input";
		}
	}
	
	private String serializeObject(){
		PartyDTO partyDto = new PartyDTO();
		try{
			String userId = (String)session.get(WebConstants.CONSTANT_USER_NAME);
			
			partyDto = partyDTO;
			System.out.print("IFSC Code :"+partyDto.getIfscCode());
			String fileName ="serial_"+userId+".ser";
		FileOutputStream fos = new FileOutputStream(fileName);
        OutputStream buffer = new BufferedOutputStream( fos );
        ObjectOutputStream oos = new ObjectOutputStream(buffer);
        oos.writeObject(partyDto);
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
	
	
	private PartyDTO getSerializedObject(String objectString){
		
		try{
					
					byte[] decoded = Base64.decode(objectString);
		            
		            FileOutputStream foss = new FileOutputStream("targetUserObject.ser");
		            foss.write(decoded);
		            foss.close();
		            PartyDTO testDTO = null;
		            
		            FileInputStream fiss = new FileInputStream("targetUserObject.ser");
		            BufferedInputStream bufferee = new BufferedInputStream( fiss );
		            ObjectInputStream oiss = new ObjectInputStream(bufferee);
		            testDTO = (PartyDTO)oiss.readObject();
		            oiss.close();
		            System.out.println("object2: " + testDTO); 
		            System.out.print("IFSC Code :"+testDTO.getIfscCode());
		          
		            return testDTO;

				}
				catch (Exception e) {
					e.printStackTrace();
					return null;
				}
			}
}
