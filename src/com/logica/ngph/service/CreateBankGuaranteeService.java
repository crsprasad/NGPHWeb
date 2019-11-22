package com.logica.ngph.service;

import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.logica.ngph.dtos.BankGuaranteeCoverDto;
import com.logica.ngph.dtos.BgMastDto;
import com.logica.ngph.dtos.CreateBankGuaranteeDto;

public interface CreateBankGuaranteeService {
	@Transactional(propagation = Propagation.REQUIRED)
	public void createBankGuarantee(CreateBankGuaranteeDto createBankGuaranteeDto, String userId) throws Exception;
	public CreateBankGuaranteeDto getBankGuarantee(String msgRef);
	public void createAmendBG (CreateBankGuaranteeDto createBankGuaranteeDto, String userId) throws Exception;
	public void createAckBG (CreateBankGuaranteeDto createBankGuaranteeDto, String userId) throws Exception;
	public CreateBankGuaranteeDto viewAmendBg(String msgRef);
	public CreateBankGuaranteeDto viewAckBg(String msgRef);
	public List<BgMastDto> getAmendBGData(String bgNumber);
	public List<BgMastDto> getAckBGData(String bgNumber);
	public CreateBankGuaranteeDto getAmendBGScreenData(String bgNumber);
	public CreateBankGuaranteeDto getAckBGScreenData(String bgNumber);
	public void createReduction(CreateBankGuaranteeDto createBankGuaranteeDto, String userId) throws Exception;
	public CreateBankGuaranteeDto getAdviceReduction(String msgRef);
	public void createFreeFormatPayment(CreateBankGuaranteeDto createBankGuaranteeDto, String userId) throws Exception;
	public CreateBankGuaranteeDto getFreeFormatMessage(String msgRef)throws Exception;

}
