<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="com.logica.ngph.web.utils.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="sd" uri="/struts-dojo-tags"%>
<%@taglib uri="http://displaytag.sf.net" prefix="display"%>
<script type="text/javascript">
	function prnTbl(strid) {

		var printContent = document.getElementById(strid);
		var windowUrl = 'about:blank';
		var uniqueName = new Date();
		var windowName = 'Print' + uniqueName.getTime();

		var printWindow = window.open(windowUrl, windowName,
				'left=50000,top=50000,width=800,height=600');
		printWindow.document.body.innerHTML = printContent.innerHTML;
		printWindow.document.close();
		printWindow.focus();
		printWindow.print();
		printWindow.close();
		return false;

	}
	
</script>
<div id="contentPane">
<div id="contentPaneTop"></div>
<div id="contentPaneFluid">
<div id="contentPaneInternal"><span class="crumbs"><a
	href="#">System</a>&nbsp;>&nbsp;Event Audit&nbsp;>&nbsp;View Details</span></div>
<div id="content">
<h1>View Details</h1>
<s:form method="post">
	
	<table width="100%" cellpadding="0" cellspacing="0" border="0">
		<tr>
			<td>
			<table width="95%" cellpadding="5" cellspacing="1" border="0"
				bgcolor="#c5d6f0" id="table1">
				<tr>
					<td width="20%" class="textRight"><s:label value="Branch"></s:label>&nbsp;</td>
					<td width="30%" class="text"><s:textfield name="eventBranch" id="eventBranch" key="Msg Type" cssClass="txtField" disabled="true"></s:textfield></td>
					<td width="20%" class="textRight"><s:label value="Departmant"></s:label>&nbsp;</td>
					<td width="30%" class="text"><s:textfield name="eventDepartment"	id="eventDepartment" key="sender Bank" cssClass="txtField"
						disabled="true"></s:textfield></td>
				</tr>
				<tr>

					<td class="textRight"><s:label value="Txn Reference"></s:label>:&nbsp;</td>
					<td class="text"><s:textfield name="eventTxnRef"	id="eventTxnRef" key="channel" cssClass="txtField" disabled="true"></s:textfield></td>
					<td class="textRight"><s:label value="Date"></s:label>:&nbsp;</td>
					<td class="text"><s:textfield name="eventFromDate" id="eventFromDate" key="Receiver Bank" cssClass="txtField"
						disabled="true"></s:textfield></td>
				</tr>
				<tr>

					<td class="textRight"><s:label value="Time"></s:label>:&nbsp;</td>
					<td class="text"><s:textfield name="formTime"
						id="formTime" key="msgDirection" cssClass="txtField"
						disabled="true"></s:textfield></td>
					<td class="textRight"><s:label value="Message Reference"></s:label>:&nbsp;</td>
					<td class="text"><s:textfield name="eventMsgRef" id="eventMsgRef"
						key="msg Amount" cssClass="txtField" disabled="true"></s:textfield></td>
				</tr>
				<tr>

					<td class="textRight"><s:label value="Event Description"></s:label>:&nbsp;</td>
					<td colspan="3" class="text"><s:textfield name="eventDesc"
						id="eventDesc" key=" TRan Reference" cssClass="txtField"
						disabled="true"></s:textfield></td>
					
				</tr>
				
				


			</table>
			</td>
		</tr>
		<tr>
			<td class="textCenter"><br />
			<input type="button" value="Print" onclick="prnTbl('content')"/> &nbsp;&nbsp;
			<input type="button" value="Cancel" onclick="javascript: history.go(-1)"/> 
			

		</tr>
	</table>
</s:form></div>
</div>
</div>
<div id="contentPaneBottom"></div>
