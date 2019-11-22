package com.logica.ngph.Entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="TA_SODEODTASKT")
public class SODEODTASKT implements Serializable {
/**
	 * 
	 */
	private static final long serialVersionUID = -485684654783452715L;
/*	BRANCH,
	BUSDATE, 
	USERID,
	ID,
	SODEODT_FOR,
	STATUS,
	ERROR,
	STARTTIME, 
	ENDTIME
*/
	
	private String branch;
	private Date  businessDate;
	private String userId;
	private String taskId;
	private String sodOrEod;
	private int status;
	private String error;
	private Timestamp startTime;
	private Timestamp endTime;
	@Column(name="BRANCH")
	public String getBranch() {
		return branch;
	}
	public void setBranch(String branch) {
		this.branch = branch;
	}
	@Column(name="BUSDATE")
	public Date getBusinessDate() {
		return businessDate;
	}
	public void setBusinessDate(Date businessDate) {
		this.businessDate = businessDate;
	}
	@Column(name="USERID")
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	@Id
	@Column(name="ID")
	public String getTaskId() {
		return taskId;
	}
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	@Column(name="SODEODT_FOR")
	public String getSodOrEod() {
		return sodOrEod;
	}
	public void setSodOrEod(String sodOrEod) {
		this.sodOrEod = sodOrEod;
	}
	@Column(name="STATUS")
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	@Column(name="ERROR")
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	@Column(name="STARTTIME")
	public Timestamp getStartTime() {
		return startTime;
	}
	public void setStartTime(Timestamp startTime) {
		this.startTime = startTime;
	}
	@Column(name="ENDTIME")
	public Timestamp getEndTime() {
		return endTime;
	}
	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}
	
}
