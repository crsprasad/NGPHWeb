package com.logica.ngph.Entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="TA_IMPS_RECONSILET")
public class IMPS_Consilet implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String	imps_PARTICIPANT_ID;
	private String imps_TRANSACTION_TYPE;
	private String imps_FROM_ACCOUNT_TYPE;
	private String imps_TO_ACCOUNT_TYPE ;
	private String imps_TRANSACTION_SERIAL ;
	private String imps_RESPONSE_CODE;
	private String imps_PAN_NUMBER;
	private String imps_MEMBER_NUMBER;
	private String imps_APPROVAL_NUMBER;
	private String imps_SYSTEM_TRACE_AUDIT;
	private String imps_TRANSACTION_DATE;
	private String imps_TRANSACTION_TIME;
	private String imps_MERCHANT_CATEGORY_CODE;
	private String imps_SETTLEMENT_DATE;
	private String imps_CARD_ACCEPTOR_ID;
	
	private String imps_CARD_ACCEPTOR_TERMINAL_ID;
	private String imps_CARD_ACCEPTOR_TERMINAL_LOC;
	private String imps_AQUIRER_ID;
	private String imps_NETWORK_ID;
	private String imps_ACCOUNT_1_NUMBER;
	private String imps_ACCOUNT_1_BRANCH_ID;
	private String imps_ACCOUNT_2_NUMBER;
	private String imps_ACCOUNT_2_BRANCH_ID;
	private String imps_TRANSACTION_CURRENCY;
	private String imps_TRANSACTION_AMOUNT;
	private String imps_ACTUAL_TRANSACTION_AMT;
	private String imps_TRANSACTION_ACTIVITY_FEE;
	private String imps_ISSUER_1_SETTLEMENT_AMOUNT;
	private String imps_ISSUER_1_SETTLEMENT_CURRENCY;
	
	private String imps_ISSUER_1_SETTLEMENT_FEE;
	private String imps_ISSUER_1_STL_PROCESSING_FEE;
	private String imps_CARDHOLDER_1_BILL_CURRENCY;
	private String imps_CARDHOLDER_1_BILLING_AMOUNT;

	private String imps_CARDHOLDER_1_BILL_ACTV_FEE;
	private String imps_CARDHOLDER_1_BILL_PROC_FEE;
	private String imps_CARDHOLDER_1_BILL_SVC_FEE;
	private String imps_CRDHLDR_1_CONV_RATE;
	private String imps_STLMNT_CRDHLDR_1_CONV_RATE;
	private String imps_DIRECTION;
	
	@Id
	@Column(name="IMPS_PARTICIPANT_ID")
	public String getImps_PARTICIPANT_ID() {
		return imps_PARTICIPANT_ID;
	}
	public void setImps_PARTICIPANT_ID(String imps_PARTICIPANT_ID) {
		this.imps_PARTICIPANT_ID = imps_PARTICIPANT_ID;
	}
	@Column(name="IMPS_TRANSACTION_TYPE")
	public String getImps_TRANSACTION_TYPE() {
		return imps_TRANSACTION_TYPE;
	}
	public void setImps_TRANSACTION_TYPE(String imps_TRANSACTION_TYPE) {
		this.imps_TRANSACTION_TYPE = imps_TRANSACTION_TYPE;
	}
	@Column(name="IMPS_FROM_ACCOUNT_TYPE")
	public String getImps_FROM_ACCOUNT_TYPE() {
		return imps_FROM_ACCOUNT_TYPE;
	}
	public void setImps_FROM_ACCOUNT_TYPE(String imps_FROM_ACCOUNT_TYPE) {
		this.imps_FROM_ACCOUNT_TYPE = imps_FROM_ACCOUNT_TYPE;
	}
	@Column(name="IMPS_TO_ACCOUNT_TYPE")
	public String getImps_TO_ACCOUNT_TYPE() {
		return imps_TO_ACCOUNT_TYPE;
	}
	public void setImps_TO_ACCOUNT_TYPE(String imps_TO_ACCOUNT_TYPE) {
		this.imps_TO_ACCOUNT_TYPE = imps_TO_ACCOUNT_TYPE;
	}
	
	@Column(name="IMPS_TRANSACTION_SERIAL")
	public String getImps_TRANSACTION_SERIAL() {
		return imps_TRANSACTION_SERIAL;
	}
	public void setImps_TRANSACTION_SERIAL(String imps_TRANSACTION_SERIAL) {
		this.imps_TRANSACTION_SERIAL = imps_TRANSACTION_SERIAL;
	}
	@Column(name="IMPS_RESPONSE_CODE")
	public String getImps_RESPONSE_CODE() {
		return imps_RESPONSE_CODE;
	}
	public void setImps_RESPONSE_CODE(String imps_RESPONSE_CODE) {
		this.imps_RESPONSE_CODE = imps_RESPONSE_CODE;
	}
	@Column(name="IMPS_PAN_NUMBER")
	public String getImps_PAN_NUMBER() {
		return imps_PAN_NUMBER;
	}
	public void setImps_PAN_NUMBER(String imps_PAN_NUMBER) {
		this.imps_PAN_NUMBER = imps_PAN_NUMBER;
	}
	@Column(name="IMPS_MEMBER_NUMBER")
	public String getImps_MEMBER_NUMBER() {
		return imps_MEMBER_NUMBER;
	}
	public void setImps_MEMBER_NUMBER(String imps_MEMBER_NUMBER) {
		this.imps_MEMBER_NUMBER = imps_MEMBER_NUMBER;
	}
	@Column(name="IMPS_APPROVAL_NUMBER")
	public String getImps_APPROVAL_NUMBER() {
		return imps_APPROVAL_NUMBER;
	}
	public void setImps_APPROVAL_NUMBER(String imps_APPROVAL_NUMBER) {
		this.imps_APPROVAL_NUMBER = imps_APPROVAL_NUMBER;
	}
	@Column(name="IMPS_SYSTEM_TRACE_AUDIT")
	public String getImps_SYSTEM_TRACE_AUDIT() {
		return imps_SYSTEM_TRACE_AUDIT;
	}
	public void setImps_SYSTEM_TRACE_AUDIT(String imps_SYSTEM_TRACE_AUDIT) {
		this.imps_SYSTEM_TRACE_AUDIT = imps_SYSTEM_TRACE_AUDIT;
	}
	@Column(name="IMPS_TRANSACTION_DATE")
	public String getImps_TRANSACTION_DATE() {
		return imps_TRANSACTION_DATE;
	}
	public void setImps_TRANSACTION_DATE(String imps_TRANSACTION_DATE) {
		this.imps_TRANSACTION_DATE = imps_TRANSACTION_DATE;
	}
	@Column(name="IMPS_TRANSACTION_TIME")
	public String getImps_TRANSACTION_TIME() {
		return imps_TRANSACTION_TIME;
	}
	public void setImps_TRANSACTION_TIME(String imps_TRANSACTION_TIME) {
		this.imps_TRANSACTION_TIME = imps_TRANSACTION_TIME;
	}
	@Column(name="IMPS_MERCHANT_CATEGORY_CODE")
	public String getImps_MERCHANT_CATEGORY_CODE() {
		return imps_MERCHANT_CATEGORY_CODE;
	}
	public void setImps_MERCHANT_CATEGORY_CODE(String imps_MERCHANT_CATEGORY_CODE) {
		this.imps_MERCHANT_CATEGORY_CODE = imps_MERCHANT_CATEGORY_CODE;
	}
	@Column(name="IMPS_SETTLEMENT_DATE")
	public String getImps_SETTLEMENT_DATE() {
		return imps_SETTLEMENT_DATE;
	}
	public void setImps_SETTLEMENT_DATE(String imps_SETTLEMENT_DATE) {
		this.imps_SETTLEMENT_DATE = imps_SETTLEMENT_DATE;
	}
	@Column(name="IMPS_CARD_ACCEPTOR_ID")
	public String getImps_CARD_ACCEPTOR_ID() {
		return imps_CARD_ACCEPTOR_ID;
	}
	public void setImps_CARD_ACCEPTOR_ID(String imps_CARD_ACCEPTOR_ID) {
		this.imps_CARD_ACCEPTOR_ID = imps_CARD_ACCEPTOR_ID;
	}
	@Column(name="IMPS_CARD_ACCEPTOR_TRMNL_ID")
	public String getImps_CARD_ACCEPTOR_TERMINAL_ID() {
		return imps_CARD_ACCEPTOR_TERMINAL_ID;
	}
	public void setImps_CARD_ACCEPTOR_TERMINAL_ID(
			String imps_CARD_ACCEPTOR_TERMINAL_ID) {
		this.imps_CARD_ACCEPTOR_TERMINAL_ID = imps_CARD_ACCEPTOR_TERMINAL_ID;
	}
	@Column(name="IMPS_CARD_ACCEPTOR_TRMNL_LOC")
	public String getImps_CARD_ACCEPTOR_TERMINAL_LOC() {
		return imps_CARD_ACCEPTOR_TERMINAL_LOC;
	}
	public void setImps_CARD_ACCEPTOR_TERMINAL_LOC(
			String imps_CARD_ACCEPTOR_TERMINAL_LOC) {
		this.imps_CARD_ACCEPTOR_TERMINAL_LOC = imps_CARD_ACCEPTOR_TERMINAL_LOC;
	}
	@Column(name="IMPS_AQUIRER_ID")
	public String getImps_AQUIRER_ID() {
		return imps_AQUIRER_ID;
	}
	public void setImps_AQUIRER_ID(String imps_AQUIRER_ID) {
		this.imps_AQUIRER_ID = imps_AQUIRER_ID;
	}
	@Column(name="IMPS_NETWORK_ID")
	public String getImps_NETWORK_ID() {
		return imps_NETWORK_ID;
	}
	public void setImps_NETWORK_ID(String imps_NETWORK_ID) {
		this.imps_NETWORK_ID = imps_NETWORK_ID;
	}
	@Column(name="IMPS_ACCOUNT_1_NUMBER")
	public String getImps_ACCOUNT_1_NUMBER() {
		return imps_ACCOUNT_1_NUMBER;
	}
	public void setImps_ACCOUNT_1_NUMBER(String imps_ACCOUNT_1_NUMBER) {
		this.imps_ACCOUNT_1_NUMBER = imps_ACCOUNT_1_NUMBER;
	}
	@Column(name="IMPS_ACCOUNT_1_BRANCH_ID")
	public String getImps_ACCOUNT_1_BRANCH_ID() {
		return imps_ACCOUNT_1_BRANCH_ID;
	}
	public void setImps_ACCOUNT_1_BRANCH_ID(String imps_ACCOUNT_1_BRANCH_ID) {
		this.imps_ACCOUNT_1_BRANCH_ID = imps_ACCOUNT_1_BRANCH_ID;
	}
	@Column(name="IMPS_ACCOUNT_2_NUMBER")
	public String getImps_ACCOUNT_2_NUMBER() {
		return imps_ACCOUNT_2_NUMBER;
	}
	public void setImps_ACCOUNT_2_NUMBER(String imps_ACCOUNT_2_NUMBER) {
		this.imps_ACCOUNT_2_NUMBER = imps_ACCOUNT_2_NUMBER;
	}
	@Column(name="IMPS_ACCOUNT_2_BRANCH_ID")
	public String getImps_ACCOUNT_2_BRANCH_ID() {
		return imps_ACCOUNT_2_BRANCH_ID;
	}
	public void setImps_ACCOUNT_2_BRANCH_ID(String imps_ACCOUNT_2_BRANCH_ID) {
		this.imps_ACCOUNT_2_BRANCH_ID = imps_ACCOUNT_2_BRANCH_ID;
	}
	@Column(name="IMPS_TRANSACTION_CURRENCY")
	public String getImps_TRANSACTION_CURRENCY() {
		return imps_TRANSACTION_CURRENCY;
	}
	public void setImps_TRANSACTION_CURRENCY(String imps_TRANSACTION_CURRENCY) {
		this.imps_TRANSACTION_CURRENCY = imps_TRANSACTION_CURRENCY;
	}
	@Column(name="IMPS_TRANSACTION_AMOUNT")
	public String getImps_TRANSACTION_AMOUNT() {
		return imps_TRANSACTION_AMOUNT;
	}
	public void setImps_TRANSACTION_AMOUNT(String imps_TRANSACTION_AMOUNT) {
		this.imps_TRANSACTION_AMOUNT = imps_TRANSACTION_AMOUNT;
	}
	@Column(name="IMPS_ACTUAL_TRANSACTION_AMT")
	public String getImps_ACTUAL_TRANSACTION_AMT() {
		return imps_ACTUAL_TRANSACTION_AMT;
	}
	public void setImps_ACTUAL_TRANSACTION_AMT(String imps_ACTUAL_TRANSACTION_AMT) {
		this.imps_ACTUAL_TRANSACTION_AMT = imps_ACTUAL_TRANSACTION_AMT;
	}
	@Column(name="IMPS_TRANSACTION_ACTIVITY_FEE")
	public String getImps_TRANSACTION_ACTIVITY_FEE() {
		return imps_TRANSACTION_ACTIVITY_FEE;
	}
	public void setImps_TRANSACTION_ACTIVITY_FEE(
			String imps_TRANSACTION_ACTIVITY_FEE) {
		this.imps_TRANSACTION_ACTIVITY_FEE = imps_TRANSACTION_ACTIVITY_FEE;
	}
	@Column(name="IMPS_ISSUER_1_STMNT_CUR")
	public String getImps_ISSUER_1_SETTLEMENT_AMOUNT() {
		return imps_ISSUER_1_SETTLEMENT_AMOUNT;
	}
	public void setImps_ISSUER_1_SETTLEMENT_AMOUNT(
			String imps_ISSUER_1_SETTLEMENT_AMOUNT) {
		this.imps_ISSUER_1_SETTLEMENT_AMOUNT = imps_ISSUER_1_SETTLEMENT_AMOUNT;
	}
	@Column(name="IMPS_ISSUER_1_STMNT_AMT")
	public String getImps_ISSUER_1_SETTLEMENT_CURRENCY() {
		return imps_ISSUER_1_SETTLEMENT_CURRENCY;
	}
	public void setImps_ISSUER_1_SETTLEMENT_CURRENCY(
			String imps_ISSUER_1_SETTLEMENT_CURRENCY) {
		this.imps_ISSUER_1_SETTLEMENT_CURRENCY = imps_ISSUER_1_SETTLEMENT_CURRENCY;
	}
	@Column(name="IMPS_ISSUER_1_STMNT_FEE")
	public String getImps_ISSUER_1_SETTLEMENT_FEE() {
		return imps_ISSUER_1_SETTLEMENT_FEE;
	}
	public void setImps_ISSUER_1_SETTLEMENT_FEE(String imps_ISSUER_1_SETTLEMENT_FEE) {
		this.imps_ISSUER_1_SETTLEMENT_FEE = imps_ISSUER_1_SETTLEMENT_FEE;
	}
	@Column(name="IMPS_ISSUER_1_STL_PRCS_FEE")
	public String getImps_ISSUER_1_STL_PROCESSING_FEE() {
		return imps_ISSUER_1_STL_PROCESSING_FEE;
	}
	public void setImps_ISSUER_1_STL_PROCESSING_FEE(
			String imps_ISSUER_1_STL_PROCESSING_FEE) {
		this.imps_ISSUER_1_STL_PROCESSING_FEE = imps_ISSUER_1_STL_PROCESSING_FEE;
	}
	@Column(name="IMPS_CRDHLDR_1_BILL_CUR")
	public String getImps_CARDHOLDER_1_BILL_CURRENCY() {
		return imps_CARDHOLDER_1_BILL_CURRENCY;
	}
	public void setImps_CARDHOLDER_1_BILL_CURRENCY(
			String imps_CARDHOLDER_1_BILL_CURRENCY) {
		this.imps_CARDHOLDER_1_BILL_CURRENCY = imps_CARDHOLDER_1_BILL_CURRENCY;
	}
	@Column(name="IMPS_CRDHLDR_1_BIL_AMT")
	public String getImps_CARDHOLDER_1_BILLING_AMOUNT() {
		return imps_CARDHOLDER_1_BILLING_AMOUNT;
	}
	public void setImps_CARDHOLDER_1_BILLING_AMOUNT(
			String imps_CARDHOLDER_1_BILLING_AMOUNT) {
		this.imps_CARDHOLDER_1_BILLING_AMOUNT = imps_CARDHOLDER_1_BILLING_AMOUNT;
	}
	@Column(name="IMPS_CRDHLDR_1_BIL_ACTV_FEE")
	public String getImps_CARDHOLDER_1_BILL_ACTV_FEE() {
		return imps_CARDHOLDER_1_BILL_ACTV_FEE;
	}
	public void setImps_CARDHOLDER_1_BILL_ACTV_FEE(
			String imps_CARDHOLDER_1_BILL_ACTV_FEE) {
		this.imps_CARDHOLDER_1_BILL_ACTV_FEE = imps_CARDHOLDER_1_BILL_ACTV_FEE;
	}
	@Column(name="IMPS_CRDHLDR_1_BIL_PROC_FEE")
	public String getImps_CARDHOLDER_1_BILL_PROC_FEE() {
		return imps_CARDHOLDER_1_BILL_PROC_FEE;
	}
	public void setImps_CARDHOLDER_1_BILL_PROC_FEE(
			String imps_CARDHOLDER_1_BILL_PROC_FEE) {
		this.imps_CARDHOLDER_1_BILL_PROC_FEE = imps_CARDHOLDER_1_BILL_PROC_FEE;
	}
	@Column(name="IMPS_CRDHLDR_1_BIL_SVC_FEE")
	public String getImps_CARDHOLDER_1_BILL_SVC_FEE() {
		return imps_CARDHOLDER_1_BILL_SVC_FEE;
	}
	public void setImps_CARDHOLDER_1_BILL_SVC_FEE(
			String imps_CARDHOLDER_1_BILL_SVC_FEE) {
		this.imps_CARDHOLDER_1_BILL_SVC_FEE = imps_CARDHOLDER_1_BILL_SVC_FEE;
	}
	@Column(name="IMPS_CRDHLDR_1_CONV_RATE")
	public String getImps_CRDHLDR_1_CONV_RATE() {
		return imps_CRDHLDR_1_CONV_RATE;
	}
	public void setImps_CRDHLDR_1_CONV_RATE(String imps_CRDHLDR_1_CONV_RATE) {
		this.imps_CRDHLDR_1_CONV_RATE = imps_CRDHLDR_1_CONV_RATE;
	}
	@Column(name="IMPS_STL_CRDHLDR_1_CONV_RATE")
	public String getImps_STLMNT_CRDHLDR_1_CONV_RATE() {
		return imps_STLMNT_CRDHLDR_1_CONV_RATE;
	}
	public void setImps_STLMNT_CRDHLDR_1_CONV_RATE(
			String imps_STLMNT_CRDHLDR_1_CONV_RATE) {
		this.imps_STLMNT_CRDHLDR_1_CONV_RATE = imps_STLMNT_CRDHLDR_1_CONV_RATE;
	}
	@Column(name="IMPS_DIRECTION")
	public String getImps_DIRECTION() {
		return imps_DIRECTION;
	}
	public void setImps_DIRECTION(String imps_DIRECTION) {
		this.imps_DIRECTION = imps_DIRECTION;
	}
	

}
