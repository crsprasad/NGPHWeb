package com.logica.ngph.Entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="TA_LIQUIDITY")
public class Liquidity {
	private static final long serialVersionUID = -1521727134634279445L;
	private String currency;
	private String debits;
	private String credits;
	private String openingBalance;
	private String closingBalance;
	private String businessDay;
	
	
	
	@Id
	@Column(name="MSGS_CUR")
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	@Column(name="DEBITS")
	public String getDebits() {
		return debits;
	}
	public void setDebits(String debits) {
		this.debits = debits;
	}
	@Column(name="CREDITS")
	public String getCredits() {
		return credits;
	}
	public void setCredits(String credits) {
		this.credits = credits;
	}
	@Column(name="OPENING_BAL")
	public String getOpeningBalance() {
		return openingBalance;
	}
	public void setOpeningBalance(String openingBalance) {
		this.openingBalance = openingBalance;
	}
	@Column(name="CLOSING_BAL")
	public String getClosingBalance() {
		return closingBalance;
	}
	public void setClosingBalance(String closingBalance) {
		this.closingBalance = closingBalance;
	}
	@Column(name="BUS_DATE")
	public String getBusinessDay() {
		return businessDay;
	}
	public void setBusinessDay(String businessDay) {
		this.businessDay = businessDay;
	}
	
	

}
