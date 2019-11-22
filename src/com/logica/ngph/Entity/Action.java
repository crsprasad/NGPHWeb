package com.logica.ngph.Entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ACTIONS")
public class Action implements Serializable{
	private static final long serialVersionUID = 1652601052485453175L;
	/*ACTIONS_RULE_CATEGORY,
	ACTIONS_ID, 
	ACTIONS_DESC,
	ACTIONS_PARAMS

	desc actions*/
	
	/**
	 * 
	 */
	
	private String actionsId;
	private String actionsRuleCategory;
	private String actionsDesc;
	private String actionsParams;
	@Id
	@Column(name="ACTIONS_ID")
	public String getActionsId() {
		return actionsId;
	}
	public void setActionsId(String actionsId) {
		this.actionsId = actionsId;
	}
	
	@Column(name="ACTIONS_RULE_CATEGORY")
	public String getActionsRuleCategory() {
		return actionsRuleCategory;
	}
	public void setActionsRuleCategory(String actionsRuleCategory) {
		this.actionsRuleCategory = actionsRuleCategory;
	}
	
	@Column(name="ACTIONS_DESC")
	public String getActionsDesc() {
		return actionsDesc;
	}
	public void setActionsDesc(String actionsDesc) {
		this.actionsDesc = actionsDesc;
	}
	
	@Column(name="ACTIONS_PARAMS")
	public String getActionsParams() {
		return actionsParams;
	}
	public void setActionsParams(String actionsParams) {
		this.actionsParams = actionsParams;
	}
	

}
