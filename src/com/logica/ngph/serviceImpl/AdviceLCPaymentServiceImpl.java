package com.logica.ngph.serviceImpl;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.logica.ngph.Entity.LcCommodity;
import com.logica.ngph.Entity.LcMast;
import com.logica.ngph.Entity.MsgPolled;
import com.logica.ngph.Entity.NgphCanonical;
import com.logica.ngph.common.utils.NGPHUtil;
import com.logica.ngph.dao.AdviceLCPaymentDao;
import com.logica.ngph.dao.LetterOfCreditDao;
import com.logica.ngph.dtos.AuthoriseLCPaymentAdviceDto;
import com.logica.ngph.dtos.LCAdvicePaymentDto;
import com.logica.ngph.dtos.LCCanonicalDto;
import com.logica.ngph.service.AdviceLCPaymentService;

public class AdviceLCPaymentServiceImpl implements AdviceLCPaymentService {
	private static Logger logger = Logger.getLogger(AdviceLCPaymentServiceImpl.class);
	
	AdviceLCPaymentDao adviceLCPaymentDao;
	
	LetterOfCreditDao letterOfCreditDao;
	
	public LetterOfCreditDao getLetterOfCreditDao() {
		return letterOfCreditDao;
	}

	public void setLetterOfCreditDao(LetterOfCreditDao letterOfCreditDao) {
		this.letterOfCreditDao = letterOfCreditDao;
	}

	public AdviceLCPaymentDao getAdviceLCPaymentDao() {
		return adviceLCPaymentDao;
	}

	public void setAdviceLCPaymentDao(AdviceLCPaymentDao adviceLCPaymentDao) {
		this.adviceLCPaymentDao = adviceLCPaymentDao;
	}

	public LCCanonicalDto getLCScreenData(String lcNumber){
		try{
			
			return adviceLCPaymentDao.getLcScreenData(lcNumber);
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
	public List<LCCanonicalDto> getLCAdvicePaymentData(String lcNumber) {
		try{
			
			return adviceLCPaymentDao.getLcAdvicePaymentData(lcNumber);
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public LCCanonicalDto getAuthriseLCScreenData(String lcNumber, String status){
		try{
			
			return adviceLCPaymentDao.getAuthriseLcScreenData(lcNumber,status);
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}	
	}

	public List<LCCanonicalDto> getAuthoriseLCAdvicePaymentData(String lcNumber){
		try{
			return adviceLCPaymentDao.getAuthoriseLcAdvicePaymentData(lcNumber);
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}	


	

	public LCCanonicalDto getAmendLCScreenData(String lcNumber){
		try{			
			return adviceLCPaymentDao.getAmendLCScreenData(lcNumber);
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	public List<LCCanonicalDto> getAdmendLCData(String lcNumber) {
		try{
			
			return adviceLCPaymentDao.getAmendLCData(lcNumber);
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public List<LCCanonicalDto> getLCAuthtoPayData(String lcNumber) {
		try{
			
			return adviceLCPaymentDao.getLCAuthtoPayData(lcNumber);
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	

}
