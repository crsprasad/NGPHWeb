/**
 * 
 */
package com.logica.ngph.dao;

import java.sql.SQLException;

import com.logica.ngph.dtos.PasswordSecurityPolicyDto;
import com.logica.ngph.dtos.SecurityQuestionsDTO;

/**
 * @author chakkar
 *
 */
public interface PasswordSecurityPolicyDao {
	
	public PasswordSecurityPolicyDto getSecurityPolicy()throws SQLException;
	public void updateSecutiyPolicy(PasswordSecurityPolicyDto passwordSecurityPolicyDto)throws SQLException;
	public SecurityQuestionsDTO getSecurityQuestionDetails(String user)throws SQLException;

}
