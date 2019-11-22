<%@page import="com.opensymphony.xwork2.ActionContext"%>
<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@taglib prefix="sx" uri="/struts-dojo-tags"%>
<link href="SpryAssets/SpryCollapsiblePanel.css" rel="stylesheet" type="text/css" />
<script src="SpryAssets/SpryCollapsiblePanel.js" type="text/javascript"></script>
<script type="text/javascript">

function callMaintain()
{
	var isConfirm = confirm("Need to Edit Password Security Policy!");
	 var aa = document.getElementById('passwordPolicyForm');

	 for ( var i = 0; i < aa.length; i++) 
		{
			aa.elements[i].disabled = false;
		}

		document.getElementById("ResetId").disable=false;
		document.getElementById("submitButtonId").disable=false;
}


function callSubmit(saveAction){
	var aa = document.getElementById('passwordPolicyForm');
	for ( var i = 0; i < aa.length-3; i++) {
		aa.elements[i].disabled = false;
	}
	document.getElementById("saveAction").value = saveAction;
	document.forms[0].action="saveSecurityPolicy";
	document.forms[0].submit();
}


function callReset()
{

	document.forms[0].action="policyReset";
	document.forms[0].submit();
}



</script>
<sx:head parseContent="true" />
<s:head />
<div id="contentPane">
<div id="contentPaneTop"></div>
<div id="contentPaneFluid">
<div id="contentPaneInternal">
	<span class="crumbs">
		<a href="#">Maintenance</a>&nbsp;>&nbsp;Password Security Policy
	</span>
</div>
<div id="content">
	<h1>Password Security Policy</h1>
	<!--Error Msg Div Starts -->
	<div>
		<s:if test="hasFieldErrors() || hasActionErrors()">
			<div class="errorMsg">
				<span class="errorMsgInline">
					<s:actionerror /><s:fielderror />
				</span>
			</div>
		</s:if>
	</div>
	<!--Error Msg Div Ends -->
<s:form method="post" name="passwordPolicyForm" id="passwordPolicyForm">
<s:hidden id="saveAction" name="saveAction"></s:hidden>
<s:hidden id="hiddenTxnRef" name="hiddenTxnRef"></s:hidden>
<s:hidden id="validUserToApprove" name="validUserToApprove"></s:hidden>
<s:hidden name="txnRef" id="txnRef"></s:hidden>
		<!-- Collapsible Panels Start-->
		<div id="CollapsiblePanel1" class="CollapsiblePanel">
			<div class="CollapsiblePanelTab">&nbsp;Password Security Policy Details</div>
			<div class="CollapsiblePanelContent">
				<div>
					<!-- Group One Div Starts --> 
					<s:div id="main" cssClass="dataGrid" style="width:100%;"> 
						<table width="100%" cellpadding="3" cellspacing="1" border="0" bgcolor="#ffffff">
							<tr>
								<td class="textRight" width="50%">
									<s:label value="Minimum Password Length"></s:label>:<span class="mandatory">*</span>
								</td>
								<td class="text" width="50%">
									<s:textfield name="minChars" id="minChars" cssClass="text" maxLength="2"></s:textfield>
								</td>
							</tr>
							<tr>
								<td class="textRight" width="50%">
									<s:label value="Maximum Password Length"></s:label>:<span class="mandatory">*</span>
								</td>
								<td class="text" width="50%">
									<s:textfield name="maxChars" id="maxChars" cssClass="text" maxLength="2"></s:textfield>
								</td>
							</tr>
							<tr>
								<td class="textRight" width="50%">
									<s:label value="No of Digits"></s:label>:<span class="mandatory">*</span>
								</td>
								<td class="text" width="50%">
									<s:textfield name="noofDigits" id="noofDigits" cssClass="text" maxLength="2"></s:textfield>
								</td>
							</tr>
							<tr>
								<td class="textRight" width="50%">
									<s:label value="No of Special Character"></s:label>:<span class="mandatory">*</span>
								</td>
								<td class="text" width="50%">
									<s:textfield name="noofSpecialChars" id="noofSpecialChars" cssClass="text" maxLength="2"></s:textfield>
								</td>
							</tr>
							<tr>
								<td class="textRight" width="50%">
									<s:label value="No of Upper Case"></s:label>:<span class="mandatory">*</span>
								</td>
								<td class="text" width="50%">
									<s:textfield name="noofUpperChars" id="noofUpperChars" cssClass="text" maxLength="2"></s:textfield>
								</td>
							</tr>
							<tr>
								<td class="textRight" width="50%">
									<s:label value="No of Lower Case"></s:label>:<span class="mandatory">*</span>
								</td>
								<td class="text" width="50%">
									<s:textfield name="noofLowerChars" id="noofLowerChars" cssClass="text" maxLength="2"></s:textfield>
								</td>
							</tr>
							<tr>
								<td class="textRight" width="50%">
									<s:label value="Password Expires in (days)"></s:label>:<span class="mandatory">*</span>
								</td>
								<td class="text" width="50%">
									<s:textfield name="passwordExpDays" id="passwordExpDays" cssClass="text" maxLength="4"></s:textfield>
								</td>
							</tr>
							<tr>
								<td class="textRight" width="50%">
									<s:label value="No of Password changes in a day"></s:label>:<span class="mandatory">*</span>
								</td>
								<td class="text" width="50%">
									<s:textfield name="maxNoofPassChangesDay" id="maxNoofPassChangesDay" cssClass="text" maxLength="2"></s:textfield>
								</td>
							</tr>
							
						</table>
					</s:div>
				</div>
				
			</div>
		</div>
	
		<!--  button panel starts -->
		<div class="clearfix"></div>
		<table width="100%" cellpadding="0" cellspacing="0" border="0">
			<tr>
			<td align="center">
			<s:if test="%{checkForSubmit!='Display_Approve_Reject'}">
				<s:submit value="Submit" action="submitPassSecPolicy" cssClass="btn" id='submitButtonId'/>
				<input type="button" id="ResetButtonId" value="Reset" onClick="javascript:callReset();" id="ResetId"/>
				<input type="button" id="maintainButtonId" value="Maintain" onClick="javascript:callMaintain();"/>
			</s:if>
			<s:if test="%{checkForSubmit=='Display_Approve_Reject'}">
				<s:if test="%{validUserToApprove}">
					<input type="button" value="Approve" id="approveBtn" onClick="javascript:callSubmit('Approve');" />
					<input type="button" value="Reject" id="RejectBtn" onClick="javascript:callSubmit('Reject');" />
				</s:if>
				<s:if test="%{!validUserToApprove}">
					<s:submit value="Submit" action="submitPassSecPolicy" cssClass="btn" id='submitButtonId'/>
					<input type="button" id="ResetButtonId" value="Reset" onClick="javascript:callReset();" id="ResetId"/>
					<input type="button" id="maintainButtonId" value="Maintain" onClick="javascript:callMaintain();"/>
				</s:if>
			</s:if>
			
			</td>
		</tr>
		</table>
		<!--  button panel ends -->
	</s:form>
</div>
</div>
</div>
<div id="contentPaneBottom"></div>
<script type="text/javascript">
	var CollapsiblePanel1 = new Spry.Widget.CollapsiblePanel(
			"CollapsiblePanel1");	
</script>
<script>
var check= document.getElementById("hiddenTxnRef").value;
if(check!=''){
	var aa = document.getElementById('passwordPolicyForm');
	var isValidUser = document.getElementById("validUserToApprove").value;
		if(isValidUser=="true")
		{
			for ( var i = 0; i < aa.length-5; i++) 
				{
					aa.elements[i].disabled = true;
				}
		}
		else
		{
			for ( var i = 0; i < aa.length-5; i++) 
			{
				aa.elements[i].enabled = true;
			}
		}
}	


window.onload = function() {
	var aa = document.getElementById('passwordPolicyForm');
	for ( var i = 0; i < aa.length-2; i++) 
	{
		aa.elements[i].disabled = true;
	}
};
	 

</script>
