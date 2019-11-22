package com.logica.ngph.service;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;



public class LoadService {
	static Logger logger = Logger.getLogger(LoadService.class);
	SearchService searchService = null;
	public  SearchService getDataSourceContext(){
		
		try{
		
		ApplicationContext appcontext = new ClassPathXmlApplicationContext("resources/DataSource.xml");
		 searchService  = (SearchService)appcontext.getBean("searchService");
		}catch(Exception ex){
			logger.debug(ex);
		}
		return searchService;
	}

}
