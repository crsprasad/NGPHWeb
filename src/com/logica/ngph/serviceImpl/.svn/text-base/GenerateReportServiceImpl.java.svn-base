package com.logica.ngph.serviceImpl;

import java.util.List;

import com.logica.ngph.dao.PaymentSubmittedReportDao;
import com.logica.ngph.dtos.PaymentMessage;
import com.logica.ngph.dtos.ReportDto;

public class GenerateReportServiceImpl {
	
	PaymentSubmittedReportDao paymentSubmittedReportDao;
	
	
	
	public PaymentSubmittedReportDao getPaymentSubmittedReportDao() {
		return paymentSubmittedReportDao;
	}



	public void setPaymentSubmittedReportDao(
			PaymentSubmittedReportDao paymentSubmittedReportDao) {
		this.paymentSubmittedReportDao = paymentSubmittedReportDao;
	}



	public List<ReportDto> getSearchResult(String stringQuery) {
		List <ReportDto> SearchList=paymentSubmittedReportDao.getSearchResult(stringQuery);
		System.out.println("Size of the list"+SearchList.size());
		for(int i=0;i<SearchList.size();i++)
		{
				System.out.println(SearchList.get(i));
		}
		return SearchList;
	}
}
