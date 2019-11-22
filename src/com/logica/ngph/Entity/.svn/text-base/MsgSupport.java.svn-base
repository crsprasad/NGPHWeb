package com.logica.ngph.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table(name="MSG_SUPPORT")
public class MsgSupport {
	
	/*SUPPORT_MSGTYPE, 
	SUPPORT_SUBMSGTYPE, 
	SUPPORT_MSG_DIRECTION, 
	SUPPORT_SOURCE, 
	SUPPORT_DEST, 
	SUPPORT_ACK*/
	private String supportMsgType;
	private String supportSubMsgType;
	private char supportMsgDirection;
	private String supportSource;
	private String supportDest;
	private int supportAck;
	private String supportScreenID;
	
	@Column(name="SUPPORT_SCREENID")
	public String getSupportScreenID() {
		return supportScreenID;
	}
	public void setSupportScreenID(String supportScreenID) {
		this.supportScreenID = supportScreenID;
	}
	@Id
	@Column(name="SUPPORT_MSGTYPE")
	public String getSupportMsgType() {
		return supportMsgType;
	}
	public void setSupportMsgType(String supportMsgType) {
		this.supportMsgType = supportMsgType;
	}
	
	@Column(name="SUPPORT_SUBMSGTYPE")
	public String getSupportSubMsgType() {
		return supportSubMsgType;
	}
	public void setSupportSubMsgType(String supportSubMsgType) {
		this.supportSubMsgType = supportSubMsgType;
	}
	

	@Column(name="SUPPORT_MSG_DIRECTION")
	public char getSupportMsgDirection() {
		return supportMsgDirection;
	}
	public void setSupportMsgDirection(char supportMsgDirection) {
		this.supportMsgDirection = supportMsgDirection;
	}
	@Column(name="SUPPORT_SOURCE")
	public String getSupportSource() {
		return supportSource;
	}
	public void setSupportSource(String supportSource) {
		this.supportSource = supportSource;
	}
	
	@Column(name="SUPPORT_DEST")
	public String getSupportDest() {
		return supportDest;
	}
	public void setSupportDest(String supportDest) {
		this.supportDest = supportDest;
	}
	
	@Column(name="SUPPORT_ACK")
	public int getSupportAck() {
		return supportAck;
	}
	public void setSupportAck(int supportAck) {
		this.supportAck = supportAck;
	}
	

}
