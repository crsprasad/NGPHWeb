package com.logica.ngph.daoImpl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.logica.ngph.Entity.Functions;
import com.logica.ngph.Entity.RoleToFunction;
import com.logica.ngph.Entity.Roles;
import com.logica.ngph.common.NGPHException;
import com.logica.ngph.dao.RoleMaintenaneceDao;

public class RoleMaintenanceDaoImpl extends HibernateDaoSupport implements RoleMaintenaneceDao{
	
	
	public void saveRole(String id, String name, String desc, List<String> functionsList)throws SQLException{
	    System.out.println("in RoleMaintenanceDaoImpl<saveRole>");
		Session session =getSession();
		try{
	    session.beginTransaction();
	    Roles roles = new Roles();
	    roles.setRoleId(id);
	    roles.setRoleName(name);
	    roles.setRoleDescription(desc);
	    session.save(roles);
	    RoleToFunction roleToFunction = null;
	    for(String functions : functionsList){
	    	roleToFunction = new RoleToFunction();
	    	roleToFunction.setRoleId(id);
	    	roleToFunction.setFunctionId(functions);
	    	Query query =session.createSQLQuery("insert into TA_ROLE_TO_FUNCTION (ROLE_ID,FUNCTION_ID) values(:ROLE_ID,:FUNCTION_ID)");
	    	query.setString("ROLE_ID", id);
	    	query.setString("FUNCTION_ID", functions);
	    	query.executeUpdate();
	    }
	    session.getTransaction().commit();
		}catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
			
		}finally{
	    session.close();
		}
	    System.out.println("save completed");
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Functions> getFunctions(){
		    Session session =getSession();	
		    Query query = session.createQuery("from Functions");
		    List<Functions> functionsList =query.list();
		    session.close();
		    return functionsList;
	}

	public List<String> getAccessibleFunctions(String userId) {
		System.out.println("in RoleMaintenanceDaoImpl<getAccessibleFunctions>");
		Session session =getSession();
		List<String> accessbileFunctionsList = new ArrayList<String>();
		Query Query = session.createSQLQuery("SELECT FUNCTION_ID FROM TA_ROLE_TO_FUNCTION WHERE ROLE_ID IN(SELECT ROLE_ID FROM TA_SEC_USERS_TO_ROLES WHERE USER_ID = :USER_ID)");
		Query.setString("USER_ID", userId);
		List<Object> functionsList = Query.list();
		for(Object functionObject : functionsList ){
		   	accessbileFunctionsList.add((String)functionObject);
		}
		System.out.println("Inside Role DAO Impl <getAccessibleFunctions>: User Accesible  funtions from DB :"+accessbileFunctionsList.size());
		session.close();
		return accessbileFunctionsList;
	}
	
	
	public void modifyRole(String id, String name, String desc, List<String> functionsList)throws SQLException{
		System.out.println("in RoleMaintenanceDaoImpl<modifyRole>");
		Session session =getSession();
		try{
	    session.beginTransaction();
	    Roles roles = new Roles();
	    roles.setRoleId(id);
	    roles.setRoleName(name);
	    roles.setRoleDescription(desc);
	    session.update(roles);
	    RoleToFunction roleToFunction = null;
	    for(String functions : functionsList){
	    	roleToFunction = new RoleToFunction();
	    	roleToFunction.setRoleId(id);
	    	roleToFunction.setFunctionId(functions);

	    	Query query =session.createSQLQuery("DELETE FROM TA_ROLE_TO_FUNCTION WHERE ROLE_ID =:ROLE_ID");
	    	query.setString("ROLE_ID", id);
	    	query.executeUpdate();
	    	
	    }
	    
	    for(String functions : functionsList){
	    	roleToFunction = new RoleToFunction();
	    	roleToFunction.setRoleId(id);
	    	roleToFunction.setFunctionId(functions);

	    	//change as said by suresh
	    	//getHibernateTemplate().saveOrUpdate(roleToFunction);
	    	
	    	Query query =session.createSQLQuery("INSERT INTO TA_ROLE_TO_FUNCTION(ROLE_ID,FUNCTION_ID) values (:ROLE_ID,:FUNCTION_ID)");
	    	query.setString("ROLE_ID", id);
	    	query.setString("FUNCTION_ID", functions);
	    	query.executeUpdate();
	    }
	    session.getTransaction().commit();
	    System.out.println("Modify Completed");
		}catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		finally{
	    session.close();
		}
	   
	}
	
	
	public void deleteRole(String id)throws SQLException{
		System.out.println("in RoleMaintenanceDaoImpl<deleteRole>");
		Session session = getHibernateTemplate().getSessionFactory().openSession();
		try{
		session.beginTransaction();
		Query deleteQuery =session.createSQLQuery("delete from  TA_SEC_ROLES WHERE ROLE_ID = :ROLE_ID");
		deleteQuery.setString("ROLE_ID", id);
		deleteQuery.executeUpdate();
		deleteQuery =session.createSQLQuery("delete from  TA_ROLE_TO_FUNCTION WHERE ROLE_ID =:ROLE_ID");
		deleteQuery.setString("ROLE_ID", id);
		deleteQuery.executeUpdate();
		session.getTransaction().commit();
		System.out.println("Delete Completed");
		}catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}finally{
			session.close();
		}
		
		
	}
	
	
	public List<String> getRoleIDList(String roleId) throws SQLException {
		System.out.println("in RoleMaintenanceDaoImpl<getRoleIDList>");
		List<String> searchList = new ArrayList<String>();
		if(roleId == null){
			roleId = "%";
		}else{
			roleId = "%"+roleId+"%";
		}
		List<String> roleIdList = 	getHibernateTemplate().find("select roleId from com.logica.ngph.Entity.Roles where roleId like ?",roleId);
	    for(String roleIDs:roleIdList){
	    	searchList.add(roleIDs);
	    }
		return searchList;
	}
	
	
	public Roles getRoleData(String roleId) throws SQLException {
		System.out.println("Inside RoleMaintenanceDaoImpl<getRoleData>:");
		List list = getHibernateTemplate().find("select roleId,roleName,roleDescription from Roles where roleId =?",roleId);
		Roles roles = null;
		if(list!=null && !list.isEmpty() && list.size()>0){
		Object[] obj = (Object[]) list.get(0);
		
		roles  = new Roles();
		roles.setRoleId((String)obj[0]);
		roles.setRoleName((String) obj[1]);
		roles.setRoleDescription((String) obj[2]);
		
		}
		/*Roles userObject= (Roles)getSession().get(Roles.class, roleId);
		getSession().close();
		*/return roles;
	}
	
	
	public List<String> getAssignedFunctions(String roleId)throws SQLException{
		
		System.out.println("Inside RoleMaintenanceDaoImpl<getAssignedFunctions>:");
		Session session = getSession();
		List<String> assignedFunctionList = new ArrayList<String>();
		try{
		Query Query = session.createSQLQuery("SELECT FUNCTION_ID FROM TA_ROLE_TO_FUNCTION WHERE ROLE_ID = :ROLE_ID");
		Query.setString("ROLE_ID", roleId);
		assignedFunctionList = Query.list();
		}catch (Exception e) {
			e.printStackTrace();
			
		}finally{
		session.close();
		}
		return assignedFunctionList;
		
	}


	public Boolean isRolePresent(String roleID) throws NGPHException {
		
		String roleIdCount = getHibernateTemplate().find("select count(roleId) from Roles where roleId =?",roleID).get(0).toString();
		/*Roles userObject= (Roles)getSession().get(Roles.class, roleID);
		getSession().close();*/
		if(roleIdCount.equals("0"))
		return false;
		else
			return true;
	}
	
}
