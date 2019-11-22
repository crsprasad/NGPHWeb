/**
 * 
 */
package com.logica.ngph.web.action;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.interceptor.validation.SkipValidation;

import com.logica.ngph.common.ConstantUtil;
import com.logica.ngph.dtos.PartyDTO;
import com.logica.ngph.service.EnquiryService;
import com.logica.ngph.service.LetterOfCreditService;
import com.logica.ngph.web.utils.ApplicationContextProvider;
import com.opensymphony.xwork2.ActionSupport;

/**
 * @author chakkar
 *
 */
public class IFSCSearchAction extends ActionSupport implements SessionAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String bankName;
	private String stateName;
	private String districtName;
	private String cityName;
	private String branchName;
	private String ifscCode;
	private List<PartyDTO> bankNameList;
	private List<PartyDTO> branchNameList;
	private List<PartyDTO> ifscCodeList;
	private List<String> stateNameDropDown;
	private List<String> districtNameDropDown;
	private List<String> cityNameDropDown;
	private Map<String, Object> session;
	private String saveAction;
	private List<PartyDTO> IfscListData;
	
	
	
	
	
	public List<PartyDTO> getIfscCodeList() {
		return ifscCodeList;
	}

	public void setIfscCodeList(List<PartyDTO> ifscCodeList) {
		this.ifscCodeList = ifscCodeList;
	}

	public List<PartyDTO> getBranchNameList() {
		return branchNameList;
	}

	public void setBranchNameList(List<PartyDTO> branchNameList) {
		this.branchNameList = branchNameList;
	}

	public String getSaveAction() {
		return saveAction;
	}

	public void setSaveAction(String saveAction) {
		this.saveAction = saveAction;
	}

	public Map<String, Object> getSession() {
		return session;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getStateName() {
		return stateName;
	}
	public void setStateName(String stateName) {
		this.stateName = stateName;
	}
	public String getDistrictName() {
		return districtName;
	}
	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public String getBranchName() {
		return branchName;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	public String getIfscCode() {
		return ifscCode;
	}
	public void setIfscCode(String ifscCode) {
		this.ifscCode = ifscCode;
	}
	public List<PartyDTO> getBankNameList() {
		return bankNameList;
	}
	public void setBankNameList(List<PartyDTO> bankNameList) {
		this.bankNameList = bankNameList;
	}
	public List<String> getStateNameDropDown() {
		return stateNameDropDown;
	}
	public void setStateNameDropDown(List<String> stateNameDropDown) {
		this.stateNameDropDown = stateNameDropDown;
		stateNameDropDown.removeAll(Collections.singleton(null)); 
		this.session.put("stateNameDropDown",stateNameDropDown);
	}
	public List<String> getDistrictNameDropDown() {
		return districtNameDropDown;
	}
	public void setDistrictNameDropDown(List<String> districtNameDropDown) {
		this.districtNameDropDown = districtNameDropDown;
		
	}
	public List<String> getCityNameDropDown() {
		return cityNameDropDown;
	}
	public void setCityNameDropDown(List<String> cityNameDropDown) {
		this.cityNameDropDown = cityNameDropDown;
	}
	

	public String getIFSCSearchList() throws Exception
	{	
		EnquiryService enquiryService = (EnquiryService)ApplicationContextProvider.getBean("enquiryService");
		setStateNameDropDown(enquiryService.getEnquiryList(ConstantUtil.ENQUIRY_STATE_NAME));
		return "input";
	}
	
	@SkipValidation
	public String getBankNameListData()
	{
		try{
			LetterOfCreditService letterOfCreditService =  (LetterOfCreditService)  ApplicationContextProvider.getBean("letterOfCreditService");
			bankNameList = letterOfCreditService.getBankNameData(getBankName());
			session.put("bankNameList", bankNameList);
			return "success";
			
		}	catch (Exception e) {
			return "input";
		
		}
	
	}
	
	@SkipValidation
	public String fetchDistrictNames()throws Exception
	{
		try
		{
			EnquiryService enquiryService = (EnquiryService)ApplicationContextProvider.getBean("enquiryService");
			districtNameDropDown = enquiryService.getEnquiryDistrictName(getStateName());
			session.put("districtNameDropDown", districtNameDropDown);
			return "success";
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return "input";
	}
	
	@SkipValidation
	public String fetchCityNames()throws Exception
	{
		try
		{
			EnquiryService enquiryService = (EnquiryService)ApplicationContextProvider.getBean("enquiryService");
			cityNameDropDown = enquiryService.getEnquiryCityName(getStateName(), getDistrictName());
			session.put("cityNameDropDown", cityNameDropDown);
			return "success";
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return "input";
	}
	
	public String getIFSCDetailsData() throws Exception
	{	
		EnquiryService enquiryService = (EnquiryService)ApplicationContextProvider.getBean("enquiryService");
		IfscListData = enquiryService.getIFSCDetailsData(getBankName(),getStateName(),getDistrictName(),getCityName(),getIfscCode(),getBranchName());
		session.put("IfscListData", IfscListData);
		System.out.println("IfscListData size is "+IfscListData.size());
		return "input";
	}
	
	private void reset()
	{
		this.setBankName("");
		this.setStateName("");
		this.setDistrictName("");
		this.setCityName("");
		this.setBranchName("");
		this.setIfscCode("");
		districtNameDropDown = null;
		cityNameDropDown = null;
		IfscListData=null;
	}
	
	@SkipValidation
	public String resetFields() 
	{
		try{
		this.setBankName("");
		this.setStateName("");
		this.setDistrictName("");
		this.setCityName("");
		this.setBranchName("");
		this.setIfscCode("");
			return "success";
		}catch (Exception e) {
			return "input";
		}
	}
	
	@SkipValidation
	public String getBranchNameListData()
	{
		try{
			EnquiryService enquiryService = (EnquiryService)ApplicationContextProvider.getBean("enquiryService");
			branchNameList = enquiryService.getBranchNameData(getBranchName(),getStateName(),getDistrictName(),getCityName(),getBankName(),getIfscCode());
			session.put("branchNameList", branchNameList);
			return "success";
			
		}	catch (Exception e) {
			return "input";
		
		}
	
	}
	
	@SkipValidation
	public String getIFSCCodeListData()
	{
		try{
			EnquiryService enquiryService = (EnquiryService)ApplicationContextProvider.getBean("enquiryService");
			ifscCodeList = enquiryService.getIFSCCodeListData(getIfscCode(),getStateName(),getDistrictName(),getCityName(),getBankName(),getBranchName());
			session.put("ifscCodeList", ifscCodeList);
			return "success";
			
		}	catch (Exception e) {
			return "input";
		
		}
	
	}
	
	
	@SkipValidation
	public String getIFSCCodeList()
	{
		try{
			EnquiryService enquiryService = (EnquiryService)ApplicationContextProvider.getBean("enquiryService");
			ifscCodeList = enquiryService.getIFSCCodeList(getIfscCode());
			session.put("ifscCodeList", ifscCodeList);
			return "success";
			
		}	catch (Exception e) {
			return "input";
		
		}
	
	}
	
	
}
