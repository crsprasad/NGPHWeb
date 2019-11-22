package com.logica.ngph.daoImpl;

import java.sql.Clob;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.logica.ngph.Entity.MsgPolled;
import com.logica.ngph.Entity.RawMessage;
import com.logica.ngph.dao.IErrorMsgsDao;


public class ErrorMsgsDao extends HibernateDaoSupport implements IErrorMsgsDao{

	public List<RawMessage> fetchErrorMsgs() throws SQLException
	{
		List<RawMessage> msgList = new ArrayList<RawMessage>();
		//Get all the Error Code
		List errorDescList1 = getHibernateTemplate().find("select errorCode,errorMsgDesc from ErrorCode  where errorChannel is null ");
		Map<String,String> map = new HashMap<String, String>();
		for(int i = 0 ;i<errorDescList1.size();i++)
		{
			Object[] objects = (Object[]) errorDescList1.get(i);
			map.put(objects[0]+"", objects[1]+"");
		}
		
		String query ="select rw.msgRef ,rw.rawChnl,rw.rawDirectn,rw.rawHost,rw.rawMsg,rw.rawRcvdTm,rw.errorCode from com.logica.ngph.Entity.RawMessage as rw where rw.rawMsgValStatus = 1 and rw.rawDirectn = 'O' order by rw.rawRcvdTm desc";

		@SuppressWarnings("rawtypes")
		List mesList = getHibernateTemplate().find(query);
		
		System.out.println(mesList.size()+"*******");
		
		for (int i = 0; i < mesList.size(); i++) 
		{
			RawMessage dto = new RawMessage();
            Object[] obj = (Object[]) mesList.get(i);
        
            dto.setMsgRef((String)obj[0]);
            dto.setRawChnl((String)obj[1]);
            dto.setRawDirectn((String)obj[2]);
            dto.setRawHost((String)obj[3]);
            Clob cb = (Clob) obj[4];
           
            
            String rawMsg = cb.getSubString(1, (int)cb.length());
            
            dto.setStrMsg(rawMsg);
            dto.setRawRcvdTm((Timestamp) obj[5]);
            String errorCode = (String)obj[6];
            int a=0;
            if(errorCode!=null && StringUtils.isNotBlank(errorCode) && StringUtils.isNotEmpty(errorCode))
			 {
            	String[] errorMsg = null;
            	if(errorCode.contains(";"))
            	{
            		errorMsg = errorCode.split(";");
            	}
            	else
            	{
            		errorMsg = new String[]{errorCode};
            	}
				 String errorDesc = "";
				// List<String> errorDesc = getHibernateTemplate().find("select errorMsgDesc from ErrorCode where errorCode =? and errorChannel is null",(String)obj[14]);
				 
				 if(errorMsg!=null  && errorMsg.length>0)
				 {
					 for(int i1 = 0 ;i1<errorMsg.length;i1++)
					 {
						 a=i1;
						 if(map.containsKey(errorMsg[i1]))
						 errorDesc  = errorDesc+(a+1)+") "+map.get(errorMsg[i1])+"\r\n";
						 else
							 errorDesc = errorDesc+(a+1)+") "+errorMsg[i1]+"\r\n";
						 
					 }
					 
				 }
				 if(StringUtils.isNotEmpty(errorDesc) && StringUtils.isNotBlank(errorDesc)) 
				 {
					 dto.setErrorCode(errorDesc);
				 }
				 else
				 {
					 dto.setErrorCode(errorCode);
				 }
			 }
            msgList.add(dto);
        }
	
		return msgList;
	}

	
	@Transactional(propagation = Propagation.REQUIRED)
	public String changeStatusAndMSgpolled(String msgRef) {
		try{
		SessionFactory fact=getHibernateTemplate().getSessionFactory();
		Session sess=fact.openSession();
		sess.beginTransaction();
		

		String query="update RAW_MSGS set RAW_MSG_VALSTATUS = 0 where RAW_MSGSREF = '"+msgRef+"'";
		Query q = sess.createSQLQuery(query);
		System.out.println("Record Update : - "+ q.executeUpdate());
		MsgPolled msgPolled = new MsgPolled();
		msgPolled.setInTime(new Timestamp(Calendar.getInstance().getTime().getTime()));
		msgPolled.setMsgRef(msgRef);
		msgPolled.setPolledStatus("P");
		getHibernateTemplate().save(msgPolled);
		
		sess.getTransaction().commit();
		sess.close();
		fact.close();
		
		}catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}
	public MsgPolled getEntityObject(String msgRef)
	{
		MsgPolled msgPolled = new MsgPolled();
		msgPolled.setInTime(new Timestamp(Calendar.getInstance().getTime().getTime()));
		msgPolled.setMsgRef(msgRef);
		msgPolled.setPolledStatus("P");
		return msgPolled;
	}

}
