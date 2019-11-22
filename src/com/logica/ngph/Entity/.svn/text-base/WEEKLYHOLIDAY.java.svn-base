package com.logica.ngph.Entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="TA_WEEKLYHOLIDAY")
public class WEEKLYHOLIDAY implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3604355664452021600L;
	/*BRANCH, 
	CURRENTDAY,
	ISWORKING*/
	private String branchCode;
	private int currentDay;
	private int isWorking;
	@Column(name="BRANCH")
	public String getBranchCode() {
		return branchCode;
	}
	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}
	@Id
	@Column(name="CURRENTDAY")
	public int getCurrentDay() {
		return currentDay;
	}
	public void setCurrentDay(int currentDay) {
		this.currentDay = currentDay;
	}
	@Column(name="ISWORKING")
	public int getIsWorking() {
		return isWorking;
	}
	public void setIsWorking(int isWorking) {
		this.isWorking = isWorking;
	}
}
