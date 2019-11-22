package com.logica.ngph.daoImpl;

import java.math.BigDecimal;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.logica.ngph.Entity.Account;
import com.logica.ngph.Entity.Addresses;
import com.logica.ngph.Entity.Limits;
import com.logica.ngph.common.ConstantUtil;
import com.logica.ngph.common.NGPHException;
import com.logica.ngph.dao.AccountMaintenaceDao;
import com.logica.ngph.dtos.AccountDto;
import com.logica.ngph.dtos.AddressDto;
import com.logica.ngph.dtos.SearchRuleIDDtos;
import com.logica.ngph.web.action.AccountMaintenanceAction;

public class AccountMaintenaceDaoImpl extends HibernateDaoSupport implements AccountMaintenaceDao {
	static Logger logger = Logger.getLogger(AccountMaintenanceAction.class);
	@Transactional(propagation=Propagation.REQUIRED)
	public String saveRule(String account,String MMID) throws SQLException {
	logger.info("Inside AccountMaintenaceDaoImpl<saveRule>");	
	int count=0;
	List check = getHibernateTemplate().find("select accountNo,MMID from Account where accountNo=? and MMID=? ",account,MMID);
	for(int i =0 ;i<check.size();i++){
		count=1;
		break;
		}
	String returnvalue = "";
	if(count==1)
	{
		returnvalue= "AllreadyPresent";
	}
	
	else{
		returnvalue = "success";
	}
	logger.info("End AccountMaintenaceDaoImpl<saveRule>");
		return returnvalue;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void updateRule(Account account,Limits limits,String branch,String accountType) throws SQLException {
		logger.info("Inside AccountMaintenaceDaoImpl<updateRule>");
		SessionFactory fact=getHibernateTemplate().getSessionFactory();
		Session sess=fact.openSession();
		sess.beginTransaction();
		Connection con=sess.connection();
		String avilableCreditLimit = null;
		String availableDebitLimit = null;
		
		try{
			
			List identifirList = new ArrayList<String>();
			
			Session session1 = getHibernateTemplate().getSessionFactory().openSession();
			try{
			Query Query = session1.createSQLQuery("select AVAILABLE_CRLIMIT,AVAILABLE_DRLIMIT from TA_LIMITS where IDENTIFIER = :identifier");
			Query.setString("identifier", branch+account.getAccountNo());
			identifirList = Query.list();
			session1.close();
			}catch (Exception e) {
				e.printStackTrace();
				session1.close();
			}
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
			
		//Delete all ready existing record for particular account from limits 
		
		Query deleteq = sess.createSQLQuery("delete from TA_LIMITS where IDENTIFIER = :identifier ");
		deleteq.setString("identifier", branch+account.getAccountNo());
		deleteq.executeUpdate();
		//System.out.println("No of records deleted are : " + deleteq.executeUpdate());
		
		Query query=sess.createQuery("update Account set MMID=:MMID ,ACCT_ACTYPE =:ACCT_ACTYPE,ACCT_OWN_BRANCH=:ACCT_OWN_BRANCH where accountNo=:ACCT_NUM  ");
			query.setParameter("MMID",account.getMMID());
			query.setParameter("ACCT_ACTYPE", accountType);	
			query.setParameter("ACCT_OWN_BRANCH", branch);	
			query.setParameter("ACCT_NUM", account.getAccountNo());	
			query.executeUpdate();
	//	System.out.println("Record Update : - "+query.executeUpdate());
		
			String insertLimit="insert into TA_LIMITS (IDENTIFIER,LIMIT_FOR,CREDIT_LIMIT,DEBIT_LIMIT,AVAILABLE_CRLIMIT,AVAILABLE_DRLIMIT) values (:IDENTIFIER,:LIMIT_FOR,:CREDIT_LIMIT,:DEBIT_LIMIT,:AVAILABLE_CRLIMIT,:AVAILABLE_DRLIMIT)";
			Query insertLimitQuery = sess.createSQLQuery(insertLimit);
				insertLimitQuery.setString("IDENTIFIER", limits.getIdentifier());
				insertLimitQuery.setString("LIMIT_FOR", limits.getLimitFor());
				insertLimitQuery.setString("CREDIT_LIMIT", limits.getCreditLimit());
				insertLimitQuery.setString("DEBIT_LIMIT", limits.getDebitLimit());
				insertLimitQuery.setString("AVAILABLE_CRLIMIT", avilableCreditLimit);
				insertLimitQuery.setString("AVAILABLE_DRLIMIT", availableDebitLimit);
				insertLimitQuery.executeUpdate();
			//System.out.println("No of records updated  LImit are : " + insertLimitQuery.executeUpdate());
		sess.getTransaction().commit();
		sess.close();
		fact.close();
		con.close();
		
		logger.info("End AccountMaintenaceDaoImpl<updateRule>");
		}
		catch (Exception e) {
			
			logger.error("Exception Occured In AccountMaintenaceDaoImpl<updateRule> : - "+e);
			sess.getTransaction().rollback();
			sess.close();
			fact.close();
			con.close();
		}
		
	}

	@Transactional(propagation=Propagation.REQUIRED)
	public void deleteRule(Account account) throws SQLException {
		logger.info("Inside AccountMaintenaceDaoImpl<deleteRule>");
		getHibernateTemplate().delete(account);
		
	}

	
	public List<AccountDto> accountSearch(String code,String branch) throws NGPHException {
		logger.info("Inside AccountMaintenaceDaoImpl<accountSearch>");
		List<AccountDto> searchList = new ArrayList<AccountDto>();
		
		if(code == null){
			code = "%";
		}else{
			code = "%"+code+"%";
		}
		
		@SuppressWarnings("rawtypes")
		List accountNo = 	getHibernateTemplate().find("select accountNo,MMID,ACCT_OWN_BRANCH from com.logica.ngph.Entity.Account where accountNo like ? and ACCT_OWN_BRANCH =?",code,branch);
		
		for (int i = 0; i < accountNo.size(); i++) {
			AccountDto accountDto = new AccountDto();
            Object[] obj = (Object[]) accountNo.get(i);

            
            accountDto.setAccoutNo((String) obj[0]);
            accountDto.setMMID((String) obj[1]);
            accountDto.setACCT_OWN_BRANCH((String) obj[2]);
             
            searchList.add(accountDto);
        }
		logger.info("End AccountMaintenaceDaoImpl<accountSearch>");
			return searchList;
	}

	public String accountNOAllreadyExit(String accoutnNO) {
		logger.info("Inside AccountMaintenaceDaoImpl<accountNOAllreadyExit>");
		List accountNo = 	getHibernateTemplate().find("select accountNo from com.logica.ngph.Entity.Account where accountNo like ?",accoutnNO);
		int count=0;
		for (int i = 0; i < accountNo.size(); i++) {
			count=1;
		}
		String forwardvalue="";
		if(count==0)
		{
			forwardvalue = "NotAvailable";
		}
		else
			forwardvalue = "Available";
		logger.info("End AccountMaintenaceDaoImpl<accountNOAllreadyExit>");
		return forwardvalue;
	}

	
	public String getLocnBin(String userID) {
		logger.info("Inside AccountMaintenaceDaoImpl<getLocnBin>");
		String returnString="";
		Object[] obj = null;
		List getList = getHibernateTemplate().find("select userBranch,userDept from SecUsers where user like ? ",userID);
		if(!getList.isEmpty())
		obj = (Object[]) getList.get(0);
		
		String LOCNBIN = "LOCNBIN";
		List list= getHibernateTemplate().find("select initValue from Initialisation where initBranch like ? and initEntry =?",(String) obj[0],LOCNBIN);
		if(!list.isEmpty())
		{
			returnString=list.get(0).toString();
		}
		else
		{
			List val= getHibernateTemplate().find("select initValue from Initialisation where initEntry =?",LOCNBIN);
			if(!val.isEmpty())
			{
				returnString=val.get(0).toString();
			}
			
		}
		logger.info("End AccountMaintenaceDaoImpl<getLocnBin>");
		return returnString;
	}

	public List<AddressDto> getGridView() throws NGPHException {
		logger.info("Inside AccountMaintenaceDaoImpl<getGridView>");
		List<AddressDto> searchList = new ArrayList<AddressDto>();
		
		
		@SuppressWarnings("rawtypes")
		List addressList = 	getHibernateTemplate().find("select mobileNo,MMID from Addresses");
		
		for (int i = 0; i < addressList.size(); i++) {
			AddressDto address = new AddressDto();
            Object[] obj = (Object[]) addressList.get(i);

            
            address.setMobileNo((String) obj[0]);
            address.setMMID((String) obj[1]);
            
             
            searchList.add(address);
        }
		logger.info("End AccountMaintenaceDaoImpl<getGridView>");
			return searchList;
	}


	public List<AddressDto> getGridViewAdd(String accountNo,String mobileNo, String MMID)
			throws NGPHException {
		logger.info("Inside AccountMaintenaceDaoImpl<getGridViewAdd>");
		
		List authRef = getHibernateTemplate().find("select ACCT_ADDRREF from Account where accountNo like ?",accountNo);
List<AddressDto> searchList = new ArrayList<AddressDto>();
		
		
		@SuppressWarnings("rawtypes")
		List addressList = 	getHibernateTemplate().find("select mobileNo,MMID from Addresses where addressRef like ?",authRef.get(0));
		
		for (int i = 0; i < addressList.size(); i++) {
			AddressDto address = new AddressDto();
			if(i < addressList.size()){
				Object[] obj = (Object[]) addressList.get(i);
				address.setMobileNo((String) obj[0]);
				address.setMMID((String) obj[1]);
			}
			else
			{
				address.setMobileNo(mobileNo);
	            address.setMMID(MMID);
			}
            searchList.add(address);
        }
		logger.info("End AccountMaintenaceDaoImpl<getGridViewAdd>");
			return searchList;
	}

	@Transactional(propagation=Propagation.REQUIRED)
	public String update(String accountNo,String mobileNO, String MMID,String flag) throws SQLException {
		logger.info("Inside AccountMaintenaceDaoImpl<update>");
		String key = null;
		String addressRef = null;
		String mmid= null;
		List checkMMID =null;
		if(flag!=null && !flag.equals("null"))
		 checkMMID = getHibernateTemplate().find("select MMID from Addresses where MMID =? and mobileNo =?",MMID,mobileNO);
		if(checkMMID!=null && checkMMID.size() > 0)
		{
			String maxMMID=getHibernateTemplate().find("select max(MMID) from Addresses where mobileNo = ?",mobileNO).get(0).toString();
			int id =Integer.parseInt(maxMMID);
			id=id+1;
			mmid=id+"";
		}
		else
		{
			mmid=MMID;
		}
			
		List getAddRef = getHibernateTemplate().find("select ACCT_ADDRREF from Account where accountNo like ? and accountNo IS NOT NULL",accountNo);
		if(getAddRef!=null && !getAddRef.isEmpty() && getAddRef.size()>0 && getAddRef.get(0)!=null){
			addressRef = getAddRef.get(0).toString();
		}
		else
		{
			Session session = getHibernateTemplate().getSessionFactory().openSession();
			try{
			
			Query seq = session.createSQLQuery("select ADDR_SEQ.nextval from dual" );
		    key = seq.uniqueResult().toString();
			String concatString = "000000"+key;
			addressRef = "ADDR"+concatString.substring(concatString.length()-7,concatString.length());
			session.close();
			}catch (Exception e) {
				session.close();
			}
		}
		List getList = getHibernateTemplate().find("select addressRef from Addresses where addressRef like ? and mobileNo = ? and  MMID = ?",addressRef,mobileNO,MMID);
		if(getList.size()==0){
			Transaction tx = null;
			SessionFactory fact=getHibernateTemplate().getSessionFactory();
			Session sess=fact.openSession();
			
			tx = sess.beginTransaction();	
			try{
				
				Query update = sess.createQuery("update Account set ACCT_ADDRREF =:ACCT_ADDRREF where  accountNo = :accountNo");
				update.setString("ACCT_ADDRREF", addressRef);
				update.setString("accountNo", accountNo);
				update.executeUpdate();	
				String query="insert into TA_ADDRESSES (ADDR_REF,ADDR_MOBILE,ADDR_MMID) values (:ADDR_REF,:ADDR_MOBILE,:ADDR_MMID )";
				Query q = sess.createSQLQuery(query);
				q.setString("ADDR_REF", addressRef);
				q.setString("ADDR_MOBILE", mobileNO);
				q.setString("ADDR_MMID", mmid);

				int result=q.executeUpdate();
			System.out.println("No of records updated are : " + result);
			tx.commit();
			
			logger.info("End AccountMaintenaceDaoImpl<update> All Objects Closed");
		}
		catch (Exception e) 
		{
			logger.error("Exception Occured In AccountMaintenaceDaoImpl<update> : - "+e);
			tx.rollback();
		}
		finally
		{
			sess.close();
			fact.close();
		}
		}
		
		
		/*if(getAddRef.size()!=0 && !getAddRef.isEmpty() && !getAddRef.equals(null) ){
			Addresses addresses = new Addresses();
			addresses.setAddressRef(getAddRef.get(0).toString());
			addresses.setMMID(MMID);
			addresses.setMobileNo(mobileNO);
		
			getHibernateTemplate().saveOrUpdate(addresses);
		}*/
		return "success";
		
		
	}

	
	
	@Transactional(propagation=Propagation.REQUIRED)
	public String deleteAddressRef(String accountNo,String mobileNo,String MMID) throws SQLException {
		logger.info("Inside AccountMaintenaceDaoImpl<deleteAddressRef>");
		
		List getAddRef = getHibernateTemplate().find("select ACCT_ADDRREF from Account where accountNo like ? and accountNo IS NOT NULL",accountNo);
		List getList = getHibernateTemplate().find("select addressRef from Addresses where addressRef like ? and mobileNo = ? and  MMID = ?",getAddRef.get(0).toString(),mobileNo,MMID);
		if(getList.size()!=0){
			SessionFactory fact=getHibernateTemplate().getSessionFactory();
			Session sess=fact.openSession();
			
			try{
			String query="delete from TA_ADDRESSES where addr_mobile = :addr_mobile and addr_mmid =:addr_mmid and addr_ref=:addr_ref";
			Query q = sess.createSQLQuery(query);
			q.setParameter("addr_mobile", mobileNo);
			q.setParameter("addr_mmid",MMID);
			q.setParameter("addr_ref", getAddRef.get(0).toString());
			int result=q.executeUpdate();
			System.out.println("No of records updated are : " + result);
			
			sess.close();
			fact.close();
			
			}catch (Exception e) {
				logger.error("Exception Occured In AccountMaintenaceDaoImpl<deleteAddressRef> :- "+e);
				sess.close();
				fact.close();
				
			}
		}
		logger.info("End AccountMaintenaceDaoImpl<deleteAddressRef>");
		return "success";
	}



	public List<AddressDto> showGridView(String accountNo,String brach) throws NGPHException {
		logger.info("Inside AccountMaintenaceDaoImpl<showGridView>");
		List<AddressDto> searchList = new ArrayList<AddressDto>();
		List authRef = getHibernateTemplate().find("select ACCT_ADDRREF,ACCT_OWN_BRANCH,ACCT_ACTYPE from Account where accountNo like ? and ACCT_OWN_BRANCH =? and ACCT_OWN_BRANCH is not null",accountNo,brach);
		if(authRef!=null && !authRef.isEmpty()){
		Object[] objREf = (Object[]) authRef.get(0);
		
		
		List limit = getHibernateTemplate().find("select creditLimit,debitLimit from Limits where identifier = ?",objREf[1]+accountNo);
		Object[] objlimit = null;
		if(limit!=null && !limit.isEmpty()){
		objlimit = (Object[]) limit.get(0);
		}
		@SuppressWarnings("rawtypes")
		List addressList = 	getHibernateTemplate().find("select mobileNo,MMID from Addresses where addressRef like ?",objREf[0]);
		
		for (int i = 0; i < addressList.size(); i++) {
			AddressDto address = new AddressDto();
			if(i < addressList.size()){
            Object[] obj = (Object[]) addressList.get(i);

            
            address.setMobileNo((String) obj[0]);
            address.setMMID((String) obj[1]);
            if(limit!=null && !limit.isEmpty()   ){
            if(objlimit[0]!=null)	
            address.setCreditLimit(objlimit[0].toString());
            if(objlimit[1]!=null)
            address.setDebitLimit(objlimit[1].toString());
            if( objREf[2]!=null)
            address.setAccountType(objREf[2].toString());
            if( objREf[1]!=null )
            address.setOwnBranch(objREf[1].toString());
            }
            
			}
			
             
            searchList.add(address);
        }
		}else
			logger.error("No Record Found In AccountMaintenaceDaoImpl<showGridView>");
		logger.info("End AccountMaintenaceDaoImpl<showGridView>");
			return searchList;
	}


	
	public String updateSequence(String MMID, String branch,int sequence)  {
		logger.info("Inside AccountMaintenaceDaoImpl<updateSequence>");
		
		try{
		
		Session sess=getHibernateTemplate().getSessionFactory().openSession();
		
		String[] split = MMID.split(",");
		Query q = sess.createSQLQuery("update TA_ADDRESSES set ADDR_SEQ = :ADDR_SEQ , ADDR_OWN_BRANCH = :ADDR_OWN_BRANCH  where ADDR_MMID =:ADDR_MMID and ADDR_MOBILE=:ADDR_MOBILE");
		q.setInteger("ADDR_SEQ", sequence);
		q.setString("ADDR_OWN_BRANCH", branch);
		q.setString("ADDR_MMID", split[0]);
		q.setString("ADDR_MOBILE", split[1]);
		int result=q.executeUpdate();
		//System.out.println("No of records updated are : " + result);
		
		sess.close();
		
		
		} catch (Exception sqlException) {
			logger.debug(sqlException);
			
		}
		logger.info("End AccountMaintenaceDaoImpl<updateSequence>");
		return "success";
	}


	
	public String getMMID(String mobileNo,String MMID) {
		logger.info("Inside AccountMaintenaceDaoImpl<getMMID>");
		
		String mmid = getHibernateTemplate().find("select count(MMID) from Addresses where mobileNo = ? and MMID = ?",mobileNo,MMID).get(0).toString();
		if(mmid.equals("0"))
				return "";
		else
		{
			mmid=getHibernateTemplate().find("select max(MMID) from Addresses where mobileNo = ?",mobileNo).get(0).toString();
			logger.info("End AccountMaintenaceDaoImpl<getMMID>");
			return mmid;
		}
		}


	public String setASDefault(String accountNo) {
		logger.info("Inside AccountMaintenaceDaoImpl<setASDefault>");
		String defaultValue= getHibernateTemplate().find("select MMID from Account where accountNo like ?",accountNo).get(0).toString();
		logger.info("End AccountMaintenaceDaoImpl<setASDefault>");
		return defaultValue;
	}

	public List<AccountDto> benificaryAccountSearch(String code) {
List<AccountDto> searchList = new ArrayList<AccountDto>();
		
		if(code == null){
			code = "%";
		}else{
			code = "%"+code+"%";
		}
		
		@SuppressWarnings("rawtypes")
		List accountNo = 	getHibernateTemplate().find("select accountNo,MMID,ACCT_OWN_BRANCH from com.logica.ngph.Entity.Account where accountNo like ? ",code);
		
		for (int i = 0; i < accountNo.size(); i++) {
			AccountDto accountDto = new AccountDto();
            Object[] obj = (Object[]) accountNo.get(i);

            
            accountDto.setAccoutNo((String) obj[0]);
            accountDto.setMMID((String) obj[1]);
            accountDto.setACCT_OWN_BRANCH((String) obj[2]);
             
            searchList.add(accountDto);
        }
		logger.info("End AccountMaintenaceDaoImpl<accountSearch>");
			return searchList;
	}

	
	
	
}
