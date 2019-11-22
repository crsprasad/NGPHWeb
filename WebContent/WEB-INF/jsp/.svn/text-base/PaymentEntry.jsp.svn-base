
<%@page import="com.opensymphony.xwork2.ActionContext"%>
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<link href="SpryAssets/SpryCollapsiblePanel.css" rel="stylesheet"
	type="text/css" />
<script src="SpryAssets/SpryCollapsiblePanel.js" type="text/javascript"></script>
<script type="text/javascript">
	function callGroupOne(actionId) {

		// alert(actionId);
		var objMandatoryDiv = document.getElementById(actionId);
		//alert(objMandatoryDiv);

		var displayMode = objMandatoryDiv.style.display;
		//alert(displayMode);
		if (displayMode == '') {
			objMandatoryDiv.style.display = 'none';
			//alert("come in ");
		}

		var obj = document.getElementById("custTxnReference");
		//alert(obj);
		//obj.focus();
		if (displayMode == 'none') {

			objMandatoryDiv.style.display = '';
		}

	}
	function SaveRepairPopup() {

		window.open(
				"<s:url action='showRepairPopup' windowState='EXCLUSIVE' />",
				'mywindow', 'top=500,left=250,width=400,height=200');

	}

	function CallPaymentRepairCancel() {

		document.forms[0].action = "loadPaymentMessageQueue";
		document.forms[0].submit();

	}
</script>
<sx:head parseContent="true" />
<s:head />
<div id="contentPane">
<div id="contentPaneTop"></div>
<div id="contentPaneFluid">
<div id="contentPaneInternal"><span class="crumbs"><a
	href="#">Outbound Payments</a>&nbsp;>&nbsp;Payment Entry</span></div>
<div id="content">
<h1>Payment Entry</h1>
<s:hidden id="repairComments" name="repairComments"></s:hidden> <s:form
	action="paymentAction" method="post">
	<!--<s:if test="hasFieldErrors()">
		<div class="errorMsg"><span class="errorMsgInline"><s:iterator
			value="getFieldErrors().entrySet()" var="entry">
			<s:iterator value="#entry.value">
				<s:property value="%{top}" />
				<br />
			</s:iterator>
		</s:iterator></span></div>
	</s:if> -->
	<!--Error Msg Div Starts --><div><s:if test="hasFieldErrors()">
				<div class="errorMsg"><span class="errorMsgInline"><s:actionerror /><s:fielderror /></span></div>
			</s:if></div><!--Error Msg Div Ends -->
	<!-- Collapsible Panels Start-->
	<div id="CollapsiblePanel1" class="CollapsiblePanel">
	<div class="CollapsiblePanelTab"><!--  <img src="images/openPanel.png" width="9" height="7" alt="openPanel" />-->&nbsp;Basic</div>
	<div class="CollapsiblePanelContent">
	<div><!-- Group One Div Starts --> <s:div id="main"
		cssClass="dataGrid" style="width:100%;">
		<table width="100%" cellpadding="3" cellspacing="1" border="0"
			bgcolor="#ffffff">
			<s:set name="paymentQueue" value="%{#session.paymentStatusVal}" />
			<tr>
				<td class="textRight" width="25%"><s:label
					value="%{getText('label.msgChnlType')}"></s:label>:<span
					class="mandatory">*</span></td>
				<td class="text" width="15%"><s:if
					test="#paymentQueue =='AWAITING_REPAIR'">
					<s:textfield name="msgChnlType" id="msgChnlType" required="true"
						cssClass="text"
						readonly="%{getText('label.msgChnlType.Readonly')}"
						value="%{ngphCanonical.msgChnlType}"></s:textfield>
				</s:if> <s:else>
					<s:textfield name="msgChnlType" id="msgChnlType" cssClass="text"
						value="%{ngphCanonical.msgChnlType}" required="true"></s:textfield>
				</s:else></td>
				<td class="text" width="10%">&nbsp;</td>
				<td class="textRight" width="25%"><s:label
					value="%{getText('label.dstMsgType')}"></s:label>:<span
					class="mandatory">*</span></td>
				<td class="text" width="15%"><s:if
					test="#paymentQueue =='AWAITING_REPAIR'">
					<s:textfield name="dstMsgType" id="dstMsgType" cssClass="text"
						readonly="%{getText('label.dstMsgType.Readonly')}"
						value="%{ngphCanonical.dstMsgType}"></s:textfield>
				</s:if> <s:else>
					<s:textfield name="dstMsgType" id="dstMsgType" required="true"
						cssClass="text" value="%{ngphCanonical.dstMsgType}"></s:textfield>
				</s:else></td>
				<td class="text" width="10%">&nbsp;</td>
			</tr>
			<tr>
				<td class="textRight"><s:label
					value="%{getText('label.dstMsgSubType')}"></s:label>:<span
					class="mandatory">*</span></td>
				<td class="text"><s:if test="#paymentQueue =='AWAITING_REPAIR'">
					<s:textfield name="dstMsgSubType" id="dstMsgSubType"
						cssClass="text"
						readonly="%{getText('label.dstMsgSubType.Readonly')}"
						value="%{ngphCanonical.dstMsgSubType}"></s:textfield>
				</s:if> <s:else>
					<s:textfield name="dstMsgSubType" id="dstMsgSubType"
						cssClass="text" value="%{ngphCanonical.dstMsgSubType}"></s:textfield>
				</s:else></td>
				<td class="text">&nbsp;</td>
				<td class="textRight"><s:label
					value="%{getText('label.AccountWithInstitution')}"></s:label>:<span
					class="mandatory">*</span></td>
				<td class="text"><s:if test="#paymentQueue =='AWAITING_REPAIR'">
					<s:textfield name="accountWithInstitution" cssClass="text"
						readonly="%{getText('label.AccountWithInstitution.Readonly')}"
						id="accountWithInstitution"
						value="%{ngphCanonical.accountWithInstitution}"></s:textfield>

				</s:if> <s:else>
					<s:textfield name="accountWithInstitution"
						id="accountWithInstitution" cssClass="text"
						value="%{ngphCanonical.accountWithInstitution}"></s:textfield>
				</s:else></td>
				<td class="text">&nbsp;</td>
			</tr>
			<tr>
				<td class="textRight"><s:label
					value="%{getText('label.TransacRef')}"></s:label>:<span
					class="mandatory">*</span></td>
				<td class="text"><s:if test="#paymentQueue =='AWAITING_REPAIR'">
					<s:textfield name="txnReference" cssClass="text" id="txnReference"
						required="true" readonly="%{getText('label.TransacRef.Readonly')}"
						value="%{ngphCanonical.txnReference}"></s:textfield>
				</s:if> <s:else>
					<s:textfield name="txnReference" cssClass="text"
						cssStyle="css/ngph.css" id="txnReference" required="true"
						value="%{ngphCanonical.txnReference}"></s:textfield>
				</s:else></td>
				<td class="text">&nbsp;</td>
				<td class="textRight"><s:label
					value="%{getText('label.OrderingCustomerAccount')}"></s:label>:<span
					class="mandatory">*</span></td>
				<td class="text"><s:if test="#paymentQueue =='AWAITING_REPAIR'">
					<s:textfield name="orderingCustAccount" id="orderingCustAccount"
						cssClass="text"
						readonly="%{getText('label.OrderingCustomerAccount.Readonly')}"
						value="%{ngphCanonical.orderingCustAccount}" required="true"></s:textfield>
				</s:if> <s:else>
					<s:textfield name="orderingCustAccount" id="orderingCustAccount"
						cssClass="text" value="%{ngphCanonical.orderingCustAccount}"
						required="true"></s:textfield>
				</s:else></td>
				<td class="text">&nbsp;</td>
			</tr>
			<tr>
				<td class="textRight"><s:label
					value="%{getText('label.SenderPriority')}"></s:label>:<span
					class="mandatory">*</span></td>
				<td class="text"><s:textfield name="sndrPymtPriority"
					id="sndrPymtPriority" cssClass="text"
					value="%{ngphCanonical.sndrPymtPriority}"></s:textfield></td>
				<td class="text">&nbsp;</td>
				<td class="textRight"><s:label
					value="%{getText('label.BeneficiaryParty')}"></s:label>:<span
					class="mandatory">*</span></td>
				<td class="text"><s:if test="#paymentQueue =='AWAITING_REPAIR'">
					<s:textfield name="beneficiaryCustomerName" cssClass="text"
						id="beneficiaryCustomerName" required="true"
						readonly="%{getText('label.BeneficiaryParty.Readonly')}"
						value="%{ngphCanonical.beneficiaryCustomerName}"></s:textfield>
				</s:if><s:else>
					<s:textfield name="beneficiaryCustomerName" cssClass="text"
						id="beneficiaryCustomerName" required="true"
						value="%{ngphCanonical.beneficiaryCustomerName}"></s:textfield>
				</s:else></td>
				<td class="text">&nbsp;</td>
			</tr>
			<tr>
				<td class="textRight"><s:label
					value="%{getText('label.ValueDate')}"></s:label>:<span
					class="mandatory">*</span></td>
				<td class="text"><s:textfield name="valueDate" cssClass="text"
					id="valueDate" required="true"></s:textfield></td>
				<td class="text">&nbsp;</td>
				<td class="textRight"><s:label
					value="%{getText('label.ultimateDebtorName')}"></s:label>:<span
					class="mandatory">*</span></td>
				<td class="text"><s:if test="#paymentQueue =='AWAITING_REPAIR'">
					<s:textfield name="ultimateDebtorName" cssClass="text"
						id="ultimateDebtorName" required="true"
						value="%{ngphCanonical.ultimateDebtorName}"
						readonly="%{getText('label.ultimateDebtorName.Readonly')}"></s:textfield>
				</s:if> <s:else>
					<s:textfield name="ultimateDebtorName" cssClass="text"
						id="ultimateDebtorName" required="true"
						value="%{ngphCanonical.ultimateDebtorName}"></s:textfield>
				</s:else></td>
				<td class="text">&nbsp;</td>
			</tr>
			<tr>
				<td class="textRight"><s:label
					value="%{getText('label.Currency')}"></s:label>:<span
					class="mandatory">*</span></td>
				<td class="text"><s:if test="#paymentQueue =='AWAITING_REPAIR'">
					<s:textfield name="msgCurrency" cssClass="text" id="msgCurrency"
						required="true" readonly="%{getText('label.Currency.Readonly')}"
						value="%{ngphCanonical.msgCurrency}"></s:textfield>
				</s:if> <s:else>
					<s:textfield name="msgCurrency" cssClass="text" id="msgCurrency"
						required="true" value="%{ngphCanonical.msgCurrency}"></s:textfield>
				</s:else> <input type="button" class="btn" value="More..." /></td>
				<td class="text">&nbsp;</td>
				<td class="textRight"><s:label
					value="%{getText('label.BeneficiaryAccount')}"></s:label>:<span
					class="mandatory">*</span></td>
				<td class="text"><s:if test="#paymentQueue =='AWAITING_REPAIR'">
					<s:textfield name="beneficiaryCustAcct" cssClass="text"
						id="beneficiaryCustAcct" required="true"
						value="%{ngphCanonical.beneficiaryCustAcct}"
						readonly="%{getText('label.BeneficiaryAccount.Readonly')}"></s:textfield>
				</s:if> <s:else>
					<s:textfield name="beneficiaryCustAcct" cssClass="text"
						id="beneficiaryCustAcct" required="true"
						value="%{ngphCanonical.beneficiaryCustAcct}"></s:textfield>
				</s:else> <input type="button" class="btn" value="More..." /></td>
				<td class="text">&nbsp;</td>
			</tr>
			<tr>
				<td class="textRight"><s:label
					value="%{getText('label.OrderingParty')}"></s:label>:<span
					class="mandatory">*</span></td>
				<td class="text"><s:textfield name="orderingCustomerName"
					cssClass="text" id="orderingCustomerName" required="true"
					value="%{ngphCanonical.orderingCustomerName}"></s:textfield> <input
					type="button" class="btn" value="More..." /></td>
				<td class="text">&nbsp;</td>
				<td class="textRight"><s:label
					value="%{getText('label.ChargesBearer')}"></s:label>:<span
					class="mandatory">*</span></td>
				<td class="text"><s:textfield name="chargeBearer"
					cssClass="text" id="chargeBearer" required="true"
					value="%{ngphCanonical.chargeBearer}"></s:textfield> <input
					type="button" class="btn" value="More..." /></td>
				<td class="text">&nbsp;</td>
			</tr>
			<tr>
				<td class="textRight"><s:label
					value="%{getText('label.Amount')}"></s:label>:<span
					class="mandatory">*</span></td>
				<td class="text"><s:if test="#paymentQueue =='AWAITING_REPAIR'">
					<s:textfield name="msgAmount" cssClass="text" id="msgAmount"
						readonly="%{getText('label.Amount.Readonly')}" required="true"
						value="%{ngphCanonical.msgAmount}"></s:textfield>
				</s:if> <s:else>
					<s:textfield name="msgAmount" cssClass="text" id="msgAmount"
						required="true" value="%{ngphCanonical.msgAmount}"></s:textfield>
				</s:else></td>
				<td class="text">&nbsp;</td>
				<td class="textRight"><s:label
					value="%{getText('label.ReceiverBankCode')}"></s:label>:<span
					class="mandatory">*</span></td>
				<td class="text"><s:if test="#paymentQueue =='AWAITING_REPAIR'">
					<s:textfield name="receiverBank" id="receiverBank" cssClass="text"
						readonly="%{getText('label.ReceiverBankCode.Readonly')}"
						value="%{ngphCanonical.receiverBank}"></s:textfield>
				</s:if> <s:else>
					<s:textfield name="receiverBank" id="receiverBank" cssClass="text"
						value="%{ngphCanonical.receiverBank}"></s:textfield>
				</s:else></td>
				<td class="text">&nbsp;</td>
			</tr>
			<tr>
				<td class="textRight"><s:label
					value="%{getText('label.InstructedCurrency')}"></s:label>:<span
					class="mandatory">*</span></td>
				<td class="text"><s:if test="#paymentQueue =='AWAITING_REPAIR'">
					<s:textfield name="instructedCurrency" id="instructedCurrency"
						cssClass="text"
						readonly="%{getText('label.InstructedCurrency.Readonly')}"
						value="%{ngphCanonical.instructedCurrency}"></s:textfield>
				</s:if> <s:else>
					<s:textfield name="instructedCurrency" id="instructedCurrency"
						cssClass="text" value="%{ngphCanonical.instructedCurrency}"></s:textfield>
				</s:else></td>
				<td class="text">&nbsp;</td>
				<td class="textRight"><s:label
					value="%{getText('label.InstructedAmount')}"></s:label>:<span
					class="mandatory">*</span></td>
				<td class="text"><s:if test="#paymentQueue =='AWAITING_REPAIR'">
					<s:textfield name="instructedAmount" id="instructedAmount"
						cssClass="text"
						readonly="%{getText('label.InstructedAmount.Readonly')}"
						value="%{ngphCanonical.instructedAmount}"></s:textfield>
				</s:if> <s:else>
					<s:textfield name="instructedAmount" id="instructedAmount"
						cssClass="text" value="%{ngphCanonical.instructedAmount}"
						required="true"></s:textfield>
				</s:else></td>
				<td class="text">&nbsp;</td>
			</tr>
		</table>
	</s:div> <!-- Group One Div Ends --></div>
	</div>
	</div>
	<br />
	<div id="CollapsiblePanel2" class="CollapsiblePanel">
	<div class="CollapsiblePanelTab"><!--  <img src="images/openPanel.png" width="9" height="7" alt="openPanel" />-->&nbsp;Parties
	& Clearing</div>
	<div class="CollapsiblePanelContent">
	<div><!-- Group Two Div Starts --> <s:div id="mandatory"
		cssClass="dataGrid" style="width:100%;">
		<table width="100%" cellpadding="5" cellspacing="1" border="0"
			bgcolor="#ffffff">
			<tr>
				<td class="textRight" width="25%"><s:label
					value="%{getText('label.CustomerTransactionReference')}"></s:label>:&nbsp;
				</td>
				<td class="text" width="15%"><s:textfield
					name="custTxnReference" id="custTxnReference" cssClass="text"
					value="%{ngphCanonical.custTxnReference}"></s:textfield></td>
				<td class="text" width="10%">&nbsp;</td>
				<td class="textRight" width="25%"><s:label
					value="%{getText('label.RegulatoryReportingAmount')}"></s:label>:&nbsp;
				</td>
				<td class="text" width="15%"><s:textfield
					name="regulatoryReportAmount" id="regulatoryReportAmount"
					cssClass="text" value="%{ngphCanonical.regulatoryReportAmount}"></s:textfield>
				</td>
				<td class="text" width="10%">&nbsp;</td>
			</tr>
			<tr>
				<td class="textRight"><s:label
					value="%{getText('label.ultimateCreditorAddress')}"></s:label>:&nbsp;</td>
				<td class="text"><s:textfield name="ultimateCreditorAddress"
					cssClass="text" id="ultimateCreditorAddress"
					value="%{ngphCanonical.ultimateCreditorAddress}"></s:textfield></td>
				<td class="text">&nbsp;</td>
				<td class="textRight"><s:label
					value="%{getText('label.ultimateCreditorID')}"></s:label>:&nbsp;</td>
				<td class="text"><s:textfield name="ultimateCreditorID"
					cssClass="text" id="ultimateCreditorID" required="true"
					value="%{ngphCanonical.ultimateCreditorID}"></s:textfield></td>
				<td class="text">&nbsp;</td>
			</tr>
			<tr>
				<td class="textRight"><s:label
					value="%{getText('label.ultimateCreditorCtry')}"></s:label>:&nbsp;</td>
				<td class="text"><s:textfield name="ultimateCreditorCtry"
					cssClass="text" id="ultimateCreditorCtry" required="true"
					value="%{ngphCanonical.ultimateCreditorCtry}"></s:textfield></td>
				<td class="text">&nbsp;</td>
				<td class="textRight"><s:label
					value="%{getText('label.ultimateCreditorCtctDtls')}"></s:label>:&nbsp;
				</td>
				<td class="text"><s:textfield name="ultimateCreditorCtctDtls"
					cssClass="text" id="ultimateCreditorCtctDtls" required="true"
					value="%{ngphCanonical.ultimateCreditorCtctDtls}"></s:textfield></td>
				<td class="text">&nbsp;</td>
			</tr>
			<tr>
				<td class="textRight"><s:label
					value="%{getText('label.orderingCustomerAddress')}"></s:label>:&nbsp;</td>
				<td class="text"><s:textfield name="orderingCustomerAddress"
					cssClass="text" id="orderingCustomerAddress"
					value="%{ngphCanonical.orderingCustomerAddress}"></s:textfield></td>
				<td class="text">&nbsp;</td>
				<td class="textRight"><s:label
					value="%{getText('label.orderingCustomerID')}"></s:label>:&nbsp;</td>
				<td class="text"><s:textfield name="orderingCustomerID"
					cssClass="text" id="orderingCustomerID"
					value="%{ngphCanonical.orderingCustomerID}"></s:textfield></td>
				<td class="text">&nbsp;</td>
			</tr>
			<tr>
				<td class="textRight"><s:label
					value="%{getText('label.orderingCustomerCtry')}"></s:label>:&nbsp;</td>
				<td class="text"><s:textfield name="orderingCustomerCtry"
					cssClass="text" id="orderingCustomerCtry"
					value="%{ngphCanonical.orderingCustomerCtry}"></s:textfield></td>
				<td class="text">&nbsp;</td>
				<td class="textRight"><s:label
					value="%{getText('label.orderingCustomerCtctDtls')}"></s:label>:&nbsp;
				</td>
				<td class="text"><s:textfield name="orderingCustomerCtctDtls"
					cssClass="text" id="orderingCustomerCtctDtls"
					value="%{ngphCanonical.orderingCustomerCtctDtls}"></s:textfield></td>
				<td class="text">&nbsp;</td>
			</tr>
			<tr>
				<td class="textRight"><s:label
					value="%{getText('label.RegulatoryReportingCurrency')}"></s:label>:&nbsp;</td>
				<td class="text"><s:textfield name="regulatoryReportCurrency"
					cssClass="text" id="regulatoryReportCurrency"
					value="%{ngphCanonical.regulatoryReportCurrency}"></s:textfield></td>
				<td class="text">&nbsp;</td>
				<td class="textRight"><s:label
					value="%{getText('label.ultimateDebtorAddress')}"></s:label>:&nbsp;
				</td>
				<td class="text"><s:if test="#paymentQueue =='AWAITING_REPAIR'">
					<s:textfield name="ultimateDebtorAddress" cssClass="text"
						id="ultimateDebtorAddress" required="true"
						value="%{ngphCanonical.ultimateDebtorAddress}"
						readonly="%{getText('label.ultimateDebtorAddress.Readonly')}"></s:textfield>
				</s:if> <s:else>
					<s:textfield name="ultimateDebtorAddress" cssClass="text"
						id="ultimateDebtorAddress" required="true"
						value="%{ngphCanonical.ultimateDebtorAddress}"></s:textfield>
				</s:else></td>
				<td class="text">&nbsp;</td>
			</tr>
			<tr>
				<td class="textRight"><s:label
					value="%{getText('label.ultimateDebtorID')}"></s:label>:&nbsp;</td>
				<td class="text"><s:if test="#paymentQueue =='AWAITING_REPAIR'">
					<s:textfield name="ultimateDebtorID" cssClass="text"
						id="ultimateDebtorID" required="true"
						value="%{ngphCanonical.ultimateDebtorID}"
						readonly="%{getText('label.ultimateDebtorID.Readonly')}"></s:textfield>
				</s:if> <s:else>
					<s:textfield name="ultimateDebtorID" cssClass="text"
						id="ultimateDebtorID" required="true"
						value="%{ngphCanonical.ultimateDebtorID}"></s:textfield>
				</s:else></td>
				<td class="text">&nbsp;</td>
				<td class="textRight"><s:label
					value="%{getText('label.ultimateDebtorCtry')}"></s:label>:&nbsp;</td>
				<td class="text"><s:if test="#paymentQueue =='AWAITING_REPAIR'">
					<s:textfield name="ultimateDebtorCtry" cssClass="text"
						id="ultimateDebtorCtry" required="true"
						value="%{ngphCanonical.ultimateDebtorCtry}"
						readonly="%{getText('label.ultimateDebtorCtry.Readonly')}"></s:textfield>
				</s:if> <s:else>
					<s:textfield name="ultimateDebtorCtry" cssClass="text"
						id="ultimateDebtorCtry" required="true"
						value="%{ngphCanonical.ultimateDebtorCtry}"></s:textfield>
				</s:else></td>
				<td class="text">&nbsp;</td>
			</tr>
			<tr>
				<td class="textRight"><s:label
					value="%{getText('label.ultimateDebtorCtctDtls')}"></s:label>:&nbsp;</td>
				<td class="text"><s:if test="#paymentQueue =='AWAITING_REPAIR'">
					<s:textfield name="ultimateDebtorCtctDtls" cssClass="text"
						id="ultimateDebtorCtctDtls" required="true"
						value="%{ngphCanonical.ultimateDebtorCtctDtls}"
						readonly="%{getText('label.ultimateDebtorCtctDtls.Readonly')}"></s:textfield>
				</s:if> <s:else>
					<s:textfield name="ultimateDebtorCtctDtls" cssClass="text"
						id="ultimateDebtorCtctDtls" required="true"
						value="%{ngphCanonical.ultimateDebtorCtctDtls}"></s:textfield>
				</s:else></td>
				<td class="text">&nbsp;</td>
				<td class="textRight"><s:label
					value="%{getText('label.initiatingPartyName')}"></s:label>:&nbsp;</td>
				<td class="text"><s:if test="#paymentQueue =='AWAITING_REPAIR'">
					<s:textfield name="initiatingPartyName" cssClass="text"
						id="initiatingPartyName" required="true"
						value="%{ngphCanonical.initiatingPartyName}"
						readonly="%{getText('label.initiatingPartyName.Readonly')}"></s:textfield>
				</s:if> <s:else>
					<s:textfield name="initiatingPartyName" cssClass="text"
						id="initiatingPartyName" required="true"
						value="%{ngphCanonical.initiatingPartyName}"></s:textfield>
				</s:else></td>
				<td class="text">&nbsp;</td>
			</tr>
			<tr>
				<td class="textRight"><s:label
					value="%{getText('label.initiatingPartyAddress')}"></s:label>:&nbsp;</td>
				<td class="text"><s:if test="#paymentQueue =='AWAITING_REPAIR'">
					<s:textfield name="initiatingPartyAddress" cssClass="text"
						id="initiatingPartyAddress" required="true"
						value="%{ngphCanonical.initiatingPartyAddress}"
						readonly="%{getText('label.initiatingPartyAddress.Readonly')}"></s:textfield>
				</s:if> <s:else>
					<s:textfield name="initiatingPartyAddress" cssClass="text"
						id="initiatingPartyAddress" required="true"
						value="%{ngphCanonical.initiatingPartyAddress}"></s:textfield>
				</s:else></td>
				<td class="text">&nbsp;</td>
				<td class="textRight"><s:label
					value="%{getText('label.initiatingPartyID')}"></s:label>:&nbsp;</td>
				<td class="text"><s:if test="#paymentQueue =='AWAITING_REPAIR'">
					<s:textfield name="initiatingPartyID" cssClass="text"
						id="initiatingPartyID" required="true"
						value="%{ngphCanonical.initiatingPartyID}"
						readonly="%{getText('label.initiatingPartyID.Readonly')}"></s:textfield>
				</s:if> <s:else>
					<s:textfield name="initiatingPartyID" cssClass="text"
						id="initiatingPartyID" required="true"
						value="%{ngphCanonical.initiatingPartyID}"></s:textfield>
				</s:else></td>
				<td class="text">&nbsp;</td>
			</tr>
			<tr>
				<td class="textRight"><s:label
					value="%{getText('label.initiatingPartyCtry')}"></s:label>:&nbsp;</td>
				<td class="text"><s:if test="#paymentQueue =='AWAITING_REPAIR'">
					<s:textfield name="initiatingPartyCtry" cssClass="text"
						id="initiatingPartyCtry" required="true"
						value="%{ngphCanonical.initiatingPartyCtry}"
						readonly="%{getText('label.initiatingPartyCtry.Readonly')}"></s:textfield>
				</s:if> <s:else>
					<s:textfield name="initiatingPartyCtry" cssClass="text"
						id="initiatingPartyCtry" required="true"
						value="%{ngphCanonical.initiatingPartyCtry}"></s:textfield>
				</s:else></td>
				<td class="text">&nbsp;</td>
				<td class="textRight"><s:label
					value="%{getText('label.initiatingPartyCtctDtls')}"></s:label>:&nbsp;</td>
				<td class="text"><s:if test="#paymentQueue =='AWAITING_REPAIR'">
					<s:textfield name="initiatingPartyCtctDtls" cssClass="text"
						id="initiatingPartyCtctDtls" required="true"
						value="%{ngphCanonical.initiatingPartyCtctDtls}"
						readonly="%{getText('label.initiatingPartyCtctDtls.Readonly')}"></s:textfield>
				</s:if> <s:else>
					<s:textfield name="initiatingPartyCtctDtls" cssClass="text"
						id="initiatingPartyCtctDtls" required="true"
						value="%{ngphCanonical.initiatingPartyCtctDtls}"></s:textfield>
				</s:else></td>
				<td class="text">&nbsp;</td>
			</tr>
			<tr>
				<td class="textRight"><s:label
					value="%{getText('label.beneficiaryCustomerAddress')}"></s:label>:&nbsp;</td>
				<td class="text"><s:if test="#paymentQueue =='AWAITING_REPAIR'">
					<s:textfield name="beneficiaryCustomerAddress" cssClass="text"
						id="beneficiaryCustomerAddress" required="true"
						readonly="%{getText('label.beneficiaryCustomerAddress.Readonly')}"
						value="%{ngphCanonical.beneficiaryCustomerAddress}"></s:textfield>
				</s:if><s:else>
					<s:textfield name="beneficiaryCustomerAddress" cssClass="text"
						id="beneficiaryCustomerAddress" required="true"
						value="%{ngphCanonical.beneficiaryCustomerAddress}"></s:textfield>
				</s:else></td>
				<td class="text">&nbsp;</td>

				<td class="textRight"><s:label
					value="%{getText('label.beneficiaryCustomerID')}"></s:label>:&nbsp;</td>
				<td class="text"><s:if test="#paymentQueue =='AWAITING_REPAIR'">
					<s:textfield name="beneficiaryCustomerID" cssClass="text"
						id="beneficiaryCustomerID" required="true"
						readonly="%{getText('label.beneficiaryCustomerID.Readonly')}"
						value="%{ngphCanonical.beneficiaryCustomerID}"></s:textfield>
				</s:if><s:else>
					<s:textfield name="beneficiaryCustomerID" cssClass="text"
						id="beneficiaryCustomerID" required="true"
						value="%{ngphCanonical.beneficiaryCustomerID}"></s:textfield>
				</s:else></td>
				<td class="text">&nbsp;</td>
			</tr>
			<tr>

				<td class="textRight"><s:label
					value="%{getText('label.beneficiaryCustomerCtry')}"></s:label>:&nbsp;</td>
				<td class="text"><s:if test="#paymentQueue =='AWAITING_REPAIR'">
					<s:textfield name="beneficiaryCustomerCtry" cssClass="text"
						id="beneficiaryCustomerCtry" required="true"
						readonly="%{getText('label.beneficiaryCustomerCtry.Readonly')}"
						value="%{ngphCanonical.beneficiaryCustomerCtry}"></s:textfield>
				</s:if><s:else>
					<s:textfield name="beneficiaryCustomerCtry" cssClass="text"
						id="beneficiaryCustomerCtry" required="true"
						value="%{ngphCanonical.beneficiaryCustomerCtry}"></s:textfield>
				</s:else></td>
				<td class="text">&nbsp;</td>
				<td class="textRight"><s:label
					value="%{getText('label.beneficiaryCustomerCtctDtls')}"></s:label>:&nbsp;</td>
				<td class="text"><s:if test="#paymentQueue =='AWAITING_REPAIR'">
					<s:textfield name="beneficiaryCustomerCtctDtls" cssClass="text"
						id="beneficiaryCustomerCtctDtls" required="true"
						readonly="%{getText('label.beneficiaryCustomerCtctDtls.Readonly')}"
						value="%{ngphCanonical.beneficiaryCustomerCtctDtls}"></s:textfield>
				</s:if><s:else>
					<s:textfield name="beneficiaryCustomerCtctDtls" cssClass="text"
						id="beneficiaryCustomerCtctDtls" required="true"
						value="%{ngphCanonical.beneficiaryCustomerCtctDtls}"></s:textfield>
				</s:else></td>
				<td class="text">&nbsp;</td>
			</tr>
			<tr>
				<td class="textRight"><s:label
					value="%{getText('label.UltimateCreditor')}"></s:label>:&nbsp;</td>
				<td class="text"><s:textfield name="ultimateCreditorName"
					cssClass="text" id="ultimateCreditorName" required="true"
					value="%{ngphCanonical.ultimateCreditorName}"></s:textfield> <input
					type="button" class="btn" value="More..." /></td>
				<td class="text">&nbsp;</td>
				<td class="textRight"><s:label
					value="%{getText('label.ServiceLevelCode')}"></s:label>:&nbsp;</td>
				<td class="text"><s:textfield name="svcLevelCode"
					cssClass="text" id="svcLevelCode"
					value="%{ngphCanonical.svcLevelCode}"></s:textfield></td>
				<td class="text">&nbsp;</td>
			</tr>
			<tr>
				<td class="textRight"><s:label
					value="%{getText('label.SenderTransactionReference')}"></s:label>:&nbsp;</td>

				<td class="text"><s:if test="#paymentQueue =='AWAITING_REPAIR'">
					<s:textfield name="sndrTxnId" id="sndrTxnId" cssClass="text"
						value="%{ngphCanonical.sndrTxnId}"></s:textfield>
				</s:if> <s:else>
					<s:textfield name="sndrTxnId" id="sndrTxnId" cssClass="text"
						value="%{ngphCanonical.sndrTxnId}"></s:textfield>
				</s:else></td>

				<td class="text">&nbsp;</td>
				<td class="textRight"><s:label
					value="%{getText('label.RegulatoryInformation')}"></s:label>:&nbsp;
				</td>
				<td class="text"><s:textfield name="regulatoryInformation"
					id="regulatoryInformation" cssClass="text"
					value="%{ngphCanonical.regulatoryInformation}"></s:textfield></td>
				<td class="text">&nbsp;</td>
			</tr>
			<tr>
				<td class="textRight"><s:label
					value="%{getText('label.ClearingSystemReference')}"></s:label>:&nbsp;
				</td>
				<td class="text"><s:if test="#paymentQueue =='AWAITING_REPAIR'">
					<s:textfield name="clrgSysReference" id="clrgSysReference"
						cssClass="text" value="%{ngphCanonical.clrgSysReference}"></s:textfield>
				</s:if> <s:else>
					<s:textfield name="clrgSysReference" id="clrgSysReference"
						cssClass="text" value="%{ngphCanonical.clrgSysReference}"></s:textfield>
				</s:else></td>
				<td class="text">&nbsp;</td>
				<td class="textRight"><s:label
					value="%{getText('label.RemittanceIdentification')}"></s:label>:&nbsp;
				</td>
				<td class="text"><s:textfield name="initiatorRemitReference"
					id="initiatorRemitReference" cssClass="text"
					value="%{ngphCanonical.initiatorRemitReference}"></s:textfield>
				<td class="text">&nbsp;</td>
			</tr>
		</table>
	</s:div> <!-- Group Two Div Ends --></div>
	</div>
	</div>
	<br />
	<div id="CollapsiblePanel3" class="CollapsiblePanel">
	<div class="CollapsiblePanelTab"><!--  <img src="images/openPanel.png" width="9" height="7" alt="openPanel" />-->&nbsp;Remittance
	& Agent Instructions</div>
	<div class="CollapsiblePanelContent">
	<div><!-- Group Three Div Starts --> <s:div id="register"
		cssClass="dataGrid" style="width:100%;">
		<table width="100%" cellpadding="5" cellspacing="1" border="0"
			bgcolor="#ffffff">
			<tr>
				<td class="textRight" width="25%"><s:label
					value="%{getText('label.RelatedReference')}"></s:label>:&nbsp;</td>
				<td class="text" width="15%"><s:textfield name="relReference"
					id="relReference" cssClass="text"
					value="%{ngphCanonical.relReference}"></s:textfield></td>
				<td width="10%">&nbsp;</td>
				<td class="textRight" width="25%"><s:label
					value="%{getText('label.LocalInstrumentInformation')}"></s:label>:&nbsp;
				</td>
				<td class="text" width="15%"><s:textfield
					name="lclInstProperitary" id="lclInstProperitary" cssClass="text"
					value="%{ngphCanonical.lclInstProperitary}"></s:textfield></td>
				<td width="10%">&nbsp;</td>
			</tr>
			<tr>
				<td class="textRight"><s:label
					value="%{getText('label.initiatorRemitAdviceMethod')}"></s:label>:&nbsp;
				</td>
				<td class="text"><s:textfield name="initiatorRemitAdviceMethod"
					id="initiatorRemitAdviceMethod" cssClass="text"
					value="%{ngphCanonical.initiatorRemitAdviceMethod}"></s:textfield>
				</td>
				<td class="text">&nbsp;</td>
				<td class="textRight"><s:label
					value="%{getText('label.PurposeCode')}"></s:label>:&nbsp;</td>
				<td class="text"><s:textfield name="msgPurposeCode"
					id="msgPurposeCode" cssClass="text"
					value="%{ngphCanonical.msgPurposeCode}"></s:textfield></td>
				<td class="text">&nbsp;</td>
			</tr>
			<tr>
				<td class="textRight"><s:label
					value="%{getText('label.ClearingChannel')}"></s:label>:&nbsp;</td>
				<td class="text"><s:if test="#paymentQueue =='AWAITING_REPAIR'">
					<s:textfield name="clrgChannel" id="clrgChannel" cssClass="text"
						value="%{ngphCanonical.clrgChannel}"></s:textfield>
				</s:if> <s:else>
					<s:textfield name="clrgChannel" id="clrgChannel" cssClass="text"
						value="%{ngphCanonical.clrgChannel}"></s:textfield>
				</s:else></td>
				<td class="text">&nbsp;</td>
				<td class="textRight"><s:label
					value="%{getText('label.RemittanceLocationeMail')}"></s:label>:&nbsp;
				</td>
				<td class="text"><s:textfield name="remitInfoEmail"
					id="remitInfoEmail" cssClass="text"
					value="%{ngphCanonical.remitInfoEmail}"></s:textfield></td>
				<td class="text">&nbsp;</td>
			</tr>
			<tr>
				<td class="textRight"><s:label
					value="%{getText('label.InstructionsforCreditorAgent')}"></s:label>:&nbsp;</td>
				<td class="text"><s:textfield
					name="instructionsForCrdtrAgtText" id="instructionsForCrdtrAgtText"
					cssClass="text"
					value="%{ngphCanonical.instructionsForCrdtrAgtText}"></s:textfield>
				</td>
				<td class="text">&nbsp;</td>
				<td class="textRight"><s:label
					value="%{getText('label.RemittanceReceiverName')}"></s:label>:&nbsp;
				</td>
				<td class="text"><s:textfield name="remitReceivingPartyName"
					id="remitReceivingPartyName" cssClass="text"
					value="%{ngphCanonical.remitReceivingPartyName}"></s:textfield></td>
				<td class="text">&nbsp;</td>
			</tr>
			<tr>
				<td class="textRight"><s:label
					value="%{getText('label.InstructionsCodeforAgent')}"></s:label>:&nbsp;</td>
				<td class="text"><s:textfield name="instructionsForNextAgtCode"
					cssClass="text" id="instructionsForNextAgtCode"
					value="%{ngphCanonical.instructionsForNextAgtCode}"></s:textfield>
				</td>
				<td class="text">&nbsp;</td>
				<td class="textRight"><s:label
					value="%{getText('label.RemittanceReceiverAddress')}"></s:label>:&nbsp;
				</td>
				<td class="text"><s:textfield name="remitReceivingPartyAddress"
					id="remitReceivingPartyAddress" cssClass="text"
					value="%{ngphCanonical.remitReceivingPartyAddress}"></s:textfield>
				</td>
				<td class="text">&nbsp;</td>
			</tr>
			<tr>
				<td class="textRight"><s:label
					value="%{getText('label.InstructionsforNextAgent')}"></s:label>:&nbsp;</td>
				<td class="text"><s:textfield name="instructionsForNextAgtText"
					cssClass="text" id="instructionsForNextAgtText"
					value="%{ngphCanonical.instructionsForNextAgtText}"></s:textfield>
				</td>
				<td class="text">&nbsp;</td>
				<td class="textRight"><s:label
					value="%{getText('label.ReturnReference')}"></s:label>:&nbsp;</td>
				<td class="text"><s:textfield name="msgReturnReference"
					id="msgReturnReference" cssClass="text"
					value="%{ngphCanonical.msgReturnReference}"></s:textfield></td>
				<td class="text">&nbsp;</td>
			</tr>
			<tr>
				<td class="textRight"><s:label
					value="%{getText('label.Purpose')}"></s:label>:&nbsp;</td>
				<td class="text"><s:textfield name="msgPurposeText"
					id="msgPurposeText" cssClass="text"
					value="%{ngphCanonical.msgPurposeText}"></s:textfield></td>
				<td class="text">&nbsp;</td>
				<td class="textRight"><s:label
					value="%{getText('label.MsgBranch')}"></s:label>:&nbsp;</td>
				<td class="text"><s:textfield name="msgBranch" id="msgBranch"
					cssClass="text" value="%{ngphCanonical.msgBranch}"></s:textfield></td>
				<td class="text">&nbsp;</td>
			</tr>
			<tr>
				<td class="textRight"><s:label
					value="%{getText('label.CategoryPurposeCode')}"></s:label>:&nbsp;</td>
				<td class="text"><s:textfield name="catgPurposeCode"
					id="catgPurposeCode" cssClass="text"
					value="%{ngphCanonical.catgPurposeCode}"></s:textfield></td>
				<td class="text">&nbsp;</td>
				<td class="textRight"><s:label
					value="%{getText('label.LocalInstrumentCode')}"></s:label>:&nbsp;</td>
				<td class="text"><s:textfield name="lclInstCode"
					id="lclInstCode" cssClass="text"
					value="%{ngphCanonical.lclInstCode}"></s:textfield></td>
				<td class="text">&nbsp;</td>
			</tr>
			<tr>
				<td class="textRight"><s:label
					value="%{getText('label.RegulatoryBankcode')}"></s:label>:&nbsp;</td>
				<td class="text"><s:textfield name="regulatoryBankCode"
					id="regulatoryBankCode" cssClass="text"
					value="%{ngphCanonical.regulatoryBankCode}"></s:textfield></td>
				<td class="text">&nbsp;</td>
				<td class="textRight"><s:label
					value="%{getText('label.CategoryPurposeInformation')}"></s:label>:&nbsp;
				</td>
				<td class="text"><s:textfield name="catgPurposeProperitary"
					id="catgPurposeProperitary" cssClass="text"
					value="%{ngphCanonical.catgPurposeProperitary}"></s:textfield></td>
				<td class="text">&nbsp;</td>
			</tr>
			<tr>
				<td class="textRight"><s:label
					value="%{getText('label.ServiceLevelInformation')}"></s:label>:&nbsp;
				</td>
				<td class="text"><s:textfield name="svcLevelProperitary"
					id="svcLevelProperitary" cssClass="text"
					value="%{ngphCanonical.svcLevelProperitary}"></s:textfield></td>
				<td class="text">&nbsp;</td>
				<td class="textRight"><s:label
					value="%{getText('label.SettlementPriority')}"></s:label>:&nbsp;</td>
				<td class="text"><s:textfield name="sndrSttlmntPriority"
					id="sndrSttlmntPriority" cssClass="text"
					value="%{ngphCanonical.sndrSttlmntPriority}"></s:textfield></td>
				<td class="text">&nbsp;</td>
			</tr>

		</table>
	</s:div> <!-- Group Three Div Ends --></div>
	</div>
	</div>
	<br />
	<div id="CollapsiblePanel4" class="CollapsiblePanel">
	<div class="CollapsiblePanelTab"><!--  <img src="images/openPanel.png" width="9" height="7" alt="openPanel" />-->&nbsp;Other
	Agents & Intermediaries</div>
	<div class="CollapsiblePanelContent"><!-- Group Four Div Starts -->
	<s:div id="insCurrency" cssClass="dataGrid" style="width:100%;">
		<table width="100%" cellpadding="5" cellspacing="1" border="0"
			bgcolor="#ffffff">
			<tr>
				<td class="textRight" width="25%"><s:label
					value="%{getText('label.Intermediary2BankAccount')}"></s:label>:&nbsp;
				</td>
				<td class="text" width="15%"><s:if
					test="#paymentQueue =='AWAITING_REPAIR'">
					<s:textfield name="intermediary2AgentAcct"
						id="intermediary2AgentAcct" cssClass="text"
						readonly="%{getText('label.Intermediary2BankAccount.Readonly')}"
						value="%{ngphCanonical.intermediary2AgentAcct}"></s:textfield>
				</s:if> <s:else>
					<s:textfield name="intermediary2AgentAcct"
						id="intermediary2AgentAcct" cssClass="text"
						value="%{ngphCanonical.intermediary2AgentAcct}"></s:textfield>
				</s:else></td>
				<td class="text" width="10%">&nbsp;</td>
				<td class="textRight" width="25%"><s:label
					value="%{getText('label.Intermediary3BankCode')}"></s:label>:&nbsp;
				</td>
				<td class="text" width="15%"><s:if
					test="#paymentQueue =='AWAITING_REPAIR'">
					<s:textfield name="intermediary3Bank" id="intermediary3Bank"
						cssClass="text" readonly="label.Intermediary3BankCode.Readonly"
						value="%{ngphCanonical.intermediary3Bank}"></s:textfield>

				</s:if> <s:else>
					<s:textfield name="intermediary3Bank" id="intermediary3Bank"
						cssClass="text" value="%{ngphCanonical.intermediary3Bank}"></s:textfield>
				</s:else></td>
				<td class="text" width="10%">&nbsp;</td>
			</tr>
			<tr>
				<td class="textRight"><s:label
					value="%{getText('label.intermediary3BankName')}"></s:label>:&nbsp;
				</td>
				<td class="text"><s:if test="#paymentQueue =='AWAITING_REPAIR'">
					<s:textfield name="intermediary3BankName"
						id="intermediary3BankName" cssClass="text"
						readonly="label.intermediary3BankName.Readonly"
						value="%{ngphCanonical.intermediary3BankName}"></s:textfield>

				</s:if> <s:else>
					<s:textfield name="intermediary3BankName"
						id="intermediary3BankName" cssClass="text"
						value="%{ngphCanonical.intermediary3BankName}"></s:textfield>
				</s:else></td>
				<td class="text">&nbsp;</td>
				<td class="textRight"><s:label
					value="%{getText('label.PreviousInstructingBank')}"></s:label>:&nbsp;
				</td>
				<td class="text"><s:if test="#paymentQueue =='AWAITING_REPAIR'">
					<s:textfield name="prevInstructingBank" id="prevInstructingBank"
						cssClass="text"
						readonly="%{getText('label.PreviousInstructingBank.Readonly')}"
						value="%{ngphCanonical.prevInstructingBank}"></s:textfield>
				</s:if> <s:else>
					<s:textfield name="prevInstructingBank" id="prevInstructingBank"
						cssClass="text" value="%{ngphCanonical.prevInstructingBank}"></s:textfield>
				</s:else></td>
				<td class="text">&nbsp;</td>
			</tr>
			<tr>
				<td class="textRight"><s:label
					value="%{getText('label.ExchangeRate')}"></s:label>:&nbsp;</td>
				<td class="text"><s:if test="#paymentQueue =='AWAITING_REPAIR'">
					<s:textfield name="xchangeRate" id="xchangeRate" cssClass="text"
						readonly="%{getText('label.ExchangeRate.Readonly')}"
						value="%{ngphCanonical.xchangeRate}"></s:textfield>
				</s:if> <s:else>
					<s:textfield name="xchangeRate" id="xchangeRate" cssClass="text"
						value="%{ngphCanonical.xchangeRate}"></s:textfield>
				</s:else></td>
				<td class="text">&nbsp;</td>
				<td class="textRight"><s:label
					value="%{getText('label.Intermediary3BankAccount')}"></s:label>:&nbsp;
				</td>
				<td class="text"><s:if test="#paymentQueue =='AWAITING_REPAIR'">
					<s:textfield name="intermediary3AgentAcct"
						id="intermediary3AgentAcct" cssClass="text"
						readonly="%{getText('label.Intermediary3BankAccount.Readonly')}"
						value="%{ngphCanonical.intermediary3AgentAcct}"></s:textfield>
				</s:if> <s:else>
					<s:textfield name="intermediary3AgentAcct"
						id="intermediary3AgentAcct" cssClass="text"
						value="%{ngphCanonical.intermediary3AgentAcct}"></s:textfield>
				</s:else></td>
				<td class="text">&nbsp;</td>
			</tr>
			<tr>
				<td class="textRight"><s:label
					value="%{getText('label.PreviousInstructingBankAccount')}"></s:label>:&nbsp;
				</td>
				<td class="text"><s:if test="#paymentQueue =='AWAITING_REPAIR'">
					<s:textfield name="prevInstructingAgentAcct" cssClass="text"
						id="prevInstructingAgentAcct"
						readonly="%{getText('label.PreviousInstructingBankAccount.Readonly')}"
						value="%{ngphCanonical.prevInstructingAgentAcct}"></s:textfield>

				</s:if> <s:else>
					<s:textfield name="prevInstructingAgentAcct"
						id="prevInstructingAgentAcct" cssClass="text"
						value="%{ngphCanonical.prevInstructingAgentAcct}"></s:textfield>
				</s:else></td>
				<td class="text">&nbsp;</td>
				<td class="textRight"><s:label
					value="%{getText('label.SenderCorrespondent')}"></s:label>:&nbsp;</td>
				<td class="text"><s:if test="#paymentQueue =='AWAITING_REPAIR'">
					<s:textfield name="senderCorrespondent" id="senderCorrespondent"
						cssClass="text"
						readonly="%{getText('Label.SenderCorrespondent.Readonly')}"
						value="%{ngphCanonical.senderCorrespondent}"></s:textfield>

				</s:if> <s:else>
					<s:textfield name="senderCorrespondent" id="senderCorrespondent"
						cssClass="text" value="%{ngphCanonical.senderCorrespondent}"></s:textfield>
				</s:else></td>
				<td class="text">&nbsp;</td>
			</tr>
			<tr>
				<td class="textRight"><s:label
					value="%{getText('label.Intermediary1BankCode')}"></s:label>:&nbsp;
				</td>
				<td class="text"><s:if test="#paymentQueue =='AWAITING_REPAIR'">
					<s:textfield name="intermediary1Bank" id="intermediary1Bank"
						cssClass="text"
						readonly="%{getText('label.Intermediary1BankCode.Readonly')}"
						value="%{ngphCanonical.intermediary1Bank}"></s:textfield>

				</s:if> <s:else>
					<s:textfield name="intermediary1Bank" id="intermediary1Bank"
						cssClass="text" value="%{ngphCanonical.intermediary1Bank}"></s:textfield>
				</s:else></td>
				<td class="text">&nbsp;</td>
				<td class="textRight"><s:label
					value="%{getText('label.intermediary1BankName')}"></s:label>:&nbsp;
				</td>
				<td class="text"><s:if test="#paymentQueue =='AWAITING_REPAIR'">
					<s:textfield name="intermediary1BankName" cssClass="text"
						readonly="%{getText('label.intermediary1BankName.Readonly')}"
						id="intermediary1BankName"
						value="%{ngphCanonical.intermediary1BankName}"></s:textfield>

				</s:if> <s:else>
					<s:textfield name="intermediary1BankName"
						id="intermediary1BankName" cssClass="text"
						value="%{ngphCanonical.intermediary1BankName}"></s:textfield>
				</s:else></td>
				<td class="text">&nbsp;</td>
			</tr>
			<tr>
				<td class="textRight"><s:label
					value="%{getText('label.Intermediary1BankAccount')}"></s:label>:&nbsp;
				</td>
				<td class="text"><s:if test="#paymentQueue =='AWAITING_REPAIR'">
					<s:textfield name="intermediary1AgentAcct"
						id="intermediary1AgentAcct" cssClass="text"
						readonly="%{getText('label.Intermediary1BankAccount.Readonly')}"
						value="%{ngphCanonical.intermediary1AgentAcct}"></s:textfield>
				</s:if> <s:else>
					<s:textfield name="intermediary1AgentAcct"
						id="intermediary1AgentAcct" cssClass="text"
						value="%{ngphCanonical.intermediary1AgentAcct}"></s:textfield>
				</s:else></td>
				<td class="text">&nbsp;</td>
				<td class="textRight"><s:label
					value="%{getText('Label.AccountWithInstitutionAccount')}"></s:label>:&nbsp;
				</td>
				<td class="text"><s:if test="#paymentQueue =='AWAITING_REPAIR'">
					<s:textfield name="accountWithInstitutionAcct"
						id="accountWithInstitutionAcct" cssClass="text"
						readonly="%{getText('label.AccountWithInstitutionAccount.Readonly')}"
						value="%{ngphCanonical.accountWithInstitutionAcct}"></s:textfield>

				</s:if> <s:else>
					<s:textfield name="accountWithInstitutionAcct"
						id="accountWithInstitutionAcct" cssClass="text"
						value="%{ngphCanonical.accountWithInstitutionAcct}"></s:textfield>
				</s:else></td>

				<td class="text">&nbsp;</td>
			</tr>
			<tr>
				<td class="textRight"><s:label
					value="%{getText('label.senderCorrespondentAcct')}"></s:label>:&nbsp;
				</td>
				<td class="text"><s:if test="#paymentQueue =='AWAITING_REPAIR'">
					<s:textfield name="senderCorrespondentAcct"
						id="senderCorrespondentAcct" cssClass="text"
						readonly="%{getText('label.senderCorrespondentAcct.Readonly')}"
						value="%{ngphCanonical.senderCorrespondentAcct}"></s:textfield>

				</s:if> <s:else>
					<s:textfield name="senderCorrespondentAcct"
						id="senderCorrespondentAcct" cssClass="text"
						value="%{ngphCanonical.senderCorrespondentAcct}"></s:textfield>
				</s:else></td>
				<td class="text">&nbsp;</td>


				<td class="textRight"><s:label
					value="%{getText('label.receiverCorrespondent')}"></s:label>:&nbsp;
				</td>
				<td class="text"><s:if test="#paymentQueue =='AWAITING_REPAIR'">
					<s:textfield name="receiverCorrespondent"
						id="receiverCorrespondent" cssClass="text"
						readonly="%{getText('label.receiverCorrespondent.Readonly')}"
						value="%{ngphCanonical.receiverCorrespondent}"></s:textfield>

				</s:if> <s:else>
					<s:textfield name="receiverCorrespondent"
						id="receiverCorrespondent" cssClass="text"
						value="%{ngphCanonical.receiverCorrespondent}"></s:textfield>
				</s:else></td>
				<td class="text">&nbsp;</td>
			</tr>
			<tr>
				<td class="textRight"><s:label
					value="%{getText('label.receiverCorrespondentAcct')}"></s:label>:&nbsp;
				</td>
				<td class="text"><s:if test="#paymentQueue =='AWAITING_REPAIR'">
					<s:textfield name="receiverCorrespondentAcct"
						id="receiverCorrespondentAcct" cssClass="text"
						readonly="%{getText('label.receiverCorrespondentAcct.Readonly')}"
						value="%{ngphCanonical.receiverCorrespondentAcct}"></s:textfield>

				</s:if> <s:else>
					<s:textfield name="receiverCorrespondentAcct"
						id="receiverCorrespondentAcct" cssClass="text"
						value="%{ngphCanonical.receiverCorrespondentAcct}"></s:textfield>
				</s:else></td>
				<td class="text">&nbsp;</td>
				<td class="textRight"><s:label
					value="%{getText('label.thirdCorrespondent')}"></s:label>:&nbsp;</td>
				<td class="text"><s:if test="#paymentQueue =='AWAITING_REPAIR'">
					<s:textfield name="thirdCorrespondent" id="thirdCorrespondent"
						cssClass="text"
						readonly="%{getText('label.thirdCorrespondent.Readonly')}"
						value="%{ngphCanonical.thirdCorrespondent}"></s:textfield>

				</s:if> <s:else>
					<s:textfield name="thirdCorrespondent" id="thirdCorrespondent"
						cssClass="text" value="%{ngphCanonical.thirdCorrespondent}"></s:textfield>
				</s:else></td>
				<td class="text">&nbsp;</td>
			</tr>
			<tr>

				<td class="textRight"><s:label
					value="%{getText('label.thirdCorrespondentAcct')}"></s:label>:&nbsp;
				</td>
				<td class="text"><s:if test="#paymentQueue =='AWAITING_REPAIR'">
					<s:textfield name="thirdCorrespondentAcct"
						id="thirdCorrespondentAcct" cssClass="text"
						readonly="%{getText('label.thirdCorrespondentAcct.Readonly')}"
						value="%{ngphCanonical.thirdCorrespondentAcct}"></s:textfield>

				</s:if> <s:else>
					<s:textfield name="thirdCorrespondentAcct"
						id="thirdCorrespondentAcct" cssClass="text"
						value="%{ngphCanonical.thirdCorrespondentAcct}"></s:textfield>
				</s:else></td>
				<td class="text">&nbsp;</td>
				<td class="textRight"><s:label
					value="%{getText('label.InstructionCodeforCreditorAgent')}"></s:label>:&nbsp;
				</td>
				<td class="text"><s:if test="#paymentQueue =='AWAITING_REPAIR'">
					<s:textfield name="instructionsForCrdtrAgtCode"
						id="instructionsForCrdtrAgtCode" cssClass="text"
						readonly="%{getText('label.AccountWithInstitutionAccount.Readonly')}"
						value="%{ngphCanonical.instructionsForCrdtrAgtCode}"></s:textfield>

				</s:if> <s:else>
					<s:textfield name="instructionsForCrdtrAgtCode"
						id="instructionsForCrdtrAgtCode" cssClass="text"
						value="%{ngphCanonical.instructionsForCrdtrAgtCode}"></s:textfield>
				</s:else></td>
				<td class="text">&nbsp;</td>
			</tr>
			<tr>
				<td class="textRight"><s:label
					value="%{getText('label.Intermediary2BankCode')}"></s:label>:&nbsp;
				</td>
				<td class="text"><s:if test="#paymentQueue =='AWAITING_REPAIR'">

					<s:textfield name="intermediary2Bank" id="intermediary2Bank"
						cssClass="text"
						readonly="%{getText('label.Intermediary2BankCode.Readonly')}"
						value="%{ngphCanonical.intermediary2Bank}"></s:textfield>

				</s:if> <s:else>
					<s:textfield name="intermediary2Bank" id="intermediary2Bank"
						cssClass="text" value="%{ngphCanonical.intermediary2Bank}"></s:textfield>
				</s:else></td>
				<td class="text">&nbsp;</td>
				<td class="textRight"><s:label
					value="%{getText('label.intermediary2BankName')}"></s:label>:&nbsp;
				</td>
				<td class="text"><s:if test="#paymentQueue =='AWAITING_REPAIR'">
					<s:textfield name="intermediary2BankName"
						id="intermediary2BankName" cssClass="text"
						readonly="%{getText('label.intermediary2BankName.Readonly')}"
						value="%{ngphCanonical.intermediary2BankName}"></s:textfield>

				</s:if> <s:else>
					<s:textfield name="intermediary2BankName"
						id="intermediary2BankName" cssClass="text"
						value="%{ngphCanonical.intermediary2BankName}"></s:textfield>
				</s:else></td>
				<td class="text">&nbsp;</td>
			</tr>
		</table>
	</s:div> <!-- Group Four Div Ends --></div>
	</div>
	<!-- Collapsible Panels End-->

	<!--  button panel starts -->
	<div class="clearfix"></div>
	<table width="100%" cellpadding="0" cellspacing="0" border="0">
		<tr>
			<td><s:if
				test="%{#session.paymentEntryStatus =='AWAITING_REPAIR'}">
				<input type="button" value="Submit Payment for ${paymentbuttonName}"
					onclick="SaveRepairPopup()" />
				<input type="button" value="Cancel"
					onclick="CallPaymentRepairCancel()" class="btn" />
			</s:if> <s:else>
				<s:submit value="Save Payment" action="savePaymentStatus"
					cssClass="btn" />
				<input type="button" value="Cancel" class="btn" />
			</s:else></td>
		</tr>
	</table>
	<!--  button panel ends -->
</s:form></div>
</div>
</div>
<div id="contentPaneBottom"></div>
<script type="text/javascript">
	var CollapsiblePanel1 = new Spry.Widget.CollapsiblePanel(
			"CollapsiblePanel1");
	var CollapsiblePanel2 = new Spry.Widget.CollapsiblePanel(
			"CollapsiblePanel2");
	var CollapsiblePanel3 = new Spry.Widget.CollapsiblePanel(
			"CollapsiblePanel3");
	var CollapsiblePanel4 = new Spry.Widget.CollapsiblePanel(
			"CollapsiblePanel4");
</script>
