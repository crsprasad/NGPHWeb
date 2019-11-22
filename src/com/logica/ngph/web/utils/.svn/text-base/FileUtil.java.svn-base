package com.logica.ngph.web.utils;


		import java.io.BufferedReader;
		import java.io.FileReader;
		import java.io.File;
		import java.io.FileWriter;
		import java.io.FileNotFoundException;
		import java.io.IOException;
import java.io.PrintWriter;

		public class FileUtil {


		  public void removeLineFromFile(String file, String lineToRemove) {

		    try {

		      File inFile = new File(file);
		      
		      if (!inFile.isFile()) {
		        System.out.println("Parameter is not an existing file");
		        return;
		      }
		       
		      //Construct the new file that will later be renamed to the original filename.
		      File tempFile = new File("c:/siddharth.xml");
		      
		      BufferedReader br = new BufferedReader(new FileReader(file));
		      PrintWriter pw = new PrintWriter(new FileWriter(tempFile));
		      
		      String line = null;

		      //Read from the original file and write to the new
		      //unless content matches data to be removed.
		      while ((line = br.readLine()) != null) {
		    	  
		        if (line.trim().equals(lineToRemove)) {
		        	System.out.println("hello");
		        	pw.println("");
		          
		        }
		        else
		        {
		        	pw.println(line);
			          pw.flush();
		        }
		      }
		      pw.close();
		      br.close();
		      
		      //Delete the original file
		      if (!inFile.delete()) {
		        System.out.println("Could not delete file");
		        return;
		      }
		      
		      //Rename the new file to the filename the original file had.
		      if (!tempFile.renameTo(inFile))
		        System.out.println("Could not rename file");
		      
		    }
		    catch (FileNotFoundException ex) {
		      ex.printStackTrace();
		    }
		    catch (IOException ex) {
		      ex.printStackTrace();
		    }
		  }

		  public static void main(String[] args) {
		    FileUtil util = new FileUtil();
		    String stringToAppendForProvide = "<jms-provider connection-factory=\"10.14.2.56:1234/Siddhartesting\" jndi-URL=\"RBIRTGS:1414/QNG_SVR_CHNL\" jndi-context-factory=\"com.ibm.mq.jms.context.WMQInitialContextFactory\" name=\"WebSphereMQ_BANK.TO.QNG.SWIFT\">\n"+
		 " <property name=\"protocol\" value=\"jms\"/>\n"+
   "<jms-bus busid=\"BANK.TO.QNG.SWIFT.GW\">\n"+
    "<jms-message-filter dest-name=\"BANK.TO.QNG.SWIFT\" dest-type=\"QUEUE\"/>\n</jms-bus>\n</jms-provider>";
		    
		    util.removeLineFromFile("c://jboss-esb.xml", stringToAppendForProvide);
		  }
		}

	