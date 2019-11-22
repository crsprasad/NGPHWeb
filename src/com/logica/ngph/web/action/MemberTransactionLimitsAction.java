package com.logica.ngph.web.action;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.springframework.context.ApplicationContextException;

import com.logica.ngph.Entity.Limits;
import com.logica.ngph.Entity.SecUsers;
import com.logica.ngph.common.ConstantUtil;
import com.logica.ngph.common.dtos.EventAudit;
import com.logica.ngph.service.AuditService;
import com.logica.ngph.service.EnquiryService;
import com.logica.ngph.service.MemberTransactionLimitService;
import com.logica.ngph.service.PaymentMessageService;
import com.logica.ngph.service.PendingAuthorizationService;
import com.logica.ngph.web.utils.ApplicationContextProvider;
import com.logica.ngph.web.utils.WebConstants;
import com.opensymphony.xwork2.ActionSupport;

public class MemberTransactionLimitsAction extends ActionSupport implements SessionAware {

	
	private static final long serialVersionUID = 1L;
	
	private static Logger logger = Logger.getLogger(MemberTransactionLimitsAction.class);
	private List<String> channelDropDown = new ArrayList<String>();
	private List<String> bankCodeDropDown = new ArrayList<String>();
	private String actionPerform;
	private String bankCode;
	private String channel;
	private String debitLimit;
	private String creditLimit;
	private String hiddenTxnRef;
	private String txnRef;
	private String saveAction;
	private String checkForSubmit;
	private String hiddenRadio;
	private String old_NewScreen;
	private String flagForNewData;
	public String getFlagForNewData() {
		return flagForNewData;
	}
	public void setFlagForNewData(String flagForNewData) {
		this.flagForNewData = flagForNewData;
	}
	public String getOld_NewScreen() {
		return old_NewScreen;
	}
	public void setOld_NewScreen(String old_NewScreen) {
		this.old_NewScreen = old_NewScreen;
	}
	public String getHiddenRadio() {
		return hiddenRadio;
	}

	public void setHiddenRadio(String hiddenRadio) {
		this.hiddenRadio = hiddenRadio;
	}

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

	

	public String getActionPerform() {
		return actionPerform;
	}
	public void setActionPerform(String actionPerform) {
		this.actionPerform = actionPerform;
	}
	public String getBankCode() {
		return bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getDebitLimit() {
		return debitLimit;
	}

	public void setDebitLimit(String debitLimit) {
		this.debitLimit = debitLimit;
	}

	public String getCreditLimit() {
		return creditLimit;
	}

	public void setCreditLimit(String creditLimit) {
		this.creditLimit = creditLimit;
	}

	public List<String> getChannelDropDown() {
		return channelDropDown;
	}

	public void setChannelDropDown(List<String> channelDropDown) {
		this.channelDropDown = channelDropDown;
		this.session.put("channelDropDown", channelDropDown);
	}

	public List<String> getBankCodeDropDown() {
		return bankCodeDropDown;
	}

	public void setBankCodeDropDown(List<String> bankCodeDropDown) {
		this.bankCodeDropDown = bankCodeDropDown;
		this.session.put("bankCodeDropDown", bankCodeDropDown);
	}

	private Map<String, Object> session;
	public Map<String, Object> getSession() {
		return session;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
	@SkipValidation
	public String initalizeAllLimitsFields()
	{
		try{
			
			EnquiryService enquiryService = (EnquiryService)ApplicationContextProvider.getBean("enquiryService");
			MemberTransactionLimitService limitService = (MemberTransactionLimitService)ApplicationContextProvider.getBean("memberTransactionLimitService");
			setChannelDropDown(enquiryService.getEnquiryList(ConstantUtil.EnquiryChannel));
			setBankCodeDropDown(limitService.getBankCode());
			setActionPerform("ADD");
			limitService = null;
			enquiryService=null;
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
	
		return "input";
	}
	@SkipValidation
	public String getTransactionLimitDetails()
	{
		try{
			MemberTransactionLimitService limitService = (MemberTransactionLimitService)ApplicationContextProvider.getBean("memberTransactionLimitService");
			
			Limits limits=  limitService.getAllDetails(getBankCode());
			setChannel(limits.getChannel());
			setDebitLimit(limits.getDebitLimit());
			setCreditLimit(limits.getCreditLimit());
			
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
	
		return "input";
		
	}
	
	
	public String callsendToAuthorize()
	{
		try{
			String txnKey="";
			AuditService	auditService = (AuditService)ApplicationContextProvider.getBean("auditService");
			MemberTransactionLimitService limitService = (MemberTransactionLimitService)ApplicationContextProvider.getBean("memberTransactionLimitService");
			PendingAuthorizationService pendingAuthorizationService = (PendingAuthorizationService)ApplicationContextProvider.getBean("pendingAuthorizationService");
			String userId = (String)session.get(WebConstants.CONSTANT_USER_NAME);
			if(getActionPerform().equals("ADD"))
			{
				if(limitService.isAllReady(getBankCode())==true){
					String delimited = getBankCode()+"~"+getChannel()+"~"+getDebitLimit()+"~"+getCreditLimit()+"~"+getActionPerform();
					txnKey = pendingAuthorizationService.getTranRef(delimited,"Member Transaction Limit",userId);
					auditService.saveEventAuditData(getEventAuditDtoForSubmit());		
					return "success";
				}
				else
				{
					addFieldError("bankCode", "Limit already exists for the member. Use Modify option");
					return "input";
				}
			}
			else 
			{
				if(!limitService.isAllReady(getBankCode())){
					String delimited = getBankCode()+"~"+getChannel()+"~"+getDebitLimit()+"~"+getCreditLimit()+"~"+getActionPerform();
					txnKey = pendingAuthorizationService.getTranRef(delimited,"Member Transaction Limit",userId);
					auditService.saveEventAuditData(getEventAuditDtoForSubmit());		
				return "success";
			}
			else
			{
				addFieldError("bankCode", "Limit not defined for the member. Cannot modify");
				return "input";
			}
				
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

	return "input";
		
		
	}
	@SkipValidation
	public String callsentToApprove()
	{
		try{
			
			initalizeAllLimitsFields();
			PendingAuthorizationService pendingAuthorizationService = (PendingAuthorizationService)ApplicationContextProvider.getBean("pendingAuthorizationService");
			setCheckForSubmit("Display_Approve_Reject");
			String userId = pendingAuthorizationService.getCreatedUser(getTxnRef());
			String userRole = pendingAuthorizationService.getUserType((String)session.get(WebConstants.CONSTANT_USER_NAME));
			if((((String)session.get(WebConstants.CONSTANT_USER_NAME)).equals(userId) || userRole.equals("T"))){
				setValidUserToApprove(false);
			} else {
				setValidUserToApprove(true);
			}
			String[] tempScreenString =pendingAuthorizationService.getScreenReturn(getTxnRef()).split("~");
			setBankCode(tempScreenString[0]);
			setChannel(tempScreenString[1]);
			setDebitLimit(tempScreenString[2]);
			setCreditLimit(tempScreenString[3]);
			setActionPerform(tempScreenString[4].trim());
			setHiddenRadio(tempScreenString[4]);
			setHiddenTxnRef(getTxnRef());
			setOld_NewScreen(null);
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

		return "input";
			
	}
	@SkipValidation
	public String doFinaLOperationForTransactionLimits()
	{
		try{
			AuditService	auditService = (AuditService)ApplicationContextProvider.getBean("auditService");
			PendingAuthorizationService pendingAuthorizationService = (PendingAuthorizationService)ApplicationContextProvider.getBean("pendingAuthorizationService");
			MemberTransactionLimitService limitService = (MemberTransactionLimitService)ApplicationContextProvider.getBean("memberTransactionLimitService");
			if(getSaveAction().equalsIgnoreCase("Approve")){
				if(StringUtils.isNotEmpty(getActionPerform()) && StringUtils.isNotBlank(getActionPerform()) && StringUtils.isNotEmpty(getBankCode()) && StringUtils.isNotBlank(getBankCode())
				&& StringUtils.isNotEmpty(getChannel()) && StringUtils.isNotBlank(getChannel()) && StringUtils.isNotEmpty(getDebitLimit()) && StringUtils.isNotBlank(getDebitLimit())		
				&& StringUtils.isNotEmpty(getCreditLimit()) && StringUtils.isNotBlank(getCreditLimit())){
					
			
			Limits limits = new Limits();
			limits.setIdentifier(getBankCode());
			limits.setChannel(getChannel());
			limits.setDebitLimit(getDebitLimit());
			limits.setCreditLimit(getCreditLimit());
			limits.setLimitFor("A");
			if(getSaveAction().equals("Approve"))
			{
				if(getActionPerform().equals("ADD"))
				{
					if(limitService.isAllReady(getBankCode())==true){
					String success = limitService.doOperation(limits,getActionPerform());
						if(success.equals("success"))
						{
							pendingAuthorizationService.changeStatus(getSaveAction(), getHiddenTxnRef());
							auditService.saveEventAuditData(getEventAuditDtoForApprove());		
							
							return "success";
						}
					}
				}else if(getActionPerform().equals("MODIFY") || getActionPerform().equals("DELETE"))
				{
					if(limitService.isAllReady(getBankCode())==false){
					String success = limitService.doOperation(limits,getActionPerform());
						if(success.equals("success"))
						{
							pendingAuthorizationService.changeStatus(getSaveAction(), getHiddenTxnRef());
							auditService.saveEventAuditData(getEventAuditDtoForApprove());		
							return "success";
						}
					}
				}
			}			
				else if(getSaveAction().equalsIgnoreCase("Reject"))
				{
				pendingAuthorizationService.changeStatus(getSaveAction(), getHiddenTxnRef());
				auditService.saveEventAuditData(getEventAuditDtoForReject());	
				return "rejected";
				}
				 return "success";
			}
			else
			{
				if(StringUtils.isEmpty(getBankCode()) && StringUtils.isBlank(getBankCode()))
						{
								addFieldError("bankCode", "Bank Code Is Required");
						}
				if(StringUtils.isEmpty(getChannel()) && StringUtils.isBlank(getChannel()))
				{
						addFieldError("channel", "Channel Is Required");
				}
				if(StringUtils.isEmpty(getDebitLimit()) && StringUtils.isBlank(getDebitLimit()))
				{
						addFieldError("debitLimit", "Debit Limit Is Required");
				}
				if(StringUtils.isEmpty(getCreditLimit()) && StringUtils.isBlank(getCreditLimit()))
				{
						addFieldError("creditLimit", "Credit Limit Is Required");
				}
				 return "input";
			}
				}
				if((getActionPerform().equalsIgnoreCase("MODIFY")||getActionPerform().equalsIgnoreCase("DELETE")) && getSaveAction().equals("Modify"))
				{
					if(limitService.isAllReady(getBankCode())==false){
				 getTransactionLimitDetails();
				 return "input";
				}else
				{
					addFieldError("bankCode", "Bank Code Is Not Available In System");
					return "input";
				}
				 
					
				}
				if(getSaveAction().equalsIgnoreCase("Reject"))
				{
				pendingAuthorizationService.changeStatus(getSaveAction(), getHiddenTxnRef());
				auditService.saveEventAuditData(getEventAuditDtoForReject());		
				return "rejected";
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
		addActionError("Unable to Process. Please Try again");
		return "input";
	}
	
	private EventAudit getEventAuditDtoForSubmit(){
		EventAudit eventAudit = getEventAuditDto();
		eventAudit.setAuditEventId("NGPHGUIMTL0001");
		String userId = (String)session.get(WebConstants.CONSTANT_USER_NAME);	
		String[] extraInformation = {userId," Member Transaction Limits "};		
		eventAudit.setExtraInformation(extraInformation);
		String eventDescription = "User {0} has Initiated {1}";
		MessageFormat msgFormat = new MessageFormat(eventDescription);		
		eventAudit.setAuditEventDesc(msgFormat.format(extraInformation));
		
		return eventAudit;		
	}
	private EventAudit getEventAuditDtoForApprove(){
		EventAudit eventAudit = getEventAuditDto();
		eventAudit.setAuditEventId("NGPHGUIMTL0002");
		String userId = (String)session.get(WebConstants.CONSTANT_USER_NAME);	
		String[] extraInformation = {userId," Member Transaction Limits "};		
		eventAudit.setExtraInformation(extraInformation);
		String eventDescription = "User {0} has Approved {1}";
		MessageFormat msgFormat = new MessageFormat(eventDescription);		
		eventAudit.setAuditEventDesc(msgFormat.format(extraInformation));
		
		return eventAudit;		
	}
	private EventAudit getEventAuditDtoForReject(){
		EventAudit eventAudit = getEventAuditDto();
		eventAudit.setAuditEventId("NGPHGUIMTL0003");
		String userId = (String)session.get(WebConstants.CONSTANT_USER_NAME);	
		String[] extraInformation = {userId," Member Transaction Limits "};		
		eventAudit.setExtraInformation(extraInformation);
		String eventDescription = "User {0} has Rejected {1}";
		MessageFormat msgFormat = new MessageFormat(eventDescription);		
		eventAudit.setAuditEventDesc(msgFormat.format(extraInformation));
		
		return eventAudit;		
	}	
	
	private EventAudit getEventAuditDto(){
		EventAudit eventAudit = new EventAudit();
		PaymentMessageService paymentMessageService = (PaymentMessageService)ApplicationContextProvider.getBean("paymentMessageService");		
		eventAudit.setAuditSource("0");
		String UserID = (String)session.get(WebConstants.CONSTANT_USER_NAME);
		SecUsers userDetails= paymentMessageService.getUserDetails(UserID);
			String msgBranch = userDetails.getUserBranch();
			String msgDept = userDetails.getUserDept();
		eventAudit.setAuditBranch(msgBranch);
		
		eventAudit.setAuditDept(msgDept);
		eventAudit.setAuditSource(getClass().getName());
		
		
		return eventAudit;
		
	}
	@SkipValidation
	public String callSeeOldData()
	{
		try{
			if(getOld_NewScreen().equals("OLD")){
				getTransactionLimitDetails();
				setFlagForNewData("flagForNewData");
				}else if(getOld_NewScreen().equals("NEW"))
				{
					callsentToApprove();
					setFlagForNewData(null);
				}
			setCheckForSubmit("Display_Approve_Reject");
			setValidUserToApprove(true);
		return SUCCESS;	
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
public void validate()
{}
	
}
