<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript">

function fetchSecurityQuestion()
{


    
}



</script>
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="css/ngph.css"
	media="screen" />
	<body>
	
<s:form namespace="/" action="userLogin" method="post">


	<div class="loginBg">
	<div id="loginLogo"><img src="images/loginLogo.png" width="143px" height="82px" alt=""></div>
	<!-- loginBox starts -->
	<div id="loginBox">
				<table width="100%">
					<tr>
						<td colspan="2"><img src="images/loginUser.jpg" width="25"
							height="25px" alt="QNG logo"> <span class="login-header">New Password Change</span></td>
					</tr>
					<tr>
						<td>
						<!--Error Msg Div Starts --> <s:if test="hasFieldErrors() || hasActionErrors() || hasActionMessages()">
				<div class="errorMsg"><span class="errorMsgInline"><s:actionerror /><s:fielderror /></span></div>
			</s:if> <!--Error Msg Div Ends -->
						
						</td>
					</tr>
					<tr>
						<s:hidden name="loginAttempt" value="%{'1'}" />	
					</tr>
					<tr>
						<td><s:label value="New Password: "></s:label><span class="mandatory">*</span></td>
					</tr>
					<tr>
						<td>
							<s:password name="newPwd" id="newPwd"></s:password> 
   						</td>
					</tr>
					<tr>
						<td><s:label value="Confirm Password:"></s:label><span class="mandatory">*</span></td>
					</tr>
					<tr>
						<td>
							<s:password name="confPwd" id="confPwd"></s:password>
						</td>
					</tr>
					</tr>			
					<tr>
						<td><s:submit value="Submit" action="newPasswordChange" cssClass="btn" ></s:submit></td>
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

</script>