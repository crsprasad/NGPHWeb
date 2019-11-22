package com.logica.ngph.web.action;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.context.ApplicationContextException;

import com.logica.ngph.Entity.RawMessage;
import com.logica.ngph.common.NGPHException;
import com.logica.ngph.dtos.PaymentMessage;
import com.logica.ngph.service.IErrorMsgsService;
import com.logica.ngph.web.utils.ApplicationContextProvider;
import com.opensymphony.xwork2.ActionSupport;


public class InvalidMesAction extends ActionSupport implements SessionAware {

	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(InvalidMesAction.class);
	private String msgRef;
	private String rawMsg;
	private String errCode;
	public String getRawMsg() {
		return rawMsg;
	}


	public void setRawMsg(String rawMsg) {
		this.rawMsg = rawMsg;
	}


	public String getErrCode() {
		return errCode;
	}


	public void setErrCode(String errCode) {
		this.errCode = errCode;
	}


	public String getMsgRef() {
		return msgRef;
	}


	public void setMsgRef(String msgRef) {
		this.msgRef = msgRef;
	}


	private Map<String, Object> session ;

	List<RawMessage> dataList;
	public Map<String, Object> getSession() {
		return session;
	}


	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	
	public List<RawMessage> getDataList() {
		return dataList;
	}

	public void setDataList(List<RawMessage> dataList) {
		this.dataList = dataList;
		this.session.put("dataList", dataList);
	}


	public String execute() throws NGPHException
	{
		try
		{
			IErrorMsgsService errorMsgsService = (IErrorMsgsService)ApplicationContextProvider.getBean("errorMsgsService");
			List<RawMessage> data = errorMsgsService.doAction();
			setDataList(data);
			return "success";
		}
		catch (NullPointerException  nullPointerException) {
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
	public String changeStatusAndMSgpolled()
	{
		try{
			
			List<Object> list = ((List<Object>) session.get("dataList"));
			for(int i = 0;i<list.size();i++)
			{
				Object ts = ((List<Object>) list).get(i);
		    	if(((RawMessage) ts).getMsgRef().equalsIgnoreCase(getMsgRef())){
		    		
		    		setErrCode(((RawMessage) ts).getErrorCode());
		    		setRawMsg(((RawMessage) ts).getStrMsg());
		    	}
			}
			
			return "success";
			
		}
		catch (NullPointerException  nullPointerException) {
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
		addActionMessage("Unable To Process .Please try Again");	
		return "input";
			
		
	}
	public String repairRawMessage()
	{
		try{
					
			return "success";
			
		}
		catch (NullPointerException  nullPointerException) {
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
