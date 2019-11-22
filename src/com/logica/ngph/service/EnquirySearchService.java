package com.logica.ngph.service;



import java.util.List;

import com.logica.ngph.dtos.PaymentMessage;

public interface EnquirySearchService {
	public List<PaymentMessage> getSearchResult(String stringQuery,String direction,String tableToUse);
	

}
