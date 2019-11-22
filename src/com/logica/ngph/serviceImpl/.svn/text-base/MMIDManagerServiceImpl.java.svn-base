package com.logica.ngph.serviceImpl;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import org.apache.log4j.Logger;

import com.logica.ngph.Entity.MsgPolled;
import com.logica.ngph.Entity.MsgsPolled;
import com.logica.ngph.Entity.NgphCanonical;
import com.logica.ngph.common.NGPHException;
import com.logica.ngph.common.enums.PaymentStatusEnum;
import com.logica.ngph.common.utils.NGPHUtil;
import com.logica.ngph.common.utils.QNGUIConstants;
import com.logica.ngph.dao.MMIDManagerDao;
import com.logica.ngph.dtos.MMIDManagerDto;
import com.logica.ngph.service.MMIDManagerService;

public class MMIDManagerServiceImpl implements MMIDManagerService {

	Logger logger = Logger.getLogger(MMIDManagerServiceImpl.class);
	MMIDManagerDao mmidManagerDao;

	public MMIDManagerDao getMmidManagerDao() {
		return mmidManagerDao;
	}

	public void setMmidManagerDao(MMIDManagerDao mmidManagerDao) {
		this.mmidManagerDao = mmidManagerDao;
	}

	
	public void populateMMIDData(MMIDManagerDto mmidManagerDto) 
	{
		
		NgphCanonical canonical = new NgphCanonical();
		MsgPolled msgPolled = new MsgPolled();
		
		canonical.setMsgRef(NGPHUtil.generateUUID());
		canonical.setMsgHost(QNGUIConstants.QNG_UI_HOST);
		canonical.setMsgChannel(QNGUIConstants.QNG_UI_IMPS_CHNL);
		canonical.setMsgChnlType(QNGUIConstants.QNG_UI_IMPS_CHNL);
		canonical.setSrcMsgType(QNGUIConstants.QNG_UI_MSG_TYPE);
		canonical.setSrcMsgSubType(QNGUIConstants.QNG_UI_MSGSUB_TYPE);
		canonical.setDstMsgType(QNGUIConstants.QNG_UI_MSG_TYPE);
		canonical.setDstMsgSubType(QNGUIConstants.QNG_UI_MSGSUB_TYPE);
		canonical.setMsgStatus(PaymentStatusEnum.findPaymentStatusCodeByEnum(PaymentStatusEnum.RECEIVED_O));
		canonical.setMsgDirection(QNGUIConstants.OUTWARD_PAYMENT);
		canonical.setReceivedTime(new Timestamp(Calendar.getInstance().getTimeInMillis()));
		canonical.setLastModTime(new Timestamp(Calendar.getInstance().getTimeInMillis()));
		canonical.setBeneficiaryCustAcct(mmidManagerDto.getBeneficaryAccountNo());
		//poller Info
		msgPolled.setMsgRef(canonical.getMsgRef());
		msgPolled.setInTime(new Timestamp(Calendar.getInstance().getTimeInMillis()));
		msgPolled.setPolledStatus("P");

		try
		{
			canonical.setMsgBranch(mmidManagerDao.getInitialisedValue("DEFBRANCH"));
		}
		catch (Exception e) {
			logger.error(e, e);
		}
		
		//txnReference,clrgSysReference--> ISO Channel Service(getStan() and Fetch RetRefNo(), apply same code)
		String stan = getStan(canonical.getMsgBranch());
		String retRefNo = getRetRefNo(stan);
		canonical.setTxnReference(retRefNo);
		canonical.setClrgSysReference(retRefNo);
		
		canonical.setBeneficiaryCustomerID(mmidManagerDto.getBenificiaryMMID());
		canonical.setOrderingCustAccount(mmidManagerDto.getAccountNo());
		canonical.setInstructionsForNextAgtText(mmidManagerDto.getRemarks());
		
		canonical.setMsgTxnType(mmidManagerDto.getTransactionType());
		if(mmidManagerDto.getTransactionType().equalsIgnoreCase("P2A"))
		{
			canonical.setMsgPurposeCode("90" + mmidManagerDto.getFromAccType() + mmidManagerDto.getToAccType());
		}
		else
		{
			canonical.setMsgPurposeCode("900000");
		}
		
		canonical.setMsgAmount(mmidManagerDto.getOrderingAmount());
		
		String hostCat = mmidManagerDao.getHostCategory(canonical.getMsgHost());
		if(hostCat.equalsIgnoreCase("MOB") || hostCat == null)
		{
			canonical.setSvcLevelCode("4814");
			canonical.setLclInstCode("019");
			canonical.setCatgPurposeCode("05");
		}
		else if(hostCat.equalsIgnoreCase("ATM"))
		{
			canonical.setSvcLevelCode("6011");
			canonical.setLclInstCode("901");
			canonical.setCatgPurposeCode("00");
		}
		else if(hostCat.equalsIgnoreCase("INET"))
		{
			canonical.setSvcLevelCode("4829");
			canonical.setLclInstCode("012");
			canonical.setCatgPurposeCode("05");
		}	
		
		try 
		{		
			//SenderBank -->getInitEntry (IMPSLOCOFFCD)
			canonical.setSenderBank(mmidManagerDao.getInitialisedValue("IMPSLOCOFFCD"));
		} catch (NGPHException e1) 
		{
			logger.error(e1, e1);
		}
		
		//InitiatorRemitReference	InitiatingPartyID
		
		 String terminalId = mmidManagerDao.getInitialisedValBranch("LOCALBIC", canonical.getMsgBranch()).substring(0, 3); 
						logger.info("terminalId-->" + terminalId);
						//get Mob number
						String mobNo = mmidManagerDao.getMobNo(canonical.getOrderingCustAccount());
						String mobVal = mobNo.substring(mobNo.length()-5, mobNo.length());
						canonical.setInitiatingPartyID(terminalId + mobNo);
						canonical.setInitiatorRemitReference(terminalId + mobVal);
		try 
		{		//Currency ->getInitEntry (BASECURRENCY)
			canonical.setMsgCurrency(mmidManagerDao.getInitialisedValue("BASECURRENCY"));
		} catch (NGPHException e1) 
		{
			logger.error(e1, e1);
		}
		
		  String ordMobNo = mmidManagerDao.getMobNo(canonical.getOrderingCustAccount());
				if (ordMobNo != null)
				{
					canonical.setOrderingCustomerCtctDtls(ordMobNo);
					ArrayList<String> acData = null;
					try {
						acData = (ArrayList<String>) mmidManagerDao.getAcDetailsByAccountAndMobile(canonical.getOrderingCustAccount(),canonical.getOrderingCustomerCtctDtls(),1);
					} catch (NGPHException e1) {
						logger.error(e1, e1);
					}
					if (acData.size() == 5)
					{
						String accName = acData.get(0);
						canonical.setOrderingCustomerName(accName);
						String acClosed = acData.get(1);
						String acCity = acData.get(2);
						String acState = acData.get(3);
						String acCtry = acData.get(4);
						if (acClosed.equals("1"))
						{
							logger.error("Outbound Payment. The account number " + canonical.getOrderingCustAccount() + " with account name " +accName + "is closed");
						}
						if (acCity != null && acState!=null && acCtry!=null)
						{
							canonical.setOrderingCustomerAddress(acCity + "\r\n" + acState + "\r\n" + acCtry);
						}
					}
		 
		System.out.println("MsgRef Generated for " + mmidManagerDto.getAccountNo() + " is " + canonical.getMsgRef());
		
		try 
		{
			mmidManagerDao.populateMMIDData(canonical);
			mmidManagerDao.populateMMIDDataforPoller(msgPolled);
		} catch (NGPHException e) 
		{
			logger.error(e, e);
		}
		catch (Exception e) {
			logger.error(e, e);
		}
				}
	}
	
	//check stan for business dates
	public String getStan(String branch)
	{
		String stan = null;
		try
		{
			int cycle = Integer.parseInt(mmidManagerDao.getStan("ImpsStan"));
			String bussDate = mmidManagerDao.getbusday_Date(branch);
			Date currdate  = mmidManagerDao.getcurrbusday_Date("ImpsStan");
			//Curr busy date is present and is not null
			if(currdate !=null)
			{
				//fetch the date and convert into same format
				SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yy");
				String date = sdf.format(currdate);
				
				Date busdate = null;
				Date curdate = null;
				//compare dates in String format to Date format
				 try 
				 { 
					 DateFormat formatter ; 
					 formatter = new SimpleDateFormat("dd-MMM-yy");
					 busdate = (Date)formatter.parse(bussDate);
					 curdate = (Date)formatter.parse(date);
					 
					 logger.info("Bussiness date -> " + busdate);
					 logger.info("Current date in Seq table-> " + curdate);
					 
				  } catch (ParseException e)
				  {
					  logger.error(e, e);  
				  } 
				  catch (Exception e) {
					  logger.error(e, e);
				}
				  
				  //compare two Dates for equality
				  //If dates are not equal set cycle =0
				  if(curdate.compareTo(busdate) > 0 || curdate.compareTo(busdate) < 0)
				  {
					  logger.info("Dates are not equal");
					  //update the cycle to 0
					  cycle=0;
					  ++cycle;
					  String temp = "00000" + cycle;
					  stan =temp.substring(temp.length()-6, temp.length());
					  
					  mmidManagerDao.updateStan(bussDate, stan, "ImpsStan");

				  }
				  //update the next cycle
				  else
				  {
					  logger.info("First Date and Second Date are equal");
					  ++cycle;
					  String temp = "00000" + cycle;
					  stan =temp.substring(temp.length()-6, temp.length());
					  
					  mmidManagerDao.updateStan(bussDate, stan, "ImpsStan");
				  }
			}
			//curr busy date is null as first time there will be no data inserted
			else
			{
				// update Ta_Seq table for date and stan value
				++cycle;
				stan ="00000" + cycle;
				mmidManagerDao.updateStan(bussDate, stan, "ImpsStan");
			}
	
			logger.info("Value returned for Stan" + "\t" + stan);
		}
		catch (Exception e) 
		{
			logger.error("Exception occurred in generating STAN");
			logger.error(e,e);
		}
		return stan;
	}
	
	//YDDDHHSSSSSS
	//Last digit of the year + Julian date + hour + STAN
	private String getRetRefNo(String stan)
	{
		try
		{
			StringBuilder sb = new StringBuilder();
			Calendar cal=Calendar.getInstance();
			String year=cal.get(Calendar.YEAR) +"";
			String hour = "0" + cal.get(Calendar.HOUR_OF_DAY);
			String dayofyear = "000" + cal.get(Calendar.DAY_OF_YEAR)+"";
			//Fetch last digit of year
			String lastDgtOfyr = year.substring(year.length()-1, year.length());
			sb.append(lastDgtOfyr);
			//fetch Julian date
			String julianDate  = dayofyear.substring(dayofyear.length()-3, dayofyear.length());
			sb.append(julianDate);
			//fetch hour
			String hr  = hour.substring(hour.length()-2, hour.length());
			sb.append(hr);
			//append stan 
			sb.append(stan);
			return sb.toString();
		}
		catch (Exception e) 
		{
			logger.error("Exception occurred in generating Reterival Reference Number for IMPS payment");
			logger.error(e,e);
			return null;
		}
	}
}
