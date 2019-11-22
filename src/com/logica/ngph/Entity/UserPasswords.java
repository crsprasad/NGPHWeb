/**
 * 
 */
package com.logica.ngph.Entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author chakkar
 *
 */
@Entity
@Table(name="TA_SEC_USERS_PASSWORDS")
public class UserPasswords implements Serializable { 
	
	private static final long serialVersionUID = -8266691896380176897L;
		
	private String user;
	private String userPassword;
	private String userPassword1;
	private String userPassword2;
	private String userPassword3;
	private String userPassword4;
	private Timestamp lastModTime;
	
	@Id
	@Column(name="USR")
	public String getUser() {
		return user;
	}
	
	public void setUser(String user) {
		this.user = user;
	}
	
	@Column(name="PASSWORD")
	public String getUserPassword() {
		return userPassword;
	}
	
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	
	@Column(name="PASSWORD_1")
	public String getUserPassword1() {
		return userPassword1;
	}
	
	public void setUserPassword1(String userPassword1) {
		this.userPassword1 = userPassword1;
	}
	
	@Column(name="PASSWORD_2")
	public String getUserPassword2() {
		return userPassword2;
	}
	
	public void setUserPassword2(String userPassword2) {
		this.userPassword2 = userPassword2;
	}
	
	@Column(name="PASSWORD_3")
	public String getUserPassword3() {
		return userPassword3;
	}
	
	public void setUserPassword3(String userPassword3) {
		this.userPassword3 = userPassword3;
	}
	
	@Column(name="PASSWORD_4")
	public String getUserPassword4() {
		return userPassword4;
	}
	
	public void setUserPassword4(String userPassword4) {
		this.userPassword4 = userPassword4;
	}
		
	@Column(name="LAST_PASS_RESET_DATE_TIME")
	public Timestamp getLastModTime() {
		return lastModTime;
	}
	
	public void setLastModTime(Timestamp lastModTime) {
		this.lastModTime = lastModTime;
	}

	
}
