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
import com.logica.ngph.dtos.BankGuaranteeCoverDto;
import com.logica.ngph.dtos.CreateBankGuaranteeDto;
import com.logica.ngph.service.AuthorizationSchemaMaitenanceService;
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

public class CoverBankGuaranteeAction extends ActionSupport implements
		ModelDriven<BankGuaranteeCoverDto>, SessionAware, ServletRequestAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(CoverBankGuaranteeAction.class);
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
		this.session.put("currCodeDropDown", currCodeDropDown);
	}

	
	@SkipValidation
	public String CoverBankGuarantee() {
		try {
			
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
	
	public String getBGCoverApproval() {
		try {			
			String txnKey="";
			EventLogging eventLogging = (EventLogging)ApplicationContextProvider.getBean("eventLogging");
			PendingAuthorizationService pendingAuthorizationService = (PendingAuthorizationService)ApplicationContextProvider.getBean("pendingAuthorizationService");
			String userId = (String)session.get(WebConstants.CONSTANT_USER_NAME);
			if(!getHiddenTxnRef().isEmpty())
			{
				pendingAuthorizationService.updateRejectStatusToPending(getHiddenTxnRef());
			}else
			{
				txnKey = pendingAuthorizationService.getTranRef(serializeObject(),"Create Bank Guarantee Cover(MT-760COVER)",userId);
				
				pendingAuthorizationService.delimitedStringValue(txnKey, 1+"", "lcCurrency"+"~"+bankGuaranteeCoverDto.getCurrency());
				 eventLogging.doEventLogging(userId, "Create Bank Guarantee Cover", ConstantUtil.EVENTID_BANK_GUARANTEE_COVER+ConstantUtil.EVENTID_SUBMIT, ConstantUtil.EVENTLOGGINGCOMMENTSUBMIT,bankGuaranteeCoverDto.getBgNumber(),bankGuaranteeCoverDto.getMsgRef());
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
	
	private String serializeObject(){
		BankGuaranteeCoverDto bankGuaranteeCoverDTo = new BankGuaranteeCoverDto();
		try{
			String userId = (String)session.get(WebConstants.CONSTANT_USER_NAME);
			
			bankGuaranteeCoverDTo = bankGuaranteeCoverDto;
			String fileName ="serial_"+userId+".ser";
		FileOutputStream fos = new FileOutputStream(fileName);
        OutputStream buffer = new BufferedOutputStream( fos );
        ObjectOutputStream oos = new ObjectOutputStream(buffer);
        oos.writeObject(bankGuaranteeCoverDto);
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
	public String getBGCoverAuthorization()
	{
		String tempScreenString =null;
		PendingAuthorizationService pendingAuthorizationService = (PendingAuthorizationService)ApplicationContextProvider.getBean("pendingAuthorizationService");
	try{
		if(getTxnRef()!=null)
		{
		setCheckForSubmit("Display_Approve_Reject");
		String userId = pendingAuthorizationService.getCreatedUser(getTxnRef());
		String userRole = pendingAuthorizationService.getUserType((String)session.get(WebConstants.CONSTANT_USER_NAME));
		if((((String)session.get(WebConstants.CONSTANT_USER_NAME)).equals(userId) || userRole.equals("T")))
			{
				setValidUserToApprove(false);
			} else 
			{
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
		bankGuaranteeCoverDto.setBgFormNumber(temp.getBgFormNumber());
		bankGuaranteeCoverDto.setBgType(temp.getBgType());
		bankGuaranteeCoverDto.setBgAmount(temp.getBgAmount());
		bankGuaranteeCoverDto.setBgFromDate(temp.getBgFromDate());
		bankGuaranteeCoverDto.setBgToDate(temp.getBgToDate());
		bankGuaranteeCoverDto.setBgEffectiveDate(temp.getBgEffectiveDate());
		bankGuaranteeCoverDto.setBgLodgementEndDate(temp.getBgLodgementEndDate());
		bankGuaranteeCoverDto.setBgLodgementPalce(temp.getBgLodgementPalce());
		bankGuaranteeCoverDto.setIssuingBankCode(temp.getIssuingBankCode());
		bankGuaranteeCoverDto.setIssunigBankNameAndAddress(temp.getIssunigBankNameAndAddress());
		bankGuaranteeCoverDto.setBgApplicentNameAndDetails(temp.getBgApplicentNameAndDetails());
		bankGuaranteeCoverDto.setBeneficiaryNameAndDetails(temp.getBeneficiaryNameAndDetails());
		bankGuaranteeCoverDto.setBeneficiaryBankCode(temp.getBeneficiaryBankCode());
		bankGuaranteeCoverDto.setBeneficiaryBankNameAndAddress(temp.getBeneficiaryBankNameAndAddress());
		bankGuaranteeCoverDto.setSenderToReciverInformation(temp.getSenderToReciverInformation());
		bankGuaranteeCoverDto.setBgPurpose(temp.getBgPurpose());
		bankGuaranteeCoverDto.setDescriptionOfUnderlinedContract(temp.getDescriptionOfUnderlinedContract());
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
		if(getTxnRef()!=null)
		{
			List multiCurrCodeList = pendingAuthorizationService.getMulScreenData(getTxnRef());
			
			for(int i=0;i<multiCurrCodeList.size();i++)
			{
				Clob tempCurrCodeList = (Clob)multiCurrCodeList.get(i);
				String data[]= tempCurrCodeList.getSubString(1, (int) tempCurrCodeList.length()).toString().split("~");
				if(data[0].toString().trim().equals("currency")  && !data[0].toString().trim().equals(""))
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
				if(data[0].toString().trim().equals("currency")  && !data[0].toString().trim().equals(""))
				{
					currCodeDropDown.add(data[1].toString());
				}
			}
		}
		bankGuaranteeCoverDto.setCurrency(temp.getCurrency());
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
	
	public String getObjectForBGCover()
	{
		try{
			String userId = (String)session.get(WebConstants.CONSTANT_USER_NAME);
			PendingAuthorizationService pendingAuthorizationService = (PendingAuthorizationService)ApplicationContextProvider.getBean("pendingAuthorizationService");
			PaymentMessageService paymentMessageService =(PaymentMessageService)ApplicationContextProvider.getBean("paymentMessageService");
			EventLogging eventLogging = (EventLogging)ApplicationContextProvider.getBean("eventLogging");
			if(getSaveAction().equalsIgnoreCase("Approve")){			
				BankGuaranteeCoverService bankGuaranteeCoverService = (BankGuaranteeCoverService)ApplicationContextProvider.getBean("bankGuaranteeCoverService");
				bankGuaranteeCoverService.createBankGuaranteeCover(bankGuaranteeCoverDto, userId);
				pendingAuthorizationService.changeStatus(getSaveAction(), getHiddenTxnRef());
				if(StringUtils.isBlank(bankGuaranteeCoverDto.getRepair()) && StringUtils.isEmpty(bankGuaranteeCoverDto.getRepair())){
					pendingAuthorizationService.changeStatus(getSaveAction(), getHiddenTxnRef());	
					 eventLogging.doEventLogging(userId, "Create Bank Guarantee Cover", ConstantUtil.EVENTID_BANK_GUARANTEE_COVER+ConstantUtil.EVENTID_APPROVE, ConstantUtil.EVENTLOGGINGCOMMENTAPPROVAL,bankGuaranteeCoverDto.getBgNumber(),bankGuaranteeCoverDto.getMsgRef());
				}
				else
				{
					paymentMessageService.changeMsgStatus2to99(bankGuaranteeCoverDto.getMsgRef());
				}
			}
			else{
				if(StringUtils.isBlank(bankGuaranteeCoverDto.getRepair()) && StringUtils.isEmpty(bankGuaranteeCoverDto.getRepair())){
					pendingAuthorizationService.changeStatus(getSaveAction(), getHiddenTxnRef());
					eventLogging.doEventLogging(userId, "Create Bank Guarantee Cover", ConstantUtil.EVENTID_BANK_GUARANTEE_COVER+ConstantUtil.EVENTID_REJECT, ConstantUtil.EVENTLOGGINGCOMMENTREJECT,bankGuaranteeCoverDto.getBgNumber(),bankGuaranteeCoverDto.getMsgRef());
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
	
	
	public BankGuaranteeCoverDto getModel() {		
		return bankGuaranteeCoverDto;
	}

	public BankGuaranteeCoverDto getBankGuaranteeCoverDto() {
		return bankGuaranteeCoverDto;
	}

	public void setCreateBankGuaranteeDto(
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
	public String viewCreateBankGuanteeCover()
	{
		try{
			
		String msgRef = (String) session.get("messageIndex");
		BankGuaranteeCoverService bankGuaranteeCoverService =  (BankGuaranteeCoverService)ApplicationContextProvider.getBean("bankGuaranteeCoverService");
		BankGuaranteeCoverDto bankGuaranteeCoverDto = bankGuaranteeCoverService.getBankGuaranteeCover(msgRef);
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
	@SkipValidation
	public String displaycovBG()
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
	
	private void setALLValueTODTO(BankGuaranteeCoverDto obj)
	{
		BankGuaranteeCoverDto  bankGuaranteeCoverDTO =  obj;
		bankGuaranteeCoverDto.setBgNumber(bankGuaranteeCoverDTO.getBgNumber());
		bankGuaranteeCoverDto.setBgFormNumber(bankGuaranteeCoverDTO.getBgFormNumber());
		bankGuaranteeCoverDto.setBgType(bankGuaranteeCoverDTO.getBgType());
		bankGuaranteeCoverDto.setCurrency(bankGuaranteeCoverDTO.getCurrency());
		bankGuaranteeCoverDto.setBgAmount(bankGuaranteeCoverDTO.getBgAmount());
		bankGuaranteeCoverDto.setBgFromDate(bankGuaranteeCoverDTO.getBgFromDate());
		bankGuaranteeCoverDto.setBgToDate(bankGuaranteeCoverDTO.getBgToDate());
		bankGuaranteeCoverDto.setBgEffectiveDate(bankGuaranteeCoverDTO.getBgEffectiveDate());
		bankGuaranteeCoverDto.setBgLodgementEndDate(bankGuaranteeCoverDTO.getBgLodgementEndDate());
		bankGuaranteeCoverDto.setBgLodgementPalce(bankGuaranteeCoverDTO.getBgLodgementPalce());
		bankGuaranteeCoverDto.setIssuingBankCode(bankGuaranteeCoverDTO.getIssuingBankCode());
		bankGuaranteeCoverDto.setIssunigBankNameAndAddress(bankGuaranteeCoverDTO.getIssunigBankNameAndAddress());
		bankGuaranteeCoverDto.setBgApplicentNameAndDetails(bankGuaranteeCoverDTO.getBgApplicentNameAndDetails());
		bankGuaranteeCoverDto.setBeneficiaryBankCode(bankGuaranteeCoverDTO.getBeneficiaryBankCode());
		bankGuaranteeCoverDto.setBeneficiaryBankNameAndAddress(bankGuaranteeCoverDTO.getBeneficiaryBankNameAndAddress());
		bankGuaranteeCoverDto.setBeneficiaryNameAndDetails(bankGuaranteeCoverDTO.getBeneficiaryNameAndDetails());
		bankGuaranteeCoverDto.setSenderToReciverInformation(bankGuaranteeCoverDTO.getSenderToReciverInformation());
		bankGuaranteeCoverDto.setBgPurpose(bankGuaranteeCoverDTO.getBgPurpose());
		bankGuaranteeCoverDto.setDescriptionOfUnderlinedContract(bankGuaranteeCoverDTO.getDescriptionOfUnderlinedContract());
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
		
        session.put("ScreenData", bankGuaranteeCoverDto);
	}
	
	
	@SkipValidation
	public String reset() {
		try{
		bankGuaranteeCoverDto.setBgNumber("");
		bankGuaranteeCoverDto.setBgFormNumber("");
		bankGuaranteeCoverDto.setBgType("");
		bankGuaranteeCoverDto.setCurrency("");
		bankGuaranteeCoverDto.setBgAmount(null);
		bankGuaranteeCoverDto.setBgFromDate(null);
		bankGuaranteeCoverDto.setBgToDate(null);
		bankGuaranteeCoverDto.setBgEffectiveDate(null);
		bankGuaranteeCoverDto.setBgLodgementEndDate(null);
		bankGuaranteeCoverDto.setBgLodgementPalce("");
		bankGuaranteeCoverDto.setIssuingBankCode("");
		bankGuaranteeCoverDto.setIssunigBankNameAndAddress("");
		bankGuaranteeCoverDto.setBgApplicentNameAndDetails("");
		bankGuaranteeCoverDto.setBeneficiaryBankCode("");
		bankGuaranteeCoverDto.setBeneficiaryBankNameAndAddress("");
		bankGuaranteeCoverDto.setBeneficiaryNameAndDetails("");
		bankGuaranteeCoverDto.setSenderToReciverInformation("");
		bankGuaranteeCoverDto.setBgPurpose("");
		bankGuaranteeCoverDto.setDescriptionOfUnderlinedContract("");
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
		bankGuaranteeCoverDto.setAdvisingBank("");
		bankGuaranteeCoverDto.setBgIssuingBankCode("");
		return "success";
		}catch (Exception e) {
			logger.error(e,e);
			return "input";
		}

	}
	
	public String saveTemplate() 
	{
		
		try {			
			String tempRefKey="";
			PendingAuthorizationService pendingAuthorizationService = (PendingAuthorizationService)ApplicationContextProvider.getBean("pendingAuthorizationService");
			String userId = (String)session.get(WebConstants.CONSTANT_USER_NAME);
			
			tempRefKey = pendingAuthorizationService.getTempRef(serializeObject(),"760","COV",tempName,"Create Bank Guarantee Cover(MT-760COVER)",userId);
			
			pendingAuthorizationService.delimitedTempStringValue(tempRefKey, 1+"", "currency"+"~"+bankGuaranteeCoverDto.getCurrency());

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
	public String viewTemplateCreateBankGuanteeCover()
	{
		try{
			
		String msgRef = (String) session.get("messageRef");
		BankGuaranteeCoverService bankGuaranteeCoverService =  (BankGuaranteeCoverService)ApplicationContextProvider.getBean("bankGuaranteeCoverService");
		BankGuaranteeCoverDto bankGuaranteeCoverDto = bankGuaranteeCoverService.getBankGuaranteeCover(msgRef);
		if(bankGuaranteeCoverDto.getBgNumber()!=null && StringUtils.isNotBlank(bankGuaranteeCoverDto.getBgNumber()) && StringUtils.isNotEmpty(bankGuaranteeCoverDto.getBgNumber())){
			setALLValueTODTO(bankGuaranteeCoverDto);
			
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
	public String displayPrintPreviewCreateBGCover()
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
			bankGuaranteeCoverDto.setBgFormNumber(temp.getBgFormNumber());
			bankGuaranteeCoverDto.setBgType(temp.getBgType());
			bankGuaranteeCoverDto.setCurrency(temp.getCurrency());
			bankGuaranteeCoverDto.setBgAmount(temp.getBgAmount());
			bankGuaranteeCoverDto.setBgFromDate(temp.getBgFromDate());
			bankGuaranteeCoverDto.setBgToDate(temp.getBgToDate());
			bankGuaranteeCoverDto.setBgEffectiveDate(temp.getBgEffectiveDate());
			bankGuaranteeCoverDto.setBgLodgementEndDate(temp.getBgLodgementEndDate());
			bankGuaranteeCoverDto.setBgLodgementPalce(temp.getBgLodgementPalce());
			bankGuaranteeCoverDto.setIssuingBankCode(temp.getIssuingBankCode());
			bankGuaranteeCoverDto.setIssunigBankNameAndAddress(temp.getIssunigBankNameAndAddress());
			bankGuaranteeCoverDto.setBgApplicentNameAndDetails(temp.getBgApplicentNameAndDetails());
			bankGuaranteeCoverDto.setBeneficiaryNameAndDetails(temp.getBeneficiaryNameAndDetails());
			bankGuaranteeCoverDto.setBeneficiaryBankCode(temp.getBeneficiaryBankCode());
			bankGuaranteeCoverDto.setBeneficiaryBankNameAndAddress(temp.getBeneficiaryBankNameAndAddress());
			bankGuaranteeCoverDto.setSenderToReciverInformation(temp.getSenderToReciverInformation());
			bankGuaranteeCoverDto.setBgPurpose(temp.getBgPurpose());
			bankGuaranteeCoverDto.setDescriptionOfUnderlinedContract(temp.getDescriptionOfUnderlinedContract());
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
			viewCreateBankGuanteeCover();
			return "success";
		}
		else
		{
			bankGuaranteeCoverDto.setBgNumber(bankGuaranteeCoverDto.getBgNumber());
			bankGuaranteeCoverDto.setBgFormNumber(bankGuaranteeCoverDto.getBgFormNumber());
			bankGuaranteeCoverDto.setBgType(bankGuaranteeCoverDto.getBgType());
			bankGuaranteeCoverDto.setCurrency(bankGuaranteeCoverDto.getCurrency());
			bankGuaranteeCoverDto.setBgAmount(bankGuaranteeCoverDto.getBgAmount());
			bankGuaranteeCoverDto.setBgFromDate(bankGuaranteeCoverDto.getBgFromDate());
			bankGuaranteeCoverDto.setBgToDate(bankGuaranteeCoverDto.getBgToDate());
			bankGuaranteeCoverDto.setBgEffectiveDate(bankGuaranteeCoverDto.getBgEffectiveDate());
			bankGuaranteeCoverDto.setBgLodgementEndDate(bankGuaranteeCoverDto.getBgLodgementEndDate());
			bankGuaranteeCoverDto.setBgLodgementPalce(bankGuaranteeCoverDto.getBgLodgementPalce());
			bankGuaranteeCoverDto.setIssuingBankCode(bankGuaranteeCoverDto.getIssuingBankCode());
			bankGuaranteeCoverDto.setIssunigBankNameAndAddress(bankGuaranteeCoverDto.getIssunigBankNameAndAddress());
			bankGuaranteeCoverDto.setBgApplicentNameAndDetails(bankGuaranteeCoverDto.getBgApplicentNameAndDetails());
			bankGuaranteeCoverDto.setBeneficiaryNameAndDetails(bankGuaranteeCoverDto.getBeneficiaryNameAndDetails());
			bankGuaranteeCoverDto.setBeneficiaryBankCode(bankGuaranteeCoverDto.getBeneficiaryBankCode());
			bankGuaranteeCoverDto.setBeneficiaryBankNameAndAddress(bankGuaranteeCoverDto.getBeneficiaryBankNameAndAddress());
			bankGuaranteeCoverDto.setSenderToReciverInformation(bankGuaranteeCoverDto.getSenderToReciverInformation());
			bankGuaranteeCoverDto.setBgPurpose(bankGuaranteeCoverDto.getBgPurpose());
			bankGuaranteeCoverDto.setDescriptionOfUnderlinedContract(bankGuaranteeCoverDto.getDescriptionOfUnderlinedContract());
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
	public String exportToExcelCreateBGCover() throws Exception
	{		
	try{
		displayPrintPreviewCreateBGCover();
		String reportFile = "Create_Bank_Guarantee_Cover_(MT-760 COVER)";
		SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyyHHmmss");
		String currentDateTime = sdf.format(new Date());
		String reportFileName = reportFile+"_"+currentDateTime+"".concat(".xls");
		setReportFile(reportFileName);
		System.out.println("reportFileName is "+reportFileName);
		ExportToExcelUtil exportToExcelUtil = new ExportToExcelUtil();
		HSSFWorkbook myWorkBook = exportToExcelUtil.generateExportToExcel(null, null, bankGuaranteeCoverDto, reportFile, 760, "COV");
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
	
public void validate()
{
		
		
	if(StringUtils.isNotBlank(bankGuaranteeCoverDto.getStampDutyPaid()) && StringUtils.isNotEmpty(bankGuaranteeCoverDto.getStampDutyPaid()) && bankGuaranteeCoverDto.getStampDutyPaid().equalsIgnoreCase("Y"))
	{
			if(String.valueOf(bankGuaranteeCoverDto.getBgAmountPaid()).equalsIgnoreCase(null) && (String.valueOf(bankGuaranteeCoverDto.getBgAmountPaid())==null) && 
					StringUtils.isEmpty(bankGuaranteeCoverDto.getBgStateCode()) && 
					  StringUtils.isEmpty(bankGuaranteeCoverDto.getBgArticleNumber()) &&
					bankGuaranteeCoverDto.getBgPaymentDate()!=null && bankGuaranteeCoverDto.getBgPaymentDate()!=null)
			{
				addFieldError("stampDutyPaid", "If Field value of 7040 is 'Y' then field 7043,7044,7045,7046 must be");
			}
	}
		
	if(StringUtils.isBlank(bankGuaranteeCoverDto.getAdvisingBank()) && StringUtils.isEmpty(bankGuaranteeCoverDto.getAdvisingBank()))
	{
			addFieldError("advisingBank", "Receiver Bank IFSC is required");
	}
}

@SkipValidation
public String generatePDFCreateBGCover()
{

	displayPrintPreviewCreateBGCover();
	String reportName ="Create Bank Guarantee Cover(MT-760 COVER)";
	Map<String,String> fieldNamesMap = new HashMap<String,String>();
	
	try{
		
		fieldNamesMap.put("label.MB.IssuingBranchIFSC", getText("label.MB.IssuingBranchIFSC"));
		fieldNamesMap.put("label.MB.Benefifsc", getText("label.MB.Benefifsc"));
		
		fieldNamesMap.put("label.760COV.pptrxReferenceNo", getText("label.760COV.pptrxReferenceNo"));
		fieldNamesMap.put("label.760COV.ppbgFormNo", getText("label.760COV.ppbgFormNo"));
		fieldNamesMap.put("label.760COV.ppbgType", getText("label.760COV.ppbgType"));
		fieldNamesMap.put("label.760COV.ppcurrency", getText("label.760COV.ppcurrency"));
		fieldNamesMap.put("label.760COV.ppamtofGuarantee", getText("label.760COV.ppamtofGuarantee"));
		fieldNamesMap.put("label.760COV.ppbgFromDate", getText("label.760COV.ppbgFromDate"));
		fieldNamesMap.put("label.760COV.ppbgToDate", getText("label.760COV.ppbgToDate"));
		fieldNamesMap.put("label.760COV.ppbgGuaranteeEffDate", getText("label.760COV.ppbgGuaranteeEffDate"));
		fieldNamesMap.put("label.760COV.pplodgementDate", getText("label.760COV.pplodgementDate"));
		fieldNamesMap.put("label.760COV.pplodgmentClaimPlace", getText("label.760COV.pplodgmentClaimPlace"));
		fieldNamesMap.put("label.760COV.ppissuingBranchIFSC", getText("label.760COV.ppissuingBranchIFSC"));
		fieldNamesMap.put("label.760COV.ppissuingBankNameandAddress", getText("label.760COV.ppissuingBankNameandAddress"));
		fieldNamesMap.put("label.760COV.ppapplicantNameAddress", getText("label.760COV.ppapplicantNameAddress"));
		fieldNamesMap.put("label.760COV.ppbenefibrname", getText("label.760COV.ppbenefibrname"));
		fieldNamesMap.put("label.760COV.ppbenefifsc", getText("label.760COV.ppbenefifsc"));
		fieldNamesMap.put("label.760COV.ppbeneficiaryNameAddress", getText("label.760COV.ppbeneficiaryNameAddress"));
		fieldNamesMap.put("label.760COV.ppsenderToReceiverInformation", getText("label.760COV.ppsenderToReceiverInformation"));
		fieldNamesMap.put("label.760COV.pppurposeofGuarantee", getText("label.760COV.pppurposeofGuarantee"));
		fieldNamesMap.put("label.760COV.ppdescriptionoftheunderlinedcontract", getText("label.760COV.ppdescriptionoftheunderlinedcontract"));
		fieldNamesMap.put("label.760COV.ppelectronicallyPaid", getText("label.760COV.ppelectronicallyPaid"));
		fieldNamesMap.put("label.760COV.ppeStampCertificateNumber", getText("label.760COV.ppeStampCertificateNumber"));
		fieldNamesMap.put("label.760COV.ppeStamdateTime", getText("label.760COV.ppeStamdateTime"));
		fieldNamesMap.put("label.760COV.ppamountpaid", getText("label.760COV.ppamountpaid"));
		fieldNamesMap.put("label.760COV.ppstatecode", getText("label.760COV.ppstatecode"));
		fieldNamesMap.put("label.760COV.pparticleno", getText("label.760COV.pparticleno"));
		fieldNamesMap.put("label.760COV.ppdatetofpayment", getText("label.760COV.ppdatetofpayment"));
		fieldNamesMap.put("label.760COV.ppplaceofpay", getText("label.760COV.ppplaceofpay"));
		fieldNamesMap.put("label.760COV.ppebginDematForm", getText("label.760COV.ppebginDematForm"));
		fieldNamesMap.put("label.760COV.ppcustodianServiceProvider", getText("label.760COV.ppcustodianServiceProvider"));
		fieldNamesMap.put("label.760COV.ppdemataccno", getText("label.760COV.ppdemataccno"));
		
		PaymentPDFGeneratationUtil paymentPDFGeneratationUtil = new PaymentPDFGeneratationUtil();
		paymentPDFGeneratationUtil.setServletRequest(servletRequest);
		paymentPDFGeneratationUtil.setReportName(reportName);
		
		paymentPDFGeneratationUtil.generatePaymentPDFReport(null, null, bankGuaranteeCoverDto,fieldNamesMap, 760, "COV");
		
	}
	catch (Exception exception) {
		AuditServiceUtil.logException(exception,logger);
	}
	addActionError("Unable to Generate PDF file! Please try again");
	return "input";
}
	
}
