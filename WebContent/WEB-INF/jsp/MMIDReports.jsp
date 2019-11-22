<%@page import="com.opensymphony.xwork2.ActionContext"%>
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<link href="SpryAssets/SpryCollapsiblePanel.css" rel="stylesheet"
	type="text/css" />
<script src="SpryAssets/SpryCollapsiblePanel.js" type="text/javascript"></script>

<script type="text/javascript">
function callSearchAccountNo(Action) {

	var accountNoField= document.getElementById("accountNo").value;

		window.open(
				"<s:url action='showAccountSearchRepotScreen' windowState='EXCLUSIVE' />",
				'mywindow', 'top=50,left=250,width=750,height=410');
	}
</script>

<sx:head parseContent="true" />
<s:head />
<div id="contentPane">
<div id="contentPaneTop"></div>
<div id="contentPaneFluid">
<div id="contentPaneInternal"><span class="crumbs"><a
	href="#">Enquiry</a>&nbsp;>&nbsp;MMID Reports</span></div>
<div id="content">
<h1>MMID Reports</h1>
<s:form method="post">
<!--Error Msg Div Starts -->
	<div><s:if test="hasFieldErrors()">
				<div class="errorMsg"><span class="errorMsgInline"><s:actionerror /><s:fielderror /></span></div>
			</s:if></div><!--Error Msg Div Ends -->
	<!-- Collapsible Panels Start-->
	<div id="CollapsiblePanel1" class="CollapsiblePanel">
	<div class="CollapsiblePanelTab">&nbsp;</div>
	<div class="CollapsiblePanelContent">
	<div><!-- Group One Div Starts --> 
	<s:div id="main"	cssClass="dataGrid" style="width:100%;">
	<table width="100%" cellpadding="3" cellspacing="1" border="0"	bgcolor="#ffffff">
	<tr>
				<td  align="center"><s:label value="%{getText('label.accountNumber')}"></s:label>:
				<s:textfield name="accountNo" id="accountNo"></s:textfield> <input type="button" value="Search" onclick="callSearchAccountNo();"></td>
				
			</tr>
		</table>
	</s:div> <!-- Group One Div Ends --></div>
	</div>
	</div>
	<br />
	<center>
	<table width="100%" cellpadding="0" cellspacing="0" border="0">
		<tr>
			<td>
			
				<s:submit value="Generate Reports" action="generteMMIDReports" cssClass="btn" />
					<input type="reset" value="Cancel" onclick="cancel()" class="btn"  />
			</td></tr></table> </center>
	</s:form></div>
</div>
</div>
<div id="contentPaneBottom"></div>