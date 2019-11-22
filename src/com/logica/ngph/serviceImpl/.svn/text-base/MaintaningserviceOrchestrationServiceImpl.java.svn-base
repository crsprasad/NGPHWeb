package com.logica.ngph.serviceImpl;

import com.logica.ngph.dao.MaintainingServiceOrchestrationDao;
import com.logica.ngph.service.MaintaningServiceOrchestrationService;

public class MaintaningserviceOrchestrationServiceImpl implements MaintaningServiceOrchestrationService{
	MaintainingServiceOrchestrationDao maintainingServiceOrchestrationDao; 

	public MaintainingServiceOrchestrationDao getMaintainingServiceOrchestrationDao() {
		return maintainingServiceOrchestrationDao;
	}

	public void setMaintainingServiceOrchestrationDao(
			MaintainingServiceOrchestrationDao maintainingServiceOrchestrationDao) {
		this.maintainingServiceOrchestrationDao = maintainingServiceOrchestrationDao;
	}

	public String insertRecord(String MsgType,String subMsgType,String serviceID,String direction,String streamID) {
		String returnValue= maintainingServiceOrchestrationDao.insertRecord( MsgType, subMsgType, serviceID, direction, streamID);
		return returnValue;
	}

	
	public String checkForUniqueStreamID(String streamID) {
		// TODO Auto-generated method stub
		return maintainingServiceOrchestrationDao.checkForUniqueStreamID(streamID);
	}

}
