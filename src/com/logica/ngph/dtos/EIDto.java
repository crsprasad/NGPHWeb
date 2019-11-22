package com.logica.ngph.dtos;

public class EIDto {
	private String EICode;
	private String EIName;
	private String EIStatus;
	
	public String getEiType() {
		return eiType;
	}
	public void setEiType(String eiType) {
		this.eiType = eiType;
	}
	public String getEiEspProvName() {
		return eiEspProvName;
	}
	public void setEiEspProvName(String eiEspProvName) {
		this.eiEspProvName = eiEspProvName;
	}
	public int getFeedIn() {
		return feedIn;
	}
	public void setFeedIn(int feedIn) {
		this.feedIn = feedIn;
	}
	public int getFeedout() {
		return feedout;
	}
	public void setFeedout(int feedout) {
		this.feedout = feedout;
	}
	public String getInputSrcQueue() {
		return inputSrcQueue;
	}
	public void setInputSrcQueue(String inputSrcQueue) {
		this.inputSrcQueue = inputSrcQueue;
	}
	public String getInputDestQueue() {
		return inputDestQueue;
	}
	public void setInputDestQueue(String inputDestQueue) {
		this.inputDestQueue = inputDestQueue;
	}
	public String getOutputSrcQueue() {
		return outputSrcQueue;
	}
	public void setOutputSrcQueue(String outputSrcQueue) {
		this.outputSrcQueue = outputSrcQueue;
	}
	public String getOutputDestQueue() {
		return outputDestQueue;
	}
	public void setOutputDestQueue(String outputDestQueue) {
		this.outputDestQueue = outputDestQueue;
	}
	public String getEIConnType() {
		return EIConnType;
	}
	public void setEIConnType(String eIConnType) {
		EIConnType = eIConnType;
	}
	public String getEIFormat() {
		return EIFormat;
	}
	public void setEIFormat(String eIFormat) {
		EIFormat = eIFormat;
	}
	public String getEIHostCatgory() {
		return EIHostCatgory;
	}
	public void setEIHostCatgory(String eIHostCatgory) {
		EIHostCatgory = eIHostCatgory;
	}
	public String getEIAppID() {
		return EIAppID;
	}
	public void setEIAppID(String eIAppID) {
		EIAppID = eIAppID;
	}
	public String getDstMsgReqd() {
		return dstMsgReqd;
	}
	public void setDstMsgReqd(String dstMsgReqd) {
		this.dstMsgReqd = dstMsgReqd;
	}
	private String eiType;
	private String eiEspProvName;
	private int feedIn;
	private int feedout;
	private String inputSrcQueue;
	private String inputDestQueue;
	private String outputSrcQueue;
	private String outputDestQueue;
	private String EIConnType;
	private String EIFormat;
	private String EIHostCatgory;
	private String EIAppID;
	private String dstMsgReqd;
	

	public String getEICode() {
		return EICode;
	}
	public void setEICode(String eICode) {
		EICode = eICode;
	}
	public String getEIName() {
		return EIName;
	}
	public void setEIName(String eIName) {
		EIName = eIName;
	}
	public String getEIStatus() {
		return EIStatus;
	}
	public void setEIStatus(String eIStatus) {
		EIStatus = eIStatus;
	}

}
