package com.logica.ngph.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.logica.ngph.common.ConstantUtil;
import com.logica.ngph.common.NGPHException;
import com.logica.ngph.dao.AuthorizationSchemaMaitenanceDao;

public class AuthorizationSchemaMaitenanceService {
	static Logger logger = Logger.getLogger(AuthorizationSchemaMaitenanceService.class);
	
	AuthorizationSchemaMaitenanceDao authorizationSchemaMaitenancedao;

	public AuthorizationSchemaMaitenanceDao getAuthorizationSchemaMaitenancedao() {
		return authorizationSchemaMaitenancedao;
	}

	public void setAuthorizationSchemaMaitenancedao(
			AuthorizationSchemaMaitenanceDao authorizationSchemaMaitenancedao) {
		this.authorizationSchemaMaitenancedao = authorizationSchemaMaitenancedao;
	}
	
	public List<String> getAuthorizationSchemaMaitenanceService(String dropDownName)throws NGPHException{
		List<String> dropDwnList = new ArrayList<String>();
		
		try{
			if(ConstantUtil.MSGTYPE.equals(dropDownName)){
				dropDwnList =  authorizationSchemaMaitenancedao.getMessageTypes();
				}
			if(ConstantUtil.MSGSUBTYPE.equals(dropDownName)){
				dropDwnList =  authorizationSchemaMaitenancedao.getSubMessageTypes();
				}
			if(ConstantUtil.CHANNEL.equals(dropDownName)){
				dropDwnList =  authorizationSchemaMaitenancedao.getChannelTypes();
				}
			if(ConstantUtil.HOSTNAME_H.equals(dropDownName)){
				dropDwnList =  authorizationSchemaMaitenancedao.getHostName();
				}
			if(ConstantUtil.HOSTID_H.equals(dropDownName)){
				dropDwnList =  authorizationSchemaMaitenancedao.getHostCode();
				}
			if(ConstantUtil.CURRENCY.equals(dropDownName)){
				dropDwnList =  authorizationSchemaMaitenancedao.getCurrencyCodes();
				}
			if(ConstantUtil.GROUPIDANDGROUPNAME.equals(dropDownName)){
				dropDwnList =  authorizationSchemaMaitenancedao.getGroupIDAndGroupName();
				}
			if(ConstantUtil.BRANCHCODE.equals(dropDownName)){
				dropDwnList =  authorizationSchemaMaitenancedao.getBranchCode();
				}
			if(ConstantUtil.BRANCHNAME.equals(dropDownName)){
				dropDwnList =  authorizationSchemaMaitenancedao.getBranchCodeName();
				}
			
			
		}catch(SQLException sqlException){
			logger.debug(sqlException);
			throw new NGPHException(ConstantUtil.DATA_ACCESS_PROBLEM);
		}
		
		System.out.println("list size in service Enquiry ANd this is ENQUIRY SERVICE"+dropDwnList.size());
		return dropDwnList;
	}
	public Map<String,Object> treeView() throws SQLException
	{
		Map <String,Object> treeView=new HashMap<String, Object>();
		treeView=authorizationSchemaMaitenancedao.treeView();
		return treeView;
		
	}
}
