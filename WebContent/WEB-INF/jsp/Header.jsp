<%@taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<%
response.setHeader("Cache-Control","max-age=0");
response.setHeader("Cache-Control","no-cache");
response.setHeader("Pragma","public");
response.setHeader("Pragma","no-cache");
response.setDateHeader("Expires", 0);
%>
<%! int idleTImeout=0; %>
<% idleTImeout = request.getSession().getMaxInactiveInterval(); %>

<script type="text/javascript">

      function displayHelpPopup(id) {
    	  sessionTimer();
	   window.open("<s:url action='getHelpData' windowState='EXCLUSIVE' />",
				'mywindow', 'top=150,left=400,width=500,height=250');
	}

      function sessionTimer()
      {
          alert("Hi");
          var idleTImeout = "<%=idleTImeout%>"; 
          alert("idleTImeout is "+idleTImeout);
    	var currSessionTime = new Date();
        var min = (idleTImeout/60)-1;
        var sec = 60;
        var time ;
        calcTimeout();

        function calcTimeout()
        {
        	
	        if (parseInt(sec) > 0) 
	            {
	  
	          	  	sec = parseInt(sec) - 1;
	          	  alert("Your Left Time  is :" + min + " Minutes ," + sec + " Seconds");
	          	  document.getElementById("showtimer").innerHTML = "Your Left Time  is :" + min + " Minutes ," + sec + " Seconds";
	            	time = setTimeout("calcTimeout()", 1000);
	        	}
	        else
	          	{
		        	if (parseInt(sec) == 0) 
		            {
		                min = parseInt(min) - 1;
		                if (parseInt(min) == 0) 
		                {
		                    //clearTimeout(time);
		                }
		                else 
			            {
		                    sec = 60;
		                    alert("Your Left Time  is :" + min + " Minutes ," + sec + " Seconds");
		                    document.getElementById("showtimer").innerHTML = "Your Left Time  is :" + min + " Minutes ," + sec + " Seconds";
		                    time = setTimeout("calcTimeout()", 1000);
		                }
		            }
	          	}	
          	}
      }
      
</script>

<div id="header">
	<div id="headerInternal">
		<div id="logo"><img src="images/logo.png" width="143" height="82" alt="Q-NG Logo" /></div>
		<div id="topNavPanel"><b>User:<s:label value="%{session.UserName}"></s:label>&nbsp;</b><b>CurrentLogin:<s:label value="%{session.currentLoginTime}"></s:label>&nbsp;</b><b>LastLogin:<s:label value="%{session.lastLoginTime}"></s:label>&nbsp;</b><b>System Date:<s:label value="%{session.businessDay}"></s:label>&nbsp;</b></div>	
		<div id="menuPanel">
			<ul id="menu">
				<!-- Outbound Menu Starts -->
				<li class="level1-li outMsg"><a class="level1-a" href="#"> </a>
					<div class="listHolder col1">
						<!--First Column Starts-->
						<div class="listCol">
							<ul>
								<li><a id='<s:property value="getText('function.access.receivedoutbound')" />' href="<s:url action="receiveOutboundAction"/>">Received</a></li>
								<li><a id='<s:property value="getText('function.access.awaitingrepairoutbound')" />' href="<s:url action="awaitingRepairOutboundAction"/>" >Awaiting Repair</a></li>
								<!--<li><a id='<s:property value="getText('function.access.awaitingAuthorization')" />' href="<s:url action="awaitingAuthorisationOutboundAction"/>" >Awaiting Authorisation</a></li>
								--><li><a id='<s:property value="getText('function.access.rejectedbychanneloutbound')" />' href="<s:url action="rejectedByChannelOutboundAction"/>" >Rejected by Channel</a></li>
								<li><a id='<s:property value="getText('function.access.exceptionoutbound')" />' href="<s:url action="exceptionsAction"/>" >Exceptions</a></li>
								<li><a id='<s:property value="getText('function.access.completedoutbound')" />' href="<s:url action="completedOutboundAction"/>" >Completed</a></li>
								<li><a id='<s:property value="getText('function.access.errormessagesoutbound')" />' href="<s:url action="executeinvalidMessages"/>">Error Messages</a></li>
								<li><a id='<s:property value="getText('function.access.awaitingacknowledgementoutbound')" />' href="<s:url action="awaitingAcknowledgementOutboundAction"/>" >Awaiting Acknowledgement</a></li>
							<li><a id='<s:property value="getText('function.access.awaitingreleaseoutbound')" />' href="<s:url action="awaitingReleaseOutboundAction"/>" >Awaiting Release</a></li>
								<li><a id='<s:property value="getText('function.access.rejectedByUserOutboundAction')" />' href="<s:url action="rejectedByUserOutboundAction"/>" >Rejected by User</a></li>
								<li><a id='<s:property value="getText('function.access.pendingAuthorizationOutboundAction')" />' href="<s:url action="pendingAuthorizationOutboundAction"/>" >Pending Authorization</a></li>
								
								<!--<li><a id='<s:property value="getText('function.access.paymententry')" />' href="<s:url action="loadPaymentStatus"/>">Payment Entry</a></li>
								--><!--<li><a id='<s:property value="getText('function.access.awaitingreleaseoutbound')" />' href="<s:url action="awaitingReleaseOutboundAction"/>" >Awaiting Release</a></li>
								<li><a id='<s:property value="getText('function.access.warehousedoutbound')" />' href="<s:url action="warehouseOutboundAction"/>" >Warehoused</a></li>
								<s:url action="getPaymentMessageQueue" var="urlTag"><s:param name="paymentStatus">EXCEPTIONS;O</s:param></s:url>
								<li><a id='<s:property value="getText('function.access.returnedoutbound')" />' href="<s:url action="returnedOutboundAction"/>" >Returned</a></li>	
								-->
							</ul>
						</div>
						<!--First Column Ends-->
		
						<!--Second Column Starts-->
						
						<div class="listCol">
							<ul>
								<li><a id='<s:property value="getText('function.access.informationoutbound')" />' href="<s:url action="displayoutboundInfoScreenoutInfo"/>">Information</a></li>
								<li><a id='<s:property value="getText('function.access.lcopen')" />' href="<s:url action="displayLCOpenScreenlcOpen"/>">LC Open (MT-700)</a></li>
								<li><a id='<s:property value="getText('function.access.amendlc')" />' href="<s:url action="displayAmendLCadmendLCPage"/>">Amend LC (MT-707)</a></li>
								<li><a id='<s:property value="getText('function.access.acknowledgelc')" />' href="<s:url action="displayLcAcknowledgementLCAcknowledgement"/>">LC Acknowledgement (MT-730)</a></li>
								<li><a id='<s:property value="getText('function.access.adviceofrefusal')" />' href="<s:url action="displayAdviceofRefusalAdviceofRefusalAction"/>">Advice of Refusal (MT-734)</a></li>
								<li><a id='<s:property value="getText('function.access.authorisepaymentadvice')" />' href="<s:url action="displayAuthoriseLcPaymentAdviceauthoriseLcPaymentAdvi"/>">Authorization To Reimburse (MT-740)</a></li>
								<li><a id='<s:property value="getText('function.access.adviceofdiscrepancy')" />' href="<s:url action="displayAdviceofDiscrepancy"/>">Advice of Discrepancy (MT-750)</a></li>
								<li><a id='<s:property value="getText('function.access.adviceofpayment')" />' href="<s:url action="displayAdviceofPayment"/>">Advice of Payment (MT-754)</a></li>
								<li><a id='<s:property value="getText('function.access.advicepayment')" />' href="<s:url action="displayLcAdvicingadvicePayment"/>">Advice Reimbursement (MT-756)</a></li>
								
								
									<!--<li><a id='<s:property value="getText('function.access.createimpspayment')" />' href="<s:url action="initalActionMMIDmmidManager"/>" >CreateIMPSPayment</a></li>
									--><!--<li><a id='<s:property value="getText('function.access.returnimpspayments')" />' href="<s:url action="displayReturnTransactionreturnImpsTrasaction"/>">Return IMPS Payments</a></li>
								--><!--	<li><a id='<s:property value="getText('function.access.invalidoutbound')" />' href="<s:url action="invalidOutboundAction"/>" >Invalids</a></li>
									
									<li><a id='<s:property value="getText('function.access.finalisedoutbound')" />' href="<s:url action="finalisedOutboundAction"/>" >Finalised</a></li>
									<li><a id='<s:property value="getText('function.access.rejectedoutbound')" />' href="<s:url action="rejectedOutboundAction"/>" >Rejected</a></li>
								-->
							</ul> 
						</div>
						
						<!--Second Column Ends-->
				
						<!-- Third Column Starts-->
						
						<div class="listCol">
							<ul>
							<li><a id='<s:property value="getText('function.access.createbankguarantee')" />' href="<s:url action="createBankGuaranteecreateBankGuaranteeAction"/>">Create Bank Guarantee (MT-760)</a></li>
							<li><a id='<s:property value="getText('function.access.amendbg')" />' href="<s:url action="displayAmendBGamendBGPage"/>">Amend BG (MT-767)</a></li>
							<li><a id='<s:property value="getText('function.access.bgacknowledgement')" />' href="<s:url action="displayAckBGackBGPage"/>">BG Acknowledgement (MT-768)</a></li>
							<li><a id='<s:property value="getText('function.access.createbgcover')" />' href="<s:url action="displaycovBGcovBGPage"/>">Create BG Cover (MT-760 COVER)</a></li>
							<li><a id='<s:property value="getText('function.access.createamendbgcover')" />' href="<s:url action="displayAmdCoverBGAmdCoverBGPage"/>">Create Amend BG Cover (MT-767 COVER)</a></li>
							<li><a id='<s:property value="getText('function.access.adviceofreduction')" />' href="<s:url action="displayadviceofreductionAdviceofReduction"/>">Advice of Reduction (MT-769)</a></li>
							<li><a id='<s:property value="getText('function.access.freeformatmessage')" />' href="<s:url action="displayfreeFormatMessageFreeFormatMessage"/>">Free Format Message (MT-799)</a></li>
							<!--<li><a id='<s:property value="getText('function.access.amendbg')" />' href="<s:url action="displayAmendBGamendBGPage"/>">Amend BG</a></li>
								<li><a id='<s:property value="getText('function.access.preadvice')" />' href="<s:url action="getCreditDetailsletterOfCredit"/>">LC Pre Advice</a></li>
								<li><a id='<s:property value="getText('function.access.lcopen')" />' href="<s:url action="displayLCOpenScreenlcOpen"/>">LC Open</a></li>
								<li><a id='<s:property value="getText('function.access.createbankguarantee')" />' href="<s:url action="createBankGuaranteecreateBankGuaranteeAction"/>">Create Bank Guarantee</a></li>
								<li><a id='<s:property value="getText('function.access.createimpspayment')" />' href="<s:url action="initalActionMMIDmmidManager"/>" >CreateIMPSPayment</a></li>
								<li><a id='<s:property value="getText('function.access.returnimpspayments')" />' href="<s:url action="displayReturnTransactionreturnImpsTrasaction"/>">Return IMPS Payments</a></li>-->
								<!-- <li><a id='<s:property value="getText('function.access.marklcacknowledgment')" />' href="<s:url action="displayMarkAckmarkAck"/>">Mark LC Acknowledgment</a></li>
								<li><a id='<s:property value="getText('function.access.advicepayment')" />' href="<s:url action="displayLcAdvicingadvicePayment"/>">Advice Payment</a></li>
							</ul>-->	
						</div>
										
						<!--Third Column Ends-->
						
						<!-- fourth Column Starts-->
						
						<div class="listCol">
							<ul>
								<!--<li><a id='<s:property value="getText('function.access.authorisepaymentadvice')" />' href="<s:url action="displayAuthoriseLcPaymentAdviceauthoriseLcPaymentAdvi"/>">Authorise Payment Advice</a></li>
								<li><a id='<s:property value="getText('function.access.amendlc')" />' href="<s:url action="displayAmendLCadmendLCPage"/>">Amend LC</a></li> 
								<li><a id='<s:property value="getText('function.access.createimpspayment')" />' href="<s:url action="initalActionMMIDmmidManager"/>" >CreateIMPSPayment</a></li>
								<li><a id='<s:property value="getText('function.access.returnimpspayments')" />' href="<s:url action="displayReturnTransactionreturnImpsTrasaction"/>">Return IMPS Payments</a></li>-->
							</ul>
						</div>
						
						<!--fourth Column Ends-->
					</div>
				</li>
				<!-- Outbound Menu Ends -->
			
				<!-- Inbound Menu Starts -->
				<li class="level1-li inMsg"><a class="level1-a" href="#"> <!--[if gte IE 7]><!--></a><!--<![endif]--><!--[if lte IE 6]><table><tr><td><![endif]-->
					<div class="listHolder col2">
						<!--First Column of Menu Starts-->
						<div class="listCol">
							<ul>		
								<!--
								<li><a id='<s:property value="getText('function.access.intervenedInbound')" />' href="<s:url action="intervenedInboundAction"/>" >Intervened</a></li>
								-->
								<s:url action="sentToHostAction" var="urlTag"></s:url>
								<li><a id='<s:property value="getText('function.access.senttohost')" />' href="<s:property value="#urlTag" />">Sent to Host</a></li>							
								<s:url action="exceptionsAction" var="urlTag"></s:url>
								<li><a id='<s:property value="getText('function.access.exception')" />' href="<s:url action="exceptionsInAction"/>" >Exceptions</a></li>
								<li><a id='<s:property value="getText('function.access.informationinbound')" />' href="<s:url action="displayInboundInfoScreen"/>" >Information</a></li>
								<!--
								<li><a id='<s:property value="getText('function.access.processedManuallyInbound')" />' href="<s:url action="processedManuallyInboundAction"/>" >Processed Manually</a></li>
								<li><a id='<s:property value="getText('function.access.returnedInbound')" />' href="<s:url action="returnedInboundAction"/>" >Returned</a></li>
								<s:url action="rejectedAction" var="urlTag"></s:url>
								<li><a id='<s:property value="getText('function.access.rejected')" />' href="<s:property value="#urlTag" />">Rejected</a></li>
								-->
							</ul>
						</div>
						<!--First Column of Menu Ends--> 
				
						<!--Second Column of Menu Starts-->						
						<!--
						<div class="listCol">
							<ul>
								<li><a id='<s:property value="getText('function.access.awaitingAccountingInbound')" />' href="<s:url action="awaitingAccountingInboundAction"/>" >Awaiting Accounting</a></li>
								<li><a id='<s:property value="getText('function.access.accountingCompletedInbound')" />' href="<s:url action="accountingCompletedInboundAction"/>" >Accounting Completed</a></li>
								<li><a id='<s:property value="getText('function.access.amlCompletedInbound')" />' href="<s:url action="amlCompletedInboundAction"/>" >AML Completed</a></li>
								<li><a id='<s:property value="getText('function.access.awaitingBillingInbound')" />' href="<s:url action="awaitingBillingInboundAction"/>" >Awaiting Billing</a></li>
								<li><a id='<s:property value="getText('function.access.billingCompletedInbound')" />' href="<s:url action="billingCompetedInboundAction"/>" >Billing Completed</a></li>
							</ul>
						</div>	
						-->
						<!--Second Column of Menu Ends--> 
						
						<!--Third Column of Menu Starts-->
							<!--
							<div class="listCol">
								<ul>			
								</ul>
							</div>
							-->
						<!--Third Column of Menu Ends-->
					</div>
				</li>
				<!-- Inbound Menu Ends -->
				
				<!-- Enquiries & Reports Start -->
				<li class="level1-li rep"><a class="level1-a" href="#"> </a>
					<div class="listHolder col3">
						<!--First Column Starts-->
						<div class="listCol">
							<ul>
								<li><a id='<s:property value="getText('function.access.paymentenquiry')" />' href="<s:url action="callLoadToDropDownenquiryAction"/>">Payment Enquiry</a></li>
								<li><a id='<s:property value="getText('function.access.STATUSMONITOR')" />' href="<s:url action="displayStatusMonitorstatusMonitor"/>">Status Monitor</a></li>
								<li><a id='<s:property value="getText('function.access.paymentsentreport')" />' href="<s:url action="loadDataOutwardPaymentSubmittedReportLoadData"/>">Payment Sent Report</a></li>
								<li><a id='<s:property value="getText('function.access.paymentreceivereport')" />' href="<s:url action="loadDataInwardPaymentSubmittedReportInwardLoadData"/>">Payment Received Report</a></li>
								<li><a id='<s:property value="getText('function.access.displayIFSCCodes')" />' href="<s:url action="searchIFSCListAction"/>">IFSC Search</a></li>
								<!--<li><a id='<s:property value="getText('function.access.MMIDREPORT')" />' href="<s:url action="displayMmidReportScreenmmidReport"/>">MMID Report</a></li>
								--><li><a id='<s:property value="getText('function.access.SearchTemplate')" />' href="<s:url action="displayTempalte"/>">Template Search</a></li>
							<!--	<li><a id='<s:property value="getText('function.access.discripancyreport')" />' href="<s:url action="getReportsDetailsPagediscrepanciesReport"/>">Discrepancies Reports</a></li>
								-->
								<li><a id='<s:property value="getText('function.access.findpaymentinhistory')" />' href="<s:url action="callFindHistoryfindPaymentHistory"/>">Find Payments in History</a></li>
							</ul>
						</div>
						<!--First Column Ends-->
						
						<!--Second Column Starts-->						
						<!--
						<div class="listCol">
							<ul>
							</ul>
						</div>						
						-->
						<!--Second Column Ends-->
					</div>
				</li>
				<!-- Enquiries & Reports End -->
				
				<!-- Maintenance Start -->
				<li class="level1-li maintain"><a class="level1-a" href="#"></a>
					<div class="listHolder col4">
						<!--First ColumnStarts-->
						<div class="listCol">
							<ul>
								<li><a id='<s:property value="getText('function.access.usermaintenance')" />' href="<s:url action="displayUserRegistraionPageuserMaintenance"/>">User Maintenance</a></li>
								<li><a id='<s:property value="getText('function.access.rolecreater')" />'  href="<s:url action="displayRolenewRoleAction"/>" >Role Maintenance</a></li>							
								<li><a id='<s:property value="getText('function.access.branchmaintenance')" />' href="<s:url action="getMaintainBranchScreenmaintainBranchScreen"/>">Branch Maintenance</a></li><!--
								<li><a id='<s:property value="getText('function.access.auhtorisationgroup')" />' href="<s:url action="loadDataGroupgroupMaintenanceAction"/>" >Authorisation Group</a></li>
--><!--								<li><a id='<s:property value="getText('function.access.authorisationschema')" />' href="<s:url action="loadDataAuthorizationauthorizationMaintenanceAction"/>" >Authorisation Schema</a></li>-->
								<li><a id='<s:property value="getText('function.access.maintaneifsc')" />' href="<s:url action="getIFSCMaintainifscMaintain"/>">IFSC Maintenance</a></li>	
								<li><a id='<s:property value="getText('function.access.securitypolicy')" />' href="<s:url action="getSecurityPolicy"/>">Password Security Policy</a></li>	
								<!--<li><a id='<s:property value="getText('function.access.lookup')" />' href="<s:url action="displayLookupnewLookupAction"/>" >Lookup Mainatenance</a></li>
								
								
								<li><a id='<s:property value="getText('function.access.rules')" />' href="<s:url action="loadruleAction"/>" >Rules</a></li> 
								<li><a id='<s:property value="getText('function.access.Accountmaintenance')" />' href="<s:url action="displayAccountMantenanceaccountMaintenance"/>" >Account Maintenance</a></li>-->
								<!--  <li><a id='<s:property value="getText('function.access.evenetmaster')" />' href="<s:url action="getEventScreeneventManage"/>" >Event Master</a></li>			
								-->
								 <!--<li><a id='<s:property value="getText('function.access.Customerdetails')" />' href="<s:url action="displayCustomerDetailscustomerDetails"/>" >Customer Maintenance</a></li> 
							--></ul>
						</div>
						<!--First Column Ends--> 
						
						<!--Second Column of Menu Starts-->
						<!--
						<div class="listCol">
							<ul>
								<li><a id='<s:property value="getText('function.access.membertransactionlimit')" />' href="<s:url action="initalizeAllLimitsFieldsmemberTransactionlimit"/>" >Member Transaction Limits</a></li>							
								<li><a id='<s:property value="getText('function.access.maintainei')" />' href="<s:url action="populateDataesbXMLCreator"/>" >Maintain EI</a></li>
								<li><a id='<s:property value="getText('function.access.maintainorchestration')" />' href="<s:url action="getValuesForOrchestrationmaintainingServiceOrchestration"/>" >Maintain Orchestration</a></li>
								<li><s:a href="#">Addresses</s:a></li>
								<li><s:a href="#">Customers</s:a></li>
								<li><s:a href="#">System Parameters</s:a></li>
							</ul>	
						</div>
						-->
						<!--Second Column of Menu Ends-->
					</div>
				</li>
				<!-- Maintenance End -->
				
				<!--<!-- Files Start -->
				<li class="level1-li fileOp"><a class="level1-a" href="#"></a>
					<div class="listHolder col5">
						<div class="listCol">
							<ul>
								<li><a id='<s:property value="getText('function.access.fileupload')" />' href="<s:url action="loadUploadFile"/>" >IFSC File Upload</a></li>
							</ul>
						</div>
						
						
					</div>
				</li>
				<!-- Files End -->
					
				<!-- System Start -->
				<li class="level1-li sys"><a class="level1-a" href="#"> </a>
					<div class="listHolder col6">
						<div class="listCol">
							<ul>
								<!--  <li><a id='<s:property value="getText('function.access.dashboard')" />' href="<s:url action="getStatsValuesquickStatsAction"/>" >Dashboard</a></li>
								-->
								<!--<li><a id='<s:property value="getText('function.access.help')" />' onclick="sessionTimer()" >HELP</a></li>
								--><!--<li><a id='<s:property value="getText('function.access.sodeodfeed')" />' href="<s:url action="displayFeedsfeedspage"/>" >Start/Stop Feeds</a></li>
								--><li><a id='<s:property value="getText('function.access.sodeod')" />' href="<s:url action="displayArchival"/>" >Data Archival</a></li>
								<li><a id='<s:property value="getText('function.access.eventaudit')" />' href="<s:url action="eventDisplayeventAudit"/>">Event Audit</a></li>	
								<li><a id='<s:property value="getText('function.access.changepassword')" />' href="<s:url action="getChangePwdgetChangePassword"/>">Change Password</a></li>
								<li><a id='<s:property value="getText('function.access.pendingauthorisation')" />' href="<s:url action="genericScreenFetchValues"/>" >Pending Authorisation</a></li>
								<li><a href="<s:url action="displayUserLogoutPage"/>">Signout</a></li>
							</ul>
						</div>
					</div>
				</li>
				<!-- System End -->
			</ul>
		</div>
	</div>
</div>
