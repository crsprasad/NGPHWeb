/**
 * 
 */
package com.logica.ngph.serviceImpl;

import java.util.List;

import org.apache.log4j.Logger;

import com.logica.ngph.dao.EnquiryHistDao;
import com.logica.ngph.dtos.PaymentMessage;
import com.logica.ngph.service.EnquiryHistSearchService;

/**
 * @author chakkark
 *
 */
public class EnquiryHistSearchServiceImpl implements EnquiryHistSearchService {
	
	static Logger logger = Logger.getLogger(EnquirySearchServiceImpl.class);

	EnquiryHistDao enquiryHistDao;
	
	public EnquiryHistDao getEnquiryHistDao() {
		return enquiryHistDao;
	}

	public void setEnquiryHistDao(EnquiryHistDao enquiryHistDao) {
		this.enquiryHistDao = enquiryHistDao;
	}
	
	public List<PaymentMessage> getSearchHistResult(String stringQuery,String direction,String tableToUse) {
		logger.info("Inside EnquirySearchServiceImpl<getSearchHistResult> ");
		List <PaymentMessage> enquirySearchList=enquiryHistDao.getSearchHistResult(stringQuery,direction,tableToUse);
		logger.info("Size of the list"+enquirySearchList.size());
		return enquirySearchList;
	}

}
