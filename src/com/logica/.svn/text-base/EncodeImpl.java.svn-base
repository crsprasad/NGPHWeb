package com.logica;

import com.logica.encoder.Encoder;
import com.logica.encoder.EncoderException;
import com.logica.encoder.common.EncodeData;

public class EncodeImpl {

	
	/**
	 * @param args
	 */
	public  void run(String message, String filePath) {
	
		System.out.println("Path:: confPath:" + filePath);
		
		Encoder enc = EncoderFactory.getInstance().getEncoder(1);
		EncodeData ed = new EncodeData();
		ed.setFormat("qr");
		ed.setMessage(message);

		try {
			enc.encode(ed, filePath);
			
		      /*File f1 = new File("D:\\eclipse\\" + filePath);
		      File f2 = new File ("D:\\projects\\hibernate\\QRTest\\WebContent\\img\\" + filePath);
		      //File f2 = new File("C:\\EjbStart\\Ejb3Workspace\\Photo\\WebContent\\images\\ding.jpg");
		      InputStream in = new FileInputStream(f1);

		      OutputStream out = new FileOutputStream(f2);

		      byte[] buf = new byte[1024];
		      int len;
		      while ((len = in.read(buf)) > 0){
		        out.write(buf, 0, len);
		      }
		      in.close();
		      out.close();*/
			
			System.out.println("File generated successfully");
		}  catch (EncoderException encEx) {
			encEx.printStackTrace();
			System.out.println("File generation failure");
		} /*catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}

}
