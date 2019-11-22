package com.logica.ngph.daoImpl;

import java.io.BufferedReader;
import java.sql.Clob;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.logica.ngph.Entity.MsgPolled;
import com.logica.ngph.Entity.NgphCanonical;
import com.logica.ngph.common.NGPHException;
import com.logica.ngph.dao.MMIDManagerDao;

public class MMIDManagerDaoImpl extends HibernateDaoSupport implements	MMIDManagerDao {

	Logger logger = Logger.getLogger(MMIDManagerDaoImpl.class);
	
private JdbcTemplate jdbcTemplate;
	
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public void populateMMIDData(NgphCanonical canonical) throws NGPHException 
	{
		try
		{
			getHibernateTemplate().saveOrUpdate(canonical);
			System.out.println("Data Inserted into DB for " + canonical.getMsgRef());
		}
		catch (Exception e) {
			logger.error(e, e);
		}
	}
	
	public void populateMMIDDataforPoller(MsgPolled msgsPoller) throws NGPHException
	{
		try
		{
			getHibernateTemplate().saveOrUpdate(msgsPoller);
			System.out.println("Data Inserted into DB for Poller " + msgsPoller.getMsgRef());
		}
		catch (Exception e) {
			logger.error(e, e);
		}
	}
	
	public String getStan(String identifier)
	{
		String stan = null;
		String query = "select SEQ_SEQUENCE from ta_sequences where SEQ_IDENTIFIER =?";
		try
		{
			stan = jdbcTemplate.queryForObject(query, new Object[]{identifier}, String.class);
		}
		catch (EmptyResultDataAccessException e) {
			logger.error(e,e);
		}
		catch (IncorrectResultSizeDataAccessException e) {
			logger.error(e,e);
		}
		catch (Exception e) {
			logger.error(e,e);
		}
		return stan;
	}
	
	public String getbusday_Date(String branchCode)
	{
		Date busDay_Date = null;
		String date=null;
		
		String query = "select TO_DATE(BUSDAY_DATE) from ta_businessdaym where busday_branch=?";
		try
		{
			busDay_Date = jdbcTemplate.queryForObject(query, new Object[]{branchCode}, Date.class);

			SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yy");
			date = sdf.format(busDay_Date);
		}
		catch (EmptyResultDataAccessException e) 
		{
			logger.error(e,e);
		}
		catch (IncorrectResultSizeDataAccessException e) {
			logger.error(e,e);
		}
		catch (Exception e) {
			logger.error(e,e);
		}
		return date;
	}
	public Date getcurrbusday_Date(String identifier)
	{
		Date busDay_Date = null;
		String query = "select TO_DATE(SEQ_LASTMODDATE) from ta_sequences where seq_identifier =?";
		try
		{
			busDay_Date = jdbcTemplate.queryForObject(query, new Object[]{identifier}, Date.class);
		}
		catch (EmptyResultDataAccessException e) 
		{
			logger.error(e,e);
		}
		catch (IncorrectResultSizeDataAccessException e) {
			logger.error(e,e);
		}
		catch (Exception e) 
		{
			logger.error(e,e);
		}
		return busDay_Date;
	}
	
	public void updateStan(String bus_date, String stan, String identifier)
	{
		String query = "UPDATE ta_sequences SET SEQ_SEQUENCE =?, SEQ_LASTMODDATE=? WHERE SEQ_IDENTIFIER =?"; 

			try
			{
				jdbcTemplate.update(query,new Object[]{stan,bus_date,identifier});
			}
			catch (Exception e) 
			{
				logger.error(e,e);
			}
	}
	
	public String getInitialisedValue(String initEntry)throws NGPHException
	{
		//logInfo("getInitialisedValue() Start...");
		String query = "SELECT INIT_VALUE FROM INITIALISATIONM WHERE INIT_ENTRY=?";
		
		String initialisedValue = null;
		Clob clob = null;
		
		try{
			clob = jdbcTemplate.queryForObject(query, new Object[] {initEntry}, Clob.class);
		}catch(EmptyResultDataAccessException e)
		{
			logger.error(e,e);
		}
		catch (IncorrectResultSizeDataAccessException e) {
			logger.error(e,e);
		}catch(Exception e)
		{
			logger.error(e,e);
			throw new NGPHException();
		}
		
		if(clob != null)
		{
			StringBuffer strOut = new StringBuffer();
			String aux;
			try {
			BufferedReader br = new BufferedReader(clob.getCharacterStream());
			while ((aux=br.readLine())!=null)
				strOut.append(aux);
			} catch (java.sql.SQLException e) {
				logger.error(e,e);
			} catch (java.io.IOException e) {
				logger.error(e,e);
			}
			initialisedValue = strOut.toString();
		}
		//logInfo("getInitialisedValue() End...");
		return initialisedValue;
	}
	
	public String getHostCategory(String hostId)
	{
		String hostCat = null;
		String query = "select EI_HOST_CATGORY from ta_ei where EI_CODE =?";
		try
		{
			hostCat = jdbcTemplate.queryForObject(query, new Object[]{hostId}, String.class);
		}
		catch (EmptyResultDataAccessException e) {
			logger.error(e,e);
		}
		catch (IncorrectResultSizeDataAccessException e) {
			logger.error(e,e);
		}
		catch (Exception e) {
			logger.error(e,e);
		}
		return hostCat;
		
	}

	public String getInitialisedValBranch(String initEntry, String branch)
	{
		String result=null;
		Clob clob = null;
		try
		{
			String bicQuery = "select init_value from initialisationm where init_entry = '" + initEntry + "' and init_branch='" +branch +"'";
			clob = jdbcTemplate.queryForObject(bicQuery, Clob.class);
			int clobLength = (int) clob.length();
			result = clob.getSubString(1, clobLength);
		}
		catch (EmptyResultDataAccessException e) {
			logger.error(e,e);
		}
		catch (IncorrectResultSizeDataAccessException e) {
			logger.error(e,e);
		}
		catch(Exception e)
		{
			logger.error(e,e);
		}
		return result;
	
	}
	
	public String getMobNo(String acctNum)
	{
		String result = null;
		//Fetch the first address if only account is available to fetch the mobile number - kind of default address and default mobile number
		String query = "select ADDR_MOBILE from ta_addresses where ADDR_REF = (select ACCT_ADDRREF from ta_accounts where ACCT_NUM='" + acctNum + "') AND ADDR_SEQ = 1";
		try
		{
			result = jdbcTemplate.queryForObject(query, String.class);
		}
		catch (EmptyResultDataAccessException e) {
			logger.error(e,e);
		}
		catch (IncorrectResultSizeDataAccessException e) {
			logger.error(e,e);
		}
		catch (Exception e) {
			logger.error(e,e);
		}
		return result;
	}

	public List<String> getAcDetailsByAccountAndMobile(String accountNo, String mobNo, int addrSeq)throws NGPHException
	{
		SqlRowSet srs = null;
		String query = null;
		List<String> holder = new ArrayList<String>();;

		if (addrSeq > 0)
		{
			query = "select ACCT_ACNAME, ACCT_CLOSED, ADDR_PSTLADR_TWNNM, ADDR_PSTLADR_CTRYSUBDVSN, ADDR_PSTLADR_CTRY FROM TA_ACCOUNTS, TA_ADDRESSES where TA_ACCOUNTS.ACCT_ADDRREF =  TA_ADDRESSES.ADDR_REF AND ACCT_NUM = ? AND ADDR_MOBILE=? AND ADDR_SEQ = ?";
			srs = jdbcTemplate.queryForRowSet(query, new Object[]{accountNo, mobNo, addrSeq});
		}
		else
		{
			query = "select ACCT_ACNAME, ACCT_CLOSED, ADDR_PSTLADR_TWNNM, ADDR_PSTLADR_CTRYSUBDVSN, ADDR_PSTLADR_CTRY FROM TA_ACCOUNTS, TA_ADDRESSES where TA_ACCOUNTS.ACCT_ADDRREF =  TA_ADDRESSES.ADDR_REF AND ACCT_NUM = ? AND ADDR_MOBILE=?";
			srs = jdbcTemplate.queryForRowSet(query, new Object[]{accountNo, mobNo});
		}
		try
		{
			while(srs.next())
			{
				holder.add(srs.getString("ACCT_ACNAME"));
				holder.add(Integer.toString((srs.getInt("ACCT_CLOSED"))));
				holder.add(srs.getString("ADDR_PSTLADR_TWNNM"));
				holder.add(srs.getString("ADDR_PSTLADR_CTRYSUBDVSN"));
				holder.add(srs.getString("ADDR_PSTLADR_CTRY"));
			}
		}catch(EmptyResultDataAccessException e)
		{
			logger.error(e,e);
		}
		catch (IncorrectResultSizeDataAccessException e) {
			logger.error(e,e);
		}catch(Exception e)
		{
			logger.error(e,e);
		}
		return holder;
	}
}
