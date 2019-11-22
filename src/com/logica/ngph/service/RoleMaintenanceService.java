package com.logica.ngph.service;

import java.util.List;

import com.logica.ngph.Entity.Functions;
import com.logica.ngph.Entity.Roles;
import com.logica.ngph.common.NGPHException;

public interface RoleMaintenanceService {
	
	List<Functions> getFunctions();
	List<String> getAccessibleFunctions (String userId);
	List<String> roleIDSearch(String roleId) throws NGPHException;
	Roles getRoleData(String roleId) throws NGPHException;
	List<String> getAssignedFunctions(String roleId)throws NGPHException;
	String performRoleAction(String roleAction, String id, String name, String desc, List<String> functionsList)throws NGPHException ;
	public Boolean isRolePresent(String roleID) throws NGPHException;
}
