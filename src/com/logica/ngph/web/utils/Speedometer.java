package com.logica.ngph.web.utils;

/**
* @author Siddharth
*
*/
public class Speedometer  {
  
 
  
   
   //Method for creating the Speedometer
   public String createFusionChart(int value1)
   {
	   String strXML="<chart bgCOlor='FFFFFF' upperLimit='240' lowerLimit='0' baseFontColor='FFFFFF'  majorTMNumber='9' " +
  		"majorTMColor='FFFFFF'  majorTMHeight='8' majorTMThickness='5' minorTMNumber='5'" +
  		" minorTMColor='FFFFFF' minorTMHeight='3' minorTMThickness='2' pivotRadius='10' pivotBgColor='000000' " +
  		"pivotBorderColor='FFFFFF' pivotBorderThickness='2' hoverCapBorderColor='FFFFFF' toolTipBgColor='333333' " +
  		" gaugeOuterRadius='135' gaugeScaleAngle='300' gaugeAlpha='0' decimalPrecision='0' displayValueDistance='0' showColorRange='0'" +
  		" placeValuesInside='1' pivotFillMix='' showPivotBorder='1' pivotBorderColor='FFFFFF' pivotBorderThickness='2' annRenderDelay='0'>"
+"<dials> ";
	  
	   System.out.println(value1);
	   strXML= strXML+"<dial value='"+ value1+"' bgColor='000000' borderColor='FFFFFF' borderAlpha='100' baseWidth='4' topWidth='4' borderThickness='2'/>";
	   
	   
	   
	   strXML=strXML+"</dials><annotations><annotationGroup xPos='160' yPos='160'><annotation type='circle' radius='150' startAngle='0' endAngle='360' fillAsGradient='1' fillColor='4B4B4B, AAAAAA' fillAlpha='100,100'  fillRatio='95,5' />"+
			"<annotation type='circle' xPos='0' yPos='0' radius='140' startAngle='0' endAngle='360' showBorder='1' borderColor= 'cccccc' fillAsGradient='1'  fillColor='ffffff, 000000'  fillAlpha='50,100'  fillRatio='1,99' />"
		+"</annotationGroup><annotationGroup xPos='160' yPos='280' showBelowChart='0'><annotation type='text' label='Messages' fontColor='FFFFFF' fontSize='14' isBold='1'/>"
		+"</annotationGroup></annotations></chart>";
	   return strXML;
   }
   public void setFocus() {
   }
 
   /**
    * Creates the Chart based on a dataset
    */
   
  
}