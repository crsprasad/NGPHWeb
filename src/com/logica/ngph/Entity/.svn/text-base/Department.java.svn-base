package com.logica.ngph.Entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="DEPARTMENTS")
public class Department implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1521727134634279445L;
	private String deptCode;
	private String deptBranch;
	private String deptName;
	
	@Id
	@Column(name="DEPT_CODE")
	public String getDeptCode() {
		return deptCode;
	}
	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}
	
	@Column(name="DEPT_BRANCH")
	public String getDeptBranch() {
		return deptBranch;
	}
	public void setDeptBranch(String deptBranch) {
		this.deptBranch = deptBranch;
	}
	
	@Column(name="DEPT_NAME")
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	

	

	//DEPT_CODE, DEPT_BRANCH, DEPT_NAME

}
