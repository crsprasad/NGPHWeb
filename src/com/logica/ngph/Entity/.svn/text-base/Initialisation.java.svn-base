package com.logica.ngph.Entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;


@Entity
@Table(name="INITIALISATIONM")
public class Initialisation implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 81558707192947621L;
	/*INIT_BRANCH, 
	INIT_ENTRY,
	INIT_VALUE, 
	INIT_COMMENTS*/
	private String initBranch;
	private String initEntry;
	private String initValue;
	private String initComments;
	
	
	@Column(name="INIT_BRANCH")
	public String getInitBranch() {
		return initBranch;
	}
	public void setInitBranch(String initBranch) {
		this.initBranch = initBranch;
	}
	@Id
	@Column(name="INIT_ENTRY")
	public String getInitEntry() {
		return initEntry;
	}
	public void setInitEntry(String initEntry) {
		this.initEntry = initEntry;
	}
	@Lob
	@Column(name="INIT_VALUE")
	public String getInitValue() {
		return initValue;
	}
	public void setInitValue(String initValue) {
		this.initValue = initValue;
	}
	@Column(name="INIT_COMMENTS")
	public String getInitComments() {
		return initComments;
	}
	public void setInitComments(String initComments) {
		this.initComments = initComments;
	}
	
	

}
