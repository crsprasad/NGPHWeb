<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<%@taglib uri="http://displaytag.sf.net" prefix="display"%>
<link href="SpryAssets/SpryCollapsiblePanel.css" rel="stylesheet"
	type="text/css" />
<script src="SpryAssets/SpryCollapsiblePanel.js" type="text/javascript"></script>
<script type="text/javascript">


function callFetchTemplate()
{
		var msgType = document.getElementById("msgType");
		var msgTypeValue = msgType.options[msgType.selectedIndex].value;
		document.getElementById("msgValue").value = msgTypeValue;
		var tempName= document.getElementById("tempName").value;
		if(tempName==""){
			 window.open(
					"<s:url action='showTemplateSearch' windowState='EXCLUSIVE' />",
					'mywindow', 'top=50,left=250,width=750,height=410');
				
		}
	}
	
	
function setDropDownVal()
{
	var msgType = document.getElementById("msgType");
	var msgTypeValue = msgType.options[msgType.selectedIndex].value;
	var tempName = document.getElementById("tempName").value;
	if(msgType.selectedIndex!=0)
		{	
			if(tempName!='')
				{	
					msgType= document.getElementById("msgType").value;
					msgTypeValue = document.getElementById("msgType").value;
				 	tempName = document.getElementById("tempName").value;
				 	document.forms[0].action = "displayTempalteData";
					document.forms[0].submit();
				}
			else{
				msgType= document.getElementById("msgType").value;
				msgTypeValue = document.getElementById("msgType").value;
			 	document.forms[0].action = "displayTempalteData";
				document.forms[0].submit();
				}
		}
	else 
		{
			alert("Please select Message Type!");
		}
	
}

function assignPopup(tempRef,actionScreen) {

	document.getElementById("tempRef").value=tempRef;

	if(actionScreen=='MMID'){
	document.forms[0].action = "genericScreenpopulateData";
	document.forms[0].submit();
	}
	else if(actionScreen=='Rules'){
		document.forms[0].action = "getRuleAuthorization";
		document.forms[0].submit();
		}
	else if(actionScreen=='Role Maintenance'){
		document.forms[0].action = "getRoleCreatorAuthorization";
		document.forms[0].submit();
		}
	else if(actionScreen=='Group Maintenance'){
		document.forms[0].action = "getGroupMaintenanceAuthorization";
		document.forms[0].submit();
		}
	else if(actionScreen=='Authorization Schema'){
		document.forms[0].action = "getAuthorizationSchemaAuthorization";
		document.forms[0].submit();
		}
	else if(actionScreen=='IMPS Returns'){
		document.forms[0].action = "getIMPSReturnsAuthorization";
		document.forms[0].submit();
		}
	else if(actionScreen=='XML Creator'){
		document.forms[0].action = "getESBCreatorAuthorization";
		document.forms[0].submit();
		}
	else if(actionScreen=='Maintaining Orchestration'){
		document.forms[0].action = "getMaintainingOrchestrationAuthorization";
		document.forms[0].submit();
		}
	else if(actionScreen=='User Maintenance'){
		document.forms[0].action = "getUserMaintenanceAuthorization";
		document.forms[0].submit();
		}
	else if(actionScreen=='Event Master'){
		document.forms[0].action = "getEventMasterAuthorization";
		document.forms[0].submit();
		}
	else if(actionScreen=='Pre Advise'){
		document.forms[0].action = "getLetterOFCreditAuthorization";
		document.forms[0].submit();
		}
	else if(actionScreen=='Lc Open(MT-700)'){
		document.forms[0].action = "getLetterOFCreditAuthorizationLcOpen";
		document.forms[0].submit();
		}
	else if(actionScreen=='Create Bank Guarantee(MT-760)'){
		document.forms[0].action = "getBGAuthorization";
		document.forms[0].submit();
		}
	else if(actionScreen=='Mark Acknowlegment'){
		document.forms[0].action = "displayAuthorizationDetails";
		document.forms[0].submit();
		}
	else if(actionScreen=='Advice Lc Payment(MT-756)'){
		document.forms[0].action = "getAdviceLCPaymentAuthorization";
		document.forms[0].submit();
		}
	else if(actionScreen=='Authorisation to Reimburse(MT-740)'){
		document.forms[0].action = "getAuthoLCPaymentAdviceAuthorization";
		document.forms[0].submit();
		}
	else if(actionScreen=='Amend LC(MT-707)'){
		document.forms[0].action = "getAmendLCAuthorization";
		document.forms[0].submit();
		}
	else if(actionScreen=='Maintain Branches'){
		document.forms[0].action = "getMaintainBranchAuthorization";
		document.forms[0].submit();
		}

	else if(actionScreen=='User Maintenance'){
		document.forms[0].action = "getUserMaintenanceAuthorization";
		document.forms[0].submit();
		}

	else if(actionScreen=='Member Transaction Limit'){
		document.forms[0].action = "callsentToApprove";
		document.forms[0].submit();
		}
	else if(actionScreen=='Transfer Documentary Credit'){
		document.forms[0].action = "getTransferDocumentaryCreditAuthorized";
		document.forms[0].submit();
		}
	else if(actionScreen=='Reimbursement Claim'){
		
		document.forms[0].action = "getReimbursementClaimAuthorized";
		document.forms[0].submit();
		}

	else if(actionScreen=='Advice Of Discharge'){
		
		document.forms[0].action = "getAdviceOfDischargeAuthorized";
		document.forms[0].submit();
		}

	else if(actionScreen=='Authorisation To Pay'){
		document.forms[0].action = "getAuthorisationToPayAuthorization";
		document.forms[0].submit();
		}
	else if(actionScreen=='Maintain IFSC'){
		document.forms[0].action = "getIFSCDateSentForApproval";
		document.forms[0].submit();
		}
	else if(actionScreen=='Amend BG(MT-767)'){
		document.forms[0].action = "getAmendBGAuthorization";
		document.forms[0].submit();
		}
	else if(actionScreen=='Acknowledge BG(MT-768)'){
		document.forms[0].action = "getAckBGAuthorization";
		document.forms[0].submit();
		}
	else if(actionScreen=='Create Bank Guarantee Cover(MT-760COVER)'){
		document.forms[0].action = "getBGCoverAuthorization";
		document.forms[0].submit();
		}
	else if(actionScreen=='Amend BG Cover(MT-767COVER)'){
		document.forms[0].action = "getAmdCoverBGAuthorization";
		document.forms[0].submit();
		}
	else if(actionScreen=='Advice of Reduction(MT-769)'){
		document.forms[0].action = "getReductionAuthorization";
		document.forms[0].submit();
		}
	else if(actionScreen=='LC Acknowledgement(MT-730)'){
		document.forms[0].action = "getLCAcknowledgementAuthorization";
		document.forms[0].submit();
		}
	else if(actionScreen=='Free Format Payment(MT-799)'){
		document.forms[0].action = "getFreeFormatAuthorization";
		document.forms[0].submit();
		}
	else if(actionScreen=='Advice of Refusal(MT-734)'){
		document.forms[0].action = "getLCAdviseofRefusalAuthorization";
		document.forms[0].submit();
		}
	else if(actionScreen=='Advice of Discrepancy(MT-750)'){
		document.forms[0].action = "getLCAdviseofDiscrepancyAuthorization";
		document.forms[0].submit();
		}
	else if(actionScreen=='Advice of Payment(MT-754)'){
		document.forms[0].action = "getLCAdviseofPaymentAuthorization";
		document.forms[0].submit();
		}

	}
</script>
<sx:head parseContent="true" />
<s:head />
<div id="contentPane">
<div id="contentPaneTop"></div>
<div id="contentPaneFluid">
<div id="contentPaneInternal"><span class="crumbs"><a
	href="#">Enquiries & Reports</a>&nbsp;>&nbsp;Template Search</span></div>
<div id="content">
<h1>Template Search</h1>
<s:form method="post" id="form" name="template">
<s:hidden id="searchAction" name="searchAction"></s:hidden>	
<s:hidden id="msgValue" name="msgValue"></s:hidden>	
	<!--Error Msg Div Starts -->
	<div><s:if test="hasFieldErrors()">
				<div class="errorMsg"><span class="errorMsgInline"><s:actionerror /><s:fielderror /></span></div>
			</s:if></div><!--Error Msg Div Ends -->
	<!-- Collapsible Panels Start-->
	<div id="CollapsiblePanel1" class="CollapsiblePanel">
	<div class="CollapsiblePanelTab"><!--  <img src="images/openPanel.png" width="9" height="7" alt="openPanel" />-->&nbsp;Search Template</div>
	<div class="CollapsiblePanelContent">
	<div><!-- Group One Div Starts --> 
	<s:div id="main"	cssClass="dataGrid" style="width:100%;">
	<table width="100%" cellpadding="3" cellspacing="1" border="0"	bgcolor="#ffffff">
	<tr>
		<td class="textRight"><s:label value="%{getText('label.ruleMsgtype')}"></s:label>:<span class="mandatory">*</span></td>
		<td class="text"><s:select label="msgType" list="#session.msgTypeDropDown" cssClass="txtField" name="msgType" id="msgType" headerKey="0" headerValue="Select Message Type" key="label.ruleMsgtype" required="true"></s:select></td>
		<td class="textRight"><s:label	value="%{getText('label.TemplateName')}"></s:label>:&nbsp;</td>
		<td class="text"><s:textfield name="tempName" id="tempName" onKeyPress='return notAllowCheck(this,event);' ></s:textfield><input type="button" value="Fetch" onclick="callFetchTemplate()"> </td>
	</tr>	
		</table>		
		
	<!--  button panel starts -->
	<div class="clearfix"></div>
	<table width="100%" cellpadding="0" cellspacing="0" border="0">
		<tr>
			<td align="center">
			
				
				<input type="button" value="Submit" onclick="setDropDownVal()" class="btn">
				<input type="reset" value="Cancel" onclick="cancel()" class="btn"  />				
				
			</td>
		</tr>
	</table>
		
		<table width="100%" cellpadding="0" cellspacing="0" border="0"
			bgcolor="#ffffff" align="center">

			<tr>
				<td><br/>
				
				<div style="overflow: auto; height: 245px; width: 100%;border:0px solid #ff0000;">
					<display:table uid="row" name="sessionScope.tempScreenValue" pagesize="10" requestURI="/displayTempalteData" cellpadding="3"
				cellspacing="0" class="dataGrid">
				<display:setProperty name="basic.msg.empty_list" 
    				value='<div class="infoMsg"><span>Nothing found to display</span></div>' />
				<display etProperty name="css.tr.even" value="TDBGlightgray" />
				<display etProperty name="css.tr.odd" value="TDBGgray" cssClass="odd"/>

					<display:column title="Template Ref" headerClass="gridHdrBg"  style="text-align:center">
						<display:setProperty name="basic.msg.empty_list" value='<div class="searchMsg"><span>Nothing found to display</span></div>' />
	    				<a href="javascript:assignPopup('${row.tempRef}','${row.tempId}')">
			 				<s:set name="tempRef">${row.tempRef}</s:set>
			 				<s:property value="%{tempRef}"/>
			 			</a>
					</display:column>				
				
				<display:column	 title="Template ID" property="tempId"  headerClass="gridHdrBg"  sortable="false" style="text-align:center"/>
				<display:column	 title="Template Name" property="tempName" headerClass="gridHdrBg"  sortable="false" style="text-align:center"/>
				<display:column title="User" property="crtdUserId" headerClass="gridHdrBg" style="text-align:center" ></display:column>
				<display:column title="Created Date & Time" property="crtDate" headerClass="gridHdrBg" style="text-align:center" ></display:column>
			</display:table>
				
				
				
				</div></td>
			</tr>
		</table>
		</td>
		</tr>
	</table>
	<s:hidden id="saveAction" name="saveAction"></s:hidden>
	<s:hidden id="hiddenTxnRef" name="hiddenTxnRef"></s:hidden>
	<s:hidden name="tempRef" id="tempRef"></s:hidden>
	<s:hidden name="tempId" id="tempId"></s:hidden>
	</s:div> <!-- Group One Div Ends --></div>
	</div>
	</div>
	<br />
	</s:form></div>
</div>
</div>
<div id="contentPaneBottom"></div>
<script type="text/javascript">
	var CollapsiblePanel1 = new Spry.Widget.CollapsiblePanel(
			"CollapsiblePanel1");	
</script>
