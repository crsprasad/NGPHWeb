package com.logica.ngph.serviceImpl;

import java.sql.SQLException;
import java.util.List;

import com.logica.ngph.dao.GroupMaintenanceDao;
import com.logica.ngph.service.GroupMainService;

public class GroupMaintenanceServiceImpl implements GroupMainService {
	GroupMaintenanceDao groupMaintenanceDao;
	
	public GroupMaintenanceDao getGroupMaintenanceDao() {
		return groupMaintenanceDao;
	}

	public void setGroupMaintenanceDao(GroupMaintenanceDao groupMaintenanceDao) {
		this.groupMaintenanceDao = groupMaintenanceDao;
	}

	
	public String insertDataInAuthGRPM(String Branch, String groupId,
			String groupName, String supervisorID,int supervisorSequence,int isMaditatory) {
		String data=groupMaintenanceDao.insertDataInAuthGRPM(Branch,groupId,groupName,supervisorID,supervisorSequence,isMaditatory);
		return data;
	}

	
	public String checkGroupIDAlreadyPresent(String groupId){
		String isAvailable = null;
		try {
			isAvailable=groupMaintenanceDao.checkGroupIDAlreadyPresent(groupId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return isAvailable;
	}

}
