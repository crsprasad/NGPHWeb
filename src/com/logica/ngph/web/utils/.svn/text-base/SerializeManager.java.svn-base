package com.logica.ngph.web.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

import org.jboss.util.Base64;



public class SerializeManager<T> {
	
	public <T> Object getSerializedObject(String objectString)
	{
		try{
			
			byte[] decoded = Base64.decode(objectString);
            
            FileOutputStream foss = new FileOutputStream("targetUserObject.ser");
            foss.write(decoded);
            foss.close();
            T testDTO = null;
            
            FileInputStream fiss = new FileInputStream("targetUserObject.ser");
            BufferedInputStream bufferee = new BufferedInputStream( fiss );
            ObjectInputStream oiss = new ObjectInputStream(bufferee);
            testDTO = (T)oiss.readObject();
            oiss.close();
            System.out.println("object2: " + testDTO); 
          
            return testDTO;

		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	
	public String serializeObject(String userId, T obj)
	{
		
		try
		{
			String fileName ="serial_"+userId+".ser";
			FileOutputStream fos = new FileOutputStream(fileName);
	        OutputStream buffer = new BufferedOutputStream( fos );
	        ObjectOutputStream oos = new ObjectOutputStream(buffer);
	        oos.writeObject(obj);
	        oos.flush();
	        oos.close();
	        File file = new File(fileName);
	        byte[] byteArray = new byte[(int) file.length()];
	        FileInputStream fis = new FileInputStream(file); 
	        fis.read(byteArray);
	        String objectString = Base64.encodeBytes(byteArray);
	        System.out.print("Object String :"+objectString);
			return objectString;
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

}
