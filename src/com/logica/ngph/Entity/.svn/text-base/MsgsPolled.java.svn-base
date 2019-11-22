package com.logica.ngph.Entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="TA_MSGSPOLLED")
public class MsgsPolled implements Serializable{
	/*MSGS_MSGREF, 
	IN_TIME, 
	LASTORCHSERVICEIDCALLED, 
	MARKED_OUT_TIME, 
	POLL_STATUS*/
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7716300985824141567L;
	private String msgsRef;
	private String lastOrchServiceId;
	private String pollStatus;
	private Timestamp markedOutTime;
	@Column(name="MARKED_OUT_TIME")
	public Timestamp getMarkedOutTime() {
		return markedOutTime;
	}
	public void setMarkedOutTime(Timestamp markedOutTime) {
		this.markedOutTime = markedOutTime;
	}
	@Id
	@Column(name="MSGS_MSGREF")
	public String getMsgsRef() {
		return msgsRef;
	}
	public void setMsgsRef(String msgsRef) {
		this.msgsRef = msgsRef;
	}
	@Column(name="LASTORCHSERVICEIDCALLED")
	public String getLastOrchServiceId() {
		return lastOrchServiceId;
	}
	public void setLastOrchServiceId(String lastOrchServiceId) {
		this.lastOrchServiceId = lastOrchServiceId;
	}
	@Column(name="POLL_STATUS")
	public String getPollStatus() {
		return pollStatus;
	}
	public void setPollStatus(String pollStatus) {
		this.pollStatus = pollStatus;
	}

}
