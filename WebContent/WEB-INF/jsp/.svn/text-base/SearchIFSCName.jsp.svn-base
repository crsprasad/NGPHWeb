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
	function assignPopup(name,branchIFSC,partyCode,partyBankName,partyAddress) {
		
		
	//alert(name+branchIFSC)
		window.opener.document.getElementById("partyName").value = name;
		window.opener.document.getElementById("ifsc").value = branchIFSC;
		window.opener.document.getElementById("partyCode").value = partyCode;
		window.opener.document.getElementById("partyBankName").value = partyBankName;
		window.opener.document.getElementById("partyAddress").value = partyAddress;
		
		
		window.close();

	}	
	function cancel() {
		window.close();
	}

	function DataSearch() {

	//	var parentAction = window.opener.document
			//	.getElementById("searchRuleIDAction").value;
		
		//document.getElementById("action").value = parentAction;
		
		

	}
</script>
</head>

<body>
<s:form method="post" action="searchIFSCAction" name="searchForm">
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
					<s:label value="Branch Code"> </s:label>:&nbsp;
				</td>
				<td width="25%" class="text">
					<s:textfield name="code" id="code"></s:textfield>
				</td>
				
			</tr>
		</table> 
		</div>
		<table width="100%" cellpadding="0" cellspacing="0" border="0" bgcolor="#ffffff" align="center">
			<tr>
				<td class="textCenter">
					<br/>
					<s:submit value="Search" cssClass="btn" onclick="DataSearch()"></s:submit>
					<br/>
				</td>
			</tr>
			<tr>
				<td><br/>
				
				<div style="overflow: auto; height: 245px; width: 100%;border:0px solid #ff0000;">
					<display:table id="row" name="searchList"  cellpadding="5" cellspacing="1" class="dataGrid" pagesize="8" requestURI="/searchIFSCAction">
					<display:column title="Party Code" headerClass="gridHdrBg" class="gridText">
						<display:setProperty name="basic.msg.empty_list" value='<div class="searchMsg"><span>Nothing found to display</span></div>' />
	    				<a onclick="javascript:assignPopup('${row.partyName}','${row.ifsc}','${row.partyCode}','${row.partyBankName}','${row.partyAddress}')" href="#">${row.partyCode}</a>
			 			</display:column>
					<display:column title="Branch Name" property="partyName" headerClass="gridHdrBg" class="gridText"></display:column>
					<display:column title="IFSC" property="ifsc" headerClass="gridHdrBg" class="gridText"></display:column>
					
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