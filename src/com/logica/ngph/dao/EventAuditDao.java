package com.logica.ngph.dao;

import java.util.List;

import com.logica.ngph.Entity.SecUsers;
import com.logica.ngph.dtos.EventAuditDto;
import com.logica.ngph.dtos.PaymentMessage;

public interface EventAuditDao {
	public List<EventAuditDto> getSearchList(String query,String formWhichTable); 
	public PaymentMessage getMessage(String msgRef);
	public List getTxnList();
	public List getDepartmentList();
	public List getBranchList();
	public SecUsers getUserBranch(String userID);
}
