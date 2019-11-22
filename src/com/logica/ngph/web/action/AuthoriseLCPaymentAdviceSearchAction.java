package com.logica.ngph.web.action;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContextException;

import com.logica.ngph.service.AdviceLCPaymentService;
import com.logica.ngph.web.utils.ApplicationContextProvider;

public class AuthoriseLCPaymentAdviceSearchAction {

	static Logger logger = Logger.getLogger(AuthoriseLCPaymentAdviceSearchAction.class);
	@SuppressWarnings("rawtypes")
	private List searchList;
	private String lcNumber;
	

	

	public String getLcNumber() {
		return lcNumber;
	}


	public void setLcNumber(String lcNumber) {
		this.lcNumber = lcNumber;
	}

	@SuppressWarnings("rawtypes")
	public List getSearchList() {
		return searchList;
	}


	public void setSearchList(@SuppressWarnings("rawtypes") List searchList) {
		this.searchList = searchList;
	}

	public String getSearchData()
	{
		try{
			AdviceLCPaymentService adviceLCPaymentService = (AdviceLCPaymentService) ApplicationContextProvider.getBean("adviceLCPaymentService");			
			setSearchList(adviceLCPaymentService.getAuthoriseLCAdvicePaymentData(getLcNumber()));
			return "populateData";
			
		}catch (NullPointerException  nullPointerException) {
			AuditServiceUtil.logNullPointerException(nullPointerException, logger);
		}
		catch (ApplicationContextException applicationContextException) {
			AuditServiceUtil.logApplicationException(applicationContextException, logger);
		}
		catch (ClassCastException classCastException) {
			AuditServiceUtil.logClassCastException(classCastException, logger)	;
		}
		catch (Exception exception) {
			AuditServiceUtil.logException(exception,logger);
		}
	
		return "input";
		
	}
	
}
