package com.logica.ngph.dao;

import java.sql.SQLException;

import com.logica.ngph.Entity.EventAudit;
import com.logica.ngph.Entity.EventMaster;
import com.logica.ngph.common.NGPHException;


public interface AuditDao {
	
	public void saveEventMaster(EventMaster eventMaster) throws SQLException;
	public void saveEventAudit( EventAudit eventAudit) throws SQLException;
	public void saveEventAuditData(EventAudit eventAudit) throws SQLException;
	

}
