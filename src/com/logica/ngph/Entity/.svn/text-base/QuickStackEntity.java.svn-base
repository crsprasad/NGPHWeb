package com.logica.ngph.Entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="TA_MSGS_RPT")
public class QuickStackEntity implements Serializable{

	private String msgRef;
	private String currency;
	private BigDecimal amount;
	private String direction;
	private String Department;
	private String branch;
	private String msgStatus;
	private String msgType;
	private String subMsgType;
	private String channel;
	/*"MSGS_MSGREF"         VARCHAR2(50 BYTE),
    "MSGS_INTRBKSTTLMCCY" VARCHAR2(3 BYTE),
    "MSGS_INTRBKSTTLMAMT" NUMBER(22,5),
    "MSGS_SRC_MSGTYPE"    VARCHAR2(10 BYTE),
    "MSGS_SRC_MSGSUBTYPE" VARCHAR2(15 BYTE),
    "MSGS_DIRECTION"      VARCHAR2(1 BYTE),
    "MSGS_DEPT"           VARCHAR2(15 BYTE),
    "MSGS_BRANCH"         VARCHAR2(15 BYTE),
    "MSGS_MSGSTS"         VARCHAR2(15 BYTE),
    "MSGS_CHANNELID"      VARCHAR2(15 BYTE)
*/
	private static final long serialVersionUID = -6409001704113734088L;
	
	@Id
	@Column(name="MSGS_MSGREF")
	public String getMsgRef() {
		return msgRef;
	}
	public void setMsgRef(String msgRef) {
		this.msgRef = msgRef;
	}
	@Column(name="MSGS_INTRBKSTTLMCCY")
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	@Column(name="MSGS_INTRBKSTTLMAMT")
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		amount = amount;
	}
	@Column(name="MSGS_DIRECTION")
	public String getDirection() {
		return direction;
	}
	public void setDirection(String direction) {
		this.direction = direction;
	}
	@Column(name="MSGS_DEPT")
	public String getDepartment() {
		return Department;
	}
	public void setDepartment(String department) {
		Department = department;
	}
	@Column(name="MSGS_BRANCH")
	public String getBranch() {
		return branch;
	}
	public void setBranch(String branch) {
		this.branch = branch;
	}
	@Column(name="MSGS_MSGSTS")
	public String getMsgStatus() {
		return msgStatus;
	}
	public void setMsgStatus(String msgStatus) {
		this.msgStatus = msgStatus;
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
	@Column(name="MSGS_CHANNELID")
	public String getChannel() {
		return channel;
	}
	public void setChannel(String channel) {
		this.channel = channel;
	}
	
	
	
}
