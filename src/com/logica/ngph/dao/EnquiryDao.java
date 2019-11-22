package com.logica.ngph.dao;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import com.logica.ngph.Entity.DiscrepanciesReport;
import com.logica.ngph.Entity.RRNDiscrepancy;
import com.logica.ngph.common.dtos.NgphCanonical;
import com.logica.ngph.dtos.PartyDTO;
import com.logica.ngph.dtos.PaymentMessage;

public interface EnquiryDao {
	
	public List<String> getEnquiryMessageTypes() throws SQLException;
	public List<String> getEnquirySubMessageTypes() throws SQLException;
	public List<String> getEnquiryChannel() throws SQLException;
	public List<String> getEnquiryCurrency() throws SQLException;
	public List<PaymentMessage> getSearchResult(String stringQuery,String direction,String tableToUse);
	public String getRawMessage(String msgRef)throws SQLException;
	
	public DiscrepanciesReport getDiscrepanciesReport(String date);
	public List<RRNDiscrepancy> getRRPOutwardList(String direction,String date);
	
	public String generateDiscrepanciesReport(Date date);	
	public List<String> getEnquiryStateName() throws SQLException;
	public List<String> getEnquiryDistrictName(String stateName) throws SQLException;
	public List<String> getEnquiryCityName(String stateName, String districtName) throws SQLException;
	public List<PartyDTO> getIFSCDetailsData(String bankName, String stateName, String districName, String cityName, String ifscCode, String branchName)throws SQLException;
	public List<PartyDTO> getBranchNameData(String branchName,String stateName,String districtName, String cityName, String bankName, String ifscCode)throws SQLException;
	public List<PartyDTO> getIFSCCodeListData(String ifscCode,String stateName,String districtName, String cityName, String bankName, String branchName)throws SQLException;
	public List<PartyDTO> getIFSCCodeList(String ifscCode)throws SQLException;
	public NgphCanonical fetchMsgData(String msgRef)throws SQLException;
}
