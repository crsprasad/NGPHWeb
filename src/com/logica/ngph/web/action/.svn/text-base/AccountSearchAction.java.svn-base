package com.logica.ngph.web.action;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContextException;

import com.logica.ngph.common.NGPHException;
import com.logica.ngph.service.AccountMaintenaceService;

import com.logica.ngph.web.utils.ApplicationContextProvider;

public class AccountSearchAction {
	static Logger logger = Logger.getLogger(AccountSearchAction.class);
	@SuppressWarnings("rawtypes")
	private List searchList;
	private String accountNo;
	private String searchAction;
	private String action;
	

	


	public String getAction() {
		return action;
	}



	public void setAction(String action) {
		this.action = action;
	}



	public String getSearchAction() {
		return searchAction;
	}



	public void setSearchAction(String searchAction) {
		this.searchAction = searchAction;
	}



	public String getAccountNo() {
		return accountNo;
	}



	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}



	public String getSearchData(){
	
			try {
				System.out.println(getSearchAction()+" get action "+getAction());
				AccountMaintenaceService accountMaintenaceService = (AccountMaintenaceService)ApplicationContextProvider.getBean("accountMaintenanceService");
				setSearchList(accountMaintenaceService.accountSearch(getAccountNo(),getAction()));
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
}
