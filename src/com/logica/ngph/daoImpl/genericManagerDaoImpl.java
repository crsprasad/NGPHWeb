package com.logica.ngph.daoImpl;



import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.logica.ngph.Entity.SecUsers;
import com.logica.ngph.common.ConstantUtil;
import com.logica.ngph.dao.GenericManagerDao;
import com.logica.ngph.dtos.GenericManagerDto;
import com.logica.ngph.dtos.TemplateDto;

public class genericManagerDaoImpl extends HibernateDaoSupport implements GenericManagerDao {

	
	public String insertString(String screenString,String destPage,String ListData) throws SQLException {
		
		return "success";  
	}

	
	public List<GenericManagerDto> fetchData(String userID) throws SQLException {
		SessionFactory fact=getHibernateTemplate().getSessionFactory();
		Session sess=fact.openSession();
		if(userID.equalsIgnoreCase("sa1"))
		{
			userID = "sa1";
		}
		else if(userID.equalsIgnoreCase("sa2"))
		{
			userID = "sa2";
		}
		else
		{
			userID = userID.toUpperCase();
		}
		SecUsers user = (SecUsers)sess.get(SecUsers.class, userID);
		sess.close();
		fact.close();
		List list = null;
		List<GenericManagerDto> fetchedList = new  ArrayList<GenericManagerDto>();
		if(user.getUserType().toString().equals("A")|| user.getUserBranch().equals(ConstantUtil.ALL))
			list = getHibernateTemplate().find("select txnRef,screenID,status,crtdUserId,crtDate from GenericManager where status = 'P' and crtdUserId != ? and crtDate is not null order by crtDate desc",userID);
		else
			list = getHibernateTemplate().find("select txnRef,screenID,status,crtdUserId,crtDate from GenericManager where status = 'P' and crtdUserId != ? and userDept=? and userBranch=? and crtDate is not null order by crtDate desc ",userID,user.getUserDept(),user.getUserBranch());
		for(int i=0;i<list.size();i++)
		{
			GenericManagerDto genericManagerDto = new GenericManagerDto();
			Object[] obj = (Object[]) list.get(i);
			genericManagerDto.setScreenID((String)obj[0]);
			genericManagerDto.setScreenData((String) obj[1]);
			genericManagerDto.setActionClass((String)obj[2]);
			genericManagerDto.setCrtdUserId((String)obj[3]);
			genericManagerDto.setCrtDate((Timestamp)obj[4]);
			fetchedList.add(genericManagerDto);
		}
		
		
		return fetchedList;
	}


	public List<TemplateDto> fetchTempateData(String userID,
			String msgType, String tempName) throws SQLException {
		SessionFactory fact=getHibernateTemplate().getSessionFactory();
		Session sess=fact.openSession();
		SecUsers user = (SecUsers)sess.get(SecUsers.class, userID);
		sess.close();
		fact.close();
		List list = null;
		if(tempName == null){
			tempName = "%";
		}else{
			tempName = "%"+tempName+"%";
		}
		List<TemplateDto> fetchedList = new  ArrayList<TemplateDto>();
		if(user.getUserType().toString().equals("A")|| user.getUserBranch().equals(ConstantUtil.ALL))
			list = getHibernateTemplate().find("select tempRef,tempId,tempName,crtdUserId, crtDate from MessageTemp where tempName like ? and msgType = ? order by tempRef desc",tempName,msgType);
		else
			list = getHibernateTemplate().find("select tempRef,tempId,tempName,crtdUserId, crtDate from MessageTemp where tempName like ? and msgType = ? and userDept=? and userBranch=? order by tempRef desc",tempName,msgType,user.getUserDept(),user.getUserBranch());
		for(int i=0;i<list.size();i++)
		{
			TemplateDto templateDto = new TemplateDto();
			Object[] obj = (Object[]) list.get(i);
			templateDto.setTempRef(obj[0].toString());
            templateDto.setTempId(obj[1].toString());
            templateDto.setTempName((String) obj[2]);
            templateDto.setCrtdUserId((String) obj[3]);
            templateDto.setCrtDate((Timestamp)obj[4]);
			fetchedList.add(templateDto);
		}
		return fetchedList;
	}


	public List<TemplateDto> getTempNameSearchData(String tempName, String msgType)
			throws SQLException {
		try{
			List<TemplateDto> searchList = new ArrayList<TemplateDto>();
			if(tempName == null){
				tempName = "%";
			}else{
				tempName = "%"+tempName+"%";
			}
			
			@SuppressWarnings("rawtypes")
			List list= getHibernateTemplate().find("select tempId,tempName,tempRef,crtdUserId from MessageTemp where tempName like ? and msgType = ? ",tempName,msgType);
			for (int i = 0; i < list.size(); i++) {
				TemplateDto templateDto = new TemplateDto();
	            Object[] obj = (Object[]) list.get(i);
	            templateDto.setTempId(obj[0].toString());
	            templateDto.setTempName((String) obj[1]);
	            templateDto.setTempRef((String) obj[2]);
	            templateDto.setCrtdUserId((String) obj[3]);
	            searchList.add(templateDto);
	        }
		return searchList;		
		}catch (Exception e) {
			e.printStackTrace();
			
			return null;
		}
	}


	public List<GenericManagerDto> fetchRejectedData(String userID)
			throws SQLException {
	
		SessionFactory fact=getHibernateTemplate().getSessionFactory();
		Session sess=fact.openSession();
		sess.close();
		fact.close();
		List list = null;
		List<GenericManagerDto> fetchedRejectedList = new  ArrayList<GenericManagerDto>();
			list = getHibernateTemplate().find("select txnRef,screenID,status,crtdUserId,crtDate from GenericManager where status = 'R' and crtdUserId = ? order by txnRef desc",userID);
		for(int i=0;i<list.size();i++)
		{
			GenericManagerDto genericManagerDto = new GenericManagerDto();
			Object[] obj = (Object[]) list.get(i);
			genericManagerDto.setScreenID((String)obj[0]);
			genericManagerDto.setScreenData((String) obj[1]);
			genericManagerDto.setActionClass((String)obj[2]);
			genericManagerDto.setCrtdUserId((String)obj[3]);
			genericManagerDto.setCrtDate((Timestamp)obj[4]);
			fetchedRejectedList.add(genericManagerDto);
		}
		return fetchedRejectedList;
	}
	
	public List<GenericManagerDto> fetchPendingAuthorizationData(String userID)
	throws SQLException {

		SessionFactory fact=getHibernateTemplate().getSessionFactory();
		Session sess=fact.openSession();
		sess.close();
		fact.close();
		List list = null;
		List<GenericManagerDto> fetchedRejectedList = new  ArrayList<GenericManagerDto>();
		list = getHibernateTemplate().find("select txnRef,screenID,status,crtdUserId,crtDate from GenericManager where status = 'P' and crtdUserId = ? order by txnRef desc",userID);
	for(int i=0;i<list.size();i++)
	{
		GenericManagerDto genericManagerDto = new GenericManagerDto();
		Object[] obj = (Object[]) list.get(i);
		genericManagerDto.setScreenID((String)obj[0]);
		genericManagerDto.setScreenData((String) obj[1]);
		genericManagerDto.setActionClass((String)obj[2]);
		genericManagerDto.setCrtdUserId((String)obj[3]);
		genericManagerDto.setCrtDate((Timestamp)obj[4]);
		fetchedRejectedList.add(genericManagerDto);
	}
	return fetchedRejectedList;
	}

}
