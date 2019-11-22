package com.logica.ngph.web.action;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.SessionAware;

import com.logica.ngph.service.AccountMaintenaceService;
import com.logica.ngph.service.ReturnIMPSTransactionService;
import com.logica.ngph.web.utils.ApplicationContextProvider;
import com.opensymphony.xwork2.ActionSupport;
import com.logica.ngph.Entity.MsgPolled;
import com.logica.ngph.dtos.AddressDto;
import com.logica.ngph.dtos.ReturnIMPSTransactionDto;

public class ReturnTransaction extends ActionSupport implements SessionAware {
	private static final long serialVersionUID = 1L;
	static Logger logger = Logger.getLogger(ReturnTransaction.class);
	private String impsDate;
	private List<ReturnIMPSTransactionDto> displayList;
	private Map<String, Object> session ;
	private List<Boolean>	 select; 
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
		this.session.remove("displayList");
		return "success";
	}
	
	public String getFetchData() throws SQLException, ParseException
	{
		
		if(!impsDate.isEmpty()){
		ReturnIMPSTransactionService impsTransactionService = (ReturnIMPSTransactionService) ApplicationContextProvider.getBean("returnIMPSTransactionService");
		setDisplayList(impsTransactionService.getReturnDetails(dateChanger(impsDate)));
		}
		else
			addFieldError("impsDate", "Date Field Is Required");
		return "success";
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String doOperation() throws SQLException
	{
		System.out.println("select.isEmpty(): -" + select!=null);
		if(select!=null ){
		ReturnIMPSTransactionService impsTransactionService = (ReturnIMPSTransactionService) ApplicationContextProvider.getBean("returnIMPSTransactionService");
		Iterator entries1 = session.entrySet().iterator();
		List<ReturnIMPSTransactionDto> selectedList = new ArrayList<ReturnIMPSTransactionDto>();
		
		while (entries1.hasNext())
		{
			Map.Entry entry = (Map.Entry) entries1.next();
			Object key = (Object)entry.getKey();
			if(key.equals("displayList")){
				selectedList = (List) entry.getValue();
			}
		}
		
		 for ( int i = 0; i < select.size(); i++ ) { 
			 ReturnIMPSTransactionDto obj =  selectedList.get(i);
			    if ( select.get(i) != null && select.get(i) ) { 
			    		System.out.println(obj.getMsgRef());
			    		MsgPolled msgPolled = new MsgPolled();
			    		msgPolled.setInTime(new Timestamp(new java.util.Date().getTime()));
			    		msgPolled.setMsgRef(obj.getMsgRef());
			    		msgPolled.setPolledStatus("P");
			    		impsTransactionService.savePolled(msgPolled);
			    	}
		 	}
		 
		
		}
		else
		addFieldError("impsDate", "Nothing Found To Do The Operation");
		return "success";
	}
	
	
	
	public List<ReturnIMPSTransactionDto> getDisplayList() {
		return displayList;
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
}
