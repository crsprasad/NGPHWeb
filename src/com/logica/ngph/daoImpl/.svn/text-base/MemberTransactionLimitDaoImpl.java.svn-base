package com.logica.ngph.daoImpl;

import java.math.BigDecimal;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.logica.ngph.Entity.Limits;
import com.logica.ngph.dao.MemberTransactionLimitDao;

public class MemberTransactionLimitDaoImpl extends HibernateDaoSupport implements MemberTransactionLimitDao {

	
	public List<String> getBankCode() {
		List list = getHibernateTemplate().find("select bankCode from BankBinMap");
		return list;
	}

	
	public Limits getAllDetails(String bankCode) {
		
		SessionFactory fact=getHibernateTemplate().getSessionFactory();
		Session sess=fact.openSession();
		Connection con=sess.connection();
		Limits limits = (Limits)sess.get(Limits.class, bankCode);
	try{
		sess.close();
		con.close();
		fact.close();
	}catch (Exception e) {
		e.printStackTrace();
	}
		
		
		
		return limits;
	}

	
	public String doOperation(Limits limits, String action) {
		try{
			
			String avilableCreditLimit = null;
			String availableDebitLimit = null;
		if(action.equals("ADD")){
			try{
			String insertLimit="insert into TA_LIMITS (IDENTIFIER,LIMIT_FOR,CREDIT_LIMIT,DEBIT_LIMIT,AVAILABLE_CRLIMIT,AVAILABLE_DRLIMIT,CHANNEL) values (:IDENTIFIER,:LIMIT_FOR,:CREDIT_LIMIT,:DEBIT_LIMIT,:AVAILABLE_CRLIMIT,:AVAILABLE_DRLIMIT,:CHANNEL)";
			
			Session sess = getHibernateTemplate().getSessionFactory().openSession();
			Query insertLimitQuery = sess.createSQLQuery(insertLimit);
				insertLimitQuery.setString("IDENTIFIER", limits.getIdentifier());
				insertLimitQuery.setString("LIMIT_FOR", limits.getLimitFor());
				insertLimitQuery.setString("CREDIT_LIMIT", limits.getCreditLimit());
				insertLimitQuery.setString("DEBIT_LIMIT", limits.getDebitLimit());
				insertLimitQuery.setString("AVAILABLE_CRLIMIT", limits.getCreditLimit());
				insertLimitQuery.setString("AVAILABLE_DRLIMIT", limits.getDebitLimit());
				insertLimitQuery.setString("CHANNEL", limits.getChannel());
				insertLimitQuery.executeUpdate();
			sess.close();
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(action.equals("MODIFY"))
		{try{
			Session sess = getHibernateTemplate().getSessionFactory().openSession();
			List identifirList = new ArrayList<String>();
			
			Session session1 = getHibernateTemplate().getSessionFactory().openSession();
			Query Query = session1.createSQLQuery("select AVAILABLE_CRLIMIT,AVAILABLE_DRLIMIT from TA_LIMITS where IDENTIFIER = :identifier");
			Query.setString("identifier", limits.getIdentifier());
			identifirList = Query.list();
			session1.close();
			if(!identifirList.isEmpty()&& identifirList!=null && identifirList.size()>0 && identifirList.get(0)!=null)
			{
				
				Object[] obj = (Object[]) identifirList.get(0);
				if(obj[0]!=null && !obj[0].equals(null)){
					BigDecimal availableCredit = new BigDecimal(obj[0].toString());
					BigDecimal Creditlimt = new BigDecimal(limits.getCreditLimit());
					
					if(availableCredit.compareTo(Creditlimt)==1){
							avilableCreditLimit = limits.getCreditLimit();
					}
					else
					{
						avilableCreditLimit = obj[0].toString();
					//availableDebitLimit  = obj[1].toString();
					}
				}else
				{
					avilableCreditLimit = limits.getCreditLimit();
				}
				if(obj[1]!=null && !obj[1].equals(null)){
					BigDecimal availabledebit = new BigDecimal(obj[1].toString());
					BigDecimal debitlimt = new BigDecimal(limits.getDebitLimit());
					if(availabledebit.compareTo(debitlimt)==1){
						
						availableDebitLimit  = limits.getDebitLimit();
					}
					else
					{
					//avilableCreditLimit = obj[0].toString();
					availableDebitLimit  = obj[1].toString();
					}
					
				}else
				{
					availableDebitLimit = limits.getDebitLimit();
				}
				
			}else
			{
				avilableCreditLimit = limits.getCreditLimit();
				availableDebitLimit = limits.getDebitLimit();
				
			}
			
			Query insertLimitQuery = sess.createSQLQuery("update TA_LIMITS set IDENTIFIER = :IDENTIFIER,LIMIT_FOR=:LIMIT_FOR,CREDIT_LIMIT=:CREDIT_LIMIT,DEBIT_LIMIT=:DEBIT_LIMIT,CHANNEL=:CHANNEL,AVAILABLE_CRLIMIT=:AVAILABLE_CRLIMIT,AVAILABLE_DRLIMIT=:AVAILABLE_DRLIMIT where IDENTIFIER=:IDENTIFIER");
				insertLimitQuery.setString("IDENTIFIER", limits.getIdentifier());
				insertLimitQuery.setString("LIMIT_FOR", limits.getLimitFor());
				insertLimitQuery.setString("CREDIT_LIMIT", limits.getCreditLimit());
				insertLimitQuery.setString("DEBIT_LIMIT", limits.getDebitLimit());
				insertLimitQuery.setString("AVAILABLE_CRLIMIT", avilableCreditLimit);
				insertLimitQuery.setString("AVAILABLE_DRLIMIT", availableDebitLimit);
				insertLimitQuery.setString("CHANNEL", limits.getChannel());
				insertLimitQuery.executeUpdate();
			sess.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
		}
		else if(action.equals("DELETE"))
				{
				getHibernateTemplate().delete(limits);
				}
		return "success";
		}catch (Exception e) {
			e.printStackTrace();
			
			return "error";
		}
		
	}

	
	public Boolean isAllReady(String bankCode) {
		List list = getHibernateTemplate().find("select identifier from Limits where identifier = ?",bankCode);
		if(list.isEmpty())
		{
			return true;
		}
		else
		return false;
	}
	

}
