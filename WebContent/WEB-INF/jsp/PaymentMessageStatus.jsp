<%@page language="java"
	import="java.util.*,com.logica.ngph.web.action.*"
	contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<script type="text/javascript">
	var appendedValues = "";
	function getPaymentMessage(paymentStatus, msgDirection) {
		//alert(paymentStatus);
		//alert(msgDirection);

		document.getElementById("paymentStatus").value = paymentStatus;
		document.getElementById("msgDirec").value = msgDirection;
		//document.forms["PS"].paymentMessageStatus.value = paymentMessage;
		//document.forms["PS"].submit();
		//window.location = "paymentMessage";

	}

	

	function validateCheckBox(obj, data) {

		//alert(obj.value);

		//var obj = document.getElementById("msgIndex");

		//var appendedVal = obj.value + "," + data;
		//alert(document.productForm.paymentCheckBox.length);
		//document.getElementById("msgIndex").value = appendedVal;
		//alert(document.getElementById("msgIndex").value);

	}
	function callRepairAction(msgReference) {

		document.getElementById("msgIndex").value = msgReference;
		document.forms[0].action = "paymentAwaitingRepair";
		document.forms[0].submit();

	}
	function callRepair(msgRef,msgType)
	{
		document.getElementById("msgIndex").value = msgRef;
		document.getElementById("paymentMessageType").value = msgType;
		
		
			document.productForm.action = "repairPreAdvice";
		
		document.productForm.submit();
			
	}
	function callPaymentView(msgReference) {

		document.getElementById("msgIndex").value = msgReference;
		document.forms[0].action = "authorizationView";
		document.forms[0].submit();

	}
	function callAction(action){
		
		document.getElementById("userAction").value = action;
		document.forms[0].action = "paymentStatusChange";
		document.forms[0].submit();
		}
	function callFilter()
	{
		//document.getElementById("paymentStatus").value=document.getElementById("payStatus").value;
		alert(document.getElementById("payStatus").value);
		document.forms[0].action = "getPaymentMessageQueue";
		document.forms[0].submit();
	}
	function callDeteleRepairedAction() {

		//window
			//	.open(
				//		"<s:url action='showDeleteRepairPopup' windowState='EXCLUSIVE' />",
					//	'mywindow', 'top=500,left=250,width=405,height=202');
		document.forms[0].action = "deletePayment";
		document.forms[0].submit();
	}

	
</script>
<s:head />

<div id="contentPane">
<div id="contentPaneTop"></div>
<div id="contentPaneFluid">
<div id="contentPaneInternal"><span class="crumbs">

<a href="#"><s:label id="msgDirction" name="msgDirction"></s:label></a>&nbsp;>&nbsp;<s:label name="#session.status" id="paymentStatusCrumbs"></s:label></span></div>
<div id="content">
<h1>Payment Message Status</h1>
<s:form action="paymentMessage" name="productForm" method="post">
<div><s:label name="message"></s:label><s:if test="hasFieldErrors()">
				<div class="errorMsg"><span class="errorMsgInline"><s:actionerror /><s:fielderror /></span></div>
			</s:if></div><!--Error Msg Div Ends -->
<s:hidden id="paymentStatus" name="paymentStatus"></s:hidden>
	<s:hidden id="msgDirec" name="msgDirec"></s:hidden>
	<s:hidden id="msgIndex" name="msgIndex"></s:hidden>
	<s:hidden id="userAction" name="userAction"></s:hidden>
	<s:hidden name ="paymentMessageType" id="paymentMessageType"></s:hidden>
	<s:hidden id="msgReference" name="msgReference"></s:hidden>
	<s:hidden id="deleteComments" name="deleteComments"></s:hidden>
	<s:hidden id ="paymentDirection" name="paymentDirection" value="%{#session.paymentStatusVal}"></s:hidden>
	<table width="99%" cellpadding="0" cellspacing="0" border="0">
		<tr>
			<td>
			<table width="95%" cellpadding="5" cellspacing="1" border="0" bgcolor="#c5d6f0" id="table1">
				<tr>
				<td width="20%" class="textRight"><s:label
						value="Channel"></s:label>:&nbsp;</td>
					<td width="20%" class="text"><s:select list="channelDropDown"
						name="channel" cssClass="txtField" id="channel"
						headerKey="" headerValue="Select Channel" key="label.Channel"></s:select> </td>
			<td width="20%" class="textRight"><s:label
						value="Message Type"></s:label>:&nbsp;</td>
					<td width="20%" class="text">
						<s:select list="msgTypeDropDown"
						cssClass="txtField" name="msgType" id="msgType"
						headerKey="" headerValue="Select Message Type"
						></s:select>
					</td>
					<td width="20%" class="textRight"><s:label
						value="Sub Message Type"></s:label>:&nbsp;</td>
					<td width="30%" class="text"><s:select list="subMsgTypeDropDown"
						cssClass="txtField" name="subMsgType"
						id="subMsgType" headerKey=""
						headerValue="Select Sub Message Type" key="label.subMessage"></s:select> </td>
					
						<td width="30%" class="text">
						<s:submit value="GO" action="getPaymentMessageQueue" cssClass="btn"></s:submit>
						</td>
					
	</tr></table>
		<tr>
			<td><s:submit value="Refresh" action="getPaymentMessageQueue" cssClass="btn"></s:submit>
			</td>
		</tr>
		<tr>
			<td><br/><display:table id="payment" uid="row"
				name="sessionScope.paymentMessageList" pagesize="10"
				requestURI="/getPaymentMessageQueue" cellpadding="5" cellspacing="0"
				class="dataGrid">
				 <display:setProperty name="basic.msg.empty_list" 
    				value='<div class="infoMsg"><span>Nothing found to display</span></div>' />
		 <display:setProperty name="css.tr.even" value="even" />
				<display:setProperty name="css.tr.odd" value="odd" />
				
				<%
					
					if(PaymentMessageStatusAction.direction.equals("I")){
					
				%>
					<display:column title="Msg Type" property="msgType"
						headerClass="gridHdrBg" sortable="true" />
					
					<display:column title="Channel" property="msgChannel"
						headerClass="gridHdrBg" sortable="true" />
				<%}else if(PaymentMessageStatusAction.direction.equals("O")){ 
					
				%>
				<s:if test="%{#session.paymentStatusVal !='AWAITING_REPAIR'}">
					<display:column title="Msg Type" property="msgType"
						headerClass="gridHdrBg" sortable="true" />
					<display:column title="Channel" property="msgChannel"
						headerClass="gridHdrBg" sortable="true" />	
				</s:if>
				<s:if test="%{#session.paymentStatusVal =='AWAITING_REPAIR'}">
					<display:column title="Msg Type" property="srcMSGType"
						headerClass="gridHdrBg" sortable="true" />
				
					<display:column title="Channel" property="srcChannel"
						headerClass="gridHdrBg" sortable="true" />
				</s:if>
				<%} %>
				<display:column title="Trans. Ref." property="txnReference"
					headerClass="gridHdrBg"  sortable="true" />
				<display:column title="Sender" property="senderBank"
					headerClass="gridHdrBg"  sortable="true" />
				<display:column title="Receiver" property="receiverBank"
					headerClass="gridHdrBg"  sortable="true" />
				<display:column title="Currency" property="msgCurrency"
					headerClass="gridHdrBg"  sortable="true" />
				<display:column title="Value Date" property="msgValueDate"
					headerClass="gridHdrBg"  sortable="true" />
				<display:column title="Last Modified Time" property="lastModTime"
					headerClass="gridHdrBg"  sortable="true" />
					
				<display:column title="Amount" property="msgAmount"
					headerClass="gridHdrBg"  sortable="true" />
				
				<display:column title="Ordering Customer"
					property="orderingCustomer" maxLength="100" headerClass="gridHdrBg"
					 />
				<display:column title="Beneficiary Account"
					property="beneficiaryAccountNo" headerClass="gridHdrBg"/>	 
					 
				<display:column title="Beneficiary Customer"
					property="beneficiaryCustomer" maxLength="100"
					headerClass="gridHdrBg"  />
					<display:column title="Transaction Type" property="txnType"
					headerClass="gridHdrBg"  sortable="true" />
				<s:if test="%{#session.paymentStatusVal =='REJECTED_I'}">
				<display:column title="Error Code"
					property="errorCode" maxLength="100"
					headerClass="gridHdrBg"  />
				</s:if>
				<s:if test="%{#session.paymentStatusVal =='REJECTED_BY_CHANNEL'}">
				<display:column title="Error Code"
					property="errorCode" maxLength="100"
					headerClass="gridHdrBg"  />
				</s:if>
				<s:if test="%{#session.paymentStatusVal =='AWAITING_REPAIR'}">
				<display:column title="Error Code"
					property="errorCode" maxLength="100"
					headerClass="gridHdrBg"  />
				</s:if>		
				<s:if test="%{#session.paymentStatusVal =='AWAITING_REPAIR_I'}">
				<display:column title="Error Code"
					property="errorCode" maxLength="100"
					headerClass="gridHdrBg"  />
				</s:if>				
				<display:column headerClass="gridHdrBg">
				<s:checkbox name="select[%{#attr.row_rowNum -1 }]"
					value="select[%{#attr.row_rowNum - 1 }]" id="payCheckBox"
					theme="simple" />
				<input type="hidden" name="payMessage" value="${row.msgRef}" />
				<input type="hidden" name="transactionReference"
					value="${row.txnReference}" />
			</display:column>
			<s:if test="%{#session.paymentStatusVal =='AWAITING_REPAIR'}">
				<display:column paramId="msgIndex" paramName="msgIndex" headerClass="gridHdrBg">
					<input type="button" name="msgRef" class="btn"
						value="${session.gridButtonName}"
						onclick="callRepair('${row.msgRef}','${row.srcMSGType}')" />
				</display:column>
			</s:if>
			<s:if test="%{#session.paymentStatusVal =='AWAITING_AUTHORISATION'}">
				<display:column paramId="msgIndex" paramName="msgIndex" headerClass="gridHdrBg">
					<input type="button" name="msgRef" class="btn"
						value="${session.gridButtonName}"
						onclick="callPaymentView('${row.msgRef}')" />
				</display:column>
			</s:if>
			</display:table></td>
		</tr>
		</table>
		<div class="clearfix">
	<table width="99%" cellpadding="10" cellspacing="0" border="0">
		<tr>
			<td><s:if
				test="%{#session.paymentStatusVal =='AWAITING_REPAIR'}">
				<!--<input type="button" value="Delete" class="btn"
					onclick="callDeteleRepairedAction()" />
			--></s:if> <s:elseif
				test="%{#session.paymentStatusVal =='AWAITING_ACCOUNTING'}">
				<s:submit value="Mark as Accounting Completed" theme="simple"
					cssClass="btn" action="paymentStatusChange"></s:submit>
			</s:elseif> <s:elseif test="%{#session.paymentStatusVal =='WAREHOUSED'}">
				<s:submit id ="%{getText('function.access.moveToRepair')}" value="Move to Repair" theme="simple" cssClass="btn"
					action="paymentStatusChange"></s:submit>
			</s:elseif> <s:elseif
				test="%{#session.paymentStatusVal =='ACCOUNTING_COMPLETED'}">

			</s:elseif> <s:elseif test="%{#session.paymentStatusVal =='AWAITING_LIQUIDITY'}">
				<s:submit value="Mark as LM Completed" theme="simple" cssClass="btn"
					action="paymentStatusChange"></s:submit>
			</s:elseif> <s:elseif
				test="%{#session.paymentStatusVal =='AWAITING_LIQUIDITY_I'}">
				<s:submit value="Mark as LM Completed" theme="simple" cssClass="btn"
					action="paymentStatusChange"></s:submit>
			</s:elseif> <s:elseif
				test="%{#session.paymentStatusVal =='LIQUIDITY_COMPLETED'}">

			</s:elseif> <s:elseif test="%{#session.paymentStatusVal =='AWAITING_AML'}">
				<s:submit value="Mark as LM Completed" theme="simple" cssClass="btn"
					action="paymentStatusChange"></s:submit>
			</s:elseif> <s:elseif test="%{#session.paymentStatusVal =='AWAITING_AML_I'}">
				<s:submit value="Mark as LM Completed" theme="simple" cssClass="btn"
					action="paymentStatusChange"></s:submit>
			</s:elseif> <s:elseif test="%{#session.paymentStatusVal =='AML_Completed'}">

			</s:elseif> <s:elseif test="%{#session.paymentStatusVal =='Awaiting_Billing'}">
				<s:submit value="Mark as Billing Completed" theme="simple"
					cssClass="btn" action="paymentStatusChange"></s:submit>
			</s:elseif> <s:elseif test="%{#session.paymentStatusVal =='AWAITING_BILLING_I'}">
				<s:submit value="Mark as Billing Completed" theme="simple"
					cssClass="btn" action="paymentStatusChange"></s:submit>
			</s:elseif> <s:elseif test="%{#session.paymentStatusVal =='Billing_Completed'}">

			</s:elseif> <s:elseif
				test="%{#session.paymentStatusVal =='Awaiting_Acknowledgement'}">
				<s:submit value="Generate Cancellation" theme="simple"
					cssClass="btn" action="paymentStatusChange"></s:submit>
			</s:elseif> <s:elseif test="%{#session.paymentStatusVal =='COMPLETED'}">
				<s:submit value="Mark as Finalised" theme="simple" cssClass="btn"
					action="paymentStatusChange"></s:submit>
			</s:elseif> <s:elseif test="%{#session.paymentStatusVal =='FINALISED'}">

			</s:elseif> <s:elseif test="%{#session.paymentStatusVal =='AWAITING_RELEASE'}">
				<s:submit value="Release" theme="simple"
					action="paymentStatusChange" cssClass="btn"></s:submit>
			</s:elseif> <s:elseif test="%{#session.paymentStatusVal =='EXCEPTIONS'}"><!--
				<s:submit id ="%{getText('function.access.moveToRepair')}" value="Move to Repair" theme="simple"
					 action="paymentStatusChange" cssClass="btn"></s:submit>
				--><s:submit  value="Reprocess" theme="simple"
					action="paymentStatusChange" cssClass="btn"></s:submit>
			</s:elseif>
			
			<s:elseif test="%{#session.paymentStatusVal =='REJECTED_I'}"><!--
				<s:submit id ="%{getText('function.access.moveToRepair')}" value="Move to Repair" theme="simple"
					 action="paymentStatusChange" cssClass="btn"></s:submit>
				<s:submit  value="Reprocess" 
					action="paymentStatusChange" cssClass="btn"></s:submit>-->
			</s:elseif>
			
			
			 <s:elseif
				test="%{#session.paymentStatusVal =='AWAITING_AUTHORISATION'}">
				<input type="button" value="Approve" onclick ="callAction('Approve')" >
				<input type="button" value="Reject"  onclick ="callAction('Reject')" >
			</s:elseif> <s:elseif
				test="%{#session.paymentStatusVal =='REJECTED_BY_CHANNEL'}">
				<!--<s:submit value="Delete" class="btn" theme="simple"
					action="paymentStatusChange" cssClass="btn"></s:submit>
				<s:submit id ="%{getText('function.access.moveToRepair')}" value="Move to Repair" theme="simple"
					action="paymentStatusChange" cssClass="btn"></s:submit>
			--></s:elseif> <s:elseif test="%{#session.paymentStatusVal =='INVALIDS'}">
				<!--<s:submit value="Delete" class="btn" theme="simple"
					action="paymentStatusChange" cssClass="btn"></s:submit>
				<s:submit id ="%{getText('function.access.moveToRepair')}" value="Move to Repair" theme="simple"
					action="paymentStatusChange" cssClass="btn"></s:submit>
				<s:submit id="%{getText('function.access.reProcess')}" value="Reprocess" theme="simple"
					action="paymentStatusChange" cssClass="btn"></s:submit>
			</s:elseif> <s:elseif test="%{#session.paymentStatusVal =='Received'}">
				<s:submit value="Select Host" theme="simple"
					action="paymentStatusChange" cssClass="btn"></s:submit>-->
			</s:elseif> <s:elseif test="%{#session.paymentStatusVal =='INTERVENED'}">
				<s:submit id ="%{getText('function.access.moveToRepair')}" value="Move to Repair" theme="simple"
					action="paymentStatusChange" cssClass="btn"></s:submit>
				<s:submit value="Return" theme="simple" action="paymentStatusChange"
					cssClass="btn"></s:submit>
				<s:submit value="Mark as Processed" theme="simple"
					action="paymentStatusChange" cssClass="btn"></s:submit>
			</s:elseif> <s:elseif test="%{#session.paymentStatusVal =='AWAITING_REPAIR_I'}">
				<s:submit value="Modify" theme="simple" action="paymentStatusChange"
					cssClass="btn"></s:submit>
			</s:elseif> <s:elseif
				test="%{#session.paymentStatusVal =='RETURNED_I'}">
				
			</s:elseif> <s:elseif test="%{#session.paymentStatusVal =='SEND_TO_HOST_I'}">
				<!--<s:submit value="Generate Return" theme="simple"
					action="paymentStatusChange" cssClass="btn"></s:submit>
			--></s:elseif> 
			<s:elseif test="%{#session.paymentStatusVal =='PROCESSED_MANUALLY_I'}">
				<s:submit value="Generate Return" theme="simple"
					action="paymentStatusChange" cssClass="btn"></s:submit>
				<s:submit id ="%{getText('function.access.moveToRepair')}" value="Move to Repair" theme="simple"
					action="paymentStatusChange" cssClass="btn"></s:submit>
			</s:elseif> 
			<input type="button" value="Cancel" class="btn" /></td>
		</tr>
	</table>
	</div>
</s:form>
<!--<script type="text/javascript">
var str = document.getElementById("paymentDirection").value;


if(str.charAt(str.length-1)!="I"){
	
	document.getElementById("msgDirction").innerText="Outward Payment";
	}
else{
	
	document.getElementById("msgDirction").innerText="Inward Payment";
}

</script>
--></div>
</div>
</div>
<div id="contentPaneBottom"></div>







