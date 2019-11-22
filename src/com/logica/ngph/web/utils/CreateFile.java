package com.logica.ngph.web.utils;

import java.io.*;

public class CreateFile {
	String addChildNode(String busID,String EI_Type, String MQ_Queue_Manager_Name,
			String MQ_Server_Name_ID, String MQ_Server_Port,
			String MQ_Queue_Name, String Client_Connection_Channel,
			String Connection_type, String scrPath, String desPath) {
		
		String sourceFile= scrPath;
		String destinationFileName = null;
		if (Connection_type.equals("MQ")) {
		
		  destinationFileName = desPath+"jboss-esb.xml";// output file
		}
		else if (Connection_type.equals("Files")) {
		
			  destinationFileName = desPath+ "jboss-esb-file-system.xml";// output file
			}
		
		else if (Connection_type.equals("Web Service")) {
		
			  destinationFileName = desPath+"jboss-esb-web_service.xml";// output file
			
		}
		
		
		try {
			// file input stream, basically a pointer to the stream of the input
			// file
			FileInputStream fStream = new FileInputStream(sourceFile);
			// file output stream, basically a pointer to the stream for
			// outputing data
			FileOutputStream fOutStream = new FileOutputStream(
					destinationFileName);

			// the input/output data streams that connect to the above streams
			DataInputStream dInput = new DataInputStream(fStream);
			DataOutputStream dOutput = new DataOutputStream(fOutStream);
			String stringToAppendForProvide = "<providers>\n<jms-provider connection-factory=\""
					+ MQ_Queue_Manager_Name+ "\""+ " jndi-URL=\""+ MQ_Server_Name_ID+ ":"+ MQ_Server_Port+ "/"+ Client_Connection_Channel+ "\" "+ "jndi-context-factory=\"com.ibm.mq.jms.context.WMQInitialContextFactory\" name=\"WebSphereMQ_"+MQ_Queue_Name+"\">\n"
					+ " <property name=\"protocol\" value=\"jms\"/>\n"
					+ "<jms-bus busid=\""+ busID+".GW\">\n"+ "<jms-message-filter dest-name=\""+ MQ_Queue_Name	+ "\" dest-type=\"QUEUE\"/>\n"
					+ "</jms-bus>\n</jms-provider>\n";
			String stringToAppendForService = "<services>\n<service category=\""+ busID	+ " SERVICE PROVIDER\" description=\"\" name=\""+ busID+ "Listener\">\n"+ "<listeners>\n<jms-listener busidref=\""
					+ busID+"GW.ID\" is-gateway=\"true\" name=\"JMS-Gateway\"/>\n"+ "<jms-listener busidref=\""+ busID+".ESB.ID\" name=\""
					+ busID	+ "\"/>\n"+ "</listeners>\n<actions mep=\"OneWay\">\n"+ "<action class=\"com.logica.ngph.action.RequestQueueHandlerAction\" name=\"doProcess\" process=\"doProcess\">\n"
					+ "<property name=\"gatewayName\" value=\""+ busID+".GW\"/>\n"	+ "</action>\n"+ "</actions>\n"	+ "</service>\n";

			String stringToAppendForJMS_Provide = "<jms-provider connection-factory=\"ConnectionFactory\" name=\"JBossMQ\">\n"
					+ "<jms-bus busid=\""+ busID+".GW.ID\">\n"	+ "<jms-message-filter dest-name=\"queue/"+ busID+".GW\" dest-type=\"QUEUE\"/>" +
							"\n</jms-bus>\n"
					+ "<jms-bus busid=\""+ busID+".ESB.ID\">\n"	+ "<jms-message-filter dest-name=\"queue/"+ busID+".ESB\" dest-type=\"QUEUE\"/>" +
							"\n</jms-bus>\n";

			String stringTOAppendForProvideInFileSystem = "<providers>\n"
					+ "<fs-provider name=\"SimpleFSProvider\">\n"
					+ "<fs-bus busid=\"" + busID + ".FS-System\">\n"
					+ "<fs-message-filter directory=\"" + MQ_Queue_Manager_Name
					+ "\" input-suffix=\".dat\"/>\n" + "</fs-bus>\n"
					+ "</fs-provider>\n";
			String stringToAppendForServiceInFileSystem = "<services>\n"
					+ "<service category=\""
					+ busID
					+ ".FS-System Queue\" description=\"SampleService\""
					+ " invmScope=\"GLOBAL\" name=\"SampleOne\">\n"
					+ "<listeners>\n"
					+ "<fs-listener busidref=\""
					+ busID
					+ ".FS-System\" is-gateway=\"true\" name=\"SampleFSListener\"/>\n"
					+ "</listeners>\n"
					+ "<actions mep=\"OneWay\">\n"
					+ "<action class=\"org.jboss.soa.esb.actions.SystemPrintln\" name=\"action1\">\n"
					+ "<property name=\"message\"/>\n"
					+ "<property name=\"printfull\" value=\"true\"/>\n"
					+ "</action>\n</actions>\n</service>\n";
			String stringToAppendForWebServices="<providers>\n"+
        "<jms-provider name=\"JBossMQ\" connection-factory=\"ConnectionFactory\">\n"+
              "<jms-bus busid=\""+busID+".EsbChannel.Web.service\">\n"+
                  "<jms-message-filter "+
                      "dest-type=\""+MQ_Queue_Manager_Name+".EsbChannel.Web.service\" "+
                      "dest-name=\"queue/"+busID+"_webservice_Request_esb\" selector=\"serviceName='HelloWorldPubService\"/>\n"+
              "</jms-bus>\n</jms-provider>\n";
			String stringToAppendForWebserviceServices=" <services>\n<service category=\"ESBServiceSample\" "+ 
        	"name=\"HelloWorldPubService\" description=\"Hello world ESB Service\">\n"+
			"<security moduleName=\"JBossWS\"/>\n"+
            "<listeners>\n<jms-listener name=\"helloWorld\" busidref=\""+MQ_Queue_Manager_Name+".EsbChannel.Web.service\" maxThreads=\"1\"/>\n"+
            "</listeners>\n"+
            "<actions  inXsd=\"/request.xsd\" outXsd=\"/response.xsd\" faultXsd=\"/fault.xsd\" validate=\"true\">\n"+
                   "<action name=\"action\" class=\"org.jboss.soa.esb.samples.quickstart.publishAsWebservice.ESBWSListenerAction\" process=\"displayMessage\"/>\n"+  
            "</actions>\n</service>\n";
int count=0;
			// whilst there is data available in the input stream
			if (Connection_type.equals("MQ")) {
				while (dInput.available() != 0) {

					String in = dInput.readLine();
					// System.out.println(in);
					if ((in.contains("<providers>")) == true) {
						System.out.println("New Provider Is Add To The File");
						dOutput.writeBytes(stringToAppendForProvide);
					} else if ((in.contains("<services>")) == true) {
						System.out.println("New Serice Is Add To The File");
						dOutput.writeBytes(stringToAppendForService);
					} else if ((in
							.contains("connection-factory=\"ConnectionFactory\" name=\"JBossMQ\"")) == true) {
						System.out.println("JMS Provider Is Add To The File");
						dOutput.writeBytes(stringToAppendForJMS_Provide);
					} else {
						dOutput.writeBytes(in + "\n");
					}
					// read a line from the input file
					// output a stream of data to the output file

				}
			} else if (Connection_type.equals("Files")) {
				while (dInput.available() != 0) {

					String in = dInput.readLine();
					if ((in.contains("<providers>")) == true) {
						System.out.println("New Provider Is Add To The File");

						dOutput.writeBytes(stringTOAppendForProvideInFileSystem);

					} else if ((in.contains("<services>")) == true) {
						dOutput.writeBytes(stringToAppendForServiceInFileSystem);
					} else {
						dOutput.writeBytes(in + "\n");
					}
				}
			}
				else if (Connection_type.equals("Web Service")) {
					while (dInput.available() != 0) {

						String in = dInput.readLine();
						if ((in.contains("<providers>")) == true) {
							System.out.println("New Provider Is Add To The File");

							dOutput.writeBytes(stringToAppendForWebServices);

						} else if ((in.contains("<services>")) == true) {
							dOutput.writeBytes(stringToAppendForWebserviceServices);
						} else {
							dOutput.writeBytes(in + "\n");
						}
					}
				}
			
			// close the two files.
			dInput.close();
			dOutput.close();
			
			callCopy(destinationFileName, sourceFile);
		} catch (Exception e)// incase of any errors
		{
			System.err.println("There was a error : " + e.toString());
		}
		return "success";

	}

	String callCopy(String SourceFile, String DestinationFileName) {
		File f1 = new File(SourceFile);

		File f2 = new File(DestinationFileName);

		InputStream in;
		try {
			in = new FileInputStream(f1);

			OutputStream out = new FileOutputStream(f2);

			byte[] buf = new byte[1024];

			int len;

			while ((len = in.read(buf)) > 0) {

				out.write(buf, 0, len);

			}
			System.out
					.println("------------------file copied------------------");
			in.close();

			out.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "success";
	}
	public String createJBMqQueueService(String busID,String EI_Type,String scrPath, String desPath)
	{
		String returnResult= "success";
		
		String syntaxFoMqueue = "<server>\n<mbean code=\"org.jboss.mq.server.jmx.Queue\""+
		    "name=\"jboss.esb:service=Queue,name="+ busID+"."+EI_Type+ ".GW\">\n"+
		    "<depends optional-attribute-name=\"DestinationManager\">\n"+
		      "jboss.mq:service=DestinationManager\n</depends>\n</mbean>";
		try {
			// file input stream, basically a pointer to the stream of the input
			// file
			FileInputStream fStream = new FileInputStream(scrPath+"jbmq-queue-service.xml");
			// file output stream, basically a pointer to the stream for
			// outputing data
			FileOutputStream fOutStream = new FileOutputStream(desPath+"jbmq-queue-service.xml");

			// the input/output data streams that connect to the above streams
			DataInputStream dInput = new DataInputStream(fStream);
			DataOutputStream dOutput = new DataOutputStream(fOutStream);
			while (dInput.available() != 0) {

				String in = dInput.readLine();
				// System.out.println(in);
				if ((in.contains("<server>")) == true) {
					System.out.println("New Provider Is Add To The File");
					dOutput.writeBytes(syntaxFoMqueue);
				} else {
					dOutput.writeBytes(in + "\n");
				}
				}
			callCopy(desPath+"jbmq-queue-service.xml", scrPath+"jbmq-queue-service.xml");
			dInput.close();
			dOutput.close();
		}
		catch(Exception e)
		{
			returnResult="failure";
			e.printStackTrace();
		}
		
		return returnResult;
	}
	
	public String createDeplomentXML(String busID,String EI_Type,String scrPath, String desPath)
	{
		String returnResult= "success";
		
		String syntaxFoMqueue = "<jbossesb-deployment>\n<depends>jboss.esb:service=Queue,name="+ busID+"."+EI_Type+ ".GW</depends>";
		try {
			// file input stream, basically a pointer to the stream of the input
			// file
			FileInputStream fStream = new FileInputStream(scrPath+"deployment.xml");
			// file output stream, basically a pointer to the stream for
			// outputing data
			FileOutputStream fOutStream = new FileOutputStream(desPath+"deployment.xml");

			// the input/output data streams that connect to the above streams
			DataInputStream dInput = new DataInputStream(fStream);
			DataOutputStream dOutput = new DataOutputStream(fOutStream);
			while (dInput.available() != 0) {

				String in = dInput.readLine();
				// System.out.println(in);
				if ((in.contains("<jbossesb-deployment>")) == true) {
					System.out.println("New Provider Is Add To The File");
					dOutput.writeBytes(syntaxFoMqueue);
				} 
				else {
					dOutput.writeBytes(in + "\n");
				}
				
				}
			callCopy(desPath+"deployment.xml", scrPath+"deployment.xml");
			dInput.close();
				dOutput.close();
		}
		catch(Exception e)
		{
			returnResult="failure";
			e.printStackTrace();
		}
		
		return returnResult;
	}
}
