package com.logica.ngph.Entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name="TA_MODIFIEDMESSAGES")
public class ModifiedMessage implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6902596733051543306L;
	/*MSGS_REPAIRID,
	MSGS_MSGREF, 
	CHANGED_ORIGINAL_VALUES,
	CHANGED_REPAIRED_VALUES,
	REPAIR_COMMENT,
	MODIFIED_USER*/
	
	private Long msgsRepairId;

	private String msgsRef;
	private String changedOriginalValues;
	private String changedRepairedValues;
	private String repairComment;
	private String modifiedUser;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "my_seq")
	@SequenceGenerator(name = "my_seq", sequenceName = "repair_seq")
	
	@Column(name="MSGS_REPAIRID")
	public Long getMsgsRepairId() {
		return msgsRepairId;
	}
	public void setMsgsRepairId(Long msgsRepairId) {
		this.msgsRepairId = msgsRepairId;
	}
	

	@Column(name="MSGS_MSGREF")
	public String getMsgsRef() {
		return msgsRef;
	}
	
	public void setMsgsRef(String msgsRef) {
		this.msgsRef = msgsRef;
	}
	@Column(name="CHANGED_ORIGINAL_VALUES")
	public String getChangedOriginalValues() {
		return changedOriginalValues;
	}
	public void setChangedOriginalValues(String changedOriginalValues) {
		this.changedOriginalValues = changedOriginalValues;
	}
	@Column(name="CHANGED_REPAIRED_VALUES")
	public String getChangedRepairedValues() {
		return changedRepairedValues;
	}
	public void setChangedRepairedValues(String changedRepairedValues) {
		this.changedRepairedValues = changedRepairedValues;
	}
	
	@Column(name="REPAIR_COMMENT")
	public String getRepairComment() {
		return repairComment;
	}
	public void setRepairComment(String repairComment) {
		this.repairComment = repairComment;
	}
	@Column(name="MODIFIED_USER")
	public String getModifiedUser() {
		return modifiedUser;
	}
	public void setModifiedUser(String modifiedUser) {
		this.modifiedUser = modifiedUser;
	}
	
	
}
