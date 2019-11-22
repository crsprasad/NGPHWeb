package com.logica.ngph.web.action;


import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.springframework.context.ApplicationContextException;

import com.logica.ngph.Entity.LcCommodity;

import com.logica.ngph.common.ConstantUtil;
import com.logica.ngph.dtos.LCCanonicalDto;
import com.logica.ngph.service.LcOpenService;
import com.logica.ngph.service.LetterOfCreditService;
import com.logica.ngph.service.PaymentMessageService;
import com.logica.ngph.service.PendingAuthorizationService;
import com.logica.ngph.web.utils.ApplicationContextProvider;
import com.logica.ngph.web.utils.SerializeManager;
import com.logica.ngph.web.utils.WebConstants;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class AdviseOfDischargeAction extends ActionSupport implements SessionAware,
		ModelDriven<LCCanonicalDto> {

	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(AdviseOfDischargeAction.class);
	private Map<String, Object> session;
	LCCanonicalDto lcCanonicalDto = new LCCanonicalDto();
	
	private String hiddenTxnRef;
	private String txnRef;
	private String saveAction;
	private String checkForSubmit;
	private boolean validUserToApprove;
	private String flagMarked;
	
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

	public String getFlagMarked() {
		return flagMarked;
	}

	public void setFlagMarked(String flagMarked) {
		this.flagMarked = flagMarked;
	}

	public Map<String, Object> getSession() {
		return session;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	public LCCanonicalDto getModel() {
		// TODO Auto-generated method stub
		return lcCanonicalDto;
	}
	@SkipValidation
	public String displayAdviseOfDischarge()
	{
		return SUCCESS;
	}
	@SkipValidation
	public String displayAdviseOfDischargeDate()
	{
		try{
			String lcnumber = lcCanonicalDto.getLcNumber();
			LCCanonicalDto canonicalDto = null;
			LetterOfCreditService letterOfCreditService =  (LetterOfCreditService)  ApplicationContextProvider.getBean("letterOfCreditService");
			LcOpenService lcOpenService = (LcOpenService) ApplicationContextProvider.getBean("lcOpenService");
		
					canonicalDto=lcOpenService.getLCScreenData(lcnumber);
					setTOMainDTO(canonicalDto);
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
		addFieldError("LcNumber", "Unable To Do The Operation. Please Try Again");
		return "input";
	}
	
	public String setTOMainDTO(LCCanonicalDto canonicalDto)
	{
		lcCanonicalDto.setLcNumber(canonicalDto.getLcNumber());
		lcCanonicalDto.setAdvisingBank(canonicalDto.getAdvisingBank());
		lcCanonicalDto.setPresentingBanksReference(canonicalDto.getPresentingBanksReference());
		lcCanonicalDto.setAmendmentDate(canonicalDto.getAmendmentDate());
		lcCanonicalDto.setLcCurrency(canonicalDto.getLcCurrency());
		lcCanonicalDto.setLcAmount(canonicalDto.getLcAmount());
		lcCanonicalDto.setSendertoReceiverInformation(canonicalDto.getSendertoReceiverInformation());
		lcCanonicalDto.setMsgRef(canonicalDto.getMsgRef());
		lcCanonicalDto.setComment(canonicalDto.getComment());
		lcCanonicalDto.setRepair(canonicalDto.getRepair());
		return null;
	}
	
	public String getAdviseOfDischargeApproval()
	{
		
		try{
			logger.info("getTransferDocumentaryCreditSentForApproval Is Called In AdviseOfDischargeAction");
			String txnKey="";
			PendingAuthorizationService pendingAuthorizationService = (PendingAuthorizationService)ApplicationContextProvider.getBean("pendingAuthorizationService");
			String userId = (String)session.get(WebConstants.CONSTANT_USER_NAME);
			if(StringUtils.isNotBlank(lcCanonicalDto.getRepair()) && StringUtils.isNotEmpty(lcCanonicalDto.getRepair())){
				if(!getHiddenTxnRef().isEmpty())
				{
					pendingAuthorizationService.updateRejectStatusToPending(getHiddenTxnRef());
				}else
				{
					txnKey = pendingAuthorizationService.getTranRef(new SerializeManager<LCCanonicalDto>().serializeObject((String)session.get(WebConstants.CONSTANT_USER_NAME), lcCanonicalDto),"Advice Of Discharge",userId);
				}
				PaymentMessageService paymentMessageService =(PaymentMessageService)ApplicationContextProvider.getBean("paymentMessageService");
				paymentMessageService.changeMsgStatus2to99(lcCanonicalDto.getMsgRef());
			}
			
			session.put("screenRef", txnKey);
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
		return "success";
	}
	
	public String saveAdviceOFDischarge()
	{
		try{		
			System.out.println(""+lcCanonicalDto.getLcType());
			PendingAuthorizationService pendingAuthorizationService = (PendingAuthorizationService)ApplicationContextProvider.getBean("pendingAuthorizationService");
			LetterOfCreditService letterOfCreditService =  (LetterOfCreditService)  ApplicationContextProvider.getBean("letterOfCreditService");
			String userId = (String)session.get(WebConstants.CONSTANT_USER_NAME);
			if(getSaveAction().equals("Approve")){
			String returnVAlue= letterOfCreditService.saveLC(setRemainingCanonical(lcCanonicalDto),(List<LcCommodity>)session.get("bgCommoditiesList"),"AdviceOfDischarge",userId,lcCanonicalDto.getRepair());
		
			
			if(returnVAlue!=null && !returnVAlue.equals("")){
				pendingAuthorizationService.changeStatus(getSaveAction(), getHiddenTxnRef());
				return "success";	
			}else	{
				addActionError("Unable to perform the operation. Please try again");
				return "input";
			}
			}
			else if(getSaveAction().equals("Reject"))
			{
				if(StringUtils.isNotBlank(lcCanonicalDto.getRepair()) && StringUtils.isNotEmpty(lcCanonicalDto.getRepair())){
					PaymentMessageService paymentMessageService =(PaymentMessageService)ApplicationContextProvider.getBean("paymentMessageService");
					paymentMessageService.changeMsgStatus99to2(lcCanonicalDto.getMsgRef());
				}
				pendingAuthorizationService.changeStatus(getSaveAction(), getHiddenTxnRef());
				
				pendingAuthorizationService = null;
				return "success";
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
	private LCCanonicalDto setRemainingCanonical(LCCanonicalDto obj)
	{
		LcOpenService lcOpenService = (LcOpenService) ApplicationContextProvider.getBean("lcOpenService");
		LCCanonicalDto canonicalDto = null;
		canonicalDto=lcOpenService.getLCScreenData(obj.getLcNumber());
		canonicalDto.setLcNumber(obj.getLcNumber());
		canonicalDto.setAdvisingBank(obj.getAdvisingBank());
		canonicalDto.setPresentingBanksReference(obj.getPresentingBanksReference());
		canonicalDto.setAmendmentDate(obj.getAmendmentDate());
		canonicalDto.setLcCurrency(obj.getLcCurrency());
		canonicalDto.setLcAmount(obj.getLcAmount());
		canonicalDto.setSendertoReceiverInformation(obj.getSendertoReceiverInformation());
		canonicalDto.setMsgRef(obj.getMsgRef());
		canonicalDto.setComment(obj.getComment());
		canonicalDto.setRepair(obj.getRepair());
		return canonicalDto;	
				
	}
	
	@SkipValidation
	public String getAdviceOfDischargeAuthorization()
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
		//LCCanonicalDto temp= getSerializedObject(tempScreenString);
		LCCanonicalDto temp = (LCCanonicalDto) new SerializeManager<LCCanonicalDto>().getSerializedObject(tempScreenString);
		setTOMainDTO(temp);
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
	
	@SkipValidation
	public String viewAdviceOfDischargePayment()
	{
		try{
		
		String msgRef = (String) session.get("messageIndex");
		
		LetterOfCreditService letterOfCreditService =  (LetterOfCreditService)  ApplicationContextProvider.getBean("letterOfCreditService");
		LCCanonicalDto canonicalDto = letterOfCreditService.getPreAdviceRepair(msgRef);
		if(canonicalDto.getLcNumber()!=null && StringUtils.isNotBlank(canonicalDto.getLcNumber()) && StringUtils.isNotEmpty(canonicalDto.getLcNumber())){	
			canonicalDto.setRepair(ConstantUtil.REPAIR);
			setTOMainDTO(canonicalDto);
		
		//setRepairData(ConstantUtil.REPAIR);
		}else
		{
			addFieldError("paymentMessageType",ConstantUtil.ERRORMESSAGE);
			return "failure";
		}
		return INPUT;
		}catch(Exception exception)
		{
			addFieldError("paymentMessageType",ConstantUtil.ERRORMESSAGE);
			return "failure";
		}
	}
	
	public void validate()
	{
		if(lcCanonicalDto.getLcNumber().startsWith("/")){
			 addFieldError("lcNumber", "Lc Number must not start with /");
		}else if(lcCanonicalDto.getLcNumber().endsWith("/")){
			addFieldError("lcNumber", "Lc Number must not End with /");;
		}else if(lcCanonicalDto.getLcNumber().contains("//")){
			addFieldError("lcNumber", "Lc Number must not contain two consecutive slashes '//'");
		}
		LetterOfCreditService letterOfCreditService =  (LetterOfCreditService)  ApplicationContextProvider.getBean("letterOfCreditService");
		if(StringUtils.isNotBlank(lcCanonicalDto.getAdvisingBank()) && StringUtils.isNotEmpty(lcCanonicalDto.getAdvisingBank()) && lcCanonicalDto.getAdvisingBank()!=null){
			if(letterOfCreditService.checkIFSC(lcCanonicalDto.getAdvisingBank())==false){
				addFieldError("advisingBank", "Advising BankCode Is Not Available In System");
			}
		}
		if(StringUtils.isBlank(lcCanonicalDto.getRepair()) && StringUtils.isEmpty(lcCanonicalDto.getRepair())){
			if(letterOfCreditService.isLcNumberExist(lcCanonicalDto.getLcNumber())!=true)
					{
						addFieldError("LcNumber","Lc Number IS Not Available In DataBase");
					}
			
			}
			else
			{
				if(!letterOfCreditService.getstatusForLCNumber(lcCanonicalDto.getMsgRef()))
				{
					addFieldError("LcNumber", "Message Is Not In Valid State");
				}
			}
		
	}

}
