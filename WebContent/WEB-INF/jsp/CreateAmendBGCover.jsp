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
	document.forms[0].action="saveAmdCoverBGAction";
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
 
function checkOption() {
	 
	if(document.getElementById("eStampDutyPaid").value == "Y") 
	{ 
        document.getElementById("bgAmountPaid").disabled = false;
        document.getElementById("bgStateCode").disabled = false;
        document.getElementById("bgArticleNumber").disabled = false;  
        document.getElementById("bgPaymentDate").disabled = false;      
    }
    else 
	{
        document.getElementById("bgAmountPaid").disabled = true;
        document.getElementById("bgStateCode").disabled = true;
        document.getElementById("bgArticleNumber").disabled = true;      
    }
}
function checkDematOption() 
{ 
    if(document.getElementById("eBgHeldDematForm").value == "Y") 
	{
        document.getElementById("dematAccNumber").disabled = false;       
    }
    else 
    {
        document.getElementById("dematAccNumber").disabled = true;    
    }
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
					document.forms[0].action="saveAmendBGCovTemplate";
					document.forms[0].submit();
				}
		} 
}
function callPrintPreview(){
	document.forms[0].action="printPreviewAmendBGCover";
	document.forms[0].submit();
}
function cancel()
{
	var isConfirm = confirm("Please confirm");
	if(isConfirm)
		{
			document.forms[0].action = "resetAmdBGCover";
			document.forms[0].submit();
		}

}
</script>
<sx:head parseContent="true" />
<s:head />
<div id="contentPane">
<div id="contentPaneTop"></div>
<div id="contentPaneFluid">
<div id="contentPaneInternal"><span class="crumbs"><a
	href="#">Outbound Payments</a>&nbsp;>&nbsp;Create Amend BG Cover</span></div>
<div id="content">
<h1>Create Amend BG Cover (MT-767 COVER)</h1>
<s:form method="post" id="form">
<s:hidden id="searchAction" name="searchAction"></s:hidden>	
<s:hidden name="IFSCFLAG" id="IFSCFLAG" ></s:hidden>
<s:hidden id="msgRef" name="msgRef"></s:hidden>
<s:hidden name="repair" id="repair"></s:hidden>
<s:hidden id="saveAction" name="saveAction"></s:hidden>
<s:hidden id="msgHost" name="msgHost"> </s:hidden>
<s:hidden name="tempName" id="tempName"></s:hidden>
<s:hidden id="seqNo" name="seqNo"> </s:hidden>

<s:hidden id="validUserToApprove" name="validUserToApprove"></s:hidden>

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
		     <s:textfield name="issuingBankCode" id="issuingBankCode"></s:textfield> 
		     <input type="button" value="Search IFSC..." onclick="return callSearchIFSC('ISSUEBANKTHOUGHIFSC','SENDERBANKIFSC')" id="btnadviceThrough"class="btn" />
		  </td>
		  <td width="20%" class="textLeft">
		     <s:label cssClass="MandatoryField" value="%{getText('label.MB.Benefifsc')}"></s:label><span class="MandatoryField">[Mandatory]</span>
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
		     <s:textfield name="issuingBankCode" id="issuingBankCode"></s:textfield> 
		     <input type="button" value="Search IFSC..." onclick="return callSearchIFSC('ISSUEBANKTHOUGHIFSC','SENDERBANKIFSC')" id="btnadviceThrough"class="btn" />
		  </td>
		  <td width="20%" class="textLeft">
		     <s:label cssClass="MandatoryField" value="%{getText('label.MB.Benefifsc')}"></s:label><span class="MandatoryField">[Mandatory]</span>
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
	<div><!-- Group two Div Starts --> 
	<s:div id="main"	cssClass="dataGrid" style="width:100%;">
	<table width="100%" cellpadding="3" cellspacing="1" border="0"	bgcolor="#ffffff">
	
<s:if test="%{repair!=''}">
	<tr>
			<td width="100%" class="textLeft"><s:label	cssClass="MandatoryField" value="%{getText('label.767COV.trxReferenceNo')}"></s:label> <span class="MandatoryField">[Mandatory]</span></td>
	</tr>	
	<tr>	
			<td width="100%" class="text"><s:textfield name="bgNumber" id="bgNumber" maxLength="16"></s:textfield> </td>
	</tr>		
	<tr>
			<td width="100%" class="textLeft"><s:label cssClass="MandatoryField" value="%{getText('label.767COV.bgrelatedReference')}"></s:label><span class="MandatoryField">[Mandatory]</span></td>
	</tr>
	<tr>		
			<td width="100%" class="text"><s:textfield name="bgRelatedReference" id="bgRelatedReference" maxLength="16"></s:textfield></td>
	</tr>		
	<tr>
	        <td width="100%" class="textLeft"><s:label	cssClass="MandatoryField" value="%{getText('label.767COV.FurtherIdentification')}"></s:label><span class="MandatoryField">[Mandatory]</span></td>
	</tr>
	<tr>        
			<td width="100%" class="text"><s:textfield name="bgFurtherIdentification" id="bgFurtherIdentification" maxLength="16"></s:textfield> </td>
	</tr>
	<tr>
			<td width="100%" class="textLeft"><s:label cssClass="MandatoryField" value="%{getText('label.767COV.AmendmentDate')}"></s:label><span class="MandatoryField">[Mandatory]</span></td>
	</tr>
	<tr>		
			<td width="100%" class="text"><sx:datetimepicker name="bgAmendmentDate" value="%{'today'}" cssClass="txtField" id="bgAmendmentDate"  displayFormat="MM/dd/yyyy" type="date"  /> </td>			
	</tr>	 
	<tr>
			<td width="100%" class="textLeft"><s:label cssClass="MandatoryField" value="%{getText('label.767COV.NumberofAmendment')}"></s:label><span class="MandatoryField">[Mandatory]</span></td>
	</tr>
	<tr>		
			<td width="100%" class="text"><s:textfield  name="bgNoofAmendments" id="bgNoofAmendments"  maxLength="2" ></s:textfield></td>
	</tr>
	<tr>		
			<td width="100%" class="textLeft"><s:label	value="%{getText('label.767COV.DateofAmendment')}"></s:label><span class="optional">[Optional]</span></td>
	</tr>
	<tr>		
			<td width="100%" class="text"><sx:datetimepicker name="bgIssueDate" cssClass="txtField" id="bgIssueDate"  displayFormat="MM/dd/yyyy" type="date" /> </td> 
	</tr>
	<tr>
			<td width="100%" class="textLeft"><s:label	value="%{getText('label.767COV.bgDetails')}"></s:label><span class="optional">[Optional]</span></td>
	</tr>
	<tr>		
			<td width="100%" class="text"><s:textarea rows="150" cols="65" name="bgAmedmentDetails" id="bgAmedmentDetails" onKeyPress='return maxLength(this,"9750");' onpaste='return maxLengthPaste(this,"9750");'></s:textarea> </td>
	</tr>
	<tr>		
			<td width="100%" class="textLeft"><s:label value="%{getText('label.767COV.senderToReceiverInformation')}"></s:label><span class="optional">[Optional]</span></td>
	</tr>
	<tr>		
			<td width="100%" class="text"><s:textarea rows="10" cols="35" name="senderToReciverInformation" id="senderToReciverInformation" onKeyPress='return maxLength(this,"350");' onpaste='return maxLengthPaste(this,"350");'></s:textarea> </td>	
	</tr>
	<tr>
			<td width="100%" class="textLeft"><s:label	cssClass="MandatoryField" value="%{getText('label.767COV.issuingBranchIFSC')}"></s:label><span class="MandatoryField">[Mandatory]</span></td>
	</tr>	
	<tr>	
			<td width="100%" class="text"><s:textfield name="bgIssuingBankCode" id="bgIssuingBankCode" maxLength="11"></s:textfield> <input type="button" value="Search IFSC..." onclick="return callSearchIFSC('BGCOVISSUEBANKTHOUGHIFSC','BOTH')" id="btnadviceThrough"class="btn" /></td>
	</tr>
	<tr>		
			<td width="100%" class="textLeft"><s:label cssClass="MandatoryField" value="%{getText('label.767COV.issuingBankNameandAddress')}"></s:label><span class="MandatoryField">[Mandatory]</span></td>
	</tr>
	<tr>		
			<td width="100%" class="text"><s:textarea name="issunigBankNameAndAddress" id="issunigBankNameAndAddress" rows="6" cols="35" wrap="HARD" onkeyup="callAuthorised()" onKeyPress='return maxLength(this,"210","authorisedAndAddress","35",event);' ></s:textarea> </td>		
	</tr>
	<tr>    
			<td width="100%" class="textLeft"><s:label cssClass="MandatoryField" value="%{getText('label.767COV.applicantNameAddress')}"></s:label><span	class="MandatoryField">[Mandatory]</span></td>
	</tr>
	<tr>
	        <td width="100%" class="text"><s:textarea name="bgApplicentNameAndDetails" id="bgApplicentNameAndDetails" rows="6" cols="35" wrap="HARD" onkeyup="callAuthorised()" onKeyPress='return maxLength(this,"210","authorisedAndAddress","35",event);' ></s:textarea> </td>
	</tr>
	<tr>        
	        <td width="100%" class="textLeft"><s:label	cssClass="MandatoryField" value="%{getText('label.767COV.benefibrname')}"></s:label><span	class="MandatoryField">[Mandatory]</span></td>
	</tr>
	<tr>        
			<td width="100%" class="text"><s:textarea name="beneficiaryNameAndDetails" id="beneficiaryNameAndDetails" rows="6" cols="35" wrap="HARD" onKeyPress='return maxLength(this,"210","beneficiaryNameAddress","35",event);'></s:textarea> </td>
	</tr>
	<tr>
			<td width="100%" class="textLeft"><s:label	cssClass="MandatoryField" value="%{getText('label.767COV.benefifsc')}"></s:label><span class="MandatoryField">[Mandatory]</span></td>
	</tr>
	<tr>		
			<td width="100%" class="text"><s:textfield name="beneficiaryBankCode" id="beneficiaryBankCode" maxLength="11"></s:textfield> <input type="button" value="Search IFSC..." onclick="return callSearchIFSC('BENEFICIARYBANKTHOUGHIFSC','BOTH')" id="btnadviceThrough"class="btn" /></td>
	</tr>
	<tr>		
			<td width="100%" class="textLeft"><s:label cssClass="MandatoryField" value="%{getText('label.767COV.beneficiaryBranchAddress')}"></s:label><span class="MandatoryField">[Mandatory]</span></td>
	</tr>
	<tr>		
			<td width="100%" class="text"><s:textarea  name="beneficiaryBankNameAndAddress" id="beneficiaryBankNameAndAddress" rows="6" cols="35"  onKeyPress='return maxLength(this,"210","beneficiaryNameAddress","35",event);' ></s:textarea> </td>
	</tr>
	<tr>		
			<td width="100%" class="textLeft"><s:label value="%{getText('label.767COV.electronicallyPaid')}"></s:label><span class="optional">[Optional]</span><b> [If Field value of 7040 is "Y" then field 7043,7044,7045,7046 must be]</b></td>
	</tr>
	<tr>		
			<td width="100%" class="text"><s:select list="{'Y','N'}" name="stampDutyPaid" id="stampDutyPaid" headerKey="" headerValue="Select" onChange="javascript:checkOption();" ></s:select></td>
	</tr>
	<tr>    
			<td width="100%" class="textLeft"><s:label value="%{getText('label.767COV.eStampCertificateNumber')}"></s:label><span class="optional">[Optional]</span></td>
	</tr>
	<tr>		
			<td width="100%" class="text"><s:textfield name="stampCertificateNumber" id="stampCertificateNumber" maxLength="20"></s:textfield> </td>
	</tr>
	<tr>
	        <td width="100%" class="textLeft"><s:label value="%{getText('label.767COV.eStamdateTime')}"></s:label><span class="optional">[Optional]</span></td>
	</tr>        
	<tr>
			<td width="100%" class="text"><sx:datetimepicker name="stampDateAndTime" value="" cssClass="txtField" id="stampDateAndTime"  displayFormat="MM/dd/yyyy 'at' hh:mm a" type="date"  /> </td>
	</tr>		
	<tr>
			<td width="100%" class="textLeft"><s:label	value="%{getText('label.767COV.amountpaid')}"></s:label><span class="optional">[Optional]</span></td>
	</tr>
	<tr>		
			<td width="100%" class="text"><s:textfield name="bgAmountPaid" id="bgAmountPaid" maxLength="15" onKeyPress='return notAllowCheck(this,event);'></s:textfield> </td>
	</tr>
	<tr>
	        <td width="100%" class="textLeft"><s:label	value="%{getText('label.767COV.statecode')}"></s:label><span class="optional">[Optional]</span></td>
	</tr>
	<tr>        
			<td width="100%" class="text"><s:textfield name="bgStateCode" id="bgStateCode" maxLength="2" onKeyPress='return notAllowCheck(this,event);'></s:textfield> </td>
	</tr>
	<tr>		
			<td width="100%" class="textLeft"><s:label	value="%{getText('label.767COV.articleno')}"></s:label><span class="optional">[Optional]</span></td>
	</tr>
	<tr>		
			<td width="100%" class="text"><s:textfield name="bgArticleNumber" id="bgArticleNumber" maxLength="35" onKeyPress='return notAllowCheck(this,event);'></s:textfield> </td>
	</tr>
	<tr>    
			<td width="100%" class="textLeft"><s:label value="%{getText('label.767COV.datetofpayment')}"></s:label><span class="optional">[Optional]</span></td>
	</tr>
	<tr>		
			<td width="100%" class="text"><sx:datetimepicker name="bgPaymentDate" value="" cssClass="txtField" id="bgPaymentDate"  displayFormat="MM/dd/yyyy" type="date"  /> </td>
	</tr>
	<tr>		
	        <td width="100%" class="textLeft"><s:label	value="%{getText('label.767COV.placeofpay')}"></s:label><span class="optional">[Optional]</span></td>
	</tr>
	<tr>        
			<td width="100%" class="text"><s:textfield name="bgPaymentPlace" id="bgPaymentPlace" maxLength="35" onKeyPress='return notAllowCheck(this,event);'></s:textfield> </td>
	</tr>
	<tr>
	        <td width="100%" class="textLeft" ><s:label value="%{getText('label.767COV.ebginDematForm')}"></s:label><span class="optional">[Optional]</span></td>
	</tr>
	<tr>        
			<td width="100%" class="text"><s:select list="{'Y','N'}" name="bgHeldDematForm" id="bgHeldDematForm" headerKey="" headerValue="Select" onChange="javascript:checkDematOption();" ></s:select></td>
	</tr>
	<tr>
	        <td width="100%" class="textLeft"><s:label	value="%{getText('label.CustodianServiceProvider')}"></s:label><span class="optional">[Optional]</span></td>
	</tr>
	<tr>        
			<td width="100%" class="text"><s:textfield name="custodianServiceProvider" id="custodianServiceProvider" maxLength="16" onKeyPress='return notAllowCheck(this,event);'></s:textfield> </td>
	</tr>
	<tr>		
			<td width="100%" class="textLeft"><s:label	value="%{getText('label.767COV.demataccno')}"></s:label><span class="optional">[Optional]</span></td>
	</tr>
	<tr>		
			<td width="100%" class="text"><s:textfield name="dematAccNumber" id="dematAccNumber" maxLength="16" onKeyPress='return notAllowCheck(this,event);'></s:textfield> </td>
	</tr>
</s:if>	
<s:if test="%{repair==''}">
	<tr>
			<td width="100%" class="textLeft"><s:label	cssClass="MandatoryField" value="%{getText('label.767COV.trxReferenceNo')}"></s:label> <span class="MandatoryField">[Mandatory]</span></td>
	</tr>	
	<tr>	
			<td width="100%" class="text"><s:textfield name="bgNumber" id="bgNumber" maxLength="16"></s:textfield> </td>
	</tr>		
	<tr>
			<td width="100%" class="textLeft"><s:label cssClass="MandatoryField" value="%{getText('label.767COV.bgrelatedReference')}"></s:label><span class="MandatoryField">[Mandatory]</span></td>
	</tr>
	<tr>		
			<td width="100%" class="text"><s:textfield name="bgRelatedReference" id="bgRelatedReference" maxLength="16"></s:textfield></td>
	</tr>		
	<tr>
	        <td width="100%" class="textLeft"><s:label	cssClass="MandatoryField" value="%{getText('label.767COV.FurtherIdentification')}"></s:label><span class="MandatoryField">[Mandatory]</span></td>
	</tr>
	<tr>        
			<td width="100%" class="text"><s:textfield name="bgFurtherIdentification" id="bgFurtherIdentification" maxLength="16"></s:textfield> </td>
	</tr>
	<tr>
			<td width="100%" class="textLeft"><s:label cssClass="MandatoryField" value="%{getText('label.767COV.AmendmentDate')}"></s:label><span class="MandatoryField">[Mandatory]</span></td>
	</tr>
	<tr>		
			<td width="100%" class="text"><sx:datetimepicker name="bgAmendmentDate" value="%{'today'}" cssClass="txtField" id="bgAmendmentDate"  displayFormat="MM/dd/yyyy" type="date"  /> </td>			
	</tr>	 
	<tr>
			<td width="100%" class="textLeft"><s:label cssClass="MandatoryField" value="%{getText('label.767COV.NumberofAmendment')}"></s:label><span class="MandatoryField">[Mandatory]</span></td>
	</tr>
	<tr>		
			<td width="100%" class="text"><s:textfield  name="bgNoofAmendments" id="bgNoofAmendments"  maxLength="2" ></s:textfield></td>
	</tr>
	<tr>		
			<td width="100%" class="textLeft"><s:label	value="%{getText('label.767COV.DateofAmendment')}"></s:label><span class="optional">[Optional]</span></td>
	</tr>
	<tr>		
			<td width="100%" class="text"><sx:datetimepicker name="bgIssueDate" cssClass="txtField" id="bgIssueDate"  displayFormat="MM/dd/yyyy" type="date" /> </td> 
	</tr>
	<tr>
			<td width="100%" class="textLeft"><s:label	value="%{getText('label.767COV.bgDetails')}"></s:label><span class="optional">[Optional]</span></td>
	</tr>
	<tr>		
			<td width="100%" class="text"><s:textarea rows="15" cols="65" name="bgAmedmentDetails" id="bgAmedmentDetails" onKeyPress='return maxLength(this,"9750");' onpaste='return maxLengthPaste(this,"9750");'></s:textarea> </td>
	</tr>
	<tr>		
			<td width="100%" class="textLeft"><s:label value="%{getText('label.767COV.senderToReceiverInformation')}"></s:label><span class="optional">[Optional]</span></td>
	</tr>
	<tr>		
			<td width="100%" class="text"><s:textarea rows="10" cols="35" name="senderToReciverInformation" id="senderToReciverInformation" onKeyPress='return maxLength(this,"350");' onpaste='return maxLengthPaste(this,"350");'></s:textarea> </td>	
	</tr>
	<tr>
			<td width="100%" class="textLeft"><s:label	cssClass="MandatoryField" value="%{getText('label.767COV.issuingBranchIFSC')}"></s:label><span class="MandatoryField">[Mandatory]</span></td>
	</tr>	
	<tr>	
			<td width="100%" class="text"><s:textfield name="bgIssuingBankCode" id="bgIssuingBankCode" maxLength="11"></s:textfield> <input type="button" value="Search IFSC..." onclick="return callSearchIFSC('BGCOVISSUEBANKTHOUGHIFSC','BOTH')" id="btnadviceThrough"class="btn" /></td>
	</tr>
	<tr>		
			<td width="100%" class="textLeft"><s:label cssClass="MandatoryField" value="%{getText('label.767COV.issuingBankNameandAddress')}"></s:label><span class="MandatoryField">[Mandatory]</span></td>
	</tr>
	<tr>		
			<td width="100%" class="text"><s:textarea name="issunigBankNameAndAddress" id="issunigBankNameAndAddress" rows="6" cols="35" wrap="HARD" onkeyup="callAuthorised()" onKeyPress='return maxLength(this,"210","authorisedAndAddress","35",event);' ></s:textarea> </td>		
	</tr>
	<tr>    
			<td width="100%" class="textLeft"><s:label cssClass="MandatoryField" value="%{getText('label.767COV.applicantNameAddress')}"></s:label><span	class="MandatoryField">[Mandatory]</span></td>
	</tr>
	<tr>
	        <td width="100%" class="text"><s:textarea name="bgApplicentNameAndDetails" id="bgApplicentNameAndDetails" rows="6" cols="35" wrap="HARD" onkeyup="callAuthorised()" onKeyPress='return maxLength(this,"210","authorisedAndAddress","35",event);' ></s:textarea> </td>
	</tr>
	<tr>        
	        <td width="100%" class="textLeft"><s:label	cssClass="MandatoryField" value="%{getText('label.767COV.benefibrname')}"></s:label><span	class="MandatoryField">[Mandatory]</span></td>
	</tr>
	<tr>        
			<td width="100%" class="text"><s:textarea name="beneficiaryNameAndDetails" id="beneficiaryNameAndDetails" rows="6" cols="35" wrap="HARD" onKeyPress='return maxLength(this,"210","beneficiaryNameAddress","35",event);'></s:textarea> </td>
	</tr>
	<tr>
			<td width="100%" class="textLeft"><s:label	cssClass="MandatoryField" value="%{getText('label.767COV.benefifsc')}"></s:label><span class="MandatoryField">[Mandatory]</span></td>
	</tr>
	<tr>		
			<td width="100%" class="text"><s:textfield name="beneficiaryBankCode" id="beneficiaryBankCode" maxLength="11"></s:textfield> <input type="button" value="Search IFSC..." onclick="return callSearchIFSC('BENEFICIARYBANKTHOUGHIFSC','BOTH')" id="btnadviceThrough"class="btn" /></td>
	</tr>
	<tr>		
			<td width="100%" class="textLeft"><s:label cssClass="MandatoryField" value="%{getText('label.767COV.beneficiaryBranchAddress')}"></s:label><span class="MandatoryField">[Mandatory]</span></td>
	</tr>
	<tr>		
			<td width="100%" class="text"><s:textarea  name="beneficiaryBankNameAndAddress" id="beneficiaryBankNameAndAddress" rows="6" cols="35"  onKeyPress='return maxLength(this,"210","beneficiaryNameAddress","35",event);' ></s:textarea> </td>
	</tr>
	<tr>		
			<td width="100%" class="textLeft"><s:label value="%{getText('label.767COV.electronicallyPaid')}"></s:label><span class="optional">[Optional]</span><b> [If Field value of 7040 is "Y" then field 7043,7044,7045,7046 must be]</b></td>
	</tr>
	<tr>		
			<td width="100%" class="text"><s:select list="{'Y','N'}" name="stampDutyPaid" id="stampDutyPaid" headerKey="" headerValue="Select" onChange="javascript:checkOption();" ></s:select></td>
	</tr>
	<tr>    
			<td width="100%" class="textLeft"><s:label value="%{getText('label.767COV.eStampCertificateNumber')}"></s:label><span class="optional">[Optional]</span></td>
	</tr>
	<tr>		
			<td width="100%" class="text"><s:textfield name="stampCertificateNumber" id="stampCertificateNumber" maxLength="20"></s:textfield> </td>
	</tr>
	<tr>
	        <td width="100%" class="textLeft"><s:label value="%{getText('label.767COV.eStamdateTime')}"></s:label><span class="optional">[Optional]</span></td>
	</tr>        
	<tr>
			<td width="100%" class="text"><sx:datetimepicker name="stampDateAndTime" value="" cssClass="txtField" id="stampDateAndTime"  displayFormat="MM/dd/yyyy 'at' hh:mm a" type="date"  /> </td>
	</tr>		
	<tr>
			<td width="100%" class="textLeft"><s:label	value="%{getText('label.767COV.amountpaid')}"></s:label><span class="optional">[Optional]</span></td>
	</tr>
	<tr>		
			<td width="100%" class="text"><s:textfield name="bgAmountPaid" id="bgAmountPaid" maxLength="15" onKeyPress='return notAllowCheck(this,event);'></s:textfield> </td>
	</tr>
	<tr>
	        <td width="100%" class="textLeft"><s:label	value="%{getText('label.767COV.statecode')}"></s:label><span class="optional">[Optional]</span></td>
	</tr>
	<tr>        
			<td width="100%" class="text"><s:textfield name="bgStateCode" id="bgStateCode" maxLength="2" onKeyPress='return notAllowCheck(this,event);'></s:textfield> </td>
	</tr>
	<tr>		
			<td width="100%" class="textLeft"><s:label	value="%{getText('label.767COV.articleno')}"></s:label><span class="optional">[Optional]</span></td>
	</tr>
	<tr>		
			<td width="100%" class="text"><s:textfield name="bgArticleNumber" id="bgArticleNumber" maxLength="35" onKeyPress='return notAllowCheck(this,event);'></s:textfield> </td>
	</tr>
	<tr>    
			<td width="100%" class="textLeft"><s:label value="%{getText('label.767COV.datetofpayment')}"></s:label><span class="optional">[Optional]</span></td>
	</tr>
	<tr>		
			<td width="100%" class="text"><sx:datetimepicker name="bgPaymentDate" value="" cssClass="txtField" id="bgPaymentDate"  displayFormat="MM/dd/yyyy" type="date"  /> </td>
	</tr>
	<tr>		
	        <td width="100%" class="textLeft"><s:label	value="%{getText('label.767COV.placeofpay')}"></s:label><span class="optional">[Optional]</span></td>
	</tr>
	<tr>        
			<td width="100%" class="text"><s:textfield name="bgPaymentPlace" id="bgPaymentPlace" maxLength="35" onKeyPress='return notAllowCheck(this,event);'></s:textfield> </td>
	</tr>
	<tr>
	        <td width="100%" class="textLeft" ><s:label value="%{getText('label.767COV.ebginDematForm')}"></s:label><span class="optional">[Optional]</span></td>
	</tr>
	<tr>        
			<td width="100%" class="text"><s:select list="{'Y','N'}" name="bgHeldDematForm" id="bgHeldDematForm" headerKey="" headerValue="Select" onChange="javascript:checkDematOption();" ></s:select></td>
	</tr>
	<tr>
	        <td width="100%" class="textLeft"><s:label	value="%{getText('label.CustodianServiceProvider')}"></s:label><span class="optional">[Optional]</span></td>
	</tr>
	<tr>        
			<td width="100%" class="text"><s:textfield name="custodianServiceProvider" id="custodianServiceProvider" maxLength="16" onKeyPress='return notAllowCheck(this,event);'></s:textfield> </td>
	</tr>
	<tr>		
			<td width="100%" class="textLeft"><s:label	value="%{getText('label.767COV.demataccno')}"></s:label><span class="optional">[Optional]</span></td>
	</tr>
	<tr>		
			<td width="100%" class="text"><s:textfield name="dematAccNumber" id="dematAccNumber" maxLength="16" onKeyPress='return notAllowCheck(this,event);'></s:textfield> </td>
	</tr>
</s:if>	
	
	
		</table>		
		
	<!--  button panel starts -->
	<div class="clearfix"></div>
	<table width="100%" cellpadding="0" cellspacing="0" border="0">
		<tr>
			<td>
			<s:if test="%{checkForSubmit!='Display_Approve_Reject'}">
					<s:submit value="Submit" action="getAmdCoverBGApproval" cssClass="btn" />
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
					<s:submit value="Submit" action="getAmdCoverBGApproval" cssClass="btn" />
					<input type="reset" value="Cancel" onclick="cancel()" class="btn"  />
					<input type="button" value="Save Template" onclick="SaveTemplate()" class="btn"  />
				</s:if>
			</s:if>
					
				
			</td>
		</tr>
	</table>

<s:hidden id="hiddenTxnRef" name="hiddenTxnRef"></s:hidden>
	
	</s:div> <!-- Group One Div Ends --></div>
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