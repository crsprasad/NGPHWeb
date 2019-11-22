package com.logica.ngph.service;


import java.util.ArrayList;

import com.logica.ngph.common.NGPHException;
import com.logica.ngph.common.dtos.EventAudit;
import com.logica.ngph.common.dtos.EventMaster;


public interface AuditService {
	public void saveEventMaster(EventMaster eventMaster) throws NGPHException;
	public void saveEventAudit( EventAudit eventAudit) throws NGPHException;
	public void saveEventAuditBatch( ArrayList<EventAudit> eveAudits) throws NGPHException;	
	public void saveEventAuditData(EventAudit eventAudit) throws NGPHException;
	
	
}
