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
	document.forms[0].action="getLCAdviseofPaymentAuthorization";
	document.getElementById("txnRef").value=document.getElementById("printPreviewTxnRef").value;
	document.forms[0].submit();
}

function callExpToExcel()
{
	
	document.getElementById("hiddenTxnRef").value=document.getElementById("hiddenTxnRef").value;
	document.forms[0].action="exportToExcelAdviceofPaymentPage";
	document.forms[0].submit();
}
function callPrintpreviewBackButton()
{
	
	document.forms[0].action="pendingAuthorizationOutboundAction";
	document.forms[0].submit();
}
function generatePdfAdviceofPayment()
{
	document.forms[0].action="generatePDFAdviceofPayment";
	document.forms[0].submit();
}
</script>
<div id="contentPane">
<div id="contentPaneTop"></div>
<div id="contentPaneFluid">
<div id="contentPaneInternal"><span class="crumbs"><a
	href="#">Outbound Payments</a>&nbsp;>&nbsp;Advice of Payment</span></div>
<div id="content">
<h1>Advice of Payment(MT-754)</h1>
<s:form method="post" id="form">
<s:hidden id="txnRef" name="txnRef"></s:hidden>
<s:hidden id="printPreviewTxnRef" name="printPreviewTxnRef"></s:hidden>
<s:hidden id="hiddenTxnRef" name="hiddenTxnRef"></s:hidden>
<h3>Basic Details </h3>
	<table width="100%">
		<tr>
			<td width="25%">
			   <s:label value="%{getText('label.messageType')}"></s:label>
			</td>
			<td width="25%">754</td>
		
			<td width="25%">
			   <s:label value="%{getText('label.subMessageType')}"></s:label>
			</td>
			<td width="25%">XXX</td>
		</tr>
		<tr>
		    <td width="25%">
		      <s:label	value="%{getText('label.MB.IssuingBranchIFSC')}"></s:label>
		    </td>
			<td width="25%">
			   <s:property value="issuingBankCode"/>
			</td>
			<td width="25%">
			   <s:label	cssClass="MandatoryField" value="%{getText('label.MB.Benefifsc')}"></s:label> <span class="MandatoryField">[MandatoryField]</span>
			 </td>
			<td width="25%">
			   <s:property value="advisingBank"/>
			</td>
		</tr> 
			
	</table>
<h3>Message Details </h3>
	<table width="85%" class="table">
		<tr>
	      <td width="5%" class="table-break-word">:20</td>
	      <td width="30%" class="table-break-word">
	        <s:label cssClass="MandatoryField" value="%{getText('label.754.ppSenderBanksReference')}"></s:label> <span class="MandatoryField">[MandatoryField]</span>
	      </td>
	     <td width="50%" class="table-break-word">
	        <s:property value="lcNumber"/>
	      </td>
	    </tr>
	    <tr>
	      <td width="5%" class="table-break-word">:21</td>
	      <td width="30%" class="table-break-word">
	        <s:label cssClass="MandatoryField" value="%{getText('label.754.pprelatedReferenceNumber')}"></s:label> <span class="MandatoryField">[MandatoryField]</span>
	      </td>
	      <td width="50%" class="table-break-word">
	        <s:property value="relatedReference"/>
	      </td>
	    </tr>
	    <tr>
	      <td width="5%" class="table-break-word">:32a</td>
	      <td width="30%" class="table-break-word">
	        <s:label cssClass="MandatoryField" value="%{getText('label.754.ppPrincipalAmount')}"></s:label> <span class="MandatoryField">[MandatoryField]</span>
	      </td>
	      <td width="50%" class="table-break-word">
	        <s:property value="pricAmount"/>
	      </td>
	    </tr>
	    <tr>
	      <td width="5%" class="table-break-word">:32a</td>
	      <td width="30%" class="table-break-word">
	        <s:label cssClass="MandatoryField" value="%{getText('label.754.ppamountPaidDate')}"></s:label> <span class="MandatoryField">[MandatoryField]</span>
	      </td>
	      <td width="50%" class="table-break-word">
	        <s:date name="amountPaidDate" format="MM/dd/yyyy" />
	      </td>
	    </tr>
	    <tr>
	      <td width="5%" class="table-break-word">:32a</td>
	      <td width="30%" class="table-break-word">
	        <s:label cssClass="MandatoryField" value="%{getText('label.754.ppCurrency')}"></s:label> <span class="MandatoryField">[MandatoryField]</span>
	      </td>
	      <td width="50%" class="table-break-word">
	        <s:property value="msgCurrency"/>
	      </td>
	    </tr>
	    <tr>
	      <td width="5%" class="table-break-word">:32a</td>
	      <td width="30%" class="table-break-word">
	        <s:label cssClass="MandatoryField" value="%{getText('label.754.ppPrincipalAmountClaimed')}"></s:label> <span class="MandatoryField">[MandatoryField]</span>
	      </td>
	     <td width="50%" class="table-break-word">
	        <s:property value="principalAmount"/>
	      </td>
	    </tr>
	    <tr>
	      <td width="5%" class="table-break-word">:33B</td>
	      <td width="30%" class="table-break-word">
	        <s:label value="%{getText('label.754.ppcurrency')}"></s:label> <span class="optional">[Optional]</span>
	      </td>
	      <td width="50%" class="table-break-word">
	        <s:property value="lcCurrency"/>
	      </td>
	    </tr>
	    <tr>
	      <td width="5%" class="table-break-word">:33B</td>
	      <td width="30%" class="table-break-word">
	        <s:label value="%{getText('label.754.ppadditionalAmounts')}"></s:label> <span class="optional">[Optional]</span>
	      </td>
	     <td width="50%" class="table-break-word">
	        <s:property value="additionalAmount"/>
	      </td>
	    </tr>
	    <tr>
	      <td width="5%" class="table-break-word">:71B</td>
	      <td width="30%" class="table-break-word">
	        <s:label value="%{getText('label.754.ppChargesDeducted')}"></s:label> <span class="optional">[Optional]</span>
	      </td>
	      <td width="50%" class="table-break-word1">
	        <s:property value="chargesDeducted"/>
	      </td>
	    </tr>
	    <tr>
	      <td width="5%" class="table-break-word">:73</td>
	      <td width="30%" class="table-break-word">
	        <s:label value="%{getText('label.754.ppChargesAdded')}"></s:label> <span class="optional">[Optional]</span>
	      </td>
	      <td width="50%" class="table-break-word1">
	        <s:property value="chargesAdded"/>
	      </td>
	    </tr>
	    <tr>
	      <td width="5%" class="table-break-word">:34a</td>
	      <td width="30%" class="table-break-word">
	        <s:label value="%{getText('label.754.ppTotalAmountClaimed')}"></s:label> <span class="optional">[Optional]</span>
	      </td>
	     <td width="50%" class="table-break-word">
	       <s:property value="totalAmountClaim"/>
	      </td>
	    </tr>
	    <tr>
	      <td width="5%" class="table-break-word">:34a</td>
	      <td width="30%" class="table-break-word">
	        <s:label value="%{getText('label.754.ppTotalAmountClaimedDate')}"></s:label> <span class="optional">[Optional]</span>
	      </td>
	      <td width="50%" class="table-break-word">
	        <s:date name="totalPaidDate" format="MM/dd/yyyy" />
	      </td>
	    </tr>
	    <tr>
	      <td width="5%" class="table-break-word">:34a</td>
	      <td width="30%" class="table-break-word">
	        <s:label value="%{getText('label.754.ppCUrrency')}"></s:label> <span class="optional">[Optional]</span>
	      </td>
	      <td width="50%" class="table-break-word">
	       <s:property value="currency"/>
	      </td>
	    </tr>
	    <tr>
	      <td width="5%" class="table-break-word">:34a</td>
	      <td width="30%" class="table-break-word">
	        <s:label value="%{getText('label.754.ppAmount')}"></s:label> <span class="optional">[Optional]</span>
	      </td>
	      <td width="50%" class="table-break-word">
	        <s:property value="totalAmount"/>
	      </td>
	    </tr>
	    <tr>
	      <td width="5%" class="table-break-word">:53a</td>
	      <td width="30%" class="table-break-word">
	        <s:label value="%{getText('label.754.ppReimbursingBank')}"></s:label> <span class="optional">[Optional]</span>
	      </td>
	      <td width="50%" class="table-break-word">
	       <s:property value="reimbursingBankpartyidentifier"/> <s:property value="reimbursingBankAcc"/>
	      </td>
	    </tr>
	    <tr>
	      <td width="5%" class="table-break-word">:53a</td>
	      <td width="30%" class="table-break-word">
	        <s:label value="%{getText('label.754.ppReimbursingBankCode')}"></s:label> <span class="optional">[Optional]</span>
	      </td>
	     <td width="50%" class="table-break-word">
	        <s:property value="reimbursingBankCode"/>
	      </td>
	    </tr>
	    <tr>
	      <td width="5%" class="table-break-word">:53a</td>
	      <td width="30%" class="table-break-word">
	        <s:label value="%{getText('label.754.ppReimbursingBankLocation')}"></s:label> <span class="optional">[Optional]</span>
	      </td>
	      <td width="50%" class="table-break-word">
	       <s:property value="reimbersingBankLoc"/>
	      </td>
	    </tr>
	    <tr>
	      <td width="5%" class="table-break-word">:53a</td>
	      <td width="30%" class="table-break-word">
	        <s:label value="%{getText('label.754.ppReimbursingBankNameandAddess')}"></s:label> <span class="optional">[Optional]</span>
	      </td>
	     <td width="50%" class="table-break-word1">
	        <s:property value="reimbursingBankNameandAddress"/>
	      </td>
	    </tr>
	    <tr>
	      <td width="5%" class="table-break-word">:57a</td>
	      <td width="30%" class="table-break-word">
	        <s:label value="%{getText('label.754.ppaccountWithPartyIdentifier1')}"></s:label> <span class="optional">[Optional]</span>
	      </td>
	     <td width="50%" class="table-break-word">
	       <s:property value="adviseThroughBankpartyidentifier"/> <s:property value="adviseThroughBankAcc"/>
	      </td>
	    </tr>
	    <tr>
	      <td width="5%" class="table-break-word">:57a</td>
	      <td width="30%" class="table-break-word">
	        <s:label value="%{getText('label.754.ppaccountWithBank')}"></s:label> <span class="optional">[Optional]</span>
	      </td>
	      <td width="50%" class="table-break-word">
	        <s:property value="adviseThroughBankCode"/>
	      </td>
	    </tr>
	    <tr>
	      <td width="5%" class="table-break-word">:57a</td>
	      <td width="30%" class="table-break-word">
	        <s:label value="%{getText('label.754.ppaccountWithPartyLocation')}"></s:label> <span class="optional">[Optional]</span>
	      </td>
	      <td width="50%" class="table-break-word">
	       <s:property value="accountWithPartyLoc"/>
	      </td>
	    </tr>
	    <tr>
	      <td width="5%" class="table-break-word">:57a</td>
	      <td width="30%" class="table-break-word">
	        <s:label value="%{getText('label.754.ppaccountWithNameAndAddress')}"></s:label> <span class="optional">[Optional]</span>
	      </td>
	      <td width="50%" class="table-break-word1">
	        <s:property value="accountWithPartyNameAndAddress"/>
	      </td>
	    </tr>
	    <tr>
	      <td width="5%" class="table-break-word">:58a</td>
	      <td width="30%" class="table-break-word">
	        <s:label value="%{getText('label.754.ppbeneficiarybank')}"></s:label> <span class="optional">[Optional]</span>
	      </td>
	      <td width="50%" class="table-break-word">
	       <s:property value="beneficiaryBankpartyidentifier"/> <s:property value="beneficiaryBankAcc"/>
	      </td>
	    </tr>
	    <tr>
	      <td width="5%" class="table-break-word">:58a</td>
	      <td width="30%" class="table-break-word">
	        <s:label value="%{getText('label.754.ppBeneficiarybankBank')}"></s:label> <span class="optional">[Optional]</span>
	      </td>
	      <td width="50%" class="table-break-word">
	       <s:property value="beneficiaryBankCode"/>
	      </td>
	    </tr>
	    <tr>
	      <td width="5%" class="table-break-word">:58a</td>
	      <td width="30%" class="table-break-word">
	        <s:label value="%{getText('label.754.ppBeneficiaryBankNameandAddess')}"></s:label> <span class="optional">[Optional]</span>
	      </td>
	      <td width="50%" class="table-break-word1">
	       <s:property value="beneficiaryBankNameAndAddress"/>
	      </td>
	    </tr>
	    <tr>
	      <td width="5%" class="table-break-word">:72</td>
	      <td width="30%" class="table-break-word">
	        <s:label value="%{getText('label.754.ppSendertoReceiverInformation')}"></s:label> <span class="optional">[Optional]</span>
	      </td>
	      <td width="50%" class="table-break-word1">
	       <s:property value="SendertoReceiverInformation"/>
	      </td>
	    </tr>
	    <tr>
	      <td width="5%" class="table-break-word">:77A</td>
	      <td width="30%" class="table-break-word">
	        <s:label value="%{getText('label.754.ppNarrative')}"></s:label> <span class="optional">[Optional]</span>
	      </td>
	      <td width="50%" class="table-break-word1">
	       <s:property value="Narrative"/>
	      </td>
	    </tr>
	</table>	
<div class="clearfix"></div>
	<table width="75%" cellpadding="0" cellspacing="0" border="0">
		<tr>
			<td align="lesf">
				<input type="button" value="Export Excel" onclick="callExpToExcel()">
				<input type="button" value="Print" onclick="generatePdfAdviceofPayment()">
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