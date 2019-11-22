package com.logica.ngph.dao;

import java.sql.SQLException;
import java.util.List;

import com.logica.ngph.Entity.EI;
import com.logica.ngph.common.NGPHException;
import com.logica.ngph.dtos.searchEI_IDDto;

public interface ESBXMLCreatorDao {
	public String insertRecordInTA_EI(EI ei) throws SQLException;
	public String updateEI(EI ei) throws SQLException;
	public String deleteEI(EI ei) throws SQLException;
	public String checkForPrimaryKey(String EI_ID);
	public List<searchEI_IDDto> dataEI_IDSearch(String code) throws NGPHException;
	
}
