package com.logica.ngph.dtos;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class AuthoriseLCPaymentAdviceDto implements Serializable {
	private static final long serialVersionUID = 1L;
	private String lcNumber;
	private String acountID;
	private String lcExpirePlace;
	private Date lcExpiryDate;
	private String negotiatingBankPartyIdentifier;
	private String negotiatingBankAccount;
	private String negotiatingBankCode;
	private String negotiatingBankNameAndAddress;
	private String beneficiaryPartyIdentifier;
	private String beneficiaryNameAddress;
	private BigDecimal creditAmount;
	private int positiveTolerance;
	private int negativeTolerance;
	private String maximumCreditAmount;
	private String additionalAmountsCovered;
	private String authorisedBankCode;
	private String authorisationMode;
	private String authorisedNameAndAddress;
	private String draftsAt;
	private String draweeBankID;
	private String draweeBankAccount;
	private String draweeBankCode;
	private String draweeBankNameAndAddress;
	private String mixedPaymentDetails;
	private String deferredPaymentDetails;
	private String reimbursingBanksCharges;
	private String otherCharges;
	private String sendertoReceiverInformation;
	private String msgRef;
	private String lcType;
	private String currency;
	private String advisingBank;	
	private Date issueDate;
	private String applicant;
	private String beneficiary;
	private BigDecimal amount;
	private String narrative;
	
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
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public String getNarrative() {
		return narrative;
	}
	public void setNarrative(String narrative) {
		this.narrative = narrative;
	}

	
	
	public String getLcType() {
		return lcType;
	}
	public void setLcType(String lcType) {
		this.lcType = lcType;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	
	
	
	public String getLcNumber() {
		return lcNumber;
	}
	public void setLcNumber(String lcNumber) {
		this.lcNumber = lcNumber;
	}
	public String getAcountID() {
		return acountID;
	}
	public void setAcountID(String acountID) {
		this.acountID = acountID;
	}
	public String getLcExpirePlace() {
		return lcExpirePlace;
	}
	public void setLcExpirePlace(String lcExpirePlace) {
		this.lcExpirePlace = lcExpirePlace;
	}
	public Date getLcExpiryDate() {
		return lcExpiryDate;
	}
	public void setLcExpiryDate(Date lcExpiryDate) {
		this.lcExpiryDate = lcExpiryDate;
	}
	public String getNegotiatingBankPartyIdentifier() {
		return negotiatingBankPartyIdentifier;
	}
	public void setNegotiatingBankPartyIdentifier(
			String negotiatingBankPartyIdentifier) {
		this.negotiatingBankPartyIdentifier = negotiatingBankPartyIdentifier;
	}
	public String getNegotiatingBankAccount() {
		return negotiatingBankAccount;
	}
	public void setNegotiatingBankAccount(String negotiatingBankAccount) {
		this.negotiatingBankAccount = negotiatingBankAccount;
	}
	public String getNegotiatingBankCode() {
		return negotiatingBankCode;
	}
	public void setNegotiatingBankCode(String negotiatingBankCode) {
		this.negotiatingBankCode = negotiatingBankCode;
	}
	public String getNegotiatingBankNameAndAddress() {
		return negotiatingBankNameAndAddress;
	}
	public void setNegotiatingBankNameAndAddress(
			String negotiatingBankNameAndAddress) {
		this.negotiatingBankNameAndAddress = negotiatingBankNameAndAddress;
	}
	public String getBeneficiaryPartyIdentifier() {
		return beneficiaryPartyIdentifier;
	}
	public void setBeneficiaryPartyIdentifier(String beneficiaryPartyIdentifier) {
		this.beneficiaryPartyIdentifier = beneficiaryPartyIdentifier;
	}
	public String getBeneficiaryNameAddress() {
		return beneficiaryNameAddress;
	}
	public void setBeneficiaryNameAddress(String beneficiaryNameAddress) {
		this.beneficiaryNameAddress = beneficiaryNameAddress;
	}
	public BigDecimal getCreditAmount() {
		return creditAmount;
	}
	public void setCreditAmount(BigDecimal creditAmount) {
		this.creditAmount = creditAmount;
	}
	public int getPositiveTolerance() {
		return positiveTolerance;
	}
	public void setPositiveTolerance(int positiveTolerance) {
		this.positiveTolerance = positiveTolerance;
	}
	public int getNegativeTolerance() {
		return negativeTolerance;
	}
	public void setNegativeTolerance(int negativeTolerance) {
		this.negativeTolerance = negativeTolerance;
	}
	public String getMaximumCreditAmount() {
		return maximumCreditAmount;
	}
	public void setMaximumCreditAmount(String maximumCreditAmount) {
		this.maximumCreditAmount = maximumCreditAmount;
	}
	public String getAdditionalAmountsCovered() {
		return additionalAmountsCovered;
	}
	public void setAdditionalAmountsCovered(String additionalAmountsCovered) {
		this.additionalAmountsCovered = additionalAmountsCovered;
	}
	public String getAuthorisedBankCode() {
		return authorisedBankCode;
	}
	public void setAuthorisedBankCode(String authorisedBankCode) {
		this.authorisedBankCode = authorisedBankCode;
	}
	public String getAuthorisationMode() {
		return authorisationMode;
	}
	public void setAuthorisationMode(String authorisationMode) {
		this.authorisationMode = authorisationMode;
	}
	public String getAuthorisedNameAndAddress() {
		return authorisedNameAndAddress;
	}
	public void setAuthorisedNameAndAddress(String authorisedNameAndAddress) {
		this.authorisedNameAndAddress = authorisedNameAndAddress;
	}
	public String getDraftsAt() {
		return draftsAt;
	}
	public void setDraftsAt(String draftsAt) {
		this.draftsAt = draftsAt;
	}
	public String getDraweeBankID() {
		return draweeBankID;
	}
	public void setDraweeBankID(String draweeBankID) {
		this.draweeBankID = draweeBankID;
	}
	public String getDraweeBankAccount() {
		return draweeBankAccount;
	}
	public void setDraweeBankAccount(String draweeBankAccount) {
		this.draweeBankAccount = draweeBankAccount;
	}
	public String getDraweeBankCode() {
		return draweeBankCode;
	}
	public void setDraweeBankCode(String draweeBankCode) {
		this.draweeBankCode = draweeBankCode;
	}
	public String getDraweeBankNameAndAddress() {
		return draweeBankNameAndAddress;
	}
	public void setDraweeBankNameAndAddress(String draweeBankNameAndAddress) {
		this.draweeBankNameAndAddress = draweeBankNameAndAddress;
	}
	public String getMixedPaymentDetails() {
		return mixedPaymentDetails;
	}
	public void setMixedPaymentDetails(String mixedPaymentDetails) {
		this.mixedPaymentDetails = mixedPaymentDetails;
	}
	public String getDeferredPaymentDetails() {
		return deferredPaymentDetails;
	}
	public void setDeferredPaymentDetails(String deferredPaymentDetails) {
		this.deferredPaymentDetails = deferredPaymentDetails;
	}
	public String getReimbursingBanksCharges() {
		return reimbursingBanksCharges;
	}
	public void setReimbursingBanksCharges(String reimbursingBanksCharges) {
		this.reimbursingBanksCharges = reimbursingBanksCharges;
	}
	public String getOtherCharges() {
		return otherCharges;
	}
	public void setOtherCharges(String otherCharges) {
		this.otherCharges = otherCharges;
	}
	public String getSendertoReceiverInformation() {
		return sendertoReceiverInformation;
	}
	public void setSendertoReceiverInformation(String sendertoReceiverInformation) {
		this.sendertoReceiverInformation = sendertoReceiverInformation;
	}
	public String getMsgRef() {
		return msgRef;
	}
	public void setMsgRef(String msgRef) {
		this.msgRef = msgRef;
	}

	
	
	
	
	
	
	
	
}
