package com.logica.ngph.dtos;

import java.io.Serializable;

public class SearchRuleIDDtos implements Serializable{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String searchRuleID;
	private String searchDescription;
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
	
	public String getRuleCategory() {
		return ruleCategory;
	}
	public void setRuleCategory(String ruleCategory) {
		this.ruleCategory = ruleCategory;
	}
	public String getRuleCondition() {
		return ruleCondition;
	}
	public void setRuleCondition(String ruleCondition) {
		this.ruleCondition = ruleCondition;
	}
	public String getRuleAction() {
		return ruleAction;
	}
	public void setRuleAction(String ruleAction) {
		this.ruleAction = ruleAction;
	}
	public String getRuleActionParam() {
		return ruleActionParam;
	}
	public void setRuleActionParam(String ruleActionParam) {
		this.ruleActionParam = ruleActionParam;
	}
	public String getRuleType() {
		return ruleType;
	}
	public void setRuleType(String ruleType) {
		this.ruleType = ruleType;
	}
	public String getRuleBranch() {
		return ruleBranch;
	}
	public void setRuleBranch(String ruleBranch) {
		this.ruleBranch = ruleBranch;
	}
	public String getRuleDept() {
		return ruleDept;
	}
	public void setRuleDept(String ruleDept) {
		this.ruleDept = ruleDept;
	}
	public String getRuleMessageType() {
		return ruleMessageType;
	}
	public void setRuleMessageType(String ruleMessageType) {
		this.ruleMessageType = ruleMessageType;
	}
	public String getRuleSysCondition() {
		return ruleSysCondition;
	}
	public void setRuleSysCondition(String ruleSysCondition) {
		this.ruleSysCondition = ruleSysCondition;
	}
	public String getRuleSubMessageType() {
		return ruleSubMessageType;
	}
	public void setRuleSubMessageType(String ruleSubMessageType) {
		this.ruleSubMessageType = ruleSubMessageType;
	}
	
	
	public String getSearchRuleID() {
		return searchRuleID;
	}
	public void setSearchRuleID(String searchRuleID) {
		this.searchRuleID = searchRuleID;
	}
	public void setSearchDescription(String searchDescription) {
		this.searchDescription = searchDescription;
	}
	public String getSearchDescription() {
		return searchDescription;
	}
	public void setRuleDirection(String ruleDirection) {
		this.ruleDirection = ruleDirection;
	}
	public String getRuleDirection() {
		return ruleDirection;
	}
		
}
