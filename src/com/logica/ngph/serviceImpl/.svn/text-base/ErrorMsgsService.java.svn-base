package com.logica.ngph.serviceImpl;

import java.sql.SQLException;
import java.util.List;

import com.logica.ngph.Entity.RawMessage;
import com.logica.ngph.common.NGPHException;
import com.logica.ngph.dao.IErrorMsgsDao;
import com.logica.ngph.service.IErrorMsgsService;


public class ErrorMsgsService implements IErrorMsgsService {

	public IErrorMsgsDao errorMsgsDao;
	
	public void setErrorMsgsDao(IErrorMsgsDao errorMsgsDao) {
		this.errorMsgsDao = errorMsgsDao;
	}

	public List<RawMessage> doAction() throws SQLException, NGPHException
	{
		List<RawMessage> dataList= errorMsgsDao.fetchErrorMsgs();
		return dataList;
	}

	
	public String changeStatusAndMSgpolled(String msgRef) {
		// TODO Auto-generated method stub
		return errorMsgsDao.changeStatusAndMSgpolled(msgRef);
	}
}
