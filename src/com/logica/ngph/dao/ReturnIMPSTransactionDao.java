package com.logica.ngph.dao;

import java.sql.SQLException;
import java.util.List;

import com.logica.ngph.Entity.MsgPolled;
import com.logica.ngph.dtos.ReturnIMPSTransactionDto;

public interface ReturnIMPSTransactionDao {
	List<ReturnIMPSTransactionDto> getReturnDetails(String date) throws SQLException;
	public String savePolled(MsgPolled msgPolled) throws SQLException;

}
