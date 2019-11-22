package com.logica.ngph.serviceImpl;

import java.util.List;

import org.apache.log4j.Logger;

import com.logica.ngph.Entity.Limits;
import com.logica.ngph.dao.MemberTransactionLimitDao;
import com.logica.ngph.service.MemberTransactionLimitService;

public class MemberTransactionLimitServiceImpl implements MemberTransactionLimitService{

	
	private static Logger logger = Logger.getLogger(MemberTransactionLimitServiceImpl.class);
	MemberTransactionLimitDao memberTransactionLimitDao; 
	
	public MemberTransactionLimitDao getMemberTransactionLimitDao() {
		return memberTransactionLimitDao;
	}

	public void setMemberTransactionLimitDao(
			MemberTransactionLimitDao memberTransactionLimitDao) {
		this.memberTransactionLimitDao = memberTransactionLimitDao;
	}

	
	public List<String> getBankCode() {
		try{
			return memberTransactionLimitDao.getBankCode();
			}catch (Exception e) {
				logger.error(e,e);
				return null;
		}
	}

	
	public Limits getAllDetails(String bankCode) {
		try{
			return memberTransactionLimitDao.getAllDetails(bankCode);
			}catch (Exception e) {
				logger.error(e,e);
				return null;
		}
	}

	
	public String doOperation(Limits limits, String action) {
		try{
			return memberTransactionLimitDao.doOperation(limits,action);
			}catch (Exception e) {
				logger.error(e,e);
				return null;
		}
	}

	
	public Boolean isAllReady(String code) {
		try{
			return memberTransactionLimitDao.isAllReady(code);
			}catch (Exception e) {
				logger.error(e,e);
				return null;
		}
	}

}
