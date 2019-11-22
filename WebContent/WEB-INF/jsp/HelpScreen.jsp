<%@page import="com.logica.ngph.Entity.Branches"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@page import="com.logica.ngph.web.action.HelpAction"%>


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="css/ngph.css"
	media="screen" />
<title>About Q-NG</title>
<s:head />

<script type="text/javascript">
	function cancel() {
		window.close();
	}
</script>
<body>

<s:form method="post" action="getHelpData" name="helpForm">

	
	<h1 align="center">Q-NG - Quaestor Next Generation</h1>
	<table align="left" width="100%" cellpadding="0" cellspacing="0"
		border="0" class="text">
		<!-- row1 -->
		<tr>
			<td align="left">
				<s:iterator value="#session.helpList">					
					&nbsp; &nbsp; &nbsp; Minor Version : <s:property value="minorVersion" />
				</s:iterator>
			</td>
		</tr>
		<!-- row2 -->
		<tr>
			<td align="left">
				<s:iterator value="#session.helpList">					
					&nbsp; &nbsp; &nbsp; Major Version : <s:property value="majorVersion" />							
				</s:iterator>
			</td>
		</tr>
		<!-- row3 -->
		<tr>
			<td align="left">
				<s:iterator value="#session.helpList">					
					&nbsp; &nbsp; &nbsp; Product Name : <s:property value="productName" />					
				</s:iterator>
			</td>
		</tr>
		<!-- row4 -->
		<tr>
			<td align="left">
				<s:iterator value="#session.helpList">					
					&nbsp; &nbsp; &nbsp; Product Code : <s:property value="productCode" />					
				</s:iterator>
			</td>
		</tr>
		<!-- row5 - static value -->
		<tr>
			<td align="left">
				</br>&nbsp; &nbsp; &nbsp; &#169; CGI Group Inc 2012. All rights reserved
			</td>
		</tr>
		</table>
		</br>
		<table align="left" width="100%" cellpadding="0" cellspacing="0"
		border="0" class="text">
		<tr>
			<td align="center"><input type="button" value="OK"
				class="btn" onclick="cancel()"/></td>
		</tr>
		</table>		
			
	
</s:form>
</body>
</html>