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
@Table(name="TA_FUNCTIONS_GROUP")
public class FunctionGroup implements Serializable{
	
	private static final long serialVersionUID = -3528522595555265105L;

	private String functionGroupId;
	private String functionGroupName;
	
	@Id
	@Column(name="FUNCTION_GROUP_ID")
	public String getFunctionGroupId() {
		return functionGroupId;
	}
	public void setFunctionGroupId(String functionGroupId) {
		this.functionGroupId = functionGroupId;
	}
	
	@Column(name="FUNCTION_GROUP_NAME")
	public String getFunctionGroupName() {
		return functionGroupName;
	}
	public void setFunctionGroupName(String functionGroupName) {
		this.functionGroupName = functionGroupName;
	}
	
}
