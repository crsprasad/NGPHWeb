package com.logica.ngph.dao;

import java.util.List;

import com.logica.ngph.Entity.LcMast;
import com.logica.ngph.dtos.BgMastDto;
import com.logica.ngph.dtos.LcMastDto;

public interface LcReportsDao {
	public List<LcMastDto> getReportDate(String lcDirection,String query,String groupBy,String localIFSC,String branch,String tableName);
	public List<BgMastDto> getBgReportDate(String lcDirection, String query,String groupBy,String localIFSC,String branch, String tableName);
	public String getLocalBankIFSC(String userID);
	
	public List<BgMastDto> getBgRejectedReportData(String lcDirection, String query,String tablename,String localIFSC,String branch);
	public List<LcMastDto> getLcRejectedReportData(String lcDirection,	String createQuery, String tablename, String localBankIFSC,String msgBranch);
	

}
