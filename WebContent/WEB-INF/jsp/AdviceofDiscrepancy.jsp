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
function callSubmit(saveAction){
	var aa = document.getElementById('form');
	for ( var i = 0; i < aa.length; i++) {
		aa.elements[i].disabled = false;
	}
	
	document.getElementById("saveAction").value = saveAction;
	document.forms[0].action="saveAdviceofDiscrepancy";
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
	 
	 

	//alert("adviseThroughBankCode : - "+adviseThroughBankCode +" adviseThroughBankLocation : - "+adviseThroughBankLocation+ " adviseThroughBankName:- "+adviseThroughBankName)
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
					document.forms[0].action="saveTemplateAdviceofDiscrepancy";
					document.forms[0].submit();
				}
		} 
}

function callPrintPreview(){
	document.forms[0].action="printPreviewAdviceofDiscrepancy";
	document.forms[0].submit();
}

function cancel()
{
	var isConfirm = confirm("Please confirm");
	if(isConfirm)
		{
			document.forms[0].action = "resetAdviceofDiscrepancy";
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
	href="#">Outbound Payments</a>&nbsp;>&nbsp;Advice of Discrepancy</span></div>
<div id="content">
<h1>Advice of Discrepancy (MT-750)</h1>
<s:form method="post" id="form">
<s:hidden id="searchAction" name="searchAction"></s:hidden>	
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
	    <s:label	cssClass="MandatoryField" value="%{getText('label.750.SenderBanksReference')}"></s:label> <span class="MandatoryField">[Mandatory]</span>
	  </td>
    </tr>
	<tr>
	  <td width="100%" class="text">
	    <s:textfield name="lcNumber" id="lcNumber" maxLength="16"></s:textfield>
	  </td>
	</tr>
	<tr>
	  <td width="100%" class="textLeft">
	    <s:label cssClass="MandatoryField" value="%{getText('label.750.relatedReferenceNumber')}"></s:label> <span class="MandatoryField">[Mandatory]</span>
	  </td>
	</tr>
	<tr>
	  <td width="100%" class="text">
	    <s:textfield name="relatedReference" id="relatedReference" maxLength="16"></s:textfield>
	  </td>
	</tr>
	<tr>
	  <td width="100%" class="textLeft">
	    <s:label cssClass="MandatoryField" value="%{getText('label.750.Currency')}"></s:label> <span class="MandatoryField">[Mandatory]</span>
	  </td>
	</tr>
	<tr>
	  <td width="100%" class="text">
	    <s:select list="currCodeDropDown" name="msgCurrency" id="msgCurrency"  headerKey="" headerValue="Select Currency Code" cssClass="txtField" ></s:select>   <b>[The currency code in the amount fields 32B and 34B must be the same] </b>
	  </td>
	</tr>	
	<tr>
	  <td width="100%" class="textLeft">
	    <s:label	cssClass="MandatoryField" value="%{getText('label.750.PrincipalAmountClaimed')}"></s:label> <span class="MandatoryField">[Mandatory]</span>
	  </td>
	</tr>
	<tr>
	  <td width="100%" class="text">
	    <s:textfield name="principalAmount" id="principalAmount" onChange='callAmountDecimal(this);' maxLength="15"></s:textfield>
	  </td>
	</tr>
	<tr>
	  <td width="100%" class="textLeft">
	    <s:label value="%{getText('label.750.currency')}"></s:label> <span class="optional">[Optional]</span>
	  </td>
	</tr>
	<tr>
	  <td width="100%" class="text">
	     <s:select list="currCodeDropDown" name="lcCurrency" id="lcCurrency"  headerKey="" headerValue="Select Currency Code" cssClass="txtField" ></s:select> <b>[If field 33B and/or field 71B and/or field 73 is/are present, field 34B must also be present] </b>
	  </td>
	</tr>
	<tr>
	   <td width="100%" class="textLeft">
	     <s:label	value="%{getText('label.750.additionalAmounts')}"></s:label> <span class="optional">[Optional]</span>
	   </td>
	</tr>
	<tr>
	  <td width="100%" class="text">
	     <s:textfield name="additionalAmount" id="additionalAmount" onChange='callAmountDecimal(this);' maxLength="15"></s:textfield>
	  </td>
	</tr>
	<tr>
	   <td width="100%" class="textLeft">
	     <s:label value="%{getText('label.750.ChargesDeducted')}"></s:label> <span class="optional">[Optional]</span> 
	   </td>
	</tr>
	<tr>
	   <td width="100%" class="text">
	     <s:textarea name="chargesDeducted" id="chargesDeducted" cols="35" rows="6" style="overflow: scroll; resize: none;" wrap="HARD" onKeyPress='return maxLength(this,"210","Narrative","35",event);'></s:textarea>
	   </td>
	</tr>
	<tr>
	   <td width="100%" class="textLeft">
	     <s:label	value="%{getText('label.750.ChargesAdded')}"></s:label> <span class="optional">[Optional]</span>
	   </td>
	</tr>
	<tr>
	   <td width="100%" class="text">
	     <s:textarea name="chargesAdded" id="chargesAdded" cols="35" rows="6"  style="overflow: scroll; resize: none;" wrap="HARD" onKeyPress='return maxLength(this,"210","SendertoReceiverInformation","35",event);'></s:textarea>
	   </td>
	</tr>
	<tr>
	  <td width="100%" class="textLeft">
	    <s:label value="%{getText('label.750.CUrrency')}"></s:label> <span class="optional">[Optional]</span>
	  </td>
	</tr>
	<tr>
	  <td width="100%" class="text">
	    <s:select list="currCodeDropDown" name="currency" id="currency"  headerKey="" headerValue="Select Currency Code" cssClass="txtField" ></s:select>
	  </td>
	</tr>
	<tr>
      <td width="100%" class="textLeft">
        <s:label	value="%{getText('label.750.TotalAmountPaid')}"></s:label> <span class="optional">[Optional]</span>
      </td>
    </tr>
	<tr>
	   <td width="100%" class="text">
	     <s:textfield name="totalAmount" id="totalAmount" onChange='callAmountDecimal(this);' maxLength="15"></s:textfield>
	   </td>
	</tr>
	<tr>
	  <td width="100%" class="textLeft">
	     <s:label value="%{getText('label.750.accountWithPartyIdentifier1')}"></s:label> <span class="optional">[Optional]</span>
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
	    <s:label	value="%{getText('label.750.accountWithBank')}"></s:label> <span class="optional">[Optional]</span>
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
	    <s:label value="%{getText('label.750.accountWithPartyLocation')}"></s:label> <span class="optional">[Optional]</span>
	  </td>
	</tr>
	<tr>
	  <td width="100%" class="text">
	     <s:textfield name="accountWithPartyLoc" id="accountWithPartyLoc" maxLength="35"></s:textfield>
	  </td>
	</tr>
	<tr>
	  <td width="100%" class="textLeft">
	    <s:label	value="%{getText('label.750.accountWithNameAndAddress')}"></s:label> <span class="optional">[Optional]</span>
	  </td>
	</tr>
	<tr>
	  <td width="100%" class="text">
	    <s:textarea name="accountWithPartyNameAndAddress" id="accountWithPartyNameAndAddress" rows = "4" cols="35" wrap="HARD" onkeyup="callAdvise()" onKeyPress='return maxLength(this,"140","adviseThroughBankName","35",event);'></s:textarea>
	  </td>	
	</tr>
	<tr>	
	  <td width="100%" class="textLeft">
	    <s:label	value="%{getText('label.750.SendertoReceiverInformation')}"></s:label> <span class="optional">[Optional]</span>
	  </td>
	</tr>
	<tr>
	  <td width="100%" class="text">
	    <s:textarea name="SendertoReceiverInformation" id="SendertoReceiverInformation" cols="35" rows="6"  style="overflow: scroll; resize: none;" wrap="HARD" onKeyPress='return maxLength(this,"210","SendertoReceiverInformation","35",event);'></s:textarea>
	  </td>
	</tr>
	<tr> 
	  <td width="100%" class="textLeft">
	    <s:label cssClass="MandatoryField" value="%{getText('label.750.Discrepancies')}"></s:label> <span class="MandatoryField">[Mandatory]</span>
	  </td>
	</tr>
	<tr>
	  <td width="100%" class="text">
	    <s:textarea name="discrepancies" id="discrepancies" rows="70" cols="50" style="overflow: scroll; resize: none;" wrap="HARD" onKeyPress='return maxLength(this,"2450","chargeDetails","50",event);'></s:textarea>
	  </td>	
	</tr>
	 
	</s:if>	
    <s:if test="%{repair==''}">
	<tr>
	  <td width="100%" class="textLeft">
	    <s:label	cssClass="MandatoryField" value="%{getText('label.750.SenderBanksReference')}"></s:label> <span class="MandatoryField">[Mandatory]</span>
	  </td>
    </tr>
	<tr>
	  <td width="100%" class="text">
	    <s:textfield name="lcNumber" id="lcNumber" maxLength="16"></s:textfield>
	  </td>
	</tr>
	<tr>
	  <td width="100%" class="textLeft">
	    <s:label cssClass="MandatoryField" value="%{getText('label.750.relatedReferenceNumber')}"></s:label> <span class="MandatoryField">[Mandatory]</span>
	  </td>
	</tr>
	<tr>
	  <td width="100%" class="text">
	    <s:textfield name="relatedReference" id="relatedReference" maxLength="16"></s:textfield>
	  </td>
	</tr>
	<tr>
	  <td width="100%" class="textLeft">
	    <s:label cssClass="MandatoryField" value="%{getText('label.750.Currency')}"></s:label> <span class="MandatoryField">[Mandatory]</span>
	  </td>
	</tr>
	<tr>
	  <td width="100%" class="text">
	    <s:select list="currCodeDropDown" name="msgCurrency" id="msgCurrency"  headerKey="" headerValue="Select Currency Code" cssClass="txtField" ></s:select>  <b>[The currency code in the amount fields 32B and 34B must be the same] </b>
	  </td>
	</tr>	
	<tr>
	  <td width="100%" class="textLeft">
	    <s:label	cssClass="MandatoryField" value="%{getText('label.750.PrincipalAmountClaimed')}"></s:label> <span class="MandatoryField">[Mandatory]</span>
	  </td>
	</tr>
	<tr>
	  <td width="100%" class="text">
	    <s:textfield name="principalAmount" id="principalAmount" onChange='callAmountDecimal(this);' maxLength="15"></s:textfield>
	  </td>
	</tr>
	<tr>
	  <td width="100%" class="textLeft">
	    <s:label value="%{getText('label.750.currency')}"></s:label> <span class="optional">[Optional]</span>
	  </td>
	</tr>
	<tr>
	  <td width="100%" class="text">
	   <s:select list="currCodeDropDown" name="lcCurrency" id="lcCurrency"  headerKey="" headerValue="Select Currency Code" cssClass="txtField" ></s:select>   <b>[If field 33B and/or field 71B and/or field 73 is/are present, field 34B must also be present] </b>
	  </td>
	</tr>
	<tr>
	   <td width="100%" class="textLeft">
	     <s:label	value="%{getText('label.750.additionalAmounts')}"></s:label> <span class="optional">[Optional]</span>
	   </td>
	</tr>
	<tr>
	  <td width="100%" class="text">
	     <s:textfield name="additionalAmount" id="additionalAmount" onChange='callAmountDecimal(this);' maxLength="15"></s:textfield>
	  </td>
	</tr>
	<tr>
	   <td width="100%" class="textLeft">
	     <s:label value="%{getText('label.750.ChargesDeducted')}"></s:label> <span class="optional">[Optional]</span> 
	   </td>
	</tr>
	<tr>
	   <td width="100%" class="text">
	     <s:textarea name="chargesDeducted" id="chargesDeducted" cols="35" rows="6" style="overflow: scroll; resize: none;" wrap="HARD" onKeyPress='return maxLength(this,"210","Narrative","35",event);'></s:textarea>
	   </td>
	</tr>
	<tr>
	   <td width="100%" class="textLeft">
	     <s:label	value="%{getText('label.750.ChargesAdded')}"></s:label> <span class="optional">[Optional]</span>
	   </td>
	</tr>
	<tr>
	   <td width="100%" class="text">
	     <s:textarea name="chargesAdded" id="chargesAdded" cols="35" rows="6"  style="overflow: scroll; resize: none;" wrap="HARD" onKeyPress='return maxLength(this,"210","SendertoReceiverInformation","35",event);'></s:textarea>
	   </td>
	</tr>
	<tr>
	  <td width="100%" class="textLeft">
	    <s:label value="%{getText('label.750.CUrrency')}"></s:label> <span class="optional">[Optional]</span>
	  </td>
	</tr>
	<tr>
	  <td width="100%" class="text">
	   <s:select list="currCodeDropDown" name="currency" id="currency"  headerKey="" headerValue="Select Currency Code" cssClass="txtField" ></s:select> 
	  </td>
	</tr>
	<tr>
      <td width="100%" class="textLeft">
        <s:label	value="%{getText('label.750.TotalAmountPaid')}"></s:label> <span class="optional">[Optional]</span>
      </td>
    </tr>
	<tr>
	   <td width="100%" class="text">
	     <s:textfield name="totalAmount" id="totalAmount" onChange='callAmountDecimal(this);' maxLength="15"></s:textfield>
	   </td>
	</tr>
	<tr>
	  <td width="100%" class="textLeft">
	     <s:label value="%{getText('label.750.accountWithPartyIdentifier1')}"></s:label> <span class="optional">[Optional]</span>
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
	    <s:label	value="%{getText('label.750.accountWithBank')}"></s:label> <span class="optional">[Optional]</span>
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
	    <s:label value="%{getText('label.750.accountWithPartyLocation')}"></s:label> <span class="optional">[Optional]</span>
	  </td>
	</tr>
	<tr>
	  <td width="100%" class="text">
	     <s:textfield name="accountWithPartyLoc" id="accountWithPartyLoc" maxLength="35"></s:textfield>
	  </td>
	</tr>
	<tr>
	  <td width="100%" class="textLeft">
	    <s:label	value="%{getText('label.750.accountWithNameAndAddress')}"></s:label> <span class="optional">[Optional]</span>
	  </td>
	</tr>
	<tr>
	  <td width="100%" class="text">
	    <s:textarea name="accountWithPartyNameAndAddress" id="accountWithPartyNameAndAddress" rows = "4" cols="35" wrap="HARD" onkeyup="callAdvise()" onKeyPress='return maxLength(this,"140","adviseThroughBankName","35",event);'></s:textarea>
	  </td>	
	</tr>
	<tr>	
	  <td width="100%" class="textLeft">
	    <s:label	value="%{getText('label.750.SendertoReceiverInformation')}"></s:label> <span class="optional">[Optional]</span>
	  </td>
	</tr>
	<tr>
	  <td width="100%" class="text">
	    <s:textarea name="SendertoReceiverInformation" id="SendertoReceiverInformation" cols="35" rows="6"  style="overflow: scroll; resize: none;" wrap="HARD" onKeyPress='return maxLength(this,"210","SendertoReceiverInformation","35",event);'></s:textarea>
	  </td>
	</tr>
	<tr> 
	  <td width="100%" class="textLeft">
	    <s:label cssClass="MandatoryField" value="%{getText('label.750.Discrepancies')}"></s:label> <span class="MandatoryField">[Mandatory]</span>
	  </td>
	</tr>
	<tr>
	  <td width="100%" class="text">
	    <s:textarea name="discrepancies" id="discrepancies" rows="70" cols="50" style="overflow: scroll; resize: none;" wrap="HARD" onKeyPress='return maxLength(this,"2450","chargeDetails","50",event);'></s:textarea>
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
				<s:submit value="Submit" action="getAdviceofDiscrepancyApproval" cssClass="btn" />
					<input type="reset" value="Reset" onclick="cancel()" class="btn"  />
					<input type="button" value="Save Template" onclick="SaveTemplate()" class="btn"  />
					<!--<input type="button" value="Print" onclick="window.print()">
			--></s:if>
			<s:if test="%{checkForSubmit=='Display_Approve_Reject'}">
				<s:if test="%{validUserToApprove}">
					<input type="button" value="Approve" onclick="callSubmit('Approve')">
					<input type="button" value="Reject" onclick="callSubmit('Reject')">
					<input type="button" value="Cancel" onclick="cancel()">
					<input type="button" value="Print Preview" onclick="callPrintPreview()">
				</s:if>
				<s:if test="%{!validUserToApprove}">
					<s:submit value="Submit" action="getAdviceofDiscrepancyApproval" cssClass="btn" />
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