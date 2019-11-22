<%@page import="com.opensymphony.xwork2.ActionContext"%>
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<%@taglib uri="http://displaytag.sf.net" prefix="display"%>

<script language="JavaScript">
	function deleteOption(object, index) {
		object.options[index] = null;
	}

	function addOption(object, text, value) {
		var defaultSelected = true;
		var selected = true;
		var optionName = new Option(text, value, defaultSelected, selected)
		object.options[object.length] = optionName;
	}

	function copySelected(fromObject, toObject, condition) {
		//var k=condition;
		//if(k=="ok"){
		//var id=callAjax();
		//alert(id)
		//}
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

	function callAjax() {
		document.forms[0].action = "treeView";
		document.forms[0].submit();

	}

	function callSubmit(saveAction)
	{
		var aa = document.getElementById('form');
		for ( var i = 0; i < 18; i++) {
			aa.elements[i].disabled = false;
		}
		document.getElementById("saveAction").value = saveAction;
		document.forms[0].action="InsertAutorizationData";
		document.forms[0].submit();
	}
	function cancel() {
		document.forms[0].action = "genericScreenFetchValues";
		document.forms[0].submit();
	}
</script>
<sx:head parseContent="true" />
<s:head theme="simple" />
<div id="contentPane">
<div id="contentPaneTop"></div>
<div id="contentPaneFluid">
<div id="contentPaneInternal"><span class="crumbs"><a
	href="#">Maintenance</a>&nbsp;>&nbsp;Authorisation Schema</span>
<div id="content">
<h1>Authorisation Schema</h1>
<s:form method="post" action="authorizationMaintenanceAction"  id="form">
<s:hidden id="saveAction" name="saveAction"></s:hidden>
					<s:hidden id="hiddenTxnRef" name="hiddenTxnRef"></s:hidden>
	<div><s:label name="message"></s:label><s:if test="hasFieldErrors()">
				<div class="errorMsg"><span class="errorMsgInline"><s:actionerror /><s:fielderror /></span></div>
				</s:if></div><!--Error Msg Div Ends -->
	<div id="CollapsiblePanel1" class="CollapsiblePanel">
	<div class="CollapsiblePanelTab"><!--  <img src="images/openPanel.png" width="9" height="7" alt="openPanel" />-->&nbsp;</div>
	<div class="CollapsiblePanelContent">
	<div><!-- Group One Div Starts --> 
	<s:div id="main"	cssClass="dataGrid" style="width:100%;">
	<table width="100%" cellpadding="3" cellspacing="1" border="0"	bgcolor="#ffffff">
		<tr>
			<td><table width="100%" cellpadding="5" cellspacing="1" border="0"
			bgcolor="#ffffff"><tr>
			<td class="textRight"><s:label value="Channel Type"></s:label>:&nbsp;</td>
			<td class="text"><s:select list="{'SWIFT','RTGS'}" name="channel_type" id="channel_type" cssClass="text" headerValue="Select Channel" key="label.Channel"></s:select></td>
			<td class="textRight"><s:label value="Message Type"></s:label>:&nbsp;</td>
			<td class="text"><s:select list="#session.msgTypeDropDown"	name="msg_type" id="msg_type" cssClass="text" headerKey="" headerValue="Select Message Type" key="label.ruleMsgtype" required="true"></s:select></td>
		</tr>
		<tr>
			<td class="textRight"><s:label value="Sub-Message Type"></s:label>:&nbsp;</td>
			<td class="text"><s:select list="#session.subMsgTypeDropDown"
				name="subMsg_type" id="subMsg_type" cssClass="text" headerKey=""
				headerValue="Select Sub Message Type" key="label.subMessage"
				required="true"></s:select></td>
		
			<td class="textRight"><s:label value="Currency"></s:label>:&nbsp;</td>
			<td class="text"><s:select list="{'INR','USD'}"
				name="curreny_type" id="curreny_type" cssClass="text" headerKey=""
				headerValue="Select Currency" key="label.currency" required="true"></s:select>
			</td>
		</tr>
		<tr>
			<td class="textRight"><s:label value="Branch Name"></s:label>:&nbsp;</td>
			<td class="text"><s:select list="#session.branchName"
				name="Branch_Name" id="Branch_Name" cssClass="text" headerKey=""
				headerValue="Select Branch" key="Branch Code" required="true"></s:select>
			</td>
		
			<td class="textRight"><s:label value="Host Name"></s:label>:&nbsp;</td>
			<td class="text"><s:select list="#session.hostName"
				name="host_name" id="host_name" headerKey="" cssClass="text"
				headerValue="Select hostName" key="hostName" required="true"></s:select>
			</td>
		</tr>
		<tr>
			<td class="textRight"><s:label value="Amount From"></s:label>:&nbsp;</td>
			<td class="text"><s:textfield name="from_Amount"
				id="from_Amount" key="From Amount" required="true" cssClass="text"></s:textfield>
			</td>
		
			<td class="textRight"><s:label value="Amount To"></s:label>:&nbsp;</td>
			<td class="text"><s:textfield name="to_Amount" id="to_Amount"
				key="To Amount" required="true" cssClass="text"></s:textfield></td>
		</tr>
		<tr>
			<td class="textRight"><s:label value="Direction"></s:label>:&nbsp;</td>
			<td class="text"><s:radio name="direction" list="#{'I':'I','O':'O'}"
				value="direction" key="label.Direction" required="true" /></td>
		</tr></table></td>
			
			<td  class="text">
			<div style="height: 100px; overflow: auto;"
				class="divBg"><sx:tree id="root" label="root"
				showRootGrid="false" cssClass="text">
				<s:iterator value="treeView">
					<sx:treenode id="groupIDandGroupName" label="%{key}">
						<s:iterator id="ele" value="value">
							<sx:treenode id="listEle" label="%{ele}" />
						</s:iterator>
					</sx:treenode>
				</s:iterator>
			</sx:tree></div>
			</td>
		
	</table>
	</s:div> <!-- Group One Div Ends --></div>
	</div>
	</div>
	<br/>
<div id="CollapsiblePanel4" class="CollapsiblePanel">
	<div class="CollapsiblePanelTab"><!--  <img src="images/openPanel.png" width="9" height="7" alt="openPanel" />-->&nbsp;<s:label value="Columns To display"></s:label></div>
	<div class="CollapsiblePanelContent">
	<div><!-- Group Three Div Starts --> <s:div id="register"
		cssClass="dataGrid" style="width:100%;">
		<table width="100%"  cellpadding="5" cellspacing="1" border="0"
			bgcolor="#ffffff">
				<tr><td width="20%" style="text-align: center;" class="textRight"><s:select
						list="groupIDandGroupName" multiple="true" size="8" name="select1"
						cssClass="text" style="width: 50mm"></s:select></td>
					<td width="10%" class="textCenter" style="vertical-align: middle;">
					<br />
					<br /><input type="button"
						class="btn" value=">>" maxlength="100"
						onClick="if (document.images) copySelected(this.form.select1,this.form.selectgroupIDAndName,'ok')"><br />
					<input type="button" class="btn" value="<<" onClick="if (document.images) copySelected(this.form.selectgroupIDAndName,this.form.select1,'DontDo')">
					</td>
						<td width="20%" style="text-align: center;" class="textRight">
					<s:if test="%{checkForSubmit!='Display_Approve_Reject'}">
					<s:select
						name="selectgroupIDAndName" list="selectgroupIDAndName"
						id="selectgroupIDAndName" size="8" multiple="true" class="text"
						style="width: 50mm"></s:select></s:if>
						<s:if test="%{checkForSubmit=='Display_Approve_Reject'}">
													<s:select list ="#session.mulRecord" name="selectgroupIDAndName" class="text" multiple="true"
										id="selectgroupIDAndName" size="10" style="width: 35mm" value="%{session.mulRecord}"></s:select>
										</s:if>
						</td>
					<td width="25%" class="textCenter"><br/><br/><br/><input type="button"
						class="btn" value="UP"
						onClick="listbox_move('selectgroupIDAndName', 'up')"><br/><input
						type="button" value="Down" class="btn"
						onClick="listbox_move('selectgroupIDAndName', 'down')"></td>
				</tr>
			</table>
		
	</s:div>
	</div></div></div>
	<!--  button panel starts -->
	<div class="clearfix"></div>
	<table width="100%" cellpadding="0" cellspacing="0" border="0">
		<tr>
			<td><s:if test="%{checkForSubmit!='Display_Approve_Reject'}"><input type="button" name="Submit" value="Submit" 
				 onclick="callSubmit('Save')"></s:if>
				<s:if test="%{checkForSubmit=='Display_Approve_Reject'}">
					<s:if test="%{validUserToApprove}">
						<input type="button" value="Approve" onclick="callSubmit('Approve')">
						<input type="button" value="Reject" onclick="callSubmit('Reject')">
					</s:if>
					<s:if test="%{!validUserToApprove}">
						<input type="button" value="Approve" onclick="callSubmit('Approve')" disabled="disabled">
						<input type="button" value="Reject" onclick="callSubmit('Reject')" disabled="disabled">
					</s:if>
				</s:if>
				<input type="button" value="Cancel" onclick="cancel()">	
				
				</td>
		</tr>
	</table>
	<!--  button panel ends -->
</s:form>
<script type="text/javascript">
var check= document.getElementById("hiddenTxnRef").value;
if(check!=''){
	var aa = document.getElementById('form');
	for ( var i = 0; i < 20; i++) {
		//alert(aa.elements[i].value)
		aa.elements[i].disabled = true;
	}
}

</script>
</div>
</div>
</div>
<div id="contentPaneBottom"></div>
</div>



