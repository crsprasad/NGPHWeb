package com.logica.ngph.Entity;

import java.io.Serializable;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="TA_EI")
public class EI implements Serializable{
/**
	 * 
	 */
	private static final long serialVersionUID = -2540043121633958990L;
/*	EI_CODE, 
	EI_NAME, 
	EI_TYPE, 
	EI_ESB_PROVNAME,
	EI_FEEDIN,
	EI_FEEDOUT
	INPUT_SRC_QUEUE
	INPUT_DEST_QUEUE
	OUTPUT_SRC_QUEUE
	OUTPUT_DEST_QUEUE
	EI_CONN_TYPE
	*/
	private String eiCode;
	private String eiName;
	private String eiType;
	private String eiEspProvName;
	private int feedIn;
	private int feedout;
	private String EI_CONN_TYPE;
	private String EI_FORMAT;
	private String EI_HOST_CATGORY;
	private String status;
	
	@Column(name ="EI_STATUS")
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Column(name="EI_FORMAT")
	public String getEI_FORMAT() {
		return EI_FORMAT;
	}
	public void setEI_FORMAT(String eI_FORMAT) {
		EI_FORMAT = eI_FORMAT;
	}
	@Column(name="EI_HOST_CATGORY")
	public String getEI_HOST_CATGORY() {
		return EI_HOST_CATGORY;
	}
	public void setEI_HOST_CATGORY(String eI_HOST_CATGORY) {
		EI_HOST_CATGORY = eI_HOST_CATGORY;
	}
	@Column(name="EI_CONN_TYPE")
	public String getEI_CONN_TYPE() {
		return EI_CONN_TYPE;
	}
	public void setEI_CONN_TYPE(String eI_CONN_TYPE) {
		EI_CONN_TYPE = eI_CONN_TYPE;
	}
	@Id
	@Column(name="EI_CODE")
	public String getEiCode() {
		return eiCode;
	}
	public void setEiCode(String eiCode) {
		this.eiCode = eiCode;
	}
	@Column(name="EI_NAME")
	public String getEiName() {
		return eiName;
	}
	public void setEiName(String eiName) {
		this.eiName = eiName;
	}
	@Column(name="EI_TYPE")
	public String getEiType() {
		return eiType;
	}
	public void setEiType(String eiType) {
		this.eiType = eiType;
	}
	@Column(name="EI_ESB_PROVNAME")
	public String getEiEspProvName() {
		return eiEspProvName;
	}
	public void setEiEspProvName(String eiEspProvName) {
		this.eiEspProvName = eiEspProvName;
	}
	@Column(name="EI_FEEDIN")
	public int getFeedIn() {
		return feedIn;
	}
	public void setFeedIn(int feedIn) {
		this.feedIn = feedIn;
	}
	@Column(name="EI_FEEDOUT")
	public int getFeedout() {
		return feedout;
	}
	public void setFeedout(int feedout) {
		this.feedout = feedout;
	}
	@Column(name="INPUT_SRC_QUEUE")
	public String getInputSrcQueue() {
		return inputSrcQueue;
	}
	public void setInputSrcQueue(String inputSrcQueue) {
		this.inputSrcQueue = inputSrcQueue;
	}
	@Column(name="INPUT_DEST_QUEUE")
	public String getInputDestQueue() {
		return inputDestQueue;
	}
	public void setInputDestQueue(String inputDestQueue) {
		this.inputDestQueue = inputDestQueue;
	}
	@Column(name="OUTPUT_SRC_QUEUE")
	public String getOutputSrcQueue() {
		return outputSrcQueue;
	}
	public void setOutputSrcQueue(String outputSrcQueue) {
		this.outputSrcQueue = outputSrcQueue;
	}
	@Column(name="OUTPUT_DEST_QUEUE")
	public String getOutputDestQueue() {
		return outputDestQueue;
	}
	public void setOutputDestQueue(String outputDestQueue) {
		this.outputDestQueue = outputDestQueue;
	}

	private String inputSrcQueue;
	private String inputDestQueue;
	private String outputSrcQueue;
	private String outputDestQueue;
	

}
