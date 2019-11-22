<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="css/ngph.css"
	media="screen" />
<title>Payment Repair</title>
<script type="text/javascript">
	function popupCancel() {
		window.close();

	}
	function callRepairSave() {
		var repaircomment = document.getElementById("popUpRepairComments").value;

		window.opener.document.getElementById("repairComments").value = repaircomment;
		window.opener.document.forms[0].action = "saveRepairedPayment";
		window.opener.document.forms[0].submit();

		window.close();

	}
</script>
</head>

<body>
<s:form action="paymentAction" method="post">
	<table width="55%" cellpadding="1" cellspacing="0" border="0">
		<tr>
			<td class="divBg">
			<table width="100%" cellpadding="5" cellspacing="0" border="0">
				<tr>
					<td class="text"><s:label
						value="%{getText('label.DeleteComments')}"></s:label>:&nbsp;
					</td>
				</tr>
				<tr>
					<td class="text"><s:textarea class="text" name="popUpRepairComments"
						id="popUpRepairComments" cols="46" rows="8"></s:textarea>
					</td>
				</tr>
				<tr>
					<td class="text"><input class="btn" type="button" value="OK"
						onclick="callRepairSave()" /> <input class="btn" type="button" value="Cancel"
						onclick="popupCancel()" /></td>
				</tr>
			</table>
			</td>
		</tr>
	</table>
</s:form>

</body>
</html>