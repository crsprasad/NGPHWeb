package com.logica.ngph.serviceImpl;

import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

import com.logica.ngph.Entity.Functions;
import com.logica.ngph.Entity.Roles;
import com.logica.ngph.common.ConstantUtil;
import com.logica.ngph.common.NGPHException;
import com.logica.ngph.dao.RoleMaintenaneceDao;
import com.logica.ngph.service.RoleMaintenanceService;


public class RoleMaintenanceServiceImpl implements RoleMaintenanceService{
	
	static Logger logger = Logger.getLogger(RoleMaintenanceServiceImpl.class);
	private RoleMaintenaneceDao roleMaintenanceDao;
	
	/**
	 * @return the roleMaintenanceDao
	 */
	public RoleMaintenaneceDao getRoleMaintenanceDao() {
		return roleMaintenanceDao;
	}

	/**
	 * @param roleMaintenanceDao the roleMaintenanceDao to set
	 */
	public void setRoleMaintenanceDao(RoleMaintenaneceDao roleMaintenanceDao) {
		this.roleMaintenanceDao = roleMaintenanceDao;
	}
	
	
	public List<Functions> getFunctions(){
		List<Functions> functionList = roleMaintenanceDao.getFunctions();
		return functionList;
	}

	
	public List<String> getAccessibleFunctions(String userId) {
		List<String> accessibleFunctionList = roleMaintenanceDao.getAccessibleFunctions(userId);
		return accessibleFunctionList;
	}
	
	
	public String performRoleAction(String roleAction, String id, String name, String desc, List<String> functionsList) throws NGPHException {
		System.out.println("in RoleMaintenanceservice<createRole>");
		
		if(roleAction.equalsIgnoreCase("A")){
			try {
				System.out.println("Inside performAction is service");
				roleMaintenanceDao.saveRole(id, name, desc, functionsList);
			} catch (SQLException sqlException) {
				logger.debug(sqlException);
				throw new NGPHException(ConstantUtil.DATA_ACCESS_PROBLEM);
			}
			return "roleCreated";
		} else if(roleAction.equalsIgnoreCase("M")){
			try {
				System.out.println("In side roleModify");
				roleMaintenanceDao.modifyRole(id, name, desc, functionsList);
			} catch (SQLException sqlException) {
				logger.debug(sqlException);
				throw new NGPHException(ConstantUtil.DATA_ACCESS_PROBLEM);
			}
			return "roleModified";	
		} else if(roleAction.equalsIgnoreCase("D")) {
			try {
				System.out.println("In side roleDelete");
				roleMaintenanceDao.deleteRole(id);
			} catch (SQLException sqlException) {
				logger.debug(sqlException);
				throw new NGPHException(ConstantUtil.DATA_ACCESS_PROBLEM);
			}
			return "roleDeleted";
		}
		return "success";
	}
	
	
	public List<String> roleIDSearch(String roleId) throws NGPHException{
		 List<String>	searchList = null;
			try{
				searchList = roleMaintenanceDao.getRoleIDList(roleId);
			}catch(SQLException sqlException){
				logger.debug(sqlException);	
				throw new NGPHException(ConstantUtil.DATA_ACCESS_PROBLEM);
				}
			return searchList;
	}
	
	
	public Roles getRoleData(String roleId) throws NGPHException {
		Roles dto = null;
			try{
				dto = roleMaintenanceDao.getRoleData(roleId);
			}catch(SQLException sqlException){
				logger.debug(sqlException);	
				throw new NGPHException(ConstantUtil.DATA_ACCESS_PROBLEM);
				}
			return dto;
	}
	
	public List<String> getAssignedFunctions(String roleId)throws NGPHException{
		List<String> assignedFunctions= null;
		try{
			assignedFunctions = roleMaintenanceDao.getAssignedFunctions(roleId);
			for(String s:assignedFunctions){
				System.out.println("AssignedFunctions in Service"+assignedFunctions);
			}
		}catch(SQLException sqlException){
			logger.debug(sqlException);	
			throw new NGPHException(ConstantUtil.DATA_ACCESS_PROBLEM);
			}
		return assignedFunctions;
	}

	public Boolean isRolePresent(String roleID) throws NGPHException {
		// TODO Auto-generated method stub
		return roleMaintenanceDao.isRolePresent(roleID);
	}

}
