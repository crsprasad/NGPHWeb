/**
 * 
 */
package com.logica.ngph.dtos;

import java.io.Serializable;

/**
 * @author chakkar
 *
 */
public class ArchivalTaskDto implements Serializable {
	
	private String tableName;
	private String operation;
	private int rowsAffected;
	private String condition;
	private String status;
	private String reason;
	
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public String getOperation() {
		return operation;
	}
	public void setOperation(String operation) {
		this.operation = operation;
	}
	public int getRowsAffected() {
		return rowsAffected;
	}
	public void setRowsAffected(int rowsAffected) {
		this.rowsAffected = rowsAffected;
	}
	public String getCondition() {
		return condition;
	}
	public void setCondition(String condition) {
		this.condition = condition;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}

	
	

}
