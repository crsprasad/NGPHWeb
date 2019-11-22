package com.logica.ngph.service;



import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.logica.ngph.Entity.SecUsers;
import com.logica.ngph.common.NGPHException;
import com.logica.ngph.dtos.PaymentMessage;

public interface PaymentMessageService {
	
	public List<PaymentMessage> getPaymentMessage(String paymentStatus,String msgBranch,String msgDept,String msgDirection,String filterQuery) throws NGPHException;
	public void deletePayment(ArrayList<String> messageList,String messageStatus,String prevMessageStatus,String user,String comments);
	public List getAuthRef(String msgBranch,String msgDept,List messageRef);
	public SecUsers getUserDetails(String userID);
	public Map<String,Long> getStatusMonitor(String direction, String date1) throws NGPHException;
	public String insertIntoDBPoller(List MsgList);
	
	public String getScreenIDFromSupport(String msgType)throws  SQLException;
	public void changeMsgStatus2to99(String msgRef);
	public void changeMsgStatus99to2(String msgRef);
	public boolean checkInRptStatusIs2(String msgRef);
}
