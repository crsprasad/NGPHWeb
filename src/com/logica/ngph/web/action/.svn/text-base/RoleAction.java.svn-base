package com.logica.ngph.web.action;

import java.sql.Clob;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.springframework.context.ApplicationContextException;

import com.logica.ngph.Entity.Functions;
import com.logica.ngph.Entity.Roles;
import com.logica.ngph.Entity.SecUsers;
import com.logica.ngph.common.ConstantUtil;
import com.logica.ngph.common.NGPHException;
import com.logica.ngph.common.dtos.EventAudit;
import com.logica.ngph.common.dtos.EventMaster;
import com.logica.ngph.common.utils.NGPHUtil;
import com.logica.ngph.service.AuditService;
import com.logica.ngph.service.PaymentMessageService;
import com.logica.ngph.service.PendingAuthorizationService;
import com.logica.ngph.service.RoleMaintenanceService;
import com.logica.ngph.web.utils.ApplicationContextProvider;
import com.logica.ngph.web.utils.WebConstants;
import com.opensymphony.xwork2.ActionSupport;

public class RoleAction extends ActionSupport implements SessionAware{
	
	static Logger logger = Logger.getLogger(RoleAction.class);
	private static final long serialVersionUID = 1L;
	private String roleId;
	private String roleName;
	private String roleDescription;
	private List<Functions> functionsList;
	private List<Functions> assignedFunctions = new ArrayList<Functions>();
	private List<String> assignedFunctionsList = new ArrayList<String>();
	private List<String> approvedFunctions = new ArrayList<String>();
	private Map<String, Object> session;
	private String roleAction;
	private List<String> searchList;
	private String saveAction;
	private String hiddenTxnRef;
	private String checkForSubmit;
	private String txnRef;
	private String old_NewScreen;
	private String flagForNewData;
	public String getFlagForNewData() {
		return flagForNewData;
	}
	public void setFlagForNewData(String flagForNewData) {
		this.flagForNewData = flagForNewData;
	}
	public String getOld_NewScreen() {
		return old_NewScreen;
	}
	public void setOld_NewScreen(String old_NewScreen) {
		this.old_NewScreen = old_NewScreen;
	}
	private List <String> mulRecord=new ArrayList<String>();
	private boolean validUserToApprove; 
	public List<String> getMulRecord() {
		return mulRecord;
	}
	public void setMulRecord(List<String> mulRecord) {
		this.mulRecord = mulRecord;
		this.session.put("mulRecord", mulRecord);
	}

	public String getSaveAction() {
		return saveAction;
	}
	public void setSaveAction(String saveAction) {
		this.saveAction = saveAction;
	}
	public String getHiddenTxnRef() {
		return hiddenTxnRef;
	}
	public void setHiddenTxnRef(String hiddenTxnRef) {
		this.hiddenTxnRef = hiddenTxnRef;
	}
	public String getCheckForSubmit() {
		return checkForSubmit;
	}
	public void setCheckForSubmit(String checkForSubmit) {
		this.checkForSubmit = checkForSubmit;
	}
	public String getTxnRef() {
		return txnRef;
	}
	public void setTxnRef(String txnRef) {
		this.txnRef = txnRef;
	}
	/**
	 * @return the searchList
	 */
	public List<String> getSearchList() {
		return searchList;
	}

	/**
	 * @param searchList the searchList to set
	 */
	public void setSearchList(List<String> searchList) {
		this.searchList = searchList;
	}


	/**
	 * @return the roleAction
	 */
	public String getRoleAction() {
		return roleAction;
	}

	/**
	 * @param roleAction the roleAction to set
	 */
	public void setRoleAction(String roleAction) {
		this.roleAction = roleAction;
	}

	/**
	 * @return the assignedFunctions
	 */
	public List<Functions> getAssignedFunctions() {
		return assignedFunctions;
	}


	/**
	 * @param assignedFunctions the assignedFunctions to set
	 */
	public void setAssignedFunctions(List<Functions> assignedFunctions) {
		this.assignedFunctions = assignedFunctions;
	}

	/**
	 * @return the functionsList
	 */
	public List<Functions> getFunctionsList() {
		return functionsList;
	}

	/**
	 * @param functionsList the functionsList to set
	 */
	public void setFunctionsList(List<Functions> functionsList) {
		this.functionsList = functionsList;
	}

	/**
	 * @return the roleId
	 */
	public String getRoleId() {
		return roleId;
	}

	/**
	 * @param roleId the roleId to set
	 */
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	/**
	 * @return the roleName
	 */
	public String getRoleName() {
		return roleName;
	}

	/**
	 * @param roleName the roleName to set
	 */
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
    
	/**
	 * @return the roleDescription
	 */
	public String getRoleDescription() {
		return roleDescription;
	}


	/**
	 * @param roleDescription the roleDescription to set
	 */
	public void setRoleDescription(String roleDescription) {
		this.roleDescription = roleDescription;
	}

    /**
	 * @return the assignedFunctionsList
	 */
	public List<String> getAssignedFunctionsList() {
		return assignedFunctionsList;
	}

	/**
	 * @param assignedFunctionsList the assignedFunctionsList to set
	 */
	public void setAssignedFunctionsList(List<String> assignedFunctionsList) {
		this.assignedFunctionsList = assignedFunctionsList;
	}
	
	@SkipValidation
	public String displayRole()
	{
		reset();
		if(session.get("AvailableFunctions") == null){
			session.put("AvailableFunctions", getRoleMaintenanceService().getFunctions());
		}
		session.put("RemainFunctionsList", session.get("AvailableFunctions"));
		session.put("AssignedFunctionsList", null);
		return "success";
	}
	
	public String performRoleAction() throws NGPHException{
		try{
		System.out.println("in RoleAction<performRoleAction> ");
		String result= "success";
		PendingAuthorizationService pendingAuthorizationService = (PendingAuthorizationService)ApplicationContextProvider.getBean("pendingAuthorizationService");
		AuditService	auditService = (AuditService)ApplicationContextProvider.getBean("auditService");
		String getTransRef="";
		System.out.println(getSaveAction());
		if(getSaveAction().equals("Save")){			
			if(getRoleAction().equalsIgnoreCase("M")){
				if(getAssignedFunctionsList()!=null && !getAssignedFunctionsList().isEmpty()){
					String getdelimtedString =getRoleAction()+ConstantUtil.delimiter+ getRoleId()+ConstantUtil.delimiter+getRoleName()+ConstantUtil.delimiter+getRoleDescription();
					String userId = (String)session.get(WebConstants.CONSTANT_USER_NAME);
					getTransRef= pendingAuthorizationService.getTranRef(getdelimtedString,"Role Maintenance",userId);
						for(int i = 0;i<getAssignedFunctionsList().size();i++){
							pendingAuthorizationService.delimitedStringValue(getTransRef, i+"",getAssignedFunctionsList().get(i).toString() );
							}
						auditService.saveEventAuditData(getEventAuditDtoForSubmit());			
						return "sendForApproval";
					}else{
						addFieldError("assignedFunctionsList","Please Assign Atleast One function For The RoleID");
						return "input";
					}				
			}else if(getRoleAction().equalsIgnoreCase("D")){
				String getdelimtedString =getRoleAction()+ConstantUtil.delimiter+ getRoleId()+ConstantUtil.delimiter+getRoleName()+ConstantUtil.delimiter+getRoleDescription();
				String userId = (String)session.get(WebConstants.CONSTANT_USER_NAME);
				getTransRef= pendingAuthorizationService.getTranRef(getdelimtedString,"Role Maintenance",userId);
					for(int i = 0;i<getAssignedFunctionsList().size();i++){
						pendingAuthorizationService.delimitedStringValue(getTransRef, i+"",getAssignedFunctionsList().get(i).toString() );
						}
					auditService.saveEventAuditData(getEventAuditDtoForSubmit());			
					return "sendForApproval";
			}else if(getRoleAction().equalsIgnoreCase("A")){
				if(getAssignedFunctionsList()!=null && !getAssignedFunctionsList().isEmpty()){
				
			Boolean isRolePresent= getRoleMaintenanceService().isRolePresent(getRoleId());	
			if(isRolePresent==false){
			String getdelimtedString =getRoleAction()+ConstantUtil.delimiter+ getRoleId()+ConstantUtil.delimiter+getRoleName()+ConstantUtil.delimiter+getRoleDescription();
			String userId = (String)session.get(WebConstants.CONSTANT_USER_NAME);
			getTransRef= pendingAuthorizationService.getTranRef(getdelimtedString,"Role Maintenance",userId);
				for(int i = 0;i<getAssignedFunctionsList().size();i++){
					pendingAuthorizationService.delimitedStringValue(getTransRef, i+"",getAssignedFunctionsList().get(i).toString() );
					}
				auditService.saveEventAuditData(getEventAuditDtoForSubmit());			
			return "sendForApproval";
			}else{
				addFieldError("roleID", "Role Id Already Exist");
				return "input";
			}
				}else{
					addFieldError("assignedFunctionsList","Please Assign Atleast One function For The RoleID");
					return "input";
				}
			}
		}else if(getSaveAction().equals("Approve")){
			if(pendingAuthorizationService.changeStatus(getSaveAction(), getHiddenTxnRef()).equals("success"))
				result = getRoleMaintenanceService().performRoleAction(getRoleAction(), getRoleId(), getRoleName(), getRoleDescription(), this.getApprovedFunctions());
			auditService.saveEventAuditData(getEventAuditDtoForApprove());
			if(getRoleAction().equalsIgnoreCase("A"))
			return "roleCreated";
			else if(getRoleAction().equalsIgnoreCase("M"))
				return "roleModified";
			else if(getRoleAction().equalsIgnoreCase("D"))
				return "roleDeleted";
			
		}else if(getSaveAction().equals("Reject")){
			pendingAuthorizationService.changeStatus(getSaveAction(), getHiddenTxnRef());
			auditService.saveEventAuditData(getEventAuditDtoForReject());
			return "rejected";
		}
		
	} catch (NGPHException ngphException) {
		AuditServiceUtil.logNgphException(ngphException,logger);
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
	addActionError("Unable To Process.Please Try again");
		return "input";
	}
		
	private EventAudit getEventAuditDtoForSubmit(){
		EventAudit eventAudit = getEventAuditDto();
		eventAudit.setAuditEventId("NGPHGUIROL0001");
		String userId = (String)session.get(WebConstants.CONSTANT_USER_NAME);	
		String[] extraInformation = {userId," User Role "};		
		eventAudit.setExtraInformation(extraInformation);
		String eventDescription = "User {0} has Initiated {1}";
		MessageFormat msgFormat = new MessageFormat(eventDescription);		
		eventAudit.setAuditEventDesc(msgFormat.format(extraInformation));
		
		return eventAudit;		
	}
	private EventAudit getEventAuditDtoForApprove(){
		EventAudit eventAudit = getEventAuditDto();
		eventAudit.setAuditEventId("NGPHGUIROL0002");
		String userId = (String)session.get(WebConstants.CONSTANT_USER_NAME);	
		String[] extraInformation = {userId," User Role "};		
		eventAudit.setExtraInformation(extraInformation);
		String eventDescription = "User {0} has Approved {1}";
		MessageFormat msgFormat = new MessageFormat(eventDescription);		
		eventAudit.setAuditEventDesc(msgFormat.format(extraInformation));
		
		return eventAudit;		
	}
	private EventAudit getEventAuditDtoForReject(){
		EventAudit eventAudit = getEventAuditDto();
		eventAudit.setAuditEventId("NGPHGUIROL0003");
		String userId = (String)session.get(WebConstants.CONSTANT_USER_NAME);	
		String[] extraInformation = {userId," User Role "};		
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
	

	private RoleMaintenanceService getRoleMaintenanceService(){
    	RoleMaintenanceService roleMaintenanceService = (RoleMaintenanceService)ApplicationContextProvider.getBean("roleMaintenanceService");
    	return roleMaintenanceService;
    }
    
    public void validate(){
    	System.out.println("in RoleAction<validate>");
    	if(getAssignedFunctions() == null ){
    		addFieldError("assignedFunctions", "Functions(s) should be assigned.");
    	}
    }

    @SkipValidation
	public String getSearchData(){
    	System.out.println("in RoleAction<getSearchData>");
		try {
	        setSearchList(getRoleMaintenanceService().roleIDSearch(getRoleId()));
		} catch (NGPHException ngphException) {
			AuditServiceUtil.logNgphException(ngphException,logger);
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
		return "populateData";
	}

	public Map<String, Object> getSession() {
		return session;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
	
	@SkipValidation
	public String getRoleData(){
		System.out.println("in RoleAction<getRoleData>");
		try {
			Roles roles = getRoleMaintenanceService().getRoleData(getRoleId());
			setRoleName(roles.getRoleName());
			setRoleDescription(roles.getRoleDescription());
			
			List<String> assignedFunctionsID = getRoleMaintenanceService().getAssignedFunctions(getRoleId());
			List<Functions> AvailableFunctionsList = (List<Functions>)session.get("AvailableFunctions");
			List<Functions> remainFunctionsList =  new ArrayList<Functions>();
			List<Functions> assignedFunctionsList =  new ArrayList<Functions>();
			
			for(Functions functions : AvailableFunctionsList){
				if(NGPHUtil.isElementInList(assignedFunctionsID, functions.getFunctionId())){
					assignedFunctionsList.add(functions);
				} else {
					remainFunctionsList.add(functions);
				}
			}
			session.put("RemainFunctionsList", remainFunctionsList);
			session.put("AssignedFunctionsList", assignedFunctionsList);
		} catch (NGPHException ngphException) {
			AuditServiceUtil.logNgphException(ngphException,logger);
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
		return "success";
	}
	@SkipValidation
	 public String getAuthorization() throws NGPHException, SQLException
		{
		try{
	    	displayRole();
	    	
			PendingAuthorizationService pendingAuthorizationService = (PendingAuthorizationService)ApplicationContextProvider.getBean("pendingAuthorizationService");
			
			setHiddenTxnRef(getTxnRef());
			setCheckForSubmit("Display_Approve_Reject");
			String userId = pendingAuthorizationService.getCreatedUser(getTxnRef());
			String userRole = pendingAuthorizationService.getUserType((String)session.get(WebConstants.CONSTANT_USER_NAME));
			if(((String)session.get(WebConstants.CONSTANT_USER_NAME)).equals(userId) || !userRole.equals("A")){
				setValidUserToApprove(false);
			} else {
				setValidUserToApprove(true);
			}
			String requiredString =pendingAuthorizationService.getScreenReturn(getTxnRef());
			String[] stringBreaker = requiredString.split("~");
			setRoleAction(stringBreaker[0]);
			setRoleId(stringBreaker[1]);
			setRoleName(stringBreaker[2]);
			setRoleDescription(stringBreaker[3]);
			List mulDataList = pendingAuthorizationService.getMulScreenData(getTxnRef());
			List<String> tempDataList = new ArrayList<String>();
			for(int i=0;i<mulDataList.size();i++)
			{
				Clob temp = (Clob)mulDataList.get(i);
				String data= temp.getSubString(1, (int) temp.length()).toString();
				tempDataList.add(data);
			}
			setMulRecord(tempDataList);
			setOld_NewScreen(null);
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
		addActionError("Unable To Process.Please Try again");
		return "input";
		}
	private void reset(){
		setRoleId("");
		setRoleName("");
		setRoleDescription("");
	}
	
	@SkipValidation
	public String callSeeOldData()
	{
		try{
			if(getOld_NewScreen().equals("OLD")){
				getRoleData();
				setFlagForNewData("flagForNewData");
				List<Functions> temp = (List<Functions>) session.get("AssignedFunctionsList");
				List newData = new ArrayList();
				for(int i=0;i<temp.size();i++)
				{
					newData.add(((Functions)temp.get(i)).getFunctionName());
				}
				
				session.put("mulRecord", newData );
				}else if(getOld_NewScreen().equals("NEW"))
				{
					getAuthorization();
					setFlagForNewData(null);
				}
			setCheckForSubmit("Display_Approve_Reject");
			setValidUserToApprove(true);
		return SUCCESS;	
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
	
	public void setApprovedFunctions(List<String> approvedFunctions) {
		this.approvedFunctions = approvedFunctions;
	}
	public List<String> getApprovedFunctions() {
		return approvedFunctions;
	}
	public boolean getValidUserToApprove() {
		return validUserToApprove;
	}
	public void setValidUserToApprove(boolean validUserToApprove) {
		this.validUserToApprove = validUserToApprove;
	}
}
