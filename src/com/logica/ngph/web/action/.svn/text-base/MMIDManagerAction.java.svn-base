package com.logica.ngph.web.action;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.springframework.context.ApplicationContextException;

import com.logica.ngph.common.NGPHException;
import com.logica.ngph.dtos.MMIDManagerDto;
import com.logica.ngph.service.AccountMaintenaceService;
import com.logica.ngph.service.MMIDManagerService;
import com.logica.ngph.serviceImpl.MMIDManagerServiceImpl;
import com.logica.ngph.web.utils.ApplicationContextProvider;
import com.opensymphony.xwork2.ActionSupport;

public class MMIDManagerAction extends ActionSupport implements SessionAware
{

	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(UserMaintenanceAction.class);
	private Map<String, Object> session;
	public Map<String, Object> getSession() {
		return session;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
	
	private String benificiaryMMID;
	private String accountNo;
	private String orderingAmount;
	private String benificiaryMobileNo;
	private String benificiaryIFSC;
	private String transactionType;
	private String fromAccType;
	private String toAccType;
	private String remarks;
	private String beneficaryAccountNo;
	private List searchList;
	
	public List getSearchList() {
		return searchList;
	}
	public void setSearchList(List searchList) {
		this.searchList = searchList;
	}
	public String getBeneficaryAccountNo() {
		return beneficaryAccountNo;
	}
	public void setBeneficaryAccountNo(String beneficaryAccountNo) {
		this.beneficaryAccountNo = beneficaryAccountNo;
	}
	public String getToAccType() {
		return toAccType;
	}
	public void setToAccType(String toAccType) {
		this.toAccType = toAccType;
	}
	public String getTransactionType() {
		return transactionType;
	}
	public String getFromAccType() {
		return fromAccType;
	}
	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}
	public void setFromAccType(String fromAccType) {
		this.fromAccType = fromAccType;
	}
	public String getBenificiaryIFSC() {
		return benificiaryIFSC;
	}
	public void setBenificiaryIFSC(String benificiaryIFSC) {
		this.benificiaryIFSC = benificiaryIFSC;
	}
	public String getBenificiaryMMID() {
		return benificiaryMMID;
	}
	public void setBenificiaryMMID(String benificiaryMMID) {
		this.benificiaryMMID = benificiaryMMID;
	}
	public String getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}
	public String getOrderingAmount() {
		return orderingAmount;
	}
	public void setOrderingAmount(String orderingAmount) {
		this.orderingAmount = orderingAmount;
	}
	public String getBenificiaryMobileNo() {
		return benificiaryMobileNo;
	}
	public void setBenificiaryMobileNo(String benificiaryMobileNo) {
		this.benificiaryMobileNo = benificiaryMobileNo;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	//For displaying the jsp
	@SkipValidation
	public String initalActionMMID()
	{
		return "success";
	}

	public String insertData() throws NGPHException
	{
		AccountMaintenaceService accountMaintenaceService = (AccountMaintenaceService)ApplicationContextProvider.getBean("accountMaintenanceService");
		String accountNoAllready=accountMaintenaceService.accountNOAllreadyExit(getAccountNo());
		try{
			if(accountNoAllready.equals("Available")){
				MMIDManagerService service = (MMIDManagerService) ApplicationContextProvider.getBean("mmidManagerService");
				
				MMIDManagerDto mmidManagerDto = new MMIDManagerDto();
				
				mmidManagerDto.setAccountNo(getAccountNo());
				mmidManagerDto.setBenificiaryMMID(getBenificiaryMMID());
				mmidManagerDto.setBenificiaryMobileNo(getBenificiaryMobileNo());
				mmidManagerDto.setOrderingAmount(new BigDecimal(getOrderingAmount()));
				mmidManagerDto.setRemarks(getRemarks());
				mmidManagerDto.setBenificiaryIFSC(getBenificiaryIFSC());
				mmidManagerDto.setTransactionType(getTransactionType());
				mmidManagerDto.setFromAccType(getFromAccType());
				mmidManagerDto.setToAccType(getToAccType());
				mmidManagerDto.setBeneficaryAccountNo(getBeneficaryAccountNo());
				
				service.populateMMIDData(mmidManagerDto);
				return "success";
			}
			else
				addFieldError(accountNo, "Account Number Is Not Avialable In Database");
				
			
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
	addActionError("Unable To Process");
		return "input";
	}
	private String searchAction;
	public String getSearchAction() {
		return searchAction;
	}
	public void setSearchAction(String searchAction) {
		this.searchAction = searchAction;
	}
	@SkipValidation
	public String getSearchData(){
		
		try {
			
			AccountMaintenaceService accountMaintenaceService = (AccountMaintenaceService)ApplicationContextProvider.getBean("accountMaintenanceService");
			setSearchList(accountMaintenaceService.benificaryAccountSearch(getAccountNo()));
		} catch (NGPHException ngphException) {
			AuditServiceUtil.logNgphException(ngphException,logger);
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
	
		return "populateData";
}
	public void validate()
	{
		if(StringUtils.isNotEmpty(getTransactionType()))
		{
			if(getTransactionType().equals("48"))
			{
				if(StringUtils.isBlank(getBenificiaryIFSC())|| StringUtils.isBlank(getBeneficaryAccountNo())) 
						{
							addFieldError("benificiaryIFSC", "benificiaryIFSC And BeneficaryAccountNo Both Are Required");
						}
				else if(StringUtils.isNotBlank(getBenificiaryMMID())|| StringUtils.isNotBlank(getBenificiaryMobileNo())) 
						{
							addFieldError("benificiaryIFSC", "BenificiaryMMID And BenificiaryMobileNo Both Are Not Required");
						}
				
				
			}
			else if(getTransactionType().equals("45") || getTransactionType().equals("47")){
				if(StringUtils.isBlank(getBenificiaryMMID())|| StringUtils.isBlank(getBenificiaryMobileNo())) 
				{
					addFieldError("benificiaryIFSC", "BenificiaryMMID And BenificiaryMobileNo Both Are Required");
				}else if(StringUtils.isNotBlank(getBenificiaryIFSC())|| StringUtils.isNotBlank(getBeneficaryAccountNo())) 
				{
					addFieldError("benificiaryIFSC", "benificiaryIFSC And BeneficaryAccountNo Both Are Not Required");
				}
		
				
			}
		}
	}

}
