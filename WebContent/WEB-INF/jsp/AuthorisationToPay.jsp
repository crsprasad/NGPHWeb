<%@page import="com.opensymphony.xwork2.ActionContext"%>
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<%@taglib uri="http://displaytag.sf.net" prefix="display"%>
<link href="SpryAssets/SpryCollapsiblePanel.css" rel="stylesheet"
	type="text/css" />
<script src="SpryAssets/SpryCollapsiblePanel.js" type="text/javascript"></script>
<script src="js/FormField.js" type="text/javascript"></script>
<script type="text/javascript">
function callFetchLCDetails()
{
	var lcNumber= document.getElementById("lcNumber").value;

	if(lcNumber==""){
		window.open(
				"<s:url action='searchLCAuthorisetoPay' windowState='EXCLUSIVE' />",
				'mywindow', 'top=50,left=250,width=750,height=410');
	}
	else
		{
		callShowGrid();
		}
		
	}

function callShowGrid()
{
	document.forms[0].action="displayAuthrisToPayData";
	document.forms[0].submit();
}

function callSenderAdvise()
{
	var sendersCorrespondentCode = document.getElementById("sendersCorrespondentCode").value;
	var sendersCorrespondentLocation = document.getElementById("sendersCorrespondentLocation").value;
	var sendersCorrespondentNameandAddress = document.getElementById("sendersCorrespondentNameandAddress").value;
	if(sendersCorrespondentCode!='')
		{
			document.getElementById("sendersCorrespondentLocation").disabled = true;
			document.getElementById("sendersCorrespondentNameandAddress").disabled = true;
		}
	else if(sendersCorrespondentLocation!='')
		{
			document.getElementById("sendersCorrespondentCode").disabled = true;
			document.getElementById("senderSearch").disabled = true;
			document.getElementById("sendersCorrespondentNameandAddress").disabled = true;
		}
	else if(sendersCorrespondentNameandAddress !='')
		{
			document.getElementById("sendersCorrespondentCode").disabled = true;
			document.getElementById("senderSearch").disabled = true;
			document.getElementById("sendersCorrespondentLocation").disabled = true;
		}
	else
		{
		
		document.getElementById("sendersCorrespondentCode").disabled = false;
		document.getElementById("sendersCorrespondentLocation").disabled = false;
		document.getElementById("sendersCorrespondentNameandAddress").disabled = false;
		document.getElementById("senderSearch").disabled = false;
		}
	
}

function callReceiverAdvise()
{
	var receiversCorrespondentCode = document.getElementById("receiversCorrespondentCode").value;
	var receiversCorrespondentLocation = document.getElementById("receiversCorrespondentLocation").value;
	var receiversCorrespondentNameandAddress = document.getElementById("receiversCorrespondentNameandAddress").value;
	if(receiversCorrespondentCode!='')
		{
			document.getElementById("receiversCorrespondentLocation").disabled = true;
			document.getElementById("receiversCorrespondentNameandAddress").disabled = true;
		}
	else if(receiversCorrespondentLocation!='')
		{
			document.getElementById("receiversCorrespondentCode").disabled = true;
			document.getElementById("receiveSearch").disabled = true;
			document.getElementById("receiversCorrespondentNameandAddress").disabled = true;
		}
	else if(receiversCorrespondentNameandAddress!='')
		{
			document.getElementById("receiversCorrespondentCode").disabled = true;
			document.getElementById("receiveSearch").disabled = true;
			document.getElementById("receiversCorrespondentLocation").disabled = true;
		}
	else
		{
		document.getElementById("receiversCorrespondentCode").disabled = false;
		document.getElementById("receiveSearch").disabled = false;
		document.getElementById("receiversCorrespondentLocation").disabled = false;
		document.getElementById("receiversCorrespondentNameandAddress").disabled = false;
		}
	
}
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
	var aa = document.getElementById('form');
	for ( var i = 0; i < aa.length; i++) {
		aa.elements[i].disabled = false;
		}
	document.forms[0].action="getAuthorisetoPaySubmit";
	document.forms[0].submit();
}


function callSubmit(saveAction)
{
	var aa = document.getElementById('form');
	for ( var i = 0; i < aa.length; i++) {
		aa.elements[i].disabled = false;
		}
	document.getElementById("saveAction").value = saveAction;
	document.forms[0].action="saveAuthorisationToPay";
	document.forms[0].submit();
}


</script>
<sx:head parseContent="true" />
<s:head />
<div id="contentPane">
<div id="contentPaneTop"></div>
<div id="contentPaneFluid">
<div id="contentPaneInternal"><span class="crumbs"><a
	href="#">Outbound Payments</a>&nbsp;>&nbsp;Letter Of Credit&nbsp;>&nbsp;Authorisation To Pay</span></div>
<div id="content">
<h1>Authorisation To Pay</h1>


<s:form method="post" id="form">
<s:hidden id="searchAction" name="searchAction"></s:hidden>
<s:hidden id="repairComments" name="repairComments"></s:hidden> 
<s:hidden name="repair" id="repair"></s:hidden>
<s:hidden name="IFSCFLAG" id="IFSCFLAG" ></s:hidden>
<s:hidden id="comment" name="comment"></s:hidden>
<s:hidden id="validUserToApprove" name="validUserToApprove"></s:hidden>
<div><s:if test="hasFieldErrors()">
				<div class="errorMsg"><span class="errorMsgInline"><s:actionerror /><s:fielderror /></span></div>
			</s:if></div><!--Error Msg Div Ends -->
			<div id="CollapsiblePanel1" class="CollapsiblePanel">
	<div class="CollapsiblePanelTab"><!--  <img src="images/openPanel.png" width="9" height="7" alt="openPanel" />-->&nbsp;Basic Details</div>
	<div class="CollapsiblePanelContent">
	<div><!-- Group One Div Starts --> 
	<s:div id="main"	cssClass="dataGrid" style="width:100%;">
	<table width="100%" cellpadding="3" cellspacing="1" border="0"	bgcolor="#ffffff">
		
		<tr>
			<s:if test="%{repair!=''}">
				<td width="20%" class="textRight"><s:label value="%{getText('label.lc_Number')}"></s:label>:<span class="mandatory">*</span></td>
				<td width="30%" class="text"><s:textfield name="lcNumber" id="lcNumber" maxLength="16" onKeyPress='return notAllowCheck(this,event);' ></s:textfield></td>
			</s:if>
			<s:if test="%{repair==''}">
				<td width="20%" class="textRight"><s:label value="%{getText('label.lc_Number')}"></s:label>:<span class="mandatory">*</span></td>
				<td width="30%" class="text"><s:textfield name="lcNumber" id="lcNumber" maxLength="16" onKeyPress='return notAllowCheck(this,event);' ></s:textfield><input type="button" value="Fetch" onclick="callFetchLCDetails()"></td>
			</s:if>
		
				<td width="20%" class="textRight"><s:label value="%{getText('label.PresentingBanksReference')}"></s:label>:<span class="mandatory">*</span></td>
				<td width="30%" class="text"><s:textfield name="presentingBanksReference" id="presentingBanksReference" maxLength="35" onKeyPress='return notAllowCheck(this,event);'></s:textfield> </td>
			</tr>
			<tr>	
				<td width="20%" class="textRight"><s:label	value="%{getText('label.currency')}"></s:label>:<span class="mandatory">*</span></td>
				<td width="30%" class="text"><s:textfield  name="lcCurrency" id="lcCurrency" readonly="true"></s:textfield></td> 
				<td width="20%" class="textRight"><s:label	value="%{getText('label.FurtherIdentification')}"></s:label>:<span class="mandatory">*</span></td>				
				<td width="30%" class="text"><s:textfield name="lcPrevAdvRef" id="lcPrevAdvRef" maxLength="16" onKeyPress='return notAllowCheck(this,event);'></s:textfield> </td>
			</tr>
			<tr>
				<td width="20%" class="textRight"><s:label value="%{getText('label.TotalAmountAdvised')}"></s:label>:<span class="mandatory">*</span></td>
				<td width="30%" class="text"><s:textfield name="creditAmount" id="creditAmount" maxLength="15" onKeyPress='return notAllowCheck(this,event);'></s:textfield></td>
				<td width="20%" class="textRight"><s:label value="%{getText('label.DateofAdviceofDiscrepancy')}"></s:label>:<span class="mandatory">*</span></td>
				<td width="30%" class="text"><sx:datetimepicker name="amendmentDate"	 cssClass="txtField" id="amendmentDate"  displayFormat="MM/dd/yyyy" type="date" /> </td>
			</tr>
				
			</table>
	</s:div>
	</div>
	</div>
	</div>					
	</br>
	<div id="CollapsiblePanel2" class="CollapsiblePanel">
	<div class="CollapsiblePanelTab"><!--  <img src="images/openPanel.png" width="9" height="7" alt="openPanel" />-->&nbsp;Other Details</div>
	<div class="CollapsiblePanelContent">
	<div><!-- Group Two Div Starts --> 
	<s:div id="mandatory"	cssClass="dataGrid" style="width:100%;">
	<table width="100%" cellpadding="3" cellspacing="1" border="0"	bgcolor="#ffffff">
		
		<tr>
			<td width="20%" class="textRight"><s:label value="%{getText('label.NetAmount')}"></s:label>:</td>
			<td>
				<table width="100%" cellpadding="3" cellspacing="1" border="1"	bgcolor="#ffffff">
					<s:if test="%{repair!=''}">					
					<tr>
						<td width="20%" class="textRight"><s:label value="%{getText('label.valueDate')}"></s:label>:</td>
						<td width="30%" class="text"><sx:datetimepicker id="msgValueDate" name="msgValueDate" cssClass="txtField"  displayFormat="MM/dd/yyyy" type="date"  ></sx:datetimepicker> </td>
					</tr>
					<tr>
						<td width="20%" class="textRight"><s:label value="%{getText('label.currency')}"></s:label>:</td>
						<td width="30%" class="text"><s:select list="{'EUR','GBS','INR','USD'}" name="msgCurrency" id="msgCurrency" headerKey="" headerValue="Select Currency"  ></s:select></td>
					</tr>
					</s:if>
				<s:if test="%{repair==''}">
					<tr>
						<td width="20%" class="textRight"><s:label value="%{getText('label.valueDate')}"></s:label>:</td>
						<td width="30%" class="text"><s:textfield  name="msgValueDate" id="msgValueDate" readonly="true" ></s:textfield></td> 
					</tr>
					<tr>
						<td width="20%" class="textRight"><s:label value="%{getText('label.currency')}"></s:label>:</td>
						<td width="30%" class="text"><s:textfield  name="msgCurrency" id="msgCurrency" readonly="true" ></s:textfield></td> 
					</tr>
				</s:if>
					<tr>
						<td width="20%" class="textRight"><s:label value="%{getText('label.Amount')}"></s:label>:</td>
						<td width="30%" class="text"><s:textfield name="lcNetAmtClaimed" id="lcNetAmtClaimed" maxLength="15" onKeyPress='return notAllowCheck(this,event);'></s:textfield> </td>
					</tr>
			</table>
			</td>
			<td width="20%" class="textRight"><s:label value="%{getText('label.ChargesDeducted')}"></s:label>:</td>
			<td width="30%" class="text"><s:textarea name="chargeDetails" id="chargeDetails" rows="4" cols="35" wrap="HARD" onKeyPress='return maxLength(this,"139","chargeDetails","35",event);' ></s:textarea> </td>
			
		</tr><!--
		<tr>
			<td width="20%" class="textRight"><s:label value="%{getText('label.TotalNetAmount')}"></s:label>:</td>
			<td width="30%" class="text"><s:textfield name="lcNetAmtClaimed" id="lcNetAmtClaimed" maxLength="15" ></s:textfield> </td>
			<td width="20%" class="textRight"><s:label value="%{getText('label.ChargesDeducted')}"></s:label>:</td>
			<td width="30%" class="text"><s:textarea name="chargeDetails" id="chargeDetails" rows="4" cols="35" onKeyPress='return maxLength(this,"139","chargeDetails","35");' ></s:textarea> </td>
		</tr>
			--><tr>
				<td width="20%" class="textRight"><s:label	value="%{getText('label.SendersCorrespondentPartyIdentifier')}"></s:label>:</td>
				<td width="30%" class="text"><s:select list="{'C','D'}" name="sendersCorrespondentPartyIdentifier" id="sendersCorrespondentPartyIdentifier" headerKey="" headerValue="Select PID" ></s:select><a>
				</a><s:textfield name="senderCorrespontAcount" id="senderCorrespontAcount" maxLength="34" onKeyPress='return notAllowCheck(this,event);'></s:textfield> </td>
				<td width="20%" class="textRight"><s:label value="%{getText('label.SendersCorrespondentcode')}"></s:label>:</td>
				<td width="30%" class="text"><s:textfield name="sendersCorrespondentCode" id="sendersCorrespondentCode" maxLength="11"  onkeyup="callSenderAdvise()" onKeyPress='return notAllowCheck(this,event);'></s:textfield>
				<input type="button" value="Search IFSC..."	onclick="callSearchIFSC('SENDERIFSC','PARTY')" id ="senderSearch" class="btn" /> </td>
		</tr>
		<tr>	
				<td width="20%" class="textRight"><s:label	value="%{getText('label.SendersCorrespondentLocation')}"></s:label>:</td>
				<td width="30%" class="text"><s:textfield name="sendersCorrespondentLocation" id="sendersCorrespondentLocation" maxLength="35" onkeyup="callReceiverAdvise()" onKeyPress='return notAllowCheck(this,event);'></s:textfield> </td>
				<td width="20%" class="textRight"><s:label	value="%{getText('label.SendersCorrespondentNameandAddress')}"></s:label>:</td>
				<td width="30%" class="text"><s:textarea name="sendersCorrespondentNameandAddress" id="sendersCorrespondentNameandAddress" rows="4" cols="35" wrap="HARD" onKeyPress='return maxLength(this,"139","sendersCorrespondentNameandAddress","35",event);' onkeyup="callSenderAdvise()"></s:textarea> </td>
		</tr>
	
		<tr>
				<td width="20%" class="textRight"><s:label	value="%{getText('label.ReceiversCorrespondentPartyIdentifier')}"></s:label>:</td>
				<td width="30%" class="text"><s:select list="{'C','D'}" name="receiversCorrespondentPartyIdentifier" id="receiversCorrespondentPartyIdentifier" headerKey="" headerValue="Select PID" ></s:select><a>
				</a><s:textfield name="receiverCorrespontAcount" id="receiverCorrespontAcount" maxLength="34" onKeyPress='return notAllowCheck(this,event);'></s:textfield> </td>
				<td width="20%" class="textRight"><s:label	value="%{getText('label.ReceiversCorrespondentCode')}"></s:label>:</td>
				<td width="30%" class="text"><s:textfield name="receiversCorrespondentCode" id="receiversCorrespondentCode" maxLength="11" onkeyup="callReceiverAdvise()" onKeyPress='return notAllowCheck(this,event);'></s:textfield>
				<input type="button" value="Search IFSC..."	onclick="callSearchIFSC('RECEIVERIFSC','PARTY')" id ="receiveSearch" class="btn" /> </td>
		</tr>
		
		<tr>	
				<td width="20%" class="textRight"><s:label	value="%{getText('label.ReceiversCorrespondentLocation')}"></s:label>:</td>
				<td width="30%" class="text"><s:textfield name="receiversCorrespondentLocation" id="receiversCorrespondentLocation" maxLength="35" onkeyup="callReceiverAdvise()" onKeyPress='return notAllowCheck(this,event);'></s:textfield> </td>
				<td width="20%" class="textRight"><s:label	value="%{getText('label.ReceiversCorrespondentNameandAddress')}"></s:label>:</td>
				<td width="30%" class="text"><s:textarea name="receiversCorrespondentNameandAddress" id="receiversCorrespondentNameandAddress" rows="4" cols="35" wrap="HARD" onKeyPress='return maxLength(this,"139","receiversCorrespondentNameandAddress","35",event);' onkeyup="callReceiverAdvise()"></s:textarea> </td>
		</tr>
		<tr>
				<td width="20%" class="textRight"><s:label	value="%{getText('label.SendertoReceiverInformation')}"></s:label>:</td>
				<td width="30%" class="text"><s:textarea name="sendertoReceiverInformation" id="sendertoReceiverInformation" rows="6" cols="35" wrap="HARD" onKeyPress='return maxLength(this,"209","sendertoReceiverInformation","35",event);'  ></s:textarea> </td>
		</tr>	
		</table>
	</s:div><!-- Group Two Div End --> 
	</div>
	</div>
	</div>
		<br />
	<!--  button panel starts -->
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
					<input type="button" value="Approve" onclick="callSubmit('Approve')" disabled="disabled">
					<input type="button" value="Reject" onclick="callSubmit('Reject')" disabled="disabled">
					<input type="button" value="Cancel" onclick="cancel()" disabled="disabled">
				</s:if>
			</s:if>  
			</td>
		</tr>
	</table>	
	<s:hidden id="saveAction" name="saveAction"></s:hidden>
	<s:hidden id="hiddenTxnRef" name="hiddenTxnRef"></s:hidden>	
	<s:hidden id="msgRef" name="msgRef"></s:hidden>
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
</script>
<script type="text/javascript">
var check= document.getElementById("hiddenTxnRef").value;
if(check!=''){
	var aa = document.getElementById('form');
	var isValidUser = document.getElementById("validUserToApprove").value;
		if(isValidUser=="true")
		{
			for ( var i = 0; i < aa.length-6; i++) 
				{
					aa.elements[i].disabled = true;
					document.getElementById("amendmentDate").disabled = true;
					document.getElementById("msgValueDate").disabled = true;
				}
			
		}
		else
		{
			for ( var i = 0; i < aa.length-6; i++) 
			{
				aa.elements[i].enabled = true;
				document.getElementById("amendmentDate").enabled = true;
				document.getElementById("msgValueDate").enabled = true;
			}
		}
}

</script>		