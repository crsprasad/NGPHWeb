<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<%@taglib uri="http://displaytag.sf.net" prefix="display"%>
<link href="SpryAssets/SpryCollapsiblePanel.css" rel="stylesheet"
	type="text/css" />
<script src="SpryAssets/SpryCollapsiblePanel.js" type="text/javascript"></script>
<script type="text/javascript">

function callFetchBGDetails()
{	
	 
	var relatedReference= document.getElementById("relatedReference").value;

	if(relatedReference==""){
		 
		window.open(
				"<s:url action='searchAckBGNo' windowState='EXCLUSIVE' />",
				'mywindow', 'top=50,left=250,width=750,height=410');
	}
	else
		{
		callShowGrid();
		}
		
	}

function callShowGrid()
{
	
	document.forms[0].action="displayAckBGData";
	document.forms[0].submit();
}
function maxLength(field,maxChars){
      if(field.value.length >= maxChars) {
         event.returnValue=false;
         return false;
      }     
}
function maxLengthPaste(field,maxChars){
      event.returnValue=false;
      if((field.value.length +  window.clipboardData.getData("Text").length) > maxChars) {
        return false;
      }
      event.returnValue=true;
}
function callSearchIFSC(Action,flag) {
 
	document.getElementById("searchAction").value = Action;
	document.getElementById("IFSCFLAG").value = flag;
		window.open(
		"<s:url action='showIFSCScreen' windowState='EXCLUSIVE' />",
		'mywindow', 'top=50,left=250,width=750,height=410');
}


function callSubmit(saveAction)
{
	var aa = document.getElementById('form');
	for ( var i = 0; i < aa.length; i++) {
		aa.elements[i].disabled = false;
		}
	document.getElementById("saveAction").value = saveAction;
	document.forms[0].action="saveAckBG";
	document.forms[0].submit();
}


function checkOption() {
	 
	if(document.getElementById("lcCurrency").value == "D") 
    {
   	 	dojo.widget.byId ('BgDebitDate'). enable();
  	 }
   else 
    { 
   		dojo.widget.byId ('BgDebitDate'). disable();
  	 }
}

function SaveTemplate()
{
	var isConfirm = confirm("Need to save as Template!");
	if(isConfirm)
		{
			var tempName = prompt("Please enter your Template name");
			if(tempName!=null)
				{
					alert("Template Name is "+tempName);
					document.getElementById("tempName").value = tempName;
					document.forms[0].action="saveAckBGTemplate";
					document.forms[0].submit();
				}
		} 
}
function callAdvise() {

	if (document.getElementById("adviseThroughBankpartyidentifier").value == "A") {
		document.getElementById("adviseThroughBankAcc").disabled = false;
		document.getElementById("adviseThroughBankCode").disabled = false;
		document.getElementById("accountwithPartyLocation").disabled = true;
		document.getElementById("adviseThroughBankName").disabled = true;
		
	} else if (document.getElementById("adviseThroughBankpartyidentifier").value == "B") {
		document.getElementById("adviseThroughBankAcc").disabled = false;
		document.getElementById("adviseThroughBankCode").disabled = true;
		document.getElementById("accountwithPartyLocation").disabled = false;
		document.getElementById("adviseThroughBankName").disabled = true;
		
	} else if (document.getElementById("adviseThroughBankpartyidentifier").value == "D") {
		document.getElementById("adviseThroughBankAcc").disabled = false;
		document.getElementById("adviseThroughBankCode").disabled = true;
		document.getElementById("accountwithPartyLocation").disabled = true;
		document.getElementById("adviseThroughBankName").disabled = false;
	} else {
		document.getElementById("adviseThroughBankAcc").disabled = false;
		document.getElementById("adviseThroughBankCode").disabled = false;
		document.getElementById("accountwithPartyLocation").disabled = false;
		document.getElementById("adviseThroughBankName").disabled = false;
	}

}
function callPrintPreview(){
	document.forms[0].action="printPreviewAckBG";
	document.forms[0].submit();
}
function cancel()
{
	var isConfirm = confirm("Please confirm");
	if(isConfirm)
		{
			document.forms[0].action = "resetAckBG";
			document.forms[0].submit();
		}

}

function callCurrencyCodes()
{
	alert("callCurrencyCodes");
	var bgCurrency =document.getElementById("bgCurrency").value;
	alert("bgCurrency "+bgCurrency);
	
}

function callAmountDecimal(input)
{
	 input.value = parseFloat(input.value).toFixed(2);
}
</script>
<sx:head parseContent="true" />
<s:head />
<div id="contentPane">
<div id="contentPaneTop"></div>
<div id="contentPaneFluid">
<div id="contentPaneInternal"><span class="crumbs"><a
	href="#">Outbound Payments</a>&nbsp;>&nbsp;BG Acknowledgement </span></div>
<div id="content">
<h1>BG Acknowledgement (MT-768)</h1>
<s:form method="post" id="form">
<s:hidden name="repair" id="repair"></s:hidden>
<s:hidden name="IFSCFLAG" id="IFSCFLAG" ></s:hidden>
<s:hidden id="searchAction" name="searchAction"></s:hidden>	
<s:hidden name="IFSCFLAG" id="IFSCFLAG" ></s:hidden>
<s:hidden id="searchAction" name="searchAction"></s:hidden>
<s:hidden id="repairComments" name="repairComments"></s:hidden> 
<s:hidden id="lcAmount" name="lcAmount"></s:hidden> 
<s:hidden name="RepairData" id="RepairData"></s:hidden>
<s:hidden name="tempName" id="tempName"></s:hidden>
<s:hidden name="msgRef" id="msgRef"></s:hidden>
<s:hidden name="seqNo" id="seqNo"></s:hidden>
<s:hidden name="msgHost" id="msgHost"></s:hidden>
<s:hidden id="validUserToApprove" name="validUserToApprove"></s:hidden>
<s:hidden id="saveAction" name="saveAction"></s:hidden>


	<!--Error Msg Div Starts -->
	<div><s:if test="hasFieldErrors()">
				<div class="errorMsg"><span class="errorMsgInline"><s:actionerror /><s:fielderror /></span></div>
			</s:if></div><!--Error Msg Div Ends -->
	<!-- Collapsible Panels Start-->
	<div id="CollapsiblePanel1" class="CollapsiblePanel">
	<div class="CollapsiblePanelTab"><!--  <img src="images/openPanel.png" width="9" height="7" alt="openPanel" />-->&nbsp;Basic Details</div>
	<div class="CollapsiblePanelContent">
	<div><!-- Group One Div Starts --> 
	<s:div id="main"	cssClass="dataGrid" style="width:100%;">
	<table width="100%" cellpadding="3" cellspacing="1" border="0"	bgcolor="#ffffff">
	<s:if test="%{repair!=''}">
	 
	    <tr>
		       <td width="20%" class="textLeft">
		          <s:label	value="%{getText('label.MB.IssuingBranchIFSC')}"></s:label><span class="optional">[Optional]</span>
		       </td>
		       <td width="30%" class="text">
		         <s:textfield name="issuingBankCode" id="issuingBankCode"></s:textfield> 
		         <input type="button" value="Search IFSC..." onclick="return callSearchIFSC('ISSUEBANKTHOUGHIFSC','SENDERBANKIFSC')" id="btnadviceThrough"class="btn" />
		       </td>
		   
		       <td width="20%" class="textLeft">
		         <s:label	cssClass="MandatoryField" value="%{getText('label.MB.Benefifsc')}"></s:label><span class="MandatoryField">[Mandatory]</span>
		       </td>
		       <td width="30%" class="text">
		          <s:textfield name="advisingBank" id="advisingBank"></s:textfield> 
		          <input type="button" value="Search IFSC..." onclick="return callSearchIFSC('ADVISINGIFSC','BOTH')" id="btnadviceThrough"class="btn" />
		        </td>
		 </tr>
	
    </s:if>	
    <s:if test="%{repair==''}">
  
      <tr>
		   	  <td width="20%" class="textLeft">
		          <s:label	value="%{getText('label.MB.IssuingBranchIFSC')}"></s:label> <span class="optional">[Optional]</span>
		       </td>
		       <td width="30%" class="text">
		         <s:textfield name="issuingBankCode" id="issuingBankCode"></s:textfield> 
		         <input type="button" value="Search IFSC..." onclick="return callSearchIFSC('ISSUEBANKTHOUGHIFSC','SENDERBANKIFSC')" id="btnadviceThrough"class="btn" />
		       </td>
		   
		       <td width="20%" class="textLeft">
		         <s:label	cssClass="MandatoryField" value="%{getText('label.MB.Benefifsc')}"></s:label> <span class="MandatoryField">[Mandatory]</span>
		       </td>
		       <td width="30%" class="text">
		          <s:textfield name="advisingBank" id="advisingBank"></s:textfield> 
		          <input type="button" value="Search IFSC..." onclick="return callSearchIFSC('ADVISINGIFSC','BOTH')" id="btnadviceThrough"class="btn" />
		        </td>
		</tr>
		    
	  </s:if>
    </table>
	</s:div><!-- Group One Div Ends --></div>
	</div>
	</div>
		<br />
	<div id="CollapsiblePanel2" class="CollapsiblePanel">
	<div class="CollapsiblePanelTab"><!--  <img src="images/openPanel.png" width="9" height="7" alt="openPanel" />-->&nbsp;Message Details</div>
	<div class="CollapsiblePanelContent">
	<div><!-- Group Two Div Starts --> 
	<s:div id="main"	cssClass="dataGrid" style="width:100%;">
	<table width="100%" cellpadding="3" cellspacing="1" border="0"	bgcolor="#ffffff">
	
	<s:if test="%{repair!=''}">
	<tr>
	  <td width="100%" class="textLeft">
	    <s:label cssClass="MandatoryField" value="%{getText('label.768.transactionReferenceNumber')}"></s:label> <span class="MandatoryField">[Mandatory]</span>
	  </td>
	</tr>
	<tr>
	  <td width="100%" class="text">
	    <s:textfield name="bgNumber" id="bgNumber" maxLength="16"></s:textfield>
	  </td>
	</tr>
	<tr>
	  <td width="100%" class="textLeft">
	    <s:label cssClass="MandatoryField" value="%{getText('label.768.bgrelatedReference')}"></s:label> <span class="MandatoryField">[Mandatory]</span>
	  </td>
	</tr>
	<tr>
	  <td width="100%" class="text">
	    <s:textfield name="relatedReference" id="relatedReference" maxLength="16"></s:textfield>
	  </td>
	</tr>
	<tr>
	  <td width="100%" class="textLeft">
	    <s:label value="%{getText('label.768.accountIdentification')}"></s:label> <span class="optional">[Optional]</span>
	  </td>
	</tr>
	<tr>
	  <td width="100%" class="text">
	    <s:textfield name="bgAccountIdentification" id="bgAccountIdentification" maxLength="35"></s:textfield>  <b>[Either field 25 or 57a, but not both, may be present]</b>
	  </td>
	</tr>
	<tr>
	  <td width="100%" class="textLeft">
	    <s:label cssClass="MandatoryField" value="%{getText('label.768.dateofMessageBeingAcknowledged')}"></s:label> <span class="MandatoryField">[Mandatory]</span>
	  </td>
	</tr>
	<tr>
	  <td width="100%" class="text">
	    <sx:datetimepicker name="dateofMessageBeingAcknowledged" value="%{'today'}" cssClass="txtField" id="dateofMessageBeingAcknowledged"  displayFormat="MM/dd/yyyy" type="date"/>
	  </td>		
	</tr>
	<tr>
	  <td width="100%" class="textLeft" >
	     <s:label value="%{getText('label.768.chargeAmount')}"></s:label> <span class="optional">[Optional]</span>
	  </td>
	</tr>
	<tr>
	  <td width="100%" class="text">
	    <s:select list="{'B','D'}" name="lcCurrency" id="lcCurrency" headerKey="" headerValue="Select" onChange="javascript:checkOption();" ></s:select> <b>[If field 32D is present, field 57a must not be present]</b>
	  </td>	
	</tr>
	<tr>
	  <td width="100%" class="textLeft" id="tdDateofIssue">
	    <s:label value="%{getText('label.768.DateofIssue')}"></s:label> <span class="optional">[Optional]</span>
	  </td>
	<tr>
	  <td width="100%" class="text">
	    <sx:datetimepicker name="BgDebitDate" cssClass="txtField" id="BgDebitDate" cssClass="txtField"  displayFormat="MM/dd/yyyy" type="date"></sx:datetimepicker>
	  </td>
	</tr>
	<tr>
	  <td width="100%" class="textLeft">
	    <s:label value="%{getText('label.768.Currency')}"></s:label> <span class="optional">[Optional]</span>
	  </td>
	</tr>
	<tr>
	  <td width="100%" class="text">
	  	<s:select list="currCodeDropDown" name="bgCurrency" id="bgCurrency"  headerKey="" headerValue="Select Currency Code" cssClass="txtField" ></s:select>
	  </td>
	</tr>
	<tr>
	   <td width="100%" class="textLeft">
	     <s:label value="%{getText('label.768.amount')}"></s:label> <span class="optional">[Optional]</span>
	   </td>
	</tr>
	<tr>
	  <td width="100%" class="text">
	    <s:textfield name="bgChargeAmount" id="bgChargeAmount" maxlength="15" onKeyPress='return notAllowCheck(this,event);'></s:textfield>
	  </td> 
	</tr>
	<tr>
	  <td width="100%" class="textLeft">
	     <s:label value="%{getText('label.768.accountWithPartyIdentifier1')}"></s:label> <span class="optional">[Optional]</span>
	  </td>
	</tr>
	<tr>
	  <td width="100%" class="text">
	     <s:select list="{'A','B','D'}" headerKey=""  headerValue="Select PID" name="adviseThroughBankpartyidentifier" id="adviseThroughBankpartyidentifier" onChange="javascript:callAdvise();"></s:select>
			<s:textfield name="adviseThroughBankAcc" id="adviseThroughBankAcc" maxLength="34"   ></s:textfield>
	  </td>
	</tr>
	<tr>
      <td width="100%" class="textLeft">
        <s:label	value="%{getText('label.768.accountWithPartyIFSC')}"></s:label> <span class="optional">[Optional]</span>
      </td>
    </tr>
	<tr>
	  <td width="100%" class="text">
	    <s:textfield name="adviseThroughBankCode" id="adviseThroughBankCode"  ></s:textfield>
	     <input type="button" value="Search IFSC..." onclick="return callSearchIFSC('ADVICETHOUGHIFSC','BOTH')" id="btnadviceThrough"class="btn" />
	  </td>
	</tr>
	<tr>
       <td width="100%" class="textLeft">
         <s:label value="%{getText('label.768.accountWithPartyLocation')}"></s:label> <span class="optional">[Optional]</span>
       </td>
    </tr>
	<tr>
	  <td width="100%" class="text">
	    <s:textfield name="accountwithPartyLocation" id="accountwithPartyLocation" maxlength="35" ></s:textfield>
	  </td>
	</tr>
	<tr>
	   <td width="100%" class="textLeft">
	     <s:label value="%{getText('label.768.accountWithNameAndAddress')}"></s:label> <span class="optional">[Optional]</span>
	   </td>
	</tr>
	<tr>
	  <td width="100%" class="text">
	    <s:textarea name="adviseThroughBankName" id="adviseThroughBankName" rows = "4" cols="35" wrap="HARD" ></s:textarea>
	  </td>
	</tr> 
	<tr>			
	  <td width="100%" class="textLeft">
	    <s:label value="%{getText('label.768.chargeDetails')}"></s:label> <span class="optional">[Optional]</span>
	  </td>
	</tr>
	<tr>
	  <td width="100%" class="text">
	    <s:textarea rows="6" cols="35" name="chargesDetails" id="chargesDetails" onKeyPress='return maxLength(this,"210");' onpaste='return maxLengthPaste(this,"210");'></s:textarea>  <b>[If field 71B is present, field 32a must also be present]</b>
	  </td>
	</tr>
	<tr>
	   <td width="100%" class="textLeft">
	     <s:label value="%{getText('label.768.senderToReceiverInformation')}"></s:label> <span class="optional">[Optional]</span>
	   </td>
	</tr>
	<tr>
	   <td width="100%" class="text">
	     <s:textarea rows="6" cols="35" id="senderToReceiverInformation" name="senderToReceiverInformation" onKeyPress='return maxLength(this,"210");' onpaste='return maxLengthPaste(this,"210");'></s:textarea>
	   </td>
	</tr>	
	 
	</s:if>	
 <s:if test="%{repair==''}">
	<tr>
	  <td width="100%" class="textLeft">
	    <s:label	cssClass="MandatoryField" value="%{getText('label.768.transactionReferenceNumber')}"></s:label> <span class="MandatoryField">[Mandatory]</span>
	  </td>
	</tr>
	<tr>
	  <td width="100%" class="text">
	    <s:textfield name="bgNumber" id="bgNumber" maxLength="16"></s:textfield>
	  </td>
	</tr>
	<tr>
	  <td width="100%" class="textLeft">
	    <s:label cssClass="MandatoryField" value="%{getText('label.768.bgrelatedReference')}"></s:label> <span class="MandatoryField">[Mandatory]</span>
	  </td>
	</tr>
	<tr>
	  <td width="100%" class="text">
	    <s:textfield name="relatedReference" id="relatedReference" maxLength="16"></s:textfield>
	  </td>
	</tr>
	<tr>
	  <td width="100%" class="textLeft">
	    <s:label value="%{getText('label.768.accountIdentification')}"></s:label> <span class="optional">[Optional]</span>
	  </td>
	</tr>
	<tr>
	  <td width="100%" class="text">
	    <s:textfield name="bgAccountIdentification" id="bgAccountIdentification" maxLength="35"></s:textfield>  <b>[Either field 25 or 57a, but not both, may be present]</b>
	  </td>
	</tr>
	<tr>
	  <td width="100%" class="textLeft">
	    <s:label cssClass="MandatoryField" value="%{getText('label.768.dateofMessageBeingAcknowledged')}"></s:label> <span class="MandatoryField">[Mandatory]</span>
	  </td>
	</tr>
	<tr>
	  <td width="100%" class="text">
	    <sx:datetimepicker name="dateofMessageBeingAcknowledged" value="%{'today'}" cssClass="txtField" id="dateofMessageBeingAcknowledged"  displayFormat="MM/dd/yyyy" type="date"/>
	  </td>		
	</tr>
	<tr>
	  <td width="100%" class="textLeft" >
	     <s:label value="%{getText('label.768.chargeAmount')}"></s:label> <span class="optional">[Optional]</span>
	  </td>
	</tr>
	<tr>
	  <td width="100%" class="text">
	    <s:select list="{'B','D'}" name="lcCurrency" id="lcCurrency" headerValue="Select" onChange="javascript:checkOption();" ></s:select> <b>[If field 32D is present, field 57a must not be present]</b>
	  </td>	
	</tr>
	<tr>
	  <td width="100%" class="textLeft" id="tdDateofIssue">
	    <s:label value="%{getText('label.768.DateofIssue')}"></s:label> <span class="optional">[Optional]</span>
	  </td>
	<tr>
	  <td width="100%" class="text">
	    <sx:datetimepicker name="BgDebitDate" cssClass="txtField" id="BgDebitDate" cssClass="txtField"  displayFormat="MM/dd/yyyy" type="date"></sx:datetimepicker>
	  </td>
	</tr>
	<tr>
	  <td width="100%" class="textLeft">
	    <s:label value="%{getText('label.768.Currency')}"></s:label> <span class="optional">[Optional]</span>
	  </td>
	</tr>
	<tr>
	  <td width="100%" class="text">
	   	<s:select list="currCodeDropDown" name="bgCurrency" id="bgCurrency"  headerKey="" headerValue="Select Currency Code" cssClass="txtField" ></s:select>
	  </td>
	</tr>
	<tr>
	   <td width="100%" class="textLeft">
	     <s:label value="%{getText('label.768.amount')}"></s:label> <span class="optional">[Optional]</span>
	   </td>
	</tr>
	<tr>
	  <td width="100%" class="text">
	    <s:textfield name="bgChargeAmount" id="bgChargeAmount" onKeyPress='return notAllowCheck(this,event);' onChange='callAmountDecimal(this);' maxLength="15"></s:textfield>
	  </td> 
	</tr>
	<tr>
	  <td width="100%" class="textLeft">
	     <s:label value="%{getText('label.768.accountWithPartyIdentifier1')}"></s:label> <span class="optional">[Optional]</span>
	  </td>
	</tr>
	<tr>
	  <td width="100%" class="text">
	     <s:select list="{'A','B','D'}" headerKey=""  headerValue="Select PID" name="adviseThroughBankpartyidentifier" id="adviseThroughBankpartyidentifier" onChange="javascript:callAdvise();"></s:select>
			<s:textfield name="adviseThroughBankAcc" id="adviseThroughBankAcc" maxLength="34"   ></s:textfield>
	  </td>
	</tr>
	<tr>
      <td width="100%" class="textLeft">
        <s:label value="%{getText('label.768.accountWithPartyIFSC')}"></s:label> <span class="optional">[Optional]</span>
      </td>
    </tr>
	<tr>
	  <td width="100%" class="text">
	    <s:textfield name="adviseThroughBankCode" id="adviseThroughBankCode"  ></s:textfield>
	     <input type="button" value="Search IFSC..." onclick="return callSearchIFSC('ADVICETHOUGHIFSC','BOTH')" id="btnadviceThrough"class="btn" />
	  </td>
	</tr>
	<tr>
       <td width="100%" class="textLeft">
         <s:label value="%{getText('label.768.accountWithPartyLocation')}"></s:label> <span class="optional">[Optional]</span>
       </td>
    </tr>
	<tr>
	  <td width="100%" class="text">
	    <s:textfield name="accountwithPartyLocation" id="accountwithPartyLocation" maxlength="35" ></s:textfield>
	  </td>
	</tr>
	<tr>
	   <td width="100%" class="textLeft">
	     <s:label	value="%{getText('label.768.accountWithNameAndAddress')}"></s:label> <span class="optional">[Optional]</span>
	   </td>
	</tr>
	<tr>
	  <td width="100%" class="text">
	    <s:textarea name="adviseThroughBankName" id="adviseThroughBankName" rows = "4" cols="35" wrap="HARD" ></s:textarea>
	  </td>
	</tr> 
	<tr>			
	  <td width="100%" class="textLeft">
	    <s:label	value="%{getText('label.768.chargeDetails')}"></s:label> <span class="optional">[Optional]</span>
	  </td>
	</tr>
	<tr>
	  <td width="100%" class="text">
	    <s:textarea rows="6" cols="35" name="chargesDetails" id="chargesDetails" onKeyPress='return maxLength(this,"210");' onpaste='return maxLengthPaste(this,"210");'></s:textarea>  <b>[If field 71B is present, field 32a must also be present]</b>
	  </td>
	</tr>
	<tr>
	   <td width="100%" class="textLeft">
	     <s:label value="%{getText('label.768.senderToReceiverInformation')}"></s:label> <span class="optional">[Optional]</span>
	   </td>
	</tr>
	<tr>
	   <td width="100%" class="text">
	     <s:textarea rows="6" cols="35" id="senderToReceiverInformation" name="senderToReceiverInformation" onKeyPress='return maxLength(this,"210");' onpaste='return maxLengthPaste(this,"210");'></s:textarea>
	   </td>
	</tr>			
   </s:if>	
	
</table>		
	</s:div> <!-- Group Two Div Ends --></div>
	</div>
	</div>
	<br />
	<!--  button panel starts -->
	<div class="clearfix"></div>
	<table width="100%" cellpadding="0" cellspacing="0" border="0">
		<tr>
			<td>
			<s:if test="%{checkForSubmit!='Display_Approve_Reject'}">
					<s:submit value="Submit" action="getAckBGSubmit" cssClass="btn" />
					<input type="reset" value="Reset" onclick="cancel()" class="btn"  />
					<input type="button" value="Save Template" onclick="SaveTemplate()" class="btn"  />
					<!--<input type="button" value="Print" onclick="window.print()">
			--></s:if>
			<s:if test="%{checkForSubmit=='Display_Approve_Reject'}">
				<s:if test="%{validUserToApprove}">
					<input type="button"  value="Reject" onclick="callSubmit('Reject')">
					<input type="button"  value="Approve" onclick="callSubmit('Approve')">
					<input type="button" value="Cancel" onclick="cancel()">
					<input type="button" id="printpreview" value="Print preview" onclick="callPrintPreview()">
				</s:if>
				<s:if test="%{!validUserToApprove}">
					<s:submit value="Submit" action="getAckBGSubmit" cssClass="btn" />
					<input type="reset" value="Cancel" onclick="cancel()" class="btn"  />
					<input type="button" value="Save Template" onclick="SaveTemplate()" class="btn"  />
				</s:if>
			</s:if>
			</td>
		</tr>
	</table>
	
	<s:hidden id="hiddenTxnRef" name="hiddenTxnRef"></s:hidden>
	
	</s:form></div>
</div>
</div>
<div id="contentPaneBottom"></div>

<script type="text/javascript">
	var CollapsiblePanel1 = new Spry.Widget.CollapsiblePanel(
			"CollapsiblePanel1");	
	
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
				}
			
		}
		else
		{
			for ( var i = 0; i < aa.length-3; i++) 
			{
				aa.elements[i].enabled = true;
			}
		}
}

	window.onload = function() {
		  
		 dojo.widget.byId("DateofMessageBeingAcknowledged").disable();
		 dojo.widget.byId("bgIssueDate").disable();
		 
}
</script>
