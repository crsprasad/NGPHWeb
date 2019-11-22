package com.logica.ngph.web.utils;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


/**
 * @author guptast
 *
 * This Generic Class will Initialize Application Context and Returns bean Name taking beanId as an Argument.
 */

public class ApplicationContextProvider {
	
	static Logger logger = Logger.getLogger(ApplicationContextProvider.class);;
	
	public static ApplicationContext context = null;
	public static Object bean = null;
	
	/**
	 * This Static method will Initialize Application Context Object.
	 */
	public static void initializeContextProvider()
	{
		try
		{
			context = new ClassPathXmlApplicationContext("WebApplicationContext.xml");
			logger.info("Context Object Initialized for WEB APPLICATION");
		}
		catch (Exception e) 
		{
			logger.info("Error in Initializing beans.xml");
			e.printStackTrace();
		}
	}
	
	/**
	 * @param beanId
	 * @return Object
	 * 
	 * This method will return Object(Bean Name) taking beanId as an Argument
	 */
	public static Object getBean(String beanId)
	{
		try
		{
			if(StringUtils.isNotBlank(beanId) && StringUtils.isNotEmpty(beanId) && beanId!=null)
			{
				bean = context.getBean(beanId);
				logger.info("Bean Object : "  + bean);
			}
			else
			{
				logger.info("BeanId is Null");
			}
		}
		catch (Exception e) 
		{
			logger.info("Error in Initializing bean : " + beanId);
			e.printStackTrace();
		}
		return bean;
	}
}
