package com.logica.ngph.service;

import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

import com.logica.ngph.Entity.Account;
import com.logica.ngph.Entity.Limits;
import com.logica.ngph.common.ConstantUtil;
import com.logica.ngph.common.NGPHException;
import com.logica.ngph.dao.AccountMaintenaceDao;
import com.logica.ngph.dtos.AccountDto;
import com.logica.ngph.dtos.AddressDto;


public class AccountMaintenaceService {
	static Logger logger = Logger.getLogger(SearchService.class);
	AccountMaintenaceDao accountMaintenaceDao;
	public AccountMaintenaceDao getAccountMaintenaceDao() {
		return accountMaintenaceDao;
	}
	public void setAccountMaintenaceDao(AccountMaintenaceDao accountMaintenaceDao) {
		this.accountMaintenaceDao = accountMaintenaceDao;
	}
	public String saveRules(String account,String MMID)throws NGPHException{
		String returnvalue= "";
		try {
			returnvalue = accountMaintenaceDao.saveRule(account,MMID);
		} catch (SQLException sqlException) {
			logger.debug(sqlException);
			throw new NGPHException(ConstantUtil.DATA_ACCESS_PROBLEM);
		}
		return returnvalue;
	}
public void updateRules(Account account,Limits limits,String branch,String accountType)throws NGPHException{
		
		try {
			accountMaintenaceDao.updateRule(account,limits,branch,accountType);
		} catch (SQLException sqlException) {
			logger.debug(sqlException);
			throw new NGPHException(ConstantUtil.DATA_ACCESS_PROBLEM);
		}
	}
	public void deleteRules(Account account)throws NGPHException{
	
	try {
		accountMaintenaceDao.deleteRule(account);
	} catch (SQLException sqlException) {
		logger.debug(sqlException);
		throw new NGPHException(ConstantUtil.DATA_ACCESS_PROBLEM);
	}
	
}
	public List<AccountDto> accountSearch(String code,String branch)throws NGPHException, SQLException{
		
		 List<AccountDto>	searchList = null;
		searchList = accountMaintenaceDao.accountSearch(code,branch);
		
		
		return searchList;
	}
	public List<AddressDto> getGridView() throws NGPHException
	{
		List<AddressDto>	gridList = null;
		gridList = accountMaintenaceDao.getGridView();
		
		
		return gridList;
	}
	public String accountNOAllreadyExit(String accountNo)throws NGPHException{
		
		
			return accountMaintenaceDao.accountNOAllreadyExit(accountNo);
		
		
	}
	public String getLocnBin(String userID)throws NGPHException{
		
		
		return accountMaintenaceDao.getLocnBin(userID);
	
	
}
	public List<AddressDto> getGridViewAdd(String accountNo,String mobileNo,String MMID) throws NGPHException
	{
		List<AddressDto>	gridList = null;
		gridList = accountMaintenaceDao.getGridViewAdd(accountNo,mobileNo,MMID);
		
		
		return gridList;
	}
	public List<AddressDto> showGridView(String accountNo,String branch) throws NGPHException
	{
		List<AddressDto>	gridList = null;
		gridList = accountMaintenaceDao.showGridView(accountNo,branch);
		
		
		return gridList;
	}
	
	public String update(String accountNo, String mobileNO,String MMID,String flag) throws SQLException
	{
				return accountMaintenaceDao.update(accountNo,mobileNO,MMID,flag);
	}
	public String deleteAddressRef(String accountNo,String mobileNo,String MMID) throws SQLException
	{
		
		return accountMaintenaceDao.deleteAddressRef(accountNo,mobileNo,MMID);
	}
	
	public String updateSequence(String MMID,String branch,int sequence)
	{
		return accountMaintenaceDao.updateSequence(MMID,branch,sequence);
	}
	public String getMMID(String mobileNo,String MMID)
	{
		return accountMaintenaceDao.getMMID(mobileNo,MMID);
	}
	 public String setASDefault(String accountNo)
	{
		return accountMaintenaceDao.setASDefault(accountNo);
	}
	
	 public List<AccountDto> benificaryAccountSearch(String code)throws NGPHException, SQLException{
			
		 List<AccountDto>	searchList = null;
		searchList = accountMaintenaceDao.benificaryAccountSearch(code);
		
		
		return searchList;
	}
	 
	
}
