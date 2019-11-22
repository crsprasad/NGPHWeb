package com.logica.ngph.web.action;


import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



import org.apache.commons.lang.NullArgumentException;
import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.springframework.context.ApplicationContextException;


import com.logica.ngph.Entity.Rules;
import com.logica.ngph.Entity.SecUsers;
import com.logica.ngph.common.NGPHException;
import com.logica.ngph.common.ConstantUtil;
import com.logica.ngph.common.dtos.EventAudit;
import com.logica.ngph.common.dtos.EventMaster;
import com.logica.ngph.common.dtos.UserMaintenanceDTO;
import com.logica.ngph.dtos.SearchRuleIDDtos;

import com.logica.ngph.service.AuditService;
import com.logica.ngph.service.PaymentMessageService;
import com.logica.ngph.service.PendingAuthorizationService;
import com.logica.ngph.service.SearchService;
import com.logica.ngph.web.utils.ApplicationContextProvider;
import com.logica.ngph.web.utils.WebConstants;

import com.opensymphony.xwork2.ActionSupport;







public class RulesAction  extends ActionSupport implements SessionAware{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3364264106258064077L;
	static Logger logger = Logger.getLogger(RulesAction.class);
	private String ruleId;
	private String ruledescription;
	private String ruleCategory;
	
	private String validatedRule;
	private String saveAction;
	//private String ruleConditionTest;
	private String ruleValidate;
	private String lhsText;
	private String rhsText;
	private Map<String, Object> session;
	private String ruleSaveData;
	private String submittedRuleData;
	private String bracketButton;
	private String ruleAction;
	private String ruleActParam;
	private String ruleType;
	private String ruleBranch;

	private String ruleDeptName;
	private String ruleCategoryDescription;
	private String lhsDropDown;
	private String operatorDropDown;
	private String rhsDropDown;
	
	private String fieldDropDown;
	private String rhsFieldDropDown;
	
	private String searchAction;
	private boolean staticInput;
	private boolean fieldInput;

	private String staticTxtBox;
	
	private String andOrOperator;
	private String msgDirection;
	private String hiddenTxnRef;
	private String txnRef;
	private String old_NewScreen;
	private String flagForNewData;
	public String getFlagForNewData() {
		return flagForNewData;
	}
	public void setFlagForNewData(String flagForNewData) {
		this.flagForNewData = flagForNewData;
	}
	public String getOld_NewScreen() {
		return old_NewScreen;
	}
	public void setOld_NewScreen(String old_NewScreen) {
		this.old_NewScreen = old_NewScreen;
	}
	private boolean validUserToApprove;

	public String getTxnRef() {
		return txnRef;
	}
	public void setTxnRef(String txnRef) {
		this.txnRef = txnRef;
	}
	public String getHiddenTxnRef() {
		return hiddenTxnRef;
	}
	public void setHiddenTxnRef(String hiddenTxnRef) {
		this.hiddenTxnRef = hiddenTxnRef;
	}
	public String getMsgDirection() {
		return msgDirection;
	}
	public void setMsgDirection(String msgDirection) {
		this.msgDirection = msgDirection;
	}


	private String ruleMsgtype;
	private boolean messageTypeAll;
	private String ruleSaveModifyOrDelete;
	private String checkForSubmit;
	
	//private SearchService searchService;

	public String getCheckForSubmit() {
		return checkForSubmit;
	}
	public void setCheckForSubmit(String checkForSubmit) {
		this.checkForSubmit = checkForSubmit;
	}
	public String getBracketButton() {
		return bracketButton;
	}
	public void setBracketButton(String bracketButton) {
		this.bracketButton = bracketButton;
	}
	
	/*public String getRuleConditionTest() {
		return ruleConditionTest;
	}
	public void setRuleConditionTest(String ruleConditionTest) {
		this.ruleConditionTest = ruleConditionTest;
	}*/
	public String getRuleBranch() {
		return ruleBranch;
	}
	public void setRuleBranch(String ruleBranch) {
		this.ruleBranch = ruleBranch;
	}

	public String getLhsDropDown() {
		return lhsDropDown;
	}
	public void setLhsDropDown(String lhsDropDown) {
		this.lhsDropDown = lhsDropDown;
	}
	public String getOperatorDropDown() {
		return operatorDropDown;
	}
	public void setOperatorDropDown(String operatorDropDown) {
		this.operatorDropDown = operatorDropDown;
	}
	public String getRhsDropDown() {
		return rhsDropDown;
	}
	public void setRhsDropDown(String rhsDropDown) {
		this.rhsDropDown = rhsDropDown;
	}
	public String getFieldDropDown() {
		return fieldDropDown;
	}
	public void setFieldDropDown(String fieldDropDown) {
		this.fieldDropDown = fieldDropDown;
	}


	public String getSearchAction() {
		return searchAction;
	}
	public void setSearchAction(String searchAction) {
		this.searchAction = searchAction;
	}
	public String getStaticTxtBox() {
		return staticTxtBox;
	}
	public void setStaticTxtBox(String staticTxtBox) {
		this.staticTxtBox = staticTxtBox;
	}
	
	public boolean isStaticInput() {
		return staticInput;
	}
	public void setStaticInput(boolean staticInput) {
		this.staticInput = staticInput;
	}
	
	public boolean isFieldInput() {
		return fieldInput;
	}
	public void setFieldInput(boolean fieldInput) {
		this.fieldInput = fieldInput;
	}
	
	public String getRuleDeptName() {
		return ruleDeptName;
	}

	public void setRuleDeptName(String ruleDeptName) {
		this.ruleDeptName = ruleDeptName;
	}
	public String getRuleCategoryDescription() {
		return ruleCategoryDescription;
	}
	public void setRuleCategoryDescription(String ruleCategoryDescription) {
		this.ruleCategoryDescription = ruleCategoryDescription;
	}


	
	public String getRuleId() {
		return ruleId;
	}
	//@RequiredStringValidator(type = ValidatorType.FIELD , message = "RuleID Required")
	
	//@CustomValidator(type = "duplicateRule", message = "RuleID duplicate")
	public void setRuleId(String ruleId) {
		this.ruleId = ruleId;
	}
	public String getRuledescription() {
		return ruledescription;
	}
	public void setRuledescription(String ruledescription) {
		this.ruledescription = ruledescription;
	}
	public String getRuleCategory() {
		return ruleCategory;
	}
	public void setRuleCategory(String ruleCategory) {
		this.ruleCategory = ruleCategory;
	}
	
	public String getRuleAction() {
		return ruleAction;
	}
	public void setRuleAction(String ruleAction) {
		this.ruleAction = ruleAction;
	}
	public String getRuleActParam() {
		return ruleActParam;
	}
	public void setRuleActParam(String ruleActParam) {
		this.ruleActParam = ruleActParam;
	}
	public String getRuleType() {
		return ruleType;
	}
	public void setRuleType(String ruleType) {
		this.ruleType = ruleType;
	}
	
	public String getRuleMsgtype() {
		return ruleMsgtype;
	}
	public void setRuleMsgtype(String ruleMsgtype) {
		this.ruleMsgtype = ruleMsgtype;
	}
	

	private List<String> msgTypeList = new ArrayList<String>();
	public List<String> getMsgTypeList() {
		return msgTypeList;
	}

	public void setMsgTypeList(List<String> msgTypeList) {
		this.msgTypeList = msgTypeList;
		this.session.put("msgTypeList", msgTypeList);
	}

	private List<String> lhsList = new ArrayList<String>();
	private List<String> ActionList = new ArrayList<String>();
	private List<String> ruleIdList = new ArrayList<String>();
	
	
	public List<String> getRuleIdList() {
		return ruleIdList;
	}
	public void setRuleIdList(List<String> ruleIdList) {
		this.ruleIdList = ruleIdList;
	}
	public List<String> getActionList() {
		return ActionList;
	}

	public void setActionList(List<String> actionList) {
		ActionList = actionList;
		this.session.put("ActionList", ActionList);
	}
	public List<String> getLhsList() {
		return lhsList;
	}

	public void setLhsList(List<String> lhsList) {
		this.lhsList = lhsList;
		this.session.put("lhsList", lhsList);
	}

	public String load(){
		
			try{
				// Added to load the bean Context
				//SearchService searchService = getSearchService();
				SearchService searchService =(SearchService)	ApplicationContextProvider.getBean("searchService");
				
				
				session.put("ruleList", searchService.getRuleID());
				
				setMsgTypeList(searchService.getRuleList(ConstantUtil.MESSAGE_TYPE));
				
				setLhsList(searchService.getRuleList(ConstantUtil.LHS));
				
				setActionList(searchService.getRuleList(ConstantUtil.ACTIONS));
				setMsgDirection("I");
				setRuleSaveModifyOrDelete("ADD");
				
			}catch(NGPHException ngphException){
				AuditServiceUtil.logNgphException(ngphException,logger);
			}catch(Exception exception){
				AuditServiceUtil.logException(exception,logger);
			}
	
		return "success";
	}
	/*Method to get the values form Pending Authorization Screen.
	 */
	public String getAuthorization()
	{
		try{
		PendingAuthorizationService pendingAuthorizationService = (PendingAuthorizationService)ApplicationContextProvider.getBean("pendingAuthorizationService");
		load();
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
		String[] stringBreaker = requiredString.split("%");
		for(int i=0;i<stringBreaker.length;i++)
			System.out.println(i+" -- "+stringBreaker[i]);
		setRuleId(stringBreaker[0]);
		if(stringBreaker[1] != null && !"-1".equals(stringBreaker[1]) && !"".equals(stringBreaker[1])){
			setRuleAction(stringBreaker[1]);
		}
		setRuleActParam(stringBreaker[2]);
		setRuleBranch(stringBreaker[3]);
		setMsgDirection(stringBreaker[4]);
		if(stringBreaker[5] != null && !"-1".equals(stringBreaker[5]) && !"".equals(stringBreaker[5])){
			setRuleCategory(stringBreaker[5]);
		}
		setRuleSaveData(stringBreaker[6]);
		setValidatedRule(stringBreaker[7]);
		setRuleDeptName(stringBreaker[8]);
		setRuledescription(stringBreaker[9]);
		setRuleSaveModifyOrDelete(stringBreaker[12].trim());
		
		if(stringBreaker[10].equals(ConstantUtil.MESSAGE_TYPE_ALL))
		{
			setMessageTypeAll(true);
		}
		else if(stringBreaker[10].length()> 2)
		{
			String[] msgValue= stringBreaker[10].split(",");
			setRuleMsgtype(msgValue[0]+msgValue[1]);
		}
		setRuleType(stringBreaker[11]);
		return "success";
		
		}catch(NullPointerException nullPointerException){
			AuditServiceUtil.logNullPointerException(nullPointerException, logger);
		}catch (Exception exception){
			AuditServiceUtil.logException(exception,logger);
		}
		return INPUT;
	}
	
/*	public void setSearchService(SearchService searchService) {
		this.searchService = searchService;
	}*/
	public String saveRules(){
		String OutputPage ="";	
		try{
			
			//ApplicationContext 	appcontext = (ApplicationContext)context.getAttribute(ConstantUtil.BEAN_CONTEXT);
		SearchService	searchService =(SearchService)	ApplicationContextProvider.getBean("searchService");
		AuditService	auditService = (AuditService)ApplicationContextProvider.getBean("auditService");
		PendingAuthorizationService pendingAuthorizationService = (PendingAuthorizationService)ApplicationContextProvider.getBean("pendingAuthorizationService");
		
			System.out.println("ACTION :- "+getSaveAction());
			String value="";
			System.out.println(getHiddenTxnRef());
			String delimtedString = "";
			String delimiter="%";
			Rules rules = new Rules();
			
				
				
				
				rules.setRuleId(getRuleId());
				delimtedString = delimtedString+getRuleId();
				
				String ruleAction = getRuleAction();
				if(ruleAction != null && !"-1".equals(ruleAction) && !"".equals(ruleAction)){
					rules.setRuleAction(ruleAction);
				}
				delimtedString = delimtedString+delimiter+ruleAction;
				
				
				rules.setRuleActionParam(getRuleActParam());
				
				rules.setRuleBranch(getCode(getRuleBranch()));
				rules.setRuleDirection(getMsgDirection());
				
				String ruleCategory = getRuleCategory();
				System.out.println("ruleCategory : -- "+ruleCategory);
				if(ruleCategory != null && !"-1".equals(ruleCategory) && !"".equals(ruleCategory)){
					rules.setRuleCategory(ruleCategory);
				}
				
				rules.setRuleSysCondition(getRuleSaveData());
				rules.setRuleCondition(getValidatedRule());
				//rules.setRuleDept(getRuleDeptName());
				delimtedString = delimtedString+delimiter+getRuleActParam()+delimiter+getRuleBranch()+delimiter+getMsgDirection()+delimiter+getRuleCategory()+delimiter+getRuleSaveData()+delimiter+getValidatedRule()
				+delimiter+getRuleDeptName()+delimiter+getRuledescription();
				
				rules.setRuleDept(getCode(getRuleDeptName()));
				
				rules.setRuleDescription(getRuledescription());
				
				if(isMessageTypeAll()){
				
					rules.setRuleMessageType(ConstantUtil.MESSAGE_TYPE_ALL);
					delimtedString = delimtedString+delimiter+ConstantUtil.MESSAGE_TYPE_ALL;
				}else{
					String msgType = getRuleMsgtype();
					if(msgType != null && !"-1".equals(msgType) && !"".equals(msgType)){
					int msglength = 	msgType.length();
					if(msglength > 2){
						
						rules.setRuleMessageType(msgType.substring(0, msglength-3));
						rules.setRuleSubMessageType(msgType.substring(msglength-3,msglength));
						delimtedString = delimtedString+delimiter+msgType.substring(0, msglength-3)+","+msgType.substring(msglength-3,msglength);
						
					}else{
						rules.setRuleMessageType(msgType);
						delimtedString = delimtedString+delimiter+msgType;
					}
						
						
					}
					
				}
				rules.setRuleType("C");
				delimtedString = delimtedString+delimiter+"C";
			
			
			if(getRuleSaveModifyOrDelete().equals("ADD")){
				if(getSaveAction().equals("Approve") )
					searchService.saveRules(rules);
			delimtedString = delimtedString+delimiter +
					"ADD";
			auditService.saveEventAuditData(getEventAuditDtoForApprove());	
			OutputPage = "ruleSuccess";
			}
			else if(getRuleSaveModifyOrDelete().equals("Modify")){
				if(getSaveAction().equals("Approve")&& value.equals("success"))
				searchService.updateRules(rules);
				delimtedString = delimtedString+delimiter+"Modify";
				auditService.saveEventAuditData(getEventAuditDtoForApprove());
				OutputPage = "ruleSuccess";
			}
			else if (getRuleSaveModifyOrDelete().equals("delete")){
				if(getSaveAction().equals("Approve") && value.equals("success"))
				searchService.deleteRules(rules);
				delimtedString = delimtedString+delimiter+"delete";
				auditService.saveEventAuditData(getEventAuditDtoForApprove());
				OutputPage = "ruleSuccess";
			}
			if(getSaveAction().equals("SAVE"))
			{
				String userId = (String)session.get(WebConstants.CONSTANT_USER_NAME);
				pendingAuthorizationService.getTranRef(delimtedString,"Rules",userId);
				auditService.saveEventAuditData(getEventAuditDtoForSubmit());
				OutputPage = "saveSuccess";
			}
			if(!getSaveAction().equals("SAVE"))
			{
				value= pendingAuthorizationService.changeStatus(getSaveAction(),getHiddenTxnRef());
			}
			
			System.out.println("delimtedString : - "+delimtedString);
		}catch(NGPHException ngphException){
			AuditServiceUtil.logNgphException(ngphException,logger);
		}catch(NullPointerException nullPointerException){
			AuditServiceUtil.logNullPointerException(nullPointerException, logger);
		}catch (Exception exception){
			AuditServiceUtil.logException(exception,logger);
		}
		return OutputPage;
	}

	private EventAudit getEventAuditDtoForSubmit(){
		EventAudit eventAudit = getEventAuditDto();
		eventAudit.setAuditEventId("NGPHGUIRUL0001");
		String userId = (String)session.get(WebConstants.CONSTANT_USER_NAME);	
		String[] extraInformation = {userId," User Rules "};		
		eventAudit.setExtraInformation(extraInformation);
		String eventDescription = "User {0} has Initiated {1}";
		MessageFormat msgFormat = new MessageFormat(eventDescription);		
		eventAudit.setAuditEventDesc(msgFormat.format(extraInformation));
		
		return eventAudit;		
	}
	private EventAudit getEventAuditDtoForApprove(){
		EventAudit eventAudit = getEventAuditDto();
		eventAudit.setAuditEventId("NGPHGUIRUL0002");
		String userId = (String)session.get(WebConstants.CONSTANT_USER_NAME);	
		String[] extraInformation = {userId," User Rules "};		
		eventAudit.setExtraInformation(extraInformation);
		String eventDescription = "User {0} has Approved {1}";
		MessageFormat msgFormat = new MessageFormat(eventDescription);		
		eventAudit.setAuditEventDesc(msgFormat.format(extraInformation));
		
		return eventAudit;		
	}
	private EventAudit getEventAuditDtoForReject(){
		EventAudit eventAudit = getEventAuditDto();
		eventAudit.setAuditEventId("NGPHGUIRUL0003");
		String userId = (String)session.get(WebConstants.CONSTANT_USER_NAME);	
		String[] extraInformation = {userId," User Rules "};		
		eventAudit.setExtraInformation(extraInformation);
		String eventDescription = "User {0} has Rejected {1}";
		MessageFormat msgFormat = new MessageFormat(eventDescription);		
		eventAudit.setAuditEventDesc(msgFormat.format(extraInformation));
		
		return eventAudit;		
	}
	private EventAudit getEventAuditDto(){
		EventAudit eventAudit = new EventAudit();
		PaymentMessageService paymentMessageService = (PaymentMessageService)ApplicationContextProvider.getBean("paymentMessageService");		
		eventAudit.setAuditSource("0");
		String UserID = (String)session.get(WebConstants.CONSTANT_USER_NAME);
		SecUsers userDetails= paymentMessageService.getUserDetails(UserID);
			String msgBranch = userDetails.getUserBranch();
			String msgDept = userDetails.getUserDept();
		eventAudit.setAuditBranch(msgBranch);
		
		eventAudit.setAuditDept(msgDept);
		eventAudit.setAuditSource(getClass().getName());
		
		
		return eventAudit;
		
	}
	
	
	
	/**
	* This method is used to get the Application Context
	* @return SearchService searchService
	*/
	
	/*private SearchService getSearchService() {
		SearchService searchService = null;
		try{
			ApplicationContext appcontext = new ClassPathXmlApplicationContext("WebApplicationContext.xml");
			
			searchService = (SearchService)appcontext.getBean("searchService");
		}catch (ApplicationContextException applicationContextException) {
		AuditServiceUtil.logApplicationException(applicationContextException, logger);
		}
		
		return searchService;
	}*/
	
	public boolean isMessageTypeAll() {
		return messageTypeAll;
	}
	
	public void setMessageTypeAll(boolean messageTypeAll) {
		this.messageTypeAll = messageTypeAll;
	}
	
	/**
	 * This method is used to get the Branch/Department code 
	 * @param code String
	 * @return String code
	 */
	private static String getCode(String code){
		StringTokenizer stringTokenizer = new StringTokenizer(code,"-");
		String splittedCode = null;
		try{
			
			while(stringTokenizer.hasMoreElements()){
				splittedCode =stringTokenizer.nextToken(); 
				break;
			}
		}catch (RuntimeException runtimeException) {
			AuditServiceUtil.logRunTimeException(runtimeException, logger);
		}
		
		return splittedCode;
	}
	/**
	 * This method is used to add the user selected rule condition data into validate Rule Text Area
	 * @param 
	 * @return
	 */
	public String addValidatedRule(){
		try{
			setValidatedRule(getRuleValidate());
			//setRuleConditionTest(getRuleSaveData());
		}catch (NullPointerException nullPointerException) {
			AuditServiceUtil.logNullPointerException(nullPointerException, logger);	
		}
		return "success";
	}
	/**
	 * This method is used to do the ruleCondition validation when save
	 * @param 
	 * @return
	 */
	private void validateRuleCondition(){
		
		
			String validateFinalRule = getSubmittedRuleData();
			String expression = getRegularExpression();
			Pattern pattern = Pattern.compile(expression); 
			StringTokenizer strTiStringTokenizer = new StringTokenizer(validateFinalRule,":");
			
			while(strTiStringTokenizer.hasMoreTokens()){
				Matcher	matcher = pattern.matcher(strTiStringTokenizer.nextToken()); 
				if(!matcher.matches()){
					
					addActionError(ConstantUtil.GIVE_VALID_RULECONDITION);
					break;
				}
				
			}
			
		}
	/**
	 * This method is used to do all Validation
	 * @param 
	 * @return
	 */
	
	public void validate() {
		try{
			String saveAction = getSaveAction();
			
			if(saveAction != null && !saveAction.isEmpty() && saveAction!=""){
				if(saveAction.equals("SAVE") ){
					
					saveValidation();
					
				}else if(saveAction.equals("RULECONDITION")){
					StringBuffer appendedRule = new StringBuffer();
					StringBuffer appendedSaveRule = new StringBuffer();
					
					boolean isMatcher = true;
					boolean isAndOr = false;
						String ruleSaveData = getRuleSaveData();
						if(ruleSaveData!= null && !ruleSaveData.isEmpty() &&  "" != ruleSaveData){
						
							String andOrRadioValue = getAndOrOperator();
							try{
								if(null  != andOrRadioValue && andOrRadioValue.equals("AND") ||andOrRadioValue.equals("OR") ){
									
									isAndOr =true;
								}
							
							}catch(NullPointerException nullPointerException){
								isMatcher = false;
								addActionError(ConstantUtil.ADD_AND_OR);
							}
								
							}
						if(isMatcher)	{
							generateRule(appendedRule, appendedSaveRule);
						
						}
					
					String ruleValidateString = appendedRule.toString();
					String ruleSaveString = appendedSaveRule.toString();
				
					
					if(ruleValidateString != null && !ruleValidateString.isEmpty() && "" != ruleValidateString){
						
						boolean expMatch = false;
					
						String expression = getRegularExpression();
						
						Pattern pattern = Pattern.compile(expression);  
						
						
						if(isMatcher){
							Matcher	matcher =  pattern.matcher(ruleValidateString);
							if(matcher.matches()){  
								
								expMatch = setRuleCondition(isAndOr,
										ruleValidateString, ruleSaveString);
									
							}  
						
						if(!expMatch ){
							
								addFieldError("validatedRule",ConstantUtil.GIVE_VALID_RULECONDITION);
							
						}
					}
						
					}	
				}
			}	
		}catch (Exception exception) {
			AuditServiceUtil.logException(exception,logger);
		}
		
	
	}
	
	/**
	 * This method is used to add the rule condition with previous rule condition
	 * @param isAndOr boolean
	 * @param ruleValidateString String
	 * @param ruleSaveString String
	 * @return
	 */
	private boolean setRuleCondition(boolean isAndOr,
			String ruleValidateString, String ruleSaveString) {
		boolean expMatch = false;
		try{
			
			expMatch = true;
			String andOrOperatorValue = getAndOrOperator();
			if(isAndOr){
				setSubmittedRuleData(getSubmittedRuleData()+":"+" "+andOrOperatorValue+" "+ruleValidateString);	
			}else{
				setSubmittedRuleData(getSubmittedRuleData()+":"+ruleValidateString);	
			}
			String bracketButton = getBracketButton();
			String validatedRule = getValidatedRule();
			String ruleSaveData = getRuleSaveData();
			if(bracketButton!= null && !bracketButton.isEmpty() && !bracketButton.equals("")){
				
				if(bracketButton.equals("(")){
					if(isAndOr){
						setRuleValidate(validatedRule+" " +andOrOperatorValue+" "+"(("+ ruleValidateString+")");	
						setRuleSaveData(ruleSaveData+ "#"+andOrOperatorValue +"#"+"(("+ruleSaveString+")");
						setBracketButton("");
					}else{
						setRuleValidate(validatedRule+"(("+ ruleValidateString+")");	
						setRuleSaveData(ruleSaveData+ "(("+ruleSaveString+")");
						setBracketButton("");
					}
					
				}else if(bracketButton.equals(")")){
					if(isAndOr){
						setRuleValidate(validatedRule+" "+andOrOperatorValue+" "+"("+ ruleValidateString+"))");	
						setRuleSaveData(ruleSaveData+"#"+andOrOperatorValue +"#"+"("+ruleSaveString+"))");
						setBracketButton("");
						
					}else{
						setRuleValidate(validatedRule+"("+ ruleValidateString+"))");	
						setRuleSaveData(ruleSaveData+ "("+ruleSaveString+"))");
						setBracketButton("");
					}
				}
				
			}else{
				if(isAndOr){
					setRuleValidate(validatedRule+" "+andOrOperatorValue+" "+"("+ ruleValidateString+")");	
					setRuleSaveData(ruleSaveData+ "#"+andOrOperatorValue+"#"+"("+ruleSaveString+")");	
				}else{
					setRuleValidate(validatedRule+"("+ ruleValidateString+")");	
					setRuleSaveData(ruleSaveData+ "("+ruleSaveString+")");	
				}
				
			}
				
		}catch(NullArgumentException nullArgumentException){
			AuditServiceUtil.logNullArgumentException(nullArgumentException, logger);
		}
		
		
		return expMatch;
	}
	
	
	/**
	 * This method is used to give all Regular Expression
	 * @param 
	 * @return
	 */
	
	private String getRegularExpression() {
		String expression ="\\s[AND,OR]+\\s[A-Za-z]+\\([A-Za-z0-9]+\\,[1-9]\\)+[\\s]+[<,>,=]+[\\s]+[A-Za-z]+\\([A-Za-z0-9]+\\,[1-9]\\)|";
		expression = expression+"[A-Za-z0-9]+[\\s]+[<,>,=]+[\\s]+[A-Za-z0-9]+|";
		expression = expression+"\\s[AND,OR]+\\s[A-Za-z0-9]+(\\.\\d{2})?+[\\s]+[<,>,=]+[\\s]+[A-Za-z0-9]+(\\.\\d{2})?$|";
		expression = expression+"\\s[AND,OR]+\\s[A-Za-z]+\\([A-Za-z0-9]+(\\.\\d{2})?+\\,[1-9]\\)+[\\s]+[<,>,=]+[\\s]+[A-Za-z0-9]+(\\.\\d{2})?$|";
		expression = expression+"[A-Za-z0-9]+[\\s]+[<,>,=]+[\\s]+[A-Za-z0-9]+(\\.\\d{2})?$|";
		expression = expression+"[A-Za-z0-9]+(\\.\\d{2})?+[\\s]+[<,>,=]+[\\s]+[A-Za-z0-9]+(\\.\\d{2})?$|";
		expression = expression+"[A-Za-z]+\\([A-Za-z0-9]+\\,[1-9]\\)+[\\s]+[<,>,=]+[\\s]+[A-Za-z]+\\([A-Za-z0-9]+\\,[1-9]\\)|";
		expression = expression + "[A-Za-z]+\\([A-Za-z0-9]+\\,[1-9]\\)+[\\s]+[<,>,=]+[\\s]+[A-Za-z0-9]+";
		return expression;
	}
	
	/**
	 * This method is used to form the ruleConditionString
	 * @param appendedRule StringBuffer
	 * @param appendedSaveRule StringBuffer
	 * @return
	 */
	private void generateRule(StringBuffer appendedRule,
			StringBuffer appendedSaveRule) {
		boolean fieldsDropDown = false;
		
		try{
			String fielddropDown = getFieldDropDown();
			if(fielddropDown !=null && !fielddropDown.isEmpty() && !fielddropDown.equals("-1")){
				fieldsDropDown = true;
				appendedRule.append(fielddropDown);
				appendedSaveRule.append("~").append(fielddropDown).append("~");
			}
			String lhsDropDown = getLhsDropDown();
			if(lhsDropDown !=null && !lhsDropDown.isEmpty() && !lhsDropDown.equals("-1")){
				if(fieldsDropDown){
					appendedRule.append("(").append(lhsDropDown).append(",");
					appendedSaveRule.append("(").append("($").append(lhsDropDown).append("$)").append(",");
				}else{
					appendedRule.append(lhsDropDown);
					appendedSaveRule.append("($").append(lhsDropDown).append("$)");
				}
				
				
			}
			String lhsText = getLhsText();
			if( lhsText!=null && !lhsText.isEmpty()){
				if(fieldsDropDown){
					
					appendedRule.append(lhsText).append(")");
					appendedSaveRule.append(lhsText).append(")");
				}
				
			}
			String operatorDropDown = getOperatorDropDown();
			if(operatorDropDown !=null && !operatorDropDown.isEmpty() && !operatorDropDown.equals("-1")){
				
					appendedRule.append(" ").append(operatorDropDown).append(" ");
					appendedSaveRule.append("~").append(operatorDropDown).append("~");
				
				
			}
			
			String staticTextBox = getStaticTxtBox();
			if(staticTextBox !=null && !staticTextBox.isEmpty()){
				appendedRule.append(staticTextBox);
				appendedSaveRule.append("({").append(staticTextBox).append("})");
			}
			boolean isRhsFieldDropDown = false;
			String rhsFieldDropDown = getRhsFieldDropDown();
			if( rhsFieldDropDown!=null && !rhsFieldDropDown.isEmpty() && !rhsFieldDropDown.equals("-1")){
				isRhsFieldDropDown = true;
				appendedRule.append(rhsFieldDropDown);
				appendedSaveRule.append("~").append(rhsFieldDropDown).append("~");
			}
			String rhsDropDown = getRhsDropDown();
			if(rhsDropDown !=null && !rhsDropDown.isEmpty() && !rhsDropDown.equals("-1")){
				
				if(isRhsFieldDropDown){
					appendedRule.append("(").append(rhsDropDown).append(",");
					appendedSaveRule.append("(").append("($").append(rhsDropDown).append("$)").append(",");
				}else{
					appendedRule.append(rhsDropDown);
					appendedSaveRule.append("($").append(rhsDropDown).append("$)");
				}
				
			}
			String rhsText  = getRhsText();
			if(rhsText !=null && !rhsText.isEmpty()){
				
				if(isRhsFieldDropDown){
					appendedRule.append(rhsText).append(")");
					appendedSaveRule.append(rhsText).append(")");
				}
				
			}
		}catch (RuntimeException runtimeException) {
			AuditServiceUtil.logRunTimeException(runtimeException, logger);
		}
		
	}
	
	
	
	/**
	 * This method is used to validate RuleId-Required,Duplicate-Rule-Id
	 * @param 
	 * @return
	 */
	
	private void saveValidation() {
		
		try{
			String ruleId = getRuleId();
			if(ruleId == null || getRuleId().length() == 0){
				addFieldError("ruleId",ConstantUtil.RULE_ID_REQUIRED );
			}else{
				ruleIdDuplicateValidation();
			}
			
			String validateFinalRule = getSubmittedRuleData();
			
			if((validateFinalRule == null || validateFinalRule.isEmpty() || "" == validateFinalRule) && !getRuleSaveModifyOrDelete().equals("delete")){
				addFieldError("validatedRule", ConstantUtil.RULE_CONDITION_REQUIRED);
			}else{
				validateRuleCondition();
			}
		}catch ( Exception exception) {
			AuditServiceUtil.logException(exception,logger);
		}
		
		
	}
	
	
	/**
	 * This method is used to validate duplicate Rule Id
	 * @param 
	 * @return
	 */
	private void ruleIdDuplicateValidation() {
		
				try{
					@SuppressWarnings("unchecked")
					List<String> ruleList = (List<String>)session.get("ruleList");
					if(ruleList != null && !org.apache.commons.lang.StringUtils.isEmpty(getRuleSaveModifyOrDelete())){
						for (String ruleId : ruleList) {
							
						    if(ruleId.equals(getRuleId()) && getRuleSaveModifyOrDelete().equals("ADD")){
						    	
						    	addFieldError("ruleId", ConstantUtil.DUPLICATE_RULE_ID);
						    }
						}	
					}
				}catch (ClassCastException classCastException) {
				AuditServiceUtil.logClassCastException(classCastException, logger);
				}
			
		}
	
	

	public String getLhsText() {
		return lhsText;
	}
	//@RegexFieldValidator(type = ValidatorType.FIELD , message = "Enter Number Field",expression = "[0-9]")
	public void setLhsText(String lhsText) {
		this.lhsText = lhsText;
	}
	public String getRhsText() {
		return rhsText;
	}
	//@RegexFieldValidator(type = ValidatorType.FIELD , message = "Enter Number Field",expression = "[0-9]")
	public void setRhsText(String rhsText) {
		this.rhsText = rhsText;
	}
	public String getRhsFieldDropDown() {
		return rhsFieldDropDown;
	}
	public void setRhsFieldDropDown(String rhsFieldDropDown) {
		this.rhsFieldDropDown = rhsFieldDropDown;
	}

	public Map<String, Object> getSession() {
		return session;
	}

	public String getRuleValidate() {
		return ruleValidate;
	}
	public void setRuleValidate(String ruleValidate) {
		this.ruleValidate = ruleValidate;
	}
	public String getAndOrOperator() {
		return andOrOperator;
	}
	public void setAndOrOperator(String andOrOperator) {
		this.andOrOperator = andOrOperator;
	}
	public String getRuleSaveData() {
		return ruleSaveData;
	}
	public void setRuleSaveData(String ruleSaveData) {
		this.ruleSaveData = ruleSaveData;
	}
	public String getValidatedRule() {
		return validatedRule;
	}
	public void setValidatedRule(String validatedRule) {
		this.validatedRule = validatedRule;
	}
	public String getSaveAction() {
		return saveAction;
	}
	public void setSaveAction(String saveAction) {
		this.saveAction = saveAction;
	}
	public String getSubmittedRuleData() {
		return submittedRuleData;
	}
	public void setSubmittedRuleData(String submittedRuleData) {
		this.submittedRuleData = submittedRuleData;
	}
	public void setSession(Map<String, Object> session) {
		this.session = session;
		
	}
	public void setRuleSaveModifyOrDelete(String ruleSaveModifyOrDelete) {
		this.ruleSaveModifyOrDelete = ruleSaveModifyOrDelete;
	}
	public String getRuleSaveModifyOrDelete() {
		return ruleSaveModifyOrDelete;
	}
	public boolean getValidUserToApprove() {
		return validUserToApprove;
	}
	public void setValidUserToApprove(boolean validUserToApprove) {
		this.validUserToApprove = validUserToApprove;
	}
	

	@SkipValidation
	public String callSeeOldData()
	{
		try{
			if(getOld_NewScreen().equals("OLD")){
				SearchService searchService  =(SearchService)	ApplicationContextProvider.getBean("searchService");
				
				
				
				SearchRuleIDDtos dtos=  searchService.dataRuleIDSearch(getRuleId()).get(0);
				setRuleId(dtos.getSearchRuleID());
				setRuledescription(dtos.getSearchDescription());
				setValidatedRule(dtos.getRuleCondition());
				setRuleAction(dtos.getRuleAction());
				setRuleActParam(dtos.getRuleActionParam());
				setRuleCategory(dtos.getRuleCategory());
				setRuleBranch(dtos.getRuleBranch());
				setRuleDeptName(dtos.getRuleDept());
				setRuleMsgtype(dtos.getRuleMessageType()+dtos.getRuleSubMessageType());
				setFlagForNewData("flagForNewData");
				}else if(getOld_NewScreen().equals("NEW"))
				{
					getAuthorization();
					setFlagForNewData(null);
				}
			setCheckForSubmit("Display_Approve_Reject");
			setValidUserToApprove(true);
		return SUCCESS;	
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

		addActionError("Unable to perform the operation. Please try again");
		return "input";
}
	
	
	
	
}
