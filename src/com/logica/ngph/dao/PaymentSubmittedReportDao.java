package com.logica.ngph.dao;

import java.sql.SQLException;


import java.util.List;


import com.logica.ngph.dtos.ReceiverDto;
import com.logica.ngph.dtos.ReportDto;

public interface PaymentSubmittedReportDao {

	public List<String> getMessageTypes() throws SQLException;
	public List<String> getHostName(String EIType) throws SQLException;
	public List<String> getHostId(String EIType) throws SQLException;
	public List<String> getReportColumns() throws SQLException;
	public List<ReportDto> getSearchResult(String stringQuery);
	public List<ReceiverDto> getPartyName(String code,String name) throws SQLException;
	public List getStreamID(String streamID);

}
