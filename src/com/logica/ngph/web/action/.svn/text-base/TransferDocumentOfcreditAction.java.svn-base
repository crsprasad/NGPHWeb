package com.logica.ngph.web.action;


import java.sql.Clob;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
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

public class TransferDocumentOfcreditAction extends ActionSupport implements ModelDriven<LCCanonicalDto>,SessionAware{
	
	LCCanonicalDto lcCanonicalDto = new LCCanonicalDto();
	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(TransferDocumentOfcreditAction.class);
	private Map<String, Object> session;
	private String lcNumber;
	private String hiddenTxnRef;
	private String txnRef;
	private String saveAction;
	private String checkForSubmit;
	private boolean validUserToApprove;
	private String msgStatusForLcFetch;
	public String getMsgStatusForLcFetch() {
		return msgStatusForLcFetch;
	}

	public void setMsgStatusForLcFetch(String msgStatusForLcFetch) {
		this.msgStatusForLcFetch = msgStatusForLcFetch;
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

	
	private String flagForScreen;
	private String flagMarked;
	private List<LCCanonicalDto> searchList;
	
	public List<LCCanonicalDto> getSearchList() {
		return searchList;
	}

	public void setSearchList(List<LCCanonicalDto> searchList) {
		this.searchList = searchList;
	}

	public String getLcNumber() {
		return lcNumber;
	}

	public void setLcNumber(String lcNumber) {
		this.lcNumber = lcNumber;
	}
	public LCCanonicalDto getModel() {
		
		return lcCanonicalDto;
	}
	public Map<String, Object> getSession() {
		return session;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
	static List<LcCommodity> gridList = new ArrayList<LcCommodity>();
	static int count=1;
	@SkipValidation
	public String displayTransferDocumentaryCreditScreen()
	{
		try{
			gridList=new ArrayList<LcCommodity>();
			session.remove("bgCommoditiesList");
			count=0;
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
	private String msgDirection;
	public String getMsgDirection() {
		return msgDirection;
	}

	public void setMsgDirection(String msgDirection) {
		this.msgDirection = msgDirection;
	}

	@SkipValidation
	public String populatedTransferDocumentaryContent()
	{
		try{
			System.out.println("getMsgStatusForLcFetch() : -"+getMsgStatusForLcFetch());
			LcOpenService lcOpenService = (LcOpenService) ApplicationContextProvider.getBean("lcOpenService");
			setSearchList(lcOpenService.getTranferDocumentaryData(getLcNumber(),getMsgStatusForLcFetch(),getMsgDirection()));
			//setFlagForScreen("LcOpen");
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
	public String displayDocumentaryCreditData()
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
					
					setBgCommoditiesList(lcOpenService.getCommodityDetails(lcnumber));
					gridList = lcOpenService.getCommodityDetails(lcnumber);
					count = gridList.size();	
		
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
	
	public String getTransferDocumentaryCreditSentForApproval()
	{
		
		try{
			logger.info("getTransferDocumentaryCreditSentForApproval Is Called In TransferDocumentOfcreditAction");
			String txnKey="";
			PendingAuthorizationService pendingAuthorizationService = (PendingAuthorizationService)ApplicationContextProvider.getBean("pendingAuthorizationService");
			EventLogging eventLogging = (EventLogging)ApplicationContextProvider.getBean("eventLogging");
			String userId = (String)session.get(WebConstants.CONSTANT_USER_NAME);
			if(StringUtils.isNotBlank(lcCanonicalDto.getRepair()) && StringUtils.isNotEmpty(lcCanonicalDto.getRepair())){
				PaymentMessageService paymentMessageService =(PaymentMessageService)ApplicationContextProvider.getBean("paymentMessageService");
				paymentMessageService.changeMsgStatus2to99(lcCanonicalDto.getMsgRef());
				eventLogging.doEventLogging(userId," Transfer Document Of Credit ",ConstantUtil.EVENTID_TRANSFER_DOCUMENT+ConstantUtil.EVENTID_REPAIR_SUBMIT,ConstantUtil.EVENTLOGGINGCOMMENTSUBMIT+" "+ ConstantUtil.EVENTLOGGINGCOMMENTREAPIR,lcCanonicalDto.getLcNumber(),lcCanonicalDto.getMsgRef());
			}else
			{
				eventLogging.doEventLogging(userId," Transfer Document Of Credit ",ConstantUtil.EVENTID_TRANSFER_DOCUMENT+ConstantUtil.EVENTID_SUBMIT,ConstantUtil.EVENTLOGGINGCOMMENTSUBMIT,lcCanonicalDto.getLcNumber(),lcCanonicalDto.getMsgRef());
			}
			txnKey = pendingAuthorizationService.getTranRef(new SerializeManager<LCCanonicalDto>().serializeObject((String)session.get(WebConstants.CONSTANT_USER_NAME), lcCanonicalDto),"Transfer Documentary Credit",userId);
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
	
	public String getObjectForTransferDocumentaryCredit()
	{
		try{		
			System.out.println(""+lcCanonicalDto.getLcType());
			PendingAuthorizationService pendingAuthorizationService = (PendingAuthorizationService)ApplicationContextProvider.getBean("pendingAuthorizationService");
			LetterOfCreditService letterOfCreditService =  (LetterOfCreditService)  ApplicationContextProvider.getBean("letterOfCreditService");
			String userId = (String)session.get(WebConstants.CONSTANT_USER_NAME);
			EventLogging eventLogging = (EventLogging)ApplicationContextProvider.getBean("eventLogging");
			if(getSaveAction().equals("Approve")){
			String returnVAlue= letterOfCreditService.saveLC(lcCanonicalDto,(List<LcCommodity>)session.get("bgCommoditiesList"),"TransferDocumentaryCredit",userId,lcCanonicalDto.getRepair());
			
			
			if(returnVAlue!=null && !returnVAlue.equals("")){
				pendingAuthorizationService.changeStatus(getSaveAction(), getHiddenTxnRef());
				if(StringUtils.isBlank(lcCanonicalDto.getRepair()) && StringUtils.isEmpty(lcCanonicalDto.getRepair())){
					eventLogging.doEventLogging(userId," Transfer Document Of Credit ",ConstantUtil.EVENTID_TRANSFER_DOCUMENT+ConstantUtil.EVENTID_APPROVE,ConstantUtil.EVENTLOGGINGCOMMENTAPPROVAL,lcCanonicalDto.getLcNumber(),lcCanonicalDto.getMsgRef());
				}else
				{
					eventLogging.doEventLogging(userId," Transfer Document Of Credit ",ConstantUtil.EVENTID_TRANSFER_DOCUMENT + ConstantUtil.EVENTID_REPAIR_APPROVE,ConstantUtil.EVENTLOGGINGCOMMENTAPPROVAL+" "+ConstantUtil.EVENTLOGGINGCOMMENTREAPIR,lcCanonicalDto.getLcNumber(),lcCanonicalDto.getMsgRef());
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
					eventLogging.doEventLogging(userId," Transfer Document Of Credit ",ConstantUtil.EVENTID_TRANSFER_DOCUMENT+ConstantUtil.EVENTID_REPAIR_REJECT,ConstantUtil.EVENTLOGGINGCOMMENTREJECT+" "+ConstantUtil.EVENTLOGGINGCOMMENTREAPIR,lcCanonicalDto.getLcNumber(),lcCanonicalDto.getMsgRef() );
				}else
					eventLogging.doEventLogging(userId," Transfer Document Of Credit ",ConstantUtil.EVENTID_TRANSFER_DOCUMENT+ConstantUtil.EVENTID_REJECT,ConstantUtil.EVENTLOGGINGCOMMENTREJECT,lcCanonicalDto.getLcNumber(),lcCanonicalDto.getMsgRef());
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
	private List<LcCommodity> bgCommoditiesList = new ArrayList<LcCommodity>();
	
	public List<LcCommodity> getBgCommoditiesList() {
		return bgCommoditiesList;
	}

	public void setBgCommoditiesList(List<LcCommodity> bgCommoditiesList) {
		this.bgCommoditiesList = bgCommoditiesList;
		this.session.put("bgCommoditiesList", bgCommoditiesList);
	}
	@SkipValidation
	public String getTransferDocumentaryCreditAuthorization()
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
		setHiddenTxnRef(getTxnRef());
		for(int i=0;i<mulDataList.size();i++)
		{
			Clob list=(Clob) mulDataList.get(i);
			String[] tempString;
			tempString = list.getSubString(1, (int) list.length()).split("~");
			LcCommodity lcCommodity = new LcCommodity();
			lcCommodity.setLcId(tempString[0]);
			lcCommodity.setLcCommodity(tempString[1]);
			lcCommodity.setMsgRef(tempString[2]);
			temportList.add(lcCommodity);
		}
		setBgCommoditiesList(temportList);
	
		
		
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
	
	public void validate()
	{
		try{
		LetterOfCreditService letterOfCreditService =  (LetterOfCreditService)  ApplicationContextProvider.getBean("letterOfCreditService");
		String flag = letterOfCreditService.getLcOpenFlagForInsertOrUpdate();
		
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
		
		
		if(StringUtils.isBlank(lcCanonicalDto.getRepair()) && StringUtils.isEmpty(lcCanonicalDto.getRepair()))
		{
			if(lcCanonicalDto.getLcExpiryDate()!=null){
				if (lcCanonicalDto.getLcExpiryDate().before(getCurrentTime())){
				      addFieldError("lcExpiryDate", "Lc Expiry Date Should Always Be Greater Than Today's date.");
				   }	
			}
			if(lcCanonicalDto.getLatestDateofShipment()!=null)
			{
				if(lcCanonicalDto.getLatestDateofShipment().before(getCurrentTime()) || lcCanonicalDto.getLatestDateofShipment().after(lcCanonicalDto.getLcExpiryDate())){
					
					 addFieldError("latestDateofShipment", "latest Date of Shipment Should Always Be Greater Than Today's date and Smaller that LC Expiry date.");
				}
			}
		}
		
		
		
		if(lcCanonicalDto.getLcNumber().startsWith("/")){
			 addFieldError("lcNumber", "Lc Number must not start with /");
		}else if(lcCanonicalDto.getLcNumber().endsWith("/")){
			addFieldError("lcNumber", "Lc Number must not End with /");;
		}else if(lcCanonicalDto.getLcNumber().contains("//")){
			addFieldError("lcNumber", "Lc Number must not contain two consecutive slashes '//'");
		}
		//LetterOfCreditService letterOfCreditService =  (LetterOfCreditService)  ApplicationContextProvider.getBean("letterOfCreditService");
		if(StringUtils.isNotBlank(lcCanonicalDto.getApplicantBankCode()) && StringUtils.isNotEmpty(lcCanonicalDto.getApplicantBankCode()) && lcCanonicalDto.getApplicantBankCode()!=null){
			if(letterOfCreditService.checkIFSC(lcCanonicalDto.getApplicantBankCode())==false){
				addFieldError("applicantBankCode", "Applicant BankCode Is Not Available In System");
			}
		}
		
		if(StringUtils.isNotBlank(lcCanonicalDto.getAdvisingBank()) && StringUtils.isNotEmpty(lcCanonicalDto.getAdvisingBank()) && lcCanonicalDto.getAdvisingBank()!=null){
			if(letterOfCreditService.checkIFSC(lcCanonicalDto.getAdvisingBank())==false){
				addFieldError("advisingBank", "Advising BankCode Is Not Available In System");
			}
		}
		if(StringUtils.isNotBlank(lcCanonicalDto.getAdviseThroughBankCode()) && StringUtils.isNotEmpty(lcCanonicalDto.getAdviseThroughBankCode()) && lcCanonicalDto.getAdviseThroughBankCode()!=null){
			if(letterOfCreditService.checkIFSC(lcCanonicalDto.getAdviseThroughBankCode())==false){
				addFieldError("adviseThroughBankCode", "Advise Through BankCode Is Not Available In System");
			}
		}
		
		if(StringUtils.isNotBlank(lcCanonicalDto.getAuthorisedBankCode()) && StringUtils.isNotEmpty(lcCanonicalDto.getAuthorisedBankCode()) && lcCanonicalDto.getAuthorisedBankCode()!=null){
			if(letterOfCreditService.checkIFSC(lcCanonicalDto.getAuthorisedBankCode())==false){
				addFieldError("adviseThroughBankCode", "Authorized Bank Code Is Not Available In System");
			}
		}
		if(StringUtils.isNotBlank(lcCanonicalDto.getDraweeBankCode()) && StringUtils.isNotEmpty(lcCanonicalDto.getDraweeBankCode()) && lcCanonicalDto.getDraweeBankCode()!=null){
			if(letterOfCreditService.checkIFSC(lcCanonicalDto.getDraweeBankCode())==false){
				addFieldError("draweeBankCode", "Drawee BankCode Is Not Available In System");
			}
		}
		
		if(StringUtils.isNotBlank(lcCanonicalDto.getReimbursingBankCode()) && StringUtils.isNotEmpty(lcCanonicalDto.getReimbursingBankCode()) && lcCanonicalDto.getReimbursingBankCode()!=null){
			if(letterOfCreditService.checkIFSC(lcCanonicalDto.getReimbursingBankCode())==false){
				addFieldError("reimbursingBankCode", "Reimbursing BankCode Is Not Available In System");
			}
		}
		
		
		if((lcCanonicalDto.getPositiveTolerance()!=null  && !lcCanonicalDto.getPositiveTolerance().equals("") && StringUtils.isNotBlank(lcCanonicalDto.getPositiveTolerance())&& StringUtils.isNotEmpty(lcCanonicalDto.getPositiveTolerance()))){
			if(lcCanonicalDto.getNegativeTolerance()==null || lcCanonicalDto.getNegativeTolerance().equals("") || StringUtils.isEmpty(lcCanonicalDto.getNegativeTolerance()) || StringUtils.isBlank(lcCanonicalDto.getNegativeTolerance())){
				addFieldError("negativeTolerance", "Negative Tolerance Is Required");
			}
		}
		if((lcCanonicalDto.getNegativeTolerance()!=null && !lcCanonicalDto.getNegativeTolerance().equals("") && StringUtils.isNotBlank(lcCanonicalDto.getNegativeTolerance())&& StringUtils.isNotEmpty(lcCanonicalDto.getNegativeTolerance()))){
			if(lcCanonicalDto.getPositiveTolerance()==null || lcCanonicalDto.getPositiveTolerance().equals("") || StringUtils.isEmpty(lcCanonicalDto.getPositiveTolerance()) || StringUtils.isBlank(lcCanonicalDto.getPositiveTolerance())){
				addFieldError("positiveTolerance", "Positive Tolerance Is Required");
			}
		}
		if(StringUtils.isNotBlank(lcCanonicalDto.getAuthorisationMode()))
		{
			
			if((StringUtils.isEmpty(lcCanonicalDto.getAuthorisedAndAddress()) && StringUtils.isBlank(lcCanonicalDto.getAuthorisedAndAddress()))&&(StringUtils.isEmpty(lcCanonicalDto.getAuthorisedBankCode()) && StringUtils.isBlank(lcCanonicalDto.getAuthorisedBankCode())))
			{
				
				addFieldError("authorisedAndAddress", "Either Auhtorised Name And Address Or Auhtorised Bank Code Must Be Present");
			}
		}
		
		
			if(StringUtils.isNotBlank(lcCanonicalDto.getApplicableNarrative()))
			{
				if(StringUtils.isEmpty(lcCanonicalDto.getApplicableRule())){
					addFieldError("applicableRule", "Appicable Rule Is Required");
				}
				else if(!lcCanonicalDto.getApplicableRule().equals("OTHR"))
				{
					addFieldError("applicableRule", "Appicable Narrative Is not Required");
				}
			
		}
			String issuingDetails =lcCanonicalDto.getIssuingBankCode()+lcCanonicalDto.getIssuingBankPID()+lcCanonicalDto.getIssunigBankNameAndAddress(); 
		if(StringUtils.isNotBlank(issuingDetails) && StringUtils.isNotEmpty(issuingDetails))
		{
			if(StringUtils.isNotBlank(lcCanonicalDto.getNonIssuingBank()) && StringUtils.isNotEmpty(lcCanonicalDto.getNonIssuingBank())){
				addFieldError("nonIssuingBank", "NonIssuingBank Is Not Required");
			}
		}else if(StringUtils.isNotBlank(lcCanonicalDto.getNonIssuingBank()) && StringUtils.isNotEmpty(lcCanonicalDto.getNonIssuingBank()))
		{
			if(StringUtils.isNotBlank(issuingDetails) && StringUtils.isNotEmpty(issuingDetails)){
				addFieldError("nonIssuingBank", "IssuingBankPid/Code/Name And Address Is Not Required");
			}
		}
		
		if(StringUtils.isNotEmpty(lcCanonicalDto.getLcType()))
		{
			if(lcCanonicalDto.getLcType().equals("IRREVOC TRANS STANDBY") ||lcCanonicalDto.getLcType().equals("IRREVOCABLE") || lcCanonicalDto.getLcType().equals("REVOCABLE"))
			{}else
			{
				addFieldError("lcType", "Message Cannot Be Transferable");
			}
		}
		networkValdation();
		validateTolerance();
		}catch (Exception e) {
			addActionError("Unable To process.");
		}
	}
	public void validateTolerance()
	{
		String tolerance = null;
		String mxdCrdAmt = null;
		tolerance = lcCanonicalDto.getPositiveTolerance()+lcCanonicalDto.getNegativeTolerance();
		mxdCrdAmt  = lcCanonicalDto.getMaximumCreditAmount();
		if(StringUtils.isNotBlank(tolerance) && StringUtils.isNotEmpty(tolerance) && tolerance!=null)
		{
			if(StringUtils.isNotBlank(mxdCrdAmt) && StringUtils.isNotEmpty(mxdCrdAmt) && mxdCrdAmt!=null)
			{
				addFieldError("maximumCreditAmount", "maximumCreditAmount must not present");
			}
		}
		if(StringUtils.isNotBlank(mxdCrdAmt) && StringUtils.isNotEmpty(mxdCrdAmt) && mxdCrdAmt!=null)
		{
			if(StringUtils.isNotBlank(tolerance) && StringUtils.isNotEmpty(tolerance) && tolerance!=null)
			{
				addFieldError("positiveTolerance", "positiveTolerance/negative Tolerance must not present");
			}
		}
		
		
	}
	public void networkValdation()
	{
		checkLcDraftsAtAndLCDrwbnkRule();		
		if(StringUtils.isNotBlank(lcCanonicalDto.getShipmentPeriod()) && StringUtils.isNotEmpty(lcCanonicalDto.getShipmentPeriod()) && lcCanonicalDto.getShipmentPeriod()!=null)
		{
			if(lcCanonicalDto.getLatestDateofShipment()!=null)
			{
				addFieldError("shipmentPeriod", "Either Of the fields Last Shipment Date Or Shipment Period should only be present");
			}
		}
		else if(lcCanonicalDto.getLatestDateofShipment()!=null)
		{
			if(StringUtils.isNotBlank(lcCanonicalDto.getShipmentPeriod()) && StringUtils.isNotEmpty(lcCanonicalDto.getShipmentPeriod()) && lcCanonicalDto.getShipmentPeriod()!=null)
			{
				addFieldError("shipmentPeriod", "Either Of the fields Last Shipment Date Or Shipment Period should only be present");
			}
		}
		
	}
	private void checkLcDraftsAtAndLCDrwbnkRule()
	{
		logger.info("checkLcDraftsAtAndLCDrwbnkRule START");
		boolean is42Cprsnt = false;
		boolean is42aprsnt = false;
		boolean is42Mprsnt = false;
		boolean is42Pprsnt = false;
		
		if (StringUtils.isNotBlank(lcCanonicalDto.getDraftsAt())) 
		{
			is42Cprsnt = true;
		}
		if(StringUtils.isNotBlank(lcCanonicalDto.getDraweeBankpartyidentifier()+lcCanonicalDto.getDraweeBankAccount()+ lcCanonicalDto.getDraweeBankNameAddress()+ lcCanonicalDto.getDraweeBankCode()))
		{
			is42aprsnt = true;
		}
		if(StringUtils.isNotBlank(lcCanonicalDto.getMixedPaymentDetails()))
		{
			is42Mprsnt = true;
		}
		if(StringUtils.isNotBlank(lcCanonicalDto.getDeferredPaymentDetails()))
		{
			is42Pprsnt = true;
		}
		
		/*
		 * check if both the fields are not present. Since these are optional fields it may not come in message
		 * In that case the validation will fail.
		 * The rule says if one is present only then second should be present
		 * 
		 * Case 2
		 */
		if(StringUtils.isNotBlank(lcCanonicalDto.getDraftsAt()) || StringUtils.isNotBlank(lcCanonicalDto.getDraweeBankpartyidentifier()+lcCanonicalDto.getDraweeBankAccount()+ lcCanonicalDto.getDraweeBankNameAddress()+ lcCanonicalDto.getDraweeBankCode()))
		{
			if(is42Cprsnt ==false || is42aprsnt ==false)
			{
				addFieldError("DraftsAt", "If Drafts At Is Present Then Drawee Bank Details Should Also Be Present");
				logger.info("checkLcDraftsAtAndLCDrwbnkRule Validation Failed");
			}
			
		}
		int count =0;
		//Case 3
		if(StringUtils.isNotBlank(lcCanonicalDto.getMixedPaymentDetails()))
		{
			 if(is42Pprsnt ==true )
				{
				 count =1;
					addFieldError("DraftsAt", "MixedPaymentDetails Should Not Be Present");
					logger.info("checkLcDraftsAtAndLCDrwbnkRule Validation Failed");
				}
			else if(is42Cprsnt ==true && is42aprsnt ==true && is42Mprsnt==true)
			{
				addFieldError("DraftsAt", "Either Of Drawee Bank details or MixedPaymentDetails Should Be Present");
				logger.info("checkLcDraftsAtAndLCDrwbnkRule Validation Failed");
				count =1;
			}
			
		}	
		
		if(StringUtils.isNotBlank(lcCanonicalDto.getDeferredPaymentDetails()))
		{
			if(is42Mprsnt ==true && count==0)
			{
				addFieldError("DraftsAt", "Deffered PaymentDetails Should Not Be Present");
				logger.info("checkLcDraftsAtAndLCDrwbnkRule Validation Failed");
			}
			else if(((is42Cprsnt ==true && is42aprsnt ==true && is42Pprsnt==true) || (is42Mprsnt ==true && is42Pprsnt ==true)) && count ==0)
			{
				addFieldError("DraftsAt", "Either of Drawee Bank details Or DeferredPaymentDetails Should Be Present");
				logger.info("checkLcDraftsAtAndLCDrwbnkRule Validation Failed");
			}
			 
		}
		
	
		logger.info("checkLcDraftsAtAndLCDrwbnkRule END");
	}
	private Timestamp getCurrentTime(){
		java.util.Date 	str_date = Calendar.getInstance().getTime();
		java.sql.Timestamp timeStampDate = new Timestamp(str_date.getTime());
		return timeStampDate;
	}
	
	@SkipValidation
	public String viewTransferDocumentaryPayment()
	{
		try{
			displayTransferDocumentaryCreditScreen();
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
			
		setBgCommoditiesList(lcOpenService.getCommodityDetails(canonicalDto.getLcNumber()));
		
		
	}
}
