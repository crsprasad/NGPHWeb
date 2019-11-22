<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="sd" uri="/struts-dojo-tags"%>
<%@taglib uri="http://displaytag.sf.net" prefix="display"%>
<html>
<head>
<s:head />
<sd:head />

<script type="text/javascript">
	function callSearch(Action) {

		document.getElementById("searchAction").value = Action;
		window
				.open(
						"<s:url action='showReceiverSearchScreen' windowState='EXCLUSIVE' />",
						'mywindow', 'top=50,left=250,width=760,height=410');

	}

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
<script type="text/javascript">

document.getElementById("searchAction").value = Action;
	window
			.open(
					"_$tag______________________________________________________________",
					'mywindow', 'top=50,left=250,width=760,height=410');


	function deleteOption(object, index) {
		object.options[index] = null;
	}
</script>
<script type="text/javascript">
	checked = false;
	function checkedAll(report) {
		
		var aa = document.getElementById('report');
		
		if (checked == false) {
			checked = true
			
		} else {
			checked = false
		}
		for ( var i = 10; i < aa.elements.length; i++) {
			
			aa.elements[i].checked = checked;
		}
	}
</script>
<div id="contentPane">
<div id="contentPaneTop"></div>
<div id="contentPaneFluid">
<div id="contentPaneInternal"><span class="crumbs"><a
	href="#">Enquiries & Reports</a>&nbsp;>&nbsp;Payment Received Report</span></div>
<div id="content">
<h1>Payment Received Report</h1>
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

<s:form method="post" name="report" action="PaymentSubmittedReportInwardLoadData"
	theme="simple">
	<s:hidden id="searchAction" name="searchAction"></s:hidden>
	<!-- Collapsible Panels Start-->
	<div id="CollapsiblePanel1" class="CollapsiblePanel">
	<div class="CollapsiblePanelTab"><!--  <img src="images/openPanel.png" width="9" height="7" alt="openPanel" />-->&nbsp;<s:label id="groupBy" key="label.groupBY"></s:label></div>
	<div class="CollapsiblePanelContent">
	<div><!-- Group One Div Starts --> 
	<s:div id="main"	cssClass="dataGrid" style="width:100%;">
	<table width="100%" cellpadding="3" cellspacing="1" border="0"	bgcolor="#ffffff">
		
				<tr>
					<td class="text"><s:radio
						list="#{'senderBank':'Sender','receiverBank':'Receiver','N/A':'N/A'}"
						name="SenderReceiver" value="N/A" value="%{SenderReceiver}" ></s:radio></td>
				</tr>
				<tr>
					<td class="text">
					<input type="checkbox" name="groupby"
						value="hostID"><s:label key="label.HostName" />
						<input type="checkbox" name="groupby"
						value="msgType"><s:label key="label.MessageType" />
						<input type="checkbox" name="groupby"
						value="msgStatus"><s:label key="label.QueueStatus" />
					<!--<s:checkboxlist
						list="#{'hostID':'Host Name','msgType':'Message Type','msgStatus':'Queue Status'}"
						name="groupby"/>
						
						--></td>
				</tr>
			

	</table>
	</s:div> <!-- Group One Div Ends --></div>
	</div>
	</div>

	<div id="CollapsiblePanel2" class="CollapsiblePanel">
	<div class="CollapsiblePanelTab"><!--  <img src="images/openPanel.png" width="9" height="7" alt="openPanel" />-->&nbsp;<s:label key="label.ReportFilter"
				id="ReportFilter"></s:label></div>
	<div class="CollapsiblePanelContent">
	<div><!-- Group Two Div Starts --> <s:div id="mandatory"
		cssClass="dataGrid" style="width:100%;">
		<table width="100%" cellpadding="5" cellspacing="1" border="0"
			bgcolor="#ffffff">
		<tr>
			<td><table width="100%" cellpadding="5" cellspacing="1" border="0"
			bgcolor="#ffffff">
		
			
				<tr>
					<td class="textRight"><s:label value="Sender"></s:label></td>
					<td class="text"><s:textfield name="reciver" id="reciver"
						cssClass="text" /> <input type="button" class="btn"
						value="Search" onclick="callSearch('search')" /></td>
					<td class="textRight"><s:label name="orderingCustomerLabel"
						key="label.beneficiaryCustomer"></s:label></td>
					<td class="text"><s:textarea name="orderingCustomer"
						id="orderingCustomer" cssClass="text"></s:textarea></td>
				</tr>
				
				<tr>
				
					<td class="textRight"><s:label value="From Date"
						key="label.valueFromDate"></s:label></td>
					<td class="text"><sd:datetimepicker name="ValueFromDate"
						displayFormat="MM/dd/yy"  cssClass="text" /></td>
					<td class="textRight"><s:label value="To Date"
						key="label.valueToDate"></s:label></td>
					<td class="text"><sd:datetimepicker name="ValueToDate"
						displayFormat="MM/dd/yy"  cssClass="text" /></td>
				</tr>
				<tr>
					<td class="textRight"><s:label value="From Amount"
						key="label.fromAmount"></s:label></td>
					<td class="text"><s:textfield name="fromAmount"
						id="fromAmount" cssClass="text"></s:textfield></td>
					<td class="textRight"><s:label value="To Amount"
						key="label.toAmount"></s:label></td>
					<td class="text"><s:textfield name="toAmount" id="toAmount"
						cssClass="text"></s:textfield></td>
				</tr><tr>
					<td class="textRight"><s:label key="label.HostName"
						name="hostName" id="hostName"></s:label></td>
					<td class="text"><s:select
						list="#session.hostNameDropDownList" name="HostNameList"
						id="HostNameList" size="8" multiple="true" cssClass="text" size="5" style="width: 50mm"></s:select></td>
					<td class="textRight"><s:label key="label.MessageType"
						name="messageListName" id="messageListName"></s:label></td>
					<td class="text"><s:select
						list="#session.messageTypeDropDownList" name="messageList"
						id="MessageList" size="5" style="width: 50mm" multiple="true" cssClass="text"></s:select></td>
						</tr>
			</table></td>
			<td>
			<td class="textRight"><s:label key="Status"></s:label></td>
				<td class="text"><s:select list="#session.statusList" name="outwardQueue" id="outwardQueue" cssClass="text" multiple="true" size="14" style="width: 50mm"></s:select> </td>
				
				</table>
	</s:div> <!-- Group Two Div Ends --></div>
	</div>
	</div>
	

<div id="CollapsiblePanel4" class="CollapsiblePanel">
	<div class="CollapsiblePanelTab"><!--  <img src="images/openPanel.png" width="9" height="7" alt="openPanel" />-->&nbsp;<s:label value="Columns To display"></s:label></div>
	<div class="CollapsiblePanelContent">
	<div><!-- Group Three Div Starts --> <s:div id="register"
		cssClass="dataGrid" style="width:100%;">
		<table width="100%"  cellpadding="5" cellspacing="1" border="0"
			bgcolor="#ffffff">
				<tr>
					<td width="20%" style="text-align: center;" class="textRight"><select multiple="multiple"
						size="8" name="select1" class="text" style="width: 50mm">
						<option value="msgRef">Message Senders Reference  [system generated]</option>
						<option value="msgType">Msg Type</option>
						<option value="branch">Branch</option>
						<option value="senderBank">Sender IFSC </option>
						<option value="receiverBank">Receiver IFSC</option>
						<option value="status">Status</option>
						<option value="txnReference">Senders Reference</option>
						<option value="dateofIssue">Date of Issue </option>
						<option value="applicent">Applicant</option>
						<option value="beneficiary">Beneficiary</option>
						<option value="applicentBank">Applicant Bank </option>
						<option value="currency">Currency</option>
						<option value="amount">Amount</option>
						<option value="dateofExpiry">Date of Expiry </option>
						<option value="dateofShipment">Date of Shipment </option>
						<option value="dateofAmendment">Date of Amendment</option>
						<option value="newDateofExpiry">New Date of Expiry</option>
						<option value="newDateofShipment">New Date of Shipment</option>
						<option value="presentBankRef">Presenting Banks Ref </option>
						<option value="noofAmendment">No of Amendment</option>
						<option value="dateofReduction">Date of Reduction</option>
						<option value="amountReduced">Amount Reduced</option>
						<option value="amountEnhanced">Amount Enhanced </option>
						<option value="amountofOutstanding">Amount Outstanding </option>
					</select></td>
					<td width="10%" class="textCenter" style="vertical-align: middle;">
					<br />
					<br /><input type="button"
						class="btn" value=">>"
						onClick="if (document.images) copySelected(this.form.select1,this.form.coloumToAdd)"><br />
					<br />
					<input type="button" class="btn" value="<<" onClick="if (document.images) copySelected(this.form.coloumToAdd,this.form.select1)">
					</td>
					<td width="20%" style="text-align: center;" class="textRight"><select name="coloumToAdd"
						id="coloumToAdd" size="8" multiple class="text"
						style="width: 50mm"></select></td>
				</tr>
			</table>
		
	</s:div>
	</div></div></div>
	<!--  button panel starts -->
	<div class="clearfix"></div>
	<table width="100%" cellpadding="0" cellspacing="0" border="0">
		<tr>
			<td><s:submit value="Generate Report" cssClass="btn"
				action="generateReportInward"></s:submit></td>
		</tr>
	</table>
	<!--  button panel ends -->
	<s:hidden name="hiddenDirection" value="I"></s:hidden>
</s:form></div>
</div>
</div>
<div id="contentPaneBottom"></div>