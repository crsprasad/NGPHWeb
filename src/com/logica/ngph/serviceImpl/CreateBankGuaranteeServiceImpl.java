package com.logica.ngph.serviceImpl;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.logica.ngph.Entity.MsgPolled;
import com.logica.ngph.Entity.NgphCanonical;
import com.logica.ngph.common.ConstantUtil;
import com.logica.ngph.common.dtos.InfoCanonical;
import com.logica.ngph.common.utils.NGPHUtil;
import com.logica.ngph.dao.CreateBankGuaranteeDao;
import com.logica.ngph.dtos.BankGuaranteeCoverDto;
import com.logica.ngph.dtos.BgMastDto;
import com.logica.ngph.dtos.CreateBankGuaranteeDto;
import com.logica.ngph.dtos.LCCanonicalDto;
import com.logica.ngph.service.CreateBankGuaranteeService;

public class CreateBankGuaranteeServiceImpl implements CreateBankGuaranteeService{
	
	static Logger logger = Logger.getLogger(CreateBankGuaranteeServiceImpl.class);
	CreateBankGuaranteeDao createBankGuaranteeDao;

	
	@Transactional(propagation = Propagation.REQUIRED)
	public void createBankGuarantee(CreateBankGuaranteeDto createBankGuaranteeDto, String userId) throws Exception{
		int bgStatus=0;
		String bgNumber="";
		if(createBankGuaranteeDto.getBgCreateType().equalsIgnoreCase("ISSUE")){
			bgNumber = createBankGuaranteeDto.getBgNumber();
			bgStatus=getBgStatus("CREATED");
		}else if(createBankGuaranteeDto.getBgCreateType().equalsIgnoreCase("REQUEST")){
			bgNumber = NGPHUtil.generateUUID();
			bgStatus=getBgStatus("REQUESTED");
		}
		
		String messageReference = NGPHUtil.generateUUID();
		String bgDirection ="O";
		
		try {
			NgphCanonical ngphCanonical = setDtoToCanonicalObject(createBankGuaranteeDto, messageReference, bgNumber, userId);
			MsgPolled msgPolled = getEntityObject(messageReference);
			createBankGuaranteeDao.createBG(createBankGuaranteeDto, bgStatus, messageReference,bgNumber, bgDirection,ngphCanonical,msgPolled);
			
		} catch (Exception sqlException) {
			logger.debug(sqlException);
			throw new Exception(ConstantUtil.DATA_ACCESS_PROBLEM);
		}
		
	}

	public MsgPolled getEntityObject(String msgRef)
	{
		MsgPolled msgPolled = new MsgPolled();
		msgPolled.setInTime(new Timestamp(Calendar.getInstance().getTime().getTime()));
		msgPolled.setMsgRef(msgRef);
		msgPolled.setPolledStatus("P");
		return msgPolled;
	}
	
	public void setCreateBankGuaranteeDao(CreateBankGuaranteeDao createBankGuaranteeDao) {
		this.createBankGuaranteeDao = createBankGuaranteeDao;
	}
	
	private Timestamp convertStringToTimestamp(Date date) {
		if(date != null){
			try{
				return new Timestamp(date.getTime());
			}catch(Exception ex){
				ex.printStackTrace();
				return null;
			}
		}else {
			return null;
		}
	}	
	
	private int getBgStatus(String key){
		
		Map<String, String> bgStatusMap = new HashMap<String, String>();
		bgStatusMap.put("CREATED", "1");
		bgStatusMap.put("REQUESTED", "2");
		bgStatusMap.put("CREATEDACK", "3");
		bgStatusMap.put("AMENDED", "4");
		bgStatusMap.put("AMENDED REGISTERED", "5");
		bgStatusMap.put("REDUCED", "6");
		bgStatusMap.put("REDUCED ACK", "7");
		bgStatusMap.put("RELEASE REQUEST", "8");
		bgStatusMap.put("RELEASED", "9");		
		
		int bgStatus = Integer.parseInt(bgStatusMap.get(key));
		return bgStatus;
		
	}
	
	public NgphCanonical setDtoToCanonicalObject(CreateBankGuaranteeDto createBgDto, String messageReference, String bgNumber, String userId){
		NgphCanonical ngphCanonical = new NgphCanonical();
		ngphCanonical.setMsgRef(messageReference);
		ngphCanonical.setLcPrevAdvRef(createBgDto.getBgCreateType());
		ngphCanonical.setLcIssueDt(convertStringToTimestamp(createBgDto.getBgDate()));
		ngphCanonical.setLcAppRulesCode(createBgDto.getBgRuleCode());
		ngphCanonical.setLcAppRulesDesc(createBgDto.getBgRuleNarration());
		ngphCanonical.setMsgHost("9999");
		ngphCanonical.setMsgChnlType("SFMS");
		ngphCanonical.setSrcMsgType("760");
		ngphCanonical.setSrcMsgSubType("XXX");
		ngphCanonical.setMsgStatus("1");
		ngphCanonical.setMsgDirection("O");
		ngphCanonical.setReceiverBank(createBgDto.getAdvisingBank());
		ngphCanonical.setReceivedTime(getCurrentTime());
		ngphCanonical.setLastModTime(getCurrentTime());
		ngphCanonical.setSequence(createBgDto.getSequence());
		ngphCanonical.setSequenceofTotal(createBgDto.getSequenceofTotal());
		ngphCanonical.setTxnReference(bgNumber);
		ngphCanonical.setLcNo(bgNumber);//20
		ngphCanonical.setSndrTxnId(bgNumber);
		ngphCanonical.setClrgSysReference(bgNumber);
		ngphCanonical.setSndrPymtPriority("12");
		ngphCanonical.setClrgChannel("SFMS");
		String[] branchDepartmentIfsc = createBankGuaranteeDao.getDept(userId).split("~");
		ngphCanonical.setLastModifiedUser(userId);
		ngphCanonical.setMsgBranch(branchDepartmentIfsc[2]);
		ngphCanonical.setMsgDept(branchDepartmentIfsc[1]);
		if(createBgDto.getIssuingBankCode()==null || createBgDto.getIssuingBankCode().isEmpty())
		{
			ngphCanonical.setSenderBank(branchDepartmentIfsc[0]);
			createBgDto.setIssuingBankCode(branchDepartmentIfsc[0]);
			
		}
		else
		{
			ngphCanonical.setSenderBank(createBgDto.getIssuingBankCode());
		}
		String bgDetails=insertNewLine(createBgDto.getBgDetails());
		if(StringUtils.isBlank(createBgDto.getRepair()) && StringUtils.isEmpty(createBgDto.getRepair()))
		{}
		else
		{
			ngphCanonical.setSeqNo(createBgDto.getSeqNo());
		}
		String part1="";
		String part2="";
		String part3="";
		String temp1="";
		String temp2="";
		if(bgDetails.length()>4000){
		part1= bgDetails.substring(0, 4000);
		temp1 = bgDetails.substring(4000,bgDetails.length());
		}else{
		part1=bgDetails;
		}
		 
		if(temp1.length()>4000){
		part2= temp1.substring(0,4000);
		temp2= temp1.substring(4000, temp1.length());
		}else{
		part2 = temp1;
		}

		if(!temp2.isEmpty()){
		part3 = temp2;
		}
		
		
		
		ngphCanonical.setLcDocsReq1(part1);
		ngphCanonical.setLcDocsReq2(part2);
		ngphCanonical.setLcAddnlCndt1(part3);
		
		String senderToReciever=createBgDto.getSenderToReceiverInformation();
		String senderToReceiverInformation="";
		if(senderToReciever!=null && !senderToReciever.isEmpty()){
			String [] strs = senderToReciever.split("\\/");
			if(strs.length==3){
				senderToReceiverInformation= strs[1];
				ngphCanonical.setInstructionsForCrdtrAgtCode(senderToReceiverInformation);
			}else{
				senderToReceiverInformation=senderToReciever;
				ngphCanonical.setInstructionsForCrdtrAgtText(senderToReceiverInformation);
			}
		}
		ngphCanonical.setNoofProcInteration(0);
		
		return ngphCanonical;
	}
	
	private Timestamp getCurrentTime(){
		java.util.Date 	str_date = Calendar.getInstance().getTime();
		java.sql.Timestamp timeStampDate = new Timestamp(str_date.getTime());
		return timeStampDate;
	}
	
	private String insertNewLine(String str){		 
		StringBuilder sb = new StringBuilder();
		String lineSeparator = System.getProperty("line.separator");
		for (int i = 0; i < str.length(); i++) { 
			if (i > 0 && (i % 65 == 0)) { 
				sb.append(lineSeparator);     
				}  
			sb.append(str.charAt(i)); 
			}  
		str = sb.toString();
		return str;
	}

	public CreateBankGuaranteeDto getBankGuarantee(String msgRef) {
		
		return createBankGuaranteeDao.getBankGuarantee(msgRef);
	}

	public void createAmendBG(CreateBankGuaranteeDto createBankGuaranteeDto, String userId) throws Exception {
	
		int bgStatus=0;
		String bgNumber="";
		if(createBankGuaranteeDto.getBgFurtherIdentification().equalsIgnoreCase("ISSUE")){
			bgNumber = createBankGuaranteeDto.getBgNumber();
			bgStatus=getBgStatus("CREATED");
		}else if(createBankGuaranteeDto.getBgFurtherIdentification().equalsIgnoreCase("REQUEST")){
			bgNumber = NGPHUtil.generateUUID();
			bgStatus=getBgStatus("REQUESTED");
		}
		String messageReference = NGPHUtil.generateUUID();
		String bgDirection ="O";
		try {
			NgphCanonical ngphCanonical = setAmendDtoToCanonicalObject(createBankGuaranteeDto, messageReference, bgNumber, userId);
			MsgPolled msgPolled = getEntityObject(messageReference);
			createBankGuaranteeDao.createAmendBG(createBankGuaranteeDto, bgStatus, messageReference,bgNumber, bgDirection,ngphCanonical,msgPolled);
		} catch (Exception sqlException) {
			logger.debug(sqlException);
			throw new Exception(ConstantUtil.DATA_ACCESS_PROBLEM);
		}
	}
	
	
	public NgphCanonical setAmendDtoToCanonicalObject(CreateBankGuaranteeDto createBgDto, String messageReference, String bgNumber, String userId)
	{
		NgphCanonical ngphCanonical = new NgphCanonical();
		ngphCanonical.setMsgRef(messageReference);
		ngphCanonical.setLcPrevAdvRef(createBgDto.getBgFurtherIdentification());//23
		ngphCanonical.setLcIssueDt(convertStringToTimestamp(createBgDto.getBgIssueDate()));//31C
		ngphCanonical.setLcAmndmntDt(convertStringToTimestamp(createBgDto.getBgDate()));//30
		ngphCanonical.setLcAmndmntNo(new BigDecimal(String.valueOf(createBgDto.getBgNoOfAmntmnt())));//26E
		ngphCanonical.setMsgHost("9999");
		ngphCanonical.setMsgChnlType("SFMS");
		ngphCanonical.setSrcMsgType("767");
		ngphCanonical.setSrcMsgSubType("XXX");
		ngphCanonical.setMsgStatus("1");
		ngphCanonical.setMsgDirection("O");
		ngphCanonical.setReceiverBank(createBgDto.getAdvisingBank());
		ngphCanonical.setReceivedTime(getCurrentTime());
		ngphCanonical.setLastModTime(getCurrentTime());
		ngphCanonical.setSequence(createBgDto.getSequence());
		ngphCanonical.setSequenceofTotal(createBgDto.getSequenceofTotal());
		ngphCanonical.setLcNo(bgNumber);//20
		ngphCanonical.setTxnReference(bgNumber);
		ngphCanonical.setSndrTxnId(bgNumber);
		ngphCanonical.setClrgSysReference(bgNumber);
		ngphCanonical.setRelReference(createBgDto.getRelatedReference());//21
		ngphCanonical.setSndrPymtPriority("12");
		ngphCanonical.setClrgChannel("SFMS");
		
	    
		if(StringUtils.isBlank(createBgDto.getRepair()) && StringUtils.isEmpty(createBgDto.getRepair()))
		{}
		else
		{
			ngphCanonical.setSeqNo(createBgDto.getSeqNo());
		}
		String[] branchDepartmentIfsc = createBankGuaranteeDao.getDept(userId).split("~");
		ngphCanonical.setLastModifiedUser(userId);
		ngphCanonical.setMsgBranch(branchDepartmentIfsc[2]);
		ngphCanonical.setMsgDept(branchDepartmentIfsc[1]);
		if(createBgDto.getIssuingBankCode()==null || createBgDto.getIssuingBankCode().isEmpty())
		{
			ngphCanonical.setSenderBank(branchDepartmentIfsc[0]);
			createBgDto.setIssuingBankCode(branchDepartmentIfsc[0]);
		}
		else
		{
			ngphCanonical.setSenderBank(createBgDto.getIssuingBankCode());
		}
		ngphCanonical.setSenderBank(branchDepartmentIfsc[0]);
		String bgDetails=insertNewLine(createBgDto.getBgDetails());

		String part1="";
		String part2="";
		String part3="";
		String temp1="";
		String temp2="";
		if(bgDetails.length()>4000){
		part1= bgDetails.substring(0, 4000);
		temp1 = bgDetails.substring(4000,bgDetails.length());
		}else{
		part1=bgDetails;
		}
		 
		if(temp1.length()>4000){
		part2= temp1.substring(0,4000);
		temp2= temp1.substring(4000, temp1.length());
		}else{
		part2 = temp1;
		}

		if(!temp2.isEmpty()){
		part3 = temp2;
		}
		
		
		
		ngphCanonical.setLcDocsReq1(part1);
		ngphCanonical.setLcDocsReq2(part2);
		ngphCanonical.setLcAddnlCndt1(part3);
		
		String senderToReciever=createBgDto.getSenderToReceiverInformation();
		String senderToReceiverInformation="";
		if(senderToReciever!=null && !senderToReciever.isEmpty()){
			String [] strs = senderToReciever.split("\\/");
			if(strs.length==3){
				senderToReceiverInformation= strs[1];
				ngphCanonical.setInstructionsForCrdtrAgtCode(senderToReceiverInformation);
			}else{
				senderToReceiverInformation=senderToReciever;
				ngphCanonical.setInstructionsForCrdtrAgtText(senderToReceiverInformation);
			}
		}
		ngphCanonical.setNoofProcInteration(0);
		
		return ngphCanonical;
	}
	
	public void createAckBG(CreateBankGuaranteeDto createBankGuaranteeDto, String userId) throws Exception {
	
		String bgNumber= createBankGuaranteeDto.getBgNumber();	
		String currency = createBankGuaranteeDto.getBgCurrency();
		String messageReference = NGPHUtil.generateUUID();
		String bgDirection ="O";
		try {
			NgphCanonical ngphCanonical = setAckDtoToCanonicalObject(createBankGuaranteeDto, messageReference, bgNumber, userId);
			MsgPolled msgPolled = getEntityObject(messageReference);
			createBankGuaranteeDao.createAckBG(createBankGuaranteeDto, messageReference,bgNumber, bgDirection, ngphCanonical, msgPolled);
		} catch (Exception sqlException) {
			logger.debug(sqlException);
			throw new Exception(ConstantUtil.DATA_ACCESS_PROBLEM);
		}
	}
	
	public NgphCanonical setAckDtoToCanonicalObject(CreateBankGuaranteeDto createBgDto, String messageReference, String bgNumber, String userId)
	{
		NgphCanonical ngphCanonical = new NgphCanonical();
		ngphCanonical.setMsgRef(messageReference);
		ngphCanonical.setMsgHost("9999");
		ngphCanonical.setMsgChnlType("SFMS");
		ngphCanonical.setSrcMsgType("768");
		ngphCanonical.setSrcMsgSubType("XXX");
		ngphCanonical.setMsgStatus("1");
		ngphCanonical.setMsgDirection("O");
		ngphCanonical.setReceivedTime(getCurrentTime());
		ngphCanonical.setLastModTime(getCurrentTime());
		ngphCanonical.setTxnReference(bgNumber);
		ngphCanonical.setLcNo(bgNumber);//20
		ngphCanonical.setRelReference(createBgDto.getRelatedReference());//21
		ngphCanonical.setSndrTxnId(bgNumber);
		ngphCanonical.setClrgSysReference(bgNumber);
		ngphCanonical.setSndrPymtPriority("12");
		ngphCanonical.setClrgChannel("SFMS");
		
		if(createBgDto.getBgCurrency()!=null && StringUtils.isNotEmpty(createBgDto.getBgCurrency()))
	     {
			 ngphCanonical.setMsgCurrency(createBgDto.getBgCurrency());//32a
	     }
		ngphCanonical.setLcToAmtClaimed(createBgDto.getBgChargeAmount());//32a
		ngphCanonical.setLcAccId(createBgDto.getBgAccountIdentification());//25
		ngphCanonical.setLcAckDt(convertStringToTimestamp(createBgDto.getDateofMessageBeingAcknowledged()));//30
		ngphCanonical.setMsgValueDate(convertStringToTimestamp(createBgDto.getBgDebitDate()));//32a
		//ngphCanonical.setAccountWithInstitutionAcct(createBgDto.getAdviseThroughBankAcc());
		ngphCanonical.setAccountWithInstitutionAccount(createBgDto.getAdviseThroughBankCode());//57a
		ngphCanonical.setAccountWithInstitutionLoc(createBgDto.getAccountwithPartyLocation());//57a
		ngphCanonical.setAccountWithInstitutionName(createBgDto.getAdviseThroughBankName());//57a
		ngphCanonical.setLcCharges(createBgDto.getChargesDetails());//71B
		ngphCanonical.setReceiverBank(createBgDto.getAdvisingBank());
		ngphCanonical.setSenderBank(createBgDto.getIssuingBankCode());
		if(StringUtils.isBlank(createBgDto.getRepair()) && StringUtils.isEmpty(createBgDto.getRepair()))
		{}
		else
		{
			ngphCanonical.setSeqNo(createBgDto.getSeqNo());
		}
		String[] branchDepartmentIfsc = createBankGuaranteeDao.getDept(userId).split("~");
		ngphCanonical.setLastModifiedUser(userId);
		ngphCanonical.setMsgBranch(branchDepartmentIfsc[2]);
		ngphCanonical.setMsgDept(branchDepartmentIfsc[1]);
		if(createBgDto.getIssuingBankCode()==null || createBgDto.getIssuingBankCode().isEmpty())
		{
			ngphCanonical.setSenderBank(branchDepartmentIfsc[0]);
			createBgDto.setIssuingBankCode(branchDepartmentIfsc[0]);
		}
		else
		{
			ngphCanonical.setSenderBank(createBgDto.getIssuingBankCode());
		}
		String senderToReciever=createBgDto.getSenderToReceiverInformation();
		String senderToReceiverInformation="";
		if(senderToReciever!=null && !senderToReciever.isEmpty()){
			String [] strs = senderToReciever.split("\\/");
			if(strs.length==3){
				senderToReceiverInformation= strs[1];
				ngphCanonical.setInstructionsForCrdtrAgtCode(senderToReceiverInformation);
			}else{
				senderToReceiverInformation=senderToReciever;
				ngphCanonical.setInstructionsForCrdtrAgtText(senderToReceiverInformation);//72
			}
		}
		
		ngphCanonical.setNoofProcInteration(0);
		return ngphCanonical;
	}

	public CreateBankGuaranteeDto viewAmendBg(String msgRef) {
		
		return createBankGuaranteeDao.getAmendBg(msgRef);
	}
	
public CreateBankGuaranteeDto viewAckBg(String msgRef) {
		
		return createBankGuaranteeDao.getAckBg(msgRef);
	}

	public List<BgMastDto> getAmendBGData(String referenceNumber) {
		try{
			
			return createBankGuaranteeDao.getAmendBGData(referenceNumber);
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public List<BgMastDto> getAckBGData(String bgNumber) {
		try{
			
			return createBankGuaranteeDao.getAckBGData(bgNumber);
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
 

	public CreateBankGuaranteeDto getAmendBGScreenData(String bgNumber) {
		try{			
			return createBankGuaranteeDao.getAmendBGScreenData(bgNumber);
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public CreateBankGuaranteeDto getAckBGScreenData(String bgNumber) {
		try{			
			return createBankGuaranteeDao.getAckBGScreenData(bgNumber);
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public void createReduction(CreateBankGuaranteeDto createBankGuaranteeDto,
			String userId) throws Exception {
		String messageReference = NGPHUtil.generateUUID();
		String bgDirection ="O";
		String bgNumber = null;
		
		try {
			bgNumber = createBankGuaranteeDto.getBgNumber();
			NgphCanonical ngphCanonical = setReductionToCanonicalObject(createBankGuaranteeDto, messageReference, bgNumber, userId);
			MsgPolled msgPolled = getEntityObject(messageReference);
			createBankGuaranteeDao.createReduction(createBankGuaranteeDto, messageReference,bgNumber, bgDirection,ngphCanonical,msgPolled);
			
		} catch (Exception sqlException) {
			logger.debug(sqlException);
			throw new Exception(ConstantUtil.DATA_ACCESS_PROBLEM);
		}
		
	}
	
	public NgphCanonical setReductionToCanonicalObject(CreateBankGuaranteeDto createBankGuaranteeDto, String messageReference, String bgNumber, String userId)
	{
		NgphCanonical ngphCanonical = new NgphCanonical();
		ngphCanonical.setMsgRef(messageReference);
		if (StringUtils.isBlank(createBankGuaranteeDto.getRepair())
				&& StringUtils.isEmpty(createBankGuaranteeDto.getRepair())) {
			ngphCanonical.setMsgHost("9999");

		} else {
			ngphCanonical.setMsgHost(createBankGuaranteeDto.getMsgHost());
		}
		ngphCanonical.setMsgChnlType("SFMS");
		ngphCanonical.setSrcMsgType("769");
		ngphCanonical.setSrcMsgSubType("XXX");
		ngphCanonical.setMsgStatus("1");
		ngphCanonical.setMsgDirection("O");
		ngphCanonical.setLastModTime(getCurrentTime());
		ngphCanonical.setTxnReference(bgNumber);//20
		ngphCanonical.setRelReference(createBankGuaranteeDto.getRelatedReference());//21
		ngphCanonical.setSndrTxnId(bgNumber);//20
		ngphCanonical.setClrgSysReference(bgNumber);//20
		ngphCanonical.setLcNo(bgNumber);//20
		ngphCanonical.setSndrPymtPriority("12");
		ngphCanonical.setClrgChannel("SFMS");
		ngphCanonical.setLcAccId(createBankGuaranteeDto.getBgAccountIdentification());//25
		ngphCanonical.setLcAmndmntDt(convertStringToTimestamp(createBankGuaranteeDto.getReductionDate()));//30
		
		if(createBankGuaranteeDto.getChargeAmtIdentifier()!=null && createBankGuaranteeDto.getChargeAmtIdentifier().equalsIgnoreCase("B"))//32B
		  {
			  if(createBankGuaranteeDto.getBgCurrency()!=null && StringUtils.isNotEmpty(createBankGuaranteeDto.getBgCurrency()))
			  {
				  ngphCanonical.setMsgCurrency(createBankGuaranteeDto.getBgCurrency());//32B
			  }
			 
			  ngphCanonical.setLcToAmtClaimed(createBankGuaranteeDto.getBgChargeAmount());//32B
		  }
		if(createBankGuaranteeDto.getChargeAmtIdentifier()!=null && createBankGuaranteeDto.getChargeAmtIdentifier().equalsIgnoreCase("D"))//32D
		  {
			  if(createBankGuaranteeDto.getBgCurrency()!=null && StringUtils.isNotEmpty(createBankGuaranteeDto.getBgCurrency()))
			  {
				  ngphCanonical.setMessageCurrency(createBankGuaranteeDto.getBgCurrency());//32D
			  }
			  
			  ngphCanonical.setLcChargesClaimed(createBankGuaranteeDto.getBgChargeAmount());//32D
			  ngphCanonical.setMsgValueDate(convertStringToTimestamp(createBankGuaranteeDto.getChargeDate()));
		  }
		 if(createBankGuaranteeDto.getReducedCurrency()!=null && StringUtils.isNotEmpty(createBankGuaranteeDto.getReducedCurrency()))//33B
	     {
			 ngphCanonical.setLcAdditionalCurrCode(createBankGuaranteeDto.getReducedCurrency());//33B
	     }
	     
		 ngphCanonical.setLcAdditionalAmt(createBankGuaranteeDto.getReducedAmt());//33B
		ngphCanonical.setInstructedCurrency(createBankGuaranteeDto.getOutstandingCurrency());//34B
		ngphCanonical.setLcTotalAmtClaimed(createBankGuaranteeDto.getOutstandingAmt());//34B
		ngphCanonical.setLcAddlAmts(createBankGuaranteeDto.getAmountSpecification());//39C
		ngphCanonical.setAccountWithInstitutionAccount(createBankGuaranteeDto.getAuthorisedBankCode());//57A
		ngphCanonical.setAccountWithInstitutionLoc(createBankGuaranteeDto.getAccountWithPartyLocation());//57B
		ngphCanonical.setAccountWithInstitutionName(createBankGuaranteeDto.getBgAccountWithNameandAddress());//57D
		ngphCanonical.setLcCharges(createBankGuaranteeDto.getChargesDetails());//71B
		String senderToReciever=createBankGuaranteeDto.getSenderToReceiverInformation();//72
		String senderToReceiverInformation="";
		if(senderToReciever!=null && !senderToReciever.isEmpty()){
			String [] strs = senderToReciever.split("\\/");
			if(strs.length==3){
				senderToReceiverInformation= strs[1];
				ngphCanonical.setInstructionsForCrdtrAgtCode(senderToReceiverInformation);
			}else{
				senderToReceiverInformation=senderToReciever;
				ngphCanonical.setInstructionsForCrdtrAgtText(senderToReceiverInformation);
			}
		}
		ngphCanonical.setReceiverBank(createBankGuaranteeDto.getAdvisingBank());
		String[] branchDepartmentIfsc = createBankGuaranteeDao.getDept(userId).split("~");
		ngphCanonical.setLastModifiedUser(userId);
		ngphCanonical.setMsgBranch(branchDepartmentIfsc[2]);
		ngphCanonical.setMsgDept(branchDepartmentIfsc[1]);
		if(createBankGuaranteeDto.getIssuingBankCode()!=null)
		{
			ngphCanonical.setSenderBank(createBankGuaranteeDto.getIssuingBankCode());
		}else
		{
			ngphCanonical.setSenderBank(branchDepartmentIfsc[0]);
		}
		
		if(StringUtils.isBlank(createBankGuaranteeDto.getRepair()) && StringUtils.isEmpty(createBankGuaranteeDto.getRepair()))
		{}
		else
		{
			ngphCanonical.setSeqNo(createBankGuaranteeDto.getSeqNo());
		}		
		
		
	
		ngphCanonical.setNoofProcInteration(0);
		return ngphCanonical;
	}
	
public CreateBankGuaranteeDto getAdviceReduction(String msgRef) {
		
		return createBankGuaranteeDao.getAdviceReduction(msgRef);
	}


public void createFreeFormatPayment(CreateBankGuaranteeDto createBankGuaranteeDto, String userId) throws Exception
	{
		String messageReference = NGPHUtil.generateUUID();
		
		try {
			String bgNumber = createBankGuaranteeDto.getBgNumber();
			InfoCanonical infoCan = new InfoCanonical();
			infoCan.setMsgRef(messageReference);
			infoCan.setSrcMsgType("799");
			infoCan.setSrcMsgSubType("XXX");
			infoCan.setInfo(createBankGuaranteeDto.getNarrative());
			infoCan.setDirection("O");
			infoCan.setPmtId_instrId(bgNumber);
			infoCan.setPmtId_relRef(createBankGuaranteeDto.getRelatedReference());
			String[] branchDepartmentIfsc = createBankGuaranteeDao.getDept(userId).split("~");
			infoCan.setBranch(branchDepartmentIfsc[2]);
			infoCan.setDept(branchDepartmentIfsc[1]);
			if(createBankGuaranteeDto.getIssuingBankCode()==null || createBankGuaranteeDto.getIssuingBankCode().isEmpty())
			{
				infoCan.setInstgagt_bkcd(branchDepartmentIfsc[0]);
			}
			else
			{
				infoCan.setInstgagt_bkcd(createBankGuaranteeDto.getIssuingBankCode());
			}
			infoCan.setInstdagt_bkcd(createBankGuaranteeDto.getAdvisingBank());
			infoCan.setDstChnl("SFMS");
			infoCan.setDstMsgType("799");
			infoCan.setDstMsgSubType("XXX");
			infoCan.setMsgMur("XXXXXXXXXXXXXXXX");
			if(StringUtils.isBlank(createBankGuaranteeDto.getRepair()) && StringUtils.isEmpty(createBankGuaranteeDto.getRepair())){
				String seq = createBankGuaranteeDao.createFreeFormatSeqNo(); 
				infoCan.setSeqNo(seq);
				
			}
			else
			{
				infoCan.setSeqNo(infoCan.getSeqNo());
			}
			MsgPolled msgPolled = getEntityObject(messageReference);
			createBankGuaranteeDao.createFreeFormatPayment(createBankGuaranteeDto, messageReference,infoCan,msgPolled);
			
		} catch (Exception sqlException) {
			logger.debug(sqlException);
			throw new Exception(ConstantUtil.DATA_ACCESS_PROBLEM);
		}
	}
	
	public CreateBankGuaranteeDto getFreeFormatMessage(String msgRef)throws Exception
	{
		 return  createBankGuaranteeDao.getFreeFormatMessage(msgRef);
	}

}
