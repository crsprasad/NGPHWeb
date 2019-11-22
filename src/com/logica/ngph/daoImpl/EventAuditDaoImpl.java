package com.logica.ngph.daoImpl;


import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.omg.PortableServer.LIFESPAN_POLICY_ID;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.logica.ngph.Entity.SecUsers;
import com.logica.ngph.common.PaymentStatusEnum;
import com.logica.ngph.dao.EventAuditDao;
import com.logica.ngph.dtos.EventAuditDto;
import com.logica.ngph.dtos.GenericManagerDto;
import com.logica.ngph.dtos.PaymentMessage;

public class EventAuditDaoImpl extends HibernateDaoSupport implements EventAuditDao {

	
	public List<EventAuditDto> getSearchList(String query,String formWhichTable) {
		List list  =  null;
		if(formWhichTable.equals("H")){
			list= getHibernateTemplate().find("select auditEventId,auditEventDesc,auditTransaction,auditBranch,auditDept,auditEventTime,auditMsgref from EventAuditHistory "+query + " order by auditEventTime desc");
			
		}else{
		 list= getHibernateTemplate().find("select auditEventId,auditEventDesc,auditTransaction,auditBranch,auditDept,auditEventTime,auditMsgref from EventAudit "+query + " order by auditEventTime desc");
		}
		List<EventAuditDto> searchList = new ArrayList<EventAuditDto>();
		for(int i = 0;i<list.size();i++)
		{
			EventAuditDto auditDto = new EventAuditDto();
			Object[] obj = (Object[]) list.get(i);
			auditDto.setEventId((String) obj[0]);
			auditDto.setEventDescription((String)obj[1]);
			auditDto.setEventTxnRef((String) obj[2]);
			auditDto.setEventBranch((String) obj[3]);
			auditDto.setEventDept((String) obj[4]);
			Timestamp dateTime= (Timestamp) obj[5];
			auditDto.setEventDate(dateTime.toString().substring(0,10));
			auditDto.setEventTime(dateTime.toString().substring(11,(dateTime.toString().length())));
			auditDto.setEventMsgRef((String) obj[6]);
			searchList.add(auditDto);
		}
		return searchList;
	}

	
	public PaymentMessage getMessage(String msgRef) {
		List directionList = getHibernateTemplate().find("select direction from ReportEntity where msgRef like '"+msgRef+"'");
		String direction="";
		List getPayments=null;
		if(directionList.size()!=0)
		{
			direction=directionList.get(0).toString();
		
		
		if(direction.equals("I")){
			getPayments= getHibernateTemplate().
			find("select concat(msgType ,' ',subMsgType) as msgType,channel,msgStatus,direction," +
					"txnReference,senderBank,receiverBank,currency,amount,msgValueDate ," +
					"concat(orderingCustomerName,' ',orderingCustomerAddress,' ',orderingCustomerId,' ',orderingCustomerCtry ,' ',orderingCustAccount,' ',orderingInstitution,' ',orderingInstitutionAcct) as orderingCustomer,"+
					"concat(beneficiaryCustomerName,' ',beneficiaryCustomerAddress,' ',beneficiaryCustomerID,' ',beneficiaryCustomerCtry,' ',beneficiaryCustAcct,' ',ultimateCreditorName,' ',ultimateCreditorAddress,' ',ultimateCreditorID)as beneficiaryCustomer ," +
					"concat(prevInstructingBank,' ',prevInstructingAgentAcct,' ',instructionsForNextAgtCode) as narration ,msgRef from ReportEntity where msgRef like '"+msgRef+"'");
			}
			else
			{
				getPayments= getHibernateTemplate().
				find("select concat(dstMsgType ,' ',dstMsgSubType) as msgType,dstChannelType,msgStatus,direction," +
						"txnReference,senderBank,receiverBank,currency,amount,msgValueDate ," +
						"concat(orderingCustomerName,' ',orderingCustomerAddress,' ',orderingCustomerId,' ',orderingCustomerCtry ,' ',orderingCustAccount,' ',orderingInstitution,' ',orderingInstitutionAcct) as orderingCustomer,"+
						"concat(beneficiaryCustomerName,' ',beneficiaryCustomerAddress,' ',beneficiaryCustomerID,' ',beneficiaryCustomerCtry,' ',beneficiaryCustAcct,' ',ultimateCreditorName,' ',ultimateCreditorAddress,' ',ultimateCreditorID)as beneficiaryCustomer ," +
						"concat(prevInstructingBank,' ',prevInstructingAgentAcct,' ',instructionsForNextAgtCode) as narration ,msgRef from ReportEntity where msgRef like '"+msgRef+"'");
				
			}
		
		System.out.println("size -- "+getPayments.size());
		PaymentMessage paymentMessage = new PaymentMessage();
			if(getPayments.size()!=0){
			
		   Object[] obj = (Object[]) getPayments.get(0);
		   paymentMessage.setMsgType((String)obj[0]);
			 
			 paymentMessage.setMsgChannel((String)obj[1]);
			 if(!obj[2].toString().equals(null)){
			 PaymentStatusEnum val = PaymentStatusEnum.findEnumByTag(obj[2].toString());
			 
			 paymentMessage.setPaymentStatus(val.toString());
			 }
			 else
				 paymentMessage.setPaymentStatus(" "); 
			 paymentMessage.setMsgDirection((String)obj[3]);
			 paymentMessage.setTxnReference((String)obj[4]);
			 paymentMessage.setSenderBank((String)obj[5]);
			 paymentMessage.setReceiverBank((String)obj[6]);
			 paymentMessage.setMsgCurrency((String)obj[7]);
			 paymentMessage.setMsgAmount((BigDecimal)obj[8]);
			 paymentMessage.setMsgValueDate(obj[9].toString().substring(0,10));
			 paymentMessage.setOrderingCustomer((String)obj[10]);
			 paymentMessage.setBeneficiaryCustomer((String)obj[11]);
			 paymentMessage.setNarration((String)obj[12]);
			 paymentMessage.setMsgRef((String)obj[13]);
			 return paymentMessage;
			}
		}
			return null;
		
	}

	
	public List getTxnList() {
		List list = getHibernateTemplate().find("select eventId from EventMaster where eventId is not null");
		return list;
	}

	
	public List getDepartmentList() {
		List list=getHibernateTemplate().find("select distinct(deptCode) from Department where deptCode is not null");
		return list;
	}

	
	public List getBranchList() {
		List list=getHibernateTemplate().find("select distinct(branchCode) from Branches where branchCode is not null");
		return list;
	}

	
	public SecUsers getUserBranch(String userID) {
		
		SessionFactory fact=getHibernateTemplate().getSessionFactory();
		Session sess=fact.openSession();
		SecUsers user = null;
		try{
		user = (SecUsers)sess.get(SecUsers.class, userID);
		
		}catch (Exception e) {
			e.printStackTrace();
			sess.close();
			fact.close();
		}
		sess.close();
		fact.close();
		return user;
	}

}
