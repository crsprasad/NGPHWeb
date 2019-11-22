package com.logica.ngph.daoImpl;


import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.logica.ngph.Entity.LcCommodity;
import com.logica.ngph.dao.LcOpenDao;
import com.logica.ngph.dtos.LCCanonicalDto;
import com.logica.ngph.dtos.LcMastDto;


public class LcOpenDaoImpl extends HibernateDaoSupport implements LcOpenDao{
	private static Logger logger = Logger.getLogger(LcOpenDaoImpl.class);

	
	public List<LCCanonicalDto> getLcDate(String lcNumber) {
		try{
			List<LCCanonicalDto> searchList = new ArrayList<LCCanonicalDto>();
			if(lcNumber == null){
				lcNumber = "%";
			}else{
				lcNumber = "%"+lcNumber+"%";
			}
			
			@SuppressWarnings("rawtypes")
			List list= getHibernateTemplate().find("select compositeKeyForLcMast.lcNo,msgRef from LcMast where compositeKeyForLcMast.lcNo like ? and lcStatus = 2 and compositeKeyForLcMast.lcDirection ='O'",lcNumber);
			for (int i = 0; i < list.size(); i++) {
				LCCanonicalDto lcCanonicalDto = new LCCanonicalDto();
	            Object[] obj = (Object[]) list.get(i);
	            lcCanonicalDto.setLcNumber(obj[0].toString());
	            lcCanonicalDto.setMsgRef((String) obj[0]);
	            searchList.add(lcCanonicalDto);
	        }
		return searchList;		
		}catch (Exception e) {
			e.printStackTrace();
			
			return null;
			// TODO: handle exception
		}

	}

	
	public LCCanonicalDto getLcScreenData(String lcNumber) {
		String query="select canonical.lcType,canonical.lcNo,canonical.lcPrevAdvRef, canonical.lcIssueDt,trunc(canonical.lcExpDt),canonical.lcExpPlace," +//6
				" canonical.lcPositiveTolerance,canonical.lcMaxCrAmt, canonical.lcAddlAmts, canonical.lcAuthBankCode, canonical.lcAuthBankAddr, canonical.lcAuthMode, canonical.initialDispatchPlace, canonical.lcDstn," +//8
				" canonical.lcLstShipDt, canonical.lcShipPeriod, canonical.lcShipTerms, canonical.lcDraftsAt, canonical.lcDraweeBnkPid, canonical.lcDraweeBnkCode, canonical.lcDraweeBnkAddr, canonical.lcMixedPymtDet," +//8
				"	 canonical.lcDefPymtDet, canonical.lcPartialShipment, canonical.lcTransShipment, concat(canonical.lcDocsReq1, canonical.lcDocsReq2), concat(canonical.lcAddnlCndt1, canonical.lcAddnlCndt2)," +//5
				" canonical.lcCharges, canonical.lcPrsntnPrd, canonical.lcConfrmInstrns, canonical.lcInstrnTopay, canonical.lcNarrative,canonical.msgRef,mast.lcBenificiary,mast.lcAppicant,canonical.accountWithInstitutionId,canonical.accountWithInstitution," +
				"canonical.accountWithInstitutionLoc,canonical.accountWithInstitutionName,canonical.instructionsForNextAgtText,canonical.orderingCustomerName," +
				"canonical.sendingInstId,canonical.sendingInst,canonical.sendingInstNameAdd,canonical.orderingCustAccount,canonical.beneficiaryCustAcct,canonical.beneficiaryCustomerName,canonical.senderCorrespondentId," +
				"canonical.senderCorrespondent,canonical.senderCorrespondentName,mast.lcAmount,mast.lcCurrency,canonical.lcNegativeTolerance,mast.lcAdvisingBank,canonical.accountWithInstitutionAccount,canonical.lcAddlAmts " +
				",canonical.lcDispatchPlace, canonical.finalDeliveryPlace,canonical.lcAppRulesDesc,canonical.accountWithInstitutionAcct,canonical.msgHost,canonical.seqNo,canonical.lcDraweeBnkAcct,canonical.lcAppRulesDesc ,canonical.accountWithInstitutionAcct,canonical.lcAppRulesCode," +
				"mast.lcIssuingBank,canonical.issunigBankNameAndAddress,canonical.nonIssuingBank,canonical.issuingBankPID," +
				"canonical.serviceID,canonical.mesgIsReturn,canonical.msgPDECount,canonical.grpSeq,canonical.pymntAcceptedTime,canonical.beneficiaryInstitutionPID,canonical.beneficiaryInstitutionAcct,canonical.beneficiaryInstitutionName,canonical.beneficiaryInstitution,canonical.lcNetAmtClaimed,canonical.lcToAmtClaimed,canonical.msgValueDate," +
				"canonical.orderingInstitutionAcct,canonical.instructionsForCrdtrAgtCode,canonical.instructionsForCrdtrAgtText" +
				" from NgphCanonical canonical ,LcMast mast where canonical.lcNo =? And mast.compositeKeyForLcMast.lcNo=? and mast.msgRef = canonical.msgRef"  ;//10
		List list = getHibernateTemplate().find(query,lcNumber,lcNumber);
		LCCanonicalDto lcCanonicalDto = new LCCanonicalDto();
		
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
        lcCanonicalDto.setAdviseThroughBankCode((String) obj[54]);
        lcCanonicalDto.setAdviseThroughBankLocation((String) obj[37]);
        lcCanonicalDto.setAdviseThroughBankName((String) obj[38]);
        
        lcCanonicalDto.setApplicantNameAddress((String) obj[40]);
        lcCanonicalDto.setApplicantBankpartyidentifier((String) obj[41]);
        lcCanonicalDto.setApplicantBankCode((String) obj[42]);
        lcCanonicalDto.setApplicantBankNameAddress((String) obj[43]);
        lcCanonicalDto.setApplicantAccount((String) obj[44]);
        lcCanonicalDto.setBeneficiaryAccount((String) obj[45]);
        lcCanonicalDto.setBeneficiaryNameAddress((String) obj[46]);
        lcCanonicalDto.setPartyIdentifier((String) obj[47]);
        lcCanonicalDto.setReimbursingBankCode((String) obj[48]);
        lcCanonicalDto.setReimbursingBankNameAddress((String) obj[49]);
        lcCanonicalDto.setLcAmount((BigDecimal) obj[50]);
        lcCanonicalDto.setLcCurrency((String) obj[51]);
        //Start With 53
        lcCanonicalDto.setAdvisingBank((String) obj[53]);
       
        lcCanonicalDto.setAdditionalAmountsCovered((String) obj[55]);
        lcCanonicalDto.setInitialDispatchPlace((String) obj[56]);
		lcCanonicalDto.setFinalDeliveryPlace((String) obj[57]);
		lcCanonicalDto.setApplicableNarrative((String) obj[58]);
		 lcCanonicalDto.setAdviseThroughBankAcc((String) obj[59]);
		 lcCanonicalDto.setMsgHost((String) obj[60]);
		 lcCanonicalDto.setSeqNo((String) obj[61]);
		 lcCanonicalDto.setDraweeBankAccount((String)obj[62]);
		 lcCanonicalDto.setApplicableNarrative((String) obj[63]);
			
		    lcCanonicalDto.setAdviseThroughBankAcc((String) obj[64]);
		    lcCanonicalDto.setApplicableRule((String) obj[65]);
		    lcCanonicalDto.setIssuingBankCode((String) obj[66]);
		    lcCanonicalDto.setIssunigBankNameAndAddress((String) obj[67]);
		    lcCanonicalDto.setNonIssuingBank((String) obj[68]);
		    lcCanonicalDto.setIssuingBankPID((String) obj[69]);
			
		    lcCanonicalDto.setServiceID((String)obj[70]);
			lcCanonicalDto.setMesgIsReturn((BigDecimal)obj[71]);
			lcCanonicalDto.setMsgPDECount((BigDecimal)obj[72]);
			lcCanonicalDto.setMsgGRPSeq((Integer)obj[73]);
			if(obj[74]!=null){
				lcCanonicalDto.setPymntAcceptedTime((Timestamp)obj[74]);
			}
			lcCanonicalDto.setNegotiatingBankPartyIdentifier((String) obj[75]);
			lcCanonicalDto.setNegotiatingBankAccount((String) obj[76]);
			lcCanonicalDto.setNegotiatingBankNameAndAddress((String) obj[77]);
			lcCanonicalDto.setNegotiatingBankCode((String)obj[78]);
			lcCanonicalDto.setLcNetAmtClaimed((BigDecimal) obj[79]);
			lcCanonicalDto.setTotalAmountClaimed(((BigDecimal) obj[80]));
			lcCanonicalDto.setMsgValueDate((Date) obj[81]);
			lcCanonicalDto.setIssuingBankAcc((String) obj[82]);
			/*if(obj[82]!=null)
			{
				String sendertoReciver =obj[82]+"";*/
				if(obj[83]!=null)
				{
					lcCanonicalDto.setSendertoReceiverInformation(obj[83]+"");
					//sendertoReciver = sendertoReciver+"/"+obj[83];
				}
				//lcCanonicalDto.setSendertoReceiverInformation(sendertoReciver);
			/*}else
			{
				
			}*/
		return lcCanonicalDto;
	}

	
	public List<LcCommodity> getCommodityDetails(String lcNumber) {
		List list= getHibernateTemplate().find("select lc.lcCommodity from LcCommodity lc , NgphCanonical Ms where lc.msgRef = Ms.msgRef and lc.lcNumber =?",lcNumber);
		List<LcCommodity> commoditiesList = new ArrayList<LcCommodity>();
		
		for(int i=0;i<list.size();i++)
		{
			LcCommodity commodity = new LcCommodity();
			
			
			commodity.setLcCommodity(list.get(i)+"");
			commodity.setLcId(i+"");
			//commodity.setLcCommodityRate((Double) obj[2]);
			commoditiesList.add(commodity);
			
			
		}
		return commoditiesList;
	}

	
	public String getLcStatus(String lcNumber) {
		List list = getHibernateTemplate().find("select lcStatus from LcMast where compositeKeyForLcMast.lcNo =?",lcNumber);
		if(list!=null)
		return list.get(0).toString();
		else
			return null;
			
	}

	
	public String saveChangeStatus(String lcNumber, String status) {
		try{
			SessionFactory fact=getHibernateTemplate().getSessionFactory();
			Session sess=fact.openSession();
			sess.beginTransaction();
			Connection con=sess.connection();

			String query="update TA_LC_MAST set LC_STATUS ="+Integer.parseInt(status)+" where LC_NUMBER = '"+lcNumber+"'";
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
		return "success";
	}

	
	public List<LCCanonicalDto> getLCNumber(String lcNumber) {
		List<LCCanonicalDto> searchList = new ArrayList<LCCanonicalDto>();
		if(lcNumber == null){
			lcNumber = "%";
		}else{
			lcNumber = "%"+lcNumber+"%";
		}
		@SuppressWarnings("rawtypes")
		List list= getHibernateTemplate().find("select compositeKeyForLcMast.lcNo,msgRef from LcMast where compositeKeyForLcMast.lcDirection = 'O' and compositeKeyForLcMast.lcNo like ?",lcNumber);
		for (int i = 0; i < list.size(); i++) {
			LCCanonicalDto lcCanonicalDto = new LCCanonicalDto();
            Object[] obj = (Object[]) list.get(i);
            lcCanonicalDto.setLcNumber(obj[0].toString());
            lcCanonicalDto.setMsgRef((String) obj[0]);
            searchList.add(lcCanonicalDto);
        }
	return searchList;		

	}

	
	public List<LcMastDto> displayLcNumber() {
		try{
			List<LcMastDto> searchList = new ArrayList<LcMastDto>();
			
			@SuppressWarnings("rawtypes")
			List list= getHibernateTemplate().find("select compositeKeyForLcMast.lcNo,msgRef,lcIssuingBank,lcAppicant,lcBenificiary,lcAmount,lcIssueDate,lcExpireDate from LcMast where compositeKeyForLcMast.lcDirection = 'I' order by lcIssueDate desc");
			for (int i = 0; i < list.size(); i++) {
				LcMastDto mast = new LcMastDto();
	            Object[] obj = (Object[]) list.get(i);
	            mast.setLcNo(obj[0].toString());
	           // CompositeKeyForLcMast compKey = new CompositeKeyForLcMast(obj[0].toString(), "I");
	            //lcCanonicalDto.setCompositeKeyForLcMast(compKey);
	            mast.setMsgRef((String) obj[1]);
	            mast.setLcIssuingBank((String) obj[2]);
	            mast.setLcAppicant((String) obj[3]);
	            mast.setLcBenificiary((String) obj[4]);
	            mast.setLcAmount((BigDecimal)obj[5]);
	            mast.setLcIssueDate(obj[6]+"");
	            mast.setLcExpireDate( obj[7]+"");
	            searchList.add(mast);
	        }
		return searchList;		
		}catch (Exception e) {
			e.printStackTrace();
			
			return null;
			// TODO: handle exception
		}

	}


	public List<LCCanonicalDto> getTranferDocumentaryData(String lcNumber,String status,String Direction) {
		try{
			String[] splitStatus = status.split(",");
			String statusQuery = "";
			for(int i = 0 ;i<splitStatus.length;i++)
			{
				if(i==splitStatus.length-1){
					statusQuery = statusQuery+"lcStatus = "+splitStatus[i];
				}else
				statusQuery = statusQuery+"lcStatus = "+splitStatus[i]+" or ";
			}
			List<LCCanonicalDto> searchList = new ArrayList<LCCanonicalDto>();
			if(lcNumber == null){
				lcNumber = "%";
			}else{
				lcNumber = "%"+lcNumber+"%";
			}
			
			List list= getHibernateTemplate().find("select compositeKeyForLcMast.lcNo,msgRef from LcMast where compositeKeyForLcMast.lcNo like ? and ("+statusQuery+")  and compositeKeyForLcMast.lcDirection =?",lcNumber,Direction);

			for (int i = 0; i < list.size(); i++) {
				LCCanonicalDto lcCanonicalDto = new LCCanonicalDto();
	            Object[] obj = (Object[]) list.get(i);
	            lcCanonicalDto.setLcNumber(obj[0].toString());
	            lcCanonicalDto.setMsgRef((String) obj[0]);
	            searchList.add(lcCanonicalDto);
	        }
		return searchList;		
		}catch (Exception e) {
			e.printStackTrace();
			
			return null;
			// TODO: handle exception
		}
	}

}
