/**
 * 
 */
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
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
import com.logica.ngph.service.CreateBankGuaranteeService;
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
public class AmendBGAction extends ActionSupport 
			implements ModelDriven<CreateBankGuaranteeDto>, SessionAware, ServletRequestAware {
	
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
	private String msgHost;
	private String seqNo;
	private String printPreviewTxnRef;
	private String reportFile;
	private InputStream inputStream;
	private HttpServletRequest servletRequest;
	
	public HttpServletRequest getServletRequest() {
		return servletRequest;
	}

	public void setServletRequest(HttpServletRequest servletRequest) {
		this.servletRequest = servletRequest;
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
	
		
	public String getSeqNo() {
		return seqNo;
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
	public String displayAmendBG()
	{
		try{
					
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
			addFieldError("relatedReference", "Related Reference Number(21) must not contain two consecutive slashes '//'");
		}

		if(createBankGuaranteeDto.getBgIssueDate()==null){
			addFieldError("bgIssueDate", "Date of Issue or Request to Issue(31C) Is Mandatory");
		}
		
		if(StringUtils.isBlank(createBankGuaranteeDto.getAdvisingBank()) && StringUtils.isEmpty(createBankGuaranteeDto.getAdvisingBank()))
		{
				addFieldError("advisingBank", "Receiver Bank IFSC is required");
		}
	}

	
	public String createAmendBGSubmit() {
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
				txnKey = pendingAuthorizationService.getTranRef(serializeObject(),"Amend BG(MT-767)",userId);
				 eventLogging.doEventLogging(userId, "Amend BG", ConstantUtil.EVENTID_AMEND_BG+ConstantUtil.EVENTID_SUBMIT, ConstantUtil.EVENTLOGGINGCOMMENTSUBMIT,createBankGuaranteeDto.getBgNumber(),createBankGuaranteeDto.getMsgRef());
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
	public String getAmendBGAuthorization()
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
		CreateBankGuaranteeDto temp= getSerializedObject(tempScreenString);
		createBankGuaranteeDto.setSequence(temp.getSequence());
		createBankGuaranteeDto.setSequenceofTotal(temp.getSequenceofTotal());
		createBankGuaranteeDto.setBgNumber(temp.getBgNumber());
		createBankGuaranteeDto.setRelatedReference(temp.getRelatedReference());
		createBankGuaranteeDto.setBgFurtherIdentification(temp.getBgFurtherIdentification());
		createBankGuaranteeDto.setBgDate(temp.getBgDate());
		createBankGuaranteeDto.setAdvisingBank(temp.getAdvisingBank());
		createBankGuaranteeDto.setBgIssueDate(temp.getBgIssueDate());
		createBankGuaranteeDto.setBgNoOfAmntmnt(temp.getBgNoOfAmntmnt());
		createBankGuaranteeDto.setBgDetails(temp.getBgDetails());
		createBankGuaranteeDto.setSenderToReceiverInformation(temp.getSenderToReceiverInformation());
		createBankGuaranteeDto.setIssuingBankCode(temp.getIssuingBankCode());
		createBankGuaranteeDto.setMsgHost(temp.getMsgHost());
		createBankGuaranteeDto.setSeqNo(temp.getSeqNo());	
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
            System.out.println("object2: " + testDTO); 
            System.out.print("User(testDTO) :"+testDTO.getBgNumber());
          
            return testDTO;

		}
		catch (Exception e) 
		{
			e.printStackTrace();
			return null;
		}
	}
	
	
	public String getObjectForAmendBG()
	{
		try
		{	
			String userId = (String)session.get(WebConstants.CONSTANT_USER_NAME);
			PendingAuthorizationService pendingAuthorizationService = (PendingAuthorizationService)ApplicationContextProvider.getBean("pendingAuthorizationService");
			CreateBankGuaranteeService createBankGuaranteeService =  (CreateBankGuaranteeService)  ApplicationContextProvider.getBean("createBankGuaranteeService");
			PaymentMessageService paymentMessageService =(PaymentMessageService)ApplicationContextProvider.getBean("paymentMessageService");
			EventLogging eventLogging = (EventLogging)ApplicationContextProvider.getBean("eventLogging");
		if(getSaveAction().equalsIgnoreCase("Approve"))
		{			
			createBankGuaranteeService.createAmendBG(createBankGuaranteeDto, userId);
			pendingAuthorizationService.changeStatus(getSaveAction(), getHiddenTxnRef());
			if(StringUtils.isBlank(createBankGuaranteeDto.getRepair()) && StringUtils.isEmpty(createBankGuaranteeDto.getRepair()))
			{
				pendingAuthorizationService.changeStatus(getSaveAction(), getHiddenTxnRef());	
				 eventLogging.doEventLogging(userId, "Amend BG", ConstantUtil.EVENTID_AMEND_BG+ConstantUtil.EVENTID_APPROVE, ConstantUtil.EVENTLOGGINGCOMMENTAPPROVAL,createBankGuaranteeDto.getBgNumber(),createBankGuaranteeDto.getMsgRef());
			}
			else
			{
				paymentMessageService.changeMsgStatus2to99(createBankGuaranteeDto.getMsgRef());
				pendingAuthorizationService.changeStatus(getSaveAction(), getHiddenTxnRef());
				 eventLogging.doEventLogging(userId, "Amend BG", ConstantUtil.EVENTID_AMEND_BG+ConstantUtil.EVENTID_REJECT, ConstantUtil.EVENTLOGGINGCOMMENTREJECT,createBankGuaranteeDto.getBgNumber(),createBankGuaranteeDto.getMsgRef());
			}
		}
		else{
			if(StringUtils.isBlank(createBankGuaranteeDto.getRepair()) && StringUtils.isEmpty(createBankGuaranteeDto.getRepair()))
			{
				pendingAuthorizationService.changeStatus(getSaveAction(), getHiddenTxnRef());
			}
			else
			{
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
	public String viewAmendBG()
	{
		try{
		String msgRef = (String) session.get("messageIndex");
		CreateBankGuaranteeService createBankGuaranteeService =  (CreateBankGuaranteeService)  ApplicationContextProvider.getBean("createBankGuaranteeService");
		CreateBankGuaranteeDto createBankGuaranteeDto = createBankGuaranteeService.viewAmendBg(msgRef);
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
		createBankGuaranteeDto.setBgDetails(temp.getBgDetails());
		try
		{
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
			Date issueDate = temp.getBgIssueDate();
			String IssueNewDate = sdf.format(issueDate);
			Date date = sdf.parse(IssueNewDate);
			createBankGuaranteeDto.setBgIssueDate(date);
		}
		catch (ParseException e) {
			e.printStackTrace();
		}
		createBankGuaranteeDto.setBgFurtherIdentification(temp.getBgFurtherIdentification());
		createBankGuaranteeDto.setBgNoOfAmntmnt(temp.getBgNoOfAmntmnt());
		createBankGuaranteeDto.setAdvisingBank(temp.getAdvisingBank());
		createBankGuaranteeDto.setIssuingBankCode(temp.getIssuingBankCode());
		createBankGuaranteeDto.setSenderToReceiverInformation(temp.getSenderToReceiverInformation());
		createBankGuaranteeDto.setRepair(temp.getRepair());
		createBankGuaranteeDto.setMsgHost(temp.getMsgHost());
		createBankGuaranteeDto.setSeqNo(temp.getSeqNo());
		createBankGuaranteeDto.setSequenceofTotal(temp.getSequenceofTotal());
		createBankGuaranteeDto.setSequence(temp.getSequence());
	
        session.put("ScreenData", createBankGuaranteeDto);
	}
	
	private void setValueTODTO(CreateBankGuaranteeDto obj) throws ParseException
	{
		
		CreateBankGuaranteeDto temp = obj;
		createBankGuaranteeDto.setRelatedReference(temp.getRelatedReference());
		createBankGuaranteeDto.setBgDetails(temp.getBgDetails());
		try
		{
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
			Date issueDate = temp.getBgIssueDate();
			String IssueNewDate = sdf.format(issueDate);
			Date date = sdf.parse(IssueNewDate);
			createBankGuaranteeDto.setBgIssueDate(date);
		}
		catch (ParseException e) {
			e.printStackTrace();
		}
		createBankGuaranteeDto.setBgFurtherIdentification(temp.getBgFurtherIdentification());
		createBankGuaranteeDto.setBgNoOfAmntmnt(temp.getBgNoOfAmntmnt());
		createBankGuaranteeDto.setAdvisingBank(temp.getAdvisingBank());
		createBankGuaranteeDto.setIssuingBankCode(temp.getIssuingBankCode());
		createBankGuaranteeDto.setSenderToReceiverInformation(temp.getSenderToReceiverInformation());
		createBankGuaranteeDto.setSequenceofTotal(temp.getSequenceofTotal());
		createBankGuaranteeDto.setSequence(temp.getSequence());
	}
	
	public String displayAmendBGData()
	{
		try
		{
			String bgNumber = createBankGuaranteeDto.getRelatedReference();
			CreateBankGuaranteeDto createBankGuaranteeDto = null;
			boolean isValidBGNumber = false;
			isValidBGNumber=validateBGNumber(bgNumber);
			  if(isValidBGNumber)
			  {
				  CreateBankGuaranteeService createBankGuaranteeService =  (CreateBankGuaranteeService)ApplicationContextProvider.getBean("createBankGuaranteeService");
				  createBankGuaranteeDto = createBankGuaranteeService.getAmendBGScreenData(bgNumber);
				  setValueTODTO(createBankGuaranteeDto);
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
		 addFieldError("relatedReference", "Unable to Perform BG Related Reference Number");
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
	
	public String saveAmendBGTemplate() {
		
		try {			
			String tempRefKey="";
			PendingAuthorizationService pendingAuthorizationService = (PendingAuthorizationService)ApplicationContextProvider.getBean("pendingAuthorizationService");
			String userId = (String)session.get(WebConstants.CONSTANT_USER_NAME);
			
			tempRefKey = pendingAuthorizationService.getTempRef(serializeObject(),"767","XXX",tempName,"Amend BG(MT-767)",userId);

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
	public String viewTemplateAmendBG()
	{
		try{
			
		String msgRef = (String) session.get("messageRef");
		System.out.println("messageRef is "+msgRef);
		CreateBankGuaranteeService createBankGuaranteeService =  (CreateBankGuaranteeService)ApplicationContextProvider.getBean("createBankGuaranteeService");
		CreateBankGuaranteeDto createBankGuaranteeDto = createBankGuaranteeService.viewAmendBg(msgRef);
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
	public String displayPrintPreviewAmendBG()
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
			createBankGuaranteeDto.setSequence(temp.getSequence());
			createBankGuaranteeDto.setSequenceofTotal(temp.getSequenceofTotal());
			createBankGuaranteeDto.setBgNumber(temp.getBgNumber());
			createBankGuaranteeDto.setRelatedReference(temp.getRelatedReference());
			createBankGuaranteeDto.setBgFurtherIdentification(temp.getBgFurtherIdentification());
			createBankGuaranteeDto.setBgDate(temp.getBgDate());
			createBankGuaranteeDto.setAdvisingBank(temp.getAdvisingBank());
			createBankGuaranteeDto.setBgIssueDate(temp.getBgIssueDate());
			createBankGuaranteeDto.setBgNoOfAmntmnt(temp.getBgNoOfAmntmnt());
			createBankGuaranteeDto.setBgDetails(temp.getBgDetails());
			createBankGuaranteeDto.setSenderToReceiverInformation(temp.getSenderToReceiverInformation());
			createBankGuaranteeDto.setIssuingBankCode(temp.getIssuingBankCode());
			createBankGuaranteeDto.setMsgHost(temp.getMsgHost());
			createBankGuaranteeDto.setSeqNo(temp.getSeqNo());
			createBankGuaranteeDto.setSequenceofTotal(temp.getSequenceofTotal());
			createBankGuaranteeDto.setSequence(temp.getSequence());
			setPrintPreviewTxnRef(getHiddenTxnRef());
		}
		else if(msgRef!=null && !msgRef.isEmpty())
		{
			viewAmendBG();
			return "success";
		}
		else
		{
			createBankGuaranteeDto.setSequence(createBankGuaranteeDto.getSequence());
			createBankGuaranteeDto.setSequenceofTotal(createBankGuaranteeDto.getSequenceofTotal());
			createBankGuaranteeDto.setBgNumber(createBankGuaranteeDto.getBgNumber());
			createBankGuaranteeDto.setRelatedReference(createBankGuaranteeDto.getRelatedReference());
			createBankGuaranteeDto.setBgFurtherIdentification(createBankGuaranteeDto.getBgFurtherIdentification());
			createBankGuaranteeDto.setBgDate(createBankGuaranteeDto.getBgDate());
			createBankGuaranteeDto.setAdvisingBank(createBankGuaranteeDto.getAdvisingBank());
			createBankGuaranteeDto.setBgIssueDate(createBankGuaranteeDto.getBgIssueDate());
			createBankGuaranteeDto.setBgNoOfAmntmnt(createBankGuaranteeDto.getBgNoOfAmntmnt());
			createBankGuaranteeDto.setBgDetails(createBankGuaranteeDto.getBgDetails());
			createBankGuaranteeDto.setSenderToReceiverInformation(createBankGuaranteeDto.getSenderToReceiverInformation());
			createBankGuaranteeDto.setIssuingBankCode(createBankGuaranteeDto.getIssuingBankCode());
			createBankGuaranteeDto.setMsgHost(createBankGuaranteeDto.getMsgHost());
			createBankGuaranteeDto.setSeqNo(createBankGuaranteeDto.getSeqNo());
			createBankGuaranteeDto.setSequenceofTotal(createBankGuaranteeDto.getSequenceofTotal());
			createBankGuaranteeDto.setSequence(createBankGuaranteeDto.getSequence());
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
			createBankGuaranteeDto.setBgFurtherIdentification("");
			createBankGuaranteeDto.setBgDate(null);
			createBankGuaranteeDto.setAdvisingBank("");
			createBankGuaranteeDto.setBgIssueDate(null);
			createBankGuaranteeDto.setBgNoOfAmntmnt(0);
			createBankGuaranteeDto.setBgDetails("");
			createBankGuaranteeDto.setSenderToReceiverInformation("");
			createBankGuaranteeDto.setIssuingBankCode("");
			createBankGuaranteeDto.setMsgHost("");
			createBankGuaranteeDto.setSeqNo("");
			createBankGuaranteeDto.setSequenceofTotal("");
			createBankGuaranteeDto.setSequence("");
			
		return "success";
		}catch (Exception e) {
			logger.error(e,e);
			return "input";
		}

	}
	
	@SkipValidation
	public String exportToExcelAmendBG() throws Exception
	{
		
		
	try{
		displayPrintPreviewAmendBG();
		String reportFile = "Amend_BG_(MT-767)";
		SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyyHHmmss");
		String currentDateTime = sdf.format(new Date());
		String reportFileName = reportFile+"_"+currentDateTime+"".concat(".xls");
		setReportFile(reportFileName);
		System.out.println("reportFileName is "+reportFileName);
		ExportToExcelUtil exportToExcelUtil = new ExportToExcelUtil();
		HSSFWorkbook myWorkBook = exportToExcelUtil.generateExportToExcel(null, createBankGuaranteeDto, null, reportFile, 767, "XXX");
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
	public String generatePDFAmendBG()
	{
		displayPrintPreviewAmendBG();
		String reportName ="Amend BG (MT-767)";
		Map<String,String> fieldNamesMap = new HashMap<String,String>();
		
		try{
			
			fieldNamesMap.put("label.MB.IssuingBranchIFSC", getText("label.MB.IssuingBranchIFSC"));
			fieldNamesMap.put("label.MB.Benefifsc", getText("label.MB.Benefifsc"));
			
			fieldNamesMap.put("label.767.ppSequenceofTotal", getText("label.767.ppSequenceofTotal"));
			fieldNamesMap.put("label.767.pptransactionReferenceNumber", getText("label.767.pptransactionReferenceNumber"));
			fieldNamesMap.put("label.767.ppbgrelatedReference", getText("label.767.ppbgrelatedReference"));
			fieldNamesMap.put("label.767.ppFurtherIdentification", getText("label.767.ppFurtherIdentification"));
			fieldNamesMap.put("label.767.ppbgDate", getText("label.767.ppbgDate"));
			fieldNamesMap.put("label.767.ppNumberofAmendment", getText("label.767.ppNumberofAmendment"));
			fieldNamesMap.put("label.767.ppDateofIssue", getText("label.767.ppDateofIssue"));
			fieldNamesMap.put("label.767.ppamendDetails", getText("label.767.ppamendDetails"));
			fieldNamesMap.put("label.767.ppSendertoReceiverInformation", getText("label.767.ppSendertoReceiverInformation"));
			
			
			PaymentPDFGeneratationUtil paymentPDFGeneratationUtil = new PaymentPDFGeneratationUtil();
			paymentPDFGeneratationUtil.setServletRequest(servletRequest);
			paymentPDFGeneratationUtil.setReportName(reportName);
			
			paymentPDFGeneratationUtil.generatePaymentPDFReport(null, createBankGuaranteeDto,null,fieldNamesMap, 767 , "XXX");
		}
		catch (Exception exception) {
			AuditServiceUtil.logException(exception,logger);
		}
		addActionError("Unable to Generate PDF file! Please try again");
		return "input";
	}
}
