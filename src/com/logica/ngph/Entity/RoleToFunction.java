package com.logica.ngph.Entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="TA_ROLE_TO_FUNCTION")
public class RoleToFunction implements Serializable{
	
	private static final long serialVersionUID = -3528522595555265105L;
	private String roleId;
	private String functionId;
	
	@Id
	@Column(name="ROLE_ID")
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	@Column(name="FUNCTION_ID")
	public String getFunctionId() {
		return functionId;
	}
	public void setFunctionId(String functionId) {
		this.functionId = functionId;
	}
	
	
	
	
}
