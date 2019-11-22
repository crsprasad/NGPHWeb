package com.logica.encoder;

public class EncoderException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EncoderException(Exception e) {
	    super("Encoding Exception: "+e);
	  }
}
