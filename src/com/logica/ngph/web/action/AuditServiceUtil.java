package com.logica.ngph.web.action;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;

import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.lang.NullArgumentException;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextException;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.xml.sax.SAXException;


import com.logica.ngph.common.NGPHException;
import com.logica.ngph.service.AuditService;
import com.logica.ngph.service.FileUploadService;

import com.logica.ngph.service.SodEodTaskService;

public class AuditServiceUtil {
	
	static Logger serviceUtillogger = Logger.getLogger(AuditServiceUtil.class);
	
	/**
	* This method is used to get the Application Context
	* @return AuditService paymentService
	*/
	
	/*public static AuditService getAuditService() {
		AuditService auditService = null;
		try{
			ApplicationContext appcontext = new ClassPathXmlApplicationContext("WebApplicationContext.xml");
			
			auditService = (AuditService)appcontext.getBean("AuditService");
		}catch (ApplicationContextException applicationContextException) {
			
			logApplicationException(applicationContextException,serviceUtillogger);	
		}
		
		return auditService;
	}*/

	/**
	* This method is used to get the Application Context
	* @return FileUploadService fileUploadService
	*/
	/*public static FileUploadService getFileUploadService() {
		FileUploadService fileUploadService = null;
		try{
			ApplicationContext appcontext = new ClassPathXmlApplicationContext("WebApplicationContext.xml");
			
			fileUploadService = (FileUploadService)appcontext.getBean("fileUploadService");
		}catch (ApplicationContextException applicationContextException) {
		
			logApplicationException(applicationContextException,serviceUtillogger);
				
		}
		
		return fileUploadService;
	}*/
	/**
	 * used to log  NGPHException
	 * 
	 */
	public static void logNgphException(NGPHException ngphException,Logger logger) {
		/**
		 * log the information when logger is in debug mode 
		 * 
		 */
		if(logger.isDebugEnabled()){
			logger.debug(ngphException);
		}
		/**
		 * log the information when logger is in info mode 
		 * 
		 */
		if(logger.isInfoEnabled()){
			logger.info(ngphException);	
		}
		/**
		 * log the information when logger is in error mode 
		 * 
		 */
		if(logger.isEnabledFor(Level.ERROR)){
			logger.error(ngphException);	
		}
	}
	/**
	 * used to log  NullPointerException
	 * 
	 */
	public static void logNullPointerException(NullPointerException nullPointerException,Logger logger) {
		/**
		 * log the information when logger is in debug mode 
		 * 
		 */
		if(logger.isDebugEnabled()){
			logger.debug(nullPointerException);
		}
		/**
		 * log the information when logger is in info mode 
		 * 
		 */
		if(logger.isInfoEnabled()){
			logger.info(nullPointerException);	
		}
		/**
		 * log the information when logger is in error mode 
		 * 
		 */
		if(logger.isEnabledFor(Level.ERROR)){
			logger.error(nullPointerException);	
		}
	}
	/**
	 * used to log  Exception
	 * 
	 */
	public static void logException(Exception exception,Logger logger) {
		/**
		 * log the information when logger is in debug mode 
		 * 
		 */
		if(logger.isDebugEnabled()){
			logger.debug(exception);
		}
		/**
		 * log the information when logger is in info mode 
		 * 
		 */
		if(logger.isInfoEnabled()){
			logger.info(exception);	
		}
		/**
		 * log the information when logger is in error mode 
		 * 
		 */
		if(logger.isEnabledFor(Level.ERROR)){
			logger.error(exception);	
		}
	}
	/**
	 * used to log  ApplicationContextException
	 * 
	 */
	public static void logApplicationException(
			ApplicationContextException applicationContextException,Logger logger) {
		/**
		 * log the information when logger is in debug mode 
		 * 
		 */
		if(logger.isDebugEnabled()){
			logger.debug(applicationContextException);
		}
		/**
		 * log the information when logger is in info mode 
		 * 
		 */
		if(logger.isInfoEnabled()){
			logger.info(applicationContextException);	
		}
		/**
		 * log the information when logger is in error mode 
		 * 
		 */
		if(logger.isEnabledFor(Level.ERROR)){
			logger.error(applicationContextException);	
		}
	}
	/**
	 * used to log  RuntimeException
	 * 
	 */
	public static void logRunTimeException(
			RuntimeException runtimeException,Logger logger) {
		/**
		 * log the information when logger is in debug mode 
		 * 
		 */
		if(logger.isDebugEnabled()){
			logger.debug(runtimeException);
		}
		/**
		 * log the information when logger is in info mode 
		 * 
		 */
		if(logger.isInfoEnabled()){
			logger.info(runtimeException);	
		}
		/**
		 * log the information when logger is in error mode 
		 * 
		 */
		if(logger.isEnabledFor(Level.ERROR)){
			logger.error(runtimeException);	
		}
	}
	/**
	 * used to log  NullArgumentException
	 * 
	 */
	public static void logNullArgumentException(
			NullArgumentException nullArgumentException,Logger logger) {
		/**
		 * log the information when logger is in debug mode 
		 * 
		 */
		if(logger.isDebugEnabled()){
			logger.debug(nullArgumentException);
		}
		/**
		 * log the information when logger is in info mode 
		 * 
		 */
		if(logger.isInfoEnabled()){
			logger.info(nullArgumentException);	
		}
		/**
		 * log the information when logger is in error mode 
		 * 
		 */
		if(logger.isEnabledFor(Level.ERROR)){
			logger.error(nullArgumentException);	
		}
	}
	/**
	 * used to log  ClassCastException
	 * 
	 */
	public static void logClassCastException(
			ClassCastException classCastException,Logger logger) {
		/**
		 * log the information when logger is in debug mode 
		 * 
		 */
		if(logger.isDebugEnabled()){
			logger.debug(classCastException);
		}
		/**
		 * log the information when logger is in info mode 
		 * 
		 */
		if(logger.isInfoEnabled()){
			logger.info(classCastException);	
		}
		/**
		 * log the information when logger is in error mode 
		 * 
		 */
		if(logger.isEnabledFor(Level.ERROR)){
			logger.error(classCastException);	
		}
	}
	/**
	 * used to log  ParseException
	 * 
	 */
	public static void logParseException(
			ParseException parseException,Logger logger) {
		/**
		 * log the information when logger is in debug mode 
		 * 
		 */
		if(logger.isDebugEnabled()){
			logger.debug(parseException);
		}
		/**
		 * log the information when logger is in info mode 
		 * 
		 */
		if(logger.isInfoEnabled()){
			logger.info(parseException);	
		}
		/**
		 * log the information when logger is in error mode 
		 * 
		 */
		if(logger.isEnabledFor(Level.ERROR)){
			logger.error(parseException);	
		}
	}
	/**
	 * used to log  FileNotFoundException
	 * 
	 */
	public static void logFileNotFoundException(
			FileNotFoundException fileNotFoundException,Logger logger) {
		/**
		 * log the information when logger is in debug mode 
		 * 
		 */
		if(logger.isDebugEnabled()){
			logger.debug(fileNotFoundException);
		}
		/**
		 * log the information when logger is in info mode 
		 * 
		 */
		if(logger.isInfoEnabled()){
			logger.info(fileNotFoundException);	
		}
		/**
		 * log the information when logger is in error mode 
		 * 
		 */
		if(logger.isEnabledFor(Level.ERROR)){
			logger.error(fileNotFoundException);	
		}
	}
	/**
	 * used to log  IOException
	 * 
	 */
	public static void logIOException(
			IOException ioException,Logger logger) {
		/**
		 * log the information when logger is in debug mode 
		 * 
		 */
		if(logger.isDebugEnabled()){
			logger.debug(ioException);
		}
		/**
		 * log the information when logger is in info mode 
		 * 
		 */
		if(logger.isInfoEnabled()){
			logger.info(ioException);	
		}
		/**
		 * log the information when logger is in error mode 
		 * 
		 */
		if(logger.isEnabledFor(Level.ERROR)){
			logger.error(ioException);	
		}
	}
	/**
	 * used to log  ArrayIndexOutOfBoundsException
	 * 
	 */
	public static void logArrayIndexOutOfBoundException(
			ArrayIndexOutOfBoundsException arrayIndexOutOfBoundsException,Logger logger) {
		/**
		 * log the information when logger is in debug mode 
		 * 
		 */
		if(logger.isDebugEnabled()){
			logger.debug(arrayIndexOutOfBoundsException);
		}
		/**
		 * log the information when logger is in info mode 
		 * 
		 */
		if(logger.isInfoEnabled()){
			logger.info(arrayIndexOutOfBoundsException);	
		}
		/**
		 * log the information when logger is in error mode 
		 * 
		 */
		if(logger.isEnabledFor(Level.ERROR)){
			logger.error(arrayIndexOutOfBoundsException);	
		}
	}
	/**
	 * used to log  SAXException
	 * 
	 */
	public static void logSaxException(
			SAXException saxException,Logger logger) {
		/**
		 * log the information when logger is in debug mode 
		 * 
		 */
		if(logger.isDebugEnabled()){
			logger.debug(saxException);
		}
		/**
		 * log the information when logger is in info mode 
		 * 
		 */
		if(logger.isInfoEnabled()){
			logger.info(saxException);	
		}
		/**
		 * log the information when logger is in error mode 
		 * 
		 */
		if(logger.isEnabledFor(Level.ERROR)){
			logger.error(saxException);	
		}
	}
	/**
	 * used to log  ParserConfigurationException
	 * 
	 */
	public static void logParserCofigurationException(
			ParserConfigurationException parserConfigurationException,Logger logger) {
		/**
		 * log the information when logger is in debug mode 
		 * 
		 */
		if(logger.isDebugEnabled()){
			logger.debug(parserConfigurationException);
		}
		/**
		 * log the information when logger is in info mode 
		 * 
		 */
		if(logger.isInfoEnabled()){
			logger.info(parserConfigurationException);	
		}
		/**
		 * log the information when logger is in error mode 
		 * 
		 */
		if(logger.isEnabledFor(Level.ERROR)){
			logger.error(parserConfigurationException);	
		}
	}
	/**
	* This method is used to get the Application Context
	* @return  fileUploadService
	*/
	public static SodEodTaskService getSodEodTaskService() {
		SodEodTaskService sodEodTaskService  = null;
		try{
			ApplicationContext appcontext = new ClassPathXmlApplicationContext("WebApplicationContext.xml");
			
			sodEodTaskService = (SodEodTaskService)appcontext.getBean("sodEodService");
		}catch (ApplicationContextException applicationContextException) {
			
			logApplicationException(applicationContextException,serviceUtillogger);
			
		}
		
		return sodEodTaskService;
	}
	/*public static  PaymentMessageService getpaymentMessageService() {
		PaymentMessageService paymentMessageService = null;

		try{
			ApplicationContext appcontext = new ClassPathXmlApplicationContext("WebApplicationContext.xml");
		 paymentMessageService = 	 (PaymentMessageService) appcontext.getBean("paymentMessageService");
			//paymentMessageService = (PaymentMessageService)appcontext.getBean("paymentMessageService");
		}catch (ApplicationContextException applicationContextException) {
			
		logger.debug(ConstantUtil.PAYMENT_ACTION+ applicationContextException);	
		}
		
		return paymentMessageService;
	}*/	
	
	public static ApplicationContext getApplicationContext() {
		ApplicationContext appcontext = null;
		try{
			 appcontext = new ClassPathXmlApplicationContext("WebApplicationContext.xml");
			
			
		}catch (ApplicationContextException applicationContextException) {
			
		logApplicationException(applicationContextException,serviceUtillogger);
		}
		
		return appcontext;
	}


}
