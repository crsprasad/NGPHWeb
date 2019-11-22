package com.logica.ngph.service;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import com.logica.ngph.common.NGPHException;
import com.logica.ngph.dtos.ArchivalTaskDto;
import com.logica.ngph.dtos.SodEodTaskTDto;

public interface SodEodTaskService {
public Map<String,Object> getLocalBranch(String userId) throws SQLException;
//public void saveSodEodT(SodEodTaskTDto sodEodDto);
public void setNextBusinessDay(SodEodTaskTDto sodEodDto);
public void releaseWarehouse();
public void openCloseInboundOutboundFeeds(SodEodTaskTDto sodEodDto,int status);
public void validatePaymentNonfinalityStatus(String branchCode,SodEodTaskTDto sodEodDto) throws NGPHException;
public void moveFinalStatusToHistoryTable(String branchCode,SodEodTaskTDto sodEodDto);
public void deleteHistoryDataBeyond(String branchCode,SodEodTaskTDto sodEodDto);
public void updateSodEodT(String errorMessage,String taskId);
public void updateSodEodTComp(String taskId);
public void updateBusinessDayM(String branchCode,String businessDayStatus);
public List<Integer> getSodEodStatus(List<String> taskIdList);

public void updateTaLimits()throws Exception;

public List<String> getbranches()throws Exception;

public Timestamp getCurBusDayforBranch(String branchCode)throws Exception;

public List<ArchivalTaskDto> startArchivalAction()throws Exception;

public String getBranchName()throws Exception;

public Date getBusinessDay()throws Exception;
}
