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
import com.logica.ngph.service.AuthorizationSchemaMaitenanceService;
import com.logica.ngph.service.CreateBankGuaranteeService;
import com.logica.ngph.service.PaymentMessageService;
import com.logica.ngph.service.PendingAuthorizationService;
import com.logica.ngph.service.UserMaintenanceService;
import com.logica.ngph.web.utils.ApplicationContextProvider;
import com.logica.ngph.web.utils.EventLogging;
import com.logica.ngph.web.utils.ExportToExcelUtil;
import com.logica.ngph.web.utils.PaymentPDFGeneratationUtil;
import com.logica.ngph.web.utils.WebConstants;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class AdviceofReductionAction extends ActionSupport implements
		ModelDriven<CreateBankGuaranteeDto>, SessionAware, ServletRequestAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(AdviceofReductionAction.class);
	private CreateBankGuaranteeDto createBankGuaranteeDto = new CreateBankGuaranteeDto();
	private Map<String, Object> session;
	private String hiddenTxnRef;
	private String txnRef;
	private String saveAction;
	private String checkForSubmit;
	private boolean validUserToApprove;
	private String IFSCFLAG;
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
	 * @return the currencyList
	 */
	public List<String> getCurrCodeDropDown() {
		return currCodeDropDown;
	}

	/**
	 * @param currencyList the currencyList to set
	 */
	public void setCurrCodeDropDown(List<String> currCodeDropDown) {
		this.currCodeDropDown = currCodeDropDown;
		this.session.put("currCodeDropDown", currCodeDropDown);
	}

	@SkipValidation
	public String displayadviceofreduction()
	{
		UserMaintenanceService userMaintenanceService = (UserMaintenanceService)ApplicationContextProvider.getBean("userMaintenanceService");
		try
		{
			setCurrCodeDropDown(userMaintenanceService.getCurrencyCodes());
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
		
	public String adviceReducionApproval() {
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
				 txnKey = pendingAuthorizationService.getTranRef(serializeObject(),"Advice of Reduction(MT-769)",userId);
				 	pendingAuthorizationService.delimitedStringValue(txnKey, 1+"", "bgCurrency"+"~"+createBankGuaranteeDto.getBgCurrency());
					pendingAuthorizationService.delimitedStringValue(txnKey, 2+"", "reducedCurrency"+"~"+createBankGuaranteeDto.getReducedCurrency());
					pendingAuthorizationService.delimitedStringValue(txnKey, 3+"", "outstandingCurrency"+"~"+createBankGuaranteeDto.getOutstandingCurrency());
					eventLogging.doEventLogging(userId, "Advice of Reduction", ConstantUtil.EVENTID_ADVICE_OF_REDUCTION+ConstantUtil.EVENTID_SUBMIT, ConstantUtil.EVENTLOGGINGCOMMENTSUBMIT,createBankGuaranteeDto.getBgNumber(),createBankGuaranteeDto.getMsgRef());
			}
		   
		    if(StringUtils.isBlank(createBankGuaranteeDto.getRepair()) && StringUtils.isEmpty(createBankGuaranteeDto.getRepair())){
				
			}else{
				PaymentMessageService paymentMessageService =(PaymentMessageService)ApplicationContextProvider.getBean("paymentMessageService");
				paymentMessageService.changeMsgStatus2to99(createBankGuaranteeDto.getMsgRef());
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
	
	@SkipValidation
	public String getReductionAuthorization()
	{
		String tempScreenString =null;
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
		CreateBankGuaranteeDto temp= getSerializedObject(tempScreenString);
		
		createBankGuaranteeDto.setBgNumber(temp.getBgNumber());
		createBankGuaranteeDto.setRelatedReference(temp.getRelatedReference());
		createBankGuaranteeDto.setBgAccountIdentification(temp.getBgAccountIdentification());
		createBankGuaranteeDto.setReductionDate(temp.getReductionDate());
		createBankGuaranteeDto.setChargeAmtIdentifier(temp.getChargeAmtIdentifier());
		createBankGuaranteeDto.setChargeAmt(temp.getChargeAmt());
		createBankGuaranteeDto.setBgCurrency(temp.getBgCurrency());
		createBankGuaranteeDto.setBgChargeAmount(temp.getBgChargeAmount());
		createBankGuaranteeDto.setChargeDate(temp.getChargeDate());
		createBankGuaranteeDto.setReducedCurrency(temp.getReducedCurrency());
		createBankGuaranteeDto.setReducedAmt(temp.getReducedAmt());
		createBankGuaranteeDto.setOutstandingCurrency(temp.getOutstandingCurrency());
		createBankGuaranteeDto.setOutstandingAmt(temp.getOutstandingAmt());
		createBankGuaranteeDto.setAmountSpecification(temp.getAmountSpecification());
		createBankGuaranteeDto.setAdvisingBank(temp.getAdvisingBank());
		createBankGuaranteeDto.setIssuingBankCode(temp.getIssuingBankCode());
		createBankGuaranteeDto.setAccountWithBank(temp.getAccountWithBank());
		createBankGuaranteeDto.setAccountwithBankCode(temp.getAccountwithBankCode());
		createBankGuaranteeDto.setAdviseThroughBankAcc(temp.getAdviseThroughBankAcc());
		createBankGuaranteeDto.setAuthorisedBankCode(temp.getAuthorisedBankCode());
		createBankGuaranteeDto.setAccountWithPartyLocation(temp.getAccountWithPartyLocation());
		createBankGuaranteeDto.setBgAccountWithNameandAddress(temp.getBgAccountWithNameandAddress());
		createBankGuaranteeDto.setChargesDetails(temp.getChargesDetails());
		createBankGuaranteeDto.setSenderToReceiverInformation(temp.getSenderToReceiverInformation());
		createBankGuaranteeDto.setIssuingBankCode(temp.getIssuingBankCode());
		createBankGuaranteeDto.setMsgRef(temp.getMsgRef());
		createBankGuaranteeDto.setRepair(temp.getRepair());
		createBankGuaranteeDto.setSeqNo(temp.getSeqNo());
		createBankGuaranteeDto.setMsgHost(temp.getMsgHost());
		if(getTxnRef()!=null)
		{
			List multiCurrCodeList = pendingAuthorizationService.getMulScreenData(getTxnRef());
			
			for(int i=0;i<multiCurrCodeList.size();i++)
			{
				Clob tempCurrCodeList = (Clob)multiCurrCodeList.get(i);
				String data[]= tempCurrCodeList.getSubString(1, (int) tempCurrCodeList.length()).toString().split("~");
				if(data[0].toString().trim().equals("bgCurrency")  && !data[0].toString().trim().equals(""))
				{
					currCodeDropDown.add(data[1].toString());
				}
				else if(data[0].toString().trim().equals("reducedCurrency")  && !data[0].toString().trim().equals(""))
				{
					currCodeDropDown.add(data[1].toString());
				}
				else if(data[0].toString().trim().equals("outstandingCurrency")  && !data[0].toString().trim().equals(""))
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
				if(data[0].toString().trim().equals("bgCurrency")  && !data[0].toString().trim().equals(""))
				{
					currCodeDropDown.add(data[1].toString());
				}
				else if(data[0].toString().trim().equals("reducedCurrency")  && !data[0].toString().trim().equals(""))
				{
					currCodeDropDown.add(data[1].toString());
				}
				else if(data[0].toString().trim().equals("outstandingCurrency")  && !data[0].toString().trim().equals(""))
				{
					currCodeDropDown.add(data[1].toString());
				}
			}
		}
		
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
	public String saveReductionData()
	{
		try
		{
			System.out.println("Inside saveReductionData method");
			String userId = (String)session.get(WebConstants.CONSTANT_USER_NAME);
			PendingAuthorizationService pendingAuthorizationService = (PendingAuthorizationService)ApplicationContextProvider.getBean("pendingAuthorizationService");
			CreateBankGuaranteeService createBankGuaranteeService =  (CreateBankGuaranteeService)  ApplicationContextProvider.getBean("createBankGuaranteeService");
			EventLogging eventLogging = (EventLogging)ApplicationContextProvider.getBean("eventLogging");
			PaymentMessageService paymentMessageService =(PaymentMessageService)ApplicationContextProvider.getBean("paymentMessageService");
			if(getSaveAction().equalsIgnoreCase("Approve"))
			{			
				System.out.println("Inside Apprive block");
				createBankGuaranteeService.createReduction(createBankGuaranteeDto, userId);
				pendingAuthorizationService.changeStatus(getSaveAction(), getHiddenTxnRef());
				if(StringUtils.isBlank(createBankGuaranteeDto.getRepair()) && StringUtils.isEmpty(createBankGuaranteeDto.getRepair())){
					pendingAuthorizationService.changeStatus(getSaveAction(), getHiddenTxnRef());	
					 eventLogging.doEventLogging(userId, "Advice of Reduction", ConstantUtil.EVENTID_ADVICE_OF_REDUCTION+ConstantUtil.EVENTID_APPROVE, ConstantUtil.EVENTLOGGINGCOMMENTAPPROVAL,createBankGuaranteeDto.getBgNumber(),createBankGuaranteeDto.getMsgRef());
				}
				else
				{
					paymentMessageService.changeMsgStatus2to99(createBankGuaranteeDto.getMsgRef());
					eventLogging.doEventLogging(userId, "Advice of Reduction", ConstantUtil.EVENTID_ADVICE_OF_REDUCTION+ConstantUtil.EVENTID_REJECT, ConstantUtil.EVENTLOGGINGCOMMENTREJECT,createBankGuaranteeDto.getBgNumber(),createBankGuaranteeDto.getMsgRef());
				}
			
			}
			else
			{
				if(StringUtils.isBlank(createBankGuaranteeDto.getRepair()) && StringUtils.isEmpty(createBankGuaranteeDto.getRepair())){
					pendingAuthorizationService.changeStatus(getSaveAction(), getHiddenTxnRef());
				}else{
					paymentMessageService.changeMsgStatus99to2(createBankGuaranteeDto.getMsgRef());
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
	public String resetAdciveofReduction() {
		try{
			createBankGuaranteeDto.setBgNumber("");
			createBankGuaranteeDto.setRelatedReference("");
			createBankGuaranteeDto.setBgAccountIdentification("");
			createBankGuaranteeDto.setReductionDate(null);
			createBankGuaranteeDto.setChargeAmtIdentifier("");
			createBankGuaranteeDto.setChargeAmt("");
			createBankGuaranteeDto.setBgCurrency("");
			createBankGuaranteeDto.setBgChargeAmount(null);
			createBankGuaranteeDto.setChargeDate(null);
			createBankGuaranteeDto.setReducedCurrency("");
			createBankGuaranteeDto.setReducedAmt(null);
			createBankGuaranteeDto.setOutstandingCurrency("");
			createBankGuaranteeDto.setOutstandingAmt(null);
			createBankGuaranteeDto.setAmountSpecification("");
			createBankGuaranteeDto.setAdvisingBank("");
			createBankGuaranteeDto.setAccountWithBank("");
			createBankGuaranteeDto.setAccountwithBankCode("");
			createBankGuaranteeDto.setAdviseThroughBankAcc("");
			createBankGuaranteeDto.setAuthorisedBankCode("");
			createBankGuaranteeDto.setAccountWithPartyLocation("");
			createBankGuaranteeDto.setBgAccountWithNameandAddress("");
			createBankGuaranteeDto.setChargesDetails("");
			createBankGuaranteeDto.setSenderToReceiverInformation("");
			createBankGuaranteeDto.setIssuingBankCode("");
		return "success";
		}catch (Exception e) {
			logger.error(e,e);
			return "input";
		}
	}
	
	private String serializeObject(){
		CreateBankGuaranteeDto createBankGuaranteeDTO = new CreateBankGuaranteeDto();
		try{
			String userId = (String)session.get(WebConstants.CONSTANT_USER_NAME);
			
			createBankGuaranteeDTO = createBankGuaranteeDto;
			String fileName ="serial_"+userId+".ser";
		FileOutputStream fos = new FileOutputStream(fileName);
        OutputStream buffer = new BufferedOutputStream( fos );
        ObjectOutputStream oos = new ObjectOutputStream(buffer);
        oos.writeObject(createBankGuaranteeDTO);
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
	
	private CreateBankGuaranteeDto getSerializedObject(String objectString){
		
		try{
			
			byte[] decoded = Base64.decode(objectString);
            
            FileOutputStream foss = new FileOutputStream("targetUserObject.ser");
            foss.write(decoded);
            foss.close();
            CreateBankGuaranteeDto testDTO = null;
            
            FileInputStream fiss = new FileInputStream("targetUserObject.ser");
            BufferedInputStream bufferee = new BufferedInputStream( fiss );
            ObjectInputStream oiss = new ObjectInputStream(bufferee);
            testDTO = (CreateBankGuaranteeDto)oiss.readObject();
            oiss.close();
            System.out.println("object2: " + testDTO); 
            System.out.print("User(testDTO) :"+testDTO.getBgNumber());

          
            return testDTO;

		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public void validate(){
		
		
	
		if(createBankGuaranteeDto.getBgNumber().startsWith("/")){
			 addFieldError("bgNumber", "Transaction Reference Number(20) Must Not start with /");
		}else if(createBankGuaranteeDto.getBgNumber().endsWith("/")){
			addFieldError("bgNumber", "Transaction Reference Number(20) must not End with /");;
		}else if(createBankGuaranteeDto.getBgNumber().contains("//")){
			addFieldError("bgNumber", "Transaction Reference Number(20) must not contain two consecutive slashes '//'");
		}
		
		if(createBankGuaranteeDto.getRelatedReference().startsWith("/")){
			 addFieldError("relatedReference", "Related Reference Number(21) Must Not start with /");
		}else if(createBankGuaranteeDto.getRelatedReference().endsWith("/")){
			addFieldError("relatedReference", "Related Reference Number(21) must not End with /");;
		}else if(createBankGuaranteeDto.getRelatedReference().contains("//")){
			addFieldError("relatedReference", "Related Reference Number(21) not contain two consecutive slashes '//'");
		}
	
	if(StringUtils.isNotBlank(createBankGuaranteeDto.getReducedCurrency()) && StringUtils.isNotEmpty(createBankGuaranteeDto.getReducedCurrency()) && !String.valueOf(createBankGuaranteeDto.getReducedAmt()).equalsIgnoreCase(null) && !(String.valueOf(createBankGuaranteeDto.getReducedAmt())==null))
	{
		if(StringUtils.isNotBlank(createBankGuaranteeDto.getAmountSpecification()) && StringUtils.isNotEmpty(createBankGuaranteeDto.getAmountSpecification()) )
		{
			addFieldError("reducedCurrency", "Either field 33B or field 39C, but not both, must be present");
		}
	}
	
	
	if(createBankGuaranteeDto.getChargeDate()!=null && createBankGuaranteeDto.getBgChargeAmount()!=null && StringUtils.isNotBlank(createBankGuaranteeDto.getBgCurrency()))
	{
		if(StringUtils.isNotEmpty(createBankGuaranteeDto.getAdviseThroughBankAcc()) || StringUtils.isNotEmpty(createBankGuaranteeDto.getAuthorisedBankCode()) || StringUtils.isNotEmpty(createBankGuaranteeDto.getAccountWithPartyLocation()) || StringUtils.isNotEmpty(createBankGuaranteeDto.getBgAccountWithNameandAddress()))
		{
			addFieldError("chargeDate", "If field 32D is present, field 57a must not be present");
		}
	}
	if(StringUtils.isNotBlank(createBankGuaranteeDto.getBgAccountIdentification()) && StringUtils.isNotEmpty(createBankGuaranteeDto.getBgAccountIdentification()))
	{
		if(StringUtils.isNotBlank(createBankGuaranteeDto.getAuthorisedBankCode()) && StringUtils.isNotEmpty(createBankGuaranteeDto.getAuthorisedBankCode()) || StringUtils.isNotBlank(createBankGuaranteeDto.getAccountWithPartyLocation()) && StringUtils.isNotEmpty(createBankGuaranteeDto.getAccountWithPartyLocation()) || StringUtils.isNotBlank(createBankGuaranteeDto.getBgAccountWithNameandAddress()) && StringUtils.isNotEmpty(createBankGuaranteeDto.getBgAccountWithNameandAddress()))
		{
			
			addFieldError("bgAccountIdentification", "Either field 25 or 57a, but not both, may be present");
		}
	}
	
	if(StringUtils.isNotBlank(createBankGuaranteeDto.getChargesDetails()) && StringUtils.isNotEmpty(createBankGuaranteeDto.getChargesDetails()))
	{
		if(StringUtils.isBlank(createBankGuaranteeDto.getBgCurrency()))
		{
			addFieldError("ChargesDetails", "If field 71B is present, field 32a must also be present");
		}
	}
	
	if(StringUtils.isNotBlank(createBankGuaranteeDto.getReducedCurrency()) && StringUtils.isNotEmpty(createBankGuaranteeDto.getReducedCurrency()))
	{
		if(StringUtils.isNotBlank(createBankGuaranteeDto.getOutstandingCurrency()) && StringUtils.isNotEmpty(createBankGuaranteeDto.getOutstandingCurrency()))
		{
			if(!createBankGuaranteeDto.getReducedCurrency().equalsIgnoreCase(createBankGuaranteeDto.getOutstandingCurrency()))
			{
				addFieldError("reducedCurrency", "The currency code in the amount fields 33B and 34B must be the same");
			}
		}
	}
	
	if(StringUtils.isBlank(createBankGuaranteeDto.getAdvisingBank()) && StringUtils.isEmpty(createBankGuaranteeDto.getAdvisingBank()))
	{
			addFieldError("advisingBank", "Receiver Bank IFSC is required");
	}
}
	public CreateBankGuaranteeDto getModel() {		
		return createBankGuaranteeDto;
	}

	public CreateBankGuaranteeDto getCreateBankGuaranteeDto() {
		return createBankGuaranteeDto;
	}

	public void setCreateBankGuaranteeDto(
			CreateBankGuaranteeDto createBankGuaranteeDto) {
		this.createBankGuaranteeDto = createBankGuaranteeDto;
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

	public Map<String, Object> getSession() {
		return session;
	}
	
	public void setSession(Map<String, Object> session) {
		
		this.session = session;
	}

	public String getIFSCFLAG() {
		return IFSCFLAG;
	}

	public void setIFSCFLAG(String iFSCFLAG) {
		IFSCFLAG = iFSCFLAG;
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
	
	

	public String getReportFile() {
		return reportFile;
	}

	public void setReportFile(String reportFile) {
		this.reportFile = reportFile;
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	@SkipValidation
	public String viewAdviceofReduction()
	{
		try{
		String msgRef = (String) session.get("messageIndex");
		CreateBankGuaranteeService createBankGuaranteeService =  (CreateBankGuaranteeService)ApplicationContextProvider.getBean("createBankGuaranteeService");
		CreateBankGuaranteeDto createBankGuaranteeDto = createBankGuaranteeService.getAdviceReduction(msgRef);
		if(createBankGuaranteeDto.getBgNumber()!=null && StringUtils.isNotBlank(createBankGuaranteeDto.getBgNumber()) && StringUtils.isNotEmpty(createBankGuaranteeDto.getBgNumber())){
			createBankGuaranteeDto.setRepair(ConstantUtil.REPAIR);
			setALLValueTODTO(createBankGuaranteeDto);
			
		}
		else
		{
			addFieldError("paymentMessageType",ConstantUtil.ERRORMESSAGE);
			return "failure";
		}
		return "success";
		}catch(Exception exception)
		{
			exception.printStackTrace();
			addFieldError("paymentMessageType",ConstantUtil.ERRORMESSAGE);
			return "input";
		
		}
	}
	
	
	private void setALLValueTODTO(CreateBankGuaranteeDto obj)
	{
		
		CreateBankGuaranteeDto temp = obj;
		createBankGuaranteeDto.setBgNumber(temp.getBgNumber());
		createBankGuaranteeDto.setRelatedReference(temp.getRelatedReference());
		createBankGuaranteeDto.setBgAccountIdentification(temp.getBgAccountIdentification());
		createBankGuaranteeDto.setReductionDate(temp.getReductionDate());
		createBankGuaranteeDto.setChargeAmtIdentifier(temp.getChargeAmtIdentifier());
		createBankGuaranteeDto.setChargeAmt(temp.getChargeAmt());
		createBankGuaranteeDto.setBgCurrency(temp.getBgCurrency());
		createBankGuaranteeDto.setBgChargeAmount(temp.getBgChargeAmount());
		createBankGuaranteeDto.setChargeDate(temp.getChargeDate());
		createBankGuaranteeDto.setReducedCurrency(temp.getReducedCurrency());
		createBankGuaranteeDto.setReducedAmt(temp.getReducedAmt());
		createBankGuaranteeDto.setOutstandingCurrency(temp.getOutstandingCurrency());
		createBankGuaranteeDto.setOutstandingAmt(temp.getOutstandingAmt());
		createBankGuaranteeDto.setAmountSpecification(temp.getAmountSpecification());
		createBankGuaranteeDto.setAccountWithBank(temp.getAccountWithBank());
		createBankGuaranteeDto.setAccountwithBankCode(temp.getAccountwithBankCode());
		createBankGuaranteeDto.setAdviseThroughBankAcc(temp.getAdviseThroughBankAcc());
		createBankGuaranteeDto.setAuthorisedBankCode(temp.getAuthorisedBankCode());
		createBankGuaranteeDto.setAccountWithPartyLocation(temp.getAccountWithPartyLocation());
		createBankGuaranteeDto.setBgAccountWithNameandAddress(temp.getBgAccountWithNameandAddress());
		createBankGuaranteeDto.setChargesDetails(temp.getChargesDetails());
		createBankGuaranteeDto.setSenderToReceiverInformation(temp.getSenderToReceiverInformation());
		createBankGuaranteeDto.setIssuingBankCode(temp.getIssuingBankCode());
		createBankGuaranteeDto.setMsgRef(temp.getMsgRef());
		createBankGuaranteeDto.setRepair(temp.getRepair());
		createBankGuaranteeDto.setSeqNo(temp.getSeqNo());
		createBankGuaranteeDto.setMsgHost(temp.getMsgHost());
		createBankGuaranteeDto.setIssuingBankCode(temp.getIssuingBankCode());
		createBankGuaranteeDto.setAdvisingBank(temp.getAdvisingBank());
	
        session.put("ScreenData", createBankGuaranteeDto);
	}
	
public String saveTemplate() {
		
		try {			
			String tempRefKey="";
			PendingAuthorizationService pendingAuthorizationService = (PendingAuthorizationService)ApplicationContextProvider.getBean("pendingAuthorizationService");
			String userId = (String)session.get(WebConstants.CONSTANT_USER_NAME);
			
			tempRefKey = pendingAuthorizationService.getTempRef(serializeObject(),"769","XXX",tempName,"Advice of Reduction(MT-769)",userId);
			
			pendingAuthorizationService.delimitedTempStringValue(tempRefKey, 1+"", "bgCurrency"+"~"+createBankGuaranteeDto.getBgCurrency());
			pendingAuthorizationService.delimitedTempStringValue(tempRefKey, 2+"", "reducedCurrency"+"~"+createBankGuaranteeDto.getReducedCurrency());
			pendingAuthorizationService.delimitedTempStringValue(tempRefKey, 3+"", "outstandingCurrency"+"~"+createBankGuaranteeDto.getOutstandingCurrency());

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
public String viewTemplateAdviceofReduction()
{
	try{
	String msgRef = (String) session.get("messageRef");
	CreateBankGuaranteeService createBankGuaranteeService =  (CreateBankGuaranteeService)ApplicationContextProvider.getBean("createBankGuaranteeService");
	CreateBankGuaranteeDto createBankGuaranteeDto = createBankGuaranteeService.getAdviceReduction(msgRef);
	if(createBankGuaranteeDto.getBgNumber()!=null && StringUtils.isNotBlank(createBankGuaranteeDto.getBgNumber()) && StringUtils.isNotEmpty(createBankGuaranteeDto.getBgNumber())){
		setALLValueTODTO(createBankGuaranteeDto);
		
	}
	else
	{
		return "failure";
	}
	return "success";
	}catch(Exception exception)
	{
		exception.printStackTrace();
		return "input";
	
	}
}

@SkipValidation
public String displayPrintPreviewAdviceofReduction()
{
	String tempScreenString =null;
	String userId =null;
	String msgRef = (String) session.get("messageIndex");
	PendingAuthorizationService pendingAuthorizationService = (PendingAuthorizationService)ApplicationContextProvider.getBean("pendingAuthorizationService");
try{
	System.out.println("getHiddenTxnRef " +getHiddenTxnRef());
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
	CreateBankGuaranteeDto temp= getSerializedObject(tempScreenString);
	createBankGuaranteeDto.setBgNumber(temp.getBgNumber());
	createBankGuaranteeDto.setRelatedReference(temp.getRelatedReference());
	createBankGuaranteeDto.setBgAccountIdentification(temp.getBgAccountIdentification());
	createBankGuaranteeDto.setReductionDate(temp.getReductionDate());
	createBankGuaranteeDto.setChargeAmtIdentifier(temp.getChargeAmtIdentifier());
	createBankGuaranteeDto.setChargeAmt(temp.getChargeAmt());
	createBankGuaranteeDto.setBgCurrency(temp.getBgCurrency());
	createBankGuaranteeDto.setBgChargeAmount(temp.getBgChargeAmount());
	createBankGuaranteeDto.setChargeDate(temp.getChargeDate());
	createBankGuaranteeDto.setReducedCurrency(temp.getReducedCurrency());
	createBankGuaranteeDto.setReducedAmt(temp.getReducedAmt());
	createBankGuaranteeDto.setOutstandingCurrency(temp.getOutstandingCurrency());
	createBankGuaranteeDto.setOutstandingAmt(temp.getOutstandingAmt());
	createBankGuaranteeDto.setAmountSpecification(temp.getAmountSpecification());
	createBankGuaranteeDto.setAdvisingBank(temp.getAdvisingBank());
	createBankGuaranteeDto.setIssuingBankCode(temp.getIssuingBankCode());
	createBankGuaranteeDto.setAccountWithBank(temp.getAccountWithBank());
	createBankGuaranteeDto.setAccountwithBankCode(temp.getAccountwithBankCode());
	createBankGuaranteeDto.setAdviseThroughBankAcc(temp.getAdviseThroughBankAcc());
	createBankGuaranteeDto.setAuthorisedBankCode(temp.getAuthorisedBankCode());
	createBankGuaranteeDto.setAccountWithPartyLocation(temp.getAccountWithPartyLocation());
	createBankGuaranteeDto.setBgAccountWithNameandAddress(temp.getBgAccountWithNameandAddress());
	createBankGuaranteeDto.setChargesDetails(temp.getChargesDetails());
	createBankGuaranteeDto.setSenderToReceiverInformation(temp.getSenderToReceiverInformation());
	createBankGuaranteeDto.setIssuingBankCode(temp.getIssuingBankCode());
	createBankGuaranteeDto.setMsgRef(temp.getMsgRef());
	createBankGuaranteeDto.setRepair(temp.getRepair());
	createBankGuaranteeDto.setSeqNo(temp.getSeqNo());
	createBankGuaranteeDto.setMsgHost(temp.getMsgHost());
	setPrintPreviewTxnRef(getHiddenTxnRef());
}
else if(msgRef!=null && !msgRef.isEmpty())
{
	viewAdviceofReduction();
	return "success";
}
else
{
	createBankGuaranteeDto.setBgNumber(createBankGuaranteeDto.getBgNumber());
	createBankGuaranteeDto.setRelatedReference(createBankGuaranteeDto.getRelatedReference());
	createBankGuaranteeDto.setBgAccountIdentification(createBankGuaranteeDto.getBgAccountIdentification());
	createBankGuaranteeDto.setReductionDate(createBankGuaranteeDto.getReductionDate());
	createBankGuaranteeDto.setChargeAmtIdentifier(createBankGuaranteeDto.getChargeAmtIdentifier());
	createBankGuaranteeDto.setChargeAmt(createBankGuaranteeDto.getChargeAmt());
	createBankGuaranteeDto.setBgCurrency(createBankGuaranteeDto.getBgCurrency());
	createBankGuaranteeDto.setBgChargeAmount(createBankGuaranteeDto.getBgChargeAmount());
	createBankGuaranteeDto.setChargeDate(createBankGuaranteeDto.getChargeDate());
	createBankGuaranteeDto.setReducedCurrency(createBankGuaranteeDto.getReducedCurrency());
	createBankGuaranteeDto.setReducedAmt(createBankGuaranteeDto.getReducedAmt());
	createBankGuaranteeDto.setOutstandingCurrency(createBankGuaranteeDto.getOutstandingCurrency());
	createBankGuaranteeDto.setOutstandingAmt(createBankGuaranteeDto.getOutstandingAmt());
	createBankGuaranteeDto.setAmountSpecification(createBankGuaranteeDto.getAmountSpecification());
	createBankGuaranteeDto.setAdvisingBank(createBankGuaranteeDto.getAdvisingBank());
	createBankGuaranteeDto.setIssuingBankCode(createBankGuaranteeDto.getIssuingBankCode());
	createBankGuaranteeDto.setAccountWithBank(createBankGuaranteeDto.getAccountWithBank());
	createBankGuaranteeDto.setAccountwithBankCode(createBankGuaranteeDto.getAccountwithBankCode());
	createBankGuaranteeDto.setAdviseThroughBankAcc(createBankGuaranteeDto.getAdviseThroughBankAcc());
	createBankGuaranteeDto.setAuthorisedBankCode(createBankGuaranteeDto.getAuthorisedBankCode());
	createBankGuaranteeDto.setAccountWithPartyLocation(createBankGuaranteeDto.getAccountWithPartyLocation());
	createBankGuaranteeDto.setBgAccountWithNameandAddress(createBankGuaranteeDto.getBgAccountWithNameandAddress());
	createBankGuaranteeDto.setChargesDetails(createBankGuaranteeDto.getChargesDetails());
	createBankGuaranteeDto.setSenderToReceiverInformation(createBankGuaranteeDto.getSenderToReceiverInformation());
	createBankGuaranteeDto.setIssuingBankCode(createBankGuaranteeDto.getIssuingBankCode());
	createBankGuaranteeDto.setMsgRef(createBankGuaranteeDto.getMsgRef());
	createBankGuaranteeDto.setRepair(createBankGuaranteeDto.getRepair());
	createBankGuaranteeDto.setSeqNo(createBankGuaranteeDto.getSeqNo());
	createBankGuaranteeDto.setMsgHost(createBankGuaranteeDto.getMsgHost());
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
public String exportToExcelAdviceofReduction() throws Exception
{
	
try{
	displayPrintPreviewAdviceofReduction();
	String reportFile = "Advice_of_Reduction_(MT-769)";
	SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyyHHmmss");
	String currentDateTime = sdf.format(new Date());
	String reportFileName = reportFile+"_"+currentDateTime+"".concat(".xls");
	setReportFile(reportFileName);
	System.out.println("reportFileName is "+reportFileName);
	ExportToExcelUtil exportToExcelUtil = new ExportToExcelUtil();
	HSSFWorkbook myWorkBook = exportToExcelUtil.generateExportToExcel(null, createBankGuaranteeDto, null, reportFile, 769, "XXX");
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
public String generatePDFAdviceofReduction()
{
	displayPrintPreviewAdviceofReduction();
	String reportName ="Advice of Reduction (MT-769)";
	Map<String,String> fieldNamesMap = new HashMap<String,String>();
	
	try{
		
		fieldNamesMap.put("label.MB.IssuingBranchIFSC", getText("label.MB.IssuingBranchIFSC"));
		fieldNamesMap.put("label.MB.Benefifsc", getText("label.MB.Benefifsc"));
		
		fieldNamesMap.put("label.769.ppbgNumber", getText("label.769.ppbgNumber"));
		fieldNamesMap.put("label.769.ppbgrelatedReference", getText("label.769.ppbgrelatedReference"));
		fieldNamesMap.put("label.769.ppaccountIdentification", getText("label.769.ppaccountIdentification"));
		fieldNamesMap.put("label.769.ppdateofReduction", getText("label.769.ppdateofReduction"));
		fieldNamesMap.put("label.769.ppchargeAmount", getText("label.769.ppchargeAmount"));
		fieldNamesMap.put("label.769.ppchargeAmtCurrency", getText("label.769.ppchargeAmtCurrency"));
		fieldNamesMap.put("label.769.ppAmount", getText("label.769.ppAmount"));
		fieldNamesMap.put("label.769.ppchargeDate", getText("label.769.ppchargeDate"));
		fieldNamesMap.put("label.769.ppamountReducedAmtCurrency", getText("label.769.ppamountReducedAmtCurrency"));
		fieldNamesMap.put("label.769.ppamountReduced", getText("label.769.ppamountReduced"));
		fieldNamesMap.put("label.769.ppoutstandingAmtCurrency", getText("label.769.ppoutstandingAmtCurrency"));
		fieldNamesMap.put("label.769.ppoutstandingAmt", getText("label.769.ppoutstandingAmt"));
		fieldNamesMap.put("label.769.ppamountSpecification", getText("label.769.ppamountSpecification"));
		fieldNamesMap.put("label.769.ppaccountWithBank", getText("label.769.ppaccountWithBank"));
		fieldNamesMap.put("label.769.ppaccountWithPartyIFSC", getText("label.769.ppaccountWithPartyIFSC"));
		fieldNamesMap.put("label.769.ppaccountWithPartyLocation", getText("label.769.ppaccountWithPartyLocation"));
		fieldNamesMap.put("label.769.ppaccountWithNameAndAddress", getText("label.769.ppaccountWithNameAndAddress"));
		fieldNamesMap.put("label.769.ppchargesDetails", getText("label.769.ppchargesDetails"));
		fieldNamesMap.put("label.769.ppsenderToReceiverInformation", getText("label.769.ppsenderToReceiverInformation"));
		
		
		PaymentPDFGeneratationUtil paymentPDFGeneratationUtil = new PaymentPDFGeneratationUtil();
		paymentPDFGeneratationUtil.setServletRequest(servletRequest);
		paymentPDFGeneratationUtil.setReportName(reportName);
		
		paymentPDFGeneratationUtil.generatePaymentPDFReport(null, createBankGuaranteeDto, null, fieldNamesMap, 769, "XXX");
	}
	catch (Exception exception) {
		AuditServiceUtil.logException(exception,logger);
	}
	addActionError("Unable to Generate PDF file! Please try again");
	return "input";
}
}
