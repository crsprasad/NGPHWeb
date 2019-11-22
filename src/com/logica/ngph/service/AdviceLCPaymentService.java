package com.logica.ngph.service;

import java.util.List;

import com.logica.ngph.Entity.LcCommodity;
import com.logica.ngph.dtos.AuthoriseLCPaymentAdviceDto;
import com.logica.ngph.dtos.LCAdvicePaymentDto;
import com.logica.ngph.dtos.LCCanonicalDto;


public interface AdviceLCPaymentService {
	
	public LCCanonicalDto getLCScreenData(String lcNumber);
	public List<LCCanonicalDto> getLCAdvicePaymentData(String lcNumber);
	public LCCanonicalDto getAuthriseLCScreenData(String lcNumber, String status);
	public List<LCCanonicalDto> getAuthoriseLCAdvicePaymentData(String lcNumber);
	public LCCanonicalDto getAmendLCScreenData(String lcNumber);
	public List<LCCanonicalDto> getAdmendLCData(String lcNumber);
	public List<LCCanonicalDto> getLCAuthtoPayData(String lcNumber);
	
	

}
