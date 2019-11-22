package com.logica.ngph.web.action;


import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.springframework.context.ApplicationContextException;

import com.logica.ngph.common.ConstantUtil;
import com.logica.ngph.common.NGPHException;
import com.logica.ngph.common.PaymentStatusEnum;
import com.logica.ngph.dtos.PaymentMessage;
import com.logica.ngph.service.EnquiryHistSearchService;
import com.logica.ngph.service.EnquirySearchService;
import com.logica.ngph.service.EnquiryService;
import com.logica.ngph.service.PaymentMessageService;
import com.logica.ngph.web.utils.ApplicationContextProvider;
import com.logica.ngph.web.utils.DisplayEnquiryData;
import com.opensymphony.xwork2.ActionSupport;





public class EnquiryAction extends ActionSupport implements SessionAware
{
	private static final long serialVersionUID = -3364264106258064077L;
	static Logger logger = Logger.getLogger(EnquiryAction.class);
	
	private String enquiryCurrency;
	private String enquiryBeneficiaryCustomer;
	private String enquiryOrderingCustomer;
	private String enquiryNarration;
	private String enquiryFromDate;
	private String enquiryTODate;
	private String enquiryMsgType;
	private String enquirySubMsgType;
	private String enquiryBound;
	private String enquiryChannel;
	private String enquiryTransactionReference;
	private String enquiryValueDate;
	private String enquiryPaymentStatus;
	private String[] EnquiryCheckBox;
	private String[] msgRef;
	private List<String> channelDropDown = new ArrayList<String>();
	private List<String> subMsgTypeDropDown = new ArrayList<String>();
	private List<String> msgTypeDropDown = new ArrayList<String>();
	private List<String> currencyDropDown = new ArrayList<String>();
	private String[] paymentStatus;
	private String seletecMessageCount;
	private String enquiryScreen;
	private String messageRef;
	private String msgType;
	private String paymentStatusCompleted;
	private String histMsgRef;
	
	
	
	
	public String getHistMsgRef() {
		return histMsgRef;
	}


	public void setHistMsgRef(String histMsgRef) {
		this.histMsgRef = histMsgRef;
	}


	public String getPaymentStatusCompleted() {
		return paymentStatusCompleted;
	}


	public void setPaymentStatusCompleted(String paymentStatusCompleted) {
		this.paymentStatusCompleted = paymentStatusCompleted;
		this.session.put("paymentStatusCompleted", getPaymentStatusCompleted());
	}


	public String getMsgType() {
		return msgType;
	}


	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}


	public String getMessageRef() {
		return messageRef;
	}


	public void setMessageRef(String messageRef) {
		this.messageRef = messageRef;
	}


	public String getEnquiryScreen() {
		return enquiryScreen;
	}


	public void setEnquiryScreen(String enquiryScreen) {
		this.enquiryScreen = enquiryScreen;
	}


	public String getSeletecMessageCount() {
		return seletecMessageCount;
	}


	public void setSeletecMessageCount(String seletecMessageCount) {
		this.seletecMessageCount = seletecMessageCount;
	}


	public String[] getPaymentStatus() {
		return paymentStatus;
	}


	public void setPaymentStatus(String[] paymentStatus) {
		this.paymentStatus = paymentStatus;
		this.session.put("paymentStatus", paymentStatus);
	}

	EnquiryService enquiryService;
	public void setEnquiryService(EnquiryService enquiryService) {
		this.enquiryService = enquiryService;
	}

	private Map<String, Object> session ;


	public String getEnquiryTransactionReference() {
		return enquiryTransactionReference;
	}


	public void setEnquiryTransactionReference(String enquiryTransactionReference) {
		this.enquiryTransactionReference = enquiryTransactionReference;
	}

	public String getEnquiryPaymentStatus() {
		return enquiryPaymentStatus;
	}


	public void setEnquiryPaymentStatus(String enquiryPaymentStatus) {
		this.enquiryPaymentStatus = enquiryPaymentStatus;
	}

	private List<PaymentMessage> enquirySearchList ;
	
	public List<PaymentMessage> getEnquirySearchList() {
		return enquirySearchList;
	}

	public void setEnquirySearchList(List<PaymentMessage> enquirySearchList) {
		this.enquirySearchList = enquirySearchList;
	}

	public String getEnquiryValueDate() {
		return enquiryValueDate;
	}


	public void setEnquiryValueDate(String enquiryValueDate) {
		this.enquiryValueDate = enquiryValueDate;
	}


	public String getEnquiryChannel() {
		return enquiryChannel;
	}


	public void setEnquiryChannel(String enquiryChannel) {
		this.enquiryChannel = enquiryChannel;
	}

	public String getEnquiryMsgType() {
		return enquiryMsgType;
	}


	public void setEnquiryMsgType(String enuiryMsgType) {
		this.enquiryMsgType = enuiryMsgType;
	}


	public String getEnquirySubMsgType() {
		return enquirySubMsgType;
	}


	public void setEnquirySubMsgType(String enquirySubMsgType) {
		this.enquirySubMsgType = enquirySubMsgType;
	}


	public String getEnquiryBound() {
		return enquiryBound;
	}


	public void setEnquiryBound(String enquiryBound) {
		this.enquiryBound = enquiryBound;
	}


	public String getEnquiryCurrency() {
		return enquiryCurrency;
	}
	public void setEnquiryCurrency(String enquiryCurrency) {
		this.enquiryCurrency = enquiryCurrency;
	}
	public String getEnquiryBeneficiaryCustomer() {
		return enquiryBeneficiaryCustomer;
	}
	public void setEnquiryBeneficiaryCustomer(String enquiryBeneficiaryCustomer) {
		this.enquiryBeneficiaryCustomer = enquiryBeneficiaryCustomer;
	}
	public String getEnquiryOrderingCustomer() {
		return enquiryOrderingCustomer;
	}
	public void setEnquiryOrderingCustomer(String enquiryOrderingCustomer) {
		this.enquiryOrderingCustomer = enquiryOrderingCustomer;
	}
	public String getEnquiryNarration() {
		return enquiryNarration;
	}
	public void setEnquiryNarration(String enquiryNarration) {
		this.enquiryNarration = enquiryNarration;
	}
	public String getEnquiryFromDate() {
		return enquiryFromDate;
	}
	public void setEnquiryFromDate(String enquiryFromDate) {
		this.enquiryFromDate = enquiryFromDate;
	}
	public String getEnquiryTODate() {
		return enquiryTODate;
	}
	public void setEnquiryTODate(String enquiryTODate) {
		this.enquiryTODate = enquiryTODate;
	}
	
	

	public Map<String, Object> getSession() {
		return session;
	}


	public void setSession(Map<String, Object> session) {
		this.session = session;
	}


	public List<String> getCurrencyDropDown() {
		return currencyDropDown;
	}


	public void setCurrencyDropDown(List<String> currencyDropDown) {
		this.currencyDropDown = currencyDropDown;
		this.session.put("currencyDropDown",currencyDropDown);
	}


	public List<String> getChannelDropDown() {
		return channelDropDown;
	}

	public void setChannelDropDown(List<String> channelDropDown) {
		this.channelDropDown = channelDropDown;
		this.session.put("channelDropDown",channelDropDown);
	}
	public List<String> getSubMsgTypeDropDown() {
		return subMsgTypeDropDown;
	}
	public void setSubMsgTypeDropDown(List<String> subMsgTypeDropDown) {
		this.subMsgTypeDropDown = subMsgTypeDropDown;
		this.session.put("subMsgTypeDropDown",subMsgTypeDropDown);
	}
	public List<String> getMsgTypeDropDown() {
		return msgTypeDropDown;
	}
	public void setMsgTypeDropDown(List<String> msgTypeDropDown) {
		this.msgTypeDropDown = msgTypeDropDown;
		this.session.put("msgTypeDropDown",msgTypeDropDown);
	}
	
	
	

	@SkipValidation
	public String callFindHistory() throws NGPHException{
		try{
			
			EnquiryService enquiryService = getEnquiryService();
		
		
		this.session.put("searchList",null);
		//System.out.println("THIS IS ENQUIRY ********************ACTION"+enquiryService.getEnquiryList(ConstantUtil.ENQUIRY_MSG_TYPE));
		//for channel drop down
		setChannelDropDown(enquiryService.getEnquiryList(ConstantUtil.EnquiryChannel));
		//for Message Type drop down
		setMsgTypeDropDown(enquiryService.getEnquiryList(ConstantUtil.ENQUIRY_MSG_TYPE));
		//for sub message type drop down
		setSubMsgTypeDropDown(enquiryService.getEnquiryList(ConstantUtil.EnquirySubMsgType));
		//for the currency
		setCurrencyDropDown(enquiryService.getEnquiryList(ConstantUtil.ENQUIRY_CURRENCY));
		
		
		String[] statusArray = getText("PAYMENTSTATUS").split(",");
		setPaymentStatus(statusArray);
		setEnquiryBound("I");
		setEnquiryScreen("FindHistory");
		
		}
		catch(Exception exception){
			logger.debug(exception);
		}
		return "success";
	}
	
	
	
	
	/**
	* This method is used to get Dynamic Data for database for drop down box
	* And setting the data to the respected setter method
	* @SkipValodation will skip the validation first time
	* And we are Making the searchList key to null On all the action is performed 
	*/
	@SkipValidation
	public String callLoadToDropDown() throws NGPHException{
		try{
			logger.info("Inside EnquiryAction<callLoadToDropDown>");
			
			EnquiryService enquiryService = getEnquiryService();
	//	System.out.println("THIS IS ENQUIRY ACTION");
		
		this.session.put("searchList",null);
		//System.out.println("THIS IS ENQUIRY ********************ACTION"+enquiryService.getEnquiryList(ConstantUtil.ENQUIRY_MSG_TYPE));
		//for channel drop down
		setChannelDropDown(enquiryService.getEnquiryList(ConstantUtil.EnquiryChannel));
		//for Message Type drop down
		setMsgTypeDropDown(enquiryService.getEnquiryList(ConstantUtil.ENQUIRY_MSG_TYPE));
		//for sub message type drop down
		setSubMsgTypeDropDown(enquiryService.getEnquiryList(ConstantUtil.EnquirySubMsgType));
		//for the currency
		setCurrencyDropDown(enquiryService.getEnquiryList(ConstantUtil.ENQUIRY_CURRENCY));
		
		//setStateNameDropDown(enquiryService.getEnquiryList(ConstantUtil.ENQUIRY_STATE_NAME));
		
		//setCurrencyDropDown(enquiryService.getEnquiryList(ConstantUtil.ENQUIRY_CURRENCY));
		
		//setCurrencyDropDown(enquiryService.getEnquiryList(ConstantUtil.ENQUIRY_CURRENCY));
		
		String[] statusArray = getText("PAYMENTSTATUS").split(",");
		setPaymentStatus(statusArray);
		setEnquiryBound("I");
		setEnquiryScreen("PaymentEnquiry");
		
		}
		catch(Exception exception){
			logger.error(exception);
		}
		logger.info("Populated All Records In The Screen");
		return "success";
	}
	
	/**
	* This method is used to generate the create query according to the values which are 
	* selected in the Payment Enquiry screen.Create query is then pass to DaoImpl so 
	* that we will get the data grid values from the database and are set in session.
	* 
	* @return EnquiryList for the data grid 
	 * @throws ParseException 
	*/
	@SuppressWarnings("deprecation")
	EnquirySearchService enquirySearch;
	EnquiryHistSearchService enquiryHistSearch;
	
	public void setEnquiryHistSearch(EnquiryHistSearchService enquiryHistSearch) {
		this.enquiryHistSearch = enquiryHistSearch;
	}
	
	public void setEnquirySearch(EnquirySearchService enquirySearch) {
		this.enquirySearch = enquirySearch;
	}

	
	public String cancelViewPage(){
		setEnquiryBound("I");
		setEnquiryScreen("PaymentEnquiry");
		return "success";
	}
	

	public String callEnquirySearch() throws ParseException
	{
		logger.info("Inside EnquiryAction<callEnquirySearch>");
		EnquirySearchService enquirySearch;
		String forward="EnquiryList";
		try{
			
			//ApplicationContext appcontext = new ClassPathXmlApplicationContext("WebApplicationContext.xml");
			
			//enquirySearch = (EnquirySearchService)appcontext.getBean("enquirySearch");
			
			enquirySearch = (EnquirySearchService)ApplicationContextProvider.getBean("enquirySearch");
			
			String searchQuery ="";
			
			
			//Checking for paramters getting from the PaymentEnquiry.jsp whether it is null or not
			//checking for channel 
			if(!getEnquiryChannel().equals("NONE"))//check for null are value is selected or not
			{
				searchQuery=searchQuery+" channel ='"+getEnquiryChannel()+"' AND";
			}
			if( !org.apache.commons.lang.StringUtils.isEmpty(getEnquiryMsgType())& !getEnquiryMsgType().equals("NONE"))
			{
				searchQuery=searchQuery+" msgType ='"+getEnquiryMsgType()+"' AND";
			}
			if(!org.apache.commons.lang.StringUtils.isEmpty(getEnquirySubMsgType())& !getEnquirySubMsgType().equals("NONE"))
			{
				searchQuery=searchQuery+" subMsgType ='"+getEnquirySubMsgType()+"' AND";
			}
			if(!org.apache.commons.lang.StringUtils.isEmpty(getEnquiryBeneficiaryCustomer()))
			{
				searchQuery= searchQuery+" concat(beneficiaryCustomerName,' ',beneficiaryCustomerAddress,' ',beneficiaryCustomerID,' ',beneficiaryCustomerCtry,' ',beneficiaryCustAcct,' ',ultimateCreditorName,' ',ultimateCreditorAddress,' ',ultimateCreditorID) like '%"+getEnquiryBeneficiaryCustomer()+"%' AND";
			}
			if(!org.apache.commons.lang.StringUtils.isEmpty(getEnquiryPaymentStatus()) & !getEnquiryPaymentStatus().equals("NONE"))
			{
				String status = PaymentStatusEnum
				.findPaymentStatusEnumByName(getEnquiryPaymentStatus());
				searchQuery=searchQuery+" msgStatus = '"+status+"'And msgStatus IS NOT NULL And";
			}
			
			try{
				
			//checking null for the form date and for to date	
			if(!org.apache.commons.lang.StringUtils.isEmpty(getEnquiryFromDate()) & !org.apache.commons.lang.StringUtils.isEmpty(getEnquiryTODate()))
					{
				String date1=dateChanger(getEnquiryFromDate());
				String date2=dateChanger(getEnquiryTODate());
				String valid=CompareDate(date1,date2);
				if(valid.equals("true")){
				searchQuery=searchQuery+" receivedTime between (to_date('"+date1+"' , 'dd/MM/yyyy')) AND (to_date('"+date2+"' , 'dd/MM/yyyy')) AND";
		//		System.out.println(searchQuery);
				
				}
				else{
					forward="input";}
								
				}
			
					
			
			}
			catch (Exception e) {
				System.out.println(e);
			}
			if(!org.apache.commons.lang.StringUtils.isEmpty(getEnquiryOrderingCustomer()))
					{
						searchQuery= searchQuery+" concat(orderingCustomerName,' ',orderingCustomerAddress,' ',orderingCustomerId,' ',orderingCustomerCtry ,' ',orderingCustAccount,' ',orderingInstitution,' ',orderingInstitutionAcct) like '%"+getEnquiryOrderingCustomer()+"%' AND";
			//			System.out.println(getEnquiryNarration());
					}
			try{
				if(!org.apache.commons.lang.StringUtils.isEmpty(getEnquiryValueDate().toString())){
			//		System.out.println("HELLO THIS IS VALUE DATE");
					searchQuery=searchQuery+" receivedTime between (to_date('"+dateChanger(getEnquiryValueDate())+" 00:00:00' , 'dd/MM/yyyy HH24:MI:SS')) and (to_date('"+dateChanger(getEnquiryValueDate())+" 23:59:59' , 'dd/MM/yyyy HH24:MI:SS')) And";
					
					
								}
				}
				catch (Exception e) {
					System.out.println(e);
				}
			if(!org.apache.commons.lang.StringUtils.isEmpty(getEnquiryBound()))
			{
				searchQuery= searchQuery+" direction = '"+getEnquiryBound()+"' AND";
			//	System.out.println(getEnquiryBound());
			}
			if(!org.apache.commons.lang.StringUtils.isEmpty(getEnquiryCurrency())& !getEnquiryCurrency().equals("NONE"))
			{
				searchQuery= searchQuery+" currency = '" +getEnquiryCurrency()+"' AND";
			}
			if(!org.apache.commons.lang.StringUtils.isEmpty(getEnquiryNarration()))
			{
				searchQuery= searchQuery+" concat(prevInstructingBank,' ',prevInstructingAgentAcct,' ',instructionsForNextAgtCode) like '%" +getEnquiryNarration()+"%' AND";
			}
			
			
			       
			
			//DateFormat format = new SimpleDateFormat("dd MMMM yyyy", getEnquiryValueDate())
			
			if(!org.apache.commons.lang.StringUtils.isEmpty(getEnquiryTransactionReference()))
			{
				searchQuery= searchQuery+" txnReference like '%" +getEnquiryTransactionReference()+"%' AND";
			}
			
			
			//System.out.println("new query string*************"+searchQuery);
			int length=searchQuery.length();
			if(length==0)
			{
				searchQuery = "where msgStatus IS NOT NULL" ;
			}
			else 
				searchQuery= "where "+searchQuery.substring(0, searchQuery.length()-4);
			
			String newstring=searchQuery;
			try{
			//	System.out.println(newstring);
			//session.put("newstring", newstring);
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			
			List<PaymentMessage> searchList = 	enquirySearch.getSearchResult(newstring,getEnquiryBound(),getEnquiryScreen());
			this.session.put("searchList",searchList);
		//	System.out.println("SEACH LIST"+searchList.size());
			setEnquirySearchList(searchList);
			logger.info("Operation Done In EnquiryAction<callEnquirySearch>");
			return forward;
		
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
			
			addActionError("Unable To Do Operation.Please Try Again");
			logger.info("Exception In EnquiryAction<callEnquirySearch>");
			if(getEnquiryScreen().equals("FindHistory"))
			return "histroyInput" ;
				else
			return "input";
		
	}
	private final static String getTime()
	{
		Calendar calendar = new GregorianCalendar();
		  int hour = calendar.get(Calendar.HOUR_OF_DAY);
		  int minute = calendar.get(Calendar.MINUTE);
		  int second = calendar.get(Calendar.SECOND);
		  return hour+":"+minute+":"+second;
	}
	/**
	* This method is used to Change The Date In Specific Format
	* it accept one argument which is date in string fromat
	* it will break string into day month and year
	* @return Converted Date
	*/
	public String dateChanger(String aNewValue) throws ParseException{
		logger.info("Inside EnquiryAction<dateChanger>");
		String date = aNewValue.substring(0, 6);
	       DateFormat sdf = new SimpleDateFormat("yymmdd");
	       sdf = new SimpleDateFormat("yyyymmdd");
	       
	       String yy = aNewValue.substring(0, 4);
	      
	       String mm = aNewValue.substring(5, 7);
	      
	       String dd = aNewValue.substring(8, 10);
	      
	      
	       String dateconverted=dd+"/"+mm+"/"+yy;
	       logger.info("Got The Covcerted Date Inside EnquiryAction<dateChanger>");
		return dateconverted;
		
	}
	/*This Method compare the two date and return true and false 
	 * so that it will do validation it accept to argument formdate 
	 * and todate.
	 * returns true when the from date is smaller then to date
	 * returns false when the todate is smaller then from date and 
	 * Do validation accordingly
	 */
	public String CompareDate(String str_date1,String str_date2) {
		 
		String valid="";
		try {  
			logger.info("Inside EnquiryAction<CompareDate>");
		 DateFormat formatter ; 
		 Date date ; 
		 Date date2 ; 
		  formatter = new SimpleDateFormat("dd/MM/yy");
		  date = (Date)formatter.parse(str_date1);  
		  date2 = (Date)formatter.parse(str_date2);
		// System.out.println("Today is " +date );
		 
		 
		 if(date.before(date2))
		 {
			 System.out.println("DATE ONE IS Smaller :----"+str_date1+" DATE 2 "+str_date2);
			 valid="true";
			 
		 }
		 else if(date.after(date2))
		 {
			 System.out.println("DATE ONE IS GREATER :---"+str_date1+" DATE 2 "+str_date2);
			 valid="false";
			 addFieldError("enquiryTODate", "TO date Must Be Smaller Than From Date ");
			
		 }
		  } catch (ParseException e)
		  {
			  logger.error("Exception Occured : - "+e);
			  }  
		  logger.info("End EnquiryAction<dateChanger>");  
		 return valid;
		 }
	/**
	* This method is used to get the Application Context bean form the applicationContext.xml
	* @return EnquiryService enquiryService
	*/

	/**
	* This method is used to get the Application Context
	* @return EnquiryService enquiryService
	*/
	private EnquiryService getEnquiryService() {
		EnquiryService enquiryService = null;
		try{
			//ApplicationContext appcontext = new ClassPathXmlApplicationContext("WebApplicationContext.xml");
			
			enquiryService = (EnquiryService)ApplicationContextProvider.getBean("enquiryService");
		}catch (ApplicationContextException applicationContextException) {
		logger.debug(ConstantUtil.RULE_ACTION+ applicationContextException);	
		}
		
		return enquiryService;
	}

	
	  private List<Boolean>	 select; 
	public List<Boolean> getSelect() {
		return select;
	}
	public void setSelect(List<Boolean> select) {
		this.select = select;
	}
	private String payMessage;
	public String getPayMessage() {
		return payMessage;
	}
	public void setPayMessage(String payMessage) {
		this.payMessage = payMessage;
	}
	/**
	* This method display the data in the view screen(view.jsp)
	* Once the user select the checkbox and click the view button 
	* it will display the selected row data in the view screen
	* @return Selected Row
	*/
	public String callOnPrint() throws IOException { 
		
		
		try{
		   		
		//int selectedMessage= Integer.parseInt(getSeletecMessageCount()); 
		//System.out.println(selectedMessage+"   selectedMessage ");
		    // If this checkbox was selected: 
		    List<Object> list = ((List<Object>) session.get("searchList"));
		    for(int i = 0 ;i<list.size();i++)
		    {
		    	Object ts = ((List<Object>) list).get(i);
		    	if(((PaymentMessage) ts).getMsgRef().equalsIgnoreCase(getSeletecMessageCount())){
		    		DisplayEnquiryData obj = new DisplayEnquiryData();
			    	obj.setMsgType(((PaymentMessage) ts).getMsgType());
			    	obj.setMsgChannel(((PaymentMessage) ts).getMsgChannel());
			    	obj.setMsgCurrency(((PaymentMessage) ts).getMsgCurrency());
			    	obj.setMsgAmount(((PaymentMessage) ts).getMsgAmount());
			    	obj.setMsgValueDate(((PaymentMessage) ts).getMsgValueDate());
			    	obj.setSenderBank(((PaymentMessage) ts).getSenderBank());
			    	obj.setReceiverBank(((PaymentMessage) ts).getReceiverBank());
			    	obj.setMsgDirection(((PaymentMessage) ts).getMsgDirection());
			    	
			    	obj.setTxnReference(((PaymentMessage) ts).getTxnReference());
			    	obj.setBeneficiaryCustomer(((PaymentMessage) ts).getBeneficiaryCustomer());
			    	obj.setNarration(((PaymentMessage) ts).getNarration());
			    	obj.setOrderingCustomer(((PaymentMessage) ts).getOrderingCustomer());
			    	obj.setMsgRef(((PaymentMessage) ts).getMsgRef());
			    	obj.setLastModTime(((PaymentMessage) ts).getLastModTime());
			    	obj.setBeneficiaryAccountNo(((PaymentMessage) ts).getBeneficiaryAccountNo());
			    	obj.setComments(((PaymentMessage) ts).getComments());
			    	this.session.put("display", obj);
			    	break;
		    	}
		    }
		    	/*Object ts = ((List<Object>) session.get("searchList")).get(selectedMessage);
		    	DisplayEnquiryData obj = new DisplayEnquiryData();
		    	obj.setMsgType(((PaymentMessage) ts).getMsgType());
		    	obj.setMsgChannel(((PaymentMessage) ts).getMsgChannel());
		    	obj.setMsgCurrency(((PaymentMessage) ts).getMsgCurrency());
		    	obj.setMsgAmount(((PaymentMessage) ts).getMsgAmount());
		    	obj.setMsgValueDate(((PaymentMessage) ts).getMsgValueDate());
		    	obj.setSenderBank(((PaymentMessage) ts).getSenderBank());
		    	obj.setReceiverBank(((PaymentMessage) ts).getReceiverBank());
		    	obj.setMsgDirection(((PaymentMessage) ts).getMsgDirection());
		    	
		    	obj.setTxnReference(((PaymentMessage) ts).getTxnReference());
		    	obj.setBeneficiaryCustomer(((PaymentMessage) ts).getBeneficiaryCustomer());
		    	obj.setNarration(((PaymentMessage) ts).getNarration());
		    	obj.setOrderingCustomer(((PaymentMessage) ts).getOrderingCustomer());
		    	obj.setMsgRef(((PaymentMessage) ts).getMsgRef());
		    	obj.setLastModTime(((PaymentMessage) ts).getLastModTime());
		    	obj.setBeneficiaryAccountNo(((PaymentMessage) ts).getBeneficiaryAccountNo());*/
		    	
		    	
		    	return "success";
		    	
		}catch (Exception e) {
			logger.error("Exception Occured In EnquiryAction<callOnPrint>");
		}
		  return "success";
		  
	  }
	


	private String getMsgDirection() {
		return null;
	}

	private String msgIndex;
	public String getMsgIndex() {
		return msgIndex;
	}
	public void setMsgIndex(String msgIndex) {
		this.msgIndex = msgIndex;
	}
	@SkipValidation
	public String viewMessage(){
		
	// String paymentStatus = 	getPaymentStatus();
	String messageIndex = getMsgIndex();
	this.session.put("messageIndex", messageIndex);
	
	return "success";
	
	}		


			public void setEnquiryCheckBox(String[] enquiryCheckBox) {
		EnquiryCheckBox = enquiryCheckBox;
	}


	public String[] getEnquiryCheckBox() {
		return EnquiryCheckBox;
	}


	public void setMsgRef(String[] msgRef) {
		this.msgRef = msgRef;
	}


	public String[] getMsgRef() {
		return msgRef;
	}

	private String labelcontent;
	

	public void setLabelcontent(String labelcontent) {
		this.labelcontent = labelcontent;
	}


	public String getLabelcontent() {
		return labelcontent;
	}
	public void validate(){
				
	}
	
	@SkipValidation
	public String fetchMsgData() throws SQLException
	{
		enquiryService = (EnquiryService)ApplicationContextProvider.getBean("enquiryService");
		PaymentMessageService paymentMessageService = (PaymentMessageService)ApplicationContextProvider.getBean("paymentMessageService");
		this.session.put("messageRef",getMessageRef());
		System.out.println("messageRef is "+getMessageRef());
		String screenId = paymentMessageService.getScreenIDFromSupport(getMsgType());
		String returnType = null;
		if(screenId!=null && StringUtils.isNotBlank(screenId) && StringUtils.isNotEmpty(screenId))
		{
			returnType = getText(screenId);
		}
		return returnType;
	}
	
	@SkipValidation
	public String callPrintPreview() {
		try{
		PaymentMessageService paymentMessageService = (PaymentMessageService)ApplicationContextProvider.getBean("paymentMessageService");
		String screenID = paymentMessageService.getScreenIDFromSupport(msgType);
		String returnType = null;
		if(screenID!=null && StringUtils.isNotBlank(screenID) && StringUtils.isNotEmpty(screenID))
		{
			returnType = getText(screenID);
		}else
		{
			returnType= "repair";
		}
	
		String messageIndex = getMsgIndex();
		this.session.put("messageIndex", getSeletecMessageCount());
		this.session.put("histMsgRef", getHistMsgRef());
		this.session.put("paymentMessageType", msgType);
		return returnType;
		}
		catch (Exception e) {
			return INPUT;
		}
	
	}
	
	
	@SkipValidation
	public void callCurrencyCodeDropDown() throws NGPHException{
		try{
			System.out.println("Start:: Inside callCurrencyCodeDropDown");
			
			EnquiryService enquiryService = getEnquiryService();
		
		this.session.put("searchList",null);
		setCurrencyDropDown(enquiryService.getEnquiryList(ConstantUtil.CURRENCY));
		}
		catch(Exception exception){
			logger.error(exception);
		}
		System.out.println("End:: Inside callCurrencyCodeDropDown");
		
	}
	
	
	
	public String callEnquiryHistSearch() throws ParseException
	{
		logger.info("Inside EnquiryAction<callEnquiryHistSearch>");
		EnquiryHistSearchService enquiryHistSearch;
		String forward="EnquiryList";
		try{
			
			enquiryHistSearch = (EnquiryHistSearchService)ApplicationContextProvider.getBean("enquiryHistService");
			
			
			String searchQuery ="";
			
			
			//Checking for paramters getting from the PaymentEnquiry.jsp whether it is null or not
			//checking for channel 
			if(!getEnquiryChannel().equals("NONE"))//check for null are value is selected or not
			{
				searchQuery=searchQuery+" channel ='"+getEnquiryChannel()+"' AND";
			}
			if( !org.apache.commons.lang.StringUtils.isEmpty(getEnquiryMsgType())& !getEnquiryMsgType().equals("NONE"))
			{
				searchQuery=searchQuery+" msgType ='"+getEnquiryMsgType()+"' AND";
			}
			if(!org.apache.commons.lang.StringUtils.isEmpty(getEnquirySubMsgType())& !getEnquirySubMsgType().equals("NONE"))
			{
				searchQuery=searchQuery+" subMsgType ='"+getEnquirySubMsgType()+"' AND";
			}
			if(!org.apache.commons.lang.StringUtils.isEmpty(getEnquiryBeneficiaryCustomer()))
			{
				searchQuery= searchQuery+" concat(beneficiaryCustomerName,' ',beneficiaryCustomerAddress,' ',beneficiaryCustomerID,' ',beneficiaryCustomerCtry,' ',beneficiaryCustAcct,' ',ultimateCreditorName,' ',ultimateCreditorAddress,' ',ultimateCreditorID) like '%"+getEnquiryBeneficiaryCustomer()+"%' AND";
			}
			if(!org.apache.commons.lang.StringUtils.isEmpty(getEnquiryPaymentStatus()) & !getEnquiryPaymentStatus().equals("NONE"))
			{
				String status = PaymentStatusEnum
				.findPaymentStatusEnumByName(getEnquiryPaymentStatus());
				searchQuery=searchQuery+" msgStatus = '"+status+"'And msgStatus IS NOT NULL And";
			}
			
			try{
				
			//checking null for the form date and for to date	
			if(!org.apache.commons.lang.StringUtils.isEmpty(getEnquiryFromDate()) & !org.apache.commons.lang.StringUtils.isEmpty(getEnquiryTODate()))
					{
				String date1=dateChanger(getEnquiryFromDate());
				String date2=dateChanger(getEnquiryTODate());
				String valid=CompareDate(date1,date2);
				if(valid.equals("true")){
				searchQuery=searchQuery+" receivedTime between (to_date('"+date1+"' , 'dd/MM/yyyy')) AND (to_date('"+date2+"' , 'dd/MM/yyyy')) AND";
		//		System.out.println(searchQuery);
				
				}
				else{
					forward="input";}
								
				}
			
					
			
			}
			catch (Exception e) {
				System.out.println(e);
			}
			if(!org.apache.commons.lang.StringUtils.isEmpty(getEnquiryOrderingCustomer()))
					{
						searchQuery= searchQuery+" concat(orderingCustomerName,' ',orderingCustomerAddress,' ',orderingCustomerId,' ',orderingCustomerCtry ,' ',orderingCustAccount,' ',orderingInstitution,' ',orderingInstitutionAcct) like '%"+getEnquiryOrderingCustomer()+"%' AND";
			//			System.out.println(getEnquiryNarration());
					}
			try{
				if(!org.apache.commons.lang.StringUtils.isEmpty(getEnquiryValueDate().toString())){
			//		System.out.println("HELLO THIS IS VALUE DATE");
					searchQuery=searchQuery+" receivedTime between (to_date('"+dateChanger(getEnquiryValueDate())+" 00:00:00' , 'dd/MM/yyyy HH24:MI:SS')) and (to_date('"+dateChanger(getEnquiryValueDate())+" 23:59:59' , 'dd/MM/yyyy HH24:MI:SS')) And";
					
					
								}
				}
				catch (Exception e) {
					System.out.println(e);
				}
			if(!org.apache.commons.lang.StringUtils.isEmpty(getEnquiryBound()))
			{
				searchQuery= searchQuery+" direction = '"+getEnquiryBound()+"' AND";
			//	System.out.println(getEnquiryBound());
			}
			if(!org.apache.commons.lang.StringUtils.isEmpty(getEnquiryCurrency())& !getEnquiryCurrency().equals("NONE"))
			{
				searchQuery= searchQuery+" currency = '" +getEnquiryCurrency()+"' AND";
			}
			if(!org.apache.commons.lang.StringUtils.isEmpty(getEnquiryNarration()))
			{
				searchQuery= searchQuery+" concat(prevInstructingBank,' ',prevInstructingAgentAcct,' ',instructionsForNextAgtCode) like '%" +getEnquiryNarration()+"%' AND";
			}
			
			
			       
			
			//DateFormat format = new SimpleDateFormat("dd MMMM yyyy", getEnquiryValueDate())
			
			if(!org.apache.commons.lang.StringUtils.isEmpty(getEnquiryTransactionReference()))
			{
				searchQuery= searchQuery+" txnReference like '%" +getEnquiryTransactionReference()+"%' AND";
			}
			
			
			//System.out.println("new query string*************"+searchQuery);
			int length=searchQuery.length();
			if(length==0)
			{
				searchQuery = "where msgStatus IS NOT NULL" ;
			}
			else 
				searchQuery= "where "+searchQuery.substring(0, searchQuery.length()-4);
			
			String newstring=searchQuery;
			try{
			//	System.out.println(newstring);
			//session.put("newstring", newstring);
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			
			List<PaymentMessage> searchList = 	enquiryHistSearch.getSearchHistResult(newstring,getEnquiryBound(),getEnquiryScreen());
			this.session.put("searchList",searchList);
		//	System.out.println("SEACH LIST"+searchList.size());
			setEnquirySearchList(searchList);
			logger.info("Operation Done In EnquiryAction<callEnquirySearch>");
			return forward;
		
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
			
			addActionError("Unable To Do Operation.Please Try Again");
			logger.info("Exception In EnquiryAction<callEnquirySearch>");
			if(getEnquiryScreen().equals("FindHistory"))
			return "histroyInput" ;
				else
			return "input";
		
	}
}
