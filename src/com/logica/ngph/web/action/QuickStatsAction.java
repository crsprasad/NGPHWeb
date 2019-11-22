package com.logica.ngph.web.action;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

//import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
//import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.context.ApplicationContextException;

import com.logica.ngph.common.ConstantUtil;
import com.logica.ngph.common.NGPHException;
import com.logica.ngph.dtos.EIDto;
import com.logica.ngph.service.QuickStatsService;

//import com.logica.ngph.web.utils.AreaChart;
import com.logica.ngph.web.utils.ApplicationContextProvider;
import com.logica.ngph.web.utils.BarGraph;
import com.logica.ngph.web.utils.Bulb;
import com.logica.ngph.web.utils.PieGraph;
import com.logica.ngph.web.utils.XYLineGraph;
import com.opensymphony.xwork2.ActionSupport;

/*
 * This the action class for Dash board and are call by the struts.xml 
 * It basically handles the request and response which we get from the 
 * view.  
 * 
 */
public class QuickStatsAction extends ActionSupport implements SessionAware {
	// private HttpServletRequest servletRequest;
	private static final long serialVersionUID = -3364264106258064077L;
	static Logger logger = Logger.getLogger(QuickStatsAction.class);

	public static String channelBarGraph;

	public static String InBoundChannelCounts;
	public static String OutBoundChannelCounts;
	public static String INboundOutboundChart;
	public static String XYLineChart;
	public static String StackChartForPary;
	public static String iMPS_Signal;
	public static List<EIDto> searchList;

	
	private String displayNextScreen;
	


	public String getDisplayNextScreen() {
		return displayNextScreen;
	}

	public void setDisplayNextScreen(String displayNextScreen) {
		this.displayNextScreen = displayNextScreen;
		this.session.put("displayNextScreen", displayNextScreen);
	}

	public static String getStackChartForPary() {
		return StackChartForPary;
	}

	public static void setStackChartForPary(String stackChartForPary) {
		StackChartForPary = stackChartForPary;
	}

	public static String getXYLineChart() {
		return XYLineChart;
	}

	public static void setXYLineChart(String xYLineChart) {
		XYLineChart = xYLineChart;
	}

	public static String getInBoundChannelCounts() {
		return InBoundChannelCounts;
	}

	public static void setInBoundChannelCounts(String inBoundChannelCounts) {
		InBoundChannelCounts = inBoundChannelCounts;
	}

	public static String getOutBoundChannelCounts() {
		return OutBoundChannelCounts;
	}

	public static void setOutBoundChannelCounts(String outBoundChannelCounts) {
		OutBoundChannelCounts = outBoundChannelCounts;
	}

	public static String getINboundOutboundChart() {
		return INboundOutboundChart;
	}

	public static void setINboundOutboundChart(String iNboundOutboundChart) {
		INboundOutboundChart = iNboundOutboundChart;
	}

	public static String getChannelBarGraph() {
		return channelBarGraph;
	}

	public static void setChannelBarGraph(String channelBarGraph) {
		QuickStatsAction.channelBarGraph = channelBarGraph;
	}
	
	public String getVariation() throws NGPHException
	{
		setDisplayNextScreen("Variation");
		QuickStatsService quickStatsService = (QuickStatsService) ApplicationContextProvider
		.getBean("quickStatsService");
		String dataStringForZoomLine = quickStatsService
		.getQuickStats(ConstantUtil.CLOSINGBALANCEVSCURRENCYDATE);
		
		XYLineGraph xyline = new XYLineGraph();

		setXYLineChart(xyline.lineGraph(dataStringForZoomLine,"Date","Closing Balance"));

		return "success";
	}
	
	public String getPosition() throws NGPHException
	{
		setDisplayNextScreen("Position");
		QuickStatsService quickStatsService = (QuickStatsService) ApplicationContextProvider
		.getBean("quickStatsService");
		String dataStringForStackGraph = quickStatsService.getQuickStats(ConstantUtil.STACKGRAPH);
		String dataStringForBarGraph = quickStatsService
		.getQuickStats(ConstantUtil.CLOSINGBALANCEVSCURRENCY);
			@SuppressWarnings("unused")
	

		XYLineGraph xyline = new XYLineGraph();

		
		setStackChartForPary(xyline.lineGraph(dataStringForStackGraph,"Party Banks","Closing Balance"));
		
		BarGraph chartDemo5 = new BarGraph();

		setChannelBarGraph(chartDemo5.Barchart(dataStringForBarGraph,
				"Chart for Busness date 24-Nov-11"));
		
		
		return "success";
	}

	@SuppressWarnings("unchecked")
	public String getStatsValues() throws NGPHException, IOException,
			SQLException {

		QuickStatsService quickStatsService = (QuickStatsService) ApplicationContextProvider
				.getBean("quickStatsService");
		try {
			setDisplayNextScreen("Message");
			@SuppressWarnings("unused")
			String dataStringForBarGraph = quickStatsService
					.getQuickStats(ConstantUtil.CLOSINGBALANCEVSCURRENCY);
			@SuppressWarnings("unused")
			String INboundChannelCount = quickStatsService
					.getQuickStats(ConstantUtil.INBOUNDCHANNELCOUNT);
			String outBoundChannelCount = quickStatsService
					.getQuickStats(ConstantUtil.OUTBOUNDCHANNELCOUNT);
			
			PieGraph graph = new PieGraph();
			BarGraph chartDemo5 = new BarGraph();
			try {
				if (INboundChannelCount.length() != 0)
					setInBoundChannelCounts(graph.pieChart1(INboundChannelCount, "In-Bound Channels"));
				else
					setINboundOutboundChart("<chart><set label='' value=''/></chart>");

			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				if (outBoundChannelCount.length() != 0)
					setOutBoundChannelCounts(graph.pieChart1(outBoundChannelCount, "Out-Bound Channels"));
				else
					setOutBoundChannelCounts("<chart><set label='' value=''/></chart>");

			} catch (Exception e) {
				e.printStackTrace();
			}

			if (INboundChannelCount.length() != 0
					&& outBoundChannelCount.length() != 0)
				setINboundOutboundChart(chartDemo5.inverseColoumChart(INboundChannelCount, outBoundChannelCount));
			else
				setINboundOutboundChart("<chart  caption=''  formatNumberScale='0'  showPercentInToolTip='0' showLegend='0'></chart>");

		} catch (Exception e) {
			logger.error("DashBoard Action Class " + e);
		}

		return "success";

	}
	
	public String getSystem() throws SQLException
	{
		setDisplayNextScreen("System");
		QuickStatsService quickStatsService = (QuickStatsService) ApplicationContextProvider
		.getBean("quickStatsService");
		
		Bulb bulb = new Bulb();
		
		searchList = quickStatsService.getEI_IMPSStatus();	
		
		return "success";
	}

	

	/*
	 * This method is to get the bean from the application context.
	 */
	private QuickStatsService getQuickstatservice() {
		QuickStatsService quickStatsServiceService = null;
		try {

			quickStatsServiceService = (QuickStatsService) ApplicationContextProvider
					.getBean("quickstatsService");
		} catch (ApplicationContextException applicationContextException) {
			logger.error(ConstantUtil.RULE_ACTION + applicationContextException);
		}

		return quickStatsServiceService;
	}

	private Map<String, Object> session;

	public Map<String, Object> getSession() {
		return session;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

}
