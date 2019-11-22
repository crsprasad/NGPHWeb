package com.logica.ngph.utils;

import java.sql.Timestamp;
import java.util.Calendar;

import com.logica.ngph.Entity.NgphCanonical;
import com.logica.ngph.Entity.ReportEntity;



public class ReportEntityUtil {
	
	public static ReportEntity getReportEntityFromCanonical(NgphCanonical ngphCanonical){
		ReportEntity reportEntity = new ReportEntity();
		
		Timestamp currentTimestamp= getCurrentTime();
		if(ngphCanonical.getMsgAmount()!=null){
			reportEntity.setAmount(ngphCanonical.getMsgAmount());		
		}
		
		reportEntity.setBeneficiaryCustAcct(ngphCanonical.getBeneficiaryCustAcct());
		reportEntity.setBeneficiaryCustomerAddress(ngphCanonical.getBeneficiaryCustomerAddress());
		reportEntity.setBeneficiaryCustomerCtry(ngphCanonical.getBeneficiaryCustomerCtry());
		reportEntity.setBeneficiaryCustomerID(ngphCanonical.getBeneficiaryCustomerID());
		reportEntity.setBeneficiaryCustomerName(ngphCanonical.getBeneficiaryCustomerName());		
		reportEntity.setChannel(ngphCanonical.getMsgChnlType());
		reportEntity.setCurrency(ngphCanonical.getMsgCurrency());
		reportEntity.setComments(ngphCanonical.getComments());
		reportEntity.setSenderBank(ngphCanonical.getSenderBank());
		reportEntity.setDepartment(ngphCanonical.getMsgDept());
		reportEntity.setBranch(ngphCanonical.getMsgBranch());
		reportEntity.setDirection(ngphCanonical.getMsgDirection());
		//reportEntity.setDstChannelType(ngphCanonical.getm);
		reportEntity.setDstMsgSubType(ngphCanonical.getDstMsgSubType());
		reportEntity.setDstMsgType(ngphCanonical.getDstMsgType());
		//reportEntity.setErrorCode(ngphCanonical.getm);
		reportEntity.setHostID(ngphCanonical.getMsgHost());
		reportEntity.setInstructionsForNextAgtCode(ngphCanonical.getInstructionsForCrdtrAgtCode());
		reportEntity.setLastModTime(currentTimestamp);
		reportEntity.setMsgRef(ngphCanonical.getMsgRef());
		if(ngphCanonical.getMsgStatus().equals("2")){
		reportEntity.setMsgStatus("12");	
		}else{
			reportEntity.setMsgStatus(ngphCanonical.getMsgStatus())	;
		}
		reportEntity.setMsgType(ngphCanonical.getSrcMsgType());			
		reportEntity.setMsgValueDate(currentTimestamp);
		reportEntity.setMUR(ngphCanonical.getMsgMur());		
		reportEntity.setOrderingCustAccount(ngphCanonical.getOrderingCustAccount());
		reportEntity.setOrderingCustomerAddress(ngphCanonical.getOrderingCustomerAddress());
		reportEntity.setOrderingCustomerCtry(ngphCanonical.getOrderingCustomerCtry());
		reportEntity.setOrderingCustomerId(ngphCanonical.getOrderingCustomerId());
		reportEntity.setOrderingCustomerName(ngphCanonical.getOrderingCustomerName());
		reportEntity.setOrderingInstitution(ngphCanonical.getOrderingInstitution());
		reportEntity.setOrderingInstitutionAcct(ngphCanonical.getOrderingInstitutionAcct());
		reportEntity.setPrevInstructingAgentAcct(ngphCanonical.getPrevInstructingAgentAcct());
		reportEntity.setPrevInstructingBank(ngphCanonical.getPrevInstructingBank());
		reportEntity.setReceivedTime(currentTimestamp);
		reportEntity.setReceiverBank(ngphCanonical.getReceiverBank());
		
		reportEntity.setRelatedRefrence(ngphCanonical.getRelReference());
			
		reportEntity.setSenderToFinal(ngphCanonical.getInstructionsForCrdtrAgtText());
		reportEntity.setSendertoreciverInfo(ngphCanonical.getInstructionsForNextAgtText());
		reportEntity.setSubMsgType(ngphCanonical.getSrcMsgSubType());
		reportEntity.setTxnReference(ngphCanonical.getTxnReference());
		reportEntity.setTxnType(ngphCanonical.getMsgTxnType());
		reportEntity.setUltimateCreditorAddress(ngphCanonical.getUltimateCreditorAddress());
		reportEntity.setUltimateCreditorID(ngphCanonical.getUltimateCreditorID());
		reportEntity.setUltimateCreditorName(ngphCanonical.getUltimateCreditorName());
		//Mizuho :: Start :: Added for report fields
		reportEntity.setIssueDate(ngphCanonical.getLcIssueDt());
		reportEntity.setApplicentBankCode(ngphCanonical.getSendingInstId());
		reportEntity.setApplicentBankLoc(ngphCanonical.getSendingInstNameAddress());
		reportEntity.setExpDate(ngphCanonical.getLcExpDt());
		reportEntity.setLastDateofShipment(ngphCanonical.getLcLstShipDt());
		reportEntity.setAmendmentDate(ngphCanonical.getLcAmndmntDt());
		reportEntity.setNoofAmendments(ngphCanonical.getLcAmndmntNo());
		reportEntity.setReducedAmtCurrCode(ngphCanonical.getLcAdditionalCurrCode());
		reportEntity.setReducedAmt(ngphCanonical.getLcAdditionalAmt());
		reportEntity.setOutstandAmtCurrCode(ngphCanonical.getInstructedCurrency());
		reportEntity.setOutstandAmt(ngphCanonical.getLcTotalAmtClaimed());
		//Mizuho :: End :: Added for report fields
		return reportEntity;
	}

	private static Timestamp getCurrentTime(){
		java.util.Date 	str_date = Calendar.getInstance().getTime();
		java.sql.Timestamp timeStampDate = new Timestamp(str_date.getTime());
		return timeStampDate;
	}
	
}
