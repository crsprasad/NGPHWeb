package com.logica.ngph.Entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="SERVICEORCH")
public class ServiceOrchestartion implements Serializable{
	/*SRVC_MSG_TYPE
	SRVC_MSG_SUBTYPE
	SRVC_SERVICEID
	SRVC_CALLSEQ
	SRVC_MSGDIRECTION
	SRVC_ORCH_STREAM_ID	*/
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String SRVC_MSG_TYPE;
	private String SRVC_MSG_SUBTYPE;
	private String SRVC_SERVICEID;
	private String SRVC_CALLSEQ;
	private String SRVC_MSGDIRECTION;
	private String SRVC_ORCH;
	@Column(name="SRVC_MSG_TYPE")
	public String getSRVC_MSG_TYPE() {
		return SRVC_MSG_TYPE;
	}
	public void setSRVC_MSG_TYPE(String sRVC_MSG_TYPE) {
		SRVC_MSG_TYPE = sRVC_MSG_TYPE;
	}
	@Column(name="SRVC_MSG_SUBTYPE")
	public String getSRVC_MSG_SUBTYPE() {
		return SRVC_MSG_SUBTYPE;
	}
	public void setSRVC_MSG_SUBTYPE(String sRVC_MSG_SUBTYPE) {
		SRVC_MSG_SUBTYPE = sRVC_MSG_SUBTYPE;
	}
	@Column(name="SRVC_SERVICEID")
	public String getSRVC_SERVICEID() {
		return SRVC_SERVICEID;
	}
	public void setSRVC_SERVICEID(String sRVC_SERVICEID) {
		SRVC_SERVICEID = sRVC_SERVICEID;
	}
	@Id
	@Column(name="SRVC_CALLSEQ")
	public String getSRVC_CALLSEQ() {
		return SRVC_CALLSEQ;
	}
	
	public void setSRVC_CALLSEQ(String sRVC_CALLSEQ) {
		SRVC_CALLSEQ = sRVC_CALLSEQ;
	}
	@Column(name="SRVC_MSGDIRECTION")
	public String getSRVC_MSGDIRECTION() {
		return SRVC_MSGDIRECTION;
	}
	public void setSRVC_MSGDIRECTION(String sRVC_MSGDIRECTION) {
		SRVC_MSGDIRECTION = sRVC_MSGDIRECTION;
	}
	@Column(name="SRVC_ORCH_STREAM_ID")
	public String getSRVC_ORCH() {
		return SRVC_ORCH;
	}
	public void setSRVC_ORCH(String sRVC_ORCH) {
		SRVC_ORCH = sRVC_ORCH;
	}

}
