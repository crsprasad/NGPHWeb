package com.logica.ngph.daoImpl;

import java.sql.Timestamp;
import java.util.List;


import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.logica.ngph.dao.ChangePwdDao;
import com.logica.ngph.dtos.UserPasswordsDTO;

public class ChangePwdDaoImpl extends HibernateDaoSupport implements ChangePwdDao  {

	
	public Boolean isOldPwdMatches(String userID,String oldpwd) {
		
		
		List list = getHibernateTemplate().find("select userPassword from SecUsers where userPassword  =? and user = ?",oldpwd,userID);
		if(list.isEmpty())
		{
			return false;
		}
		else
			return true;
		
	}

	
	public String changePwd(String userID, String newPwd, UserPasswordsDTO userPasswordsDTO) {
		
		Session session = getHibernateTemplate().getSessionFactory().openSession();
			session.beginTransaction();
			
			java.util.Date today = new java.util.Date();
		    System.out.println(new java.sql.Timestamp(today.getTime()));
			
			Query query =session.createSQLQuery("update TA_SEC_USERS set USRPSWDS=:USRPSWDS, PASS_LAST_MOD_DATE_TIME = :PASS_CHANGE_DATE_TIME  where USR =:USR");
			query.setString("USRPSWDS", newPwd);
			query.setString("USR", userID);
			query.setTimestamp("PASS_CHANGE_DATE_TIME", new java.sql.Timestamp(today.getTime()));
			System.out.println("Record Update : - "+query.executeUpdate());
		
			Query pwdQuery =session.createSQLQuery("update TA_SEC_USERS_PASSWORDS set PASSWORD=:USRNEWPSWD,PASSWORD_1 =:USEROLDPWD," +
					"PASSWORD_2 = :USEROLDPWD1,  PASSWORD_3 = :USEROLDPWD2, PASSWORD_4= :USEROLDPWD3 where USR =:USR");
			pwdQuery.setString("USRNEWPSWD", newPwd);
			pwdQuery.setString("USEROLDPWD", userPasswordsDTO.getUserPassword());
			pwdQuery.setString("USEROLDPWD1", userPasswordsDTO.getUserPassword1());
			pwdQuery.setString("USEROLDPWD2", userPasswordsDTO.getUserPassword2());
			pwdQuery.setString("USEROLDPWD3", userPasswordsDTO.getUserPassword3());
			pwdQuery.setString("USR", userID);
			System.out.println("Record Update : - "+pwdQuery.executeUpdate());
			session.getTransaction().commit();
			return "success";
		
	}


	@Override
	public UserPasswordsDTO getOldPwds(String userId) throws Exception
	{
		UserPasswordsDTO userPasswordsDTO = new UserPasswordsDTO();
		try
		{
			String query = "select userPassword, userPassword1, userPassword2, userPassword3, userPassword4 from UserPasswords where user = ? ";
			List list = getHibernateTemplate().find(query,userId);
			if(list!=null && !list.isEmpty() && list.size()>0)
			{
				Object[] obj = (Object[]) list.get(0);
				userPasswordsDTO.setUserPassword((String)obj[0]);
				userPasswordsDTO.setUserPassword1((String)obj[1]);
				userPasswordsDTO.setUserPassword2((String)obj[2]);
				userPasswordsDTO.setUserPassword3((String)obj[3]);
				userPasswordsDTO.setUserPassword4((String)obj[4]);
			}
			
			return userPasswordsDTO;
		} catch (Exception ex) {
			
			ex.printStackTrace();
		}
		return null;
	}
	
	@Override
	public String getPassPolicyIsReq(String initEntry)
	{
		List list = getHibernateTemplate().find("select initValue from Initialisation where initEntry  =? ",initEntry);
		if(list!=null)
			return list.get(0).toString();
			else
				return null;
	}

}
