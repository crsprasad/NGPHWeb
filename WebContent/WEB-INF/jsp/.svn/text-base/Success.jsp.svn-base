<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
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
	
	
	
	<!--Error Msg Div Starts -->
	<div class="normalMsg"><span>Operation completed successfully</span></div>
	<!--Error Msg Div Ends -->
</s:form></div>
</div>
<div id="contentPaneBottom"></div>
</div>