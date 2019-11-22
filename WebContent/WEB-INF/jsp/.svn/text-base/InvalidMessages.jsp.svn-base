<%@page import="com.opensymphony.xwork2.ActionContext"%>
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<%@taglib uri="http://displaytag.sf.net" prefix="display"%>
<script type="text/javascript">


function displaymsg(msgRef)
{
	
	document.getElementById("msgRef").value = msgRef;
	
	document.forms[0].action = "changeStatusAndMSgpolled";
	document.forms[0].submit();
}
</script>
<s:head theme="simple" />
<sx:head parseContent="true" />

<div id="contentPane">
<div id="contentPaneTop"></div>
<div id="contentPaneFluid">
<div id="contentPaneInternal"><span class="crumbs"><a href="#">Outward Payments></a>&nbsp;Invalid Messages</span></div>
<div id="content">
<h1>Error Messages </h1>
<br />
<form method="post" >
<!--Error Msg Div Ends -->
			
<table width="99%" cellpadding="3" cellspacing="1" border="0" bgcolor="#ffffff" id="table1">
<s:hidden name="rawMsg" id="rawMsg"></s:hidden>
<s:hidden name="errCode" id="errCode"></s:hidden>
<s:hidden name="msgRef" id="msgRef"></s:hidden>
		<tr>
			<td><display:table uid="row" name="sessionScope.dataList"
				pagesize="10" requestURI="/invalidMessages" cellpadding="3"
				cellspacing="0" class="dataGrid">
				<display etProperty name="css.tr.even" value="TDBGlightgray" />
				<display etProperty name="css.tr.odd" value="TDBGgray" cssClass="odd"/>

				<display:column title="MSGREF" property="msgRef"
					headerClass="gridHdrBg"  sortable="true" />

				<display:column title="MESSAGE" property="strMsg"
					headerClass="gridHdrBg"  sortable="true" maxLength="50" />
				
				<display:column title="PROVIDER HOST" property="rawHost"
					headerClass="gridHdrBg"  sortable="true" />
				
				<display:column title="CHANNEL" property="rawChnl"
					headerClass="gridHdrBg"  sortable="true" />
				
				<display:column title="DIRECTION" property="rawDirectn"
					headerClass="gridHdrBg"  sortable="true" />
				
				<display:column title="RECEIVED TIME" property="rawRcvdTm"
					headerClass="gridHdrBg"  sortable="true" />
					
					<display:column title="Error" property="errorCode"
					headerClass="gridHdrBg"  sortable="true" />
					<display:column>
					
						<input type ="button" value="View" onclick="javascript:displaymsg('${row.msgRef}'); "/>
					</display:column>
				
				</display:table></td>
		</tr>
	</table>
</form>
</div>
</div>
</div>