package com.logica.ngph.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.logica.ngph.Entity.Rules;
import com.logica.ngph.common.ConstantUtil;
import com.logica.ngph.common.NGPHException;
import com.logica.ngph.dao.RuleDao;
import com.logica.ngph.dtos.SearchDTO;
import com.logica.ngph.dtos.SearchRuleIDDtos;

public class SearchService {
	static Logger logger = Logger.getLogger(SearchService.class);
	RuleDao ruleDao;
	

	public RuleDao getRuleDao() {
		return ruleDao;
	}


	public void setRuleDao(RuleDao ruleDao) {
		this.ruleDao = ruleDao;
	}


	public List<SearchDTO> dataSearch(String code,String name,String action,String direction)throws NGPHException{
		
		 List<SearchDTO>	searchList = null;
		try{
			if(ConstantUtil.ACTION_BRANCH.equals(action)){
					searchList = ruleDao.getBranches(code, name);
			}
			if(ConstantUtil.ACTION_DEPARTMENT.equals(action) ){
				//System.out.println("calling department");	
				searchList = ruleDao.getDepartments(code, name);
			}
			if(ConstantUtil.ACTION_PARAM.equals(action) ){
				//System.out.println("calling department");	
				searchList = ruleDao.getActionParam(code, name,direction);
			}
		}catch(SQLException sqlException){
			logger.debug(sqlException);	
			throw new NGPHException(ConstantUtil.DATA_ACCESS_PROBLEM);
			}
		if(action == null){
			throw new NullPointerException(ConstantUtil.CODE_NAME_EMPTY);
		}
		
		return searchList;
	}
	public List<SearchRuleIDDtos> dataRuleIDSearch(String code)throws NGPHException{
		
		 List<SearchRuleIDDtos>	searchList = null;
		try{
				searchList = ruleDao.getRuleIDList(code);
			
		}catch(SQLException sqlException){
			logger.debug(sqlException);	
			throw new NGPHException(ConstantUtil.DATA_ACCESS_PROBLEM);
			}
		
		
		return searchList;
	}
	
	
	public List<String> getRuleList(String dropDownName)throws NGPHException{
		List<String> dropDwnList = new ArrayList<String>();
		
		try{
			if(ConstantUtil.MESSAGE_TYPE.equals(dropDownName)){
				dropDwnList =  ruleDao.getMessageTypes();
				System.out.println("calling msg type");
				
			}
			if(ConstantUtil.LHS.equals(dropDownName)){
				dropDwnList =  ruleDao.getLHS();
				
			}
			if(ConstantUtil.ACTIONS.equals(dropDownName)){
				dropDwnList =  ruleDao.getActions();
				
			}
			
		}catch(SQLException sqlException){
			logger.debug(sqlException);
			throw new NGPHException(ConstantUtil.DATA_ACCESS_PROBLEM);
		}
		
		System.out.println("list size in service"+dropDwnList.size());
		return dropDwnList;
	}
	
	public void saveRules(Rules rules)throws NGPHException{
		
		try {
			ruleDao.saverule(rules);
		} catch (SQLException sqlException) {
			logger.debug(sqlException);
			throw new NGPHException(ConstantUtil.DATA_ACCESS_PROBLEM);
		}
	}
	
public void updateRules(Rules rules)throws NGPHException{
		
		try {
			ruleDao.updateRule(rules);
		} catch (SQLException sqlException) {
			logger.debug(sqlException);
			throw new NGPHException(ConstantUtil.DATA_ACCESS_PROBLEM);
		}
	}
public void deleteRules(Rules rules)throws NGPHException{
	
	try {
		ruleDao.deleteRule(rules);
	} catch (SQLException sqlException) {
		logger.debug(sqlException);
		throw new NGPHException(ConstantUtil.DATA_ACCESS_PROBLEM);
	}
}
	
	public List<String> getRuleID() throws NGPHException{
		List<String> ruleIdList = null;
		try {
			ruleIdList = ruleDao.getRuleID();
		} catch (SQLException sqlException) {
			logger.debug(sqlException);
			throw new NGPHException(ConstantUtil.DATA_ACCESS_PROBLEM);
		}
		return ruleIdList;
		
	}
	public String insertDelimtedString(String delimtedStringVAlue)
	{
		return ruleDao.insertDelimtedString(delimtedStringVAlue);
	}
	

}
