package com.logica.ngph.dtos;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

public class SodEodTaskTDto implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7282358865593597494L;
	private String branch;
	private Date businessDate;
	private String userId;
	private String taskId;
	private String sodOrEod;
	private String status;
	private String tableName;
	private String operation;
	private int rowsAffected;
	private String statuss;
	private String reason;
	
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getStatuss() {
		return statuss;
	}
	public void setStatuss(String statuss) {
		this.statuss = statuss;
	}
	
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	
	public String getOperation() {
		return operation;
	}
	public void setOperation(String operation) {
		this.operation = operation;
	}
	public int getRowsAffected() {
		return rowsAffected;
	}
	public void setRowsAffected(int rowsAffected) {
		this.rowsAffected = rowsAffected;
	}
	
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public String getBranch() {
		return branch;
	}
	public void setBranch(String branch) {
		this.branch = branch;
	}
	public Date getBusinessDate() {
		return businessDate;
	}
	public void setBusinessDate(Date businessDate) {
		this.businessDate = businessDate;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getTaskId() {
		return taskId;
	}
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	public String getSodOrEod() {
		return sodOrEod;
	}
	public void setSodOrEod(String sodOrEod) {
		this.sodOrEod = sodOrEod;
	}
	

}
