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
import java.text.SimpleDateFormat;
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
import com.logica.ngph.dtos.ReportDto;
import com.logica.ngph.service.CreateBankGuaranteeService;
import com.logica.ngph.service.PaymentMessageService;
import com.logica.ngph.service.PendingAuthorizationService;
import com.logica.ngph.web.utils.ApplicationContextProvider;
import com.logica.ngph.web.utils.EventLogging;
import com.logica.ngph.web.utils.ExportToExcelUtil;
import com.logica.ngph.web.utils.PaymentPDFGeneratationUtil;
import com.logica.ngph.web.utils.ReportGenerationUtil;
import com.logica.ngph.web.utils.WebConstants;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * @author chakkar
 *
 */
public class FreeFormatPaymentAction extends ActionSupport implements
ModelDriven<CreateBankGuaranteeDto>, SessionAware,ServletRequestAware  {
	
	
	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(FreeFormatPaymentAction.class);
	public CreateBankGuaranteeDto createBankGuaranteeDto = new CreateBankGuaranteeDto();
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
	private String isValidUser;
	private String reportFile;
	private InputStream inputStream;
	private String messagegRef;
	private HttpServletRequest servletRequest;
	private String msgRef;
	
	
	
	
	
	
	public String getMsgRef() {
		return msgRef;
	}

	public void setMsgRef(String msgRef) {
		this.msgRef = msgRef;
	}

	public HttpServletRequest getServletRequest() {
		return servletRequest;
	}

	public void setServletRequest(HttpServletRequest servletRequest) {
		this.servletRequest = servletRequest;
	}

	/**
	 * @return the msgRef
	 */
	public String getMessagegRef() {
		return messagegRef;
	}

	/**
	 * @param msgRef the msgRef to set
	 */
	public void setMessagegRef(String messagegRef) {
		this.messagegRef = messagegRef;
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
	 * @return the isValidUser
	 */
	public String getIsValidUser() {
		return isValidUser;
	}

	/**
	 * @param isValidUser the isValidUser to set
	 */
	public void setIsValidUser(String isValidUser) {
		this.isValidUser = isValidUser;
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

	public String getTempName() {
		return tempName;
	}

	public void setTempName(String tempName) {
		this.tempName = tempName;
	}

	public String getTempRef() {
		return tempRef;
	}

	public void setTempRef(String tempRef) {
		this.tempRef = tempRef;
	}

	/**
	 * @return the bgMastDto
	 */
	public CreateBankGuaranteeDto getCreateBankGuaranteeDto() {
		return createBankGuaranteeDto;
	}

	/**
	 * @param bgMastDto the bgMastDto to set
	 */
	public void setCreateBankGuaranteeDto(CreateBankGuaranteeDto createBankGuaranteeDto) {
		this.createBankGuaranteeDto = createBankGuaranteeDto;
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
	 * @return the checkForSubmit
	 */
	public String getCheckForSubmit() {
		return checkForSubmit;
	}

	/**
	 * @param checkForSubmit the checkForSubmit to set
	 */
	public void setCheckForSubmit(String checkForSubmit) {
		this.checkForSubmit = checkForSubmit;
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
	 * @return the iFSCFLAG
	 */
	public String getIFSCFLAG() {
		return IFSCFLAG;
	}

	/**
	 * @param iFSCFLAG the iFSCFLAG to set
	 */
	public void setIFSCFLAG(String iFSCFLAG) {
		IFSCFLAG = iFSCFLAG;
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

	public CreateBankGuaranteeDto getModel() {
		return createBankGuaranteeDto;
	}
	
	@SkipValidation
	public String displayfreeFormatMessage()
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

	public String getFreeFormatApproval() {
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
				 txnKey = pendingAuthorizationService.getTranRef(serializeObject(),"Free Format Payment(MT-799)",userId);
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
					 addFieldError("Transaction Reference Number", "Transaction Reference Number Must Not start with /");
				}else if(createBankGuaranteeDto.getBgNumber().endsWith("/")){
					addFieldError("Transaction Reference Number", "Transaction Reference Number must not End with /");;
				}else if(createBankGuaranteeDto.getBgNumber().contains("//")){
					addFieldError("Transaction Reference Number", "Transaction Reference Number must not contain two consecutive slashes '//'");
				}
				
				if(createBankGuaranteeDto.getRelatedReference().startsWith("/")){
					 addFieldError("Related Reference Number", "Related Reference Number Must Not start with /");
				}else if(createBankGuaranteeDto.getRelatedReference().endsWith("/")){
					addFieldError("Related Reference Number", "Related Reference Number must not End with /");;
				}else if(createBankGuaranteeDto.getRelatedReference().contains("//")){
					addFieldError("Related Reference Number", "Related Reference Number must not contain two consecutive slashes '//'");
				}
				
				if(StringUtils.isBlank(createBankGuaranteeDto.getAdvisingBank()) && StringUtils.isEmpty(createBankGuaranteeDto.getAdvisingBank()))
				{
						addFieldError("advisingBank", "Receiver Bank IFSC is required");
				}

			}
	
			
			@SkipValidation
			public String getFreeFormatAuthorization()
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
				} else{
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
				createBankGuaranteeDto.setNarrative(temp.getNarrative());
				createBankGuaranteeDto.setAdvisingBank(temp.getAdvisingBank());
				createBankGuaranteeDto.setIssuingBankCode(temp.getIssuingBankCode());
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
			
			
			public String getObjectForFreeFormat()
			{
				try{
					String userId = (String)session.get(WebConstants.CONSTANT_USER_NAME);
					PendingAuthorizationService pendingAuthorizationService = (PendingAuthorizationService)ApplicationContextProvider.getBean("pendingAuthorizationService");
					PaymentMessageService paymentMessageService =(PaymentMessageService)ApplicationContextProvider.getBean("paymentMessageService");
					EventLogging eventLogging = (EventLogging)ApplicationContextProvider.getBean("eventLogging");
					CreateBankGuaranteeService createBankGuaranteeService = (CreateBankGuaranteeService)ApplicationContextProvider.getBean("createBankGuaranteeService");
					if(getSaveAction().equalsIgnoreCase("Approve")){
						createBankGuaranteeService.createFreeFormatPayment(createBankGuaranteeDto, userId);
						if(StringUtils.isBlank(createBankGuaranteeDto.getRepair()) && StringUtils.isEmpty(createBankGuaranteeDto.getRepair())){
							pendingAuthorizationService.changeStatus(getSaveAction(), getHiddenTxnRef());	
							 eventLogging.doEventLogging(userId, "Free Format Payment", ConstantUtil.EVENTID_FREE_FORMAT+ConstantUtil.EVENTID_APPROVE, ConstantUtil.EVENTLOGGINGCOMMENTAPPROVAL,createBankGuaranteeDto.getBgNumber(),createBankGuaranteeDto.getMsgRef());
						}
						else
						{
							paymentMessageService.changeMsgStatus2to99(createBankGuaranteeDto.getMsgRef());
						}
					}
					else
					{
						if(StringUtils.isBlank(createBankGuaranteeDto.getRepair()) && StringUtils.isEmpty(createBankGuaranteeDto.getRepair())){
							pendingAuthorizationService.changeStatus(getSaveAction(), getHiddenTxnRef());
							eventLogging.doEventLogging(userId, "Free Format Payment", ConstantUtil.EVENTID_FREE_FORMAT+ConstantUtil.EVENTID_REJECT, ConstantUtil.EVENTLOGGINGCOMMENTREJECT,createBankGuaranteeDto.getBgNumber(),createBankGuaranteeDto.getMsgRef());
						}else{
							paymentMessageService.changeMsgStatus99to2(createBankGuaranteeDto.getMsgRef());
							pendingAuthorizationService.changeStatus(getSaveAction(), getHiddenTxnRef());
						}
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
			public String viewFreeFormatPayment()
			{
				try{
					
				String msgRef = (String) session.get("messageIndex");
				CreateBankGuaranteeService createBankGuaranteeService = (CreateBankGuaranteeService)ApplicationContextProvider.getBean("createBankGuaranteeService");
				CreateBankGuaranteeDto createBankGuaranteeDto = createBankGuaranteeService.getFreeFormatMessage(msgRef);
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
				createBankGuaranteeDto.setNarrative(temp.getNarrative());
				createBankGuaranteeDto.setAdvisingBank(temp.getAdvisingBank());
				createBankGuaranteeDto.setIssuingBankCode(temp.getIssuingBankCode());
				createBankGuaranteeDto.setSeqNo(temp.getSeqNo());
		        session.put("ScreenData", createBankGuaranteeDto);
			}
			
			public String saveFreeFormatTemplate() {
				
				try {			
					String tempRefKey="";
					PendingAuthorizationService pendingAuthorizationService = (PendingAuthorizationService)ApplicationContextProvider.getBean("pendingAuthorizationService");
					String userId = (String)session.get(WebConstants.CONSTANT_USER_NAME);
					tempRefKey = pendingAuthorizationService.getTempRef(serializeObject(),"799","XXX",tempName,"Free Format Payment(MT-799)",userId);

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
			public String viewTemplateFreeFormatPayment()
			{
				try{
					
				String msgRef = (String) session.get("messageRef");
				CreateBankGuaranteeDto createBankGuaranteeDto =null;
				CreateBankGuaranteeService createBankGuaranteeService = (CreateBankGuaranteeService)ApplicationContextProvider.getBean("createBankGuaranteeService");
				if(getMessagegRef()!=null && !getMessagegRef().isEmpty())
				{
					createBankGuaranteeDto = createBankGuaranteeService.getFreeFormatMessage(getMessagegRef());
				}
				else
				{
					createBankGuaranteeDto = createBankGuaranteeService.getFreeFormatMessage(msgRef);
				}
				
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
			public String displayPrintPreviewFreeFormatPage()
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
					createBankGuaranteeDto.setNarrative(temp.getNarrative());
					createBankGuaranteeDto.setAdvisingBank(temp.getAdvisingBank());
					createBankGuaranteeDto.setIssuingBankCode(temp.getIssuingBankCode());
					createBankGuaranteeDto.setSeqNo(temp.getSeqNo());
					setPrintPreviewTxnRef(getHiddenTxnRef());
				}
				else if(msgRef!=null && !msgRef.isEmpty() || getMessagegRef()!=null && !getMessagegRef().isEmpty())
				{
					viewTemplateFreeFormatPayment();
					this.session.put("messageRef", getMessagegRef());
					return "success";
				}
				else
				{
					createBankGuaranteeDto.setBgNumber(createBankGuaranteeDto.getBgNumber());
					createBankGuaranteeDto.setRelatedReference(createBankGuaranteeDto.getRelatedReference());
					createBankGuaranteeDto.setNarrative(createBankGuaranteeDto.getNarrative());
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
			public String reset() {
				try{
					createBankGuaranteeDto.setBgNumber("");
					createBankGuaranteeDto.setRelatedReference("");
					createBankGuaranteeDto.setNarrative("");
					createBankGuaranteeDto.setAdvisingBank("");
					createBankGuaranteeDto.setIssuingBankCode("");
					createBankGuaranteeDto.setSeqNo("");
				return "success";
				}catch (Exception e) {
					logger.error(e,e);
					return "input";
				}

			}
			
			@SkipValidation
			public String exportToExcelFreeformat() throws Exception
			{
				
				
			try{
				displayPrintPreviewFreeFormatPage();
				String reportFile = "Free_Format_Payment_(MT-799)";
				SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyyHHmmss");
				String currentDateTime = sdf.format(new Date());
				String reportFileName = reportFile+"_"+currentDateTime+"".concat(".xls");
				setReportFile(reportFileName);
				System.out.println("reportFileName is "+reportFileName);
				ExportToExcelUtil exportToExcelUtil = new ExportToExcelUtil();
				if(StringUtils.isNotEmpty((String) session.get("messageRef")) && StringUtils.isNotBlank((String) session.get("messageRef")))
				{
					viewTemplateFreeFormatPayment();
				}
				HSSFWorkbook myWorkBook = exportToExcelUtil.generateExportToExcel(null, createBankGuaranteeDto, null, reportFile, 799, "XXX");
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
			public String generatePDFFreeFormat()
			{
				displayPrintPreviewFreeFormatPage();
				String reportName ="Free Format Message(MT-799)";
				String msgRef = (String) session.get("messageIndex");
				Map<String,String> fieldNamesMap = new HashMap<String,String>();
				
				try{
					
					fieldNamesMap.put("label.MB.IssuingBranchIFSC", getText("label.MB.IssuingBranchIFSC"));
					fieldNamesMap.put("label.MB.Benefifsc", getText("label.MB.Benefifsc"));
					
					fieldNamesMap.put("label.799.pptransactionReferenceNumber", getText("label.799.pptransactionReferenceNumber"));
					fieldNamesMap.put("label.799.pprelatedReferenceNumber", getText("label.799.pprelatedReferenceNumber"));
					fieldNamesMap.put("label.799.ppNarrative", getText("label.799.ppNarrative"));
					
					PaymentPDFGeneratationUtil paymentPDFGeneratationUtil = new PaymentPDFGeneratationUtil();
					paymentPDFGeneratationUtil.setServletRequest(servletRequest);
					paymentPDFGeneratationUtil.setReportName(reportName);
					
					if(StringUtils.isNotEmpty((String) session.get("messageRef")) && StringUtils.isNotBlank((String) session.get("messageRef")))
					{
						viewTemplateFreeFormatPayment();
					}
					paymentPDFGeneratationUtil.generatePaymentPDFReport(null, createBankGuaranteeDto, null,fieldNamesMap, 799, "XXX");
					
				}
				catch (Exception exception) {
					AuditServiceUtil.logException(exception,logger);
				}
				addActionError("Unable to Generate PDF file! Please try again");
				return "input";
			}
}
