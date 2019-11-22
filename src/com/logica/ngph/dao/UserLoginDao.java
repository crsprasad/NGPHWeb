package com.logica.ngph.dao;

import com.logica.ngph.common.dtos.UserMaintenanceDTO;

public interface UserLoginDao {
public boolean validateUser(String user,String passWord);
public boolean validateUserId(String userId);
public UserMaintenanceDTO getLogInTimeDetails(String user);
public boolean isValidUser(String userId);
}
