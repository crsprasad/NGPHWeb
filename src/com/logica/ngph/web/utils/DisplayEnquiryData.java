package com.logica.ngph.web.utils;

import java.math.BigDecimal;


import java.sql.Timestamp;
import java.util.Iterator;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;
import org.springframework.context.ApplicationContextException;

import com.logica.ngph.common.ConstantUtil;
import com.logica.ngph.service.EnquiryService;



public class DisplayEnquiryData implements SessionAware {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String msgType;
	private String msgChannel;
	private String msgRef;
	public String getMsgRef() {
		return msgRef;
	}
	public void setMsgRef(String msgRef) {
		this.msgRef = msgRef;
	}
	private String paymentStatus;
	private String msgDirection;
	private String txnReference;
	private String senderBank;
	private String receiverBank;
	private String msgCurrency;
	private BigDecimal msgAmount;
	private String msgValueDate;
	private String orderingCustomer;
	private String beneficiaryCustomer;
	private String narration;
	private String lastModTime;
	private String beneficiaryAccountNo;
	private String comments;
	
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public String getLastModTime() {
		return lastModTime;
	}
	public void setLastModTime(String lastModTime) {
		this.lastModTime = lastModTime;
	}
	public String getBeneficiaryAccountNo() {
		return beneficiaryAccountNo;
	}
	public void setBeneficiaryAccountNo(String beneficiaryAccountNo) {
		this.beneficiaryAccountNo = beneficiaryAccountNo;
	}
	private Map<String, Object> session ;

	public Map<String, Object> getSession() {
		return session;
	}

	
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
	public String getMsgType() {
		return msgType;
	}
	public void setMsgType(String msgType) {
		System.out.println(msgType);
		
		this.msgType = msgType;
		/*this.session.put("msgTypeDisplayMessage", msgType);*/
		
	}
	public String getMsgChannel() {
		return msgChannel;
	}
	public void setMsgChannel(String msgChannel) {
		this.msgChannel = msgChannel;
	}
	public String getPaymentStatus() {
		return paymentStatus;
	}
	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}
	public String getMsgDirection() {
		return msgDirection;
	}
	public void setMsgDirection(String msgDirection) {
		this.msgDirection = msgDirection;
	}
	public String getTxnReference() {
		return txnReference;
	}
	public void setTxnReference(String txnReference) {
		this.txnReference = txnReference;
	}
	public String getSenderBank() {
		return senderBank;
	}
	public void setSenderBank(String senderBank) {
		this.senderBank = senderBank;
	}
	public String getReceiverBank() {
		return receiverBank;
	}
	public void setReceiverBank(String receiverBank) {
		this.receiverBank = receiverBank;
	}
	public String getMsgCurrency() {
		return msgCurrency;
	}
	public void setMsgCurrency(String msgCurrency) {
		this.msgCurrency = msgCurrency;
	}
	public BigDecimal getMsgAmount() {
		return msgAmount;
	}
	public void setMsgAmount(BigDecimal msgAmount) {
		this.msgAmount = msgAmount;
	}
	public String getMsgValueDate() {
		return msgValueDate;
	}
	public void setMsgValueDate(String msgValueDate) {
		this.msgValueDate = msgValueDate;
	}
	public String getOrderingCustomer() {
		return orderingCustomer;
	}
	public void setOrderingCustomer(String orderingCustomer) {
		this.orderingCustomer = orderingCustomer;
	}
	public String getBeneficiaryCustomer() {
		return beneficiaryCustomer;
	}
	public void setBeneficiaryCustomer(String beneficiaryCustomer) {
		this.beneficiaryCustomer = beneficiaryCustomer;
	}
	public String getNarration() {
		return narration;
	}
	public void setNarration(String narration) {
		this.narration = narration;
	}

public String loadData() throws Exception {
	System.out.println(getMsgType());
	setMsgType(getMsgType());
	
	Iterator entries1 = session.entrySet().iterator();
	while (entries1.hasNext())
	{
		Map.Entry entry = (Map.Entry) entries1.next();
		Object key = (Object)entry.getKey();
		if(key.equals("display")){
		DisplayEnquiryData value = (DisplayEnquiryData)entry.getValue();
		setBeneficiaryCustomer(value.getBeneficiaryCustomer());
		setMsgAmount(value.getMsgAmount());
		setMsgChannel(value.getMsgChannel());
		setMsgCurrency(value.getMsgCurrency());
		setMsgDirection(value.getMsgDirection());
		setMsgType(value.getMsgType());
		setMsgValueDate(value.getMsgValueDate());
		setNarration(value.getNarration());
		setOrderingCustomer(value.getOrderingCustomer());
		setPaymentStatus(value.getPaymentStatus());
		setReceiverBank(value.getReceiverBank());
		setSenderBank(value.getSenderBank());
		setTxnReference(value.getTxnReference());
		setLastModTime(value.getLastModTime());
		setBeneficiaryAccountNo(value.getBeneficiaryAccountNo());
		setComments(value.getComments());
		EnquiryService enquiryService = (EnquiryService)ApplicationContextProvider.getBean("enquiryService");
		setMsgRef(enquiryService.getRawMessage(value.getMsgRef()));
		
		
		}
	}
	return "success";
}


}
