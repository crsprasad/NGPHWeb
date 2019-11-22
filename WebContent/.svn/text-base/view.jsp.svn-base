<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="com.logica.ngph.web.utils.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="sd" uri="/struts-dojo-tags" %>
<%@taglib uri="http://displaytag.sf.net" prefix="display" %>
<html>
<head>
<script type="text/javascript">
function prnTbl() 
{
		
	alert(document.getElementById('table1'));

	var printContent = document.getElementById("table1");

	var windowUrl = 'about:blank';

	var num; 

	var uniqueName = new Date();

	var windowName = 'Print' + uniqueName.getTime();
	var printWindow = window.open(num, windowName, 'left=50000,top=50000,width=0,height=0'); 

	printWindow.document.write(printContent);

	printWindow.document.close();

	printWindow.focus();

	printWindow.print();

	printWindow.close();

}
</script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>View Message</title>
</head>
<body>

<s:form action="displayMessages" method="post" >
<table id="table1">
<tr><td>
<s:textfield name="msgType" id="msgType" key="Msg Type"></s:textfield>
</td></tr>
<tr><td>
<s:textfield name="msgChannel" id="msgChannel" key="channel"></s:textfield>
</td></tr>
<tr><td>
<s:textfield name="msgDirection" id="msgDirection" key="msgDirection"></s:textfield>
</td></tr>
<tr><td>
<s:textfield name="beneficiaryCustomer" id="beneficiaryCustomer" key="Beneficiary Customer"></s:textfield>
</td></tr>
<tr><td>
<s:textfield name="txnReference" id="txnReference" key=" TRan Reference"></s:textfield>
</td></tr>
<tr><td>
<s:textfield name="senderBank" id="senderBank" key="sender Bank"></s:textfield>
</td></tr>
<tr><td>
<s:textfield name="receiverBank" id="receiverBank" key="Receiver Bank"></s:textfield>
</td></tr>
<tr><td>
<s:textfield name="msgAmount" id="msgAmount" key="msg Amount"></s:textfield>
</td></tr>
<tr><td>
<s:textfield name="msgValueDate" id="msgValueDate" key="ValueDate"></s:textfield>
</td></tr>
<tr><td>
<s:textfield name="orderingCustomer" id="orderingCustomer" key="ordering Customer"></s:textfield>
</td></tr>
<tr><td>
<s:textfield name="narration" id="narration" key="Narration type"></s:textfield>
</td></tr>

</table>
<s:submit value="Print" onclick="prnTbl()"></s:submit>
</s:form>
</body>
</html>