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
	function assignPopup(id,name,branchBIC,branchIFSC,countrySubDivision,department,addressLine,postCode,streetName,townName,addressRef,countryCode,subDepartment,addressType,buildingNumber) {
		
		
		window.opener.document.getElementById("branchCode").value = id;
		window.opener.document.getElementById("branchName").value = name;
		window.opener.document.getElementById("branchBIC").value = branchBIC;
		window.opener.document.getElementById("branchIFSC").value = branchIFSC;
		window.opener.document.getElementById("countrySubDivision").value = countrySubDivision;
		window.opener.document.getElementById("department").value = department;
		window.opener.document.getElementById("addressLine").value = addressLine;
		window.opener.document.getElementById("postCode").value = postCode;
		window.opener.document.getElementById("addressType").value = addressType;
		window.opener.document.getElementById("townName").value = townName;
		window.opener.document.getElementById("addressRef").value = addressRef;
	//	alert(countryCode)
		window.opener.document.getElementById("countryCode").value = countryCode;
		window.opener.document.getElementById("subDepartment").value = subDepartment;
		window.opener.document.getElementById("subDepartment").value = subDepartment;
		window.opener.document.getElementById("streetName").value = streetName;
		window.opener.document.getElementById("buildingNumber").value = buildingNumber;
		
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
<s:form method="post" action="searchBranchAction" name="searchForm">
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
					<display:table id="row" name="searchList"  cellpadding="5" cellspacing="1" class="dataGrid" pagesize="8">
					<display:column title="Branch Code" headerClass="gridHdrBg" class="gridText">
						<display:setProperty name="basic.msg.empty_list" value='<div class="searchMsg"><span>Nothing found to display</span></div>' />
	    				<a onclick="javascript:assignPopup('${row.branchCode}','${row.branchName}','${row.branchBIC}','${row.branchIFSC}','${row.countrySubDivision}','${row.department }','${row.addressLine}','${row.postCode}','${row.streetName}','${row.townName }','${row.addressRef}','${row.countryCode}','${row.subDepartment}','${row.addressType}','${row.buildingNumber}')" href="#">${row.branchCode}</a>
			 			</display:column>
					<display:column title="Branch Name" property="branchName" headerClass="gridHdrBg" class="gridText"></display:column>
					
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