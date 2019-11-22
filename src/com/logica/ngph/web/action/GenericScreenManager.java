package com.logica.ngph.web.action;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.springframework.context.ApplicationContextException;

import com.logica.ngph.common.ConstantUtil;
import com.logica.ngph.dtos.AddressDto;
import com.logica.ngph.dtos.GenericManagerDto;
import com.logica.ngph.dtos.TemplateDto;
import com.logica.ngph.service.AdviceLCPaymentService;
import com.logica.ngph.service.EnquiryService;
import com.logica.ngph.service.GenericManagerService;
import com.logica.ngph.service.PaymentSubmittedReportService;
import com.logica.ngph.web.utils.ApplicationContextProvider;
import com.logica.ngph.web.utils.WebConstants;
import com.opensymphony.xwork2.ActionSupport;

public class GenericScreenManager extends ActionSupport implements SessionAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String screenAppender;
	private List<GenericManagerDto> screenValue;
	private List<TemplateDto> tempScreenValue;
	private List<TemplateDto> tempSearchList;
	private Map<String, Object> session ;
	private String msgType;
	private String tempName;
	private List<String> msgTypeDropDown;
	private String msgValue;
	
	
	
	
	
	/**
	 * @return the msgValue
	 */
	public String getMsgValue() {
		return msgValue;
	}
	/**
	 * @param msgValue the msgValue to set
	 */
	public void setMsgValue(String msgValue) {
		this.msgValue = msgValue;
	}
	/**
	 * @return the tempSearchList
	 */
	public List<TemplateDto> getTempSearchList() {
		return tempSearchList;
	}
	/**
	 * @param tempSearchList the tempSearchList to set
	 */
	public void setTempSearchList(List<TemplateDto> tempSearchList) {
		this.tempSearchList = tempSearchList;
	}
	public List<String> getMsgTypeDropDown() {
		return msgTypeDropDown;
	}
	public void setMsgTypeDropDown(List<String> msgTypeDropDown) {
		this.msgTypeDropDown = msgTypeDropDown;
		this.session.put("msgTypeDropDown",msgTypeDropDown);
	}
	/**
	 * @return the msgType
	 */
	public String getMsgType() {
		return msgType;
	}
	/**
	 * @param msgType the msgType to set
	 */
	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}
	/**
	 * @return the tempName
	 */
	public String getTempName() {
		return tempName;
	}
	/**
	 * @param tempName the tempName to set
	 */
	public void setTempName(String tempName) {
		this.tempName = tempName;
	}
	public Map<String, Object> getSession() {
		return session;
	}
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
	
	public String getScreenAppender() {
		return screenAppender;
	}
	public void setScreenAppender(String screenAppender) {
		this.screenAppender = screenAppender;
		
		
	}
	
	public String fetchData() throws SQLException
	{
		try{
		String userId = (String)session.get(WebConstants.CONSTANT_USER_NAME);
		GenericManagerService genericManagerService = (GenericManagerService)ApplicationContextProvider.getBean("genericManagerService");
		setScreenValue(genericManagerService.fetchData(userId.toUpperCase()));
		
		return "success";
		}
		catch (Exception e) {
			return "input";
		}
	}
	
	public List<GenericManagerDto> getScreenValue() {
		return screenValue;
	}
	
	public void setScreenValue(List<GenericManagerDto> screenValue) {
		this.screenValue = screenValue;
		this.session.put("screenValue", screenValue);
	}
	
	public String fetchTempateData() throws SQLException
	{
		try{
		String userId = (String)session.get(WebConstants.CONSTANT_USER_NAME);
		GenericManagerService genericManagerService = (GenericManagerService)ApplicationContextProvider.getBean("genericManagerService");
		tempScreenValue = genericManagerService.fetchTempateData(userId,getMsgType(),getTempName());
		session.put("tempScreenValue", tempScreenValue);
		
		return "success";
		}
		catch (Exception e) {
			return "input";
		}
	}
	
	public String fetchTempat() throws Exception
	{
		try{
			EnquiryService enquiryService = (EnquiryService)ApplicationContextProvider.getBean("enquiryService");
			setMsgTypeDropDown(enquiryService.getEnquiryList(ConstantUtil.ENQUIRY_MSG_TYPE));
			return "success";
		}
		catch (Exception e) {
			return "input";
		}
	}
	

	@SkipValidation
	public String getSearchData()
	{
		try{
			GenericManagerService genericManagerService = (GenericManagerService)ApplicationContextProvider.getBean("genericManagerService");
			tempSearchList = genericManagerService.getTempNameSearchData(getTempName(),getMsgValue());
			session.put("tempSearchList", tempSearchList);
		
			return "success";
			
		}	catch (Exception e) {
			return "input";
		
		}
	
	}
	/**
	 * @return the tempScreenValue
	 */
	public List<TemplateDto> getTempScreenValue() {
		return tempScreenValue;
	}
	/**
	 * @param tempScreenValue the tempScreenValue to set
	 */
	public void setTempScreenValue(List<TemplateDto> tempScreenValue) {
		this.tempScreenValue = tempScreenValue;
	}
		
	private void reset()
	{
		this.setMsgType("");
		this.setTempName("");	
	}
	
	/**
	 * This method is for getting which all are messages are rejected by user 
	 * @return
	 * @throws Exception
	 */
	public String fetchRejectedData() throws SQLException
	{
		try{
		String userId = (String)session.get(WebConstants.CONSTANT_USER_NAME);
		GenericManagerService genericManagerService = (GenericManagerService)ApplicationContextProvider.getBean("genericManagerService");
		setScreenValue(genericManagerService.fetchRejectedData(userId));
		
		return "success";
		}
		catch (Exception e) {
			return "input";
		}
	}
	
	
	/**
	 * This method is for getting which all are messages are created by user 
	 * @return
	 * @throws Exception
	 */
	public String fetchpendingAuthorizationOutboundData() throws SQLException
	{
		try{
		String userId = (String)session.get(WebConstants.CONSTANT_USER_NAME);
		GenericManagerService genericManagerService = (GenericManagerService)ApplicationContextProvider.getBean("genericManagerService");
		setScreenValue(genericManagerService.fetchPendingAuthorizationData(userId));
		
		return "success";
		}
		catch (Exception e) {
			return "input";
		}
	}
	

}
