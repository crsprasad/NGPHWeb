<%@page import="com.logica.ngph.Entity.Branches"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="css/ngph.css"
	media="screen" />
<title>Search Page</title>
<s:head />

<script type="text/javascript">
	
	function cancel() {
		window.close();
	}

	function DataSearch() {

		var parentAction = window.opener.document
				.getElementById("StreamIDsearchAction").value;
		

	}
</script>
</head>

<body>

<s:actionerror />
<s:form method="post" action="StreamIDsearchAction" name="searchForm">
	
	
	<table width="100%" cellpadding="0" cellspacing="0" border="0"
		class="text">
		<tr>
			<td>
			
		<div class="dataGrid">
		<table width="100%" cellpadding="3" cellspacing="1" border="0"
				bgcolor="#ffffff" align="center">
			<tr>
				<td width="25%" class="textRight"><s:label
					value="Stream ID"></s:label>:&nbsp;</td>
				<td width="25%" class="text"><s:textfield name="StreamID"></s:textfield></td>
				
			</tr>
		</table>
		</div>
		<table width="100%" cellpadding="0" cellspacing="0" border="0"
			bgcolor="#ffffff" align="center">
			<tr>
				<td class="textCenter"><br />
				<s:submit value="Search" cssClass="btn" onclick="DataSearch()"></s:submit><br />
				</td>
			</tr>
			<tr>
				<td><br/>
				
				<table width="100%" cellpadding="0" cellspacing="0" border="0" bgcolor="#ffffff" align="center">
				<s:iterator value="searchList">
 					 <tr><td width="25%" class="text"><s:property /></td></tr>
						</s:iterator>
						</table>
				
				</td>
			</tr>
		</table>
		</td>
		</tr>
	</table>
</s:form>
</body>
</html>