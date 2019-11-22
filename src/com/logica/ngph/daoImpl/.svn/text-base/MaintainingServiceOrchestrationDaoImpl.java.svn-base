package com.logica.ngph.daoImpl;

import java.sql.Connection;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.logica.ngph.dao.MaintainingServiceOrchestrationDao;

import java.util.List;

public class MaintainingServiceOrchestrationDaoImpl extends HibernateDaoSupport implements MaintainingServiceOrchestrationDao {

		

	
	public String insertRecord(String MsgType, String subMsgType,
			String serviceID, String direction, String streamID) {
		
		try
		{
			
			String[] TempserviceID=serviceID.split(",");
			SessionFactory fact=getHibernateTemplate().getSessionFactory();
			Session sess=fact.openSession();
			Connection con=sess.connection();
			
			for(int i=1;i<TempserviceID.length;i++)
			{
				String[] temperoryArray=TempserviceID[i].split(" - ");
			String query="insert into SERVICEORCH (SRVC_MSG_TYPE,SRVC_MSG_SUBTYPE,SRVC_SERVICEID,SRVC_CALLSEQ,SRVC_MSGDIRECTION,SRVC_ORCH_STREAM_ID) values ('"+MsgType+"','"+subMsgType+"','"+temperoryArray[0]+"','"+i+"','"+direction+"','"+streamID+"')";
			
			Query q = sess.createSQLQuery(query);

			int result=q.executeUpdate();
			System.out.println("No of records updated are : " + result);
			}
			sess.close();
			fact.close();
			con.close();
		}
		catch (Exception e) {
			logger.debug("EXCEPTION"+e);
		}
		return "success";
	}

	
	public String checkForUniqueStreamID(String streamID) {
		List resultset =  getHibernateTemplate().find("select count(*) from ServiceOrchestartion where SRVC_ORCH = '"+streamID+"'");
		return resultset.get(0).toString();
	}

}
