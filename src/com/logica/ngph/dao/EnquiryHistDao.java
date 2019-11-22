/**
 * 
 */
package com.logica.ngph.dao;

import java.util.List;

import com.logica.ngph.dtos.PaymentMessage;

/**
 * @author chakkar
 *
 */
public interface EnquiryHistDao {
	
	public List<PaymentMessage> getSearchHistResult(String stringQuery,String direction,String tableToUse);

}
