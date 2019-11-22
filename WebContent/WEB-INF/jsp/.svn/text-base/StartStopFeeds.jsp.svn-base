<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<%@taglib uri="http://displaytag.sf.net" prefix="display"%>
<link href="SpryAssets/SpryCollapsiblePanel.css" rel="stylesheet"
	type="text/css" />
<script src="SpryAssets/SpryCollapsiblePanel.js" type="text/javascript"></script>
<script type="text/javascript">

function callCheckStatus(codeObject){
	var names = document.getElementById("nameList").value;	
	names = names.replace('[', ""); 
	names = names.replace(']', ""); 	
	var namesArr = new Array();
	namesArr = names.split(",");		
	var status = document.getElementById("statusList").value;	
	status = status.replace('[', ""); 
	status = status.replace(']', ""); 
	var statusArr = new Array();
	statusArr = status.split(",");	
	var l = codeObject.options.length; 	
	for ( var i = 0; i < l ; i++) {		
		if (codeObject.options[i].selected) {		
			document.getElementById("EIName").value = "";
			document.getElementById("Stop").disabled = false;
			document.getElementById("Start").disabled = false;
		}
		if (codeObject.options[i+1].selected) {				
			document.getElementById("EIName").value = namesArr[i];
			if(statusArr[i] == 2){
				document.getElementById("Start").disabled = true;
				document.getElementById("Stop").disabled = false;
			}else if(statusArr[i] == 0){
				document.getElementById("Stop").disabled = true;
				document.getElementById("Start").disabled = false;
			}else{
				document.getElementById("Stop").disabled = false;
				document.getElementById("Start").disabled = false;
			}
			break;
		}
	}
}

function callSubmit(saveAction)
{
	var aa = document.getElementById('form');
	for ( var i = 0; i < aa.length; i++) {
		aa.elements[i].disabled = false;
		}
	document.getElementById("saveAction").value = saveAction;
	document.forms[0].action="changeStatus";
	document.forms[0].submit();
}


</script>

<sx:head parseContent="true" />

<s:head />
<div id="contentPane">
<div id="contentPaneTop"></div>
<div id="contentPaneFluid">
<div id="contentPaneInternal"><span class="crumbs"><a
	href="#">System</a>&nbsp;>&nbsp;Start / Stop Feeds</span></div>
<div id="content">
<h1>Start / Stop Feeds</h1>
<s:form method="post" id="form">

	<div><s:if test="hasFieldErrors()">
				<div class="errorMsg"><span class="errorMsgInline"><s:actionerror /><s:fielderror /></span></div>
			</s:if></div><!--Error Msg Div Ends -->
				<div id="CollapsiblePanel1" class="CollapsiblePanel">
	<div class="CollapsiblePanelTab"><!--  <img src="images/openPanel.png" width="9" height="7" alt="openPanel" />--></div>
	<div class="CollapsiblePanelContent">
	<div><!-- Group One Div Starts --> 
	<s:div id="main"	cssClass="dataGrid" style="width:100%;">
	<s:hidden id="nameList" name="nameList"></s:hidden>
	<s:hidden id="statusList" name="statusList"></s:hidden>
	<s:hidden id="saveAction" name="saveAction"></s:hidden>
	<table width="100%" cellpadding="3" cellspacing="1" border="0"	bgcolor="#ffffff">
		<tr>
			
			<td width="20%" class="textRight"><s:label value="%{getText('label.ExternalSystem')}"></s:label>:<span class="mandatory">*</span></td>
			<td width="30%" class="text"><s:select list="#session.codeList" name="EICode" id="EICode"  headerKey="" key="EICode" value="" headerValue="Select EI code " onchange="callCheckStatus(this.form.EICode)" ></s:select></td>
			<td width="20%" class="textRight"><s:label	value="%{getText('label.Description')}"></s:label>:</td>
			<td width="30%" class="text"><s:textfield name="EIName" id="EIName" ></s:textfield>
				
		</tr>
		</table>
	</s:div>
	</div>
	</div>
	</div>
	<center>
	<div class="clearfix"></div>
	<table width="100%" cellpadding="0" cellspacing="0" border="0">
		<tr>
			<td>	
					<input type="button" id="Start" value="Start" onclick="callSubmit('Start')">
					<input type="button" id="Stop" value="Stop" onclick="callSubmit('Stop')">
			</td>
		</tr>
	</table></center>
	</s:form></div>
</div>
</div>