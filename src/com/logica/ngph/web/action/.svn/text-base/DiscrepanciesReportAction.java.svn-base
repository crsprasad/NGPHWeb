package com.logica.ngph.web.action;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.springframework.context.ApplicationContextException;

import com.logica.ngph.Entity.DiscrepanciesReport;
import com.logica.ngph.Entity.RRNDiscrepancy;
import com.logica.ngph.service.EnquiryService;
import com.logica.ngph.web.utils.ApplicationContextProvider;
import com.logica.ngph.web.utils.ChangeDate;
import com.logica.ngph.web.utils.GenerateDiscripancyReports;
import com.opensymphony.xwork2.ActionSupport;

public class DiscrepanciesReportAction extends ActionSupport implements SessionAware{

	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(DiscrepanciesReportAction.class);
	private Map<String, Object> session;
	private String date;
	private int ob_Npci_Count;
	private int ob_Qng_Count;
	private int ob_Diff_Qng;
	private int ob_Diff_Npci;
	private int ib_Npci_Count;
	private int ib_Qng_Count;
	private int ii_Diff_Qng;
	private int ib_Diff_Npci;
	public int getOb_Npci_Count() {
		return ob_Npci_Count;
	}

	public void setOb_Npci_Count(int ob_Npci_Count) {
		this.ob_Npci_Count = ob_Npci_Count;
	}

	public int getOb_Qng_Count() {
		return ob_Qng_Count;
	}

	public void setOb_Qng_Count(int ob_Qng_Count) {
		this.ob_Qng_Count = ob_Qng_Count;
	}

	public int getOb_Diff_Qng() {
		return ob_Diff_Qng;
	}

	public void setOb_Diff_Qng(int ob_Diff_Qng) {
		this.ob_Diff_Qng = ob_Diff_Qng;
	}

	public int getOb_Diff_Npci() {
		return ob_Diff_Npci;
	}

	public void setOb_Diff_Npci(int ob_Diff_Npci) {
		this.ob_Diff_Npci = ob_Diff_Npci;
	}

	public int getIb_Npci_Count() {
		return ib_Npci_Count;
	}

	public void setIb_Npci_Count(int ib_Npci_Count) {
		this.ib_Npci_Count = ib_Npci_Count;
	}

	public int getIb_Qng_Count() {
		return ib_Qng_Count;
	}

	public void setIb_Qng_Count(int ib_Qng_Count) {
		this.ib_Qng_Count = ib_Qng_Count;
	}

	public int getIi_Diff_Qng() {
		return ii_Diff_Qng;
	}

	public void setIi_Diff_Qng(int ii_Diff_Qng) {
		this.ii_Diff_Qng = ii_Diff_Qng;
	}

	public int getIb_Diff_Npci() {
		return ib_Diff_Npci;
	}

	public void setIb_Diff_Npci(int ib_Diff_Npci) {
		this.ib_Diff_Npci = ib_Diff_Npci;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Map<String, Object> getSession() {
		return session;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
	public String getReportsDetailsPage()
	{
		return "success";
	}
	private List<RRNDiscrepancy> rrpOutward = new ArrayList();
	private List<RRNDiscrepancy> rrpInward = new ArrayList();
	
	public List<RRNDiscrepancy> getRrpOutward() {
		return rrpOutward;
	}

	public void setRrpOutward(List<RRNDiscrepancy> rrpOutward) {
		this.rrpOutward = rrpOutward;
		this.session.put("rrpOutward", rrpOutward);
	}

	public List<RRNDiscrepancy> getRrpInward() {
		return rrpInward;
	}

	public void setRrpInward(List<RRNDiscrepancy> rrpInward) {
		this.rrpInward = rrpInward;
		this.session.put("rrpInward", rrpInward);
	}
	/**
	 * getNewGeneratorReportsDetails method generate new report if the report is not exist on a particular date.
	 * @return
	 */
	public String getNewGeneratorReportsDetails()
	{
		try{
			if(StringUtils.isNotBlank(getDate()) && StringUtils.isNotEmpty(getDate())){
			EnquiryService enquiryService = (EnquiryService)ApplicationContextProvider.getBean("enquiryService");
			String discripancyDate= new ChangeDate().dateChanger(getDate());
			Date generationDate = getGenerationDate(getDate());
			//System.out.println("generationDate :"+generationDate);
			
			String output = enquiryService.generateDiscrepanciesReport(generationDate);
			
		//	System.out.println("output :"+output);			
			
			setRrpOutward(enquiryService.getRRPOutwardList("O",discripancyDate));
			setRrpInward(enquiryService.getRRPOutwardList("I",discripancyDate));
			
			DiscrepanciesReport discrepanciesReport =  enquiryService.getDiscrepanciesReport(discripancyDate);
			if(discrepanciesReport!=null && !discrepanciesReport.equals(null)){
			setIb_Diff_Npci(discrepanciesReport.getIb_Diff_Npci());
			setIi_Diff_Qng(discrepanciesReport.getIi_Diff_Qng());
			setIb_Npci_Count(discrepanciesReport.getIb_Npci_Count());
			setIb_Qng_Count(discrepanciesReport.getIb_Qng_Count());
			setOb_Diff_Npci(discrepanciesReport.getIb_Diff_Npci());
			setOb_Diff_Qng(discrepanciesReport.getOb_Diff_Qng());
			setOb_Npci_Count(discrepanciesReport.getOb_Npci_Count());
			setOb_Qng_Count(discrepanciesReport.getOb_Qng_Count());
			}
			else
				addFieldError("date", "No Record Found For The Select Date");
			session.put("report", discrepanciesReport);
			return "input";
			}
			else{
				addFieldError("date", "Date Field Is Required");
				
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
	
	public Date getGenerationDate(String date){
		String dateString = date.substring(0, 10);
		return java.sql.Date.valueOf(dateString);
	}
	
	public String getReportsDetails()
	{
		try{
			if(StringUtils.isNotBlank(getDate()) && StringUtils.isNotEmpty(getDate())){
			EnquiryService enquiryService = (EnquiryService)ApplicationContextProvider.getBean("enquiryService");
			String discripancyDate= new ChangeDate().dateChanger(getDate());
			setRrpOutward(enquiryService.getRRPOutwardList("O",discripancyDate));
			setRrpInward(enquiryService.getRRPOutwardList("I",discripancyDate));
			
			DiscrepanciesReport discrepanciesReport =  enquiryService.getDiscrepanciesReport(discripancyDate);
			if(discrepanciesReport!=null && !discrepanciesReport.equals(null)){
			setIb_Diff_Npci(discrepanciesReport.getIb_Diff_Npci());
			setIi_Diff_Qng(discrepanciesReport.getIi_Diff_Qng());
			setIb_Npci_Count(discrepanciesReport.getIb_Npci_Count());
			setIb_Qng_Count(discrepanciesReport.getIb_Qng_Count());
			setOb_Diff_Npci(discrepanciesReport.getIb_Diff_Npci());
			setOb_Diff_Qng(discrepanciesReport.getOb_Diff_Qng());
			setOb_Npci_Count(discrepanciesReport.getOb_Npci_Count());
			setOb_Qng_Count(discrepanciesReport.getOb_Qng_Count());
			}
			else
				addFieldError("date", "No Record Found For The Select Date");
			session.put("report", discrepanciesReport);
			return "input";
			}
			else{
				addFieldError("date", "Date Field Is Required");
				
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
	
	@SkipValidation
	public String downloadReport(){
		
		
		try{
			String reportName ="Discrepanices Report";
			GenerateDiscripancyReports reportUtil = new GenerateDiscripancyReports();
			reportUtil.setReportName(reportName);
			reportUtil.generatePaymentSentReport((DiscrepanciesReport)session.get("report"), (List<RRNDiscrepancy>)session.get("rrpOutward"),(List<RRNDiscrepancy>)session.get("rrpInward"),getReportType());
			return "success";
		}catch(Exception ex){
			ex.printStackTrace();
			addActionError(ex.getMessage());
			
			return "input";
		}
		
		
	}
	private String reportType;
	public String getReportType() {
		return reportType;
	}

	public void setReportType(String reportType) {
		this.reportType = reportType;
		this.session.put("reportType", reportType);
	}
	public void validate()
	{}	

}
