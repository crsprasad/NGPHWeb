package com.logica.ngph.Entity;

import java.io.Serializable;
import java.sql.Clob;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="TXN_SCREENDATA")
public class GenericManager implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String txnRef;
	private String screenID;
	private Clob screenData;
	private String status;
	private String operation;
	private String crtdUserId;
	private String mdfdUserId;
	private String userDept;
	private String userBranch;
	private Timestamp crtDate;
	
	
	
	@Column(name="CRTD_USER")
	public String getCrtdUserId() {
		return crtdUserId;
	}

	public void setCrtdUserId(String crtdUserId) {
		this.crtdUserId = crtdUserId;
	}
	
	@Column(name="MODIFIED_USER")
	public String getMdfdUserId() {
		return mdfdUserId;
	}
	
	public void setMdfdUserId(String mdfdUserId) {
		this.mdfdUserId = mdfdUserId;
	}
	
	@Id
	@Column(name="TXNREF")
	public String getTxnRef() {
		return txnRef;
	}
	public void setTxnRef(String txnRef) {
		this.txnRef = txnRef;
	}
	@Column(name="SCREEN_ID")
	public String getScreenID() {
		return screenID;
	}
	public void setScreenID(String screenID) {
		this.screenID = screenID;
	}
	@Column(name ="SCREENDATA")
	public Clob getScreenData() {
		return screenData;
	}
	public void setScreenData(Clob screenData) {
		this.screenData = screenData;
	}
	@Column(name="STATUS")
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Column(name="OPERATION")
	public String getOperation() {
		return operation;
	}
	
	public void setOperation(String operation) {
		this.operation = operation;
	}

	@Column(name="USER_DEPT")
	public String getUserDept() {
		return userDept;
	}

	public void setUserDept(String userDept) {
		this.userDept = userDept;
	}

	@Column(name="USER_BRANCH")
	public String getUserBranch() {
		return userBranch;
	}

	public void setUserBranch(String userBranch) {
		this.userBranch = userBranch;
	}

	@Column(name="CREATED_DATETIME")
	public Timestamp getCrtDate() {
		return crtDate;
	}

	public void setCrtDate(Timestamp crtDate) {
		this.crtDate = crtDate;
	}
	
	

}
