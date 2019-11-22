package com.logica.ngph.web.action;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContextException;

import com.logica.ngph.common.NGPHException;
import com.logica.ngph.service.SearchService;
import com.logica.ngph.web.utils.ApplicationContextProvider;

public class SearchRuleIDAction {
	
	
	static Logger logger = Logger.getLogger(SearchRuleIDAction.class);
	private String RuleId;
	
	@SuppressWarnings("rawtypes")
	private List searchList;
	

	public String getSearchData(){
	
			try {
				/*ApplicationContext 	appcontext = (ApplicationContext)context.getAttribute(ConstantUtil.BEAN_CONTEXT);
				SearchService searchService = (SearchService)appcontext.getBean("searchService");*/
	
				//ApplicationContext appcontext = new ClassPathXmlApplicationContext("WebApplicationContext.xml");
				SearchService searchService  =(SearchService)	ApplicationContextProvider.getBean("searchService");
				
				
				
					setSearchList(searchService.dataRuleIDSearch(getRuleId()));
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



	


	public String getRuleId() {
		return RuleId;
	}



	public void setRuleId(String ruleId) {
		RuleId = ruleId;
	}





	




	
	
}
