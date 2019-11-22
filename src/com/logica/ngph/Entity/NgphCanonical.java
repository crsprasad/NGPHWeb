package com.logica.ngph.Entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="TA_MESSAGES_TX")
public class NgphCanonical implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6409001704113734088L;
	private String msgRef;
	private String grpMsgId;
	private Integer grpSeq;
	
	private String msgHost;
	private String msgChnlType;
	private String srcMsgType;
	
	private String msgChannel;
	private String srcMsgSubType;
	private String dstMsgType;
	private String dstMsgSubType;
	private String msgStatus;
	private String msgDirection;
	private String msgPrevStatus;
	private Timestamp receivedTime;
	private Timestamp lastModTime;
	private String txnReference;
	private String custTxnReference;
	private String sndrTxnId;
	private String clrgSysReference;
	private String sndrPymtPriority;
	private String clrgChannel;
	private String svcLevelCode;
	private String svcLevelPriority;
	private String lclInstCode;
	private String lclInstPriority;
	private String catgPurposeCode;
	


	private String catgPurposePriority;
	
	private String msgCurrency;
	private BigDecimal msgAmount;
	private Timestamp msgValueDate;
	private String sndrSttlmntPriority;
	private Timestamp drDateTime;
	private Timestamp crDateTime;
	private Timestamp clsDateTime;
	
	private Timestamp sttlmntTillTime;
	private Timestamp sttlmntFromTime;
	private Timestamp sttlmntRejTime;
	
	private Timestamp pymntAcceptedTime;
	private Timestamp cashpoolAdjstmntTime;
	private String instructedCurrency;
	private BigDecimal instructedAmount;
	private BigDecimal xchangeRate;
	private String chargeBearer;
	//private String chargeCurrency;
	//private BigDecimal chargeAmount;
	//private String chargingPartyBank;
	private String prevInstructingBank;
	private String prevInstructingAgentAcct;
	private String senderBank;
	private String receiverBank;
	private String intermediary1Bank;
	private String intermediary1AgentAcct;
	private String intermediary2Bank;
	private String intermediary2AgentAcct;
	private String intermediary3Bank;
	private String intermediary3AgentAcct;
	//private String ultimateDebtor;
	//The NGPH Code of the party identifier as the initiating party
	//@Column MSGS_INITGPRTY
	//private String initiatingParty;
	//The NGPH Code of the customer identifier as the debtor, the person who owes the money to the ultimate creditor. 
	//@Column MSGS_DBTR
	//The account id of the debtor customer
	//@Column MSGS_DBTRACCT
	private String orderingCustAccount;
	
	//Financial institution servicing an account for the debtor
	//@Column MSGS_DBTRAGT
	private String orderingInstitution;
	
	//Unambiguous identification of the account of the debtor agent at its servicing agent in the payment chain
	//@Column MSGS_DBTRAGTACCT
	private String orderingInstitutionAcct;
	//Financial institution servicing an account for the creditor
	//@Column MSGS_CDTRAGT
	private String accountWithInstitution;
	
	//Unambiguous identification of the account of the creditor agent at its servicing agent to which a credit entry will be made as a result of the payment transaction.
	//@Column MSGS_CDTRAGTACCT
	private String accountWithInstitutionAcct;
	//MSGS_REL_STATUS
	private String msgRelStatus;
	//@LC_STATUS
	/*private String relatedStatus;
	@Column(name="LC_STATUS")
	public String getRelatedStatus() {
		return relatedStatus;
	}
	public void setRelatedStatus(String relatedStatus) {
		this.relatedStatus = relatedStatus;
	}
	
	
*/
	//Start :: Added for BG Cover Messages
	
	private String bgFormNumber;
	private String bgType;
	private BigDecimal bgAmount;
	private String bgCurrency;
	private Timestamp bgFromDate;
	private Timestamp bgToDate;
	private Timestamp bgEffectiveDate;
	private Timestamp bgLodgementEndDate;
	private String bgLodgementPalce;
	private String issuingBankCode;
	private String bgApplicentNameAndDetails;
	private String beneficiaryNameAndDetails;
	private String beneficiaryBankCode;
	private String bgIssuingBankAddrDetails;
	private String bgBeneficiaryBankAddr;
	private String bgPurpose;
	private String descriptionOfUnderlinedContract;
	private String eStampDutyPaid;
	private String eStampCertificateNumber;
	private Timestamp eStampDateAndTime;
	private BigDecimal bgAmountPaid;
	private String bgStateCode;
	private String bgArticleNumber;
	private Timestamp bgPaymentDate;
	private String bgPaymentPlace;
	private String eBgHeldDematForm;
	private String custodianServiceProvider;
	private String dematAccNumber;
	
	
	
	//End :: Added for BG Cover Messages
	//Start :: Added for 769 message enable
	private String lcAdditionalCurrCode;
	private BigDecimal lcAdditionalAmt;
	//End :: Added for 769 message enable
	//Start :: Added for Mizuho Bank
	private String lcChargesDeducted;
	private String lcChargesAdded;
	private Timestamp principalDate;
	private String principalCurrency;
	private BigDecimal princPrincipalAmt;
	private String sequence;
	private String sequenceofTotal;
	
	private String applicentIdentifier;
	private String availableIdentifier;

	private String reimbursingIdentifier;
	private String advisingIdentifier;
	private int noofProcInteration;
	private String uiMsgIdentifier32;
	private String uiMsgIdentifier34;
	private String descriptionofGoods1;
	private String descriptionofGoods2;
	private String messageCurrency;
	private BigDecimal lcChargesClaimed;
	private BigDecimal lcTotalAmountClaimed;
	private String beneficiaryInstitutionClrgCd;
	private String principalCurr;
	private BigDecimal principalAmount;
	//End :: Added for Mizuho Bank
	
	
	
	
	/**
	 * @return the uiMsgIdentifier32
	 */
	@Column(name="LC_UI_MSG_IDENTIFIER_32")
	public String getUiMsgIdentifier32() {
		return uiMsgIdentifier32;
	}

	/**
	 * @return the principalCurr
	 */
	@Column(name="MSGS_PRINCIPALCURR")
	public String getPrincipalCurr() {
		return principalCurr;
	}

	/**
	 * @param principalCurr the principalCurr to set
	 */
	public void setPrincipalCurr(String principalCurr) {
		this.principalCurr = principalCurr;
	}

	/**
	 * @return the principalAmount
	 */
	@Column(name="MSGS_PRINCIPALAMOUNT")
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
	 * @return the lcTotalAmountClaimed
	 */
	@Column(name="LC_ADDNLAMOUNT_CLAIMED")
	public BigDecimal getLcTotalAmountClaimed() {
		return lcTotalAmountClaimed;
	}

	/**
	 * @param lcTotalAmountClaimed the lcTotalAmountClaimed to set
	 */
	public void setLcTotalAmountClaimed(BigDecimal lcTotalAmountClaimed) {
		this.lcTotalAmountClaimed = lcTotalAmountClaimed;
	}

	/**
	 * @return the beneficiaryInstitutionClrgCd
	 */
	@Column(name="MSGS_BENFINST_BKCD")
	public String getBeneficiaryInstitutionClrgCd() {
		return beneficiaryInstitutionClrgCd;
	}

	/**
	 * @param beneficiaryInstitutionClrgCd the beneficiaryInstitutionClrgCd to set
	 */
	public void setBeneficiaryInstitutionClrgCd(String beneficiaryInstitutionClrgCd) {
		this.beneficiaryInstitutionClrgCd = beneficiaryInstitutionClrgCd;
	}

	/**
	 * @return the messageCurrency
	 */
	@Column(name="MSGS_MESSAGECURR")
	public String getMessageCurrency() {
		return messageCurrency;
	}

	/**
	 * @param messageCurrency the messageCurrency to set
	 */
	public void setMessageCurrency(String messageCurrency) {
		this.messageCurrency = messageCurrency;
	}

	/**
	 * @return the lcChargesClaimed
	 */
	@Column(name="MSGS_CHARGEAMT")
	public BigDecimal getLcChargesClaimed() {
		return lcChargesClaimed;
	}

	/**
	 * @param lcChargesClaimed the lcChargesClaimed to set
	 */
	public void setLcChargesClaimed(BigDecimal lcChargesClaimed) {
		this.lcChargesClaimed = lcChargesClaimed;
	}

	/**
	 * @return the descriptionofGoods1
	 */
	@Column(name="LC_DESCRIPTIONOFGOODS_1")
	public String getDescriptionofGoods1() {
		return descriptionofGoods1;
	}

	/**
	 * @param descriptionofGoods1 the descriptionofGoods1 to set
	 */
	public void setDescriptionofGoods1(String descriptionofGoods1) {
		this.descriptionofGoods1 = descriptionofGoods1;
	}

	/**
	 * @return the descriptionofGoods2
	 */
	@Column(name="LC_DESCRIPTIONOFGOODS_2")
	public String getDescriptionofGoods2() {
		return descriptionofGoods2;
	}

	/**
	 * @param descriptionofGoods2 the descriptionofGoods2 to set
	 */
	public void setDescriptionofGoods2(String descriptionofGoods2) {
		this.descriptionofGoods2 = descriptionofGoods2;
	}

	/**
	 * @param uiMsgIdentifier32 the uiMsgIdentifier32 to set
	 */
	public void setUiMsgIdentifier32(String uiMsgIdentifier32) {
		this.uiMsgIdentifier32 = uiMsgIdentifier32;
	}

	/**
	 * @return the uiMsgIdentifier34
	 */
	@Column(name="LC_UI_MSG_IDENTIFIER_34")
	public String getUiMsgIdentifier34() {
		return uiMsgIdentifier34;
	}

	/**
	 * @param uiMsgIdentifier34 the uiMsgIdentifier34 to set
	 */
	public void setUiMsgIdentifier34(String uiMsgIdentifier34) {
		this.uiMsgIdentifier34 = uiMsgIdentifier34;
	}
	
	/**
	 * @return the lcAdditionalCurrCode
	 */
	@Column(name="LC_ADDITIONAL_CURR_CODE")
	public String getLcAdditionalCurrCode() {
		return lcAdditionalCurrCode;
	}
	
	

	@Column(name="NOOF_PROC_ITERATION")
	public int getNoofProcInteration() {
		return noofProcInteration;
	}
	public void setNoofProcInteration(int noofProcInteration) {
		this.noofProcInteration = noofProcInteration;
	}
	@Column(name="SEQUENCE_NO")
	public String getSequence() {
		return sequence;
	}
	public void setSequence(String sequence) {
		this.sequence = sequence;
	}
	@Column(name="SEQUENCE_TOTAL")
	public String getSequenceofTotal() {
		return sequenceofTotal;
	}
	public void setSequenceofTotal(String sequenceofTotal) {
		this.sequenceofTotal = sequenceofTotal;
	}
	
	@Column(name="LC_APPLICENT_IDENTIFIER")
	public String getApplicentIdentifier() {
		return applicentIdentifier;
	}
	public void setApplicentIdentifier(String applicentIdentifier) {
		this.applicentIdentifier = applicentIdentifier;
	}
	
	@Column(name="LC_AVAILABLE_IDENTIFIER")
	public String getAvailableIdentifier() {
		return availableIdentifier;
	}
	public void setAvailableIdentifier(String availableIdentifier) {
		this.availableIdentifier = availableIdentifier;
	}
	@Column(name="LC_REIMBURSBANK_IDENTIFIER")
	public String getReimbursingIdentifier() {
		return reimbursingIdentifier;
	}
	public void setReimbursingIdentifier(String reimbursingIdentifier) {
		this.reimbursingIdentifier = reimbursingIdentifier;
	}
	@Column(name="LC_ADVISING_IDENTIFIER")
	public String getAdvisingIdentifier() {
		return advisingIdentifier;
	}
	public void setAdvisingIdentifier(String advisingIdentifier) {
		this.advisingIdentifier = advisingIdentifier;
	}
	/**
	 * @return the principalCurrency
	 */
	@Column(name="LC_PRIC_CURR")
	public String getPrincipalCurrency() {
		return principalCurrency;
	}
	/**
	 * @param principalCurrency the principalCurrency to set
	 */
	public void setPrincipalCurrency(String principalCurrency) {
		this.principalCurrency = principalCurrency;
	}
	/**
	 * @return the princPrincipalAmt
	 */
	@Column(name="LC_PRIC_AMOUNT")
	public BigDecimal getPrincPrincipalAmt() {
		return princPrincipalAmt;
	}
	/**
	 * @param princPrincipalAmt the princPrincipalAmt to set
	 */
	public void setPrincPrincipalAmt(BigDecimal princPrincipalAmt) {
		this.princPrincipalAmt = princPrincipalAmt;
	}
	/**
	 * @return the principalDate
	 */
	@Column(name="LC_PRIC_DATE")
	public Timestamp getPrincipalDate() {
		return principalDate;
	}
	/**
	 * @param principalDate the principalDate to set
	 */
	public void setPrincipalDate(Timestamp principalDate) {
		this.principalDate = principalDate;
	}
	/**
	 * @return the lcChargesDeducted
	 */
	@Column(name="LC_CHARGES_DEDUCTED")
	public String getLcChargesDeducted() {
		return lcChargesDeducted;
	}
	/**
	 * @param lcChargesDeducted the lcChargesDeducted to set
	 */
	public void setLcChargesDeducted(String lcChargesDeducted) {
		this.lcChargesDeducted = lcChargesDeducted;
	}
	/**
	 * @return the lcChargesAdded
	 */
	@Column(name="LC_CHARGES_ADDED")
	public String getLcChargesAdded() {
		return lcChargesAdded;
	}
	/**
	 * @param lcChargesAdded the lcChargesAdded to set
	 */
	public void setLcChargesAdded(String lcChargesAdded) {
		this.lcChargesAdded = lcChargesAdded;
	}
	/**
	 * @param lcAdditionalCurrCode the lcAdditionalCurrCode to set
	 */
	public void setLcAdditionalCurrCode(String lcAdditionalCurrCode) {
		this.lcAdditionalCurrCode = lcAdditionalCurrCode;
	}
	/**
	 * @return the lcAdditionalAmt
	 */
	@Column(name="LC_ADDITIONAL_AMT")
	public BigDecimal getLcAdditionalAmt() {
		return lcAdditionalAmt;
	}
	/**
	 * @param lcAdditionalAmt the lcAdditionalAmt to set
	 */
	public void setLcAdditionalAmt(BigDecimal lcAdditionalAmt) {
		this.lcAdditionalAmt = lcAdditionalAmt;
	}
	/**
	 * @return the bgFormNumber
	 */
	@Column(name="BG_FORM_NUMBER")
	public String getBgFormNumber() {
		return bgFormNumber;
	}

	/**
	 * @param bgFormNumber the bgFormNumber to set
	 */
	public void setBgFormNumber(String bgFormNumber) {
		this.bgFormNumber = bgFormNumber;
	}
	/**
	 * @return the bgType
	 */
	@Column(name="BG_TYPE")
	public String getBgType() {
		return bgType;
	}
	/**
	 * @param bgType the bgType to set
	 */
	public void setBgType(String bgType) {
		this.bgType = bgType;
	}
	/**
	 * @return the bgAmount
	 */
	@Column(name="BG_AMOUNT")
	public BigDecimal getBgAmount() {
		return bgAmount;
	}
	/**
	 * @param bgAmount the bgAmount to set
	 */
	public void setBgAmount(BigDecimal bgAmount) {
		this.bgAmount = bgAmount;
	}
	/**
	 * @return the bgCurrency
	 */
	@Column(name="BG_CURR_CODE")
	public String getBgCurrency() {
		return bgCurrency;
	}
	/**
	 * @param bgCurrency the bgCurrency to set
	 */
	public void setBgCurrency(String bgCurrency) {
		this.bgCurrency = bgCurrency;
	}
	/**
	 * @return the bgFromDate
	 */
	@Column(name="BG_GUARANTEE_FROM_DATE")
	public Timestamp getBgFromDate() {
		return bgFromDate;
	}
	/**
	 * @param bgFromDate the bgFromDate to set
	 */
	public void setBgFromDate(Timestamp bgFromDate) {
		this.bgFromDate = bgFromDate;
	}
	/**
	 * @return the bgToDate
	 */
	@Column(name="BG_GUARANTEE_TO_DATE")
	public Timestamp getBgToDate() {
		return bgToDate;
	}
	/**
	 * @param bgToDate the bgToDate to set
	 */
	public void setBgToDate(Timestamp bgToDate) {
		this.bgToDate = bgToDate;
	}
	/**
	 * @return the bgEffectiveDate
	 */
	@Column(name="BG_EFFECTIVE_DATE")
	public Timestamp getBgEffectiveDate() {
		return bgEffectiveDate;
	}
	/**
	 * @param bgEffectiveDate the bgEffectiveDate to set
	 */
	public void setBgEffectiveDate(Timestamp bgEffectiveDate) {
		this.bgEffectiveDate = bgEffectiveDate;
	}
	/**
	 * @return the bgLodgementEndDate
	 */
	@Column(name="BG_LODGEMENT_END_DATE")
	public Timestamp getBgLodgementEndDate() {
		return bgLodgementEndDate;
	}
	/**
	 * @param bgLodgementEndDate the bgLodgementEndDate to set
	 */
	public void setBgLodgementEndDate(Timestamp bgLodgementEndDate) {
		this.bgLodgementEndDate = bgLodgementEndDate;
	}
	/**
	 * @return the bgLodgementPalce
	 */
	@Column(name="BG_LODGEMENT_PLACE")
	public String getBgLodgementPalce() {
		return bgLodgementPalce;
	}
	/**
	 * @param bgLodgementPalce the bgLodgementPalce to set
	 */
	public void setBgLodgementPalce(String bgLodgementPalce) {
		this.bgLodgementPalce = bgLodgementPalce;
	}
	/**
	 * @return the issuingBankCode
	 */
	@Column(name="ISSUINGBANK_CODE")
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
	 * @return the bgApplicentNameAndDetails
	 */
	@Column(name="BG_APPLICENT_NAME")
	public String getBgApplicentNameAndDetails() {
		return bgApplicentNameAndDetails;
	}
	/**
	 * @param bgApplicentNameAndDetails the bgApplicentNameAndDetails to set
	 */
	public void setBgApplicentNameAndDetails(String bgApplicentNameAndDetails) {
		this.bgApplicentNameAndDetails = bgApplicentNameAndDetails;
	}
	/**
	 * @return the beneficiaryNameAndDetails
	 */
	@Column(name="BG_BENIFICIARY_NAME")
	public String getBeneficiaryNameAndDetails() {
		return beneficiaryNameAndDetails;
	}
	/**
	 * @param beneficiaryNameAndDetails the beneficiaryNameAndDetails to set
	 */
	public void setBeneficiaryNameAndDetails(String beneficiaryNameAndDetails) {
		this.beneficiaryNameAndDetails = beneficiaryNameAndDetails;
	}
	/**
	 * @return the beneficiaryBankCode
	 */
	@Column(name="BENIFICIARY_CODE")
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
	 * @return the bgIssuingBankAddrDetails
	 */
	@Column(name="ISSUINGBANK_ADDR")
	public String getBgIssuingBankAddrDetails() {
		return bgIssuingBankAddrDetails;
	}
	/**
	 * @param bgIssuingBankAddrDetails the bgIssuingBankAddrDetails to set
	 */
	public void setBgIssuingBankAddrDetails(String bgIssuingBankAddrDetails) {
		this.bgIssuingBankAddrDetails = bgIssuingBankAddrDetails;
	}
	/**
	 * @return the bgBeneficiaryBankAddr
	 */
	@Column(name="BENIFICIARY_ADDR")
	public String getBgBeneficiaryBankAddr() {
		return bgBeneficiaryBankAddr;
	}
	/**
	 * @param bgBeneficiaryBankAddr the bgBeneficiaryBankAddr to set
	 */
	public void setBgBeneficiaryBankAddr(String bgBeneficiaryBankAddr) {
		this.bgBeneficiaryBankAddr = bgBeneficiaryBankAddr;
	}
	/**
	 * @return the bgPurpose
	 */
	@Column(name="BG_GUARANTEE_PURPOSE")
	public String getBgPurpose() {
		return bgPurpose;
	}
	/**
	 * @param bgPurpose the bgPurpose to set
	 */
	public void setBgPurpose(String bgPurpose) {
		this.bgPurpose = bgPurpose;
	}
	/**
	 * @return the descriptionOfUnderlinedContract
	 */
	@Column(name="BG_CONTRACT_REFERENCE")
	public String getDescriptionOfUnderlinedContract() {
		return descriptionOfUnderlinedContract;
	}
	/**
	 * @param descriptionOfUnderlinedContract the descriptionOfUnderlinedContract to set
	 */
	public void setDescriptionOfUnderlinedContract(
			String descriptionOfUnderlinedContract) {
		this.descriptionOfUnderlinedContract = descriptionOfUnderlinedContract;
	}
	/**
	 * @return the eStampDutyPaid
	 */
	@Column(name="BG_STAMP_DUTY_PAID")
	public String geteStampDutyPaid() {
		return eStampDutyPaid;
	}
	/**
	 * @param eStampDutyPaid the eStampDutyPaid to set
	 */
	public void seteStampDutyPaid(String eStampDutyPaid) {
		this.eStampDutyPaid = eStampDutyPaid;
	}
	/**
	 * @return the eStampCertificateNumber
	 */
	@Column(name="BG_STAMP_CERTIFICATE_NUM")
	public String geteStampCertificateNumber() {
		return eStampCertificateNumber;
	}
	/**
	 * @param eStampCertificateNumber the eStampCertificateNumber to set
	 */
	public void seteStampCertificateNumber(String eStampCertificateNumber) {
		this.eStampCertificateNumber = eStampCertificateNumber;
	}
	/**
	 * @return the eStampDateAndTime
	 */
	@Column(name="BG_STAMP_CERT_DATE_TIME")
	public Timestamp geteStampDateAndTime() {
		return eStampDateAndTime;
	}
	/**
	 * @param eStampDateAndTime the eStampDateAndTime to set
	 */
	public void seteStampDateAndTime(Timestamp eStampDateAndTime) {
		this.eStampDateAndTime = eStampDateAndTime;
	}
	/**
	 * @return the bgAmountPaid
	 */
	@Column(name="BG_AMOUNT_PAID")
	public BigDecimal getBgAmountPaid() {
		return bgAmountPaid;
	}
	/**
	 * @param bgAmountPaid the bgAmountPaid to set
	 */
	public void setBgAmountPaid(BigDecimal bgAmountPaid) {
		this.bgAmountPaid = bgAmountPaid;
	}
	/**
	 * @return the bgStateCode
	 */
	@Column(name="BG_STATE_CODE")
	public String getBgStateCode() {
		return bgStateCode;
	}
	/**
	 * @param bgStateCode the bgStateCode to set
	 */
	public void setBgStateCode(String bgStateCode) {
		this.bgStateCode = bgStateCode;
	}
	/**
	 * @return the bgArticleNumber
	 */
	@Column(name="BG_ARTICLE_NUM")
	public String getBgArticleNumber() {
		return bgArticleNumber;
	}
	/**
	 * @param bgArticleNumber the bgArticleNumber to set
	 */
	public void setBgArticleNumber(String bgArticleNumber) {
		this.bgArticleNumber = bgArticleNumber;
	}
	/**
	 * @return the bgPaymentDate
	 */
	@Column(name="BG_PAYMENT_DATE")
	public Timestamp getBgPaymentDate() {
		return bgPaymentDate;
	}
	/**
	 * @param bgPaymentDate the bgPaymentDate to set
	 */
	public void setBgPaymentDate(Timestamp bgPaymentDate) {
		this.bgPaymentDate = bgPaymentDate;
	}
	/**
	 * @return the bgPaymentPlace
	 */
	@Column(name="BG_PAYMENT_PLACE")
	public String getBgPaymentPlace() {
		return bgPaymentPlace;
	}
	/**
	 * @param bgPaymentPlace the bgPaymentPlace to set
	 */
	public void setBgPaymentPlace(String bgPaymentPlace) {
		this.bgPaymentPlace = bgPaymentPlace;
	}
	/**
	 * @return the eBgHeldDematForm
	 */
	@Column(name="BG_DEMAT_FORM")
	public String geteBgHeldDematForm() {
		return eBgHeldDematForm;
	}
	/**
	 * @param eBgHeldDematForm the eBgHeldDematForm to set
	 */
	public void seteBgHeldDematForm(String eBgHeldDematForm) {
		this.eBgHeldDematForm = eBgHeldDematForm;
	}
	/**
	 * @return the custodianServiceProvider
	 */
	@Column(name="BG_COSTODIAN_PROVIDER")
	public String getCustodianServiceProvider() {
		return custodianServiceProvider;
	}
	/**
	 * @param custodianServiceProvider the custodianServiceProvider to set
	 */
	public void setCustodianServiceProvider(String custodianServiceProvider) {
		this.custodianServiceProvider = custodianServiceProvider;
	}
	/**
	 * @return the dematAccNumber
	 */
	@Column(name="BG_DEMAT_ACC_NUM")
	public String getDematAccNumber() {
		return dematAccNumber;
	}
	/**
	 * @param dematAccNumber the dematAccNumber to set
	 */
	public void setDematAccNumber(String dematAccNumber) {
		this.dematAccNumber = dematAccNumber;
	}
	@Column(name="MSGS_REL_STATUS")
	public String getMsgRelStatus() {
		return msgRelStatus;
	}
	public void setMsgRelStatus(String msgRelStatus) {
		this.msgRelStatus = msgRelStatus;
	}


	//Party to which an amount of money is due
	//@Column MSGS_CDTR
	//private String beneficiaryCustomer;
	private String beneficiaryCustAcct;
	
	//Name of the party (person or company) to which an amount of money is due
	//@MSGS_CDTR_NM
	private String beneficiaryCustomerName;
	//@MSGS_CDTR_CTCTDTLS
	private String beneficiaryCustomerCtctDtls;
	//MSGS_CDTRAGT_ID
	private String accountWithInstitutionId;
	//MSGS_CDTRAGT_CLRCODE
	private String accountWithInstitutionAccount;
	@Column(name="MSGS_CDTRAGT_CLRCODE")
	public String getAccountWithInstitutionAccount() {
		return accountWithInstitutionAccount;
	}
	public void setAccountWithInstitutionAccount(String accountWithInstitutionAccount) {
		this.accountWithInstitutionAccount = accountWithInstitutionAccount;
	}
	@Column(name="MSGS_CDTRAGT_ID")
	public String getAccountWithInstitutionId() {
		return accountWithInstitutionId;
	}
	public void setAccountWithInstitutionId(String accountWithInstitutionId) {
		this.accountWithInstitutionId = accountWithInstitutionId;
	}
	/**
	 * @return the beneficiaryCustomerCtctDtls
	 */
	@Column(name="MSGS_CDTR_CTCTDTLS")
	public String getBeneficiaryCustomerCtctDtls() {
		return beneficiaryCustomerCtctDtls;
	}
	/**
	 * @param beneficiaryCustomerCtctDtls the beneficiaryCustomerCtctDtls to set
	 */
	public void setBeneficiaryCustomerCtctDtls(String beneficiaryCustomerCtctDtls) {
		this.beneficiaryCustomerCtctDtls = beneficiaryCustomerCtctDtls;
	}


	//Postal address of the party (person or company) to which an amount of money is due
	//@MSGS_CDTR_PSTLADDR
	private String beneficiaryCustomerAddress;
	
	
	//ID of the party (person or company) to which an amount of money is due
	//@MSGS_CDTR_ID
	private String beneficiaryCustomerID;
	
	//Country of Residence of the party (person or company) to which an amount of money is due
	//@MSGS_CDTR_CTRYOFRES
	private String beneficiaryCustomerCtry;
	
	
	//Name of the ultimate party (person or company) to which an amount of money is due
	//@MSGS_ULTMTCDTR_NM
	private String ultimateCreditorName;
	//MSGS_CDTRAGT_LOC
	private String accountWithInstitutionLoc;
	//MSGS_CDTRAGT_NM
	private String accountWithInstitutionName;
	//MSGS_DBTRAGT_ID
	private String issuingBankPID;
	//MSGS_DBTRAGT_NAME
	private String issunigBankNameAndAddress;
	//LC_NONBANK_ISSUER
	private String nonIssuingBank;
	
	
	@Column(name="MSGS_DBTRAGT_ID")
	public String getIssuingBankPID() {
		return issuingBankPID;
	}
	public void setIssuingBankPID(String issuingBankPID) {
		this.issuingBankPID = issuingBankPID;
	}
	@Column(name="MSGS_DBTRAGT_NAME")
	public String getIssunigBankNameAndAddress() {
		return issunigBankNameAndAddress;
	}
	public void setIssunigBankNameAndAddress(String issunigBankNameAndAddress) {
		this.issunigBankNameAndAddress = issunigBankNameAndAddress;
	}
	@Column(name="LC_NONBANK_ISSUER")
	public String getNonIssuingBank() {
		return nonIssuingBank;
	}
	public void setNonIssuingBank(String nonIssuingBank) {
		this.nonIssuingBank = nonIssuingBank;
	}
	@Column(name="MSGS_CDTRAGT_NM")
	public String getAccountWithInstitutionName() {
		return accountWithInstitutionName;
	}
	public void setAccountWithInstitutionName(String accountWithInstitutionName) {
		this.accountWithInstitutionName = accountWithInstitutionName;
	}
	@Column(name="MSGS_CDTRAGT_LOC")
	public String getAccountWithInstitutionLoc() {
		return accountWithInstitutionLoc;
	}
	public void setAccountWithInstitutionLoc(String accountWithInstitutionLoc) {
		this.accountWithInstitutionLoc = accountWithInstitutionLoc;
	}
	/**
	 * @return the ultimateCreditorCtry
	 */
	@Column(name="MSGS_ULTMTCDTR_CTRYOFRES")
	public String getUltimateCreditorCtry() {
		return ultimateCreditorCtry;
	}
	/**
	 * @param ultimateCreditorCtry the ultimateCreditorCtry to set
	 */
	public void setUltimateCreditorCtry(String ultimateCreditorCtry) {
		this.ultimateCreditorCtry = ultimateCreditorCtry;
	}
	/**
	 * @return the ultimateCreditorCtctDtls
	 */
	@Column(name="MSGS_ULTMTCDTR_CTCTDTLS")
	public String getUltimateCreditorCtctDtls() {
		return ultimateCreditorCtctDtls;
	}
	/**
	 * @param ultimateCreditorCtctDtls the ultimateCreditorCtctDtls to set
	 */
	public void setUltimateCreditorCtctDtls(String ultimateCreditorCtctDtls) {
		this.ultimateCreditorCtctDtls = ultimateCreditorCtctDtls;
	}


	//Postal Address of the ultimate party (person or company) to which an amount of money is due
	//@MSGS_ULTMTCDTR_PSTLADDR
	private String ultimateCreditorAddress;
	
	//ID of the ultimate party (person or company) to which an amount of money is due
	//@MSGS_ULTMTCDTR_ID
	private String ultimateCreditorID;
	
	//MSGS_ULTMTCDTR_CTRYOFRES
	private String ultimateCreditorCtry;
	//MSGS_ULTMTCDTR_CTCTDTLS
	private String ultimateCreditorCtctDtls;
	
	// who modified the messages last 
	//MSGS_MODIFIED_USER
	private String lastModifiedUser;
	// comments added when the user do the payment transaction action for ex(repair ,deletion etc)
	//@Column MSGS_COMMENTS
	private String comments;
	// to identify all payments has been completed
	//@Column MSGS_STS_ISFINAL
	
	
	private String msgTxnType;
	
	private String lcType;
	private String lcNo;
	private String lcPrevAdvRef;
	private Timestamp lcIssueDt;
	private Timestamp lcExpDt;
	private String lcExpPlace;
	//LC_POS_TOLERANCE
	private int lcPositiveTolerance;
	@Column(name="LC_POS_TOLERANCE")
	public int getLcPositiveTolerance() {
		return lcPositiveTolerance;
	}
	public void setLcPositiveTolerance(int lcPositiveTolerance) {
		this.lcPositiveTolerance = lcPositiveTolerance;
	}
	@Column(name="LC_NEG_TOLERANCE")
	public int getLcNegativeTolerance() {
		return lcNegativeTolerance;
	}
	public void setLcNegativeTolerance(int lcNegativeTolerance) {
		this.lcNegativeTolerance = lcNegativeTolerance;
	}


	//LC_NEG_TOLERANCE
	private int lcNegativeTolerance;
	private String lcMaxCrAmt;
	private String lcAddlAmts;
	private String lcAuthBankCode;
	private String lcAuthBankAddr;
	private String lcAuthMode;
	private String lcDispatchPlace;
	private String lcDstn;
	private Timestamp lcLstShipDt;
	private String lcShipPeriod;
	private String lcShipTerms;
	private String lcDraftsAt;
	private String lcDraweeBnkPid;
	private String lcDraweeBnkCode;
	private String lcDraweeBnkAddr;
	private String lcMixedPymtDet;
	private String lcDefPymtDet;
	private String lcPartialShipment;
	private String lcTransShipment;
	private String lcDocsReq1;
	private String lcDocsReq2;
	private String lcAddnlCndt1;
	private String lcAddnlCndt2;
	private String lcCharges;
	private String lcPrsntnPrd;
	private String lcConfrmInstrns;
	private String lcInstrnTopay;
	private String lcNarrative;
	private BigDecimal lcAmndmntNo;
	private Timestamp lcAmndmntDt;
	private Timestamp lcOldExpDt;
	private BigDecimal lcAmndmntIncAmt;
	private BigDecimal lcAmndmntDecAmt;
	private BigDecimal lcAmndmntOldAmt;
	private String lcAccId;
	private Timestamp lcAckDt;
	private BigDecimal lcChgsClaimed;
	private BigDecimal lcToAmtClaimed;
	private BigDecimal lcTotalAmtClaimed;
	private BigDecimal lcNetAmtClaimed;
	private BigDecimal lcAmtPaid;
	private String lcDiscrepancies;
	private String lcDispoDocs;
	private String lcAppRulesCode;
	private String lcAppRulesDesc;
	private String senderCorrespondentId;
	private String seqNo;

	
	@Column(name="MSGS_SEQNO")
	public String getSeqNo() {
		return seqNo;
	}
	public void setSeqNo(String seqNo) {
		this.seqNo = seqNo;
	}


	//MSGS_SENDING_INSTID
	private String sendingInstId;
	//MSGS_SENDING_INSTCODE
	private String sendingInst;
	private String sendingInstNameAddress;
	
	@Column(name="MSGS_SENDING_INSTNAMEADD")
	public String getSendingInstNameAddress() {
		return sendingInstNameAddress;
	}
	public void setSendingInstNameAddress(String sendingInstNameAddress) {
		this.sendingInstNameAddress = sendingInstNameAddress;
	}


	private String sendingInstNameAdd;
	private String initialDispatchPlace;
	private String finalDeliveryPlace;
	@Column(name="LC_DEPARTURE_PLACE")
	public String getInitialDispatchPlace() {
		return initialDispatchPlace;
	}
	public void setInitialDispatchPlace(String initialDispatchPlace) {
		this.initialDispatchPlace = initialDispatchPlace;
	}
	@Column(name="LC_FINAL_DESTINATION")
	public String getFinalDeliveryPlace() {
		return finalDeliveryPlace;
	}
	public void setFinalDeliveryPlace(String finalDeliveryPlace) {
		this.finalDeliveryPlace = finalDeliveryPlace;
	}


	//MSGS_SNDRAGT_NAME
	private String senderCorrespondentName;
	
	@Column(name="MSGS_SNDRAGT_NAME")
	public String getSenderCorrespondentName() {
		return senderCorrespondentName;
	}
	public void setSenderCorrespondentName(String senderCorrespondentName) {
		this.senderCorrespondentName = senderCorrespondentName;
	}
	@Column(name="MSGS_SENDING_INSTID")
	public String getSendingInstId() {
		return sendingInstId;
	}
	public void setSendingInstId(String sendingInstId) {
		this.sendingInstId = sendingInstId;
	}
	@Column(name="MSGS_SENDING_INSTCODE")
	public String getSendingInst() {
		return sendingInst;
	}
	public void setSendingInst(String sendingInst) {
		this.sendingInst = sendingInst;
	}
	@Column(name="MSGS_SENDING_INSTAC")
	public String getSendingInstNameAdd() {
		return sendingInstNameAdd;
	}
	public void setSendingInstNameAdd(String sendingInstNameAdd) {
		this.sendingInstNameAdd = sendingInstNameAdd;
	}
	@Column(name="MSGS_SNDRAGT_ID")
	public String getSenderCorrespondentId() {
		return senderCorrespondentId;
	}
	public void setSenderCorrespondentId(String senderCorrespondentId) {
		this.senderCorrespondentId = senderCorrespondentId;
	}
	@Column(name="LC_TYPE")
	public String getLcType() {
		return lcType;
	}
	public void setLcType(String lcType) {
		this.lcType = lcType;
	}
	@Column(name="LC_NUMBER")
	public String getLcNo() {
		return lcNo;
	}
	public void setLcNo(String lcNo) {
		this.lcNo = lcNo;
	}
	@Column(name="LC_PRE_ADV_REF")
	public String getLcPrevAdvRef() {
		return lcPrevAdvRef;
	}
	public void setLcPrevAdvRef(String lcPrevAdvRef) {
		this.lcPrevAdvRef = lcPrevAdvRef;
	}
	@Column(name="LC_ISSUE_DT")
	public Timestamp getLcIssueDt() {
		return lcIssueDt;
	}
	public void setLcIssueDt(Timestamp lcIssueDt) {
		this.lcIssueDt = lcIssueDt;
	}
	@Column(name="LC_EXP_DATE")
	public Timestamp getLcExpDt() {
		return lcExpDt;
	}
	public void setLcExpDt(Timestamp lcExpDt) {
		this.lcExpDt = lcExpDt;
	}
	@Column(name="LC_EXP_PLACE")
	public String getLcExpPlace() {
		return lcExpPlace;
	}
	public void setLcExpPlace(String lcExpPlace) {
		this.lcExpPlace = lcExpPlace;
	}
	
	@Column(name="LC_MAX_CRAMT")
	public String getLcMaxCrAmt() {
		return lcMaxCrAmt;
	}
	public void setLcMaxCrAmt(String lcMaxCrAmt) {
		this.lcMaxCrAmt = lcMaxCrAmt;
	}
	@Column(name="LC_ADDNL_AMTS")
	public String getLcAddlAmts() {
		return lcAddlAmts;
	}
	public void setLcAddlAmts(String lcAddlAmts) {
		this.lcAddlAmts = lcAddlAmts;
	}
	@Column(name="LC_AUTHBANK_CODE")
	public String getLcAuthBankCode() {
		return lcAuthBankCode;
	}
	public void setLcAuthBankCode(String lcAuthBankCode) {
		this.lcAuthBankCode = lcAuthBankCode;
	}
	@Column(name="LC_AUTHBANK_ADDR")
	public String getLcAuthBankAddr() {
		return lcAuthBankAddr;
	}
	public void setLcAuthBankAddr(String lcAuthBankAddr) {
		this.lcAuthBankAddr = lcAuthBankAddr;
	}
	@Column(name="LC_AUTH_MODE")
	public String getLcAuthMode() {
		return lcAuthMode;
	}
	public void setLcAuthMode(String lcAuthMode) {
		this.lcAuthMode = lcAuthMode;
	}
	@Column(name="LC_DISPATCH_PLACE")
	public String getLcDispatchPlace() {
		return lcDispatchPlace;
	}
	public void setLcDispatchPlace(String lcDispatchPlace) {
		this.lcDispatchPlace = lcDispatchPlace;
	}
	@Column(name="LC_DESTINATION")
	public String getLcDstn() {
		return lcDstn;
	}
	public void setLcDstn(String lcDstn) {
		this.lcDstn = lcDstn;
	}
	@Column(name="LC_LAST_SHIPDT")
	public Timestamp getLcLstShipDt() {
		return lcLstShipDt;
	}
	public void setLcLstShipDt(Timestamp lcLstShipDt) {
		this.lcLstShipDt = lcLstShipDt;
	}
	@Column(name="LC_SHIP_PRD")
	public String getLcShipPeriod() {
		return lcShipPeriod;
	}
	public void setLcShipPeriod(String lcShipPeriod) {
		this.lcShipPeriod = lcShipPeriod;
	}
	@Column(name="LC_SHIP_TERMS")
	public String getLcShipTerms() {
		return lcShipTerms;
	}
	public void setLcShipTerms(String lcShipTerms) {
		this.lcShipTerms = lcShipTerms;
	}
	@Column(name="LC_DRAFTS_AT")
	public String getLcDraftsAt() {
		return lcDraftsAt;
	}
	public void setLcDraftsAt(String lcDraftsAt) {
		this.lcDraftsAt = lcDraftsAt;
	}
	@Column(name="LC_DRAWEEBANK_PID")
	public String getLcDraweeBnkPid() {
		return lcDraweeBnkPid;
	}
	public void setLcDraweeBnkPid(String lcDraweeBnkPid) {
		this.lcDraweeBnkPid = lcDraweeBnkPid;
	}
	@Column(name="LC_DRAWEEBANK_CODE")
	public String getLcDraweeBnkCode() {
		return lcDraweeBnkCode;
	}
	public void setLcDraweeBnkCode(String lcDraweeBnkCode) {
		this.lcDraweeBnkCode = lcDraweeBnkCode;
	}
	@Column(name="LC_DRAWEEBANK_ADDR")
	public String getLcDraweeBnkAddr() {
		return lcDraweeBnkAddr;
	}
	public void setLcDraweeBnkAddr(String lcDraweeBnkAddr) {
		this.lcDraweeBnkAddr = lcDraweeBnkAddr;
	}
	@Column(name="LC_MIXED_PYMT_DET")
	public String getLcMixedPymtDet() {
		return lcMixedPymtDet;
	}
	public void setLcMixedPymtDet(String lcMixedPymtDet) {
		this.lcMixedPymtDet = lcMixedPymtDet;
	}
	@Column(name="LC_DEF_PYMT_DET")
	public String getLcDefPymtDet() {
		return lcDefPymtDet;
	}
	public void setLcDefPymtDet(String lcDefPymtDet) {
		this.lcDefPymtDet = lcDefPymtDet;
	}
	@Column(name="LC_PARTIAL_SHIPMENT")
	public String getLcPartialShipment() {
		return lcPartialShipment;
	}
	public void setLcPartialShipment(String lcPartialShipment) {
		this.lcPartialShipment = lcPartialShipment;
	}
	@Column(name="LC_TRANS_SHIPMENT")
	public String getLcTransShipment() {
		return lcTransShipment;
	}
	public void setLcTransShipment(String lcTransShipment) {
		this.lcTransShipment = lcTransShipment;
	}
	@Column(name="LC_DOCS_REQD_1")
	public String getLcDocsReq1() {
		return lcDocsReq1;
	}
	public void setLcDocsReq1(String lcDocsReq1) {
		this.lcDocsReq1 = lcDocsReq1;
	}
	@Column(name="LC_DOCS_REQD_2")
	public String getLcDocsReq2() {
		return lcDocsReq2;
	}
	public void setLcDocsReq2(String lcDocsReq2) {
		this.lcDocsReq2 = lcDocsReq2;
	}
	@Column(name="LC_ADDNL_CONDITIONS_1")
	public String getLcAddnlCndt1() {
		return lcAddnlCndt1;
	}
	public void setLcAddnlCndt1(String lcAddnlCndt1) {
		this.lcAddnlCndt1 = lcAddnlCndt1;
	}
	@Column(name="LC_ADDNL_CONDITIONS_2")
	public String getLcAddnlCndt2() {
		return lcAddnlCndt2;
	}
	public void setLcAddnlCndt2(String lcAddnlCndt2) {
		this.lcAddnlCndt2 = lcAddnlCndt2;
	}
	@Column(name="LC_CHARGES")
	public String getLcCharges() {
		return lcCharges;
	}
	public void setLcCharges(String lcCharges) {
		this.lcCharges = lcCharges;
	}
	@Column(name="LC_PRSNTN_PRD")
	public String getLcPrsntnPrd() {
		return lcPrsntnPrd;
	}
	public void setLcPrsntnPrd(String lcPrsntnPrd) {
		this.lcPrsntnPrd = lcPrsntnPrd;
	}
	@Column(name="LC_CONFRM_INSTRNS")
	public String getLcConfrmInstrns() {
		return lcConfrmInstrns;
	}
	public void setLcConfrmInstrns(String lcConfrmInstrns) {
		this.lcConfrmInstrns = lcConfrmInstrns;
	}
	@Column(name="LC_INSTRNS_TOPAY")
	public String getLcInstrnTopay() {
		return lcInstrnTopay;
	}
	public void setLcInstrnTopay(String lcInstrnTopay) {
		this.lcInstrnTopay = lcInstrnTopay;
	}
	@Column(name="LC_NARRATIVE")
	public String getLcNarrative() {
		return lcNarrative;
	}
	public void setLcNarrative(String lcNarrative) {
		this.lcNarrative = lcNarrative;
	}
	@Column(name="LC_AMNDMNT_NO")
	public BigDecimal getLcAmndmntNo() {
		return lcAmndmntNo;
	}
	public void setLcAmndmntNo(BigDecimal lcAmndmntNo) {
		this.lcAmndmntNo = lcAmndmntNo;
	}
	@Column(name="LC_AMNDMNT_DATE")
	public Timestamp getLcAmndmntDt() {
		return lcAmndmntDt;
	}
	public void setLcAmndmntDt(Timestamp lcAmndmntDt) {
		this.lcAmndmntDt = lcAmndmntDt;
	}
	@Column(name="LC_OLD_EXP_DATE")
	public Timestamp getLcOldExpDt() {
		return lcOldExpDt;
	}
	public void setLcOldExpDt(Timestamp lcOldExpDt) {
		this.lcOldExpDt = lcOldExpDt;
	}
	@Column(name="LC_AMNDMNT_INCAMT")
	public BigDecimal getLcAmndmntIncAmt() {
		return lcAmndmntIncAmt;
	}
	public void setLcAmndmntIncAmt(BigDecimal lcAmndmntIncAmt) {
		this.lcAmndmntIncAmt = lcAmndmntIncAmt;
	}
	@Column(name="LC_AMNDMNT_DECAMT")
	public BigDecimal getLcAmndmntDecAmt() {
		return lcAmndmntDecAmt;
	}
	public void setLcAmndmntDecAmt(BigDecimal lcAmndmntDecAmt) {
		this.lcAmndmntDecAmt = lcAmndmntDecAmt;
	}
	@Column(name="LC_AMNDMNT_OLDAMT")
	public BigDecimal getLcAmndmntOldAmt() {
		return lcAmndmntOldAmt;
	}
	public void setLcAmndmntOldAmt(BigDecimal lcAmndmntOldAmt) {
		this.lcAmndmntOldAmt = lcAmndmntOldAmt;
	}
	@Column(name="LC_ACC_ID")
	public String getLcAccId() {
		return lcAccId;
	}
	public void setLcAccId(String lcAccId) {
		this.lcAccId = lcAccId;
	}
	@Column(name="LC_ACK_DATE")
	public Timestamp getLcAckDt() {
		return lcAckDt;
	}
	public void setLcAckDt(Timestamp lcAckDt) {
		this.lcAckDt = lcAckDt;
	}
	@Column(name="LC_CHGS_CLAIMED")
	public BigDecimal getLcChgsClaimed() {
		return lcChgsClaimed;
	}
	public void setLcChgsClaimed(BigDecimal lcChgsClaimed) {
		this.lcChgsClaimed = lcChgsClaimed;
	}
	@Column(name="LC_TOTAMT_CLAIMED")
	public BigDecimal getLcToAmtClaimed() {
		return lcToAmtClaimed;
	}
	public void setLcToAmtClaimed(BigDecimal lcToAmtClaimed) {
		this.lcToAmtClaimed = lcToAmtClaimed;
	}
	@Column(name="LC_ADDNLAMT_CLAIMED")
	public BigDecimal getLcTotalAmtClaimed() {
		return lcTotalAmtClaimed;
	}
	public void setLcTotalAmtClaimed(BigDecimal lcTotalAmtClaimed) {
		this.lcTotalAmtClaimed = lcTotalAmtClaimed;
	}
	@Column(name="LC_NETAMT_CLAIMED")
	public BigDecimal getLcNetAmtClaimed() {
		return lcNetAmtClaimed;
	}
	public void setLcNetAmtClaimed(BigDecimal lcNetAmtClaimed) {
		this.lcNetAmtClaimed = lcNetAmtClaimed;
	}
	@Column(name="LC_AMT_PAID")
	public BigDecimal getLcAmtPaid() {
		return lcAmtPaid;
	}
	public void setLcAmtPaid(BigDecimal lcAmtPaid) {
		this.lcAmtPaid = lcAmtPaid;
	}
	@Column(name="LC_DISCREPANCIES")
	public String getLcDiscrepancies() {
		return lcDiscrepancies;
	}
	public void setLcDiscrepancies(String lcDiscrepancies) {
		this.lcDiscrepancies = lcDiscrepancies;
	}
	@Column(name="LC_DISPO_DOCS")
	public String getLcDispoDocs() {
		return lcDispoDocs;
	}
	public void setLcDispoDocs(String lcDispoDocs) {
		this.lcDispoDocs = lcDispoDocs;
	}
	@Column(name="LC_APPLICABLE_RULES_CODE")
	public String getLcAppRulesCode() {
		return lcAppRulesCode;
	}
	public void setLcAppRulesCode(String lcAppRulesCode) {
		this.lcAppRulesCode = lcAppRulesCode;
	}
	@Column(name="LC_APPLICABLE_RULES_DESC")
	public String getLcAppRulesDesc() {
		return lcAppRulesDesc;
	}
	public void setLcAppRulesDesc(String lcAppRulesDesc) {
		this.lcAppRulesDesc = lcAppRulesDesc;
	}
	
	
	@Column(name = "MSGS_TXNTYPE")
	public String getMsgTxnType() {
		return msgTxnType;
	}
	public void setMsgTxnType(String msgTxnType) {
		this.msgTxnType = msgTxnType;
	}
	@Column(name="MSGS_MODIFIED_USER")
	public String getLastModifiedUser() {
		return lastModifiedUser;
	}
	public void setLastModifiedUser(String lastModifiedUser) {
		this.lastModifiedUser = lastModifiedUser;
	}
	@Column(name="MSGS_COMMENTS")
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	@Column(name="MSGS_ULTMTCDTR_ID")
	public String getUltimateCreditorID() {
		return ultimateCreditorID;
	}
	public void setUltimateCreditorID(String ultimateCreditorID) {
		this.ultimateCreditorID = ultimateCreditorID;
	}
	@Column(name="MSGS_ULTMTCDTR_PSTLADDR")
	public String getUltimateCreditorAddress() {
		return ultimateCreditorAddress;
	}
	public void setUltimateCreditorAddress(String ultimateCreditorAddress) {
		this.ultimateCreditorAddress = ultimateCreditorAddress;
	}
	@Column(name="MSGS_ULTMTCDTR_NM")
	public String getUltimateCreditorName() {
		return ultimateCreditorName;
	}
	public void setUltimateCreditorName(String ultimateCreditorName) {
		this.ultimateCreditorName = ultimateCreditorName;
	}
	@Column(name="MSGS_CDTR_CTRYOFRES")
	public String getBeneficiaryCustomerCtry() {
		return beneficiaryCustomerCtry;
	}
	public void setBeneficiaryCustomerCtry(String beneficiaryCustomerCtry) {
		this.beneficiaryCustomerCtry = beneficiaryCustomerCtry;
	}
	@Column(name="MSGS_CDTR_ID")
	public String getBeneficiaryCustomerID() {
		return beneficiaryCustomerID;
	}
	public void setBeneficiaryCustomerID(String beneficiaryCustomerID) {
		this.beneficiaryCustomerID = beneficiaryCustomerID;
	}
	@Column(name="MSGS_CDTR_PSTLADDR")
	public String getBeneficiaryCustomerAddress() {
		return beneficiaryCustomerAddress;
	}
	public void setBeneficiaryCustomerAddress(String beneficiaryCustomerAddress) {
		this.beneficiaryCustomerAddress = beneficiaryCustomerAddress;
	}
	
	
	@Column(name="MSGS_CDTR_NM")
	public String getBeneficiaryCustomerName() {
		return beneficiaryCustomerName;
	}
	public void setBeneficiaryCustomerName(String beneficiaryCustomerName) {
		this.beneficiaryCustomerName = beneficiaryCustomerName;
	}
	@Column(name="MSGS_CDTRACCT")
	public String getBeneficiaryCustAcct() {
		return beneficiaryCustAcct;
	}
	public void setBeneficiaryCustAcct(String beneficiaryCustAcct) {
		this.beneficiaryCustAcct = beneficiaryCustAcct;
	}


	//private String ultimateCreditor;
	//Coded information related to the processing of the payment instruction, provided by the initiating party, and intended for the creditor's agent.
	//@Column MSGS_INSTRFORCDTRAGT_CD
	private String instructionsForCrdtrAgtCode;
	//Further information complementing the coded instruction or instruction to the creditor's agent that is bilaterally agreed or specific to a user community.
	//@Column MSGS_INSTRFORCDTRAGT_INSTRINF
	private String instructionsForCrdtrAgtText;
	//Coded information related to the processing of the payment instruction, provided by the initiating party, and intended for the next agent in the payment chain.
	//@Column MSGS_INSTRFORNXTAGT_CD
	private String instructionsForNextAgtCode;
	//Further information complementing the coded instruction or instruction to the next agent that is bilaterally agreed or specific to a user community.
	//@Column MSGS_INSTRFORNXTAGT_INSTRINF
	private String instructionsForNextAgtText;
	//Underlying reason for the payment transaction, as published in an external purpose code list
	//@Column MSGS_PURP_CD
	private String msgPurposeCode;
	//Purpose, in a proprietary form
	//@Column MSGS_PURP_PRTRY
	private String msgPurposeText;
	/**
	 * @return the msgErrorCode
	 */
	//@Column(name="MSGS_ERRORCODE")
	/*public String getMsgErrorCode() {
		return msgErrorCode;
	}
	*//**
	 * @param msgErrorCode the msgErrorCode to set
	 *//*
	public void setMsgErrorCode(String msgErrorCode) {
		this.msgErrorCode = msgErrorCode;
	}
*/

	//The identification of the regulatory reporting authority
	//@Column MSGS_RGLTRYRPTG_BKID
	private String regulatoryBankCode;
	//The currency of the information reported to the regulatory authority
	//@Column MSGS_RGLTRYRPTG_CCY
	private String regulatoryReportCurrency;
	//The amount that is reported to the regulatory authority
	//@Column MSGS_RGLTRYRPTG_AMT
	private BigDecimal regulatoryReportAmount;
	//The information to the regulatory authority
	//@Column MSGS_RGLTRYRPTG_INF
	private String regulatoryInformation;
	//Unique identification, as assigned by the initiating party, to unambiguously identify the remittance information sent separately from the payment instruction, such as a remittance advice.
	//@Column MSGS_R_RMTID
	private String initiatorRemitReference;
	
	//Method used to deliver the remittance advice information
	//@Column MSGS_R_RMTLCTNMTD
	private String initiatorRemitAdviceMethod;
	//Electronic address to which an agent is to send the remittance information
	//@Column MSGS_R_RMTLCTNELCTRNCADR

	private String remitInfoEmail;
	//Name by which a party is known and is usually used to identify that identity
	//@Column MSGS_R_RMTLCTNPSTLADR_NM
	private String remitReceivingPartyName;
	//Postal address of a party.
	//@Column MSGS_R_RMTLCTNPSTLADR_ADR
	private String remitReceivingPartyAddress;
	
	//Related remittance information reference stored in remittance information table
	//@Column MSGS_R_RMTINF_REF
	private String relatedRemitInfo;
	//Remittance information stored in remittance information table
	//@Column MSGS_RMTINF_REF FIXME get the correct name
	private String remtInfoRef;

	//The return reference if any
	//@Column MSGS_RETURNREF
	private String msgReturnReference;
	
	//The identified customer account
	//@Column MSGS_IDTFD_CUSTAC
	private String custAccount;
	
	//The time of the batch for which this message was sent
	//@Column MSGS_BATCHTIME
	private Timestamp msgBatchTime;
	
	//The department code identified based on department rules. Will be set by entity control service
	//@Column MSGS_DEPT
	private String msgDept;
	//The branch that this message is intended for or sent by
	//@Column MSGS_BRANCH
	private String msgBranch;
	
	//The rules applied on this message
	//@Column MSGS_RULESAPPLIED
	private String msgRules;
	//message error code
	//@MSGS_ERRORCODE
	//private String msgErrorCode;
	//@MSGS_DBTRTYPE
	private String orderingType;
	
	//@MSGS_CDTRTYPE
	private String beneficiaryType;
	
	//@MSGS_PMTID_RELREF
	private String relReference;
	
	
	//@MSGS_RELMSG_MSGREF
	private String relUid;
	
	
	//Name of the party (person or company) that owes an amount of money to the (ultimate) creditor
	//@MSGS_DBTR_NM
	private String orderingCustomerName;
	
	
	//Postal address of the party (person or company) that owes an amount of money to the (ultimate) creditor
	//@MSGS_DBTR_PSTLADDR
	private String orderingCustomerAddress;
	
	
	//ID of the party (person or company) that owes an amount of money to the (ultimate) creditor
	//@MSGS_DBTR_ID
	private String orderingCustomerId;
	
	
	//@MSGS_DBTR_CTRYOFRES
	private String orderingCustomerCtry;
	//@MSGS_DBTR_CTCTDTLS
	private String orderingCustomerCtctDtls;

	


	//@MSGS_INTRMYAGT1_NM
	private String intermediary1BankName;
	//@MSGS_INTRMYAGT2_NM
	private String intermediary2BankName;

	
	//@MSGS_INTRMYAGT3_NM
	private String intermediary3BankName;
	
	//@MSGS_ULTMTDBTR_NM
	private String ultimateDebtorName;
	//@MSGS_ULTMTDBTR_PSTLADR
	private String ultimateDebtorAddress;
	//@MSGS_ULTMTDBTR_ID
	private String ultimateDebtorID;
	//@MSGS_ULTMTDBTR_CTRYOFRES
	private String ultimateDebtorCtry;
	//@MSGS_ULTMTDBTR_CTCTDTLS
	private String ultimateDebtorCtctDtls;
	
	//@MSGS_INITGPRTY_NM
	private String initiatingPartyName;
	//@MSGS_INITGPRTY_PSTLADDR
	private String initiatingPartyAddress;
	//@MSGS_INITGPRTY_ID
	private String initiatingPartyID;
	//@MSGS_INITGPRTY_CTRYOFRES
	private String initiatingPartyCtry;
	//@MSGS_INITGPRTY_CTCTDTLS
	private String initiatingPartyCtctDtls;
	
	//@MSGS_SNDRAGT
	private String senderCorrespondent;
	//@MSGS_SNDRAGTACCT
	private String senderCorrespondentAcct;
	//@MSGS_RCVRAGT
	private String receiverCorrespondent;
	//@MSGS_RCVRAGTACCT
	private String receiverCorrespondentAcct;
	//@MSGS_THIRDAGT
	private String thirdCorrespondent;
	//@MSGS_THIRDAGTACCT
	private String thirdCorrespondentAcct;
	//MSG_SRVID
	private String serviceID;
	/**
	 * @return the serviceID
	 */
	@Column(name="MSG_SRVID")
	public String getServiceID() {
		return serviceID;
	}
	/**
	 * @param serviceID the serviceID to set
	 */
	public void setServiceID(String serviceID) {
		this.serviceID = serviceID;
	}
	
	//MSG_INSTDCURAMT
	private BigDecimal instructedCcyAmount;
	//MSG_MSGCURAMT
	private BigDecimal msgCurrencyAmount;
	//MSG_BASECURAMT
	private BigDecimal baseCcyAmount;
	//MSG_DRCUR
	private String drCurrency;
	//MSG_CRCUR
	private String crCurrency;
	//MSGS_STS_ISFINAL
	private Integer finalStatus;
	
	
	//MSG_RELATED_REF
	
	private String relatedReference;
	//MSG_MUR
	private String msgMur;
	
	//MSGS_RGLTRYRPTG_DRCR
	private String regulatoryReportDrCr;
	
	
	/**
	 * @return the instructedCcyAmount
	 */
	@Column(name="MSG_INSTDCURAMT")
	public BigDecimal getInstructedCcyAmount() {
		return instructedCcyAmount;
	}
	/**
	 * @param instructedCcyAmount the instructedCcyAmount to set
	 */
	public void setInstructedCcyAmount(BigDecimal instructedCcyAmount) {
		this.instructedCcyAmount = instructedCcyAmount;
	}
	/**
	 * @return the msgCurrencyAmount
	 */
	@Column(name="MSG_MSGCURAMT")
	public BigDecimal getMsgCurrencyAmount() {
		return msgCurrencyAmount;
	}
	/**
	 * @param msgCurrencyAmount the msgCurrencyAmount to set
	 */
	public void setMsgCurrencyAmount(BigDecimal msgCurrencyAmount) {
		this.msgCurrencyAmount = msgCurrencyAmount;
	}
	/**
	 * @return the baseCcyAmount
	 */
	@Column(name="MSG_BASECURAMT")
	public BigDecimal getBaseCcyAmount() {
		return baseCcyAmount;
	}
	/**
	 * @param baseCcyAmount the baseCcyAmount to set
	 */
	public void setBaseCcyAmount(BigDecimal baseCcyAmount) {
		this.baseCcyAmount = baseCcyAmount;
	}
	/**
	 * @return the drCurrency
	 */
	@Column(name="MSG_DRCUR")
	public String getDrCurrency() {
		return drCurrency;
	}
	/**
	 * @param drCurrency the drCurrency to set
	 */
	public void setDrCurrency(String drCurrency) {
		this.drCurrency = drCurrency;
	}
	/**
	 * @return the crCurrency
	 */
	@Column(name="MSG_CRCUR")
	public String getCrCurrency() {
		return crCurrency;
	}
	/**
	 * @param crCurrency the crCurrency to set
	 */
	public void setCrCurrency(String crCurrency) {
		this.crCurrency = crCurrency;
	}
	/**
	 * @return the finalStatus
	 */
	@Column(name="MSGS_STS_ISFINAL")
	public Integer getFinalStatus() {
		return finalStatus;
	}
	/**
	 * @param finalStatus the finalStatus to set
	 */
	public void setFinalStatus(Integer finalStatus) {
		this.finalStatus = finalStatus;
	}
	/**
	 * @return the relatedReference
	 */
	@Column(name="MSG_RELATED_REF")
	public String getRelatedReference() {
		return relatedReference;
	}
	/**
	 * @param relatedReference the relatedReference to set
	 */
	public void setRelatedReference(String relatedReference) {
		this.relatedReference = relatedReference;
	}
	/**
	 * @return the msgMur
	 */
	@Column(name="MSG_MUR")
	public String getMsgMur() {
		return msgMur;
	}
	/**
	 * @param msgMur the msgMur to set
	 */
	public void setMsgMur(String msgMur) {
		this.msgMur = msgMur;
	}
	/**
	 * @return the regulatoryReportDrCr
	 */
	@Column(name="MSGS_RGLTRYRPTG_DRCR")
	public String getRegulatoryReportDrCr() {
		return regulatoryReportDrCr;
	}
	/**
	 * @param regulatoryReportDrCr the regulatoryReportDrCr to set
	 */
	public void setRegulatoryReportDrCr(String regulatoryReportDrCr) {
		this.regulatoryReportDrCr = regulatoryReportDrCr;
	}


	/**
	 * @return the senderCorrespondent
	 */
	@Column(name="MSGS_SNDRAGT")
	public String getSenderCorrespondent() {
		return senderCorrespondent;
	}
	/**
	 * @param senderCorrespondent the senderCorrespondent to set
	 */
	public void setSenderCorrespondent(String senderCorrespondent) {
		this.senderCorrespondent = senderCorrespondent;
	}
	/**
	 * @return the senderCorrespondentAcct
	 */
	@Column(name="MSGS_SNDRAGTACCT")
	public String getSenderCorrespondentAcct() {
		return senderCorrespondentAcct;
	}
	/**
	 * @param senderCorrespondentAcct the senderCorrespondentAcct to set
	 */
	public void setSenderCorrespondentAcct(String senderCorrespondentAcct) {
		this.senderCorrespondentAcct = senderCorrespondentAcct;
	}
	/**
	 * @return the receiverCorrespondent
	 */
	@Column(name="MSGS_RCVRAGT")
	public String getReceiverCorrespondent() {
		return receiverCorrespondent;
	}
	/**
	 * @param receiverCorrespondent the receiverCorrespondent to set
	 */
	public void setReceiverCorrespondent(String receiverCorrespondent) {
		this.receiverCorrespondent = receiverCorrespondent;
	}
	/**
	 * @return the receiverCorrespondentAcct
	 */
	@Column(name="MSGS_RCVRAGTACCT")
	public String getReceiverCorrespondentAcct() {
		return receiverCorrespondentAcct;
	}
	/**
	 * @param receiverCorrespondentAcct the receiverCorrespondentAcct to set
	 */
	public void setReceiverCorrespondentAcct(String receiverCorrespondentAcct) {
		this.receiverCorrespondentAcct = receiverCorrespondentAcct;
	}
	/**
	 * @return the thirdCorrespondent
	 */
	@Column(name="MSGS_THIRDAGT")
	public String getThirdCorrespondent() {
		return thirdCorrespondent;
	}
	/**
	 * @param thirdCorrespondent the thirdCorrespondent to set
	 */
	public void setThirdCorrespondent(String thirdCorrespondent) {
		this.thirdCorrespondent = thirdCorrespondent;
	}
	/**
	 * @return the thirdCorrespondentAcct
	 */
	@Column(name="MSGS_THIRDAGTACCT")
	public String getThirdCorrespondentAcct() {
		return thirdCorrespondentAcct;
	}
	/**
	 * @param thirdCorrespondentAcct the thirdCorrespondentAcct to set
	 */
	public void setThirdCorrespondentAcct(String thirdCorrespondentAcct) {
		this.thirdCorrespondentAcct = thirdCorrespondentAcct;
	}
	/**
	 * @return the orderingCustomerCtctDtls
	 */
	@Column(name="MSGS_DBTR_CTCTDTLS")
	public String getOrderingCustomerCtctDtls() {
		return orderingCustomerCtctDtls;
	}
	/**
	 * @param orderingCustomerCtctDtls the orderingCustomerCtctDtls to set
	 */
	public void setOrderingCustomerCtctDtls(String orderingCustomerCtctDtls) {
		this.orderingCustomerCtctDtls = orderingCustomerCtctDtls;
	}
	/**
	 * @return the initiatingPartyName
	 */
	@Column(name="MSGS_INITGPRTY_NM")
	public String getInitiatingPartyName() {
		return initiatingPartyName;
	}
	
	public void setInitiatingPartyName(String initiatingPartyName) {
		this.initiatingPartyName = initiatingPartyName;
	}
	/**
	 * @return the initiatingPartyAddress
	 */
	@Column(name="MSGS_INITGPRTY_PSTLADDR")
	public String getInitiatingPartyAddress() {
		return initiatingPartyAddress;
	}
	/**
	 * @param initiatingPartyAddress the initiatingPartyAddress to set
	 */
	public void setInitiatingPartyAddress(String initiatingPartyAddress) {
		this.initiatingPartyAddress = initiatingPartyAddress;
	}
	/**
	 * @return the initiatingPartyID
	 */
	@Column(name="MSGS_INITGPRTY_ID")
	public String getInitiatingPartyID() {
		return initiatingPartyID;
	}
	/**
	 * @param initiatingPartyID the initiatingPartyID to set
	 */
	public void setInitiatingPartyID(String initiatingPartyID) {
		this.initiatingPartyID = initiatingPartyID;
	}
	/**
	 * @return the initiatingPartyCtry
	 */
	@Column(name="MSGS_INITGPRTY_CTRYOFRES")
	public String getInitiatingPartyCtry() {
		return initiatingPartyCtry;
	}
	/**
	 * @param initiatingPartyCtry the initiatingPartyCtry to set
	 */
	public void setInitiatingPartyCtry(String initiatingPartyCtry) {
		this.initiatingPartyCtry = initiatingPartyCtry;
	}
	/**
	 * @return the initiatingPartyCtctDtls
	 */
	@Column(name="MSGS_INITGPRTY_CTCTDTLS")
		public String getInitiatingPartyCtctDtls() {
		return initiatingPartyCtctDtls;
	}
	/**
	 * @param initiatingPartyCtctDtls the initiatingPartyCtctDtls to set
	 */
	public void setInitiatingPartyCtctDtls(String initiatingPartyCtctDtls) {
		this.initiatingPartyCtctDtls = initiatingPartyCtctDtls;
	}
	/**
	 * @return the ultimateDebtorName
	 */
	@Column(name="MSGS_ULTMTDBTR_NM")
	public String getUltimateDebtorName() {
		return ultimateDebtorName;
	}
	/**
	 * @param ultimateDebtorName the ultimateDebtorName to set
	 */
	public void setUltimateDebtorName(String ultimateDebtorName) {
		this.ultimateDebtorName = ultimateDebtorName;
	}
	/**
	 * @return the ultimateDebtorAddress
	 */
	@Column(name="MSGS_ULTMTDBTR_PSTLADR")
	public String getUltimateDebtorAddress() {
		return ultimateDebtorAddress;
	}
	/**
	 * @param ultimateDebtorAddress the ultimateDebtorAddress to set
	 */
	public void setUltimateDebtorAddress(String ultimateDebtorAddress) {
		this.ultimateDebtorAddress = ultimateDebtorAddress;
	}
	/**
	 * @return the ultimateDebtorID
	 */
	@Column(name="MSGS_ULTMTDBTR_ID")
	public String getUltimateDebtorID() {
		return ultimateDebtorID;
	}
	/**
	 * @param ultimateDebtorID the ultimateDebtorID to set
	 */
	public void setUltimateDebtorID(String ultimateDebtorID) {
		this.ultimateDebtorID = ultimateDebtorID;
	}
	/**
	 * @return the ultimateDebtorCtry
	 */
	@Column(name="MSGS_ULTMTDBTR_CTRYOFRES")
	public String getUltimateDebtorCtry() {
		return ultimateDebtorCtry;
	}
	/**
	 * @param ultimateDebtorCtry the ultimateDebtorCtry to set
	 */
	public void setUltimateDebtorCtry(String ultimateDebtorCtry) {
		this.ultimateDebtorCtry = ultimateDebtorCtry;
	}
	/**
	 * @return the ultimateDebtorCtctDtls
	 */
	@Column(name="MSGS_ULTMTDBTR_CTCTDTLS")
	public String getUltimateDebtorCtctDtls() {
		return ultimateDebtorCtctDtls;
	}
	/**
	 * @param ultimateDebtorCtctDtls the ultimateDebtorCtctDtls to set
	 */
	public void setUltimateDebtorCtctDtls(String ultimateDebtorCtctDtls) {
		this.ultimateDebtorCtctDtls = ultimateDebtorCtctDtls;
	}
	/**
	 * @return the intermediary3BankName
	 */
	@Column(name="MSGS_INTRMYAGT3_NM")
	public String getIntermediary3BankName() {
		return intermediary3BankName;
	}
	/**
	 * @param intermediary3BankName the intermediary3BankName to set
	 */
	public void setIntermediary3BankName(String intermediary3BankName) {
		this.intermediary3BankName = intermediary3BankName;
	}
	/**
	 * @return the intermediary2BankName
	 */
	@Column(name="MSGS_INTRMYAGT2_NM")
	public String getIntermediary2BankName() {
		return intermediary2BankName;
	}
	/**
	 * @param intermediary2BankName the intermediary2BankName to set
	 */
	public void setIntermediary2BankName(String intermediary2BankName) {
		this.intermediary2BankName = intermediary2BankName;
	}
	/**
	 * @return the intermediary1BankName
	 */
	@Column(name="MSGS_INTRMYAGT1_NM")
	public String getIntermediary1BankName() {
		return intermediary1BankName;
	}
	/**
	 * @param intermediary1BankName the intermediary1BankName to set
	 */
	public void setIntermediary1BankName(String intermediary1BankName) {
		this.intermediary1BankName = intermediary1BankName;
	}
	@Column(name="MSGS_DBTR_CTRYOFRES")
	public String getOrderingCustomerCtry() {
		return orderingCustomerCtry;
	}
	public void setOrderingCustomerCtry(String orderingCustomerCtry) {
		this.orderingCustomerCtry = orderingCustomerCtry;
	}
	
	@Column(name="MSGS_DBTR_ID")
	public String getOrderingCustomerId() {
		return orderingCustomerId;
	}
	public void setOrderingCustomerId(String orderingCustomerId) {
		this.orderingCustomerId = orderingCustomerId;
	}
	@Column(name="MSGS_DBTR_PSTLADDR")
	public String getOrderingCustomerAddress() {
		return orderingCustomerAddress;
	}
	public void setOrderingCustomerAddress(String orderingCustomerAddress) {
		this.orderingCustomerAddress = orderingCustomerAddress;
	}
	@Column(name="MSGS_DBTR_NM")
	public String getOrderingCustomerName() {
		return orderingCustomerName;
	}
	public void setOrderingCustomerName(String orderingCustomerName) {
		this.orderingCustomerName = orderingCustomerName;
	}
	@Column(name="MSGS_RELMSG_MSGREF")
	public String getRelUid() {
		return relUid;
	}
	public void setRelUid(String relUid) {
		this.relUid = relUid;
	}
	@Column(name="MSGS_PMTID_RELREF")
	public String getRelReference() {
		return relReference;
	}
	public void setRelReference(String relReference) {
		this.relReference = relReference;
	}
	@Column(name="MSGS_CDTRTYPE")
	public String getBeneficiaryType() {
		return beneficiaryType;
	}
	public void setBeneficiaryType(String beneficiaryType) {
		this.beneficiaryType = beneficiaryType;
	}
	@Column(name="MSGS_DBTRTYPE")
	public String getOrderingType() {
		return orderingType;
	}
	public void setOrderingType(String orderingType) {
		this.orderingType = orderingType;
	}
	
	/*public String getMsgErrorCode() {
		return msgErrorCode;
	}
	public void setMsgErrorCode(String msgErrorCode) {
		this.msgErrorCode = msgErrorCode;
	}*/
	@Column(name="MSGS_GRP_SEQ")
	public Integer getGrpSeq() {
		return grpSeq;
	}
	public void setGrpSeq(Integer grpSeq) {
		this.grpSeq = grpSeq;
	}
	
	
	
	@Column(name="MSGS_RULESAPPLIED")
	public String getMsgRules() {
		return msgRules;
	}
	public void setMsgRules(String msgRules) {
		this.msgRules = msgRules;
	}
	@Column(name="MSGS_BRANCH")
	public String getMsgBranch() {
		return msgBranch;
	}
	public void setMsgBranch(String msgBranch) {
		this.msgBranch = msgBranch;
	}
	@Column(name="MSGS_DEPT")
	public String getMsgDept() {
		return msgDept;
	}
	public void setMsgDept(String msgDept) {
		this.msgDept = msgDept;
	}
	@Column(name="MSGS_BATCHTIME")
	public Timestamp getMsgBatchTime() {
		return msgBatchTime;
	}
	public void setMsgBatchTime(Timestamp msgBatchTime) {
		this.msgBatchTime = msgBatchTime;
	}
	@Column(name="MSGS_IDTFD_CUSTAC")
	public String getCustAccount() {
		return custAccount;
	}
	public void setCustAccount(String custAccount) {
		this.custAccount = custAccount;
	}

	
	@Column(name="MSGS_RETURNREF")
	public String getMsgReturnReference() {
		return msgReturnReference;
	}
	public void setMsgReturnReference(String msgReturnReference) {
		this.msgReturnReference = msgReturnReference;
	}
	@Column(name="MSGS_RMTINF_REF")
	public String getRemtInfoRef() {
		return remtInfoRef;
	}
	public void setRemtInfoRef(String remtInfoRef) {
		this.remtInfoRef = remtInfoRef;
	}
	@Column(name="MSGS_R_RMTINF_REF")
	public String getRelatedRemitInfo() {
		return relatedRemitInfo;
	}
	public void setRelatedRemitInfo(String relatedRemitInfo) {
		this.relatedRemitInfo = relatedRemitInfo;
	}
	@Column(name="MSGS_R_RMTLCTNPSTLADR_ADR")
	public String getRemitReceivingPartyAddress() {
		return remitReceivingPartyAddress;
	}
	public void setRemitReceivingPartyAddress(String remitReceivingPartyAddress) {
		this.remitReceivingPartyAddress = remitReceivingPartyAddress;
	}
	@Column(name="MSGS_R_RMTLCTNPSTLADR_NM")
	public String getRemitReceivingPartyName() {
		return remitReceivingPartyName;
	}
	public void setRemitReceivingPartyName(String remitReceivingPartyName) {
		this.remitReceivingPartyName = remitReceivingPartyName;
	}
	@Column(name="MSGS_R_RMTLCTNELCTRNCADR")
	public String getRemitInfoEmail() {
		return remitInfoEmail;
	}
	public void setRemitInfoEmail(String remitInfoEmail) {
		this.remitInfoEmail = remitInfoEmail;
	}
	@Column(name="MSGS_R_RMTLCTNMTD")
	public String getInitiatorRemitAdviceMethod() {
		return initiatorRemitAdviceMethod;
	}
	public void setInitiatorRemitAdviceMethod(String initiatorRemitAdviceMethod) {
		this.initiatorRemitAdviceMethod = initiatorRemitAdviceMethod;
	}
	@Column(name="MSGS_R_RMTID")
	public String getInitiatorRemitReference() {
		return initiatorRemitReference;
	}
	public void setInitiatorRemitReference(String initiatorRemitReference) {
		this.initiatorRemitReference = initiatorRemitReference;
	}
	@Column(name="MSGS_RGLTRYRPTG_INF")
	public String getRegulatoryInformation() {
		return regulatoryInformation;
	}
	public void setRegulatoryInformation(String regulatoryInformation) {
		this.regulatoryInformation = regulatoryInformation;
	}
	@Column(name="MSGS_RGLTRYRPTG_AMT")
	public BigDecimal getRegulatoryReportAmount() {
		return regulatoryReportAmount;
	}
	public void setRegulatoryReportAmount(BigDecimal regulatoryReportAmount) {
		this.regulatoryReportAmount = regulatoryReportAmount;
	}
	@Column(name="MSGS_RGLTRYRPTG_CCY")
	public String getRegulatoryReportCurrency() {
		return regulatoryReportCurrency;
	}
	public void setRegulatoryReportCurrency(String regulatoryReportCurrency) {
		this.regulatoryReportCurrency = regulatoryReportCurrency;
	}
	@Column(name="MSGS_RGLTRYRPTG_BKID")
	public String getRegulatoryBankCode() {
		return regulatoryBankCode;
	}
	public void setRegulatoryBankCode(String regulatoryBankCode) {
		this.regulatoryBankCode = regulatoryBankCode;
	}
	@Column(name="MSGS_PURP_PRTRY")
	public String getMsgPurposeText() {
		return msgPurposeText;
	}
	public void setMsgPurposeText(String msgPurposeText) {
		this.msgPurposeText = msgPurposeText;
	}
	@Column(name="MSGS_PURP_CD")
	public String getMsgPurposeCode() {
		return msgPurposeCode;
	}
	public void setMsgPurposeCode(String msgPurposeCode) {
		this.msgPurposeCode = msgPurposeCode;
	}
	@Column(name="MSGS_INSTRFORNXTAGT_INSTRINF")
	public String getInstructionsForNextAgtText() {
		return instructionsForNextAgtText;
	}
	public void setInstructionsForNextAgtText(String instructionsForNextAgtText) {
		this.instructionsForNextAgtText = instructionsForNextAgtText;
	}
	@Column(name="MSGS_INSTRFORNXTAGT_CD")
	public String getInstructionsForNextAgtCode() {
		return instructionsForNextAgtCode;
	}
	public void setInstructionsForNextAgtCode(String instructionsForNextAgtCode) {
		this.instructionsForNextAgtCode = instructionsForNextAgtCode;
	}
	@Column(name="MSGS_INSTRFORCDTRAGT_INSTRINF")
	public String getInstructionsForCrdtrAgtText() {
		return instructionsForCrdtrAgtText;
	}
	public void setInstructionsForCrdtrAgtText(String instructionsForCrdtrAgtText) {
		this.instructionsForCrdtrAgtText = instructionsForCrdtrAgtText;
	}
	@Column(name="MSGS_INSTRFORCDTRAGT_CD")
	public String getInstructionsForCrdtrAgtCode() {
		return instructionsForCrdtrAgtCode;
	}
	public void setInstructionsForCrdtrAgtCode(String instructionsForCrdtrAgtCode) {
		this.instructionsForCrdtrAgtCode = instructionsForCrdtrAgtCode;
	}
	/*@Column(name="MSGS_ULTMTCDTR")
	public String getUltimateCreditor() {
		return ultimateCreditor;
	}
	public void setUltimateCreditor(String ultimateCreditor) {
		this.ultimateCreditor = ultimateCreditor;
	}*/
	
	@Column(name="MSGS_CDTRAGTACCT")
	public String getAccountWithInstitutionAcct() {
		return accountWithInstitutionAcct;
	}
	public void setAccountWithInstitutionAcct(String accountWithInstitutionAcct) {
		this.accountWithInstitutionAcct = accountWithInstitutionAcct;
	}
	@Column(name="MSGS_CDTRAGT")
	public String getAccountWithInstitution() {
		return accountWithInstitution;
	}
	public void setAccountWithInstitution(String accountWithInstitution) {
		this.accountWithInstitution = accountWithInstitution;
	}
	@Column(name="MSGS_DBTRAGTACCT")
	
	public String getOrderingInstitutionAcct() {
		return orderingInstitutionAcct;
	}
	public void setOrderingInstitutionAcct(String orderingInstitutionAcct) {
		this.orderingInstitutionAcct = orderingInstitutionAcct;
	}
	@Column(name="MSGS_DBTRAGT")
	public String getOrderingInstitution() {
		return orderingInstitution;
	}
	public void setOrderingInstitution(String orderingInstitution) {
		this.orderingInstitution = orderingInstitution;
	}
	@Column(name="MSGS_DBTRACCT")
	public String getOrderingCustAccount() {
		return orderingCustAccount;
	}
	public void setOrderingCustAccount(String orderingCustAccount) {
		this.orderingCustAccount = orderingCustAccount;
	}

	
	//private String orderingCustomer;
	

	/*@Column(name="MSGS_DBTR")
	public String getOrderingCustomer() {
		return orderingCustomer;
	}*/
	/*public void setOrderingCustomer(String orderingCustomer) {
		this.orderingCustomer = orderingCustomer;
	}*/
	/*@Column(name="MSGS_ULTMTDBTR")
	public String getUltimateDebtor() {
		return ultimateDebtor;
	}*/
	/*@Column(name="MSGS_INITGPRTY")
	public String getInitiatingParty() {
		return initiatingParty;
	}

	public void setInitiatingParty(String initiatingParty) {
		this.initiatingParty = initiatingParty;
	}
*/
	/*public void setUltimateDebtor(String ultimateDebtor) {
		this.ultimateDebtor = ultimateDebtor;
	}*/
	@Column(name="MSGS_INTRMYAGT3_ACCT")
	public String getIntermediary3AgentAcct() {
		return intermediary3AgentAcct;
	}

	public void setIntermediary3AgentAcct(String intermediary3AgentAcct) {
		this.intermediary3AgentAcct = intermediary3AgentAcct;
	}

	@Column(name="MSGS_INTRMYAGT3_BKCD")
	public String getIntermediary3Bank() {
		return intermediary3Bank;
	}

	public void setIntermediary3Bank(String intermediary3Bank) {
		this.intermediary3Bank = intermediary3Bank;
	}

	@Column(name="MSGS_INTRMYAGT2_ACCT")
	public String getIntermediary2AgentAcct() {
		return intermediary2AgentAcct;
	}

	public void setIntermediary2AgentAcct(String intermediary2AgentAcct) {
		this.intermediary2AgentAcct = intermediary2AgentAcct;
	}

	@Column(name="MSGS_INTRMYAGT2_BKCD")
	public String getIntermediary2Bank() {
		return intermediary2Bank;
	}

	public void setIntermediary2Bank(String intermediary2Bank) {
		this.intermediary2Bank = intermediary2Bank;
	}

	@Column(name="MSGS_INTRMYAGT1_ACCT")
	public String getIntermediary1AgentAcct() {
		return intermediary1AgentAcct;
	}

	public void setIntermediary1AgentAcct(String intermediary1AgentAcct) {
		this.intermediary1AgentAcct = intermediary1AgentAcct;
	}

	@Column(name="MSGS_INTRMYAGT1_BKCD")
	public String getIntermediary1Bank() {
		return intermediary1Bank;
	}

	public void setIntermediary1Bank(String intermediary1Bank) {
		this.intermediary1Bank = intermediary1Bank;
	}

	@Column(name="MSGS_INSTDAGT_BKCD")
	public String getReceiverBank() {
		return receiverBank;
	}

	public void setReceiverBank(String receiverBank) {
		this.receiverBank = receiverBank;
	}

	@Column(name="MSGS_INSTGAGT_BKCD")
	public String getSenderBank() {
		return senderBank;
	}

	public void setSenderBank(String senderBank) {
		this.senderBank = senderBank;
	}

	@Column(name="MSGS_PRVSINSTGAGT_ACCT")
	public String getPrevInstructingAgentAcct() {
		return prevInstructingAgentAcct;
	}

	public void setPrevInstructingAgentAcct(String prevInstructingAgentAcct) {
		this.prevInstructingAgentAcct = prevInstructingAgentAcct;
	}

	@Column(name="MSGS_PRVSINSTGAGT_BKCD")
	public String getPrevInstructingBank() {
		return prevInstructingBank;
	}

	public void setPrevInstructingBank(String prevInstructingBank) {
		this.prevInstructingBank = prevInstructingBank;
	}

/*	@Column(name="MSGS_CHRGSINF_PTY_BKCD")
	public String getChargingPartyBank() {
		return chargingPartyBank;
	}

	public void setChargingPartyBank(String chargingPartyBank) {
		this.chargingPartyBank = chargingPartyBank;
	}*/

	/*@Column(name="MSGS_CHRGSINF_AMT")
	public BigDecimal getChargeAmount() {
		return chargeAmount;
	}

	public void setChargeAmount(BigDecimal chargeAmount) {
		this.chargeAmount = chargeAmount;
	}*/

	/*@Column(name="MSGS_CHRGSINF_CCY")
	public String getChargeCurrency() {
		return chargeCurrency;
	}

	public void setChargeCurrency(String chargeCurrency) {
		this.chargeCurrency = chargeCurrency;
	}*/

	@Column(name="MSGS_CHRGBR")
	public String getChargeBearer() {
		return chargeBearer;
	}

	public void setChargeBearer(String chargeBearer) {
		this.chargeBearer = chargeBearer;
	}

	@Column(name="MSGS_XCHGRATE")
	public BigDecimal getXchangeRate() {
		return xchangeRate;
	}

	public void setXchangeRate(BigDecimal xchangeRate) {
		this.xchangeRate = xchangeRate;
	}

	@Column(name="MSGS_INSTDAMT")
	public BigDecimal getInstructedAmount() {
		return instructedAmount;
	}

	public void setInstructedAmount(BigDecimal instructedAmount) {
		this.instructedAmount = instructedAmount;
	}

	@Column(name="MSGS_INSTDCCY")
	public String getInstructedCurrency() {
		return instructedCurrency;
	}

	public void setInstructedCurrency(String instructedCurrency) {
		this.instructedCurrency = instructedCurrency;
	}

	@Column(name="MSGS_POOLGADJSTMNTDT")
	public Timestamp getCashpoolAdjstmntTime() {
		return cashpoolAdjstmntTime;
	}

	public void setCashpoolAdjstmntTime(Timestamp cashpoolAdjstmntTime) {
		this.cashpoolAdjstmntTime = cashpoolAdjstmntTime;
	}

	@Column(name="MSGS_ACCPTNCDTTM")
	public Timestamp getPymntAcceptedTime() {
		return pymntAcceptedTime;
	}

	public void setPymntAcceptedTime(Timestamp pymntAcceptedTime) {
		this.pymntAcceptedTime = pymntAcceptedTime;
	}

	@Column(name="MSGS_STTLMTMREQ_RJCTTM")
	public Timestamp getSttlmntRejTime() {
		return sttlmntRejTime;
	}

	public void setSttlmntRejTime(Timestamp sttlmntRejTime) {
		this.sttlmntRejTime = sttlmntRejTime;
	}

	@Column(name="MSGS_STTLMTMREQ_FRTM")
	public Timestamp getSttlmntFromTime() {
		return sttlmntFromTime;
	}

	public void setSttlmntFromTime(Timestamp sttlmntFromTime) {
		this.sttlmntFromTime = sttlmntFromTime;
	}

	@Column(name="MSGS_STTLMTMREQ_TILLTM")
	public Timestamp getSttlmntTillTime() {
		return sttlmntTillTime;
	}

	public void setSttlmntTillTime(Timestamp sttlmntTillTime) {
		this.sttlmntTillTime = sttlmntTillTime;
	}

	@Column(name="MSGS_STTLMTMREQ_CLSTM")
	public Timestamp getClsDateTime() {
		return clsDateTime;
	}

	public void setClsDateTime(Timestamp clsDateTime) {
		this.clsDateTime = clsDateTime;
	}

	@Column(name="MSGS_STTLMTMINDCTN_CDTDTTM")
	public Timestamp getCrDateTime() {
		return crDateTime;
	}

	public void setCrDateTime(Timestamp crDateTime) {
		this.crDateTime = crDateTime;
	}

	@Column(name="MSGS_STTLMTMINDCTN_DBTDTTM")
	
	public Timestamp getDrDateTime() {
		return drDateTime;
	}

	public void setDrDateTime(Timestamp drDateTime) {
		this.drDateTime = drDateTime;
	}

	@Column(name="MSGS_INTRBKSTTLMPRTY")
	public String getSndrSttlmntPriority() {
		return sndrSttlmntPriority;
	}

	public void setSndrSttlmntPriority(String sndrSttlmntPriority) {
		this.sndrSttlmntPriority = sndrSttlmntPriority;
	}

	@Column(name="MSGS_INTRBKSTTLMDT")
	public Timestamp getMsgValueDate() {
		return msgValueDate;
	}

	public void setMsgValueDate(Timestamp msgValueDate) {
		this.msgValueDate = msgValueDate;
	}

	@Column(name="MSGS_INTRBKSTTLMAMT")
	public BigDecimal getMsgAmount() {
		return msgAmount;
	}

	public void setMsgAmount(BigDecimal msgAmount) {
		this.msgAmount = msgAmount;
	}

	@Column(name="MSGS_INTRBKSTTLMCCY")
	public String getMsgCurrency() {
		return msgCurrency;
	}

	public void setMsgCurrency(String msgCurrency) {
		this.msgCurrency = msgCurrency;
	}

	@Column(name="MSGS_PMTTPLINF_CTGYPURP_PRTRY")
	public String getCatgPurposePriority() {
		return catgPurposePriority;
	}

	public void setCatgPurposePriority(String catgPurposePriority) {
		this.catgPurposePriority = catgPurposePriority;
	}

	@Column(name="MSGS_PMTTPLINF_CTGYPURP_CD")
	public String getCatgPurposeCode() {
		return catgPurposeCode;
	}

	public void setCatgPurposeCode(String catgPurposeCode) {
		this.catgPurposeCode = catgPurposeCode;
	}

	@Column(name="MSGS_PMTTPLINF_LCLINSTRM_PRTRY")
		public String getLclInstPriority() {
		return lclInstPriority;
	}

	public void setLclInstPriority(String lclInstPriority) {
		this.lclInstPriority = lclInstPriority;
	}

	@Column(name="MSGS_PMTTPLINF_LCLINSTRM_CD")
	public String getLclInstCode() {
		return lclInstCode;
	}

	public void setLclInstCode(String lclInstCode) {
		this.lclInstCode = lclInstCode;
	}

	@Column(name="MSGS_PMTTPLINF_SVCLVL_PRTRY")
	public String getSvcLevelPriority() {
		return svcLevelPriority;
	}

	public void setSvcLevelPriority(String svcLevelPriority) {
		this.svcLevelPriority = svcLevelPriority;
	}

	@Column(name="MSGS_PMTTPLINF_SVCLVL_CD")
		public String getSvcLevelCode() {
		return svcLevelCode;
	}

	public void setSvcLevelCode(String svcLevelCode) {
		this.svcLevelCode = svcLevelCode;
	}

	@Column(name="MSGS_PMTTPLINF_CLRCHNL")
	public String getClrgChannel() {
		return clrgChannel;
	}

	public void setClrgChannel(String clrgChannel) {
		this.clrgChannel = clrgChannel;
	}

	@Column(name="MSGS_PMTTPLINF_INSTRPRTY")
	public String getSndrPymtPriority() {
		return sndrPymtPriority;
	}

	public void setSndrPymtPriority(String sndrPymtPriority) {
		this.sndrPymtPriority = sndrPymtPriority;
	}

	@Column(name="MSGS_PMTID_CLRSYSREF")
	public String getClrgSysReference() {
		return clrgSysReference;
	}

	public void setClrgSysReference(String clrgSysReference) {
		this.clrgSysReference = clrgSysReference;
	}

	@Column(name="MSGS_PMTID_INSTGID")
	public String getSndrTxnId() {
		return sndrTxnId;
	}

	public void setSndrTxnId(String sndrTxnId) {
		this.sndrTxnId = sndrTxnId;
	}
	
	@Column(name="MSGS_PMTID_ENDTOENDID")
	public String getCustTxnReference() {
		return custTxnReference;
	}
	
	public void setCustTxnReference(String custTxnReference) {
		this.custTxnReference = custTxnReference;
	}
	
	@Column(name="MSGS_PMTID_INSTRID")
	public String getTxnReference() {
		return txnReference;
	}

	public void setTxnReference(String txnReference) {
		this.txnReference = txnReference;
	}

	@Column(name="MSGS_LASTMODIFIEDTIME")
	public Timestamp getLastModTime() {
		return lastModTime;
	}

	public void setLastModTime(Timestamp lastModTime) {
		this.lastModTime = lastModTime;
	}

	@Column(name="MSGS_RECVDTIME")
		public Timestamp getReceivedTime() {
		return receivedTime;
	}

	public void setReceivedTime(Timestamp receivedTime) {
		this.receivedTime = receivedTime;
	}

	@Column(name="MSGS_PREVMSGSTS")
	
	public String getMsgPrevStatus() {
		return msgPrevStatus;
	}

	public void setMsgPrevStatus(String msgPrevStatus) {
		this.msgPrevStatus = msgPrevStatus;
	}

	@Column(name="MSGS_DIRECTION")
		public String getMsgDirection() {
		return msgDirection;
	}

	public void setMsgDirection(String msgDirection) {
		this.msgDirection = msgDirection;
	}

	@Column(name="MSGS_MSGSTS")
	public String getMsgStatus() {
		return msgStatus;
	}

	public void setMsgStatus(String msgStatus) {
		this.msgStatus = msgStatus;
	}

	@Column(name="MSGS_DST_SUBMSGTYPE")
	public String getDstMsgSubType() {
		return dstMsgSubType;
	}

	public void setDstMsgSubType(String dstMsgSubType) {
		this.dstMsgSubType = dstMsgSubType;
	}

	@Column(name="MSGS_DST_MSGTYPE")
	public String getDstMsgType() {
		return dstMsgType;
	}

	public void setDstMsgType(String dstMsgType) {
		this.dstMsgType = dstMsgType;
	}

	@Column(name="MSGS_SRC_MSGSUBTYPE")
	public String getSrcMsgSubType() {
		return srcMsgSubType;
	}

	public void setSrcMsgSubType(String srcMsgSubType) {
		this.srcMsgSubType = srcMsgSubType;
	}

	@Column(name="MSGS_SRC_MSGTYPE")
	public String getSrcMsgType() {
		return srcMsgType;
	}

	public void setSrcMsgType(String srcMsgType) {
		this.srcMsgType = srcMsgType;
	}

	
	
	@Column(name="MSGS_MSGCHNLTYPE")
	public String getMsgChnlType() {
		return msgChnlType;
	}

	public void setMsgChnlType(String msgChnlType) {
		this.msgChnlType = msgChnlType;
	}

	@Column(name="MSGS_CHANNELID")
	public String getMsgChannel() {
		return msgChannel;
	}

	public void setMsgChannel(String msgChannel) {
		this.msgChannel = msgChannel;
	}
	@Id
	@Column(name="MSGS_MSGREF")
	public String getMsgRef() {
		return msgRef;
	}

	public void setMsgRef(String msgRef) {
		this.msgRef = msgRef;
	}
	@Column(name="MSGS_GRP_MSGID")
	public String getGrpMsgId() {
		return grpMsgId;
	}

	public void setGrpMsgId(String grpMsgId) {
		this.grpMsgId = grpMsgId;
	}
	

	
	@Column(name="MSGS_HOSTID")
	public String getMsgHost() {
		return msgHost;
	}

	public void setMsgHost(String msgHost) {
		this.msgHost = msgHost;
	}
	//@Column MSGS_SNDRAGT_LOC
	private String senderCorrespondentLoc;
	//@Column MSGS_RCVRAGT_ID
	private String receiverCorrespondentId;
	//@Column MSGS_RCVRAGT_LOC
	private String receiverCorrespondentLoc;
	//@Column MSGS_RCVRAGT_NAME
	
	private String receiverCorrespondentName;
	
	@Column(name="MSGS_SNDRAGT_LOC")
	public String getSenderCorrespondentLoc() {
		return senderCorrespondentLoc;
	}
	public void setSenderCorrespondentLoc(String senderCorrespondentLoc) {
		this.senderCorrespondentLoc = senderCorrespondentLoc;
	}
	@Column(name="MSGS_RCVRAGT_ID")
	public String getReceiverCorrespondentId() {
		return receiverCorrespondentId;
	}
	public void setReceiverCorrespondentId(String receiverCorrespondentId) {
		this.receiverCorrespondentId = receiverCorrespondentId;
	}
	@Column(name="MSGS_RCVRAGT_LOC")
	public String getReceiverCorrespondentLoc() {
		return receiverCorrespondentLoc;
	}
	public void setReceiverCorrespondentLoc(String receiverCorrespondentLoc) {
		this.receiverCorrespondentLoc = receiverCorrespondentLoc;
	}
	@Column(name="MSGS_RCVRAGT_NAME")
	public String getReceiverCorrespondentName() {
		return receiverCorrespondentName;
	}
	public void setReceiverCorrespondentName(String receiverCorrespondentName) {
		this.receiverCorrespondentName = receiverCorrespondentName;
	}
	
	
	private String lcDraweeBnkAcct;
	@Column(name="LC_DRAWEEBANK_ACCT")
	public String getLcDraweeBnkAcct() {
		return lcDraweeBnkAcct;
	}
	public void setLcDraweeBnkAcct(String lcDraweeBnkAcct) {
		this.lcDraweeBnkAcct = lcDraweeBnkAcct;
	}
	
	
	
	//@Column MSGS_BENFINST_CD
	private String beneficiaryInstitution;
	//@Column MSGS_BENFINST_NAME
	private String beneficiaryInstitutionName;
	//@Column MSGS_BENFINST_NAME
	private String beneficiaryInstitutionAcct;
	
	@Column(name="MSGS_BENFINST_CD")
	public String getBeneficiaryInstitution() {
		return beneficiaryInstitution;
	}
	public void setBeneficiaryInstitution(String beneficiaryInstitution) {
		this.beneficiaryInstitution = beneficiaryInstitution;
	}
	@Column(name="MSGS_BENFINST_NAME")
	public String getBeneficiaryInstitutionName() {
		return beneficiaryInstitutionName;
	}
	public void setBeneficiaryInstitutionName(String beneficiaryInstitutionName) {
		this.beneficiaryInstitutionName = beneficiaryInstitutionName;
	}
	@Column(name="MSGS_BENFINST_ACCT")
	public String getBeneficiaryInstitutionAcct() {
		return beneficiaryInstitutionAcct;
	}
	public void setBeneficiaryInstitutionAcct(String beneficiaryInstitutionAcct) {
		this.beneficiaryInstitutionAcct = beneficiaryInstitutionAcct;
	}
	//@Column MSGS_BENFINST_PID
	private String beneficiaryInstitutionPID;
	@Column(name="MSGS_BENFINST_PID")
	public String getBeneficiaryInstitutionPID() {
		return beneficiaryInstitutionPID;
	}
	public void setBeneficiaryInstitutionPID(String beneficiaryInstitutionPID) {
		this.beneficiaryInstitutionPID = beneficiaryInstitutionPID;
	}

	private BigDecimal mesgIsReturn;
	private BigDecimal msgPDECount;

	@Column(name="MSGS_IS_RETURN")
	public BigDecimal getMesgIsReturn() {
		return mesgIsReturn;
	}
	public void setMesgIsReturn(BigDecimal mesgIsReturn) {
		this.mesgIsReturn = mesgIsReturn;
	}
	@Column(name="MSGS_PDECOUNT")
	public BigDecimal getMsgPDECount() {
		return msgPDECount;
	}
	public void setMsgPDECount(BigDecimal msgPDECount) {
		this.msgPDECount = msgPDECount;
	}


	
	


	
}
