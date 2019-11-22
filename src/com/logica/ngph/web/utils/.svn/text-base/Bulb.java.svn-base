package com.logica.ngph.web.utils;

public class Bulb {
	public String show(String Value)
	{
		
		String tempXML ="<chart bgColor='C4D283, FFFFFF' decimalPrecision='0' lowerLimit='0' upperLimit='100'><colorRange>";
		if(Value.equals("0"))
		{
			tempXML = tempXML+"<color minValue='0' maxValue='0' name='NO Message' code='FF654F'/>";
		}
		else if(Value.equals("1"))
		{
			tempXML = tempXML+"<color minValue='1' maxValue='1' name='Ready State' code='8BBA00'/>";
		}
		else
		{
			tempXML = tempXML+"<color minValue='2' maxValue='2' name='Action' code='F6BD0F'/>";
		}
		tempXML = tempXML+"</colorRange><value>52</value></chart>";
		return tempXML;
	}

}
