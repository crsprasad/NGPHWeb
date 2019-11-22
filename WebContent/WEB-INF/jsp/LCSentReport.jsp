
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
	href="#">Enquiry</a>&nbsp;>&nbsp;Lc sent Reports</span></div>
<div id="content">
<h1>Lc Sent Reports</h1>
<s:hidden id="repairComments" name="repairComments"></s:hidden> <s:form method="post">
<s:hidden name="lcDirection" id="lcDirection" value="O"></s:hidden>	
<s:hidden name="advising_issuingBank" id="advising_issuingBank" value="advisingBank"></s:hidden>
	<!--Error Msg Div Starts -->
	<div><s:if test="hasFieldErrors()">
				<div class="errorMsg"><span class="errorMsgInline"><s:actionerror /><s:fielderror /></span></div>
			</s:if></div><!--Error Msg Div Ends -->
	<!-- Collapsible Panels Start-->
	<div id="CollapsiblePanel1" class="CollapsiblePanel">
	<div class="CollapsiblePanelTab"><!--  <img src="images/openPanel.png" width="9" height="7" alt="openPanel" />-->&nbsp;Group By</div>
	<div class="CollapsiblePanelContent">
	<div><!-- Group One Div Starts --> 
	<s:div id="main"	cssClass="dataGrid" style="width:100%;">
	<table width="100%" cellpadding="3" cellspacing="1" border="0"	bgcolor="#ffffff">
	<tr>
				<td  align="center"><s:radio list="{'Advising Bank','N/A'}" name="groupBy" id="groupBy"></s:radio></td>
				<td align="center"><s:label value="Status"></s:label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<s:checkbox name="groupByStatus" id="groupByStatus"></s:checkbox> </td>
					</tr>
		</table>
	</s:div> <!-- Group One Div Ends --></div>
	</div>
	</div>
	<br />
	<div id="CollapsiblePanel2" class="CollapsiblePanel">
	<div class="CollapsiblePanelTab"><!--  <img src="images/openPanel.png" width="9" height="7" alt="openPanel" />-->&nbsp;Filters</div>
	<div class="CollapsiblePanelContent">
	<div><!-- Group Two Div Starts --> <s:div id="mandatory"
		cssClass="dataGrid" style="width:100%;">
		<table width="100%" cellpadding="5" cellspacing="1" border="0" bgcolor="#ffffff">
			<tr>
			<td width="20%" class="textRight"><s:label
					value="%{getText('label.AdvisingBank')}"></s:label>:</td>
			<td width="30%" class="text"><s:textfield name="advisingBank" id="advisingBank"></s:textfield> </td>
			<td width="20%" class="textRight"><s:label	value="%{getText('label.status')}"></s:label>:</td>
			<td width="30%" class="text">
			<select id="status1" name="status1" multiple="multiple">
			<option value="01">PRE ADVICE</option>
			<option value="02">PRE ADVICE ACK</option>
			<option value="03">OPENED</option>
			<option value="04">REGISTERED</option>
			<option value="05">AMENDED</option>
			<option value="06">AMEND REGISTERED</option>
			<option value="07">PYMT ADVICED</option>
			<option value="08">PYMT AUTHORISED</option>
			<option value="09">CLAIMED</option>
			<option value="10">CLAIM-PAID</option>
			</select><!--
						
			<s:select name="status" id="status" 
			list="#{'01':'PRE ADVICE','02':'PRE ADVICE ACK','03':'OPENED','04':'REGISTERED','05':'AMENDED','06':'AMEND REGISTERED','07':'PYMT ADVICED','08':'PYMT AUTHORISED','09':'CLAIMED','10':'CLAIM-PAID'}" multiple="true"></s:select>
			
			--></td>
		</tr>
		<tr>
			<td width="20%" class="textRight"><s:label
					value="%{getText('label.Applicant')}"></s:label>:</td>
			<td width="30%" class="text"><s:textfield name="applicant" id="applicant"></s:textfield> </td>
			<td width="20%" class="textRight"><s:label	value="%{getText('label.Beneficiary')}"></s:label>:</td>
			<td width="30%" class="text"><s:textfield name="beneficiary" id="Beneficiary"></s:textfield> </td>
		</tr>
		<tr>
			<td width="20%" class="textRight"><s:label
					value="%{getText('label.fromDate')}"></s:label>:</td>
			<td width="30%" class="text"><sx:datetimepicker name="fromDate" id="fromDate" value="today" type="date" displayFormat="MM/dd/yyyy"></sx:datetimepicker> </td>
			<td width="20%" class="textRight"><s:label	value="%{getText('label.toDate')}"></s:label>:</td>
			<td width="30%" class="text"><sx:datetimepicker name="toDate" id="toDate" value="toDate" type="date" displayFormat="MM/dd/yyyy"></sx:datetimepicker> </td>
		</tr>
		<tr>
			<td width="20%" class="textRight"><s:label
					value="%{getText('label.fromAmount')}"></s:label>:</td>
			<td width="30%" class="text"><s:textfield name="fromAmount" id="fromAmount"></s:textfield> </td>
			<td width="20%" class="textRight"><s:label	value="%{getText('label.toAmount')}"></s:label>:</td>
			<td width="30%" class="text"><s:textfield name="toAmount" id="toAmount"></s:textfield> </td>
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
						<option value="lcNo">Number</option>
						<option value="lcType">Type</option>
						<option value="lcAdvisingBank">Advising Bank</option>
						<option value="lcAppicant">Appicant</option>
						<option value="lcBenificiary">Benificiary</option>
						<option value="lcIssueDate">Issue Date</option>
						<option value="lcCurrency">Ccy</option>
						<option value="lcAmount">Amount</option>
						<option value="lcExpireDate">Expiry Date</option>
						<option value="lcNarrative">Narrative</option>
						<option value="status">Status</option>
						<option value="lstModifiedTime">last Modified Time</option>
						<option value="msgStatus">Msg Status</option>
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
			
				<s:submit value="Generate Reports" action="generteLcReports" cssClass="btn" />
					<input type="reset" value="Cancel" onclick="cancel()" class="btn"  />
			</td></tr></table>
	</s:form></div>
</div>
</div>
<div id="contentPaneBottom"></div>

	