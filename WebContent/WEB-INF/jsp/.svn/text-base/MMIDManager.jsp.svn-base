<%@page import="com.opensymphony.xwork2.ActionContext"%>
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<%@taglib uri="http://displaytag.sf.net" prefix="display"%>

<s:head theme="simple" />
<sx:head parseContent="true" />
<script type="text/javascript">
function callSearchAccountNo(Action) {

	var accountNoField= document.getElementById("accountNo").value;

		window.open(
				"<s:url action='showBenificaryAccountSearchScreen' windowState='EXCLUSIVE' />",
				'mywindow', 'top=50,left=250,width=750,height=410');
	}
	function calltransactionType(){
	var transactionType = document.getElementById("transactionType").value;
	
	if(transactionType == "48")
		{
		document.getElementById("benificiaryMobileNo").disabled = true;
		document.getElementById("benificiaryMMID").disabled = true;
		document.getElementById("benificiaryMMID").value = "";
		document.getElementById("benificiaryMobileNo").value = "";
		document.getElementById("beneficaryAccountNo").disabled = false;
		document.getElementById("benificiaryIFSC").disabled = false;
		document.getElementById("fromAccType").disabled = false;
		document.getElementById("toAccType").disabled = false;
		}
	else if(transactionType == "45" || transactionType == "47"){

		document.getElementById("benificiaryMobileNo").disabled = false;
		document.getElementById("benificiaryMMID").disabled = false;
		document.getElementById("benificiaryIFSC").disabled = true;
		document.getElementById("beneficaryAccountNo").disabled = true;
		document.getElementById("fromAccType").disabled = true;
		document.getElementById("toAccType").disabled = true;
		document.getElementById("benificiaryIFSC").value = "";
		document.getElementById("beneficaryAccountNo").value = "";
		document.getElementById("fromAccType").value = "";
		document.getElementById("toAccType").value = "";
		
		}
	else
		{
		document.getElementById("benificiaryMobileNo").disabled = false;
		document.getElementById("benificiaryMMID").disabled = false;
		document.getElementById("benificiaryIFSC").disabled = false;
		document.getElementById("beneficaryAccountNo").disabled = false;
		document.getElementById("fromAccType").disabled = false;
		document.getElementById("toAccType").disabled = false;
		}
		}
</script>
	
<div id="contentPane">
<div id="contentPaneTop"></div>
<div id="contentPaneFluid">
<div id="contentPaneInternal"><span class="crumbs"><a
	href="#">Outbound</a>&nbsp;>&nbsp;Create IMPS payment</span></div>
<div id="content" >
<h1>Create IMPS payment</h1>
<form method="post">
<s:hidden id="searchAction" name="searchAction"></s:hidden>
<div><s:label name="message"></s:label><s:if test="hasFieldErrors()">
				<div class="errorMsg"><span class="errorMsgInline"><s:actionerror /><s:fielderror /></span></div>
			</s:if></div><!--Error Msg Div Ends -->
	<table width="100%" cellpadding="3" cellspacing="1" border="0">
		<tr>
			<td>
					<table width="100%" cellpadding="0" cellspacing="0" border="0">
					<tr>
						<td>
								<table width="95%" cellpadding="5" cellspacing="1" border="0"
									bgcolor="#c5d6f0" id="table1">
									<tr>
									<td class="textRight" width="10%">
											<s:label value="Transaction Type"></s:label>:&nbsp;
										</td>
									<td class="text" width="40%">
											<s:select list="#{'45':'P2P','47':'P2M','48':'P2A'}" name="transactionType" id="transactionType" cssClass="txtField" headerKey=""
												headerValue="Select Transaction Type" onchange="calltransactionType()" ></s:select>
									</td>
										
										<td width="10%" class="textRight">
											<s:label value="Ordering Customer Account"></s:label>:&nbsp;
										</td>
										<td width="40%" class="text">
											<s:textfield name="accountNo" id="accountNo" cssClass="txtField" required="true"></s:textfield>
											<input type="button" value="search Account.." onclick="callSearchAccountNo('AccountNO')">
										</td>
									</tr>
									<tr>
									<td class="textRight" width="10%">
											<s:label value="Benificiary Mobile Number"></s:label>:&nbsp;
										</td>
										<td class="text" width="40%">
											<s:textfield name="benificiaryMobileNo" id="benificiaryMobileNo" cssClass="txtField" maxLength="10" ></s:textfield>
											
										</td>
										<td class="textRight" width="10%">
											<s:label value="Benificiary MMID"></s:label>:&nbsp;
										</td>
										<td class="text" width="40%">
											<s:textfield name="benificiaryMMID" id="benificiaryMMID" cssClass="txtField"  maxLength="7"></s:textfield>
										</td>
	
																			</tr>
									<tr>
									<td class="textRight" width="10%">
											<s:label value="Benificiary IFSC"></s:label>:&nbsp;
										</td>
										<td class="text" width="40%">
											<s:textfield name="benificiaryIFSC" id="benificiaryIFSC" cssClass="txtField"  ></s:textfield>
											
										</td>
										<td class="textRight" width="10%">
											<s:label value="Beneficary Account No"></s:label>:&nbsp;
										</td>
										<td class="text" width="40%">
											<s:textfield name="beneficaryAccountNo" id="beneficaryAccountNo"></s:textfield>
										</td>
	
										
									
	
									</tr>
									<tr>
									<td class="textRight" width="10%">
											<s:label value="From A/C Type"></s:label>:&nbsp;
									</td>
									<td class="text" width="40%">
											<s:select list="#{'S':'Savings','C':'Current'}" name="fromAccType" id="fromAccType" cssClass="txtField"  headerKey=""
												headerValue="Select From Account Type" ></s:select>
									</td>
									<td class="textRight" width="10%">
											<s:label value="To A/C Type"></s:label>:&nbsp;
										</td>
										<td class="text" width="40%">
											<s:select list="#{'S':'Savings','C':'Current'}" name="toAccType" id="toAccType" cssClass="txtField"  headerKey=""
												headerValue="Select To Account Type" ></s:select>
										</td>
									</tr>
									<tr>
									<td width="10%" class="textRight">
											<s:label value="Ordering Amount"></s:label>:&nbsp;</td>
										<td width="40%" class="text">
											<s:textfield name="orderingAmount" id="orderingAmount" cssClass="txtField" required="true"></s:textfield>
											
										</td>
								<td class="textRight" width="10%">
										<s:label value="Remarks"></s:label>:&nbsp;
										</td>
									<td width="40%" class="text">
										<s:textfield name="remarks" id="remarks" cssClass="txtField" required="true"></s:textfield>
										</td>
										
										
									</tr>
								</table>
						</td>
					</tr>
					<tr>
					<td><s:submit value="Submit" action="insertMMID"></s:submit>
					<s:reset value="Reset"></s:reset>
					</td>
					</tr>
					</table>
			</td>
		</tr>
	</table>
</form>
</div>
</div>
</div>
<div id="contentPaneBottom"></div>