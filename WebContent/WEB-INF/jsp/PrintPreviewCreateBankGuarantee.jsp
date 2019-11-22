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
	document.forms[0].action="getBGAuthorization";
	document.getElementById("txnRef").value=document.getElementById("printPreviewTxnRef").value;
	document.forms[0].submit();
}

function callExpToExcel()
{
	document.getElementById("hiddenTxnRef").value=document.getElementById("hiddenTxnRef").value;
	document.forms[0].action="exportToExcelCreateBankGuaranteePage";
	document.forms[0].submit();
	}

function callPrintpreviewBackButton()
{
	
	document.forms[0].action="pendingAuthorizationOutboundAction";
	document.forms[0].submit();
}

function generatePdfCreateBankGuarantee(){
	document.forms[0].action="generatePDFCreateBankGuarantee";
	document.forms[0].submit();
}
</script>
<div id="contentPane">
<div id="contentPaneTop"></div>
<div id="contentPaneFluid">
<div id="contentPaneInternal"><span class="crumbs"><a
	href="#">Outbound Payments</a>&nbsp;>&nbsp;Create Bank Guarantee</span></div>
<div id="content">
<h1>Create Bank Guarantee (MT-760)</h1>
<s:form method="post" id="form">
<s:hidden id="txnRef" name="txnRef"></s:hidden>
<s:hidden id="printPreviewTxnRef" name="printPreviewTxnRef"></s:hidden>
<s:hidden id="hiddenTxnRef" name="hiddenTxnRef"></s:hidden>
<s:hidden id="validUserToApprove" name="validUserToApprove"></s:hidden>
<h3>Basic Details </h3>
	<table width="100%">
		<tr>
			<td width="25%">
			   <s:label value="%{getText('label.messageType')}"></s:label>
			</td>
			<td width="25%">760</td>
		
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
	<table width="85%" class="table" >
		<tr>
	      <td width="5%" class="table-break-word">:27</td>
	      <td width="30%" class="table-break-word">
	        <s:label cssClass="MandatoryField" value="%{getText('label.760.ppSequenceofTotal')}"></s:label> <span class="MandatoryField">[MandatoryField]</span>
	      </td>
	      <td width="50%" class="table-break-word">
	        <s:property value="sequence"/> / <s:property value="sequenceofTotal"/>
	      </td>
	    </tr>
	    <tr>
	      
	      <td width="5%" class="table-break-word">:20</td>
	      <td width="30%" class="table-break-word">
	        <s:label cssClass="MandatoryField" value="%{getText('label.760.pptransactionReferenceNumber')}"></s:label> <span class="MandatoryField">[MandatoryField]</span>
	      </td>
	    <td width="50%" class="table-break-word">
	        <s:property value="bgNumber"/>
	      </td>
	    </tr>
		<tr>
			<td width="5%" class="table-break-word">:23</td>
			<td width="30%" class="table-break-word">
			   <s:label cssClass="MandatoryField" value="%{getText('label.760.ppFurtherIdentification')}"></s:label> <span class="MandatoryField">[MandatoryField]</span>
			</td>
		 <td width="50%" class="table-break-word">
		     <s:property value="bgCreateType"/>
		  </td>
		</tr>	
		<tr>
		  <td width="5%" class="table-break-word">:30</td>
		  <td width="30%" class="table-break-word">
		     <s:label value="%{getText('label.760.ppbgDate')}"></s:label> <span class="optional">[Optional]</span>
		  </td>
		<td width="50%" class="table-break-word">
		      <s:date name="bgDate" format="MM/dd/yyyy" />
		   </td>
		</tr>
		<tr>
		  <td width="5%" class="table-break-word">:30C</td>
		  <td width="30%" class="table-break-word">
		    <s:label cssClass="MandatoryField" value="%{getText('label.760.ppbgRuleCode')}"></s:label> <span class="MandatoryField">[MandatoryField]</span>
		  </td>
		<td width="50%" class="table-break-word">
		     <s:property value="bgRuleCode"/>
		   </td>
		</tr>
		<tr>
			<td width="5%" class="table-break-word">:30C</td>
			<td width="30%" class="table-break-word">
			  <s:label cssClass="MandatoryField" value="%{getText('label.760.ppbgRuleNarration')}"></s:label> <span class="MandatoryField">[MandatoryField]</span>
			</td>
		<td width="50%" class="table-break-word">
		    <s:property value="bgRuleNarration"/>
		  </td>
		</tr>
		<tr>
		  <td width="5%" class="table-break-word">:77C</td>
		  <td width="30%" class="table-break-word">
		    <s:label cssClass="MandatoryField" value="%{getText('label.760.ppbgDetails')}"></s:label> <span class="MandatoryField">[MandatoryField]</span>
		  </td>
		<td width="50%" class="table-break-word1">
		      <s:property value="bgDetails"/>
		   </td>
		</tr>
		<tr>
		  <td width="5%" class="table-break-word">:72</td>
		  <td width="30%" class="table-break-word">
		     <s:label value="%{getText('label.760.ppSendertoReceiverInformation')}"></s:label> <span class="optional">[Optional]</span>
		  </td>
		 <td width="50%" class="table-break-word1">
		      <s:property value="senderToReceiverInformation"/>
		   </td>
		</tr>
	</table>	
<div class="clearfix"></div>
	<table width="75%" cellpadding="0" cellspacing="0" border="0">
		<tr>
			<td align="lesf">
				<input type="button" value="Export Excel" onclick="callExpToExcel()">
				<input type="button" value="Print" onclick="generatePdfCreateBankGuarantee();">
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