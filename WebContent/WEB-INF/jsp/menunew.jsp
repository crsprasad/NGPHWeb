<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fx" %>

<%
response.setHeader("Cache-Control","max-age=0");
response.setHeader("Cache-Control","no-cache");
response.setHeader("Pragma","public");
response.setHeader("Pragma","no-cache");
response.setDateHeader("Expires", 0);
%>
<div id="menu">
	<div id="menuPanel">
		<sx:tree id='PaymentList' label="PaymentList" >
 			<c:forEach var="groupName" items="%{session.FUNCTION_GROUP_MAP}">
 				<sx:treenode label="%{groupName.value}"/>
 			</c:forEach>
				</sx:tree>
	</div>
</div>