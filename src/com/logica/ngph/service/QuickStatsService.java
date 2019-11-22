package com.logica.ngph.service;

import java.sql.SQLException;
import java.util.List;

import javax.swing.text.StyledEditorKit.BoldAction;



import org.apache.log4j.Logger;

import com.logica.ngph.common.ConstantUtil;
import com.logica.ngph.common.NGPHException;
import com.logica.ngph.dao.QuickStackDao;

public class QuickStatsService {
	
	static Logger logger = Logger.getLogger(QuickStatsService.class);
	QuickStackDao quickStackDao;

	public QuickStackDao getQuickStackDao() {
		return quickStackDao;
	}

	public void setQuickStackDao(QuickStackDao quickStackDao) {
		this.quickStackDao = quickStackDao;
	}
	public String getQuickStats(String getStack)throws NGPHException{
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
			if(ConstantUtil.INBOUNDCHANNELCOUNT.equals(getStack)){
				getStackvalues =  quickStackDao.getInBoundChannelCount();
			}
			if(ConstantUtil.OUTBOUNDCHANNELCOUNT.equals(getStack)){
				getStackvalues =  quickStackDao.getOutBoundChannelCount();
			}
			if(ConstantUtil.CLOSINGBALANCEVSCURRENCY.equals(getStack)){
				getStackvalues =  quickStackDao.getClosingBalanceBarGraph();
			}
			if(ConstantUtil.CLOSINGBALANCEVSCURRENCYDATE.equals(getStack)){
				getStackvalues =  quickStackDao.getClosingBalanceLineGraph();
			}
			if(ConstantUtil.STACKGRAPH.equals(getStack)){
				getStackvalues =  quickStackDao.getPartyStackGraph();
			}
			
		}catch(SQLException sqlException){
			logger.error(sqlException);
			throw new NGPHException(ConstantUtil.DATA_ACCESS_PROBLEM);
		}
		return getStackvalues;
	}
	
	public int getMessage(String getStack)throws NGPHException, SQLException{
		
		int getStackvalues=0;
		if(ConstantUtil.MESSAGECOUNT.equals(getStack)){
			getStackvalues =  quickStackDao.getMessageCount();
			
		}
	return getStackvalues;
	}
	
	public List<com.logica.ngph.dtos.EIDto> getEI_IMPSStatus() throws SQLException
	{
		
		List <com.logica.ngph.dtos.EIDto> result=quickStackDao.getEI_IMPSStatus();
		
		return result;
	}

}
