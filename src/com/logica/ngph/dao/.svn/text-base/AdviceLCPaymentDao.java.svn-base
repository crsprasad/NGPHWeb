package com.logica.ngph.dao;

import java.util.List;

import com.logica.ngph.Entity.LcMast;
import com.logica.ngph.Entity.MsgPolled;
import com.logica.ngph.Entity.NgphCanonical;
import com.logica.ngph.Entity.Parties;
import com.logica.ngph.dtos.AuthoriseLCPaymentAdviceDto;
import com.logica.ngph.dtos.LCAdvicePaymentDto;
import com.logica.ngph.dtos.LCCanonicalDto;

public interface AdviceLCPaymentDao {
	public LCCanonicalDto getLcScreenData(String lcNumber); 
	public void changeStatus(String msgRef);	
	public List<LCCanonicalDto> getLcAdvicePaymentData(String lcNumber);
	
	public LCCanonicalDto getAuthriseLcScreenData(String lcNumber, String status); 
	
	public void changeStatusToAuth(String msgRef);
	public List<LCCanonicalDto> getAuthoriseLcAdvicePaymentData(String lcNumber);
	
	public LCCanonicalDto getAmendLCScreenData(String lcNumber); 
	public List<LCCanonicalDto> getAmendLCData(String lcNumber);
	public void changeStatusAmend(String msgRef);
	public List<LCCanonicalDto> getLCAuthtoPayData(String lcNumber);

}
