package com.logica.ngph.dtos;

import java.io.Serializable;
import java.sql.Timestamp;

import com.logica.ngph.common.PaymentStatusEnum;

public class InformationDto implements Serializable{
	private static final long serialVersionUID = 1L;
	private String msgRef;
	private String direction;
	private String information;
	private String squenceNo;
	private String department;
	private String branch;
	private String msgType;
	private String subMsgType;
	private String txnReference;
	private String senderBank;
	private String receiverBank;
	private String relatedRefrence;
	private String lastModTime;
	private PaymentStatusEnum msgStatus;
	
	public String getMsgRef() {
		return msgRef;
	}
	public void setMsgRef(String msgRef) {
		this.msgRef = msgRef;
	}
	public String getDirection() {
		return direction;
	}
	public void setDirection(String direction) {
		this.direction = direction;
	}
	public String getInformation() {
		return information;
	}
	public void setInformation(String information) {
		this.information = information;
	}
	public String getSquenceNo() {
		return squenceNo;
	}
	public void setSquenceNo(String squenceNo) {
		this.squenceNo = squenceNo;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getBranch() {
		return branch;
	}
	public void setBranch(String branch) {
		this.branch = branch;
	}
	public String getMsgType() {
		return msgType;
	}
	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}
	public String getSubMsgType() {
		return subMsgType;
	}
	public void setSubMsgType(String subMsgType) {
		this.subMsgType = subMsgType;
	}
	public String getTxnReference() {
		return txnReference;
	}
	public void setTxnReference(String txnReference) {
		this.txnReference = txnReference;
	}
	public String getSenderBank() {
		return senderBank;
	}
	public void setSenderBank(String senderBank) {
		this.senderBank = senderBank;
	}
	public String getReceiverBank() {
		return receiverBank;
	}
	public void setReceiverBank(String receiverBank) {
		this.receiverBank = receiverBank;
	}
	public String getRelatedRefrence() {
		return relatedRefrence;
	}
	public void setRelatedRefrence(String relatedRefrence) {
		this.relatedRefrence = relatedRefrence;
	}
	public String getLastModTime() {
		return lastModTime;
	}
	public void setLastModTime(String lastModTime) {
		this.lastModTime = lastModTime;
	}
	public void setMsgStatus(PaymentStatusEnum paymentStatusEnum) {
		this.msgStatus = paymentStatusEnum;
	}
	public PaymentStatusEnum getMsgStatus() {
		return msgStatus;
	}
	
}
