package com.logica.ngph.web.action;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Date;

import org.apache.log4j.Logger;

import com.logica.ngph.common.ConstantUtil;
import com.opensymphony.xwork2.ActionSupport;


public class FeedbackAction extends ActionSupport 
{
	private static final long serialVersionUID = -3364264106258064077L;
	static Logger logger = Logger.getLogger(BannerAction.class);
	private String feedbacks;
	public void setFeedbacks(String feedbacks) {
		this.feedbacks = feedbacks;
	}
	public String getFeedbacks() {
		return feedbacks;
	}
	private String rating;
	
	public String onloadFeedbackForm()
	{
		return "success";
	}
	public String getFeedback()
	{
		try{
			
			if(!getFeedbacks().equals(null) & !getRating().equals(null)){
			Date date=new Date();
			  // Create file 
				String filename=date.toString().replace(":", "_");
				System.out.println(ConstantUtil.FEEDBACKPATH+filename+".txt");
			  FileWriter fstream = new FileWriter(ConstantUtil.FEEDBACKPATH+filename+".txt");
			  BufferedWriter out = new BufferedWriter(fstream);
			  out.write(getFeedbacks()+"\n\n"+"RATING : -"+getRating());
			  //Close the output stream
			  out.close();
			}
			else
			{
				addFieldError(feedbacks, "Please Provide the FeedBacks and Ratings");
			}
			  }catch (Exception e){//Catch exception if any
			  System.err.println("Error: " + e.getMessage());
			  }
		
		return "success";
	}
	public void setRating(String rating) {
		this.rating = rating;
	}
	public String getRating() {
		return rating;
	}
}
