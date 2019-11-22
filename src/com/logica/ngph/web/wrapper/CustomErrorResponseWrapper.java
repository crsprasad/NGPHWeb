package com.logica.ngph.web.wrapper;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

public class CustomErrorResponseWrapper extends HttpServletResponseWrapper {

	private int statusCode;

	public CustomErrorResponseWrapper(HttpServletResponse response) {
		super(response);
	}

	public int getStatus() {
		return statusCode;
	}

	public void sendError(int errorCode) throws IOException {
		this.statusCode = adjust(errorCode);
		super.sendError(this.statusCode);
	}

	public void sendError(int errorCode, String errorMessage)
			throws IOException {
		this.statusCode = adjust(errorCode);
		super.sendError(this.statusCode, errorMessage);
	}

	public void setStatus(int statusCode) {
		this.statusCode = adjust(statusCode);
		super.setStatus(this.statusCode);
	}

	public void setStatus(int statusCode, String message) {
		this.statusCode = adjust(statusCode);
		super.setStatus(this.statusCode, message);
	}

	private int adjust(int errorCode) {
		if(errorCode == HttpServletResponse.SC_FORBIDDEN ||errorCode == HttpServletResponse.SC_INTERNAL_SERVER_ERROR){
			return HttpServletResponse.SC_NOT_FOUND;
		}
		return errorCode;
	}

}
