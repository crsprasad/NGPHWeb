package com.logica.ngph.Entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="TA_SEC_ROLES")
public class Roles implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3528522595555265105L;

	private String roleId;
	private String roleName;
	private String roleDescription;
	
	
	@Id
	@Column(name="ROLE_ID")
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String ruleId) {
		this.roleId = ruleId;
	}
	@Column(name="ROLE_NAME")
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	@Column(name="ROLE_DESC")
	public String getRoleDescription() {
		return roleDescription;
	}
	public void setRoleDescription(String roleDescription) {
		this.roleDescription = roleDescription;
	}
	
}
