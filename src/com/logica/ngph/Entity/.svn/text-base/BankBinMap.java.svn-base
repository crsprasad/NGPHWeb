package com.logica.ngph.Entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="TA_BNK_BIN_MAP")
public class BankBinMap implements Serializable {

	/**
	 * BNK_CODE
IMPS_NBIN
	 */
	private static final long serialVersionUID = 1L;
	private String bankCode;
	private String impsBin;
	@Id
	@Column(name="BNK_CODE")
	public String getBankCode() {
		return bankCode;
	}
	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}
	@Column(name="IMPS_NBIN")
	public String getImpsBin() {
		return impsBin;
	}
	public void setImpsBin(String impsBin) {
		this.impsBin = impsBin;
	}
	
	

}
