<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="com.logica.ngph.web.action.*,com.logica.ngph.web.utils.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<%@taglib uri="http://displaytag.sf.net" prefix="display"%>
<link href="SpryAssets/SpryCollapsiblePanel.css" rel="stylesheet"
	type="text/css" />
<script src="SpryAssets/SpryCollapsiblePanel.js" type="text/javascript"></script>
<script type="text/javascript">

function callFetchLCDetails()
{	
	 
	var relatedReference= document.getElementById("relatedReference").value;

	if(relatedReference==""){
		 
		window.open(
				"<s:url action='searchAckLCNo' windowState='EXCLUSIVE' />",
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

function callSubmitForApproval()
{
	
	var repair = document.getElementById("repair").value;

	if(repair!="")
	{
		callSubmitForScreen();
	}
	else
	{
		callSubmitForScreen();	
	}
	
}

function callSubmitForScreen()
{
	document.forms[0].action="lcAcknowledgementApproval";
	document.forms[0].submit();
}

function callSubmit(saveAction)
{
	var aa = document.getElementById('form');
	for ( var i = 0; i < aa.length; i++) {
		aa.elements[i].disabled = false;
		}
	document.getElementById("saveAction").value = saveAction;
	document.forms[0].action="saveLCAcknowledgement";
	document.forms[0].submit();
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
					document.forms[0].action="saveTemplateLcAck";
					document.forms[0].submit();
				}
		} 
}

function checkOption() {
	 
	if(document.getElementById("currency").value == "D") 
    {
   	 	dojo.widget.byId ('amountPaidDate'). enable();
  	 }
   else 
    { 
   		dojo.widget.byId ('amountPaidDate'). disable();
  	 }
}
function callAdvise() {
	
	if (document.getElementById("adviseThroughBankpartyidentifier").value == "A") {
		document.getElementById("adviseThroughBankName").disabled = true;
		document.getElementById("adviseThroughBankCode").disabled = false;
		document.getElementById("adviseThroughBankAcc").disabled = false;
		
	} else if (document.getElementById("adviseThroughBankpartyidentifier").value == "D") {
		document.getElementById("adviseThroughBankCode").disabled = true;
		document.getElementById("adviseThroughBankAcc").disabled = false;
		document.getElementById("adviseThroughBankName").disabled = false;
	} else {
		document.getElementById("adviseThroughBankCode").disabled = false;
		document.getElementById("adviseThroughBankName").disabled = false;
		document.getElementById("adviseThroughBankAcc").disabled = false;
	}

}

function callPrintPreview(){
	document.forms[0].action="printPreviewLCAcknowledgement";
	document.forms[0].submit();
}

function cancel(){
	var isConfirm = confirm("Please confirm");
	if(isConfirm)
		{
			document.forms[0].action="resetLCAck";
			document.forms[0].submit();
		}
}

function callAmountDecimal(input)
{
	 input.value = parseFloat(input.value).toFixed(2);
}

function generatePdf()
{
	document.forms[0].action="generatePDFLCAck";
	document.forms[0].submit();
}
</script>
<sx:head parseContent="true" />
<s:head />
<div id="contentPane">
<div id="contentPaneTop"></div>
<div id="contentPaneFluid">
<div id="contentPaneInternal"><span class="crumbs"><a
	href="#">Outbound Payments</a>&nbsp;>&nbsp;LC Acknowledgement</span></div>
<div id="content">
<h1>LC Acknowledgement (MT-730)</h1>
<s:form method="post" id="form">
<s:hidden name="repair" id="repair"></s:hidden>
<s:hidden name="IFSCFLAG" id="IFSCFLAG" ></s:hidden>
<s:hidden id="searchAction" name="searchAction"></s:hidden>	
<s:hidden name="IFSCFLAG" id="IFSCFLAG" ></s:hidden>
<s:hidden id="searchAction" name="searchAction"></s:hidden>
<s:hidden id="repairComments" name="repairComments"></s:hidden> 
<s:hidden name="RepairData" id="RepairData"></s:hidden>
<s:hidden name="tempName" id="tempName"></s:hidden>
<s:hidden name="msgHost" id="msgHost"></s:hidden>
<s:hidden name="seqNo" id="seqNo"></s:hidden>
<s:hidden id="validUserToApprove" name="validUserToApprove"></s:hidden>


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
		     <s:label	cssClass="MandatoryField" value="%{getText('label.730.SenderBanksReference')}"></s:label> <span class="MandatoryField">[Mandatory]</span>
		   </td>
		</tr>
	   <tr>
	      <td width="100%" class="text">
	        <s:textfield name="lcNumber" id="lcNumber" maxLength="16"></s:textfield> 
	      </td>
	   </tr>
	   <tr>
	     <td width="100%" class="textLeft">
	        <s:label cssClass="MandatoryField" value="%{getText('label.730.ReceiverBanksReference')}"></s:label> <span class="MandatoryField">[Mandatory]</span>
	     </td>
	   </tr>
	   <tr>
	     <td width="100%" class="text">
	        <s:textfield name="relatedReference" id="relatedReference" maxLength="16"></s:textfield> 
	     </td>
	   </tr>
	   <tr>
		<td width="100%" class="textLeft">
		  <s:label value="%{getText('label.730.accountIdentification')}"></s:label> <span class="optional">[Optional]</span>
		</td>
	   </tr>
	   <tr>
	     <td width="100%" class="text">
	       <s:textfield cols="35" name="lcAccId" id="lcAccId" maxLength="35"></s:textfield>  <b>[Either field 25 or 57a, but not both, may be present] </b>
	     </td>  
	   </tr>	
	   <tr>
	     <td width="100%" class="textLeft">
	       <s:label cssClass="MandatoryField" value="%{getText('label.730.dateofMessageBeingAcknowledged')}"></s:label>  <span class="MandatoryField">[Mandatory]</span>
	     </td>
	   </tr>
	   <tr>
	     <td width="100%" class="text">
	       <sx:datetimepicker name="lcAckDate" value="%{'today'}" cssClass="txtField" id="lcAckDate"  displayFormat="MM/dd/yyyy" type="date"/>
	     </td>
	   </tr>
	   <tr>
		 <td width="100%" class="textLeft" >
		   <s:label value="%{getText('label.730.ChargeAmount')}"></s:label> <span class="optional">[Optional]</span>
		 </td>
	   </tr>
	   <tr>
	     <td width="100%" class="text">
	       <s:select list="{'B','D'}" name="currency" id="currency" headerKey="" headerValue="Select PID" onChange="javascript:checkOption();" ></s:select> <b>[If field 32D is present, field 57a must not be present]</b>
	     </td>
	   </tr>
	   <tr>
		 <td width="100%" class="textLeft">
		   <s:label value="%{getText('label.730.Currency')}"></s:label> <span class="optional">[Optional]</span>
		 </td>
	   </tr>
	   <tr>
		 <td width="100%" class="text">
		   <!--<s:select list="{'EUR','GBS','INR','USD'}" name="lcCurrency" id="lcCurrency" headerKey="" headerValue="Select Currency"></s:select>
		 --><s:select list="currCodeDropDown" name="lcCurrency" id="lcCurrency"  headerKey="" headerValue="Select Currency Code" cssClass="txtField" onSelect="javascript:callCurrencyCodes();"></s:select>
		 </td>
	   </tr>	
	   <tr>
	     <td width="100%" class="textLeft">
	        <s:label value="%{getText('label.730.Amount')}"></s:label> <span class="optional">[Optional]</span>
	     </td>
	   </tr>
	   <tr>
	     <td width="100%" class="text">
	       <s:textfield name="lcAmount" id="lcAmount" onChange='callAmountDecimal(this);' maxLength="15"></s:textfield>
	     </td>
	</tr>
	<tr>
	  <td width="100%" class="textLeft">
	    <s:label value="%{getText('label.730.amountPaidDate')}"></s:label> <span class="optional">[Optional]</span>
	  </td>
	</tr>
	<tr>
	  <td width="100%" class="text">
	    <sx:datetimepicker name="amountPaidDate" cssClass="txtField" id="amountPaidDate"  displayFormat="MM/dd/yyyy" type="date"/> 
	  </td>	
	</tr>
	<tr>
	  <td width="100%" class="textLeft">
	     <s:label value="%{getText('label.730.accountWithPartyIdentifier1')}"></s:label> <span class="optional">[Optional]</span>
	  </td>
	</tr>
	<tr>
	  <td width="100%" class="text">
	    <s:select list="{'A','D'}" headerKey=""  headerValue="Select PID" name="adviseThroughBankpartyidentifier" id="adviseThroughBankpartyidentifier" onChange="javascript:callAdvise();"></s:select>
			<s:textfield name="adviseThroughBankAcc" id="adviseThroughBankAcc" maxLength="34"   ></s:textfield>
	  </td>
	</tr>
	<tr>
	  <td width="100%" class="textLeft">
	     <s:label value="%{getText('label.730.accountWithBank')}"></s:label> <span class="optional">[Optional]</span>
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
	     <s:label value="%{getText('label.730.accountWithNameAndAddress')}"></s:label> <span class="optional">[Optional]</span>
	  </td>
	</tr>
	<tr>
	  <td width="100%" class="text">
	    <s:textarea name="adviseThroughBankName" id="adviseThroughBankName" rows ="4" cols="35" wrap="HARD" onkeyup="callAdvise()" onKeyPress='return maxLength(this,"139","adviseThroughBankName","35",event);'></s:textarea>
	  </td>		
	</tr> 
	<tr>			
	  <td width="100%" class="textLeft">
	    <s:label value="%{getText('label.730.ChargeDetails')}"></s:label> <span class="optional">[Optional]</span>
	  </td>
	</tr>
	<tr>
	  <td width="100%" class="text">
	    <s:textarea name="chargeDetails" id="chargeDetails" rows="6" cols="35" wrap="HARD" onKeyPress='return maxLength(this,"210","chargeDetails","35",event);'></s:textarea>
	  </td>
	</tr>
	<tr>
	   <td width="100%" class="textLeft">
	     <s:label value="%{getText('label.730.SendertoReceiverInformation')}"></s:label> <span class="optional">[Optional]</span>
	   </td>
	</tr>
	<tr>
	  <td width="100%" class="text">
	     <s:textarea name="SendertoReceiverInformation" id="SendertoReceiverInformation" cols="35" rows="6" wrap="HARD" onKeyPress='return maxLength(this,"210","SendertoReceiverInformation","35",event);'></s:textarea>
	  </td>
	</tr>	
		
	</s:if>	
<s:if test="%{repair==''}">
       <tr>
		   <td width="100%" class="textLeft">
		     <s:label cssClass="MandatoryField" value="%{getText('label.730.SenderBanksReference')}"></s:label> <span class="MandatoryField">[Mandatory]</span>
		   </td>
		</tr>
	   <tr>
	      <td width="100%" class="text">
	        <s:textfield name="lcNumber" id="lcNumber" maxLength="16"></s:textfield> 
	      </td>
	   </tr>
	   <tr>
	     <td width="100%" class="textLeft">
	        <s:label cssClass="MandatoryField" value="%{getText('label.730.ReceiverBanksReference')}"></s:label> <span class="MandatoryField">[Mandatory]</span>
	     </td>
	   </tr>
	   <tr>
	     <td width="100%" class="text">
	        <s:textfield name="relatedReference" id="relatedReference" maxLength="16"></s:textfield> 
	     </td>
	   </tr>
	   <tr>
		<td width="100%" class="textLeft">
		  <s:label value="%{getText('label.730.accountIdentification')}"></s:label> <span class="optional">[Optional]</span>
		</td>
	   </tr>
	   <tr>
	     <td width="100%" class="text">
	       <s:textfield cols="35" name="lcAccId" id="lcAccId" maxLength="35"></s:textfield>  <b>[Either field 25 or 57a, but not both, may be present] </b>
	     </td>  
	   </tr>	
	   <tr>
	     <td width="100%" class="textLeft">
	       <s:label cssClass="MandatoryField" value="%{getText('label.730.dateofMessageBeingAcknowledged')}"></s:label>  <span class="MandatoryField">[Mandatory]</span>
	     </td>
	   </tr>
	   <tr>
	     <td width="100%" class="text">
	       <sx:datetimepicker name="lcAckDate" value="%{'today'}" cssClass="txtField" id="lcAckDate"  displayFormat="MM/dd/yyyy" type="date"/>
	     </td>
	   </tr>
	   <tr>
		 <td width="100%" class="textLeft" >
		   <s:label value="%{getText('label.730.ChargeAmount')}"></s:label> <span class="optional">[Optional]</span>
		 </td>
	   </tr>
	   <tr>
	     <td width="100%" class="text">
	       <s:select list="{'B','D'}" name="currency" id="currency" headerKey="" headerValue="Select PID" onChange="javascript:checkOption();" ></s:select> <b>[If field 32D is present, field 57a must not be present]</b>
	     </td>
	   </tr>
	   <tr>
		 <td width="100%" class="textLeft">
		   <s:label value="%{getText('label.730.Currency')}"></s:label> <span class="optional">[Optional]</span>
		 </td>
	   </tr>
	   <tr>
		 <td width="100%" class="text"><!--
		   <s:select list="{'EUR','GBS','INR','USD'}" name="lcCurrency" id="lcCurrency" headerKey="" headerValue="Select Currency"  ></s:select>
		 --><s:select list="currCodeDropDown" name="lcCurrency" id="lcCurrency"  headerKey="" headerValue="Select Currency Code" cssClass="txtField" onSelect="javascript:callCurrencyCodes();"></s:select></td>
	   </tr>	
	   <tr>
	     <td width="100%" class="textLeft">
	        <s:label	value="%{getText('label.730.Amount')}"></s:label> <span class="optional">[Optional]</span>
	     </td>
	   </tr>
	   <tr>
	     <td width="100%" class="text">
	       <s:textfield name="lcAmount" id="lcAmount" onChange='callAmountDecimal(this);' maxLength="15"></s:textfield>
	     </td>
	</tr>
	<tr>
	  <td width="100%" class="textLeft">
	    <s:label value="%{getText('label.730.amountPaidDate')}"></s:label> <span class="optional">[Optional]</span>
	  </td>
	</tr>
	<tr>
	  <td width="100%" class="text">
	    <sx:datetimepicker name="amountPaidDate" cssClass="txtField" id="amountPaidDate"  displayFormat="MM/dd/yyyy" type="date"/> 
	  </td>	
	</tr>
	<tr>
	  <td width="100%" class="textLeft">
	     <s:label value="%{getText('label.730.accountWithPartyIdentifier1')}"></s:label> <span class="optional">[Optional]</span>
	  </td>
	</tr>
	<tr>
	  <td width="100%" class="text">
	    <s:select list="{'A','D'}" headerKey=""  headerValue="Select PID" name="adviseThroughBankpartyidentifier" id="adviseThroughBankpartyidentifier" onChange="javascript:callAdvise();"></s:select>
			<s:textfield name="adviseThroughBankAcc" id="adviseThroughBankAcc" maxLength="34"   ></s:textfield>
	  </td>
	</tr>
	<tr>
	  <td width="100%" class="textLeft">
	     <s:label	value="%{getText('label.730.accountWithBank')}"></s:label> <span class="optional">[Optional]</span>
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
	     <s:label	value="%{getText('label.730.accountWithNameAndAddress')}"></s:label> <span class="optional">[Optional]</span>
	  </td>
	</tr>
	<tr>
	  <td width="100%" class="text">
	    <s:textarea name="adviseThroughBankName" id="adviseThroughBankName" rows ="4" cols="35" wrap="HARD" onkeyup="callAdvise()" onKeyPress='return maxLength(this,"139","adviseThroughBankName","35",event);'></s:textarea>
	  </td>		
	</tr> 
	<tr>			
	  <td width="100%" class="textLeft">
	    <s:label value="%{getText('label.730.ChargeDetails')}"></s:label> <span class="optional">[Optional]</span>
	  </td>
	</tr>
	<tr>
	  <td width="100%" class="text">
	    <s:textarea name="chargeDetails" id="chargeDetails" rows="6" cols="35" wrap="HARD" onKeyPress='return maxLength(this,"210","chargeDetails","35",event);'></s:textarea>
	  </td>
	</tr>
	<tr>
	   <td width="100%" class="textLeft">
	     <s:label	value="%{getText('label.730.SendertoReceiverInformation')}"></s:label> <span class="optional">[Optional]</span>
	   </td>
	</tr>
	<tr>
	  <td width="100%" class="text">
	     <s:textarea name="SendertoReceiverInformation" id="SendertoReceiverInformation" cols="35" rows="6" wrap="HARD" onKeyPress='return maxLength(this,"210","SendertoReceiverInformation","35",event);'></s:textarea>
	  </td>
	</tr>	
		
</s:if>	
	
</table>		
		
	<!--  button panel starts -->
	<div class="clearfix"></div>
	<table width="100%" cellpadding="0" cellspacing="0" border="0">
		<tr>
			<td>
			<s:if test="%{checkForSubmit!='Display_Approve_Reject'}">
				<input type ="button" value="Submit"  class="btn"  onclick="callSubmitForApproval()"/>
				<input type="reset" value="Reset" onclick="cancel()" class="btn"  />
				<input type="button" value="Save Template" onclick="SaveTemplate()" class="btn"  />
				<!--<input type="button" value="Print" onclick="generatePdf();">
			--></s:if>
			<s:if test="%{checkForSubmit=='Display_Approve_Reject'}">
				<s:if test="%{validUserToApprove}">
					<input type="button" value="Approve" onclick="callSubmit('Approve')">
					<input type="button" value="Reject" onclick="callSubmit('Reject')">
					<input type="button" value="Cancel" onclick="cancel()">
					<input type="button" value="Print Preview" onclick="callPrintPreview()">
				</s:if>
				<s:if test="%{!validUserToApprove}">
					<input type ="button" value="Submit"  class="btn"  onclick="callSubmitForApproval()"/>
				<input type="reset" value="Cancel" onclick="cancel()" class="btn"  />
				<input type="button" value="Save Template" onclick="SaveTemplate()" class="btn"  />
				</s:if>
			</s:if>
					
				
			</td>
		</tr>
	</table>
<s:hidden id="saveAction" name="saveAction"></s:hidden>
<s:hidden id="hiddenTxnRef" name="hiddenTxnRef"></s:hidden>
	
	</s:div> <!-- Group Two Div Ends --></div>
	</div>
	</div>
	<br />
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
			for ( var i = 0; i < aa.length-6; i++) 
				{
					aa.elements[i].disabled = true;
				}
			
		}
		else
		{
			for ( var i = 0; i < aa.length-6; i++) 
			{
				aa.elements[i].enabled = true;
			}
		}
}
	

</script>
