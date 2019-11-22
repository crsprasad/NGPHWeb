/**
 * 
 */
package com.logica.ngph.dtos;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author chakkar
 *
 */
public class TemplateDto implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String tempRef;
	private String tempId;
	private String tempName;
	private String msgType;
	private String subMsgType;
	private String tempData;
	private String crtdUserId;
	private Timestamp crtDate;
	
	/**
	 * @return the tempRef
	 */
	public String getTempRef() {
		return tempRef;
	}
	/**
	 * @param tempRef the tempRef to set
	 */
	public void setTempRef(String tempRef) {
		this.tempRef = tempRef;
	}
	/**
	 * @return the tempId
	 */
	public String getTempId() {
		return tempId;
	}
	/**
	 * @param tempId the tempId to set
	 */
	public void setTempId(String tempId) {
		this.tempId = tempId;
	}
	/**
	 * @return the tempName
	 */
	public String getTempName() {
		return tempName;
	}
	/**
	 * @param tempName the tempName to set
	 */
	public void setTempName(String tempName) {
		this.tempName = tempName;
	}
	/**
	 * @return the msgType
	 */
	public String getMsgType() {
		return msgType;
	}
	/**
	 * @param msgType the msgType to set
	 */
	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}
	/**
	 * @return the subMsgType
	 */
	public String getSubMsgType() {
		return subMsgType;
	}
	/**
	 * @param subMsgType the subMsgType to set
	 */
	public void setSubMsgType(String subMsgType) {
		this.subMsgType = subMsgType;
	}
	/**
	 * @return the tempData
	 */
	public String getTempData() {
		return tempData;
	}
	/**
	 * @param tempData the tempData to set
	 */
	public void setTempData(String tempData) {
		this.tempData = tempData;
	}
	/**
	 * @return the crtdUserId
	 */
	public String getCrtdUserId() {
		return crtdUserId;
	}
	/**
	 * @param crtdUserId the crtdUserId to set
	 */
	public void setCrtdUserId(String crtdUserId) {
		this.crtdUserId = crtdUserId;
	}
	/**
	 * @return the crtDate
	 */
	public Timestamp getCrtDate() {
		return crtDate;
	}
	/**
	 * @param crtDate the crtDate to set
	 */
	public void setCrtDate(Timestamp crtDate) {
		this.crtDate = crtDate;
	}
	
	
}
