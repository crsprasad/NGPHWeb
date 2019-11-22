<%@page import="com.logica.ngph.web.action.*,com.logica.ngph.web.utils.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http//www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>

<link href="SpryAssets/SpryCollapsiblePanel.css" rel="stylesheet"
	type="text/css" />
<script src="SpryAssets/SpryCollapsiblePanel.js" type="text/javascript"></script>
<script type="text/javascript">

function callBackButton(){
	document.forms[0].action="getAmdCoverBGAuthorization";
	document.getElementById("txnRef").value=document.getElementById("printPreviewTxnRef").value;
	document.forms[0].submit();
}

function callExpToExcel()
{
	document.getElementById("hiddenTxnRef").value=document.getElementById("hiddenTxnRef").value;
	document.forms[0].action="exportToExcelAmendBGCovPage";
	document.forms[0].submit();
}
function callPrintpreviewBackButton()
{
	
	document.forms[0].action="pendingAuthorizationOutboundAction";
	document.forms[0].submit();
}
function generatePdfAmendBGCover()
{
	document.forms[0].action="generatePDFAmendBGCover";
	document.forms[0].submit();
}
</script>
<div id="contentPane">
<div id="contentPaneTop"></div>
<div id="contentPaneFluid">
<div id="contentPaneInternal"><span class="crumbs"><a href="#">Outbound Payments</a>&nbsp;>&nbsp;Create Amend BG Cover</span></div>
<div id="content"><h1>Create Amend BG Cover (MT-767 COVER)</h1>
<s:form method="post" id="form">
<h3> Basic Details </h3>
<br>
	<table width="100%">
		<tr>
			<td width="25%"><s:label value="%{getText('label.messageType')}"></s:label></td>
			<td width="25%">767</td>
			<td width="25%"><s:label value="%{getText('label.subMessageType')}"></s:label></td>
			<td width="25%">COV</td>
		</tr>
		<tr>
			<td width="25%"><s:label value="%{getText('label.MB.IssuingBranchIFSC')}"></s:label></td>
			<td width="25%"><s:property value="issuingBankCode"/></td>
			<td width="25%"><s:label cssClass="MandatoryField" value="%{getText('label.MB.Benefifsc')}"></s:label>  <span class="MandatoryField">[MandatoryField]</span></td>
			<td width="25%"><s:property value="advisingBank"/></td>
		</tr>
	</table>
<h3>Message Details </h3>
<br>
	<table width="85%" class="table">
		<tr>
				<td width="5%" class="table-break-word">:7020</td>
				<td width="30%" class="table-break-word"><s:label	cssClass="MandatoryField" value="%{getText('label.767COV.pptrxReferenceNo')}"></s:label>  <span class="MandatoryField">[MandatoryField]</span></td>
				<td width="50%" class="table-break-word"><s:property value="bgNumber"/></td>
		</tr>
		<tr>
				<td width="5%" class="table-break-word">:7021</td>
				<td width="30%" class="table-break-word"><s:label cssClass="MandatoryField" value="%{getText('label.767COV.ppbgrelatedReference')}"></s:label> <span class="MandatoryField">[MandatoryField]</span></td>
				<td width="50%" class="table-break-word"><s:property value="bgRelatedReference"/></td>
		</tr>
		<tr>
	        	<td width="5%" class="table-break-word">:7055</td>
	        	<td width="30%" class="table-break-word"><s:label cssClass="MandatoryField" value="%{getText('label.767COV.ppFurtherIdentification')}"></s:label> <span class="MandatoryField">[MandatoryField]</span></td>
				<td width="50%" class="table-break-word"><s:property value="bgFurtherIdentification"/></td>
		</tr>
		<tr>
				<td width="5%" class="table-break-word">:7056</td>
				<td width="30%" class="table-break-word"><s:label cssClass="MandatoryField" value="%{getText('label.767COV.ppAmendmentDate')}"></s:label> <span class="MandatoryField">[MandatoryField]</span></td>
				<td width="50%" class="table-break-word"><s:date name="bgAmendmentDate" format="MM/dd/yyyy" /></td>
		</tr>
		<tr>
				<td width="5%" class="table-break-word">:7057</td>
				<td width="30%" class="table-break-word"><s:label cssClass="MandatoryField" value="%{getText('label.767COV.ppNumberofAmendment')}"></s:label> <span class="MandatoryField">[MandatoryField]</span></td>
				<td width="50%" class="table-break-word"><s:property value="bgNoofAmendments"/></td>
		</tr>
		<tr>		
				<td width="5%" class="table-break-word">:7058</td>
				<td width="30%" class="table-break-word"><s:label value="%{getText('label.767COV.ppDateofAmendment')}"></s:label> <span class="optional">[Optional]</span></td>
				<td width="50%" class="table-break-word"><s:date name="bgIssueDate" format="MM/dd/yyyy" /></td>
		</tr>
		<tr>
				<td width="5%" class="table-break-word">:7059</td>
				<td width="30%" class="table-break-word"><s:label value="%{getText('label.767COV.ppbgDetails')}"></s:label> <span class="optional">[Optional]</span></td>
				<td width="50%" class="table-break-word1"><s:property value="bgAmedmentDetails"/></td>
		</tr>
		<tr>		
				<td width="5%" class="table-break-word">:7037</td>
				<td width="30%" class="table-break-word"><s:label value="%{getText('label.767COV.ppsenderToReceiverInformation')}"></s:label> <span class="optional">[Optional]</span></td>
				<td width="50%" class="table-break-word1"><s:property value="senderToReciverInformation"/></td>
		</tr>
		<tr>
				<td width="5%" class="table-break-word">:7031</td>
				<td width="30%" class="table-break-word"><s:label cssClass="MandatoryField" value="%{getText('label.767COV.ppissuingBranchIFSC')}"></s:label> <span class="MandatoryField">[MandatoryField]</span></td>
				<td width="50%" class="table-break-word"><s:property value="bgIssuingBankCode"/></td>
		</tr>	
		<tr>		
				<td width="5%" class="table-break-word">:7032</td>
				<td width="30%" class="table-break-word"><s:label cssClass="MandatoryField" value="%{getText('label.767COV.ppissuingBankNameandAddress')}"></s:label> <span class="MandatoryField">[MandatoryField]</span></td>
				<td width="50%" class="table-break-word1"><s:property value="issunigBankNameAndAddress"/></td>
		</tr>
		<tr>    
				<td width="5%" class="table-break-word">:7033</td>
				<td width="30%" class="table-break-word"><s:label cssClass="MandatoryField" value="%{getText('label.767COV.ppapplicantNameAddress')}"></s:label> <span class="MandatoryField">[MandatoryField]</span></td>
				<td width="50%" class="table-break-word1"><s:property value="bgApplicentNameAndDetails"/></td>
		</tr>
		<tr>        
	        	<td width="5%" class="table-break-word">:7034</td>
	        	<td width="30%" class="table-break-word"><s:label cssClass="MandatoryField" value="%{getText('label.767COV.ppbenefibrname')}"></s:label> <span class="MandatoryField">[MandatoryField]</span></td>
				<td width="50%" class="table-break-word1"><s:property value="beneficiaryNameAndDetails"/></td>
		</tr>
		<tr>
				<td width="5%" class="table-break-word">:7035</td>
				<td width="30%" class="table-break-word"><s:label cssClass="MandatoryField" value="%{getText('label.767COV.ppbenefifsc')}"></s:label> <span class="MandatoryField">[MandatoryField]</span></td>
				<td width="50%" class="table-break-word"><s:property value="beneficiaryBankCode"/></td>
		</tr>
		<tr>		
				<td width="5%" class="table-break-word">:7036</td>
				<td width="30%" class="table-break-word"><s:label cssClass="MandatoryField" value="%{getText('label.767COV.ppbeneficiaryBranchAddress')}"></s:label> <span class="MandatoryField">[MandatoryField]</span></td>
				<td width="50%" class="table-break-word1"><s:property value="beneficiaryBankNameAndAddress"/></td>
		</tr>
		<tr>		
				<td width="5%" class="table-break-word">:7040</td>
				<td width="30%" class="table-break-word"><s:label cssClass="MandatoryField" value="%{getText('label.767COV.ppelectronicallyPaid')}"></s:label> <span class="MandatoryField">[MandatoryField]</span> </td>
				<td width="50%" class="table-break-word"><s:property value="stampDutyPaid"/></td>
		</tr>
		<tr>    
				<td width="5%" class="table-break-word">:7041</td>
				<td width="30%" class="table-break-word"><s:label value="%{getText('label.767COV.ppeStampCertificateNumber')}"></s:label> <span class="optional">[Optional]</span></td>
				<td width="50%" class="table-break-word"><s:property value="stampCertificateNumber"/></td>
		</tr>
		<tr>
	        	<td width="5%" class="table-break-word">:7042</td>
	        	<td width="30%" class="table-break-word"><s:label value="%{getText('label.767COV.ppeStamdateTime')}"></s:label> <span class="optional">[Optional]</span></td>
				<td width="50%" class="table-break-word"><s:date name="bgIssueDate" format="MM/dd/yyyy 'at' hhmm a" /></td>
		</tr>
		<tr>
				<td width="5%" class="table-break-word">:7043</td>
				<td width="30%" class="table-break-word"><s:label value="%{getText('label.767COV.ppamountpaid')}"></s:label> <span class="optional">[Optional]</span></td>
				<td width="50%" class="table-break-word"><s:property value="bgAmountPaid"/></td>
		</tr>
		<tr>
	       		 <td width="5%" class="table-break-word">:7044</td>
	       		 <td width="30%" class="table-break-word"><s:label value="%{getText('label.767COV.ppstatecode')}"></s:label> <span class="optional">[Optional]</span></td>
				 <td width="50%" class="table-break-word"><s:property value="bgStateCode"/></td>
		</tr>
		<tr>		
				<td width="5%" class="table-break-word">:7045</td>
				<td width="30%" class="table-break-word"><s:label value="%{getText('label.767COV.pparticleno')}"></s:label> <span class="optional">[Optional]</span></td>
				<td width="50%" class="table-break-word"><s:property value="bgArticleNumber"/></td>
		</tr>
		<tr>    
				<td width="5%" class="table-break-word">:7046</td>
				<td width="30%" class="table-break-word"><s:label value="%{getText('label.767COV.ppdatetofpayment')}"></s:label> <span class="optional">[Optional]</span></td>
				<td width="50%" class="table-break-word"><s:date name="bgIssueDate" format="MM/dd/yyyy" /></td>
		</tr>
		<tr>		
	        	<td width="5%" class="table-break-word">:7047</td>
	        	<td width="30%" class="table-break-word"><s:label value="%{getText('label.767COV.ppplaceofpay')}"></s:label> <span class="optional">[Optional]</span></td>
				<td width="50%" class="table-break-word"><s:property value="bgPaymentPlace"/></td>
		</tr>
		<tr>
	        	<td width="5%" class="table-break-word">:7048</td>
	        	<td width="30%" class="table-break-word"><s:label value="%{getText('label.767COV.ppebginDematForm')}"></s:label> <span class="optional">[Optional]</span></td>
				<td width="50%" class="table-break-word"><s:property value="bgHeldDematForm"/></td>
		</tr>
		<tr>
	        	<td width="5%" class="table-break-word">:7050</td>
	        	<td width="30%" class="table-break-word"><s:label value="%{getText('label.767COV.ppCustodianServiceProvider')}"></s:label> <span class="optional">[Optional]</span></td>
				<td width="50%" class="table-break-word"><s:property value="custodianServiceProvider"/></td>
		</tr>
		<tr>		
				<td width="5%" class="table-break-word">:7049</td>
				<td width="30%" class="table-break-word"><s:label value="%{getText('label.767COV.ppdemataccno')}"></s:label> <span class="optional">[Optional]</span></td>
				<td width="50%" class="table-break-word"><s:property value="dematAccNumber"/></td>
		</tr>
	</table>	
<div class="clearfix"></div>
	<table width="75%" cellpadding="0" cellspacing="0" border="0">
		<tr>
			<td align="lesf">
				<input type="button" value="Export Excel" onclick="callExpToExcel()">
				<input type="button" value="Print" onclick="generatePdfAmendBGCover()">
				<s:if test="%{validUserToApprove}">
					<input type="button" value="Back" onclick="callBackButton()">
				</s:if>
				<s:if test="%{!validUserToApprove}">
					<input type="button" value="Back" onclick="callPrintpreviewBackButton()">
				</s:if>
			</td>
		</tr>
	</table>
	
<s:hidden id="txnRef" name="txnRef"></s:hidden>
<s:hidden id="printPreviewTxnRef" name="printPreviewTxnRef"></s:hidden>
<s:hidden id="hiddenTxnRef" name="hiddenTxnRef"></s:hidden>
</s:form>
<div class="clearfix"></div>

</div>
</div>
</div>
<div id="contentPaneBottom"></div>