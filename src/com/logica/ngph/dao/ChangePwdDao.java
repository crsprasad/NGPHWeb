package com.logica.ngph.dao;



import com.logica.ngph.dtos.UserPasswordsDTO;

public interface ChangePwdDao {
	public Boolean isOldPwdMatches(String userID,String oldpwd);
	public String changePwd(String userID, String newPwd,UserPasswordsDTO userPasswordsDTO);
	public UserPasswordsDTO getOldPwds(String userId)throws Exception;
	public String getPassPolicyIsReq(String initEntry)throws Exception;
}
