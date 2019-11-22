<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<%@taglib uri="http://displaytag.sf.net" prefix="display"%>
<div id="contentPane">
<div id="contentPaneTop"></div>
<div id="contentPaneFluid">
<div id="contentPaneInternal">
<s:form method="post">
<s:if test="hasFieldErrors()">
		<div class="errorMsg"><span class="errorMsgInline"><s:iterator
			value="getFieldErrors().entrySet()" var="entry">
			<s:iterator value="#entry.value">
				<s:property value="%{top}" />
				<br />
			</s:iterator>
		</s:iterator></span></div>
	</s:if>
	
	
	<br/>
	<!--Error Msg Div Starts -->
	<div class="normalMsg"><span>Operation completed successfully</span></div>
	<br/><br/>
	<table width="100%" cellpadding="3" cellspacing="1" border="0">
	<tr>
		<td><display:table uid="row" name="sessionScope.searchList" pagesize="10" requestURI="/success" cellpadding="3"
				cellspacing="0" class="dataGrid">
				<display:setProperty name="basic.msg.empty_list" 
    				value='<div class="infoMsg"><span>Nothing found to display</span></div>' />
				<display etProperty name="css.tr.even" value="TDBGlightgray" />
				<display etProperty name="css.tr.odd" value="TDBGgray" cssClass="odd"/>

				<display:column title="Mobile No" property="mobileNo"
					headerClass="gridHdrBg"  sortable="false" style="text-align:center" />

				<display:column title="MMID" property="MMID"
					headerClass="gridHdrBg"  sortable="false" style="text-align:center"/>
					
			</display:table></td>
		</tr></table>
	<!--Error Msg Div Ends -->
</s:form></div>
</div>
<div id="contentPaneBottom"></div>
</div>