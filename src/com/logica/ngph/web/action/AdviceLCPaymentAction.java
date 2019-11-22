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
import com.logica.ngph.common.NGPHException;
import com.logica.ngph.dtos.LCAdvicePaymentDto;
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

public class AdviceLCPaymentAction extends ActionSupport implements ModelDriven<LCCanonicalDto>, SessionAware, ServletRequestAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(AdviceLCPaymentAction.class);
	private Map<String, Object> session;
	LCCanonicalDto lcAdvicePaymentDto =new LCCanonicalDto();
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
	private String lcNumber;
	@SuppressWarnings("rawtypes")
	private List<LCAdvicePaymentDto> searchList;
	
	public String getLcNumber() {
		return lcNumber;
	}

	public void setLcNumber(String lcNumber) {
		this.lcNumber = lcNumber;
	}
	@SuppressWarnings("rawtypes")
	public List<LCAdvicePaymentDto> getSearchList() {
		return searchList;
	}

	public void setSearchList(List<LCAdvicePaymentDto> searchList) {
		this.searchList = searchList;
	}
	
	public String getTxnRef() {
		return txnRef;
	}

	public void setTxnRef(String txnRef) {
		this.txnRef = txnRef;
	}

	public String getAdvicePayment()
	{
		try{
				String txnKey="";
				boolean isvalidLcAdvicePaymentDto = false;
				isvalidLcAdvicePaymentDto =validateLcAdvicePaymentDto(lcAdvicePaymentDto);
				if(isvalidLcAdvicePaymentDto){
				PendingAuthorizationService pendingAuthorizationService = (PendingAuthorizationService)ApplicationContextProvider.getBean("pendingAuthorizationService");
				EventLogging eventLogging = (EventLogging)ApplicationContextProvider.getBean("eventLogging");
				String userId = (String)session.get(WebConstants.CONSTANT_USER_NAME);
				if(StringUtils.isBlank(lcAdvicePaymentDto.getRepair()) && StringUtils.isEmpty(lcAdvicePaymentDto.getRepair())){
					if(!getHiddenTxnRef().isEmpty())
					{
						pendingAuthorizationService.updateRejectStatusToPending(getHiddenTxnRef());
					}else
					{
						txnKey = pendingAuthorizationService.getTranRef(serializeObject(),"Advice Lc Payment(MT-756)",userId);
						pendingAuthorizationService.delimitedStringValue(txnKey, 1+"", "claimCurrency"+"~"+lcAdvicePaymentDto.getClaimCurrency());
						pendingAuthorizationService.delimitedStringValue(txnKey, 2+"", "currency"+"~"+lcAdvicePaymentDto.getCurrency());
						eventLogging.doEventLogging(userId, "Advice Lc Payment", ConstantUtil.EVENTID_LC_ADVICE+ConstantUtil.EVENTID_SUBMIT, ConstantUtil.EVENTLOGGINGCOMMENTSUBMIT,lcAdvicePaymentDto.getLcNumber(),lcAdvicePaymentDto.getMsgRef());
					}
					
				}else{
					PaymentMessageService paymentMessageService =(PaymentMessageService)ApplicationContextProvider.getBean("paymentMessageService");
					paymentMessageService.changeMsgStatus2to99(lcAdvicePaymentDto.getMsgRef());
					txnKey = pendingAuthorizationService.getTranRef(serializeObject(),"Advice Lc Payment(MT-756)",userId);
					pendingAuthorizationService.delimitedStringValue(txnKey, 1+"", "claimCurrency"+"~"+lcAdvicePaymentDto.getClaimCurrency());
					pendingAuthorizationService.delimitedStringValue(txnKey, 2+"", "currency"+"~"+lcAdvicePaymentDto.getCurrency());
				}
				
				session.put("screenRef", txnKey);
				return "success";
				}
				else{
					return "input";	
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
			return "input";				
	}
	
	private boolean validateLcAdvicePaymentDto(LCCanonicalDto lcAdvicePaymentDto){
		boolean result = true;
		if(lcAdvicePaymentDto.getPresentingBanksReference() != null && !lcAdvicePaymentDto.getPresentingBanksReference().equals("")){
			result = validateLcNumber(lcAdvicePaymentDto.getPresentingBanksReference());
			if(!result){
				addFieldError("Presenting Bank's Reference", "Presenting Bank's Reference, field must not start or end with a slash '/' and must not contain two consecutive slashes '//'");							
				result = false;	
			}
			
		}else{
			addFieldError("presentingBanksReference","Presenting Bank's Reference is Mandatory Fields");
			result = false;				
		}		
		return result;
	}
	
	public String getObjectForLCAP()
	{
		try{	
			
			String userId = (String)session.get(WebConstants.CONSTANT_USER_NAME);	
			PendingAuthorizationService pendingAuthorizationService = (PendingAuthorizationService)ApplicationContextProvider.getBean("pendingAuthorizationService");
			LetterOfCreditService letterOfCreditService =  (LetterOfCreditService)  ApplicationContextProvider.getBean("letterOfCreditService");
			EventLogging eventLogging = (EventLogging)ApplicationContextProvider.getBean("eventLogging");
			if(getSaveAction().equals("Approve")){
				 String returnString =letterOfCreditService.saveLC(lcAdvicePaymentDto,null,"PaymentAdvice",userId,lcAdvicePaymentDto.getRepair());
				if(returnString!=null && !returnString.equals("") ){
					pendingAuthorizationService.changeStatus(getSaveAction(), getHiddenTxnRef());
					eventLogging.doEventLogging(userId, "Advice Lc Payment", ConstantUtil.EVENTID_LC_ADVICE+ConstantUtil.EVENTID_APPROVE, ConstantUtil.EVENTLOGGINGCOMMENTAPPROVAL,lcAdvicePaymentDto.getLcNumber(),lcAdvicePaymentDto.getMsgRef());
					if(StringUtils.isBlank(lcAdvicePaymentDto.getRepair()) && StringUtils.isEmpty(lcAdvicePaymentDto.getRepair())){
					}else{
					}
					return "success";	
				}else{
					addActionError("Unable to perform the operation. Please try again");
					return "input";
					}
				}else{
					if(StringUtils.isBlank(lcAdvicePaymentDto.getRepair()) && StringUtils.isEmpty(lcAdvicePaymentDto.getRepair())){
						pendingAuthorizationService.changeStatus(getSaveAction(), getHiddenTxnRef());
						eventLogging.doEventLogging(userId, "Advice Lc Payment", ConstantUtil.EVENTID_LC_ADVICE+ConstantUtil.EVENTID_REJECT, ConstantUtil.EVENTLOGGINGCOMMENTREJECT,lcAdvicePaymentDto.getLcNumber(),lcAdvicePaymentDto.getMsgRef());
					}else{
						PaymentMessageService paymentMessageService =(PaymentMessageService)ApplicationContextProvider.getBean("paymentMessageService");
						paymentMessageService.changeMsgStatus99to2(lcAdvicePaymentDto.getMsgRef());
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
		return "input";
	}
	@SkipValidation
	public String displayLCAdvicePaymentData()
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
			
			canonicalDto = lcAdvicePaymentDto;
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
	public LCCanonicalDto getSerializedObject(String objectString)
	{
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
            System.out.print("User(testDTO) :"+testDTO.getLcNumber());
          
            return testDTO;

		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	@SkipValidation
	public String getAdviceLCPaymentAuthorization()
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
		LCCanonicalDto temp= getSerializedObject(tempScreenString);
		lcAdvicePaymentDto.setLcNumber(temp.getLcNumber());
		lcAdvicePaymentDto.setPresentingBanksReference(temp.getPresentingBanksReference());
		lcAdvicePaymentDto.setTotalAmountClaimed(temp.getTotalAmountClaimed());
		lcAdvicePaymentDto.setAmountPaidDate(temp.getAmountPaidDate());
		lcAdvicePaymentDto.setPaidAmount(temp.getPaidAmount());	
		lcAdvicePaymentDto.setSendersCorrespondentPartyIdentifier(temp.getSendersCorrespondentPartyIdentifier());
		lcAdvicePaymentDto.setSenderCorrespontAcount(temp.getSenderCorrespontAcount());	
		lcAdvicePaymentDto.setSendersCorrespondentCode(temp.getSendersCorrespondentCode());
		lcAdvicePaymentDto.setSendersCorrespondentLocation(temp.getSendersCorrespondentLocation());
		lcAdvicePaymentDto.setSendersCorrespondentNameandAddress(temp.getSendersCorrespondentNameandAddress());
		lcAdvicePaymentDto.setReceiversCorrespondentPartyIdentifier(temp.getReceiversCorrespondentPartyIdentifier());
		lcAdvicePaymentDto.setReceiverCorrespontAcount(temp.getReceiverCorrespontAcount());		
		lcAdvicePaymentDto.setReceiversCorrespondentCode(temp.getReceiversCorrespondentCode());
		lcAdvicePaymentDto.setReceiversCorrespondentLocation(temp.getReceiversCorrespondentLocation());
		lcAdvicePaymentDto.setReceiversCorrespondentNameandAddress(temp.getReceiversCorrespondentNameandAddress());
		lcAdvicePaymentDto.setSendertoReceiverInformation(temp.getSendertoReceiverInformation());
		lcAdvicePaymentDto.setIssuingBankCode(temp.getIssuingBankCode());
	    lcAdvicePaymentDto.setAdvisingBank(temp.getAdvisingBank());
		lcAdvicePaymentDto.setMsgRef(temp.getMsgRef());
		if(getTxnRef()!=null)
		{
			List multiCurrCodeList = pendingAuthorizationService.getMulScreenData(getTxnRef());
			
			for(int i=0;i<multiCurrCodeList.size();i++)
			{
				Clob tempCurrCodeList = (Clob)multiCurrCodeList.get(i);
				String data[]= tempCurrCodeList.getSubString(1, (int) tempCurrCodeList.length()).toString().split("~");
				if(data[0].toString().trim().equals("claimCurrency")  && !data[0].toString().trim().equals(""))
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
				if(data[0].toString().trim().equals("claimCurrency")  && !data[0].toString().trim().equals(""))
				{
					currCodeDropDown.add(data[1].toString());
				}
				else if(data[0].toString().trim().equals("currency")  && !data[0].toString().trim().equals(""))
				{
					currCodeDropDown.add(data[1].toString());
				}
			}
		}
		lcAdvicePaymentDto.setClaimCurrency(temp.getClaimCurrency());
		lcAdvicePaymentDto.setCurrency(temp.getCurrency());
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
	public String viewAdvicePayment()
	{
		try{
			String msgRef = (String) session.get("messageIndex");
			LetterOfCreditService letterOfCreditService =  (LetterOfCreditService)  ApplicationContextProvider.getBean("letterOfCreditService");
			LCCanonicalDto canonicalDto = letterOfCreditService.getPreAdviceRepair(msgRef);
			if(canonicalDto.getLcNumber()!=null && StringUtils.isNotBlank(canonicalDto.getLcNumber()) && StringUtils.isNotEmpty(canonicalDto.getLcNumber())){
				canonicalDto.setRepair(ConstantUtil.REPAIR);
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
		lcAdvicePaymentDto.setLcNumber(temp.getLcNumber());
		lcAdvicePaymentDto.setPresentingBanksReference(temp.getPresentingBanksReference());
		lcAdvicePaymentDto.setClaimCurrency(temp.getClaimCurrency());
		lcAdvicePaymentDto.setTotalAmountClaimed(temp.getTotalAmountClaimed());
		lcAdvicePaymentDto.setAmountPaidDate(temp.getAmountPaidDate());
		lcAdvicePaymentDto.setCurrency(temp.getCurrency());
		lcAdvicePaymentDto.setPaidAmount(temp.getPaidAmount());	
		lcAdvicePaymentDto.setSendersCorrespondentPartyIdentifier(temp.getSendersCorrespondentPartyIdentifier());
		lcAdvicePaymentDto.setSenderCorrespontAcount(temp.getSenderCorrespontAcount());	
		lcAdvicePaymentDto.setSendersCorrespondentCode(temp.getSendersCorrespondentCode());
		lcAdvicePaymentDto.setSendersCorrespondentLocation(temp.getSendersCorrespondentLocation());
		lcAdvicePaymentDto.setSendersCorrespondentNameandAddress(temp.getSendersCorrespondentNameandAddress());
		lcAdvicePaymentDto.setReceiversCorrespondentPartyIdentifier(temp.getReceiversCorrespondentPartyIdentifier());
		lcAdvicePaymentDto.setReceiverCorrespontAcount(temp.getReceiverCorrespontAcount());		
		lcAdvicePaymentDto.setReceiversCorrespondentCode(temp.getReceiversCorrespondentCode());
		lcAdvicePaymentDto.setReceiversCorrespondentLocation(temp.getReceiversCorrespondentLocation());
		lcAdvicePaymentDto.setReceiversCorrespondentNameandAddress(temp.getReceiversCorrespondentNameandAddress());
		lcAdvicePaymentDto.setSendertoReceiverInformation(temp.getSendertoReceiverInformation());
		lcAdvicePaymentDto.setIssuingBankCode(temp.getIssuingBankCode());
	    lcAdvicePaymentDto.setAdvisingBank(temp.getAdvisingBank());
		lcAdvicePaymentDto.setMsgRef(temp.getMsgRef());
		lcAdvicePaymentDto.setRepair(temp.getRepair());
	}
	
	public void validate()
	{
		
		LetterOfCreditService letterOfCreditService =  (LetterOfCreditService)  ApplicationContextProvider.getBean("letterOfCreditService");
		String senderBank = letterOfCreditService.getDept((String)session.get(WebConstants.CONSTANT_USER_NAME));
		if(StringUtils.isBlank(lcAdvicePaymentDto.getRepair()) && StringUtils.isEmpty(lcAdvicePaymentDto.getRepair())){
			
			if(senderBank!=null &&  lcAdvicePaymentDto.getAdvisingBank()!=null)
			{
				if(senderBank.trim().equalsIgnoreCase(lcAdvicePaymentDto.getAdvisingBank().trim()))
						{
							addFieldError("advisingBank", "Receiver Bank IFSC Should Not Be Sender Bank IFSC");
						}
			}
			}else
			{
				if(StringUtils.isNotBlank(lcAdvicePaymentDto.getSenderBank())&&StringUtils.isNotEmpty(lcAdvicePaymentDto.getSenderBank()) &&  lcAdvicePaymentDto.getAdvisingBank()!=null)
				{
					if(lcAdvicePaymentDto.getSenderBank().trim().equalsIgnoreCase(lcAdvicePaymentDto.getAdvisingBank().trim()))
							{
								addFieldError("advisingBank", "Receiver Bank IFSC Should Not Be Sender Bank IFSC");
							}
				}	
			}	
		
		
	
			if(!StringUtils.isBlank(lcAdvicePaymentDto.getRepair()) && !StringUtils.isEmpty(lcAdvicePaymentDto.getRepair())){
				PaymentMessageService paymentMessageService =(PaymentMessageService)ApplicationContextProvider.getBean("paymentMessageService");
				if(!paymentMessageService.checkInRptStatusIs2(lcAdvicePaymentDto.getMsgRef()))
				{
					if(!(getSaveAction().equals("Approve") || getSaveAction().equals("Reject")))
						addFieldError("LcNumber", "Message Is Not In Valid State");
				}
			}
		
		if(StringUtils.isNotBlank(lcAdvicePaymentDto.getSendersCorrespondentCode()) && StringUtils.isNotEmpty(lcAdvicePaymentDto.getSendersCorrespondentCode()) && lcAdvicePaymentDto.getSendersCorrespondentCode()!=null){
			if(letterOfCreditService.checkIFSC(lcAdvicePaymentDto.getSendersCorrespondentCode())==false){
				addFieldError("sendersCorrespondentCode", "Senders Correspondent BankCode 53A Is Not Available In System");
			}
		}
		
		if(StringUtils.isNotBlank(lcAdvicePaymentDto.getReceiversCorrespondentCode()) && StringUtils.isNotEmpty(lcAdvicePaymentDto.getReceiversCorrespondentCode()) && lcAdvicePaymentDto.getReceiversCorrespondentCode()!=null){
			if(letterOfCreditService.checkIFSC(lcAdvicePaymentDto.getReceiversCorrespondentCode())==false){
				addFieldError("receiversCorrespondentCode", "Receivers Correspondent Bank Code 54A Is Not Available In System");
			}
		}
		
		
		
		if(lcAdvicePaymentDto.getClaimCurrency()!=null && StringUtils.isNotEmpty(lcAdvicePaymentDto.getClaimCurrency()))
		{
			System.out.println("LcCurrency "+lcAdvicePaymentDto.getClaimCurrency());
			if(lcAdvicePaymentDto.getCurrency()!=null && StringUtils.isNotEmpty(lcAdvicePaymentDto.getCurrency()))
			{
				System.out.println("Currency"+lcAdvicePaymentDto.getCurrency());
				if(!lcAdvicePaymentDto.getClaimCurrency().equalsIgnoreCase(lcAdvicePaymentDto.getCurrency()))
				{
					addFieldError("lcCurrency","The currency code in the amount fields 32B and 33A must be the same");
				}
			}
		}
	
		
		if(StringUtils.isBlank(lcAdvicePaymentDto.getAdvisingBank()) && StringUtils.isEmpty(lcAdvicePaymentDto.getAdvisingBank()))
		{
				addFieldError("advisingBank", "Receiver Bank IFSC is required");
		}
	}
	
	@SkipValidation
	public String displayLcAdvicing() 
	{
		try{
			AuthorizationSchemaMaitenanceService authorizationSchemaMaitenanceService = (AuthorizationSchemaMaitenanceService)ApplicationContextProvider.getBean("authorizationSchemaMaitenanceService");
			setCurrCodeDropDown(authorizationSchemaMaitenanceService.getAuthorizationSchemaMaitenanceService(ConstantUtil.CURRENCY));
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
		return "input";
	}
	
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
	public void setMobel(LCCanonicalDto connionicalDto)
	{
		 this.lcAdvicePaymentDto=connionicalDto;
	}
	public LCCanonicalDto getModel() {
		
		return lcAdvicePaymentDto;
	}

public String saveTemplate() {
		
		try {			
			System.out.println("Template Name is "+tempName);
			String tempRefKey="";
			PendingAuthorizationService pendingAuthorizationService = (PendingAuthorizationService)ApplicationContextProvider.getBean("pendingAuthorizationService");
			String userId = (String)session.get(WebConstants.CONSTANT_USER_NAME);
			
			tempRefKey = pendingAuthorizationService.getTempRef(serializeObject(),"756","XXX",tempName,"Advice Lc Payment(MT-756)",userId);
			pendingAuthorizationService.delimitedTempStringValue(tempRefKey, 1+"", "claimCurrency"+"~"+lcAdvicePaymentDto.getClaimCurrency());
			pendingAuthorizationService.delimitedTempStringValue(tempRefKey, 2+"", "currency"+"~"+lcAdvicePaymentDto.getCurrency());

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
public String viewTemplateAdvicePayment()
{
	try{
		String msgRef = (String) session.get("messageRef");
		LetterOfCreditService letterOfCreditService =  (LetterOfCreditService)  ApplicationContextProvider.getBean("letterOfCreditService");
		LCCanonicalDto canonicalDto = letterOfCreditService.getPreAdviceRepair(msgRef);
		if(canonicalDto.getLcNumber()!=null && StringUtils.isNotBlank(canonicalDto.getLcNumber()) && StringUtils.isNotEmpty(canonicalDto.getLcNumber())){
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
	public String displayPrintPreviewAdviceLCPayment()
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
					if((((String)session.get(WebConstants.CONSTANT_USER_NAME)).equals(userId) || userRole.equals("T")))
					{
						setValidUserToApprove(false);
					} else
					{
						setValidUserToApprove(true);
					}

				tempScreenString =pendingAuthorizationService.getScreenReturn(getHiddenTxnRef());
				LCCanonicalDto temp= getSerializedObject(tempScreenString);
				lcAdvicePaymentDto.setLcNumber(temp.getLcNumber());
				lcAdvicePaymentDto.setPresentingBanksReference(temp.getPresentingBanksReference());
				lcAdvicePaymentDto.setClaimCurrency(temp.getClaimCurrency());
				lcAdvicePaymentDto.setTotalAmountClaimed(temp.getTotalAmountClaimed());
				lcAdvicePaymentDto.setAmountPaidDate(temp.getAmountPaidDate());
				lcAdvicePaymentDto.setCurrency(temp.getCurrency());
				lcAdvicePaymentDto.setPaidAmount(temp.getPaidAmount());	
				lcAdvicePaymentDto.setSendersCorrespondentPartyIdentifier(temp.getSendersCorrespondentPartyIdentifier());
				lcAdvicePaymentDto.setSenderCorrespontAcount(temp.getSenderCorrespontAcount());	
				lcAdvicePaymentDto.setSendersCorrespondentCode(temp.getSendersCorrespondentCode());
				lcAdvicePaymentDto.setSendersCorrespondentLocation(temp.getSendersCorrespondentLocation());
				lcAdvicePaymentDto.setSendersCorrespondentNameandAddress(temp.getSendersCorrespondentNameandAddress());
				lcAdvicePaymentDto.setReceiversCorrespondentPartyIdentifier(temp.getReceiversCorrespondentPartyIdentifier());
				lcAdvicePaymentDto.setReceiverCorrespontAcount(temp.getReceiverCorrespontAcount());		
				lcAdvicePaymentDto.setReceiversCorrespondentCode(temp.getReceiversCorrespondentCode());
				lcAdvicePaymentDto.setReceiversCorrespondentLocation(temp.getReceiversCorrespondentLocation());
				lcAdvicePaymentDto.setReceiversCorrespondentNameandAddress(temp.getReceiversCorrespondentNameandAddress());
				lcAdvicePaymentDto.setSendertoReceiverInformation(temp.getSendertoReceiverInformation());
				lcAdvicePaymentDto.setIssuingBankCode(temp.getIssuingBankCode());
			    lcAdvicePaymentDto.setAdvisingBank(temp.getAdvisingBank());
				setPrintPreviewTxnRef(getHiddenTxnRef());
			}
			else if(msgRef!=null && !msgRef.isEmpty())
			{
				viewAdvicePayment();
				return "success";
			}
			else
			{
				lcAdvicePaymentDto.setLcNumber(lcAdvicePaymentDto.getLcNumber());
				lcAdvicePaymentDto.setPresentingBanksReference(lcAdvicePaymentDto.getPresentingBanksReference());
				lcAdvicePaymentDto.setClaimCurrency(lcAdvicePaymentDto.getClaimCurrency());
				lcAdvicePaymentDto.setTotalAmountClaimed(lcAdvicePaymentDto.getTotalAmountClaimed());
				lcAdvicePaymentDto.setAmountPaidDate(lcAdvicePaymentDto.getAmountPaidDate());
				lcAdvicePaymentDto.setCurrency(lcAdvicePaymentDto.getCurrency());
				lcAdvicePaymentDto.setPaidAmount(lcAdvicePaymentDto.getPaidAmount());	
				lcAdvicePaymentDto.setSendersCorrespondentPartyIdentifier(lcAdvicePaymentDto.getSendersCorrespondentPartyIdentifier());
				lcAdvicePaymentDto.setSenderCorrespontAcount(lcAdvicePaymentDto.getSenderCorrespontAcount());	
				lcAdvicePaymentDto.setSendersCorrespondentCode(lcAdvicePaymentDto.getSendersCorrespondentCode());
				lcAdvicePaymentDto.setSendersCorrespondentLocation(lcAdvicePaymentDto.getSendersCorrespondentLocation());
				lcAdvicePaymentDto.setSendersCorrespondentNameandAddress(lcAdvicePaymentDto.getSendersCorrespondentNameandAddress());
				lcAdvicePaymentDto.setReceiversCorrespondentPartyIdentifier(lcAdvicePaymentDto.getReceiversCorrespondentPartyIdentifier());
				lcAdvicePaymentDto.setReceiverCorrespontAcount(lcAdvicePaymentDto.getReceiverCorrespontAcount());		
				lcAdvicePaymentDto.setReceiversCorrespondentCode(lcAdvicePaymentDto.getReceiversCorrespondentCode());
				lcAdvicePaymentDto.setReceiversCorrespondentLocation(lcAdvicePaymentDto.getReceiversCorrespondentLocation());
				lcAdvicePaymentDto.setReceiversCorrespondentNameandAddress(lcAdvicePaymentDto.getReceiversCorrespondentNameandAddress());
				lcAdvicePaymentDto.setSendertoReceiverInformation(lcAdvicePaymentDto.getSendertoReceiverInformation());
				lcAdvicePaymentDto.setIssuingBankCode(lcAdvicePaymentDto.getIssuingBankCode());
			    lcAdvicePaymentDto.setAdvisingBank(lcAdvicePaymentDto.getAdvisingBank());
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
			lcAdvicePaymentDto.setLcNumber("");
			lcAdvicePaymentDto.setPresentingBanksReference("");
			lcAdvicePaymentDto.setClaimCurrency("");
			lcAdvicePaymentDto.setTotalAmountClaimed(null);
			lcAdvicePaymentDto.setAmountPaidDate(null);
			lcAdvicePaymentDto.setCurrency("");
			lcAdvicePaymentDto.setPaidAmount(null);	
			lcAdvicePaymentDto.setSendersCorrespondentPartyIdentifier("");
			lcAdvicePaymentDto.setSenderCorrespontAcount("");	
			lcAdvicePaymentDto.setSendersCorrespondentCode("");
			lcAdvicePaymentDto.setSendersCorrespondentLocation("");
			lcAdvicePaymentDto.setSendersCorrespondentNameandAddress("");
			lcAdvicePaymentDto.setReceiversCorrespondentPartyIdentifier("");
			lcAdvicePaymentDto.setReceiverCorrespontAcount("");		
			lcAdvicePaymentDto.setReceiversCorrespondentCode("");
			lcAdvicePaymentDto.setReceiversCorrespondentLocation("");
			lcAdvicePaymentDto.setReceiversCorrespondentNameandAddress("");
			lcAdvicePaymentDto.setSendertoReceiverInformation("");
			lcAdvicePaymentDto.setIssuingBankCode("");
		    lcAdvicePaymentDto.setAdvisingBank("");
		return "success";
		}catch (Exception e) {
			logger.error(e,e);
			return "input";
		}

	}
	
	@SkipValidation
	public String exportToExcelAdviceLCPayment() throws Exception
	{		
	try{
		displayPrintPreviewAdviceLCPayment();
		String reportFile = "Advice_LC_Payment_(MT-756)";
		SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyyHHmmss");
		String currentDateTime = sdf.format(new Date());
		String reportFileName = reportFile+"_"+currentDateTime+"".concat(".xls");
		setReportFile(reportFileName);
		System.out.println("reportFileName is "+reportFileName);
		ExportToExcelUtil exportToExcelUtil = new ExportToExcelUtil();
		HSSFWorkbook myWorkBook = exportToExcelUtil.generateExportToExcel(lcAdvicePaymentDto, null, null, reportFile, 756, "XXX");
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
	public String generatePDFAdviceLCPayment()
	{
		displayPrintPreviewAdviceLCPayment();
		String reportName ="Advice LC Payment(MT-756)";
		Map<String,String> fieldNamesMap = new HashMap<String,String>();
		
		try{
			
			fieldNamesMap.put("label.MB.IssuingBranchIFSC", getText("label.MB.IssuingBranchIFSC"));
			fieldNamesMap.put("label.MB.Benefifsc", getText("label.MB.Benefifsc"));
			
			fieldNamesMap.put("label.756.pplc_Number", getText("label.756.pplc_Number"));
			fieldNamesMap.put("label.756.ppPresentingBanksReference", getText("label.756.ppPresentingBanksReference"));
			fieldNamesMap.put("label.756.pplcCurrency", getText("label.756.pplcCurrency"));
			fieldNamesMap.put("label.756.ppTotalAmountClaimed", getText("label.756.ppTotalAmountClaimed"));
			fieldNamesMap.put("label.756.ppamountPaidDate", getText("label.756.ppamountPaidDate"));
			fieldNamesMap.put("label.756.ppcurrency", getText("label.756.ppcurrency"));
			fieldNamesMap.put("label.756.ppPaidAmount", getText("label.756.ppPaidAmount"));
			fieldNamesMap.put("label.756.ppSendersCorrespondentPartyIdentifier", getText("label.756.ppSendersCorrespondentPartyIdentifier"));
			fieldNamesMap.put("label.756.ppSendersCorrespondentcode", getText("label.756.ppSendersCorrespondentcode"));
			fieldNamesMap.put("label.756.ppSendersCorrespondentLocation", getText("label.756.ppSendersCorrespondentLocation"));
			fieldNamesMap.put("label.756.ppSendersCorrespondentNameandAddress", getText("label.756.ppSendersCorrespondentNameandAddress"));
			fieldNamesMap.put("label.756.ppReceiversCorrespondentPartyIdentifier", getText("label.756.ppReceiversCorrespondentPartyIdentifier"));
			fieldNamesMap.put("label.756.ppReceiversCorrespondentCode", getText("label.756.ppReceiversCorrespondentCode"));
			fieldNamesMap.put("label.756.ppReceiversCorrespondentLocation", getText("label.756.ppReceiversCorrespondentLocation"));
			fieldNamesMap.put("label.756.ppReceiversCorrespondentNameandAddress", getText("label.756.ppReceiversCorrespondentNameandAddress"));
			fieldNamesMap.put("label.756.ppSendertoReceiverInformation", getText("label.756.ppSendertoReceiverInformation"));
			
			PaymentPDFGeneratationUtil paymentPDFGeneratationUtil = new PaymentPDFGeneratationUtil();
			paymentPDFGeneratationUtil.setServletRequest(servletRequest);
			paymentPDFGeneratationUtil.setReportName(reportName);
			
			paymentPDFGeneratationUtil.generatePaymentPDFReport(lcAdvicePaymentDto, null, null, fieldNamesMap, 756, "XXX");
		}
		catch (Exception exception) {
			AuditServiceUtil.logException(exception,logger);
		}
		addActionError("Unable to Generate PDF file! Please try again");
		return "input";
	}
	
}
