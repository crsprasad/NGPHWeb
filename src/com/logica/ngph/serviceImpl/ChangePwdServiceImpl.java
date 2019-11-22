package com.logica.ngph.serviceImpl;

import com.logica.ngph.dao.ChangePwdDao;
import com.logica.ngph.dtos.UserPasswordsDTO;
import com.logica.ngph.service.ChangePwdService;
import com.logica.ngph.utils.StringEncrypter;

public class ChangePwdServiceImpl implements ChangePwdService{
	ChangePwdDao changePwdDao;

	public ChangePwdDao getChangePwdDao() {
		return changePwdDao;
	}

	public void setChangePwdDao(ChangePwdDao changePwdDao) {
		this.changePwdDao = changePwdDao;
	}

	
	public Boolean isOldPwdMatches(String userID,String oldpwd) {
		try{
			String str1=StringEncrypter.encryptURL(userID, oldpwd);
			str1 = new String(new sun.misc.BASE64Encoder().encode(str1.getBytes()));
			return changePwdDao.isOldPwdMatches(userID,str1);
		}catch (Exception e) {
			
		}
		return null;
	}

	
	public String changePwd(String userID, String newPwd, UserPasswordsDTO userPasswordsDTO) {
		try{
			String str1=StringEncrypter.encryptURL(userID, newPwd);
			str1 = new String(new sun.misc.BASE64Encoder().encode(str1.getBytes()));
			return changePwdDao.changePwd(userID,str1,userPasswordsDTO);
			
		}catch (Exception e) {
			return null;
		}
	}

	
	public UserPasswordsDTO getOldPwds(String userId) {
		UserPasswordsDTO userPasswordsDTO = new UserPasswordsDTO();
		try{
			userPasswordsDTO = changePwdDao.getOldPwds(userId);
			return userPasswordsDTO;
		}catch (Exception e) {
			return null;
		}
	}

	@Override
	public String getPassPolicyIsReq(String initEntry) throws Exception {
		String initValue = null;
		try{
			initValue = changePwdDao.getPassPolicyIsReq(initEntry);
			return initValue;
		}catch (Exception e) {
			return null;
		}
	}

}
