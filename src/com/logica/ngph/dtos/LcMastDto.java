package com.logica.ngph.dtos;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

public class LcMastDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String msgRef;
	private String lcNo;
	private String lcType;
	private String lcDirection;
	private String lcIssueDate;
	private String lcExpireDate;
	private String lcAppicant;
	private String lcBenificiary;
	private String lcCurrency;
	private BigDecimal lcAmount;
	private int lcNoofMsg;
	private int lcStatus;
	private int lcNoofAmndment;
	private String lcNarrative;
	private String lcAdvisingBank;
	private String lcIssuingBank;
	private String lstModifiedTime;
	private String status;
	private String msgStatus;
	
	public String getMsgStatus() {
		return msgStatus;
	}
	public void setMsgStatus(String msgStatus) {
		this.msgStatus = msgStatus;
	}
	public String getMsgRef() {
		return msgRef;
	}
	public void setMsgRef(String msgRef) {
		this.msgRef = msgRef;
	}
	public String getLcNo() {
		return lcNo;
	}
	public void setLcNo(String lcNo) {
		this.lcNo = lcNo;
	}
	public String getLcType() {
		return lcType;
	}
	public void setLcType(String lcType) {
		this.lcType = lcType;
	}
	public String getLcDirection() {
		return lcDirection;
	}
	public void setLcDirection(String lcDirection) {
		this.lcDirection = lcDirection;
	}
	public String getLcIssueDate() {
		return lcIssueDate;
	}
	public void setLcIssueDate(String lcIssueDate) {
		this.lcIssueDate = lcIssueDate;
	}
	public String getLcExpireDate() {
		return lcExpireDate;
	}
	public void setLcExpireDate(String lcExpireDate) {
		this.lcExpireDate = lcExpireDate;
	}
	public String getLcAppicant() {
		return lcAppicant;
	}
	public void setLcAppicant(String lcAppicant) {
		this.lcAppicant = lcAppicant;
	}
	public String getLcBenificiary() {
		return lcBenificiary;
	}
	public void setLcBenificiary(String lcBenificiary) {
		this.lcBenificiary = lcBenificiary;
	}
	public String getLcCurrency() {
		return lcCurrency;
	}
	public void setLcCurrency(String lcCurrency) {
		this.lcCurrency = lcCurrency;
	}
	public BigDecimal getLcAmount() {
		return lcAmount;
	}
	public void setLcAmount(BigDecimal lcAmount) {
		this.lcAmount = lcAmount;
	}
	public int getLcNoofMsg() {
		return lcNoofMsg;
	}
	public void setLcNoofMsg(int lcNoofMsg) {
		this.lcNoofMsg = lcNoofMsg;
	}
	public int getLcStatus() {
		return lcStatus;
	}
	public void setLcStatus(int lcStatus) {
		this.lcStatus = lcStatus;
	}
	public int getLcNoofAmndment() {
		return lcNoofAmndment;
	}
	public void setLcNoofAmndment(int lcNoofAmndment) {
		this.lcNoofAmndment = lcNoofAmndment;
	}
	public String getLcNarrative() {
		return lcNarrative;
	}
	public void setLcNarrative(String lcNarrative) {
		this.lcNarrative = lcNarrative;
	}
	public String getLcAdvisingBank() {
		return lcAdvisingBank;
	}
	public void setLcAdvisingBank(String lcAdvisingBank) {
		this.lcAdvisingBank = lcAdvisingBank;
	}
	public String getLcIssuingBank() {
		return lcIssuingBank;
	}
	public void setLcIssuingBank(String lcIssuingBank) {
		this.lcIssuingBank = lcIssuingBank;
	}
	public String getLstModifiedTime() {
		return lstModifiedTime;
	}
	public void setLstModifiedTime(String lstModifiedTime) {
		this.lstModifiedTime = lstModifiedTime;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
		System.out.println("Status : -  "+status);
	}

}
