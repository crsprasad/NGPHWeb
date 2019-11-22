package com.logica.ngph.daoImpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.logica.ngph.Entity.Addresses;
import com.logica.ngph.Entity.Branches;
import com.logica.ngph.Entity.BusinessDayM;
import com.logica.ngph.dao.MaintainBranchesDao;
import com.logica.ngph.dtos.LCCanonicalDto;
import com.logica.ngph.dtos.MaintainBranchesDTO;
import com.logica.ngph.web.action.MaintainBranchAction;


public class MaintainBranchesDaoImpl extends HibernateDaoSupport implements MaintainBranchesDao{
	private static Logger logger = Logger.getLogger(MaintainBranchesDaoImpl.class);
	@Transactional(propagation = Propagation.REQUIRED)
	public String saveData(Branches branches,Addresses addresses,String action,String oldRef) {
			logger.info("Inside MaintainBranchesDaoImpl<saveData>");
		SessionFactory fact=getHibernateTemplate().getSessionFactory();
		Session sess=fact.openSession();
		sess.beginTransaction();
		
		try{
			logger.info("Inside MaintainBranchesDaoImpl<saveData>");
			
		    
		BusinessDayM businessDayM  = new BusinessDayM();
		businessDayM.setBranch(branches.getBranchCode());
		businessDayM.setBusinessDay(new Timestamp(Calendar.getInstance().getTime().getTime()));
		businessDayM.setBusinessDayStatus("E");
		
		if(action.equals("ADD")){
		getHibernateTemplate().save(branches);
		getHibernateTemplate().save(addresses);
		getHibernateTemplate().save(businessDayM);
		
		}else if(action.equals("MODIFY"))
		{
			getHibernateTemplate().update(branches);
			getHibernateTemplate().update(addresses);
			



			//String query="update TA_ADDRESSES set ADDR_REF = '"+addresses.getAddressRef()+"' where ADDR_REF ='"+oldRef+"' ";
		//	Query q = sess.createSQLQuery(query);

		//	int result=q.executeUpdate();
		//	System.out.println("No of records updated are : " + result);
			
		}else if(action.equals("DELETE"))
		{
			Query add = sess.createSQLQuery("delete from TA_ADDRESSES where ADDR_REF = :ADDR_REF");
			add.setString("ADDR_REF", oldRef);
			System.out.println("Deleted From TA_ADDRESSES :- "+add.executeUpdate());
			Query branch = sess.createSQLQuery("delete from BRANCHES where BRANCH_CODE = :BRANCH_CODE and BRANCH_ADDRREF = :BRANCH_ADDRREF");
			branch.setString("BRANCH_CODE", branches.getBranchCode());
			branch.setString("BRANCH_ADDRREF",oldRef);
		
			
		}
		sess.getTransaction().commit();
		sess.close();
		fact.close();

		
		
		
		logger.info("End MaintainBranchesDaoImpl<saveData> All Object Closed");

		return "success";
		}catch (Exception e) {

			sess.beginTransaction().rollback();
			sess.close();
			fact.close();
			
		

			logger.error("Exception Occured In MaintainBranchesDaoImpl<saveData>");

			return null;	
		}
		
	}

	
	public String getSequence() {
		
		String key="";
		try{
		SessionFactory fact=getHibernateTemplate().getSessionFactory();
		Session sess=fact.openSession();
		Connection con=sess.connection();
		Query seq = sess.createSQLQuery("select ADDR_SEQ.nextval from dual" );
	    key = seq.uniqueResult().toString();
	    sess.close();
		fact.close();
		con.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return key;
	}

	
	public Boolean isBranchCodePresent(String code) {
		List list = getHibernateTemplate().find("select branchName from Branches where branchCode = ? ",code);
		
		if(list!=null)
		{
			if(list.size()==0)
			return true;
			else 
				return false;
		}
		else
		{
			return false;
		}
		
	}

	
	public List<MaintainBranchesDTO> getALLBranch(String code) {
		
		List<MaintainBranchesDTO> searchList = new ArrayList<MaintainBranchesDTO>();
		if(code == null && StringUtils.isEmpty(code)){
			code = "%";
		}else{
			code = "%"+code+"%";
		}
		
	List list = getHibernateTemplate().find("select br.branchCode,br.branchName,br.branchBicCode,br.branchIFSCCode,ar.buildingDetail," +
			"ar.citySubDivision,ar.department,ar.postalServiceAddress,ar.postalCode,ar.streetName," +
			"ar.townName,br.branchAddressRef,br.branchCategory,ar.subDepartment,ar.postalAddressNature from Branches br , Addresses ar  where br.branchAddressRef = ar.addressRef  and br.branchCode like ?",code);
	for(int i = 0 ;i<list.size();i++)
	{
		MaintainBranchesDTO branchesDTO=  new MaintainBranchesDTO();
		 Object[] obj = (Object[]) list.get(i);
		 branchesDTO.setBranchCode((String) obj[0]);
		 branchesDTO.setBranchName((String) obj[1]);
		branchesDTO.setBranchBIC((String) obj[2]);
		branchesDTO.setBranchIFSC((String) obj[3]);
		branchesDTO.setBuildingNumber((String) obj[4]);
		branchesDTO.setCountrySubDivision((String) obj[5]);
		branchesDTO.setDepartment((String) obj[6]);
		System.out.println((String) obj[7]);
		branchesDTO.setAddressLine(((String) obj[7]));
		branchesDTO.setPostCode(((String) obj[8]));
		branchesDTO.setStreetName(((String) obj[9]));
		branchesDTO.setTownName(((String) obj[10]));
		branchesDTO.setAddressRef((String) obj[11]);
		branchesDTO.setCountryCode((String)obj[12]);
		branchesDTO.setSubDepartment((String) obj[13]);
		System.out.println("ADRRESS TYPE: -"+(String) obj[14]);
		branchesDTO.setAddressType((String) obj[14]);
		 searchList.add(branchesDTO);
	}
		return searchList;
	}
	
	
	

}
