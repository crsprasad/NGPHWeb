<%@page import="com.opensymphony.xwork2.ActionContext"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="sd" uri="/struts-dojo-tags"%>
<%@taglib uri="http://displaytag.sf.net" prefix="display"%>
<script type="text/javascript">
   function callSeeOldData(data)   {
	//alert(data)
	document.getElementById("old_NewScreen").value=data;
	var aa = document.getElementById('form');
	for ( var i = 0; i < aa.elements.length-3 ; i++) {
		
		aa.elements[i].disabled = false;
	}
	document.forms[0].action="callSeeOldDataRoleMaitenance";
	document.forms[0].submit();
	}

	function deleteOption(object, index) {
		object.options[index] = null;
	}

	function addOption(object, text, value) {
		var defaultSelected = true;
		var selected = true;
		var optionName = new Option(text, value, defaultSelected, selected);
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
			object[i].selected = "1";
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
		listbox.options[selIndex].value = listbox.options[selIndex + increment].value;
		listbox.options[selIndex].text = listbox.options[selIndex + increment].text;
		listbox.options[selIndex + increment].value = selValue;
		listbox.options[selIndex + increment].text = selText;
		listbox.selectedIndex = selIndex + increment;
	}

	function defaultAction(){
		if(!document.roleMaintenanceForm.roleAction[0].checked &&
				!document.roleMaintenanceForm.roleAction[1].checked &&
				!document.roleMaintenanceForm.roleAction[2].checked){
			document.roleMaintenanceForm.roleAction[0].checked = true;
			}
		if(document.roleMaintenanceForm.roleAction[0].checked){
			
			document.getElementById("roleId").readOnly=false;
			document.getElementById("searchButton").disabled = true;
		} 
		else {
			document.getElementById("roleId").readOnly=true;
			document.getElementById("searchButton").disabled = false;
		}
	}

	function checkUserActionOnSubmit(){
		if(document.roleMaintenanceForm.roleAction[2].checked){
			if(confirm('Are you sure you want to delete Role?')){
				document.roleMaintenanceForm.action="performRoleAction";
			}
		}
		if(document.roleMaintenanceForm.roleAction[0].checked){
			if(confirm('Are you sure you want to create an Role?')){
				document.roleMaintenanceForm.action="performRoleAction";
			}
		}
		if(document.roleMaintenanceForm.roleAction[1].checked){
			if(confirm('Are you sure you want to modify  Role?')){
				document.roleMaintenanceForm.action="performRoleAction";
			}
		}
		return false;
	}

	function callSearchRoleID() {
			window.open(
					"<s:url action='showRoleIDSearchScreen' windowState='EXCLUSIVE' />",
					'mywindow', 'top=50,left=250,width=750,height=410');
	}

	function changeAction(){
		document.roleMaintenanceForm.action="displayRolenewRoleAction";
		document.roleMaintenanceForm.submit();
	}

	function copyAll(fromObject, toObject) {
		document.getElementById("saveAction").value = 'Save';
		for ( var i = 0, l = fromObject.options.length; i < l; i++) {
				addOption(toObject, fromObject.options[i].text,
						fromObject.options[i].value);
		}
		
	}
	function callAction(saveAction)
	{	
		var aa = document.getElementById('form');
		for ( var i = 0; i < aa.elements.length; i++) {
			
			aa.elements[i].disabled = false;
		}
		document.getElementById("saveAction").value = saveAction;
		document.forms[0].action="performRoleAction";
		document.forms[0].submit();
		
	}
	
</script>
<s:head theme="simple" />
<div id="contentPane">
<div id="contentPaneFluid">
<div id="contentPaneInternal">
	<span class="crumbs">
		<a href="#">Maintenance</a>&nbsp;>>&nbsp;Role Maintenance
	</span>
</div>

<div id="content">
<h1>Role Maintenance</h1>
<s:form method="post" name="roleMaintenanceForm" action="performRoleAction" id="form">
<s:hidden id="searchAction" name="searchAction"></s:hidden>
	<s:hidden id="saveAction" name="saveAction"></s:hidden>
	<s:hidden id="hiddenTxnRef" name="hiddenTxnRef"></s:hidden>
	<s:hidden name="old_NewScreen" id="old_NewScreen"></s:hidden>
	<s:hidden name="txnRef" id="txnRef"></s:hidden>
	<s:hidden id="validUserToApprove" name="validUserToApprove"></s:hidden>
	<div><s:label name="message"></s:label><s:if test="hasFieldErrors()">
				<div class="errorMsg"><span class="errorMsgInline"><s:actionerror /><s:fielderror /></span></div>
			</s:if></div><!--Error Msg Div Ends -->
	<s:hidden id="searchAction" name="searchAction"></s:hidden>
	<table width="100%" cellpadding="0" cellspacing="1" border="0">
		<tr>
			<td class="text">Create New Role</td>
		</tr>
		<tr>
			<td>
			<table width="100%" cellpadding="3" cellspacing="1" border="0"
				class="grid">
				<tr>
				    <td class="textRight"><s:label value="Operation"></s:label></td>
					<td class="text">
					    <s:radio id="roleAction" name="roleAction" list="#{'A':'ADD','M':'MODIFY', 'D':'DELETE'}" onclick="changeAction();" value='roleAction'/>
					</td>
				</tr>
				<tr>
					<td class="textRight"><s:label name="roleId"
						value="Role ID"></s:label>:<span class="mandatory">*</span></td>
					<td class="text">
					    <s:textfield name="roleId" cssClass="text" id="roleId"  required="true"></s:textfield>
					    <input type="button" value="Search Role ID..." onclick="callSearchRoleID()" class="btn" id="searchButton" />
					</td>
					<td class="textRight"><s:label name="roleName"
						value="RoleName"></s:label>:<span class="mandatory">*</span></td>
					<td class="text"><s:textfield name="roleName" id="roleName"
						cssClass="text"></s:textfield></td>
					<td class="textRight"><s:label name="roleDescription"
						value="Role Description"></s:label></td>
					<td class="text"><s:textfield name="roleDescription"
						id="roleDescription" cssClass="text"></s:textfield></td>
					
				</tr>
			</table>
			</td>
		</tr>
	</table>
	<br />
	<br />
	<table width="100%" cellpadding="0" cellspacing="1" border="0"
		bgcolor="#ffffff">
		<tr>
			<td class="textBold"> Functions </td>
		</tr>
		<tr>
			<td>
			<table width="100%" cellpadding="3" cellspacing="1" border="0"
				class="grid">
				<tr>
					<td width="10%" class="text">Assign Functions:<span class="mandatory">*</span></td>
					<td width="20%" class="text">
					<select multiple="multiple" size="8" name="role" class="text" style="width: 50mm">
						 <s:iterator value="%{#session.RemainFunctionsList}" var="function">
							<option value="<s:property value="#function.functionId"/>"><s:property value="#function.functionName"/></option>
						</s:iterator>
					</select>
					<td width="5%" class="textCenter"><input type="button"
						class="btn" value=">>"
						onClick="if (document.images) copySelected(this.form.role,this.form.functions)"><br />
					<input type="button" class="btn" value="<<" onClick="if (document.images) copySelected(this.form.functions,this.form.role)">
					</td>
					<td width="20%" class="text">
					<s:if test="%{checkForSubmit!='Display_Approve_Reject'}">
						<select name="functions" id="functions" size="8" multiple class="text" style="width: 50mm">
						    <s:iterator value="%{#session.AssignedFunctionsList}" var="function">
							<option value="<s:property value="#function.functionId"/>"><s:property value="#function.functionName"/></option>
						    </s:iterator>
						</select>
						<div style="display:none">
							<select name="assignedFunctionsList" id="assignedFunctionsList" multiple></select>
							
						</div></s:if>
						<s:if test="%{checkForSubmit=='Display_Approve_Reject'}">
													<s:select list ="#session.mulRecord" name="approvedFunctions" class="text" multiple="true"
										id="assignedFunctions" size="10" style="width: 35mm" value="%{session.mulRecord}"></s:select>
										</s:if>
					</td>
				</tr>
			</table>
			</td>
		</tr>
	</table>
	<!--  button panel starts -->
	<div class="clearfix"></div>
	<table width="100%" cellpadding="0" cellspacing="0" border="0">
		<tr>
			<td>
			<s:if test="%{checkForSubmit!='Display_Approve_Reject'}">
				<input type="submit" id="submitButtonId" value="Submit" onclick="copyAll(this.form.functions,this.form.assignedFunctionsList);checkUserActionOnSubmit();"/>
			</s:if>
			<s:if test="%{checkForSubmit=='Display_Approve_Reject'}">
				<s:if test="%{validUserToApprove}">
					<input type="button" value="Approve" onclick="callAction('Approve')" id="approveBtn">
					<input type="button" value="Reject" onclick="callAction('Reject')" id="rejectBtn">
						<s:if test="%{flagForNewData != 'flagForNewData'}">
							<input type="button" value="Old Data" id="oldBtn" onclick="callSeeOldData('OLD')" >
						</s:if>
						<s:if test="%{flagForNewData == 'flagForNewData'}">
							<input type="button" value="New Data" onclick="callSeeOldData('NEW')" >
						</s:if>
				</s:if>
				<s:if test="%{!validUserToApprove}">
					<input type="submit" id="submitButtonId" value="Submit" onclick="copyAll(this.form.functions,this.form.assignedFunctionsList);checkUserActionOnSubmit();"/>
				</s:if>
			</s:if>
			</td>
		</tr>
	</table>
	<!--  button panel ends -->
</s:form></div>
</div>
</div>
<div id="contentPaneBottom"></div>
<script>
defaultAction();

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

var radioButtons = document.getElementsByName("roleAction")	;
//alert("1"+radioButtons[1].checked);
//alert("2"+radioButtons[2].checked);
if(radioButtons[1].checked!=true)
	{
	 document.getElementById("oldBtn").disabled=true;
	}
var check= document.getElementById("hiddenTxnRef").value;
if(check!=''){
	var aa = document.getElementById('form');
	var isValidUser = document.getElementById("validUserToApprove").value;
		if(isValidUser=="true")
		{
			for ( var i = 0; i < aa.length-17; i++) 
				{
					aa.elements[i].disabled = true;
				}
			
		}
		else
		{
			for ( var i = 0; i < aa.length-17; i++) 
			{
				aa.elements[i].enabled = true;
			}
		}
}
</script>

