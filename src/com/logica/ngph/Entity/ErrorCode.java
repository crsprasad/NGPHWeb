package com.logica.ngph.Entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="TA_ERRMSGSM")
public class ErrorCode implements Serializable {

	/**
	 * ERR_MSGCODE
ERR_CHNL
ERR_MAPCODE
ERR_MSGDESC
	 */
	private static final long serialVersionUID = 1L;
	private String errorCode;
	private String errorChannel;
	private String errorMapCode;
	private String errorMsgDesc;
	@Id
	@Column(name="ERR_MSGCODE")
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	@Column(name="ERR_CHNL")
	public String getErrorChannel() {
		return errorChannel;
	}
	public void setErrorChannel(String errorChannel) {
		this.errorChannel = errorChannel;
	}
	
	@Column(name="ERR_MAPCODE")
	public String getErrorMapCode() {
		return errorMapCode;
	}
	public void setErrorMapCode(String errorMapCode) {
		this.errorMapCode = errorMapCode;
	}
	
	@Column(name="ERR_MSGDESC")
	public String getErrorMsgDesc() {
		return errorMsgDesc;
	}
	public void setErrorMsgDesc(String errorMsgDesc) {
		this.errorMsgDesc = errorMsgDesc;
	}
	

}
