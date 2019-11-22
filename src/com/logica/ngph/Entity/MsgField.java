package com.logica.ngph.Entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="MSG_FIELDS")
public class MsgField implements Serializable{
	
	

	/* msg_fields

	FIELDS_LOG_NAME, 
	FIELDS_TAG,
	FIELDS_DATATYPE,
	FIELDS_XPATH,
	FIELDS_RULECATG, 
	FIELDS_INSEQ
*/
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8670961796419610242L;
	private String fieldsLogName;
	private String fieldsTag;
	private String fieldsDataType;
	private String fieldsXPath;
	private String fieldsRuleCategory;
	private int fieldsInSeq;
	
	
	
	@Column(name="FIELDS_TAG")
	public String getFieldsTag() {
		return fieldsTag;
	}
	
	@Id
	@Column(name="FIELDS_LOG_NAME")
	public String getFieldsLogName() {
		return fieldsLogName;
	}
	
	public void setFieldsLogName(String fieldsLogName) {
		this.fieldsLogName = fieldsLogName;
	}
	public void setFieldsTag(String fieldsTag) {
		this.fieldsTag = fieldsTag;
	}
	
	@Column(name="FIELDS_DATATYPE")
	public String getFieldsDataType() {
		return fieldsDataType;
	}
	public void setFieldsDataType(String fieldsDataType) {
		this.fieldsDataType = fieldsDataType;
	}
	
	@Column(name="FIELDS_XPATH")
	public String getFieldsXPath() {
		return fieldsXPath;
	}
	public void setFieldsXPath(String fieldsXPath) {
		this.fieldsXPath = fieldsXPath;
	}
	
	@Column(name="FIELDS_RULECATG")
	public String getFieldsRuleCategory() {
		return fieldsRuleCategory;
	}
	public void setFieldsRuleCategory(String fieldsRuleCategory) {
		this.fieldsRuleCategory = fieldsRuleCategory;
	}
	
	@Column(name="FIELDS_INSEQ")
	public int getFieldsInSeq() {
		return fieldsInSeq;
	}
	public void setFieldsInSeq(int fieldsInSeq) {
		this.fieldsInSeq = fieldsInSeq;
	}
}
