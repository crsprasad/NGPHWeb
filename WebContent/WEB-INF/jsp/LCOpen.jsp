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
<script language="javascript" type="text/javascript">
function limitText(limitField, limitCount, limitNum) {
	if (limitField.value.length > limitNum) {
		limitField.value = limitField.value.substring(0, limitNum);
	} else {
		limitCount.value = limitNum - limitField.value.length;
	}
}




function callDelete(row)
{
	document.getElementById("rowTodelete").value=row;
	document.forms[0].action = "rowToDeletelcOpen";
	document.forms[0].submit();

}

function callApplicableNarrative()
{
	var applicableRule = document.getElementById("applicableRule").value;
	
	var applicableNarrative = document.getElementById("applicableNarrative").value;
	if (applicableRule == "OTHR" ) {

		document.getElementById("applicableNarrative").disabled = false;
	}
	else
		{
		document.getElementById("applicableNarrative").disabled = true;
		document.getElementById("applicableNarrative").value = "";
		}

}
function callAuthorize()
{
	document.forms[0].action = "markAsAcknowledge";
	document.forms[0].submit();
}
function callSubmit(saveAction)
{
	var aa = document.getElementById('form');
	for ( var i = 0; i < aa.length; i++) {
		aa.elements[i].disabled = false;
	}
	document.getElementById("saveAction").value = saveAction;
	document.forms[0].action="saveLcCreditLcOpen";
	document.forms[0].submit();
}
function callAddTOGrid()
{
	document.forms[0].action = "addRowToLcGridlcOpen";
	document.forms[0].submit();
}


	function callFetchLCDetails()
	{
		var lcNumber= document.getElementById("lcNumber").value;
	
		if(lcNumber==""){
			window.open(
					"<s:url action='lcOpenSearchLCNo' windowState='EXCLUSIVE' />",
					'mywindow', 'top=50,left=250,width=750,height=410');
		}
		else
			{
			
			callShowGrid();
			}
			
		}

	function callShowGrid()
	{
		
		
		document.forms[0].action="displayLCOpenData";
		document.forms[0].submit();
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
		document.forms[0].action="getLetterOFCreditApprovalLcOpen";
		document.forms[0].submit();
	}

	function callTolerance() {

		var positiveTolerence = document.getElementById("positiveTolerance").value;
		var negativeTolerance = document.getElementById("negativeTolerance").value;
		var maxCredit = document.getElementById("maximumCreditAmount").value;
	
		if (positiveTolerence != "") {
			document.getElementById("maximumCreditAmount").disabled = true;
		}else if(negativeTolerance!="")
		{
			document.getElementById("maximumCreditAmount").disabled = true;
		}
		 else if (maxCredit != "")
		{
			document.getElementById("positiveTolerance").disabled = true;
			document.getElementById("negativeTolerance").disabled = true;
		} else 
		{
			document.getElementById("maximumCreditAmount").disabled = false;
			document.getElementById("positiveTolerance").disabled = false;
			document.getElementById("negativeTolerance").disabled = false;
		}
	}
	

	

	

	

	function callShipping() {
		var shipmentPeriod = document.getElementById("shipmentPeriod").value;

		var latestDateofShipment = document
				.getElementById("latestDateofShipment").value;
		if (shipmentPeriod != ""  ) { 
			document.getElementById("latestDateofShipment").disabled = true;
			document.getElementById("shipmentPeriod").disabled = false;
		} else if (shipmentPeriod == "" ) {
			document.getElementById("latestDateofShipment").disabled = false;
			document.getElementById("shipmentPeriod").disabled = true;
		}
		else
			{
			document.getElementById("latestDateofShipment").disabled = false;
			document.getElementById("shipmentPeriod").disabled = false;
			}
	}

	

	

	function SaveTemplate()
	{
		var isConfirm = confirm("Need to save as Template !");
		if(isConfirm)
			{
				var tempName = prompt("Please enter your Template name");
				if(tempName!=null)
					{
						alert("Template Name is "+tempName);
						document.getElementById("tempName").value = tempName;
						document.forms[0].action="saveLCOpenTemplate";
						document.forms[0].submit();
					}
			} 
	}	

	function callPrintPreview(){
		document.forms[0].action="printPreviewLCOpenPage";
		document.forms[0].submit();
	}	

	function cancel(){
		var isConfirm = confirm("Please confirm");
		if(isConfirm)
			{
				document.forms[0].action="resetLCOpen";
				document.forms[0].submit();
			}
		
	}	


	function callApplicentBank()
	{
		var applicentIdentifier = document.getElementById("applicentIdentifier").value;
			if(applicentIdentifier =='A')
				{
					document.getElementById("applicantBankCode").disabled=false;
					document.getElementById("applicentBankNameandAddr").disabled=true;
				}
			else if(applicentIdentifier =='D')
			{
				document.getElementById("applicantBankCode").disabled=true;
				document.getElementById("applicentBankNameandAddr").disabled=false;
			}
			else
			{
				document.getElementById("applicantBankCode").disabled=false;
				document.getElementById("applicentBankNameandAddr").disabled=false;
			}
		
		}

	function callAvailableBank()
	{
		var availableIdentifier = document.getElementById("availableIdentifier").value;
			if(availableIdentifier =='A')
				{
					document.getElementById("authorisedBankCode").disabled=false;
					document.getElementById("authorisationMode").disabled=false;
					document.getElementById("authorisedAndAddress").disabled=true;
				}
			else if(availableIdentifier =='D')
			{
				document.getElementById("authorisedBankCode").disabled=true;
				document.getElementById("authorisationMode").disabled=false;
				document.getElementById("authorisedAndAddress").disabled=false;
			}
			else
			{
				document.getElementById("authorisedBankCode").disabled=false;
				document.getElementById("authorisationMode").disabled=false;
				document.getElementById("authorisedAndAddress").disabled=true;
			}
		
		}

	function callDrawee()
	{
		var draweeIdentifier = document.getElementById("draweeIdentifier").value;
			if(draweeIdentifier =='A')
				{
					document.getElementById("draweeBankCode").disabled=false;
					document.getElementById("draweeBankNameAddress").disabled=true;
				}
			else if(draweeIdentifier =='D')
			{
				document.getElementById("draweeBankCode").disabled=true;
				document.getElementById("draweeBankNameAddress").disabled=false;
			}
			else
			{
				document.getElementById("draweeBankCode").disabled=false;
				document.getElementById("draweeBankNameAddress").disabled=true;
			}
		
		}
	
	function callReimbursing()
	{
		var reimbursingIdentifier = document.getElementById("reimbursingIdentifier").value;
		if(reimbursingIdentifier =='A')
			{
				document.getElementById("reimbursingBankCode").disabled=false;
				document.getElementById("reimbursingBankNameAddress").disabled=true;
			}
		else if(reimbursingIdentifier =='D')
		{
			document.getElementById("reimbursingBankCode").disabled=true;
			document.getElementById("reimbursingBankNameAddress").disabled=false;
		}
		else
		{
			document.getElementById("reimbursingBankCode").disabled=false;
			document.getElementById("reimbursingBankNameAddress").disabled=true;
		}
	}


	function callAdvisingThroughBank()
	{
		var advisingIdentifier = document.getElementById("advisingIdentifier").value;
		if(reimbursingIdentifier =='A')
			{
				document.getElementById("adviseThroughBankCode").disabled=false;
				document.getElementById("adviseThroughBankLocation").disabled=true;
				document.getElementById("adviseThroughBankName").disabled=true;
			}
		else if(advisingIdentifier =='B')
		{
			document.getElementById("adviseThroughBankCode").disabled=true;
			document.getElementById("adviseThroughBankLocation").disabled=false;
			document.getElementById("adviseThroughBankName").disabled=true;
		}
		else if(advisingIdentifier =='D')
		{
			document.getElementById("adviseThroughBankCode").disabled=true;
			document.getElementById("adviseThroughBankLocation").disabled=true;
			document.getElementById("adviseThroughBankName").disabled=false;
		}
		else
		{
			document.getElementById("adviseThroughBankCode").disabled=false;
			document.getElementById("adviseThroughBankLocation").disabled=false;
			document.getElementById("adviseThroughBankName").disabled=false;
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
	href="#">Outbound Payments</a>&nbsp;>&nbsp;LC Open</span></div>
<div id="content">
<h1>LC Open (MT-700)</h1>
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
<s:hidden id="senderBank" name="senderBank"></s:hidden>
<s:hidden name="shipmentTerms" id="shipmentTerms"></s:hidden>
<s:hidden name="tempName" id="tempName"></s:hidden>
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
	<s:div id="main" cssClass="dataGrid" style="width:100%;">
	<table width="100%" cellpadding="3" cellspacing="1" border="0"	bgcolor="#ffffff">
	<s:if test="%{repair!=''}">
	    <tr>
		  <td width="20%" class="textLeft">
		     <s:label value="%{getText('label.MB.IssuingBranchIFSC')}"></s:label><span class="optional">[Optional]</span>
		  </td>
		  <td width="30%" class="text">
		     <s:textfield name="issuingBankCode" id="issuingBankCode"></s:textfield><input type="button" value="Search IFSC..." onclick="return callSearchIFSC('ISSUEBANKTHOUGHIFSC','SENDERBANKIFSC')" id="btnadviceThrough"class="btn" />
		  </td>
		  <td width="20%" class="textLeft">
		     <s:label cssClass="MandatoryField" value="%{getText('label.MB.Benefifsc')}"></s:label><span class="MandatoryField">[Mandatory]</span>
		  </td>
		  <td width="30%" class="text">
		     <s:textfield name="advisingBank" id="advisingBank"></s:textfield><input type="button" value="Search IFSC..." onclick="return callSearchIFSC('ADVISINGIFSC','BOTH')" id="btnadviceThrough"class="btn" />
		  </td>
		</tr>
    </s:if>	
    
    <s:if test="%{repair==''}">
       <tr>
		  <td width="20%" class="textLeft">
		     <s:label value="%{getText('label.MB.IssuingBranchIFSC')}"></s:label><span class="optional">[Optional]</span>
		  </td>
		  <td width="30%" class="text">
		     <s:textfield name="issuingBankCode" id="issuingBankCode"></s:textfield><input type="button" value="Search IFSC..." onclick="return callSearchIFSC('ISSUEBANKTHOUGHIFSC','SENDERBANKIFSC')" id="btnadviceThrough"class="btn" />
		  </td>
		  <td width="20%" class="textLeft">
		     <s:label cssClass="MandatoryField" value="%{getText('label.MB.Benefifsc')}"></s:label><span class="MandatoryField">[Mandatory]</span>
		  </td>
		  <td width="30%" class="text">
		     <s:textfield name="advisingBank" id="advisingBank"></s:textfield><input type="button" value="Search IFSC..." onclick="return callSearchIFSC('ADVISINGIFSC','BOTH')" id="btnadviceThrough"class="btn" />
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
	<div><!-- Group two Div Starts --> 
	<s:div id="main"	cssClass="dataGrid" style="width:100%;">
	<table width="100%" cellpadding="3" cellspacing="1" border="0"	bgcolor="#ffffff">
	
<s:if test="%{repair=='REPAIR'}">
	<tr>
			<td width="100%" class="textLeft"><s:label	cssClass="MandatoryField" value="%{getText('label.700XXX.seqofTotal')}"></s:label> <span class="MandatoryField">[Mandatory]</span></td>
	</tr>
	<tr>
			<td width="100%" class="text"><s:textfield name="sequence" id="sequence" value="1" readonly="true"></s:textfield>  / <s:textfield name="sequenceofTotal" id="sequenceofTotal" value="1" readonly="true"></s:textfield></td>
	</tr> 
	<tr>
			<td width="100%" class="textLeft"><s:label	cssClass="MandatoryField" value="%{getText('label.700XXX.formofDoc')}"></s:label><span class="MandatoryField">[Mandatory]</span></td>
	</tr>	
	<tr>	
			<td width="100%" class="text"><s:select list="{'IRREVOCABLE','REVOCABLE', 'IRREVOCABLE TRANSFERABLE', 'REVOCABLE TRANSFERABLE', 'IRREVOCABLE STANDBY', 'REVOCABLE STANDBY', 'IRREVOC TRANS STANDBY'}" name="lcType" id="lcType" headerKey="" headerValue="Select Lc Type" ></s:select></td>
	</tr>
	<tr>
			<td width="100%" class="textLeft"><s:label cssClass="MandatoryField" value="%{getText('label.700XXX.lcNumber')}"></s:label><span class="MandatoryField">[Mandatory]</span></td>
	</tr>
	<tr>		
			<td width="100%" class="text"><s:textfield name="lcNumber" id="lcNumber" maxLength="16" readonly="true"></s:textfield></td>
	</tr>	
	<tr>
			<td width="100%" class="textLeft"><s:label value="%{getText('label.700XXX.referenceToPreadvice')}"></s:label><span class="optional">[Optional]</span></td>
	</tr>
	<tr>		
			<td width="100%" class="text"><s:textfield name="lcPresdvice" id="lcPresdvice" maxLength="16"></s:textfield></td>
	</tr>
	<tr>
			<td width="100%" class="textLeft"><s:label value="%{getText('label.700XXX.dateofIssue')}"></s:label><span class="optional">[Optional]</span></td>
	</tr>
	<tr>
		    <td width="100%" class="text"><sx:datetimepicker name="issueDate" value="" cssClass="txtField" id="issueDate" displayFormat="MM/dd/yyyy" type="date"  /> </td>		
	</tr>
	<tr>
			<td width="100%" class="textLeft"><s:label	cssClass="MandatoryField" value="%{getText('label.700XXX.applicableRules')}"></s:label><span class="MandatoryField">[Mandatory]</span></td>
	</tr>
	<tr>
			<td width="100%" class="text"><s:select list="{'EUCP LATEST VERSION','EUCPURR LATEST VERSION','ISP LATEST VERSION','OTHR','UCP LATEST VERSION','UCPURR LATEST VERSION'}" name="applicableRule" id="applicableRule" headerKey="" headerValue="Select Applicable Rule" onchange="callApplicableNarrative()"></s:select>  </td>
	</tr>	
	<tr>
			<td width="100%" class="textLeft"><s:label cssClass="MandatoryField" value="%{getText('label.700XXX.dateofExp')}"></s:label><span	class="MandatoryField">[Mandatory]</span></td>
	</tr>
	<tr>		
			<td width="100%" class="text"><sx:datetimepicker name="lcExpiryDate" value="" cssClass="txtField" id="lcExpiryDate"  displayFormat="MM/dd/yyyy" type="date"  /> </td>
	</tr>
	<tr>
			<td width="100%" class="textLeft"><s:label cssClass="MandatoryField" value="%{getText('label.700XXX.placeofExp')}"></s:label><span class="MandatoryField">[Mandatory]</span></td>
	</tr>
	<tr>		
			<td width="100%" class="text"><s:textfield name="lcExpirePlace" id="lcExpirePlace" maxLength="29" onKeyPress='return notAllowCheck(this,event);' ></s:textfield> </td>
	</tr>
	<tr>    
			<td width="100%" class="textLeft"><s:label value="%{getText('label.700XXX.applicentBank')}"></s:label><span class="optional">[Optional]</span></td>
	</tr>
	<tr>		
			<td width="100%" class="text"><s:select list="{'A','D'}" name="applicentIdentifier" id="applicentIdentifier" headerKey="" headerValue="Select" onChange="javascript:callApplicentBank();" ></s:select></td>
	</tr>
	<tr>    
			<td width="100%" class="textLeft"><s:label value="%{getText('label.700XXX.applicentBankCode')}"></s:label><span class="optional">[Optional]</span></td>
	</tr>
	<tr>
			<td width="100%" class="text"><s:textfield name="applicantBankCode" id="applicantBankCode" maxLength="11" onKeyPress='return notAllowCheck(this,event);'> </s:textfield><input type="button" value="Search IFSC..." onclick="return callSearchIFSC('APPLICANTIFSC','BOTH')" id="btnSearch" class="btn" /> </td>
	</tr>
	<tr>    
			<td width="100%" class="textLeft"><s:label value="%{getText('label.700XXX.applicentBankNameAddress')}"></s:label><span class="optional">[Optional]</span></td>
	</tr>	
	<tr>
			<td width="100%" class="text"><s:textarea name="applicentBankNameandAddr" id="applicentBankNameandAddr" cols="35" rows="4" wrap="HARD" onKeyPress='return maxLength(this,"139","applicentBankNameandAddr","35",event);'></s:textarea> </td>
	</tr>	
	<tr>    
			<td width="100%" class="textLeft"><s:label cssClass="MandatoryField" value="%{getText('label.700XXX.applicent')}"></s:label><span class="MandatoryField">[Mandatory]</span></td>
	</tr>	
	<tr>
			<td width="100%" class="text"><s:textarea name="applicantNameAddress" id="applicantNameAddress" rows="4" cols="35" wrap="HARD" onKeyPress='return maxLength(this,"139","applicantNameAddress","35",event);'></s:textarea> </td>
	</tr>
	<tr>    
			<td width="100%" class="textLeft"><s:label cssClass="MandatoryField" value="%{getText('label.700XXX.beneficiaryNameandAddress')}"></s:label><span class="MandatoryField">[Mandatory]</span></td>
	</tr>
	<tr>
			<td width="100%" class="text"><s:textarea name="beneficiaryNameAddress" id="beneficiaryNameAddress" rows="4" cols="35" wrap="HARD" onKeyPress='return maxLength(this,"139","beneficiaryNameAddress","35",event);'></s:textarea> </td>
	</tr>
	<tr>    
			<td width="100%" class="textLeft"><s:label cssClass="MandatoryField" value="%{getText('label.700XXX.curreyCode')}"></s:label><span class="MandatoryField">[Mandatory]</span></td>
	</tr>
	<tr>
			 <td width="100%" class="text"><s:select list="currCodeDropDown" name="lcCurrency" id="lcCurrency"  headerKey="" headerValue="Select Currency Code" cssClass="txtField"></s:select></td>
	</tr>
	<tr>    
			<td width="100%" class="textLeft"><s:label cssClass="MandatoryField" value="%{getText('label.700XXX.amount')}"></s:label><span class="MandatoryField">[Mandatory]</span></td>
	</tr>
	<tr>
			<td width="100%" class="text"><s:textfield maxLength="15" name="lcAmount" id="lcAmount" onKeyPress='return notAllowCheck(this,event);' onChange='callAmountDecimal(this);'></s:textfield> </td> 
	</tr>
	<tr>    
			<td width="100%" class="textLeft"><s:label value="%{getText('label.700XXX.positiveTolerance')}"></s:label><span class="optional">[Optional]</span></td>
	</tr>
	<tr>
			<td width="100%" class="text"><s:textfield name="positiveTolerance" id="positiveTolerance" maxLength="2" onKeyPress='return notAllowCheck(this,event);'></s:textfield><b>[Either field 39A or 39B, but not both, may be present]</b></td>
	</tr>
	<tr>    
			<td width="100%" class="textLeft"><s:label value="%{getText('label.700XXX.negativeTolerance')}"></s:label><span class="optional">[Optional]</span></td>
	</tr>
	<tr>
			<td width="100%" class="text"><s:textfield name="negativeTolerance" id="negativeTolerance" maxLength="2" onKeyPress='return notAllowCheck(this,event);'></s:textfield></td>
	</tr>
	<tr>    
			<td width="100%" class="textLeft"><s:label value="%{getText('label.700XXX.maximumCreditAmount')}"></s:label><span class="optional">[Optional]</span></td>
	</tr>
	<tr>
			<td width="100%" class="text"><s:select name="maximumCreditAmount" id="maximumCreditAmount" headerKey="" headerValue="Select Maximum Credit Amount" list="{'NOT EXCEEDING'}" onchange="callTolerance()"></s:select> </td>
	</tr>
	<tr>
			<td width="100%" class="textLeft"><s:label value="%{getText('label.700XXX.additinalAmtCoverd')}"></s:label><span class="optional">[Optional]</span></td>
	</tr>
	<tr>
			<td width="100%" class="text"><s:textarea name="additionalAmounts" id="additionalAmounts" cols="35" rows="4" wrap="HARD" onKeyPress='return maxLength(this,"139","draweeBankNameAddress","35",event);'></s:textarea> </td>
	</tr>
	<tr>
			<td width="100%" class="textLeft"><s:label cssClass="MandatoryField" value="%{getText('label.700XXX.available')}"></s:label><span class="MandatoryField">[Mandatory]</span></td>
	</tr>
	<tr>			
			<td width="100%" class="text"><s:select list="{'A','D'}" name="availableIdentifier" id="availableIdentifier" headerKey="" headerValue="Select" onChange="javascript:callAvailableBank();" ></s:select></td>
	</tr>
	<tr>
			<td width="100%" class="textLeft"><s:label cssClass="MandatoryField" value="%{getText('label.700XXX.availableBankCode')}"></s:label><span class="MandatoryField">[Mandatory]</span></td>
	</tr>
	<tr>
			<td width="100%" class="text"><s:textfield name="authorisedBankCode" id="authorisedBankCode" onkeyup="callAuthorised()" onKeyPress='return notAllowCheck(this,event);' ></s:textfield><input type="button" value="Search IFSC..." onclick="return callSearchIFSC('AUTHORISEDIFSC','BOTH')" id ="btnAuth" class="btn" /></td>
	</tr>
	<tr>
			<td width="100%" class="textLeft"><s:label cssClass="MandatoryField" value="%{getText('label.700XXX.availableCode')}"></s:label><span class="MandatoryField">[Mandatory]</span></td>
	</tr>
	<tr>
			<td width="100%" class="text"><s:select list="{'BY PAYMENT','BY ACCEPTANCE','BY NEGOTIATION','BY DEF PAYMENT','BY MIXED PYMT'}" name="authorisationMode" id="authorisationMode" headerKey="" headerValue="Select Mode" ></s:select></td>
	</tr>
	<tr>
			<td width="100%" class=textLeft><s:label cssClass="MandatoryField" value="%{getText('label.700XXX.availableBankNameandAddress')}"></s:label><span class="MandatoryField">[Mandatory]</span></td>
	</tr>
	<tr>
			<td width="100%" class="text"><s:textarea name="authorisedAndAddress" id="authorisedAndAddress" rows="4" cols="35" wrap="HARD" onkeyup="callAuthorised()" onKeyPress='return maxLength(this,"139","authorisedAndAddress","35",event);' ></s:textarea> </td>
	</tr>
	<tr>
			<td width="100%" class="textLeft"><s:label value="%{getText('label.700XXX.draftsAt')}"></s:label><span class="optional">[Optional]</span></td>
	</tr>
	<tr>
			<td width="100%" class="text"><s:textarea name="DraftsAt" id="DraftsAt" rows="3" cols="35" wrap="HARD" onKeyPress='return maxLength(this,"104","DraftsAt","35",event);'></s:textarea><b>[When used, fields 42C and 42a must both be present]</b> </td>
	</tr>
	<tr>
			<td width="100%" class="textLeft"><s:label value="%{getText('label.700XXX.Drawee')}"></s:label><span class="optional">[Optional]</span></td>
	</tr>
	<tr>
			<td width="100%" class="text"><s:select list="{'A','D'}" name="draweeIdentifier" id="draweeIdentifier" headerKey="" headerValue="Select" onChange="javascript:callDrawee();" ></s:select><b>[Either fields 42C and 42a together, or field 42M alone, or field 42P alone may be present. No other
combination of these fields is allowed]</b></td>
	</tr>
	<tr>
			<td width="100%" class="textLeft"><s:label value="%{getText('label.700XXX.draweeBankCode')}"></s:label><span class="optional">[Optional]</span></td>
	</tr>
	<tr>
			<td width="100%" class="text"><s:textfield name="draweeBankCode" id="draweeBankCode" maxLength="11" onkeyup="callDraweeAt()" onKeyPress='return notAllowCheck(this,event);'></s:textfield><input type="button" value="Search IFSC..." onclick="return callSearchIFSC('DRAWEEIFSC','BOTH')" id ="draweeSearch" class="btn" /> </td>
	</tr>
	<tr>
			<td width="100%" class="textLeft"><s:label value="%{getText('label.700XXX.dreaweeNameandAddress')}"></s:label><span class="optional">[Optional]</span></td>
	</tr>
	<tr>
			<td width="100%" class="text"><s:textarea name="draweeBankNameAddress" id="draweeBankNameAddress" cols="35" rows="4" wrap="HARD" onkeyup="callDraweeAt()" onKeyPress='return maxLength(this,"139","draweeBankNameAddress","35",event);'></s:textarea> </td>
	</tr>
	<tr>
			<td width="100%" class="textLeft"><s:label value="%{getText('label.700XXX.mixedPaymnetDetails')}"></s:label><span class="optional">[Optional]</span></td>
	</tr>
	<tr>
			<td width="100%" class="text"><s:textarea name="mixedPaymentDetails" id="mixedPaymentDetails" rows="4" cols="35" wrap="HARD" onKeyPress='return maxLength(this,"139","mixedPaymentDetails","35",event);'></s:textarea> </td>
	</tr>
	<tr>
			<td width="100%" class="textLeft"><s:label value="%{getText('label.700XXX.deferredPaymentDetails')}"></s:label><span class="optional">[Optional]</span></td>
	</tr>
	<tr>
			<td width="100%" class="text"><s:textarea name="deferredPaymentDetails" id="deferredPaymentDetails" rows="4" cols="35" wrap="HARD" onKeyPress='return maxLength(this,"139","deferredPaymentDetails","35",event);'></s:textarea> </td>
	</tr>
	<tr>
			<td width="100%" class="textLeft"><s:label value="%{getText('label.700XXX.partialPayments')}"></s:label><span class="optional">[Optional]</span></td>
	</tr>
	<tr>
			<td width="100%" class="text"><s:textarea name="partialShipments" id="partialShipments" rows="1" cols="35" onKeyPress='return notAllowCheck(this,event);' ></s:textarea> </td>
	</tr>
	<tr>
			<td width="100%" class="textLeft"><s:label value="%{getText('label.700XXX.transshipment')}"></s:label><span class="optional">[Optional]</span></td>
	</tr>
	<tr>
			<td width="100%" class="text"><s:textarea name="transhipment" id="transhipment" rows="1" cols="35" onKeyPress='return notAllowCheck(this,event);' ></s:textarea> </td>
	</tr>
	<tr>
			<td width="100%" class="textLeft"><s:label value="%{getText('label.700XXX.placeofDispatch')}"></s:label><span class="optional">[Optional]</span></td>
	</tr>
	<tr>
			<td width="100%" class="text"><s:textarea name="initialDispatchPlace" id="initialDispatchPlace" rows="1" cols="65" onKeyPress='return notAllowCheck(this,event);' ></s:textarea> </td>
	</tr>
	<tr>
			<td width="100%" class="textLeft"><s:label value="%{getText('label.700XXX.portofLoading')}"></s:label><span class="optional">[Optional]</span></td>
	</tr>
	<tr>
			<td width="100%" class="text"><s:textarea name="goodsLoadingDispatchPlace" id="goodsLoadingDispatchPlace" rows="1" cols="65" onKeyPress='return notAllowCheck(this,event);' ></s:textarea> </td>
	</tr>
	<tr>
			<td width="100%" class="textLeft"><s:label value="%{getText('label.700XXX.portofDischarge')}"></s:label><span class="optional">[Optional]</span></td>
	</tr>
	<tr>
			<td width="100%" class="text"><s:textarea name="goodsDestination" id="goodsDestination" rows="1" cols="65" onKeyPress='return notAllowCheck(this,event);'></s:textarea> </td>
	</tr>
	<tr>
			<td width="100%" class="textLeft"><s:label value="%{getText('label.700XXX.placeofFinalDestination')}"></s:label><span class="optional">[Optional]</span></td>
	</tr>
	<tr>
			<td width="100%" class="text"><s:textarea name="finalDeliveryPlace" id="finalDeliveryPlace" rows="1" cols="65" onKeyPress='return notAllowCheck(this,event);' ></s:textarea> </td>
	</tr>
	<tr>
			<td width="100%" class="textLeft"><s:label value="%{getText('label.700XXX.latestDateofShipment')}"></s:label><span class="optional">[Optional]</span></td>
	</tr>
	<tr>
			<td width="100%" class="text"><sx:datetimepicker name="latestDateofShipment" displayFormat="MM/dd/yyyy" cssClass="txtField" type="date" id="latestDateofShipment"  ></sx:datetimepicker><b>[Either field 44C or 44D, but not both, may be present]</b></td>
	</tr>
	<tr>
			<td width="100%" class="textLeft"><s:label value="%{getText('label.700XXX.shipmentPeriod')}"></s:label><span class="optional">[Optional]</span></td>
	</tr>
	<tr>
			<td width="100%" class="text"><s:textarea name="shipmentPeriod" id="shipmentPeriod" rows="6" cols="65" wrap="HARD"  onKeyPress='return maxLength(this,"389","shipmentPeriod","65",event);'></s:textarea> </td>
	</tr>
	<tr>
			<td width="100%" class="textLeft"><s:label value="%{getText('label.700XXX.descriptionofGoods')}"></s:label><span class="optional">[Optional]</span></td>
	</tr>			
	<tr>
			<td width="100%" class="text"><s:textarea name="descriptionofGoods" id="descriptionofGoods" rows="100" cols="65" wrap="HARD" style="overflow: scroll; resize: none;" onKeyPress='return maxLength(this,"6500","lcCommodity","65",event);'></s:textarea> </td>
	</tr>
	<tr>
			<td width="100%" class="textLeft"><s:label value="%{getText('label.700XXX.documentsRequired')}"></s:label><span class="optional">[Optional]</span></td>
	</tr>
	<tr>	
			<td width="100%" class="text"><s:textarea name="documentsRequired" id="documentsRequired" rows="100" cols="65" wrap="HARD" style="overflow: scroll; resize: none;" onKeyPress='return maxLength(this,"6500","documentsRequired","65",event);'></s:textarea> </td>
	</tr>
	<tr>
			<td width="100%" class="textLeft"><s:label value="%{getText('label.700XXX.additinalConditions')}"></s:label><span class="optional">[Optional]</span></td>
	</tr>
	<tr>
			<td width="100%" class="text"><s:textarea name="additionalConditions" id="additionalConditions" rows="100" cols="65" wrap="HARD" style="overflow: scroll; resize: none;" onKeyPress='return maxLength(this,"6500","additionalConditions","65",event);'></s:textarea> </td>
	</tr>
	<tr>
			<td width="100%" class="textLeft"><s:label value="%{getText('label.700XXX.charges')}"></s:label><span class="optional">[Optional]</span></td>
	</tr>
	<tr>
			<td width="100%" class="text"><s:textarea name="chargeDetails" id="chargeDetails" rows="6" cols="35" wrap="HARD" onKeyPress='return maxLength(this,"209","chargeDetails","35",event);'></s:textarea> </td>
	</tr>
	<tr>
			<td width="100%" class="textLeft"><s:label value="%{getText('label.700XXX.periodForPresentaion')}"></s:label><span class="optional">[Optional]</span></td>
	</tr>
	<tr>
			<td width="100%" class="text"><s:textarea name="periodforPresentation" id="periodforPresentation" rows="4" cols="35" wrap="HARD" onKeyPress='return maxLength(this,"139","shipmentPeriod","35",event);' ></s:textarea> </td>
	</tr>
	<tr>
			<td width="100%" class="textLeft"><s:label cssClass="MandatoryField" value="%{getText('label.700XXX.confirmation')}"></s:label><span class="MandatoryField">[Mandatory]</span></td>
	</tr>
	<tr>
			<td width="100%" class="text"><s:select list="{'CONFIRM','MAY ADD','WITHOUT'}" headerKey="" headerValue="Select Code" name="confirmationCode" id="confirmationCode" ></s:select> </td>
	</tr>
	<tr>
			<td width="100%" class="textLeft"><s:label value="%{getText('label.700XXX.reimbursing')}"></s:label><span class="optional">[Optional]</span></td>
	</tr>
	<tr>		
			<td width="100%" class="text"><s:select list="{'A','D'}" name="reimbursingIdentifier" id="reimbursingIdentifier" headerKey="" headerValue="Select" onChange="javascript:callReimbursing();" ></s:select></td>
	</tr>
	<tr>
			<td width="100%" class="textLeft"><s:label value="%{getText('label.700XXX.reimbursingBankCode')}"></s:label><span class="optional">[Optional]</span></td>
	</tr>
	<tr>
			<td width="100%" class="text"><s:textfield name="reimbursingBankCode" id="reimbursingBankCode" maxLength="11" onkeyup="callReimbusing()" onKeyPress='return notAllowCheck(this,event);'></s:textfield><input type="button" value="Search IFSC..." onclick="return callSearchIFSC('REIMBUSINGIFSC','BOTH')" id="reimbursingSearch" class="btn" />
	</tr>
	<tr>
			<td width="100%" class="textLeft"><s:label value="%{getText('label.700XXX.reimbursingNameandAddress')}"></s:label><span class="optional">[Optional]</span></td>
	</tr>
	<tr>
			<td width="100%" class="text"><s:textarea name="reimbursingBankNameAddress" id="reimbursingBankNameAddress" rows="4" cols="35" wrap="HARD" onkeyup="callReimbusing()" onKeyPress='return maxLength(this,"139","reimbursingBankNameAddress","35",event);'></s:textarea> </td>
	</tr>
	<tr>
			<td width="100%" class="textLeft"><s:label value="%{getText('label.700XXX.instructionToPaying')}"></s:label><span class="optional">[Optional]</span></td>
	</tr>
	<tr>
			<td width="100%" class="text"><s:textarea rows="12" cols="65" wrap="HARD" name="instructionstoPayingBank" id="instructionstoPayingBank" onKeyPress='return maxLength(this,"779","instructionstoPayingBank","65",event);'></s:textarea> </td>
	</tr>
	<tr>
			<td width="100%" class="textLeft"><s:label value="%{getText('label.700XXX.advisingThrough')}"></s:label><span class="optional">[Optional]</span></td>
	</tr>
	<tr>
			<td width="100%" class="text"><s:select list="{'A','B','D'}" name="advisingIdentifier" id="advisingIdentifier" headerKey="" headerValue="Select" onChange="javascript:callAdvisingThroughBank();" ></s:select></td>
	</tr>
	<tr>
			<td width="100%" class="textLeft"><s:label value="%{getText('label.700XXX.advisingThroughBankCode')}"></s:label><span class="optional">[Optional]</span></td>
	</tr>
	<tr>
			<td width="100%" class="text"><s:textfield name="adviseThroughBankCode" id="adviseThroughBankCode" maxLength="11" onKeyPress='return notAllowCheck(this,event);'> </s:textfield><input type="button" value="Search IFSC..." onclick="return callSearchIFSC('ADVICETHOUGHIFSC','BOTH')" id="btnSearch" class="btn" /> </td>
	</tr>
	<tr>
			<td width="100%" class="textLeft"><s:label value="%{getText('label.700XXX.advisingThroughBankLocation')}"></s:label><span class="optional">[Optional]</span></td>
	</tr>
	<tr>
			<td width="100%" class="text"><s:textfield name="adviseThroughBankLocation" id="adviseThroughBankLocation" maxLength="35" onkeyup="callAdvise()" onKeyPress='return notAllowCheck(this,event);' ></s:textfield> </td>
	</tr>
	<tr>
			<td width="100%" class="textLeft"><s:label value="%{getText('label.700XXX.advisingThroughBankNameAndAddress')}"></s:label><span class="optional">[Optional]</span></td>
	</tr>
	<tr>
			<td width="100%" class="text"><s:textarea name="adviseThroughBankName" id="adviseThroughBankName" rows = "4" cols="35" wrap="HARD" onkeyup="callAdvise()" onKeyPress='return maxLength(this,"139","adviseThroughBankName","35",event);'></s:textarea> </td>
	</tr>
	<tr>
			<td width="100%" class="textLeft"><s:label value="%{getText('label.700XXX.senderToReceiverInfo')}"></s:label><span class="optional">[Optional]</span></td>
	</tr>
	<tr>
			<td width="100%" class="text"><s:textarea name="SendertoReceiverInformation" id="SendertoReceiverInformation" cols="35" rows="6" wrap="HARD" onKeyPress='return maxLength(this,"209","SendertoReceiverInformation","35",event);'></s:textarea> </td>
	</tr>
	

</s:if>
<s:if test="%{repair!='REPAIR'}">
	<tr>
			<td width="100%" class="textLeft"><s:label	cssClass="MandatoryField" value="%{getText('label.700XXX.seqofTotal')}"></s:label> <span class="MandatoryField">[Mandatory]</span></td>
	</tr>
	<tr>
			<td width="100%" class="text"><s:textfield name="sequence" id="sequence" value="1" readonly="true"></s:textfield>  / <s:textfield name="sequenceofTotal" id="sequenceofTotal" value="1" readonly="true"></s:textfield></td>
	</tr> 
	<tr>
			<td width="100%" class="textLeft"><s:label	cssClass="MandatoryField" value="%{getText('label.700XXX.formofDoc')}"></s:label><span class="MandatoryField">[Mandatory]</span></td>
	</tr>	
	<tr>	
			<td width="100%" class="text"><s:select list="{'IRREVOCABLE','REVOCABLE', 'IRREVOCABLE TRANSFERABLE', 'REVOCABLE TRANSFERABLE', 'IRREVOCABLE STANDBY', 'REVOCABLE STANDBY', 'IRREVOC TRANS STANDBY'}" name="lcType" id="lcType" headerKey="" headerValue="Select Lc Type" ></s:select></td>
	</tr>
	<tr>
			<td width="100%" class="textLeft"><s:label cssClass="MandatoryField" value="%{getText('label.700XXX.lcNumber')}"></s:label><span class="MandatoryField">[Mandatory]</span></td>
	</tr>
	<tr>		
			<td width="100%" class="text"><s:textfield name="lcNumber" id="lcNumber" maxLength="16" ></s:textfield></td>
	</tr>	
	<tr>
			<td width="100%" class="textLeft"><s:label value="%{getText('label.700XXX.referenceToPreadvice')}"></s:label><span class="optional">[Optional]</span></td>
	</tr>
	<tr>		
			<td width="100%" class="text"><s:textfield name="lcPresdvice" id="lcPresdvice" maxLength="16"></s:textfield></td>
	</tr>
	<tr>
			<td width="100%" class="textLeft"><s:label value="%{getText('label.700XXX.dateofIssue')}"></s:label><span class="optional">[Optional]</span></td>
	</tr>
	<tr>
		    <td width="100%" class="text"><sx:datetimepicker name="issueDate" value="" cssClass="txtField" id="issueDate" displayFormat="MM/dd/yyyy" type="date"  /> </td>		
	</tr>
	<tr>
			<td width="100%" class="textLeft"><s:label	cssClass="MandatoryField" value="%{getText('label.700XXX.applicableRules')}"></s:label><span class="MandatoryField">[Mandatory]</span></td>
	</tr>
	<tr>
			<td width="100%" class="text"><s:select list="{'EUCP LATEST VERSION','EUCPURR LATEST VERSION','ISP LATEST VERSION','OTHR','UCP LATEST VERSION','UCPURR LATEST VERSION'}" name="applicableRule" id="applicableRule" headerKey="" headerValue="Select Applicable Rule" onchange="callApplicableNarrative()"></s:select>  </td>
	</tr>	
	<tr>
			<td width="100%" class="textLeft"><s:label cssClass="MandatoryField" value="%{getText('label.700XXX.dateofExp')}"></s:label><span	class="MandatoryField">[Mandatory]</span></td>
	</tr>
	<tr>		
			<td width="100%" class="text"><sx:datetimepicker name="lcExpiryDate" value="" cssClass="txtField" id="lcExpiryDate"  displayFormat="MM/dd/yyyy" type="date"  /> </td>
	</tr>
	<tr>
			<td width="100%" class="textLeft"><s:label cssClass="MandatoryField" value="%{getText('label.700XXX.placeofExp')}"></s:label><span class="MandatoryField">[Mandatory]</span></td>
	</tr>
	<tr>		
			<td width="100%" class="text"><s:textfield name="lcExpirePlace" id="lcExpirePlace" maxLength="29" onKeyPress='return notAllowCheck(this,event);' ></s:textfield> </td>
	</tr>
	<tr>    
			<td width="100%" class="textLeft"><s:label value="%{getText('label.700XXX.applicentBank')}"></s:label><span class="optional">[Optional]</span></td>
	</tr>
	<tr>		
			<td width="100%" class="text"><s:select list="{'A','D'}" name="applicentIdentifier" id="applicentIdentifier" headerKey="" headerValue="Select" onChange="javascript:callApplicentBank();" ></s:select></td>
	</tr>
	<tr>    
			<td width="100%" class="textLeft"><s:label value="%{getText('label.700XXX.applicentBankCode')}"></s:label><span class="optional">[Optional]</span></td>
	</tr>
	<tr>
			<td width="100%" class="text"><s:textfield name="applicantBankCode" id="applicantBankCode" maxLength="11" onKeyPress='return notAllowCheck(this,event);'> </s:textfield><input type="button" value="Search IFSC..." onclick="return callSearchIFSC('APPLICANTIFSC','BOTH')" id="btnSearch" class="btn" /> </td>
	</tr>
	<tr>    
			<td width="100%" class="textLeft"><s:label value="%{getText('label.700XXX.applicentBankNameAddress')}"></s:label><span class="optional">[Optional]</span></td>
	</tr>	
	<tr>
			<td width="100%" class="text"><s:textarea name="applicentBankNameandAddr" id="applicentBankNameandAddr" cols="35" rows="4" wrap="HARD" onKeyPress='return maxLength(this,"139","applicentBankNameandAddr","35",event);'></s:textarea> </td>
	</tr>	
	<tr>    
			<td width="100%" class="textLeft"><s:label cssClass="MandatoryField" value="%{getText('label.700XXX.applicent')}"></s:label><span class="MandatoryField">[Mandatory]</span></td>
	</tr>	
	<tr>
			<td width="100%" class="text"><s:textarea name="applicantNameAddress" id="applicantNameAddress" rows="4" cols="35" wrap="HARD" onKeyPress='return maxLength(this,"139","applicantNameAddress","35",event);'></s:textarea> </td>
	</tr>
	<tr>    
			<td width="100%" class="textLeft"><s:label cssClass="MandatoryField" value="%{getText('label.700XXX.beneficiaryNameandAddress')}"></s:label><span class="MandatoryField">[Mandatory]</span></td>
	</tr>
	<tr>
			<td width="100%" class="text"><s:textarea name="beneficiaryNameAddress" id="beneficiaryNameAddress" rows="4" cols="35" wrap="HARD" onKeyPress='return maxLength(this,"139","beneficiaryNameAddress","35",event);'></s:textarea> </td>
	</tr>
	<tr>    
			<td width="100%" class="textLeft"><s:label cssClass="MandatoryField" value="%{getText('label.700XXX.curreyCode')}"></s:label><span class="MandatoryField">[Mandatory]</span></td>
	</tr>
	<tr>
			 <td width="100%" class="text"><s:select list="currCodeDropDown" name="lcCurrency" id="lcCurrency"  headerKey="" headerValue="Select Currency Code" cssClass="txtField"></s:select></td>
	</tr>
	<tr>    
			<td width="100%" class="textLeft"><s:label cssClass="MandatoryField" value="%{getText('label.700XXX.amount')}"></s:label><span class="MandatoryField">[Mandatory]</span></td>
	</tr>
	<tr>
			<td width="100%" class="text"><s:textfield maxLength="15" name="lcAmount" id="lcAmount" onKeyPress='return notAllowCheck(this,event);'></s:textfield> </td> 
	</tr>
	<tr>    
			<td width="100%" class="textLeft"><s:label value="%{getText('label.700XXX.positiveTolerance')}"></s:label><span class="optional">[Optional]</span></td>
	</tr>
	<tr>
			<td width="100%" class="text"><s:textfield name="positiveTolerance" id="positiveTolerance" maxLength="2" onKeyPress='return notAllowCheck(this,event);'></s:textfield><b>[Either field 39A or 39B, but not both, may be present]</b></td>
	</tr>
	<tr>    
			<td width="100%" class="textLeft"><s:label value="%{getText('label.700XXX.negativeTolerance')}"></s:label><span class="optional">[Optional]</span></td>
	</tr>
	<tr>
			<td width="100%" class="text"><s:textfield name="negativeTolerance" id="negativeTolerance" maxLength="2" onKeyPress='return notAllowCheck(this,event);'></s:textfield></td>
	</tr>
	<tr>    
			<td width="100%" class="textLeft"><s:label value="%{getText('label.700XXX.maximumCreditAmount')}"></s:label><span class="optional">[Optional]</span></td>
	</tr>
	<tr>
			<td width="100%" class="text"><s:select name="maximumCreditAmount" id="maximumCreditAmount" headerKey="" headerValue="Select Maximum Credit Amount" list="{'NOT EXCEEDING'}" onchange="callTolerance()"></s:select> </td>
	</tr>
	<tr>
			<td width="100%" class="textLeft"><s:label value="%{getText('label.700XXX.additinalAmtCoverd')}"></s:label><span class="optional">[Optional]</span></td>
	</tr>
	<tr>
			<td width="100%" class="text"><s:textarea name="additionalAmounts" id="additionalAmounts" cols="35" rows="4" wrap="HARD" onKeyPress='return maxLength(this,"139","draweeBankNameAddress","35",event);'></s:textarea> </td>
	</tr>
	<tr>
			<td width="100%" class="textLeft"><s:label cssClass="MandatoryField" value="%{getText('label.700XXX.available')}"></s:label><span class="MandatoryField">[Mandatory]</span></td>
	</tr>
	<tr>			
			<td width="100%" class="text"><s:select list="{'A','D'}" name="availableIdentifier" id="availableIdentifier" headerKey="" headerValue="Select" onChange="javascript:callAvailableBank();" ></s:select></td>
	</tr>
	<tr>
			<td width="100%" class="textLeft"><s:label cssClass="MandatoryField" value="%{getText('label.700XXX.availableBankCode')}"></s:label><span class="MandatoryField">[Mandatory]</span></td>
	</tr>
	<tr>
			<td width="100%" class="text"><s:textfield name="authorisedBankCode" id="authorisedBankCode" maxLength="11" onkeyup="callAuthorised()" onKeyPress='return notAllowCheck(this,event);' ></s:textfield><input type="button" value="Search IFSC..." onclick="return callSearchIFSC('AUTHORISEDIFSC','BOTH')" id ="btnAuth" class="btn" /></td>
	</tr>
	<tr>
			<td width="100%" class="textLeft"><s:label cssClass="MandatoryField" value="%{getText('label.700XXX.availableCode')}"></s:label><span class="MandatoryField">[Mandatory]</span></td>
	</tr>
	<tr>
			<td width="100%" class="text"><s:select list="{'BY PAYMENT','BY ACCEPTANCE','BY NEGOTIATION','BY DEF PAYMENT','BY MIXED PYMT'}" name="authorisationMode" id="authorisationMode" headerKey="" headerValue="Select Mode" ></s:select></td>
	</tr>
	<tr>
			<td width="100%" class=textLeft><s:label cssClass="MandatoryField" value="%{getText('label.700XXX.availableBankNameandAddress')}"></s:label><span class="MandatoryField">[Mandatory]</span></td>
	</tr>
	<tr>
			<td width="100%" class="text"><s:textarea name="authorisedAndAddress" id="authorisedAndAddress" rows="4" cols="35" wrap="HARD" onkeyup="callAuthorised()" onKeyPress='return maxLength(this,"139","authorisedAndAddress","35",event);' ></s:textarea> </td>
	</tr>
	<tr>
			<td width="100%" class="textLeft"><s:label value="%{getText('label.700XXX.draftsAt')}"></s:label><span class="optional">[Optional]</span></td>
	</tr>
	<tr>
			<td width="100%" class="text"><s:textarea name="DraftsAt" id="DraftsAt" rows="3" cols="35" wrap="HARD" onKeyPress='return maxLength(this,"104","DraftsAt","35",event);'></s:textarea><b>[When used, fields 42C and 42a must both be present]</b> </td>
	</tr>
	<tr>
			<td width="100%" class="textLeft"><s:label value="%{getText('label.700XXX.Drawee')}"></s:label><span class="optional">[Optional]</span></td>
	</tr>
	<tr>
			<td width="100%" class="text"><s:select list="{'A','D'}" name="draweeIdentifier" id="draweeIdentifier" headerKey="" headerValue="Select" onChange="javascript:callDrawee();" ></s:select><b>[Either fields 42C and 42a together, or field 42M alone, or field 42P alone may be present. No other
combination of these fields is allowed]</b></td>
	</tr>
	<tr>
			<td width="100%" class="textLeft"><s:label value="%{getText('label.700XXX.draweeBankCode')}"></s:label><span class="optional">[Optional]</span></td>
	</tr>
	<tr>
			<td width="100%" class="text"><s:textfield name="draweeBankCode" id="draweeBankCode" maxLength="11" onkeyup="callDraweeAt()" onKeyPress='return notAllowCheck(this,event);'></s:textfield><input type="button" value="Search IFSC..." onclick="return callSearchIFSC('DRAWEEIFSC','BOTH')" id ="draweeSearch" class="btn" /> </td>
	</tr>
	<tr>
			<td width="100%" class="textLeft"><s:label value="%{getText('label.700XXX.dreaweeNameandAddress')}"></s:label><span class="optional">[Optional]</span></td>
	</tr>
	<tr>
			<td width="100%" class="text"><s:textarea name="draweeBankNameAddress" id="draweeBankNameAddress" cols="35" rows="4" wrap="HARD" onkeyup="callDraweeAt()" onKeyPress='return maxLength(this,"139","draweeBankNameAddress","35",event);'></s:textarea> </td>
	</tr>
	<tr>
			<td width="100%" class="textLeft"><s:label value="%{getText('label.700XXX.mixedPaymnetDetails')}"></s:label><span class="optional">[Optional]</span></td>
	</tr>
	<tr>
			<td width="100%" class="text"><s:textarea name="mixedPaymentDetails" id="mixedPaymentDetails" rows="4" cols="35" wrap="HARD" onKeyPress='return maxLength(this,"139","mixedPaymentDetails","35",event);'></s:textarea> </td>
	</tr>
	<tr>
			<td width="100%" class="textLeft"><s:label value="%{getText('label.700XXX.deferredPaymentDetails')}"></s:label><span class="optional">[Optional]</span></td>
	</tr>
	<tr>
			<td width="100%" class="text"><s:textarea name="deferredPaymentDetails" id="deferredPaymentDetails" rows="4" cols="35" wrap="HARD" onKeyPress='return maxLength(this,"139","deferredPaymentDetails","35",event);'></s:textarea> </td>
	</tr>
	<tr>
			<td width="100%" class="textLeft"><s:label value="%{getText('label.700XXX.partialPayments')}"></s:label><span class="optional">[Optional]</span></td>
	</tr>
	<tr>
			<td width="100%" class="text"><s:textarea name="partialShipments" id="partialShipments" rows="1" cols="35" onKeyPress='return notAllowCheck(this,event);' ></s:textarea> </td>
	</tr>
	<tr>
			<td width="100%" class="textLeft"><s:label value="%{getText('label.700XXX.transshipment')}"></s:label><span class="optional">[Optional]</span></td>
	</tr>
	<tr>
			<td width="100%" class="text"><s:textarea name="transhipment" id="transhipment" rows="1" cols="35" onKeyPress='return notAllowCheck(this,event);' ></s:textarea> </td>
	</tr>
	<tr>
			<td width="100%" class="textLeft"><s:label value="%{getText('label.700XXX.placeofDispatch')}"></s:label><span class="optional">[Optional]</span></td>
	</tr>
	<tr>
			<td width="100%" class="text"><s:textarea name="initialDispatchPlace" id="initialDispatchPlace" rows="1" cols="65" onKeyPress='return notAllowCheck(this,event);' ></s:textarea> </td>
	</tr>
	<tr>
			<td width="100%" class="textLeft"><s:label value="%{getText('label.700XXX.portofLoading')}"></s:label><span class="optional">[Optional]</span></td>
	</tr>
	<tr>
			<td width="100%" class="text"><s:textarea name="goodsLoadingDispatchPlace" id="goodsLoadingDispatchPlace" rows="1" cols="65" onKeyPress='return notAllowCheck(this,event);' ></s:textarea> </td>
	</tr>
	<tr>
			<td width="100%" class="textLeft"><s:label value="%{getText('label.700XXX.portofDischarge')}"></s:label><span class="optional">[Optional]</span></td>
	</tr>
	<tr>
			<td width="100%" class="text"><s:textarea name="goodsDestination" id="goodsDestination" rows="1" cols="65" onKeyPress='return notAllowCheck(this,event);'></s:textarea> </td>
	</tr>
	<tr>
			<td width="100%" class="textLeft"><s:label value="%{getText('label.700XXX.placeofFinalDestination')}"></s:label><span class="optional">[Optional]</span></td>
	</tr>
	<tr>
			<td width="100%" class="text"><s:textarea name="finalDeliveryPlace" id="finalDeliveryPlace" rows="1" cols="65" onKeyPress='return notAllowCheck(this,event);' ></s:textarea> </td>
	</tr>
	<tr>
			<td width="100%" class="textLeft"><s:label value="%{getText('label.700XXX.latestDateofShipment')}"></s:label><span class="optional">[Optional]</span></td>
	</tr>
	<tr>
			<td width="100%" class="text"><sx:datetimepicker name="latestDateofShipment" displayFormat="MM/dd/yyyy" cssClass="txtField" type="date" id="latestDateofShipment"  ></sx:datetimepicker><b>[Either field 44C or 44D, but not both, may be present]</b></td>
	</tr>
	<tr>
			<td width="100%" class="textLeft"><s:label value="%{getText('label.700XXX.shipmentPeriod')}"></s:label><span class="optional">[Optional]</span></td>
	</tr>
	<tr>
			<td width="100%" class="text"><s:textarea name="shipmentPeriod" id="shipmentPeriod" rows="6" cols="65" wrap="HARD"  onKeyPress='return maxLength(this,"389","shipmentPeriod","65",event);'></s:textarea> </td>
	</tr>
	<tr>
			<td width="100%" class="textLeft"><s:label value="%{getText('label.700XXX.descriptionofGoods')}"></s:label><span class="optional">[Optional]</span></td>
	</tr>			
	<tr>
			<td width="100%" class="text"><s:textarea name="descriptionofGoods" id="descriptionofGoods" rows="100" cols="65" wrap="HARD" style="overflow: scroll; resize: none;" onKeyPress='return maxLength(this,"6500","lcCommodity","65",event);'></s:textarea> </td>
	</tr>
	<tr>
			<td width="100%" class="textLeft"><s:label value="%{getText('label.700XXX.documentsRequired')}"></s:label><span class="optional">[Optional]</span></td>
	</tr>
	<tr>	
			<td width="100%" class="text"><s:textarea name="documentsRequired" id="documentsRequired" rows="100" cols="65" wrap="HARD" style="overflow: scroll; resize: none;" onKeyPress='return maxLength(this,"6500","documentsRequired","65",event);'></s:textarea> </td>
	</tr>
	<tr>
			<td width="100%" class="textLeft"><s:label value="%{getText('label.700XXX.additinalConditions')}"></s:label><span class="optional">[Optional]</span></td>
	</tr>
	<tr>
			<td width="100%" class="text"><s:textarea name="additionalConditions" id="additionalConditions" rows="100" cols="65" wrap="HARD" style="overflow: scroll; resize: none;" onKeyPress='return maxLength(this,"6500","additionalConditions","65",event);'></s:textarea> </td>
	</tr>
	<tr>
			<td width="100%" class="textLeft"><s:label value="%{getText('label.700XXX.charges')}"></s:label><span class="optional">[Optional]</span></td>
	</tr>
	<tr>
			<td width="100%" class="text"><s:textarea name="chargeDetails" id="chargeDetails" rows="6" cols="35" wrap="HARD" onKeyPress='return maxLength(this,"209","chargeDetails","35",event);'></s:textarea> </td>
	</tr>
	<tr>
			<td width="100%" class="textLeft"><s:label value="%{getText('label.700XXX.periodForPresentaion')}"></s:label><span class="optional">[Optional]</span></td>
	</tr>
	<tr>
			<td width="100%" class="text"><s:textarea name="periodforPresentation" id="periodforPresentation" rows="4" cols="35" wrap="HARD" onKeyPress='return maxLength(this,"139","shipmentPeriod","35",event);' ></s:textarea> </td>
	</tr>
	<tr>
			<td width="100%" class="textLeft"><s:label cssClass="MandatoryField" value="%{getText('label.700XXX.confirmation')}"></s:label><span class="MandatoryField">[Mandatory]</span></td>
	</tr>
	<tr>
			<td width="100%" class="text"><s:select list="{'CONFIRM','MAY ADD','WITHOUT'}" headerKey="" headerValue="Select Code" name="confirmationCode" id="confirmationCode" ></s:select> </td>
	</tr>
	<tr>
			<td width="100%" class="textLeft"><s:label value="%{getText('label.700XXX.reimbursing')}"></s:label><span class="optional">[Optional]</span></td>
	</tr>
	<tr>		
			<td width="100%" class="text"><s:select list="{'A','D'}" name="reimbursingIdentifier" id="reimbursingIdentifier" headerKey="" headerValue="Select" onChange="javascript:callReimbursing();" ></s:select></td>
	</tr>
	<tr>
			<td width="100%" class="textLeft"><s:label value="%{getText('label.700XXX.reimbursingBankCode')}"></s:label><span class="optional">[Optional]</span></td>
	</tr>
	<tr>
			<td width="100%" class="text"><s:textfield name="reimbursingBankCode" id="reimbursingBankCode" maxLength="11" onkeyup="callReimbusing()" onKeyPress='return notAllowCheck(this,event);'></s:textfield><input type="button" value="Search IFSC..." onclick="return callSearchIFSC('REIMBUSINGIFSC','BOTH')" id="reimbursingSearch" class="btn" />
	</tr>
	<tr>
			<td width="100%" class="textLeft"><s:label value="%{getText('label.700XXX.reimbursingNameandAddress')}"></s:label><span class="optional">[Optional]</span></td>
	</tr>
	<tr>
			<td width="100%" class="text"><s:textarea name="reimbursingBankNameAddress" id="reimbursingBankNameAddress" rows="4" cols="35" wrap="HARD" onkeyup="callReimbusing()" onKeyPress='return maxLength(this,"139","reimbursingBankNameAddress","35",event);'></s:textarea> </td>
	</tr>
	<tr>
			<td width="100%" class="textLeft"><s:label value="%{getText('label.700XXX.instructionToPaying')}"></s:label><span class="optional">[Optional]</span></td>
	</tr>
	<tr>
			<td width="100%" class="text"><s:textarea rows="12" cols="65" wrap="HARD" name="instructionstoPayingBank" id="instructionstoPayingBank" onKeyPress='return maxLength(this,"779","instructionstoPayingBank","65",event);'></s:textarea> </td>
	</tr>
	<tr>
			<td width="100%" class="textLeft"><s:label value="%{getText('label.700XXX.advisingThrough')}"></s:label><span class="optional">[Optional]</span></td>
	</tr>
	<tr>
			<td width="100%" class="text"><s:select list="{'A','B','D'}" name="advisingIdentifier" id="advisingIdentifier" headerKey="" headerValue="Select" onChange="javascript:callAdvisingThroughBank();" ></s:select></td>
	</tr>
	<tr>
			<td width="100%" class="textLeft"><s:label value="%{getText('label.700XXX.advisingThroughBankCode')}"></s:label><span class="optional">[Optional]</span></td>
	</tr>
	<tr>
			<td width="100%" class="text"><s:textfield name="adviseThroughBankCode" id="adviseThroughBankCode" maxLength="11" onKeyPress='return notAllowCheck(this,event);'> </s:textfield><input type="button" value="Search IFSC..." onclick="return callSearchIFSC('ADVICETHOUGHIFSC','BOTH')" id="btnSearch" class="btn" /> </td>
	</tr>
	<tr>
			<td width="100%" class="textLeft"><s:label value="%{getText('label.700XXX.advisingThroughBankLocation')}"></s:label><span class="optional">[Optional]</span></td>
	</tr>
	<tr>
			<td width="100%" class="text"><s:textfield name="adviseThroughBankLocation" id="adviseThroughBankLocation" maxLength="35" onkeyup="callAdvise()" onKeyPress='return notAllowCheck(this,event);' ></s:textfield> </td>
	</tr>
	<tr>
			<td width="100%" class="textLeft"><s:label value="%{getText('label.700XXX.advisingThroughBankNameAndAddress')}"></s:label><span class="optional">[Optional]</span></td>
	</tr>
	<tr>
			<td width="100%" class="text"><s:textarea name="adviseThroughBankName" id="adviseThroughBankName" rows = "4" cols="35" wrap="HARD" onkeyup="callAdvise()" onKeyPress='return maxLength(this,"139","adviseThroughBankName","35",event);'></s:textarea> </td>
	</tr>
	<tr>
			<td width="100%" class="textLeft"><s:label value="%{getText('label.700XXX.senderToReceiverInfo')}"></s:label><span class="optional">[Optional]</span></td>
	</tr>
	<tr>
			<td width="100%" class="text"><s:textarea name="SendertoReceiverInformation" id="SendertoReceiverInformation" cols="35" rows="6" wrap="HARD" onKeyPress='return maxLength(this,"209","SendertoReceiverInformation","35",event);'></s:textarea> </td>
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
					<s:if test="%{flagMarked!='flagMarked'}" >
						<input type ="button" value="Submit"  class="btn"  onclick="callCommentPOPUp()"/> 
						
					</s:if>
					<s:if test="%{flagMarked=='flagMarked'}">
						<input type="button" value="Mark As Acknowledgement" onclick="callAuthorize()">
					</s:if>		
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
					<s:if test="%{flagMarked!='flagMarked'}" >
						<input type ="button" value="Submit"  class="btn"  onclick="callCommentPOPUp()"/> 
						<input type="button" value="Save Template" onclick="SaveTemplate()" class="btn"  />	
					</s:if>
					<s:if test="%{flagMarked=='flagMarked'}">
						<input type="button" value="Mark As Acknowledgement" onclick="callAuthorize()">
					</s:if>		
				<input type="reset" value="Cancel" onclick="cancel()" class="btn"  />
				</s:if>
			</s:if>
					
				
			</td>
		</tr>
	</table>
	
	<s:hidden id="hiddenTxnRef" name="hiddenTxnRef"></s:hidden>
	
 <!-- Group One Div Ends --></div>
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

window.onload = function() {
	  
	 dojo.widget.byId("sequence").disable();
	 dojo.widget.byId("sequenceofTotal").disable();

};
</script>