package com.logica.ngph.web.action;

import java.math.BigDecimal;
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
import java.util.Map.Entry;
import java.util.TimeZone;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.springframework.context.ApplicationContextException;

import com.logica.ngph.Entity.LcMast;
import com.logica.ngph.Entity.SecUsers;
import com.logica.ngph.common.dtos.UserMaintenanceDTO;
import com.logica.ngph.dtos.LcMastDto;
import com.logica.ngph.service.LcReportsService;
import com.logica.ngph.service.PaymentMessageService;
import com.logica.ngph.service.UserLoginService;
import com.logica.ngph.web.utils.ApplicationContextProvider;
import com.logica.ngph.web.utils.GenrateLcReportUtil;
import com.logica.ngph.web.utils.WebConstants;
import com.opensymphony.xwork2.ActionSupport;

public class LcReportsAction extends ActionSupport implements SessionAware{

	/**
	 * 
	 */
	private Logger logger = Logger.getLogger(LcReportsAction.class);
	private static final long serialVersionUID = 1L;
	private Map<String, Object> session;
	private String advisingBank;
	private List status;
	private String applicant;
	private String Beneficiary;
	private Date fromDate;
	private Date toDate;
	private BigDecimal fromAmount;
	private BigDecimal toAmount;
	private List seletedColumn = new ArrayList();
	private List<LcMastDto> displayList;
	private String lcDirection;
	private String advising_issuingBank;
	private String reportType;
	public static String direction ="";
	private String title;
	
	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}

	private HttpServletRequest servletRequest;

	public void setServletRequest(HttpServletRequest servletRequest) {
		this.servletRequest = servletRequest;
	}


	public String getAdvising_issuingBank() {
		return advising_issuingBank;
	}
	public void setAdvising_issuingBank(String advising_issuingBank) {
		this.advising_issuingBank = advising_issuingBank;
	}
	public String getLcDirection() {
		return lcDirection;
	}
	public void setLcDirection(String lcDirection) {
		this.lcDirection = lcDirection;
		this.session.put("lcDirection", lcDirection);
	}
	public List<LcMastDto> getDisplayList() {
		return displayList;
	}
	public void setDisplayList(List<LcMastDto> displayList) {
		this.displayList = displayList;
		this.session.put("displayList", displayList);
	}
	public List getSeletedColumn() {
		return seletedColumn;
	}
	public void setSeletedColumn(List seletedColumn) {
		this.seletedColumn = seletedColumn;
	}

	private String groupBy;
	private Boolean groupByStatus;


		public String displayLCReciveScreen(){
			try{
				removeForSession();
				setGroupBy("N/A");
				setAdvising_issuingBank("issuingBank");
				setReportType("I");
				setLcDirection("I");
				setFromDate(getDateTime());
				setToDate(getDateTime());
				return "success";
			}catch (Exception e) {
				e.printStackTrace();
				return "input";
			}	
		}
	
	public String displayLCsentScreen()
	{
		try{
			removeForSession();
			setGroupBy("N/A");
			setReportType("O");
			setLcDirection("O");
			setFromDate(getDateTime());
			setToDate(getDateTime());
			setAdvising_issuingBank("advisingBank");
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
	@SuppressWarnings("unchecked")
	public String generateReport()
	{
		try{
			PaymentMessageService paymentMessageService = (PaymentMessageService)ApplicationContextProvider.getBean("paymentMessageService");
			LcReportsService lcReportsService=(LcReportsService) ApplicationContextProvider.getBean("lcReportsService");
			String userId = (String)session.get(WebConstants.CONSTANT_USER_NAME);
			String getLocalBankIFSC = lcReportsService.getLocalBankIFSC(userId);
			SecUsers userDetails= paymentMessageService.getUserDetails(userId);
			String msgBranch = userDetails.getUserBranch();
			UserLoginService userLoginService = (UserLoginService)ApplicationContextProvider.getBean("userLoginService");
			UserMaintenanceDTO userDto = userLoginService.getLogInTimeDetails(userId);
			Timestamp businessDay = userDto.getBusinessDay();
			@SuppressWarnings("rawtypes")
			List dataQuery = new ArrayList();
			System.out.println(getGroupBy());
			System.out.println(getGroupByStatus());
			String tablename ="";
			String[] statusArray = (String[]) ServletActionContext.getRequest().getParameterValues("status1");
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
					dataQuery.add("lc.lcAdvisingBank = '"+getAdvisingBank()+"'");
				else
					dataQuery.add("lc.lcAdvisingBank like '%"+getAdvisingBank()+"'");
				}	
				else if(getAdvising_issuingBank().equals("issuingBank")){
					if(getAdvisingBank().length()==11)
						dataQuery.add("lc.lcIssuingBank = '"+getAdvisingBank()+"'");
					else
						dataQuery.add("lc.lcIssuingBank like '%"+getAdvisingBank()+"'");
				}
			}
			
			if(getStatus().size()!=0 && getStatus()!=null)
			{
				List temp = getStatus();
				String tempString="(";
				for(int i =0;i<temp.size();i++)
				{
					if(i==temp.size()-1)
					tempString = tempString+"lc.lcStatus = '"+Integer.parseInt(temp.get(i).toString())+"')";
					else
						tempString = tempString+"lc.lcStatus = '"+Integer.parseInt(temp.get(i).toString())+"' OR ";
				}
				dataQuery.add(tempString);
			}
			if(StringUtils.isNotBlank(getApplicant())&& StringUtils.isNotEmpty(getApplicant()))
			{
				dataQuery.add("lc.lcAppicant like '%"+getApplicant()+"'");
				
			}
			if(StringUtils.isNotBlank(getBeneficiary())&& StringUtils.isNotEmpty(getBeneficiary()))
			{
				dataQuery.add("lc.lcBenificiary like '%"+getBeneficiary()+"'");
				
			}
			if(getFromDate()!=null && getToDate()!=null)
			{
				Date date1 = getFromDate();
				SimpleDateFormat sdf;
				sdf = new SimpleDateFormat("dd-MMM-yy");
//				System.out.println(sdf.format(date1));
				
				Date date2 = getToDate();
				String valid=CompareDate(date1,date2);
				if(valid.equals("true")){
					tablename =getTableQuery(date1,date2,businessDay);
				
				dataQuery.add("lc.lstModifiedTime between (to_date('"+sdf.format(date1)+" 00:00:00' , 'dd-MON-yy hh24:mi:ss')) AND (to_date('"+sdf.format(date2)+" 23:59:59' , 'dd-MON-yy hh24:mi:ss'))");
				}
				else{
					return "input";
					}
								
				}else{
					tablename ="BOTH";
				}
			String tempGroupBy="";
			if(getFromAmount()!=null && getToAmount()!=null)
			{
				if (Double.parseDouble(getFromAmount().toString()) < Double.parseDouble(getToAmount().toString()))
				dataQuery.add("lc.lcAmount between "+getFromAmount()+" And "+getToAmount()+"");
				else{
					addFieldError(fromAmount.toString(),"TO Amount Should Always Be Greater Than From Amount");
					return "input";}
			}
			if(StringUtils.isNotBlank(getGroupBy()) && StringUtils.isNotEmpty(getGroupBy()))
			{
				if(!getGroupBy().equals("N/A"))
					tempGroupBy = tempGroupBy+" order by lc.lcAdvisingBank";
				
			}
			
			if(getGroupByStatus()==true)
			{
				if(tempGroupBy.equals(""))
				{
					tempGroupBy = tempGroupBy+" order by lc.lcStatus";
				}
				else
				{
					tempGroupBy = tempGroupBy+" ,lc.lcStatus";
				}
				
			}
			if(tempGroupBy.equals(""))
			{
				tempGroupBy = " order by lc.lcIssueDate desc ";
			}
			else
			{
				tempGroupBy = tempGroupBy+" ,lc.lcIssueDate desc";
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
				addFieldError("advisingBank", "Please Select Atleast One Column From Columns To Display");
				return INPUT;
			}
			
			
			setDisplayList(lcReportsService.getReportDate(getLcDirection(),createQuery,tempGroupBy,getLocalBankIFSC,msgBranch,tablename));
			session.put("searchList", getDisplayList());
			setReportList(getDisplayList());
			if(getLcDirection().equals("O"))
			{
				setTitle("Lc Sent Reports");
			}
			else if(getLcDirection().equals("I"))
			{
				setTitle("Lc Received Reports");
			}
			System.out.println("Dirction : - "+getLcDirection());
			direction = getLcDirection();
			
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
	
	
	private final static String getTime()
	{
		Calendar calendar = new GregorianCalendar();
		  String am_pm;
		  int hour = calendar.get(Calendar.HOUR_OF_DAY);
		  int minute = calendar.get(Calendar.MINUTE);
		  int second = calendar.get(Calendar.SECOND);
		  return hour+":"+minute+":"+second;
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
	
	private String getStringDateTime(Date date)  
    {  
		String updateDate="";
		try{
        DateFormat df = new SimpleDateFormat("MM/dd/yy");  
        df.setTimeZone(java.util.TimeZone.getDefault());  
     
        Date d = date;
        int dd = d.getDate();
        int mm = d.getMonth()+1;
        int yy = d.getYear();
        
        
        String dt = mm +"/"+dd+"/"+yy;
        Date d1 = df.parse(dt);
        
        updateDate = df.format(d1);
        return updateDate;
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
	
	private List<LcMastDto> ReportList;
	public List<LcMastDto> getReportList() {
		return ReportList;
	}


	public void setReportList(List<LcMastDto> reportList) {
		ReportList = reportList;
	}


	@SkipValidation
	public String downloadReport(){
		
		String reportType = (String)session.get("lcDirection");
		String reportName ="LC Sent Report";
		if(reportType.equals("I")){
			reportName ="Lc Received Report";
		}
		System.out.println("Number of Reports ::" +((List<LcMastDto>)session.get("searchList")).size());
		Map<String,String> columnMap = new HashMap<String,String>();
		for(Map.Entry<String, Object> entry:session.entrySet()){
			if(entry.getValue().equals("Required")){
				columnMap.put(entry.getKey(), (String)entry.getValue());
			}
		}
		System.out.println("Report Type ::" +getReportType());
		if(getReportType().equalsIgnoreCase("PDF")){
			if(columnMap.size()>14){
				addActionError("PDF file can accommodate  upto 14 columns.");
				setReportList((List<LcMastDto>)session.get("searchList"));
				return "input";
			}
		}
		try{
			GenrateLcReportUtil reportUtil = new GenrateLcReportUtil();
			reportUtil.setServletRequest(servletRequest);
			reportUtil.setReportName(reportName);
			reportUtil.generatePaymentSentReport((List<LcMastDto>)session.get("searchList"), columnMap, getReportType());
		}catch(Exception ex){
			ex.printStackTrace();
			setGroupBy("N/A");
			addFieldError("groupBy", "No Record Found To Create Reports");
			//addActionError(ex.getMessage());
			setReportList((List<LcMastDto>)session.get("searchList"));
			return "input";
		}
		return null;
		
	}
	public String getReportType() {
		return reportType;
	}

	public void setReportType(String reportType) {
		this.reportType = reportType;
		this.session.put("reportType", reportType);
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

	public String getApplicant() {
		return applicant;
	}

	public void setApplicant(String applicant) {
		this.applicant = applicant;
	}

	public String getBeneficiary() {
		return Beneficiary;
	}

	public void setBeneficiary(String beneficiary) {
		Beneficiary = beneficiary;
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

	public Map<String, Object> getSession() {
		return session;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
public void validate()
{
	
}
	

}
