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
	function assignPopup(code, name) {

		var parentAction = document.getElementById("action").value;

		if (parentAction == "BRANCH") {
			window.opener.document.getElementById("ruleBranch").value = code
					+ "-" + name;
		}
		if (parentAction == "DEPARTMENT") {
			window.opener.document.getElementById("ruleDeptName").value = code
					+ "-" + name;
		}
		if (parentAction == "ACTION") {
			window.opener.document.getElementById("ruleActParam").value = code;
		}

		window.close();

	}
	function cancel() {
		window.close();
	}

	function DataSearch() {

		var parentAction = window.opener.document
				.getElementById("searchAction").value;
		var direction = window.opener.document
		.getElementById("messageDirection").value;
		document.getElementById("action").value = parentAction;
		document.getElementById("MessageDirection").value = direction;
		

	}
</script>
</head>

<body>

<s:actionerror />
<s:form action="searchAction" name="searchForm" method="post">
	<s:hidden id="action" name="action" />
	<s:hidden id="MessageDirection" name="direction" />
	
	<table width="100%" cellpadding="0" cellspacing="0" border="0"
		class="text">
		<tr>
			<td>
			<table width="100%" cellpadding="0" cellspacing="0" border="0">
				<tr>
					<td width="90%" class="text">${action}</td>
					<td class="text"><input type="button" value="Close Window"
						class="btn" onclick="cancel()" /></td>
				</tr>
			</table>
		<div class="dataGrid">
		<table width="100%" cellpadding="3" cellspacing="1" border="0"
				bgcolor="#ffffff" align="center">
			<tr>
				<td width="25%" class="textRight"><s:label
					value="%{getText('label.Code')}"></s:label>:&nbsp;</td>
				<td width="25%" class="text"><s:textfield name="code"></s:textfield></td>
				<td width="25%" class="textRight"><s:label
					value="%{getText('label.Name')}"></s:label>:&nbsp;</td>
				<td width="25%" class="text"><s:textfield name="description"></s:textfield></td>
			</tr>
		</table>
		</div>
		<table width="100%" cellpadding="0" cellspacing="0" border="0"
			bgcolor="#ffffff" align="center">
			<tr>
				<td class="textCenter"><br />
				<s:submit value="Search" cssClass="btn" onclick="DataSearch()"></s:submit><br />
				</td>
			</tr>
			<tr>
				<td><br/>
				
				<div style="overflow: auto; height: 245px; width: 100%;border:0px solid #ff0000;"><display:table
					id="row" name="searchList" requestURI="/searchAction"
					cellpadding="5" cellspacing="1" class="dataGrid" pagesize="3">
					 <display:setProperty name="basic.msg.empty_list" 
    				value='<div class="searchMsg"><span>Nothing found to display</span></div>' />
					<display:column title="Code" headerClass="gridHdrBg"
						class="gridText">
						
						<a href="javascript:assignPopup('${row.code}','${row.name}')">
						<s:set name="myRuleCode">${row.code}</s:set> <s:property
							value="%{myRuleCode}" /> </a>
					</display:column>
					<display:column title="Name" property="name"
						headerClass="gridHdrBg" class="gridText"></display:column>
						<s:if test="%{action=='ACTION'}">
						
						<display:column title="Type" property="type"
						headerClass="gridHdrBg" class="gridText"></display:column>
						</s:if>
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