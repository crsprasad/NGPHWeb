<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<%@taglib uri="http://displaytag.sf.net" prefix="display"%>

<script type="text/javascript">
	function setReportType(type) {
		document.getElementById("reportType").value =type;
		document.downloadReport.submit();
	}
</script>
<div id="contentPane">
<div id="contentPaneTop"></div>
<div id="contentPaneFluid">
<div id="contentPaneInternal"><span class="crumbs"><a
	href="#">Report</a>&nbsp;>&nbsp;Report</span></div>
<div id="content">
<h1>Generated Reports</h1>
<br />
<!--Error Msg Div Starts -->
	<div>
		<s:if test="hasFieldErrors() || hasActionErrors()">
			<div class="errorMsg">
				<span class="errorMsgInline">
					<s:actionerror /><s:fielderror />
				</span>
			</div>
		</s:if>
	</div>
	<!--Error Msg Div Ends -->
<s:form name="downloadReport" action="downloadReport" method="post" validate="true">
	<s:hidden name="reportType" id="reportType"/>
<table width="70%" cellpadding="3" cellspacing="1" border="1"
	bgcolor="#ffffff" align="center">
	<tr>
		<td class="divBg">
		<table width="99%" align="center" cellpadding="3" cellspacing="1" border="0"
		bgcolor="#ffffff" id="table1" >
			<tr>
				<td class="textRight">Message Type:&nbsp;</td>
				<td class="text"><s:label name="messageList" key="Message Type"></s:label>
				</td>
			</tr>
			<tr>
				<td class="textRight">Status:&nbsp;</td>
				<td><s:label name="outwardQueue" key="Outward Message"></s:label>
				</td>
			</tr>

			<tr>
				<td class="textRight">Receiver Name:&nbsp;</td>
				<td><s:label name="SenderReceiver" key="Receiver Name"></s:label>
				</td>
			</tr>
			<tr>
				<td class="textRight">Group By:&nbsp;</td>
				<td><s:label name="groupby" key="Group By"></s:label></td>
			</tr>

			<tr>
				<td class="textRight">From Date:&nbsp;</td>
				<td><s:label name="ValueFromDate" key="Value From Date" displayFormat="MM/dd/yyyy"></s:label>
				</td>
			</tr>

			<tr>
				<td class="textRight">To Date:&nbsp;</td>
				<td><s:label name="ValueToDate" key="Value To Date" displayFormat="MM/dd/yyyy"></s:label>
				</td>
			</tr>

			<tr>
				<td class="textRight">Amount From:&nbsp;</td>
				<td><s:label name="fromAmount" key="From Amount"></s:label></td>
			</tr>
			<tr>
				<td class="textRight">Amount To:&nbsp;</td>
				<td><s:label name="toAmount" key="To Amount"></s:label></td>
			</tr>
		</table>
		</td>
	</tr>
</table><br/><br/>
		<table width="100%" cellpadding="3" cellspacing="1" border="0"
		bgcolor="#ffffff" id="table1">
			<tr>
			<td><display:table uid="row" name="ReportList"
					pagesize="30" requestURI="/success" cellpadding="3" cellspacing="0" class="dataGrid">
				<display:setProperty name="basic.msg.empty_list" 
    				value='<div class="infoMsg"><span>Nothing found to display</span></div>' />
				<display etProperty name="css.tr.even" value="TDBGlightgray" />
				<display etProperty name="css.tr.odd" value="TDBGgray" cssClass="odd"/>
				
				<s:if test="%{session.msgRef=='Required'}">
					<display:column title="Message Senders Reference" property="msgRef" headerClass="gridHdrBg" style="text-align:center" />
				</s:if>
				<s:if test="%{session.msgType=='Required'}">
					<display:column title="Msg Type" property="msgType" headerClass="gridHdrBg" style="text-align:center" />
				</s:if>
				<s:if test="%{session.branch=='Required'}">
					<display:column title="Branch" property="branch" headerClass="gridHdrBg" style="text-align:center"/>
				</s:if>
				<s:if test="%{session.senderBank=='Required'}">
					<display:column title="Sender IFSC" property="senderBank" headerClass="gridHdrBg" style="text-align:center" />
				</s:if>
				<s:if test="%{session.receiverBank=='Required'}">
					<display:column title="Receiver IFSC" property="receiverBank" headerClass="gridHdrBg" style="text-align:center" />
				</s:if>
				<s:if test="%{session.status=='Required'}">
					<display:column title="Status" property="paymentStatus" headerClass="gridHdrBg"  style="text-align:center"/>
				</s:if>
				<s:if test="%{session.txnReference=='Required'}">
					<display:column title="Senders Reference" property="txnReference" headerClass="gridHdrBg" style="text-align:center" />
				</s:if>	
				<s:if test="%{session.dateofIssue=='Required'}">
					<display:column title="Date of Issue (MM/dd/yyyy)" property="issueDate" headerClass="gridHdrBg" format="{0,date,MM/dd/yyyy}" style="text-align:center" />
				</s:if>
				<s:if test="%{session.applicent=='Required'}">
					<display:column title="Applicant" property="orderingCustomer" headerClass="gridHdrBg" style="text-align:center" />
				</s:if>
				<s:if test="%{session.beneficiary=='Required'}">
					<display:column title="Beneficiary" property="beneficiaryCustomer" headerClass="gridHdrBg" style="text-align:center"/>
				</s:if>
				<s:if test="%{session.applicentBank=='Required'}">
					<display:column title="Applicent Bank" property="applicentBank" headerClass="gridHdrBg" style="text-align:center"/>
				</s:if>
				<s:if test="%{session.currency=='Required'}">
					<display:column title="Currency" property="msgCurrency" headerClass="gridHdrBg" style="text-align:center" />
				</s:if>
				<s:if test="%{session.amount=='Required'}">
					<display:column title="Amount" property="msgAmount" headerClass="gridHdrBg" style="text-align:center" />
				</s:if>
				<s:if test="%{session.dateofExpiry=='Required'}">
					<display:column title="Date of Expiry(MM/dd/yyyy)" property="expDate" headerClass="gridHdrBg" format="{0,date,MM/dd/yyyy}" style="text-align:center" />
				</s:if>			
				<s:if test="%{session.dateofShipment=='Required'}">
					<display:column title="Date of Shipment(MM/dd/yyyy)" property="lastDateofShipment" headerClass="gridHdrBg" format="{0,date,MM/dd/yyyy}" style="text-align:center" />
				</s:if>
				<s:if test="%{session.dateofAmendment=='Required'}">
					<display:column title="Date of Amendment(MM/dd/yyyy)" property="amendmentDate" headerClass="gridHdrBg" format="{0,date,MM/dd/yyyy}" style="text-align:center" />
				</s:if>	
				<s:if test="%{session.newDateofExpiry=='Required'}">
					<display:column title="New Date of Expiry(MM/dd/yyyy)" property="expDate" headerClass="gridHdrBg" format="{0,date,MM/dd/yyyy}" style="text-align:center" />
				</s:if>	
				<s:if test="%{session.newDateofShipment=='Required'}">
					<display:column title="New Date of Shipment(MM/dd/yyyy)" property="lastDateofShipment" headerClass="gridHdrBg" format="{0,date,MM/dd/yyyy}" style="text-align:center" />
				</s:if>
				<s:if test="%{session.presentBankRef=='Required'}">
					<display:column title="Presenting Banks Ref" property="relatedRefrence" headerClass="gridHdrBg" style="text-align:center" />
				</s:if>
				<s:if test="%{session.noofAmendment=='Required'}">
					<display:column title="No of Amendment" property="noofAmendments" headerClass="gridHdrBg" style="text-align:center" />
				</s:if>	
				<s:if test="%{session.dateofReduction=='Required'}">
					<display:column title="Date of Reduction(MM/dd/yyyy)" property="amendmentDate" headerClass="gridHdrBg"  format="{0,date,MM/dd/yyyy}" style="text-align:center" />
				</s:if>	
				<s:if test="%{session.amountReduced=='Required'}">
					<display:column title="Amount Reduced" property="reducedAmt" headerClass="gridHdrBg" style="text-align:center" />
				</s:if>		
				<s:if test="%{session.amountofOutstanding=='Required'}">
					<display:column title="Amount Outstanding" property="outstandAmt" headerClass="gridHdrBg" style="text-align:center" />
				</s:if>		
			</display:table></td>
			</tr>
		</table>
		
		<!--  button panel starts -->
	<div class="clearfix"></div>
	<table width="100%" cellpadding="0" cellspacing="0" border="0">
		<tr>
			<td> <font>Download Report :</font>
				<a href="#" onclick="setReportType('EXCEL')">
					<img src="images/xls.png" width="24" height="24" alt="EXCEL" border="0"/>
				</a>|
				<a href="#" onclick="setReportType('PDF')">
					<img src="images/pdf.png" width="24" height="24" alt="PDF" border="0"/>
				</a>|
				<a href="#" onclick="setReportType('CSV')">
					<img src="images/csv.png" width="24" height="24" alt="CSV" border="0"/>
				</a>
			</td>
		</tr>
	</table>
	<!--  button panel ends -->
	</s:form>	
</div>
</div>
</div>
<div id="contentPaneBottom"></div>