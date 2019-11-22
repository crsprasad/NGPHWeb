package com.logica.ngph.Entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="TA_BUSINESSDAYM")
public class BusinessDayM implements Serializable{
	/*BUSDAY_BRANCH,
	BUSDAY_DATE, 
	BUSDAY_STATUS*/
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 319160322331512049L;
	private String branch;
	private Timestamp businessDay;
	private String businessDayStatus;
	
	@Id
	@Column(name="BUSDAY_BRANCH")
	public String getBranch() {
		return branch;
	}
	public void setBranch(String branch) {
		this.branch = branch;
	}
	@Column(name="BUSDAY_DATE")
	public Timestamp getBusinessDay() {
		return businessDay;
	}
	public void setBusinessDay(Timestamp businessDay) {
		this.businessDay = businessDay;
	}
	@Column(name="BUSDAY_STATUS")
	public String getBusinessDayStatus() {
		return businessDayStatus;
	}
	public void setBusinessDayStatus(String businessDayStatus) {
		this.businessDayStatus = businessDayStatus;
	} 
}
