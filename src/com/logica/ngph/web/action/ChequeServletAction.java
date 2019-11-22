package com.logica.ngph.web.action;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;

import org.apache.struts2.interceptor.validation.SkipValidation;

import com.logica.AmountBean;
import com.logica.EncodeImpl;
import com.opensymphony.xwork2.ActionSupport;

public class ChequeServletAction extends ActionSupport {

	/**
	 * 		// setting the values in request to display in GenerateCheque.jsp
		request.setAttribute("qrName", "http://localhost:8080/EchequeGenerator/SendImage/"+qrName);
		request.setAttribute("nWords", nWords);
		request.setAttribute("name", request.getParameter("name"));
		request.setAttribute("mdate", request.getParameter("mdate"));
		request.setAttribute("chequeNo", chequeNo);
		request.setAttribute("amount", lAmount);
	 */
	private static final long serialVersionUID = 1L;
	private String qrName;
	private String words;
	private String name;
	private String mdate;
	private String chequeNo;
	private String amount;
	static public String imgURL;
	
	public String getQrName() {
		return qrName;
	}
	public void setQrName(String qrName) {
		this.qrName = qrName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMdate() {
		return mdate;
	}
	public void setMdate(String mdate) {
		this.mdate = mdate;
	}
	public String getChequeNo() {
		return chequeNo;
	}
	public void setChequeNo(String chequeNo) {
		this.chequeNo = chequeNo;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getWords() {
		return words;
	}
	public void setWords(String words) {
		this.words = words;
	}
	@SkipValidation
	public String loadData()
	{
		return "success";
	}
	
	public String getServlet()
	{
		long cNo = (long) Math.floor((Math.random() * 4100) + 1000);
		String qrName = "qr" + Long.toString(cNo) + ".png";

		long randomChequeNo = (long) Math.floor((Math.random() * 4100) + 1000);
		String chequeNo = Long.toString(randomChequeNo);

		EncodeImpl e = new EncodeImpl();
		String path = "/opt/wamp/www/" + qrName;
		// String path = "/home/tomcat/webapps/RBS/" + qrName;
		String hiddenValues = " Account No = 0022440022 :" 
							+ " Amount = " + getAmount() + ":"
							+ " Name = " + getName()+ ":" 
							+ " Date = " + getMdate()
							+ ":" + " Cheque NO = " + chequeNo;
		e.run(hiddenValues, path); // 0022440022:336688:446677\n

		String mAmount = getAmount();
		long lAmount = Long.parseLong(mAmount.trim());

		AmountBean amountBean = new AmountBean();
		amountBean.setNum(lAmount);
		String nWords = amountBean.getNwords();
		setWords(nWords);
		imgURL = "http://10.14.236.252:8080/NGPHWeb/SendImage/"+qrName;
		/*String Url = "http://localhost:8080/EchequeGenerator/SendImage/"+qrName;
		setQrName(Url);*/
		setChequeNo(chequeNo);
		
		// setting the values in request to display in GenerateCheque.jsp
		/*request.setAttribute("qrName", "http://localhost:8080/EchequeGenerator/SendImage/"+qrName);
		request.setAttribute("nWords", nWords);
		request.setAttribute("name", request.getParameter("name"));
		request.setAttribute("mdate", request.getParameter("mdate"));
		request.setAttribute("chequeNo", chequeNo);
		request.setAttribute("amount", lAmount);

		ServletContext application = getServletContext();
		RequestDispatcher rd = application
				.getRequestDispatcher("/GenerateCheque.jsp");
		rd.forward(request, response);*/
		return "success";
	}
	public void validate()
	{
		
	}
}
