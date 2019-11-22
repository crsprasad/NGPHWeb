/**
 * 
 */
package com.logica.ngph.daoImpl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.logica.ngph.common.PaymentStatusEnum;
import com.logica.ngph.common.enums.TransactionTypeEnum;
import com.logica.ngph.dao.EnquiryHistDao;
import com.logica.ngph.dtos.PaymentMessage;

/**
 * @author chakkar
 *
 */
public class EnquiryHistDaoImpl extends HibernateDaoSupport implements EnquiryHistDao {
	
	static Logger logger = Logger.getLogger(EnquiryDaoImpl.class);
	@SuppressWarnings("unchecked")


	@Override
	public List<PaymentMessage> getSearchHistResult(String stringQuery, String direction, String tableToUse) {
logger.info("Inside EnquiryDaoImpl<getSearchResult>");
		
		List enquiryList=null;
		String tableName = "";
		if(tableToUse.equals("FindHistory"))
		{
			tableName  = "ReportHistEntity";
		}
		else
		{
			tableName="ReportHistEntity";
		}
		String queryString ="";
		List <PaymentMessage> enquirySearchList = new ArrayList<PaymentMessage>();
		if(direction.equals("I")){
			queryString = "select concat(msgType ,' ',subMsgType) as msgType,channel,msgStatus,direction," +
			"txnReference,senderBank,receiverBank,currency,amount,msgValueDate ," +
			"concat(orderingCustomerName,' ',orderingCustomerAddress,' ',orderingCustomerId,' ',orderingCustomerCtry ,' ',orderingCustAccount,' ',orderingInstitution,' ',orderingInstitutionAcct) as orderingCustomer,"+
			"concat(beneficiaryCustomerName,' ',beneficiaryCustomerAddress,' ',beneficiaryCustomerID,' ',beneficiaryCustomerCtry,' ',beneficiaryCustAcct,' ',ultimateCreditorName,' ',ultimateCreditorAddress,' ',ultimateCreditorID)as beneficiaryCustomer ," +
			"concat(prevInstructingBank,' ',prevInstructingAgentAcct,' ',instructionsForNextAgtCode) as narration ,msgRef,txnType,lastModTime,beneficiaryCustAcct,comments from "+tableName+" "+stringQuery+" order by lastModTime desc";
		enquiryList= getHibernateTemplate().find(queryString);
		}
		else
		{
			queryString = "select concat(dstMsgType ,' ',dstMsgSubType) as msgType,dstChannelType,msgStatus,direction," +
			"txnReference,senderBank,receiverBank,currency,amount,msgValueDate ," +
			"concat(orderingCustomerName,' ',orderingCustomerAddress,' ',orderingCustomerId,' ',orderingCustomerCtry ,' ',orderingCustAccount,' ',orderingInstitution,' ',orderingInstitutionAcct) as orderingCustomer,"+
			"concat(beneficiaryCustomerName,' ',beneficiaryCustomerAddress,' ',beneficiaryCustomerID,' ',beneficiaryCustomerCtry,' ',beneficiaryCustAcct,' ',ultimateCreditorName,' ',ultimateCreditorAddress,' ',ultimateCreditorID)as beneficiaryCustomer ," +
			"concat(prevInstructingBank,' ',prevInstructingAgentAcct,' ',instructionsForNextAgtCode) as narration ,msgRef,txnType,lastModTime,beneficiaryCustAcct,comments from "+tableName+" " +stringQuery+" order by lastModTime desc";
			enquiryList= getHibernateTemplate().find(queryString);
			
		}
		
		for(int i=0;i<enquiryList.size();i++){
			PaymentMessage paymentMessage = new PaymentMessage();
			
			
			 Object[] obj = (Object[]) enquiryList.get(i);
			 
			 paymentMessage.setMsgType((String)obj[0]);
			 
			 paymentMessage.setMsgChannel((String)obj[1]);
			 if(obj[2] != null && !obj[2].toString().equals(null) ){
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
			 if(obj[9] != null && !obj[9].toString().equals(null))
			 paymentMessage.setMsgValueDate(obj[9].toString().substring(0,10));
			 paymentMessage.setOrderingCustomer((String)obj[10]);
			 paymentMessage.setBeneficiaryCustomer((String)obj[11]);
			 paymentMessage.setNarration((String)obj[12]);
			 paymentMessage.setMsgRef((String)obj[13]);
			 if(obj[14]!=null){
				 TransactionTypeEnum transactionTypeEnum = TransactionTypeEnum.findEnumByTag((String)obj[14]);
				 paymentMessage.setTxnType(transactionTypeEnum.toString());
				}
			 if(obj[15]!=null)
			 paymentMessage.setLastModTime(obj[15].toString());
			 paymentMessage.setBeneficiaryAccountNo((String) obj[16]);
			 paymentMessage.setComments((String) obj[17]);
			 enquirySearchList.add(paymentMessage);
		}
		logger.info("End EnquiryDaoImpl<getSearchResult>");
		return enquirySearchList;
	}

}
