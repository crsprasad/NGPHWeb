<%@page import="com.logica.ngph.web.action.BannerAction"%>
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="/struts-tags" prefix="s"%>

<%@taglib uri="http://displaytag.sf.net" prefix="display" %>
 	
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>FeedBack</title>
</head>
<body>

<s:form name="feedback" action="FeedBack" method="POST">

<table cellpadding="2" cellspacing="2">
	<tr>
		<td>
			<s:textarea name="feedbacks" id="feedbacks" rows="5" ></s:textarea>
		</td>
	</tr>
	<tr>
		<td>
			<s:radio list="{'excellent','good','average','bad','worst'}" value="true" name="rating"></s:radio>
			<s:submit name ="submit" value="Submit" action="FeedBackReturn"></s:submit>
		</td>
	</tr>
</table>
</s:form>

</body>
</html>