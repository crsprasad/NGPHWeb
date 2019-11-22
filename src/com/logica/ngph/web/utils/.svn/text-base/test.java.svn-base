package com.logica.ngph.web.utils;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.logica.ngph.service.AuthStpService;
import com.logica.ngph.serviceImpl.AuthStpServiceImpl;

public class test {

	/**
	 * @param args
	 */
	
	private AuthStpService authStpService;
	
	/**
	 * @param authStpService the authStpService to set
	 */
	public void setAuthStpService(AuthStpService authStpService) {
		this.authStpService = authStpService;
	}

	public void process()
	{
		try {
			ApplicationContext appcontext = new ClassPathXmlApplicationContext("WebApplicationContext.xml");
			
			authStpService = (AuthStpService)appcontext.getBean("authStpService");

			new AuthStpServiceImpl().execute("32e785d5-0201-41a2-a250-5fb6ace57bbd");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		new test().process();
	}

}
