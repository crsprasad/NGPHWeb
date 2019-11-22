<%@page import="com.opensymphony.xwork2.ActionContext"%>
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<%@taglib uri="http://displaytag.sf.net" prefix="display"%>
<sx:head parseContent="true" />
<s:head theme="simple" />
<script type="text/javascript">
function checkUserActionOnSubmit(){
	

	if(document.getElementById("bankCode").value!=""){
	document.getElementById("saveAction").value = "Modify";
	if(document.forms[0].actionPerform[1].checked==true || document.forms[0].actionPerform[2].checked== true){
		
		document.forms[0].submit();
		}

	}
	
	else{
		if(document.forms[0].actionPerform[0].checked==true){
		}else{
		alert("Please Select Bank Code");
		document.forms[0].actionPerform[0].checked = true;
		}
	}

	


}
function callSubmit(saveAction) {
	
	
	var aa = document.getElementById('form');
	for ( var i = 0; i < aa.length; i++) {
		aa.elements[i].disabled = false;
	}
	document.getElementById("saveAction").value = saveAction;

	document.forms[0].submit();
}
function callSeeOldData(data)
{
	//alert(data);
	document.getElementById("old_NewScreen").value=data;
	var aa = document.getElementById('form');
	for ( var i = 0; i < aa.elements.length-3 ; i++) {
		
		aa.elements[i].disabled = false;
	}	
	document.forms[0].action="callSeeOldDataMemberTransactionLimit";
	document.forms[0].submit();
}

</script>

<div id="contentPane">
<div id="contentPaneTop"></div>
<div id="contentPaneFluid">
<div id="contentPaneInternal">
	<span class="crumbs">
		<a href="#">Maintenance</a>&nbsp;>&nbsp;Member Transaction Limits
	</span>
</div>
<div id="content">
	<h1>Member Transaction Limits</h1>
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
	<s:form method="post" id="form" action = "doFinaLOperationForTransactionLimits">
	<s:hidden name="old_NewScreen" id="old_NewScreen"></s:hidden>
	<s:hidden name="txnRef" id="txnRef"></s:hidden>
	<!-- Collapsible Panels Start-->
		<div id="CollapsiblePanel1" class="CollapsiblePanel">
			<div class="CollapsiblePanelTab">&nbsp;User Details</div>
			<div class="CollapsiblePanelContent">
				<div>
					<!-- Group One Div Starts --> 
					<s:div id="main" cssClass="dataGrid" style="width:100%;"> 
						<table width="100%" cellpadding="3" cellspacing="1" border="0" bgcolor="#ffffff">
						<tr>
								<td width="25%" class="textRight">
									<s:label value="Operation"></s:label> 
								</td>
								<td colspan="3" class="text">
									<s:radio id="actionPerform" name="actionPerform" list="{'ADD','MODIFY', 'DELETE'}"  value="actionPerform" onclick="checkUserActionOnSubmit()"/>
								</td>
							</tr>
							<tr>
								<td width="20%" class="textRight"><s:label	value="%{getText('label.BankCode')}"></s:label>:</td>
								<td width="30%" class="text"><s:select list="#session.bankCodeDropDown" name="bankCode" id="bankCode" headerKey="" headerValue="Select Bank Code" ></s:select></td>
								<td width="20%" class="textRight"><s:label value="%{getText('label.PaymentChannel')}"></s:label>:</td>
								<td width="30%" class="text"><s:select list="#session.channelDropDown" name="channel" id="channel" headerKey="" headerValue="Select Channel" ></s:select> </td>
							</tr>
						
							<tr>
								<td width="20%" class="textRight"><s:label	value="%{getText('label.DebitLimit')}"></s:label>:</td>
								<td width="30%" class="text"><s:textfield name="debitLimit" id="debitLimit" maxLength="16"></s:textfield></td>
								<td width="20%" class="textRight"><s:label value="%{getText('label.creditLimit')}"></s:label>:</td>
								<td width="30%" class="text"><s:textfield name = "creditLimit"  id="creditLimit" maxLength="16"></s:textfield> </td>
							</tr>
			
						</table>
					</s:div>
				</div>
			</div>
			</div>
			
				
				<table width="100%" cellpadding="0" cellspacing="0" border="0">
			<tr>
			<td>
			<s:if test="%{checkForSubmit!='Display_Approve_Reject'}">
				<s:submit action="callsendToAuthorize" value="Submit" ></s:submit>
			</s:if>
			<s:if test="%{checkForSubmit=='Display_Approve_Reject'}">
				<s:if test="%{validUserToApprove}">
					<input type="button" value="Approve" onclick="callSubmit('Approve')" id="approveBtn">
					<input type="button" value="Reject" onclick="callSubmit('Reject')" id="rejectBtn">
					<s:if test="%{flagForNewData != 'flagForNewData'}">
					<input type="button" id="oldBtn" value="Old Data" onclick="callSeeOldData('OLD')" >
						</s:if>
						<s:if test="%{flagForNewData == 'flagForNewData'}">
						<input type="button" value="New Data" onclick="callSeeOldData('NEW')" >
						</s:if>
				</s:if>
				<s:if test="%{!validUserToApprove}">
					<input type="button" value="Approve" onclick="callAction('Approve')" disabled="disabled">
					<input type="button" value="Reject" onclick="callAction('Reject')" disabled="disabled">
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
		</table><s:hidden id="saveAction" name="saveAction"></s:hidden>
	<s:hidden id="hiddenTxnRef" name="hiddenTxnRef"></s:hidden>
	<s:hidden name="hiddenRadio" id="hiddenRadio"></s:hidden>
		<!--  button panel ends -->
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

	var radioButtons = document.getElementsByName("actionPerform")	;
	//alert("1"+radioButtons[1].checked);
	//alert("2"+radioButtons[2].checked);
	if(radioButtons[1].checked!=true)
		{
		 document.getElementById("oldBtn").disabled=true;
		}
		
var check= document.getElementById("hiddenTxnRef").value;
if(check!=''){
	var aa = document.getElementById('form');
	
	for ( var i = 0; i < aa.length - 6; i++) {
		//alert(aa.elements[i].value)
		if (aa.elements[i].value != 'Approve'
				|| aa.elements[i].value != 'Reject')
			aa.elements[i].disabled = true;
	}
}
</script>
</div>
</div>
</div>
<div id="contentPaneBottom"></div>
