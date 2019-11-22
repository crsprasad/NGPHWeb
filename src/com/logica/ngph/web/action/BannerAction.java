package com.logica.ngph.web.action;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;

import org.apache.log4j.Logger;

import com.logica.ngph.common.ConstantUtil;
import com.opensymphony.xwork2.ActionSupport;

public class BannerAction extends ActionSupport 
{
	private static final long serialVersionUID = -3364264106258064077L;
	static Logger logger = Logger.getLogger(BannerAction.class);
	private String bannerDetails;
	private String bannerTxtField;
	
	public String getBannerDetails() {
		return bannerDetails;
	}

	public void setBannerDetails(String bannerDetails) {
		this.bannerDetails = bannerDetails;
	}

	public String getBanner()
	{
		try{
			// Open the file that is the first 
			// command line parameter
			FileInputStream fstream = new FileInputStream(ConstantUtil.BANNERPATH);
			// Get the object of DataInputStream
			DataInputStream in = new DataInputStream(fstream);
	        BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine;
			String newline="";
			StringBuilder appendstring=new StringBuilder();
			StringBuilder appendstringfortxtarea=new StringBuilder();
			int index=0;
			int lastindex=0;
		
			String getImage="";
			//Read File Line By Line
			while ((strLine = br.readLine()) != null) 	{
				if(!strLine.equals("")){
				newline=strLine;
				//Getting the string whether is its a normal tag or New tag 
				getImage=newline.substring(newline.indexOf(" ")+2,newline.indexOf(">")-1);
								index=newline.indexOf(">");
				lastindex=newline.lastIndexOf("<");
				if(newline.substring(index+1, lastindex-1).equalsIgnoreCase(" ")){
				 if(getImage.equalsIgnoreCase("normal"))
				 {
					
					appendstring.append("<img src=\"c:/normal.gif\"/>"+newline.substring(index+1,lastindex));
					appendstringfortxtarea.append(newline.substring(index+1,lastindex));
					System.out.println(newline.substring(index+1,lastindex));
				}
				 else if(getImage.equalsIgnoreCase("new"))
				 {
					 appendstring.append("<IMG SRC="+"'c:/newimg.jpg'"+">"+newline.substring(index+1,lastindex));
					 appendstringfortxtarea.append(newline.substring(index+1,lastindex));
						System.out.println(newline.substring(index+1,lastindex));
				 }
				 else if(getImage.equalsIgnoreCase("Alert"))
				 {
					 appendstring.append("<IMG SRC="+"'c:/alertimg.jpg'"+">"+newline.substring(index+1,lastindex));
					 appendstringfortxtarea.append(newline.substring(index+1,lastindex));
						System.out.println(newline.substring(index+1,lastindex));
				 }
				 else{
						//System.out.println("hellllllloooooooooooooooo");
					 }
				}
			 System.out.println(appendstring);
			}
				else
				{
					System.out.println("value is null");
				}
				
			}
			
			
			setBannerTxtField(appendstringfortxtarea.toString());
			setBannerDetails(appendstring.toString());
			in.close();
			}catch (Exception e){//Catch exception if any
				System.err.println("Error: " + e.getMessage());
			}
			

			return "success";
	}

	public void setBannerTxtField(String bannerTxtField) {
		this.bannerTxtField = bannerTxtField;
	}

	public String getBannerTxtField() {
		return bannerTxtField;
	}
	
}
