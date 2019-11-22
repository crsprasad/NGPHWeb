/**
 * 
 */
package com.logica.ngph.dtos;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author chakkar
 *
 */
public class UserPasswordsDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String user;
	private String userPassword;
	private String userPassword1;
	private String userPassword2;
	private String userPassword3;
	private String userPassword4;
	private Timestamp lastModTime;
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public String getUserPassword1() {
		return userPassword1;
	}
	public void setUserPassword1(String userPassword1) {
		this.userPassword1 = userPassword1;
	}
	public String getUserPassword2() {
		return userPassword2;
	}
	public void setUserPassword2(String userPassword2) {
		this.userPassword2 = userPassword2;
	}
	public String getUserPassword3() {
		return userPassword3;
	}
	public void setUserPassword3(String userPassword3) {
		this.userPassword3 = userPassword3;
	}
	public String getUserPassword4() {
		return userPassword4;
	}
	public void setUserPassword4(String userPassword4) {
		this.userPassword4 = userPassword4;
	}
	public Timestamp getLastModTime() {
		return lastModTime;
	}
	public void setLastModTime(Timestamp lastModTime) {
		this.lastModTime = lastModTime;
	}
	
	

}
