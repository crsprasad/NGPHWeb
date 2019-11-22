package com.logica.ngph.web.action;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.springframework.context.ApplicationContextException;


import com.logica.ngph.Entity.BgMast;
import com.logica.ngph.Entity.LcMast;
import com.logica.ngph.dtos.AccountDto;
import com.logica.ngph.service.LcReportsService;
import com.logica.ngph.service.MMIDReportService;
import com.logica.ngph.web.utils.ApplicationContextProvider;
import com.logica.ngph.web.utils.GenerateBgReportUtil;
import com.logica.ngph.web.utils.MMIDReportGenerationUtil;
import com.opensymphony.xwork2.ActionSupport;

public class MMIDReportAction extends ActionSupport implements SessionAware{
	private Logger logger = Logger.getLogger(MMIDReportAction.class);
	private static final long serialVersionUID = 1L;
	private Map<String, Object> session;
	private String accountNo;
	private String reportType;
	private List <AccountDto> reportList;
	private List <AccountDto> AccountreportList;
	
	private  Map<String, List<AccountDto>> displayaccNumVsAccDto;


	public  Map<String, List<AccountDto>> getDisplayaccNumVsAccDto() {
		return displayaccNumVsAccDto;
	}


	public  void setDisplayaccNumVsAccDto(
			Map<String, List<AccountDto>> displayaccNumVsAccDto) {
		this.displayaccNumVsAccDto = displayaccNumVsAccDto;
		this.session.put("displayaccNumVsAccDto", displayaccNumVsAccDto);
		
	}


	public List<AccountDto> getAccountreportList() {
		return AccountreportList;
	}


	public void setAccountreportList(List<AccountDto> accountreportList) {
		AccountreportList = accountreportList;
	}


	public List<AccountDto> getReportList() {
		return reportList;
	}


	public void setReportList(List<AccountDto> reportList) {
		this.reportList = reportList;
	}


	public String getReportType() {
		return reportType;
	}


	public void setReportType(String reportType) {
		this.reportType = reportType;
	}


	public String getAccountNo() {
		return accountNo;
	}


	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public String generateReport()
	{
		try{
			
			System.out.println(" accountNo= "+ this.getAccountNo());
			MMIDReportService mmidReportService=(MMIDReportService) ApplicationContextProvider.getBean("mmidReportService");
			
			setAccountreportList(mmidReportService.getAccountDetails(this.getAccountNo()));
			
			Map<String, List<AccountDto>> displayaccNumVsAccDto = new LinkedHashMap<String, List<AccountDto>>();
			
			for (AccountDto accountDto : getAccountreportList()) {
				if ( !displayaccNumVsAccDto.containsKey(accountDto.getAccoutNo()) ) {
					List<AccountDto> accDtoSubList = new ArrayList<AccountDto>();
					accDtoSubList.add(accountDto);
					displayaccNumVsAccDto.put(accountDto.getAccoutNo(), accDtoSubList);
				} else {
					displayaccNumVsAccDto.get(accountDto.getAccoutNo()).add(accountDto);
				}
			}
			session.put("displayaccNumVsAccDto", displayaccNumVsAccDto);
			
			session.put("searchList", getAccountreportList());
			setReportList(getAccountreportList());
			
			System.out.println(" generate report");
			
			
		
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
	
	@SkipValidation
	public String downloadReport(){
		
		String reportName ="MMID Report";
	
		
		Map<String,String> columnMap = new HashMap<String,String>();
//		for(Map.Entry<String, Object> entry:session.entrySet()){
//			if(entry.getValue().equals("Required")){
//				columnMap.put(entry.getKey(), (String)entry.getValue());
//			}
//		}
		columnMap.put("accoutNo","Required");
		columnMap.put("accountName","Required");
		columnMap.put("MMID","Required");
		columnMap.put("mobileNo","Required");
		System.out.println("Report Type ::" +getReportType());
		if(getReportType().equalsIgnoreCase("PDF")){
			if(columnMap.size()>13){
				addActionError("PDF file can accommodate  upto 6 columns.");
				setReportList((List<AccountDto>)session.get("searchList"));
				return "input";
			}
		}
		try{
			MMIDReportGenerationUtil reportUtil = new MMIDReportGenerationUtil();
			//reportUtil.setServletRequest(servletRequest);
			reportUtil.setReportName(reportName);
			
			List<AccountDto> accDtoList = (List<AccountDto>) session.get("searchList");
			Map<String, List<AccountDto>> accNumVsAccDto = new LinkedHashMap<String, List<AccountDto>>();
			
			for (AccountDto accountDto : accDtoList) {
				if ( !accNumVsAccDto.containsKey(accountDto.getAccoutNo()) ) {
					List<AccountDto> accDtoSubList = new ArrayList<AccountDto>();
					accDtoSubList.add(accountDto);
					accNumVsAccDto.put(accountDto.getAccoutNo(), accDtoSubList);
				} else {
					accNumVsAccDto.get(accountDto.getAccoutNo()).add(accountDto);
				}
			}			
			
			
			/*for (String accountNumber : accNumVsAccDto.keySet()) {
				System.out.println("========================================================");
				System.out.println("AccountNumber = " + accountNumber);
				System.out.println("========================================================");
				List<AccountDto> accDtoSubList = accNumVsAccDto.get(accountNumber);
				for (AccountDto accountDto : accDtoSubList) {
					System.out.println("MMID: " + accountDto.getMMID() + "  Mobile: " + accountDto.getMobileNo());
				}
			}*/
			
			
			
			/*
			List addlist = new ArrayList();
			for(int i = 0 ; i< getListData.size();i++){
				AccountDto accDto = (AccountDto)getListData.get(i);
				String accnokey = accDto.getAccoutNo()+":"+accDto.getAccountName();			
				String dataValues = accDto.getMMID() +":"+accDto.getMobileNo();
				
					
			
				
			}*/
			
			
			
			
			
			reportUtil.generateMMIDReport(accNumVsAccDto, columnMap, getReportType());
		}catch(Exception ex){
			ex.printStackTrace();
			addActionError(ex.getMessage());
			setReportList((List<AccountDto>)session.get("searchList"));
			return "input";
		}
		return null;
		
	}
	
	

	public String displayMmidReportScreen(){
		try{
			
			return "success";
		}catch (Exception e) {
			e.printStackTrace();
			return "input";
		}	
	}
	
	
	public Map<String, Object> getSession() {
		return session;
	}
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
}
