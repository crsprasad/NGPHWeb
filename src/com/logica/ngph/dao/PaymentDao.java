package com.logica.ngph.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.logica.ngph.Entity.ModifiedMessage;
import com.logica.ngph.Entity.NgphCanonical;
import com.logica.ngph.Entity.SecUsers;

import com.logica.ngph.dtos.PaymentMessage;


public interface PaymentDao {
	
	public Map<String, List<String>> getMsgSupport();
	public void savePayment(NgphCanonical ngphCanonical) ;
	
	public List<PaymentMessage> getPaymentMessage(String paymentStatus,
			String msgBranch,String msgDept,String msgDirection,String filterQuery) throws SQLException;
	
	public com.logica.ngph.common.dtos.NgphCanonical getPayment(String msgRef)throws SQLException;
	
	public void saveRepairedPayment(ModifiedMessage modifiedMessage) throws SQLException;
	
	public void deletePayment(final ArrayList<String> messageList,String messageStatus,String prevMessageStatus,String user,String comments) ;
	public List getAuthRef(String msgBranch,String msgDept,List messageRef);
	public SecUsers getUserDetails(String userID) ;
	public Map<String,Long> getStatusMonitor(String direction,String queryDate)throws SQLException;
	public String insertIntoDBPoller(List insertIntoDBPoller);
	
	public String getScreenIDFromSupport(String msgType) throws SQLException;
	
	public void changeMsgStatus2to99(String msgRef);
	public void changeMsgStatus99to2(String msgRef);
	public boolean checkInRptStatusIs2(String msgRef);
}
