
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


function callSubmit(saveAction)
{
	var aa = document.getElementById('form123');
	for ( var i = 0; i < aa.length; i++) {
		aa.elements[i].disabled = false;
	}
	
	document.getElementById("saveAction").value = saveAction;
	document.forms["form123"].submit();
	
}

</script>
<script type="text/javascript">
//Screen comparison
function callSeeOldData(data)
{
	//alert(data)
	document.getElementById("old_NewScreen").value=data;
	var aa = document.getElementById('form123');
	for ( var i = 0; i < aa.length; i++) {
		aa.elements[i].disabled = false;
	}
	document.forms[0].action="callSeeOldData";
	document.forms[0].submit();
}
//<!-- Screen comparison End-->
function callValidate()
{
	
	var branchBIC = document.getElementById("branchBIC").value;
	var branchIFSC = document.getElementById("branchIFSC").value;

	
	if(branchBIC!=""){
		document.getElementById("branchBIC").disabled = false;
		document.getElementById("branchIFSC").disabled = true;
		}
	else if(branchIFSC!=""){
		document.getElementById("branchBIC").disabled = true;
		document.getElementById("branchIFSC").disabled = false;
		}
	else{
		document.getElementById("branchBIC").disabled = false;
		document.getElementById("branchIFSC").disabled = false;
		
		}
	 
}

function callSearchBranchCode(Action) {

	window.open(
			"<s:url action='showBranchScreen' windowState='EXCLUSIVE' />",
			'mywindow', 'top=50,left=250,width=750,height=410');

}
function callDisplay()
{
	
	document.forms["form123"].action="callDisplay";
	document.forms["form123"].submit();
}
function callDisableOnDelete(form)
{
	var radioButtons = document.getElementsByName("actionPerform")	;
	//alert("1"+radioButtons[1].checked);
	//alert("2"+radioButtons[2].checked);
	if(radioButtons[0].checked==true)
		{
			document.getElementById("searchBtn").disabled = true;
		}
	else if(radioButtons[1].checked==true){
		document.getElementById("searchBtn").disabled = false;
		}
	else if(radioButtons[2].checked==true){
		document.getElementById("searchBtn").disabled = false;

		
		}
}
	
</script>
<sx:head parseContent="true" />

<s:head />
<div id="contentPane">
<div id="contentPaneTop"></div>
<div id="contentPaneFluid">
<div id="contentPaneInternal"><span class="crumbs"><a href="#">Maintance</a>&nbsp;>&nbsp;Maintain Branches</span></div>
<div id="content">
<h1>Maintain Branches</h1>
<s:form method="post" id="form123" action="submitBranchMaintainance">
<!-- Screen comparison -->
<s:hidden name="addressRef" id="addressRef"></s:hidden>
<s:hidden name="old_NewScreen" id="old_NewScreen"></s:hidden>
<!-- Screen comparison End-->
	<!--Error Msg Div Starts -->
	<div><s:if test="hasFieldErrors()">
				<div class="errorMsg"><span class="errorMsgInline"><s:actionerror /><s:fielderror /></span></div>
			</s:if></div><!--Error Msg Div Ends -->
	<!-- Collapsible Panels Start-->
	<div id="CollapsiblePanel1" class="CollapsiblePanel">
	<div class="CollapsiblePanelTab"><!--  <img src="images/openPanel.png" width="9" height="7" alt="openPanel" />-->&nbsp;Basic</div>
	<div class="CollapsiblePanelContent">
	<div><!-- Group One Div Starts --> 
	<s:div id="main"	cssClass="dataGrid" style="width:100%;">
	<table width="100%" cellpadding="3" cellspacing="1" border="0"	bgcolor="#ffffff">
			<tr>
				<td class="textRight"><s:label	value="%{getText('label.ACTION')}"></s:label>:</td>
				<td colspan="3" class="text">
				
				<s:radio list="{'ADD','MODIFY','DELETE'}"
												id="actionPerform" cssClass="dropDown"
												
												name="actionPerform" value="actionPerform" onclick="callDisableOnDelete(this.form)"></s:radio>
				</td>
			</tr>
	
	<tr>
				<td width="20%" class="textRight"><s:label
					value="%{getText('label.BRANCHCODE')}"></s:label>:<span
					class="mandatory">*</span></td>
				<td width="30%" class="text"><s:textfield name="branchCode" id="branchCode" maxLength="15"></s:textfield>
				<input type="button" value="Search Branch" id="searchBtn" onclick="callSearchBranchCode()" disabled="disabled">
				</td>
				<td width="20%" class="textRight"><s:label
					value="%{getText('label.BRANCHNAME')}"></s:label>:<span
					class="mandatory">*</span></td>
				<td width="30%" class="text"><s:textfield name="branchName"
					id="branchName" maxLength="50"></s:textfield></td>
					</tr>
				<tr>
				<td width="20%" class="textRight"><s:label
					value="%{getText('label.BRANCHBIC')}"></s:label>:</td>
				<td width="30%" class="text"><s:textfield name="branchBIC" id="branchBIC" maxLength="15" onkeyup="callValidate()"></s:textfield></td>
				<td width="20%" class="textRight"><s:label
					value="%{getText('label.BRANCHIFSC')}"></s:label>:</td>
				<td width="30%" class="text"><s:textfield name="branchIFSC"
					id="branchIFSC" maxLength="11" onkeyup="callValidate()"></s:textfield></td>
					</tr>
		</table>
	</s:div> <!-- Group One Div Ends --></div>
	</div>
	</div>
	<br />
	<div id="CollapsiblePanel2" class="CollapsiblePanel">
	<div class="CollapsiblePanelTab"><!--  <img src="images/openPanel.png" width="9" height="7" alt="openPanel" />-->&nbsp;Address Details</div>
	<div class="CollapsiblePanelContent">
	<div><!-- Group Two Div Starts --> <s:div id="mandatory"
		cssClass="dataGrid" style="width:100%;">
		<table width="100%" cellpadding="5" cellspacing="1" border="0"
			bgcolor="#ffffff">
			<tr>
				<td class="textRight"><s:label
					value="%{getText('label.ADDRESSTYPE')}"></s:label>:</td>
				<td colspan="3" class="text"><s:select list="#{'ADDR':'ADDR','BIZZ':'BIZZ','DLVY':'DLVY','HOME':'HOME','MLTO':'MLTO','PBOX':'PBOX'}" name="addressType" id="addressType" headerKey="" headerValue="Select Address"></s:select> </td>
				
			</tr>
			<tr>
				<td width="20%" class="textRight"><s:label	value="%{getText('label.DEPARTMENT')}"></s:label>:</td>
				<td width="30%" class="text"><s:textfield name="department" id="department" maxLength="70"></s:textfield> </td>
				<td width="20%" class="textRight"><s:label value="%{getText('label.SUBDEPARTMENT')}"></s:label>:</td>
				<td width="30%" class="text"><s:textfield name="subDepartment" id="subDepartment" maxLength="70"></s:textfield></td>
				
			</tr>
			<tr>
				<td width="20%" class="textRight"><s:label value="%{getText('label.STREETNAME')}"></s:label>:</td>
				<td width="30%" class="text"><s:textfield name="streetName" id="streetName" ></s:textfield></td>
				<td width="20%" class="textRight"><s:label	value="%{getText('label.BUILDINGNUMBER')}"></s:label>:</td>
				<td width="30%" class="text"><s:textfield name="buildingNumber" id="buildingNumber" maxLength="16"></s:textfield> </td>
			</tr>
			<tr>
			<td width="20%" class="textRight"><s:label	value="%{getText('label.POSTCODE')}"></s:label>:</td>
				<td width="30%" class="text"><s:textfield name="postCode" id="postCode" maxLength="16"></s:textfield> </td>
				<td width="20%" class="textRight"><s:label value="%{getText('label.TOWNNAME')}"></s:label>:</td>
				<td width="30%" class="text"><s:textfield name="townName" id="townName" maxLength="35"></s:textfield></td>
				
			</tr>
			<tr>
			<td width="20%" class="textRight"><s:label	value="%{getText('label.COUNTRYSUBDIVISION')}"></s:label>:</td>
				<td width="30%" class="text"><s:textfield name="countrySubDivision" id="countrySubDivision" maxLength="35"></s:textfield> </td>
				<td width="20%" class="textRight"><s:label value="%{getText('label.COUNTRYCODE')}"></s:label>:</td>
				<td width="30%" class="text"><s:select list="#{'IN':'India','US':'United States of America'}" name="countryCode" id="countryCode" headerKey="" headerValue="Select Country"></s:select></td>
				
			</tr>
			<tr>
				<td class="textRight"><s:label	value="%{getText('label.ADDRESSLINE')}"></s:label>:</td>
				<td colspan="3" class="text"><s:textfield name="addressLine" id="addressLine" maxLength="70"></s:textfield> </td>
			</tr>
		</table>
	</s:div> <!-- Group Two Div Ends --></div>
	</div>
	</div>
	<br />
	<table width="100%" cellpadding="0" cellspacing="0" border="0">
		<tr>
			<td><s:if test="%{checkForSubmit!='Display_Approve_Reject'}">
				<s:submit value="Submit"  action="getMaintainBranchApproval"
					cssClass="btn" />
				<input type="reset" value="Cancel" onclick="cancel()" class="btn" />
			</s:if> <s:if test="%{checkForSubmit=='Display_Approve_Reject'}">
				<s:if test="%{validUserToApprove}">
				
					<input type="button" value="Approve"
						onclick="callSubmit('Approve')" id="approveBtn">
					<input type="button" value="Reject" onclick="callSubmit('Reject')" id="rejectBtn">
					<!-- Screen comparison -->
					<s:if test="%{flagForNewData != 'flagForNewData'}">
					<input type="button" value="Old Data" id="oldBtn"
						onclick="callSeeOldData('OLD')" >
						</s:if>
						<s:if test="%{flagForNewData == 'flagForNewData'}">
						<input type="button" value="New Data"
						onclick="callSeeOldData('NEW')" id="newBTN">
						</s:if>
						<!-- Screen comparison end -->
					<input type="button" value="Cancel" onclick="cancel()">
				</s:if>
				<s:if test="%{!validUserToApprove}">
					<input type="button" value="Approve"
						onclick="callSubmit('Approve')" disabled="disabled">
					<input type="button" value="Reject" onclick="callSubmit('Reject')"
						disabled="disabled">
						<!-- Screen comparison -->
					<s:if test="%{flagForNewData != 'flagForNewData'}">
					<input type="button" value="Old Data"
						onclick="callSeeOldData('OLD')" disabled="disabled">
						</s:if>
						<s:if test="%{flagForNewData == 'flagForNewData'}">
						<input type="button" value="New Data"
						onclick="callSeeOldData('NEW')" disabled="disabled">
						</s:if>
						<!-- Screen comparison End-->
					<input type="button" value="Cancel" onclick="cancel()"
						disabled="disabled">
				</s:if>
			</s:if></td>
		</tr>
	</table>
	<s:hidden id="saveAction" name="saveAction"></s:hidden>
	<s:hidden id="hiddenTxnRef" name="hiddenTxnRef"></s:hidden>

	
	</s:form></div>
</div>
</div>
<div id="contentPaneBottom"></div>
<script type="text/javascript">
	var CollapsiblePanel1 = new Spry.Widget.CollapsiblePanel(
			"CollapsiblePanel1");
	var CollapsiblePanel2 = new Spry.Widget.CollapsiblePanel(
			"CollapsiblePanel2");
	var CollapsiblePanel3 = new Spry.Widget.CollapsiblePanel(
			"CollapsiblePanel3");
	var CollapsiblePanel4 = new Spry.Widget.CollapsiblePanel(
			"CollapsiblePanel4");
</script>
<script type="text/javascript">
//Screen comparison
var radioButtons = document.getElementsByName("actionPerform")	;
//alert("1"+radioButtons[1].checked);
//alert("2"+radioButtons[2].checked);
if(radioButtons[1].checked!=true)
	{
	 document.getElementById("oldBtn").disabled=true;
	}

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
	//-----End Screen comparison-----
var check= document.getElementById("hiddenTxnRef").value;
//alert(check)
if(check!=''){
	var aa = document.getElementById('form123');
	for ( var i = 0; i < aa.length-6; i++) {
		//alert(aa.elements[i].value)
		
		aa.elements[i].disabled = true;
	}
}

</script>


