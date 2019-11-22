package com.logica.ngph.web.action;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContextException;

import com.logica.ngph.common.NGPHException;
import com.logica.ngph.dtos.EventManageDto;
import com.logica.ngph.service.EventManagerService;
import com.logica.ngph.web.utils.ApplicationContextProvider;

public class EventSearchAction {
	static Logger logger = Logger.getLogger(EventManagerAction.class);
	private String eventid;
	private List<EventManageDto> searchList;


	public String getEventid() {
		return eventid;
	}

	public void setEventid(String eventid) {
		this.eventid = eventid;
	}

	public String getSearchData() throws NGPHException{
		
		try {
			/*ApplicationContext 	appcontext = (ApplicationContext)context.getAttribute(ConstantUtil.BEAN_CONTEXT);
			SearchService searchService = (SearchService)appcontext.getBean("searchService");*/

			//ApplicationContext appcontext = new ClassPathXmlApplicationContext("WebApplicationContext.xml");
			EventManagerService eventManagerService = (EventManagerService) ApplicationContextProvider.getBean("eventManagerServiceService");
			
			
			
				setSearchList(eventManagerService.eventIDSearch(getEventid()));
		} catch (NullPointerException  nullPointerException) {
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
	
		return "success";
}

public List<EventManageDto> getSearchList() {
	return searchList;
}

public void setSearchList(List<EventManageDto> searchList) {
	this.searchList = searchList;
}

}
