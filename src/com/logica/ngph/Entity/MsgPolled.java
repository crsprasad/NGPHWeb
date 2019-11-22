package com.logica.ngph.Entity;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="TA_MSGSPOLLED")
public class MsgPolled implements Serializable{

	private static final long serialVersionUID = 1L;
	private String msgRef;
	private Timestamp inTime;
	private String polledStatus;
	private String serviceID;
	private String polledReason;
	@Column(name="POLL_REASON")
	public String getPolledReason() {
		return polledReason;
	}
	public void setPolledReason(String polledReason) {
		this.polledReason = polledReason;
	}
	@Column(name="LASTORCHSERVICEIDCALLED")
	public String getServiceID() {
		return serviceID;
	}
	public void setServiceID(String serviceID) {
		this.serviceID = serviceID;
	}
	@Id
	@Column(name ="MSGS_MSGREF")
	public String getMsgRef() {
		return msgRef;
	}
	public void setMsgRef(String msgRef) {
		this.msgRef = msgRef;
	}
	@Column(name="IN_TIME")
	public Timestamp getInTime() {
		return inTime;
	}
	public void setInTime(Timestamp inTime) {
		this.inTime = inTime;
	}
	@Column(name="POLL_STATUS")
	public String getPolledStatus() {
		return polledStatus;
	}
	public void setPolledStatus(String polledStatus) {
		this.polledStatus = polledStatus;
	}

}
