package com.logica.ngph.web.utils;

import java.awt.Color;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import org.apache.log4j.Logger;

import com.opensymphony.xwork2.ActionSupport;
/*This Class Create a pie chart 
 */
public class PieGraph {
	static Logger logger = Logger.getLogger(PieGraph.class);

	
	/*
	 * String Array for the color 
	 */
	String[] arr_FCColors = new String[]{
			"D8EEF8",
			"4BE2F2",
			"B1AFF4", 
			"D49E93", 
			"AFA8A7", 
			"E95D0F", 
			"E95D0F",
			"7C7CB4",
			"FF9933",
			"9900FF",
			"99FFCC",
			"CCCCFF",
			"669900",
		};
	
	
	/*
	 * Create a pie chart 
	 * @param datastring contain a string which we get from data base i.e. string which we get
	 * from stored procedure 
	 * @param graph graphtitle Contain the tittle of the graph
	 * @param filename will content the context path of the server to get the root directory 
	 * 
	 */
	
	
	/*
	 * Create a pie chart 
	 * @param datastring contain a string which we get from data base i.e. string which we get
	 * from stored procedure 
	 * @param graph graphtitle Contain the tittle of the graph
	 * @param filename will content the context path of the server to get the root directory 
	 * 
	 */
	public String pieChart1(String datastring1, String graphtitle)
	{
		String strXML="";
		
		try{
		//Initialize <graph> element
		strXML = "<chart  caption='"+graphtitle+"'  formatNumberScale='0'  showPercentInToolTip='0' showLegend='0'>";
		//Add all data
		
		if (graphtitle.equalsIgnoreCase("In-Bound Currency")
				|| graphtitle.equalsIgnoreCase("Out-Bound Currency")) {
			String[] temp = datastring1.split(",");
			for (int i = 4; i < temp.length; i++) {
				String[] temp1 = temp[i].split("=");
				System.out.println("temp1[i] :-- " + temp1[0]
						+ "Float.parseFloat(temp1[i]) :-- " + temp1[1]);
				
				strXML = strXML + "<set label='"+temp1[0]+"' value='"+temp1[1]+"' color='"+arr_FCColors[i+3]+"' isSliced='0'/>";
				//System.out.println("new Str XML :--"+strXML);
				
			}
		} else {
			String[] temp = datastring1.split(",");
			for (int i = 0; i < temp.length; i++) {
				String[] temp1 = temp[i].split("=");
			/*	System.out.println("temp1[i] :-- " + temp1[0]
						+ "Float.parseFloat(temp1[i]) :-- " + temp1[1]);*/
				strXML = strXML + "<set label='"+temp1[0]+"' value='"+temp1[1]+"' color='"+arr_FCColors[i]+"' isSliced='0'/>";
				
				
			}
		}
		strXML=strXML+"<styles><definition><style name='CaptionFont' type='FONT' size='12' bgColor='03407E' bold='1' />" +
				"<style name='LabelFont' type='FONT' color='2E4A89' bgColor='FFFFFF' bold='1' />" +
				"<style name='ToolTipFont' type='FONT' bgColor='2E4A89' borderColor='2E4A89' /></definition></styles></chart>";
		}
		catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return strXML;
	}
	public String createFunnelChart(String datastring1, String graphtitle)
	{
		String strXML="";
		strXML="<chart isSliced='1' slicingDistance='4' decimalPrecision='0'>";
		String[] temp = datastring1.split(",");
		for (int i = 0; i < temp.length; i++) {
			String[] temp1 = temp[i].split("=");
			/*System.out.println("temp1[i] :-- " + temp1[0]
					+ "Float.parseFloat(temp1[i]) :-- " + temp1[1]);*/
			strXML = strXML + "<set name='"+temp1[0]+"' value='"+temp1[1]+"'  color='"+arr_FCColors[i]+"' />";
		}
		strXML = strXML +"</chart>";
		return strXML;
	}
	public String createPrymidChart(String datastring1, String graphtitle)
	{

		String strXML="";
		strXML="<chart bgColor='CCCCCC,FFFFFF' caption='"+graphtitle+"' baseFontColor='333333' enableSmartLabels='0' plotfillAlpha='80' decimalPrecision='0' numberSuffix='%25' pyramidYScale='40' chartRightMargin='130' chartBottomMargin='0' captionPadding='0'>";
		String[] temp = datastring1.split(",");
		for (int i = 0; i < temp.length; i++) {
			String[] temp1 = temp[i].split("=");
			/*System.out.println("temp1[i] :-- " + temp1[0]
					+ "Float.parseFloat(temp1[i]) :-- " + temp1[1]);*/
			strXML = strXML + "<set name='"+temp1[0]+"' value='"+temp1[1]+"' color='"+arr_FCColors[i]+"' />";
		}
		strXML = strXML +"</chart>";
		return strXML;
	}
	public static void main(String s[]) {
		PieGraph pieChart = new PieGraph();
		String strXML=pieChart.pieChart1("INR=3000000,USD=400000,URO=500000", "Area Currency");
		System.out.println("new Str XML :--"+strXML);
	}
}
