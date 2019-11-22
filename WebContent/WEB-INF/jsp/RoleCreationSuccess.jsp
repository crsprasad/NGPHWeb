
<%@page import="com.opensymphony.xwork2.ActionContext"%>
<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@taglib prefix="sx" uri="/struts-dojo-tags"%>
<link href="SpryAssets/SpryCollapsiblePanel.css" rel="stylesheet" type="text/css" />
<script src="SpryAssets/SpryCollapsiblePanel.js" type="text/javascript"></script>
<sx:head parseContent="true" />
<s:head />
<div id="contentPane">
<div id="contentPaneTop"></div>
<div id="contentPaneFluid">
<div id="contentPaneInternal">
	<span class="crumbs">
		<a href="#">Maintenance</a>&nbsp;>&nbsp;Role Creation
	</span>
</div>
<div id="content">
	<h1>Role Created Successfully.</h1>
	<s:form method="post">
		<!-- Collapsible Panels Start-->
		<div id="CollapsiblePanel1" class="CollapsiblePanel">
			<div class="CollapsiblePanelTab">&nbsp;Role Details</div>
			<div class="CollapsiblePanelContent">
				<div>
					<!-- Group One Div Starts --> 
					<s:div id="main" cssClass="dataGrid" style="width:100%;"> 
						<table width="100%" cellpadding="3" cellspacing="1" border="0" bgcolor="#ffffff">
							<tr>
								<td class="textRight" width="25%">
									<s:label value="Role ID"></s:label>:</span>
								</td>
								<td class="text" width="15%">
									<s:property value='roleId'/>
								</td>
								<td class="text" width="10%">&nbsp;</td>
							</tr>
							<tr>
								<td class="textRight">
									<s:label value="Role Name"></s:label>:</span>
								</td>
								<td class="text">
									<s:property value='roleName'/>
								</td>
								<td class="text">&nbsp;</td>
							</tr>
														
						</table>
					</s:div>
				</div>
				
			</div>
		</div>
	</s:form>
</div>
</div>
</div>
<div id="contentPaneBottom"></div>
