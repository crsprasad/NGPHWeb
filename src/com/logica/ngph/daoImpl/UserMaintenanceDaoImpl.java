package com.logica.ngph.daoImpl;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.logica.ngph.Entity.Department;
import com.logica.ngph.Entity.Roles;
import com.logica.ngph.Entity.SecUsers;
import com.logica.ngph.Entity.SecurityQuesions;
import com.logica.ngph.common.NGPHException;
import com.logica.ngph.common.dtos.UserMaintenanceDTO;
import com.logica.ngph.dao.UserMaintenanceDao;
import com.logica.ngph.dtos.UserPasswordsDTO;
import com.logica.ngph.utils.StringEncrypter;

/**
 * 
 * @author SATHISH
 *
 */
public class UserMaintenanceDaoImpl extends HibernateDaoSupport implements UserMaintenanceDao{
	
	static Logger logger = Logger.getLogger(UserMaintenanceDaoImpl.class);
	
	@Transactional(propagation=Propagation.REQUIRED)
	public void createUser(SecUsers secUser, List<String> assignedRoles, List<String> assignedDepts) throws SQLException {
		System.out.println("In UserMaintenanceDaoImpl<createUser>:");
		Session session = getHibernateTemplate().getSessionFactory().openSession();
		session.beginTransaction();
		
		try{
			java.util.Date today = new java.util.Date();
		    System.out.println(new java.sql.Timestamp(today.getTime()));
		session.save(secUser);
		
		for(String roleName :assignedRoles){
			Query roleidQuery =session.createSQLQuery("select ROLE_ID from TA_SEC_ROLES where ROLE_NAME = :ROLE_ID");
			roleidQuery.setString("ROLE_ID", roleName);
			String roleid = roleidQuery.uniqueResult().toString();
			Query query =session.createSQLQuery("insert into TA_SEC_USERS_TO_ROLES (USER_ID,ROLE_ID) values(:USER_ID,:ROLE_ID)");
			query.setString("USER_ID", secUser.getUser().toUpperCase());
			query.setString("ROLE_ID", roleid);
			query.executeUpdate();
		}
		for(String deptId :assignedDepts){
			Query query =session.createSQLQuery("insert into TA_SEC_USERS_TO_DEPARTMENTS (USER_ID,DEPT_CODE) values(:USER_ID,:DEPT_CODE)");
			query.setString("USER_ID", secUser.getUser().toUpperCase());
			query.setString("DEPT_CODE", deptId);
			query.executeUpdate();
		}
		Query query =session.createSQLQuery("insert into TA_SEC_USERS_PASSWORDS (USR,PASSWORD) values(:USER_ID,:PASSWORD)");
		query.setString("USER_ID", secUser.getUser().toUpperCase());
		String str1=StringEncrypter.encryptURL(secUser.getUser().toUpperCase(), secUser.getUserPassword());
		String PASSWORD = new String(new sun.misc.BASE64Encoder().encode(str1.getBytes()));
		query.setString("PASSWORD", PASSWORD);
		query.executeUpdate();
		session.getTransaction().commit();
		
		Query datequery = session.createSQLQuery("UPDATE TA_SEC_USERS SET PASS_LAST_MOD_DATE_TIME = :PASS_LAST_MOD_DATE_TIME WHERE USR =:USR");
		datequery.setString("USR", secUser.getUser().toUpperCase());
		datequery.setTimestamp("PASS_LAST_MOD_DATE_TIME", new java.sql.Timestamp(today.getTime()));
		datequery.executeUpdate();
		
		}catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
			
		}finally{
		
		session.close();
		}
		System.out.println("afeter insersion:");
	}
	
	
	@Transactional(propagation=Propagation.REQUIRED)
	public void modifyUser(SecUsers userEntity, List<String> assignedRoles, List<String> assignedDepts, UserPasswordsDTO userPasswordsDTO) throws SQLException {
		System.out.println("In UserMaintenanceDaoImpl<modifyUser>:");
		Session session = getHibernateTemplate().getSessionFactory().openSession();
		session.beginTransaction();
		try{
		session.update(userEntity);
		Query deleteQuery =session.createSQLQuery("delete from  TA_SEC_USERS_TO_ROLES where USER_ID = :USER_ID");
		deleteQuery.setString("USER_ID", userEntity.getUser().toUpperCase());
		deleteQuery.executeUpdate();
		for(String roleName :assignedRoles){
			Query roleidQuery =session.createSQLQuery("select ROLE_ID from TA_SEC_ROLES where ROLE_NAME = :ROLE_ID");
			roleidQuery.setString("ROLE_ID", roleName);
			String roleid = roleidQuery.uniqueResult().toString();
			Query query =session.createSQLQuery("insert into TA_SEC_USERS_TO_ROLES (USER_ID,ROLE_ID) values(:USER_ID,:ROLE_ID)");
			query.setString("USER_ID", userEntity.getUser().toUpperCase());
			query.setString("ROLE_ID", roleid);
			query.executeUpdate();
		}
		deleteQuery =session.createSQLQuery("delete from  TA_SEC_USERS_TO_DEPARTMENTS where USER_ID = :USER_ID");
		deleteQuery.setString("USER_ID", userEntity.getUser().toUpperCase());
		deleteQuery.executeUpdate();
		for(String deptId :assignedDepts){
			Query query =session.createSQLQuery("insert into TA_SEC_USERS_TO_DEPARTMENTS (USER_ID,DEPT_CODE) values(:USER_ID,:DEPT_CODE)");
			query.setString("USER_ID", userEntity.getUser().toUpperCase());
			query.setString("DEPT_CODE", deptId );
			query.executeUpdate();
		}
		
		Query pwdQuery =session.createSQLQuery("update TA_SEC_USERS_PASSWORDS set PASSWORD=:USRNEWPSWD,PASSWORD_1 =:USEROLDPWD," +
		"PASSWORD_2 = :USEROLDPWD1,  PASSWORD_3 = :USEROLDPWD2, PASSWORD_4= :USEROLDPWD3 where USR =:USR");
		pwdQuery.setString("USRNEWPSWD", userEntity.getUserPassword());
		pwdQuery.setString("USEROLDPWD", userPasswordsDTO.getUserPassword());
		pwdQuery.setString("USEROLDPWD1", userPasswordsDTO.getUserPassword1());
		pwdQuery.setString("USEROLDPWD2", userPasswordsDTO.getUserPassword2());
		pwdQuery.setString("USEROLDPWD3", userPasswordsDTO.getUserPassword3());
		pwdQuery.setString("USR", userEntity.getUser());
		System.out.println("Record Update : - "+pwdQuery.executeUpdate());
		session.getTransaction().commit();
		
		}catch (Exception e) {
			session.getTransaction().rollback();
			logger.error("Exception Occured Is: - "+e);
		}
		finally{
		session.close();
		}
		System.out.println("afeter Modification:");
		
	}

	
	@Transactional(propagation=Propagation.REQUIRED)
	public void deleteUser(String userId) throws SQLException {
		System.out.println("In UserMaintenanceDaoImpl<deleteUser>:");
		Session session = getHibernateTemplate().getSessionFactory().openSession();
		
		try{
			session.beginTransaction();
		Query deleteQuery =session.createSQLQuery("delete from  TA_SEC_USERS_TO_ROLES where USER_ID = :USER_ID");
		deleteQuery.setString("USER_ID", userId.toUpperCase());
		deleteQuery.executeUpdate();
		deleteQuery =session.createSQLQuery("delete from  TA_SEC_USERS_TO_DEPARTMENTS where USER_ID = :USER_ID");
		deleteQuery.setString("USER_ID", userId.toUpperCase());
		deleteQuery.executeUpdate();
		Query query = session.createQuery("delete SecUsers where user = :user");
		query.setString("user", userId);
		query.executeUpdate();
		session.getTransaction().commit();
		}
		catch (Exception e) {
			session.getTransaction().rollback();
			logger.error("Exception Occured Is: - "+e);
		}
		finally{
		session.close();
		}
		
		System.out.println("afeter Deletion:");
	}

	
	public List<Roles> getAvailableRoles() throws SQLException {
		System.out.println("Inside UserMaintenanceDaoImpl<getAvailableRoles>:");
		Session session = getHibernateTemplate().getSessionFactory().openSession();
		Query query = session.createQuery("from Roles");
		List<Roles> roleList = query.list();
		System.out.println("in getAvailableRoles(): after fetching all roles.list size:"+roleList.size());
		session.close();
		return roleList;
				
	}
	
	
	public List<Department> getAvailableDepts() throws SQLException {
		System.out.println("Inside UserMaintenanceDaoImpl<getAvailableDepts>:");
		Session session = getHibernateTemplate().getSessionFactory().openSession();
		Query query = session.createQuery("from Department");
		List<Department> deptList = query.list();
		System.out.println("in getAvailableDepts(): after fetching all roles. list size:"+deptList.size());
		session.close();
		return deptList;
				
	}

	
	public boolean isUserIdAvailable(String userId) {
		SecUsers secUser = (SecUsers)getSession().get(SecUsers.class, userId);
		getSession().close();
		System.out.println(" useid check. userId in request:"+userId);
		if(secUser!=null && secUser.getUser().equals(userId)){
			System.out.println(" userId check. userId got from DB:"+secUser.getUser());
			return true;
		}
		return false;
	}

	
	public List<UserMaintenanceDTO> getUserIDList(String userId) throws SQLException {
		System.out.println("Inside UserMaintenanceDaoImpl<getUserIDList>:");
		List<UserMaintenanceDTO> searchList = new ArrayList<UserMaintenanceDTO>();
		if(userId == null){
			userId = "%";
		}else{
			userId = "%"+userId+"%";
		}
		@SuppressWarnings("rawtypes")
		List userIdList = 	getHibernateTemplate().find("select user,userFirstName,userLastName from com.logica.ngph.Entity.SecUsers where user like ?",userId);
		for (int i = 0; i < userIdList.size(); i++) {
			UserMaintenanceDTO userDTO = new UserMaintenanceDTO();
            Object[] obj = (Object[]) userIdList.get(i);
            userDTO.setUser((String) obj[0]);
            userDTO.setUserFirstName((String) obj[1]);
            userDTO.setUserLastName((String) obj[2]);
            searchList.add(userDTO);
        }
		return searchList;
	}

	
	public SecUsers getUserDataAction(String userId) throws SQLException {
		System.out.println("Inside UserMaintenanceDaoImpl<getUserDataAction>:");
		SecUsers userObject= (SecUsers)getSession().get(SecUsers.class, userId);
		getSession().close();
		return userObject;
	}
	
	
	public List<String> getAssignedRoles(String userId) throws SQLException {
		System.out.println("Inside UserMaintenanceDaoImpl<getAssignedRoles>:");
		Session session = getSession();
		List<String> assignedRoleList = new ArrayList<String>();
		Query Query = session.createSQLQuery("SELECT ROLE_ID FROM TA_SEC_USERS_TO_ROLES WHERE USER_ID = :USER_ID");
		Query.setString("USER_ID", userId);
		assignedRoleList = Query.list();
		session.close();
		return assignedRoleList;
	}
	
	
	public List<String>  getAssignedDepts(String userId) throws SQLException {
		System.out.println("Inside UserMaintenanceDaoImpl<getAssignedDepts>:");
		Session session = getSession();
		List<String> assignedDeptList = new ArrayList<String>();
		Query Query = session.createSQLQuery("SELECT DEPT_CODE FROM TA_SEC_USERS_TO_DEPARTMENTS WHERE USER_ID = :USER_ID");
		Query.setString("USER_ID", userId);
		assignedDeptList = Query.list();
		session.close();
		return assignedDeptList;
	}


	
	public Boolean isUserPresent(String user) throws NGPHException {
		
		String userCount = getHibernateTemplate().find("select count(userFirstName) from SecUsers where user =?",user).get(0).toString();
		/*SecUsers userObject= (SecUsers)getSession().get(SecUsers.class, user);
		getSession().close();*/
		if(userCount.equals("0")){
			return false;
			
		}else{
			
			return true;
		}
		
	}


	public String getRoleName(String roleId) throws NGPHException {
		Session session = getSession();
		Query query = session.createSQLQuery("SELECT ROLE_NAME FROM TA_SEC_ROLES WHERE ROLE_ID = :ROLE_ID");
		query.setString("ROLE_ID", roleId);
		String roleName = query.uniqueResult().toString();
		session.close();
		return roleName;
	}


	public String getDeptName(String deptId) throws NGPHException {
		Session session = getSession();
		Query query = session.createSQLQuery("SELECT DEPT_NAME FROM DEPARTMENTS WHERE DEPT_CODE = :DEPT_CODE");
		query.setString("DEPT_CODE", deptId);
		String deptName = query.uniqueResult().toString();
		session.close();
		return deptName;
	}

	public List<String> getCurrencyCodes() throws SQLException {
		System.out.println("Inside UserMaintenanceDaoImpl<getCurrencyCodes>:");
		Session session = getHibernateTemplate().getSessionFactory().openSession();
		Query query = session.createQuery("SELECT currencyCode from CurrencyMaster group by currencyCode order by currencyCode ");
		List<String> currencyCodeList = query.list();
		System.out.println("in getCurrencyCodes(): :"+currencyCodeList.size());
		session.close();
		return currencyCodeList;
				
	}

	public Timestamp getLastModPassByUser(String userId) throws NGPHException {
		Timestamp passLastModDateByUser =null;
		try
		{
				List lastPassModDateTimeList =getHibernateTemplate().find("select lastPassModDateTime from SecUsers where USR = ?",userId);
				passLastModDateByUser = (Timestamp)lastPassModDateTimeList.get(0);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return passLastModDateByUser;
	}

	
	/*public List<SecurityQuesions> getSecurityQuestions() throws SQLException {
		System.out.println("Inside UserMaintenanceDaoImpl<getSecurityQuestions>:");
		Session session = getHibernateTemplate().getSessionFactory().openSession();
		Query query = session.createQuery("from SecurityQuesions");
		List<SecurityQuesions> securityQuestionsList = query.list();
		System.out.println("in getSecurityQuestions():  list size:"+securityQuestionsList.size());
		session.close();
		return securityQuestionsList;
				
	}*/

	
	public List<String> getSecurityQuestions() throws SQLException {
		logger.info("Inside EnquiryDaoImpl<getSecurityQuestions>");
		@SuppressWarnings("rawtypes")
		List SecurityQuestionList= getHibernateTemplate().
		 find("select security.securityQuesionName as securityQuestion from com.logica.ngph.Entity.SecurityQuesions as security order by (security.securityQuesionName)");
		logger.info("End EnquiryDaoImpl<getSecurityQuestions>");
		return SecurityQuestionList;
	}
	
}
