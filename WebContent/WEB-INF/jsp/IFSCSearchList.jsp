<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="/struts-tags" prefix="s"%>
<%@taglib uri="http://displaytag.sf.net" prefix="display"%>
<link href="SpryAssets/SpryCollapsiblePanel.css" rel="stylesheet"
	type="text/css" />
<script src="SpryAssets/SpryCollapsiblePanel.js" type="text/javascript"></script>
<script type="text/javascript" src="js/jquery-1.11.0.js"></script>
<script type="text/javascript">


function callFetchBankName()
{
	var bankName= document.getElementById("bankName").value;
	if(bankName=="" || bankName!=""){
		window.open(
			"<s:url action='showBankNameSearch' windowState='EXCLUSIVE' />",
				'mywindow', 'top=50,left=250,width=750,height=410');
				
		}
	}

function callFetchBranchName()
{
	var bankName= document.getElementById("bankName").value;
	var branchName= document.getElementById("branchName").value;
	var stateName = document.getElementById("stateName");
	var stateNameValue = stateName.options[stateName.selectedIndex].value;
	
	

	if(branchName=="" || branchName!=""){
		window.open(
			"<s:url action='showBranchNameSearch' windowState='EXCLUSIVE' />",
				'mywindow', 'top=50,left=250,width=750,height=410');
				
		}
	}

function callFetchIFSCCodes()
{
	var ifscCode= document.getElementById("ifscCode").value;
	var bankName= document.getElementById("bankName").value;
	var branchName= document.getElementById("branchName").value;
	var stateName = document.getElementById("stateName");
	var stateNameValue = stateName.options[stateName.selectedIndex].value;
	
	
	if(ifscCode=="" || ifscCode!=""){
		window.open(
			"<s:url action='showIFSCCodeSearch' windowState='EXCLUSIVE' />",
				'mywindow', 'top=50,left=250,width=750,height=410');
				
		}
	}

function fetchDistrictNames()
{
	document.getElementById("districtNameDropDown").value="0";
	document.getElementById("cityNameDropDown").value="0";
	var stateName = document.getElementById("stateName");
	var stateNameValue = stateName.options[stateName.selectedIndex].value;
	if(stateName.selectedIndex!=0)
	{	
		
		document.forms[0].action = "fetchDistrictNames";
		document.forms[0].submit();
		
	}
	else
	{
		alert("Please select State Name!");
	}
    
}


function fetchCityNames()
{
	var stateName = document.getElementById("stateName");
	var districtName = document.getElementById("districtName");
	var stateNameValue = stateName.options[stateName.selectedIndex].value;
	var districtNameValue = districtName.options[districtName.selectedIndex].value;
	if(stateName.selectedIndex!=0)
	{	
		if(districtName.selectedIndex!=0)
			{
				document.forms[0].action = "fetchCityNames";
				document.forms[0].submit();
			}
		else
			{
			alert("Please select District Name!");
			}
		
	}
else 
	{
		alert("Please select State Name!");
	}
}


function getIFSCDetails()
{
	document.forms[0].action = "getIFSCDetailsData";
	document.forms[0].submit();
	}
function cancel()
{
	document.forms[0].action = "resetFields";
	document.forms[0].submit();

}

</script>
<sx:head parseContent="true" />
<s:head />
<div id="contentPane">
<div id="contentPaneTop"></div>
<div id="contentPaneFluid">
<div id="contentPaneInternal"><span class="crumbs"><a
	href="#">Enquiries & Reports</a>&nbsp;>&nbsp;IFSCCode Search</span></div>
<div id="content">
<h1>IFSCCode Search</h1>
<sj:head />
<s:form method="post" id="form" name="template">
<s:hidden id="searchAction" name="searchAction"></s:hidden>	
<s:hidden id="districtNameDropDown" name="districtNameDropDown"/>
<s:hidden id="cityNameDropDown" name="cityNameDropDown"/>
	<!--Error Msg Div Starts -->
	<div><s:if test="hasFieldErrors()">
				<div class="errorMsg"><span class="errorMsgInline"><s:actionerror /><s:fielderror /></span></div>
			</s:if></div><!--Error Msg Div Ends -->
	<!-- Collapsible Panels Start-->
	<div id="CollapsiblePanel1" class="CollapsiblePanel">
	<div class="CollapsiblePanelTab"><!--  <img src="images/openPanel.png" width="9" height="7" alt="openPanel" />-->&nbsp;IFSCCode Search</div>
	<div class="CollapsiblePanelContent">
	<div><!-- Group One Div Starts --> 
	<s:div id="main"	cssClass="dataGrid" style="width:100%;">
<table width="100%" cellpadding="3" cellspacing="1" border="0"	bgcolor="#ffffff">
	<tr>
		<td class="textRight" width="20%"><s:label value="%{getText('label.bankName')}"></s:label>:</td>
		<td class="text" width="30%"><s:textfield name="bankName" id="bankName" onKeyPress='return notAllowCheck(this,event);' ></s:textfield><input type="button" value="Fetch" onclick="callFetchBankName()"> </td>
		<td class="textRight" width="20%"><s:label value="%{getText('label.stateName')}"></s:label>:</td>
		<td class="text" width="30%"><s:select label="stateName" list="#session.stateNameDropDown" cssClass="txtField" name="stateName" id="stateName" headerKey="0" headerValue="Select State Name" key="label.stateName" required="true" onchange="fetchDistrictNames();"></s:select></td>
	<tr>
		<s:if test="%{#session.districtNameDropDown == null}">
		<s:set name="districtNameDropDown" value="" scope="session"/>
		<td class="textRight" width="20%"><s:label value="%{getText('label.districtName')}"></s:label>:</td>
		<td class="text" width="30%"><select name="districtName" ><option value="" selected="selected">Select District Name</option></select></td>	
		</s:if>
		<s:else>
		<td class="textRight" width="20%"><s:label value="%{getText('label.districtName')}"></s:label>:</td>
		<td class="text" width="30%"><s:select label="districtName" list="#session.districtNameDropDown" cssClass="txtField" name="districtName" id="districtName" headerKey="0" headerValue="Select District Name" key="label.districtName" required="true" onchange="fetchCityNames();"></s:select></td>
		</s:else>
		<s:if test="%{#session.cityNameDropDown == null}">
		<s:set name="districtNameDropDown" value="" scope="session"/>
		<s:set name="cityNameDropDown" value="" scope="session"/>
		<td class="textRight" width="20%"><s:label value="%{getText('label.cityName')}"></s:label>:</td>
		<td class="text" width="30%"><select name="cityName" ><option value="" selected="selected">Select City Name</option></select></td>		
		</s:if>
		<s:else>
		<td class="textRight" width="20%"><s:label value="%{getText('label.cityName')}"></s:label>:</td>
		<td class="text" width="30%"><s:select label="cityName" list="#session.cityNameDropDown" cssClass="txtField" name="cityName" id="cityName" headerKey="0" headerValue="Select City Name" key="label.districtName" required="true" onchange=""></s:select></td>
		</s:else>
	</tr>	
	<tr>
		<td class="textRight" width="20%"><s:label value="%{getText('label.partyName')}"></s:label>:</td>
		<td class="text" width="30%"><s:textfield name="branchName" id="branchName" onKeyPress='return notAllowCheck(this,event);' ></s:textfield><input type="button" value="Fetch" onclick="callFetchBranchName()"> </td>
		<td class="textRight" width="20%"><s:label value="%{getText('label.partyIFSC')}"></s:label>:</td>
		<td class="text" width="30%"><s:textfield name="ifscCode" id="ifscCode" onKeyPress='return notAllowCheck(this,event);' ></s:textfield><input type="button" value="Fetch" onclick="callFetchIFSCCodes()"> </td>
	</tr>
</table>		
		
	 
	<div class="clearfix"></div>
	<table width="100%" cellpadding="0" cellspacing="0" border="0">
		<tr>
			<td align="center">
				<input type="button" value="Submit" onclick="getIFSCDetails()" class="btn">
				<input type="reset" value="Reset" onclick="cancel()" class="btn"  />				
				
			</td>
		</tr>
	</table>
		
		<table width="100%" cellpadding="0" cellspacing="0" border="0"
			bgcolor="#ffffff" align="center">

			<tr>
				<td><br/>
				
				<div style="overflow: auto; height: 245px; width: 100%;border:0px solid #ff0000;">
					<display:table uid="row" name="sessionScope.IfscListData" pagesize="10" requestURI="/getIFSCDetailsData" cellpadding="3"
				cellspacing="0" class="dataGrid">
				<display:setProperty name="basic.msg.empty_list" 
    				value='<div class="infoMsg"><span>Nothing found to display</span></div>' />
				<display etProperty name="css.tr.even" value="TDBGlightgray" />
				<display etProperty name="css.tr.odd" value="TDBGgray" cssClass="odd"/>	
				
				<display:column	 title="IFSC Code" property="ifscCode"  headerClass="gridHdrBg"  sortable="false" style="text-align:center"/>
				<display:column	 title="IFSC Type" property="ifscType" headerClass="gridHdrBg"  sortable="false" style="text-align:center"/>
				<display:column  title="MICR Code" property="micrCode" headerClass="gridHdrBg" style="text-align:center" />
				<display:column	 title="Bank Name" property="partyName"  headerClass="gridHdrBg"  sortable="false" style="text-align:center"/>
				<display:column	 title="Branch Name" property="branchName"  headerClass="gridHdrBg"  sortable="false" style="text-align:center"/>
				<display:column  title="Branch Address" property="partyAddress" headerClass="gridHdrBg" style="text-align:center"/>
				<display:column	 title="State" property="stateName" headerClass="gridHdrBg"  sortable="false" style="text-align:center"/>
				<display:column  title="District" property="districName" headerClass="gridHdrBg" style="text-align:center"/>
				<display:column	 title="City" property="cityName"  headerClass="gridHdrBg"  sortable="false" style="text-align:center"/>
				
				
			</display:table>
				
				
				
				</div></td>
			</tr>
		</table>
		</td>-->
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
