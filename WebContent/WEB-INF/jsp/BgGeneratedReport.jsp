<%@page import="com.opensymphony.xwork2.ActionContext"%>
<%@page language="java" import="java.util.*,com.logica.ngph.web.action.*" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<%@taglib uri="http://displaytag.sf.net" prefix="display"%>
<script type="text/javascript">
function setReportType(type) {
	document.getElementById("reportType").value =type;
	document.form.submit();
}

</script>
<s:head theme="simple" />
<sx:head parseContent="true" />
<div id="contentPane">
<div id="contentPaneTop"></div>
<div id="contentPaneFluid">
<div id="contentPaneInternal"><span class="crumbs"><a
	href="#">Enquiries & Reports</a>&nbsp;>&nbsp;Bg Reports</span></div>
<div id="content">
<h1><s:label name="title" ></s:label></h1>
<s:form method="post" action="downloadReportBgReport" validate="true" id="form">
<s:hidden name="reportType" id="reportType"/>
<div><s:label name="message"></s:label><s:if test="hasFieldErrors()">
				<div class="errorMsg"><span class="errorMsgInline"><s:actionerror /><s:fielderror /></span></div>
			</s:if></div><!--Error Msg Div Ends -->
			<table width="70%" cellpadding="3" cellspacing="1" border="1"
	bgcolor="#ffffff" align="center">
	<tr>
		<td class="divBg">
		<table width="99%" align="center" cellpadding="3" cellspacing="1" border="0"
		bgcolor="#ffffff" id="table1" >
			<tr>
				<td class="textRight">Advising Bank:&nbsp;</td>
				<td class="text"><s:label name="advisingBank" ></s:label>
				</td>
			</tr>
			<tr>
				<td class="textRight">Status:&nbsp;</td>
				<td><s:label name="status" ></s:label>
				</td>
			</tr>

			
			<tr>
				<td class="textRight">From Date:&nbsp;</td>
				<td><s:label name="fromDate" ></s:label>
				</td>
			</tr>

			<tr>
				<td class="textRight">To Date:&nbsp;</td>
				<td><s:label name="toDate" ></s:label>
				</td>
			</tr>

		</table>
		</td>
	</tr>
</table><br/><br/>
	<table width="100%" cellpadding="3" cellspacing="1" border="0">
		<tr>
			<td>
			
			<display:table id="data" uid="row" name="sessionScope.displayBgList" pagesize="10"  requestURI="/generteBgReports" cellpadding="3"
				cellspacing="0" class="dataGrid" >
				<display:setProperty name="basic.msg.empty_list" 
    				value='<div class="infoMsg"><span>Nothing found to display</span></div>' />
				<display etProperty name="css.tr.even" value="TDBGlightgray" />
				<display etProperty name="css.tr.odd" value="TDBGgray" cssClass="odd"/>
				<s:if test="%{session.bgNumber=='Required'}">
				<display:column title="Bg Number" property="bgNumber" headerClass="gridHdrBg"  sortable="false" /></s:if>
					<s:if test="%{session.bgCreateType=='Required'}">
					<display:column title="Bg Type" property="bgCreateType" headerClass="gridHdrBg"  sortable="false" style="text-align:center" />
				</s:if>
				<s:if test="%{session.advisingBank=='Required'}">
					<% if(BgReportsAction.direction.equals("O")){ %>
					<display:column title="Advising Bank"	headerClass="gridHdrBg" property="advisingBank" sortable="false" style="text-align:center" />
					<% }if(BgReportsAction.direction.equals("I")){ %>
					<display:column title="Issuing Bank"	headerClass="gridHdrBg" property="advisingBank" sortable="false" style="text-align:center" />
					<% } %>
				</s:if>
				<s:if test="%{session.bgIssueDate=='Required'}">
					<display:column title="Issue Date"	headerClass="gridHdrBg" property="bgIssueDate" sortable="false" style="text-align:center" maxLength="30" /></s:if>
				<s:if test="%{session.bgAmount=='Required'}">
					<display:column title="Amount"	headerClass="gridHdrBg" property="bgAmount" sortable="false" style="text-align:center" /></s:if>
				<s:if test="%{session.details=='Required'}">
					<display:column title="Details"	headerClass="gridHdrBg" property="details" sortable="false" style="text-align:center" maxLength="30" /></s:if>
				<s:if test="%{session.bgStatus=='Required'}">
						<display:column title="Status"	headerClass="gridHdrBg" property="bgStatus" sortable="false" style="text-align:center" /></s:if>
			<s:if test="%{session.bgLastModifiedTime=='Required'}">
						<display:column title="Last Modified Time"	headerClass="gridHdrBg" property="bgLastModifiedTime" sortable="false" style="text-align:center" /></s:if>
				<s:if test="%{session.msgStatus=='Required'}">
						<display:column title="Msg Status"	headerClass="gridHdrBg" property="msgStatus" sortable="false" style="text-align:center" /></s:if>
					
				<!--<s:if test="%{session.bgRuleCode=='Required'}">
				<display:column title="Bg Rule Code" property="bgRuleCode" headerClass="gridHdrBg"  sortable="false" style="text-align:center"/></s:if>
				<s:if test="%{session.bgRuleDesc=='Required'}">
				<display:column title="Bg Rule Description" property="bgRuleDesc"	headerClass="gridHdrBg"  sortable="false" style="text-align:center" />
					</s:if>
					
					<s:if test="%{session.bgNarration=='Required'}">
				<display:column title="Lc ApplicantbgNarration" property="bgNarration" headerClass="gridHdrBg"  sortable="false" style="text-align:center" />
					</s:if>
				
			
				<s:if test="%{session.bgNoOfAmntmnt=='Required'}">
					<display:column title="No Of Amendment"	headerClass="gridHdrBg" property="bgNoOfAmntmnt" sortable="false" style="text-align:center" /></s:if>
				-->
					
					
			
					
					
			</display:table></td></tr></table>
			<div class="clearfix"></div>
	<table width="100%" cellpadding="0" cellspacing="0" border="0">
		<tr>
			<td> <font>Download Report :</font>
				<a href="#" onclick="setReportType('EXCEL')">
					<img src="images/xls.png" width="24" height="24" alt="EXCEL" border="0"/>
				</a>|
				<a href="#" onclick="setReportType('PDF')">
					<img src="images/pdf.png" width="24" height="24" alt="PDF" border="0"/>
				</a>
				|
				<a href="#" onclick="setReportType('CSV')">
					<img src="images/csv.png" width="24" height="24" alt="CSV" border="0"/>
				</a>
			</td>
		</tr>
	</table>
			
			</table></s:form></div>
</div>
</div>
<div id="contentPaneBottom"></div>
