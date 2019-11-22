package com.logica.ngph.web.action;

import java.io.File;
import java.sql.SQLException;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.interceptor.validation.SkipValidation;

import com.logica.ngph.Entity.EI;
import com.logica.ngph.common.ConstantUtil;
import com.logica.ngph.common.NGPHException;
import com.logica.ngph.service.ESBXMLCreator;
import com.logica.ngph.service.PendingAuthorizationService;
import com.logica.ngph.web.utils.ApplicationContextProvider;
import com.logica.ngph.web.utils.JbossEsbWEbCreator;
import com.logica.ngph.web.utils.ModifyXmlFiles;
import com.logica.ngph.web.utils.WebConstants;
import com.opensymphony.xwork2.ActionSupport;

public class ESBXMLCreatorAction extends ActionSupport implements
		ServletRequestAware, SessionAware {
	private HttpServletRequest servletRequest;

	private static final long serialVersionUID = -3364264106258064077L;
	private String[] EI_Type;
	private String[] EI_Conn_Type;
	private String EI_ID;
	private String EI_Name;
	private String EI_Gateway;
	private String MQ_Manager_Name;
	private String MQ_Server_ID;
	private String MQ_Server_Port;
	private String Client_Connection_Channel;
	private String EIType;
	private String EIConnectionType;
	private String displayNextScreen;
	private String MQ_Queue_Name;
	private String message;
	private String EI_Format;
	private String EI_Host_Category;
	private String SaveModifyOrDelete;
	private String hiddenTxnRef;
	private String txnRef;
	private String saveAction;
	private String checkForSubmit;
	private boolean validUserToApprove;
	public String getCheckForSubmit() {
		return checkForSubmit;
	}

	public void setCheckForSubmit(String checkForSubmit) {
		this.checkForSubmit = checkForSubmit;
	}

	public String getHiddenTxnRef() {
		return hiddenTxnRef;
	}

	public void setHiddenTxnRef(String hiddenTxnRef) {
		this.hiddenTxnRef = hiddenTxnRef;
	}

	public String getTxnRef() {
		return txnRef;
	}

	public void setTxnRef(String txnRef) {
		this.txnRef = txnRef;
	}

	public String getSaveAction() {
		return saveAction;
	}

	public void setSaveAction(String saveAction) {
		this.saveAction = saveAction;
	}

	public String getSaveModifyOrDelete() {
		return SaveModifyOrDelete;
	}

	public void setSaveModifyOrDelete(String saveModifyOrDelete) {
		SaveModifyOrDelete = saveModifyOrDelete;
	}

	public String getEI_Host_Category() {
		return EI_Host_Category;
	}

	public void setEI_Host_Category(String eI_Host_Category) {
		EI_Host_Category = eI_Host_Category;
	}

	private String[]EI_Format_Drop;

	

	private String FsFilePath;
	private String webServiceURL;
	private String webServiceMethod;
	
	public String getEI_Format() {
		return EI_Format;
	}

	public void setEI_Format(String eI_Format) {
		EI_Format = eI_Format;
	}

	public String[] getEI_Format_Drop() {
		return EI_Format_Drop;
	}

	public void setEI_Format_Drop(String[] eI_Format_Drop) {
		EI_Format_Drop = eI_Format_Drop;
		session.put("EI_Format_Drop", EI_Format_Drop);
	}
	
	public String getWebServiceURL() {
		return webServiceURL;
	}

	public void setWebServiceURL(String webServiceURL) {
		this.webServiceURL = webServiceURL;
	}

	public String getWebServiceMethod() {
		return webServiceMethod;
	}

	public void setWebServiceMethod(String webServiceMethod) {
		this.webServiceMethod = webServiceMethod;
	}

	public String getFsFilePath() {
		return FsFilePath;
	}

	public void setFsFilePath(String fsFilePath) {
		FsFilePath = fsFilePath;
	}

	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getMQ_Queue_Name() {
		return MQ_Queue_Name;
	}

	public void setMQ_Queue_Name(String mQ_Queue_Name) {
		MQ_Queue_Name = mQ_Queue_Name;
	}

	public String getEIType() {
		return EIType;
	}

	public void setEIType(String eIType) {
		EIType = eIType;
		
	}

	public String getEIConnectionType() {
		return EIConnectionType;
	}

	public String getDisplayNextScreen() {
		return displayNextScreen;
	}

	public void setDisplayNextScreen(String displayNextScreen) {
		this.displayNextScreen = displayNextScreen;
		this.session.put("displayNextScreen", displayNextScreen);
		
	}

	public void setEIConnectionType(String eIConnectionType) {
		EIConnectionType = eIConnectionType;
		this.session.put("EIConnectionType", EIConnectionType);
	}

	public String[] getEI_Type() {
		return EI_Type;
	}

	public void setEI_Type(String[] eI_Type) {
		EI_Type = eI_Type;
	}

	public String[] getEI_Conn_Type() {
		return EI_Conn_Type;
	}

	public void setEI_Conn_Type(String[] eI_Conn_Type) {
		EI_Conn_Type = eI_Conn_Type;
	}

	public String getEI_ID() {
		return EI_ID;
	}

	public void setEI_ID(String eI_ID) {
		EI_ID = eI_ID;
	}

	public String getEI_Name() {
		return EI_Name;
	}

	public void setEI_Name(String eI_Name) {
		EI_Name = eI_Name;
	}

	public String getEI_Gateway() {
		return EI_Gateway;
	}

	public void setEI_Gateway(String eI_Gateway) {
		EI_Gateway = eI_Gateway;
	}

	public String getMQ_Manager_Name() {
		return MQ_Manager_Name;
	}

	public void setMQ_Manager_Name(String mQ_Manager_Name) {
		MQ_Manager_Name = mQ_Manager_Name;
	}

	public String getMQ_Server_ID() {
		return MQ_Server_ID;
	}

	public void setMQ_Server_ID(String mQ_Server_ID) {
		MQ_Server_ID = mQ_Server_ID;
	}

	public String getMQ_Server_Port() {
		return MQ_Server_Port;
	}

	public void setMQ_Server_Port(String mQ_Server_Port) {
		MQ_Server_Port = mQ_Server_Port;
	}

	public String getClient_Connection_Channel() {
		return Client_Connection_Channel;
	}

	public void setClient_Connection_Channel(String client_Connection_Channel) {
		Client_Connection_Channel = client_Connection_Channel;
	}

	static Logger logger = Logger.getLogger(ESBXMLCreatorAction.class);
	private Map<String, Object> session;

	public Map<String, Object> getSession() {
		return session;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	@SkipValidation
	public String ajaxForQueueDetails() {

		if (getEIConnectionType().equals("MQ"))
			setDisplayNextScreen("MQ");
		else if (getEIConnectionType().equals("Files"))
			setDisplayNextScreen("Files");
		else if (getEIConnectionType().equals("Web Service"))
			setDisplayNextScreen("Web Service");

		return "success";
	}

	@SkipValidation
	public String populateData() {
		setEI_Format_Drop(getText("EI_Format").split(","));
		setDisplayNextScreen("MQ");
		setSaveModifyOrDelete("ADD");
		return "success";
	}

	public String createXML() throws SQLException {
		ESBXMLCreator creator;
		String returnResult = "success";
		String srcPath = getText("FilePath");
		EI ei= new EI();
		PendingAuthorizationService pendingAuthorizationService = (PendingAuthorizationService)ApplicationContextProvider.getBean("pendingAuthorizationService"); 
		String destinationPath = "";
		//destinationPath = srcPath.substring(0, srcPath.length() - 40);
		destinationPath = servletRequest.getSession().getServletContext()
		.getRealPath("/");
		System.out.println("destinationPath :- " + destinationPath);
		creator = (ESBXMLCreator) ApplicationContextProvider
				.getBean("ESBXMLCreator");
		
		String resultForXmlCreation = "";

		int flag = 0;
		// iterate through HashMap values iterator
		String delimitedString = ""; 
		if (getEIConnectionType().equals("MQ")) {
			flag = 0;
		} else if (getEIConnectionType().equals("Files")) {
			flag = 1;
		} else if (getEIConnectionType().equals("Web Service")) {
			flag = 2;
		}
		System.out.println("creator.checkForPrimaryKey(getEI_ID()) :--- "
				+ creator.checkForPrimaryKey(getEI_ID()));
		//if (creator.checkForPrimaryKey(getEI_ID()).equals("0") ) {
			// returnResult =
			// creator.insertRecordInTA_EI(getEI_ID(),getEI_Name(),getEIType(),getEI_Gateway());
			JbossEsbWEbCreator testFile = new JbossEsbWEbCreator();
			
	       
	       
			if (flag == 0) {
				if(org.apache.commons.lang.StringUtils.isEmpty(getMQ_Manager_Name()))
					addFieldError(MQ_Manager_Name,"MQ_Manager_Name is Required");
				else if(org.apache.commons.lang.StringUtils.isEmpty(getMQ_Server_ID()))
					addFieldError(MQ_Server_ID,"MQ Server ID is Required");
				else if(!IsMatch(getMQ_Server_ID(),ConstantUtil.REGEXFORIPADDRESS))
					addFieldError(MQ_Server_ID, "MQ Server ID Is Not A Valid IP Address");
				else if(org.apache.commons.lang.StringUtils.isEmpty(getMQ_Server_Port()))
					addFieldError(MQ_Server_Port,"MQ Server Port is Required");
				else if(!IsMatch(getMQ_Server_Port(),ConstantUtil.REGEXFORINTEGERONLY))
					addFieldError(MQ_Server_Port, "MQ Server port Is Not A Valid IP Address");
				else if(org.apache.commons.lang.StringUtils.isEmpty(getMQ_Queue_Name()))
					addFieldError(MQ_Queue_Name,"MQ_Queue_Name is Required");
				else if(org.apache.commons.lang.StringUtils.isEmpty(getClient_Connection_Channel()))
					addFieldError(Client_Connection_Channel,"Client Connection Channel is Required");
				else if (!org.apache.commons.lang.StringUtils.isEmpty(getMQ_Manager_Name())
						&& !org.apache.commons.lang.StringUtils.isEmpty(getMQ_Server_ID())
						&& !org.apache.commons.lang.StringUtils.isEmpty(getMQ_Server_Port())
						&& !org.apache.commons.lang.StringUtils.isEmpty(getMQ_Queue_Name())
						&& !org.apache.commons.lang.StringUtils.isEmpty(getClient_Connection_Channel())) {
					
					delimitedString = getEI_ID()+ConstantUtil.delimiter+getEI_Name()+ConstantUtil.delimiter+getEIType()+ConstantUtil.delimiter+
					getEIConnectionType()+ConstantUtil.delimiter+getEI_Gateway()+ConstantUtil.delimiter+getEI_Format()+ConstantUtil.delimiter+getEI_Host_Category();
					
					//Add new node to the XMl file 
					if(getSaveModifyOrDelete().equals("ADD")){
						if(getSaveAction().equals("Approve")){
													
					resultForXmlCreation = testFile.createXML(getEI_ID(),getEIType(),getMQ_Manager_Name(), getMQ_Server_ID(),getMQ_Server_Port(), getMQ_Queue_Name(),
							getClient_Connection_Channel(),getEIConnectionType(), srcPath ,	destinationPath);
						}
						else if(getSaveAction().equals("Save"))
						{
							delimitedString = delimitedString+ConstantUtil.delimiter+getMQ_Manager_Name()+ConstantUtil.delimiter+getMQ_Server_ID()+ConstantUtil.delimiter+getMQ_Server_Port()+ConstantUtil.delimiter+getMQ_Queue_Name()+ConstantUtil.delimiter+getClient_Connection_Channel()+ConstantUtil.delimiter+"ADD";
						}
					}
					//Modify the node according to the EI_ID and EI_type
					else if(getSaveModifyOrDelete().equals("Modify")){
						if(getSaveAction().equals("Approve")){
						ModifyXmlFiles files = new ModifyXmlFiles();
						
						resultForXmlCreation=files.modifyXML(getEI_ID(),getEIType(),getMQ_Manager_Name(), getMQ_Server_ID(),getMQ_Server_Port(), getMQ_Queue_Name(),
								getClient_Connection_Channel(),getEIConnectionType());
						}
						else if(getSaveAction().equals("Save"))
						{
							delimitedString = delimitedString+ConstantUtil.delimiter+getMQ_Manager_Name()+ConstantUtil.delimiter+getMQ_Server_ID()+ConstantUtil.delimiter+getMQ_Server_Port()+ConstantUtil.delimiter+getMQ_Queue_Name()+ConstantUtil.delimiter+getClient_Connection_Channel()+ConstantUtil.delimiter+"Modify";
						}
						
					}
					else
						System.out.println("TO delete the Node");
					
					if (resultForXmlCreation.equals("alreadyPresent")) {
						addFieldError(MQ_Queue_Name,"MQ_Queue_Name is Already Present");
					} else if(resultForXmlCreation.equals("failure")){
						setMessage("Unable to Find the Xml file");
					}
					else{
						ei.setEiCode(getEI_ID());
						ei.setEiName(getEI_Name());
						ei.setEiType(getEIType());
						ei.setEI_CONN_TYPE(getEIConnectionType());
						ei.setEiEspProvName(getEI_Gateway());
						ei.setEI_FORMAT(getEI_Format());
						ei.setEI_HOST_CATGORY(getEI_Host_Category());
						String userId = (String)session.get(WebConstants.CONSTANT_USER_NAME);
						
						if(getSaveModifyOrDelete().equals("ADD") && creator.checkForPrimaryKey(getEI_ID()).equals("0")){
							
						if(getSaveAction().equals("Approve"))
							returnResult = creator.insertRecordInTA_EI(ei);
						else if(getSaveAction().equals("Save"))
							pendingAuthorizationService.getTranRef(delimitedString, "XML Creator",userId);
						}
						else if (getSaveModifyOrDelete().equals("Modify")){
							if(getSaveAction().equals("Approve"))
							returnResult = creator.updateEI(ei);
							else if(getSaveAction().equals("Save"))
								pendingAuthorizationService.getTranRef(delimitedString, "XML Creator",userId);
													
						}
						else{
							if(getSaveAction().equals("Approve"))
							returnResult = creator.deleteEI(ei);
						else if(getSaveAction().equals("Save"))
							pendingAuthorizationService.getTranRef(delimitedString, "ESB Creator",userId);
						}
					}
					if(getSaveAction().equals("Approve") || getSaveAction().equals("Reject"))
					{
						String status = pendingAuthorizationService.changeStatus(getSaveAction(), getHiddenTxnRef());
					}
						if (returnResult.equals("success"))
								System.out.println("Operation success");
					}
				
					
				}  
				// For MQ queues

			
			if (flag == 1) {
				// For File System
				System.out.println("file system");
				System.out.println("File PAth :-- " + getFsFilePath());
				File d = new File("getFsFilePath()");
				if (d.isDirectory()) {
				System.out.println(" Domain Directory is Present");
				} else {
				System.out.println(" Domain Directory is not Present");
				System.out.println(" Creating Domain Directory....");
				d.mkdirs();
				}
				
				System.out.println(IsMatch(getFsFilePath(),ConstantUtil.REGEXFORFILEPATH));
				 if(!IsMatch(getFsFilePath(),ConstantUtil.REGEXFORFILEPATH))
					addFieldError(FsFilePath, "FsFilePath is Not a valid File Path");
				 else if (!org.apache.commons.lang.StringUtils
						.isEmpty(getFsFilePath())) {
					resultForXmlCreation=testFile.createXML(getEI_ID(),getEIType(), getFsFilePath(), "", "", "",
							"", getEIConnectionType(), srcPath,destinationPath);
					if (resultForXmlCreation.equals("alreadyPresent")) {
						addFieldError(MQ_Queue_Name,
								"MQ_Queue_Name is Already Present");
					} else if(resultForXmlCreation.equals("failure")){
						addActionMessage("Unable to Find the Xml file");
					}
					else{
						ei.setEiCode(getEI_ID());
						ei.setEiName(getEI_Name());
						ei.setEiType(getEIType());
						ei.setEI_CONN_TYPE(getEIConnectionType());
						ei.setEiEspProvName(getEI_Gateway());
						ei.setEI_FORMAT(getEI_Format());
						ei.setEI_HOST_CATGORY(getEI_Host_Category());
						
						
						
						returnResult = creator.insertRecordInTA_EI(ei);
					if(returnResult.equals("success"))
						setMessage("Data base and XMl file is Create");
					}
				} 
				else
				{
					addFieldError(FsFilePath, "FsFilePath is Required");
				}

			}
			if (flag == 2) {
				// For Web service

				System.out.println("Web sphere");
				if(!IsMatch(getWebServiceURL(),ConstantUtil.REGEXFORURL))
					addFieldError(webServiceURL, "URL is Not a valid One");
				else if (!org.apache.commons.lang.StringUtils.isEmpty(getWebServiceURL()) && !org.apache.commons.lang.StringUtils.isEmpty(getWebServiceMethod())) {
					testFile.createXML(getEI_ID(),getEIType(), getWebServiceURL(),getWebServiceMethod(), "", "", "",getEIConnectionType(), srcPath, destinationPath);
					ei.setEiCode(getEI_ID());
					ei.setEiName(getEI_Name());
					ei.setEiType(getEIType());
					ei.setEI_CONN_TYPE(getEIConnectionType());
					ei.setEiEspProvName(getEI_Gateway());
					ei.setEI_FORMAT(getEI_Format());
					ei.setEI_HOST_CATGORY(getEI_Host_Category());
					returnResult = creator.insertRecordInTA_EI(ei);

				}
				 
				else if (org.apache.commons.lang.StringUtils.isEmpty(getWebServiceURL())) {
					addFieldError(webServiceURL, "URL  is Required");
				}
				else if (org.apache.commons.lang.StringUtils.isEmpty(getWebServiceMethod())) {
					addFieldError(webServiceMethod, "Web Service Method()  is Required");
				}
			}

		/*} else
			addFieldError(EI_ID, "EI ID Is All Ready Present");*/

		return "success";
	}

	public void validate() {
		System.out.println("HI Valodation");
	}

	public void setServletRequest(HttpServletRequest servletRequest) {
		this.servletRequest = servletRequest;
	}
	@SkipValidation
	public String getAuthorization() throws NGPHException, SQLException
	{
		PendingAuthorizationService pendingAuthorizationService = (PendingAuthorizationService)ApplicationContextProvider.getBean("pendingAuthorizationService");
		populateData();
		setHiddenTxnRef(getTxnRef());
		setCheckForSubmit("Display_Approve_Reject");
		String userId = pendingAuthorizationService.getCreatedUser(getTxnRef());
		String userRole = pendingAuthorizationService.getUserType((String)session.get(WebConstants.CONSTANT_USER_NAME));
		if((((String)session.get(WebConstants.CONSTANT_USER_NAME)).equals(userId) || userRole.equals("T"))){
			setValidUserToApprove(false);
		} else {
			setValidUserToApprove(true);
		}
		String requiredString =pendingAuthorizationService.getScreenReturn(getTxnRef());
		String[] stringBreaker = requiredString.split("~");
		setDisplayNextScreen(stringBreaker[3]);
		setEI_ID(stringBreaker[0]);
		setEI_Name(stringBreaker[1]);
		setEIType(stringBreaker[2]);
		setEIConnectionType(stringBreaker[3]);
		setEI_Gateway(stringBreaker[4]);
		setEI_Format(stringBreaker[5]);
		setEI_Host_Category(stringBreaker[6]);
		
		if(stringBreaker[3].equals("MQ"))
		{
			setMQ_Manager_Name(stringBreaker[7]);
			setMQ_Server_ID(stringBreaker[8]);
			setMQ_Server_Port(stringBreaker[9]);
			setMQ_Queue_Name(stringBreaker[10]);
			setClient_Connection_Channel(stringBreaker[11]);
			
		}
		setSaveModifyOrDelete(stringBreaker[12].replaceAll("\n", ""));
	

		return "success";
	}
	private  boolean IsMatch(String s, String pattern) {
        try {
            Pattern patt = Pattern.compile(pattern);
            Matcher matcher = patt.matcher(s);
            return matcher.matches();
        } catch (RuntimeException e) {
        return false;
    }    
	}

	public boolean getValidUserToApprove() {
		return validUserToApprove;
	}

	public void setValidUserToApprove(boolean validUserToApprove) {
		this.validUserToApprove = validUserToApprove;
	}
}
