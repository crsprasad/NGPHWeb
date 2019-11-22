/**
 * 
 */
package com.logica.ngph.web.action;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContextException;

import com.logica.ngph.service.CreateBankGuaranteeService;
import com.logica.ngph.web.utils.ApplicationContextProvider;


/**
 * @author chakkar
 *
 */
public class AmendBGSearchAction {
	static Logger logger = Logger.getLogger(AmendBGSearchAction.class);
	@SuppressWarnings("rawtypes")
	private List searchList;
	private String bgNumber;
	private String relatedReferenceNo;
	
	@SuppressWarnings("rawtypes")
	public List getSearchList() {
		return searchList;
	}
	public void setSearchList(@SuppressWarnings("rawtypes") List searchList) {
		this.searchList = searchList;
	}
	public String getBgNumber() {
		return bgNumber;
	}
	public void setBgNumber(String bgNumber) {
		this.bgNumber = bgNumber;
	}

	/**
	 * @return the relatedReferenceNo
	 */
	public String getRelatedReferenceNo() {
		return relatedReferenceNo;
	}
	/**
	 * @param relatedReferenceNo the relatedReferenceNo to set
	 */
	public void setRelatedReferenceNo(String relatedReferenceNo) {
		this.relatedReferenceNo = relatedReferenceNo;
	}
	public String getSearchData()
	{
		try{
			CreateBankGuaranteeService createBankGuaranteeService = (CreateBankGuaranteeService) ApplicationContextProvider.getBean("createBankGuaranteeService");			
			setSearchList(createBankGuaranteeService.getAmendBGData(getRelatedReferenceNo()));
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
