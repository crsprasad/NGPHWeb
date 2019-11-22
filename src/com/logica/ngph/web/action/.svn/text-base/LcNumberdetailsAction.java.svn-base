package com.logica.ngph.web.action;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.context.ApplicationContextException;

import com.logica.ngph.Entity.LcMast;
import com.logica.ngph.dtos.LCCanonicalDto;
import com.logica.ngph.dtos.LcMastDto;
import com.logica.ngph.service.LcOpenService;
import com.logica.ngph.web.utils.ApplicationContextProvider;
import com.opensymphony.xwork2.ActionSupport;

public class LcNumberdetailsAction extends ActionSupport implements SessionAware {
	private Map<String, Object> session;
	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(LcNumberdetailsAction.class);
	private List<LcMastDto> searchList;
	private String lcNumber;
	public String getLcNumber() {
		return lcNumber;
	}

	public void setLcNumber(String lcNumber) {
		this.lcNumber = lcNumber;
	}

	public List<LcMastDto> getSearchList() {
		return searchList;
	}

	public void setSearchList(List<LcMastDto> searchList) {
		this.searchList = searchList;
		this.session.put("searchList", searchList);
	}

	public Map<String, Object> getSession() {
		return session;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
	public String getAllInwardLcNumber()
	{
		try{
			logger.info("Lc Number for LcNumberdetailsAction Start");
			LcOpenService lcOpenService = (LcOpenService) ApplicationContextProvider.getBean("lcOpenService");
			setSearchList(lcOpenService.displayLcNumber());
			
			
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
	logger.info("Encounters error");
		return "input";
	}
	
	
	LCCanonicalDto lcCanonicalDto = new LCCanonicalDto();
	public String setLcOpenField(){
	try{
		String lcnumber = getLcNumber();
		
		LcOpenService lcOpenService = (LcOpenService) ApplicationContextProvider.getBean("lcOpenService");
		LCCanonicalDto canonicalDto=lcOpenService.getLCScreenData(lcnumber);
		
		
       // Object[] obj = (Object[]) list.get(0);
        lcCanonicalDto.setLcType(canonicalDto.getLcType());
        lcCanonicalDto.setLcNumber(canonicalDto.getLcNumber());
        lcCanonicalDto.setLcExpiryDate(canonicalDto.getLcExpiryDate());
        lcCanonicalDto.setLcExpirePlace(canonicalDto.getLcExpirePlace());
             	
        	lcCanonicalDto.setPositiveTolerance(canonicalDto.getPositiveTolerance());
            lcCanonicalDto.setNegativeTolerance(canonicalDto.getNegativeTolerance());
            
       
        lcCanonicalDto.setMaximumCreditAmount(canonicalDto.getMaximumCreditAmount());
        lcCanonicalDto.setAdditionalAmounts(canonicalDto.getAdditionalAmounts());
        lcCanonicalDto.setAuthorisedBankCode(canonicalDto.getAuthorisedBankCode());
        lcCanonicalDto.setAuthorisedAndAddress(canonicalDto.getAuthorisedAndAddress());
        lcCanonicalDto.setAuthorisationMode(canonicalDto.getAuthorisationMode());
        lcCanonicalDto.setGoodsLoadingDispatchPlace(canonicalDto.getGoodsLoadingDispatchPlace());
        lcCanonicalDto.setGoodsDestination(canonicalDto.getGoodsDestination());
        lcCanonicalDto.setLatestDateofShipment(canonicalDto.getLatestDateofShipment());
        lcCanonicalDto.setShipmentPeriod(canonicalDto.getShipmentPeriod());
        lcCanonicalDto.setShipmentTerms(canonicalDto.getShipmentTerms());
        lcCanonicalDto.setDraftsAt(canonicalDto.getDraftsAt());
        lcCanonicalDto.setDraweeBankpartyidentifier(canonicalDto.getDraweeBankpartyidentifier());
        lcCanonicalDto.setDraweeBankCode(canonicalDto.getDraweeBankCode());
        lcCanonicalDto.setDraweeBankNameAddress(canonicalDto.getDraweeBankNameAddress());
        lcCanonicalDto.setMixedPaymentDetails(canonicalDto.getMixedPaymentDetails());
        lcCanonicalDto.setDeferredPaymentDetails(canonicalDto.getDeferredPaymentDetails());
        lcCanonicalDto.setPartialShipments(canonicalDto.getPartialShipments());
        lcCanonicalDto.setTranshipment(canonicalDto.getTranshipment());
        lcCanonicalDto.setDocumentsRequired(canonicalDto.getDocumentsRequired());
        lcCanonicalDto.setAdditionalConditions(canonicalDto.getAdditionalConditions());
        lcCanonicalDto.setChargeDetails(canonicalDto.getChargeDetails());
        lcCanonicalDto.setPeriodforPresentation(canonicalDto.getPeriodforPresentation());
        lcCanonicalDto.setConfirmationCode(canonicalDto.getConfirmationCode());
        lcCanonicalDto.setInstructionstoPayingBank(canonicalDto.getInstructionstoPayingBank());
        lcCanonicalDto.setNarrative(canonicalDto.getNarrative());
        lcCanonicalDto.setMsgRef(canonicalDto.getMsgRef());       
        
        lcCanonicalDto.setAdviseThroughBankpartyidentifier(canonicalDto.getAdviseThroughBankpartyidentifier());
		lcCanonicalDto.setAdvisingBank(canonicalDto.getAdvisingBank());
		lcCanonicalDto.setAdviseThroughBankCode(canonicalDto.getAdviseThroughBankCode());
		lcCanonicalDto.setAdviseThroughBankLocation(canonicalDto.getAdviseThroughBankLocation());
		lcCanonicalDto.setAdviseThroughBankName(canonicalDto.getAdviseThroughBankName());
		lcCanonicalDto.setSendertoReceiverInformation(canonicalDto.getSendertoReceiverInformation());
		lcCanonicalDto.setReimbursingBank(canonicalDto.getReimbursingBank());
		lcCanonicalDto.setApplicantNameAddress(canonicalDto.getApplicantNameAddress());
		lcCanonicalDto.setApplicantBankNameAddress(canonicalDto.getApplicantBankNameAddress());
		lcCanonicalDto.setApplicantBankCode(canonicalDto.getApplicantBankCode());
		lcCanonicalDto.setApplicantBankpartyidentifier(canonicalDto.getApplicantBankpartyidentifier());
		lcCanonicalDto.setApplicantAccount(canonicalDto.getApplicantAccount());
		lcCanonicalDto.setAdvisingBank(canonicalDto.getAdvisingBank());
		lcCanonicalDto.setBeneficiaryAccount(canonicalDto.getBeneficiaryAccount());
		lcCanonicalDto.setBeneficiaryNameAddress(canonicalDto.getBeneficiaryNameAddress());
		lcCanonicalDto.setLcAmount(canonicalDto.getLcAmount());
		lcCanonicalDto.setLcCurrency(canonicalDto.getLcCurrency());
		
		
		
		return "success";
		
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
	addFieldError("LcNumber", "Lc Number Not In DataBase");
	return "input";
}

}
