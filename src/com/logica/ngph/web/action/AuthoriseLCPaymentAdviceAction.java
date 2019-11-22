package com.logica.ngph.web.action;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.EOFException;
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
import com.logica.ngph.dtos.BankGuaranteeCoverDto;
import com.logica.ngph.dtos.CreateBankGuaranteeDto;
import com.logica.ngph.dtos.LCCanonicalDto;
import com.logica.ngph.service.AdviceLCPaymentService;
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

public class AuthoriseLCPaymentAdviceAction extends ActionSupport implements ModelDriven<LCCanonicalDto>,SessionAware, ServletRequestAware{
	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(AuthoriseLCPaymentAdviceAction.class);
	private Map<String, Object> session;
	private String saveAction;
	private String checkForSubmit;
	private boolean validUserToApprove;
	private String hiddenTxnRef;
	private String txnRef;
	private String tempName;
	private String tempRef;
	private String printPreviewTxnRef;
	private String reportFile;
	private InputStream inputStream;
	private static List<String> currCodeDropDown= new ArrayList<String>();
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
		this.session.put("currCodeDropDown", currCodeDropDown);
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

	public String getTempRef() {
		return tempRef;
	}

	public void setTempRef(String tempRef) {
		this.tempRef = tempRef;
	}

	public String getTempName() {
		return tempName;
	}

	public void setTempName(String tempName) {
		this.tempName = tempName;
	}

	LCCanonicalDto authoriseLCPaymentAdviceDto =new LCCanonicalDto();
	



	@SkipValidation
	public String displayAuthoLCPaymentAdviceData()
	{
			return "success";
		
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
	

	
	public String serializeObject()
	{
		
		LCCanonicalDto canonicalDto = new LCCanonicalDto();
		try{
			String userId = (String)session.get(WebConstants.CONSTANT_USER_NAME);
			
			canonicalDto = authoriseLCPaymentAdviceDto;
			System.out.print("LC NUMBER :"+canonicalDto.getLcNumber());
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
	
	private LCCanonicalDto getSerializedObject(String objectString){
		
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
            return testDTO;
		}
		catch (EOFException endOfJournal) {
			endOfJournal.printStackTrace();
			return null;
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public String getAuthoriseLCAdvicePayment()
	{
		try{
				String txnKey="";
				PendingAuthorizationService pendingAuthorizationService = (PendingAuthorizationService)ApplicationContextProvider.getBean("pendingAuthorizationService");
				EventLogging eventLogging = (EventLogging)ApplicationContextProvider.getBean("eventLogging");
				String userId = (String)session.get(WebConstants.CONSTANT_USER_NAME);
				if(StringUtils.isBlank(authoriseLCPaymentAdviceDto.getRepair()) && StringUtils.isEmpty(authoriseLCPaymentAdviceDto.getRepair())){
					if(!getHiddenTxnRef().isEmpty())
					{
						pendingAuthorizationService.updateRejectStatusToPending(getHiddenTxnRef());
					}else
					{
						txnKey = pendingAuthorizationService.getTranRef(serializeObject(),"Authorisation to Reimburse(MT-740)",userId);
						pendingAuthorizationService.delimitedStringValue(txnKey, 1+"", "lcCurrency"+"~"+authoriseLCPaymentAdviceDto.getLcCurrency());
						eventLogging.doEventLogging(userId, "Authorisation to Reimburse", ConstantUtil.EVENTID_LC_AUTHORIZE+ConstantUtil.EVENTID_SUBMIT, ConstantUtil.EVENTLOGGINGCOMMENTSUBMIT,authoriseLCPaymentAdviceDto.getLcNumber(),authoriseLCPaymentAdviceDto.getMsgRef());
					}
					
				}else{
					PaymentMessageService paymentMessageService =(PaymentMessageService)ApplicationContextProvider.getBean("paymentMessageService");
					paymentMessageService.changeMsgStatus2to99(authoriseLCPaymentAdviceDto.getMsgRef());
					txnKey = pendingAuthorizationService.getTranRef(serializeObject(),"Authorisation to Reimburse",userId);
					eventLogging.doEventLogging(userId, "Authorisation to Reimburse", ConstantUtil.EVENTID_LC_AUTHORIZE+ConstantUtil.EVENTID_REPAIR_SUBMIT, ConstantUtil.EVENTLOGGINGCOMMENTSUBMIT+" "+ConstantUtil.EVENTLOGGINGCOMMENTREAPIR,authoriseLCPaymentAdviceDto.getLcNumber(),authoriseLCPaymentAdviceDto.getMsgRef());
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
	
	@SkipValidation
	public String getAuthoLCPaymentAdviceAuthorization()
	{
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
		LCCanonicalDto temp= getSerializedObject(tempScreenString);
		 authoriseLCPaymentAdviceDto.setLcNumber(temp.getLcNumber());
		 authoriseLCPaymentAdviceDto.setAcountID(temp.getAcountID());
		 authoriseLCPaymentAdviceDto.setApplicableRule(temp.getApplicableRule());
		 authoriseLCPaymentAdviceDto.setLcExpiryDate(temp.getLcExpiryDate());
		 authoriseLCPaymentAdviceDto.setLcExpirePlace(temp.getLcExpirePlace());
		 authoriseLCPaymentAdviceDto.setNegotiatingBankPartyIdentifier(temp.getNegotiatingBankPartyIdentifier());
		 authoriseLCPaymentAdviceDto.setNegotiatingBankAccount(temp.getNegotiatingBankAccount());
		 authoriseLCPaymentAdviceDto.setNegotiatingBankCode(temp.getNegotiatingBankCode());
		 authoriseLCPaymentAdviceDto.setNegotiatingBankNameAndAddress(temp.getNegotiatingBankNameAndAddress());
		 authoriseLCPaymentAdviceDto.setBeneficiaryNameAddress(temp.getBeneficiaryNameAddress());
		 authoriseLCPaymentAdviceDto.setCreditAmount(temp.getCreditAmount());
		 authoriseLCPaymentAdviceDto.setPositiveTolerance(temp.getPositiveTolerance());
		 authoriseLCPaymentAdviceDto.setNegativeTolerance(temp.getNegativeTolerance());
		 authoriseLCPaymentAdviceDto.setMaximumCreditAmount(temp.getMaximumCreditAmount());
		 authoriseLCPaymentAdviceDto.setAdditionalAmountsCovered(temp.getAdditionalAmountsCovered());
		 authoriseLCPaymentAdviceDto.setAvailableWithIdentifier(temp.getAvailableWithIdentifier());
		 authoriseLCPaymentAdviceDto.setAuthorisedBankCode(temp.getAuthorisedBankCode());
		 authoriseLCPaymentAdviceDto.setAuthorisationMode(temp.getAuthorisationMode());
		 authoriseLCPaymentAdviceDto.setAuthorisedAndAddress(temp.getAuthorisedAndAddress());
		 authoriseLCPaymentAdviceDto.setDraftsAt(temp.getDraftsAt());
		 authoriseLCPaymentAdviceDto.setDraweeBankpartyidentifier(temp.getDraweeBankpartyidentifier());
		 authoriseLCPaymentAdviceDto.setDraweeBankAccount(temp.getDraweeBankAccount());
		 authoriseLCPaymentAdviceDto.setDraweeBankCode(temp.getDraweeBankCode());
		 authoriseLCPaymentAdviceDto.setDraweeBankNameAddress(temp.getDraweeBankNameAddress());
		 authoriseLCPaymentAdviceDto.setMixedPaymentDetails(temp.getMixedPaymentDetails());
		 authoriseLCPaymentAdviceDto.setDeferredPaymentDetails(temp.getDeferredPaymentDetails());
		 authoriseLCPaymentAdviceDto.setReimbursingBanksCharges(temp.getReimbursingBanksCharges());
		 authoriseLCPaymentAdviceDto.setOtherCharges(temp.getOtherCharges());
		 authoriseLCPaymentAdviceDto.setSendertoReceiverInformation(temp.getSendertoReceiverInformation());
	     authoriseLCPaymentAdviceDto.setIssuingBankCode(temp.getIssuingBankCode());
	     authoriseLCPaymentAdviceDto.setAdvisingBank(temp.getAdvisingBank()); 
	     authoriseLCPaymentAdviceDto.setRepair(temp.getRepair());
	     
	     if(getTxnRef()!=null)
			{
				List multiCurrCodeList = pendingAuthorizationService.getMulScreenData(getTxnRef());
				
				for(int i=0;i<multiCurrCodeList.size();i++)
				{
					Clob tempCurrCodeList = (Clob)multiCurrCodeList.get(i);
					String data[]= tempCurrCodeList.getSubString(1, (int) tempCurrCodeList.length()).toString().split("~");
					if(data[0].toString().trim().equals("lcCurrency")  && !data[0].toString().trim().equals(""))
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
					if(data[0].toString().trim().equals("lcCurrency")  && !data[0].toString().trim().equals(""))
					{
						currCodeDropDown.add(data[1].toString());
					}
					
				}
			}
	     authoriseLCPaymentAdviceDto.setLcCurrency(temp.getLcCurrency());
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
	
	private String RepairData;

	public String getRepairData() {
		return RepairData;
	}

	public void setRepairData(String repairData) {
		RepairData = repairData;
	}
	public String getObjectForAuthoLCPaymentAdvice()
	{
		try{
			System.out.println("inside getObjectForAuthoLCPaymentAdvice ");
			String userId = (String)session.get(WebConstants.CONSTANT_USER_NAME);
			PendingAuthorizationService pendingAuthorizationService = (PendingAuthorizationService)ApplicationContextProvider.getBean("pendingAuthorizationService");
			LetterOfCreditService letterOfCreditService =  (LetterOfCreditService)  ApplicationContextProvider.getBean("letterOfCreditService");
			if(getSaveAction().equals("Approve")){
				 String returnString =letterOfCreditService.saveLC(authoriseLCPaymentAdviceDto,null,"AuthPaymentAdvice",userId,authoriseLCPaymentAdviceDto.getRepair());
				if(returnString!=null && !returnString.equals("") ){
					pendingAuthorizationService.changeStatus(getSaveAction(), getHiddenTxnRef());
					return "success";	
				}else{
					addActionError("Unable to perform the operation. Please try again");
					return "input";
					}
				}else{
					if(StringUtils.isBlank(authoriseLCPaymentAdviceDto.getRepair()) && StringUtils.isEmpty(authoriseLCPaymentAdviceDto.getRepair())){
						pendingAuthorizationService.changeStatus(getSaveAction(), getHiddenTxnRef());
					}else{
						PaymentMessageService paymentMessageService =(PaymentMessageService)ApplicationContextProvider.getBean("paymentMessageService");
						paymentMessageService.changeMsgStatus99to2(authoriseLCPaymentAdviceDto.getMsgRef());
						pendingAuthorizationService.changeStatus(getSaveAction(), getHiddenTxnRef());
					}
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
	public String viewAuthorisePayment()
	{
		try{
		displayAuthoriseLcPaymentAdvice();
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
		 authoriseLCPaymentAdviceDto.setLcNumber(temp.getLcNumber());
		 authoriseLCPaymentAdviceDto.setAcountID(temp.getAcountID());
		 authoriseLCPaymentAdviceDto.setApplicableRule(temp.getApplicableRule());
		 authoriseLCPaymentAdviceDto.setLcExpiryDate(temp.getLcExpiryDate());
		 authoriseLCPaymentAdviceDto.setLcExpirePlace(temp.getLcExpirePlace());
		 authoriseLCPaymentAdviceDto.setNegotiatingBankPartyIdentifier(temp.getNegotiatingBankPartyIdentifier());
		 authoriseLCPaymentAdviceDto.setNegotiatingBankAccount(temp.getNegotiatingBankAccount());
		 authoriseLCPaymentAdviceDto.setNegotiatingBankCode(temp.getNegotiatingBankCode());
		 authoriseLCPaymentAdviceDto.setNegotiatingBankNameAndAddress(temp.getNegotiatingBankNameAndAddress());
		 //authoriseLCPaymentAdviceDto.setBeneficiaryAccount(temp.getBeneficiaryAccount());
		 authoriseLCPaymentAdviceDto.setBeneficiaryNameAddress(temp.getBeneficiaryNameAddress());
		 authoriseLCPaymentAdviceDto.setLcCurrency(temp.getLcCurrency());
		 authoriseLCPaymentAdviceDto.setCreditAmount(temp.getCreditAmount());
		 authoriseLCPaymentAdviceDto.setPositiveTolerance(temp.getPositiveTolerance());
		 authoriseLCPaymentAdviceDto.setNegativeTolerance(temp.getNegativeTolerance());
		 authoriseLCPaymentAdviceDto.setMaximumCreditAmount(temp.getMaximumCreditAmount());
		 authoriseLCPaymentAdviceDto.setAdditionalAmountsCovered(temp.getAdditionalAmountsCovered());
		 authoriseLCPaymentAdviceDto.setAvailableWithIdentifier(temp.getAvailableWithIdentifier());
		 authoriseLCPaymentAdviceDto.setAuthorisedBankCode(temp.getAuthorisedBankCode());
		 authoriseLCPaymentAdviceDto.setAuthorisationMode(temp.getAuthorisationMode());
		 authoriseLCPaymentAdviceDto.setAuthorisedAndAddress(temp.getAuthorisedAndAddress());
		 authoriseLCPaymentAdviceDto.setDraftsAt(temp.getDraftsAt());
		 authoriseLCPaymentAdviceDto.setDraweeBankpartyidentifier(temp.getDraweeBankpartyidentifier());
		 authoriseLCPaymentAdviceDto.setDraweeBankAccount(temp.getDraweeBankAccount());
		 authoriseLCPaymentAdviceDto.setDraweeBankCode(temp.getDraweeBankCode());
		 authoriseLCPaymentAdviceDto.setDraweeBankNameAddress(temp.getDraweeBankNameAddress());
		 authoriseLCPaymentAdviceDto.setMixedPaymentDetails(temp.getMixedPaymentDetails());
		 authoriseLCPaymentAdviceDto.setDeferredPaymentDetails(temp.getDeferredPaymentDetails());
		 authoriseLCPaymentAdviceDto.setReimbursingBanksCharges(temp.getReimbursingBanksCharges());
		 authoriseLCPaymentAdviceDto.setOtherCharges(temp.getOtherCharges());
		 authoriseLCPaymentAdviceDto.setSendertoReceiverInformation(temp.getSendertoReceiverInformation());
		 authoriseLCPaymentAdviceDto.setMsgRef(temp.getMsgRef());
	     authoriseLCPaymentAdviceDto.setSeqNo(temp.getSeqNo());
	     authoriseLCPaymentAdviceDto.setMsgHost(temp.getMsgHost());
	     authoriseLCPaymentAdviceDto.setRepair(temp.getRepair());
	     authoriseLCPaymentAdviceDto.setIssuingBankCode(temp.getIssuingBankCode());
	     authoriseLCPaymentAdviceDto.setAdvisingBank(temp.getAdvisingBank()); 
		
	}

	private String lcNumber;
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

	public String getLcNumber() {
		return lcNumber;
	}

	public void setLcNumber(String lcNumber) {
		this.lcNumber = lcNumber;
	}
	public void setMobel(LCCanonicalDto connionicalDto)
	{
		 this.authoriseLCPaymentAdviceDto=connionicalDto;
	}
	public LCCanonicalDto getModel() {
		
		return authoriseLCPaymentAdviceDto;
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
	public String displayAuthoriseLcPaymentAdvice()
	{	
		
			try{
				AuthorizationSchemaMaitenanceService authorizationSchemaMaitenanceService = (AuthorizationSchemaMaitenanceService)ApplicationContextProvider.getBean("authorizationSchemaMaitenanceService");
				setCurrCodeDropDown(authorizationSchemaMaitenanceService.getAuthorizationSchemaMaitenanceService(ConstantUtil.CURRENCY));
				
				List temp= new ArrayList();
				for(int i=0;i<100;i++){
					temp.add(i+"");
				}
				
				session.put("positiveToleranceList", temp);
				session.put("negativeToleranceList", temp);
				authoriseLCPaymentAdviceDto.setRepair("");
				
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
	
	public void validate()
	{                {
        try{
            LetterOfCreditService letterOfCreditService =  (LetterOfCreditService)  ApplicationContextProvider.getBean("letterOfCreditService");
            String senderBank = letterOfCreditService.getDept((String)session.get(WebConstants.CONSTANT_USER_NAME));
            if(StringUtils.isBlank(authoriseLCPaymentAdviceDto.getRepair()) && StringUtils.isEmpty(authoriseLCPaymentAdviceDto.getRepair())){
                            if(senderBank!=null &&  authoriseLCPaymentAdviceDto.getAdvisingBank()!=null)
                            {
                                            if(senderBank.trim().equalsIgnoreCase(authoriseLCPaymentAdviceDto.getAdvisingBank().trim()))
                                                                            {
                                                                                            addFieldError("advisingBank", "Receiver Bank IFSC Should Not Be Sender Bank IFSC");
                                                                            }
                            }
                            }else
                            {
                                            if(StringUtils.isNotBlank(authoriseLCPaymentAdviceDto.getIssuingBankCode())&&StringUtils.isNotEmpty(authoriseLCPaymentAdviceDto.getIssuingBankCode()) &&  authoriseLCPaymentAdviceDto.getAdvisingBank()!=null)
                                            {
                                                            if(authoriseLCPaymentAdviceDto.getIssuingBankCode().trim().equalsIgnoreCase(authoriseLCPaymentAdviceDto.getAdvisingBank().trim()))
                                                                                            {
                                                                                                            addFieldError("advisingBank", "Receiver Bank IFSC Should Not Be Sender Bank IFSC");
                                                                                            }
                                            }              
                            }              
            
            if(letterOfCreditService.isLcNumberExist(authoriseLCPaymentAdviceDto.getLcNumber())==false)
            {
            }else{
                            if(authoriseLCPaymentAdviceDto.getLcNumber().startsWith("/")){
                                            addFieldError("lcNumber", "Documentary Credit Number(20) must not start with /");
                            }else if(authoriseLCPaymentAdviceDto.getLcNumber().endsWith("/")){
                                            addFieldError("lcNumber", "Documentary Credit Number(20) must not End with /");;
                            }else if(authoriseLCPaymentAdviceDto.getLcNumber().contains("//")){
                                            addFieldError("lcNumber", "Documentary Credit Number(20) must not contain two consecutive slashes '//'");
                            }
            }
                            
                            if(!StringUtils.isBlank(authoriseLCPaymentAdviceDto.getRepair()) && !StringUtils.isEmpty(authoriseLCPaymentAdviceDto.getRepair())){
                                            PaymentMessageService paymentMessageService =(PaymentMessageService)ApplicationContextProvider.getBean("paymentMessageService");
                                            if(!paymentMessageService.checkInRptStatusIs2(authoriseLCPaymentAdviceDto.getMsgRef()))
                                            {
                                                            if(!(getSaveAction().equals("Approve") || getSaveAction().equals("Reject")))
                                                                            addFieldError("LcNumber", "Message Is Not In Valid State");
                                            }
                            
                            }
                            
            
            if(StringUtils.isNotBlank(authoriseLCPaymentAdviceDto.getNegotiatingBankCode()) && StringUtils.isNotEmpty(authoriseLCPaymentAdviceDto.getNegotiatingBankCode()) && authoriseLCPaymentAdviceDto.getNegotiatingBankCode()!=null){
                            if(letterOfCreditService.checkIFSC(authoriseLCPaymentAdviceDto.getNegotiatingBankCode())==false){
                                            addFieldError("negotiatingBankCode", "Negotiating Bank IFSC(58A) Is Not Available In System");
                            }
            }
            
            if(authoriseLCPaymentAdviceDto.getAvailableWithIdentifier().equalsIgnoreCase("A"))
            {
                  if(StringUtils.isBlank(authoriseLCPaymentAdviceDto.getAuthorisedBankCode()) && StringUtils.isEmpty(authoriseLCPaymentAdviceDto.getAuthorisedBankCode()))
                  {
                        addFieldError("authorisedBankCode","Authorised Bank Code(41A) is Required ");
                  }
                  if(StringUtils.isBlank(authoriseLCPaymentAdviceDto.getAuthorisationMode()) && StringUtils.isEmpty(authoriseLCPaymentAdviceDto.getAuthorisationMode()))
                  {
                        addFieldError("authorisationMode","Authorised Mode(41a) is Required ");
                  }
                  
            }
            if(authoriseLCPaymentAdviceDto.getAvailableWithIdentifier().equalsIgnoreCase("D"))
            {
                  if(StringUtils.isBlank(authoriseLCPaymentAdviceDto.getAuthorisationMode()) && StringUtils.isEmpty(authoriseLCPaymentAdviceDto.getAuthorisationMode()))
                  {
                        addFieldError("authorisationMode","Authorised Mode(41a) is Required ");
                  }
                  if(StringUtils.isBlank(authoriseLCPaymentAdviceDto.getAuthorisedAndAddress()) && StringUtils.isEmpty(authoriseLCPaymentAdviceDto.getAuthorisedAndAddress()))
                  {
                        addFieldError("authorisedAndAddress","Authorised Bank Name and Address(41D) is Required ");
                  }
                  
                  
            }

           
checkLcDraftsAtAndLCDrwbnkRule();
negotiating58aOr59Networkvalidation();
validateTolerance();
}
catch (Exception e) {
            e.printStackTrace();
            addActionError("Unable To process");
}

if(StringUtils.isBlank(authoriseLCPaymentAdviceDto.getAdvisingBank()) && StringUtils.isEmpty(authoriseLCPaymentAdviceDto.getAdvisingBank()))
{
		addFieldError("advisingBank", "Receiver Bank IFSC is required");
}

	}                       



}
public void validateTolerance()
{
String tolerance = null;
String mxdCrdAmt = null;
tolerance = authoriseLCPaymentAdviceDto.getPositiveTolerance()+authoriseLCPaymentAdviceDto.getNegativeTolerance();
mxdCrdAmt  = authoriseLCPaymentAdviceDto.getMaximumCreditAmount();
if(StringUtils.isNotBlank(tolerance) && StringUtils.isNotEmpty(tolerance) && tolerance!=null)
{
            if(StringUtils.isNotBlank(mxdCrdAmt) && StringUtils.isNotEmpty(mxdCrdAmt) && mxdCrdAmt!=null)
            {
                            addFieldError("maximumCreditAmount", "Either field 39A or 39B, but not both, may be present");
            }
}
}
private void negotiating58aOr59Networkvalidation()
{
String negotiatingBank58a = null;
String benificary59 = null;
negotiatingBank58a  = authoriseLCPaymentAdviceDto.getNegotiatingBankPartyIdentifier()+authoriseLCPaymentAdviceDto.getNegotiatingBankNameAndAddress()+authoriseLCPaymentAdviceDto.getNegotiatingBankCode();
benificary59 = authoriseLCPaymentAdviceDto.getBeneficiaryNameAddress();
if(StringUtils.isNotBlank(negotiatingBank58a) && StringUtils.isNotEmpty(negotiatingBank58a) && negotiatingBank58a!=null)
{
            if(StringUtils.isNotBlank(benificary59) && StringUtils.isNotEmpty(benificary59) && benificary59!=null)
            {
                            addFieldError("beneficiaryAccount", "Either field 58a or 59, but not both, may be present");
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
if (StringUtils.isNotBlank(authoriseLCPaymentAdviceDto.getDraftsAt())) 
{
            is42Cprsnt = true;
}
if(StringUtils.isNotBlank(authoriseLCPaymentAdviceDto.getDraweeBankpartyidentifier()+ authoriseLCPaymentAdviceDto.getDraweeBankCode()+ authoriseLCPaymentAdviceDto.getDraweeBankNameAddress()))
{
            is42aprsnt = true;
}
if(StringUtils.isNotBlank(authoriseLCPaymentAdviceDto.getMixedPaymentDetails()))
{
            is42Mprsnt = true;
}
if(StringUtils.isNotBlank(authoriseLCPaymentAdviceDto.getDeferredPaymentDetails()))
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
if(StringUtils.isNotBlank(authoriseLCPaymentAdviceDto.getDraftsAt()) || StringUtils.isNotBlank(authoriseLCPaymentAdviceDto.getDraweeBankpartyidentifier()+authoriseLCPaymentAdviceDto.getDraweeBankCode()+ authoriseLCPaymentAdviceDto.getDraweeBankNameAddress()))
{
            if(is42Cprsnt ==false || is42aprsnt ==false)
            {
                            addFieldError("draftsAt", "When used, fields 42C and 42a must both be present");
                            logger.info("checkLcDraftsAtAndLCDrwbnkRule Validation Failed");
            }
}

//Case 3
if(StringUtils.isNotBlank(authoriseLCPaymentAdviceDto.getMixedPaymentDetails())||StringUtils.isNotBlank(authoriseLCPaymentAdviceDto.getDeferredPaymentDetails()))
{
            if((is42Cprsnt ==true && is42aprsnt ==true && is42Mprsnt==true)||(is42Cprsnt ==true && is42aprsnt ==true && is42Pprsnt==true) || (is42Mprsnt ==true && is42Pprsnt ==true))
            {
                            addFieldError("DraftsAt", "Either fields 42C and 42a together, or field 42M alone, or field 42P alone may be present. No other combination of these fields is allowed");
                            //addFieldError("DraftsAt", "Drawee Bank details And MixedPaymentDetails Should Not Be Present");
                            logger.info("checkLcDraftsAtAndLCDrwbnkRule Validation Failed");
            }
}


logger.info("checkLcDraftsAtAndLCDrwbnkRule END");
}

	
public String saveAuthoriseTemplate() {
		
		try {			
			String tempRefKey="";
			PendingAuthorizationService pendingAuthorizationService = (PendingAuthorizationService)ApplicationContextProvider.getBean("pendingAuthorizationService");
			String userId = (String)session.get(WebConstants.CONSTANT_USER_NAME);
			
			tempRefKey = pendingAuthorizationService.getTempRef(serializeObject(),"740","XXX",tempName,"Authorisation to Reimburse(MT-740)",userId);
			pendingAuthorizationService.delimitedTempStringValue(tempRefKey, 1+"", "lcCurrency"+"~"+authoriseLCPaymentAdviceDto.getLcCurrency());

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
public String viewTemplateAuthorisePayment()
{
	try{
	displayAuthoriseLcPaymentAdvice();
	String msgRef = (String) session.get("messageRef");
	LetterOfCreditService letterOfCreditService =  (LetterOfCreditService)  ApplicationContextProvider.getBean("letterOfCreditService");
	LCCanonicalDto canonicalDto = letterOfCreditService.getPreAdviceRepair(msgRef);
	if(canonicalDto.getLcNumber()!=null && StringUtils.isNotBlank(canonicalDto.getLcNumber()) && StringUtils.isNotEmpty(canonicalDto.getLcNumber()))
		{
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
 
		@SkipValidation
		public String displayPrintPreviewAuthoriseLCPaymentAdvice()
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
					LCCanonicalDto temp= getSerializedObject(tempScreenString);
					 authoriseLCPaymentAdviceDto.setLcNumber(temp.getLcNumber());
					 authoriseLCPaymentAdviceDto.setAcountID(temp.getAcountID());
					 authoriseLCPaymentAdviceDto.setApplicableRule(temp.getApplicableRule());
					 authoriseLCPaymentAdviceDto.setLcExpiryDate(temp.getLcExpiryDate());
					 authoriseLCPaymentAdviceDto.setLcExpirePlace(temp.getLcExpirePlace());
					 authoriseLCPaymentAdviceDto.setNegotiatingBankPartyIdentifier(temp.getNegotiatingBankPartyIdentifier());
					 authoriseLCPaymentAdviceDto.setNegotiatingBankAccount(temp.getNegotiatingBankAccount());
					 authoriseLCPaymentAdviceDto.setNegotiatingBankCode(temp.getNegotiatingBankCode());
					 authoriseLCPaymentAdviceDto.setNegotiatingBankNameAndAddress(temp.getNegotiatingBankNameAndAddress());
					 //authoriseLCPaymentAdviceDto.setBeneficiaryAccount(temp.getBeneficiaryAccount());
					 authoriseLCPaymentAdviceDto.setBeneficiaryNameAddress(temp.getBeneficiaryNameAddress());
					 authoriseLCPaymentAdviceDto.setLcCurrency(temp.getLcCurrency());
					 authoriseLCPaymentAdviceDto.setCreditAmount(temp.getCreditAmount());
					 authoriseLCPaymentAdviceDto.setPositiveTolerance(temp.getPositiveTolerance());
					 authoriseLCPaymentAdviceDto.setNegativeTolerance(temp.getNegativeTolerance());
					 authoriseLCPaymentAdviceDto.setMaximumCreditAmount(temp.getMaximumCreditAmount());
					 authoriseLCPaymentAdviceDto.setAdditionalAmountsCovered(temp.getAdditionalAmountsCovered());
					 authoriseLCPaymentAdviceDto.setAvailableWithIdentifier(temp.getAvailableWithIdentifier());
					 authoriseLCPaymentAdviceDto.setAuthorisedBankCode(temp.getAuthorisedBankCode());
					 authoriseLCPaymentAdviceDto.setAuthorisationMode(temp.getAuthorisationMode());
					 authoriseLCPaymentAdviceDto.setAuthorisedAndAddress(temp.getAuthorisedAndAddress());
					 authoriseLCPaymentAdviceDto.setDraftsAt(temp.getDraftsAt());
					 authoriseLCPaymentAdviceDto.setDraweeBankpartyidentifier(temp.getDraweeBankpartyidentifier());
					 authoriseLCPaymentAdviceDto.setDraweeBankAccount(temp.getDraweeBankAccount());
					 authoriseLCPaymentAdviceDto.setDraweeBankCode(temp.getDraweeBankCode());
					 authoriseLCPaymentAdviceDto.setDraweeBankNameAddress(temp.getDraweeBankNameAddress());
					 authoriseLCPaymentAdviceDto.setMixedPaymentDetails(temp.getMixedPaymentDetails());
					 authoriseLCPaymentAdviceDto.setDeferredPaymentDetails(temp.getDeferredPaymentDetails());
					 authoriseLCPaymentAdviceDto.setReimbursingBanksCharges(temp.getReimbursingBanksCharges());
					 authoriseLCPaymentAdviceDto.setOtherCharges(temp.getOtherCharges());
					 authoriseLCPaymentAdviceDto.setSendertoReceiverInformation(temp.getSendertoReceiverInformation());
					 authoriseLCPaymentAdviceDto.setMsgRef(temp.getMsgRef());
				     authoriseLCPaymentAdviceDto.setSeqNo(temp.getSeqNo());
				     authoriseLCPaymentAdviceDto.setMsgHost(temp.getMsgHost());
				     authoriseLCPaymentAdviceDto.setRepair(temp.getRepair());
				     authoriseLCPaymentAdviceDto.setIssuingBankCode(temp.getIssuingBankCode());
				     authoriseLCPaymentAdviceDto.setAdvisingBank(temp.getAdvisingBank()); 
				        setPrintPreviewTxnRef(getHiddenTxnRef());
				}
				else if(msgRef!=null && !msgRef.isEmpty())
				{
					viewAuthorisePayment();
					return "success";
				}
				else
				{
					 authoriseLCPaymentAdviceDto.setLcNumber(authoriseLCPaymentAdviceDto.getLcNumber());
					 authoriseLCPaymentAdviceDto.setAcountID(authoriseLCPaymentAdviceDto.getAcountID());
					 authoriseLCPaymentAdviceDto.setApplicableRule(authoriseLCPaymentAdviceDto.getApplicableRule());
					 authoriseLCPaymentAdviceDto.setLcExpiryDate(authoriseLCPaymentAdviceDto.getLcExpiryDate());
					 authoriseLCPaymentAdviceDto.setLcExpirePlace(authoriseLCPaymentAdviceDto.getLcExpirePlace());
					 authoriseLCPaymentAdviceDto.setNegotiatingBankPartyIdentifier(authoriseLCPaymentAdviceDto.getNegotiatingBankPartyIdentifier());
					 authoriseLCPaymentAdviceDto.setNegotiatingBankAccount(authoriseLCPaymentAdviceDto.getNegotiatingBankAccount());
					 authoriseLCPaymentAdviceDto.setNegotiatingBankCode(authoriseLCPaymentAdviceDto.getNegotiatingBankCode());
					 authoriseLCPaymentAdviceDto.setNegotiatingBankNameAndAddress(authoriseLCPaymentAdviceDto.getNegotiatingBankNameAndAddress());
					 //authoriseLCPaymentAdviceDto.setBeneficiaryAccount(authoriseLCPaymentAdviceDto.getBeneficiaryAccount());
					 authoriseLCPaymentAdviceDto.setBeneficiaryNameAddress(authoriseLCPaymentAdviceDto.getBeneficiaryNameAddress());
					 authoriseLCPaymentAdviceDto.setLcCurrency(authoriseLCPaymentAdviceDto.getLcCurrency());
					 authoriseLCPaymentAdviceDto.setCreditAmount(authoriseLCPaymentAdviceDto.getCreditAmount());
					 authoriseLCPaymentAdviceDto.setPositiveTolerance(authoriseLCPaymentAdviceDto.getPositiveTolerance());
					 authoriseLCPaymentAdviceDto.setNegativeTolerance(authoriseLCPaymentAdviceDto.getNegativeTolerance());
					 authoriseLCPaymentAdviceDto.setMaximumCreditAmount(authoriseLCPaymentAdviceDto.getMaximumCreditAmount());
					 authoriseLCPaymentAdviceDto.setAdditionalAmountsCovered(authoriseLCPaymentAdviceDto.getAdditionalAmountsCovered());
					 authoriseLCPaymentAdviceDto.setAvailableWithIdentifier(authoriseLCPaymentAdviceDto.getAvailableWithIdentifier());
					 authoriseLCPaymentAdviceDto.setAuthorisedBankCode(authoriseLCPaymentAdviceDto.getAuthorisedBankCode());
					 authoriseLCPaymentAdviceDto.setAuthorisationMode(authoriseLCPaymentAdviceDto.getAuthorisationMode());
					 authoriseLCPaymentAdviceDto.setAuthorisedAndAddress(authoriseLCPaymentAdviceDto.getAuthorisedAndAddress());
					 authoriseLCPaymentAdviceDto.setDraftsAt(authoriseLCPaymentAdviceDto.getDraftsAt());
					 authoriseLCPaymentAdviceDto.setDraweeBankpartyidentifier(authoriseLCPaymentAdviceDto.getDraweeBankpartyidentifier());
					 authoriseLCPaymentAdviceDto.setDraweeBankAccount(authoriseLCPaymentAdviceDto.getDraweeBankAccount());
					 authoriseLCPaymentAdviceDto.setDraweeBankCode(authoriseLCPaymentAdviceDto.getDraweeBankCode());
					 authoriseLCPaymentAdviceDto.setDraweeBankNameAddress(authoriseLCPaymentAdviceDto.getDraweeBankNameAddress());
					 authoriseLCPaymentAdviceDto.setMixedPaymentDetails(authoriseLCPaymentAdviceDto.getMixedPaymentDetails());
					 authoriseLCPaymentAdviceDto.setDeferredPaymentDetails(authoriseLCPaymentAdviceDto.getDeferredPaymentDetails());
					 authoriseLCPaymentAdviceDto.setReimbursingBanksCharges(authoriseLCPaymentAdviceDto.getReimbursingBanksCharges());
					 authoriseLCPaymentAdviceDto.setOtherCharges(authoriseLCPaymentAdviceDto.getOtherCharges());
					 authoriseLCPaymentAdviceDto.setSendertoReceiverInformation(authoriseLCPaymentAdviceDto.getSendertoReceiverInformation());
					 authoriseLCPaymentAdviceDto.setMsgRef(authoriseLCPaymentAdviceDto.getMsgRef());
				     authoriseLCPaymentAdviceDto.setSeqNo(authoriseLCPaymentAdviceDto.getSeqNo());
				     authoriseLCPaymentAdviceDto.setMsgHost(authoriseLCPaymentAdviceDto.getMsgHost());
				     authoriseLCPaymentAdviceDto.setRepair(authoriseLCPaymentAdviceDto.getRepair());
				     authoriseLCPaymentAdviceDto.setIssuingBankCode(authoriseLCPaymentAdviceDto.getIssuingBankCode());
				     authoriseLCPaymentAdviceDto.setAdvisingBank(authoriseLCPaymentAdviceDto.getAdvisingBank()); 
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
		public String reset() {
			try{
				authoriseLCPaymentAdviceDto.setLcNumber("");
				 authoriseLCPaymentAdviceDto.setAcountID("");
				 authoriseLCPaymentAdviceDto.setApplicableRule("");
				 authoriseLCPaymentAdviceDto.setLcExpiryDate(null);
				 authoriseLCPaymentAdviceDto.setLcExpirePlace("");
				 authoriseLCPaymentAdviceDto.setNegotiatingBankPartyIdentifier("");
				 authoriseLCPaymentAdviceDto.setNegotiatingBankAccount("");
				 authoriseLCPaymentAdviceDto.setNegotiatingBankCode("");
				 authoriseLCPaymentAdviceDto.setNegotiatingBankNameAndAddress("");
				// authoriseLCPaymentAdviceDto.setBeneficiaryAccount("");
				 authoriseLCPaymentAdviceDto.setBeneficiaryNameAddress("");
				 authoriseLCPaymentAdviceDto.setLcCurrency("");
				 authoriseLCPaymentAdviceDto.setCreditAmount(null);
				 authoriseLCPaymentAdviceDto.setPositiveTolerance("");
				 authoriseLCPaymentAdviceDto.setNegativeTolerance("");
				 authoriseLCPaymentAdviceDto.setMaximumCreditAmount("");
				 authoriseLCPaymentAdviceDto.setAdditionalAmountsCovered("");
				 authoriseLCPaymentAdviceDto.setAvailableWithIdentifier("");
				 authoriseLCPaymentAdviceDto.setAuthorisedBankCode("");
				 authoriseLCPaymentAdviceDto.setAuthorisationMode("");
				 authoriseLCPaymentAdviceDto.setAuthorisedAndAddress("");
				 authoriseLCPaymentAdviceDto.setDraftsAt("");
				 authoriseLCPaymentAdviceDto.setDraweeBankpartyidentifier("");
				 authoriseLCPaymentAdviceDto.setDraweeBankAccount("");
				 authoriseLCPaymentAdviceDto.setDraweeBankCode("");
				 authoriseLCPaymentAdviceDto.setDraweeBankNameAddress("");
				 authoriseLCPaymentAdviceDto.setMixedPaymentDetails("");
				 authoriseLCPaymentAdviceDto.setDeferredPaymentDetails("");
				 authoriseLCPaymentAdviceDto.setReimbursingBanksCharges("");
				 authoriseLCPaymentAdviceDto.setOtherCharges("");
				 authoriseLCPaymentAdviceDto.setSendertoReceiverInformation("");
				 authoriseLCPaymentAdviceDto.setMsgRef("");
			     authoriseLCPaymentAdviceDto.setSeqNo("");
			     authoriseLCPaymentAdviceDto.setMsgHost("");
			     authoriseLCPaymentAdviceDto.setRepair("");
			     authoriseLCPaymentAdviceDto.setIssuingBankCode("");
			     authoriseLCPaymentAdviceDto.setAdvisingBank(""); 
			return "success";
			}catch (Exception e) {
				logger.error(e,e);
				return "input";
			}

		}
		
		@SkipValidation
		public String exportToExcelAuthoriseLCPaymentAdvice() throws Exception
		{		
		try{
			displayPrintPreviewAuthoriseLCPaymentAdvice();
			String reportFile = "Authorization_To_Reimburse (MT-740)";
			SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyyHHmmss");
			String currentDateTime = sdf.format(new Date());
			String reportFileName = reportFile+"_"+currentDateTime+"".concat(".xls");
			setReportFile(reportFileName);
			System.out.println("reportFileName is "+reportFileName);
			ExportToExcelUtil exportToExcelUtil = new ExportToExcelUtil();
			HSSFWorkbook myWorkBook = exportToExcelUtil.generateExportToExcel(authoriseLCPaymentAdviceDto, null, null, reportFile, 740, "XXX");
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
		public String generatePDFAuthoriseLCPaymentAdvice()
		{
			displayPrintPreviewAuthoriseLCPaymentAdvice();
			String reportName ="Authorization To Reimburse (MT-740)";
			Map<String,String> fieldNamesMap = new HashMap<String,String>();
			
			try{
				
				fieldNamesMap.put("label.MB.IssuingBranchIFSC", getText("label.MB.IssuingBranchIFSC"));
				fieldNamesMap.put("label.MB.Benefifsc", getText("label.MB.Benefifsc"));
				
				fieldNamesMap.put("label.740.pplc_Number", getText("label.740.pplc_Number"));
				fieldNamesMap.put("label.740.ppAccountID", getText("label.740.ppAccountID"));
				fieldNamesMap.put("label.740.ppapplicableRules", getText("label.740.ppapplicableRules"));
				fieldNamesMap.put("label.740.ppExpiryDate", getText("label.740.ppExpiryDate"));
				fieldNamesMap.put("label.740.ppexpiryPlace", getText("label.740.ppexpiryPlace"));
				fieldNamesMap.put("label.740.ppNegotiatingBankPartyIdentifier", getText("label.740.ppNegotiatingBankPartyIdentifier"));
				fieldNamesMap.put("label.740.ppNegotiatingBankNameAndAddress", getText("label.740.ppNegotiatingBankNameAndAddress"));
				fieldNamesMap.put("label.740.ppNegotiatingBankCode", getText("label.740.ppNegotiatingBankCode"));
				fieldNamesMap.put("label.740.ppbeneficiaryNameAddress", getText("label.740.ppbeneficiaryNameAddress"));
				fieldNamesMap.put("label.740.ppcreditCurrency", getText("label.740.ppcreditCurrency"));
				fieldNamesMap.put("label.740.ppCreditAmount", getText("label.740.ppCreditAmount"));
				fieldNamesMap.put("label.740.pppositiveTolerance", getText("label.740.pppositiveTolerance"));
				fieldNamesMap.put("label.740.ppnegativeTolerance", getText("label.740.ppnegativeTolerance"));
				fieldNamesMap.put("label.740.ppmaximumCreditAmount", getText("label.740.ppmaximumCreditAmount"));
				fieldNamesMap.put("label.740.ppAdditionalAmountsCovered", getText("label.740.ppAdditionalAmountsCovered"));
				fieldNamesMap.put("label.740.ppAvailableWithIdentifier", getText("label.740.ppAvailableWithIdentifier"));
				fieldNamesMap.put("label.740.ppauthorisedBankCode", getText("label.740.ppauthorisedBankCode"));
				fieldNamesMap.put("label.740.ppauthorisationMode", getText("label.740.ppauthorisationMode"));
				fieldNamesMap.put("label.740.ppauthorisedAndAddress", getText("label.740.ppauthorisedAndAddress"));
				fieldNamesMap.put("label.740.ppDraftsAt", getText("label.740.ppDraftsAt"));
				fieldNamesMap.put("label.740.ppDraweeBankID", getText("label.740.ppDraweeBankID"));
				fieldNamesMap.put("label.740.ppDraweeBankCode", getText("label.740.ppDraweeBankCode"));
				fieldNamesMap.put("label.740.ppDraweeBankNameAddress", getText("label.740.ppDraweeBankNameAddress"));
				fieldNamesMap.put("label.740.ppMixedPaymentDetails", getText("label.740.ppMixedPaymentDetails"));
				fieldNamesMap.put("label.740.ppDeferredPaymentDetails", getText("label.740.ppDeferredPaymentDetails"));
				fieldNamesMap.put("label.740.ppReimbursingBanksCharges", getText("label.740.ppReimbursingBanksCharges"));
				fieldNamesMap.put("label.740.ppOtherCharges", getText("label.740.ppOtherCharges"));
				fieldNamesMap.put("label.740.ppSendertoReceiverInformation", getText("label.740.ppSendertoReceiverInformation"));
				
				PaymentPDFGeneratationUtil paymentPDFGeneratationUtil = new PaymentPDFGeneratationUtil();
				paymentPDFGeneratationUtil.setServletRequest(servletRequest);
				paymentPDFGeneratationUtil.setReportName(reportName);
				
				paymentPDFGeneratationUtil.generatePaymentPDFReport(authoriseLCPaymentAdviceDto, null, null,fieldNamesMap, 740, "XXX");
			}
			catch (Exception exception) {
				AuditServiceUtil.logException(exception,logger);
			}
			addActionError("Unable to Generate PDF file! Please try again");
			return "input";
		}
}
