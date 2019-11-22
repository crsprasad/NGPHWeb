<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<%@taglib uri="http://displaytag.sf.net" prefix="display"%>
<script type="text/javascript">


	function callSubmit(saveAction)
	{
		
		var aa = document.getElementById('form');
		for ( var i = 0; i < aa.length; i++) {
			aa.elements[i].disabled = false;
			}
		document.getElementById("saveAction").value = saveAction;
		document.forms[0].action="saveAmendLC";
		document.forms[0].submit();
	}

function increseAmount(){
	var increaseAmendAmount = document.getElementById("increaseAmendAmount").value;
	var lcAmount = document.getElementById("lcAmount").value;	
	var newLcAmount = document.getElementById("newLcAmount");
	var chkNum = /^[0-9.]+$/;
	if(increaseAmendAmount.search(chkNum)== -1){
		alert("Please enter a valid Increase of Documentary Credit Amount(32B)");
	}else{ 
		if(increaseAmendAmount == ""){
			document.getElementById("decreaseAmendAmount").disabled = false;
			document.getElementById("newLcAmount").value = lcAmount;	
		}else{
			newLcAmount = parseFloat(increaseAmendAmount) + parseFloat(lcAmount);
			document.getElementById("newLcAmount").value = newLcAmount;	
			document.getElementById("decreaseAmendAmount").disabled = true;
		}
	}
}


function decreserAmount(){
	var decreaseAmendAmount = document.getElementById("decreaseAmendAmount").value;
	var lcAmount = document.getElementById("lcAmount").value;	
	var newLcAmount = document.getElementById("newLcAmount");
	var chkNum = /^[0-9.]+$/;
	if(decreaseAmendAmount.search(chkNum)== -1){
		alert("Please enter a valid Decrease of Documentary Credit Amount(33B)");
	}else{ 
		if(decreaseAmendAmount == ""){
			document.getElementById("increaseAmendAmount").disabled = false;
			document.getElementById("newLcAmount").value = lcAmount;	
		}else{
			newLcAmount = parseFloat(lcAmount) - parseFloat(decreaseAmendAmount);
			document.getElementById("newLcAmount").value = newLcAmount;	
			document.getElementById("increaseAmendAmount").disabled = true;
		}
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

function callCommentPOPUp()
{
	
	var repair = document.getElementById("repair").value;
	if(repair!="")
	{
	
			callSubmitForScreen();
		}
	else{
		callSubmitForScreen();	
		}
	
}

function callSubmitForScreen()
{
		var aa = document.getElementById('form');
	for ( var i = 0; i < aa.length; i++) {
		aa.elements[i].disabled = false;
		}
	document.forms[0].action="getAmendLCSubmit";
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
	//sendersCorrespondentPartyIdentifier applicantBankCode issunigBankNameAndAddress	 
	if (document.getElementById("sendersCorrespondentPartyIdentifier").value == "A") {
		document.getElementById("applicantBankCode").disabled = false;
		document.getElementById("issuingBankIFSCSearchBt").disabled=false;
		document.getElementById("issunigBankNameAndAddress").disabled = true;
		
	
		
	} else if (document.getElementById("sendersCorrespondentPartyIdentifier").value == "D") {
		document.getElementById("applicantBankCode").disabled = true;
		document.getElementById("issuingBankIFSCSearchBt").disabled=true;
		document.getElementById("issunigBankNameAndAddress").disabled = false;

	}else{
		document.getElementById("applicantBankCode").disabled = false;
		document.getElementById("issuingBankIFSCSearchBt").disabled=false;
		document.getElementById("issunigBankNameAndAddress").disabled = false;
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
					document.forms[0].action="saveAmendLCTemplate";
					document.forms[0].submit();
				}
		} 
}

function callPrintPreview(){
	document.forms[0].action="printPreviewAmendLCPage";
	document.forms[0].submit();
}
function cancel(){
	var isConfirm = confirm("Please confirm");
	if(isConfirm)
		{
			document.forms[0].action="resetAmendLC";
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
	href="#">Outbound Payments</a>&nbsp;>&nbsp;Amend LC</span></div>
<div id="content">
<h1>Amend LC (MT-707)</h1>
<s:form method="post" id="form">
<s:hidden name="repair" id="repair"></s:hidden>
<s:hidden name="IFSCFLAG" id="IFSCFLAG" ></s:hidden>
<s:hidden id="searchAction" name="searchAction"></s:hidden>	
<s:hidden id="repairComments" name="repairComments"></s:hidden> 
<s:hidden name="RepairData" id="RepairData"></s:hidden>
<s:hidden name="tempName" id="tempName"></s:hidden>
<s:hidden id="seqNo" name="seqNo"></s:hidden>
<s:hidden id="msgRef" name="msgRef"></s:hidden>
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
		          <s:label value="%{getText('label.MB.IssuingBranchIFSC')}"></s:label><span class="optional">[Optional]</span>
		       </td>
		       <td width="30%" class="text">
		         <s:textfield name="issuingBankCode" id="issuingBankCode"></s:textfield><input type="button" value="Search IFSC..." onclick="return callSearchIFSC('ISSUEBANKTHOUGHIFSC','SENDERBANKIFSC')" id="btnadviceThrough"class="btn" />
		       </td>
		       <td width="20%" class="textLeft">
		         <s:label	cssClass="MandatoryField" value="%{getText('label.MB.Benefifsc')}"></s:label> <span class="MandatoryField">[Mandatory]</span>
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
	<div><!-- Group Two Div Starts --> 
	<s:div id="main"	cssClass="dataGrid" style="width:100%;">
    <table width="100%" cellpadding="3" cellspacing="1" border="0"	bgcolor="#ffffff">
    <s:if test="%{repair==''}">
		<tr>
		 <td width="100%" class="textLeft">
		   <s:label cssClass="MandatoryField" value="%{getText('label.707.SenderBanksReference')}"></s:label> <span class="MandatoryField">[Mandatory]</span>
		 </td>
	    </tr>
		<tr>
		  <td width="100%" class="text">
		    <s:textfield name="lcNumber" id="lcNumber" maxLength="16" ></s:textfield>
		  </td>
		</tr>
		<tr>
		  <td width="100%" class="textLeft">
		    <s:label cssClass="MandatoryField" value="%{getText('label.707.ReceiverBanksReference')}"></s:label> <span class="MandatoryField">[Mandatory]</span>
		  </td>
		</tr>
		<tr>
	      <td width="100%" class="text">
	        <s:textfield name="receiverBankReference" id="receiverBankReference" maxLength="16"> </s:textfield>
	      </td>
	    </tr>
		<tr>
		  <td width="100%" class="textLeft">
		    <s:label	value="%{getText('label.707.IssuingBankReference')}"></s:label> <span class="optional">[Optional]</span>
		  </td>
		</tr>
		<tr>
		  <td width="100%" class="text">
		    <s:textfield name="senderBankReference" id="senderBankReference" maxLength="16" onKeyPress='return notAllowCheck(this,event);'></s:textfield> <b>[If field 23 is present, field 52a must also be present]</b>
		  </td>	
		</tr>
		<tr>
		  <td width="100%" class="textLeft" >
		    <s:label value="%{getText('label.707.IssuingBankPartyIdentifier')}"></s:label> <span class="optional">[Optional]</span>
		  </td>
		</tr>
		<tr>
		  <td width="100%" class="text">
		    <s:select list="{'A','D'}" name="sendersCorrespondentPartyIdentifier" id="sendersCorrespondentPartyIdentifier" headerKey="" headerValue="Select" onChange="javascript:callAdvise();" ></s:select>
		  </td>
		</tr>
		<tr>
		  <td width="100%" class="textLeft">
		    <s:label value="%{getText('label.707.IssuingBankCode')}"></s:label> <span class="optional">[Optional]</span>
		  </td>
		</tr>
		<tr>
		  <td width="100%" class="text">
		    <s:textfield name="applicantBankCode" id="applicantBankCode" maxLength="11"></s:textfield>
		     <input type="button" value="Search IFSC..." onclick="return callSearchIFSC('APPLICANTIFSC','BOTH')" id="issuingBankIFSCSearchBt"class="btn" />
		  </td>
		</tr>
			
		<tr>
		  <td width="100%" class="textLeft">
		    <s:label value="%{getText('label.707.IssuingBankNameandAddress')}"></s:label> <span class="optional">[Optional]</span>
		  </td>
		</tr>
		<tr>
		  <td width="100%" class="text">
		    <s:textarea name="issunigBankNameAndAddress" id="issunigBankNameAndAddress" rows="4" cols="35" wrap="HARD" onKeyPress='return maxLength(this,"139","issunigBankNameAndAddress","35",event);' onkeyup="callNegotidvise()"></s:textarea>
		  </td>
		</tr>
		<tr>
		  <td width="100%" class="textLeft" id="tdDateofIssue">
		    <s:label value="%{getText('label.707.DateofIssue')}"></s:label> <span class="optional">[Optional]</span>
		  </td>
		</tr>
		<tr>
		  <td width="100%" class="text">
		    <sx:datetimepicker name="issueDate" cssClass="txtField" id="issueDate" cssClass="txtField"  displayFormat="MM/dd/yyyy" type="date"></sx:datetimepicker>
		  </td>
		</tr>
		<tr>
		  <td width="100%" class="textLeft">
		    <s:label value="%{getText('label.707.DateofAmendment')}"></s:label> <span class="optional">[Optional]</span>
		  </td>
		</tr>
		<tr>
		  <td width="100%" class="text">
		    <sx:datetimepicker name="amendmentDate" cssClass="txtField" id="amendmentDate"  displayFormat="MM/dd/yyyy" type="date"  />
		  </td>
		</tr>
		<tr>
		  <td width="100%" class="textLeft">
		    <s:label value="%{getText('label.707.NumberofAmendment')}"></s:label> <span class="optional">[Optional]</span>
		  </td>
		</tr>
		<tr>
		  <td width="100%" class="text">
		    <s:textfield name="lcAmndmntNo" id="lcAmndmntNo"  maxLength="2"  ></s:textfield>
		  </td>				
		</tr>
		<tr>
		  <td width="100%" class="textLeft">
		    <s:label cssClass="MandatoryField" value="%{getText('label.707.beneficiaryNameAddress')}"></s:label> <span class="MandatoryField">[Mandatory]</span>
		  </td>
		</tr>
		<tr>
		  <td width="100%" class="text">
		    <s:textarea name="beneficiaryNameAddress" id="beneficiaryNameAddress" rows="4" cols="35" wrap="HARD" onKeyPress='return maxLength(this,"209","beneficiaryNameAddress","35",event);' ></s:textarea>
		  </td>
		</tr>
		<tr>
		  <td width="100%" class="textLeft">
		    <s:label value="%{getText('label.707.NewDateofExpiry')}"></s:label> <span class="optional">[Optional]</span>
		  </td>
		</tr>
		<tr>
		  <td width="100%" class="text">
		    <sx:datetimepicker name="newAmendExpiryDate" cssClass="txtField" id="newAmendExpiryDate"  displayFormat="MM/dd/yyyy" type="date" /> <b>[At least one of the fields 31E, 32B, 33B, 34B, 39A, 39B, 39C, 44A, 44E, 44F, 44B, 44C, 44D, 79 or 72 must be present]</b>
		  </td>		
		</tr>
		<tr>
	      <td width="100%" class="textLeft">
	       <s:label value="%{getText('label.707.Currency')}"></s:label> <span class="optional">[Optional]</span>
	      </td>
	    </tr>
	    <tr>
	      <td width="100%" class="text">   
	     	 <s:select list="currCodeDropDown" cssClass="txtField" name="msgCurrency" id="msgCurrency" value="msgCurrency"  headerKey="" headerValue="Select Currency" ></s:select>
	     	 <b>[The currency code in the amount fields 32B, 33B, and 34B must be the same] </b>
	      </td>
	    </tr>
		<tr>		
		  <td width="100%" class="textLeft">
		    <s:label value="%{getText('label.707.IncreaseofLCAmount')}"></s:label> <span class="optional">[Optional]</span>
		  </td>
		</tr>
		<tr>
		  <td width="100%" class="text">
		    <s:textfield name="increaseAmendAmount" id="increaseAmendAmount" maxLength="15" onChange='callAmountDecimal(this);' onKeyPress='return notAllowCheck(this,event);'></s:textfield> <b>[If either field 32B or 33B is present, field 34B must also be present]</b>
		  </td>
		</tr>
		<tr>
	      <td width="100%" class="textLeft">
	        <s:label value="%{getText('label.707.currency')}"></s:label> <span class="optional">[Optional]</span>
	      </td>
	    </tr>
	    <tr>
	      <td width="100%" class="text">
	      		<s:select list="currCodeDropDown" cssClass="txtField" name="lcCurrency" id="lcCurrency" value="lcCurrency"  headerKey="" headerValue="Select Currency" ></s:select></td>
	      </td>
	    </tr>
		<tr>
		  <td width="100%" class="textLeft">
		    <s:label value="%{getText('label.707.DecreaseofLCAmount')}"></s:label> <span class="optional">[Optional]</span>
		  </td>
		</tr>
		<tr>
		  <td width="100%" class="text">
		    <s:textfield name="decreaseAmendAmount" id="decreaseAmendAmount" maxLength="15" onChange='callAmountDecimal(this);' onKeyPress='return notAllowCheck(this,event);'></s:textfield>
		  </td>
		</tr>
		<tr>
	      <td width="100%" class="textLeft">
	         <s:label value="%{getText('label.707.CUrrency')}"></s:label> <span class="optional">[Optional]</span>
	      </td>
	    </tr>
	    <tr>
	      <td width="100%" class="text">
	     		<s:select list="currCodeDropDown" cssClass="txtField" name="currency" id="currency" value="currency"  headerKey="" headerValue="Select Currency" ></s:select><b>[If field 34B is present, either field 32B or 33B must also be present]</b>
	      </td>
	    </tr>
	    <tr>
		   <td width="100%" class="textLeft">
		      <s:label	value="%{getText('label.707.NewLCAmount')}"></s:label> <span class="optional">[Optional]</span>
		   </td>
		</tr>
		<tr>
		   <td width="100%" class="text">
		      <s:textfield name="newLcAmount" id="newLcAmount" maxLength="15" onChange='callAmountDecimal(this);'></s:textfield>
		   </td>
		</tr>
		<tr>
		 <td width="100%" class="textLeft">
		   <s:label value="%{getText('label.707.positiveTolerance')}"></s:label> <span class="optional">[Optional]</span>
		 </td>
		</tr>
		<tr>
		  <td width="100%" class="text">
		     <s:textfield name="positiveTolerance" id="positiveTolerance" maxLength="2" ></s:textfield> <b>[Either field 39A or 39B, but not both, may be present]</b>
		  </td>
		<tr>
		  <td width="100%" class="textLeft">
		    <s:label	value="%{getText('label.707.negativeTolerance')}"></s:label> <span class="optional">[Optional]</span>
		  </td>
		</tr>
		<tr>
		  <td width="100%" class="text">
		    <s:textfield name="negativeTolerance" id="negativeTolerance" maxLength="2" ></s:textfield>
		  </td>
		</tr>
		<tr>
		  <td width="100%" class="textLeft">
		    <s:label value="%{getText('label.707.maximumCreditAmount')}"></s:label> <span class="optional">[Optional]</span>
		  </td>
		</tr>
		<tr>
		  <td width="100%" class="text">
		    <s:select list="{'NOT EXCEEDING'}" name="maximumCreditAmount" id="maximumCreditAmount" headerKey="" headerValue="Select Maximum Credit Amount" ></s:select>
		  </td>
		</tr>
		<tr>
		  <td width="100%" class="textLeft">
		    <s:label value="%{getText('label.707.AdditionalAmountsCovered')}"></s:label> <span class="optional">[Optional]</span>
		  </td>
		</tr>
		<tr>
		  <td width="100%" class="text">
		    <s:textarea name="additionalAmountsCovered" id="additionalAmountsCovered" rows="4" cols="35" wrap="HARD" onKeyPress='return maxLength(this,"139","additionalAmountsCovered","35",event);' ></s:textarea>
		  </td>
		</tr>
		<tr>
		  <td width="100%" class="textLeft">
		     <s:label value="%{getText('label.707.placeofDispatch')}"></s:label> <span class="optional">[Optional]</span>
		  </td>
	    </tr>
	    <tr>
		  <td width="100%" class="text">
		     <s:textarea name="initialDispatchPlace" id="initialDispatchPlace" rows="1" cols="65" onKeyPress='return notAllowCheck(this,event);' ></s:textarea>
		  </td>
	    </tr>
	    <tr>
		  <td width="100%" class="textLeft">
		     <s:label value="%{getText('label.707.portofLoading')}"></s:label> <span class="optional">[Optional]</span>
		  </td>
	    </tr>
	    <tr>
		  <td width="100%" class="text">
		    <s:textarea name="goodsLoadingDispatchPlace" id="goodsLoadingDispatchPlace" rows="1" cols="65" onKeyPress='return notAllowCheck(this,event);' ></s:textarea>
		  </td>
	    </tr>
	    <tr>
		  <td width="100%" class="textLeft">
		    <s:label value="%{getText('label.707.portofDischarge')}"></s:label> <span class="optional">[Optional]</span>
		  </td>
	    </tr>
	    <tr>
		  <td width="100%" class="text">
		   <s:textarea name="goodsDestination" id="goodsDestination" rows="1" cols="65" onKeyPress='return notAllowCheck(this,event);'></s:textarea>
		  </td>
	    </tr>
		<tr>
		  <td width="100%" class="textLeft">
		    <s:label value="%{getText('label.707.finalDeliveryPlace')}"></s:label> <span class="optional">[Optional]</span>
		  </td>
		</tr>
		<tr>
		  <td width="100%" class="text">
		    <s:textarea name="finalDeliveryPlace" id="finalDeliveryPlace" rows="1" cols="65" onKeyPress='return notAllowCheck(this,event);'></s:textarea>
		 </td>
		</tr>
		<tr>
		  <td width="100%" class="textLeft">
		    <s:label value="%{getText('label.707.latestDateofShipment')}"></s:label> <span class="optional">[Optional]</span>
		  </td>
		</tr>
		<tr>
		  <td width="100%" class="text">
		    <sx:datetimepicker name="latestDateofShipment" displayFormat="MM/dd/yy" cssClass="txtField" type="date" id="latestDateofShipment"  ></sx:datetimepicker> <b>[Either field 44C or 44D, but not both, may be present]</b>
		  </td>
		</tr>
		<tr>
		  <td width="100%" class="textLeft">
		    <s:label value="%{getText('label.707.ShipmentPeriod')}"></s:label> <span class="optional">[Optional]</span>
		  </td>
		</tr>
		<tr>
		  <td width="100%" class="text">
		    <s:textarea name="shipmentPeriod" id="shipmentPeriod" wrap="HARD" rows="6" cols="65" onKeyPress='return maxLength(this,"389","shipmentPeriod","65",event);'></s:textarea>
		  </td>
		</tr>
		<tr>			
		  <td width="100%" class="textLeft">
		    <s:label value="%{getText('label.707.Narrative')}"></s:label> <span class="optional">[Optional]</span>
		  </td>
		</tr>
		<tr>
		  <td width="100%" class="text">
		    <s:textarea name="Narrative" id="Narrative" rows="35" cols="50" wrap="HARD" onKeyPress='return maxLength(this,"1749","Narrative","35",event);' ></s:textarea>
		  </td>
		</tr>
		<tr>
		  <td width="100%" class="textLeft">
		    <s:label value="%{getText('label.707.SendertoReceiverInformation')}"></s:label> <span class="optional">[Optional]</span>
		  </td>
		</tr>
		<tr>
		  <td width="100%" class="text">
		    <s:textarea name="sendertoReceiverInformation" id="sendertoReceiverInformation" rows="6" cols="35" wrap="HARD" onKeyPress='return maxLength(this,"209","sendertoReceiverInformation","35",event);' ></s:textarea> 
		  </td>
		</tr>
				
	</s:if>
	<s:if test="%{repair!=''}">
		<tr>
		 <td width="100%" class="textLeft">
		   <s:label cssClass="MandatoryField" value="%{getText('label.707.SenderBanksReference')}"></s:label> <span class="MandatoryField">[Mandatory]</span>
		 </td>
	    </tr>
		<tr>
		  <td width="100%" class="text">
		    <s:textfield name="lcNumber" id="lcNumber" maxLength="16" ></s:textfield>
		  </td>
		</tr>
		<tr>
		  <td width="100%" class="textLeft">
		    <s:label cssClass="MandatoryField" value="%{getText('label.707.ReceiverBanksReference')}"></s:label> <span class="MandatoryField">[Mandatory]</span>
		  </td>
		</tr>
		<tr>
	      <td width="100%" class="text">
	        <s:textfield name="receiverBankReference" id="receiverBankReference" maxLength="16"> </s:textfield>
	      </td>
	    </tr>
		<tr>
		  <td width="100%" class="textLeft">
		    <s:label value="%{getText('label.707.IssuingBankReference')}"></s:label> <span class="optional">[Optional]</span>
		  </td>
		</tr>
		<tr>
		  <td width="100%" class="text">
		    <s:textfield name="senderBankReference" id="senderBankReference" maxLength="16" onKeyPress='return notAllowCheck(this,event);'></s:textfield> <b>[If field 23 is present, field 52a must also be present]</b>
		  </td>	
		</tr>
		<tr>
		  <td width="100%" class="textLeft" >
		    <s:label value="%{getText('label.707.IssuingBankPartyIdentifier')}"></s:label> <span class="optional">[Optional]</span>
		  </td>
		</tr>
		<tr>
		  <td width="100%" class="text">
		    <s:select list="{'A','D'}" name="sendersCorrespondentPartyIdentifier" id="sendersCorrespondentPartyIdentifier" headerKey="" headerValue="Select" onChange="javascript:callAdvise();" ></s:select>
		  </td>
		</tr>
		<tr>
		  <td width="100%" class="textLeft">
		    <s:label value="%{getText('label.707.IssuingBankCode')}"></s:label> <span class="optional">[Optional]</span>
		  </td>
		</tr>
		<tr>
		  <td width="100%" class="text">
		    <s:textfield name="applicantBankCode" id="applicantBankCode" maxLength="11"></s:textfield>
		     <input type="button" value="Search IFSC..." onclick="return callSearchIFSC('APPLICANTIFSC','BOTH')" id="issuingBankIFSCSearchBt"class="btn" />
		  </td>
		</tr>
			
		<tr>
		  <td width="100%" class="textLeft">
		    <s:label value="%{getText('label.707.IssuingBankNameandAddress')}"></s:label> <span class="optional">[Optional]</span>
		  </td>
		</tr>
		<tr>
		  <td width="100%" class="text">
		    <s:textarea name="issunigBankNameAndAddress" id="issunigBankNameAndAddress" rows="4" cols="35" wrap="HARD" onKeyPress='return maxLength(this,"139","issunigBankNameAndAddress","35",event);' onkeyup="callNegotidvise()"></s:textarea>
		  </td>
		</tr>
		<tr>
		  <td width="100%" class="textLeft" id="tdDateofIssue">
		    <s:label value="%{getText('label.707.DateofIssue')}"></s:label> <span class="optional">[Optional]</span>
		  </td>
		</tr>
		<tr>
		  <td width="100%" class="text">
		    <sx:datetimepicker name="issueDate" cssClass="txtField" id="issueDate" cssClass="txtField"  displayFormat="MM/dd/yyyy" type="date"></sx:datetimepicker>
		  </td>
		</tr>
		<tr>
		  <td width="100%" class="textLeft">
		    <s:label	value="%{getText('label.707.DateofAmendment')}"></s:label> <span class="optional">[Optional]</span>
		  </td>
		</tr>
		<tr>
		  <td width="100%" class="text">
		    <sx:datetimepicker name="amendmentDate" cssClass="txtField" id="amendmentDate"  displayFormat="MM/dd/yyyy" type="date"  />
		  </td>
		</tr>
		<tr>
		  <td width="100%" class="textLeft">
		    <s:label value="%{getText('label.707.NumberofAmendment')}"></s:label> <span class="optional">[Optional]</span>
		  </td>
		</tr>
		<tr>
		  <td width="100%" class="text">
		    <s:textfield  name="lcAmndmntNo" id="lcAmndmntNo"  maxLength="2"  ></s:textfield>
		  </td>				
		</tr>
		
		<tr>
		  <td width="100%" class="textLeft">
		    <s:label cssClass="MandatoryField" value="%{getText('label.707.beneficiaryNameAddress')}"></s:label> <span class="MandatoryField">[Mandatory]</span>
		  </td>
		</tr>
		<tr>
		  <td width="100%" class="text">
		    <s:textarea name="beneficiaryNameAddress" id="beneficiaryNameAddress" rows="4" cols="35" wrap="HARD" onKeyPress='return maxLength(this,"209","beneficiaryNameAddress","35",event);' ></s:textarea>
		  </td>
		</tr>
		<tr>
		  <td width="100%" class="textLeft">
		    <s:label value="%{getText('label.707.NewDateofExpiry')}"></s:label> <span class="optional">[Optional]</span>
		  </td>
		</tr>
		<tr>
		  <td width="100%" class="text">
		    <sx:datetimepicker name="newAmendExpiryDate" cssClass="txtField" id="newAmendExpiryDate"  displayFormat="MM/dd/yyyy" type="date" /> <b>[At least one of the fields 31E, 32B, 33B, 34B, 39A, 39B, 39C, 44A, 44E, 44F, 44B, 44C, 44D, 79 or 72 must be present]</b>
		  </td>		
		</tr>
		<tr>
	      <td width="100%" class="textLeft">
	       <s:label value="%{getText('label.707.Currency')}"></s:label> <span class="optional">[Optional]</span>
	      </td>
	    </tr>
	    <tr>
	      <td width="100%" class="text">
	        <s:select list="currCodeDropDown" cssClass="txtField" name="msgCurrency" id="msgCurrency" value="msgCurrency"  headerKey="" headerValue="Select Currency" ></s:select>
	        <b>[The currency code in the amount fields 32B, 33B, and 34B must be the same] </b>
	      </td>
	    </tr>
		<tr>		
		  <td width="100%" class="textLeft">
		    <s:label	value="%{getText('label.707.IncreaseofLCAmount')}"></s:label> <span class="optional">[Optional]</span>
		  </td>
		</tr>
		<tr>
		  <td width="100%" class="text">
		    <s:textfield name="increaseAmendAmount" id="increaseAmendAmount" maxLength="15" onChange='callAmountDecimal(this);' onKeyPress='return notAllowCheck(this,event);'></s:textfield> <b>[If either field 32B or 33B is present, field 34B must also be present]</b>
		  </td>
		</tr>
		<tr>
	      <td width="100%" class="textLeft">
	        <s:label value="%{getText('label.707.currency')}"></s:label> <span class="optional">[Optional]</span>
	      </td>
	    </tr>
	    <tr>
	      <td width="100%" class="text">
	       	<s:select list="currCodeDropDown" cssClass="txtField" name="lcCurrency" id="lcCurrency" value="lcCurrency"  headerKey="" headerValue="Select Currency" ></s:select>
	      </td>
	    </tr>
		<tr>
		  <td width="100%" class="textLeft">
		    <s:label value="%{getText('label.707.DecreaseofLCAmount')}"></s:label> <span class="optional">[Optional]</span>
		  </td>
		</tr>
		<tr>
		  <td width="100%" class="text">
		    <s:textfield name="decreaseAmendAmount" id="decreaseAmendAmount" maxLength="15" onChange='callAmountDecimal(this);' onKeyPress='return notAllowCheck(this,event);'></s:textfield>
		  </td>
		</tr>
		<tr>
	      <td width="100%" class="textLeft">
	         <s:label value="%{getText('label.707.CUrrency')}"></s:label> <span class="optional">[Optional]</span>
	      </td>
	    </tr>
	    <tr>
	      <td width="100%" class="text">
	      <s:select list="currCodeDropDown" cssClass="txtField" name="currency" id="currency" value="currency"  headerKey="" headerValue="Select Currency" ></s:select>
	      	<b>[If field 34B is present, either field 32B or 33B must also be present]</b>
	      </td>
	    </tr>
	    <tr>
		   <td width="100%" class="textLeft">
		      <s:label	value="%{getText('label.707.NewLCAmount')}"></s:label> <span class="optional">[Optional]</span>
		   </td>
		</tr>
		<tr>
		   <td width="100%" class="text">
		      <s:textfield name="newLcAmount" id="newLcAmount" maxLength="15" onChange='callAmountDecimal(this);'></s:textfield>
		   </td>
		</tr>
		<tr>
		 <td width="100%" class="textLeft">
		   <s:label value="%{getText('label.707.positiveTolerance')}"></s:label> <span class="optional">[Optional]</span>
		 </td>
		</tr>
		<tr>
		  <td width="100%" class="text">
		     <s:textfield name="positiveTolerance" id="positiveTolerance" maxLength="2" ></s:textfield> <b>[Either field 39A or 39B, but not both, may be present]</b>
		  </td>
		<tr>
		  <td width="100%" class="textLeft">
		    <s:label value="%{getText('label.707.negativeTolerance')}"></s:label> <span class="optional">[Optional]</span>
		  </td>
		</tr>
		<tr>
		  <td width="100%" class="text">
		    <s:textfield name="negativeTolerance" id="negativeTolerance" maxLength="2" ></s:textfield>
		  </td>
		</tr>
		<tr>
		  <td width="100%" class="textLeft">
		    <s:label value="%{getText('label.707.maximumCreditAmount')}"></s:label> <span class="optional">[Optional]</span>
		  </td>
		</tr>
		<tr>
		  <td width="100%" class="text">
		    <s:select list="{'NOT EXCEEDING'}" name="maximumCreditAmount" id="maximumCreditAmount" headerKey="" headerValue="Select Maximum Credit Amount" ></s:select>
		  </td>
		</tr>
		<tr>
		  <td width="100%" class="textLeft">
		    <s:label value="%{getText('label.707.AdditionalAmountsCovered')}"></s:label> <span class="optional">[Optional]</span>
		  </td>
		</tr>
		<tr>
		  <td width="100%" class="text">
		    <s:textarea name="additionalAmountsCovered" id="additionalAmountsCovered" rows="4" cols="35" wrap="HARD" onKeyPress='return maxLength(this,"139","additionalAmountsCovered","35",event);' ></s:textarea>
		  </td>
		</tr>
		<tr>
		  <td width="100%" class="textLeft">
		    <s:label value="%{getText('label.707.finalDeliveryPlace')}"></s:label> <span class="optional">[Optional]</span>
		  </td>
		</tr>
		<tr>
		  <td width="100%" class="text">
		    <s:textarea name="finalDeliveryPlace" id="finalDeliveryPlace" rows="1" cols="65" onKeyPress='return notAllowCheck(this,event);'></s:textarea>
		 </td>
		</tr>
		<tr>
		  <td width="100%" class="textLeft">
		    <s:label value="%{getText('label.707.latestDateofShipment')}"></s:label> <span class="optional">[Optional]</span>
		  </td>
		</tr>
		<tr>
		  <td width="100%" class="text">
		    <sx:datetimepicker name="latestDateofShipment" displayFormat="MM/dd/yy" cssClass="txtField" type="date" id="latestDateofShipment"  ></sx:datetimepicker> <b>[Either field 44C or 44D, but not both, may be present]</b>
		  </td>
		</tr>
		<tr>
		  <td width="100%" class="textLeft">
		    <s:label value="%{getText('label.707.ShipmentPeriod')}"></s:label> <span class="optional">[Optional]</span>
		  </td>
		</tr>
		<tr>
		  <td width="100%" class="text">
		    <s:textarea name="shipmentPeriod" id="shipmentPeriod" wrap="HARD" rows="6" cols="65" onKeyPress='return maxLength(this,"389","shipmentPeriod","65",event);'></s:textarea>
		  </td>
		</tr>
		<tr>			
		  <td width="100%" class="textLeft">
		    <s:label value="%{getText('label.707.Narrative')}"></s:label> <span class="optional">[Optional]</span>
		  </td>
		</tr>
		<tr>
		  <td width="100%" class="text">
		    <s:textarea name="Narrative" id="Narrative" rows="35" cols="50" wrap="HARD" onKeyPress='return maxLength(this,"1749","Narrative","35",event);' ></s:textarea>
		  </td>
		</tr>
		<tr>
		  <td width="100%" class="textLeft">
		    <s:label value="%{getText('label.707.SendertoReceiverInformation')}"></s:label> <span class="optional">[Optional]</span>
		  </td>
		</tr>
		<tr>
		  <td width="100%" class="text">
		    <s:textarea name="sendertoReceiverInformation" id="sendertoReceiverInformation" rows="6" cols="35" wrap="HARD" onKeyPress='return maxLength(this,"209","sendertoReceiverInformation","35",event);' ></s:textarea> 
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
					<input type ="button" value="Submit"  class="btn"  onclick="callCommentPOPUp()"/>
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
					<input type ="button" value="Submit"  class="btn"  onclick="callCommentPOPUp()"/>
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
