package com.logica.ngph.Entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="TA_HOLIDAYM")
public class HOLIDAYM implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3138628592586379508L;
	/*BRANCH,
	HOLIDAYDATE,
	CURRENCY*/
	
	private String branch;
	private Timestamp holiday;
	private String currency;
	@Column(name="BRANCH")
	public String getBranch() {
		return branch;
	}
	public void setBranch(String branch) {
		this.branch = branch;
	}
	@Id
	@Column(name="HOLIDAYDATE")
	public Timestamp getHoliday() {
		return holiday;
	}
	public void setHoliday(Timestamp holiday) {
		this.holiday = holiday;
	}
	@Column(name="CURRENCY")
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	
	
	
}
