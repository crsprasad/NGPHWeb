package com.logica.ngph.serviceImpl;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.logica.ngph.Entity.CurrencyMaster;
import com.logica.ngph.Entity.Department;
import com.logica.ngph.Entity.Roles;
import com.logica.ngph.Entity.SecUsers;
import com.logica.ngph.Entity.SecurityQuesions;
import com.logica.ngph.common.ConstantUtil;
import com.logica.ngph.common.NGPHException;
import com.logica.ngph.common.dtos.UserMaintenanceDTO;
import com.logica.ngph.dao.ChangePwdDao;
import com.logica.ngph.dao.UserMaintenanceDao;
import com.logica.ngph.dtos.UserPasswordsDTO;
import com.logica.ngph.service.UserMaintenanceService;
import com.logica.ngph.utils.StringEncrypter;

/**
 * 
 * @author SATHISH
 *
 */
public class UserMaintenanceServiceImpl implements UserMaintenanceService{
	
	static Logger logger = Logger.getLogger(UserMaintenanceServiceImpl.class);
	UserMaintenanceDao userMaintenanceDao;
	ChangePwdDao changePwdDao;
	
	
	public void performUserAction(UserMaintenanceDTO userObject) throws NGPHException {
		System.out.println("in User service<createUser>");
		SecUsers  userEntity =  convertUserMaintenanceDTOtoSecUsersEntity(userObject);; 
		UserPasswordsDTO userPasswordsDTO = new UserPasswordsDTO();
		try{
			userPasswordsDTO = changePwdDao.getOldPwds(userEntity.getUser());
		}catch (SQLException sqlException) {
			logger.debug(sqlException);
			throw new NGPHException(ConstantUtil.DATA_ACCESS_PROBLEM);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(userObject.getUserAction().equalsIgnoreCase("A")){
			userEntity = convertUserMaintenanceDTOtoSecUsersEntity(userObject);
			userEntity.setUserLocked(1);
			userEntity.setUserActive(1);
			userEntity.setFirstlogin(1);
			try {
				 userMaintenanceDao.createUser(userEntity, userObject.getAssignedRoles(), userObject.getAssignedDepts());
			} catch (SQLException sqlException) {
				logger.debug(sqlException);
				throw new NGPHException(ConstantUtil.DATA_ACCESS_PROBLEM);
			}
		} else if(userObject.getUserAction().equalsIgnoreCase("M")){
			try {
				userEntity = convertUserMaintenanceDTOtoSecUsersEntity(userObject);
				
				 userMaintenanceDao.modifyUser(userEntity, userObject.getAssignedRoles(), userObject.getAssignedDepts(),userPasswordsDTO);
			} catch (SQLException sqlException) {
				logger.debug(sqlException);
				throw new NGPHException(ConstantUtil.DATA_ACCESS_PROBLEM);
			}
			
		} else if(userObject.getUserAction().equalsIgnoreCase("D")) {
			try {
				 userMaintenanceDao.deleteUser(userObject.getUser());
			} catch (SQLException sqlException) {
				logger.debug(sqlException);
				throw new NGPHException(ConstantUtil.DATA_ACCESS_PROBLEM);
			}
		}
	}
	
	
	public List<Roles> getAvailableRoles() throws NGPHException {
		System.out.println("in User service<getAvailableRoles> ");
		try {
			return userMaintenanceDao.getAvailableRoles();
		} catch (SQLException sqlException) {
			logger.debug(sqlException);
			throw new NGPHException(ConstantUtil.DATA_ACCESS_PROBLEM);
		}
	}
	
	
	public List<Department> getAvailableDepts() throws NGPHException {
		System.out.println("in User service<getAvailableDepts> ");
		try {
			return userMaintenanceDao.getAvailableDepts();
		} catch (SQLException sqlException) {
			logger.debug(sqlException);
			throw new NGPHException(ConstantUtil.DATA_ACCESS_PROBLEM);
		}
	}
	
	
	public List<UserMaintenanceDTO> dataUserIDSearch(String userId) throws NGPHException{
		 List<UserMaintenanceDTO>	searchList = null;
			try{
				searchList = userMaintenanceDao.getUserIDList(userId);
			}catch(SQLException sqlException){
				logger.debug(sqlException);	
				throw new NGPHException(ConstantUtil.DATA_ACCESS_PROBLEM);
				}
			return searchList;
	}

	
	public SecUsers getUserDataAction(String userId) throws NGPHException {
			SecUsers dto = null;
			try{
				dto = userMaintenanceDao.getUserDataAction(userId);
			}catch(SQLException sqlException){
				logger.debug(sqlException);	
				throw new NGPHException(ConstantUtil.DATA_ACCESS_PROBLEM);
				}
			return dto;
	}

	
	public List<String> getAssignedDepts(String userId) throws NGPHException {
		List<String> deptsList = new ArrayList<String>();;
		try{
			deptsList = userMaintenanceDao.getAssignedDepts(userId);
		}catch(SQLException sqlException){
			logger.debug(sqlException);	
			throw new NGPHException(ConstantUtil.DATA_ACCESS_PROBLEM);
			}
		return deptsList;
		
	}

	
	public List<String> getAssignedRoles(String userId) throws NGPHException {
		List<String> roleList = new ArrayList<String>();;
		try{
			roleList = userMaintenanceDao.getAssignedRoles(userId);
		}catch(SQLException sqlException){
			logger.debug(sqlException);	
			throw new NGPHException(ConstantUtil.DATA_ACCESS_PROBLEM);
			}
		return roleList;
	}

	
	public boolean performUserIdAvailabilityCheck(String userId) {
		System.out.println("in User service<performUserIdAvailabilityCheck> ");
		
		return userMaintenanceDao.isUserIdAvailable(userId);
	}

	public UserMaintenanceDao getUserMaintenanceDao() {
		return userMaintenanceDao;
	}
	public void setUserMaintenanceDao(UserMaintenanceDao userMaintenanceDao) {
		this.userMaintenanceDao = userMaintenanceDao;
	}
	
	private SecUsers convertUserMaintenanceDTOtoSecUsersEntity(UserMaintenanceDTO userObject){
		SecUsers userEntity = new SecUsers();
		userEntity.setAppl("QNG");
		
		String pwd = StringEncrypter.encryptURL(userObject.getUser(), userObject.getUserPassword());
		userEntity.setUser(userObject.getUser().toUpperCase());
		userEntity.setUserFirstName(userObject.getUserFirstName());
		userEntity.setUserLastName(userObject.getUserLastName());
		userEntity.setUserDept(userObject.getUserDept());
		userEntity.setUserType(userObject.getUserType());
		userEntity.setEmpNo(userObject.getEmpNo());
		userEntity.setUserExpiryDate(convertStringToTimestamp(userObject.getUserExpiryDate()));
		userEntity.setUserEmailId(userObject.getUserEmailId());
		String str1=StringEncrypter.encryptURL(userObject.getUser(), userObject.getUserPassword());
		System.out.println(str1);
		str1 = new String(new sun.misc.BASE64Encoder().encode(str1.getBytes()));
		
		System.out.println(str1);
		//userEntity.setUserPassword(pwd);
		userEntity.setUserPassword(str1);
		userEntity.setUserBranch(userObject.getUserBranch());
		userEntity.setEffectiveDateForRole(convertStringToTimestamp(userObject.getEffectiveDateForRole()));
		userEntity.setUserDailyLimit(userObject.getUserDailyLimit());
		userEntity.setMobileNumber(userObject.getMobileNumber());
		userEntity.setFirstlogin(userObject.getFirstlogin());
		userEntity.setUserLocked(userObject.getUserLocked());
		userEntity.setUserActive(userObject.getUserActive());
		userEntity.setSecurityQuesion(userObject.getSecurityQuestion());
		userEntity.setSecurityAnswer(userObject.getSecurityAnswer());
		return userEntity;
	}
	
	private java.sql.Timestamp convertStringToTimestamp(Date date) {
		if(date != null){
			try{
				return new java.sql.Timestamp(date.getTime());
			}catch(Exception ex){
				ex.printStackTrace();
				return null;
			}
		}else {
			return null;
		}
	}


	public Boolean isUserPresent(String user) throws NGPHException {
		
			return userMaintenanceDao.isUserPresent(user);
		
		
	}


	public String getRoleName(String roleId) throws NGPHException {
		String roleName = null;
		roleName = userMaintenanceDao.getRoleName(roleId);
		return roleName;
	}


	public String getDeptName(String deptId) throws NGPHException {
		String deptName = null;
		deptName = userMaintenanceDao.getDeptName(deptId);
		return deptName;
	}

	public List<String> getCurrencyCodes() throws NGPHException {
		System.out.println("in User service<getCurrencyCodes> ");
		try {
			return userMaintenanceDao.getCurrencyCodes();
		} catch (SQLException sqlException) {
			logger.debug(sqlException);
			throw new NGPHException(ConstantUtil.DATA_ACCESS_PROBLEM);
		}
	}


	
	public Timestamp getLastModPassByUser(String userId) throws NGPHException {
		try {
			return userMaintenanceDao.getLastModPassByUser(userId);
		} catch (Exception sqlException) {
			logger.debug(sqlException);
			throw new NGPHException(ConstantUtil.DATA_ACCESS_PROBLEM);
		}
	}
	
	
	public List<String> getSecurityQuestions() throws NGPHException {
		try {
			return userMaintenanceDao.getSecurityQuestions();
		} catch (SQLException sqlException) {
			logger.debug(sqlException);
			throw new NGPHException(ConstantUtil.DATA_ACCESS_PROBLEM);
		}
	}
	
}
