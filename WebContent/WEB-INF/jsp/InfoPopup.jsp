<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="com.logica.ngph.web.utils.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="/struts-tags" prefix="s"%>


<div id="contentPane">
<div id="contentPaneTop"></div>
<div id="contentPaneFluid">
<div id="contentPaneInternal"><span class="crumbs">
<s:form action="displayInformation">
	<s:hidden id="action" name="action" />
	<s:hidden id="infoDirection" name="infoDirection" />
	<div>
		<s:if test="hasFieldErrors() || hasActionErrors()">
			<div class="errorMsg">
				<span class="errorMsgInline">
					<s:actionerror /><s:fielderror />
				</span>
			</div>
		</s:if>
	</div>
<table width="100%" cellpadding="3" cellspacing="1" border="2" bgcolor="#ffffff">
		<td width="10%" class="textRight"><s:label value="Info Message"></s:label></td>
		<td align="left"><s:textarea name ="rawMsg" id="rawMsg" rows="25" cols="160" style="overflow: scroll; resize: none;" wrap="HARD" readonly="true"></s:textarea>
	</td>
	</tr>
	<br>
	<tr>
	<td width="10%" /> 
	<td align="center"><input type="button" value="Back" onclick="Back();"/></td>
	</tr>
</table>
</s:form>
</div>
</div>
</div>
<div id="contentPaneBottom"></div>
<script type="text/javascript">

function Back()
{
	var direction = document.getElementById("infoDirection").value;
	if(direction=="O")
	{
		document.forms[0].action="displayoutboundInfoScreenoutInfo";
		document.forms[0].submit();
	}
	else
	{
		document.forms[0].action="displayInboundInfoScreen";
		document.forms[0].submit();
	}

	}


</script>