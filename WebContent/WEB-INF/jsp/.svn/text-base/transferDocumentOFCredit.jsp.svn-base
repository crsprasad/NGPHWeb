
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
	//alert(row)
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
	document.forms[0].action="saveTransferDocumentaryCredit";
	document.forms[0].submit();
}
function callAddTOGrid()
{
	document.forms[0].action = "addRowToLcGridlcOpen";
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
		
		
		document.forms[0].action="displayDocumentaryCreditData";
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
	//alert("positiveTolerence P: -"+positiveTolerence);
	//alert("negativeTolerance P: -"+negativeTolerance);
	//alert("maxCredit P: -"+maxCredit);
	
		if (positiveTolerence != "") {
		//	alert("+");
			document.getElementById("maximumCreditAmount").disabled = true;
		}else if(negativeTolerance!="")
		{
		//	alert("-");
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

	function callApplicant()
	{
		var applicantBankNameAndAddress = document.getElementById("applicantBankNameAddress").value;
		var applicantBankCode = document.getElementById("applicantBankCode").value;
		if (applicantBankCode != "") {
			document.getElementById("applicantBankNameAddress").disabled = true;
			
		}else if (applicantBankNameAndAddress != "") {
			document.getElementById("applicantBankCode").disabled = true;
			document.getElementById("applicantSearch").disabled = true;
			
		}else
			{
			document.getElementById("applicantBankNameAddress").disabled = false;
			document.getElementById("applicantBankCode").disabled = false;
			document.getElementById("applicantSearch").disabled = false;
			document.getElementById("applicantBankNameAddress").value="";
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

	function callReimbusing()
	{
		var reimbursingBankNameAddress = document.getElementById("reimbursingBankNameAddress").value;
		var reimbursingBankCode = document.getElementById("reimbursingBankCode").value;
		if (reimbursingBankCode != "") {
			document.getElementById("reimbursingBankNameAddress").disabled = true;
			
		}else if (reimbursingBankNameAddress != "") {
			document.getElementById("reimbursingBankCode").disabled = true;
			document.getElementById("reimbursingSearch").disabled = true;
			
		}else
			{
			document.getElementById("reimbursingBankNameAddress").disabled = false;
			document.getElementById("reimbursingBankCode").disabled = false;
			document.getElementById("reimbursingSearch").disabled = false;
			document.getElementById("reimbursingBankNameAddress").value="";
			}
		
	}

	function callCommentPOPUp()
	{
		var repair = document.getElementById("repair").value;
//	alert("Repair :  - "+repair);
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
		document.forms[0].action="getTransferDocumentaryCreditApproval" ;
		document.forms[0].submit();
	}
</script>


<sx:head parseContent="true" />

<s:head />


<div id="contentPane">
<div id="contentPaneTop"></div>
<div id="contentPaneFluid">
<div id="contentPaneInternal"><span class="crumbs" ><a
	href="#">Outbound Payments</a>&nbsp;>&nbsp;Letter Of Credit&nbsp;>&nbsp;Transfer Documentary Credit</span></div>
<div id="content">
<h1>Transfer Documentary Credit</h1><s:form method="post" id="form"  >
<s:hidden id="repairComments" name="repairComments"></s:hidden> 
<s:hidden id="searchAction" name="searchAction"></s:hidden>	
<s:hidden name="rowTodelete" id="rowTodelete"></s:hidden>
<s:hidden name="repair" id="repair"></s:hidden>
<s:hidden name="IFSCFLAG" id="IFSCFLAG" ></s:hidden>
<s:hidden id="flagForScreen" name="flagForScreen"></s:hidden>
<s:hidden id="issueDate" name="issueDate"></s:hidden>
<s:hidden id="msgHost" name="msgHost"> </s:hidden>
<s:hidden id="seqNo" name="seqNo"></s:hidden>
<s:hidden id="comment" name="comment"></s:hidden>
<s:hidden id="msgDirection" name="msgDirection" value="I"></s:hidden>
<s:hidden id="msgStatusForLcFetch" name="msgStatusForLcFetch"></s:hidden>
<s:hidden name="Narrative" id="Narrative" ></s:hidden>
<s:hidden name="senderCorrespontAcount" id="senderCorrespontAcount" ></s:hidden>
<s:hidden name="reimbursingBankNameAddress" id="reimbursingBankNameAddress"></s:hidden>
<s:hidden name="reimbursingBankCode" id="reimbursingBankCode" ></s:hidden>
<s:hidden name="shipmentTerms" id="shipmentTerms" ></s:hidden>	
<s:hidden name="applicantBankNameAddress" id="applicantBankNameAddress"></s:hidden>
<s:hidden name="applicantBankCode" id="applicantBankCode" maxLength="11" ></s:hidden>
<s:hidden  name="applicantBankpartyidentifier" id="applicantBankpartyidentifier"></s:hidden>
<s:hidden name="applicantAccount" id="applicantAccount" ></s:hidden>
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
			<td width="20%" class="textRight"><s:label	value="%{getText('label.lc_Number')}"></s:label>:<span	class="mandatory">*</span></td>
			<td width="30%" class="text"><s:textfield name="lcNumber" id="lcNumber" maxLength="16" onKeyPress='return notAllowCheck(this,event);'></s:textfield><input type="button" value="Fetch" onclick="callFetchLCDetails('4,6')"> </td>
			<td width="20%" class="textRight"><s:label value="%{getText('label.lcType')}"></s:label>:</td>
			<td width="30%" class="text"><s:textfield name="lcType" id="lcType" readonly="true" ></s:textfield></td>
		</tr>
		<tr>
			<td width="20%" class="textRight"><s:label	value="%{getText('label.DateofIssue')}"></s:label>:</td>
			<td width="30%" class="text"><s:textfield name="issueDate"  id="issueDate" readonly="true"/> </td>
		</tr>
		<tr>
			<td width="20%" class="textRight"><s:label	value="%{getText('label.issuingBankPID')}"></s:label>:</td>
			<td width="30%" class="text"><s:textfield name="issuingBankPID" id="issuingBankPID" readonly="true"></s:textfield></td>
			<td width="20%" class="textRight"><s:label	value="%{getText('label.issuingBankCode')}"></s:label>:</td>
			<td width="30%" class="text"><s:textfield name="issuingBankCode" id="issuingBankCode" readonly="true"></s:textfield> </td>
		</tr>
		<tr>
			<td width="20%" class="textRight"><s:label	value="%{getText('label.issuingBankNameAndAddress')}"></s:label>:</td>
			<td width="30%" class="text"><s:textfield name="issunigBankNameAndAddress" id="issunigBankNameAndAddress" readonly="true"></s:textfield> </td>
			<td width="20%" class="textRight"><s:label	value="%{getText('label.nonBankIssing')}"></s:label>:</td>
			<td width="30%" class="text"><s:textfield name="nonIssuingBank" id="nonIssuingBank" onKeyPress='return notAllowCheck(this,event);'></s:textfield> </td>
		</tr>
		<tr>
			<td width="20%" class="textRight"><s:label
					value="%{getText('label.lcCurrency')}"></s:label>:<span	class="mandatory">*</span></td>
			<td width="30%" class="text">
			<s:textfield name="lcCurrency" id="lcCurrency" readonly="true" ></s:textfield>
			</td>
			<td width="20%" class="textRight"><s:label	value="%{getText('label.lcAmount')}"></s:label>:<span	class="mandatory">*</span></td>
			<td width="30%" class="text"><s:textfield name="lcAmount" id="lcAmount" readonly="true"></s:textfield> </td>
		</tr>
		<tr>
		<td width="20%" class="textRight"><s:label	value="%{getText('label.applicableRules')}"></s:label>:<span class="mandatory">*</span></td>				
		<td width="30%" class="text"><s:textfield name="applicableRule" id="applicableRule" readonly="true"></s:textfield>  </td>
		<td width="20%" class="textRight"><s:label	value="%{getText('label.applicableNarrative')}"></s:label></td>
		<td width="30%" class="text"> <s:textfield name="applicableNarrative" id="applicableNarrative" readonly="true"></s:textfield></td>
		</tr>
		<tr>
			<td width="20%" class="textRight"><s:label
					value="%{getText('label.lcExpiryDate')}"></s:label>:<span	class="mandatory">*</span></td>
			<td width="30%" class="text"><s:textfield
					name="lcExpiryDate" cssClass="txtField" id="lcExpiryDate"
					readonly="true"/></td>
			<td width="20%" class="textRight"><s:label	value="%{getText('label.lcExpiryPlace')}"></s:label>:<span	class="mandatory">*</span></td>
			<td width="30%" class="text"><s:textfield name="lcExpirePlace" id="lcExpirePlace" readonly="true" ></s:textfield> </td>
		</tr>
		
		<tr>
			<td width="20%" class="textRight"><s:label
					value="%{getText('label.firstBenificary')}"></s:label>:<span	class="mandatory">*</span></td>
			<td width="30%" class="text"><s:textarea rows="4" name="firstBeneficiaryNameAddress" id="firstBeneficiaryNameAddress" cols="35" readonly="true" onKeyPress='return maxLength(this,"139","applicantNameAddress","35",event);'></s:textarea> </td>
			<td width="20%" class="textRight"><s:label	value="%{getText('label.AdvisingBank')}"></s:label>:<span	class="mandatory">*</span></td>
			<td width="30%" class="text"><s:textfield name="advisingBank" id="advisingBank" maxLength="11" onKeyPress='return notAllowCheck(this,event);'> </s:textfield><input type="button" value="Search IFSC..."
					onclick="return callSearchIFSC('ADVISINGIFSC','PARTY')" id="btnSearch"
					class="btn" /> </td>
		</tr>
		
		<tr>
			<td width="20%" class="textRight"><s:label
					value="%{getText('label.secondBeneficiaryAccount')}"></s:label></td>
			<td width="30%" class="text"><s:textfield  name="beneficiaryAccount" id="beneficiaryAccount" onKeyPress='return notAllowCheck(this,event);'></s:textfield> </td>
			<td width="20%" class="textRight"><s:label	value="%{getText('label.secondBeneficiaryNameAddress')}"></s:label>:<span	class="mandatory">*</span></td>
			<td width="30%" class="text"><s:textarea name="beneficiaryNameAddress" id="beneficiaryNameAddress" rows="4" cols="35"  onKeyPress='return maxLength(this,"139","beneficiaryNameAddress","35",event);'></s:textarea> </td>
		</tr>
		<tr>
			<td width="20%" class="textRight"><s:label
					value="%{getText('label.authorisedBankCode')}"></s:label>:</td>
			<td width="30%" class="text"><s:textfield name="authorisedBankCode" id="authorisedBankCode" onkeyup="callAuthorised()" onKeyPress='return notAllowCheck(this,event);'></s:textfield><input type="button" value="Search IFSC..."
						onclick="return callSearchIFSC('AUTHORISEDIFSC','BRANCH')" id ="btnAuth" class="btn" /></td>
			<td width="20%" class="textRight"><s:label	value="%{getText('label.authorisationMode')}"></s:label>:<span	class="mandatory">*</span></td>
			<td width="30%" class="text">
			<s:select list="{'BY PAYMENT','BY ACCEPTANCE','BY NEGOTIATION','BY DEF PAYMENT','BY MIXED PYMT'}" name="authorisationMode" id="authorisationMode" headerKey="" headerValue="Select Mode" ></s:select>
			</td>
		</tr>
		
		<tr>
			<td width="20%" class="textRight"><s:label
					value="%{getText('label.authorisedAndAddress')}"></s:label>:</td>
			<td width="30%" class="text"><s:textfield name="authorisedAndAddress" id="authorisedAndAddress" onkeyup="callAuthorised()" onKeyPress='return notAllowCheck(this,event);'></s:textfield> </td>
			<td width="20%" class="textRight"><s:label	value="%{getText('label.InstructionstoPayingBank')}"></s:label>:</td>
		<td width="30%" class="text"><s:textarea name="instructionstoPayingBank" id="instructionstoPayingBank" onKeyPress='return maxLength(this,"139","instructionstoPayingBank","35",event);'></s:textarea> </td>
			
		</tr>
			<tr>
			<td width="20%" class="textRight"><s:label
					value="%{getText('label.goodsDestination')}"></s:label>:</td>
			<td width="30%" class="text"><s:textfield name="goodsDestination" id="goodsDestination" maxLength="65" readonly="true"></s:textfield> </td>
			<td width="20%" class="textRight"><s:label	value="%{getText('label.latestDateofShipment')}"></s:label>:</td>
			<td width="30%" class="text">
			<s:textfield name="latestDateofShipment" id="latestDateofShipment"  readonly="true"></s:textfield>
			
		</tr>	
		<tr>
			<td width="20%" class="textRight"><s:label	value="%{getText('label.ShipmentPeriod')}"></s:label>:</td>
			<td width="30%" class="text"><s:textarea name="shipmentPeriod" id="shipmentPeriod" readonly="true"   onKeyPress='return maxLength(this,"389","shipmentPeriod","65",event);'></s:textarea> </td>
			<td class="textRight"><s:label	value="%{getText('label.goodsLoadingDispatchPlace')}"></s:label>:</td>
		<td width="30%" class="text"><s:textfield name="goodsLoadingDispatchPlace" id="goodsLoadingDispatchPlace" readonly="true"></s:textfield> </td>
		
		</tr>
		<tr>
			<td width="20%" class="textRight"><s:label
					value="%{getText('label.PeriodforPresentation')}"></s:label>:</td>
			<td width="30%" class="text"><s:textarea name="periodforPresentation" id="periodforPresentation" readonly="true"></s:textarea> </td>
			<td width="20%" class="textRight"><s:label	value="%{getText('label.ConfirmationCode')}"></s:label>:<span
					class="mandatory">*</span></td>
			<td width="30%" class="text"><s:textfield name="confirmationCode" id="confirmationCode" readonly="true"></s:textfield> </td>
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
					value="%{getText('label.positiveTolerance')}"></s:label>:</td>
			<td width="30%" class="text">
			<s:textfield name="positiveTolerance" id="positiveTolerance" readonly="true"></s:textfield>
			
			</td>
			<td width="20%" class="textRight"><s:label	value="%{getText('label.negativeTolerance')}"></s:label>:</td>
			<td width="30%" class="text">
			
			<s:textfield name="negativeTolerance" id="negativeTolerance" readonly="true"></s:textfield>
						</td>
		</tr>
		<tr>
			<td width="20%" class="textRight"><s:label
					value="%{getText('label.maximumCreditAmount')}"></s:label>:</td>
			<td width="30%" class="text"><s:textfield
					name="maximumCreditAmount" id="maximumCreditAmount" readonly="true"></s:textfield > </td>
			<td width="20%" class="textRight"><s:label	value="%{getText('label.additionalAmounts')}"></s:label>:</td>
			<td width="30%" class="text"><s:textfield name="additionalAmounts" id="additionalAmounts" readonly="true"></s:textfield> </td>
		</tr>
		
		<tr>
			<td width="20%" class="textRight"><s:label	value="%{getText('label.DraftsAt')}"></s:label>:</td>
			<td width="30%" class="text"><s:textarea name="DraftsAt" id="DraftsAt" rows="3" onKeyPress='return maxLength(this,"104","DraftsAt","35",event);'></s:textarea> </td>
		</tr>
		
		<tr>
			<td width="20%" class="textRight"><s:label
					value="%{getText('label.DraweeBankpartyidentifier')}"></s:label>:</td>
			<td width="30%" class="text"><s:select list="{'C','D'}" name="draweeBankpartyidentifier" id="draweeBankpartyidentifier" headerKey="" headerValue="Select PID"></s:select> 
			<s:textfield name="draweeBankAccount" id="draweeBankAccount" maxLength="34" onKeyPress='return notAllowCheck(this,event);'></s:textfield>
			</td>
			<td width="20%" class="textRight"><s:label	value="%{getText('label.DraweeBankCode')}"></s:label>:</td>
			<td width="30%" class="text"><s:textfield name="draweeBankCode" id="draweeBankCode" maxLength="11" onkeyup="callDraweeAt()" onKeyPress='return notAllowCheck(this,event);'></s:textfield><input type="button" value="Search IFSC..."
						onclick="return callSearchIFSC('DRAWEEIFSC','BOTH')" id ="draweeSearch" class="btn" /> </td>
		</tr>
		
		<tr>
			<td width="20%" class="textRight"><s:label
					value="%{getText('label.DraweeBankNameAddress')}"></s:label>:</td>
			<td width="30%" class="text"><s:textarea name="draweeBankNameAddress" id="draweeBankNameAddress" cols="35" rows="4" onkeyup="callDraweeAt()" onKeyPress='return maxLength(this,"139","draweeBankNameAddress","35",event);'></s:textarea> </td>
			<td width="20%" class="textRight"><s:label	value="%{getText('label.MixedPaymentDetails')}"></s:label>:</td>
			<td width="30%" class="text"><s:textarea name="mixedPaymentDetails" id="mixedPaymentDetails" rows="4" cols="35"  onKeyPress='return maxLength(this,"139","mixedPaymentDetails","35",event);'></s:textarea> </td>
		</tr>
		<tr>
			<td width="20%" class="textRight"><s:label
					value="%{getText('label.PartialShipments')}"></s:label>:</td>
			<td width="30%" class="text"><s:textfield name="partialShipments" id="partialShipments" maxLength="35" readonly="true"></s:textfield> </td>
			<td width="20%" class="textRight"><s:label	value="%{getText('label.Transhipment')}"></s:label>:</td>
			<td width="30%" class="text"><s:textfield name="transhipment" id="transhipment" maxLength="35" readonly="true"></s:textfield> </td>
		</tr>
		
		<tr>
			<td width="20%" class="textRight"><s:label	value="%{getText('label.DeferredPaymentDetails')}"></s:label>:</td>
			<td width="30%" class="text"><s:textarea name="deferredPaymentDetails" id="deferredPaymentDetails"  rows="4" cols="35" onKeyPress='return maxLength(this,"139","deferredPaymentDetails","35",event);'></s:textarea> </td>
		</tr>
		
		<tr>
			<td width="20%" class="textRight"><s:label	value="%{getText('label.initialDispatchPlace')}"></s:label>:</td>
			<td width="30%" class="text"><s:textfield name="initialDispatchPlace" id="initialDispatchPlace" readonly="true"></s:textfield> </td>
			
			<td width="20%" class="textRight"><s:label	value="%{getText('label.finalDeliveryPlace')}"></s:label>:</td>
			<td width="30%" class="text"><s:textfield name="finalDeliveryPlace" id="finalDeliveryPlace" readonly="true"></s:textfield> </td>
		</tr>
		
		</table>
	</s:div> <!-- Group Two Div Ends --></div>
	</div>
	</div>
	<br />
	<div id="CollapsiblePanel3" class="CollapsiblePanel">
	<div class="CollapsiblePanelTab">  <img src="images/openPanel.png" width="9" height="7" alt="openPanel" />&nbsp;Commodity Details</div>
	<div class="CollapsiblePanelContent">
	<div> <s:div id="register"
		cssClass="dataGrid" style="width:100%;">
		<table width="100%" cellpadding="5" cellspacing="1" border="0"
			bgcolor="#ffffff">
		<tr>
			<td>
			<display:table uid="row" name="sessionScope.bgCommoditiesList"
				pagesize="10"  cellpadding="3" 
				cellspacing="0" class="dataGrid">
				<display etProperty name="css.tr.even" value="TDBGlightgray" />
				<display etProperty name="css.tr.odd" value="TDBGgray" cssClass="odd"/>
				<display:column title="ID" 
					headerClass="gridHdrBg"  sortable="true" style="text-align:center" property="lcId"></display:column>
				<display:column title="Commodity" 
					headerClass="gridHdrBg"  sortable="true" style="text-align:center" property="lcCommodity"></display:column>

				<!--<display:column>
						<input type="button" value="delete"
							onclick="callDelete('${row.lcId}')">
					</display:column>
				
				--></display:table>
				
				</td></tr></table>
			
	
	</s:div> 
	</div></div></div><br/>
	<div id="CollapsiblePanel4" class="CollapsiblePanel">
	<div class="CollapsiblePanelTab">  <img src="images/openPanel.png" width="9" height="7" alt="openPanel" />&nbsp;
	Other Details</div>
	<div class="CollapsiblePanelContent">
	<s:div id="insCurrency" cssClass="dataGrid" style="width:100%;">
		<table width="100%" cellpadding="5" cellspacing="1" border="0"
			bgcolor="#ffffff">
			<tr>
			
				
		
		</tr>
		
		
			
			
			<tr>
			<td width="20%" class="textRight"><s:label
					value="%{getText('label.DocumentsRequired')}"></s:label>:</td>
			<td width="30%" class="text"><s:textarea name="documentsRequired" id="documentsRequired" rows="20" cols="65" readonly="true" onKeyPress='return maxLength(this,"6500","documentsRequired","65",event);'></s:textarea> </td>
			<td width="20%" class="textRight"><s:label	value="%{getText('label.AdditionalConditions')}"></s:label>:</td>
			<td width="30%" class="text"><s:textarea name="additionalConditions" id="additionalConditions" rows="20" cols="65" onKeyPress='return maxLength(this,"6500","additionalConditions","65",event);'></s:textarea> </td>
		</tr>
		<tr>
			<td width="20%" class="textRight"><s:label
					value="%{getText('label.ChargeDetails')}"></s:label>:</td>
			<td width="30%" class="text"><s:textarea name="chargeDetails" id="chargeDetails" rows="6" cols="35" onKeyPress='return maxLength(this,"209","chargeDetails","35",event);'></s:textarea> </td>
			
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
			<td width="30%" class="text"><s:textarea name="adviseThroughBankName" id="adviseThroughBankName" rows = "4" cols="35" onkeyup="callAdvise()" onKeyPress='return maxLength(this,"139","adviseThroughBankName","35",event);'></s:textarea> </td>
		</tr>
		<tr>
			
			<td width="20%" class="textRight"><s:label	value="%{getText('label.SendertoReceiverInformation')}"></s:label>:</td>
			<td width="30%" class="text"><s:textarea name="SendertoReceiverInformation" id="SendertoReceiverInformation" cols="35" rows="4" onKeyPress='return maxLength(this,"209","SendertoReceiverInformation","35",event);'></s:textarea> </td>
		</tr>
			
			
			</table></s:div></div></div>
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
	var CollapsiblePanel4 = new Spry.Widget.CollapsiblePanel(
			"CollapsiblePanel4");
</script>
<script type="text/javascript">
var check= document.getElementById("hiddenTxnRef").value;
if(check!=''){
	var aa = document.getElementById('form');
	for ( var i = 0; i < aa.length-6; i++) {
		//alert(aa.elements[i].value)
		if(aa.elements[i].value!='Approve' || aa.elements[i].value!='Reject')
		aa.elements[i].disabled = true;
	}
	document.getElementById("lcExpiryDate").disabled= true;
	document.getElementById("latestDateofShipment").disabled = true;
}

</script>
	