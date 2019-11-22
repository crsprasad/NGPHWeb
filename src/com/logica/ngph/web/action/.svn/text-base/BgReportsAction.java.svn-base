package com.logica.ngph.web.action;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import java.util.Map.Entry;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.springframework.context.ApplicationContextException;


import com.logica.ngph.Entity.SecUsers;
import com.logica.ngph.common.dtos.UserMaintenanceDTO;
import com.logica.ngph.dtos.BgMastDto;
import com.logica.ngph.service.LcReportsService;
import com.logica.ngph.service.PaymentMessageService;
import com.logica.ngph.service.UserLoginService;
import com.logica.ngph.web.utils.ApplicationContextProvider;
import com.logica.ngph.web.utils.GenerateBgReportUtil;
import com.logica.ngph.web.utils.WebConstants;
import com.opensymphony.xwork2.ActionSupport;

public class BgReportsAction extends ActionSupport implements SessionAware{
	private Logger logger = Logger.getLogger(LcReportsAction.class);
	private static final long serialVersionUID = 1L;
	private Map<String, Object> session;
	private String advisingBank;
	private List status;
	private String groupBy;
	private Boolean groupByStatus;
	private Date fromDate;
	private Date toDate;
	private List seletedColumn = new ArrayList();
	private List<BgMastDto> displayBgList;
	private String lcDirection;
	private List<BgMastDto> displayList;
	private String title;
	private String advising_issuingBank;
	public String getAdvising_issuingBank() {
		return advising_issuingBank;
	}

	public void setAdvising_issuingBank(String advising_issuingBank) {
		this.advising_issuingBank = advising_issuingBank;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	public static String direction; 
	
	
	public List<BgMastDto> getDisplayList() {
		return displayList;
	}

	public void setDisplayList(List<BgMastDto> displayList) {
		this.displayList = displayList;
	}

	public String getReportType() {
		return reportType;
	}

	public void setReportType(String reportType) {
		this.reportType = reportType;
		this.session.put("reportType", reportType);
	}
	private String reportType;
	
	
	public String getAdvisingBank() {
		return advisingBank;
	}

	public void setAdvisingBank(String advisingBank) {
		this.advisingBank = advisingBank;
	}

	public List getStatus() {
		return status;
	}

	public void setStatus(List status) {
		this.status = status;
	}

	public String getGroupBy() {
		return groupBy;
	}

	public void setGroupBy(String groupBy) {
		this.groupBy = groupBy;
	}

	public Boolean getGroupByStatus() {
		return groupByStatus;
	}

	public void setGroupByStatus(Boolean groupByStatus) {
		this.groupByStatus = groupByStatus;
	}

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	public List getSeletedColumn() {
		return seletedColumn;
	}

	public void setSeletedColumn(List seletedColumn) {
		this.seletedColumn = seletedColumn;
	}

	

	public List<BgMastDto> getDisplayBgList() {
		return displayBgList;
	}

	public void setDisplayBgList(List<BgMastDto> displayBgList) {
		this.displayBgList = displayBgList;
		this.session.put("displayBgList", displayBgList);
	}

	public String getLcDirection() {
		return lcDirection;
	}

	public void setLcDirection(String lcDirection) {
		this.lcDirection = lcDirection;
		this.session.put("lcDirection", lcDirection);
	}

	public String displayBgReceiveScreen(){
		try{
			removeForSession();
			setGroupBy("N/A");
			setLcDirection("I");
			setFromDate(getDateTime());
			setToDate(getDateTime());
			return "success";
		}catch (Exception e) {
			e.printStackTrace();
			return "input";
		}	
	}

public String displayBgSentScreen()
{
	try{
		removeForSession();
		setGroupBy("N/A");
		setLcDirection("O");
		setFromDate(getDateTime());
		setToDate(getDateTime());
		return "success";
	}catch (Exception e) {
		e.printStackTrace();
		return "input";
	}
}
public void removeForSession()
{
	for (Entry entry : session.entrySet()) {
        if ("Required".equals(entry.getValue())) {
        	session.remove(entry.getKey());

        }
    }
}
 
public String generateReport()
{
	try{
		@SuppressWarnings("rawtypes")
		List dataQuery = new ArrayList();
		System.out.println(getGroupBy());
		System.out.println(getGroupByStatus());
		LcReportsService lcReportsService=(LcReportsService) ApplicationContextProvider.getBean("lcReportsService");
		String userId = (String)session.get(WebConstants.CONSTANT_USER_NAME);
		String getLocalBankIFSC = lcReportsService.getLocalBankIFSC(userId);
		PaymentMessageService paymentMessageService = (PaymentMessageService)ApplicationContextProvider.getBean("paymentMessageService");
		SecUsers userDetails= paymentMessageService.getUserDetails(userId);
		String msgBranch = userDetails.getUserBranch();
		UserLoginService userLoginService = (UserLoginService)ApplicationContextProvider.getBean("userLoginService");
		UserMaintenanceDTO userDto = userLoginService.getLogInTimeDetails(userId);
		Timestamp businessDay = userDto.getBusinessDay();
		String tablename ="";
		String[] statusArray = (String[]) ServletActionContext.getRequest().getParameterValues("selectBG");
		if(statusArray!=null){
		List statusTemp = Arrays.asList(statusArray);
		setStatus(statusTemp);	
		}else{
			setStatus(new ArrayList());	
		}
		
		if(StringUtils.isNotBlank(getAdvisingBank()) && StringUtils.isNotEmpty(getAdvisingBank()))
		{
			if(getAdvising_issuingBank().equals("advisingBank")){
				if(getAdvisingBank().length()==11)
					dataQuery.add("bm.advisingBank = '"+getAdvisingBank()+"'");
				else
					dataQuery.add("bm.advisingBank like '%"+getAdvisingBank()+"'");
				}	
				else if(getAdvising_issuingBank().equals("issuingBank")){
					if(getAdvisingBank().length()==11)
						dataQuery.add("bm.issuingBank = '"+getAdvisingBank()+"'");
					else
						dataQuery.add("bm.issuingBank like '%"+getAdvisingBank()+"'");
				}
			
			
			if(getAdvisingBank().length()==11)
				dataQuery.add("bm.advisingBank = '"+getAdvisingBank()+"'");
			else
				dataQuery.add("bm.advisingBank like '%"+getAdvisingBank()+"'");
				
		}
		if(getStatus().size()!=0 && getStatus()!=null)
		{
			List temp = getStatus();
			String tempString="(";
			for(int i =0;i<temp.size();i++)
			{
				if(i==temp.size()-1)
				tempString = tempString+"bm.bgStatus = '"+Integer.parseInt(temp.get(i).toString())+"')";
				else
					tempString = tempString+"bm.bgStatus = '"+Integer.parseInt(temp.get(i).toString())+"' OR ";
			}
			dataQuery.add(tempString);
		}
		
		
		if(getFromDate()!=null && getToDate()!=null)
		{
			Date date1 = getFromDate();
			SimpleDateFormat sdf;
			sdf = new SimpleDateFormat("dd-MMM-yy");
//			System.out.println(sdf.format(date1));
			
			Date date2 = getToDate();
			String valid=CompareDate(date1,date2);
			if(valid.equals("true")){
				tablename =getTableQuery(date1,date2,businessDay);
			dataQuery.add("bm.bgLastModifiedTime between (to_date('"+sdf.format(date1)+" 00:00:00' , 'dd-MON-yy hh24:mi:ss')) AND (to_date('"+sdf.format(date2)+" 23:59:59' , 'dd-MON-yy hh24:mi:ss'))");
			}
			else{
				return "input";
				}
							
			}else{
				tablename ="BOTH";
			}
		String tempGroupBy="";
		
		if(StringUtils.isNotBlank(getGroupBy()) && StringUtils.isNotEmpty(getGroupBy()))
		{
			if(!getGroupBy().equals("N/A"))
				tempGroupBy = tempGroupBy+" order by bm.advisingBank";
			
		}
		
		if(getGroupByStatus()==true)
		{
			if(tempGroupBy.equals(""))
			{
				tempGroupBy = tempGroupBy+" order by bm.bgStatus";
			}
			else
			{
				tempGroupBy = tempGroupBy+" ,bm.bgStatus";
			}
			
		}
		String createQuery="";
		for(int i = 0;i<dataQuery.size();i++)
		{
			if(i==(dataQuery.size()-1))
				createQuery = createQuery+dataQuery.get(i);
			else
			createQuery = createQuery+dataQuery.get(i)+" AND ";
			
			System.out.println("Create Query : - "+ createQuery);
		}
		
		String ColumnString="";
		List tempColumn = getSeletedColumn();
		
		if(tempColumn.size()!=0){
			
			for(int i = 0;i<tempColumn.size();i++)
			{
					this.session.put(tempColumn.get(i).toString(), "Required");				
			}
		}else{
			addFieldError("advisingBank", "Please Select Atleast One Column");
			return INPUT;
		}
		
		
		
		setDisplayBgList(lcReportsService.getBgReportDate(getLcDirection(),createQuery,tempGroupBy,getLocalBankIFSC,msgBranch,tablename));
		if(getLcDirection().equals("O"))
		{
			setTitle("Bg Sent Reports");
		}
		else if(getLcDirection().equals("I"))
		{
			setTitle("Bg Received Reports");
		}
		direction = getLcDirection();
		session.put("searchList", getDisplayBgList());
		setReportList(getDisplayBgList());
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

return "input";
}
String getTableQuery(Date fromDate,Date toDate,Timestamp businessDay){
	String tableName="";
	try {  
	 DateFormat formatter ; 
	 Date date ; 
	 Date date2 ; 
	 Date curDate ; 
	  formatter = new SimpleDateFormat("dd/MM/yy");
	  date = fromDate;  
	  date2 = toDate;
	  DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	  String currrDate = df.format(businessDay);	
	//  String currrDate = df.format(new Date());
	 String cdate = dateChanger(currrDate);
	 curDate = (Date)formatter.parse(cdate);
	 
	 if(date.before(curDate) && (date2.after(curDate) || date2.equals(curDate)))
	 {
		 tableName="BOTH";
		 
	 }
	 else if(date.before(curDate) && date2.before(curDate))
	 {
		 tableName="HIST";
		
	 }else if(date.equals(curDate) && date2.equals(curDate)){
		 tableName="RPT";
	 }else{
		 tableName="RPT";
	 }
	// System.out.println("Table to fetch "+tableName);
	  } catch (ParseException e)
	  {
		  System.out.println("Exception :"+e); 
		  }  
	 return tableName;		
	
}


private  final static Date getDateTime()  
{  
	String updateDate="";
	try{
    DateFormat df = new SimpleDateFormat("MM/dd/yy");  
    df.setTimeZone(java.util.TimeZone.getDefault());  
    System.out.println("Default System Time Zone : - "+java.util.TimeZone.getDefault());
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

private final static String getTime()
{
	Calendar calendar = new GregorianCalendar();
	  String am_pm;
	  int hour = calendar.get(Calendar.HOUR_OF_DAY);
	  int minute = calendar.get(Calendar.MINUTE);
	  int second = calendar.get(Calendar.SECOND);
	  return hour+":"+minute+":"+second;
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

private List<BgMastDto> ReportList;
public List<BgMastDto> getReportList() {
	return ReportList;
}

public void setReportList(List<BgMastDto> reportList) {
	ReportList = reportList;
}

@SkipValidation
public String downloadReport(){
	
	String reportType = (String)session.get("lcDirection");
	String reportName ="Bg Sent Report";
	if(reportType.equals("I")){
		reportName ="Bg Received Report";
	}
	
	Map<String,String> columnMap = new HashMap<String,String>();
	for(Map.Entry<String, Object> entry:session.entrySet()){
		if(entry.getValue().equals("Required")){
			columnMap.put(entry.getKey(), (String)entry.getValue());
		}
	}
	System.out.println("Report Type ::" +getReportType());
	if(getReportType().equalsIgnoreCase("PDF")){
		if(columnMap.size()>13){
			addActionError("PDF file can accommodate  upto 6 columns.");
			setReportList((List<BgMastDto>)session.get("searchList"));
			return "input";
		}
	}
	try{
		GenerateBgReportUtil reportUtil = new GenerateBgReportUtil();
		//reportUtil.setServletRequest(servletRequest);
		reportUtil.setReportName(reportName);
		
		reportUtil.generatePaymentSentReport((List<BgMastDto>)session.get("searchList"), columnMap, getReportType());
	}catch(Exception ex){
		ex.printStackTrace();
		addActionError(ex.getMessage());
		setReportList((List<BgMastDto>)session.get("searchList"));
		return "input";
	}
	return null;
	
}


	public Map<String, Object> getSession() {
		return session;
	}
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

}
