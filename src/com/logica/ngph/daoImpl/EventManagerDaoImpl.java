package com.logica.ngph.daoImpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.logica.ngph.Entity.EventMaster;
import com.logica.ngph.dao.EventManagerDao;

import com.logica.ngph.dtos.EventManageDto;

public class EventManagerDaoImpl extends HibernateDaoSupport implements EventManagerDao {

	
	public String insertValues(EventMaster eventMaster)
			throws SQLException {
		getHibernateTemplate().update(eventMaster);
		return "success";
	}

	
	public String checkForEventID(String eventId) throws SQLException {
		List list=getHibernateTemplate().find("select eventId from EventMaster where eventId =?",eventId);
		String returnType="success";
		if(list.size()!=0)
		{
			returnType ="Is Available";
		}
		
		return returnType;
	}

	
	public List<EventManageDto> eventIDSearch(String eventID)
			throws SQLException {
List<EventManageDto> searchList = new ArrayList<EventManageDto>();
		
		if(eventID == null){
			eventID = "%";
		}else{
			eventID = "%"+eventID+"%";
		}
		System.out.println(eventID);
		@SuppressWarnings("rawtypes")
		List eventIDList = 	getHibernateTemplate().find("select eventId,eventDesc,eventAlertable,eventAlertType,eventAlertTO,eventAlertFor,eventAlertConsolidate from EventMaster where eventId IS NOT NULL And eventDesc IS NOT NULL And eventId like ?",eventID);
		
		for (int i = 0; i < eventIDList.size(); i++) {
			EventManageDto eventManageDto = new EventManageDto();
            Object[] obj = (Object[]) eventIDList.get(i);
            eventManageDto.setEventID((String)obj[0]);
            eventManageDto.setEventDesc((String)obj[1]);
            eventManageDto.setAlertable((Integer)obj[2]);
            eventManageDto.setAlert_type((String)obj[3]);
            eventManageDto.setAlert_To((String)obj[4]);
            eventManageDto.setAlert_For((String)obj[5]);
            eventManageDto.setAlert_consolidate((String)obj[6]);
            
           
            
             
            searchList.add(eventManageDto);
        }
		
			return searchList;
		
	}

	
	public List getUserList() throws SQLException {
		List list = getHibernateTemplate().find("select userFirstName from SecUsers where userFirstName is not null");
		return list;
	}

	
	public List getCannonical() throws SQLException {
		List list = getHibernateTemplate().find("select distinct fieldsLogName from MsgField");
		return list;
	}

}
