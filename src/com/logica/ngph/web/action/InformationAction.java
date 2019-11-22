package com.logica.ngph.web.action;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.SessionAware;

import com.logica.ngph.dtos.InformationDto;
import com.logica.ngph.service.InformationService;
import com.logica.ngph.web.utils.ApplicationContextProvider;
import com.opensymphony.xwork2.ActionSupport;

public class InformationAction extends ActionSupport implements SessionAware
{
	private Logger logger = Logger.getLogger(InformationAction.class);
	private static final long serialVersionUID = 1L;
	private Map<String, Object> session;
	private String direction;
	private String infoMsg;
	private String msgDirection;
	private List<InformationDto> informationList;
	private String rawInformationMsg;
	private String msgRef;
	private String rawMsg;
	HttpServletRequest request;
	private String infoDirection;
	private String actionError;
	
	
	
	
	/**
	 * @return the actionError
	 */
	public String getActionError() {
		return actionError;
	}
	/**
	 * @param actionError the actionError to set
	 */
	public void setActionError(String actionError) {
		this.actionError = actionError;
	}
	/**
	 * @return the infoDirection
	 */
	public String getInfoDirection() {
		return infoDirection;
	}
	/**
	 * @param infoDirection the infoDirection to set
	 */
	public void setInfoDirection(String infoDirection) {
		this.infoDirection = infoDirection;
	}
	public String getMsgDirection() 
	{
		return msgDirection;
	}
	public void setMsgDirection(String msgDirection) {
		this.msgDirection = msgDirection;
	}
	public String getInfoMsg() 
	{
		return infoMsg;
	}
	public void setInfoMsg(String infoMsg) {
		this.infoMsg = infoMsg;
	}
	
	public String getDirection() {
		return direction;
	}
	public void setDirection(String direction) {
		this.direction = direction;
	}
	public List<InformationDto> getInformationList() {
		return informationList;
	}
	public void setInformationList(List<InformationDto> informationList) {
		this.informationList = informationList;
	}
	public String getMsgRef() {
		return msgRef;
	}
	public void setMsgRef(String msgRef) {
		this.msgRef = msgRef;
	}
	
	public String getRawMsg() {
		return rawMsg;
	}
	public void setRawMsg(String rawMsg) {
		this.rawMsg = rawMsg;
	}
	public String displayoutboundInfoScreen() throws IOException{
		InformationService informationService = (InformationService)ApplicationContextProvider.getBean("informationService");
		setDirection("O");
		setMsgDirection("Outbound Payment");
		informationList  = informationService.getReportDate(getDirection());	
		session.put("informationList", informationList);
		return "success";
	}
	
	public String displayInboundInfoScreen(){
		InformationService informationService = (InformationService)ApplicationContextProvider.getBean("informationService");
		setDirection("I");
		setMsgDirection("Inbound Payment");
		informationList  = informationService.getReportDate(getDirection());
		logger.info("Number of records are getting from DB for inward message ="+informationList.size());
		session.put("informationList", informationList);	
		return "INBOUND";
	}
	
	public String displayInformation(){
		System.out.println("Msg Ref is :: "+getMsgRef());
		return "success";
	}
	
	public String displayRawInformationMsg()
	{
		
		InformationService informationService = (InformationService)ApplicationContextProvider.getBean("informationService");
		rawInformationMsg = informationService.getRawInformationMsg(getMsgRef(),getInfoDirection());
		setInfoDirection(getInfoDirection());
		//session.put("rawMsg", rawInformationMsg);
		if(StringUtils.isNotEmpty(rawInformationMsg) && StringUtils.isNotBlank(rawInformationMsg))
		{
			setRawMsg(rawInformationMsg);
			return "success";
		}
		else
		{
			addActionError("Raw Information Message was not Found");
			setActionError("true");
			setRawMsg("");
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
