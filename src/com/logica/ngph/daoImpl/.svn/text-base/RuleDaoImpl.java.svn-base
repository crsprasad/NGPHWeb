package com.logica.ngph.daoImpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.logica.ngph.Entity.Rules;
import com.logica.ngph.dao.RuleDao;
import com.logica.ngph.dtos.SearchDTO;
import com.logica.ngph.dtos.SearchRuleIDDtos;

public class RuleDaoImpl extends HibernateDaoSupport implements RuleDao{

	
	@Transactional(propagation=Propagation.REQUIRED)
	public void saverule(Rules rules) throws SQLException  {
		
			getHibernateTemplate().saveOrUpdate(rules);
		
		
	}
	
	
	public List<SearchDTO> getBranches(String code,String name) throws SQLException{
		List<SearchDTO> searchList = new ArrayList<SearchDTO>();
		List branches = new ArrayList<SearchDTO>(); 
		if(StringUtils.isEmpty(code)){
			code = "";
		}else{
			code = "%"+code+"%";
		}
		if(StringUtils.isEmpty(name)){
			name = "";
		}else{
			name = "%"+name+"%";
		}
		if(!StringUtils.isEmpty(code) && !StringUtils.isEmpty(name))
			branches = 	getHibernateTemplate().find("select branch.branchCode,branch.branchName from com.logica.ngph.Entity.Branches as branch  where branch.branchCode like ? or branch.branchName like ?",
				code,name);
		else if(!StringUtils.isEmpty(code) && StringUtils.isEmpty(name)){
			branches = 	getHibernateTemplate().find("select branch.branchCode,branch.branchName from com.logica.ngph.Entity.Branches as branch  where branch.branchCode like ?",code);
		}else if(StringUtils.isEmpty(code) && !StringUtils.isEmpty(name)){
			branches = 	getHibernateTemplate().find("select branch.branchCode,branch.branchName from com.logica.ngph.Entity.Branches as branch  where branch.branchName like ?",name);
		}else{
			branches = 	getHibernateTemplate().find("select branch.branchCode,branch.branchName from com.logica.ngph.Entity.Branches as branch");
		}
		
		 createSearchList(searchList, branches);
		
			return searchList;
			}
		



	
	public List<SearchDTO> getDepartments(java.lang.String code,
			java.lang.String name) throws SQLException{
		List<SearchDTO> searchList = new ArrayList<SearchDTO>();
		List departments = new ArrayList<SearchDTO>();
		
		if(StringUtils.isEmpty(code)){
			code = "";
		}else{
			code = "%"+code+"%";
		}
		if(StringUtils.isEmpty(name)){
			name = "";
		}else{
			name = "%"+name+"%";
		}
		
		if(!StringUtils.isEmpty(code) && !StringUtils.isEmpty(name)){
			departments = 	getHibernateTemplate().find("select dept.deptCode,dept.deptName from com.logica.ngph.Entity.Department as dept  where dept.deptCode like ? or dept.deptName like ?",
				code,name);
		}else if(!StringUtils.isEmpty(code) && StringUtils.isEmpty(name)){
			departments = 	getHibernateTemplate().find("select dept.deptCode,dept.deptName from com.logica.ngph.Entity.Department as dept  where dept.deptCode like ?",code);
		}else if(StringUtils.isEmpty(code) && !StringUtils.isEmpty(name)){
			departments = 	getHibernateTemplate().find("select dept.deptCode,dept.deptName from com.logica.ngph.Entity.Department as dept  where dept.deptName like ?",name);
		}else{
			departments = 	getHibernateTemplate().find("select dept.deptCode,dept.deptName from com.logica.ngph.Entity.Department as dept");
		}
		System.out.println("data size"+ departments.size());
		
		if(departments != null){
		
        createSearchList(searchList, departments);
		}
		
		
	return searchList;
	}
	
	public List<SearchDTO> getActionParam(String code,String name,String direction) throws SQLException{
		List<SearchDTO> searchList = new ArrayList<SearchDTO>();
		
		if(code == null){
			code = "";
		}else{
			code = "%"+code+"%";
		}
		if(name == null){
			name = "";
		}else{
			name = "%"+name+"%";
		}
		if(direction.equals("I")){
			direction = "H";
		}else if (direction.equals("O")){
			direction = "P";
		}
		
		
	@SuppressWarnings("rawtypes")
	List ActionParam = 	getHibernateTemplate().find("select eiCode,eiName,eiType  from com.logica.ngph.Entity.EI where (eiCode like ? or eiName like ?) and eiType ='"+direction+"'",
				code,name);
		System.out.println("data size"+ ActionParam.size());
		
		if(ActionParam != null){
			for (int i = 0; i < ActionParam.size(); i++) {
	        	SearchDTO searchDTO = new SearchDTO();
	            Object[] obj = (Object[]) ActionParam.get(i);

	            String EICode = (String) obj[0];
	            searchDTO.setCode(EICode);
	            String EIName = (String) obj[1];
	            searchDTO.setName(EIName);
	            String EIType = (String) obj[2];
	            searchDTO.setType(EIType);
	            searchList.add(searchDTO);
	        }
        
		}
		
		
	return searchList;
	}
	
	
	private void createSearchList(List<SearchDTO> searchList, @SuppressWarnings("rawtypes") List daoList) {
		for (int i = 0; i < daoList.size(); i++) {
        	SearchDTO searchDTO = new SearchDTO();
            Object[] obj = (Object[]) daoList.get(i);

            String deptCode = (String) obj[0];
            searchDTO.setCode(deptCode);
            String deptName = (String) obj[1];
            searchDTO.setName(deptName);
            
             
            searchList.add(searchDTO);
        }
	}
	@SuppressWarnings("unchecked")
	
	public List<String> getMessageTypes() throws SQLException{
		
		@SuppressWarnings("rawtypes")
		List msgTypeList = getHibernateTemplate().
		find("select concat(msgSupports.supportMsgType,msgSupports.supportSubMsgType) as msgType from com.logica.ngph.Entity.MsgSupport as msgSupports");
	
		return msgTypeList;
	}
	
	@SuppressWarnings("unchecked")
	
	public List<String> getLHS() throws SQLException{
		
		List<String> messageFieldsList = getHibernateTemplate()
		.find("select msgFields.fieldsLogName as fieldlogName from com.logica.ngph.Entity.MsgField as msgFields");
		
		return messageFieldsList;
	}
	
	@SuppressWarnings("unchecked")
	
	public List<String> getActions() throws SQLException{
	
		List<String> actionList = getHibernateTemplate()
		.find("select actions.actionsId as actionsId from com.logica.ngph.Entity.Action as actions");
	
		return actionList;
	}
	@SuppressWarnings("unchecked")
	
	public List<String> getRuleID() throws SQLException{
		List<String> ruleIdList = getHibernateTemplate()
		.find("select rules.ruleId as rulesId from com.logica.ngph.Entity.Rules as rules");
		
	return ruleIdList;
	}

	
	public List<SearchRuleIDDtos> getRuleIDList(String code) throws SQLException {
			List<SearchRuleIDDtos> searchList = new ArrayList<SearchRuleIDDtos>();
		
		if(code == null){
			code = "%";
		}else{
			code = "%"+code+"%";
		}
		
		@SuppressWarnings("rawtypes")
		List ruleID = 	getHibernateTemplate().find("select ruleId,ruleDescription,ruleCategory,ruleCondition,ruleAction,ruleActionParam,ruleType,ruleBranch,ruleDept,ruleMessageType,ruleSysCondition,ruleSubMessageType,ruleDirection from com.logica.ngph.Entity.Rules where ruleId like ?",code);
		
		for (int i = 0; i < ruleID.size(); i++) {
			SearchRuleIDDtos searchRuleIDDtos = new SearchRuleIDDtos();
            Object[] obj = (Object[]) ruleID.get(i);

            String ruleId = (String) obj[0];
            searchRuleIDDtos.setSearchRuleID(ruleId);
            String description = (String) obj[1];
            searchRuleIDDtos.setSearchDescription(description);
            searchRuleIDDtos.setRuleCategory((String) obj[2]);
            searchRuleIDDtos.setRuleCondition((String) obj[3]);
            searchRuleIDDtos.setRuleAction((String) obj[4]);
            searchRuleIDDtos.setRuleActionParam((String) obj[5]);
            searchRuleIDDtos.setRuleType((String) obj[6]);
            
            searchRuleIDDtos.setRuleBranch((String) obj[7]);
            searchRuleIDDtos.setRuleDept((String) obj[8]);
            searchRuleIDDtos.setRuleMessageType((String) obj[9]);
            searchRuleIDDtos.setRuleSysCondition((String) obj[10]);
            searchRuleIDDtos.setRuleSubMessageType((String) obj[11]);
            searchRuleIDDtos.setRuleDirection((String) obj[12]);
             
            searchList.add(searchRuleIDDtos);
        }
		
			return searchList;
			}

	@Transactional(propagation=Propagation.REQUIRED)
	public void updateRule(Rules rules) throws SQLException {
		getHibernateTemplate().update(rules);
		
	}

	@Transactional(propagation=Propagation.REQUIRED)
	public void deleteRule(Rules rules) throws SQLException {
		getHibernateTemplate().delete(rules);
		
	}

	
	public String insertDelimtedString(String delimtedStringVAlue) {
		String key="";
		try{
		SessionFactory fact=getHibernateTemplate().getSessionFactory();
		Session sess=fact.openSession();
		Connection con=sess.connection();
		
		
		Query seq = sess.createSQLQuery("select GENERICSCREENSEQUENCE.nextval from dual" );
	    key = seq.uniqueResult().toString();
		
			String queryListData="insert into TXN_SCREENDATA (TXNREF,STATUS,SCREEN_ID,SCREENDATA) values ('"+key+"','P','Rules','"+delimtedStringVAlue+"')";
			Query q = sess.createSQLQuery(queryListData);
			int resultSet= q.executeUpdate();
			System.out.println(resultSet+" Record updated");

			sess.close();
			fact.close();
			con.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return key;
	}
	
	
	
}
