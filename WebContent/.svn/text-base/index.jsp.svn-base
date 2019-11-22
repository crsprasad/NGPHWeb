<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cheque Details Entry</title>
<!--  for date entry -->
<link rel="stylesheet" type="text/css" media="all" href="jsDatePick_ltr.min.css" />
<script type="text/javascript" src="jsDatePick.min.1.3.js"></script>
<script type="text/javascript">
	window.onload = function(){
		new JsDatePick({
			useMode:2,
			target:"inputField",
			dateFormat:"%d-%M-%Y"
			/*selectedDate:{				This is an example of what the full configuration offers.
				day:5,						For full documentation about these settings please see the full version of the code.
				month:9,
				year:2006
			},
			yearsRange:[1978,2020],
			limitToToday:false,
			cellColorScheme:"beige",
			dateFormat:"%m-%d-%Y",
			imgPath:"img/",
			weekStartDay:1*/
		});
	};
</script>

</head>

<div id="contentPane">
<div id="contentPaneTop"></div>
<div id="contentPaneFluid">
<div id="contentPaneInternal"><div><s:label name="message"></s:label><s:if test="hasFieldErrors()">
				<div class="errorMsg"><span class="errorMsgInline"><s:actionerror /><s:fielderror /></span></div>
			</s:if></div></div>
<div id="content">

<body bgcolor="FFFFFF">
<form action="ChequeServlet" method="post">
<table width="100%" border="0">
<tr>
<td>
&nbsp;
</td>
<td colspan="2" align="middle">
<font face="impact" color="696969" size=5> Logica eCheque Generator </font></td>
<td align="right">
&nbsp;
</td>


</tr>
<tr>
<td colspan="4">
<div id="abc" class="muk" runat="server">.</div>
</td></tr>
<!--  <form action="http://localhost:8080/QRTest/GenerateCheque.jsp">
 -->

<tr>
<td align="left"><font face="impact" color=000000 size=3> Account Details: </font></td>
<td align="right"><font face="impact" color=ffCC00 size=5> Payee Name </font></td>
<td><input type="text" name="name" value=""  size="39" /></td>
<td align="center" rowspan="6" > <img src="swed_right.bmp"></img></td>

</tr>
<tr>
<td align="left"><font face="verdana" color=000000 size=2> Mukesh R S </font></td>

<td align="right"><font face="impact" color=ffCC00 size=5> Amount </font></td>
<td><input type="text" name="amount" value=""  size="39" /></td>

</tr>
<tr>
<td align="left"><font face="verdana" color=000000 size=2> Account No: 0022440022 </font></td>

<td align="right"><font face="impact" color=ffCC00 size=5> Date </font></td>
<td><input type="text" name="mdate" value=""  id="inputField" size="39" /></td>
</tr>

<tr>
<td align="left" valign="bottom"><font face="verdana" color=000000 size=2> Branch Code: 336688 </font></td>

</tr>

<tr>
<td> </td>

</tr>

<tr>
<td> </td>
<td> </td>
<td>
	
	<s:submit  action ="generateCheck" value="Generate Cheque" style="background-color:black; color:white;font-size: 20pt;height: 50px; width: 260px"></s:submit>
<% long cNo  = (long)Math.floor((Math.random() * 4100) + 1000);
String chequeNo = Long.toString(cNo);
%>

<input type="hidden" name="acc" value="0022440022:336688:446677">
<input type="hidden" name="chequeNo" value="<%=chequeNo %>">

</td>
</tr>
</table>






<table width="100%" bgcolor="000000"><tr>
<td align="center">s</td>
<td align="center">

</td>
<td align="center">s</td>
</tr></table>
</form>
</div>
</div>
</div>
<div id="contentPaneBottom"></div>
</body>
</html>