package com.logica.ngph.daoImpl;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.event.SaveOrUpdateEvent;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.logica.ngph.Entity.MsgPolled;
import com.logica.ngph.dao.ReturnIMPSTransactionDao;

import com.logica.ngph.dtos.ReturnIMPSTransactionDto;

public class ReturnIMPSTransactionDaoImpl extends HibernateDaoSupport implements ReturnIMPSTransactionDao{

	
	public List<ReturnIMPSTransactionDto> getReturnDetails(String date)
			throws SQLException {
		List <ReturnIMPSTransactionDto> resultList = new ArrayList<ReturnIMPSTransactionDto>();
		List list = 	getHibernateTemplate().find("select rpt.msgAmount,concat(rpt.orderingCustomerName,' ',rpt.orderingCustomerAddress,' ',rpt.orderingCustomerId,' ',rpt.orderingCustomerCtry ,' ',rpt.orderingCustAccount,' ',rpt.orderingInstitution,' ',rpt.orderingInstitutionAcct) as orderingCustomer," +
				"concat(rpt.beneficiaryCustomerName,' ',rpt.beneficiaryCustomerAddress,' ',rpt.beneficiaryCustomerID,' ',rpt.beneficiaryCustomerCtry,' ',rpt.beneficiaryCustAcct,' ',rpt.ultimateCreditorName,' ',rpt.ultimateCreditorAddress,' ',rpt.ultimateCreditorID) as beneficiaryCustomer,concat(rpt.prevInstructingBank,' ',rpt.instructionsForCrdtrAgtText,' ',rpt.instructionsForNextAgtCode,' ',rpt.instructionsForNextAgtText) as narration, rpt.msgRef,rpt.txnReference " +
				"from NgphCanonical as rpt ,IMPSTransaction as transaction where rpt.msgRef = transaction.impsMsgRef and rpt.msgDirection ='I' and trunc(rpt.msgValueDate) like (to_date('"+date+"' , 'dd/MM/yyyy'))");
		
		
		for(int i=0;i<list.size();i++){
			ReturnIMPSTransactionDto impsTransaction = new ReturnIMPSTransactionDto();
			 Object[] obj = (Object[]) list.get(i);
			 impsTransaction.setBenificiaryCustomer((String) obj[2]);
			 impsTransaction.setOrderingCustomer((String) obj[1]);
			 impsTransaction.setAmount((BigDecimal) obj[0]);
			 impsTransaction.setNarration((String) obj[3]);
			 impsTransaction.setMsgRef((String) obj[4]);
			 impsTransaction.setTransRef((String) obj[5]);
			 
			 resultList.add(impsTransaction);
			 
		}
		return resultList;
	}

	
	public String savePolled(MsgPolled msgPolled) throws SQLException {
				getHibernateTemplate().saveOrUpdate(msgPolled);
		return "success";
	}

}
