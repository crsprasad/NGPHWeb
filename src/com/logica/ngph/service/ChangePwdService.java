package com.logica.ngph.service;

import com.logica.ngph.dtos.UserPasswordsDTO;

public interface ChangePwdService {
	
public Boolean isOldPwdMatches(String userID,String oldpwd);
public String changePwd(String userID,String newPwd, UserPasswordsDTO userPasswordsDTO);
public UserPasswordsDTO getOldPwds(String userId);
public String getPassPolicyIsReq(String initEntry)throws Exception;

}
