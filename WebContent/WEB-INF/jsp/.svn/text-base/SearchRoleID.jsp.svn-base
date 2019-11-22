<%@page import="com.logica.ngph.Entity.Branches"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="css/ngph.css"
	media="screen" />
<title>Search Page</title>
<s:head />

<script type="text/javascript">

   function assignPopup(id) {
		window.opener.document.getElementById("roleId").value = id;
		window.opener.document.roleMaintenanceForm.action = "getRoleData";
		window.opener.document.roleMaintenanceForm.submit();
		window.close();
	}
	
	function cancel() {
		window.close();
	}

</script>
</head>

<body>
<s:actionerror />
<s:form method="post" action="searchRoleIDAction" name="searchForm">
	<s:hidden id="action" name="action" />
	
	<table width="100%" cellpadding="0" cellspacing="0" border="0"
		class="text">
		<tr>
			<td>
			<table width="100%" cellpadding="0" cellspacing="0" border="0">
				<tr>
					
					<td class="text"><input type="button" value="Close Window"
						class="btn" onclick="cancel()" /></td>
				</tr>
			</table>
		<div class="dataGrid">
		<table width="100%" cellpadding="3" cellspacing="1" border="0"
				bgcolor="#ffffff" align="center">
			<tr>
				<td width="25%" class="textRight">
					<s:label value="Role ID"> </s:label>:&nbsp;
				</td>
				<td width="25%" class="text">
					<s:textfield name="roleId" id="roleId"></s:textfield>
				</td>
				
			</tr>
		</table> 
		</div>
		<table width="100%" cellpadding="0" cellspacing="0" border="0" bgcolor="#ffffff" align="center">
			<tr>
				<td class="textCenter">
					<br/>
					<s:submit value="Search" cssClass="btn" ></s:submit>
					<br/>
				</td>
			</tr>
			<tr>
				<td><br/>
				
				<div style="overflow: auto; height: 245px; width: 100%;border:0px solid #ff0000;">
					<display:table id="row" name="searchList"  cellpadding="5" cellspacing="1" class="dataGrid" pagesize="5" requestURI="/searchRoleIDAction">
					<display:column title="Role Id" headerClass="gridHdrBg" class="gridText">
						<display:setProperty name="basic.msg.empty_list" value='<div class="searchMsg"><span>Nothing found to display</span></div>' />
	    				<a onclick="javascript:assignPopup('${row}')" href="#">${row}</a>
			 			</display:column>
					</display:table>
				</div>
				</td>
			</tr>
		</table>
		</td>
		</tr> 
	</table>
</s:form>
</body>
</html>