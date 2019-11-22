package com.logica.ngph.web.utils;

import java.util.ArrayList;
import java.util.List;


public class XYLineGraph {
public String lineGraph(String datastring1,String XAxis,String YAxis) {
		
		String strXML = "";
		try{
		strXML = "<chart bgColor='CCCCCC,FFFFFF'   yAxisName='"+YAxis+"' xAxisName='"+XAxis+"' showValues='0'  numVDivLines='4' vDivLineAlpha='0' showAlternateVGridColor='1' alternateVGridAlpha='7' canvasPadding='0' bgColor='C6DCFF' borderColor='FFFFFF' canvasBorderColor='FFFFFF' > <categories>";
		List test = new ArrayList();

		String[] temp = datastring1.split("#");
		
		String[] temp1 = temp[0].split(",");
		for (int i = 0; i < temp1.length; i++) {
			
			strXML = strXML + "<category label='" + temp1[i] + "'  />";
			//System.out.println(strXML);
			
		}
		strXML = strXML + "</categories>";
	//	System.out.println("STRXML=  "+strXML);
		
		
			String[] datasetFirst=temp[1].split("@");
			for(int j=0;j<datasetFirst.length;j++)
			{
				String[] currencydate=datasetFirst[j].split("=");
				strXML=strXML+"<dataset seriesName='"+currencydate[0]+"'  anchorBorderColor='A66EDD' anchorRadius='4'>";
				String[] newcurrnetdate=currencydate[1].split(",");
				for(int k=0;k<newcurrnetdate.length;k++){
					strXML=strXML+"<set value='"+newcurrnetdate[k]+"'/>";
				}
				strXML=strXML+"</dataset>";
				
			}
		
			strXML=strXML+"</chart>";

		//System.out.println(strXML);

		
		}
		catch (Exception e) {
			e.printStackTrace();
			System.out.println(e);
			// TODO: handle exception
		}
		return strXML;
	}
	
	public static void main(String s[])
	{
		XYLineGraph graph = new XYLineGraph();
		//graph.lineGraph("24-NOV-11,15-FEB-12,17-FEB-12#EUR=-590,,878@INR=-590,-590@USD=9012,34566,3434");
	}
}
