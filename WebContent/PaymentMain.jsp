<%@ page  language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="/struts-tags" prefix="s"%>



<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Search Page</title>
<s:head/>
</head>

<body>
<s:form action="paymentMainAction"  method="post">
<table>

<tr>

<td>
<s:select list="msgTypeList" name="msgtype" id="msgtype" headerKey="-1"
				headerValue="Select Message Type" key="label.ruleMsgtype" ></s:select>
</td>

</tr>

<tr>

<td>
<s:select list="msgSubTypeList" name="msgSubtype" id="msgSubtype" headerKey="-1"
				headerValue="Select Message Sub Type" key="label.paymnetMsgSubType" ></s:select>
</td>

</tr>
<tr>

<td>
<s:select list="msgSourceList" name="msgSource" id="msgSource" headerKey="-1"
				headerValue="Select Message Source" key="label.paymnetMsgSource" ></s:select>
</td>

</tr>
</table>
<s:optgroup></s:optgroup>
<s:submit value="Create" action="paymentAction"></s:submit>
</s:form>
</body>
</html>