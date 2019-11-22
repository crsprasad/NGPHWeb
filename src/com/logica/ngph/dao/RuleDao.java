package com.logica.ngph.dao;

import java.sql.SQLException;
import java.util.List;

import com.logica.ngph.Entity.Rules;
import com.logica.ngph.dtos.SearchDTO;
import com.logica.ngph.dtos.SearchRuleIDDtos;


public interface RuleDao {
	public void saverule(Rules rules) throws SQLException;
	public void updateRule(Rules rules) throws SQLException;
	public void deleteRule(Rules rules) throws SQLException;
	
	public List<SearchDTO> getBranches(String code,String name) throws SQLException;
	public List<SearchDTO> getDepartments(String code,String name) throws SQLException;
	public List<SearchDTO> getActionParam(String code,String name,String direction) throws SQLException;
	public List<String> getMessageTypes() throws SQLException;
	public List<String> getLHS() throws SQLException;
	public List<String> getActions() throws SQLException;
	public List<String> getRuleID() throws SQLException;
	public List<SearchRuleIDDtos> getRuleIDList(String code) throws SQLException;
	public String insertDelimtedString(String delimtedStringVAlue);
	

}
