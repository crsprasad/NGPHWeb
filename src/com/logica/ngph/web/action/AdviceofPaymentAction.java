/**
 * 
 */
package com.logica.ngph.web.action;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.sql.Clob;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.jboss.util.Base64;
import org.springframework.context.ApplicationContextException;

import com.logica.ngph.common.ConstantUtil;
import com.logica.ngph.common.NGPHException;
import com.logica.ngph.dtos.CreateBankGuaranteeDto;
import com.logica.ngph.dtos.LCCanonicalDto;
import com.logica.ngph.service.AuthorizationSchemaMaitenanceService;
import com.logica.ngph.service.LetterOfCreditService;
import com.logica.ngph.service.PaymentMessageService;
import com.logica.ngph.service.PendingAuthorizationService;
import com.logica.ngph.web.utils.ApplicationContextProvider;
import com.logica.ngph.web.utils.EventLogging;
import com.logica.ngph.web.utils.ExportToExcelUtil;
import com.logica.ngph.web.utils.PaymentPDFGeneratationUtil;
import com.logica.ngph.web.utils.WebConstants;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * @author chakkar
 *
 */
public class AdviceofPaymentAction extends ActionSupport implements ModelDriven<LCCanonicalDto>, SessionAware, ServletRequestAware {
	
	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(LCAcknowledgementAction.class);
	private Map<String, Object> session;
	//create the instance of dto class to get the model driven object
	

	private String hiddenTxnRef;
	private String txnRef;
	private String saveAction;
	private boolean validUserToApprove;
	private String action;
	private String lcNumberForInward;
	private String flagForScreen;
	private String flagMarked;
	private String repairCommodity;
	private String actionError;
	private LCCanonicalDto lcCanonicalDto = new LCCanonicalDto();
	private String checkForSubmit;
	private String msgHost;
	private String seqNo;
	private String tempName;
	private String tempRef;
	private String printPreviewTxnRef;
	private String reportFile;
	private InputStream inputStream;
	private static List<String> currCodeDropDown = new ArrayList<String>();
	private HttpServletRequest servletRequest;
	
	public HttpServletRequest getServletRequest() {
		return servletRequest;
	}

	public void setServletRequest(HttpServletRequest servletRequest) {
		this.servletRequest = servletRequest;
	}
	
	
	
	/**
	 * @return the currCodeDropDown
	 */
	public List<String> getCurrCodeDropDown() {
		return currCodeDropDown;
	}

	/**
	 * @param currCodeDropDown the currCodeDropDown to set
	 */
	public void setCurrCodeDropDown(List<String> currCodeDropDown) {
		this.currCodeDropDown = currCodeDropDown;
	}

	
	
	
	/**
	 * @return the reportFile
	 */
	public String getReportFile() {
		return reportFile;
	}


	/**
	 * @param reportFile the reportFile to set
	 */
	public void setReportFile(String reportFile) {
		this.reportFile = reportFile;
	}


	/**
	 * @return the inputStream
	 */
	public InputStream getInputStream() {
		return inputStream;
	}


	/**
	 * @param inputStream the inputStream to set
	 */
	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}


	@SkipValidation
	public String displayAdviceofPayment()
	{
		try{
			AuthorizationSchemaMaitenanceService authorizationSchemaMaitenanceService = (AuthorizationSchemaMaitenanceService)ApplicationContextProvider.getBean("authorizationSchemaMaitenanceService");
			setCurrCodeDropDown(authorizationSchemaMaitenanceService.getAuthorizationSchemaMaitenanceService(ConstantUtil.CURRENCY));
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
	
	
	public void validate() {


		if (lcCanonicalDto.getLcNumber().startsWith("/")) {
			addFieldError("lcNumber","Sender Bank's Reference(20) Must Not start with /");
		} else if (lcCanonicalDto.getLcNumber().endsWith("/")) {
			addFieldError("lcNumber","Sender Bank's Reference(20) must not End with /");		
		} else if (lcCanonicalDto.getLcNumber().contains("//")) {
			addFieldError("lcNumber","Sender Bank's Reference(20) must not contain two consecutive slashes '//'");
		}

		if (lcCanonicalDto.getRelatedReference().startsWith("/")) {
			addFieldError("relatedReference","Receiver Bank's Reference(21) Must Not start with /");
		} else if (lcCanonicalDto.getRelatedReference().endsWith("/")) {
			addFieldError("relatedReference","Receiver Bank's Reference(21) must not End with /");
		} else if (lcCanonicalDto.getRelatedReference().contains("//")) {
			addFieldError("relatedReference","Receiver Bank's Reference(21) must not contain two consecutive slashes '//'");
		}
		
		if((lcCanonicalDto.getReimbursingBankpartyidentifier()!=null  && !lcCanonicalDto.getReimbursingBankpartyidentifier().isEmpty()) ||(lcCanonicalDto.getReimbursingBankCode()!=null  && !lcCanonicalDto.getReimbursingBankCode().isEmpty()) || (lcCanonicalDto.getReimbersingBankLoc()!=null && !lcCanonicalDto.getReimbersingBankLoc().isEmpty()) || (lcCanonicalDto.getReimbursingBankNameandAddress()!=null && !lcCanonicalDto.getReimbursingBankNameandAddress().isEmpty()))
		{
			if((lcCanonicalDto.getAdviseThroughBankpartyidentifier()!=null && !lcCanonicalDto.getAdviseThroughBankpartyidentifier().isEmpty()) ||(lcCanonicalDto.getAdviseThroughBankCode()!=null && !lcCanonicalDto.getAdviseThroughBankCode().isEmpty()) || (lcCanonicalDto.getAccountWithPartyLoc()!= null && !lcCanonicalDto.getAccountWithPartyLoc().isEmpty())|| (lcCanonicalDto.getAccountWithPartyNameAndAddress()!=null && !lcCanonicalDto.getAccountWithPartyNameAndAddress().isEmpty()))
			{
				addFieldError("reimbursingBankpartyidentifier","Either field 53a or 57a may be present, but not both");
			}
		}
		if(StringUtils.isNotBlank(lcCanonicalDto.getPricAmount()) && lcCanonicalDto.getPricAmount().equalsIgnoreCase("A"))
		{
			if(lcCanonicalDto.getAmountPaidDate()==null)
		   {
			   addFieldError("amountPaidDate", "Amount Paid Or Reimbursed Date(32a) is required");
			}
		}
		
	if(lcCanonicalDto.getSendertoReceiverInformation()!=null && !lcCanonicalDto.getSendertoReceiverInformation().isEmpty())
		{
			if(lcCanonicalDto.getNarrative()!=null && !lcCanonicalDto.getNarrative().isEmpty())
			{
				addFieldError("SendertoReceiverInformation","Either field 72 or 77A may be present, but not both");
			}
			
		}
		if(StringUtils.isNotBlank(lcCanonicalDto.getMsgCurrency()) && StringUtils.isNotEmpty(lcCanonicalDto.getMsgCurrency()) && StringUtils.isNotBlank(lcCanonicalDto.getLcCurrency()) && StringUtils.isNotEmpty(lcCanonicalDto.getLcCurrency()) && StringUtils.isNotBlank(lcCanonicalDto.getCurrency()) && StringUtils.isNotEmpty(lcCanonicalDto.getCurrency()))
		{
			if(!lcCanonicalDto.getMsgCurrency().equalsIgnoreCase(lcCanonicalDto.getCurrency()) && !lcCanonicalDto.getMsgCurrency().equalsIgnoreCase(lcCanonicalDto.getLcCurrency()))
				{
				     addFieldError("msgCurrency","The currency code in the amount fields 32a, 33Band 34a must be the same");
				}
		}
		
		
		if(StringUtils.isBlank(lcCanonicalDto.getAdvisingBank()) && StringUtils.isEmpty(lcCanonicalDto.getAdvisingBank()))
		{
				addFieldError("advisingBank", "Receiver Bank IFSC is required");
		}
	}
	
	@SkipValidation
	public String reset()throws NGPHException {
		try{
		lcCanonicalDto.setLcNumber("");
		lcCanonicalDto.setRelatedReference("");
		lcCanonicalDto.setMsgCurrency("");
		lcCanonicalDto.setLcCurrency("");
		lcCanonicalDto.setCurrency("");
		lcCanonicalDto.setPrincipalAmount(BigDecimal.ZERO);
		lcCanonicalDto.setAdditionalAmount(BigDecimal.ZERO);
		lcCanonicalDto.setTotalAmountClaim("");
		lcCanonicalDto.setTotalAmount(BigDecimal.ZERO);
		lcCanonicalDto.setPricAmount("");
		lcCanonicalDto.setChargesDeducted("");
		lcCanonicalDto.setChargesAdded("");
		lcCanonicalDto.setNarrative("");
		lcCanonicalDto.setAdviseThroughBankAcc("");
		lcCanonicalDto.setReimbursingBankAcc("");
		lcCanonicalDto.setBeneficiaryBankAcc("");
		lcCanonicalDto.setAdviseThroughBankCode("");
		lcCanonicalDto.setAdviseThroughBankLocation("");
		lcCanonicalDto.setAdviseThroughBankName("");
		lcCanonicalDto.setBeneficiaryBankLoc("");
		lcCanonicalDto.setAdviseThroughBankpartyidentifier("");
		lcCanonicalDto.setBeneficiaryBankNameAndAddress("");
		lcCanonicalDto.setAccountWithPartyLoc("");
		lcCanonicalDto.setAccountWithPartyNameAndAddress("");
		lcCanonicalDto.setBeneficiaryBankLoc("");
		lcCanonicalDto.setBeneficiaryBankNameAndAddress("");
		lcCanonicalDto.setBeneficiaryBankCode("");
		lcCanonicalDto.setReimbursingBankCode("");
		lcCanonicalDto.setChargeDetails("");
		lcCanonicalDto.setSendertoReceiverInformation("");
		lcCanonicalDto.setIssuingBankCode("");
		lcCanonicalDto.setAdvisingBank("");
		return "success";
		}catch (Exception e) {
			logger.error(e,e);
			return "input";
		}
	}
	
	
	/**
	 * @return the msgHost
	 */
	public String getMsgHost() {
		return msgHost;
	}


	/**
	 * @param msgHost the msgHost to set
	 */
	public void setMsgHost(String msgHost) {
		this.msgHost = msgHost;
	}


	/**
	 * @return the seqNo
	 */
	public String getSeqNo() {
		return seqNo;
	}


	/**
	 * @param seqNo the seqNo to set
	 */
	public void setSeqNo(String seqNo) {
		this.seqNo = seqNo;
	}


	/**
	 * @return the hiddenTxnRef
	 */
	public String getHiddenTxnRef() {
		return hiddenTxnRef;
	}

	/**
	 * @param hiddenTxnRef the hiddenTxnRef to set
	 */
	public void setHiddenTxnRef(String hiddenTxnRef) {
		this.hiddenTxnRef = hiddenTxnRef;
	}

	/**
	 * @return the txnRef
	 */
	public String getTxnRef() {
		return txnRef;
	}

	/**
	 * @param txnRef the txnRef to set
	 */
	public void setTxnRef(String txnRef) {
		this.txnRef = txnRef;
	}

	/**
	 * @return the saveAction
	 */
	public String getSaveAction() {
		return saveAction;
	}

	/**
	 * @param saveAction the saveAction to set
	 */
	public void setSaveAction(String saveAction) {
		this.saveAction = saveAction;
	}

	/**
	 * @return the validUserToApprove
	 */
	public boolean isValidUserToApprove() {
		return validUserToApprove;
	}

	/**
	 * @param validUserToApprove the validUserToApprove to set
	 */
	public void setValidUserToApprove(boolean validUserToApprove) {
		this.validUserToApprove = validUserToApprove;
	}

	/**
	 * @return the action
	 */
	public String getAction() {
		return action;
	}

	/**
	 * @param action the action to set
	 */
	public void setAction(String action) {
		this.action = action;
	}

	/**
	 * @return the lcNumberForInward
	 */
	public String getLcNumberForInward() {
		return lcNumberForInward;
	}

	/**
	 * @param lcNumberForInward the lcNumberForInward to set
	 */
	public void setLcNumberForInward(String lcNumberForInward) {
		this.lcNumberForInward = lcNumberForInward;
	}

	/**
	 * @return the flagForScreen
	 */
	public String getFlagForScreen() {
		return flagForScreen;
	}

	/**
	 * @param flagForScreen the flagForScreen to set
	 */
	public void setFlagForScreen(String flagForScreen) {
		this.flagForScreen = flagForScreen;
	}

	/**
	 * @return the flagMarked
	 */
	public String getFlagMarked() {
		return flagMarked;
	}

	/**
	 * @param flagMarked the flagMarked to set
	 */
	public void setFlagMarked(String flagMarked) {
		this.flagMarked = flagMarked;
	}

	/**
	 * @return the repairCommodity
	 */
	public String getRepairCommodity() {
		return repairCommodity;
	}

	/**
	 * @param repairCommodity the repairCommodity to set
	 */
	public void setRepairCommodity(String repairCommodity) {
		this.repairCommodity = repairCommodity;
	}

	/**
	 * @return the actionError
	 */
	public String getActionError() {
		return actionError;
	}

	/**
	 * @param actionError the actionError to set
	 */
	public void setActionError(String actionError) {
		this.actionError = actionError;
	}


	/**
	 * @return the session
	 */
	public Map<String, Object> getSession() {
		return session;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	public LCCanonicalDto getModel() {
		
		return lcCanonicalDto;
	}
	/**
	 * @return the lcCanonicalDto
	 */
	public LCCanonicalDto getLcCanonicalDto() {
		return lcCanonicalDto;
	}


	/**
	 * @param lcCanonicalDto the lcCanonicalDto to set
	 */
	public void setLcCanonicalDto(LCCanonicalDto lcCanonicalDto) {
		this.lcCanonicalDto = lcCanonicalDto;
	
	}


	/**
	 * @return the checkForSubmit
	 */
	public String getCheckForSubmit() {
		return checkForSubmit;
	}

	/**
	 * @return the tempName
	 */
	public String getTempName() {
		return tempName;
	}


	/**
	 * @param tempName the tempName to set
	 */
	public void setTempName(String tempName) {
		this.tempName = tempName;
	}

	/**
	 * @param checkForSubmit the checkForSubmit to set
	 */
	public void setCheckForSubmit(String checkForSubmit) {
		this.checkForSubmit = checkForSubmit;
	}
	
	/**
	 * @return the tempRef
	 */
	public String getTempRef() {
		return tempRef;
	}


	/**
	 * @param tempRef the tempRef to set
	 */
	public void setTempRef(String tempRef) {
		this.tempRef = tempRef;
	}
	
	
	
	/**
	 * @return the printPreviewTxnRef
	 */
	public String getPrintPreviewTxnRef() {
		return printPreviewTxnRef;
	}


	/**
	 * @param printPreviewTxnRef the printPreviewTxnRef to set
	 */
	public void setPrintPreviewTxnRef(String printPreviewTxnRef) {
		this.printPreviewTxnRef = printPreviewTxnRef;
	}


	public String getAdviceofPaymentApproval() {
		try {			
			String txnKey="";
			PendingAuthorizationService pendingAuthorizationService = (PendingAuthorizationService)ApplicationContextProvider.getBean("pendingAuthorizationService");
			EventLogging eventLogging = (EventLogging)ApplicationContextProvider.getBean("eventLogging");
			String userId = (String)session.get(WebConstants.CONSTANT_USER_NAME);
			if(!getHiddenTxnRef().isEmpty())
			{
				pendingAuthorizationService.updateRejectStatusToPending(getHiddenTxnRef());
			}else
			{
				txnKey = pendingAuthorizationService.getTranRef(serializeObject(),"Advice of Payment(MT-754)",userId);
				pendingAuthorizationService.delimitedStringValue(txnKey, 1+"", "msgCurrency"+"~"+lcCanonicalDto.getMsgCurrency());
				pendingAuthorizationService.delimitedStringValue(txnKey, 2+"", "lcCurrency"+"~"+lcCanonicalDto.getLcCurrency());
				pendingAuthorizationService.delimitedStringValue(txnKey, 3+"", "currency"+"~"+lcCanonicalDto.getCurrency());
				eventLogging.doEventLogging(userId, "Advice of Payment", ConstantUtil.EVENTID_ADVICE_OF_PAYMENT+ConstantUtil.EVENTID_SUBMIT, ConstantUtil.EVENTLOGGINGCOMMENTSUBMIT,lcCanonicalDto.getLcNumber(),lcCanonicalDto.getMsgRef());
			}
		    
		    if(StringUtils.isBlank(lcCanonicalDto.getRepair()) && StringUtils.isEmpty(lcCanonicalDto.getRepair())){
				
			}else{
				PaymentMessageService paymentMessageService =(PaymentMessageService)ApplicationContextProvider.getBean("paymentMessageService");
				paymentMessageService.changeMsgStatus2to99(lcCanonicalDto.getMsgRef());
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
		LCCanonicalDto lcCanonicalDTO = new LCCanonicalDto();
		try{
			String userId = (String)session.get(WebConstants.CONSTANT_USER_NAME);
			
			lcCanonicalDTO = lcCanonicalDto;
			String fileName ="serial_"+userId+".ser";
		FileOutputStream fos = new FileOutputStream(fileName);
        OutputStream buffer = new BufferedOutputStream( fos );
        ObjectOutputStream oos = new ObjectOutputStream(buffer);
        oos.writeObject(lcCanonicalDTO);
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
	
	public String getObjectAdviceofPayment()
	{
		try{
			String userId = (String)session.get(WebConstants.CONSTANT_USER_NAME);
			PendingAuthorizationService pendingAuthorizationService = (PendingAuthorizationService)ApplicationContextProvider.getBean("pendingAuthorizationService");
			PaymentMessageService paymentMessageService =(PaymentMessageService)ApplicationContextProvider.getBean("paymentMessageService");
			EventLogging eventLogging = (EventLogging)ApplicationContextProvider.getBean("eventLogging");
			if(getSaveAction().equalsIgnoreCase("Approve")){			
				LetterOfCreditService letterOfCreditService =  (LetterOfCreditService)  ApplicationContextProvider.getBean("letterOfCreditService");
				letterOfCreditService.saveLC(lcCanonicalDto,null,"AdviceofPayment",userId,lcCanonicalDto.getRepair());
				pendingAuthorizationService.changeStatus(getSaveAction(), getHiddenTxnRef());
				eventLogging.doEventLogging(userId, "Advice of Payment", ConstantUtil.EVENTID_ADVICE_OF_PAYMENT+ConstantUtil.EVENTID_APPROVE, ConstantUtil.EVENTLOGGINGCOMMENTAPPROVAL,lcCanonicalDto.getLcNumber(),lcCanonicalDto.getMsgRef());
				if(StringUtils.isBlank(lcCanonicalDto.getRepair()) && StringUtils.isEmpty(lcCanonicalDto.getRepair())){
					pendingAuthorizationService.changeStatus(getSaveAction(), getHiddenTxnRef());
				}
				else
				{
					paymentMessageService.changeMsgStatus2to99(lcCanonicalDto.getMsgRef());
					pendingAuthorizationService.changeStatus(getSaveAction(), getHiddenTxnRef());
				}
			}
			else{
				if(StringUtils.isBlank(lcCanonicalDto.getRepair()) && StringUtils.isEmpty(lcCanonicalDto.getRepair())){
					pendingAuthorizationService.changeStatus(getSaveAction(), getHiddenTxnRef());
					eventLogging.doEventLogging(userId, "Advice of Payment", ConstantUtil.EVENTID_ADVICE_OF_PAYMENT+ConstantUtil.EVENTID_REJECT, ConstantUtil.EVENTLOGGINGCOMMENTREJECT,lcCanonicalDto.getLcNumber(),lcCanonicalDto.getMsgRef());
				}else{
					paymentMessageService.changeMsgStatus99to2(lcCanonicalDto.getMsgRef());
					pendingAuthorizationService.changeStatus(getSaveAction(), getHiddenTxnRef());
				}
				
				pendingAuthorizationService = null;
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
	
		addActionError("Unable to perform the operation. Please try again");
		return "input";
	}
	
	@SkipValidation
	public String getLCAdviseofPaymentAuthorization() {
		String tempScreenString =null;
		PendingAuthorizationService pendingAuthorizationService = (PendingAuthorizationService) ApplicationContextProvider.getBean("pendingAuthorizationService");
		try {
			if(getTxnRef()!=null)
			{
				setCheckForSubmit("Display_Approve_Reject");
				String userId = pendingAuthorizationService.getCreatedUser(getTxnRef());
				String userRole = pendingAuthorizationService.getUserType((String) session.get(WebConstants.CONSTANT_USER_NAME));
				if ((((String) session.get(WebConstants.CONSTANT_USER_NAME)).equals(userId) || userRole.equals("T"))) {
					setValidUserToApprove(false);
				} else {
					setValidUserToApprove(true);
				}
				 tempScreenString = pendingAuthorizationService.getScreenReturn(getTxnRef());
			}
			else if(getTempRef()!=null)
			{
				tempScreenString = pendingAuthorizationService.getTemplateScreen(getTempRef());
			}
			LCCanonicalDto temp = getSerializedObject(tempScreenString);

			lcCanonicalDto.setLcNumber(temp.getLcNumber());
			lcCanonicalDto.setRelatedReference(temp.getRelatedReference());
			lcCanonicalDto.setPrincipalAmount(temp.getPrincipalAmount());
			lcCanonicalDto.setAmountPaidDate(temp.getAmountPaidDate());
			lcCanonicalDto.setAdditionalAmount(temp.getAdditionalAmount());
			lcCanonicalDto.setTotalAmountClaim(temp.getTotalAmountClaim());
			lcCanonicalDto.setTotalPaidDate(temp.getTotalPaidDate());
			lcCanonicalDto.setTotalAmount(temp.getTotalAmount());
			lcCanonicalDto.setPricAmount(temp.getPricAmount());
			lcCanonicalDto.setChargesDeducted(temp.getChargesDeducted());
			lcCanonicalDto.setChargesAdded(temp.getChargesAdded());
			lcCanonicalDto.setNarrative(temp.getNarrative());
			lcCanonicalDto.setAdviseThroughBankAcc(temp.getAdviseThroughBankAcc());
			lcCanonicalDto.setReimbursingBankAcc(temp.getReimbursingBankAcc());
			lcCanonicalDto.setReimbersingBankLoc(temp.getReimbersingBankLoc());
			lcCanonicalDto.setBeneficiaryBankAcc(temp.getBeneficiaryBankAcc());
			lcCanonicalDto.setAdviseThroughBankCode(temp.getAdviseThroughBankCode());
			lcCanonicalDto.setAdviseThroughBankLocation(temp.getAdviseThroughBankLocation());
			lcCanonicalDto.setAdviseThroughBankName(temp.getAdviseThroughBankName());
			lcCanonicalDto.setBeneficiaryBankLoc(temp.getBeneficiaryBankLoc());
			lcCanonicalDto.setBeneficiaryBankNameAndAddress(temp.getBeneficiaryBankNameAndAddress());
			lcCanonicalDto.setAdviseThroughBankpartyidentifier(temp.getAdviseThroughBankpartyidentifier());
			lcCanonicalDto.setAccountWithPartyLoc(temp.getAccountWithPartyLoc());
			lcCanonicalDto.setAccountWithPartyNameAndAddress(temp.getAccountWithPartyNameAndAddress());
			lcCanonicalDto.setBeneficiaryBankLoc(temp.getBeneficiaryBankLoc());
			lcCanonicalDto.setBeneficiaryBankNameAndAddress(temp.getBeneficiaryBankNameAndAddress());
			lcCanonicalDto.setBeneficiaryBankCode(temp.getBeneficiaryBankCode());
			lcCanonicalDto.setReimbursingBankpartyidentifier(temp.getReimbursingBankpartyidentifier());
			lcCanonicalDto.setReimbursingBankCode(temp.getReimbursingBankCode());
			lcCanonicalDto.setChargeDetails(temp.getChargeDetails());
			lcCanonicalDto.setSendertoReceiverInformation(temp.getSendertoReceiverInformation());
			lcCanonicalDto.setIssuingBankCode(temp.getIssuingBankCode());
			lcCanonicalDto.setAdvisingBank(temp.getAdvisingBank());
			lcCanonicalDto.setMsgHost(temp.getMsgHost());
			lcCanonicalDto.setSeqNo(temp.getSeqNo());
			lcCanonicalDto.setMsgCurrency(temp.getMsgCurrency());
			lcCanonicalDto.setLcCurrency(temp.getLcCurrency());
			lcCanonicalDto.setCurrency(temp.getCurrency());
			
			if(getTxnRef()!=null)
			{
				List multiCurrCodeList = pendingAuthorizationService.getMulScreenData(getTxnRef());
				
				for(int i=0;i<multiCurrCodeList.size();i++)
				{
					Clob tempCurrCodeList = (Clob)multiCurrCodeList.get(i);
					String data[]= tempCurrCodeList.getSubString(1, (int) tempCurrCodeList.length()).toString().split("~");
					if(data[0].toString().trim().equals("msgCurrency")  && !data[0].toString().trim().equals(""))
					{
						currCodeDropDown.add(data[1].toString());
					}
					else if(data[0].toString().trim().equals("lcCurrency")  && !data[0].toString().trim().equals(""))
					{
						currCodeDropDown.add(data[1].toString());
					}
					else if(data[0].toString().trim().equals("currency")  && !data[0].toString().trim().equals(""))
					{
						currCodeDropDown.add(data[1].toString());
					}
				}
			}
			else if(getTempRef()!=null)
			{
				List  tempmultiCurrCodeList = pendingAuthorizationService.gettempMulScreenData(getTempRef());
				
				for(int i=0;i<tempmultiCurrCodeList.size();i++)
				{
					Clob tempCurrCodeList = (Clob)tempmultiCurrCodeList.get(i);
					String data[]= tempCurrCodeList.getSubString(1, (int) tempCurrCodeList.length()).toString().split("~");
					if(data[0].toString().trim().equals("msgCurrency")  && !data[0].toString().trim().equals(""))
					{
						currCodeDropDown.add(data[1].toString());
					}
					else if(data[0].toString().trim().equals("lcCurrency")  && !data[0].toString().trim().equals(""))
					{
						currCodeDropDown.add(data[1].toString());
					}
					else if(data[0].toString().trim().equals("currency")  && !data[0].toString().trim().equals(""))
					{
						currCodeDropDown.add(data[1].toString());
					}
				}
			}
			lcCanonicalDto.setMsgCurrency(temp.getMsgCurrency());
			lcCanonicalDto.setLcCurrency(temp.getLcCurrency());
			lcCanonicalDto.setCurrency(temp.getCurrency());
			setHiddenTxnRef(getTxnRef());

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
	private LCCanonicalDto getSerializedObject(String objectString) {

		try {

			byte[] decoded = Base64.decode(objectString);
			FileOutputStream foss = new FileOutputStream("targetUserObject.ser");
			foss.write(decoded);
			foss.close();
			LCCanonicalDto testDTO = null;
			FileInputStream fiss = new FileInputStream("targetUserObject.ser");
			BufferedInputStream bufferee = new BufferedInputStream(fiss);
			ObjectInputStream oiss = new ObjectInputStream(bufferee);
			testDTO = (LCCanonicalDto) oiss.readObject();
			oiss.close();
			System.out.println("object2: " + testDTO);
			System.out.print("User(testDTO) :" + testDTO.getLcNumber());

			return testDTO;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public String viewAdviceofPayment()
	{
		displayAdviceofPayment();
			try{
				String msgRef = (String) session.get("messageIndex");
				
				LetterOfCreditService letterOfCreditService =  (LetterOfCreditService)  ApplicationContextProvider.getBean("letterOfCreditService");
				LCCanonicalDto canonicalDto = letterOfCreditService.getAdviceofPayment(msgRef);
				if(canonicalDto.getLcNumber()!=null && StringUtils.isNotBlank(canonicalDto.getLcNumber()) && StringUtils.isNotEmpty(canonicalDto.getLcNumber())){	
					canonicalDto.setRepair(ConstantUtil.REPAIR);
					setALLValueTODTO(canonicalDto);
					return "success";
				}else
				{
					addFieldError("paymentMessageType",ConstantUtil.ERRORMESSAGE);
					return "failure";
				}
				
				}catch(Exception exception)
				{
					exception.printStackTrace();
					addFieldError("paymentMessageType",ConstantUtil.ERRORMESSAGE);
					return "failure";
				
				}
	}
	
	private void setALLValueTODTO(LCCanonicalDto obj)
	{
		
		LCCanonicalDto  lcCanonicalDTO =  obj;
		
		lcCanonicalDto.setLcNumber(lcCanonicalDTO.getLcNumber());
		lcCanonicalDto.setRelatedReference(lcCanonicalDTO.getRelatedReference());
		lcCanonicalDto.setMsgCurrency(lcCanonicalDTO.getMsgCurrency());
		lcCanonicalDto.setLcCurrency(lcCanonicalDTO.getLcCurrency());
		lcCanonicalDto.setCurrency(lcCanonicalDTO.getCurrency());
		lcCanonicalDto.setPrincipalAmount(lcCanonicalDTO.getPrincipalAmount());
		lcCanonicalDto.setAmountPaidDate(lcCanonicalDTO.getAmountPaidDate());
		lcCanonicalDto.setAdditionalAmount(lcCanonicalDTO.getAdditionalAmount());
		lcCanonicalDto.setTotalAmountClaim(lcCanonicalDTO.getTotalAmountClaim());
		lcCanonicalDto.setTotalPaidDate(lcCanonicalDTO.getTotalPaidDate());
		lcCanonicalDto.setTotalAmount(lcCanonicalDTO.getTotalAmount());
		lcCanonicalDto.setPricAmount(lcCanonicalDTO.getPricAmount());
		lcCanonicalDto.setChargesDeducted(lcCanonicalDTO.getChargesDeducted());
		lcCanonicalDto.setChargesAdded(lcCanonicalDTO.getChargesAdded());
		lcCanonicalDto.setNarrative(lcCanonicalDTO.getNarrative());
		lcCanonicalDto.setAdviseThroughBankAcc(lcCanonicalDTO.getAdviseThroughBankAcc());
		lcCanonicalDto.setReimbursingBankAcc(lcCanonicalDTO.getReimbursingBankAcc());
		lcCanonicalDto.setReimbersingBankLoc(lcCanonicalDTO.getReimbersingBankLoc());
		lcCanonicalDto.setBeneficiaryBankAcc(lcCanonicalDTO.getBeneficiaryBankAcc());
		lcCanonicalDto.setAdviseThroughBankCode(lcCanonicalDTO.getAdviseThroughBankCode());
		lcCanonicalDto.setAdviseThroughBankLocation(lcCanonicalDTO.getAdviseThroughBankLocation());
		lcCanonicalDto.setAdviseThroughBankName(lcCanonicalDTO.getAdviseThroughBankName());
		lcCanonicalDto.setBeneficiaryBankLoc(lcCanonicalDTO.getBeneficiaryBankLoc());
		lcCanonicalDto.setBeneficiaryBankNameAndAddress(lcCanonicalDTO.getBeneficiaryBankNameAndAddress());
		lcCanonicalDto.setAccountWithPartyLoc(lcCanonicalDTO.getAccountWithPartyLoc());
		lcCanonicalDto.setAccountWithPartyNameAndAddress(lcCanonicalDTO.getAccountWithPartyNameAndAddress());
		lcCanonicalDto.setAdviseThroughBankpartyidentifier(lcCanonicalDTO.getAdviseThroughBankpartyidentifier());
		lcCanonicalDto.setBeneficiaryBankLoc(lcCanonicalDTO.getBeneficiaryBankLoc());
		lcCanonicalDto.setBeneficiaryBankNameAndAddress(lcCanonicalDTO.getBeneficiaryBankNameAndAddress());
		lcCanonicalDto.setBeneficiaryBankCode(lcCanonicalDTO.getBeneficiaryBankCode());
		lcCanonicalDto.setReimbursingBankCode(lcCanonicalDTO.getReimbursingBankCode());
		lcCanonicalDto.setReimbursingBankpartyidentifier(lcCanonicalDTO.getReimbursingBankpartyidentifier());
		lcCanonicalDto.setChargeDetails(lcCanonicalDTO.getChargeDetails());
		lcCanonicalDto.setSendertoReceiverInformation(lcCanonicalDTO.getSendertoReceiverInformation());
		lcCanonicalDto.setIssuingBankCode(lcCanonicalDTO.getIssuingBankCode());
		lcCanonicalDto.setAdvisingBank(lcCanonicalDTO.getAdvisingBank());
		lcCanonicalDto.setMsgHost(lcCanonicalDTO.getMsgHost());
		lcCanonicalDto.setSeqNo(lcCanonicalDTO.getSeqNo());
		lcCanonicalDto.setRepair(lcCanonicalDTO.getRepair());
	
	}
	
	public String saveTemplate() {
		
		try {			
			String tempRefKey="";
			PendingAuthorizationService pendingAuthorizationService = (PendingAuthorizationService)ApplicationContextProvider.getBean("pendingAuthorizationService");
			String userId = (String)session.get(WebConstants.CONSTANT_USER_NAME);
			
			tempRefKey = pendingAuthorizationService.getTempRef(serializeObject(),"754","XXX",tempName,"Advice of Payment(MT-754)",userId);
			pendingAuthorizationService.delimitedStringValue(tempRefKey, 1+"", "msgCurrency"+"~"+lcCanonicalDto.getMsgCurrency());
			pendingAuthorizationService.delimitedStringValue(tempRefKey, 2+"", "lcCurrency"+"~"+lcCanonicalDto.getLcCurrency());
			pendingAuthorizationService.delimitedStringValue(tempRefKey, 3+"", "currency"+"~"+lcCanonicalDto.getCurrency());

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
@SkipValidation
public String viewTemplateAdviceofPayment()
{
	displayAdviceofPayment();
		try{
			String msgRef = (String) session.get("messageRef");
			
			LetterOfCreditService letterOfCreditService =  (LetterOfCreditService)  ApplicationContextProvider.getBean("letterOfCreditService");
			LCCanonicalDto canonicalDto = letterOfCreditService.getAdviceofPayment(msgRef);
			if(canonicalDto.getLcNumber()!=null && StringUtils.isNotBlank(canonicalDto.getLcNumber()) && StringUtils.isNotEmpty(canonicalDto.getLcNumber())){	
				setALLValueTODTO(canonicalDto);
				return "success";
			}else
			{
				return "failure";
			}
			
			}catch(Exception exception)
			{
				exception.printStackTrace();
				return "failure";
			
			}
}

	@SkipValidation
	public String displayPrintPreviewAdviceofPayment()
	{
		String tempScreenString =null;
		String userId =null;
		String msgRef = (String) session.get("messageIndex");
		PendingAuthorizationService pendingAuthorizationService = (PendingAuthorizationService)ApplicationContextProvider.getBean("pendingAuthorizationService");
		try{
			if(getHiddenTxnRef()!=null && !getHiddenTxnRef().isEmpty())
			{
				setCheckForSubmit("Display_Approve_Reject");
				userId= pendingAuthorizationService.getCreatedUser(getHiddenTxnRef());
				String userRole = pendingAuthorizationService.getUserType((String)session.get(WebConstants.CONSTANT_USER_NAME));
				if((((String)session.get(WebConstants.CONSTANT_USER_NAME)).equals(userId) || userRole.equals("T"))){
					setValidUserToApprove(false);
				} else{
					setValidUserToApprove(true);
				}
	
				tempScreenString =pendingAuthorizationService.getScreenReturn(getHiddenTxnRef());
				LCCanonicalDto temp = getSerializedObject(tempScreenString);

				lcCanonicalDto.setLcNumber(temp.getLcNumber());
				lcCanonicalDto.setRelatedReference(temp.getRelatedReference());
				lcCanonicalDto.setMsgCurrency(temp.getMsgCurrency());
				lcCanonicalDto.setLcCurrency(temp.getLcCurrency());
				lcCanonicalDto.setCurrency(temp.getCurrency());
				lcCanonicalDto.setPrincipalAmount(temp.getPrincipalAmount());
				lcCanonicalDto.setAmountPaidDate(temp.getAmountPaidDate());
				lcCanonicalDto.setAdditionalAmount(temp.getAdditionalAmount());
				lcCanonicalDto.setTotalAmountClaim(temp.getTotalAmountClaim());
				lcCanonicalDto.setTotalPaidDate(temp.getTotalPaidDate());
				lcCanonicalDto.setTotalAmount(temp.getTotalAmount());
				lcCanonicalDto.setPricAmount(temp.getPricAmount());
				lcCanonicalDto.setChargesDeducted(temp.getChargesDeducted());
				lcCanonicalDto.setChargesAdded(temp.getChargesAdded());
				lcCanonicalDto.setNarrative(temp.getNarrative());
				lcCanonicalDto.setAdviseThroughBankAcc(temp.getAdviseThroughBankAcc());
				lcCanonicalDto.setReimbursingBankAcc(temp.getReimbursingBankAcc());
				lcCanonicalDto.setReimbersingBankLoc(temp.getReimbersingBankLoc());
				lcCanonicalDto.setBeneficiaryBankAcc(temp.getBeneficiaryBankAcc());
				lcCanonicalDto.setAdviseThroughBankCode(temp.getAdviseThroughBankCode());
				lcCanonicalDto.setAdviseThroughBankLocation(temp.getAdviseThroughBankLocation());
				lcCanonicalDto.setAdviseThroughBankName(temp.getAdviseThroughBankName());
				lcCanonicalDto.setBeneficiaryBankLoc(temp.getBeneficiaryBankLoc());
				lcCanonicalDto.setBeneficiaryBankNameAndAddress(temp.getBeneficiaryBankNameAndAddress());
				lcCanonicalDto.setAdviseThroughBankpartyidentifier(temp.getAdviseThroughBankpartyidentifier());
				lcCanonicalDto.setAccountWithPartyLoc(temp.getAccountWithPartyLoc());
				lcCanonicalDto.setAccountWithPartyNameAndAddress(temp.getAccountWithPartyNameAndAddress());
				lcCanonicalDto.setBeneficiaryBankLoc(temp.getBeneficiaryBankLoc());
				lcCanonicalDto.setBeneficiaryBankNameAndAddress(temp.getBeneficiaryBankNameAndAddress());
				lcCanonicalDto.setBeneficiaryBankCode(temp.getBeneficiaryBankCode());
				lcCanonicalDto.setReimbursingBankCode(temp.getReimbursingBankCode());
				lcCanonicalDto.setReimbursingBankpartyidentifier(temp.getReimbursingBankpartyidentifier());
				lcCanonicalDto.setChargeDetails(temp.getChargeDetails());
				lcCanonicalDto.setSendertoReceiverInformation(temp.getSendertoReceiverInformation());
				lcCanonicalDto.setIssuingBankCode(temp.getIssuingBankCode());
				lcCanonicalDto.setAdvisingBank(temp.getAdvisingBank());
				lcCanonicalDto.setMsgHost(temp.getMsgHost());
				lcCanonicalDto.setSeqNo(temp.getSeqNo());
				setPrintPreviewTxnRef(getHiddenTxnRef());
			}
			else if(msgRef!=null && !msgRef.isEmpty())
			{
				viewAdviceofPayment();
				return "success";
			}
			else
			{
				lcCanonicalDto.setLcNumber(lcCanonicalDto.getLcNumber());
				lcCanonicalDto.setRelatedReference(lcCanonicalDto.getRelatedReference());
				lcCanonicalDto.setMsgCurrency(lcCanonicalDto.getMsgCurrency());
				lcCanonicalDto.setLcCurrency(lcCanonicalDto.getLcCurrency());
				lcCanonicalDto.setCurrency(lcCanonicalDto.getCurrency());
				lcCanonicalDto.setPrincipalAmount(lcCanonicalDto.getPrincipalAmount());
				lcCanonicalDto.setAmountPaidDate(lcCanonicalDto.getAmountPaidDate());
				lcCanonicalDto.setAdditionalAmount(lcCanonicalDto.getAdditionalAmount());
				lcCanonicalDto.setTotalAmountClaim(lcCanonicalDto.getTotalAmountClaim());
				lcCanonicalDto.setTotalPaidDate(lcCanonicalDto.getTotalPaidDate());
				lcCanonicalDto.setTotalAmount(lcCanonicalDto.getTotalAmount());
				lcCanonicalDto.setPricAmount(lcCanonicalDto.getPricAmount());
				lcCanonicalDto.setChargesDeducted(lcCanonicalDto.getChargesDeducted());
				lcCanonicalDto.setChargesAdded(lcCanonicalDto.getChargesAdded());
				lcCanonicalDto.setNarrative(lcCanonicalDto.getNarrative());
				lcCanonicalDto.setAdviseThroughBankAcc(lcCanonicalDto.getAdviseThroughBankAcc());
				lcCanonicalDto.setReimbursingBankAcc(lcCanonicalDto.getReimbursingBankAcc());
				lcCanonicalDto.setReimbersingBankLoc(lcCanonicalDto.getReimbersingBankLoc());
				lcCanonicalDto.setBeneficiaryBankAcc(lcCanonicalDto.getBeneficiaryBankAcc());
				lcCanonicalDto.setAdviseThroughBankCode(lcCanonicalDto.getAdviseThroughBankCode());
				lcCanonicalDto.setAdviseThroughBankLocation(lcCanonicalDto.getAdviseThroughBankLocation());
				lcCanonicalDto.setAdviseThroughBankName(lcCanonicalDto.getAdviseThroughBankName());
				lcCanonicalDto.setBeneficiaryBankLoc(lcCanonicalDto.getBeneficiaryBankLoc());
				lcCanonicalDto.setBeneficiaryBankNameAndAddress(lcCanonicalDto.getBeneficiaryBankNameAndAddress());
				lcCanonicalDto.setAdviseThroughBankpartyidentifier(lcCanonicalDto.getAdviseThroughBankpartyidentifier());
				lcCanonicalDto.setAccountWithPartyLoc(lcCanonicalDto.getAccountWithPartyLoc());
				lcCanonicalDto.setAccountWithPartyNameAndAddress(lcCanonicalDto.getAccountWithPartyNameAndAddress());
				lcCanonicalDto.setBeneficiaryBankLoc(lcCanonicalDto.getBeneficiaryBankLoc());
				lcCanonicalDto.setBeneficiaryBankNameAndAddress(lcCanonicalDto.getBeneficiaryBankNameAndAddress());
				lcCanonicalDto.setBeneficiaryBankCode(lcCanonicalDto.getBeneficiaryBankCode());
				lcCanonicalDto.setReimbursingBankCode(lcCanonicalDto.getReimbursingBankCode());
				lcCanonicalDto.setReimbursingBankpartyidentifier(lcCanonicalDto.getReimbursingBankpartyidentifier());
				lcCanonicalDto.setChargeDetails(lcCanonicalDto.getChargeDetails());
				lcCanonicalDto.setSendertoReceiverInformation(lcCanonicalDto.getSendertoReceiverInformation());
				lcCanonicalDto.setIssuingBankCode(lcCanonicalDto.getIssuingBankCode());
				lcCanonicalDto.setAdvisingBank(lcCanonicalDto.getAdvisingBank());
				lcCanonicalDto.setMsgHost(lcCanonicalDto.getMsgHost());
				lcCanonicalDto.setSeqNo(lcCanonicalDto.getSeqNo());
			}
		
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
	public String exportToExcelAdviceofPayment() throws Exception
	{		
	try{
		displayPrintPreviewAdviceofPayment();
		String reportFile = "Advice_of_Payment_(MT-754)";
		SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyyHHmmss");
		String currentDateTime = sdf.format(new Date());
		String reportFileName = reportFile+"_"+currentDateTime+"".concat(".xls");
		setReportFile(reportFileName);
		System.out.println("reportFileName is "+reportFileName);
		ExportToExcelUtil exportToExcelUtil = new ExportToExcelUtil();
		HSSFWorkbook myWorkBook = exportToExcelUtil.generateExportToExcel(lcCanonicalDto, null, null, reportFile, 754, "XXX");
		ByteArrayOutputStream boas = new ByteArrayOutputStream();
		myWorkBook.write(boas);
		setInputStream(new ByteArrayInputStream(boas.toByteArray()));
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
public String generatePDFAdviceofPayment()
{
	displayPrintPreviewAdviceofPayment();
	String reportName ="Advice of Payment(MT-754)";
	Map<String,String> fieldNamesMap = new HashMap<String,String>();
	
	try{
		
		fieldNamesMap.put("label.MB.IssuingBranchIFSC", getText("label.MB.IssuingBranchIFSC"));
		fieldNamesMap.put("label.MB.Benefifsc", getText("label.MB.Benefifsc"));
		
		fieldNamesMap.put("label.754.ppSenderBanksReference", getText("label.754.ppSenderBanksReference"));
		fieldNamesMap.put("label.754.pprelatedReferenceNumber", getText("label.754.pprelatedReferenceNumber"));
		fieldNamesMap.put("label.754.ppPrincipalAmount", getText("label.754.ppPrincipalAmount"));
		fieldNamesMap.put("label.754.ppamountPaidDate", getText("label.754.ppamountPaidDate"));
		fieldNamesMap.put("label.754.ppCurrency", getText("label.754.ppCurrency"));
		fieldNamesMap.put("label.754.ppPrincipalAmountClaimed", getText("label.754.ppPrincipalAmountClaimed"));
		fieldNamesMap.put("label.754.ppcurrency", getText("label.754.ppcurrency"));
		fieldNamesMap.put("label.754.ppadditionalAmounts", getText("label.754.ppadditionalAmounts"));
		fieldNamesMap.put("label.754.ppChargesDeducted", getText("label.754.ppChargesDeducted"));
		fieldNamesMap.put("label.754.ppChargesAdded", getText("label.754.ppChargesAdded"));
		fieldNamesMap.put("label.754.ppTotalAmountClaimed", getText("label.754.ppTotalAmountClaimed"));
		fieldNamesMap.put("label.754.ppTotalAmountClaimedDate", getText("label.754.ppTotalAmountClaimedDate"));
		fieldNamesMap.put("label.754.ppCUrrency", getText("label.754.ppCUrrency"));
		fieldNamesMap.put("label.754.ppAmount", getText("label.754.ppAmount"));
		fieldNamesMap.put("label.754.ppReimbursingBank", getText("label.754.ppReimbursingBank"));
		fieldNamesMap.put("label.754.ppReimbursingBankCode", getText("label.754.ppReimbursingBankCode"));
		fieldNamesMap.put("label.754.ppReimbursingBankLocation", getText("label.754.ppReimbursingBankLocation"));
		fieldNamesMap.put("label.754.ppReimbursingBankNameandAddess", getText("label.754.ppReimbursingBankNameandAddess"));
		fieldNamesMap.put("label.754.ppaccountWithPartyIdentifier1", getText("label.754.ppaccountWithPartyIdentifier1"));
		fieldNamesMap.put("label.754.ppaccountWithBank", getText("label.754.ppaccountWithBank"));
		fieldNamesMap.put("label.754.ppaccountWithPartyLocation", getText("label.754.ppaccountWithPartyLocation"));
		fieldNamesMap.put("label.754.ppaccountWithNameAndAddress", getText("label.754.ppaccountWithNameAndAddress"));
		fieldNamesMap.put("label.754.ppbeneficiarybank", getText("label.754.ppbeneficiarybank"));
		fieldNamesMap.put("label.754.ppBeneficiarybankBank", getText("label.754.ppBeneficiarybankBank"));
		fieldNamesMap.put("label.754.ppBeneficiaryBankNameandAddess", getText("label.754.ppBeneficiaryBankNameandAddess"));
		fieldNamesMap.put("label.754.ppSendertoReceiverInformation", getText("label.754.ppSendertoReceiverInformation"));
		fieldNamesMap.put("label.754.ppNarrative", getText("label.754.ppNarrative"));
		
		
		
		PaymentPDFGeneratationUtil paymentPDFGeneratationUtil = new PaymentPDFGeneratationUtil();
		paymentPDFGeneratationUtil.setServletRequest(servletRequest);
		paymentPDFGeneratationUtil.setReportName(reportName);
		
		paymentPDFGeneratationUtil.generatePaymentPDFReport(lcCanonicalDto, null, null, fieldNamesMap, 754, "XXX");
	}
	catch (Exception exception) {
		AuditServiceUtil.logException(exception,logger);
	}
	addActionError("Unable to Generate PDF file! Please try again");
	return "input";
}

}
