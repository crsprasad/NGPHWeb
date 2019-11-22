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
	function assignPopup(accountNo, MMID) {

			window.opener.document.getElementById("accountNo").value = accountNo;
			window.opener.document.getElementById("branch").value = MMID;
			
	window.close();
	//window.opener.location.reload();
	window.opener.callShowGrid();
	
	 

	}
	function cancel() {
		window.close();
	}

	function DataSearch() {

		var parentAction = window.opener.document
				.getElementById("searchAction").value;
		
		document.getElementById("action").value = parentAction;
		
		

	}
</script>
</head>

<body>

<s:actionerror />
<s:form method="post" action="accountSearchAction" name="searchForm">
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
				<td width="25%" class="textRight"><s:label
					value="Account No"></s:label>:&nbsp;</td>
				<td width="25%" class="text"><s:textfield name="accountNo"></s:textfield></td>
				
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
				
				<div style="overflow: auto; height: 245px; width: 100%;border:0px solid #ff0000;">
					<display:table id="row" name="searchList" requestURI="/accountSearchAction" cellpadding="5" cellspacing="1" class="dataGrid" pagesize="10">
					<display:column title="Code" headerClass="gridHdrBg" class="gridText">
					<display:setProperty name="basic.msg.empty_list" 
    				value='<div class="searchMsg"><span>Nothing found to display</span></div>' />
    				<a href="javascript:assignPopup('${row.accoutNo}','${row.ACCT_OWN_BRANCH}')">
		 			<s:set name="accoutNo">${row.accoutNo}</s:set>
		 			<s:property value="%{accoutNo}"/></a>
					</display:column>
					<display:column title=" Branch Code" property="ACCT_OWN_BRANCH"
						headerClass="gridHdrBg" class="gridText"></display:column>
					
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