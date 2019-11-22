package com.logica.ngph.Entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Clob;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="TA_BG_MAST")
public class BgMast implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String msgRef;
	private String bgNumber;
	private Integer noOfMsg;
	private String bgDirection;
	private Timestamp bgIssueDate;
	private String bgCreateType;
	private String bgRuleCode;
	private String bgRuleDesc;
	private Clob bgDetails;
	private String bgNarration;
	private BigDecimal bgAmount;
	private Integer bgStatus;
	private Integer bgNoOfAmntmnt;
	private Timestamp bgLastModifiedTime;
	private String advisingBank;
	private String details;
	private String issuingBank;
	
	
	@Column(name="BG_ISSUINGBANK")
	public String getIssuingBank() {
		return issuingBank;
	}
	
	public void setIssuingBank(String issuingBank) {
		this.issuingBank = issuingBank;
	}
	@Column(name="DETAILS")
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	@Column(name="MSGS_MSGREF")
	public String getMsgRef() {
		return msgRef;
	}
	public void setMsgRef(String msgRef) {
		this.msgRef = msgRef;
	}
	@Id
	@Column(name="BG_NUMBER")
	public String getBgNumber() {
		return bgNumber;
	}
	public void setBgNumber(String bgNumber) {
		this.bgNumber = bgNumber;
	}
	@Column(name="BG_NOOF_MSGS")
	public Integer getNoOfMsg() {
		return noOfMsg;
	}
	public void setNoOfMsg(Integer noOfMsg) {
		this.noOfMsg = noOfMsg;
	}
	@Column(name="BG_DIRECTION")
	public String getBgDirection() {
		return bgDirection;
	}
	public void setBgDirection(String bgDirection) {
		this.bgDirection = bgDirection;
	}
	@Column(name="BG_ISSUE_DT")
	public Timestamp getBgIssueDate() {
		return bgIssueDate;
	}
	public void setBgIssueDate(Timestamp bgIssueDate) {
		this.bgIssueDate = bgIssueDate;
	}
	@Column(name="BG_CREATE_TYPE")
	public String getBgCreateType() {
		return bgCreateType;
	}
	public void setBgCreateType(String bgCreateType) {
		this.bgCreateType = bgCreateType;
	}
	@Column(name="BG_RULES_CODE")
	public String getBgRuleCode() {
		return bgRuleCode;
	}
	public void setBgRuleCode(String bgRuleCode) {
		this.bgRuleCode = bgRuleCode;
	}
	@Column(name="BG_RULES_DESC")
	public String getBgRuleDesc() {
		return bgRuleDesc;
	}
	public void setBgRuleDesc(String bgRuleDesc) {
		this.bgRuleDesc = bgRuleDesc;
	}
	@Column(name="BG_DETAILS")
	public Clob getBgDetails() {
		return bgDetails;
	}
	public void setBgDetails(Clob bgDetails) {
		this.bgDetails = bgDetails;
	}
	@Column(name="BG_NARRATION")
	public String getBgNarration() {
		return bgNarration;
	}
	public void setBgNarration(String bgNarration) {
		this.bgNarration = bgNarration;
	}
	@Column(name="BG_AMOUNT")
	public BigDecimal getBgAmount() {
		return bgAmount;
	}
	public void setBgAmount(BigDecimal bgAmount) {
		this.bgAmount = bgAmount;
	}
	@Column(name="BG_STATUS")
	public Integer getBgStatus() {
		return bgStatus;
	}
	public void setBgStatus(Integer bgStatus) {
		this.bgStatus = bgStatus;
	}
	@Column(name="BG_NOOF_AMNDMNTS")
	public Integer getBgNoOfAmntmnt() {
		return bgNoOfAmntmnt;
	}
	public void setBgNoOfAmntmnt(Integer bgNoOfAmntmnt) {
		this.bgNoOfAmntmnt = bgNoOfAmntmnt;
	}
	@Column(name="BG_LASTMODIFIED_DATE")
	public Timestamp getBgLastModifiedTime() {
		return bgLastModifiedTime;
	}
	public void setBgLastModifiedTime(Timestamp bgLastModifiedTime) {
		this.bgLastModifiedTime = bgLastModifiedTime;
	}
	@Column(name="BG_ADVISINGBANK")
	public String getAdvisingBank() {
		return advisingBank;
	}
	public void setAdvisingBank(String advisingBank) {
		this.advisingBank = advisingBank;
	}
	

}
