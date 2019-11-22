<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<script type="text/javascript">

	

function callRedirect(){

	var aa= document.getElementById("EIConnectionType").value;
	alert(aa)
	if(aa!=null){
	document.forms[0].action= "displayHostDetails";
	document.forms[0].submit();
	}
}
function callSearchID(Action) {

	document.getElementById("searchAction").value = Action;
		
		
		window.open(
				"<s:url action='showEI_IDSearchScreen' windowState='EXCLUSIVE' />",
				'mywindow', 'top=50,left=250,width=750,height=410');
	}
function callSubmit(saveAction) {
	document.getElementById("saveAction").value = saveAction;
	document.forms[0].action="doProcess";
	document.forms[0].submit();
}
function cancel() {
	document.forms[0].action = "genericScreenFetchValues";
	document.forms[0].submit();
}
</script>
<sx:head parseContent="true" />
<s:head />
<body onload="callConnectionType()">
<div id="contentPane">
<div id="contentPaneTop"></div>
<div id="contentPaneFluid">
<div id="contentPaneInternal"><div><s:label name="message"></s:label><s:if test="hasFieldErrors()">
				<div class="errorMsg"><span class="errorMsgInline"><s:actionerror /><s:fielderror /></span></div>
			</s:if></div></div>
<div id="content">
<h1>Maintaining EI</h1>

<s:form method="post" action="esbXMLCreator" name ="form">
<s:hidden id="searchAction" name="searchAction"></s:hidden>
<s:hidden id="saveAction" name="saveAction"></s:hidden>
	<s:hidden id="hiddenTxnRef" name="hiddenTxnRef"></s:hidden>
	        <table width="70%"  border="0" cellpadding="5" cellspacing="1" class="gridHdrBg">
          <tr>
            <td  class="textRight"><s:label value="EI ID:"></s:label>&nbsp;:</td>
            <td class="text">
           <s:textfield name="EI_ID" cssClass="txtField" id="EI_ID" required="true"></s:textfield><input type="button" value="Search EI ID..."
						onclick="callSearchID('RULEID')" class="btn" />
					</td>
             <td width="8%" class="textRight"><s:label value="EI Host Category:"></s:label>&nbsp;</td>
            <td width="62%" class="text"><s:select list="{'ATM','Mobile','Internet'}" name="EI_Host_Category" id="EI_Host_Category" headerKey="" headerValue="Select Type" required="true"></s:select></td>
            
            </tr>
          <tr>

            <td class="textRight"><s:label value="EI Name:"></s:label>&nbsp;</td>
            <td class="text"><s:textfield name="EI_Name" cssClass="txtField" id="EI_Name" required="true"></s:textfield></td>
            <td width="8%" class="textRight"><s:label value="EI Type:"></s:label>&nbsp;</td>
            <td width="62%" class="text"><s:select list="#{'H':'Host','P':'Payment System'}" name="EIType" id="EIType" headerKey="" headerValue="Select Type" required="true"></s:select></td>

          </tr>
          <tr>
            <td class="textRight"><s:label value="Gateway Name:"></s:label>&nbsp;</td>
            <td class="text"><s:textfield name="EI_Gateway" cssClass="txtField" id="EI_Gateway" required="true"></s:textfield></td>
             <td width="8%" class="textRight"><s:label value="EI Format:"></s:label>&nbsp;</td>
            <td width="62%" class="text"><s:select list="#session.EI_Format_Drop" name="EI_Format"  id="EI_Format" headerKey="" headerValue="Select Type" required="true"></s:select></td>
            
          </tr>
          <tr>
            <td class="textRight"><s:label value="Connection Type:"></s:label>&nbsp;</td>
            <td class="text">

              <s:select list="{'MQ','Files','Web Service'}" name="EIConnectionType" id="EIConnectionType" headerKey=""
				headerValue="Select Connection Type" required="true" onchange="callRedirect()"></s:select></td>
				<td width="8%" class="textRight"><s:label value="Rule Action"></s:label> </td>
				<td width="62%" class="text">
											<s:radio list="{'ADD','Modify','delete'}"
												id="SaveModifyOrDelete" cssClass="dropDown"
												
												name="SaveModifyOrDelete"  onclick="callDisableOnDelete(this.form)"></s:radio></td>
												
          </tr>
          
          <tr>
            <td colspan="4" class="textCenter">
            <br />
            <s:if test="%{displayNextScreen=='MQ'}">
            <table width="50%" border="0" align="center" cellpadding="5" cellspacing="1" class="gridHdrBg">

              <tr>
                <td class="textRight"><s:label value="MQ Queue Manager Name:"></s:label>&nbsp;</td>
                <td class="text"><s:textfield name="MQ_Manager_Name" cssClass="txtField" id="MQ_Manager_Name" required="true"></s:textfield></td>
              </tr>
              <tr>
                <td class="textRight"><s:label value="MQ Server Name / IP:"></s:label>&nbsp;</td>
                <td class="text"><s:textfield name="MQ_Server_ID" cssClass="txtField" id="MQ_Server_ID" required="true"></s:textfield></td>
              </tr>

              <tr>
                <td class="textRight"><s:label value="MQ Server Port:"></s:label>&nbsp;</td>
                <td class="text"><s:textfield name="MQ_Server_Port" cssClass="txtField" id="MQ_Server_Port" required="true"></s:textfield></td>
              </tr>
              <tr>
                <td class="textRight"><s:label value="MQ Queue Name:"></s:label>&nbsp;</td>
                <td class="text"><s:textfield name="MQ_Queue_Name" cssClass="txtField" id="MQ_Queue_Name" required="true"></s:textfield></td>
              </tr>
              <tr>
                <td class="textRight"><s:label value="Client Connection Channel:"></s:label>&nbsp;</td>
                <td class="text"><s:textfield name="Client_Connection_Channel" cssClass="txtField" id="Client_Connection_Channel" required="true"></s:textfield></td>
              </tr>

            </table></s:if>
            <s:if test="%{displayNextScreen=='Files'}">
             <table width="50%" border="0" align="center" cellpadding="5" cellspacing="1" class="gridHdrBg">

              <tr>
                <td class="textRight"><s:label value="File Path :"></s:label>&nbsp;</td>
                <td class="text"><s:textfield name="FsFilePath" cssClass="txtField" id="FsFilePath" required="true"></s:textfield></td>
              </tr>
              </table>
            </s:if>
             <s:if test="%{displayNextScreen=='Web Service'}">
             <table width="50%" border="0" align="center" cellpadding="5" cellspacing="1" class="gridHdrBg">

              <tr>
                <td class="textRight"><s:label value="URL :"></s:label>&nbsp;</td>
                <td class="text"><s:textfield name="webServiceURL" cssClass="txtField" id="wServiceURL" required="true"></s:textfield></td>
              </tr>
              <tr>
                <td class="textRight"><s:label value="Method :"></s:label>&nbsp;</td>
                <td class="text"><s:textfield name="webServiceMethod" cssClass="txtField" id="wServiceMethod" required="true"></s:textfield></td>
              </tr>
              </table>
            </s:if>
            
            <br />
            <s:if test="%{checkForSubmit!='Display_Approve_Reject'}">
            <input type="button"  class="btn" id="button" value="Submit" onclick="callSubmit('Save')" >
            
            <input name="button2" type="reset" class="btn" id="button2" value="Cancel" /></s:if>
            <s:if test="%{checkForSubmit=='Display_Approve_Reject'}">
            	<s:if test="%{validUserToApprove}">
					<input type="button" value="Approve" onclick="callSubmit('Approve')">
					<input type="button" value="Reject" onclick="callSubmit('Reject')">
					<input type="button" value="Cancel" onclick="cancel()">
				</s:if>
				<s:if test="%{!validUserToApprove}">
					<input type="button" value="Approve" onclick="callSubmit('Approve')" disabled="disabled">
					<input type="button" value="Reject" onclick="callSubmit('Reject')" disabled="disabled">
					
				</s:if>
			</s:if>  </td>
          </tr>
        </table>
        	

</s:form></div>
</div>
</div>
<div id="contentPaneBottom"></div>
</body>