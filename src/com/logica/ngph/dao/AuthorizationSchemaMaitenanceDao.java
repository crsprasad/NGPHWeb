package com.logica.ngph.dao;



import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.logica.ngph.Entity.AuthorizationMaintenance;

public interface AuthorizationSchemaMaitenanceDao {
	public List<String> getMessageTypes() throws SQLException;
	public List<String> getSubMessageTypes() throws SQLException;
	public List<String> getChannelTypes() throws SQLException;
	public List<String> getHostName() throws SQLException;
	public List<String> getHostCode() throws SQLException;
	public List<String> getCurrency() throws SQLException;
	public List<String> getGroupIDAndGroupName() throws SQLException;
	public List<String> getBranchCode() throws SQLException;
	public List<String> getBranchCodeName() throws SQLException;
	public Map<String,Object> treeView() throws SQLException;
	public String insertDataInTA_AUTHSCHEMAM(AuthorizationMaintenance obj);
	public List<String> getCurrencyCodes() throws SQLException;
}
