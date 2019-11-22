package com.logica.ngph.dao;

import java.sql.SQLException;
import java.util.List;

import com.logica.ngph.Entity.Functions;
import com.logica.ngph.Entity.Roles;
import com.logica.ngph.common.NGPHException;

public interface RoleMaintenaneceDao {
	
    List<Functions> getFunctions();
	void saveRole(String id, String name, String desc, List<String> functionsList)throws SQLException;	
	void modifyRole(String id, String name, String desc, List<String> functionsList)throws SQLException;
	void deleteRole(String id)throws SQLException;
	List<String> getRoleIDList(String roleId) throws SQLException;
	List<String> getAccessibleFunctions(String userId);
	Roles getRoleData(String roleId) throws SQLException ;
	List<String> getAssignedFunctions(String roleId)throws SQLException;
	public Boolean isRolePresent(String roleID) throws NGPHException ;
}
