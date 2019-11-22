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
		window.opener.callShowGrid();
		
		
	}
	function cancel() {
		window.close();
	}

	function DataSearch() {
				
		var parentAction = window.opener.document.getElementById("searchAction").value;
		document.getElementById("action").value = parentAction;
		
	}
	window.onload = function() {
	
};
</script>
</head>

<body>
<s:form method="post" action="getIFSCCodeList" >
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
				<display:table uid="row" name="sessionScope.ifscCodeList" pagesize="10" requestURI="/getIFSCCodeList" cellpadding="3"
				cellspacing="1" class="dataGrid" >
				<display:setProperty name="basic.msg.empty_list" 
    				value='<div class="infoMsg"><span>Nothing found to display</span></div>' />
				<display etProperty name="css.tr.even" value="TDBGlightgray" />
				<display etProperty name="css.tr.odd" value="TDBGgray" cssClass="odd"/>

				<display:column title="IFSC Code" headerClass="gridHdrBg" class="gridText" style="text-align:center">
					<display:setProperty name="basic.msg.empty_list" 
    				value='<div class="searchMsg" ><span>Nothing found to display</span></div>' />
    				<a href="javascript:assignPopup('${row.ifscCode}')">${row.ifscCode}
		 			</a>
				</display:column>
				<display:column title="BankName" property="partyName" headerClass="gridHdrBg" class="gridText" style="text-align:left"/>
				<display:column title="Branch Name" property="branchName"  headerClass="gridHdrBg" class="gridText" style="text-align:left"/>
				<display:column title="Branch Address" property="partyAddress"  headerClass="gridHdrBg" class="gridText" style="text-align:left"/>
				
					
							
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