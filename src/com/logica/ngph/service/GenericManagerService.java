package com.logica.ngph.service;

import java.sql.SQLException;
import java.util.List;

import com.logica.ngph.dao.GenericManagerDao;
import com.logica.ngph.dtos.GenericManagerDto;
import com.logica.ngph.dtos.LCCanonicalDto;
import com.logica.ngph.dtos.TemplateDto;

public class GenericManagerService {
	GenericManagerDao genericManagerDao;
	public GenericManagerDao getGenericManagerDao() {
		return genericManagerDao;
	}

	public void setGenericManagerDao(GenericManagerDao genericManagerDao) {
		this.genericManagerDao = genericManagerDao;
	}

	
	public String insertString(String screenString,String destPage,String ListData)throws SQLException
	{
		return genericManagerDao.insertString(screenString,destPage,ListData);
	}

	public List<GenericManagerDto> fetchData(String userID) throws SQLException
	{
		return genericManagerDao.fetchData(userID);
	}

	public List<TemplateDto> fetchTempateData(String userID, String msgType, String tempName) throws SQLException
	{
		return genericManagerDao.fetchTempateData(userID,msgType,tempName);
	}
	
	public List<TemplateDto> getTempNameSearchData(String tempName,String msgType)throws SQLException
	{
		return genericManagerDao.getTempNameSearchData(tempName,msgType);
	}
	
	public List<GenericManagerDto> fetchRejectedData(String userID) throws SQLException
	{
		return genericManagerDao.fetchRejectedData(userID);
	}
	public List<GenericManagerDto> fetchPendingAuthorizationData(String userID) throws SQLException
	{
		return genericManagerDao.fetchPendingAuthorizationData(userID);
	}
	
	
}
