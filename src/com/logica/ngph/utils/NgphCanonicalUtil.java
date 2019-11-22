package com.logica.ngph.utils;

import com.logica.ngph.Entity.NgphCanonical;





public class NgphCanonicalUtil {
public static com.logica.ngph.common.dtos.NgphCanonical ngphCanonicalEntityToDto(NgphCanonical ngphCanonical){
		
		com.logica.ngph.common.dtos.NgphCanonical canonicalDto = new com.logica.ngph.common.dtos.NgphCanonical();
		
		canonicalDto.setMsgRef(ngphCanonical.getMsgRef());
		canonicalDto.setAccountWithInstitution(ngphCanonical.getAccountWithInstitution());
		canonicalDto.setAccountWithInstitutionAcct(ngphCanonical.getAccountWithInstitutionAcct());
		canonicalDto.setBeneficiaryCustAcct(ngphCanonical.getBeneficiaryCustAcct());
		canonicalDto.setBeneficiaryCustomerName(ngphCanonical.getBeneficiaryCustomerName());
		canonicalDto.setBeneficiaryCustomerAddress(ngphCanonical.getBeneficiaryCustomerAddress());
		canonicalDto.setBeneficiaryCustomerID(ngphCanonical.getBeneficiaryCustomerID());
		canonicalDto.setBaseCcyAmount(ngphCanonical.getBaseCcyAmount());
		canonicalDto.setBeneficiaryCustomerCtctDtls(ngphCanonical.getBeneficiaryCustomerCtctDtls());
		canonicalDto.setBeneficiaryType(ngphCanonical.getBeneficiaryType());
		canonicalDto.setClrgChannel(ngphCanonical.getClrgChannel());
		canonicalDto.setChargeBearer(ngphCanonical.getChargeBearer());
		canonicalDto.setCatgPurposeCode(ngphCanonical.getCatgPurposeCode());
		canonicalDto.setClsDateTime(ngphCanonical.getClsDateTime());
		canonicalDto.setClrgSysReference(ngphCanonical.getClrgSysReference());
		canonicalDto.setCustAccount(ngphCanonical.getCustAccount());
		canonicalDto.setCustTxnReference(ngphCanonical.getCustTxnReference());
		canonicalDto.setCashpoolAdjstmntTime(ngphCanonical.getCashpoolAdjstmntTime());
		canonicalDto.setCatgPurposeProperitary(ngphCanonical.getCatgPurposePriority());
		canonicalDto.setComments(ngphCanonical.getComments());
		canonicalDto.setCrCurrency(ngphCanonical.getCrCurrency());
		canonicalDto.setCrDateTime(ngphCanonical.getCrDateTime());
		canonicalDto.setCustAccount(ngphCanonical.getCustAccount());
		canonicalDto.setCustTxnReference(ngphCanonical.getCustTxnReference());
		canonicalDto.setDrDateTime(ngphCanonical.getDrDateTime());
		canonicalDto.setDstMsgSubType(ngphCanonical.getDstMsgSubType());
		canonicalDto.setDstMsgType(ngphCanonical.getDstMsgType());
		canonicalDto.setDrCurrency(ngphCanonical.getDrCurrency());
		canonicalDto.setGrpMsgId(ngphCanonical.getGrpMsgId());
		if(ngphCanonical.getGrpSeq()!= null){
			canonicalDto.setGrpSeq(ngphCanonical.getGrpSeq());
		}
		
		canonicalDto.setInitiatorRemitAdviceMethod(ngphCanonical.getInitiatorRemitAdviceMethod());
		canonicalDto.setInitiatorRemitReference(ngphCanonical.getInitiatorRemitReference());
		canonicalDto.setInstructedAmount(ngphCanonical.getInstructedAmount());
		canonicalDto.setInstructedCurrency(ngphCanonical.getInstructedCurrency());
		canonicalDto.setInstructionsForCrdtrAgtCode(ngphCanonical.getInstructionsForCrdtrAgtCode());
		canonicalDto.setInstructionsForCrdtrAgtText(ngphCanonical.getInstructionsForCrdtrAgtText());
		canonicalDto.setInstructionsForNextAgtCode(ngphCanonical.getInstructionsForNextAgtCode());
		canonicalDto.setInstructionsForNextAgtText(ngphCanonical.getInstructionsForNextAgtText());
		canonicalDto.setIntermediary1AgentAcct(ngphCanonical.getIntermediary1AgentAcct());
		canonicalDto.setIntermediary1Bank(ngphCanonical.getIntermediary1Bank());
		canonicalDto.setIntermediary2AgentAcct(ngphCanonical.getIntermediary2AgentAcct());
		canonicalDto.setIntermediary2Bank(ngphCanonical.getIntermediary2Bank());
		canonicalDto.setIntermediary3AgentAcct(ngphCanonical.getIntermediary3AgentAcct());
		canonicalDto.setIntermediary3Bank(ngphCanonical.getIntermediary3Bank());
		canonicalDto.setInitiatingPartyName(ngphCanonical.getInitiatingPartyName());
		canonicalDto.setInitiatingPartyAddress(ngphCanonical.getInitiatingPartyAddress());
		canonicalDto.setInitiatingPartyID(ngphCanonical.getInitiatingPartyID());
		canonicalDto.setInitiatingPartyCtry(ngphCanonical.getInitiatingPartyCtry());
		canonicalDto.setInitiatingPartyCtctDtls(ngphCanonical.getInitiatingPartyCtctDtls());
		canonicalDto.setInstructedCcyAmount(ngphCanonical.getInstructedCcyAmount());
		canonicalDto.setIntermediary1BankName(ngphCanonical.getIntermediary1BankName());
		canonicalDto.setIntermediary2BankName(ngphCanonical.getIntermediary2BankName());
		canonicalDto.setIntermediary3BankName(ngphCanonical.getIntermediary3BankName());
		canonicalDto.setLclInstCode(ngphCanonical.getLclInstCode());
		canonicalDto.setLastModifiedUser(ngphCanonical.getLastModifiedUser());
		canonicalDto.setLclInstProperitary(ngphCanonical.getLclInstPriority());
		canonicalDto.setMsgDept(ngphCanonical.getMsgDept());
		canonicalDto.setMsgPrevStatus(ngphCanonical.getMsgPrevStatus());
		canonicalDto.setMsgStatus(ngphCanonical.getMsgStatus());
		canonicalDto.setMsgAmount(ngphCanonical.getMsgAmount());
		canonicalDto.setMsgBatchTime(ngphCanonical.getMsgBatchTime());
		canonicalDto.setMsgBranch(ngphCanonical.getMsgBranch());
		canonicalDto.setMsgChannel(ngphCanonical.getMsgChannel());
		canonicalDto.setMsgChnlType(ngphCanonical.getMsgChnlType());
		canonicalDto.setMsgCurrency(ngphCanonical.getMsgCurrency());
		canonicalDto.setMsgCurrencyAmount(ngphCanonical.getMsgCurrencyAmount());
		canonicalDto.setMsgDirection(ngphCanonical.getMsgDirection());
		//canonicalDto.setMsgErrorCode(ngphCanonical.getMsgErrorCode());
		canonicalDto.setMsgHost(ngphCanonical.getMsgHost());
		canonicalDto.setMsgMur(ngphCanonical.getMsgMur());
		canonicalDto.setMsgPurposeCode(ngphCanonical.getMsgPurposeCode());
		canonicalDto.setMsgPurposeText(ngphCanonical.getMsgPurposeText());
		canonicalDto.setMsgReturnReference(ngphCanonical.getMsgReturnReference());
		canonicalDto.setMsgRules(ngphCanonical.getMsgRules());
		canonicalDto.setMsgValueDate(ngphCanonical.getMsgValueDate());
		canonicalDto.setMsgHost(ngphCanonical.getMsgHost());
		canonicalDto.setMsgPurposeCode(ngphCanonical.getMsgPurposeCode());
		canonicalDto.setMsgRules(ngphCanonical.getMsgRules());
		canonicalDto.setMsgReturnReference(ngphCanonical.getMsgReturnReference());
		canonicalDto.setOrderingCustAccount(ngphCanonical.getOrderingCustAccount());
		canonicalDto.setOrderingInstitution(ngphCanonical.getOrderingInstitution());
		canonicalDto.setOrderingInstitutionAcct(ngphCanonical.getOrderingInstitutionAcct());
		canonicalDto.setOrderingType(ngphCanonical.getOrderingType());
		canonicalDto.setOrderingCustomerName(ngphCanonical.getOrderingCustomerName());
		canonicalDto.setOrderingCustomerAddress(ngphCanonical.getOrderingCustomerAddress());
		canonicalDto.setOrderingCustomerCtry(ngphCanonical.getOrderingCustomerCtry());
		canonicalDto.setOrderingCustomerId(ngphCanonical.getOrderingCustomerId());
		canonicalDto.setOrderingCustomerCtctDtls(ngphCanonical.getOrderingCustomerCtctDtls());
		canonicalDto.setOrderingCustomerId(ngphCanonical.getOrderingCustomerId());
		canonicalDto.setOrderingType(ngphCanonical.getOrderingType());
		canonicalDto.setPrevInstructingAgentAcct(ngphCanonical.getPrevInstructingAgentAcct());
		canonicalDto.setPrevInstructingBank(ngphCanonical.getPrevInstructingBank());
		canonicalDto.setPymntAcceptedTime(ngphCanonical.getPymntAcceptedTime());
		canonicalDto.setReceiverBank(ngphCanonical.getReceiverBank());
		canonicalDto.setRegulatoryBankCode(ngphCanonical.getRegulatoryBankCode());
		canonicalDto.setRegulatoryInformation(ngphCanonical.getRegulatoryInformation());
		canonicalDto.setRegulatoryReportAmount(ngphCanonical.getRegulatoryReportAmount());
		canonicalDto.setRegulatoryReportCurrency(ngphCanonical.getRegulatoryReportCurrency());
		canonicalDto.setRelRemitInfoRef(ngphCanonical.getRelatedRemitInfo());
		canonicalDto.setRelReference(ngphCanonical.getRelReference());
		canonicalDto.setRemitInfoEmail(ngphCanonical.getRemitInfoEmail());
		canonicalDto.setRemitReceivingPartyAddress(ngphCanonical.getRemitReceivingPartyAddress());
		canonicalDto.setRemitReceivingPartyName(ngphCanonical.getRemitReceivingPartyName());
		canonicalDto.setReceivedTime(ngphCanonical.getReceivedTime());
		canonicalDto.setReceiverCorrespondent(ngphCanonical.getReceiverCorrespondent());
		canonicalDto.setReceiverCorrespondentAcct(ngphCanonical.getReceiverCorrespondentAcct());
		canonicalDto.setRegulatoryReportDrCr(ngphCanonical.getRegulatoryReportDrCr());
		
		canonicalDto.setRelUid(ngphCanonical.getRelUid());
		canonicalDto.setRemitInfoRef(ngphCanonical.getRemtInfoRef());
		canonicalDto.setSenderBank(ngphCanonical.getSenderBank());
		canonicalDto.setSndrPymtPriority(ngphCanonical.getSndrPymtPriority());
		canonicalDto.setSndrSttlmntPriority(ngphCanonical.getSndrSttlmntPriority());
		canonicalDto.setSndrTxnId(ngphCanonical.getSndrTxnId());
		canonicalDto.setSrcMsgSubType(ngphCanonical.getSrcMsgSubType());
		canonicalDto.setSrcMsgType(ngphCanonical.getSrcMsgType());
		canonicalDto.setSvcLevelCode(ngphCanonical.getSvcLevelCode());
		canonicalDto.setSttlmntFromTime(ngphCanonical.getSttlmntFromTime());
		canonicalDto.setSttlmntRejTime(ngphCanonical.getSttlmntRejTime());
		canonicalDto.setSttlmntTillTime(ngphCanonical.getSttlmntTillTime());
		
		canonicalDto.setSenderCorrespondent(ngphCanonical.getSenderCorrespondent());
		canonicalDto.setSenderCorrespondentAcct(ngphCanonical.getSenderCorrespondentAcct());
		canonicalDto.setServiceID(ngphCanonical.getServiceID());
		canonicalDto.setSvcLevelProperitary(ngphCanonical.getSvcLevelPriority());
		canonicalDto.setTxnReference(ngphCanonical.getTxnReference());
		canonicalDto.setThirdCorrespondent(ngphCanonical.getThirdCorrespondent());
		canonicalDto.setThirdCorrespondentAcct(ngphCanonical.getThirdCorrespondentAcct());
		canonicalDto.setUltimateCreditorName(ngphCanonical.getUltimateCreditorName());
		canonicalDto.setUltimateDebtorName(ngphCanonical.getUltimateDebtorName());
		canonicalDto.setUltimateDebtorAddress(ngphCanonical.getUltimateDebtorAddress());
		canonicalDto.setUltimateDebtorID(ngphCanonical.getUltimateDebtorID());
		canonicalDto.setUltimateDebtorCtry(ngphCanonical.getUltimateDebtorCtry());
		canonicalDto.setUltimateDebtorCtctDtls(ngphCanonical.getUltimateDebtorCtctDtls());
		canonicalDto.setUltimateCreditorCtry(ngphCanonical.getUltimateCreditorCtry());
		canonicalDto.setUltimateCreditorCtctDtls(ngphCanonical.getUltimateCreditorCtctDtls());
		canonicalDto.setUltimateCreditorAddress(ngphCanonical.getUltimateCreditorAddress());
		canonicalDto.setUltimateCreditorID(ngphCanonical.getUltimateCreditorID());
		canonicalDto.setXchangeRate(ngphCanonical.getXchangeRate());
		
				
		return canonicalDto;
		
	}
}
