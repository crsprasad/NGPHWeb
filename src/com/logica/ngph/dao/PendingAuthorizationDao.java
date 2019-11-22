package com.logica.ngph.dao;

import java.util.List;

public interface PendingAuthorizationDao {
	public String delimitedStringValue(String transRef,String sequence,String screenData);
	public String getTranRef(String screenData,String actionMapping, String userId);
	public String getScreenReturn(String trnRef);
	public String getCreatedUser(String trnRef);
	public String getUserType(String userId);
	public List getMulScreenData(String trnRef);
	public String changeStatus(String Status,String txnRef);
	public String getTempRef(String screenData, String srcMsg, String subMsg,String tempName, String actionMapping, String userId);
	public String getTemplateScreen(String tempRef);
	public int updateRejectStatusToPending(String txnRef);
	public String delimitedTempStringValue(String transRef,String sequence,String screenData);
	public List gettempMulScreenData(String tempRef);
}
