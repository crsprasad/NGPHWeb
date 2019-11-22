package com.logica.ngph.Entity;

import java.io.Serializable;
import java.sql.Clob;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="RAW_MSGS")
public class RawMessage implements Serializable {
	
	private static final long serialVersionUID = 1L;
	/*
	 	RAW_RECVDTIME
		RAW_MSG_VALSTSTUS
		RAW_MSGSREF
		RAW_MSG
		RAW_HOST
		RAW_DIRECTION
		RAW_CHNL
		RAW_MSG_VALSTATUS
	*/
	
	private String msgRef;
	private Clob rawMsg;
	private String rawHost;
	private String rawChnl;
	private String rawDirectn;
	private Timestamp rawRcvdTm;
	private int rawMsgValStatus;
	private String strMsg;
	private String errorCode;
	
	@Column(name="RAW_ERRCODE")
	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	
	/**
	 * @return the strMsg
	 */
	public String getStrMsg() {
		return strMsg;
	}

	/**
	 * @param strMsg the strMsg to set
	 */
	public void setStrMsg(String strMsg) {
		this.strMsg = strMsg;
	}

	@Id
	@Column (name="RAW_MSGSREF")
	public String getMsgRef() 
	{
		return msgRef;
	}
	
	@Column (name="RAW_MSG")
	public Clob getRawMsg() 
	{
		return rawMsg;
	}
	
	@Column (name ="RAW_HOST")
	public String getRawHost() 
	{
		return rawHost;
	}
	
	@Column (name ="RAW_CHNL")
	public String getRawChnl() 
	{
		return rawChnl;
	}
	
	@Column (name ="RAW_DIRECTION")
	public String getRawDirectn() 
	{
		return rawDirectn;
	}
	
	@Column (name = "RAW_RECVDTIME")
	public Timestamp getRawRcvdTm() 
	{
		return rawRcvdTm;
	}
	
	@Column (name = "RAW_MSG_VALSTATUS")
	public int getRawMsgValStatus() 
	{
		return rawMsgValStatus;
	}
	
	public void setMsgRef(String msgRef) {
		this.msgRef = msgRef;
	}
	public void setRawMsg(Clob rawMsg) {
		this.rawMsg = rawMsg;
	}

	public void setRawHost(String rawHost) {
		this.rawHost = rawHost;
	}
	public void setRawChnl(String rawChnl) {
		this.rawChnl = rawChnl;
	}
	public void setRawDirectn(String rawDirectn) {
		this.rawDirectn = rawDirectn;
	}
	public void setRawRcvdTm(Timestamp rawRcvdTm) {
		this.rawRcvdTm = rawRcvdTm;
	}
	public void setRawMsgValStatus(int rawMsgValStatus) {
		this.rawMsgValStatus = rawMsgValStatus;
	}
}
