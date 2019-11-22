package com.logica.ngph.web.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ChangeDate {
	
	public String dateChanger(String aNewValue) throws ParseException{
		String date = aNewValue.substring(0, 6);
	       String currentDate = date;
	       DateFormat sdf = new SimpleDateFormat("yymmdd");
	       Date dt = sdf.parse(currentDate);
	       sdf = new SimpleDateFormat("yyyymmdd");
	       String formattedDate = sdf.format(dt);
	       
	       String yy = aNewValue.substring(0, 4);
	       System.out.println(yy);
	       String mm = aNewValue.substring(5, 7);
	       System.out.println(mm);
	       String dd = aNewValue.substring(8, 10);
	       System.out.println(dd);
	       System.out.println(yy+"/"+mm+"/"+dd );
	       String dateconverted=dd+"/"+mm+"/"+yy;
		return dateconverted;
		
	}

}
