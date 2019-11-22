
<%@page import="com.opensymphony.xwork2.ActionContext"%>
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<%@taglib uri="http://displaytag.sf.net" prefix="display"%>

<s:head theme="simple" />
<sx:head parseContent="true" />
<script type="text/javascript">
function defaultAction(){
	if(!document.custRegistrationForm.custAction[0].checked &&
			!document.custRegistrationForm.custAction[1].checked &&
			!document.custRegistrationForm.custAction[2].checked){
		document.custRegistrationForm.custAction[0].checked = true;
		}
	if(document.custRegistrationForm.custAction[0].checked){
		document.custRegistrationForm.custActive[0].checked = true;
		document.custRegistrationForm.custActive[0].disabled = true;
		document.custRegistrationForm.custActive[1].disabled = true;
		
	} else {
		document.custRegistrationForm.custActive[0].disabled = false;
		document.custRegistrationForm.custActive[1].disabled = false;
		}
		
	}

function checkcustActionOnSubmit(){
	if(document.custRegistrationForm.custAction[2].checked){
		document.custRegistrationForm.action="deleteCustomerAction";
		if(confirm('Are you sure you want to delete Customer?')){
			
			document.custRegistrationForm.submit();
		}
	}
	if(document.custRegistrationForm.custAction[0].checked){
		document.custRegistrationForm.action="performCustomerAction";
		if(confirm('Are you sure you want to create an Customer?')){
		
			document.custRegistrationForm.submit();
		}
	}
	if(document.custRegistrationForm.custAction[1].checked){
		document.custRegistrationForm.action="performCustomerAction";
		if(confirm('Are you sure you want to modify  Customer?')){
			document.custRegistrationForm.submit();
		}
	}
}

</script>
<div id="contentPane">
<div id="contentPaneTop"></div>
<div id="contentPaneFluid">
<div id="contentPaneInternal"><span class="crumbs"><a
	href="#">Maintenance</a>&nbsp;>&nbsp;Customer Maintenance </span></div>
<div id="content" >
<h1>Customer Maintenance</h1>
	<s:form method="post" name="custRegistrationForm" action="performCustomerAction" id="form">
	<s:hidden id="saveAction" name="saveAction"></s:hidden>
	<s:hidden id="hiddenTxnRef" name="hiddenTxnRef"></s:hidden>
	
	<s:hidden name="txnRef" id="txnRef"></s:hidden>
		<!-- Collapsible Panels Start-->
		<div id="CollapsiblePanel1" class="CollapsiblePanel">
			<div class="CollapsiblePanelTab">&nbsp;Customer Details</div>
			<div class="CollapsiblePanelContent">
				<div>
					<!-- Group One Div Starts --> 
					<s:div id="main" cssClass="dataGrid" style="width:100%;"> 
						<table width="100%" cellpadding="3" cellspacing="1" border="0" bgcolor="#ffffff">		
							<tr>
								<td width="25%" class="textRight">
									<s:label value="Operation"></s:label> 
								</td>
								<td width="15%" class="text">
									<s:radio id="custAction" name="custAction" list="#{'A':'ADD','M':'MODIFY', 'D':'DELETE'}" onclick="changeAction();" value='custAction'/>
								</td>
								<td width="25%" class="textRight">
								</td>
								<td width="15%" class="text">
								</td>
								<td class="text">&nbsp;</td>
							</tr>									
							<tr>
								<td class="textRight" width="25%">
									<s:label value="Customer First Name"></s:label>:<span class="mandatory">*</span>
								</td>
								<td class="text" width="15%">
									<s:textfield name="custFirstName" id="custFirstName" cssClass="text" maxLength="50"></s:textfield>
								</td>
								<td class="textRight">
									<s:label value="Customer Last Name"></s:label>:<span class="mandatory">*</span>
								</td>
								<td class="text">
									<s:textfield name="custLastName" cssClass="text" id="custLastName" maxLength="50"></s:textfield>
								</td>
								<td class="text">&nbsp;</td>
							</tr>
							<tr>
								<td class="textRight">
									<s:label value="Customer E-Mail"></s:label>:<span class="mandatory">*</span>
								</td>
								<td class="text">
									<s:textfield name="custEmail" cssClass="text" id="custEmail" maxLength="50"></s:textfield>
								</td>
								<td class="textRight">
									<s:label value="Mobile Number"></s:label>:<span class="mandatory">*</span>
								</td>
								<td class="text">
									<s:textfield name="phoneNo" cssClass="text" id="phoneNo" maxLength="10"></s:textfield>
								</td>
								<td class="text">&nbsp;</td>
							</tr>
							<tr>
								<td class="textRight">
									<s:label value="Address"></s:label>:<span class="mandatory">*</span>
								</td>
								<td class="text">
									<s:textarea name="custAddress" cssClass="text" id="custAddress" maxLength="300"></s:textarea>
								</td>
								<td class="textRight">
									<s:label value="Customer Status"></s:label>:<span class="mandatory">*</span>
								</td>
								<td class="text">
									<s:radio name="custActive" list="#{'1':'ACTIVE','0':'INACTIVE'}" value="custActive"/>
								</td>
								<td class="text">&nbsp;</td>
							</tr>
						</table>
					</s:div>
				</div>

	
		<!--  button panel starts -->
		<div class="clearfix"></div>
		<table width="100%" cellpadding="0" cellspacing="0" border="0">
			<tr>
			<td align="center">
			<s:if test="%{checkForSubmit!='Display_Approve_Reject'}">
				<input type="button" id="submitButtonId" value="Submit" onclick="checkcustActionOnSubmit();"/>
			</s:if>
			<s:if test="%{checkForSubmit=='Display_Approve_Reject'}">
				<s:if test="%{validUserToApprove}">
					<input type="button" value="Approve" onclick="callAction('Approve')" id="approveBtn">
					<input type="button" value="Reject" onclick="callAction('Reject')" id="rejectBtn">
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
				</s:if>
				<s:if test="%{!validUserToApprove}">
					<input type="button" value="Approve" onclick="callAction('Approve')" disabled="disabled">
					<input type="button" value="Reject" onclick="callAction('Reject')" disabled="disabled">
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
				</s:if>
			</s:if>
			</td>
		</tr>
		</table>
		<!--  button panel ends -->
	</s:form>
</div>
</div>
</div>
<div id="contentPaneBottom"></div>
<script>
defaultAction();
var check= document.getElementById("hiddenTxnRef").value;
if(check!=''){
	var aa = document.getElementById('form');
	for ( var i = 0; i < aa.length - 3; i++) {
		
		aa.elements[i].disabled = true;
	}
}
</script>
