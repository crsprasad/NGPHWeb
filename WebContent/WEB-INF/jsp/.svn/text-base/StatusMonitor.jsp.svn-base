<%@page import="com.opensymphony.xwork2.ActionContext"%>
<%@page language="java" import="java.util.*,com.logica.ngph.web.action.*,java.lang.*,java.math.*" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<%@taglib uri="http://displaytag.sf.net" prefix="display"%>
<link href="SpryAssets/SpryCollapsiblePanel.css" rel="stylesheet"
	type="text/css" />
<script src="SpryAssets/SpryCollapsiblePanel.js" type="text/javascript"></script>
<script type="text/javascript">
function callFetchStatusDetails()
{
	var lcNumber= document.getElementById("userDate").value;
	document.forms[0].action="fetchStatusData";
	document.forms[0].submit();
}

</script>
<sx:head parseContent="true" />
<s:head />
<div id="contentPane">
<div id="contentPaneTop"></div>
<div id="contentPaneFluid">
<div id="contentPaneInternal"><span class="crumbs"><a
	href="#">Enquiry</a>&nbsp;>&nbsp;Status Monitor</span></div>
<div id="content">
<h1>Status Monitor</h1>
<s:form method="post">
<!--Error Msg Div Starts -->
	<div><s:if test="hasFieldErrors()">
				<div class="errorMsg"><span class="errorMsgInline"><s:actionerror /><s:fielderror /></span></div>
			</s:if></div><!--Error Msg Div Ends -->
	<!-- Collapsible Panels Start-->
	<div id="CollapsiblePanel2" class="CollapsiblePanel">
	<div class="CollapsiblePanelTab"><!--  <img src="images/openPanel.png" width="9" height="7" alt="openPanel" />-->&nbsp;Filters</div>
	<div class="CollapsiblePanelContent">
	<div><!-- Group Two Div Starts --> <s:div id="mandatory"
		cssClass="dataGrid" style="width:100%;">
		<table width="100%" cellpadding="5" cellspacing="1" border="0" bgcolor="#ffffff">
			
		<tr>
			<td width="20%" class="textRight"><s:label
					value="%{getText('label.Date')}"></s:label>:</td>
			<td width="30%" class="text"><sx:datetimepicker name="userDate" id="userDate" value="today"></sx:datetimepicker>  <input type="button" value="Fetch" onclick="callFetchStatusDetails();"> </td>
			
		</tr>
	</table>
	</s:div> <!-- Group Two Div Ends --></div>
	</div>
	</div>
	<br />	
	<div id="CollapsiblePanel1" class="CollapsiblePanel">
	<div class="CollapsiblePanelTab"><!--  <img src="images/openPanel.png" width="9" height="7" alt="openPanel" />-->Filter Results</div>
	<div class="CollapsiblePanelContent">
	<div><!-- Group One Div Starts --> 
	<s:div id="main"	cssClass="dataGrid" style="width:100%;">
	<table width="100%" cellpadding="3" cellspacing="1" border="1"	bgcolor="#ffffff">
	<tr>
				<th  align="center" width="50%"><s:label value="%{getText('label.Outbound')}"></s:label></th>
				<th  align="center" width="50%"><s:label value="%{getText('label.Inbound')}"></s:label></th>
	</tr>
	<tr valign="top">
	<td width="50%">
	<table width="100%" cellpadding="3" cellspacing="1" border="1"	bgcolor="#ffffff">
	<tr>
	<th align="center" width="20%" class="textBold"><s:label value="%{getText('label.status')}"></s:label></th>
	<th align="center" width="20%" class="textBold"><s:label value="%{getText('label.count')}"></s:label></th>
	</tr>	
	<%	Map<String, Long> outboundStatus = (Map<String, Long>) session.getAttribute("outboundStatus"); 
				if(outboundStatus.isEmpty()){%>			
			<tr><td colspan="2">
			<div class="infoMsg"><span>Nothing found to display</span></div>
			</td></tr></table>
			<%}  %>
		
			
	<% if(!outboundStatus.isEmpty()){ 
		for (String status : outboundStatus.keySet()) {
	
	%>	
	
	<tr>
	<td align="center" width="30%" class="textCenter"><%=status %></td>
	<td align="center" width="30%" class="textCenter"><%=outboundStatus.get(status) %></td>
	</tr>	
	<%} %>
	</table>
	<%} %>
	
	</td>
	<td width="50%">
	<table width="100%" cellpadding="3" cellspacing="1" border="1"	bgcolor="#ffffff">
	<tr>
	<th align="center" width="20%" class="textBold"><s:label value="%{getText('label.status')}"></s:label></th>
	<th align="center" width="20%" class="textBold"><s:label value="%{getText('label.count')}"></s:label></th>
	</tr>
	
	<%	Map<String, Long> inboundStatus = (Map<String, Long>) session.getAttribute("inboundStatus"); 
				if(inboundStatus.isEmpty()){ %>			
			<tr> <td colspan="2">
			<div class="infoMsg"><span>Nothing found to display</span></div>
			</td></tr></table>
			<%}  %>
			
	<% if(!inboundStatus.isEmpty()){ 
		for (String status : inboundStatus.keySet()) {
	
	%>	
	
	<tr>
	<td align="center" width="30%" class="textCenter"><%=status %></td>
	<td align="center" width="30%" class="textCenter"><%=inboundStatus.get(status) %></td>
	</tr>	
	<%} %>
	</table>
	<%} %>
	
	</td>
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