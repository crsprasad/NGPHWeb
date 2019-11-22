package com.logica.ngph.dao;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import com.logica.ngph.common.NGPHException;
import com.logica.ngph.dtos.ArchivalTaskDto;
import com.logica.ngph.dtos.SodEodTaskTDto;

public interface SodEodTaskDao {
	public Map<String,Object> getLocalBranch(String userId) throws SQLException;
	
	public void setNextBusinessDate(SodEodTaskTDto sodEodTaskTDto);
	public void openCloseInboundOutboundFeeds(SodEodTaskTDto sodEodTaskTDto,int status);
	public void validatePaymentNonfinalityStatus(String branchCode,SodEodTaskTDto sodEodTaskTDto) throws NGPHException;
	public void moveFinalStatusToHistoryTable(String branchCode,SodEodTaskTDto sodEodTaskTDto);
	public void deleteHistoryDataBeyond(String branchCode,SodEodTaskTDto sodEodTaskTDto);
	public void updateSodEodT(String errorMessage,String taskId);
	public void updateSodEodTComp(String taskId);
	public void updateBusinessDayM(String branchCode,String status);
	public List<Integer> getSodEodStatus(List<String> taskIdList) ;
	
	public void updateTaLimits() throws Exception;
	
	public List<String> getbranches()throws Exception;
	
	public Timestamp getCurBusDayforBranch (String Branch) throws Exception;
	
	public List<ArchivalTaskDto> startArchivalAction()throws Exception;
	
	public String getBranchName()throws Exception;

	public Date getBusinessDay()throws Exception;
	
}
