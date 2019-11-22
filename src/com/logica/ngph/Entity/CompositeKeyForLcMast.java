package com.logica.ngph.Entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
@Embeddable
public class CompositeKeyForLcMast implements Serializable{

	private String lcNo;
	private String lcDirection;
	private String lcIssuingBank;
	
	public CompositeKeyForLcMast(String lcNo, String lcDirection, String lcIssuingBank) {
		super();
		this.lcNo = lcNo;
		this.lcDirection = lcDirection;
		this.lcIssuingBank = lcIssuingBank;
	}
	@Column(name="LC_NUMBER")
	public String getLcNo() {
		return lcNo;
	}
	public void setLcNo(String lcNo) {
		this.lcNo = lcNo;
	}
	@Column(name="LC_DIRECTION")
	public String getLcDirection() {
		return lcDirection;
	}
	public void setLcDirection(String lcDirection) {
		this.lcDirection = lcDirection;
	}
	@Column(name="LC_ISSUING_BANK")
	public String getLcIssuingBank() {
		return lcIssuingBank;
	}
	public void setLcIssuingBank(String lcIssuingBank) {
		this.lcIssuingBank = lcIssuingBank;
	}

	
}
