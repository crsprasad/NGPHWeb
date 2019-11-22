package com.logica.ngph.dao;

import java.sql.SQLException;
import java.util.List;

import com.logica.ngph.dtos.GenericManagerDto;
import com.logica.ngph.dtos.TemplateDto;

public interface GenericManagerDao {
	public String insertString(String screenString,String destPage,String ListData) throws SQLException;
	public List<GenericManagerDto> fetchData(String userID) throws SQLException;
	public List<TemplateDto> fetchTempateData(String userID,String msgType,String tempName) throws SQLException;
	public List<TemplateDto> getTempNameSearchData(String tempName, String msgType)throws SQLException;
	public List<GenericManagerDto> fetchRejectedData(String userID) throws SQLException;
	public List<GenericManagerDto> fetchPendingAuthorizationData(String userID) throws SQLException;
}
