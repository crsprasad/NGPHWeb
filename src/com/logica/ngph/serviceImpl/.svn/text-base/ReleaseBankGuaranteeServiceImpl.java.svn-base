package com.logica.ngph.serviceImpl;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.logica.ngph.Entity.BgMast;
import com.logica.ngph.Entity.MsgPolled;
import com.logica.ngph.Entity.NgphCanonical;
import com.logica.ngph.common.enums.EnumBgStatus;
import com.logica.ngph.common.utils.NGPHUtil;
import com.logica.ngph.dao.ReleaseBankGuaranteeDao;
import com.logica.ngph.dtos.ReleaseBankGuaranteeDto;
import com.logica.ngph.service.ReleaseBankGuaranteeService;

public class ReleaseBankGuaranteeServiceImpl implements ReleaseBankGuaranteeService {	

	static Logger logger = Logger.getLogger(CreateBankGuaranteeServiceImpl.class);
	ReleaseBankGuaranteeDao releaseBankGuaranteeDao;	
	
	public ReleaseBankGuaranteeDao getReleaseBankGuaranteeDao() {
		return releaseBankGuaranteeDao;
	}
	public void setReleaseBankGuaranteeDao(
			ReleaseBankGuaranteeDao releaseBankGuaranteeDao) {
		this.releaseBankGuaranteeDao = releaseBankGuaranteeDao;
	}

	@Transactional(propagation=Propagation.REQUIRED)
	public ReleaseBankGuaranteeDto getReleaseBankGuaranteeData(
			String bgNumber) throws Exception{
		ReleaseBankGuaranteeDto releaseBankGuaranteeDto = null;
		NgphCanonical canonicalData = null;
		BgMast bgMastData = releaseBankGuaranteeDao.getBgMast(bgNumber);
		if(bgMastData!=null && bgMastData.getBgStatus()!=null && bgMastData.getBgStatus()!=Integer.parseInt(EnumBgStatus.RELEASED.getValue())){
			canonicalData = releaseBankGuaranteeDao.getCanonical(bgMastData.getMsgRef());
		}
		if(canonicalData!=null){
			releaseBankGuaranteeDto = new ReleaseBankGuaranteeDto();
			releaseBankGuaranteeDto.setBgNumber(bgMastData.getBgNumber());
			releaseBankGuaranteeDto.setReleaseReqRef(canonicalData.getRelReference());
			releaseBankGuaranteeDto.setBgStatus(bgMastData.getBgStatus());
			releaseBankGuaranteeDto.setAccountNumber(canonicalData.getLcAccId());
			releaseBankGuaranteeDto.setChargeAmount(canonicalData.getLcChgsClaimed());
			releaseBankGuaranteeDto.setBgAmount(canonicalData.getMsgAmount());
			releaseBankGuaranteeDto.setBgAmountDesc(canonicalData.getLcAddlAmts());
			releaseBankGuaranteeDto.setAcctWithPartyIdentifier1(canonicalData.getAccountWithInstitutionId());			
			releaseBankGuaranteeDto.setAcctWithPartyIdentifier2(canonicalData.getAccountWithInstitutionAccount());
			releaseBankGuaranteeDto.setAcctWithPartyIFSC(canonicalData.getAccountWithInstitution());
			releaseBankGuaranteeDto.setAcctWithPartyLoc(canonicalData.getAccountWithInstitutionLoc());
			releaseBankGuaranteeDto.setAcctWithNameAndAddr(canonicalData.getAccountWithInstitutionName());
			releaseBankGuaranteeDto.setChargesDetails(canonicalData.getLcCharges());
			if(canonicalData.getInstructionsForCrdtrAgtCode()!=null && !(canonicalData.getInstructionsForCrdtrAgtCode().isEmpty())){
				releaseBankGuaranteeDto.setSenderToReceiverInformation(canonicalData.getInstructionsForCrdtrAgtCode());
			}else{
				releaseBankGuaranteeDto.setSenderToReceiverInformation(canonicalData.getInstructionsForCrdtrAgtText());
			}			
		}
		
		if(bgMastData!=null && bgMastData.getBgStatus()!=null && bgMastData.getBgStatus()==Integer.parseInt(EnumBgStatus.RELEASED.getValue())){
			releaseBankGuaranteeDto = new ReleaseBankGuaranteeDto();
			releaseBankGuaranteeDto.setBgNumber(bgMastData.getBgNumber());
			releaseBankGuaranteeDto.setBgStatus(bgMastData.getBgStatus());
		}
		return releaseBankGuaranteeDto;
	}

	@Transactional(propagation=Propagation.REQUIRED)
	public void releaseOrCreateBankGuarantee(ReleaseBankGuaranteeDto releaseBankGuaranteeDto, String userId, String txnRef) throws Exception{
			String messageReference = NGPHUtil.generateUUID();
			NgphCanonical ngphCanonical = setDtoToCanonicalObject(releaseBankGuaranteeDto, messageReference, userId);
			MsgPolled msgPolled = getEntityObject(messageReference);
			releaseBankGuaranteeDao.releaseOrCreateBankGuarantee(releaseBankGuaranteeDto, ngphCanonical, msgPolled, txnRef, messageReference);		
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	public NgphCanonical setDtoToCanonicalObject(ReleaseBankGuaranteeDto releaseBankGuaranteeDto, String messageReference, String userId) throws Exception{
		NgphCanonical ngphCanonical = new NgphCanonical();		
		String bgNumber = releaseBankGuaranteeDto.getBgNumber();
		
		ngphCanonical.setMsgRef(messageReference);		
		ngphCanonical.setMsgHost("9999");
		ngphCanonical.setMsgChnlType("SFMS");
		ngphCanonical.setSrcMsgType("760");
		ngphCanonical.setSrcMsgSubType("XXX");
		ngphCanonical.setMsgStatus("1");
		ngphCanonical.setMsgDirection("O");		
		ngphCanonical.setReceivedTime(getCurrentTime());
		ngphCanonical.setLastModTime(getCurrentTime());
		ngphCanonical.setTxnReference(bgNumber);
		ngphCanonical.setSndrTxnId(bgNumber);
		ngphCanonical.setClrgSysReference(bgNumber);
		ngphCanonical.setSndrPymtPriority("12");
		ngphCanonical.setClrgChannel("SFMS");
		ngphCanonical.setDstMsgType("768");
		ngphCanonical.setDstMsgSubType("XXX");
		
		
		String[] branchDepartmentIfsc = releaseBankGuaranteeDao.getDept(userId).split("~");
		ngphCanonical.setLastModifiedUser(userId);
		ngphCanonical.setMsgBranch(branchDepartmentIfsc[2]);
		ngphCanonical.setMsgDept(branchDepartmentIfsc[1]);
		ngphCanonical.setSenderBank(branchDepartmentIfsc[0]);
		
		ngphCanonical.setLcAccId(releaseBankGuaranteeDto.getAccountNumber());
		ngphCanonical.setLcExpDt(convertDateToTimestamp(releaseBankGuaranteeDto.getDateOfRelease()));
		ngphCanonical.setLcChgsClaimed(releaseBankGuaranteeDto.getChargeAmount());
		ngphCanonical.setMsgAmount(releaseBankGuaranteeDto.getBgAmount());
		ngphCanonical.setLcAddlAmts(releaseBankGuaranteeDto.getBgAmountDesc());
		ngphCanonical.setAccountWithInstitutionId(releaseBankGuaranteeDto.getAcctWithPartyIdentifier1());
		ngphCanonical.setAccountWithInstitutionAccount(releaseBankGuaranteeDto.getAcctWithPartyIdentifier2());
		ngphCanonical.setAccountWithInstitution(releaseBankGuaranteeDto.getAcctWithPartyIFSC());
		ngphCanonical.setAccountWithInstitutionLoc(releaseBankGuaranteeDto.getAcctWithPartyLoc());
		ngphCanonical.setAccountWithInstitutionName(releaseBankGuaranteeDto.getAcctWithNameAndAddr());
		ngphCanonical.setLcCharges(releaseBankGuaranteeDto.getChargesDetails());
		ngphCanonical.setRelReference(releaseBankGuaranteeDto.getReleaseReqRef());
				
		String senderToReciever=releaseBankGuaranteeDto.getSenderToReceiverInformation();
		String senderToReceiverInformation="";
		if(senderToReciever!=null && !senderToReciever.isEmpty()){
			String [] strs = senderToReciever.split("\\/");
			if(strs.length==3){
				senderToReceiverInformation= strs[1];
				ngphCanonical.setInstructionsForCrdtrAgtCode(senderToReceiverInformation);
			}else{
				senderToReceiverInformation=senderToReciever;
				ngphCanonical.setInstructionsForCrdtrAgtText(senderToReceiverInformation);
			}
		}
		
		
		return ngphCanonical;
	}
	
	private Timestamp getCurrentTime(){
		java.util.Date 	str_date = Calendar.getInstance().getTime();
		java.sql.Timestamp timeStampDate = new Timestamp(str_date.getTime());
		return timeStampDate;
	}
	
	public MsgPolled getEntityObject(String msgRef){
		MsgPolled msgPolled = new MsgPolled();
		msgPolled.setInTime(new Timestamp(Calendar.getInstance().getTime().getTime()));
		msgPolled.setMsgRef(msgRef);
		msgPolled.setPolledStatus("P");
		return msgPolled;
	}
	
	private Timestamp convertDateToTimestamp(Date date) {
		if(date != null){
			try{
				return new Timestamp(date.getTime());
			}catch(Exception ex){
				ex.printStackTrace();
				return null;
			}
		}else {
			return null;
		}
	}	
}
