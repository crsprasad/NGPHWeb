package com.logica.ngph.daoImpl;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.logica.ngph.Entity.LcCommodity;
import com.logica.ngph.Entity.LcMast;
import com.logica.ngph.Entity.MsgPolled;
import com.logica.ngph.Entity.NgphCanonical;
import com.logica.ngph.Entity.Parties;
import com.logica.ngph.Entity.ReportEntity;
import com.logica.ngph.common.ConstantUtil;
import com.logica.ngph.dao.LetterOfCreditDao;
import com.logica.ngph.dtos.LCCanonicalDto;
import com.logica.ngph.dtos.PartyDTO;

public class LetterOfCreditDaoImpl extends HibernateDaoSupport implements LetterOfCreditDao{

	
	public void saveLCCommodity(LcCommodity commodity,String msgRef,String lcNumber) {
		String commodityValue ="";
		try{
			SessionFactory fact=getHibernateTemplate().getSessionFactory();
			Session sess=fact.openSession();
			sess.beginTransaction();
			Connection con=sess.connection();
			if(commodity.getLcCommodity().contains("'"))
			{
				commodityValue = commodity.getLcCommodity().replace("'","''" );
			}
			else
			{
				commodityValue = commodity.getLcCommodity();
			}
			String query="insert into TA_LC_COMMODITY (MSGS_MSGREF,LC_COMMODITY,LC_NUMBER) values ('"+msgRef+"','"+commodityValue+"','"+lcNumber+"' )";
			Query q = sess.createSQLQuery(query);

			int result=q.executeUpdate();
			System.out.println("No of records updated are : " + result);
			sess.getTransaction().commit();
			sess.close();
			fact.close();
			con.close();
		}
		catch (Exception e)
		{e.printStackTrace();
		}
		
	}

	
	public void saveLCMast(LcMast lcMast) {
		try{
		getHibernateTemplate().saveOrUpdate(lcMast);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}

@Transactional(propagation=Propagation.REQUIRED)
	public void saveLCCanonical(NgphCanonical canonical) {
	try{
		getHibernateTemplate().saveOrUpdate(canonical);
	}
	catch (Exception e) {
		e.printStackTrace();
	}
		
	}

public List<Parties> getIFSCCode(String ifscCode,String userId) {
	
	List getBranch = getHibernateTemplate().find("select userBranch from SecUsers where user =?",userId);
	
	List<Parties> partyList = new ArrayList<Parties>();
	if(ifscCode == null){
		ifscCode = "";
	}else{
		ifscCode = "%"+ifscCode+"%";
	}
	List list=getHibernateTemplate().find("select branchIFSCCode from Branches where branchIFSCCode like ? and branchCode =?",ifscCode,getBranch.get(0));
	for(int i=0;i<list.size();i++)
	{
		Parties parties= new Parties();
		
		 parties.setClearingSystemMemberId(list.get(i).toString());
		 partyList.add(parties);
	}
	
	return partyList;
}

public void changeStatus(String msgRef) {
	try{
		SessionFactory fact=getHibernateTemplate().getSessionFactory();
		Session sess=fact.openSession();
		sess.beginTransaction();
		Connection con=sess.connection();

		String query="update  TA_LC_MAST set LC_STATUS = 3 where MSGS_MSGREF ='"+msgRef+"'";
		Query q = sess.createSQLQuery(query);

		int result=q.executeUpdate();
		System.out.println("No of records updated are : " + result);
		sess.getTransaction().commit();
		sess.close();
		fact.close();
		con.close();
		
		
	}catch (Exception e) {
		e.printStackTrace();
		// TODO: handle exception
	}
	
}


public void saveINPolled(MsgPolled msgPolled) {
	getHibernateTemplate().saveOrUpdate(msgPolled);
	
}


public Boolean checkIFSC(String toCheck) {
	List partlist = getHibernateTemplate().find("select clearingSystemMemberId from Parties where clearingSystemMemberId = ? and clearingSystemMemberId is not null",toCheck);
	List branchlist = getHibernateTemplate().find("select branchIFSCCode from Branches where branchIFSCCode = ? and branchIFSCCode is not null",toCheck);
	
	if(partlist.size()!=0 || branchlist.size()!=0)
	{
		return true;
	}else
		return false;
	
	
}


public List<Parties> getIFSCCodeList(String ifscCode,String flag) {
	List<Parties> partyList = new ArrayList<Parties>();
	if(ifscCode == null){
		ifscCode = "";
	}else{
		ifscCode = "%"+ifscCode+"%";
	}
	String searchQuery = "";

	List list = null ;
	if(flag.equalsIgnoreCase("PARTY"))
	{
		searchQuery = "select clearingSystemMemberId, partyBranchName, partyAddress  from Parties where clearingSystemMemberId like '"+ifscCode+"' and clearingSystemMemberId is not null order by clearingSystemMemberId";
	}
	else if(flag.equalsIgnoreCase("BRANCH")){
		searchQuery = "select br.branchIFSCCode, br.branchName,  concat(ad.buildingDetail,' ', ad.streetName,' ', ad.townName,' ', ad.postalCode) as branchAddress  from Branches br, Addresses ad  where br.branchIFSCCode like '"+ifscCode+"' and br.branchAddressRef = ad.addressRef and br.branchIFSCCode is not null order by br.branchIFSCCode";
	}
	else if(flag.equalsIgnoreCase("BOTH")){
		@SuppressWarnings("unchecked")
		List<Parties> partyLists = getHibernateTemplate().find("select clearingSystemMemberId, partyBranchName, partyAddress  from Parties where clearingSystemMemberId like '"+ifscCode+"' and clearingSystemMemberId is not null");
		@SuppressWarnings("unchecked")
		List<Parties> branchLists = getHibernateTemplate().find("select br.branchIFSCCode, br.branchName,  concat(ad.buildingDetail,' ', ad.streetName,' ', ad.townName,' ', ad.postalCode) as branchAddress  from Branches br, Addresses ad  where br.branchIFSCCode like '"+ifscCode+"' and br.branchAddressRef = ad.addressRef and br.branchIFSCCode is not null order by br.branchIFSCCode");
			partyLists.addAll(branchLists);
				
		list = partyLists;
	}
	else if(flag.equalsIgnoreCase("SENDERBANKIFSC")){
		@SuppressWarnings("unchecked")
		List<Parties> partyLists = getHibernateTemplate().find("select clearingSystemMemberId, partyBranchName, partyAddress  from Parties where clearingSystemMemberId like 'MHCB%' and clearingSystemMemberId is not null");
		list = partyLists;
	}
	if(!flag.equalsIgnoreCase("BOTH") && !flag.equalsIgnoreCase("SENDERBANKIFSC"))
	list = getHibernateTemplate().find(searchQuery);
	for(int i=0;i<list.size();i++){
		Parties parties= new Parties();
	
		Object[] obj = (Object[]) list.get(i);
		parties.setClearingSystemMemberId((String)obj[0]);
		parties.setPartyBranchName((String)obj[1]);
		parties.setPartyAddress((String)obj[2]);
		 partyList.add(parties);
		}

	
	return partyList;
}


public String getIssuingIFSC(String lcNumber) {
	List list = getHibernateTemplate().find("select lcIssuingBank from LcMast where lcNumber=?",lcNumber);
	String issuningBank=null;
	if(list!=null)
	{
		issuningBank= list.get(0).toString();
	}
	return issuningBank;
}


public String getDept(String userID) {
	List getBranchDepart = getHibernateTemplate().find("select userDept,userBranch from SecUsers where user =?",userID);
	List getIfsc=null;
	String branchCode = null;
	
	if(getBranchDepart!=null){
		Object[] obj = (Object[]) getBranchDepart.get(0);
		if(obj[1].toString().equals(ConstantUtil.ALL)){
			String initEntry = "DEFBRANCH";
			List val= getHibernateTemplate().find("select initValue from Initialisation where initEntry =?",initEntry);
			if(!val.isEmpty())
			{
				branchCode=val.get(0).toString();
			}
		
		}else
			branchCode = obj[1].toString();
		getIfsc = getHibernateTemplate().find("select branchIFSCCode from Branches where  branchCode =?",branchCode);
		if(getIfsc!=null)
		return getIfsc.get(0)+"~"+obj[0]+"~"+obj[1];
	}
	return null;
}


public String getLcOpenFlagForInsertOrUpdate() {
	String flag = getHibernateTemplate().find("select initValue from Initialisation where initEntry = 'LCOPENFLAG'").get(0).toString();
	return flag;
}


public Boolean isLcNumberExist(String lcNumber) {
	List list =  getHibernateTemplate().find("select compositeKeyForLcMast.lcNo from LcMast where compositeKeyForLcMast.lcNo = ?",lcNumber);
	if(list.size()==0)
	{
		return false;
	}
	else 
	{
		return true;
	}
	
}


public Boolean isLcStatusTwo(String lcNumber) {
	List list =  getHibernateTemplate().find("select compositeKeyForLcMast.lcNo from LcMast where compositeKeyForLcMast.lcNo = ? and lcStatus = 1",lcNumber);
	if(list.size()==0)
	{
		return false;
	}
	else 
	{
		return true;
	}
	
}


@Transactional(propagation = Propagation.REQUIRED)
public String doCompleteTransaction(List<LcCommodity> commodityList,
		LcMast lcMast, NgphCanonical canonical, MsgPolled msgPolled,
		String msgRef, String status,String generatedMsgRef,String lcNumber, String repairMsgref,String repair,ReportEntity entity) {
	
	SessionFactory fact=getHibernateTemplate().getSessionFactory();
	Session sess=fact.openSession();
	sess.beginTransaction();
	
	try{
		System.out.println();
	String  query="";
	String commodityValue="";
	if(commodityList!=null && commodityList.size()!=0 ){
		for(int i =0 ;i<commodityList.size();i++){
			if(commodityList.get(i).getLcCommodity().contains("'"))
			{
				commodityValue = commodityList.get(i).getLcCommodity().replace("'", "''");
			}
			else
			{
				commodityValue = commodityList.get(i).getLcCommodity();
			}
	query="insert into TA_LC_COMMODITY (MSGS_MSGREF,LC_COMMODITY,LC_NUMBER) values ('"+generatedMsgRef+"','"+commodityValue+"','"+lcNumber+"' )";
	Query q = sess.createSQLQuery(query);

	int result=q.executeUpdate();
	System.out.println("No of records updated are : " + result);
		}
	}
	
	getHibernateTemplate().saveOrUpdate(lcMast);
	getHibernateTemplate().saveOrUpdate(canonical);		
	getHibernateTemplate().saveOrUpdate(msgPolled);
	getHibernateTemplate().saveOrUpdate(entity);
	/*String rptQuery = null;
	ReportEntity reportEntity = null;
	String connQuery=null;*/
	if(StringUtils.isNotBlank(repair) && StringUtils.isNotEmpty(repair)){
		Query statusQuery=null;
		statusQuery = sess.createSQLQuery("update  TA_MESSAGES_TX set MSGS_MSGSTS = '99' where MSGS_MSGREF ='"+msgRef+"'");
		int result=statusQuery.executeUpdate();
		System.out.println("Result : -"+result );
		Query statusQueryRpt=null;
		statusQueryRpt = sess.createSQLQuery("update  TA_MSGS_RPT set MSGS_MSGSTS = '99' where MSGS_MSGREF ='"+msgRef+"'");
		int result1=statusQueryRpt.executeUpdate();
		System.out.println("Result : -"+result1 );
	
	}
	
	/*reportEntity = (ReportEntity)sess.createQuery(rptQuery).uniqueResult();
	
	if(reportEntity!=null){
		NgphCanonical ngphCanonical = null;
		ngphCanonical = (NgphCanonical)sess.createQuery(connQuery).uniqueResult();		
		ReportEntity reportEntity1 = ReportEntityUtil.getReportEntityFromCanonical(ngphCanonical);
		ngphCanonical.setMsgStatus("99");
		reportEntity1.setMsgStatus("99");
		getHibernateTemplate().update(ngphCanonical);
		getHibernateTemplate().update(reportEntity1);	
	}else{
		ReportEntity reportEntity1 = ReportEntityUtil.getReportEntityFromCanonical(canonical);
		getHibernateTemplate().save(reportEntity1);	
	}*/
	
	String changeStatus = "";
	Query changeMSGStaus=null;
	
	if(status.equals("LcOpen")){
		changeStatus ="update  TA_LC_MAST set LC_STATUS = 3 where MSGS_MSGREF ='"+msgRef+"'";
		changeMSGStaus = sess.createSQLQuery(changeStatus);
		int result=changeMSGStaus.executeUpdate();
	}else if(status.equals("AuthPaymentAdvice")){
		changeStatus ="update  TA_LC_MAST set LC_STATUS = 9 where MSGS_MSGREF ='"+msgRef+"'";	
		changeMSGStaus = sess.createSQLQuery(changeStatus);
		int result=changeMSGStaus.executeUpdate();
		}else if(status.equals("PaymentAdvice")){
			changeStatus ="update  TA_LC_MAST set LC_STATUS = 7 where MSGS_MSGREF ='"+msgRef+"'";
			changeMSGStaus = sess.createSQLQuery(changeStatus);
			int result=changeMSGStaus.executeUpdate();
		}else if(status.equals("Amend_LC")){
			changeStatus ="update  TA_LC_MAST set LC_STATUS = 5 where MSGS_MSGREF ='"+msgRef+"'";
			changeMSGStaus = sess.createSQLQuery(changeStatus);
			int result=changeMSGStaus.executeUpdate();
		}else if(status.equals("TransferDocumentaryCredit")){
			changeStatus ="update  TA_LC_MAST set LC_STATUS = 17 where MSGS_MSGREF ='"+msgRef+"'";
			changeMSGStaus = sess.createSQLQuery(changeStatus);
			int result=changeMSGStaus.executeUpdate();
		}else if(status.equals("ReimburesmentClaim")){
			changeStatus ="update  TA_LC_MAST set LC_STATUS = 11 where MSGS_MSGREF ='"+msgRef+"'";
			changeMSGStaus = sess.createSQLQuery(changeStatus);
			int result=changeMSGStaus.executeUpdate();
		}
	
	/*if(StringUtils.isNotBlank(repair)&&StringUtils.isNotEmpty(repair) && repair!=null)
	{
		Query statusQuery=null;
		statusQuery = sess.createSQLQuery("update  TA_MESSAGES_TX set MSGS_MSGSTS = '99' where MSGS_MSGREF ='"+msgRef+"'");
		int result=statusQuery.executeUpdate();
		Query statusQueryRpt=null;
		statusQueryRpt = sess.createSQLQuery("update  TA_MSGS_RPT set MSGS_MSGSTS = '99' where MSGS_MSGREF ='"+msgRef+"'");
		int result1=statusQueryRpt.executeUpdate();
		System.out.println("Result : -"+result1 );
	}*/
	
	sess.getTransaction().commit();
	sess.close();
	fact.close();
	
	System.out.println("All Records Are Inserted");
	return "success";
	}
	catch (Exception e) {
		sess.beginTransaction().rollback();
		e.printStackTrace();
		sess.close();
		fact.close();
		
		return null;
	}
	
	
}
public String getSequenceNo()
{
	SessionFactory fact=getHibernateTemplate().getSessionFactory();
	Session sess=fact.openSession();
	String key="";
	Query seq = sess.createSQLQuery("select GENERICSCREENSEQUENCE.nextval from dual" );
    key = seq.uniqueResult().toString();
    return key;
}

@SuppressWarnings("null")

public LCCanonicalDto getPreAdviceRepair(String msgRef) {
	String query="select canonical.lcType,canonical.lcNo,canonical.lcPrevAdvRef, canonical.lcIssueDt,trunc(canonical.lcExpDt),canonical.lcExpPlace," +//6
	" canonical.lcPositiveTolerance,canonical.lcMaxCrAmt, canonical.lcAddlAmts, canonical.lcAuthBankCode, canonical.lcAuthBankAddr, canonical.lcAuthMode, canonical.initialDispatchPlace, canonical.lcDstn," +//8
	" canonical.lcLstShipDt, canonical.lcShipPeriod, canonical.lcShipTerms, canonical.lcDraftsAt, canonical.lcDraweeBnkPid, canonical.lcDraweeBnkCode, canonical.lcDraweeBnkAddr, canonical.lcMixedPymtDet," +//8
	"	 canonical.lcDefPymtDet, canonical.lcPartialShipment, canonical.lcTransShipment, concat(canonical.lcDocsReq1, canonical.lcDocsReq2), concat(canonical.lcAddnlCndt1, canonical.lcAddnlCndt2)," +//5
	" canonical.lcCharges, canonical.lcPrsntnPrd, canonical.lcConfrmInstrns, canonical.lcInstrnTopay, canonical.lcNarrative,canonical.msgRef,canonical.beneficiaryCustomerName,canonical.orderingCustomerName,canonical.accountWithInstitutionId,canonical.accountWithInstitution," +
	"canonical.accountWithInstitutionLoc,canonical.accountWithInstitutionName,canonical.instructionsForNextAgtText,canonical.orderingCustomerName," +
	"canonical.sendingInstId,canonical.sendingInst,canonical.sendingInstNameAdd,canonical.orderingCustAccount,canonical.beneficiaryCustAcct,canonical.beneficiaryCustomerName,canonical.senderCorrespondentId," +
	"canonical.senderCorrespondent,canonical.senderCorrespondentName,canonical.msgAmount,canonical.msgCurrency,canonical.lcNegativeTolerance,canonical.receiverBank,canonical.accountWithInstitutionAccount,canonical.lcAddlAmts, " +
	" canonical.lcIssueDt,canonical.txnReference, canonical.custTxnReference, canonical.sndrTxnId,canonical.lcDispatchPlace, canonical.finalDeliveryPlace, "+	//56
	" canonical.relReference, canonical.lcToAmtClaimed, canonical.lcAmtPaid, canonical.senderCorrespondentId, canonical.senderCorrespondentAcct, canonical.senderCorrespondent, canonical.senderCorrespondentLoc, canonical.senderCorrespondentName, "+
	" canonical.receiverCorrespondentId, canonical.receiverCorrespondentAcct, canonical.receiverCorrespondent, canonical.receiverCorrespondentLoc, canonical.receiverCorrespondentName,canonical.msgValueDate,canonical.lcAppRulesCode,canonical.lcAmndmntIncAmt,canonical.lcAmndmntDecAmt,canonical.lcAmndmntDt,canonical.lcAmndmntNo, "+
	"canonical.lcAppRulesDesc ,canonical.accountWithInstitutionAcct,canonical.msgHost,canonical.seqNo,canonical.lcOldExpDt,canonical.lcAmndmntOldAmt,canonical.lcDraweeBnkAcct,canonical.lcAccId,canonical.chargeBearer,canonical.serviceID,canonical.mesgIsReturn,canonical.msgPDECount,canonical.grpSeq,canonical.pymntAcceptedTime," +
	"canonical.orderingInstitution,canonical.beneficiaryInstitutionPID,canonical.beneficiaryInstitutionAcct,canonical.beneficiaryInstitutionName,canonical.beneficiaryInstitution,canonical.lcNetAmtClaimed,canonical.orderingInstitutionAcct,canonical.senderBank,canonical.instructionsForCrdtrAgtCode,canonical.instructionsForCrdtrAgtText from NgphCanonical canonical  where canonical.msgRef=?"  ;//10
		List list = getHibernateTemplate().find(query,msgRef.trim());
		LCCanonicalDto lcCanonicalDto = new LCCanonicalDto();
		if(list!=null && !list.isEmpty() && list.size()>0){
		Object[] obj = (Object[]) list.get(0);
		lcCanonicalDto.setLcType((String)obj[0]);
		lcCanonicalDto.setLcNumber((String) obj[1]);
		lcCanonicalDto.setIssueDate((Date) obj[3]);
		lcCanonicalDto.setLcExpiryDate((Date)obj[4]);
		lcCanonicalDto.setLcExpirePlace((String)obj[5]);
		  if(obj[6]!=null && obj[52]!=null )
	        {
	        	if(Integer.parseInt(obj[6]+"")==0 || Integer.parseInt(obj[52]+"")==0){
	        		lcCanonicalDto.setPositiveTolerance(null);
	            	lcCanonicalDto.setNegativeTolerance(null);
	        	}else{
	        	lcCanonicalDto.setPositiveTolerance(obj[6]+"");
	        	lcCanonicalDto.setNegativeTolerance(obj[52]+"");
	        	}
	        }
		/*lcCanonicalDto.setPositiveTolerance(obj[6]+"");
		lcCanonicalDto.setNegativeTolerance(obj[52]+"");*/
		lcCanonicalDto.setMaximumCreditAmount((String)obj[7]);
		lcCanonicalDto.setAdditionalAmounts((String)obj[8]);
		lcCanonicalDto.setAuthorisedBankCode((String)obj[9]);
		lcCanonicalDto.setAuthorisedAndAddress((String)obj[10]);
		lcCanonicalDto.setAuthorisationMode((String)obj[11]);
		lcCanonicalDto.setGoodsLoadingDispatchPlace((String)obj[12]);
		lcCanonicalDto.setGoodsDestination((String) obj[13]);
		lcCanonicalDto.setLatestDateofShipment((Date) obj[14]);
		lcCanonicalDto.setShipmentPeriod((String) obj[15]);
		lcCanonicalDto.setShipmentTerms((String) obj[16]);
		lcCanonicalDto.setDraftsAt((String) obj[17]);
		lcCanonicalDto.setDraweeBankpartyidentifier((String) obj[18]);
		lcCanonicalDto.setDraweeBankCode((String) obj[19]);
		lcCanonicalDto.setDraweeBankNameAddress((String) obj[20]);
		lcCanonicalDto.setMixedPaymentDetails((String) obj[21]);
		lcCanonicalDto.setDeferredPaymentDetails((String) obj[22]);
		lcCanonicalDto.setPartialShipments((String) obj[23]);
		lcCanonicalDto.setTranshipment((String) obj[24]);
		lcCanonicalDto.setDocumentsRequired((String) obj[25]);
		lcCanonicalDto.setAdditionalConditions((String) obj[26]);
		lcCanonicalDto.setChargeDetails((String) obj[27]);
		lcCanonicalDto.setPeriodforPresentation((String) obj[28]);
		lcCanonicalDto.setConfirmationCode((String) obj[29]);
		lcCanonicalDto.setInstructionstoPayingBank((String) obj[30]);
		lcCanonicalDto.setNarrative((String) obj[31]);
		lcCanonicalDto.setMsgRef((String) obj[32]);
		lcCanonicalDto.setBeneficiaryNameAddress((String) obj[33]);
		lcCanonicalDto.setApplicantNameAddress((String) obj[34]);
		lcCanonicalDto.setAdviseThroughBankpartyidentifier((String) obj[35]);
		lcCanonicalDto.setAdviseThroughBankCode((String) obj[36]);
		lcCanonicalDto.setAdviseThroughBankLocation((String) obj[37]);
		lcCanonicalDto.setAdviseThroughBankName((String) obj[38]);
		lcCanonicalDto.setSendertoReceiverInformation((String) obj[39]);
		lcCanonicalDto.setApplicantNameAddress((String) obj[40]);
		lcCanonicalDto.setApplicantBankpartyidentifier((String) obj[41]);
		lcCanonicalDto.setApplicantBankCode((String) obj[42]);
		lcCanonicalDto.setApplicantBankNameAddress((String) obj[44]);
		lcCanonicalDto.setApplicantAccount((String) obj[43]);
		lcCanonicalDto.setBeneficiaryAccount((String) obj[45]);
		lcCanonicalDto.setBeneficiaryNameAddress((String) obj[46]);
		lcCanonicalDto.setPartyIdentifier((String) obj[47]);
		lcCanonicalDto.setReimbursingBankCode((String) obj[48]);
		lcCanonicalDto.setReimbursingBankNameAddress((String) obj[49]);
		lcCanonicalDto.setLcAmount((BigDecimal) obj[50]);
		lcCanonicalDto.setCreditAmount((BigDecimal) obj[50]);
		lcCanonicalDto.setLcCurrency((String) obj[51]);
		//Start With 53
		lcCanonicalDto.setAdvisingBank((String) obj[53]);
		lcCanonicalDto.setAdviseThroughBankCode((String) obj[54]);
		
		lcCanonicalDto.setAdditionalAmountsCovered((String) obj[55]);
		//prasanna added
		//lcCanonicalDto.setIssueDate((Date) obj[56]);
		lcCanonicalDto.setTxnReference((String) obj[57]);
		lcCanonicalDto.setCustTxnReference((String) obj[58]);
		lcCanonicalDto.setSndrTxnId((String) obj[59]);
		lcCanonicalDto.setInitialDispatchPlace((String) obj[60]);
		lcCanonicalDto.setFinalDeliveryPlace((String) obj[61]);
		//fields for Advice LC payment
		lcCanonicalDto.setPresentingBanksReference((String) obj[62]);
		lcCanonicalDto.setSenderBankReference((String) obj[62]);
		lcCanonicalDto.setTotalAmountClaimed((BigDecimal) obj[63]);
		lcCanonicalDto.setPaidAmount((BigDecimal) obj[64]);
	    lcCanonicalDto.setSendersCorrespondentPartyIdentifier((String) obj[65]);
	    lcCanonicalDto.setSenderCorrespontAcount((String) obj[66]);
	    lcCanonicalDto.setSendersCorrespondentCode((String) obj[67]);
	    lcCanonicalDto.setSendersCorrespondentLocation((String) obj[68]);
	    lcCanonicalDto.setSendersCorrespondentNameandAddress((String) obj[69]);
	    lcCanonicalDto.setReceiversCorrespondentPartyIdentifier((String) obj[70]);
	    lcCanonicalDto.setReceiverCorrespontAcount((String) obj[71]);
	    lcCanonicalDto.setReceiversCorrespondentCode((String) obj[72]);
	    lcCanonicalDto.setReceiversCorrespondentLocation((String) obj[73]);
	    lcCanonicalDto.setReceiversCorrespondentNameandAddress((String) obj[74]);
	    lcCanonicalDto.setAmountPaidDate((Date) obj[75]);
	    lcCanonicalDto.setMsgValueDate((Date) obj[75]);
	    lcCanonicalDto.setApplicableRule((String) obj[76]);
	    lcCanonicalDto.setIncreaseAmendAmount((BigDecimal) obj[77]);
	    lcCanonicalDto.setDecreaseAmendAmount((BigDecimal) obj[78]);
	    lcCanonicalDto.setAmendmentDate((Date)obj[79]);
	    if(obj[80]!=null){
       	 	BigDecimal bd = (BigDecimal) obj[80];
       	 	lcCanonicalDto.setLcAmndmntNo(new Integer(bd.intValue()));
       }	  
	    lcCanonicalDto.setApplicableNarrative((String) obj[81]);
		
	    lcCanonicalDto.setAdviseThroughBankAcc((String) obj[82]);
		lcCanonicalDto.setMsgHost((String) obj[83]);
		lcCanonicalDto.setSeqNo((String) obj[84]);
		lcCanonicalDto.setOldAmendExpiryDate((Date)obj[85]);
		lcCanonicalDto.setOldLcAmount((BigDecimal) obj[86]);
		lcCanonicalDto.setDraweeBankAccount((String)obj[87]);
		lcCanonicalDto.setAcountID((String)obj[88]);
		lcCanonicalDto.setReimbursingBanksCharges((String)obj[89]);
		lcCanonicalDto.setServiceID((String)obj[90]);
		lcCanonicalDto.setMesgIsReturn((BigDecimal)obj[91]);
		lcCanonicalDto.setMsgPDECount((BigDecimal)obj[92]);
		lcCanonicalDto.setMsgGRPSeq((Integer)obj[93]);
		if(obj[94]!=null){
			lcCanonicalDto.setPymntAcceptedTime((Timestamp)obj[94]);
		}
		lcCanonicalDto.setIssuingBankCode((String)obj[95]);

		lcCanonicalDto.setNegotiatingBankPartyIdentifier((String) obj[96]);
		lcCanonicalDto.setNegotiatingBankAccount((String) obj[97]);
		lcCanonicalDto.setNegotiatingBankNameAndAddress((String) obj[98]);
		lcCanonicalDto.setNegotiatingBankCode((String)obj[99]);
		lcCanonicalDto.setLcNetAmtClaimed((BigDecimal) obj[100]);
		lcCanonicalDto.setIssuingBankAcc((String) obj[101]);
		lcCanonicalDto.setSenderBank((String) obj[102]);
		if(obj[103]!=null)
		{
			String sendertoReciver =obj[103]+"";
			if(obj[104]!=null)
			{
				sendertoReciver = sendertoReciver+"/"+obj[104];
			}
			lcCanonicalDto.setSendertoReceiverInformation(sendertoReciver);
		}else
		{
			lcCanonicalDto.setSendertoReceiverInformation((String)obj[104]);
		}

		}
		
		
		return lcCanonicalDto;
	
}


public Boolean getstatusForLCNumber(String msgRef) {
	List list = getHibernateTemplate().find("select msgStatus from NgphCanonical where msgRef =?",msgRef);
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


public List getListOfIFSC(String code) {
	List<PartyDTO> searchList = new ArrayList<PartyDTO>();
	if(code == null && StringUtils.isEmpty(code)){
		code = "%";
	}else{
		code = "%"+code+"%";
	}
	
List list = getHibernateTemplate().find("select clearingSystemMemberId , agentName,clearingSystemMemberCode,partyBranchName,partyAddress from Parties where clearingSystemMemberCode like ?",code);
for(int i = 0 ;i<list.size();i++)
{
	PartyDTO branchesDTO=  new PartyDTO();
	 Object[] obj = (Object[]) list.get(i);
	 branchesDTO.setIfscCode((String) obj[0]);
	 branchesDTO.setPartyName((String) obj[3]);
	 branchesDTO.setPartyCode((String) obj[2]);
	 branchesDTO.setPartyBankName((String) obj[1]);
	 branchesDTO.setPartyAddress((String) obj[4]);
	 searchList.add(branchesDTO);
}
	return searchList;
}


public void saveIFSC(PartyDTO dto) {
	SessionFactory fact=getHibernateTemplate().getSessionFactory();
	Session sess=fact.openSession();
	sess.beginTransaction();
	Query changeMSGStaus  = null;
	
	if(dto.getActionPerform().equalsIgnoreCase("ADD")){
			changeMSGStaus = sess.createSQLQuery("insert into TA_PARTIES (PARTY_CLRSYSMMBID_MMBID, PARTY_NM, PARTY_BRANCH_NAME, PARTY_ADDRESS, CITY_NAME, DISTRICT_NAME, STATE_NAME, IFSC_TYPE, MICR_CODE) values (:IFSC,:bankName,:BranchName,:address,:City,:District,:State,:IFSCType,:MICRCode)");
			changeMSGStaus.setString("IFSC", dto.getIfscCode());
			changeMSGStaus.setString("bankName", dto.getPartyName());
			changeMSGStaus.setString("BranchName", dto.getBranchName());
			changeMSGStaus.setString("address", dto.getPartyAddress());
			changeMSGStaus.setString("City", dto.getCityName());
			changeMSGStaus.setString("District", dto.getDistricName());
			changeMSGStaus.setString("State", dto.getStateName());
			changeMSGStaus.setString("IFSCType", dto.getIfscType());
			changeMSGStaus.setInteger("MICRCode", dto.getMicrCode());
		} else if (dto.getActionPerform().equalsIgnoreCase("DELETE")) {
			changeMSGStaus = sess.createSQLQuery("delete from TA_PARTIES where PARTY_CLRSYSMMBID_MMBID = :IFSC");
			changeMSGStaus.setString("IFSC", dto.getIfscCode());


		}
		int result = changeMSGStaus.executeUpdate();
		sess.getTransaction().commit();
		sess.close();
		fact.close();
	
}
@SuppressWarnings("unused")
public boolean checkPartyIFSC(String ifsc)
{
	List list = getHibernateTemplate().find("select clearingSystemMemberId from Parties where clearingSystemMemberId = ? ",ifsc);
	if(list.isEmpty() && list.size()<=0 )
	{
		return true;
	}else{
		return false;
	}
}


public LCCanonicalDto getLCAckRepair(String msgRef) {
	System.out.println("Inside getLCAckRepair");
	System.out.println("MsgRef is "+msgRef);
	LCCanonicalDto lcCanonicalDto = new LCCanonicalDto();
	String query = "select canonical.lcNo,canonical.relReference," +//2
	" canonical.lcAccId, canonical.lcAckDt, canonical.msgCurrency, canonical.lcTotalAmtClaimed, canonical.msgValueDate, canonical.accountWithInstitutionId, canonical.accountWithInstitutionAcct, canonical.accountWithInstitutionLoc," +//8
	" canonical.senderBank, canonical.receiverBank, canonical.accountWithInstitutionName, canonical.lcCharges, canonical.instructionsForCrdtrAgtCode, canonical.instructionsForCrdtrAgtText,"+ //6
	" canonical.msgRef, canonical.seqNo, canonical.msgHost from NgphCanonical canonical  where canonical.msgRef=?"; //3
		List list = getHibernateTemplate().find(query,msgRef.trim());			
		if(list!=null && !list.isEmpty() && list.size()>0){
			Object[] obj = (Object[]) list.get(0);
			lcCanonicalDto.setLcNumber((String)obj[0]);
			lcCanonicalDto.setRelatedReference((String)obj[1]);
			lcCanonicalDto.setLcAccId((String)obj[2]);
			lcCanonicalDto.setLcAckDate((Date)obj[3]);
			lcCanonicalDto.setMsgCurrency((String)obj[4]);
			lcCanonicalDto.setLcAmount((BigDecimal)obj[5]);
			lcCanonicalDto.setAmountPaidDate((Date)obj[6]);
			lcCanonicalDto.setAdviseThroughBankCode((String)obj[7]);
			lcCanonicalDto.setAdviseThroughBankAcc((String)obj[8]);
			lcCanonicalDto.setAdviseThroughBankLocation((String)obj[9]);
			lcCanonicalDto.setSenderBank((String)obj[10]);
			lcCanonicalDto.setAdvisingBank((String)obj[11]);
			lcCanonicalDto.setAdviseThroughBankName((String)obj[12]);
			lcCanonicalDto.setChargeDetails((String)obj[13]);
			lcCanonicalDto.setSendertoReceiverInformation((String)obj[14]);
			lcCanonicalDto.setSendertoReceiverInformation((String)obj[15]);
			lcCanonicalDto.setMsgRef((String)obj[15]);
			lcCanonicalDto.setSeqNo((String)obj[16]);
			lcCanonicalDto.setMsgHost((String)obj[17]);
		}
	return lcCanonicalDto;
}


public LCCanonicalDto getAdviceofRefusal(String msgRef) {
	
	LCCanonicalDto lcCanonicalDto = new LCCanonicalDto();
	String query = "select canonical.lcNo,canonical.relReference," +//2
	" canonical.msgCurrency, canonical.msgAmount, canonical.msgValueDate, canonical.lcCharges, canonical.lcDispoDocs,canonical.lcTotalAmtClaimed, canonical.accountWithInstitutionId, canonical.accountWithInstitutionAcct, canonical.accountWithInstitutionLoc," +//9
	" canonical.lcDiscrepancies,canonical.senderBank, canonical.receiverBank, canonical.accountWithInstitutionName, canonical.instructionsForCrdtrAgtCode, canonical.instructionsForCrdtrAgtText,"+ //6
	" canonical.msgRef, canonical.seqNo, canonical.msgHost from NgphCanonical canonical  where canonical.msgRef=?"; //3
		List list = getHibernateTemplate().find(query,msgRef.trim());			
		if(list!=null && !list.isEmpty() && list.size()>0){
			Object[] obj = (Object[]) list.get(0);
			System.out.println("List size is "+list.size());
			lcCanonicalDto.setLcNumber((String)obj[0]);
			lcCanonicalDto.setRelatedReference((String)obj[1]);
			lcCanonicalDto.setLcCurrency((String)obj[2]);
			lcCanonicalDto.setLcAmount((BigDecimal)obj[3]);
			lcCanonicalDto.setAmountPaidDate((Date)obj[4]);
			lcCanonicalDto.setChargesClaimed((String)obj[5]);
			lcCanonicalDto.setLcDispoDocs((String)obj[6]);
			lcCanonicalDto.setLcClaimAmount((BigDecimal)obj[7]);
			lcCanonicalDto.setAdviseThroughBankCode((String)obj[8]);
			lcCanonicalDto.setAdviseThroughBankAcc((String)obj[9]);
			lcCanonicalDto.setAdviseThroughBankLocation((String)obj[10]);
			lcCanonicalDto.setLcDispoDocs((String)obj[11]);
			lcCanonicalDto.setSenderBank((String)obj[12]);
			lcCanonicalDto.setAdvisingBank((String)obj[13]);
			lcCanonicalDto.setAdviseThroughBankName((String)obj[14]);
			lcCanonicalDto.setSendertoReceiverInformation((String)obj[15]);
			lcCanonicalDto.setSendertoReceiverInformation((String)obj[16]);
			lcCanonicalDto.setMsgRef((String)obj[17]);
			lcCanonicalDto.setSeqNo((String)obj[18]);
			lcCanonicalDto.setMsgHost((String)obj[19]);
		}
	return lcCanonicalDto;
}


public List displayIFSCCodes(String ifscCode, String bankName, String branchName) {
	return null;
}


public List<PartyDTO> getBankNameData(String bankName) {
	List<PartyDTO> bankNameList = new ArrayList<PartyDTO>();
	if(bankName == null && StringUtils.isEmpty(bankName)){
		bankName = "%";
	}else{
		bankName = "%"+bankName+"%";
	}
	List list = getHibernateTemplate().find("select distinct agentName from Parties where agentName like ? order by agentName",bankName);
	for(int i = 0 ;i<list.size();i++)
	{
		PartyDTO branchesDTO=  new PartyDTO();
		 branchesDTO.setPartyBankName((String)list.get(i));
		 bankNameList.add(branchesDTO);
	}
	return bankNameList;
}


public PartyDTO displayIFSCData(String ifscCode) {
	
	PartyDTO partyDto=  null;
	List list = getHibernateTemplate().find("select clearingSystemMemberId, agentName, partyAddress, cityName, distName, stateName, partyBranchName, micrCode, ifscType  from Parties where clearingSystemMemberId = ?",ifscCode);
	for(int i = 0 ;i<list.size();i++)
	{
		if(list!=null && list.size()>0)
		{
			partyDto = new PartyDTO();
			Object[] obj = (Object[]) list.get(0);
			partyDto.setIfscCode((String) obj[0]);
			partyDto.setPartyName((String) obj[1]);
			partyDto.setPartyAddress((String)obj[2]);
			partyDto.setCityName((String) obj[3]);
			partyDto.setDistricName((String) obj[4]);
			partyDto.setStateName((String) obj[5]);
			partyDto.setBranchName((String) obj[6]);
			partyDto.setMicrCode((Integer) obj[7]);
			partyDto.setIfscType((String) obj[8]);
			
		}
		 
	}
	return partyDto;
}


public LCCanonicalDto getAdviceofDiscrepancy(String msgRef) {
	
	LCCanonicalDto lcCanonicalDto = new LCCanonicalDto();
	String query = "select canonical.lcNo,canonical.relReference," +//2
	" canonical.msgCurrency, canonical.lcAdditionalCurrCode, canonical.msgAmount, canonical.lcAdditionalAmt, canonical.lcTotalAmtClaimed, canonical.lcChargesDeducted, canonical.lcChargesAdded, canonical.receiverBank," + //8
	" canonical.lcDiscrepancies, canonical.senderBank, canonical.accountWithInstitutionId, canonical.accountWithInstitutionAcct,  canonical.accountWithInstitutionLoc, canonical.accountWithInstitutionName," +//6
	" canonical.instructionsForCrdtrAgtCode, canonical.instructionsForCrdtrAgtText, canonical.msgRef, canonical.seqNo, canonical.msgHost from NgphCanonical canonical  where canonical.msgRef=? "; //5
		List list = getHibernateTemplate().find(query,msgRef.trim());			
		if(list!=null && !list.isEmpty() && list.size()>0){
			Object[] obj = (Object[]) list.get(0);
			lcCanonicalDto.setLcNumber((String)obj[0]);
			lcCanonicalDto.setRelatedReference((String)obj[1]);
			lcCanonicalDto.setCurrency((String)obj[2]);
			lcCanonicalDto.setLcCurrency((String)obj[3]);
			lcCanonicalDto.setPrincipalAmount((BigDecimal)obj[4]);
			lcCanonicalDto.setAdditionalAmount((BigDecimal)obj[5]);
			lcCanonicalDto.setTotalAmount((BigDecimal)obj[6]);
			lcCanonicalDto.setChargesDeducted((String)obj[7]);
			lcCanonicalDto.setChargesAdded((String)obj[8]);
			lcCanonicalDto.setAdvisingBank((String)obj[9]);
			lcCanonicalDto.setDiscrepancies((String)obj[10]);
			lcCanonicalDto.setIssuingBankCode((String)obj[11]);
			lcCanonicalDto.setAdviseThroughBankCode((String)obj[12]);
			lcCanonicalDto.setAdviseThroughBankAcc((String)obj[13]);
			lcCanonicalDto.setAdviseThroughBankLocation((String)obj[14]);
			lcCanonicalDto.setAdviseThroughBankName((String)obj[15]);
			String senderToReceiverCode = ((String)obj[16]);
			String senderToReceiverTest = ((String)obj[17]);
			if(!StringUtils.isEmpty(senderToReceiverTest))
			{
				if(!StringUtils.isEmpty(senderToReceiverCode))
				{
					lcCanonicalDto.setSendertoReceiverInformation(senderToReceiverCode+"\n"+senderToReceiverTest);
				}
				else
				{
					lcCanonicalDto.setSendertoReceiverInformation(senderToReceiverTest);
				}
				
			}
			lcCanonicalDto.setMsgRef((String)obj[18]);
			lcCanonicalDto.setSeqNo((String)obj[19]);
			lcCanonicalDto.setMsgHost((String)obj[20]);
		}
	return lcCanonicalDto;
}


public LCCanonicalDto getAdviceofPayment(String msgRef) {
	
	LCCanonicalDto lcCanonicalDto = new LCCanonicalDto();
	String query = "select canonical.lcNo, canonical.relReference," +//2
	" canonical.principalCurrency, canonical.lcAdditionalCurrCode, canonical.msgCurrency, canonical.princPrincipalAmt, canonical.lcAdditionalAmt, canonical.lcTotalAmtClaimed, canonical.principalDate, canonical.msgValueDate, canonical.lcChargesDeducted,  canonical.lcChargesAdded, canonical.receiverBank," + //11
	" canonical.senderBank, canonical.senderCorrespondentId, canonical.senderCorrespondent, canonical.senderCorrespondentLoc,  canonical.senderCorrespondentName, canonical.accountWithInstitutionId, canonical.accountWithInstitutionAccount, canonical.accountWithInstitutionLoc, canonical.accountWithInstitutionName," +//9
	" canonical.beneficiaryInstitutionPID, canonical.beneficiaryInstitutionName, canonical.beneficiaryBankCode, canonical.lcNarrative, canonical.instructionsForCrdtrAgtCode, canonical.instructionsForCrdtrAgtText, canonical.msgRef, canonical.seqNo, canonical.msgHost from NgphCanonical canonical  where canonical.msgRef=? "; //9
		List list = getHibernateTemplate().find(query,msgRef.trim());			
		if(list!=null && !list.isEmpty() && list.size()>0){
			Object[] obj = (Object[]) list.get(0);
			lcCanonicalDto.setLcNumber((String)obj[0]);
			lcCanonicalDto.setRelatedReference((String)obj[1]);
			lcCanonicalDto.setMsgCurrency((String)obj[2]);
			lcCanonicalDto.setLcCurrency((String)obj[3]);
			lcCanonicalDto.setCurrency((String)obj[4]);
			lcCanonicalDto.setPrincipalAmount((BigDecimal)obj[5]);
			lcCanonicalDto.setAdditionalAmount((BigDecimal)obj[6]);
			lcCanonicalDto.setTotalAmount((BigDecimal)obj[7]);
			lcCanonicalDto.setAmountPaidDate((Date)obj[8]);
			lcCanonicalDto.setTotalPaidDate((Date)obj[9]);	
			lcCanonicalDto.setChargesDeducted((String)obj[10]);
			lcCanonicalDto.setChargesAdded((String)obj[11]);
			lcCanonicalDto.setAdvisingBank((String)obj[12]);
			lcCanonicalDto.setIssuingBankCode((String)obj[13]);
			lcCanonicalDto.setReimbursingBankAcc((String)obj[14]);
			lcCanonicalDto.setReimbursingBankCode((String)obj[15]);
			lcCanonicalDto.setReimbersingBankLoc((String)obj[16]);
			lcCanonicalDto.setReimbursingBankNameandAddress((String)obj[17]);
			lcCanonicalDto.setAdviseThroughBankAcc((String)obj[18]);
			lcCanonicalDto.setAdviseThroughBankCode((String)obj[19]);
			lcCanonicalDto.setAdviseThroughBankLocation((String)obj[20]);
			lcCanonicalDto.setAdviseThroughBankName((String)obj[21]);	
			lcCanonicalDto.setBeneficiaryBankAcc((String)obj[22]);
			lcCanonicalDto.setBeneficiaryBankNameAndAddress((String)obj[23]);
			lcCanonicalDto.setBeneficiaryBankCode((String)obj[24]);
			lcCanonicalDto.setNarrative((String)obj[25]);	
			String senderToReceiverCode = ((String)obj[26]);
			String senderToReceiverTest = ((String)obj[27]);
			if(!StringUtils.isEmpty(senderToReceiverTest))
			{
				if(!StringUtils.isEmpty(senderToReceiverCode))
				{
					lcCanonicalDto.setSendertoReceiverInformation(senderToReceiverCode+"\n"+senderToReceiverTest);
				}
				else
				{
					lcCanonicalDto.setSendertoReceiverInformation(senderToReceiverTest);
				}
				
			}
			lcCanonicalDto.setMsgRef((String)obj[28]);
			lcCanonicalDto.setSeqNo((String)obj[29]);
			lcCanonicalDto.setMsgHost((String)obj[30]);
		}
	return lcCanonicalDto;

}





}
