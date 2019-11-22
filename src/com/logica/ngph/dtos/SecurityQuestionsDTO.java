/**
 * 
 */
package com.logica.ngph.dtos;

import java.io.Serializable;

/**
 * @author chakkar
 *
 */
public class SecurityQuestionsDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String user;
	private String securityQuesion;
	private String securityAnswer;
	
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getSecurityQuesion() {
		return securityQuesion;
	}
	public void setSecurityQuesion(String securityQuesion) {
		this.securityQuesion = securityQuesion;
	}
	public String getSecurityAnswer() {
		return securityAnswer;
	}
	public void setSecurityAnswer(String securityAnswer) {
		this.securityAnswer = securityAnswer;
	}
	
	
	
	

}
