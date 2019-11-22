
<%@page import="com.opensymphony.xwork2.ActionContext"%>
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<link href="SpryAssets/SpryCollapsiblePanel.css" rel="stylesheet"
	type="text/css" />
<script src="SpryAssets/SpryCollapsiblePanel.js" type="text/javascript"></script>
<script src="js/FormField.js" type="text/javascript"></script>
<script type="text/javascript">


function callSearchIFSC(Action,flag) {

	document.getElementById("searchAction").value = Action;
	document.getElementById("IFSCFLAG").value = flag;
		window.open(
		"<s:url action='showIFSCScreen' windowState='EXCLUSIVE' />",
		'mywindow', 'top=50,left=250,width=750,height=410');
}


function callCommentPOPUp()
{
	var repair = document.getElementById("repair").value;
//alert("Repair :  - "+repair);
	if(repair!="")
	{
	//	var comment  = document.getElementById("comment").value; 
	//	if(comment==""){
	//	window
	//	.open(
		//		"<s:url action='showRepairLetterOFCreditPopup' windowState='EXCLUSIVE' />",
		//		'mywindow', 'top=500,left=250,width=750,height=410');
		//	}else{
					callSubmitForScreen();
		//		}
		}
	else{
		callSubmitForScreen();	
		}
	
}
function callSubmitForScreen()
{
	//alert("callSubmitForScreen")
	document.forms[0].action="getAdviseOfDischargeApproval" ;
	document.forms[0].submit();
}
function callSubmit(saveAction)
{
	var aa = document.getElementById('form');
	for ( var i = 0; i < aa.length; i++) {
		aa.elements[i].disabled = false;
	}
	alert("hello")
	document.getElementById("saveAction").value = saveAction;
	document.forms[0].action="saveAdviceOFDischarge";
	document.forms[0].submit();
}
function callFetchLCDetails(status)
{
	var lcNumber= document.getElementById("lcNumber").value;
	document.getElementById("msgStatusForLcFetch").value= status;

	if(lcNumber==""){
		window.open(
				"<s:url action='transferDocumentarySearchLCNo' windowState='EXCLUSIVE' />",
				'mywindow', 'top=50,left=250,width=750,height=410');
	}
	else
		{
		
		callShowGrid();
		}
		
	}

function callShowGrid()
{
	document.forms[0].action="displayAdviseOfDischargeDate";
	document.forms[0].submit();
}
</script>
<sx:head parseContent="true" />
<s:head />
<div id="contentPane">
<div id="contentPaneTop"></div>
<div id="contentPaneFluid">
<div id="contentPaneInternal"><span class="crumbs"><a
	href="#">Outbound Payments</a>&nbsp;>&nbsp;Advise Of Discharge</span></div>
<div id="content">
<h1>Advise Of Discharge</h1>
 <s:form method="post" id="form">
<s:hidden id="searchAction" name="searchAction"></s:hidden>	
<s:hidden id="msgRef" name="msgRef"></s:hidden>
<s:hidden name="repair" id="repair"></s:hidden>
<s:hidden name="IFSCFLAG" id="IFSCFLAG" ></s:hidden>
<!--<s:hidden id="flagForScreen" name="flagForScreen"></s:hidden>
<s:hidden id="issueDate" name="issueDate"></s:hidden>
<s:hidden name="rowTodelete" id="rowTodelete"></s:hidden>
<s:hidden id="msgHost" name="msgHost"> </s:hidden>
<s:hidden id="seqNo" name="seqNo"></s:hidden>
--><s:hidden id="comment" name="comment"></s:hidden>
<s:hidden id="saveAction" name="saveAction"></s:hidden>
	<s:hidden id="hiddenTxnRef" name="hiddenTxnRef"></s:hidden>
<s:hidden id="msgStatusForLcFetch" name="msgStatusForLcFetch"></s:hidden>	
<s:hidden id="msgDirection" name="msgDirection" value="O"></s:hidden>
<s:hidden id="validUserToApprove" name="validUserToApprove"></s:hidden>

	<!--Error Msg Div Starts -->
	<div><s:if test="hasFieldErrors()">
				<div class="errorMsg"><span class="errorMsgInline"><s:actionerror /><s:fielderror /></span></div>
			</s:if></div><!--Error Msg Div Ends -->
	<!-- Collapsible Panels Start-->
	<div id="CollapsiblePanel1" class="CollapsiblePanel">
	<div class="CollapsiblePanelTab"><!--  <img src="images/openPanel.png" width="9" height="7" alt="openPanel" />-->&nbsp;Basic</div>
	<div class="CollapsiblePanelContent">
	<div><!-- Group One Div Starts --> 
	<s:div id="main"	cssClass="dataGrid" style="width:100%;">
	<table width="100%" cellpadding="3" cellspacing="1" border="0"	bgcolor="#ffffff">
	<tr>	
			<td width="20%" class="textRight"><s:label	value="%{getText('label.receiverBank')}"></s:label>:<span	class="mandatory">*</span></td>
			<td width="30%" class="text"><s:textfield name="advisingBank" id="advisingBank" maxLength="11" onKeyPress='return notAllowCheck(this,event);'> </s:textfield><input type="button" value="Search IFSC..."
					onclick="return callSearchIFSC('ADVISINGIFSC','BOTH')" id="btnSearch"
					class="btn" /></td>
		<s:if test="%{repair!='REPAIR'}">
			<td width="20%" class="textRight"><s:label	value="%{getText('label.SenderTRN')}"></s:label>:<span	class="mandatory">*</span></td>
			<td width="30%" class="text"><s:textfield name="lcNumber" id="lcNumber" maxLength="16" onKeyPress='return notAllowCheck(this,event);'></s:textfield><input type="button" value="Fetch" onclick="callFetchLCDetails('4,6')"> </td>
		</s:if>
		<s:if test="%{repair=='REPAIR'}">
			<td width="20%" class="textRight"><s:label	value="%{getText('label.SenderTRN')}"></s:label>:<span	class="mandatory">*</span></td>
			<td width="30%" class="text"><s:textfield name="lcNumber" id="lcNumber" maxLength="16" readonly="true"></s:textfield></td>
		</s:if>		
	</tr>
	<tr>
			<td width="20%" class="textRight"><s:label	value="%{getText('label.PresentingBanksReference')}"></s:label>:<span	class="mandatory">*</span></td>
			<td width="30%" class="text"><s:textfield name="presentingBanksReference" id="presentingBanksReference" onKeyPress='return notAllowCheck(this,event);'></s:textfield></td>	
			<td width="20%" class="textRight"><s:label	value="%{getText('label.DateofAmendment')}"></s:label>:</td>
			<td width="30%" class="text"><sx:datetimepicker name="amendmentDate" cssClass="txtField" id="amendmentDate"  displayFormat="MM/dd/yyyy" type="date" /> </td>
	</tr>
	<tr>
		<td width="20%" class="textRight"><s:label	value="%{getText('label.Utilisation')}"></s:label>:<span	class="mandatory">*</span></td>
		<td width="30%" class="text">
			<table width="100%" cellpadding="0" cellspacing="0" style="border-width: 10"	bgcolor="#ffffff">
				<tr>
					<td width="10%" ><s:label	value="%{getText('label.currency')}"></s:label>:</td>
					<td width="30%" class="text">
						<s:select list="{'EUR','GBS','INR','USD'}" name="lcCurrency" id="lcCurrency" headerKey="" headerValue="Select Currency"  ></s:select>
					</td>
				</tr>
				<tr>
					<td width="10%" ><s:label	value="%{getText('label.Amount')}"></s:label>:</td>
					<td width="30%" class="text">
						<s:textfield name="lcAmount" id="lcAmount" onKeyPress='return notAllowCheck(this,event);'></s:textfield>
					</td>
				</tr>
			</table>
			</td>
		<td width="20%" class="textRight"><s:label	value="%{getText('label.SendertoReceiverInformation')}"></s:label>:</td>
		<td width="30%" class="text"><s:textarea name="SendertoReceiverInformation" id="SendertoReceiverInformation" cols="35" rows="4" onKeyPress='return maxLength(this,"209","SendertoReceiverInformation","35",event);'></s:textarea> </td>
		</tr>
	</table>
	</s:div> <!-- Group One Div Ends --></div>
	</div>
	</div>
	<br />
	<div class="clearfix"></div>
	<table width="100%" cellpadding="0" cellspacing="0" border="0">
		<tr>
			<td>
		  	<s:if test="%{checkForSubmit!='Display_Approve_Reject'}">  
				<input type="button" onclick="callCommentPOPUp()" value="Submit"  
					class="btn" />
					<input type="reset" value="Cancel" onclick="cancel()" class="btn"  />
			</s:if> 
			<s:if test="%{checkForSubmit=='Display_Approve_Reject'}">
				<s:if test="%{validUserToApprove}">
					<input type="button" value="Approve" onclick="callSubmit('Approve')">
					<input type="button" value="Reject" onclick="callSubmit('Reject')">
					<input type="button" value="Cancel" onclick="cancel()">
				</s:if>
				<s:if test="%{!validUserToApprove}">
					<input type="button" onclick="callCommentPOPUp()" value="Submit"  class="btn" />
					<input type="reset" value="Cancel" onclick="cancel()" class="btn"  />
				</s:if>
			</s:if>  
			</td>
		</tr>
	</table>	
	
	</s:form></div>
</div>
</div>
<div id="contentPaneBottom"></div>
<script type="text/javascript">
	var CollapsiblePanel1 = new Spry.Widget.CollapsiblePanel(
			"CollapsiblePanel1");
	var CollapsiblePanel2 = new Spry.Widget.CollapsiblePanel(
			"CollapsiblePanel2");
	var CollapsiblePanel3 = new Spry.Widget.CollapsiblePanel(
			"CollapsiblePanel3");
	var CollapsiblePanel4 = new Spry.Widget.CollapsiblePanel(
			"CollapsiblePanel4");
</script>
<script type="text/javascript">
var check= document.getElementById("hiddenTxnRef").value;
if(check!=''){
	var aa = document.getElementById('form');
	var isValidUser = document.getElementById("validUserToApprove").value;
		if(isValidUser=="true")
		{
			for ( var i = 0; i < aa.length-3; i++) 
				{
					aa.elements[i].disabled = true;
					document.getElementById("lcExpiryDate").disabled= true;
					document.getElementById("latestDateofShipment").disabled = true;
				}
			
		}
		else
		{
			for ( var i = 0; i < aa.length-3; i++) 
			{
				aa.elements[i].enabled = true;
				document.getElementById("lcExpiryDate").enabled= true;
				document.getElementById("latestDateofShipment").enabled = true;
			}
		}
}

</script>
	