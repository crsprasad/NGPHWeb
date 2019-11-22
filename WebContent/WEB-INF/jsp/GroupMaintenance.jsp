<%@page import="com.opensymphony.xwork2.ActionContext"%>
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<%@taglib uri="http://displaytag.sf.net" prefix="display"%>

<s:head theme="simple" />
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

	function copySelected(fromObject, toObject, checkMandatory) {
		var k = 0;

		for ( var i = 0, l = fromObject.options.length; i < l; i++) {
			k = fromObject.options[i].value;

			if (fromObject.options[i].selected) {
				if (fromObject.options[i].text.length <= 12) {

					addOption(toObject, fromObject.options[i].text + ' '
							+ checkMandatory, fromObject.options[i].value + ' '
							+ checkMandatory);
				} else {
					//alert("hello-------:  "+fromObject.options[i].text.length);
					addOption(toObject, fromObject.options[i].text.substring(0,
							12), fromObject.options[i].value);
				}
			}

		}
		for ( var i = fromObject.options.length - 1; i > -1; i--) {
			if (fromObject.options[i].selected) {
				//alert("delete is called"+fromObject)
				deleteOption(fromObject, i);
			}
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

	function callSubmit(saveAction) {
		var aa = document.getElementById('groupMaintenance');
		for ( var i = 0; i < 14; i++) {
			//alert(aa.elements[i].value)
			aa.elements[i].disabled = false;
		}
		document.getElementById("saveAction").value = saveAction;
		document.forms[0].action = "groupMaintenanceDataInsert";
		document.forms[0].submit();
	}
	function cancel() {
		document.forms[0].action = "genericScreenFetchValues";
		document.forms[0].submit();
	}
</script>

<div id="contentPane">
<div id="contentPaneTop"></div>
<div id="contentPaneFluid">
<div id="contentPaneInternal"><span class="crumbs"><a
	href="#">Maintenance</a>&nbsp;>&nbsp;Authorisation Group</span></div>
<div id="content">
<h1>Authorisation Group</h1>
<s:form method="post" name="groupMaintenance" action="groupMaintenanceAction" id="groupMaintenance" validate="true">
	<s:hidden id="saveAction" name="saveAction"></s:hidden>
	<s:hidden id="hiddenTxnRef" name="hiddenTxnRef"></s:hidden>
	<div><s:if test="hasFieldErrors()">
		<div class="errorMsg"><span class="errorMsgInline"><s:actionerror /><s:fielderror /></span></div>
	</s:if></div>
	<!--Error Msg Div Ends -->

	<table width="100%" cellpadding="3" cellspacing="1" border="0">
		<tr>
			<td>

			<table width="100%" cellpadding="0" cellspacing="0" border="0">
				<tr>
					<td>
					<table width="95%" cellpadding="5" cellspacing="1" border="0"
						bgcolor="#c5d6f0" id="table1">

						<tr>
							<td width="20%" class="textRight"><s:label
								value="Branch Name"></s:label>&nbsp;:</td>
							<td width="30%" class="text"><s:select
								list="#session.BranchNameDropDown" cssClass="text"
								name="branchCode" key="Branch Name" headerKey=""
								headerValue="Select Branch Name" required="true" id="branchCode" ></s:select></td>

							<td width="20%" class="textRight"><s:label value="Group ID"></s:label>&nbsp;:
							</td>
							<td width="30%" class="text"><s:textfield name="groupID" cssClass="text" key="label.groupId" id="groupID" required="true"></s:textfield></td>
						</tr>
						<tr>
							<td class="textRight"><s:label value="Group Name"></s:label>&nbsp;:
							</td>
							<td colspan="3" class="text"><s:textfield name="groupName"
								cssClass="text" key="label.groupName" id="groupName"
								required="true"></s:textfield></td>
						</tr>
					</table>
					</td>
				</tr>
			</table>
			</td>
		</tr>
	</table>
	<table width="90%" cellpadding="3" cellspacing="1" border="0">
		<tr>
			<br />
			<td class="textBold">&nbsp;Group Selection</td>
		</tr>
		<tr>
			<td>
			<table width="100%" cellpadding="3" cellspacing="1" border="0"
				class="grid">
				<tr>
					<td width="20%" style="text-align: center;" class="textRight">
					<s:select list="#session.supervisorSelect" cssClass="text"
						id="select1" multiple="true" size="10" name="select1"
						style="width: 35mm"></s:select></td>
					<td width="10%" class="textCenter" style="vertical-align: middle;">
					<br />
					<br />
					<input type="button" class="btn" id="ADDbtnO"
						value="Is Optional >>"
						onClick="if (document.images) copySelected(this.form.select1,this.form.supervisorSelectbox,'O')"><br />
					<br />
					<input type="button" class="btn" id="ADDbtnM"
						value="Is Mandatatory >>"
						onClick="if (document.images) copySelected(this.form.select1,this.form.supervisorSelectbox,'M')"><br />
					<br />
					<input type="button" class="btn" id='Rem' value="<<  Remove	Item" onClick="if (document.images) copySelected(this.form.supervisorSelectbox,this.form.select1,'') ">
					</td>
					<td width="20%" style="text-align: center;" class="textRight">
					<s:if test="%{checkForSubmit!='Display_Approve_Reject'}">
						<select name="supervisorSelectbox" class="text" multiple
							id="supervisorSelectbox" size="10" style="width: 35mm" ></select>
					</s:if> <s:if test="%{checkForSubmit=='Display_Approve_Reject'}">
						<s:select list="#session.mulRecord" name="supervisorSelectbox"
							class="text" multiple="true" id="supervisorSelectbox" size="10"
							style="width: 35mm" value="%{session.mulRecord}"  ></s:select>
					</s:if></td>
					<td width="10%" class="text"><br />
					<br />
					<br />
					<br />
					<input type="button" class="btn" id="btnUP" value="UP"
						onClick="listbox_move('supervisorSelectbox', 'up')"> </br>
					<input type="button" class="btn" id="btnDown" value="Down"
						onClick="listbox_move('supervisorSelectbox', 'down')"></td>

				</tr>
			</table>
			</td>
		</tr>
	</table>

	<!--  button panel starts -->
	<div class="clearfix"></div>
	<table width="100%" cellpadding="0" cellspacing="0" border="0">
		<tr>
			<td><s:if test="%{checkForSubmit!='Display_Approve_Reject'}">
				<s:submit name="submit" cssClass="btn" value="Submit"
					process="supervisorSelectbox" onclick="callSubmit('Save')">
				</s:submit>
				<input type="reset" class="btn" value="Reset">
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
	<!--  button panel ends -->
</s:form>
<script type="text/javascript">
var check= document.getElementById("hiddenTxnRef").value;
if(check!=''){
	var aa = document.getElementById('groupMaintenance');
	for ( var i = 0; i < 14; i++) {
		//alert(aa.elements[i].value)
		aa.elements[i].disabled = true;
	}
}

</script>
</div>
</div>
</div>
<div id="contentPaneBottom"></div>
