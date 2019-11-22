package com.logica.ngph.Entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="TA_LC_MAST")
public class LcMast implements Serializable{

	/**
	 MSGS_MSGREF
LC_NUMBER
LC_TYPE
LC_DIRECTION
LC_ISSUE_DT
LC_EXP_DT
LC_APPLICANT
LC_BENFICIARY
LC_CURRENCY
LC_AMOUNT
LC_NOOF_MSGS
LC_STATUS
LC_NOOF_AMNDMNTS
LC_NARRATIVE
	 */
	private static final long serialVersionUID = 1L;
	private String msgRef;
	//private String lcNo;
	private String lcType;
	//private String lcDirection;
	private Timestamp lcIssueDate;
	private Timestamp lcExpireDate;
	private String lcAppicant;
	private String lcBenificiary;
	private String lcCurrency;
	private BigDecimal lcAmount;
	private int lcNoofMsg;
	private int lcStatus;
	private int lcNoofAmndment;
	private String lcNarrative;
	private String lcAdvisingBank;
	private String lstModifiedTime;
	
	private CompositeKeyForLcMast compositeKeyForLcMast;
	
	
	
	
	@Id
	public CompositeKeyForLcMast getCompositeKeyForLcMast() {
		return compositeKeyForLcMast;
	}
	public void setCompositeKeyForLcMast(CompositeKeyForLcMast compositeKeyForLcMast) {
		this.compositeKeyForLcMast = compositeKeyForLcMast;
	}
	/*private String status;
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}*/
	@Column(name="LC_LASTMODIFIEDTIME")
	public String getLstModifiedTime() {
		return lstModifiedTime;
	}
	public void setLstModifiedTime(String lstModifiedTime) {
		this.lstModifiedTime = lstModifiedTime;
	}
	@Column(name="LC_ADVISINGBANK")
	public String getLcAdvisingBank() {
		return lcAdvisingBank;
	}
	public void setLcAdvisingBank(String lcAdvisingBank) {
		this.lcAdvisingBank = lcAdvisingBank;
	}
	@Column(name="MSGS_MSGREF")
	public String getMsgRef() {
		return msgRef;
	}
	public void setMsgRef(String msgRef) {
		this.msgRef = msgRef;
	}
/*	@Id
	@Column(name="LC_NUMBER")
	public String getLcNo() {
		return lcNo;
	}
	public void setLcNo(String lcNo) {
		this.lcNo = lcNo;
	}*/
	@Column(name="LC_TYPE")
	public String getLcType() {
		return lcType;
	}
	public void setLcType(String lcType) {
		this.lcType = lcType;
	}
/*	@Column(name="LC_DIRECTION")
	public String getLcDirection() {
		return lcDirection;
	}
	public void setLcDirection(String lcDirection) {
		this.lcDirection = lcDirection;
	}*/
	@Column(name="LC_ISSUE_DT")
	public Timestamp getLcIssueDate() {
		return lcIssueDate;
	}
	public void setLcIssueDate(Timestamp lcIssueDate) {
		this.lcIssueDate = lcIssueDate;
	}
	@Column(name="LC_EXP_DT")
	public Timestamp getLcExpireDate() {
		return lcExpireDate;
	}
	public void setLcExpireDate(Timestamp lcExpireDate) {
		this.lcExpireDate = lcExpireDate;
	}
	@Column(name="LC_APPLICANT")
	public String getLcAppicant() {
		return lcAppicant;
	}
	public void setLcAppicant(String lcAppicant) {
		this.lcAppicant = lcAppicant;
	}
	@Column(name="LC_BENFICIARY")
	public String getLcBenificiary() {
		return lcBenificiary;
	}
	public void setLcBenificiary(String lcBenificiary) {
		this.lcBenificiary = lcBenificiary;
	}
	@Column(name="LC_CURRENCY")
	public String getLcCurrency() {
		return lcCurrency;
	}
	public void setLcCurrency(String lcCurrency) {
		this.lcCurrency = lcCurrency;
	}
	@Column(name="LC_AMOUNT")
	public BigDecimal getLcAmount() {
		return lcAmount;
	}
	public void setLcAmount(BigDecimal lcAmount) {
		this.lcAmount = lcAmount;
	}
	@Column(name="LC_NOOF_MSGS")
	public int getLcNoofMsg() {
		return lcNoofMsg;
	}
	public void setLcNoofMsg(int lcNoofMsg) {
		this.lcNoofMsg = lcNoofMsg;
	}
	@Column(name="LC_STATUS")
	public int getLcStatus() {
		return lcStatus;
	}
	public void setLcStatus(int lcStatus) {
		this.lcStatus = lcStatus;
	}
	@Column(name="LC_NOOF_AMNDMNTS")
	public int getLcNoofAmndment() {
		return lcNoofAmndment;
	}
	public void setLcNoofAmndment(int lcNoofAmndment) {
		this.lcNoofAmndment = lcNoofAmndment;
	}
	@Column(name="LC_NARRATIVE")
	public String getLcNarrative() {
		return lcNarrative;
	}
	public void setLcNarrative(String lcNarrative) {
		this.lcNarrative = lcNarrative;
	}
	

}
