package com.logica.ngph.dtos;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class CreateBankGuaranteeDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String bgNumber;
	private String bgCreateType;
	private Date bgDate;
	private String bgRuleCode;
	private String bgRuleNarration;
	private String senderToReceiverInformation;	
	private String msgRef;	
	private int noOfMsg;
	private String bgDirection;
	private Date bgIssueDate;	
	private String bgRuleDesc;
	private String bgDetails;	
	private int bgAmount;
	private int bgStatus;
	private int bgNoOfAmntmnt;
	private Date bgLastModifiedTime;
	private String advisingBank;
	private String repair;
	private String relatedReference;
	private String bgAccountIdentification;
	private BigDecimal bgChargeAmount = new BigDecimal(0.00);
	private String bgCurrency;
	private String bgAccountWithPartyIdentifier;
	private String bgAccountWithNameandAddress;
	private String accountWithPartyLocation;
	private String chargesDetails;
	private Date dateofMessageBeingAcknowledged;
	private String accountWithPartyIFSC;
	private Date bgDebitDate;
	//Start :: Added for 769 //
	private Date reductionDate;
	private String reducedCurrency;
	private BigDecimal reducedAmt= new BigDecimal(0.00);
	private String outstandingCurrency;
	private BigDecimal outstandingAmt= new BigDecimal(0.00);
	private Date chargeDate;
	private String accountWithBank;
	private String chargeAmt;
	private String amountSpecification;
	private String adviseThroughBankAcc;
	private String accountwithBankCode;
	private String authorisedBankCode;
	private String adviseThroughBankpartyidentifier;
	private String seqNo;
	private String msgHost;
	private String narrative;
	private String issuingBankCode;
	//End :: Added for 769 //
	//Start :: Added for Mizuho Bank//
	private String adviseThroughBankCode;
	private String adviseThroughBankName;
	private String lcCurrency;
	private String accountwithPartyLocation;
	private String bgFurtherIdentification;
	private String sequenceofTotal;
	private String sequence;
	private String chargeAmtIdentifier;
	
	
	
	/**
	 * @return the chargeAmtIdentifier
	 */
	public String getChargeAmtIdentifier() {
		return chargeAmtIdentifier;
	}
	/**
	 * @param chargeAmtIdentifier the chargeAmtIdentifier to set
	 */
	public void setChargeAmtIdentifier(String chargeAmtIdentifier) {
		this.chargeAmtIdentifier = chargeAmtIdentifier;
	}
	public String getSequence() {
		return sequence;
	}
	public void setSequence(String sequence) {
		this.sequence = sequence;
	}
	public String getSequenceofTotal() {
		return sequenceofTotal;
	}
	public void setSequenceofTotal(String sequenceofTotal) {
		this.sequenceofTotal = sequenceofTotal;
	}
	
	//Start :: Added for Mizuho Bank//
	
	
	
	/**
	 * @return the accountWithBank
	 */
	public String getAccountWithBank() {
		return accountWithBank;
	}
	/**
	 * @return the adviseThroughBankpartyidentifier
	 */
	public String getAdviseThroughBankpartyidentifier() {
		return adviseThroughBankpartyidentifier;
	}
	/**
	 * @param adviseThroughBankpartyidentifier the adviseThroughBankpartyidentifier to set
	 */
	public void setAdviseThroughBankpartyidentifier(
			String adviseThroughBankpartyidentifier) {
		this.adviseThroughBankpartyidentifier = adviseThroughBankpartyidentifier;
	}
	public String getBgFurtherIdentification() {
		return bgFurtherIdentification;
	}
	public void setBgFurtherIdentification(String bgFurtherIdentification) {
		this.bgFurtherIdentification = bgFurtherIdentification;
	}
	/**
	 * @return the accountwithPartyLocation
	 */
	public String getAccountwithPartyLocation() {
		return accountwithPartyLocation;
	}
	/**
	 * @param accountwithPartyLocation the accountwithPartyLocation to set
	 */
	public void setAccountwithPartyLocation(String accountwithPartyLocation) {
		this.accountwithPartyLocation = accountwithPartyLocation;
	}
	/**
	 * @return the lcCurrency
	 */
	public String getLcCurrency() {
		return lcCurrency;
	}
	/**
	 * @param lcCurrency the lcCurrency to set
	 */
	public void setLcCurrency(String lcCurrency) {
		this.lcCurrency = lcCurrency;
	}
	/**
	 * @return the adviseThroughBankCode
	 */
	public String getAdviseThroughBankCode() {
		return adviseThroughBankCode;
	}
	/**
	 * @param adviseThroughBankCode the adviseThroughBankCode to set
	 */
	public void setAdviseThroughBankCode(String adviseThroughBankCode) {
		this.adviseThroughBankCode = adviseThroughBankCode;
	}
	/**
	 * @return the adviseThroughBankName
	 */
	public String getAdviseThroughBankName() {
		return adviseThroughBankName;
	}
	/**
	 * @param adviseThroughBankName the adviseThroughBankName to set
	 */
	public void setAdviseThroughBankName(String adviseThroughBankName) {
		this.adviseThroughBankName = adviseThroughBankName;
	}
	/**
	 * @return the issuingBankCode
	 */
	public String getIssuingBankCode() {
		return issuingBankCode;
	}
	/**
	 * @param issuingBankCode the issuingBankCode to set
	 */
	public void setIssuingBankCode(String issuingBankCode) {
		this.issuingBankCode = issuingBankCode;
	}
	/**
	 * @return the narrative
	 */
	public String getNarrative() {
		return narrative;
	}
	/**
	 * @param narrative the narrative to set
	 */
	public void setNarrative(String narrative) {
		this.narrative = narrative;
	}
	/**
	 * @return the msgHost
	 */
	public String getMsgHost() {
		return msgHost;
	}
	/**
	 * @param msgHost the msgHost to set
	 */
	public void setMsgHost(String msgHost) {
		this.msgHost = msgHost;
	}
	/**
	 * @return the seqNo
	 */
	public String getSeqNo() {
		return seqNo;
	}
	/**
	 * @param seqNo the seqNo to set
	 */
	public void setSeqNo(String seqNo) {
		this.seqNo = seqNo;
	}
	/**
	 * @return the authorisedBankCode
	 */
	public String getAuthorisedBankCode() {
		return authorisedBankCode;
	}
	/**
	 * @param authorisedBankCode the authorisedBankCode to set
	 */
	public void setAuthorisedBankCode(String authorisedBankCode) {
		this.authorisedBankCode = authorisedBankCode;
	}
	/**
	 * @return the accountwithBankCode
	 */
	public String getAccountwithBankCode() {
		return accountwithBankCode;
	}
	/**
	 * @param accountwithBankCode the accountwithBankCode to set
	 */
	public void setAccountwithBankCode(String accountwithBankCode) {
		this.accountwithBankCode = accountwithBankCode;
	}
	/**
	 * @return the outstandingCurrency
	 */
	public String getOutstandingCurrency() {
		return outstandingCurrency;
	}
	/**
	 * @param outstandingCurrency the outstandingCurrency to set
	 */
	public void setOutstandingCurrency(String outstandingCurrency) {
		this.outstandingCurrency = outstandingCurrency;
	}
	/**
	 * @return the adviseThroughBankAcc
	 */
	public String getAdviseThroughBankAcc() {
		return adviseThroughBankAcc;
	}
	/**
	 * @param adviseThroughBankAcc the adviseThroughBankAcc to set
	 */
	public void setAdviseThroughBankAcc(String adviseThroughBankAcc) {
		this.adviseThroughBankAcc = adviseThroughBankAcc;
	}
	/**
	 * @return the chargeAmt
	 */
	public String getChargeAmt() {
		return chargeAmt;
	}
	/**
	 * @param chargeAmt the chargeAmt to set
	 */
	public void setChargeAmt(String chargeAmt) {
		this.chargeAmt = chargeAmt;
	}
	/**
	 * @param accountWithBank the accountWithBank to set
	 */
	public void setAccountWithBank(String accountWithBank) {
		this.accountWithBank = accountWithBank;
	}
	/**
	 * @return the chargeDate
	 */
	public Date getChargeDate() {
		return chargeDate;
	}
	/**
	 * @param chargeDate the chargeDate to set
	 */
	public void setChargeDate(Date chargeDate) {
		this.chargeDate = chargeDate;
	}
	/**
	 * @return the reducedCurrency
	 */
	public String getReducedCurrency() {
		return reducedCurrency;
	}
	/**
	 * @param reducedCurrency the reducedCurrency to set
	 */
	public void setReducedCurrency(String reducedCurrency) {
		this.reducedCurrency = reducedCurrency;
	}
	/**
	 * @return the reducedAmt
	 */
	public BigDecimal getReducedAmt() {
		return reducedAmt;
	}
	/**
	 * @param reducedAmt the reducedAmt to set
	 */
	public void setReducedAmt(BigDecimal reducedAmt) {
		this.reducedAmt = reducedAmt;
	}
	/**
	 * @return the outstandingAmt
	 */
	public BigDecimal getOutstandingAmt() {
		return outstandingAmt;
	}
	/**
	 * @param outstandingAmt the outstandingAmt to set
	 */
	public void setOutstandingAmt(BigDecimal outstandingAmt) {
		this.outstandingAmt = outstandingAmt;
	}
	/**
	 * @return the amountSpecification
	 */
	public String getAmountSpecification() {
		return amountSpecification;
	}
	/**
	 * @param amountSpecification the amountSpecification to set
	 */
	public void setAmountSpecification(String amountSpecification) {
		this.amountSpecification = amountSpecification;
	}
		
	/**
	 * @return the reductionDate
	 */
	public Date getReductionDate() {
		return reductionDate;
	}
	/**
	 * @param reductionDate the reductionDate to set
	 */
	public void setReductionDate(Date reductionDate) {
		this.reductionDate = reductionDate;
	}
	public Date getBgDebitDate() {
		return bgDebitDate;
	}
	public void setBgDebitDate(Date bgDebitDate) {
		this.bgDebitDate = bgDebitDate;
	}
	public String getRepair() {
		return repair;
	}
	public void setRepair(String repair) {
		this.repair = repair;
	}
	public String getBgNumber() {
		return bgNumber;
	}
	public void setBgNumber(String bgNumber) {
		this.bgNumber = bgNumber;
	}
	public String getBgCreateType() {
		return bgCreateType;
	}
	public void setBgCreateType(String bgCreateType) {
		this.bgCreateType = bgCreateType;
	}
	public String getBgRuleCode() {
		return bgRuleCode;
	}
	public void setBgRuleCode(String bgRuleCode) {
		this.bgRuleCode = bgRuleCode;
	}
	public String getBgRuleNarration() {
		return bgRuleNarration;
	}
	public void setBgRuleNarration(String bgRuleNarration) {
		this.bgRuleNarration = bgRuleNarration;
	}	
	public String getSenderToReceiverInformation() {
		return senderToReceiverInformation;
	}
	public void setSenderToReceiverInformation(String senderToReceiverInformation) {
		this.senderToReceiverInformation = senderToReceiverInformation;
	}
	public Date getBgDate() {
		return bgDate;
	}
	public void setBgDate(Date bgDate) {
		this.bgDate = bgDate;
	}
	public String getMsgRef() {
		return msgRef;
	}
	public void setMsgRef(String msgRef) {
		this.msgRef = msgRef;
	}
	public int getNoOfMsg() {
		return noOfMsg;
	}
	public void setNoOfMsg(int noOfMsg) {
		this.noOfMsg = noOfMsg;
	}
	public String getBgDirection() {
		return bgDirection;
	}
	public void setBgDirection(String bgDirection) {
		this.bgDirection = bgDirection;
	}
	public Date getBgIssueDate() {
		return bgIssueDate;
	}
	public void setBgIssueDate(Date bgIssueDate) {
		this.bgIssueDate = bgIssueDate;
	}
	public String getBgRuleDesc() {
		return bgRuleDesc;
	}
	public void setBgRuleDesc(String bgRuleDesc) {
		this.bgRuleDesc = bgRuleDesc;
	}
	public String getBgDetails() {
		return bgDetails;
	}
	public void setBgDetails(String bgDetails) {
		this.bgDetails = bgDetails;
	}
	public int getBgAmount() {
		return bgAmount;
	}
	public void setBgAmount(int bgAmount) {
		this.bgAmount = bgAmount;
	}
	public int getBgStatus() {
		return bgStatus;
	}
	public void setBgStatus(int bgStatus) {
		this.bgStatus = bgStatus;
	}
	public int getBgNoOfAmntmnt() {
		return bgNoOfAmntmnt;
	}
	public void setBgNoOfAmntmnt(int bgNoOfAmntmnt) {
		this.bgNoOfAmntmnt = bgNoOfAmntmnt;
	}
	public Date getBgLastModifiedTime() {
		return bgLastModifiedTime;
	}
	public void setBgLastModifiedTime(Date bgLastModifiedTime) {
		this.bgLastModifiedTime = bgLastModifiedTime;
	}
	public String getAdvisingBank() {
		return advisingBank;
	}
	public void setAdvisingBank(String advisingBank) {
		this.advisingBank = advisingBank;
	}
	public String getRelatedReference() {
		return relatedReference;
	}
	public void setRelatedReference(String relatedReference) {
		this.relatedReference = relatedReference;
	}
	public void setBgAccountIdentification(String bgAccountIdentification) {
		this.bgAccountIdentification = bgAccountIdentification;
	}
	public String getBgAccountIdentification() {
		return bgAccountIdentification;
	}
	 
	public void setBgCurrency(String bgCurrency) {
		this.bgCurrency = bgCurrency;
	}
	public String getBgCurrency() {
		return bgCurrency;
	}
	 
	public void setBgAccountWithNameandAddress(
			String bgAccountWithNameandAddress) {
		this.bgAccountWithNameandAddress = bgAccountWithNameandAddress;
	}
	public String getBgAccountWithNameandAddress() {
		return bgAccountWithNameandAddress;
	}
	public void setAccountWithPartyLocation(String accountWithPartyLocation) {
		this.accountWithPartyLocation = accountWithPartyLocation;
	}
	public String getAccountWithPartyLocation() {
		return accountWithPartyLocation;
	}
	public void setChargesDetails(String chargesDetails) {
		this.chargesDetails = chargesDetails;
	}
	public String getChargesDetails() {
		return chargesDetails;
	}
	public void setBgAccountWithPartyIdentifier(
			String bgAccountWithPartyIdentifier) {
		this.bgAccountWithPartyIdentifier = bgAccountWithPartyIdentifier;
	}
	public String getBgAccountWithPartyIdentifier() {
		return bgAccountWithPartyIdentifier;
	}
	public void setBgChargeAmount(BigDecimal bgChargeAmount) {
		this.bgChargeAmount = bgChargeAmount;
	}
	public BigDecimal getBgChargeAmount() {
		return bgChargeAmount;
	}
	public void setDateofMessageBeingAcknowledged(
			Date dateofMessageBeingAcknowledged) {
		this.dateofMessageBeingAcknowledged = dateofMessageBeingAcknowledged;
	}
	public Date getDateofMessageBeingAcknowledged() {
		return dateofMessageBeingAcknowledged;
	}
	public void setAccountWithPartyIFSC(String accountWithPartyIFSC) {
		this.accountWithPartyIFSC = accountWithPartyIFSC;
	}
	public String getAccountWithPartyIFSC() {
		return accountWithPartyIFSC;
	}
	
	
}
