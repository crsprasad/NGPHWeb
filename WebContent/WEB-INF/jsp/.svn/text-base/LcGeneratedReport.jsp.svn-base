
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
	href="#">Enquiries & Reports</a>&nbsp;>&nbsp;<s:label name="title" ></s:label></span></div>
<div id="content">
<h1><s:label name="title" ></s:label></h1>
<s:form method="post" action="downloadReportLCReport" validate="true" id="form">
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
			<%if(LcReportsAction.direction.equals("O")){ %>
				<td class="textRight">Advising Bank:&nbsp;</td>
				<td class="text"><s:label name="advisingBank" ></s:label>
				</td>
				<%}if( LcReportsAction.direction.equals("I")){%>
				<td class="textRight">Issuing Bank:&nbsp;</td>
				<td class="text"><s:label name="advisingBank" ></s:label>
				</td>
					<%} %>
			</tr>
			<tr>
				<td class="textRight">Status:&nbsp;</td>
				<td><s:label name="status" ></s:label>
				</td>
			</tr>

			<tr>
				<td class="textRight">Applicant:&nbsp;</td>
				<td><s:label name="applicant"></s:label>
				</td>
			</tr>
			<tr>
				<td class="textRight">Beneficiary:&nbsp;</td>
				<td><s:label name="Beneficiary" ></s:label></td>
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

			<tr>
				<td class="textRight">Amount From:&nbsp;</td>
				<td><s:label name="fromAmount" ></s:label></td>
			</tr>
			<tr>
				<td class="textRight">Amount To:&nbsp;</td>
				<td><s:label name="toAmount" ></s:label></td>
			</tr>
		</table>
		</td>
	</tr>
</table><br/><br/>
	<table width="100%" cellpadding="3" cellspacing="1" border="0">
		<tr>
			<td>
			
			<display:table id="data" uid="row" name="sessionScope.displayList" pagesize="10"  requestURI="/generteLcReports" cellpadding="3"
				cellspacing="0" class="dataGrid" >
				<display:setProperty name="basic.msg.empty_list" 
    				value='<div class="infoMsg"><span>Nothing found to display</span></div>' />
				<display etProperty name="css.tr.even" value="TDBGlightgray" />
				<display etProperty name="css.tr.odd" value="TDBGgray" cssClass="odd"/>
				<s:if test="%{session.lcNo=='Required'}">
				<display:column title="Number" property="lcNo" headerClass="gridHdrBg"  sortable="false" /></s:if>
				<s:if test="%{session.lcType=='Required'}">
				<display:column title="Type" property="lcType" headerClass="gridHdrBg"  sortable="false" style="text-align:center" />
				</s:if>
				<s:if test="%{session.lcAdvisingBank=='Required'}">
											<%if(LcReportsAction.direction.equals("O")){ %>
						<display:column title="Advising Bank"	headerClass="gridHdrBg" property="lcAdvisingBank" sortable="false" style="text-align:center" />
					
						<%}if( LcReportsAction.direction.equals("I")){%>
						<display:column title="Issuing Bank"	headerClass="gridHdrBg" property="lcAdvisingBank" sortable="false" style="text-align:center" />
					<%} %>
				</s:if>
				<s:if test="%{session.lcAppicant=='Required'}">
				<display:column title="Applicant" property="lcAppicant" headerClass="gridHdrBg"  sortable="false" style="text-align:center" />
					</s:if>
				<s:if test="%{session.lcBenificiary=='Required'}">
				<display:column title="Beneficiary"	headerClass="gridHdrBg" property="lcBenificiary" sortable="false" style="text-align:center" /></s:if>
				<s:if test="%{session.lcIssueDate=='Required'}">
				<display:column title="Issue Date"   property="lcIssueDate" headerClass="gridHdrBg"  sortable="false" style="text-align:center;width:70px;"/></s:if>
				<s:if test="%{session.lcCurrency=='Required'}">
				<display:column title="Ccy"	headerClass="gridHdrBg" property="lcCurrency" sortable="false" style="text-align:center" /></s:if>
				<s:if test="%{session.lcAmount=='Required'}">
				<display:column title="Amount"	headerClass="gridHdrBg" property="lcAmount" sortable="false" style="text-align:center" /></s:if>
				<s:if test="%{session.lcExpireDate=='Required'}">
				<display:column title="Expiry Date" property="lcExpireDate"	headerClass="gridHdrBg"  sortable="false" style="text-align:center;width:70px;" />
				</s:if>
				<s:if test="%{session.lcNarrative=='Required'}">
				<display:column title="Narrative"	headerClass="gridHdrBg" property="lcNarrative" sortable="false" style="text-align:center" /></s:if>
				<s:if test="%{session.status=='Required'}">
				<display:column title="Status" property="status" headerClass="gridHdrBg"  sortable="false" style="text-align:center"/></s:if>
				<s:if test="%{session.lstModifiedTime=='Required'}">
				<display:column title="Last Modified Time" property="lstModifiedTime" headerClass="gridHdrBg"  sortable="false" style="text-align:center"/></s:if>
				<s:if test="%{session.msgStatus=='Required'}">
				<display:column title="Msg Status" property="msgStatus" headerClass="gridHdrBg"  sortable="false" style="text-align:center"/></s:if>
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
				</a>|
				<a href="#" onclick="setReportType('CSV')">
					<img src="images/csv.png" width="24" height="24" alt="CSV" border="0"/>
				</a>
			</td>
		</tr>
	</table>
			
			</s:form></div>
</div>
</div>
<div id="contentPaneBottom"></div>
