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
import com.logica.ngph.service.BankGuaranteeCoverService;
import com.logica.ngph.service.PaymentMessageService;
import com.logica.ngph.service.PendingAuthorizationService;
import com.logica.ngph.web.utils.ApplicationContextProvider;
import com.logica.ngph.web.utils.EventLogging;
import com.logica.ngph.web.utils.ExportToExcelUtil;
import com.logica.ngph.web.utils.PaymentPDFGeneratationUtil;
import com.logica.ngph.web.utils.WebConstants;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class AmdCoverBankGuaranteeAction extends ActionSupport implements
		ModelDriven<BankGuaranteeCoverDto>, SessionAware, ServletRequestAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(AmdCoverBankGuaranteeAction.class);
	private BankGuaranteeCoverDto bankGuaranteeCoverDto = new BankGuaranteeCoverDto();
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
	
	@SkipValidation
	public String AmdCoverBankGuarantee() {
		try {
		
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
	
	public String getAmdCoverBGApproval() {
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
				 txnKey = pendingAuthorizationService.getTranRef(serializeObject(),"Amend BG Cover(MT-767COVER)",userId);
				 eventLogging.doEventLogging(userId, "Amend BG Cover", ConstantUtil.EVENTID_AMEND_BG_COVER+ConstantUtil.EVENTID_SUBMIT, ConstantUtil.EVENTLOGGINGCOMMENTSUBMIT,bankGuaranteeCoverDto.getBgNumber(),bankGuaranteeCoverDto.getMsgRef());
			}
		   
		    if(StringUtils.isBlank(bankGuaranteeCoverDto.getRepair()) && StringUtils.isEmpty(bankGuaranteeCoverDto.getRepair())){
				
			}else{
				PaymentMessageService paymentMessageService =(PaymentMessageService)ApplicationContextProvider.getBean("paymentMessageService");
				paymentMessageService.changeMsgStatus2to99(bankGuaranteeCoverDto.getMsgRef());
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
	public String getAmdCoverBGAuthorization()
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
		BankGuaranteeCoverDto temp= getSerializedObject(tempScreenString);
		
		bankGuaranteeCoverDto.setBgNumber(temp.getBgNumber());
		bankGuaranteeCoverDto.setBgRelatedReference(temp.getBgRelatedReference());
		bankGuaranteeCoverDto.setBgFurtherIdentification(temp.getBgFurtherIdentification());
		bankGuaranteeCoverDto.setBgAmendmentDate(temp.getBgAmendmentDate());
		bankGuaranteeCoverDto.setBgNoofAmendments(temp.getBgNoofAmendments());
		bankGuaranteeCoverDto.setBgIssueDate(temp.getBgIssueDate());
		bankGuaranteeCoverDto.setBgAmedmentDetails(temp.getBgAmedmentDetails());
		bankGuaranteeCoverDto.setSenderToReciverInformation(temp.getSenderToReciverInformation());
		bankGuaranteeCoverDto.setIssuingBankCode(temp.getIssuingBankCode());
		bankGuaranteeCoverDto.setIssunigBankNameAndAddress(temp.getIssunigBankNameAndAddress());
		bankGuaranteeCoverDto.setBgApplicentNameAndDetails(temp.getBgApplicentNameAndDetails());
		bankGuaranteeCoverDto.setBeneficiaryNameAndDetails(temp.getBeneficiaryNameAndDetails());
		bankGuaranteeCoverDto.setBeneficiaryBankCode(temp.getBeneficiaryBankCode());
		bankGuaranteeCoverDto.setBeneficiaryBankNameAndAddress(temp.getBeneficiaryBankNameAndAddress());
		bankGuaranteeCoverDto.setStampDutyPaid(temp.getStampDutyPaid());
		bankGuaranteeCoverDto.setStampCertificateNumber(temp.getStampCertificateNumber());
		bankGuaranteeCoverDto.setStampDateAndTime(temp.getStampDateAndTime());
		bankGuaranteeCoverDto.setBgAmountPaid(temp.getBgAmountPaid());
		bankGuaranteeCoverDto.setBgStateCode(temp.getBgStateCode());
		bankGuaranteeCoverDto.setBgArticleNumber(temp.getBgArticleNumber());
		bankGuaranteeCoverDto.setBgPaymentDate(temp.getBgPaymentDate());
		bankGuaranteeCoverDto.setBgPaymentPlace(temp.getBgPaymentPlace());
		bankGuaranteeCoverDto.setBgHeldDematForm(temp.getBgHeldDematForm());
		bankGuaranteeCoverDto.setCustodianServiceProvider(temp.getCustodianServiceProvider());
		bankGuaranteeCoverDto.setDematAccNumber(temp.getDematAccNumber());
		bankGuaranteeCoverDto.setMsgRef(temp.getMsgRef());
		bankGuaranteeCoverDto.setRepair(temp.getRepair());
		bankGuaranteeCoverDto.setSeqNo(temp.getSeqNo());
		bankGuaranteeCoverDto.setMsgHost(temp.getMsgHost());
		bankGuaranteeCoverDto.setAdvisingBank(temp.getAdvisingBank());
		bankGuaranteeCoverDto.setBgIssuingBankCode(temp.getBgIssuingBankCode());
		
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
	
	public String getObjectForAmdCoverBG()
	{
		try{
			String userId = (String)session.get(WebConstants.CONSTANT_USER_NAME);
			PendingAuthorizationService pendingAuthorizationService = (PendingAuthorizationService)ApplicationContextProvider.getBean("pendingAuthorizationService");
			PaymentMessageService paymentMessageService =(PaymentMessageService)ApplicationContextProvider.getBean("paymentMessageService");
			EventLogging eventLogging = (EventLogging)ApplicationContextProvider.getBean("eventLogging");
			if(getSaveAction().equalsIgnoreCase("Approve")){			
				BankGuaranteeCoverService bankGuaranteeCoverService = (BankGuaranteeCoverService)ApplicationContextProvider.getBean("bankGuaranteeCoverService");
				bankGuaranteeCoverService.createAmendBankGuaranteeCover(bankGuaranteeCoverDto, userId);
				pendingAuthorizationService.changeStatus(getSaveAction(), getHiddenTxnRef());
				if(StringUtils.isBlank(bankGuaranteeCoverDto.getRepair()) && StringUtils.isEmpty(bankGuaranteeCoverDto.getRepair())){
					pendingAuthorizationService.changeStatus(getSaveAction(), getHiddenTxnRef());	
					eventLogging.doEventLogging(userId, "Amend BG Cover", ConstantUtil.EVENTID_AMEND_BG_COVER+ConstantUtil.EVENTID_APPROVE, ConstantUtil.EVENTLOGGINGCOMMENTAPPROVAL,bankGuaranteeCoverDto.getBgNumber(),bankGuaranteeCoverDto.getMsgRef());
				}
				else
				{
					paymentMessageService.changeMsgStatus2to99(bankGuaranteeCoverDto.getMsgRef());
					pendingAuthorizationService.changeStatus(getSaveAction(), getHiddenTxnRef());
				}
			}
			else{
				if(StringUtils.isBlank(bankGuaranteeCoverDto.getRepair()) && StringUtils.isEmpty(bankGuaranteeCoverDto.getRepair())){
					pendingAuthorizationService.changeStatus(getSaveAction(), getHiddenTxnRef());
					eventLogging.doEventLogging(userId, "Amend BG Cover", ConstantUtil.EVENTID_AMEND_BG_COVER+ConstantUtil.EVENTID_REJECT, ConstantUtil.EVENTLOGGINGCOMMENTREJECT,bankGuaranteeCoverDto.getBgNumber(),bankGuaranteeCoverDto.getMsgRef());
				}else{
					paymentMessageService.changeMsgStatus99to2(bankGuaranteeCoverDto.getMsgRef());
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
	public String reset() {
		try{
			bankGuaranteeCoverDto.setBgNumber("");
			bankGuaranteeCoverDto.setBgRelatedReference("");
			bankGuaranteeCoverDto.setBgFurtherIdentification("");
			bankGuaranteeCoverDto.setBgAmendmentDate(null);
			bankGuaranteeCoverDto.setBgNoofAmendments(null);
			bankGuaranteeCoverDto.setBgIssueDate(null);
			bankGuaranteeCoverDto.setBgAmedmentDetails("");
			bankGuaranteeCoverDto.setSenderToReciverInformation("");
			bankGuaranteeCoverDto.setIssuingBankCode("");
			bankGuaranteeCoverDto.setIssunigBankNameAndAddress("");
			bankGuaranteeCoverDto.setBgApplicentNameAndDetails("");
			bankGuaranteeCoverDto.setBeneficiaryNameAndDetails("");
			bankGuaranteeCoverDto.setBeneficiaryBankCode("");
			bankGuaranteeCoverDto.setBeneficiaryBankNameAndAddress("");
			bankGuaranteeCoverDto.setStampDutyPaid("");
			bankGuaranteeCoverDto.setStampCertificateNumber("");
			bankGuaranteeCoverDto.setStampDateAndTime(null);
			bankGuaranteeCoverDto.setBgAmountPaid(null);
			bankGuaranteeCoverDto.setBgStateCode("");
			bankGuaranteeCoverDto.setBgArticleNumber("");
			bankGuaranteeCoverDto.setBgPaymentDate(null);
			bankGuaranteeCoverDto.setBgPaymentPlace("");
			bankGuaranteeCoverDto.setBgHeldDematForm("");
			bankGuaranteeCoverDto.setCustodianServiceProvider("");
			bankGuaranteeCoverDto.setDematAccNumber("");
			bankGuaranteeCoverDto.setMsgRef("");
			bankGuaranteeCoverDto.setRepair("");
			bankGuaranteeCoverDto.setAdvisingBank("");
			bankGuaranteeCoverDto.setBgIssuingBankCode("");
			return "success";
		}catch (Exception e) {
			logger.error(e,e);
			return "input";
		}

	}
	
	private String serializeObject(){
		BankGuaranteeCoverDto bankGuaranteeCoverDTO = new BankGuaranteeCoverDto();
		try{
			String userId = (String)session.get(WebConstants.CONSTANT_USER_NAME);
			
			bankGuaranteeCoverDTO = bankGuaranteeCoverDto;
			String fileName ="serial_"+userId+".ser";
		FileOutputStream fos = new FileOutputStream(fileName);
        OutputStream buffer = new BufferedOutputStream( fos );
        ObjectOutputStream oos = new ObjectOutputStream(buffer);
        oos.writeObject(bankGuaranteeCoverDTO);
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
	
	private BankGuaranteeCoverDto getSerializedObject(String objectString){
		
		try{
			
			byte[] decoded = Base64.decode(objectString);
            
            FileOutputStream foss = new FileOutputStream("targetUserObject.ser");
            foss.write(decoded);
            foss.close();
            BankGuaranteeCoverDto testDTO = null;
            
            FileInputStream fiss = new FileInputStream("targetUserObject.ser");
            BufferedInputStream bufferee = new BufferedInputStream( fiss );
            ObjectInputStream oiss = new ObjectInputStream(bufferee);
            testDTO = (BankGuaranteeCoverDto)oiss.readObject();
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
		
		if(StringUtils.isNotBlank(bankGuaranteeCoverDto.getStampDutyPaid()) && StringUtils.isNotEmpty(bankGuaranteeCoverDto.getStampDutyPaid()))
		{
			if(bankGuaranteeCoverDto.getStampDutyPaid().equalsIgnoreCase("Y"))
			{
				if(//String.valueOf(bankGuaranteeCoverDto.getBgAmountPaid()).equalsIgnoreCase(null) && (String.valueOf(bankGuaranteeCoverDto.getBgAmountPaid())==null) && 
						StringUtils.isEmpty(bankGuaranteeCoverDto.getBgStateCode()) && StringUtils.isBlank(bankGuaranteeCoverDto.getBgStateCode()) && 
								StringUtils.isEmpty(bankGuaranteeCoverDto.getBgArticleNumber()) && StringUtils.isBlank(bankGuaranteeCoverDto.getBgArticleNumber()) &&
						 bankGuaranteeCoverDto.getBgPaymentDate()==null)
				{
					addFieldError("stampDutyPaid", "If Field value of 7040 is 'Y' then field 7043,7044,7045,7046 must be");
				}
			}
		}
		if(StringUtils.isBlank(bankGuaranteeCoverDto.getAdvisingBank()) && StringUtils.isEmpty(bankGuaranteeCoverDto.getAdvisingBank()))
		{
				addFieldError("advisingBank", "Receiver Bank IFSC is required");
		}
		
		if(StringUtils.isBlank(bankGuaranteeCoverDto.getBgIssuingBankCode()) && StringUtils.isEmpty(bankGuaranteeCoverDto.getBgIssuingBankCode()))
		{
			addFieldError("bgIssuingBankCode", "Issuing Branch IFSC(7031) is Mandatory");
		}
		
		if(StringUtils.isBlank(bankGuaranteeCoverDto.getBeneficiaryBankCode()) && StringUtils.isEmpty(bankGuaranteeCoverDto.getBeneficiaryBankCode()))
		{
			addFieldError("beneficiaryBankCode", "Beneficiary IFSC(7035) is Mandatory");
		}
		
	}
	
	public BankGuaranteeCoverDto getModel() {		
		return bankGuaranteeCoverDto;
	}

	public BankGuaranteeCoverDto getBankGuaranteeCoverDto() {
		return bankGuaranteeCoverDto;
	}

	public void setBankGuaranteeCoverDto(
			BankGuaranteeCoverDto bankGuaranteeCoverDto) {
		this.bankGuaranteeCoverDto = bankGuaranteeCoverDto;
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

	@SkipValidation
	public String displayAmdCoverBG()
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
	private void setALLValueTODTO(BankGuaranteeCoverDto obj)
	{
		
		BankGuaranteeCoverDto  bankGuaranteeCoverDTO =  obj;
		bankGuaranteeCoverDto.setBgNumber(bankGuaranteeCoverDTO.getBgNumber());
		bankGuaranteeCoverDto.setBgRelatedReference(bankGuaranteeCoverDTO.getBgRelatedReference());
		bankGuaranteeCoverDto.setBgFurtherIdentification(bankGuaranteeCoverDTO.getBgFurtherIdentification());
		bankGuaranteeCoverDto.setBgAmendmentDate(bankGuaranteeCoverDTO.getBgAmendmentDate());
		bankGuaranteeCoverDto.setBgNoofAmendments(bankGuaranteeCoverDTO.getBgNoofAmendments());
		bankGuaranteeCoverDto.setBgIssueDate(bankGuaranteeCoverDTO.getBgIssueDate());
		bankGuaranteeCoverDto.setIssuingBankCode(bankGuaranteeCoverDTO.getIssuingBankCode());
		bankGuaranteeCoverDto.setBgAmedmentDetails(bankGuaranteeCoverDTO.getBgAmedmentDetails());
		bankGuaranteeCoverDto.setIssunigBankNameAndAddress(bankGuaranteeCoverDTO.getIssunigBankNameAndAddress());
		bankGuaranteeCoverDto.setBgApplicentNameAndDetails(bankGuaranteeCoverDTO.getBgApplicentNameAndDetails());
		bankGuaranteeCoverDto.setBeneficiaryBankCode(bankGuaranteeCoverDTO.getBeneficiaryBankCode());
		bankGuaranteeCoverDto.setBeneficiaryBankNameAndAddress(bankGuaranteeCoverDTO.getBeneficiaryBankNameAndAddress());
		bankGuaranteeCoverDto.setBeneficiaryNameAndDetails(bankGuaranteeCoverDTO.getBeneficiaryNameAndDetails());
		bankGuaranteeCoverDto.setSenderToReciverInformation(bankGuaranteeCoverDTO.getSenderToReciverInformation());
		bankGuaranteeCoverDto.setStampDutyPaid(bankGuaranteeCoverDTO.getStampDutyPaid());
		bankGuaranteeCoverDto.setStampCertificateNumber(bankGuaranteeCoverDTO.getStampCertificateNumber());
		bankGuaranteeCoverDto.setStampDateAndTime(bankGuaranteeCoverDTO.getStampDateAndTime());
		bankGuaranteeCoverDto.setBgAmountPaid(bankGuaranteeCoverDTO.getBgAmountPaid());
		bankGuaranteeCoverDto.setBgStateCode(bankGuaranteeCoverDTO.getBgStateCode());
		bankGuaranteeCoverDto.setBgArticleNumber(bankGuaranteeCoverDTO.getBgArticleNumber());
		bankGuaranteeCoverDto.setBgPaymentDate(bankGuaranteeCoverDTO.getBgPaymentDate());
		bankGuaranteeCoverDto.setBgPaymentPlace(bankGuaranteeCoverDTO.getBgPaymentPlace());
		bankGuaranteeCoverDto.setBgHeldDematForm(bankGuaranteeCoverDTO.getBgHeldDematForm());
		bankGuaranteeCoverDto.setCustodianServiceProvider(bankGuaranteeCoverDTO.getCustodianServiceProvider());
		bankGuaranteeCoverDto.setDematAccNumber(bankGuaranteeCoverDTO.getDematAccNumber());
		bankGuaranteeCoverDto.setMsgRef(bankGuaranteeCoverDTO.getMsgRef());
		bankGuaranteeCoverDto.setRepair(bankGuaranteeCoverDTO.getRepair());
		bankGuaranteeCoverDto.setSeqNo(bankGuaranteeCoverDTO.getSeqNo());
		bankGuaranteeCoverDto.setMsgHost(bankGuaranteeCoverDTO.getMsgHost());
		bankGuaranteeCoverDto.setAdvisingBank(bankGuaranteeCoverDTO.getAdvisingBank());
		bankGuaranteeCoverDto.setBgIssuingBankCode(bankGuaranteeCoverDTO.getBgIssuingBankCode());
	}
	
	@SkipValidation
	public String viewCreateAmendBGCover()
	{
		try{
			
		String msgRef = (String) session.get("messageIndex");
		BankGuaranteeCoverService bankGuaranteeCoverService =  (BankGuaranteeCoverService)ApplicationContextProvider.getBean("bankGuaranteeCoverService");
		BankGuaranteeCoverDto bankGuaranteeCoverDto = bankGuaranteeCoverService.getCreateAmendBGCover(msgRef);
		if(bankGuaranteeCoverDto.getBgNumber()!=null && StringUtils.isNotBlank(bankGuaranteeCoverDto.getBgNumber()) && StringUtils.isNotEmpty(bankGuaranteeCoverDto.getBgNumber())){
			bankGuaranteeCoverDto.setRepair(ConstantUtil.REPAIR);
			setALLValueTODTO(bankGuaranteeCoverDto);
			
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
	
public String saveTemplate() {
		
		try {			
			String tempRefKey="";
			PendingAuthorizationService pendingAuthorizationService = (PendingAuthorizationService)ApplicationContextProvider.getBean("pendingAuthorizationService");
			String userId = (String)session.get(WebConstants.CONSTANT_USER_NAME);
			
			tempRefKey = pendingAuthorizationService.getTempRef(serializeObject(),"767","COV",tempName,"Amend BG Cover(MT-767COVER)",userId);

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
public String viewTempCreateAmendBGCover()
{
	try
	{
		String msgRef = (String) session.get("messageRef");
		BankGuaranteeCoverService bankGuaranteeCoverService =  (BankGuaranteeCoverService)ApplicationContextProvider.getBean("bankGuaranteeCoverService");
		BankGuaranteeCoverDto bankGuaranteeCoverDto = bankGuaranteeCoverService.getCreateAmendBGCover(msgRef);
		if(bankGuaranteeCoverDto.getBgNumber()!=null && StringUtils.isNotBlank(bankGuaranteeCoverDto.getBgNumber()) && StringUtils.isNotEmpty(bankGuaranteeCoverDto.getBgNumber()))
		{
			setALLValueTODTO(bankGuaranteeCoverDto);
			return "success";
		}
		else
		{
			addFieldError("paymentMessageType",ConstantUtil.ERRORMESSAGE);
			return "input";
		}
	}
	catch(Exception exception)
	{
		exception.printStackTrace();
		addFieldError("paymentMessageType",ConstantUtil.ERRORMESSAGE);
		return "input";
	
	}
}

@SkipValidation
public String displayPrintPreviewAmendBGCover()
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
		BankGuaranteeCoverDto temp= getSerializedObject(tempScreenString);
		
		bankGuaranteeCoverDto.setBgNumber(temp.getBgNumber());
		bankGuaranteeCoverDto.setBgRelatedReference(temp.getBgRelatedReference());
		bankGuaranteeCoverDto.setBgFurtherIdentification(temp.getBgFurtherIdentification());
		bankGuaranteeCoverDto.setBgAmendmentDate(temp.getBgAmendmentDate());
		bankGuaranteeCoverDto.setBgNoofAmendments(temp.getBgNoofAmendments());
		bankGuaranteeCoverDto.setBgIssueDate(temp.getBgIssueDate());
		bankGuaranteeCoverDto.setBgAmedmentDetails(temp.getBgAmedmentDetails());
		bankGuaranteeCoverDto.setSenderToReciverInformation(temp.getSenderToReciverInformation());
		bankGuaranteeCoverDto.setIssuingBankCode(temp.getIssuingBankCode());
		bankGuaranteeCoverDto.setIssunigBankNameAndAddress(temp.getIssunigBankNameAndAddress());
		bankGuaranteeCoverDto.setBgApplicentNameAndDetails(temp.getBgApplicentNameAndDetails());
		bankGuaranteeCoverDto.setBeneficiaryNameAndDetails(temp.getBeneficiaryNameAndDetails());
		bankGuaranteeCoverDto.setBeneficiaryBankCode(temp.getBeneficiaryBankCode());
		bankGuaranteeCoverDto.setBeneficiaryBankNameAndAddress(temp.getBeneficiaryBankNameAndAddress());
		bankGuaranteeCoverDto.setStampDutyPaid(temp.getStampDutyPaid());
		bankGuaranteeCoverDto.setStampCertificateNumber(temp.getStampCertificateNumber());
		bankGuaranteeCoverDto.setStampDateAndTime(temp.getStampDateAndTime());
		bankGuaranteeCoverDto.setBgAmountPaid(temp.getBgAmountPaid());
		bankGuaranteeCoverDto.setBgStateCode(temp.getBgStateCode());
		bankGuaranteeCoverDto.setBgArticleNumber(temp.getBgArticleNumber());
		bankGuaranteeCoverDto.setBgPaymentDate(temp.getBgPaymentDate());
		bankGuaranteeCoverDto.setBgPaymentPlace(temp.getBgPaymentPlace());
		bankGuaranteeCoverDto.setBgHeldDematForm(temp.getBgHeldDematForm());
		bankGuaranteeCoverDto.setCustodianServiceProvider(temp.getCustodianServiceProvider());
		bankGuaranteeCoverDto.setDematAccNumber(temp.getDematAccNumber());
		bankGuaranteeCoverDto.setMsgRef(temp.getMsgRef());
		bankGuaranteeCoverDto.setRepair(temp.getRepair());
		bankGuaranteeCoverDto.setSeqNo(temp.getSeqNo());
		bankGuaranteeCoverDto.setMsgHost(temp.getMsgHost());
		bankGuaranteeCoverDto.setAdvisingBank(temp.getAdvisingBank());
		bankGuaranteeCoverDto.setBgIssuingBankCode(temp.getBgIssuingBankCode());
		setPrintPreviewTxnRef(getHiddenTxnRef());
	}
	else if(msgRef!=null && !msgRef.isEmpty())
	{
		viewCreateAmendBGCover();
		return "success";
	}
	else
	{
		bankGuaranteeCoverDto.setBgNumber(bankGuaranteeCoverDto.getBgNumber());
		bankGuaranteeCoverDto.setBgRelatedReference(bankGuaranteeCoverDto.getBgRelatedReference());
		bankGuaranteeCoverDto.setBgFurtherIdentification(bankGuaranteeCoverDto.getBgFurtherIdentification());
		bankGuaranteeCoverDto.setBgAmendmentDate(bankGuaranteeCoverDto.getBgAmendmentDate());
		bankGuaranteeCoverDto.setBgNoofAmendments(bankGuaranteeCoverDto.getBgNoofAmendments());
		bankGuaranteeCoverDto.setBgIssueDate(bankGuaranteeCoverDto.getBgIssueDate());
		bankGuaranteeCoverDto.setBgAmedmentDetails(bankGuaranteeCoverDto.getBgAmedmentDetails());
		bankGuaranteeCoverDto.setSenderToReciverInformation(bankGuaranteeCoverDto.getSenderToReciverInformation());
		bankGuaranteeCoverDto.setIssuingBankCode(bankGuaranteeCoverDto.getIssuingBankCode());
		bankGuaranteeCoverDto.setIssunigBankNameAndAddress(bankGuaranteeCoverDto.getIssunigBankNameAndAddress());
		bankGuaranteeCoverDto.setBgApplicentNameAndDetails(bankGuaranteeCoverDto.getBgApplicentNameAndDetails());
		bankGuaranteeCoverDto.setBeneficiaryNameAndDetails(bankGuaranteeCoverDto.getBeneficiaryNameAndDetails());
		bankGuaranteeCoverDto.setBeneficiaryBankCode(bankGuaranteeCoverDto.getBeneficiaryBankCode());
		bankGuaranteeCoverDto.setBeneficiaryBankNameAndAddress(bankGuaranteeCoverDto.getBeneficiaryBankNameAndAddress());
		bankGuaranteeCoverDto.setStampDutyPaid(bankGuaranteeCoverDto.getStampDutyPaid());
		bankGuaranteeCoverDto.setStampCertificateNumber(bankGuaranteeCoverDto.getStampCertificateNumber());
		bankGuaranteeCoverDto.setStampDateAndTime(bankGuaranteeCoverDto.getStampDateAndTime());
		bankGuaranteeCoverDto.setBgAmountPaid(bankGuaranteeCoverDto.getBgAmountPaid());
		bankGuaranteeCoverDto.setBgStateCode(bankGuaranteeCoverDto.getBgStateCode());
		bankGuaranteeCoverDto.setBgArticleNumber(bankGuaranteeCoverDto.getBgArticleNumber());
		bankGuaranteeCoverDto.setBgPaymentDate(bankGuaranteeCoverDto.getBgPaymentDate());
		bankGuaranteeCoverDto.setBgPaymentPlace(bankGuaranteeCoverDto.getBgPaymentPlace());
		bankGuaranteeCoverDto.setBgHeldDematForm(bankGuaranteeCoverDto.getBgHeldDematForm());
		bankGuaranteeCoverDto.setCustodianServiceProvider(bankGuaranteeCoverDto.getCustodianServiceProvider());
		bankGuaranteeCoverDto.setDematAccNumber(bankGuaranteeCoverDto.getDematAccNumber());
		bankGuaranteeCoverDto.setMsgRef(bankGuaranteeCoverDto.getMsgRef());
		bankGuaranteeCoverDto.setRepair(bankGuaranteeCoverDto.getRepair());
		bankGuaranteeCoverDto.setSeqNo(bankGuaranteeCoverDto.getSeqNo());
		bankGuaranteeCoverDto.setMsgHost(bankGuaranteeCoverDto.getMsgHost());
		bankGuaranteeCoverDto.setAdvisingBank(bankGuaranteeCoverDto.getAdvisingBank());
		bankGuaranteeCoverDto.setBgIssuingBankCode(bankGuaranteeCoverDto.getBgIssuingBankCode());
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
public String exportToExcelAmendBGCov() throws Exception
{		
try{
	displayPrintPreviewAmendBGCover();
	String reportFile = "Create_Amend_BG_Cover_(MT-767 COVER)";
	SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyyHHmmss");
	String currentDateTime = sdf.format(new Date());
	String reportFileName = reportFile+"_"+currentDateTime+"".concat(".xls");
	setReportFile(reportFileName);
	System.out.println("reportFileName is "+reportFileName);
	ExportToExcelUtil exportToExcelUtil = new ExportToExcelUtil();
	HSSFWorkbook myWorkBook = exportToExcelUtil.generateExportToExcel(null, null, bankGuaranteeCoverDto, reportFile, 767, "COV");
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
public String generatePDFAmendBGCover()
{
	displayPrintPreviewAmendBGCover();
	String reportName ="Create Amend BG Cover (MT-767 COVER)";
	Map<String,String> fieldNamesMap = new HashMap<String,String>();
	
	try{
		
		fieldNamesMap.put("label.MB.IssuingBranchIFSC", getText("label.MB.IssuingBranchIFSC"));
		fieldNamesMap.put("label.MB.Benefifsc", getText("label.MB.Benefifsc"));
		
		fieldNamesMap.put("label.767COV.pptrxReferenceNo", getText("label.767COV.pptrxReferenceNo"));
		fieldNamesMap.put("label.767COV.ppbgrelatedReference", getText("label.767COV.ppbgrelatedReference"));
		fieldNamesMap.put("label.767COV.ppFurtherIdentification", getText("label.767COV.ppFurtherIdentification"));
		fieldNamesMap.put("label.767COV.ppAmendmentDate", getText("label.767COV.ppAmendmentDate"));
		fieldNamesMap.put("label.767COV.ppNumberofAmendment", getText("label.767COV.ppNumberofAmendment"));
		fieldNamesMap.put("label.767COV.ppDateofAmendment", getText("label.767COV.ppDateofAmendment"));
		fieldNamesMap.put("label.767COV.ppbgDetails", getText("label.767COV.ppbgDetails"));
		fieldNamesMap.put("label.767COV.ppsenderToReceiverInformation", getText("label.767COV.ppsenderToReceiverInformation"));
		fieldNamesMap.put("label.767COV.ppissuingBranchIFSC", getText("label.767COV.ppissuingBranchIFSC"));
		fieldNamesMap.put("label.767COV.ppissuingBankNameandAddress", getText("label.767COV.ppissuingBankNameandAddress"));
		fieldNamesMap.put("label.767COV.ppapplicantNameAddress", getText("label.767COV.ppapplicantNameAddress"));
		fieldNamesMap.put("label.767COV.ppbenefibrname", getText("label.767COV.ppbenefibrname"));
		fieldNamesMap.put("label.767COV.ppbenefifsc", getText("label.767COV.ppbenefifsc"));
		fieldNamesMap.put("label.767COV.ppbeneficiaryBranchAddress", getText("label.767COV.ppbeneficiaryBranchAddress"));
		fieldNamesMap.put("label.767COV.ppelectronicallyPaid", getText("label.767COV.ppelectronicallyPaid"));
		fieldNamesMap.put("label.767COV.ppeStampCertificateNumber", getText("label.767COV.ppeStampCertificateNumber"));
		fieldNamesMap.put("label.767COV.ppeStamdateTime", getText("label.767COV.ppeStamdateTime"));
		fieldNamesMap.put("label.767COV.ppamountpaid", getText("label.767COV.ppamountpaid"));
		fieldNamesMap.put("label.767COV.ppstatecode", getText("label.767COV.ppstatecode"));
		fieldNamesMap.put("label.767COV.pparticleno", getText("label.767COV.pparticleno"));
		fieldNamesMap.put("label.767COV.ppdatetofpayment", getText("label.767COV.ppdatetofpayment"));
		fieldNamesMap.put("label.767COV.ppplaceofpay", getText("label.767COV.ppplaceofpay"));
		fieldNamesMap.put("label.767COV.ppebginDematForm", getText("label.767COV.ppebginDematForm"));
		fieldNamesMap.put("label.767COV.ppCustodianServiceProvider", getText("label.767COV.ppCustodianServiceProvider"));
		fieldNamesMap.put("label.767COV.ppdemataccno", getText("label.767COV.ppdemataccno"));
		
		PaymentPDFGeneratationUtil paymentPDFGeneratationUtil = new PaymentPDFGeneratationUtil();
		paymentPDFGeneratationUtil.setServletRequest(servletRequest);
		paymentPDFGeneratationUtil.setReportName(reportName);
		paymentPDFGeneratationUtil.generatePaymentPDFReport(null, null, bankGuaranteeCoverDto,fieldNamesMap, 767, "COV");
		
	}
	catch (Exception exception) {
		AuditServiceUtil.logException(exception,logger);
	}
	addActionError("Unable to Generate PDF file! Please try again");
	return "input";
}

}
