/**
 * 
 */
package com.logica.ngph.Entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author chakkar
 *
 */
@Entity
@Table(name="PASSWORD_SECURITY_QUESTIONS")
public class SecurityQuesions implements Serializable {
	
	private static final long serialVersionUID = 3009143549823566044L;
	
	private String securityQuesionID;
	private String securityQuesionName;
	
	@Id
	@Column(name="SECURITY_QUESTION_ID")
	public String getSecurityQuesionID() {
		return securityQuesionID;
	}
	public void setSecurityQuesionID(String securityQuesionID) {
		this.securityQuesionID = securityQuesionID;
	}
	
	@Column(name="SECURITY_QUESTIONS")
	public String getSecurityQuesionName() {
		return securityQuesionName;
	}
	public void setSecurityQuesionName(String securityQuesionName) {
		this.securityQuesionName = securityQuesionName;
	}
	
	

}
