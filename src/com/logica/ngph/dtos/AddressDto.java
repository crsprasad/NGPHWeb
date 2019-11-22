package com.logica.ngph.dtos;

public class AddressDto {
	public String getOwnBranch() {
		return ownBranch;
	}
	public void setOwnBranch(String ownBranch) {
		this.ownBranch = ownBranch;
	}
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	private String mobileNo;
	private String MMID;
	private String creditLimit;
	private String ownBranch;
	private String accountType;
	private String flag;
	
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public String getCreditLimit() {
		return creditLimit;
	}
	public void setCreditLimit(String creditLimit) {
		this.creditLimit = creditLimit;
	}
	public String getDebitLimit() {
		return debitLimit;
	}
	public void setDebitLimit(String debitLimit) {
		this.debitLimit = debitLimit;
	}
	private String debitLimit;
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public String getMMID() {
		return MMID;
	}
	public void setMMID(String mMID) {
		MMID = mMID;
	}
	

}
