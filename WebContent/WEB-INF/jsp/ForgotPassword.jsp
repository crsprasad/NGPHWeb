<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript">

function fetchSecurityQuestion()
{

	var userName = document.getElementById("user");
	
	if(userName!=null)
	{	
		var userId = document.getElementById("user").value;
		if(userId!=null){
			document.forms[0].action = "fetchSecurityQuestion";
			document.forms[0].submit();
		}
		else
			{
				alert("Please Enter Username!");
			}
		
	}
	else
	{
		alert("Please Enter Username");
	}
    
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
							height="25px" alt="QNG logo"> <span class="login-header">Forgot Password</span></td>
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
						<td><s:label value="Username:"></s:label><span class="mandatory">*</span></td>
					</tr>
					<tr>
						<td><s:textfield name="user" id="user" cssClass="txtField" required="true" onchange="fetchSecurityQuestion();"></s:textfield></td>
					</tr>
					<tr>
						<td><s:label value="Security Question: "></s:label><span class="mandatory">*</span></td>
					</tr>
					<tr>
						<td>
							<s:select label="securityQuestion" list="{'What is your fathers middle name?','In what city were you born?','What was your favorite place to visit as a child?','What is the name of your first school?','What is your mothers maiden name?','What was the make of your first car?', 'What is your favorite color?','Where Did you travel for the first time?','When is your anniversary year?','Whats your dream job?' }" cssClass="txtField" name="securityQuestion" id="securityQuestion"
   							 headerKey="0" headerValue="Select Security Question" key="label.securityQuestion" value="securityQuestion"
   							 required="true"></s:select>
   						
   						<!--
   						<s:select label="securityQuestion" list="#{session.sequrityQuestionDropDownList}" cssClass="txtField" name="securityQuestion" id="securityQuestion"
   							 headerKey="0" headerValue="Select Security Question" key="label.securityQuestion" value="securityQuestion"
   							 required="true"></s:select>-->
   						</td>
					</tr>
					<tr>
						<td><s:label value="Security Answer:"></s:label><span class="mandatory">*</span></td>
					</tr>
					<tr>
						<td><s:textfield name="securityAnswer" cssClass="txtField"></s:textfield></td>
					</tr>
					</tr>			
					<tr>
						<td><s:submit value="Submit" action="validateSecAnswerAction" cssClass="btn" ></s:submit></td>
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