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
	function assignPopup(ifscCode) {
		window.opener.document.getElementById("ifscCode").value = ifscCode;
		window.close();
		
	}
	function cancel() {
		window.close();
	}

	function DataSearch() {
		document.getElementById("stateName").value  = window.opener.document.getElementById("stateName").value;
		document.getElementById("bankName").value  = window.opener.document.getElementById("bankName").value;
		document.getElementById("branchName").value  = window.opener.document.getElementById("branchName").value;
		var parentAction = window.opener.document.getElementById("searchAction").value;
		document.getElementById("action").value = parentAction;
		
		

	}
	window.onload = function() {
	
};
</script>
</head>

<body>
<s:form method="post" action="getIFSCCodeListData" >
	<s:hidden id="action" name="action" />
	<s:hidden name="stateName" id="stateName"></s:hidden>
	<s:hidden name="bankName" id="bankName"></s:hidden>
	<s:hidden name="branchName" id="branchName"></s:hidden>
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
				<td width="25%" class="textRight"><s:label value="IFSC Code"></s:label>:&nbsp;</td>
				<td width="25%" class="text"><s:textfield name="ifscCode" id="ifscCode"></s:textfield></td>
				
			</tr>
		</table>
		</div>
		<table width="100%" cellpadding="0" cellspacing="0" border="0"
			bgcolor="#ffffff" align="center">
			<tr>
				<td class="textCenter"><br />
					<s:submit value="Search" onclick="DataSearch()" cssClass="btn" ></s:submit><br />
				</td>
			</tr>
			<tr>
				<td><br/>
				
				<div style="overflow: auto; height: 245px; width: 100%;border:0px solid #ff0000;" >
				<display:table uid="row" name="sessionScope.ifscCodeList" pagesize="10" requestURI="/getIFSCCodeListData" cellpadding="3"
				cellspacing="0" class="dataGrid" >
				<display:setProperty name="basic.msg.empty_list" 
    				value='<div class="infoMsg"><span>Nothing found to display</span></div>' />
				<display etProperty name="css.tr.even" value="TDBGlightgray" />
				<display etProperty name="css.tr.odd" value="TDBGgray" cssClass="odd"/>

				<display:column title="IFSC Code" headerClass="gridHdrBg" class="gridText" style="text-align:left">
					<display:setProperty name="basic.msg.empty_list" 
    				value='<div class="searchMsg" ><span>Nothing found to display</span></div>' />
    				<a href="javascript:assignPopup('${row.ifscCode}')">${row.ifscCode}
		 			</a>
				</display:column>
				<display:column title="Bank Name" property="partyBankName" headerClass="gridHdrBg" 
					class="gridText" style="text-align:left"/>
				<display:column title="Branch" property="branchName" headerClass="gridHdrBg" 
					class="gridText" style="text-align:left"/>
				<display:column title="Bank Address" property="partyAddress" headerClass="gridHdrBg" 
					class="gridText" style="text-align:left"/>		
			</display:table>
				
				
				
				</div></td>
			</tr>
		</table>
		</td>
		</tr>
	</table>
		</td>
		</tr>
	</table>
</s:form>
</body>
</html>