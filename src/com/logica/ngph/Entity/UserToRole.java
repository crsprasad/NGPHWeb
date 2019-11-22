package com.logica.ngph.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="TA_SEC_USERS_TO_ROLES")
public class UserToRole {
	private String userId;
	private String roleId;
	
	@Id
	@Column(name="USER_ID")
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	@Column(name="ROLE_ID")
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

}
