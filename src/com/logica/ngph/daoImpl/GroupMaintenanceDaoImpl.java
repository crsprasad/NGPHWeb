package com.logica.ngph.daoImpl;
import java.sql.Connection;


import java.sql.SQLException;

import java.util.List;


import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;


import com.logica.ngph.dao.GroupMaintenanceDao;

public class GroupMaintenanceDaoImpl extends HibernateDaoSupport implements GroupMaintenanceDao {

	
	@SuppressWarnings("unchecked")
	
	public List<String> getGroupMaintenanceBranchCode() throws SQLException {
		@SuppressWarnings("rawtypes")
		List branchCode  = getHibernateTemplate().
		find("select Branches.branchCode as branchCode from com.logica.ngph.Entity.Branches as Branches)");
		return branchCode;
	}

	@SuppressWarnings("unchecked")
	
	public List<String> getGroupMaintenanceBranchName() throws SQLException {
		@SuppressWarnings("rawtypes")
		List branchName = getHibernateTemplate().
		find("select Branches.branchName as branchName from com.logica.ngph.Entity.Branches as Branches)");
		return branchName;
	}

	
	public String insertDataInAuthGRPM(String Branch, String groupId,
			String groupName, String supervisorID,int supervisorSequence,int isMaditatory) {
		try
		{
			
			
			SessionFactory fact=getHibernateTemplate().getSessionFactory();
			Session sess=fact.openSession();
			Connection con=sess.connection();
			
			String query="insert into TA_AUTHGRPM values ('"+Branch+"','"+groupId+"','"+groupName+"','"
					+supervisorID+"',"+supervisorSequence+","+isMaditatory+")";
			Query q = sess.createSQLQuery(query);

			int result=q.executeUpdate();
			System.out.println("No of records updated are : " + result);
			
			sess.close();
			fact.close();
			con.close();
		}
		catch (Exception e) {
			logger.debug("EXCEPTION"+e);
		}
		
		
		return "success";
	}

	
	public String checkGroupIDAlreadyPresent(String groupId) throws SQLException {
		int counter=0;
		List checkForPrimaryKey=getHibernateTemplate().
		find("select distinct groupID from com.logica.ngph.Entity.AuthorizationGRPM where groupID=?",groupId);
		
			
		if(!(checkForPrimaryKey.size()==0))
			return "failure";
		else
			return "success";
	}

	

}
