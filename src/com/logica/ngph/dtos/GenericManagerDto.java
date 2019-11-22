package com.logica.ngph.dtos;

import java.io.Serializable;
import java.sql.Timestamp;

public class GenericManagerDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String screenID;
	private String screenData;
	private String actionClass;
	private String crtdUserId;
	private Timestamp crtDate;
	
	public String getCrtdUserId() {
		return crtdUserId;
	}
	public void setCrtdUserId(String crtdUserId) {
		this.crtdUserId = crtdUserId;
	}
	public String getScreenID() {
		return screenID;
	}
	public void setScreenID(String screenID) {
		this.screenID = screenID;
	}
	public String getScreenData() {
		return screenData;
	}
	public void setScreenData(String screenData) {
		this.screenData = screenData;
	}
	public String getActionClass() {
		return actionClass;
	}
	public void setActionClass(String actionClass) {
		this.actionClass = actionClass;
	}
	public Timestamp getCrtDate() {
		return crtDate;
	}
	public void setCrtDate(Timestamp crtDate) {
		this.crtDate = crtDate;
	}
	

}
