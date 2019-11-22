package com.logica.ngph.dao;

import java.sql.SQLException;
import java.util.List;

public interface GroupMaintenanceDao {
	public List <String> getGroupMaintenanceBranchCode() throws SQLException;
	public List <String> getGroupMaintenanceBranchName() throws SQLException;
	public String checkGroupIDAlreadyPresent(String groupId) throws SQLException;
	public String insertDataInAuthGRPM(String Branch, String groupId,
			String groupName, String supervisorID,int supervisorSequence,int isMaditatory);

}
