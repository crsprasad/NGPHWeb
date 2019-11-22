package com.logica.ngph.Entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="TA_ADDRESSES")
public class Addresses implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3090545271133252166L;
	/*ADDR_REF, 
	ADDR_PSTLADR_ADRTP,
	ADDR_PSTLADR_DEPT,
	ADDR_PSTLADR_SUBDEPT, 
	ADDR_PSTLADR_STRTNM,
	ADDR_PSTLADR_BLDGNB, 
	ADDR_PSTLADR_PSTCD, 
	ADDR_PSTLADR_TWNNM,
	ADDR_PSTLADR_CTRYSUBDVSN,
	ADDR_PSTLADR_CTRY,
	ADDR_PSTLADR_ADRLINE,
	ADDR_PHONE1,
	ADDR_PHONE2, 
	ADDR_PHONE3,
	ADDR_FAX, 
	ADDR_EMAIL1,
	ADDR_EMAIL2,
	ADDR_EMAIL3,
	ADDR_ADDRESS_FOR,
	ADDR_OWN_BRANCH
	ADDR_SEQ
	*/
	
	private String addressRef;
	private String postalAddressNature;
	private String department;
	private String subDepartment;
	private String streetName;
	private String buildingDetail;
	private String postalCode;
	private String townName;
	private String citySubDivision;
	private String cityName;
	private String postalServiceAddress;
	
	private String phoneOne;
	private String phoneTwo;
	private String phoneThree;
	private String fax;
	private String emailOne;
	private String emailTwo;
	private String emailThree;
	private String addressFor;
	private String branchReference;
	private String mobileNo;
	private String MMID;
	private String seq;
	@Column(name="ADDR_SEQ")
	public String getSeq() {
		return seq;
	}
	public void setSeq(String seq) {
		this.seq = seq;
	}
	@Column(name ="ADDR_MMID")
	public String getMMID() {
		return MMID;
	}
	public void setMMID(String mMID) {
		MMID = mMID;
	}
	//ADDR_REF
	@Id
	@Column(name="ADDR_REF")
	public String getAddressRef() {
		return addressRef;
	}
	@Column(name ="ADDR_MOBILE")
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public void setAddressRef(String addressRef) {
		this.addressRef = addressRef;
	}
	//ADDR_PSTLADR_ADRTP
	@Column(name="ADDR_PSTLADR_ADRTP")
	public String getPostalAddressNature() {
		return postalAddressNature;
	}
	public void setPostalAddressNature(String postalAddressNature) {
		this.postalAddressNature = postalAddressNature;
	}
	//ADDR_PSTLADR_DEPT
	@Column(name="ADDR_PSTLADR_DEPT")
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	//ADDR_PSTLADR_SUBDEPT
	@Column(name="ADDR_PSTLADR_SUBDEPT")
	public String getSubDepartment() {
		return subDepartment;
	}
	public void setSubDepartment(String subDepartment) {
		this.subDepartment = subDepartment;
	}
	//ADDR_PSTLADR_STRTNM
	@Column(name="ADDR_PSTLADR_STRTNM")
	public String getStreetName() {
		return streetName;
	}
	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}
	//ADDR_PSTLADR_BLDGNB
	@Column(name="ADDR_PSTLADR_BLDGNB")
	public String getBuildingDetail() {
		return buildingDetail;
	}
	public void setBuildingDetail(String buildingDetail) {
		this.buildingDetail = buildingDetail;
	}
	//ADDR_PSTLADR_PSTCD
	@Column(name="ADDR_PSTLADR_PSTCD")
	public String getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	//ADDR_PSTLADR_TWNNM
	@Column(name="ADDR_PSTLADR_TWNNM")
	public String getTownName() {
		return townName;
	}
	public void setTownName(String townName) {
		this.townName = townName;
	}
	//ADDR_PSTLADR_CTRYSUBDVSN
	@Column(name="ADDR_PSTLADR_CTRYSUBDVSN")
	public String getCitySubDivision() {
		return citySubDivision;
	}
	public void setCitySubDivision(String citySubDivision) {
		this.citySubDivision = citySubDivision;
	}
	//ADDR_PSTLADR_CTRY
	@Column(name="ADDR_PSTLADR_CTRY")
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	//ADDR_PSTLADR_ADRLINE
	@Column(name="ADDR_PSTLADR_ADRLINE")
	public String getPostalServiceAddress() {
		return postalServiceAddress;
	}
	public void setPostalServiceAddress(String postalServiceAddress) {
		this.postalServiceAddress = postalServiceAddress;
	}
	//ADDR_PHONE1
	@Column(name="ADDR_PHONE1")
	public String getPhoneOne() {
		return phoneOne;
	}
	public void setPhoneOne(String phoneOne) {
		this.phoneOne = phoneOne;
	}
	//ADDR_PHONE2
	@Column(name="ADDR_PHONE2")
	public String getPhoneTwo() {
		return phoneTwo;
	}
	public void setPhoneTwo(String phoneTwo) {
		this.phoneTwo = phoneTwo;
	}
	//ADDR_PHONE3
	@Column(name="ADDR_PHONE3")
	public String getPhoneThree() {
		return phoneThree;
	}
	public void setPhoneThree(String phoneThree) {
		this.phoneThree = phoneThree;
	}
	//ADDR_FAX
	@Column(name="ADDR_FAX")
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	//ADDR_EMAIL1
	@Column(name="ADDR_EMAIL1")
	public String getEmailOne() {
		return emailOne;
	}
	public void setEmailOne(String emailOne) {
		this.emailOne = emailOne;
	}
	//ADDR_EMAIL2
	@Column(name="ADDR_EMAIL2")
	public String getEmailTwo() {
		return emailTwo;
	}
	public void setEmailTwo(String emailTwo) {
		this.emailTwo = emailTwo;
	}
	//ADDR_EMAIL3
	@Column(name="ADDR_EMAIL3")
	public String getEmailThree() {
		return emailThree;
	}
	public void setEmailThree(String emailThree) {
		this.emailThree = emailThree;
	}
	//ADDR_ADDRESS_FOR
	@Column(name="ADDR_ADDRESS_FOR")
	public String getAddressFor() {
		return addressFor;
	}
	public void setAddressFor(String addressFor) {
		this.addressFor = addressFor;
	}
	//ADDR_OWN_BRANCH
	@Column(name="ADDR_OWN_BRANCH")
	public String getBranchReference() {
		return branchReference;
	}
	public void setBranchReference(String branchReference) {
		this.branchReference = branchReference;
	}


}
