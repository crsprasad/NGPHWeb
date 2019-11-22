package com.logica.ngph.web.action;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.springframework.context.ApplicationContextException;

import com.logica.ngph.dtos.EIDto;

import com.logica.ngph.service.FeedsService;

import com.logica.ngph.web.utils.ApplicationContextProvider;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class FeedsAction extends ActionSupport implements ModelDriven<EIDto>, SessionAware{
	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(FeedsAction.class);
	private Map<String, Object> session;
	EIDto eIDto =new EIDto();
	public  List<EIDto> searchList = new ArrayList<EIDto>();
	
	private String saveAction;
	
	public String getSaveAction() {
		return saveAction;
	}
	public void setSaveAction(String saveAction) {
		this.saveAction = saveAction;
	}
	public  List codeList = new ArrayList();
	public List getCodeList() {
		return codeList;
	}
	public void setCodeList(List codeList) {
		this.codeList = codeList;
	}
	public List getNameList() {
		return nameList;
	}
	public void setNameList(List nameList) {
		this.nameList = nameList;
	}
	public List getStatusList() {
		return statusList;
	}
	public void setStatusList(List statusList) {
		this.statusList = statusList;
	}
	public  List nameList = new ArrayList();
	public  List statusList = new ArrayList();
	public String getEICode() {
		return EICode;
	}
	public void setEICode(String eICode) {
		EICode = eICode;
	}
	public String getEIName() {
		return EIName;
	}
	public void setEIName(String eIName) {
		EIName = eIName;
	}
	private String EICode;
	private String EIName;

	
	
	public  List<EIDto> getSearchList() {
		return searchList;
	}
	public  void setSearchList(List<EIDto> searchList) {
		this.searchList = searchList;
		this.session.put("searchList", searchList);
	}
	@SkipValidation
	public String displayFeeds()
	{
	try{
		List EIDtoList = new ArrayList<EIDto>();

		FeedsService feedsService = 
			(FeedsService)ApplicationContextProvider.getBean("feedsService");
	
			
		EIDtoList = feedsService.getEIFeedDetails();
		Iterator<EIDto> ite = EIDtoList.iterator();
		while(ite.hasNext()){
			EIDto eiDto = (EIDto)ite.next();
			codeList.add(eiDto.getEICode());
			nameList.add(eiDto.getEIName());
			statusList.add(eiDto.getEIStatus());
			searchList.add(eiDto);
			
			
		}
		
	
		session.put("searchList", searchList);
		session.put("codeList", codeList);
		session.put("nameList", nameList.toArray());
		session.put("statusList", statusList);
			
			
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
	
		return "input";
	}	
	
	public String changeStatusFeeds()
	{
		try{	
			/*session.remove("searchList");
			session.remove("codeList");
			session.remove("nameList");
			session.remove("statusList");*/
		
			 String returnString ="";
			FeedsService feedsService = (FeedsService)ApplicationContextProvider.getBean("feedsService");	
			String eiCode = eIDto.getEICode();
			if(!eiCode.equals("")){
				if(getSaveAction().equals("Start")){
					  returnString = feedsService.changeStatus(eiCode, "Start");
				}else if(getSaveAction().equals("Stop")){
					  returnString = feedsService.changeStatus(eiCode, "Stop");
				}
				if(returnString.equals("success")){
					session.remove("searchList");
					session.remove("codeList");
					session.remove("nameList");
					session.remove("statusList");
					return "success";	
				}
			}
			
			
		return "input";
		
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
	
	
	
	
	public void setMobel(EIDto connionicalDto)
	{
		 this.eIDto=connionicalDto;
	}
	public EIDto getModel() {
		
		return eIDto;
	}
	
	public Map<String, Object> getSession() {
		return session;
	}
	public void setSession(Map<String, Object> session) {
		this.session = session;		
	}
}
