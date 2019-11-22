package com.logica.ngph.web.action;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.springframework.context.ApplicationContextException;

import com.logica.ngph.common.ConstantUtil;
import com.logica.ngph.common.NGPHException;
import com.logica.ngph.service.EnquiryService;
import com.logica.ngph.service.MaintaningServiceOrchestrationService;
import com.logica.ngph.service.PendingAuthorizationService;
import com.logica.ngph.web.utils.ApplicationContextProvider;
import com.logica.ngph.web.utils.WebConstants;
import com.opensymphony.xwork2.ActionSupport;

public class MaintainingServiceOrchestrationAction extends ActionSupport
		implements SessionAware {

	private static final long serialVersionUID = -3364264106258064077L;
	static Logger logger = Logger.getLogger(ESBXMLCreatorAction.class);
	private Map<String, Object> session;
	private String streamID;
	private String msgType;
	private String subMsgType;
	private String Direction;
	private List<String> subMsgTypeDropDown = new ArrayList<String>();
	private List<String> msgTypeDropDown = new ArrayList<String>();
	private String sequenceOfString;
	private String message;
	private String checkForSubmit;
	private String saveAction;
	private String hiddenTxnRef;
	private String txnRef;
	private boolean validUserToApprove;
	public String getCheckForSubmit() {
		return checkForSubmit;
		
	}

	public void setCheckForSubmit(String checkForSubmit) {
		this.checkForSubmit = checkForSubmit;
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

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	public static String[] serviceIDToBeDragged;
	public static int serviceIDLength;
	

	public String getSequenceOfString() {
		return sequenceOfString;
	}

	public void setSequenceOfString(String sequenceOfString) {
		this.sequenceOfString = sequenceOfString;
	}

	public Map<String, Object> getSession() {
		return session;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	public String getStreamID() {
		return streamID;
	}

	public void setStreamID(String streamID) {
		this.streamID = streamID;
	}

	public String getMsgType() {
		return msgType;
	}

	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}

	public String getSubMsgType() {
		return subMsgType;
	}

	public void setSubMsgType(String subMsgType) {
		this.subMsgType = subMsgType;
	}

	public String getDirection() {
		return Direction;
	}

	public void setDirection(String direction) {
		Direction = direction;
	}

	public List<String> getSubMsgTypeDropDown() {
		return subMsgTypeDropDown;
	}

	public void setSubMsgTypeDropDown(List<String> subMsgTypeDropDown) {
		this.subMsgTypeDropDown = subMsgTypeDropDown;
		this.session.put("subMsgTypeDropDown", subMsgTypeDropDown);
	}

	public List<String> getMsgTypeDropDown() {
		return msgTypeDropDown;
	}

	public void setMsgTypeDropDown(List<String> msgTypeDropDown) {
		this.msgTypeDropDown = msgTypeDropDown;
		this.session.put("msgTypeDropDown", msgTypeDropDown);
	}
	@SkipValidation
	public String getValuesForOrchestration() {
		EnquiryService enquiryService = getEnquiryService();
		// for Message Type drop down
		try {
			setMsgTypeDropDown(enquiryService
					.getEnquiryList(ConstantUtil.ENQUIRY_MSG_TYPE));

			// for sub message type drop down
			setSubMsgTypeDropDown(enquiryService
					.getEnquiryList(ConstantUtil.EnquirySubMsgType));
			
			serviceIDToBeDragged = getText("serviceIDString").split(",");
			serviceIDLength=serviceIDToBeDragged.length;
			System.out.println(serviceIDLength);
		} catch (NGPHException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// for the currency
		return "success";
	}


	public String insertSequencyOfRecord() {
		MaintaningServiceOrchestrationService maintaningServiceOrchestrationService;
		maintaningServiceOrchestrationService = (MaintaningServiceOrchestrationService) ApplicationContextProvider
				.getBean("MaintaningServiceOrchestration");
		PendingAuthorizationService pendingAuthorizationService = (PendingAuthorizationService)ApplicationContextProvider.getBean("pendingAuthorizationService");
		String retrunValue=null;
		//String[] serviceID=getSequenceOfString().split(",");
		String checkForUnique=null;
		
		
			checkForUnique=maintaningServiceOrchestrationService.checkForUniqueStreamID(getStreamID());
			if(!checkForUnique.equals("0")){
				addFieldError(streamID, "Stream Id Is Allready Avilable For The Sequence");
			}
			else{
			System.out.println("String Sequency :---- "+getSequenceOfString());
			if(getSaveAction().equals("Approve")){
				String Status= pendingAuthorizationService.changeStatus(getSaveAction(), getHiddenTxnRef());
				if(Status.equals("success"))
						retrunValue= maintaningServiceOrchestrationService.insertRecord(getMsgType(),getSubMsgType(),getSequenceOfString(),getDirection(),getStreamID());
				
			}
			else if(getSaveAction().equals("Save"))
			{
				String delemitedString = getStreamID()+ConstantUtil.delimiter+getMsgType()+ConstantUtil.delimiter+getSubMsgType()+ConstantUtil.delimiter+getDirection()+ConstantUtil.delimiter+getSequenceOfString();
				String userId = (String)session.get(WebConstants.CONSTANT_USER_NAME);
				pendingAuthorizationService.getTranRef(delemitedString, "Maintaining Orchestration",userId);
			}
			else
			{
				String Status= pendingAuthorizationService.changeStatus(getSaveAction(), getHiddenTxnRef());
			}
			
			}
			
		
		
		
		
		return "success";
	}

	private EnquiryService getEnquiryService() {
		EnquiryService enquiryService = null;
		try {
			// ApplicationContext appcontext = new
			// ClassPathXmlApplicationContext("WebApplicationContext.xml");

			enquiryService = (EnquiryService) ApplicationContextProvider
					.getBean("enquiryService");
		} catch (ApplicationContextException applicationContextException) {
			logger.debug(ConstantUtil.RULE_ACTION + applicationContextException);
		}

		return enquiryService;
	}
	@SkipValidation
	public String getAuthorization() throws NGPHException, SQLException
	{
		PendingAuthorizationService pendingAuthorizationService = (PendingAuthorizationService)ApplicationContextProvider.getBean("pendingAuthorizationService");
		getValuesForOrchestration();
		setHiddenTxnRef(getTxnRef());
		setCheckForSubmit("Display_Approve_Reject");
		String userId = pendingAuthorizationService.getCreatedUser(getTxnRef());
		String userRole = pendingAuthorizationService.getUserType((String)session.get(WebConstants.CONSTANT_USER_NAME));
		if((((String)session.get(WebConstants.CONSTANT_USER_NAME)).equals(userId) || userRole.equals("T"))){
			setValidUserToApprove(false);
		} else {
			setValidUserToApprove(true);
		}
		String requiredString =pendingAuthorizationService.getScreenReturn(getTxnRef());
		String[] stringBreaker = requiredString.split("~");
		setStreamID(stringBreaker[0]);
		setMsgType(stringBreaker[1]);
		setSubMsgType(stringBreaker[2]);
		setDirection(stringBreaker[3]);
		setSequenceOfString(stringBreaker[4].substring(1, stringBreaker[4].length()));
	return "success";
	}
	public void validate() {
		System.out.println("HI Valodation");
	}

	public boolean getValidUserToApprove() {
		return validUserToApprove;
	}

	public void setValidUserToApprove(boolean validUserToApprove) {
		this.validUserToApprove = validUserToApprove;
	}
}
