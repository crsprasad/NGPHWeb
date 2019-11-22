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
	document.forms[0].action="getFreeFormatAuthorization";
	document.getElementById("txnRef").value=document.getElementById("printPreviewTxnRef").value;
	document.forms[0].submit();
}

function callExpToExcel()
{
	document.getElementById("hiddenTxnRef").value=document.getElementById("hiddenTxnRef").value;
	document.forms[0].action="exportToExcelAdviceofReductionPage";
	document.forms[0].submit();
	}
function callPrintpreviewBackButton()
{
	
	document.forms[0].action="pendingAuthorizationOutboundAction";
	document.forms[0].submit();
}	

function generatePdfAdviceofReduction()
{
	document.forms[0].action="generatePDFAdviceofReduction";
	document.forms[0].submit();
}
</script>
<div id="contentPane">
<div id="contentPaneTop"></div>
<div id="contentPaneFluid">
<div id="contentPaneInternal"><span class="crumbs"><a
	href="#">Outbound Payments</a>&nbsp;>&nbsp;Advice of Reduction</span></div>
<div id="content">
<h1>Advice of Reduction (MT-769)</h1>
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
			<td width="25%">769</td>
		
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
	        <s:label cssClass="MandatoryField" value="%{getText('label.769.ppbgNumber')}"></s:label> <span class="MandatoryField">[MandatoryField]</span>
	      </td>
	    <td width="50%" class="table-break-word">
	        <s:property value="bgNumber"/>
	      </td>
	    </tr>
	    <tr>
	      <td width="5%" class="table-break-word">:21</td>
	      <td width="30%" class="table-break-word">
	        <s:label cssClass="MandatoryField" value="%{getText('label.769.ppbgrelatedReference')}"></s:label> <span class="MandatoryField">[MandatoryField]</span>
	      </td>
	      <td width="50%" class="table-break-word">
	        <s:property value="relatedReference"/>
	      </td>
	    </tr>
	    <tr>
	      <td width="5%" class="table-break-word">:25</td>
	      <td width="30%" class="table-break-word">
	        <s:label value="%{getText('label.769.ppaccountIdentification')}"></s:label> <span class="optional">[Optional]</span>
	      </td>
	     <td width="50%" class="table-break-word">
	        <s:property value="bgAccountIdentification"/>
	      </td>
	    </tr>
	    <tr>
	      <td width="5%" class="table-break-word">:30</td>
	      <td width="30%" class="table-break-word">
	        <s:label value="%{getText('label.769.ppdateofReduction')}"></s:label> <span class="optional">[Optional]</span>
	      </td>
	    <td width="50%" class="table-break-word">
	        <s:date name="reductionDate" format="MM/dd/yyyy" />
	      </td>
	    </tr>
	    <tr>
	      <td width="5%" class="table-break-word">:32a</td>
	      <td width="30%" class="table-break-word">
	        <s:label value="%{getText('label.769.ppchargeAmount')}"></s:label> <span class="optional">[Optional]</span>
	      </td>
	      <td width="50%" class="table-break-word">
	        <s:property value="chargeAmt"/>
	      </td>
	    </tr>
	    <tr>
	      <td width="5%" class="table-break-word">:32a</td>
	      <td width="30%" class="table-break-word">
	        <s:label value="%{getText('label.769.ppchargeAmtCurrency')}"></s:label> <span class="optional">[Optional]</span>
	      </td>
	      <td width="50%" class="table-break-word">
	        <s:property value="bgCurrency"/>
	      </td>
	    </tr>
	    <tr>
	      <td width="5%" class="table-break-word">:32a</td>
	      <td width="30%" class="table-break-word">
	        <s:label value="%{getText('label.769.ppAmount')}"></s:label> <span class="optional">[Optional]</span>
	      </td>
	     <td width="50%" class="table-break-word">
	        <s:property value="bgChargeAmount"/>
	      </td>
	    </tr>
	    <tr>
	      <td width="5%" class="table-break-word">:32a</td>
	      <td width="30%" class="table-break-word">
	        <s:label value="%{getText('label.769.ppchargeDate')}"></s:label> <span class="optional">[Optional]</span>
	      </td>
	      <td width="50%" class="table-break-word">
	        <s:date name="chargeDate" format="MM/dd/yyyy" />
	      </td>
	    </tr>
	     <tr>
	      <td width="5%" class="table-break-word">:33B</td>
	      <td width="30%" class="table-break-word">
	        <s:label value="%{getText('label.769.ppamountReducedAmtCurrency')}"></s:label> <span class="optional">[Optional]</span>
	      </td>
	     <td width="50%" class="table-break-word">
	        <s:property value="reducedCurrency"/>
	      </td>
	    </tr>
	    <tr>
	      <td width="5%" class="table-break-word">:33B</td>
	      <td width="30%" class="table-break-word">
	        <s:label value="%{getText('label.769.ppamountReduced')}"></s:label> <span class="optional">[Optional]</span>
	      </td>
	      <td width="50%" class="table-break-word">
	        <s:property value="reducedAmt" />
	      </td>
	    </tr>
	    <tr>
	      <td width="5%" class="table-break-word">:34B</td>
	      <td width="30%" class="table-break-word">
	        <s:label value="%{getText('label.769.ppoutstandingAmtCurrency')}"></s:label> <span class="optional">[Optional]</span>
	      </td>
	     <td width="50%" class="table-break-word">
	        <s:property value="outstandingCurrency"/>
	      </td>
	    </tr>
	    <tr>
	      <td width="5%" class="table-break-word">:34B</td>
	      <td width="30%" class="table-break-word">
	        <s:label value="%{getText('label.769.ppoutstandingAmt')}"></s:label> <span class="optional">[Optional]</span>
	      </td>
	     <td width="50%" class="table-break-word">
	        <s:property value="outstandingAmt" />
	      </td>
	    </tr>
	    <tr>
	      <td width="5%" class="table-break-word">:39C</td>
	      <td width="30%" class="table-break-word">
	        <s:label value="%{getText('label.769.ppamountSpecification')}"></s:label> <span class="optional">[Optional]</span>
	      </td>
	      <td width="50%" class="table-break-word1">
	        <s:property value="amountSpecification"/>
	      </td>
	    </tr>
	    <tr>
	      <td width="5%" class="table-break-word">:57a</td>
	      <td width="30%" class="table-break-word">
	        <s:label value="%{getText('label.769.ppaccountWithBank')}"></s:label> <span class="optional">[Optional]</span>
	      </td>
	     <td width="50%" class="table-break-word">
	       <s:property value="accountWithBank" /> <s:property value="adviseThroughBankAcc" />
	      </td>
	    </tr>
	    <tr>
	      <td width="5%" class="table-break-word">:57a</td>
	      <td width="30%" class="table-break-word">
	        <s:label value="%{getText('label.769.ppaccountWithPartyIFSC')}"></s:label> <span class="optional">[Optional]</span>
	      </td>
	      <td width="50%" class="table-break-word">
	        <s:property value="authorisedBankCode"/>
	      </td>
	    </tr>
	    <tr>
	      <td width="5%" class="table-break-word">:57a</td>
	      <td width="30%" class="table-break-word">
	        <s:label value="%{getText('label.769.ppaccountWithPartyLocation')}"></s:label> <span class="optional">[Optional]</span>
	      </td>
	     <td width="50%" class="table-break-word">
	       <s:property value="accountWithPartyLocation" />
	      </td>
	    </tr>
	     <tr>
	      <td width="5%" class="table-break-word">:57a</td>
	      <td width="30%" class="table-break-word">
	        <s:label value="%{getText('label.769.ppaccountWithNameAndAddress')}"></s:label> <span class="optional">[Optional]</span>
	      </td>
	      <td width="50%" class="table-break-word1">
	        <s:property value="bgAccountWithNameandAddress"/>
	      </td>
	    </tr>
	    <tr>
	      <td width="5%" class="table-break-word">:71B</td>
	      <td width="30%" class="table-break-word">
	        <s:label value="%{getText('label.769.ppchargesDetails')}"></s:label> <span class="optional">[Optional]</span>
	      </td>
	     <td width="50%" class="table-break-word1">
	       <s:property value="chargesDetails"/>
	      </td>
	    </tr>
	    <tr>
	      <td width="5%" class="table-break-word">:72</td>
	      <td width="30%" class="table-break-word">
	        <s:label value="%{getText('label.769.ppsenderToReceiverInformation')}"></s:label> <span class="optional">[Optional]</span>
	      </td>
	     <td width="50%" class="table-break-word1">
	       <s:property value="senderToReceiverInformation" />
	      </td>
	    </tr>
	</table>	
<div class="clearfix"></div>
	<table width="75%" cellpadding="0" cellspacing="0" border="0">
		<tr>
			<td align="lesf">
				<input type="button" value="Export Excel" onclick="callExpToExcel()">
				<input type="button" value="Print" onclick="generatePdfAdviceofReduction();">
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