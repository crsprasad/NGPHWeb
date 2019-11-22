package com.logica.ngph.web.action;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.Clob;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.springframework.context.ApplicationContextException;

import com.logica.ngph.Entity.LcCommodity;
import com.logica.ngph.common.ConstantUtil;
import com.logica.ngph.dtos.LCCanonicalDto;
import com.logica.ngph.service.AuthorizationSchemaMaitenanceService;
import com.logica.ngph.service.EnquiryService;
import com.logica.ngph.service.LcOpenService;
import com.logica.ngph.service.LetterOfCreditService;
import com.logica.ngph.service.PaymentMessageService;
import com.logica.ngph.service.PendingAuthorizationService;
import com.logica.ngph.service.UserMaintenanceService;
import com.logica.ngph.web.utils.ApplicationContextProvider;
import com.logica.ngph.web.utils.EventLogging;
import com.logica.ngph.web.utils.ExportToExcelUtil;
import com.logica.ngph.web.utils.PaymentPDFGeneratationUtil;
import com.logica.ngph.web.utils.SerializeManager;
import com.logica.ngph.web.utils.WebConstants;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
/**
 * This class handle 700 msg type and do all operation related to 700 msg type
 * , also handle the inward lc payments and also acknowledge the message 
 * @author guptast
 *
 */
public class LcOpenAction extends ActionSupport implements ModelDriven<LCCanonicalDto>, SessionAware, ServletRequestAware {

	
	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(LcOpenAction.class);
	private Map<String, Object> session;
	//create the instance of dto class to get the model driven object
	LCCanonicalDto lcCanonicalDto = new LCCanonicalDto();
	private List<LCCanonicalDto> searchList;
	private List positiveToleranceList = new ArrayList();
	private List negativeToleranceList  = new ArrayList();
	private List lcCurrencyList = new ArrayList();
	private String lcCommodity;
	private int lcQuntity;
	private Double lcRate;
	private int rowTodelete;
	private String hiddenTxnRef;
	private String txnRef;
	private String saveAction;
	private String checkForSubmit;
	private boolean validUserToApprove;
	private String action;
	private String lcNumberForInward;
	private String flagForScreen;
	private String flagMarked;
	private String repairCommodity;
	private String actionError;
	private String tempRef;
	private String tempName;
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

	public String getRepairCommodity() {
		return repairCommodity;
	}

	public void setRepairCommodity(String repairCommodity) {
		this.repairCommodity = repairCommodity;
	}

	public String getFlagMarked() {
		return flagMarked;
	}

	public void setFlagMarked(String flagMarked) {
		this.flagMarked = flagMarked;
	}

	public String getFlagForScreen() {
		return flagForScreen;
	}

	public void setFlagForScreen(String flagForScreen) {
		this.flagForScreen = flagForScreen;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public List<LCCanonicalDto> getSearchList() {
		return searchList;
	}

	public void setSearchList(List<LCCanonicalDto> searchList) {
		this.searchList = searchList;
	}
	private String lcNumber;
	public String getLcNumber() {
		return lcNumber;
	}

	public void setLcNumber(String lcNumber) {
		this.lcNumber = lcNumber;
	}

	public Map<String, Object> getSession() {
		return session;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
	
	//Made static so as to get the count of lcCommodity details and to add more commodity details
	static List<LcCommodity> gridList = new ArrayList<LcCommodity>();
	
	/**
	 * This method load the lcopen screen and load the values for database
	 * which is required for drop down list.
	 * 	
	 *   @return success if no error is thrown
	 */
	
	@SkipValidation
	public String displayLCOpenScreen()
	{
		try{
			UserMaintenanceService userMaintenanceService = (UserMaintenanceService)ApplicationContextProvider.getBean("userMaintenanceService");
			setCurrCodeDropDown(userMaintenanceService.getCurrencyCodes());
			gridList=new ArrayList<LcCommodity>();
			session.remove("bgCommoditiesList");
			
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
	
	/**
	 * This method load all the data object present for status = 3 from ta_messages_tx if lcNumber is not present
	 * if lcNumber is present is give the fetch the value and populate in the search screen.
	 * @return success if exception in not there
	 **/
	@SkipValidation
	public String PopulatedLCContent()
	{
		try{
			LcOpenService lcOpenService = (LcOpenService) ApplicationContextProvider.getBean("lcOpenService");
			//fetch the record according to lcnumber .if lcnumber is null then fetch all record
			setSearchList(lcOpenService.getDate(getLcNumber()));
			//setting the flag to know its for lc open 
			setFlagForScreen("LcOpen");
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
	private List<LcCommodity> bgCommoditiesList = new ArrayList<LcCommodity>();
	
	public List<LcCommodity> getBgCommoditiesList() {
		return bgCommoditiesList;
	}

	public void setBgCommoditiesList(List<LcCommodity> bgCommoditiesList) {
		this.bgCommoditiesList = bgCommoditiesList;
		this.session.put("bgCommoditiesList", bgCommoditiesList);
	}
	
	/**
	 * This method Do the mark acknowledgment i.e
	 * if any message is there of status 3 it will mark acknowlege and change the status to 4
	 * same for all the message who have odd no of status.
	 * @return success it all message are in valid state
	 * @return input when message are not in valide state 
	 */
	@SkipValidation
	public String markAsAcknowledge()
	{
		try{
			LcOpenService lcOpenService = (LcOpenService) ApplicationContextProvider.getBean("lcOpenService");
			String lcnumber = lcCanonicalDto.getLcNumber();
			//fetching the record for lc number which is selected in mark acknowledge screen
			String lcStatus = lcOpenService.getLcStatus(lcnumber);
			
			if(lcStatus.equals("1"))
			{
				//changing status to 1 to 2
				lcOpenService.saveChangeStatus(lcnumber,"2");
			}
			else if(lcStatus.equals("3"))
			{
				//changing status from 3 to 4
				lcOpenService.saveChangeStatus(lcnumber,"4");
			}
			else if(lcStatus.equals("5"))
			{
				//changing status from 5 to 6
				lcOpenService.saveChangeStatus(lcnumber,"6");
			}
			else if(lcStatus.equals("7"))
			{
				//changing status from 7 to 8
				lcOpenService.saveChangeStatus(lcnumber,"8");
			}
			else if(lcStatus.equals("9"))
			{
				//changing status from 9 to 10
				lcOpenService.saveChangeStatus(lcnumber,"10");
			}
			
			else if(lcStatus.equals("2") || lcStatus.equals("4") || lcStatus.equals("6")|| lcStatus.equals("8") || lcStatus.equals("10"))
			{
				addFieldError("lcNumberForInward", "Lc Number : "+lcnumber+" : Not In Valid Status For Marking Acknowledge");
				return "input";
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
	addFieldError("LcNumber", "Unable To Do The Operation. Please Try Again");
	return "input";
}
	/**
	 * This method display the field value to the crossponding field in the lc open screen.
	 * Once any lc Number is select it will display the details of that lc in Lcopen screen
	 *  and same is done for lc inward
	 * @return success
	 */
	@SkipValidation
	public String displayLCOpenData()
	{
		try{
			
			String lcnumber = lcCanonicalDto.getLcNumber();
			LCCanonicalDto canonicalDto = null;
			LetterOfCreditService letterOfCreditService =  (LetterOfCreditService)  ApplicationContextProvider.getBean("letterOfCreditService");
			LcOpenService lcOpenService = (LcOpenService) ApplicationContextProvider.getBean("lcOpenService");
			if(getLcNumberForInward()!=null ){
				displayLCOpenScreen();
				canonicalDto=lcOpenService.getLCScreenData(getLcNumberForInward());
			}else
			{
				if(letterOfCreditService.isLcStatusTwo(lcCanonicalDto.getLcNumber())==true)
				{
					addFieldError("LcNumber","Lc Number Not In Valid Status");
					return "input";
				}else
					canonicalDto=lcOpenService.getLCScreenData(lcnumber);
			}
			


			

	       // Object[] obj = (Object[]) list.get(0);
	        lcCanonicalDto.setLcType(canonicalDto.getLcType());
	        lcCanonicalDto.setLcNumber(canonicalDto.getLcNumber());
	        lcCanonicalDto.setLcExpiryDate(canonicalDto.getLcExpiryDate());
	        lcCanonicalDto.setLcExpirePlace(canonicalDto.getLcExpirePlace());
	             	
	        	lcCanonicalDto.setPositiveTolerance(canonicalDto.getPositiveTolerance());
	            lcCanonicalDto.setNegativeTolerance(canonicalDto.getNegativeTolerance());
	            
	       
	        lcCanonicalDto.setMaximumCreditAmount(canonicalDto.getMaximumCreditAmount());
	        lcCanonicalDto.setAdditionalAmounts(canonicalDto.getAdditionalAmounts());
	        lcCanonicalDto.setAuthorisedBankCode(canonicalDto.getAuthorisedBankCode());
	        lcCanonicalDto.setAuthorisedAndAddress(canonicalDto.getAuthorisedAndAddress());
	        lcCanonicalDto.setAuthorisationMode(canonicalDto.getAuthorisationMode());
	        lcCanonicalDto.setGoodsLoadingDispatchPlace(canonicalDto.getGoodsLoadingDispatchPlace());
	        lcCanonicalDto.setGoodsDestination(canonicalDto.getGoodsDestination());
	        lcCanonicalDto.setLatestDateofShipment(canonicalDto.getLatestDateofShipment());
	        lcCanonicalDto.setShipmentPeriod(canonicalDto.getShipmentPeriod());
	        lcCanonicalDto.setShipmentTerms(canonicalDto.getShipmentTerms());
	        lcCanonicalDto.setDraftsAt(canonicalDto.getDraftsAt());
	        lcCanonicalDto.setDraweeBankpartyidentifier(canonicalDto.getDraweeBankpartyidentifier());
	        lcCanonicalDto.setDraweeBankCode(canonicalDto.getDraweeBankCode());
	        lcCanonicalDto.setDraweeBankNameAddress(canonicalDto.getDraweeBankNameAddress());
	        lcCanonicalDto.setMixedPaymentDetails(canonicalDto.getMixedPaymentDetails());
	        lcCanonicalDto.setDeferredPaymentDetails(canonicalDto.getDeferredPaymentDetails());
	        lcCanonicalDto.setPartialShipments(canonicalDto.getPartialShipments());
	        lcCanonicalDto.setTranshipment(canonicalDto.getTranshipment());
	        lcCanonicalDto.setDocumentsRequired(canonicalDto.getDocumentsRequired());
	        lcCanonicalDto.setAdditionalConditions(canonicalDto.getAdditionalConditions());
	        lcCanonicalDto.setChargeDetails(canonicalDto.getChargeDetails());
	        lcCanonicalDto.setPeriodforPresentation(canonicalDto.getPeriodforPresentation());
	        lcCanonicalDto.setConfirmationCode(canonicalDto.getConfirmationCode());
	        lcCanonicalDto.setInstructionstoPayingBank(canonicalDto.getInstructionstoPayingBank());
	        lcCanonicalDto.setNarrative(canonicalDto.getNarrative());
	        lcCanonicalDto.setMsgRef(canonicalDto.getMsgRef());       
	        
	        lcCanonicalDto.setAdviseThroughBankpartyidentifier(canonicalDto.getAdviseThroughBankpartyidentifier());
			lcCanonicalDto.setAdvisingBank(canonicalDto.getAdvisingBank());
			lcCanonicalDto.setAdviseThroughBankCode(canonicalDto.getAdviseThroughBankCode());
			lcCanonicalDto.setAdviseThroughBankLocation(canonicalDto.getAdviseThroughBankLocation());
			lcCanonicalDto.setAdviseThroughBankName(canonicalDto.getAdviseThroughBankName());
			//lcCanonicalDto.setSendertoReceiverInformation(canonicalDto.getSendertoReceiverInformation());
			lcCanonicalDto.setReimbursingBank(canonicalDto.getReimbursingBank());
			lcCanonicalDto.setApplicantNameAddress(canonicalDto.getApplicantNameAddress());
			lcCanonicalDto.setApplicentBankNameandAddr(canonicalDto.getApplicentBankNameandAddr());
			System.out.println("setApplicentBankNameandAddr "+lcCanonicalDto.getApplicentBankNameandAddr());
			lcCanonicalDto.setApplicantBankCode(canonicalDto.getApplicantBankCode());
			System.out.println("applicant bank code"+lcCanonicalDto.getApplicantBankCode());
			lcCanonicalDto.setApplicantBankpartyidentifier(canonicalDto.getApplicantBankpartyidentifier());
			lcCanonicalDto.setApplicantAccount(canonicalDto.getApplicantAccount());
			lcCanonicalDto.setAdvisingBank(canonicalDto.getAdvisingBank());
			//lcCanonicalDto.setBeneficiaryAccount(canonicalDto.getBeneficiaryAccount());
			lcCanonicalDto.setBeneficiaryNameAddress(canonicalDto.getBeneficiaryNameAddress());
			lcCanonicalDto.setLcAmount(canonicalDto.getLcAmount());
			lcCanonicalDto.setLcCurrency(canonicalDto.getLcCurrency());
			lcCanonicalDto.setAdviseThroughBankAcc(canonicalDto.getAdviseThroughBankAcc());
			lcCanonicalDto.setAdditionalAmountsCovered(canonicalDto.getAdditionalAmountsCovered());
			lcCanonicalDto.setInitialDispatchPlace(canonicalDto.getInitialDispatchPlace());
			lcCanonicalDto.setFinalDeliveryPlace(canonicalDto.getFinalDeliveryPlace());
			lcCanonicalDto.setApplicableNarrative(canonicalDto.getApplicableNarrative());
			lcCanonicalDto.setApplicableRule(canonicalDto.getApplicableRule());
			lcCanonicalDto.setMsgHost(canonicalDto.getMsgHost());
			lcCanonicalDto.setSeqNo(canonicalDto.getSeqNo());
			lcCanonicalDto.setIssuingBankCode(canonicalDto.getIssuingBankCode());
			
			lcCanonicalDto.setSequence(canonicalDto.getSequence());
			lcCanonicalDto.setSequenceofTotal(canonicalDto.getSequenceofTotal());
			lcCanonicalDto.setLcPresdvice(canonicalDto.getLcPresdvice());
			lcCanonicalDto.setApplicentIdentifier(canonicalDto.getApplicentIdentifier());
			lcCanonicalDto.setApplicentBankNameandAddr(canonicalDto.getApplicentBankNameandAddr());
			lcCanonicalDto.setDraweeIdentifier(canonicalDto.getDraweeIdentifier());
			lcCanonicalDto.setReimbursingIdentifier(canonicalDto.getReimbursingIdentifier());
			lcCanonicalDto.setAdvisingIdentifier(canonicalDto.getAdvisingIdentifier());
			lcCanonicalDto.setApplicantBankCode(canonicalDto.getApplicantBankCode());
			
			setBgCommoditiesList(lcOpenService.getCommodityDetails(lcnumber));
			gridList = lcOpenService.getCommodityDetails(lcnumber);
			count = gridList.size();
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
		addFieldError("LcNumber", "Unable To Do The Operation. Please Try Again");
		return "input";
	}

	public String getLcNumberForInward() {
		return lcNumberForInward;
	}

	public void setLcNumberForInward(String lcNumberForInward) {
		this.lcNumberForInward = lcNumberForInward;
	}

	public LCCanonicalDto getModel() {
		
		return lcCanonicalDto;
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

	public List getLcCurrencyList() {
		return lcCurrencyList;
	}

	public void setLcCurrencyList(List lcCurrencyList) {
		this.lcCurrencyList = lcCurrencyList;
	}

	public String getLcCommodity() {
		return lcCommodity;
	}

	public void setLcCommodity(String lcCommodity) {
		this.lcCommodity = lcCommodity;
	}

	public int getLcQuntity() {
		return lcQuntity;
	}

	public void setLcQuntity(int lcQuntity) {
		this.lcQuntity = lcQuntity;
	}

	public Double getLcRate() {
		return lcRate;
	}

	public void setLcRate(Double lcRate) {
		this.lcRate = lcRate;
	}

	public int getRowTodelete() {
		return rowTodelete;
	}

	public void setRowTodelete(int rowTodelete) {
		this.rowTodelete = rowTodelete;
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
	
	
	public String getActionError() {
		return actionError;
	}

	public void setActionError(String actionError) {
		this.actionError = actionError;
	}

	/**
	 * This method check the pattern to the crossponding value
	 * @param pattern whatever pattern u want to match
	 * @param toMatch value according to pattern is valid or not
	 * @return true is value is valid
	 * @return false if value is invalid
	 */
	public Boolean getExpressionCheck(String pattern,String toMatch)
	{
		Pattern patternString = Pattern.compile(pattern);
		Matcher matcher  = patternString.matcher(toMatch);
	//	System.out.println(matcher.matches());
		
		return matcher.matches() ;
	}
	
	/**
	 * This method add new commodity details for newly created lc number
	 * And if its old lc then it will display the it to the screen 
	 */
	//count the record to display according to the number 	
	static int count=1;
	@SkipValidation
	public String addRowToLcGrid()
	{
		try{
			if(StringUtils.isNotBlank(getLcCommodity()) && StringUtils.isNotEmpty(getLcCommodity()) && getExpressionCheck("[0-9a-zA-z/-?:().,+' \n]+[^@%\\[\\]&*$#{}<>'~`;_!\"=]+$", getLcCommodity())){
			//gridList=(List)session.get("commoditiesList");	
				LcCommodity commodity = new LcCommodity();
				commodity.setLcCommodity(getLcCommodity());
				commodity.setLcId(count+"");
				gridList.add(commodity);
				
				//System.out.println(count++);
				setBgCommoditiesList(gridList);
				count=count+1;
				setLcCommodity("");
				
			return "success";
			}
			else
			{
				if(StringUtils.isBlank(getLcCommodity()) && StringUtils.isEmpty(getLcCommodity()) )
				{
					addFieldError("lcCommodity", "Description of Goods and/or Services Is Required");
					
				}else{
				addFieldError("lcCommodity", "Description of Goods and/or Services Is Invalid");
				}
				return INPUT;
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
	/**
	 * This is the main method which communicate with service 
	 * In this we are doing event logging also and 
	 * also change the statues of particulare transaction to 'P' or 'A' according to reject or approval
	 *  
	 * @return success if all the operation is completed 
	 * @return input if any exception is there
	 * @return  
	 */
	public String getObjectForLC()
	{
		try{		
			PendingAuthorizationService pendingAuthorizationService = (PendingAuthorizationService)ApplicationContextProvider.getBean("pendingAuthorizationService");
			EventLogging eventLogging = (EventLogging)ApplicationContextProvider.getBean("eventLogging");
			
			LetterOfCreditService letterOfCreditService =  (LetterOfCreditService)  ApplicationContextProvider.getBean("letterOfCreditService");
			String userId = (String)session.get(WebConstants.CONSTANT_USER_NAME);
			
			if(getSaveAction().equals("Approve")){
				updateCommoditiList(getRepairCommodity(), lcCanonicalDto.getLcNumber(), lcCanonicalDto.getMsgRef());
			String returnVAlue= letterOfCreditService.saveLC(lcCanonicalDto,(List<LcCommodity>)session.get("bgCommoditiesList"),"LcOpen",userId,lcCanonicalDto.getRepair());
		
			
			if(returnVAlue!=null && !returnVAlue.equals("")){
				pendingAuthorizationService.changeStatus(getSaveAction(), getHiddenTxnRef());
				
					if(StringUtils.isBlank(lcCanonicalDto.getRepair()) && StringUtils.isEmpty(lcCanonicalDto.getRepair())){
						eventLogging.doEventLogging(userId," Lc Open ",ConstantUtil.EVENTID_LCOPEN+ConstantUtil.EVENTID_APPROVE,ConstantUtil.EVENTLOGGINGCOMMENTAPPROVAL,lcCanonicalDto.getLcNumber(),lcCanonicalDto.getMsgRef());
					}else
					{
						eventLogging.doEventLogging(userId,"Lc Open",ConstantUtil.EVENTID_LCOPEN +ConstantUtil.EVENTID_REPAIR_APPROVE,ConstantUtil.EVENTLOGGINGCOMMENTAPPROVAL+" "+ConstantUtil.EVENTLOGGINGCOMMENTREAPIR,lcCanonicalDto.getLcNumber(),lcCanonicalDto.getMsgRef());
					}
				return "success";	
			}else	{
				addActionError("Unable to perform the operation. Please try again");
				return "input";
			}
			}
			else if(getSaveAction().equals("Reject"))
			{
				if(StringUtils.isBlank(lcCanonicalDto.getRepair()) && StringUtils.isEmpty(lcCanonicalDto.getRepair())){
					pendingAuthorizationService.changeStatus(getSaveAction(), getHiddenTxnRef());
					eventLogging.doEventLogging(userId," Lc Open ",ConstantUtil.EVENTID_LCOPEN+ConstantUtil.EVENTID_REJECT,ConstantUtil.EVENTLOGGINGCOMMENTREJECT,lcCanonicalDto.getLcNumber(),lcCanonicalDto.getMsgRef());
				}else{
					PaymentMessageService paymentMessageService =(PaymentMessageService)ApplicationContextProvider.getBean("paymentMessageService");
					paymentMessageService.changeMsgStatus99to2(lcCanonicalDto.getMsgRef());
					pendingAuthorizationService.changeStatus(getSaveAction(), getHiddenTxnRef());
					eventLogging.doEventLogging(userId," Lc Open ",ConstantUtil.EVENTID_LCOPEN+ConstantUtil.EVENTID_REPAIR_REJECT,ConstantUtil.EVENTLOGGINGCOMMENTREJECT+" "+ConstantUtil.EVENTLOGGINGCOMMENTREAPIR,lcCanonicalDto.getLcNumber(),lcCanonicalDto.getMsgRef() );
				}
				
				pendingAuthorizationService = null;
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
	
	/**
	 * This method Delete the row for commodity details
	 * @return
	 */
	@SkipValidation
	public String rowToDelete()
	{
		try{
			List<LcCommodity> list= ((List<LcCommodity>)session.get("bgCommoditiesList"));
			for(int i  = 0;i<list.size();i++)
			{
				
				LcCommodity commodity = (LcCommodity) list.get(i);
				if(commodity.getLcId().equals(getRowTodelete()+"".trim()))
				{
					//list.remove(i);
					gridList.remove(i);
					
					break;
				}
				 
			}
			setBgCommoditiesList(gridList);
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
	private Timestamp getCurrentTime(){
		java.util.Date 	str_date = Calendar.getInstance().getTime();
		java.sql.Timestamp timeStampDate = new Timestamp(str_date.getTime());
		return timeStampDate;
	}
	
	public String getLetterOFCreditApproval()
	{
		
		try{
			logger.info("getLetterOFCreditApproval Is Called In LcOpen");
			String txnKey="";
			EventLogging eventLogging = (EventLogging)ApplicationContextProvider.getBean("eventLogging");
			//AuditService	auditService = (AuditService)ApplicationContextProvider.getBean("auditService");
			PendingAuthorizationService pendingAuthorizationService = (PendingAuthorizationService)ApplicationContextProvider.getBean("pendingAuthorizationService");
			String userId = (String)session.get(WebConstants.CONSTANT_USER_NAME);
			if(!getHiddenTxnRef().isEmpty())
			{
				pendingAuthorizationService.updateRejectStatusToPending(getHiddenTxnRef());
			}else
			{
				txnKey = pendingAuthorizationService.getTranRef(new SerializeManager<LCCanonicalDto>().serializeObject((String)session.get(WebConstants.CONSTANT_USER_NAME), lcCanonicalDto),"Lc Open(MT-700)",userId);
				pendingAuthorizationService.delimitedStringValue(txnKey, 1+"", "lcCurrency"+"~"+lcCanonicalDto.getLcCurrency());
			}
			if(StringUtils.isBlank(lcCanonicalDto.getRepair()) && StringUtils.isEmpty(lcCanonicalDto.getRepair())){
				
				eventLogging.doEventLogging(userId," Lc Open ",ConstantUtil.EVENTID_LCOPEN+ConstantUtil.EVENTID_SUBMIT,ConstantUtil.EVENTLOGGINGCOMMENTSUBMIT,lcCanonicalDto.getLcNumber(),lcCanonicalDto.getMsgRef());
			}else{
				 updateCommoditiList(getRepairCommodity(),lcCanonicalDto.getLcNumber(),lcCanonicalDto.getMsgRef());
				PaymentMessageService paymentMessageService =(PaymentMessageService)ApplicationContextProvider.getBean("paymentMessageService");
				paymentMessageService.changeMsgStatus2to99(lcCanonicalDto.getMsgRef());
				eventLogging.doEventLogging(userId," Lc Open ",ConstantUtil.EVENTID_LCOPEN+ConstantUtil.EVENTID_REPAIR_SUBMIT,ConstantUtil.EVENTLOGGINGCOMMENTSUBMIT+" "+ ConstantUtil.EVENTLOGGINGCOMMENTREAPIR,lcCanonicalDto.getLcNumber(),lcCanonicalDto.getMsgRef());
			}
			session.remove("Adv_Acc");
		//List<LcCommodity> list= ((List<LcCommodity>)session.get("bgCommoditiesList"));
		//if(list!=null){
		//for(int i = 0 ;i<list.size();i++){
			//LcCommodity dataString = list.get(i);
			//String stringToObject=dataString.getLcId()+"~"+dataString.getLcCommodity()+"~"+dataString.getMsgRef();
			//pendingAuthorizationService.delimitedStringValue(txnKey, i+"", stringToObject);
			
			//}
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
		return "success";
	}
	
	public void updateCommoditiList(String repairCommodity,String lcNumber,String msgRef){
		
		List<LcCommodity> list=new ArrayList<LcCommodity>();
		if(repairCommodity!=null){
			String strar[] = repairCommodity.split("\r\n");
			for(int i=0;i<strar.length;i++){
				LcCommodity com = new LcCommodity();
				com.setLcId(i+"");
				com.setLcCommodity(strar[i]);
				com.setLcNumber(lcNumber);
				com.setMsgRef(msgRef);
				list.add(com);
			}
		}
		setBgCommoditiesList(list);
	}
	
	
	@SkipValidation
	public String getLetterOFCreditAuthorization()
	{
		String tempScreenString =null;
		PendingAuthorizationService pendingAuthorizationService = (PendingAuthorizationService)ApplicationContextProvider.getBean("pendingAuthorizationService");
	try{
		displayLCOpenScreen();
		if(getTxnRef()!=null)
		{
			setCheckForSubmit("Display_Approve_Reject");
			String userId = pendingAuthorizationService.getCreatedUser(getTxnRef());
			String userRole = pendingAuthorizationService.getUserType((String)session.get(WebConstants.CONSTANT_USER_NAME));
			if((((String)session.get(WebConstants.CONSTANT_USER_NAME)).equals(userId) || userRole.equals("T"))){
				setValidUserToApprove(false);
		} else 
		{
			setValidUserToApprove(true);
		}
			tempScreenString = pendingAuthorizationService.getScreenReturn(getTxnRef());
		}
		else if(getTempRef()!=null)
		{
			tempScreenString = pendingAuthorizationService.getTemplateScreen(getTempRef());
		}
		//LCCanonicalDto temp= getSerializedObject(tempScreenString);
		LCCanonicalDto temp = (LCCanonicalDto) new SerializeManager<LCCanonicalDto>().getSerializedObject(tempScreenString);
		List mulDataList = pendingAuthorizationService.getMulScreenData(getTxnRef());
		List<LcCommodity> temportList = new ArrayList<LcCommodity>();
		
		lcCanonicalDto.setLcNumber(temp.getLcNumber());
		lcCanonicalDto.setLcType(temp.getLcType());
		lcCanonicalDto.setAdditionalAmounts(temp.getAdditionalAmounts());
		lcCanonicalDto.setAdviseThroughBankCode(temp.getAdviseThroughBankCode());
		lcCanonicalDto.setAdviseThroughBankLocation(temp.getAdviseThroughBankLocation());
		
		lcCanonicalDto.setLatestDateofShipment(temp.getLatestDateofShipment());
		lcCanonicalDto.setLcAmount(temp.getLcAmount());
		lcCanonicalDto.setLcExpirePlace(temp.getLcExpirePlace());
		lcCanonicalDto.setLcExpiryDate(temp.getLcExpiryDate());
		
		lcCanonicalDto.setAdviseThroughBankName(temp.getAdviseThroughBankName());
		lcCanonicalDto.setAdviseThroughBankpartyidentifier(temp.getAdviseThroughBankpartyidentifier());
		lcCanonicalDto.setApplicantNameAddress(temp.getApplicantNameAddress());
		lcCanonicalDto.setAuthorisationMode(temp.getAuthorisationMode());
		lcCanonicalDto.setAuthorisedAndAddress(temp.getAuthorisedAndAddress());
		lcCanonicalDto.setAuthorisedBankCode(temp.getAuthorisedBankCode());
		
		//lcCanonicalDto.setBeneficiaryAccount(temp.getBeneficiaryAccount());
		lcCanonicalDto.setBeneficiaryNameAddress(temp.getBeneficiaryNameAddress());
		lcCanonicalDto.setMaximumCreditAmount(temp.getMaximumCreditAmount());
		lcCanonicalDto.setNarrative(temp.getNarrative());
		lcCanonicalDto.setNegativeTolerance(temp.getNegativeTolerance());
		lcCanonicalDto.setPositiveTolerance(temp.getPositiveTolerance());
		lcCanonicalDto.setSendertoReceiverInformation(temp.getSendertoReceiverInformation());
		lcCanonicalDto.setShipmentPeriod(temp.getShipmentPeriod());
		lcCanonicalDto.setShipmentTerms(temp.getShipmentTerms());
		
		lcCanonicalDto.setDraftsAt(temp.getDraftsAt());
        lcCanonicalDto.setDraweeBankpartyidentifier(temp.getDraweeBankpartyidentifier());
        lcCanonicalDto.setDraweeBankAccount(temp.getDraweeBankAccount());
        lcCanonicalDto.setDraweeBankCode(temp.getDraweeBankCode());
        lcCanonicalDto.setDraweeBankNameAddress(temp.getDraweeBankNameAddress());
        lcCanonicalDto.setMixedPaymentDetails(temp.getMixedPaymentDetails());
        lcCanonicalDto.setDeferredPaymentDetails(temp.getDeferredPaymentDetails());
        lcCanonicalDto.setPartialShipments(temp.getPartialShipments());
        lcCanonicalDto.setTranshipment(temp.getTranshipment());
        lcCanonicalDto.setDocumentsRequired(temp.getDocumentsRequired());
        lcCanonicalDto.setAdditionalConditions(temp.getAdditionalConditions());
        lcCanonicalDto.setChargeDetails(temp.getChargeDetails());
        lcCanonicalDto.setPeriodforPresentation(temp.getPeriodforPresentation());
        lcCanonicalDto.setConfirmationCode(temp.getConfirmationCode());
        lcCanonicalDto.setInstructionstoPayingBank(temp.getInstructionstoPayingBank());
        lcCanonicalDto.setNarrative(temp.getNarrative());
        lcCanonicalDto.setMsgRef(temp.getMsgRef());
        
        lcCanonicalDto.setAdviseThroughBankpartyidentifier(temp.getAdviseThroughBankpartyidentifier());
		lcCanonicalDto.setAdvisingBank(temp.getAdvisingBank());
		lcCanonicalDto.setAdviseThroughBankCode(temp.getAdviseThroughBankCode());
		lcCanonicalDto.setAdviseThroughBankLocation(temp.getAdviseThroughBankLocation());
		lcCanonicalDto.setAdviseThroughBankAcc(temp.getAdviseThroughBankAcc());
		
		lcCanonicalDto.setAdviseThroughBankName(temp.getAdviseThroughBankName());
		lcCanonicalDto.setSendertoReceiverInformation(temp.getSendertoReceiverInformation());
		lcCanonicalDto.setReimbursingBank(temp.getReimbursingBank());
		lcCanonicalDto.setGoodsDestination(temp.getGoodsDestination());
		lcCanonicalDto.setGoodsLoadingDispatchPlace(temp.getGoodsLoadingDispatchPlace());
		lcCanonicalDto.setApplicantAccount(temp.getApplicantAccount());
		lcCanonicalDto.setApplicantBankCode(temp.getApplicantBankCode());
		lcCanonicalDto.setApplicentBankNameandAddr(temp.getApplicentBankNameandAddr());
		lcCanonicalDto.setApplicantBankpartyidentifier(temp.getApplicantBankpartyidentifier());
		lcCanonicalDto.setPartialShipments(temp.getPartialShipments());
		lcCanonicalDto.setNetChargeAmount(temp.getNetChargeAmount());
		lcCanonicalDto.setPartyIdentifier(temp.getPartyIdentifier());
		lcCanonicalDto.setSenderCorrespontAcount(temp.getSenderCorrespontAcount());
		lcCanonicalDto.setReimbursingBankCode(temp.getReimbursingBankCode());
		lcCanonicalDto.setReimbursingBankNameAddress(temp.getReimbursingBankNameAddress());
		lcCanonicalDto.setSendersCorrespondentCode(temp.getSendersCorrespondentCode());	
		lcCanonicalDto.setInitialDispatchPlace(temp.getInitialDispatchPlace());
		lcCanonicalDto.setFinalDeliveryPlace(temp.getFinalDeliveryPlace());
		lcCanonicalDto.setRepair(temp.getRepair());
		lcCanonicalDto.setApplicableNarrative(temp.getApplicableNarrative());
		lcCanonicalDto.setApplicableRule(temp.getApplicableRule());
		lcCanonicalDto.setMsgHost(temp.getMsgHost());
		lcCanonicalDto.setSeqNo(temp.getSeqNo());
		lcCanonicalDto.setIssueDate(temp.getIssueDate());
		lcCanonicalDto.setComment(temp.getComment());
		lcCanonicalDto.setIssuingBankCode(temp.getIssuingBankCode());
		lcCanonicalDto.setAvailableIdentifier(temp.getAvailableIdentifier());
		lcCanonicalDto.setSequence(temp.getSequence());
		lcCanonicalDto.setSequenceofTotal(temp.getSequenceofTotal());
		lcCanonicalDto.setLcPresdvice(temp.getLcPresdvice());
		lcCanonicalDto.setApplicentIdentifier(temp.getApplicentIdentifier());
		lcCanonicalDto.setApplicentBankNameandAddr(temp.getApplicentBankNameandAddr());
		lcCanonicalDto.setDraweeIdentifier(temp.getDraweeIdentifier());
		lcCanonicalDto.setReimbursingIdentifier(temp.getReimbursingIdentifier());
		lcCanonicalDto.setAdvisingIdentifier(temp.getAdvisingIdentifier());
		lcCanonicalDto.setAdvisingIdentifier(temp.getAdvisingIdentifier());
		lcCanonicalDto.setDescriptionofGoods(temp.getDescriptionofGoods());
		
		/*if(StringUtils.isBlank(temp.getRepair()) && StringUtils.isEmpty(temp.getRepair())){
		for(int i=0;i<mulDataList.size();i++)
		{
			Clob list=(Clob) mulDataList.get(i);
			String[] tempString;
			tempString = list.getSubString(1, (int) list.length()).split("~");
			LcCommodity lcCommodity = new LcCommodity();
			lcCommodity.setLcId(tempString[0]);
			lcCommodity.setLcCommodity(tempString[1]);
			lcCommodity.setMsgRef(tempString[2]);
			temportList.add(lcCommodity);
		}
		setBgCommoditiesList(temportList);
		}else{
			LcOpenService lcOpenService = (LcOpenService) ApplicationContextProvider.getBean("lcOpenService");
			List<LcCommodity> commodityList= (List<LcCommodity>)lcOpenService.getCommodityDetails(temp.getLcNumber());
			StringBuffer tempStr=new StringBuffer();
			for(int i=0;i<mulDataList.size();i++)
			{
				Clob list=(Clob) mulDataList.get(i);
				String[] tempString;
				tempString = list.getSubString(1, (int) list.length()).split("~");
				tempStr.append(tempString[1]);
				tempStr.append("\r\n");
				
			}
				setRepairCommodity(tempStr+"");
		}*/
		
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
		
		lcCanonicalDto.setLcCurrency(temp.getLcCurrency());
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
	private  final static String getDateTime()  
    {  
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd_hhmmss");  
        df.setTimeZone(TimeZone.getTimeZone("GMT"));  
        return df.format(new Date());  
    }  
		public void validate()
	{
			
		try{
		LetterOfCreditService letterOfCreditService =  (LetterOfCreditService)  ApplicationContextProvider.getBean("letterOfCreditService");
		String senderBank = letterOfCreditService.getDept((String)session.get(WebConstants.CONSTANT_USER_NAME));
		if(StringUtils.isBlank(lcCanonicalDto.getRepair()) && StringUtils.isEmpty(lcCanonicalDto.getRepair())){
		if(senderBank!=null &&  lcCanonicalDto.getAdvisingBank()!=null)
		{
			if(senderBank.trim().equalsIgnoreCase(lcCanonicalDto.getAdvisingBank().trim()))
					{
						addFieldError("advisingBank", "Receiver Bank IFSC Should Not Be Sender Bank IFSC");
						
					}
		}
		}else
		{
			if(StringUtils.isNotBlank(lcCanonicalDto.getSenderBank())&&StringUtils.isNotEmpty(lcCanonicalDto.getSenderBank()) &&  lcCanonicalDto.getAdvisingBank()!=null)
			{
				if(lcCanonicalDto.getSenderBank().trim().equalsIgnoreCase(lcCanonicalDto.getAdvisingBank().trim()))
						{
							addFieldError("advisingBank", "Receiver Bank IFSC Should Not Be Sender Bank IFSC");
						}
			}	
		}
		String flag = letterOfCreditService.getLcOpenFlagForInsertOrUpdate();
		
		if(flag.equals("Y") && !(getSaveAction().equals("Approve") || getSaveAction().equals("Reject")))
		{
			if(StringUtils.isBlank(lcCanonicalDto.getRepair()) && StringUtils.isEmpty(lcCanonicalDto.getRepair()))
			{
				if(letterOfCreditService.isLcNumberExist(lcCanonicalDto.getLcNumber())==true)
					{
						addFieldError("LcNumber","Documentary Credit Number(20) AllReady Available In DataBase");
					}
			
			}
			else
			{ 
				if(!letterOfCreditService.getstatusForLCNumber(lcCanonicalDto.getMsgRef()))
				{
					addFieldError("LcNumber", "Message Is Not In Valid State");
				}
			}
		}
		else if(flag.equals("N"))
		{
			if(letterOfCreditService.isLcNumberExist(lcCanonicalDto.getLcNumber())==false)
			{
				addFieldError("LcNumber","Documentary Credit Number(20) Not Available In DataBase");
			}
		
		}
		if(StringUtils.isBlank(lcCanonicalDto.getRepair()) && StringUtils.isEmpty(lcCanonicalDto.getRepair()))
		{
			if(lcCanonicalDto.getLcExpiryDate()!=null){
				if (lcCanonicalDto.getLcExpiryDate().before(getCurrentTime())){
				      addFieldError("lcExpiryDate", "Date of Expiry(31D) Should Always Be Greater Than Today's date.");
				      setActionError("true");
				   }	
			}
			if(lcCanonicalDto.getLatestDateofShipment()!=null)
			{
				if(lcCanonicalDto.getLatestDateofShipment().before(getCurrentTime()) || lcCanonicalDto.getLatestDateofShipment().after(lcCanonicalDto.getLcExpiryDate())){
					
					 addFieldError("latestDateofShipment", "Latest Date of Shipment(44C) Should Always Be Greater Than Today's date and Smaller that Date of Expiry.");
					 setActionError("true");
				}
			}
		}else{
			PaymentMessageService paymentMessageService =(PaymentMessageService)ApplicationContextProvider.getBean("paymentMessageService");
			if(!paymentMessageService.checkInRptStatusIs2(lcCanonicalDto.getMsgRef()))
			{
				if(!(getSaveAction().equals("Approve") || getSaveAction().equals("Reject")))
					addFieldError("LcNumber", "Message Is Not In Valid State");
			}
			if(lcCanonicalDto.getLatestDateofShipment()!=null)
			{
				if(lcCanonicalDto.getLatestDateofShipment().after(lcCanonicalDto.getLcExpiryDate())){
					
					 addFieldError("latestDateofShipment", "Latest Date of Shipment(44C) Should Always Be Smaller that Date of Expiry(31D).");
				}
			}
		}
		
		
		
		if(lcCanonicalDto.getLcNumber().startsWith("/")){
			 addFieldError("lcNumber", "Documentary Credit Number(20) must not start with /");
		}else if(lcCanonicalDto.getLcNumber().endsWith("/")){
			addFieldError("lcNumber", "Documentary Credit Number(20) must not End with /");;
		}else if(lcCanonicalDto.getLcNumber().contains("//")){
			addFieldError("lcNumber", "Documentary Credit Number(20) must not contain two consecutive slashes '//'");
		}
		if(StringUtils.isNotBlank(lcCanonicalDto.getApplicantBankCode()) && StringUtils.isNotEmpty(lcCanonicalDto.getApplicantBankCode()) && lcCanonicalDto.getApplicantBankCode()!=null){
			if(letterOfCreditService.checkIFSC(lcCanonicalDto.getApplicantBankCode())==false){
				addFieldError("applicantBankCode", "Applicant BankCode(51A) Is Not Available In System");
			}
		}
		
		if(StringUtils.isNotBlank(lcCanonicalDto.getAdvisingBank()) && StringUtils.isNotEmpty(lcCanonicalDto.getAdvisingBank()) && lcCanonicalDto.getAdvisingBank()!=null){
			if(letterOfCreditService.checkIFSC(lcCanonicalDto.getAdvisingBank())==false){
				addFieldError("advisingBank", "Advising BankCode Is Not Available In System");
			}
		}
		if(StringUtils.isNotBlank(lcCanonicalDto.getAdviseThroughBankCode()) && StringUtils.isNotEmpty(lcCanonicalDto.getAdviseThroughBankCode()) && lcCanonicalDto.getAdviseThroughBankCode()!=null){
			if(letterOfCreditService.checkIFSC(lcCanonicalDto.getAdviseThroughBankCode())==false){
				addFieldError("adviseThroughBankCode", "Advise Through BankCode(57A) Is Not Available In System");
			}
		}
		
		if(StringUtils.isNotBlank(lcCanonicalDto.getAuthorisedBankCode()) && StringUtils.isNotEmpty(lcCanonicalDto.getAuthorisedBankCode()) && lcCanonicalDto.getAuthorisedBankCode()!=null){
			if(letterOfCreditService.checkIFSC(lcCanonicalDto.getAuthorisedBankCode())==false){
				addFieldError("adviseThroughBankCode", "Available Bank Code(41A) Is Not Available In System");
			}
		}
		if(StringUtils.isNotBlank(lcCanonicalDto.getDraweeBankCode()) && StringUtils.isNotEmpty(lcCanonicalDto.getDraweeBankCode()) && lcCanonicalDto.getDraweeBankCode()!=null){
			if(letterOfCreditService.checkIFSC(lcCanonicalDto.getDraweeBankCode())==false){
				addFieldError("draweeBankCode", "Drawee Bank Code(42A) Is Not Available In System");
			}
		}
		
		if(StringUtils.isNotBlank(lcCanonicalDto.getReimbursingBankCode()) && StringUtils.isNotEmpty(lcCanonicalDto.getReimbursingBankCode()) && lcCanonicalDto.getReimbursingBankCode()!=null){
			if(letterOfCreditService.checkIFSC(lcCanonicalDto.getReimbursingBankCode())==false){
				addFieldError("reimbursingBankCode", "Reimbursing BankCode(53A) Is Not Available In System");
			}
		}
		
		
		if((lcCanonicalDto.getPositiveTolerance()!=null  && !lcCanonicalDto.getPositiveTolerance().equals("") && StringUtils.isNotBlank(lcCanonicalDto.getPositiveTolerance())&& StringUtils.isNotEmpty(lcCanonicalDto.getPositiveTolerance()))){
			if(lcCanonicalDto.getNegativeTolerance()==null || lcCanonicalDto.getNegativeTolerance().equals("") || StringUtils.isEmpty(lcCanonicalDto.getNegativeTolerance()) || StringUtils.isBlank(lcCanonicalDto.getNegativeTolerance())){
				addFieldError("negativeTolerance", "Negative Tolerance(39A) Is Required");
			}
		}
		if((lcCanonicalDto.getNegativeTolerance()!=null && !lcCanonicalDto.getNegativeTolerance().equals("") && StringUtils.isNotBlank(lcCanonicalDto.getNegativeTolerance())&& StringUtils.isNotEmpty(lcCanonicalDto.getNegativeTolerance()))){
			if(lcCanonicalDto.getPositiveTolerance()==null || lcCanonicalDto.getPositiveTolerance().equals("") || StringUtils.isEmpty(lcCanonicalDto.getPositiveTolerance()) || StringUtils.isBlank(lcCanonicalDto.getPositiveTolerance())){
				addFieldError("positiveTolerance", "Positive Tolerance(39A) Is Required");
			}
		}
		if(StringUtils.isNotBlank(lcCanonicalDto.getAuthorisationMode()) && StringUtils.isNotBlank(lcCanonicalDto.getAvailableIdentifier()) && lcCanonicalDto.getAvailableIdentifier().equalsIgnoreCase("D"))
		{
			
			if((StringUtils.isEmpty(lcCanonicalDto.getAuthorisedAndAddress()) && StringUtils.isBlank(lcCanonicalDto.getAuthorisedAndAddress()))&&(StringUtils.isEmpty(lcCanonicalDto.getAuthorisedBankCode()) && StringUtils.isBlank(lcCanonicalDto.getAuthorisedBankCode())))
			{
				
				addFieldError("authorisedAndAddress", "Either Available Bank Name and Address(41D) Or Available Bank Code(41A) Must Be Present");
			}
		}
		
		
			if(StringUtils.isNotBlank(lcCanonicalDto.getApplicableNarrative()))
			{
				if(StringUtils.isEmpty(lcCanonicalDto.getApplicableRule())){
					addFieldError("applicableRule", "Appicable Rule(40E) Is Required");
				}
				else if(!lcCanonicalDto.getApplicableRule().equals("OTHR"))
				{
					addFieldError("applicableRule", "Appicable Narrative(40E) Is not Required");
				}
			
		}
			if(StringUtils.isBlank(lcCanonicalDto.getRepair()) && StringUtils.isEmpty(lcCanonicalDto.getRepair())){
			List<LcCommodity> list= ((List<LcCommodity>)session.get("bgCommoditiesList"));
			String  description = null;
			if(list!=null && !list.isEmpty()){
				for(LcCommodity lcCommodity : list){
					description =  lcCommodity.getLcCommodity();
					if(!getExpressionCheck("[0-9a-zA-z/-?:().,+' \n]+[^@%\\[\\]&*$#{}<>'~`;_!\"=]+$", description)){
						addFieldError("lcCommodity", "Description of Goods and/or Services not valid");
					}
				}
			}
			}
		if(getActionError()!=null && getActionError().equalsIgnoreCase("true"))
		{
			addActionError("Unable To Appove this payment, due to Validation Fails. Please Reject the Payment");
		}
		networkValdation();
		validateTolerance();
		}catch (Exception e) {
			addActionError("Unable To process.");
		}
		
		if(StringUtils.isBlank(lcCanonicalDto.getAdvisingBank()) && StringUtils.isEmpty(lcCanonicalDto.getAdvisingBank()))
			{
					addFieldError("advisingBank", "Receiver Bank IFSC is required");
			}
		
		
		if(StringUtils.isNotBlank(lcCanonicalDto.getDraftsAt()))
		{
			if(StringUtils.isNotBlank(lcCanonicalDto.getDraweeIdentifier()) && StringUtils.isNotEmpty(lcCanonicalDto.getDraweeIdentifier()))
			{
					if(lcCanonicalDto.getDraweeIdentifier().equalsIgnoreCase("A"))
					{
						if(StringUtils.isNotBlank(lcCanonicalDto.getDraweeBankCode()) && StringUtils.isNotEmpty(lcCanonicalDto.getDraweeBankCode()))
						{
							
						}
						else
						{
							addFieldError("draweeBankCode","Drawee Bank Code(42A) is Required ");
						}
					}
					else if(lcCanonicalDto.getDraweeIdentifier().equalsIgnoreCase("D"))
					{
						if(StringUtils.isNotBlank(lcCanonicalDto.getDraweeBankNameAddress()) && StringUtils.isNotEmpty(lcCanonicalDto.getDraweeBankNameAddress()))
						{
							
						}
						else
						{
							addFieldError("draweeBankNameAddress","Drawee Name and Address(42D) is Required ");
						}
					}
				}
			else
			{
				addFieldError("DraftsAt", "If Drafts At(42C) Is Present Then Drawee Bank Details(42a) Should also Be Present");
				logger.info("checkLcDraftsAtAndLCDrwbnkRule Validation Failed");
			}
			
		}
		
		if(lcCanonicalDto.getAvailableIdentifier().equalsIgnoreCase("A"))
		{
			if(StringUtils.isNotBlank(lcCanonicalDto.getAuthorisedBankCode()) && StringUtils.isNotEmpty(lcCanonicalDto.getAuthorisedBankCode()))
			{
				
			}
			else
			{
				addFieldError("authorisedBankCode","Available Bank Code(41A) is Required ");
			}
			if(StringUtils.isNotBlank(lcCanonicalDto.getAuthorisationMode()) && StringUtils.isNotEmpty(lcCanonicalDto.getAuthorisationMode()))
			{
				
			}
			else
			{
				addFieldError("authorisationMode","Available Code(41a) is Required ");
			}
			
		}
		
		if(lcCanonicalDto.getAvailableIdentifier().equalsIgnoreCase("D"))
		{
			if(StringUtils.isNotBlank(lcCanonicalDto.getAuthorisedAndAddress()) && StringUtils.isNotEmpty(lcCanonicalDto.getAuthorisedAndAddress()))
			{
				
			}
			else
			{
				addFieldError("authorisedAndAddress","Available Bank Name and Address(41D) is Required ");
			}
			if(StringUtils.isNotBlank(lcCanonicalDto.getAuthorisationMode()) && StringUtils.isNotEmpty(lcCanonicalDto.getAuthorisationMode()))
			{
				
			}
			else
			{
				addFieldError("authorisationMode","Available Code(41a) is Required ");
			}
		}
		
	}
	public void validateTolerance()
	{
		String tolerance = null;
		String mxdCrdAmt = null;
		tolerance = lcCanonicalDto.getPositiveTolerance()+lcCanonicalDto.getNegativeTolerance();
		mxdCrdAmt  = lcCanonicalDto.getMaximumCreditAmount();
		if(StringUtils.isNotBlank(tolerance) && StringUtils.isNotEmpty(tolerance) && tolerance!=null)
		{
			if(StringUtils.isNotBlank(mxdCrdAmt) && StringUtils.isNotEmpty(mxdCrdAmt) && mxdCrdAmt!=null)
			{
				addFieldError("maximumCreditAmount", "Either Maximum Credit Amount(39B) or positiveTolerance/negative Tolerance(39A) present, but not both, may be present");
			}
		}
		if(StringUtils.isNotBlank(mxdCrdAmt) && StringUtils.isNotEmpty(mxdCrdAmt) && mxdCrdAmt!=null)
		{
			if(StringUtils.isNotBlank(tolerance) && StringUtils.isNotEmpty(tolerance) && tolerance!=null)
			{
				addFieldError("positiveTolerance", "Either Maximum Credit Amount(39B) or positiveTolerance/negative Tolerance(39A) present, but not both, may be present");
			}
		}
		
		
	}
	public void networkValdation()
	{
		checkLcDraftsAtAndLCDrwbnkRule();		
		if(StringUtils.isNotBlank(lcCanonicalDto.getShipmentPeriod()) && StringUtils.isNotEmpty(lcCanonicalDto.getShipmentPeriod()) && lcCanonicalDto.getShipmentPeriod()!=null)
		{
			if(lcCanonicalDto.getLatestDateofShipment()!=null)
			{
				addFieldError("shipmentPeriod", "Either field Last Shipment Date(44C) Or Shipment Period(44D). But not both, may be present ");
			}
		}
		else if(lcCanonicalDto.getLatestDateofShipment()!=null)
		{
			if(StringUtils.isNotBlank(lcCanonicalDto.getShipmentPeriod()) && StringUtils.isNotEmpty(lcCanonicalDto.getShipmentPeriod()) && lcCanonicalDto.getShipmentPeriod()!=null)
			{
				addFieldError("shipmentPeriod", "Either field Last Shipment Date(44C) Or Shipment Period(44D). But not both, may be present");
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
		
		if (StringUtils.isNotBlank(lcCanonicalDto.getDraftsAt())) 
		{
			is42Cprsnt = true;
		}
		if(StringUtils.isNotBlank(lcCanonicalDto.getDraweeBankpartyidentifier()+lcCanonicalDto.getDraweeBankAccount()+ lcCanonicalDto.getDraweeBankNameAddress()+ lcCanonicalDto.getDraweeBankCode()))
		{
			is42aprsnt = true;
		}
		if(StringUtils.isNotBlank(lcCanonicalDto.getMixedPaymentDetails()))
		{
			is42Mprsnt = true;
		}
		if(StringUtils.isNotBlank(lcCanonicalDto.getDeferredPaymentDetails()))
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

		int count =0;
		//Case 3
		if(StringUtils.isNotBlank(lcCanonicalDto.getMixedPaymentDetails()))
		{
			 if(is42Pprsnt ==true )
				{
				 count =1;
					addFieldError("DraftsAt", "Mixed Payment Details(42M) Should Not Be Present");
					logger.info("checkLcDraftsAtAndLCDrwbnkRule Validation Failed");
				}
			else if(is42Cprsnt ==true && is42aprsnt ==true && is42Mprsnt==true)
			{
				addFieldError("DraftsAt", "Either Of Drawee Bank(42A) details or Mixed Payment Details(42M) Should Be Present");
				logger.info("checkLcDraftsAtAndLCDrwbnkRule Validation Failed");
				count =1;
			}
			
		}	
		
		if(StringUtils.isNotBlank(lcCanonicalDto.getDeferredPaymentDetails()))
		{
			if(is42Mprsnt ==true && count==0)
			{
				addFieldError("DraftsAt", "Deffered PaymentDetails(42P) Should Not Be Present");
				logger.info("checkLcDraftsAtAndLCDrwbnkRule Validation Failed");
			}
			else if(((is42Cprsnt ==true && is42aprsnt ==true && is42Pprsnt==true) || (is42Mprsnt ==true && is42Pprsnt ==true)) && count ==0)
			{
				addFieldError("DraftsAt", "Either of Drawee Bank(42A) details Or Deferred Payment Details(42P) Should Be Present");
				logger.info("checkLcDraftsAtAndLCDrwbnkRule Validation Failed");
			}
			 
		}
		
	
		logger.info("checkLcDraftsAtAndLCDrwbnkRule END");
	}
	
	

	@SkipValidation
	public String viewOpenLcPayment()
	{
		try{
		displayLCOpenScreen();
		String msgRef = (String) session.get("messageIndex");
		
		LetterOfCreditService letterOfCreditService =  (LetterOfCreditService)  ApplicationContextProvider.getBean("letterOfCreditService");
		LCCanonicalDto canonicalDto = letterOfCreditService.getPreAdviceRepair(msgRef);
		if(canonicalDto.getLcNumber()!=null && StringUtils.isNotBlank(canonicalDto.getLcNumber()) && StringUtils.isNotEmpty(canonicalDto.getLcNumber())){	
			canonicalDto.setRepair(ConstantUtil.REPAIR);
			setALLValueTODTO(canonicalDto);
		
		//setRepairData(ConstantUtil.REPAIR);
		}else
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
		LcOpenService lcOpenService = (LcOpenService) ApplicationContextProvider.getBean("lcOpenService");
		LCCanonicalDto canonicalDto = obj;
		
		lcCanonicalDto.setLcType(canonicalDto.getLcType());
		lcCanonicalDto.setLcNumber(canonicalDto.getLcNumber());
		lcCanonicalDto.setLcExpiryDate(canonicalDto.getLcExpiryDate());
		lcCanonicalDto.setLcExpirePlace(canonicalDto.getLcExpirePlace());
	             	
		lcCanonicalDto.setPositiveTolerance(canonicalDto.getPositiveTolerance());
		lcCanonicalDto.setNegativeTolerance(canonicalDto.getNegativeTolerance());
	            
	     lcCanonicalDto.setDraweeBankAccount(canonicalDto.getDraweeBankAccount());  
		lcCanonicalDto.setMaximumCreditAmount(canonicalDto.getMaximumCreditAmount());
		lcCanonicalDto.setAdditionalAmounts(canonicalDto.getAdditionalAmounts());
		lcCanonicalDto.setAuthorisedBankCode(canonicalDto.getAuthorisedBankCode());
		lcCanonicalDto.setAuthorisedAndAddress(canonicalDto.getAuthorisedAndAddress());
		lcCanonicalDto.setAuthorisationMode(canonicalDto.getAuthorisationMode());
		lcCanonicalDto.setGoodsLoadingDispatchPlace(canonicalDto.getGoodsLoadingDispatchPlace());
		lcCanonicalDto.setGoodsDestination(canonicalDto.getGoodsDestination());
		lcCanonicalDto.setLatestDateofShipment(canonicalDto.getLatestDateofShipment());
		lcCanonicalDto.setShipmentPeriod(canonicalDto.getShipmentPeriod());
		lcCanonicalDto.setShipmentTerms(canonicalDto.getShipmentTerms());
		lcCanonicalDto.setDraftsAt(canonicalDto.getDraftsAt());
		lcCanonicalDto.setDraweeBankpartyidentifier(canonicalDto.getDraweeBankpartyidentifier());
		lcCanonicalDto.setDraweeBankCode(canonicalDto.getDraweeBankCode());
		lcCanonicalDto.setDraweeBankNameAddress(canonicalDto.getDraweeBankNameAddress());
		lcCanonicalDto.setMixedPaymentDetails(canonicalDto.getMixedPaymentDetails());
		lcCanonicalDto.setDeferredPaymentDetails(canonicalDto.getDeferredPaymentDetails());
		lcCanonicalDto.setPartialShipments(canonicalDto.getPartialShipments());
		lcCanonicalDto.setTranshipment(canonicalDto.getTranshipment());
		lcCanonicalDto.setDocumentsRequired(canonicalDto.getDocumentsRequired());
		lcCanonicalDto.setAdditionalConditions(canonicalDto.getAdditionalConditions());
		lcCanonicalDto.setChargeDetails(canonicalDto.getChargeDetails());
		lcCanonicalDto.setPeriodforPresentation(canonicalDto.getPeriodforPresentation());
		lcCanonicalDto.setConfirmationCode(canonicalDto.getConfirmationCode());
		lcCanonicalDto.setInstructionstoPayingBank(canonicalDto.getInstructionstoPayingBank());
		lcCanonicalDto.setNarrative(canonicalDto.getNarrative());
		lcCanonicalDto.setMsgRef(canonicalDto.getMsgRef());       
	        
		lcCanonicalDto.setAdviseThroughBankpartyidentifier(canonicalDto.getAdviseThroughBankpartyidentifier());
		lcCanonicalDto.setAdvisingBank(canonicalDto.getAdvisingBank());
		lcCanonicalDto.setAdviseThroughBankCode(canonicalDto.getAdviseThroughBankCode());
		lcCanonicalDto.setAdviseThroughBankLocation(canonicalDto.getAdviseThroughBankLocation());
		lcCanonicalDto.setAdviseThroughBankName(canonicalDto.getAdviseThroughBankName());
		lcCanonicalDto.setSendertoReceiverInformation(canonicalDto.getSendertoReceiverInformation());
		lcCanonicalDto.setReimbursingBank(canonicalDto.getReimbursingBank());
		lcCanonicalDto.setApplicantNameAddress(canonicalDto.getApplicantNameAddress());
		lcCanonicalDto.setApplicentBankNameandAddr(canonicalDto.getApplicentBankNameandAddr());
		lcCanonicalDto.setApplicantBankCode(canonicalDto.getApplicantBankCode());
		lcCanonicalDto.setApplicantBankpartyidentifier(canonicalDto.getApplicantBankpartyidentifier());
		lcCanonicalDto.setApplicantAccount(canonicalDto.getApplicantAccount());
		lcCanonicalDto.setAdvisingBank(canonicalDto.getAdvisingBank());
		//lcCanonicalDto.setBeneficiaryAccount(canonicalDto.getBeneficiaryAccount());
		lcCanonicalDto.setBeneficiaryNameAddress(canonicalDto.getBeneficiaryNameAddress());
		lcCanonicalDto.setLcAmount(canonicalDto.getLcAmount());
		lcCanonicalDto.setLcCurrency(canonicalDto.getLcCurrency());
		lcCanonicalDto.setAdviseThroughBankAcc(canonicalDto.getAdviseThroughBankAcc());
		lcCanonicalDto.setAdditionalAmountsCovered(canonicalDto.getAdditionalAmountsCovered());
		lcCanonicalDto.setApplicableNarrative(canonicalDto.getApplicableNarrative());
		
		lcCanonicalDto.setRepair(canonicalDto.getRepair());
		lcCanonicalDto.setInitialDispatchPlace(canonicalDto.getInitialDispatchPlace());
		lcCanonicalDto.setFinalDeliveryPlace(canonicalDto.getFinalDeliveryPlace());
		lcCanonicalDto.setApplicableRule(canonicalDto.getApplicableRule());
		lcCanonicalDto.setReimbursingBankCode(canonicalDto.getReimbursingBankCode());
		lcCanonicalDto.setReimbursingBankNameAddress(canonicalDto.getReimbursingBankNameAddress());
		lcCanonicalDto.setSenderCorrespontAcount(canonicalDto.getSenderCorrespontAcount());
		lcCanonicalDto.setSendersCorrespondentPartyIdentifier(canonicalDto.getSendersCorrespondentPartyIdentifier());
		lcCanonicalDto.setSequence(canonicalDto.getSequence());
		lcCanonicalDto.setSequenceofTotal(canonicalDto.getSequenceofTotal());
		lcCanonicalDto.setLcPresdvice(canonicalDto.getLcPresdvice());
		lcCanonicalDto.setApplicentIdentifier(canonicalDto.getApplicentIdentifier());
		lcCanonicalDto.setApplicentBankNameandAddr(canonicalDto.getApplicentBankNameandAddr());
		lcCanonicalDto.setDraweeIdentifier(canonicalDto.getDraweeIdentifier());
		lcCanonicalDto.setReimbursingIdentifier(canonicalDto.getReimbursingIdentifier());
		lcCanonicalDto.setAdvisingIdentifier(canonicalDto.getAdvisingIdentifier());
		lcCanonicalDto.setIssuingBankCode(canonicalDto.getIssuingBankCode());
		lcCanonicalDto.setDescriptionofGoods(canonicalDto.getDescriptionofGoods());//45A
		try{
		lcCanonicalDto.setIssueDate(canonicalDto.getIssueDate());
		}catch (Exception e) {
		e.printStackTrace();
		}
		lcCanonicalDto.setSenderBank(canonicalDto.getSenderBank());
		lcCanonicalDto.setMsgHost(canonicalDto.getMsgHost());
		lcCanonicalDto.setSeqNo(canonicalDto.getSeqNo());
		lcCanonicalDto.setAdviseThroughBankAcc(canonicalDto.getAdviseThroughBankAcc());
		lcCanonicalDto.setPartyIdentifier(canonicalDto.getPartyIdentifier());
		if(canonicalDto.getTxnReference()!= null && !canonicalDto.getTxnReference().equals("")){
				lcCanonicalDto.setReceiverBankReference(canonicalDto.getTxnReference());
			 }else if(canonicalDto.getCustTxnReference()!= null && !canonicalDto.getCustTxnReference().equals("")){
				lcCanonicalDto.setReceiverBankReference(canonicalDto.getCustTxnReference());
			 }else if(canonicalDto.getSndrTxnId()!=null && !canonicalDto.getSndrTxnId().equals("")){
				lcCanonicalDto.setReceiverBankReference(canonicalDto.getSndrTxnId());
			 }
			
		List<LcCommodity> commodityList= (List<LcCommodity>)lcOpenService.getCommodityDetails(canonicalDto.getLcNumber());
		setBgCommoditiesList(lcOpenService.getCommodityDetails(canonicalDto.getLcNumber()));
		if(commodityList!=null && !commodityList.isEmpty()){
			StringBuffer tempStr=new StringBuffer();
			for(LcCommodity strComm : commodityList){
				String str = strComm.getLcCommodity();
				tempStr.append(str);
				tempStr.append("\r\n");
			}
			setRepairCommodity(tempStr.toString());
		}
		
		
	}
	private java.sql.Timestamp convertStringToTimestamp(Date date) {
		if(date != null){
			try{
				return new java.sql.Timestamp(date.getTime());
			}catch(Exception ex){
				ex.printStackTrace();
				return null;
			}
		}else {
			return null;
		}
	}
	public boolean checkLength(String value,int length)
	{
		try{
			if(value.length()==length)
				return true;
			else
				return false;
		}catch (Exception e) {
			return false;
		}
	}
	
public String saveTemplate() {
		
		try {			
			String tempRefKey="";
			PendingAuthorizationService pendingAuthorizationService = (PendingAuthorizationService)ApplicationContextProvider.getBean("pendingAuthorizationService");
			String userId = (String)session.get(WebConstants.CONSTANT_USER_NAME);
			
			tempRefKey = pendingAuthorizationService.getTempRef(new SerializeManager<LCCanonicalDto>().serializeObject((String)session.get(WebConstants.CONSTANT_USER_NAME), lcCanonicalDto),"700","XXX",tempName,"Lc Open(MT-700)",userId);
			pendingAuthorizationService.delimitedTempStringValue(tempRefKey, 1+"", "lcCurrency"+"~"+lcCanonicalDto.getLcCurrency());
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


public String viewTempOpenLcPayment()
{
	try{
	displayLCOpenScreen();
	String msgRef = (String) session.get("messageRef");
	
	LetterOfCreditService letterOfCreditService =  (LetterOfCreditService)  ApplicationContextProvider.getBean("letterOfCreditService");
	LCCanonicalDto canonicalDto = letterOfCreditService.getPreAdviceRepair(msgRef);
	if(canonicalDto.getLcNumber()!=null && StringUtils.isNotBlank(canonicalDto.getLcNumber()) && StringUtils.isNotEmpty(canonicalDto.getLcNumber())){	
		setALLValueTODTO(canonicalDto);
	
	//setRepairData(ConstantUtil.REPAIR);
	}else
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
public String viewTeplateOpenLcPayment()
{
	try{
	displayLCOpenScreen();
	String msgRef = (String) session.get("messageRef");
	
	LetterOfCreditService letterOfCreditService =  (LetterOfCreditService)  ApplicationContextProvider.getBean("letterOfCreditService");
	LCCanonicalDto canonicalDto = letterOfCreditService.getPreAdviceRepair(msgRef);
	if(canonicalDto.getLcNumber()!=null && StringUtils.isNotBlank(canonicalDto.getLcNumber()) && StringUtils.isNotEmpty(canonicalDto.getLcNumber())){	
		setALLValueTODTO(canonicalDto);
	
	}else
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

		@SkipValidation
		public String displayPrintPreviewLCOpenPage()
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
					
					LCCanonicalDto temp = (LCCanonicalDto) new SerializeManager<LCCanonicalDto>().getSerializedObject(tempScreenString);
					List mulDataList = pendingAuthorizationService.getMulScreenData(getTxnRef());
					List<LcCommodity> temportList = new ArrayList<LcCommodity>();
					
					lcCanonicalDto.setLcNumber(temp.getLcNumber());
					lcCanonicalDto.setLcType(temp.getLcType());
					lcCanonicalDto.setAdditionalAmounts(temp.getAdditionalAmounts());
					lcCanonicalDto.setAdviseThroughBankCode(temp.getAdviseThroughBankCode());
					lcCanonicalDto.setAdviseThroughBankLocation(temp.getAdviseThroughBankLocation());
					lcCanonicalDto.setLcCurrency(temp.getLcCurrency());
					lcCanonicalDto.setLatestDateofShipment(temp.getLatestDateofShipment());
					lcCanonicalDto.setLcAmount(temp.getLcAmount());
					lcCanonicalDto.setLcExpirePlace(temp.getLcExpirePlace());
					lcCanonicalDto.setLcExpiryDate(temp.getLcExpiryDate());
					
					lcCanonicalDto.setAdviseThroughBankName(temp.getAdviseThroughBankName());
					lcCanonicalDto.setAdviseThroughBankpartyidentifier(temp.getAdviseThroughBankpartyidentifier());
					lcCanonicalDto.setApplicantNameAddress(temp.getApplicantNameAddress());
					lcCanonicalDto.setAuthorisationMode(temp.getAuthorisationMode());
					lcCanonicalDto.setAuthorisedAndAddress(temp.getAuthorisedAndAddress());
					lcCanonicalDto.setAuthorisedBankCode(temp.getAuthorisedBankCode());
					//lcCanonicalDto.setBeneficiaryAccount(temp.getBeneficiaryAccount());
					lcCanonicalDto.setBeneficiaryNameAddress(temp.getBeneficiaryNameAddress());
					lcCanonicalDto.setMaximumCreditAmount(temp.getMaximumCreditAmount());
					lcCanonicalDto.setNarrative(temp.getNarrative());
					lcCanonicalDto.setNegativeTolerance(temp.getNegativeTolerance());
					lcCanonicalDto.setPositiveTolerance(temp.getPositiveTolerance());
					lcCanonicalDto.setSendertoReceiverInformation(temp.getSendertoReceiverInformation());
					lcCanonicalDto.setShipmentPeriod(temp.getShipmentPeriod());
					lcCanonicalDto.setShipmentTerms(temp.getShipmentTerms());
					
					lcCanonicalDto.setDraftsAt(temp.getDraftsAt());
			        lcCanonicalDto.setDraweeBankpartyidentifier(temp.getDraweeBankpartyidentifier());
			        lcCanonicalDto.setDraweeBankAccount(temp.getDraweeBankAccount());
			        lcCanonicalDto.setDraweeBankCode(temp.getDraweeBankCode());
			        lcCanonicalDto.setDraweeBankNameAddress(temp.getDraweeBankNameAddress());
			        lcCanonicalDto.setMixedPaymentDetails(temp.getMixedPaymentDetails());
			        lcCanonicalDto.setDeferredPaymentDetails(temp.getDeferredPaymentDetails());
			        lcCanonicalDto.setPartialShipments(temp.getPartialShipments());
			        lcCanonicalDto.setTranshipment(temp.getTranshipment());
			        lcCanonicalDto.setDocumentsRequired(temp.getDocumentsRequired());
			        lcCanonicalDto.setAdditionalConditions(temp.getAdditionalConditions());
			        lcCanonicalDto.setChargeDetails(temp.getChargeDetails());
			        lcCanonicalDto.setPeriodforPresentation(temp.getPeriodforPresentation());
			        lcCanonicalDto.setConfirmationCode(temp.getConfirmationCode());
			        lcCanonicalDto.setInstructionstoPayingBank(temp.getInstructionstoPayingBank());
			        lcCanonicalDto.setNarrative(temp.getNarrative());
			        lcCanonicalDto.setMsgRef(temp.getMsgRef());
			        
			        lcCanonicalDto.setAdviseThroughBankpartyidentifier(temp.getAdviseThroughBankpartyidentifier());
					lcCanonicalDto.setAdvisingBank(temp.getAdvisingBank());
					lcCanonicalDto.setAdviseThroughBankCode(temp.getAdviseThroughBankCode());
					lcCanonicalDto.setAdviseThroughBankLocation(temp.getAdviseThroughBankLocation());
					lcCanonicalDto.setAdviseThroughBankAcc(temp.getAdviseThroughBankAcc());
					
					lcCanonicalDto.setAdviseThroughBankName(temp.getAdviseThroughBankName());
					lcCanonicalDto.setSendertoReceiverInformation(temp.getSendertoReceiverInformation());
					lcCanonicalDto.setReimbursingBank(temp.getReimbursingBank());
					lcCanonicalDto.setGoodsDestination(temp.getGoodsDestination());
					lcCanonicalDto.setGoodsLoadingDispatchPlace(temp.getGoodsLoadingDispatchPlace());
					lcCanonicalDto.setApplicantAccount(temp.getApplicantAccount());
					lcCanonicalDto.setApplicantBankCode(temp.getApplicantBankCode());
					lcCanonicalDto.setApplicentBankNameandAddr(temp.getApplicentBankNameandAddr());
					lcCanonicalDto.setApplicantBankpartyidentifier(temp.getApplicantBankpartyidentifier());
					lcCanonicalDto.setPartialShipments(temp.getPartialShipments());
					lcCanonicalDto.setNetChargeAmount(temp.getNetChargeAmount());
					lcCanonicalDto.setPartyIdentifier(temp.getPartyIdentifier());
					lcCanonicalDto.setSenderCorrespontAcount(temp.getSenderCorrespontAcount());
					lcCanonicalDto.setReimbursingBankCode(temp.getReimbursingBankCode());
					lcCanonicalDto.setReimbursingBankNameAddress(temp.getReimbursingBankNameAddress());
					lcCanonicalDto.setSendersCorrespondentCode(temp.getSendersCorrespondentCode());	
					lcCanonicalDto.setInitialDispatchPlace(temp.getInitialDispatchPlace());
					lcCanonicalDto.setFinalDeliveryPlace(temp.getFinalDeliveryPlace());
					lcCanonicalDto.setRepair(temp.getRepair());
					lcCanonicalDto.setApplicableNarrative(temp.getApplicableNarrative());
					lcCanonicalDto.setApplicableRule(temp.getApplicableRule());
					lcCanonicalDto.setMsgHost(temp.getMsgHost());
					lcCanonicalDto.setSeqNo(temp.getSeqNo());
					lcCanonicalDto.setIssueDate(temp.getIssueDate());
					lcCanonicalDto.setComment(temp.getComment());
					lcCanonicalDto.setIssuingBankCode(temp.getIssuingBankCode());
					
					lcCanonicalDto.setSequence(temp.getSequence());
					lcCanonicalDto.setSequenceofTotal(temp.getSequenceofTotal());
					lcCanonicalDto.setLcPresdvice(temp.getLcPresdvice());
					lcCanonicalDto.setApplicentIdentifier(temp.getApplicentIdentifier());
					lcCanonicalDto.setApplicentBankNameandAddr(temp.getApplicentBankNameandAddr());
					lcCanonicalDto.setDraweeIdentifier(temp.getDraweeIdentifier());
					lcCanonicalDto.setReimbursingIdentifier(temp.getReimbursingIdentifier());
					lcCanonicalDto.setAdvisingIdentifier(temp.getAdvisingIdentifier());
					lcCanonicalDto.setDescriptionofGoods(temp.getDescriptionofGoods());//45A
					if(StringUtils.isBlank(temp.getRepair()) && StringUtils.isEmpty(temp.getRepair())){
					for(int i=0;i<mulDataList.size();i++)
					{
						Clob list=(Clob) mulDataList.get(i);
						String[] tempString;
						tempString = list.getSubString(1, (int) list.length()).split("~");
						LcCommodity lcCommodity = new LcCommodity();
						lcCommodity.setLcId(tempString[0]);
						lcCommodity.setLcCommodity(tempString[1]);
						lcCommodity.setMsgRef(tempString[2]);
						temportList.add(lcCommodity);
					}
					setBgCommoditiesList(temportList);
					}else{
						LcOpenService lcOpenService = (LcOpenService) ApplicationContextProvider.getBean("lcOpenService");
						List<LcCommodity> commodityList= (List<LcCommodity>)lcOpenService.getCommodityDetails(temp.getLcNumber());
						StringBuffer tempStr=new StringBuffer();
						for(int i=0;i<mulDataList.size();i++)
						{
							Clob list=(Clob) mulDataList.get(i);
							String[] tempString;
							tempString = list.getSubString(1, (int) list.length()).split("~");
							tempStr.append(tempString[1]);
							tempStr.append("\r\n");
							
						}
							setRepairCommodity(tempStr+"");
					}
					setPrintPreviewTxnRef(getHiddenTxnRef());
				}
				else if(msgRef!=null && !msgRef.isEmpty())
				{
					viewOpenLcPayment();
					return "success";
				}
				else
				{
					
					
					lcCanonicalDto.setLcNumber(lcCanonicalDto.getLcNumber());
					lcCanonicalDto.setLcType(lcCanonicalDto.getLcType());
					lcCanonicalDto.setAdditionalAmounts(lcCanonicalDto.getAdditionalAmounts());
					lcCanonicalDto.setAdviseThroughBankCode(lcCanonicalDto.getAdviseThroughBankCode());
					lcCanonicalDto.setAdviseThroughBankLocation(lcCanonicalDto.getAdviseThroughBankLocation());
					lcCanonicalDto.setLcCurrency(lcCanonicalDto.getLcCurrency());
					lcCanonicalDto.setLatestDateofShipment(lcCanonicalDto.getLatestDateofShipment());
					lcCanonicalDto.setLcAmount(lcCanonicalDto.getLcAmount());
					lcCanonicalDto.setLcExpirePlace(lcCanonicalDto.getLcExpirePlace());
					lcCanonicalDto.setLcExpiryDate(lcCanonicalDto.getLcExpiryDate());
					
					lcCanonicalDto.setAdviseThroughBankName(lcCanonicalDto.getAdviseThroughBankName());
					lcCanonicalDto.setAdviseThroughBankpartyidentifier(lcCanonicalDto.getAdviseThroughBankpartyidentifier());
					lcCanonicalDto.setApplicantNameAddress(lcCanonicalDto.getApplicantNameAddress());
					lcCanonicalDto.setAuthorisationMode(lcCanonicalDto.getAuthorisationMode());
					lcCanonicalDto.setAuthorisedAndAddress(lcCanonicalDto.getAuthorisedAndAddress());
					lcCanonicalDto.setAuthorisedBankCode(lcCanonicalDto.getAuthorisedBankCode());
					//lcCanonicalDto.setBeneficiaryAccount(lcCanonicalDto.getBeneficiaryAccount());
					lcCanonicalDto.setBeneficiaryNameAddress(lcCanonicalDto.getBeneficiaryNameAddress());
					lcCanonicalDto.setMaximumCreditAmount(lcCanonicalDto.getMaximumCreditAmount());
					lcCanonicalDto.setNarrative(lcCanonicalDto.getNarrative());
					lcCanonicalDto.setNegativeTolerance(lcCanonicalDto.getNegativeTolerance());
					lcCanonicalDto.setPositiveTolerance(lcCanonicalDto.getPositiveTolerance());
					lcCanonicalDto.setSendertoReceiverInformation(lcCanonicalDto.getSendertoReceiverInformation());
					lcCanonicalDto.setShipmentPeriod(lcCanonicalDto.getShipmentPeriod());
					lcCanonicalDto.setShipmentTerms(lcCanonicalDto.getShipmentTerms());
					
					lcCanonicalDto.setDraftsAt(lcCanonicalDto.getDraftsAt());
			        lcCanonicalDto.setDraweeBankpartyidentifier(lcCanonicalDto.getDraweeBankpartyidentifier());
			        lcCanonicalDto.setDraweeBankAccount(lcCanonicalDto.getDraweeBankAccount());
			        lcCanonicalDto.setDraweeBankCode(lcCanonicalDto.getDraweeBankCode());
			        lcCanonicalDto.setDraweeBankNameAddress(lcCanonicalDto.getDraweeBankNameAddress());
			        lcCanonicalDto.setMixedPaymentDetails(lcCanonicalDto.getMixedPaymentDetails());
			        lcCanonicalDto.setDeferredPaymentDetails(lcCanonicalDto.getDeferredPaymentDetails());
			        lcCanonicalDto.setPartialShipments(lcCanonicalDto.getPartialShipments());
			        lcCanonicalDto.setTranshipment(lcCanonicalDto.getTranshipment());
			        lcCanonicalDto.setDocumentsRequired(lcCanonicalDto.getDocumentsRequired());
			        lcCanonicalDto.setAdditionalConditions(lcCanonicalDto.getAdditionalConditions());
			        lcCanonicalDto.setChargeDetails(lcCanonicalDto.getChargeDetails());
			        lcCanonicalDto.setPeriodforPresentation(lcCanonicalDto.getPeriodforPresentation());
			        lcCanonicalDto.setConfirmationCode(lcCanonicalDto.getConfirmationCode());
			        lcCanonicalDto.setInstructionstoPayingBank(lcCanonicalDto.getInstructionstoPayingBank());
			        lcCanonicalDto.setNarrative(lcCanonicalDto.getNarrative());
			        lcCanonicalDto.setMsgRef(lcCanonicalDto.getMsgRef());
			        
			        lcCanonicalDto.setAdviseThroughBankpartyidentifier(lcCanonicalDto.getAdviseThroughBankpartyidentifier());
					lcCanonicalDto.setAdvisingBank(lcCanonicalDto.getAdvisingBank());
					lcCanonicalDto.setAdviseThroughBankCode(lcCanonicalDto.getAdviseThroughBankCode());
					lcCanonicalDto.setAdviseThroughBankLocation(lcCanonicalDto.getAdviseThroughBankLocation());
					lcCanonicalDto.setAdviseThroughBankAcc(lcCanonicalDto.getAdviseThroughBankAcc());
					
					lcCanonicalDto.setAdviseThroughBankName(lcCanonicalDto.getAdviseThroughBankName());
					lcCanonicalDto.setSendertoReceiverInformation(lcCanonicalDto.getSendertoReceiverInformation());
					lcCanonicalDto.setReimbursingBank(lcCanonicalDto.getReimbursingBank());
					lcCanonicalDto.setGoodsDestination(lcCanonicalDto.getGoodsDestination());
					lcCanonicalDto.setGoodsLoadingDispatchPlace(lcCanonicalDto.getGoodsLoadingDispatchPlace());
					lcCanonicalDto.setApplicantAccount(lcCanonicalDto.getApplicantAccount());
					lcCanonicalDto.setApplicantBankCode(lcCanonicalDto.getApplicantBankCode());
					lcCanonicalDto.setApplicentBankNameandAddr(lcCanonicalDto.getApplicentBankNameandAddr());
					lcCanonicalDto.setApplicantBankpartyidentifier(lcCanonicalDto.getApplicantBankpartyidentifier());
					lcCanonicalDto.setPartialShipments(lcCanonicalDto.getPartialShipments());
					lcCanonicalDto.setNetChargeAmount(lcCanonicalDto.getNetChargeAmount());
					lcCanonicalDto.setPartyIdentifier(lcCanonicalDto.getPartyIdentifier());
					lcCanonicalDto.setSenderCorrespontAcount(lcCanonicalDto.getSenderCorrespontAcount());
					lcCanonicalDto.setReimbursingBankCode(lcCanonicalDto.getReimbursingBankCode());
					lcCanonicalDto.setReimbursingBankNameAddress(lcCanonicalDto.getReimbursingBankNameAddress());
					lcCanonicalDto.setSendersCorrespondentCode(lcCanonicalDto.getSendersCorrespondentCode());	
					lcCanonicalDto.setInitialDispatchPlace(lcCanonicalDto.getInitialDispatchPlace());
					lcCanonicalDto.setFinalDeliveryPlace(lcCanonicalDto.getFinalDeliveryPlace());
					lcCanonicalDto.setRepair(lcCanonicalDto.getRepair());
					lcCanonicalDto.setApplicableNarrative(lcCanonicalDto.getApplicableNarrative());
					lcCanonicalDto.setApplicableRule(lcCanonicalDto.getApplicableRule());
					lcCanonicalDto.setMsgHost(lcCanonicalDto.getMsgHost());
					lcCanonicalDto.setSeqNo(lcCanonicalDto.getSeqNo());
					lcCanonicalDto.setIssueDate(lcCanonicalDto.getIssueDate());
					lcCanonicalDto.setComment(lcCanonicalDto.getComment());
					lcCanonicalDto.setIssuingBankCode(lcCanonicalDto.getIssuingBankCode());
					
					lcCanonicalDto.setSequence(lcCanonicalDto.getSequence());
					lcCanonicalDto.setSequenceofTotal(lcCanonicalDto.getSequenceofTotal());
					lcCanonicalDto.setLcPresdvice(lcCanonicalDto.getLcPresdvice());
					lcCanonicalDto.setApplicentIdentifier(lcCanonicalDto.getApplicentIdentifier());
					lcCanonicalDto.setApplicentBankNameandAddr(lcCanonicalDto.getApplicentBankNameandAddr());
					lcCanonicalDto.setDraweeIdentifier(lcCanonicalDto.getDraweeIdentifier());
					lcCanonicalDto.setReimbursingIdentifier(lcCanonicalDto.getReimbursingIdentifier());
					lcCanonicalDto.setAdvisingIdentifier(lcCanonicalDto.getAdvisingIdentifier());
					lcCanonicalDto.setAdvisingIdentifier(lcCanonicalDto.getAdvisingIdentifier());
					lcCanonicalDto.setDescriptionofGoods(lcCanonicalDto.getDescriptionofGoods());//45A
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
				lcCanonicalDto.setLcType("");
				lcCanonicalDto.setAdditionalAmounts("");
				lcCanonicalDto.setAdviseThroughBankCode("");
				lcCanonicalDto.setAdviseThroughBankLocation("");
				lcCanonicalDto.setLcCurrency("");
				lcCanonicalDto.setLatestDateofShipment(null);
				lcCanonicalDto.setLcAmount(BigDecimal.ZERO);
				lcCanonicalDto.setLcExpirePlace("");
				lcCanonicalDto.setLcExpiryDate(null);
				
				lcCanonicalDto.setAdviseThroughBankName("");
				lcCanonicalDto.setAdviseThroughBankpartyidentifier("");
				lcCanonicalDto.setApplicantNameAddress("");
				lcCanonicalDto.setAuthorisationMode("");
				lcCanonicalDto.setAuthorisedAndAddress("");
				lcCanonicalDto.setAuthorisedBankCode("");
				//lcCanonicalDto.setBeneficiaryAccount("");
				lcCanonicalDto.setBeneficiaryNameAddress("");
				lcCanonicalDto.setMaximumCreditAmount("");
				lcCanonicalDto.setNarrative("");
				lcCanonicalDto.setNegativeTolerance("");
				lcCanonicalDto.setPositiveTolerance("");
				lcCanonicalDto.setSendertoReceiverInformation("");
				lcCanonicalDto.setShipmentPeriod("");
				lcCanonicalDto.setShipmentTerms("");
				
				lcCanonicalDto.setDraftsAt("");
		        lcCanonicalDto.setDraweeBankpartyidentifier("");
		        lcCanonicalDto.setDraweeBankAccount("");
		        lcCanonicalDto.setDraweeBankCode("");
		        lcCanonicalDto.setDraweeBankNameAddress("");
		        lcCanonicalDto.setMixedPaymentDetails("");
		        lcCanonicalDto.setDeferredPaymentDetails("");
		        lcCanonicalDto.setPartialShipments("");
		        lcCanonicalDto.setTranshipment("");
		        lcCanonicalDto.setDocumentsRequired("");
		        lcCanonicalDto.setAdditionalConditions("");
		        lcCanonicalDto.setChargeDetails("");
		        lcCanonicalDto.setPeriodforPresentation("");
		        lcCanonicalDto.setConfirmationCode("");
		        lcCanonicalDto.setInstructionstoPayingBank("");
		        lcCanonicalDto.setNarrative("");
		        lcCanonicalDto.setMsgRef("");
		        
		        lcCanonicalDto.setAdviseThroughBankpartyidentifier("");
				lcCanonicalDto.setAdvisingBank("");
				lcCanonicalDto.setAdviseThroughBankCode("");
				lcCanonicalDto.setAdviseThroughBankLocation("");
				lcCanonicalDto.setAdviseThroughBankAcc("");
				
				lcCanonicalDto.setAdviseThroughBankName("");
				lcCanonicalDto.setSendertoReceiverInformation("");
				lcCanonicalDto.setReimbursingBank("");
				lcCanonicalDto.setGoodsDestination("");
				lcCanonicalDto.setGoodsLoadingDispatchPlace("");
				lcCanonicalDto.setApplicantAccount("");
				lcCanonicalDto.setApplicantBankCode("");
				lcCanonicalDto.setApplicentBankNameandAddr("");
				lcCanonicalDto.setApplicantBankpartyidentifier("");
				lcCanonicalDto.setPartialShipments("");
				lcCanonicalDto.setNetChargeAmount(BigDecimal.ZERO);
				lcCanonicalDto.setPartyIdentifier("");
				lcCanonicalDto.setSenderCorrespontAcount("");
				lcCanonicalDto.setReimbursingBankCode("");
				lcCanonicalDto.setReimbursingBankNameAddress("");
				lcCanonicalDto.setSendersCorrespondentCode("");	
				lcCanonicalDto.setInitialDispatchPlace("");
				lcCanonicalDto.setFinalDeliveryPlace("");
				lcCanonicalDto.setRepair("");
				lcCanonicalDto.setApplicableNarrative("");
				lcCanonicalDto.setApplicableRule("");
				lcCanonicalDto.setMsgHost("");
				lcCanonicalDto.setSeqNo("");
				lcCanonicalDto.setIssueDate(null);
				lcCanonicalDto.setComment("");
				lcCanonicalDto.setSenderBank("");
				lcCanonicalDto.setSequence("");
				lcCanonicalDto.setSequenceofTotal("");
				lcCanonicalDto.setLcPresdvice("");
				lcCanonicalDto.setApplicentIdentifier("");
				lcCanonicalDto.setApplicentBankNameandAddr("");
				lcCanonicalDto.setDraweeIdentifier("");
				lcCanonicalDto.setReimbursingIdentifier("");
				lcCanonicalDto.setAdvisingIdentifier("");
				lcCanonicalDto.setDescriptionofGoods("");//45A
			return "success";
			}catch (Exception e) {
				logger.error(e,e);
				return "input";
			}

		}	
		
		
		@SkipValidation
		public String exportToExcelLcOpen() throws Exception
		{
			PendingAuthorizationService pendingAuthorizationService = (PendingAuthorizationService)ApplicationContextProvider.getBean("pendingAuthorizationService");
			
		try{
			displayPrintPreviewLCOpenPage();
			String reportFile = "LC_Open_(MT-700)";
			SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyyHHmmss");
			String currentDateTime = sdf.format(new Date());
			String reportFileName = reportFile+"_"+currentDateTime+"".concat(".xls");
			setReportFile(reportFileName);
			System.out.println("reportFileName is "+reportFileName);
			List mulDataList = pendingAuthorizationService.getMulScreenData(getTxnRef());
			List<String> commodityList = new  ArrayList<String>();
			for(int i=0;i<mulDataList.size();i++)
			{
				Clob list=(Clob) mulDataList.get(i);
				String[] tempString;
				
				tempString = list.getSubString(1, (int) list.length()).split("~");
				commodityList.add(tempString[1]);
			}
			ExportToExcelUtil exportToExcelUtil = new ExportToExcelUtil();
			HSSFWorkbook myWorkBook = exportToExcelUtil.generateExportToExcel(lcCanonicalDto, null, null, reportFile, 700, "XXX");
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
		public String generatePDFLcOpen()
		{

			displayPrintPreviewLCOpenPage();
			String reportName ="LC Open(MT-700)";
			Map<String,String> fieldNamesMap = new HashMap<String,String>();
			
			try{
				
				fieldNamesMap.put("label.MB.IssuingBranchIFSC", getText("label.MB.IssuingBranchIFSC"));
				fieldNamesMap.put("label.MB.Benefifsc", getText("label.MB.Benefifsc"));
				
				fieldNamesMap.put("label.700XXX.ppseqofTotal", getText("label.700XXX.ppseqofTotal"));
				fieldNamesMap.put("label.700XXX.ppformofDoc", getText("label.700XXX.ppformofDoc"));
				fieldNamesMap.put("label.700XXX.pplcNumber", getText("label.700XXX.pplcNumber"));
				fieldNamesMap.put("label.700XXX.ppreferenceToPreadvice", getText("label.700XXX.ppreferenceToPreadvice"));
				fieldNamesMap.put("label.700XXX.ppdateofIssue", getText("label.700XXX.ppdateofIssue"));
				fieldNamesMap.put("label.700XXX.ppapplicableRules", getText("label.700XXX.ppapplicableRules"));
				fieldNamesMap.put("label.700XXX.ppdateofExp", getText("label.700XXX.ppdateofExp"));
				fieldNamesMap.put("label.700XXX.ppplaceofExp", getText("label.700XXX.ppplaceofExp"));
				fieldNamesMap.put("label.700XXX.ppapplicentBank", getText("label.700XXX.ppapplicentBank"));
				fieldNamesMap.put("label.700XXX.ppapplicentBankCode", getText("label.700XXX.ppapplicentBankCode"));
				fieldNamesMap.put("label.700XXX.ppapplicentBankNameAddress", getText("label.700XXX.ppapplicentBankNameAddress"));
				fieldNamesMap.put("label.700XXX.ppapplicent", getText("label.700XXX.ppapplicent"));
				fieldNamesMap.put("label.700XXX.ppbeneficiaryNameandAddress", getText("label.700XXX.ppbeneficiaryNameandAddress"));
				fieldNamesMap.put("label.700XXX.ppcurreyCode", getText("label.700XXX.ppcurreyCode"));
				fieldNamesMap.put("label.700XXX.ppamount", getText("label.700XXX.ppamount"));
				fieldNamesMap.put("label.700XXX.pppositiveTolerance", getText("label.700XXX.pppositiveTolerance"));
				fieldNamesMap.put("label.700XXX.ppnegativeTolerance", getText("label.700XXX.ppnegativeTolerance"));
				fieldNamesMap.put("label.700XXX.ppmaximumCreditAmount", getText("label.700XXX.ppmaximumCreditAmount"));
				fieldNamesMap.put("label.700XXX.ppadditinalAmtCoverd", getText("label.700XXX.ppadditinalAmtCoverd"));
				fieldNamesMap.put("label.700XXX.ppavailable", getText("label.700XXX.ppavailable"));
				fieldNamesMap.put("label.700XXX.ppavailableBankCode", getText("label.700XXX.ppavailableBankCode"));
				fieldNamesMap.put("label.700XXX.ppavailableCode", getText("label.700XXX.ppavailableCode"));
				fieldNamesMap.put("label.700XXX.ppavailableBankNameandAddress", getText("label.700XXX.ppavailableBankNameandAddress"));
				fieldNamesMap.put("label.700XXX.ppdraftsAt", getText("label.700XXX.ppdraftsAt"));
				fieldNamesMap.put("label.700XXX.ppDrawee", getText("label.700XXX.ppDrawee"));
				fieldNamesMap.put("label.700XXX.ppdraweeBankCode", getText("label.700XXX.ppdraweeBankCode"));
				fieldNamesMap.put("label.700XXX.ppdreaweeNameandAddress", getText("label.700XXX.ppdreaweeNameandAddress"));
				fieldNamesMap.put("label.700XXX.ppmixedPaymnetDetails", getText("label.700XXX.ppmixedPaymnetDetails"));
				fieldNamesMap.put("label.700XXX.ppdeferredPaymentDetails", getText("label.700XXX.ppdeferredPaymentDetails"));
				fieldNamesMap.put("label.700XXX.pppartialPayments", getText("label.700XXX.pppartialPayments"));
				fieldNamesMap.put("label.700XXX.pptransshipment", getText("label.700XXX.pptransshipment"));
				fieldNamesMap.put("label.700XXX.ppplaceofDispatch", getText("label.700XXX.ppplaceofDispatch"));
				fieldNamesMap.put("label.700XXX.ppportofLoading", getText("label.700XXX.ppportofLoading"));
				fieldNamesMap.put("label.700XXX.ppportofDischarge", getText("label.700XXX.ppportofDischarge"));
				fieldNamesMap.put("label.700XXX.ppplaceofFinalDestination", getText("label.700XXX.ppplaceofFinalDestination"));
				fieldNamesMap.put("label.700XXX.pplatestDateofShipment", getText("label.700XXX.pplatestDateofShipment"));
				fieldNamesMap.put("label.700XXX.ppshipmentPeriod", getText("label.700XXX.ppshipmentPeriod"));
				fieldNamesMap.put("label.700XXX.ppdescriptionofGoods", getText("label.700XXX.ppdescriptionofGoods"));
				fieldNamesMap.put("label.700XXX.ppdocumentsRequired", getText("label.700XXX.ppdocumentsRequired"));
				fieldNamesMap.put("label.700XXX.ppadditinalConditions", getText("label.700XXX.ppadditinalConditions"));
				fieldNamesMap.put("label.700XXX.ppcharges", getText("label.700XXX.ppcharges"));
				fieldNamesMap.put("label.700XXX.ppperiodForPresentaion", getText("label.700XXX.ppperiodForPresentaion"));
				fieldNamesMap.put("label.700XXX.ppconfirmation", getText("label.700XXX.ppconfirmation"));
				fieldNamesMap.put("label.700XXX.ppreimbursing", getText("label.700XXX.ppreimbursing"));
				fieldNamesMap.put("label.700XXX.ppreimbursingBankCode", getText("label.700XXX.ppreimbursingBankCode"));
				fieldNamesMap.put("label.700XXX.ppreimbursingNameandAddress", getText("label.700XXX.ppreimbursingNameandAddress"));
				fieldNamesMap.put("label.700XXX.ppinstructionToPaying", getText("label.700XXX.ppinstructionToPaying"));
				fieldNamesMap.put("label.700XXX.ppadvisingThrough", getText("label.700XXX.ppadvisingThrough"));
				fieldNamesMap.put("label.700XXX.ppadvisingThroughBankCode", getText("label.700XXX.ppadvisingThroughBankCode"));
				fieldNamesMap.put("label.700XXX.ppadvisingThroughBankLocation", getText("label.700XXX.ppadvisingThroughBankLocation"));
				fieldNamesMap.put("label.700XXX.ppadvisingThroughBankNameAndAddress", getText("label.700XXX.ppadvisingThroughBankNameAndAddress"));
				fieldNamesMap.put("label.700XXX.ppsenderToReceiverInfo", getText("label.700XXX.ppsenderToReceiverInfo"));

				PaymentPDFGeneratationUtil paymentPDFGeneratationUtil = new PaymentPDFGeneratationUtil();
				paymentPDFGeneratationUtil.setServletRequest(servletRequest);
				paymentPDFGeneratationUtil.setReportName(reportName);
				
				paymentPDFGeneratationUtil.generatePaymentPDFReport(lcCanonicalDto,null, null,fieldNamesMap, 700, "XXX");
				
			}
			catch (Exception exception) {
				AuditServiceUtil.logException(exception,logger);
			}
			addActionError("Unable to Generate PDF file! Please try again");
			return "input";
		}
		
}
