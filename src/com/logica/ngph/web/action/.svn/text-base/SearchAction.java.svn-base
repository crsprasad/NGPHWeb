package com.logica.ngph.web.action;


import java.util.List;



import org.apache.log4j.Logger;

import org.springframework.context.ApplicationContextException;




import com.logica.ngph.common.NGPHException;

import com.logica.ngph.service.SearchService;
import com.logica.ngph.web.utils.ApplicationContextProvider;



public class SearchAction {
	
	
	static Logger logger = Logger.getLogger(SearchAction.class);
	private String code;
	private String description;
	@SuppressWarnings("rawtypes")
	private List searchList;
	private String action;
	private String actionParam;
	private String direction;
public String getDirection() {
		return direction;
	}


	public void setDirection(String direction) {
		this.direction = direction;
	}


public String getActionParam() {
		return actionParam;
	}


	public void setActionParam(String actionParam) {
		this.actionParam = actionParam;
	}


	//	private ServletContext context;
	//private SearchService searchService;
	public String getAction() {
		return action;
	}


	public void setAction(String action) {
		this.action = action;
	}


	public String getSearchData(){
	
			try {
				/*ApplicationContext 	appcontext = (ApplicationContext)context.getAttribute(ConstantUtil.BEAN_CONTEXT);
				SearchService searchService = (SearchService)appcontext.getBean("searchService");*/
				System.out.println("Action :- "+getAction());
				//ApplicationContext appcontext = new ClassPathXmlApplicationContext("WebApplicationContext.xml");
				SearchService searchService  =(SearchService)	ApplicationContextProvider.getBean("searchService");
			
				
				
					setSearchList(searchService.dataSearch(getCode(), getDescription(),getAction(),getDirection()));
			} catch (NGPHException ngphException) {
				AuditServiceUtil.logNgphException(ngphException,logger);
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
		
			return "populateData";
	}



/*	public void setSearchService(SearchService searchService) {
		this.searchService = searchService;
	}
*/

	@SuppressWarnings("rawtypes")
	public List getSearchList() {
		return searchList;
	}


	public void setSearchList(@SuppressWarnings("rawtypes") List searchList) {
		this.searchList = searchList;
	}


	public String getCode() {
		return code;
	}


	public void setCode(String code) {
		this.code = code;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}




	
	
}
