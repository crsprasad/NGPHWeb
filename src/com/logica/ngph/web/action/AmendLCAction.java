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
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
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
import com.logica.ngph.dtos.LCCanonicalDto;
import com.logica.ngph.service.AuthorizationSchemaMaitenanceService;
import com.logica.ngph.service.LetterOfCreditService;
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

public class AmendLCAction extends ActionSupport implements ModelDriven<LCCanonicalDto>, SessionAware, ServletRequestAware{

	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(AmendLCAction.class);
	private Map<String, Object> session;
	LCCanonicalDto lcCanonicalDto =new LCCanonicalDto();
	private String saveAction;
	private String checkForSubmit;
	private boolean validUserToApprove;
	private String hiddenTxnRef;
	private String txnRef;
	private List positiveToleranceList = new ArrayList();
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
	@SkipValidation
	public String displayAmendLC()
	{
		try{
			UserMaintenanceService userMaintenanceService = (UserMaintenanceService)ApplicationContextProvider.getBean("userMaintenanceService");
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
	
	
	public String getAmendLCSubmit()
	{
		try{
				String txnKey="";
				PendingAuthorizationService pendingAuthorizationService = (PendingAuthorizationService)ApplicationContextProvider.getBean("pendingAuthorizationService");
				EventLogging eventLogging = (EventLogging)ApplicationContextProvider.getBean("eventLogging");
				String userId = (String)session.get(WebConstants.CONSTANT_USER_NAME);
				
				if(StringUtils.isBlank(lcCanonicalDto.getRepair()) && StringUtils.isEmpty(lcCanonicalDto.getRepair())){
					if(!getHiddenTxnRef().isEmpty())
					{
						pendingAuthorizationService.updateRejectStatusToPending(getHiddenTxnRef());
					}else
					{
						txnKey = pendingAuthorizationService.getTranRef(serializeObject(),"Amend LC(MT-707)",userId);
						pendingAuthorizationService.delimitedStringValue(txnKey, 1+"", "msgCurrency"+"~"+lcCanonicalDto.getMsgCurrency());
						pendingAuthorizationService.delimitedStringValue(txnKey, 2+"", "lcCurrency"+"~"+lcCanonicalDto.getLcCurrency());
						pendingAuthorizationService.delimitedStringValue(txnKey, 3+"", "currency"+"~"+lcCanonicalDto.getCurrency());
						
						eventLogging.doEventLogging(userId, "Amend LC", ConstantUtil.EVENTID_AMEND_LC+ConstantUtil.EVENTID_SUBMIT, ConstantUtil.EVENTLOGGINGCOMMENTSUBMIT,lcCanonicalDto.getLcNumber(),lcCanonicalDto.getMsgRef());
					}
				}else{
					PaymentMessageService paymentMessageService =(PaymentMessageService)ApplicationContextProvider.getBean("paymentMessageService");
					paymentMessageService.changeMsgStatus2to99(lcCanonicalDto.getMsgRef());
					txnKey = pendingAuthorizationService.getTranRef(serializeObject(),"Amend LC",userId);
					eventLogging.doEventLogging(userId, "Amend LC", ConstantUtil.EVENTID_AMEND_LC+ConstantUtil.EVENTID_REPAIR_SUBMIT, ConstantUtil.EVENTLOGGINGCOMMENTSUBMIT+" "+ConstantUtil.EVENTLOGGINGCOMMENTREAPIR,lcCanonicalDto.getLcNumber(),lcCanonicalDto.getMsgRef());
				}
				
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
	
	
	
	public String serializeObject()
	{
		
		LCCanonicalDto canonicalDto = new LCCanonicalDto();
		try{
			String userId = (String)session.get(WebConstants.CONSTANT_USER_NAME);
			
			canonicalDto = lcCanonicalDto;
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
	public String getAmendLCAuthorization()
	{
		String tempScreenString = null;
		String userId = null;
		PendingAuthorizationService pendingAuthorizationService = (PendingAuthorizationService)ApplicationContextProvider.getBean("pendingAuthorizationService");
		
	try{
		
		if(getTxnRef()!=null)
		{
			displayAmendLC();
		setCheckForSubmit("Display_Approve_Reject");
		 userId = pendingAuthorizationService.getCreatedUser(getTxnRef());
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
			displayAmendLC();
			tempScreenString = pendingAuthorizationService.getTemplateScreen(getTempRef());
		}
		LCCanonicalDto temp= getSerializedObject(tempScreenString);
			
		lcCanonicalDto.setLcNumber(temp.getLcNumber());
		lcCanonicalDto.setReceiverBankReference(temp.getReceiverBankReference());
		lcCanonicalDto.setSenderBankReference(temp.getSenderBankReference());
		lcCanonicalDto.setSendersCorrespondentPartyIdentifier(temp.getSendersCorrespondentPartyIdentifier());
		lcCanonicalDto.setApplicantBankCode(temp.getApplicantBankCode());
		lcCanonicalDto.setIssunigBankNameAndAddress(temp.getIssunigBankNameAndAddress());
		lcCanonicalDto.setIssueDate(temp.getIssueDate());
		lcCanonicalDto.setAmendmentDate(temp.getAmendmentDate());
		lcCanonicalDto.setLcAmndmntNo(temp.getLcAmndmntNo());
		lcCanonicalDto.setBeneficiaryNameAddress(temp.getBeneficiaryNameAddress());
		lcCanonicalDto.setIncreaseAmendAmount(temp.getIncreaseAmendAmount());
		lcCanonicalDto.setDecreaseAmendAmount(temp.getDecreaseAmendAmount());
		lcCanonicalDto.setNewAmendExpiryDate(temp.getNewAmendExpiryDate());
		lcCanonicalDto.setNewLcAmount(temp.getNewLcAmount());	
		lcCanonicalDto.setPositiveTolerance(temp.getPositiveTolerance());
        lcCanonicalDto.setNegativeTolerance(temp.getNegativeTolerance());
        lcCanonicalDto.setMaximumCreditAmount(temp.getMaximumCreditAmount());
        lcCanonicalDto.setAdditionalAmountsCovered(temp.getAdditionalAmountsCovered());
        lcCanonicalDto.setGoodsLoadingDispatchPlace(temp.getGoodsLoadingDispatchPlace());
        lcCanonicalDto.setGoodsDestination(temp.getGoodsDestination());
        lcCanonicalDto.setInitialDispatchPlace(temp.getInitialDispatchPlace());
        lcCanonicalDto.setFinalDeliveryPlace(temp.getFinalDeliveryPlace());
        lcCanonicalDto.setLatestDateofShipment(temp.getLatestDateofShipment());
        lcCanonicalDto.setShipmentPeriod(temp.getShipmentPeriod());
        lcCanonicalDto.setNarrative(temp.getNarrative());
        lcCanonicalDto.setSendertoReceiverInformation(temp.getSendertoReceiverInformation());
        lcCanonicalDto.setIssuingBankCode(temp.getIssuingBankCode());
    	lcCanonicalDto.setAdvisingBank(temp.getAdvisingBank());
    	lcCanonicalDto.setMsgCurrency(temp.getMsgCurrency());
    	lcCanonicalDto.setLcCurrency(temp.getLcCurrency());
    	lcCanonicalDto.setCurrency(temp.getCurrency());
    	
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
	
	
	public String getObjectForAmendLC()
	{
		try{	
			String userId = (String)session.get(WebConstants.CONSTANT_USER_NAME);	
			PendingAuthorizationService pendingAuthorizationService = (PendingAuthorizationService)ApplicationContextProvider.getBean("pendingAuthorizationService");
			LetterOfCreditService letterOfCreditService =  (LetterOfCreditService)  ApplicationContextProvider.getBean("letterOfCreditService");
			EventLogging eventLogging = (EventLogging)ApplicationContextProvider.getBean("eventLogging");
			if(getSaveAction().equals("Approve")){
				 String returnString =letterOfCreditService.saveLC(lcCanonicalDto,null,"Amend_LC",userId,lcCanonicalDto.getRepair());
				if(returnString!=null && !returnString.equals("") ){
					pendingAuthorizationService.changeStatus(getSaveAction(), getHiddenTxnRef());
					eventLogging.doEventLogging(userId, "Amend LC", ConstantUtil.EVENTID_AMEND_LC+ConstantUtil.EVENTID_APPROVE, ConstantUtil.EVENTLOGGINGCOMMENTAPPROVAL,lcCanonicalDto.getLcNumber(),lcCanonicalDto.getMsgRef());
					if(StringUtils.isBlank(lcCanonicalDto.getRepair()) && StringUtils.isEmpty(lcCanonicalDto.getRepair())){
					}else{
					}
					return "success";	
				}else{
					addActionError("Unable to perform the operation. Please try again");
					return "input";
					}
				}else if(getSaveAction().equals("Reject"))
				{
					if(StringUtils.isBlank(lcCanonicalDto.getRepair()) && StringUtils.isEmpty(lcCanonicalDto.getRepair())){
						pendingAuthorizationService.changeStatus(getSaveAction(), getHiddenTxnRef());
						eventLogging.doEventLogging(userId, "Amend LC", ConstantUtil.EVENTID_AMEND_LC+ConstantUtil.EVENTID_REJECT, ConstantUtil.EVENTLOGGINGCOMMENTREJECT,lcCanonicalDto.getLcNumber(),lcCanonicalDto.getMsgRef());
					}
					else
					{
						PaymentMessageService paymentMessageService =(PaymentMessageService)ApplicationContextProvider.getBean("paymentMessageService");
						paymentMessageService.changeMsgStatus99to2(lcCanonicalDto.getMsgRef());
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
	
	
	
	public List getPositiveToleranceList() {
		return positiveToleranceList;
	}
	public void setPositiveToleranceList(List positiveToleranceList) {
		this.positiveToleranceList = positiveToleranceList;
		this.session.put("positiveToleranceList", positiveToleranceList);
	}
	public List getNegativeToleranceList() {
		return negativeToleranceList;
	}
	public void setNegativeToleranceList(List negativeToleranceList) {
		this.negativeToleranceList = negativeToleranceList;
		this.session.put("negativeToleranceList", negativeToleranceList);
	}

	private List negativeToleranceList  = new ArrayList();
	
	private String lcNumber;
	
	
	
	
	public Map<String, Object> getSession() {
		return session;
	}
	public void setSession(Map<String, Object> session) {
		this.session = session;		
	}

	public void setMobel(LCCanonicalDto connionicalDto)
	{
		 this.lcCanonicalDto=connionicalDto;
	}
	public LCCanonicalDto getModel() {
		
		return lcCanonicalDto;
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
	
	
	
	
	public InputStream getInputStream() {
		return inputStream;
	}
	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}
	public String getReportFile() {
		return reportFile;
	}
	public void setReportFile(String reportFile) {
		this.reportFile = reportFile;
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
		//this.session.put("currCodeDropDown", currCodeDropDown);
	}
	
	public void validate(){

        try {
              LetterOfCreditService letterOfCreditService = (LetterOfCreditService) ApplicationContextProvider.getBean("letterOfCreditService");
              if (StringUtils.isBlank(lcCanonicalDto.getRepair()) && StringUtils.isEmpty(lcCanonicalDto.getRepair())) {
                    if (lcCanonicalDto.getIssuingBankCode() != null && lcCanonicalDto.getAdvisingBank() != null) {
                          if (lcCanonicalDto.getIssuingBankCode().trim().equalsIgnoreCase(lcCanonicalDto.getAdvisingBank().trim())) {
                          }
                    }
              } else {
                    if (StringUtils.isNotBlank(lcCanonicalDto.getIssuingBankCode())
                                && StringUtils.isNotEmpty(lcCanonicalDto
                                            .getIssuingBankCode())
                                && lcCanonicalDto.getAdvisingBank() != null) {
                          if (lcCanonicalDto
                                      .getIssuingBankCode()
                                      .trim()
                                      .equalsIgnoreCase(
                                                  lcCanonicalDto.getAdvisingBank().trim())) {
                                addFieldError("advisingBank",
                                            "Reciver Bank IFSC Should Not Be Sender Bank IFSC");
                          }
                    }
              }
              if (letterOfCreditService.isLcNumberExist(lcCanonicalDto
                          .getLcNumber()) == false) {
              } else {
                    if (lcCanonicalDto.getLcNumber().startsWith("/")) {
                          addFieldError("lcNumber",
                                      "Sender's Reference(20) must not start with /");
                    } else if (lcCanonicalDto.getLcNumber().endsWith("/")) {
                          addFieldError("lcNumber",
                                      "Sender's Reference(20) must not End with /");
                          ;
                    } else if (lcCanonicalDto.getLcNumber().contains("//")) {
                          addFieldError("lcNumber","Sender's Reference(20) must not contain two consecutive slashes '//'");
                    }
              }
              if (!StringUtils.isBlank(lcCanonicalDto.getRepair())
                          && !StringUtils.isEmpty(lcCanonicalDto.getRepair())) {
                    PaymentMessageService paymentMessageService = (PaymentMessageService) ApplicationContextProvider
                                .getBean("paymentMessageService");
                    if (!paymentMessageService.checkInRptStatusIs2(lcCanonicalDto
                                .getMsgRef())) {
                          if (!(getSaveAction().equals("Approve") || getSaveAction()
                                      .equals("Reject")))
                                addFieldError("LcNumber",
                                            "Message Is Not In Valid State");
                    }
              }

              

              if (lcCanonicalDto.getSenderBankReference() != null
                          && StringUtils.isNotBlank(lcCanonicalDto
                                      .getSenderBankReference())
                          && StringUtils.isNotEmpty(lcCanonicalDto
                                      .getSenderBankReference())) {
                    if (StringUtils.isBlank(lcCanonicalDto
                                .getSendersCorrespondentPartyIdentifier())) {
                          addFieldError("senderBankReference",
                                      "If Issuing Bank's Reference(23) is present, Issuing Bank(52a) must also be present");
                    }
              }
              /*if (lcCanonicalDto.getSendersCorrespondentPartyIdentifier()
                          .equalsIgnoreCase("A")
                          && StringUtils.isBlank(lcCanonicalDto
                                      .getApplicantBankCode())
                          || lcCanonicalDto.getSendersCorrespondentPartyIdentifier()
                                      .equalsIgnoreCase("D")
                          && StringUtils.isBlank(lcCanonicalDto
                                      .getIssunigBankNameAndAddress())) {
                    addFieldError("sendersCorrespondentPartyIdentifier",
                                "If Issuing Bank's Reference(23) is present, Issuing Bank(52a) must also be present");
              }*/
              if(lcCanonicalDto.getSendersCorrespondentPartyIdentifier().equalsIgnoreCase("A"))
      		{
      			if(StringUtils.isBlank(lcCanonicalDto.getApplicantBankCode()) && StringUtils.isEmpty(lcCanonicalDto.getApplicantBankCode()))
      			{
      				addFieldError("applicantBankCode","Issuing Bank Code(52a) is Required ");
      			}
      			
      		}
              
      		if(lcCanonicalDto.getSendersCorrespondentPartyIdentifier().equalsIgnoreCase("D"))
      		{
      			
      			if(StringUtils.isBlank(lcCanonicalDto.getIssunigBankNameAndAddress()) && StringUtils.isEmpty(lcCanonicalDto.getIssunigBankNameAndAddress()))
      			{
      				addFieldError("issunigBankNameAndAddress","Issuing Bank Name and Address(52a) is Required ");
      			}
      			
      			
      		}
              
             /* //msgCurrency lcCurrency currency
              //1
              if (lcCanonicalDto.getCurrency()!=null && StringUtils.isNotEmpty(lcCanonicalDto.getCurrency()))
        	  {
              	if (lcCanonicalDto.getMsgCurrency()==null && StringUtils.isEmpty(lcCanonicalDto.getMsgCurrency())&&
              			lcCanonicalDto.getLcCurrency()==null && StringUtils.isEmpty(lcCanonicalDto.getLcCurrency()))
              	{
              		addFieldError("currency","If field 34B is present1, either field 32B or 33B must also be present");
              	}
              	if (lcCanonicalDto.getMsgCurrency()!=null && StringUtils.isNotEmpty(lcCanonicalDto.getMsgCurrency())&&
              			lcCanonicalDto.getLcCurrency()!=null && StringUtils.isNotEmpty(lcCanonicalDto.getLcCurrency()))
              	{
              		addFieldError("currency","If field 34B is present1, either field 32B or 33B must also be present but not both");
              	}
              	
        	  }
              if (StringUtils.isNotBlank(lcCanonicalDto.getCurrency()) && StringUtils.isNotEmpty(lcCanonicalDto.getCurrency()))
        	  {
              	if (StringUtils.isBlank(lcCanonicalDto.getMsgCurrency()) && StringUtils.isEmpty(lcCanonicalDto.getMsgCurrency())&&
              			StringUtils.isBlank(lcCanonicalDto.getLcCurrency()) && StringUtils.isEmpty(lcCanonicalDto.getLcCurrency()))
              	{
              		addFieldError("currency","If field 34B is present2, either field 32B or 33B must also be present");
              	}
              	if (StringUtils.isNotBlank(lcCanonicalDto.getMsgCurrency()) && StringUtils.isNotEmpty(lcCanonicalDto.getMsgCurrency())&&
              			StringUtils.isNotBlank(lcCanonicalDto.getLcCurrency()) && StringUtils.isNotEmpty(lcCanonicalDto.getLcCurrency()))
              	{
              		addFieldError("currency","If field 34B is present2, either field 32B or 33B must also be present but not both");
              	}
              	
        	  }
              if (lcCanonicalDto.getCurrency()!=null && StringUtils.isNotBlank(lcCanonicalDto.getCurrency()))
        	  {
              	if (lcCanonicalDto.getMsgCurrency()==null && StringUtils.isBlank(lcCanonicalDto.getMsgCurrency())&&
              			lcCanonicalDto.getLcCurrency()==null && StringUtils.isBlank(lcCanonicalDto.getLcCurrency()))
              	{
              		addFieldError("currency","If field 34B is present3, either field 32B or 33B must also be present");
              	}
              	if (lcCanonicalDto.getMsgCurrency()!=null && StringUtils.isNotBlank(lcCanonicalDto.getMsgCurrency())&&
              			lcCanonicalDto.getLcCurrency()!=null && StringUtils.isNotBlank(lcCanonicalDto.getLcCurrency()))
              	{
              		addFieldError("currency","If field 34B is present3, either field 32B or 33B must also be present but not both");
              	}
              	
        	  }
              //2
              if(lcCanonicalDto.getMsgCurrency()!=null && StringUtils.isNotEmpty(lcCanonicalDto.getMsgCurrency()))
              {
            	  if (lcCanonicalDto.getCurrency()==null && StringUtils.isEmpty(lcCanonicalDto.getCurrency()))
                  {
                    addFieldError("msgCurrency","If field 32B is present1, field 34B must also be present");
                  }
              }
              if(lcCanonicalDto.getMsgCurrency()!=null && StringUtils.isNotBlank(lcCanonicalDto.getMsgCurrency()))
              {
            	  if (lcCanonicalDto.getCurrency()==null && StringUtils.isBlank(lcCanonicalDto.getCurrency()))
                  {
                    addFieldError("msgCurrency","If field 32B is present2, field 34B must also be present");
                  }
              }
              if(StringUtils.isNotBlank(lcCanonicalDto.getMsgCurrency()) && StringUtils.isNotEmpty(lcCanonicalDto.getMsgCurrency()))
              {
            	  if (StringUtils.isBlank(lcCanonicalDto.getCurrency()) && StringUtils.isEmpty(lcCanonicalDto.getCurrency()))
                  {
                    addFieldError("msgCurrency","If field 32B is present3, field 34B must also be present");
                  }
              }
              //3	
              if(lcCanonicalDto.getLcCurrency()!=null && StringUtils.isNotEmpty(lcCanonicalDto.getLcCurrency()))
                  {
              		if (lcCanonicalDto.getCurrency()==null && StringUtils.isEmpty(lcCanonicalDto.getCurrency()))
                    { 
              		  addFieldError("currency","If field 33B is present1, field 34B must also be present");
                     }
                  }
              if(lcCanonicalDto.getLcCurrency()!=null && StringUtils.isNotBlank(lcCanonicalDto.getLcCurrency()))
              {
          		if (lcCanonicalDto.getCurrency()==null && StringUtils.isBlank(lcCanonicalDto.getCurrency()))
                { 
          		  addFieldError("currency","If field 33B is present2, field 34B must also be present");
                 }
              }
              if(StringUtils.isNotBlank(lcCanonicalDto.getLcCurrency()) && StringUtils.isNotEmpty(lcCanonicalDto.getLcCurrency()))
              {
          		if (StringUtils.isBlank(lcCanonicalDto.getCurrency()) && StringUtils.isEmpty(lcCanonicalDto.getCurrency()))
                { 
          		  addFieldError("currency","If field 33B is present3, field 34B must also be present");
                 }
              }
              //4	
              if (lcCanonicalDto.getCurrency()!=null && StringUtils.isNotEmpty(lcCanonicalDto.getCurrency()))
          	  {
              	if(lcCanonicalDto.getMsgCurrency()!=null && StringUtils.isNotEmpty(lcCanonicalDto.getMsgCurrency()))
                {
            		if(!lcCanonicalDto.getMsgCurrency().equalsIgnoreCase(lcCanonicalDto.getCurrency()))
                    {
            			addFieldError("msgCurrency","The currency code in the amount fields 32B and 34B must be the same1");
            		  }
                }
                }
              if (lcCanonicalDto.getCurrency()!=null && StringUtils.isNotBlank(lcCanonicalDto.getCurrency()))
          	  {
              	if(lcCanonicalDto.getMsgCurrency()!=null && StringUtils.isNotBlank(lcCanonicalDto.getMsgCurrency()))
                {
            		if(!lcCanonicalDto.getMsgCurrency().equalsIgnoreCase(lcCanonicalDto.getCurrency()))
                    {
            			addFieldError("msgCurrency","The currency code in the amount fields 32B and 34B must be the same2");
            		  }
                }
                }
              if (StringUtils.isNotBlank(lcCanonicalDto.getCurrency()) && StringUtils.isNotEmpty(lcCanonicalDto.getCurrency()))
          	  {
              	if(StringUtils.isNotBlank(lcCanonicalDto.getMsgCurrency()) && StringUtils.isNotEmpty(lcCanonicalDto.getMsgCurrency()))
                {
            		if(!lcCanonicalDto.getMsgCurrency().equalsIgnoreCase(lcCanonicalDto.getCurrency()))
                    {
            			addFieldError("msgCurrency","The currency code in the amount fields 32B and 34B must be the same3");
            		  }
                }
                }
              //5	
              if (lcCanonicalDto.getCurrency()!=null && StringUtils.isNotEmpty(lcCanonicalDto.getCurrency()))
          	  {
              	if (lcCanonicalDto.getLcCurrency()!=null && StringUtils.isNotEmpty(lcCanonicalDto.getLcCurrency()))
          	    {
         	           if(!lcCanonicalDto.getLcCurrency().equalsIgnoreCase(lcCanonicalDto.getCurrency()))
         	           {
                          addFieldError("lcCurrency","The currency code in the amount fields 33B and 34B must be the same1");
                       }
          	    }
          	  }
              if (lcCanonicalDto.getCurrency()!=null && StringUtils.isNotBlank(lcCanonicalDto.getCurrency()))
          	  {
              	if (lcCanonicalDto.getLcCurrency()!=null && StringUtils.isNotBlank(lcCanonicalDto.getLcCurrency()))
          	    {
         	           if(!lcCanonicalDto.getLcCurrency().equalsIgnoreCase(lcCanonicalDto.getCurrency()))
         	           {
                          addFieldError("lcCurrency","The currency code in the amount fields 33B and 34B must be the same2");
                       }
          	    }
          	  }
              if (StringUtils.isNotBlank(lcCanonicalDto.getCurrency()) && StringUtils.isNotEmpty(lcCanonicalDto.getCurrency()))
          	  {
              	if (StringUtils.isNotBlank(lcCanonicalDto.getLcCurrency()) && StringUtils.isNotEmpty(lcCanonicalDto.getLcCurrency()))
          	    {
         	           if(!lcCanonicalDto.getLcCurrency().equalsIgnoreCase(lcCanonicalDto.getCurrency()))
         	           {
                          addFieldError("lcCurrency","The currency code in the amount fields 33B and 34B must be the same3");
                       }
          	    }
          	  }*/
              if (lcCanonicalDto.getNewAmendExpiryDate() == null)
              {
                    if(StringUtils.isBlank(lcCanonicalDto.getMsgCurrency())&& StringUtils.isEmpty(lcCanonicalDto.getMsgCurrency())&&
                       StringUtils.isBlank(lcCanonicalDto.getLcCurrency())&& StringUtils.isEmpty(lcCanonicalDto.getLcCurrency())&&
                       StringUtils.isBlank(lcCanonicalDto.getCurrency()) && StringUtils.isEmpty(lcCanonicalDto.getCurrency()) &&
                       StringUtils.isBlank(lcCanonicalDto.getPositiveTolerance())&& StringUtils.isEmpty(lcCanonicalDto.getPositiveTolerance())&&
                       StringUtils.isBlank(lcCanonicalDto.getNegativeTolerance())&& StringUtils.isEmpty(lcCanonicalDto.getNegativeTolerance())&&
                       StringUtils.isBlank(lcCanonicalDto.getMaximumCreditAmount())&&StringUtils.isEmpty(lcCanonicalDto.getMaximumCreditAmount())&&
                       StringUtils.isBlank(lcCanonicalDto.getAdditionalAmountsCovered())&& StringUtils.isEmpty(lcCanonicalDto.getAdditionalAmountsCovered())&&
                       StringUtils.isBlank(lcCanonicalDto.getInitialDispatchPlace())&&StringUtils.isEmpty(lcCanonicalDto.getInitialDispatchPlace())&&
                       StringUtils.isBlank(lcCanonicalDto.getGoodsLoadingDispatchPlace())&&StringUtils.isEmpty(lcCanonicalDto.getGoodsLoadingDispatchPlace())&&
                       StringUtils.isBlank(lcCanonicalDto.getGoodsDestination())&&StringUtils.isEmpty(lcCanonicalDto.getGoodsDestination())&&
                       StringUtils.isBlank(lcCanonicalDto.getFinalDeliveryPlace())&&StringUtils.isEmpty(lcCanonicalDto.getFinalDeliveryPlace())&&
                       lcCanonicalDto.getLatestDateofShipment() == null &&
                       StringUtils.isBlank(lcCanonicalDto.getShipmentPeriod())&&StringUtils.isEmpty(lcCanonicalDto.getShipmentPeriod())&&
                       StringUtils.isBlank(lcCanonicalDto.getNarrative())&&StringUtils.isEmpty(lcCanonicalDto.getNarrative())&&
                    StringUtils.isBlank(lcCanonicalDto.getSendertoReceiverInformation())&&StringUtils.isEmpty(lcCanonicalDto.getSendertoReceiverInformation()))
                    {
                        addFieldError("newAmendExpiryDate",
                                      "At least one of the fields 31E, 32B, 33B, 34B, 39A, 39B, 39C, 44A, 44E, 44F, 44B, 44C, 44D, 79 or 72 must be present");
                     }
                 } 
              /*if (lcCanonicalDto.getCurrency()==null && StringUtils.isEmpty(lcCanonicalDto.getCurrency()))
               {
            	  if(lcCanonicalDto.getMsgCurrency()!=null && StringUtils.isNotEmpty(lcCanonicalDto.getMsgCurrency())||
                    		lcCanonicalDto.getLcCurrency()!=null && StringUtils.isNotEmpty(lcCanonicalDto.getLcCurrency()))
                    
                    {
                          addFieldError("currency","If either field 32B or 33B is present, field 34B must also be present");
                    }
              }
              if (lcCanonicalDto.getCurrency()!=null &&  StringUtils.isNotEmpty(lcCanonicalDto.getCurrency()))
              {
                    if(lcCanonicalDto.getMsgCurrency()==null &&  StringUtils.isEmpty(lcCanonicalDto.getMsgCurrency())&&
                  		  lcCanonicalDto.getLcCurrency()==null &&  StringUtils.isEmpty(lcCanonicalDto.getLcCurrency()))
                     {
                  		 
                               addFieldError("currency","If field 34B is present, either field 32B or 33B must also be present");
                              
                     }
                    if(lcCanonicalDto.getMsgCurrency()!=null && StringUtils.isNotEmpty(lcCanonicalDto.getMsgCurrency())||
                    		lcCanonicalDto.getLcCurrency()!=null && StringUtils.isNotEmpty(lcCanonicalDto.getLcCurrency()))
                    else
                 {
              		if(!lcCanonicalDto.getMsgCurrency().equalsIgnoreCase(lcCanonicalDto.getCurrency())||
              				!lcCanonicalDto.getLcCurrency().equalsIgnoreCase(lcCanonicalDto.getCurrency()))
      				{

              	       addFieldError("currency","The currency code in the amount fields 32B, 33B and 34B must be the same");
                    }
                 }
               }*/
             /* if(lcCanonicalDto.getMsgCurrency()!=null && StringUtils.isNotEmpty(lcCanonicalDto.getMsgCurrency()))
      		{
      			if(lcCanonicalDto.getCurrency()!=null && StringUtils.isNotEmpty(lcCanonicalDto.getCurrency()))
      			{
      				if(!lcCanonicalDto.getMsgCurrency().equalsIgnoreCase(lcCanonicalDto.getCurrency()))
      				{
      					addFieldError("lcCurrency","The currency code in the amount fields 32B and 34B must be the same");
      				}
      			}
      		}*/
             /* if(lcCanonicalDto.getCurrency()!=null && StringUtils.isNotEmpty(lcCanonicalDto.getCurrency()))
              {
            	  if(lcCanonicalDto.getMsgCurrency()!=null && StringUtils.isNotEmpty(lcCanonicalDto.getMsgCurrency())||
                    		lcCanonicalDto.getLcCurrency()!=null && StringUtils.isNotEmpty(lcCanonicalDto.getLcCurrency()))
                 {
              		if(!lcCanonicalDto.getMsgCurrency().equalsIgnoreCase(lcCanonicalDto.getCurrency())||
              				!lcCanonicalDto.getLcCurrency().equalsIgnoreCase(lcCanonicalDto.getCurrency()))
      				{

              	       addFieldError("currency","The currency code in the amount fields 32B, 33B and 34B must be the same");
                    }
                 }
              }*/
              currency();
              networkValdation();
              validateTolerance();
        } catch (Exception e) {
              addActionError("Unable To process.");
        }

        

  }
public void currency()
{
	if(lcCanonicalDto.getMsgCurrency()!=null && StringUtils.isNotEmpty(lcCanonicalDto.getMsgCurrency()))
		//&& StringUtils.isNotBlank(lcCanonicalDto.getMsgCurrency()))
    { 
	   if (lcCanonicalDto.getCurrency()==null && StringUtils.isEmpty(lcCanonicalDto.getCurrency()))// && StringUtils.isBlank(lcCanonicalDto.getCurrency()))
       {  
		   addFieldError("msgCurrency","If field 32B is present, field 34B must also be present");
       }
	   
	   
			if(lcCanonicalDto.getCurrency()!=null && StringUtils.isNotEmpty(lcCanonicalDto.getCurrency()))
			{
				if(!lcCanonicalDto.getMsgCurrency().equalsIgnoreCase(lcCanonicalDto.getCurrency()))
				{
					addFieldError("lcCurrency","The currency code in the amount fields 32B and 34B must be the same");
				}
			}
		
	  /* if (lcCanonicalDto.getCurrency()!=null && StringUtils.isNotEmpty(lcCanonicalDto.getCurrency()) && StringUtils.isNotBlank(lcCanonicalDto.getCurrency()))
          {
    		if(lcCanonicalDto.getLcCurrency()==null && StringUtils.isEmpty(lcCanonicalDto.getLcCurrency()))
            {  
    			if(!lcCanonicalDto.getMsgCurrency().equalsIgnoreCase(lcCanonicalDto.getCurrency()))
                      {
                         addFieldError("msgCurrency","The currency code in the amount fields 32B and 34B must be the same");
                      }
             }
          }*/
      }
    
    if(lcCanonicalDto.getLcCurrency()!=null && StringUtils.isNotEmpty(lcCanonicalDto.getLcCurrency()) && StringUtils.isNotBlank(lcCanonicalDto.getLcCurrency()))
    {
    	if (lcCanonicalDto.getCurrency()==null && StringUtils.isEmpty(lcCanonicalDto.getCurrency()) && StringUtils.isBlank(lcCanonicalDto.getCurrency()))
        {
    		addFieldError("lcCurrency","If field 33B is present, field 34B must also be present"); 
        }
    	 
    	 if (lcCanonicalDto.getCurrency()!=null && StringUtils.isNotEmpty(lcCanonicalDto.getCurrency()) && StringUtils.isNotBlank(lcCanonicalDto.getCurrency()))
          {
    		 if(lcCanonicalDto.getMsgCurrency()==null && StringUtils.isEmpty(lcCanonicalDto.getMsgCurrency()))
             { 
    			 if(!lcCanonicalDto.getLcCurrency().equalsIgnoreCase(lcCanonicalDto.getCurrency()))
    			 {
    				 addFieldError("lcCurrency","If field 33B is present, field 34B must also be present The currency code in the amount fields 33B and 34B must be the same");
    			 }          	  
             } 
          }
    }
    
    if (lcCanonicalDto.getCurrency()!=null && StringUtils.isNotEmpty(lcCanonicalDto.getCurrency())&& StringUtils.isNotBlank(lcCanonicalDto.getCurrency()))
    {
          if(lcCanonicalDto.getMsgCurrency()==null && StringUtils.isEmpty(lcCanonicalDto.getMsgCurrency()) && StringUtils.isBlank(lcCanonicalDto.getMsgCurrency()) &&
                      lcCanonicalDto.getLcCurrency()==null && StringUtils.isEmpty(lcCanonicalDto.getLcCurrency()) && StringUtils.isBlank(lcCanonicalDto.getLcCurrency()))
          {
          addFieldError("currency","If field 34B is present, either field 32B or 33B must also be present");
        }
          if(lcCanonicalDto.getMsgCurrency()==null && StringUtils.isEmpty(lcCanonicalDto.getMsgCurrency()) && StringUtils.isBlank(lcCanonicalDto.getMsgCurrency()) ||
                  lcCanonicalDto.getLcCurrency()==null && StringUtils.isEmpty(lcCanonicalDto.getLcCurrency()) && StringUtils.isBlank(lcCanonicalDto.getLcCurrency()))
      {
      addFieldError("currency","If field 34B is present2, either field 32B or 33B must also be present");
    }
           /* if(lcCanonicalDto.getMsgCurrency()!=null && StringUtils.isNotEmpty(lcCanonicalDto.getMsgCurrency()))
            {
                if(lcCanonicalDto.getLcCurrency()!=null && StringUtils.isNotEmpty(lcCanonicalDto.getLcCurrency()))
              {
                addFieldError("currency","If field 34B is present, either field 32B or 33B must also be present not both");
             }
            }*/
    }
}

  public void validateTolerance() {
        String tolerance = null;
        String mxdCrdAmt = null;
        tolerance = lcCanonicalDto.getPositiveTolerance()
                    + lcCanonicalDto.getNegativeTolerance();
        mxdCrdAmt = lcCanonicalDto.getMaximumCreditAmount();
        if (StringUtils.isNotBlank(tolerance)
                    && StringUtils.isNotEmpty(tolerance) && tolerance != null) {
              if (StringUtils.isNotBlank(mxdCrdAmt)
                          && StringUtils.isNotEmpty(mxdCrdAmt) && mxdCrdAmt != null) {
                    addFieldError("maximumCreditAmount",
                                "Either field 39A or 39B, but not both, may be present");
              }
        }

  }

  public void networkValdation() {
        if (lcCanonicalDto.getLatestDateofShipment() != null) {
              if (StringUtils.isNotBlank(lcCanonicalDto.getShipmentPeriod())
                          && StringUtils.isNotEmpty(lcCanonicalDto
                                      .getShipmentPeriod())
                          && lcCanonicalDto.getShipmentPeriod() != null) {
                    addFieldError("shipmentPeriod",
                                "Either field 44C or 44D, but not both, may be present");
              }
        }
  }
       
              
           
   /*if(lcCanonicalDto.getMsgCurrency()!=null && StringUtils.isNotEmpty(lcCanonicalDto.getMsgCurrency()))
        {
	         if(lcCanonicalDto.getCurrency()==null && StringUtils.isEmpty(lcCanonicalDto.getCurrency()))
	         {
	        	 addFieldError("msgCurrency","If field 32B is present, field 34B must also be present");
              }
	         if(lcCanonicalDto.getCurrency()!=null && StringUtils.isNotEmpty(lcCanonicalDto.getCurrency()))
	         {
	        	 if(!lcCanonicalDto.getMsgCurrency().equalsIgnoreCase(lcCanonicalDto.getCurrency()))
                 {
                    addFieldError("msgCurrency","The currency code in the amount fields 32B and 34B must be the same");
                 }
	        	 
              }
        }
   if(lcCanonicalDto.getLcCurrency()!=null && StringUtils.isNotEmpty(lcCanonicalDto.getLcCurrency()))
   {
	   if(lcCanonicalDto.getCurrency()==null && StringUtils.isEmpty(lcCanonicalDto.getCurrency()))
       {
      	 addFieldError("lcCurrency","If field 33B is present, field 34B must also be present");
        }
	   if(lcCanonicalDto.getCurrency()!=null && StringUtils.isNotEmpty(lcCanonicalDto.getCurrency()))
       {
      	 if(!lcCanonicalDto.getLcCurrency().equalsIgnoreCase(lcCanonicalDto.getCurrency()))
           {
              addFieldError("lcCurrency","The currency code in the amount fields 32B and 34B must be the same");
           }
      	 
        } 
   }*/
 
        
        /*if(lcCanonicalDto.getLcCurrency()!=null && StringUtils.isNotEmpty(lcCanonicalDto.getLcCurrency()))
        {
              if (lcCanonicalDto.getCurrency()==null && StringUtils.isEmpty(lcCanonicalDto.getCurrency()))
              {
                    addFieldError("currency","If field 33B is present, field 34B must also be present");
              }
        }
        if(lcCanonicalDto.getCurrency()!=null && StringUtils.isNotEmpty(lcCanonicalDto.getCurrency()))
              {
    	            if(lcCanonicalDto.getMsgCurrency()!=null && StringUtils.isNotEmpty(lcCanonicalDto.getMsgCurrency()))
                      {
    	   				if(!lcCanonicalDto.getCurrency().equalsIgnoreCase(lcCanonicalDto.getMsgCurrency()))
                          {
                             addFieldError("msgCurrency","The currency code in the amount fields 32B and 34B must be the same");
                          }
                      }
              }
       
        
        
        if(lcCanonicalDto.getCurrency()!=null && StringUtils.isNotEmpty(lcCanonicalDto.getCurrency()))
        {
	            if(lcCanonicalDto.getLcCurrency()!=null && StringUtils.isNotEmpty(lcCanonicalDto.getLcCurrency()))
                {
	   				if(!lcCanonicalDto.getCurrency().equalsIgnoreCase(lcCanonicalDto.getMsgCurrency()))
                    {
                       addFieldError("msgCurrency","The currency code in the amount fields 33B and 34B must be the same");
                    }
                }
        }*/
        
        
         /*if (StringUtils.isNotBlank(lcCanonicalDto.getCurrency()) && StringUtils.isNotEmpty(lcCanonicalDto.getCurrency()))
              {
                if(StringUtils.isNotBlank(lcCanonicalDto.getMsgCurrency()) && StringUtils.isNotEmpty(lcCanonicalDto.getMsgCurrency()))
                {
                    if(StringUtils.isNotBlank(lcCanonicalDto.getLcCurrency())&& StringUtils.isNotEmpty(lcCanonicalDto.getLcCurrency()))
                  {
                    addFieldError("currency","If field 34B is present2, either field 32B or 33B must also be present not both");
                 }
                }
              }*/
       
        /*if(lcCanonicalDto.getMsgCurrency()!=null && StringUtils.isNotEmpty(lcCanonicalDto.getMsgCurrency()))
        {
              if (lcCanonicalDto.getCurrency()==null && StringUtils.isEmpty(lcCanonicalDto.getCurrency()))
              {
                    addFieldError("msgCurrency","If field 32B is present, field 34B must also be present");
              }

              else 
       	   {
            	  if (lcCanonicalDto.getCurrency()!=null && StringUtils.isNotEmpty(lcCanonicalDto.getCurrency()))
            	  {
       	   
            	  if(!lcCanonicalDto.getMsgCurrency().equalsIgnoreCase(lcCanonicalDto.getCurrency()))
                             {
                                addFieldError("msgCurrency","The currency code in the amount fields 32B and 34B must be the same");
                             }
            	  }
                 }
                 
        }*/
       /*if(lcCanonicalDto.getMsgCurrency()!=null && StringUtils.isNotEmpty(lcCanonicalDto.getMsgCurrency()))
              {
    	   if (lcCanonicalDto.getCurrency()!=null && StringUtils.isNotEmpty(lcCanonicalDto.getCurrency()))
    	   {
    	   if(!lcCanonicalDto.getCurrency().equalsIgnoreCase(lcCanonicalDto.getMsgCurrency()))
                          {
                             addFieldError("msgCurrency","The currency code in the amount fields 32B and 34B must be the same");
                          }
              }
              }*/
       
        
        /*if(lcCanonicalDto.getLcCurrency()!=null && StringUtils.isNotEmpty(lcCanonicalDto.getLcCurrency()))
        {
              if (lcCanonicalDto.getCurrency()==null && StringUtils.isEmpty(lcCanonicalDto.getCurrency()))
              {
                    addFieldError("currency","If field 33B is present, field 34B must also be present");
              }
              else 
          	   {
            	  if (lcCanonicalDto.getCurrency()!=null && StringUtils.isNotEmpty(lcCanonicalDto.getCurrency()))
            	  {
           	  if(!lcCanonicalDto.getLcCurrency().equalsIgnoreCase(lcCanonicalDto.getCurrency()))
                         {
                            addFieldError("lcCurrency","The currency code in the amount fields 33B and 34B must be the same");
                         }
            	  }
          	   }
        }*/
             /* if(lcCanonicalDto.getLcCurrency()!=null && StringUtils.isNotEmpty(lcCanonicalDto.getLcCurrency()))
              {
            	  if (lcCanonicalDto.getCurrency()!=null && StringUtils.isNotEmpty(lcCanonicalDto.getCurrency()))
           	   {
            	  if(!lcCanonicalDto.getCurrency().equalsIgnoreCase(lcCanonicalDto.getLcCurrency()))
                          {
                             addFieldError("lcCurrency","The currency code in the amount fields 33B and 34B must be the same");
                          }
           	   }
              }*/
        
        
       /* if (lcCanonicalDto.getCurrency()!=null && StringUtils.isNotEmpty(lcCanonicalDto.getCurrency()))
        {
              if(lcCanonicalDto.getMsgCurrency()!=null && StringUtils.isEmpty(lcCanonicalDto.getMsgCurrency()) &&
            		  lcCanonicalDto.getLcCurrency()!=null&& StringUtils.isEmpty(lcCanonicalDto.getLcCurrency()))
              {
              addFieldError("currency","If field 34B is present1, either field 32B or 33B must also be present");
              }
         } */      
        



		
	
	private Timestamp getCurrentTime(){
		java.util.Date 	str_date = Calendar.getInstance().getTime();
		java.sql.Timestamp timeStampDate = new Timestamp(str_date.getTime());
		return timeStampDate;
	}
	
	@SkipValidation
	public String lcAmend()
	{
		try{
		displayAmendLC();
		String msgRef = (String) session.get("messageIndex");
		LetterOfCreditService letterOfCreditService =  (LetterOfCreditService)  ApplicationContextProvider.getBean("letterOfCreditService");
		LCCanonicalDto canonicalDto = letterOfCreditService.getPreAdviceRepair(msgRef);
		if(canonicalDto.getLcNumber()!=null && StringUtils.isNotBlank(canonicalDto.getLcNumber()) && StringUtils.isNotEmpty(canonicalDto.getLcNumber())){
			canonicalDto.setRepair(ConstantUtil.REPAIR);
			setALLValueTODTO(canonicalDto);
			 /*session.remove("isRepairScreen");
			setIsRepairScreen("TRUE");
			 session.put("isRepairScreen", isRepairScreen);*/
		}
		else
		{
			addFieldError("paymentMessageType",ConstantUtil.ERRORMESSAGE);
			return "failure";
		}
		return INPUT;
		}catch(Exception exception)
		{
			exception.printStackTrace();
			addFieldError("paymentMessageType",ConstantUtil.ERRORMESSAGE);
			return "failure";
		
		}
	}
	private void setALLValueTODTO(LCCanonicalDto obj)
	{
		
		lcCanonicalDto.setLcNumber(obj.getLcNumber());
		lcCanonicalDto.setReceiverBankReference(obj.getReceiverBankReference());
		lcCanonicalDto.setSenderBankReference(obj.getSenderBankReference());
		lcCanonicalDto.setSendersCorrespondentPartyIdentifier(obj.getSendersCorrespondentPartyIdentifier());
		lcCanonicalDto.setApplicantBankCode(obj.getApplicantBankCode());
		lcCanonicalDto.setIssunigBankNameAndAddress(obj.getIssunigBankNameAndAddress());
		lcCanonicalDto.setIssueDate(obj.getIssueDate());
		lcCanonicalDto.setAmendmentDate(obj.getAmendmentDate());
		lcCanonicalDto.setLcAmndmntNo(obj.getLcAmndmntNo());
		//lcCanonicalDto.setBeneficiaryAccount(obj.getBeneficiaryAccount());
		lcCanonicalDto.setBeneficiaryNameAddress(obj.getBeneficiaryNameAddress());
		lcCanonicalDto.setIncreaseAmendAmount(obj.getIncreaseAmendAmount());
		lcCanonicalDto.setDecreaseAmendAmount(obj.getDecreaseAmendAmount());
		lcCanonicalDto.setNewAmendExpiryDate(obj.getNewAmendExpiryDate());
		lcCanonicalDto.setNewLcAmount(obj.getNewLcAmount());	
		lcCanonicalDto.setPositiveTolerance(obj.getPositiveTolerance());
        lcCanonicalDto.setNegativeTolerance(obj.getNegativeTolerance());
        lcCanonicalDto.setMaximumCreditAmount(obj.getMaximumCreditAmount());
        lcCanonicalDto.setAdditionalAmountsCovered(obj.getAdditionalAmountsCovered());
        lcCanonicalDto.setGoodsLoadingDispatchPlace(obj.getGoodsLoadingDispatchPlace());
        lcCanonicalDto.setGoodsDestination(obj.getGoodsDestination());
        lcCanonicalDto.setInitialDispatchPlace(obj.getInitialDispatchPlace());
        lcCanonicalDto.setFinalDeliveryPlace(obj.getFinalDeliveryPlace());
        lcCanonicalDto.setLatestDateofShipment(obj.getLatestDateofShipment());
        lcCanonicalDto.setShipmentPeriod(obj.getShipmentPeriod());
        lcCanonicalDto.setNarrative(obj.getNarrative());
        lcCanonicalDto.setSendertoReceiverInformation(obj.getSendertoReceiverInformation());
        lcCanonicalDto.setIssuingBankCode(obj.getIssuingBankCode());
    	lcCanonicalDto.setAdvisingBank(obj.getAdvisingBank());
    	lcCanonicalDto.setMsgRef(obj.getMsgRef());
    	lcCanonicalDto.setRepair(obj.getRepair());
    	lcCanonicalDto.setSeqNo(obj.getSeqNo());
		lcCanonicalDto.setMsgHost(obj.getMsgHost());
		lcCanonicalDto.setMsgCurrency(obj.getMsgCurrency());
    	lcCanonicalDto.setLcCurrency(obj.getLcCurrency());
    	lcCanonicalDto.setCurrency(obj.getCurrency());
			
		 session.put("ScreenData", lcCanonicalDto);
		
	}
	
	
	
public String saveAmendLCTemplate() {
				
			try {			
				String tempRefKey="";
				PendingAuthorizationService pendingAuthorizationService = (PendingAuthorizationService)ApplicationContextProvider.getBean("pendingAuthorizationService");
				String userId = (String)session.get(WebConstants.CONSTANT_USER_NAME);
				System.out.println("Template Name is "+tempName);
				tempRefKey = pendingAuthorizationService.getTempRef(serializeObject(),"707","XXX",tempName,"Amend LC(MT-707)",userId);
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
public String viewTempalteMessage()
{
	try{
	displayAmendLC();
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
		exception.printStackTrace();
		addFieldError("paymentMessageType",ConstantUtil.ERRORMESSAGE);
		return "failure";
	
	}
}

	@SkipValidation
	public String displayPrintPreviewAmendLCPage()
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
					
					lcCanonicalDto.setLcNumber(temp.getLcNumber());
					lcCanonicalDto.setReceiverBankReference(temp.getReceiverBankReference());
					lcCanonicalDto.setSenderBankReference(temp.getSenderBankReference());
					lcCanonicalDto.setSendersCorrespondentPartyIdentifier(temp.getSendersCorrespondentPartyIdentifier());
					lcCanonicalDto.setApplicantBankCode(temp.getApplicantBankCode());
					lcCanonicalDto.setIssunigBankNameAndAddress(temp.getIssunigBankNameAndAddress());
					lcCanonicalDto.setIssueDate(temp.getIssueDate());
					lcCanonicalDto.setAmendmentDate(temp.getAmendmentDate());
					lcCanonicalDto.setLcAmndmntNo(temp.getLcAmndmntNo());
					//lcCanonicalDto.setBeneficiaryAccount(temp.getBeneficiaryAccount());
					lcCanonicalDto.setBeneficiaryNameAddress(temp.getBeneficiaryNameAddress());
					lcCanonicalDto.setIncreaseAmendAmount(temp.getIncreaseAmendAmount());
					lcCanonicalDto.setDecreaseAmendAmount(temp.getDecreaseAmendAmount());
					lcCanonicalDto.setNewAmendExpiryDate(temp.getNewAmendExpiryDate());
					lcCanonicalDto.setNewLcAmount(temp.getNewLcAmount());	
					lcCanonicalDto.setPositiveTolerance(temp.getPositiveTolerance());
			        lcCanonicalDto.setNegativeTolerance(temp.getNegativeTolerance());
			        lcCanonicalDto.setMaximumCreditAmount(temp.getMaximumCreditAmount());
			        lcCanonicalDto.setAdditionalAmountsCovered(temp.getAdditionalAmountsCovered());
			        lcCanonicalDto.setGoodsLoadingDispatchPlace(temp.getGoodsLoadingDispatchPlace());
			        lcCanonicalDto.setGoodsDestination(temp.getGoodsDestination());
			        lcCanonicalDto.setInitialDispatchPlace(temp.getInitialDispatchPlace());
			        lcCanonicalDto.setFinalDeliveryPlace(temp.getFinalDeliveryPlace());
			        lcCanonicalDto.setLatestDateofShipment(temp.getLatestDateofShipment());
			        lcCanonicalDto.setShipmentPeriod(temp.getShipmentPeriod());
			        lcCanonicalDto.setNarrative(temp.getNarrative());
			        lcCanonicalDto.setSendertoReceiverInformation(temp.getSendertoReceiverInformation());
			        lcCanonicalDto.setIssuingBankCode(temp.getIssuingBankCode());
			    	lcCanonicalDto.setAdvisingBank(temp.getAdvisingBank());
			    	lcCanonicalDto.setMsgCurrency(temp.getMsgCurrency());
			    	lcCanonicalDto.setLcCurrency(temp.getLcCurrency());
			    	lcCanonicalDto.setCurrency(temp.getCurrency());
			    	
			    	setPrintPreviewTxnRef(getHiddenTxnRef());
				}
				else if(msgRef!=null && !msgRef.isEmpty())
				{
					lcAmend();
					return "success";
				}
				else
				{
					lcCanonicalDto.setLcNumber(lcCanonicalDto.getLcNumber());
					lcCanonicalDto.setReceiverBankReference(lcCanonicalDto.getReceiverBankReference());
					lcCanonicalDto.setSenderBankReference(lcCanonicalDto.getSenderBankReference());
					lcCanonicalDto.setSendersCorrespondentPartyIdentifier(lcCanonicalDto.getSendersCorrespondentPartyIdentifier());
					lcCanonicalDto.setApplicantBankCode(lcCanonicalDto.getApplicantBankCode());
					lcCanonicalDto.setIssunigBankNameAndAddress(lcCanonicalDto.getIssunigBankNameAndAddress());
					lcCanonicalDto.setIssueDate(lcCanonicalDto.getIssueDate());
					lcCanonicalDto.setAmendmentDate(lcCanonicalDto.getAmendmentDate());
					lcCanonicalDto.setLcAmndmntNo(lcCanonicalDto.getLcAmndmntNo());
					//lcCanonicalDto.setBeneficiaryAccount(lcCanonicalDto.getBeneficiaryAccount());
					lcCanonicalDto.setBeneficiaryNameAddress(lcCanonicalDto.getBeneficiaryNameAddress());
					lcCanonicalDto.setIncreaseAmendAmount(lcCanonicalDto.getIncreaseAmendAmount());
					lcCanonicalDto.setDecreaseAmendAmount(lcCanonicalDto.getDecreaseAmendAmount());
					lcCanonicalDto.setNewAmendExpiryDate(lcCanonicalDto.getNewAmendExpiryDate());
					lcCanonicalDto.setNewLcAmount(lcCanonicalDto.getNewLcAmount());	
					lcCanonicalDto.setPositiveTolerance(lcCanonicalDto.getPositiveTolerance());
			        lcCanonicalDto.setNegativeTolerance(lcCanonicalDto.getNegativeTolerance());
			        lcCanonicalDto.setMaximumCreditAmount(lcCanonicalDto.getMaximumCreditAmount());
			        lcCanonicalDto.setAdditionalAmountsCovered(lcCanonicalDto.getAdditionalAmountsCovered());
			        lcCanonicalDto.setGoodsLoadingDispatchPlace(lcCanonicalDto.getGoodsLoadingDispatchPlace());
			        lcCanonicalDto.setGoodsDestination(lcCanonicalDto.getGoodsDestination());
			        lcCanonicalDto.setInitialDispatchPlace(lcCanonicalDto.getInitialDispatchPlace());
			        lcCanonicalDto.setFinalDeliveryPlace(lcCanonicalDto.getFinalDeliveryPlace());
			        lcCanonicalDto.setLatestDateofShipment(lcCanonicalDto.getLatestDateofShipment());
			        lcCanonicalDto.setShipmentPeriod(lcCanonicalDto.getShipmentPeriod());
			        lcCanonicalDto.setNarrative(lcCanonicalDto.getNarrative());
			        lcCanonicalDto.setSendertoReceiverInformation(lcCanonicalDto.getSendertoReceiverInformation());
			        lcCanonicalDto.setIssuingBankCode(lcCanonicalDto.getIssuingBankCode());
			    	lcCanonicalDto.setAdvisingBank(lcCanonicalDto.getAdvisingBank());
			    	lcCanonicalDto.setMsgCurrency(lcCanonicalDto.getMsgCurrency());
			    	lcCanonicalDto.setLcCurrency(lcCanonicalDto.getLcCurrency());
			    	lcCanonicalDto.setCurrency(lcCanonicalDto.getCurrency());
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
			lcCanonicalDto.setLcNumber("");
			lcCanonicalDto.setReceiverBankReference("");
			lcCanonicalDto.setSenderBankReference("");
			lcCanonicalDto.setSendersCorrespondentPartyIdentifier("");
			lcCanonicalDto.setApplicantBankCode("");
			lcCanonicalDto.setIssunigBankNameAndAddress("");
			lcCanonicalDto.setIssueDate(null);
			lcCanonicalDto.setAmendmentDate(null);
			lcCanonicalDto.setLcAmndmntNo(0);
			//lcCanonicalDto.setBeneficiaryAccount("");
			lcCanonicalDto.setIncreaseAmendAmount(BigDecimal.ZERO);
			lcCanonicalDto.setDecreaseAmendAmount(BigDecimal.ZERO);
			lcCanonicalDto.setNewAmendExpiryDate(null);
			lcCanonicalDto.setNewLcAmount(BigDecimal.ZERO);	
			lcCanonicalDto.setPositiveTolerance("");
	        lcCanonicalDto.setNegativeTolerance("");
	        lcCanonicalDto.setMaximumCreditAmount("");
	        lcCanonicalDto.setAdditionalAmountsCovered("");
	        lcCanonicalDto.setGoodsLoadingDispatchPlace("");
	        lcCanonicalDto.setGoodsDestination("");
	        lcCanonicalDto.setInitialDispatchPlace("");
	        lcCanonicalDto.setFinalDeliveryPlace("");
	        lcCanonicalDto.setLatestDateofShipment(null);
	        lcCanonicalDto.setShipmentPeriod("");
	        lcCanonicalDto.setNarrative("");
	        lcCanonicalDto.setSendertoReceiverInformation("");
	        lcCanonicalDto.setIssuingBankCode("");
	    	lcCanonicalDto.setAdvisingBank("");
	    	lcCanonicalDto.setMsgCurrency("");
	    	lcCanonicalDto.setLcCurrency("");
	    	lcCanonicalDto.setCurrency("");
		return "success";
		}catch (Exception e) {
			logger.error(e,e);
			return "input";
		}

	}
	
	
	@SkipValidation
	public String exportToExcelAmendLC() throws Exception
	{		
	try{
		displayPrintPreviewAmendLCPage();
		String reportFile = "Amend_LC_(MT-707)";
		SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyyHHmmss");
		String currentDateTime = sdf.format(new Date());
		String reportFileName = reportFile+"_"+currentDateTime+"".concat(".xls");
		setReportFile(reportFileName);
		System.out.println("reportFileName is "+reportFileName);
		ExportToExcelUtil exportToExcelUtil = new ExportToExcelUtil();
		HSSFWorkbook myWorkBook = exportToExcelUtil.generateExportToExcel(lcCanonicalDto, null, null, reportFile, 707, "XXX");
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
	public String generatePDFAmendLC()
	{

		displayPrintPreviewAmendLCPage();
		String reportName ="Amend LC(MT-707)";
		Map<String,String> fieldNamesMap = new HashMap<String,String>();
		
		try{
			
			fieldNamesMap.put("label.MB.IssuingBranchIFSC", getText("label.MB.IssuingBranchIFSC"));
			fieldNamesMap.put("label.MB.Benefifsc", getText("label.MB.Benefifsc"));
			
			fieldNamesMap.put("label.707.ppSenderBanksReference", getText("label.707.ppSenderBanksReference"));
			fieldNamesMap.put("label.707.ppReceiverBanksReference", getText("label.707.ppReceiverBanksReference"));
			fieldNamesMap.put("label.707.ppIssuingBankReference", getText("label.707.ppIssuingBankReference"));
			fieldNamesMap.put("label.707.ppIssuingBankPartyIdentifier", getText("label.707.ppIssuingBankPartyIdentifier"));
			fieldNamesMap.put("label.707.ppIssuingBankCode", getText("label.707.ppIssuingBankCode"));
			fieldNamesMap.put("label.707.ppIssuingBankNameandAddress", getText("label.707.ppIssuingBankNameandAddress"));
			fieldNamesMap.put("label.707.ppDateofIssue", getText("label.707.ppDateofIssue"));
			fieldNamesMap.put("label.707.ppDateofAmendment", getText("label.707.ppDateofAmendment"));
			fieldNamesMap.put("label.707.ppNumberofAmendment", getText("label.707.ppNumberofAmendment"));
			fieldNamesMap.put("label.707.ppbeneficiaryNameAddress", getText("label.707.ppbeneficiaryNameAddress"));
			fieldNamesMap.put("label.707.ppNewDateofExpiry", getText("label.707.ppNewDateofExpiry"));
			fieldNamesMap.put("label.707.ppCurrency", getText("label.707.ppCurrency"));
			fieldNamesMap.put("label.707.ppIncreaseofLCAmount", getText("label.707.ppIncreaseofLCAmount"));
			fieldNamesMap.put("label.707.ppcurrency", getText("label.707.ppcurrency"));
			fieldNamesMap.put("label.707.ppDecreaseofLCAmount", getText("label.707.ppDecreaseofLCAmount"));
			fieldNamesMap.put("label.707.ppCUrrency", getText("label.707.ppCUrrency"));
			fieldNamesMap.put("label.707.ppNewLCAmount", getText("label.707.ppNewLCAmount"));
			fieldNamesMap.put("label.707.pppositiveTolerance", getText("label.707.pppositiveTolerance"));
			fieldNamesMap.put("label.707.ppnegativeTolerance", getText("label.707.ppnegativeTolerance"));
			fieldNamesMap.put("label.707.ppmaximumCreditAmount", getText("label.707.ppmaximumCreditAmount"));
			fieldNamesMap.put("label.707.ppAdditionalAmountsCovered", getText("label.707.ppAdditionalAmountsCovered"));
			fieldNamesMap.put("label.707.ppplaceofDispatch", getText("label.707.ppplaceofDispatch"));
			fieldNamesMap.put("label.707.ppportofLoading", getText("label.707.ppportofLoading"));
			fieldNamesMap.put("label.707.ppportofDischarge", getText("label.707.ppportofDischarge"));
			fieldNamesMap.put("label.707.ppfinalDeliveryPlace", getText("label.707.ppfinalDeliveryPlace"));
			fieldNamesMap.put("label.707.pplatestDateofShipment", getText("label.707.pplatestDateofShipment"));
			fieldNamesMap.put("label.707.ppShipmentPeriod", getText("label.707.ppShipmentPeriod"));
			fieldNamesMap.put("label.707.ppNarrative", getText("label.707.ppNarrative"));
			fieldNamesMap.put("label.707.ppSendertoReceiverInformation", getText("label.707.ppSendertoReceiverInformation"));
			
			PaymentPDFGeneratationUtil paymentPDFGeneratationUtil = new PaymentPDFGeneratationUtil();
			paymentPDFGeneratationUtil.setServletRequest(servletRequest);
			paymentPDFGeneratationUtil.setReportName(reportName);
			
			paymentPDFGeneratationUtil.generatePaymentPDFReport(lcCanonicalDto, null, null,fieldNamesMap, 707, "XXX");
			
		}
		catch (Exception exception) {
			AuditServiceUtil.logException(exception,logger);
		}
		addActionError("Unable to Generate PDF file! Please try again");
		return "input";
	}
}
