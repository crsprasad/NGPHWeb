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

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.jboss.util.Base64;
import org.springframework.context.ApplicationContextException;

import com.logica.ngph.common.ConstantUtil;
import com.logica.ngph.dtos.LCCanonicalDto;
import com.logica.ngph.service.AdviceLCPaymentService;
import com.logica.ngph.service.LetterOfCreditService;
import com.logica.ngph.service.PaymentMessageService;
import com.logica.ngph.service.PendingAuthorizationService;
import com.logica.ngph.web.utils.ApplicationContextProvider;
import com.logica.ngph.web.utils.EventLogging;
import com.logica.ngph.web.utils.WebConstants;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class AuthorisationToPayAction extends ActionSupport implements ModelDriven<LCCanonicalDto>,SessionAware {
	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(AuthorisationToPayAction.class);
	private Map<String, Object> session;
	private String saveAction;
	private String checkForSubmit;
	private boolean validUserToApprove;
	private String hiddenTxnRef;
	private String txnRef;
	LCCanonicalDto lcCanonicalDto =new LCCanonicalDto();
	private String lcNumber;
	public String getLcNumber() {
		return lcNumber;
	}
	public void setLcNumber(String lcNumber) {
		this.lcNumber = lcNumber;
	}
	public void setMobel(LCCanonicalDto connionicalDto)
	{
		 this.lcCanonicalDto=connionicalDto;
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
	public LCCanonicalDto getLcCanonicalDto() {
		return lcCanonicalDto;
	}
	public void setLcCanonicalDto(LCCanonicalDto lcCanonicalDto) {
		this.lcCanonicalDto = lcCanonicalDto;
	}

	
	@SkipValidation
	public String displayAuthoriToPayData()
	{
		try{
			String lcnumber = lcCanonicalDto.getLcNumber();
			LCCanonicalDto canonicalDto = null;
		  boolean isValidLcNumber = false;
		  String status="752";
		  isValidLcNumber=validateLcNumber(lcnumber);
			if(isValidLcNumber){
				
			AdviceLCPaymentService adviceLCPaymentService = (AdviceLCPaymentService) ApplicationContextProvider.getBean("adviceLCPaymentService");
			 canonicalDto=adviceLCPaymentService.getAuthriseLCScreenData(lcnumber,status);
			 
			 session.put("ScreenData", canonicalDto);
			
			 lcCanonicalDto.setLcNumber(canonicalDto.getLcNumber());
			 lcCanonicalDto.setPresentingBanksReference(canonicalDto.getPresentingBanksReference());
			 lcCanonicalDto.setLcNetAmtClaimed(canonicalDto.getLcNetAmtClaimed());
			 lcCanonicalDto.setAmendmentDate(canonicalDto.getAmendmentDate());
			 lcCanonicalDto.setLcCurrency(canonicalDto.getLcCurrency());
			 lcCanonicalDto.setLcPrevAdvRef(canonicalDto.getLcNumber());
			 lcCanonicalDto.setCreditAmount(canonicalDto.getCreditAmount());
			 lcCanonicalDto.setChargeDetails(canonicalDto.getChargeDetails());
			 lcCanonicalDto.setMsgCurrency(canonicalDto.getLcCurrency());
			 lcCanonicalDto.setMsgValueDate(canonicalDto.getMsgValueDate());
			 lcCanonicalDto.setSendersCorrespondentPartyIdentifier(canonicalDto.getSendersCorrespondentPartyIdentifier());
			 lcCanonicalDto.setSenderCorrespontAcount(canonicalDto.getSenderCorrespontAcount());
			 lcCanonicalDto.setSendersCorrespondentCode(canonicalDto.getSendersCorrespondentCode());
			 lcCanonicalDto.setSendersCorrespondentLocation(canonicalDto.getSendersCorrespondentLocation());
			 lcCanonicalDto.setSendersCorrespondentNameandAddress(canonicalDto.getSendersCorrespondentNameandAddress());
			 lcCanonicalDto.setReceiversCorrespondentPartyIdentifier(canonicalDto.getReceiversCorrespondentPartyIdentifier());
			 lcCanonicalDto.setReceiverCorrespontAcount(canonicalDto.getReceiverCorrespontAcount());
			 lcCanonicalDto.setReceiversCorrespondentCode(canonicalDto.getReceiversCorrespondentCode());
			 lcCanonicalDto.setReceiversCorrespondentLocation(canonicalDto.getReceiversCorrespondentLocation());
			 lcCanonicalDto.setReceiversCorrespondentNameandAddress(canonicalDto.getReceiversCorrespondentNameandAddress());
		
			return "success";
		}else{
			addFieldError("LcNumber", "LcNumber, field must not start or end with a slash '/' and must not contain two consecutive slashes '//'");
			
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
		addFieldError("LcNumber", "Unable Do Perform Lc Number Not In DataBase");
	
		return "input";
	}
	private boolean validateLcNumber(String lcNumber){
		boolean result = true;
		if(lcNumber.startsWith("/")){
			 result=false;
		}else if(lcNumber.endsWith("/")){
			 result=false;
		}else if(lcNumber.contains("//")){
			result = false;
		}
		
		return result;
	}
	
	public String getAuthorisToPaySubmit()
	{
		try{
				String txnKey="";
				PendingAuthorizationService pendingAuthorizationService = (PendingAuthorizationService)ApplicationContextProvider.getBean("pendingAuthorizationService");
				EventLogging eventLogging = (EventLogging)ApplicationContextProvider.getBean("eventLogging");
				String userId = (String)session.get(WebConstants.CONSTANT_USER_NAME);
				if(StringUtils.isBlank(lcCanonicalDto.getRepair()) && StringUtils.isEmpty(lcCanonicalDto.getRepair())){
					if(!getHiddenTxnRef().isEmpty())
					{
						pendingAuthorizationService.updateRejectStatusToPending(getHiddenTxnRef());
					}else
					{
						txnKey = pendingAuthorizationService.getTranRef(serializeObject(),"Authorisation To Pay",userId);
						eventLogging.doEventLogging(userId, "Authorisation To Pay", ConstantUtil.EVENTID_LC_AUTHORIZE_PAY+ConstantUtil.EVENTID_SUBMIT, ConstantUtil.EVENTLOGGINGCOMMENTSUBMIT,lcCanonicalDto.getLcNumber(),lcCanonicalDto.getMsgRef());
					}
					
				}else{
					PaymentMessageService paymentMessageService =(PaymentMessageService)ApplicationContextProvider.getBean("paymentMessageService");
					paymentMessageService.changeMsgStatus2to99(lcCanonicalDto.getMsgRef());
					txnKey = pendingAuthorizationService.getTranRef(serializeObject(),"Authorisation To Pay",userId);
					eventLogging.doEventLogging(userId, "Authorisation To Pay", ConstantUtil.EVENTID_LC_AUTHORIZE_PAY+ConstantUtil.EVENTID_REPAIR_SUBMIT, ConstantUtil.EVENTLOGGINGCOMMENTSUBMIT+" "+ConstantUtil.EVENTLOGGINGCOMMENTREAPIR,lcCanonicalDto.getLcNumber(),lcCanonicalDto.getMsgRef());
				}
				session.remove("ScreenData");
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
			session.remove("ScreenData");
			return "input";				
	}
	private LCCanonicalDto getScreenDataFromSession(){
		LCCanonicalDto authorisetoPayDto= (LCCanonicalDto)session.get("ScreenData");
		
		//authorisetoPayDto.setLcExpiryDate(lcCanonicalDto.getLcExpiryDate());
		authorisetoPayDto.setLcNumber(lcCanonicalDto.getLcNumber());
		authorisetoPayDto.setPresentingBanksReference(lcCanonicalDto.getPresentingBanksReference());
		authorisetoPayDto.setLcNetAmtClaimed(lcCanonicalDto.getLcNetAmtClaimed());
		authorisetoPayDto.setAmendmentDate(lcCanonicalDto.getAmendmentDate());
		authorisetoPayDto.setLcCurrency(lcCanonicalDto.getLcCurrency());
		authorisetoPayDto.setMsgCurrency(lcCanonicalDto.getMsgCurrency());
		authorisetoPayDto.setLcPrevAdvRef(lcCanonicalDto.getLcPrevAdvRef());
		 authorisetoPayDto.setCreditAmount(lcCanonicalDto.getCreditAmount());
		 authorisetoPayDto.setChargeDetails(lcCanonicalDto.getChargeDetails());
		 authorisetoPayDto.setMsgValueDate(lcCanonicalDto.getMsgValueDate());
		 authorisetoPayDto.setSendersCorrespondentPartyIdentifier(lcCanonicalDto.getSendersCorrespondentPartyIdentifier());
		 authorisetoPayDto.setSenderCorrespontAcount(lcCanonicalDto.getSenderCorrespontAcount());
		 authorisetoPayDto.setSendersCorrespondentCode(lcCanonicalDto.getSendersCorrespondentCode());
		 authorisetoPayDto.setSendersCorrespondentLocation(lcCanonicalDto.getSendersCorrespondentLocation());
		 authorisetoPayDto.setSendersCorrespondentNameandAddress(lcCanonicalDto.getSendersCorrespondentNameandAddress());
		 authorisetoPayDto.setReceiversCorrespondentPartyIdentifier(lcCanonicalDto.getReceiversCorrespondentPartyIdentifier());
		 authorisetoPayDto.setReceiverCorrespontAcount(lcCanonicalDto.getReceiverCorrespontAcount());
		 authorisetoPayDto.setReceiversCorrespondentCode(lcCanonicalDto.getReceiversCorrespondentCode());
		 authorisetoPayDto.setReceiversCorrespondentLocation(lcCanonicalDto.getReceiversCorrespondentLocation());
		 authorisetoPayDto.setReceiversCorrespondentNameandAddress(lcCanonicalDto.getReceiversCorrespondentNameandAddress());
		 authorisetoPayDto.setSendertoReceiverInformation(lcCanonicalDto.getSendertoReceiverInformation());

		return authorisetoPayDto;
	}
	public String serializeObject()
	{
		LCCanonicalDto canonicalDto = new LCCanonicalDto();
		LCCanonicalDto canonicalDto1 = new LCCanonicalDto();
		try{
			String userId = (String)session.get(WebConstants.CONSTANT_USER_NAME);
			canonicalDto1 = getScreenDataFromSession();
			canonicalDto = canonicalDto1;
			System.out.print("LC NUMBER :"+lcCanonicalDto.getLcNumber());
			String fileName ="serial_"+userId+".ser";
		FileOutputStream fos = new FileOutputStream(fileName);
        OutputStream buffer = new BufferedOutputStream( fos );
        ObjectOutputStream oos = new ObjectOutputStream(buffer);
        oos.writeObject(canonicalDto);
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
	public LCCanonicalDto getSerializedObject(String objectString)
	{
		try{
			
			byte[] decoded = Base64.decode(objectString);
            
            FileOutputStream foss = new FileOutputStream("targetUserObject.ser");
            foss.write(decoded);
            foss.close();
            LCCanonicalDto testDTO = null;
            
            FileInputStream fiss = new FileInputStream("targetUserObject.ser");
            BufferedInputStream bufferee = new BufferedInputStream( fiss );
            ObjectInputStream oiss = new ObjectInputStream(bufferee);
            testDTO = (LCCanonicalDto)oiss.readObject();
            oiss.close();
            System.out.println("object2: " + testDTO); 
            System.out.print("User(testDTO) :"+testDTO.getLcNumber());
          
            return testDTO;

		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	@SkipValidation
	public String getAuthoToPayAuthorization()
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
		LCCanonicalDto temp= getSerializedObject(tempScreenString);
		lcCanonicalDto.setLcNumber(temp.getLcNumber());
		lcCanonicalDto.setAcountID(temp.getAcountID());
		lcCanonicalDto.setLcExpiryDate(temp.getLcExpiryDate());
		lcCanonicalDto.setLcExpirePlace(temp.getLcExpirePlace());
		lcCanonicalDto.setNegotiatingBankPartyIdentifier(temp.getNegotiatingBankPartyIdentifier());
		lcCanonicalDto.setNegotiatingBankAccount(temp.getNegotiatingBankAccount());
		lcCanonicalDto.setNegotiatingBankCode(temp.getNegotiatingBankCode());
		lcCanonicalDto.setNegotiatingBankNameAndAddress(temp.getNegotiatingBankNameAndAddress());
		lcCanonicalDto.setBeneficiaryAccount(temp.getBeneficiaryAccount());
		lcCanonicalDto.setBeneficiaryNameAddress(temp.getBeneficiaryNameAddress());
		lcCanonicalDto.setPositiveTolerance(temp.getPositiveTolerance());
		 lcCanonicalDto.setNegativeTolerance(temp.getNegativeTolerance());
		 lcCanonicalDto.setMaximumCreditAmount(temp.getMaximumCreditAmount());
		 lcCanonicalDto.setAdditionalAmountsCovered(temp.getAdditionalAmountsCovered());
		 lcCanonicalDto.setAuthorisedBankCode(temp.getAuthorisedBankCode());
		 lcCanonicalDto.setAuthorisedAndAddress(temp.getAuthorisedAndAddress());
		 lcCanonicalDto.setAuthorisationMode(temp.getAuthorisationMode());
		 lcCanonicalDto.setDraftsAt(temp.getDraftsAt());
		 lcCanonicalDto.setDraweeBankpartyidentifier(temp.getDraweeBankpartyidentifier());
		 lcCanonicalDto.setDraweeBankAccount(temp.getDraweeBankAccount());
		 lcCanonicalDto.setDraweeBankCode(temp.getDraweeBankCode());
		 lcCanonicalDto.setDraweeBankNameAddress(temp.getDraweeBankNameAddress());
		 lcCanonicalDto.setMixedPaymentDetails(temp.getMixedPaymentDetails());
		 lcCanonicalDto.setDeferredPaymentDetails(temp.getDeferredPaymentDetails());
		 lcCanonicalDto.setReimbursingBanksCharges(temp.getReimbursingBanksCharges());
		 lcCanonicalDto.setOtherCharges(temp.getOtherCharges());
		 lcCanonicalDto.setSendertoReceiverInformation(temp.getSendertoReceiverInformation());
		 lcCanonicalDto.setMsgRef(temp.getMsgRef());
		 lcCanonicalDto.setCreditAmount(temp.getCreditAmount());
		 lcCanonicalDto.setLcType(temp.getLcType());
		 lcCanonicalDto.setLcCurrency(temp.getLcCurrency());
		 lcCanonicalDto.setAdvisingBank(temp.getAdvisingBank());
		 lcCanonicalDto.setLcAmount(temp.getLcAmount());
		 lcCanonicalDto.setApplicantNameAddress(temp.getApplicantNameAddress());
		 lcCanonicalDto.setBeneficiary(temp.getBeneficiary());			 
		 lcCanonicalDto.setIssueDate(temp.getIssueDate());
		 lcCanonicalDto.setNarrative(temp.getNarrative());
		 lcCanonicalDto.setNegotiatingBankPartyIdentifier(temp.getNegotiatingBankPartyIdentifier());
		 lcCanonicalDto.setApplicableRule(temp.getApplicableRule());
		 lcCanonicalDto.setApplicableNarrative(temp.getApplicableNarrative());		 
		 lcCanonicalDto.setAdditionalAmounts(temp.getAdditionalAmounts());	      
		 lcCanonicalDto.setInitialDispatchPlace(temp.getInitialDispatchPlace());
		 lcCanonicalDto.setGoodsDestination(temp.getGoodsDestination());
		 lcCanonicalDto.setLatestDateofShipment(temp.getLatestDateofShipment());
		 lcCanonicalDto.setShipmentPeriod(temp.getShipmentPeriod());
	        lcCanonicalDto.setShipmentTerms(temp.getShipmentTerms());	      
	        lcCanonicalDto.setPartialShipments(temp.getPartialShipments());
	        lcCanonicalDto.setTranshipment(temp.getTranshipment());
	        lcCanonicalDto.setDocumentsRequired(temp.getDocumentsRequired());
	        lcCanonicalDto.setAdditionalConditions(temp.getAdditionalConditions());
	        lcCanonicalDto.setChargeDetails(temp.getChargeDetails());
	        lcCanonicalDto.setPeriodforPresentation(temp.getPeriodforPresentation());
	        lcCanonicalDto.setConfirmationCode(temp.getConfirmationCode());
	        lcCanonicalDto.setInstructionstoPayingBank(temp.getInstructionstoPayingBank());		
	        lcCanonicalDto.setAdviseThroughBankpartyidentifier(temp.getAdviseThroughBankpartyidentifier());
	        lcCanonicalDto.setAdviseThroughBankCode(temp.getAdviseThroughBankCode());
	        lcCanonicalDto.setAdviseThroughBankLocation(temp.getAdviseThroughBankLocation());
	        lcCanonicalDto.setAdviseThroughBankAcc(temp.getAdviseThroughBankAcc());
	        lcCanonicalDto.setAdviseThroughBankName(temp.getAdviseThroughBankName());
	        lcCanonicalDto.setPartyIdentifier(temp.getPartyIdentifier());
	        lcCanonicalDto.setReimbursingBankCode(temp.getReimbursingBankCode());
			lcCanonicalDto.setReimbursingBankNameAddress(temp.getReimbursingBankNameAddress());
			lcCanonicalDto.setApplicantAccount(temp.getApplicantAccount());   
			lcCanonicalDto.setApplicantBankpartyidentifier(temp.getApplicantBankpartyidentifier());
			lcCanonicalDto.setApplicantBankCode(temp.getApplicantBankCode());
			lcCanonicalDto.setApplicantBankNameAddress(temp.getApplicantBankNameAddress()); 
			lcCanonicalDto.setPresentingBanksReference(temp.getPresentingBanksReference());
	        lcCanonicalDto.setTotalAmountClaimed(temp.getTotalAmountClaimed());
	        lcCanonicalDto.setPaidAmount(temp.getPaidAmount());		
	        lcCanonicalDto.setSendersCorrespondentPartyIdentifier(temp.getSendersCorrespondentPartyIdentifier());
	        lcCanonicalDto.setSenderCorrespontAcount(temp.getSenderCorrespontAcount());		
	        lcCanonicalDto.setSendersCorrespondentCode(temp.getSendersCorrespondentCode());
	        lcCanonicalDto.setSendersCorrespondentLocation(temp.getSendersCorrespondentLocation());
	        lcCanonicalDto.setSendersCorrespondentNameandAddress(temp.getSendersCorrespondentNameandAddress());
	        lcCanonicalDto.setReceiversCorrespondentPartyIdentifier(temp.getReceiversCorrespondentPartyIdentifier());
	        lcCanonicalDto.setReceiverCorrespontAcount(temp.getReceiverCorrespontAcount());		
	        lcCanonicalDto.setReceiversCorrespondentCode(temp.getReceiversCorrespondentCode());
	        lcCanonicalDto.setReceiversCorrespondentLocation(temp.getReceiversCorrespondentLocation());
	        lcCanonicalDto.setReceiversCorrespondentNameandAddress(temp.getReceiversCorrespondentNameandAddress());
	        lcCanonicalDto.setNetChargeAmount(temp.getNetChargeAmount());
	        lcCanonicalDto.setGoodsLoadingDispatchPlace(temp.getGoodsLoadingDispatchPlace());
	        lcCanonicalDto.setFinalDeliveryPlace(temp.getFinalDeliveryPlace());
	        lcCanonicalDto.setAmountPaidDate(temp.getAmountPaidDate());
	        lcCanonicalDto.setIncreaseAmendAmount(temp.getIncreaseAmendAmount());
	        lcCanonicalDto.setDecreaseAmendAmount(temp.getDecreaseAmendAmount());
	        lcCanonicalDto.setAmendmentDate(temp.getAmendmentDate());
	        lcCanonicalDto.setLcAmndmntNo(temp.getLcAmndmntNo());
	        lcCanonicalDto.setOldAmendExpiryDate(temp.getOldAmendExpiryDate());
	        lcCanonicalDto.setOldLcAmount(temp.getOldLcAmount());
	        lcCanonicalDto.setSeqNo(temp.getSeqNo());
	        lcCanonicalDto.setMsgHost(temp.getMsgHost());
	        lcCanonicalDto.setRepair(temp.getRepair());
	        lcCanonicalDto.setComment(temp.getComment());
	        lcCanonicalDto.setServiceID(temp.getServiceID());
	        lcCanonicalDto.setMesgIsReturn(temp.getMesgIsReturn());
	        lcCanonicalDto.setMsgPDECount(temp.getMsgPDECount());
	        lcCanonicalDto.setMsgGRPSeq(temp.getMsgGRPSeq());
	        lcCanonicalDto.setMsgValueDate(temp.getMsgValueDate());
	        lcCanonicalDto.setPymntAcceptedTime(temp.getPymntAcceptedTime());
	        lcCanonicalDto.setIssuingBankCode(temp.getIssuingBankCode());
	        lcCanonicalDto.setSenderBankReference(temp.getSenderBankReference());
	        lcCanonicalDto.setLcNetAmtClaimed(temp.getLcNetAmtClaimed());
	        lcCanonicalDto.setLcPrevAdvRef(temp.getLcPrevAdvRef());
	        lcCanonicalDto.setMsgCurrency(temp.getMsgCurrency());
	        session.put("APPROVE_DATA", lcCanonicalDto);
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
	public String getObjectForAuthoToPaySave()
	{
		try{
			LCCanonicalDto authoriseToPayDto = (LCCanonicalDto)session.get("APPROVE_DATA");
			String userId = (String)session.get(WebConstants.CONSTANT_USER_NAME);
			PendingAuthorizationService pendingAuthorizationService = (PendingAuthorizationService)ApplicationContextProvider.getBean("pendingAuthorizationService");
			EventLogging eventLogging = (EventLogging)ApplicationContextProvider.getBean("eventLogging");
			LetterOfCreditService letterOfCreditService =  (LetterOfCreditService)  ApplicationContextProvider.getBean("letterOfCreditService");
			if(getSaveAction().equals("Approve")){
				 String returnString =letterOfCreditService.saveLC(authoriseToPayDto,null,"AuthToPay",userId,authoriseToPayDto.getRepair());
				if(returnString!=null && !returnString.equals("") ){
					pendingAuthorizationService.changeStatus(getSaveAction(), getHiddenTxnRef());
					session.remove("APPROVE_DATA");
					if(StringUtils.isBlank(authoriseToPayDto.getRepair()) && StringUtils.isEmpty(authoriseToPayDto.getRepair())){
						eventLogging.doEventLogging(userId," Authorisation To Pay ",ConstantUtil.EVENTID_LC_AUTHORIZE_PAY+ConstantUtil.EVENTID_APPROVE,ConstantUtil.EVENTLOGGINGCOMMENTAPPROVAL,authoriseToPayDto.getLcNumber(),authoriseToPayDto.getMsgRef());
					}else{
						eventLogging.doEventLogging(userId,"Authorisation To Pay",ConstantUtil.EVENTID_LC_AUTHORIZE_PAY +ConstantUtil.EVENTID_REPAIR_APPROVE,ConstantUtil.EVENTLOGGINGCOMMENTAPPROVAL+" "+ConstantUtil.EVENTLOGGINGCOMMENTREAPIR,authoriseToPayDto.getLcNumber(),authoriseToPayDto.getMsgRef());
					}
					return "success";	
				}else{
					addActionError("Unable to perform the operation. Please try again");
					session.remove("APPROVE_DATA");
					return "input";
					}
				}else{
					if(StringUtils.isBlank(authoriseToPayDto.getRepair()) && StringUtils.isEmpty(authoriseToPayDto.getRepair())){
						pendingAuthorizationService.changeStatus(getSaveAction(), getHiddenTxnRef());
						eventLogging.doEventLogging(userId, "AuthToPay", ConstantUtil.EVENTID_LC_AUTHORIZE_PAY+ConstantUtil.EVENTID_REJECT, ConstantUtil.EVENTLOGGINGCOMMENTREJECT,authoriseToPayDto.getLcNumber(),authoriseToPayDto.getMsgRef());
					}else{
						PaymentMessageService paymentMessageService =(PaymentMessageService)ApplicationContextProvider.getBean("paymentMessageService");
						paymentMessageService.changeMsgStatus99to2(authoriseToPayDto.getMsgRef());
						pendingAuthorizationService.changeStatus(getSaveAction(), getHiddenTxnRef());
						eventLogging.doEventLogging(userId, "AuthToPay", ConstantUtil.EVENTID_LC_AUTHORIZE_PAY+ConstantUtil.EVENTID_REPAIR_REJECT, ConstantUtil.EVENTLOGGINGCOMMENTREJECT+" "+ConstantUtil.EVENTLOGGINGCOMMENTREAPIR,authoriseToPayDto.getLcNumber(),authoriseToPayDto.getMsgRef());
					}
					session.remove("APPROVE_DATA");
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
		session.remove("APPROVE_DATA");
		return "input";
	}
	
	@SkipValidation
	public String viewAuthorisationToPayRepair()
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
		
		LCCanonicalDto temp = obj;
		lcCanonicalDto.setLcNumber(temp.getLcNumber());
		lcCanonicalDto.setAcountID(temp.getAcountID());
		lcCanonicalDto.setLcExpiryDate(temp.getLcExpiryDate());
		 lcCanonicalDto.setLcExpirePlace(temp.getLcExpirePlace());
		 lcCanonicalDto.setNegotiatingBankPartyIdentifier(temp.getNegotiatingBankPartyIdentifier());
		 lcCanonicalDto.setNegotiatingBankAccount(temp.getNegotiatingBankAccount());
		 lcCanonicalDto.setNegotiatingBankCode(temp.getNegotiatingBankCode());
		 lcCanonicalDto.setNegotiatingBankNameAndAddress(temp.getNegotiatingBankNameAndAddress());
		 lcCanonicalDto.setBeneficiaryAccount(temp.getBeneficiaryAccount());
		 lcCanonicalDto.setBeneficiaryNameAddress(temp.getBeneficiaryNameAddress());
		 lcCanonicalDto.setPositiveTolerance(temp.getPositiveTolerance());
		 lcCanonicalDto.setNegativeTolerance(temp.getNegativeTolerance());
		 lcCanonicalDto.setMaximumCreditAmount(temp.getMaximumCreditAmount());
		 lcCanonicalDto.setAdditionalAmountsCovered(temp.getAdditionalAmountsCovered());
		 lcCanonicalDto.setAuthorisedBankCode(temp.getAuthorisedBankCode());
		 lcCanonicalDto.setAuthorisedAndAddress(temp.getAuthorisedAndAddress());
		 lcCanonicalDto.setAuthorisationMode(temp.getAuthorisationMode());
		 lcCanonicalDto.setDraftsAt(temp.getDraftsAt());
		 lcCanonicalDto.setDraweeBankpartyidentifier(temp.getDraweeBankpartyidentifier());
		 lcCanonicalDto.setDraweeBankAccount(temp.getDraweeBankAccount());
		 lcCanonicalDto.setDraweeBankCode(temp.getDraweeBankCode());
		 lcCanonicalDto.setDraweeBankNameAddress(temp.getDraweeBankNameAddress());
		 lcCanonicalDto.setMixedPaymentDetails(temp.getMixedPaymentDetails());
		 lcCanonicalDto.setDeferredPaymentDetails(temp.getDeferredPaymentDetails());
		 lcCanonicalDto.setReimbursingBanksCharges(temp.getReimbursingBanksCharges());
		 lcCanonicalDto.setOtherCharges(temp.getChargeDetails());
		 lcCanonicalDto.setSendertoReceiverInformation(temp.getSendertoReceiverInformation());
		 lcCanonicalDto.setMsgRef(temp.getMsgRef());
		 lcCanonicalDto.setCreditAmount(temp.getCreditAmount());
		 lcCanonicalDto.setLcType(temp.getLcType());
		 lcCanonicalDto.setLcCurrency(temp.getLcCurrency());
		 lcCanonicalDto.setAdvisingBank(temp.getAdvisingBank());
		 lcCanonicalDto.setLcAmount(temp.getLcAmount());
		 lcCanonicalDto.setApplicantNameAddress(temp.getApplicantNameAddress());
		 lcCanonicalDto.setBeneficiary(temp.getBeneficiary());			 
		 lcCanonicalDto.setIssueDate(temp.getIssueDate());
		 lcCanonicalDto.setNarrative(temp.getNarrative());
		 lcCanonicalDto.setNegotiatingBankPartyIdentifier(temp.getNegotiatingBankPartyIdentifier());
		 lcCanonicalDto.setApplicableRule(temp.getApplicableRule());
			        
		  lcCanonicalDto.setAdditionalAmounts(temp.getAdditionalAmounts());	      
		  lcCanonicalDto.setInitialDispatchPlace(temp.getInitialDispatchPlace());
	        lcCanonicalDto.setGoodsDestination(temp.getGoodsDestination());
	        lcCanonicalDto.setLatestDateofShipment(temp.getLatestDateofShipment());
	        lcCanonicalDto.setShipmentPeriod(temp.getShipmentPeriod());
	        lcCanonicalDto.setShipmentTerms(temp.getShipmentTerms());	      
	        lcCanonicalDto.setPartialShipments(temp.getPartialShipments());
	        lcCanonicalDto.setTranshipment(temp.getTranshipment());
	        lcCanonicalDto.setDocumentsRequired(temp.getDocumentsRequired());
	        lcCanonicalDto.setAdditionalConditions(temp.getAdditionalConditions());
	        lcCanonicalDto.setChargeDetails(temp.getChargeDetails());
	        lcCanonicalDto.setPeriodforPresentation(temp.getPeriodforPresentation());
	        lcCanonicalDto.setConfirmationCode(temp.getConfirmationCode());
	        lcCanonicalDto.setInstructionstoPayingBank(temp.getInstructionstoPayingBank());		
	        lcCanonicalDto.setAdviseThroughBankpartyidentifier(temp.getAdviseThroughBankpartyidentifier());
	        lcCanonicalDto.setAdviseThroughBankCode(temp.getAdviseThroughBankCode());
	        lcCanonicalDto.setAdviseThroughBankLocation(temp.getAdviseThroughBankLocation());
	        lcCanonicalDto.setAdviseThroughBankAcc(temp.getAdviseThroughBankAcc());
	        lcCanonicalDto.setAdviseThroughBankName(temp.getAdviseThroughBankName());
	        lcCanonicalDto.setPartyIdentifier(temp.getPartyIdentifier());
			lcCanonicalDto.setReimbursingBankCode(temp.getReimbursingBankCode());
			lcCanonicalDto.setReimbursingBankNameAddress(temp.getReimbursingBankNameAddress());
	        lcCanonicalDto.setApplicantAccount(temp.getApplicantAccount());   
	        lcCanonicalDto.setApplicantBankpartyidentifier(temp.getApplicantBankpartyidentifier());
			lcCanonicalDto.setApplicantBankCode(temp.getApplicantBankCode());
	        lcCanonicalDto.setApplicantBankNameAddress(temp.getApplicantBankNameAddress());     
	        
	        
	        lcCanonicalDto.setPresentingBanksReference(temp.getPresentingBanksReference());
	        lcCanonicalDto.setTotalAmountClaimed(temp.getTotalAmountClaimed());
	        lcCanonicalDto.setPaidAmount(temp.getPaidAmount());		
	        lcCanonicalDto.setSendersCorrespondentPartyIdentifier(temp.getSendersCorrespondentPartyIdentifier());
	        lcCanonicalDto.setSenderCorrespontAcount(temp.getSenderCorrespontAcount());		
	        lcCanonicalDto.setSendersCorrespondentCode(temp.getSendersCorrespondentCode());
	        lcCanonicalDto.setSendersCorrespondentLocation(temp.getSendersCorrespondentLocation());
	        lcCanonicalDto.setSendersCorrespondentNameandAddress(temp.getSendersCorrespondentNameandAddress());
	        lcCanonicalDto.setReceiversCorrespondentPartyIdentifier(temp.getReceiversCorrespondentPartyIdentifier());
	        lcCanonicalDto.setReceiverCorrespontAcount(temp.getReceiverCorrespontAcount());		
	        lcCanonicalDto.setReceiversCorrespondentCode(temp.getReceiversCorrespondentCode());
	        lcCanonicalDto.setReceiversCorrespondentLocation(temp.getReceiversCorrespondentLocation());
	        lcCanonicalDto.setReceiversCorrespondentNameandAddress(temp.getReceiversCorrespondentNameandAddress());
	        lcCanonicalDto.setNetChargeAmount(temp.getNetChargeAmount());	
	        lcCanonicalDto.setGoodsLoadingDispatchPlace(temp.getGoodsLoadingDispatchPlace());
	        lcCanonicalDto.setFinalDeliveryPlace(temp.getFinalDeliveryPlace());
	        lcCanonicalDto.setAmountPaidDate(temp.getAmountPaidDate());
	        lcCanonicalDto.setRepair(temp.getRepair());
	        lcCanonicalDto.setIncreaseAmendAmount(temp.getIncreaseAmendAmount());
	        lcCanonicalDto.setDecreaseAmendAmount(temp.getDecreaseAmendAmount());
	        lcCanonicalDto.setAmendmentDate(temp.getAmendmentDate());
	        lcCanonicalDto.setLcAmndmntNo(temp.getLcAmndmntNo());
	        lcCanonicalDto.setIssueDate(temp.getIssueDate());
	        lcCanonicalDto.setApplicableNarrative(temp.getApplicableNarrative());
	        lcCanonicalDto.setOldAmendExpiryDate(temp.getOldAmendExpiryDate());
	        lcCanonicalDto.setOldLcAmount(temp.getOldLcAmount());
	        lcCanonicalDto.setSeqNo(temp.getSeqNo()); 
	        lcCanonicalDto.setMsgHost(temp.getMsgHost());
	        lcCanonicalDto.setDraweeBankAccount(temp.getDraweeBankAccount());
	        lcCanonicalDto.setServiceID(temp.getServiceID());
	        lcCanonicalDto.setMesgIsReturn(temp.getMesgIsReturn());
	        lcCanonicalDto.setMsgPDECount(temp.getMsgPDECount());
	        lcCanonicalDto.setSenderBankReference(temp.getSenderBankReference());
	        lcCanonicalDto.setMsgGRPSeq(temp.getMsgGRPSeq());
	        lcCanonicalDto.setMsgValueDate(temp.getMsgValueDate());
	        lcCanonicalDto.setPymntAcceptedTime(temp.getPymntAcceptedTime());
	        lcCanonicalDto.setIssuingBankCode(temp.getIssuingBankCode());
	        lcCanonicalDto.setLcPrevAdvRef(temp.getLcNumber());
	        lcCanonicalDto.setLcNetAmtClaimed(temp.getLcNetAmtClaimed());
	        lcCanonicalDto.setMsgCurrency(temp.getLcCurrency());
			if(temp.getTxnReference()!= null && !temp.getTxnReference().equals("")){
				lcCanonicalDto.setReceiverBankReference(temp.getTxnReference());
				 }else if(temp.getCustTxnReference()!= null && !temp.getCustTxnReference().equals("")){
					 lcCanonicalDto.setReceiverBankReference(temp.getCustTxnReference());
				 }else if(temp.getSndrTxnId()!=null && !temp.getSndrTxnId().equals("")){
					 lcCanonicalDto.setReceiverBankReference(temp.getSndrTxnId());
				 }
	        
	        session.put("ScreenData", lcCanonicalDto);
		
	}
	
	
	
	@SkipValidation
	public String displayAuthoriseTopay()
	{	
		lcCanonicalDto.setRepair("");
		return "success";
	}
	public void validate(){
		try{
			LetterOfCreditService letterOfCreditService =  (LetterOfCreditService)  ApplicationContextProvider.getBean("letterOfCreditService");
			if(letterOfCreditService.isLcNumberExist(lcCanonicalDto.getLcNumber())==false){
				addFieldError("LcNumber","Lc Number Not Available In DataBase");
			}else{
				if(lcCanonicalDto.getLcNumber().startsWith("/")){
					 addFieldError("lcNumber", "Lc Number must not start with /");
				}else if(lcCanonicalDto.getLcNumber().endsWith("/")){
					addFieldError("lcNumber", "Lc Number must not End with /");;
				}else if(lcCanonicalDto.getLcNumber().contains("//")){
					addFieldError("lcNumber", "Lc Number must not contain two consecutive slashes '//'");
				}
			}
			if(!StringUtils.isBlank(lcCanonicalDto.getRepair()) && !StringUtils.isEmpty(lcCanonicalDto.getRepair())){
				PaymentMessageService paymentMessageService =(PaymentMessageService)ApplicationContextProvider.getBean("paymentMessageService");
				if(!paymentMessageService.checkInRptStatusIs2(lcCanonicalDto.getMsgRef()))
				{
					if(!(getSaveAction().equals("Approve") || getSaveAction().equals("Reject")))
						System.out.println("getSaveAction() in Authorisation page is "+getSaveAction().toString());
						addFieldError("LcNumber", "Message Is Not In Valid State");
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
			addActionError("Unable To process");
		}
	}

}
