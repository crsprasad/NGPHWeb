package com.logica.ngph.utils;

import java.io.FileInputStream;
import java.util.StringTokenizer;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class CheckXMLContent {
	
	public String createJBMqQueueService(String busID,String connectionType)
	{
		String scrPath = "c:/jboss-esb.xml";
	
		String returnResult= "";
		try {

			// Open the file that is the first
			// command line parameter
			FileInputStream fstream = new FileInputStream(scrPath);
			// Get the object of DataInputStream
			DocumentBuilderFactory factory = DocumentBuilderFactory
					.newInstance();
			// Use the factory to create a builder
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(fstream);
			// Get a list of all elements in the document

			if (connectionType.equals("MQ")) {
				NodeList listForProvider = doc.getElementsByTagName("jms-provider");
				NodeList listForJMS_Message_Filter = doc.getElementsByTagName("jms-message-filter");
				NodeList listForBUSID = doc.getElementsByTagName("jms-bus");
				int counterForMatchedCondition = 0;
				System.out.println("XML Elements: ");
				for (int i = 0; i < listForProvider.getLength(); i++) {
					// Get element
					Element element = (Element) listForProvider.item(i);
					Element element1 = (Element) listForJMS_Message_Filter
							.item(i);
					Element element2 = (Element) listForBUSID.item(i);
					if(element2.getAttribute("busid").equals(busID+".GW")){
					System.out.println(" coonnection factory : -"+element.getAttribute("connection-factory"));
					System.out.println(" jndi-URL : -"+element.getAttribute("jndi-URL"));
					System.out.println(" dest-name : -"+element1.getAttribute("dest-name"));
					returnResult = returnResult+ element.getAttribute("connection-factory")+","+element.getAttribute("jndi-URL")+","+element1.getAttribute("dest-name");
					break;
					}
				}
				
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return returnResult;
		}
	
	public static void main(String[] args) {
		CheckXMLContent obj = new CheckXMLContent();
		
		String a = obj.createJBMqQueueService("9007","MQ");
		System.out.println(" Out Put Sring : --  "+a);
		StringTokenizer st = new StringTokenizer(a, ":/,");
		String key = st.nextToken();
		String midvalue = st.nextToken();
		String val = st.nextToken();
		String lastval = st.nextToken();
		
		System.out.println(key +"\t"+midvalue +"\t" + val+"\t"+lastval); 
	}

}
