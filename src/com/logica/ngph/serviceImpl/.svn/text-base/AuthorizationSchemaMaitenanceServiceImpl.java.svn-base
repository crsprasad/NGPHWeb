package com.logica.ngph.serviceImpl;

import java.math.BigDecimal;

import com.logica.ngph.Entity.AuthorizationMaintenance;
import com.logica.ngph.dao.AuthorizationSchemaMaitenanceDao;
import com.logica.ngph.service.AuthorizationMaitenanceService;

public class AuthorizationSchemaMaitenanceServiceImpl implements AuthorizationMaitenanceService {

	AuthorizationSchemaMaitenanceDao authorizationSchemaMaitenanceDao;
	
	public AuthorizationSchemaMaitenanceDao getAuthorizationSchemaMaitenanceDao() {
		return authorizationSchemaMaitenanceDao;
	}

	public void setAuthorizationSchemaMaitenanceDao(
			AuthorizationSchemaMaitenanceDao authorizationSchemaMaitenanceDao) {
		this.authorizationSchemaMaitenanceDao = authorizationSchemaMaitenanceDao;
	}

	public String insertDataInTA_AUTHSCHEMAM(String Branch, String HostId,
			String channelType, String msgType, String SubMsgType,
			String Direction, String Currency, BigDecimal fromAmount,
			BigDecimal toAmount, String groupID, int groupSequence) {
		AuthorizationMaintenance obj = new AuthorizationMaintenance();
		
		obj.setBranch_Code(Branch);
		obj.setHost_code(HostId);
		obj.setChannel_type(channelType);
		obj.setMsg_type(msgType);
		obj.setSubMsg_type(SubMsgType);
		obj.setCurreny_type(Currency);
		obj.setFrom_Amount(fromAmount);
		obj.setTo_Amount(toAmount);
		obj.setGroupID(groupID);
		obj.setGroupSequence(groupSequence);
		obj.setDirection(Direction);
		
		
		
		String data=authorizationSchemaMaitenanceDao.insertDataInTA_AUTHSCHEMAM(obj);
		return data;
	}

}
