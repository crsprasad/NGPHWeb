package com.logica.ngph.web.action;

import java.sql.Timestamp;
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

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
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

public class BgRejectReportsAction extends ActionSupport implements SessionAware {
	private Logger logger = Logger.getLogger(BgRejectReportsAction.class);
	private static final long serialVersionUID = 1L;
	private Map<String, Object> session;
	private String fromDate;
	private String toDate;
	private String lcDirection;
	private List seletedColumn = new ArrayList();
	private List<BgMastDto> displayBgList;
	private static String direction;
	
	public static String getDirection() {
		return direction;
	}
	public static void setDirection(String direction) {
		BgRejectReportsAction.direction = direction;
	}
	public List<BgMastDto> getDisplayBgList() {
		return displayBgList;
	}
	public void setDisplayBgList(List<BgMastDto> displayBgList) {
		this.displayBgList = displayBgList;
		this.session.put("displayBgList", displayBgList);
	}
	private List<BgMastDto> displayList;
	private String reportType;
	
	private List<BgMastDto> ReportList;
	
	
	public List<BgMastDto> getReportList() {
		return ReportList;
	}
	public void setReportList(List<BgMastDto> reportList) {
		ReportList = reportList;
	}
	public String getReportType() {
		return reportType;
	}
	public void setReportType(String reportType) {
		this.reportType = reportType;
	}
	
	public String getFromDate() {
		return fromDate;
	}
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}
	public String getToDate() {
		return toDate;
	}
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
	public String getLcDirection() {
		return lcDirection;
	}
	public void setLcDirection(String lcDirection) {
		this.lcDirection = lcDirection;
	}
	
	public List getSeletedColumn() {
		return seletedColumn;
	}
	public void setSeletedColumn(List seletedColumn) {
		this.seletedColumn = seletedColumn;
	}
	public List<BgMastDto> getDisplayList() {
		return displayList;
	}
	public void setDisplayList(List<BgMastDto> displayList) {
		this.displayList = displayList;
	}
	
	
	public String displayBgRejectScreen(){
		try{
			removeForSession();
			setFromDate(getDateTime());
			setToDate(getDateTime());
			setLcDirection("O");
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

	private  final static String getDateTime()  
	{  
	   /* DateFormat df = new SimpleDateFormat("MM/dd/yy");  
	    df.setTimeZone(TimeZone.getTimeZone("GMT"));  
	    return df.format(new Date());  */
		String updateDate="";
		try{
        DateFormat df = new SimpleDateFormat("MM/dd/yy");  
        df.setTimeZone(TimeZone.getTimeZone("GMT"));  
        
        Date d = Calendar.getInstance().getTime();
        Date d2 = new Date();
        int dd = d.getDate();
        int mm = d.getMonth()+1;
        int yy = d.getYear();
        
        
        String dt = mm +"/"+dd+"/"+yy;
        Date d1 = df.parse(dt);
        
        updateDate = df.format(d1);  
		}catch(ParseException e){
			 System.out.println("Exception :"+e); 
		}
		return updateDate;
	} 
	
	public String generateReport()
	{
		try{
			@SuppressWarnings("rawtypes")
			List dataQuery = new ArrayList();
			String tablename ="";
			String createQuery="";
			System.out.println("direction "+getLcDirection());
			LcReportsService lcReportsService=(LcReportsService) ApplicationContextProvider.getBean("lcReportsService");
			String userId = (String)session.get(WebConstants.CONSTANT_USER_NAME);
			String getLocalBankIFSC = lcReportsService.getLocalBankIFSC(userId);
			PaymentMessageService paymentMessageService = (PaymentMessageService)ApplicationContextProvider.getBean("paymentMessageService");
			SecUsers userDetails= paymentMessageService.getUserDetails(userId);
			String msgBranch = userDetails.getUserBranch();
			UserLoginService userLoginService = (UserLoginService)ApplicationContextProvider.getBean("userLoginService");
			UserMaintenanceDTO userDto = userLoginService.getLogInTimeDetails(userId);
			Timestamp businessDay = userDto.getBusinessDay();
			String ColumnString="";
			List tempColumn = getSeletedColumn();
			
			if(tempColumn.size()!=0){
				
				for(int i = 0;i<tempColumn.size();i++)
				{
						this.session.put(tempColumn.get(i).toString(), "Required");				
				}
			}else{
				addFieldError("selectColumn", "Please Select Atleast One Column");
				return INPUT;
			}
			
			if(StringUtils.isNotBlank(getFromDate()) && StringUtils.isNotEmpty(getFromDate()) && StringUtils.isNotBlank(getToDate()) && StringUtils.isNotEmpty(getToDate()))
			{
				String date1=dateChanger(getFromDate());
				String date2=dateChanger(getToDate());
				String valid=CompareDate(date1,date2);
				if(valid.equals("true")){
					tablename =getTableQuery(date1,date2,businessDay);
					createQuery = "bm.bgLastModifiedTime between (to_date('"+date1+" 00:00:00' , 'dd/MM/yyyy HH24:MI:SS')) AND (to_date('"+date2+" "+getTime()+"' , 'dd/MM/yyyy HH24:MI:SS'))";
					for(int i = 0;i<dataQuery.size();i++)
					System.out.println("Create Query : - "+ createQuery);
 				setDisplayBgList(lcReportsService.getBgRejectedReportData(getLcDirection(),createQuery,tablename,getLocalBankIFSC,msgBranch));
				direction = getLcDirection();
				session.put("searchList", getDisplayBgList());
				setReportList(getDisplayBgList());
				
				}
			}else{
				addFieldError("fromDate", "From Date is Required");
				addFieldError("toDate", "To Date is Required");
				return "input";
		}
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
	
	String getTableQuery(String fromDate,String toDate,Timestamp businessDay){
		String tableName="";
		try {  
		 DateFormat formatter ; 
		 Date date ; 
		 Date date2 ; 
		 Date curDate ; 
		  formatter = new SimpleDateFormat("dd/MM/yy");
		  date = (Date)formatter.parse(fromDate);  
		  date2 = (Date)formatter.parse(toDate);
		  
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
		 System.out.println("Table to fetch "+tableName);
		  } catch (ParseException e)
		  {
			  System.out.println("Exception :"+e); 
			  }  
		 return tableName;		
		
	}
	
	
	@SkipValidation
	public String downloadReport(){
		
		String reportName ="Bg Rejected Report";
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
	public String CompareDate(String str_date1,String str_date2) {
		 
		String valid="";
		try {  
		 DateFormat formatter ; 
		 Date date ; 
		 Date date2 ; 
		  formatter = new SimpleDateFormat("dd/MM/yy");
		  date = (Date)formatter.parse(str_date1);  
		  date2 = (Date)formatter.parse(str_date2);
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
			 addFieldError("enquiryTODate", "TO date Must Be Smaller Than From Date ");
			
		 }else{
			 valid="true"; 
		 }
		  } catch (ParseException e)
		  {
			  System.out.println("Exception :"+e); 
			  }  
		 return valid;
		 }
	public Map<String, Object> getSession() {
		return session;
	}
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
}
