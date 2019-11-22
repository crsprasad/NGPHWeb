package com.logica.ngph.Entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="ARCH_EODSOD_LOG")
public class SodEodLog implements Serializable {
	private static final long serialVersionUID = 5490734814589126343L;
	private String tableName;
	private String operation;
	private int rowsAffected;
	private String statuss;
	private String reason;
	private String condition;
	
	@Column(name="CONDITION")
	public String getCondition() {
		return condition;
	}
	public void setCondition(String condition) {
		this.condition = condition;
	}
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
	public String getStatuss() {
		return statuss;
	}
	public void setStatuss(String statuss) {
		this.statuss = statuss;
	}
	@Column(name="REASON")
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
		
}

	
	
