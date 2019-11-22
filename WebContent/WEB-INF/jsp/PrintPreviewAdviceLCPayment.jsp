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
	document.forms[0].action="getAdviceLCPaymentAuthorization";
	document.getElementById("txnRef").value=document.getElementById("printPreviewTxnRef").value;
	document.forms[0].submit();
}

function callExpToExcel()
{
	alert("call ExpToExcel Page");
	document.getElementById("hiddenTxnRef").value=document.getElementById("hiddenTxnRef").value;
	document.forms[0].action="exportToExcelAdviceLCPaymentPage";
	document.forms[0].submit();
	}

function callPrintpreviewBackButton()
{
	
	document.forms[0].action="pendingAuthorizationOutboundAction";
	document.forms[0].submit();
}

function generatePdfAdviceLCPayment()
{
	document.forms[0].action="generatePDFAdviceLCPayment";
	document.forms[0].submit();
}
</script>
<div id="contentPane">
<div id="contentPaneTop"></div>
<div id="contentPaneFluid">
<div id="contentPaneInternal"><span class="crumbs"><a
	href="#">Outbound Payments</a>&nbsp;>&nbsp;Advice LC Payment</span></div>
<div id="content">
<h1>Advice LC Payment (MT-756)</h1>
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
			<td width="25%">756</td>
		
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
	        <s:label cssClass="MandatoryField" value="%{getText('label.756.pplc_Number')}"></s:label> <span class="MandatoryField">[MandatoryField]</span>
	      </td>
	     <td width="50%" class="table-break-word">
	        <s:property value="lcNumber"/>
	      </td>
	    </tr>
	    <tr>
	      <td width="5%" class="table-break-word">:21</td>
	      <td width="30%" class="table-break-word">
	        <s:label cssClass="MandatoryField" value="%{getText('label.756.ppPresentingBanksReference')}"></s:label> <span class="MandatoryField">[MandatoryField]</span>
	      </td>
	     <td width="50%" class="table-break-word">
	        <s:property value="presentingBanksReference"/>
	      </td>
	    </tr>
	    <tr>
	      <td width="5%" class="table-break-word">:32B</td>
	      <td width="30%" class="table-break-word">
	        <s:label cssClass="MandatoryField" value="%{getText('label.756.pplcCurrency')}"></s:label> <span class="MandatoryField">[MandatoryField]</span>
	      </td>
	     <td width="50%" class="table-break-word">
	        <s:property value="claimCurrency"/>
	      </td>
	    </tr>
	    <tr>
	      <td width="5%" class="table-break-word">:32B</td>
	      <td width="30%" class="table-break-word">
	        <s:label cssClass="MandatoryField" value="%{getText('label.756.ppTotalAmountClaimed')}"></s:label> <span class="MandatoryField">[MandatoryField]</span>
	      </td>
	    <td width="50%" class="table-break-word">
	        <s:property value="totalAmountClaimed"/>
	      </td>
	    </tr>
	    <tr>
	      <td width="5%" class="table-break-word">:33A</td>
	      <td width="30%" class="table-break-word">
	        <s:label cssClass="MandatoryField" value="%{getText('label.756.ppamountPaidDate')}"></s:label> <span class="MandatoryField">[MandatoryField]</span>
	      </td>
	      <td width="50%" class="table-break-word">
	        <s:date name="amountPaidDate" format="MM/dd/yyyy" />
	      </td>
	    </tr>
	    <tr>
	      <td width="5%" class="table-break-word">:33A</td>
	      <td width="30%" class="table-break-word">
	        <s:label cssClass="MandatoryField" value="%{getText('label.756.ppcurrency')}"></s:label> <span class="MandatoryField">[MandatoryField]</span>
	      </td>
	     <td width="50%" class="table-break-word">
	        <s:date name="currency" format="MM/dd/yyyy 'at' hh:mm a" />
	      </td>
	    </tr>
	    <tr>
	      <td width="5%" class="table-break-word">:33A</td>
	      <td width="30%" class="table-break-word">
	        <s:label cssClass="MandatoryField" value="%{getText('label.756.ppPaidAmount')}"></s:label> <span class="MandatoryField">[MandatoryField]</span>
	      </td>
	     <td width="50%" class="table-break-word">
	        <s:property value="paidAmount"/>
	      </td>
	    </tr>
	    <tr>
	      <td width="5%" class="table-break-word">:53a</td>
	      <td width="30%" class="table-break-word">
	        <s:label value="%{getText('label.756.ppSendersCorrespondentPartyIdentifier')}"></s:label> <span class="optional">[Optional]</span>
	      </td>
	    <td width="50%" class="table-break-word">
	       <s:property value="sendersCorrespondentPartyIdentifier"/> <s:property value="senderCorrespontAcount"/>
	      </td>
	    </tr>
	    <tr>
	      <td width="5%" class="table-break-word">:53a</td>
	      <td width="30%" class="table-break-word">
	        <s:label value="%{getText('label.756.ppSendersCorrespondentcode')}"></s:label> <span class="optional">[Optional]</span>
	      </td>
	    <td width="50%" class="table-break-word">
	        <s:property value="sendersCorrespondentCode" />
	      </td>
	    </tr>
	    <tr>
	      <td width="5%" class="table-break-word">:53a</td>
	      <td width="30%" class="table-break-word">
	        <s:label value="%{getText('label.756.ppSendersCorrespondentLocation')}"></s:label> <span class="optional">[Optional]</span>
	      </td>
	     <td width="50%" class="table-break-word">
	        <s:property value="sendersCorrespondentLocation" />
	      </td>
	    </tr>
	    <tr>
	      <td width="5%" class="table-break-word">:53a</td>
	      <td width="30%" class="table-break-word">
	        <s:label value="%{getText('label.756.ppSendersCorrespondentNameandAddress')}"></s:label> <span class="optional">[Optional]</span>
	      </td>
	     <td width="50%" class="table-break-word1">
	        <s:property value="sendersCorrespondentNameandAddress"/>
	      </td>
	    </tr>
	    <tr>
	      <td width="5%" class="table-break-word">:54a</td>
	      <td width="30%" class="table-break-word">
	        <s:label value="%{getText('label.756.ppReceiversCorrespondentPartyIdentifier')}"></s:label> <span class="optional">[Optional]</span>
	      </td>
	      <td width="50%" class="table-break-word">
	        <s:property value="receiversCorrespondentPartyIdentifier" /> <s:property value="receiverCorrespontAcount" />
	      </td>
	    </tr>
	    <tr>
	      <td width="5%" class="table-break-word">:54a</td>
	      <td width="30%" class="table-break-word">
	        <s:label value="%{getText('label.756.ppReceiversCorrespondentCode')}"></s:label> <span class="optional">[Optional]</span>
	      </td>
	    <td width="50%" class="table-break-word">
	        <s:property value="receiversCorrespondentCode"/>
	      </td>
	    </tr>
	    <tr>
	    <td width="5%" class="table-break-word">:54a</td>
	      <td width="30%" class="table-break-word">
	        <s:label value="%{getText('label.756.ppReceiversCorrespondentLocation')}"></s:label> <span class="optional">[Optional]</span>
	      </td>
	     <td width="50%" class="table-break-word">
	        <s:property value="receiversCorrespondentLocation" />
	      </td>
	    </tr>
	    <tr>
	      <td width="5%" class="table-break-word">:54a</td>
	      <td width="30%" class="table-break-word">
	        <s:label value="%{getText('label.756.ppReceiversCorrespondentNameandAddress')}"></s:label> <span class="optional">[Optional]</span>
	      </td>
	    <td width="50%" class="table-break-word1">
	        <s:property value="receiversCorrespondentNameandAddress"/>
	      </td>
	    </tr>
	    <tr>
	      <td width="5%" class="table-break-word">:72</td>
	      <td width="30%" class="table-break-word">
	        <s:label value="%{getText('label.756.ppSendertoReceiverInformation')}"></s:label> <span class="optional">[Optional]</span>
	      </td>
	     <td width="50%" class="table-break-word1">
	        <s:property value="sendertoReceiverInformation" />
	      </td>
	    </tr>
	</table>	
<div class="clearfix"></div>
	<table width="75%" cellpadding="0" cellspacing="0" border="0">
		<tr>
			<td align="lesf">
				<input type="button" value="Export Excel" onclick="callExpToExcel()">
				<input type="button" value="Print" onclick="generatePdfAdviceLCPayment()">
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