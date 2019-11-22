<%@taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<%
response.setHeader("Cache-Control","max-age=0");
response.setHeader("Cache-Control","no-cache");
response.setHeader("Pragma","public");
response.setHeader("Pragma","no-cache");
response.setDateHeader("Expires", 0);
%>

<div id="header">
<div id="headerInternal">
		<div id="logo"><img src="images/logo.png" width="143" height="82" alt="Q-NG Logo" /></div>
		<div id="topNavPanel"><b>User:<s:label value="%{session.UserName}"></s:label>&nbsp;</b><b>CurrentLogin:<s:label value="%{session.currentLoginTime}"></s:label>&nbsp;</b><b>LastLogin:<s:label value="%{session.lastLoginTime}"></s:label>&nbsp;</b><b>BusinessDay:<s:label value="%{session.businessDay}"></s:label>&nbsp;</b></div>
		