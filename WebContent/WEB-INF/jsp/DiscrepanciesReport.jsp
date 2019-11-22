
<%@page import="com.opensymphony.xwork2.ActionContext"%>
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<%@taglib uri="http://displaytag.sf.net" prefix="display"%>
<link href="SpryAssets/SpryCollapsiblePanel.css" rel="stylesheet"
	type="text/css" />
<script src="SpryAssets/SpryCollapsiblePanel.js" type="text/javascript"></script>
<script type="text/javascript">
function newcodeload(){
	document.getElementById("btnSearch").disabled = true;
	document.getElementById("generateButton").disabled = false;
		
}
function newcodeload1(){
	document.getElementById("btnSearch").disabled = false;
	document.getElementById("generateButton").disabled = true;
		
}
function callFetchDetails()
	{
	document.forms[0].action = "displayDiscripancy";
	document.forms[0].submit();
	
	}

function callGenerateNewDiscReport()
{
document.forms[0].action = "displayNewGenerateDiscripancy";
document.forms[0].submit();

}
function setReportType(type) {
	
	document.getElementById("reportType").value =type;
	document.form.action = "downloadDiscripenyReport";
	document.form.submit();
}
</script>
<sx:head parseContent="true" />
<s:head />

<div id="contentPane">
<div id="contentPaneTop"></div>
<div id="contentPaneFluid">
<div id="contentPaneInternal"><span class="crumbs"><a
	href="#">Enquiries & Reports</a>&nbsp;>&nbsp;Discrepancy Reports</span></div>
<div id="content">
<h1>Discrepancy Reports</h1>
<s:hidden id="repairComments" name="repairComments"></s:hidden> <s:form method="post" id="form">
	<s:hidden name="reportType"/>
	<!--Error Msg Div Starts -->
	<div><s:if test="hasFieldErrors()">
				<div class="errorMsg"><span class="errorMsgInline"><s:actionerror /><s:fielderror /></span></div>
				    <script language="JavaScript">  
				   		 window.onload=newcodeload ; 				    
        			 </script>  
			</s:if>	
			<s:else>
			 <script language="JavaScript">   
				 window.onload=newcodeload1 ;				 	
        	 </script> 
			</s:else>
			</div><!--Error Msg Div Ends -->
	<!-- Collapsible Panels Start-->
	<div id="CollapsiblePanel1" class="CollapsiblePanel">
	<div class="CollapsiblePanelTab"><!--  <img src="images/openPanel.png" width="9" height="7" alt="openPanel" />-->&nbsp;</div>
	<div class="CollapsiblePanelContent">
	<div><!-- Group One Div Starts --> 
	<s:div id="main"	cssClass="dataGrid" style="width:100%;">
	<table width="100%" cellpadding="3" cellspacing="1" border="0"	bgcolor="#ffffff">
	<tr>
				<td width="50%" class="textRight"><s:label
						value="Date(MM/dd/yy)"></s:label>:&nbsp;</td>
				<td width="50%" class="text"><sx:datetimepicker name="date"
						displayFormat="dd/MM/yy" cssClass="txtField" id ="date" />
					<input type="button" value="Fetch..."
						onclick="callFetchDetails()" id ="btnSearch" class="btn" />
						<input type="button" value="Generate Discrepancies Report"
						onclick="callGenerateNewDiscReport()" id ="generateButton" class="btn" />
						</td>
						
	</tr>
	
			</table>
	</s:div> <!-- Group One Div Ends --></div>
	</div>
	</div>
	<br />
	<div id="CollapsiblePanel2" class="CollapsiblePanel">
	<div class="CollapsiblePanelTab"><!--  <img src="images/openPanel.png" width="9" height="7" alt="openPanel" />-->&nbsp;Discrepancies</div>
	<div class="CollapsiblePanelContent">
	<div><!-- Group Two Div Starts --> <s:div id="mandatory"
		cssClass="dataGrid" style="width:100%;">
		<table width="100%" cellpadding="5" cellspacing="1" border="0"
			bgcolor="#ffffff">
		<tr>
			<td class="textRight" width="25%"><s:label	value="%{getText('label.No_OUT_PAYMENT_NPCI')}"></s:label>:</td>
			<td class="text" width="15%"><s:label value="%{ob_Npci_Count}" ></s:label> </td>
		
			<td class="textRight" width="25%"><s:label	value="%{getText('label.No_In_PAYMENT_NPCI')}"></s:label>:</td>
			<td class="text" width="15%"><s:label value="%{ib_Npci_Count}" ></s:label> </td>
			
		</tr>
		
		<tr>
			<td class="textRight" width="25%"><s:label	value="%{getText('label.No_OUT_PAYMENT_QNG')}"></s:label>:</td>
			<td class="text" width="15%"><s:label value="%{ob_Qng_Count}"></s:label> </td>
		
			<td class="textRight" width="25%"><s:label	value="%{getText('label.No_In_PAYMENT_QNG')}"></s:label>:</td>
			<td class="text" width="15%"><s:label value="%{ib_Qng_Count} "></s:label></td>
			
		</tr>
		<tr>
			<td class="textRight" width="25%"><s:label	value="No Of OutBound Payment Sent By NPCI, Not At QNG"></s:label>:</td>
			<td class="text" width="15%"><s:label value="%{ob_Diff_Npci}"></s:label></td>
			
			
			<td class="textRight" width="25%"><s:label	value="No Of InBound Payment Sent By NPCI, Not At QNG"></s:label>:</td>
			<td class="text" width="15%"><s:label value="%{ib_Diff_Npci} "></s:label>
				 </td>
		</tr>
		
		<tr>
			<td class="textRight" width="25%"><s:label	value="No Of OutBound Payment Sent By QNG, Not received At NPCI"></s:label>:</td>
			<td class="text" width="15%"><s:label value="%{ob_Diff_Qng}"></s:label></td>
			
			
			<td class="textRight" width="25%"><s:label	value="No Of InBound Payment Sent By QNG, Not received At NPCI"></s:label>:</td>
			<td class="text" width="15%"><s:label value="%{ii_Diff_Qng}"></s:label> </td>
		</tr>
		
		
			</table>
	</s:div> <!-- Group Two Div Ends --></div>
	</div>
	</div>
	<br />
	<div id="CollapsiblePanel3" class="CollapsiblePanel">
	<div class="CollapsiblePanelTab"><!--  <img src="images/openPanel.png" width="9" height="7" alt="openPanel" />-->&nbsp;RRN  Of Payments </div>
	<div class="CollapsiblePanelContent">
	<div><!-- Group Three Div Starts --> <s:div id="register"
		cssClass="dataGrid" style="width:100%;">
		<table width="100%" cellpadding="5" cellspacing="1" border="0"
			bgcolor="#ffffff">
			<tr>
			<td align="center"><b> Outward Payments</b></td>
			<td align="center"><b> Inward Payments</b></td>
			</tr>
			
			<tr>
			<td width="50%">
			<display:table uid="row" name="rrpOutward" pagesize="5" requestURI="displayDiscripancy" cellpadding="5" cellspacing="1" class="dataGrid"  >				<display:setProperty name="basic.msg.empty_list" 
    				value='<div class="infoMsg"><span>Nothing found to display</span></div>' />
				<display etProperty name="css.tr.even" value="TDBGlightgray" />
				<display etProperty name="css.tr.odd" value="TDBGgray" cssClass="odd"/>
				<display:column title="RRN" property="RRN" headerClass="gridHdrBg"  sortable="false" />
					<display:column title="Transaction Ref" property="TRN_REF"
					headerClass="gridHdrBg"  sortable="false" style="text-align:center" />
				
				<display:column title="Transaction Date" property="TRN_DATE"
					headerClass="gridHdrBg"  sortable="false" style="text-align:center;font-family: Arial;"/>
			</display:table>
			</td >
			<td width="50%">
			<display:table uid="row" name="rrpInward" pagesize="5" requestURI="displayDiscripancy" cellpadding="5" cellspacing="1" class="dataGrid" >
				<display:setProperty name="basic.msg.empty_list" 
    				value='<div class="infoMsg"><span>Nothing found to display</span></div>' />
				<display etProperty name="css.tr.even" value="TDBGlightgray" />
				<display etProperty name="css.tr.odd" value="TDBGgray" cssClass="odd"/>
				<display:column title="RRN" property="RRN" headerClass="gridHdrBg"  sortable="false" />
					<display:column title="Transaction Ref" property="TRN_REF"
					headerClass="gridHdrBg"  sortable="false" style="text-align:center" />
				
				<display:column title="Transaction Date" property="TRN_DATE"
					headerClass="gridHdrBg"  sortable="false" style="text-align:center"/>
			</display:table>
			
			</td>
			
			</tr></table>
			
	
	</s:div>
	</div></div></div><br/>
	<table width="100%" cellpadding="0" cellspacing="0" border="0">
		<tr>
			<td> <font>Download Report :</font>
				<a href="#" onclick="setReportType('EXCEL')">
					Excel
				</a> |
				<a href="#" onclick="setReportType('PDF')">
					PDF
				</a>|
				<a href="#" onclick="setReportType('CSV')">
					CSV
				</a>
			</td>
		</tr>
	</table>
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