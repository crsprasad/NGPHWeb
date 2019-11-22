package com.logica.ngph.serviceImpl;

import java.util.List;

import org.apache.log4j.Logger;

import com.logica.ngph.dao.EnquiryDao;
import com.logica.ngph.dao.EnquiryHistDao;
import com.logica.ngph.dtos.PaymentMessage;
import com.logica.ngph.service.EnquirySearchService;

public class EnquirySearchServiceImpl implements EnquirySearchService {
	
	static Logger logger = Logger.getLogger(EnquirySearchServiceImpl.class);
	EnquiryDao enquirydao;
	

	public EnquiryDao getEnquirydao() {
		return enquirydao;
	}

	public void setEnquirydao(EnquiryDao enquirydao) {
		this.enquirydao = enquirydao;
	}

	
	public List<PaymentMessage> getSearchResult(String stringQuery,String direction,String tableToUse) {
		logger.info("Inside EnquirySearchServiceImpl<getSearchResult> ");
		List <PaymentMessage> enquirySearchList=enquirydao.getSearchResult(stringQuery,direction,tableToUse);
		logger.info("Size of the list"+enquirySearchList.size());
		return enquirySearchList;
	}
	
	
	
	

}
