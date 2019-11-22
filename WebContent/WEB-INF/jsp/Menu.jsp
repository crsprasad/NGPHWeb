<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<div id="tabLeftImg"></div>

<!-- Tab div contains all the Tab Menu links in ordered list format -->
<div id="tab">
<div class="button-panel"><!-- Mega Menu Starts -->
<ul id="MenuBar1" class="MenuBarHorizontal">
	<li style="width: 9.4em"><a class="MenuBarItemSubmenu" href="#" accesskey="M">Out-bound Payments</a>
	<ul class="sub-menu">
		<li><a href="<s:url action="loadPaymentStatus"/>" >Payment Entry</a></li>
		
		<s:url action="getPaymentMessageQueue" var="urlTag">
			<s:param name="paymentStatus">RECEIVED;O</s:param>
		</s:url>
		<li><a href="<s:property value="#urlTag" />">Received</a></li>
		
		<s:url action="getPaymentMessageQueue" var="urlTag">
			<s:param name="paymentStatus">AWAITING_REPAIR;O</s:param>
		</s:url>
		<li><a href="<s:property value="#urlTag" />">Awaiting Repair</a></li>
		
		<s:url action="getPaymentMessageQueue" var="urlTag">
			<s:param name="paymentStatus">WAREHOUSED;O</s:param>
		</s:url>
		<li><a href="<s:property value="#urlTag" />">Warehoused</a></li>
		
		<s:url action="getPaymentMessageQueue" var="urlTag">
			<s:param name="paymentStatus">AWAITING_ACCOUNTING;O</s:param>
		</s:url>
		<li><a href="<s:property value="#urlTag" />">Awaiting Accounting</a></li>

		<s:url action="getPaymentMessageQueue" var="urlTag">
			<s:param name="paymentStatus">ACCOUNTING_COMPLETED;O</s:param>
		</s:url>
		<li><a href="<s:property value="#urlTag" />">Accounting Completed</a></li>
		
		<s:url action="getPaymentMessageQueue" var="urlTag">
			<s:param name="paymentStatus">AWAITING_LIQUIDITY;O</s:param>
		</s:url>
		<li><a href="<s:property value="#urlTag" />">Awaiting Liquidity</a></li>
		
		<s:url action="getPaymentMessageQueue" var="urlTag">
			<s:param name="paymentStatus">LIQUIDITY_COMPLETED;O</s:param>
		</s:url>
		<li><a href="<s:property value="#urlTag" />">Liquidity Completed</a></li>
		
		<s:url action="getPaymentMessageQueue" var="urlTag">
			<s:param name="paymentStatus">AWAITING_AML;O</s:param>
		</s:url>
		<li><a href="<s:property value="#urlTag" />">Awaiting AML</a></li>
		
		<s:url action="getPaymentMessageQueue" var="urlTag">
			<s:param name="paymentStatus">AML_COMPLETED;O</s:param>
		</s:url>
		<li><a href="<s:property value="#urlTag" />">AML Completed</a></li>
		
		<s:url action="getPaymentMessageQueue" var="urlTag">
			<s:param name="paymentStatus">AWAITING_BILLING;O</s:param>
		</s:url>
		<li><a href="<s:property value="#urlTag" />">Awaiting Billing</a></li>
		
		<s:url action="getPaymentMessageQueue" var="urlTag">
			<s:param name="paymentStatus">BILLING_COMPLETED;O</s:param>
		</s:url>
		<li><a href="<s:property value="#urlTag" />">Billing Completed</a></li>

		<s:url action="getPaymentMessageQueue" var="urlTag">
			<s:param name="paymentStatus">AWAITING_ACKNOWLEDGEMENT;O</s:param>
		</s:url>
		<li><a href="<s:property value="#urlTag" />">Awaiting Acknowledgement</a></li>
		
		<s:url action="getPaymentMessageQueue" var="urlTag">
			<s:param name="paymentStatus">COMPLETED;O</s:param>
		</s:url>
		<li><a href="<s:property value="#urlTag" />">Completed</a></li>
		
		<s:url action="getPaymentMessageQueue" var="urlTag">
			<s:param name="paymentStatus">FINALISED;O</s:param>
		</s:url>
		<li><a href="<s:property value="#urlTag" />">Finalised</a></li>
		
		<s:url action="getPaymentMessageQueue" var="urlTag">
			<s:param name="paymentStatus">AWAITING_RELEASE;O</s:param>
		</s:url>
		<li><a href="<s:property value="#urlTag" />">Awaiting Release</a></li>
		
		<s:url action="getPaymentMessageQueue" var="urlTag">
			<s:param name="paymentStatus">EXCEPTIONS;O</s:param>
		</s:url>
		<li><a href="<s:property value="#urlTag" />">Exceptions</a></li>
		
		<s:url action="getPaymentMessageQueue" var="urlTag">
			<s:param name="paymentStatus">AWAITING_AUTHORISATION;O</s:param>
		</s:url>
		<li><a href="<s:property value="#urlTag" />">Awaiting Authorisation</a></li>
		
		<s:url action="getPaymentMessageQueue" var="urlTag">
			<s:param name="paymentStatus">REJECTED_BY_CHANNEL;O</s:param>
		</s:url>
		<li><a href="<s:property value="#urlTag" />">Rejected by Channel</a></li>
		
		<s:url action="getPaymentMessageQueue" var="urlTag">
			<s:param name="paymentStatus">INVALIDS;O</s:param>
		</s:url>
		<li><a href="<s:property value="#urlTag" />">Invalids</a></li>
	</ul>
	</li>
	<li style="width: 9.4em"><a class="MenuBarItemSubmenu" href="#" accesskey="G">In-bound Payments</a>
	<ul class="sub-menu">
		<s:url action="getPaymentMessageQueue" var="urlTag">
			<s:param name="paymentStatus">RECEIVED_I;I</s:param>
		</s:url>
		<li><a href="<s:property value="#urlTag" />">Received</a></li>

		<s:url action="getPaymentMessageQueue" var="urlTag">
			<s:param name="paymentStatus">INTERVENED;I</s:param>
		</s:url>
		<li><a href="<s:property value="#urlTag" />">Intervened</a></li>

		<s:url action="getPaymentMessageQueue" var="urlTag">
			<s:param name="paymentStatus">AWAITING_REPAIR_I;I</s:param>
		</s:url>
		<li><a href="<s:property value="#urlTag" />">Awaiting Repair</a></li>
		
		<s:url action="getPaymentMessageQueue" var="urlTag">
			<s:param name="paymentStatus">AWAITING_ACCOUNTING_I;I</s:param>
		</s:url>
		<li><a href="<s:property value="#urlTag" />">Awaiting Accounting</a></li>

		<s:url action="getPaymentMessageQueue" var="urlTag">
			<s:param name="paymentStatus">ACCOUNTING_COMPLETED_I;I</s:param>
		</s:url>
		<li><a href="<s:property value="#urlTag" />">Accounting Completed</a></li>
		
		<s:url action="getPaymentMessageQueue" var="urlTag">
			<s:param name="paymentStatus">ACCOUNTING_MISMATCH_I;I</s:param>
		</s:url>
		<li><a href="<s:property value="#urlTag" />">Account Mismatch</a></li>
		
		<s:url action="getPaymentMessageQueue" var="urlTag">
			<s:param name="paymentStatus">ACCOUNTING_MISMATCH_I;I</s:param>
		</s:url>
		<li><a href="<s:property value="#urlTag" />">Account Mismatch</a></li>

		<s:url action="getPaymentMessageQueue" var="urlTag">
			<s:param name="paymentStatus">AWAITING_LIQUIDITY_I;I</s:param>
		</s:url>
		<li><a href="<s:property value="#urlTag" />">Awaiting Liquidity</a></li>
		
		<s:url action="getPaymentMessageQueue" var="urlTag">
			<s:param name="paymentStatus">LIQUIDITY_COMPLETED_I;I</s:param>
		</s:url>
		<li><a href="<s:property value="#urlTag" />">Liquidity Completed</a></li>
		
		<s:url action="getPaymentMessageQueue" var="urlTag">
			<s:param name="paymentStatus">AWAITING_AML_I;I</s:param>
		</s:url>
		<li><a href="<s:property value="#urlTag" />">Awaiting AML</a></li>
		
		<s:url action="getPaymentMessageQueue" var="urlTag">
			<s:param name="paymentStatus">AML_COMPLETED_I;I</s:param>
		</s:url>
		<li><a href="<s:property value="#urlTag" />">AML Completed</a></li>
		
		<s:url action="getPaymentMessageQueue" var="urlTag">
			<s:param name="paymentStatus">AWAITING_BILLING_I;I</s:param>
		</s:url>
		<li><a href="<s:property value="#urlTag" />">Awaiting Billing</a></li>
		
		<s:url action="getPaymentMessageQueue" var="urlTag">
			<s:param name="paymentStatus">BILLING_COMPLETED_I;I</s:param>
		</s:url>
		<li><a href="<s:property value="#urlTag" />">Billing Completed</a></li>
		
		<s:url action="getPaymentMessageQueue" var="urlTag">
			<s:param name="paymentStatus">SEND_TO_HOST_I;I</s:param>
		</s:url>
		<li><a href="<s:property value="#urlTag" />">Sent to Host</a></li>

		<s:url action="getPaymentMessageQueue" var="urlTag">
			<s:param name="paymentStatus">PROCESSED_MANUALLY_I;I</s:param>
		</s:url>
		<li><a href="<s:property value="#urlTag" />">Processed Manually</a></li>
	</ul>
	</li>

	<li style="width: 9.4em"><a class="MenuBarItemSubmenu" href="#"
		accesskey="G">Enquiries & Reports</a>
	<ul class="sub-menu">
		<li><a href="<s:url action="callLoadToDropDownenquiryAction"/>" >Payment Enquiry</a></li>
		<li><s:a href="#">Find Payments in History</s:a></li>
		<li><s:a href="#">Customer Enquiry</s:a></li>
		<li><s:a href="#">Organisation Code Enquiry</s:a></li>
		<li><a href="<s:url action="loadDataOutwardPaymentSubmittedReportLoadData"/>" >Payment Sent Report</a></li>
		<li><a href="<s:url action="loadDataInwardPaymentSubmittedReportLoadData"/>" >Payment Received Report</a></li>
		
		
	</ul>
	</li>
	<li style="width: 9.4em"><a class="MenuBarItemSubmenu" href="#"
		accesskey="G">Maintenance</a>
	<ul class="sub-menu">
		<li><a href="<s:url action="loadruleAction"/>" >Rules</a></li>
		<li><a href="<s:url action="loadDataGroupgroupMaintenanceAction"/>" >Authorisation Group</a></li>
		<li><a href="<s:url action="loadDataAuthorizationauthorizationMaintenanceAction"/>" >Authorisation Schema</a></li>	
		<li><a href="<s:url action="populateDataesbXMLCreator"/>" >Maintain EI</a></li>
		
		

	</ul>
	</li>
	<li style="width: 9.4em"><a class="MenuBarItemSubmenu" href="#" accesskey="G">File Operations</a>
	<ul class="sub-menu">
		<li><a href="<s:url action="loadUploadFile"/>" >File Upload</a></li>
		<li><s:a href="#">File Export</s:a></li>
	</ul>
	</li>
	<li style="width: 9.4em"><a class="MenuBarItemSubmenu" href="#" accesskey="G">System</a>
	<ul class="sub-menu">
		<li><a href="<s:url action="getStatsValuesquickStatsAction"/>" >Dashboard</a></li>
		<li><a href="<s:url action="performSodEodsodEodAction"/>" >EOD/SOD</a></li>
		<li><s:a href="#">Event Audit</s:a></li>
	</ul>
	</li>
</ul>
<!-- Mega Menu Ends --></div>
</div>
<div id="tabRightImg"></div>









