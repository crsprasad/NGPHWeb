<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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

<style type="text/css"> 
.muk
{
background: url('main_nav_black_bg.gif'); background-repeat:repeat-x; 
}
</style> 
</head>



<body bgcolor="FFFFFF">
<form action="ChequeServlet" method="post">
<table width="100%" border="0">
	<tr>
		<td><img src="logica_logo.png"></td>
		<td colspan="2" align="center">
			<font face="impact" color=ffCC00 size=5> Logica eCheque Generator </font>
		</td>
		<td align="right">
			<img src="logica_logo.png">
		</td>
	</tr>
	<tr>
		<td colspan="4">
			<div id="abc" class="muk" runat="server">.</div>
		</td>
	</tr>

	<tr>
		<td align="left"><font face="impact" color=000000 size=3> Account Details: </font></td>
		<td align="right"><font face="impact" color=ffCC00 size=5> Payee Name </font></td>
		<td><input type="text" name="name" value=""  size="39" /></td>
		<!--<td align="center" rowspan="6" > <img src="rbs_right.gif"></img></td>
	--></tr>
	<tr>
		<td align="left"><font face="verdana" color=000000 size=2> Madhan Julakanti </font></td>
		<td align="right"><font face="impact" color=ffCC00 size=5> Amount </font></td>
		<td><input type="text" name="amount" value=""  size="39" /></td>
	</tr>
	<tr>
		<td align="left"><font face="verdana" color=000000 size=2> Account No: 0022440022 </font></td>
		<td align="right"><font face="impact" color=ffCC00 size=5> Date </font></td>
		<td><input type="text" name="mdate" value="" id="inputField" size="39" /></td>
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
			<input type="submit"  value="Generate Cheque" style="background-color:black; color:white;font-size: 20pt;height: 50px; width: 260px" />
		</td>
	</tr>
</table>

<table width="100%" bgcolor="000000">
	<!--<tr>
		<td align="center">s</td>
		<td align="center"><img src="rbs_bottom.bmp"/></td>
		<td align="center">s</td>
	</tr>
--></table>
</form>
</body>
</html>