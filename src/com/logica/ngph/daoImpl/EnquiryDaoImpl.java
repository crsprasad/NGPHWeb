package com.logica.ngph.daoImpl;



import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.Date;

import java.sql.SQLException;
import java.sql.Types;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.logica.ngph.Entity.DiscrepanciesReport;
import com.logica.ngph.Entity.Parties;
import com.logica.ngph.Entity.RRNDiscrepancy;
import com.logica.ngph.common.PaymentStatusEnum;
import com.logica.ngph.common.dtos.NgphCanonical;
import com.logica.ngph.common.enums.TransactionTypeEnum;
import com.logica.ngph.dao.EnquiryDao;
import com.logica.ngph.dtos.PartyDTO;
import com.logica.ngph.dtos.PaymentMessage;
import com.logica.ngph.serviceImpl.EnquirySearchServiceImpl;

public class EnquiryDaoImpl extends HibernateDaoSupport implements EnquiryDao {
	static Logger logger = Logger.getLogger(EnquiryDaoImpl.class);
	@SuppressWarnings("unchecked")
	
	public List<String> getEnquiryMessageTypes() throws SQLException {
		logger.info("Inside EnquiryDaoImpl<getEnquiryMessageTypes>");
	
		@SuppressWarnings("rawtypes")
		List msgTypeList = getHibernateTemplate().
		find("select msgSupports.supportMsgType as msgType from com.logica.ngph.Entity.MsgSupport as msgSupports group by (msgSupports.supportMsgType) order by (msgSupports.supportMsgType)");
		logger.info("End EnquiryDaoImpl<getEnquiryMessageTypes>");
		return msgTypeList;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	
	public List<String> getEnquirySubMessageTypes() throws SQLException {
		logger.info("Inside EnquiryDaoImpl<getEnquirySubMessageTypes>");
		List subMessageType = getHibernateTemplate().
		find("select msgSupports.supportSubMsgType as msgType from com.logica.ngph.Entity.MsgSupport as msgSupports group by (msgSupports.supportSubMsgType) order by (msgSupports.supportSubMsgType)");
		logger.info("End EnquiryDaoImpl<getEnquirySubMessageTypes>");
		return subMessageType;
	}

	
	public List<String> getEnquiryChannel() throws SQLException {
		logger.info("Inside EnquiryDaoImpl<getEnquiryChannel>");
		@SuppressWarnings("rawtypes")
		List channel= getHibernateTemplate().
		 find("select EI_FORMAT as channel from com.logica.ngph.Entity.EI as channel where channel.EI_FORMAT is not null group by (channel.EI_FORMAT)");
		logger.info("End EnquiryDaoImpl<getEnquiryChannel>");
		return channel;
	}
	
	public List<String> getEnquiryCurrency() throws SQLException {
		logger.info("Inside EnquiryDaoImpl<getEnquiryCurrency>");
		@SuppressWarnings("rawtypes")
		List Currency= getHibernateTemplate().
		 find("select currency.currencyCode as Currency from com.logica.ngph.Entity.CurrencyMaster as currency group by (currency.currencyCode) order by (currency.currencyCode)");
		logger.info("End EnquiryDaoImpl<getEnquiryCurrency>");
		return Currency;
	}
	

	
	public List<PaymentMessage> getSearchResult(String stringQuery,String direction,String tableToUse) {
		logger.info("Inside EnquiryDaoImpl<getSearchResult>");
		
		List enquiryList=null;
		String tableName = "";
		if(tableToUse.equals("FindHistory"))
		{
			tableName  = "ReportMessageHist";
		}
		else
		{
			tableName="ReportEntity";
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

	
	public String getRawMessage(String msgRef) throws SQLException {
		logger.info("Inside EnquiryDaoImpl<getRawMessage>");
		Clob list=null;
		String raw_Msg = null;
		try{
			list = (Clob) getHibernateTemplate().find("select rawMsg from RawMessage where msgRef=?",msgRef).get(0);
		raw_Msg = list.getSubString(1, (int) list.length());
		}catch (Exception e) {
			
		}
		logger.info("End EnquiryDaoImpl<getRawMessage>");
		return raw_Msg;
	}

	
	public DiscrepanciesReport getDiscrepanciesReport(String date) {
		try{
			logger.info("Inside EnquiryDaoImpl<getDiscrepanciesReport>");
			List diList = getHibernateTemplate().find("select ob_Npci_Count,ob_Qng_Count,ob_Diff_Qng,ob_Diff_Npci,ib_Npci_Count,ib_Qng_Count,ii_Diff_Qng,ib_Diff_Npci from DiscrepanciesReport where TRN_DATE like (to_date('"+date+"' , 'dd/MM/yyyy')) ");
			if(diList!=null && !diList.isEmpty()){
			Object[] objects  = (Object[])diList.get(0);
			DiscrepanciesReport discrepanciesReport = new DiscrepanciesReport();
			discrepanciesReport.setOb_Npci_Count((Integer)objects[0]);
			discrepanciesReport.setOb_Qng_Count((Integer)objects[1]);
			discrepanciesReport.setOb_Diff_Qng((Integer)objects[2]);
			discrepanciesReport.setOb_Diff_Npci((Integer)objects[3]);
			discrepanciesReport.setIb_Npci_Count((Integer)objects[4]);
			discrepanciesReport.setIb_Qng_Count((Integer)objects[5]);
			discrepanciesReport.setIi_Diff_Qng((Integer)objects[6]);
			discrepanciesReport.setIb_Diff_Npci((Integer)objects[7]);
			logger.info("End EnquiryDaoImpl<getDiscrepanciesReport>");
			return discrepanciesReport;
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	
	public List<RRNDiscrepancy> getRRPOutwardList(String direction,String date) {
		logger.info("Inside EnquiryDaoImpl<getRRPOutwardList>");
		Map<String,String> map = new HashMap<String,String>(); 
		List tranref =  getHibernateTemplate().find("select txn.msgRef,txn.txnReference from NgphCanonical " +
				"txn,RRNDiscrepancy dis  where dis.TRN_REF =txn.msgRef and dis.direction='"+direction+"'");
		if(tranref!=null && !tranref.isEmpty())
		{
			for(int i = 0;i<tranref.size();i++){
			Object[] objects = (Object[]) tranref.get(i);
			map.put(objects[0].toString(), objects[1].toString());
			
			}
		}
		
		List<RRNDiscrepancy> discrepancies = new ArrayList<RRNDiscrepancy>();
 		
		List list = getHibernateTemplate().find("select RRN,TRN_REF,trunc(TRN_DATE) from RRNDiscrepancy where direction = ? and TRN_DATE like (to_date('"+date+"' , 'dd/MM/yyyy')) ",direction);
		if(list!=null && !list.isEmpty()){
			for(int i = 0;i<list.size();i++)
			{	
				Object[] objects  = (Object[])list.get(i);
				RRNDiscrepancy discrepancy = new RRNDiscrepancy();
				discrepancy.setRRN((String)objects[0]);
				if(objects[2]!=null)
				discrepancy.setTRN_DATE(((String) objects[2]).substring(0,10));
				discrepancy.setTRN_REF(map.get((String)objects[1]));
				discrepancies.add(discrepancy);
			}
		}
		logger.info("End EnquiryDaoImpl<getRRPOutwardList>");
		return discrepancies;
	}

	public String generateDiscrepanciesReport(Date date){
		
		String str1 ="";
		try {
			SessionFactory fact = getHibernateTemplate().getSessionFactory();
			Session sess = fact.openSession();
			Connection con = sess.connection();			
			String str = "call DISCREPANCYREPORT(?,?)";
			CallableStatement st = con.prepareCall(str);
			st.setDate(1, date);
			st.registerOutParameter(2, Types.VARCHAR);

			st.executeUpdate();
			str1 = st.getString(2);
				sess.flush();
				sess.close();
				fact.close();
				con.close();
		} catch (HibernateException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}	

		return str1;		
		
	}

	public List<String> getEnquiryStateName() throws SQLException {
		List stateNameList = getHibernateTemplate().
		find("select DISTINCT IFSC.stateName as stateName from com.logica.ngph.Entity.Parties as IFSC group by stateName order by stateName");
		return stateNameList;
	}

	public List<String> getEnquiryDistrictName(String stateName) throws SQLException {
		List districtNameList = getHibernateTemplate().
		find("select DISTINCT distName as distName from com.logica.ngph.Entity.Parties where stateName = ? group by distName order by distName",stateName);
		return districtNameList;
	}

	public List<String> getEnquiryCityName(String stateName, String districtName) throws SQLException {
		List cityNameList = getHibernateTemplate().
		find("select DISTINCT cityName as cityName from com.logica.ngph.Entity.Parties where stateName = ? and distName = ? group by cityName order by cityName",stateName,districtName);
		return cityNameList;
	}

	public List<PartyDTO> getIFSCDetailsData(String bankName, String stateName,String districName, String cityName, String ifscCode, String branchName) throws SQLException {
		List <PartyDTO> ifscDetailsList = new ArrayList<PartyDTO>();
		try 
		{
			
			String queryString ="";
			List list = null ;
						
			if(bankName==null && StringUtils.isEmpty(bankName))
			{
				bankName="%";
			}
			else
			{
				bankName = "%"+bankName+"%";
			}
			if(stateName=="0" || stateName.equalsIgnoreCase("0"))
			{
				stateName="%";
			}
			else
			{
				stateName = "%"+stateName+"%";
			}
			if(districName=="0" || districName.equalsIgnoreCase("0"))
			{
				districName="%";
			}
			else
			{
				districName = "%"+districName+"%";
			}
			if(cityName=="0" || cityName.equalsIgnoreCase("0"))
			{
				cityName="%";
			}
			else
			{
				cityName = "%"+cityName+"%";
			}
			if(ifscCode==null && StringUtils.isEmpty(ifscCode))
			{
				ifscCode="%";
			}
			else
			{
				ifscCode = "%"+ifscCode+"%";
			}
			
			if(branchName==null && StringUtils.isEmpty(branchName))
			{
				branchName="%";
			}	
			else
			{
				branchName = "%"+branchName+"%";
			}
			
			
			
			queryString = "select agentName, stateName, distName, cityName, "+ //4
							"partyBranchName, clearingSystemMemberId, partyAddress, ifscType, micrCode " + //5
							"from com.logica.ngph.Entity.Parties where agentName like '"+bankName+"' and stateName like '"+stateName+"' and distName like '"+districName+"' and "+ 
							"cityName like '"+cityName+"' and partyBranchName like '"+branchName+"' and clearingSystemMemberId like '"+ifscCode+"'";
			
			list = getHibernateTemplate().find(queryString);
			for(int i=0;i<list.size();i++)
			{
				PartyDTO partyDTO= new PartyDTO();
				 Object[] obj = (Object[]) list.get(i);
				partyDTO.setPartyName((String) obj[0]);
				partyDTO.setStateName((String) obj[1]);
				partyDTO.setDistricName((String) obj[2]);
				partyDTO.setCityName((String) obj[3]);
				partyDTO.setBranchName((String) obj[4]);
				partyDTO.setIfscCode((String) obj[5]);
				partyDTO.setPartyAddress((String) obj[6]);
				partyDTO.setIfscType((String) obj[7]);
				partyDTO.setMicrCode((Integer) obj[8]);
				ifscDetailsList.add(partyDTO);
				
			}
				return ifscDetailsList;
			} 
		catch (HibernateException e) {
			e.printStackTrace();
		}
		return null;
		
	}

	public List<PartyDTO> getBranchNameData(String branchName,String stateName,String districtName, String cityName, String bankName, String ifscCode) {
		List<PartyDTO> branckNameList = new ArrayList<PartyDTO>();
		if(bankName==null && StringUtils.isEmpty(bankName))
		{
			bankName="%";
		}
		else
		{
			bankName = "%"+bankName+"%";
		}
		if(stateName=="0" || stateName.equalsIgnoreCase("0"))
		{
			stateName="%";
		}
		else
		{
			stateName = "%"+stateName+"%";
		}
		if(districtName==null)
		{
			districtName="%";
		}
		else
		{
			districtName = "%"+districtName+"%";
		}
		if(cityName==null)
		{
			cityName="%";
		}
		else
		{
			cityName = "%"+cityName+"%";
		}
		if(ifscCode==null && StringUtils.isEmpty(ifscCode))
		{
			ifscCode="%";
		}
		else
		{
			ifscCode = "%"+ifscCode+"%";
		}
		
		if(branchName==null && StringUtils.isEmpty(branchName))
		{
			branchName="%";
		}	
		else
		{
			branchName = "%"+branchName+"%";
		}
		List list = getHibernateTemplate().find("select distinct partyBranchName from Parties where partyBranchName like ? and stateName like ? and distName like ? and cityName like ? and agentName like ? and clearingSystemMemberId like ?",branchName,stateName,districtName,cityName,bankName,ifscCode);
		for(int i = 0 ;i<list.size();i++)
		{
			PartyDTO brancheNames=  new PartyDTO();
			brancheNames.setBranchName((String)list.get(i));
			 branckNameList.add(brancheNames);
		}
		return branckNameList;
	}

	public List<PartyDTO> getIFSCCodeListData(String ifscCode,String stateName,String districtName, String cityName, String bankName, String branchName) {
		List<PartyDTO> ifscCodeList = new ArrayList<PartyDTO>();
		if(bankName==null && StringUtils.isEmpty(bankName))
		{
			bankName="%";
		}
		else
		{
			bankName = "%"+bankName+"%";
		}
		if(stateName=="0" || stateName.equalsIgnoreCase("0"))
		{
			stateName="%";
		}
		else
		{
			stateName = "%"+stateName+"%";
		}
		if(districtName==null)
		{
			districtName="%";
		}
		else
		{
			districtName = "%"+districtName+"%";
		}
		if(cityName==null)
		{
			cityName="%";
		}
		else
		{
			cityName = "%"+cityName+"%";
		}
		if(ifscCode==null && StringUtils.isEmpty(ifscCode))
		{
			ifscCode="%";
		}
		else
		{
			ifscCode = "%"+ifscCode+"%";
		}
		
		if(branchName==null && StringUtils.isEmpty(branchName))
		{
			branchName="%";
		}	
		else
		{
			branchName = "%"+branchName+"%";
		}
		List list = getHibernateTemplate().find("select distinct clearingSystemMemberId, agentName, partyBranchName, partyAddress from Parties where clearingSystemMemberId like ? and stateName like ? and distName like ? and cityName like ? and agentName like ? and partyBranchName like ? order by clearingSystemMemberId ",ifscCode,stateName,districtName,cityName,bankName,branchName);
		for(int i = 0 ;i<list.size();i++)
		{
			/*PartyDTO IfscCode=  new PartyDTO();
			IfscCode.setIfscCode((String)list.get(i));
			ifscCodeList.add(IfscCode);*/
			
			PartyDTO partyDTO= new PartyDTO();
			
			Object[] obj = (Object[]) list.get(i);
			partyDTO.setIfscCode((String)obj[0]);
			partyDTO.setPartyBankName((String)obj[1]);
			partyDTO.setBranchName((String)obj[2]);
			partyDTO.setPartyAddress((String)obj[3]);
			ifscCodeList.add(partyDTO);
		}
		return ifscCodeList;
	}

	public List<PartyDTO> getIFSCCodeList(String ifscCode) throws SQLException {
        List<PartyDTO> ifscCodeSearchList = new ArrayList<PartyDTO>();
        if(ifscCode==null && StringUtils.isEmpty(ifscCode))
        {
                        ifscCode="%";
        }
        else
        {
                        ifscCode = "%"+ifscCode+"%";
        }
                        
        List list = getHibernateTemplate().find("select distinct clearingSystemMemberId,agentName,partyBranchName,partyAddress from Parties where clearingSystemMemberId like ? ",ifscCode);
        for(int i = 0 ;i<list.size();i++)
        {
                        /*PartyDTO IfscCode=  new PartyDTO();
                        IfscCode.setIfscCode((String)list.get(i));
                        ifscCodeSearchList.add(IfscCode);*/
                        PartyDTO partyDTO= new PartyDTO();
                        
                        Object[] obj = (Object[]) list.get(i);
                        partyDTO.setIfscCode((String)obj[0]);
                        partyDTO.setPartyName((String)obj[1]);
                        partyDTO.setBranchName((String)obj[2]);
                        partyDTO.setPartyAddress((String)obj[3]);
                        ifscCodeSearchList.add(partyDTO);
        }              
        return ifscCodeSearchList;
}


	public NgphCanonical fetchMsgData(String msgRef) throws SQLException {
		String queryString = null;
		NgphCanonical ngphCanonical = new NgphCanonical();
		List list = null ;
		try
		{
			queryString = "select msgRef, msgHost, srcMsgType, srcMsgSubType  from NgphCanonical where msgStatus = 13 and msgRef=?";
	
	list = getHibernateTemplate().find(queryString,msgRef);
	for(int i=0;i<list.size();i++)
	{
		
		Object[] obj = (Object[]) list.get(i);
		ngphCanonical.setMsgRef((String) obj[0]);
		ngphCanonical.setMsgHost((String) obj[1]);
		ngphCanonical.setSrcMsgType((String) obj[2]);
		ngphCanonical.setSrcMsgSubType((String) obj[3]);
		
	
	
	}
	return ngphCanonical;
	} 
	catch (HibernateException e) {
	e.printStackTrace();
	}
	return null;
	}

	
}
