package com.logica.ngph.web.action;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.springframework.context.ApplicationContextException;

import com.logica.ngph.common.ConstantUtil;
import com.logica.ngph.common.PaymentStatusEnum;
import com.logica.ngph.dtos.ReportDto;
import com.logica.ngph.service.PaymentSubmittedReportService;
import com.logica.ngph.serviceImpl.GenerateReportServiceImpl;
import com.logica.ngph.web.utils.ApplicationContextProvider;
import com.logica.ngph.web.utils.ReportGenerationUtil;
import com.opensymphony.xwork2.ActionSupport;

public class PaymentSubmittedReportAction extends ActionSupport implements
		SessionAware, ServletRequestAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static Logger logger = Logger.getLogger(PaymentSubmittedReportAction.class);
	private HttpServletRequest servletRequest;

	private String SenderReceiver;
	private String HostName;
	private String MessageType;
	private String QueueStatus;
	private String receiver;
	private String orderingCustomer;
	private String[] HostNameList;
	private String[] messageList;
	private Date ValueFromDate;
	private Date ValueToDate;
	private BigDecimal fromAmount;
	private BigDecimal toAmount;
	private String[] statusList;
	public String[] getStatusList() {
		return statusList;
	}

	public void setStatusList(String[] statusList) {
		this.statusList = statusList;
		session.put("statusList", statusList);
	}



	private List<String> messageTypeDropDownList;
	private List<String> hostNameDropDownList;
	private List<String> hostIdDropDowmList;
	// private List<String> ColumnToAdd;
	private String[] queue;
	private List<String> groupby = new ArrayList<String>();
	private List<String> outwardQueue = new ArrayList<String>();
	private List<String> coloumToAdd = new ArrayList<String>();
	private Map<String, Object> session;
	private String reportType;
	private String hiddenDirection;
	

	public String getHiddenDirection() {
		return hiddenDirection;
	}

	public void setHiddenDirection(String hiddenDirection) {
		this.hiddenDirection = hiddenDirection;
	}

	public String getSenderReceiver() {
		return SenderReceiver;
	}

	public void setSenderReceiver(String senderReceiver) {
		SenderReceiver = senderReceiver;
	}

	public String getHostName() {
		return HostName;
	}

	public void setHostName(String hostName) {
		HostName = hostName;
	}

	public String getMessageType() {
		return MessageType;
	}

	public void setMessageType(String messageType) {
		MessageType = messageType;
	}

	public String getQueueStatus() {
		return QueueStatus;
	}

	public void setQueueStatus(String queueStatus) {
		QueueStatus = queueStatus;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReciver(String receiver) {
		this.receiver = receiver;
	}

	public String getOrderingCustomer() {
		return orderingCustomer;
	}

	public void setOrderingCustomer(String orderingCustomer) {
		this.orderingCustomer = orderingCustomer;
	}

	public String[] getHostNameList() {
		return HostNameList;
	}

	public void setHostNameList(String[] hostNameList) {
		HostNameList = hostNameList;
	}

	public String[] getMessageList() {
		return messageList;
	}

	public void setMessageList(String[] messageList) {
		this.messageList = messageList;
	}

	public Date getValueFromDate() {
		return ValueFromDate;
	}

	public void setValueFromDate(Date valueFromDate) {
		ValueFromDate = valueFromDate;
	}

	public Date getValueToDate() {
		return ValueToDate;
	}

	public void setValueToDate(Date valueToDate) {
		ValueToDate = valueToDate;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public BigDecimal getFromAmount() {
		return fromAmount;
	}

	public void setFromAmount(BigDecimal fromAmount) {
		this.fromAmount = fromAmount;
	}

	public BigDecimal getToAmount() {
		return toAmount;
	}

	public void setToAmount(BigDecimal toAmount) {
		this.toAmount = toAmount;
	}

	

	/*
	 * public List<String> getColumnToAdd() { return ColumnToAdd; } public void
	 * setColumnToAdd(List<String> columnToAdd) { ColumnToAdd = columnToAdd; }
	 */
	public List<String> getMessageTypeDropDownList() {
		return messageTypeDropDownList;
	}

	public void setMessageTypeDropDownList(List<String> messageTypeDropDownList) {
		this.messageTypeDropDownList = messageTypeDropDownList;
		this.session.put("messageTypeDropDownList", messageTypeDropDownList);
	}

	public List<String> getHostNameDropDownList() {
		return hostNameDropDownList;
	}

	public void setHostNameDropDownList(List<String> hostNameDropDownList) {
		this.hostNameDropDownList = hostNameDropDownList;
		this.session.put("hostNameDropDownList", hostNameDropDownList);
	}

	public List<String> getHostIdDropDowmList() {
		return hostIdDropDowmList;
	}

	public void setHostIdDropDowmList(List<String> hostIdDropDowmList) {
		this.hostIdDropDowmList = hostIdDropDowmList;
		this.session.put("hostIdDropDowmList", hostIdDropDowmList);
	}
	public Map<String, Object> getSession() {
		return session;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
	public String[] getQueue() {
		return queue;
	}

	public void setQueue(String[] queue) {
		this.queue = queue;
	}

	

	public List<String> getGroupby() {
		return groupby;
	}

	public void setGroupby(List<String> groupby) {
		this.groupby = groupby;
	}
	

	public List<String> getOutwardQueue() {
		return outwardQueue;
	}

	public void setOutwardQueue(List<String> outwardQueue) {
		this.outwardQueue = outwardQueue;
		

	}
	
	

	public void setColoumToAdd(List<String> coloumToAdd) {
		this.coloumToAdd = coloumToAdd;
	}

	public List<String> getColoumToAdd() {
		return coloumToAdd;
	}

	public String loadDataOutward() {
		
		this.session.remove("hostNameDropDownList");
		this.session.remove("hostIdDropDowmList");
		displayContent("H");
		session.put("reportType", "H");
		String[] paymentlist = getText("PAYMENTSTATUSOUTBOUND").split(",");
		setStatusList(paymentlist);
		setValueFromDate(getDateTime());
		setValueToDate(getDateTime());
		/*queue = ConstantUtil.OUTWARDQUEUE.split(",");
		
		setQueue(queue);*/
		return "success";

	}
	private  final static Date getDateTime()  
	{  
		String updateDate="";
		try{
	    DateFormat df = new SimpleDateFormat("MM/dd/yy");  
	    df.setTimeZone(java.util.TimeZone.getDefault());  
	    Date d = Calendar.getInstance().getTime();
	    Date d2 = new Date();
	    int dd = d.getDate();
	    int mm = d.getMonth()+1;
	    int yy = d.getYear();
	    
	    
	    String dt = mm +"/"+dd+"/"+yy;
	    Date d1 = df.parse(dt);
	    
	    updateDate = df.format(d1);
	    return d;
		}catch(ParseException e){
			 System.out.println("Exception :"+e); 
		}
		return null;
	}
 
	public String loadDataInward()

	{
		this.session.remove("hostNameDropDownList");
		this.session.remove("hostIdDropDowmList");
		String[] paymentlist = getText("PAYMENTSTATUSINBOUND").split(",");
		setStatusList(paymentlist);
		displayContent("P");
		setValueFromDate(getDateTime());
		setValueToDate(getDateTime());
		session.put("reportType", "P");
		/*queue = ConstantUtil.INWARDQUEUE.split(",");
		
		setQueue(queue);*/
		return "success";

	}

	public void displayContent(String EIType) {
		try {
			
			for (Entry entry : session.entrySet()) {
		        if ("Required".equals(entry.getValue())) {
		        	session.remove(entry.getKey());

		        }
		    }

			PaymentSubmittedReportService paymentSubmittedReportService = getPaymentSubmittedReportService();
			setSenderReceiver("N/A");
			setMessageTypeDropDownList(paymentSubmittedReportService.getList(ConstantUtil.MESSAGE_TYPE));
			if(EIType=="H"){
			setHostNameDropDownList(paymentSubmittedReportService.getList(ConstantUtil.HOSTNAME_H));

			setHostIdDropDowmList(paymentSubmittedReportService.getList(ConstantUtil.HOSTID_H));
			}
			else if(EIType=="P")
			{
				setHostNameDropDownList(paymentSubmittedReportService
						.getList(ConstantUtil.HOSTNAME_P));

				setHostIdDropDowmList(paymentSubmittedReportService
						.getList(ConstantUtil.HOSTID_P));
				}
			
		}
			

		

		catch (Exception exception) {
			logger.debug(exception);
		}
	}

	

	private List<ReportDto> ReportList;
	public List<ReportDto> getReportList() {
		return ReportList;
	}

	public void setReportList(List<ReportDto> reportList) {
		ReportList = reportList;
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

	GenerateReportServiceImpl generateReportService;
	@SuppressWarnings("unchecked")
	public String generateReport() {
		
		try{
			String forward = "success";
		if (getColoumToAdd().size()< 1) {
			addFieldError("coloumToAdd", "Please Select Atleast One Column From Columns To Display");
			//setValueFromDate(null);
			
			//setValueToDate(null);
			if(getHiddenDirection().equalsIgnoreCase("I"))
			return "inputInward";
			else
				return "input";
		}
		generateReportService = (GenerateReportServiceImpl)ApplicationContextProvider.getBean("generateReportService");

		String createQuery ="where direction ='"+getHiddenDirection()+"' AND ";

		ArrayList<String> obj = null;

		java.util.Iterator<Entry<String, Object>> itr = session.entrySet().iterator();

		@SuppressWarnings("rawtypes")
		Map.Entry entry = (Map.Entry) itr.next();
		@SuppressWarnings("unused")
		String key = entry.getKey().toString();
		obj = (ArrayList<String>) session.get("hostNameDropDownList");
		ArrayList<String> matchedValues = (ArrayList<String>) session
				.get("hostIdDropDowmList");
		String hostList = "";
		
		
		
		
		
		
		
		if (HostNameList.length > 0) {

			int count = 0;
			for (int i = 0; i < obj.size(); i++) {
				for(int j=0;j<HostNameList.length;j++)
				
					//System.out.println(" compare result :- "+obj.get(i).equals(HostNameList[j]));
					if (obj.get(i).equals(HostNameList[j])) {
						count = i;
						hostList = hostList + "hostID ='"
								+ matchedValues.get(count) + "' or ";

					}
				

			}
			if(hostList.length()>2)
			hostList = hostList.substring(0, hostList.length() - 3) + ")";

			/*System.out.println(obj + "***************match*****"
					+ matchedValues);*/
		}
		//System.out.println(hostList + " hostList ");

		String messagetypelist = "";
		if (messageList.length > 0) {
			for (int i = 0; i < messageList.length; i++) {
				messagetypelist = messagetypelist + "msgType ='"
						+ messageList[i] + "' or ";

			}
			messagetypelist = messagetypelist.substring(0,
					messagetypelist.length() - 3)
					+ ")";
		}
		if (!org.apache.commons.lang.StringUtils.isEmpty(getReceiver())) {
			createQuery = createQuery + " receiverBank ='" + getReceiver()
					+ "' and ";
		}
		if (getValueFromDate()!=null && getValueToDate()!=null) {

			try {
				
				SimpleDateFormat sdf;
				sdf = new SimpleDateFormat("dd-MMM-yy");
//				
				Date date1 = getValueFromDate();
				Date date2 = getValueToDate();
				String valid = CompareDate(date1, date2);
				//System.out.println(getValueFromDate());
				createQuery = createQuery + " receivedTime between (to_date('"+sdf.format(date1)+" 00:00:00' , 'dd-MON-yy hh24:mi:ss')) AND (to_date('"+sdf.format(date2)+" 23:59:59' , 'dd-MON-yy hh24:mi:ss')) AND ";
					setValueFromDate(date1);
					setValueToDate(date2);
				if(valid.equals("false"))
					forward="input";
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		try {
			if ((getFromAmount()!=null)&& (getToAmount()!=null))
					{
				if (Double.parseDouble(getFromAmount().toString()) < Double
						.parseDouble(getToAmount().toString()))
					createQuery = createQuery + " amount between '"
							+ getFromAmount() + "' and '" + getToAmount()
							+ "' and ";
					
				
			}
			else
			{
				setFromAmount(new BigDecimal(0));
				setToAmount(new BigDecimal(0));
			}
		} catch (Exception e) {
					System.out.println(e);
		}
		if (!org.apache.commons.lang.StringUtils.isEmpty(getOrderingCustomer())) {
			createQuery = createQuery
					+ " concat(beneficiaryCustomerName,' ',beneficiaryCustomerAddress,' ',beneficiaryCustomerID,' ',beneficiaryCustomerCtry,' ',beneficiaryCustAcct,' ',ultimateCreditorName,' ',ultimateCreditorAddress,' ',ultimateCreditorID) like '%"
					+ getOrderingCustomer() + "%' AND";
		}
		String tempgroupbyString = "order by ";
		try {
			if(StringUtils.isNotBlank(getSenderReceiver()) || StringUtils.isNotEmpty(getSenderReceiver()) || getSenderReceiver()!=null)
			{
				if(getSenderReceiver().equals("N/A"))
				{
					System.out.println("Option selected was NA");
					
				}
				else
				{
					tempgroupbyString = tempgroupbyString + getSenderReceiver()
							+ ",";
				}
			}
			else{
				
				addFieldError("SenderReceiver", "Please Select Any Option in Group By if Not please Select N/A");
				if(getHiddenDirection().equalsIgnoreCase("I"))
					forward="inputInward";
					else
						forward = "input";
			}
		} catch (Exception e) {
			logger.error(e);
			addFieldError("SenderReceiver", "Please Select Any Option");
			if(getHiddenDirection().equalsIgnoreCase("I"))
				forward="inputInward";
				else
					forward = "input";
		}
		int counter = 0;
		for (int i = 0; i < groupby.size(); i++) {
			tempgroupbyString = tempgroupbyString + groupby.get(i) + ",";
			counter++;
		}
		String newTempgroupByString = "";
		if (counter > 0) {
			newTempgroupByString = tempgroupbyString.substring(0,
					tempgroupbyString.length() - 1);
		}
		counter = 0;
		String createStatus = "";
		if((getOutwardQueue().size())>0 && !getOutwardQueue().isEmpty()){
		for (int i = 0; i < outwardQueue.size(); i++) {
			String status = PaymentStatusEnum
					.findPaymentStatusEnumByName(outwardQueue.get(i));
			createStatus = createStatus + " msgStatus ='" + status + "' or";
			//System.out.println(outwardQueue.get(i) + "value of s :--- " + status);
			counter++;

		}
		if (counter > 0) {
			createStatus = createStatus.substring(0, createStatus.length() - 2)
					+ ")";

			//System.out.println("createStatus:-- " + createStatus);
		}
		}
		if (createQuery.length() > 3) {
			createQuery = createQuery.substring(0, createQuery.length() - 4);
			/*System.out.println("CREATED QUERY:-----" + createQuery
					+ tempgroupbyString);*/
		}
		
		String finalQuery = "";
		
		if (createQuery.length() > 0){
			finalQuery = finalQuery + createQuery;}
		if (hostList.length() > 0) {
			if (createQuery.length() == 0)
				finalQuery = finalQuery + "  (" + hostList + " ";
			else
				finalQuery = finalQuery + " AND (" + hostList + " ";
		}
		if (messagetypelist.length() > 0) {
			if (createQuery.length() == 0 && hostList.length()==0)
				finalQuery = finalQuery + "  (" + messagetypelist + " ";
			else
				finalQuery = finalQuery + " AND (" + messagetypelist + " ";
		}
		if (createStatus.length() > 0) {
			if (createQuery.length() == 0 && messagetypelist.length()==0 && hostList.length()==0)
				finalQuery = finalQuery + "  (" + createStatus + " ";
			else
				finalQuery = finalQuery + " AND (" + createStatus + " ";
		}
		if (newTempgroupByString.length() > 0)
				finalQuery = finalQuery + "  " + newTempgroupByString + " ";
			
			
		
		System.out.println(finalQuery.length());
		if(!(finalQuery.length()>7))
			finalQuery="";
		if (getColoumToAdd().size()> 0) {
			for (int i = 0; i < getColoumToAdd().size(); i++) {
				
				session.put(coloumToAdd.get(i), "Required");

		}
		}
		else
		{
			addFieldError("coloumToAdd", "Please Select Atleast One Column From Columns To Display");
			setValueFromDate(null);
			
			setValueToDate(null);
			if(getHiddenDirection().equalsIgnoreCase("I"))
			forward="inputInward";
			else
				forward = "input";
		}
		
		if(forward.equals("success")){
		List<ReportDto> searchList = generateReportService.getSearchResult(finalQuery);
		System.out.println("Final Query is "+finalQuery);
		session.put("searchList", searchList);
		setReportList(searchList);
		}

		
		if(getValueFromDate()==null && getValueToDate()==null){
			if(!forward.equals("input")){
			setValueFromDate(null);
			setValueToDate(null);
			
			}
		}
		return forward;
		}catch (Exception e) {
			e.printStackTrace();
			
		}
		
		if(getHiddenDirection().equalsIgnoreCase("I"))
			return "inputInward";
			else
				return "input";
		
	}
	
	@SkipValidation
	public String downloadReport(){
		
		String reportType = (String)session.get("reportType");
		String reportName ="Payment Sent Report";
		if(reportType.equals("P")){
			reportName ="Payment Received Report";
		}
		System.out.println("Number of Reports ::" +((List<ReportDto>)session.get("searchList")).size());
		Map<String,String> columnMap = new HashMap<String,String>();
		for(Map.Entry<String, Object> entry:session.entrySet()){
			if(entry.getValue().equals("Required")){
				columnMap.put(entry.getKey(), (String)entry.getValue());
			}
		}
		System.out.println("Report Type ::" +getReportType());
		if(getReportType().equalsIgnoreCase("PDF")){
			if(columnMap.size()>25){
				addActionError("PDF file can accommodate  upto 24 columns.");
				setReportList((List<ReportDto>)session.get("searchList"));
				return "input";
			}
		}
		try{
			ReportGenerationUtil reportUtil = new ReportGenerationUtil();
			reportUtil.setServletRequest(servletRequest);
			reportUtil.setReportName(reportName);
			reportUtil.generatePaymentSentReport((List<ReportDto>)session.get("searchList"), columnMap, getReportType());
		}catch(Exception ex){
			ex.printStackTrace();
			addActionError(ex.getMessage());
			setReportList((List<ReportDto>)session.get("searchList"));
			return "input";
		}
		return "success";
		
	}
	

	private PaymentSubmittedReportService getPaymentSubmittedReportService() {
		PaymentSubmittedReportService	paymentSubmittedReportService = null;
		try {
			
				paymentSubmittedReportService = (PaymentSubmittedReportService) ApplicationContextProvider
					.getBean("paymentSubmittedReportService");
		} catch (ApplicationContextException applicationContextException) {
			logger.error("Payment Submitted Report"
					+ applicationContextException);
		}

		return paymentSubmittedReportService;
	}

	public String dateChanger(String aNewValue) throws ParseException {
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
		System.out.println(yy + "/" + mm + "/" + dd);
		String dateconverted = dd + "/" + mm + "/" + yy;
		return dateconverted;

	}

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
		 
		
		 if(date.before(date2) || date.equals(date2))
		 {
			 System.out.println("DATE ONE IS Smaller :----"+str_date1+" DATE 2 "+str_date2);
			 valid="true";
			 
		 }
		 else if(date.after(date2))
		 {
			 System.out.println("DATE ONE IS GREATER :---"+str_date1+" DATE 2 "+str_date2);
			 valid="false";
			 addFieldError("enquiryTODate", "TO date Must Be Smaller Than From Date ");
			
		 }
		  } catch (Exception e)
		  {
			  System.out.println("Exception :"+e); 
			  }  
		 return valid;
		 }

public void setServletRequest(HttpServletRequest servletRequest) {
	this.servletRequest = servletRequest;
}

public void validate(){
	
	}

public String getReportType() {
	return reportType;
}

public void setReportType(String reportType) {
	this.reportType = reportType;
}

}
