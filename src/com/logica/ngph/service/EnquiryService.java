package com.logica.ngph.service;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.logica.ngph.Entity.DiscrepanciesReport;
import com.logica.ngph.Entity.RRNDiscrepancy;
import com.logica.ngph.common.ConstantUtil;
import com.logica.ngph.common.NGPHException;
import com.logica.ngph.common.dtos.NgphCanonical;
import com.logica.ngph.dao.EnquiryDao;
import com.logica.ngph.dtos.PartyDTO;

public class EnquiryService {
	static Logger logger = Logger.getLogger(SearchService.class);
	EnquiryDao enquiryDao;
	
	
	
	public EnquiryDao getEnquiryDao() {
		return enquiryDao;
	}



	public void setEnquiryDao(EnquiryDao enquiryDao) {
		this.enquiryDao = enquiryDao;
	}



	public List<String> getEnquiryList(String dropDownName)throws NGPHException{
		List<String> dropDwnList = new ArrayList<String>();
		
		try{
			if(ConstantUtil.ENQUIRY_MSG_TYPE.equals(dropDownName)){
				dropDwnList =  enquiryDao.getEnquiryMessageTypes();
				
			}
			if(ConstantUtil.EnquirySubMsgType.equals(dropDownName)){
				dropDwnList =  enquiryDao.getEnquirySubMessageTypes();
				
			}
			if(ConstantUtil.EnquiryChannel.equals(dropDownName)){
				dropDwnList =  enquiryDao.getEnquiryChannel();
				
			}
			if(ConstantUtil.ENQUIRY_CURRENCY.equals(dropDownName)){
				dropDwnList =  enquiryDao.getEnquiryCurrency();
				
			}
			if(ConstantUtil.ENQUIRY_STATE_NAME.equals(dropDownName)){
				dropDwnList =  enquiryDao.getEnquiryStateName();
				
			}
			
			
			
		}catch(SQLException sqlException){
			logger.debug(sqlException);
			throw new NGPHException(ConstantUtil.DATA_ACCESS_PROBLEM);
		}
		
		System.out.println("list size in service Enquiry ANd this is ENQUIRY SERVICE"+dropDwnList.size());
		return dropDwnList;
	}
	
	
	public String getRawMessage(String msgRef) throws SQLException
	{
		return enquiryDao.getRawMessage(msgRef);
	}
	
	public DiscrepanciesReport getDiscrepanciesReport(String date)
	{
		return enquiryDao.getDiscrepanciesReport(date);
	}
	
	public List<RRNDiscrepancy> getRRPOutwardList(String direction,String date)
	{
		return enquiryDao.getRRPOutwardList(direction,date);
	}

	public String generateDiscrepanciesReport(Date date)
	{
		return enquiryDao.generateDiscrepanciesReport(date);
	}
	
	public List<String> getEnquiryDistrictName(String stateName) throws SQLException
	{
		return enquiryDao.getEnquiryDistrictName(stateName);
	}

	public List<String> getEnquiryCityName(String stateName, String districtName) throws SQLException
	{
		return enquiryDao.getEnquiryCityName(stateName, districtName);
	}
	
	public List<PartyDTO> getIFSCDetailsData(String bankName, String stateName, String districtName, String cityName, String IfscCode, String branchName) throws SQLException
	{
		return enquiryDao.getIFSCDetailsData(bankName, stateName, districtName, cityName, IfscCode, branchName);
	}
	
	public List<PartyDTO> getBranchNameData(String branchName,String stateName,String districtName, String cityName, String bankName, String ifscCode) throws SQLException
	{
		return enquiryDao.getBranchNameData(branchName, stateName, districtName, cityName, bankName, ifscCode);
	}
	
	public List<PartyDTO> getIFSCCodeListData(String ifscCode,String stateName,String districtName, String cityName, String bankName, String branchName) throws SQLException
	{
		return enquiryDao.getIFSCCodeListData(ifscCode, stateName, districtName, cityName, bankName, branchName);
	}
	
	public List<PartyDTO> getIFSCCodeList(String ifscCode) throws SQLException
	{
		return enquiryDao.getIFSCCodeList(ifscCode);
	}
	
	public NgphCanonical fetchMsgData(String msgRef) throws SQLException
	{
		return enquiryDao.fetchMsgData(msgRef);
	}
}
