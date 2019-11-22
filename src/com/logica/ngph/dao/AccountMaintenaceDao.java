package com.logica.ngph.dao;

import java.sql.SQLException;

import java.util.List;

import com.logica.ngph.Entity.Account;
import com.logica.ngph.Entity.Limits;

import com.logica.ngph.common.NGPHException;
import com.logica.ngph.dtos.AccountDto;
import com.logica.ngph.dtos.AddressDto;

public interface AccountMaintenaceDao {
	public String saveRule(String account,String MMID) throws SQLException;
	public String accountNOAllreadyExit(String accoutnNO);
	public void updateRule(Account account,Limits limits,String branch,String accountType) throws SQLException;
	public void deleteRule(Account account) throws SQLException;
	public List<AccountDto> accountSearch(String code,String branch)throws NGPHException;
	public List<AddressDto> getGridView()throws NGPHException;
	public List<AddressDto> getGridViewAdd(String accountNo,String mobileNo,String MMID)throws NGPHException;
	public List<AddressDto> showGridView(String accountNo,String branch)throws NGPHException;
	public String getLocnBin(String userID);
	public String update(String accountNo,String mobileNO,String MMID,String flag) throws SQLException;
	public String deleteAddressRef(String accountNo,String mobileNo,String MMID) throws SQLException;
	public String updateSequence(String MMID,String branch,int sequence);
	public String getMMID(String mobileNo,String MMID);
	public String setASDefault(String accountNo);
	 public List<AccountDto> benificaryAccountSearch(String code);
	
}
