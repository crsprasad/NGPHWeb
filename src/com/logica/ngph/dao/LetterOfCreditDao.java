package com.logica.ngph.dao;

import java.util.List;

import com.logica.ngph.Entity.LcCommodity;
import com.logica.ngph.Entity.LcMast;
import com.logica.ngph.Entity.MsgPolled;
import com.logica.ngph.Entity.NgphCanonical;
import com.logica.ngph.Entity.Parties;
import com.logica.ngph.Entity.ReportEntity;
import com.logica.ngph.dtos.LCCanonicalDto;
import com.logica.ngph.dtos.PartyDTO;

public interface LetterOfCreditDao {
	public void saveLCCommodity(LcCommodity commodity,String msgRef,String lcNumber);
	
	public void saveLCMast(LcMast lcMast);
	
	public void saveLCCanonical(NgphCanonical canonical);
	
	public List<Parties> getIFSCCode(String ifscCode,String userID);
	
	public List<Parties> getIFSCCodeList(String ifscCode,String flag);
	
	public void changeStatus(String msgRef);
	
	public void saveINPolled(MsgPolled msgPolled);
	
	public Boolean checkIFSC(String toCheck);
	
	public String getIssuingIFSC(String lcNumber);
	
	public String getDept(String userID);
	
	public String getLcOpenFlagForInsertOrUpdate();
	
	public Boolean isLcNumberExist(String lcNumber); 
	
	public Boolean isLcStatusTwo(String lcNumber);
	
	public String doCompleteTransaction(List <LcCommodity> commodityList,LcMast lcMast,NgphCanonical canonical,MsgPolled msgPolled,String msgRef,String status ,String generatedMsgRef,String lcNumber, String repairMsgref,String repair,ReportEntity entity);
	
	public LCCanonicalDto getPreAdviceRepair(String msgRef);
	
	public Boolean getstatusForLCNumber(String msgRef);
	
	public String getSequenceNo();
	
	public List getListOfIFSC(String code);
	
	public void saveIFSC(PartyDTO dto);
	
	public boolean checkPartyIFSC(String ifsc);
	
	public LCCanonicalDto getLCAckRepair(String msgRef);
	
	public LCCanonicalDto getAdviceofRefusal(String msgRef);
	
	public List displayIFSCCodes(String ifscCode, String bankName, String branchName);
	
	public List<PartyDTO> getBankNameData(String bankName);
	
	public PartyDTO displayIFSCData(String ifscCode);
	
	public LCCanonicalDto getAdviceofDiscrepancy(String msgRef);
	
	public LCCanonicalDto getAdviceofPayment(String msgRef);
		
}
