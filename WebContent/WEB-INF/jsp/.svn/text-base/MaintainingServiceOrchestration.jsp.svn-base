<%@ page language="java" contentType="text/html; charset=ISO-8859-1" import="com.logica.ngph.web.action.*"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>



<style type="text/css">
/* CSS for the demo. CSS needed for the scripts are loaded dynamically by the scripts */
#mainContainer {
	width: 600px;
	margin: 0 auto;
	margin-top: 10px;
	border: 1px solid #000;
	padding: 2px;
}

#capitals {
	width: 200px;
	float: left;
	border: 1px solid #000;
	background-color: #696969;
	padding: 3px;
	height: 400px;
}

#countries {
	width: 300px;
	float: right;
	margin: 2px;
	height: 400px;
}
div.img{
 background-image:url("../images/arrow.GIF");  
width: 10px;
	height: 10px;
	float: center;
	margin-right: 5px;
	padding: 5px;
}
.dragableBox,.dragableBoxRight {
	width: 60px;
	height: 20px;
	}

div.dragableBoxRight {
	height: 40px;
	width: 150px;
	float: left;
	margin-right: 5px;
	padding: 5px;
}
</style>
<script type="text/javascript" src="js/drag-drop-custom.js"></script>
<script type="text/javascript">

var dynamic_array = new Array()

function callGetValuesForTextBox(saveAction) {
	document.getElementById("saveAction").value = saveAction;
		var targetObj = document.getElementById('countries'); // Creating reference to target obj
		
		var concatinatestring='';
		var subDivs = targetObj.getElementsByTagName('input');

		for(var i=0;i<subDivs.length;i++){
			
			if(subDivs[i].value!=''){
		concatinatestring = concatinatestring + ','+subDivs[i].value;
			}
		
		}
		document.getElementById("sequenceOfString").value = concatinatestring;
		
		callAction();
		

	}
	function callAction()
	{
		document.getElementById("saveAction").value = 'Save';
		document.forms[0].action = "doProcessForOrchestration";
		document.forms[0].submit();
	}
	function callSearch(Action) {

		
		window
				.open(
						"<s:url action='showStreamIDSearchScreen' windowState='EXCLUSIVE' />",
						'mywindow', 'top=50,left=250,width=760,height=410');

	}


	function callText(value,position)
	
	{

		var length = document.getElementById("serviceIDLenght").value;
		
		for(var i=0;i<length;i++){
			
			if(document.getElementById("text"+i).value==''){
			document.getElementById("text"+i).value=value;
			document.getElementById("text"+i).style.visibility = 'visible';
			dynamic_array.push(position);
			
			break;
			}
		
			
		}
		
		document.getElementById("box"+position).style.visibility = 'hidden';
		
	}
	function callDelete()
	{
		
		var length = document.getElementById("serviceIDLenght").value;
		
		for(var i=length-1;i>0;i--){

				if(document.getElementById("text"+i).value==''){
					
				}
				else{
					document.getElementById("text"+i).value='';
					document.getElementById("text"+i).style.visibility = 'hidden';
					
					break;
					}
			
			}
	
		for(var k=dynamic_array.length-1;k>0;k--)
			{
			
			document.getElementById("box"+dynamic_array[k]).style.visibility = 'visible';
			dynamic_array.pop();
			break;
			
			}
	}
	function callSubmit(saveAction) {
		var aa = document.getElementById('form1');
		for ( var i = 0; i < aa.elements.length-7; i++) {
			
			aa.elements[i].disabled = false;
		}
		document.getElementById("saveAction").value = saveAction;
		document.forms[0].action = "doProcessForOrchestration";
		document.forms[0].submit();
	}
	function cancel() {
		document.forms[0].action = "genericScreenFetchValues";
		document.forms[0].submit();
	}
</script>

<sx:head parseContent="true" />
<s:head />
<div id="contentPane">
<div id="contentPaneTop"></div>
<div id="contentPaneFluid">
<div id="contentPaneInternal"><span class="crumbs"><a
	href="#">Maintenance</a>&nbsp;>&nbsp;Maintain Service Orchestration </span></div>
<div id="content">
<h1>Maintain Service Orchestration</h1>
<form action="maintainingServiceOrchestration" id="form1" method="post" >

<div><s:label name="message"></s:label><s:if test="hasFieldErrors()">
				<div class="errorMsg"><span class="errorMsgInline"><s:actionerror /><s:fielderror /></span></div>
			</s:if></div>
<!--Error Msg Div Ends -->

<table width="70%" border="0" cellpadding="5" cellspacing="1"
	class="gridHdrBg" align="center">
	<tr>
		<td class="textRight"><s:label key="label.StreamID"></s:label>:&nbsp;</td>
		<td class="text"><s:textfield name="streamID" id="streamID"
			cssClass="text" /> <input type="button" class="btn" value="Search"
			onclick="callSearch('search')" /></td>
		<td class="textRight"><s:label key="label.MessageType"></s:label>:&nbsp;</td>
		<td class="text"><s:select list="#session.msgTypeDropDown"
			cssClass="txtField" name="msgType" id="msgType" headerKey=""
			headerValue="Select Message Type" required="true"></s:select></td>
	</tr>
	<tr>
		<td class="textRight"><s:label key="label.subMessage"></s:label>:&nbsp;</td>
		<td class="text"><s:select list="#session.subMsgTypeDropDown"
			cssClass="txtField" name="subMsgType" id="subMsgType"
			headerKey="" headerValue="Select Sub Message Type" required="true"></s:select></td>
		<td class="textRight"><s:label key="label.Direction"></s:label>:&nbsp;</td>
		<td class="text"><s:radio name="direction" list="#{'I':'I','O':'O'}"
			value="direction" key="label.Direction" required="true"/></td>
	</tr>

</table>

<div class="textRight" id="mainContainer" align="center" style="width: 80%; border: 2px; border-color: #006699;">

<div id="capitals" class="gridHdrBg">
<p><b>Service Id</b></p>
<div id="dropContent">
<%
	for (int i = 0; i < MaintainingServiceOrchestrationAction.serviceIDLength; i++) {
%>
<input id="box<%=i%>" type="button" readonly="readonly" width="20"
	style="width: 110px; height: 30px" onclick="callText('<%=MaintainingServiceOrchestrationAction.serviceIDToBeDragged[i]%>','<%=i%>')"
	value="<%=MaintainingServiceOrchestrationAction.serviceIDToBeDragged[i]%>">
<%
	}
%>
</div>
</div>
<div id="countries" align="center"
	style="width: 80%; height: 20%; border: 2mm;">
<%
	for (int i = 0; i < MaintainingServiceOrchestrationAction.serviceIDLength; i++) {
		
%>
<input type="text" id="imageBox<%=(i)%>" name = "text<%=(i)%>" class="dragableBoxRight" style="visibility: hidden; width: 150px;height: 40px;" />


<%
	}
%>
</div>

</div>
<input type="hidden" name="serviceIDLenght" id="serviceIDLenght"
	value="<%=MaintainingServiceOrchestrationAction.serviceIDLength%>" /> 
	<s:if test="%{checkForSubmit!='Display_Approve_Reject'}">
	<input type="button" name="Submit" Value="Submit" onclick="callGetValuesForTextBox('Save')"> 
	<input type ="button" value ="Delete" onclick="callDelete()"></s:if>
	<s:if test="%{checkForSubmit=='Display_Approve_Reject'}">
		<s:if test="%{validUserToApprove}">
			<input type="button" value="Approve" onclick="callSubmit('Approve')">
			<input type="button" value="Reject" onclick="callSubmit('Reject')">
			
		</s:if>
		<s:if test="%{!validUserToApprove}">
			<input type="button" value="Approve" onclick="callSubmit('Approve')" disabled="disabled">
			<input type="button" value="Reject" onclick="callSubmit('Reject')" disabled="disabled">
			
		</s:if>
		<input type="button" value="Cancel" onclick="cancel()">
	</s:if>
	<s:hidden name="sequenceOfString" id="sequenceOfString"></s:hidden>
	<s:hidden id="saveAction" name="saveAction"></s:hidden>
	<s:hidden id="hiddenTxnRef" name="hiddenTxnRef"></s:hidden>
	
	</form>
<script type="text/javascript">
var check = document.getElementById("hiddenTxnRef").value;

if(check!=''){
var sequence=document.getElementById("sequenceOfString").value;

var split = sequence.split(",");
for(var i =0;i<split.length;i++)
	{
	document.getElementById("text"+i).value=split[i];
	document.getElementById("text"+i).style.visibility = 'visible';
	}
var aa = document.getElementById('form1');
for ( var i = 0; i < aa.elements.length-7; i++) {
	//alert(aa.elements[i].value)
	aa.elements[i].disabled = true;
}
}
</script> 
</div>




</div>

</div>

<div id="contentPaneBottom"></div>
