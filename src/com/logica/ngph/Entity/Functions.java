package com.logica.ngph.Entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="TA_SEC_FUNCTIONS")
public class Functions implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3528522595555265105L;

	private String functionId;
	private String functionName;
	private String functionType;
	private String fieldId;
	
	
	@Id
	@Column(name="FUNCTION_ID")
	public String getFunctionId() {
		return functionId;
	}
	public void setFunctionId(String functionId) {
		this.functionId = functionId;
	}
	@Column(name="FUNCTION_NAME")
	public String getFunctionName() {
		return functionName;
	}
	public void setFunctionName(String functionName) {
		this.functionName = functionName;
	}
	@Column(name="FUNCTION_TYPE")
	public String getFunctionType() {
		return functionType;
	}
	public void setFunctionType(String functionType) {
		this.functionType = functionType;
	}
	@Column(name="FIELD_ID")
	public String getFieldId() {
		return fieldId;
	}
	public void setFieldId(String fieldId) {
		this.fieldId = fieldId;
	}
	
}
