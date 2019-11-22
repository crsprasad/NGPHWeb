package com.logica.ngph.serviceImpl;


import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;



import com.logica.ngph.Entity.ModifiedMessage;

import com.logica.ngph.common.NGPHException;
import com.logica.ngph.common.dtos.NgphCanonical;
import com.logica.ngph.dao.PaymentDao;
import com.logica.ngph.dtos.ModifiedMessagesDto;

import com.logica.ngph.service.PaymentService;



public class PaymentServiceImpl implements PaymentService{
	static Logger logger = Logger.getLogger(PaymentServiceImpl.class);
	PaymentDao paymentDao;
	public PaymentDao getPaymentDao() {
		return paymentDao;
	}
	public void setPaymentDao(PaymentDao paymentDao) {
		this.paymentDao = paymentDao;
	}
	

	/**
	* This method is used to get the message support 
	* @return  Map<String, List<String>> 
	*/
	public Map<String, List<String>> getMsgSupport(){
		
		
		Map<String , List<String>> messageMap = paymentDao.getMsgSupport();
		
		//Map<String , List<String>> messageMap = new HashMap<String, List<String>>();
		
		return messageMap;
	}
	
	/**
	* This method is used to save the payment entry screen data
	* @return  void
	*/
	
	public void savePayment(NgphCanonical ngphCanonical) {
			paymentDao.savePayment(setDTOCanonicalTOEntity( ngphCanonical));
	}
	
	/**
	* This method is used to set the data from DTO canonical into Entity Canonical
	* @return  com.logica.ngph.Entity.NgphCanonical entityCanonical
	*/
	private com.logica.ngph.Entity.NgphCanonical setDTOCanonicalTOEntity(NgphCanonical ngphCanonical){
		
		com.logica.ngph.Entity.NgphCanonical entityCanonical = new com.logica.ngph.Entity.NgphCanonical();
		entityCanonical.setMsgRef(ngphCanonical.getMsgRef());
		entityCanonical.setAccountWithInstitution(ngphCanonical.getAccountWithInstitution());
		entityCanonical.setAccountWithInstitutionAcct(ngphCanonical.getAccountWithInstitutionAcct());
		entityCanonical.setBeneficiaryCustAcct(ngphCanonical.getBeneficiaryCustAcct());
		entityCanonical.setBeneficiaryCustomerName(ngphCanonical.getBeneficiaryCustomerName());
		entityCanonical.setBeneficiaryCustomerAddress(ngphCanonical.getBeneficiaryCustomerAddress());
		entityCanonical.setBeneficiaryCustomerID(ngphCanonical.getBeneficiaryCustomerID());
		entityCanonical.setBaseCcyAmount(ngphCanonical.getBaseCcyAmount());
		entityCanonical.setBeneficiaryCustomerCtctDtls(ngphCanonical.getBeneficiaryCustomerCtctDtls());
		entityCanonical.setBeneficiaryType(ngphCanonical.getBeneficiaryType());
		entityCanonical.setClrgChannel(ngphCanonical.getClrgChannel());
		entityCanonical.setChargeBearer(ngphCanonical.getChargeBearer());
		entityCanonical.setCatgPurposeCode(ngphCanonical.getCatgPurposeCode());
		entityCanonical.setClsDateTime(ngphCanonical.getClsDateTime());
		entityCanonical.setClrgSysReference(ngphCanonical.getClrgSysReference());
		entityCanonical.setCustAccount(ngphCanonical.getCustAccount());
		entityCanonical.setCustTxnReference(ngphCanonical.getCustTxnReference());
		entityCanonical.setCashpoolAdjstmntTime(ngphCanonical.getCashpoolAdjstmntTime());
		entityCanonical.setCatgPurposePriority(ngphCanonical.getCatgPurposeProperitary());
		entityCanonical.setComments(ngphCanonical.getComments());
		entityCanonical.setCrCurrency(ngphCanonical.getCrCurrency());
		entityCanonical.setCrDateTime(ngphCanonical.getCrDateTime());
		entityCanonical.setCustAccount(ngphCanonical.getCustAccount());
		entityCanonical.setCustTxnReference(ngphCanonical.getCustTxnReference());
		entityCanonical.setDrDateTime(ngphCanonical.getDrDateTime());
		entityCanonical.setDstMsgSubType(ngphCanonical.getDstMsgSubType());
		entityCanonical.setDstMsgType(ngphCanonical.getDstMsgType());
		entityCanonical.setDrCurrency(ngphCanonical.getDrCurrency());
		entityCanonical.setGrpMsgId(ngphCanonical.getGrpMsgId());
		entityCanonical.setGrpSeq(ngphCanonical.getGrpSeq());
		entityCanonical.setInitiatorRemitAdviceMethod(ngphCanonical.getInitiatorRemitAdviceMethod());
		entityCanonical.setInitiatorRemitReference(ngphCanonical.getInitiatorRemitReference());
		entityCanonical.setInstructedAmount(ngphCanonical.getInstructedAmount());
		entityCanonical.setInstructedCurrency(ngphCanonical.getInstructedCurrency());
		entityCanonical.setInstructionsForCrdtrAgtCode(ngphCanonical.getInstructionsForCrdtrAgtCode());
		entityCanonical.setInstructionsForCrdtrAgtText(ngphCanonical.getInstructionsForCrdtrAgtText());
		entityCanonical.setInstructionsForNextAgtCode(ngphCanonical.getInstructionsForNextAgtCode());
		entityCanonical.setInstructionsForNextAgtText(ngphCanonical.getInstructionsForNextAgtText());
		entityCanonical.setIntermediary1AgentAcct(ngphCanonical.getIntermediary1AgentAcct());
		entityCanonical.setIntermediary1Bank(ngphCanonical.getIntermediary1Bank());
		entityCanonical.setIntermediary2AgentAcct(ngphCanonical.getIntermediary2AgentAcct());
		entityCanonical.setIntermediary2Bank(ngphCanonical.getIntermediary2Bank());
		entityCanonical.setIntermediary3AgentAcct(ngphCanonical.getIntermediary3AgentAcct());
		entityCanonical.setIntermediary3Bank(ngphCanonical.getIntermediary3Bank());
		entityCanonical.setInitiatingPartyName(ngphCanonical.getInitiatingPartyName());
		entityCanonical.setInitiatingPartyAddress(ngphCanonical.getInitiatingPartyAddress());
		entityCanonical.setInitiatingPartyID(ngphCanonical.getInitiatingPartyID());
		entityCanonical.setInitiatingPartyCtry(ngphCanonical.getInitiatingPartyCtry());
		entityCanonical.setInitiatingPartyCtctDtls(ngphCanonical.getInitiatingPartyCtctDtls());
		entityCanonical.setInstructedCcyAmount(ngphCanonical.getInstructedCcyAmount());
		entityCanonical.setIntermediary1BankName(ngphCanonical.getIntermediary1BankName());
		entityCanonical.setIntermediary2BankName(ngphCanonical.getIntermediary2BankName());
		entityCanonical.setIntermediary3BankName(ngphCanonical.getIntermediary3BankName());
		entityCanonical.setLclInstCode(ngphCanonical.getLclInstCode());
		entityCanonical.setLastModifiedUser(ngphCanonical.getLastModifiedUser());
		entityCanonical.setLclInstPriority(ngphCanonical.getLclInstProperitary());
		entityCanonical.setMsgDept(ngphCanonical.getMsgDept());
		entityCanonical.setMsgPrevStatus(ngphCanonical.getMsgPrevStatus());
		entityCanonical.setMsgStatus(ngphCanonical.getMsgStatus());
		entityCanonical.setMsgAmount(ngphCanonical.getMsgAmount());
		entityCanonical.setMsgBatchTime(ngphCanonical.getMsgBatchTime());
		entityCanonical.setMsgBranch(ngphCanonical.getMsgBranch());
		entityCanonical.setMsgChannel(ngphCanonical.getMsgChannel());
		entityCanonical.setMsgChnlType(ngphCanonical.getMsgChnlType());
		entityCanonical.setMsgCurrency(ngphCanonical.getMsgCurrency());
		entityCanonical.setMsgCurrencyAmount(ngphCanonical.getMsgCurrencyAmount());
		entityCanonical.setMsgDirection(ngphCanonical.getMsgDirection());
		//entityCanonical.setMsgErrorCode(ngphCanonical.getMsgErrorCode());
		entityCanonical.setMsgHost(ngphCanonical.getMsgHost());
		entityCanonical.setMsgMur(ngphCanonical.getMsgMur());
		entityCanonical.setMsgPurposeCode(ngphCanonical.getMsgPurposeCode());
		entityCanonical.setMsgPurposeText(ngphCanonical.getMsgPurposeText());
		entityCanonical.setMsgReturnReference(ngphCanonical.getMsgReturnReference());
		entityCanonical.setMsgRules(ngphCanonical.getMsgRules());
		entityCanonical.setMsgValueDate(ngphCanonical.getMsgValueDate());
		entityCanonical.setMsgHost(ngphCanonical.getMsgHost());
		entityCanonical.setMsgPurposeCode(ngphCanonical.getMsgPurposeCode());
		entityCanonical.setMsgRules(ngphCanonical.getMsgRules());
		entityCanonical.setMsgReturnReference(ngphCanonical.getMsgReturnReference());
		entityCanonical.setOrderingCustAccount(ngphCanonical.getOrderingCustAccount());
		entityCanonical.setOrderingInstitution(ngphCanonical.getOrderingInstitution());
		entityCanonical.setOrderingInstitutionAcct(ngphCanonical.getOrderingInstitutionAcct());
		entityCanonical.setOrderingType(ngphCanonical.getOrderingType());
		entityCanonical.setOrderingCustomerName(ngphCanonical.getOrderingCustomerName());
		entityCanonical.setOrderingCustomerAddress(ngphCanonical.getOrderingCustomerAddress());
		entityCanonical.setOrderingCustomerCtry(ngphCanonical.getOrderingCustomerCtry());
		entityCanonical.setOrderingCustomerId(ngphCanonical.getOrderingCustomerId());
		entityCanonical.setOrderingCustomerCtctDtls(ngphCanonical.getOrderingCustomerCtctDtls());
		entityCanonical.setOrderingCustomerId(ngphCanonical.getOrderingCustomerId());
		entityCanonical.setOrderingType(ngphCanonical.getOrderingType());
		entityCanonical.setPrevInstructingAgentAcct(ngphCanonical.getPrevInstructingAgentAcct());
		entityCanonical.setPrevInstructingBank(ngphCanonical.getPrevInstructingBank());
		entityCanonical.setPymntAcceptedTime(ngphCanonical.getPymntAcceptedTime());
		entityCanonical.setReceiverBank(ngphCanonical.getReceiverBank());
		entityCanonical.setRegulatoryBankCode(ngphCanonical.getRegulatoryBankCode());
		entityCanonical.setRegulatoryInformation(ngphCanonical.getRegulatoryInformation());
		entityCanonical.setRegulatoryReportAmount(ngphCanonical.getRegulatoryReportAmount());
		entityCanonical.setRegulatoryReportCurrency(ngphCanonical.getRegulatoryReportCurrency());
		entityCanonical.setRelReference(ngphCanonical.getRelReference());
		entityCanonical.setRemitInfoEmail(ngphCanonical.getRemitInfoEmail());
		entityCanonical.setRemitReceivingPartyAddress(ngphCanonical.getRemitReceivingPartyAddress());
		entityCanonical.setRemitReceivingPartyName(ngphCanonical.getRemitReceivingPartyName());
		entityCanonical.setReceivedTime(ngphCanonical.getReceivedTime());
		entityCanonical.setReceiverCorrespondent(ngphCanonical.getReceiverCorrespondent());
		entityCanonical.setReceiverCorrespondentAcct(ngphCanonical.getReceiverCorrespondentAcct());
		entityCanonical.setRegulatoryReportDrCr(ngphCanonical.getRegulatoryReportDrCr());
		entityCanonical.setRelatedRemitInfo(ngphCanonical.getRelRemitInfoRef());
		entityCanonical.setRelUid(ngphCanonical.getRelUid());
		entityCanonical.setRemtInfoRef(ngphCanonical.getRemitInfoRef());
		entityCanonical.setSenderBank(ngphCanonical.getSenderBank());
		entityCanonical.setSndrPymtPriority(ngphCanonical.getSndrPymtPriority());
		entityCanonical.setSndrSttlmntPriority(ngphCanonical.getSndrSttlmntPriority());
		entityCanonical.setSndrTxnId(ngphCanonical.getSndrTxnId());
		entityCanonical.setSrcMsgSubType(ngphCanonical.getSrcMsgSubType());
		entityCanonical.setSrcMsgType(ngphCanonical.getSrcMsgType());
		entityCanonical.setSvcLevelCode(ngphCanonical.getSvcLevelCode());
		entityCanonical.setSttlmntFromTime(ngphCanonical.getSttlmntFromTime());
		entityCanonical.setSttlmntRejTime(ngphCanonical.getSttlmntRejTime());
		entityCanonical.setSttlmntTillTime(ngphCanonical.getSttlmntTillTime());
		entityCanonical.setSenderCorrespondent(ngphCanonical.getSenderCorrespondent());
		entityCanonical.setSenderCorrespondentAcct(ngphCanonical.getSenderCorrespondentAcct());
		entityCanonical.setServiceID(ngphCanonical.getServiceID());
		entityCanonical.setSvcLevelPriority(ngphCanonical.getSvcLevelProperitary());
		entityCanonical.setTxnReference(ngphCanonical.getTxnReference());
		entityCanonical.setThirdCorrespondent(ngphCanonical.getThirdCorrespondent());
		entityCanonical.setThirdCorrespondentAcct(ngphCanonical.getThirdCorrespondentAcct());
		entityCanonical.setUltimateCreditorName(ngphCanonical.getUltimateCreditorName());
		entityCanonical.setUltimateDebtorName(ngphCanonical.getUltimateDebtorName());
		entityCanonical.setUltimateDebtorAddress(ngphCanonical.getUltimateDebtorAddress());
		entityCanonical.setUltimateDebtorID(ngphCanonical.getUltimateDebtorID());
		entityCanonical.setUltimateDebtorCtry(ngphCanonical.getUltimateDebtorCtry());
		entityCanonical.setUltimateDebtorCtctDtls(ngphCanonical.getUltimateDebtorCtctDtls());
		entityCanonical.setUltimateCreditorCtry(ngphCanonical.getUltimateCreditorCtry());
		entityCanonical.setUltimateCreditorCtctDtls(ngphCanonical.getUltimateCreditorCtctDtls());
		entityCanonical.setUltimateCreditorAddress(ngphCanonical.getUltimateCreditorAddress());
		entityCanonical.setUltimateCreditorID(ngphCanonical.getUltimateCreditorID());
		entityCanonical.setXchangeRate(ngphCanonical.getXchangeRate());
		entityCanonical.setLastModTime(getCurrentTime());
		return entityCanonical;
		//entityCanonical.setChargeAmount(ngphCanonical.getMsgAmount());
	}
	
	private Timestamp getCurrentTime(){
		java.util.Date 	str_date = Calendar.getInstance().getTime();
		java.sql.Timestamp timeStampDate = new Timestamp(str_date.getTime());
		return timeStampDate;
	}
	
	/**
	* This method is used to get the payment Queue data from DB based on the Message Reference
	* @return  NgphCanonical canonicalDTO
	*/
	public NgphCanonical getPayment(String msgReference) throws NGPHException {
		NgphCanonical ngphCanonical = null;
		try {
		 ngphCanonical = 	paymentDao.getPayment(msgReference);
		} catch (SQLException sqlException) {
			logger.debug(sqlException);
		}
		return ngphCanonical;
	}
	/**
	* This method is used to save the repaired/modified data into TA_MODIFIEDMESSAGES table
	*/
	
	@Transactional(propagation = Propagation.REQUIRED)
	public void savePaymentRepair(ModifiedMessagesDto modifiedMessagesDto) {
		
	ModifiedMessage modifiedMessage = new ModifiedMessage();
	
	modifiedMessage.setMsgsRef(modifiedMessagesDto.getMsgsRef());
	//modifiedMessage.setMsgsRepairId(modifiedMessagesDto.getMsgsRepairId());
	modifiedMessage.setRepairComment(modifiedMessagesDto.getRepairComment());
	modifiedMessage.setModifiedUser(modifiedMessagesDto.getModifiedUser());
	modifiedMessage.setChangedOriginalValues(modifiedMessagesDto.getChangedOriginalValues());
	modifiedMessage.setChangedRepairedValues(modifiedMessagesDto.getChangedRepairedValues());	
		
	try {
		paymentDao.saveRepairedPayment(modifiedMessage);
	} catch (SQLException sqlException) {
		logger.debug(sqlException);
	
	}
		
	}
	

}