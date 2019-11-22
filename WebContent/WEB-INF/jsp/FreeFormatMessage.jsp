<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="com.logica.ngph.web.action.*,com.logica.ngph.web.utils.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<%@taglib uri="http://displaytag.sf.net" prefix="display"%>
<link href="SpryAssets/SpryCollapsiblePanel.css" rel="stylesheet"
	type="text/css" />
<script src="SpryAssets/SpryCollapsiblePanel.js" type="text/javascript"></script>
<script type="text/javascript">
function callSubmit(saveAction){
	
	var aa = document.getElementById('form');
	for ( var i = 0; i < aa.length; i++) {
		aa.elements[i].disabled = false;
	}
	
	document.getElementById("saveAction").value = saveAction;
	document.forms[0].action="saveFreeFormatAction";
	document.forms[0].submit();
}

function callSearchIFSC(Action,flag) {
	document.getElementById("searchAction").value = Action;
	document.getElementById("IFSCFLAG").value = flag;
	window.open(
			"<s:url action='showIFSCScreen' windowState='EXCLUSIVE' />",
			'mywindow', 'top=50,left=250,width=750,height=410');
}
function callShowGrid()
{
	document.forms[0].action="displayAmendBGData";
	document.forms[0].submit();
}
 

function SaveTemplate()
{
	var isConfirm = confirm("Need to save as Template!");
	if(isConfirm)
		{
			var tempName = prompt("Please enter your Template name");
			if(tempName!=null)
				{
					alert("Template Name is "+tempName);
					document.getElementById("tempName").value = tempName;
					document.forms[0].action="saveFreeFormatTemplate";
					document.forms[0].submit();
				}
		} 
	
}

function callPrintPreview(){
	document.forms[0].action="printPreviewFreeFormatPage";
	document.forms[0].submit();
}

function cancel(){
	var isConfirm = confirm("Please confirm");
	if(isConfirm)
		{
			document.forms[0].action="resetFreeFormat";
			document.forms[0].submit();
		}
}

function generatePdf()
{
	document.forms[0].action="generatePDFFreeFormat";
	document.forms[0].submit();
	}
</script>
<sx:head parseContent="true" />
<s:head />
<div id="contentPane">
<div id="contentPaneTop"></div>
<div id="contentPaneFluid">
<div id="contentPaneInternal"><span class="crumbs"><a
	href="#">Outbound Payments</a>&nbsp;>&nbsp;Free Format Payment</span></div>
<div id="content">
<h1>Free Format Payment (MT-799)</h1>
<s:form method="post" id="form">
<s:hidden id="searchAction" name="searchAction"></s:hidden>	
<s:hidden name="IFSCFLAG" id="IFSCFLAG" ></s:hidden>
<s:hidden id="msgRef" name="msgRef"></s:hidden>
<s:hidden name="repair" id="repair"></s:hidden>
<s:hidden id="saveAction" name="saveAction"></s:hidden>
<s:hidden id="msgHost" name="msgHost"> </s:hidden>
<s:hidden id="validUserToApprove" name="validUserToApprove"></s:hidden>
<s:hidden name="tempName" id="tempName"></s:hidden>
<s:hidden id="tempRef" name="tempRef"></s:hidden>
	<!--Error Msg Div Starts -->
	<div><s:if test="hasFieldErrors()">
				<div class="errorMsg"><span class="errorMsgInline"><s:actionerror /><s:fielderror /></span></div>
			</s:if></div><!--Error Msg Div Ends -->
	<!-- Collapsible Panels Start-->
	<div id="CollapsiblePanel1" class="CollapsiblePanel">
	<div class="CollapsiblePanelTab"><!--  <img src="images/openPanel.png" width="9" height="7" alt="openPanel" />-->&nbsp;Basic Details</div>
	<div class="CollapsiblePanelContent">
	<div><!-- Group One Div Starts --> 
	<s:div id="main"	cssClass="dataGrid" style="width:100%;">
	<table width="100%" cellpadding="3" cellspacing="1" border="0"	bgcolor="#ffffff">
	<s:if test="%{repair!=''}">
	 
	    <tr>
		       <td width="20%" class="textLeft">
		          <s:label	value="%{getText('label.MB.IssuingBranchIFSC')}"></s:label> <span class="optional">[Optional]</span>
		       </td>
		       <td width="30%" class="text">
		         <s:textfield name="issuingBankCode" id="issuingBankCode"></s:textfield> 
		         <input type="button" value="Search IFSC..." onclick="return callSearchIFSC('ISSUEBANKTHOUGHIFSC','SENDERBANKIFSC')" id="btnadviceThrough"class="btn" />
		       </td>
		   
		       <td width="20%" class="textLeft">
		         <s:label cssClass="MandatoryField" value="%{getText('label.MB.Benefifsc')}"></s:label> <span class="MandatoryField">[Mandatory]</span>
		       </td>
		       <td width="30%" class="text">
		          <s:textfield name="advisingBank" id="advisingBank"></s:textfield> 
		          <input type="button" value="Search IFSC..." onclick="return callSearchIFSC('ADVISINGIFSC','BOTH')" id="btnadviceThrough"class="btn" />
		        </td>
		 </tr>
	
    </s:if>	
    <s:if test="%{repair==''}">
  
      <tr>
		   	  <td width="20%" class="textLeft">
		          <s:label	value="%{getText('label.MB.IssuingBranchIFSC')}"></s:label> <span class="optional">[Optional]</span>
		       </td>
		       <td width="30%" class="text">
		         <s:textfield name="issuingBankCode" id="issuingBankCode"></s:textfield> 
		         <input type="button" value="Search IFSC..." onclick="return callSearchIFSC('ISSUEBANKTHOUGHIFSC','SENDERBANKIFSC')" id="btnadviceThrough"class="btn" />
		       </td>
		   
		       <td width="20%" class="textLeft">
		         <s:label cssClass="MandatoryField" value="%{getText('label.MB.Benefifsc')}"></s:label> <span class="MandatoryField">[Mandatory]</span>
		       </td>
		       <td width="30%" class="text">
		          <s:textfield name="advisingBank" id="advisingBank"></s:textfield> 
		          <input type="button" value="Search IFSC..." onclick="return callSearchIFSC('ADVISINGIFSC','BOTH')" id="btnadviceThrough"class="btn" />
		        </td>
		</tr>
		    
	  </s:if>
    </table>
	</s:div><!-- Group One Div Ends --></div>
	</div>
	</div>
		<br />
 
	<div id="CollapsiblePanel2" class="CollapsiblePanel">
	<div class="CollapsiblePanelTab"><!--  <img src="images/openPanel.png" width="9" height="7" alt="openPanel" />-->&nbsp;Message Details</div>
	<div class="CollapsiblePanelContent">
	<div><!-- Group Two Div Starts --> 
	<s:div id="main"	cssClass="dataGrid" style="width:100%;">
	<table width="100%" cellpadding="3" cellspacing="1" border="0" bgcolor="#ffffff">
    <s:if test="%{repair!=''}">
	
			<tr>
			   <td width="100%" class="textLeft">
			      <s:label cssClass="MandatoryField" value="%{getText('label.799.transactionReferenceNumber')}"></s:label>  <span class="MandatoryField">[Mandatory]</span>
			   </td>
			</tr>
			<tr>
			   <td width="100%" class="text">
			      <s:textfield name="bgNumber" id="bgNumber" maxLength="16"></s:textfield>
			   </td>
			</tr>
			<tr>
			   <td width="100%" class="textLeft">
			     <s:label value="%{getText('label.799.relatedReferenceNumber')}"></s:label> <span	class="optional">[Optional]</span>
			   </td>
			</tr>
			<tr>
			   <td width="100%" class="text">
			     <s:textfield name="relatedReference" id="relatedReference" maxLength="16"></s:textfield>
			   </td>
			</tr>
			<tr>
			   <td width="100%" class="textLeft">
			     <s:label cssClass="MandatoryField" value="%{getText('label.799.Narrative')}"></s:label> <span class="MandatoryField">[Mandatory]</span>
			   </td>
			</tr>
		    <tr>
		       <td width="100%" class="text">
		          <s:textarea name="narrative" id="narrative" cols="50" rows="35" style="overflow: scroll; resize: none;" wrap="HARD" onKeyPress='return maxLength(this,"209","Narrative","35",event);'></s:textarea>
		       </td>
		    </tr>
		    
	
	</s:if>	
    <s:if test="%{repair==''}">
	
			<tr>
			   <td width="100%" class="textLeft">
			      <s:label cssClass="MandatoryField" value="%{getText('label.799.transactionReferenceNumber')}"></s:label>  <span class="MandatoryField">[Mandatory]</span>
			   </td>
			</tr>
			<tr>
			   <td width="100%" class="text">
			      <s:textfield name="bgNumber" id="bgNumber" maxLength="16"></s:textfield>
			   </td>
			</tr>
			<tr>
			   <td width="100%" class="textLeft">
			     <s:label value="%{getText('label.799.relatedReferenceNumber')}"></s:label> <span	class="optional">[Optional]</span>
			   </td>
			</tr>
			<tr>
			   <td width="100%" class="text">
			      <s:textfield name="relatedReference" id="relatedReference" maxLength="16"></s:textfield>
			   </td>
			</tr>
			<tr>
			   <td width="100%" class="textLeft">
			     <s:label cssClass="MandatoryField" value="%{getText('label.799.Narrative')}"></s:label> <span class="MandatoryField">[Mandatory]</span>
			   </td>
			</tr>
		    <tr>
		       <td width="100%" class="text">
		          <s:textarea name="narrative" id="narrative" cols="50" rows="35" style="overflow: scroll; resize: none;" wrap="HARD" onKeyPress='return maxLength(this,"209","Narrative","35",event);'></s:textarea>
		       </td>
		    </tr>
		    
		   	
													
     </s:if>	
	
	
		</table>		
		</s:div> <!-- Group Two Div Ends --></div>
	</div>
	</div>
	<br />
		
	<!--  button panel starts -->
	<div class="clearfix"></div>
	<table width="100%" cellpadding="0" cellspacing="0" border="0">
		<tr>
			<td>
			<s:if test="%{checkForSubmit!='Display_Approve_Reject'}">
					<s:submit value="Submit" action="getFreeFormatApproval" cssClass="btn" />
					<input type="reset" value="Reset" onclick="cancel()" class="btn"  />
					<input type="button" value="Save Template" onclick="SaveTemplate()" class="btn"  />
					<!--<input type="button" value="Print" onclick="generatePdf();">
			--></s:if>
			<s:if test="%{checkForSubmit=='Display_Approve_Reject'}">
				<s:if test="%{validUserToApprove}">
					<input type="button" value="Approve" onclick="callSubmit('Approve')">
					<input type="button" value="Reject" onclick="callSubmit('Reject')">
					<input type="button" value="Cancel" onclick="cancel()">
					<input type="button" value="Print Preview" onclick="callPrintPreview()">
				</s:if>
				<s:if test="%{!validUserToApprove}">
					<s:submit value="Submit" action="getFreeFormatApproval" cssClass="btn" />
					<input type="reset" value="Cancel" onclick="cancel()" class="btn"  />
					<input type="button" value="Save Template" onclick="SaveTemplate()" class="btn"  />
				</s:if>
			</s:if>
					
				
			</td>
		</tr>
	</table>
	<s:hidden id="hiddenTxnRef" name="hiddenTxnRef"></s:hidden>
	
	
	</s:form></div>
</div>
</div>
<div id="contentPaneBottom"></div>
<script type="text/javascript">
	var CollapsiblePanel1 = new Spry.Widget.CollapsiblePanel(
			"CollapsiblePanel1");	
</script>
<script type="text/javascript">
var check= document.getElementById("hiddenTxnRef").value;
if(check!=''){
	var aa = document.getElementById('form');
	var isValidUser = document.getElementById("validUserToApprove").value;
		if(isValidUser=="true")
		{
			for ( var i = 0; i < aa.length-5; i++) 
				{
					aa.elements[i].disabled = true;
				}
			
		}
		else
		{
			for ( var i = 0; i < aa.length-5; i++) 
			{
				aa.elements[i].enabled = true;
			}
		}
}

</script>