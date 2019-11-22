<%@page import="com.opensymphony.xwork2.ActionContext"%>
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<%@taglib uri="http://displaytag.sf.net" prefix="display"%>
<link href="SpryAssets/SpryCollapsiblePanel.css" rel="stylesheet"
	type="text/css" />
<script src="SpryAssets/SpryCollapsiblePanel.js" type="text/javascript"></script>
<script type="text/javascript">
function callSubmit(saveAction){
	var aa = document.getElementById('form');
	for ( var i = 0; i < aa.length; i++) {
		aa.elements[i].disabled = false;
	}
	
	document.getElementById("saveAction").value = saveAction;
	document.forms[0].action="approveReleaseBGAction";
	document.forms[0].submit();
}
function onSubmit(){
	
	if(document.getElementById("bgNumber").value==null||document.getElementById("bgNumber").value==""){
		alert("BG NUMBER cannot be empty.");
		return false;
		}
	if(document.getElementById("releaseReqRef").value==null||document.getElementById("releaseReqRef").value==""){
		alert("RELEASE REQUEST REFERENCE cannot be empty");
		return false;
		}
	if(document.getElementById("bgAmount").value==null||document.getElementById("bgAmount").value==""){
		alert("BG AMOUNT cannot be empty");
		return false;
		}
	if(document.form.dateOfRelease.value==null||document.form.dateOfRelease.value==""){		
		alert("DATE OF RELEASE cannot be empty");
		return false;
		}	
	document.forms[0].action="submitBGAction";
	document.forms[0].submit();
}
function fetchData(){
	document.forms[0].action="fetchBGAction";
	document.forms[0].submit();
}
function callSearchIFSC(Action) {

	document.getElementById("searchAction").value = Action;
	window.open(
			"<s:url action='showIFSCScreen' windowState='EXCLUSIVE' />",
			'mywindow', 'top=50,left=250,width=750,height=410');
}
function maxLength(field,maxChars){
      if(field.value.length >= maxChars) {
         event.returnValue=false;
         return false;
      }     
}
function maxLengthPaste(field,maxChars){
      event.returnValue=false;
      if((field.value.length +  window.clipboardData.getData("Text").length) > maxChars) {
        return false;
      }
      event.returnValue=true;
}
</script>
<sx:head parseContent="true" />

<s:head />

<div id="contentPane">
<div id="contentPaneTop"></div>
<div id="contentPaneFluid">
<div id="contentPaneInternal"><span class="crumbs"><a
	href="#">Outbound Payments</a>&nbsp;>&nbsp;Release Bank Guarantee</span></div>
	
<div id="content">

<h1>Release Bank Guarantee</h1>
<s:form id="form" method="post">
<s:hidden id="searchAction" name="searchAction"></s:hidden>

	<!--Error Msg Div Starts -->
	<div><s:if test="hasFieldErrors()">
				<div class="errorMsg"><span class="errorMsgInline"><s:actionerror /><s:fielderror /></span></div>
			</s:if></div><!--Error Msg Div Ends -->
	<div><s:if test="hasActionMessages()">
				<div class="errorMsg"><span class="errorMsgInline"><s:actionmessage /></span></div>
	</s:if></div>
			
			
	<div id="CollapsiblePanel1" class="CollapsiblePanel">
	<div class="CollapsiblePanelTab"><!--  <img src="images/openPanel.png" width="9" height="7" alt="openPanel" />-->&nbsp;Release Bank Guarantee</div>
	<div class="CollapsiblePanelContent">
	<div><!-- Group One Div Starts --> 
	<s:div id="main"	cssClass="dataGrid" style="width:100%;">
	<table width="100%" cellpadding="3" cellspacing="1" border="0"	bgcolor="#ffffff">
		<tr>
			<td width="20%" class="textRight"><s:label	value="%{getText('label.bgNumber')}"></s:label>:<span class="mandatory">*</span></td>
			<td width="30%" class="text"><s:textfield name="bgNumber" id="bgNumber" maxLength="16"></s:textfield><input type="button" value="Fetch" onclick=" return fetchData()" id="btnSearch"
					class="btn"> </td>
			<td width="20%" class="textRight"><s:label value="%{getText('label.releaseRequestReference')}"></s:label>:<span	class="mandatory">*</span></td>
			<td width="30%" class="text"><s:textfield name="releaseReqRef" id="releaseReqRef"/></td>
		</tr>
		
		<tr>
			<td width="20%" class="textRight"><s:label	value="%{getText('label.AccountNo')}"></s:label>:</td>
			<td width="30%" class="text"><s:textfield name="accountNumber" id="accountNumber" maxLength="25"></s:textfield></td>
			<td width="20%" class="textRight"><s:label value="%{getText('label.dateOfRelease')}"></s:label>:<span class="mandatory">*</span></td>
			<td width="30%" class="text"><sx:datetimepicker name="dateOfRelease" value="%{'today'}" cssClass="txtField" id="dateOfRelease"  displayFormat="MM/dd/yyyy" type="date"  /></td>
		</tr>
		
		<tr>
			<td width="20%" class="textRight"><s:label	value="%{getText('label.chargeAmount')}"></s:label>:</td>
			<td width="30%" class="text"><s:textfield name="chargeAmount" id="chargeAmount" maxLength="19"></s:textfield></td>			
		</tr>
		
		<tr>
			<td width="20%" class="textRight"><s:label	value="%{getText('label.bgAmount')}"></s:label>:<span	class="mandatory">*</span></td>
			<td width="30%" class="text"><s:textfield name="bgAmount" id="bgAmount" maxLength="19"></s:textfield></td>
			<td width="20%" class="textRight"><s:label value="%{getText('label.bgAmountDescription')}"></s:label>:</td>
			<td width="30%" class="text"><s:textarea rows="4" cols="35" name="bgAmountDesc" id="bgAmountDesc" onKeyPress='return maxLength(this,"140");' onpaste='return maxLengthPaste(this,"140");'/></td>
		</tr>
		
		<tr>
			<td width="20%" class="textRight"><s:label	value="%{getText('label.accountWithPartyIdentifier1')}"></s:label>:</td>
			<td width="30%" class="text"><s:select list="{'C','D'}" name="acctWithPartyIdentifier1" id="acctWithPartyIdentifier1" headerKey="" headerValue="Select" ></s:select></td>
			<td width="20%" class="textRight"><s:label	value="%{getText('label.accountWithPartyIdentifier2')}"></s:label>:</td>
			<td width="30%" class="text"><s:textfield name="acctWithPartyIdentifier2" id="acctWithPartyIdentifier2" maxLength="34"></s:textfield></td>
		</tr>
		
		<tr>			
			<td width="20%" class="textRight"><s:label value="%{getText('label.accountWithPartyIFSC')}"></s:label>:</td>
			<td width="30%" class="text"><s:textfield name="acctWithPartyIFSC" id="acctWithPartyIFSC" /><input type="button"
					value="Search IFSC"
					onclick="return callSearchIFSC('ACCOUNTWITHPARTYIFSC')" id="btnSearch"
					class="btn" /></td>
		</tr>
		
		<tr>
			<td width="20%" class="textRight"><s:label	value="%{getText('label.accountWithPartyLocation')}"></s:label>:</td>
			<td width="30%" class="text"><s:textfield name="acctWithPartyLoc" id="acctWithPartyLoc" maxLength="35"></s:textfield></td>
			<td width="20%" class="textRight"><s:label value="%{getText('label.accountWithNameAndAddress')}"></s:label>:</td>
			<td width="30%" class="text"><s:textarea rows="4" cols="35" name="acctWithNameAndAddr" id="acctWithNameAndAddr" onKeyPress='return maxLength(this,"140");' onpaste='return maxLengthPaste(this,"140");'/></td>
		</tr>
		
		<tr>
			<td width="20%" class="textRight"><s:label	value="%{getText('label.chargesDetails')}"></s:label>:</td>
			<td width="30%" class="text"><s:textarea rows="6" cols="35" name="chargesDetails" id="chargesDetails" onKeyPress='return maxLength(this,"210");' onpaste='return maxLengthPaste(this,"210");'></s:textarea></td>
			<td width="20%" class="textRight"><s:label	value="%{getText('label.senderToReceiverInformation')}"></s:label>:</td>
			<td width="30%" class="text"><s:textarea rows="6" cols="35" name="senderToReceiverInformation" id="senderToReceiverInformation" onKeyPress='return maxLength(this,"210");' onpaste='return maxLengthPaste(this,"210");'></s:textarea></td>
		</tr>
	</table>
	</s:div>
	</div>
	</div>
	
	<!--  button panel starts -->
	<div class="clearfix"></div>
	<table width="100%" cellpadding="0" cellspacing="0" border="0">
		<tr>
			<td>
			<s:if test="%{checkForSubmit!='Display_Approve_Reject'}">
					<input type="button" value="Submit" onclick="onSubmit()" class="btn"/>
					<input type="reset" value="Cancel" onclick="cancel()" class="btn"  />
			</s:if>
			<s:if test="%{checkForSubmit=='Display_Approve_Reject'}">
				<s:if test="%{validUserToApprove}">
					<input type="button" value="Approve" onclick="callSubmit('Approve')">
					<input type="button" value="Reject" onclick="callSubmit('Reject')">
					<input type="button" value="Cancel" onclick="cancel()">
				</s:if>
				<s:if test="%{!validUserToApprove}">
					<input type="button" value="Approve" onclick="callSubmit('Approve')" disabled="disabled">
					<input type="button" value="Reject" onclick="callSubmit('Reject')" disabled="disabled">
					<input type="button" value="Cancel" onclick="cancel()" disabled="disabled">
				</s:if>
			</s:if>
					
				
			</td>
		</tr>
	</table>
	<s:hidden id="saveAction" name="saveAction"></s:hidden>
	<s:hidden id="hiddenTxnRef" name="hiddenTxnRef"></s:hidden>
	
	<!--  button panel ends -->
	
	</div>
</s:form>	
</div>

</div>
</div>

<div id="contentPaneBottom"></div>
<script type="text/javascript">
	var CollapsiblePanel1 = new Spry.Widget.CollapsiblePanel(
			"CollapsiblePanel1");
	
</script>
<script type="text/javascript">
var check= document.getElementById("hiddenTxnRef").value;
if(check!=''){
	var aa = document.getElementById('form');
	for ( var i = 0; i < aa.length-5; i++) {
		//alert(aa.elements[i].value)
		if(aa.elements[i].value!='Approve' || aa.elements[i].value!='Reject')
		aa.elements[i].disabled = true;
	}
}

</script>