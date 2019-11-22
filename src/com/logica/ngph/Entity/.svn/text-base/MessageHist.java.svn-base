package com.logica.ngph.Entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="TA_MESSAGES_TX_HIST")
public class MessageHist implements Serializable{
	private String msgRef;
	private String currency;
	private BigDecimal amount;
	private String direction;
	private String department;
	private String branch;
	private String msgStatus;
	private String msgType;
	private String subMsgType;
	private String channel;
	private String txnReference;
	private String senderBank;
	private String receiverBank;
	private Timestamp msgValueDate;
	private String prevInstructingBank;
	private String prevInstructingAgentAcct;
	private String narration;
	private String MUR;
	private String sendertoreciverInfo;
	private String hostID;
	private String relatedRefrence;
	private String dstMsgType;
	private String dstMsgSubType;
	private String dstChannelType;
	private Timestamp receivedTime;
	@Column(name="MSGS_RECVDTIME")
	public Timestamp getReceivedTime() {
	return receivedTime;
}

public void setReceivedTime(Timestamp receivedTime) {
	this.receivedTime = receivedTime;
}
	
	@Column(name="MSGS_DST_MSGTYPE")
	public String getDstMsgType() {
		return dstMsgType;
	}

	public void setDstMsgType(String dstMsgType) {
		this.dstMsgType = dstMsgType;
	}
	@Column(name="MSGS_DST_MSGSUBTYPE")
	public String getDstMsgSubType() {
		return dstMsgSubType;
	}

	public void setDstMsgSubType(String dstMsgSubType) {
		this.dstMsgSubType = dstMsgSubType;
	}
	@Column(name="MSGS_DST_CHANNELID")
	public String getDstChannelType() {
		return dstChannelType;
	}

	public void setDstChannelType(String dstChannelType) {
		this.dstChannelType = dstChannelType;
	}
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
	//The account id of the debtor customer
	//@Column MSGS_DBTRACCT
	private String orderingCustAccount;
	
	//Financial institution servicing an account for the debtor
	//@Column MSGS_DBTRAGT
	private String orderingInstitution;
	
	//Unambiguous identification of the account of the debtor agent at its servicing agent in the payment chain
	//@Column MSGS_DBTRAGTACCT
	private String orderingInstitutionAcct;
	
	//@Column MSGS_CDTRACCT
	private String beneficiaryCustAcct;
	
	//Name of the party (person or company) to which an amount of money is due
	//@MSGS_CDTR_NM
	private String beneficiaryCustomerName;
	

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
	
	//Postal Address of the ultimate party (person or company) to which an amount of money is due
	//@MSGS_ULTMTCDTR_PSTLADDR
	private String ultimateCreditorAddress;
	
	//ID of the ultimate party (person or company) to which an amount of money is due
	//@MSGS_ULTMTCDTR_ID
	private String ultimateCreditorID;
	
	//Coded information related to the processing of the payment instruction, provided by the initiating party, and intended for the next agent in the payment chain.
	//@Column MSGS_INSTRFORNXTAGT_CD
	private String instructionsForNextAgtCode;
	
	
	
	@Column(name="MSGS_INSTRFORNXTAGT_CD")
	public String getInstructionsForNextAgtCode() {
		return instructionsForNextAgtCode;
	}

	public void setInstructionsForNextAgtCode(String instructionsForNextAgtCode) {
		this.instructionsForNextAgtCode = instructionsForNextAgtCode;
	}

	@Column(name="MSGS_DBTR_PSTLADDR")
	public String getOrderingCustomerAddress() {
		return orderingCustomerAddress;
	}

	public void setOrderingCustomerAddress(String orderingCustomerAddress) {
		this.orderingCustomerAddress = orderingCustomerAddress;
	}
	@Column(name="MSGS_DBTR_ID")
	public String getOrderingCustomerId() {
		return orderingCustomerId;
	}

	public void setOrderingCustomerId(String orderingCustomerId) {
		this.orderingCustomerId = orderingCustomerId;
	}
	@Column(name="MSGS_DBTR_CTRYOFRES")
	public String getOrderingCustomerCtry() {
		return orderingCustomerCtry;
	}

	public void setOrderingCustomerCtry(String orderingCustomerCtry) {
		this.orderingCustomerCtry = orderingCustomerCtry;
	}
	@Column(name="MSGS_DBTRACCT")
	public String getOrderingCustAccount() {
		return orderingCustAccount;
	}

	public void setOrderingCustAccount(String orderingCustAccount) {
		this.orderingCustAccount = orderingCustAccount;
	}
	@Column(name="MSGS_DBTRAGT")
	public String getOrderingInstitution() {
		return orderingInstitution;
	}

	public void setOrderingInstitution(String orderingInstitution) {
		this.orderingInstitution = orderingInstitution;
	}
	@Column(name="MSGS_DBTRAGTACCT")
	public String getOrderingInstitutionAcct() {
		return orderingInstitutionAcct;
	}

	public void setOrderingInstitutionAcct(String orderingInstitutionAcct) {
		this.orderingInstitutionAcct = orderingInstitutionAcct;
	}
	@Column(name="MSGS_CDTRACCT")
	public String getBeneficiaryCustAcct() {
		return beneficiaryCustAcct;
	}

	public void setBeneficiaryCustAcct(String beneficiaryCustAcct) {
		this.beneficiaryCustAcct = beneficiaryCustAcct;
	}
	@Column(name="MSGS_CDTR_PSTLADDR")
	public String getBeneficiaryCustomerAddress() {
		return beneficiaryCustomerAddress;
	}

	public void setBeneficiaryCustomerAddress(String beneficiaryCustomerAddress) {
		this.beneficiaryCustomerAddress = beneficiaryCustomerAddress;
	}
	@Column(name="MSGS_CDTR_ID")
	public String getBeneficiaryCustomerID() {
		return beneficiaryCustomerID;
	}

	public void setBeneficiaryCustomerID(String beneficiaryCustomerID) {
		this.beneficiaryCustomerID = beneficiaryCustomerID;
	}
	@Column(name="MSGS_CDTR_CTRYOFRES")
	public String getBeneficiaryCustomerCtry() {
		return beneficiaryCustomerCtry;
	}

	public void setBeneficiaryCustomerCtry(String beneficiaryCustomerCtry) {
		this.beneficiaryCustomerCtry = beneficiaryCustomerCtry;
	}
	@Column(name="MSGS_ULTMTCDTR_NM")
	public String getUltimateCreditorName() {
		return ultimateCreditorName;
	}

	public void setUltimateCreditorName(String ultimateCreditorName) {
		this.ultimateCreditorName = ultimateCreditorName;
	}
	@Column(name="MSGS_ULTMTCDTR_PSTLADDR")
	public String getUltimateCreditorAddress() {
		return ultimateCreditorAddress;
	}

	public void setUltimateCreditorAddress(String ultimateCreditorAddress) {
		this.ultimateCreditorAddress = ultimateCreditorAddress;
	}
	@Column(name="MSGS_ULTMTCDTR_ID")
	public String getUltimateCreditorID() {
		return ultimateCreditorID;
	}

	public void setUltimateCreditorID(String ultimateCreditorID) {
		this.ultimateCreditorID = ultimateCreditorID;
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
	
	@Column(name="MSGS_PMTID_RELREF")
	public String getRelatedRefrence() {
		return relatedRefrence;
	}
	public void setRelatedRefrence(String relatedRefrence) {
		this.relatedRefrence = relatedRefrence;
	}
	@Column(name="MSGS_INSTGAGT_BKCD")
	public String getSenderBank() {
		return senderBank;
	}
	public void setSenderBank(String senderBank) {
		this.senderBank = senderBank;
	}
	@Column(name="MSGS_INSTDAGT_BKCD")
	public String getReceiverBank() {
		return receiverBank;
	}
	public void setReceiverBank(String receiverBank) {
		this.receiverBank = receiverBank;
	}
	@Column(name="MSGS_INTRBKSTTLMDT")
	public Timestamp getMsgValueDate() {
		return msgValueDate;
	}
	public void setMsgValueDate(Timestamp msgValueDate) {
		this.msgValueDate = msgValueDate;
	}
	@Column(name="MSGS_CDTR_NM")
	public String getBeneficiaryCustomerName() {
		return beneficiaryCustomerName;
	}
	public void setBeneficiaryCustomerName(String beneficiaryCustomerName) {
		this.beneficiaryCustomerName = beneficiaryCustomerName;
	}
	
	public String getNarration() {
		return narration;
	}
	@Column(name="MSGS_PMTID_INSTRID")
	public String getTxnReference() {
		return txnReference;
	}

	public void setTxnReference(String txnReference) {
		this.txnReference = txnReference;
	}

	public void setNarration(String narration) {
		this.narration = narration;
	}
	@Column(name="MSG_MUR")
	public String getMUR() {
		return MUR;
	}
	public void setMUR(String mUR) {
		MUR = mUR;
	}
	private String senderToFinal;
	@Column(name="MSGS_INSTRFORNXTAGT_INSTRINF")
	public String getSendertoreciverInfo() {
		return sendertoreciverInfo;
	}
	@Column(name="MSGS_INSTRFORCDTRAGT_INSTRINF")
	public String getSenderToFinal() {
		return senderToFinal;
	}
	public void setSenderToFinal(String senderToFinal) {
		this.senderToFinal = senderToFinal;
	}
	public void setSendertoreciverInfo(String sendertoreciverInfo) {
		this.sendertoreciverInfo = sendertoreciverInfo;
	}
	@Column(name="MSGS_HOSTID")
	public String getHostID() {
		return hostID;
	}
	public void setHostID(String hostID) {
		this.hostID = hostID;
	}
	
	@Column (name="MSGS_DBTR_NM")
	public String getOrderingCustomerName() {
		return orderingCustomerName;
	}
	public void setOrderingCustomerName(String orderingCustomerName) {
		this.orderingCustomerName = orderingCustomerName;
	}
	
	private static final long serialVersionUID = -6409001704113734088L;
	
	@Id
	@Column(name="MSGS_MSGREF")
	public String getMsgRef() {
		return msgRef;
	}
	public void setMsgRef(String msgRef) {
		this.msgRef = msgRef;
	}
	@Column(name="MSGS_INTRBKSTTLMCCY")
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	@Column(name="MSGS_INTRBKSTTLMAMT")
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		amount = amount;
	}
	@Column(name="MSGS_DIRECTION")
	public String getDirection() {
		return direction;
	}
	public void setDirection(String direction) {
		this.direction = direction;
	}
	@Column(name="MSGS_DEPT")
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	@Column(name="MSGS_BRANCH")
	public String getBranch() {
		return branch;
	}
	public void setBranch(String branch) {
		this.branch = branch;
	}
	@Column(name="MSGS_MSGSTS")
	public String getMsgStatus() {
		return msgStatus;
	}
	public void setMsgStatus(String msgStatus) {
		this.msgStatus = msgStatus;
	}
	@Column(name="MSGS_SRC_MSGTYPE")
	public String getMsgType() {
		return msgType;
	}
	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}
	@Column(name="MSGS_SRC_MSGSUBTYPE")
	public String getSubMsgType() {
		return subMsgType;
	}
	public void setSubMsgType(String subMsgType) {
		this.subMsgType = subMsgType;
	}
	@Column(name="MSGS_CHANNELID")
	public String getChannel() {
		return channel;
	}
	public void setChannel(String channel) {
		this.channel = channel;
	}
	
	
	
}
