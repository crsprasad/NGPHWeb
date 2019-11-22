package com.logica.ngph.web.action;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.context.ApplicationContextException;

import com.logica.ngph.common.PaymentStatusEnum;
import com.logica.ngph.common.enums.EnumLcStatus;
import com.logica.ngph.dtos.LCCanonicalDto;
import com.logica.ngph.service.LcOpenService;
import com.logica.ngph.service.PendingAuthorizationService;
import com.logica.ngph.web.utils.ApplicationContextProvider;
import com.logica.ngph.web.utils.WebConstants;
import com.opensymphony.xwork2.ActionSupport;

public class MarkAcknowledgementAction extends ActionSupport implements SessionAware{
	/**
	 * 
	 */
	private Logger logger = Logger.getLogger(MarkAcknowledgementAction.class);
	private static final long serialVersionUID = 1L;
	private Map<String, Object> session;
	private String lcNumber;
	private String currentStatus;
	private String ackStatus;
	private String hiddenTxnRef;
	private String txnRef;
	private String saveAction;
	private String checkForSubmit;
	private boolean validUserToApprove;
	private String lcNumberFetch;
	public String getLcNumberFetch() {
		return lcNumberFetch;
	}

	public void setLcNumberFetch(String lcNumberFetch) {
		this.lcNumberFetch = lcNumberFetch;
	}
	private List<LCCanonicalDto> searchList;
	public List<LCCanonicalDto> getSearchList() {
		return searchList;
	}
	
	public void setSearchList(List<LCCanonicalDto> searchList) {
		this.searchList = searchList;
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

	public String getLcNumber() {
		return lcNumber;
	}

	public void setLcNumber(String lcNumber) {
		this.lcNumber = lcNumber;
	}

	public String getCurrentStatus() {
		return currentStatus;
	}

	public void setCurrentStatus(String currentStatus) {
		this.currentStatus = currentStatus;
	}

	public String getAckStatus() {
		return ackStatus;
	}

	public void setAckStatus(String ackStatus) {
		this.ackStatus = ackStatus;
	}

	public Map<String, Object> getSession() {
		return session;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
	public String displayMarkAck()
	{
		return "success";
	}
	
	
	public String PopulatedLCContent()
	{
		try{
			LcOpenService lcOpenService = (LcOpenService) ApplicationContextProvider.getBean("lcOpenService");
			setSearchList(lcOpenService.getLCNumber(getLcNumberFetch()));
			
			
			return "success";
		}catch (Exception e) {
			return "input";
			// TODO: handle exception
		}
	}
	public String fetchStatus()
	{
		try{
			LcOpenService lcOpenService = (LcOpenService) ApplicationContextProvider.getBean("lcOpenService");
			String lcStatus = lcOpenService.getLcStatus(getLcNumber());
			EnumLcStatus val = EnumLcStatus.findEnumByTag(lcStatus);
			setCurrentStatus(val.toString().replaceAll("_"," "));
			if(lcStatus.equals("1"))
			{
				setAckStatus("PRE ADVICE ACK");
			}
			else if(lcStatus.equals("3"))
			{
				setAckStatus("REGISTERED");			
			}
			else if(lcStatus.equals("5"))
			{
				setAckStatus("AMEND REGISTERED");		
			}
			else if(lcStatus.equals("7"))
			{
				setAckStatus("PYMT ADVICE ACK");			
			}
			else if(lcStatus.equals("9"))
			{
				setAckStatus("PYMT AUTHORISEACK");		
			}
			else if(lcStatus.equals("17"))
			{
				setAckStatus("TRANSFERRED_ACK");		
			}
			
			else if(lcStatus.equals("2") || lcStatus.equals("4") || lcStatus.equals("6")|| lcStatus.equals("8") || lcStatus.equals("10")||lcStatus.equals("18"))
			{
				addFieldError("lcNumber", "Lc Number Not In Valid Status For Marking Acknowledge");
				return "input";
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
	
		return "input";

	}
	public String sendForApproval()
	{
		try{
			if(StringUtils.isNotBlank(getLcNumber()) && StringUtils.isNotEmpty(getLcNumber())){
			String txnKey="";
			PendingAuthorizationService pendingAuthorizationService = (PendingAuthorizationService)ApplicationContextProvider.getBean("pendingAuthorizationService");
			String userId = (String)session.get(WebConstants.CONSTANT_USER_NAME);
			
			txnKey = pendingAuthorizationService.getTranRef(getLcNumber()+"~"+getCurrentStatus()+"~"+getAckStatus(),"Mark Acknowlegment",userId);
			
			session.put("screenRef", txnKey);
			return "success";
			}
			else
			{
				addFieldError("lcNumber", "Lc number is Required");
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
	
		return "input";
		
	}
	
	public String displayAuthorizationDetails()
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
			String[] tempScreenString =pendingAuthorizationService.getScreenReturn(getTxnRef()).split("~");
			if(tempScreenString!=null)
			{
				setLcNumber(tempScreenString[0]);
				setCurrentStatus(tempScreenString[1]);
				setAckStatus(tempScreenString[2]);
			
			}
			setHiddenTxnRef(getTxnRef());
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
	public String changeLcStatus()
	{
		try{
			PendingAuthorizationService pendingAuthorizationService = (PendingAuthorizationService)ApplicationContextProvider.getBean("pendingAuthorizationService");
			if(getSaveAction().equals("Approve")){
			LcOpenService lcOpenService = (LcOpenService) ApplicationContextProvider.getBean("lcOpenService");
			String temStatus=getAckStatus().replaceAll(" ", "_");
			String status = EnumLcStatus.findPaymentStatusEnumByName(temStatus);
			lcOpenService.saveChangeStatus(getLcNumber(),status);
			pendingAuthorizationService.changeStatus(getSaveAction(), getHiddenTxnRef());
			}
			else if(getSaveAction().equals("Reject"))
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
	
		return "input";
		
	}

}
