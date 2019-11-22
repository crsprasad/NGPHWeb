package com.logica.ngph.Entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;
@Entity
@Table(name="TA_SEC_USERS_TO_ROLES")
public class TA_SEC_USERS_TO_ROLES implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String getUSER_ID() {
		return USER_ID;
	}
	public void setUSER_ID(String uSER_ID) {
		USER_ID = uSER_ID;
	}
	public String getROLE_ID() {
		return ROLE_ID;
	}
	public void setROLE_ID(String rOLE_ID) {
		ROLE_ID = rOLE_ID;
	}
	private String USER_ID;
	private String ROLE_ID;
	

}
