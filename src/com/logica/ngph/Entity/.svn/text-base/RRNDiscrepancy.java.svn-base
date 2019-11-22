package com.logica.ngph.Entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="TA_DISCREPANCY_DETAILS")
public class RRNDiscrepancy  implements Serializable{

	/**
	 * RRN
TRN_REF
DIRECTION
TRN_DATE
	 */
	private static final long serialVersionUID = 1L;
	
	private String RRN;
	private String TRN_REF;
	private String direction;
	private String TRN_DATE;
	@Id
	@Column(name="RRN")
	public String getRRN() {
		return RRN;
	}
	public void setRRN(String rRN) {
		RRN = rRN;
	}
	@Column(name="DIRECTION")
	public String getDirection() {
		return direction;
	}
	public void setDirection(String direction) {
		this.direction = direction;
	}
	@Column(name="TRN_DATE")
	public String getTRN_DATE() {
		return TRN_DATE;
	}
	public void setTRN_DATE(String tRN_DATE) {
		TRN_DATE = tRN_DATE;
	}
	@Column(name="TRN_REF")
	public String getTRN_REF() {
		return TRN_REF;
	}
	public void setTRN_REF(String tRN_REF) {
		TRN_REF = tRN_REF;
	} 
}
