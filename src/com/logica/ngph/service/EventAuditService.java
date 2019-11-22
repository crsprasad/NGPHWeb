package com.logica.ngph.service;

import java.util.List;

import com.logica.ngph.Entity.SecUsers;
import com.logica.ngph.dao.EventAuditDao;
import com.logica.ngph.dtos.EventAuditDto;
import com.logica.ngph.dtos.PaymentMessage;

public class EventAuditService {
	EventAuditDao auditDao;

	public EventAuditDao getAuditDao() {
		return auditDao;
	}

	public void setAuditDao(EventAuditDao auditDao) {
		this.auditDao = auditDao;
	}
	
	public List<EventAuditDto> getSearchList(String query,String formWhichTable)
	{
		try{
			return auditDao.getSearchList(query,formWhichTable);
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public PaymentMessage getObject(String msgRef)
	{
		try{
			return auditDao.getMessage(msgRef);
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
	public List getBranchList()
	{
		try{
			return auditDao.getBranchList();
		}catch (Exception e) {
			return null;
		}
	}
	public List getDepartmentList()
	{
		try{
			return auditDao.getDepartmentList();
		}catch (Exception e) {
			return null;
		}
	}
	public List getTxnList()
	{
		try{
			return auditDao.getTxnList();
		}catch (Exception e) {
			return null;
		}
	}
	public SecUsers getUserBranch(String userID)
	{
		try{
			return auditDao.getUserBranch(userID);
		}catch (Exception e) {
			return null;
		}
	}

}
