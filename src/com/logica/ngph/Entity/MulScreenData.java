package com.logica.ngph.Entity;

import java.io.Serializable;
import java.sql.Clob;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="TXN_MULTSCREENDATA")
public class MulScreenData implements Serializable{
	/**
	 * 
	 */
	
	private static final long serialVersionUID = -707851555872194934L;
	private String trnRef;
	private String sequence;
	private Clob screenData;
	@Id
	@Column(name="TXNREF")
	public String getTrnRef() {
		return trnRef;
	}
	public void setTrnRef(String trnRef) {
		this.trnRef = trnRef;
	}
	@Column(name="SEQUENCE")
	public String getSequence() {
		return sequence;
	}
	public void setSequence(String sequence) {
		this.sequence = sequence;
	}
	@Column(name ="SCREENDATA")
	public Clob getScreenData() {
		return screenData;
	}
	public void setScreenData(Clob screenData) {
		this.screenData = screenData;
	}
	

}
