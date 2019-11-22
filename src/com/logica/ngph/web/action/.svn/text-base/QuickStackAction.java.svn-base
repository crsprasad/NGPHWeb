package com.logica.ngph.web.action;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextException;
import org.springframework.context.support.ClassPathXmlApplicationContext;


import com.logica.ngph.common.ConstantUtil;
import com.logica.ngph.common.NGPHException;
import com.logica.ngph.service.EnquiryService;
import com.logica.ngph.service.QuickStacksService;
import com.opensymphony.xwork2.ActionSupport;

public class QuickStackAction extends ActionSupport 
{
	private static final long serialVersionUID = -3364264106258064077L;
	static Logger logger = Logger.getLogger(QuickStackAction.class);
	private String InboundMessage;
	private String OutboundMessage;
	
	private String authorizedmessge;
	private String recivedathorization;
	private String authorizedmessgeOut;
	private String recivedathorizationOut;
	private String sendToHost;
	private String releasedState;
	
	public String getSendToHost() {
		return sendToHost;
	}
	public void setSendToHost(String sendToHost) {
		this.sendToHost = sendToHost;
	}
	public String getReleasedState() {
		return releasedState;
	}
	public void setReleasedState(String releasedState) {
		this.releasedState = releasedState;
	}
	public String getAuthorizedmessgeOut() {
		return authorizedmessgeOut;
	}
	public void setAuthorizedmessgeOut(String authorizedmessgeOut) {
		this.authorizedmessgeOut = authorizedmessgeOut;
	}
	public String getRecivedathorizationOut() {
		return recivedathorizationOut;
	}
	public void setRecivedathorizationOut(String recivedathorizationOut) {
		this.recivedathorizationOut = recivedathorizationOut;
	}
	public String getAuthorizedmessge() {
		return authorizedmessge;
	}
	public void setAuthorizedmessge(String authorizedmessge) {
		this.authorizedmessge = authorizedmessge;
	}
	public String getRecivedathorization() {
		return recivedathorization;
	}
	public void setRecivedathorization(String recivedathorization) {
		this.recivedathorization = recivedathorization;
	}
	public String getOutboundMessage() {
		return OutboundMessage;
	}
	public void setOutboundMessage(String outboundMessage) {
		OutboundMessage = outboundMessage;
	}
	
	public void setInboundMessage(String inboundMessage) {
		InboundMessage = inboundMessage;
	}
	public String getInboundMessage() {
		return InboundMessage;
	}
	
	List<String> total;
	List<String> totalOut;
	public List<String> getTotalOut() {
		return totalOut;
	}
	public void setTotalOut(List<String> totalOut) {
		this.totalOut = totalOut;
	}
	public List<String> getTotal() {
		return total;
	}
	public void setTotal(List<String> total) {
		this.total = total;
	}
	public String getInboundcurrency() {
		return inboundcurrency;
	}
	public void setInboundcurrency(String inboundcurrency) {
		this.inboundcurrency = inboundcurrency;
	}
	public String getOutboundcurrency() {
		return outboundcurrency;
	}
	public void setOutboundcurrency(String outboundcurrency) {
		this.outboundcurrency = outboundcurrency;
	}

	private String inboundcurrency;
	private String outboundcurrency;
	private String channeldata;
	
	@SuppressWarnings("unchecked")
	public String getStackValues() throws NGPHException
	{
		QuickStacksService quickStacksService=getQuickStackService();
		String stackValueInbound=quickStacksService.getQuickStack(ConstantUtil.TOTALAMOUNT);
		String stackValueOutbound=quickStacksService.getQuickStack(ConstantUtil.AUTHORIZATIONMESSAGE);
		String stackValueChannel=quickStacksService.getQuickStack(ConstantUtil.RECIVEDAUTHORIZATION);
		setInboundcurrency(stackValueInbound);
		setOutboundcurrency(stackValueOutbound);
		setChanneldata(stackValueChannel);
		String [] temp = null;
	 	temp = stackValueInbound.split(",");
	 	
		setInboundMessage(temp[0]);
		setAuthorizedmessge(temp[1]);
		setRecivedathorization(temp[2]);
		setSendToHost(temp[3]);
		List  temprary=new ArrayList();
		int countIn=0;
		for(int i=4;i<temp.length;i++){
			
			temprary.add(temp[i].replace("=", " Transaction With Value Amount    "));
			countIn++;
			 }
		if(countIn==0){
			temprary.add("No CURRENCY TO DISPLAY");}
		
		setTotal(temprary);
		
		String [] tempOut = null;
		tempOut = stackValueOutbound.split(",");
	 	
		setOutboundMessage(tempOut[0]);
		setAuthorizedmessgeOut(tempOut[1]);
		setRecivedathorizationOut(tempOut[2]);
		setReleasedState(tempOut[3]);
		List  tempraryOut=new ArrayList();
		int count=0;
		for(int i=4;i<tempOut.length;i++){
			tempraryOut.add(tempOut[i].replace("=", " Transaction With Value Amount     "));
			count++;
			 }
		if(count==0)
		{
			tempraryOut.add("NO CURRENCY TO DISPLAY");
			
		}
		setTotalOut(tempraryOut);
		
		
		

		return "success";
		
	}
	private QuickStacksService getQuickStackService() {
		QuickStacksService quickStacksServiceService = null;
		try{
			ApplicationContext appcontext = new ClassPathXmlApplicationContext("WebApplicationContext.xml");
			
			quickStacksServiceService = (QuickStacksService)appcontext.getBean("quickStacksService");
		}catch (ApplicationContextException applicationContextException) {
		logger.debug(ConstantUtil.RULE_ACTION+ applicationContextException);	
		}
		
		return quickStacksServiceService;
	}
	public void setChanneldata(String channeldata) {
		this.channeldata = channeldata;
	}
	public String getChanneldata() {
		return channeldata;
	}
	

}
