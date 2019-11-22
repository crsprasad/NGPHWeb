package com.logica.ngph.web.action;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContextException;

import com.logica.ngph.common.NGPHException;
import com.logica.ngph.service.ESBXMLCreator;
import com.logica.ngph.web.utils.ApplicationContextProvider;

public class EI_IDSearchAction {
	
	
	static Logger logger = Logger.getLogger(EI_IDSearchAction.class);
	private String RuleId;
	
	@SuppressWarnings("rawtypes")
	private List searchList;
	ESBXMLCreator creator;

	public String getSearchData() throws NGPHException{
	
			try {
				/*ApplicationContext 	appcontext = (ApplicationContext)context.getAttribute(ConstantUtil.BEAN_CONTEXT);
				SearchService searchService = (SearchService)appcontext.getBean("searchService");*/
	
				//ApplicationContext appcontext = new ClassPathXmlApplicationContext("WebApplicationContext.xml");
				creator = (ESBXMLCreator) ApplicationContextProvider
				.getBean("ESBXMLCreator");
				
				
					setSearchList(creator.dataRuleIDSearch(getRuleId()));
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



	


	public String getRuleId() {
		return RuleId;
	}



	public void setRuleId(String ruleId) {
		RuleId = ruleId;
	}





	




	
	
}
