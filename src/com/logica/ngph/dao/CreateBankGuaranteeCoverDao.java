/**
 * 
 */
package com.logica.ngph.dao;

import com.logica.ngph.Entity.MsgPolled;
import com.logica.ngph.Entity.NgphCanonical;
import com.logica.ngph.dtos.BankGuaranteeCoverDto;

/**
 * @author chakkar
 *
 */
public interface CreateBankGuaranteeCoverDao 
{
	public void createBGCover(BankGuaranteeCoverDto bankGuaranteeCoverDto, int status, String mesRef, String bgNumber, String bgDirection,NgphCanonical canonical, MsgPolled msgPolled) throws Exception;
	public void createAmendBGCover(BankGuaranteeCoverDto bankGuaranteeCoverDto, String mesRef, String bgNumber, String bgDirection,NgphCanonical canonical, MsgPolled msgPolled) throws Exception;
	public String getDept(String userId);
	public BankGuaranteeCoverDto getBankGuaranteeCover(String msgRef);
	public BankGuaranteeCoverDto getCreateAmendBGCover(String msgRef);
}
