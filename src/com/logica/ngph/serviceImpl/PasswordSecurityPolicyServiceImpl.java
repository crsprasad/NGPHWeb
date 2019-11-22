/**
 * 
 */
package com.logica.ngph.serviceImpl;

import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

import com.logica.ngph.Entity.Roles;
import com.logica.ngph.common.ConstantUtil;
import com.logica.ngph.common.NGPHException;
import com.logica.ngph.dao.PasswordSecurityPolicyDao;
import com.logica.ngph.dtos.PasswordSecurityPolicyDto;
import com.logica.ngph.dtos.SecurityQuestionsDTO;
import com.logica.ngph.service.PasswordSecurityPolicyService;

/**
 * @author chakkar
 *
 */
public class PasswordSecurityPolicyServiceImpl implements PasswordSecurityPolicyService {

	static Logger logger = Logger.getLogger(UserMaintenanceServiceImpl.class);
	
	PasswordSecurityPolicyDao passwordSecurityPolicyDao ;
	
	
	
	
	public PasswordSecurityPolicyDao getPasswordSecurityPolicyDao() {
		return passwordSecurityPolicyDao;
	}

	public void setPasswordSecurityPolicyDao(
			PasswordSecurityPolicyDao passwordSecurityPolicyDao) {
		this.passwordSecurityPolicyDao = passwordSecurityPolicyDao;
	}




	public PasswordSecurityPolicyDto getSecurityPolicy() throws NGPHException {
		try {
			return passwordSecurityPolicyDao.getSecurityPolicy();
		} catch (SQLException sqlException) {
			logger.debug(sqlException);
			throw new NGPHException(ConstantUtil.DATA_ACCESS_PROBLEM);
		}
	}

	@Override
	public void updateSecutiyPolicy(PasswordSecurityPolicyDto passwordSecurityPolicyDto)throws NGPHException {
		try {
			  passwordSecurityPolicyDao.updateSecutiyPolicy(passwordSecurityPolicyDto);
		} catch (SQLException sqlException) {
			logger.debug(sqlException);
			throw new NGPHException(ConstantUtil.DATA_ACCESS_PROBLEM);
		}
		
	}

	@Override
	public SecurityQuestionsDTO getSecurityQuestionDetails(String user) throws NGPHException {
		
		try {
			return passwordSecurityPolicyDao.getSecurityQuestionDetails(user);
		} catch (SQLException sqlException) {
			logger.debug(sqlException);
			throw new NGPHException(ConstantUtil.DATA_ACCESS_PROBLEM);
		}
	}
	
	
	
}
