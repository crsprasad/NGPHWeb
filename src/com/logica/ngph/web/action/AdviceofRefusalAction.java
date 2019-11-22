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
public class AdviceofRefusalAction extends ActionSupport implements ModelDriven<LCCanonicalDto>, SessionAware, ServletRequestAware {
	
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
	private String tempName;
	private String tempRef;
	private String printPreviewTxnRef;
	private String reportFile;
	private InputStream inputStream;
	private static List<String> currCodeDropDown = new ArrayList<String>();
	private String tempTxnRef;
	private HttpServletRequest servletRequest;
	
	public HttpServletRequest getServletRequest() {
		return servletRequest;
	}

	public void setServletRequest(HttpServletRequest servletRequest) {
		this.servletRequest = servletRequest;
	}
	
	
	
	public String getTempTxnRef() {
		return tempTxnRef;
	}


	public void setTempTxnRef(String tempTxnRef) {
		this.tempTxnRef = tempTxnRef;
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


	@SkipValidation
	public String displayAdviceofRefusal()
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
			addFieldError("lcNumber","Sender's TRN(20) Must Not start with /");
		} else if (lcCanonicalDto.getLcNumber().endsWith("/")) {
			addFieldError("lcNumber","Sender's TRN(20) must not End with /");		
		} else if (lcCanonicalDto.getLcNumber().contains("//")) {
			addFieldError("lcNumber","Sender's TRN(20) must not contain two consecutive slashes '//'");
		}

		if (lcCanonicalDto.getRelatedReference().startsWith("/")) {
			addFieldError("relatedReference","Presenting Bank's Reference(21) Must Not start with /");
		} else if (lcCanonicalDto.getRelatedReference().endsWith("/")) {
			addFieldError("relatedReference","Presenting Bank's Reference(21) must not End with /");
		} else if (lcCanonicalDto.getRelatedReference().contains("//")) {
			addFieldError("relatedReference","Presenting Bank's Reference(21) must not contain two consecutive slashes '//'");
		}
		

		if(lcCanonicalDto.getLcAckDate()==null)
		{
			addFieldError("lcAckDate", "Utilisation Date(32A) is required");
		}
		
		if(lcCanonicalDto.getLcCurrency()!=null && StringUtils.isNotEmpty(lcCanonicalDto.getLcCurrency()))
		{
			if(lcCanonicalDto.getClaimCurrency()!=null && StringUtils.isNotEmpty(lcCanonicalDto.getClaimCurrency()))
			{
				if(!lcCanonicalDto.getLcCurrency().equalsIgnoreCase(lcCanonicalDto.getClaimCurrency()))
				{
					addFieldError("lcCurrency","The currency code in the amount fields 32A and 33a must be the same");
				}
			}
		}

		if(StringUtils.isNotBlank(lcCanonicalDto.getChargesClaimed()) && StringUtils.isNotEmpty(lcCanonicalDto.getChargesClaimed()))
		{
			if(StringUtils.isNotBlank(lcCanonicalDto.getCurrency()) && StringUtils.isNotEmpty(lcCanonicalDto.getCurrency()))
			{
				if(lcCanonicalDto.getCurrency().equalsIgnoreCase("A"))
				{
					if(lcCanonicalDto.getAmountPaidDate()==null && (StringUtils.isEmpty(lcCanonicalDto.getClaimCurrency()) && StringUtils.isBlank(lcCanonicalDto.getClaimCurrency()))
							&& lcCanonicalDto.getLcClaimAmount()==null)
					{
						addFieldError("chargesClaimed", "If field 73 is present, field 33a must also be present");
					}
				}
				else if(lcCanonicalDto.getCurrency().equalsIgnoreCase("B"))
				{
					if(StringUtils.isEmpty(lcCanonicalDto.getClaimCurrency()) && StringUtils.isBlank(lcCanonicalDto.getClaimCurrency()) && lcCanonicalDto.getLcClaimAmount()==null)
					{
						addFieldError("chargesClaimed", "If field 73 is present, field 33a must also be present");
					}
				}
			}
			else
			{
				addFieldError("chargesClaimed", "If field 73 is present, field 33a must also be present");
			}
		}
		
		if(StringUtils.isBlank(lcCanonicalDto.getAdvisingBank()) && StringUtils.isEmpty(lcCanonicalDto.getAdvisingBank()))
		{
				addFieldError("advisingBank", "Receiver Bank IFSC is required");
		}
					
	}
	

	@SkipValidation
	public String reset() {
		try{
			lcCanonicalDto.setLcNumber("");
			lcCanonicalDto.setRelatedReference("");
			lcCanonicalDto.setCurrency("");
			lcCanonicalDto.setLcCurrency("");
			lcCanonicalDto.setClaimCurrency("");
			lcCanonicalDto.setLcAmount(BigDecimal.ZERO);
			lcCanonicalDto.setAmountPaidDate(null);
			lcCanonicalDto.setChargesClaimed("");
			lcCanonicalDto.setLcClaimAmount(BigDecimal.ZERO);
			lcCanonicalDto.setLcDispoDocs("");
			lcCanonicalDto.setDiscrepancies("");
			lcCanonicalDto.setAdviseThroughBankCode("");
			lcCanonicalDto.setAdviseThroughBankLocation("");
			lcCanonicalDto.setAdviseThroughBankName("");
			lcCanonicalDto.setChargeDetails("");
			lcCanonicalDto.setSendertoReceiverInformation("");
			lcCanonicalDto.setIssuingBankCode("");
			lcCanonicalDto.setAdvisingBank("");
			lcCanonicalDto.setSeqNo("");
		return "success";
		}catch (Exception e) {
			logger.error(e,e);
			return "input";
		}

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
	
	
	
	public String getTempRef() {
		return tempRef;
	}


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


	public String getAdviceofRefusalApproval() {
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
				txnKey = pendingAuthorizationService.getTranRef(serializeObject(),"Advice of Refusal(MT-734)",userId);
				
				pendingAuthorizationService.delimitedStringValue(txnKey, 1+"", "lcCurrency"+"~"+lcCanonicalDto.getLcCurrency());
				pendingAuthorizationService.delimitedStringValue(txnKey, 2+"", "claimCurrency"+"~"+lcCanonicalDto.getClaimCurrency());
				
				eventLogging.doEventLogging(userId, "Advice of Refusal", ConstantUtil.EVENTID_ADVICE_OF_REFUSAL+ConstantUtil.EVENTID_SUBMIT, ConstantUtil.EVENTLOGGINGCOMMENTSUBMIT,lcCanonicalDto.getLcNumber(),lcCanonicalDto.getMsgRef());
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
	
	public String getObjectAdviceofRefusal()
	{
		try{
			String userId = (String)session.get(WebConstants.CONSTANT_USER_NAME);
			PendingAuthorizationService pendingAuthorizationService = (PendingAuthorizationService)ApplicationContextProvider.getBean("pendingAuthorizationService");
			PaymentMessageService paymentMessageService =(PaymentMessageService)ApplicationContextProvider.getBean("paymentMessageService");
			EventLogging eventLogging = (EventLogging)ApplicationContextProvider.getBean("eventLogging");
			LetterOfCreditService letterOfCreditService =  (LetterOfCreditService)  ApplicationContextProvider.getBean("letterOfCreditService");
			if(getSaveAction().equalsIgnoreCase("Approve")){			
				letterOfCreditService.saveLC(lcCanonicalDto,null,"AdviceofRefusal",userId,lcCanonicalDto.getRepair());
				pendingAuthorizationService.changeStatus(getSaveAction(), getHiddenTxnRef());
				eventLogging.doEventLogging(userId, "Advice of Refusal", ConstantUtil.EVENTID_ADVICE_OF_REFUSAL+ConstantUtil.EVENTID_APPROVE, ConstantUtil.EVENTLOGGINGCOMMENTAPPROVAL,lcCanonicalDto.getLcNumber(),lcCanonicalDto.getMsgRef());
				if(StringUtils.isBlank(lcCanonicalDto.getRepair()) && StringUtils.isEmpty(lcCanonicalDto.getRepair())){
					pendingAuthorizationService.changeStatus(getSaveAction(), getHiddenTxnRef());	
				}
				else
				{
					paymentMessageService.changeMsgStatus2to99(lcCanonicalDto.getMsgRef());
				}
			}
			else{
				if(StringUtils.isBlank(lcCanonicalDto.getRepair()) && StringUtils.isEmpty(lcCanonicalDto.getRepair())){
					pendingAuthorizationService.changeStatus(getSaveAction(), getHiddenTxnRef());
					eventLogging.doEventLogging(userId, "Advice of Refusal", ConstantUtil.EVENTID_ADVICE_OF_REFUSAL+ConstantUtil.EVENTID_REJECT, ConstantUtil.EVENTLOGGINGCOMMENTREJECT,lcCanonicalDto.getLcNumber(),lcCanonicalDto.getMsgRef());
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
	public String getLCAdviseofRefusalAuthorization() {
		String tempScreenString = null;
		PendingAuthorizationService pendingAuthorizationService = (PendingAuthorizationService)ApplicationContextProvider.getBean("pendingAuthorizationService");
	try{
		if(getTxnRef()!=null)
		{
		setCheckForSubmit("Display_Approve_Reject");
		String userId = pendingAuthorizationService.getCreatedUser(getTxnRef());
		String userRole = pendingAuthorizationService.getUserType((String)session.get(WebConstants.CONSTANT_USER_NAME));
		if((((String)session.get(WebConstants.CONSTANT_USER_NAME)).equals(userId) || userRole.equals("T"))){
			setValidUserToApprove(false);
		} else {
			setValidUserToApprove(true);
		}
		 tempScreenString =pendingAuthorizationService.getScreenReturn(getTxnRef());
		}
		else if(getTempRef()!=null)
		{
			tempScreenString = pendingAuthorizationService.getTemplateScreen(getTempRef());
		}
			LCCanonicalDto temp = getSerializedObject(tempScreenString);

			lcCanonicalDto.setLcNumber(temp.getLcNumber());
			lcCanonicalDto.setRelatedReference(temp.getRelatedReference());
			lcCanonicalDto.setLcAckDate(temp.getLcAckDate());
			lcCanonicalDto.setLcCurrency(temp.getLcCurrency());
			lcCanonicalDto.setCurrency(temp.getCurrency());
			lcCanonicalDto.setLcAmount(temp.getLcAmount());
			lcCanonicalDto.setAmountPaidDate(temp.getAmountPaidDate());
			lcCanonicalDto.setChargesClaimed(temp.getChargesClaimed());
			lcCanonicalDto.setLcClaimAmount(temp.getLcClaimAmount());
			lcCanonicalDto.setLcDispoDocs(temp.getLcDispoDocs());
			lcCanonicalDto.setDiscrepancies(temp.getDiscrepancies());
			lcCanonicalDto.setAdviseThroughBankCode(temp.getAdviseThroughBankCode());
			lcCanonicalDto.setAdviseThroughBankLocation(temp.getAdviseThroughBankLocation());
			lcCanonicalDto.setAdviseThroughBankName(temp.getAdviseThroughBankName());
			lcCanonicalDto.setChargeDetails(temp.getChargeDetails());
			lcCanonicalDto.setSendertoReceiverInformation(temp.getSendertoReceiverInformation());
			lcCanonicalDto.setIssuingBankCode(temp.getIssuingBankCode());
			lcCanonicalDto.setAdvisingBank(temp.getAdvisingBank());
			lcCanonicalDto.setSeqNo(temp.getSeqNo());
			lcCanonicalDto.setAdviseThroughBankpartyidentifier(temp.getAdviseThroughBankpartyidentifier());
			
			if(getTxnRef()!=null)
			{
				List multiCurrCodeList = pendingAuthorizationService.getMulScreenData(getTxnRef());
				
				for(int i=0;i<multiCurrCodeList.size();i++)
				{
					Clob tempCurrCodeList = (Clob)multiCurrCodeList.get(i);
					String data[]= tempCurrCodeList.getSubString(1, (int) tempCurrCodeList.length()).toString().split("~");
					if(data[0].toString().trim().equals("lcCurrency"))
					{
						if(data[1]!=null)
						{
							currCodeDropDown.add(data[1].toString());
						}
						else
						{
							currCodeDropDown.add("");
						}
					}
					else if(data[0].toString().trim().equals("claimCurrency"))
					{
						if(data[1]!=null)
						{
							currCodeDropDown.add(data[1].toString());
						}
						else
						{
							currCodeDropDown.add("");
						}
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
					if(data[0].toString().trim().equals("lcCurrency")  && !(data[0].toString().trim().equals("")))
					{
						currCodeDropDown.add(data[1].toString());
					}
					else if(data[0].toString().trim().equals("claimCurrency")  && !(data[0].toString().trim().equals("")))
					{
						currCodeDropDown.add(data[1].toString());
					}
				}
			}
			
			
			lcCanonicalDto.setLcCurrency(temp.getLcCurrency());
			lcCanonicalDto.setClaimCurrency(temp.getClaimCurrency());
			
			setHiddenTxnRef(getTxnRef());
			setTempTxnRef(getTxnRef());
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

	@SkipValidation
	public String viewAdviceofRefusal()
	{
		
			try{
				String msgRef = (String) session.get("messageIndex");
				LetterOfCreditService letterOfCreditService =  (LetterOfCreditService)  ApplicationContextProvider.getBean("letterOfCreditService");
				LCCanonicalDto canonicalDto = letterOfCreditService.getAdviceofRefusal(msgRef);
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
					return "input";
				
				}
	}
	
	private void setALLValueTODTO(LCCanonicalDto obj)
	{
				
		lcCanonicalDto.setLcNumber(obj.getLcNumber());
		lcCanonicalDto.setRelatedReference(obj.getRelatedReference());
		lcCanonicalDto.setLcAccId(obj.getLcAccId());
		lcCanonicalDto.setLcAckDate(obj.getLcAckDate());
		lcCanonicalDto.setCurrency(obj.getCurrency());
		lcCanonicalDto.setLcCurrency(obj.getLcCurrency());
		lcCanonicalDto.setClaimCurrency(obj.getClaimCurrency());
		lcCanonicalDto.setLcAmount(obj.getLcAmount());
		lcCanonicalDto.setAmountPaidDate(obj.getAmountPaidDate());
		lcCanonicalDto.setAdviseThroughBankCode(obj.getAdviseThroughBankCode());
		lcCanonicalDto.setAdviseThroughBankLocation(obj.getAdviseThroughBankLocation());
		lcCanonicalDto.setAdviseThroughBankName(obj.getAdviseThroughBankName());
		lcCanonicalDto.setChargeDetails(obj.getChargeDetails());
		lcCanonicalDto.setSendertoReceiverInformation(obj.getSendertoReceiverInformation());
		lcCanonicalDto.setIssuingBankCode(obj.getIssuingBankCode());
		lcCanonicalDto.setAdvisingBank(obj.getAdvisingBank());
		lcCanonicalDto.setMsgRef(obj.getMsgRef());
		lcCanonicalDto.setSeqNo(obj.getSeqNo());
		lcCanonicalDto.setAdviseThroughBankpartyidentifier(obj.getAdviseThroughBankpartyidentifier());
	}
	
public String saveAdviceofRefusalTemplate() {
		
		try {			
			String tempRefKey="";
			PendingAuthorizationService pendingAuthorizationService = (PendingAuthorizationService)ApplicationContextProvider.getBean("pendingAuthorizationService");
			String userId = (String)session.get(WebConstants.CONSTANT_USER_NAME);
			
			tempRefKey = pendingAuthorizationService.getTempRef(serializeObject(),"734","XXX",tempName.toUpperCase(),"Advice of Refusal(MT-734)",userId);
			
			pendingAuthorizationService.delimitedTempStringValue(tempRefKey, 1+"", "lcCurrency"+"~"+lcCanonicalDto.getLcCurrency());
			pendingAuthorizationService.delimitedTempStringValue(tempRefKey, 2+"", "claimCurrency"+"~"+lcCanonicalDto.getClaimCurrency());

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
public String viewTemplateAdviceofRefusal()
{
	
		try{
			String msgRef = (String) session.get("messageRef");
			LetterOfCreditService letterOfCreditService =  (LetterOfCreditService)  ApplicationContextProvider.getBean("letterOfCreditService");
			LCCanonicalDto canonicalDto = letterOfCreditService.getAdviceofRefusal(msgRef);
			if(canonicalDto.getLcNumber()!=null && StringUtils.isNotBlank(canonicalDto.getLcNumber()) && StringUtils.isNotEmpty(canonicalDto.getLcNumber())){	
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
				return "input";
			
			}
}
		
	@SkipValidation
		public String displayPrintPreviewAdviceofRefusal()
		{
				String tempScreenString =null;
				String userId =null;
				String msgRef = (String) session.get("messageIndex");
				PendingAuthorizationService pendingAuthorizationService = (PendingAuthorizationService)ApplicationContextProvider.getBean("pendingAuthorizationService");
			try{
				if(getHiddenTxnRef()!=null && !getHiddenTxnRef().isEmpty())// || getTempTxnRef()!=null && !getTempTxnRef().isEmpty())
					{
						setCheckForSubmit("Display_Approve_Reject");
						//userId= pendingAuthorizationService.getCreatedUser(getTempTxnRef());
						userId= pendingAuthorizationService.getCreatedUser(getHiddenTxnRef());
						String userRole = pendingAuthorizationService.getUserType((String)session.get(WebConstants.CONSTANT_USER_NAME));
						if((((String)session.get(WebConstants.CONSTANT_USER_NAME)).equals(userId) || userRole.equals("T"))){
							setValidUserToApprove(false);
						} else{
							setValidUserToApprove(true);
						}
					    //tempScreenString =pendingAuthorizationService.getScreenReturn(getTempTxnRef());
						tempScreenString =pendingAuthorizationService.getScreenReturn(getHiddenTxnRef());
						LCCanonicalDto temp = getSerializedObject(tempScreenString);
	
						lcCanonicalDto.setLcNumber(temp.getLcNumber());
						lcCanonicalDto.setRelatedReference(temp.getRelatedReference());
						lcCanonicalDto.setLcAckDate(temp.getLcAckDate());
						lcCanonicalDto.setCurrency(temp.getCurrency());
						lcCanonicalDto.setLcCurrency(temp.getLcCurrency());
						lcCanonicalDto.setClaimCurrency(temp.getClaimCurrency());
						lcCanonicalDto.setLcAmount(temp.getLcAmount());
						lcCanonicalDto.setAmountPaidDate(temp.getAmountPaidDate());
						lcCanonicalDto.setChargesClaimed(temp.getChargesClaimed());
						lcCanonicalDto.setLcClaimAmount(temp.getLcClaimAmount());
						lcCanonicalDto.setLcDispoDocs(temp.getLcDispoDocs());
						lcCanonicalDto.setDiscrepancies(temp.getDiscrepancies());
						lcCanonicalDto.setAdviseThroughBankCode(temp.getAdviseThroughBankCode());
						lcCanonicalDto.setAdviseThroughBankLocation(temp.getAdviseThroughBankLocation());
						lcCanonicalDto.setAdviseThroughBankName(temp.getAdviseThroughBankName());
						lcCanonicalDto.setChargeDetails(temp.getChargeDetails());
						lcCanonicalDto.setSendertoReceiverInformation(temp.getSendertoReceiverInformation());
						lcCanonicalDto.setIssuingBankCode(temp.getIssuingBankCode());
						lcCanonicalDto.setAdvisingBank(temp.getAdvisingBank());
						lcCanonicalDto.setSeqNo(temp.getSeqNo());
						lcCanonicalDto.setAdviseThroughBankpartyidentifier(temp.getAdviseThroughBankpartyidentifier());
						//setPrintPreviewTxnRef(getTempTxnRef());
						setPrintPreviewTxnRef(getHiddenTxnRef());
				}
				else if(msgRef!=null && !msgRef.isEmpty())
				{
					viewAdviceofRefusal();
					return "success";
				}
				else
				{
					lcCanonicalDto.setLcNumber(lcCanonicalDto.getLcNumber());
					lcCanonicalDto.setRelatedReference(lcCanonicalDto.getRelatedReference());
					lcCanonicalDto.setLcAckDate(lcCanonicalDto.getLcAckDate());
					lcCanonicalDto.setCurrency(lcCanonicalDto.getCurrency());
					lcCanonicalDto.setLcCurrency(lcCanonicalDto.getLcCurrency());
					lcCanonicalDto.setClaimCurrency(lcCanonicalDto.getClaimCurrency());
					lcCanonicalDto.setLcAmount(lcCanonicalDto.getLcAmount());
					lcCanonicalDto.setAmountPaidDate(lcCanonicalDto.getAmountPaidDate());
					lcCanonicalDto.setChargesClaimed(lcCanonicalDto.getChargesClaimed());
					lcCanonicalDto.setLcClaimAmount(lcCanonicalDto.getLcClaimAmount());
					lcCanonicalDto.setLcDispoDocs(lcCanonicalDto.getLcDispoDocs());
					lcCanonicalDto.setDiscrepancies(lcCanonicalDto.getDiscrepancies());
					lcCanonicalDto.setAdviseThroughBankCode(lcCanonicalDto.getAdviseThroughBankCode());
					lcCanonicalDto.setAdviseThroughBankLocation(lcCanonicalDto.getAdviseThroughBankLocation());
					lcCanonicalDto.setAdviseThroughBankName(lcCanonicalDto.getAdviseThroughBankName());
					lcCanonicalDto.setChargeDetails(lcCanonicalDto.getChargeDetails());
					lcCanonicalDto.setSendertoReceiverInformation(lcCanonicalDto.getSendertoReceiverInformation());
					lcCanonicalDto.setIssuingBankCode(lcCanonicalDto.getIssuingBankCode());
					lcCanonicalDto.setAdvisingBank(lcCanonicalDto.getAdvisingBank());
					lcCanonicalDto.setSeqNo(lcCanonicalDto.getSeqNo());
					lcCanonicalDto.setAdviseThroughBankpartyidentifier(lcCanonicalDto.getAdviseThroughBankpartyidentifier());
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
	public String exportToExcelAdviceofRefusal() throws Exception
	{		
	try{
		displayPrintPreviewAdviceofRefusal();
		String reportFile = "Advice_of_Refusal_(MT-734)";
		SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyyHHmmss");
		String currentDateTime = sdf.format(new Date());
		String reportFileName = reportFile+"_"+currentDateTime+"".concat(".xls");
		setReportFile(reportFileName);
		System.out.println("reportFileName is "+reportFileName);
		ExportToExcelUtil exportToExcelUtil = new ExportToExcelUtil();
		HSSFWorkbook myWorkBook = exportToExcelUtil.generateExportToExcel(lcCanonicalDto, null, null, reportFile, 734, "XXX");
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
	public String generatePDFAdviceofRefusal()
	{
		displayPrintPreviewAdviceofRefusal();
		String reportName ="Advice of Refusal (MT-734)";
		Map<String,String> fieldNamesMap = new HashMap<String,String>();
		
		try{
			
			fieldNamesMap.put("label.MB.IssuingBranchIFSC", getText("label.MB.IssuingBranchIFSC"));
			fieldNamesMap.put("label.MB.Benefifsc", getText("label.MB.Benefifsc"));
			
			fieldNamesMap.put("label.734.ppSenderBanksReference", getText("label.734.ppSenderBanksReference"));
			fieldNamesMap.put("label.734.ppReceiverBanksReference", getText("label.734.ppReceiverBanksReference"));
			fieldNamesMap.put("label.734.ppUtilizationDate", getText("label.734.ppUtilizationDate"));
			fieldNamesMap.put("label.734.ppCurrency", getText("label.734.ppCurrency"));
			fieldNamesMap.put("label.734.ppUtilizationAmount", getText("label.734.ppUtilizationAmount"));
			fieldNamesMap.put("label.734.ppChargesClaimed", getText("label.734.ppChargesClaimed"));
			fieldNamesMap.put("label.734.ppchargeAmount", getText("label.734.ppchargeAmount"));
			fieldNamesMap.put("label.734.ppamountPaidDate", getText("label.734.ppamountPaidDate"));
			fieldNamesMap.put("label.734.ppcurrency", getText("label.734.ppcurrency"));
			fieldNamesMap.put("label.734.ppTotalAmountClaimed", getText("label.734.ppTotalAmountClaimed"));
			fieldNamesMap.put("label.734.ppaccountWithPartyIdentifier1", getText("label.734.ppaccountWithPartyIdentifier1"));
			fieldNamesMap.put("label.734.ppaccountWithBank", getText("label.734.ppaccountWithBank"));
			fieldNamesMap.put("label.734.ppaccountWithPartyLocation", getText("label.734.ppaccountWithPartyLocation"));
			fieldNamesMap.put("label.734.ppaccountWithNameAndAddress", getText("label.734.ppaccountWithNameAndAddress"));
			fieldNamesMap.put("label.734.ppSendertoReceiverInformation", getText("label.734.ppSendertoReceiverInformation"));
			fieldNamesMap.put("label.734.ppDiscrepancies", getText("label.734.ppDiscrepancies"));
			fieldNamesMap.put("label.734.ppDisposalDocs", getText("label.734.ppDisposalDocs"));
			
			PaymentPDFGeneratationUtil paymentPDFGeneratationUtil = new PaymentPDFGeneratationUtil();
			paymentPDFGeneratationUtil.setServletRequest(servletRequest);
			paymentPDFGeneratationUtil.setReportName(reportName);
			
			paymentPDFGeneratationUtil.generatePaymentPDFReport(lcCanonicalDto,null, null, fieldNamesMap, 734, "XXX");
		}
		catch (Exception exception) {
			AuditServiceUtil.logException(exception,logger);
		}
		addActionError("Unable to Generate PDF file! Please try again");
		return "input";
	}
}
