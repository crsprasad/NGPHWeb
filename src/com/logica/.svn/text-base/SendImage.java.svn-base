package com.logica;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.StringTokenizer;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SendImage extends HttpServlet {
	static final long serialVersionUID = 1L;
	String image_name = "";

	public SendImage() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String reqUrl = request.getRequestURL().toString();
		StringTokenizer tokens = new StringTokenizer(reqUrl, "/");
		int noOfTokens = tokens.countTokens();
		String tokensString[] = new String[noOfTokens];
		int count = 0;
		while (tokens.hasMoreElements()) {
			tokensString[count++] = (String) tokens.nextToken();
		}
		image_name = tokensString[noOfTokens - 1];
		String fullFilePath = "/opt/wamp/www/" + image_name;
		doDownload(fullFilePath, request, response);

	}

	private void doDownload(String filePath, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		File fileName = new File(filePath);
		int length = 0;
		ServletOutputStream outputStream = response.getOutputStream();
		String mimetype = getServletContext().getMimeType(filePath);
		response.setContentType((mimetype != null) ? mimetype : "application/octet-stream");
		response.setContentLength((int) fileName.length());
		response.setHeader("Content-Disposition", "attachment; filename=\"" + image_name + "\"");
		byte[] bbuf = new byte[100];
		DataInputStream in = new DataInputStream(new FileInputStream(fileName));
		while ((in != null) && ((length = in.read(bbuf)) != -1)) {
			outputStream.write(bbuf, 0, length);
		}
		in.close();
		outputStream.flush();
		outputStream.close();
	}
}
