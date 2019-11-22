package com.logica.ngph.Entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="TA_RULES")
public class Rules implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3528522595555265105L;
	//private String ruleId;
	private String ruleId;
	
	private String ruleDescription;
	private String ruleCategory;
	private String ruleCondition;
	private String ruleAction;
	private String ruleActionParam;
	private String ruleType;
	private String ruleBranch;
	private String ruleDept;
	private String ruleMessageType;
	private String ruleDirection;
	
	private String ruleSysCondition;	
	private String ruleSubMessageType;


	/*private String 
	RULE_ID, 
	RULE_DESCRIPTION, 
	RULE_CATEGORY, 
	RULE_CONDITION, 
	RULE_ACTION, 
	RULE_ACT_PARAM,
	RULE_TYPE, 
	RULE_BRANCH, 
	RULE_DEPT,
	RULE_MSGTYPE*/
	
	@Column(name="RULE_SUBMSGTYPE")
	public String getRuleSubMessageType() {
		return ruleSubMessageType;
	}
	public void setRuleSubMessageType(String ruleSubMessageType) {
		this.ruleSubMessageType = ruleSubMessageType;
	}
	@Column(name="RULE_SYS_CONDITION")
	public String getRuleSysCondition() {
		return ruleSysCondition;
	}
	public void setRuleSysCondition(String ruleSysCondition) {
		this.ruleSysCondition = ruleSysCondition;
	}
	
	//@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "my_seq")
	//@SequenceGenerator(name = "my_seq", sequenceName = "rule_seq")
	
	
	@Column(name="RULE_DESCRIPTION")
	public String getRuleDescription() {
		return ruleDescription;
	}
	@Id
	@Column(name="RULE_ID")
	public String getRuleId() {
		return ruleId;
	}
	public void setRuleId(String ruleId) {
		this.ruleId = ruleId;
	}
	public void setRuleDescription(String ruleDescription) {
		this.ruleDescription = ruleDescription;
	}
	@Column(name="RULE_CATEGORY")
	public String getRuleCategory() {
		return ruleCategory;
	}
	public void setRuleCategory(String ruleCategory) {
		this.ruleCategory = ruleCategory;
	}
	
	@Column(name="RULE_CONDITION")
	public String getRuleCondition() {
		return ruleCondition;
	}
	public void setRuleCondition(String ruleCondition) {
		this.ruleCondition = ruleCondition;
	}
	
	@Column(name="RULE_ACTION")
	public String getRuleAction() {
		return ruleAction;
	}
	public void setRuleAction(String ruleAction) {
		this.ruleAction = ruleAction;
	}
	
	@Column(name="RULE_ACT_PARAM")
	public String getRuleActionParam() {
		return ruleActionParam;
	}
	public void setRuleActionParam(String ruleActionParam) {
		this.ruleActionParam = ruleActionParam;
	}
	
	@Column(name="RULE_TYPE")
	public String getRuleType() {
		return ruleType;
	}
	public void setRuleType(String ruleType) {
		this.ruleType = ruleType;
	}
	
	@Column(name="RULE_BRANCH")
	public String getRuleBranch() {
		return ruleBranch;
	}
	public void setRuleBranch(String ruleBranch) {
		this.ruleBranch = ruleBranch;
	}
	
	@Column(name="RULE_DEPT")
	public String getRuleDept() {
		return ruleDept;
	}
	public void setRuleDept(String ruleDept) {
		this.ruleDept = ruleDept;
	}
	
	@Column(name="RULE_MSGTYPE")
	public String getRuleMessageType() {
		return ruleMessageType;
	}
	public void setRuleMessageType(String ruleMessageType) {
		this.ruleMessageType = ruleMessageType;
	}
	@Column(name="RULE_DIRECTION")
	public String getRuleDirection() {
		return ruleDirection;
	}
	public void setRuleDirection(String ruleDirection) {
		this.ruleDirection = ruleDirection;
	}
	
	

}
