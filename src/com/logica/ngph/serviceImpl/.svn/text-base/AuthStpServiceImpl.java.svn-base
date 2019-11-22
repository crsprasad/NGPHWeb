package com.logica.ngph.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.logica.ngph.dao.AuthStpServiceDao;
import com.logica.ngph.dtos.AuthMsgPolled;
import com.logica.ngph.service.AuthStpService;
import com.logica.ngph.utils.AuthSupMapper;

public class AuthStpServiceImpl implements	AuthStpService {

	static Logger logger = Logger.getLogger(AuthStpServiceImpl.class);
	
	public AuthStpServiceImpl() {
	}

	private AuthStpServiceDao authStpServiceDao;
	public AuthStpServiceDao getAuthStpServiceDao() {
		return authStpServiceDao;
	}

	/**
	 * @param authStpServiceDao the authStpServiceDao to set
	 */
	public void setAuthStpServiceDao(AuthStpServiceDao authStpServiceDao) {
		this.authStpServiceDao = authStpServiceDao;
	}

	/**
	 * This is the main method which is invoked by the verifyMsgQueue. This is
	 * the main processing unit of the Service.
	 */
	public void execute(String key) throws Exception {

		try {
			
			//ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
			//authStpServiceDao = (AuthStpServiceDao)context.getBean("authStpServiceDao");
			
			//ApplicationContext context = new ClassPathXmlApplicationContext("ApplicationContext.xml");
			//authStpServiceDao = (AuthStpServiceDao)ApplicationContextProvider.getBean("authStpServiceDao");

			System.out.println("MsgRef received by Auth STP is : " + key);
			

			List<AuthMsgPolled> vals = authStpServiceDao.getMsgsPolled(key);
			System.out.println("Messages_tx table vals " + vals);

			Map<String, Object> grpres = authStpServiceDao.getGroupInfo(vals,key);
			System.out.println("Ta_AUthSchemam_tx table values " + grpres.size());
			

			/*
			 * Here we check if we dont get any Default supervisor, the system
			 * will stop further processing and log the warning
			 */
			if (grpres.size()>0) 
			{
				ArrayList<Object> supvals = authStpServiceDao.getSupInfo(grpres);
				List<Object> sortedVals = new AuthSupMapper().processMap(supvals);
				
				authStpServiceDao.insertData(sortedVals, grpres, key,vals);
				System.out.println("Auth Table has been populated Appropriately for Message Ref : " + key);
			} 
			else 
			{
				// TODO: Initializationm concept for default supervisor else further log
				System.out.println("Service has been terminated due to non availability of Default supervisor for Message Key : "
						+ key);
			}
		} catch (Exception e) {
			System.out.println("Exception Occured");
			e.printStackTrace();
			logger.debug("Exception Occured in Service Impl");
		}
	}
	
	/*public static void main(String[] args) {
		try {
			new AuthStpServiceImpl().execute("32e785d5-0201-41a2-a250-5fb6ace57bbd");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/
}
