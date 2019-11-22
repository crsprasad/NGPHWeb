package com.logica.ngph.dtos;

import java.io.Serializable;
import java.math.BigDecimal;

public class ReturnIMPSTransactionDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String transRef;
	private String orderingCustomer;
	private String benificiaryCustomer;
	private BigDecimal amount;
	private String msgRef;
	
	
	public String getMsgRef() {
		return msgRef;
	}
	public void setMsgRef(String msgRef) {
		this.msgRef = msgRef;
	}
	public String getTransRef() {
		return transRef;
	}
	public void setTransRef(String transRef) {
		this.transRef = transRef;
	}
	public String getOrderingCustomer() {
		return orderingCustomer;
	}
	public void setOrderingCustomer(String orderingCustomer) {
		this.orderingCustomer = orderingCustomer;
	}
	public String getBenificiaryCustomer() {
		return benificiaryCustomer;
	}
	public void setBenificiaryCustomer(String benificiaryCustomer) {
		this.benificiaryCustomer = benificiaryCustomer;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public String getNarration() {
		return narration;
	}
	public void setNarration(String narration) {
		this.narration = narration;
	}
	private String narration;
	
	

}
