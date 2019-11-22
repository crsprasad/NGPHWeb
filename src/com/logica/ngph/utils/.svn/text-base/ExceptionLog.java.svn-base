package com.logica.ngph.utils;

import java.text.ParseException;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

public class ExceptionLog {
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
	 * used to log  ParseException
	 * 
	 */
	public static void logParseException(ParseException parseException,Logger logger) {
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
}
