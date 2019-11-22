package com.logica.ngph.service;

import java.sql.SQLException;
import java.util.List;

import com.logica.ngph.dtos.EIDto;

public interface FeedsService {
	
	public List <EIDto> getEIFeedDetails() throws SQLException;
	
	public String changeStatus(String eiCode, String Status) throws SQLException;


}
