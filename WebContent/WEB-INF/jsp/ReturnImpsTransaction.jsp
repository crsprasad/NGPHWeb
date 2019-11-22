<%@page import="com.opensymphony.xwork2.ActionContext"%>
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<%@taglib uri="http://displaytag.sf.net" prefix="display"%>

<s:head theme="simple" />
<sx:head parseContent="true" />
<script type="text/javascript">
function callFetchDetails()
	{
	document.forms[0].action = "getFetchTrasactionData";
	document.forms[0].submit();
	
	}

function cancel()
{
	
	document.forms[0].action ="displayReturnTransactionreturnImpsTrasaction";
	document.forms[0].submit();
}
function callSubmit(saveAction)
{
	var aa = document.getElementById('form');
	for ( var i = 0; i < aa.elements.length-3 ; i++) {
		
		aa.elements[i].disabled = false;
	}
	document.getElementById("saveAction").value = saveAction;
	document.forms[0].action="doPolledOperation";
	document.forms[0].submit();
}
function cancel()
{
	document.forms[0].action="genericScreenFetchValues";
	document.forms[0].submit();
}
</script>
<div id="contentPane">
<div id="contentPaneTop"></div>
<div id="contentPaneFluid">
<div id="contentPaneInternal"><span class="crumbs"><a
	href="#">OutBound</a>&nbsp;>&nbsp;Return IMPS Transaction</span></div>
<div id="content">
<h1>IMPS Transaction</h1>
<s:form method="post" action="returnImpsTrasaction" validate="true" id="form">
	<s:hidden id="payMessage" name="payMessage"></s:hidden>
	<div><s:label name="message"></s:label><s:if test="hasFieldErrors()">
				<div class="errorMsg"><span class="errorMsgInline"><s:actionerror /><s:fielderror /></span></div>
			</s:if></div><!--Error Msg Div Ends -->
	<table width="100%" cellpadding="3" cellspacing="1" border="0"
			>
		<tr>
			<td>
			
			<table width="100%" cellpadding="0" cellspacing="0" border="0">
		<tr>
			<td>
			<table width="95%" cellpadding="5" cellspacing="1" border="0"
				bgcolor="#c5d6f0" id="table1">
				<tr>
				<td width="20%" class="textRight"><s:label
						value="Date(MM/dd/yy)"></s:label>:&nbsp;</td>
				<td width="30%" class="text"><sx:datetimepicker name="impsDate"
						displayFormat="MM/dd/yy" cssClass="txtField" id ="impsDate" /></td>
						<td width="45%" class="text"><input type="button" value="Fetch..."
						onclick="callFetchDetails()" id ="btnSearch" class="btn" /></td>
				</tr>
				
	
	
	
	

			</table></td></tr></table>
			</td>
		</tr>
		<tr>
		<td><display:table uid="row" name="sessionScope.displayList" pagesize="10" requestURI="/getFetchTrasactionData" cellpadding="3"
				cellspacing="0" class="dataGrid">
				<display:setProperty name="basic.msg.empty_list" 
    				value='<div class="infoMsg"><span>Nothing found to display</span></div>' />
				<display etProperty name="css.tr.even" value="TDBGlightgray" />
				<display etProperty name="css.tr.odd" value="TDBGgray" cssClass="odd"/>
				<display:column title="Transaction Reference" property="transRef"
					headerClass="gridHdrBg"  sortable="false" />
					<display:column title="Benificiary Customer" property="benificiaryCustomer"
					headerClass="gridHdrBg"  sortable="false" style="text-align:center" />
				
				<display:column title="Ordering Customer" property="orderingCustomer"
					headerClass="gridHdrBg"  sortable="false" style="text-align:center"/>

				<display:column title="Narration" property="narration"
					headerClass="gridHdrBg"  sortable="false" style="text-align:center" />
					
					
				<display:column title="Amount" property="amount"
					headerClass="gridHdrBg"  sortable="false" style="text-align:center" />
				
					<display:column title="Returns"	headerClass="gridHdrBg"  sortable="false" style="text-align:center" >
					<s:checkbox  name="select[%{#attr.row_rowNum -1 }]"
					value="%{select[%{#attr.row_rowNum - 1 }]}" id="%{select[%{#attr.row_rowNum - 1 }]}"
					theme="simple" />
				<input type="hidden" name="msgRef" value="${row.msgRef}" />
			</display:column>
					
					
			</display:table></td>
		</tr>
		
		<tr>
			<td class="textCenter"><br />
			<s:if test="%{checkForSubmit!='Display_Approve_Reject'}">
			<input type="button" value="Submit"   onclick="callSubmit('Save')">
			<input type="reset" value="Cancel" onclick="cancel()" class="btn"  />
			</s:if>
			<s:if test="%{checkForSubmit=='Display_Approve_Reject'}">
				<s:if test="%{validUserToApprove}">
					<input type="button" value="Approve" onclick="callSubmit('Approve')">
					<input type="button" value="Reject" onclick="callSubmit('Reject')">
					<input type="button" value="Cancel" onclick="cancel()">
				</s:if>
				<s:if test="%{!validUserToApprove}">
					<input type="button" value="Approve" onclick="callSubmit('Approve')" disabled="disabled">
					<input type="button" value="Reject" onclick="callSubmit('Reject')" disabled="disabled">
					<input type="button" value="Cancel" onclick="cancel()" disabled="disabled">
				</s:if>
			</s:if>

		</tr>
	</table>
	<s:hidden name="valueDate" id="valueData"></s:hidden>
	<s:hidden id="saveAction" name="saveAction"></s:hidden>
	<s:hidden id="hiddenTxnRef" name="hiddenTxnRef"></s:hidden>
	
	<s:hidden name="hiddenList" id="hiddenList"></s:hidden>
</s:form>
<script type="text/javascript">
var hiddenList = document.getElementById("hiddenList").value;
var stringArray = hiddenList.split("~");
//alert(stringArray.length);
for(var i=0;i<stringArray.length;i++){
	//alert("for  "+stringArray[i].replace(/^\s+|\s+$/g,''))
	if(stringArray[i].replace(/^\s+|\s+$/g,'') == 'true'){
	//	alert("if")
var select= document.getElementsByName("select["+i+"]");
select[0].checked = true;
select[0].disabled = true;
//alert(select[0].checked +"     checked")
}
//	alert("out")	
		
}

var check= document.getElementById("hiddenTxnRef").value;
if(check!=''){
	var aa = document.getElementById('form');
	for ( var i = 0; i < aa.elements.length-7; i++) {
		
		aa.elements[i].disabled = true;
	}
}

</script>
</div>
</div>
</div>
<div id="contentPaneBottom"></div>
