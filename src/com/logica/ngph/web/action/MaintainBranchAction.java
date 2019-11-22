package com.logica.ngph.web.action;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.context.ApplicationContextException;


import com.logica.ngph.Entity.Functions;
import com.logica.ngph.Entity.SecUsers;
import com.logica.ngph.common.dtos.EventAudit;
import com.logica.ngph.common.dtos.EventMaster;
import com.logica.ngph.dtos.MaintainBranchesDTO;
import com.logica.ngph.service.AuditService;
import com.logica.ngph.service.MaintainBranchesService;
import com.logica.ngph.service.PaymentMessageService;
import com.logica.ngph.service.PendingAuthorizationService;
import com.logica.ngph.web.utils.ApplicationContextProvider;
import com.logica.ngph.web.utils.SerializeManager;
import com.logica.ngph.web.utils.UserRolesAccess;
import com.logica.ngph.web.utils.WebConstants;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
/**
 * 
 * @author guptast
 *
 */
public class MaintainBranchAction extends ActionSupport implements ModelDriven<MaintainBranchesDTO>, SessionAware{
	
	
	private static Logger logger = Logger.getLogger(MaintainBranchAction.class);
	private static final long serialVersionUID = 1L;
	private Map<String, Object> session;
	//set when control come for pending authorization
	private String hiddenTxnRef;
	//set to display submit/approve or reject on screen
	private String checkForSubmit;
	//set sequence no which is selected in pending authorization
	private String txnRef;
	private String saveAction;
	
	//Disabling/enabling approve and reject button of valid/invalid users 
	private boolean validUserToApprove;
	//fetch the record for all the branches
	private List searchList;
	
	private String code;
	
	// Screen comparison 
	private String flagForNewData;
	private String old_NewScreen;
	//Screen comparison End
	public String getOld_NewScreen() {
		return old_NewScreen;
	}
	public void setOld_NewScreen(String old_NewScreen) {
		this.old_NewScreen = old_NewScreen;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public List getSearchList() {
		return searchList;
	}
	public void setSearchList(List searchList) {
		this.searchList = searchList;
		
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

	MaintainBranchesDTO maintainBranchesDTO =  new MaintainBranchesDTO();
	
	public Map<String, Object> getSession() {
		return session;
	}
	
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
	
	private List<String> addressTypeList = new ArrayList<String>();
	
	/**
	* This method is used to load the Branch Maintenance Screen
	* In this it will fetch record for database/applicationProperties and display to drop down.  
	* @return success on loading other details
	* @return input if any exceptions comes
	* @return notValidUser for unauthorized user
	* 
	*/
	public String getMaintainBranchScreen()
	{
		try{
			UserRolesAccess access = (UserRolesAccess) ApplicationContextProvider.getBean("notValidUser");
			boolean roleAccess=	access.checkForRole(((List<Functions>)session.get("restrictedFunctionsList")),"function.access.branchmaintenance");
				if(roleAccess){
					return "notValidUser";
				}
			String[] split = getText("AddressType").split(",");
			setAddressTypeList(Arrays.asList(split));
			session.get(WebConstants.CONSTANT_USER_NAME);
			maintainBranchesDTO.setActionPerform("ADD");
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
	
	/**
	 * This Method se
	 * */
	
	public String searchBranchAction()
	{
		MaintainBranchesService maintainBranchesService = (MaintainBranchesService) ApplicationContextProvider.getBean("maintainBranchesService");
		setSearchList(maintainBranchesService.getALLBranch(getCode()));
		return "success";
	}
	
	public String displayDetails()
	{
		MaintainBranchesService maintainBranchesService = (MaintainBranchesService) ApplicationContextProvider.getBean("maintainBranchesService");
		
		MaintainBranchesDTO temp =(MaintainBranchesDTO) maintainBranchesService.getALLBranch(maintainBranchesDTO.getBranchCode());
		maintainBranchesDTO.setActionPerform(temp.getActionPerform());
		maintainBranchesDTO.setAddressLine(temp.getAddressLine());
		maintainBranchesDTO.setAddressType(temp.getAddressType());
		maintainBranchesDTO.setBranchBIC(temp.getBranchBIC());
		maintainBranchesDTO.setBranchCode(temp.getBranchCode());
		maintainBranchesDTO.setBranchIFSC(temp.getBranchIFSC());
		maintainBranchesDTO.setBranchName(temp.getBranchName());
		maintainBranchesDTO.setBuildingNumber(temp.getBuildingNumber());
		maintainBranchesDTO.setCountryCode(temp.getCountryCode());
		maintainBranchesDTO.setCountrySubDivision(temp.getCountrySubDivision());
		maintainBranchesDTO.setDepartment(temp.getDepartment());
		maintainBranchesDTO.setPostCode(temp.getPostCode());
		maintainBranchesDTO.setStreetName(temp.getStreetName());
		maintainBranchesDTO.setSubDepartment(temp.getSubDepartment());
		maintainBranchesDTO.setTownName(temp.getTownName());
		
		
		return "success";
	}
	public String getMaintainBranchApproval()
	{
		try{
			String txnKey="";
			AuditService	auditService = (AuditService)ApplicationContextProvider.getBean("auditService");
			PendingAuthorizationService pendingAuthorizationService = (PendingAuthorizationService)ApplicationContextProvider.getBean("pendingAuthorizationService");
			String userId = (String)session.get(WebConstants.CONSTANT_USER_NAME);
			MaintainBranchesService maintainBranchesService = (MaintainBranchesService) ApplicationContextProvider.getBean("maintainBranchesService");
			boolean isPresent =  maintainBranchesService.isBranchCodePresent(maintainBranchesDTO.getBranchCode());
			if(StringUtils.isNotEmpty(maintainBranchesDTO.getBranchCode()) && StringUtils.isNotBlank(maintainBranchesDTO.getBranchCode()) && StringUtils.isNotEmpty(maintainBranchesDTO.getBranchName()) && StringUtils.isNotBlank(maintainBranchesDTO.getBranchName())
				&& (StringUtils.isNotEmpty(maintainBranchesDTO.getBranchBIC()) && StringUtils.isNotBlank(maintainBranchesDTO.getBranchBIC()) ||( StringUtils.isNotEmpty(maintainBranchesDTO.getBranchIFSC()) && StringUtils.isNotBlank(maintainBranchesDTO.getBranchIFSC())))	
			){
			if(maintainBranchesDTO.getActionPerform().equals("ADD"))
			{
				if(isPresent==true){
					txnKey = pendingAuthorizationService.getTranRef(new SerializeManager<MaintainBranchesDTO>().serializeObject((String)session.get(WebConstants.CONSTANT_USER_NAME), maintainBranchesDTO),"Maintain Branches",userId);
					auditService.saveEventAuditData(getEventAuditDtoForSubmit());
				}else{
					addFieldError("action", "Banch Code Is AlReady Available");
				return "input";
				}
				 
			}
			else{
				if(isPresent==false)
				{
					txnKey = pendingAuthorizationService.getTranRef(new SerializeManager<MaintainBranchesDTO>().serializeObject((String)session.get(WebConstants.CONSTANT_USER_NAME), maintainBranchesDTO),"Maintain Branches",userId);
					auditService.saveEventAuditData(getEventAuditDtoForSubmit());
				}else{
					addFieldError("action", "Branch Code Is Not Avilable In DB");
					return "input";
				}
			}
			}else
			{
				if(StringUtils.isEmpty(maintainBranchesDTO.getBranchCode()) && StringUtils.isBlank(maintainBranchesDTO.getBranchCode()))
				addFieldError("branchCode", "Branch Code Is Required");
				else if(StringUtils.isEmpty(maintainBranchesDTO.getBranchName()) && StringUtils.isBlank(maintainBranchesDTO.getBranchName()))
					addFieldError("branchCode", "Branch Name Is Required");
				else
					addFieldError("branchCode", "Either BIC/IFSC Is Required");
				
				return "input";
			}
			pendingAuthorizationService=null;
			session.put("screenRef", txnKey);
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
	
	public String getMaintainBranchAuthorization()
	{
	try{
		PendingAuthorizationService pendingAuthorizationService = (PendingAuthorizationService)ApplicationContextProvider.getBean("pendingAuthorizationService");
		getMaintainBranchScreen();
		setCheckForSubmit("Display_Approve_Reject");
		String userId = pendingAuthorizationService.getCreatedUser(getTxnRef());
		String userRole = pendingAuthorizationService.getUserType((String)session.get(WebConstants.CONSTANT_USER_NAME));
		if((((String)session.get(WebConstants.CONSTANT_USER_NAME)).equals(userId) || userRole.equals("T"))){
			setValidUserToApprove(false);
		} else {
			setValidUserToApprove(true);
		}
		String tempScreenString =pendingAuthorizationService.getScreenReturn(getTxnRef());
		MaintainBranchesDTO temp= (MaintainBranchesDTO) new SerializeManager<MaintainBranchesDTO>().getSerializedObject(tempScreenString);
		
		maintainBranchesDTO.setActionPerform(temp.getActionPerform());
		maintainBranchesDTO.setAddressLine(temp.getAddressLine());
		maintainBranchesDTO.setAddressType(temp.getAddressType());
		maintainBranchesDTO.setBranchBIC(temp.getBranchBIC());
		maintainBranchesDTO.setBranchCode(temp.getBranchCode());
		maintainBranchesDTO.setBranchIFSC(temp.getBranchIFSC());
		maintainBranchesDTO.setBranchName(temp.getBranchName());
		maintainBranchesDTO.setBuildingNumber(temp.getBuildingNumber());
		maintainBranchesDTO.setCountryCode(temp.getCountryCode());
		maintainBranchesDTO.setCountrySubDivision(temp.getCountrySubDivision());
		maintainBranchesDTO.setDepartment(temp.getDepartment());
		maintainBranchesDTO.setPostCode(temp.getPostCode());
		maintainBranchesDTO.setStreetName(temp.getStreetName());
		maintainBranchesDTO.setSubDepartment(temp.getSubDepartment());
		maintainBranchesDTO.setTownName(temp.getTownName());
		maintainBranchesDTO.setAddressRef(temp.getAddressRef());
		maintainBranchesDTO.setSubDepartment(temp.getSubDepartment());
		session.put("branchObject", maintainBranchesDTO);
		//Screen comparison
		setOld_NewScreen(null);
		setHiddenTxnRef(getTxnRef());
		System.out.println(getTxnRef());
		pendingAuthorizationService = null;
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
	
	/**
	 * This method save all the value which is being enter in the screen
	 * according to the action 
	 * if saveAction flag is reject it will reject the transaction and 
	 * change the statue of particalure transaction to "p" in ta_screen data table   
	 * and @return rejected
	 * else
	 * it will add/modify the record in database and change the status 
	 * to "a" in TA_Screen_data for a particalure transaction
	 * And @return success 
	 */ 
	public String saveMaintainBranches(){
		try{
			MaintainBranchesService maintainBranchesService = (MaintainBranchesService) ApplicationContextProvider.getBean("maintainBranchesService");
			AuditService	auditService = (AuditService)ApplicationContextProvider.getBean("auditService");
			String returnString = null;
			PendingAuthorizationService pendingAuthorizationService = (PendingAuthorizationService)ApplicationContextProvider.getBean("pendingAuthorizationService");	
			boolean isPresent =  maintainBranchesService.isBranchCodePresent(maintainBranchesDTO.getBranchCode());
			if(getSaveAction().equals("Reject"))
			{
				pendingAuthorizationService.changeStatus(getSaveAction(), getHiddenTxnRef());
				auditService.saveEventAuditData(getEventAuditDtoForReject());
				return "rejected";
			}
			if(maintainBranchesDTO.getActionPerform().equals("ADD"))
			{
				if(isPresent==true){
					returnString= maintainBranchesService.saveData((MaintainBranchesDTO)session.get("branchObject"));
					auditService.saveEventAuditData(getEventAuditDtoForApprove());
				
				}else{
					addFieldError("action", "Branch Code Is All Ready Available");
				return "input";
				}
				 
			}
			else{
				if(isPresent==false)
				{
					returnString= maintainBranchesService.saveData((MaintainBranchesDTO)session.get("branchObject"));
					auditService.saveEventAuditData(getEventAuditDtoForApprove());
				}else{
					addFieldError("action", "Banch Code Is Not Available In DB");
					return "input";
				}
			}
			if(returnString!=null && !returnString.equals(null)){
				
				pendingAuthorizationService.changeStatus(getSaveAction(), getHiddenTxnRef());
			}
			
			pendingAuthorizationService = null;
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
		
	private EventAudit getEventAuditDtoForSubmit(){
		EventAudit eventAudit = getEventAuditDto();
		eventAudit.setAuditEventId("NGPHGUIBRN0001");
		String userId = (String)session.get(WebConstants.CONSTANT_USER_NAME);	
		String[] extraInformation = {userId," Maintain Branch "};		
		eventAudit.setExtraInformation(extraInformation);
		String eventDescription = "User {0} has Initiated {1}";
		MessageFormat msgFormat = new MessageFormat(eventDescription);		
		eventAudit.setAuditEventDesc(msgFormat.format(extraInformation));
		
		return eventAudit;		
	}
	private EventAudit getEventAuditDtoForApprove(){
		EventAudit eventAudit = getEventAuditDto();
		eventAudit.setAuditEventId("NGPHGUIBRN0002");
		String userId = (String)session.get(WebConstants.CONSTANT_USER_NAME);	
		String[] extraInformation = {userId," Maintain Branch "};		
		eventAudit.setExtraInformation(extraInformation);
		String eventDescription = "User {0} has Approved {1}";
		MessageFormat msgFormat = new MessageFormat(eventDescription);		
		eventAudit.setAuditEventDesc(msgFormat.format(extraInformation));
		
		return eventAudit;		
	}
	private EventAudit getEventAuditDtoForReject(){
		EventAudit eventAudit = getEventAuditDto();
		eventAudit.setAuditEventId("NGPHGUIBRN0003");
		String userId = (String)session.get(WebConstants.CONSTANT_USER_NAME);	
		String[] extraInformation = {userId," Maintain Branch "};		
		eventAudit.setExtraInformation(extraInformation);
		String eventDescription = "User {0} has Rejected {1}";
		MessageFormat msgFormat = new MessageFormat(eventDescription);		
		eventAudit.setAuditEventDesc(msgFormat.format(extraInformation));
		
		return eventAudit;		
	}
	private EventAudit getEventAuditDto(){
		EventAudit eventAudit = new EventAudit();
		PaymentMessageService paymentMessageService = (PaymentMessageService)ApplicationContextProvider.getBean("paymentMessageService");		
		eventAudit.setAuditSource("0");
		String UserID = (String)session.get(WebConstants.CONSTANT_USER_NAME);
		SecUsers userDetails= paymentMessageService.getUserDetails(UserID);
			String msgBranch = userDetails.getUserBranch();
			String msgDept = userDetails.getUserDept();
		eventAudit.setAuditBranch(msgBranch);
		
		eventAudit.setAuditDept(msgDept);
		eventAudit.setAuditSource(getClass().getName());
		
		
		return eventAudit;
		
	}
	
	
	
	public List<String> getAddressTypeList() {
		return addressTypeList;
	}
	public void setAddressTypeList(List<String> addressTypeList) {
		this.addressTypeList = addressTypeList;
		this.session.put("addressTypeList", addressTypeList);
	}
	public MaintainBranchesDTO getModel() {
		
		return maintainBranchesDTO;
	}
	/**
	 * This method compares the data befor and after marker checker
	 * if status of old_NewScreen flag is "old" it will display old data which
	 * i.e currently present in data base
	 * old_NewScreen flag is "new" it will display new data
	 * i.e same data as we send for marker checker(pending Authorization) 
	 */
	//Screen comparison
	public String callSeeOldData()
	{
		try{
			MaintainBranchesService maintainBranchesService = (MaintainBranchesService) ApplicationContextProvider.getBean("maintainBranchesService");
			MaintainBranchesDTO branchesDTO = new MaintainBranchesDTO();
			if(getOld_NewScreen().equals("OLD")){
			branchesDTO = maintainBranchesService.getALLBranch(maintainBranchesDTO.getBranchCode()).get(0);
			
			setFlagForNewData("flagForNewData");
			}else if(getOld_NewScreen().equals("NEW"))
			{
				branchesDTO = (MaintainBranchesDTO) session.get("branchObject");
				setFlagForNewData(null);
				
				
			}
			maintainBranchesDTO.setActionPerform("MODIFY");
			maintainBranchesDTO.setAddressLine(branchesDTO.getAddressLine());
			maintainBranchesDTO.setAddressType(branchesDTO.getAddressType());
			maintainBranchesDTO.setBranchBIC(branchesDTO.getBranchBIC());
			maintainBranchesDTO.setBranchCode(branchesDTO.getBranchCode());
			maintainBranchesDTO.setBranchIFSC(branchesDTO.getBranchIFSC());
			maintainBranchesDTO.setBranchName(branchesDTO.getBranchName());
			maintainBranchesDTO.setBuildingNumber(branchesDTO.getBuildingNumber());
			maintainBranchesDTO.setCountryCode(branchesDTO.getCountryCode());
			maintainBranchesDTO.setCountrySubDivision(branchesDTO.getCountrySubDivision());
			maintainBranchesDTO.setDepartment(branchesDTO.getDepartment());
			maintainBranchesDTO.setPostCode(branchesDTO.getPostCode());
			maintainBranchesDTO.setStreetName(branchesDTO.getStreetName());
			maintainBranchesDTO.setSubDepartment(branchesDTO.getSubDepartment());
			maintainBranchesDTO.setTownName(branchesDTO.getTownName());
			maintainBranchesDTO.setAddressRef(branchesDTO.getAddressRef());
			maintainBranchesDTO.setSubDepartment(branchesDTO.getSubDepartment());
			setCheckForSubmit("Display_Approve_Reject");
			
			
				setValidUserToApprove(true);
			
			return SUCCESS;
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
	public String getFlagForNewData() {
		return flagForNewData;
	}
	public void setFlagForNewData(String flagForNewData) {
		this.flagForNewData = flagForNewData;
	}

}
 