<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<%@taglib uri="http://displaytag.sf.net" prefix="display"%>
<script type="text/javascript">
	function callRepairAction(count) {

		document.getElementById("seletecMessageCount").value=count;
		document.forms[0].action = "ViewMessage";
		document.forms[0].submit();

	}

	function saveTemplate(msgRef,msgType)
	{
		document.getElementById("messageRef").value=msgRef;
		document.getElementById("msgType").value=msgType;
		document.forms[0].action="fetchMsgData";
		document.forms[0].submit();
	}

	function callPrintPreView(msgRef,msgType)
	{
		document.getElementById("seletecMessageCount").value=msgRef;
		document.getElementById("histMsgRef").value=msgRef;
		document.getElementById("msgType").value=msgType;
		document.forms[0].action="enquiryPrintPreview";
		document.forms[0].submit();
		
		}
</script>
<div id="contentPane">
<div id="contentPaneTop"></div>
<div id="contentPaneFluid">
<div id="contentPaneInternal"><span class="crumbs"><a href="#">Enquiries & Reports</a>&nbsp;&nbsp;>View All Messages</span></div>
<div id="content">
<h1>View All Messages</h1>
<br />
<s:form method="post" name="enquiryForm" validate="true">

	<s:hidden id="seletecMessageCount" name="seletecMessageCount"></s:hidden>
	<s:hidden id="histMsgRef" name="histMsgRef"></s:hidden>
	<s:hidden id="payMessage" name="payMessage"></s:hidden>
	<s:hidden id="msgRef" name="msgRef"/>
	<s:hidden id="messageRef" name="messageRef"/>
	<s:hidden id="msgType" name="msgType"/>
	<table width="100%" cellpadding="3" cellspacing="1" border="0"
		bgcolor="#ffffff" id="table1">
		<tr><%int count=0; %>
			<td><display:table uid="row" name="sessionScope.searchList"
				pagesize="10" requestURI="/EnquiryList" cellpadding="3"
				cellspacing="0" class="dataGrid" >
				<display etProperty name="css.tr.even" value="TDBGlightgray" />
				<display etProperty name="css.tr.odd" value="TDBGgray" cssClass="odd"/>

				<display:column title="MSGTYPE" property="msgType"
					headerClass="gridHdrBg"  sortable="true" />

				<display:column title="Channel" property="msgChannel"
					headerClass="gridHdrBg"  sortable="true" />
				<display:column title="Status" property="paymentStatus"
					headerClass="gridHdrBg"  sortable="true" />
				<display:column title="Trans Ref" property="txnReference"
					headerClass="gridHdrBg"  sortable="true" />
				<display:column title="Sender" property="senderBank"
					headerClass="gridHdrBg"  sortable="true" />
				<display:column title="Receiver" property="receiverBank"
					headerClass="gridHdrBg"  sortable="true" />
				<display:column title="Currency" property="msgCurrency"
					headerClass="gridHdrBg"  sortable="true" />
				<display:column title="Val Date" property="msgValueDate"
					headerClass="gridHdrBg"  sortable="true" />
				<display:column title="Last Modified Time" property="lastModTime"
					headerClass="gridHdrBg"  sortable="true" />
				<display:column title="Amount" property="msgAmount" sortable="true"
					headerClass="gridHdrBg"  />
				<display:column title="Ordering Customer" headerClass="gridHdrBg"
					 property="orderingCustomer" maxLength="10"
					sortable="true" />
				<display:column title="Beneficiary Account"
					property="beneficiaryAccountNo" headerClass="gridHdrBg"/>
				<display:column title="Beneficiary Customer" headerClass="gridHdrBg"
					 property="beneficiaryCustomer" sortable="true" />
				<display:column paramId="msgIndex" paramName="msgIndex">
					<!--<s:checkbox name="select[%{#attr.row_rowNum -1 }]"
						value="select[%{#attr.row_rowNum - 1 }]" id="payCheckBox"
						theme="simple" />
					--><input type="hidden" name="payMessage" value="${row.msgRef}" />
					<input type="hidden" name="transactionReference"
						value="${row.txnReference}" />		
					<input type="button" class="btn" value="Print Preview" onclick="callPrintPreView('${row.msgRef}','${row.msgType}');"/>			
					<input type="button" class="btn" name="msgRef" value="View"
						onclick="callRepairAction('${row.msgRef}')" />
						<s:if test="%{#session.paymentStatusCompleted == 'COMPLETED'}">
							<input type="button" class="btn" name="tempalte" value="Save as Template" id="compbtn"
								onclick="saveTemplate('${row.msgRef}','${row.msgType}');" />
						</s:if>
					<% count++;%>
				</display:column>
 <!--<display:setProperty name="export.excel.filename" value="CustomerCreatedData.xls"/>
				 <display:setProperty name="export.csv.filename" value="CustomerCreatedData.csv"/>
    <display:setProperty name="export.pdf.filename" value="CustomerCreatedData.pdf"/>
   <display:setProperty name="export.pdf" value="true" />
			--></display:table></td>
		</tr>
<tr><td><input type="button" value="Cancel" onclick="javascript: history.go(-1)"/> </td></td></tr>
	</table>
</s:form></div>
</div>
</div>
<div id="contentPaneBottom"></div>
