package com.logica.ngph.web.action;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.context.ApplicationContextException;

import com.logica.ngph.common.ConstantUtil;
import com.logica.ngph.dtos.EventAuditDto;
import com.logica.ngph.dtos.PaymentMessage;
import com.logica.ngph.service.EventAuditService;
import com.logica.ngph.web.utils.ApplicationContextProvider;
import com.logica.ngph.web.utils.WebConstants;
import com.opensymphony.xwork2.ActionSupport;
/**
 * @author guptast
 */
public class EventAuditAction extends ActionSupport implements SessionAware {

	
	public Map<String, Object> getSession() {
		return session;
	}
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
	private static final long serialVersionUID = 1L;
	private static final String EventAuditDto = null;
	private static org.apache.log4j.Logger logger= org.apache.log4j.Logger.getLogger(EventAuditAction.class);
	private String eventBranch;
	private String eventDepartment;
	private String eventTxnRef;
	private Date eventFromDate;
	private Date eventToDate;
	private String formTime;
	private String toTime;
	private String eventMsgRef;
	private String eventDesc;
	private String msgRef;
	private String msgType;
	private String msgChannel;
	private List branchList = new ArrayList();
	private List departmentList = new ArrayList();
	private List txnList= new ArrayList();
	private String eventID;
	private String currHist;
	
	public String getCurrHist() {
		return currHist;
	}
	public void setCurrHist(String currHist) {
		this.currHist = currHist;
	}
	public String getEventID() {
		return eventID;
	}
	public void setEventID(String eventID) {
		this.eventID = eventID;
	}
	public List getBranchList() {
		return branchList;
	}
	public void setBranchList(List branchList) {
		this.branchList = branchList;
		this.session.put("branchList", branchList);
	}
	public List getDepartmentList() {
		return departmentList;
	}
	public void setDepartmentList(List departmentList) {
		this.departmentList = departmentList;
		this.session.put("departmentList", departmentList);
	}
	public List getTxnList() {
		return txnList;
	}
	public void setTxnList(List txnList) {
		this.txnList = txnList;
		this.session.put("txnList", txnList);
	}
	private String paymentStatus;
	private String msgDirection;
	private String txnReference;
	private String senderBank;
	private String receiverBank;
	private String msgCurrency;
	private BigDecimal msgAmount;
	private String msgValueDate;
	private String orderingCustomer;
	private String beneficiaryCustomer;
	private String narration;
	
	
	List<EventAuditDto> searchList;
	private Map<String, Object> session ;
	public List<EventAuditDto> getSearchList() {
		return searchList;
	}
	public void setSearchList(List<EventAuditDto> searchList) {
		this.searchList = searchList;
		this.session.put("searchList", searchList);
	}
	public String eventDisplay(){
		try{
		EventAuditService auditService = 	(EventAuditService)ApplicationContextProvider.getBean("eventAuditService");
		//setBranchList(auditService.getBranchList());
		setDepartmentList(auditService.getDepartmentList());
		String userId = (String)session.get(WebConstants.CONSTANT_USER_NAME);
		setEventBranch(auditService.getUserBranch(userId).getUserBranch());
		auditService.getUserBranch(userId);
		setTxnList(auditService.getTxnList());
		session.remove("searchList");
		setEventFromDate(getDateTime());
		setEventToDate(getDateTime());
		setCurrHist("C");
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
			return "input";
	}
	public String getEventSearch()
	{
		try{
			
			List queryCreator = new ArrayList();
			if(StringUtils.isNotEmpty(getEventBranch()) && StringUtils.isNotBlank(getEventBranch()) && !getEventBranch().equals(""))
			{
				if(!getEventBranch().equals(ConstantUtil.ALL)){
					queryCreator.add("auditBranch like '"+getEventBranch()+"'");
				}
			}
			if(StringUtils.isNotEmpty(getEventDepartment())&& StringUtils.isNotBlank(getEventDepartment()) && !getEventDepartment().equals(""))
			{
				queryCreator.add("auditDept like '"+getEventDepartment()+"'");
			}
			if(StringUtils.isNotBlank(getEventTxnRef()) && StringUtils.isNotEmpty(getEventTxnRef()))
			{
				queryCreator.add("auditTransaction like '"+getEventTxnRef()+"%'");
			}
			if(StringUtils.isNotBlank(getEventID()) && StringUtils.isNotEmpty(getEventID()) && !getEventID().equals(""))
			{
				queryCreator.add("auditEventId like '"+getEventID()+"'");
			}
		/*	if(StringUtils.isNotBlank(getEventFromDate())&& StringUtils.isNotEmpty(getEventFromDate())){
				if(StringUtils.isNotBlank(getEventToDate()) && StringUtils.isNotEmpty(getEventToDate()))
				{
					String date1=dateChanger(getEventFromDate());
					String date2=dateChanger(getEventToDate());
					String valid=CompareDate(date1,date2);
					if(valid.equals("true") || date1.equals(date2)){
						if(StringUtils.isBlank(getFormTime())&& StringUtils.isBlank(getToTime()))
					queryCreator.add("auditEventTime between (to_date('"+date1+" 00:00:00' , 'dd/MM/yyyy HH24:MI:SS')) AND (to_date('"+date2+" "+getTime()+"' , 'dd/MM/yyyy HH24:MI:SS'))");
						else
							queryCreator.add("auditEventTime between (to_date('"+date1+" "+getFormTime().substring(11, 19)+"' , 'dd/MM/yyyy HH24:MI:SS')) AND (to_date('"+date2+" "+getToTime().substring(11, 19)+"' , 'dd/MM/yyyy HH24:MI:SS'))");
							
					}else
						return "input";
				}
				else{
					addFieldError("eventToDate","TO date is required");
					return "input";
				}
			}*/
			
			if(getEventFromDate()!=null){
				if(getEventToDate()!=null){
					Date date1 = getEventFromDate();
					SimpleDateFormat sdf;
					sdf = new SimpleDateFormat("dd-MMM-yy");
					Date date2 = getEventToDate();
					String valid=CompareDate(date1,date2);
					if(valid.equals("true") || date1.equals(date2)){
						if(StringUtils.isBlank(getFormTime())&& StringUtils.isBlank(getToTime()))
					queryCreator.add("auditEventTime between (to_date('"+sdf.format(date1)+" 00:00:00' , 'dd-MON-yy hh24:mi:ss')) AND (to_date('"+sdf.format(date2)+" 23:59:59' , 'dd-MON-yy hh24:mi:ss'))");
						else
							queryCreator.add("auditEventTime between (to_date('"+sdf.format(date1)+" "+getFormTime().substring(11, 19)+"' , 'dd-MON-yy hh24:mi:ss')) AND (to_date('"+sdf.format(date2)+" "+getToTime().substring(11, 19)+"' , 'dd-MON-yy hh24:mi:ss'))");
							
					}else
						return "input";
				}else{
					addFieldError("eventToDate","TO date is required");
					return "input";
				}
			}
			else{
				if(StringUtils.isNotBlank(getFormTime())|| StringUtils.isNotBlank(getToTime()))
				{
					addFieldError("formTime", "Please Select From date And To date");
					return "input";
					
				}
			}
				
			
		String query="";
		if(queryCreator.size()!=0)
		{
			query = "where ";
		}
		for(int i=0;i<queryCreator.size();i++)
		{
			if(i!=queryCreator.size()-1)
			query = query+queryCreator.get(i)+" AND ";
			else
				query=query+queryCreator.get(i);
			
		}
			
		EventAuditService auditService = 	(EventAuditService)ApplicationContextProvider.getBean("eventAuditService");
		setSearchList(auditService.getSearchList(query,getCurrHist()));
		
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
			return "input";
	}
	
	public String ViewDetails()
	{
		try{
			System.out.println("getMsgRef()  : --- "+getMsgRef());
			
			EventAuditService auditService = 	(EventAuditService)ApplicationContextProvider.getBean("eventAuditService");
			PaymentMessage obj=  auditService.getObject(getMsgRef());
			if(obj!=null){
				
					setBeneficiaryCustomer(obj.getBeneficiaryCustomer());
					setMsgAmount(obj.getMsgAmount());
					setMsgChannel(obj.getMsgChannel());
					setMsgCurrency(obj.getMsgCurrency());
					setMsgDirection(obj.getMsgDirection());
					setMsgType(obj.getMsgType());
					setMsgValueDate(obj.getMsgValueDate());
					setNarration(obj.getNarration());
					setOrderingCustomer(obj.getOrderingCustomer());
					setPaymentStatus(obj.getPaymentStatus());
					setReceiverBank(obj.getReceiverBank());
					setSenderBank(obj.getSenderBank());
					setTxnReference(obj.getTxnReference());
					
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
		addFieldError("eventTxnRef", getMsgRef() +" No Payment View For This Event");
		return "input";
	}
	
/*	private  final static String getDateTime()  
    {  
        DateFormat df = new SimpleDateFormat("MM/dd/yy");  
        df.setTimeZone(TimeZone.getTimeZone("GMT"));  
        return df.format(new Date());  
    } */
	private  final static Date getDateTime()  
    {  
		try{
        DateFormat df = new SimpleDateFormat("MM/dd/yy");  
        df.setTimeZone(java.util.TimeZone.getDefault());  
        System.out.println("Default System Time Zone : - "+java.util.TimeZone.getDefault());
        Date d = Calendar.getInstance().getTime();
        Date d2 = new Date();
        int dd = d.getDate();
        int mm = d.getMonth()+1;
        int yy = d.getYear();
              
        return d;
		}catch(Exception e){
			 System.out.println("Exception :"+e); 
		}
		return null;
    }
	
	private final static String getTime()
	{
		Calendar calendar = new GregorianCalendar();
		  String am_pm;
		  int hour = calendar.get(Calendar.HOUR_OF_DAY);
		  int minute = calendar.get(Calendar.MINUTE);
		  int second = calendar.get(Calendar.SECOND);
		  return hour+":"+minute+":"+second;
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
	/*This Method compare the two date and return true and false 
	 * so that it will do validation it accept to argument formdate 
	 * and todate.
	 * returns true when the from date is smaller then to date
	 * returns false when the todate is smaller then from date and 
	 * Do validation accordingly
	 */
	public String CompareDate(Date str_date1,Date str_date2) {
		 
		String valid="";
		try {  
			 DateFormat formatter ; 
			 Date date ; 
			 Date date2 ; 
			  //formatter = new SimpleDateFormat("dd/MM/yy");
			  date = str_date1;  
			  date2 = str_date2;
			 System.out.println("Today is " +date );
		 
		 
		 if(date.before(date2))
		 {
			 System.out.println("DATE ONE IS Smaller :----"+str_date1+" DATE 2 "+str_date2);
			 valid="true";
			 
		 }
		 else if(date.after(date2))
		 {
			 System.out.println("DATE ONE IS GREATER :---"+str_date1+" DATE 2 "+str_date2);
			 valid="false";
			 addFieldError("enquiryTODate", "TO date Must Be Greater Than From Date ");
			
		 }
		  } catch (Exception e)
		  {
			  System.out.println("Exception :"+e); 
			  }  
		 return valid;
		 }
	
	public String getMsgRef() {
		return msgRef;
	}
	public void setMsgRef(String msgRef) {
		this.msgRef = msgRef;
	}
	public String getEventMsgRef() {
		return eventMsgRef;
	}
	public void setEventMsgRef(String eventMsgRef) {
		this.eventMsgRef = eventMsgRef;
	}
	public String getEventDesc() {
		return eventDesc;
	}
	public void setEventDesc(String eventDesc) {
		this.eventDesc = eventDesc;
	}
		
	public String getEventBranch() {
		return eventBranch;
	}
	public void setEventBranch(String eventBranch) {
		this.eventBranch = eventBranch;
	}
	public String getEventDepartment() {
		return eventDepartment;
	}
	public void setEventDepartment(String eventDepartment) {
		this.eventDepartment = eventDepartment;
	}
	public String getEventTxnRef() {
		return eventTxnRef;
	}
	public void setEventTxnRef(String eventTxnRef) {
		this.eventTxnRef = eventTxnRef;
	}
	public Date getEventFromDate() {
		return eventFromDate;
	}
	public void setEventFromDate(Date eventFromDate) {
		this.eventFromDate = eventFromDate;
	}
	public Date getEventToDate() {
		return eventToDate;
	}
	public void setEventToDate(Date eventToDate) {
		this.eventToDate = eventToDate;
	}
	public String getFormTime() {
		return formTime;
	}
	public void setFormTime(String formTime) {
		this.formTime = formTime;
	}
	public String getToTime() {
		return toTime;
	}
	public void setToTime(String toTime) {
		this.toTime = toTime;
	}
	
	
	
	public void validate(){
		
		
	}
	public String getMsgType() {
		return msgType;
	}
	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}
	public String getMsgChannel() {
		return msgChannel;
	}
	public void setMsgChannel(String msgChannel) {
		this.msgChannel = msgChannel;
	}
	public String getPaymentStatus() {
		return paymentStatus;
	}
	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}
	public String getMsgDirection() {
		return msgDirection;
	}
	public void setMsgDirection(String msgDirection) {
		this.msgDirection = msgDirection;
	}
	public String getTxnReference() {
		return txnReference;
	}
	public void setTxnReference(String txnReference) {
		this.txnReference = txnReference;
	}
	public String getSenderBank() {
		return senderBank;
	}
	public void setSenderBank(String senderBank) {
		this.senderBank = senderBank;
	}
	public String getReceiverBank() {
		return receiverBank;
	}
	public void setReceiverBank(String receiverBank) {
		this.receiverBank = receiverBank;
	}
	public String getMsgCurrency() {
		return msgCurrency;
	}
	public void setMsgCurrency(String msgCurrency) {
		this.msgCurrency = msgCurrency;
	}
	public BigDecimal getMsgAmount() {
		return msgAmount;
	}
	public void setMsgAmount(BigDecimal msgAmount) {
		this.msgAmount = msgAmount;
	}
	public String getMsgValueDate() {
		return msgValueDate;
	}
	public void setMsgValueDate(String msgValueDate) {
		this.msgValueDate = msgValueDate;
	}
	public String getOrderingCustomer() {
		return orderingCustomer;
	}
	public void setOrderingCustomer(String orderingCustomer) {
		this.orderingCustomer = orderingCustomer;
	}
	public String getBeneficiaryCustomer() {
		return beneficiaryCustomer;
	}
	public void setBeneficiaryCustomer(String beneficiaryCustomer) {
		this.beneficiaryCustomer = beneficiaryCustomer;
	}
	public String getNarration() {
		return narration;
	}
	public void setNarration(String narration) {
		this.narration = narration;
	}
	

}
