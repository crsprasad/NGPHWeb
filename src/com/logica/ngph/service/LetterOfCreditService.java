package com.logica.ngph.service;

import java.util.List;

import com.logica.ngph.Entity.LcCommodity;
import com.logica.ngph.Entity.Parties;
import com.logica.ngph.common.ConstantUtil;
import com.logica.ngph.dtos.LCCanonicalDto;
import com.logica.ngph.dtos.PartyDTO;

public interface LetterOfCreditService {
	public String saveLC(LCCanonicalDto canonicalDto,List <LcCommodity> commodityList,String Status,String UserId,String repair);
	
	public List<Parties> getIFSCCodeList(String ifscCode,String userID);
	
	public List<Parties> getIFSCCodeList(String ifscCode,String flag,String required);
	
	public Boolean checkIFSC(String toCheck);
	
	public String getLcOpenFlagForInsertOrUpdate();
	
	public Boolean isLcNumberExist(String lcNumber);
	
	public Boolean isLcStatusTwo(String lcNumber);
	
	public LCCanonicalDto getPreAdviceRepair(String msgRef);
	
	public Boolean getstatusForLCNumber(String msgRef);
	
	public String getDept(String userID);
	
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
