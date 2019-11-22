<%@page import="com.opensymphony.xwork2.ActionContext"%>
<%@page language="java" import="java.util.*,com.logica.ngph.web.action.*,java.lang.*, com.logica.ngph.dtos.AccountDto" contentType="text/html; charset=ISO-8859-1"
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
	href="#">Enquiries & Reports</a>&nbsp;>&nbsp;MMID Reports</span></div>
<div id="content">
<h1><s:label name="title" ></s:label></h1>
<s:form method="post" action="downloadReportMMIDReport" validate="true" id="form">
<s:hidden name="reportType"/>
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
				<td class="textRight">Account Number&nbsp;</td>
				<td class="text"><s:label name="accountNo" ></s:label>
				</td>
			</tr>
			</table>
		</td>
	</tr>
</table><br/>

	<table width="100%" cellpadding="3" cellspacing="1" border="0">
		<tr>
			<td>
						
				<%
				Map<String, List<AccountDto>> displayaccNumVsAccDto = (Map<String, List<AccountDto>>) session.getAttribute("displayaccNumVsAccDto"); 
				if(displayaccNumVsAccDto.isEmpty()){ %>			
			
			<div class="infoMsg"><span>Nothing found to display</span></div>
			<%}  %>
			
			</td></tr></table>
			<%if(!displayaccNumVsAccDto.isEmpty()) 
				
				 for (String accountNumber : displayaccNumVsAccDto.keySet()) {
			
			%>					  
				    	<table width="100%" align="center" cellpadding="3" cellspacing="1" border="2">
				   		 <tr>
				    	<td width="20%" class="textRight"><s:label value="%{getText('label.accountNumber')}"></s:label>:</td>
				   		 <td width="30%" class="text"><%=accountNumber%></td>
				   		 <%
				   		List<AccountDto> accDtoSubList1 = displayaccNumVsAccDto.get(accountNumber);
						String  accountName = "";
						for (AccountDto accountDto : accDtoSubList1) { 
						
							accountName = accountDto.getAccountName();
						}
				   		 %>
				    	 <td width="30%" class="text"><%=accountName%></td>
				    	 </tr>
				    	 <tr>
				    	 <td width="20%" class="textRight"><s:label value="%{getText('label.MMID')}"></s:label>:</td>
				    	 <td width="20%" class="textRight"><s:label value="%{getText('label.MobileNumber')}"></s:label>:</td>
				    	  <td width="20%" class="textRight"><s:label value="Is Default"></s:label>:</td>
				    	 
				    	 </tr>
				    	 <%
				    		List<AccountDto> accDtoSubList = displayaccNumVsAccDto.get(accountNumber);
				    		for (AccountDto accountDto : accDtoSubList) {
				    	 %>
						<tr><td width="30%" class="text"><%=accountDto.getMMID() %></td> 
							<td width="30%" class="text"><%=accountDto.getMobileNo()%></td>
							<td width="30%" class="text"><%=accountDto.getIsDefault()%></td>
						</tr>
				    
				   		 <%} %>
				    	</table>
				   
					
			<%} %>



	<div class="clearfix"></div>
	<table width="100%" cellpadding="0" cellspacing="0" border="0">
		<tr>
			<td> <font>Download Report :</font>
				<a href="#" onclick="setReportType('EXCEL')">
					Excel 
				</a>|
				<a href="#" onclick="setReportType('PDF')">
					PDF
				</a> |
				<a href="#" onclick="setReportType('CSV')">
					CSV
				</a>
			</td>
		</tr>
	</table>
			
			</table></s:form></div>
</div>
</div>
<div id="contentPaneBottom"></div>