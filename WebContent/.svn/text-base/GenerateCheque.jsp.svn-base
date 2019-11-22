<%@taglib uri="/struts-tags" prefix="s"%>
<%@page import="com.logica.ngph.web.action.ChequeServletAction"%>
<HTML>
	<HEAD>
		<META HTTP-EQUIV=\"Pragma\" CONTENT=\"no-cache\">
		<META HTTP-EQUIV=\"Expires\" CONTENT=\"-1\">
	</HEAD>
	<BODY>
	<div id="contentPane">
<div id="contentPaneTop"></div>
<div id="contentPaneFluid">
		<table background='Logica_bg.bmp' width='680' border='0'>
			<tr height='100' >
				<td colspan='4' align='right'>				
				<img src="<%out.print(ChequeServletAction.imgURL);%>" height="100"/>
				</td>
			</tr>
			<tr>
				<td>
					<b><font face='Calibri' size='4'>PAY</font></b>
				</td>
				<td>
					<b><font face='Calibri' size='5'><s:label name="name" id ="name"></s:label></font></b>
	    		</td>
	    	</tr>
	    	
	    	<tr>
	    		<td><font face='Calibri' size='4'><b>AMOUNT</b></font></td>
				<td rowspan='2'><font face='Calibri' size='5' ><b><i>
				<s:label name="words" id ="words"></s:label>Euros</i></b></font>
				</td>
				<td colspan='2' align='right'>
					<table border=1>
						<tr>
							<td>
							<img src='euro_symbol.png' width='8' height='12'/>
							<s:label name="amount" id ="amount"></s:label>
							<%//out.print(request.getAttribute("amount"));%>
							</b></font>
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr >
				<td>&nbsp;</td>
				<td>&nbsp;</td>
			</tr>
			<tr >
				<td>
					<b><font face='Calibri' size='4'>Date</font></b>
				</td>
				<td>
					<b><font face='Calibri' size='5'>
					<s:label name="mdate" id ="mdate"></s:label>
					<!--<%//out.print(request.getAttribute("mdate"));%>
				 	--></font></b>
				 </td>
			</tr>
	   		<tr >
	   			<td><font face='Calibri' size='3' color='FFFFFF'>Bank Account Of <br><i>Madhan Julakanti</i></font></td>
	   			<td><font face='Calibri' size='3' color='FFFFFF'>Account No. <br><i>0022440022</i></font></td>
	   			<td><font face='Calibri' size='3' color='FFFFFF'>Branch Code <br><i>336688</i></font></td>
	   			<td><font face='Calibri' size='3' color='FFFFFF'>Cheque No. <br><i><s:label name="chequeNo" id ="chequeNo"></s:label></i></font></td>
	   		</tr>	    	
		</table>
		</div></div>
	</BODY>
</HTML>