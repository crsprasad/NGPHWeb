package com.logica.ngph.service;

import java.util.ArrayList;
import java.util.List;

import com.logica.ngph.common.ConstantUtil;
import com.logica.ngph.common.NGPHException;
import com.logica.ngph.dao.GroupMaintenanceDao;

public class GroupMaintenanceService {
	GroupMaintenanceDao groupMaintenanceDao;

	public GroupMaintenanceDao getGroupMaintenanceDao() {
		return groupMaintenanceDao;
	}

	public void setGroupMaintenanceDao(GroupMaintenanceDao groupMaintenanceDao) {
		this.groupMaintenanceDao = groupMaintenanceDao;
	}
	
	public List<String> getGroupMaintenanceService(String dropDownName)throws NGPHException{
		List<String> dropDwnList = new ArrayList<String>();
		try{
			if(ConstantUtil.GROUPMAINTENANANCEBRANCHCODE.equals(dropDownName)){
				dropDwnList =  groupMaintenanceDao.getGroupMaintenanceBranchCode();
				System.out.println("calling msg type");
				
			}
			if(ConstantUtil.GROUPMAINTENANANCEBRANCHNAME.equals(dropDownName)){
				dropDwnList =  groupMaintenanceDao.getGroupMaintenanceBranchName();
				
			}
			
		}
		catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return dropDwnList;
}
	
}