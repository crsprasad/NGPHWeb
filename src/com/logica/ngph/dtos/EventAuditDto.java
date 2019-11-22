package com.logica.ngph.dtos;

import java.io.Serializable;

public class EventAuditDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	public String getEventId() {
		return eventId;
	}
	public void setEventId(String eventId) {
		this.eventId = eventId;
	}
	public String getEventDescription() {
		return eventDescription;
	}
	public void setEventDescription(String eventDescription) {
		this.eventDescription = eventDescription;
	}
	public String getEventDate() {
		return eventDate;
	}
	public void setEventDate(String eventDate) {
		this.eventDate = eventDate;
	}
	public String getEventTime() {
		return eventTime;
	}
	public void setEventTime(String eventTime) {
		this.eventTime = eventTime;
	}
	public String getEventMsgRef() {
		return eventMsgRef;
	}
	public void setEventMsgRef(String eventMsgRef) {
		this.eventMsgRef = eventMsgRef;
	}
	public String getEventTxnRef() {
		return eventTxnRef;
	}
	public void setEventTxnRef(String eventTxnRef) {
		this.eventTxnRef = eventTxnRef;
	}
	public String getEventBranch() {
		return eventBranch;
	}
	public void setEventBranch(String eventBranch) {
		this.eventBranch = eventBranch;
	}
	public String getEventDept() {
		return eventDept;
	}
	public void setEventDept(String eventDept) {
		this.eventDept = eventDept;
	}
	private String eventId;
	private String eventDescription;
	private String eventDate;
	private String eventTime;
	private String eventMsgRef;
	private String eventTxnRef;
	private String eventBranch;
	private String eventDept;
	
	

}
