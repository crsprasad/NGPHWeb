<%@page import="com.opensymphony.xwork2.ActionContext"%>
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<%@taglib uri="http://displaytag.sf.net" prefix="display"%>

<s:head theme="simple" />
<sx:head parseContent="true" />
<script type="text/javascript">

</script>
<script type="text/javascript">
function callSearchAccountNo(Action) {

	var accountNoField= document.getElementById("accountNo").value;
	var branch= document.getElementById("branch").value;
	document.getElementById("searchAction").value = branch;
	if(branch!=""){
	if(accountNoField==""){
		window.open(
				"<s:url action='showAccountSearchScreen' windowState='EXCLUSIVE' />",
				'mywindow', 'top=50,left=250,width=750,height=410');
	}
	else
		{
			callShowGrid();
			
		}
	}
else
	alert("Please Select Branch");
		
	
}

	function callMMIDGenerator()
	{
		document.getElementById("branch").disabled=false;
		document.forms[0].action = "generteMMID";
		document.forms[0].submit();
	}
	function callADD()
	{
		document.getElementById("branch").disabled=false;
		document.forms[0].action = "addMMIDTOGrid";
		document.forms[0].submit();
		
	}
	function cancel()
	{
		document.forms[0].action = "reset";
		document.forms[0].submit();
	
	}
	function callShowGrid()
	{
		document.getElementById("branch").disabled=false;
		//alert("hellollll")
		document.forms[0].action = "displayGridView";
		document.forms[0].submit();
	}
	function callAction(Action)
	{	
		var aa = document.getElementById('form');
		for ( var i = 0; i < aa.elements.length-3 ; i++) {
			
			aa.elements[i].disabled = false;
		}
		document.getElementById("saveAction").value= Action;
			document.forms[0].action="accountMaintenanceAction";
			document.forms[0].submit();
	}
	function callSeeOldData(data)
	{
		//alert(data)
		document.getElementById("old_NewScreen").value=data;
		var aa = document.getElementById('form');
		for ( var i = 0; i < aa.elements.length-3 ; i++) {
			
			aa.elements[i].disabled = false;
		}
		document.forms[0].action="callSeeOldDataAccountMaintainence";
		document.forms[0].submit();
	}
</script>

<div id="contentPane">
<div id="contentPaneTop"></div>
<div id="contentPaneFluid">
<div id="contentPaneInternal"><span class="crumbs"><a
	href="#">Maintenance</a>&nbsp;>&nbsp;Account Maintenance </span></div>
<div id="content" >
<h1>Account Maintenance</h1>
<s:form method="post" action="accountMaintenance" validate="true" id="form"> 
	<s:hidden id="payMessage" name="payMessage"></s:hidden>
	<s:hidden id="searchAction" name="searchAction"></s:hidden>
	<s:hidden id="saveAction" name="saveAction"></s:hidden>
	<s:hidden id="hiddenTxnRef" name="hiddenTxnRef"></s:hidden>
	<s:hidden id="hiddenBranch" name="hiddenBranch"></s:hidden>
	<s:hidden name="old_NewScreen" id="old_NewScreen"></s:hidden>
	<s:hidden name="txnRef" id="txnRef"></s:hidden>
	<div><s:label name="message"></s:label><s:if test="hasFieldErrors()">
				<div class="errorMsg"><span class="errorMsgInline"><s:actionerror /><s:fielderror /></span></div>
			</s:if></div><!--Error Msg Div Ends -->
	<table width="100%" cellpadding="3" cellspacing="1" border="0"
			>
		<tr>
			<td>
			
			<table width="100%" cellpadding="0" cellspacing="0" border="0">
		<tr>
			<td>
			<table width="95%" cellpadding="5" cellspacing="1" border="0"
				bgcolor="#c5d6f0" id="table1">
				
				<tr>
					<td width="20%" class="textRight"><s:label
						value="%{getText('label.branch')}"></s:label>:<span	class="mandatory">*</span>&nbsp;</td>
					<td width="30%" class="text">
					<s:select list ="#session.branchCode"name="branch" id="branch" value="branch" cssClass="txtField" required="true" headerKey="" headerValue="Select Branch"></s:select>
					<input type="button" value="Search Acc No..."
						onclick="return callSearchAccountNo('AccountNO')" id ="btnSearch" class="btn" />
					</td>
					<td class="textRight" width="20%"><s:label
						value="%{getText('label.AccountNo')}"></s:label>:<span	class="mandatory">*</span>&nbsp;</td>
					<td width="30%" class="text">
					<s:textfield name="accountNo" id="accountNo" cssClass="txtField" onkeypress="callDisableButton()" ></s:textfield>
					
					</td>
					
	</tr>
	<tr>
	<td width="20%" class="textRight"><s:label	value="%{getText('label.DebitLimit')}"></s:label>:</td>
			<td width="30%" class="text"><s:textfield name="debitLimit" id="debitLimit" maxLength="16"></s:textfield></td>
			<td width="20%" class="textRight"><s:label value="%{getText('label.creditLimit')}"></s:label>:</td>
			<td width="30%" class="text"><s:textfield name = "creditLimit"  id="creditLimit" maxLength="16"></s:textfield> </td>
		</tr>
		<tr>
			<td width="20%" class="textRight"><s:label	value="%{getText('label.accountype')}"></s:label>:</td>
			<td colspan="3" class="text">
			
			
			<s:select list="{'SAVING','CURRENT','CASH CREDIT','LOAN ACCOUNT','OVER DRAFT','NRE','INDO NEPAL REMITTANCE','CARD PAYMENT','REMITTANCE FOR CUSTOMERS NOT HAVING ACCOUNT WITH REMITTING BANKS'}" name="accountype" id="accountype" value="%{accountype}" headerKey="" headerValue="Select Account Type"></s:select> 
			</td>
		</tr>
	
	<tr>
	
					<td width="20%" class="textRight"><s:label
						value="%{getText('label.MMID')}"></s:label>:&nbsp;</td>
					<td width="30%" class="text"><s:textfield name="MMDI" id="MMDI" cssClass="txtField" required="true" maxLength="7" readonly="true"></s:textfield>
					
				<td width="20%" class="textRight"><s:label
						value="Mobile Number"></s:label>&nbsp;</td>
				<td width="30%" class="text"><s:textfield name="mobileNo" id="mobileNo" cssClass="txtField" maxLength="10" ></s:textfield>&nbsp;
				<input type="button" value="Generate MMID" onclick="callMMIDGenerator()">&nbsp;
				<input type="button" value="ADD" onclick="callADD()"></td>
				</tr>
	
	
	
	

			</table></td></tr></table>
			</td>
		</tr>
		<tr>
		<td><display:table uid="row" name="sessionScope.searchList" pagesize="10" requestURI="/success" cellpadding="3"
				cellspacing="0" class="dataGrid">
				<display:setProperty name="basic.msg.empty_list" 
    				value='<div class="infoMsg"><span>Nothing found to display</span></div>' />
				<display etProperty name="css.tr.even" value="TDBGlightgray" />
				<display etProperty name="css.tr.odd" value="TDBGgray" cssClass="odd"/>

				<display:column title="Mobile No" property="mobileNo"
					headerClass="gridHdrBg"  sortable="false" style="text-align:center" />

				<display:column title="MMID" property="MMID"
					headerClass="gridHdrBg"  sortable="false" style="text-align:center"/>
					<display:column title="Delete" headerClass="gridHdrBg" style="text-align:center" >
				<s:checkbox  name="select[%{#attr.row_rowNum -1 }]"
					value="%{select[%{#attr.row_rowNum - 1 }]}" id="%{select[%{#attr.row_rowNum - 1 }]}"
					theme="simple" />
				<input type="hidden" name="mobileno" value="${row.mobileNo}" />
				<input type="hidden" name="flag" value="${row.flag}" />
				
				</display:column>
				<display:column title="Set As Default" headerClass="gridHdrBg" style="text-align:center" >
				<input type="radio" id="${row.MMID},${row.mobileNo}" name="setAsDefault" value="${row.MMID},${row.mobileNo}">
				</display:column>
			</display:table></td>
		</tr>
		
		<tr>
			<td class="textCenter"><br />
		
			<s:if test="%{checkForSubmit!='Display_Approve_Reject'}">
			<input type="button" value="Submit"  onclick="callAction('Save')">
			</s:if>
			<s:if test="%{checkForSubmit=='Display_Approve_Reject'}">
			
			<s:if test="%{validUserToApprove}">
				<input type="button" value="Approve" onclick="callAction('Approve')" id="approveBtn">
				<input type="button" value="Reject" onclick="callAction('Reject')" id="rejectBtn">
				<s:if test="%{flagForNewData != 'flagForNewData'}">
					<input type="button" value="Old Data"
						onclick="callSeeOldData('OLD')" >
						</s:if>
						<s:if test="%{flagForNewData == 'flagForNewData'}">
						<input type="button" value="New Data"
						onclick="callSeeOldData('NEW')" >
						</s:if>
			</s:if>
			<s:if test="%{!validUserToApprove}">
				<input type="button" value="Approve" onclick="callAction('Approve')" disabled="disabled">
				<input type="button" value="Reject" onclick="callAction('Reject')" disabled="disabled">
				<s:if test="%{flagForNewData != 'flagForNewData'}">
					<input type="button" value="Old Data"
						onclick="callSeeOldData('OLD')" disabled="disabled">
						</s:if>
						<s:if test="%{flagForNewData == 'flagForNewData'}">
						<input type="button" value="New Data"
						onclick="callSeeOldData('NEW')" disabled="disabled">
						</s:if>
			</s:if>
			</s:if>
					<input type ="reset" value="Cancel" onclick="cancel()" class="btn" />

		</tr>
	</table>
	<s:hidden name="hiddenSize" id="hiddenSize"></s:hidden>
	<s:hidden name="hiddenList" id="hiddenList"></s:hidden>
	<s:hidden name="hiddenRadio" id="hiddenRadio"></s:hidden>
	<s:hidden name="hiddenAccountType" id="hiddenAccountType"></s:hidden>
	
</s:form>
<script type="text/javascript">
var branch = document.getElementById("branch").value;
var hiddenAccountType = document.getElementById("hiddenAccountType").value;
var hiddenBranch = document.getElementById("hiddenBranch").value;
//alert(hiddenBranch)
if(hiddenBranch!='')
	{
		//alert("hello")
		document.getElementById("branch").disabled = true;
	
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
//alert(hiddenAccountType)
if(hiddenAccountType!='')
	document.getElementById("accountype").value = hiddenAccountType;
var hiddenList = document.getElementById("hiddenList").value;
var stringArray = hiddenList.split(",");

var hiddenLength = document.getElementById("hiddenSize").value;
var check = document.getElementById(document.getElementById('hiddenRadio').value);
check.checked = true;



for(var i=0;i<hiddenLength;i++){
	
	if(stringArray[i] == 'true'){
var select= document.getElementsByName("select["+i+"]");
select[0].checked = Boolean(stringArray[i]);
select[0].disabled = true;

}
		
		
}

var check= document.getElementById("hiddenTxnRef").value;
if(check!=''){
	var aa = document.getElementById('form');
	for ( var i = 0; i < aa.elements.length-8; i++) {
		
		aa.elements[i].disabled = true;
	}
}	
</script>
</div>
</div>
</div>
<div id="contentPaneBottom"></div>
