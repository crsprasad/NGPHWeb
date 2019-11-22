<%@page import="com.opensymphony.xwork2.ActionContext"%>
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<%@taglib uri="http://displaytag.sf.net" prefix="display"%>

<s:head theme="simple" />
<sx:head parseContent="true" />
<script type="text/javascript">

function callSubmit(lcNumber,flagMarked)
{
	document.getElementById("lcNumberForInward").value = lcNumber;
	document.getElementById("flagMarked").value = flagMarked;
	document.forms[0].action = "populateDataInLcOpen";
	document.forms[0].submit();

}
</script>
<div id="contentPane">
<div id="contentPaneTop"></div>
<div id="contentPaneFluid">
<div id="contentPaneInternal"><span class="crumbs"><a href="#">Outward Payments></a>&nbsp;Lc Inward</span></div>
<div id="content">
<h1>Lc Inward </h1>
<br />
<form method="post">
<!--Error Msg Div Ends -->
			<s:hidden name="lcNumberForInward" id="lcNumberForInward">  </s:hidden>
			<s:hidden name="flagMarked" id="flagMarked">  </s:hidden>
			<div><s:if test="hasFieldErrors()">
				<div class="errorMsg"><span class="errorMsgInline"><s:actionerror /><s:fielderror /></span></div>
			</s:if></div><!--Error Msg Div Ends -->
<table width="99%" cellpadding="3" cellspacing="1" border="0" bgcolor="#ffffff" id="table1">
		<tr>
			<td><display:table uid="row" name="sessionScope.searchList"
				pagesize="10" requestURI="/displayAllLcNumber" cellpadding="3"
				cellspacing="0" class="dataGrid">
				<display etProperty name="css.tr.even" value="TDBGlightgray" />
				<display etProperty name="css.tr.odd" value="TDBGgray" cssClass="odd"/>

				<display:column title="Lc Number" property="lcNo"
					headerClass="gridHdrBg"  sortable="true"  />
				<display:column title="Issuing Bank" property="lcIssuingBank"
					headerClass="gridHdrBg"  sortable="true"  />
				<display:column title="Appicant" property="lcAppicant"
					headerClass="gridHdrBg"  sortable="true"  />
					<display:column title="Benificiary" property="lcBenificiary"
					headerClass="gridHdrBg"  sortable="true"  />
					<display:column title="Amount" property="lcAmount"
					headerClass="gridHdrBg"  sortable="true"  />
					<display:column title="Issue Date" property="lcIssueDate"
					headerClass="gridHdrBg"  sortable="true"  />
					<display:column title="Expire Date" property="lcExpireDate"
					headerClass="gridHdrBg"  sortable="true"  />
					
					

				<display:column	headerClass="gridHdrBg"  sortable="true" maxLength="50" >
			
					<input type="button" value="View" onclick="callSubmit('${row.lcNo}','flagMarked')">
				</display:column>
				
					
			</display:table></td>
		</tr>
	</table>
</form>
</div>
</div>
</div>