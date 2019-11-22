package com.logica.ngph.Entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="TA_AUTHGRPM")
public class AuthorizationGRPM implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String branch;
	private String groupID;
	private String groupName;
	private String SUPID;
	private int supervisorSequence;
	private int ismanditatory;
	@Column (name="BRANCH")
	public String getBranch() {
		return branch;
	}
	public void setBranch(String branch) {
		this.branch = branch;
	}
	@Id
	@Column (name="GROUPID")
	public String getGroupID() {
		return groupID;
	}
	public void setGroupID(String groupID) {
		this.groupID = groupID;
	}
	@Column (name="GROUPNAME")
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	
	@Column (name="SUPID")
	public String getSUPID() {
		return SUPID;
	}
	public void setSUPID(String sUPID) {
		SUPID = sUPID;
	}
	@Column (name="SUPSEQ")
	public int getSupervisorSequence() {
		return supervisorSequence;
	}
	public void setSupervisorSequence(int supervisorSequence) {
		this.supervisorSequence = supervisorSequence;
	}
	@Column (name="ISMANDATORY")
	public int getIsmanditatory() {
		return ismanditatory;
	}
	public void setIsmanditatory(int ismanditatory) {
		this.ismanditatory = ismanditatory;
	}

}
