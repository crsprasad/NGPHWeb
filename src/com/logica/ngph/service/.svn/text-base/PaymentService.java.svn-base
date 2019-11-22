package com.logica.ngph.service;

import java.util.List;
import java.util.Map;




import com.logica.ngph.common.NGPHException;
import com.logica.ngph.common.dtos.NgphCanonical;
import com.logica.ngph.dtos.ModifiedMessagesDto;





public interface PaymentService {
	
	public Map<String, List<String>> getMsgSupport();
	public void savePayment(NgphCanonical ngphCanonical) throws NGPHException ;
	public NgphCanonical getPayment(String msgReference) throws NGPHException;
	public void savePaymentRepair(ModifiedMessagesDto modifiedMessagesDto) throws NGPHException;
	
	
	
}
