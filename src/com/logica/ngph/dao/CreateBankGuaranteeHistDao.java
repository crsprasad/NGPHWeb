/**
 * 
 */
package com.logica.ngph.dao;

import com.logica.ngph.dtos.CreateBankGuaranteeDto;

/**
 * @author chakkar
 *
 */
public interface CreateBankGuaranteeHistDao {
	
	public CreateBankGuaranteeDto getBankGuarantee(String msgRef);
	public CreateBankGuaranteeDto getAmendBg(String msgRef);
	public CreateBankGuaranteeDto getAckBg(String msgRef);

}
