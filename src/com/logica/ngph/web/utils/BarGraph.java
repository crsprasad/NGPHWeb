package com.logica.ngph.web.utils;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

public class BarGraph {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static Logger logger = Logger.getLogger(BarGraph.class);

	/**
	 * @param args
	 * @throws IOException
	 */
	String[] arr_FCColors = new String[] { "D8EEF8", "4BE2F2", "6140E7",
			"EAC8F0", "6495B3", "76D2E0", "E95D0F", "7C7CB4", "FF9933",
			"9900FF", "99FFCC", "CCCCFF", "669900", };

	public String Barchart(String datastring1, String graphtitle) {
		String strXML = "";
		// Initialize <graph> element
		strXML = "<graph caption='"
				+ graphtitle
				+ "'  sxAxisName='Currency' yAxisName='Closing Balance' decimalPrecision='0'"
				+ "formatNumberScale='0' chartRightMargin='30' showLegend='2' areaOverColumns='0'>";

		String[] temp = datastring1.split(",");
		for (int i = 0; i < temp.length; i++) {
			String[] temp1 = temp[i].split("=");
			System.out.println("temp1[i] :-- " + temp1[0]
					+ "Float.parseFloat(temp1[i]) :-- " + temp1[1]);
			strXML = strXML + "<set name='" + temp1[0] + "' value='" + temp1[1]
					+ "' color='" + arr_FCColors[i] + "' />";
			System.out.println("new Str XML :--" + strXML);

		}
		strXML = strXML + "</graph>";
		return strXML;

	}

	public String inverseColoumChart(String datastring1, String datastring2) {

		String strXML = "";
		try {
			strXML = "<chart caption='Inbound / Outbound Comparative Chart' xAxisName='Channel'yAxisName='Message Count' showvalues='1' showLegend='1' areaOverColumns='0' stack100Percent='0' showPercentValues='0'><categories>";

			List test = new ArrayList();

			String[] temp = datastring1.split(",");
			for (int i = 0; i < temp.length; i++) {
				String[] temp1 = temp[i].split("=");
				strXML = strXML + "<category label='" + temp1[0] + "'  />";
				test.add(temp1[0]);
			}
			strXML = strXML + "</categories>";
			strXML = strXML + "<dataset seriesName='In-Bound'>";

			String[] tempfirst = datastring1.split(",");
			String[] tempsecond = datastring2.split(",");
			// for (int i = 0, j = 0; i < tempfirst.length || j <
			// tempsecond.length;
			// i++, j++) {
			for (int i = 0; i < tempfirst.length; i++) {
				if (i < temp.length) {
					String[] temp1 = tempfirst[i].split("=");
					if (test.get(i).equals(temp1[0])) {

					/*	System.out.println(temp1[0] + " Inound       "
								+ temp1[1] + " value ");*/
						strXML = strXML + "<set value='" + temp1[1] + "' />";

					}
				}

			}

			strXML = strXML + "</dataset><dataset seriesName='Out-Bound'>";

			for (int j = 0; j < tempfirst.length; j++) {
				if (j < tempsecond.length) {
					String[] temp2 = tempsecond[j].split("=");
					if (test.get(j).equals(temp2[0])) {
					//	System.out.println(temp2[0] + " Outbound       "+ temp2[1] + " value");
						strXML = strXML + "<set value='" + temp2[1] + "' />";

					}
				}
			}

			strXML = strXML + "</dataset></chart>";

			//System.out.println(strXML);
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return strXML;
	}
	/*
	 * public static void main(String st[]) { String
	 * outpur=inverseColoumChart("swift=3,rtgs=8,nfty=9"
	 * ,"swift=3,rtgs=8,nfty=9"); System.out.println(outpur); }
	 */

}
