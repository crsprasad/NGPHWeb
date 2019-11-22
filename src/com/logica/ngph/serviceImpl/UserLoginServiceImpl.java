package com.logica.ngph.serviceImpl;

import com.logica.ngph.common.dtos.UserMaintenanceDTO;
import com.logica.ngph.dao.UserLoginDao;
import com.logica.ngph.service.UserLoginService;

public class UserLoginServiceImpl implements UserLoginService{
	private UserLoginDao userLoginDao;

	public UserLoginDao getUserLoginDao() {
		return userLoginDao;
	}

	public void setUserLoginDao(UserLoginDao userLoginDao) {
		this.userLoginDao = userLoginDao;
	}

	
	public boolean getValidateUser(String user, String userPassword) {
		return userLoginDao.validateUser(user, userPassword);
	}

	
	public UserMaintenanceDTO getLogInTimeDetails(String user) {		
		return userLoginDao.getLogInTimeDetails(user);
	}

	
	public boolean validateUserId(String userId) {		
		return userLoginDao.validateUserId(userId);
	}

	
	public boolean isValidUser(String userId) {
		return userLoginDao.isValidUser(userId);
	}
	
	
	
}
