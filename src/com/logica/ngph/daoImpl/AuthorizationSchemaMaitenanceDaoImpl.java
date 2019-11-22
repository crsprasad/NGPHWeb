package com.logica.ngph.daoImpl;


import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.logica.ngph.Entity.AuthorizationMaintenance;
import com.logica.ngph.dao.AuthorizationSchemaMaitenanceDao;

public class AuthorizationSchemaMaitenanceDaoImpl extends HibernateDaoSupport implements AuthorizationSchemaMaitenanceDao{

	
	public List<String> getMessageTypes() throws SQLException {

		@SuppressWarnings("rawtypes")
		List msgTypeList = getHibernateTemplate().
		find("select msgSupports.supportMsgType as msgType from com.logica.ngph.Entity.MsgSupport as msgSupports group by (msgSupports.supportMsgType)");
		System.out.println("*****************" + msgTypeList);
		return msgTypeList;
		
	}

	
	public List<String> getSubMessageTypes() throws SQLException {
		@SuppressWarnings("rawtypes")
		List subMessageType = getHibernateTemplate().
		find("select msgSupports.supportSubMsgType as msgType from com.logica.ngph.Entity.MsgSupport as msgSupports group by (msgSupports.supportSubMsgType)");
		System.out.println("THIS IS ENQUIRY DAO IMPL"+subMessageType);
		return subMessageType;
	}

	
	public List<String> getChannelTypes() throws SQLException {
		 @SuppressWarnings("rawtypes")
			List channel= getHibernateTemplate().
			 find("select channel.msgChnlType as channel from com.logica.ngph.Entity.NgphCanonical as channel group by (channel.msgChnlType)");
			 
			return channel;
	}


	public List<String> getHostName() throws SQLException {
		@SuppressWarnings("rawtypes")
		List HostName= getHibernateTemplate().
		 find("select hostName as HostName from com.logica.ngph.Entity.Host as Host");
		
		return HostName;
	}


	public List<String> getHostCode() throws SQLException {
		@SuppressWarnings("rawtypes")
		List HostCode= getHibernateTemplate().
		 find("select hostCode as HostCode from com.logica.ngph.Entity.Host as Host");
		return HostCode;
	}

	
	public List<String> getCurrency() throws SQLException {
		@SuppressWarnings("rawtypes")
		List Currency= getHibernateTemplate().
		 find("select currency.msgCurrency as Currency from com.logica.ngph.Entity.NgphCanonical as currency group by (currency.msgCurrency)");
		return Currency;
	}

	
	public List<String> getGroupIDAndGroupName() throws SQLException {
		@SuppressWarnings("rawtypes")
		List GroupIDnName= getHibernateTemplate().
		 find("select distinct concat(groupID,concat(' ',groupName))  from com.logica.ngph.Entity.AuthorizationGRPM  ");
		return GroupIDnName;
	}

	
	public List<String> getBranchCode() throws SQLException {
		@SuppressWarnings("rawtypes")
		List branchCode  = getHibernateTemplate().
		find("select Branches.branchCode as branchCode from com.logica.ngph.Entity.Branches as Branches)");
		return branchCode;
	}


	public List<String> getBranchCodeName() throws SQLException {
		@SuppressWarnings("rawtypes")
		List branchName = getHibernateTemplate().
		find("select Branches.branchName as branchName from com.logica.ngph.Entity.Branches as Branches)");
		return branchName;
	}

	public List<String> getCurrencyCodes() throws SQLException {

		@SuppressWarnings("rawtypes")
		List currencyList = getHibernateTemplate().
		find("select currency.currencyCode as currCode from com.logica.ngph.Entity.CurrencyMaster as currency group by currency.currencyCode order by currency.currencyCode ");
		System.out.println("*****************" + currencyList);
		return currencyList;
		
	}
	/*public String insertDataInTA_AUTHSCHEMAM(String Branch, String HostId,
			String channelType, String msgType, String SubMsgType,
			String Direction, String Currency, BigDecimal fromAmount,
			BigDecimal toAmount, String groupID, int groupSequence) {
		String data=getHibernateTemplate().saveOrUpdate(entity)
		return null;
	}*/
	@Transactional(propagation =Propagation.REQUIRED)
	public String insertDataInTA_AUTHSCHEMAM(AuthorizationMaintenance obj)
	{
		int count =0;
		try{
		getHibernateTemplate().save(obj);
		}
		catch(Exception e)
		{
			count=1;
			e.printStackTrace();
		}
		if(count==0)
		return "success";
		else
			return "FAIL";
	}


	public Map<String, Object> treeView() throws SQLException {
		/*SessionFactory fact=getHibernateTemplate().getSessionFactory();
		Session sess=fact.openSession();
		Connection con=sess.connection();
		*/
		Map<String, Object> val = new HashMap<String, Object>();
		
		
		try{
			
			
		String query="select distinct groupName from com.logica.ngph.Entity.AuthorizationGRPM";
		
		List data=getHibernateTemplate().find(query);
		for(int i=0;i<data.size();i++)
		{
			val.put(data.get(i).toString(),getHibernateTemplate().find("select concat(concat(SUPID,' ') ,ismanditatory) from com.logica.ngph.Entity.AuthorizationGRPM where groupName='"+data.get(i).toString()+"'") );
		}
		System.out.println(data);
		}
		catch (Exception e) {
			e.printStackTrace();
		}

		return val;
	
	}

	
}
