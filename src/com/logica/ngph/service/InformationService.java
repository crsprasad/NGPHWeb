package com.logica.ngph.service;

import java.util.List;

import com.logica.ngph.dtos.InformationDto;

public interface InformationService {
	public List<InformationDto> getReportDate(String direction);
	public String getRawInformationMsg(String msgref, String msgDirection);
}
