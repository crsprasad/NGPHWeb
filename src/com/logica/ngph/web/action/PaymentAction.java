package com.logica.ngph.web.action;




import java.math.BigDecimal;
import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Map;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.interceptor.validation.SkipValidation;

import com.logica.ngph.common.ConstantUtil;
import com.logica.ngph.common.NGPHException;
import com.logica.ngph.common.dtos.EventAudit;
import com.logica.ngph.common.dtos.EventMaster;
import com.logica.ngph.common.dtos.NgphCanonical;
import com.logica.ngph.common.enums.PaymentNameEnum;
import com.logica.ngph.common.utils.NGPHUtil;
import com.logica.ngph.dtos.ModifiedMessagesDto;
import com.logica.ngph.service.AuditService;
import com.logica.ngph.service.AuthStpService;
import com.logica.ngph.service.PaymentService;
import com.logica.ngph.web.utils.ApplicationContextProvider;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;


public class PaymentAction extends ActionSupport implements ModelDriven<NgphCanonical>,SessionAware{
	 
	/**
	 * 
	 */
	private static final long serialVersionUID = 3268396278216534999L;
	static Logger logger = Logger.getLogger(PaymentAction.class);
	NgphCanonical ngphCanonical= new NgphCanonical();
	private Map<String, Object> session;
	
	public Map<String, Object> getSession() {
		return session;
	}
	
	

	private String paymentEntryStatus;
	private String repairComments;
	private String popUpRepairComments;
	private String paymentbuttonName;
	private PaymentService paymentService;
	private AuditService auditService;
	
	private AuthStpService authStpService;


	public void setAuthStpService(AuthStpService authStpService) {
		this.authStpService = authStpService;
	}

	public void setAuditService(AuditService auditService) {
		this.auditService = auditService;
	}

	public void setPaymentService(PaymentService paymentService) {
		this.paymentService = paymentService;
	}

	public String getPaymentbuttonName() {
		return paymentbuttonName;
	}

	public void setPaymentbuttonName(String paymentbuttonName) {
		this.paymentbuttonName = paymentbuttonName;
	}

	public String getPopUpRepairComments() {
		return popUpRepairComments;
	}

	public void setPopUpRepairComments(String popUpRepairComments) {
		this.popUpRepairComments = popUpRepairComments;
	}

	public String getRepairComments() {
		return repairComments;
	}

	public void setRepairComments(String repairComments) {
		this.repairComments = repairComments;
	}

	

	public String getPaymentEntryStatus() {
		return paymentEntryStatus;
	}

	public void setPaymentEntryStatus(String paymentEntryStatus) {
		this.paymentEntryStatus = paymentEntryStatus;
		this.session.put("paymentEntryStatus", paymentEntryStatus);
	}

	public NgphCanonical getNgphCanonical() {
		return ngphCanonical;
	}

	public void setNgphCanonical(NgphCanonical ngphCanonical) {
		this.ngphCanonical = ngphCanonical;
	}
	
	
	//private Map<String, Object> session;
	/*public Map<String, Object> getSession() {
		return session;
	}*/

	private String valueDate;
	
	public String getValueDate() {
		return valueDate;
	}

	public void setValueDate(String valueDate) {
		this.valueDate = valueDate;
	}
	/**
	* This method is used to load the Payment Entry Screen 
	* @return String payment
	*/
	@SkipValidation
	public String loadPayment(){
		session.remove("paymentEntryStatus");
		session.remove("paymentStatusSession");
		
	return 	"payment";
	}
	/**
	* This method is used to load the Payment Entry Screen for Repair 
	* @return String payment
	*/
	@SkipValidation
	public String performPayment(){
	
		String msgReference = (String)ActionContext.getContext().getSession().get("messageIndex");
		String paymentStatus = (String)ActionContext.getContext().getSession().get("paymentStatusSession");
		setPaymentEntryStatus(paymentStatus);
		getpaymentButnName(paymentStatus);
		if(msgReference != null){
			
			paymentService = (PaymentService)ApplicationContextProvider.getBean("paymentService");
			
		
		try {
		ngphCanonical =	paymentService.getPayment(msgReference);
		
		this.session.put("OriginalNgPhCanonical", ngphCanonical);
		//this.session.put("msgReference", msgReference);
		if(ngphCanonical.getMsgValueDate() != null){
			setValueDate(ngphCanonical.getMsgValueDate().toString().substring(0,10));
		}
		
		} catch (NGPHException ngphException) {
			AuditServiceUtil.logNgphException(ngphException,logger);
			
		}catch (Exception exception)
		{
			AuditServiceUtil.logException(exception,logger);
		}
	}
	
		return "payment";
	}

	private void getpaymentButnName(String internalPaymentName){
		setPaymentbuttonName(PaymentNameEnum.findPaymentNamesEnumByName(internalPaymentName));
	}
	@SkipValidation
	public String viewPayment(){
	
		String msgReference = (String)ActionContext.getContext().getSession().get("messageIndex");
		String paymentStatus = (String)ActionContext.getContext().getSession().get("paymentStatusSession");
		setPaymentEntryStatus(paymentStatus);
		if(msgReference != null){
			paymentService = (PaymentService)ApplicationContextProvider.getBean("paymentService");
		try {
		ngphCanonical =	paymentService.getPayment(msgReference);
		
		this.session.put("OriginalNgPhCanonical", ngphCanonical);
		//this.session.put("msgReference", msgReference);
		if(ngphCanonical.getMsgValueDate() != null){
			setValueDate(ngphCanonical.getMsgValueDate().toString().substring(0,10));
		}
		
		} catch (NGPHException ngphException) {
			AuditServiceUtil.logNgphException(ngphException,logger);
			
		}catch (Exception exception)
		{
			AuditServiceUtil.logException(exception,logger);
		}
	}
	
		return "viewPayment";
	}
	/**
	* This method is used to get the Application Context
	* @return PaymentService paymentService
	*/
	
	/*private PaymentService getpaymentService() {
		PaymentService paymentService = null;
		try{
			ApplicationContext appcontext = new ClassPathXmlApplicationContext("WebApplicationContext.xml");
			
			
		}catch (ApplicationContextException applicationContextException) {
			
			AuditServiceUtil.logApplicationException(applicationContextException,logger);
			
		}
		
		return paymentService;
	}*/
	//Rajat's code started

	//Rajat's code ended
	/**
	* This method is used to save the UI input into database except awaiting repair
	* @return String paymentSuccess
	*/
	public String savePayment(){
		String forwardString = "paymentSuccess";
		updateCanonical();
		ngphCanonical.setMsgRef(NGPHUtil.generateUUID());
		ngphCanonical.setMsgStatus("17");
		try {
		
			paymentService = (PaymentService)ApplicationContextProvider.getBean("paymentService");
			authStpService = (AuthStpService)ApplicationContextProvider.getBean("authStpService");
			paymentService.savePayment(ngphCanonical);
			authStpService.execute(ngphCanonical.getMsgRef());
		
		} catch (NGPHException ngphException) {
			/**
			 * log the information when logger is in debug mode 
			 * 
			 */
			if(logger.isDebugEnabled()){
				logger.debug(ConstantUtil.DATA_ACCESS_PROBLEM,ngphException);
			}
			/**
			 * log the information when logger is in info mode 
			 * 
			 */
			if(logger.isInfoEnabled()){
				logger.info(ConstantUtil.DATA_ACCESS_PROBLEM,ngphException);	
			}
			/**
			 * log the information when logger is in error mode 
			 * 
			 */
			if(logger.isEnabledFor(Level.ERROR)){
				logger.error(ConstantUtil.DATA_ACCESS_PROBLEM,ngphException);	
			}
			
		}catch(Exception exception){
			forwardString ="input";
			addActionError("Business Exception Occured");
			AuditServiceUtil.logException(exception,logger);
		}
	
		return forwardString;
	}
	/**
	* This method is used to set the message value data into action property
	* @return void
	*/
	private void updateCanonical(){
		String valueDate = getValueDate();
		
		SimpleDateFormat valueDateFormat = new SimpleDateFormat("yyyy-MM-dd"); 
		java.util.Date date = null;
		try {
			date = valueDateFormat.parse(valueDate);
		} catch (ParseException parseException) {
			
			/**
			 * log the information when logger is in debug mode 
			 * 
			 */
			if(logger.isDebugEnabled()){
				logger.debug(ConstantUtil.CANONICAL_VALUE_DATE_EXCEPTION, parseException);
			}
			/**
			 * log the information when logger is in info mode 
			 * 
			 */
			if(logger.isInfoEnabled()){
				logger.info(ConstantUtil.CANONICAL_VALUE_DATE_EXCEPTION, parseException);	
			}
			/**
			 * log the information when logger is in error mode 
			 * 
			 */
			if(logger.isEnabledFor(Level.ERROR)){
				logger.error(ConstantUtil.CANONICAL_VALUE_DATE_EXCEPTION, parseException);	
			}
			
		}
		
		java.sql.Timestamp valueDateTimeStamp = new java.sql.Timestamp(date.getTime()); 
		//ngphCanonical.setOrderingCustAccount("\\"+ngphCanonical.getOrderingCustAccount());
		ngphCanonical.setMsgValueDate(valueDateTimeStamp);
		
			
	}
	/**
	* This method is used to update the UI not displaying fields into DTO and
	* save the update NGPHCanonical into TA_MESSAGES_TX table
	* save the modified value into TA_MODIFIEDMESSAGES table
	* Save the Event logging
	* @return PaymentService paymentService
	*/
	
	public String saveRepairedPayment(){
		updateCanonical();
		NgphCanonical ngphCanonicalOriginal = (NgphCanonical)	this.getSession().get("OriginalNgPhCanonical");
		ngphCanonical.setMsgRef(ngphCanonicalOriginal.getMsgRef());
		ngphCanonical.setMsgStatus("17");
		ngphCanonical.setMsgPrevStatus("2");
		ngphCanonical.setLastModifiedUser("0015");
		ngphCanonical.setComments(getRepairComments());
		//ngphCanonical.setMsgBranch(ngphCanonicalOriginal.getMsgBranch());
		ngphCanonical.setMsgDept(ngphCanonicalOriginal.getMsgDept());
		ngphCanonical.setMsgDirection(ngphCanonicalOriginal.getMsgDirection());
		ngphCanonical.setMsgChannel(ngphCanonicalOriginal.getMsgChannel());
		ngphCanonical.setBeneficiaryType(ngphCanonicalOriginal.getBeneficiaryType());
		ngphCanonical.setClsDateTime(ngphCanonicalOriginal.getClsDateTime());
		ngphCanonical.setDrDateTime(ngphCanonicalOriginal.getDrDateTime());
		ngphCanonical.setCrDateTime(ngphCanonicalOriginal.getCrDateTime());
		
		
	
		//ngphCanonical.setMsgPurposeCode(ngphCanonicalOriginal.getMsgPurposeCode());
		ngphCanonical.setMsgRules(ngphCanonicalOriginal.getMsgRules());
		ngphCanonical.setRelUid(ngphCanonicalOriginal.getRelUid());
		ngphCanonical.setMsgReturnReference(ngphCanonicalOriginal.getMsgReturnReference());
		ngphCanonical.setCustAccount(ngphCanonicalOriginal.getCustAccount());
		ngphCanonical.setMsgBatchTime(ngphCanonicalOriginal.getMsgBatchTime());
		
	
		//ngphCanonical.setOrderingCustomerName(ngphCanonicalOriginal.getOrderingCustomerName());
		//ngphCanonical.setOrderingCustomerAddress(ngphCanonicalOriginal.getOrderingCustomerAddress());
		//ngphCanonical.setOrderingCustomerCtry(ngphCanonicalOriginal.getOrderingCustomerCtry());
		//ngphCanonical.setOrderingCustomerId(ngphCanonicalOriginal.getOrderingCustomerId());
		//ngphCanonical.setSvcLevelProperitary(ngphCanonicalOriginal.getSvcLevelProperitary());
		//ngphCanonical.setXchangeRate(ngphCanonicalOriginal.getXchangeRate());
		//ngphCanonical.setInstructedAmount(ngphCanonicalOriginal.getInstructedAmount());
		
		// modified
		
		//ngphCanonical.setMsgChnlType(ngphCanonicalOriginal.getMsgChnlType());
		
		//ngphCanonical.setBeneficiaryType(ngphCanonicalOriginal.getBeneficiaryType());
		//ngphCanonical.setBeneficiaryCustomerCtry(ngphCanonicalOriginal.getBeneficiaryCustomerCtry());
		//ngphCanonical.setBeneficiaryCustomerAddress(ngphCanonicalOriginal.getBeneficiaryCustomerAddress());
		//ngphCanonical.setBeneficiaryCustomerID(ngphCanonicalOriginal.getBeneficiaryCustomerID());
		//ngphCanonical.setClrgChannel(ngphCanonicalOriginal.getClrgChannel());
		//ngphCanonical.setCatgPurposeCode(ngphCanonicalOriginal.getCatgPurposeCode());
		
		
		/*ngphCanonical.setMsgChnlType(ngphCanonicalOriginal.getMsgChnlType());
		ngphCanonical.setUltimateDebtorName(ngphCanonicalOriginal.getUltimateDebtorName());
		ngphCanonical.setUltimateDebtorAddress(ngphCanonicalOriginal.getUltimateDebtorAddress());
		ngphCanonical.setUltimateDebtorID(ngphCanonicalOriginal.getUltimateDebtorID());
		ngphCanonical.setUltimateDebtorCtry(ngphCanonicalOriginal.getUltimateDebtorCtry());
		ngphCanonical.setUltimateDebtorCtctDtls(ngphCanonicalOriginal.getUltimateDebtorCtctDtls());
		
		ngphCanonical.setInitiatingPartyName(ngphCanonicalOriginal.getInitiatingPartyName());
		ngphCanonical.setInitiatingPartyAddress(ngphCanonicalOriginal.getInitiatingPartyAddress());
		ngphCanonical.setInitiatingPartyID(ngphCanonicalOriginal.getInitiatingPartyID());
		ngphCanonical.setInitiatingPartyCtry(ngphCanonicalOriginal.getInitiatingPartyCtry());
		ngphCanonical.setInitiatingPartyCtctDtls(ngphCanonicalOriginal.getInitiatingPartyCtctDtls());
		
		ngphCanonical.setOrderingCustomerCtry(ngphCanonicalOriginal.getOrderingCustomerCtry());
		ngphCanonical.setOrderingCustomerCtctDtls(ngphCanonicalOriginal.getOrderingCustomerCtctDtls());
		ngphCanonical.setSenderCorrespondent(ngphCanonicalOriginal.getSenderCorrespondent());
		ngphCanonical.setSenderCorrespondentAcct(ngphCanonicalOriginal.getSenderCorrespondentAcct());
		ngphCanonical.setReceiverCorrespondent(ngphCanonicalOriginal.getReceiverCorrespondent());
		ngphCanonical.setReceiverCorrespondentAcct(ngphCanonicalOriginal.getReceiverCorrespondentAcct());
		
		ngphCanonical.setThirdCorrespondent(ngphCanonicalOriginal.getThirdCorrespondent());
		ngphCanonical.setThirdCorrespondentAcct(ngphCanonicalOriginal.getThirdCorrespondentAcct());
		ngphCanonical.setUltimateCreditorCtry(ngphCanonicalOriginal.getUltimateCreditorCtry());
		ngphCanonical.setUltimateCreditorCtctDtls(ngphCanonicalOriginal.getUltimateCreditorCtctDtls());
		ngphCanonical.setUltimateCreditorAddress(ngphCanonicalOriginal.getUltimateCreditorAddress());
		ngphCanonical.setUltimateCreditorID(ngphCanonicalOriginal.getUltimateCreditorID());*/
		ngphCanonical.setServiceID(ngphCanonicalOriginal.getServiceID());
		ngphCanonical.setRemitInfoRef(ngphCanonicalOriginal.getRemitInfoRef());
		//ngphCanonical.setThirdCorrespondent(ngphCanonicalOriginal.getThirdCorrespondent());
		ngphCanonical.setOrderingInstitutionAcct(ngphCanonicalOriginal.getOrderingInstitutionAcct());
		ngphCanonical.setOrderingType(ngphCanonicalOriginal.getOrderingType());
		ngphCanonical.setOrderingInstitution(ngphCanonicalOriginal.getOrderingInstitution());
		ngphCanonical.setSenderBank(ngphCanonicalOriginal.getSenderBank());
		ngphCanonical.setCashpoolAdjstmntTime(ngphCanonicalOriginal.getCashpoolAdjstmntTime());
		ngphCanonical.setPymntAcceptedTime(ngphCanonicalOriginal.getPymntAcceptedTime());
		ngphCanonical.setSttlmntRejTime(ngphCanonicalOriginal.getSttlmntRejTime());
		ngphCanonical.setSttlmntFromTime(ngphCanonicalOriginal.getSttlmntFromTime());
		ngphCanonical.setSttlmntTillTime(ngphCanonicalOriginal.getSttlmntTillTime());
		
		
		ngphCanonical.setSndrSttlmntPriority(ngphCanonicalOriginal.getSndrSttlmntPriority());
		ngphCanonical.setCatgPurposeProperitary(ngphCanonicalOriginal.getCatgPurposeProperitary());
		ngphCanonical.setLclInstProperitary(ngphCanonicalOriginal.getLclInstProperitary());
		ngphCanonical.setSvcLevelProperitary(ngphCanonicalOriginal.getSvcLevelProperitary());
		//ngphCanonical.setLastModTime(ngphCanonicalOriginal.getLastModTime());
		ngphCanonical.setReceivedTime(ngphCanonicalOriginal.getReceivedTime());
		ngphCanonical.setSrcMsgType(ngphCanonicalOriginal.getSrcMsgType());
		ngphCanonical.setSrcMsgSubType(ngphCanonicalOriginal.getSrcMsgSubType());
		ngphCanonical.setMsgChannel(ngphCanonicalOriginal.getMsgChannel());
		ngphCanonical.setMsgHost(ngphCanonicalOriginal.getMsgHost());
		ngphCanonical.setGrpSeq(ngphCanonicalOriginal.getGrpSeq());
		ngphCanonical.setGrpMsgId(ngphCanonicalOriginal.getGrpMsgId());
	
		try {
			paymentService = (PaymentService)ApplicationContextProvider.getBean("paymentService");
			paymentService.savePayment(ngphCanonical);
			paymentService.savePaymentRepair(getRepairedMessageDto(ngphCanonicalOriginal));
			authStpService = (AuthStpService)ApplicationContextProvider.getBean("authStpService");
			auditServiceSave();
			authStpService.execute(ngphCanonical.getMsgRef());
		} catch (NGPHException ngphException) {
			AuditServiceUtil.logNgphException(ngphException,logger);
			
		}catch (Exception exception){
			AuditServiceUtil.logException(exception,logger);
		}
		return "success";
	}

	
	

	public NgphCanonical getModel() {
		return ngphCanonical;
	}
	
	/**
	* This method is used to do the validation
	* write now we are not using future need to call the validator service
	* @return void
	*/
	
	public void validate(){
		BigDecimal instructedAmount =ngphCanonical.getInstructedAmount();	
		if(instructedAmount == null){
			addFieldError("instructedAmount", getText("label.InstructedAmount")+" "+getText("error.Required"));	
			
		}
		String instructedCurrency = ngphCanonical.getInstructedCurrency();
		String msgCurrency = ngphCanonical.getMsgCurrency();
		if(!instructedCurrency.equals(msgCurrency)){
			if(ngphCanonical.getXchangeRate()== null){
				addFieldError("xchangeRate", getText("label.ExchangeRate")+" "+ getText("error.Required"));
			}
		}
		
	//BigDecimal msgAmount = ngphCanonical.getMsgAmount();
	////BigDecimal chargeAmount = ngphCanonical.getMsgAmount();
	//BigDecimal instructedAmount = ngphCanonical.getInstructedAmount();
	//BigDecimal xchangeRate = ngphCanonical.getXchangeRate();
	//Map fieldError = getFieldErrors();
	
	
	
	/*if(!fieldError.containsKey("msgAmount")){
		
		if(msgAmount == null){
		
			addFieldError("msgAmount", getText("label.Amount")+getText("error.Required"));
		}
		
	}*/
	/*if(!fieldError.containsKey("chargeAmount")){
			
			if(chargeAmount == null){
			
				addFieldError("chargeAmount", getText("label.ChargeAmount")+getText("error.Required"));
			}
			
			
			
		}*/
	/*if(msgAmount!= null){
		if(msgAmount.precision() > 22){
			addFieldError("msgAmount", getText("label.Amount")+getText("error.LengthAmount"));
		}
	}*/
	
	/*if(chargeAmount.precision() > 22){
		addFieldError("chargeAmount", getText("label.ChargeAmount")+getText("error.LengthAmount"));
	}*/
	//msgAmount.scale()
	
	//msgAmount.precision()
	
	/*if(!fieldError.containsKey("instructedAmount")){
		
		if(instructedAmount == null){
		
			addFieldError("instructedAmount", getText("label.Amount")+"is required");
		}
		
	}
	if(!fieldError.containsKey("xchangeRate")){
		
		if(xchangeRate == null){
		
			addFieldError("xchangeRate", getText("label.Amount")+"is required");
		}
		
	}*/
	
	
	}
	
	/**
	* This method is used to get the Application Context
	* @return PaymentService paymentService
	*/
	
/*	private ApplicationContext getApplicationContext() {
		ApplicationContext appcontext = null;
		try{
		 appcontext = new ClassPathXmlApplicationContext("WebApplicationContext.xml");
		}catch (ApplicationContextException applicationContextException) {
			AuditServiceUtil.logApplicationException(applicationContextException,logger);
		}
		return appcontext;
		
	}*/
	/**
	* This method is used for event logging when manual repair
	* @return void
	*/
	private void auditServiceSave(){
		auditService = (AuditService)ApplicationContextProvider.getBean("auditService");
	
	try {
		auditService.saveEventMaster(getEventMaterDto());
		auditService.saveEventAudit(getEventAuditDto());
	} catch (NGPHException ngphException) {
		AuditServiceUtil.logNgphException(ngphException,logger);
		
	}
	
	
	}

	
	

	/**
	* This method is used to get the Application Context
	* @return AuditService paymentService
	*/
	
	/*private AuditService getAuditService() {
	
		try{
			ApplicationContext appcontext = new ClassPathXmlApplicationContext("WebApplicationContext.xml");
			
			auditService = (AuditService)appcontext.getBean("AuditService");
		}catch (ApplicationContextException applicationContextException) {
			
		logger.debug(ConstantUtil.PAYMENT_ACTION+ applicationContextException);	
		}
		
		return auditService;
	}*/
	/**
	* This method is used to set the event details into EventMaster DTO
	* @return EventMaster eventMaster
	*/
	private EventMaster getEventMaterDto(){
		EventMaster eventMaster = new EventMaster();
		eventMaster.setEventId("NGPHEV00009");
		eventMaster.setEventDesc("Message with {0} is moved to {1} queue from Awaiting Repair queue by Users {2}");
		eventMaster.setEventAlertable(0);
		eventMaster.setEventSeverity("A");
		return eventMaster;
	}
	
	/**
	* This method is used to set the Audit details into EventAudit DTO
	* @return EventAudit eventAudit
	*/
	private EventAudit getEventAuditDto(){
		EventAudit eventAudit = new EventAudit();
		String msgReference = ngphCanonical.getMsgRef();
		eventAudit.setAuditEventId("NGPHEV00009");
		String userId = "0987AS";
		String[] extraInformation = {msgReference,getPaymentEntryStatus(),userId};
		
		eventAudit.setExtraInformation(extraInformation);
		String eventDescription = "Message with {0} is moved to {1} queue from Awaiting Repair queue by Users {2}";
		MessageFormat msgFormat = new MessageFormat(eventDescription);
		
		eventAudit.setAuditEventDesc(msgFormat.format(extraInformation));
		eventAudit.setAuditSource("0");
		eventAudit.setAuditBranch(ngphCanonical.getMsgBranch());
		eventAudit.setAuditDept(ngphCanonical.getMsgDept());
		eventAudit.setAuditMessageRef(msgReference);
		eventAudit.setAuditTransactionRef(ngphCanonical.getTxnReference());
		
		return eventAudit;
		
	}
	
	/**
	* This method is used to set the changed values and old values into stringBuffer
	* @param NgphCanonical ngphCanonicalOriginal
	* @return ModifiedMessagesDto modifiedMessagesDto
	*/
	@SuppressWarnings("unused")
	private ModifiedMessagesDto getRepairedMessageDto(NgphCanonical ngphCanonicalOriginal){
		ModifiedMessagesDto modifiedMessagesDto = new ModifiedMessagesDto();
		modifiedMessagesDto.setModifiedUser("OO00924");
	
		modifiedMessagesDto.setMsgsRef(ngphCanonicalOriginal.getMsgRef());
		
		StringBuffer modifiedStringBuffer = new StringBuffer();
		StringBuffer originalStringBuffer = new StringBuffer();
		String labelTemp;
		
		try{
			generateModifiedData(ngphCanonicalOriginal, modifiedStringBuffer,
					originalStringBuffer);
		}catch(NullPointerException nullPointerException){
			AuditServiceUtil.logNullPointerException(nullPointerException,logger);
		}
		
		//modifiedMessagesDto.setMsgsRepairId("092TX0");
		modifiedMessagesDto.setRepairComment(getRepairComments());
		
		
		if(!(originalStringBuffer.length()==0) && !(modifiedStringBuffer.length() == 0)){
			if(';' == originalStringBuffer.charAt(0) ){
				originalStringBuffer.deleteCharAt(0);
			}
			if(';' == modifiedStringBuffer.charAt(0)){
				modifiedStringBuffer.deleteCharAt(0);
			}
			modifiedMessagesDto.setChangedOriginalValues(originalStringBuffer.toString());
			modifiedMessagesDto.setChangedRepairedValues(modifiedStringBuffer.toString());
		}
		
		
		return modifiedMessagesDto;
	}
	/**
	* This method is used to set the changed values and old values into stringBuffer
	* @param NgphCanonical ngphCanonicalOriginal
	* @param StringBuffer modifiedStringBuffer
	* @param StringBuffer originalStringBuffer
	*/
	@SuppressWarnings("null")
	private void generateModifiedData(NgphCanonical ngphCanonicalOriginal,
			StringBuffer modifiedStringBuffer, StringBuffer originalStringBuffer) {
		String labelTemp;
		
		if(ngphCanonical.getTxnReference() != null &&  !"".equals(ngphCanonical.getTxnReference())){
			if(!ngphCanonical.getTxnReference().equals(ngphCanonicalOriginal.getTxnReference()))
			{
				labelTemp = getText("label.TransacRef");
				
				performModifiedField(ngphCanonicalOriginal.getTxnReference(), modifiedStringBuffer,
						originalStringBuffer,labelTemp,ngphCanonical.getTxnReference());
			
			}
		}
		
		if(ngphCanonical.getSvcLevelCode() != null &&  !"".equals(ngphCanonical.getSvcLevelCode())){
			if(!ngphCanonical.getSvcLevelCode().equals(ngphCanonicalOriginal.getSvcLevelCode()))
			{
				labelTemp = getText("label.ServiceLevelCode");
				
				performModifiedField(ngphCanonicalOriginal.getSvcLevelCode(), modifiedStringBuffer,
						originalStringBuffer,labelTemp,ngphCanonical.getSvcLevelCode());
			
			
			}
		}
		if(ngphCanonical.getMsgCurrency() != null && !"".equals(ngphCanonical.getMsgCurrency())){
			if(!ngphCanonical.getMsgCurrency().equals(ngphCanonicalOriginal.getMsgCurrency()))
			{
				labelTemp = getText("label.Currency");
				performModifiedField(ngphCanonicalOriginal.getMsgCurrency(), modifiedStringBuffer,
						originalStringBuffer,labelTemp,ngphCanonical.getMsgCurrency());
		
						
			}
		}
		if(ngphCanonical.getMsgAmount() != null ){
			if(!ngphCanonical.getMsgAmount().equals(ngphCanonicalOriginal.getMsgAmount()))
			{
				labelTemp = getText("label.Amount");
					modifiedStringBuffer.append(";").append(labelTemp).append(" ").append("=").append(" ").append(ngphCanonical.getMsgAmount());
					originalStringBuffer.append(";").append(labelTemp).append(" ").append("=").append(" ").append(ngphCanonicalOriginal.getMsgAmount());
				
			}
		}
		
		if(ngphCanonical.getOrderingCustomerName() != null && !"".equals(ngphCanonical.getOrderingCustomerName())){
			if(!ngphCanonical.getOrderingCustomerName().equals(ngphCanonicalOriginal.getOrderingCustomerName()))
			{
				labelTemp = getText("label.OrderingParty");
				performModifiedField(ngphCanonicalOriginal.getOrderingCustomerName(), modifiedStringBuffer,
						originalStringBuffer,labelTemp,ngphCanonical.getOrderingCustomerName());
					
			}
		}
		
		
	/*	if(ngphCanonicalOriginal.getOrderingCustAccount() != null && ngphCanonical.getOrderingCustAccount() !=""){
			if(!ngphCanonical.getOrderingCustAccount().equals(ngphCanonicalOriginal.getOrderingCustAccount()))
			{
				labelTemp = getText("label.OrderingCustomerAccount");
				performModifiedField(ngphCanonicalOriginal.getOrderingCustAccount(), modifiedStringBuffer,
						originalStringBuffer,labelTemp,ngphCanonical.getOrderingCustAccount());
				
			}	
		}*/
		
		if(ngphCanonical.getOrderingCustAccount() != null && !"".equals(ngphCanonical.getOrderingCustAccount())){
			if(!ngphCanonical.getOrderingCustAccount().equals(ngphCanonicalOriginal.getOrderingCustAccount()))
			{
				labelTemp = getText("label.OrderingCustomerAccount");
				performModifiedField(ngphCanonicalOriginal.getOrderingCustAccount(), modifiedStringBuffer,
						originalStringBuffer,labelTemp,ngphCanonical.getOrderingCustAccount());
			}	
		}
			
		if(ngphCanonical.getBeneficiaryCustomerName() != null && !"".equals(ngphCanonical.getBeneficiaryCustomerName())){

			if(!ngphCanonical.getBeneficiaryCustomerName().equals(ngphCanonicalOriginal.getBeneficiaryCustomerName()))
			{
				labelTemp = getText("label.BeneficiaryParty");
				
				performModifiedField(ngphCanonicalOriginal.getBeneficiaryCustomerName(), modifiedStringBuffer,
						originalStringBuffer,labelTemp,ngphCanonical.getBeneficiaryCustomerName());
				
			}	
		}
		
		if(ngphCanonical.getUltimateCreditorName() != null && !"".equals(ngphCanonical.getUltimateCreditorName())){
			if(!ngphCanonical.getUltimateCreditorName().equals(ngphCanonicalOriginal.getUltimateCreditorName()))
			{
				labelTemp = getText("label.UltimateCreditor");
				performModifiedField(ngphCanonicalOriginal.getUltimateCreditorName(), modifiedStringBuffer,
						originalStringBuffer,labelTemp,ngphCanonical.getUltimateCreditorName());
			
			}
		}
		
		
		if(ngphCanonical.getBeneficiaryCustAcct() != null && !"".equals(ngphCanonical.getBeneficiaryCustAcct())){
			if(!ngphCanonical.getBeneficiaryCustAcct().equals(ngphCanonicalOriginal.getBeneficiaryCustAcct()))
			{
				labelTemp = getText("label.BeneficiaryAccount");
				performModifiedField(ngphCanonicalOriginal.getBeneficiaryCustAcct(), modifiedStringBuffer,
						originalStringBuffer,labelTemp,ngphCanonical.getBeneficiaryCustAcct());
			}
		}
		if(ngphCanonical.getChargeBearer() != null && !"".equals(ngphCanonical.getChargeBearer())){
			if(!ngphCanonical.getChargeBearer().equals(ngphCanonicalOriginal.getChargeBearer()))
			{
				labelTemp = getText("label.ChargesBearer");
				performModifiedField(ngphCanonicalOriginal.getChargeBearer(), modifiedStringBuffer,
						originalStringBuffer,labelTemp,ngphCanonical.getChargeBearer());
			
			}
		}
		
		if(ngphCanonical.getRegulatoryReportCurrency() != null && !"".equals(ngphCanonical.getRegulatoryReportCurrency())){
			if(!ngphCanonical.getRegulatoryReportCurrency().equals(ngphCanonicalOriginal.getRegulatoryReportCurrency()))
			{
				labelTemp = getText("label.ChargeCurrency");
				performModifiedField(ngphCanonicalOriginal.getRegulatoryReportCurrency(), modifiedStringBuffer,
						originalStringBuffer,labelTemp,ngphCanonical.getRegulatoryReportCurrency());
			
			}
		}
		
		if(ngphCanonical.getCustTxnReference() != null && !"".equals(ngphCanonical.getCustTxnReference())){
			if(!ngphCanonical.getCustTxnReference().equals(ngphCanonicalOriginal.getCustTxnReference()))
			{
				labelTemp = getText("label.CustomerTransactionReference");
				performModifiedField(ngphCanonicalOriginal.getCustTxnReference(), modifiedStringBuffer,
						originalStringBuffer,labelTemp,ngphCanonical.getCustTxnReference());
			}
		}
		
		
		
		if(ngphCanonical.getSndrTxnId() != null && !"".equals(ngphCanonical.getSndrTxnId())){
			if(!ngphCanonical.getSndrTxnId().equals(ngphCanonicalOriginal.getSndrTxnId()))
			{
				labelTemp = getText("label.SenderTransactionReference");
				performModifiedField(ngphCanonicalOriginal.getSndrTxnId(), modifiedStringBuffer,
						originalStringBuffer,labelTemp,ngphCanonical.getSndrTxnId());
			
			}	
		}
		if(ngphCanonical.getClrgSysReference() != null && !"".equals(ngphCanonical.getClrgSysReference())){
			if(!ngphCanonical.getClrgSysReference().equals(ngphCanonicalOriginal.getClrgSysReference()))
			{
				labelTemp = getText("label.ClearingSystemReference");
				performModifiedField(ngphCanonicalOriginal.getClrgSysReference(), modifiedStringBuffer,
						originalStringBuffer,labelTemp,ngphCanonical.getClrgSysReference());
			
			}
		}
		if(ngphCanonical.getSndrPymtPriority() != null && !"".equals(ngphCanonical.getSndrPymtPriority())){
			if(!ngphCanonical.getSndrPymtPriority().equals(ngphCanonicalOriginal.getSndrPymtPriority()))
			{
				labelTemp = getText("label.SenderPriority");
				performModifiedField(ngphCanonicalOriginal.getSndrPymtPriority(), modifiedStringBuffer,
						originalStringBuffer,labelTemp,ngphCanonical.getSndrPymtPriority());
			
			}	
		}
		if(ngphCanonical.getClrgChannel() != null && !"".equals(ngphCanonical.getClrgChannel())){
				
				if(!ngphCanonical.getClrgChannel().equals(ngphCanonicalOriginal.getClrgChannel())){
					labelTemp = getText("label.ClearingChannel");
					performModifiedField(ngphCanonicalOriginal.getClrgChannel(), modifiedStringBuffer,
							originalStringBuffer,labelTemp,ngphCanonical.getClrgChannel());
				
					}
		}
		
		
		if(ngphCanonical.getInstructionsForNextAgtCode() != null && !"".equals(ngphCanonical.getInstructionsForNextAgtCode())){
			if(!ngphCanonical.getInstructionsForNextAgtCode().equals(ngphCanonicalOriginal.getInstructionsForNextAgtCode()))
			{
				labelTemp = getText("label.InstructionsCodeforAgent");
				
				performModifiedField(ngphCanonicalOriginal.getInstructionsForNextAgtCode(), modifiedStringBuffer,
						originalStringBuffer,labelTemp,ngphCanonical.getInstructionsForNextAgtCode());
					
			}
		}
				
		
		if(ngphCanonical.getInstructionsForNextAgtText() != null && !"".equals(ngphCanonical.getInstructionsForNextAgtText())){
			if(!ngphCanonical.getInstructionsForNextAgtText().equals(ngphCanonicalOriginal.getInstructionsForNextAgtText()))
			{
				labelTemp = getText("label.InstructionsforNextAgent");
				performModifiedField(ngphCanonicalOriginal.getInstructionsForNextAgtText(), modifiedStringBuffer,
						originalStringBuffer,labelTemp,ngphCanonical.getInstructionsForNextAgtText());
			
			}
		}
		
		if(ngphCanonical.getMsgPurposeCode() != null && !"".equals(ngphCanonical.getMsgPurposeCode())){
			if(!ngphCanonical.getMsgPurposeCode().equals(ngphCanonicalOriginal.getMsgPurposeCode()))
			{
				labelTemp = getText("label.PurposeCode");
				performModifiedField(ngphCanonicalOriginal.getMsgPurposeCode(), modifiedStringBuffer,
						originalStringBuffer,labelTemp,ngphCanonical.getMsgPurposeCode());
				
			}
		}
		
		if(ngphCanonical.getMsgPurposeText() != null && !"".equals(ngphCanonical.getMsgPurposeText())){
			if(!ngphCanonical.getMsgPurposeText().equals(ngphCanonicalOriginal.getMsgPurposeText()))
			{
				labelTemp = getText("label.Purpose");
				performModifiedField(ngphCanonicalOriginal.getMsgPurposeText(), modifiedStringBuffer,
						originalStringBuffer,labelTemp,ngphCanonical.getMsgPurposeText());
			}	
		}
		if(ngphCanonical.getRegulatoryBankCode() != null && !"".equals(ngphCanonical.getRegulatoryBankCode())){
			if(!ngphCanonical.getRegulatoryBankCode().equals(ngphCanonicalOriginal.getRegulatoryBankCode()))
			{
				labelTemp = getText("label.RegulatoryBankcode");
				performModifiedField(ngphCanonicalOriginal.getRegulatoryBankCode(), modifiedStringBuffer,
						originalStringBuffer,labelTemp,ngphCanonical.getRegulatoryBankCode());
			}
		}
	
			
		if(ngphCanonical.getRegulatoryReportAmount() != null  && !ngphCanonical.getRegulatoryReportAmount().equals("0")){
			labelTemp = getText("label.RegulatoryReportingAmount");
			if(!ngphCanonical.getRegulatoryReportAmount().equals(ngphCanonicalOriginal.getRegulatoryReportAmount())){
				modifiedStringBuffer.append(";").append(labelTemp).append(" ").append("=").append(" ").append(ngphCanonical.getRegulatoryReportAmount());
				originalStringBuffer.append(";").append(labelTemp).append(" ").append("=").append(" ").append(ngphCanonicalOriginal.getRegulatoryReportAmount());				
			}
			
		}
		/*if(!ngphCanonical.getRegulatoryReportAmount().equals(ngphCanonicalOriginal.getRegulatoryReportAmount()))
		{
			
		
		}*/
		if(ngphCanonical.getRegulatoryInformation() != null && !"".equals(ngphCanonical.getRegulatoryInformation())){
			if(!ngphCanonical.getRegulatoryInformation().equals(ngphCanonicalOriginal.getRegulatoryInformation()))
			{
				labelTemp = getText("label.RegulatoryInformation");
				performModifiedField(ngphCanonicalOriginal.getRegulatoryInformation(), modifiedStringBuffer,
						originalStringBuffer,labelTemp,ngphCanonical.getRegulatoryInformation());
				
			}
		}
		if(ngphCanonical.getInitiatorRemitReference() != null && !"".equals(ngphCanonical.getInitiatorRemitReference())){
			if(!ngphCanonical.getInitiatorRemitReference().equals(ngphCanonicalOriginal.getInitiatorRemitReference()))
			{
				labelTemp = getText("label.RemittanceIdentification");
				performModifiedField(ngphCanonicalOriginal.getInitiatorRemitReference(), modifiedStringBuffer,
						originalStringBuffer,labelTemp,ngphCanonical.getInitiatorRemitReference());
				
			}	
		}
	
		if(ngphCanonical.getInitiatorRemitAdviceMethod() != null && !"".equals(ngphCanonical.getInitiatorRemitAdviceMethod())){
			if(!ngphCanonical.getInitiatorRemitAdviceMethod().equals(ngphCanonicalOriginal.getInitiatorRemitAdviceMethod()))
			{
				labelTemp = getText("label.RemittanceLocationMethod");
				performModifiedField(ngphCanonicalOriginal.getInitiatorRemitAdviceMethod(), modifiedStringBuffer,
						originalStringBuffer,labelTemp,ngphCanonical.getInitiatorRemitAdviceMethod());
				
			}	
		}
		if(ngphCanonical.getRemitInfoEmail() != null && !"".equals(ngphCanonical.getRemitInfoEmail())){
			if(!ngphCanonical.getRemitInfoEmail().equals(ngphCanonicalOriginal.getRemitInfoEmail()))
			{
				labelTemp = getText("label.RemittanceLocationeMail");
				performModifiedField(ngphCanonicalOriginal.getRemitInfoEmail(), modifiedStringBuffer,
						originalStringBuffer,labelTemp,ngphCanonical.getRemitInfoEmail());
				
			}	
		}
		if(ngphCanonical.getRemitReceivingPartyName() != null && !"".equals(ngphCanonical.getRemitReceivingPartyName())){
			if(!ngphCanonical.getRemitReceivingPartyName().equals(ngphCanonicalOriginal.getRemitReceivingPartyName()))
			{
				labelTemp = getText("label.RemittanceReceiverName");
				performModifiedField(ngphCanonicalOriginal.getRemitReceivingPartyName(), modifiedStringBuffer,
						originalStringBuffer,labelTemp,ngphCanonical.getRemitReceivingPartyName());
				
			}
		}
		if(ngphCanonical.getRemitReceivingPartyAddress() != null && !"".equals(ngphCanonical.getRemitReceivingPartyAddress())){
			if(!ngphCanonical.getRemitReceivingPartyAddress().equals(ngphCanonicalOriginal.getRemitReceivingPartyAddress()))
			{
				labelTemp = getText("label.RemittanceReceiverAddress");
				performModifiedField(ngphCanonicalOriginal.getRemitReceivingPartyAddress(), modifiedStringBuffer,
						originalStringBuffer,labelTemp,ngphCanonical.getRemitReceivingPartyAddress());
				
			}
		}
		if(ngphCanonical.getMsgReturnReference() != null && !"".equals(ngphCanonical.getMsgReturnReference())){
			if(!ngphCanonical.getMsgReturnReference().equals(ngphCanonicalOriginal.getMsgReturnReference()))
			{
				labelTemp = getText("label.ReturnReference");
				
				performModifiedField(ngphCanonicalOriginal.getMsgReturnReference(), modifiedStringBuffer,
						originalStringBuffer,labelTemp,ngphCanonical.getMsgReturnReference());
		
			}
		}
		if(ngphCanonical.getSrcMsgSubType() != null && !"".equals(ngphCanonical.getSrcMsgSubType())){
			if(!ngphCanonical.getSrcMsgSubType().equals(ngphCanonicalOriginal.getSrcMsgSubType()))
			{
				labelTemp = getText("label.ReturnReference");
				performModifiedField(ngphCanonicalOriginal.getSrcMsgSubType(), modifiedStringBuffer,
						originalStringBuffer,labelTemp,ngphCanonical.getSrcMsgSubType());
			
			}
		}
		if(ngphCanonical.getMsgBranch() != null && !"".equals(ngphCanonical.getMsgBranch())){
			if(!ngphCanonical.getMsgBranch().equals(ngphCanonicalOriginal.getMsgBranch()))
			{
				labelTemp = getText("label.MsgBranch");
				performModifiedField(ngphCanonicalOriginal.getMsgBranch(), modifiedStringBuffer,
						originalStringBuffer,labelTemp,ngphCanonical.getMsgBranch());
				
			}	
		}
		if(ngphCanonical.getRelReference() != null && !"".equals(ngphCanonical.getRelReference())){
			if(!ngphCanonical.getRelReference().equals(ngphCanonicalOriginal.getRelReference()))
			{
				labelTemp = getText("label.RelatedReference");
				performModifiedField(ngphCanonicalOriginal.getRelReference(), modifiedStringBuffer,
						originalStringBuffer,labelTemp,ngphCanonical.getRelReference());
			
			}	
		}
		if(ngphCanonical.getOrderingType() != null && !"".equals(ngphCanonical.getOrderingType())){
			if(!ngphCanonical.getOrderingType().equals(ngphCanonicalOriginal.getOrderingType()))
			{
				labelTemp = getText("label.TypeofOrderingParty");
					modifiedStringBuffer.append(";").append(labelTemp).append(" ").append("=").append(" ").append(ngphCanonical.getOrderingType());
					originalStringBuffer.append(";").append(labelTemp).append(" ").append("=").append(" ").append(ngphCanonicalOriginal.getOrderingType());				
			}
		}
		if(ngphCanonical.getBeneficiaryType() != null && !"".equals(ngphCanonical.getBeneficiaryType())){
			if(!ngphCanonical.getBeneficiaryType().equals(ngphCanonicalOriginal.getBeneficiaryType()))
			{
				labelTemp = getText("label.TypeofBeneficiaryParty");
				performModifiedField(ngphCanonicalOriginal.getBeneficiaryType(), modifiedStringBuffer,
						originalStringBuffer,labelTemp,ngphCanonical.getBeneficiaryType());
				
			}
		}
		if(ngphCanonical.getSvcLevelProperitary() != null && !"".equals(ngphCanonical.getSvcLevelProperitary())){
			if(!ngphCanonical.getSvcLevelProperitary().equals(ngphCanonicalOriginal.getSvcLevelProperitary()))
			{
				labelTemp = getText("label.ServiceLevelInformation");
				performModifiedField(ngphCanonicalOriginal.getSvcLevelProperitary(), modifiedStringBuffer,
						originalStringBuffer,labelTemp,ngphCanonical.getSvcLevelProperitary());
			
			}	
			}
		if(ngphCanonical.getLclInstCode() != null && !"".equals(ngphCanonical.getLclInstCode())){
			if(!ngphCanonical.getLclInstCode().equals(ngphCanonicalOriginal.getLclInstCode()))
			{
				labelTemp = getText("label.LocalInstrumentCode");
				performModifiedField(ngphCanonicalOriginal.getLclInstCode(), modifiedStringBuffer,
						originalStringBuffer,labelTemp,ngphCanonical.getLclInstCode());
				
			}
		}
		if(ngphCanonical.getLclInstProperitary() != null && !"".equals(ngphCanonical.getLclInstProperitary())){
			if(!ngphCanonical.getLclInstProperitary().equals(ngphCanonicalOriginal.getLclInstProperitary()))
			{
				labelTemp = getText("label.LocalInstrumentInformation");
				performModifiedField(ngphCanonicalOriginal.getLclInstProperitary(), modifiedStringBuffer,
						originalStringBuffer,labelTemp,ngphCanonical.getLclInstProperitary());
			
			}	
		}
		if(ngphCanonical.getCatgPurposeCode() != null && !"".equals(ngphCanonical.getCatgPurposeCode())){
			if(!ngphCanonical.getCatgPurposeCode().equals(ngphCanonicalOriginal.getCatgPurposeCode()))
			{
				labelTemp = getText("label.CategoryPurposeCode");
				performModifiedField(ngphCanonicalOriginal.getCatgPurposeCode(), modifiedStringBuffer,
						originalStringBuffer,labelTemp,ngphCanonical.getCatgPurposeCode());
				
			}
		}
		if(ngphCanonical.getCatgPurposeProperitary() != null && !"".equals(ngphCanonical.getCatgPurposeProperitary())){
			if(!ngphCanonical.getCatgPurposeProperitary().equals(ngphCanonicalOriginal.getCatgPurposeProperitary()))
			{
				labelTemp = getText("label.CategoryPurposeInformation");
				performModifiedField(ngphCanonicalOriginal.getCatgPurposeProperitary(), modifiedStringBuffer,
						originalStringBuffer,labelTemp,ngphCanonical.getCatgPurposeProperitary());
		
			}
		}
		if(ngphCanonical.getSndrSttlmntPriority() != null && !"".equals(ngphCanonical.getSndrSttlmntPriority())){
			if(!ngphCanonical.getSndrSttlmntPriority().equals(ngphCanonicalOriginal.getSndrSttlmntPriority()))
			{
				labelTemp = getText("label.SettlementPriority");
				
				performModifiedField(ngphCanonicalOriginal.getSndrSttlmntPriority(), modifiedStringBuffer,
						originalStringBuffer,labelTemp,ngphCanonical.getSndrSttlmntPriority());
			
			}
		}
		
		if(ngphCanonical.getInstructedCurrency() != null && !"".equals(ngphCanonical.getInstructedCurrency())){
			if(!ngphCanonical.getInstructedCurrency().equals(ngphCanonicalOriginal.getInstructedCurrency()))
			{
				labelTemp = getText("label.InstructedCurrency");
				performModifiedField(ngphCanonicalOriginal.getInstructedCurrency(), modifiedStringBuffer,
						originalStringBuffer,labelTemp,ngphCanonical.getInstructedCurrency());
				
			}	
		}
		
		if(ngphCanonical.getInstructedAmount() != null ){
			if(!ngphCanonical.getInstructedAmount().equals(ngphCanonicalOriginal.getInstructedAmount()))
			{
				labelTemp = getText("label.InstructedAmount");
				
				if(modifiedStringBuffer != null){
					modifiedStringBuffer.append(";").append(labelTemp).append(" ").append("=").append(" ").append(ngphCanonical.getInstructedAmount());
					originalStringBuffer.append(";").append(labelTemp).append(" ").append("=").append(" ").append(ngphCanonicalOriginal.getInstructedAmount());				
				}else{
					modifiedStringBuffer.append(labelTemp).append(" ").append("=").append(" ").append(ngphCanonical.getInstructedAmount());
					originalStringBuffer.append(labelTemp).append(" ").append("=").append(" ").append(ngphCanonicalOriginal.getInstructedAmount());				
				}		
			
			}
		}
		if(ngphCanonical.getXchangeRate() != null){
			if(!ngphCanonical.getXchangeRate().equals(ngphCanonicalOriginal.getXchangeRate()))
			{
				labelTemp = getText("label.ExchangeRate");
				if(modifiedStringBuffer != null){
					modifiedStringBuffer.append(";").append(labelTemp).append(" ").append("=").append(" ").append(ngphCanonical.getXchangeRate());
					originalStringBuffer.append(";").append(labelTemp).append(" ").append("=").append(" ").append(ngphCanonicalOriginal.getXchangeRate());				
				}else{
					modifiedStringBuffer.append(labelTemp).append(" ").append("=").append(" ").append(ngphCanonical.getXchangeRate());
					originalStringBuffer.append(labelTemp).append(" ").append("=").append(" ").append(ngphCanonicalOriginal.getXchangeRate());				
				}	
			
			}
		}
	
		
		if(ngphCanonical.getPrevInstructingAgentAcct() != null && !"".equals(ngphCanonical.getPrevInstructingAgentAcct())){
			if(!ngphCanonical.getPrevInstructingAgentAcct().equals(ngphCanonicalOriginal.getPrevInstructingAgentAcct()))
			{
				labelTemp = getText("label.PreviousInstructingBankAccount");
				performModifiedField(ngphCanonicalOriginal.getPrevInstructingAgentAcct(), modifiedStringBuffer,
						originalStringBuffer,labelTemp,ngphCanonical.getPrevInstructingAgentAcct());
			
			}	
		}
		if(ngphCanonical.getSenderBank() != null && !"".equals(ngphCanonical.getSenderBank())){
			if(!ngphCanonical.getSenderBank().equals(ngphCanonicalOriginal.getSenderBank()))
			{
				labelTemp = getText("label.SenderBankCode");
				performModifiedField(ngphCanonicalOriginal.getSenderBank(), modifiedStringBuffer,
						originalStringBuffer,labelTemp,ngphCanonical.getSenderBank());
					
			
			}
		}
			
			
			
		if(ngphCanonical.getReceiverBank() != null && !"".equals(ngphCanonical.getReceiverBank())){
			if(!ngphCanonical.getReceiverBank().equals(ngphCanonicalOriginal.getReceiverBank()))
			{
				labelTemp = getText("label.ReceiverBankCode");
				performModifiedField(ngphCanonicalOriginal.getReceiverBank(), modifiedStringBuffer,
						originalStringBuffer,labelTemp,ngphCanonical.getReceiverBank());
			
			}
		}
		if(ngphCanonical.getIntermediary1Bank() != null && !"".equals(ngphCanonical.getIntermediary1Bank())){
			if(!ngphCanonical.getIntermediary1Bank().equals(ngphCanonicalOriginal.getIntermediary1Bank()))
			{
				labelTemp = getText("label.Intermediary1BankCode");
				performModifiedField(ngphCanonicalOriginal.getIntermediary1Bank(), modifiedStringBuffer,
						originalStringBuffer,labelTemp,ngphCanonical.getIntermediary1Bank());
				
			}	
		}
		if(ngphCanonical.getIntermediary1AgentAcct() != null && !"".equals(ngphCanonical.getIntermediary1AgentAcct())){
			if(!ngphCanonical.getIntermediary1AgentAcct().equals(ngphCanonicalOriginal.getIntermediary1AgentAcct()))
			{
				labelTemp = getText("label.Intermediary1BankAccount");
				performModifiedField(ngphCanonicalOriginal.getIntermediary1AgentAcct(), modifiedStringBuffer,
						originalStringBuffer,labelTemp,ngphCanonical.getIntermediary1AgentAcct());
				
			}
		}
		if(ngphCanonical.getIntermediary2Bank() != null && !"".equals(ngphCanonical.getIntermediary2Bank())){
			if(!ngphCanonical.getIntermediary2Bank().equals(ngphCanonicalOriginal.getIntermediary2Bank()))
			{
				labelTemp = getText("label.Intermediary2BankAccount");
				performModifiedField(ngphCanonicalOriginal.getIntermediary2Bank(), modifiedStringBuffer,
						originalStringBuffer,labelTemp,ngphCanonical.getIntermediary2Bank());
				
			}	
		}
		if(ngphCanonical.getIntermediary2AgentAcct() != null && !"".equals(ngphCanonical.getIntermediary2AgentAcct())){
			if(!ngphCanonical.getIntermediary2AgentAcct().equals(ngphCanonicalOriginal.getIntermediary2AgentAcct()))
			{
				labelTemp = getText("label.Intermediary2BankCode");
				
				performModifiedField(ngphCanonicalOriginal.getIntermediary2AgentAcct(), modifiedStringBuffer,
						originalStringBuffer,labelTemp,ngphCanonical.getIntermediary2AgentAcct());
			
			}
		}
		if(ngphCanonical.getIntermediary3Bank() != null && !"".equals(ngphCanonical.getIntermediary3Bank())){
			if(!ngphCanonical.getIntermediary3Bank().equals(ngphCanonicalOriginal.getIntermediary3Bank()))
			{
				labelTemp = getText("label.Intermediary3BankCode");
				if(modifiedStringBuffer != null){
					modifiedStringBuffer.append(";").append(labelTemp).append(" ").append("=").append(" ").append(ngphCanonical.getIntermediary3Bank());
					originalStringBuffer.append(";").append(labelTemp).append(" ").append("=").append(" ").append(ngphCanonicalOriginal.getIntermediary3Bank());				
				}else{
					modifiedStringBuffer.append(labelTemp).append(" ").append("=").append(" ").append(ngphCanonical.getIntermediary3Bank());
					originalStringBuffer.append(labelTemp).append(" ").append("=").append(" ").append(ngphCanonicalOriginal.getIntermediary3Bank());				
				}		
			
			}	
		}
		if(ngphCanonical.getIntermediary3AgentAcct() != null && !"".equals(ngphCanonical.getIntermediary3AgentAcct())){
			if(!ngphCanonical.getIntermediary3AgentAcct().equals(ngphCanonicalOriginal.getIntermediary3AgentAcct()))
			{
				labelTemp = getText("label.Intermediary3BankAccount");
				performModifiedField(ngphCanonicalOriginal.getIntermediary3AgentAcct(), modifiedStringBuffer,
						originalStringBuffer,labelTemp,ngphCanonical.getIntermediary3AgentAcct());
				
			}
		}
		
		/*if(!ngphCanonical.getUltimateDebtor().equals(ngphCanonicalOriginal.getIntermediary3AgentAcct()))
		{
			
		
		}*/
		
		/*if(!ngphCanonical.getInitiatingParty().equals(ngphCanonicalOriginal.getIntermediary3AgentAcct()))
		{
			
		
		}*/
		if(ngphCanonical.getOrderingInstitution() != null && !"".equals(ngphCanonical.getOrderingInstitution())){
			if(!ngphCanonical.getOrderingInstitution().equals(ngphCanonicalOriginal.getOrderingInstitution()))
			{

				labelTemp = getText("label.OrderingInstitution");
				
				performModifiedField(ngphCanonicalOriginal.getOrderingInstitution(), modifiedStringBuffer,
						originalStringBuffer,labelTemp,ngphCanonical.getOrderingInstitution());
			
			
			}	
		}
		if(ngphCanonical.getOrderingInstitutionAcct() != null && !"".equals(ngphCanonical.getOrderingInstitutionAcct())){
			if(!ngphCanonical.getOrderingInstitutionAcct().equals(ngphCanonicalOriginal.getOrderingInstitutionAcct()))
			{

				labelTemp = getText("label.OrderingInstitutionAccount");
				performModifiedField(ngphCanonicalOriginal.getOrderingInstitutionAcct(), modifiedStringBuffer,
						originalStringBuffer,labelTemp,ngphCanonical.getOrderingInstitutionAcct());
			
			}
		}
		if(ngphCanonical.getInstructionsForCrdtrAgtCode() != null && !"".equals(ngphCanonical.getInstructionsForCrdtrAgtCode())){
			if(!ngphCanonical.getInstructionsForCrdtrAgtCode().equals(ngphCanonicalOriginal.getInstructionsForCrdtrAgtCode()))
			{
				labelTemp = getText("label.InstructionCodeforCreditorAgent");
				
				performModifiedField(ngphCanonicalOriginal.getInstructionsForCrdtrAgtCode(), modifiedStringBuffer,
						originalStringBuffer,labelTemp,ngphCanonical.getInstructionsForCrdtrAgtCode());
			
			}	
		}
		// modified
		if(ngphCanonical.getBeneficiaryCustomerCtry() != null && !"".equals(ngphCanonical.getBeneficiaryCustomerCtry())){
			if(!ngphCanonical.getBeneficiaryCustomerCtry().equals(ngphCanonicalOriginal.getBeneficiaryCustomerCtry()))
			{
				labelTemp = getText("label.beneficiaryCustomerCtry");
				
				performModifiedField(ngphCanonicalOriginal.getBeneficiaryCustomerCtry(), modifiedStringBuffer,
						originalStringBuffer,labelTemp,ngphCanonical.getBeneficiaryCustomerCtry());
			
			}	
		}
		
		if(ngphCanonical.getBeneficiaryCustomerAddress() != null && !"".equals(ngphCanonical.getBeneficiaryCustomerAddress())){
			if(!ngphCanonical.getBeneficiaryCustomerAddress().equals(ngphCanonicalOriginal.getBeneficiaryCustomerAddress()))
			{
				labelTemp = getText("label.beneficiaryCustomerAddress");
				
				performModifiedField(ngphCanonicalOriginal.getBeneficiaryCustomerAddress(), modifiedStringBuffer,
						originalStringBuffer,labelTemp,ngphCanonical.getBeneficiaryCustomerAddress());
			
			}	
		}
		
		if(ngphCanonical.getBeneficiaryCustomerID() != null && !"".equals(ngphCanonical.getBeneficiaryCustomerID())){
			if(!ngphCanonical.getBeneficiaryCustomerID().equals(ngphCanonicalOriginal.getBeneficiaryCustomerID()))
			{
				labelTemp = getText("label.beneficiaryCustomerID");
				
				performModifiedField(ngphCanonicalOriginal.getBeneficiaryCustomerID(), modifiedStringBuffer,
						originalStringBuffer,labelTemp,ngphCanonical.getBeneficiaryCustomerID());
			
			}	
		}
		if(ngphCanonical.getMsgChnlType() != null && !"".equals(ngphCanonical.getMsgChnlType())){
			if(!ngphCanonical.getMsgChnlType().equals(ngphCanonicalOriginal.getMsgChnlType()))
			{
				labelTemp = getText("label.msgChnlType");
				
				performModifiedField(ngphCanonicalOriginal.getMsgChnlType(), modifiedStringBuffer,
						originalStringBuffer,labelTemp,ngphCanonical.getMsgChnlType());
			
			}	
		}
		if(ngphCanonical.getUltimateDebtorName() != null && !"".equals(ngphCanonical.getUltimateDebtorName())){
			if(!ngphCanonical.getUltimateDebtorName().equals(ngphCanonicalOriginal.getUltimateDebtorName()))
			{
				labelTemp = getText("label.ultimateDebtorName");
				
				performModifiedField(ngphCanonicalOriginal.getUltimateDebtorName(), modifiedStringBuffer,
						originalStringBuffer,labelTemp,ngphCanonical.getUltimateDebtorName());
			
			}	
		}
		
		if(ngphCanonical.getUltimateDebtorAddress() != null && !"".equals(ngphCanonical.getUltimateDebtorAddress())){
			if(!ngphCanonical.getUltimateDebtorAddress().equals(ngphCanonicalOriginal.getUltimateDebtorAddress()))
			{
				labelTemp = getText("label.ultimateDebtorAddress");
				
				performModifiedField(ngphCanonicalOriginal.getUltimateDebtorAddress(), modifiedStringBuffer,
						originalStringBuffer,labelTemp,ngphCanonical.getUltimateDebtorAddress());
			
			}	
		}
		if(ngphCanonical.getUltimateDebtorAddress() != null && !"".equals(ngphCanonical.getUltimateDebtorAddress())){
			if(!ngphCanonical.getUltimateDebtorAddress().equals(ngphCanonicalOriginal.getUltimateDebtorAddress()))
			{
				labelTemp = getText("label.ultimateDebtorAddress");
				
				performModifiedField(ngphCanonicalOriginal.getUltimateDebtorAddress(), modifiedStringBuffer,
						originalStringBuffer,labelTemp,ngphCanonical.getUltimateDebtorAddress());
			
			}	
		}
		if(ngphCanonical.getUltimateDebtorID() != null && !"".equals(ngphCanonical.getUltimateDebtorID())){
			if(!ngphCanonical.getUltimateDebtorID().equals(ngphCanonicalOriginal.getUltimateDebtorID()))
			{
				labelTemp = getText("label.ultimateDebtorID");
				
				performModifiedField(ngphCanonicalOriginal.getUltimateDebtorID(), modifiedStringBuffer,
						originalStringBuffer,labelTemp,ngphCanonical.getUltimateDebtorID());
			
			}	
		}
		
		if(ngphCanonical.getUltimateDebtorCtry() != null && !"".equals(ngphCanonical.getUltimateDebtorCtry())){
			if(!ngphCanonical.getUltimateDebtorCtry().equals(ngphCanonicalOriginal.getUltimateDebtorCtry()))
			{
				labelTemp = getText("label.ultimateDebtorCtry");
				
				performModifiedField(ngphCanonicalOriginal.getUltimateDebtorCtry(), modifiedStringBuffer,
						originalStringBuffer,labelTemp,ngphCanonical.getUltimateDebtorCtry());
			
			}	
		}
		if(ngphCanonical.getUltimateDebtorCtctDtls() != null && !"".equals(ngphCanonical.getUltimateDebtorCtctDtls())){
			if(!ngphCanonical.getUltimateDebtorCtctDtls().equals(ngphCanonicalOriginal.getUltimateDebtorCtctDtls()))
			{
				labelTemp = getText("label.ultimateDebtorCtctDtls");
				
				performModifiedField(ngphCanonicalOriginal.getUltimateDebtorCtctDtls(), modifiedStringBuffer,
						originalStringBuffer,labelTemp,ngphCanonical.getUltimateDebtorCtctDtls());
			
			}	
		}
		if(ngphCanonical.getInitiatingPartyName() != null && !"".equals(ngphCanonical.getInitiatingPartyName())){
			if(!ngphCanonical.getInitiatingPartyName().equals(ngphCanonicalOriginal.getInitiatingPartyName()))
			{
				labelTemp = getText("label.initiatingPartyName");
				
				performModifiedField(ngphCanonicalOriginal.getInitiatingPartyName(), modifiedStringBuffer,
						originalStringBuffer,labelTemp,ngphCanonical.getInitiatingPartyName());
			
			}	
		}
		if(ngphCanonical.getInitiatingPartyAddress() != null && !"".equals(ngphCanonical.getInitiatingPartyAddress())){
			if(!ngphCanonical.getInitiatingPartyAddress().equals(ngphCanonicalOriginal.getInitiatingPartyAddress()))
			{
				labelTemp = getText("label.initiatingPartyAddress");
				
				performModifiedField(ngphCanonicalOriginal.getInitiatingPartyAddress(), modifiedStringBuffer,
						originalStringBuffer,labelTemp,ngphCanonical.getInitiatingPartyAddress());
			
			}	
		}
		if(ngphCanonical.getInitiatingPartyID() != null && !"".equals(ngphCanonical.getInitiatingPartyID())){
			if(!ngphCanonical.getInitiatingPartyID().equals(ngphCanonicalOriginal.getInitiatingPartyID()))
			{
				labelTemp = getText("label.initiatingPartyID");
				
				performModifiedField(ngphCanonicalOriginal.getInitiatingPartyID(), modifiedStringBuffer,
						originalStringBuffer,labelTemp,ngphCanonical.getInitiatingPartyID());
			
			}	
		}
		
		if(ngphCanonical.getInitiatingPartyID() != null && !"".equals(ngphCanonical.getInitiatingPartyID())){
			if(!ngphCanonical.getInitiatingPartyID().equals(ngphCanonicalOriginal.getInitiatingPartyID()))
			{
				labelTemp = getText("label.initiatingPartyID");
				
				performModifiedField(ngphCanonicalOriginal.getInitiatingPartyID(), modifiedStringBuffer,
						originalStringBuffer,labelTemp,ngphCanonical.getInitiatingPartyID());
			
			}	
		}
		if(ngphCanonical.getInitiatingPartyCtry() != null && !"".equals(ngphCanonical.getInitiatingPartyCtry())){
			if(!ngphCanonical.getInitiatingPartyCtry().equals(ngphCanonicalOriginal.getInitiatingPartyCtry()))
			{
				labelTemp = getText("label.initiatingPartyCtry");
				
				performModifiedField(ngphCanonicalOriginal.getInitiatingPartyCtry(), modifiedStringBuffer,
						originalStringBuffer,labelTemp,ngphCanonical.getInitiatingPartyCtry());
			
			}	
		}
		if(ngphCanonical.getInitiatingPartyCtctDtls() != null && !"".equals(ngphCanonical.getInitiatingPartyCtctDtls())){
			if(!ngphCanonical.getInitiatingPartyCtctDtls().equals(ngphCanonicalOriginal.getInitiatingPartyCtctDtls()))
			{
				labelTemp = getText("label.initiatingPartyCtctDtls");
				
				performModifiedField(ngphCanonicalOriginal.getInitiatingPartyCtctDtls(), modifiedStringBuffer,
						originalStringBuffer,labelTemp,ngphCanonical.getInitiatingPartyCtctDtls());
			
			}	
		}
		if(ngphCanonical.getOrderingCustomerAddress() != null && !"".equals(ngphCanonical.getOrderingCustomerAddress())){
			if(!ngphCanonical.getOrderingCustomerAddress().equals(ngphCanonicalOriginal.getOrderingCustomerAddress()))
			{
				labelTemp = getText("label.orderingCustomerAddress");
				
				performModifiedField(ngphCanonicalOriginal.getOrderingCustomerAddress(), modifiedStringBuffer,
						originalStringBuffer,labelTemp,ngphCanonical.getOrderingCustomerAddress());
			
			}	
		}
		if(ngphCanonical.getOrderingCustomerId() != null && !"".equals(ngphCanonical.getOrderingCustomerId())){
			if(!ngphCanonical.getOrderingCustomerId().equals(ngphCanonicalOriginal.getOrderingCustomerId()))
			{
				labelTemp = getText("label.orderingCustomerID");
				
				performModifiedField(ngphCanonicalOriginal.getOrderingCustomerId(), modifiedStringBuffer,
						originalStringBuffer,labelTemp,ngphCanonical.getOrderingCustomerId());
			
			}	
		}
		if(ngphCanonical.getOrderingCustomerCtry() != null && !"".equals(ngphCanonical.getOrderingCustomerCtry())){
			if(!ngphCanonical.getOrderingCustomerCtry().equals(ngphCanonicalOriginal.getOrderingCustomerCtry()))
			{
				labelTemp = getText("label.orderingCustomerCtry");
				
				performModifiedField(ngphCanonicalOriginal.getOrderingCustomerCtry(), modifiedStringBuffer,
						originalStringBuffer,labelTemp,ngphCanonical.getOrderingCustomerCtry());
			
			}	
		}
		
		if(ngphCanonical.getOrderingCustomerCtctDtls() != null && !"".equals(ngphCanonical.getOrderingCustomerCtctDtls())){
			if(!ngphCanonical.getOrderingCustomerCtctDtls().equals(ngphCanonicalOriginal.getOrderingCustomerCtctDtls()))
			{
				labelTemp = getText("label.orderingCustomerCtctDtls");
				
				performModifiedField(ngphCanonicalOriginal.getOrderingCustomerCtctDtls(), modifiedStringBuffer,
						originalStringBuffer,labelTemp,ngphCanonical.getOrderingCustomerCtctDtls());
			
			}	
		}
		if(ngphCanonical.getSenderCorrespondent() != null && !"".equals(ngphCanonical.getSenderCorrespondent())){
			if(!ngphCanonical.getSenderCorrespondent().equals(ngphCanonicalOriginal.getSenderCorrespondent()))
			{
				labelTemp = getText("label.SenderCorrespondent");
				
				performModifiedField(ngphCanonicalOriginal.getSenderCorrespondent(), modifiedStringBuffer,
						originalStringBuffer,labelTemp,ngphCanonical.getSenderCorrespondent());
			
			}	
		}
		if(ngphCanonical.getSenderCorrespondentAcct() != null && !"".equals(ngphCanonical.getSenderCorrespondentAcct())){
			if(!ngphCanonical.getSenderCorrespondentAcct().equals(ngphCanonicalOriginal.getSenderCorrespondentAcct()))
			{
				labelTemp = getText("label.senderCorrespondentAcct");
				
				performModifiedField(ngphCanonicalOriginal.getSenderCorrespondentAcct(), modifiedStringBuffer,
						originalStringBuffer,labelTemp,ngphCanonical.getSenderCorrespondentAcct());
			
			}	
		}
		if(ngphCanonical.getReceiverCorrespondent() != null && !"".equals(ngphCanonical.getReceiverCorrespondent())){
			if(!ngphCanonical.getReceiverCorrespondent().equals(ngphCanonicalOriginal.getReceiverCorrespondent()))
			{
				labelTemp = getText("label.receiverCorrespondent");
				
				performModifiedField(ngphCanonicalOriginal.getReceiverCorrespondent(), modifiedStringBuffer,
						originalStringBuffer,labelTemp,ngphCanonical.getReceiverCorrespondent());
			
			}	
		}
		
		if(ngphCanonical.getReceiverCorrespondentAcct() != null && !"".equals(ngphCanonical.getReceiverCorrespondentAcct())){
			if(!ngphCanonical.getReceiverCorrespondentAcct().equals(ngphCanonicalOriginal.getReceiverCorrespondentAcct()))
			{
				labelTemp = getText("label.receiverCorrespondentAcct");
				
				performModifiedField(ngphCanonicalOriginal.getReceiverCorrespondentAcct(), modifiedStringBuffer,
						originalStringBuffer,labelTemp,ngphCanonical.getReceiverCorrespondentAcct());
			
			}	
		}

		if(ngphCanonical.getThirdCorrespondent() != null && !"".equals(ngphCanonical.getThirdCorrespondent())){
			if(!ngphCanonical.getThirdCorrespondent().equals(ngphCanonicalOriginal.getThirdCorrespondent()))
			{
				labelTemp = getText("label.thirdCorrespondent");
				
				performModifiedField(ngphCanonicalOriginal.getThirdCorrespondent(), modifiedStringBuffer,
						originalStringBuffer,labelTemp,ngphCanonical.getThirdCorrespondent());
			
			}	
		}
		if(ngphCanonical.getThirdCorrespondentAcct() != null && !"".equals(ngphCanonical.getThirdCorrespondentAcct())){
			if(!ngphCanonical.getThirdCorrespondentAcct().equals(ngphCanonicalOriginal.getThirdCorrespondentAcct()))
			{
				labelTemp = getText("label.thirdCorrespondentAcct");
				
				performModifiedField(ngphCanonicalOriginal.getThirdCorrespondentAcct(), modifiedStringBuffer,
						originalStringBuffer,labelTemp,ngphCanonical.getThirdCorrespondentAcct());
			
			}	
		}
		if(ngphCanonical.getBeneficiaryCustomerCtctDtls() != null && !"".equals(ngphCanonical.getBeneficiaryCustomerCtctDtls())){
			if(!ngphCanonical.getBeneficiaryCustomerCtctDtls().equals(ngphCanonicalOriginal.getBeneficiaryCustomerCtctDtls()))
			{
				labelTemp = getText("label.beneficiaryCustomerCtctDtls");
				
				performModifiedField(ngphCanonicalOriginal.getBeneficiaryCustomerCtctDtls(), modifiedStringBuffer,
						originalStringBuffer,labelTemp,ngphCanonical.getBeneficiaryCustomerCtctDtls());
			
			}	
		}
		if(ngphCanonical.getUltimateCreditorAddress() != null && !"".equals(ngphCanonical.getUltimateCreditorAddress())){
			if(!ngphCanonical.getUltimateCreditorAddress().equals(ngphCanonicalOriginal.getUltimateCreditorAddress()))
			{
				labelTemp = getText("label.ultimateCreditorAddress");
				
				performModifiedField(ngphCanonicalOriginal.getUltimateCreditorAddress(), modifiedStringBuffer,
						originalStringBuffer,labelTemp,ngphCanonical.getUltimateCreditorAddress());
			
			}	
		}
		if(ngphCanonical.getUltimateCreditorID() != null && !"".equals(ngphCanonical.getUltimateCreditorID())){
			if(!ngphCanonical.getUltimateCreditorID().equals(ngphCanonicalOriginal.getUltimateCreditorID()))
			{
				labelTemp = getText("label.ultimateCreditorID");
				
				performModifiedField(ngphCanonicalOriginal.getUltimateCreditorID(), modifiedStringBuffer,
						originalStringBuffer,labelTemp,ngphCanonical.getUltimateCreditorID());
			
			}	
		}
		if(ngphCanonical.getUltimateCreditorCtry() != null && !"".equals(ngphCanonical.getUltimateCreditorCtry())){
			if(!ngphCanonical.getUltimateCreditorCtry().equals(ngphCanonicalOriginal.getUltimateCreditorCtry()))
			{
				labelTemp = getText("label.ultimateCreditorCtry");
				
				performModifiedField(ngphCanonicalOriginal.getUltimateCreditorCtry(), modifiedStringBuffer,
						originalStringBuffer,labelTemp,ngphCanonical.getUltimateCreditorCtry());
			
			}	
		}
		if(ngphCanonical.getUltimateCreditorCtctDtls() != null && !"".equals(ngphCanonical.getUltimateCreditorCtctDtls())){
			if(!ngphCanonical.getUltimateCreditorCtctDtls().equals(ngphCanonicalOriginal.getUltimateCreditorCtctDtls()))
			{
				labelTemp = getText("label.ultimateCreditorCtctDtls");
				
				performModifiedField(ngphCanonicalOriginal.getUltimateCreditorCtctDtls(), modifiedStringBuffer,
						originalStringBuffer,labelTemp,ngphCanonical.getUltimateCreditorCtctDtls());
			
			}	
		}
		if(ngphCanonical.getDstMsgType() != null && !"".equals(ngphCanonical.getDstMsgType())){
			if(!ngphCanonical.getDstMsgType().equals(ngphCanonicalOriginal.getDstMsgType()))
			{
				labelTemp = getText("label.dstMsgType");
				
				performModifiedField(ngphCanonicalOriginal.getDstMsgType(), modifiedStringBuffer,
						originalStringBuffer,labelTemp,ngphCanonical.getDstMsgType());
			
			}	
		}
		if(ngphCanonical.getDstMsgSubType() != null && !"".equals(ngphCanonical.getDstMsgSubType())){
			if(!ngphCanonical.getDstMsgSubType().equals(ngphCanonicalOriginal.getDstMsgSubType()))
			{
				labelTemp = getText("label.dstMsgSubType");
				
				performModifiedField(ngphCanonicalOriginal.getDstMsgSubType(), modifiedStringBuffer,
						originalStringBuffer,labelTemp,ngphCanonical.getDstMsgSubType());
			
			}	
		}
		
		if(ngphCanonical.getMsgValueDate() != null ){
			if(!ngphCanonical.getMsgValueDate().equals(ngphCanonicalOriginal.getMsgValueDate()))
			{
				labelTemp = getText("label.dstMsgSubType");
				
				modifiedStringBuffer.append(";").append(labelTemp).append(" ").append("=").append(" ").append(ngphCanonical.getMsgValueDate());
				originalStringBuffer.append(";").append(labelTemp).append(" ").append("=").append(" ").append(ngphCanonicalOriginal.getMsgValueDate());	
				
			}	
		}
		if(ngphCanonical.getIntermediary2BankName() != null && !"".equals(ngphCanonical.getIntermediary2BankName())){
			if(!ngphCanonical.getIntermediary2BankName().equals(ngphCanonicalOriginal.getIntermediary2BankName()))
			{
				labelTemp = getText("label.intermediary2BankName");
				
				performModifiedField(ngphCanonicalOriginal.getIntermediary2BankName(), modifiedStringBuffer,
						originalStringBuffer,labelTemp,ngphCanonical.getIntermediary2BankName());
				
			}	
		}
		if(ngphCanonical.getIntermediary1BankName() != null && !"".equals(ngphCanonical.getIntermediary1BankName())){
			if(!ngphCanonical.getIntermediary1BankName().equals(ngphCanonicalOriginal.getIntermediary1BankName()))
			{
				labelTemp = getText("label.intermediary1BankName");
				
				performModifiedField(ngphCanonicalOriginal.getIntermediary1BankName(), modifiedStringBuffer,
						originalStringBuffer,labelTemp,ngphCanonical.getIntermediary1BankName());
				
			}	
		}
		if(ngphCanonical.getIntermediary3BankName() != null && !"".equals(ngphCanonical.getIntermediary3BankName())){
			if(!ngphCanonical.getIntermediary3BankName().equals(ngphCanonicalOriginal.getIntermediary3BankName()))
			{
				labelTemp = getText("label.intermediary3BankName");
				
				performModifiedField(ngphCanonicalOriginal.getIntermediary3BankName(), modifiedStringBuffer,
						originalStringBuffer,labelTemp,ngphCanonical.getIntermediary3BankName());
				
			}	
		}
	
		
	}
	/**
	* This method is used to set the changed values and old values into stringBuffer
	* is updatating the value as fieldname=value 
	* @param String originalFieldValue
	* @param StringBuffer modifiedStringBuffer
	* @param StringBuffer originalStringBuffer
	* @param String label
	* @param String modifiedFieldValue
	*/
	private void performModifiedField(String originalFieldValue,
			StringBuffer modifiedStringBuffer, StringBuffer originalStringBuffer,String label,String modifiedFieldValue) {
		
			modifiedStringBuffer.append(";").append(label).append(" ").append("=").append(" ").append(modifiedFieldValue);
			originalStringBuffer.append(";").append(label).append(" ").append("=").append(" ").append(originalFieldValue);	
		
		
	}
	


	public void setSession(Map<String, Object> session) {
		this.session = session;
		
	}
	
/*	public void setSession(Map<String, Object> session) {
	this.session= session;
		
	}*/
}
