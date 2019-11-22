package com.logica.ngph.serviceImpl;

import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.logica.ngph.dao.FeedsDao;
import com.logica.ngph.dtos.EIDto;
import com.logica.ngph.service.FeedsService;

public class FeedsServiceImpl implements  FeedsService{	
	static Logger logger = Logger.getLogger(FeedsServiceImpl.class);
	FeedsDao feedsDao;
	

	
	public FeedsDao getFeedsDao() {
		return feedsDao;
	}



	public void setFeedsDao(FeedsDao feedsDao) {
		this.feedsDao = feedsDao;
	}



	public List<com.logica.ngph.dtos.EIDto> getEIFeedDetails() throws SQLException
	{
		
		List <com.logica.ngph.dtos.EIDto> result=feedsDao.getEIFeedDetails();
		
		return result;
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	public String changeStatus(String eiCode, String status) throws SQLException{
		
		String result = feedsDao.changeStatus(eiCode, status);
		return result;
		
	}
	
}
