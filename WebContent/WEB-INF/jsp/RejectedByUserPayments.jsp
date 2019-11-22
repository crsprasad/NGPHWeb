<%@page import="com.opensymphony.xwork2.ActionContext"%>
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<%@taglib uri="http://displaytag.sf.net" prefix="display"%>

<s:head theme="simple" />

<script type="text/javascript">


function assignPopup(txnRef,actionScreen,createdUserID) {

document.getElementById("txnRef").value=txnRef;

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
	else if(actionScreen=='Customer Maintenance'){
		document.forms[0].action = "getCustomerDetailsAuthorization";
		document.forms[0].submit();
		}
	else if(actionScreen=='Password Security Policy'){
		document.forms[0].action = "getPassPolicyPolicysAuthorization";
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
	href="#">Outbound Payments</a>&nbsp;>&nbsp;Rejected By User</span></div>
<div id="content">
<s:form method="post" id="form"></br></br></br>
<s:hidden name="txnRef" id="txnRef"></s:hidden>
<display:table uid="row" name="screenValue" pagesize="10" requestURI="/rejectedByUserOutboundAction" cellpadding="3"
				cellspacing="0" class="dataGrid">
				<display:setProperty name="basic.msg.empty_list" 
    				value='<div class="infoMsg"><span>Nothing found to display</span></div>' />
				<display etProperty name="css.tr.even" value="TDBGlightgray" />
				<display etProperty name="css.tr.odd" value="TDBGgray" cssClass="odd"/>

				

					<display:column title="TXN Ref" headerClass="gridHdrBg"  style="text-align:center">
						<display:setProperty name="basic.msg.empty_list" value='<div class="searchMsg"><span>Nothing found to display</span></div>' />
	    				<a href="javascript:assignPopup('${row.screenID}','${row.screenData}','${row.crtdUserId}')">
			 				<s:set name="screenID">${row.screenID}</s:set>
			 				<s:property value="%{screenID}"/>
			 			</a>
					</display:column>				
				
				<display:column	 title="Screen ID" property="screenData"  headerClass="gridHdrBg"  sortable="false" style="text-align:center"/>
				<display:column	 title="Created User" property="crtdUserId" headerClass="gridHdrBg"  sortable="false" style="text-align:center"/>
				<display:column title="Status" property="actionClass" headerClass="gridHdrBg" style="text-align:center" ></display:column>
				<display:column title="Created Date" property="crtDate" headerClass="gridHdrBg" style="text-align:center" ></display:column>
			</display:table>

</s:form>
</div>




</div>

</div>

<div id="contentPaneBottom"></div>
