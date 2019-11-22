package com.logica.ngph.dao;

import java.sql.SQLException;
import java.util.List;

import com.logica.ngph.Entity.EventMaster;
import com.logica.ngph.dtos.EventManageDto;

public interface EventManagerDao {
	public String insertValues(EventMaster eventMaster) throws SQLException;
	public String checkForEventID(String eventId) throws SQLException;
	public List <EventManageDto> eventIDSearch(String eventID) throws SQLException;
	public List getUserList() throws SQLException;
	public List getCannonical() throws SQLException;
}
