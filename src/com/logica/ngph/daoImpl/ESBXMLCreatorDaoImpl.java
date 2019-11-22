package com.logica.ngph.daoImpl;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;


import org.hsqldb.lib.StringUtil;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.logica.ngph.Entity.EI;
import com.logica.ngph.dao.ESBXMLCreatorDao;
import com.logica.ngph.dtos.searchEI_IDDto;
import com.logica.ngph.utils.CheckXMLContent;

public class ESBXMLCreatorDaoImpl extends HibernateDaoSupport implements ESBXMLCreatorDao{

	@Transactional(propagation=Propagation.REQUIRED)
	public String insertRecordInTA_EI(EI ei) throws SQLException {
		
			
			
			getHibernateTemplate().saveOrUpdate(ei);
		
		return "success";
	}

	
	public String checkForPrimaryKey(String EI_ID) {
		List CheckForPrimary = getHibernateTemplate().find("select eiCode from EI where eiCode='"+EI_ID+"'");
		int flag=0;
		if(CheckForPrimary.size()>0)
		{
			flag=1;
		}
		return flag+"";
	}

	
	public List<searchEI_IDDto> dataEI_IDSearch(String code) {
List<searchEI_IDDto> searchList = new ArrayList<searchEI_IDDto>();
		
		if(code == null){
			code = "%";
		}else{
			code = "%"+code+"%";
		}
		
		@SuppressWarnings("rawtypes")
		List EI_ID = 	getHibernateTemplate().find("select eiCode,eiName,eiType,eiEspProvName,EI_CONN_TYPE,EI_FORMAT,EI_HOST_CATGORY from com.logica.ngph.Entity.EI where eiCode like ?",code);
		
		for (int i = 0; i < EI_ID.size(); i++) {
			searchEI_IDDto searchEI_IDDtos = new searchEI_IDDto();
            Object[] obj = (Object[]) EI_ID.get(i);

            searchEI_IDDtos.setEiCode((String) obj[0]);
            searchEI_IDDtos.setEiName((String) obj[1]);
            searchEI_IDDtos.setEiType((String) obj[2]);
            searchEI_IDDtos.setEiEspProvName((String) obj[3]);
            searchEI_IDDtos.setEI_CONN_TYPE((String) obj[4]);
            searchEI_IDDtos.setEI_FORMAT((String) obj[5]);
            searchEI_IDDtos.setEI_HOST_CATGORY((String) obj[6]);
            
            
            CheckXMLContent checkXMLContent = new CheckXMLContent();
            String xmlValString =checkXMLContent.createJBMqQueueService((String) obj[0], (String) obj[4]);
            if(!xmlValString.equals(null) && !StringUtil.isEmpty(xmlValString)){
            StringTokenizer st = new StringTokenizer(xmlValString, ":/,");
    		System.out.println("String :- "+xmlValString);
    		
    		searchEI_IDDtos.setMQ_Manager_Name(st.nextToken());
    		searchEI_IDDtos.setMQ_Server_ID(st.nextToken());
    		searchEI_IDDtos.setMQ_Server_Port(st.nextToken());
    		searchEI_IDDtos.setMQ_Queue_Name(st.nextToken());
    		searchEI_IDDtos.setClient_Connection_Channel(st.nextToken());
    		}
            searchList.add(searchEI_IDDtos);
            
        }
		
			return searchList;
	}

	@Transactional(propagation=Propagation.REQUIRED)
	public String updateEI(EI ei) throws SQLException {
		getHibernateTemplate().update(ei);
		return "success";
	}

	@Transactional(propagation=Propagation.REQUIRED)
	public String deleteEI(EI ei) throws SQLException {
		getHibernateTemplate().delete(ei);
		return "success";
	}

}
