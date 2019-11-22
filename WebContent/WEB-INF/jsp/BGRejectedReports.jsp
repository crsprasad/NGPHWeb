
<%@page import="com.opensymphony.xwork2.ActionContext"%>
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<link href="SpryAssets/SpryCollapsiblePanel.css" rel="stylesheet"
	type="text/css" />
<script src="SpryAssets/SpryCollapsiblePanel.js" type="text/javascript"></script>

<script type="text/javascript">

function deleteOption(object, index) {
	object.options[index] = null;
}

function addOption(object, text, value) {
	var defaultSelected = true;
	var selected = true;
	var optionName = new Option(text, value, defaultSelected, selected)
	object.options[object.length] = optionName;
}

function copySelected(fromObject, toObject) {

	for ( var i = 0, l = fromObject.options.length; i < l; i++) {
		if (fromObject.options[i].selected)
			addOption(toObject, fromObject.options[i].text,
					fromObject.options[i].value);
	}
	for ( var i = fromObject.options.length - 1; i > -1; i--) {
		if (fromObject.options[i].selected)
			deleteOption(fromObject, i);
	}
}

function selectAll(object) {
	for ( var i = 0, len = object.options.length; i < len; i++) {
		object[i].selected = "1"
	}
}
function listbox_move(listID, direction) {
	var listbox = document.getElementById(listID);
	var selIndex = listbox.selectedIndex;
	if (-1 == selIndex) {
		alert("Please select an option to move.");
		return;
	}
	var increment = -1;
	if (direction == 'up')
		increment = -1;
	else
		increment = 1;
	if ((selIndex + increment) < 0
			|| (selIndex + increment) > (listbox.options.length - 1)) {
		return;
	}
	var selValue = listbox.options[selIndex].value;
	var selText = listbox.options[selIndex].text;
	listbox.options[selIndex].value = listbox.options[selIndex + increment].value
	listbox.options[selIndex].text = listbox.options[selIndex + increment].text
	listbox.options[selIndex + increment].value = selValue;
	listbox.options[selIndex + increment].text = selText;
	listbox.selectedIndex = selIndex + increment;
}
</script>

<sx:head parseContent="true" />
<s:head />
<div id="contentPane">
<div id="contentPaneTop"></div>
<div id="contentPaneFluid">
<div id="contentPaneInternal"><span class="crumbs"><a
	href="#">Enquiry</a>&nbsp;>&nbsp;Bg Rejected Reports</span></div>
<div id="content">
<h1>Bg Rejected Reports</h1>
<s:hidden id="repairComments" name="repairComments"></s:hidden> <s:form method="post">
<!--Error Msg Div Starts -->
	<div><s:if test="hasFieldErrors()">
				<div class="errorMsg"><span class="errorMsgInline"><s:actionerror /><s:fielderror /></span></div>
			</s:if></div><!--Error Msg Div Ends -->
	<!-- Collapsible Panels Start-->
	<!--<div id="CollapsiblePanel1" class="CollapsiblePanel">
	<div class="CollapsiblePanelTab">  <img src="images/openPanel.png" width="9" height="7" alt="openPanel" />&nbsp;Group By</div>
	<div class="CollapsiblePanelContent">
	<div> Group One Div Starts  
	<s:div id="main"	cssClass="dataGrid" style="width:100%;">
	<table width="100%" cellpadding="3" cellspacing="1" border="0"	bgcolor="#ffffff">
	<tr>
				<td  align="center"><s:radio list="{'Advising Bank','N/A'}" name="groupBy" id="groupBy"></s:radio></td>
				<td align="center"><s:label value="Status"></s:label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<s:checkbox name="groupByStatus" id="groupByStatus"></s:checkbox> </td>
					</tr>
		</table>
	</s:div>  Group One Div Ends </div>
	</div>
	</div>
	<br />
	--><div id="CollapsiblePanel2" class="CollapsiblePanel">
	<div class="CollapsiblePanelTab"><!--  <img src="images/openPanel.png" width="9" height="7" alt="openPanel" />-->&nbsp;Filters</div>
	<div class="CollapsiblePanelContent">
	<div><!-- Group Two Div Starts --> <s:div id="mandatory"
		cssClass="dataGrid" style="width:100%;">
		<table width="100%" cellpadding="5" cellspacing="1" border="0" bgcolor="#ffffff">
			<tr>
			<!--<td width="20%" class="textRight"><s:label
					value="%{getText('label.AdvisingBank')}"></s:label>:<span
					class="mandatory">*</span></td>
			<td width="30%" class="text"><s:textfield name="advisingBank" id="applicantNameAddress"></s:textfield> </td>
			--><!--<td width="20%" class="textRight"><s:label	value="%{getText('label.status')}"></s:label>:</td>
			<td width="30%" class="text"><s:select name="status" id="status" 
			list="#{'1':'CREATED','2':'REQUESTED','3':'CREATEDACK','4':'AMENDED','5':'AMENDED REGISTERED','6':'REDUCED','7':'REDUCED ACK','8':'RELEASE REQUEST','9':'RELEASED'}" multiple="true"></s:select> </td>
		-->
		<td class="textRight"><s:label	value="%{getText('label.Direction')}"></s:label>:&nbsp;</td>
		<td class="text"><s:radio name="lcDirection" list="#{'O':'O','I':'I'}"	value="true" key="label.Direction" value="%{lcDirection}"/></td>
		</tr>		
		<tr>
			<td width="20%" class="textRight"><s:label
					value="%{getText('label.fromDate')}"></s:label>:</td>
			<td width="30%" class="text"><sx:datetimepicker name="fromDate" id="fromDate" value="fromDate"></sx:datetimepicker> </td>
			<td width="20%" class="textRight"><s:label	value="%{getText('label.toDate')}"></s:label>:</td>
			<td width="30%" class="text"><sx:datetimepicker name="toDate" id="toDate" value="toDate"></sx:datetimepicker> </td>
		</tr>
		
		</table>
	</s:div> <!-- Group Two Div Ends --></div>
	</div>
	</div>
	<br />
	<div id="CollapsiblePanel3" class="CollapsiblePanel">
	<div class="CollapsiblePanelTab"><!--  <img src="images/openPanel.png" width="9" height="7" alt="openPanel" />-->&nbsp;Columns To display</div>
	<div class="CollapsiblePanelContent">
	<div><!-- Group Three Div Starts --> <s:div id="register"
		cssClass="dataGrid" style="width:100%;">
		<table width="100%" cellpadding="3" cellspacing="1" border="0"
				class="grid">
				<tr>
					<td width="20%" style="text-align: center;" class="textRight">
					<select multiple="multiple"	size="8" name="selectColumn" id="selectColumn" class="text" style="width: 50mm">
						<option value="bgNumber">Bg Number</option>
						<option value="bgCreateType">Bg Type</option>
						<option value="advisingBank">Advising Bank(O)/Issuing Bank(I)</option>
						<option value="bgIssueDate">Issue Date</option>
						<option value="bgAmount">Amount</option>
						<option value="details">Details</option>
						<option value="bgStatus">Status</option>
						<option value="bgLastModifiedTime">Last Modified Time</option>
						<option value="msgStatus">Msg Status</option>
						<!--
						<option value="bgNumber">Bg Number</option>
						<option value="noOfMsg">No Of Message</option>
						<option value="bgIssueDate">Issue Date</option>
						<option value="bgCreateType">Create Type</option>
						<option value="bgRuleCode">Rule Code</option>
						<option value="bgRuleDesc">Rule Description</option>
						<option value="bgNarration">Narration</option>
						<option value="bgAmount">Amount</option>
						<option value="bgStatus">Status</option>
						<option value="bgNoOfAmntmnt">No Of Amendment</option>
						<option value="bgLastModifiedTime">Last Modified Time</option>
						<option value="advisingBank">Advising Bank</option>
						<option value="details">Details</option>
					-->
					</select></td>
					<td width="10%" class="textCenter" style="vertical-align: middle;">
					<br />
					<br />
					<input type="button" class="btn" id="ADDbtnO"
						value=">>"	onClick="if (document.images) copySelected(this.form.selectColumn,this.form.seletedColumn)"><br />
					<br />
					<br />
					<input type="button" class="btn" id='Rem' value="<<" onClick="if (document.images) copySelected(this.form.seletedColumn,this.form.selectColumn) ">
					</td>
					<td width="20%" style="text-align: center;" class="textRight">
					
						<select name="seletedColumn" class="text" multiple
							id="seletedColumn" size="10" style="width: 35mm" ></select>
					
						</td>
					
					
					

				</tr>
			</table>
			
	
	</s:div>
	</div></div></div><br/>
	<table width="100%" cellpadding="0" cellspacing="0" border="0">
		<tr>
			<td>
			
				<s:submit value="Generate Reports" action="generteBgRejectReports" cssClass="btn" />
					<input type="reset" value="Cancel" onclick="cancel()" class="btn"  />
			</td></tr></table>
	</s:form></div>
</div>
</div>
<div id="contentPaneBottom"></div>

	