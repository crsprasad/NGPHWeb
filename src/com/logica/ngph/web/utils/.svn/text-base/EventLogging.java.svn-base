package com.logica.ngph.web.utils;

import java.text.MessageFormat;

import com.logica.ngph.Entity.SecUsers;
import com.logica.ngph.common.NGPHException;
import com.logica.ngph.common.dtos.EventAudit;
import com.logica.ngph.service.AuditService;
import com.logica.ngph.service.PaymentMessageService;

public class EventLogging {
	

	public EventAudit getEventAuditDto(String userId,String SreenId,String eventId,String loggingComment,String auditRef,String auditMsgRef){
		EventAudit eventAudit = getEventAuditDto(userId);
		eventAudit.setAuditEventId(eventId);
		String[] extraInformation = {userId,SreenId};		
		eventAudit.setExtraInformation(extraInformation);
		String eventDescription = "User {0} has "+loggingComment+" {1}";
		MessageFormat msgFormat = new MessageFormat(eventDescription);		
		eventAudit.setAuditEventDesc(msgFormat.format(extraInformation));
		eventAudit.setAuditTransactionRef(auditRef);
		eventAudit.setAuditMessageRef(auditMsgRef);
		
		return eventAudit;		
	}
	private EventAudit getEventAuditDto(String UserID){
		EventAudit eventAudit = new EventAudit();
		PaymentMessageService paymentMessageService = (PaymentMessageService)ApplicationContextProvider.getBean("paymentMessageService");		
		eventAudit.setAuditSource("0");
		SecUsers userDetails= paymentMessageService.getUserDetails(UserID);
			String msgBranch = userDetails.getUserBranch();
			String msgDept = userDetails.getUserDept();
		eventAudit.setAuditBranch(msgBranch);
		
		eventAudit.setAuditDept(msgDept);
		eventAudit.setAuditSource(getClass().getName());
		
		
		return eventAudit;
		
	}
	
	public void doEventLogging(String userId,String screenID,String eventID,String loggingComment, String auditRef,String auditMsgRef) throws NGPHException
	{
		AuditService	auditService = (AuditService)ApplicationContextProvider.getBean("auditService");
		auditService.saveEventAuditData(getEventAuditDto(userId,screenID,eventID,loggingComment,auditRef,auditMsgRef));
	}

}
