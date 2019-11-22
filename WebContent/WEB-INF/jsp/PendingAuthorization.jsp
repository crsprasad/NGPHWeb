<%@page import="com.opensymphony.xwork2.ActionContext"%>
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<%@taglib uri="http://displaytag.sf.net" prefix="display"%>

<s:head theme="simple" />

<script type="text/javascript">


function callPrintPreview(txnRef,actionScreen) {

document.getElementById("hiddenTxnRef").value=txnRef;
document.getElementById("tempTxnRef").value=txnRef;


 if(actionScreen=='Lc Open(MT-700)'){
		document.forms[0].action = "printPreviewLCOpenPage";
		document.forms[0].submit();
		}
	else if(actionScreen=='Create Bank Guarantee(MT-760)'){
		document.forms[0].action = "printPreviewCreateBankGuarantee";
		document.forms[0].submit();
		}
	else if(actionScreen=='Advice Lc Payment(MT-756)'){
		document.forms[0].action = "printPreviewAdviceLCPayment";
		document.forms[0].submit();
		}
	else if(actionScreen=='Authorisation to Reimburse(MT-740)'){
		document.forms[0].action = "printPreviewAuthoriseLCPaymentAdvice";
		document.forms[0].submit();
		}
	else if(actionScreen=='Amend LC(MT-707)'){
		document.forms[0].action = "printPreviewAmendLCPage";
		document.forms[0].submit();
		}
	else if(actionScreen=='Amend BG(MT-767)'){
		document.forms[0].action = "printPreviewAmendBG";
		document.forms[0].submit();
		}
	else if(actionScreen=='Acknowledge BG(MT-768)'){
		document.forms[0].action = "printPreviewAckBG";
		document.forms[0].submit();
		}
	else if(actionScreen=='Create Bank Guarantee Cover(MT-760COVER)'){
		document.forms[0].action = "printPreviewCreateBGCover";
		document.forms[0].submit();
		}
	else if(actionScreen=='Amend BG Cover(MT-767COVER)'){
		document.forms[0].action = "printPreviewAmendBGCover";
		document.forms[0].submit();
		}
	else if(actionScreen=='Advice of Reduction(MT-769)'){
		document.forms[0].action = "printPreviewAdviceofReduction";
		document.forms[0].submit();
		}
	else if(actionScreen=='LC Acknowledgement(MT-730)'){
		document.forms[0].action = "printPreviewLCAcknowledgement";
		document.forms[0].submit();
		}
	else if(actionScreen=='Free Format Payment(MT-799)'){
		document.forms[0].action = "printPreviewFreeFormatPage";
		document.forms[0].submit();
		}
	else if(actionScreen=='Advice of Refusal(MT-734)'){
		document.forms[0].action = "printPreviewAdviceofRefusal";
		document.forms[0].submit();
		}
	else if(actionScreen=='Advice of Discrepancy(MT-750)'){
		document.forms[0].action = "printPreviewAdviceofDiscrepancy";
		document.forms[0].submit();
		}
	else if(actionScreen=='Advice of Payment(MT-754)'){
		document.forms[0].action = "printPreviewAdviceofPayment";
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
	href="#">Outbound Payments</a>&nbsp;>&nbsp;Pending Authorization</span></div>
<div id="content">
<s:form method="post" id="form"></br></br></br>
<s:hidden name="hiddenTxnRef" id="hiddenTxnRef"></s:hidden>
<s:hidden name="tempTxnRef" id="tempTxnRef"></s:hidden>
<display:table uid="row" name="screenValue" pagesize="10" requestURI="/pendingAuthorizationOutboundAction" cellpadding="3"
				cellspacing="0" class="dataGrid">
				<display:setProperty name="basic.msg.empty_list" 
    				value='<div class="infoMsg"><span>Nothing found to display</span></div>' />
				<display etProperty name="css.tr.even" value="TDBGlightgray" />
				<display etProperty name="css.tr.odd" value="TDBGgray" cssClass="odd"/>
				
				<display:column	 title="TXN Ref" property="screenID"  headerClass="gridHdrBg"  sortable="false" style="text-align:center"/>
				<display:column	 title="Screen ID" property="screenData"  headerClass="gridHdrBg"  sortable="false" style="text-align:center"/>
				<display:column	 title="Created User" property="crtdUserId" headerClass="gridHdrBg"  sortable="false" style="text-align:center"/>
				<display:column title="Status" property="actionClass" headerClass="gridHdrBg" style="text-align:center" ></display:column>
				<display:column title="Created Date" property="crtDate" headerClass="gridHdrBg" style="text-align:center"></display:column>
				<display:column style="text-align:left">
					<input type="button" class="btn"  value="Print Preview"	onclick="callPrintPreview('${row.screenID}','${row.screenData}');" />
				</display:column>
			</display:table>

</s:form>
</div>




</div>

</div>

<div id="contentPaneBottom"></div>
