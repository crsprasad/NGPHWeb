package com.logica.ngph.serviceImpl;

import java.util.List;

import org.apache.log4j.Logger;

import com.logica.ngph.Entity.LcMast;
import com.logica.ngph.dao.LcReportsDao;
import com.logica.ngph.dtos.BgMastDto;
import com.logica.ngph.dtos.LcMastDto;
import com.logica.ngph.service.LcReportsService;

public class LcReportsServiceImpl implements LcReportsService {
	
	private Logger logger = Logger.getLogger(LcReportsServiceImpl.class);
	LcReportsDao lcReportsDao;

	public LcReportsDao getLcReportsDao() {
		return lcReportsDao;
	}

	public void setLcReportsDao(LcReportsDao lcReportsDao) {
		this.lcReportsDao = lcReportsDao;
		
		
	}
	public List<LcMastDto> getReportDate(String lcDirection,String query,String groupBy,String localIFSC,String branch,String tableName)
	{
		try{
			return lcReportsDao.getReportDate(lcDirection,query,groupBy,localIFSC,branch,tableName);
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	
	public List<BgMastDto> getBgReportDate(String lcDirection, String query,
			String groupBy,String localIFSC,String branch, String tableName) {
		try{
			return lcReportsDao.getBgReportDate(lcDirection,query,groupBy,localIFSC,branch,tableName);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	
	public String getLocalBankIFSC(String userID) {
		try{
			return lcReportsDao.getLocalBankIFSC(userID);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<BgMastDto> getBgRejectedReportData(String lcDirection,
			String query, String tablename,String localIFSC, String branch) {
		try{
			return lcReportsDao.getBgRejectedReportData(lcDirection,query,tablename,localIFSC,branch);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}


	public List<LcMastDto> getLcRejectReportData(String lcDirection,String createQuery, String tablename, String localBankIFSC,	String msgBranch) {
		
		try{
			return lcReportsDao.getLcRejectedReportData(lcDirection,createQuery,tablename,localBankIFSC,msgBranch);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;	}

}
