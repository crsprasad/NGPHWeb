package com.logica.ngph.Entity;

import java.io.Serializable;
import java.sql.Timestamp;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;





@Entity
@Table(name="TA_EVENT_AUDIT")
public class EventAudit implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -890663941803991645L;
	/*AUDIT_EVENTID
AUDIT_EVENTDESC
AUDIT_EVENTTIMESTAMP
AUDIT_TXNREF
AUDIT_MSGREF
AUDIT_SOURCE
AUDIT_BRANCH
AUDIT_DEPT
	
*/
	
	private String auditEventId;
	private String auditEventDesc;
	//private Date auditEventDetails;
	private String auditTransaction;
	//private String auditMonitoring;
	private String auditSource;
	private String auditBranch;
	private String auditDept;
	private Timestamp auditEventTime;
	private String auditMsgref;
	

	
	@Column(name="AUDIT_MSGREF")
	public String getAuditMsgref() {
		return auditMsgref;
	}
	public void setAuditMsgref(String auditMsgref) {
		this.auditMsgref = auditMsgref;
	}
	@Id
	@Column(name="AUDIT_EVENTID")
	public String getAuditEventId() {
		return auditEventId;
	}
	@Column(name="AUDIT_EVENTTIMESTAMP")
	public Timestamp getAuditEventTime() {
		return auditEventTime;
	}
	public void setAuditEventTime(Timestamp auditEventTime) {
		this.auditEventTime = auditEventTime;
	}
	public void setAuditEventId(String auditEventId) {
		this.auditEventId = auditEventId;
	}
	
	@Column(name="AUDIT_EVENTDESC")
	public String getAuditEventDesc() {
		return auditEventDesc;
	}
	public void setAuditEventDesc(String auditEventDesc) {
		this.auditEventDesc = auditEventDesc;
	}
	
	
	
	@Column(name="AUDIT_TXNREF")
	public String getAuditTransaction() {
		return auditTransaction;
	}
	public void setAuditTransaction(String auditTransaction) {
		this.auditTransaction = auditTransaction;
	}
	
	/*@Column(name="AUDIT_MRN")
	public String getAuditMonitoring() {
		return auditMonitoring;
	}
	public void setAuditMonitoring(String auditMonitoring) {
		this.auditMonitoring = auditMonitoring;
	}*/
	
	@Column(name="AUDIT_SOURCE")
	public String getAuditSource() {
		return auditSource;
	}
	public void setAuditSource(String auditSource) {
		this.auditSource = auditSource;
	}
	
	@Column(name="AUDIT_BRANCH")
	public String getAuditBranch() {
		return auditBranch;
	}
	public void setAuditBranch(String auditBranch) {
		this.auditBranch = auditBranch;
	}
	
	@Column(name="AUDIT_DEPT")
	public String getAuditDept() {
		return auditDept;
	}
	public void setAuditDept(String auditDept) {
		this.auditDept = auditDept;
	}
	//@Column(name="AUDIT_EVENTDT", nullable = false,columnDefinition="TimeStamp default sysdate")
	//@Temporal(TemporalType.TIMESTAMP)
	//@Column(name="AUDIT_EVENTDT" ,columnDefinition="TIMESTAMP default CURRENT_TIMESTAMP",insertable=false,updatable=false)
	//@org.hibernate.annotations.Generated(org.hibernate.annotations.GenerationTime.ALWAYS)
	
	
	
	
}
