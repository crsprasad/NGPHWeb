<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="com.opensymphony.xwork2.ActionContext"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<div id="contentPane">
<div id="contentPaneTop"></div>
<div id="contentPaneFluid">
<div id="contentPaneInternal"><span class="crumbs"><a
	href="#">File Operations</a>&nbsp;>&nbsp;File Upload</span></div>
<div id="content">
<h1>File Upload</h1>
<!--Error Msg Div Starts -->
	
<s:form action="fileUpload" method="post" enctype="multipart/form-data">
	<div style="width:40%;"><!--Error Msg Div Starts -->
	 <s:if test="hasFieldErrors()">
		<div class="errorMsg"><span class="errorMsgInline"><s:actionerror /><s:fielderror /></span></div>
	</s:if> 
	 <s:if test="hasActionErrors()">
		<div class="hasActionErrors"><span class="errorMsgInline"><s:actionerror /><s:fielderror /></span></div>
	</s:if><!--Error Msg Div Ends -->
	</div>
	<div style="width:594px;" class="dataGrid">
	<table width="100%" cellpadding="5" cellspacing="1" border="0"
		bgcolor="#ffffff">
		<tr>
			<td class="textRight"><s:label value="Select File"></s:label>:&nbsp;</td>
			<td class="text"><s:file label="Upload File" name="ifscFileDataUpload"
				cssClass="btnLeft" size="100"></s:file></td>
		</tr>
		</table>
	</div>

	<!--  button panel starts -->
	<div class="clearfix"></div>
	<table width="100%" cellpadding="0" cellspacing="0" border="0">
		<tr>
			<td><s:submit theme="simple" type="submit" value="Upload"
				action="performUploadFile" cssClass="btn"></s:submit></td>
		</tr>
	</table>
	<!--  button panel ends -->
</s:form></div>
</div>
</div>
<div id="contentPaneBottom"></div>


