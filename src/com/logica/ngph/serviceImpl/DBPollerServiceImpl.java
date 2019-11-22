package com.logica.ngph.serviceImpl;

import java.sql.Timestamp;
import java.util.List;


import com.logica.ngph.common.NGPHException;
import com.logica.ngph.common.dtos.MsgsPolledDto;
import com.logica.ngph.common.dtos.NgphCanonical;
import com.logica.ngph.dao.DBPollerDao;
import com.logica.ngph.dtos.SodEodTaskTDto;

import com.logica.ngph.service.DBPollerService;

public class DBPollerServiceImpl implements DBPollerService{
DBPollerDao dbPollerDao;

public DBPollerDao getDbPollerDao() {
	return dbPollerDao;
}

public void setDbPollerDao(DBPollerDao dbPollerDao) {
	this.dbPollerDao = dbPollerDao;
}


public List<MsgsPolledDto> getPolledMessages() {
	return dbPollerDao.getPolledMessages();
	
}


public void perFormDbPoll(Timestamp businesDate, String prevMessageStatus,
		String messageRef,SodEodTaskTDto sodEodTaskTDto) throws NGPHException {
	dbPollerDao.perFormDbPoll(businesDate, prevMessageStatus, messageRef,sodEodTaskTDto);
	
}


public NgphCanonical getMessage(String msgRef) {
	return dbPollerDao.getMessage(msgRef);
	
}


public Timestamp getBusinessDate(String branchCode) {
	
	return dbPollerDao.getBusinessDate(branchCode);
}


}
