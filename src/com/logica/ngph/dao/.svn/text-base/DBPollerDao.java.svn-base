package com.logica.ngph.dao;

import java.sql.Timestamp;
import java.util.List;

import com.logica.ngph.common.NGPHException;
import com.logica.ngph.common.dtos.MsgsPolledDto;
import com.logica.ngph.common.dtos.NgphCanonical;
import com.logica.ngph.dtos.SodEodTaskTDto;





public interface DBPollerDao {
	public List<MsgsPolledDto> getPolledMessages();
	public void perFormDbPoll(Timestamp businessDate,String prevMsgStatus,String msgRef,SodEodTaskTDto sodEodTaskTDto) throws NGPHException;
	public NgphCanonical getMessage(String msgRef);
	public Timestamp getBusinessDate(String branchCode);
	
}
