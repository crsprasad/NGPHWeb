package com.logica.ngph.Entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="TA_EVENT_MAST")
public class EventMaster implements Serializable{
	
	/*EVENTM_EVENTID,
	EVENTM_DESC, 
	EVENTM_ALERTABLE, 
	EVENTM_SEVERITY
	
	EVENTM_ALERT_TYPE
	EVENTM_ALERT_TO
	EVENTM_ALERT_FOR
	EVENTM_ALERT_CONSOLIDATE
	*/
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3842814911227203036L;
	private String eventId;
	private String eventDesc;
	private int eventAlertable;
	private String eventseverity;
	private String eventAlertType;
	private String eventAlertTO;
	private String eventAlertFor;
	private String eventAlertConsolidate;
	@Column(name="EVENTM_ALERT_TYPE")
	public String getEventAlertType() {
		return eventAlertType;
	}
	public void setEventAlertType(String eventAlertType) {
		this.eventAlertType = eventAlertType;
	}
	@Column(name="EVENTM_ALERT_TO")
	public String getEventAlertTO() {
		return eventAlertTO;
	}
	public void setEventAlertTO(String eventAlertTO) {
		this.eventAlertTO = eventAlertTO;
	}
	@Column(name="EVENTM_ALERT_FOR")
	public String getEventAlertFor() {
		return eventAlertFor;
	}
	public void setEventAlertFor(String eventAlertFor) {
		this.eventAlertFor = eventAlertFor;
	}
	@Column(name="EVENTM_ALERT_CONSOLIDATE")
	public String getEventAlertConsolidate() {
		return eventAlertConsolidate;
	}
	public void setEventAlertConsolidate(String eventAlertConsolidate) {
		this.eventAlertConsolidate = eventAlertConsolidate;
	}
	@Id
	@Column(name="EVENTM_EVENTID")
	public String getEventId() {
		return eventId;
	}
	public void setEventId(String eventId) {
		this.eventId = eventId;
	}
	
	@Column(name="EVENTM_DESC")
	public String getEventDesc() {
		return eventDesc;
	}
	public void setEventDesc(String eventDesc) {
		this.eventDesc = eventDesc;
	}
	
	@Column(name="EVENTM_ALERTABLE")
	public int getEventAlertable() {
		return eventAlertable;
	}
	public void setEventAlertable(int eventAlertable) {
		this.eventAlertable = eventAlertable;
	}
	
	@Column(name="EVENTM_SEVERITY")
	public String getEventseverity() {
		return eventseverity;
	}
	public void setEventseverity(String eventseverity) {
		this.eventseverity = eventseverity;
	}
	
	

}
