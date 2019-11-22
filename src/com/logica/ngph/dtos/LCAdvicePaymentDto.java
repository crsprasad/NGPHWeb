package com.logica.ngph.dtos;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class LCAdvicePaymentDto implements Serializable{
	private static final long serialVersionUID = 1L;
	private String lcType;
	private String lcNumber;
	private String advisingBank;	
	private Date issueDate;
	//private Date expiryDate;
	private String applicant;
	private String beneficiary;
	public Date getLcExpiryDate() {
		return lcExpiryDate;
	}
	public void setLcExpiryDate(Date lcExpiryDate) {
		this.lcExpiryDate = lcExpiryDate;
	}
	public String getLcCurrency() {
		return lcCurrency;
	}
	public void setLcCurrency(String lcCurrency) {
		this.lcCurrency = lcCurrency;
	}
	public BigDecimal getLcAmount() {
		return lcAmount;
	}
	public void setLcAmount(BigDecimal lcAmount) {
		this.lcAmount = lcAmount;
	}
	private Date lcExpiryDate;
	private String lcCurrency;
	private BigDecimal lcAmount;
	private String narrative;
	
	
	private String presentingBanksReference;
	private BigDecimal totalAmountClaimed;
	private BigDecimal paidAmount;
	private String sendersCorrespondentPartyIdentifier;
	private String senderCorrespontAcount;
	private String sendersCorrespondentCode;
	private String sendersCorrespondentLocation;
	private String sendersCorrespondentNameandAddress;
	private String receiversCorrespondentPartyIdentifier;
	private String receiverCorrespontAcount;
	private String receiversCorrespondentCode;
	private String receiversCorrespondentLocation;
	private String receiversCorrespondentNameandAddress;
	private String sendertoReceiverInformation;
	private String msgRef;
	
	public String getMsgRef() {
		return msgRef;
	}
	public void setMsgRef(String msgRef) {
		this.msgRef = msgRef;
	}
	public String getSenderCorrespontAcount() {
		return senderCorrespontAcount;
	}
	public void setSenderCorrespontAcount(String senderCorrespontAcount) {
		this.senderCorrespontAcount = senderCorrespontAcount;
	}
	
	public String getReceiverCorrespontAcount() {
		return receiverCorrespontAcount;
	}
	public void setReceiverCorrespontAcount(String receiverCorrespontAcount) {
		this.receiverCorrespontAcount = receiverCorrespontAcount;
	}
	
	public String getAdvisingBank() {
		return advisingBank;
	}
	public void setAdvisingBank(String advisingBank) {
		this.advisingBank = advisingBank;
	}
	public Date getIssueDate() {
		return issueDate;
	}
	public void setIssueDate(Date issueDate) {
		this.issueDate = issueDate;
	}
/*	public Date getExpiryDate() {
		return expiryDate;
	}
	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}*/
	public String getApplicant() {
		return applicant;
	}
	public void setApplicant(String applicant) {
		this.applicant = applicant;
	}
	public String getBeneficiary() {
		return beneficiary;
	}
	public void setBeneficiary(String beneficiary) {
		this.beneficiary = beneficiary;
	}
/*	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}*/
	public String getNarrative() {
		return narrative;
	}
	public void setNarrative(String narrative) {
		this.narrative = narrative;
	}
	public String getPresentingBanksReference() {
		return presentingBanksReference;
	}
	public void setPresentingBanksReference(String presentingBanksReference) {
		this.presentingBanksReference = presentingBanksReference;
	}
	public BigDecimal getTotalAmountClaimed() {
		return totalAmountClaimed;
	}
	public void setTotalAmountClaimed(BigDecimal totalAmountClaimed) {
		this.totalAmountClaimed = totalAmountClaimed;
	}
	public BigDecimal getPaidAmount() {
		return paidAmount;
	}
	public void setPaidAmount(BigDecimal paidAmount) {
		this.paidAmount = paidAmount;
	}
	public String getSendersCorrespondentPartyIdentifier() {
		return sendersCorrespondentPartyIdentifier;
	}
	public void setSendersCorrespondentPartyIdentifier(
			String sendersCorrespondentPartyIdentifier) {
		this.sendersCorrespondentPartyIdentifier = sendersCorrespondentPartyIdentifier;
	}
	public String getSendersCorrespondentCode() {
		return sendersCorrespondentCode;
	}
	public void setSendersCorrespondentCode(String sendersCorrespondentCode) {
		this.sendersCorrespondentCode = sendersCorrespondentCode;
	}
	public String getSendersCorrespondentLocation() {
		return sendersCorrespondentLocation;
	}
	public void setSendersCorrespondentLocation(String sendersCorrespondentLocation) {
		this.sendersCorrespondentLocation = sendersCorrespondentLocation;
	}
	public String getSendersCorrespondentNameandAddress() {
		return sendersCorrespondentNameandAddress;
	}
	public void setSendersCorrespondentNameandAddress(
			String sendersCorrespondentNameandAddress) {
		this.sendersCorrespondentNameandAddress = sendersCorrespondentNameandAddress;
	}
	public String getReceiversCorrespondentPartyIdentifier() {
		return receiversCorrespondentPartyIdentifier;
	}
	public void setReceiversCorrespondentPartyIdentifier(
			String receiversCorrespondentPartyIdentifier) {
		this.receiversCorrespondentPartyIdentifier = receiversCorrespondentPartyIdentifier;
	}
	public String getReceiversCorrespondentCode() {
		return receiversCorrespondentCode;
	}
	public void setReceiversCorrespondentCode(String receiversCorrespondentCode) {
		this.receiversCorrespondentCode = receiversCorrespondentCode;
	}
	public String getReceiversCorrespondentLocation() {
		return receiversCorrespondentLocation;
	}
	public void setReceiversCorrespondentLocation(
			String receiversCorrespondentLocation) {
		this.receiversCorrespondentLocation = receiversCorrespondentLocation;
	}
	public String getReceiversCorrespondentNameandAddress() {
		return receiversCorrespondentNameandAddress;
	}
	public void setReceiversCorrespondentNameandAddress(
			String receiversCorrespondentNameandAddress) {
		this.receiversCorrespondentNameandAddress = receiversCorrespondentNameandAddress;
	}
	public String getSendertoReceiverInformation() {
		return sendertoReceiverInformation;
	}
	public void setSendertoReceiverInformation(String sendertoReceiverInformation) {
		this.sendertoReceiverInformation = sendertoReceiverInformation;
	}
	
	

	
	
	public String getLcType() {
		return lcType;
	}
	public void setLcType(String lcType) {
		this.lcType = lcType;
	}
	public String getLcNumber() {
		return lcNumber;
	}
	public void setLcNumber(String lcNumber) {
		this.lcNumber = lcNumber;
	}


	
}
