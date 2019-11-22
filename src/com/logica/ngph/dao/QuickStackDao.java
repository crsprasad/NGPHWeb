package com.logica.ngph.dao;


import java.sql.SQLException;
import java.util.List;


public interface QuickStackDao {
	public String getInboundMessageCount() throws SQLException;
	public String getOutboundMessageCount() throws SQLException;
	public String getAuthorizationCount() throws SQLException;
	public String getTotalAmout() throws SQLException;
	public String getRecivedAuthorization() throws SQLException;
	public int getMessageCount() throws SQLException;
	public List<com.logica.ngph.dtos.EIDto> getEI_IMPSStatus() throws SQLException;
    public String getInBoundChannelCount() throws SQLException;
    public String getOutBoundChannelCount() throws SQLException;
    public String getClosingBalanceBarGraph() throws SQLException;
    public String getClosingBalanceLineGraph() throws SQLException;
    public String getPartyStackGraph() throws SQLException;
}
