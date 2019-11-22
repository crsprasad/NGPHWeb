package com.logica.ngph.web.action;

import java.sql.Clob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.springframework.context.ApplicationContextException;

import com.logica.ngph.common.ConstantUtil;
import com.logica.ngph.common.NGPHException;
import com.logica.ngph.service.AuthStpService;
import com.logica.ngph.service.AuthUIService;
import com.logica.ngph.service.GroupMainService;
import com.logica.ngph.service.GroupMaintenanceService;
import com.logica.ngph.service.PendingAuthorizationService;
import com.logica.ngph.serviceImpl.AuthUIServiceImpl;
import com.logica.ngph.web.utils.ApplicationContextProvider;
import com.logica.ngph.web.utils.WebConstants;

import com.opensymphony.xwork2.ActionSupport;



public class GroupMaintenanceAction extends ActionSupport implements SessionAware
{
	private static final long serialVersionUID = -3364264106258064077L;
	static Logger logger = Logger.getLogger(GroupMaintenanceAction.class);
	private Map<String, Object> session ;
	
	private String groupID;
	private String groupName;
	private String branchCode;
	private List <String> BranchCodeDropDown=new ArrayList<String>();
	private List <String> BranchNameDropDown=new ArrayList<String>();
	private List <String> supervisorSelect=new ArrayList<String>();
	private List <String> mulRecord=new ArrayList<String>();
	public List<String> getMulRecord() {
		return mulRecord;
	}
	public void setMulRecord(List<String> mulRecord) {
		this.mulRecord = mulRecord;
		this.session.put("mulRecord", mulRecord);
	}
	private String message;
	private String checkForSubmit;
	private String saveAction;
	private String hiddenTxnRef;
	private String txnRef;
	private boolean validUserToApprove;
	
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
	public List<String> getSupervisorSelect() {
			return supervisorSelect;
	}
	public void setSupervisorSelect(List<String> supervisorSelect) {
		this.supervisorSelect = supervisorSelect;
		this.session.put("supervisorSelect", supervisorSelect);
	}
	public String getGroupID() {
		return groupID;
	}
	public void setGroupID(String groupID) {
		this.groupID = groupID;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public String getBranchCode() {
		return branchCode;
	}
	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}
	public List<String> getBranchCodeDropDown() {
		return BranchCodeDropDown;
	}
	public void setBranchCodeDropDown(List<String> branchCodeDropDown) {
		BranchCodeDropDown = branchCodeDropDown;
		this.session.put("BranchCodeDropDown", BranchCodeDropDown);
	}
	public List<String> getBranchNameDropDown() {
		return BranchNameDropDown;
	}
	public void setBranchNameDropDown(List<String> branchNameDropDown) {
		BranchNameDropDown = branchNameDropDown;
		this.session.put("BranchNameDropDown", branchNameDropDown);
	}
	public Map<String, Object> getSession() {
		return session;
	}
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
	private List <String> supervisorSelectbox= new ArrayList<String>();
	public void setSupervisorSelectbox(List <String> supervisorSelectbox) {
		this.supervisorSelectbox = supervisorSelectbox;
	}
	public List <String> getSupervisorSelectbox() {
		return supervisorSelectbox;
	}
	/*This method load the Date when this class is called
	 * In this method it will set the data in the drop down list 
	 * i.e. Get the branch code and branch name.
	 * 
	 * */
	@SkipValidation
	public String loadDataGroup() throws NGPHException
	{
		supervisorSelect.add("101");
		supervisorSelect.add("102");
		supervisorSelect.add("103");
		supervisorSelect.add("104");
		supervisorSelect.add("105");
		supervisorSelect.add("106");
		getGroupMaintenanceService();
		setBranchCodeDropDown(groupMaintenanceService.getGroupMaintenanceService(ConstantUtil.GROUPMAINTENANANCEBRANCHCODE));
		setBranchNameDropDown(groupMaintenanceService.getGroupMaintenanceService(ConstantUtil.GROUPMAINTENANANCEBRANCHNAME));
		setSupervisorSelect(supervisorSelect);
		
		return "getValue";
	}
	/*This method is to get the data from the Maintenance Screen and insert the 
	 * data in the database ane return success if the data is inserted 
	 * properly.
	 * 
	 * */
	
	GroupMainService groupMaintenance;
	@SuppressWarnings("null")
	public String insertData() throws NGPHException
	{
		
	//	ApplicationContext appcontext = new ClassPathXmlApplicationContext("WebApplicationContext.xml");
		PendingAuthorizationService pendingAuthorizationService = (PendingAuthorizationService)ApplicationContextProvider.getBean("pendingAuthorizationService");
		groupMaintenance = (GroupMainService)ApplicationContextProvider.getBean("groupMaintenance");
		String delimitedString ="";
		String isPresent=null;
		try {
			if(!getGroupID().equals(""))
			isPresent=groupMaintenance.checkGroupIDAlreadyPresent(getGroupID());
			else
				addFieldError("groupID", "Group ID is required");
			if(isPresent.equals("failure"))
				addFieldError("groupID", "Group ID is Already Avialble");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(isPresent.equalsIgnoreCase("success"))
		{
		System.out.println(getBranchCode());
 		int i=0;
 		int count=0;
 		ArrayList<String> obj = null;
		
			obj = (ArrayList<String>)session.get("BranchNameDropDown");
			
			for(i=0;i<obj.size();i++){
			if(obj.get(i).equals(getBranchCode())){
				count=i;
				
				}
			}
			@SuppressWarnings("unchecked")
			ArrayList<String> matchedValues = (ArrayList<String>)session.get("BranchCodeDropDown");
			System.out.println(obj + "***************match*****"+matchedValues);
		String Branchreleventcode=matchedValues.get(count);
		System.out.println(Branchreleventcode);
		List selectedItems=getSupervisorSelectbox();
		String key="";
		
		int isMandatory=0;
		String returnvalue="false";
		String status="";
		int countforSelect=0;
		if(getSaveAction().equals("Save")){
		delimitedString = delimitedString +getBranchCode()+ConstantUtil.delimiter+getGroupID()+ConstantUtil.delimiter+getGroupName();
		String userId = (String)session.get(WebConstants.CONSTANT_USER_NAME);
		key = pendingAuthorizationService.getTranRef(delimitedString,"Group Maintenance",userId);
		}
		else
		{
			status= pendingAuthorizationService.changeStatus( getSaveAction(),getHiddenTxnRef());
		}
		
		try{
		if(selectedItems.size()!=0){
		for(int j=0;j<selectedItems.size();j++)
		{
			countforSelect++;
			//for(int j=0;j< (selectedItems.get(i))).length();j++){}
			String arr[] = selectedItems.get(j).toString().split(" ");
			
			System.out.println(arr[0]+" ---- "+arr[1]);
			//changing the value for mandatory or for isOptional fields 
			if(arr[1].equals("M"))
				isMandatory=1;
			else
				isMandatory=0;
				
			System.out.println(Branchreleventcode+getGroupID()+ getGroupName()+ arr[0]+isMandatory);
			
			//Insert the values in data base
			if(getSaveAction().equals("Approve")){
			returnvalue=groupMaintenance.insertDataInAuthGRPM(Branchreleventcode, getGroupID(), getGroupName(), arr[0],
											j,isMandatory);
			}
			else if(getSaveAction().equals("Save"))
			{
				pendingAuthorizationService.delimitedStringValue(key, j+"", selectedItems.get(j).toString());
				
			
			}
			
			
		}
		}
		else
			addFieldError("supervisorSelectbox", "Please Select atleast one Option");
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		if(returnvalue.equals("success"))
		{
			setMessage("Record Are Inserted");
		}
		}
		
		
		return "success";
	}
	
/*This method is to get the bean form the application context.
 */
	GroupMaintenanceService groupMaintenanceService;
	

	private GroupMaintenanceService getGroupMaintenanceService() {
		
		try{
			groupMaintenanceService = (GroupMaintenanceService)ApplicationContextProvider.getBean("groupMaintenanceService");
		}catch (ApplicationContextException applicationContextException) {
		logger.debug(applicationContextException);	
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return groupMaintenanceService;
	}
	@SkipValidation
	public String getAuthorization() throws NGPHException, SQLException
	{
		PendingAuthorizationService pendingAuthorizationService = (PendingAuthorizationService)ApplicationContextProvider.getBean("pendingAuthorizationService");
		loadDataGroup();
		setHiddenTxnRef(getTxnRef());
		setCheckForSubmit("Display_Approve_Reject");
		String userId = pendingAuthorizationService.getCreatedUser(getTxnRef());
		String userRole = pendingAuthorizationService.getUserType((String)session.get(WebConstants.CONSTANT_USER_NAME));
		if((((String)session.get(WebConstants.CONSTANT_USER_NAME)).equals(userId) || userRole.equals("T"))){
			setValidUserToApprove(false);
		} else {
			setValidUserToApprove(true);
		}
		String requiredString =pendingAuthorizationService.getScreenReturn(getTxnRef());
		String[] stringBreaker = requiredString.split("~");
		setBranchCode(stringBreaker[0]);
		setGroupID(stringBreaker[1]);
		setGroupName(stringBreaker[2]);
		List mulDataList = pendingAuthorizationService.getMulScreenData(getTxnRef());
		List<String> tempDataList = new ArrayList<String>();
		for(int i=0;i<mulDataList.size();i++)
		{
			Clob temp = (Clob)mulDataList.get(i);
			String data= temp.getSubString(1, (int) temp.length()).toString();
			tempDataList.add(data);
		}
		setMulRecord(tempDataList);
		return "success";
	}
	
	
	/*This method is struts predefine method call automatically if there is any 
	 * validation is done.
	 * 
	*/
	public void validate()
	{
		System.out.println("VALIDATOR");
	}


	public void setMessage(String message) {
		this.message = message;
	}


	public String getMessage() {
		return message;
	}
	public boolean getValidUserToApprove() {
		return validUserToApprove;
	}
	public void setValidUserToApprove(boolean validUserToApprove) {
		this.validUserToApprove = validUserToApprove;
	}
	
	
}
