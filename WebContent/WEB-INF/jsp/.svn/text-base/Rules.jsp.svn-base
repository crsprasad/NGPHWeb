<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<s:head />
<script type="text/javascript">
function callSeeOldData(data)
{
	//alert(data)
	document.getElementById("old_NewScreen").value=data;
	var aa = document.getElementById('form');
	for ( var i = 0; i < aa.elements.length-3 ; i++) {
		
		aa.elements[i].disabled = false;
	}
	document.forms[0].action="callSeeOldDataRulesMaitenance";
	document.forms[0].submit();
}
function callSearch(Action) {
	var radioButtons = document.getElementsByName("msgDirection")
if(radioButtons[0].checked)
	{
	document.getElementById("messageDirection").value = 'I';
	}
else{
document.getElementById("messageDirection").value = 'O';
}
document.getElementById("searchAction").value = Action;
	
	
	window.open(
			"<s:url action='showSearchScreen' windowState='EXCLUSIVE' />",
			'mywindow', 'top=50,left=250,width=750,height=410');
}
function callDisableOnDelete(form)
{
		
		var radioButtons = document.getElementsByName("ruleSaveModifyOrDelete")	;
		var btnDepartment = document.getElementById("btnDepartment");
 		var validatedRule = document.getElementById("validatedRule");
   	    var btnCondition = document.getElementById("btnCondition");
   	 var msgList = document.getElementById("ruleMsgtype");
   	 var ruledescription = document.getElementById("ruledescription");
   	 var btnBranch = document.getElementById("btnBranch");
   	
   	var btnDepartment = document.getElementById("btnDepartment");
  	 var ruleCategory = document.getElementById("ruleCategory");

  	var lhsDropDown = document.getElementById("lhsDropDown");
 	 var lhsFieldDropDown = document.getElementById("lhsFieldDropDown");
   	
   	
   	 var ruledescription = document.getElementById("ruledescription");
	   

	  //  var ruleMsgtype = document.getElementById("ruleMsgtype");
	  
	  var lhsText = document.getElementById("lhsText");
  	

  	var ruleActParam = document.getElementById("ruleActParam");
 	 var ruleAction1 = document.getElementById("ruleAction1");
   	
	   
		   
		    var ruleAction1 = document.getElementsByTagName("ruleAction1");
		        if (radioButtons[2].checked) {
		        	var fieldDis = document.getElementById("validatedRule");

		    		fieldDis.value = '';
		    		btnBranch.disabled = true;
		    		ruledescription.disabled = true;
		    		msgList.disabled = true;
		    		validatedRule.disabled = true;
		    		btnCondition.disabled = true;
		    		btnDepartment.disabled = true;
		    		ruleCategory.disabled = true;

		    		lhsDropDown.disabled = true;
		    		lhsFieldDropDown.disabled = true;

		    		lhsText.disabled = true;

		    		ruleActParam.disabled = true;
		    		ruleAction1.disabled = true;
		    			    		
		       }
		        else{
		        	ruledescription.disabled = false;
		    		validatedRule.disabled = false;
		    		btnCondition.disabled = false;
		    		ruledescription.disabled = false;
		    		btnBranch.disabled = false;
		    		msgList.disabled = false;
		    		btnDepartment.disabled = false;
		    		ruleCategory.disabled = false;
		    		lhsDropDown.disabled = false;
		    		lhsFieldDropDown.disabled = false;
		    		lhsText.disabled = false;

		    		ruleActParam.disabled = false;
		    		ruleAction1.disabled = false;
			        }
			        
	

}
function callClear()
{
var fieldDis = document.getElementById("validatedRule");
	
	fieldDis.value = '';
}
function callSearchRuleID(Action) {

	document.getElementById("searchAction").value = Action;
		
		
		window.open(
				"<s:url action='showRuleIDSearchScreen' windowState='EXCLUSIVE' />",
				'mywindow', 'top=50,left=250,width=750,height=410');
	}
function callStaticRadio(selectedRadio) {
	
	var staticTxt = document.getElementById("staticTxt");
	var fieldDis = document.getElementById("rhsFieldDropDown");
	var rhsDis = document.getElementById("rhsDropDown");
	var rhsTextBox = document.getElementById("rhsText");

	if (selectedRadio == "true") {

		fieldDis.disabled = true;
		fieldDis.value = '-1';
		rhsDis.disabled = true;
		rhsDis.value = '-1';
		rhsTextBox.disabled = true;
		rhsTextBox.value = "";
		staticTxt.disabled = false;
	} else if (selectedRadio == "Field") {
		staticTxt.disabled = true;
		staticTxt.value = "";
		fieldDis.disabled = false;
		rhsDis.disabled = false;
		rhsTextBox.disabled = false;
	}
}

function resetField() {

	
	var fieldDis = document.getElementById("rhsFieldDropDown");
	
	fieldDis.value = '-1';
	var rhsDis = document.getElementById("rhsDropDown");
	
	rhsDis.value = '-1';
	var rhsTextBox = document.getElementById("rhsText");
	
	rhsTextBox.value = "";
}

function callSave(saveAction) {

	document.getElementById("saveAction").value = saveAction;
	
	
}
function callSubmit(saveAction)
{
	document.getElementById("saveAction").value = saveAction;
	document.forms[0].action="saveRulesruleAction";
	document.forms[0].submit();
}
function callBracket(buttonValue) {

	var bracketBut = document.getElementById("bracketButton");
	bracketBut.value = buttonValue;

}
function show(){
	
	var val =document.ruleForm.ruleAction.value;
		if( val!="-1")
		document.getElementById('btnAction').style.visibility = 'visible';
				else{
			document.getElementById('btnAction').style.visibility = 'hidden';}
}
function disableMsgType() {

	var msgList = document.getElementById("ruleMsgtype");
	var msgCheckBox = document.getElementById("messageTypeAll");

	if (msgCheckBox.checked == true) {
		msgList.disabled = true;
		//alert("checked");
	}
	if (msgCheckBox.checked == false) {
		msgList.disabled = false;

		//alert("not");
	}

}	
</script>

<div id="contentPane">
<div id="contentPaneTop"></div>
<div id="contentPaneFluid">
<div id="contentPaneInternal"><span class="crumbs"><a
	href="#">Maintenance</a>&nbsp;>&nbsp;Rules</span></div>
<div id="content">
<h1>Rules</h1>
<s:form action="ruleAction" method="post" name="ruleForm" id="form"
	validate="true">
	<s:hidden name="old_NewScreen" id="old_NewScreen"></s:hidden>
	<s:hidden name="txnRef" id="txnRef"></s:hidden>
	<table width="100%" cellpadding="0" cellspacing="0" border="0">
		<tr>
			<td><!--Error Msg Div Starts --> <s:if test="hasFieldErrors()">
				<div class="errorMsg"><span class="errorMsgInline"><s:actionerror /><s:fielderror /></span></div>
			</s:if> <!--Error Msg Div Ends -->
			<div width="100%" class="dataGrid">
			<table width="100%" cellpadding="3" cellspacing="1" border="0"
				bgcolor="#ffffff">
				<tr>
					<td width="20%" class="textRight"><s:label value="Rule Action"></s:label> </td><td width="30%" class="text">
											<s:radio list="{'ADD','Modify','delete'}"
												id="ruleSaveModifyOrDelete" cssClass="dropDown"
												
												name="ruleSaveModifyOrDelete" value="ruleSaveModifyOrDelete" onclick="callDisableOnDelete(this.form)"></s:radio></td>
												
								<td td width="20%" class="textRight"><s:label value = " Direction" ></s:label></td>
							<td width="30%" class="text"><s:radio list="{'I','O'}" cssClass="dropDown" name ="msgDirection" id="msgDirection" value="msgDirection"/>
							</td>
				</tr>
				<tr>
					<td width="20%" class="textRight"><s:label
						value="%{getText('label.ruleId')}"></s:label>:&nbsp;</td>
					<td width="30%" class="text"><s:textfield id = "ruleId" name="ruleId"
						cssClass="txtField"></s:textfield><input type="button" value="Search Rule ID..."
						onclick="callSearchRuleID('RULEID')" class="btn" /></td>
					<td width="20%" class="textRight"><s:label
						value="%{getText('label.ruledescription')}"></s:label>:&nbsp;</td>
					<td width="30%" class="text"><s:textfield name="ruledescription" id ="ruledescription"
						cssClass="txtField"></s:textfield></td>
				</tr>
				<tr>
					<td class="textRight"><s:label
						value="%{getText('label.ruleBranch')}"></s:label>:&nbsp;</td>
					<td class="text"><s:textfield name="ruleBranch"	id="ruleBranch" cssClass="txtField" theme="simple"></s:textfield>
					<input type="button" value="Search Branches..."
						onclick="callSearch('BRANCH')" class="btn" id ="btnBranch"/></td>
					<td class="textRight"><s:label
						value="%{getText('label.ruleDeptName')}"></s:label>:&nbsp;</td>
					<td class="text"><s:textfield name="ruleDeptName"
						id="ruleDeptName" cssClass="txtField"></s:textfield> <input
						type="button" value="Search Departments"
						onclick="callSearch('DEPARTMENT')" class="btn" id="btnDepartment"/></td>
				</tr>
				<tr>
					<td class="textRight"><s:label
						value="%{getText('label.ruleMsgtype')}"></s:label>:&nbsp;</td>
					<td class="text"><s:select list="#session.msgTypeList"
						name="ruleMsgtype" id="ruleMsgtype" headerKey="-1"
						headerValue="Select message Type" cssClass="dropDown"></s:select>
					&nbsp;&nbsp;&nbsp;&nbsp;<s:checkbox name="messageTypeAll"
						id="messageTypeAll" key="label.ruleAllMsgtype"
						onclick="disableMsgType()"></s:checkbox><s:label
						value="Select all message types"></s:label></td>
					<td class="textRight"><s:label
						value="%{getText('label.ruleCategory')}"></s:label>:&nbsp;</td>
					<td class="text"><s:select headerKey="-1"
						headerValue="Select Rule Category" list="{'RTNG','INTV','MOVE'}"
						name="ruleCategory" cssClass="dropDown" id = "ruleCategory" /></td>
				</tr>
			</table>
			<table width="100%" cellpadding="3" cellspacing="1" border="0"
				bgcolor="#ffffff">
				<tr>
					<td colspan="4" class="textCenter"><br />
					<table width="97%" cellpadding="5" cellspacing="0" align="center"
						border="0">
						<tr>
							<td class="text" width="5%"><!-- -Left Braces Button --><input
								type="button" value="(" onclick="callBracket(this.value)"></td>
							<td class="textBold" width="35%">&nbsp;<s:label value="LHS"></s:label>&nbsp;
							<table width="92%" cellpadding="0" cellspacing="0" border="0">
								<tr>
									<td colspan="3" class="greyBg"><img
										src="images/spacer.gif" width="1" height="1" /></td>
								</tr>
								<tr>
									<td class="greyBg"><img src="images/spacer.gif" width="1"
										height="1" /></td>
									<td>
									<table width="100%" cellpadding="5" cellspacing="0" border="0"
										class="text">
										<tr>
											<td class="text"><s:label value="Functions"></s:label> <s:select
												list="{'subStr','left','right'}" id="lhsFieldDropDown"
												cssClass="dropDown" headerKey="-1"
												headerValue="Select Field" name="fieldDropDown"></s:select>
											</td>
											<td class="text"><s:label value="Message Fields"></s:label>
											<s:select list="#session.lhsList" headerKey="-1"
												cssClass="dropDown" headerValue="Select LHS"
												name="lhsDropDown" id ="lhsDropDown"></s:select></td>
											<td class="text"><s:label value="Add Text"></s:label> <s:textfield
												name="lhsText" id="lhsText" cssClass="txtField"></s:textfield>
											</td>
										</tr>
										<tr>
											<td><br />
											<br />
											</td>
											<td>&nbsp;</td>
											<td>&nbsp;</td>
										</tr>
									</table>
									</td>
									<td class="greyBg"><img src="images/spacer.gif" width="1"
										height="1" /></td>
								</tr>
								<tr>
									<td colspan="3" class="spacer"></td>
								</tr>
							</table>
							</td>
							<td class="textCenter" width="10%"><s:label
								value="%{getText('label.Operator')}"></s:label>
							<table width="100%" cellpadding="5" cellspacing="0" border="0">
								<tr>
									<td><br />
									<s:select list="{'>','<','=','  like'}" id="operatorDropDown"
										cssClass="dropDown" headerKey="-1"
										headerValue="Select Operator" name="operatorDropDown"></s:select></td>
								</tr>
							</table>
							</td>
							<td class="textBold" width="45%">&nbsp;<s:label value="RHS"></s:label>&nbsp;
							<table width="98%" cellpadding="0" cellspacing="0" border="0">
								<tr>
									<td colspan="3" class="greyBg"><img
										src="images/spacer.gif" width="1" height="1" /></td>
								</tr>
								<tr>
									<td class="greyBg"><img src="images/spacer.gif" width="1"
										height="1" /></td>
									<td>
									<table width="100%" cellpadding="5" cellspacing="0" border="0"
										class="text">
										<tr>
											<td class="text"><s:radio name="fieldInput"
												value="Field" list="{'Field'}"
												onclick="callStaticRadio (this.value)" /></td>
											<td class="text"><s:label value="Functions"></s:label> <br />
											<s:select list="{'subStr','left','right'}"
												id="rhsFieldDropDown" disabled="true" cssClass="dropDown"
												headerKey="-1" headerValue="Select Field"
												name="rhsFieldDropDown"></s:select></td>
											<td class="text"><s:label value="Message Fields"></s:label>
											<br />
											<s:select list="#session.lhsList" headerKey="-1"
												id="rhsDropDown" disabled="true" cssClass="dropDown"
												headerValue="Select RHS" name="rhsDropDown"></s:select></td>
											<td class="text"><s:label value="Add Text"></s:label> <br />
											<s:textfield name="rhsText" id="rhsText" cssClass="txtField"
												disabled="true"></s:textfield></td>
										</tr>
										<tr>
											<td class="text"><s:radio name="fieldInput"
												list="#{'true':'Static'}" value="true"
												onclick="callStaticRadio(this.value)" /></td>
											<td colspan="3" class="text"><s:textfield
												name="staticTxtBox" id="staticTxt" cssClass="txtField"
												onclick="resetField()"></s:textfield></td>
										</tr>
									</table>
									</td>
									<td class="greyBg"><img src="images/spacer.gif" width="1"
										height="1" /></td>
								</tr>
								<tr>
									<td colspan="3" class="spacer"></td>
								</tr>
							</table>
							</td>
							<td class="text" width="5%"><!-- -Right Braces Button --><input
								type="button" value=")" onclick="callBracket(this.value)"></td>
						</tr>
					</table>
					<table width="35%" cellpadding="5" cellspacing="0" border="0"
						align="center">
						<tr>
							<td><s:radio name="andOrOperator" id="andOrRadio"
								list="{'AND','OR'}" /></td>
							
						</tr>
					</table>
					<table width="50%" cellpadding="5" cellspacing="0" align="center"
						border="0">
						<tr>
							<td class="text">
							<table width="100%" cellpadding="10" cellspacing="0" border="0"
								align="center">
								<tr>
									<td class="text"><s:label value="Action ID"></s:label><br />
									<s:select key="" headerKey="-1" cssClass="dropDown"
										headerValue="Select Action ID" list="#session.ActionList" id="ruleAction1" name="ruleAction" onchange="show()"/></td>
									<td class="text"><s:label
										value="%{getText('label.ruleActParam')}"></s:label><br />
									<s:textfield name="ruleActParam" id ="ruleActParam" cssClass="txtField"></s:textfield></td>
									<td class="text"><input type="button" value="Search Action..."
						onclick="callSearch('ACTION')" class="btn" id="btnAction" style="visibility:hidden"/>
									<s:submit value="Add Condition" cssClass="btn" id ="btnCondition"
										action="addValidatedRuleruleAction"
										onclick="callSave('RULECONDITION')">
									</s:submit></td>
									<td class="text"><s:label
										value="%{getText('label.ruleCondition')}"></s:label><br />
									<s:textarea name="validatedRule" cols="70" rows="5"
										id="validatedRule" wrap="ture" cssClass="txtField"></s:textarea><input type = "button" cssClass="btn" value ="Clear" onclick="callClear()"/></td>
										
								</tr>
								
							</table>
							</td>
						</tr>
					</table>
					<br />
					</td>
				</tr>
			</table>
			</div>
			</td>
		</tr>
		<tr>
			<td>
			<s:if test="%{checkForSubmit!='Display_Approve_Reject'}"><input type="button" value="Save Rule" onclick="callSubmit('SAVE')"></s:if>
			<s:if test="%{checkForSubmit=='Display_Approve_Reject'}">
				<s:if test="%{validUserToApprove}">
					<input type="button" value="Approve" onclick="callSubmit('Approve')" id="approveBtn">
					<input type="button" value="Reject" onclick="callSubmit('Reject')" id="rejectBtn">
					<s:if test="%{flagForNewData != 'flagForNewData'}">
					<input type="button" value="Old Data" id="oldBtn" onclick="callSeeOldData('OLD')" >
						</s:if>
						<s:if test="%{flagForNewData == 'flagForNewData'}">
						<input type="button" value="New Data" id="newBTN" onclick="callSeeOldData('NEW')" >
						</s:if>
				</s:if>
				<s:if test="%{!validUserToApprove}">
					<input type="button" value="Approve" onclick="callSubmit('Approve')" disabled="disabled">
					<input type="button" value="Reject" onclick="callSubmit('Reject')" disabled="disabled">
					<s:if test="%{flagForNewData != 'flagForNewData'}">
					<input type="button" value="Old Data" onclick="callSeeOldData('OLD')" disabled="disabled">
						</s:if>
						<s:if test="%{flagForNewData == 'flagForNewData'}">
						<input type="button" value="New Data" onclick="callSeeOldData('NEW')" disabled="disabled">
					</s:if>
				</s:if>
			</s:if>
			</td>
		</tr>
	</table>
	<s:hidden id="searchAction" name="searchAction"></s:hidden>
	<s:hidden id="messageDirection" name="messageDirection"></s:hidden>
	<s:hidden id="ruleSaveData" name="ruleSaveData"></s:hidden>
	<s:hidden id="saveAction" name="saveAction"></s:hidden>
	<s:hidden id="bracketButton" name="bracketButton"></s:hidden>
	<s:hidden id="submittedRuleData" name="submittedRuleData"></s:hidden>
	<s:hidden id="subStr" name="subStr"></s:hidden>
	<s:hidden id="authorizationType" name="authorizationType"></s:hidden>
	<s:hidden id="hiddenTxnRef" name="hiddenTxnRef"></s:hidden>
	
</s:form>
<script type="text/javascript">
var old_new = document.getElementById("old_NewScreen").value;

if(old_new=="NEW")
	{
		document.getElementById("approveBtn").disabled=false;
		document.getElementById("rejectBtn").disabled=false;
	}else if(old_new=="OLD")
	{
		document.getElementById("approveBtn").disabled=true;
		document.getElementById("rejectBtn").disabled=true;
	}

var radioButtons = document.getElementsByName("ruleSaveModifyOrDelete")	;
//alert("1"+radioButtons[1].checked);
//alert("2"+radioButtons[2].checked);
if(radioButtons[1].checked!=true)
	{
	 document.getElementById("oldBtn").disabled=true;
	}
var check= document.getElementById("hiddenTxnRef").value;
if(check!=''){
var radioButtons = document.getElementsByName("ruleSaveModifyOrDelete")	;
var btnDepartment = document.getElementById("btnDepartment");
	var validatedRule = document.getElementById("validatedRule");
   var btnCondition = document.getElementById("btnCondition");
var msgList = document.getElementById("ruleMsgtype");
var ruledescription = document.getElementById("ruledescription");
var btnBranch = document.getElementById("btnBranch");

var btnDepartment = document.getElementById("btnDepartment");
var ruleCategory = document.getElementById("ruleCategory");

var lhsDropDown = document.getElementById("lhsDropDown");
var lhsFieldDropDown = document.getElementById("lhsFieldDropDown");


var ruledescription = document.getElementById("ruledescription");


//  var ruleMsgtype = document.getElementById("ruleMsgtype");

var lhsText = document.getElementById("lhsText");


var ruleActParam = document.getElementById("ruleActParam");
var ruleAction1 = document.getElementById("ruleAction1");
btnBranch.disabled = true;
ruledescription.readOnly = true;
msgList.readOnly = true;
validatedRule.readOnly = true;
btnCondition.disabled = true;
btnDepartment.disabled = true;
ruleCategory.readOnly = true;

lhsDropDown
lhsFieldDropDown.readOnly = true;

lhsText.readOnly = true;

ruleActParam.readOnly = true;
ruleAction1.readOnly = true;
}
</script>
</div>
</div>
</div>
<div id="contentPaneBottom"></div>
