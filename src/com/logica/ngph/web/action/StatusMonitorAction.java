package com.logica.ngph.web.action;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.SessionAware;

import com.logica.ngph.common.dtos.UserMaintenanceDTO;
import com.logica.ngph.common.enums.PaymentStatusEnum;
import com.logica.ngph.dtos.ReportDto;
import com.logica.ngph.service.PaymentMessageService;
import com.logica.ngph.service.UserLoginService;
import com.logica.ngph.web.utils.ApplicationContextProvider;
import com.logica.ngph.web.utils.WebConstants;
import com.opensymphony.xwork2.ActionSupport;

public class StatusMonitorAction extends ActionSupport implements SessionAware{
	private Logger logger = Logger.getLogger(StatusMonitorAction.class);
	private static final long serialVersionUID = 1L;
	private Map<String, Object> session;
	private Date userDate;
	
	public Date getUserDate() {
		return userDate;
	}


	public void setUserDate(Date userDate) {
		this.userDate = userDate;
	}
	private PaymentMessageService paymentMessageService;
	
	Map<String,Long> outboundStatus = new HashMap<String,Long>();
	Map<String,Long> inboundStatus = new HashMap<String,Long>();
	
	
	public Map<String, Long> getOutboundStatus() {
		return outboundStatus;
		
	}


	public void setOutboundStatus(Map<String, Long> outboundStatus) {
		this.outboundStatus = outboundStatus;
		this.session.put("outboundStatus", outboundStatus);
	}


	public Map<String, Long> getInboundStatus() {
		return inboundStatus;
	}


	public void setInboundStatus(Map<String, Long> inboundStatus) {
		this.inboundStatus = inboundStatus;
		this.session.put("inboundStatus", inboundStatus);
	}
	
	private Timestamp getCurrentTime(){
		java.util.Date 	str_date = Calendar.getInstance().getTime();
		java.sql.Timestamp timeStampDate = new Timestamp(str_date.getTime());
		return timeStampDate;
	}
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
	public String displayStatusMonitor(){
		
		try{
			Map<String,Long> outboundStatusTemp = new HashMap<String,Long>();
			Map<String,Long> inboundStatusTemp = new HashMap<String,Long>();
			paymentMessageService = (PaymentMessageService)ApplicationContextProvider.getBean("paymentMessageService");
			String userId = (String)session.get(WebConstants.CONSTANT_USER_NAME);
			UserLoginService userLoginService = (UserLoginService)ApplicationContextProvider.getBean("userLoginService");
			UserMaintenanceDTO userDto = userLoginService.getLogInTimeDetails(userId);
			Timestamp businessDay = userDto.getBusinessDay();
			// DateFormat df = new SimpleDateFormat("MM/dd/yy");  
			//String date11 = df.format(getCurrentTime());
			 setUserDate(getDateTime());
			 DateFormat df1 = new SimpleDateFormat("yyyy-MM-dd");
			  String currrDate = df1.format(businessDay);	
			 String cdate = dateChanger(currrDate);
			String Querydate = " lastModTime like (to_date('"+cdate+"' , 'dd/MM/yyyy'))";
			
			outboundStatusTemp = paymentMessageService.getStatusMonitor("O",Querydate);
			inboundStatusTemp = paymentMessageService.getStatusMonitor("I",Querydate);
			if(!inboundStatusTemp.isEmpty()){
				for (String status : inboundStatusTemp.keySet()) {
				inboundStatus.put(PaymentStatusEnum.findPaymentStatusDescriptionByCode(status), inboundStatusTemp.get(status));
				}
			}
			if(!outboundStatusTemp.isEmpty()){
				for (String status : outboundStatusTemp.keySet()) {
					outboundStatus.put(PaymentStatusEnum.findPaymentStatusDescriptionByCode(status), outboundStatusTemp.get(status));
				}
			}
			session.put("outboundStatus", outboundStatus);
			session.put("inboundStatus", inboundStatus);
			
			return "success";
		}catch (Exception e) {
			e.printStackTrace();
			return "input";
		}	
	}
	
	public String fetchStatusDataOnDate(){
		try{
			session.remove("outboundStatus");
			session.remove("inboundStatus");
			Map<String,Long> outboundStatusTemp = new HashMap<String,Long>();
			Map<String,Long> inboundStatusTemp = new HashMap<String,Long>();
			paymentMessageService = (PaymentMessageService)ApplicationContextProvider.getBean("paymentMessageService");
			Date date = getUserDate();
			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			String strDate = df.format(date);
			String Querydate = " lastModTime like (to_date('"+strDate+"' , 'dd/MM/yyyy'))";
			outboundStatusTemp = paymentMessageService.getStatusMonitor("O",Querydate);
			inboundStatusTemp = paymentMessageService.getStatusMonitor("I",Querydate);
			if(!inboundStatusTemp.isEmpty()){
				for (String status : inboundStatusTemp.keySet()) {
				inboundStatus.put(PaymentStatusEnum.findPaymentStatusDescriptionByCode(status), inboundStatusTemp.get(status));
				}
			}
			if(!outboundStatusTemp.isEmpty()){
					for (String status : outboundStatusTemp.keySet()) {
					outboundStatus.put(PaymentStatusEnum.findPaymentStatusDescriptionByCode(status), outboundStatusTemp.get(status));
				}
					
			}
			session.put("outboundStatus", outboundStatus);
			session.put("inboundStatus", inboundStatus);
			
			return "success";
		}catch (Exception e) {
			e.printStackTrace();
			return "input";
		}
	 }
	
	
	
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
	
	public Map<String, Object> getSession() {
		return session;
	}
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

}
