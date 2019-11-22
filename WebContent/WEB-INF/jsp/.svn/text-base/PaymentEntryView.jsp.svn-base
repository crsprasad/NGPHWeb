
<%@page import="com.opensymphony.xwork2.ActionContext"%>
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="/struts-tags" prefix="s"%>

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
	

	function CallPaymentRepairCancel() {

		document.forms[0].action = "loadPaymentMessageQueue";
		document.forms[0].submit();

	}
</script>

<s:head />
<s:form action="paymentAction" method="post">
	<s:hidden id="repairComments" name="repairComments"></s:hidden>
	<div id="colCont"><!-- Content Pane starts and col3 is the class for content-->
	<div id="col3" style="width: 99%">&nbsp;Payment Entry View
	<div id="content">
	<table width="100%" cellpadding="5" cellspacing="0" border="0"
		bgcolor="#ffffff">
		<tr>
			<td class="errorMsg">
			<s:if test="hasFieldErrors()">
			<div style="overflow: auto; height: 100px; width: 98%; border: 1px solid #f0f0f0;">
			<s:iterator
				value="getFieldErrors().entrySet()" var="entry">
				<s:iterator value="#entry.value">
					<span class="errorMsg"> <s:property value="%{top}" /></span>
					<br />
				</s:iterator>
			</s:iterator></div>
			</s:if>
			</td>
		</tr>
		<tr>
			<td>
			<table width="100%" cellpadding="0" cellspacing="0" border="0">
				<tr>
					<td>
					<div class="tabToggle"><a href="#"
						onclick="callGroupOne('main')">&nbsp;+&nbsp;Group One</a></div>
					<div class="divBg"><!-- Group One Div Starts --> <s:div
						id="main" cssClass="divBg">
						<table width="100%" cellpadding="3" cellspacing="1" border="0"
							bgcolor="#ffffff">
							<s:set name="paymentQueue" value="%{#session.paymentStatusVal}" />
							<tr>
								<td width="15%" class="textRight"><s:label
									value="%{getText('label.msgChnlType')}"></s:label>:&nbsp;</td>
								<td width="16%" class="text"> 
									<s:textfield name="msgChnlType" id="msgChnlType"
										cssClass="text" value="%{ngphCanonical.msgChnlType}"
										readonly="true"></s:textfield>
								</td>
								<td width="19%" class="text">&nbsp;</td>
								<td width="15%" class="textRight"><s:label
									value="%{getText('label.dstMsgType')}"></s:label>:&nbsp;</td>
								<td width="16%" class="text">
									<s:textfield name="dstMsgType" id="dstMsgType" readonly="true"
										cssClass="text" value="%{ngphCanonical.dstMsgType}"></s:textfield>
								</td>
								<td width="19%" class="text">&nbsp;</td>
							</tr>
							<tr>
								<td class="textRight"><s:label
									value="%{getText('label.dstMsgSubType')}"></s:label>:&nbsp;</td>
								<td class="text">
									<s:textfield name="dstMsgSubType" id="dstMsgSubType"
										cssClass="text" value="%{ngphCanonical.dstMsgSubType}" readonly="true"></s:textfield>
								</td>
								<td class="text">&nbsp;</td>
								<td class="textRight"><s:label
									value="%{getText('label.AccountWithInstitution')}"></s:label>:&nbsp;
								</td>
								<td class="text">
									<s:textfield name="accountWithInstitution"
										id="accountWithInstitution" cssClass="text" readonly="true"
										value="%{ngphCanonical.accountWithInstitution}"></s:textfield>
								</td>
								<td class="text">&nbsp;</td>
							</tr>
							<tr>
								<td class="textRight"><s:label
									value="%{getText('label.TransacRef')}"></s:label>:&nbsp;</td>
								<td class="text">
									<s:textfield name="txnReference" cssClass="text"
										cssStyle="css/ngph.css" id="txnReference" readonly="true"
										value="%{ngphCanonical.txnReference}"></s:textfield>
								</td>
								<td class="text">&nbsp;</td>
								<td class="textRight"><s:label
									value="%{getText('label.OrderingCustomerAccount')}"></s:label>:&nbsp;
								</td>
								<td class="text">
									<s:textfield name="orderingCustAccount"
										id="orderingCustAccount" cssClass="text"
										value="%{ngphCanonical.orderingCustAccount}" readonly="true"></s:textfield>
								</td>
								<td class="text">&nbsp;</td>
							</tr>
							<tr>
								<td class="textRight"><s:label
									value="%{getText('label.SenderPriority')}"></s:label>:&nbsp;</td>
								<td class="text"><s:textfield name="sndrPymtPriority"
									id="sndrPymtPriority" cssClass="text"
									value="%{ngphCanonical.sndrPymtPriority}" readonly="true"></s:textfield></td>
								<td class="text">&nbsp;</td>
								<td class="textRight"><s:label
									value="%{getText('label.BeneficiaryParty')}"></s:label>:&nbsp;</td>
								<td class="text">
									<s:textfield name="beneficiaryCustomerName" cssClass="text"
										id="beneficiaryCustomerName" readonly="true"
										value="%{ngphCanonical.beneficiaryCustomerName}"></s:textfield>
								</td>
								<td class="text">&nbsp;</td>
							</tr>
							<tr>
								<td class="textRight"><s:label
									value="%{getText('label.ValueDate')}"></s:label>:&nbsp;</td>
								<td class="text"><s:textfield name="valueDate"
									cssClass="text" id="valueDate" readonly="true"></s:textfield></td>
								<td class="text">&nbsp;</td>
								<td class="textRight"><s:label
									value="%{getText('label.ultimateDebtorName')}"></s:label>:&nbsp;</td>
								<td class="text">
									<s:textfield name="ultimateDebtorName" cssClass="text"
										id="ultimateDebtorName" readonly="true"
										value="%{ngphCanonical.ultimateDebtorName}"></s:textfield>
							</td>
								<td class="text">&nbsp;</td>
							</tr>
							<tr>
								<td class="textRight"><s:label
									value="%{getText('label.Currency')}"></s:label>:&nbsp;</td>
								<td class="text">
									<s:textfield name="msgCurrency" cssClass="text"
										id="msgCurrency" readonly="true"
										value="%{ngphCanonical.msgCurrency}"></s:textfield>
								<button type="button" class="btn" disabled="disabled">More...</button>
								</td>
								<td class="text">&nbsp;</td>
								<td class="textRight"><s:label
									value="%{getText('label.BeneficiaryAccount')}"></s:label>:&nbsp;
								</td>
								<td class="text">
									<s:textfield name="beneficiaryCustAcct" cssClass="text"
										id="beneficiaryCustAcct" readonly="true"
										value="%{ngphCanonical.beneficiaryCustAcct}"></s:textfield>
								<button type="button" class="btn" disabled="disabled">More...</button>
								</td>
								<td class="text">&nbsp;</td>
							</tr>
							<tr>
								<td class="textRight"><s:label
									value="%{getText('label.OrderingParty')}"></s:label>:&nbsp;</td>
								<td class="text"><s:textfield name="orderingCustomerName"
									cssClass="text" id="orderingCustomerName" readonly="true"
									value="%{ngphCanonical.orderingCustomerName}"></s:textfield>
								<button type="button" class="btn" disabled="disabled">More...</button>
								</td>
								<td class="text">&nbsp;</td>
								<td class="textRight"><s:label
									value="%{getText('label.ChargesBearer')}"></s:label>:&nbsp;</td>
								<td class="text"><s:textfield name="chargeBearer"
									cssClass="text" id="chargeBearer" readonly="true"
									value="%{ngphCanonical.chargeBearer}"></s:textfield>
								<button type="button" class="btn" disabled="disabled">More...</button>
								</td>
								<td class="text">&nbsp;</td>
							</tr>
							<tr>
								<td class="textRight"><s:label
									value="%{getText('label.Amount')}"></s:label>:&nbsp;</td>
								<td class="text">
									<s:textfield name="msgAmount" cssClass="text" id="msgAmount"
										readonly="true" value="%{ngphCanonical.msgAmount}"></s:textfield>
								</td>
								<td class="text">&nbsp;</td>
								<td class="textRight"><s:label
									value="%{getText('label.ReceiverBankCode')}"></s:label>:&nbsp;</td>
								<td class="text">
									<s:textfield name="receiverBank" id="receiverBank" readonly="true"
										cssClass="text" value="%{ngphCanonical.receiverBank}"></s:textfield>
								</td>
								<td class="text">&nbsp;</td>
							</tr>
							<tr>
								<td class="textRight"><s:label
									value="%{getText('label.InstructedCurrency')}"></s:label>:&nbsp;</td>
								<td class="text">
									<s:textfield name="instructedCurrency" id="instructedCurrency" readonly="true"
										cssClass="text" value="%{ngphCanonical.instructedCurrency}"></s:textfield>
							</td>
								<td class="text">&nbsp;</td>
								<td class="textRight"><s:label
									value="%{getText('label.InstructedAmount')}"></s:label>:&nbsp;</td>
								<td class="text">
									<s:textfield name="instructedAmount" id="instructedAmount"
										cssClass="text" value="%{ngphCanonical.instructedAmount}"
										readonly="true"></s:textfield>
								</td>
								<td class="text">&nbsp;</td>
							</tr>
						</table>
					</s:div> <!-- Group One Div Ends --></div>
					</td>
				</tr>
				<tr>
					<td class="text">&nbsp;</td>
				</tr>
				<tr>
				<tr>
					<td>
					<div class="tabToggle"><a href="#"
						onclick="callGroupOne('mandatory')">&nbsp;+&nbsp;Group Two</a></div>
					<div class="divBg"><!-- Group Two Div Starts --> <s:div
						id="mandatory" cssClass="divBg">
						<table width="100%" cellpadding="3" cellspacing="1" border="0"
							bgcolor="#ffffff">
							<tr>
								<td width="15%" class="textRight"><s:label
									value="%{getText('label.CustomerTransactionReference')}"></s:label>:&nbsp;
								</td>
								<td width="16%" class="text"><s:textfield readonly="true"
									name="custTxnReference" id="custTxnReference" cssClass="text"
									value="%{ngphCanonical.custTxnReference}"></s:textfield></td>
								<td width="19%" class="text">&nbsp;</td>
								<td width="15%" class="textRight"><s:label
									value="%{getText('label.RegulatoryReportingAmount')}"></s:label>:&nbsp;
								</td>
								<td width="16%" class="text"><s:textfield
									name="regulatoryReportAmount" id="regulatoryReportAmount" readonly="true"
									cssClass="text" value="%{ngphCanonical.regulatoryReportAmount}"></s:textfield>
								</td>
								<td width="19%" class="text">&nbsp;</td>
							</tr>
							<tr>
								<td class="textRight"><s:label
									value="%{getText('label.ultimateCreditorAddress')}"></s:label>:&nbsp;</td>
								<td class="text"><s:textfield
									name="ultimateCreditorAddress" cssClass="text"
									id="ultimateCreditorAddress"
									value="%{ngphCanonical.ultimateCreditorAddress}"
									readonly="true"></s:textfield></td>
								<td class="text">&nbsp;</td>
								<td class="textRight"><s:label
									value="%{getText('label.ultimateCreditorID')}"></s:label>:&nbsp;
								</td>
								<td class="text"><s:textfield name="ultimateCreditorID"
									cssClass="text" id="ultimateCreditorID" readonly="true"
									value="%{ngphCanonical.ultimateCreditorID}"></s:textfield></td>
								<td class="text">&nbsp;</td>
							</tr>
							<tr>
								<td class="textRight"><s:label
									value="%{getText('label.ultimateCreditorCtry')}"></s:label>:&nbsp;</td>
								<td class="text"><s:textfield name="ultimateCreditorCtry"
									cssClass="text" id="ultimateCreditorCtry" readonly="true"
									value="%{ngphCanonical.ultimateCreditorCtry}"></s:textfield></td>
								<td class="text">&nbsp;</td>
								<td class="textRight"><s:label
									value="%{getText('label.ultimateCreditorCtctDtls')}"></s:label>:&nbsp;
								</td>
								<td class="text"><s:textfield
									name="ultimateCreditorCtctDtls" cssClass="text"
									id="ultimateCreditorCtctDtls" readonly="true"
									value="%{ngphCanonical.ultimateCreditorCtctDtls}"></s:textfield></td>
								<td class="text">&nbsp;</td>
							</tr>
							<tr>
								<td class="textRight"><s:label
									value="%{getText('label.orderingCustomerAddress')}"></s:label>:&nbsp;</td>
								<td class="text"><s:textfield
									name="orderingCustomerAddress" cssClass="text"
									id="orderingCustomerAddress" readonly="true"
									value="%{ngphCanonical.orderingCustomerAddress}"></s:textfield></td>
								<td class="text">&nbsp;</td>
								<td class="textRight"><s:label
									value="%{getText('label.orderingCustomerID')}"></s:label>:&nbsp;
								</td>
								<td class="text"><s:textfield name="orderingCustomerID"
									cssClass="text" id="orderingCustomerID" readonly="true"
									value="%{ngphCanonical.orderingCustomerID}"></s:textfield></td>
								<td class="text">&nbsp;</td>
							</tr>
							<tr>
								<td class="textRight"><s:label
									value="%{getText('label.orderingCustomerCtry')}"></s:label>:&nbsp;</td>
								<td class="text"><s:textfield name="orderingCustomerCtry"
									cssClass="text" id="orderingCustomerCtry" readonly="true"
									value="%{ngphCanonical.orderingCustomerCtry}"></s:textfield></td>
								<td class="text">&nbsp;</td>
								<td class="textRight"><s:label
									value="%{getText('label.orderingCustomerCtctDtls')}"></s:label>:&nbsp;
								</td>
								<td class="text"><s:textfield
									name="orderingCustomerCtctDtls" cssClass="text"
									id="orderingCustomerCtctDtls" readonly="true"
									value="%{ngphCanonical.orderingCustomerCtctDtls}"></s:textfield></td>
								<td class="text">&nbsp;</td>
							</tr>
							<tr>
								<td class="textRight"><s:label
									value="%{getText('label.RegulatoryReportingCurrency')}"></s:label>:&nbsp;</td>
								<td class="text"><s:textfield
									name="regulatoryReportCurrency" cssClass="text" readonly="true"
									id="regulatoryReportCurrency"
									value="%{ngphCanonical.regulatoryReportCurrency}"></s:textfield></td>
								<td class="text">&nbsp;</td>
								<td class="textRight"><s:label
									value="%{getText('label.ultimateDebtorAddress')}"></s:label>:&nbsp;
								</td>
								<td class="text">
									<s:textfield name="ultimateDebtorAddress" cssClass="text"
										id="ultimateDebtorAddress" readonly="true"
										value="%{ngphCanonical.ultimateDebtorAddress}"></s:textfield>
								</td>
								<td class="text">&nbsp;</td>
							</tr>
							<tr>
								<td class="textRight"><s:label
									value="%{getText('label.ultimateDebtorID')}"></s:label>:&nbsp;</td>
								<td class="text">
									<s:textfield name="ultimateDebtorID" cssClass="text"
										id="ultimateDebtorID" readonly="true"
										value="%{ngphCanonical.ultimateDebtorID}"></s:textfield>
							</td>
								<td class="text">&nbsp;</td>
								<td class="textRight"><s:label
									value="%{getText('label.ultimateDebtorCtry')}"></s:label>:&nbsp;
								</td>
								<td class="text">
									<s:textfield name="ultimateDebtorCtry" cssClass="text"
										id="ultimateDebtorCtry" readonly="true"
										value="%{ngphCanonical.ultimateDebtorCtry}"></s:textfield>
							</td>
								<td class="text">&nbsp;</td>
							</tr>
							<tr>
								<td class="textRight"><s:label
									value="%{getText('label.ultimateDebtorCtctDtls')}"></s:label>:&nbsp;</td>
								<td class="text">
									<s:textfield name="ultimateDebtorCtctDtls" cssClass="text"
										id="ultimateDebtorCtctDtls" readonly="true"
										value="%{ngphCanonical.ultimateDebtorCtctDtls}"></s:textfield>
								</td>
								<td class="text">&nbsp;</td>
								<td class="textRight"><s:label
									value="%{getText('label.initiatingPartyName')}"></s:label>:&nbsp;
								</td>
								<td class="text">
									<s:textfield name="initiatingPartyName" cssClass="text"
										id="initiatingPartyName" readonly="true"
										value="%{ngphCanonical.initiatingPartyName}"></s:textfield>
							</td>
								<td class="text">&nbsp;</td>
							</tr>
							<tr>
								<td class="textRight"><s:label
									value="%{getText('label.initiatingPartyAddress')}"></s:label>:&nbsp;</td>
								<td class="text">
									<s:textfield name="initiatingPartyAddress" cssClass="text"
										id="initiatingPartyAddress" readonly="true"
										value="%{ngphCanonical.initiatingPartyAddress}"></s:textfield>
								</td>
								<td class="text">&nbsp;</td>
								<td class="textRight"><s:label
									value="%{getText('label.initiatingPartyID')}"></s:label>:&nbsp;
								</td>
								<td class="text">
									<s:textfield name="initiatingPartyID" cssClass="text"
										id="initiatingPartyID" readonly="true"
										value="%{ngphCanonical.initiatingPartyID}"></s:textfield>
							</td>
								<td class="text">&nbsp;</td>
							</tr>
							<tr>
								<td class="textRight"><s:label
									value="%{getText('label.initiatingPartyCtry')}"></s:label>:&nbsp;</td>
								<td class="text">
									<s:textfield name="initiatingPartyCtry" cssClass="text"
										id="initiatingPartyCtry" readonly="true"
										value="%{ngphCanonical.initiatingPartyCtry}"></s:textfield>
							</td>
								<td class="text">&nbsp;</td>
								<td class="textRight"><s:label
									value="%{getText('label.initiatingPartyCtctDtls')}"></s:label>:&nbsp;</td>
								<td class="text">
									<s:textfield name="initiatingPartyCtctDtls" cssClass="text"
										id="initiatingPartyCtctDtls" readonly="true"
										value="%{ngphCanonical.initiatingPartyCtctDtls}"></s:textfield>
								</td>
								<td class="text">&nbsp;</td>
							</tr>
							<tr>
								<td class="textRight"><s:label
									value="%{getText('label.beneficiaryCustomerAddress')}"></s:label>:&nbsp;</td>
								<td class="text">
									<s:textfield name="beneficiaryCustomerAddress" cssClass="text"
										id="beneficiaryCustomerAddress" readonly="true"
										value="%{ngphCanonical.beneficiaryCustomerAddress}"></s:textfield>
								</td>
								<td class="text">&nbsp;</td>

								<td class="textRight"><s:label
									value="%{getText('label.beneficiaryCustomerID')}"></s:label>:&nbsp;</td>
								<td class="text">
									<s:textfield name="beneficiaryCustomerID" cssClass="text"
										id="beneficiaryCustomerID" readonly="true"
										value="%{ngphCanonical.beneficiaryCustomerID}"></s:textfield>
								</td>
								<td class="text">&nbsp;</td>
							</tr>
							<tr>

								<td class="textRight"><s:label
									value="%{getText('label.beneficiaryCustomerCtry')}"></s:label>:&nbsp;</td>
								<td class="text">
									<s:textfield name="beneficiaryCustomerCtry" cssClass="text"
										id="beneficiaryCustomerCtry" readonly="true"
										value="%{ngphCanonical.beneficiaryCustomerCtry}"></s:textfield>
								</td>
								<td class="text">&nbsp;</td>
								<td class="textRight"><s:label
									value="%{getText('label.beneficiaryCustomerCtctDtls')}"></s:label>:&nbsp;</td>
								<td class="text">
									<s:textfield name="beneficiaryCustomerCtctDtls" cssClass="text"
										id="beneficiaryCustomerCtctDtls" readonly="true"
										value="%{ngphCanonical.beneficiaryCustomerCtctDtls}"></s:textfield>
								</td>
								<td class="text">&nbsp;</td>
							</tr>
							<tr>
								<td class="textRight"><s:label
									value="%{getText('label.UltimateCreditor')}"></s:label>:&nbsp;</td>
								<td class="text"><s:textfield name="ultimateCreditorName"
									cssClass="text" id="ultimateCreditorName" readonly="true"
									value="%{ngphCanonical.ultimateCreditorName}"></s:textfield>
								<button type="button" class="btn" disabled="disabled">More...</button>
								</td>
								<td class="text">&nbsp;</td>
								<td class="textRight"><s:label
									value="%{getText('label.ServiceLevelCode')}"></s:label>:&nbsp;</td>
								<td class="text"><s:textfield name="svcLevelCode"
									cssClass="text" id="svcLevelCode" readonly="true"
									value="%{ngphCanonical.svcLevelCode}"></s:textfield></td>
								<td class="text">&nbsp;</td>
							</tr>
							<tr>
								<td class="textRight"><s:label
									value="%{getText('label.SenderTransactionReference')}"></s:label>:&nbsp;</td>
								
								<td class="text">
									<s:textfield name="sndrTxnId" id="sndrTxnId" readonly="true"
										cssClass="text" value="%{ngphCanonical.sndrTxnId}"></s:textfield>
								</td>
								
								<td class="text">&nbsp;</td>
								<td class="textRight"><s:label
									value="%{getText('label.RegulatoryInformation')}"></s:label>:&nbsp;
								</td>
								<td class="text"><s:textfield name="regulatoryInformation"
									id="regulatoryInformation" cssClass="text" readonly="true"
									value="%{ngphCanonical.regulatoryInformation}"></s:textfield></td>
								<td class="text">&nbsp;</td>
							</tr>
							<tr>
								<td class="textRight"><s:label
									value="%{getText('label.ClearingSystemReference')}"></s:label>:&nbsp;
								</td>
								<td class="text">
									<s:textfield name="clrgSysReference" id="clrgSysReference" readonly="true"
										cssClass="text" value="%{ngphCanonical.clrgSysReference}"></s:textfield>
								</td>
								<td class="text">&nbsp;</td>
								<td class="textRight"><s:label
									value="%{getText('label.RemittanceIdentification')}"></s:label>:&nbsp;
								</td>
								<td class="text"><s:textfield
									name="initiatorRemitReference" id="initiatorRemitReference"
									cssClass="text" readonly="true"
									value="%{ngphCanonical.initiatorRemitReference}"></s:textfield>
								<td class="text">&nbsp;</td>
							</tr>
						</table>
					</s:div> <!-- Group Two Div Ends --></div>
					</td>
				</tr>
				<tr>
					<td class="text">&nbsp;</td>
				</tr>
				<tr>
					<td>
					<div class="tabToggle"><a href="#"
						onclick="callGroupOne('register')">&nbsp;+&nbsp;Group Three</a></div>
					<div class="divBg"><!-- Group Three Div Starts --> <s:div
						id="register" cssClass="divBg">
						<table width="100%" cellpadding="3" cellspacing="1" border="0"
							bgcolor="#ffffff">
							<tr>
								<td width="15%" class="textRight"><s:label
									value="%{getText('label.RelatedReference')}"></s:label>:&nbsp;</td>
								<td width="16%" class="text"><s:textfield
									name="relReference" id="relReference" cssClass="text" readonly="true"
									value="%{ngphCanonical.relReference}"></s:textfield></td>
								<td width="19%">&nbsp;</td>
								<td width="15%" class="textRight"><s:label
									value="%{getText('label.LocalInstrumentInformation')}"></s:label>:&nbsp;
								</td>
								<td width="16%" class="text"><s:textfield readonly="true"
									name="lclInstProperitary" id="lclInstProperitary"
									cssClass="text" value="%{ngphCanonical.lclInstProperitary}"></s:textfield>
								</td>
								<td width="19%">&nbsp;</td>
							</tr>
							<tr>
								<td class="textRight"><s:label
									value="%{getText('label.initiatorRemitAdviceMethod')}"></s:label>:&nbsp;
								</td>
								<td class="text"><s:textfield
									name="initiatorRemitAdviceMethod" readonly="true"
									id="initiatorRemitAdviceMethod" cssClass="text"
									value="%{ngphCanonical.initiatorRemitAdviceMethod}"></s:textfield>
								</td>
								<td class="text">&nbsp;</td>
								<td class="textRight"><s:label
									value="%{getText('label.PurposeCode')}"></s:label>:&nbsp;</td>
								<td class="text"><s:textfield name="msgPurposeCode" readonly="true"
									id="msgPurposeCode" cssClass="text"
									value="%{ngphCanonical.msgPurposeCode}"></s:textfield></td>
								<td class="text">&nbsp;</td>
							</tr>
							<tr>
								<td class="textRight"><s:label
									value="%{getText('label.ClearingChannel')}"></s:label>:&nbsp;</td>
								<td class="text">
									<s:textfield name="clrgChannel" id="clrgChannel" readonly="true"
										cssClass="text" value="%{ngphCanonical.clrgChannel}"></s:textfield>
								</td>
								<td class="text">&nbsp;</td>
								<td class="textRight"><s:label
									value="%{getText('label.RemittanceLocationeMail')}"></s:label>:&nbsp;
								</td>
								<td class="text"><s:textfield name="remitInfoEmail"
									id="remitInfoEmail" cssClass="text" readonly="true"
									value="%{ngphCanonical.remitInfoEmail}"></s:textfield></td>
								<td class="text">&nbsp;</td>
							</tr>
							<tr>
								<td class="textRight"><s:label
									value="%{getText('label.InstructionsforCreditorAgent')}"></s:label>:&nbsp;</td>
								<td class="text"><s:textfield
									name="instructionsForCrdtrAgtText" readonly="true"
									id="instructionsForCrdtrAgtText" cssClass="text"
									value="%{ngphCanonical.instructionsForCrdtrAgtText}"></s:textfield>
								</td>
								<td class="text">&nbsp;</td>
								<td class="textRight"><s:label
									value="%{getText('label.RemittanceReceiverName')}"></s:label>:&nbsp;
								</td>
								<td class="text"><s:textfield readonly="true"
									name="remitReceivingPartyName" id="remitReceivingPartyName"
									cssClass="text"
									value="%{ngphCanonical.remitReceivingPartyName}"></s:textfield>
								</td>
								<td class="text">&nbsp;</td>
							</tr>
							<tr>
								<td class="textRight"><s:label
									value="%{getText('label.InstructionsCodeforAgent')}"></s:label>:&nbsp;</td>
								<td class="text"><s:textfield readonly="true"
									name="instructionsForNextAgtCode" cssClass="text"
									id="instructionsForNextAgtCode"
									value="%{ngphCanonical.instructionsForNextAgtCode}"></s:textfield>
								</td>
								<td class="text">&nbsp;</td>
								<td class="textRight"><s:label
									value="%{getText('label.RemittanceReceiverAddress')}"></s:label>:&nbsp;
								</td>
								<td class="text"><s:textfield readonly="true"
									name="remitReceivingPartyAddress"
									id="remitReceivingPartyAddress" cssClass="text"
									value="%{ngphCanonical.remitReceivingPartyAddress}"></s:textfield>
								</td>
								<td class="text">&nbsp;</td>
							</tr>
							<tr>
								<td class="textRight"><s:label
									value="%{getText('label.InstructionsforNextAgent')}"></s:label>:&nbsp;</td>
								<td class="text"><s:textfield readonly="true"
									name="instructionsForNextAgtText" cssClass="text"
									id="instructionsForNextAgtText"
									value="%{ngphCanonical.instructionsForNextAgtText}"></s:textfield>
								</td>
								<td class="text">&nbsp;</td>
								<td class="textRight"><s:label
									value="%{getText('label.ReturnReference')}"></s:label>:&nbsp;</td>
								<td class="text"><s:textfield name="msgReturnReference" readonly="true"
									id="msgReturnReference" cssClass="text"
									value="%{ngphCanonical.msgReturnReference}"></s:textfield></td>
								<td class="text">&nbsp;</td>
							</tr>
							<tr>
								<td class="textRight"><s:label
									value="%{getText('label.Purpose')}"></s:label>:&nbsp;</td>
								<td class="text"><s:textfield name="msgPurposeText" readonly="true"
									id="msgPurposeText" cssClass="text"
									value="%{ngphCanonical.msgPurposeText}"></s:textfield></td>
								<td class="text">&nbsp;</td>
								<td class="textRight"><s:label
									value="%{getText('label.MsgBranch')}"></s:label>:&nbsp;</td>
								<td class="text"><s:textfield name="msgBranch" readonly="true"
									id="msgBranch" cssClass="text"
									value="%{ngphCanonical.msgBranch}"></s:textfield></td>
								<td class="text">&nbsp;</td>
							</tr>
							<tr>
								<td class="textRight"><s:label
									value="%{getText('label.CategoryPurposeCode')}"></s:label>:&nbsp;
								</td>
								<td class="text"><s:textfield name="catgPurposeCode" readonly="true"
									id="catgPurposeCode" cssClass="text"
									value="%{ngphCanonical.catgPurposeCode}"></s:textfield></td>
								<td class="text">&nbsp;</td>
								<td class="textRight"><s:label
									value="%{getText('label.LocalInstrumentCode')}"></s:label>:&nbsp;
								</td>
								<td class="text"><s:textfield name="lclInstCode" readonly="true"
									id="lclInstCode" cssClass="text"
									value="%{ngphCanonical.lclInstCode}"></s:textfield></td>
								<td class="text">&nbsp;</td>
							</tr>
							<tr>
							<td class="textRight"><s:label
									value="%{getText('label.RegulatoryBankcode')}"></s:label>:&nbsp;</td>
								<td class="text"><s:textfield name="regulatoryBankCode"
									id="regulatoryBankCode" cssClass="text" readonly="true"
									value="%{ngphCanonical.regulatoryBankCode}"></s:textfield></td>
								<td class="text">&nbsp;</td>
								<td class="textRight"><s:label
									value="%{getText('label.CategoryPurposeInformation')}"></s:label>:&nbsp;
								</td>
								<td class="text"><s:textfield name="catgPurposeProperitary"
									id="catgPurposeProperitary" cssClass="text" readonly="true"
									value="%{ngphCanonical.catgPurposeProperitary}"></s:textfield></td>
								<td class="text">&nbsp;</td>
							</tr>
							<tr>
								<td class="textRight"><s:label
									value="%{getText('label.ServiceLevelInformation')}"></s:label>:&nbsp;
								</td>
								<td class="text"><s:textfield name="svcLevelProperitary" readonly="true"
									id="svcLevelProperitary" cssClass="text"
									value="%{ngphCanonical.svcLevelProperitary}"></s:textfield></td>
								<td class="text">&nbsp;</td>
								<td class="textRight"><s:label
									value="%{getText('label.SettlementPriority')}"></s:label>:&nbsp;
								</td>
								<td class="text"><s:textfield name="sndrSttlmntPriority"
									id="sndrSttlmntPriority" cssClass="text" readonly="true"
									value="%{ngphCanonical.sndrSttlmntPriority}"></s:textfield></td>
								<td class="text">&nbsp;</td>
							</tr>
							
						</table>
					</s:div> <!-- Group Three Div Ends --></div>
					</td>
				</tr>
				<tr>
					<td class="text">&nbsp;</td>
				</tr>
				<tr>
					<td>
					<div class="tabToggle"><a href="#"
						onclick="callGroupOne('insCurrency')">&nbsp;+&nbsp;Group Four</a></div>
					<div class="divBg"><!-- Group Four Div Starts --> <s:div
						id="insCurrency" cssClass="divBg">
						<table width="100%" cellpadding="3" cellspacing="1" border="0"
							bgcolor="#ffffff">

							<tr>
								<td width="15%" class="textRight"><s:label
									value="%{getText('label.Intermediary2BankAccount')}"></s:label>:&nbsp;
								</td>
								<td width="16%" class="text">
									<s:textfield name="intermediary2AgentAcct"
										id="intermediary2AgentAcct" cssClass="text" readonly="true"
										value="%{ngphCanonical.intermediary2AgentAcct}"></s:textfield>
								</td>
								<td width="19%" class="text">&nbsp;</td>
								<td width="15%" class="textRight"><s:label
									value="%{getText('label.Intermediary3BankCode')}"></s:label>:&nbsp;
								</td>
								<td width="16%" class="text">
									<s:textfield name="intermediary3Bank" id="intermediary3Bank" readonly="true"
										cssClass="text" value="%{ngphCanonical.intermediary3Bank}"></s:textfield>
							</td>
								<td width="19%" class="text">&nbsp;</td>
							</tr>
							<tr>
								<td class="textRight"><s:label
									value="%{getText('label.intermediary3BankName')}"></s:label>:&nbsp;
								</td>
								<td class="text">
									<s:textfield name="intermediary3BankName"
										id="intermediary3BankName" cssClass="text" readonly="true"
										value="%{ngphCanonical.intermediary3BankName}"></s:textfield>
								</td>
								<td class="text">&nbsp;</td>
								<td class="textRight"><s:label
									value="%{getText('label.PreviousInstructingBank')}"></s:label>:&nbsp;
								</td>
								<td class="text">
									<s:textfield name="prevInstructingBank" readonly="true"
										id="prevInstructingBank" cssClass="text"
										value="%{ngphCanonical.prevInstructingBank}"></s:textfield>
								</td>
								<td class="text">&nbsp;</td>
							</tr>
							<tr>
								<td class="textRight"><s:label
									value="%{getText('label.ExchangeRate')}"></s:label>:&nbsp;</td>
								<td class="text">
									<s:textfield name="xchangeRate" id="xchangeRate" readonly="true"
										cssClass="text" value="%{ngphCanonical.xchangeRate}"></s:textfield>
								</td>
								<td class="text">&nbsp;</td>
								<td class="textRight"><s:label
									value="%{getText('label.Intermediary3BankAccount')}"></s:label>:&nbsp;
								</td>
								<td class="text">
									<s:textfield name="intermediary3AgentAcct"
										id="intermediary3AgentAcct" cssClass="text" readonly="true"
										value="%{ngphCanonical.intermediary3AgentAcct}"></s:textfield>
								</td>
								<td class="text">&nbsp;</td>
							</tr>
							<tr>
								<td class="textRight"><s:label
									value="%{getText('label.PreviousInstructingBankAccount')}"></s:label>:&nbsp;
								</td>
								<td class="text">
									<s:textfield name="prevInstructingAgentAcct"
										id="prevInstructingAgentAcct" cssClass="text" readonly="true"
										value="%{ngphCanonical.prevInstructingAgentAcct}"></s:textfield>
								</td>
								<td class="text">&nbsp;</td>
								<td class="textRight"><s:label
									value="%{getText('label.SenderCorrespondent')}"></s:label>:&nbsp;
								</td>
								<td class="text">
									<s:textfield name="senderCorrespondent" readonly="true"
										id="senderCorrespondent" cssClass="text"
										value="%{ngphCanonical.senderCorrespondent}"></s:textfield>
								</td>
								<td class="text">&nbsp;</td>
							</tr>
							<tr>
								<td class="textRight"><s:label
									value="%{getText('label.Intermediary1BankCode')}"></s:label>:&nbsp;
								</td>
								<td class="text">
									<s:textfield name="intermediary1Bank" id="intermediary1Bank" readonly="true"
										cssClass="text" value="%{ngphCanonical.intermediary1Bank}"></s:textfield>
								</td>
								<td class="text">&nbsp;</td>
								<td class="textRight"><s:label
									value="%{getText('label.intermediary1BankName')}"></s:label>:&nbsp;
								</td>
								<td class="text">
									<s:textfield name="intermediary1BankName" readonly="true"
										id="intermediary1BankName" cssClass="text"
										value="%{ngphCanonical.intermediary1BankName}"></s:textfield>
								</td>
								<td class="text">&nbsp;</td>
							</tr>
							<tr>
								<td class="textRight"><s:label
									value="%{getText('label.Intermediary1BankAccount')}"></s:label>:&nbsp;
								</td>
								<td class="text">
									<s:textfield name="intermediary1AgentAcct"
										id="intermediary1AgentAcct" cssClass="text" readonly="true"
										value="%{ngphCanonical.intermediary1AgentAcct}"></s:textfield>
								</td>
								<td class="text">&nbsp;</td>
								<td class="textRight"><s:label
									value="%{getText('Label.AccountWithInstitutionAccount')}"></s:label>:&nbsp;
								</td>
								<td class="text">
									<s:textfield name="accountWithInstitutionAcct"
										id="accountWithInstitutionAcct" cssClass="text" readonly="true"
										value="%{ngphCanonical.accountWithInstitutionAcct}"></s:textfield>
								</td>

								<td class="text">&nbsp;</td>
							</tr>
							<tr>
								<td class="textRight"><s:label
									value="%{getText('label.senderCorrespondentAcct')}"></s:label>:&nbsp;
								</td>
								<td class="text">
									<s:textfield name="senderCorrespondentAcct" readonly="true"
										id="senderCorrespondentAcct" cssClass="text"
										value="%{ngphCanonical.senderCorrespondentAcct}"></s:textfield>
								</td>
								<td class="text">&nbsp;</td>


								<td class="textRight"><s:label
									value="%{getText('label.receiverCorrespondent')}"></s:label>:&nbsp;
								</td>
								<td class="text">
									<s:textfield name="receiverCorrespondent" readonly="true"
										id="receiverCorrespondent" cssClass="text"
										value="%{ngphCanonical.receiverCorrespondent}"></s:textfield>
								</td>
								<td class="text">&nbsp;</td>
							</tr>
							<tr>
								<td class="textRight"><s:label
									value="%{getText('label.receiverCorrespondentAcct')}"></s:label>:&nbsp;
								</td>
								<td class="text">
									<s:textfield name="receiverCorrespondentAcct" readonly="true"
										id="receiverCorrespondentAcct" cssClass="text"
										value="%{ngphCanonical.receiverCorrespondentAcct}"></s:textfield>
								</td>
								<td class="text">&nbsp;</td>
								<td class="textRight"><s:label
									value="%{getText('label.thirdCorrespondent')}"></s:label>:&nbsp;
								</td>
								<td class="text">
									<s:textfield name="thirdCorrespondent" id="thirdCorrespondent" readonly="true"
										cssClass="text" value="%{ngphCanonical.thirdCorrespondent}"></s:textfield>
								</td>
								<td class="text">&nbsp;</td>
							</tr>
							<tr>

								<td class="textRight"><s:label
									value="%{getText('label.thirdCorrespondentAcct')}"></s:label>:&nbsp;
								</td>
								<td class="text">
									<s:textfield name="thirdCorrespondentAcct"
										id="thirdCorrespondentAcct" cssClass="text" readonly="true"
										value="%{ngphCanonical.thirdCorrespondentAcct}"></s:textfield>
								</td>
								<td class="text">&nbsp;</td>
								<td class="textRight"><s:label
									value="%{getText('label.InstructionCodeforCreditorAgent')}"></s:label>:&nbsp;
								</td>
								<td class="text">
									<s:textfield name="instructionsForCrdtrAgtCode" readonly="true"
										id="instructionsForCrdtrAgtCode" cssClass="text"
										value="%{ngphCanonical.instructionsForCrdtrAgtCode}"></s:textfield>
								</td>
								<td class="text">&nbsp;</td>
							</tr>
							<tr>
								<td class="textRight"><s:label
									value="%{getText('label.Intermediary2BankCode')}"></s:label>:&nbsp;
								</td>
								<td class="text">
									<s:textfield name="intermediary2Bank" id="intermediary2Bank" readonly="true"
										cssClass="text" value="%{ngphCanonical.intermediary2Bank}"></s:textfield>
								</td>
								<td class="text">&nbsp;</td>
								<td class="textRight"><s:label
									value="%{getText('label.intermediary2BankName')}"></s:label>:&nbsp;
								</td>
								<td class="text">
									<s:textfield name="intermediary2BankName" readonly="true"
										id="intermediary2BankName" cssClass="text"
										value="%{ngphCanonical.intermediary2BankName}"></s:textfield>
								</td>
								<td class="text">&nbsp;</td>
							</tr>
						</table>
					</s:div> <!-- Group Four Div Ends --></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td class="text">
				
				<input type="button" value="Cancel"
					onclick="CallPaymentRepairCancel()" class="btn" />
			</td>
		</tr>
	</table>
	</div>
	</div>
	</div>
</s:form>
