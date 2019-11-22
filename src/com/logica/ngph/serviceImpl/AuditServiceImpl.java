package com.logica.ngph.serviceImpl;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;

import org.apache.log4j.Logger;

import com.logica.ngph.Entity.EventAudit;
import com.logica.ngph.Entity.EventMaster;
import com.logica.ngph.common.NGPHException;
import com.logica.ngph.dao.AuditDao;

import com.logica.ngph.service.AuditService;

public class AuditServiceImpl implements AuditService{
	static Logger logger = Logger.getLogger(AuditServiceImpl.class);
	AuditDao auditDao;
	
	public AuditDao getAuditDao() {
		return auditDao;
	}

	public void setAuditDao(AuditDao auditDao) {
		this.auditDao = auditDao;
	}
	/**
	* This method is used to save the repaired/modified data into TA_EVENTMASTER table
	* @return void
	*/
	
	public void saveEventMaster(com.logica.ngph.common.dtos.EventMaster eventMasterDto) throws NGPHException{
		EventMaster eventMaster = new EventMaster();
		eventMaster.setEventId(eventMasterDto.getEventId());
		eventMaster.setEventDesc(eventMasterDto.getEventDesc());
		eventMaster.setEventAlertable(eventMasterDto.getEventAlertable());
		eventMaster.setEventseverity(eventMasterDto.getEventSeverity());
		try {
			auditDao.saveEventMaster(eventMaster);
		} catch (SQLException sqlException) {
			logger.debug(sqlException.getMessage(), sqlException);
		}
	
	}
	/**
	* This method is used log the repaired into TA_EVENTAUDIT table
	* @return void
	*/
	
	public void saveEventAudit(com.logica.ngph.common.dtos.EventAudit eventAuditDto)
			throws NGPHException {
		EventAudit eventAudit = new EventAudit();
		eventAudit.setAuditEventId(eventAuditDto.getAuditEventId());
		eventAudit.setAuditEventDesc(eventAuditDto.getAuditEventDesc());
		eventAudit.setAuditTransaction(eventAuditDto.getAuditTransactionRef());
		//eventAudit.setAuditMonitoring(eventAuditDto.get());
		eventAudit.setAuditSource(eventAuditDto.getAuditSource());
		eventAudit.setAuditBranch(eventAuditDto.getAuditBranch());
		eventAudit.setAuditDept(eventAuditDto.getAuditDept());
		eventAudit.setAuditEventTime(getCurrentTime());
		try {
			auditDao.saveEventAudit(eventAudit);
		} catch (SQLException sqlException) {
			logger.debug(sqlException.getMessage(), sqlException);
		
		}
		
	}
	
	public void saveEventAuditData(com.logica.ngph.common.dtos.EventAudit eventAuditDto)
	throws NGPHException {
	EventAudit eventAudit = new EventAudit();
	eventAudit.setAuditEventId(eventAuditDto.getAuditEventId());
	eventAudit.setAuditEventDesc(eventAuditDto.getAuditEventDesc());
	eventAudit.setAuditTransaction(eventAuditDto.getAuditTransactionRef());
	//eventAudit.setAuditMonitoring(eventAuditDto.get());
	eventAudit.setAuditSource(eventAuditDto.getAuditSource());
	eventAudit.setAuditBranch(eventAuditDto.getAuditBranch());
	eventAudit.setAuditDept(eventAuditDto.getAuditDept());
	eventAudit.setAuditMsgref(eventAuditDto.getAuditMessageRef());
	eventAudit.setAuditEventTime(getCurrentTime());
	try {
		auditDao.saveEventAuditData(eventAudit);
	} catch (SQLException sqlException) {
		logger.debug(sqlException.getMessage(), sqlException);
	
	}

}
	
	/**
	* This method is used to log the deleted transaction data into TA_EVENT_AUDIT table
	* @return void
	*/
	
	public void saveEventAuditBatch(
			ArrayList<com.logica.ngph.common.dtos.EventAudit> eveAudits)
			throws NGPHException {
		for(com.logica.ngph.common.dtos.EventAudit eventAuditDto :eveAudits){
			EventAudit eventAudit = new EventAudit();
			eventAudit.setAuditEventId(eventAuditDto.getAuditEventId());
			eventAudit.setAuditEventDesc(eventAuditDto.getAuditEventDesc());
			eventAudit.setAuditTransaction(eventAuditDto.getAuditTransactionRef());
			//eventAudit.setAuditMonitoring(eventAuditDto.get());
			eventAudit.setAuditSource(eventAuditDto.getAuditSource());
			eventAudit.setAuditBranch(eventAuditDto.getAuditBranch());
			eventAudit.setAuditDept(eventAuditDto.getAuditDept());
			
			eventAudit.setAuditEventTime(getCurrentTime());
			try {
				auditDao.saveEventAudit(eventAudit);
			} catch (SQLException sqlException) {
				logger.debug(sqlException.getMessage(), sqlException);
			
			}
		}
		
	}

	
	/**
	* This method is used to get the current timestamp
	* @return Timestamp timeStampDate
	*/
	private Timestamp getCurrentTime(){
		java.util.Date 	str_date = Calendar.getInstance().getTime();
		java.sql.Timestamp timeStampDate = new Timestamp(str_date.getTime());
		return timeStampDate;
	}

}
