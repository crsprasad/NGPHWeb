

<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<%@taglib uri="http://displaytag.sf.net" prefix="display"%>
<link href="SpryAssets/SpryCollapsiblePanel.css" rel="stylesheet"
	type="text/css" />
<script src="SpryAssets/SpryCollapsiblePanel.js" type="text/javascript"></script>
<script src="js/FormField.js" type="text/javascript"></script>
<script type="text/javascript">

	function callEnable() {
		var aa = document.getElementById('form');
		for ( var i = 0; i < aa.length; i++) {
			aa.elements[i].disabled = false;
		}
	}
	function callDelete(row) {
		document.getElementById("rowTodelete").value = row;
		//	alert(row);
		document.forms[0].action = "rowToDelete";
		document.forms[0].submit();

	}
	function callShipping() {
		var shipmentPeriod = document.getElementById("shipmentPeriod").value;

		var latestDateofShipment = document
				.getElementById("latestDateofShipment").value;
		if (shipmentPeriod != ""  ) { 
			document.getElementById("latestDateofShipment").disabled = true;
			document.getElementById("shipmentPeriod").disabled = false;
		} else if (shipmentPeriod == "" ) {
			document.getElementById("latestDateofShipment").disabled = false;
			document.getElementById("shipmentPeriod").disabled = true;
		}
		else
			{
			document.getElementById("latestDateofShipment").disabled = false;
			document.getElementById("shipmentPeriod").disabled = false;
			}
	}
	function callTolerance() {

		var positiveTolerence = document.getElementById("positiveTolerance").value;
		var negativeTolerance = document.getElementById("negativeTolerance").value;
		var maxCredit = document.getElementById("maximumCreditAmount").value;

		if (positiveTolerence != ""  ) {

			document.getElementById("maximumCreditAmount").disabled = true;
		}
		else if(negativeTolerance != ""){

			document.getElementById("maximumCreditAmount").disabled = true;
		}

		 else if (maxCredit != "") {

			document.getElementById("positiveTolerance").disabled = true;
			document.getElementById("negativeTolerance").disabled = true;
		} else {
			document.getElementById("maximumCreditAmount").disabled = false;
			document.getElementById("positiveTolerance").disabled = false;
			document.getElementById("negativeTolerance").disabled = false;
		}
	}

	function callAddTOGrid() {
		document.forms[0].action = "addRowToLcGrid";
		document.forms[0].submit();
	}
	function callSubmit(saveAction) {
		var aa = document.getElementById('form');
		for ( var i = 0; i < aa.length; i++) {
			aa.elements[i].disabled = false;
		}
		document.getElementById("saveAction").value = saveAction;
		document.forms[0].action = "saveLcCredit";
		document.forms[0].submit();
	}

	function callSearchAccountNo(Action) {

		window.open(
				"<s:url action='showIFSCScreen' windowState='EXCLUSIVE' />",
				'mywindow', 'top=50,left=250,width=750,height=410');

	}

	function callAdvisingSearchAccountNo() {

		window
				.open(
						"<s:url action='showAdvisingIFSCScreen' windowState='EXCLUSIVE' />",
						'mywindow', 'top=50,left=250,width=750,height=410');

	}
	function callSearchIFSC(Action,flag) {

		document.getElementById("searchAction").value = Action;
		document.getElementById("IFSCFLAG").value = flag;
		window.open(
				"<s:url action='showIFSCScreen' windowState='EXCLUSIVE' />",
				'mywindow', 'top=50,left=250,width=750,height=410');
	}
	function callAdvise() {
		var adviseThroughBankCode = document
				.getElementById("adviseThroughBankCode").value;
		var adviseThroughBankLocation = document
				.getElementById("adviseThroughBankLocation").value;
		var adviseThroughBankName = document
				.getElementById("adviseThroughBankName").value;

		//alert("adviseThroughBankCode : - "+adviseThroughBankCode +" adviseThroughBankLocation : - "+adviseThroughBankLocation+ " adviseThroughBankName:- "+adviseThroughBankName)
		if (adviseThroughBankCode != "") {
			document.getElementById("adviseThroughBankLocation").disabled = true;
			document.getElementById("adviseThroughBankName").disabled = true;
		} else if (adviseThroughBankLocation != "") {
			document.getElementById("adviseThroughBankCode").disabled = true;
			document.getElementById("advisethroBtn").disabled = true;
			document.getElementById("adviseThroughBankName").disabled = true;
		} else if (adviseThroughBankName != "") {
			document.getElementById("adviseThroughBankCode").disabled = true;
			document.getElementById("advisethroBtn").disabled = true;
			document.getElementById("adviseThroughBankLocation").disabled = true;
		} else {
			document.getElementById("adviseThroughBankCode").disabled = false;
			document.getElementById("adviseThroughBankLocation").disabled = false;
			document.getElementById("adviseThroughBankName").disabled = false;
			document.getElementById("advisethroBtn").disabled = false;
		}

	}

	function callAuthorize() {
		var authorisedBankCode = document
				.getElementById("authorisedBankCode").value;
		var authorisedAndAddress = document
				.getElementById("authorisedAndAddress").value;

		if (authorisedBankCode != "") {

			document.getElementById("authorisedAndAddress").disabled = true;
			document.getElementById("authorisedBankCode").disabled = false;
			document.getElementById("btnAUTHORISEDIFSC").disabled = false;
			
		} else if (authorisedAndAddress != "") {
			document.getElementById("authorisedAndAddress").disabled = false;
			document.getElementById("authorisedBankCode").disabled = true;
			document.getElementById("btnAUTHORISEDIFSC").disabled = true;
			
		} else {
			document.getElementById("authorisedBankCode").disabled = false;
			document.getElementById("authorisedAndAddress").disabled = false;
			document.getElementById("btnAUTHORISEDIFSC").disabled = false;
		}		
		
	}

	function callCommentPOPUp()
	{
		var repair = document.getElementById("repair").value;
//	alert("Repair :  - "+repair);
		if(repair!="")
			{
			var comment  = document.getElementById("comment").value; 
			if(comment==""){
			window
			.open(
					"<s:url action='showRepairLetterOFCreditPopup' windowState='EXCLUSIVE' />",
					'mywindow', 'top=500,left=250,width=750,height=410');
				}else{
						callSubmitForScreen();
					}
			}
		else{
			callSubmitForScreen();	
			}
		
	}
	function callSubmitForScreen()
	{
		//alert("callSubmitForScreen")
		document.forms[0].action="getLetterOFCreditApproval" ;
		document.forms[0].submit();
	}
</script>

<sx:head parseContent="true" />

<s:head />
<div id="contentPane">
<div id="contentPaneTop"></div>
<div id="contentPaneFluid">
<div id="contentPaneInternal"><span class="crumbs"><a
	href="#">Outbound Payments</a>&nbsp;>&nbsp;Letter Of
Credit&nbsp;>&nbsp;Generate Pre Advice</span></div>
<div id="content">
<h1>Generate Pre Advice</h1>
<s:form id="form" method="post">
	<s:hidden id="searchAction" name="searchAction"></s:hidden>
	<s:hidden name="rowTodelete" id="rowTodelete"></s:hidden>
	<s:hidden name="repairMsgref" id="repairMsgref"></s:hidden>
	<s:hidden name="RepairData" id="RepairData"></s:hidden>
	<s:hidden name="repair" id="repair"></s:hidden>
	<s:hidden id="msgRef" name="msgRef"></s:hidden>
	<s:hidden name="IFSCFLAG" id="IFSCFLAG" ></s:hidden>
	<s:hidden id="issueDate" name="issueDate"></s:hidden>
	<s:hidden id="comment" name="comment"></s:hidden>
<s:hidden id="msgHost" name="msgHost"> </s:hidden>
<s:hidden id="seqNo" name="seqNo"></s:hidden>
<s:hidden id="senderBank" name="senderBank"></s:hidden>
<s:hidden id="validUserToApprove" name="validUserToApprove"></s:hidden>

	<!--Error Msg Div Starts -->
	<div><s:if test="hasFieldErrors()">
		<div class="errorMsg"><span class="errorMsgInline"><s:actionerror /><s:fielderror /></span></div>
	</s:if></div>
	<!--Error Msg Div Ends -->
	<!-- Collapsible Panels Start-->
	<div id="CollapsiblePanel1" class="CollapsiblePanel">
	<div class="CollapsiblePanelTab"><!--  <img src="images/openPanel.png" width="9" height="7" alt="openPanel" />-->&nbsp;Basic
	Details</div>
	<div class="CollapsiblePanelContent">
	<div><!-- Group One Div Starts --> <s:div id="main"
		cssClass="dataGrid" style="width:100%;">
		<table width="100%" cellpadding="3" cellspacing="1" border="0"
			bgcolor="#ffffff">
			<tr>
				<td width="20%" class="textRight"><s:label
					value="%{getText('label.lcType')}"></s:label>:<span
					class="mandatory">*</span></td>
				<td width="30%" class="text"><s:select
					list="{'IRREVOCABLE','REVOCABLE', 'IRREVOCABLE TRANSFERABLE', 'REVOCABLE TRANSFERABLE', 'IRREVOCABLE STANDBY', 'REVOCABLE STANDBY', 'IRREVOC TRANS STANDBY'}"
					name="lcType" id="lcType" headerKey="" headerValue="Select Lc Type"></s:select></td>
				
				<s:if test="%{repair=='REPAIR'}">
				<td width="20%" class="textRight"><s:label
					value="%{getText('label.lc_Number')}"></s:label>:<span
					class="mandatory">*</span></td>
				<td width="30%" class="text" ><s:textfield name="lcNumber"
					id="lcNumber" maxLength="16" readonly="true"></s:textfield></td>
					</s:if>
					<s:if test="%{repair!='REPAIR'}">
					
					<td width="20%" class="textRight"><s:label
					value="%{getText('label.lc_Number')}"></s:label>:<span
					class="mandatory">*</span></td>
				<td width="30%" class="text"><s:textfield name="lcNumber"
					id="lcNumber" maxLength="16" onKeyPress='return notAllowCheck(this,event);'></s:textfield></td>
					</s:if>
			</tr>
			<tr>
				<td width="20%" class="textRight"><s:label
					value="%{getText('label.lcCurrency')}"></s:label>:<span
					class="mandatory">*</span></td>
				<td width="30%" class="text"><s:select
					list="{'EUR','GBS','INR','USD'}" name="lcCurrency" id="lcCurrency"
					headerKey="" headerValue="Select Currency"></s:select></td>
				<td width="20%" class="textRight"><s:label
					value="%{getText('label.lcAmount')}"></s:label>:<span
					class="mandatory">*</span></td>
				<td width="30%" class="text"><s:textfield name="lcAmount"
					id="lcAmount" maxLength="15" onKeyPress='return notAllowCheck(this,event);'></s:textfield></td>
			</tr>

			<tr>
				<td width="20%" class="textRight"><s:label
					value="%{getText('label.lcExpiryDate')}"></s:label>:<span
					class="mandatory">*</span></td>
				<td width="30%" class="text"><sx:datetimepicker
					name="lcExpiryDate" cssClass="txtField" id="lcExpiryDate"
					displayFormat="MM/dd/yyyy" type="date" /></td>
				<td width="20%" class="textRight"><s:label
					value="%{getText('label.lcExpiryPlace')}"></s:label>:<span
					class="mandatory">*</span></td>
				<td width="30%" class="text"><s:textfield name="lcExpirePlace"
					id="lcExpirePlace" maxLength="29" onKeyPress='return notAllowCheck(this,event);' ></s:textfield></td>
			</tr>
			<tr>
				<td width="20%" class="textRight"><s:label
					value="%{getText('label.applicantNameAddress')}"></s:label>:<span
					class="mandatory">*</span></td>
				<td width="30%" class="text"><s:textarea rows="4" cols="35" wrap="HARD"
					name="applicantNameAddress" id="applicantNameAddress"
					onKeyPress='return maxLength(this,"139","applicantNameAddress","35",event);'></s:textarea></td>
				<td width="20%" class="textRight"><s:label
					value="%{getText('label.beneficiaryNameAddress')}"></s:label>:<span
					class="mandatory">*</span></td>
				<td width="30%" class="text"><s:textarea
					name="beneficiaryNameAddress" id="beneficiaryNameAddress" rows="4" cols="35" wrap="HARD"
					onKeyPress='return maxLength(this,"139","beneficiaryNameAddress","35",event);'></s:textarea></td>
			</tr>
			<tr>
				<td width="20%" class="textRight"><s:label
					value="%{getText('label.beneficiaryAccount')}"></s:label></td>
				<td width="30%" class="text"><s:textfield
					name="beneficiaryAccount" id="beneficiaryAccount" maxLength="34" onKeyPress='return notAllowCheck(this,event);' ></s:textfield>
				</td>
				<td width="20%" class="textRight"><s:label
					value="%{getText('label.AdvisingBank')}"></s:label>:<span
					class="mandatory">*</span></td>
				<td width="30%" class="text"><s:textfield name="advisingBank"
					id="advisingBank" maxLength="11" onKeyPress='return notAllowCheck(this,event);'></s:textfield><input type="button"
					value="Search IFSC..."
					onclick="return callSearchIFSC('ADVISINGBANK','PARTY')" id="btnSearch"
					class="btn" /></td>
			</tr>
			
		</table>
	</s:div> <!-- Group One Div Ends --></div>
	</div>
	</div>
	<br />
	<div id="CollapsiblePanel2" class="CollapsiblePanel">
	<div class="CollapsiblePanelTab"><!--  <img src="images/openPanel.png" width="9" height="7" alt="openPanel" />-->&nbsp;</div>
	<div class="CollapsiblePanelContent">
	<div><!-- Group Two Div Starts --> <s:div id="mandatory"
		cssClass="dataGrid" style="width:100%;">
		<table width="100%" cellpadding="5" cellspacing="1" border="0"
			bgcolor="#ffffff">
			<tr>
				<td width="20%" class="textRight"><s:label
					value="%{getText('label.positiveTolerance')}"></s:label>:</td>

				<td width="30%" class="text"><s:select
					list="#session.positiveToleranceList" name="positiveTolerance"
					id="positiveTolerance" headerKey=""
					headerValue="Select Positive Tolerance" onchange="callTolerance()"></s:select>
				</td>
				<td width="20%" class="textRight"><s:label
					value="%{getText('label.negativeTolerance')}"></s:label>:</td>
				<td width="30%" class="text"><s:select
					list="#session.negativeToleranceList" name="negativeTolerance"
					id="negativeTolerance" headerKey=""
					headerValue="Select Negative Tolerance" onchange="callTolerance()" ></s:select>

				</td>
			</tr>
			<tr>
				<td width="20%" class="textRight"><s:label
					value="%{getText('label.maximumCreditAmount')}"></s:label>:</td>
				<td width="30%" class="text"><s:select
					name="maximumCreditAmount" id="maximumCreditAmount" headerKey=""
					headerValue="Select Maximum Credit Amount" list="{'NOT EXCEEDING'}"
					onchange="callTolerance()"></s:select></td>
				<td width="20%" class="textRight"><s:label
					value="%{getText('label.additionalAmounts')}"></s:label>:</td>
				<td width="30%" class="text"><s:textarea
					name="additionalAmounts" id="additionalAmounts" rows="4" cols="35" wrap="HARD"
					onKeyPress='return maxLength(this,"139","additionalAmounts","35",event);'></s:textarea></td>
			</tr>
			<tr>
				<td width="20%" class="textRight"><s:label
					value="%{getText('label.authorisedBankCode')}"></s:label>:</td>
				<td width="30%" class="text"><s:textfield
					name="authorisedBankCode" id="authorisedBankCode" maxLength="11" onkeyup="callAuthorize()" onpaste="callAuthorize()" onKeyPress='return notAllowCheck(this,event);'></s:textfield><input
					type="button" value="Search IFSC..."
					onclick="return callSearchIFSC('AUTHORISEDIFSC','PARTY')" id="btnAUTHORISEDIFSC"
					class="btn" /></td>
				<td width="20%" class="textRight"><s:label
					value="%{getText('label.authorisationMode')}"></s:label>:</td>
				<td width="30%" class="text"><s:select
					list="{'BY PAYMENT','BY ACCEPTANCE','BY NEGOTIATION','BY DEF PAYMENT','BY MIXED PYMT'}"
					name="authorisationMode" id="authorisationMode" headerKey=""
					headerValue="Select Mode"></s:select></td>
			</tr>

			<tr>
				<td width="20%" class="textRight"><s:label
					value="%{getText('label.authorisedAndAddress')}"></s:label>:</td>
				<td width="30%" class="text"><s:textarea
					name="authorisedAndAddress" id="authorisedAndAddress" rows="4" cols="35" wrap="HARD" 
					onKeyPress='return maxLength(this,"139","authorisedAndAddress","35",event);' onkeyup="callAuthorize()" ></s:textarea></td>
				<td width="20%" class="textRight"><s:label
					value="%{getText('label.goodsLoadingDispatchPlace')}"></s:label>:</td>
				<td width="30%" class="text"><s:textfield
					name="goodsLoadingDispatchPlace" id="goodsLoadingDispatchPlace"
					maxLength="65" onKeyPress='return notAllowCheck(this,event);' ></s:textfield></td>
			</tr>
			<tr>
				<td width="20%" class="textRight"><s:label
					value="%{getText('label.goodsDestination')}"></s:label>:</td>
				<td width="30%" class="text"><s:textfield
					name="goodsDestination" id="goodsDestination" maxLength="65" onKeyPress='return notAllowCheck(this,event);' ></s:textfield>
				</td>
				<td width="20%" class="textRight"><s:label
					value="%{getText('label.latestDateofShipment')}"></s:label>:</td>
				<td width="30%" class="text"><sx:datetimepicker
					name="latestDateofShipment" displayFormat="MM/dd/yy"
					cssClass="txtField" type="date" id="latestDateofShipment"
					></sx:datetimepicker>
			</tr>
			<tr>
				<td width="20%" class="textRight"><s:label
					value="%{getText('label.ShipmentPeriod')}"></s:label>:</td>
				<td width="30%" class="text"><s:textarea name="shipmentPeriod"
					id="shipmentPeriod" rows="6" cols="65" wrap="HARD" onKeyPress='return maxLength(this,"389","shipmentPeriod","65",event);' ></s:textarea></td>
				<td width="20%" class="textRight"><s:label
					value="%{getText('label.ShipmentTerms')}"></s:label>:</td>
				<td width="30%" class="text"><s:textfield name="shipmentTerms"
					id="shipmentTerms" maxLength="65" onKeyPress='return notAllowCheck(this,event);' ></s:textfield></td>
			</tr>
			<tr>
			<td width="20%" class="textRight"><s:label	value="%{getText('label.initialDispatchPlace')}"></s:label>:</td>
			<td width="30%" class="text"><s:textfield name="initialDispatchPlace" id="initialDispatchPlace" onKeyPress='return notAllowCheck(this,event);' ></s:textfield> </td>
			
			<td width="20%" class="textRight"><s:label	value="%{getText('label.finalDeliveryPlace')}"></s:label>:</td>
			<td width="30%" class="text"><s:textfield name="finalDeliveryPlace" id="finalDeliveryPlace" onKeyPress='return notAllowCheck(this,event);' ></s:textfield> </td>
		</tr>
		</table>
	</s:div> <!-- Group Two Div Ends --></div>
	</div>
	</div>
	<br />
	<div id="CollapsiblePanel3" class="CollapsiblePanel">
	<div class="CollapsiblePanelTab"><!--  <img src="images/openPanel.png" width="9" height="7" alt="openPanel" />-->&nbsp;Commodity
	Details</div>
	<div class="CollapsiblePanelContent">
	<div><!-- Group Three Div Starts --> <s:div id="register"
		cssClass="dataGrid" style="width:100%;">
		<table width="100%" cellpadding="5" cellspacing="1" border="0"
			bgcolor="#ffffff">
			<tr>
				<td width="20%" class="textRight"><s:label
					value="%{getText('label.Commodity')}"></s:label>:</td>
				<td width="40%" class="text"><s:textfield name="lcCommodity"
					id="lcCommodity" maxLength="65" size="65" onKeyPress='return notAllowCheck(this,event);'></s:textfield></td>
				<td width="40%" class="text"><input type="button"
					value="ADD Commodity" onclick="callAddTOGrid()"></td>

			</tr>
		</table>
		<table width="100%" cellpadding="5" cellspacing="1" border="0"
			bgcolor="#ffffff">
			<tr>
				<td><display:table uid="row"
					name="sessionScope.commoditiesList" pagesize="10"
					requestURI="/addRowToLcGrid" cellpadding="3" cellspacing="0"
					class="dataGrid">
					<display etProperty name="css.tr.even" value="TDBGlightgray" />
					<display etProperty name="css.tr.odd" value="TDBGgray"
						cssClass="odd" />

					<display:column title="Commodity" property="lcCommodity"
						headerClass="gridHdrBg" sortable="true" style="text-align:center" />


					<display:column>
						<input type="button" value="delete"
							onclick="callDelete('${row.lcId}')">
					</display:column>
				</display:table></td>
			</tr>
		</table>


	</s:div></div>
	</div>
	</div>
	<br />
	<div id="CollapsiblePanel4" class="CollapsiblePanel">
	<div class="CollapsiblePanelTab"><!--  <img src="images/openPanel.png" width="9" height="7" alt="openPanel" />-->&nbsp;
	Other Details</div>
	<div class="CollapsiblePanelContent"><!-- Group Four Div Starts -->
	<s:div id="insCurrency" cssClass="dataGrid" style="width:100%;">
		<table width="100%" cellpadding="5" cellspacing="1" border="0"
			bgcolor="#ffffff">

			<tr>
				<td width="20%" class="textRight"><s:label
					value="%{getText('label.adviseThroughBankpartyidentifier')}"></s:label>:</td>
				<td width="30%" class="text"><s:select list="{'C','D'}"
					name="adviseThroughBankpartyidentifier"
					id="adviseThroughBankpartyidentifier" headerKey=""
					headerValue="Select PID"></s:select><a> </a><s:textfield
					name="adviseThroughBankAcc" id="adviseThroughBankAcc" maxLength="34" onKeyPress='return notAllowCheck(this,event);' ></s:textfield>
				</td>
				<td width="20%" class="textRight"><s:label
					value="%{getText('label.adviseThroughBankCode')}"></s:label>:</td>
				<td width="30%" class="text"><s:textfield
					name="adviseThroughBankCode" id="adviseThroughBankCode"
					maxLength="11" onkeyup="callAdvise()" onKeyPress='return notAllowCheck(this,event);'>
				</s:textfield> <input type="button" value="Search IFSC..."
					onclick="return callSearchIFSC('ADVICETHOUGHIFSC','PARTY')" id="advisethroBtn"
					class="btn" /></td>
			</tr>
			<tr>
				<td width="20%" class="textRight"><s:label
					value="%{getText('label.adviseThroughBankLocation')}"></s:label>:</td>
				<td width="30%" class="text"><s:textfield
					name="adviseThroughBankLocation" id="adviseThroughBankLocation"
					maxLength="35" onkeyup="callAdvise()" onKeyPress='return notAllowCheck(this,event);' ></s:textfield></td>
				<td width="20%" class="textRight"><s:label
					value="%{getText('label.adviseThroughBankNameAddress')}"></s:label>:</td>
				<td width="30%" class="text"><s:textarea
					name="adviseThroughBankName" id="adviseThroughBankName" rows="4" cols="35" wrap="HARD"
					onkeyup="callAdvise()" onKeyPress='return maxLength(this,"139","adviseThroughBankName","35",event);'></s:textarea></td>
			</tr>
			<tr>
				<td width="20%" class="textRight"><s:label
					value="%{getText('label.Narrative')}"></s:label>:</td>
				<td width="30%" class="text"><s:textarea name="Narrative" wrap="HARD"
					id="Narrative" cols="50" rows="10" onKeyPress='return maxLength(this,"1749","Narrative","35",event);'></s:textarea></td>
				<td width="20%" class="textRight"><s:label
					value="%{getText('label.SendertoReceiverInformation')}"></s:label>:</td>
				<td width="30%" class="text"><s:textarea
					name="SendertoReceiverInformation" id="SendertoReceiverInformation" wrap="HARD" rows="4" cols="35" onKeyPress='return maxLength(this,"209","SendertoReceiverInformation","35",event);'></s:textarea>
				</td>
			</tr>


		</table>
	</s:div></div>
	</div>
	<!--  button panel starts -->
	<div class="clearfix"></div>
	<table width="100%" cellpadding="0" cellspacing="0" border="0">
		<tr>
			<td><s:if test="%{checkForSubmit!='Display_Approve_Reject'}">
				<input type="button" value="Submit" 
					class="btn" onclick="callCommentPOPUp()" />
				<input type="reset" value="Cancel" onclick="cancel()" class="btn" />
			</s:if> <s:if test="%{checkForSubmit=='Display_Approve_Reject'}">
				<s:if test="%{validUserToApprove}">
					<input type="button" value="Approve"
						onclick="callSubmit('Approve')">
					<input type="button" value="Reject" onclick="callSubmit('Reject')">
					<input type="button" value="Cancel" onclick="cancel()">
				</s:if>
				<s:if test="%{!validUserToApprove}">
				<input type="button" value="Submit" 
					class="btn" onclick="callCommentPOPUp()" />
				<input type="reset" value="Cancel" onclick="cancel()" class="btn" />
				</s:if>
			</s:if></td>
		</tr>
	</table>
	<s:hidden id="saveAction" name="saveAction"></s:hidden>
	<s:hidden id="hiddenTxnRef" name="hiddenTxnRef"></s:hidden>

	<!--  button panel ends -->
</s:form></div>
</div>
</div>
<div id="contentPaneBottom"></div>
<script type="text/javascript">
	var CollapsiblePanel1 = new Spry.Widget.CollapsiblePanel(
			"CollapsiblePanel1");
	var CollapsiblePanel2 = new Spry.Widget.CollapsiblePanel(
			"CollapsiblePanel2");
	var CollapsiblePanel3 = new Spry.Widget.CollapsiblePanel(
			"CollapsiblePanel3");
	var CollapsiblePanel4 = new Spry.Widget.CollapsiblePanel(
			"CollapsiblePanel4");
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
						document.getElementById("lcExpiryDate").disabled= true;
						document.getElementById("latestDateofShipment").disabled = true;
					}
				
			}
			else
			{
				for ( var i = 0; i < aa.length-5; i++) 
				{
					aa.elements[i].enabled = true;
					document.getElementById("lcExpiryDate").enabled= true;
					document.getElementById("latestDateofShipment").enabled = true;
				}
			}
	}
</script>
