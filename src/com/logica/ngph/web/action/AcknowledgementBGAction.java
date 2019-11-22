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

/**
 * @author chakkar
 *
 */
public class AcknowledgementBGAction extends ActionSupport implements ModelDriven<CreateBankGuaranteeDto>, SessionAware, ServletRequestAware {
	
	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(AmendBGAction.class);
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

	@SkipValidation
	public String displayAckBG()
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
	
	public String getAckBGSubmit() {
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
				txnKey = pendingAuthorizationService.getTranRef(serializeObject(),"Acknowledge BG(MT-768)",userId);
				eventLogging.doEventLogging(userId, "Acknowledge BG", ConstantUtil.EVENTID_BANK_GUARANTEE+ConstantUtil.EVENTID_SUBMIT, ConstantUtil.EVENTLOGGINGCOMMENTSUBMIT,createBankGuaranteeDto.getBgNumber(),createBankGuaranteeDto.getMsgRef());
				pendingAuthorizationService.delimitedStringValue(txnKey, 1+"", "bgCurrency"+"~"+createBankGuaranteeDto.getBgCurrency());
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
	
	private String serializeObject(){
		CreateBankGuaranteeDto createBankGuaranteeDTO = new CreateBankGuaranteeDto();
		try{
			String userId = (String)session.get(WebConstants.CONSTANT_USER_NAME);
			
			createBankGuaranteeDTO = createBankGuaranteeDto;
			System.out.print("BG NUMBER :"+createBankGuaranteeDTO.getBgNumber());
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
	
	
	@SkipValidation
	public String getAckBGAuthorization()
	{
		String tempScreenString =null;
		PendingAuthorizationService pendingAuthorizationService = (PendingAuthorizationService) ApplicationContextProvider.getBean("pendingAuthorizationService");
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
		
		createBankGuaranteeDto.setBgNumber(temp.getBgNumber());//20
		createBankGuaranteeDto.setRelatedReference(temp.getRelatedReference());
		createBankGuaranteeDto.setBgCreateType(temp.getBgCreateType());
		createBankGuaranteeDto.setBgAccountIdentification(temp.getBgAccountIdentification());
		createBankGuaranteeDto.setDateofMessageBeingAcknowledged(temp.getDateofMessageBeingAcknowledged());
		createBankGuaranteeDto.setBgChargeAmount(temp.getBgChargeAmount());
		createBankGuaranteeDto.setBgDebitDate(temp.getBgDate());
		createBankGuaranteeDto.setBgRuleCode(temp.getBgRuleCode());
		createBankGuaranteeDto.setChargesDetails(temp.getChargesDetails());	
		createBankGuaranteeDto.setSenderToReceiverInformation(temp.getSenderToReceiverInformation());
		createBankGuaranteeDto.setAdviseThroughBankpartyidentifier(temp.getAdviseThroughBankpartyidentifier());
		createBankGuaranteeDto.setAdviseThroughBankAcc(temp.getAdviseThroughBankAcc());
		createBankGuaranteeDto.setAdviseThroughBankCode(temp.getAdviseThroughBankCode());
		createBankGuaranteeDto.setAccountwithPartyLocation(temp.getAccountwithPartyLocation());
		createBankGuaranteeDto.setAdviseThroughBankName(temp.getAdviseThroughBankName());
		createBankGuaranteeDto.setIssuingBankCode(temp.getIssuingBankCode());
		createBankGuaranteeDto.setAdvisingBank(temp.getAdvisingBank());
		createBankGuaranteeDto.setLcCurrency(temp.getLcCurrency());
		createBankGuaranteeDto.setSeqNo(temp.getSeqNo());
		
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
			}
		}
		createBankGuaranteeDto.setBgCurrency(temp.getBgCurrency());
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
		            		          
		            return testDTO;

				}
				catch (Exception e) {
					e.printStackTrace();
					return null;
				}
			}

	public String displayAckBGData()
	{
		try{
			String bgNumber = createBankGuaranteeDto.getBgNumber();
			CreateBankGuaranteeDto createBankGuaranteeDto = null;
		  boolean isValidBGNumber = false;
		  isValidBGNumber=validateBGNumber(bgNumber);
		  
		  if(isValidBGNumber)
		  {
			  CreateBankGuaranteeService createBankGuaranteeService =  (CreateBankGuaranteeService)  ApplicationContextProvider.getBean("createBankGuaranteeService");
			  createBankGuaranteeDto = createBankGuaranteeService.getAmendBGScreenData(bgNumber);
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
	addFieldError("BG Related Reference", "Unable Do Perform BG Related Reference Number Not In DataBase");
	
	return "input";
	}

	private boolean validateBGNumber(String lcNumber){
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
	
	public String getObjectForAckBG()
	{
		PendingAuthorizationService pendingAuthorizationService = (PendingAuthorizationService)ApplicationContextProvider.getBean("pendingAuthorizationService");
		PaymentMessageService paymentMessageService =(PaymentMessageService)ApplicationContextProvider.getBean("paymentMessageService");
		EventLogging eventLogging = (EventLogging)ApplicationContextProvider.getBean("eventLogging");
		CreateBankGuaranteeService createBankGuaranteeService =  (CreateBankGuaranteeService)  ApplicationContextProvider.getBean("createBankGuaranteeService");
		try{
			String userId = (String)session.get(WebConstants.CONSTANT_USER_NAME);
			if(getSaveAction().equalsIgnoreCase("Approve"))
			{			
						
				if(StringUtils.isBlank(createBankGuaranteeDto.getRepair()) && StringUtils.isEmpty(createBankGuaranteeDto.getRepair())){
					createBankGuaranteeService.createAckBG(createBankGuaranteeDto, userId);
					pendingAuthorizationService.changeStatus(getSaveAction(), getHiddenTxnRef());
					eventLogging.doEventLogging(userId, "Acknowledge BG", ConstantUtil.EVENTID_ACK_BG+ConstantUtil.EVENTID_APPROVE, ConstantUtil.EVENTLOGGINGCOMMENTAPPROVAL,createBankGuaranteeDto.getBgNumber(),createBankGuaranteeDto.getMsgRef());
				}
				else
				{
					paymentMessageService.changeMsgStatus2to99(createBankGuaranteeDto.getMsgRef());
					pendingAuthorizationService.changeStatus(getSaveAction(), getHiddenTxnRef());
					createBankGuaranteeService.createAckBG(createBankGuaranteeDto, userId);
				}
		}
		else
		{
			if(StringUtils.isBlank(createBankGuaranteeDto.getRepair()) && StringUtils.isEmpty(createBankGuaranteeDto.getRepair())){
				pendingAuthorizationService.changeStatus(getSaveAction(), getHiddenTxnRef());
				eventLogging.doEventLogging(userId, "Acknowledge BG", ConstantUtil.EVENTID_ACK_BG+ConstantUtil.EVENTID_REJECT, ConstantUtil.EVENTLOGGINGCOMMENTREJECT,createBankGuaranteeDto.getBgNumber(),createBankGuaranteeDto.getMsgRef());
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
	
	public String saveTemplate() {
		
		try {			
			String tempRefKey="";
			PendingAuthorizationService pendingAuthorizationService = (PendingAuthorizationService)ApplicationContextProvider.getBean("pendingAuthorizationService");
			String userId = (String)session.get(WebConstants.CONSTANT_USER_NAME);
			
			tempRefKey = pendingAuthorizationService.getTempRef(serializeObject(),"768","XXX",tempName.toUpperCase(),"Acknowledge BG(MT-768)",userId);
			
			pendingAuthorizationService.delimitedTempStringValue(tempRefKey, 1+"", "bgCurrency"+"~"+createBankGuaranteeDto.getBgCurrency());

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
	
	public void validate() {

		if (createBankGuaranteeDto.getBgNumber().startsWith("/")) {
			addFieldError("bgNumber","Transaction Reference No(20) Must Not start with /");
		} else if (createBankGuaranteeDto.getBgNumber().endsWith("/")) {
			addFieldError("bgNumber","Transaction Reference No(20) must not End with /");		
		} else if (createBankGuaranteeDto.getBgNumber().contains("//")) {
			addFieldError("bgNumber","Transaction Reference No(20) must not contain two consecutive slashes '//'");
		}

		if (createBankGuaranteeDto.getRelatedReference().startsWith("/")) {
			addFieldError("relatedReference","Related Reference(21) Must Not start with /");
		} else if (createBankGuaranteeDto.getRelatedReference().endsWith("/")) {
			addFieldError("relatedReference","Related Reference(21) must not End with /");
		} else if (createBankGuaranteeDto.getRelatedReference().contains("//")) {
			addFieldError("relatedReference","Related Reference(21) must not contain two consecutive slashes '//'");
		}
		
		if(StringUtils.isNotBlank(createBankGuaranteeDto.getBgAccountIdentification())&& StringUtils.isNotEmpty(createBankGuaranteeDto.getBgAccountIdentification()))
		{
			if(StringUtils.isNotEmpty(createBankGuaranteeDto.getAdviseThroughBankpartyidentifier()) || StringUtils.isNotEmpty(createBankGuaranteeDto.getAdviseThroughBankCode()) || StringUtils.isNotEmpty(createBankGuaranteeDto.getAccountWithPartyLocation()) || StringUtils.isNotEmpty(createBankGuaranteeDto.getAdviseThroughBankName()))
			{
				addFieldError("BgAccountIdentification", "Either field 25 or 57a, but not both, may be present");
			}
		}
		if(createBankGuaranteeDto.getBgDebitDate()!=null && createBankGuaranteeDto.getBgChargeAmount()!=null && StringUtils.isNotBlank(createBankGuaranteeDto.getBgCurrency()))
		{
			if(StringUtils.isNotEmpty(createBankGuaranteeDto.getAdviseThroughBankpartyidentifier()) || StringUtils.isNotEmpty(createBankGuaranteeDto.getAdviseThroughBankCode()) || StringUtils.isNotEmpty(createBankGuaranteeDto.getAccountWithPartyLocation()) || StringUtils.isNotEmpty(createBankGuaranteeDto.getAdviseThroughBankName()))
			{
				addFieldError("BgDebitDate", "If field 32D is present, field 57a must not be present");
			}
		}
		if(StringUtils.isNotBlank(createBankGuaranteeDto.getChargesDetails()) && StringUtils.isNotEmpty(createBankGuaranteeDto.getChargesDetails()))
		{
			if(StringUtils.isBlank(createBankGuaranteeDto.getLcCurrency()))
			{
				addFieldError("ChargesDetails", "If field 71B is present, field 32a must also be present");
			}
			
		}
		if(StringUtils.isBlank(createBankGuaranteeDto.getAdvisingBank()) && StringUtils.isEmpty(createBankGuaranteeDto.getAdvisingBank()))
		{
				addFieldError("advisingBank", "Receiver Bank IFSC is required");
		}

	}

	
	@SkipValidation
	public String viewAckBG()
	{
		try{
			
		String msgRef = (String) session.get("messageIndex");
		CreateBankGuaranteeService createBankGuaranteeService =  (CreateBankGuaranteeService)ApplicationContextProvider.getBean("createBankGuaranteeService");
		CreateBankGuaranteeDto createBankGuaranteeDto = createBankGuaranteeService.viewAckBg(msgRef);
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
		createBankGuaranteeDto.setBgCreateType(temp.getBgCreateType());
		createBankGuaranteeDto.setBgAccountIdentification(temp.getBgAccountIdentification());
		createBankGuaranteeDto.setDateofMessageBeingAcknowledged(temp.getDateofMessageBeingAcknowledged());
		createBankGuaranteeDto.setBgCurrency(temp.getBgCurrency());
		createBankGuaranteeDto.setBgChargeAmount(temp.getBgChargeAmount());
		createBankGuaranteeDto.setBgDebitDate(temp.getBgDate());
		createBankGuaranteeDto.setBgRuleCode(temp.getBgRuleCode());
		createBankGuaranteeDto.setChargesDetails(temp.getChargesDetails());	
		createBankGuaranteeDto.setSenderToReceiverInformation(temp.getSenderToReceiverInformation());
		createBankGuaranteeDto.setAdviseThroughBankpartyidentifier(temp.getAdviseThroughBankpartyidentifier());
		createBankGuaranteeDto.setAdviseThroughBankAcc(temp.getAdviseThroughBankAcc());
		createBankGuaranteeDto.setAdviseThroughBankCode(temp.getAdviseThroughBankCode());
		createBankGuaranteeDto.setAccountwithPartyLocation(temp.getAccountwithPartyLocation());
		createBankGuaranteeDto.setAdviseThroughBankName(temp.getAdviseThroughBankName());
		createBankGuaranteeDto.setIssuingBankCode(temp.getIssuingBankCode());
		createBankGuaranteeDto.setAdvisingBank(temp.getAdvisingBank());
		createBankGuaranteeDto.setMsgRef(temp.getMsgRef());
		createBankGuaranteeDto.setSeqNo(temp.getSeqNo());
		createBankGuaranteeDto.setMsgHost(temp.getMsgHost());
	
        session.put("ScreenData", createBankGuaranteeDto);
	}
	
	@SkipValidation
	public String viewTemplateAckBG()
	{
		try{
			
		String msgRef = (String) session.get("messageRef");
		CreateBankGuaranteeService createBankGuaranteeService =  (CreateBankGuaranteeService)ApplicationContextProvider.getBean("createBankGuaranteeService");
		CreateBankGuaranteeDto createBankGuaranteeDto = createBankGuaranteeService.viewAckBg(msgRef);
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
	public String displayPrintPreviewAckBG()
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
				
				
				CreateBankGuaranteeDto temp= getSerializedObject(tempScreenString);
				createBankGuaranteeDto.setBgNumber(temp.getBgNumber());
				createBankGuaranteeDto.setRelatedReference(temp.getRelatedReference());
				createBankGuaranteeDto.setBgCreateType(temp.getBgCreateType());
				createBankGuaranteeDto.setBgAccountIdentification(temp.getBgAccountIdentification());
				createBankGuaranteeDto.setDateofMessageBeingAcknowledged(temp.getDateofMessageBeingAcknowledged());
				createBankGuaranteeDto.setBgCurrency(temp.getBgCurrency());
				createBankGuaranteeDto.setBgChargeAmount(temp.getBgChargeAmount());
				createBankGuaranteeDto.setBgDebitDate(temp.getBgDate());
				createBankGuaranteeDto.setBgRuleCode(temp.getBgRuleCode());
				createBankGuaranteeDto.setChargesDetails(temp.getChargesDetails());	
				createBankGuaranteeDto.setSenderToReceiverInformation(temp.getSenderToReceiverInformation());
				createBankGuaranteeDto.setAdviseThroughBankpartyidentifier(temp.getAdviseThroughBankpartyidentifier());
				createBankGuaranteeDto.setAdviseThroughBankAcc(temp.getAdviseThroughBankAcc());
				createBankGuaranteeDto.setAdviseThroughBankCode(temp.getAdviseThroughBankCode());
				createBankGuaranteeDto.setAccountwithPartyLocation(temp.getAccountwithPartyLocation());
				createBankGuaranteeDto.setAdviseThroughBankName(temp.getAdviseThroughBankName());
				createBankGuaranteeDto.setIssuingBankCode(temp.getIssuingBankCode());
				createBankGuaranteeDto.setAdvisingBank(temp.getAdvisingBank());
				createBankGuaranteeDto.setLcCurrency(temp.getLcCurrency());
				createBankGuaranteeDto.setSeqNo(temp.getSeqNo());
				setPrintPreviewTxnRef(getHiddenTxnRef());
		}
			else if(msgRef!=null && !msgRef.isEmpty())
			{
				viewAckBG();
				return "success";
			}
		else
		{
			createBankGuaranteeDto.setBgNumber(createBankGuaranteeDto.getBgNumber());
			createBankGuaranteeDto.setRelatedReference(createBankGuaranteeDto.getRelatedReference());
			createBankGuaranteeDto.setBgCreateType(createBankGuaranteeDto.getBgCreateType());
			createBankGuaranteeDto.setBgAccountIdentification(createBankGuaranteeDto.getBgAccountIdentification());
			createBankGuaranteeDto.setDateofMessageBeingAcknowledged(createBankGuaranteeDto.getDateofMessageBeingAcknowledged());
			createBankGuaranteeDto.setBgCurrency(createBankGuaranteeDto.getBgCurrency());
			createBankGuaranteeDto.setBgChargeAmount(createBankGuaranteeDto.getBgChargeAmount());
			createBankGuaranteeDto.setBgDebitDate(createBankGuaranteeDto.getBgDate());
			createBankGuaranteeDto.setBgRuleCode(createBankGuaranteeDto.getBgRuleCode());
			createBankGuaranteeDto.setChargesDetails(createBankGuaranteeDto.getChargesDetails());	
			createBankGuaranteeDto.setSenderToReceiverInformation(createBankGuaranteeDto.getSenderToReceiverInformation());
			createBankGuaranteeDto.setAdviseThroughBankpartyidentifier(createBankGuaranteeDto.getAdviseThroughBankpartyidentifier());
			createBankGuaranteeDto.setAdviseThroughBankAcc(createBankGuaranteeDto.getAdviseThroughBankAcc());
			createBankGuaranteeDto.setAdviseThroughBankCode(createBankGuaranteeDto.getAdviseThroughBankCode());
			createBankGuaranteeDto.setAccountwithPartyLocation(createBankGuaranteeDto.getAccountwithPartyLocation());
			createBankGuaranteeDto.setAdviseThroughBankName(createBankGuaranteeDto.getAdviseThroughBankName());
			createBankGuaranteeDto.setIssuingBankCode(createBankGuaranteeDto.getIssuingBankCode());
			createBankGuaranteeDto.setAdvisingBank(createBankGuaranteeDto.getAdvisingBank());
			createBankGuaranteeDto.setLcCurrency(createBankGuaranteeDto.getLcCurrency());
			createBankGuaranteeDto.setSeqNo(createBankGuaranteeDto.getSeqNo());
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
			createBankGuaranteeDto.setBgNumber("");
			createBankGuaranteeDto.setRelatedReference("");
			createBankGuaranteeDto.setBgCreateType("");
			createBankGuaranteeDto.setBgAccountIdentification("");
			createBankGuaranteeDto.setDateofMessageBeingAcknowledged(null);
			createBankGuaranteeDto.setBgCurrency("");
			createBankGuaranteeDto.setBgChargeAmount(BigDecimal.ZERO);
			createBankGuaranteeDto.setBgDebitDate(null);
			createBankGuaranteeDto.setBgRuleCode("");
			createBankGuaranteeDto.setChargesDetails("");	
			createBankGuaranteeDto.setSenderToReceiverInformation("");
			createBankGuaranteeDto.setAdviseThroughBankpartyidentifier("");
			createBankGuaranteeDto.setAdviseThroughBankAcc("");
			createBankGuaranteeDto.setAdviseThroughBankCode("");
			createBankGuaranteeDto.setAccountwithPartyLocation("");
			createBankGuaranteeDto.setAdviseThroughBankName("");
			createBankGuaranteeDto.setIssuingBankCode("");
			createBankGuaranteeDto.setAdvisingBank("");
			createBankGuaranteeDto.setLcCurrency("");
			createBankGuaranteeDto.setSeqNo("");
		return "success";
		}catch (Exception e) {
			logger.error(e,e);
			return "input";
		}

	}
	
	
	@SkipValidation
	public String exportToExcelAckBG() throws Exception
	{
		
		
	try{
		displayPrintPreviewAckBG();
		String reportFile = "BG_Acknowledgement_(MT-768)";
		SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyyHHmmss");
		String currentDateTime = sdf.format(new Date());
		String reportFileName = reportFile+"_"+currentDateTime+"".concat(".xls");
		setReportFile(reportFileName);
		System.out.println("reportFileName is "+reportFileName);
		ExportToExcelUtil exportToExcelUtil = new ExportToExcelUtil();
		HSSFWorkbook myWorkBook = exportToExcelUtil.generateExportToExcel(null, createBankGuaranteeDto, null, reportFile, 768, "XXX");
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
	public String generatePDFAckBG()
	{
		displayPrintPreviewAckBG();
		String reportName ="BG Acknowledgement (MT-768)";
		Map<String,String> fieldNamesMap = new HashMap<String,String>();
		
		try{
			
			fieldNamesMap.put("label.MB.IssuingBranchIFSC", getText("label.MB.IssuingBranchIFSC"));
			fieldNamesMap.put("label.MB.Benefifsc", getText("label.MB.Benefifsc"));
			
			fieldNamesMap.put("label.768.pptransactionReferenceNumber", getText("label.768.pptransactionReferenceNumber"));
			fieldNamesMap.put("label.768.ppbgrelatedReference", getText("label.768.ppbgrelatedReference"));
			fieldNamesMap.put("label.768.ppaccountIdentification", getText("label.768.ppaccountIdentification"));
			fieldNamesMap.put("label.768.ppdateofMessageBeingAcknowledged", getText("label.768.ppdateofMessageBeingAcknowledged"));
			fieldNamesMap.put("label.768.ppchargeAmount", getText("label.768.ppchargeAmount"));
			fieldNamesMap.put("label.768.ppDateofIssue", getText("label.768.ppDateofIssue"));
			fieldNamesMap.put("label.768.ppCurrency", getText("label.768.ppCurrency"));
			fieldNamesMap.put("label.768.ppamount", getText("label.768.ppamount"));
			fieldNamesMap.put("label.768.ppaccountIdentification", getText("label.768.ppaccountIdentification"));
			fieldNamesMap.put("label.768.ppaccountWithPartyIdentifier1", getText("label.768.ppaccountWithPartyIdentifier1"));
			fieldNamesMap.put("label.768.ppaccountWithPartyIFSC", getText("label.768.ppaccountWithPartyIFSC"));
			fieldNamesMap.put("label.768.ppaccountWithPartyLocation", getText("label.768.ppaccountWithPartyLocation"));
			fieldNamesMap.put("label.768.ppaccountWithNameAndAddress", getText("label.768.ppaccountWithNameAndAddress"));
			fieldNamesMap.put("label.768.ppchargeDetails", getText("label.768.ppchargeDetails"));
			fieldNamesMap.put("label.768.ppsenderToReceiverInformation", getText("label.768.ppsenderToReceiverInformation"));
			
			
			PaymentPDFGeneratationUtil paymentPDFGeneratationUtil = new PaymentPDFGeneratationUtil();
			paymentPDFGeneratationUtil.setServletRequest(servletRequest);
			paymentPDFGeneratationUtil.setReportName(reportName);
			
			paymentPDFGeneratationUtil.generatePaymentPDFReport(null, createBankGuaranteeDto, null, fieldNamesMap, 768, "XXX");
		}
		catch (Exception exception) {
			AuditServiceUtil.logException(exception,logger);
		}
		addActionError("Unable to Generate PDF file! Please try again");
		return "input";
	}
	
	
}
