package com.logica.ngph.Entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name ="TA_LIMITS")
public class Limits implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/*
	 * IDENTIFIER
LIMIT_FOR
CREDIT_LIMIT
DEBIT_LIMIT
AVAILABLE_CREDIT
CHANNEL
ACCOUNT_TYPE
AVAILABLE_CRLIMIT
AVAILABLE_DRLIMIT
	 * 
	 * 
	 * */
	
	private String identifier;
	private String limitFor;
	private String creditLimit;
	private String debitLimit;
	
	private String channel;
	private String availableCreditlimit;
	private String availableDebitLimit;
	@Column(name="AVAILABLE_CRLIMIT")
	public String getAvailableCreditlimit() {
		return availableCreditlimit;
	}
	public void setAvailableCreditlimit(String availableCreditlimit) {
		this.availableCreditlimit = availableCreditlimit;
	}
	@Column(name="AVAILABLE_DRLIMIT")
	public String getAvailableDebitLimit() {
		return availableDebitLimit;
	}
	public void setAvailableDebitLimit(String availableDebitLimit) {
		this.availableDebitLimit = availableDebitLimit;
	}
	@Id
	@Column(name="IDENTIFIER")
	public String getIdentifier() {
		return identifier;
	}
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}
	@Column(name="LIMIT_FOR")
	public String getLimitFor() {
		return limitFor;
	}
	public void setLimitFor(String limitFor) {
		this.limitFor = limitFor;
	}
	@Column(name="CREDIT_LIMIT")
	public String getCreditLimit() {
		return creditLimit;
	}
	public void setCreditLimit(String creditLimit) {
		this.creditLimit = creditLimit;
	}
	@Column(name="DEBIT_LIMIT")
	public String getDebitLimit() {
		return debitLimit;
	}
	public void setDebitLimit(String debitLimit) {
		this.debitLimit = debitLimit;
	}
	
	@Column(name="CHANNEL")
	public String getChannel() {
		return channel;
	}
	public void setChannel(String channel) {
		this.channel = channel;
	}
	
	
	

}
