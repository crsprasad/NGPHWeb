package com.logica.ngph.dao;

import java.sql.SQLException;
import java.util.List;

import com.logica.ngph.dtos.EIDto;

public interface FeedsDao {
	
	public List<com.logica.ngph.dtos.EIDto> getEIFeedDetails() throws SQLException;
	
	public String changeStatus(String eiCode, String status) throws SQLException;

}
