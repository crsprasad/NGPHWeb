package com.logica.ngph.Entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="TA_IMPS_TX")
public class IMPSTransaction implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String impsMsgRef;
	private String impsMsgType;
	private String impsMsgSubType;
	
	@Id
	@Column(name ="IMPS_MSGREF")
	public String getImpsMsgRef() {
		return impsMsgRef;
	}
	public void setImpsMsgRef(String impsMsgRef) {
		this.impsMsgRef = impsMsgRef;
	}
	public String getImpsMsgType() {
		return impsMsgType;
	}
	public void setImpsMsgType(String impsMsgType) {
		this.impsMsgType = impsMsgType;
	}
	public String getImpsMsgSubType() {
		return impsMsgSubType;
	}
	public void setImpsMsgSubType(String impsMsgSubType) {
		this.impsMsgSubType = impsMsgSubType;
	}
	
	
}
