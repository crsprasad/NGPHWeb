<%@page import="com.opensymphony.xwork2.ActionContext"%>
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<%@taglib uri="http://displaytag.sf.net" prefix="display"%>

<s:head theme="simple" />

<script type="text/javascript">
function callSearchEventID(Action) {

	var eventID= document.getElementById("eventID").value;
	//alert("acount field :- " +accountNoField);
	
		//alert("null");
		window.open(
				"<s:url action='showEventIDSearchScreen' windowState='EXCLUSIVE' />",
				'mywindow', 'top=50,left=250,width=750,height=410');
	
		
		
	}


function doGetCaretPosition (ctrl) {
	var CaretPos = 0;   // IE Support
	if (document.selection) {
	ctrl.focus ();
	var Sel = document.selection.createRange ();
	Sel.moveStart ('character', -ctrl.value.length);
	CaretPos = Sel.text.length;
	}
	// Firefox support
	else if (ctrl.selectionStart || ctrl.selectionStart == '0')
	CaretPos = ctrl.selectionStart;
	return (CaretPos);
	}
function getposition()
{
	

    var txt1=document.getElementById("eventDescription");

    var currentRange=document.selection.createRange();  
    var workRange=currentRange.duplicate();
    txt1.select();
    var allRange=document.selection.createRange();
    var len=0;  

    while(workRange.compareEndPoints("StartToStart",allRange)>0)  
    {  
      workRange.moveStart("character",-1);  
      len++;  
    }  

    currentRange.select();  
    document.getElementById("cursorPostion").value= len;
   
    
    
    
}
function callSubmitForPosition()
{
	
	document.forms[0].action = "getStringAppender";
	document.forms[0].submit();
	}
	function callDoOperation()
	{
		document.getElementById("saveAction").value = 'Save';
		document.forms[0].action = "eventManagerSubmit";
		document.forms[0].submit();
}
	function callPopulate()
	{
		document.forms[0].action="populateDate";
		document.forms[0].submit();
	}
	function callSubmit(saveAction)
	{
		var aa = document.getElementById('form');
		for ( var i = 0; i < aa.length; i++) {
			//alert(aa.elements[i].value)
			
			aa.elements[i].disabled = false;
		}
		document.getElementById("saveAction").value = saveAction;
		document.forms[0].action="eventManagerSubmit";
		document.forms[0].submit();
	}
	function cancel() {
		document.forms[0].action = "genericScreenFetchValues";
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
		document.forms[0].action="callSeeOldDataEventManager";
		document.forms[0].submit();
	}

</script>
<sx:head parseContent="true" />
<s:head />
<div id="contentPane">
<div id="contentPaneTop"></div>
<div id="contentPaneFluid">
<div id="contentPaneInternal"><span class="crumbs"><a
	href="#">Maintenance</a>&nbsp;>&nbsp;Event Master </span></div>
<div id="content">
<h1>Event Master</h1>
<div id="content">
<s:form method="post" id="form">
<s:hidden name="old_NewScreen" id="old_NewScreen"></s:hidden>
<s:hidden name="txnRef" id="txnRef"></s:hidden>
<s:hidden name="cursorPostion" id="cursorPostion" ></s:hidden>
<s:hidden id="saveAction" name="saveAction"></s:hidden>
					<s:hidden id="hiddenTxnRef" name="hiddenTxnRef"></s:hidden>
<div><s:label name="message"></s:label><s:if test="hasFieldErrors()">
				<div class="errorMsg"><span class="errorMsgInline"><s:actionerror /><s:fielderror /></span></div>
			</s:if></div><!--Error Msg Div Ends -->
	<table width="100%" cellpadding="0" cellspacing="0" border="0">
		<tr>
			<td>
			<table width="95%" cellpadding="5" cellspacing="1" border="0" bgcolor="#c5d6f0" id="table1">
				<tr>
			<td width="20%" class="textRight"><s:label
						value="Event ID"></s:label>:&nbsp;</td>
					<td width="30%" class="text">
					<s:textfield name="eventID" id="eventID" cssClass="txtField" readonly="true"></s:textfield>
					<input type="button" value="Search Event ID"
						onclick="callSearchEventID('EventID')" id ="btnSearch" class="btn" />
					</td>
					<td width="20%" class="textRight"><s:label
						value="Canonical values"></s:label>:&nbsp;</td>
					<td width="30%" class="text"><s:select list="#session.cannonical" name="canonical" id="canonical" onchange="callSubmitForPosition()" headerKey="" headerValue="Select"> </s:select>  </td>
					
	</tr>
	<tr>
	<td class="textRight"><s:label value="Event Description"></s:label> </td>
	<td colspan="3" class="text"><s:textarea name="eventDescription" id="eventDescription" style="width:70%;heigth:40px;" rows="5" onclick="getposition('eventDescription')"></s:textarea> </td>
	</tr>
	
	<tr>
			<td width="20%" class="textRight"><s:label
						value="Is Alertable"></s:label>:&nbsp;</td>
					<td width="30%" class="text">
					<s:checkbox name="isAlertable" id="isAlertable"></s:checkbox>
					
					</td>
					<td width="20%" class="textRight"><s:label
						value="Alert For"></s:label>:&nbsp;</td>
					<td width="30%" class="text"><s:radio list="#{'U':'User','C':'Customer'}" name="alertFor" id="alertFor"></s:radio> </td>
					
	</tr>
	<tr>
			<td width="20%" class="textRight"><s:label
						value="Alert To"></s:label>:&nbsp;</td>
					<td width="30%" class="text">
					<s:select list="#session.userList" name="alertTo" id="alertTo"></s:select>
					
					</td>
					<td width="20%" class="textRight"><s:label
						value="Alert Type"></s:label>:&nbsp;</td>
					<td width="30%" class="text"><s:radio list="#{'M':'Mobile','E':'Email'}" name="alerttype" id="alerttype"></s:radio> </td>
					
	</tr>
	<tr>
	<td class="textRight"><s:label value="Alert Consolidation"></s:label> </td>
	<td colspan="3" class="text">
	<s:checkbox name="alert_consolidate" id="alert_consolidate"></s:checkbox>
	</td>
	</tr>
	
			</table></td></tr>
			<tr><td class="textCenter"><br />
			<s:if test="%{checkForSubmit!='Display_Approve_Reject'}">
			<input type="button" onclick="callDoOperation()" value="Submit">
			
			&nbsp;&nbsp;<input type="reset" value="Cancel"  class="btn" /></s:if>
			<s:if test="%{checkForSubmit=='Display_Approve_Reject'}">
			<s:if test="%{validUserToApprove}">
			<input type="button" value="Approve" onclick="callSubmit('Approve')" id="approveBtn">
			<input type="button" value="Reject" onclick="callSubmit('Reject')" id="rejectBtn">
				<s:if test="%{flagForNewData != 'flagForNewData'}">
						<input type="button" value="Old Data" onclick="callSeeOldData('OLD')" >
				</s:if>
				<s:if test="%{flagForNewData == 'flagForNewData'}">
						<input type="button" value="New Data" onclick="callSeeOldData('NEW')" >
				</s:if>
			</s:if>
			<s:if test="%{!validUserToApprove}">
				<input type="button" value="Approve" onclick="callAction('Approve')" disabled="disabled">
				<input type="button" value="Reject" onclick="callAction('Reject')" disabled="disabled">
				<s:if test="%{flagForNewData != 'flagForNewData'}">
						<input type="button" value="Old Data" onclick="callSeeOldData('OLD')" disabled="disabled">
				</s:if>
				<s:if test="%{flagForNewData == 'flagForNewData'}">
						<input type="button" value="New Data" onclick="callSeeOldData('NEW')" disabled="disabled">
				</s:if>
			</s:if>
			<input type="button" value="Cancel" onclick="cancel()">	</s:if>
			</td></tr>
			</table>


</s:form>
</div>
<script type="text/javascript">
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
	
var check= document.getElementById("hiddenTxnRef").value;
if(check!=''){
	var aa = document.getElementById('form');
	for ( var i = 0; i < aa.length-5; i++) {
		//alert(aa.elements[i].value)
		if(aa.elements[i].value!='Approve' || aa.elements[i].value!='Reject')
		aa.elements[i].disabled = true;
	}
}

</script>

</div>

</div>

</div>

<div id="contentPaneBottom"></div>
