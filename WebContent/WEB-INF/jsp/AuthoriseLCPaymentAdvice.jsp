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
				"<s:url action='searchAuthoriseLCPayAdvno' windowState='EXCLUSIVE' />",
				'mywindow', 'top=50,left=250,width=750,height=410');
	}
	else
		{
		callShowGrid();
		}
		
	}

function callShowGrid()
{
	document.forms[0].action="displayAuthoLCPaymentAdData";
	document.forms[0].submit();
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




function callSubmit(saveAction)
{
	var aa = document.getElementById('form');
	for ( var i = 0; i < aa.length; i++) {
		aa.elements[i].disabled = false;
		}
	document.getElementById("saveAction").value = saveAction;
	document.forms[0].action="saveAuthoLcPayment";
	document.forms[0].submit();
}

function callSearchIFSC(Action,flag) {

	document.getElementById("searchAction").value = Action;
	document.getElementById("IFSCFLAG").value = flag;
window.open(
		"<s:url action='showIFSCScreen' windowState='EXCLUSIVE' />",
		'mywindow', 'top=50,left=250,width=750,height=410');
}

function callTolerance() {

	var positiveTolerence = document.getElementById("positiveTolerance").value;
	var negativeTolerance = document.getElementById("negativeTolerance").value;
	var maxCredit = document.getElementById("maximumCreditAmount").value;

	if (positiveTolerence != "" ) {

		document.getElementById("maximumCreditAmount").disabled = true;
	}else if(negativeTolerance != "")
	{
		document.getElementById("maximumCreditAmount").disabled = true;
		}
	 else if (maxCredit != "") {

		document.getElementById("positiveTolerance").disabled = true;
		document.getElementById("negativeTolerance").disabled = true;
	} else {
		document.getElementById("maximumCreditAmount").disabled = false;
		document.getElementById("positiveTolerance").disabled = false;
		document.getElementById("negativeTolerance").disabled = false;
	}
}

function callAuthorised()
{
	var authorisedBankCode = document.getElementById("authorisedBankCode").value;
	var authorisedBankNameAndAddress = document.getElementById("authorisedAndAddress").value;
	if (authorisedBankCode != "") {
		document.getElementById("authorisedAndAddress").disabled = true;
		
	}else if (authorisedBankNameAndAddress != "") {
		document.getElementById("authorisedBankCode").disabled = true;
		document.getElementById("btnAuth").disabled = true;
		
	}else
		{
		document.getElementById("authorisedAndAddress").disabled = false;
		document.getElementById("authorisedBankCode").disabled = false;
		document.getElementById("btnAuth").disabled = false;
		document.getElementById("authorisedAndAddress").value="";
		}
	
}

function callDraweeAt()
{
	var draweeBankNameAddress = document.getElementById("draweeBankNameAddress").value;
	var draweeBankCode = document.getElementById("draweeBankCode").value;
	if (draweeBankCode != "") {
		document.getElementById("draweeBankNameAddress").disabled = true;
		
	}else if (draweeBankNameAddress != "") {
		document.getElementById("draweeBankCode").disabled = true;
		document.getElementById("draweeSearch").disabled = true;
		
	}else
		{
		document.getElementById("draweeBankNameAddress").disabled = false;
		document.getElementById("draweeBankCode").disabled = false;
		document.getElementById("draweeSearch").disabled = false;
		document.getElementById("draweeBankNameAddress").value="";
		}
	
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
	document.forms[0].action="getAuthoriseLCAdvicePayment";
	document.forms[0].submit();
}


function callAdvise() {
	//availableWithIdentifier authorisedBankCode authorisationMode authorisedAndAddress
	if (document.getElementById("availableWithIdentifier").value == "A") {
		document.getElementById("authorisedBankCode").disabled = false;
		document.getElementById("authorisationMode").disabled = false;
		document.getElementById("authorisedAndAddress").disabled = true;
	} else if (document.getElementById("availableWithIdentifier").value == "D") {
		document.getElementById("authorisedBankCode").disabled = true;
		document.getElementById("authorisationMode").disabled = false;
		document.getElementById("authorisedAndAddress").disabled = false;
		
	}else{
		document.getElementById("authorisedBankCode").disabled = false;
		document.getElementById("authorisationMode").disabled = false;
		document.getElementById("authorisedAndAddress").disabled = false;
		
	}

}

function callDraweeBank() {
	//draweeBankpartyidentifier'A','D' draweeBankCode draweeBankNameAddress
	if (document.getElementById("draweeBankpartyidentifier").value == "A") {
		document.getElementById("draweeBankCode").disabled = false;
		document.getElementById("draweeBankNameAddress").disabled = true;
	} else if (document.getElementById("draweeBankpartyidentifier").value == "D") {
		document.getElementById("draweeBankCode").disabled = true;
		document.getElementById("draweeBankNameAddress").disabled = false;
	} else{
		document.getElementById("draweeBankCode").disabled = false;
		document.getElementById("draweeBankNameAddress").disabled = false;
	}
}

function callNegotiation() {
	
	if (document.getElementById("negotiatingBankPartyIdentifier").value == "A") {
		document.getElementById("negotiatingBankCode").disabled = false;
		document.getElementById("negotiatingBankNameAndAddress").disabled = true;
	} else if (document.getElementById("negotiatingBankPartyIdentifier").value == "D") {
		document.getElementById("negotiatingBankCode").disabled = true;
		document.getElementById("negotiatingBankNameAndAddress").disabled = false;
		
	}else{
		document.getElementById("negotiatingBankCode").disabled = false;
		document.getElementById("negotiatingBankNameAndAddress").disabled = false;
		
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
					document.forms[0].action="saveAuthoriseTemplate";
					document.forms[0].submit();
				}
		} 
}

function callPrintPreview(){
	document.forms[0].action="printPreviewAuthoriseLCPaymentAdvice";
	document.forms[0].submit();
}

function cancel()
{
	var isConfirm = confirm("Please confirm");
	if(isConfirm)
		{
			document.forms[0].action = "resetAuthoriseLCPaymentADvice";
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
	href="#">Outbound Payments</a>&nbsp;>&nbsp;Authorization To Reimburse</span></div>
<div id="content">
<h1>Authorization To Reimburse (MT-740)</h1>
<s:form method="post" id="form">
<s:hidden id="searchAction" name="searchAction"></s:hidden>
<s:hidden id="repairComments" name="repairComments"></s:hidden> 
<s:hidden name="repair" id="repair"></s:hidden>
<s:hidden name="IFSCFLAG" id="IFSCFLAG" ></s:hidden>
<s:hidden id="comment" name="comment"></s:hidden>
<s:hidden id="senderBank" name="senderBank"></s:hidden>
<s:hidden name="tempName" id="tempName"></s:hidden>
<s:hidden id="validUserToApprove" name="validUserToApprove"></s:hidden>
<s:hidden id="saveAction" name="saveAction"></s:hidden>

<s:hidden id="msgRef" name="msgRef"></s:hidden>

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
	<s:div id="mandatory"	cssClass="dataGrid" style="width:100%;">
    <table width="100%" cellpadding="3" cellspacing="1" border="0"	bgcolor="#ffffff">
	<s:if test="%{repair!=''}">
		<tr>
		  <td width="100%" class="textLeft">
		     <s:label cssClass="MandatoryField" value="%{getText('label.740.lc_Number')}"></s:label> <span class="MandatoryField">[Mandatory]</span>
		  </td>
		</tr>
		<tr>
	      <td width="100%" class="text">
	        <s:textfield name="lcNumber" id="lcNumber" maxLength="16" onKeyPress='return notAllowCheck(this,event);' ></s:textfield>
		  </td>
	    </tr>
		<tr>
		  <td width="100%" class="textLeft">
		    <s:label value="%{getText('label.740.AccountID')}"></s:label> <span class="optional">[Optional]</span>
		  </td>
		</tr>
		<tr>
		  <td width="100%" class="text">
		    <s:textfield name="acountID" id="acountID" maxLength="35" onKeyPress='return notAllowCheck(this,event);' ></s:textfield>
		  </td>
		</tr>
		<tr>
		  <td width="100%" class="textLeft">
		    <s:label cssClass="MandatoryField" value="%{getText('label.740.applicableRules')}"></s:label> <span class="MandatoryField">[Mandatory]</span>
		  </td>
		</tr>				
		<tr>
		  <td width="100%" class="text">
		    <s:select list="{'NOTURR','URR LATEST VERSION'}" name="applicableRule" id="applicableRule" maxLength="30" headerKey="" headerValue="Select Applicable Rule" ></s:select>
		  </td>
		</tr>
		<tr>
		  <td width="100%" class="textLeft">
		    <s:label value="%{getText('label.740.ExpiryDate')}"></s:label> <span class="optional">[Optional]</span>
		  </td>
		</tr>
		<tr>
		  <td width="100%" class="text">
		    <sx:datetimepicker name="lcExpiryDate" cssClass="txtField" id="lcExpiryDate"  displayFormat="MM/dd/yyyy" type="date" />
		  </td>
		</tr>
		
		<tr>	
		  <td width="100%" class="textLeft">
		    <s:label value="%{getText('label.740.expiryPlace')}"></s:label> <span class="optional">[Optional]</span>
		  </td>
		</tr>
		<tr>
		  <td width="100%" class="text">
		    <s:textfield name="lcExpirePlace" id="lcExpirePlace" maxLength="29" onKeyPress='return notAllowCheck(this,event);' ></s:textfield>
		  </td>
		</tr>
		<tr>
		  <td width="100%" class="textLeft">
		    <s:label value="%{getText('label.740.NegotiatingBankPartyIdentifier')}"></s:label> <span class="optional">[Optional]</span>
		  </td>
		</tr>
		<tr>
		  <td width="100%" class="text">
		    <s:select list="{'A','D'}" name="negotiatingBankPartyIdentifier" id="negotiatingBankPartyIdentifier" headerKey="" headerValue="Select PID" onchange="callNegotiation();"></s:select><a></a><s:textfield name="negotiatingBankAccount" id="negotiatingBankAccount" maxLength="34" onKeyPress='return notAllowCheck(this,event);' ></s:textfield>  <b>[Either field 58a or 59, but not both, may be present]</b>
		  </td>
		</tr>
		<tr>	
		  <td width="100%" class="textLeft">
		    <s:label value="%{getText('label.740.NegotiatingBankCode')}"></s:label> <span class="optional">[Optional]</span>
		  </td>
		</tr>
		<tr>
		  <td width="100%" class="text">
		    <s:textfield name="negotiatingBankCode" id="negotiatingBankCode" maxLength="11" onKeyPress='return notAllowCheck(this,event);'></s:textfield>
		  			<input type="button" value="Search IFSC..."	onclick="callSearchIFSC('NEGOTIATIONIFSC','PARTY')" id ="negotiationSearch" class="btn" />
		  </td>
		</tr>
		<tr>
		  <td width="100%" class="textLeft">
		    <s:label value="%{getText('label.740.NegotiatingBankNameAndAddress')}"></s:label> <span class="optional">[Optional]</span>
		  </td>
		</tr>
		<tr>
		  <td width="100%" class="text">
		    <s:textarea name="negotiatingBankNameAndAddress" id="negotiatingBankNameAndAddress" rows="4" cols="35" wrap="HARD" onKeyPress='return maxLength(this,"139","negotiatingBankNameAndAddress","35",event);'></s:textarea>
		  </td>
		</tr>
		
		<tr>
		  <td width="100%" class="textLeft">
		    <s:label value="%{getText('label.740.beneficiaryNameAddress')}"></s:label> <span class="optional">[Optional]</span>
		  </td>
		</tr>
		<tr>
		  <td width="100%" class="text">
		    <s:textarea name="beneficiaryNameAddress" id="beneficiaryNameAddress" rows="4" cols="35" wrap="HARD" onKeyPress='return maxLength(this,"139","beneficiaryNameAddress","35",event);'></s:textarea>
		  </td>
		</tr>
		<tr>
	      <td width="100%" class="textLeft">
		    <s:label cssClass="MandatoryField" value="%{getText('label.740.creditCurrency')}"></s:label> <span class="MandatoryField">[Mandatory]</span>
		  </td>
		</tr>
		<tr>
		  <td width="100%" class="text">
		   <s:select list="currCodeDropDown" name="lcCurrency" id="lcCurrency"  headerKey="" headerValue="Select Currency Code" cssClass="txtField" ></s:select> 
		  </td>
        </tr>		  
		<tr>
		  <td width="100%" class="textLeft">
		    <s:label cssClass="MandatoryField" value="%{getText('label.740.CreditAmount')}"></s:label> <span class="MandatoryField">[Mandatory]</span>
		  </td>
		</tr>
		<tr>
		  <td width="100%" class="text">
		   <s:textfield name="creditAmount" id="creditAmount" maxLength="15" onChange='callAmountDecimal(this);' onKeyPress='return notAllowCheck(this,event);'></s:textfield>
		  </td>
		</tr>
		<tr>
		  <td width="100%" class="textLeft">
		    <s:label value="%{getText('label.740.positiveTolerance')}"></s:label> <span class="optional">[Optional]</span>
		  </td>
		</tr>
		<tr>
		  <td width="100%" class="text">
		    <s:textfield name="positiveTolerance" id="positiveTolerance" maxLength="2" onchange="callTolerance()" ></s:textfield>  <b>[Either field 39A or 39B, but not both, may be present]</b>
		  </td>
		</tr>
		<tr>
		  <td width="100%" class="textLeft">
		    <s:label value="%{getText('label.740.negativeTolerance')}"></s:label> <span class="optional">[Optional]</span>
		  </td>
		</tr>
		<tr>
		  <td width="100%" class="text">
		    <s:textfield name="negativeTolerance" id="negativeTolerance" maxLength="2" onchange="callTolerance()" ></s:textfield>
		  </td>
		</tr>
		
		<tr>
		  <td width="100%" class="textLeft">
		    <s:label value="%{getText('label.740.maximumCreditAmount')}"></s:label> <span class="optional">[Optional]</span>
		  </td>
		</tr>
		<tr>
		  <td width="100%" class="text">
		    <s:select name="maximumCreditAmount" id="maximumCreditAmount" maxLength="13" headerKey=""	headerValue="Select Maximum Credit Amount" list="{'NOT EXCEEDING'}"	onchange="callTolerance()"></s:select>
		  </td>
		</tr>
		<tr>
		  <td width="100%" class="textLeft">
		    <s:label value="%{getText('label.740.AdditionalAmountsCovered')}"></s:label> <span class="optional">[Optional]</span>
		  </td>
		</tr>
		<tr>
		  <td width="100%" class="text">
		    <s:textarea name="additionalAmountsCovered" id="additionalAmountsCovered" rows="4" cols="35" wrap="HARD" onKeyPress='return maxLength(this,"139","additionalAmountsCovered","35",event);' ></s:textarea>
		  </td>
		</tr>
		<tr>
		  <td width="100%" class="textLeft">
		    <s:label cssClass="MandatoryField" value="%{getText('label.740.AvailableWithIdentifier')}"></s:label> <span class="MandatoryField">[Mandatory]</span>
		  </td>
		</tr>
		<tr>
		  <td width="100%" class="text">
		    <s:select list="{'A','D'}" name="availableWithIdentifier" id="availableWithIdentifier" headerKey="" headerValue="Select PID" onchange="callAdvise();"></s:select><a></a><s:textfield name="availableWithIdentifier1" id="availableWithIdentifier1" maxLength="34" onKeyPress='return notAllowCheck(this,event);' ></s:textfield>
		  </td>		
		</tr>
        <tr>
		  <td width="100%" class="textLeft">
		    <s:label cssClass="MandatoryField" value="%{getText('label.740.authorisedBankCode')}"></s:label> <span class="MandatoryField">[Mandatory]</span>
		  </td>
		</tr>
		<tr>
		  <td width="100%" class="text">
		    <s:textfield name="authorisedBankCode" id="authorisedBankCode"  onKeyPress='return notAllowCheck(this,event);'></s:textfield><input type="button" value="Search IFSC..." onclick="return callSearchIFSC('AUTHORISEDIFSC','BRANCH')" id ="btnAuth" class="btn" />
		  </td>
        </tr>
        <tr>		
		  <td width="100%" class="textLeft">
		    <s:label cssClass="MandatoryField" value="%{getText('label.740.authorisationMode')}"></s:label> <span class="MandatoryField">[Mandatory]</span>
		  </td>
		</tr>
		<tr>
		  <td width="100%" class="text">
		    <s:select list="{'BY PAYMENT','BY ACCEPTANCE','BY NEGOTIATION','BY DEF PAYMENT','BY MIXED PYMT'}" name="authorisationMode" id="authorisationMode" headerKey="" headerValue="Select Mode" ></s:select>
		  </td>
		</tr>	
		<tr>
		  <td width="100%" class="textLeft">
		    <s:label cssClass="MandatoryField" value="%{getText('label.740.authorisedAndAddress')}"></s:label> <span class="MandatoryField">[Mandatory]</span>
		  </td>
		</tr>
		<tr>
		  <td width="100%" class="text">
		    <s:textarea name="authorisedAndAddress" id="authorisedAndAddress" rows="4" cols="35" wrap="HARD"  onKeyPress='return maxLength(this,"139","authorisedAndAddress","35",event);' ></s:textarea>
		  </td>
        </tr>		  
		<tr>
          <td width="100%" class="textLeft">
            <s:label value="%{getText('label.740.DraftsAt')}"></s:label> <span class="optional">[Optional]</span>
          </td>
        </tr>
		<tr>
		  <td width="100%" class="text">
		     <s:textarea name="draftsAt" id="draftsAt" rows="3" cols="35" wrap="HARD" onKeyPress='return maxLength(this,"104");' ></s:textarea> <b>[When used, fields 42C and 42a must both be present]</b>
		  </td>
		</tr>
		<tr>
		  <td width="100%" class="textLeft">
			 <s:label value="%{getText('label.740.DraweeBankID')}"></s:label> <span class="optional">[Optional]</span>
		  </td>
		</tr>
		<tr>
		  <td width="100%" class="text">
		    <s:select list="{'A','D'}" name="draweeBankpartyidentifier" id="draweeBankpartyidentifier" headerKey="" headerValue="PID" onchange="callDraweeBank();"></s:select><a></a><s:textfield name="draweeBankAccount" id="draweeBankAccount" maxLength="34" onKeyPress='return notAllowCheck(this,event);' ></s:textfield> 
		  </td>
		</tr>		
		<tr>	
		  <td width="100%" class="textLeft">
		    <s:label value="%{getText('label.740.DraweeBankCode')}"></s:label> <span class="optional">[Optional]</span>
		  </td>
		</tr>
		<tr>
		   <td width="100%" class="text">
		     <s:textfield name="draweeBankCode" id="draweeBankCode" maxLength="11"  onKeyPress='return notAllowCheck(this,event);'></s:textfield><input type="button" value="Search IFSC..." onclick="return callSearchIFSC('DRAWEEIFSC','BOTH')" id ="draweeSearch" class="btn" /> <b>[Either fields 42C and 42a together, or field 42M alone, or field 42P alone may be present. No othercombination of these fields is allowed]</b>
		   </td>				
		</tr>
		<tr>
		  <td width="100%" class="textLeft">
		    <s:label value="%{getText('label.740.DraweeBankNameAddress')}"></s:label> <span class="optional">[Optional]</span>
		  </td>
		</tr>
		<tr>
		  <td width="100%" class="text">
		     <s:textarea name="draweeBankNameAddress" id="draweeBankNameAddress" rows="4" cols="35" wrap="HARD"  onKeyPress='return maxLength(this,"139","draweeBankNameAddress","35",event);' ></s:textarea>
		  </td>
		</tr>
		<tr>
		  <td width="100%" class="textLeft">
		     <s:label value="%{getText('label.740.MixedPaymentDetails')}"></s:label> <span class="optional">[Optional]</span>
		  </td>
		</tr>
		<tr>
		  <td width="100%" class="text">
		    <s:textarea name="mixedPaymentDetails" id="mixedPaymentDetails" rows="4" cols="35" wrap="HARD" onKeyPress='return maxLength(this,"139","mixedPaymentDetails","35",event);'  ></s:textarea>
		   </td>
	    </tr>
		<tr>
		  <td width="100%" class="textLeft">
		    <s:label value="%{getText('label.740.DeferredPaymentDetails')}"></s:label> <span class="optional">[Optional]</span>
		  </td>
		</tr>
		<tr>
		  <td width="100%" class="text">
		    <s:textarea name="deferredPaymentDetails" id="deferredPaymentDetails" rows="4" cols="35" wrap="HARD" onKeyPress='return maxLength(this,"139","deferredPaymentDetails","35",event);' ></s:textarea>
		  </td>
		</tr>
		<tr>
		  <td width="100%" class="textLeft">
		    <s:label value="%{getText('label.740.ReimbursingBanksCharges')}"></s:label> <span class="optional">[Optional]</span>
		  </td>
		</tr>				
		<tr>
		  <td width="100%" class="text">
		    <s:select list="{'CLM','OUR'}" name="reimbursingBanksCharges" id="reimbursingBanksCharges" headerKey="" headerValue="Select Reimbursing Bank charge" ></s:select>
		  </td>
	    </tr>
		<tr>
		  <td width="100%" class="textLeft">
		    <s:label value="%{getText('label.740.OtherCharges')}"></s:label> <span class="optional">[Optional]</span>
		  </td>
		</tr>
		<tr>
		  <td width="100%" class="text">
		    <s:textarea name="otherCharges" id="otherCharges" rows="6" cols="35" wrap="HARD" onKeyPress='return maxLength(this,"210","otherCharges","35",event);'></s:textarea>
		  </td>
		</tr>
		<tr>
		  <td width="100%" class="textLeft">
		    <s:label value="%{getText('label.740.SendertoReceiverInformation')}"></s:label> <span class="optional">[Optional]</span>
		  </td>
		</tr>
		<tr>
		   <td width="100%" class="text">
		     <s:textarea name="sendertoReceiverInformation" id="sendertoReceiverInformation" rows="6" cols="35" wrap="HARD" onKeyPress='return maxLength(this,"209","sendertoReceiverInformation","35",event);'></s:textarea>
		   </td>
		</tr>
			
	</s:if>
	<s:if test="%{repair==''}">
		<tr>
		  <td width="100%" class="textLeft">
		     <s:label cssClass="MandatoryField" value="%{getText('label.740.lc_Number')}"></s:label> <span class="MandatoryField">[Mandatory]</span>
		  </td>
		</tr>
		<tr>
	      <td width="100%" class="text">
	        <s:textfield name="lcNumber" id="lcNumber" maxLength="16" onKeyPress='return notAllowCheck(this,event);' ></s:textfield>
		  </td>
	    </tr>
		<tr>
		  <td width="100%" class="textLeft">
		    <s:label value="%{getText('label.740.AccountID')}"></s:label> <span class="optional">[Optional]</span>
		  </td>
		</tr>
		<tr>
		  <td width="100%" class="text">
		    <s:textfield name="acountID" id="acountID" maxLength="35" onKeyPress='return notAllowCheck(this,event);' ></s:textfield>
		  </td>
		</tr>
		<tr>
		  <td width="100%" class="textLeft">
		    <s:label cssClass="MandatoryField" value="%{getText('label.740.applicableRules')}"></s:label> <span class="MandatoryField">[Mandatory]</span>
		  </td>
		</tr>				
		<tr>
		  <td width="100%" class="text">
		    <s:select list="{'NOTURR','URR LATEST VERSION'}" name="applicableRule" id="applicableRule" maxLength="30" headerKey="" headerValue="Select Applicable Rule" ></s:select>
		  </td>
		</tr>
		<tr>
		  <td width="100%" class="textLeft">
		    <s:label value="%{getText('label.740.ExpiryDate')}"></s:label> <span class="optional">[Optional]</span>
		  </td>
		</tr>
		<tr>
		  <td width="100%" class="text">
		    <sx:datetimepicker name="lcExpiryDate" cssClass="txtField" id="lcExpiryDate"  displayFormat="MM/dd/yyyy" type="date" />
		  </td>
		</tr>
		
		<tr>	
		  <td width="100%" class="textLeft">
		    <s:label value="%{getText('label.740.expiryPlace')}"></s:label> <span class="optional">[Optional]</span>
		  </td>
		</tr>
		<tr>
		  <td width="100%" class="text">
		    <s:textfield name="lcExpirePlace" id="lcExpirePlace" maxLength="29" onKeyPress='return notAllowCheck(this,event);' ></s:textfield>
		  </td>
		</tr>
		<tr>
		  <td width="100%" class="textLeft">
		    <s:label value="%{getText('label.740.NegotiatingBankPartyIdentifier')}"></s:label> <span class="optional">[Optional]</span>
		  </td>
		</tr>
		<tr>
		  <td width="100%" class="text">
		    <s:select list="{'A','D'}" name="negotiatingBankPartyIdentifier" id="negotiatingBankPartyIdentifier" headerKey="" headerValue="Select PID" onchange="callNegotiation();"></s:select><a></a><s:textfield name="negotiatingBankAccount" id="negotiatingBankAccount" maxLength="34" onKeyPress='return notAllowCheck(this,event);' ></s:textfield>  <b>[Either field 58a or 59, but not both, may be present]</b>
		  </td>
		</tr>
		<tr>	
		  <td width="100%" class="textLeft">
		    <s:label value="%{getText('label.740.NegotiatingBankCode')}"></s:label> <span class="optional">[Optional]</span>
		  </td>
		</tr>
		<tr>
		  <td width="100%" class="text">
		    <s:textfield name="negotiatingBankCode" id="negotiatingBankCode" maxLength="11" onKeyPress='return notAllowCheck(this,event);'></s:textfield>
		  			<input type="button" value="Search IFSC..."	onclick="callSearchIFSC('NEGOTIATIONIFSC','PARTY')" id ="negotiationSearch" class="btn" />
		  </td>
		</tr>
		<tr>
		  <td width="100%" class="textLeft">
		    <s:label value="%{getText('label.740.NegotiatingBankNameAndAddress')}"></s:label> <span class="optional">[Optional]</span>
		  </td>
		</tr>
		<tr>
		  <td width="100%" class="text">
		    <s:textarea name="negotiatingBankNameAndAddress" id="negotiatingBankNameAndAddress" rows="4" cols="35" wrap="HARD" onKeyPress='return maxLength(this,"139","negotiatingBankNameAndAddress","35",event);'></s:textarea>
		  </td>
		</tr>
		
		<tr>
		  <td width="100%" class="textLeft">
		    <s:label value="%{getText('label.740.beneficiaryNameAddress')}"></s:label> <span class="optional">[Optional]</span>
		  </td>
		</tr>
		<tr>
		  <td width="100%" class="text">
		    <s:textarea name="beneficiaryNameAddress" id="beneficiaryNameAddress" rows="4" cols="35" wrap="HARD" onKeyPress='return maxLength(this,"139","beneficiaryNameAddress","35",event);'></s:textarea>
		  </td>
		</tr>
		<tr>
	      <td width="100%" class="textLeft">
		    <s:label cssClass="MandatoryField" value="%{getText('label.740.creditCurrency')}"></s:label> <span class="MandatoryField">[Mandatory]</span>
		  </td>
		</tr>
		<tr>
		  <td width="100%" class="text">
		   <s:select list="currCodeDropDown" name="lcCurrency" id="lcCurrency"  headerKey="" headerValue="Select Currency Code" cssClass="txtField" ></s:select> 
		  </td>
        </tr>		  
		<tr>
		  <td width="100%" class="textLeft">
		    <s:label cssClass="MandatoryField" value="%{getText('label.740.CreditAmount')}"></s:label> <span class="MandatoryField">[Mandatory]</span>
		  </td>
		</tr>
		<tr>
		  <td width="100%" class="text">
		   <s:textfield name="creditAmount" id="creditAmount" maxLength="15" onChange='callAmountDecimal(this);' onKeyPress='return notAllowCheck(this,event);'></s:textfield>
		  </td>
		</tr>
		<tr>
		  <td width="100%" class="textLeft">
		    <s:label value="%{getText('label.740.positiveTolerance')}"></s:label> <span class="optional">[Optional]</span>
		  </td>
		</tr>
		<tr>
		  <td width="100%" class="text">
		    <s:textfield name="positiveTolerance" id="positiveTolerance" maxLength="2" onchange="callTolerance()" ></s:textfield>  <b>[Either field 39A or 39B, but not both, may be present]</b>
		  </td>
		</tr>
		<tr>
		  <td width="100%" class="textLeft">
		    <s:label	value="%{getText('label.740.negativeTolerance')}"></s:label> <span class="optional">[Optional]</span>
		  </td>
		</tr>
		<tr>
		  <td width="100%" class="text">
		    <s:textfield name="negativeTolerance" id="negativeTolerance" maxLength="2" onchange="callTolerance()" ></s:textfield>
		  </td>
		</tr>
		
		<tr>
		  <td width="100%" class="textLeft">
		    <s:label value="%{getText('label.740.maximumCreditAmount')}"></s:label> <span class="optional">[Optional]</span>
		  </td>
		</tr>
		<tr>
		  <td width="100%" class="text">
		    <s:select name="maximumCreditAmount" id="maximumCreditAmount" maxLength="13" headerKey=""	headerValue="Select Maximum Credit Amount" list="{'NOT EXCEEDING'}"	onchange="callTolerance()"></s:select>
		  </td>
		</tr>
		<tr>
		  <td width="100%" class="textLeft">
		    <s:label	value="%{getText('label.740.AdditionalAmountsCovered')}"></s:label> <span class="optional">[Optional]</span>
		  </td>
		</tr>
		<tr>
		  <td width="100%" class="text">
		    <s:textarea name="additionalAmountsCovered" id="additionalAmountsCovered" rows="4" cols="35" wrap="HARD" onKeyPress='return maxLength(this,"139","additionalAmountsCovered","35",event);' ></s:textarea>
		  </td>
		</tr>
		<tr>
		  <td width="100%" class="textLeft">
		    <s:label cssClass="MandatoryField" value="%{getText('label.740.AvailableWithIdentifier')}"></s:label> <span class="MandatoryField">[Mandatory]</span>
		  </td>
		</tr>
		<tr>
		  <td width="100%" class="text">
		    <s:select list="{'A','D'}" name="availableWithIdentifier" id="availableWithIdentifier" headerKey="" headerValue="Select PID" onchange="callAdvise();"></s:select><a></a><s:textfield name="availableWithIdentifier1" id="availableWithIdentifier1" maxLength="34" onKeyPress='return notAllowCheck(this,event);' ></s:textfield>
		  </td>		
		</tr>
        <tr>
		  <td width="100%" class="textLeft">
		    <s:label cssClass="MandatoryField" value="%{getText('label.740.authorisedBankCode')}"></s:label> <span class="MandatoryField">[Mandatory]</span>
		  </td>
		</tr>
		<tr>
		  <td width="100%" class="text">
		    <s:textfield name="authorisedBankCode" id="authorisedBankCode"  onKeyPress='return notAllowCheck(this,event);'></s:textfield><input type="button" value="Search IFSC..." onclick="return callSearchIFSC('AUTHORISEDIFSC','BRANCH')" id ="btnAuth" class="btn" />
		  </td>
        </tr>
        <tr>		
		  <td width="100%" class="textLeft">
		    <s:label cssClass="MandatoryField" value="%{getText('label.740.authorisationMode')}"></s:label> <span class="MandatoryField">[Mandatory]</span>
		  </td>
		</tr>
		<tr>
		  <td width="100%" class="text">
		    <s:select list="{'BY PAYMENT','BY ACCEPTANCE','BY NEGOTIATION','BY DEF PAYMENT','BY MIXED PYMT'}" name="authorisationMode" id="authorisationMode" headerKey="" headerValue="Select Mode" ></s:select>
		  </td>
		</tr>	
		<tr>
		  <td width="100%" class="textLeft">
		    <s:label cssClass="MandatoryField" value="%{getText('label.740.authorisedAndAddress')}"></s:label> <span class="MandatoryField">[Mandatory]</span>
		  </td>
		</tr>
		<tr>
		  <td width="100%" class="text">
		    <s:textarea name="authorisedAndAddress" id="authorisedAndAddress" rows="4" cols="35" wrap="HARD"  onKeyPress='return maxLength(this,"139","authorisedAndAddress","35",event);' ></s:textarea>
		  </td>
        </tr>		  
		<tr>
          <td width="100%" class="textLeft">
            <s:label value="%{getText('label.740.DraftsAt')}"></s:label> <span class="optional">[Optional]</span>
          </td>
        </tr>
		<tr>
		  <td width="100%" class="text">
		     <s:textarea name="draftsAt" id="draftsAt" rows="3" cols="35" wrap="HARD" onKeyPress='return maxLength(this,"104");' ></s:textarea> <b>[When used, fields 42C and 42a must both be present]</b>
		  </td>
		</tr>
		<tr>
		  <td width="100%" class="textLeft">
			 <s:label value="%{getText('label.740.DraweeBankID')}"></s:label> <span class="optional">[Optional]</span>
		  </td>
		</tr>
		<tr>
		  <td width="100%" class="text">
		    <s:select list="{'A','D'}" name="draweeBankpartyidentifier" id="draweeBankpartyidentifier" headerKey="" headerValue="PID" onchange="callDraweeBank();"></s:select><a></a><s:textfield name="draweeBankAccount" id="draweeBankAccount" maxLength="34" onKeyPress='return notAllowCheck(this,event);' ></s:textfield> 
		  </td>
		</tr>		
		<tr>	
		  <td width="100%" class="textLeft">
		    <s:label value="%{getText('label.740.DraweeBankCode')}"></s:label> <span class="optional">[Optional]</span>
		  </td>
		</tr>
		<tr>
		   <td width="100%" class="text">
		     <s:textfield name="draweeBankCode" id="draweeBankCode" maxLength="11"  onKeyPress='return notAllowCheck(this,event);'></s:textfield><input type="button" value="Search IFSC..." onclick="return callSearchIFSC('DRAWEEIFSC','BOTH')" id ="draweeSearch" class="btn" /> <b>[Either fields 42C and 42a together, or field 42M alone, or field 42P alone may be present. No othercombination of these fields is allowed]</b>
		   </td>				
		</tr>
		<tr>
		  <td width="100%" class="textLeft">
		    <s:label value="%{getText('label.740.DraweeBankNameAddress')}"></s:label> <span class="optional">[Optional]</span>
		  </td>
		</tr>
		<tr>
		  <td width="100%" class="text">
		     <s:textarea name="draweeBankNameAddress" id="draweeBankNameAddress" rows="4" cols="35" wrap="HARD"  onKeyPress='return maxLength(this,"139","draweeBankNameAddress","35",event);' ></s:textarea>
		  </td>
		</tr>
		<tr>
		  <td width="100%" class="textLeft">
		     <s:label value="%{getText('label.740.MixedPaymentDetails')}"></s:label> <span class="optional">[Optional]</span>
		  </td>
		</tr>
		<tr>
		  <td width="100%" class="text">
		    <s:textarea name="mixedPaymentDetails" id="mixedPaymentDetails" rows="4" cols="35" wrap="HARD" onKeyPress='return maxLength(this,"139","mixedPaymentDetails","35",event);'  ></s:textarea>
		   </td>
	    </tr>
		<tr>
		  <td width="100%" class="textLeft">
		    <s:label value="%{getText('label.740.DeferredPaymentDetails')}"></s:label> <span class="optional">[Optional]</span>
		  </td>
		</tr>
		<tr>
		  <td width="100%" class="text">
		    <s:textarea name="deferredPaymentDetails" id="deferredPaymentDetails" rows="4" cols="35" wrap="HARD" onKeyPress='return maxLength(this,"139","deferredPaymentDetails","35",event);' ></s:textarea>
		  </td>
		</tr>
		<tr>
		  <td width="100%" class="textLeft">
		    <s:label value="%{getText('label.740.ReimbursingBanksCharges')}"></s:label> <span class="optional">[Optional]</span>
		  </td>
		</tr>				
		<tr>
		  <td width="100%" class="text">
		    <s:select list="{'CLM','OUR'}" name="reimbursingBanksCharges" id="reimbursingBanksCharges" headerKey="" headerValue="Select Reimbursing Bank charge" ></s:select>
		  </td>
	    </tr>
		<tr>
		  <td width="100%" class="textLeft">
		    <s:label value="%{getText('label.740.OtherCharges')}"></s:label> <span class="optional">[Optional]</span>
		  </td>
		</tr>
		<tr>
		  <td width="100%" class="text">
		    <s:textarea name="otherCharges" id="otherCharges" rows="6" cols="35" wrap="HARD" onKeyPress='return maxLength(this,"210","otherCharges","35",event);'></s:textarea>
		  </td>
		</tr>
		<tr>
		  <td width="100%" class="textLeft">
		    <s:label	value="%{getText('label.740.SendertoReceiverInformation')}"></s:label> <span class="optional">[Optional]</span>
		  </td>
		</tr>
		<tr>
		   <td width="100%" class="text">
		     <s:textarea name="sendertoReceiverInformation" id="sendertoReceiverInformation" rows="6" cols="35" wrap="HARD" onKeyPress='return maxLength(this,"209","sendertoReceiverInformation","35",event);'></s:textarea>
		   </td>
		</tr>
	</s:if>
</table>
	</s:div><!-- Group Two Div End --> 
	</div>
	</div>
	
	<!--  button panel starts -->
	<div class="clearfix"></div>
	<table width="100%" cellpadding="0" cellspacing="0" border="0">
		<tr>
			<td>
		  	<s:if test="%{checkForSubmit!='Display_Approve_Reject'}">  
					<input type="button" onclick="callCommentPOPUp()" value="Submit" class="btn" />
					<input type="reset" value="Reset" onclick="cancel()" class="btn"  />
					<input type="button" value="Save Template" onclick="SaveTemplate()" class="btn"  />
					<!--<input type="button" value="Print" onclick="window.print()">
			--></s:if> 
			<s:if test="%{checkForSubmit=='Display_Approve_Reject'}">
				<s:if test="%{validUserToApprove}">
					<input type="button" id="Approve" value="Approve" onclick="callSubmit('Approve')">
					<input type="button" id="Reject" value="Reject" onclick="callSubmit('Reject')">
					<input type="button" value="Cancel" onclick="cancel()">
					<input type="button" id="printpreview" value="Print Preview" onclick="callPrintPreview()">
				</s:if>
				<s:if test="%{!validUserToApprove}">
					<input type="button" onclick="callCommentPOPUp()" value="Submit" class="btn" />
					<input type="reset" value="Cancel" onclick="cancel()" class="btn"  />
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