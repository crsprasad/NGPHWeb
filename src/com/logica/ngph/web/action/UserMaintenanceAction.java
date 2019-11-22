package com.logica.ngph.web.action;

import java.sql.Clob;
import java.text.DateFormat;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.springframework.context.ApplicationContextException;

import com.logica.ngph.Entity.Department;
import com.logica.ngph.Entity.Roles;
import com.logica.ngph.Entity.SecUsers;
import com.logica.ngph.Entity.SecurityQuesions;
import com.logica.ngph.common.ConstantUtil;
import com.logica.ngph.common.NGPHException;
import com.logica.ngph.common.dtos.EventAudit;
import com.logica.ngph.common.dtos.UserMaintenanceDTO;
import com.logica.ngph.common.utils.NGPHUtil;
import com.logica.ngph.dtos.PasswordSecurityPolicyDto;
import com.logica.ngph.service.AuditService;
import com.logica.ngph.service.AuthorizationSchemaMaitenanceService;
import com.logica.ngph.service.ChangePwdService;
import com.logica.ngph.service.PasswordSecurityPolicyService;
import com.logica.ngph.service.PaymentMessageService;
import com.logica.ngph.service.PendingAuthorizationService;
import com.logica.ngph.service.UserMaintenanceService;
import com.logica.ngph.utils.StringEncrypter;
import com.logica.ngph.web.utils.ApplicationContextProvider;
import com.logica.ngph.web.utils.WebConstants;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 
 * @author SATHISH
 *
 */
public class UserMaintenanceAction extends ActionSupport implements ModelDriven<UserMaintenanceDTO>, SessionAware{

	/**
	 * Serial Version ID
	 */
	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(UserMaintenanceAction.class);
	private UserMaintenanceDTO userObject = new UserMaintenanceDTO();
	private Map<String, Object> session;
	private String UserId;
	private List<UserMaintenanceDTO> searchList;
	private String saveAction;
	private String hiddenTxnRef;
	private String txnRef;
	private String checkForSubmit;
	
	private List<String> deptList;
	private List<String> roleList;
	// Screen comparison 
	private String flagForNewData;
	private String old_NewScreen;
	
	private List<String> securityQuestionDropDown = new ArrayList<String>();
	

	
	public List<String> getSecurityQuestionDropDown() {
		return securityQuestionDropDown;
	}
	public void setSecurityQuestionDropDown(List<String> securityQuestionDropDown) {
		this.securityQuestionDropDown = securityQuestionDropDown;
		this.session.put("sequrityQuestionDropDownList", securityQuestionDropDown);
	}
	//Screen comparison End
	public String getOld_NewScreen() {
		return old_NewScreen;
	}
	public void setOld_NewScreen(String old_NewScreen) {
		this.old_NewScreen = old_NewScreen;
	}
	
 	public String getCheckForSubmit() {
		return checkForSubmit;
	}


	public List<String> getDeptList() {
		return deptList;
	}


	public void setDeptList(List<String> deptList) {
		this.deptList = deptList;
		this.session.put("deptList", deptList);
	}


	public List<String> getRoleList() {
		return roleList;
	}


	public void setRoleList(List<String> roleList) {
		this.roleList = roleList;
		this.session.put("roleList", roleList);
	}


	public void setCheckForSubmit(String checkForSubmit) {
		this.checkForSubmit = checkForSubmit;
	}


	private List <String> mulRecord=new ArrayList<String>();
	public UserMaintenanceDTO getUserObject() {
		return userObject;
	}


	public void setUserObject(UserMaintenanceDTO userObject) {
		this.userObject = userObject;
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


	public String getTxnRef() {
		return txnRef;
	}


	public void setTxnRef(String txnRef) {
		this.txnRef = txnRef;
	}


	public List<String> getMulRecord() {
		return mulRecord;
	}


	public void setMulRecord(List<String> mulRecord) {
		this.mulRecord = mulRecord;
	}


	public boolean isValidUserToApprove() {
		return validUserToApprove;
	}


	public void setValidUserToApprove(boolean validUserToApprove) {
		this.validUserToApprove = validUserToApprove;
	}


	private boolean validUserToApprove;
	
	private List<String> branchCode = new ArrayList<String>();
	public List<String> getBranchCode() {
		return branchCode;
	}
	


	public void setBranchCode(List<String> branchCode) {
		this.branchCode = branchCode;
		if(branchCode!=null)
		{
			while(branchCode.contains(null))
			{
				branchCode.remove(null);
			}
			this.session.put("branchCode",branchCode);
		}
		
	}
	/*
	 * To display  User Maintenance initial page
	 */
	@SkipValidation
	public String displayUserRegistraionPage() throws Exception
	{
		
		PasswordSecurityPolicyService passwordSecurityPolicyService = (PasswordSecurityPolicyService)ApplicationContextProvider.getBean("passwordSecurityPolicyService");
		ChangePwdService changePwdService  = (ChangePwdService) ApplicationContextProvider.getBean("changePwdService");
		PasswordSecurityPolicyDto passwordSecurityPolicyDto = passwordSecurityPolicyService.getSecurityPolicy();
			
		AuthorizationSchemaMaitenanceService authorizationSchemaMaitenanceService = (AuthorizationSchemaMaitenanceService)ApplicationContextProvider.getBean("authorizationSchemaMaitenanceService");
		List<String> branches = authorizationSchemaMaitenanceService.getAuthorizationSchemaMaitenanceService(ConstantUtil.BRANCHCODE);
		branches.add(ConstantUtil.ALL);
		setBranchCode(branches);
		
		System.out.println("In UserMaintenanceAction312 <displayUserRegistraionPage>");
		UserMaintenanceService service = getApplicationContext();
		if(session.get("AvailableRolesList")==null){
			List<Object> rolesList = (new ArrayList<Object>(service.getAvailableRoles()));
			session.put("AvailableRolesList", rolesList);
		}
		if(session.get("AvailableDeptsList")==null){
			List<Object> deptsList = (new ArrayList<Object>(service.getAvailableDepts()));
			session.put("AvailableDeptsList", deptsList);
		}
		/*if(session.get("securityQuestionDropDown")==null){
			List<SecurityQuesions> securityQuestionDropDown = (new ArrayList<SecurityQuesions>(service.getSecurityQuestions()));
			session.put("securityQuestionDropDown", securityQuestionDropDown);
		}*/
		
	
	
		
		setSecurityQuestionDropDown(service.getSecurityQuestions());
		
		int minChars;
		int maxChars;
		int noofDigits;
		int noofSpecialChars;
		int noofUpperChars;
		int noofLowerChars;
		
		minChars = passwordSecurityPolicyDto.getMinChars();
		maxChars = passwordSecurityPolicyDto.getMaxChars();
		noofDigits = passwordSecurityPolicyDto.getNoofDigits();
		noofSpecialChars = passwordSecurityPolicyDto.getNoofSpecialChars();
		noofUpperChars = passwordSecurityPolicyDto.getNoofUpperChars();
		noofLowerChars = passwordSecurityPolicyDto.getNoofLowerChars();
		
		String isPassPolicyEnable = changePwdService.getPassPolicyIsReq("ISPASSPOLICYENABLE");
		if(isPassPolicyEnable.equalsIgnoreCase("Y"))
		{
			addActionMessage("Please Enter Password according to Policy!");
			addActionMessage("As per the policy, the maximum length is "+maxChars +" minimum length is "+minChars+" no of uppercase characters is "+noofUpperChars+
				 " lowercase characters is "+ noofLowerChars+ " special characters is "+noofSpecialChars + " digits is "+noofDigits);
		}
		session.put("RemainRolesList", session.get("AvailableRolesList"));
		session.put("RemainDeptsList", session.get("AvailableDeptsList"));
		session.put("AssignedDeptsList", null);
		session.put("AssignedRolesList", null);
		reset(userObject);
		return "success";
	}
	
	
	
	@SkipValidation
	public String getAuthorization()
	{
		
		try{
			
			PendingAuthorizationService pendingAuthorizationService = (PendingAuthorizationService)ApplicationContextProvider.getBean("pendingAuthorizationService");
			UserMaintenanceService service = getApplicationContext();
			displayUserRegistraionPage();
			setHiddenTxnRef(getTxnRef());
			setCheckForSubmit("Display_Approve_Reject");
			String userId = pendingAuthorizationService.getCreatedUser(getTxnRef());
			String userRole = pendingAuthorizationService.getUserType((String)session.get(WebConstants.CONSTANT_USER_NAME));
			if((((String)session.get(WebConstants.CONSTANT_USER_NAME)).equalsIgnoreCase(userId) || userRole.equals("T"))){
				setValidUserToApprove(false);
			} else {
				setValidUserToApprove(true);
			}
			String requiredString =pendingAuthorizationService.getScreenReturn(getTxnRef());
			String[] stringBreaker = requiredString.split("~");
			System.out.println("stringBreaker length "+stringBreaker.length);
			userObject.setAppl(stringBreaker[0]);
			userObject.setEmpNo(stringBreaker[1]);
			if(stringBreaker[2]!=null && !stringBreaker[2].equals(null) && !stringBreaker[2].equals(""))
			userObject.setFirstlogin(Integer.parseInt(stringBreaker[2]));
			userObject.setMobileNumber(stringBreaker[3]);
			userObject.setUser(stringBreaker[4]);
			userObject.setUserAction(stringBreaker[5]);
			userObject.setUserActive(Integer.parseInt(stringBreaker[6]));
			userObject.setUserBranch(stringBreaker[7]);
			if(stringBreaker[8]!=null && !stringBreaker[8].equals(null) && !stringBreaker[8].equals(""))
			userObject.setUserDailyLimit(Double.parseDouble(stringBreaker[8]));
			userObject.setUserDept(stringBreaker[9]);
			userObject.setUserEmailId(stringBreaker[10]);
			userObject.setUserFirstName(stringBreaker[11]);
			userObject.setUserLastName(stringBreaker[12]);
			if(stringBreaker[13]!=null && !stringBreaker[13].equals(null) && !stringBreaker[13].equals(""))
			userObject.setUserLocked(Integer.parseInt(stringBreaker[13]));
			userObject.setUserPassword(stringBreaker[14]);
			userObject.setUserType(stringBreaker[15]);
			userObject.setEffectiveDateForRole(getConvertedDate(stringBreaker[16]));
			userObject.setSecurityQuestion(stringBreaker[17]);
			userObject.setSecurityAnswer(stringBreaker[18]);
			if(!stringBreaker[19].trim().equals("Not")){
				 userObject.setUserExpiryDate(getConvertedDate(stringBreaker[19]));
			}
			
			
			List mulDataList = pendingAuthorizationService.getMulScreenData(getTxnRef());
			List<String> tempRole = new ArrayList<String>();
			List<String> tempDept = new ArrayList<String>();
			for(int i=0;i<mulDataList.size();i++)
			{
				Clob temp = (Clob)mulDataList.get(i);
				String data[]= temp.getSubString(1, (int) temp.length()).toString().split("~");
				if(data[1].toString().trim().equals("Role")  && !data[0].toString().trim().equals("")){
				tempRole.add(service.getRoleName(data[0]));
				}else if((data[1].toString().trim().equals("Dept"))){
				tempDept.add(service.getDeptName(data[0]));
				}
				
			}
			setDeptList(tempDept);
			setRoleList(tempRole);
			
			userObject.setAssignedDepts(tempDept);
			userObject.setAssignedRoles(tempRole);
			tempDept = null;
			tempRole = null;
			mulDataList = null;
			pendingAuthorizationService = null;
			//Screen comparison
			setOld_NewScreen(null);
			session.put("userMaintenanceTranRef", getTxnRef());
			return "success";
		} catch (NullPointerException  nullPointerException) {
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
		
	
	
	/*
	 * Action to create/modify an user
	 */
	public String performUserAction() throws NGPHException
	{
		System.out.println("In UserMaintenanceAction <createUserAction>");
		try {
			String txnKey="";
			UserMaintenanceService service = getApplicationContext();
			PendingAuthorizationService pendingAuthorizationService = (PendingAuthorizationService)ApplicationContextProvider.getBean("pendingAuthorizationService");
			AuditService auditService = (AuditService)ApplicationContextProvider.getBean("auditService");
			System.out.println("getSaveAction(): - "+getSaveAction());
			List<String> role = (List)userObject.getAssignedRoles();
			List<String> dept = (List)userObject.getAssignedDepts();
			Boolean userPresent = service.isUserPresent(userObject.getUser());
			if(getSaveAction().equals("Save"))
			{		
				if(userObject.getUserAction().equals("M")){
					if(role!=null && !role.isEmpty() && dept!=null && !dept.isEmpty()){
						String delimitedString="";
						
						
							delimitedString=userObject.getAppl()+"~"+userObject.getEmpNo()+"~"+userObject.getFirstlogin()+"~"+userObject.getMobileNumber()+"~"+userObject.getUser()
							+"~"+userObject.getUserAction()+"~"+userObject.getUserActive()+"~"+userObject.getUserBranch()+"~"+userObject.getUserDailyLimit()+"~"+userObject.getUserDept()
							+"~"+userObject.getUserEmailId()+"~"+userObject.getUserFirstName()+"~"+userObject.getUserLastName()+"~"+userObject.getUserLocked()+"~"+userObject.getUserPassword()
							+"~"+userObject.getUserType()+"~"+userObject.getEffectiveDateForRole()+"~"+userObject.getSecurityQuestion()+"~"+userObject.getSecurityAnswer()+"~";
							
							
							if(userObject.getUserExpiryDate()!=null)
								delimitedString = delimitedString+userObject.getUserExpiryDate();
							
							else
								delimitedString = delimitedString+"Not";
							String userId = (String)session.get(WebConstants.CONSTANT_USER_NAME);
							txnKey = pendingAuthorizationService.getTranRef(delimitedString,"User Maintenance",userId);
							
							
							for(int i = 0;i<role.size();i++)
							{
								pendingAuthorizationService.delimitedStringValue(txnKey, i+"", role.get(i).toString()+"~Role");
							}
							for(int i = 0;i<dept.size();i++)
							{
								pendingAuthorizationService.delimitedStringValue(txnKey, i+"", dept.get(i).toString()+"~Dept");
							}
							
							 auditService.saveEventAuditData(getEventAuditDtoForSubmit());				
							
							}else
							{								
								addActionError("Please Assign One Role And Department For The User");
								
								return "input";
							}
				}else if(userObject.getUserAction().equals("A")){
					if(role!=null && !role.isEmpty() && dept!=null && !dept.isEmpty() && userPresent==false){
				String delimitedString="";
					delimitedString=userObject.getAppl()+"~"+userObject.getEmpNo()+"~"+userObject.getFirstlogin()+"~"+userObject.getMobileNumber()+"~"+userObject.getUser()
					+"~"+userObject.getUserAction()+"~"+userObject.getUserActive()+"~"+userObject.getUserBranch()+"~"+userObject.getUserDailyLimit()+"~"+userObject.getUserDept()
					+"~"+userObject.getUserEmailId()+"~"+userObject.getUserFirstName()+"~"+userObject.getUserLastName()+"~"+userObject.getUserLocked()+"~"+userObject.getUserPassword()
					+"~"+userObject.getUserType()+"~"+userObject.getEffectiveDateForRole()+"~"+userObject.getSecurityQuestion()+"~"+userObject.getSecurityAnswer()+"~";
					
					if(userObject.getUserExpiryDate()!=null)
						delimitedString = delimitedString+userObject.getUserExpiryDate();
					
					else
						delimitedString = delimitedString+"Not";
					String userId = (String)session.get(WebConstants.CONSTANT_USER_NAME);
					txnKey = pendingAuthorizationService.getTranRef(delimitedString,"User Maintenance",userId);
					
					
					for(int i = 0;i<role.size();i++)
					{
						pendingAuthorizationService.delimitedStringValue(txnKey, i+"", role.get(i).toString()+"~Role");
					}
					for(int i = 0;i<dept.size();i++)
					{
						pendingAuthorizationService.delimitedStringValue(txnKey, i+"", dept.get(i).toString()+"~Dept");
					}
					
					 auditService.saveEventAuditData(getEventAuditDtoForSubmit());				
					
					}
					else
					{
						if(userPresent==true)
						{
							addFieldError("user", "User Already Exist");
						}else
						addActionError("Please Assign One Role And Department For The User");
						
						return "input";
					}
				}
				session.put("screenRef", txnKey);
			return "sendForApproval";
			}
			else if(getSaveAction().equals("Approve")){
			
			service.performUserAction(userObject);			
			pendingAuthorizationService.changeStatus(getSaveAction(), getHiddenTxnRef());
			auditService.saveEventAuditData(getEventAuditDtoForApprove());
			}
			else if(getSaveAction().equals("Reject")){
				pendingAuthorizationService.changeStatus(getSaveAction(), getHiddenTxnRef());
				 auditService.saveEventAuditData(getEventAuditDtoForReject());
				 return "rejected";
			}
			System.out.println("In UserMaintenanceAction <createUserAction>:After return form service");
			return "success";
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
		addActionError("Unable to perform the operation. Please try again");
		return "input";
	}
	
	private EventAudit getEventAuditDtoForSubmit(){
		EventAudit eventAudit = getEventAuditDto();
		eventAudit.setAuditEventId("NGPHGUIUSM0001");
		String userId = (String)session.get(WebConstants.CONSTANT_USER_NAME);	
		String[] extraInformation = {userId," User Registration "};		
		eventAudit.setExtraInformation(extraInformation);
		String eventDescription = "User {0} has Initiated {1}";
		MessageFormat msgFormat = new MessageFormat(eventDescription);		
		eventAudit.setAuditEventDesc(msgFormat.format(extraInformation));
		
		return eventAudit;		
	}
	private EventAudit getEventAuditDtoForApprove(){
		EventAudit eventAudit = getEventAuditDto();
		eventAudit.setAuditEventId("NGPHGUIUSM0002");
		String userId = (String)session.get(WebConstants.CONSTANT_USER_NAME);	
		String[] extraInformation = {userId," User Registration "};		
		eventAudit.setExtraInformation(extraInformation);
		String eventDescription = "User {0} has Approved {1}";
		MessageFormat msgFormat = new MessageFormat(eventDescription);		
		eventAudit.setAuditEventDesc(msgFormat.format(extraInformation));
		
		return eventAudit;		
	}
	private EventAudit getEventAuditDtoForReject(){
		EventAudit eventAudit = getEventAuditDto();
		eventAudit.setAuditEventId("NGPHGUIUSM0003");
		String userId = (String)session.get(WebConstants.CONSTANT_USER_NAME);	
		String[] extraInformation = {userId," User Registration "};		
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
	
	
	/*
	 * Action to delete an user
	 */
	@SkipValidation
	public String deleteUserAction() throws NGPHException
	{
		System.out.println("In UserMaintenanceAction <deleteUserAction>");
		try{UserMaintenanceService service = getApplicationContext();
		PendingAuthorizationService pendingAuthorizationService = (PendingAuthorizationService)ApplicationContextProvider.getBean("pendingAuthorizationService");
		AuditService	auditService = (AuditService)ApplicationContextProvider.getBean("auditService");
		System.out.println("getSaveAction(): - "+getSaveAction());
		String userId = (String)session.get(WebConstants.CONSTANT_USER_NAME);
		if(getSaveAction().equals("Save"))
		{
			if(!userObject.getUser().equals(userId)){
			String delimitedString="";
			String txnKey="";
			
				delimitedString=userObject.getAppl()+"~"+userObject.getEmpNo()+"~"+userObject.getFirstlogin()+"~"+userObject.getMobileNumber()+"~"+userObject.getUser()
				+"~"+userObject.getUserAction()+"~"+userObject.getUserActive()+"~"+userObject.getUserBranch()+"~"+userObject.getUserDailyLimit()+"~"+userObject.getUserDept()
				+"~"+userObject.getUserEmailId()+"~"+userObject.getUserFirstName()+"~"+userObject.getUserLastName()+"~"+userObject.getUserLocked()+"~"+userObject.getUserPassword()
				+"~"+userObject.getUserType()+"~"+userObject.getEffectiveDateForRole()+"~";
				
				if(userObject.getUserExpiryDate()!=null)
					delimitedString = delimitedString+userObject.getUserExpiryDate();
				else
					delimitedString = delimitedString+"Not";
				
				txnKey = pendingAuthorizationService.getTranRef(delimitedString,"User Maintenance",userId);
				System.out.println(userObject.getAssignedRoles());
				List<String> role = (List)userObject.getAssignedRoles();
				List<String> dept = (List)userObject.getAssignedDepts();
				for(int i = 0;i<role.size();i++)
				{
					pendingAuthorizationService.delimitedStringValue(txnKey, i+"", role.get(i).toString()+"~Role");
				}
				for(int i = 0;i<dept.size();i++)
				{
					pendingAuthorizationService.delimitedStringValue(txnKey, i+"", dept.get(i).toString()+"~Dept");
				}
				auditService.saveEventAuditData(getEventAuditDtoForSubmit());
			
		return "sendForApproval";
		}else
		{
			addFieldError("user", "Cannot delete the current logged in user ");
			return INPUT;
		}
		}
		else if(getSaveAction().equals("Approve")){
					service.performUserAction(userObject);
					auditService.saveEventAuditData(getEventAuditDtoForApprove());
		}
		else if(getSaveAction().equals("Reject")){
			pendingAuthorizationService.changeStatus(getSaveAction(), getHiddenTxnRef());
			auditService.saveEventAuditData(getEventAuditDtoForReject());
		}
			System.out.println("In UserMaintenanceAction <createUserAction>:After return form service");
			return "success";
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
		addActionError("Unable to perform the operation. Please try again");
		return "input";
	}
	
	
	
	/*
	 * This method fetches all userIds available in DB
	 */
	@SkipValidation
	public String getSearchData(){
		try {
			UserMaintenanceService service = getApplicationContext();
			setSearchList(service.dataUserIDSearch(getUserId()));
			return "populateData";
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
		addActionError("Unable to perform the operation. Please try again");
		return "input";
	}
	
	/*
	 * This method fetches use data for the userId 
	 */
	@SkipValidation
	public String getUserDataAction(){
		try {
			UserMaintenanceService service = getApplicationContext();
			SecUsers entityObject= service.getUserDataAction(userObject.getUser());
			userObject.setUserFirstName(entityObject.getUserFirstName());
			userObject.setUserLastName(entityObject.getUserLastName());
			userObject.setMobileNumber(entityObject.getMobileNumber());
			userObject.setUserBranch(entityObject.getUserBranch());
			userObject.setUserDept(entityObject.getUserDept());
			userObject.setEmpNo(entityObject.getEmpNo());
			userObject.setUserLocked(entityObject.getUserLocked());
			userObject.setUserActive(entityObject.getUserActive());
			userObject.setUserExpiryDate(entityObject.getUserExpiryDate());
			userObject.setUserEmailId(entityObject.getUserEmailId());
			userObject.setUserPassword(StringEncrypter.decryptURL(userObject.getUser(), new String(new sun.misc.BASE64Decoder().decodeBuffer(entityObject.getUserPassword()))));
			System.out.println("User Password is "+userObject.getUserPassword());
			userObject.setEffectiveDateForRole(entityObject.getEffectiveDateForRole());
			userObject.setUserDailyLimit(entityObject.getUserDailyLimit());
			userObject.setFirstlogin(entityObject.getFirstlogin());
			userObject.setUserType(entityObject.getUserType());
			userObject.setSecurityQuestion(entityObject.getSecurityQuesion());
			userObject.setSecurityAnswer(entityObject.getSecurityAnswer());
			List<String> assignedRoleIdList = service.getAssignedRoles(userObject.getUser());
			List<String> assignedDeptIdList = service.getAssignedDepts(userObject.getUser());
			List<Object> availableRolesList = (List)session.get("AvailableRolesList");
			List<Object> availableDeptsList = (List)session.get("AvailableDeptsList");
			
			List<Object> assignedRoleList = new ArrayList<Object>();
			List<Object> assignedDeptList = new ArrayList<Object>();
			List<Object> remainRolesList =  new ArrayList<Object>();
			List<Object> remainDeptsList =  new ArrayList<Object>();
			
			for(Object role : availableRolesList){
				Roles temp = (Roles) role;
				if(NGPHUtil.isElementInList(assignedRoleIdList, temp.getRoleId())){
					assignedRoleList.add(temp);
				} else {
					remainRolesList.add(temp);
				}
			}
			session.put("RemainRolesList", remainRolesList);
			session.put("AssignedRolesList", assignedRoleList);
			for(Object dept : availableDeptsList){
				Department temp = (Department) dept;
				if(NGPHUtil.isElementInList(assignedDeptIdList, temp.getDeptCode())){
					assignedDeptList.add(temp);
				} else {
					remainDeptsList.add(temp);
				}
			}
			setCheckForSubmit("");
			session.put("RemainDeptsList", remainDeptsList);
			session.put("AssignedDeptsList", assignedDeptList);
			return "success";
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
	
		addActionError("Unable to perform the operation. Please try again");
		return "input";
		
	}
	
	
	private void reset(UserMaintenanceDTO userObject){
		userObject.setUser(null);
		userObject.setUserFirstName("");
		userObject.setUserLastName("");
		userObject.setMobileNumber("");
		userObject.setUserBranch("");
		userObject.setUserDept("");
		userObject.setEmpNo(null);
		userObject.setUserLocked(1);
		userObject.setUserActive(1);
		userObject.setUserExpiryDate(null);
		userObject.setUserEmailId("");
		userObject.setUserPassword("");
		userObject.setEffectiveDateForRole(null);
		userObject.setUserDailyLimit(0.0);
		userObject.setFirstlogin(0);
		userObject.setUserType("");
	}
	
	
	/*
	 * Returns  the instance of User Maintenance service
	 */
	public UserMaintenanceService getApplicationContext()
	{
		UserMaintenanceService userMaintenanceService = (UserMaintenanceService)ApplicationContextProvider.getBean("userMaintenanceService");
		return userMaintenanceService;
	}
	
	/*
	 * 
	 */
	public UserMaintenanceDTO getModel() {
		return userObject;
	}

	public Map<String, Object> getSession() {
		return session;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}


	public String getUserId() {
		return UserId;
	}


	public void setUserId(String userId) {
		UserId = userId;
	}


	public List<UserMaintenanceDTO> getSearchList() {
		return searchList;
	}


	public void setSearchList(List<UserMaintenanceDTO> searchList) {
		this.searchList = searchList;
	}
	public Date getConvertedDate(String StringDate)
	{
		try{
			String date =StringDate;
			
			DateFormat formatter=new SimpleDateFormat("EEE MMM d HH:mm:ss zzz yyyy");  
			Date Converteddate = (Date)formatter.parse(date);
			
			return Converteddate;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
	//Screen comparison
	public String callSeeOldData()
	{
		try{			
			if(getOld_NewScreen().equals("OLD")){				
				getUserOldData();			
				setFlagForNewData("flagForNewData");
				setCheckForSubmit("Display_Approve_Reject");			
				setValidUserToApprove(true);
			}else if(getOld_NewScreen().equals("NEW")){
				String tranRef =  (String)session.get("userMaintenanceTranRef");
				setTxnRef(tranRef);
				getAuthorization();
				setFlagForNewData(null);				
			}			
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
	
	@SkipValidation
	public void getUserOldData(){
		try {
			UserMaintenanceService service = getApplicationContext();
			SecUsers entityObject= service.getUserDataAction(userObject.getUser());
			userObject.setUserFirstName(entityObject.getUserFirstName());
			userObject.setUserLastName(entityObject.getUserLastName());
			userObject.setMobileNumber(entityObject.getMobileNumber());
			userObject.setUserBranch(entityObject.getUserBranch());
			userObject.setUserDept(entityObject.getUserDept());
			userObject.setEmpNo(entityObject.getEmpNo());
			userObject.setUserLocked(entityObject.getUserLocked());
			userObject.setUserActive(entityObject.getUserActive());
			userObject.setUserExpiryDate(entityObject.getUserExpiryDate());
			userObject.setUserEmailId(entityObject.getUserEmailId());
			userObject.setUserPassword(StringEncrypter.decryptURL(userObject.getUser(), new String(new sun.misc.BASE64Decoder().decodeBuffer(entityObject.getUserPassword()))));
			System.out.println("User Password is "+userObject.getUserPassword());
			userObject.setEffectiveDateForRole(entityObject.getEffectiveDateForRole());
			userObject.setUserDailyLimit(entityObject.getUserDailyLimit());
			userObject.setFirstlogin(entityObject.getFirstlogin());
			userObject.setUserType(entityObject.getUserType());
			List<String> assignedRoleIdList = service.getAssignedRoles(userObject.getUser());
			List<String> assignedDeptIdList = service.getAssignedDepts(userObject.getUser());
//			userObject.setAssignedRoles(assignedRoleIdList);
//			userObject.setAssignedDepts(assignedDeptIdList);
			List<Object> availableRolesList = (new ArrayList<Object>(service.getAvailableRoles()));
			List<Object> availableDeptsList = (new ArrayList<Object>(service.getAvailableDepts()));
//			List<String> assignedRoleIdList = service.getAssignedRoles(userObject.getUser());
//			List<String> assignedDeptIdList = service.getAssignedDepts(userObject.getUser());
			
			List<Object> assignedRoleList = new ArrayList<Object>();
			List<Object> assignedDeptList = new ArrayList<Object>();
			List<Object> remainRolesList =  new ArrayList<Object>();
			List<Object> remainDeptsList =  new ArrayList<Object>();
			
			for(Object role : availableRolesList){
				Roles temp = (Roles) role;
				if(NGPHUtil.isElementInList(assignedRoleIdList, temp.getRoleId())){
					assignedRoleList.add(temp);
				} else {
					remainRolesList.add(temp);
				}
			}
			session.put("RemainRolesList", remainRolesList);
			session.put("AssignedRolesList", assignedRoleIdList);
			for(Object dept : availableDeptsList){
				Department temp = (Department) dept;
				if(NGPHUtil.isElementInList(assignedDeptIdList, temp.getDeptCode())){
					assignedDeptList.add(temp);
				} else {
					remainDeptsList.add(temp);
				}
			}
			setCheckForSubmit("");
			session.put("RemainDeptsList", remainDeptsList);
			session.put("AssignedDeptsList", assignedDeptIdList);
			
			setDeptList(assignedDeptIdList);
			setRoleList(assignedRoleIdList);
			
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
		
	}
	
	
	public void validate(){
		
		try
		{
			String newPassword = userObject.getUserPassword();
			String userId = userObject.getUser();
			
			PasswordSecurityPolicyDto passwordSecurityPolicyDto = new PasswordSecurityPolicyDto();
			boolean isValid = true;
			int minChars;
			int maxChars;
			int noofDigits;
			int noofSpecialChars;
			int noofUpperChars;
			int noofLowerChars;
			
			int isNoofDigits=0;
			int isNoofSpecialChars=0;
			int isNoofUpperChars=0;
			int isNoofLowerChars=0;
			
			PasswordSecurityPolicyService passwordSecurityPolicyService = (PasswordSecurityPolicyService)ApplicationContextProvider.getBean("passwordSecurityPolicyService");
			passwordSecurityPolicyDto = passwordSecurityPolicyService.getSecurityPolicy();
			
			minChars = passwordSecurityPolicyDto.getMinChars();
			maxChars = passwordSecurityPolicyDto.getMaxChars();
			noofDigits = passwordSecurityPolicyDto.getNoofDigits();
			noofSpecialChars = passwordSecurityPolicyDto.getNoofSpecialChars();
			noofUpperChars = passwordSecurityPolicyDto.getNoofUpperChars();
			noofLowerChars = passwordSecurityPolicyDto.getNoofLowerChars();
			
			if(StringUtils.isNotBlank(userId) && StringUtils.isNotEmpty(userId))
			{
				if(!(userId.equalsIgnoreCase("SA1")) && !(userId.equalsIgnoreCase("SA2")))
				{
					//check min and max characters in password 
					if(newPassword.length() > maxChars ||  newPassword.length() < minChars)
					{
						isValid = false;
					}
					
					for(int i=0; i<newPassword.length(); i++)
					{
						//check count digits 
						if(Character.isDigit(newPassword.charAt(i)))
						{
							isNoofDigits++;
						}
						
						//Check count of noof Special characters
						if( newPassword.charAt(i) == '$' ) 
						{
							 isNoofSpecialChars++;
						} 
						if( newPassword.charAt(i) == '@' ) 
						{
							 isNoofSpecialChars++;
						} 
						if( newPassword.charAt(i) == '#' ) 
						{
							 isNoofSpecialChars++;
						}
						if( newPassword.charAt(i) == '%' ) 
						{
							 isNoofSpecialChars++;
						}
						
						//check count upper case characters
						if(Character.isUpperCase(newPassword.charAt(i)))
						{
							isNoofUpperChars++;
						}
		
						//check count lower case characters
						if(Character.isLowerCase(newPassword.charAt(i)))
						{
							isNoofLowerChars++;
						}
						
					}
					
					if(noofDigits > isNoofDigits)
					{
						isValid = false;
					}
					if(noofSpecialChars > isNoofSpecialChars)
					{
						isValid = false;
					}
					if(noofUpperChars > isNoofUpperChars)
					{
						isValid = false;
					}
					if(noofLowerChars > isNoofLowerChars)
					{
						isValid = false;
					}
									 
					 if(!isValid)
					 {
						 addActionError("Please Enter Valid password");
						 addActionError("According to the policy, the maximum length is "+maxChars +" characters minimum length is "+minChars+" characters no of uppercase characters is "+noofUpperChars+
								 " no of lowercase characters is "+noofLowerChars + " no of special characters is "+noofSpecialChars + " and no of digits is "+noofDigits);
					 }
				}
				
			}
			else
			{
				addActionError("Please Select User");
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
	}

}
