/**
 * 
 */
package com.logica.ngph.service;

import java.util.List;

import com.logica.ngph.dtos.PaymentMessage;

/**
 * @author chakkar
 *
 */
public interface EnquiryHistSearchService {
	public List<PaymentMessage> getSearchHistResult(String stringQuery,String direction,String tableToUse);

}
