<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="s" uri="/struts-tags"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="css/ngph.css"
	media="screen" />
<title>Payment Delete</title>
<script type="text/javascript">
function popupCancel(){
	window.close();

	
}
function callRepairComment(){
	var repaircomment = document.getElementById("repairedComments").value;


	if((repaircomment == null) ||(repaircomment.length == 0) ){
		alert("Please enter Comments");
		}
		
	window.opener.document.getElementById("comment").value = repaircomment;
	window.close();
	window.opener.callSubmitForScreen();
}

</script>
<s:head/>
</head>

<body>
<s:form method="post" >
	<table width="55%" cellpadding="1" cellspacing="0" border="0">
		<tr>
			<td class="divBg">
			<table width="100%" cellpadding="5" cellspacing="0" border="0">
				<tr>
					<td class="text"><s:label
						value="Comments"></s:label>:&nbsp;
					</td>
				</tr>
				<tr>
				<td class="text">
				<s:textarea name="repairedComments" id="repairedComments" class="text" key="" cols="46" rows="8" required="true"></s:textarea>
				</td>
				</tr>
				<tr>
				<td class="text">
				<input type="button" class="btn" value="OK" onclick="callRepairComment()" />
				<input type="button" class="btn" value="Cancel" onclick="popupCancel()" />
				</td>
				</tr>
				</table></td>
				</tr>
				</table>
</s:form>

</body>
</html>