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
@Table(name="ARCH_LCBG_LOG")
public class ArchivalLCBGLog implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String tableName;
	private String operation;
	private int rowsAffected;
	private String status;
	private String reason;
	private String condition;
	
	@Id
	@Column(name="TABLE_NAME")
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	
	@Column(name="OPERATION")
	public String getOperation() {
		return operation;
	}
	public void setOperation(String operation) {
		this.operation = operation;
	}
	
	@Column(name="ROWS_AFFECTED")
	public int getRowsAffected() {
		return rowsAffected;
	}
	public void setRowsAffected(int rowsAffected) {
		this.rowsAffected = rowsAffected;
	}
	
	@Column(name="STATUS")
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	@Column(name="REASON")
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	
	@Column(name="CONDITION")
	public String getCondition() {
		return condition;
	}
	public void setCondition(String condition) {
		this.condition = condition;
	}
	
	

}
