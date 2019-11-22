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
	document.forms[0].action="getLetterOFCreditAuthorizationLcOpen";
	document.getElementById("txnRef").value=document.getElementById("printPreviewTxnRef").value;
	document.forms[0].submit();
}

function callExpToExcel()
{
	
	document.getElementById("hiddenTxnRef").value=document.getElementById("hiddenTxnRef").value;
	document.forms[0].action="exportToExcelLcOpenPage";
	document.forms[0].submit();
}
function callPrintpreviewBackButton()
{
	
	document.forms[0].action="pendingAuthorizationOutboundAction";
	document.forms[0].submit();
}
function generatePdfLcOpen()
{
	document.forms[0].action="generatePDFLcOpen";
	document.forms[0].submit();
}
</script>
<div id="contentPane">
<div id="contentPaneTop"></div>
<div id="contentPaneFluid">
<div id="contentPaneInternal"><span class="crumbs"><a
	href="#">Outbound Payments</a>&nbsp;>&nbsp;Lc Open</span></div>
<div id="content">
<h1>Lc Open (MT-700)</h1>
<s:form method="post" id="form">
<s:hidden id="txnRef" name="txnRef"></s:hidden>
<s:hidden id="printPreviewTxnRef" name="printPreviewTxnRef"></s:hidden>
<s:hidden id="hiddenTxnRef" name="hiddenTxnRef"></s:hidden>
<h3> Basic Details </h3>
	<table width="100%">
		<tr>
			<td width="25%"><s:label value="%{getText('label.messageType')}"></s:label></td>
			<td width="25%">700</td>
			<td width="25%"><s:label value="%{getText('label.subMessageType')}"></s:label></td>
			<td width="25%">XXX</td>
		</tr>
		<tr>
			<td width="25%"><s:label value="%{getText('label.MB.IssuingBranchIFSC')}"></s:label></td>
			<td width="25%"><s:property value="issuingBankCode"/></td>
			<td width="25%"><s:label cssClass="MandatoryField" value="%{getText('label.MB.Benefifsc')}"></s:label> <span class="MandatoryField">[MandatoryField]</span></td>
			<td width="25%"><s:property value="advisingBank"/></td>
		</tr>
	</table>
<h3>Message Details </h3>

	<table width="85%" class="table">
		<tr>
			<td width="5%" class="table-break-word">:27</td>
			<td width="30%" class="table-break-word"><s:label cssClass="MandatoryField" value="%{getText('label.700XXX.ppseqofTotal')}"></s:label> <span class="MandatoryField">[MandatoryField]</span></td>
			<td width="50%" class="table-break-word"><s:property value="sequence"/> / <s:property value="sequenceofTotal"/></td>
		</tr>	
		<tr>
			<td width="5%" class="table-break-word">:40A</td>
			<td width="30%" class="table-break-word"><s:label cssClass="MandatoryField" value="%{getText('label.700XXX.ppformofDoc')}"></s:label><span class="MandatoryField">[MandatoryField]</span></td>
			<td width="50%" class="table-break-word"><s:property value="lcType"/></td>
		</tr>
		<tr>
			<td width="5%" class="table-break-word">:20</td>
			<td width="30%" class="table-break-word"><s:label cssClass="MandatoryField" value="%{getText('label.700XXX.pplcNumber')}"></s:label><span class="MandatoryField">[MandatoryField]</span></td>
			<td width="50%" class="table-break-word"><s:property value="lcNumber"/></td>
		</tr>
		<tr>
			<td width="5%" class="table-break-word">:23</td>
			<td width="30%" class="table-break-word"><s:label value="%{getText('label.700XXX.ppreferenceToPreadvice')}"></s:label><span class="optional">[Optional]</span></td>
			<td width="50%" class="table-break-word"><s:property value="lcPresdvice"/></td>
		</tr>
		<tr>
			<td width="5%" class="table-break-word">:31C</td>
			<td width="30%" class="table-break-word"><s:label value="%{getText('label.700XXX.ppdateofIssue')}"></s:label><span class="optional">[Optional]</span></td>
			<td width="50%" class="table-break-word"><s:date name="issueDate" format="MM/dd/yyyy" /></td>
		</tr>
		<tr>
			<td width="5%" class="table-break-word">:40E</td>
			<td width="30%" class="table-break-word"><s:label cssClass="MandatoryField" value="%{getText('label.700XXX.ppapplicableRules')}"></s:label><span class="MandatoryField">[MandatoryField]</span></td>
			<td width="50%" class="table-break-word"><s:property value="applicableRule"/></td>
		</tr>
		<tr>
			<td width="5%" class="table-break-word">:31D</td>
			<td width="30%" class="table-break-word"><s:label cssClass="MandatoryField" value="%{getText('label.700XXX.ppdateofExp')}"></s:label><span	class="MandatoryField">[MandatoryField]</span></td>
			<td width="50%" class="table-break-word"><s:date name="lcExpiryDate" format="MM/dd/yyyy" /></td>
		</tr>
		<tr>
			<td width="5%" class="table-break-word">:31D</td>
			<td width="30%" class="table-break-word"><s:label cssClass="MandatoryField" value="%{getText('label.700XXX.ppplaceofExp')}"></s:label><span class="MandatoryField">[MandatoryField]</span></td>
			<td width="50%" class="table-break-word"><s:property value="lcExpirePlace"/></td>
		</tr>
		<tr>    
			<td width="5%" class="table-break-word">:51a</td>
			<td width="30%" class="table-break-word"><s:label value="%{getText('label.700XXX.ppapplicentBank')}"></s:label><span class="optional">[Optional]</span></td>
			<td width="50%" class="table-break-word"><s:property value="applicentIdentifier"/></td>
		</tr>
		<tr>    
			<td width="5%" class="table-break-word">:51A</td>
			<td width="30%" class="table-break-word"><s:label value="%{getText('label.700XXX.ppapplicentBankCode')}"></s:label><span class="optional">[Optional]</span></td>
			<td width="50%" class="table-break-word"><s:property value="applicantBankCode"/></td>
		</tr>
		<tr>    
			<td width="5%" class="table-break-word">:51D</td>
			<td width="30%" class="table-break-word"><s:label value="%{getText('label.700XXX.ppapplicentBankNameAddress')}"></s:label><span class="optional">[Optional]</span></td>
			<td width="50%" class="table-break-word1"><s:property value="applicentBankNameandAddr"/></td>
		</tr>
		<tr>    
			<td width="5%" class="table-break-word">:50</td>
			<td width="30%" class="table-break-word"><s:label cssClass="MandatoryField" value="%{getText('label.700XXX.ppapplicent')}"></s:label><span class="MandatoryField">[MandatoryField]</span></td>
			<td width="50%" class="table-break-word1"><s:property value="applicantNameAddress"/></td>
		</tr>
		<tr>    
			<td width="5%" class="table-break-word">:59</td>
			<td width="30%" class="table-break-word"><s:label cssClass="MandatoryField" value="%{getText('label.700XXX.ppbeneficiaryNameandAddress')}"></s:label><span class="MandatoryField">[MandatoryField]</span></td>
			<td width="50%" class="table-break-word1"><s:property value="beneficiaryNameAddress"/></td>
		</tr>
		<tr>    
			<td width="5%" class="table-break-word">:32B</td>
			<td width="30%" class="table-break-word"><s:label cssClass="MandatoryField" value="%{getText('label.700XXX.ppcurreyCode')}"></s:label><span class="MandatoryField">[MandatoryField]</span></td>
			<td width="50%" class="table-break-word"><s:property value="lcCurrency"/></td>
		</tr>
		<tr>    
			<td width="5%" class="table-break-word">:32B</td>
			<td width="30%" class="table-break-word"><s:label cssClass="MandatoryField" value="%{getText('label.700XXX.ppamount')}"></s:label><span class="MandatoryField">[MandatoryField]</span></td>
			<td width="50%" class="table-break-word"><s:property value="lcAmount"/></td>
		</tr>
		<tr>    
			<td width="5%" class="table-break-word">:39A</td>
			<td width="30%" class="table-break-word"><s:label value="%{getText('label.700XXX.pppositiveTolerance')}"></s:label><span class="optional">[Optional]</span></td>
			<td width="50%" class="table-break-word"><s:property value="positiveTolerance"/></td>
		</tr>
		<tr>    
			<td width="5%" class="table-break-word">:39A</td>
			<td width="30%" class="table-break-word"><s:label value="%{getText('label.700XXX.ppnegativeTolerance')}"></s:label><span class="optional">[Optional]</span></td>
			<td width="50%" class="table-break-word"><s:property value="negativeTolerance"/></td>
		</tr>
		<tr>    
			<td width="5%" class="table-break-word">:39B</td>
			<td width="30%" class="table-break-word"><s:label value="%{getText('label.700XXX.ppmaximumCreditAmount')}"></s:label><span class="optional">[Optional]</span></td>
			<td width="50%" class="table-break-word"><s:property value="maximumCreditAmount"/></td>
		</tr>
		<tr>
			<td width="5%" class="table-break-word">:39C</td>
			<td width="30%" class="table-break-word"><s:label value="%{getText('label.700XXX.ppadditinalAmtCoverd')}"></s:label><span class="optional">[Optional]</span></td>
			<td width="50%" class="table-break-word1"><s:property value="additionalAmounts"/></td>
		</tr>
		<tr>
			<td width="5%" class="table-break-word">:41a</td>
			<td width="30%" class="table-break-word"><s:label cssClass="MandatoryField" value="%{getText('label.700XXX.ppavailable')}"></s:label><span class="MandatoryField">[MandatoryField]</span></td>
			<td width="50%" class="table-break-word"><s:property value="availableIdentifier"/></td>
		</tr>
		<tr>
			<td width="5%" class="table-break-word">:41A</td>
			<td width="30%" class="table-break-word"><s:label cssClass="MandatoryField" value="%{getText('label.700XXX.ppavailableBankCode')}"></s:label><span class="MandatoryField">[MandatoryField]</span></td>
			<td width="50%" class="table-break-word"><s:property value="authorisedBankCode"/></td>
		</tr>
		<tr>
			<td width="5%" class="table-break-word">:41a</td>
			<td width="30%" class="table-break-word"><s:label cssClass="MandatoryField" value="%{getText('label.700XXX.ppavailableCode')}"></s:label><span class="MandatoryField">[MandatoryField]</span></td>
			<td width="50%" class="table-break-word"><s:property value="authorisationMode"/></td>
		</tr>
		<tr>
			<td width="5%" class="table-break-word">:41D</td>
			<td width="30%" class="table-break-word"><s:label cssClass="MandatoryField" value="%{getText('label.700XXX.ppavailableBankNameandAddress')}"></s:label><span class="MandatoryField">[MandatoryField]</span></td>
			<td width="50%" class="table-break-word1"><s:property value="authorisedAndAddress"/></td>
		</tr>
		<tr>
			<td width="5%" class="table-break-word">:42C</td>
			<td width="30%" class="table-break-word"><s:label value="%{getText('label.700XXX.ppdraftsAt')}"></s:label><span class="optional">[Optional]</span></td>
			<td width="50%" class="table-break-word1"><s:property value="DraftsAt"/></td>
		</tr>
		<tr>
			<td width="5%" class="table-break-word">:42a</td>
			<td width="30%" class="table-break-word"><s:label value="%{getText('label.700XXX.ppDrawee')}"></s:label><span class="optional">[Optional]</span></td>
			<td width="50%" class="table-break-word"><s:property value="draweeIdentifier"/></td>
		</tr>
		<tr>
			<td width="5%" class="table-break-word">:42A</td>
			<td width="30%" class="table-break-word"><s:label value="%{getText('label.700XXX.ppdraweeBankCode')}"></s:label><span class="optional">[Optional]</span></td>
			<td width="50%" class="table-break-word"><s:property value="draweeBankCode"/></td>
		</tr>
		<tr>
			<td width="5%" class="table-break-word">:42D</td>
			<td width="30%" class="table-break-word"><s:label value="%{getText('label.700XXX.ppdreaweeNameandAddress')}"></s:label><span class="optional">[Optional]</span></td>
			<td width="50%" class="table-break-word1"><s:property value="draweeBankNameAddress"/></td>
		</tr>
		<tr>
			<td width="5%" class="table-break-word">:42M</td>
			<td width="30%" class="table-break-word"><s:label value="%{getText('label.700XXX.ppmixedPaymnetDetails')}"></s:label><span class="optional">[Optional]</span></td>
			<td width="50%" class="table-break-word1"><s:property value="mixedPaymentDetails"/></td>
		</tr>
		<tr>
			<td width="5%" class="table-break-word">:42P</td>
			<td width="30%" class="table-break-word"><s:label value="%{getText('label.700XXX.ppdeferredPaymentDetails')}"></s:label><span class="optional">[Optional]</span></td>
			<td width="50%" class="table-break-word1"><s:property value="deferredPaymentDetails"/></td>
		</tr>
		<tr>
			<td width="5%" class="table-break-word">:43P</td>
			<td width="30%" class="table-break-word"><s:label value="%{getText('label.700XXX.pppartialPayments')}"></s:label><span class="optional">[Optional]</span></td>
			<td width="50%" class="table-break-word"><s:property value="partialShipments"/></td>
		</tr>
		<tr>
			<td width="5%" class="table-break-word">:43T</td>
			<td width="30%" class="table-break-word"><s:label value="%{getText('label.700XXX.pptransshipment')}"></s:label><span class="optional">[Optional]</span></td>
			<td width="50%" class="table-break-word"><s:property value="transhipment"/></td>
		</tr>
		<tr>
			<td width="5%" class="table-break-word">:44A</td>
			<td width="30%" class="table-break-word"><s:label value="%{getText('label.700XXX.ppplaceofDispatch')}"></s:label><span class="optional">[Optional]</span></td>
			<td width="50%" class="table-break-word"><s:property value="initialDispatchPlace"/></td>
		</tr>
		<tr>
			<td width="5%" class="table-break-word">:44E</td>
			<td width="30%" class="table-break-word"><s:label value="%{getText('label.700XXX.ppportofLoading')}"></s:label><span class="optional">[Optional]</span></td>
			<td width="50%" class="table-break-word"><s:property value="goodsLoadingDispatchPlace"/></td>
		</tr>
		<tr>
			<td width="5%" class="table-break-word">:44F</td>
			<td width="30%" class="table-break-word"><s:label value="%{getText('label.700XXX.ppportofDischarge')}"></s:label><span class="optional">[Optional]</span></td>
			<td width="50%" class="table-break-word"><s:property value="goodsDestination"/></td>
		</tr>
		<tr>
			<td width="5%" class="table-break-word">:44B</td>
			<td width="30%" class="table-break-word"><s:label value="%{getText('label.700XXX.ppplaceofFinalDestination')}"></s:label><span class="optional">[Optional]</span></td>
			<td width="50%" class="table-break-word"><s:property value="finalDeliveryPlace"/></td>
		</tr>
		<tr>
			<td width="5%" class="table-break-word">:44C</td>
			<td width="30%" class="table-break-word"><s:label value="%{getText('label.700XXX.pplatestDateofShipment')}"></s:label><span class="optional">[Optional]</span></td>
			<td width="50%" class="table-break-word"><s:date name="latestDateofShipment" format="MM/dd/yyyy" /></td>
		</tr>
		<tr>
			<td width="5%" class="table-break-word">:44D</td>
			<td width="30%" class="table-break-word"><s:label value="%{getText('label.700XXX.ppshipmentPeriod')}"></s:label><span class="optional">[Optional]</span></td>
			<td width="50%" class="table-break-word1"><s:property value="shipmentPeriod"/></td>
		</tr>
		<tr>
			<td width="5%" class="table-break-word">:45A</td>
			<td width="30%" class="table-break-word"><s:label value="%{getText('label.700XXX.ppdescriptionofGoods')}"></s:label><span class="optional">[Optional]</span></td>
			<td width="50%" class="table-break-word1"><s:property value="descriptionofGoods"/></td>
		<tr>
			<td width="5%" class="table-break-word">:46A</td>
			<td width="30%" class="table-break-word"><s:label value="%{getText('label.700XXX.ppdocumentsRequired')}"></s:label><span class="optional">[Optional]</span></td>
			<td width="50%" class="table-break-word1"><s:property value="documentsRequired"/></td>
		</tr>
		<tr>
			<td width="5%" class="table-break-word">:47A</td>
			<td width="30%" class="table-break-word"><s:label value="%{getText('label.700XXX.ppadditinalConditions')}"></s:label><span class="optional">[Optional]</span></td>
			<td width="50%" class="table-break-word1"><s:property value="additionalConditions"/></td>
		</tr>
		<tr>
			<td width="5%" class="table-break-word">:71B</td>
			<td width="30%" class="table-break-word"><s:label value="%{getText('label.700XXX.ppcharges')}"></s:label><span class="optional">[Optional]</span></td>
			<td width="50%" class="table-break-word1"><s:property value="chargeDetails"/></td>
		</tr>
		<tr>
			<td width="5%" class="table-break-word">:48</td>
			<td width="30%" class="table-break-word"><s:label value="%{getText('label.700XXX.ppperiodForPresentaion')}"></s:label><span class="optional">[Optional]</span></td>
			<td width="50%" class="table-break-word1"><s:property value="periodforPresentation"/></td>
		</tr>
		<tr>
			<td width="5%" class="table-break-word">:49</td>
			<td width="30%" class="table-break-word"><s:label cssClass="MandatoryField" value="%{getText('label.700XXX.ppconfirmation')}"></s:label><span class="MandatoryField">[MandatoryField]</span></td>
			<td width="50%" class="table-break-word"><s:property value="confirmationCode"/></td>
		</tr>
		<tr>
			<td width="5%" class="table-break-word">:53a</td>
			<td width="30%" class="table-break-word"><s:label value="%{getText('label.700XXX.ppreimbursing')}"></s:label><span class="optional">[Optional]</span></td>
			<td width="50%" class="table-break-word"><s:property value="reimbursingIdentifier"/></td>
		</tr>
		<tr>
			<td width="5%" class="table-break-word">:53A</td>
			<td width="30%" class="table-break-word"><s:label value="%{getText('label.700XXX.ppreimbursingBankCode')}"></s:label><span class="optional">[Optional]</span></td>
			<td width="50%" class="table-break-word"><s:property value="reimbursingBankCode"/></td>
		</tr>
		<tr>
			<td width="5%" class="table-break-word">:53D</td>
			<td width="30%" class="table-break-word"><s:label value="%{getText('label.700XXX.ppreimbursingNameandAddress')}"></s:label><span class="optional">[Optional]</span></td>
			<td width="50%" class="table-break-word1"><s:property value="reimbursingBankNameAddress"/></td>
		</tr>
		<tr>
			<td width="5%" class="table-break-word">:78</td>
			<td width="30%" class="table-break-word"><s:label value="%{getText('label.700XXX.ppinstructionToPaying')}"></s:label><span class="optional">[Optional]</span></td>
			<td width="50%" class="table-break-word1"><s:property value="instructionstoPayingBank"/></td>
		</tr>
		<tr>
			<td width="5%" class="table-break-word">:57a</td>
			<td width="30%" class="table-break-word"><s:label value="%{getText('label.700XXX.ppadvisingThrough')}"></s:label><span class="optional">[Optional]</span></td>
			<td width="50%" class="table-break-word"><s:property value="advisingIdentifier"/></td>
		</tr>
		<tr>
			<td width="5%" class="table-break-word">:57A</td>
			<td width="30%" class="table-break-word"><s:label value="%{getText('label.700XXX.ppadvisingThroughBankCode')}"></s:label><span class="optional">[Optional]</span></td>
			<td width="50%" class="table-break-word"><s:property value="adviseThroughBankCode"/></td>
		</tr>
		<tr>
			<td width="5%" class="table-break-word">:57B</td>
			<td width="30%" class="table-break-word"><s:label value="%{getText('label.700XXX.ppadvisingThroughBankLocation')}"></s:label><span class="optional">[Optional]</span></td>
			<td width="50%" class="table-break-word"><s:property value="adviseThroughBankLocation"/></td>
		</tr>
		<tr>
			<td width="5%" class="table-break-word">:57D</td>
			<td width="30%" class="table-break-word"><s:label value="%{getText('label.700XXX.ppadvisingThroughBankNameAndAddress')}"></s:label><span class="optional">[Optional]</span></td>
			<td width="50%" class="table-break-word1"><s:property value="adviseThroughBankName"/></td>
		</tr>
		<tr>
			<td width="5%" class="table-break-word">:72</td>
			<td width="30%" class="table-break-word"><s:label value="%{getText('label.700XXX.ppsenderToReceiverInfo')}"></s:label><span class="optional">[Optional]</span></td>
			<td width="50%" class="table-break-word1"><s:property value="SendertoReceiverInformation"/></td>
		</tr>
	</table>	

<div class="clearfix"></div>
	<table width="75%" cellpadding="0" cellspacing="0" border="0">
		<tr>
			<td align="lesf">
				<input type="button" value="Export Excel" onclick="callExpToExcel()">
				<input type="button" value="Print" onclick="generatePdfLcOpen()">
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