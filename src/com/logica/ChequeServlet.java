package com.logica;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ChequeServlet
 */
public class ChequeServlet extends HttpServlet {
	private static final long serialVersionUID = 903098L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		long cNo = (long) Math.floor((Math.random() * 4100) + 1000);
		String qrName = "qr" + Long.toString(cNo) + ".png";

		long randomChequeNo = (long) Math.floor((Math.random() * 4100) + 1000);
		String chequeNo = Long.toString(randomChequeNo);

		EncodeImpl e = new EncodeImpl();
		String path = "/opt/wamp/www/" + qrName;
		// String path = "/home/tomcat/webapps/RBS/" + qrName;
		String hiddenValues = " Account No = 0022440022 :" 
							+ " Amount = " + request.getParameter("amount") + ":"
							+ " Name = " + request.getParameter("name")+ ":" 
							+ " Date = " + request.getParameter("mdate")
							+ ":" + " Cheque NO = " + chequeNo;
		e.run(hiddenValues, path); // 0022440022:336688:446677\n

		String mAmount = request.getParameter("amount");
		long lAmount = Long.parseLong(mAmount.trim());

		AmountBean amountBean = new AmountBean();
		amountBean.setNum(lAmount);
		String nWords = amountBean.getNwords();
		// setting the values in request to display in GenerateCheque.jsp
		request.setAttribute("qrName", "http://localhost:8080/EchequeGenerator/SendImage/"+qrName);
		request.setAttribute("nWords", nWords);
		request.setAttribute("name", request.getParameter("name"));
		request.setAttribute("mdate", request.getParameter("mdate"));
		request.setAttribute("chequeNo", chequeNo);
		request.setAttribute("amount", lAmount);

		ServletContext application = getServletContext();
		RequestDispatcher rd = application
				.getRequestDispatcher("/GenerateCheque.jsp");
		rd.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
