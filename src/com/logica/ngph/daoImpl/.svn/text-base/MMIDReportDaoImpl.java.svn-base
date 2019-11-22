package com.logica.ngph.daoImpl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.logica.ngph.dao.MMIDReportDao;
import com.logica.ngph.dtos.AccountDto;
import com.logica.ngph.dtos.LCCanonicalDto;

public class MMIDReportDaoImpl extends HibernateDaoSupport implements MMIDReportDao {
	private static Logger logger = Logger.getLogger(MMIDReportDaoImpl.class);

	
	public List<AccountDto> getAccountDetails(String accountNo) {
		
		try{
			List<AccountDto> result = new ArrayList<AccountDto>();
			List rslist = new ArrayList();
			
			if(accountNo.equals("")){
				 rslist = getHibernateTemplate().find("select ac.accountNo, ac.ACCT_ACNAME, ac.MMID, ad.MMID, ad.mobileNo,ad.seq from Account ac,Addresses ad where ac.ACCT_ADDRREF = ad.addressRef ");
			}else{
				 rslist = getHibernateTemplate().find("select ac.accountNo, ac.ACCT_ACNAME, ac.MMID, ad.MMID, ad.mobileNo,ad.seq from Account ac,Addresses ad where ac.ACCT_ADDRREF = ad.addressRef and ac.accountNo=?",accountNo);
			}
			
		
			
			for (int i = 0; i < rslist.size(); i++) {
				AccountDto accountDto = new AccountDto();
	            Object[] obj = (Object[]) rslist.get(i);
	            accountDto.setAccoutNo((String) obj[0]);
	            accountDto.setAccountName((String) obj[1]);
	            if(obj[2].equals(obj[3]) && obj[5].equals("1") ){
	            	accountDto.setIsDefault("YES");
	            }else{
	            	accountDto.setIsDefault("");
	            }
	            accountDto.setMMID((String) obj[3]);
	            //accountDto.setMobileNo((String) obj[3]);
	            accountDto.setMobileNo((String) obj[4]);
	            result.add(accountDto);
	        }
		return result;		
		}catch (Exception e) {
			e.printStackTrace();
			
			return null;
			// TODO: handle exception
		}
	}
	
	
	public List<AccountDto> getAccountForSearch(String accountNo) {
		
		try{
			List<AccountDto> result = new ArrayList<AccountDto>();
			
			List rslist = new ArrayList();
			
			if(accountNo.equals("")){
				 rslist = getHibernateTemplate().find("select distinct ac.accountNo from Account ac,Addresses ad where ac.ACCT_ADDRREF = ad.addressRef ");
			}else{
				accountNo = "%"+accountNo+"%";
				 rslist = getHibernateTemplate().find("select distinct ac.accountNo from Account ac,Addresses ad where ac.ACCT_ADDRREF = ad.addressRef and ac.accountNo like ? ",accountNo);
			}
			
			for (int i = 0; i < rslist.size(); i++) {
				AccountDto accountDto = new AccountDto();	         
	            accountDto.setAccoutNo((String)rslist.get(i));	        
	            result.add(accountDto);
	        }
		return result;		
		}catch (Exception e) {
			e.printStackTrace();
			
			return null;
			// TODO: handle exception
		}
	}

}
