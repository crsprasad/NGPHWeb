package com.logica.ngph.Entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="TA_PARTIES")
public class Parties implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5490734814589126343L;
	//PARTY_BIC
	private String bankIdentifierCode;
	//PARTY_CLRSYSMMBID_MMBID
	private String clearingSystemMemberId;
	//PARTY_CLRSYSMMBID_C_CD
	private String clearingSystemMemberCode;
	//PARTY_CLRSYSMMBID_C_PRTRY
	private String clearingSystemMemberExternalCode;
	//PARTY_NM
	private String agentName;
	//PARTY_ADDRREF
	private String addressRef;
	//PARTY_OTHR_ID
	private String partyIdendificationId;
	//PARTY_OTHR_SCHMENM_CD
	private String externalPartyIdendificationId;
	//PARTY_OTHR_SCHMENM_PRTRY
	private String idendificationSchme;
	//PARTY_OTHR_ISSR
	private String partyissr;
	//PARTY_BRANCH
	private int branch;
	//PARTY_PARENT_ID_IND
	private String parentIdIndicator;
	//PARTY_REFERENCE
	private String bankReference;
	//PARTY_ISCORRESPONDENT
	private int isCorrespondent;
	
	private String partyAddress;
	private String partyBranchName;
	
	/*
		START :: Added new properties for MIZUHO Bank CR File upload functionality
	 */
	
	private String ifscType;
	private int micrCode;
	private String cityName;
	private String distName;
	private String stateName;
	private int stdCode;
	private int phoneNo;
	private String isNEFTEnable;
	private String isRTGSEnable;
	private String isLCSEnable;
	private String isBGSEnable;
	
	@Column(name="IFSC_TYPE")
	public String getIfscType() {
		return ifscType;
	}
	public void setIfscType(String ifscType) {
		this.ifscType = ifscType;
	}
	
	@Column(name="MICR_CODE")
	public int getMicrCode() {
		return micrCode;
	}
	
	public void setMicrCode(int micrCode) {
		this.micrCode = micrCode;
	}
	
	@Column(name="CITY_NAME")
	public String getCityName() {
		return cityName;
	}
	
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	
	@Column(name="DISTRICT_NAME")
	public String getDistName() {
		return distName;
	}
	
	public void setDistName(String distName) {
		this.distName = distName;
	}
	
	@Column(name="STATE_NAME")
	public String getStateName() {
		return stateName;
	}
	
	public void setStateName(String stateName) {
		this.stateName = stateName;
	}
	
	@Column(name="STD_CODE")
	public int getStdCode() {
		return stdCode;
	}
	
	public void setStdCode(int stdCode) {
		this.stdCode = stdCode;
	}
	
	@Column(name="PHONE_NUM")
	public int getPhoneNo() {
		return phoneNo;
	}
	
	public void setPhoneNo(int phoneNo) {
		this.phoneNo = phoneNo;
	}
	
	@Column(name="IS_NEFT_ENABLED")
	public String getIsNEFTEnable() {
		return isNEFTEnable;
	}
	
	public void setIsNEFTEnable(String isNEFTEnable) {
		this.isNEFTEnable = isNEFTEnable;
	}
	
	@Column(name="IS_RTGS_ENABLED")
	public String getIsRTGSEnable() {
		return isRTGSEnable;
	}
	
	public void setIsRTGSEnable(String isRTGSEnable) {
		this.isRTGSEnable = isRTGSEnable;
	}
	
	@Column(name="IS_LCS_ENABLED")
	public String getIsLCSEnable() {
		return isLCSEnable;
	}
	
	public void setIsLCSEnable(String isLCSEnable) {
		this.isLCSEnable = isLCSEnable;
	}
	
	@Column(name="IS_BGS_ENABLED")
	public String getIsBGSEnable() {
		return isBGSEnable;
	}
	
	public void setIsBGSEnable(String isBGSEnable) {
		this.isBGSEnable = isBGSEnable;
	}
	
	/*
		END :: Added new properties for MIZUHO Bank CR File upload functionality
	 */
	
	@Column(name="PARTY_ADDRESS")
	public String getPartyAddress() {
		return partyAddress;
	}
	@Column(name="PARTY_BRANCH_NAME")
	public String getPartyBranchName() {
		return partyBranchName;
	}
	public void setPartyBranchName(String partyBranchName) {
		this.partyBranchName = partyBranchName;
	}
	public void setPartyAddress(String partyAddress) {
		this.partyAddress = partyAddress;
	}
	

	@Column(name="PARTY_BIC")
	public String getBankIdentifierCode() {
		return bankIdentifierCode;
	}
	public void setBankIdentifierCode(String bankIdentifierCode) {
		this.bankIdentifierCode = bankIdentifierCode;
	}
	@Id
	@Column(name="PARTY_CLRSYSMMBID_MMBID")
	public String getClearingSystemMemberId() {
		return clearingSystemMemberId;
	}
	public void setClearingSystemMemberId(String clearingSystemMemberId) {
		this.clearingSystemMemberId = clearingSystemMemberId;
	}
	@Column(name="PARTY_CLRSYSMMBID_C_CD")
	public String getClearingSystemMemberCode() {
		return clearingSystemMemberCode;
	}
	public void setClearingSystemMemberCode(String clearingSystemMemberCode) {
		this.clearingSystemMemberCode = clearingSystemMemberCode;
	}
	@Column(name="PARTY_CLRSYSMMBID_C_PRTRY")
	public String getClearingSystemMemberExternalCode() {
		return clearingSystemMemberExternalCode;
	}
	public void setClearingSystemMemberExternalCode(
			String clearingSystemMemberExternalCode) {
		this.clearingSystemMemberExternalCode = clearingSystemMemberExternalCode;
	}
	@Column(name="PARTY_NM")
	public String getAgentName() {
		return agentName;
	}
	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}
	@Column(name="PARTY_ADDRREF")
	public String getAddressRef() {
		return addressRef;
	}
	public void setAddressRef(String addressRef) {
		this.addressRef = addressRef;
	}
	@Column(name="PARTY_OTHR_ID")
	public String getPartyIdendificationId() {
		return partyIdendificationId;
	}
	public void setPartyIdendificationId(String partyIdendificationId) {
		this.partyIdendificationId = partyIdendificationId;
	}
	@Column(name="PARTY_OTHR_SCHMENM_CD")
	public String getExternalPartyIdendificationId() {
		return externalPartyIdendificationId;
	}
	public void setExternalPartyIdendificationId(
			String externalPartyIdendificationId) {
		this.externalPartyIdendificationId = externalPartyIdendificationId;
	}
	@Column(name="PARTY_OTHR_SCHMENM_PRTRY")
	public String getIdendificationSchme() {
		return idendificationSchme;
	}
	public void setIdendificationSchme(String idendificationSchme) {
		this.idendificationSchme = idendificationSchme;
	}
	@Column(name="PARTY_OTHR_ISSR")
	public String getPartyissr() {
		return partyissr;
	}
	public void setPartyissr(String partyissr) {
		this.partyissr = partyissr;
	}
	
	@Column(name="PARTY_BRANCH")
	public int getBranch() {
		return branch;
	}
	public void setBranch(int branch) {
		this.branch = branch;
	}
	@Column(name="PARTY_PARENT_ID_IND")
	public String getParentIdIndicator() {
		return parentIdIndicator;
	}
	public void setParentIdIndicator(String parentIdIndicator) {
		this.parentIdIndicator = parentIdIndicator;
	}
	@Column(name="PARTY_REFERENCE")
	public String getBankReference() {
		return bankReference;
	}
	public void setBankReference(String bankReference) {
		this.bankReference = bankReference;
	}
	@Column(name="PARTY_ISCORRESPONDENT")
	public int getIsCorrespondent() {
		return isCorrespondent;
	}
	public void setIsCorrespondent(int isCorrespondent) {
		this.isCorrespondent = isCorrespondent;
	}
}
