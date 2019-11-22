package com.logica.ngph.web.action;

import java.util.ArrayList;
import java.util.List;

public class PaymentMainAction {
	
	private String msgtype;
	private String msgSubtype;
	private String msgSource;
	private List<String> msgTypeList = new ArrayList<String>();
	private List<String> msgsubTypeList = new ArrayList<String>();
	private List<String> msgSourceList = new ArrayList<String>();
	
	public String performPaymentMain(){
		
		
		
		
		
		
		
		return "populatePaymentMain";
	}

	public String getMsgtype() {
		return msgtype;
	}

	public void setMsgtype(String msgtype) {
		this.msgtype = msgtype;
	}

	public String getMsgSubtype() {
		return msgSubtype;
	}

	public void setMsgSubtype(String msgSubtype) {
		this.msgSubtype = msgSubtype;
	}

	public String getMsgSource() {
		return msgSource;
	}

	public void setMsgSource(String msgSource) {
		this.msgSource = msgSource;
	}

	public List<String> getMsgTypeList() {
		return msgTypeList;
	}

	public void setMsgTypeList(List<String> msgTypeList) {
		this.msgTypeList = msgTypeList;
	}

	public List<String> getMsgsubTypeList() {
		return msgsubTypeList;
	}

	public void setMsgsubTypeList(List<String> msgsubTypeList) {
		this.msgsubTypeList = msgsubTypeList;
	}

	public List<String> getMsgSourceList() {
		return msgSourceList;
	}

	public void setMsgSourceList(List<String> msgSourceList) {
		this.msgSourceList = msgSourceList;
	}
	

}
