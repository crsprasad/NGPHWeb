package com.logica.ngph.daoImpl;

import java.math.BigDecimal;
import java.sql.Clob;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.logica.ngph.common.ConstantUtil;
import com.logica.ngph.common.enums.EnumBgStatus;
import com.logica.ngph.common.enums.EnumLcStatus;
import com.logica.ngph.common.PaymentStatusEnum;
import com.logica.ngph.dao.LcReportsDao;
import com.logica.ngph.dtos.BgMastDto;
import com.logica.ngph.dtos.LcMastDto;

public class LcReportsDaoImpl extends HibernateDaoSupport implements LcReportsDao{
	static Logger logger = Logger.getLogger(LcReportsDaoImpl.class);

	
	public List<LcMastDto> getReportDate(String lcDirection,String query,String groupBy,String localIFSC,String branch,String tableName) {

		String fullQuery="";
		String fullQuery1="";
		String columnString="";
		String columnString1="";
		String localIFscQuery = "";
		List list = new ArrayList();
		if(lcDirection.equals("O")){
			if(branch.equals(ConstantUtil.ALL))
			{
				localIFscQuery = "";
			}else
				 localIFscQuery = " and lc.lcIssuingBank='"+localIFSC+"'";
			
		}else{
			if(branch.equals(ConstantUtil.ALL))
			{
				localIFscQuery = "";
			}else
				 localIFscQuery = " and lc.lcAdvisingBank='"+localIFSC+"'";
			
		}
		
		if(lcDirection.equals("O")){
			if(tableName.equals("RPT")){
				columnString = "select lc.compositeKeyForLcMast.lcNo,lc.lcType,lc.compositeKeyForLcMast.lcDirection,lc.lcIssueDate,lc.lcExpireDate,lc.lcAppicant,lc.lcBenificiary,lc.lcCurrency," +
				"lc.lcAmount,lc.lcStatus,lc.lcNarrative,lc.lcAdvisingBank,re.msgStatus,lc.lstModifiedTime from LcMast lc, ReportEntity re  where lc.msgRef = re.msgRef "+localIFscQuery+" ";
			}else if(tableName.equals("HIST")){
				columnString = "select lc.compositeKeyForLcMast.lcNo,lc.lcType,lc.compositeKeyForLcMast.lcDirection,lc.lcIssueDate,lc.lcExpireDate,lc.lcAppicant,lc.lcBenificiary,lc.lcCurrency," +
				"lc.lcAmount,lc.lcStatus,lc.lcNarrative,lc.lcAdvisingBank,reh.msgStatus from LcMast,lc.lstModifiedTime lc, ReportMessageHist reh  where lc.msgRef = reh.msgRef "+localIFscQuery+" ";
				
			}else if(tableName.equals("BOTH")){
				columnString = "select lc.compositeKeyForLcMast.lcNo,lc.lcType,lc.compositeKeyForLcMast.lcDirection,lc.lcIssueDate,lc.lcExpireDate,lc.lcAppicant,lc.lcBenificiary,lc.lcCurrency," +
				"lc.lcAmount,lc.lcStatus,lc.lcNarrative,lc.lcAdvisingBank,re.msgStatus,lc.lstModifiedTime from LcMast lc, ReportEntity re  where lc.msgRef = re.msgRef "+localIFscQuery+" ";
				columnString1 = "select lc.compositeKeyForLcMast.lcNo,lc.lcType,lc.compositeKeyForLcMast.lcDirection,lc.lcIssueDate,lc.lcExpireDate,lc.lcAppicant,lc.lcBenificiary,lc.lcCurrency," +
				"lc.lcAmount,lc.lcStatus,lc.lcNarrative,lc.lcAdvisingBank,reh.msgStatus,lc.lstModifiedTime from LcMast lc, ReportMessageHist reh  where lc.msgRef = reh.msgRef "+localIFscQuery+" ";
				
			}
		
			
		}else if(lcDirection.equals("I")){
				if(tableName.equals("RPT")){
					columnString = "select lc.compositeKeyForLcMast.lcNo,lc.lcType,lc.compositeKeyForLcMast.lcDirection,lc.lcIssueDate,lc.lcExpireDate,lc.lcAppicant,lc.lcBenificiary,lc.lcCurrency,lc.lcAmount,lc.lcStatus" +
					",lc.lcNarrative,lc.lcIssuingBank, re.msgStatus,lc.lstModifiedTime from LcMast lc, ReportEntity re  where lc.msgRef = re.msgRef "+localIFscQuery+"";
				}else if(tableName.equals("HIST")){
					columnString = "select lc.compositeKeyForLcMast.lcNo,lc.lcType,lc.compositeKeyForLcMast.lcDirection,lc.lcIssueDate,lc.lcExpireDate,lc.lcAppicant,lc.lcBenificiary,lc.lcCurrency,lc.lcAmount,lc.lcStatus" +
					",lc.lcNarrative,lc.lcIssuingBank, reh.msgStatus,lc.lstModifiedTime from LcMast lc, ReportMessageHist reh  where lc.msgRef = reh.msgRef "+localIFscQuery+"";
				}else if(tableName.equals("BOTH")){
					columnString = "select lc.compositeKeyForLcMast.lcNo,lc.lcType,lc.compositeKeyForLcMast.lcDirection,lc.lcIssueDate,lc.lcExpireDate,lc.lcAppicant,lc.lcBenificiary,lc.lcCurrency,lc.lcAmount,lc.lcStatus" +
					",lc.lcNarrative,lc.lcIssuingBank, re.msgStatus,lc.lstModifiedTime from LcMast lc, ReportEntity re  where lc.msgRef = re.msgRef "+localIFscQuery+"";
					columnString1 = "select lc.compositeKeyForLcMast.lcNo,lc.lcType,lc.compositeKeyForLcMast.lcDirection,lc.lcIssueDate,lc.lcExpireDate,lc.lcAppicant,lc.lcBenificiary,lc.lcCurrency,lc.lcAmount,lc.lcStatus" +
					",lc.lcNarrative,lc.lcIssuingBank, reh.msgStatus,lc.lstModifiedTime from LcMast lc, ReportMessageHist reh  where lc.msgRef = reh.msgRef "+localIFscQuery+"";
				}
			}
		
		List<LcMastDto> lcMastsList = new ArrayList();
		if(!tableName.equals("BOTH")){
			if(query.equals("")){
				if(localIFscQuery.equals(""))
					fullQuery = columnString+"AND lc.compositeKeyForLcMast.lcDirection = '"+lcDirection+"'";
				else
					fullQuery=columnString+" AND lc.compositeKeyForLcMast.lcDirection = '"+lcDirection+"'";
			}
			else{
				if(localIFscQuery.equals(""))
						fullQuery = columnString+"AND lc.compositeKeyForLcMast.lcDirection = '"+lcDirection+"' and "+query;
					else
						fullQuery=columnString+" AND lc.compositeKeyForLcMast.lcDirection = '"+lcDirection+"' and "+query;
			}
		}else{
			if(query.equals(""))
			{
				if(localIFscQuery.equals("")){
					fullQuery = columnString+"AND lc.compositeKeyForLcMast.lcDirection = '"+lcDirection +"'";
					fullQuery1 = columnString1+"AND lc.compositeKeyForLcMast.lcDirection = '"+lcDirection+"'";
				}else{
					fullQuery=columnString+" AND lc.compositeKeyForLcMast.lcDirection = '"+lcDirection+"'";
					fullQuery1=columnString1+" AND lc.compositeKeyForLcMast.lcDirection = '"+lcDirection+"'";
				}
			}
			else{
				if(localIFscQuery.equals("")){
					fullQuery = columnString+"AND lc.compositeKeyForLcMast.lcDirection = '"+lcDirection+"' and "+query;
					fullQuery1 = columnString1+"AND lc.compositeKeyForLcMast.lcDirection = '"+lcDirection+"' and "+query;
				}else{
					fullQuery=columnString+" AND lc.compositeKeyForLcMast.lcDirection = '"+lcDirection+"' and "+query;
					fullQuery1=columnString1+" AND lc.compositeKeyForLcMast.lcDirection = '"+lcDirection+"' and "+query;
				}
			}
		}
		
		if(tableName.equals("BOTH")){
			List list1 = getHibernateTemplate().find(fullQuery+" order by lc.lcIssueDate desc ");
			List list2 = getHibernateTemplate().find(fullQuery1+" order by lc.lcIssueDate desc ");
			list.addAll(list1);
			list.addAll(list2);
		}else{
			list = getHibernateTemplate().find(fullQuery+" order by lc.lcIssueDate desc ");
		}
		for(int i =0 ;i<list.size();i++){
			LcMastDto lcMast = new LcMastDto();
			Object[] obj = (Object[]) list.get(i);
			lcMast.setLcNo((String) obj[0]);
			lcMast.setLcType((String) obj[1]);
			lcMast.setLcDirection((String) obj[2]);
			if(obj[3]!=null){
			lcMast.setLcIssueDate( obj[3].toString().substring(0,10));
			}
			if(obj[4]!=null){
			lcMast.setLcExpireDate( obj[4].toString().substring(0,10));
			}
			lcMast.setLcAppicant((String) obj[5]);
			lcMast.setLcBenificiary((String) obj[6]);
			lcMast.setLcCurrency((String) obj[7]);
			lcMast.setLcAmount((BigDecimal) obj[8]);
			//lcMast.setLcStatus(Integer.parseInt(obj[9].toString()));
			if(obj[9]!=null){
				EnumLcStatus val = EnumLcStatus.findEnumByTag(obj[9].toString());
				 lcMast.setStatus(val.toString());
			 }
			
			//lcMast.setLcStatus(Integer.parseInt(obj[9].toString()));
			lcMast.setLcNarrative((String) obj[10]);
			lcMast.setLcAdvisingBank((String) obj[11]);
			if(obj[12]!=null){
				PaymentStatusEnum val = PaymentStatusEnum.findEnumByTag(obj[12].toString());
				 lcMast.setMsgStatus(val.toString());
			 }
			
			try {
				lcMast.setLstModifiedTime(dateChanger(obj[13] + ""));
			} catch (Exception e) {
				e.printStackTrace();
			}
			lcMastsList.add(lcMast);
		}
		return lcMastsList;
	}

	public String dateChanger(String aNewValue) throws ParseException{
		String dateconverted ="";
		if(aNewValue!=null){ 
		String  StrArr[] =    aNewValue.split("[.]");
		    dateconverted = StrArr[0];
		}
	       
		return dateconverted;
		
	}
	public List<BgMastDto> getBgReportDate(String lcDirection, String query,String groupBy,String localIFSC,String branch,String tableName) {
		String fullQuery="";
		String fullQuery1="";
		String columnString="";
		String columnString1="";
		String localIFscQuery = "";
		List list = new ArrayList();
		if(lcDirection.equals("O")){
			if(branch.equals(ConstantUtil.ALL))
			{
				localIFscQuery = "";
			}else
				 localIFscQuery = "bm.issuingBank='"+localIFSC+"'";
			
		}else{
			if(branch.equals(ConstantUtil.ALL))
			{
				localIFscQuery = "";
			}else
				 localIFscQuery = "bm.advisingBank ='"+localIFSC+"'";
			
		}
		if(lcDirection.equals("O")){
			if(tableName.equals("RPT")){
				columnString = "select bm.bgNumber,bm.noOfMsg,bm.bgIssueDate,bm.bgCreateType,bm.bgRuleCode,bm.bgRuleDesc,bm.bgDetails,bm.bgNarration,bm.bgAmount,bm.bgStatus,bm.advisingBank,bm.bgLastModifiedTime,bm.bgDirection,re.msgStatus" +
					" from BgMast bm,ReportEntity re  where bm.msgRef = re.msgRef "+localIFscQuery+" ";
			}else if(tableName.equals("HIST")){
				columnString = "select bm.bgNumber,bm.noOfMsg,bm.bgIssueDate,bm.bgCreateType,bm.bgRuleCode,bm.bgRuleDesc,bm.bgDetails,bm.bgNarration,bm.bgAmount,bm.bgStatus,bm.advisingBank,bm.bgLastModifiedTime,bm.bgDirection,reh.msgStatus" +
				" from BgMast bm,ReportMessageHist reh  where bm.msgRef = reh.msgRef "+localIFscQuery+" ";
			}else if(tableName.equals("BOTH")){
				columnString = "select bm.bgNumber,bm.noOfMsg,bm.bgIssueDate,bm.bgCreateType,bm.bgRuleCode,bm.bgRuleDesc,bm.bgDetails,bm.bgNarration,bm.bgAmount,bm.bgStatus,bm.advisingBank,bm.bgLastModifiedTime,bm.bgDirection,re.msgStatus" +
				" from BgMast bm,ReportEntity re  where bm.msgRef = re.msgRef "+localIFscQuery+" ";
				columnString1 = "select bm.bgNumber,bm.noOfMsg,bm.bgIssueDate,bm.bgCreateType,bm.bgRuleCode,bm.bgRuleDesc,bm.bgDetails,bm.bgNarration,bm.bgAmount,bm.bgStatus,bm.advisingBank,bm.bgLastModifiedTime,bm.bgDirection,reh.msgStatus" +
				" from BgMast bm,ReportMessageHist reh  where bm.msgRef = reh.msgRef "+localIFscQuery+" ";
			}
		}
		else if(lcDirection.equals("I")){
			if(tableName.equals("RPT")){
				columnString = "select bm.bgNumber,bm.noOfMsg,bm.bgIssueDate,bm.bgCreateType,bm.bgRuleCode,bm.bgRuleDesc,bm.bgDetails,bm.bgNarration,bm.bgAmount,bm.bgStatus,bm.issuingBank,bm.bgLastModifiedTime,bm.bgDirection,re.msgStatus" +
				" from BgMast bm,ReportEntity re  where bm.msgRef = re.msgRef "+localIFscQuery+" ";
			}else if(tableName.equals("HIST")){
				columnString = "select bm.bgNumber,bm.noOfMsg,bm.bgIssueDate,bm.bgCreateType,bm.bgRuleCode,bm.bgRuleDesc,bm.bgDetails,bm.bgNarration,bm.bgAmount,bm.bgStatus,bm.issuingBank,bm.bgLastModifiedTime,bm.bgDirection,reh.msgStatus" +
				" from BgMast bm,ReportMessageHist reh  where bm.msgRef = reh.msgRef "+localIFscQuery+" ";
			}else if(tableName.equals("BOTH")){
				columnString = "select bm.bgNumber,bm.noOfMsg,bm.bgIssueDate,bm.bgCreateType,bm.bgRuleCode,bm.bgRuleDesc,bm.bgDetails,bm.bgNarration,bm.bgAmount,bm.bgStatus,bm.issuingBank,bm.bgLastModifiedTime,bm.bgDirection,re.msgStatus" +
				" from BgMast bm,ReportEntity re  where bm.msgRef = re.msgRef "+localIFscQuery+" ";
				columnString1 = "select bm.bgNumber,bm.noOfMsg,bm.bgIssueDate,bm.bgCreateType,bm.bgRuleCode,bm.bgRuleDesc,bm.bgDetails,bm.bgNarration,bm.bgAmount,bm.bgStatus,bm.issuingBank,bm.bgLastModifiedTime,bm.bgDirection,reh.msgStatus" +
				" from BgMast bm,ReportMessageHist reh  where bm.msgRef = reh.msgRef "+localIFscQuery+" ";
			}
		}
		
		List<BgMastDto> bgMastsList = new ArrayList();
		if(!tableName.equals("BOTH")){
			if(query.equals(""))
			{
				if(localIFscQuery.equals(""))
					fullQuery = columnString+"AND bm.bgDirection = '"+lcDirection+"'";
				else
					fullQuery=columnString+" AND bm.bgDirection = '"+lcDirection+"'";
			}
			else{
				if(localIFscQuery.equals(""))
					fullQuery = columnString+"AND bm.bgDirection = '"+lcDirection+"' and "+query;
					else
						fullQuery=columnString+" AND bm.bgDirection = '"+lcDirection+"' and "+query;
			}
		}else{
			if(query.equals(""))
			{
				if(localIFscQuery.equals("")){
					fullQuery = columnString+"AND bm.bgDirection = '"+lcDirection+"'";
					fullQuery1 = columnString1+"AND bm.bgDirection = '"+lcDirection+"'";
				}else{
					fullQuery=columnString+" AND bm.bgDirection = '"+lcDirection+"'";
					fullQuery1=columnString1+" AND bm.bgDirection = '"+lcDirection+"'";
				
				}
			}
			else{
				if(localIFscQuery.equals("")){
					fullQuery = columnString+"AND bm.bgDirection = '"+lcDirection+"' and "+query;
					fullQuery1 = columnString1+"AND bm.bgDirection = '"+lcDirection+"' and "+query;
				}else{
						fullQuery=columnString+" AND bm.bgDirection = '"+lcDirection+"' and "+query;
						fullQuery1=columnString1+" AND bm.bgDirection = '"+lcDirection+"' and "+query;
				}
			}
		}
		if(tableName.equals("BOTH")){
			List list1 = getHibernateTemplate().find(fullQuery+" order by bm.bgIssueDate desc ");
			List list2 = getHibernateTemplate().find(fullQuery1+" order by bm.bgIssueDate desc ");
			list.addAll(list1);
			list.addAll(list2);	
			
		}else{
			list = getHibernateTemplate().find(fullQuery+" order by bm.bgIssueDate desc ");
		}
		for(int i =0 ;i<list.size();i++){
			BgMastDto bgMast = new BgMastDto();
			Object[] obj = (Object[]) list.get(i);
			bgMast.setBgNumber((String) obj[0]);
			/*String tempNoOFMsg =(String) obj[1];
			if(tempNoOFMsg!=null && !tempNoOFMsg.equals("")){
			bgMast.setNoOfMsg(Integer.parseInt(tempNoOFMsg));}*/
			bgMast.setBgIssueDate( obj[2].toString().substring(0,10));
			bgMast.setBgCreateType((String) obj[3]);
			bgMast.setBgRuleCode((String) obj[4]);
			bgMast.setBgRuleDesc((String) obj[5]);
			if(obj[6]!=null){
			Clob temp=(Clob)obj[6];
			String tempString;
			try {
				tempString = temp.getSubString(1, (int) temp.length());
				bgMast.setDetails(tempString);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
			bgMast.setBgNarration((String) obj[7]);
			bgMast.setBgAmount((BigDecimal) obj[8]);
			if(obj[9]!=null){
			EnumBgStatus bgStatus = EnumBgStatus.findEnumByTag(obj[9].toString());
			bgMast.setBgStatus(bgStatus.toString());
			}
			bgMast.setAdvisingBank((String) obj[10]);
			bgMast.setBgLastModifiedTime((Timestamp) obj[11]);
			bgMast.setBgDirection((String) obj[12]);
			if(obj[13]!=null){
				PaymentStatusEnum val = PaymentStatusEnum.findEnumByTag(obj[13].toString());
				bgMast.setMsgStatus(val.toString());
			 }
			bgMastsList.add(bgMast);
		}
		return bgMastsList;
	}

	
	public String getLocalBankIFSC(String userID) {
		List userBranch  = getHibernateTemplate().find("select userBranch from SecUsers where user =?",userID);
		
		List ifsc = null;
		if(userBranch!=null)
			ifsc=getHibernateTemplate().find("select branchIFSCCode from Branches where branchCode =?",userBranch.get(0));
		if(ifsc!=null && !ifsc.isEmpty() && ifsc.size()>0)
		{
			return ifsc.get(0).toString();
		}
		return null;
	}


	public List<BgMastDto> getBgRejectedReportData(String lcDirection,
			String query,String tablename, String localIFSC, String branch) {
		
		String fullQuery="";
		String fullQuery1="";
		String fullQuery2="";
		String columnString="";
		String columnString1="";
		String columnString2="";
		String localIFscQuery = "";
		List list = new ArrayList();
		if(lcDirection.equals("O")){
			if(branch.equals(ConstantUtil.ALL))
			{
				localIFscQuery = "";
			}else
				 localIFscQuery = " and bm.issuingBank='"+localIFSC+"'";
			
		}else{
			if(branch.equals(ConstantUtil.ALL))
			{
				localIFscQuery = "";
			}else
				 localIFscQuery = " and bm.advisingBank ='"+localIFSC+"'";
			
		}
		
		if(lcDirection.equals("O")){
			if(tablename.equals("RPT")){
				columnString = "select bm.bgNumber,bm.noOfMsg,bm.bgIssueDate,bm.bgCreateType,bm.bgRuleCode,bm.bgRuleDesc,bm.bgDetails,bm.bgNarration,bm.bgAmount,bm.bgStatus,bm.advisingBank,bm.bgLastModifiedTime,bm.bgDirection,re.msgStatus" +
					" from BgMast bm ,ReportEntity re  where bm.msgRef = re.msgRef AND re.msgStatus in(16,18,22,23) "+localIFscQuery+" "; 
			}else if(tablename.equals("HIST")){
				columnString = "select bm.bgNumber,bm.noOfMsg,bm.bgIssueDate,bm.bgCreateType,bm.bgRuleCode,bm.bgRuleDesc,bm.bgDetails,bm.bgNarration,bm.bgAmount,bm.bgStatus,bm.advisingBank,bm.bgLastModifiedTime,bm.bgDirection,reh.msgStatus" +
				" from BgMast bm ,ReportMessageHist reh  where bm.msgRef = reh.msgRef AND reh.msgStatus in(16,18,22,23) "+localIFscQuery+" ";
			}else if(tablename.equals("BOTH")){
				columnString1 = "select bm.bgNumber,bm.noOfMsg,bm.bgIssueDate,bm.bgCreateType,bm.bgRuleCode,bm.bgRuleDesc,bm.bgDetails,bm.bgNarration,bm.bgAmount,bm.bgStatus,bm.advisingBank,bm.bgLastModifiedTime,bm.bgDirection,re.msgStatus" +
				" from BgMast bm ,ReportEntity re  where bm.msgRef = re.msgRef AND re.msgStatus in(16,18,22,23) "+localIFscQuery+" ";
				columnString2 = "select bm.bgNumber,bm.noOfMsg,bm.bgIssueDate,bm.bgCreateType,bm.bgRuleCode,bm.bgRuleDesc,bm.bgDetails,bm.bgNarration,bm.bgAmount,bm.bgStatus,bm.advisingBank,bm.bgLastModifiedTime,bm.bgDirection,reh.msgStatus" +
				" from BgMast bm ,ReportMessageHist reh  where bm.msgRef = reh.msgRef AND reh.msgStatus in(16,18,22,23) "+localIFscQuery+" ";
			}
			}
			else if(lcDirection.equals("I")){
					if(tablename.equals("RPT")){
						columnString = "select bm.bgNumber,bm.noOfMsg,bm.bgIssueDate,bm.bgCreateType,bm.bgRuleCode,bm.bgRuleDesc,bm.bgDetails,bm.bgNarration,bm.bgAmount,bm.bgStatus,bm.issuingBank,bm.bgLastModifiedTime,bm.bgDirection,re.msgStatus" +
					" from  BgMast bm ,ReportEntity re  where bm.msgRef = re.msgRef AND re.msgStatus in(16,47,48) "+localIFscQuery+" "; 
					}else if(tablename.equals("HIST")){
						columnString = "select bm.bgNumber,bm.noOfMsg,bm.bgIssueDate,bm.bgCreateType,bm.bgRuleCode,bm.bgRuleDesc,bm.bgDetails,bm.bgNarration,bm.bgAmount,bm.bgStatus,bm.issuingBank,bm.bgLastModifiedTime,bm.bgDirection,reh.msgStatus" +
						" from  BgMast bm ,ReportMessageHist reh  where bm.msgRef = reh.msgRef AND reh.msgStatus in(16,47,48) "+localIFscQuery+" ";
					}else if(tablename.equals("BOTH")){
						columnString1 = "select bm.bgNumber,bm.noOfMsg,bm.bgIssueDate,bm.bgCreateType,bm.bgRuleCode,bm.bgRuleDesc,bm.bgDetails,bm.bgNarration,bm.bgAmount,bm.bgStatus,bm.issuingBank,bm.bgLastModifiedTime,bm.bgDirection,re.msgStatus" +
						" from  BgMast bm ,ReportEntity re  where bm.msgRef = re.msgRef AND re.msgStatus in(16,47,48) "+localIFscQuery+" ";
						columnString2 = "select bm.bgNumber,bm.noOfMsg,bm.bgIssueDate,bm.bgCreateType,bm.bgRuleCode,bm.bgRuleDesc,bm.bgDetails,bm.bgNarration,bm.bgAmount,bm.bgStatus,bm.issuingBank,bm.bgLastModifiedTime,bm.bgDirection,reh.msgStatus" +
						" from  BgMast bm ,ReportMessageHist reh  where bm.msgRef = reh.msgRef AND reh.msgStatus in(16,47,48) "+localIFscQuery+" ";
					}
			}
			List<BgMastDto> bgMastsList = new ArrayList();
			if(!tablename.equals("BOTH")){
				if(query.equals(""))
				{
					if(localIFscQuery.equals(""))
					fullQuery = columnString+"AND bm.bgDirection = '"+lcDirection+"'";
					else
						fullQuery=columnString+" AND bm.bgDirection = '"+lcDirection+"'";
				}
				else{
					if(localIFscQuery.equals(""))
						fullQuery = columnString+"AND bm.bgDirection = '"+lcDirection+"' and "+query;
						else
							fullQuery=columnString+" AND bm.bgDirection = '"+lcDirection+"' and "+query;
				}
			}else{
				if(query.equals(""))
				{
					if(localIFscQuery.equals("")){
					fullQuery1 = columnString1+"AND bm.bgDirection = '"+lcDirection+"'";
					fullQuery2 = columnString2+"AND bm.bgDirection = '"+lcDirection+"'";
					}else{
						fullQuery1=columnString1+" AND bm.bgDirection = '"+lcDirection+"'";
					fullQuery2=columnString2+" AND bm.bgDirection = '"+lcDirection+"'";
					
					}
				}
				else{
					if(localIFscQuery.equals("")){
						fullQuery1 = columnString1+"AND bm.bgDirection = '"+lcDirection+"' and "+query;
					fullQuery2 = columnString2+"AND bm.bgDirection = '"+lcDirection+"' and "+query;
					}else{
							fullQuery1=columnString1+" AND bm.bgDirection = '"+lcDirection+"' and "+query;
							fullQuery2=columnString2+" AND bm.bgDirection = '"+lcDirection+"' and "+query;
					}
				}
			}
			if(tablename.equals("BOTH")){
				List list1 = getHibernateTemplate().find(fullQuery1+" order by bm.bgIssueDate desc ");
				List list2 = getHibernateTemplate().find(fullQuery2+" order by bm.bgIssueDate desc ");
				list.addAll(list1);
				list.addAll(list2);	
			}else{
			 list = getHibernateTemplate().find(fullQuery+" order by bm.bgIssueDate desc ");
			}
			for(int i =0 ;i<list.size();i++){
				
				
				System.out.println(list.size());
				BgMastDto bgMast = new BgMastDto();
				Object[] obj = (Object[]) list.get(i);
				bgMast.setBgNumber((String) obj[0]);
				bgMast.setBgIssueDate( obj[2].toString().substring(0,10));
				bgMast.setBgCreateType((String) obj[3]);
				bgMast.setBgRuleCode((String) obj[4]);
				bgMast.setBgRuleDesc((String) obj[5]);
				if(obj[6]!=null){
				Clob temp=(Clob)obj[6];
				String tempString;
				try {
					tempString = temp.getSubString(1, (int) temp.length());
					bgMast.setDetails(tempString);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				}
				bgMast.setBgNarration((String) obj[7]);
				bgMast.setBgAmount((BigDecimal) obj[8]);
				if(obj[9]!=null){
				EnumBgStatus bgStatus = EnumBgStatus.findEnumByTag(obj[9].toString());
				bgMast.setBgStatus(bgStatus.toString());
				}
				bgMast.setAdvisingBank((String) obj[10]);
				bgMast.setBgLastModifiedTime((Timestamp) obj[11]);
				bgMast.setBgDirection((String) obj[12]);
				if(obj[13]!=null){
					PaymentStatusEnum val = PaymentStatusEnum.findEnumByTag(obj[13].toString());
					bgMast.setMsgStatus(val.toString());
				 }
				bgMastsList.add(bgMast);
			}

			
			return bgMastsList;
		
		
	}



	public List<LcMastDto> getLcRejectedReportData(String lcDirection,	String createQuery, String tablename, String localBankIFSC,
			String msgBranch) {
		String fullQuery="";
		String fullQuery1="";
		String fullQuery2="";
		String columnString="";
		String columnString1="";
		String columnString2="";
		String localIFscQuery = "";
		List list = new ArrayList();
		if(lcDirection.equals("O")){
			if(msgBranch.equals(ConstantUtil.ALL))
			{
				localIFscQuery = "";
			}else
				 localIFscQuery = " and lc.lcIssuingBank='"+localBankIFSC+"'";
			
		}else{
			if(msgBranch.equals(ConstantUtil.ALL))
			{
				localIFscQuery = "";
			}else
				 localIFscQuery = " and lc.lcAdvisingBank ='"+localBankIFSC+"'";
			
		}
		if(lcDirection.equals("O")){
			if(tablename.equals("RPT")){
			columnString = "select lc.compositeKeyForLcMast.lcNo,lc.lcType,lc.compositeKeyForLcMast.lcDirection,lc.lcIssueDate,lc.lcExpireDate,lc.lcAppicant,lc.lcBenificiary,lc.lcCurrency," +
					"lc.lcAmount,lc.lcStatus,lc.lcNarrative,lc.lcAdvisingBank,re.msgStatus,lc.lstModifiedTime from LcMast lc, ReportEntity re  where lc.msgRef = re.msgRef AND re.msgStatus in(16,18,22,23) "+localIFscQuery+" ";
			}else if(tablename.equals("HIST")){
				columnString = "select lc.compositeKeyForLcMast.lcNo,lc.lcType,lc.compositeKeyForLcMast.lcDirection,lc.lcIssueDate,lc.lcExpireDate,lc.lcAppicant,lc.lcBenificiary,lc.lcCurrency," +
				"lc.lcAmount,lc.lcStatus,lc.lcNarrative,lc.lcAdvisingBank,reh.msgStatus,lc.lstModifiedTime from LcMast lc, ReportMessageHist reh  where lc.msgRef = reh.msgRef AND reh.msgStatus in(16,18,22,23) "+localIFscQuery+" ";
			
			}else if(tablename.equals("BOTH")){
				columnString1 = "select lc.compositeKeyForLcMast.lcNo,lc.lcType,lc.compositeKeyForLcMast.lcDirection,lc.lcIssueDate,lc.lcExpireDate,lc.lcAppicant,lc.lcBenificiary,lc.lcCurrency," +
				"lc.lcAmount,lc.lcStatus,lc.lcNarrative,lc.lcAdvisingBank,re.msgStatus,lc.lstModifiedTime from LcMast lc, ReportEntity re  where lc.msgRef = re.msgRef AND re.msgStatus in(16,18,22,23) "+localIFscQuery+" ";
				columnString2= "select lc.compositeKeyForLcMast.lcNo,lc.lcType,lc.compositeKeyForLcMast.lcDirection,lc.lcIssueDate,lc.lcExpireDate,lc.lcAppicant,lc.lcBenificiary,lc.lcCurrency," +
				"lc.lcAmount,lc.lcStatus,lc.lcNarrative,lc.lcAdvisingBank,reh.msgStatus,lc.lstModifiedTime from LcMast lc, ReportMessageHist reh  where lc.msgRef = reh.msgRef AND reh.msgStatus in(16,18,22,23) "+localIFscQuery+" ";
			}
		
		}else if(lcDirection.equals("I")){
			if(tablename.equals("RPT")){
				columnString = "select lc.compositeKeyForLcMast.lcNo,lc.lcType,lc.compositeKeyForLcMast.lcDirection,lc.lcIssueDate,lc.lcExpireDate,lc.lcAppicant,lc.lcBenificiary,lc.lcCurrency,lc.lcAmount,lc.lcStatus" +
				",lc.lcNarrative,lc.lcIssuingBank,re.msgStatus,lc.lstModifiedTime from LcMast lc, ReportEntity re  where lc.msgRef = re.msgRef AND re.msgStatus in(16,47,48)  "+localIFscQuery+"";
			}else if(tablename.equals("HIST")){
				columnString = "select lc.compositeKeyForLcMast.lcNo,lc.lcType,lc.compositeKeyForLcMast.lcDirection,lc.lcIssueDate,lc.lcExpireDate,lc.lcAppicant,lc.lcBenificiary,lc.lcCurrency,lc.lcAmount,lc.lcStatus" +
				",lc.lcNarrative,lc.lcIssuingBank,reh.msgStatus,lc.lstModifiedTime from LcMast lc, ReportMessageHist reh  where lc.msgRef = reh.msgRef AND reh.msgStatus in(16,47,48)  "+localIFscQuery+"";
				
				}else if(tablename.equals("BOTH")){
					columnString1 = "select lc.compositeKeyForLcMast.lcNo,lc.lcType,lc.compositeKeyForLcMast.lcDirection,lc.lcIssueDate,lc.lcExpireDate,lc.lcAppicant,lc.lcBenificiary,lc.lcCurrency,lc.lcAmount,lc.lcStatus" +
					",lc.lcNarrative,lc.lcIssuingBank,re.msgStatus,lc.lstModifiedTime from LcMast lc, ReportEntity re  where lc.msgRef = re.msgRef AND re.msgStatus in(16,47,48)  "+localIFscQuery+" ";
					columnString2 ="select lc.compositeKeyForLcMast.lcNo,lc.lcType,lc.compositeKeyForLcMast.lcDirection,lc.lcIssueDate,lc.lcExpireDate,lc.lcAppicant,lc.lcBenificiary,lc.lcCurrency,lc.lcAmount,lc.lcStatus" +
					",lc.lcNarrative,lc.lcIssuingBank,reh.msgStatus,lc.lstModifiedTime from LcMast lc, ReportMessageHist reh  where lc.msgRef = reh.msgRef AND reh.msgStatus in(16,47,48)  "+localIFscQuery+"";
				}
			}
			List<LcMastDto> lcMastsList = new ArrayList();
			if(!tablename.equals("BOTH")){
				if(createQuery.equals("")){
					if(localIFscQuery.equals(""))
					fullQuery = columnString+"AND lc.compositeKeyForLcMast.lcDirection = '"+lcDirection+"'";
					else
						fullQuery=columnString+" AND lc.compositeKeyForLcMast.lcDirection = '"+lcDirection+"'";
				}
				else{
					if(localIFscQuery.equals(""))
						fullQuery = columnString+"AND lc.compositeKeyForLcMast.lcDirection = '"+lcDirection+"' and "+createQuery;
						else
							fullQuery=columnString+" AND lc.compositeKeyForLcMast.lcDirection = '"+lcDirection+"' and "+createQuery;
				}
			}else{
				if(createQuery.equals(""))
				{
					if(localIFscQuery.equals("")){
					fullQuery1 = columnString1+"AND lc.compositeKeyForLcMast.lcDirection = '"+lcDirection+"'";
					fullQuery2 = columnString2+"AND lc.compositeKeyForLcMast.lcDirection = '"+lcDirection+"'";
					}else{
						fullQuery1=columnString1+" AND lc.compositeKeyForLcMast.lcDirection = '"+lcDirection+"'";
						fullQuery2=columnString2+" AND lc.compositeKeyForLcMast.lcDirection = '"+lcDirection+"'";
					}
				}
				else{
					if(localIFscQuery.equals("")){
						fullQuery1 = columnString1+"AND lc.compositeKeyForLcMast.lcDirection = '"+lcDirection+"' and "+createQuery;
					fullQuery2 = columnString2+"AND lc.compositeKeyForLcMast.lcDirection = '"+lcDirection+"' and "+createQuery;
					}else{
							fullQuery1=columnString1+" AND lc.compositeKeyForLcMast.lcDirection = '"+lcDirection+"' and "+createQuery;
							fullQuery2=columnString2+" AND lc.compositeKeyForLcMast.lcDirection = '"+lcDirection+"' and "+createQuery;
					}
				}
			}
			
			if(tablename.equals("BOTH")){
				List list1 = getHibernateTemplate().find(fullQuery1+" order by lc.lcIssueDate desc ");
				List list2 = getHibernateTemplate().find(fullQuery2+" order by lc.lcIssueDate desc ");
				list.addAll(list1);
				list.addAll(list2);
			}else{
			 list = getHibernateTemplate().find(fullQuery+" order by lc.lcIssueDate desc ");
			}
			
			for(int i =0 ;i<list.size();i++){
				System.out.println(list.size());
				LcMastDto lcMast = new LcMastDto();
				Object[] obj = (Object[]) list.get(i);
				lcMast.setLcNo((String) obj[0]);
				lcMast.setLcType((String) obj[1]);
				lcMast.setLcDirection((String) obj[2]);
				if(obj[3]!=null){
				lcMast.setLcIssueDate( obj[3].toString().substring(0,10));
				}
				if(obj[4]!=null){
				lcMast.setLcExpireDate( obj[4].toString().substring(0,10));
				}
				lcMast.setLcAppicant((String) obj[5]);
				lcMast.setLcBenificiary((String) obj[6]);
				lcMast.setLcCurrency((String) obj[7]);
				lcMast.setLcAmount((BigDecimal) obj[8]);
				if(obj[9]!=null){
					EnumLcStatus val = EnumLcStatus.findEnumByTag(obj[9].toString());
					 lcMast.setStatus(val.toString());
				 }
				lcMast.setLcNarrative((String) obj[10]);
				lcMast.setLcAdvisingBank((String) obj[11]);
				if(obj[12]!=null){
					PaymentStatusEnum val = PaymentStatusEnum.findEnumByTag(obj[12].toString());
					 lcMast.setMsgStatus(val.toString());
				 }
				try {
					lcMast.setLstModifiedTime(dateChanger(obj[13] + ""));
				} catch (Exception e) {
					e.printStackTrace();
				}
				lcMastsList.add(lcMast);
			}
			return lcMastsList;
		
	}

}
