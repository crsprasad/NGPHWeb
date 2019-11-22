<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<%@taglib uri="http://displaytag.sf.net" prefix="display"%>
<link href="SpryAssets/SpryCollapsiblePanel.css" rel="stylesheet"
	type="text/css" />
<script src="SpryAssets/SpryCollapsiblePanel.js" type="text/javascript"></script>
<script type="text/javascript" src="js/jquery-1.11.0.js"></script>

<sx:head parseContent="true" cache="true" />
<s:head />
<div id="contentPane">
<div id="contentPaneTop"></div>
<div id="contentPaneFluid">
<div id="contentPaneInternal"><span class="crumbs"><a
	href="#">System</a>&nbsp;>&nbsp;Archival Process</span></div>
<div id="content">
<h1>Archival Process</h1>
<s:form method="post" id="form" name="template">
<!--Error Msg Div Starts -->
	<div><s:if test="hasFieldErrors()">
				<div class="errorMsg"><span class="errorMsgInline"><s:actionerror /><s:fielderror /></span></div>
			</s:if></div><!--Error Msg Div Ends -->
	<!-- Collapsible Panels Start-->
	<div id="CollapsiblePanel1" class="CollapsiblePanel">
	<div class="CollapsiblePanelTab"><!--  <img src="images/openPanel.png" width="9" height="7" alt="openPanel" />-->&nbsp;Archival Process</div>
	<div class="CollapsiblePanelContent">
	<div><!-- Group One Div Starts --> 
	<s:div id="main" cssClass="dataGrid" style="width:100%;">
<table width="100%" cellpadding="0" cellspacing="0" border="0">
		<tr>
			<td class="dataGrid">
			<table width="100%" cellpadding="5" cellspacing="1" border="0"
				bgcolor="#ffffff">
				<tr>
					<td width="25%" class="textRight"><s:label value="Branch"></s:label>:&nbsp;</td>
					<td class="text"><s:textfield name="branch" readonly="true" cssClass="text" value="%{#session.branchName}" size="25"></s:textfield></td>
				</tr>
				<!--<tr>
					<td width="25%" class="textRight"><s:label value="Business Date"></s:label>:&nbsp;</td>
					<td class="text"><sx:datetimepicker name="businessDate" value="%{'today'}"  cssClass="txtField" id="businessDate" displayFormat="MM/dd/yyyy" type="date"  /> </td>	
				</tr>
			--></table>
			</td>
		</tr>
	</table>		
		
	 
	<div class="clearfix"></div>
	<table width="100%" cellpadding="0" cellspacing="0" border="0">
		<tr>
			<!--<td align="center"><s:submit value="Submit" action="saveSodEodTasksodEodAction" cssClass="btn"></s:submit> 
				<s:submit value="Refresh" action="getUpdatedStatussodEodAction" cssClass="btn"></s:submit> 
				--><td align="center"><s:submit value="Start Archival" action="startArchivalAction" cssClass="btn"></s:submit> 
					<input type="button" value="Cancel" class="btn" /></td>
		</tr>
	</table>
		
		<table width="100%" cellpadding="0" cellspacing="0" border="0" bgcolor="#ffffff" align="center">
			<tr>
				<td><br/>
				<div style="overflow: auto; height: 245px; width: 100%;border:0px solid #ff0000;">
				<display:table uid="row" name="sessionScope.ArchivalLogList" pagesize="10" requestURI="/startArchivalAction" cellpadding="3" cellspacing="0" class="dataGrid">
				<display:setProperty name="basic.msg.empty_list" value='<div class="infoMsg"><span>Nothing found to display</span></div>' />
				<display etProperty name="css.tr.even" value="TDBGlightgray" />
				<display etProperty name="css.tr.odd" value="TDBGgray" cssClass="odd"/>	
				<display:column	 title="Table Name" property="tableName"  headerClass="gridHdrBg"  sortable="false" style="text-align:center"/>
				<display:column	 title="Operation" property="operation" headerClass="gridHdrBg"  sortable="false" style="text-align:center"/>
				<display:column	 title="Condition" property="condition"  headerClass="gridHdrBg"  sortable="false" style="text-align:center"/>
				<display:column  title="Noof Rows Effected" property="rowsAffected" headerClass="gridHdrBg" style="text-align:center" />
				<display:column	 title="Status" property="status"  headerClass="gridHdrBg"  sortable="false" style="text-align:center"/>
				<display:column	 title="Reason" property="reason"  headerClass="gridHdrBg"  sortable="false" style="text-align:center"/>
				</display:table>
				</div></td>
			</tr>
		</table>
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
<script type="text/javascript">

	var CollapsiblePanel1 = new Spry.Widget.CollapsiblePanel(
			"CollapsiblePanel1");	
</script>
