/**
 * 
 */
package com.logica.ngph.dtos;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author chakkar
 *
 */
public class BankGuaranteeCoverDto implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String bgNumber;
	private String bgType;
	private String bgFormNumber;
	private String currency;
	private BigDecimal bgAmount=new BigDecimal(0.00);
	private Date bgFromDate;
	private Date bgToDate;
	private Date bgEffectiveDate;
	private Date bgLodgementEndDate;
	private String bgLodgementPalce;
	private String issuingBankCode;
	private String issunigBankNameAndAddress;
	private String bgApplicentNameAndDetails;
	private String beneficiaryNameAndDetails; 
	private String beneficiaryBankCode;
	private String beneficiaryBankNameAndAddress;
	private String senderToReciverInformation;
	private String bgPurpose;
	private String descriptionOfUnderlinedContract;
	private String stampDutyPaid;
	private String stampCertificateNumber;
	private Date stampDateAndTime;
	private BigDecimal bgAmountPaid =new BigDecimal(0.00);
	private String bgStateCode;
	private String bgArticleNumber;
	private Date bgPaymentDate;
	private String bgPaymentPlace;
	private String bgHeldDematForm;
	private String custodianServiceProvider;
	private String dematAccNumber;
	private String bgRelatedReference;
	private String bgFurtherIdentification;
	private Date bgAmendmentDate;
	private BigDecimal bgNoofAmendments = new BigDecimal(0);
	private Date bgIssueDate;
	private String bgAmedmentDetails;
	private String repair;
	private String msgRef;
	private String seqNo;
	private String msgHost;
	private String advisingBank;
	private String bgIssuingBankCode;
	
	
	
	
	

	public String getBgIssuingBankCode() {
		return bgIssuingBankCode;
	}
	public void setBgIssuingBankCode(String bgIssuingBankCode) {
		this.bgIssuingBankCode = bgIssuingBankCode;
	}
	public String getAdvisingBank() {
		return advisingBank;
	}
	public void setAdvisingBank(String advisingBank) {
		this.advisingBank = advisingBank;
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
	 * @return the msgRef
	 */
	public String getMsgRef() {
		return msgRef;
	}
	/**
	 * @param msgRef the msgRef to set
	 */
	public void setMsgRef(String msgRef) {
		this.msgRef = msgRef;
	}
	/**
	 * @return the repair
	 */
	public String getRepair() {
		return repair;
	}
	/**
	 * @param repair the repair to set
	 */
	public void setRepair(String repair) {
		this.repair = repair;
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
	 * @return the bgRelatedReference
	 */
	public String getBgRelatedReference() {
		return bgRelatedReference;
	}
	/**
	 * @param bgRelatedReference the bgRelatedReference to set
	 */
	public void setBgRelatedReference(String bgRelatedReference) {
		this.bgRelatedReference = bgRelatedReference;
	}
	/**
	 * @return the bgFurtherIdentification
	 */
	public String getBgFurtherIdentification() {
		return bgFurtherIdentification;
	}
	/**
	 * @param bgFurtherIdentification the bgFurtherIdentification to set
	 */
	public void setBgFurtherIdentification(String bgFurtherIdentification) {
		this.bgFurtherIdentification = bgFurtherIdentification;
	}
	/**
	 * @return the bgAmendmentDate
	 */
	public Date getBgAmendmentDate() {
		return bgAmendmentDate;
	}
	/**
	 * @param bgAmendmentDate the bgAmendmentDate to set
	 */
	public void setBgAmendmentDate(Date bgAmendmentDate) {
		this.bgAmendmentDate = bgAmendmentDate;
	}
	/**
	 * @return the bgNoofAmendments
	 */
	public BigDecimal getBgNoofAmendments() {
		return bgNoofAmendments;
	}
	/**
	 * @param bgNoofAmendments the bgNoofAmendments to set
	 */
	public void setBgNoofAmendments(BigDecimal bgNoofAmendments) {
		this.bgNoofAmendments = bgNoofAmendments;
	}
	/**
	 * @return the bgIssueDate
	 */
	public Date getBgIssueDate() {
		return bgIssueDate;
	}
	/**
	 * @param bgIssueDate the bgIssueDate to set
	 */
	public void setBgIssueDate(Date bgIssueDate) {
		this.bgIssueDate = bgIssueDate;
	}
	/**
	 * @return the bgAmedmentDetails
	 */
	public String getBgAmedmentDetails() {
		return bgAmedmentDetails;
	}
	/**
	 * @param bgAmedmentDetails the bgAmedmentDetails to set
	 */
	public void setBgAmedmentDetails(String bgAmedmentDetails) {
		this.bgAmedmentDetails = bgAmedmentDetails;
	}
	/**
	 * @return the bgNumber
	 */
	public String getBgNumber() {
		return bgNumber;
	}
	/**
	 * @param bgNumber the bgNumber to set
	 */
	public void setBgNumber(String bgNumber) {
		this.bgNumber = bgNumber;
	}
	/**
	 * @return the bgType
	 */
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
	 * @return the bgFormNumber
	 */
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
	 * @return the bgAmount
	 */
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
	 * @return the bgFromDate
	 */
	public Date getBgFromDate() {
		return bgFromDate;
	}
	/**
	 * @param bgFromDate the bgFromDate to set
	 */
	public void setBgFromDate(Date bgFromDate) {
		this.bgFromDate = bgFromDate;
	}
	/**
	 * @return the bgToDate
	 */
	public Date getBgToDate() {
		return bgToDate;
	}
	/**
	 * @param bgToDate the bgToDate to set
	 */
	public void setBgToDate(Date bgToDate) {
		this.bgToDate = bgToDate;
	}
	/**
	 * @return the bgEffectiveDate
	 */
	public Date getBgEffectiveDate() {
		return bgEffectiveDate;
	}
	/**
	 * @param bgEffectiveDate the bgEffectiveDate to set
	 */
	public void setBgEffectiveDate(Date bgEffectiveDate) {
		this.bgEffectiveDate = bgEffectiveDate;
	}
	/**
	 * @return the bgLodgementEndDate
	 */
	public Date getBgLodgementEndDate() {
		return bgLodgementEndDate;
	}
	/**
	 * @param bgLodgementEndDate the bgLodgementEndDate to set
	 */
	public void setBgLodgementEndDate(Date bgLodgementEndDate) {
		this.bgLodgementEndDate = bgLodgementEndDate;
	}
	/**
	 * @return the bgLodgementPalce
	 */
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
	 * @return the issunigBankNameAndAddress
	 */
	public String getIssunigBankNameAndAddress() {
		return issunigBankNameAndAddress;
	}
	/**
	 * @param issunigBankNameAndAddress the issunigBankNameAndAddress to set
	 */
	public void setIssunigBankNameAndAddress(String issunigBankNameAndAddress) {
		this.issunigBankNameAndAddress = issunigBankNameAndAddress;
	}
	/**
	 * @return the bgApplicentNameAndDetails
	 */
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
	 * @return the senderToReciverInformation
	 */
	public String getSenderToReciverInformation() {
		return senderToReciverInformation;
	}
	/**
	 * @param senderToReciverInformation the senderToReciverInformation to set
	 */
	public void setSenderToReciverInformation(String senderToReciverInformation) {
		this.senderToReciverInformation = senderToReciverInformation;
	}
	/**
	 * @return the bgPurpose
	 */
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
	public String getStampDutyPaid() {
		return stampDutyPaid;
	}
	/**
	 * @param eStampDutyPaid the eStampDutyPaid to set
	 */
	public void setStampDutyPaid(String stampDutyPaid) {
		this.stampDutyPaid = stampDutyPaid;
	}
	/**
	 * @return the eStampCertificateNumber
	 */
	public String getStampCertificateNumber() {
		return stampCertificateNumber;
	}
	/**
	 * @param eStampCertificateNumber the eStampCertificateNumber to set
	 */
	public void setStampCertificateNumber(String stampCertificateNumber) {
		this.stampCertificateNumber = stampCertificateNumber;
	}
	/**
	 * @return the eStampDateAndTime
	 */
	public Date getStampDateAndTime() {
		return stampDateAndTime;
	}
	/**
	 * @param eStampDateAndTime the eStampDateAndTime to set
	 */
	public void setStampDateAndTime(Date stampDateAndTime) {
		this.stampDateAndTime = stampDateAndTime;
	}
	/**
	 * @return the bgAmountPaid
	 */
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
	public Date getBgPaymentDate() {
		return bgPaymentDate;
	}
	/**
	 * @param bgPaymentDate the bgPaymentDate to set
	 */
	public void setBgPaymentDate(Date bgPaymentDate) {
		this.bgPaymentDate = bgPaymentDate;
	}
	/**
	 * @return the bgPaymentPlace
	 */
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
	public String getBgHeldDematForm() {
		return bgHeldDematForm;
	}
	/**
	 * @param eBgHeldDematForm the eBgHeldDematForm to set
	 */
	public void setBgHeldDematForm(String bgHeldDematForm) {
		this.bgHeldDematForm = bgHeldDematForm;
	}
	/**
	 * @return the custodianServiceProvider
	 */
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
	public String getDematAccNumber() {
		return dematAccNumber;
	}
	/**
	 * @param dematAccNumber the dematAccNumber to set
	 */
	public void setDematAccNumber(String dematAccNumber) {
		this.dematAccNumber = dematAccNumber;
	}
	
	

}
