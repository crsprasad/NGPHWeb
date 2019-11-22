package com.logica.ngph.service;

import java.sql.SQLException;
import java.util.List;

import com.logica.ngph.Entity.EventMaster;
import com.logica.ngph.dao.EventManagerDao;
import com.logica.ngph.dtos.EventManageDto;

public class EventManagerService {
	EventManagerDao eventManagerDao;

	public EventManagerDao getEventManagerDao() {
		return eventManagerDao;
	}

	public void setEventManagerDao(EventManagerDao eventManagerDao) {
		this.eventManagerDao = eventManagerDao;
	}
	
	public String insertValues(EventMaster eventMaster) throws SQLException
	{
		return eventManagerDao.insertValues(eventMaster);
	}
	public String checkForEventID(String eventId) throws SQLException
	{
		return eventManagerDao.checkForEventID(eventId);
	}
	public List <EventManageDto> eventIDSearch(String eventID) throws SQLException
	{
		return eventManagerDao.eventIDSearch(eventID);
	}
	public List getUserList() throws SQLException
	{
		return eventManagerDao.getUserList();
	}
	public List getCannonical() throws SQLException
	{
		return eventManagerDao.getCannonical();
	}
}
