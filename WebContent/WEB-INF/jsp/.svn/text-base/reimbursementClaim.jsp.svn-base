
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


function limitText(limitField, limitCount, limitNum) {
	if (limitField.value.length > limitNum) {
		limitField.value = limitField.value.substring(0, limitNum);
	} else {
		limitCount.value = limitNum - limitField.value.length;
	}
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
	
	
	document.forms[0].action="displayReimbursementClaimData";
	document.forms[0].submit();
}

function callCommentPOPUp()
{
	var repair = document.getElementById("repair").value;
//alert("Repair :  - "+repair);
	if(repair!="")
		{
		var comment  = document.getElementById("comment").value; 
		if(comment==""){
		window
		.open(
				"<s:url action='showRepairLetterOFCreditPopup' windowState='EXCLUSIVE' />",
				'mywindow', 'top=500,left=250,width=750,height=410');
			}else{
					callSubmitForScreen();
				}
		}
	else{
		//alert("hello")
		callSubmitForScreen();	
		}
	
}
function callSubmitForScreen()
{
	//alert("callSubmitForScreen")
	document.forms[0].action="getReimbursementClaimApproval" ;
	document.forms[0].submit();
}
function callSubmit(saveAction)
{
	var aa = document.getElementById('form');
	for ( var i = 0; i < aa.length; i++) {
		aa.elements[i].disabled = false;
	}
	document.getElementById("saveAction").value = saveAction;
	document.forms[0].action="saveReimbursementClaim";
	document.forms[0].submit();
}
function callSearchIFSC(Action,flag) {

	document.getElementById("searchAction").value = Action;
	document.getElementById("IFSCFLAG").value = flag;
window.open(
		"<s:url action='showIFSCScreen' windowState='EXCLUSIVE' />",
		'mywindow', 'top=50,left=250,width=750,height=410');
}
function callAdvise() {
	var adviseThroughBankCode = document
			.getElementById("adviseThroughBankCode").value;
	var adviseThroughBankLocation = document
			.getElementById("adviseThroughBankLocation").value;
	var adviseThroughBankName = document
			.getElementById("adviseThroughBankName").value;

	//alert("adviseThroughBankCode : - "+adviseThroughBankCode +" adviseThroughBankLocation : - "+adviseThroughBankLocation+ " adviseThroughBankName:- "+adviseThroughBankName)
	if (adviseThroughBankCode != "") {
		document.getElementById("adviseThroughBankLocation").disabled = true;
		document.getElementById("adviseThroughBankName").disabled = true;
	} else if (adviseThroughBankLocation != "") {
		document.getElementById("adviseThroughBankCode").disabled = true;
		document.getElementById("btnadviceThrough").disabled = true;
		document.getElementById("adviseThroughBankName").disabled = true;
	} else if (adviseThroughBankName != "") {
		document.getElementById("adviseThroughBankCode").disabled = true;
		document.getElementById("btnadviceThrough").disabled = true;
		document.getElementById("adviseThroughBankLocation").disabled = true;
	} else {
		document.getElementById("adviseThroughBankCode").disabled = false;
		document.getElementById("adviseThroughBankLocation").disabled = false;
		document.getElementById("adviseThroughBankName").disabled = false;
		document.getElementById("btnadviceThrough").disabled = false;
	}

}
function callNegotidvise()
{
	var negotiatingBankCode = document.getElementById("negotiatingBankCode").value;
	var negotiatingBankNameAndAddress = document.getElementById("negotiatingBankNameAndAddress").value;
	if(negotiatingBankCode!='')
		{
			
			document.getElementById("negotiatingBankNameAndAddress").disabled = true;
		}
	else if(negotiatingBankNameAndAddress!='')
		{
			document.getElementById("negotiatingBankCode").disabled = true;	
		document.getElementById("negotiationSearch").disabled = true;		
		}
	else
		{
		document.getElementById("negotiatingBankNameAndAddress").disabled = false;
		document.getElementById("negotiatingBankCode").disabled = false;
		document.getElementById("negotiationSearch").disabled = false;
		
		}
	
}
</script>
<sx:head parseContent="true" />
<s:head />
<div id="contentPane">
<div id="contentPaneTop"></div>
<div id="contentPaneFluid">
<div id="contentPaneInternal"><span class="crumbs"><a
	href="#">Outbound Payments</a>&nbsp;>&nbsp;Reimbursement Claim</span></div>
<div id="content">
<h1>Reimbursement Claim</h1>
<s:form method="post" id="form">
<s:hidden id="repairComments" name="repairComments"></s:hidden> 
<s:hidden id="searchAction" name="searchAction"></s:hidden>	
<s:hidden name="rowTodelete" id="rowTodelete"></s:hidden>
<s:hidden name="repair" id="repair"></s:hidden>
<s:hidden name="IFSCFLAG" id="IFSCFLAG" ></s:hidden>
<s:hidden id="flagForScreen" name="flagForScreen"></s:hidden>

<s:hidden id="msgHost" name="msgHost"> </s:hidden>
<s:hidden id="seqNo" name="seqNo"></s:hidden>
<s:hidden id="comment" name="comment"></s:hidden>
<s:hidden id="saveAction" name="saveAction"></s:hidden>
	<s:hidden id="hiddenTxnRef" name="hiddenTxnRef"></s:hidden>
	<s:hidden id="msgRef" name="msgRef"></s:hidden>
	<s:hidden id="msgStatusForLcFetch" name="msgStatusForLcFetch"></s:hidden>
<s:hidden id="msgDirection" name="msgDirection" value="I"></s:hidden>
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
			<td width="20%" class="textRight"><s:label	value="%{getText('label.claimingBankReference')}"></s:label>:<span	class="mandatory">*</span></td>
			<td width="30%" class="text"><s:textfield name="lcNumber" id="lcNumber" maxLength="16" onKeyPress='return notAllowCheck(this,event);'></s:textfield><input type="button" value="Fetch" onclick="callFetchLCDetails('4,6')"> </td>
			<s:if test="%{repair=='REPAIR'}">
			<td width="20%" class="textRight"><s:label	value="%{getText('label.DateofIssue')}"></s:label>:<span	class="mandatory">*</span></td>
			<td width="30%" class="text"><sx:datetimepicker id="issueDate" name="issueDate" cssClass="txtField"  displayFormat="MM/dd/yyyy" type="date"  ></sx:datetimepicker> </td>
			</s:if>
			<s:if test="%{repair!='REPAIR'}">
			<td width="20%" class="textRight"><s:label	value="%{getText('label.DateofIssue')}"></s:label>:</td>
			<td width="30%" class="text"><s:textfield id="issueDate" name="issueDate" readonly="true"></s:textfield> </td>
			</s:if>
		</tr>
		<s:if test="%{repair=='REPAIR'}">
		<tr>
			<td width="20%" class="textRight"><s:label	value="%{getText('label.issuingBankPID')}"></s:label>:<span	class="mandatory">*</span></td>
			<td width="30%" class="text"><s:select list="{'C','D'}" headerKey=""  headerValue="Select PID" name="issuingBankPID" id="issuingBankPID" ></s:select> <s:textfield name="issuingBankAcc" id="issuingBankAcc" onKeyPress='return notAllowCheck(this,event);'></s:textfield> </td>
			<td width="20%" class="textRight"><s:label	value="%{getText('label.issuingBankCode')}"></s:label>:</td>
			<td width="30%" class="text"><s:textfield name="issuingBankCode" id="issuingBankCode" onKeyPress='return notAllowCheck(this,event);'></s:textfield> <input type="button" value="Search IFSC..." onclick="return callSearchIFSC('ADVISINGIFSC','PARTY')" id="btnSearch" class="btn" /></td>
		</tr>
		</s:if>
		<s:if test="%{repair!='REPAIR'}">
		<tr>
			<td width="20%" class="textRight"><s:label	value="%{getText('label.issuingBankPID')}"></s:label>:<span	class="mandatory">*</span></td>
			<td width="30%" class="text"><s:textfield name="issuingBankPID" id="issuingBankPID" readonly="true"></s:textfield> <s:textfield name="issuingBankAcc" id="issuingBankAcc" readonly="true"></s:textfield> </td>
			<td width="20%" class="textRight"><s:label	value="%{getText('label.issuingBankCode')}"></s:label>:</td>
			<td width="30%" class="text"><s:textfield name="issuingBankCode" id="issuingBankCode" readonly="true"></s:textfield> </td>
		</tr>
		</s:if>
		<tr>
		<s:if test="%{repair==''}">
			<td width="20%" class="textRight"><s:label	value="%{getText('label.issuingBankNameAndAddress')}"></s:label>:</td>
			<td width="30%" class="text"><s:textfield name="issunigBankNameAndAddress" id="issunigBankNameAndAddress" readonly="true"></s:textfield> </td>
		</s:if>
		<s:if test="%{repair!=''}">
			<td width="20%" class="textRight"><s:label	value="%{getText('label.issuingBankNameAndAddress')}"></s:label>:</td>
			<td width="30%" class="text"><s:textfield name="issunigBankNameAndAddress" id="issunigBankNameAndAddress" onKeyPress='return notAllowCheck(this,event);'></s:textfield> </td>
		</s:if>
		
			<td width="20%" class="textRight"><s:label	value="%{getText('label.PrincipalAmountClaimed')}"></s:label>:<span	class="mandatory">*</span></td>
			<td width="30%" class="text"><s:textfield name="lcAmount" id="lcAmount" onKeyPress='return notAllowCheck(this,event);'></s:textfield> </td>
		</tr>
		<tr>
			<td width="20%" class="textRight"><s:label	value="%{getText('label.PrincipalAmountClaimedDate')}"></s:label>:</td>
			<td width="30%" class="text"><sx:datetimepicker	name="msgValueDate" cssClass="txtField" id="msgValueDate" displayFormat="MM/dd/yyyy" type="date" /> </td>
			<td width="20%" class="textRight"><s:label	value="%{getText('label.ExcessAmount')}"></s:label>:</td>
			<td width="30%" class="text"><s:textfield name="lcNetAmtClaimed" id="lcNetAmtClaimed" onKeyPress='return notAllowCheck(this,event);'></s:textfield></td>
		</tr>
		</table>
	</s:div> <!-- Group One Div Ends --></div>
	</div>
	</div>
	<br />
	<div id="CollapsiblePanel2" class="CollapsiblePanel">
	<div class="CollapsiblePanelTab"><!--  <img src="images/openPanel.png" width="9" height="7" alt="openPanel" />-->&nbsp;</div>
	<div class="CollapsiblePanelContent">
	<div><!-- Group Two Div Starts --> <s:div id="mandatory"
		cssClass="dataGrid" style="width:100%;">
		<table width="100%" cellpadding="5" cellspacing="1" border="0"
			bgcolor="#ffffff">
			<tr>
			<td width="20%" class="textRight"><s:label
					value="%{getText('label.ChargeDetails')}"></s:label>:</td>
			<td width="30%" class="text"><s:textarea name="chargeDetails" id="chargeDetails" rows="6" cols="35" wrap="HARD" onKeyPress='return maxLength(this,"209","chargeDetails","35",event);'></s:textarea> </td>
				<td width="20%" class="textRight"><s:label	value="%{getText('label.TotalAmountClaimed')}"></s:label>:<span	class="mandatory">*</span></td>
			<td width="30%" class="text"><s:textfield name="totalAmountClaimed" id="totalAmountClaimed" onKeyPress='return notAllowCheck(this,event);'></s:textfield></td>
			</tr>
			
			<tr>
			<td width="20%" class="textRight"><s:label
					value="%{getText('label.adviseThroughBankpartyidentifier')}"></s:label>:</td>
			<td width="30%" class="text"><s:select list="{'C','D'}" headerKey=""  headerValue="Select PID" name="adviseThroughBankpartyidentifier" id="adviseThroughBankpartyidentifier"></s:select>
			<s:textfield name="adviseThroughBankAcc" id="adviseThroughBankAcc" maxLength="34" onKeyPress='return notAllowCheck(this,event);'></s:textfield> </td>
			<td width="20%" class="textRight"><s:label	value="%{getText('label.adviseThroughBankCode')}"></s:label>:</td>
			<td width="30%" class="text"><s:textfield name="adviseThroughBankCode" id="adviseThroughBankCode" onkeyup="callAdvise()" onKeyPress='return notAllowCheck(this,event);'></s:textfield> 
			<input type="button" value="Search IFSC..." 
					onclick="return callSearchIFSC('ADVICETHOUGHIFSC','PARTY')" id="btnadviceThrough"
					class="btn" />
			</td>
		</tr>
		<tr>
			<td width="20%" class="textRight"><s:label
					value="%{getText('label.adviseThroughBankLocation')}"></s:label>:</td>
			<td width="30%" class="text"><s:textfield name="adviseThroughBankLocation" id="adviseThroughBankLocation" onkeyup="callAdvise()" onKeyPress='return notAllowCheck(this,event);'></s:textfield> </td>
			<td width="20%" class="textRight"><s:label	value="%{getText('label.adviseThroughBankNameAddress')}"></s:label>:</td>
			<td width="30%" class="text"><s:textarea name="adviseThroughBankName" id="adviseThroughBankName" rows = "4" cols="35" wrap="HARD" onkeyup="callAdvise()" onKeyPress='return maxLength(this,"139","adviseThroughBankName","35",event);'></s:textarea> </td>
		</tr>
		
		<tr>
			<td width="20%" class="textRight"><s:label value="%{getText('label.NegotiatingBankPartyIdentifier')}"></s:label>:</td>
			<td width="30%" class="text"><s:select list="{'C','D'}" name="negotiatingBankPartyIdentifier" id="negotiatingBankPartyIdentifier" headerKey="" headerValue="Select PID" ></s:select><a>
				</a><s:textfield name="negotiatingBankAccount" id="negotiatingBankAccount" maxLength="34" onKeyPress='return notAllowCheck(this,event);'></s:textfield> </td>
			<td width="20%" class="textRight"><s:label value="%{getText('label.NegotiatingBankNameAndAddress')}"></s:label>:</td>
			<td width="30%" class="text"><s:textarea name="negotiatingBankNameAndAddress" id="negotiatingBankNameAndAddress" rows="4" cols="35" wrap="HARD" onKeyPress='return maxLength(this,"139","negotiatingBankNameAndAddress","35",event);' onkeyup="callNegotidvise()"></s:textarea> </td>
		</tr>
		<tr>	
			<td width="20%" class="textRight"><s:label value="%{getText('label.NegotiatingBankCode')}"></s:label>:</td>
			<td width="30%" class="text"><s:textfield name="negotiatingBankCode" id="negotiatingBankCode" maxLength="11" onkeyup="callNegotidvise()" onKeyPress='return notAllowCheck(this,event);'></s:textfield>
			<input type="button" value="Search IFSC..."	onclick="callSearchIFSC('NEGOTIATIONIFSC','PARTY')" id ="negotiationSearch" class="btn" /></td>
			<td width="20%" class="textRight"><s:label	value="%{getText('label.SendertoReceiverInformation')}"></s:label>:</td>
				<td width="30%" class="text"><s:textarea name="sendertoReceiverInformation" id="sendertoReceiverInformation" rows="6" cols="35" onKeyPress='return maxLength(this,"209","sendertoReceiverInformation","35",event);'></s:textarea> </td>
		</tr>
		</table>
	</s:div> <!-- Group Two Div Ends --></div>
	</div>
	</div>
	
	<div class="clearfix"></div>
	<table width="100%" cellpadding="0" cellspacing="0" border="0">
		<tr>
			<td>
			
					<s:if test="%{checkForSubmit!='Display_Approve_Reject'}">
					<s:if test="%{flagMarked!='flagMarked'}">
						<input type="button" value="Submit" class="btn" onclick="callCommentPOPUp()"/>
					</s:if>
					<s:if test="%{flagMarked=='flagMarked'}">
						<input type="button" value="Mark As Acknowledgement" onclick="callAuthorize()">
					</s:if>		
				<input type="reset" value="Cancel" onclick="cancel()" class="btn"  />
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
	for ( var i = 0; i < aa.length-3; i++) {
		//alert(aa.elements[i].value)
		if(aa.elements[i].value!='Approve' || aa.elements[i].value!='Reject')
		aa.elements[i].disabled = true;
	}
	document.getElementById("lcExpiryDate").disabled= true;
	document.getElementById("latestDateofShipment").disabled = true;
}

</script>
		