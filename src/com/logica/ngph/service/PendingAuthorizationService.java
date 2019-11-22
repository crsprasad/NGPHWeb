package com.logica.ngph.service;

import java.util.List;

import com.logica.ngph.dao.PendingAuthorizationDao;

public class PendingAuthorizationService {
	
	PendingAuthorizationDao pendingAuthorizationDao;
	
	
	public PendingAuthorizationDao getPendingAuthorizationDao() {
		return pendingAuthorizationDao;
	}
	public void setPendingAuthorizationDao(
			PendingAuthorizationDao pendingAuthorizationDao) {
		this.pendingAuthorizationDao = pendingAuthorizationDao;
	}
	public String delimitedStringValue(String transRef ,String sequence,String screenData)
	{
		return pendingAuthorizationDao.delimitedStringValue(transRef,sequence,screenData);
	}
	public String getTranRef(String screenData,String actionMapping, String userId)
	{
		System.gc();
		return pendingAuthorizationDao.getTranRef(screenData,actionMapping, userId.toUpperCase());
	}
	public String getScreenReturn(String trnRef)
	{
		return pendingAuthorizationDao.getScreenReturn(trnRef);
	}
	public String getCreatedUser(String trnRef)
	{
		return pendingAuthorizationDao.getCreatedUser(trnRef);
	}
	public String getUserType(String userId)
	{
		return pendingAuthorizationDao.getUserType(userId);
	}
	public List getMulScreenData(String trnRef)
	{
		return pendingAuthorizationDao.getMulScreenData(trnRef);
	}
	public String changeStatus(String Status,String txnRef)
	{
		return pendingAuthorizationDao.changeStatus(Status,txnRef);
	}
	
	public String getTempRef(String screenData,String srcMsg, String subMsg, String tempName,String actionMapping, String userId)
	{
		System.gc();
		return pendingAuthorizationDao.getTempRef(screenData,srcMsg,subMsg,tempName,actionMapping,userId);
	}
	
	public String getTemplateScreen(String tempRef)
	{
		return pendingAuthorizationDao.getTemplateScreen(tempRef);
	}
	
	public int updateRejectStatusToPending(String txnRef)
	{
		System.gc();
		return pendingAuthorizationDao.updateRejectStatusToPending(txnRef);
	}
	
	public String delimitedTempStringValue(String transRef ,String sequence,String screenData)
	{
		return pendingAuthorizationDao.delimitedTempStringValue(transRef,sequence,screenData);
	}
	public List gettempMulScreenData(String trnRef)
	{
		return pendingAuthorizationDao.gettempMulScreenData(trnRef);
	}
}
