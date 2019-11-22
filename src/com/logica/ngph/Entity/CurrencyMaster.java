package com.logica.ngph.Entity;

import java.io.Serializable;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author guptast
 *This class is mapped with table TA_CURRENCY_MAST
 */
@Entity
@Table(name="TA_CURRENCY_MAST")
public class CurrencyMaster implements Serializable{
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String currencyCode;
	private String currencyName;
	private String currencyDecimal;
	
	
	
	@Id
	@Column(name ="CUR_CODE")
	public String getCurrencyCode() {
		return currencyCode;
	}
	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}
	@Column(name ="CUR_NAME")
	public String getCurrencyName() {
		return currencyName;
	}
	public void setCurrencyName(String currencyName) {
		this.currencyName = currencyName;
	}
	@Column(name ="CUR_DECIMAL")
	public String getCurrencyDecimal() {
		return currencyDecimal;
	}
	public void setCurrencyDecimal(String currencyDecimal) {
		this.currencyDecimal = currencyDecimal;
	}

}
