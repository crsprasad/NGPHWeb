<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%
response.setHeader("Cache-Control","no-cache"); 
response.setHeader("Pragma","no-cache"); 
response.setDateHeader ("Expires", -1); 
%>    
<%@ page  language="java" errorPage="" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>Cheque</title>
</head>
<body>

<%@ page import="com.logica.*" %>
<% EncodeImpl e = new EncodeImpl();

String p = "./qr.png";

String hiddenValues = request.getParameter("acc");
hiddenValues = hiddenValues + ":" + request.getParameter("amount");
hiddenValues = hiddenValues + ":" + request.getParameter("name");
hiddenValues = hiddenValues + ":" + request.getParameter("mdate");
String chequeNo = request.getParameter("chequeNo");
hiddenValues = hiddenValues + ":" + chequeNo; 


e.run(hiddenValues , p); // 0022440022:336688:446677\n 

//e.run("0022440022:336688:446677\n", pathR); 
%>


<jsp:useBean id="test" class="com.logica.AmountBean" />
<%
String mAmount="";
long lAmount=0L;
%>

<% 
mAmount= request.getParameter("amount"); 
lAmount = Long.parseLong(mAmount.trim());
%> 

<jsp:setProperty name="test" 
                 property="num" 
                 value="<%=lAmount%>"  />


<table background="swed_bg_red.bmp" width="680" border="0">
<tr height="100" >
<td colspan="4" align="right">
<img src="http://localhost:8080/QRTest/img/qr.png" ></img>
</td>
</tr>

<tr >
<td>
<b>
<font face="Calibri" size="4">
PAY </font></b>
</td>
<td>
<b>
<font face="Calibri" size="5">

<%= request.getParameter("name") %> 

</font>
</b>
</td>

</tr>
<tr >
<td>
<font face="Calibri" size="4">
<b>AMOUNT</b>
</font>
</td>
<td rowspan="2">
<font face="Calibri" size="5" >
<b><i><jsp:getProperty name="test" property="nwords" />
 Kronos</i></b> 
</font>
</td>
<td colspan="2" align="right"  >
<table border=1><tr><td>

<font face="Calibri" size="5">
<b>
Kr.  <jsp:getProperty name="test" property="num" />

</b>
</font>
</td></tr></table>

</td>
</tr>
<tr >
<td>
&nbsp;
</td>
<td>
&nbsp;
</td>


</tr>
<tr >
<td>
<b>
<font face="Calibri" size="4">
Date 
</font>
</b>
</td>
<td>
<b>
<font face="Calibri" size="5">

<%= request.getParameter("mdate") %>

</font>
</b>
</td>

</tr>

<tr >
<td><font face="Calibri" size="3" color="FFFFFF"><b>Account Of <i>Mukesh R S</i></b></td>
<td><font face="Calibri" size="3" color="FFFFFF"><b>Account No. <br/><i>0022440022</i></b></td>
<td><font face="Calibri" size="3" color="FFFFFF"><b>Branch Code <i>336688</i></b></font></td>
<td><font face="Calibri" size="3" color="FFFFFF"><b>Cheque No. <i><%= chequeNo %></i></b></font></td>
</tr>

</table>


             


</body>
</html>