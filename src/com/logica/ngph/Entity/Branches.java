package com.logica.ngph.Entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table(name="BRANCHES")
public class Branches implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3009143549823566044L;
	private String branchCategory;
	private String branchCode;
	private String branchName;
	private String branchAddressRef;
	private String branchBicCode;
	private String branchAddress;
	
	//BRANCH_CLRGMMBID
	private String branchIFSCCode; 
	
	
/*	BRANCH_CTRY, 
	BRANCH_CODE, 
	BRANCH_NAME, 
	BRANCH_ADDRREF,
	BRANCH_BIC*/
	
	
	
	@Column(name="BRANCH_ADDRESS")
	public String getBranchAddress() {
		return branchAddress;
	}
	
	public void setBranchAddress(String branchAddress) {
		this.branchAddress = branchAddress;
	}
	@Column(name="BRANCH_CLRGMMBID")
	public String getBranchIFSCCode() {
		return branchIFSCCode;
	}
	
	public void setBranchIFSCCode(String branchIFSCCode) {
		this.branchIFSCCode = branchIFSCCode;
	}
	
	@Column(name="BRANCH_CTRY")
	public String getBranchCategory() {
		return branchCategory;
	}
	public void setBranchCategory(String branchCategory) {
		this.branchCategory = branchCategory;
	}
	
	@Id
	@Column(name="BRANCH_CODE")
	public String getBranchCode() {
		return branchCode;
	}
	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}
	
	@Column(name="BRANCH_NAME")
	public String getBranchName() {
		return branchName;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	
	@Column(name="BRANCH_ADDRREF")
	public String getBranchAddressRef() {
		return branchAddressRef;
	}
	public void setBranchAddressRef(String branchAddressRef) {
		this.branchAddressRef = branchAddressRef;
	}
	
	@Column(name="BRANCH_BIC")
	public String getBranchBicCode() {
		return branchBicCode;
	}
	public void setBranchBicCode(String branchBicCode) {
		this.branchBicCode = branchBicCode;
	}
	
	

}
