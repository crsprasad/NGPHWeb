package com.logica.ngph.dao;

import java.util.Date;
import java.util.List;

import com.logica.ngph.Entity.MsgPolled;
import com.logica.ngph.Entity.NgphCanonical;
import com.logica.ngph.common.NGPHException;

public interface MMIDManagerDao {
	
	public void populateMMIDData(NgphCanonical canonical) throws NGPHException;
	public void populateMMIDDataforPoller(MsgPolled msgsPoller) throws NGPHException;
	
	public String getStan(String identifier);
	public String getbusday_Date(String branchCode);
	public Date getcurrbusday_Date(String identifier);
	public void updateStan(String bus_date, String stan, String identifier);
	public String getInitialisedValue(String initEntry)throws NGPHException;
	public String getHostCategory(String hostId);
	public String getInitialisedValBranch(String initEntry, String branch);
	public String getMobNo(String acctNum);
	public List<String> getAcDetailsByAccountAndMobile(String accountNo, String mobNo, int addrSeq)throws NGPHException;


}
