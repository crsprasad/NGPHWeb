
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
	function assignPopup(text) {
		var parentAction = document.getElementById("action").value;

		if (parentAction == "APPLICANTIFSC") {
			window.opener.document.getElementById("applicantBankCode").value = text;
		}
		if (parentAction == "AUTHORISEDIFSC") {
			window.opener.document.getElementById("authorisedBankCode").value = text;
		}
		if (parentAction == "REIMBUSINGIFSC") {
			window.opener.document.getElementById("reimbursingBankCode").value = text;
		}
		if (parentAction == "DRAWEEIFSC") {
			window.opener.document.getElementById("draweeBankCode").value = text;
		}
		if (parentAction == "ADVISINGBANK") {
			window.opener.document.getElementById("advisingBank").value = text;
		}
		if (parentAction == "ADVISINGIFSC") {
			window.opener.document.getElementById("advisingBank").value = text;
		}
		if (parentAction == "SENDERIFSC") {
			window.opener.document.getElementById("sendersCorrespondentCode").value = text;
		}
		if (parentAction == "RECEIVERIFSC") {
			window.opener.document.getElementById("receiversCorrespondentCode").value = text;
		}
		if (parentAction == "NEGOTIATIONIFSC") {
			window.opener.document.getElementById("negotiatingBankCode").value = text;
		}
		if (parentAction == "ADVICETHOUGHIFSC") {
			window.opener.document.getElementById("adviseThroughBankCode").value = text;
		}
		if (parentAction == "ISSUEBANKTHOUGHIFSC") {
			window.opener.document.getElementById("issuingBankCode").value = text;
		}	
		if (parentAction == "BENEFICIARYBANKTHOUGHIFSC") {
			window.opener.document.getElementById("beneficiaryBankCode").value = text;
		}
		if (parentAction == "BGCOVISSUEBANKTHOUGHIFSC") {
			window.opener.document.getElementById("bgIssuingBankCode").value = text;
		}
			
		window.close();

	}
	function cancel() {
		window.close();
	}
	
	function DataSearch() {
		//alert(window.opener.document.getElementById("IFSCFLAG").value)
		document.getElementById("IFSCFLAG").value  = window.opener.document.getElementById("IFSCFLAG").value;
		var parentAction = window.opener.document.getElementById("searchAction").value;
		document.getElementById("action").value = parentAction;

	}
</script>
</head>

<body>

<s:actionerror />
<s:form method="post" action="searchIFSCCode">

	<s:hidden id="action" name="action" />

<s:hidden name="IFSCFLAG" id="IFSCFLAG"></s:hidden>

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
					<td width="25%" class="textRight"><s:label value="IFSC Code">
					</s:label>:&nbsp;</td>
					<td width="25%" class="text"><s:textfield name="ifscCode"
						id="ifscCode"></s:textfield></td>

				</tr>
			</table>
			</div>
			<table width="100%" cellpadding="0" cellspacing="0" border="0"
				bgcolor="#ffffff" align="center">
				<tr>
					<td class="textCenter"><br />
					<s:submit value="Search" onclick="DataSearch()"></s:submit> <br />
					</td>
				</tr>
				<tr>
					<td><br />

					<div
						style="overflow: auto; height: 245px; width: 100%; border: 0px solid #ff0000;">
					<display:table id="row" name="ifscList" cellpadding="5"
						cellspacing="1" class="dataGrid" pagesize="8"
						requestURI="/searchIFSCCode">
						<display:column title="IFSC CODE" headerClass="gridHdrBg"
							class="gridText">
							<display:setProperty name="basic.msg.empty_list"
								value='<div class="searchMsg"><span>Nothing found to display</span></div>' />
							<a
								onclick="javascript:assignPopup('${row.clearingSystemMemberId}')"
								href="#">${row.clearingSystemMemberId}</a>
						</display:column>
						<display:column title="Branch" property="partyBranchName" headerClass="gridHdrBg" class="gridText" style="text-align:left"/>
						<display:column title="Branch Address" property="partyAddress"  headerClass="gridHdrBg" class="gridText" style="text-align:left"/>


					</display:table></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
	</table>
</s:form>
</body>
</html>




















