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

function callSubmit(saveAction)
{
	var aa = document.getElementById('form');
	for ( var i = 0; i < aa.length; i++) {
		aa.elements[i].disabled = false;
		}
	document.getElementById("saveAction").value = saveAction;
	document.forms[0].action="saveAdviceLcPayment";
	document.forms[0].submit();
}
function callFetchLCDetails()
{
	var lcNumber= document.getElementById("lcNumber").value;

	if(lcNumber==""){
		window.open(
				"<s:url action='searchAdvicePaymentLCno' windowState='EXCLUSIVE' />",
				'mywindow', 'top=50,left=250,width=750,height=410');
	}
	else
		{
		callShowGrid();
		}
		
	}

function callShowGrid()
{
	document.forms[0].action="displayLCAdvPayData";
	document.forms[0].submit();
}


function callSenderVal() {
    
    if (document.getElementById("sendersCorrespondentPartyIdentifier").value == "A") {
          document.getElementById("senderCorrespontAcount").disabled = false;
          document.getElementById("sendersCorrespondentCode").disabled = false;
          document.getElementById("sendersCorrespondentLocation").disabled = true;
          document.getElementById("sendersCorrespondentNameandAddress").disabled = true;
          
    } else if (document.getElementById("sendersCorrespondentPartyIdentifier").value == "B") {
          document.getElementById("senderCorrespontAcount").disabled = false;
          document.getElementById("sendersCorrespondentCode").disabled = true;
          document.getElementById("sendersCorrespondentLocation").disabled = false;
          document.getElementById("sendersCorrespondentNameandAddress").disabled = true;
          
    } else if (document.getElementById("sendersCorrespondentPartyIdentifier").value == "D") {
          document.getElementById("senderCorrespontAcount").disabled = false;
          document.getElementById("sendersCorrespondentCode").disabled = true;
          document.getElementById("sendersCorrespondentLocation").disabled = true;
          document.getElementById("sendersCorrespondentNameandAddress").disabled = false;
    } else {
          document.getElementById("senderCorrespontAcount").disabled = false;
          document.getElementById("sendersCorrespondentCode").disabled = false;
          document.getElementById("sendersCorrespondentLocation").disabled = false;
          document.getElementById("sendersCorrespondentNameandAddress").disabled = false;
    }
}

function callReceiverVal() {
    
    if (document.getElementById("receiversCorrespondentPartyIdentifier").value == "A") {
          document.getElementById("receiverCorrespontAcount").disabled = false;
          document.getElementById("receiversCorrespondentCode").disabled = false;
          document.getElementById("receiversCorrespondentLocation").disabled = true;
          document.getElementById("receiversCorrespondentNameandAddress").disabled = true;
          
    } else if (document.getElementById("receiversCorrespondentPartyIdentifier").value == "B") {
          document.getElementById("receiverCorrespontAcount").disabled = false;
          document.getElementById("receiversCorrespondentCode").disabled = true;
          document.getElementById("receiversCorrespondentLocation").disabled = false;
          document.getElementById("receiversCorrespondentNameandAddress").disabled = true;
          
    } else if (document.getElementById("receiversCorrespondentPartyIdentifier").value == "D") {
          document.getElementById("receiverCorrespontAcount").disabled = false;
          document.getElementById("receiversCorrespondentCode").disabled = true;
          document.getElementById("receiversCorrespondentLocation").disabled = true;
          document.getElementById("receiversCorrespondentNameandAddress").disabled = false;
    } else {
          document.getElementById("receiverCorrespontAcount").disabled = false;
          document.getElementById("receiversCorrespondentCode").disabled = false;
          document.getElementById("receiversCorrespondentLocation").disabled = false;
          document.getElementById("receiversCorrespondentNameandAddress").disabled = false;
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
	//			"<s:url action='showRepairLetterOFCreditPopup' windowState='EXCLUSIVE' />",
	//			'mywindow', 'top=500,left=250,width=750,height=410');
	//		}else{
					callSubmitForScreen();
	//			}
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
	document.forms[0].action="getAdvicePayment";
	document.forms[0].submit();
}

function callSearchIFSC(Action,flag) {

	document.getElementById("searchAction").value = Action;
	document.getElementById("IFSCFLAG").value = flag;
	window.open(
		"<s:url action='showIFSCScreen' windowState='EXCLUSIVE' />",
		'mywindow', 'top=50,left=250,width=750,height=410');
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
					document.forms[0].action="saveLCTemplate";
					document.forms[0].submit();
				}
		} 
}

function callPrintPreview(){
	document.forms[0].action="printPreviewAdviceLCPayment";
	document.forms[0].submit();
}

function cancel(){
	var isConfirm = confirm("Please confirm");
	if(isConfirm)
		{
			document.forms[0].action="resetAdviceLCPayment";
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
	href="#">Outbound Payments</a>&nbsp;>&nbsp;Letter Of Credit&nbsp;>&nbsp;Advice LC Payment</span></div>
<div id="content">
<h1>Advice LC Payment (MT-756)</h1>
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
		<table width="100%" cellpadding="5" cellspacing="1" border="0" bgcolor="#ffffff">
		
		<s:if test="%{repair!=''}">
				 <tr>
			       <td width="100%" class="textLeft">
			         <s:label cssClass="MandatoryField" value="%{getText('label.756.lc_Number')}"></s:label> <span class="MandatoryField">[Mandatory]</span>
			       </td>
			     </tr>
			      <tr>
			        <td width="100%" class="text">
			          <s:textfield name="lcNumber" id="lcNumber" maxLength="16"></s:textfield>
			        </td>
			      </tr>
				  <tr>
				    <td width="100%" class="textLeft">
				      <s:label cssClass="MandatoryField" value="%{getText('label.756.PresentingBanksReference')}"></s:label> <span class="MandatoryField">[Mandatory]</span>
				    </td>
				 </tr>
				<tr>
				   <td width="100%" class="text">
				     <s:textfield name="presentingBanksReference" id="presentingBanksReference" maxLength="16" onKeyPress='return notAllowCheck(this,event);'></s:textfield>
				   </td>
				</tr>
				<tr>
				   <td width="100%" class="textLeft">
				     <s:label cssClass="MandatoryField" value="%{getText('label.756.lcCurrency')}"></s:label> <span class="MandatoryField">[Mandatory]</span>
				   </td>
				</tr>
				<tr>
				  <td width="100%" class="text">
				    <s:select list="currCodeDropDown" name="claimCurrency" id="claimCurrency"  headerKey="" headerValue="Select Currency Code" cssClass="txtField" ></s:select>  <b>[The currency code in the amount fields 32B and 33A must be the same]</b>
				  </td>
				</tr>
				<tr>
				  <td width="100%" class="textLeft">
				   <s:label	cssClass="MandatoryField" value="%{getText('label.756.TotalAmountClaimed')}"></s:label> <span class="MandatoryField">[Mandatory]</span>
				  </td>
				</tr>
				<tr>
				  <td width="100%" class="text">
				    <s:textfield name="totalAmountClaimed" id="totalAmountClaimed" maxLength="15" onKeyPress='return notAllowCheck(this,event);' onChange='callAmountDecimal(this);'></s:textfield>  
				  </td>
				</tr>
				<tr>
				  <td width="100%" class="textLeft">
				    <s:label cssClass="MandatoryField" value="%{getText('label.756.amountPaidDate')}"></s:label> <span class="MandatoryField">[Mandatory]</span>
				  </td>
				</tr>
				<tr>
				  <td width="100%" class="text">
				    <sx:datetimepicker name="amountPaidDate" cssClass="txtField" id="amountPaidDate" displayFormat="MM/dd/yyyy" type="date" />
				  </td>
				</tr>
				<tr>
				  <td width="100%" class="textLeft">
				    <s:label cssClass="MandatoryField" value="%{getText('label.756.currency')}"></s:label> <span class="MandatoryField">[Mandatory]</span>
				  </td>
				</tr>
				<tr>
				   <td width="100%" class="text">
				    <s:select list="currCodeDropDown" name="currency" id="currency"  headerKey="" headerValue="Select Currency Code" cssClass="txtField" ></s:select>
				   </td>
				</tr>
				<tr>
				   <td width="100%" class="textLeft">
				     <s:label cssClass="MandatoryField" value="%{getText('label.756.PaidAmount')}"></s:label> <span class="MandatoryField">[Mandatory]</span>
				   </td>
				</tr>
				<tr>
				   <td width="100%" class="text">
				     <s:textfield name="paidAmount" id="paidAmount" maxLength="15" onKeyPress='return notAllowCheck(this,event);' onChange='callAmountDecimal(this);'></s:textfield> 
				   </td>
				</tr>
				<tr>
				  <td width="100%" class="textLeft">
				    <s:label value="%{getText('label.756.SendersCorrespondentPartyIdentifier')}"></s:label> <span class="optional">[Optional]</span>
				  </td>
				</tr>
				<tr>
				  <td width="100%" class="text">
				    <s:select list="{'A','B','D'}" name="sendersCorrespondentPartyIdentifier" id="sendersCorrespondentPartyIdentifier" headerKey="" headerValue="Select PID" onChange="javascript:callSenderVal();" ></s:select><a></a><s:textfield name="senderCorrespontAcount" id="senderCorrespontAcount" maxLength="34" onKeyPress='return notAllowCheck(this,event);'></s:textfield> 
				  </td>
				</tr>
				<tr>
				  <td width="100%" class="textLeft">
				    <s:label value="%{getText('label.756.SendersCorrespondentcode')}"></s:label> <span class="optional">[Optional]</span>
				  </td>
				</tr>
				<tr>
				  <td width="100%" class="text">
				    <s:textfield name="sendersCorrespondentCode" id="sendersCorrespondentCode" maxLength="11"  onkeyup="callSenderAdvise()" onKeyPress='return notAllowCheck(this,event);'></s:textfield>
				    <input type="button" value="Search IFSC..."	onclick="callSearchIFSC('SENDERIFSC','PARTY')" id ="senderSearch" class="btn" /> 
				  </td>
				</tr>
				<tr>
				  <td width="100%" class="textLeft">
				    <s:label value="%{getText('label.756.SendersCorrespondentLocation')}"></s:label> <span class="optional">[Optional]</span>
				  </td>
				</tr>
				<tr>
				  <td width="100%" class="text">
				    <s:textfield name="sendersCorrespondentLocation" id="sendersCorrespondentLocation" maxLength="35" onkeyup="callSenderAdvise()" onKeyPress='return notAllowCheck(this,event);' ></s:textfield> 
				  </td>
				</tr>
				<tr>
				  <td width="100%" class="textLeft">
				    <s:label value="%{getText('label.756.SendersCorrespondentNameandAddress')}"></s:label> <span class="optional">[Optional]</span>
				   </td>
				</tr>
				<tr>
				  <td width="100%" class="text">
				    <s:textarea name="sendersCorrespondentNameandAddress" id="sendersCorrespondentNameandAddress" rows="4" cols="35" wrap="HARD" onKeyPress='return maxLength(this,"140","sendersCorrespondentNameandAddress","35",event);' onkeyup="callSenderAdvise()"></s:textarea>
				  </td>
				</tr>
				<tr>
				  <td width="100%" class="textLeft">
				    <s:label value="%{getText('label.756.ReceiversCorrespondentPartyIdentifier')}"></s:label> <span class="optional">[Optional]</span>
				  </td>
				</tr>
				<tr>
				  <td width="100%" class="text">
				    <s:select list="{'A','B','D'}" name="receiversCorrespondentPartyIdentifier" id="receiversCorrespondentPartyIdentifier" headerKey="" headerValue="Select PID" onChange="javascript:callReceiverVal();"></s:select><a>
				     </a><s:textfield name="receiverCorrespontAcount" id="receiverCorrespontAcount" maxLength="34" onKeyPress='return notAllowCheck(this,event);'></s:textfield> 
				  </td>
				</tr>
				<tr>
				  <td width="100%" class="textLeft">
				    <s:label value="%{getText('label.756.ReceiversCorrespondentCode')}"></s:label> <span class="optional">[Optional]</span>
				  </td>
				</tr>
				<tr>
				  <td width="100%" class="text">
				    <s:textfield name="receiversCorrespondentCode" id="receiversCorrespondentCode" maxLength="11" onkeyup="callReceiverAdvise()" onKeyPress='return notAllowCheck(this,event);'></s:textfield>
				     <input type="button" value="Search IFSC..."	onclick="callSearchIFSC('RECEIVERIFSC','PARTY')" id ="receiveSearch" class="btn" /> 
				  </td>
				</tr>
				<tr>
				  <td width="100%" class="textLeft">
				    <s:label value="%{getText('label.756.ReceiversCorrespondentLocation')}"></s:label> <span class="optional">[Optional]</span>
				  </td>
				</tr>
				<tr>
				  <td width="100%" class="text">
				    <s:textfield name="receiversCorrespondentLocation" id="receiversCorrespondentLocation" maxLength="35" onkeyup="callReceiverAdvise()" onKeyPress='return notAllowCheck(this,event);'></s:textfield> 
				  </td>
				</tr>
				<tr>
				  <td width="100%" class="textLeft">
				    <s:label value="%{getText('label.756.ReceiversCorrespondentNameandAddress')}"></s:label> <span class="optional">[Optional]</span>
				  </td>
				</tr>
				<tr>
				   <td width="100%" class="text">
				     <s:textarea name="receiversCorrespondentNameandAddress" id="receiversCorrespondentNameandAddress" rows="4" cols="35" wrap="HARD" onKeyPress='return maxLength(this,"140","receiversCorrespondentNameandAddress","35",event);' onkeyup="callReceiverAdvise()"></s:textarea> 
				   </td>
				</tr>
				<tr>
				   <td width="100%" class="textLeft">
				     <s:label value="%{getText('label.756.SendertoReceiverInformation')}"></s:label> <span class="optional">[Optional]</span>
				   </td>
				</tr>
				<tr>
				   <td width="100%" class="text">
				      <s:textarea name="sendertoReceiverInformation" id="sendertoReceiverInformation" rows="6" wrap="HARD" cols="35" onKeyPress='return maxLength(this,"210","sendertoReceiverInformation","35",event);'  ></s:textarea> 
				   </td>
				</tr>
				
		</s:if>	
    <s:if test="%{repair==''}">
		<tr>
			       <td width="100%" class="textLeft">
			         <s:label cssClass="MandatoryField" value="%{getText('label.756.lc_Number')}"></s:label> <span class="MandatoryField">[Mandatory]</span>
			       </td>
			     </tr>
			      <tr>
			        <td width="100%" class="text">
			          <s:textfield name="lcNumber" id="lcNumber" maxLength="16"></s:textfield>
			        </td>
			      </tr>
				  <tr>
				    <td width="100%" class="textLeft">
				      <s:label cssClass="MandatoryField" value="%{getText('label.756.PresentingBanksReference')}"></s:label> <span class="MandatoryField">[Mandatory]</span>
				    </td>
				 </tr>
				<tr>
				   <td width="100%" class="text">
				     <s:textfield name="presentingBanksReference" id="presentingBanksReference" maxLength="16" onKeyPress='return notAllowCheck(this,event);'></s:textfield>
				   </td>
				</tr>
				<tr>
				   <td width="100%" class="textLeft">
				     <s:label cssClass="MandatoryField" value="%{getText('label.756.lcCurrency')}"></s:label> <span class="MandatoryField">[Mandatory]</span>
				   </td>
				</tr>
				<tr>
				  <td width="100%" class="text">
				    <s:select list="currCodeDropDown" name="claimCurrency" id="claimCurrency"  headerKey="" headerValue="Select Currency Code" cssClass="txtField" ></s:select>  <b>[The currency code in the amount fields 32B and 33A must be the same]</b>
				  </td>
				</tr>
				<tr>
				  <td width="100%" class="textLeft">
				   <s:label	cssClass="MandatoryField" value="%{getText('label.756.TotalAmountClaimed')}"></s:label> <span class="MandatoryField">[Mandatory]</span>
				  </td>
				</tr>
				<tr>
				  <td width="100%" class="text">
				    <s:textfield name="totalAmountClaimed" id="totalAmountClaimed" maxLength="15" onKeyPress='return notAllowCheck(this,event);' onChange='callAmountDecimal(this);'></s:textfield>  
				  </td>
				</tr>
				<tr>
				  <td width="100%" class="textLeft">
				    <s:label cssClass="MandatoryField" value="%{getText('label.756.amountPaidDate')}"></s:label> <span class="MandatoryField">[Mandatory]</span>
				  </td>
				</tr>
				<tr>
				  <td width="100%" class="text">
				    <sx:datetimepicker name="amountPaidDate" cssClass="txtField" id="amountPaidDate" displayFormat="MM/dd/yyyy" type="date" />
				  </td>
				</tr>
				<tr>
				  <td width="100%" class="textLeft">
				    <s:label cssClass="MandatoryField" value="%{getText('label.756.currency')}"></s:label> <span class="MandatoryField">[Mandatory]</span>
				  </td>
				</tr>
				<tr>
				   <td width="100%" class="text">
				     <s:select list="currCodeDropDown" name="currency" id="currency"  headerKey="" headerValue="Select Currency Code" cssClass="txtField" ></s:select>
				   </td>
				</tr>
				<tr>
				   <td width="100%" class="textLeft">
				     <s:label cssClass="MandatoryField" value="%{getText('label.756.PaidAmount')}"></s:label> <span class="MandatoryField">[Mandatory]</span>
				   </td>
				</tr>
				<tr>
				   <td width="100%" class="text">
				     <s:textfield name="paidAmount" id="paidAmount" maxLength="15" onKeyPress='return notAllowCheck(this,event);' onChange='callAmountDecimal(this);'></s:textfield> 
				   </td>
				</tr>
				<tr>
				  <td width="100%" class="textLeft">
				    <s:label value="%{getText('label.756.SendersCorrespondentPartyIdentifier')}"></s:label> <span class="optional">[Optional]</span>
				  </td>
				</tr>
				<tr>
				  <td width="100%" class="text">
				    <s:select list="{'A','B','D'}" name="sendersCorrespondentPartyIdentifier" id="sendersCorrespondentPartyIdentifier" headerKey="" headerValue="Select PID" onChange="javascript:callSenderVal();" ></s:select><a></a><s:textfield name="senderCorrespontAcount" id="senderCorrespontAcount" maxLength="34" onKeyPress='return notAllowCheck(this,event);'></s:textfield> 
				  </td>
				</tr>
				<tr>
				  <td width="100%" class="textLeft">
				    <s:label value="%{getText('label.756.SendersCorrespondentcode')}"></s:label> <span class="optional">[Optional]</span>
				  </td>
				</tr>
				<tr>
				  <td width="100%" class="text">
				    <s:textfield name="sendersCorrespondentCode" id="sendersCorrespondentCode" maxLength="11"  onkeyup="callSenderAdvise()" onKeyPress='return notAllowCheck(this,event);'></s:textfield>
				    <input type="button" value="Search IFSC..."	onclick="callSearchIFSC('SENDERIFSC','PARTY')" id ="senderSearch" class="btn" /> 
				  </td>
				</tr>
				<tr>
				  <td width="100%" class="textLeft">
				    <s:label value="%{getText('label.756.SendersCorrespondentLocation')}"></s:label> <span class="optional">[Optional]</span>
				  </td>
				</tr>
				<tr>
				  <td width="100%" class="text">
				    <s:textfield name="sendersCorrespondentLocation" id="sendersCorrespondentLocation" maxLength="35" onkeyup="callSenderAdvise()" onKeyPress='return notAllowCheck(this,event);' ></s:textfield> 
				  </td>
				</tr>
				<tr>
				  <td width="100%" class="textLeft">
				    <s:label value="%{getText('label.756.SendersCorrespondentNameandAddress')}"></s:label> <span class="optional">[Optional]</span>
				   </td>
				</tr>
				<tr>
				  <td width="100%" class="text">
				    <s:textarea name="sendersCorrespondentNameandAddress" id="sendersCorrespondentNameandAddress" rows="4" cols="35" wrap="HARD" onKeyPress='return maxLength(this,"140","sendersCorrespondentNameandAddress","35",event);' onkeyup="callSenderAdvise()"></s:textarea>
				  </td>
				</tr>
				<tr>
				  <td width="100%" class="textLeft">
				    <s:label value="%{getText('label.756.ReceiversCorrespondentPartyIdentifier')}"></s:label> <span class="optional">[Optional]</span>
				  </td>
				</tr>
				<tr>
				  <td width="100%" class="text">
				    <s:select list="{'A','B','D'}" name="receiversCorrespondentPartyIdentifier" id="receiversCorrespondentPartyIdentifier" headerKey="" headerValue="Select PID" onChange="javascript:callReceiverVal();"></s:select><a>
				     </a><s:textfield name="receiverCorrespontAcount" id="receiverCorrespontAcount" maxLength="34" onKeyPress='return notAllowCheck(this,event);'></s:textfield> 
				  </td>
				</tr>
				<tr>
				  <td width="100%" class="textLeft">
				    <s:label value="%{getText('label.756.ReceiversCorrespondentCode')}"></s:label> <span class="optional">[Optional]</span>
				  </td>
				</tr>
				<tr>
				  <td width="100%" class="text">
				    <s:textfield name="receiversCorrespondentCode" id="receiversCorrespondentCode" maxLength="11" onkeyup="callReceiverAdvise()" onKeyPress='return notAllowCheck(this,event);'></s:textfield>
				     <input type="button" value="Search IFSC..."	onclick="callSearchIFSC('RECEIVERIFSC','PARTY')" id ="receiveSearch" class="btn" /> 
				  </td>
				</tr>
				<tr>
				  <td width="100%" class="textLeft">
				    <s:label value="%{getText('label.756.ReceiversCorrespondentLocation')}"></s:label> <span class="optional">[Optional]</span>
				  </td>
				</tr>
				<tr>
				  <td width="100%" class="text">
				    <s:textfield name="receiversCorrespondentLocation" id="receiversCorrespondentLocation" maxLength="35" onkeyup="callReceiverAdvise()" onKeyPress='return notAllowCheck(this,event);'></s:textfield> 
				  </td>
				</tr>
				<tr>
				  <td width="100%" class="textLeft">
				    <s:label value="%{getText('label.756.ReceiversCorrespondentNameandAddress')}"></s:label> <span class="optional">[Optional]</span>
				  </td>
				</tr>
				<tr>
				   <td width="100%" class="text">
				     <s:textarea name="receiversCorrespondentNameandAddress" id="receiversCorrespondentNameandAddress" rows="4" cols="35" wrap="HARD" onKeyPress='return maxLength(this,"140","receiversCorrespondentNameandAddress","35",event);' onkeyup="callReceiverAdvise()"></s:textarea> 
				   </td>
				</tr>
				<tr>
				   <td width="100%" class="textLeft">
				     <s:label value="%{getText('label.756.SendertoReceiverInformation')}"></s:label> <span class="optional">[Optional]</span>
				   </td>
				</tr>
				<tr>
				   <td width="100%" class="text">
				      <s:textarea name="sendertoReceiverInformation" id="sendertoReceiverInformation" rows="6" wrap="HARD" cols="35" onKeyPress='return maxLength(this,"210","sendertoReceiverInformation","35",event);'  ></s:textarea> 
				   </td>
				</tr>
				
		</s:if>	
		
		</table>
		</s:div> <!-- Group Two Div Ends --></div>
		</div>
		</div>
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
					document.getElementById("amountPaidDate").disabled = true;
					
				}
			
		}
		else
		{
			for ( var i = 0; i < aa.length-6; i++) 
			{
				aa.elements[i].enabled = true;
				document.getElementById("amountPaidDate").enabled = true;
				
			}
		}
}
</script>
