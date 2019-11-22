package com.logica.ngph.serviceImpl;

import java.util.List;

import com.logica.ngph.dao.MMIDReportDao;
import com.logica.ngph.dtos.AccountDto;
import com.logica.ngph.service.MMIDReportService;

public class MMIDReportServiceImpl implements MMIDReportService {
	MMIDReportDao mmidReportDao;
	
	public MMIDReportDao getMmidReportDao() {
		return mmidReportDao;
	}

	public void setMmidReportDao(MMIDReportDao mmidReportDao) {
		this.mmidReportDao = mmidReportDao;
	}

	
	public List<AccountDto> getAccountDetails(String accountNo) {
	
		List<AccountDto> result = mmidReportDao.getAccountDetails(accountNo);
		return result;
	}

	
	public List<AccountDto> getAccountForSearch(String accountNo) {
	
		List<AccountDto> result = mmidReportDao.getAccountForSearch(accountNo);
		return result;
	}
	
}
