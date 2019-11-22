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
import java.text.Format;
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
import com.logica.ngph.dtos.BgMastDto;
import com.logica.ngph.dtos.CreateBankGuaranteeDto;
import com.logica.ngph.dtos.LCCanonicalDto;
import com.logica.ngph.service.CreateBankGuaranteeService;
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

public class CreateBankGuaranteeAction extends ActionSupport implements
		ModelDriven<CreateBankGuaranteeDto>, SessionAware , ServletRequestAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(CreateBankGuaranteeAction.class);
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
	private String createdUserId;
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
	 * @return the createdUserId
	 */
	public String getCreatedUserId() {
		return createdUserId;
	}

	/**
	 * @param createdUserId the createdUserId to set
	 */
	public void setCreatedUserId(String createdUserId) {
		this.createdUserId = createdUserId;
	}

	@SkipValidation
	public String createBankGuarantee() {
		try {
			System.out.println(createBankGuaranteeDto.getBgCreateType());
			//reset(createBankGuaranteeDto);
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
	
	public String getBGApproval() {
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
				  txnKey = pendingAuthorizationService.getTranRef(serializeObject(),"Create Bank Guarantee(MT-760)",userId);
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
	public String getBGAuthorization()
	{
		String tempScreenString = null;
		PendingAuthorizationService pendingAuthorizationService = (PendingAuthorizationService)ApplicationContextProvider.getBean("pendingAuthorizationService");
	try{
		if(getTxnRef()!=null)
		{
		setCheckForSubmit("Display_Approve_Reject");
		String userId = pendingAuthorizationService.getCreatedUser(getTxnRef());
		String userRole = pendingAuthorizationService.getUserType((String)session.get(WebConstants.CONSTANT_USER_NAME));
		if((((String)session.get(WebConstants.CONSTANT_USER_NAME)).equalsIgnoreCase(userId) || userRole.equals("T"))){
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
		createBankGuaranteeDto.setBgCreateType(temp.getBgCreateType());
		createBankGuaranteeDto.setBgDate(temp.getBgDate());
		createBankGuaranteeDto.setBgRuleCode(temp.getBgRuleCode());
		createBankGuaranteeDto.setBgRuleNarration(temp.getBgRuleNarration());
		createBankGuaranteeDto.setBgDetails(temp.getBgDetails());
		createBankGuaranteeDto.setSenderToReceiverInformation(temp.getSenderToReceiverInformation());
		createBankGuaranteeDto.setAdvisingBank(temp.getAdvisingBank());
		createBankGuaranteeDto.setIssuingBankCode(temp.getIssuingBankCode());
		createBankGuaranteeDto.setSeqNo(temp.getSeqNo());
		createBankGuaranteeDto.setSequenceofTotal(temp.getSequenceofTotal());
		createBankGuaranteeDto.setSequence(temp.getSequence());

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
	
	public String getObjectForBG()
	{
		try{
			String userId = (String)session.get(WebConstants.CONSTANT_USER_NAME);
			PendingAuthorizationService pendingAuthorizationService = (PendingAuthorizationService)ApplicationContextProvider.getBean("pendingAuthorizationService");
			CreateBankGuaranteeService createBankGuaranteeService =  (CreateBankGuaranteeService)  ApplicationContextProvider.getBean("createBankGuaranteeService");
			EventLogging eventLogging = (EventLogging)ApplicationContextProvider.getBean("eventLogging");
			if(getSaveAction().equalsIgnoreCase("Approve"))
			{			
				createBankGuaranteeService.createBankGuarantee(createBankGuaranteeDto, userId);
				pendingAuthorizationService.changeStatus(getSaveAction(), getHiddenTxnRef());
				 eventLogging.doEventLogging(userId, "Create Bank Guarantee", ConstantUtil.EVENTID_BANK_GUARANTEE+ConstantUtil.EVENTID_APPROVE, ConstantUtil.EVENTLOGGINGCOMMENTAPPROVAL,createBankGuaranteeDto.getBgNumber(),createBankGuaranteeDto.getMsgRef());
			}
			else
			{
				pendingAuthorizationService.changeStatus(getSaveAction(), getHiddenTxnRef());
				eventLogging.doEventLogging(userId, "Create Bank Guarantee", ConstantUtil.EVENTID_BANK_GUARANTEE+ConstantUtil.EVENTID_REJECT, ConstantUtil.EVENTLOGGINGCOMMENTREJECT,createBankGuaranteeDto.getBgNumber(),createBankGuaranteeDto.getMsgRef());
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
	public String reset() {
		try{
		createBankGuaranteeDto.setBgCreateType("");
		createBankGuaranteeDto.setBgDate(null);
		createBankGuaranteeDto.setBgDetails("");
		createBankGuaranteeDto.setBgNumber("");
		createBankGuaranteeDto.setBgRuleCode("");
		createBankGuaranteeDto.setBgRuleNarration("");
		createBankGuaranteeDto.setSenderToReceiverInformation("");
		createBankGuaranteeDto.setIssuingBankCode("");
		createBankGuaranteeDto.setAdvisingBank("");
		createBankGuaranteeDto.setSequenceofTotal("");
		createBankGuaranteeDto.setSequence("");
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
		if(createBankGuaranteeDto.getBgCreateType().equalsIgnoreCase("ISSUE")){
			if(createBankGuaranteeDto.getBgNumber()==null ||createBankGuaranteeDto.getBgNumber().isEmpty()){
				addFieldError("bgNumber", "Transaction Reference Number(20) cannot be left empty.");
			}
		}
		if(createBankGuaranteeDto.getBgRuleCode().equalsIgnoreCase("OTHR")){
			if(createBankGuaranteeDto.getBgRuleNarration()==null||createBankGuaranteeDto.getBgRuleNarration().isEmpty()){
				addFieldError("bgRuleNarration", "bgRuleNarration(40C) cannot be left empty.");
			}
		}

		
		if(createBankGuaranteeDto.getBgNumber().startsWith("/")){
			 addFieldError("lcNumber", "Lc Number Must Not start with /");
		}else if(createBankGuaranteeDto.getBgNumber().endsWith("/")){
			addFieldError("lcNumber", "Lc Number must not End with /");;
		}else if(createBankGuaranteeDto.getBgNumber().contains("//")){
			addFieldError("lcNumber", "Lc Number must not contain two consecutive slashes '//'");
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

	@SkipValidation
	public String viewCreateBankGuantee()
	{
		try{
			
		String msgRef = (String) session.get("messageIndex");
		CreateBankGuaranteeService createBankGuaranteeService =  (CreateBankGuaranteeService)ApplicationContextProvider.getBean("createBankGuaranteeService");
		CreateBankGuaranteeDto createBankGuaranteeDto = createBankGuaranteeService.getBankGuarantee(msgRef);
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
		createBankGuaranteeDto.setSequence(temp.getSequence());
		createBankGuaranteeDto.setSequenceofTotal(temp.getSequenceofTotal());
		createBankGuaranteeDto.setBgNumber(temp.getBgNumber());
		createBankGuaranteeDto.setBgCreateType(temp.getBgCreateType());
		createBankGuaranteeDto.setBgDate(temp.getBgDate());
		createBankGuaranteeDto.setBgRuleCode(temp.getBgRuleCode());
		createBankGuaranteeDto.setBgRuleNarration(temp.getBgRuleNarration());
		createBankGuaranteeDto.setBgDetails(temp.getBgDetails());
		createBankGuaranteeDto.setSenderToReceiverInformation(temp.getSenderToReceiverInformation());
		createBankGuaranteeDto.setAdvisingBank(temp.getAdvisingBank());
		createBankGuaranteeDto.setIssuingBankCode(temp.getIssuingBankCode());
		createBankGuaranteeDto.setRepair(temp.getRepair());
		createBankGuaranteeDto.setMsgRef(temp.getMsgRef());
		createBankGuaranteeDto.setSeqNo(temp.getSeqNo());
		createBankGuaranteeDto.setMsgHost(temp.getMsgHost());
        session.put("ScreenData", createBankGuaranteeDto);
	}
	
public String saveTemplate() {
		
		try {			
			String tempRefKey="";
			PendingAuthorizationService pendingAuthorizationService = (PendingAuthorizationService)ApplicationContextProvider.getBean("pendingAuthorizationService");
			String userId = (String)session.get(WebConstants.CONSTANT_USER_NAME);
			
			tempRefKey = pendingAuthorizationService.getTempRef(serializeObject(),"760","XXX",tempName,"Create Bank Guarantee(MT-760)",userId);

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
public String viewTemplateCreateBankGuantee()
{
	try{
		
	String msgRef = (String) session.get("messageRef");
	System.out.println("messageRef is "+msgRef);
	CreateBankGuaranteeService createBankGuaranteeService =  (CreateBankGuaranteeService)ApplicationContextProvider.getBean("createBankGuaranteeService");
	CreateBankGuaranteeDto createBankGuaranteeDto = createBankGuaranteeService.getBankGuarantee(msgRef);
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
public String displayPrintPreviewCreateBankGuarantee()
{
	String tempScreenString =null;
	String userId =null;
	String msgRef = (String) session.get("messageIndex");
	String histMsgRef = (String) session.get("messageIndex");
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
		createBankGuaranteeDto.setBgCreateType(temp.getBgCreateType());
		createBankGuaranteeDto.setBgDate(temp.getBgDate());
		createBankGuaranteeDto.setBgRuleCode(temp.getBgRuleCode());
		createBankGuaranteeDto.setBgRuleNarration(temp.getBgRuleNarration());
		createBankGuaranteeDto.setBgDetails(temp.getBgDetails());
		createBankGuaranteeDto.setSenderToReceiverInformation(temp.getSenderToReceiverInformation());
		createBankGuaranteeDto.setAdvisingBank(temp.getAdvisingBank());
		createBankGuaranteeDto.setIssuingBankCode(temp.getIssuingBankCode());
		createBankGuaranteeDto.setSeqNo(temp.getSeqNo());
		setPrintPreviewTxnRef(getHiddenTxnRef());
	}
	else if(msgRef!=null && !msgRef.isEmpty())
	{
		viewCreateBankGuantee();
		return "success";
	}
	else 
	{
		createBankGuaranteeDto.setSequence(createBankGuaranteeDto.getSequence());
		createBankGuaranteeDto.setSequenceofTotal(createBankGuaranteeDto.getSequenceofTotal());
		createBankGuaranteeDto.setBgNumber(createBankGuaranteeDto.getBgNumber());
		createBankGuaranteeDto.setBgCreateType(createBankGuaranteeDto.getBgCreateType());
		createBankGuaranteeDto.setBgDate(createBankGuaranteeDto.getBgDate());
		createBankGuaranteeDto.setBgRuleCode(createBankGuaranteeDto.getBgRuleCode());
		createBankGuaranteeDto.setBgRuleNarration(createBankGuaranteeDto.getBgRuleNarration());
		createBankGuaranteeDto.setBgDetails(createBankGuaranteeDto.getBgDetails());
		createBankGuaranteeDto.setSenderToReceiverInformation(createBankGuaranteeDto.getSenderToReceiverInformation());
		createBankGuaranteeDto.setAdvisingBank(createBankGuaranteeDto.getAdvisingBank());
		createBankGuaranteeDto.setIssuingBankCode(createBankGuaranteeDto.getIssuingBankCode());
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
public String exportToExcelCreateBankGuarantee() throws Exception
{
	
	
try{
	displayPrintPreviewCreateBankGuarantee();
	String reportFile = "Create_BankGuarantee(MT-760)";
	SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyyHHmmss");
	String currentDateTime = sdf.format(new Date());
	String reportFileName = reportFile+"_"+currentDateTime+"".concat(".xls");
	setReportFile(reportFileName);
	System.out.println("reportFileName is "+reportFileName);
	ExportToExcelUtil exportToExcelUtil = new ExportToExcelUtil();
	HSSFWorkbook myWorkBook = exportToExcelUtil.generateExportToExcel(null, createBankGuaranteeDto, null, reportFile, 760, "XXX");
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
public String generatePDFCreateBankGuarantee()
{
	displayPrintPreviewCreateBankGuarantee();
	String reportName ="Create Bank Guarantee (MT-760)";
	Map<String,String> fieldNamesMap = new HashMap<String,String>();
	
	try{
		
		fieldNamesMap.put("label.MB.IssuingBranchIFSC", getText("label.MB.IssuingBranchIFSC"));
		fieldNamesMap.put("label.MB.Benefifsc", getText("label.MB.Benefifsc"));
		
		fieldNamesMap.put("label.760.ppSequenceofTotal", getText("label.760.ppSequenceofTotal"));
		fieldNamesMap.put("label.760.pptransactionReferenceNumber", getText("label.760.pptransactionReferenceNumber"));
		fieldNamesMap.put("label.760.ppFurtherIdentification", getText("label.760.ppFurtherIdentification"));
		fieldNamesMap.put("label.760.ppbgDate", getText("label.760.ppbgDate"));
		fieldNamesMap.put("label.760.ppbgRuleCode", getText("label.760.ppbgRuleCode"));
		fieldNamesMap.put("label.760.ppbgRuleNarration", getText("label.760.ppbgRuleNarration"));
		fieldNamesMap.put("label.760.ppbgDetails", getText("label.760.ppbgDetails"));
		fieldNamesMap.put("label.760.ppSendertoReceiverInformation", getText("label.760.ppSendertoReceiverInformation"));
		
		
		PaymentPDFGeneratationUtil paymentPDFGeneratationUtil = new PaymentPDFGeneratationUtil();
		paymentPDFGeneratationUtil.setServletRequest(servletRequest);
		paymentPDFGeneratationUtil.setReportName(reportName);
		
		paymentPDFGeneratationUtil.generatePaymentPDFReport(null, createBankGuaranteeDto,null,fieldNamesMap, 760, "XXX");
	}
	catch (Exception exception) {
		AuditServiceUtil.logException(exception,logger);
	}
	addActionError("Unable to Generate PDF file! Please try again");
	return "input";
}

}
