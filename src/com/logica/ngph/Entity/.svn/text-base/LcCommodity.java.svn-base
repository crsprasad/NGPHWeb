package com.logica.ngph.Entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table(name="TA_LC_COMMODITY")
public class LcCommodity implements Serializable {

	/**
	 LC_COMMODITY_NAME
	LC_COMMODITY_QTY
	LC_COMMODITY_RATE
	 */
	private static final long serialVersionUID = 1L;
	private String lcCommodity;
	/*private int lcCommodityQty;
	private Double lcCommodityRate;*/
	private String msgRef;
	private String lcNumber;
	
	@Column(name="LC_NUMBER")
	public String getLcNumber() {
		return lcNumber;
	}
	public void setLcNumber(String lcNumber) {
		this.lcNumber = lcNumber;
	}
	private String lcId;
	@Id
	@Column(name="LC_ID")
	public String getLcId() {
		return lcId;
	}
	public void setLcId(String lcId) {
		this.lcId = lcId;
	}
	@Column(name="MSGS_MSGREF")
	public String getMsgRef() {
		return msgRef;
	}
	public void setMsgRef(String msgRef) {
		this.msgRef = msgRef;
	}
	@Column(name="LC_COMMODITY")
	public String getLcCommodity() {
		return lcCommodity;
	}
	public void setLcCommodity(String lcCommodity) {
		this.lcCommodity = lcCommodity;
	}
	

	/*@Column(name="LC_COMMODITY_NAME")
	public String getLcCommodityName() {
		return lcCommodityName;
	}
	public void setLcCommodityName(String lcCommodityName) {
		this.lcCommodityName = lcCommodityName;
	}
	@Column(name="LC_COMMODITY_QTY")
	public int getLcCommodityQty() {
		return lcCommodityQty;
	}
	public void setLcCommodityQty(int lcCommodityQty) {
		this.lcCommodityQty = lcCommodityQty;
	}
	@Column(name="LC_COMMODITY_RATE")
	public Double getLcCommodityRate() {
		return lcCommodityRate;
	}
	public void setLcCommodityRate(Double lcCommodityRate) {
		this.lcCommodityRate = lcCommodityRate;
	}*/
	

}
