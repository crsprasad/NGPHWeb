package com.logica.ngph.web.utils;
import java.io.File;
import java.io.IOException;
import java.util.StringTokenizer;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
public class ModifyXmlFiles {
	/**
 * @param argv
 */
public String modifyXML(String busID,String EI_Type, String MQ_Queue_Manager_Name,
		String MQ_Server_Name_ID, String MQ_Server_Port,
		String MQ_Queue_Name, String Client_Connection_Channel,
		String Connection_type ) {
	 
	   try {
		String filepath = "c:\\jboss-esb.xml";
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
		Document doc = docBuilder.parse(filepath);

		// Get the root element
		Node company = doc.getFirstChild();

		// Get the staff element , it may not working if tag has spaces, or
		// whatever weird characters in front...it's better to use
		// getElementsByTagName() to get it directly.
		// Node staff = company.getFirstChild();

		// Get the staff element by tag name directly
		NodeList listForBUSID = doc.getElementsByTagName("jms-bus");
		int count = 0;

		// update staff attribute
		
		for (int i = 0; i < listForBUSID.getLength(); i++) {
			System.out.println("listForBUSID.item(i) :-  "+((Element) listForBUSID.item(i)).getAttribute("busid")
					);
			Element element2 = (Element) listForBUSID.item(i);
			StringTokenizer toGetBusID = new StringTokenizer(element2.getAttribute("busid"),".");
			String busIDToMatch = toGetBusID.nextToken();
		if(busIDToMatch.equals(busID)){
			if(count==0){
			Node staff = doc.getElementsByTagName("jms-provider").item(i);
			NamedNodeMap attr = staff.getAttributes();
			Node nodeAttr = attr.getNamedItem("connection-factory");
			nodeAttr.setTextContent(MQ_Queue_Manager_Name);
			Node nodeAttr1 = attr.getNamedItem("jndi-URL");
			nodeAttr1.setTextContent(MQ_Server_Name_ID+":"+MQ_Server_Port+"/"+Client_Connection_Channel);
			nodeAttr.setTextContent(MQ_Queue_Name);
			
			Node filter = doc.getElementsByTagName("jms-message-filter").item(i);
			NamedNodeMap filterattr = filter.getAttributes();
			Node filternodeAttr = filterattr.getNamedItem("dest-name");
			filternodeAttr.setTextContent(MQ_Queue_Name);
			
			count++;
				}
			
			
			}
		
		}
		
		// write the content into xml file
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(new File(filepath));
		transformer.transform(source, result);

		System.out.println("Done");

	   } catch (ParserConfigurationException pce) {
		pce.printStackTrace();
	   } catch (TransformerException tfe) {
		tfe.printStackTrace();
	   } catch (IOException ioe) {
		ioe.printStackTrace();
	   } catch (SAXException sae) {
		sae.printStackTrace();
	   }
	   return "success";
	}
public static void main(String[] args) {
	ModifyXmlFiles files = new ModifyXmlFiles();
	files.modifyXML("90010", "P", "MQ_Queue_Manager_Name", "252.252.252.99", "8080", "MQ_Queue_Name", "Client_Connection_Channel", "MQ");
}
}