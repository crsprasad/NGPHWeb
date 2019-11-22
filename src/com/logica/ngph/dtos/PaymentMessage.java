package com.logica.ngph.dtos;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class PaymentMessage {
	
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
	private String errorCode;
	private String txnType;
	private String lastModTime;
	private String beneficiaryAccountNo;
	private String srcMSGType;
	private String srcSubMsgType;
	private String srcChannel;
	private String comments;
	
	//private String chkboxstatus;
	
	

	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public String getSrcChannel() {
		return srcChannel;
	}
	public void setSrcChannel(String srcChannel) {
		this.srcChannel = srcChannel;
	}
	public String getSrcSubMsgType() {
		return srcSubMsgType;
	}
	public void setSrcSubMsgType(String srcSubMsgType) {
		this.srcSubMsgType = srcSubMsgType;
	}
	public String getSrcMSGType() {
		return srcMSGType;
	}
	public void setSrcMSGType(String srcMSGType) {
		this.srcMSGType = srcMSGType;
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
	public String getTxnType() {
		return txnType;
	}
	public void setTxnType(String txnType) {
		this.txnType = txnType;
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
	public String getMsgType() {
		return msgType;
	}
	public void setMsgType(String msgType) {
		this.msgType = msgType;
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
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	

}
