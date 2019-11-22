package com.logica.ngph.dao;

import com.logica.ngph.Entity.BgMast;
import com.logica.ngph.Entity.MsgPolled;
import com.logica.ngph.Entity.NgphCanonical;
import com.logica.ngph.dtos.ReleaseBankGuaranteeDto;

public interface ReleaseBankGuaranteeDao {
	public NgphCanonical getCanonical(String bgNumber);
	public BgMast getBgMast(String bgNumber);
	public void releaseOrCreateBankGuarantee(ReleaseBankGuaranteeDto releaseBankGuaranteeDto, NgphCanonical ngphCanonical, MsgPolled msgPolled, String txnRef, String messageReference);
	public String getDept(String userId)throws Exception;
}
