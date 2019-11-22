<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="/struts-tags" prefix="s"%>
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Quick Stacks</title>

<script type="text/javascript" src="https://www.google.com/jsapi"></script>   
	<script type="text/javascript">
	google.load("visualization", "1", {packages:["corechart"]}); 
	 google.setOnLoadCallback(callPieForInbound); 
	 google.setOnLoadCallback(callPieForOutbound);  
	 google.setOnLoadCallback(callPieForChannels);
		
		function callPieForInbound()
	{
		
	
		var stringvalue=document.getElementById('inboundcurrency').value;
		 var data = new google.visualization.DataTable();
		 alert(stringvalue);
		var currencyArray=new Array();
		currencyArray=stringvalue.split(",");
		
		
		data.addColumn('string', 'Task');
		data.addColumn('number', 'Hours per Day');
		 data.addRows(currencyArray.length);
		
		var amountcurrency = new Array();
		for(var i=0;i<currencyArray.length;i++){
				
			
				amountcurrency=currencyArray[i].split("=");
				
				data.setValue(i, 0, amountcurrency[0]);
				
				data.setValue(i, 1, (parseInt(amountcurrency[1])));
				
		}
		 
	
	 var chart = new google.visualization.PieChart(document.getElementById('chart_div'));
	 chart.draw(data, {width: 450, height: 300, title: 'In Bound Traffic'});
	}
	 function callPieForOutbound()
		{
			
		
			var stringvalue=document.getElementById('outboundcurrency').value;
			 var data = new google.visualization.DataTable();
			 alert(stringvalue);
			var currencyArray=new Array();
			currencyArray=stringvalue.split(",");
			
			
			data.addColumn('string', 'Task');
			data.addColumn('number', 'Hours per Day');
			 data.addRows(currencyArray.length);
			
			var amountcurrency = new Array();
			for(var i=0;i<currencyArray.length;i++){
					
				
					amountcurrency=currencyArray[i].split("=");
					
					data.setValue(i, 0, amountcurrency[0]);
					
					data.setValue(i, 1, (parseInt(amountcurrency[1])));
					
			}
			 
		 
		 var chart = new google.visualization.PieChart(document.getElementById('chart_div1'));
		 chart.draw(data, {width: 450, height: 300, title: 'Out Bound Traffic'});
		
		}
	 function callPieForChannels()
		{
			
		
			var stringvalue=document.getElementById('channeldata').value;
			alert(stringvalue);
			 var data = new google.visualization.DataTable();
			var currencyArray=new Array();
			currencyArray=stringvalue.split(",");
			
			
			data.addColumn('string', 'Task');
			data.addColumn('number', 'Hours per Day');
			 data.addRows(currencyArray.length);
			
			var amountcurrency = new Array();
			for(var i=0;i<currencyArray.length;i++){
					
				
					amountcurrency=currencyArray[i].split("=");
					
					data.setValue(i, 0, amountcurrency[0]);
					
					data.setValue(i, 1, (parseInt(amountcurrency[1])));
					
			}
			 
		 
		 var chart = new google.visualization.PieChart(document.getElementById('chart_div2'));
		 chart.draw(data, {width: 450, height: 300, title: 'Channels'});
		
		}
	
	</script>
</head>
<body>
<s:hidden id="inboundcurrency" name="inboundcurrency" ></s:hidden>
<s:hidden id="outboundcurrency" name="outboundcurrency" ></s:hidden>
<s:hidden id="channeldata" name="channeldata" ></s:hidden>
	
 <div style="width:300px; width: 350px;    left: 10px;" id="chart_div"></div> 
 <div style="width:300px; height: 250px;  position:fixed; top: 20px; left: 330px; " id="chart_div1"></div>
 <div style="width:300px; height: 250px;  position:fixed; top: 20px; left: 650px; " id="chart_div2"></div>
<s:form name="quickStacks" action="quickStackAction" >
 

<table  cellpadding="7" cellspacing="7" width="400" frame="void">
<tr><td align="center"">INBOUND DIRECTION STATUS </td></tr>
	<tr>
		
		<td>
		
			<s:label name="InboundMessage" id="InboundMessage" key="label.InboundMessages" ></s:label>
		</td>
	</tr>
	<tr>
		
		<td>
			<s:label name="authorizedmessge" id="authorizedmessge" key="label.InAuthorizeMessage" ></s:label>
		</td>
	</tr>
	<tr>
		
		<td>
			<s:label name="recivedathorization" id="recivedathorization" key="label.ReceivedState" ></s:label>
		</td>
	</tr>
	<tr>
		
		<td>
			<s:label name="sendToHost" id="sendToHost" key="label.InSentHost" ></s:label>
		</td>
	</tr>
	<s:iterator value="total">
	<tr>
		
		<td>
			<s:property  />
		</td>
	</tr>
	</s:iterator>

</table>

<table  cellpadding="7" cellspacing="7" width="400">
<tr><td>OUTBOUND DIRECTION STATUS</td></tr>
	<tr>
		
			<s:label name="OutboundMessage" id="OutboundMessage" key="label.OutAuthorizeMessage" ></s:label>
		
	</tr>
	<tr>
		
		<td>
			<s:label name="authorizedmessgeOut" id="authorizedmessgeOut" key="label.OutAuthorizeMessage" ></s:label>
		</td>
	</tr>
	<tr>
		
		<td>
			<s:label name="recivedathorizationOut" id="recivedathorizationOut" key="label.OutReceivedState" ></s:label>
		</td>
	</tr>
	<tr>
		
		<td>
			<s:label name="releasedState" id="releasedState" key="label.OutReleased" ></s:label>
		</td>
	</tr>
	<s:iterator value="totalOut">
	<tr>
		
		<td>
			<s:property  />
		</td>
	</tr>
	</s:iterator>

</table>	
</s:form>

</body>
</html>