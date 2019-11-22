package com.logica.ngph.web.action;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.SessionAware;

import com.logica.ngph.service.AccountMaintenaceService;
import com.logica.ngph.service.PendingAuthorizationService;
import com.logica.ngph.service.ReturnIMPSTransactionService;
import com.logica.ngph.web.utils.ApplicationContextProvider;
import com.logica.ngph.web.utils.WebConstants;
import com.opensymphony.xwork2.ActionSupport;
import com.logica.ngph.Entity.MsgPolled;
import com.logica.ngph.common.ConstantUtil;
import com.logica.ngph.common.NGPHException;
import com.logica.ngph.dtos.AddressDto;
import com.logica.ngph.dtos.ReturnIMPSTransactionDto;

public class ReturnTransactionAction extends ActionSupport implements SessionAware {
	private static final long serialVersionUID = 1L;
	static Logger logger = Logger.getLogger(ReturnTransactionAction.class);
	private String impsDate;
	private List<ReturnIMPSTransactionDto> displayList;
	private Map<String, Object> session ;
	private List<Boolean>	 select; 
	private String message;
	private String valueDate;
	private String hiddenTxnRef;
	private String txnRef;
	private String saveAction;
	private String checkForSubmit;
	private String hiddenList;
	private String hiddenSize;
	private boolean validUserToApprove;

	public String getHiddenSize() {
		return hiddenSize;
	}
	public void setHiddenSize(String hiddenSize) {
		this.hiddenSize = hiddenSize;
	}
	public String getHiddenList() {
		return hiddenList;
	}
	public void setHiddenList(String hiddenList) {
		this.hiddenList = hiddenList;
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
	public String getTxnRef() {
		return txnRef;
	}
	public void setTxnRef(String txnRef) {
		this.txnRef = txnRef;
	}
	public String getHiddenTxnRef() {
		return hiddenTxnRef;
	}
	public void setHiddenTxnRef(String hiddenTxnRef) {
		this.hiddenTxnRef = hiddenTxnRef;
	}
	public String getValueDate() {
		return valueDate;
	}
	public void setValueDate(String valueDate) {
		this.valueDate = valueDate;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public List<Boolean> getSelect() {
		return select;
	}
	public void setSelect(List<Boolean> select) {
		this.select = select;
	}
		
	public String getImpsDate() {
		return impsDate;
	}

	public void setImpsDate(String impsDate) {
		this.impsDate = impsDate;
	}
	public void setDisplayList(List<ReturnIMPSTransactionDto> displayList) {
		this.displayList = displayList;
		this.session.put("displayList",displayList);
	}

	public Map<String, Object> getSession() {
		return session;
	}
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	public String displayReturnTransaction()
	{
		setImpsDate("");
		this.session.remove("displayList");
		return "success";
	}
	
	public String getFetchData() throws SQLException, ParseException
	{
		
		if(!getImpsDate().isEmpty()){
		ReturnIMPSTransactionService impsTransactionService = (ReturnIMPSTransactionService) ApplicationContextProvider.getBean("returnIMPSTransactionService");
		String date= dateChanger(getImpsDate());
		setDisplayList(impsTransactionService.getReturnDetails(date));
		setValueDate(date);
		}
		else
			addFieldError("impsDate", "Date Field Is Required");
		return "success";
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String doOperation() throws SQLException, ParseException
	{
		
		String returnString ="success";
		String getTransRef="";
		System.out.println(getSaveAction());
		String getdelimtedString="";
		System.out.println("select.isEmpty(): -" + select!=null);
		if(select!=null ){
		ReturnIMPSTransactionService impsTransactionService = (ReturnIMPSTransactionService) ApplicationContextProvider.getBean("returnIMPSTransactionService");
		PendingAuthorizationService pendingAuthorizationService = (PendingAuthorizationService)ApplicationContextProvider.getBean("pendingAuthorizationService");
		
		
		if(getSaveAction().equals("Save"))
		{
			setDisplayList(impsTransactionService.getReturnDetails(getValueDate()));
			getdelimtedString +=getValueDate();
		
		}
		
		//Iterator entries1 = session.entrySet().iterator();
		List<ReturnIMPSTransactionDto> selectedList = new ArrayList<ReturnIMPSTransactionDto>();
		selectedList= (List)session.get("displayList");
		/*while (entries1.hasNext())
		{
			Map.Entry entry = (Map.Entry) entries1.next();
			Object key = (Object)entry.getKey();
			if(key.equals("displayList")){
				selectedList = (List) entry.getValue();
			}
		}*/
		int count = 0;
		for(int i = 0; i < select.size(); i++ )
		{
			if(select.get(i))
			{
				count=1;
				break;
			}
		}
		if(count==0)
		{
			
			addFieldError("impsDate", "User Has Not Selected Anything ");
			return "input";
		}
		 for ( int i = 0; i < select.size(); i++ ) { 
			 ReturnIMPSTransactionDto obj =  selectedList.get(i);
			 if(getSaveAction().equals("Save")){
				 getdelimtedString=getdelimtedString+ConstantUtil.delimiter+select.get(i);
			 }else if(getSaveAction().equals("Approve")){
				 
				 
			    if ( select.get(i) != null && select.get(i) ) { 
			    		System.out.println(obj.getMsgRef());
			    		MsgPolled msgPolled = new MsgPolled();
			    		msgPolled.setInTime(new Timestamp(Calendar.getInstance().getTime().getTime()));
			    		msgPolled.setMsgRef(obj.getMsgRef());
			    		msgPolled.setPolledStatus("P");
			    		msgPolled.setPolledReason("R");
			    		impsTransactionService.savePolled(msgPolled);
			    	}
			 }
			   
		 	}
		 if(getSaveAction().equals("Approve"))
			 pendingAuthorizationService.changeStatus(getSaveAction(), getHiddenTxnRef());
		 if(getSaveAction().equals("Save")){
			 String userId = (String)session.get(WebConstants.CONSTANT_USER_NAME);
			 getTransRef= pendingAuthorizationService.getTranRef(getdelimtedString,"IMPS Returns",userId);
			 return "sendForApproval";
		 }
		 if(getSaveAction().equals("Reject"))
			 pendingAuthorizationService.changeStatus(getSaveAction(), getHiddenTxnRef());
			
		 setMessage("Operation Successfull");
		
		}
		else
		addFieldError("impsDate", "Nothing Found To Do The Operation");
		return returnString;
	}
	
	
	
	public List<ReturnIMPSTransactionDto> getDisplayList() {
		return displayList;
	}
	
	 public String getAuthorization() throws NGPHException, SQLException, ParseException
		{
	    	
		 ReturnIMPSTransactionService impsTransactionService = (ReturnIMPSTransactionService) ApplicationContextProvider.getBean("returnIMPSTransactionService");
			PendingAuthorizationService pendingAuthorizationService = (PendingAuthorizationService)ApplicationContextProvider.getBean("pendingAuthorizationService");
			
			String requiredString =pendingAuthorizationService.getScreenReturn(getTxnRef());
			String[] stringBreaker = requiredString.split("~");
			System.out.println(stringBreaker[0]);
			setDisplayList(impsTransactionService.getReturnDetails(stringBreaker[0]));
			setHiddenTxnRef(getTxnRef());
			setCheckForSubmit("Display_Approve_Reject");
			String userId = pendingAuthorizationService.getCreatedUser(getTxnRef());
			String userRole = pendingAuthorizationService.getUserType((String)session.get(WebConstants.CONSTANT_USER_NAME));
			if((((String)session.get(WebConstants.CONSTANT_USER_NAME)).equals(userId) || userRole.equals("T"))){
				setValidUserToApprove(false);
			} else {
				setValidUserToApprove(true);
			}
			setImpsDate(stringBreaker[0]);
			System.out.println(requiredString.replaceAll(stringBreaker[0]+ConstantUtil.delimiter, ""));
			setHiddenList(requiredString.replaceAll(stringBreaker[0]+ConstantUtil.delimiter, ""));
			System.out.println(requiredString.replaceAll(stringBreaker[0]+ConstantUtil.delimiter, "").split("~").length);
		return "success";
		}
	

	/**
	* This method is used to Change The Date In Specific Format
	* it accept one argument which is date in string fromat
	* it will break string into day month and year
	* @return Converted Date
	*/
	public String dateChanger(String aNewValue) throws ParseException{
		String date = aNewValue.substring(0, 6);
	       String currentDate = date;
	       DateFormat sdf = new SimpleDateFormat("yymmdd");
	       Date dt = sdf.parse(currentDate);
	       sdf = new SimpleDateFormat("yyyymmdd");
	       String formattedDate = sdf.format(dt);
	       
	       String yy = aNewValue.substring(0, 4);
	       System.out.println(yy);
	       String mm = aNewValue.substring(5, 7);
	       System.out.println(mm);
	       String dd = aNewValue.substring(8, 10);
	       System.out.println(dd);
	       System.out.println(yy+"/"+mm+"/"+dd );
	       String dateconverted=dd+"/"+mm+"/"+yy;
		return dateconverted;
		
	}
	
	
public void validate()
{
}
public boolean getValidUserToApprove() {
	return validUserToApprove;
}
public void setValidUserToApprove(boolean validUserToApprove) {
	this.validUserToApprove = validUserToApprove;
}
}
