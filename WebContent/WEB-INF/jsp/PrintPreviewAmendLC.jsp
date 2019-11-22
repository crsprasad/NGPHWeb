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
	document.forms[0].action="getAmendLCAuthorization";
	document.getElementById("txnRef").value=document.getElementById("printPreviewTxnRef").value;
	document.forms[0].submit();
}

function callExpToExcel()
{
	document.getElementById("hiddenTxnRef").value=document.getElementById("hiddenTxnRef").value;
	document.forms[0].action="exportToExcelAmendLCPage";
	document.forms[0].submit();
	}
function callPrintpreviewBackButton()
{
	
	document.forms[0].action="pendingAuthorizationOutboundAction";
	document.forms[0].submit();
}	
function generatePdfAmendLC()
{
	document.forms[0].action="generatePDFAmendLC";
	document.forms[0].submit();
}
</script>
<div id="contentPane">
<div id="contentPaneTop"></div>
<div id="contentPaneFluid">
<div id="contentPaneInternal"><span class="crumbs"><a
	href="#">Outbound Payments</a>&nbsp;>&nbsp;Amend LC</span></div>
<div id="content">
<h1>Amend LC (MT-707)</h1>
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
			<td width="25%">707</td>
		
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
	        <s:label cssClass="MandatoryField" value="%{getText('label.707.ppSenderBanksReference')}"></s:label> <span class="MandatoryField">[MandatoryField]</span>
	      </td>
	      <td width="50%" class="table-break-word">
	        <s:property value="lcNumber"/>
	      </td>
	    </tr>
	    <tr>
	      <td width="5%" class="table-break-word">:21</td>
	      <td width="30%" class="table-break-word">
	        <s:label cssClass="MandatoryField" value="%{getText('label.707.ppReceiverBanksReference')}"></s:label> <span class="MandatoryField">[MandatoryField]</span>
	      </td>
	      <td width="50%" class="table-break-word">
	        <s:property value="receiverBankReference"/>
	      </td>
	    </tr>
	    <tr>
	      <td width="5%" class="table-break-word">:23</td>
	      <td width="30%" class="table-break-word">
	        <s:label value="%{getText('label.707.ppIssuingBankReference')}"></s:label> <span class="optional">[Optional]</span>
	      </td>
	      <td width="50%" class="table-break-word">
	        <s:property value="senderBankReference"/>
	      </td>
	    </tr>
	    <tr>
	      <td width="5%" class="table-break-word">:52a</td>
	      <td width="30%" class="table-break-word">
	        <s:label value="%{getText('label.707.ppIssuingBankPartyIdentifier')}"></s:label> <span class="optional">[Optional]</span>
	      </td>
	      <td width="50%" class="table-break-word">
	        <s:property value="sendersCorrespondentPartyIdentifier"/>
	      </td>
	    </tr>
	    <tr>
	      <td width="5%" class="table-break-word">:52a</td>
	      <td width="30%" class="table-break-word">
	        <s:label value="%{getText('label.707.ppIssuingBankCode')}"></s:label> <span class="optional">[Optional]</span>
	      </td>
	      <td width="50%" class="table-break-word">
	        <s:property value="applicantBankCode"/>
	      </td>
	    </tr>
	    <tr>
	      <td width="5%" class="table-break-word">:52a</td>
	      <td width="30%" class="table-break-word">
	        <s:label value="%{getText('label.707.ppIssuingBankNameandAddress')}"></s:label> <span class="optional">[Optional]</span>
	      </td>
	      <td width="50%" class="table-break-word1">
	        <s:property value="issunigBankNameAndAddress"/>
	      </td>
	    </tr>
	    <tr>
	      <td width="5%" class="table-break-word">:31C</td>
	      <td width="30%" class="table-break-word">
	        <s:label value="%{getText('label.707.ppDateofIssue')}"></s:label> <span class="optional">[Optional]</span>
	      </td>
	     <td width="50%" class="table-break-word">
	        <s:date name="issueDate" format="MM/dd/yyyy" />
	      </td>
	    </tr>
	    <tr>
	      <td width="5%" class="table-break-word">:30</td>
	      <td width="30%" class="table-break-word">
	        <s:label value="%{getText('label.707.ppDateofAmendment')}"></s:label> <span class="optional">[Optional]</span>
	      </td>
	     <td width="50%" class="table-break-word">
	        <s:date name="amendmentDate" format="MM/dd/yyyy" />
	      </td>
	    </tr>
	    <tr>
	      <td width="5%" class="table-break-word">:26E</td>
	      <td width="30%" class="table-break-word">
	        <s:label value="%{getText('label.707.ppNumberofAmendment')}"></s:label> <span class="optional">[Optional]</span>
	      </td>
	      <td width="50%" class="table-break-word">
	        <s:property value="lcAmndmntNo"/>
	      </td>
	    </tr>
	    <tr>
	      <td width="5%" class="table-break-word">:59</td>
	      <td width="30%" class="table-break-word">
	        <s:label cssClass="MandatoryField" value="%{getText('label.707.ppbeneficiaryNameAddress')}"></s:label> <span class="MandatoryField">[MandatoryField]</span>
	      </td>
	      <td width="50%" class="table-break-word1">
	        <s:property value="beneficiaryNameAddress"/>
	      </td>
	    </tr>
	    <tr>
	      <td width="5%" class="table-break-word">:31E</td>
	      <td width="30%" class="table-break-word">
	        <s:label value="%{getText('label.707.ppNewDateofExpiry')}"></s:label> <span class="optional">[Optional]</span>
	      </td>
	      <td width="50%" class="table-break-word">
	        <s:date name="newAmendExpiryDate" format="MM/dd/yyyy" />
	      </td>
	    </tr>
	    <tr>
	      <td width="5%" class="table-break-word">:32B</td>
	      <td width="30%" class="table-break-word">
	        <s:label value="%{getText('label.707.ppCurrency')}"></s:label> <span class="optional">[Optional]</span>
	      </td>
	      <td width="50%" class="table-break-word">
	        <s:property value="msgCurrency"/>
	      </td>
	    </tr>
	    <tr>
	      <td width="5%" class="table-break-word">:32B</td>
	      <td width="30%" class="table-break-word">
	        <s:label value="%{getText('label.707.ppIncreaseofLCAmount')}"></s:label> <span class="optional">[Optional]</span>
	      </td>
	      <td width="50%" class="table-break-word">
	       <s:property value="increaseAmendAmount"/>
	      </td>
	    </tr>
	    <tr>
	      <td width="5%" class="table-break-word">:33B</td>
	      <td width="30%" class="table-break-word">
	        <s:label value="%{getText('label.707.ppcurrency')}"></s:label> <span class="optional">[Optional]</span>
	      </td>
	      <td width="50%" class="table-break-word">
	        <s:property value="lcCurrency"/>
	      </td>
	    </tr>
	    <tr>
	      <td width="5%" class="table-break-word">:33B</td>
	      <td width="30%" class="table-break-word">
	        <s:label value="%{getText('label.707.ppDecreaseofLCAmount')}"></s:label> <span class="optional">[Optional]</span>
	      </td>
	      <td width="50%" class="table-break-word">
	       <s:property value="decreaseAmendAmount"/>
	      </td>
	    </tr>
	    <tr>
	      <td width="5%" class="table-break-word">:34B</td>
	      <td width="30%" class="table-break-word">
	        <s:label value="%{getText('label.707.ppCUrrency')}"></s:label> <span class="optional">[Optional]</span>
	      </td>
	      <td width="50%" class="table-break-word">
	       <s:property value="currency"/>
	      </td>
	    </tr>
	    <tr>
	      <td width="5%" class="table-break-word">:34B</td>
	      <td width="30%" class="table-break-word">
	        <s:label value="%{getText('label.707.ppNewLCAmount')}"></s:label> <span class="optional">[Optional]</span>
	      </td>
	      <td width="50%" class="table-break-word">
	       <s:property value="newLcAmount"/>
	      </td>
	    </tr>
	    <tr>
	      <td width="5%" class="table-break-word">:39A</td>
	      <td width="30%" class="table-break-word">
	        <s:label value="%{getText('label.707.pppositiveTolerance')}"></s:label> <span class="optional">[Optional]</span>
	      </td>
	      <td width="50%" class="table-break-word">
	       <s:property value="positiveTolerance"/>
	      </td>
	    </tr>
	    <tr>
	      <td width="5%" class="table-break-word">:39A</td>
	      <td width="30%" class="table-break-word">
	        <s:label value="%{getText('label.707.ppnegativeTolerance')}"></s:label> <span class="optional">[Optional]</span>
	      </td>
	      <td width="50%" class="table-break-word">
	       <s:property value="negativeTolerance"/>
	      </td>
	    </tr>
	    <tr>
	      <td width="5%" class="table-break-word">:39B</td>
	      <td width="30%" class="table-break-word">
	        <s:label value="%{getText('label.707.ppmaximumCreditAmount')}"></s:label> <span class="optional">[Optional]</span>
	      </td>
	      <td width="50%" class="table-break-word">
	       <s:property value="maximumCreditAmount"/>
	      </td>
	    </tr>
	    <tr>
	      <td width="5%" class="table-break-word">:39C</td>
	      <td width="30%" class="table-break-word">
	        <s:label value="%{getText('label.707.ppAdditionalAmountsCovered')}"></s:label> <span class="optional">[Optional]</span>
	      </td>
	      <td width="50%" class="table-break-word1">
	       <s:property value="additionalAmountsCovered"/>
	      </td>
	    </tr>
	    <tr>
	      <td width="5%" class="table-break-word">:44A</td>
	      <td width="30%" class="table-break-word">
	        <s:label value="%{getText('label.707.ppplaceofDispatch')}"></s:label> <span class="optional">[Optional]</span>
	      </td>
	      <td width="50%" class="table-break-word">
	       <s:property value="initialDispatchPlace"/>
	      </td>
	    </tr>
	    <tr>
	      <td width="5%" class="table-break-word">:44E</td>
	      <td width="30%" class="table-break-word">
	        <s:label value="%{getText('label.707.ppportofLoading')}"></s:label> <span class="optional">[Optional]</span>
	      </td>
	      <td width="50%" class="table-break-word">
	       <s:property value="goodsLoadingDispatchPlace"/>
	      </td>
	    </tr>
	    <tr>
	      <td width="5%" class="table-break-word">:44F</td>
	      <td width="30%" class="table-break-word">
	        <s:label value="%{getText('label.707.ppportofDischarge')}"></s:label> <span class="optional">[Optional]</span>
	      </td>
	      <td width="50%" class="table-break-word">
	       <s:property value="goodsDestination"/>
	      </td>
	    </tr> 
	    <tr>
	      <td width="5%" class="table-break-word">:44B</td>
	      <td width="30%" class="table-break-word">
	        <s:label value="%{getText('label.707.ppfinalDeliveryPlace')}"></s:label> <span class="optional">[Optional]</span>
	      </td>
	      <td width="50%" class="table-break-word">
	       <s:property value="finalDeliveryPlace"/>
	      </td>
	    </tr>
	    <tr>
	      <td width="5%" class="table-break-word">:44C</td>
	      <td width="30%" class="table-break-word">
	        <s:label value="%{getText('label.707.pplatestDateofShipment')}"></s:label> <span class="optional">[Optional]</span>
	      </td>
	      <td width="50%" class="table-break-word">
	       <s:date name="latestDateofShipment" format="MM/dd/yyyy" />
	      </td>
	    </tr>
	    <tr>
	      <td width="5%" class="table-break-word">:44D</td>
	      <td width="30%" class="table-break-word">
	        <s:label value="%{getText('label.707.ppShipmentPeriod')}"></s:label> <span class="optional">[Optional]</span>
	      </td>
	      <td width="50%" class="table-break-word1">
	       <s:property value="shipmentPeriod"/>
	      </td>
	    </tr>
	    <tr>
	      <td width="5%" class="table-break-word">:79</td>
	      <td width="30%" class="table-break-word">
	        <s:label value="%{getText('label.707.ppNarrative')}"></s:label> <span class="optional">[Optional]</span>
	      </td>
	      <td width="50%" class="table-break-word1">
	       <s:property value="Narrative"/>
	      </td>
	    </tr>
	    <tr>
	      <td width="5%" class="table-break-word">:72</td>
	      <td width="30%" class="table-break-word">
	        <s:label value="%{getText('label.707.ppSendertoReceiverInformation')}"></s:label> <span class="optional">[Optional]</span>
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
				<input type="button" value="Print" onclick="generatePdfAmendLC()">
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