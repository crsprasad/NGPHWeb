package com.logica.ngph.web.action;

import java.math.BigDecimal;

import java.sql.Clob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextException;


import com.logica.ngph.common.ConstantUtil;
import com.logica.ngph.common.NGPHException;
import com.logica.ngph.service.AuthorizationMaitenanceService;
import com.logica.ngph.service.AuthorizationSchemaMaitenanceService;
import com.logica.ngph.service.PendingAuthorizationService;
import com.logica.ngph.web.utils.ApplicationContextProvider;
import com.logica.ngph.web.utils.WebConstants;

import com.opensymphony.xwork2.ActionSupport;

public class AuthorizationMaintenanceAction extends ActionSupport implements SessionAware
{
	private static final long serialVersionUID = -3364264106258064077L;
	static Logger logger = Logger.getLogger(AuthorizationMaintenanceAction.class);
	private Map<String, Object> session ;

	public Map<String, Object> getSession() {
		return session;
	}


	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
	private List<String> channelDropDown = new ArrayList<String>();
	private List<String> subMsgTypeDropDown = new ArrayList<String>();
	private List<String> msgTypeDropDown = new ArrayList<String>();
	private List<String> currencyDropDown = new ArrayList<String>();
	private List<String> hostID = new ArrayList<String>();
	private List<String> hostName = new ArrayList<String>();
	private List<String> branchName = new ArrayList<String>();
	private List<String> branchCode = new ArrayList<String>();
	private List<String> groupIDandGroupName = new ArrayList<String>();
	
	private String Branch_Name;
	private String branch_Code;
	private String host_name;
	private String host_code;
	private String channel_type;
	private String msg_type;
	private String subMsg_type;
	private String curreny_type;
	private BigDecimal from_Amount;
	private BigDecimal to_Amount;
	private List <String> selectgroupIDAndName=new ArrayList<String>();
	private String direction;
	private String message;
	private String checkForSubmit;
	private String saveAction;
	private String hiddenTxnRef;
	private String txnRef;
	private List <String> mulRecord=new ArrayList<String>();
	private boolean validUserToApprove;
	
	public List<String> getMulRecord() {
		return mulRecord;
	}


	public void setMulRecord(List<String> mulRecord) {
		this.mulRecord = mulRecord;
		this.session.put("mulRecord", mulRecord);
	}


	public String getCheckForSubmit() {
		return checkForSubmit;
	}


	public void setCheckForSubmit(String checkForSubmit) {
		this.checkForSubmit = checkForSubmit;
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
	private List <String> select1=new ArrayList<String>();
	
	
	public String getDirection() {
		return direction;
	}


	public void setDirection(String direction) {
		this.direction = direction;
	}


	public String getBranch_Name() {
		return Branch_Name;
	}


	public void setBranch_Name(String branch_Name) {
		Branch_Name = branch_Name;
	}


	public String getBranch_Code() {
		return branch_Code;
	}


	public void setBranch_Code(String branch_Code) {
		this.branch_Code = branch_Code;
	}


	public String getHost_name() {
		return host_name;
	}


	public void setHost_name(String host_name) {
		this.host_name = host_name;
	}


	public String getHost_code() {
		return host_code;
	}


	public void setHost_code(String host_code) {
		this.host_code = host_code;
	}


	public String getChannel_type() {
		return channel_type;
	}


	public void setChannel_type(String channel_type) {
		this.channel_type = channel_type;
	}


	public String getMsg_type() {
		return msg_type;
	}


	public void setMsg_type(String msg_type) {
		this.msg_type = msg_type;
	}


	public String getSubMsg_type() {
		return subMsg_type;
	}


	public void setSubMsg_type(String subMsg_type) {
		this.subMsg_type = subMsg_type;
	}


	public String getCurreny_type() {
		return curreny_type;
	}


	public void setCurreny_type(String curreny_type) {
		this.curreny_type = curreny_type;
	}


	public BigDecimal getFrom_Amount() {
		return from_Amount;
	}


	public void setFrom_Amount(BigDecimal from_Amount) {
		this.from_Amount = from_Amount;
	}


	public BigDecimal getTo_Amount() {
		return to_Amount;
	}


	public void setTo_Amount(BigDecimal to_Amount) {
		this.to_Amount = to_Amount;
	}


	public List<String> getSelectgroupIDAndName() {
		return selectgroupIDAndName;
	}


	public void setSelectgroupIDAndName(List<String> selectgroupIDAndName) {
		
		this.selectgroupIDAndName = selectgroupIDAndName;
		this.session.put("selectgroupIDAndName", selectgroupIDAndName);
	}


	public List<String> getHostID() {
		return hostID;
	}


	public void setHostID(List<String> hostID) {
		this.hostID = hostID;
		this.session.put("hostID",hostID);
	}


	public List<String> getHostName() {
		return hostName;
	}


	public void setHostName(List<String> hostName) {
		this.hostName = hostName;
		this.session.put("hostName",hostName);
	}


	public List<String> getBranchName() {
		return branchName;
	}


	public void setBranchName(List<String> branchName) {
		this.branchName = branchName;
		this.session.put("branchName",branchName);
	}


	public List<String> getBranchCode() {
		return branchCode;
	}


	public void setBranchCode(List<String> branchCode) {
		this.branchCode = branchCode;
		this.session.put("branchCode",branchCode);
	}


	public List<String> getGroupIDandGroupName() {
		return groupIDandGroupName;
	}


	public void setGroupIDandGroupName(List<String> groupIDandGroupName) {
		this.groupIDandGroupName = groupIDandGroupName;
		this.session.put("groupIDandGroupName",groupIDandGroupName);
	}


	public void setChannelDropDown(List<String> channelDropDown) {
		this.channelDropDown = channelDropDown;
		this.session.put("channelDropDown",channelDropDown);
	}

	public List<String> getChannelDropDown() {
		return channelDropDown;
	}
	public List<String> getSubMsgTypeDropDown() {
		return subMsgTypeDropDown;
	}

	public void setSubMsgTypeDropDown(List<String> subMsgTypeDropDown) {
		this.subMsgTypeDropDown = subMsgTypeDropDown;
		this.session.put("subMsgTypeDropDown",subMsgTypeDropDown);
	}

	public List<String> getMsgTypeDropDown() {
		return msgTypeDropDown;
	}

	public void setMsgTypeDropDown(List<String> msgTypeDropDown) {
		this.msgTypeDropDown = msgTypeDropDown;
		this.session.put("msgTypeDropDown",msgTypeDropDown);
	}

	public List<String> getCurrencyDropDown() {
		return currencyDropDown;
	}

	public void setCurrencyDropDown(List<String> currencyDropDown) {
		this.currencyDropDown = currencyDropDown;
		this.session.put("currencyDropDown",currencyDropDown);
	}
	
	/**
	* This method is used to get Dynamic Data for database for drop down box
	* @return Dropdown Data
	 * @throws SQLException 
	*/
	private Map <String,Object> treeView;
	public Map<String, Object> getTreeView() {
		return treeView;
	}


	public void setTreeView(Map<String, Object> treeView) {
		this.treeView = treeView;
		this.session.put("treeView", treeView);
	}

	/*public String treeView() throws SQLException, NGPHException
	{
		AuthorizationSchemaMaitenanceService authorizationSchemaMaitenanceService = getAuthorizationSchemaMaitenanceService();
		String createQuery="";
		
		
		if(getSelect1().size()!=0){
		createQuery="where";
		
		for(int i=0;i<getSelect1().size();i++){
			
			String[] groupid=getSelect1().get(i).split(" ");
		createQuery=createQuery+" groupID ='"+groupid[0]+"' OR ";
		
		}
		createQuery=createQuery.substring(0, createQuery.length()-3);
		}
		treeView=authorizationSchemaMaitenanceService.treeView(createQuery);
		List <String> collection=authorizationSchemaMaitenanceService.getAuthorizationSchemaMaitenanceService(ConstantUtil.GROUPIDANDGROUPNAME);
		setGroupIDandGroupName(authorizationSchemaMaitenanceService.getAuthorizationSchemaMaitenanceService(ConstantUtil.GROUPIDANDGROUPNAME));
	
		setSelectgroupIDAndName(getSelect1());
	
			
		for(int i=0;i<getSelect1().size();i++){
			collection.remove(getSelect1().get(i));
		
		}
		setGroupIDandGroupName(collection);
	
		return "success";
	}*/
	
	@SkipValidation
	public String loadDataAuthorization() throws NGPHException, SQLException
	{
		AuthorizationSchemaMaitenanceService authorizationSchemaMaitenanceService = getAuthorizationSchemaMaitenanceService();
		setChannelDropDown(authorizationSchemaMaitenanceService.getAuthorizationSchemaMaitenanceService(ConstantUtil.CHANNEL));
		setMsgTypeDropDown(authorizationSchemaMaitenanceService.getAuthorizationSchemaMaitenanceService(ConstantUtil.MSGTYPE));
		setSubMsgTypeDropDown(authorizationSchemaMaitenanceService.getAuthorizationSchemaMaitenanceService(ConstantUtil.MSGSUBTYPE));
		setHostID(authorizationSchemaMaitenanceService.getAuthorizationSchemaMaitenanceService(ConstantUtil.HOSTID_H));
		setHostName(authorizationSchemaMaitenanceService.getAuthorizationSchemaMaitenanceService(ConstantUtil.HOSTNAME_H));
		setBranchCode(authorizationSchemaMaitenanceService.getAuthorizationSchemaMaitenanceService(ConstantUtil.BRANCHCODE));
		setBranchName(authorizationSchemaMaitenanceService.getAuthorizationSchemaMaitenanceService(ConstantUtil.BRANCHNAME));
		setCurrencyDropDown(authorizationSchemaMaitenanceService.getAuthorizationSchemaMaitenanceService(ConstantUtil.CURRENCY));
		treeView=authorizationSchemaMaitenanceService.treeView();
		setGroupIDandGroupName(authorizationSchemaMaitenanceService.getAuthorizationSchemaMaitenanceService(ConstantUtil.GROUPIDANDGROUPNAME));
		
		 	
		
		return "success";
	}
	//AuthorizationMaitenanceService authorizationSchemaMaitenance;
	

	public String insertData(){
		
		
		try{
			
			PendingAuthorizationService pendingAuthorizationService = (PendingAuthorizationService)ApplicationContextProvider.getBean("pendingAuthorizationService");
			String delimitedString="";
			String txnKey="";
			String status="";
			if(getSaveAction().equals("Save")){
			delimitedString=getChannel_type()+ConstantUtil.delimiter+getMsg_type()+ConstantUtil.delimiter+getSubMsg_type()+ConstantUtil.delimiter+getCurreny_type()+ConstantUtil.delimiter
			+getBranch_Name()+ConstantUtil.delimiter+getHost_name()+ConstantUtil.delimiter+getFrom_Amount()+ConstantUtil.delimiter+getTo_Amount()+ConstantUtil.delimiter+getDirection();
			String userId = (String)session.get(WebConstants.CONSTANT_USER_NAME);
			if(!getHiddenTxnRef().isEmpty())
			{
				pendingAuthorizationService.updateRejectStatusToPending(getHiddenTxnRef());
			}else
			{
				txnKey = pendingAuthorizationService.getTranRef(delimitedString,"Authorization Schema",userId);
			}
			
			
			}
			else
			{
				System.out.println(getSaveAction()+"getTxnRef : - "+getTxnRef());
				status= pendingAuthorizationService.changeStatus( getSaveAction(),getHiddenTxnRef());
			}
			session.put("screenRef", txnKey);
		AuthorizationMaitenanceService authorizationMaitenanceService = (AuthorizationMaitenanceService)ApplicationContextProvider.getBean("authorizationSchemaMaitenance");
		int i=0;
 		int count=0;
 		int count1=0;
 		ArrayList<String> obj = null;
 		ArrayList<String> objBranch = null;
		java.util.Iterator<Entry<String, Object>> itr= session.entrySet().iterator();
		
			Map.Entry entry = (Map.Entry) itr.next();
			String key = entry.getKey().toString();
			obj = (ArrayList<String>)session.get("hostName");
			objBranch = (ArrayList<String>)session.get("branchName");
			
			for(i=0;i<obj.size();i++){
			if(obj.get(i).equals(getHost_name())){
				count=i;
				
				}
			}
			for(i=0;i<objBranch.size();i++){
				if(objBranch.get(i).equals(getBranch_Name())){
					count1=i;
					
					}
				}
						
			ArrayList<String> matchedValues = (ArrayList<String>)session.get("hostID");
			System.out.println(obj + "***************match*****"+matchedValues);
		String Hostreleventcode=matchedValues.get(count);
		System.out.println(Hostreleventcode);
		
		ArrayList<String> matchedValuesForBranch = (ArrayList<String>)session.get("branchCode");
		System.out.println(objBranch + "***************match*****"+matchedValuesForBranch);
	String branchreleventbranchcode=matchedValuesForBranch.get(count1);
	System.out.println(branchreleventbranchcode);
	List selectedItems=getSelectgroupIDAndName();
	String returnvalue="false";
	
	
	if(Double.parseDouble(getFrom_Amount().toString()) > Double.parseDouble(getTo_Amount().toString()))
	{
	
		addFieldError("to_Amount", "To Amount Must Be Bigger That From Amount");
	}
	
	if (selectedItems.size()!=0){
	for(int j=0;j<selectedItems.size();j++)
	{
		
		//for(int j=0;j< (selectedItems.get(i))).length();j++){}
		String arr[] = selectedItems.get(j).toString().split(" ");
		
		System.out.println(arr[0]+arr[1]);
		if(getSaveAction().equals("Approve")){
		returnvalue=authorizationMaitenanceService.insertDataInTA_AUTHSCHEMAM(branchreleventbranchcode, Hostreleventcode, getChannel_type(), getMsg_type(), getSubMsg_type(), getDirection(), getCurreny_type(), getFrom_Amount(), getTo_Amount(),arr[0] , j);
		}
		else if(getSaveAction().equals("Save"))
		{
			pendingAuthorizationService.delimitedStringValue(txnKey, j+"", selectedItems.get(j).toString());
		}
	}
	}
	else 
		addFieldError("selectgroupIDAndName", "To Amount Must Be Bigger That From Amount");
		
	if(returnvalue.equals("success"))
	{
		setMessage("Record Are Inserted");
	}
		}
		catch (Exception e) {
			// TODO: handle exception
		}
	
		return "success";
	}
	/*public void setAuthorizationSchemaMaitenance(
			AuthorizationMaitenanceService authorizationSchemaMaitenance) {
		this.authorizationSchemaMaitenance = authorizationSchemaMaitenance;
	}*/
	AuthorizationSchemaMaitenanceService authorizationSchemaMaitenanceService;
	/*public void setAuthorizationSchemaMaitenanceService(
			AuthorizationSchemaMaitenanceService authorizationSchemaMaitenanceService) {
		this.authorizationSchemaMaitenanceService = authorizationSchemaMaitenanceService;
	}*/


	private AuthorizationSchemaMaitenanceService getAuthorizationSchemaMaitenanceService() {
		 
		try{
			//ApplicationContext appcontext = new ClassPathXmlApplicationContext("WebApplicationContext.xml");
			
			authorizationSchemaMaitenanceService = (AuthorizationSchemaMaitenanceService)ApplicationContextProvider.getBean("authorizationSchemaMaitenanceService");
		}catch (ApplicationContextException applicationContextException) {
		logger.debug(applicationContextException);	
		}
		
		return authorizationSchemaMaitenanceService;
	}
	public void validate(){

		try {
			String loadedData = loadDataAuthorization();
		} catch (NGPHException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		System.out.println("Inside AuthMainSchema---0--validate-----getFrom_Amount()-----"+getFrom_Amount()+"-----"+this.from_Amount);
		
		
			
		
		System.out.println("Inside AuthMainSchema---2--validate");
		
	}
	@SkipValidation
	public String getAuthorization() throws NGPHException, SQLException
	{
		PendingAuthorizationService pendingAuthorizationService = (PendingAuthorizationService)ApplicationContextProvider.getBean("pendingAuthorizationService");
		loadDataAuthorization();
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
		setChannel_type(stringBreaker[0]);
		setMsg_type(stringBreaker[1]);
		setSubMsg_type(stringBreaker[2]);
		setCurreny_type(stringBreaker[3]);
		setBranch_Name(stringBreaker[4]);
		setHost_name(stringBreaker[5]);
		setFrom_Amount(new BigDecimal(stringBreaker[6]));
		setTo_Amount(new BigDecimal(stringBreaker[7]));
		setDirection(stringBreaker[8]);
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
	
	public void setMessage(String message) {
		this.message = message;
	}


	public String getMessage() {
		return message;
	}


	public void setSelect1(List <String> select1) {
		System.out.println(" List <String> select :- "+ select1);
		this.session.put("select1",select1);
		this.select1 = select1;
	}


	public List <String> getSelect1() {
		return select1;
	}


	public boolean getValidUserToApprove() {
		return validUserToApprove;
	}


	public void setValidUserToApprove(boolean validUserToApprove) {
		this.validUserToApprove = validUserToApprove;
	}


	
}
