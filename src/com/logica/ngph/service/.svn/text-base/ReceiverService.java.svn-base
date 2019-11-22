package com.logica.ngph.service;

import java.sql.SQLException;

import java.util.List;

import org.apache.log4j.Logger;

import com.logica.ngph.common.ConstantUtil;
import com.logica.ngph.common.NGPHException;
import com.logica.ngph.dao.PaymentSubmittedReportDao;
import com.logica.ngph.dtos.ReceiverDto;


public class ReceiverService {
	static Logger logger = Logger.getLogger(ReceiverService.class);
	PaymentSubmittedReportDao paymentSubmittedReportDao;

	public PaymentSubmittedReportDao getPaymentSubmittedReportDao() {
		return paymentSubmittedReportDao;
	}

	public void setPaymentSubmittedReportDao(
			PaymentSubmittedReportDao paymentSubmittedReportDao) {
		this.paymentSubmittedReportDao = paymentSubmittedReportDao;
	}
	public List<ReceiverDto> dataSearch(String code,String name)throws NGPHException{
		
		 List<ReceiverDto>	searchList = null;
		try{
			
					searchList = paymentSubmittedReportDao.getPartyName(code,name);
			
		}catch(SQLException sqlException){
			logger.debug(sqlException);	
			throw new NGPHException(ConstantUtil.DATA_ACCESS_PROBLEM);
			}
		return searchList;

	}
	public List dataSearchForStreamID(String streamID)throws NGPHException{
		
		 List	searchList = null;
		try{
			
					searchList = paymentSubmittedReportDao.getStreamID(streamID);
			
		}catch(Exception e){
			logger.debug("Exception :- "+e);	
			throw new NGPHException(ConstantUtil.DATA_ACCESS_PROBLEM);
			}
		return searchList;

	}
}
