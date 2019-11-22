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
function callSubmitToSave(saveAction){
	var aa = document.getElementById('form');
	for ( var i = 0; i < aa.length; i++) {
		aa.elements[i].disabled = false;
	}
	document.getElementById("saveAction").value = saveAction;
	document.forms[0].action="saveAdviceofPayment";
	document.forms[0].submit();
}

function callSubmit()
{
			document.forms[0].action="getAdviceofPaymentApproval";
			document.forms[0].submit();
}

function callFetchBGDetails()
{
	var relatedReference= document.getElementById("relatedReference").value;
	if(relatedReference==""){
		window.open(
				"<s:url action='searchAmendBGNo' windowState='EXCLUSIVE' />",
				'mywindow', 'top=50,left=250,width=750,height=410');
	}
	else
		{
			callShowGrid();
		}
		
	}
function callShowGrid()
{
	document.forms[0].action="displayAmendBGData";
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
	 
	if (document.getElementById("adviseThroughBankpartyidentifier").value == "A") {
		document.getElementById("adviseThroughBankAcc").disabled = false;
		document.getElementById("adviseThroughBankCode").disabled = false;
		document.getElementById("accountWithPartyLoc").disabled = true;
		document.getElementById("accountWithPartyNameAndAddress").disabled = true;
		
	} else if (document.getElementById("adviseThroughBankpartyidentifier").value == "B") {
		document.getElementById("adviseThroughBankAcc").disabled = false;
		document.getElementById("adviseThroughBankCode").disabled = true;
		document.getElementById("accountWithPartyLoc").disabled = false;
		document.getElementById("accountWithPartyNameAndAddress").disabled = true;
		
	} else if (document.getElementById("adviseThroughBankpartyidentifier").value == "D") {
		document.getElementById("adviseThroughBankAcc").disabled = false;
		document.getElementById("adviseThroughBankCode").disabled = true;
		document.getElementById("accountWithPartyLoc").disabled = true;
		document.getElementById("accountWithPartyNameAndAddress").disabled = false;
	} else {
		document.getElementById("adviseThroughBankAcc").disabled = false;
		document.getElementById("adviseThroughBankCode").disabled = false;
		document.getElementById("accountWithPartyLoc").disabled = false;
		document.getElementById("accountWithPartyNameAndAddress").disabled = false;
	}

}


function callReimVal() {
	 
	if (document.getElementById("reimbursingBankpartyidentifier").value == "A") {
		document.getElementById("reimbursingBankAcc").disabled = false;
		document.getElementById("reimbursingBankCode").disabled = false;
		document.getElementById("reimbersingBankLoc").disabled = true;
		document.getElementById("reimbursingBankNameandAddress").disabled = true;
		
	} else if (document.getElementById("reimbursingBankpartyidentifier").value == "B") {
		document.getElementById("reimbursingBankAcc").disabled = false;
		document.getElementById("reimbursingBankCode").disabled = true;
		document.getElementById("reimbersingBankLoc").disabled = false;
		document.getElementById("reimbursingBankNameandAddress").disabled = true;
		
	} else if (document.getElementById("reimbursingBankpartyidentifier").value == "D") {
		document.getElementById("reimbursingBankAcc").disabled = false;
		document.getElementById("reimbursingBankCode").disabled = true;
		document.getElementById("reimbersingBankLoc").disabled = true;
		document.getElementById("reimbursingBankNameandAddress").disabled = false;
	} else {
		document.getElementById("reimbursingBankAcc").disabled = false;
		document.getElementById("reimbursingBankCode").disabled = false;
		document.getElementById("reimbersingBankLoc").disabled = false;
		document.getElementById("reimbursingBankNameandAddress").disabled = false;
	}

}

function callBenific() {
	
	if (document.getElementById("beneficiaryBankpartyidentifier").value == "A") {
		document.getElementById("beneficiaryBankAcc").disabled = false;
		document.getElementById("beneficiaryBankCode").disabled = false;
		document.getElementById("beneficiaryBankNameAndAddress").disabled = true;
		
	} else if (document.getElementById("beneficiaryBankpartyidentifier").value == "D") {
		document.getElementById("beneficiaryBankAcc").disabled = false;
		document.getElementById("beneficiaryBankCode").disabled = true;
		document.getElementById("beneficiaryBankNameAndAddress").disabled = false;
	} else {
		document.getElementById("beneficiaryBankAcc").disabled = false;
		document.getElementById("beneficiaryBankCode").disabled = false;
		document.getElementById("beneficiaryBankNameAndAddress").disabled = false;
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
					document.forms[0].action="saveTemplate";
					document.forms[0].submit();
				}
		} 
}

function callNaraandSender()
{
	var senderReceiverInfo = document.getElementById("SendertoReceiverInformation").value;
	var narrative = document.getElementById("Narrative").value;
		var isValid = true;
	if(senderReceiverInfo!='' && narrative!='')
		{
			alert("Please Enter Either Sender to Receiver Information Field or Narrative Field ");
			isValid = false;
			return isValid;
		}
	else{
			isValid=true;
			return isValid;
		}
	
	}


function checkpricAmountOption() {
	 
	if(document.getElementById("pricAmount").value == "A") 
    {
   	 	dojo.widget.byId ('amountPaidDate'). enable();
  	 }
   else 
    { 
   		dojo.widget.byId ('amountPaidDate'). disable();
  	 }
}

function checktotalAmountClaimOption() {
	 
	if(document.getElementById("totalAmountClaim").value == "A") 
    {
   	 	dojo.widget.byId ('totalPaidDate'). enable();
  	 }
   else 
    { 
   		dojo.widget.byId ('totalPaidDate'). disable();
  	 }
}

function callPrintPreview(){
	document.forms[0].action="printPreviewAdviceofPayment";
	document.forms[0].submit();
}

function cancel()
{
	var isConfirm = confirm("Please confirm");
	if(isConfirm)
		{
			document.forms[0].action = "resetAdviceofPayment";
			document.forms[0].submit();
		}

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
	href="#">Outbound Payments</a>&nbsp;>&nbsp;Advice of Payment</span></div>
<div id="content">
<h1>Advice of Payment(MT-754)</h1>
<s:form method="post" id="form">
<s:hidden id="searchAction" name="searchAction"></s:hidden>	
<s:hidden name="repair" id="repair"></s:hidden>
<s:hidden name="IFSCFLAG" id="IFSCFLAG" ></s:hidden>
<s:hidden id="searchAction" name="searchAction"></s:hidden>	
<s:hidden name="IFSCFLAG" id="IFSCFLAG" ></s:hidden>
<s:hidden id="searchAction" name="searchAction"></s:hidden>
<s:hidden id="repairComments" name="repairComments"></s:hidden> 
	<s:hidden id="saveAction" name="saveAction"></s:hidden>
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
	    <s:label cssClass="MandatoryField" value="%{getText('label.754.SenderBanksReference')}"></s:label> <span class="MandatoryField">[Mandatory]</span>
	  </td>
	</tr>
	<tr>
	  <td width="100%" class="text">
	    <s:textfield name="lcNumber" id="lcNumber" maxLength="16"></s:textfield>
	  </td>
	</tr>
	<tr>
	  <td width="100%" class="textLeft">
	     <s:label cssClass="MandatoryField" value="%{getText('label.754.relatedReferenceNumber')}"></s:label> <span class="MandatoryField">[Mandatory]</span>
	  </td>
	</tr>
	<tr>
	  <td width="100%" class="text">
	    <s:textfield name="relatedReference" id="relatedReference" maxLength="16"></s:textfield>
	  </td>
	</tr>	
	<tr>
	  <td width="100%" class="textLeft">
	    <s:label cssClass="MandatoryField" value="%{getText('label.754.PrincipalAmount')}"></s:label> <span class="MandatoryField">[Mandatory]</span>
	  </td>
	</tr>
	<tr>
	  <td width="100%" class="text">
	    <s:select list="{'A','B'}" headerKey=""  headerValue="Select PID" name="pricAmount" id="pricAmount" onChange="javascript:checkpricAmountOption();"></s:select>
	  </td>
	</tr>
	<tr>
	  <td width="100%" class="textLeft">
	    <s:label cssClass="MandatoryField" value="%{getText('label.754.amountPaidDate')}"></s:label> <span class="MandatoryField">[Mandatory]</span>
	  </td>
	</tr>
	<tr>
	  <td width="100%" class="text">
	    <sx:datetimepicker name="amountPaidDate" cssClass="txtField" id="amountPaidDate"  displayFormat="MM/dd/yyyy" type="date"/>
	  </td>
	</tr>
	<tr>
	  <td width="100%" class="textLeft">
	    <s:label cssClass="MandatoryField" value="%{getText('label.754.Currency')}"></s:label> <span class="MandatoryField">[Mandatory]</span>
	  </td>
	</tr>
	<tr>
	  <td width="100%" class="text">
	    	<s:select list="currCodeDropDown" name="msgCurrency" id="msgCurrency"  headerKey="" headerValue="Select Currency Code" cssClass="txtField" ></s:select> <b>[The currency code in the amount fields 32a and 34a must be the same]</b>
	  </td>
	</tr>
	<tr>
	  <td width="100%" class="textLeft">
	    <s:label	cssClass="MandatoryField" value="%{getText('label.754.PrincipalAmountClaimed')}"></s:label> <span class="MandatoryField">[Mandatory]</span>
	  </td>
	</tr>
	<tr>
	  <td width="100%" class="text">
	    <s:textfield name="principalAmount" id="principalAmount" onChange='callAmountDecimal(this);' maxLength="15"></s:textfield>
	  </td>
	</tr>
	
	<tr>
	  <td width="100%" class="textLeft">
	     <s:label value="%{getText('label.754.currency')}"></s:label> <span class="optional">[Optional]</span>
	  </td>
	</tr>
	<tr>
	  <td width="100%" class="text">
	    <s:select list="currCodeDropDown" name="lcCurrency" id="lcCurrency"  headerKey="" headerValue="Select Currency Code" cssClass="txtField" ></s:select>
	  </td>
	</tr>
	<tr>	
	  <td width="100%" class="textLeft">
	    <s:label	value="%{getText('label.754.additionalAmounts')}"></s:label> <span class="optional">[Optional]</span>
	  </td>
	</tr>
	<tr>
	  <td width="100%" class="text">
	    <s:textfield name="additionalAmount" id="additionalAmount" onChange='callAmountDecimal(this);' maxLength="15"></s:textfield>
	  </td>
	</tr>
	<tr>
	  <td width="100%" class="textLeft">
	    <s:label value="%{getText('label.754.ChargesDeducted')}"></s:label> <span class="optional">[Optional]</span>
	  </td>
	</tr>
	<tr>
	  <td width="100%" class="text">
	    <s:textarea name="chargesDeducted" id="chargesDeducted" cols="35" rows="6" style="overflow: scroll; resize: none;" wrap="HARD" onKeyPress='return maxLength(this,"209","Narrative","35",event);'></s:textarea>
	  </td>
	</tr>
	<tr>
	  <td width="100%" class="textLeft">
	    <s:label value="%{getText('label.754.ChargesAdded')}"></s:label> <span class="optional">[Optional]</span>
	  </td>
	</tr>
	<tr>
	  <td width="100%" class="text">
	    <s:textarea name="chargesAdded" id="chargesAdded" cols="35" rows="6"  style="overflow: scroll; resize: none;" wrap="HARD" onKeyPress='return maxLength(this,"210","SendertoReceiverInformation","35",event);'></s:textarea>
	  </td>
	</tr>
	<tr>
	  <td width="100%" class="textLeft">
	    <s:label value="%{getText('label.754.TotalAmountClaimed')}"></s:label> <span class="optional">[Optional]</span>
	  </td>
	</tr>
	<tr>
	  <td width="100%" class="text">
	    <s:select list="{'A','B'}" headerKey=""  headerValue="Select PID" name="totalAmountClaim" id="totalAmountClaim" onChange="javascript:checktotalAmountClaimOption();"></s:select>
	  </td>
	</tr>
	<tr>
	  <td width="100%" class="textLeft">
	    <s:label value="%{getText('label.754.TotalAmountClaimedDate')}"></s:label> <span class="optional">[Optional]</span>
	  </td>
	</tr>
	<tr>
	  <td width="100%" class="text">
	    <sx:datetimepicker name="totalPaidDate" cssClass="txtField" id="totalPaidDate"  displayFormat="MM/dd/yyyy" type="date"/>
	  </td>
	</tr>
	<tr>
	  <td width="100%" class="textLeft">
	    <s:label value="%{getText('label.754.CUrrency')}"></s:label> <span class="optional">[Optional]</span>
	  </td>
	</tr>
	<tr>
	  <td width="100%" class="text">
	    <s:select list="currCodeDropDown" name="currency" id="currency"  headerKey="" headerValue="Select Currency Code" cssClass="txtField" ></s:select>
	  </td>
	</tr>
	<tr>
	  <td width="100%" class="textLeft">
	    <s:label value="%{getText('label.754.Amount')}"></s:label> <span class="optional">[Optional]</span>
	  </td>
	</tr>
	<tr>
	  <td width="100%" class="text">
	    <s:textfield name="totalAmount" id="totalAmount" onChange='callAmountDecimal(this);' maxLength="15"></s:textfield>
	  </td>
	</tr>
	<tr>
	  <td width="100%" class="textLeft">
	    <s:label value="%{getText('label.754.ReimbursingBank')}"></s:label> <span class="optional">[Optional]</span>
	  </td>
	</tr>
	<tr>
	  <td width="100%" class="text">
	    <s:select list="{'A','B','D'}" headerKey=""  headerValue="Select PID" name="reimbursingBankpartyidentifier" id="reimbursingBankpartyidentifier" onChange="javascript:callReimVal();"></s:select>
			<s:textfield name="reimbursingBankAcc" id="reimbursingBankAcc" maxLength="34"   ></s:textfield> <b>[Either field 53a or 57a may be present, but not both]</b>
	  </td>
	</tr>
	<tr>
	  <td width="100%" class="textLeft">
	    <s:label	value="%{getText('label.754.ReimbursingBankCode')}"></s:label> <span class="optional">[Optional]</span>
	  </td>
	</tr>
	<tr>
	  <td width="100%" class="text">
	    <s:textfield name="reimbursingBankCode" id="reimbursingBankCode"  ></s:textfield>
	      <input type="button" value="Search IFSC..." onclick="return callSearchIFSC('REIMBUSINGIFSC','BOTH')" id="btnadviceThrough"class="btn" />
	  </td>
	</tr>
	<tr>
	  <td width="100%" class="textLeft">
	    <s:label value="%{getText('label.754.ReimbursingBankLocation')}"></s:label> <span class="optional">[Optional]</span>
	  </td>
	</tr>
	<tr>
	  <td width="100%" class="text">
	    <s:textfield name="reimbersingBankLoc" id="reimbersingBankLoc" maxLength="35"></s:textfield>
	  </td>
	</tr>
	<tr>
	 <td width="100%" class="textLeft">
	   <s:label value="%{getText('label.754.ReimbursingBankNameandAddess')}"></s:label> <span class="optional">[Optional]</span>
	 </td>
	</tr>
	<tr>
	 <td width="100%" class="text">
	   <s:textarea name="reimbursingBankNameandAddress" id="reimbursingBankNameandAddress" rows="4" cols="35" style="overflow: scroll; resize: none;" wrap="HARD" onKeyPress='return maxLength(this,"140","chargeDetails","35",event);'></s:textarea>
	 </td>	
	</tr>
	<tr>			
	  <td width="100%" class="textLeft">
	     <s:label value="%{getText('label.754.accountWithPartyIdentifier1')}"></s:label> <span class="optional">[Optional]</span>
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
	    <s:label value="%{getText('label.754.accountWithBank')}"></s:label> <span class="optional">[Optional]</span>
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
	     <s:label value="%{getText('label.754.accountWithPartyLocation')}"></s:label> <span class="optional">[Optional]</span>
	   </td>
	</tr>
	<tr>
	  <td width="100%" class="text">
	    <s:textfield name="accountWithPartyLoc" id="accountWithPartyLoc"  maxLength="35"></s:textfield>
	  </td>
	</tr>
	<tr>
	  <td width="100%" class="textLeft">
	    <s:label value="%{getText('label.754.accountWithNameAndAddress')}"></s:label> <span class="optional">[Optional]</span>
	  </td>
	</tr>
	<tr>
	  <td width="100%" class="text">
	    <s:textarea name="accountWithPartyNameAndAddress" id="accountWithPartyNameAndAddress" rows = "4" cols="35" wrap="HARD" onkeyup="callAdvise()" onKeyPress='return maxLength(this,"140","adviseThroughBankName","35",event);'></s:textarea>
	  </td>	
	</tr>
	<tr>
	  <td width="100%" class="textLeft">
	    <s:label value="%{getText('label.754.beneficiarybank')}"></s:label> <span class="optional">[Optional]</span>
	  </td>
	</tr>
	<tr>
	  <td width="100%" class="text">
	    <s:select list="{'A','D'}" headerKey=""  headerValue="Select PID" name="beneficiaryBankpartyidentifier" id="beneficiaryBankpartyidentifier" onChange="javascript:callBenific();"></s:select>
			<s:textfield name="beneficiaryBankAcc" id="beneficiaryBankAcc" maxLength="34"   ></s:textfield>
	  </td>
	</tr>
	<tr>
	  <td width="100%" class="textLeft">
	    <s:label	value="%{getText('label.754.BeneficiarybankBank')}"></s:label> <span class="optional">[Optional]</span>
	  </td>
	</tr>
	<tr>
	  <td width="100%" class="text">
	     <s:textfield name="beneficiaryBankCode" id="beneficiaryBankCode"  ></s:textfield>
	       <input type="button" value="Search IFSC..." onclick="return callSearchIFSC('BENEFICIARYBANKTHOUGHIFSC','BOTH')" id="btnadviceThrough"class="btn" />
	  </td>
	</tr>	
	<tr>
	  <td width="100%" class="textLeft">
	    <s:label value="%{getText('label.754.BeneficiaryBankNameandAddess')}"></s:label> <span class="optional">[Optional]</span>
	  </td>
	</tr>
	<tr>
	  <td width="100%" class="text">
	    <s:textarea name="beneficiaryBankNameAndAddress" id="beneficiaryBankNameAndAddress" rows="4" cols="35" style="overflow: scroll; resize: none;" wrap="HARD" onKeyPress='return maxLength(this,"140","chargeDetails","35",event);'></s:textarea>
	  </td>	
	</tr>
	<tr>	
	  <td width="100%" class="textLeft">
	    <s:label	value="%{getText('label.754.SendertoReceiverInformation')}"></s:label> <span class="optional">[Optional]</span>
	  </td>
	</tr>
	<tr>
	  <td width="100%" class="text">
	    <s:textarea name="SendertoReceiverInformation" id="SendertoReceiverInformation" cols="35" rows="6"  style="overflow: scroll; resize: none;" wrap="HARD" onKeyPress='return maxLength(this,"210","SendertoReceiverInformation","35",event);'></s:textarea>  <b>[Either field 72 or 77A may be present, but not both]</b>
	  </td>
	</tr>
	<tr>
	  <td width="100%" class="textLeft">
	    <s:label value="%{getText('label.754.Narrative')}"></s:label> <span class="optional">[Optional]</span>
	  </td>
	</tr>
	<tr>
	  <td width="100%" class="text">
	    <s:textarea name="Narrative" id="Narrative" rows="20" cols="35" style="overflow: scroll; resize: none;" wrap="HARD" onKeyPress='return maxLength(this,"700","chargeDetails","35",event);'></s:textarea>
	  </td>	
	</tr>
	 
	</s:if>	
<s:if test="%{repair==''}">
	<tr>
	  <td width="100%" class="textLeft">
	    <s:label cssClass="MandatoryField" value="%{getText('label.754.SenderBanksReference')}"></s:label> <span class="MandatoryField">[Mandatory]</span>
	  </td>
	</tr>
	<tr>
	  <td width="100%" class="text">
	    <s:textfield name="lcNumber" id="lcNumber" maxLength="16"></s:textfield>
	  </td>
	</tr>
	<tr>
	  <td width="100%" class="textLeft">
	     <s:label cssClass="MandatoryField" value="%{getText('label.754.relatedReferenceNumber')}"></s:label> <span class="MandatoryField">[Mandatory]</span>
	  </td>
	</tr>
	<tr>
	  <td width="100%" class="text">
	    <s:textfield name="relatedReference" id="relatedReference" maxLength="16"></s:textfield>
	  </td>
	</tr>	
	<tr>
	  <td width="100%" class="textLeft">
	    <s:label cssClass="MandatoryField" value="%{getText('label.754.PrincipalAmount')}"></s:label> <span class="MandatoryField">[Mandatory]</span>
	  </td>
	</tr>
	<tr>
	  <td width="100%" class="text">
	    <s:select list="{'A','B'}" headerKey=""  headerValue="Select PID" name="pricAmount" id="pricAmount" onChange="javascript:checkpricAmountOption();"></s:select>
	  </td>
	</tr>
	<tr>
	  <td width="100%" class="textLeft">
	    <s:label cssClass="MandatoryField" value="%{getText('label.754.amountPaidDate')}"></s:label> <span class="MandatoryField">[Mandatory]</span>
	  </td>
	</tr>
	<tr>
	  <td width="100%" class="text">
	    <sx:datetimepicker name="amountPaidDate" cssClass="txtField" id="amountPaidDate"  displayFormat="MM/dd/yyyy" type="date"/>
	  </td>
	</tr>
	<tr>
	  <td width="100%" class="textLeft">
	    <s:label cssClass="MandatoryField" value="%{getText('label.754.Currency')}"></s:label> <span class="MandatoryField">[Mandatory]</span>
	  </td>
	</tr>
	<tr>
	  <td width="100%" class="text">
	    <s:select list="currCodeDropDown" name="msgCurrency" id="msgCurrency"  headerKey="" headerValue="Select Currency Code" cssClass="txtField" ></s:select> <b>[The currency code in the amount fields 32a and 34a must be the same]</b>
	  </td>
	</tr>
	<tr>
	  <td width="100%" class="textLeft">
	    <s:label cssClass="MandatoryField" value="%{getText('label.754.PrincipalAmountClaimed')}"></s:label> <span class="MandatoryField">[Mandatory]</span>
	  </td>
	</tr>
	<tr>
	  <td width="100%" class="text">
	    <s:textfield name="principalAmount" id="principalAmount" onChange='callAmountDecimal(this);' maxLength="15"></s:textfield>
	  </td>
	</tr>
	
	<tr>
	  <td width="100%" class="textLeft">
	     <s:label value="%{getText('label.754.currency')}"></s:label> <span class="optional">[Optional]</span>
	  </td>
	</tr>
	<tr>
	  <td width="100%" class="text">
	     <s:select list="currCodeDropDown" name="lcCurrency" id="lcCurrency"  headerKey="" headerValue="Select Currency Code" cssClass="txtField" ></s:select>
	  </td>
	</tr>
	<tr>	
	  <td width="100%" class="textLeft">
	    <s:label value="%{getText('label.754.additionalAmounts')}"></s:label> <span class="optional">[Optional]</span>
	  </td>
	</tr>
	<tr>
	  <td width="100%" class="text">
	    <s:textfield name="additionalAmount" id="additionalAmount" onChange='callAmountDecimal(this);' maxLength="15"></s:textfield>
	  </td>
	</tr>
	<tr>
	  <td width="100%" class="textLeft">
	    <s:label value="%{getText('label.754.ChargesDeducted')}"></s:label> <span class="optional">[Optional]</span>
	  </td>
	</tr>
	<tr>
	  <td width="100%" class="text">
	    <s:textarea name="chargesDeducted" id="chargesDeducted" cols="35" rows="6" style="overflow: scroll; resize: none;" wrap="HARD" onKeyPress='return maxLength(this,"209","Narrative","35",event);'></s:textarea>
	  </td>
	</tr>
	<tr>
	  <td width="100%" class="textLeft">
	    <s:label value="%{getText('label.754.ChargesAdded')}"></s:label> <span class="optional">[Optional]</span>
	  </td>
	</tr>
	<tr>
	  <td width="100%" class="text">
	    <s:textarea name="chargesAdded" id="chargesAdded" cols="35" rows="6"  style="overflow: scroll; resize: none;" wrap="HARD" onKeyPress='return maxLength(this,"210","SendertoReceiverInformation","35",event);'></s:textarea>
	  </td>
	</tr>
	<tr>
	  <td width="100%" class="textLeft">
	    <s:label value="%{getText('label.754.TotalAmountClaimed')}"></s:label> <span class="optional">[Optional]</span>
	  </td>
	</tr>
	<tr>
	  <td width="100%" class="text">
	    <s:select list="{'A','B'}" headerKey=""  headerValue="Select PID" name="totalAmountClaim" id="totalAmountClaim" onChange="javascript:checktotalAmountClaimOption();"></s:select>
	  </td>
	</tr>
	<tr>
	  <td width="100%" class="textLeft">
	    <s:label value="%{getText('label.754.TotalAmountClaimedDate')}"></s:label> <span class="optional">[Optional]</span>
	  </td>
	</tr>
	<tr>
	  <td width="100%" class="text">
	    <sx:datetimepicker name="totalPaidDate" cssClass="txtField" id="totalPaidDate"  displayFormat="MM/dd/yyyy" type="date"/>
	  </td>
	</tr>
	<tr>
	  <td width="100%" class="textLeft">
	    <s:label value="%{getText('label.754.CUrrency')}"></s:label> <span class="optional">[Optional]</span>
	  </td>
	</tr>
	<tr>
	  <td width="100%" class="text">
	     <s:select list="currCodeDropDown" name="currency" id="currency"  headerKey="" headerValue="Select Currency Code" cssClass="txtField" ></s:select>
	  </td>
	</tr>
	<tr>
	  <td width="100%" class="textLeft">
	    <s:label value="%{getText('label.754.Amount')}"></s:label> <span class="optional">[Optional]</span>
	  </td>
	</tr>
	<tr>
	  <td width="100%" class="text">
	    <s:textfield name="totalAmount" id="totalAmount" onChange='callAmountDecimal(this);' maxLength="15"></s:textfield>
	  </td>
	</tr>
	<tr>
	  <td width="100%" class="textLeft">
	    <s:label value="%{getText('label.754.ReimbursingBank')}"></s:label> <span class="optional">[Optional]</span>
	  </td>
	</tr>
	<tr>
	  <td width="100%" class="text">
	    <s:select list="{'A','B','D'}" headerKey=""  headerValue="Select PID" name="reimbursingBankpartyidentifier" id="reimbursingBankpartyidentifier" onChange="javascript:callReimVal();"></s:select>
			<s:textfield name="reimbursingBankAcc" id="reimbursingBankAcc" maxLength="34"   ></s:textfield> <b>[Either field 53a or 57a may be present, but not both]</b>
	  </td>
	</tr>
	<tr>
	  <td width="100%" class="textLeft">
	    <s:label	value="%{getText('label.754.ReimbursingBankCode')}"></s:label> <span class="optional">[Optional]</span>
	  </td>
	</tr>
	<tr>
	  <td width="100%" class="text">
	    <s:textfield name="reimbursingBankCode" id="reimbursingBankCode"  ></s:textfield>
	      <input type="button" value="Search IFSC..." onclick="return callSearchIFSC('REIMBUSINGIFSC','BOTH')" id="btnadviceThrough"class="btn" />
	  </td>
	</tr>
	<tr>
	  <td width="100%" class="textLeft">
	    <s:label value="%{getText('label.754.ReimbursingBankLocation')}"></s:label> <span class="optional">[Optional]</span>
	  </td>
	</tr>
	<tr>
	  <td width="100%" class="text">
	    <s:textfield name="reimbersingBankLoc" id="reimbersingBankLoc"></s:textfield>
	  </td>
	</tr>
	<tr>
	 <td width="100%" class="textLeft">
	   <s:label value="%{getText('label.754.ReimbursingBankNameandAddess')}"></s:label> <span class="optional">[Optional]</span>
	 </td>
	</tr>
	<tr>
	 <td width="100%" class="text">
	   <s:textarea name="reimbursingBankNameandAddress" id="reimbursingBankNameandAddress" rows="4" cols="35" style="overflow: scroll; resize: none;" wrap="HARD" onKeyPress='return maxLength(this,"140","chargeDetails","35",event);'></s:textarea>
	 </td>	
	</tr>
	<tr>			
	  <td width="100%" class="textLeft">
	     <s:label value="%{getText('label.754.accountWithPartyIdentifier1')}"></s:label> <span class="optional">[Optional]</span>
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
	    <s:label	value="%{getText('label.754.accountWithBank')}"></s:label> <span class="optional">[Optional]</span>
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
	     <s:label value="%{getText('label.754.accountWithPartyLocation')}"></s:label> <span class="optional">[Optional]</span>
	   </td>
	</tr>
	<tr>
	  <td width="100%" class="text">
	    <s:textfield name="accountWithPartyLoc" id="accountWithPartyLoc"  maxLength="35"></s:textfield>
	  </td>
	</tr>
	<tr>
	  <td width="100%" class="textLeft">
	    <s:label	value="%{getText('label.754.accountWithNameAndAddress')}"></s:label> <span class="optional">[Optional]</span>
	  </td>
	</tr>
	<tr>
	  <td width="100%" class="text">
	    <s:textarea name="accountWithPartyNameAndAddress" id="accountWithPartyNameAndAddress" rows = "4" cols="35" wrap="HARD" onkeyup="callAdvise()" onKeyPress='return maxLength(this,"140","adviseThroughBankName","35",event);'></s:textarea>
	  </td>	
	</tr>
	<tr>
	  <td width="100%" class="textLeft">
	    <s:label value="%{getText('label.754.beneficiarybank')}"></s:label> <span class="optional">[Optional]</span>
	  </td>
	</tr>
	<tr>
	  <td width="100%" class="text">
	    <s:select list="{'A','D'}" headerKey=""  headerValue="Select PID" name="beneficiaryBankpartyidentifier" id="beneficiaryBankpartyidentifier" onChange="javascript:callBenific();"></s:select>
			<s:textfield name="beneficiaryBankAcc" id="beneficiaryBankAcc" maxLength="34"   ></s:textfield>
	  </td>
	</tr>
	<tr>
	  <td width="100%" class="textLeft">
	    <s:label	value="%{getText('label.754.BeneficiarybankBank')}"></s:label> <span class="optional">[Optional]</span>
	  </td>
	</tr>
	<tr>
	  <td width="100%" class="text">
	     <s:textfield name="beneficiaryBankCode" id="beneficiaryBankCode"  ></s:textfield>
	       <input type="button" value="Search IFSC..." onclick="return callSearchIFSC('BENEFICIARYBANKTHOUGHIFSC','BOTH')" id="btnadviceThrough"class="btn" />
	  </td>
	</tr>	
	<tr>
	  <td width="100%" class="textLeft">
	    <s:label value="%{getText('label.754.BeneficiaryBankNameandAddess')}"></s:label> <span class="optional">[Optional]</span>
	  </td>
	</tr>
	<tr>
	  <td width="100%" class="text">
	    <s:textarea name="beneficiaryBankNameAndAddress" id="beneficiaryBankNameAndAddress" rows="4" cols="35" style="overflow: scroll; resize: none;" wrap="HARD" onKeyPress='return maxLength(this,"140","chargeDetails","35",event);'></s:textarea>
	  </td>	
	</tr>
	<tr>	
	  <td width="100%" class="textLeft">
	    <s:label	value="%{getText('label.754.SendertoReceiverInformation')}"></s:label> <span class="optional">[Optional]</span>
	  </td>
	</tr>
	<tr>
	  <td width="100%" class="text">
	    <s:textarea name="SendertoReceiverInformation" id="SendertoReceiverInformation" cols="35" rows="6"  style="overflow: scroll; resize: none;" wrap="HARD" onKeyPress='return maxLength(this,"210","SendertoReceiverInformation","35",event);'></s:textarea>  <b>[Either field 72 or 77A may be present, but not both]</b>
	  </td>
	</tr>
	<tr>
	  <td width="100%" class="textLeft">
	    <s:label value="%{getText('label.754.Narrative')}"></s:label> <span class="optional">[Optional]</span>
	  </td>
	</tr>
	<tr>
	  <td width="100%" class="text">
	    <s:textarea name="Narrative" id="Narrative" rows="20" cols="35" style="overflow: scroll; resize: none;" wrap="HARD" onKeyPress='return maxLength(this,"700","chargeDetails","35",event);'></s:textarea>
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
				
					<input type ="button" value="Submit"  class="btn"  onclick="callSubmit()"/> 	
					<input type="reset" value="Reset" onclick="cancel()" class="btn"  />
					<input type="button" value="Save Template" onclick="SaveTemplate()" class="btn"  />
					<!--<input type="button" value="Print" onclick="window.print()">
			--></s:if>
			<s:if test="%{checkForSubmit=='Display_Approve_Reject'}">
				<s:if test="%{validUserToApprove}">
					<input type="button" value="Approve" onclick="callSubmitToSave('Approve')">
					<input type="button" value="Reject" onclick="callSubmitToSave('Reject')">
					<input type="button" value="Cancel" onclick="cancel()">
					<input type="button" value="Print Preview" onclick="callPrintPreview()">
				</s:if>
				<s:if test="%{!validUserToApprove}">
					<input type ="button" value="Submit"  class="btn"  onclick="callSubmit()"/> 	
					<input type="reset" value="Cancel" onclick="cancel()" class="btn"  />
					<input type="button" value="Save Template" onclick="SaveTemplate()" class="btn"  />
				</s:if>
			</s:if>
					
				
			</td>
		</tr>
	</table>

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
			for ( var i = 0; i < aa.length-5; i++) 
				{
					aa.elements[i].disabled = true;
				}
			
		}
		else
		{
			for ( var i = 0; i < aa.length-5; i++) 
			{
				aa.elements[i].enabled = true;
			}
		}
}

</script>