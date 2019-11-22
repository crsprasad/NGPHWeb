/**
 * 
 */
package com.logica.ngph.service;


import com.logica.ngph.common.NGPHException;
import com.logica.ngph.dtos.PasswordSecurityPolicyDto;
import com.logica.ngph.dtos.SecurityQuestionsDTO;

/**
 * @author chakkar
 *
 */
public interface PasswordSecurityPolicyService {

	public PasswordSecurityPolicyDto getSecurityPolicy()throws NGPHException;
	public void updateSecutiyPolicy(PasswordSecurityPolicyDto passwordSecurityPolicyDto)throws NGPHException;
	public SecurityQuestionsDTO getSecurityQuestionDetails(String user)throws NGPHException;
	
}
