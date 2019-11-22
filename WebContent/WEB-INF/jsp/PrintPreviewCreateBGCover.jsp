<%@page import="com.logica.ngph.web.action.*,com.logica.ngph.web.utils.*"%>
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

function callBackButton(){
	document.forms[0].action="getBGCoverAuthorization";
	document.getElementById("txnRef").value=document.getElementById("printPreviewTxnRef").value;
	document.forms[0].submit();
}

function callExpToExcel()
{
	document.getElementById("hiddenTxnRef").value=document.getElementById("hiddenTxnRef").value;
	document.forms[0].action="exportToExcelCreateBGCoverPage";
	document.forms[0].submit();
}
function callPrintpreviewBackButton()
{
	
	document.forms[0].action="pendingAuthorizationOutboundAction";
	document.forms[0].submit();
}
function generatePdfCreateBGCover()
{
	document.forms[0].action="generatePDFCreateBGCover";
	document.forms[0].submit();
}
</script>
<div id="contentPane">
<div id="contentPaneTop"></div>
<div id="contentPaneFluid">
<div id="contentPaneInternal"><span class="crumbs"><a
	href="#">Outbound Payments</a>&nbsp;>&nbsp;Create Bank Guarantee Cover</span></div>
<div id="content">
<h1>Create Bank Guarantee Cover (MT-760 COVER)</h1>
<s:form method="post" id="form">
<s:hidden id="txnRef" name="txnRef"></s:hidden>
<s:hidden id="printPreviewTxnRef" name="printPreviewTxnRef"></s:hidden>
<s:hidden id="hiddenTxnRef" name="hiddenTxnRef"></s:hidden>
<h3> Basic Details </h3>
<br>
	<table width="100%">
		<tr>
			<td width="25%"><s:label value="%{getText('label.messageType')}"></s:label></td>
			<td width="25%">760</td>
			<td width="25%"><s:label value="%{getText('label.subMessageType')}"></s:label></td>
			<td width="25%">COV</td>
		</tr>
		<tr>
			<td width="25%"><s:label value="%{getText('label.MB.IssuingBranchIFSC')}"></s:label></td>
			<td width="25%"><s:property value="issuingBankCode"/></td>
			<td width="25%"><s:label cssClass="MandatoryField" value="%{getText('label.MB.Benefifsc')}"></s:label> <span class="MandatoryField">[MandatoryField]</span></td>
			<td width="25%"><s:property value="advisingBank"/></td>
		</tr>
	</table>
<h3>Message Details </h3>
<br>
	<table width="85%" class="table">
		<tr>
				<td width="5%" class="table-break-word">:7020</td>
				<td width="30%" class="table-break-word"><s:label cssClass="MandatoryField" value="%{getText('label.760COV.pptrxReferenceNo')}"></s:label> <span class="MandatoryField">[MandatoryField]</span></td>
            	<td width="50%" class="table-break-word"><s:property value="bgNumber"/></td>
		</tr>
		<tr>
				<td width="5%" class="table-break-word">:7022</td>
				<td width="30%" class="table-break-word"><s:label value="%{getText('label.760COV.ppbgFormNo')}"></s:label> <span class="optional">[Optional]</span></td>
	     		<td width="50%" class="table-break-word"><s:property value="bgFormNumber"/></td>
		</tr>
		<tr>
				<td width="5%" class="table-break-word">:7024</td>
				<td width="30%" class="table-break-word"><s:label cssClass="MandatoryField" value="%{getText('label.760COV.ppbgType')}"></s:label><span	class="MandatoryField">[MandatoryField]</span></td>
				<td width="50%" class="table-break-word"><s:property value="bgType"/></td>
		</tr>
		<tr>
				<td width="5%" class="table-break-word">:7025</td>
				<td width="30%" class="table-break-word"><s:label cssClass="MandatoryField" value="%{getText('label.760COV.ppcurrency')}"></s:label> <span class="MandatoryField">[MandatoryField]</span></td>
				<td width="50%" class="table-break-word"><s:property value="currency"/></td>
		</tr>
		<tr>
				<td width="5%" class="table-break-word">:7025</td>
				<td width="30%" class="table-break-word"><s:label cssClass="MandatoryField" value="%{getText('label.760COV.ppamtofGuarantee')}"></s:label><span	class="MandatoryField">[MandatoryField]</span></td>
				<td width="50%" class="table-break-word"><s:property value="bgAmount"/></td>
		</tr>
		<tr>
				<td width="5%" class="table-break-word">:7026</td>
				<td width="30%" class="table-break-word"><s:label cssClass="MandatoryField" value="%{getText('label.760COV.ppbgFromDate')}"></s:label> <span class="MandatoryField">[MandatoryField]</span></td>
				<td width="50%" class="table-break-word"><s:date name="bgFromDate" format="MM/dd/yyyy" /></td>
		</tr>
		<tr>
				<td width="5%" class="table-break-word">:7026</td>
				<td width="30%" class="table-break-word"><s:label cssClass="MandatoryField" value="%{getText('label.760COV.ppbgToDate')}"></s:label> <span class="MandatoryField">[MandatoryField]</span></td>
				<td width="50%" class="table-break-word"><s:date name="bgToDate" format="MM/dd/yyyy" /></td>
		</tr>
		<tr>
				<td width="5%" class="table-break-word">:7027</td>
				<td width="30%" class="table-break-word"><s:label cssClass="MandatoryField" value="%{getText('label.760COV.ppbgGuaranteeEffDate')}"></s:label> <span class="MandatoryField">[MandatoryField]</span></td>
				<td width="50%" class="table-break-word"><s:date name="bgEffectiveDate" format="MM/dd/yyyy" /></td>
		</tr>
		<tr>    
				<td width="5%" class="table-break-word">:7029</td>
				<td width="30%" class="table-break-word"><s:label cssClass="MandatoryField" value="%{getText('label.760COV.pplodgementDate')}"></s:label> <span class="MandatoryField">[MandatoryField]</span></td>
				<td width="50%" class="table-break-word"><s:date name="bgLodgementEndDate" format="MM/dd/yyyy" /></td>
		</tr>
		<tr>		
				<td width="5%" class="table-break-word">:7030</td>
				<td width="30%" class="table-break-word"><s:label value="%{getText('label.760COV.pplodgmentClaimPlace')}"></s:label> <span class="optional">[Optional]</span></td>
				<td width="50%" class="table-break-word"><s:property value="bgLodgementPalce"/></td>
		</tr>
		<tr>
				<td width="5%" class="table-break-word">:7031</td>
				<td width="30%" class="table-break-word"><s:label cssClass="MandatoryField" value="%{getText('label.760COV.ppissuingBranchIFSC')}"></s:label> <span class="MandatoryField">[MandatoryField]</span></td>
				<td width="50%" class="table-break-word"><s:property value="bgIssuingBankCode" /></td>
		</tr>
		<tr>		
				<td width="5%" class="table-break-word">:7032</td>
				<td width="30%" class="table-break-word"><s:label cssClass="MandatoryField" value="%{getText('label.760COV.ppissuingBankNameandAddress')}"></s:label> <span class="MandatoryField">[MandatoryField]</span></td>
				<td width="50%" class="table-break-word1"><s:property value="issunigBankNameAndAddress" /></td>
		</tr>
		<tr>    
				<td width="5%" class="table-break-word">:7033</td>
				<td width="30%" class="table-break-word"><s:label cssClass="MandatoryField" value="%{getText('label.760COV.ppapplicantNameAddress')}"></s:label><span	class="MandatoryField">[MandatoryField]</span></td>
				<td width="50%" class="table-break-word1"><s:property value="bgApplicentNameAndDetails" /></td>
		</tr>
		<tr>
				<td width="5%" class="table-break-word">:7034</td>
				<td width="30%" class="table-break-word"><s:label cssClass="MandatoryField" value="%{getText('label.760COV.ppbenefibrname')}"></s:label> <span class="MandatoryField">[MandatoryField]</span></td>
				<td width="50%" class="table-break-word1"><s:property value="beneficiaryBankNameAndAddress" /></td>
		</tr>
		<tr>
				<td width="5%" class="table-break-word">:7035</td>
				<td width="30%" class="table-break-word"><s:label cssClass="MandatoryField" value="%{getText('label.760COV.ppbenefifsc')}"></s:label> <span class="MandatoryField">[MandatoryField]</span></td>
				<td width="50%" class="table-break-word"><s:property value="beneficiaryBankCode" /></td>
		</tr>
		<tr>		
				<td width="5%" class="table-break-word">:7036</td>
				<td width="30%" class="table-break-word"><s:label cssClass="MandatoryField" value="%{getText('label.760COV.ppbeneficiaryNameAddress')}"></s:label> <span class="MandatoryField">[MandatoryField]</span></td>
				<td width="50%" class="table-break-word1"><s:property value="beneficiaryNameAndDetails" /></td>
		</tr>
		<tr>		
				<td width="5%" class="table-break-word">:7037</td>
				<td width="30%" class="table-break-word"><s:label value="%{getText('label.760COV.ppsenderToReceiverInformation')}"></s:label> <span class="optional">[Optional]</span></td>
				<td width="50%" class="table-break-word1"><s:property value="senderToReciverInformation" /></td>
		</tr>
		<tr>		
           		 <td width="5%" class="table-break-word">:7038</td>
           		 <td width="30%" class="table-break-word"><s:label value="%{getText('label.760COV.pppurposeofGuarantee')}"></s:label> <span class="optional">[Optional]</span></td>
				 <td width="50%" class="table-break-word1"><s:property value="bgPurpose" /></td>
		</tr>
		<tr>			
				<td width="5%" class="table-break-word">:7039</td>
				<td width="30%" class="table-break-word"><s:label value="%{getText('label.760COV.ppdescriptionoftheunderlinedcontract')}"></s:label> <span class="optional">[Optional]</span></td>
				<td width="50%" class="table-break-word1"><s:property value="descriptionOfUnderlinedContract" /></td>
		</tr>
		<tr>		
				<td width="5%" class="table-break-word">:7040</td>
				<td width="30%" class="table-break-word"><s:label value="%{getText('label.760COV.ppelectronicallyPaid')}"></s:label> <span class="optional">[Optional]</span></td>
				<td width="50%" class="table-break-word"><s:property value="stampDutyPaid" /></td>
		</tr>
		<tr>
				<td width="5%" class="table-break-word">:7041</td>
				<td width="30%" class="table-break-word"><s:label value="%{getText('label.760COV.ppeStampCertificateNumber')}"></s:label> <span class="optional">[Optional]</span></td>
				<td width="50%" class="table-break-word1"><s:property value="stampCertificateNumber" /></td>
		</tr>
		<tr>
	        	<td width="5%" class="table-break-word">:7042</td>
	        	<td width="30%" class="table-break-word"><s:label value="%{getText('label.760COV.ppeStamdateTime')}"></s:label> <span class="optional">[Optional]</span></td>
				<td width="50%" class="table-break-word"><s:date name="stampDateAndTime" format="MM/dd/yyyy 'at' hh:mm a" /></td>
		</tr>
		<tr>		
				<td width="5%" class="table-break-word">:7043</td>
				<td width="30%" class="table-break-word"><s:label value="%{getText('label.760COV.ppamountpaid')}"></s:label> <span class="optional">[Optional]</span></td>
				<td width="50%" class="table-break-word"><s:property value="bgAmountPaid" /></td>
		</tr>
		<tr>
	       		 <td width="5%" class="table-break-word">:7044</td>
	       		 <td width="30%" class="table-break-word"><s:label value="%{getText('label.760COV.ppstatecode')}"></s:label> <span class="optional">[Optional]</span></td>
 				 <td width="50%" class="table-break-word"><s:property value="bgStateCode" /></td>
		</tr>
		<tr>		
				<td width="5%" class="table-break-word">:7045</td>
				<td width="30%" class="table-break-word"><s:label value="%{getText('label.760COV.pparticleno')}"></s:label> <span class="optional">[Optional]</span></td>
				<td width="50%" class="table-break-word"><s:property value="bgArticleNumber" /></td>
		</tr>
		<tr>    
				<td width="5%" class="table-break-word">:7046</td>
				<td width="30%" class="table-break-word"><s:label value="%{getText('label.760COV.ppdatetofpayment')}"></s:label> <span class="optional">[Optional]</span></td>
				<td width="50%" class="table-break-word"><s:date name="bgPaymentDate" format="MM/dd/yyyy" /></td>
		</tr>
		<tr>		
	        	<td width="5%" class="table-break-word">:7047</td>
	        	<td width="30%" class="table-break-word"><s:label value="%{getText('label.760COV.ppplaceofpay')}"></s:label> <span class="optional">[Optional]</span></td>
				<td width="50%" class="table-break-word"><s:property value="bgPaymentPlace" /></td>
		</tr>
		<tr>
	        	<td width="5%" class="table-break-word">:7048</td>
	        	<td width="30%" class="table-break-word"><s:label value="%{getText('label.760COV.ppebginDematForm')}"></s:label> <span class="optional">[Optional]</span></td>
				<td width="50%" class="table-break-word"><s:property value="bgHeldDematForm" /></td>
		</tr>
		<tr>
	       		 <td width="5%" class="table-break-word">:7050</td>
	       		 <td width="30%" class="table-break-word"><s:label value="%{getText('label.760COV.ppcustodianServiceProvider')}"></s:label> <span class="optional">[Optional]</span></td>
				 <td width="50%" class="table-break-word"><s:property value="custodianServiceProvider" /></td>
		</tr>
		<tr>		
				<td width="5%" class="table-break-word">:7049</td>
				<td width="30%" class="table-break-word"><s:label	value="%{getText('label.760COV.ppdemataccno')}"></s:label> <span class="optional">[Optional]</span></td>
				<td width="50%" class="table-break-word"><s:property value="dematAccNumber" /></td>
		</tr>
	</table>	
<div class="clearfix"></div>
	<table width="75%" cellpadding="0" cellspacing="0" border="0">
		<tr>
			<td align="lesf">
				<input type="button" value="Export Excel" onclick="callExpToExcel()">
				<input type="button" value="Print" onclick="generatePdfCreateBGCover()">
				<s:if test="%{validUserToApprove}">
					<input type="button" value="Back" onclick="callBackButton()">
				</s:if>
				<s:if test="%{!validUserToApprove}">
					<input type="button" value="Back" onclick="callPrintpreviewBackButton()">
				</s:if>
			</td>
		</tr>
	</table>
</s:form>
<div class="clearfix"></div>

</div>
</div>
</div>
<div id="contentPaneBottom"></div>