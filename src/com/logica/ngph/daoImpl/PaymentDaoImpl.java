package com.logica.ngph.daoImpl;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.logica.ngph.Entity.ModifiedMessage;
import com.logica.ngph.Entity.MsgPolled;
import com.logica.ngph.Entity.NgphCanonical;
import com.logica.ngph.Entity.SecUsers;
import com.logica.ngph.common.ConstantUtil;
import com.logica.ngph.common.PaymentStatusEnum;
import com.logica.ngph.common.enums.TransactionTypeEnum;
import com.logica.ngph.dao.PaymentDao;
import com.logica.ngph.dtos.PaymentMessage;
import com.logica.ngph.utils.NgphCanonicalUtil;





public class PaymentDaoImpl extends HibernateDaoSupport implements PaymentDao{
	static Logger logger = Logger.getLogger(PaymentDaoImpl.class);
	
	
	
	
	
	/**
	* This method is not using now
	* @return Map<String, List<String>>
	*/
	@SuppressWarnings("unchecked")
	public Map<String, List<String>> getMsgSupport(){
		//getHibernateTemplate().
		
		Map<String, List<String>> msgSupport =  new HashMap<String, List<String>>();
		
		
		
		List paymentList = 	getHibernateTemplate().
		find("select concat(orderingCustAccount,orderingInstitutionAcct,orderingInstitution) as orderingCustomer from NgphCanonical");
		
		
		
		for(int i=0;i<paymentList.size();i++){
			 
			 System.out.println(paymentList.get(0));
			 
		}
		
		
		List test = 	getHibernateTemplate().
		find("select concat(orderingCustAccount,orderingInstitutionAcct) as orderingCustomer from NgphCanonical");
		for(int i=0;i<paymentList.size();i++){
			
			
			System.out.println(paymentList.get(0));
			
		}
		
		/*List<String> msgList = new ArrayList<String>();
		List<String> msgSubList = new ArrayList<String>();
		List<String> msgSupportSourceList = new ArrayList<String>();
		List msgTypeList = getHibernateTemplate().
		find("select distinct msgSupports.supportMsgType,msgSupports.supportSubMsgType,msgSupports.supportSource " +
				"from com.logica.ngph.Entity.MsgSupport as msgSupports " +
				"where msgSupports.supportMsgDirection = ?","O");
		
		
		for (int i = 0; i < msgTypeList.size(); i++) {
        	//chDTO searchDTO = new SearchDTO();
            Object[] obj = (Object[]) msgTypeList.get(i);
           
            String msgType = (String) obj[0];
            msgList.add(msgType);
            //searchDTO.setCode(deptCode);
            String msgSubType = (String) obj[1];
            msgSubList.add(msgSubType);
            //searchDTO.setName(deptName);
            String supportSource = (String) obj[2];
            msgSupportSourceList.add(supportSource);
           // searchList.add(searchDTO);
        }
		msgSupport.put("MSGTYPE", msgList);
		msgSupport.put("MSGSUBTYPE", msgSubList);
		msgSupport.put("MSGSOURCE", msgSupportSourceList);*/
		return msgSupport;
	}
	

	/**
	* This method is used to save the Payment Entry Screen Data
	* @return void
	*/
	
	public void savePayment(NgphCanonical ngphCanonical){
		
			getHibernateTemplate().saveOrUpdate(ngphCanonical);
	
	}

	/**
	* This method is used to get the payment Status data from TA_MESSAGES_TX
	* @return  List<PaymentMessage>
	*/
	@SuppressWarnings("rawtypes")
	
	public List<PaymentMessage> getPaymentMessage(String paymentStatus,String msgBranch,String msgDept,String msgDirection,String filterQuery) throws SQLException {
		
		List<PaymentMessage> paymentMessageList = new ArrayList<PaymentMessage>();
		Session session = getHibernateTemplate().getSessionFactory().openSession();
		List paymentList=null; 
		if(msgDirection.equals("I")){
			if(paymentStatus.trim().equals("47") || paymentStatus.trim().equals("48") || paymentStatus.trim().equals("2")){
				String paymentQueryI = "select concat(msgType ,' ',subMsgType) as msgType,channel,msgRef,msgStatus,direction," +
				"txnReference,senderBank,receiverBank,currency,amount,msgValueDate ," +
				"concat(orderingCustomerName,' ',orderingCustomerAddress,' ',orderingCustomerId,' ',orderingCustomerCtry ,' ',orderingCustAccount,' ',orderingInstitution,' ',orderingInstitutionAcct) as orderingCustomer,"+
				"concat(beneficiaryCustomerName,' ',beneficiaryCustomerAddress,' ',beneficiaryCustomerID,' ',beneficiaryCustomerCtry,' ',beneficiaryCustAcct,' ',ultimateCreditorName,' ',ultimateCreditorAddress,' ',ultimateCreditorID)as beneficiaryCustomer ," +
				"concat(prevInstructingBank,' ',prevInstructingAgentAcct,' ',instructionsForNextAgtCode) as narration "+
				",errorCode as errorCode,txnType,lastModTime,beneficiaryCustAcct,concat(dstMsgType ,' ',dstMsgSubType),dstChannelType "+
				"from ReportEntity where "+filterQuery+" msgStatus=:status and direction=:direction order by lastModTime desc";
				Query query =	session.createQuery(paymentQueryI);
				query.setString("status", paymentStatus);
				query.setString("direction", msgDirection);
				paymentList = query.list();
			}else{
				String paymentQuery ="select concat(msgType ,' ',subMsgType) as msgType,channel,msgRef,msgStatus,direction," +
				"txnReference,senderBank,receiverBank,currency,amount,msgValueDate ," +
				"concat(orderingCustomerName,' ',orderingCustomerAddress,' ',orderingCustomerId,' ',orderingCustomerCtry ,' ',orderingCustAccount,' ',orderingInstitution,' ',orderingInstitutionAcct) as orderingCustomer,"+
				"concat(beneficiaryCustomerName,' ',beneficiaryCustomerAddress,' ',beneficiaryCustomerID,' ',beneficiaryCustomerCtry,' ',beneficiaryCustAcct,' ',ultimateCreditorName,' ',ultimateCreditorAddress,' ',ultimateCreditorID)as beneficiaryCustomer ," +
				"concat(prevInstructingBank,' ',prevInstructingAgentAcct,' ',instructionsForNextAgtCode) as narration "+
				",errorCode as errorCode,txnType,lastModTime,beneficiaryCustAcct,concat(dstMsgType ,' ',dstMsgSubType),dstChannelType ";
				if(msgBranch.equals(ConstantUtil.ALL) || paymentStatus=="2"){
					paymentQuery = paymentQuery+"from ReportEntity where "+filterQuery+" trim(' ' from msgStatus)=:status and direction=:direction order by lastModTime desc";
				}else{
					paymentQuery = paymentQuery+"from ReportEntity where "+filterQuery+" trim(' ' from msgStatus)=:status and branch=:branch and department=:dept and direction=:direction order by lastModTime desc";
				}
			Query query =	session.createQuery(paymentQuery);
			query.setString("status", paymentStatus.trim());
			query.setString("direction", msgDirection);
			if(!msgBranch.equals(ConstantUtil.ALL)|| paymentStatus=="2"){
				query.setString("branch", msgBranch);
				query.setString("dept", msgDept);
			}
			paymentList = query.list();
			}
		}
		else
		{
			if(paymentStatus.trim().equals("2")){
				String paymentQueryO ="select concat(dstMsgType ,' ',dstMsgSubType) as msgType,dstChannelType,msgRef,msgStatus,direction," +
				"txnReference,senderBank,receiverBank,currency,amount,msgValueDate ," +
				"concat(orderingCustomerName,' ',orderingCustomerAddress,' ',orderingCustomerId,' ',orderingCustomerCtry ,' ',orderingCustAccount,' ',orderingInstitution,' ',orderingInstitutionAcct) as orderingCustomer,"+
				"concat(beneficiaryCustomerName,' ',beneficiaryCustomerAddress,' ',beneficiaryCustomerID,' ',beneficiaryCustomerCtry,' ',beneficiaryCustAcct,' ',ultimateCreditorName,' ',ultimateCreditorAddress,' ',ultimateCreditorID)as beneficiaryCustomer ," +
				"concat(prevInstructingBank,' ',prevInstructingAgentAcct,' ',instructionsForNextAgtCode) as narration "+
				",errorCode as errorCode,txnType,lastModTime,beneficiaryCustAcct,concat(msgType ,' ',subMsgType),channel ";
				
					paymentQueryO = paymentQueryO +"from ReportEntity where "+filterQuery+" trim(' ' from msgStatus)=:status  and trim(' ' from direction)=:direction order by lastModTime desc";
				
				Query query =	session.createQuery(paymentQueryO);
				query.setString("status", paymentStatus.trim());
				
				query.setString("direction", msgDirection.trim());
				
				paymentList = query.list();
				logger.info("In side if for Status 2");
				
			}else{
			String paymentQueryO ="select concat(dstMsgType ,' ',dstMsgSubType) as msgType,dstChannelType,msgRef,msgStatus,direction," +
			"txnReference,senderBank,receiverBank,currency,amount,msgValueDate ," +
			"concat(orderingCustomerName,' ',orderingCustomerAddress,' ',orderingCustomerId,' ',orderingCustomerCtry ,' ',orderingCustAccount,' ',orderingInstitution,' ',orderingInstitutionAcct) as orderingCustomer,"+
			"concat(beneficiaryCustomerName,' ',beneficiaryCustomerAddress,' ',beneficiaryCustomerID,' ',beneficiaryCustomerCtry,' ',beneficiaryCustAcct,' ',ultimateCreditorName,' ',ultimateCreditorAddress,' ',ultimateCreditorID)as beneficiaryCustomer ," +
			"concat(prevInstructingBank,' ',prevInstructingAgentAcct,' ',instructionsForNextAgtCode) as narration "+
			",errorCode as errorCode,txnType,lastModTime,beneficiaryCustAcct,concat(msgType ,' ',subMsgType),channel ";
			if(msgBranch.equals(ConstantUtil.ALL)){
				paymentQueryO = paymentQueryO +"from ReportEntity where "+filterQuery+" trim(' ' from msgStatus)=:status  and trim(' ' from direction)=:direction order by lastModTime desc";
			}else{
				paymentQueryO = paymentQueryO +"from ReportEntity where "+filterQuery+" trim(' ' from msgStatus)=:status and branch=:branch and department=:dept and trim(' ' from direction)=:direction order by lastModTime desc";
			}
			Query query =	session.createQuery(paymentQueryO);
			query.setString("status", paymentStatus.trim());
			query.setString("direction", msgDirection.trim());
			
			if(!msgBranch.equals(ConstantUtil.ALL)){
				query.setString("branch", msgBranch);
				query.setString("dept", msgDept);
				
			}
			paymentList = query.list();
		}
		}
		session.close();
		List errorDescList1 = getHibernateTemplate().find("select errorCode,errorMsgDesc from ErrorCode  where errorChannel is null ");
		Map<String,String> map = new HashMap<String, String>();
		for(int i = 0 ;i<errorDescList1.size();i++)
		{
			Object[] objects = (Object[]) errorDescList1.get(i);
			map.put(objects[0]+"", objects[1]+"");
		}
	for(int i=0;i<paymentList.size();i++){
		try{
			PaymentMessage paymentMessage = new PaymentMessage();
			
			 Object[] obj = (Object[]) paymentList.get(i);
			 
			 paymentMessage.setMsgType((String)obj[0]);
			 paymentMessage.setMsgChannel((String)obj[1]);
			 paymentMessage.setMsgRef((String)obj[2]);
			 PaymentStatusEnum val = PaymentStatusEnum.findEnumByTag((String)obj[3].toString());
			 paymentMessage.setPaymentStatus(val.toString());
			 
			 paymentMessage.setMsgDirection((String)obj[4]);
			 paymentMessage.setTxnReference((String)obj[5]);
			 paymentMessage.setSenderBank((String)obj[6]);
			 paymentMessage.setReceiverBank((String)obj[7]);
			 paymentMessage.setMsgCurrency((String)obj[8]);
			 paymentMessage.setMsgAmount((BigDecimal)obj[9]);
			 paymentMessage.setMsgValueDate(obj[10].toString().substring(0,10));
			
			 paymentMessage.setOrderingCustomer((String)obj[11]);
			 paymentMessage.setBeneficiaryCustomer((String)obj[12]);
			 paymentMessage.setNarration((String)obj[13]);
			 if(obj[14]!=null)
			 {
				 String[] errorMsg  = ((String)obj[14]).split(";");
				 String errorDesc = "";
				// List<String> errorDesc = getHibernateTemplate().find("select errorMsgDesc from ErrorCode where errorCode =? and errorChannel is null",(String)obj[14]);
				 
				 if(errorMsg!=null  && errorMsg.length>0)
				 {
					 for(int i1 = 0 ;i1<errorMsg.length;i1++)
					 {
						// List<String> errorDescList = getHibernateTemplate().find("select errorMsgDesc from ErrorCode where errorCode =? and errorChannel is null",errorMsg[i1]);
						 //if(errorDescList!=null && !errorDescList.isEmpty()&& errorDescList.size()>0){
						 
							 errorDesc  = errorDesc+(i1+1)+") "+map.get(errorMsg[i1])+"\r\n";
						 
					 }
					 
				 }
				paymentMessage.setErrorCode(errorDesc);
			 }
			 //paymentMessage.setErrorCode((String)obj[14]);
			 if(obj[15]!=null){
				 TransactionTypeEnum transactionTypeEnum = TransactionTypeEnum.findEnumByTag((String)obj[15]);
				 paymentMessage.setTxnType(transactionTypeEnum.toString());
				}
			 if(obj[16]!=null)
			 paymentMessage.setLastModTime(obj[16].toString());
			 paymentMessage.setBeneficiaryAccountNo((String) obj[17]);
			 paymentMessage.setSrcMSGType((String) obj[18]);
			 paymentMessage.setSrcChannel((String) obj[19]);
			 paymentMessageList.add(paymentMessage);
			
		}catch(Exception exception){
			logger.debug(exception);
		}
		
	}
	
	return paymentMessageList;
	}
	
	/**
	* This method is used to get  ngphcanonical data from TA_MESSAGES_TX table for manual repair
	* @param String msgRef
	* @return  com.logica.ngph.common.dtos.NgphCanonical canonicalDto
	*/
	@Transactional
	
	public com.logica.ngph.common.dtos.NgphCanonical getPayment(String msgRef)
			throws SQLException {
	@SuppressWarnings("unchecked")
	List<NgphCanonical> canonicalEntityList = 	getHibernateTemplate().find("from NgphCanonical where msgRef=?", msgRef);
	NgphCanonical ngphCanonical = null;
	
	if(canonicalEntityList.size()>0){
		ngphCanonical = canonicalEntityList.get(0);
	}
	return NgphCanonicalUtil.ngphCanonicalEntityToDto(ngphCanonical);
	}
	
	/*private com.logica.ngph.common.dtos.NgphCanonical ngphCanonicalEntityToDto(NgphCanonical ngphCanonical){
		
		com.logica.ngph.common.dtos.NgphCanonical canonicalDto = new com.logica.ngph.common.dtos.NgphCanonical();
		
		canonicalDto.setMsgRef(ngphCanonical.getMsgRef());
		
		canonicalDto.setAccountWithInstitution(ngphCanonical.getAccountWithInstitution());
		canonicalDto.setAccountWithInstitutionAcct(ngphCanonical.getAccountWithInstitutionAcct());
		canonicalDto.setBeneficiaryCustAcct(ngphCanonical.getBeneficiaryCustAcct());
		canonicalDto.setBeneficiaryCustomerName(ngphCanonical.getBeneficiaryCustomerName());
		canonicalDto.setBeneficiaryCustomerID(ngphCanonical.getBeneficiaryCustomerID());
		canonicalDto.setBeneficiaryType(ngphCanonical.getBeneficiaryType());
		canonicalDto.setBeneficiaryCustomerCtry(ngphCanonical.getBeneficiaryCustomerCtry());
		///canonicalDto.setBeneficiaryCustomerCtctDtls(ngphCanonical.getb)
		
		canonicalDto.setCatgPurposeCode(ngphCanonical.getCatgPurposeCode());
		//canonicalDto.setCatgPurposePriority(ngphCanonical.getCatgPurposePriority());
		
		//canonicalDto.setChargeAmount(ngphCanonical.getMsgAmount());
		
		canonicalDto.setChargeBearer(ngphCanonical.getChargeBearer());
		//canonicalDto.setChargeCurrency(ngphCanonical.getMsgCurrency());
	//	canonicalDto.setChargingPartyBank(ngphCanonical.getChargingPartyBank());
		canonicalDto.setClrgSysReference(ngphCanonical.getClrgSysReference());
		canonicalDto.setCustAccount(ngphCanonical.getCustAccount());
		canonicalDto.setCustTxnReference(ngphCanonical.getCustTxnReference());
		//canonicalDto.setInitiatingParty(ngphCanonical.getInitiatingParty());
		
		canonicalDto.setInitiatorRemitAdviceMethod(ngphCanonical.getInitiatorRemitAdviceMethod());
		canonicalDto.setInitiatorRemitReference(ngphCanonical.getInitiatorRemitReference());
		canonicalDto.setInstructedAmount(ngphCanonical.getInstructedAmount());
		
		canonicalDto.setInstructedCurrency(ngphCanonical.getInstructedCurrency());
		canonicalDto.setInstructionsForCrdtrAgtCode(ngphCanonical.getInstructionsForCrdtrAgtCode());
		
		canonicalDto.setInstructionsForCrdtrAgtText(ngphCanonical.getInstructionsForCrdtrAgtText());
		canonicalDto.setInstructionsForNextAgtCode(ngphCanonical.getInstructionsForNextAgtCode());
		
		canonicalDto.setInstructionsForNextAgtText(ngphCanonical.getInstructionsForNextAgtText());
		canonicalDto.setIntermediary1AgentAcct(ngphCanonical.getIntermediary1AgentAcct());
		canonicalDto.setIntermediary1Bank(ngphCanonical.getIntermediary1Bank());
		
		canonicalDto.setIntermediary2AgentAcct(ngphCanonical.getIntermediary2AgentAcct());
		canonicalDto.setIntermediary2Bank(ngphCanonical.getIntermediary2Bank());
		canonicalDto.setIntermediary3AgentAcct(ngphCanonical.getIntermediary3AgentAcct());
		canonicalDto.setIntermediary3Bank(ngphCanonical.getIntermediary3Bank());
	
		canonicalDto.setLclInstCode(ngphCanonical.getLclInstCode());
		//canonicalDto.setLclInstPriority(ngphCanonical.getLclInstPriority());
		
		canonicalDto.setMsgAmount(ngphCanonical.getMsgAmount());
		canonicalDto.setMsgCurrency(ngphCanonical.getMsgCurrency());
		canonicalDto.setMsgPurposeCode(ngphCanonical.getMsgPurposeCode());
		
		canonicalDto.setMsgPurposeText(ngphCanonical.getMsgPurposeText());
		
		canonicalDto.setMsgReturnReference(ngphCanonical.getMsgReturnReference());
		
		canonicalDto.setMsgBranch(ngphCanonical.getMsgBranch());
		canonicalDto.setMsgDept(ngphCanonical.getMsgDept());
		canonicalDto.setMsgPrevStatus(ngphCanonical.getMsgPrevStatus());
		canonicalDto.setMsgStatus(ngphCanonical.getMsgStatus());
		canonicalDto.setMsgValueDate(ngphCanonical.getMsgValueDate());
		canonicalDto.setMsgDirection(ngphCanonical.getMsgDirection());
		canonicalDto.setOrderingCustAccount(ngphCanonical.getOrderingCustAccount());
		
		//canonicalDto.setOrderingCustomer(ngphCanonical.getOrderingCustomer());
		canonicalDto.setOrderingCustomerName(ngphCanonical.getOrderingCustomerName());
		canonicalDto.setOrderingCustomerId(ngphCanonical.getOrderingCustomerId());
		canonicalDto.setOrderingCustomerAddress(ngphCanonical.getOrderingCustomerAddress());
		canonicalDto.setOrderingType(ngphCanonical.getOrderingType());
		canonicalDto.setOrderingInstitution(ngphCanonical.getOrderingInstitution());
		canonicalDto.setOrderingInstitutionAcct(ngphCanonical.getOrderingInstitutionAcct());
		canonicalDto.setOrderingType(ngphCanonical.getOrderingType());
		canonicalDto.setPrevInstructingAgentAcct(ngphCanonical.getPrevInstructingAgentAcct());
		canonicalDto.setPrevInstructingBank(ngphCanonical.getPrevInstructingBank());
		
		canonicalDto.setReceiverBank(ngphCanonical.getReceiverBank());
		
		canonicalDto.setRegulatoryBankCode(ngphCanonical.getRegulatoryBankCode());
		canonicalDto.setRegulatoryInformation(ngphCanonical.getRegulatoryInformation());
		
		canonicalDto.setRegulatoryReportAmount(ngphCanonical.getRegulatoryReportAmount());
		canonicalDto.setRegulatoryReportCurrency(ngphCanonical.getRegulatoryReportCurrency());
		canonicalDto.setRemitInfoRef(ngphCanonical.getRelatedRemitInfo());
		
		canonicalDto.setRelReference(ngphCanonical.getRelReference());
		canonicalDto.setRemitInfoEmail(ngphCanonical.getRemitInfoEmail());
		canonicalDto.setRemitReceivingPartyAddress(ngphCanonical.getRemitReceivingPartyAddress());
		canonicalDto.setRemitReceivingPartyName(ngphCanonical.getRemitReceivingPartyName());
		//canonicalDto.setRemtInfoRef(ngphCanonical.getRemtInfoRef());
		
		canonicalDto.setSenderBank(ngphCanonical.getSenderBank());
		
		canonicalDto.setSndrPymtPriority(ngphCanonical.getSndrPymtPriority());
		canonicalDto.setSndrSttlmntPriority(ngphCanonical.getSndrSttlmntPriority());
		
		canonicalDto.setSndrTxnId(ngphCanonical.getSndrTxnId());
		canonicalDto.setSrcMsgSubType(ngphCanonical.getSrcMsgSubType());
		
		canonicalDto.setSrcMsgType(ngphCanonical.getSrcMsgType());
		
		canonicalDto.setSvcLevelCode(ngphCanonical.getSvcLevelCode());
		
		//canonicalDto.setSvcLevelPriority(ngphCanonical.getSvcLevelPriority());
		canonicalDto.setTxnReference(ngphCanonical.getTxnReference());
		canonicalDto.setUltimateCreditorName(ngphCanonical.getUltimateCreditorName());
		//canonicalDto.setUltimateCreditor(ngphCanonical.getUltimateCreditor());
		//canonicalDto.setUltimateDebtor(ngphCanonical.getUltimateDebtor());
		
		canonicalDto.setXchangeRate(ngphCanonical.getXchangeRate());
		
		
		
		
		return canonicalDto;
		
	}
*/
	/**
	* This method is used to update repaired  data into TA_MODIFIEDMESSAGES table
	* @param ModifiedMessage modifiedMessage
	* @return  void
	*/
	
	@Transactional
	public void saveRepairedPayment(ModifiedMessage modifiedMessage){
		
		getHibernateTemplate().saveOrUpdate(modifiedMessage);	
	}

	/**
	* This method is used to update payment status as delete into TA_MESSAGES_TX table
	* @param final ArrayList<String> messageList
	* @param final String messageStatus
	* @param final String prevMessageStatus
	* @param final String user
	* @param final String comments
	* @return  void
	*/
	@SuppressWarnings("rawtypes")
	
	@Transactional 
	public void deletePayment(final ArrayList<String> messageList,final String messageStatus,final String prevMessageStatus,final String user,final String comments) {
		
			getHibernateTemplate().executeFind(new HibernateCallback() {
				
				 public Object doInHibernate(Session session) throws HibernateException, SQLException {
					 Query query ;
					 Query rpt;
						for (String message : messageList)  
				      {  
				    	  query = session.createQuery("update NgphCanonical set msgStatus = :msgStatus ,msgPrevStatus = :prevStatus ,lastModifiedUser = :modifiedUser,comments = :deleteComments ,lastModTime = :modifiedTime where msgRef = :msgRef");
			                query.setString("msgStatus",messageStatus);
			                query.setString("msgRef",message.trim());
			                query.setString("prevStatus",prevMessageStatus);
			                query.setString("modifiedUser",user);
			                query.setString("deleteComments",comments);
			               
			                query.setTimestamp("modifiedTime", getCurrentTime());
			              int i=  query.executeUpdate();
			             
			              rpt = session.createQuery("update ReportEntity set msgStatus = :msgStatus  ,comments = :deleteComments ,lastModTime = :modifiedTime where msgRef = :msgRef");
			              rpt.setString("msgStatus",messageStatus);
			              rpt.setString("msgRef",message.trim());
			            
			             
			              rpt.setString("deleteComments",comments);
			               
			              rpt.setTimestamp("modifiedTime", getCurrentTime());
			              int i1=  rpt.executeUpdate();
			             
			              
			              logger.debug("number of rows affected"+i+" In RPT "+i1);
				      }  
		          
		                return null;
		            }
		        });
			
		
	/*getHibernateTemplate().executeFind(new HibernateCallback() {
			 public Object doInHibernate(Session session) throws HibernateException, SQLException {
	                Query query = session.createQuery("update NgphCanonical set msgStatus = :msgStatus where msgRef = :msgRef");
	                query.setString("msgStatus","5");
	                query.setString("msgRef","d010b472-2a75-468b-8d7c-af4c048ec946");
	              int i=  query.executeUpdate();
	            logger.debug("number of rows affected"+i);
	                return null;
	            }
	        });*/
		
	
	}
	/**
	* This method is used to get the current timestamp
	* @return Timestamp timeStampDate
	*/
	private Timestamp getCurrentTime(){
		java.util.Date 	str_date = Calendar.getInstance().getTime();
		java.sql.Timestamp timeStampDate = new Timestamp(str_date.getTime());
		return timeStampDate;
	}


	
	public List getAuthRef(String msgBranch, String msgDept, List messageRef) {
		 List  authRef = new ArrayList<String>();
		
		for(int i =0;i< messageRef.size();i++)
		{
			authRef = getHibernateTemplate().find("select authRef from AuthUIStatusT where branch ='"+msgBranch+"' and dept ='"+msgDept+"' and " +
					"msgRef = '"+messageRef.get(i)+"' and authTime is null and rejectedTime is null");
			}
		
		return authRef;
	}


	
	public SecUsers getUserDetails(String user) {
		try{
		SessionFactory fact=getHibernateTemplate().getSessionFactory();
		Session sess=fact.openSession();
		Connection con=sess.connection();
		SecUsers userObj = (SecUsers)sess.get(SecUsers.class, user);
		sess.close();
		fact.close();
		con.close();
		return userObj;
		}
		catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return null;
	}
	/**
	 * This method getStatusMonitor use for display the status monitor screen
	 */
	
	public Map<String,Long> getStatusMonitor(String direction,String queryDate)throws SQLException{
		Map<String,Long> result = new HashMap<String,Long>();
		List tempResult = new ArrayList();
	//	System.out.println( "query date "+queryDate);

		if(direction.equals("I")){
			tempResult = getHibernateTemplate().find("select msgStatus,count(msgStatus) from ReportEntity where direction=? and msgStatus<>99 and "+queryDate+" group by msgStatus ",direction);
			if(tempResult!=null && tempResult.isEmpty()){
				tempResult = getHibernateTemplate().find("select msgStatus,count(msgStatus) from ReportMessageHist where direction=? and msgStatus<>99 and "+queryDate+" group by msgStatus ",direction);
			}			
		}else if(direction.equals("O")){
			tempResult = getHibernateTemplate().find("select msgStatus,count(msgStatus) from ReportEntity where direction=? and msgStatus<>99 and "+queryDate+" group by msgStatus",direction);
			if(tempResult!=null && tempResult.isEmpty()){
				tempResult = getHibernateTemplate().find("select msgStatus,count(msgStatus) from ReportMessageHist where direction=? and msgStatus<>99 and "+queryDate+" group by msgStatus ",direction);
			}
		}
		if(tempResult!=null && tempResult.size()>0){
			for(int i=0;i<tempResult.size();i++){
				try{				
					 Object[] obj = (Object[]) tempResult.get(i);
					 String status = (String)obj[0];
					 Long count = (Long)obj[1];				 
					 result.put(status, count);				 
				
				}catch(Exception exception){
					logger.debug(exception);
				}
			}	
		}
		return result;
	}
			
	@Transactional(propagation = Propagation.REQUIRED)
	public String insertIntoDBPoller(List insertIntoDBPoller) {
		String returnType = "success";
		Session session = getHibernateTemplate().getSessionFactory().openSession();
		session.beginTransaction();
		try{
		for(int i  = 0; i<insertIntoDBPoller.size();i++){
			List serviceIDList = getHibernateTemplate().find("select serviceID from NgphCanonical where msgRef = ?",insertIntoDBPoller.get(i).toString());
			if(serviceIDList!=null && !serviceIDList.isEmpty() && serviceIDList.size()>0)
			{
				String serviceID = serviceIDList.get(0).toString();
			MsgPolled msgPolled = new MsgPolled();
			msgPolled.setInTime(new Timestamp(Calendar.getInstance().getTime().getTime()));
			msgPolled.setMsgRef(insertIntoDBPoller.get(i).toString());
			msgPolled.setPolledStatus("P");
			msgPolled.setServiceID(serviceID);
			
			getHibernateTemplate().saveOrUpdate(msgPolled);
			
			Query query = session.createSQLQuery("update TA_MSGS_RPT  set MSGS_MSGSTS = :MSGS_MSGSTS where MSGS_MSGREF =:MSGS_MSGREF");
			query.setString("MSGS_MSGSTS", "99");
			query.setString("MSGS_MSGREF", insertIntoDBPoller.get(i).toString());
			query.executeUpdate();
			
			}
			else
			{
				logger.error("Service Id Not Available In Database");
				
				returnType =  "error";
			}
		}
			session.beginTransaction().commit();
			session.close();
		}catch (Exception e) {
			e.printStackTrace();
			session.beginTransaction().rollback();
			session.close();
			returnType =  "error";
		}
		return returnType;
	
	}


	
	public String getScreenIDFromSupport(String msgType) throws SQLException {
		String screenID = null;
		@SuppressWarnings("unchecked")
		Object[] patams = {msgType.split(" ")[0], msgType.split(" ")[1]};
		System.out.println("Param Values are "+patams.length);
		List<String> list = getHibernateTemplate().find("select supportScreenID from MsgSupport where supportMsgType = ? and supportSubMsgType =? ",patams);
		System.out.println("List is "+list.size());
		if(list!=null && !list.isEmpty() && list.size()>0)
		{
			screenID = list.get(0);
		}
		return screenID;
	}


	
	public void changeMsgStatus2to99(String msgRef) {
		try{
		
			Query query=null;
			SessionFactory fact=getHibernateTemplate().getSessionFactory();
			Session session=fact.openSession();
			session.beginTransaction();
			query = session.createSQLQuery("update  TA_MSGS_RPT set MSGS_MSGSTS = '99' where MSGS_MSGREF ='"+msgRef+"'");
			int result=query.executeUpdate();
			session.getTransaction().commit();
			session.close();
			fact.close();
		}catch (Exception e) {
			e.printStackTrace();
			
		}
	}
	
	public void changeMsgStatus99to2(String msgRef) {
		try{
		
			Query query=null;
			SessionFactory fact=getHibernateTemplate().getSessionFactory();
			Session session=fact.openSession();
			session.beginTransaction();
			query = session.createSQLQuery("update  TA_MSGS_RPT set MSGS_MSGSTS = '2' where MSGS_MSGREF ='"+msgRef+"'");
			int result=query.executeUpdate();
			session.getTransaction().commit();
			session.close();
			fact.close();
		}catch (Exception e) {
			e.printStackTrace();
			
		}
	}
	
	public boolean checkInRptStatusIs2(String msgRef) {
		List list = getHibernateTemplate().find("select msgStatus from ReportEntity where msgRef =?",msgRef);
		if(!list.isEmpty()&& list!=null && list.size()>0 ){
			if(list.get(0).toString().equals("2")){
				return true;
			}else
			{
				return false;
			}
			
		}
		else
			return false;
	}

}
