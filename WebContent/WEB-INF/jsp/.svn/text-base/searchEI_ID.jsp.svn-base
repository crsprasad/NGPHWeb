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
	function assignPopup(eiCode, eiName,eiType,eiEspProvName,EI_CONN_TYPE,EI_FORMAT,EI_HOST_CATGORY,MQ_Manager_Name,
			MQ_Server_ID,MQ_Server_Port,MQ_Queue_Name) {

alert("hello");
		
		
			window.opener.document.getElementById("EI_ID").value = eiCode;
			window.opener.document.getElementById("EI_Host_Category").value = EI_HOST_CATGORY;

			window.opener.document.getElementById("EI_Name").value = eiName;
			window.opener.document.getElementById("EIType").value = eiType;
			window.opener.document.getElementById("EI_Gateway").value = eiEspProvName;
			window.opener.document.getElementById("EI_Format").value = EI_FORMAT;
			window.opener.document.getElementById("EIConnectionType").value = EI_CONN_TYPE;

			window.opener.document.getElementById("MQ_Manager_Name").value = MQ_Manager_Name;
			window.opener.document.getElementById("MQ_Server_ID").value = MQ_Server_ID;
			window.opener.document.getElementById("MQ_Server_Port").value = MQ_Server_Port;
			window.opener.document.getElementById("MQ_Queue_Name").value = MQ_Queue_Name;
		//	window.opener.document.getElementById("Client_Connection_Channel").value = Client_Connection_Channel;
			

		window.close();

	}
	function cancel() {
		window.close();
	}

	function DataSearch() {

		var parentAction = window.opener.document
				.getElementById("eiIDsearchAction").value;
		
		document.getElementById("action").value = parentAction;
		
		

	}
</script>
</head>

<body>

<s:actionerror />
<s:form method="post" action="eiIDsearchAction" name="searchForm">
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
					value="EI ID"></s:label>:&nbsp;</td>
				<td width="25%" class="text"><s:textfield name="RuleId"></s:textfield></td>
				
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
					<display:table id="row" name="searchList" requestURI="/SearchRuleIDAction" cellpadding="5" cellspacing="1" class="dataGrid" pagesize="10">
					<display:column title="Code" headerClass="gridHdrBg" class="gridText">
					<display:setProperty name="basic.msg.empty_list" 
    				value='<div class="searchMsg"><span>Nothing found to display</span></div>' />
    				<a href="javascript:assignPopup('${row.eiCode}','${row.eiName}','${row.eiType}','${row.eiEspProvName}','${row.EI_CONN_TYPE}','${row.EI_FORMAT}','${row.EI_HOST_CATGORY}','${row.MQ_Manager_Name}','${row.MQ_Server_ID}','${row.MQ_Server_Port}','${row.MQ_Queue_Name}')">
		 			<s:set name="eiCode">${row.eiCode}</s:set>
		 			<s:property value="%{eiCode}"/></a>
					</display:column>
					<display:column title="Name" property="eiName"
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