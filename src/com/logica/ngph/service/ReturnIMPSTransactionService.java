package com.logica.ngph.service;

import java.sql.SQLException;

import java.util.List;

import org.apache.log4j.Logger;

import com.logica.ngph.Entity.MsgPolled;
import com.logica.ngph.dtos.ReturnIMPSTransactionDto;


import com.logica.ngph.dao.ReturnIMPSTransactionDao;

public class ReturnIMPSTransactionService {
	static Logger logger = Logger.getLogger(ReturnIMPSTransactionService.class);
	ReturnIMPSTransactionDao returnIMPSTransactionDao;
	
	public ReturnIMPSTransactionDao getReturnIMPSTransactionDao() {
		return returnIMPSTransactionDao;
	}
	public void setReturnIMPSTransactionDao(
			ReturnIMPSTransactionDao returnIMPSTransactionDao) {
		this.returnIMPSTransactionDao = returnIMPSTransactionDao;
	}
	public List<ReturnIMPSTransactionDto> getReturnDetails(String date) throws SQLException{
		List<ReturnIMPSTransactionDto> list = returnIMPSTransactionDao.getReturnDetails(date);
		
		return list;
	}
	public String savePolled(MsgPolled msgPolled) throws SQLException
	{
		String msgReference = returnIMPSTransactionDao.savePolled(msgPolled);
		return msgReference;
	}
	

}
