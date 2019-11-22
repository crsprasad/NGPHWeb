package com.logica.ngph.daoImpl;


import java.math.BigDecimal;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.logica.ngph.common.PaymentStatusEnum;
import com.logica.ngph.common.enums.TransactionTypeEnum;
import com.logica.ngph.dao.PaymentSubmittedReportDao;
import com.logica.ngph.dtos.ReceiverDto;
import com.logica.ngph.dtos.ReportDto;

public class PaymentSubmittedReportDaoImpl extends HibernateDaoSupport implements PaymentSubmittedReportDao {

	
	public List<String> getMessageTypes() throws SQLException {
		@SuppressWarnings("rawtypes")
		List msgTypeList = getHibernateTemplate().
		find("select distinct msgSupports.supportMsgType as msgType from com.logica.ngph.Entity.MsgSupport as msgSupports where msgSupports.supportMsgType IS NOT NULL order by (msgSupports.supportMsgType)");
		System.out.println("*****************" + msgTypeList);
		return msgTypeList;
	}

	
	public List<String> getHostName(String EIType) throws SQLException {
		@SuppressWarnings("rawtypes")
		List HostName= getHibernateTemplate().find("select hostName as HostName from com.logica.ngph.Entity.Host as Host where queueType=? and hostName IS NOT NULL order by (hostName)",EIType);
		System.out.println("*****************" + HostName);
		return HostName;
	}

	
	public List<String> getHostId(String EIType) throws SQLException {
		@SuppressWarnings("rawtypes")
		List HostCode= getHibernateTemplate().
		 find("select hostCode as HostCode from com.logica.ngph.Entity.Host as Host where queueType=? and hostCode IS NOT NULL order by (hostCode) ",EIType);
		return HostCode;
	}

	
	public List<String> getReportColumns() throws SQLException {
		List Column_name= getHibernateTemplate().
		 find("select columnName as ColumnName from com.logica.ngph.Entity.ColumnDetailsEntity where tableName ='TA_MSGS_RPT' ");
		return Column_name;
	}

	
	public List<ReportDto> getSearchResult(String stringQuery) {
		List <ReportDto> returnList = new ArrayList<ReportDto>();
		System.out.println("select msgRef, concat(msgType ,' ',subMsgType) as msgType, branch, " +
				"senderBank, receiverBank, msgStatus, txnReference, issueDate, orderingCustomerName, beneficiaryCustomerName, " +
				"applicentBankCode, applicentBankLoc, currency, amount, expDate, lastDateofShipment, amendmentDate, " +
				"noofAmendments, reducedAmt as amountReduced, outstandAmt as amountofOutstanding, relatedRefrence "+
				"from com.logica.ngph.Entity.ReportEntity "+stringQuery);
		List DataList= getHibernateTemplate().
		find("select msgRef, concat(msgType ,' ',subMsgType) as msgType, branch, " +
				"senderBank, receiverBank, msgStatus, txnReference, issueDate, orderingCustomerName, beneficiaryCustomerName, " +
				"applicentBankCode, applicentBankLoc, currency, amount, expDate, lastDateofShipment, amendmentDate, " +
				"noofAmendments, reducedAmt as amountReduced, outstandAmt as amountofOutstanding, relatedRefrence "+
				"from com.logica.ngph.Entity.ReportEntity "+stringQuery);
		
		
		
		for(int i=0;i<DataList.size();i++){
			ReportDto reportDto = new ReportDto();
		 Object[] obj = (Object[]) DataList.get(i);
		 reportDto.setMsgRef((String)obj[0]);
		 reportDto.setMsgType((String) obj[1]);
		 reportDto.setBranch((String)obj[2]);
		 reportDto.setSenderBank((String)obj[3]);
		 reportDto.setReceiverBank((String)obj[4]);
		if(obj[5]!=null){
			 PaymentStatusEnum val = PaymentStatusEnum.findEnumByTag(obj[5].toString());
			 reportDto.setPaymentStatus(val.toString());
		 }
		 reportDto.setTxnReference((String)obj[6]);
		 reportDto.setIssueDate((Timestamp)obj[7]);
		 reportDto.setOrderingCustomer((String)obj[8]);
		 reportDto.setBeneficiaryCustomer((String)obj[9]);
		 if((String)obj[10]!=null)
		 {
			 reportDto.setApplicentBank((String)obj[10]);
		 }
		 if((String)obj[11]!=null)
		 {
			 reportDto.setApplicentBank((String)obj[11]);
		 }
		 reportDto.setMsgCurrency((String)obj[12]);
		 reportDto.setMsgAmount((BigDecimal)obj[13]);
		 reportDto.setExpDate((Timestamp)obj[14]);
		 reportDto.setLastDateofShipment((Timestamp)obj[15]);
		 reportDto.setAmendmentDate((Timestamp)obj[16]);
		 if((BigDecimal)obj[17]!=null)
		 {
			 reportDto.setNoofAmendments((BigDecimal)obj[17]);
		 }
		 if((BigDecimal)obj[18]!=null)
		 {
			 reportDto.setReducedAmt((BigDecimal)obj[18]);
		 }
		 if((BigDecimal)obj[19]!=null)
		 {
			 reportDto.setOutstandAmt((BigDecimal)obj[19]);
		 }
		 if((String)obj[20]!=null)
		 {
			 reportDto.setRelatedRefrence((String)obj[20]);
		 }
		/* 
		 reportDto.setMsgRef((String)obj[11]);
		 reportDto.setMsgType((String) obj[0]);
		 reportDto.setMsgChannel((String)obj[1]);
		 if(obj[2]!=null){
			 PaymentStatusEnum val = PaymentStatusEnum.findEnumByTag(obj[2].toString());
			 reportDto.setPaymentStatus(val.toString());
		 }
	//	 reportDto.setMsgDirection((String)obj[3]);
		 
		 reportDto.setSenderBank((String)obj[3]);
		 reportDto.setReceiverBank((String)obj[4]);
		 reportDto.setMsgCurrency((String)obj[5]);
		 reportDto.setMsgAmount((BigDecimal)obj[6]);
		 
		 reportDto.setMsgValueDate(obj[7].toString().substring(0,10));
		 reportDto.setOrderingCustomer((String)obj[8]);
		 reportDto.setBeneficiaryCustomer((String)obj[9]);
		 reportDto.setSendertoreciverInfo((String)obj[10]);
		// reportDto.setMUR((String)obj[12]);
		// reportDto.setRelatedRefrence((String)obj[13]);
		// reportDto.setDepartment((String)obj[12]);
		 reportDto.setTxnReference((String)obj[12]);
		 reportDto.setBranch((String)obj[13]);
		 if(obj[14]!=null){
		 TransactionTypeEnum transactionTypeEnum = TransactionTypeEnum.findEnumByTag((String)obj[14]);
		 reportDto.setTxnType(transactionTypeEnum.toString());
		}
		 if(obj[0]!=null ||obj[17]!=null){
		String msgType = (String)obj[0];
		String msgSubType = (String)obj[17];

			 reportDto.setMsgType(msgType+msgSubType);
 
		 }
		 reportDto.setBeneficiaryAccount((String)obj[15]);
		 reportDto.setOrderingAccount((String)obj[16]);
		 reportDto.setLastModifiedDate(obj[18].toString().substring(0,10));
		 System.out.println((String)obj[11]);*/
		 
		 returnList.add(reportDto);
		}
		for(int i=0;i<returnList.size();i++)
		{
				System.out.println(returnList.get(i));
		}
		return returnList;
	}

	
	public List<ReceiverDto> getPartyName(String code,String name) throws SQLException {
		List<ReceiverDto> searchList = new ArrayList<ReceiverDto>();
		
		if(code == null){
			code = "";
		}else{
			code = "%"+code+"%";
		}
		if(name == null){
			name = "";
		}else{
			name = "%"+name+"%";
		}
		@SuppressWarnings("rawtypes")
		List receiver = 	getHibernateTemplate().find("select clearingSystemMemberExternalCode,agentName from com.logica.ngph.Entity.Parties as Parties  where agentName like ? And clearingSystemMemberExternalCode like ?",
				name,code);
		createSearchList(searchList, receiver);
		
		return searchList;
	}
	private void createSearchList(List<ReceiverDto> searchList, @SuppressWarnings("rawtypes") List daoList) {
		for (int i = 0; i < daoList.size(); i++) {
			ReceiverDto receiverDto = new ReceiverDto();
            Object[] obj = (Object[]) daoList.get(i);

            String Code = (String) obj[0];
            receiverDto.setReceiverCOde(Code);
            String Name = (String) obj[1];
            receiverDto.setReceiverName(Name);
             
            searchList.add(receiverDto);
        }
	}

	
	public List getStreamID(String streamID) {
		if(streamID == null){
			streamID = "";
		}else{
			streamID = "%"+streamID+"%";
		}
		@SuppressWarnings("rawtypes")
		List receiver = 	getHibernateTemplate().
		find("select distinct SRVC_ORCH from com.logica.ngph.Entity.ServiceOrchestartion as ServiceOrchestartion  where SRVC_ORCH like ?",	streamID);
		
		
		return receiver;
	}

}
