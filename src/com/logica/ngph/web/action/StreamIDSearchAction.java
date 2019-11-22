package com.logica.ngph.web.action;

import java.util.*;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.context.ApplicationContextException;

import com.logica.ngph.common.ConstantUtil;
import com.logica.ngph.service.ReceiverService;
import com.logica.ngph.web.utils.ApplicationContextProvider;
import com.opensymphony.xwork2.ActionSupport;

public class StreamIDSearchAction extends ActionSupport implements SessionAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static Logger logger = Logger.getLogger(StreamIDSearchAction.class);
	private List searchList;
	private String streamID;

	public void setSession(Map<String, Object> arg0) {
		// TODO Auto-generated method stub
		
	}
	public String getSearchData()
	{

		try {
			
			
			//ApplicationContext appcontext = new ClassPathXmlApplicationContext("WebApplicationContext.xml");
			ReceiverService ReceiverService = (ReceiverService)ApplicationContextProvider.getBean("ReceiverService");
			
			setSearchList(ReceiverService.dataSearchForStreamID(getStreamID()));
		} catch (NullPointerException  nullPointerException) {
			logger.debug(ConstantUtil.CODE_NAME_EMPTY, nullPointerException);	
		}
		catch (ApplicationContextException applicationContextException) {
			logger.debug(applicationContextException);	
		}
		catch (ClassCastException classCastException) {
			logger.debug(classCastException);	
		}
		catch (Exception exception) {
			logger.debug(exception);	
		}
		return "populateData";
	}
	public List getSearchList() {
		return searchList;
	}
	public void setSearchList(List searchList) {
		this.searchList = searchList;
	}
	public String getStreamID() {
		return streamID;
	}
	public void setStreamID(String streamID) {
		this.streamID = streamID;
	}
	

}
