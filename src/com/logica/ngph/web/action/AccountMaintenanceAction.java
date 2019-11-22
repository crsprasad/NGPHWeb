package com.logica.ngph.web.action;

import java.sql.Clob;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.springframework.context.ApplicationContextException;

import com.logica.ngph.Entity.Account;
import com.logica.ngph.Entity.Limits;
import com.logica.ngph.Entity.SecUsers;
import com.logica.ngph.common.ConstantUtil;
import com.logica.ngph.common.NGPHException;
import com.logica.ngph.common.dtos.EventAudit;
import com.logica.ngph.common.enums.AccountTypeEnums;
import com.logica.ngph.dtos.AddressDto;
import com.logica.ngph.service.AccountMaintenaceService;
import com.logica.ngph.service.AuditService;
import com.logica.ngph.service.AuthorizationSchemaMaitenanceService;
import com.logica.ngph.service.PaymentMessageService;
import com.logica.ngph.service.PendingAuthorizationService;
import com.logica.ngph.web.utils.ApplicationContextProvider;
import com.logica.ngph.web.utils.WebConstants;
import com.opensymphony.xwork2.ActionSupport;

public class AccountMaintenanceAction extends ActionSupport implements SessionAware{

	/**
	 * 
	 */
	static Logger logger = Logger.getLogger(AccountMaintenanceAction.class);
	private static final long serialVersionUID = 1L;
	private Map<String, Object> session ;
	private String accountNo;
	private String MMDI;
	private String branch;
	private String creditLimit;
	private String debitLimit;
	private String accountype;
	private String hiddenBranch;
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
	public String getHiddenBranch() {
		return hiddenBranch;
	}


	public void setHiddenBranch(String hiddenBranch) {
		this.hiddenBranch = hiddenBranch;
	}
	private List<String> branchName = new ArrayList<String>();
	private List<String> branchCode = new ArrayList<String>();
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

	public String getBranch() {
		return branch;
	}
	public void setBranch(String branch) {
		this.branch = branch;
	}
	public String getCreditLimit() {
		return creditLimit;
	}
	public void setCreditLimit(String creditLimit) {
		this.creditLimit = creditLimit;
	}
	public String getDebitLimit() {
		return debitLimit;
	}
	public void setDebitLimit(String debitLimit) {
		this.debitLimit = debitLimit;
	}
	public String getAccountype() {
		return accountype;
	}
	public void setAccountype(String accountype) {
		this.accountype = accountype;
		this.session.put("accountype", accountype);
		
	}
	private String SaveModifyOrDelete;
	private String message;
	private String mobileNo;
	public static int addCount=0;
	private String setAsDefault;
	private String hiddenTxnRef;
	private String txnRef;
	private String saveAction;
	private String checkForSubmit;
	private String hiddenList;
	private String hiddenSize;
	private String hiddenRadio;
	private boolean validUserToApprove;
	private String hiddenAccountType;
	
	
	

	public String getHiddenAccountType() {
		return hiddenAccountType;
	}


	public void setHiddenAccountType(String hiddenAccountType) {
		this.hiddenAccountType = hiddenAccountType;
	}


	public String getHiddenSize() {
		return hiddenSize;
	}
	public void setHiddenSize(String hiddenSize) {
		this.hiddenSize = hiddenSize;
	}
	public String getHiddenRadio() {
		return hiddenRadio;
	}
	public void setHiddenRadio(String hiddenRadio) {
		this.hiddenRadio = hiddenRadio;
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
	public String getHiddenList() {
		return hiddenList;
	}
	public void setHiddenList(String hiddenList) {
		this.hiddenList = hiddenList;
	}
	public String getSetAsDefault() {
		return setAsDefault;
	}
	public void setSetAsDefault(String setAsDefault) {
		this.setAsDefault = setAsDefault;
	}
	private List<Boolean>	 select; 
		public List<Boolean> getSelect() {
			return select;
		}
		public void setSelect(List<Boolean> select) {
			this.select = select;
		}
	
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public Map<String, Object> getSession() {
		return session;
	}
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
	@SuppressWarnings("rawtypes")
	private List searchList;
	public List getSearchList() {
		return searchList;
	}
	public void setSearchList(List searchList) {
		this.searchList = searchList;
		this.session.put("searchList", searchList);
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getAccountNo() {
		return accountNo;
	}
	public boolean getValidUserToApprove() {
		return validUserToApprove;
	}
	public void setValidUserToApprove(boolean validUserToApprove) {
		this.validUserToApprove = validUserToApprove;
	}
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public String getMMDI() {
		return MMDI;
	}
	public void setMMDI(String mMDI) {
		MMDI = mMDI;
	}
	public String getSaveModifyOrDelete() {
		return SaveModifyOrDelete;
	}
	public void setSaveModifyOrDelete(String saveModifyOrDelete) {
		SaveModifyOrDelete = saveModifyOrDelete;
	}
	
	@SkipValidation
	public String displayAccountMantenance() throws NGPHException
	{
		logger.info("Inside AccountMaintanence<displayAccountMantenance>");
		//AccountMaintenaceService accountMaintenaceService = (AccountMaintenaceService)ApplicationContextProvider.getBean("accountMaintenanceService");
		//setSearchList(accountMaintenaceService.getGridView());
		AuthorizationSchemaMaitenanceService authorizationSchemaMaitenanceService = (AuthorizationSchemaMaitenanceService)ApplicationContextProvider.getBean("authorizationSchemaMaitenanceService");
		setBranchCode(authorizationSchemaMaitenanceService.getAuthorizationSchemaMaitenanceService(ConstantUtil.BRANCHCODE));
		
		addCount=0;
		count=0;
		temportList.clear();
		session.remove("searchList");
		setSaveModifyOrDelete("Modify");
		
		logger.info("End AccountMaintanence<displayAccountMantenance>");
		return "success";
	}
	static List<AddressDto> temportList = new ArrayList<AddressDto>();
	static int count=0;
	@SkipValidation
	public String getAddTOGrid() throws NGPHException
	{
		try{
			logger.info("Inside AccountMaintanence<getAddTOGrid>");
		AccountMaintenaceService accountMaintenaceService = getApplicationContext();
		if(!getAccountNo().isEmpty() && !getMobileNo().isEmpty()&& getExpressionCheck("[0-9]{10}", getMobileNo()) && StringUtils.isNotBlank(getAccountNo()) && getMMDI().length()==7 && getExpressionCheck("[0-9]{7}", getMMDI())){
		if(count==0){
		setSearchList(accountMaintenaceService.getGridViewAdd(getAccountNo(),getMobileNo(),getMMDI()));
		
		temportList = searchList;
		AddressDto addressDto = new AddressDto();
		addressDto.setMMID(getMMDI());
		addressDto.setMobileNo(getMobileNo());
		addressDto.setFlag("add");
		temportList.add(addressDto);
		}
		else
		{
			AddressDto addressDto = new AddressDto();
			addressDto.setMMID(getMMDI());
			addressDto.setMobileNo(getMobileNo());
			addressDto.setFlag("add");
			temportList.add(addressDto);
			
		}
		count++;
		setSearchList(temportList);
		/*setAccountNo("");*/
		setMMDI("");
		setMobileNo("");
		}
		else{
			if(getAccountNo().isEmpty()||getMobileNo().isEmpty())
				addFieldError("mobileNo", "All fields Are Mandatory");
		else if(getMMDI().length()!=7){
				addFieldError("MMDI", "MMID Should Be Of Lenght 7 ");
			}
		else if(!getExpressionCheck("[0-9]{10}", getMobileNo()))
		{
			addFieldError("mobileNo", "Mobile No Should Of 10 Digit");
		}
		else
			addFieldError("mobileNo", "All fields Are Mandatory");
		}
		addCount++;
		logger.info("End AccountMaintanence<getAddTOGrid>");
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
		logger.error("Exception Occured Inside AccountMaintanence<getAddTOGrid>");
		addActionError("Unable to perform the operation. Please try again");
		return "input";
	}
	
	@SkipValidation
	public String getActionPerform() throws NGPHException, SQLException{
		try{
			logger.info("Inside AccountMaintanence<getActionPerform>");
		AccountMaintenaceService accountMaintenaceService = getApplicationContext();
		String accountNoAllready=accountMaintenaceService.accountNOAllreadyExit(getAccountNo());
		String returnTypr="success";
		String tempMMID="";
		int defaultcount=0;
		if(accountNoAllready.equals("Available")){
		
		Iterator entries1 = session.entrySet().iterator();
		List<AddressDto> selectedList = new ArrayList<AddressDto>();
		
		while (entries1.hasNext())
		{
			Map.Entry entry = (Map.Entry) entries1.next();
			Object key = (Object)entry.getKey();
			if(key.equals("searchList")){
				selectedList = (List) entry.getValue();
			}
		}
		if(selectedList.size()!=0){
		AddressDto obj =  selectedList.get(selectedList.size()-1);
		tempMMID = obj.getMMID();
		
		Account account = new Account();
		account.setAccountNo(getAccountNo());
		account.setMMID(tempMMID);
		int count =0;
		if(tempMMID.length()==7 && !getAccountNo().equals("") && select.size()!=0)
		{
			count =1;
		}
		else if(getAccountNo().equals("")){
			addFieldError("accountNo", "Account No Is required");
		returnTypr="input";
		}
			else{
			addFieldError("MMID", "MMID Length Should Be 7");
			returnTypr="input";
			}
			if(count==1){
				
				if(StringUtils.isNotEmpty(getSetAsDefault()) && !getSetAsDefault().equals(null))
				{
					for ( int i = 0; i < select.size(); i++ ) { 
						 AddressDto object =  selectedList.get(i);
						    if ( select.get(i) != null && select.get(i) ) {
						    	if((object.getMMID()+","+object.getMobileNo()).equals(getSetAsDefault()))
					    		{
						    		defaultcount++;
					    		}
						    }
					}
					if(defaultcount==0){
					String returnString = doOperation();
					return returnString;
					}
					else{
						addFieldError("setAsDefault", "Cannot Delete With default Select Operation Fails");
						 returnTypr="input";
					}
					//session.remove("searchList");
					/*showGrid();
					setMessage("Operation SuccessFull");*/
				}
		else
		{
			addFieldError("MMID", "Please Chose Default Field");
			returnTypr="input";
		}
		//	accountMaintenaceService.updateRules(account);
			
			
			}
		}
		else
		{
			addFieldError("MMID", "Nothing Found To Take Action");
			returnTypr="input";
		}
		}
		else{
			addFieldError(accountNo, "Account Number Is Not Avialable In Database");
		 returnTypr="input";
				
		}
	
		return returnTypr;
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
	@SkipValidation
	public String getMMIDGenerator() throws NGPHException
	{
		try{
		AccountMaintenaceService accountMaintenaceService = getApplicationContext();
		
		String branch =getBranch();
	//System.out.println(getBranch()+"Brach");
		if(!getAccountNo().equals("") && !getMobileNo().equals("")&&getExpressionCheck("[0-9]{10}", getMobileNo()) )
		{
			String accountNoAllready=accountMaintenaceService.accountNOAllreadyExit(getAccountNo());
			if(accountNoAllready.equals("Available")){
				
				int count=0;
				Map<String, Object> map = session;
				String userId = (String)session.get(WebConstants.CONSTANT_USER_NAME);
				if(userId==null)
				{
					
					return "input";
				}
								String getMMID = accountMaintenaceService.getLocnBin(userId);
				List templist=new ArrayList();
				
				if(StringUtils.isNotEmpty(getMMID) && !getMMID.equals(null)){
					String RandomNo="";
					for(int i=0;i<temportList.size();i++)
					{
						AddressDto obj =  temportList.get(i);
						if(obj.getMobileNo().equals(getMobileNo()))	{
							templist.add(obj.getMMID());
							
						}
					}
				if(templist.size()!=0){
					Collections.sort(templist);
					String mmidString=templist.get(templist.size()-1).toString();
					int id =Integer.parseInt(mmidString.substring(mmidString.length()-3, mmidString.length()));
					id=id+1;
					if((id+"").length()==1)
					{
						RandomNo="00"+id;	
					}
					else if((id+"").length()==2)
							RandomNo="0"+id;	
					}else
						RandomNo = "001";
				
				String check = accountMaintenaceService.getMMID(getMobileNo(),getMMID+RandomNo);
				if(!check.equals(""))
				{
					int id =Integer.parseInt(check.substring(check.length()-3, check.length()));
					id=id+1;
					if((id+"").length()==1)
					{
						RandomNo="00"+id;	
					}
					else if((id+"").length()==2)
							RandomNo="0"+id;	
					}
					
				
				if((getMMID+RandomNo).length()==7)
				setMMDI(getMMID+RandomNo);
				else if((getMMID+RandomNo).length()<7)
					setMMDI(getMMID+RandomNo);
				else
					setMMDI((getMMID+RandomNo));
				setBranch(branch);
				setHiddenBranch(getBranch());
				}
				else{
					if(getMMID==null && getMMID.equals(null))
					addFieldError("MMID", "Invalid User For This Branch");
					return "input";
				}
			}
			else
				addFieldError(accountNo, "Account Number Is Not Avialable In Database");
			
			
		}
		else{
			if(getAccountNo().equals(""))
			addFieldError("accountNo", "Account Number Is required");
		if(getMobileNo().equals(""))
			addFieldError("mobileNo", "Mobile Number Is required And Should Be Of Ten Digit");
		else if(!getExpressionCheck("[0-9]{10}", getMobileNo()))
			{
				addFieldError("mobileNo", "Mobile Number Should Be Of ten Digit");
			}
		}
		
		//System.out.println("Generator Is Called"+getBranch());
		setBranch(branch);
		setHiddenBranch(getBranch());
		
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
	
	public String doOperation() throws SQLException, NGPHException
	{
		try{
		AccountMaintenaceService accountMaintenaceService = getApplicationContext();
		PendingAuthorizationService pendingAuthorizationService = (PendingAuthorizationService)ApplicationContextProvider.getBean("pendingAuthorizationService");
		AuditService	auditService = (AuditService)ApplicationContextProvider.getBean("auditService");
		Iterator entries1 = session.entrySet().iterator();
		List<AddressDto> selectedList = new ArrayList<AddressDto>();
	//	System.out.println(getSetAsDefault());
		Account account= new Account();
		account.setAccountNo(getAccountNo());
		
		Limits limits = new Limits();
		
		limits.setCreditLimit(getCreditLimit());
		limits.setDebitLimit(getDebitLimit());
		limits.setIdentifier(getBranch()+getAccountNo());
		limits.setLimitFor("A");
		String txnRef="";
		int count=0;
		selectedList = (List) session.get("searchList");
		/*while (entries1.hasNext())
		{
			Map.Entry entry = (Map.Entry) entries1.next();
			Object key = (Object)entry.getKey();
			if(key.equals("searchList")){
				selectedList = (List) entry.getValue();
			}
		}*/
		 for ( int i = 0; i < select.size(); i++ ) { 
			 AddressDto obj =  selectedList.get(i);
			    if ( select.get(i) != null && select.get(i) ) {
			    	if((obj.getMMID()+","+obj.getMobileNo()).equals(getSetAsDefault()))
		    		{
		    			count++;
		    		}
			    }
		}
		if(getSaveAction().equals("Save"))
		{
			String userId = (String)session.get(WebConstants.CONSTANT_USER_NAME);
			if(StringUtils.isNotBlank( getBranch()) && StringUtils.isNotEmpty(getBranch()) && 
					StringUtils.isNotBlank( getCreditLimit()) && StringUtils.isNotEmpty(getCreditLimit()) && getExpressionCheck("^[0-9.,]+$", getCreditLimit())&&
					StringUtils.isNotBlank( getDebitLimit()) && StringUtils.isNotEmpty(getDebitLimit()) && getExpressionCheck("^[0-9.,]+$", getDebitLimit())
							&& StringUtils.isNotBlank( getAccountype()) && StringUtils.isNotEmpty(getAccountype()))
			{
				String accountType = AccountTypeEnums.findAccountTypeByName(getAccountype().replaceAll(" ", "_"));
				
			txnRef = pendingAuthorizationService.getTranRef(getAccountNo()+"~"+getSetAsDefault()+"~"+getBranch()+"~"+getDebitLimit()
					+"~"+getCreditLimit()+"~"+accountType,"MMID",userId);
			}
			else
			{
				if(StringUtils.isBlank( getCreditLimit()) && StringUtils.isEmpty(getCreditLimit()))
				{
					
					addFieldError("creditLimit", "Credit Limit Is Required");
				}
				else if(!getExpressionCheck("[0-9]", getCreditLimit()))
				{
					addFieldError("creditLimit", "Credit Limit Should Contain Only Numbers");
				}
				if(StringUtils.isBlank( getDebitLimit()) && StringUtils.isEmpty(getDebitLimit()))
				{
					
					addFieldError("debitLimit", "Debit Limit Is Required");
				}
				else if(!getExpressionCheck("[0-9]", getDebitLimit()))
				{
					addFieldError("debitLimit", "Debit Limit Should Contain Only Numbers");
				}
				if(StringUtils.isBlank( getAccountype()) && StringUtils.isEmpty(getAccountype()))
				{
					
					addFieldError("accountType", "Account Type Is Required");
				}
				if(StringUtils.isBlank( getBranch()) && StringUtils.isEmpty(getBranch()))
				{
					
					addFieldError("branch", "Branch Is Required");
				}
					return "input";	
			}
		}
		int sequence=2;
		 for ( int i = 0; i < select.size(); i++ ) { 
			 AddressDto obj =  selectedList.get(i);
			    if ( select.get(i) != null && select.get(i) ) { 
			    	if(getSaveAction().equals("Approve"))
			        accountMaintenaceService.deleteAddressRef(getAccountNo(),obj.getMobileNo(), obj.getMMID() );
			    	else if(getSaveAction().equals("Save"))
			    		pendingAuthorizationService.delimitedStringValue(txnRef,i+"",obj.getMobileNo()+"~"+ obj.getMMID()+"~D"+"~"+obj.getFlag());
			    	
			    		if((obj.getMMID()+","+obj.getMobileNo()).equals(getSetAsDefault()))
			    		{
			    			count++;
			    		}
			    	}
			    else
				 {
			    	//System.out.println("FOR Insert :----  "+obj.getMobileNo()+" ----- "+ obj.getMMID());
			    	if(getSaveAction().equals("Approve")){
			    		
			    	accountMaintenaceService.update( getAccountNo(),obj.getMobileNo(), obj.getMMID(),obj.getFlag() );
			    	if(!(obj.getMMID()+","+obj.getMobileNo()).equals(getSetAsDefault())){
			    		
			    			accountMaintenaceService.updateSequence((obj.getMMID()+","+obj.getMobileNo()),getBranch(), sequence);
			    			sequence++;
		    			}
		    			else{
		    					accountMaintenaceService.updateSequence((obj.getMMID()+","+obj.getMobileNo()),getBranch(), 1);
		    					
		    				}    		
			    	
			    		}
			    	else if(getSaveAction().equals("Save"))
			    		pendingAuthorizationService.delimitedStringValue(txnRef,i+"",obj.getMobileNo()+"~"+ obj.getMMID()+"~U"+"~"+obj.getFlag());  	
			    	
				 }
		 }
		 
		 if(count==0)
		 {
			 String[] defaultMMID= getSetAsDefault().split(",");
			 if(getSaveAction().equals("Approve")){
			 account.setMMID(defaultMMID[0]);
			 accountMaintenaceService.updateRules(account,limits,getBranch(),AccountTypeEnums.findAccountTypeByName(getAccountype().replaceAll(" ", "_")));			
			 }
			 
			 
		 }
		 else
		 {
			 if(getSaveAction().equals("Approve")){
			 account.setMMID(null);
			 accountMaintenaceService.updateRules(account,limits,getBranch(),AccountTypeEnums.findAccountTypeByName(getAccountype().replaceAll(" ", "_")));			 
			 }
		 }
		 if(getSaveAction().equals("Approve")){
		 pendingAuthorizationService.changeStatus(getSaveAction(), getHiddenTxnRef());
		 setSearchList(accountMaintenaceService.showGridView(getAccountNo(),getBranch()));
			return "successfullyApproved";
		
		 }else if(getSaveAction().equals("Reject"))
		 {
			 pendingAuthorizationService.changeStatus(getSaveAction(), getHiddenTxnRef());
			 return "rejected";
			 
		 }
		 if(getSaveAction().equals("Approve")){
			 auditService.saveEventAuditData(getEventAuditDtoForApprove());
			
			 
		 }else if(getSaveAction().equals("Save")){	    		
			 auditService.saveEventAuditData(getEventAuditDtoForSubmit());
		 }else if(getSaveAction().equals("Reject")){
			 auditService.saveEventAuditData(getEventAuditDtoForReject());
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

	private EventAudit getEventAuditDtoForSubmit(){
		EventAudit eventAudit = getEventAuditDto();
		eventAudit.setAuditEventId("NGPHGUIACM0001");
		String userId = (String)session.get(WebConstants.CONSTANT_USER_NAME);	
		String[] extraInformation = {userId," User Account "};		
		eventAudit.setExtraInformation(extraInformation);
		String eventDescription = "User {0} has Initiated {1}";
		MessageFormat msgFormat = new MessageFormat(eventDescription);		
		eventAudit.setAuditEventDesc(msgFormat.format(extraInformation));
		
		return eventAudit;		
	}
	private EventAudit getEventAuditDtoForApprove(){
		EventAudit eventAudit = getEventAuditDto();
		eventAudit.setAuditEventId("NGPHGUIACM0002");
		String userId = (String)session.get(WebConstants.CONSTANT_USER_NAME);	
		String[] extraInformation = {userId," User Account "};		
		eventAudit.setExtraInformation(extraInformation);
		String eventDescription = "User {0} has Approved {1}";
		MessageFormat msgFormat = new MessageFormat(eventDescription);		
		eventAudit.setAuditEventDesc(msgFormat.format(extraInformation));
		
		return eventAudit;		
	}
	private EventAudit getEventAuditDtoForReject(){
		EventAudit eventAudit = getEventAuditDto();
		eventAudit.setAuditEventId("NGPHGUIACM0003");
		String userId = (String)session.get(WebConstants.CONSTANT_USER_NAME);	
		String[] extraInformation = {userId," User Account "};		
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
	
	
	@SkipValidation
	public String getScreenData() throws NGPHException
	{
		try {
			
			displayAccountMantenance();
		PendingAuthorizationService pendingAuthorizationService = (PendingAuthorizationService)ApplicationContextProvider.getBean("pendingAuthorizationService");
		setCheckForSubmit("Display_Approve_Reject");
		String userId = pendingAuthorizationService.getCreatedUser(getTxnRef());
		String userRole = pendingAuthorizationService.getUserType((String)session.get(WebConstants.CONSTANT_USER_NAME));
		if((((String)session.get(WebConstants.CONSTANT_USER_NAME)).equals(userId) || userRole.equals("T"))){
			setValidUserToApprove(false);
		} else {
			setValidUserToApprove(true);
		}
		String[] tempScreenString =pendingAuthorizationService.getScreenReturn(getTxnRef()).split("~");
		setAccountNo(tempScreenString[0]);
		setSetAsDefault(tempScreenString[1]);
		setBranch(tempScreenString[2]);
		setDebitLimit(tempScreenString[3]);
		setCreditLimit(tempScreenString[4]);
	//	System.out.println((String)tempScreenString[5]);
		if(tempScreenString[5]!=null){
		AccountTypeEnums val = AccountTypeEnums.findEnumByTag(tempScreenString[5].toString().trim());
		//List list = session.get(arg0)
		setAccountype(val.toString().replaceAll("_", " "));
		setHiddenAccountType(val.toString().replaceAll("_", " "));
		}
		setHiddenTxnRef(getTxnRef());
		List mulDataList = pendingAuthorizationService.getMulScreenData(getTxnRef());
		List<AddressDto> temportList = new ArrayList<AddressDto>();
		List<Boolean> checkBoxSelected = new ArrayList<Boolean>();
		String tempCheckbox="";
		
		for(int i=0;i<mulDataList.size();i++)
		{
			Clob list=(Clob) mulDataList.get(i);
			String[] tempString;
			tempString = list.getSubString(1, (int) list.length()).split("~");
			AddressDto addressDto = new AddressDto();
			addressDto.setMMID(tempString[1]);
			addressDto.setMobileNo(tempString[0]);
			addressDto.setFlag(tempString[3]);	
			if(tempString[2].equals("U")){
					checkBoxSelected.add(false);
					tempCheckbox = tempCheckbox+false+",";
				}else{
					checkBoxSelected.add(true);
					tempCheckbox = tempCheckbox+true+",";
		}
			temportList.add(addressDto);
		}
		setSearchList(temportList);
		setSelect(checkBoxSelected);
		setHiddenSize(checkBoxSelected.size()+"");
		if(StringUtils.isNotBlank(tempCheckbox) && StringUtils.isNotEmpty(tempCheckbox))
		setHiddenList(tempCheckbox.substring(0,(tempCheckbox.length()-1)));
		setHiddenRadio(tempScreenString[1]);
		setOld_NewScreen(null);
		//System.out.println(tempScreenString[1]);
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
	public AccountMaintenaceService getApplicationContext()
	{
		AccountMaintenaceService accountMaintenaceService = (AccountMaintenaceService)ApplicationContextProvider.getBean("accountMaintenanceService");
		return accountMaintenaceService;
	}
	
	@SkipValidation
	public String showGrid() throws NGPHException
	{
		try{
			String branch  = getBranch();
		//	System.out.println("getBranch()"+getBranch());
		AccountMaintenaceService accountMaintenaceService = getApplicationContext();
		if(accountMaintenaceService.accountNOAllreadyExit(getAccountNo()).equals("Available") && StringUtils.isNotBlank(getBranch())&&StringUtils.isNotEmpty(getBranch()) ){
			temportList=accountMaintenaceService.showGridView(getAccountNo(),getBranch());
			setSearchList(temportList);
			if(!temportList.isEmpty()){
			setCreditLimit(temportList.get(0).getCreditLimit());
			setDebitLimit(temportList.get(0).getDebitLimit());
			if(temportList.get(0).getAccountType()!=null && !temportList.get(0).getAccountType().equals("")){
			setAccountype(AccountTypeEnums.findEnumByTag((String)temportList.get(0).getAccountType()).toString().replaceAll("_", " "));
			setHiddenAccountType(AccountTypeEnums.findEnumByTag((String)temportList.get(0).getAccountType()).toString().replaceAll("_", " "));
			}
			setBranch(branch);
			
			}
			String mobileNo=null;
			for(int i=0;i<temportList.size();i++)
			{
				AddressDto obj =  temportList.get(i);
				if(obj.getMMID().equals(accountMaintenaceService.setASDefault(getAccountNo())))	{
					mobileNo=obj.getMobileNo();
					
				}
			}
			
			setHiddenRadio(accountMaintenaceService.setASDefault(getAccountNo())+","+mobileNo);
			setHiddenBranch(branch);
			setBranch(branch);
			return "success";
		}
		else{
			if(StringUtils.isBlank(getBranch())&&StringUtils.isEmpty(getBranch()))
			{
				addFieldError(accountNo, "Branch Is Required");
			}else
			addFieldError(accountNo, "Account Number Is Not Avialable In Database");
			return "input";
		}
		
		
		
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
	public void validate(){}
	
	public Boolean getExpressionCheck(String pattern,String toMatch)
	{
		Pattern patternString = Pattern.compile(pattern);
		Matcher matcher  = patternString.matcher(toMatch);
	//	System.out.println(matcher.matches());
		
		return matcher.matches() ;
	}
	@SkipValidation
	public String reset() throws NGPHException
	{
		
		try{
			setAccountNo("");
			setAccountype("");
			setBranch("");
			setCreditLimit("");
			setDebitLimit("");
			setHiddenAccountType("");
			setMMDI("");
			setMobileNo("");
			displayAccountMantenance();
			return "success";
		}catch (Exception e) {
			logger.error(e,e);
			return "input";
		}
	}
	@SkipValidation
	public String callSeeOldData()
	{
		try{
			if(getOld_NewScreen().equals("OLD")){
				showGrid();
				setFlagForNewData("flagForNewData");
				}else if(getOld_NewScreen().equals("NEW"))
				{
					getScreenData();
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
}
