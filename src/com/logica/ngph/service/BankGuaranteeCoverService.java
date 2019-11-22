/**
 * 
 */
package com.logica.ngph.service;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.logica.ngph.dtos.BankGuaranteeCoverDto;

/**
 * @author chakkar
 *
 */
public interface BankGuaranteeCoverService {
	@Transactional(propagation = Propagation.REQUIRED)
	public void createBankGuaranteeCover(BankGuaranteeCoverDto bankGuaranteeCoverDto, String userId) throws Exception;
	public void createAmendBankGuaranteeCover(BankGuaranteeCoverDto bankGuaranteeCoverDto, String userId) throws Exception;
	public BankGuaranteeCoverDto getBankGuaranteeCover(String msgRef);
	public BankGuaranteeCoverDto getCreateAmendBGCover(String msgRef);
	
}
