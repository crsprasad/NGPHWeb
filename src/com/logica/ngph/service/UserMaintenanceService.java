package com.logica.ngph.service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import com.logica.ngph.Entity.CurrencyMaster;
import com.logica.ngph.Entity.Department;
import com.logica.ngph.Entity.Roles;
import com.logica.ngph.Entity.SecUsers;
import com.logica.ngph.Entity.SecurityQuesions;
import com.logica.ngph.common.NGPHException;
import com.logica.ngph.common.dtos.UserMaintenanceDTO;

public interface UserMaintenanceService {
	public void performUserAction(UserMaintenanceDTO userObject) throws NGPHException;
	public List<Roles> getAvailableRoles() throws NGPHException;
	public List<Department> getAvailableDepts() throws NGPHException;
	public boolean performUserIdAvailabilityCheck(String userId);
	public List<UserMaintenanceDTO> dataUserIDSearch(String userId)throws NGPHException;
	public SecUsers getUserDataAction(String userId)throws NGPHException;
	public List<String>  getAssignedDepts(String userId) throws NGPHException;
	public List<String>  getAssignedRoles(String userId) throws NGPHException;
	public Boolean isUserPresent(String user) throws NGPHException;
	public String getRoleName(String roleId) throws NGPHException;
	public String getDeptName(String deptId) throws NGPHException;
	public List<String> getCurrencyCodes() throws NGPHException;
	public Timestamp getLastModPassByUser(String userId)throws NGPHException;
	public List<String> getSecurityQuestions() throws NGPHException;
}
