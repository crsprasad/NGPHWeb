package com.logica.ngph.dao;

import java.util.List;

import com.logica.ngph.dtos.InformationDto;

public interface InformationDao {
	
	public List<InformationDto> getReportDate(String direction);
	public String getRawInformationMsg(String msgref, String msgDirection);

}
