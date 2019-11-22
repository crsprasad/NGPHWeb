<%@page import="com.opensymphony.xwork2.ActionContext"%>
<%@page language="java" import="java.util.*,com.logica.ngph.web.action.*,java.lang.*,java.math.*" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<%@taglib uri="http://displaytag.sf.net" prefix="display"%>
<link href="SpryAssets/SpryCollapsiblePanel.css" rel="stylesheet"
	type="text/css" />
<script src="SpryAssets/SpryCollapsiblePanel.js" type="text/javascript"></script>
<script type="text/javascript">

function callViewInformation(msgRef,infoDirection)
{
	
	document.getElementById("msgRef").value=msgRef;
	document.getElementById("infoDirection").value=infoDirection;
	document.forms[0].action="displayRawInformationMsg";
	document.forms[0].submit();
	

}

function callPrintPreview(msgRef)
{
	document.getElementById("messagegRef").value=msgRef;
	document.forms[0].action="printPreviewFreeFormatPage";
	document.forms[0].submit();
}

</script>
<sx:head parseContent="true" />
<s:head />
<div id="contentPane">
<div id="contentPaneTop"></div>
<div id="contentPaneFluid">
<div id="contentPaneInternal"><span class="crumbs"><a
	href="#"><s:label id="msgDirection" name="msgDirection"></s:label></a>&nbsp;>&nbsp;Information</span></div>
<div id="content">
<h1>Information</h1>
<s:form method="post">
<div>
<s:if test="hasFieldErrors()">
<div class="errorMsg"><span class="errorMsgInline"><s:actionerror /><s:fielderror /></span></div>
</s:if></div><!--Error Msg Div Ends -->
<s:hidden id="direction" name="direction" />
<s:hidden id="infoMsg" name="infoMsg"  />
<s:hidden id="msgRef" name="msgRef" />
<s:hidden id="rawMsg" name="rawMsg" />
<s:hidden id="messagegRef" name="messagegRef" />
<s:hidden id="infoDirection" name="infoDirection" />
	<table width="99%" cellpadding="3" cellspacing="1" border="0"
		bgcolor="#ffffff" id="table1">
			<tr>
			<td>
			<display:table uid="row" name="sessionScope.informationList"
					pagesize="10" requestURI="/displayoutboundInfoScreenoutInfo" cellpadding="3" cellspacing="0" class="dataGrid">
				<display:setProperty name="basic.msg.empty_list" 
    				value='<div class="infoMsg"><span>Nothing found to display</span></div>' />
				<display etProperty name="css.tr.even" value="TDBGlightgray" />
				<display etProperty name="css.tr.odd" value="TDBGgray" cssClass="odd"/>
				
				<display:column title="Reference" property="txnReference"
					headerClass="gridHdrBg"  style="text-align:center" />

				<display:column title="Rel Refer" property="relatedRefrence"
					headerClass="gridHdrBg"  style="text-align:center" />
				<display:column title="Branch" property="branch"
					headerClass="gridHdrBg"  style="text-align:center" />
				<display:column title="Department" property="department"
					headerClass="gridHdrBg"  style="text-align:center" />
				<display:column title="MsgT ype" property="msgType"
					headerClass="gridHdrBg"  style="text-align:center" />
				<display:column title="Sub Msg Type" property="subMsgType"
					headerClass="gridHdrBg"  style="text-align:center" />
				<display:column title="Sender Bank" property="senderBank"
					headerClass="gridHdrBg"  style="text-align:center" />
				<display:column title="Receiver Bank" property="receiverBank"
					headerClass="gridHdrBg"  style="text-align:center" />
				<display:column title="info" property="information"
					headerClass="gridHdrBg" maxLength="20" style="text-align:center" />
				<display:column title="Last Mod Time" property="lastModTime"
					headerClass="gridHdrBg"  style="text-align:center" />
				<display:column title="Status" property="msgStatus"
					headerClass="gridHdrBg" maxLength="20" style="text-align:center" />
				<display:column style="text-align:center">
				<input type="hidden" value="${row.direction}" id="direction" />
				<!--  <input type ="hidden" value="${row.information}" id="${row.msgRef}" />-->
				<!--<inpu type="hidden" value="${row.msgRef}" id="msgRef">
				--><input type="button" class="btn"  value="View Raw Message"	onclick="callViewInformation('${row.msgRef}','${row.direction}')" />	
				<input type="button" class="btn"  value="Print Preview"	onclick="callPrintPreview('${row.msgRef}')" />
				</display:column>
					</display:table>
					</td>
			</tr>
	</table>
					
</s:form>
</div>
</div>
</div>
<div id="contentPaneBottom"></div>