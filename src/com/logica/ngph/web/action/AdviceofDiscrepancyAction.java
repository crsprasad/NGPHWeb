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
public class AdviceofDiscrepancyAction extends ActionSupport implements ModelDriven<LCCanonicalDto>, SessionAware, ServletRequestAware {
	
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
	private String tempRef;
	private String tempName;
	private String msgHost;
	private String seqNo;
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

	
	
	@SkipValidation
	public String displayAdviceofDiscrepancy()
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
			addFieldError("lcNumber","Sender's Reference(20) Must Not start with /");
		} else if (lcCanonicalDto.getLcNumber().endsWith("/")) {
			addFieldError("lcNumber","Sender's Reference(20) must not End with /");		
		} else if (lcCanonicalDto.getLcNumber().contains("//")) {
			addFieldError("lcNumber","Sender's Reference(20) must not contain two consecutive slashes '//'");
		}

		if (lcCanonicalDto.getRelatedReference().startsWith("/")) {
			addFieldError("relatedReference","Related Reference(21) Must Not start with /");
		} else if (lcCanonicalDto.getRelatedReference().endsWith("/")) {
			addFieldError("relatedReference","Related Reference(21) must not End with /");
		} else if (lcCanonicalDto.getRelatedReference().contains("//")) {
			addFieldError("relatedReference","Related Reference(21) must not contain two consecutive slashes '//'");
		}

		if(lcCanonicalDto.getMsgCurrency()!=null && StringUtils.isNotEmpty(lcCanonicalDto.getMsgCurrency()))
		{
			if(lcCanonicalDto.getCurrency()!=null && StringUtils.isNotEmpty(lcCanonicalDto.getCurrency()))
			{
				if(!lcCanonicalDto.getMsgCurrency().equalsIgnoreCase(lcCanonicalDto.getCurrency()))
				{
					addFieldError("lcCurrency","The currency code in the amount fields 32B and 34B must be the same");
				}
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
		lcCanonicalDto.setMsgCurrency("");
		lcCanonicalDto.setPrincipalAmount(BigDecimal.ZERO);
		lcCanonicalDto.setAdditionalAmount(BigDecimal.ZERO);
		lcCanonicalDto.setTotalAmount(BigDecimal.ZERO);
		lcCanonicalDto.setAmountPaidDate(null);
		lcCanonicalDto.setChargesDeducted("");
		lcCanonicalDto.setChargesAdded("");
		lcCanonicalDto.setDiscrepancies("");
		lcCanonicalDto.setAdviseThroughBankpartyidentifier("");
		lcCanonicalDto.setAdviseThroughBankCode("");
		lcCanonicalDto.setAccountWithPartyNameAndAddress("");
		lcCanonicalDto.setAccountWithPartyLoc("");
		lcCanonicalDto.setAdviseThroughBankName("");
		lcCanonicalDto.setChargeDetails("");
		lcCanonicalDto.setSendertoReceiverInformation("");
		lcCanonicalDto.setIssuingBankCode("");
		lcCanonicalDto.setAdvisingBank("");
		lcCanonicalDto.setMsgHost("");
		lcCanonicalDto.setSeqNo("");
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


	public String getAdviceofDiscrepancyApproval() {
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
				txnKey = pendingAuthorizationService.getTranRef(serializeObject(),"Advice of Discrepancy(MT-750)",userId);
				pendingAuthorizationService.delimitedStringValue(txnKey, 1+"", "msgCurrency"+"~"+lcCanonicalDto.getMsgCurrency());
				pendingAuthorizationService.delimitedStringValue(txnKey, 2+"", "lcCurrency"+"~"+lcCanonicalDto.getLcCurrency());
				pendingAuthorizationService.delimitedStringValue(txnKey, 3+"", "currency"+"~"+lcCanonicalDto.getCurrency());
				eventLogging.doEventLogging(userId, "Advice of Discrepancy", ConstantUtil.EVENTID_ADVICE_OF_DISCREPANCY+ConstantUtil.EVENTID_SUBMIT, ConstantUtil.EVENTLOGGINGCOMMENTSUBMIT,lcCanonicalDto.getLcNumber(),lcCanonicalDto.getMsgRef());
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
	
	public String getObjectAdviceofDiscrepancy()
	{
		try{
			String userId = (String)session.get(WebConstants.CONSTANT_USER_NAME);
			PendingAuthorizationService pendingAuthorizationService = (PendingAuthorizationService)ApplicationContextProvider.getBean("pendingAuthorizationService");
			PaymentMessageService paymentMessageService =(PaymentMessageService)ApplicationContextProvider.getBean("paymentMessageService");
			EventLogging eventLogging = (EventLogging)ApplicationContextProvider.getBean("eventLogging");
			if(getSaveAction().equalsIgnoreCase("Approve")){			
				LetterOfCreditService letterOfCreditService =  (LetterOfCreditService)  ApplicationContextProvider.getBean("letterOfCreditService");
				letterOfCreditService.saveLC(lcCanonicalDto,null,"AdviceofDiscrepancy",userId,lcCanonicalDto.getRepair());
				pendingAuthorizationService.changeStatus(getSaveAction(), getHiddenTxnRef());
				if(StringUtils.isBlank(lcCanonicalDto.getRepair()) && StringUtils.isEmpty(lcCanonicalDto.getRepair())){
					pendingAuthorizationService.changeStatus(getSaveAction(), getHiddenTxnRef());
					eventLogging.doEventLogging(userId, "Advice of Discrepancy", ConstantUtil.EVENTID_ADVICE_OF_DISCREPANCY+ConstantUtil.EVENTID_APPROVE, ConstantUtil.EVENTLOGGINGCOMMENTAPPROVAL,lcCanonicalDto.getLcNumber(),lcCanonicalDto.getMsgRef());
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
					eventLogging.doEventLogging(userId, "Advice of Discrepancy", ConstantUtil.EVENTID_ADVICE_OF_DISCREPANCY+ConstantUtil.EVENTID_REJECT, ConstantUtil.EVENTLOGGINGCOMMENTREJECT,lcCanonicalDto.getLcNumber(),lcCanonicalDto.getMsgRef());
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
	public String getLCAdviseofDiscrepancyAuthorization() {
		  String tempScreenString =null;
		PendingAuthorizationService pendingAuthorizationService = (PendingAuthorizationService) ApplicationContextProvider.getBean("pendingAuthorizationService");
		try {
			
			if(getTxnRef()!=null){
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
			lcCanonicalDto.setCurrency(temp.getCurrency());
			lcCanonicalDto.setLcCurrency(temp.getLcCurrency());
			lcCanonicalDto.setMsgCurrency(temp.getMsgCurrency());
			lcCanonicalDto.setPrincipalAmount(temp.getPrincipalAmount());
			lcCanonicalDto.setAdditionalAmount(temp.getAdditionalAmount());
			lcCanonicalDto.setTotalAmount(temp.getTotalAmount());
			lcCanonicalDto.setAmountPaidDate(temp.getAmountPaidDate());
			lcCanonicalDto.setChargesDeducted(temp.getChargesDeducted());
			lcCanonicalDto.setChargesAdded(temp.getChargesAdded());
			lcCanonicalDto.setDiscrepancies(temp.getDiscrepancies());
			lcCanonicalDto.setAdviseThroughBankpartyidentifier(temp.getAdviseThroughBankpartyidentifier());
			lcCanonicalDto.setAdviseThroughBankCode(temp.getAdviseThroughBankCode());
			lcCanonicalDto.setAccountWithPartyNameAndAddress(temp.getAccountWithPartyNameAndAddress());
			lcCanonicalDto.setAccountWithPartyLoc(temp.getAccountWithPartyLoc());
			lcCanonicalDto.setAdviseThroughBankName(temp.getAdviseThroughBankName());
			lcCanonicalDto.setChargeDetails(temp.getChargeDetails());
			lcCanonicalDto.setSendertoReceiverInformation(temp.getSendertoReceiverInformation());
			lcCanonicalDto.setIssuingBankCode(temp.getIssuingBankCode());
			lcCanonicalDto.setAdvisingBank(temp.getAdvisingBank());
			lcCanonicalDto.setMsgHost(temp.getMsgHost());
			lcCanonicalDto.setSeqNo(temp.getSeqNo());
			
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
	@SkipValidation
	public String viewAdviceofDiscrepancy()
	{
		displayAdviceofDiscrepancy();
			try{
				String msgRef = (String) session.get("messageIndex");
				
				LetterOfCreditService letterOfCreditService =  (LetterOfCreditService)  ApplicationContextProvider.getBean("letterOfCreditService");
				LCCanonicalDto canonicalDto = letterOfCreditService.getAdviceofDiscrepancy(msgRef);
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
		
		LCCanonicalDto  lcCanonicalDTo =  obj;
		
		lcCanonicalDto.setLcNumber(lcCanonicalDTo.getLcNumber());
		lcCanonicalDto.setRelatedReference(lcCanonicalDTo.getRelatedReference());
		lcCanonicalDto.setCurrency(lcCanonicalDTo.getCurrency());
		lcCanonicalDto.setLcCurrency(lcCanonicalDTo.getLcCurrency());
		lcCanonicalDto.setMsgCurrency(lcCanonicalDTo.getMsgCurrency());
		lcCanonicalDto.setPrincipalAmount(lcCanonicalDTo.getPrincipalAmount());
		lcCanonicalDto.setAdditionalAmount(lcCanonicalDTo.getAdditionalAmount());
		lcCanonicalDto.setTotalAmount(lcCanonicalDTo.getTotalAmount());
		lcCanonicalDto.setAmountPaidDate(lcCanonicalDTo.getAmountPaidDate());
		lcCanonicalDto.setChargesDeducted(lcCanonicalDTo.getChargesDeducted());
		lcCanonicalDto.setChargesAdded(lcCanonicalDTo.getChargesAdded());
		lcCanonicalDto.setDiscrepancies(lcCanonicalDTo.getDiscrepancies());
		lcCanonicalDto.setAdviseThroughBankpartyidentifier(lcCanonicalDTo.getAdviseThroughBankpartyidentifier());
		lcCanonicalDto.setAdviseThroughBankCode(lcCanonicalDTo.getAdviseThroughBankCode());
		lcCanonicalDto.setAccountWithPartyLoc(lcCanonicalDTo.getAccountWithPartyLoc());
		lcCanonicalDto.setAdviseThroughBankName(lcCanonicalDTo.getAdviseThroughBankName());
		lcCanonicalDto.setAccountWithPartyNameAndAddress(lcCanonicalDTo.getAccountWithPartyNameAndAddress());
		lcCanonicalDto.setChargeDetails(lcCanonicalDTo.getChargeDetails());
		lcCanonicalDto.setSendertoReceiverInformation(lcCanonicalDTo.getSendertoReceiverInformation());
		lcCanonicalDto.setIssuingBankCode(lcCanonicalDTo.getIssuingBankCode());
		lcCanonicalDto.setAdvisingBank(lcCanonicalDTo.getAdvisingBank());
		lcCanonicalDto.setMsgHost(lcCanonicalDTo.getMsgHost());
		lcCanonicalDto.setSeqNo(lcCanonicalDTo.getSeqNo());
		lcCanonicalDto.setMsgHost(lcCanonicalDTo.getMsgHost());
	
	}
	
public String saveTemplateAdviceofDiscrepancy() {
		
		try {			
			String tempRefKey="";
			PendingAuthorizationService pendingAuthorizationService = (PendingAuthorizationService)ApplicationContextProvider.getBean("pendingAuthorizationService");
			String userId = (String)session.get(WebConstants.CONSTANT_USER_NAME);
			
			tempRefKey = pendingAuthorizationService.getTempRef(serializeObject(),"750","XXX",tempName,"Advice of Discrepancy(MT-750)",userId);
			pendingAuthorizationService.delimitedTempStringValue(tempRefKey, 1+"", "msgCurrency"+"~"+lcCanonicalDto.getMsgCurrency());
			pendingAuthorizationService.delimitedTempStringValue(tempRefKey, 2+"", "lcCurrency"+"~"+lcCanonicalDto.getLcCurrency());
			pendingAuthorizationService.delimitedTempStringValue(tempRefKey, 3+"", "currency"+"~"+lcCanonicalDto.getCurrency());

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
public String viewTemplateAdviceofDiscrepancy()
{
		try{
			String msgRef = (String) session.get("messageRef");
			LetterOfCreditService letterOfCreditService =  (LetterOfCreditService)  ApplicationContextProvider.getBean("letterOfCreditService");
			LCCanonicalDto canonicalDto = letterOfCreditService.getAdviceofDiscrepancy(msgRef);
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
public String displayPrintPreviewAdviceofDiscrepancy()
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
		lcCanonicalDto.setCurrency(temp.getCurrency());
		lcCanonicalDto.setLcCurrency(temp.getLcCurrency());
		lcCanonicalDto.setMsgCurrency(temp.getMsgCurrency());
		lcCanonicalDto.setPrincipalAmount(temp.getPrincipalAmount());
		lcCanonicalDto.setAdditionalAmount(temp.getAdditionalAmount());
		lcCanonicalDto.setTotalAmount(temp.getTotalAmount());
		lcCanonicalDto.setAmountPaidDate(temp.getAmountPaidDate());
		lcCanonicalDto.setChargesDeducted(temp.getChargesDeducted());
		lcCanonicalDto.setChargesAdded(temp.getChargesAdded());
		lcCanonicalDto.setDiscrepancies(temp.getDiscrepancies());
		lcCanonicalDto.setAdviseThroughBankpartyidentifier(temp.getAdviseThroughBankpartyidentifier());
		lcCanonicalDto.setAdviseThroughBankCode(temp.getAdviseThroughBankCode());
		lcCanonicalDto.setAccountWithPartyLoc(temp.getAccountWithPartyLoc());
		lcCanonicalDto.setAccountWithPartyNameAndAddress(temp.getAccountWithPartyNameAndAddress());
		lcCanonicalDto.setAdviseThroughBankName(temp.getAdviseThroughBankName());
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
		viewAdviceofDiscrepancy();
		return "success";
	}
	else
	{
		lcCanonicalDto.setLcNumber(lcCanonicalDto.getLcNumber());
		lcCanonicalDto.setRelatedReference(lcCanonicalDto.getRelatedReference());
		lcCanonicalDto.setCurrency(lcCanonicalDto.getCurrency());
		lcCanonicalDto.setLcCurrency(lcCanonicalDto.getLcCurrency());
		lcCanonicalDto.setMsgCurrency(lcCanonicalDto.getMsgCurrency());
		lcCanonicalDto.setPrincipalAmount(lcCanonicalDto.getPrincipalAmount());
		lcCanonicalDto.setAdditionalAmount(lcCanonicalDto.getAdditionalAmount());
		lcCanonicalDto.setTotalAmount(lcCanonicalDto.getTotalAmount());
		lcCanonicalDto.setAmountPaidDate(lcCanonicalDto.getAmountPaidDate());
		lcCanonicalDto.setChargesDeducted(lcCanonicalDto.getChargesDeducted());
		lcCanonicalDto.setChargesAdded(lcCanonicalDto.getChargesAdded());
		lcCanonicalDto.setDiscrepancies(lcCanonicalDto.getDiscrepancies());
		lcCanonicalDto.setAdviseThroughBankpartyidentifier(lcCanonicalDto.getAdviseThroughBankpartyidentifier());
		lcCanonicalDto.setAdviseThroughBankCode(lcCanonicalDto.getAdviseThroughBankCode());
		lcCanonicalDto.setAccountWithPartyLoc(lcCanonicalDto.getAccountWithPartyLoc());
		lcCanonicalDto.setAccountWithPartyNameAndAddress(lcCanonicalDto.getAccountWithPartyNameAndAddress());
		lcCanonicalDto.setAdviseThroughBankName(lcCanonicalDto.getAdviseThroughBankName());
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
	public String exportToExcelAdviceofDiscrepancy() throws Exception
	{		
	try{
		displayPrintPreviewAdviceofDiscrepancy();
		String reportFile = "Advice_of_Discrepancy_(MT-750)";
		SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyyHHmmss");
		String currentDateTime = sdf.format(new Date());
		String reportFileName = reportFile+"_"+currentDateTime+"".concat(".xls");
		setReportFile(reportFileName);
		System.out.println("reportFileName is "+reportFileName);
		ExportToExcelUtil exportToExcelUtil = new ExportToExcelUtil();
		HSSFWorkbook myWorkBook = exportToExcelUtil.generateExportToExcel(lcCanonicalDto, null, null, reportFile, 750, "XXX");
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
	public String generatePDFAdviceofDiscrepancy()
	{
		displayPrintPreviewAdviceofDiscrepancy();
		String reportName ="Advice of Discrepancy (MT-750)";
		Map<String,String> fieldNamesMap = new HashMap<String,String>();
		
		try{
			
			fieldNamesMap.put("label.MB.IssuingBranchIFSC", getText("label.MB.IssuingBranchIFSC"));
			fieldNamesMap.put("label.MB.Benefifsc", getText("label.MB.Benefifsc"));
			
			fieldNamesMap.put("label.750.ppSenderBanksReference", getText("label.750.ppSenderBanksReference"));
			fieldNamesMap.put("label.750.pprelatedReferenceNumber", getText("label.750.pprelatedReferenceNumber"));
			fieldNamesMap.put("label.750.ppCurrency", getText("label.750.ppCurrency"));
			fieldNamesMap.put("label.750.ppPrincipalAmountClaimed", getText("label.750.ppPrincipalAmountClaimed"));
			fieldNamesMap.put("label.750.ppcurrency", getText("label.750.ppcurrency"));
			fieldNamesMap.put("label.750.ppadditionalAmounts", getText("label.750.ppadditionalAmounts"));
			fieldNamesMap.put("label.750.ppChargesDeducted", getText("label.750.ppChargesDeducted"));
			fieldNamesMap.put("label.750.ppChargesAdded", getText("label.750.ppChargesAdded"));
			fieldNamesMap.put("label.750.ppCUrrency", getText("label.750.ppCUrrency"));
			fieldNamesMap.put("label.750.ppTotalAmountPaid", getText("label.750.ppTotalAmountPaid"));
			fieldNamesMap.put("label.750.ppaccountWithPartyIdentifier1", getText("label.750.ppaccountWithPartyIdentifier1"));
			fieldNamesMap.put("label.750.ppaccountWithBank", getText("label.750.ppaccountWithBank"));
			fieldNamesMap.put("label.750.ppaccountWithPartyLocation", getText("label.750.ppaccountWithPartyLocation"));
			fieldNamesMap.put("label.750.ppaccountWithNameAndAddress", getText("label.750.ppaccountWithNameAndAddress"));
			fieldNamesMap.put("label.750.ppSendertoReceiverInformation", getText("label.750.ppSendertoReceiverInformation"));
			fieldNamesMap.put("label.750.ppDiscrepancies", getText("label.750.ppDiscrepancies"));
			
			PaymentPDFGeneratationUtil paymentPDFGeneratationUtil = new PaymentPDFGeneratationUtil();
			paymentPDFGeneratationUtil.setServletRequest(servletRequest);
			paymentPDFGeneratationUtil.setReportName(reportName);
			
			paymentPDFGeneratationUtil.generatePaymentPDFReport(lcCanonicalDto, null, null, fieldNamesMap, 750, "XXX");
		}
		catch (Exception exception) {
			AuditServiceUtil.logException(exception,logger);
		}
		addActionError("Unable to Generate PDF file! Please try again");
		return "input";
	}
	
}
