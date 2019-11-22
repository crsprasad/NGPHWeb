package com.logica.ngph.service;

import java.sql.SQLException;


import org.apache.log4j.Logger;

import com.logica.ngph.common.ConstantUtil;
import com.logica.ngph.common.NGPHException;
import com.logica.ngph.dao.QuickStackDao;

public class QuickStacksService {
	
	static Logger logger = Logger.getLogger(QuickStacksService.class);
	QuickStackDao quickStackDao;

	public QuickStackDao getQuickStackDao() {
		return quickStackDao;
	}

	public void setQuickStackDao(QuickStackDao quickStackDao) {
		this.quickStackDao = quickStackDao;
	}
	public String getQuickStack(String getStack)throws NGPHException{
		String getStackvalues="" ;
		int amount=0;
		try{
			if(ConstantUtil.INBOUNDMESSAGECOUNT.equals(getStack)){
				getStackvalues =  quickStackDao.getInboundMessageCount();
				System.out.println("calling msg type");
				
			}
			if(ConstantUtil.OUTBOUNDMESSAGECOUNT.equals(getStack)){
				getStackvalues =  quickStackDao.getOutboundMessageCount();
				
			}
			if(ConstantUtil.TOTALAMOUNT.equals(getStack)){
				getStackvalues =  quickStackDao.getTotalAmout();
				
			}
			if(ConstantUtil.AUTHORIZATIONMESSAGE.equals(getStack)){
				getStackvalues =  quickStackDao.getAuthorizationCount();
				
			}
			if(ConstantUtil.RECIVEDAUTHORIZATION.equals(getStack)){
				getStackvalues =  quickStackDao.getRecivedAuthorization();
				
			}
			
			
		}catch(SQLException sqlException){
			logger.debug(sqlException);
			throw new NGPHException(ConstantUtil.DATA_ACCESS_PROBLEM);
		}
		return getStackvalues;
	}

}
