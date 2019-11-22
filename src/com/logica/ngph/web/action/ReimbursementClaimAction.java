package com.logica.ngph.web.action;

import java.sql.Clob;
import java.util.ArrayList;
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
import com.logica.ngph.web.utils.EventLogging;
import com.logica.ngph.web.utils.SerializeManager;
import com.logica.ngph.web.utils.WebConstants;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class ReimbursementClaimAction extends ActionSupport implements ModelDriven<LCCanonicalDto>,SessionAware{
	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(LcOpenAction.class);
	private Map<String, Object> session;
	LCCanonicalDto lcCanonicalDto = new LCCanonicalDto();
	private String hiddenTxnRef;
	private String txnRef;
	private String saveAction;
	private String checkForSubmit;
	private boolean validUserToApprove;
	private String action;
	private String lcNumberForInward;
	private String flagForScreen;
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




	public String getAction() {
		return action;
	}




	public void setAction(String action) {
		this.action = action;
	}




	public String getLcNumberForInward() {
		return lcNumberForInward;
	}




	public void setLcNumberForInward(String lcNumberForInward) {
		this.lcNumberForInward = lcNumberForInward;
	}




	public String getFlagForScreen() {
		return flagForScreen;
	}




	public void setFlagForScreen(String flagForScreen) {
		this.flagForScreen = flagForScreen;
	}




	public String getFlagMarked() {
		return flagMarked;
	}




	public void setFlagMarked(String flagMarked) {
		this.flagMarked = flagMarked;
	}



	@SkipValidation
	public String displayReimbursmentClaim()
	{
		return SUCCESS;
	}
	
	
	
	
	public Map<String, Object> getSession() {
		return session;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
	
	public LCCanonicalDto getModel() {
		
		return lcCanonicalDto;
	}
	
	@SkipValidation
	public String displayReimbursementClaimData()
	{
		try{
			
			String lcnumber = lcCanonicalDto.getLcNumber();
			LCCanonicalDto canonicalDto = null;
			LetterOfCreditService letterOfCreditService =  (LetterOfCreditService)  ApplicationContextProvider.getBean("letterOfCreditService");
			LcOpenService lcOpenService = (LcOpenService) ApplicationContextProvider.getBean("lcOpenService");
		
					canonicalDto=lcOpenService.getLCScreenData(lcnumber);
			
	       // Object[] obj = (Object[]) list.get(0);
					lcCanonicalDto.setLcType(canonicalDto.getLcType());
					lcCanonicalDto.setLcNumber(canonicalDto.getLcNumber());
					lcCanonicalDto.setLcExpiryDate(canonicalDto.getLcExpiryDate());
					lcCanonicalDto.setLcExpirePlace(canonicalDto.getLcExpirePlace());
					lcCanonicalDto.setPositiveTolerance(canonicalDto.getPositiveTolerance());
					lcCanonicalDto.setNegativeTolerance(canonicalDto.getNegativeTolerance());
				            
				    lcCanonicalDto.setDraweeBankAccount(canonicalDto.getDraweeBankAccount());  
					lcCanonicalDto.setMaximumCreditAmount(canonicalDto.getMaximumCreditAmount());
					lcCanonicalDto.setAdditionalAmounts(canonicalDto.getAdditionalAmounts());
					lcCanonicalDto.setAuthorisedBankCode(canonicalDto.getAuthorisedBankCode());
					lcCanonicalDto.setAuthorisedAndAddress(canonicalDto.getAuthorisedAndAddress());
					lcCanonicalDto.setAuthorisationMode(canonicalDto.getAuthorisationMode());
					lcCanonicalDto.setGoodsLoadingDispatchPlace(canonicalDto.getGoodsLoadingDispatchPlace());
					lcCanonicalDto.setGoodsDestination(canonicalDto.getGoodsDestination());
					lcCanonicalDto.setLatestDateofShipment(canonicalDto.getLatestDateofShipment());
					lcCanonicalDto.setShipmentPeriod(canonicalDto.getShipmentPeriod());
					lcCanonicalDto.setShipmentTerms(canonicalDto.getShipmentTerms());
					lcCanonicalDto.setDraftsAt(canonicalDto.getDraftsAt());
					lcCanonicalDto.setDraweeBankpartyidentifier(canonicalDto.getDraweeBankpartyidentifier());
					lcCanonicalDto.setDraweeBankCode(canonicalDto.getDraweeBankCode());
					lcCanonicalDto.setDraweeBankNameAddress(canonicalDto.getDraweeBankNameAddress());
					lcCanonicalDto.setMixedPaymentDetails(canonicalDto.getMixedPaymentDetails());
					lcCanonicalDto.setDeferredPaymentDetails(canonicalDto.getDeferredPaymentDetails());
					lcCanonicalDto.setPartialShipments(canonicalDto.getPartialShipments());
					lcCanonicalDto.setTranshipment(canonicalDto.getTranshipment());
					lcCanonicalDto.setDocumentsRequired(canonicalDto.getDocumentsRequired());
					lcCanonicalDto.setAdditionalConditions(canonicalDto.getAdditionalConditions());
					lcCanonicalDto.setChargeDetails(canonicalDto.getChargeDetails());
					lcCanonicalDto.setPeriodforPresentation(canonicalDto.getPeriodforPresentation());
					lcCanonicalDto.setConfirmationCode(canonicalDto.getConfirmationCode());
					lcCanonicalDto.setInstructionstoPayingBank(canonicalDto.getInstructionstoPayingBank());
					lcCanonicalDto.setNarrative(canonicalDto.getNarrative());
					lcCanonicalDto.setMsgRef(canonicalDto.getMsgRef());       
				        
					lcCanonicalDto.setAdviseThroughBankpartyidentifier(canonicalDto.getAdviseThroughBankpartyidentifier());
					lcCanonicalDto.setAdvisingBank(canonicalDto.getAdvisingBank());
					lcCanonicalDto.setAdviseThroughBankCode(canonicalDto.getAdviseThroughBankCode());
					lcCanonicalDto.setAdviseThroughBankLocation(canonicalDto.getAdviseThroughBankLocation());
					lcCanonicalDto.setAdviseThroughBankName(canonicalDto.getAdviseThroughBankName());
					//lcCanonicalDto.setSendertoReceiverInformation(canonicalDto.getSendertoReceiverInformation());
					lcCanonicalDto.setReimbursingBank(canonicalDto.getReimbursingBank());
					lcCanonicalDto.setApplicantNameAddress(canonicalDto.getApplicantNameAddress());
					lcCanonicalDto.setApplicantBankNameAddress(canonicalDto.getApplicantBankNameAddress());
					lcCanonicalDto.setApplicantBankCode(canonicalDto.getApplicantBankCode());
					lcCanonicalDto.setApplicantBankpartyidentifier(canonicalDto.getApplicantBankpartyidentifier());
					lcCanonicalDto.setApplicantAccount(canonicalDto.getApplicantAccount());
					lcCanonicalDto.setAdvisingBank(canonicalDto.getAdvisingBank());
					//lcCanonicalDto.setBeneficiaryAccount(canonicalDto.getBeneficiaryAccount());
					lcCanonicalDto.setFirstBeneficiaryNameAddress(canonicalDto.getBeneficiaryNameAddress());
					lcCanonicalDto.setLcAmount(canonicalDto.getLcAmount());
					lcCanonicalDto.setLcCurrency(canonicalDto.getLcCurrency());
					lcCanonicalDto.setAdviseThroughBankAcc(canonicalDto.getAdviseThroughBankAcc());
					lcCanonicalDto.setAdditionalAmountsCovered(canonicalDto.getAdditionalAmountsCovered());
					lcCanonicalDto.setApplicableNarrative(canonicalDto.getApplicableNarrative());
					
					lcCanonicalDto.setRepair(canonicalDto.getRepair());
					lcCanonicalDto.setInitialDispatchPlace(canonicalDto.getInitialDispatchPlace());
					lcCanonicalDto.setFinalDeliveryPlace(canonicalDto.getFinalDeliveryPlace());
					lcCanonicalDto.setApplicableRule(canonicalDto.getApplicableRule());
					lcCanonicalDto.setReimbursingBankCode(canonicalDto.getReimbursingBankCode());
					lcCanonicalDto.setReimbursingBankNameAddress(canonicalDto.getReimbursingBankNameAddress());
					lcCanonicalDto.setSenderCorrespontAcount(canonicalDto.getSenderCorrespontAcount());
					lcCanonicalDto.setSendersCorrespondentPartyIdentifier(canonicalDto.getSendersCorrespondentPartyIdentifier());
					lcCanonicalDto.setIssueDate(canonicalDto.getIssueDate());
					lcCanonicalDto.setMsgHost(canonicalDto.getMsgHost());
					lcCanonicalDto.setSeqNo(canonicalDto.getSeqNo());
					lcCanonicalDto.setAdviseThroughBankAcc(canonicalDto.getAdviseThroughBankAcc());
					lcCanonicalDto.setPartyIdentifier(canonicalDto.getPartyIdentifier());
					lcCanonicalDto.setIssuingBankCode(canonicalDto.getIssuingBankCode());
					lcCanonicalDto.setIssuingBankPID(canonicalDto.getIssuingBankPID());
					lcCanonicalDto.setIssunigBankNameAndAddress(canonicalDto.getIssunigBankNameAndAddress());
					lcCanonicalDto.setNonIssuingBank(canonicalDto.getNonIssuingBank());
					if(canonicalDto.getTxnReference()!= null && !canonicalDto.getTxnReference().equals("")){
							lcCanonicalDto.setReceiverBankReference(canonicalDto.getTxnReference());
						 }else if(canonicalDto.getCustTxnReference()!= null && !canonicalDto.getCustTxnReference().equals("")){
							lcCanonicalDto.setReceiverBankReference(canonicalDto.getCustTxnReference());
						 }else if(canonicalDto.getSndrTxnId()!=null && !canonicalDto.getSndrTxnId().equals("")){
							lcCanonicalDto.setReceiverBankReference(canonicalDto.getSndrTxnId());
						 }
					lcCanonicalDto.setPrincipalAmountClaimedDate(canonicalDto.getPrincipalAmountClaimedDate());
					lcCanonicalDto.setLcNetAmtClaimed(canonicalDto.getLcNetAmtClaimed());
					lcCanonicalDto.setTotalAmountClaimed(canonicalDto.getTotalAmountClaimed());
					lcCanonicalDto.setNegotiatingBankPartyIdentifier(canonicalDto.getNegotiatingBankPartyIdentifier());
					lcCanonicalDto.setNegotiatingBankAccount(canonicalDto.getNegotiatingBankAccount());
					lcCanonicalDto.setNegotiatingBankNameAndAddress(canonicalDto.getNegotiatingBankNameAndAddress());
					lcCanonicalDto.setNegotiatingBankCode(canonicalDto.getNegotiatingBankCode());
					lcCanonicalDto.setMsgValueDate(canonicalDto.getMsgValueDate());
		
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
	
	public String getReimbursementClaimApproval()
	{
		
		try{
			logger.info("getTransferDocumentaryCreditSentForApproval Is Called In TransferDocumentOfcreditAction");
			String txnKey="";
			PendingAuthorizationService pendingAuthorizationService = (PendingAuthorizationService)ApplicationContextProvider.getBean("pendingAuthorizationService");
			String userId = (String)session.get(WebConstants.CONSTANT_USER_NAME);
			EventLogging eventLogging = (EventLogging)ApplicationContextProvider.getBean("eventLogging");
			if(StringUtils.isNotBlank(lcCanonicalDto.getRepair()) && StringUtils.isNotEmpty(lcCanonicalDto.getRepair())){
				PaymentMessageService paymentMessageService =(PaymentMessageService)ApplicationContextProvider.getBean("paymentMessageService");
				paymentMessageService.changeMsgStatus2to99(lcCanonicalDto.getMsgRef());
				eventLogging.doEventLogging(userId," Reimbursement Claim ",ConstantUtil.EVENTID_REIMBURSMENT_CLAIM+ConstantUtil.EVENTID_REPAIR_SUBMIT,ConstantUtil.EVENTLOGGINGCOMMENTSUBMIT+" "+ ConstantUtil.EVENTLOGGINGCOMMENTREAPIR,lcCanonicalDto.getLcNumber(),lcCanonicalDto.getMsgRef());
			}else
				eventLogging.doEventLogging(userId," Reimbursement Claim ",ConstantUtil.EVENTID_REIMBURSMENT_CLAIM+ConstantUtil.EVENTID_SUBMIT,ConstantUtil.EVENTLOGGINGCOMMENTSUBMIT,lcCanonicalDto.getLcNumber(),lcCanonicalDto.getMsgRef());
			txnKey = pendingAuthorizationService.getTranRef(new SerializeManager<LCCanonicalDto>().serializeObject((String)session.get(WebConstants.CONSTANT_USER_NAME), lcCanonicalDto),"Reimbursement Claim",userId);
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
	public String getObjectForReimbursementClaim()
	{
		try{		
			System.out.println(""+lcCanonicalDto.getLcType());
			PendingAuthorizationService pendingAuthorizationService = (PendingAuthorizationService)ApplicationContextProvider.getBean("pendingAuthorizationService");
			LetterOfCreditService letterOfCreditService =  (LetterOfCreditService)  ApplicationContextProvider.getBean("letterOfCreditService");
			EventLogging eventLogging = (EventLogging)ApplicationContextProvider.getBean("eventLogging");
			String userId = (String)session.get(WebConstants.CONSTANT_USER_NAME);
			if(getSaveAction().equals("Approve")){
			String returnVAlue= letterOfCreditService.saveLC(setRemainingCanonical(lcCanonicalDto),(List<LcCommodity>)session.get("bgCommoditiesList"),"ReimburesmentClaim",userId,lcCanonicalDto.getRepair());
		
			
			if(returnVAlue!=null && !returnVAlue.equals("")){
				pendingAuthorizationService.changeStatus(getSaveAction(), getHiddenTxnRef());
				if(StringUtils.isBlank(lcCanonicalDto.getRepair()) && StringUtils.isEmpty(lcCanonicalDto.getRepair())){
					eventLogging.doEventLogging(userId," Reimbursement Claim ",ConstantUtil.EVENTID_REIMBURSMENT_CLAIM+ConstantUtil.EVENTID_APPROVE,ConstantUtil.EVENTLOGGINGCOMMENTAPPROVAL,lcCanonicalDto.getLcNumber(),lcCanonicalDto.getMsgRef());
				}else
				{
					eventLogging.doEventLogging(userId," Reimbursement Claim ",ConstantUtil.EVENTID_REIMBURSMENT_CLAIM + ConstantUtil.EVENTID_REPAIR_APPROVE,ConstantUtil.EVENTLOGGINGCOMMENTAPPROVAL+" "+ConstantUtil.EVENTLOGGINGCOMMENTREAPIR,lcCanonicalDto.getLcNumber(),lcCanonicalDto.getMsgRef());
				}
				return "success";	
			}else	{
				addActionError("Unable to perform the operation. Please try again");
				return "input";
			}
			}
			else if(getSaveAction().equals("Reject"))
			{
				pendingAuthorizationService.changeStatus(getSaveAction(), getHiddenTxnRef());
				if(StringUtils.isNotBlank(lcCanonicalDto.getRepair()) && StringUtils.isNotEmpty(lcCanonicalDto.getRepair())){
					PaymentMessageService paymentMessageService =(PaymentMessageService)ApplicationContextProvider.getBean("paymentMessageService");
					paymentMessageService.changeMsgStatus99to2(lcCanonicalDto.getMsgRef());
					eventLogging.doEventLogging(userId," Reimbursement Claim ",ConstantUtil.EVENTID_REIMBURSMENT_CLAIM+ConstantUtil.EVENTID_REPAIR_REJECT,ConstantUtil.EVENTLOGGINGCOMMENTREJECT+" "+ConstantUtil.EVENTLOGGINGCOMMENTREAPIR,lcCanonicalDto.getLcNumber(),lcCanonicalDto.getMsgRef() );
				}else
					eventLogging.doEventLogging(userId," Reimbursement Claim ",ConstantUtil.EVENTID_REIMBURSMENT_CLAIM+ConstantUtil.EVENTID_REJECT,ConstantUtil.EVENTLOGGINGCOMMENTREJECT,lcCanonicalDto.getLcNumber(),lcCanonicalDto.getMsgRef());
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
		canonicalDto.setLcAmount(obj.getLcAmount());
		canonicalDto.setPrincipalAmountClaimedDate(obj.getPrincipalAmountClaimedDate());
		canonicalDto.setLcNetAmtClaimed(obj.getLcNetAmtClaimed());
		canonicalDto.setTotalAmountClaimed(obj.getTotalAmountClaimed());
		canonicalDto.setAdviseThroughBankpartyidentifier(obj.getAdviseThroughBankpartyidentifier());
		canonicalDto.setAdviseThroughBankAcc(obj.getAdviseThroughBankAcc());
		canonicalDto.setAdviseThroughBankCode(obj.getAdviseThroughBankCode());
		canonicalDto.setAdviseThroughBankLocation(obj.getAdviseThroughBankLocation());
		canonicalDto.setAdviseThroughBankName(obj.getAdviseThroughBankName());
		canonicalDto.setNegotiatingBankPartyIdentifier(obj.getNegotiatingBankPartyIdentifier());
		canonicalDto.setNegotiatingBankAccount(obj.getNegotiatingBankAccount());
		canonicalDto.setNegotiatingBankNameAndAddress(obj.getNegotiatingBankNameAndAddress());
		canonicalDto.setNegotiatingBankCode(obj.getNegotiatingBankCode());
		canonicalDto.setSendertoReceiverInformation(obj.getSendertoReceiverInformation());
		canonicalDto.setMsgValueDate(obj.getMsgValueDate());	
		canonicalDto.setMsgRef(obj.getMsgRef());
		canonicalDto.setComment(obj.getComment());
		return canonicalDto;	
				
	}
	@SkipValidation
	public String getReimbursementClaimAuthorization()
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
		List mulDataList = pendingAuthorizationService.getMulScreenData(getTxnRef());
		List<LcCommodity> temportList = new ArrayList<LcCommodity>();
		
		lcCanonicalDto.setLcNumber(temp.getLcNumber());
		lcCanonicalDto.setLcType(temp.getLcType());
		lcCanonicalDto.setAdditionalAmounts(temp.getAdditionalAmounts());
		lcCanonicalDto.setAdviseThroughBankCode(temp.getAdviseThroughBankCode());
		lcCanonicalDto.setAdviseThroughBankLocation(temp.getAdviseThroughBankLocation());
		lcCanonicalDto.setLcCurrency(temp.getLcCurrency());
		lcCanonicalDto.setLatestDateofShipment(temp.getLatestDateofShipment());
		lcCanonicalDto.setLcAmount(temp.getLcAmount());
		lcCanonicalDto.setLcExpirePlace(temp.getLcExpirePlace());
		lcCanonicalDto.setLcExpiryDate(temp.getLcExpiryDate());
		
		lcCanonicalDto.setAdviseThroughBankName(temp.getAdviseThroughBankName());
		lcCanonicalDto.setAdviseThroughBankpartyidentifier(temp.getAdviseThroughBankpartyidentifier());
		lcCanonicalDto.setApplicantNameAddress(temp.getApplicantNameAddress());
		lcCanonicalDto.setAuthorisationMode(temp.getAuthorisationMode());
		lcCanonicalDto.setAuthorisedAndAddress(temp.getAuthorisedAndAddress());
		lcCanonicalDto.setAuthorisedBankCode(temp.getAuthorisedBankCode());
		lcCanonicalDto.setBeneficiaryAccount(temp.getBeneficiaryAccount());
		lcCanonicalDto.setBeneficiaryNameAddress(temp.getBeneficiaryNameAddress());
		lcCanonicalDto.setMaximumCreditAmount(temp.getMaximumCreditAmount());
		lcCanonicalDto.setNarrative(temp.getNarrative());
		lcCanonicalDto.setNegativeTolerance(temp.getNegativeTolerance());
		lcCanonicalDto.setPositiveTolerance(temp.getPositiveTolerance());
		lcCanonicalDto.setSendertoReceiverInformation(temp.getSendertoReceiverInformation());
		lcCanonicalDto.setShipmentPeriod(temp.getShipmentPeriod());
		lcCanonicalDto.setShipmentTerms(temp.getShipmentTerms());
		
		lcCanonicalDto.setDraftsAt(temp.getDraftsAt());
        lcCanonicalDto.setDraweeBankpartyidentifier(temp.getDraweeBankpartyidentifier());
        lcCanonicalDto.setDraweeBankAccount(temp.getDraweeBankAccount());
        lcCanonicalDto.setDraweeBankCode(temp.getDraweeBankCode());
        lcCanonicalDto.setDraweeBankNameAddress(temp.getDraweeBankNameAddress());
        lcCanonicalDto.setMixedPaymentDetails(temp.getMixedPaymentDetails());
        lcCanonicalDto.setDeferredPaymentDetails(temp.getDeferredPaymentDetails());
        lcCanonicalDto.setPartialShipments(temp.getPartialShipments());
        lcCanonicalDto.setTranshipment(temp.getTranshipment());
        lcCanonicalDto.setDocumentsRequired(temp.getDocumentsRequired());
        lcCanonicalDto.setAdditionalConditions(temp.getAdditionalConditions());
        lcCanonicalDto.setChargeDetails(temp.getChargeDetails());
        lcCanonicalDto.setPeriodforPresentation(temp.getPeriodforPresentation());
        lcCanonicalDto.setConfirmationCode(temp.getConfirmationCode());
        lcCanonicalDto.setInstructionstoPayingBank(temp.getInstructionstoPayingBank());
        lcCanonicalDto.setNarrative(temp.getNarrative());
        lcCanonicalDto.setMsgRef(temp.getMsgRef());
        
        lcCanonicalDto.setAdviseThroughBankpartyidentifier(temp.getAdviseThroughBankpartyidentifier());
		lcCanonicalDto.setAdvisingBank(temp.getAdvisingBank());
		lcCanonicalDto.setAdviseThroughBankCode(temp.getAdviseThroughBankCode());
		lcCanonicalDto.setAdviseThroughBankLocation(temp.getAdviseThroughBankLocation());
		lcCanonicalDto.setAdviseThroughBankAcc(temp.getAdviseThroughBankAcc());
		
		lcCanonicalDto.setAdviseThroughBankName(temp.getAdviseThroughBankName());
		lcCanonicalDto.setSendertoReceiverInformation(temp.getSendertoReceiverInformation());
		lcCanonicalDto.setReimbursingBank(temp.getReimbursingBank());
		lcCanonicalDto.setGoodsDestination(temp.getGoodsDestination());
		lcCanonicalDto.setGoodsLoadingDispatchPlace(temp.getGoodsLoadingDispatchPlace());
		lcCanonicalDto.setApplicantAccount(temp.getApplicantAccount());
		lcCanonicalDto.setApplicantBankCode(temp.getApplicantBankCode());
		lcCanonicalDto.setApplicantBankNameAddress(temp.getApplicantBankNameAddress());
		lcCanonicalDto.setApplicantBankpartyidentifier(temp.getApplicantBankpartyidentifier());
		lcCanonicalDto.setPartialShipments(temp.getPartialShipments());
		lcCanonicalDto.setNetChargeAmount(temp.getNetChargeAmount());
		lcCanonicalDto.setPartyIdentifier(temp.getPartyIdentifier());
		lcCanonicalDto.setSenderCorrespontAcount(temp.getSenderCorrespontAcount());
		lcCanonicalDto.setReimbursingBankCode(temp.getReimbursingBankCode());
		lcCanonicalDto.setReimbursingBankNameAddress(temp.getReimbursingBankNameAddress());
		lcCanonicalDto.setSendersCorrespondentCode(temp.getSendersCorrespondentCode());	
		lcCanonicalDto.setInitialDispatchPlace(temp.getInitialDispatchPlace());
		lcCanonicalDto.setFinalDeliveryPlace(temp.getFinalDeliveryPlace());
		lcCanonicalDto.setRepair(temp.getRepair());
		lcCanonicalDto.setApplicableNarrative(temp.getApplicableNarrative());
		lcCanonicalDto.setApplicableRule(temp.getApplicableRule());
		lcCanonicalDto.setMsgHost(temp.getMsgHost());
		lcCanonicalDto.setSeqNo(temp.getSeqNo());
		lcCanonicalDto.setIssueDate(temp.getIssueDate());
		lcCanonicalDto.setIssuingBankCode(temp.getIssuingBankCode());
		lcCanonicalDto.setIssuingBankPID(temp.getIssuingBankPID());
		lcCanonicalDto.setIssunigBankNameAndAddress(temp.getIssunigBankNameAndAddress());
		lcCanonicalDto.setNonIssuingBank(temp.getNonIssuingBank());
		lcCanonicalDto.setFirstBeneficiaryNameAddress(temp.getFirstBeneficiaryNameAddress());
		lcCanonicalDto.setPrincipalAmountClaimedDate(temp.getPrincipalAmountClaimedDate());
		lcCanonicalDto.setLcNetAmtClaimed(temp.getLcNetAmtClaimed());
		lcCanonicalDto.setTotalAmountClaimed(temp.getTotalAmountClaimed());
		lcCanonicalDto.setNegotiatingBankPartyIdentifier(temp.getNegotiatingBankPartyIdentifier());
		lcCanonicalDto.setNegotiatingBankAccount(temp.getNegotiatingBankAccount());
		lcCanonicalDto.setNegotiatingBankNameAndAddress(temp.getNegotiatingBankNameAndAddress());
		lcCanonicalDto.setNegotiatingBankCode(temp.getNegotiatingBankCode());
		lcCanonicalDto.setMsgValueDate(temp.getMsgValueDate());
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
	public String viewReimbursementClaimPayment()
	{
		try{
			
		String msgRef = (String) session.get("messageIndex");
		LetterOfCreditService letterOfCreditService =  (LetterOfCreditService)  ApplicationContextProvider.getBean("letterOfCreditService");
		LCCanonicalDto canonicalDto = letterOfCreditService.getPreAdviceRepair(msgRef);
		if(canonicalDto.getLcNumber()!=null && StringUtils.isNotBlank(canonicalDto.getLcNumber()) && StringUtils.isNotEmpty(canonicalDto.getLcNumber()))
			{canonicalDto.setRepair(ConstantUtil.REPAIR);
			setALLValueTODTO(canonicalDto);
			}
		else
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
	private void setALLValueTODTO(LCCanonicalDto obj)
	{
		LcOpenService lcOpenService = (LcOpenService) ApplicationContextProvider.getBean("lcOpenService");
		LCCanonicalDto canonicalDto = obj;
		
		lcCanonicalDto.setLcType(canonicalDto.getLcType());
		lcCanonicalDto.setLcNumber(canonicalDto.getLcNumber());
		lcCanonicalDto.setLcExpiryDate(canonicalDto.getLcExpiryDate());
		lcCanonicalDto.setLcExpirePlace(canonicalDto.getLcExpirePlace());
	             	
		lcCanonicalDto.setPositiveTolerance(canonicalDto.getPositiveTolerance());
		lcCanonicalDto.setNegativeTolerance(canonicalDto.getNegativeTolerance());
	            
	     lcCanonicalDto.setDraweeBankAccount(canonicalDto.getDraweeBankAccount());  
		lcCanonicalDto.setMaximumCreditAmount(canonicalDto.getMaximumCreditAmount());
		lcCanonicalDto.setAdditionalAmounts(canonicalDto.getAdditionalAmounts());
		lcCanonicalDto.setAuthorisedBankCode(canonicalDto.getAuthorisedBankCode());
		lcCanonicalDto.setAuthorisedAndAddress(canonicalDto.getAuthorisedAndAddress());
		lcCanonicalDto.setAuthorisationMode(canonicalDto.getAuthorisationMode());
		lcCanonicalDto.setGoodsLoadingDispatchPlace(canonicalDto.getGoodsLoadingDispatchPlace());
		lcCanonicalDto.setGoodsDestination(canonicalDto.getGoodsDestination());
		lcCanonicalDto.setLatestDateofShipment(canonicalDto.getLatestDateofShipment());
		lcCanonicalDto.setShipmentPeriod(canonicalDto.getShipmentPeriod());
		lcCanonicalDto.setShipmentTerms(canonicalDto.getShipmentTerms());
		lcCanonicalDto.setDraftsAt(canonicalDto.getDraftsAt());
		lcCanonicalDto.setDraweeBankpartyidentifier(canonicalDto.getDraweeBankpartyidentifier());
		lcCanonicalDto.setDraweeBankCode(canonicalDto.getDraweeBankCode());
		lcCanonicalDto.setDraweeBankNameAddress(canonicalDto.getDraweeBankNameAddress());
		lcCanonicalDto.setMixedPaymentDetails(canonicalDto.getMixedPaymentDetails());
		lcCanonicalDto.setDeferredPaymentDetails(canonicalDto.getDeferredPaymentDetails());
		lcCanonicalDto.setPartialShipments(canonicalDto.getPartialShipments());
		lcCanonicalDto.setTranshipment(canonicalDto.getTranshipment());
		lcCanonicalDto.setDocumentsRequired(canonicalDto.getDocumentsRequired());
		lcCanonicalDto.setAdditionalConditions(canonicalDto.getAdditionalConditions());
		lcCanonicalDto.setChargeDetails(canonicalDto.getChargeDetails());
		lcCanonicalDto.setPeriodforPresentation(canonicalDto.getPeriodforPresentation());
		lcCanonicalDto.setConfirmationCode(canonicalDto.getConfirmationCode());
		lcCanonicalDto.setInstructionstoPayingBank(canonicalDto.getInstructionstoPayingBank());
		lcCanonicalDto.setNarrative(canonicalDto.getNarrative());
		lcCanonicalDto.setMsgRef(canonicalDto.getMsgRef());       
	        
		lcCanonicalDto.setAdviseThroughBankpartyidentifier(canonicalDto.getAdviseThroughBankpartyidentifier());
		lcCanonicalDto.setAdvisingBank(canonicalDto.getAdvisingBank());
		lcCanonicalDto.setAdviseThroughBankCode(canonicalDto.getAdviseThroughBankCode());
		lcCanonicalDto.setAdviseThroughBankLocation(canonicalDto.getAdviseThroughBankLocation());
		lcCanonicalDto.setAdviseThroughBankName(canonicalDto.getAdviseThroughBankName());
		lcCanonicalDto.setSendertoReceiverInformation(canonicalDto.getSendertoReceiverInformation());
		lcCanonicalDto.setReimbursingBank(canonicalDto.getReimbursingBank());
		lcCanonicalDto.setApplicantNameAddress(canonicalDto.getApplicantNameAddress());
		lcCanonicalDto.setApplicantBankNameAddress(canonicalDto.getApplicantBankNameAddress());
		lcCanonicalDto.setApplicantBankCode(canonicalDto.getApplicantBankCode());
		lcCanonicalDto.setApplicantBankpartyidentifier(canonicalDto.getApplicantBankpartyidentifier());
		lcCanonicalDto.setApplicantAccount(canonicalDto.getApplicantAccount());
		lcCanonicalDto.setAdvisingBank(canonicalDto.getAdvisingBank());
		lcCanonicalDto.setBeneficiaryAccount(canonicalDto.getBeneficiaryAccount());
		lcCanonicalDto.setBeneficiaryNameAddress(canonicalDto.getBeneficiaryNameAddress());
		lcCanonicalDto.setLcAmount(canonicalDto.getLcAmount());
		lcCanonicalDto.setLcCurrency(canonicalDto.getLcCurrency());
		lcCanonicalDto.setAdviseThroughBankAcc(canonicalDto.getAdviseThroughBankAcc());
		lcCanonicalDto.setAdditionalAmountsCovered(canonicalDto.getAdditionalAmountsCovered());
		lcCanonicalDto.setApplicableNarrative(canonicalDto.getApplicableNarrative());
		
		lcCanonicalDto.setRepair(canonicalDto.getRepair());
		lcCanonicalDto.setInitialDispatchPlace(canonicalDto.getInitialDispatchPlace());
		lcCanonicalDto.setFinalDeliveryPlace(canonicalDto.getFinalDeliveryPlace());
		lcCanonicalDto.setApplicableRule(canonicalDto.getApplicableRule());
		lcCanonicalDto.setReimbursingBankCode(canonicalDto.getReimbursingBankCode());
		lcCanonicalDto.setReimbursingBankNameAddress(canonicalDto.getReimbursingBankNameAddress());
		lcCanonicalDto.setSenderCorrespontAcount(canonicalDto.getSenderCorrespontAcount());
		lcCanonicalDto.setSendersCorrespondentPartyIdentifier(canonicalDto.getSendersCorrespondentPartyIdentifier());
		lcCanonicalDto.setIssueDate(canonicalDto.getIssueDate());
		lcCanonicalDto.setMsgHost(canonicalDto.getMsgHost());
		lcCanonicalDto.setSeqNo(canonicalDto.getSeqNo());
		lcCanonicalDto.setAdviseThroughBankAcc(canonicalDto.getAdviseThroughBankAcc());
		lcCanonicalDto.setPartyIdentifier(canonicalDto.getPartyIdentifier());
		if(canonicalDto.getTxnReference()!= null && !canonicalDto.getTxnReference().equals("")){
				lcCanonicalDto.setReceiverBankReference(canonicalDto.getTxnReference());
			 }else if(canonicalDto.getCustTxnReference()!= null && !canonicalDto.getCustTxnReference().equals("")){
				lcCanonicalDto.setReceiverBankReference(canonicalDto.getCustTxnReference());
			 }else if(canonicalDto.getSndrTxnId()!=null && !canonicalDto.getSndrTxnId().equals("")){
				lcCanonicalDto.setReceiverBankReference(canonicalDto.getSndrTxnId());
			 }
		lcCanonicalDto.setIssuingBankCode(canonicalDto.getIssuingBankCode());
		lcCanonicalDto.setIssuingBankPID(canonicalDto.getIssuingBankPID());
		lcCanonicalDto.setIssunigBankNameAndAddress(canonicalDto.getIssunigBankNameAndAddress());
		lcCanonicalDto.setNonIssuingBank(canonicalDto.getNonIssuingBank());
		lcCanonicalDto.setPrincipalAmountClaimedDate(canonicalDto.getPrincipalAmountClaimedDate());
		lcCanonicalDto.setLcNetAmtClaimed(canonicalDto.getLcNetAmtClaimed());
		lcCanonicalDto.setTotalAmountClaimed(canonicalDto.getTotalAmountClaimed());
		lcCanonicalDto.setNegotiatingBankPartyIdentifier(canonicalDto.getNegotiatingBankPartyIdentifier());
		lcCanonicalDto.setNegotiatingBankAccount(canonicalDto.getNegotiatingBankAccount());
		lcCanonicalDto.setNegotiatingBankNameAndAddress(canonicalDto.getNegotiatingBankNameAndAddress());
		lcCanonicalDto.setNegotiatingBankCode(canonicalDto.getNegotiatingBankCode());
		lcCanonicalDto.setMsgValueDate(canonicalDto.getMsgValueDate());
		
		
	}
	public void validate()
	{
		try{
			LetterOfCreditService letterOfCreditService =  (LetterOfCreditService)  ApplicationContextProvider.getBean("letterOfCreditService");
			if(StringUtils.isBlank(lcCanonicalDto.getRepair()) && StringUtils.isEmpty(lcCanonicalDto.getRepair())){
				if(letterOfCreditService.isLcNumberExist(lcCanonicalDto.getLcNumber())==false)
				{
					addFieldError("LcNumber","Lc Number Not Available In DataBase");
				
				}
			}
				if(lcCanonicalDto.getLcNumber().startsWith("/")){
					 addFieldError("lcNumber", "Lc Number must not start with /");
				}else if(lcCanonicalDto.getLcNumber().endsWith("/")){
					addFieldError("lcNumber", "Lc Number must not End with /");;
				}else if(lcCanonicalDto.getLcNumber().contains("//")){
					addFieldError("lcNumber", "Lc Number must not contain two consecutive slashes '//'");
				}
				
				if(StringUtils.isNotBlank(lcCanonicalDto.getNegotiatingBankCode()) && StringUtils.isNotEmpty(lcCanonicalDto.getNegotiatingBankCode()) && lcCanonicalDto.getNegotiatingBankCode()!=null){
					if(letterOfCreditService.checkIFSC(lcCanonicalDto.getNegotiatingBankCode())==false){
						addFieldError("negotiatingBankCode", "Negotiating Bank Code Is Not Available In System");
					}
				}
		}catch (Exception e) {
			
		}
	}
}
