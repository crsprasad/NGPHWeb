<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<s:head theme="xhtml" />
<div id="contentPane">
	<div id="contentPaneTop"></div>
	<div id="contentPaneFluid">
		<div id="contentPaneInternal">
		<!-- <span class="crumbs">&nbsp;<a href="#">Enquiries & Reports</a>&nbsp;>&nbsp;Authorisation Schema</span> -->
<div id="content">
					<h1>Welcome User  <s:label value="%{session.UserName}"></s:label></h1>
					<br />
				</div>
		</div>
	</div>
	<div id="contentPaneBottom"></div>
</div>