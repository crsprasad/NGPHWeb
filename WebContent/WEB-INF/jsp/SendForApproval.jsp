<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<div id="contentPane">
<div id="contentPaneTop"></div>
<div id="contentPaneFluid">
<div id="contentPaneInternal">
<s:form method="post">

	
	
	<!--Error Msg Div Starts -->
	<div class="normalMsg"><span>Sent For Approval with Reference No: </span><s:property value="#session['screenRef']"/></div>
	<!--Error Msg Div Ends -->
</s:form></div>
</div>
<div id="contentPaneBottom"></div>
</div>