package com.logica.ngph.dtos;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

public class LCCanonicalDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String lcType;
	private String lcNumber;
	private String lcExpirePlace;
	private Date lcExpiryDate;
	private String applicantNameAddress;
	private String beneficiaryNameAddress;
	private String beneficiaryAccount;
	private String lcCurrency;
	private BigDecimal lcAmount= new BigDecimal(0.00);
	private String positiveTolerance;
	private String negativeTolerance;
	private String maximumCreditAmount;
	private String additionalAmounts;
	private String authorisedBankCode;
	private String authorisationMode;
	private String authorisedAndAddress;
	private String goodsLoadingDispatchPlace;
	private String goodsDestination;
	private Date latestDateofShipment;
	private String shipmentPeriod;
	private String shipmentTerms;
	private String Commodity;

	private String adviseThroughBankCode;
	private String adviseThroughBankpartyidentifier;
	private String adviseThroughBankName;
	private String adviseThroughBankLocation;
	private String adviseThroughBankAcc;
	private String initialDispatchPlace;
	private String finalDeliveryPlace;
	private String repair;
	private Date amountPaidDate;
	private String applicableNarrative;
	private String msgHost;
	private String seqNo;
	private String transferBankReference;
	private String issuingBankPID;
	private String issuingBankCode;
	private String issunigBankNameAndAddress;
	private String nonIssuingBank;
	private String comment;
	
	private BigDecimal totalAmountClaimed = new BigDecimal(0.00);
	private BigDecimal paidAmount = new BigDecimal(0);
	private String sendersCorrespondentPartyIdentifier;
	private String senderCorrespontAcount;
	private String sendersCorrespondentCode;
	private String sendersCorrespondentLocation;
	private String sendersCorrespondentNameandAddress;
	private String receiversCorrespondentPartyIdentifier;
	private String receiverCorrespontAcount;
	private String receiversCorrespondentCode;
	private String receiversCorrespondentLocation;
	private String receiversCorrespondentNameandAddress;
	private String Narrative;
	private String SendertoReceiverInformation;
	private String applicantBankNameAddress;
	private String applicantBankpartyidentifier;
	private String applicantAccount;
	private String applicantBankCode;
	private String senderBank;
	public String getSenderBank() {
		return senderBank;
	}
	public void setSenderBank(String senderBank) {
		this.senderBank = senderBank;
	}
	private String repairMsgref;
	
	private String advisingBank;
	private String DraftsAt;
	private String draweeBankpartyidentifier;
	private String draweeBankCode;
	private String draweeBankNameAddress;
	private String mixedPaymentDetails;
	private String transhipment;
	private String documentsRequired;
	private String additionalConditions;
	private String deferredPaymentDetails;
	private String chargeDetails;
	private BigDecimal netChargeAmount = new BigDecimal(0.00);
	private String periodforPresentation;
	private String confirmationCode;
	private String reimbursingBank;
	private String partyIdentifier;
	private String reimbursingBankCode;
	private String reimbursingBankNameAddress;
	private String instructionstoPayingBank;
	private String msgRef;
	private String partialShipments;
	private String issuingBankAcc;
	//Start :: Added for Mizuho Bank 
	private String relatedReference;
	private String lcAccId;
	private Date lcAckDate;
	private String currency;
	private String chargesClaimed;
	private String lcDispoDocs;
	private BigDecimal lcClaimAmount = new BigDecimal(0.00);
	private String discrepancies;
	//Added for 750 message
	private BigDecimal principalAmount = new BigDecimal(0.00);
	private BigDecimal additionalAmount= new BigDecimal(0.00);
	private BigDecimal totalAmount = new BigDecimal(0.00);
	private String chargesDeducted;
	private String chargesAdded;
	private String beneficiaryBankCode;
	private String reimbersingBankLoc;
	private String reimbursingBankNameandAddress;
	private String accountWithPartyLoc;
	private String accountWithPartyNameAndAddress;
	private String beneficiaryBankLoc;
	private String beneficiaryBankNameAndAddress;
	private String reimbursingBankAcc;
	private String beneficiaryBankAcc;
	private String pricAmount;
	private Date totalPaidDate;
	private String totalAmountClaim;
	private String claimCurrency;
	private String availableWithIdentifier;
	private String reimbursingBankpartyidentifier;
	private String beneficiaryBankpartyidentifier;
	//Added for Mizuho CR
	private String sequence;
	private String sequenceofTotal;
	private String lcPresdvice;
	private String applicentIdentifier;
	private String applicentBankNameandAddr;
	private String availableIdentifier;
	private String draweeIdentifier;
	private String reimbursingIdentifier;
	private String advisingIdentifier;
	private String descriptionofGoods;

	//Added for Mizuho CR
	
	
	//Added for 750 message
	//End :: Added for Mizuho Bank 
	
	
	/**
	 * @return the descriptionofGoods
	 */
	public String getDescriptionofGoods() {
		return descriptionofGoods;
	}
	/**
	 * @param descriptionofGoods the descriptionofGoods to set
	 */
	public void setDescriptionofGoods(String descriptionofGoods) {
		this.descriptionofGoods = descriptionofGoods;
	}
	public String getLcPresdvice() {
		return lcPresdvice;
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
	public String getApplicentIdentifier() {
		return applicentIdentifier;
	}
	public void setApplicentIdentifier(String applicentIdentifier) {
		this.applicentIdentifier = applicentIdentifier;
	}
	public String getApplicentBankNameandAddr() {
		return applicentBankNameandAddr;
	}
	public void setApplicentBankNameandAddr(String applicentBankNameandAddr) {
		this.applicentBankNameandAddr = applicentBankNameandAddr;
	}
	public String getAvailableIdentifier() {
		return availableIdentifier;
	}
	public void setAvailableIdentifier(String availableIdentifier) {
		this.availableIdentifier = availableIdentifier;
	}
	public String getDraweeIdentifier() {
		return draweeIdentifier;
	}
	public void setDraweeIdentifier(String draweeIdentifier) {
		this.draweeIdentifier = draweeIdentifier;
	}
	public String getReimbursingIdentifier() {
		return reimbursingIdentifier;
	}
	public void setReimbursingIdentifier(String reimbursingIdentifier) {
		this.reimbursingIdentifier = reimbursingIdentifier;
	}
	public String getAdvisingIdentifier() {
		return advisingIdentifier;
	}
	public void setAdvisingIdentifier(String advisingIdentifier) {
		this.advisingIdentifier = advisingIdentifier;
	}
	public void setLcPresdvice(String lcPresdvice) {
		this.lcPresdvice = lcPresdvice;
	}
	/**
	 * @return the reimbursingBankpartyidentifier
	 */
	public String getReimbursingBankpartyidentifier() {
		return reimbursingBankpartyidentifier;
	}
	/**
	 * @return the beneficiaryBankpartyidentifier
	 */
	public String getBeneficiaryBankpartyidentifier() {
		return beneficiaryBankpartyidentifier;
	}
	/**
	 * @param beneficiaryBankpartyidentifier the beneficiaryBankpartyidentifier to set
	 */
	public void setBeneficiaryBankpartyidentifier(
			String beneficiaryBankpartyidentifier) {
		this.beneficiaryBankpartyidentifier = beneficiaryBankpartyidentifier;
	}
	/**
	 * @param reimbursingBankpartyidentifier the reimbursingBankpartyidentifier to set
	 */
	public void setReimbursingBankpartyidentifier(
			String reimbursingBankpartyidentifier) {
		this.reimbursingBankpartyidentifier = reimbursingBankpartyidentifier;
	}
	public String getAvailableWithIdentifier() {
		return availableWithIdentifier;
	}
	public void setAvailableWithIdentifier(String availableWithIdentifier) {
		this.availableWithIdentifier = availableWithIdentifier;
	}
	public String getClaimCurrency() {
		return claimCurrency;
	}
	public void setClaimCurrency(String claimCurrency) {
		this.claimCurrency = claimCurrency;
	}
	
	/**
	 * @return the relatedReference
	 */
	public String getRelatedReference() {
		return relatedReference;
	}
	
	
	/**
	 * @return the totalPaidDate
	 */
	public Date getTotalPaidDate() {
		return totalPaidDate;
	}
	/**
	 * @param totalPaidDate the totalPaidDate to set
	 */
	public void setTotalPaidDate(Date totalPaidDate) {
		this.totalPaidDate = totalPaidDate;
	}
	/**
	 * @return the totalAmountClaim
	 */
	public String getTotalAmountClaim() {
		return totalAmountClaim;
	}
	/**
	 * @param totalAmountClaim the totalAmountClaim to set
	 */
	public void setTotalAmountClaim(String totalAmountClaim) {
		this.totalAmountClaim = totalAmountClaim;
	}
	/**
	 * @return the pricAmount
	 */
	public String getPricAmount() {
		return pricAmount;
	}
	/**
	 * @param pricAmount the pricAmount to set
	 */
	public void setPricAmount(String pricAmount) {
		this.pricAmount = pricAmount;
	}
	/**
	 * @return the reimbursingBankAcc
	 */
	public String getReimbursingBankAcc() {
		return reimbursingBankAcc;
	}
	/**
	 * @param reimbursingBankAcc the reimbursingBankAcc to set
	 */
	public void setReimbursingBankAcc(String reimbursingBankAcc) {
		this.reimbursingBankAcc = reimbursingBankAcc;
	}
	/**
	 * @return the beneficiaryBankAcc
	 */
	public String getBeneficiaryBankAcc() {
		return beneficiaryBankAcc;
	}
	/**
	 * @param beneficiaryBankAcc the beneficiaryBankAcc to set
	 */
	public void setBeneficiaryBankAcc(String beneficiaryBankAcc) {
		this.beneficiaryBankAcc = beneficiaryBankAcc;
	}
	/**
	 * @return the reimbersingBankLoc
	 */
	public String getReimbersingBankLoc() {
		return reimbersingBankLoc;
	}
	/**
	 * @param reimbersingBankLoc the reimbersingBankLoc to set
	 */
	public void setReimbersingBankLoc(String reimbersingBankLoc) {
		this.reimbersingBankLoc = reimbersingBankLoc;
	}
	/**
	 * @return the reimbursingBankNameandAddress
	 */
	public String getReimbursingBankNameandAddress() {
		return reimbursingBankNameandAddress;
	}
	/**
	 * @param reimbursingBankNameandAddress the reimbursingBankNameandAddress to set
	 */
	public void setReimbursingBankNameandAddress(
			String reimbursingBankNameandAddress) {
		this.reimbursingBankNameandAddress = reimbursingBankNameandAddress;
	}
	/**
	 * @return the accountWithPartyLoc
	 */
	public String getAccountWithPartyLoc() {
		return accountWithPartyLoc;
	}
	/**
	 * @param accountWithPartyLoc the accountWithPartyLoc to set
	 */
	public void setAccountWithPartyLoc(String accountWithPartyLoc) {
		this.accountWithPartyLoc = accountWithPartyLoc;
	}
	/**
	 * @return the accountWithPartyNameAndAddress
	 */
	public String getAccountWithPartyNameAndAddress() {
		return accountWithPartyNameAndAddress;
	}
	/**
	 * @param accountWithPartyNameAndAddress the accountWithPartyNameAndAddress to set
	 */
	public void setAccountWithPartyNameAndAddress(
			String accountWithPartyNameAndAddress) {
		this.accountWithPartyNameAndAddress = accountWithPartyNameAndAddress;
	}
	/**
	 * @return the beneficiaryBankLoc
	 */
	public String getBeneficiaryBankLoc() {
		return beneficiaryBankLoc;
	}
	/**
	 * @param beneficiaryBankLoc the beneficiaryBankLoc to set
	 */
	public void setBeneficiaryBankLoc(String beneficiaryBankLoc) {
		this.beneficiaryBankLoc = beneficiaryBankLoc;
	}
	/**
	 * @return the beneficiaryBankNameAndAddress
	 */
	public String getBeneficiaryBankNameAndAddress() {
		return beneficiaryBankNameAndAddress;
	}
	/**
	 * @param beneficiaryBankNameAndAddress the beneficiaryBankNameAndAddress to set
	 */
	public void setBeneficiaryBankNameAndAddress(
			String beneficiaryBankNameAndAddress) {
		this.beneficiaryBankNameAndAddress = beneficiaryBankNameAndAddress;
	}
	/**
	 * @return the beneficiaryBankCode
	 */
	public String getBeneficiaryBankCode() {
		return beneficiaryBankCode;
	}
	/**
	 * @param beneficiaryBankCode the beneficiaryBankCode to set
	 */
	public void setBeneficiaryBankCode(String beneficiaryBankCode) {
		this.beneficiaryBankCode = beneficiaryBankCode;
	}
	/**
	 * @return the chargesDeducted
	 */
	public String getChargesDeducted() {
		return chargesDeducted;
	}
	/**
	 * @param chargesDeducted the chargesDeducted to set
	 */
	public void setChargesDeducted(String chargesDeducted) {
		this.chargesDeducted = chargesDeducted;
	}
	/**
	 * @return the chargesAdded
	 */
	public String getChargesAdded() {
		return chargesAdded;
	}
	/**
	 * @param chargesAdded the chargesAdded to set
	 */
	public void setChargesAdded(String chargesAdded) {
		this.chargesAdded = chargesAdded;
	}
	/**
	 * @return the principalAmount
	 */
	public BigDecimal getPrincipalAmount() {
		return principalAmount;
	}
	/**
	 * @param principalAmount the principalAmount to set
	 */
	public void setPrincipalAmount(BigDecimal principalAmount) {
		this.principalAmount = principalAmount;
	}
	/**
	 * @return the additionalAmount
	 */
	public BigDecimal getAdditionalAmount() {
		return additionalAmount;
	}
	/**
	 * @param additionalAmount the additionalAmount to set
	 */
	public void setAdditionalAmount(BigDecimal additionalAmount) {
		this.additionalAmount = additionalAmount;
	}
	/**
	 * @return the totalAmount
	 */
	public BigDecimal getTotalAmount() {
		return totalAmount;
	}
	/**
	 * @param totalAmount the totalAmount to set
	 */
	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}
	/**
	 * @return the discrepancies
	 */
	public String getDiscrepancies() {
		return discrepancies;
	}
	/**
	 * @param discrepancies the discrepancies to set
	 */
	public void setDiscrepancies(String discrepancies) {
		this.discrepancies = discrepancies;
	}
	/**
	 * @return the lcClaimAmount
	 */
	public BigDecimal getLcClaimAmount() {
		return lcClaimAmount;
	}
	/**
	 * @param lcClaimAmount the lcClaimAmount to set
	 */
	public void setLcClaimAmount(BigDecimal lcClaimAmount) {
		this.lcClaimAmount = lcClaimAmount;
	}
	/**
	 * @return the lcDispoDocs
	 */
	public String getLcDispoDocs() {
		return lcDispoDocs;
	}
	/**
	 * @param lcDispoDocs the lcDispoDocs to set
	 */
	public void setLcDispoDocs(String lcDispoDocs) {
		this.lcDispoDocs = lcDispoDocs;
	}
	/**
	 * @return the chargesClaimed
	 */
	public String getChargesClaimed() {
		return chargesClaimed;
	}
	/**
	 * @param chargesClaimed the chargesClaimed to set
	 */
	public void setChargesClaimed(String chargesClaimed) {
		this.chargesClaimed = chargesClaimed;
	}
	/**
	 * @return the currency
	 */
	public String getCurrency() {
		return currency;
	}
	/**
	 * @param currency the currency to set
	 */
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	/**
	 * @param relatedReference the relatedReference to set
	 */
	public void setRelatedReference(String relatedReference) {
		this.relatedReference = relatedReference;
	}
	/**
	 * @return the lcAccId
	 */
	public String getLcAccId() {
		return lcAccId;
	}
	/**
	 * @param lcAccId the lcAccId to set
	 */
	public void setLcAccId(String lcAccId) {
		this.lcAccId = lcAccId;
	}
	/**
	 * @return the lcAckDate
	 */
	public Date getLcAckDate() {
		return lcAckDate;
	}
	/**
	 * @param lcAckDate the lcAckDate to set
	 */
	public void setLcAckDate(Date lcAckDate) {
		this.lcAckDate = lcAckDate;
	}
	public String getIssuingBankAcc() {
		return issuingBankAcc;
	}
	public void setIssuingBankAcc(String issuingBankAcc) {
		this.issuingBankAcc = issuingBankAcc;
	}
	/*private String lcType;
	private String lcNumber;
	private String advisingBank;*/	
	private Date issueDate;
	//private Date expiryDate;
	private String applicant;
	private String beneficiary;
//	private String currency;
//	private BigDecimal amount;
	//private String narrative;
	private String negotiatingBankAccount;
	private String negotiatingBankCode;
	private String negotiatingBankNameAndAddress;
	private String acountID;
	private BigDecimal creditAmount= new BigDecimal(0.00);
	private String additionalAmountsCovered;	
	private String draweeBankAccount;
	private String reimbursingBanksCharges;
	private String otherCharges;
	private String applicableRule;
	
	public String getApplicableRule() {
		return applicableRule;
	}
	public void setApplicableRule(String applicableRule) {
		this.applicableRule = applicableRule;
	}
	private Integer lcAmndmntNo= new Integer(0);	
	private String receiverBankReference;
	private String senderBankReference;
	private Date amendmentDate;
	private BigDecimal increaseAmendAmount= new BigDecimal(0.00);
	private BigDecimal decreaseAmendAmount= new BigDecimal(0.00);
	private BigDecimal newLcAmount = new BigDecimal(0.00);
	private BigDecimal oldLcAmount = new BigDecimal(0.00);
	private Date oldAmendExpiryDate;
	private Date newAmendExpiryDate;
	private BigDecimal lcNetAmtClaimed;
	public BigDecimal getLcNetAmtClaimed() {
		return lcNetAmtClaimed;
	}
	public void setLcNetAmtClaimed(BigDecimal lcNetAmtClaimed) {
		this.lcNetAmtClaimed = lcNetAmtClaimed;
	}
	private Date msgValueDate;
	private String presentingBanksReference;
	
	
	public String getFirstBeneficiaryNameAddress() {
		return firstBeneficiaryNameAddress;
	}
	public void setFirstBeneficiaryNameAddress(String firstBeneficiaryNameAddress) {
		this.firstBeneficiaryNameAddress = firstBeneficiaryNameAddress;
	}
	private Date PrincipalAmountClaimedDate;
	private String firstBeneficiaryNameAddress;
	
	public Date getPrincipalAmountClaimedDate() {
		return PrincipalAmountClaimedDate;
	}
	public void setPrincipalAmountClaimedDate(Date principalAmountClaimedDate) {
		PrincipalAmountClaimedDate = principalAmountClaimedDate;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getTransferBankReference() {
		return transferBankReference;
	}
	public void setTransferBankReference(String transferBankReference) {
		this.transferBankReference = transferBankReference;
	}
	public String getSeqNo() {
		return seqNo;
	}
	public void setSeqNo(String seqNo) {
		this.seqNo = seqNo;
	}
	public String getMsgHost() {
		return msgHost;
	}
	public void setMsgHost(String msgHost) {
		this.msgHost = msgHost;
	}
	public String getApplicableNarrative() {
		return applicableNarrative;
	}
	public void setApplicableNarrative(String applicableNarrative) {
		this.applicableNarrative = applicableNarrative;
	}
	public Date getAmountPaidDate() {
		return amountPaidDate;
	}
	public void setAmountPaidDate(Date amountPaidDate) {
		this.amountPaidDate = amountPaidDate;
	}
	public String getRepair() {
		return repair;
	}
	public void setRepair(String repair) {
		this.repair = repair;
	}
	public String getInitialDispatchPlace() {
		return initialDispatchPlace;
	}
	public void setInitialDispatchPlace(String initialDispatchPlace) {
		this.initialDispatchPlace = initialDispatchPlace;
	}
	public String getFinalDeliveryPlace() {
		return finalDeliveryPlace;
	}
	public void setFinalDeliveryPlace(String finalDeliveryPlace) {
		this.finalDeliveryPlace = finalDeliveryPlace;
	}
	public String getAdviseThroughBankAcc() {
		return adviseThroughBankAcc;
	}
	public void setAdviseThroughBankAcc(String adviseThroughBankAcc) {
		this.adviseThroughBankAcc = adviseThroughBankAcc;
	}
	
	
	public String getRepairMsgref() {
		return repairMsgref;
	}
	public void setRepairMsgref(String repairMsgref) {
		this.repairMsgref = repairMsgref;
	}
	public String getApplicantBankCode() {
		return applicantBankCode;
	}
	public void setApplicantBankCode(String applicantBankCode) {
		this.applicantBankCode = applicantBankCode;
	}
		
	
	public String getPartialShipments() {
		return partialShipments;
	}
	public void setPartialShipments(String partialShipments) {
		this.partialShipments = partialShipments;
	}
	public String getMsgRef() {
		return msgRef;
	}
	public void setMsgRef(String msgRef) {
		this.msgRef = msgRef;
	}
	public String getLcType() {
		return lcType;
	}
	public void setLcType(String lcType) {
		this.lcType = lcType;
	}
	public String getLcNumber() {
		return lcNumber;
	}
	public void setLcNumber(String lcNumber) {
		this.lcNumber = lcNumber;
	}
	public String getLcExpirePlace() {
		return lcExpirePlace;
	}
	public void setLcExpirePlace(String lcExpirePlace) {
		this.lcExpirePlace = lcExpirePlace;
	}
	public Date getLcExpiryDate() {
		return lcExpiryDate;
	}
	public void setLcExpiryDate(Date lcExpiryDate) {
		this.lcExpiryDate = lcExpiryDate;
	}
	public String getApplicantNameAddress() {
		return applicantNameAddress;
	}
	public void setApplicantNameAddress(String applicantNameAddress) {
		this.applicantNameAddress = applicantNameAddress;
	}
	public String getBeneficiaryNameAddress() {
		return beneficiaryNameAddress;
	}
	public void setBeneficiaryNameAddress(String beneficiaryNameAddress) {
		this.beneficiaryNameAddress = beneficiaryNameAddress;
	}
	public String getIssuingBankPID() {
		return issuingBankPID;
	}
	public void setIssuingBankPID(String issuingBankPID) {
		this.issuingBankPID = issuingBankPID;
	}
	public String getIssuingBankCode() {
		return issuingBankCode;
	}
	public void setIssuingBankCode(String issuingBankCode) {
		this.issuingBankCode = issuingBankCode;
	}
	public String getIssunigBankNameAndAddress() {
		return issunigBankNameAndAddress;
	}
	public void setIssunigBankNameAndAddress(String issunigBankNameAndAddress) {
		this.issunigBankNameAndAddress = issunigBankNameAndAddress;
	}
	public String getNonIssuingBank() {
		return nonIssuingBank;
	}
	public void setNonIssuingBank(String nonIssuingBank) {
		this.nonIssuingBank = nonIssuingBank;
	}
	public String getBeneficiaryAccount() {
		return beneficiaryAccount;
	}
	public void setBeneficiaryAccount(String beneficiaryAccount) {
		this.beneficiaryAccount = beneficiaryAccount;
	}
	public String getLcCurrency() {
		return lcCurrency;
	}
	public void setLcCurrency(String lcCurrency) {
		this.lcCurrency = lcCurrency;
	}
	public BigDecimal getLcAmount() {
		return lcAmount;
	}
	public void setLcAmount(BigDecimal lcAmount) {
		this.lcAmount = lcAmount;
	}
	public String getPositiveTolerance() {
		return positiveTolerance;
	}
	public void setPositiveTolerance(String positiveTolerance) {
		this.positiveTolerance = positiveTolerance;
	}
	public String getNegativeTolerance() {
		return negativeTolerance;
	}
	public void setNegativeTolerance(String negativeTolerance) {
		this.negativeTolerance = negativeTolerance;
	}
	public String getMaximumCreditAmount() {
		return maximumCreditAmount;
	}
	public void setMaximumCreditAmount(String maximumCreditAmount) {
		this.maximumCreditAmount = maximumCreditAmount;
	}
	public String getAdditionalAmounts() {
		return additionalAmounts;
	}
	public void setAdditionalAmounts(String additionalAmounts) {
		this.additionalAmounts = additionalAmounts;
	}
	public String getAuthorisedBankCode() {
		return authorisedBankCode;
	}
	public void setAuthorisedBankCode(String authorisedBankCode) {
		this.authorisedBankCode = authorisedBankCode;
	}
	public String getAuthorisationMode() {
		return authorisationMode;
	}
	public void setAuthorisationMode(String authorisationMode) {
		this.authorisationMode = authorisationMode;
	}
	public String getAuthorisedAndAddress() {
		return authorisedAndAddress;
	}
	public void setAuthorisedAndAddress(String authorisedAndAddress) {
		this.authorisedAndAddress = authorisedAndAddress;
	}
	public String getGoodsLoadingDispatchPlace() {
		return goodsLoadingDispatchPlace;
	}
	public void setGoodsLoadingDispatchPlace(String goodsLoadingDispatchPlace) {
		this.goodsLoadingDispatchPlace = goodsLoadingDispatchPlace;
	}
	public String getGoodsDestination() {
		return goodsDestination;
	}
	public void setGoodsDestination(String goodsDestination) {
		this.goodsDestination = goodsDestination;
	}
	public Date getLatestDateofShipment() {
		return latestDateofShipment;
	}
	public void setLatestDateofShipment(Date latestDateofShipment) {
		this.latestDateofShipment = latestDateofShipment;
	}
	public String getShipmentPeriod() {
		return shipmentPeriod;
	}
	public void setShipmentPeriod(String shipmentPeriod) {
		this.shipmentPeriod = shipmentPeriod;
	}
	public String getShipmentTerms() {
		return shipmentTerms;
	}
	public void setShipmentTerms(String shipmentTerms) {
		this.shipmentTerms = shipmentTerms;
	}
	public String getCommodity() {
		return Commodity;
	}
	public void setCommodity(String commodity) {
		Commodity = commodity;
	}
	
	public String getAdviseThroughBankCode() {
		return adviseThroughBankCode;
	}
	public void setAdviseThroughBankCode(String adviseThroughBankCode) {
		this.adviseThroughBankCode = adviseThroughBankCode;
	}
	public String getAdviseThroughBankpartyidentifier() {
		return adviseThroughBankpartyidentifier;
	}
	public void setAdviseThroughBankpartyidentifier(
			String adviseThroughBankpartyidentifier) {
		this.adviseThroughBankpartyidentifier = adviseThroughBankpartyidentifier;
	}
	public String getAdviseThroughBankName() {
		return adviseThroughBankName;
	}
	public void setAdviseThroughBankName(String adviseThroughBankName) {
		this.adviseThroughBankName = adviseThroughBankName;
	}
	public String getAdviseThroughBankLocation() {
		return adviseThroughBankLocation;
	}
	public void setAdviseThroughBankLocation(String adviseThroughBankLocation) {
		this.adviseThroughBankLocation = adviseThroughBankLocation;
	}
	public String getNarrative() {
		return Narrative;
	}
	public void setNarrative(String narrative) {
		Narrative = narrative;
	}
	public String getSendertoReceiverInformation() {
		return SendertoReceiverInformation;
	}
	public void setSendertoReceiverInformation(String sendertoReceiverInformation) {
		SendertoReceiverInformation = sendertoReceiverInformation;
	}
	public String getApplicantBankNameAddress() {
		return applicantBankNameAddress;
	}
	public void setApplicantBankNameAddress(String applicantBankNameAddress) {
		this.applicantBankNameAddress = applicantBankNameAddress;
	}
	public String getApplicantBankpartyidentifier() {
		return applicantBankpartyidentifier;
	}
	public void setApplicantBankpartyidentifier(String applicantBankpartyidentifier) {
		this.applicantBankpartyidentifier = applicantBankpartyidentifier;
	}
	public String getApplicantAccount() {
		return applicantAccount;
	}
	public void setApplicantAccount(String applicantAccount) {
		this.applicantAccount = applicantAccount;
	}
	public String getAdvisingBank() {
		return advisingBank;
	}
	public void setAdvisingBank(String advisingBank) {
		this.advisingBank = advisingBank;
	}
	public String getDraftsAt() {
		return DraftsAt;
	}
	public void setDraftsAt(String draftsAt) {
		DraftsAt = draftsAt;
	}
	public String getDraweeBankpartyidentifier() {
		return draweeBankpartyidentifier;
	}
	public void setDraweeBankpartyidentifier(String draweeBankpartyidentifier) {
		this.draweeBankpartyidentifier = draweeBankpartyidentifier;
	}
	public String getDraweeBankCode() {
		return draweeBankCode;
	}
	public void setDraweeBankCode(String draweeBankCode) {
		this.draweeBankCode = draweeBankCode;
	}
	public String getDraweeBankNameAddress() {
		return draweeBankNameAddress;
	}
	public void setDraweeBankNameAddress(String draweeBankNameAddress) {
		this.draweeBankNameAddress = draweeBankNameAddress;
	}
	public String getMixedPaymentDetails() {
		return mixedPaymentDetails;
	}
	public void setMixedPaymentDetails(String mixedPaymentDetails) {
		this.mixedPaymentDetails = mixedPaymentDetails;
	}
	public String getTranshipment() {
		return transhipment;
	}
	public void setTranshipment(String transhipment) {
		this.transhipment = transhipment;
	}
	public String getDocumentsRequired() {
		return documentsRequired;
	}
	public void setDocumentsRequired(String documentsRequired) {
		this.documentsRequired = documentsRequired;
	}
	public String getAdditionalConditions() {
		return additionalConditions;
	}
	public void setAdditionalConditions(String additionalConditions) {
		this.additionalConditions = additionalConditions;
	}
	public String getDeferredPaymentDetails() {
		return deferredPaymentDetails;
	}
	public void setDeferredPaymentDetails(String deferredPaymentDetails) {
		this.deferredPaymentDetails = deferredPaymentDetails;
	}
	public String getChargeDetails() {
		return chargeDetails;
	}
	public void setChargeDetails(String chargeDetails) {
		this.chargeDetails = chargeDetails;
	}
	public BigDecimal getNetChargeAmount() {
		return netChargeAmount;
	}
	public void setNetChargeAmount(BigDecimal netChargeAmount) {
		this.netChargeAmount = netChargeAmount;
	}
	public String getPeriodforPresentation() {
		return periodforPresentation;
	}
	public void setPeriodforPresentation(String periodforPresentation) {
		this.periodforPresentation = periodforPresentation;
	}
	public String getConfirmationCode() {
		return confirmationCode;
	}
	public void setConfirmationCode(String confirmationCode) {
		this.confirmationCode = confirmationCode;
	}
	public String getReimbursingBank() {
		return reimbursingBank;
	}
	public void setReimbursingBank(String reimbursingBank) {
		this.reimbursingBank = reimbursingBank;
	}
	public String getPartyIdentifier() {
		return partyIdentifier;
	}
	public void setPartyIdentifier(String partyIdentifier) {
		this.partyIdentifier = partyIdentifier;
	}
	public String getReimbursingBankCode() {
		return reimbursingBankCode;
	}
	public void setReimbursingBankCode(String reimbursingBankCode) {
		this.reimbursingBankCode = reimbursingBankCode;
	}
	public String getReimbursingBankNameAddress() {
		return reimbursingBankNameAddress;
	}
	public void setReimbursingBankNameAddress(String reimbursingBankNameAddress) {
		this.reimbursingBankNameAddress = reimbursingBankNameAddress;
	}
	public String getInstructionstoPayingBank() {
		return instructionstoPayingBank;
	}
	public void setInstructionstoPayingBank(String instructionstoPayingBank) {
		this.instructionstoPayingBank = instructionstoPayingBank;
	}
	
	
	public Date getIssueDate() {
		return issueDate;
	}
	public void setIssueDate(Date issueDate) {
		this.issueDate = issueDate;
	}
	public String getApplicant() {
		return applicant;
	}
	public void setApplicant(String applicant) {
		this.applicant = applicant;
	}
	public String getBeneficiary() {
		return beneficiary;
	}
	public void setBeneficiary(String beneficiary) {
		this.beneficiary = beneficiary;
	}
	public String getPresentingBanksReference() {
		return presentingBanksReference;
	}
	public void setPresentingBanksReference(String presentingBanksReference) {
		this.presentingBanksReference = presentingBanksReference;
	}
	public BigDecimal getTotalAmountClaimed() {
		return totalAmountClaimed;
	}
	public void setTotalAmountClaimed(BigDecimal totalAmountClaimed) {
		this.totalAmountClaimed = totalAmountClaimed;
	}
	public BigDecimal getPaidAmount() {
		return paidAmount;
	}
	public void setPaidAmount(BigDecimal paidAmount) {
		this.paidAmount = paidAmount;
	}
	public String getSendersCorrespondentPartyIdentifier() {
		return sendersCorrespondentPartyIdentifier;
	}
	public void setSendersCorrespondentPartyIdentifier(
			String sendersCorrespondentPartyIdentifier) {
		this.sendersCorrespondentPartyIdentifier = sendersCorrespondentPartyIdentifier;
	}
	public String getSenderCorrespontAcount() {
		return senderCorrespontAcount;
	}
	public void setSenderCorrespontAcount(String senderCorrespontAcount) {
		this.senderCorrespontAcount = senderCorrespontAcount;
	}
	public String getSendersCorrespondentCode() {
		return sendersCorrespondentCode;
	}
	public void setSendersCorrespondentCode(String sendersCorrespondentCode) {
		this.sendersCorrespondentCode = sendersCorrespondentCode;
	}
	public String getSendersCorrespondentLocation() {
		return sendersCorrespondentLocation;
	}
	public void setSendersCorrespondentLocation(String sendersCorrespondentLocation) {
		this.sendersCorrespondentLocation = sendersCorrespondentLocation;
	}
	public String getSendersCorrespondentNameandAddress() {
		return sendersCorrespondentNameandAddress;
	}
	public void setSendersCorrespondentNameandAddress(
			String sendersCorrespondentNameandAddress) {
		this.sendersCorrespondentNameandAddress = sendersCorrespondentNameandAddress;
	}
	public String getReceiversCorrespondentPartyIdentifier() {
		return receiversCorrespondentPartyIdentifier;
	}
	public void setReceiversCorrespondentPartyIdentifier(
			String receiversCorrespondentPartyIdentifier) {
		this.receiversCorrespondentPartyIdentifier = receiversCorrespondentPartyIdentifier;
	}
	public String getReceiverCorrespontAcount() {
		return receiverCorrespontAcount;
	}
	public void setReceiverCorrespontAcount(String receiverCorrespontAcount) {
		this.receiverCorrespontAcount = receiverCorrespontAcount;
	}
	public String getReceiversCorrespondentCode() {
		return receiversCorrespondentCode;
	}
	public void setReceiversCorrespondentCode(String receiversCorrespondentCode) {
		this.receiversCorrespondentCode = receiversCorrespondentCode;
	}
	public String getReceiversCorrespondentLocation() {
		return receiversCorrespondentLocation;
	}
	public void setReceiversCorrespondentLocation(
			String receiversCorrespondentLocation) {
		this.receiversCorrespondentLocation = receiversCorrespondentLocation;
	}
	public String getReceiversCorrespondentNameandAddress() {
		return receiversCorrespondentNameandAddress;
	}
	public void setReceiversCorrespondentNameandAddress(
			String receiversCorrespondentNameandAddress) {
		this.receiversCorrespondentNameandAddress = receiversCorrespondentNameandAddress;
	}
	
	
	private String negotiatingBankPartyIdentifier;
	public String getNegotiatingBankPartyIdentifier() {
		return negotiatingBankPartyIdentifier;
	}
	public void setNegotiatingBankPartyIdentifier(
			String negotiatingBankPartyIdentifier) {
		this.negotiatingBankPartyIdentifier = negotiatingBankPartyIdentifier;
	}
	public String getNegotiatingBankAccount() {
		return negotiatingBankAccount;
	}
	public void setNegotiatingBankAccount(String negotiatingBankAccount) {
		this.negotiatingBankAccount = negotiatingBankAccount;
	}
	public String getNegotiatingBankCode() {
		return negotiatingBankCode;
	}
	public void setNegotiatingBankCode(String negotiatingBankCode) {
		this.negotiatingBankCode = negotiatingBankCode;
	}
	public String getNegotiatingBankNameAndAddress() {
		return negotiatingBankNameAndAddress;
	}
	public void setNegotiatingBankNameAndAddress(
			String negotiatingBankNameAndAddress) {
		this.negotiatingBankNameAndAddress = negotiatingBankNameAndAddress;
	}
	public String getAcountID() {
		return acountID;
	}
	public void setAcountID(String acountID) {
		this.acountID = acountID;
	}
	public BigDecimal getCreditAmount() {
		return creditAmount;
	}
	public void setCreditAmount(BigDecimal creditAmount) {
		this.creditAmount = creditAmount;
	}
	public String getAdditionalAmountsCovered() {
		return additionalAmountsCovered;
	}
	public void setAdditionalAmountsCovered(String additionalAmountsCovered) {
		this.additionalAmountsCovered = additionalAmountsCovered;
	}

	public String getDraweeBankAccount() {
		return draweeBankAccount;
	}
	public void setDraweeBankAccount(String draweeBankAccount) {
		this.draweeBankAccount = draweeBankAccount;
	}
	public String getReimbursingBanksCharges() {
		return reimbursingBanksCharges;
	}
	public void setReimbursingBanksCharges(String reimbursingBanksCharges) {
		this.reimbursingBanksCharges = reimbursingBanksCharges;
	}
	public String getOtherCharges() {
		return otherCharges;
	}
	public void setOtherCharges(String otherCharges) {
		this.otherCharges = otherCharges;
	}
	
	
	public Date getMsgValueDate() {
		return msgValueDate;
	}
	public void setMsgValueDate(Date msgValueDate) {
		this.msgValueDate = msgValueDate;
	}
	public Date getNewAmendExpiryDate() {
		return newAmendExpiryDate;
	}
	public void setNewAmendExpiryDate(Date newAmendExpiryDate) {
		this.newAmendExpiryDate = newAmendExpiryDate;
	}
	public Integer getLcAmndmntNo() {
		return lcAmndmntNo;
	}
	public void setLcAmndmntNo(Integer lcAmndmntNo) {
		this.lcAmndmntNo = lcAmndmntNo;
	}

	public String getReceiverBankReference() {
		return receiverBankReference;
	}
	public void setReceiverBankReference(String receiverBankReference) {
		this.receiverBankReference = receiverBankReference;
	}
	public String getSenderBankReference() {
		return senderBankReference;
	}
	public void setSenderBankReference(String senderBankReference) {
		this.senderBankReference = senderBankReference;
	}
	public Date getAmendmentDate() {
		return amendmentDate;
	}
	public void setAmendmentDate(Date amendmentDate) {
		this.amendmentDate = amendmentDate;
	}
	public BigDecimal getIncreaseAmendAmount() {
		return increaseAmendAmount;
	}
	public void setIncreaseAmendAmount(BigDecimal increaseAmendAmount) {
		this.increaseAmendAmount = increaseAmendAmount;
	}
	public BigDecimal getDecreaseAmendAmount() {
		return decreaseAmendAmount;
	}
	public void setDecreaseAmendAmount(BigDecimal decreaseAmendAmount) {
		this.decreaseAmendAmount = decreaseAmendAmount;
	}
	public BigDecimal getNewLcAmount() {
		return newLcAmount;
	}
	public void setNewLcAmount(BigDecimal newLcAmount) {
		this.newLcAmount = newLcAmount;
	}
	public BigDecimal getOldLcAmount() {
		return oldLcAmount;
	}
	public void setOldLcAmount(BigDecimal oldLcAmount) {
		this.oldLcAmount = oldLcAmount;
	}
	public Date getOldAmendExpiryDate() {
		return oldAmendExpiryDate;
	}
	public void setOldAmendExpiryDate(Date oldAmendExpiryDate) {
		this.oldAmendExpiryDate = oldAmendExpiryDate;
	}
	private String txnReference;
	public String getTxnReference() {
		return txnReference;
	}
	public void setTxnReference(String txnReference) {
		this.txnReference = txnReference;
	}
	public String getCustTxnReference() {
		return custTxnReference;
	}
	public void setCustTxnReference(String custTxnReference) {
		this.custTxnReference = custTxnReference;
	}
	public String getSndrTxnId() {
		return sndrTxnId;
	}
	public void setSndrTxnId(String sndrTxnId) {
		this.sndrTxnId = sndrTxnId;
	}
	private String custTxnReference;
	private String sndrTxnId;

	private String serviceID;
	public String getServiceID() {
		return serviceID;
	}
	public void setServiceID(String serviceID) {
		this.serviceID = serviceID;
	}
	public BigDecimal getMesgIsReturn() {
		return mesgIsReturn;
	}
	public void setMesgIsReturn(BigDecimal mesgIsReturn) {
		this.mesgIsReturn = mesgIsReturn;
	}
	public BigDecimal getMsgPDECount() {
		return msgPDECount;
	}
	public void setMsgPDECount(BigDecimal msgPDECount) {
		this.msgPDECount = msgPDECount;
	}
	private BigDecimal mesgIsReturn;
	private BigDecimal msgPDECount;
	private Integer msgGRPSeq;
	public Integer getMsgGRPSeq() {
		return msgGRPSeq;
	}
	public void setMsgGRPSeq(Integer msgGRPSeq) {
		this.msgGRPSeq = msgGRPSeq;
	}
	private Timestamp pymntAcceptedTime;
	public Timestamp getPymntAcceptedTime() {
		return pymntAcceptedTime;
	}
	public void setPymntAcceptedTime(Timestamp pymntAcceptedTime) {
		this.pymntAcceptedTime = pymntAcceptedTime;
	}
	private String lcPrevAdvRef;
	private String msgCurrency;

	public String getMsgCurrency() {
		return msgCurrency;
	}
	public void setMsgCurrency(String msgCurrency) {
		this.msgCurrency = msgCurrency;
	}
	public String getLcPrevAdvRef() {
		return lcPrevAdvRef;
	}
	public void setLcPrevAdvRef(String lcPrevAdvRef) {
		this.lcPrevAdvRef = lcPrevAdvRef;
	}
	
	
}
