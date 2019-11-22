<%@page import="com.opensymphony.xwork2.ActionContext"%>
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<%@taglib uri="http://displaytag.sf.net" prefix="display"%>
<link href="SpryAssets/SpryCollapsiblePanel.css" rel="stylesheet"
	type="text/css" />
<script src="SpryAssets/SpryCollapsiblePanel.js" type="text/javascript"></script>


<s:head />
<div id="contentPane">
<div id="contentPaneTop"></div>
<div id="contentPaneFluid">
<div id="contentPaneInternal"><span class="crumbs"><a href="#">Outward Payments></a>&nbsp;Invalid Messages&nbsp;>&nbsp;Raw Message</span></div>
<div id="content">
<h1>Raw Message</h1>
<s:form action="repairRawMessage">
<!-- Screen comparison -->
<s:hidden name="msgRef" id="msgRef"></s:hidden>

<!-- Screen comparison End-->
	<!--Error Msg Div Starts -->
	<div><s:if test="hasFieldErrors()">
				<div class="errorMsg"><span class="errorMsgInline"><s:actionerror /><s:fielderror /></span></div>
			</s:if></div><!--Error Msg Div Ends -->
	<!-- Collapsible Panels Start-->
	<div id="CollapsiblePanel1" class="CollapsiblePanel">
	
	<div class="CollapsiblePanelContent">
	<div><!-- Group One Div Starts --> 
	<s:div id="main"	cssClass="dataGrid" style="width:100%;">
	<table width="100%" cellpadding="3" cellspacing="1" border="0"	bgcolor="#ffffff">
		<tr>
		<td width="15%" class="textRight"><s:label
					value="Error Description"></s:label></td>
		
			
			<td width="85%" class="text"><s:textarea name="errCode" id="errCode" rows="4" cols="70" readonly="true"></s:textarea> 
			</td>
			
	</tr>
		<tr>
		<td width="15%" class="textRight"><s:label
					value="Raw Message"></s:label></td>
		
			
			<td width="85%" class="text"><s:textarea name="rawMsg" id="rawMsg" rows="20" cols="130" ></s:textarea> 
			</td>
			
	</tr>
	<tr>
		<td width="15"></td><td align="center"><input type="button" value="Back" onclick="Back();"/></td>
	</tr>
		</table>
	</s:div> <!-- Group One Div Ends --></div>
	</div>
	</div>
	<br />
	</s:form></div>
</div>
</div>
<div id="contentPaneBottom"></div>
<script type="text/javascript">
	var CollapsiblePanel1 = new Spry.Widget.CollapsiblePanel(
			"CollapsiblePanel1");

	function Back()
	{
		
			document.forms[0].action="executeinvalidMessages";
			document.forms[0].submit();
		

		}
	
</script>