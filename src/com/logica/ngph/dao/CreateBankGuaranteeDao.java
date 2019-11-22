package com.logica.ngph.dao;

import java.util.List;

import com.logica.ngph.Entity.MsgPolled;
import com.logica.ngph.Entity.NgphCanonical;
import com.logica.ngph.common.dtos.InfoCanonical;
import com.logica.ngph.dtos.BgMastDto;
import com.logica.ngph.dtos.CreateBankGuaranteeDto;

public interface CreateBankGuaranteeDao {
	
	public void createBG(CreateBankGuaranteeDto createBankGuaranteeDto, int status, String mesRef, String bgNumber, String bgDirection,NgphCanonical canonical, MsgPolled msgPolled) throws Exception;
	public String getDept(String userId);
	public CreateBankGuaranteeDto getBankGuarantee(String msgRef);
	public CreateBankGuaranteeDto getAmendBg(String msgRef);
	public CreateBankGuaranteeDto getAckBg(String msgRef);
	public List<BgMastDto> getAmendBGData(String bgNumber); 
	public List<BgMastDto> getAckBGData(String bgNumber); 
	public CreateBankGuaranteeDto getAmendBGScreenData(String bgNumber);
	public CreateBankGuaranteeDto getAckBGScreenData(String bgNumber);
	public void createAmendBG(CreateBankGuaranteeDto createBankGuaranteeDto, int status, String mesRef, String bgNumber, String bgDirection,NgphCanonical canonical, MsgPolled msgPolled) throws Exception;
	public void createAckBG(CreateBankGuaranteeDto createBankGuaranteeDto,  String mesRef, String bgNumber, String bgDirection,NgphCanonical canonical, MsgPolled msgPolled) throws Exception;
	public void createReduction(CreateBankGuaranteeDto createBankGuaranteeDto, String mesRef, String bgNumber, String bgDirection,NgphCanonical canonical, MsgPolled msgPolled) throws Exception;
	public CreateBankGuaranteeDto getAdviceReduction(String msgRef);
	public void createFreeFormatPayment(CreateBankGuaranteeDto createBankGuaranteeDto, String msgRef, InfoCanonical infoCan, MsgPolled msgPolled)throws Exception;;
	public CreateBankGuaranteeDto getFreeFormatMessage(String msgRef);
	public String createFreeFormatSeqNo();
}
