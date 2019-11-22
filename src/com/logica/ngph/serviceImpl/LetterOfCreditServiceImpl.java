package com.logica.ngph.serviceImpl;

import com.logica.ngph.Entity.CompositeKeyForLcMast;
import com.logica.ngph.Entity.LcCommodity;
import com.logica.ngph.Entity.LcMast;
import com.logica.ngph.Entity.MsgPolled;
import com.logica.ngph.Entity.NgphCanonical;
import com.logica.ngph.Entity.Parties;
import com.logica.ngph.common.utils.NGPHUtil;
import com.logica.ngph.dao.AdviceLCPaymentDao;
import com.logica.ngph.dao.LetterOfCreditDao;
import com.logica.ngph.dtos.LCCanonicalDto;
import com.logica.ngph.dtos.PartyDTO;
import com.logica.ngph.service.LetterOfCreditService;
import com.logica.ngph.utils.ReportEntityUtil;
import com.logica.ngph.web.action.LetterOfCreditAction;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public class LetterOfCreditServiceImpl
  implements LetterOfCreditService
{
  LetterOfCreditDao letterOfCreditDao;
  AdviceLCPaymentDao adviceLCPaymentDao;
  private static Logger logger = Logger.getLogger(LetterOfCreditAction.class);
  
  public AdviceLCPaymentDao getAdviceLCPaymentDao()
  {
    return this.adviceLCPaymentDao;
  }
  
  public void setAdviceLCPaymentDao(AdviceLCPaymentDao adviceLCPaymentDao)
  {
    this.adviceLCPaymentDao = adviceLCPaymentDao;
  }
  
  public LetterOfCreditDao getLetterOfCreditDao()
  {
    return this.letterOfCreditDao;
  }
  
  public void setLetterOfCreditDao(LetterOfCreditDao letterOfCreditDao)
  {
    this.letterOfCreditDao = letterOfCreditDao;
  }
  
  @Transactional(propagation=Propagation.REQUIRED)
  public String saveLC(LCCanonicalDto canonicalDto, List<LcCommodity> commodityList, String status, String userID, String repair)
  {
    try
    {
      logger.info("Inside saveLC() in LetterOfCreditServiceImpl ");
      
      String msgRef = NGPHUtil.generateUUID();
      String lcNumber = canonicalDto.getLcNumber();
      System.out.println("Generated Message Reference :- " + msgRef + " LCNumber : -  " + lcNumber + " OLD Message Reference For This Lc Number :- " + canonicalDto.getMsgRef());
      

      NgphCanonical canonical = changeDtoToCanonical(canonicalDto, msgRef, status, userID, repair);
      logger.info("End saveLC() in LetterOfCreditServiceImpl ");
      return this.letterOfCreditDao.doCompleteTransaction(commodityList, changeDtoToLCMast(canonicalDto, msgRef, status, userID, repair), canonical, getEntityObject(msgRef), canonicalDto.getMsgRef(), status, msgRef, lcNumber, canonicalDto.getRepairMsgref(), repair, ReportEntityUtil.getReportEntityFromCanonical(canonical));
    }
    catch (Exception e)
    {
      logger.error("Exception Occured AT saveLC() In LetterOfcreditServiceImpl  :-  " + e);
      e.printStackTrace();
    }
    return null;
  }
  
  public LcMast changeDtoToLCMast(LCCanonicalDto canonicalDto, String msgRef, String status, String userID, String repair)
  {
    logger.info("Inside changeDtoToLCMast() in LetterOfCreditServiceImpl ");
    LcMast lcMast = new LcMast();
    CompositeKeyForLcMast compKeyLcMast = new CompositeKeyForLcMast(canonicalDto.getLcNumber(), "O",canonicalDto.getIssuingBankCode());
    lcMast.setLcType(canonicalDto.getLcType());
    lcMast.setCompositeKeyForLcMast(compKeyLcMast);
    

    lcMast.setLcIssueDate(getCurrentTime());
    lcMast.setLcExpireDate(convertStringToTimestamp(canonicalDto.getLcExpiryDate()));
    lcMast.setLcAppicant(canonicalDto.getApplicantNameAddress());
    if ((canonicalDto.getBeneficiary() != null) && (canonicalDto.getBeneficiary().length() > 0)) {
      lcMast.setLcBenificiary(canonicalDto.getBeneficiary());
    } else {
      lcMast.setLcBenificiary(canonicalDto.getBeneficiaryNameAddress());
    }
    lcMast.setLcNoofMsg(0);
    lcMast.setLcCurrency(canonicalDto.getLcCurrency());
    lcMast.setLcAmount(canonicalDto.getLcAmount());
    lcMast.setLcNarrative(canonicalDto.getNarrative());
    lcMast.setMsgRef(msgRef);
    
    lcMast.setLcAdvisingBank(canonicalDto.getAdvisingBank());
    String[] getDepartmantANDIFSC = this.letterOfCreditDao.getDept(userID).split("~");
    if (status.equals("TransferDocumentaryCredit")) {
      lcMast.getCompositeKeyForLcMast().setLcIssuingBank(canonicalDto.getIssuingBankCode());
    } else if ((StringUtils.isBlank(repair)) && (StringUtils.isEmpty(repair))) {
      lcMast.getCompositeKeyForLcMast().setLcIssuingBank(getDepartmantANDIFSC[0]);
    }
    if (status.equals("PreAdvice"))
    {
      lcMast.setLcStatus(1);
    }
    else if (status.equals("LcOpen"))
    {
      lcMast.setLcStatus(3);
    }
    else if (status.equals("PaymentAdvice"))
    {
      lcMast.setLcStatus(7);
    }
    else if (status.equals("AuthPaymentAdvice"))
    {
      lcMast.setLcStatus(9);
    }
    else if (status.equals("Amend_LC"))
    {
      lcMast.setLcStatus(5);
      
      lcMast.setLcAmount(canonicalDto.getNewLcAmount());
      if (canonicalDto.getLcAmndmntNo() != null) {
        lcMast.setLcNoofAmndment(canonicalDto.getLcAmndmntNo().intValue() + 1);
      } else {
        lcMast.setLcNoofAmndment(new Integer(1).intValue());
      }
      lcMast.setLcExpireDate(convertStringToTimestamp(canonicalDto.getNewAmendExpiryDate()));
    }
    else if (status.equals("TransferDocumentaryCredit"))
    {
      lcMast.setLcStatus(17);
    }
    else if (status.equals("ReimburesmentClaim"))
    {
      lcMast.setLcStatus(11);
    }
    else if (status.equals("AdviceOfDischarge"))
    {
      lcMast.setLcStatus(13);
    }
    else if (status.equals("AuthToPay"))
    {
      lcMast.setLcStatus(21);
    }
    logger.info("End changeDtoToLCMast() in LetterOfCreditServiceImpl ");
    return lcMast;
  }
  
  private Timestamp getCurrentTime()
  {
    java.util.Date str_date = Calendar.getInstance().getTime();
    Timestamp timeStampDate = new Timestamp(str_date.getTime());
    return timeStampDate;
  }
  
  public LcCommodity changeDtoTOLcCommodity(LCCanonicalDto canonicalDto, String msgRef, String lcNumber)
  {
    logger.info("Inside changeDtoTOLcCommodity() in LetterOfCreditServiceImpl ");
    LcCommodity commodity = new LcCommodity();
    commodity.setLcCommodity(canonicalDto.getCommodity());
    

    commodity.setLcNumber(canonicalDto.getLcNumber());
    commodity.setMsgRef(msgRef);
    logger.info("End changeDtoTOLcCommodity() in LetterOfCreditServiceImpl ");
    return commodity;
  }
  
  public NgphCanonical changeDtoToCanonical(LCCanonicalDto lcCanonicalDto, String msgRef, String status, String userID, String repair)
  {
    logger.info("Inside changeDtoToCanonical() in LetterOfCreditServiceImpl ");
    NgphCanonical canonical = new NgphCanonical();
    Timestamp currentTimestamp = getCurrentTime();
    canonical.setMsgRef(msgRef);
    
    
    if (status.equals("LcOpen"))//700msg
    {
    	canonical = callLcOpenDTOToCanonical (lcCanonicalDto, msgRef, status, userID, repair);
      
    }
    
    if (status.equals("Amend_LC"))//707msg
    {
    	canonical = callAmendLCDTOToCanonical (lcCanonicalDto, msgRef, status, userID, repair);
      
    }
    
    if (status.equals("LcAck"))//730msg
    {
    	canonical = callLcAckDTOToCanonical (lcCanonicalDto, msgRef, status, userID, repair);
      
    }
    if (status.equals("AdviceofRefusal"))//734msg
    {
    	canonical = callAdviceofRefusalDTOToCanonical (lcCanonicalDto, msgRef, status, userID, repair);
      
    }
    if (status.equals("AuthPaymentAdvice"))//740msg
    {
    	canonical = callAuthPaymentAdviceDTOToCanonical (lcCanonicalDto, msgRef, status, userID, repair);
      
    }
    if (status.equals("AdviceofDiscrepancy"))//750msg
    {
    	canonical = callAdviceofDiscrepancyDTOToCanonical (lcCanonicalDto, msgRef, status, userID, repair);
      
    }
    if (status.equals("AdviceofPayment"))//754msg
    {
    	canonical = callAdviceofPaymentDTOToCanonical (lcCanonicalDto, msgRef, status, userID, repair);
      
    }
    if (status.equals("PaymentAdvice"))//756msg
    {
    	canonical = callPaymentAdviceDTOToCanonical (lcCanonicalDto, msgRef, status, userID, repair);
      
    }
    
    
    if ((StringUtils.isBlank(repair)) && (StringUtils.isEmpty(repair)))
    {
      canonical.setMsgHost("9999");
      canonical.setLcIssueDt(getCurrentTime());
      //canonical.setMsgValueDate(currentTimestamp);
    }
    else
    {
      canonical.setLcIssueDt(convertStringToTimestamp(lcCanonicalDto.getIssueDate()));
      canonical.setMsgHost(lcCanonicalDto.getMsgHost());
      canonical.setSeqNo(lcCanonicalDto.getSeqNo());
     // canonical.setMsgValueDate(convertStringToTimestamp(lcCanonicalDto.getMsgValueDate()));
    }
    canonical.setMsgChnlType("SFMS");
    if (status.equals("PreAdvice"))
    {
      canonical.setSrcMsgType("705");
      canonical.setMsgRelStatus("1");
    }
    else if (status.equals("LcOpen"))
    {
      canonical.setSrcMsgType("700");
      canonical.setMsgRelStatus("3");
      canonical.setLcAppRulesDesc(lcCanonicalDto.getApplicableNarrative());
    }
    else if (status.equals("PaymentAdvice"))
    {
      canonical.setSrcMsgType("756");
      canonical.setMsgRelStatus("7");
    }
    else if (status.equals("AuthPaymentAdvice"))
    {
      canonical.setSrcMsgType("740");
      canonical.setMsgRelStatus("9");
    }
    else if (status.equals("Amend_LC"))
    {
      canonical.setSrcMsgType("707");
      canonical.setMsgRelStatus("5");
    }
    else if (status.equals("TransferDocumentaryCredit"))
    {
      canonical.setSrcMsgType("720");
      canonical.setMsgRelStatus("17");
    }
    else if (status.equals("ReimburesmentClaim"))
    {
      canonical.setSrcMsgType("742");
      canonical.setMsgRelStatus("11");
    }
    else if (status.equals("AdviceOfDischarge"))
    {
      canonical.setSrcMsgType("732");
      canonical.setMsgRelStatus("13");
    }
    else if (status.equals("AuthToPay"))
    {
      canonical.setSrcMsgType("752");
      //canonical.setMsgValueDate(convertStringToTimestamp(lcCanonicalDto.getMsgValueDate()));
      canonical.setMsgRelStatus("21");
    }
    else if (status.equals("LcAck"))
    {
      canonical.setSrcMsgType("730");
      canonical.setMsgRelStatus("21");
    }
    else if (status.equals("AdviceofRefusal"))
    {
      canonical.setSrcMsgType("734");
      canonical.setMsgRelStatus("21");
    }
    else if (status.equals("AdviceofDiscrepancy"))
    {
      canonical.setSrcMsgType("750");
      canonical.setMsgRelStatus("21");
    }
    canonical.setMsgStatus("15");
    

    canonical.setSrcMsgSubType("XXX");
    
    canonical.setMsgDirection("O");
    canonical.setReceivedTime(currentTimestamp);
    canonical.setLastModTime(currentTimestamp);
    canonical.setTxnReference(lcCanonicalDto.getLcNumber());
    canonical.setSndrTxnId(lcCanonicalDto.getLcNumber());
    canonical.setClrgSysReference(lcCanonicalDto.getLcNumber());
    canonical.setSndrPymtPriority("99");
    canonical.setClrgChannel("SFMS");
    if(lcCanonicalDto.getLcCurrency()!=null && StringUtils.isNotEmpty(lcCanonicalDto.getLcCurrency()))
    {
    	canonical.setMsgCurrency(lcCanonicalDto.getLcCurrency());
    }
    
    //canonical.setMsgAmount(lcCanonicalDto.getLcAmount());
    canonical.setPymntAcceptedTime(lcCanonicalDto.getPymntAcceptedTime());
    canonical.setInstructedCurrency(lcCanonicalDto.getLcCurrency());
    canonical.setOrderingCustomerName(lcCanonicalDto.getApplicantNameAddress());
    canonical.setOrderingType("I");
    //canonical.setSenderCorrespondentId(lcCanonicalDto.getPartyIdentifier());   
    canonical.setRelUid(lcCanonicalDto.getMsgRef());
    canonical.setSenderBank(lcCanonicalDto.getIssuingBankCode());
    canonical.setSendingInstId(lcCanonicalDto.getApplicantBankpartyidentifier());
    canonical.setSendingInst(lcCanonicalDto.getApplicantBankCode());
    canonical.setSendingInstNameAdd(lcCanonicalDto.getApplicantAccount());
    //canonical.setSendingInstNameAddress(lcCanonicalDto.getApplicantBankNameAddress());
    //canonical.setRelReference(lcCanonicalDto.getPresentingBanksReference());
    //canonical.setLcToAmtClaimed(lcCanonicalDto.getTotalAmountClaimed());
    canonical.setLcAmtPaid(lcCanonicalDto.getPaidAmount());
    
    if(status.equalsIgnoreCase("PaymentAdvice"))
    {}
    
    if (status.equals("AuthPaymentAdvice"))
    {}
    //canonical.setSenderCorrespondentAcct(lcCanonicalDto.getSenderCorrespontAcount());
   // canonical.setSenderCorrespondentLoc(lcCanonicalDto.getSendersCorrespondentLocation());
    //canonical.setReceiverCorrespondentId(lcCanonicalDto.getReceiversCorrespondentPartyIdentifier());
    //canonical.setReceiverCorrespondentAcct(lcCanonicalDto.getReceiverCorrespontAcount());
    //canonical.setReceiverCorrespondent(lcCanonicalDto.getReceiversCorrespondentCode());
   // canonical.setReceiverCorrespondentLoc(lcCanonicalDto.getReceiversCorrespondentLocation());
    canonical.setLcAppRulesCode(lcCanonicalDto.getApplicableRule());
    canonical.setLcAppRulesDesc(lcCanonicalDto.getApplicableNarrative());
    //canonical.setLcAccId(lcCanonicalDto.getAcountID());
   // canonical.setBeneficiaryInstitution(lcCanonicalDto.getNegotiatingBankCode());
    //canonical.setBeneficiaryInstitutionName(lcCanonicalDto.getNegotiatingBankNameAndAddress());
    //canonical.setBeneficiaryInstitutionAcct(lcCanonicalDto.getNegotiatingBankAccount());
    canonical.setLcDraweeBnkAcct(lcCanonicalDto.getDraweeBankAccount());
    canonical.setLcDefPymtDet(lcCanonicalDto.getDeferredPaymentDetails());
    canonical.setChargeBearer(lcCanonicalDto.getReimbursingBanksCharges());
    if (status.equals("AuthPaymentAdvice"))
    {
      canonical.setLcCharges(lcCanonicalDto.getOtherCharges());
      canonical.setMsgAmount(lcCanonicalDto.getCreditAmount());
    }
    //canonical.setBeneficiaryInstitutionPID(lcCanonicalDto.getNegotiatingBankPartyIdentifier());
    canonical.setLcAmndmntIncAmt(lcCanonicalDto.getIncreaseAmendAmount());
    canonical.setLcAmndmntDecAmt(lcCanonicalDto.getDecreaseAmendAmount());
    canonical.setLcAmndmntDt(convertStringToTimestamp(lcCanonicalDto.getAmendmentDate()));
    canonical.setLcAmndmntOldAmt(lcCanonicalDto.getOldLcAmount());
    canonical.setLcOldExpDt(convertStringToTimestamp(lcCanonicalDto.getOldAmendExpiryDate()));
    if (status.equals("Amend_LC"))
	    {
    	
}
    canonical.setIssuingBankPID(lcCanonicalDto.getIssuingBankPID());
    canonical.setIssunigBankNameAndAddress(lcCanonicalDto.getIssunigBankNameAndAddress());
    //canonical.setOrderingInstitution(lcCanonicalDto.getIssuingBankCode());
    canonical.setNonIssuingBank(lcCanonicalDto.getNonIssuingBank());
    canonical.setComments(lcCanonicalDto.getComment());
    canonical.setServiceID(lcCanonicalDto.getServiceID());
    canonical.setMesgIsReturn(lcCanonicalDto.getMesgIsReturn());
    canonical.setMsgPDECount(lcCanonicalDto.getMsgPDECount());
    canonical.setGrpSeq(lcCanonicalDto.getMsgGRPSeq());
    canonical.setLcNetAmtClaimed(lcCanonicalDto.getLcNetAmtClaimed());
    String[] getDepartmant = this.letterOfCreditDao.getDept(userID).split("~");
    canonical.setLastModifiedUser(userID);
    canonical.setMsgBranch(getDepartmant[2]);
    canonical.setMsgDept(getDepartmant[1]);
    if(lcCanonicalDto.getIssuingBankCode()==null || lcCanonicalDto.getIssuingBankCode().isEmpty())
	{
	  	canonical.setSenderBank(getDepartmant[0]);
	}
	else
	{
		canonical.setSenderBank(lcCanonicalDto.getIssuingBankCode());
	}
    if (status.equalsIgnoreCase("LcAck"))
    {}
    if (status.equalsIgnoreCase("AdviceofRefusal"))
    {}
    if (status.equalsIgnoreCase("AdviceofDiscrepancy"))
    {}
    if (status.equalsIgnoreCase("AdviceofPayment"))
    {}
    logger.info("End changeDtoToCanonical() in LetterOfCreditServiceImpl ");
    canonical.setNoofProcInteration(0);
    return canonical;
  }
  
  public void changeStatus(String msgRef)
  {
    this.letterOfCreditDao.changeStatus(msgRef);
  }
  
  private java.sql.Date convertStringToDate(java.util.Date date)
  {
    if (date != null) {
      try
      {
        return new java.sql.Date(date.getTime());
      }
      catch (Exception ex)
      {
        ex.printStackTrace();
        return null;
      }
    }
    return null;
  }
  
  private Timestamp convertStringToTimestamp(java.util.Date date)
  {
    if (date != null) {
      try
      {
        return new Timestamp(date.getTime());
      }
      catch (Exception ex)
      {
        ex.printStackTrace();
        return null;
      }
    }
    return null;
  }
  
  public List<Parties> getIFSCCodeList(String ifscCode, String userID)
  {
    try
    {
      return this.letterOfCreditDao.getIFSCCode(ifscCode, userID);
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    return null;
  }
  
  public MsgPolled getEntityObject(String msgRef)
  {
    logger.info("Inside getEntityObject() in LetterOfCreditServiceImpl ");
    MsgPolled msgPolled = new MsgPolled();
    msgPolled.setInTime(new Timestamp(Calendar.getInstance().getTime().getTime()));
    msgPolled.setMsgRef(msgRef);
    msgPolled.setPolledStatus("P");
    logger.info("End getEntityObject() in LetterOfCreditServiceImpl ");
    return msgPolled;
  }
  
  public Boolean checkIFSC(String toCheck)
  {
    return this.letterOfCreditDao.checkIFSC(toCheck);
  }
  
  public List<Parties> getIFSCCodeList(String ifscCode, String flag, String required)
  {
    try
    {
      return this.letterOfCreditDao.getIFSCCodeList(ifscCode, flag);
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    return null;
  }
  
  public String getLcOpenFlagForInsertOrUpdate()
  {
    try
    {
      return this.letterOfCreditDao.getLcOpenFlagForInsertOrUpdate();
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    return null;
  }
  
  public Boolean isLcNumberExist(String lcNumber)
  {
    try
    {
      return this.letterOfCreditDao.isLcNumberExist(lcNumber);
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    return null;
  }
  
  public Boolean isLcStatusTwo(String lcNumber)
  {
    try
    {
      return this.letterOfCreditDao.isLcStatusTwo(lcNumber);
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    return null;
  }
  
  public LCCanonicalDto getPreAdviceRepair(String msgRef)
  {
    return this.letterOfCreditDao.getPreAdviceRepair(msgRef);
  }
  
  public Boolean getstatusForLCNumber(String msgRef)
  {
    return this.letterOfCreditDao.getstatusForLCNumber(msgRef);
  }
  
  public String getDept(String userID)
  {
    String[] localIfsc = this.letterOfCreditDao.getDept(userID).split("~");
    String ifsc = null;
    if (localIfsc != null) {
      ifsc = localIfsc[0];
    }
    return ifsc;
  }
  
  public List getListOfIFSC(String code)
  {
    return this.letterOfCreditDao.getListOfIFSC(code);
  }
  
  public void saveIFSC(PartyDTO dto)
  {
    this.letterOfCreditDao.saveIFSC(dto);
  }
  
  public boolean checkPartyIFSC(String ifsc)
  {
    return this.letterOfCreditDao.checkPartyIFSC(ifsc);
  }
  
  public LCCanonicalDto getLCAckRepair(String msgRef)
  {
    return this.letterOfCreditDao.getLCAckRepair(msgRef);
  }
  
  public LCCanonicalDto getAdviceofRefusal(String msgRef)
  {
    return this.letterOfCreditDao.getAdviceofRefusal(msgRef);
  }
  
  public List displayIFSCCodes(String ifscCode, String bankName, String branchName)
  {
    return this.letterOfCreditDao.displayIFSCCodes(ifscCode, bankName, branchName);
  }

  public List<PartyDTO> getBankNameData(String bankName) 
  {
	return this.letterOfCreditDao.getBankNameData(bankName);
  }

  public PartyDTO displayIFSCData(String ifscCode) {
	
	return this.letterOfCreditDao.displayIFSCData(ifscCode);
  }
  
  public LCCanonicalDto getAdviceofDiscrepancy(String msgRef)
  {
    return this.letterOfCreditDao.getAdviceofDiscrepancy(msgRef);
  }

  public LCCanonicalDto getAdviceofPayment(String msgRef)
  {
	return this.letterOfCreditDao.getAdviceofPayment(msgRef);
  }
  
  
  public NgphCanonical callLcOpenDTOToCanonical(LCCanonicalDto lcCanonicalDto, String msgRef, String status, String userID, String repair)
  {
	System.out.println("Inside callLcOpenDTOToCanonical() in LetterOfCreditServiceImpl ");
    NgphCanonical canonical = new NgphCanonical();
    Timestamp currentTimestamp = getCurrentTime();
    canonical.setMsgRef(msgRef);
  
 //Start :: Added for Mizuho bank UI changes for 700 message 
    
    canonical.setSequence(lcCanonicalDto.getSequence());//27
    canonical.setSequenceofTotal(lcCanonicalDto.getSequenceofTotal());//27
    canonical.setLcType(lcCanonicalDto.getLcType());//40A
    canonical.setLcNo(lcCanonicalDto.getLcNumber());//20
    canonical.setLcPrevAdvRef(lcCanonicalDto.getLcPresdvice());//23
    canonical.setLcIssueDt(convertStringToTimestamp(lcCanonicalDto.getIssueDate()));//31C
    canonical.setLcAppRulesCode(lcCanonicalDto.getApplicableRule());//40E
    canonical.setLcExpDt(convertStringToTimestamp(lcCanonicalDto.getLcExpiryDate()));//31D
    canonical.setLcExpPlace(lcCanonicalDto.getLcExpirePlace());//31D
    //canonical.setApplicentIdentifier(lcCanonicalDto.getApplicentIdentifier());//51a
    canonical.setSendingInstId(lcCanonicalDto.getApplicantBankCode());//51a
    canonical.setSendingInstNameAddress(lcCanonicalDto.getApplicentBankNameandAddr());//51a
    canonical.setOrderingCustomerName(lcCanonicalDto.getApplicantNameAddress());//50
    //canonical.setBeneficiaryCustAcct(lcCanonicalDto.getBeneficiaryAccount());//59
    canonical.setBeneficiaryCustomerName(lcCanonicalDto.getBeneficiaryNameAddress());//59
    if(lcCanonicalDto.getLcCurrency()!=null && StringUtils.isNotEmpty(lcCanonicalDto.getLcCurrency()))
    {
    	canonical.setMsgCurrency(lcCanonicalDto.getLcCurrency());//32B
    }
   
    
    canonical.setMsgAmount(lcCanonicalDto.getLcAmount());//32B
    
    if ((lcCanonicalDto.getPositiveTolerance() != null) && (StringUtils.isNotEmpty(lcCanonicalDto.getPositiveTolerance())) && (StringUtils.isNotEmpty(lcCanonicalDto.getNegativeTolerance())) && (lcCanonicalDto.getNegativeTolerance() != null))
    {
      canonical.setLcPositiveTolerance(Integer.parseInt(lcCanonicalDto.getPositiveTolerance()));//39A
      canonical.setLcNegativeTolerance(Integer.parseInt(lcCanonicalDto.getNegativeTolerance()));//39A
    }
    canonical.setLcMaxCrAmt(lcCanonicalDto.getMaximumCreditAmount());//39B
    canonical.setLcAddlAmts(lcCanonicalDto.getAdditionalAmounts());//39C
    //canonical.setAvailableIdentifier(lcCanonicalDto.getAvailableIdentifier());//41a
    canonical.setLcAuthBankCode(lcCanonicalDto.getAuthorisedBankCode());//41a
    canonical.setLcAuthMode(lcCanonicalDto.getAuthorisationMode());//41a
    canonical.setLcAuthBankAddr(lcCanonicalDto.getAuthorisedAndAddress());//41a
    canonical.setLcDraftsAt(lcCanonicalDto.getDraftsAt());//42C
   // canonical.setLcDraweeBnkPid(lcCanonicalDto.getDraweeIdentifier());//42a
    canonical.setLcDraweeBnkCode(lcCanonicalDto.getDraweeBankCode());//42a
    canonical.setLcDraweeBnkAddr(lcCanonicalDto.getDraweeBankNameAddress());//42a
    canonical.setLcMixedPymtDet(lcCanonicalDto.getMixedPaymentDetails());//42M
    canonical.setLcDefPymtDet(lcCanonicalDto.getDeferredPaymentDetails());//42P
    canonical.setLcPartialShipment(lcCanonicalDto.getPartialShipments());//43P
    canonical.setLcTransShipment(lcCanonicalDto.getTranshipment());//43T
    canonical.setLcDispatchPlace(lcCanonicalDto.getInitialDispatchPlace());//44A
    canonical.setInitialDispatchPlace(lcCanonicalDto.getGoodsLoadingDispatchPlace());//44E
    canonical.setLcDstn(lcCanonicalDto.getGoodsDestination());//44F
    canonical.setFinalDeliveryPlace(lcCanonicalDto.getFinalDeliveryPlace());//44B
    canonical.setLcLstShipDt(convertStringToTimestamp(lcCanonicalDto.getLatestDateofShipment()));//44C
    canonical.setLcShipPeriod(lcCanonicalDto.getShipmentPeriod());//44D
    String descriptionOfGoods = lcCanonicalDto.getDescriptionofGoods();//45A
    if ((StringUtils.isNotEmpty(descriptionOfGoods)) && (StringUtils.isNotBlank(descriptionOfGoods)) && (descriptionOfGoods != null)) {
      if (descriptionOfGoods.length() >= 4000)
      {
        String descriptionOfGoods1 = descriptionOfGoods.substring(0, 4000);
        if (descriptionOfGoods.length() > 4000)
        {
          String descriptionOfGoods2 = descriptionOfGoods.substring(descriptionOfGoods1.length(), descriptionOfGoods.length());
          canonical.setDescriptionofGoods2(descriptionOfGoods2);
        }
        canonical.setDescriptionofGoods1(descriptionOfGoods1);
      }
      else
      {
        canonical.setDescriptionofGoods1(descriptionOfGoods);
      }
    }
    System.out.println("descriptionOfGoods is "+canonical.getDescriptionofGoods1());
    //45A description of goods and/or services F1916-->lcArrCommodity
    String docsReq = lcCanonicalDto.getDocumentsRequired();//46A
    if ((StringUtils.isNotEmpty(docsReq)) && (StringUtils.isNotBlank(docsReq)) && (docsReq != null)) {
      if (docsReq.length() >= 4000)
      {
        String docsReq1 = docsReq.substring(0, 4000);
        if (docsReq.length() > 4000)
        {
          String docsReq2 = docsReq.substring(docsReq1.length(), docsReq.length());
          canonical.setLcDocsReq2(docsReq2);
        }
        canonical.setLcDocsReq1(docsReq1);
      }
      else
      {
        canonical.setLcDocsReq1(docsReq);
      }
    }
    
    String addlCndts = lcCanonicalDto.getAdditionalConditions();//47A
    if ((StringUtils.isNotEmpty(addlCndts)) && (StringUtils.isNotBlank(addlCndts)) && (addlCndts != null)) {
      if (addlCndts.length() >= 4000)
      {
        String addlCndts1 = addlCndts.substring(0, 4000);
        if (addlCndts.length() > 4000)
        {
          String addlCndts2 = addlCndts.substring(addlCndts1.length(), addlCndts.length());
          canonical.setLcAddnlCndt2(addlCndts2);
        }
        canonical.setLcAddnlCndt1(addlCndts1);
      }
      else
      {
        canonical.setLcAddnlCndt1(addlCndts);
      }
    }
    canonical.setLcCharges(lcCanonicalDto.getChargeDetails());//71B
    canonical.setLcPrsntnPrd(lcCanonicalDto.getPeriodforPresentation());//48
    canonical.setLcConfrmInstrns(lcCanonicalDto.getConfirmationCode());//49
    canonical.setReimbursingIdentifier(lcCanonicalDto.getReimbursingIdentifier());//53a
    canonical.setSenderCorrespondent(lcCanonicalDto.getReimbursingBankCode());//53a
    canonical.setSenderCorrespondentName(lcCanonicalDto.getReimbursingBankNameAddress());//53a
    canonical.setLcInstrnTopay(lcCanonicalDto.getInstructionstoPayingBank());//78
    canonical.setAdvisingIdentifier(lcCanonicalDto.getAdvisingIdentifier());//57a
    canonical.setAccountWithInstitutionAccount(lcCanonicalDto.getAdviseThroughBankCode());//57a
    canonical.setAccountWithInstitutionLoc(lcCanonicalDto.getAdviseThroughBankLocation());//57a
    canonical.setAccountWithInstitutionName(lcCanonicalDto.getAdviseThroughBankName());//57a
    canonical.setInstructionsForCrdtrAgtText(lcCanonicalDto.getSendertoReceiverInformation());//72
    String[] getDepartmant = this.letterOfCreditDao.getDept(userID).split("~");
    canonical.setLastModifiedUser(userID);
    canonical.setMsgBranch(getDepartmant[2]);
    canonical.setMsgDept(getDepartmant[1]);
	 if(lcCanonicalDto.getIssuingBankCode()==null || lcCanonicalDto.getIssuingBankCode().isEmpty())
	  	{
	  	  	canonical.setSenderBank(getDepartmant[0]);
	  	}
	  	else
	  	{
	  		canonical.setSenderBank(lcCanonicalDto.getIssuingBankCode());
	  	}
	 canonical.setReceiverBank(lcCanonicalDto.getAdvisingBank());
    //End :: Added for Mizuho bank UI changes for 700 message 
    
    
    return canonical;
  }


public NgphCanonical callAmendLCDTOToCanonical(LCCanonicalDto lcCanonicalDto, String msgRef, String status, String userID, String repair)
{
	System.out.println("Inside callAmendLCDTOToCanonical() in LetterOfCreditServiceImpl ");
  NgphCanonical canonical = new NgphCanonical();
  Timestamp currentTimestamp = getCurrentTime();
  canonical.setMsgRef(msgRef);

  if ((StringUtils.isBlank(lcCanonicalDto.getRepair())) && (StringUtils.isEmpty(lcCanonicalDto.getRepair()))) {
      canonical.setMsgHost("9999");
    } else {
      canonical.setMsgHost(lcCanonicalDto.getMsgHost());
    }
    canonical.setMsgChnlType("SFMS");
    canonical.setSrcMsgType("707");
    canonical.setSrcMsgSubType("XXX");
    canonical.setMsgStatus("5");
    canonical.setMsgDirection("O");
    canonical.setLastModTime(getCurrentTime());
	canonical.setLcNo(lcCanonicalDto.getLcNumber());//20
	canonical.setRelReference(lcCanonicalDto.getReceiverBankReference());//21
	canonical.setLcPrevAdvRef(lcCanonicalDto.getSenderBankReference());//23
	canonical.setOrderingInstitution(lcCanonicalDto.getApplicantBankCode());//52A
	canonical.setLcDraweeBnkAddr(lcCanonicalDto.getIssunigBankNameAndAddress());//52D
	canonical.setLcIssueDt(convertStringToTimestamp(lcCanonicalDto.getIssueDate()));//31C
	canonical.setLcAmndmntDt(convertStringToTimestamp(lcCanonicalDto.getAmendmentDate()));//30
	canonical.setLcAmndmntNo(new BigDecimal(lcCanonicalDto.getLcAmndmntNo().intValue()));//26E
	canonical.setBeneficiaryCustAcct(lcCanonicalDto.getBeneficiaryAccount());//59
	canonical.setBeneficiaryCustomerAddress(lcCanonicalDto.getBeneficiaryNameAddress());//59
	canonical.setLcExpDt(convertStringToTimestamp(lcCanonicalDto.getNewAmendExpiryDate()));//31E
	if(lcCanonicalDto.getMsgCurrency()!=null && StringUtils.isNotEmpty(lcCanonicalDto.getMsgCurrency()))
    {
  	  canonical.setMsgCurrency(lcCanonicalDto.getMsgCurrency());//32B
    }
    
	canonical.setLcAmndmntIncAmt(lcCanonicalDto.getIncreaseAmendAmount());//32B
	if(lcCanonicalDto.getMsgCurrency()!=null && StringUtils.isNotEmpty(lcCanonicalDto.getMsgCurrency()))
    {
  	  canonical.setMsgCurrency(lcCanonicalDto.getLcCurrency());//33B
    }
   
	canonical.setLcAmndmntDecAmt(lcCanonicalDto.getDecreaseAmendAmount());//33B
	if(lcCanonicalDto.getMsgCurrency()!=null && StringUtils.isNotEmpty(lcCanonicalDto.getMsgCurrency()))
    {
  	  canonical.setMsgCurrency(lcCanonicalDto.getCurrency());//34B
    }
   
	canonical.setLcToAmtClaimed(lcCanonicalDto.getNewLcAmount());//34B
	if ((lcCanonicalDto.getPositiveTolerance() != null) && (StringUtils.isNotEmpty(lcCanonicalDto.getPositiveTolerance())) && (StringUtils.isNotEmpty(lcCanonicalDto.getNegativeTolerance())) && (lcCanonicalDto.getNegativeTolerance() != null))
  {
    canonical.setLcPositiveTolerance(Integer.parseInt(lcCanonicalDto.getPositiveTolerance()));//39A
    canonical.setLcNegativeTolerance(Integer.parseInt(lcCanonicalDto.getNegativeTolerance()));//39A
  }
	
	canonical.setLcMaxCrAmt(lcCanonicalDto.getMaximumCreditAmount());//39B
	canonical.setLcAddlAmts(lcCanonicalDto.getAdditionalAmountsCovered());//39C
	canonical.setLcDispatchPlace(lcCanonicalDto.getInitialDispatchPlace());//44A
	canonical.setInitialDispatchPlace(lcCanonicalDto.getGoodsLoadingDispatchPlace());//44E
	canonical.setLcDstn(lcCanonicalDto.getGoodsDestination());//44F
	canonical.setFinalDeliveryPlace(lcCanonicalDto.getFinalDeliveryPlace());//44B
	canonical.setLcLstShipDt(convertStringToTimestamp(lcCanonicalDto.getLatestDateofShipment()));//44C
	canonical.setLcShipPeriod(lcCanonicalDto.getShipmentPeriod());//44D
	canonical.setLcNarrative(lcCanonicalDto.getNarrative());//79
	canonical.setInstructionsForCrdtrAgtText(lcCanonicalDto.getSendertoReceiverInformation());//72
	 String[] getDepartmant = this.letterOfCreditDao.getDept(userID).split("~");
    canonical.setLastModifiedUser(userID);
    canonical.setMsgBranch(getDepartmant[2]);
    canonical.setMsgDept(getDepartmant[1]);
	 if(lcCanonicalDto.getIssuingBankCode()==null || lcCanonicalDto.getIssuingBankCode().isEmpty())
	  	{
	  	  	canonical.setSenderBank(getDepartmant[0]);
	  	}
	  	else
	  	{
	  		canonical.setSenderBank(lcCanonicalDto.getIssuingBankCode());
	  	}
	 canonical.setReceiverBank(lcCanonicalDto.getAdvisingBank());
	return canonical;
}


public NgphCanonical callLcAckDTOToCanonical(LCCanonicalDto lcCanonicalDto, String msgRef, String status, String userID, String repair)
{
	System.out.println("Inside callLcAckDTOToCanonical() in LetterOfCreditServiceImpl ");
  NgphCanonical canonical = new NgphCanonical();
  Timestamp currentTimestamp = getCurrentTime();
  canonical.setMsgRef(msgRef);
  String[] getDepartmant = this.letterOfCreditDao.getDept(userID).split("~");

  if ((StringUtils.isBlank(lcCanonicalDto.getRepair())) && (StringUtils.isEmpty(lcCanonicalDto.getRepair()))) {
    canonical.setMsgHost("9999");
  } else {
    canonical.setMsgHost(lcCanonicalDto.getMsgHost());
  }
  canonical.setMsgChnlType("SFMS");
  canonical.setSrcMsgType("730");
  canonical.setSrcMsgSubType("XXX");
  canonical.setMsgStatus("1");
  canonical.setMsgDirection("O");
  canonical.setLastModTime(getCurrentTime());
  canonical.setLcNo(lcCanonicalDto.getLcNumber());//20
  canonical.setRelReference(lcCanonicalDto.getRelatedReference());//21
  canonical.setLcAccId(lcCanonicalDto.getLcAccId());//25
  canonical.setLcAckDt(convertStringToTimestamp(lcCanonicalDto.getLcAckDate()));//30
  if(lcCanonicalDto.getCurrency()!=null && lcCanonicalDto.getCurrency().equalsIgnoreCase("B"))//32B
  {
	  if(lcCanonicalDto.getLcCurrency()!=null && StringUtils.isNotEmpty(lcCanonicalDto.getLcCurrency()))
	  {
		  canonical.setMsgCurrency(lcCanonicalDto.getLcCurrency());//32B
	  }
	 
	  canonical.setLcChgsClaimed(lcCanonicalDto.getLcAmount());//32B
  }
  if(lcCanonicalDto.getCurrency()!=null && lcCanonicalDto.getCurrency().equalsIgnoreCase("D"))//32D
  {
	  if(lcCanonicalDto.getLcCurrency()!=null && StringUtils.isNotEmpty(lcCanonicalDto.getLcCurrency()))
	  {
		  canonical.setMessageCurrency(lcCanonicalDto.getLcCurrency());//32D
	  }
	 
	  canonical.setLcChargesClaimed(lcCanonicalDto.getLcAmount());//32D
	  canonical.setMsgValueDate(convertStringToTimestamp(lcCanonicalDto.getAmountPaidDate()));//32D
  }
  //canonical.setAccountWithInstitutionAcct(lcCanonicalDto.getAdviseThroughBankAcc());//57a
  canonical.setAccountWithInstitutionAccount(lcCanonicalDto.getAdviseThroughBankCode());//57a
  canonical.setAccountWithInstitutionName(lcCanonicalDto.getAdviseThroughBankName());//57a
  canonical.setLcCharges(lcCanonicalDto.getChargeDetails());//71B
  String aValue = lcCanonicalDto.getSendertoReceiverInformation();//72
  canonical.setLastModifiedUser(userID);
   canonical.setMsgBranch(getDepartmant[2]);
  canonical.setMsgDept(getDepartmant[1]);
  canonical.setMsgStatus("15");
  canonical.setReceiverBank(lcCanonicalDto.getAdvisingBank());
  if(lcCanonicalDto.getIssuingBankCode()==null || lcCanonicalDto.getIssuingBankCode().isEmpty())
	{
  		canonical.setSenderBank(getDepartmant[0]);
	}
	else
	{
		canonical.setSenderBank(lcCanonicalDto.getIssuingBankCode());
	}
  
  if(StringUtils.isBlank(lcCanonicalDto.getRepair()) && StringUtils.isEmpty(lcCanonicalDto.getRepair()))
	{}
	else
	{
		canonical.setSeqNo(lcCanonicalDto.getSeqNo());
	}
  if (lcCanonicalDto.getSendertoReceiverInformation().startsWith("/"))
  {
    if (aValue.contains("/PHONBEN/"))
    {
      String instructionsForCrdtrAgtCode = aValue.substring(aValue.indexOf("/PHONBEN/"), 9);
      String instructionsForCrdtrAgtText = aValue.substring(instructionsForCrdtrAgtCode.length(), aValue.length());
      canonical.setInstructionsForCrdtrAgtCode(instructionsForCrdtrAgtCode);
      canonical.setInstructionsForCrdtrAgtText(instructionsForCrdtrAgtText);
    }
    else if (aValue.contains("/TELEBEN//"))
    {
      String instructionsForCrdtrAgtCode = aValue.substring(aValue.indexOf("/TELEBEN/"), 9);
      String instructionsForCrdtrAgtText = aValue.substring(instructionsForCrdtrAgtCode.length(), aValue.length());
      canonical.setInstructionsForCrdtrAgtCode(instructionsForCrdtrAgtCode);
      canonical.setInstructionsForCrdtrAgtText(instructionsForCrdtrAgtText);
    }
    else if (aValue.contains("/BENCON/"))
    {
      String instructionsForCrdtrAgtCode = aValue.substring(aValue.indexOf("/BENCON/"), 8);
      String instructionsForCrdtrAgtText = aValue.substring(instructionsForCrdtrAgtCode.length(), aValue.length());
      canonical.setInstructionsForCrdtrAgtCode(instructionsForCrdtrAgtCode);
      canonical.setInstructionsForCrdtrAgtText(instructionsForCrdtrAgtText);
    }
    else if (aValue.contains("/BENACC/"))
    {
      String instructionsForCrdtrAgtCode = aValue.substring(aValue.indexOf("/BENACC/"), 8);
      String instructionsForCrdtrAgtText = aValue.substring(instructionsForCrdtrAgtCode.length(), aValue.length());
      canonical.setInstructionsForCrdtrAgtCode(instructionsForCrdtrAgtCode);
      canonical.setInstructionsForCrdtrAgtText(instructionsForCrdtrAgtText);
    }
    else if (aValue.contains("/BENREJ/"))
    {
      String instructionsForCrdtrAgtCode = aValue.substring(aValue.indexOf("/BENREJ/"), 8);
      String instructionsForCrdtrAgtText = aValue.substring(instructionsForCrdtrAgtCode.length(), aValue.length());
      canonical.setInstructionsForCrdtrAgtCode(instructionsForCrdtrAgtCode);
      canonical.setInstructionsForCrdtrAgtText(instructionsForCrdtrAgtText);
    }
    else
    {
      canonical.setInstructionsForCrdtrAgtText(aValue);
    }
  }
  else {
    canonical.setInstructionsForCrdtrAgtText(aValue);
  }

  canonical.setLastModifiedUser(userID);
  canonical.setMsgBranch(getDepartmant[2]);
  canonical.setMsgDept(getDepartmant[1]);
	 if(lcCanonicalDto.getIssuingBankCode()==null || lcCanonicalDto.getIssuingBankCode().isEmpty())
	  	{
	  	  	canonical.setSenderBank(getDepartmant[0]);
	  	}
	  	else
	  	{
	  		canonical.setSenderBank(lcCanonicalDto.getIssuingBankCode());
	  	}
	 canonical.setReceiverBank(lcCanonicalDto.getAdvisingBank());
 return canonical;
}

public NgphCanonical callAdviceofRefusalDTOToCanonical(LCCanonicalDto lcCanonicalDto, String msgRef, String status, String userID, String repair)
{
	System.out.println("Inside callAdviceofRefusalDTOToCanonical() in LetterOfCreditServiceImpl ");
  NgphCanonical canonical = new NgphCanonical();
  Timestamp currentTimestamp = getCurrentTime();
  canonical.setMsgRef(msgRef);

  if ((StringUtils.isBlank(lcCanonicalDto.getRepair())) && (StringUtils.isEmpty(lcCanonicalDto.getRepair()))) {
    canonical.setMsgHost("9999");
  } else {
    canonical.setMsgHost(lcCanonicalDto.getMsgHost());
  }
  canonical.setMsgChnlType("SFMS");
  canonical.setSrcMsgType("734");
  canonical.setSrcMsgSubType("XXX");
  canonical.setMsgStatus("1");
  canonical.setMsgDirection("O");
  canonical.setLastModTime(getCurrentTime());
  canonical.setLcNo(lcCanonicalDto.getLcNumber());//20
  canonical.setRelReference(lcCanonicalDto.getRelatedReference());//21
  canonical.setMsgValueDate(convertStringToTimestamp(lcCanonicalDto.getLcAckDate()));//32A
  if(lcCanonicalDto.getLcCurrency()!=null && StringUtils.isNotEmpty(lcCanonicalDto.getLcCurrency()))
  {
	  canonical.setMsgCurrency(lcCanonicalDto.getLcCurrency());//32A
  }
  canonical.setMsgAmount(lcCanonicalDto.getLcAmount());//32A
  canonical.setLcCharges(lcCanonicalDto.getChargesClaimed());//73
  if(lcCanonicalDto.getCurrency().equalsIgnoreCase("A"))
  {
	  canonical.setMsgValueDate(convertStringToTimestamp(lcCanonicalDto.getAmountPaidDate()));//33a
	  if(lcCanonicalDto.getClaimCurrency()!=null && StringUtils.isNotEmpty(lcCanonicalDto.getClaimCurrency()))
	  {
		  canonical.setMsgCurrency(lcCanonicalDto.getClaimCurrency());//33a
	  }
	  canonical.setLcTotalAmtClaimed(lcCanonicalDto.getLcClaimAmount());//33a
  }
  else if(lcCanonicalDto.getCurrency().equalsIgnoreCase("B"))
  {
	  if(lcCanonicalDto.getClaimCurrency()!=null && StringUtils.isNotEmpty(lcCanonicalDto.getClaimCurrency()))
	  {
		  canonical.setMessageCurrency(lcCanonicalDto.getClaimCurrency());//33a
	  }
	  canonical.setLcTotalAmountClaimed(lcCanonicalDto.getLcClaimAmount());//33a
  }
  canonical.setAccountWithInstitutionAcct(lcCanonicalDto.getAdviseThroughBankAcc());//57a
  canonical.setAccountWithInstitutionId(lcCanonicalDto.getAdviseThroughBankCode());//57a
  canonical.setAccountWithInstitutionLoc(lcCanonicalDto.getAdviseThroughBankLocation());//57a
  canonical.setAccountWithInstitutionName(lcCanonicalDto.getAdviseThroughBankName());//57a
  String aValue = lcCanonicalDto.getSendertoReceiverInformation();//72
  canonical.setLcDiscrepancies(lcCanonicalDto.getDiscrepancies());//77J
  canonical.setLcDispoDocs(lcCanonicalDto.getLcDispoDocs());//77B
  canonical.setReceiverBank(lcCanonicalDto.getAdvisingBank());
  String[] getDepartmant = this.letterOfCreditDao.getDept(userID).split("~");
  canonical.setLastModifiedUser(userID);
  canonical.setMsgBranch(getDepartmant[2]);
  canonical.setMsgDept(getDepartmant[1]);
  if(lcCanonicalDto.getIssuingBankCode()==null || lcCanonicalDto.getIssuingBankCode().isEmpty())
	{
  		canonical.setSenderBank(getDepartmant[0]);
	}
	else
	{
		canonical.setSenderBank(lcCanonicalDto.getIssuingBankCode());
	}
 
  if(StringUtils.isBlank(lcCanonicalDto.getRepair()) && StringUtils.isEmpty(lcCanonicalDto.getRepair()))
	{}
	else
	{
		canonical.setSeqNo(lcCanonicalDto.getSeqNo());
	}
  if (lcCanonicalDto.getSendertoReceiverInformation().startsWith("/"))
  {
    if (aValue.contains("/PHONBEN/"))
    {
      String instructionsForCrdtrAgtCode = aValue.substring(aValue.indexOf("/PHONBEN/"), 9);
      String instructionsForCrdtrAgtText = aValue.substring(instructionsForCrdtrAgtCode.length(), aValue.length());
      canonical.setInstructionsForCrdtrAgtCode(instructionsForCrdtrAgtCode);
      canonical.setInstructionsForCrdtrAgtText(instructionsForCrdtrAgtText);
    }
    else if (aValue.contains("/TELEBEN//"))
    {
      String instructionsForCrdtrAgtCode = aValue.substring(aValue.indexOf("/TELEBEN/"), 9);
      String instructionsForCrdtrAgtText = aValue.substring(instructionsForCrdtrAgtCode.length(), aValue.length());
      canonical.setInstructionsForCrdtrAgtCode(instructionsForCrdtrAgtCode);
      canonical.setInstructionsForCrdtrAgtText(instructionsForCrdtrAgtText);
    }
    else if (aValue.contains("/BENCON/"))
    {
      String instructionsForCrdtrAgtCode = aValue.substring(aValue.indexOf("/BENCON/"), 8);
      String instructionsForCrdtrAgtText = aValue.substring(instructionsForCrdtrAgtCode.length(), aValue.length());
      canonical.setInstructionsForCrdtrAgtCode(instructionsForCrdtrAgtCode);
      canonical.setInstructionsForCrdtrAgtText(instructionsForCrdtrAgtText);
    }
    else if (aValue.contains("/BENACC/"))
    {
      String instructionsForCrdtrAgtCode = aValue.substring(aValue.indexOf("/BENACC/"), 8);
      String instructionsForCrdtrAgtText = aValue.substring(instructionsForCrdtrAgtCode.length(), aValue.length());
      canonical.setInstructionsForCrdtrAgtCode(instructionsForCrdtrAgtCode);
      canonical.setInstructionsForCrdtrAgtText(instructionsForCrdtrAgtText);
    }
    else if (aValue.contains("/BENREJ/"))
    {
      String instructionsForCrdtrAgtCode = aValue.substring(aValue.indexOf("/BENREJ/"), 8);
      String instructionsForCrdtrAgtText = aValue.substring(instructionsForCrdtrAgtCode.length(), aValue.length());
      canonical.setInstructionsForCrdtrAgtCode(instructionsForCrdtrAgtCode);
      canonical.setInstructionsForCrdtrAgtText(instructionsForCrdtrAgtText);
    }
    else
    {
      canonical.setInstructionsForCrdtrAgtText(aValue);
    }
  }
  else
  {
    canonical.setInstructionsForCrdtrAgtText(aValue);
  }
  
  	canonical.setLastModifiedUser(userID);
    canonical.setMsgBranch(getDepartmant[2]);
    canonical.setMsgDept(getDepartmant[1]);
  	 if(lcCanonicalDto.getIssuingBankCode()==null || lcCanonicalDto.getIssuingBankCode().isEmpty())
  	  	{
  	  	  	canonical.setSenderBank(getDepartmant[0]);
  	  	}
  	  	else
  	  	{
  	  		canonical.setSenderBank(lcCanonicalDto.getIssuingBankCode());
  	  	}
  	 canonical.setReceiverBank(lcCanonicalDto.getAdvisingBank());
  return canonical;
}

public NgphCanonical callAuthPaymentAdviceDTOToCanonical(LCCanonicalDto lcCanonicalDto, String msgRef, String status, String userID, String repair)
{
	System.out.println("Inside callAuthPaymentAdviceDTOToCanonical() in LetterOfCreditServiceImpl ");
  NgphCanonical canonical = new NgphCanonical();
  Timestamp currentTimestamp = getCurrentTime();
  canonical.setMsgRef(msgRef);
  String[] getDepartmant = this.letterOfCreditDao.getDept(userID).split("~");
 

  if ((StringUtils.isBlank(lcCanonicalDto.getRepair())) && (StringUtils.isEmpty(lcCanonicalDto.getRepair()))) {
    canonical.setMsgHost("9999");
  } else {
    canonical.setMsgHost(lcCanonicalDto.getMsgHost());
  }
  canonical.setMsgChnlType("SFMS");
  canonical.setSrcMsgType("740");
  canonical.setSrcMsgSubType("XXX");
  canonical.setMsgStatus("1");
  canonical.setMsgDirection("O");
  canonical.setLastModTime(getCurrentTime());
  
   canonical.setLcNo(lcCanonicalDto.getLcNumber());//20
   canonical.setLcAccId(lcCanonicalDto.getAcountID());//25
   canonical.setLcAppRulesCode(lcCanonicalDto.getApplicableRule());//40F
  canonical.setLcExpDt(convertStringToTimestamp(lcCanonicalDto.getLcExpiryDate()));//31D
  canonical.setLcExpPlace(lcCanonicalDto.getLcExpirePlace());//31D
  //canonical.setBeneficiaryInstitutionPID(lcCanonicalDto.getNegotiatingBankPartyIdentifier());//58a
   canonical.setBeneficiaryInstitutionClrgCd(lcCanonicalDto.getNegotiatingBankCode());//58a
   canonical.setBeneficiaryInstitutionName(lcCanonicalDto.getNegotiatingBankNameAndAddress());//58a
   //canonical.setBeneficiaryCustAcct(lcCanonicalDto.getBeneficiaryAccount());//59
    canonical.setBeneficiaryCustomerName(lcCanonicalDto.getBeneficiaryNameAddress());//59
    if(lcCanonicalDto.getLcCurrency()!=null && StringUtils.isNotEmpty(lcCanonicalDto.getLcCurrency()))
  {
  	canonical.setMsgCurrency(lcCanonicalDto.getLcCurrency());//32B
  }
 
  canonical.setMsgAmount(lcCanonicalDto.getCreditAmount());//32B
   if ((lcCanonicalDto.getPositiveTolerance() != null) && (StringUtils.isNotEmpty(lcCanonicalDto.getPositiveTolerance())) && (StringUtils.isNotEmpty(lcCanonicalDto.getNegativeTolerance())) && (lcCanonicalDto.getNegativeTolerance() != null))
    {
      canonical.setLcPositiveTolerance(Integer.parseInt(lcCanonicalDto.getPositiveTolerance()));//39A
      canonical.setLcNegativeTolerance(Integer.parseInt(lcCanonicalDto.getNegativeTolerance()));//39A
    }
    canonical.setLcMaxCrAmt(lcCanonicalDto.getMaximumCreditAmount());//39B
    if (lcCanonicalDto.getAdditionalAmountsCovered() != null) {
    canonical.setLcAddlAmts(lcCanonicalDto.getAdditionalAmountsCovered());//39C
  } else {
    canonical.setLcAddlAmts(lcCanonicalDto.getAdditionalAmounts());
  }
  
   canonical.setLcAuthBankCode(lcCanonicalDto.getAuthorisedBankCode());//41a
    canonical.setLcAuthMode(lcCanonicalDto.getAuthorisationMode());//41a
    canonical.setLcAuthBankAddr(lcCanonicalDto.getAuthorisedAndAddress());//41a
    canonical.setLcDraftsAt(lcCanonicalDto.getDraftsAt());//42C
   // canonical.setLcDraweeBnkPid(lcCanonicalDto.getDraweeBankpartyidentifier());//42a
    canonical.setLcDraweeBnkCode(lcCanonicalDto.getDraweeBankCode());//42a
    canonical.setLcDraweeBnkAddr(lcCanonicalDto.getDraweeBankNameAddress());//42a
    canonical.setLcMixedPymtDet(lcCanonicalDto.getMixedPaymentDetails());//42M
    canonical.setLcDefPymtDet(lcCanonicalDto.getDeferredPaymentDetails());//42P
    canonical.setChargeBearer(lcCanonicalDto.getReimbursingBanksCharges());//71A
    canonical.setLcCharges(lcCanonicalDto.getOtherCharges());//71B
    canonical.setInstructionsForCrdtrAgtText(lcCanonicalDto.getSendertoReceiverInformation());//72

  
  
  canonical.setLastModifiedUser(userID);
  canonical.setMsgBranch(getDepartmant[2]);
  canonical.setMsgDept(getDepartmant[1]);
	 if(lcCanonicalDto.getIssuingBankCode()==null || lcCanonicalDto.getIssuingBankCode().isEmpty())
	  	{
	  	  	canonical.setSenderBank(getDepartmant[0]);
	  	}
	  	else
	  	{
	  		canonical.setSenderBank(lcCanonicalDto.getIssuingBankCode());
	  	}
	 canonical.setReceiverBank(lcCanonicalDto.getAdvisingBank());
  return canonical;
}

public NgphCanonical callAdviceofDiscrepancyDTOToCanonical(LCCanonicalDto lcCanonicalDto, String msgRef, String status, String userID, String repair)
{
	System.out.println("Inside callAdviceofDiscrepancyDTOToCanonical() in LetterOfCreditServiceImpl ");
  NgphCanonical canonical = new NgphCanonical();
  Timestamp currentTimestamp = getCurrentTime();
  canonical.setMsgRef(msgRef);

  if ((StringUtils.isBlank(lcCanonicalDto.getRepair())) && (StringUtils.isEmpty(lcCanonicalDto.getRepair()))) {
    canonical.setMsgHost("9999");
  } else {
    canonical.setMsgHost(lcCanonicalDto.getMsgHost());
  }
  canonical.setMsgChnlType("SFMS");
  canonical.setSrcMsgType("750");
  canonical.setSrcMsgSubType("XXX");
  canonical.setMsgStatus("1");
  canonical.setMsgDirection("O");
  canonical.setLastModTime(getCurrentTime());
  canonical.setLcNo(lcCanonicalDto.getLcNumber());//20
  canonical.setRelReference(lcCanonicalDto.getRelatedReference());//21 
  if(lcCanonicalDto.getMsgCurrency()!=null && StringUtils.isNotEmpty(lcCanonicalDto.getMsgCurrency()))
  {
	  canonical.setPrincipalCurrency(lcCanonicalDto.getMsgCurrency());//32B
  }
  canonical.setPrincPrincipalAmt(lcCanonicalDto.getPrincipalAmount());//32B
  canonical.setLcAdditionalCurrCode(lcCanonicalDto.getLcCurrency());//33B
  canonical.setLcAdditionalAmt(lcCanonicalDto.getAdditionalAmount());//33B
  canonical.setLcChargesDeducted(lcCanonicalDto.getChargesDeducted());//71B
  canonical.setLcChargesAdded(lcCanonicalDto.getChargesAdded());//73
  if(lcCanonicalDto.getCurrency()!=null && StringUtils.isNotEmpty(lcCanonicalDto.getCurrency()))
  {
	  canonical.setMsgCurrency(lcCanonicalDto.getCurrency());//34B
  }
  canonical.setLcTotalAmtClaimed(lcCanonicalDto.getTotalAmount());//34B
  canonical.setAccountWithInstitutionAccount(lcCanonicalDto.getAdviseThroughBankCode());//57a
  canonical.setAccountWithInstitutionLoc(lcCanonicalDto.getAccountWithPartyLoc());//57a
  canonical.setAccountWithInstitutionName(lcCanonicalDto.getAccountWithPartyNameAndAddress());//57a
  String aValue = lcCanonicalDto.getSendertoReceiverInformation();//72
  canonical.setLcDiscrepancies(lcCanonicalDto.getDiscrepancies());//77J
  
  canonical.setReceiverBank(lcCanonicalDto.getAdvisingBank());
  String[] getDepartmant = this.letterOfCreditDao.getDept(userID).split("~");
  canonical.setLastModifiedUser(userID);
  canonical.setMsgBranch(getDepartmant[2]);
  canonical.setMsgDept(getDepartmant[1]);
  if(lcCanonicalDto.getIssuingBankCode()==null || lcCanonicalDto.getIssuingBankCode().isEmpty())
	{
	  	canonical.setSenderBank(getDepartmant[0]);
	}
	else
	{
		 canonical.setSenderBank(lcCanonicalDto.getIssuingBankCode());
	}
  
  
  if(StringUtils.isBlank(lcCanonicalDto.getRepair()) && StringUtils.isEmpty(lcCanonicalDto.getRepair()))
	{}
	else
	{
		canonical.setSeqNo(lcCanonicalDto.getSeqNo());
	}
  if (lcCanonicalDto.getSendertoReceiverInformation().startsWith("/"))
  {
    if (aValue.contains("/PHONBEN/"))
    {
      String instructionsForCrdtrAgtCode = aValue.substring(aValue.indexOf("/PHONBEN/"), 9);
      String instructionsForCrdtrAgtText = aValue.substring(instructionsForCrdtrAgtCode.length(), aValue.length());
      canonical.setInstructionsForCrdtrAgtCode(instructionsForCrdtrAgtCode);
      canonical.setInstructionsForCrdtrAgtText(instructionsForCrdtrAgtText);
    }
    else if (aValue.contains("/TELEBEN//"))
    {
      String instructionsForCrdtrAgtCode = aValue.substring(aValue.indexOf("/TELEBEN/"), 9);
      String instructionsForCrdtrAgtText = aValue.substring(instructionsForCrdtrAgtCode.length(), aValue.length());
      canonical.setInstructionsForCrdtrAgtCode(instructionsForCrdtrAgtCode);
      canonical.setInstructionsForCrdtrAgtText(instructionsForCrdtrAgtText);
    }
    else if (aValue.contains("/BENCON/"))
    {
      String instructionsForCrdtrAgtCode = aValue.substring(aValue.indexOf("/BENCON/"), 8);
      String instructionsForCrdtrAgtText = aValue.substring(instructionsForCrdtrAgtCode.length(), aValue.length());
      canonical.setInstructionsForCrdtrAgtCode(instructionsForCrdtrAgtCode);
      canonical.setInstructionsForCrdtrAgtText(instructionsForCrdtrAgtText);
    }
    else if (aValue.contains("/BENACC/"))
    {
      String instructionsForCrdtrAgtCode = aValue.substring(aValue.indexOf("/BENACC/"), 8);
      String instructionsForCrdtrAgtText = aValue.substring(instructionsForCrdtrAgtCode.length(), aValue.length());
      canonical.setInstructionsForCrdtrAgtCode(instructionsForCrdtrAgtCode);
      canonical.setInstructionsForCrdtrAgtText(instructionsForCrdtrAgtText);
    }
    else if (aValue.contains("/BENREJ/"))
    {
      String instructionsForCrdtrAgtCode = aValue.substring(aValue.indexOf("/BENREJ/"), 8);
      String instructionsForCrdtrAgtText = aValue.substring(instructionsForCrdtrAgtCode.length(), aValue.length());
      canonical.setInstructionsForCrdtrAgtCode(instructionsForCrdtrAgtCode);
      canonical.setInstructionsForCrdtrAgtText(instructionsForCrdtrAgtText);
    }
    else
    {
      canonical.setInstructionsForCrdtrAgtText(aValue);
    }
  }
  else {
    canonical.setInstructionsForCrdtrAgtText(aValue);
  }
  canonical.setLastModifiedUser(userID);
  canonical.setMsgBranch(getDepartmant[2]);
  canonical.setMsgDept(getDepartmant[1]);
	 if(lcCanonicalDto.getIssuingBankCode()==null || lcCanonicalDto.getIssuingBankCode().isEmpty())
	  	{
	  	  	canonical.setSenderBank(getDepartmant[0]);
	  	}
	  	else
	  	{
	  		canonical.setSenderBank(lcCanonicalDto.getIssuingBankCode());
	  	}
	 canonical.setReceiverBank(lcCanonicalDto.getAdvisingBank());
  
  return canonical;
}

public NgphCanonical callAdviceofPaymentDTOToCanonical(LCCanonicalDto lcCanonicalDto, String msgRef, String status, String userID, String repair)
{
  System.out.println("Inside callAdviceofPaymentDTOToCanonical() in LetterOfCreditServiceImpl ");
  NgphCanonical canonical = new NgphCanonical();
  Timestamp currentTimestamp = getCurrentTime();
  canonical.setMsgRef(msgRef);

  if ((StringUtils.isBlank(lcCanonicalDto.getRepair())) && (StringUtils.isEmpty(lcCanonicalDto.getRepair()))) {
    canonical.setMsgHost("9999");
  } else {
    canonical.setMsgHost(lcCanonicalDto.getMsgHost());
  }
  canonical.setMsgChnlType("SFMS");
  canonical.setSrcMsgType("754");
  canonical.setSrcMsgSubType("XXX");
  canonical.setMsgStatus("1");
  canonical.setMsgDirection("O");
  canonical.setLastModTime(getCurrentTime());
  canonical.setLcNo(lcCanonicalDto.getLcNumber());//20
  canonical.setRelReference(lcCanonicalDto.getRelatedReference());//21
  
  if(lcCanonicalDto.getPricAmount().equalsIgnoreCase("A"))
  {
	  canonical.setPrincipalDate(convertStringToTimestamp(lcCanonicalDto.getAmountPaidDate()));//32A
	  if(lcCanonicalDto.getMsgCurrency()!=null && StringUtils.isNotEmpty(lcCanonicalDto.getMsgCurrency()))
	  {
		  canonical.setPrincipalCurrency(lcCanonicalDto.getMsgCurrency());//32A
	  }
	  canonical.setPrincPrincipalAmt(lcCanonicalDto.getPrincipalAmount());//32A
  }
  else if(lcCanonicalDto.getPricAmount().equalsIgnoreCase("B"))//32B
  {
	  if(lcCanonicalDto.getMsgCurrency()!=null && StringUtils.isNotEmpty(lcCanonicalDto.getMsgCurrency()))
	  {
		  canonical.setPrincipalCurr(lcCanonicalDto.getMsgCurrency());//32B
	  }
	  canonical.setPrincipalAmount(lcCanonicalDto.getPrincipalAmount());//32B
  }
  canonical.setLcAdditionalCurrCode(lcCanonicalDto.getLcCurrency());//33B
  canonical.setLcAdditionalAmt(lcCanonicalDto.getAdditionalAmount());//33B
  canonical.setLcChargesDeducted(lcCanonicalDto.getChargesDeducted());//71B
  canonical.setLcChargesAdded(lcCanonicalDto.getChargesAdded());//73
  canonical.setUiMsgIdentifier34(lcCanonicalDto.getTotalAmountClaim()); //34 Field Identifier
  if(lcCanonicalDto.getTotalAmountClaim().equalsIgnoreCase("A"))
  {
	  canonical.setMsgValueDate(convertStringToTimestamp(lcCanonicalDto.getTotalPaidDate()));//34A
	  if(lcCanonicalDto.getCurrency()!=null && StringUtils.isNotEmpty(lcCanonicalDto.getCurrency()))
	  {
		  canonical.setMsgCurrency(lcCanonicalDto.getCurrency());//34A
	  }
	  canonical.setLcTotalAmtClaimed(lcCanonicalDto.getTotalAmount());//34A
  }
  else if(lcCanonicalDto.getTotalAmountClaim().equalsIgnoreCase("B"))//34B
  {
	  if(lcCanonicalDto.getCurrency()!=null && StringUtils.isNotEmpty(lcCanonicalDto.getCurrency()))
	  {
		  canonical.setMessageCurrency(lcCanonicalDto.getCurrency());//34B
	  }
	  canonical.setLcTotalAmountClaimed(lcCanonicalDto.getTotalAmount());//34B
  }
  canonical.setSenderCorrespondentId(lcCanonicalDto.getReimbursingBankAcc());//53a
  canonical.setSenderCorrespondent(lcCanonicalDto.getReimbursingBankCode());//53a
  canonical.setSenderCorrespondentLoc(lcCanonicalDto.getReimbersingBankLoc());//53a
  canonical.setSenderCorrespondentName(lcCanonicalDto.getReimbursingBankNameandAddress());//53a
  //canonical.setAccountWithInstitutionId(lcCanonicalDto.getAdviseThroughBankAcc());//57a
  canonical.setAccountWithInstitutionAccount(lcCanonicalDto.getAdviseThroughBankCode());//57a
  canonical.setAccountWithInstitutionLoc(lcCanonicalDto.getAccountWithPartyLoc());//57a
  canonical.setAccountWithInstitutionName(lcCanonicalDto.getAccountWithPartyNameAndAddress());//57a
  canonical.setBeneficiaryInstitutionPID(lcCanonicalDto.getBeneficiaryBankAcc());//58a
  canonical.setBeneficiaryBankCode(lcCanonicalDto.getBeneficiaryBankCode());//58a
  canonical.setBeneficiaryInstitutionName(lcCanonicalDto.getBeneficiaryBankNameAndAddress());//58a
  String aValue = lcCanonicalDto.getSendertoReceiverInformation();//72
  canonical.setLcNarrative(lcCanonicalDto.getNarrative());//77A
  
  canonical.setReceiverBank(lcCanonicalDto.getAdvisingBank());
  String[] getDepartmant = this.letterOfCreditDao.getDept(userID).split("~");
  canonical.setLastModifiedUser(userID);
  canonical.setMsgBranch(getDepartmant[2]);
  canonical.setMsgDept(getDepartmant[1]);
  if(lcCanonicalDto.getIssuingBankCode()==null || lcCanonicalDto.getIssuingBankCode().isEmpty())
	{
	  	canonical.setSenderBank(getDepartmant[0]);
	}
	else
	{
		canonical.setSenderBank(lcCanonicalDto.getIssuingBankCode());
	}
 
  if(StringUtils.isBlank(lcCanonicalDto.getRepair()) && StringUtils.isEmpty(lcCanonicalDto.getRepair()))
	{}
	else
	{
		canonical.setSeqNo(lcCanonicalDto.getSeqNo());
	}
  if (lcCanonicalDto.getSendertoReceiverInformation().startsWith("/"))
  {
    if (aValue.contains("/PHONBEN/"))
    {
      String instructionsForCrdtrAgtCode = aValue.substring(aValue.indexOf("/PHONBEN/"), 9);
      String instructionsForCrdtrAgtText = aValue.substring(instructionsForCrdtrAgtCode.length(), aValue.length());
      canonical.setInstructionsForCrdtrAgtCode(instructionsForCrdtrAgtCode);
      canonical.setInstructionsForCrdtrAgtText(instructionsForCrdtrAgtText);
    }
    else if (aValue.contains("/TELEBEN//"))
    {
      String instructionsForCrdtrAgtCode = aValue.substring(aValue.indexOf("/TELEBEN/"), 9);
      String instructionsForCrdtrAgtText = aValue.substring(instructionsForCrdtrAgtCode.length(), aValue.length());
      canonical.setInstructionsForCrdtrAgtCode(instructionsForCrdtrAgtCode);
      canonical.setInstructionsForCrdtrAgtText(instructionsForCrdtrAgtText);
    }
    else if (aValue.contains("/BENCON/"))
    {
      String instructionsForCrdtrAgtCode = aValue.substring(aValue.indexOf("/BENCON/"), 8);
      String instructionsForCrdtrAgtText = aValue.substring(instructionsForCrdtrAgtCode.length(), aValue.length());
      canonical.setInstructionsForCrdtrAgtCode(instructionsForCrdtrAgtCode);
      canonical.setInstructionsForCrdtrAgtText(instructionsForCrdtrAgtText);
    }
    else if (aValue.contains("/BENACC/"))
    {
      String instructionsForCrdtrAgtCode = aValue.substring(aValue.indexOf("/BENACC/"), 8);
      String instructionsForCrdtrAgtText = aValue.substring(instructionsForCrdtrAgtCode.length(), aValue.length());
      canonical.setInstructionsForCrdtrAgtCode(instructionsForCrdtrAgtCode);
      canonical.setInstructionsForCrdtrAgtText(instructionsForCrdtrAgtText);
    }
    else if (aValue.contains("/BENREJ/"))
    {
      String instructionsForCrdtrAgtCode = aValue.substring(aValue.indexOf("/BENREJ/"), 8);
      String instructionsForCrdtrAgtText = aValue.substring(instructionsForCrdtrAgtCode.length(), aValue.length());
      canonical.setInstructionsForCrdtrAgtCode(instructionsForCrdtrAgtCode);
      canonical.setInstructionsForCrdtrAgtText(instructionsForCrdtrAgtText);
    }
    else
    {
      canonical.setInstructionsForCrdtrAgtText(aValue);
    }
  }
  else {
    canonical.setInstructionsForCrdtrAgtText(aValue);
  }
  canonical.setLastModifiedUser(userID);
  canonical.setMsgBranch(getDepartmant[2]);
  canonical.setMsgDept(getDepartmant[1]);
	 if(lcCanonicalDto.getIssuingBankCode()==null || lcCanonicalDto.getIssuingBankCode().isEmpty())
	  	{
	  	  	canonical.setSenderBank(getDepartmant[0]);
	  	}
	  	else
	  	{
	  		canonical.setSenderBank(lcCanonicalDto.getIssuingBankCode());
	  	}
	 canonical.setReceiverBank(lcCanonicalDto.getAdvisingBank());
  return canonical;
}
  
public NgphCanonical callPaymentAdviceDTOToCanonical(LCCanonicalDto lcCanonicalDto, String msgRef, String status, String userID, String repair)
{
  System.out.println("Inside callPaymentAdviceDTOToCanonical() in LetterOfCreditServiceImpl ");
  NgphCanonical canonical = new NgphCanonical();
  Timestamp currentTimestamp = getCurrentTime();
  canonical.setMsgRef(msgRef);
  String[] getDepartmant = this.letterOfCreditDao.getDept(userID).split("~");
  
  if ((StringUtils.isBlank(lcCanonicalDto.getRepair())) && (StringUtils.isEmpty(lcCanonicalDto.getRepair()))) {
    canonical.setMsgHost("9999");
  } else {
    canonical.setMsgHost(lcCanonicalDto.getMsgHost());
  }
  canonical.setMsgChnlType("SFMS");
  canonical.setSrcMsgType("756");
  canonical.setSrcMsgSubType("XXX");
  canonical.setMsgStatus("1");
  canonical.setMsgDirection("O");
  canonical.setLastModTime(getCurrentTime());
  canonical.setLcNo(lcCanonicalDto.getLcNumber());//20
  canonical.setRelReference(lcCanonicalDto.getPresentingBanksReference());//21
	if(lcCanonicalDto.getClaimCurrency()!=null && StringUtils.isNotEmpty(lcCanonicalDto.getClaimCurrency()))
  {
  	canonical.setMsgCurrency(lcCanonicalDto.getClaimCurrency());//32B
  }
 
	canonical.setMsgAmount(lcCanonicalDto.getTotalAmountClaimed());//32B
	canonical.setMsgValueDate(convertStringToTimestamp(lcCanonicalDto.getAmountPaidDate()));//33A
	 if(lcCanonicalDto.getCurrency()!=null && StringUtils.isNotEmpty(lcCanonicalDto.getCurrency()))
   {
   	canonical.setMsgCurrency(lcCanonicalDto.getCurrency());//33A
   }
  
	canonical.setLcAmtPaid(lcCanonicalDto.getPaidAmount());//33A
	canonical.setSenderCorrespondent(lcCanonicalDto.getSendersCorrespondentCode());//53a
	canonical.setSenderCorrespondentLoc(lcCanonicalDto.getSendersCorrespondentLocation());//53a
	canonical.setSenderCorrespondentName(lcCanonicalDto.getSendersCorrespondentNameandAddress());//53a
	canonical.setReceiverCorrespondent(lcCanonicalDto.getReceiversCorrespondentCode());//54a
	canonical.setReceiverCorrespondentLoc(lcCanonicalDto.getReceiversCorrespondentLocation());//54a
	canonical.setReceiverCorrespondentName(lcCanonicalDto.getReceiversCorrespondentNameandAddress());//54a
	canonical.setInstructionsForCrdtrAgtText(lcCanonicalDto.getSendertoReceiverInformation());//72
	canonical.setLastModifiedUser(userID);
	  canonical.setMsgBranch(getDepartmant[2]);
	  canonical.setMsgDept(getDepartmant[1]);
		 if(lcCanonicalDto.getIssuingBankCode()==null || lcCanonicalDto.getIssuingBankCode().isEmpty())
		  	{
		  	  	canonical.setSenderBank(getDepartmant[0]);
		  	}
		  	else
		  	{
		  		canonical.setSenderBank(lcCanonicalDto.getIssuingBankCode());
		  	}
		 canonical.setReceiverBank(lcCanonicalDto.getAdvisingBank());
	
	

  return canonical;
}
}