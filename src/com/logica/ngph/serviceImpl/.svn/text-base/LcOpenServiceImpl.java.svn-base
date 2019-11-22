package com.logica.ngph.serviceImpl;

import java.util.List;

import org.apache.log4j.Logger;

import com.logica.ngph.Entity.LcCommodity;
import com.logica.ngph.dao.LcOpenDao;
import com.logica.ngph.dtos.LCCanonicalDto;
import com.logica.ngph.dtos.LcMastDto;
import com.logica.ngph.service.LcOpenService;

public class LcOpenServiceImpl implements LcOpenService{
	private static Logger logger = Logger.getLogger(LcOpenServiceImpl.class);
	LcOpenDao lcOpenDao;
	public LcOpenDao getLcOpenDao() {
		return lcOpenDao;
	}
	public void setLcOpenDao(LcOpenDao lcOpenDao) {
		this.lcOpenDao = lcOpenDao;
	}
	
	public List<LCCanonicalDto> getDate(String lcNumber) {
		try{
			
			return lcOpenDao.getLcDate(lcNumber);
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public LCCanonicalDto getLCScreenData(String lcNumber) {
		try{
			
			return lcOpenDao.getLcScreenData(lcNumber);
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public List<LcCommodity> getCommodityDetails(String lcNumber) {
		try{
			return lcOpenDao.getCommodityDetails(lcNumber);
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public String saveLC(LCCanonicalDto canonicalDto,
			List<LcCommodity> commodityList) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public String getLcStatus(String lcNumber) {
		
		return lcOpenDao.getLcStatus(lcNumber);
	}
	
	public String saveChangeStatus(String lcNumber, String status) {
		
		return lcOpenDao.saveChangeStatus(lcNumber,status);
	}
	
	public List<LCCanonicalDto> getLCNumber(String lcNumber) {
		// TODO Auto-generated method stub
		return lcOpenDao.getLCNumber(lcNumber);
	}
	
	public List<LcMastDto>  displayLcNumber() {
		// TODO Auto-generated method stub
		return lcOpenDao.displayLcNumber();
	}
	public List<LCCanonicalDto> getTranferDocumentaryData(String lcNumber,String status,String direction) {
		// TODO Auto-generated method stub
		return lcOpenDao.getTranferDocumentaryData(lcNumber,status,direction);
	}

	

}
