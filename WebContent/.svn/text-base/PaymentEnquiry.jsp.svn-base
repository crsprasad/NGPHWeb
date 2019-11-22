<%@page import="com.opensymphony.xwork2.ActionContext"%>
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<%@taglib uri="http://displaytag.sf.net" prefix="display"%>
<style type="text/css">
table.sample {
	border-width: 1px 1px 1px 1px;
	border-spacing: 2px;
	border-style: outset outset outset outset;
	border-color: gray gray gray gray;
	border-collapse: separate;
	background-color: white;
}

table.sample th {
	border-width: 1px 1px 1px 1px;
	padding: 1px 1px 1px 1px;
	border-style: inset inset inset inset;
	border-color: gray gray gray gray;
	background-color: white;
	-moz-border-radius: 0px 0px 0px 0px;
}

table.sample td {
	border-width: 1px 1px 1px 1px;
	padding: 1px 1px 1px 1px;
	border-style: inset inset inset inset;
	border-color: gray gray gray gray;
	background-color: white;
	-moz-border-radius: 0px 0px 0px 0px;
}
</style>
<script language="javascript" type="text/javascript"
	src="js/datetimepicker.js">
	//Date Time Picker script- by TengYong Ng of http://www.rainforestnet.com
	//Script featured on JavaScript Kit (http://www.javascriptkit.com)
	//For this script, visit http://www.javascriptkit.com
</script>


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
	function callViewDiplay() {
		window.open(
				"<s:url action='viewMessagePage' windowState='EXCLUSIVE' />",
				'mywindow', 'top=50,left=250,width=750,height=620');

	}

	function callSearch(enquirySearchAction) {

		document.getElementById("enquirySearch").value = enquirySearchAction;
	}

	function printCall() {
		document.getElementById("enquiryPrint").value = 'enquiryPrint';
		Alert();
	}

	function validateCheckBox() {

		var checkBox = document.getElementById("checkBox");

		if (checkBox.checked) {
			window.location = "paymentMessage";
		} else {
			alert("Please select transaction for action to be applicable");
		}

	}
</script>

<sx:head parseContent="true" />
<s:head theme="simple" />
<s:form action="enquiryAction" method="post" name="enquiryForm"
	validate="true">
	<s:hidden id="payMessage" name="payMessage"></s:hidden>
	<div id="colCont"><!-- Content Pane Starts -->
	<div id="col3" style="width: 99%">&nbsp;Payment Enquiry
	<div id="content"><s:actionerror /><s:fielderror />
	<table width="50%" cellpadding="0" cellspacing="0" border="0">
		<tr>
			<td class="divBg">
			<table width="100%" cellpadding="5" cellspacing="1" border="0"
				bgcolor="#c5d6f0">

				<s:set name="channelDropDown" value="%{channelDropDown}" />
				<tr>

					<td width="40%" class="textRight">Select Channel:&nbsp;</td>
					<td width="60%" class="text"><s:select list="{'SWIFT'}"
						name="enquiryChannel" cssClass="text" id="enquiryChannel"
						headerKey="NONE" headerValue="Select Channel" key="label.Channel"></s:select></td>
				</tr>
				<tr>
					<td class="textRight">Select Message Type:&nbsp;</td>
					<td class="text"><s:select list="#session.msgTypeDropDown"
						cssClass="text" name="enquiryMsgType" id="enquiryMsgtype"
						headerKey="NONE" headerValue="Select Message Type"
						key="label.ruleMsgtype"></s:select></td>
				</tr>
				<tr>
					<td class="textRight">Select Sub-Message Type:&nbsp;</td>
					<td class="text"><s:select list="#session.subMsgTypeDropDown"
						cssClass="text" name="enquirySubMsgType" id="enquirySubMsgType"
						headerKey="NONE" headerValue="Select Sub Message Type"
						key="label.subMessage"></s:select></td>
				</tr>
				<tr>
					<td class="textRight">Select Currency:&nbsp;</td>
					<td class="text"><s:select list="{'INR','USD'}"
						name="enquiryCurrency" cssClass="text" id="enquiryCurrency"
						headerKey="NONE" headerValue="Select Currency"
						key="label.currency"></s:select></td>
				</tr>
				<tr>
					<td class="textRight">&nbsp;</td>
					<td class="text"><s:select
						list="{'None','RECV','Repair','WAREHOUSED','Accounting Called','Accounted','LM called','LM Completed','AML Called'
		,'AML Completed','Billing Called','Billing Completed'}"
						key="label.paymentStatus" name="enquiryPaymentStatus"
						cssClass="text" id="enquiryPaymentStatus" headerKey="NONE"
						headerValue="Select Payment Status"></s:select></td>
				</tr>
				<tr>
					<td class="textRight">Enquiry Bound:&nbsp;</td>
					<td class="text"><s:radio name="enquiryBound" list="{'I','O'}"
						value="true" key="label.Direction" /></td>
				</tr>
				<tr>
					<td class="textRight">Select From Date:&nbsp;</td>
					<td class="text"><sxatetimepicker name="enquiryFromDate"
						cssClass="text" displayFormat="MM/dd/yy" value="today" /></td></tr>
					

					<td class="textRight"><sx:datetimepicker
						name="enquiryFromDate" cssClass="textRight"
						displayFormat="MM/dd/yy" value="today" label="Select From Date:" />
					<sx:datetimepicker name="enquiryTODate" cssClass="text"
						displayFormat="MM/dd/yy" startDate="enquiryFromDate"
						label="Select To Date:" /> <sx:datetimepicker
						name="enquiryValueDate" cssClass="text" displayFormat="MM/dd/yy"
						value="today" label="Select Value Date(yyyy-mm-dd):" /></td>
				</tr>
				<tr>
					<td class="textRight">Transaction Reference:&nbsp;</td>
					<td class="text"><s:textfield
						name="enquiryTransactionReference" cssClass="text"
						id="transactionReferenceTxt" key="label.transactionReference"></s:textfield></td>
				</tr>
				<tr>
					<td class="textRight">Ordering Customer:&nbsp;</td>
					<td class="text"><s:textfield name="enquiryOrderingCustomer"
						cssClass="text" id="enquiryOrderingCustomer"
						key="label.orderingCustomer"></s:textfield></td>
				</tr>
				<tr>
					<td class="textRight">Beneficiary Customer:&nbsp;</td>
					<td class="text"><s:textfield
						name="enquiryBeneficiaryCustomer" cssClass="text"
						id="beneficiaryCustomerTxt" key="label.beneficiaryCustomer"></s:textfield></td>
				</tr>
				<tr>
					<td class="textRight">Narration:&nbsp;</td>
					<td class="text"><s:textfield name="enquiryNarration"
						id="NarrationTxt" cssClass="text" key="label.Narration"></s:textfield></td>
				</tr>
				<tr>
					<td colspan="2" class="textCenter"><s:submit value="Search"
						action="callEnquirySearchenquiryAction" cssClass="btn"
						onclick="callSearch('enquirySearch')"></s:submit> <input
						type="reset" value="Cancel" class="btn" /></td>


				</tr>

			</table>
			</td>
		</tr>
	</table>
	<table width="100%" cellpadding="3" cellspacing="1" border="0"
		bgcolor="#ffffff" id="table1">
		<tr>
			<td class="divBg"><display:table uid="row"
				name="sessionScope.searchList" pagesize="10"
				requestURI="/EnquiryList" cellpadding="3" cellspacing="0">

				<display:column title="MSGTYPE" property="msgType"
					headerClass="gridHdrBg" class="gridText" sortable="true" />

				<display:column title="Channel" property="msgChannel"
					headerClass="gridHdrBg" class="gridText" sortable="true" />
				<display:column title="Trans Ref" property="txnReference"
					headerClass="gridHdrBg" class="gridText" sortable="true" />
				<display:column title="Sender" property="senderBank"
					headerClass="gridHdrBg" class="gridText" sortable="true" />
				<display:column title="Receiver" property="receiverBank"
					headerClass="gridHdrBg" class="gridText" sortable="true" />
				<display:column title="Currency" property="msgCurrency"
					headerClass="gridHdrBg" class="gridText" sortable="true" />
				<display:column title="Val Date" property="msgValueDate"
					headerClass="gridHdrBg" class="gridText" sortable="true" />
				<display:column title="Amount" property="msgAmount" sortable="true"
					headerClass="gridHdrBg" class="gridText" />
				<display:column title="Ordering Customer" headerClass="gridHdrBg"
					class="gridText" property="orderingCustomer" maxLength="10"
					sortable="true" />
				<display:column title="Beneficiary Customer" headerClass="gridHdrBg"
					class="gridText" property="beneficiaryCustomer" sortable="true" />
				<display:column title="Narration" property="narration"
					headerClass="gridHdrBg" class="gridText" sortable="true" />
				<display:column>

					<s:checkbox name="select[%{#attr.row_rowNum -1 }]"
						value="select[%{#attr.row_rowNum - 1 }]" id="payCheckBox"
						theme="simple" />
					<input type="hidden" name="payMessage" value="${row.msgRef}" />
					<input type="hidden" name="transactionReference"
						value="${row.txnReference}" />

				</display:column>
				<display:column paramId="msgIndex" paramName="msgIndex">
					<input type="button" class="btn" name="msgRef" value="view"
						onclick="callRepairAction('${row.msgRef}')" />

				</display:column>

			</display:table></td>
		</tr>

	</table>
	</div>
	</div>
	<!-- Content Pane Ends --></div>
</s:form>
