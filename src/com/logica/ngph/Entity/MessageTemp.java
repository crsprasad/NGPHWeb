/**
 * 
 */
package com.logica.ngph.Entity;

import java.io.Serializable;
import java.sql.Clob;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="TXN_TEMPLATEDATA")
public class MessageTemp implements Serializable {

	private static final long serialVersionUID = 1L;
	private String tempRef;
	private String tempId;
	private String tempName;
	private Clob tempData;
	private String userDept;
	private String userBranch;
	private String crtdUserId;
	private String msgType;
	private String subMsgType;
	private String status;
	private Timestamp crtDate;
	

	@Id
	@Column(name="TEMP_REF")
	public String getTempRef() {
		return tempRef;
	}

	public void setTempRef(String tempRef) {
		this.tempRef = tempRef;
	}

	@Column(name="TEMP_ID")
	public String getTempId() {
		return tempId;
	}

	public void setTempId(String tempId) {
		this.tempId = tempId;
	}

	@Column(name="TEMP_NAME")
	public String getTempName() {
		return tempName;
	}

	public void setTempName(String tempName) {
		this.tempName = tempName;
	}

	@Column(name="DATA")
	public Clob getTempData() {
		return tempData;
	}

	public void setTempData(Clob tempData) {
		this.tempData = tempData;
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

	@Column(name="CRTD_USER")
	public String getCrtdUserId() {
		return crtdUserId;
	}

	public void setCrtdUserId(String crtdUserId) {
		this.crtdUserId = crtdUserId;
	}

	@Column(name="MSGS_SRC_MSGTYPE")
	public String getMsgType() {
		return msgType;
	}

	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}

	@Column(name="MSGS_SRC_MSGSUBTYPE")
	public String getSubMsgType() {
		return subMsgType;
	}

	public void setSubMsgType(String subMsgType) {
		this.subMsgType = subMsgType;
	}

	@Column(name="STATUS")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name="CREATED_DATETIME")
	public Timestamp getCrtDate() {
		return crtDate;
	}
	
	public void setCrtDate(Timestamp crtDate) {
		this.crtDate = crtDate;
	}
	
	
}
