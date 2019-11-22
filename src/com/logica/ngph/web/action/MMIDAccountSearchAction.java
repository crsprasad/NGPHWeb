package com.logica.ngph.web.action;


import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContextException;


import com.logica.ngph.dtos.AccountDto;
import com.logica.ngph.service.MMIDReportService;
import com.logica.ngph.web.utils.ApplicationContextProvider;

public class MMIDAccountSearchAction {
	static Logger logger = Logger.getLogger(MMIDAccountSearchAction.class);
	@SuppressWarnings("rawtypes")
	private List<AccountDto> searchList;
	private String accountNo;
	private String searchAction;
	private String action;
	
	@SuppressWarnings("rawtypes")
	public List<AccountDto> getSearchList() {
		return searchList;
	}


	public void setSearchList(@SuppressWarnings("rawtypes") List<AccountDto> searchList) {
		this.searchList = searchList;
	}	
	
	public String getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}
	public String getSearchAction() {
		return searchAction;
	}
	public void setSearchAction(String searchAction) {
		this.searchAction = searchAction;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	
	
	public String getSearchData(){
		
		try {
			System.out.println(getSearchAction()+" get action "+getAction());
			
			MMIDReportService mmidReportService=(MMIDReportService) ApplicationContextProvider.getBean("mmidReportService");
			setSearchList(mmidReportService.getAccountForSearch(this.getAccountNo()));
			
			
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
	
	

}
