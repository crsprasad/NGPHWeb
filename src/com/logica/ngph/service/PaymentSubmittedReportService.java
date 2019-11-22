package com.logica.ngph.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.logica.ngph.common.ConstantUtil;
import com.logica.ngph.common.NGPHException;
import com.logica.ngph.dao.PaymentSubmittedReportDao;

public class PaymentSubmittedReportService {
	static Logger logger = Logger.getLogger(PaymentSubmittedReportService.class);

	PaymentSubmittedReportDao paymentSubmittedReportDao;

	public PaymentSubmittedReportDao getPaymentSubmittedReportDao() {
		return paymentSubmittedReportDao;
	}

	public void setPaymentSubmittedReportDao(
			PaymentSubmittedReportDao paymentSubmittedReportDao) {
		this.paymentSubmittedReportDao = paymentSubmittedReportDao;
	}
	public List<String> getList(String dropDownName)throws NGPHException{
		List<String> dropDwnList = new ArrayList<String>();
		
		try{
			if(ConstantUtil.MESSAGE_TYPE.equals(dropDownName)){
				dropDwnList =  paymentSubmittedReportDao.getMessageTypes();
			}
			if(ConstantUtil.HOSTNAME_H.equals(dropDownName)){
				System.out.println("calling getHostName");
				dropDwnList =  paymentSubmittedReportDao.getHostName("H");
			}
			if(ConstantUtil.HOSTID_H.equals(dropDownName)){
				System.out.println("calling getHostId");
				dropDwnList =  paymentSubmittedReportDao.getHostId("H");
			}
			if(ConstantUtil.HOSTNAME_P.equals(dropDownName)){
				System.out.println("calling getHostName");
				dropDwnList =  paymentSubmittedReportDao.getHostName("P");
			}
			if(ConstantUtil.HOSTID_P.equals(dropDownName)){
				System.out.println("calling getHostId");
				dropDwnList =  paymentSubmittedReportDao.getHostId("P");
			}
			if(ConstantUtil.REPORTCOLUMNS.equals(dropDownName)){
				System.out.println("calling REPORTCOLUMNS");
				dropDwnList =  paymentSubmittedReportDao.getReportColumns();
				
				
			}
			
		}catch(SQLException sqlException){
			logger.debug(sqlException);
			throw new NGPHException(ConstantUtil.DATA_ACCESS_PROBLEM);
		}
		
		System.out.println("list size in service Enquiry ANd this is ENQUIRY SERVICE"+dropDwnList.size());
		return dropDwnList;
			
	}
}
