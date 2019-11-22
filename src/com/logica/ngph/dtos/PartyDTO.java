package com.logica.ngph.dtos;

import java.io.Serializable;
import java.math.BigDecimal;

public class PartyDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String partyName;
	private String actionPerform;
	private String partyCode;
	private String partyBankName;
	private String partyAddress; 
	private String stateName;
	private String districName;
	private String cityName;
	private String branchName;
	private int micrCode;
	private boolean isNEFTEnabled;
	private boolean isRTGSEnabled;
	private boolean isLCSEnabled;
	private boolean isBGSEnabled;
	private String ifscType;
	private String ifscCode;
	
	
	
	public String getIfscCode() {
		return ifscCode;
	}
	public void setIfscCode(String ifscCode) {
		this.ifscCode = ifscCode;
	}
	public boolean isNEFTEnabled() {
		return isNEFTEnabled;
	}
	public void setNEFTEnabled(boolean isNEFTEnabled) {
		this.isNEFTEnabled = isNEFTEnabled;
	}
	public boolean isRTGSEnabled() {
		return isRTGSEnabled;
	}
	public void setRTGSEnabled(boolean isRTGSEnabled) {
		this.isRTGSEnabled = isRTGSEnabled;
	}
	public boolean isLCSEnabled() {
		return isLCSEnabled;
	}
	public void setLCSEnabled(boolean isLCSEnabled) {
		this.isLCSEnabled = isLCSEnabled;
	}
	public boolean isBGSEnabled() {
		return isBGSEnabled;
	}
	public void setBGSEnabled(boolean isBGSEnabled) {
		this.isBGSEnabled = isBGSEnabled;
	}
	public String getIfscType() {
		return ifscType;
	}
	public void setIfscType(String ifscType) {
		this.ifscType = ifscType;
	}
	public int getMicrCode() {
		return micrCode;
	}
	public void setMicrCode(int micrCode) {
		this.micrCode = micrCode;
	}
	public String getStateName() {
		return stateName;
	}
	public void setStateName(String stateName) {
		this.stateName = stateName;
	}
	public String getDistricName() {
		return districName;
	}
	public void setDistricName(String districName) {
		this.districName = districName;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public String getBranchName() {
		return branchName;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	public String getPartyCode() {
		return partyCode;
	}
	public void setPartyCode(String partyCode) {
		this.partyCode = partyCode;
	}
	public String getPartyBankName() {
		return partyBankName;
	}
	public void setPartyBankName(String partyBankName) {
		this.partyBankName = partyBankName;
	}
	public String getPartyAddress() {
		return partyAddress;
	}
	public void setPartyAddress(String partyAddress) {
		this.partyAddress = partyAddress;
	}

	public String getPartyName() {
		return partyName;
	}
	public void setPartyName(String partyName) {
		this.partyName = partyName;
	}
	public String getActionPerform() {
		return actionPerform;
	}
	public void setActionPerform(String actionPerform) {
		this.actionPerform = actionPerform;
	}

}
