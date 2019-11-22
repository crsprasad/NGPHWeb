/**
 * 
 */
package com.logica.ngph.Entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="TA_AUTHSTATUST")
public class AuthUIStatusT implements Serializable {

	/*
	BRANCH
	DEPT
	MSGREF
	AUTHREF
	HOSTID
	CHNLTYPE
	MSGTYPE
	DIRECTION
	CURRENCY
	AMOUNT
	RECVDTIME
	AUTHTIME
	COMMENTS
	SUBMSGTYPE
	AUTHCYCLE
	REJECTEDTIME
	*/
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -9149642510186430395L;
	String branch;
	String dept;
	String msgRef;
	String authRef;
	String hostID;
	String channelType;
	String msgType;
	String direction;
	String currency;
	BigDecimal amt;
	Timestamp recTime;
	Timestamp authTime;
	Timestamp rejectedTime;
	String comments;
	String subMsgType;
	String authCycle;
	
	/**
	 * @return the rejectedTime
	 */
	public Timestamp getRejectedTime() {
		return rejectedTime;
	}
	/**
	 * @param rejectedTime the rejectedTime to set
	 */
	public void setRejectedTime(Timestamp rejectedTime) {
		this.rejectedTime = rejectedTime;
	}
	
	/**
	 * @return the branch
	 */
	@Column (name="BRANCH")
	public String getBranch() {
		return branch;
	}
	/**
	 * @return the dept
	 */
	@Column (name="DEPT")
	public String getDept() {
		return dept;
	}
	/**
	 * @return the msgRef
	 */
	@Column (name="MSGREF")
	public String getMsgRef() {
		return msgRef;
	}
	/**
	 * @return the authRef
	 */
	
	@Id
	@Column (name="authRef")
	public String getAuthRef() {
		return authRef;
	}
	/**
	 * @return the hostID
	 */
	@Column (name="HOSTID")
	public String getHostID() {
		return hostID;
	}
	/**
	 * @return the channelType
	 */
	@Column (name="CHNLTYPE")
	public String getChannelType() {
		return channelType;
	}
	/**
	 * @return the msgType
	 */
	@Column (name="MSGTYPE")
	public String getMsgType() {
		return msgType;
	}
	/**
	 * @return the direction
	 */
	@Column (name="DIRECTION")
	public String getDirection() {
		return direction;
	}
	/**
	 * @return the currency
	 */
	@Column (name="CURRENCY")
	public String getCurrency() {
		return currency;
	}
	/**
	 * @return the amt
	 */
	@Column (name="AMOUNT")
	public BigDecimal getAmt() {
		return amt;
	}
	/**
	 * @return the recTime
	 */
	@Column (name="RECVDTIME")
	public Timestamp getRecTime() {
		return recTime;
	}
	/**
	 * @return the authTime
	 */
	@Column (name="AUTHTIME")
	public Timestamp getAuthTime() {
		return authTime;
	}
	/**
	 * @return the comments
	 */
	@Column (name="COMMENTS")
	public String getComments() {
		return comments;
	}
	/**
	 * @return the subMsgType
	 */
	@Column (name="SUBMSGTYPE")
	public String getSubMsgType() {
		return subMsgType;
	}
	/**
	 * @return the authCycle
	 */
	@Column (name="AUTHCYCLE")
	public String getAuthCycle() {
		return authCycle;
	}
	/**
	 * @param branch the branch to set
	 */
	public void setBranch(String branch) {
		this.branch = branch;
	}
	/**
	 * @param dept the dept to set
	 */
	public void setDept(String dept) {
		this.dept = dept;
	}
	/**
	 * @param msgRef the msgRef to set
	 */
	public void setMsgRef(String msgRef) {
		this.msgRef = msgRef;
	}
	/**
	 * @param authRef the authRef to set
	 */
	public void setAuthRef(String authRef) {
		this.authRef = authRef;
	}
	/**
	 * @param hostID the hostID to set
	 */
	public void setHostID(String hostID) {
		this.hostID = hostID;
	}
	/**
	 * @param channelType the channelType to set
	 */
	public void setChannelType(String channelType) {
		this.channelType = channelType;
	}
	/**
	 * @param msgType the msgType to set
	 */
	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}
	/**
	 * @param direction the direction to set
	 */
	public void setDirection(String direction) {
		this.direction = direction;
	}
	/**
	 * @param currency the currency to set
	 */
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	/**
	 * @param amt the amt to set
	 */
	public void setAmt(BigDecimal amt) {
		this.amt = amt;
	}
	/**
	 * @param recTime the recTime to set
	 */
	public void setRecTime(Timestamp recTime) {
		this.recTime = recTime;
	}
	/**
	 * @param authTime the authTime to set
	 */
	public void setAuthTime(Timestamp authTime) {
		this.authTime = authTime;
	}
	/**
	 * @param comments the comments to set
	 */
	public void setComments(String comments) {
		this.comments = comments;
	}
	/**
	 * @param subMsgType the subMsgType to set
	 */
	public void setSubMsgType(String subMsgType) {
		this.subMsgType = subMsgType;
	}
	/**
	 * @param authCycle the authCycle to set
	 */
	public void setAuthCycle(String authCycle) {
		this.authCycle = authCycle;
	}
}
