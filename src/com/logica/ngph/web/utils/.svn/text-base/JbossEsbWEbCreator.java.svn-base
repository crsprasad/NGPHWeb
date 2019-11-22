package com.logica.ngph.web.utils;

import java.io.FileInputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import org.w3c.dom.NodeList;

public class JbossEsbWEbCreator {
	public String createXML(String busID, String EI_Type,
			String MQ_Queue_Manager_Name, String MQ_Server_Name_ID,
			String MQ_Server_Port, String MQ_Queue_Name,
			String Client_Connection_Channel, String connectionType,
			String Path, String desPath) {
		String returnValue = "success";

		String scrPath = Path;

		if (connectionType.equals("MQ")) {
			scrPath = scrPath + "jboss-esb.xml";// input file

		} else if (connectionType.equals("Files")) {
			scrPath = scrPath + "jboss-esb-file-system.xml";// input file

		}

		else if (connectionType.equals("Web Service")) {
			scrPath = scrPath + "jboss-esb-web_service.xml";// input file

		}
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
				NodeList listForProvider = doc
						.getElementsByTagName("jms-provider");
				NodeList listForJMS_Message_Filter = doc
						.getElementsByTagName("jms-message-filter");
				int counterForMatchedCondition = 0;
				System.out.println("XML Elements: ");
				for (int i = 0; i < listForProvider.getLength(); i++) {
					// Get element
					System.out.println(listForJMS_Message_Filter.getLength()
							+ "--------------");

					Element element = (Element) listForProvider.item(i);
					Element element1 = (Element) listForJMS_Message_Filter
							.item(i);
					if (element.getAttribute("connection-factory").equals(
							MQ_Queue_Manager_Name)
							&& element.getAttribute("jndi-URL").equals(
									MQ_Server_Name_ID + ":" + MQ_Server_Port
											+ "/" + MQ_Queue_Name)
							&& element1.getAttribute("dest-name").equals(
									Client_Connection_Channel)) {
						System.out.println("MATCHED");
						counterForMatchedCondition = 1;
						returnValue = "alreadyPresent";
					}

				}
				if (counterForMatchedCondition != 1) {
					CreateFile createFile = new CreateFile();
					
					createFile.addChildNode(busID, EI_Type,
							MQ_Queue_Manager_Name, MQ_Server_Name_ID,
							MQ_Server_Port, MQ_Queue_Name,
							Client_Connection_Channel, connectionType, scrPath,
							desPath);
					System.out.println(Path);
					createFile.createJBMqQueueService(busID,EI_Type, Path,desPath);
					createFile.createDeplomentXML(busID,EI_Type, Path,desPath);
				}
			}

			else if (connectionType.equals("Files")) {
				NodeList listForProvider = doc.getElementsByTagName("jms-bus");

				int counterForMatchedCondition = 0;
				System.out.println("XML Elements: ");
				for (int i = 0; i < listForProvider.getLength(); i++) {
					// Get element

					Element element = (Element) listForProvider.item(i);

					if (element.getAttribute("busid").equals(busID)) {
						System.out.println("MATCHED");
						counterForMatchedCondition = 1;
						returnValue = "alreadyPresent";
					}

				}
				if (counterForMatchedCondition != 1) {
					CreateFile createFile = new CreateFile();
					createFile.addChildNode(busID, EI_Type,
							MQ_Queue_Manager_Name, MQ_Server_Name_ID,
							MQ_Server_Port, MQ_Queue_Name,
							Client_Connection_Channel, connectionType, scrPath,
							desPath);
					createFile.createJBMqQueueService(busID,EI_Type, Path,desPath);
					createFile.createDeplomentXML(busID,EI_Type, Path,desPath);
					
				}

			} else if (connectionType.equals("Web Service")) {
				NodeList listForProvider = doc.getElementsByTagName("fs-bus");

				int counterForMatchedCondition = 0;
				System.out.println("XML Elements: ");
				for (int i = 0; i < listForProvider.getLength(); i++) {
					// Get element

					Element element = (Element) listForProvider.item(i);

					if (element.getAttribute("busid").equals(
							busID + ".EsbChannel.Web.service")) {
						System.out.println("MATCHED");
						counterForMatchedCondition = 1;
						returnValue = "alreadyPresent";
					}

				}
				if (counterForMatchedCondition != 1) {
					CreateFile createFile = new CreateFile();
					createFile.addChildNode(busID, EI_Type,
							MQ_Queue_Manager_Name, MQ_Server_Name_ID,
							MQ_Server_Port, MQ_Queue_Name,
							Client_Connection_Channel, connectionType, scrPath,
							desPath);
					createFile.createJBMqQueueService(busID,EI_Type, Path,desPath);
					createFile.createDeplomentXML(busID,EI_Type, Path,desPath);
				}

			}

			// in.close();
			// out.close();
		} catch (Exception e) {// Catch exception if any
			System.err.println("Error: " + e.getMessage());
			returnValue = "failure";
		}
		return returnValue;
	}

	public static void main(String s[]) {
		JbossEsbWEbCreator creator = new JbossEsbWEbCreator();
		creator.createXML("", "", "QNGQMGR", "RBIRTGS:1406/QNG_SVR_CHNL",
				"FFFFF", "RRB.QNG.INCOMING", "DDDDDD", "MQ", "", "");

	}
}
