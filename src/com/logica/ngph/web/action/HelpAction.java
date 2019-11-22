package com.logica.ngph.web.action;


import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.context.ApplicationContextException;

import com.logica.ngph.common.NGPHException;
import com.logica.ngph.dtos.HelpData;

import com.logica.ngph.service.HelpService;
import com.logica.ngph.web.utils.ApplicationContextProvider;
import com.opensymphony.xwork2.ActionSupport;

public class HelpAction extends ActionSupport implements SessionAware{
	private Map<String, Object> session;
	
	static Logger logger = Logger.getLogger(HelpAction.class);
	private static final long serialVersionUID = 1L;
	
	    
	    // It returns minor version which includes major version no in it

	private List<HelpData> helpList;
	
	
	
	public String fetchHelpData() throws NGPHException {
		
		try {
			logger.info("in HelpAction < fetchHelpData > ");

			HelpService helpService = (HelpService) ApplicationContextProvider.getBean("helpService");

			
			helpList  = helpService.fetchHelpData();			
			session.put("helpList", helpList);			
			//session.put("MINOR VERSION",(Object) helpList.get(0).getMinorVersion());
			//session.put("BUILD VERSION",(Object)helpList.get(0).getMajorVersion());
			//session.put("PRODUCT NAME",(Object)helpList.get(0).getProductName());
			//session.put("PRODUCT CODE",(Object)helpList.get(0).getProductCode());
			
		
			
			logger.info("MINOR VERSION is "+session.get("MINOR VERSION"));
			return "fetchData";
		} catch (NullPointerException nullPointerException) {
			AuditServiceUtil.logNullPointerException(nullPointerException,
					logger);
		} catch (ApplicationContextException applicationContextException) {
			AuditServiceUtil.logApplicationException(
					applicationContextException, logger);
		} catch (ClassCastException classCastException) {
			AuditServiceUtil.logClassCastException(classCastException, logger);
		} catch (Exception exception) {
			AuditServiceUtil.logException(exception, logger);
		}

		return "input";

	}



	public void setHelpList(List<HelpData> helpList) {
		this.helpList = helpList;
	}



	public List<HelpData> getHelpList() {
		return helpList;
	}
	public Map<String, Object> getSession() {
		return session;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
	





	

}
