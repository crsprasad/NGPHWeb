<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="/struts-tags" prefix="s"%>

<html>

<script type="text/javascript">
function load(){
	document.getElementById("loginAttempt").value = "SSOLogin";
	document.forms[0].action="userLoginSSO";	
	document.forms[0].submit();
}
</script>
<body onload="load()">
<s:form>
<s:hidden id="loginAttempt" name="loginAttempt"></s:hidden>
<br><br><br><br><br><br><br><br>
<center><p><b>Please wait!!! You are being automatically redirected.</b></p></center>
<center><p><b>If you are not redirected, please <a href="#" onclick="load();">click here</a></b></p></center>
</s:form>

</body>
</html>