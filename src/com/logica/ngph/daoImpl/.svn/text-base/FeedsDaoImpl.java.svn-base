package com.logica.ngph.daoImpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.logica.ngph.dao.FeedsDao;
import com.logica.ngph.dtos.EIDto;

public class FeedsDaoImpl extends HibernateDaoSupport implements FeedsDao{
	
	public List<EIDto> getEIFeedDetails() throws SQLException{
		List <EIDto> returnList = new ArrayList<EIDto>();
		@SuppressWarnings("rawtypes")
		List status = getHibernateTemplate().find("select eiCode,eiName,status,eiEspProvName,feedIn,feedout,EI_CONN_TYPE,EI_FORMAT,EI_HOST_CATGORY,inputSrcQueue,inputDestQueue,outputSrcQueue,outputDestQueue from EI");
		for(int i=0;i<status.size();i++){
			EIDto eiDto = new EIDto();
		 Object[] obj = (Object[]) status.get(i);
		 eiDto.setEICode((String)obj[0]);
		 eiDto.setEIName((String)obj[1]);
		 eiDto.setEIStatus((String)obj[2]);
		 eiDto.setEiEspProvName((String)obj[3]);
		 eiDto.setFeedIn((Integer)obj[4]);
		 eiDto.setFeedout((Integer)obj[5]);
		 eiDto.setEIConnType((String)obj[6]);
		 eiDto.setEIFormat((String)obj[7]);
		 eiDto.setEIHostCatgory((String)obj[8]);
		 eiDto.setInputSrcQueue((String)obj[9]);
		 eiDto.setInputDestQueue((String)obj[10]);
		 eiDto.setOutputSrcQueue((String)obj[11]);
		 eiDto.setOutputDestQueue((String)obj[12]);
		 returnList.add(eiDto);
		}
		return returnList;
	}
	
	public String changeStatus(String eiCode, String status) throws SQLException{
		String result = "";
		try{
			SessionFactory fact=getHibernateTemplate().getSessionFactory();
			Session sess=fact.openSession();
			sess.beginTransaction();
		if(status.equals("Start")){
			String query="update  EI set status = 2 where eiCode ='"+eiCode+"'";
			int output = getHibernateTemplate().bulkUpdate(query);				
			if(output>0){
				result = "success";	
			}
			
		}else if(status.equals("Stop")){
			String query="update  EI set status = 0 where eiCode ='"+eiCode+"'";
			int output = getHibernateTemplate().bulkUpdate(query);				
			if(output>0){
				result = "success";	
			}
		}
		sess.getTransaction().commit();
		sess.close();
		fact.close();	
		return result;
		
		}catch (Exception e) {
			e.printStackTrace();
		// TODO: handle exception
			return result;
		}
		
	}
	

}
