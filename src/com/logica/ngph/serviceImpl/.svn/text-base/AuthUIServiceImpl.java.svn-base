package com.logica.ngph.serviceImpl;

import org.apache.commons.lang.StringUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.logica.ngph.dao.AuthUIDao;
import com.logica.ngph.service.AuthUIService;

public class AuthUIServiceImpl implements AuthUIService{

	//static Logger logger = Logger.getLogger(AuthUIServiceImpl.class);
	
	private AuthUIDao authUIDao;
	/**
	 * @return the authUIDao
	 */
	public AuthUIDao getAuthUIDao() {
		return authUIDao;
	}
	/**
	 * @param authUIDao the authUIDao to set
	 */
	public void setAuthUIDao(AuthUIDao authUIDao) {
		this.authUIDao = authUIDao;
	}
	
	public void initialize()
	{
		//ApplicationContext context = new ClassPathXmlApplicationContext("ApplicationContext.xml");
		//AuthUIService = (AuthUIDao) context.getBean("authUIService");
	}
	
	
	public static void main(String[] args) {
		
	
		ApplicationContext context = new ClassPathXmlApplicationContext("ApplicationContext.xml");
		AuthUIServiceImpl authUIServiceImpl= (AuthUIServiceImpl) context.getBean("authUIService");
		AuthUIDao authUIDao= (AuthUIDao) context.getBean("authUIDao");

		// Action is comments
		//authUIServiceImpl.updateTable("103", "7351e840-be2d-4684-a54b-7e49b95ef713000001", "hello");
		
		// Action is Reject
		authUIServiceImpl.updateTable("103", "7351e840-be2d-4684-a54b-7e49b95ef713000001", "R");
		
		// Action is Authorize
		//authUIServiceImpl.updateTable("103", "7351e840-be2d-4684-a54b-7e49b95ef713000001", "A");

	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	public String updateTable(String supId, String authRef, String action)
	{
		String response=null;
		try
		{
		
		// Checking for the null values
		if(StringUtils.isEmpty(supId) && StringUtils.isEmpty(authRef) && StringUtils.isEmpty(action))
		{
			//logger.info("Empty Values received by AuthUIServiceImpl");
			System.out.println("Empty Values received by AuthUIServiceImpl");
			return response;
		}
		
		// if values are not null
		else
		{
			// if action is "A" then update status to Authorize
			if(action.equalsIgnoreCase("A"))
			{
				System.out.println("Action is Authorize");
				response= authUIDao.updateTableforAuthorize(supId,authRef, action);

			}
			// if action is "R" then update status to reject
			else if(action.equalsIgnoreCase("R"))
			{
				System.out.println("Action is Reject");
				response=authUIDao.updateTableforReject(supId,authRef, action);
				
			}
			// Only the notes has to be updated and no action is required
			else
			{  
				System.out.println("Action is only notes");
				response=authUIDao.updateNotes(supId,authRef, action);
				
			}
			
		}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}
}
