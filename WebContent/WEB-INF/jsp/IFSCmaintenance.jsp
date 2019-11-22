
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

function changeToUpperCase()
{
	str = document.getElementById("ifscCode").value;
	document.getElementById("ifscCode").value = str.toUpperCase();	
}


function callSubmit(saveAction)
{
	var aa = document.getElementById('ifscData');
	for ( var i = 0; i < aa.length; i++) {
		aa.elements[i].disabled = false;
	}
	document.getElementById("saveAction").value = saveAction;
	document.forms["ifscData"].action= "getIFSCApproval";
	document.forms["ifscData"].submit();
	
}

</script>
<script type="text/javascript">






function callDisableOnDelete(form)
{
	var radioButtons = document.getElementsByName("actionPerform")	;
	if(radioButtons[0].checked==true)
		{
			document.getElementById("btnSearch").disabled = true;
		}
	else if(radioButtons[1].checked==true)
		{
			document.getElementById("btnSearch").disabled = false;
			alert("After Enable Delete button");
		}
	
}

function callFetchIFSCCodes()
{

		if(ifscCode=="" || ifscCode!=""){
			window.open(
				"<s:url action='showIFSCData' windowState='EXCLUSIVE' />",
					'mywindow', 'top=50,left=250,width=750,height=410');
					
			
	}
	else{
			
			callShowGrid();
		}
	}


	function callShowGrid()
	{
		
		document.forms[0].action="displayIFSCData";
		document.forms[0].submit();
	}

	function cancel()
	{
		document.forms[0].action="resetIFSCMaintain";
		document.forms[0].submit();
	}


</script>
<sx:head parseContent="true" />

<s:head />
<div id="contentPane">
<div id="contentPaneTop"></div>
<div id="contentPaneFluid">
<div id="contentPaneInternal"><span class="crumbs"><a href="#">Maintance</a>&nbsp;>&nbsp;Maintain IFSC</span></div>
<div id="content">
<h1>Maintain IFSC</h1>

<s:form method="post" id="ifscData" >
<!-- Screen comparison -->
<s:hidden name="addressRef" id="addressRef"></s:hidden>
<s:hidden id="validUserToApprove" name="validUserToApprove"></s:hidden>
<s:hidden id="searchAction" name="searchAction"></s:hidden>	
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
				<td colspan="3" class="text"><s:radio list="{'ADD','DELETE'}" id="actionPerform" cssClass="dropDown" name="actionPerform" value="actionPerform" onclick="callDisableOnDelete(form)"></s:radio></td>
			</tr>
			<tr>
				<td width="20%" class="textRight"><s:label	value="%{getText('label.IFSCCode')}"></s:label>:<span class="mandatory">*</span></td>
				<td class="text" width="30%"><s:textfield name="ifscCode" id="ifscCode" onKeyPress='return notAllowCheck(this,event);' onblur="changeToUpperCase()"></s:textfield><input type="button" value="Fetch" id ="btnSearch" onclick="callFetchIFSCCodes()"> </td>
				<td width="20%" class="textRight"><s:label value="%{getText('label.bankName')}"></s:label><span class="mandatory">*</span>:</td>
				<td width="30%" class="text"><s:textfield name="partyName" id="partyName" maxLength="200" ></s:textfield></td>
			</tr>
			<tr>
				<td width="20%" class="textRight"><s:label value="%{getText('label.partyName')}"></s:label><span class="mandatory">*</span>:</td>
				<td width="30%" class="text"><s:textfield name="branchName"	id="branchName" maxLength="200"></s:textfield>
				<td width="20%" class="textRight"><s:label value="%{getText('label.branchaddress')}"></s:label><span class="mandatory">*</span>:</td>
				<td width="30%" class="text"><s:textarea name="partyAddress" id="partyAddress" maxLength="400" ></s:textarea></td>
			</tr>
			<tr>
				<td class="textRight" width="20%"><s:label value="%{getText('label.stateName')}"></s:label><span class="mandatory">*</span>:</td>
				<td class="text" width="30%"><s:textfield name="stateName" id="stateName" maxLength="200" required="true"></s:textfield></td>
				<td class="textRight" width="20%"><s:label value="%{getText('label.districtName')}"></s:label><span class="mandatory">*</span>:</td>
				<td class="text" width="30%"><s:textfield name="districName" id="districName" maxLength="200" ></s:textfield></td>
			</tr>
			<tr>
				<td class="textRight" width="20%"><s:label value="%{getText('label.cityName')}"></s:label><span class="mandatory">*</span>:</td>
				<td class="text" width="30%"><s:textfield name="cityName" id="cityName" maxLength="200" ></s:textfield></td>
				<td class="textRight" width="20%"><s:label value="%{getText('label.IFSCType')}"></s:label></td>
				<td class="text" width="30%"><s:textfield name="ifscType" id="ifscType" maxLength="200"></s:textfield></td>
			</tr>	
			<tr>
				<td class="textRight" width="20%"><s:label value="%{getText('label.MICRCode')}" maxLength="20"></s:label></td>
				<td class="text" width="30%"><s:textfield name="micrCode" id="micrCode" ></s:textfield></td>
				<td class="textRight" width="20%"></td>
				<td class="text" width="30%"></td>
			</tr>
			
		</table>
	</s:div> <!-- Group One Div Ends --></div>
	</div>
	</div>
	<br />
	<table width="100%" cellpadding="0" cellspacing="0" border="0">
		<tr>
			<td align="center"><s:if test="%{checkForSubmit!='Display_Approve_Reject'}">
				<s:submit value="Submit"  action="ifscSendForApproval" cssClass="btn" />
				<input type="reset" value="Reset" onclick="cancel()" class="btn" />
				
			</s:if> 
			<s:if test="%{checkForSubmit=='Display_Approve_Reject'}">
				<s:if test="%{validUserToApprove}">
				
					<input type="button" value="Approve" onclick="callSubmit('Approve')" id="approveBtn">
					<input type="button" value="Reject" onclick="callSubmit('Reject')" id="rejectBtn">
			        
					<input type="reset" value="Cancel" onclick="cancel()" class="btn" />
					
					
				</s:if>
				<s:if test="%{!validUserToApprove}">
					<s:submit value="Submit"  action="ifscSendForApproval"
					cssClass="btn" />
				<input type="reset" value="Reset" onclick="cancel()" class="btn" />
				
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
var check= document.getElementById("hiddenTxnRef").value;
if(check!=''){
	var aa = document.getElementById('ifscData');
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

</script>


