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
	document.forms[0].action="getAuthoLCPaymentAdviceAuthorization";
	document.getElementById("txnRef").value=document.getElementById("printPreviewTxnRef").value;
	document.forms[0].submit();
}

function callExpToExcel()
{
	document.getElementById("hiddenTxnRef").value=document.getElementById("hiddenTxnRef").value;
	document.forms[0].action="exportToExcelAuthoriseLCPaymentAdvicePage";
	document.forms[0].submit();
	}
function callPrintpreviewBackButton()
{
	
	document.forms[0].action="pendingAuthorizationOutboundAction";
	document.forms[0].submit();
}
function generatePdfAuthoriseLCPaymentAdvice()
{
	document.forms[0].action="generatePDFAuthoriseLCPaymentAdvice";
	document.forms[0].submit();
}
</script>
<div id="contentPane">
<div id="contentPaneTop"></div>
<div id="contentPaneFluid">
<div id="contentPaneInternal"><span class="crumbs"><a
	href="#">Outbound Payments</a>&nbsp;>&nbsp;Authorization To Reimburse</span></div>
<div id="content">
<h1>Authorization To Reimburse (MT-740)</h1>
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
			<td width="25%">740</td>
		
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
	        <s:label cssClass="MandatoryField" value="%{getText('label.740.pplc_Number')}"></s:label> <span class="MandatoryField">[MandatoryField]</span>
	      </td>
	      <td width="50%" class="table-break-word">
	        <s:property value="lcNumber"/>
	      </td>
	    </tr>
	    <tr>
	      <td width="5%" class="table-break-word">:25</td>
	      <td width="30%" class="table-break-word">
	        <s:label value="%{getText('label.740.ppAccountID')}"></s:label> <span class="optional">[Optional]</span>
	      </td>
	      <td width="50%" class="table-break-word">
	        <s:property value="acountID"/>
	      </td>
	    </tr>
	    <tr>
	      <td width="5%" class="table-break-word">:40F</td>
	      <td width="30%" class="table-break-word">
	        <s:label cssClass="MandatoryField" value="%{getText('label.740.ppapplicableRules')}"></s:label> <span class="MandatoryField">[MandatoryField]</span>
	      </td>
	      <td width="50%" class="table-break-word">
	        <s:property value="applicableRule"/>
	      </td>
	    </tr>
	    <tr>
	      <td width="5%" class="table-break-word">:31D</td>
	      <td width="30%" class="table-break-word">
	        <s:label value="%{getText('label.740.ppExpiryDate')}"></s:label> <span class="optional">[Optional]</span>
	      </td>
	      <td width="50%" class="table-break-word">
	        <s:date name="lcExpiryDate" format="MM/dd/yyyy" />
	      </td>
	    </tr>
	    <tr>
	      <td width="5%" class="table-break-word">:31D</td>
	      <td width="30%" class="table-break-word">
	        <s:label value="%{getText('label.740.ppexpiryPlace')}"></s:label> <span class="optional">[Optional]</span>
	      </td>
	      <td width="50%" class="table-break-word">
	        <s:property value="lcExpirePlace"/>
	      </td>
	    </tr>
	    <tr>
	      <td width="5%" class="table-break-word">:58a</td>
	      <td width="30%" class="table-break-word">
	        <s:label value="%{getText('label.740.ppNegotiatingBankPartyIdentifier')}"></s:label> <span class="optional">[Optional]</span>
	      </td>
	      <td width="50%" class="table-break-word">
	        <s:property value="negotiatingBankPartyIdentifier"/> <s:property value="negotiatingBankAccount"/>
	      </td>
	    </tr>
	    <tr>
	      <td width="5%" class="table-break-word">:58a</td>
	      <td width="30%" class="table-break-word">
	        <s:label value="%{getText('label.740.ppNegotiatingBankCode')}"></s:label> <span class="optional">[Optional]</span>
	      </td>
	      <td width="50%" class="table-break-word">
	        <s:property value="negotiatingBankCode"/>
	      </td>
	    </tr>
	     <tr>
	      <td width="5%" class="table-break-word">:58a</td>
	      <td width="30%" class="table-break-word">
	        <s:label value="%{getText('label.740.ppNegotiatingBankNameAndAddress')}"></s:label> <span class="optional">[Optional]</span>
	      </td>
	      <td width="50%" class="table-break-word1">
	        <s:property value="negotiatingBankNameAndAddress"/>
	      </td>
	    </tr>
	    <tr>
	      <td width="5%" class="table-break-word">:59</td>
	      <td width="30%" class="table-break-word">
	        <s:label value="%{getText('label.740.ppbeneficiaryNameAddress')}"></s:label> <span class="optional">[Optional]</span>
	      </td>
	      <td width="50%" class="table-break-word1">
	        <s:property value="beneficiaryNameAddress"/>
	      </td>
	    </tr>
	    <tr>
	      <td width="5%" class="table-break-word">:32B</td>
	      <td width="30%" class="table-break-word">
	        <s:label cssClass="MandatoryField" value="%{getText('label.740.ppcreditCurrency')}"></s:label> <span class="MandatoryField">[MandatoryField]</span>
	      </td>
	      <td width="50%" class="table-break-word">
	        <s:property value="lcCurrency"/>
	      </td>
	    </tr>
	    <tr>
	      <td width="5%" class="table-break-word">:32B</td>
	      <td width="30%" class="table-break-word">
	        <s:label cssClass="MandatoryField" value="%{getText('label.740.ppCreditAmount')}"></s:label> <span class="MandatoryField">[MandatoryField]</span>
	      </td>
	      <td width="50%" class="table-break-word">
	        <s:property value="creditAmount"/>
	      </td>
	    </tr>
	    <tr>
	      <td width="5%" class="table-break-word">:39A</td>
	      <td width="30%" class="table-break-word">
	        <s:label value="%{getText('label.740.pppositiveTolerance')}"></s:label> <span class="optional">[Optional]</span>
	      </td>
	      <td width="50%" class="table-break-word">
	        <s:property value="positiveTolerance"/>
	      </td>
	    </tr>
	    <tr>
	      <td width="5%" class="table-break-word">:39A</td>
	      <td width="30%" class="table-break-word">
	        <s:label value="%{getText('label.740.ppnegativeTolerance')}"></s:label> <span class="optional">[Optional]</span>
	      </td>
	      <td width="50%" class="table-break-word">
	        <s:property value="negativeTolerance"/>
	      </td>
	    </tr>
	    <tr>
	      <td width="5%" class="table-break-word">:39B</td>
	      <td width="30%" class="table-break-word">
	        <s:label value="%{getText('label.740.ppmaximumCreditAmount')}"></s:label> <span class="optional">[Optional]</span>
	      </td>
	      <td width="50%" class="table-break-word">
	        <s:property value="maximumCreditAmount"/>
	      </td>
	    </tr>
	    <tr>
	      <td width="5%" class="table-break-word">:39C</td>
	      <td width="30%" class="table-break-word">
	        <s:label value="%{getText('label.740.ppAdditionalAmountsCovered')}"></s:label> <span class="optional">[Optional]</span>
	      </td>
	      <td width="50%" class="table-break-word1">
	        <s:property value="additionalAmountsCovered"/>
	      </td>
	    </tr>
	    <tr>
	      <td width="5%" class="table-break-word">:41a</td>
	      <td width="30%" class="table-break-word">
	        <s:label cssClass="MandatoryField" value="%{getText('label.740.ppAvailableWithIdentifier')}"></s:label> <span class="MandatoryField">[MandatoryField]</span>
	      </td>
	      <td width="50%" class="table-break-word">
	        <s:property value="availableWithIdentifier"/> <s:property value="availableWithIdentifier1"/>
	      </td>
	    </tr>
	    <tr>
	      <td width="5%" class="table-break-word">:41A</td>
	      <td width="30%" class="table-break-word">
	        <s:label cssClass="MandatoryField" value="%{getText('label.740.ppauthorisedBankCode')}"></s:label> <span class="MandatoryField">[MandatoryField]</span>
	      </td>
	      <td width="50%" class="table-break-word">
	        <s:property value="authorisedBankCode"/>
	      </td>
	    </tr>
	    <tr>
	      <td width="5%" class="table-break-word">:41a</td>
	      <td width="30%" class="table-break-word">
	        <s:label cssClass="MandatoryField" value="%{getText('label.740.ppauthorisationMode')}"></s:label> <span class="MandatoryField">[MandatoryField]</span>
	      </td>
	      <td width="50%" class="table-break-word">
	        <s:property value="authorisationMode"/>
	      </td>
	    </tr>
	    <tr>
	      <td width="5%" class="table-break-word">:41D</td>
	      <td width="30%" class="table-break-word">
	        <s:label cssClass="MandatoryField" value="%{getText('label.740.ppauthorisedAndAddress')}"></s:label> <span class="MandatoryField">[MandatoryField]</span>
	      </td>
	      <td width="50%" class="table-break-word1">
	        <s:property value="authorisedAndAddress"/>
	      </td>
	    </tr>
	    <tr>
	      <td width="5%" class="table-break-word">:42C</td>
	      <td width="30%" class="table-break-word">
	        <s:label value="%{getText('label.740.ppDraftsAt')}"></s:label> <span class="optional">[Optional]</span>
	      </td>
	      <td width="50%" class="table-break-word1">
	        <s:property value="draftsAt"/>
	      </td>
	    </tr>
	    <tr>
	      <td width="5%" class="table-break-word">:42a</td>
	      <td width="30%" class="table-break-word">
	        <s:label value="%{getText('label.740.ppDraweeBankID')}"></s:label> <span class="optional">[Optional]</span>
	      </td>
	      <td width="50%" class="table-break-word">
	        <s:property value="draweeBankpartyidentifier"/> <s:property value="draweeBankAccount"/>
	      </td>
	    </tr>
	    <tr>
	      <td width="5%" class="table-break-word">:42a</td>
	      <td width="30%" class="table-break-word">
	        <s:label value="%{getText('label.740.ppDraweeBankCode')}"></s:label> <span class="optional">[Optional]</span>
	      </td>
	      <td width="50%" class="table-break-word">
	        <s:property value="draweeBankCode"/>
	      </td>
	    </tr>
	    <tr>
	      <td width="5%" class="table-break-word">:42a</td>
	      <td width="30%" class="table-break-word">
	        <s:label value="%{getText('label.740.ppDraweeBankNameAddress')}"></s:label> <span class="optional">[Optional]</span>
	      </td>
	      <td width="50%" class="table-break-word1">
	        <s:property value="draweeBankNameAddress"/>
	      </td>
	    </tr>
	    <tr>
	      <td width="5%" class="table-break-word">:42M</td>
	      <td width="30%" class="table-break-word">
	        <s:label value="%{getText('label.740.ppMixedPaymentDetails')}"></s:label> <span class="optional">[Optional]</span>
	      </td>
	     <td width="50%" class="table-break-word1">
	        <s:property value="mixedPaymentDetails"/>
	      </td>
	    </tr>
	    <tr>
	      <td width="5%" class="table-break-word">:42P</td>
	      <td width="30%" class="table-break-word">
	        <s:label value="%{getText('label.740.ppDeferredPaymentDetails')}"></s:label> <span class="optional">[Optional]</span>
	      </td>
	      <td width="50%" class="table-break-word1">
	        <s:property value="deferredPaymentDetails"/>
	      </td>
	    </tr>
	    <tr>
	      <td width="5%" class="table-break-word">:71A</td>
	      <td width="30%" class="table-break-word">
	        <s:label value="%{getText('label.740.ppReimbursingBanksCharges')}"></s:label> <span class="optional">[Optional]</span>
	      </td>
	      <td width="50%" class="table-break-word">
	        <s:property value="reimbursingBanksCharges"/>
	      </td>
	    </tr>
	    <tr>
	      <td width="5%" class="table-break-word">:71B</td>
	      <td width="30%" class="table-break-word">
	        <s:label value="%{getText('label.740.ppOtherCharges')}"></s:label> <span class="optional">[Optional]</span>
	      </td>
	      <td width="50%" class="table-break-word1">
	        <s:property value="otherCharges"/>
	      </td>
	    </tr>
	    <tr>
	      <td width="5%" class="table-break-word">:72</td>
	      <td width="30%" class="table-break-word">
	        <s:label value="%{getText('label.740.ppSendertoReceiverInformation')}"></s:label> <span class="optional">[Optional]</span>
	      </td>
	      <td width="50%" class="table-break-word1">
	        <s:property value="sendertoReceiverInformation"/>
	      </td>
	    </tr>
	</table>		
<div class="clearfix"></div>
	<table width="75%" cellpadding="0" cellspacing="0" border="0">
		<tr>
			<td align="lesf">
				<input type="button" value="Export Excel" onclick="callExpToExcel()">
				<input type="button" value="Print" onclick="generatePdfAuthoriseLCPaymentAdvice()">
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