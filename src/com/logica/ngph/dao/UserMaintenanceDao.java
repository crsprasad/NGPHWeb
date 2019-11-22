package com.logica.ngph.dao;

import java.sql.SQLException;
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
import com.logica.ngph.dtos.UserPasswordsDTO;

public interface UserMaintenanceDao {
	public void createUser(SecUsers userEntity, List<String> assignedRoles, List<String> assignedDepts) throws SQLException;
	public List<Roles> getAvailableRoles() throws SQLException;
	public List<Department> getAvailableDepts() throws SQLException;
	public boolean isUserIdAvailable(String userId);
	public List<UserMaintenanceDTO> getUserIDList(String userId) throws SQLException;
	public SecUsers getUserDataAction(String userId) throws SQLException;
	public List<String>  getAssignedDepts(String userId) throws SQLException;
	public List<String>  getAssignedRoles(String userId) throws SQLException;
	public void modifyUser(SecUsers userEntity, List<String> assignedRoles, List<String> assignedDepts, UserPasswordsDTO userPasswordsDTO) throws SQLException;
	public void deleteUser(String userId) throws SQLException;
	public Boolean isUserPresent(String user) throws NGPHException;
	public String getRoleName(String roleId) throws NGPHException;
	public String getDeptName(String deptId) throws NGPHException;
	public List<String> getCurrencyCodes() throws SQLException;
	public Timestamp getLastModPassByUser(String userId)throws NGPHException;
	public List<String> getSecurityQuestions() throws SQLException;
}
