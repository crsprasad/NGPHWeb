package com.logica.ngph.web.action;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.context.ApplicationContextException;

import com.logica.ngph.common.ConstantUtil;
import com.logica.ngph.dtos.ReceiverDto;
import com.logica.ngph.service.ReceiverService;
import com.logica.ngph.web.utils.ApplicationContextProvider;

import com.opensymphony.xwork2.ActionSupport;

public class ReceiverAction extends ActionSupport implements SessionAware  {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
static Logger logger = Logger.getLogger(SearchAction.class);
	
	
	private String code;
	private String name;
	@SuppressWarnings("rawtypes")
	private List<ReceiverDto> searchList;
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	

	public List<ReceiverDto> getSearchList() {
		return searchList;
	}

	public void setSearchList(List<ReceiverDto> searchList) {
		this.searchList = searchList;
	}

	public void setSession(Map<String, Object> arg0) {
		// TODO Auto-generated method stub
		
	}
	/*This Method Search the ReceiverBank code and ReceiverBank name according to the selected values
	 *  
	 * 
	 */
	/*ReceiverService ReceiverService;
	

	public void setReceiverService(ReceiverService receiverService) {
		ReceiverService = receiverService;
	}*/

	public String getSearchData()
	{

		try {
			
			
			//ApplicationContext appcontext = new ClassPathXmlApplicationContext("WebApplicationContext.xml");
			ReceiverService ReceiverService = (ReceiverService)ApplicationContextProvider.getBean("ReceiverService");
			
			setSearchList(ReceiverService.dataSearch(getCode(), getName()));
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
	
}
