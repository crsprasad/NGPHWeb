package com.logica.ngph.dtos;

import java.math.BigDecimal;

public class MMIDManagerDto {
	private String benificiaryMMID;
	private String accountNo;
	private BigDecimal orderingAmount;
	private String benificiaryMobileNo;
	private String remarks;
	private String benificiaryIFSC;
	private String transactionType;
	private String fromAccType;
	private String toAccType;
	private String beneficaryAccountNo;
	
	public String getBeneficaryAccountNo() {
		return beneficaryAccountNo;
	}
	public void setBeneficaryAccountNo(String beneficaryAccountNo) {
		this.beneficaryAccountNo = beneficaryAccountNo;
	}
	/**
	 * @return the toAccType
	 */
	public String getToAccType() {
		return toAccType;
	}
	/**
	 * @param toAccType the toAccType to set
	 */
	public void setToAccType(String toAccType) {
		this.toAccType = toAccType;
	}
	public String getBenificiaryIFSC() {
		return benificiaryIFSC;
	}
	public String getTransactionType() {
		return transactionType;
	}
	public String getFromAccType() {
		return fromAccType;
	}
	public void setBenificiaryIFSC(String benificiaryIFSC) {
		this.benificiaryIFSC = benificiaryIFSC;
	}
	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}
	public void setFromAccType(String fromAccType) {
		this.fromAccType = fromAccType;
	}
	public String getBenificiaryMMID() {
		return benificiaryMMID;
	}
	public void setBenificiaryMMID(String benificiaryMMID) {
		this.benificiaryMMID = benificiaryMMID;
	}
	public String getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}
	public BigDecimal getOrderingAmount() {
		return orderingAmount;
	}
	public void setOrderingAmount(BigDecimal orderingAmount) {
		this.orderingAmount = orderingAmount;
	}
	public String getBenificiaryMobileNo() {
		return benificiaryMobileNo;
	}
	public void setBenificiaryMobileNo(String benificiaryMobileNo) {
		this.benificiaryMobileNo = benificiaryMobileNo;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

}
