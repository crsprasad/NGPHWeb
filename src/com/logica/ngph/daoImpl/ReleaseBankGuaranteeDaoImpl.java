package com.logica.ngph.daoImpl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.logica.ngph.Entity.BgMast;
import com.logica.ngph.Entity.GenericManager;
import com.logica.ngph.Entity.MsgPolled;
import com.logica.ngph.Entity.NgphCanonical;
import com.logica.ngph.common.enums.EnumBgStatus;
import com.logica.ngph.dao.ReleaseBankGuaranteeDao;
import com.logica.ngph.dtos.ReleaseBankGuaranteeDto;

public class ReleaseBankGuaranteeDaoImpl extends HibernateDaoSupport implements ReleaseBankGuaranteeDao{
	
	private static final int BG_STATUS_RELEASED = Integer.parseInt(EnumBgStatus.RELEASED.getValue());

	
	public NgphCanonical getCanonical(String msgRef) {				
		Session session = null; 
		Transaction tx = null;
		NgphCanonical canonicalData = null;		
		try{			
			session = getHibernateTemplate().getSessionFactory().openSession();
			tx = session.beginTransaction();			
			canonicalData = (NgphCanonical)session.get(NgphCanonical.class, msgRef);			
			tx.commit();			
		}catch(HibernateException exception){
			if(tx!=null){
				tx.rollback();
				exception.printStackTrace();				
			}
		}finally{
			session.close();
		}		

		return canonicalData;
	}


	public BgMast getBgMast(String bgNumber){
		Session session = null; 
		Transaction tx = null;
		BgMast bgMastData = null;
		try{
			session = getHibernateTemplate().getSessionFactory().openSession();
			tx = session.beginTransaction();
			bgMastData = (BgMast)session.get(BgMast.class, bgNumber);
			tx.commit();			
		}catch(HibernateException exception){
			if(tx!=null){
				tx.rollback();
				exception.printStackTrace();				
			}
		}finally{
			session.close();
		}
		return bgMastData;

	}

	public void releaseOrCreateBankGuarantee(ReleaseBankGuaranteeDto releaseBankGuaranteeDto, NgphCanonical ngphCanonical, MsgPolled msgPolled, String txnRef, String messageReference){

		Session session = null; 
		Transaction tx = null;		
		try{
			session = getHibernateTemplate().getSessionFactory().openSession();
			tx = session.beginTransaction();			
			String bgNumber = releaseBankGuaranteeDto.getBgNumber();
			BgMast bgMast = (BgMast)session.get(BgMast.class, bgNumber);
			if(bgMast!=null){
				bgMast.setBgStatus(BG_STATUS_RELEASED);
				session.update(bgMast);
			}else{
				BgMast newBgMast = new BgMast();
				newBgMast.setMsgRef(messageReference);
				newBgMast.setBgNumber(releaseBankGuaranteeDto.getBgNumber());
				newBgMast.setBgStatus(BG_STATUS_RELEASED);
				session.save(newBgMast);
			}			
			session.save(ngphCanonical);
			session.save(msgPolled);
			GenericManager genericManager = (GenericManager)session.get(GenericManager.class, txnRef);
			genericManager.setStatus("A");
			session.update(genericManager);

			tx.commit();

		}catch(Exception exception){
			if(tx!=null){
				tx.rollback();
				exception.printStackTrace();				
			}
		}finally{
			session.close();
		}
	}

	@SuppressWarnings("rawtypes")

	public String getDept(String userId) throws Exception{
		try{
		List branchnDepartment = getHibernateTemplate().find("select userDept,userBranch from SecUsers where user =?",userId);
		List ifsc=null;
		if(branchnDepartment!=null){
			Object[] obj = (Object[]) branchnDepartment.get(0);
			ifsc = getHibernateTemplate().find("select branchIFSCCode from Branches where  branchCode =?",obj[1].toString());
			if(ifsc!=null)
				return ifsc.get(0)+"~"+obj[0]+"~"+obj[1];
		}
		}catch(Exception exception){
			throw exception;
		}
		return null;
	}

}
