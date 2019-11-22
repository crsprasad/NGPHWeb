package com.logica.ngph.serviceImpl;

import java.util.List;

import org.apache.log4j.Logger;

import com.logica.ngph.dao.InformationDao;
import com.logica.ngph.dao.LcReportsDao;
import com.logica.ngph.dtos.InformationDto;
import com.logica.ngph.service.InformationService;

public class InformationServiceImpl implements InformationService {
	private Logger logger = Logger.getLogger(InformationServiceImpl.class);
	InformationDao informationDao;
	
	
	public InformationDao getInformationDao() {
		return informationDao;
	}


	public void setInformationDao(InformationDao informationDao) {
		this.informationDao = informationDao;
	}


	public List<InformationDto> getReportDate(String direction) {
		try{
			return informationDao.getReportDate(direction);
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
				
	}


	public String getRawInformationMsg(String msgref, String direction) {
		try
		{
			return informationDao.getRawInformationMsg(msgref,direction);
		}
		catch (Exception e)
		{
		e.printStackTrace();
		return null;
		}
	}
}
