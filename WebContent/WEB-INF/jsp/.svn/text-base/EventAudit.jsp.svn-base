<%@page import="com.opensymphony.xwork2.ActionContext"%>
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<%@taglib uri="http://displaytag.sf.net" prefix="display"%>
<script type="text/javascript">
function submit()
{
	alert("hello");
	document.forms[0].action="eventSearch";
	document.forms[0].submit();
}

function callRepairAction(count) {
	//alert(count);
	document.getElementById("msgRef").value=count;
	document.forms[0].action = "eventView";
	document.forms[0].submit();

}


</script>
<s:head theme="simple" />
<sx:head parseContent="true" />

<div id="contentPane">
<div id="contentPaneTop"></div>
<div id="contentPaneFluid">
<div id="contentPaneInternal"><span class="crumbs"><a
	href="#">System</a>&nbsp;>&nbsp;Event Audit </span></div>
<div id="content">
<h1>Event Audit</h1>
<div id="content">
<s:form method="post" id="form">
<s:hidden id="msgRef" name="msgRef"></s:hidden>
<div><s:if test="hasFieldErrors()">
				<div class="errorMsg"><span class="errorMsgInline"><s:actionerror /><s:fielderror /></span></div>
			</s:if></div><!--Error Msg Div Ends -->
	<table width="100%" cellpadding="0" cellspacing="0" border="0">
		<tr>
			<td>
			<table width="95%" cellpadding="5" cellspacing="1" border="0" bgcolor="#c5d6f0" id="table1">
			<tr>
			<td width="20%" class="textRight"><s:label value="Action"></s:label></td>
			<td colspan="3" class="text"><s:radio list="#{'C':'Current','H':'History'}" name="currHist" id="currHist" value='currHist' ></s:radio></td>
			</tr>
				<tr>
			<td width="20%" class="textRight"><s:label
						value="Branch"></s:label>:&nbsp;</td>
					<td width="30%" class="text">
						<s:textfield name="eventBranch" id="eventBranch" cssClass="txtField" ></s:textfield>
					</td>
					<td width="20%" class="textRight"><s:label
						value="Department"></s:label>:&nbsp;</td>
					<td width="30%" class="text">
					<s:select list="#session.departmentList" name="eventDepartment" id="eventDepartment" cssClass="txtField" headerKey="" headerValue="Select Department"></s:select>
					 </td>
					
	</tr>
	<tr>
	<td width="20%" class="textRight"><s:label value="Transaction Reference"></s:label> </td>
	<td width="30%" class="text"><s:textfield name="eventTxnRef" id="eventTxnRef"></s:textfield></td>
	<td width="20%" class="textRight"><s:label
						value="Event ID"></s:label>:&nbsp;</td>
					<td width="30%" class="text">
					<s:select list="#session.txnList" name="eventID" id="eventID" cssClass="txtField" headerKey="" headerValue="Select Event ID"></s:select>
					 </td>
	</tr>
	
	<tr>
			<td width="20%" class="textRight"><s:label
						value="From Date"></s:label>:&nbsp;</td>
					<td width="30%" class="text">
					<sx:datetimepicker name="eventFromDate" cssClass="txtField"	displayFormat="MM/dd/yy" value="today"  />		
					
					</td>
					<td width="20%" class="textRight"><s:label
						value="To Date"></s:label>:&nbsp;</td>
					<td width="30%" class="text"><sx:datetimepicker name="eventToDate" cssClass="txtField"	displayFormat="MM/dd/yy" value="today" /></td>
					
	</tr>
	<tr>
			<td width="20%" class="textRight"><s:label
						value="From Time"></s:label>:&nbsp;</td>
					<td width="30%" class="text">
					<sx:datetimepicker type="time" id="formTime" name="formTime" cssClass="txtField"	displayFormat="HH:mm:ss" />
							
					</td>
					<td width="20%" class="textRight"><s:label
						value="To Time"></s:label>:&nbsp;</td>
					<td width="30%" class="text">
					<sx:datetimepicker type="time" id="toTime" name="toTime" cssClass="txtField" displayFormat="HH:mm:ss" />
					</td>
					
	</tr>
	
	
			</table></td></tr>
			<tr><td class="textCenter"><br />
			<s:submit action="eventSearch" value="search"></s:submit>
			<input type="reset" value="Cancel" onclick="cancel()">	
			</td></tr>
			<tr>
		<td><display:table uid="row" name="sessionScope.searchList" pagesize="10" requestURI="/eventSearch" cellpadding="3"
				cellspacing="0" class="dataGrid">
				<display:setProperty name="basic.msg.empty_list" 
    				value='<div class="infoMsg"><span>Nothing found to display</span></div>' />
				<display etProperty name="css.tr.even" value="TDBGlightgray" />
				<display etProperty name="css.tr.odd" value="TDBGgray" cssClass="odd"/>
				<display:column title="Event ID" property="eventId"
					headerClass="gridHdrBg"  sortable="false" />
				<display:column title="Branch" property="eventBranch" headerClass="gridHdrBg"  sortable="false" style="text-align:center" ></display:column>
					<display:column title="Department" property="eventDept" headerClass="gridHdrBg"  sortable="false" style="text-align:center" ></display:column>
						
				<display:column title="Date" property="eventDate"
					headerClass="gridHdrBg"  sortable="false" style="text-align:center"/>

				<display:column title="Time" property="eventTime"
					headerClass="gridHdrBg"  sortable="false" style="text-align:center" />
					
					
				<display:column title="Message Reference" property="eventMsgRef"
					headerClass="gridHdrBg"  sortable="false" style="text-align:center" />
				
					<display:column title="Txn Reference" property="eventTxnRef" headerClass="gridHdrBg"  sortable="false" style="text-left" ></display:column>
					<display:column title="Event Description" property="eventDescription"
					headerClass="gridHdrBg"  sortable="false" style="text-align:left" />
					<display:column>
					<input type="hidden" name="transactionReference"
						value="${row.eventMsgRef}" />
						
					<input type="button" class="btn" value="View"
						onclick="callRepairAction('${row.eventMsgRef}')" />
					
					</display:column>
					
			</display:table></td>
		</tr>
			</table>


</s:form>
</div>


</div>

</div>

</div>

<div id="contentPaneBottom"></div>
