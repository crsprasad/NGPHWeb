<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript">

</script>
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="css/ngph.css"
	media="screen" />
	<body>
	
<s:form namespace="/" action="userLogin" method="post">
<s:hidden name="signOnFlag" id="signOnFlag"></s:hidden>

	<div class="loginBg">
	<div id="loginLogo"><img src="images/loginLogo.png" width="143px" height="82px" alt=""></div>
	<!-- loginBox starts -->
	<div id="loginBox">
				<table width="100%">
					<tr>
						<td colspan="2"><img src="images/loginUser.jpg" width="25"
							height="25px" alt="QNG logo"> <span class="login-header">User
						Login</span></td>
					</tr>
					<tr>
						<td>
						<!--Error Msg Div Starts --> <s:if test="hasFieldErrors() || hasActionErrors()">
				<div class="errorMsg"><span class="errorMsgInline"><s:actionerror /><s:fielderror /></span></div>
			</s:if> <!--Error Msg Div Ends -->
						
						</td>
					</tr>
					<tr>
						<s:hidden name="loginAttempt" value="%{'1'}" />
						<td><s:label value="Username:"></s:label></td>
					</tr>
					<tr>
						<td><s:textfield name="user" cssClass="txtField"></s:textfield></td>
					</tr>
					<tr>
						<td><label for=""><s:label value="Password:"></s:label></label></td>
					</tr>
					<tr>
						<td><s:password name="userPassword" id="userPassword" cssClass="txtField" ></s:password></td>
					</tr>
					<tr>
						<td><s:submit value="LOGIN" action="userLogin" cssClass="btn" ></s:submit>
						<td><s:submit value="Forgot Password" action="forgotPasswordAction" cssClass="btn" ></s:submit>
						
						<!--<a id='forgotPassword' href="<s:url action="forgotPasswordAction"/>">Forgot Password</a>
						--></td>
					</tr>
				</table>
	</div>
	<!-- loginBox ends -->
	<div class="footer-info"><img src="images/CGILogo.jpg"
		width="100" height="20" alt="powered by logica" /></div>
	</div>
</s:form>
</body>
<script type="text/javascript">
var signOn = document.getElementById("signOnFlag").value;

//alert(signOn);
if(signOn==1)
	{
	//alert("Confirmation Box Are U Want To delete the old session");
	if(confirm('Do You Want To Stop The Existing session ?')){
		
		document.forms[0].action = "reLogin" ;
		document.forms[0].submit();
	}
	}
else
	{
		
	}
</script>