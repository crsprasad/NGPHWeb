<%@page import="com.opensymphony.xwork2.ActionContext"%>
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<%@taglib uri="http://displaytag.sf.net" prefix="display"%>
<sx:head parseContent="true" />
<s:head theme="simple" />
<script type="text/javascript">
	function prnTbl() {

		alert(document.getElementById('payMessage'));

		var printContent = document.getElementById("payMessage");

		var windowUrl = 'about:blank';
		var stringelmt = "MSGTYPE : MT 103 \n Channel : SWIFT \n Trans Ref : MTO-793-001481 \n Sender : VYSAINBB \n Reciver :  \n Currency : USD \n Val Date : 2011-08-08 12:45:14  \n Amount : 300011"
		var num;

		var uniqueName = new Date();

		var windowName = 'Print' + uniqueName.getTime();
		var printWindow = window.open(num, windowName,
				'left=50000,top=50000,width=0,height=0');

		printWindow.document.write(printContent);

		printWindow.document.close();

		printWindow.focus();

		printWindow.print();

		printWindow.close();

	}
	function callRepairAction() {

		document.forms[0].action = "ViewMessage";
		document.forms[0].submit();

	}

	function callPaymentStatus()
	{
		var paymentStatus = document.getElementById("enquiryPaymentStatus");
		var paymentStatusValue = paymentStatus.options[paymentStatus.selectedIndex].value;
		if(paymentStatusValue=="COMPLETED")
			{
				document.getElementById("paymentStatusCompleted").value=paymentStatusValue;
			}
		}
</script>
<div id="contentPane">
<div id="contentPaneTop"></div>
<div id="contentPaneFluid">
<div id="contentPaneInternal"><span class="crumbs"><a
	href="#">Enquiries & Reports</a>&nbsp;>&nbsp;Payment Enquiry</span></div>
<div id="content">
<h1>Payment Enquiry</h1>
<s:form method="post" name="enquiryForm" validate="true">
	<s:hidden id="payMessage" name="payMessage"></s:hidden>
	<s:hidden name="enquiryScreen" id="enquiryScreen" value="PaymentEnquiry"></s:hidden>
	<s:hidden name="paymentStatusCompleted" id="paymentStatusCompleted" ></s:hidden>
	<div><s:if test="hasFieldErrors()">
				<div class="errorMsg"><span class="errorMsgInline"><s:actionerror /><s:fielderror /></span></div>
			</s:if></div><!--Error Msg Div Ends -->
	<div id="CollapsiblePanel4" class="CollapsiblePanel">
	<div class="CollapsiblePanelTab"><!--  <img src="images/openPanel.png" width="9" height="7" alt="openPanel" />-->&nbsp;
	Search Filter</div>
	<div class="CollapsiblePanelContent"><!-- Group Four Div Starts -->
	<s:div id="insCurrency" cssClass="dataGrid" style="width:100%;">
		<table width="100%" cellpadding="5" cellspacing="1" border="0"
			bgcolor="#ffffff">
			<tr>
				<s:set name="channelDropDown" value="%{channelDropDown}" />				
				<tr>

					<td width="20%" class="textRight"><s:label
						value="%{getText('label.Channel')}"></s:label>:&nbsp;</td>
					<td width="30%" class="text"><s:select list="#session.channelDropDown"
						name="enquiryChannel" cssClass="txtField" id="enquiryChannel"
						headerKey="NONE" headerValue="Select Channel" key="label.Channel"></s:select></td>
					<td class="textRight"><s:label
						value="%{getText('label.ruleMsgtype')}"></s:label>:&nbsp;</td>
					<td class="text"><s:select list="#session.msgTypeDropDown"
						cssClass="txtField" name="enquiryMsgType" id="enquiryMsgtype"
						headerKey="NONE" headerValue="Select Message Type"
						key="label.ruleMsgtype"></s:select></td>
					
				</tr>
				
				<tr>
					<td class="textRight"><s:label
						value="%{getText('label.subMessage')}"></s:label>:&nbsp;</td>
					<td class="text"><s:select list="#session.subMsgTypeDropDown"
						cssClass="txtField" name="enquirySubMsgType"
						id="enquirySubMsgType" headerKey="NONE"
						headerValue="Select Sub Message Type" key="label.subMessage"></s:select></td>
					<td class="textRight"><s:label
						value="%{getText('label.valueDate')}"></s:label>:&nbsp;</td>
					<td class="text"><sx:datetimepicker name="enquiryValueDate"
						cssClass="txtField" displayFormat="MM/dd/yy" value="today" /></td>
				</tr>
				<tr>
					<td class="textRight"><s:label
						value="%{getText('label.currency')}"></s:label>:&nbsp;</td>
					<td class="text"><s:select list="#session.currencyDropDown"
						name="enquiryCurrency" cssClass="txtField" id="enquiryCurrency"
						headerKey="NONE" headerValue="Select Currency"
						key="label.currency"></s:select></td>
					<td class="textRight"><s:label
						value="%{getText('label.transactionReference')}"></s:label>:&nbsp;</td>
					<td class="text"><s:textfield
						name="enquiryTransactionReference" cssClass="txtField"
						id="transactionReferenceTxt" key="label.transactionReference"></s:textfield></td>
				</tr>
				<tr>
					<td class="textRight">&nbsp;<s:label
						value="%{getText('label.paymentStatus')}"></s:label>:&nbsp;</td>
					<td class="text"><s:select
						list="#session.paymentStatus"
						name="enquiryPaymentStatus" cssClass="txtField"
						id="enquiryPaymentStatus" headerKey="NONE"
						headerValue="Select Payment Status" onchange="callPaymentStatus();"></s:select></td>
					<td class="textRight"><s:label
						value="%{getText('label.orderingCustomer')}"></s:label>:&nbsp;</td>
					<td class="text"><s:textfield name="enquiryOrderingCustomer"
						cssClass="txtField" id="enquiryOrderingCustomer"
						key="label.orderingCustomer"></s:textfield></td>
				</tr>
				<tr>
					<td class="textRight"><s:label
						value="%{getText('label.Direction')}"></s:label>:&nbsp;</td>
					<td class="text"><s:radio name="enquiryBound" list="#{'I':'I','O':'O'}"
						value="true" key="label.Direction" value="%{enquiryBound}"/></td>
					<td class="textRight"><s:label
						value="%{getText('label.beneficiaryCustomer')}"></s:label>:&nbsp;</td>
					<td class="text"><s:textfield
						name="enquiryBeneficiaryCustomer" cssClass="txtField"
						id="beneficiaryCustomerTxt" key="label.beneficiaryCustomer"></s:textfield></td>
				</tr>
				<tr>
					<td class="textRight"><s:label
						value="%{getText('label.Narration')}"></s:label>:&nbsp;</td>
					<td class="text"><s:textfield
						name="enquiryNarration" id="NarrationTxt" cssClass="txtField"
						key="label.Narration"></s:textfield></td>
					<td class="textRight"></td>
					<td class="text"></td>
				</tr>
				</table></s:div></div></div>
			<div class="clearfix"></div>
	<table width="100%" cellpadding="0" cellspacing="0" border="0">
		<tr>
			<td align="center">
					<s:submit value="Search" action="enquirySearch" cssClass="btn"></s:submit>
					<input type="reset" value="Cancel" class="btn" /></td>
				</tr>
				</table>
				<!--  button panel ends -->
	</s:form>
</div>
</div>
<div id="contentPaneBottom"></div>