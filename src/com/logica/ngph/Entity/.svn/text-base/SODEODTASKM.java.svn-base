package com.logica.ngph.Entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="TA_SODEODTASKM")
public class SODEODTASKM implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1367504135611995534L;
	/*BRANCH, 
	ID,
	SODEOD_DESC,
	SODEOD_FOR,
	SODEOD_SEQUENCE,
	PARAM, 
	DELETED*/
	
	private String branch;
	private String taskId;
	private String taskDesc;
	private String taskFor;
	private int taskSequence;
	private String param;
	private int deleted;
	@Column(name="BRANCH")
	public String getBranch() {
		return branch;
	}
	public void setBranch(String branch) {
		this.branch = branch;
	}
	@Id
	@Column(name="ID")
	public String getTaskId() {
		return taskId;
	}
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	@Column(name="SODEOD_DESC")
	public String getTaskDesc() {
		return taskDesc;
	}
	public void setTaskDesc(String taskDesc) {
		this.taskDesc = taskDesc;
	}
	@Column(name="SODEOD_FOR")
	public String getTaskFor() {
		return taskFor;
	}
	public void setTaskFor(String taskFor) {
		this.taskFor = taskFor;
	}
	@Column(name="SODEOD_SEQUENCE")
	public int getTaskSequence() {
		return taskSequence;
	}
	public void setTaskSequence(int taskSequence) {
		this.taskSequence = taskSequence;
	}
	@Column(name="PARAM")
	public String getParam() {
		return param;
	}
	public void setParam(String param) {
		this.param = param;
	}
	@Column(name="DELETED")
	public int getDeleted() {
		return deleted;
	}
	public void setDeleted(int deleted) {
		this.deleted = deleted;
	}
	

}
