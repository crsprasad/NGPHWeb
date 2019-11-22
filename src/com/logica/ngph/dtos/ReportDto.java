package com.logica.ngph.dtos;

import java.math.BigDecimal;
import java.sql.Timestamp;



public class ReportDto {
	private String msgType;
	private String msgChannel;
	private String msgRef;
	private String paymentStatus;
	private String msgDirection;
	private String senderBank;
	private String receiverBank;
	private String msgCurrency;
	private BigDecimal msgAmount;
	private String msgValueDate;
	private String orderingCustomer;
	private String beneficiaryCustomer;
	private String MUR;
	private String sendertoreciverInfo;
	private String hostID;
	private String relatedRefrence;
	private String department;
	private String branch;
	private String txnType;
	private String beneficiaryAccount;
	private String orderingAccount;
	private String txnReference;
	private String lastModifiedDate;
	//Mizuho Start :: Added 
	private Timestamp issueDate;
	private String applicentBank;
	private Timestamp expDate;
	private Timestamp lastDateofShipment;
	private Timestamp amendmentDate;
	private BigDecimal noofAmendments;
	private String reducedAmtCurrCode;
	private BigDecimal reducedAmt;
	private String outstandAmtCurrCode;
	private BigDecimal outstandAmt;
	
	//Mizuho End :: Added 
	
	

	
	public Timestamp getIssueDate() {
		return issueDate;
	}
	public void setIssueDate(Timestamp issueDate) {
		this.issueDate = issueDate;
	}
	
	public String getApplicentBank() {
		return applicentBank;
	}
	public void setApplicentBank(String applicentBank) {
		this.applicentBank = applicentBank;
	}
	public Timestamp getExpDate() {
		return expDate;
	}
	public void setExpDate(Timestamp expDate) {
		this.expDate = expDate;
	}
	public Timestamp getLastDateofShipment() {
		return lastDateofShipment;
	}
	public void setLastDateofShipment(Timestamp lastDateofShipment) {
		this.lastDateofShipment = lastDateofShipment;
	}
	public Timestamp getAmendmentDate() {
		return amendmentDate;
	}
	public void setAmendmentDate(Timestamp amendmentDate) {
		this.amendmentDate = amendmentDate;
	}
	public BigDecimal getNoofAmendments() {
		return noofAmendments;
	}
	public void setNoofAmendments(BigDecimal noofAmendments) {
		this.noofAmendments = noofAmendments;
	}
	public String getReducedAmtCurrCode() {
		return reducedAmtCurrCode;
	}
	public void setReducedAmtCurrCode(String reducedAmtCurrCode) {
		this.reducedAmtCurrCode = reducedAmtCurrCode;
	}
	public BigDecimal getReducedAmt() {
		return reducedAmt;
	}
	public void setReducedAmt(BigDecimal reducedAmt) {
		this.reducedAmt = reducedAmt;
	}
	public String getOutstandAmtCurrCode() {
		return outstandAmtCurrCode;
	}
	public void setOutstandAmtCurrCode(String outstandAmtCurrCode) {
		this.outstandAmtCurrCode = outstandAmtCurrCode;
	}
	public BigDecimal getOutstandAmt() {
		return outstandAmt;
	}
	public void setOutstandAmt(BigDecimal outstandAmt) {
		this.outstandAmt = outstandAmt;
	}
	public String getLastModifiedDate() {
		return lastModifiedDate;
	}
	public void setLastModifiedDate(String lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}
	public String getTxnReference() {
		return txnReference;
	}
	public void setTxnReference(String txnReference) {
		this.txnReference = txnReference;
	}
	public String getOrderingAccount() {
		return orderingAccount;
	}
	public void setOrderingAccount(String orderingAccount) {
		this.orderingAccount = orderingAccount;
	}
	public String getBeneficiaryAccount() {
		return beneficiaryAccount;
	}
	public void setBeneficiaryAccount(String beneficiaryAccount) {
		this.beneficiaryAccount = beneficiaryAccount;
	}
	public String getTxnType() {
		return txnType;
	}
	public void setTxnType(String txnType) {
		this.txnType = txnType;
	}
	public String getRelatedRefrence() {
		return relatedRefrence;
	}
	public void setRelatedRefrence(String relatedRefrence) {
		this.relatedRefrence = relatedRefrence;
		System.out.println("relatedRefrence"+relatedRefrence);
	}
	
	
	public String getMsgType() {
		return msgType;
	}
	public void setMsgType(String msgType) {
		this.msgType = msgType;
		System.out.println("msgType"+msgType);
	}
	public String getMsgChannel() {
		return msgChannel;
	}
	@Override
	public String toString() {
		
		
		return "ReportDto [msgType=" + msgType + ", msgChannel=" + msgChannel
				+ ", msgRef=" + msgRef + ", paymentStatus=" + paymentStatus
				+ ", msgDirection=" + msgDirection + ", senderBank="
				+ senderBank + ", receiverBank=" + receiverBank
				+ ", msgCurrency=" + msgCurrency + ", msgAmount=" + msgAmount
				+ ", msgValueDate=" + msgValueDate + ", orderingCustomer="
				+ orderingCustomer + ", beneficiaryCustomer="
				+ beneficiaryCustomer + ", MUR=" + MUR
				+ ", sendertoreciverInfo=" + sendertoreciverInfo + ", hostID="
				+ hostID + ", relatedRefrence=" + relatedRefrence + ",beneficiaryAccount="
				+ beneficiaryAccount + ",orderingAccount="+orderingAccount + ",txnReference="+ txnReference+"]";
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getBranch() {
		return branch;
	}
	public void setBranch(String branch) {
		this.branch = branch;
	}
	public void setMsgChannel(String msgChannel) {
		this.msgChannel = msgChannel;
	}
	
	public String getPaymentStatus() {
		return paymentStatus;
	}
	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
		System.out.println("paymentStatus"+paymentStatus);
	}
	public String getMsgDirection() {
		return msgDirection;
	}
	public void setMsgDirection(String msgDirection) {
		this.msgDirection = msgDirection;
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
	
	public String getMUR() {
		return MUR;
	}
	public void setMUR(String mUR) {
		MUR = mUR;
	}
	public String getSendertoreciverInfo() {
		return sendertoreciverInfo;
	}
	public void setSendertoreciverInfo(String sendertoreciverInfo) {
		this.sendertoreciverInfo = sendertoreciverInfo;
	}
	public String getHostID() {
		return hostID;
	}
	public void setHostID(String hostID) {
		this.hostID = hostID;
	}
	public void setMsgRef(String msgRef) {
		this.msgRef = msgRef;
	}
	public String getMsgRef() {
		return msgRef;
	}
	
	

}
