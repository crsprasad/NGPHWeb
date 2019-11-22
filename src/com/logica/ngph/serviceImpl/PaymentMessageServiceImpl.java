package com.logica.ngph.serviceImpl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.logica.ngph.Entity.SecUsers;
import com.logica.ngph.common.ConstantUtil;
import com.logica.ngph.common.NGPHException;
import com.logica.ngph.dao.PaymentDao;
import com.logica.ngph.dtos.PaymentMessage;
import com.logica.ngph.service.PaymentMessageService;

public class PaymentMessageServiceImpl implements PaymentMessageService{
	static Logger logger = Logger.getLogger(PaymentMessageServiceImpl.class);
	PaymentDao paymentDao;
	
	public PaymentDao getPaymentDao() {
		return paymentDao;
	}
	public void setPaymentDao(PaymentDao paymentDao) {
		this.paymentDao = paymentDao;
	}
	


	
	/**
	* This method is used get the message status queue data
	* @return List<PaymentMessage>
	*/
	
	public List<PaymentMessage> getPaymentMessage(String paymentStatus,
			String msgBranch,String msgDept,String msgDirection,String filterQuery) throws NGPHException {
		List<PaymentMessage> paymentMessageList = null;
		
		try {
			
	 paymentMessageList = paymentDao.getPaymentMessage(paymentStatus,msgBranch,msgDept,msgDirection,filterQuery);
		} catch (SQLException sqlException) {
			logger.debug(sqlException);
			throw new NGPHException(ConstantUtil.DATA_ACCESS_PROBLEM);
		}
		
		return paymentMessageList;
	}
	
	/**
	* This method is used to delete the payment status when manual repair
	* @return void
	*/
	
	public void deletePayment(ArrayList<String> messageList,String messageStatus,String prevMessageStatus,String user,String comments) {
		paymentDao.deletePayment(messageList,messageStatus,prevMessageStatus,user,comments);
		
	}
	
	public List getAuthRef(String msgBranch, String msgDept, List messageRef) {
		
		return paymentDao.getAuthRef(msgBranch, msgDept, messageRef);
	}
	
	public SecUsers getUserDetails(String userID) {
		// TODO Auto-generated method stub
		return paymentDao.getUserDetails(userID);
	}
	/**
	 * This method getStatusMonitor used for display the screen data for Status Monitor page
	 */
	public Map<String,Long> getStatusMonitor(String direction,String date1) throws NGPHException{
		Map<String,Long> StatusMap = new HashMap<String,Long>();
		try {
			StatusMap = paymentDao.getStatusMonitor(direction,date1);
		} catch (SQLException sqlException) {
			logger.debug(sqlException);
			throw new NGPHException(ConstantUtil.DATA_ACCESS_PROBLEM);
		}
		
		return StatusMap;
	}
	public String insertIntoDBPoller(List insertIntoDBPoller) {
		// TODO Auto-generated method stub
		return paymentDao.insertIntoDBPoller(insertIntoDBPoller);
	}

	public String getScreenIDFromSupport(String msgType) throws SQLException {
		
		return paymentDao.getScreenIDFromSupport(msgType);
	}
	
	public void changeMsgStatus2to99(String msgRef) {
		try {
			 paymentDao.changeMsgStatus2to99(msgRef);
		} catch (Exception e) {
			logger.debug(e);
			e.printStackTrace();
		}
		
	}

	

	
	public void changeMsgStatus99to2(String msgRef) {
		try {
			 paymentDao.changeMsgStatus99to2(msgRef);
		} catch (Exception e) {
			logger.debug(e);
			e.printStackTrace();
		}
		
	}


	public boolean checkInRptStatusIs2(String msgRef) {
		boolean result = false;
		try {
			result = paymentDao.checkInRptStatusIs2(msgRef);
		} catch (Exception e) {
			logger.debug(e);
			e.printStackTrace();
		}
		return result;
	}
}
