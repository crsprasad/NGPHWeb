<%@page import="com.opensymphony.xwork2.ActionContext"%>
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<%@taglib uri="http://displaytag.sf.net" prefix="display"%>

<s:head theme="simple" />
<sx:head parseContent="true" />
<script type="text/javascript">
function callFetchLCDetails()
{
	var lcNumber= document.getElementById("lcNumber").value;

	if(lcNumber==""){
		window.open(
				"<s:url action='lCNoFetch' windowState='EXCLUSIVE' />",
				'mywindow', 'top=50,left=250,width=750,height=410');
	}
	else
		{
		callShowLcRelatedValue();
		}
		
	}
function callShowLcRelatedValue()
{
	document.forms[0].action="fetchStatus";
	document.forms[0].submit();
}
function callAction(Action)
{	
	var aa = document.getElementById('form');
	for ( var i = 0; i < aa.elements.length ; i++) {
		
		aa.elements[i].disabled = false;
	}
	document.getElementById("saveAction").value= Action;
		document.forms[0].action="changeLcStatus";
		document.forms[0].submit();
}
</script>
<script type="text/javascript">
</script>

<div id="contentPane">
<div id="contentPaneTop"></div>
<div id="contentPaneFluid">
<div id="contentPaneInternal"><span class="crumbs"><a
	href="#">Outward</a>&nbsp;>&nbsp;Mark Acknowledge</span></div>
<div id="content" >
<h1>Mark Acknowledge</h1>
<s:form method="post" id="form"> 
	
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
				<td class="textRight"><s:label
						value="%{getText('label.lc_Number')}"></s:label>:&nbsp;</td>
				<td colspan="3" class="text"><s:textfield name="lcNumber" id="lcNumber" maxLength="16"></s:textfield><input type="button" value="Fetch" onclick="callFetchLCDetails()"> </td>
				</tr>
				<tr>

					<td class="textRight" width="10%"><s:label
						value="%{getText('label.currentStatus')}"></s:label>:&nbsp;</td>
					<td class="text" width="40%">
					<s:textfield name="currentStatus" id="currentStatus" readonly="true"></s:textfield> </td>
					<td width="10%" class="textRight"><s:label
						value="%{getText('label.ackStatus')}"></s:label>:&nbsp;</td>
					<td width="40%" class="text"><s:textfield name="ackStatus" id="ackStatus" cssClass="txtField" required="true" readonly="true"></s:textfield>
					</td>
	</tr>
	
	
	
	
	

			</table></td></tr>
			<tr>
			<td class="textCenter"><br />
		
			<s:if test="%{checkForSubmit!='Display_Approve_Reject'}">
			<s:submit value="Submit"  action="sendForApproval"></s:submit>
			</s:if>
			<s:if test="%{checkForSubmit=='Display_Approve_Reject'}">
			
			<s:if test="%{validUserToApprove}">
				<input type="button" value="Approve" onclick="callAction('Approve')">
				<input type="button" value="Reject" onclick="callAction('Reject')">
			</s:if>
			<s:if test="%{!validUserToApprove}">
				<input type="button" value="Approve" onclick="callAction('Approve')" disabled="disabled">
				<input type="button" value="Reject" onclick="callAction('Reject')" disabled="disabled">
			</s:if>
			</s:if>
					<input type="reset" value="Cancel" onclick="cancel()" class="btn" />

		</tr>
			
			</table>
			</td>
		</tr>
	</table>
	<s:hidden id="saveAction" name="saveAction"></s:hidden>
	<s:hidden id="hiddenTxnRef" name="hiddenTxnRef"></s:hidden>
</s:form>
<script type="text/javascript">
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
