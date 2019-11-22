<%@page import="com.logica.ngph.web.action.BannerAction"%>
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="/struts-tags" prefix="s"%>

<%@taglib uri="http://displaytag.sf.net" prefix="display" %>
 	
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Banner</title>

<script type="text/javascript">

	
	function ShowHide(divId)
	{
		if(document.getElementById(divId).style.display == 'none')
			{
				document.getElementById(divId).style.display='block';
			}
		else{
				document.getElementById(divId).style.display = 'none';
			}
	}
	
	window.onload = setupRefresh;

    function setupRefresh() {
      setTimeout("refreshPage();", 30000); // milliseconds
    }
    function refreshPage() {
       window.location = location.href;
    }


</script>




</head>
<body>
<div id="autorefresh">
<marquee id="scroller" width=500 height=25  style="background-color:#9E7BFF; border:0px solid black">
<s:label name="bannerDetails" ></s:label></marquee>
<input type="button" value="More News >> " onclick ="javascript:ShowHide('HiddenDiv')" >

<div class="mid" id="HiddenDiv" style="DISPLAY: none" >
<s:textfield name="bannerTxtField" id="txt" size="90" ></s:textfield>

<img alt="" src="c:/normal.gif" width="10" height="10">
</div>
</div>
</body>

</html>