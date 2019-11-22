package com.logica.ngph.daoImpl;

import java.math.BigDecimal;

import java.sql.CallableStatement;
import java.sql.Connection;

import java.sql.SQLException;
import java.sql.Types;
import java.text.DateFormat;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.net.ssl.SSLEngineResult.Status;



import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.logica.ngph.dao.QuickStackDao;
import com.logica.ngph.dtos.EIDto;
import com.logica.ngph.dtos.ReportDto;

public class QuickStackDaoImpl extends HibernateDaoSupport implements
		QuickStackDao {

	
	public String getInboundMessageCount() throws SQLException {

		List<?> count = getHibernateTemplate()
				.find("select count(direction) as InboundMessage from com.logica.ngph.Entity.ReportEntity as QuickStackEntity where direction='I'");
		System.out.println("*****************" + count);

		Object values = count.get(0);
		return values.toString();
	}

	
	public String getOutboundMessageCount() throws SQLException {
		List<?> count = getHibernateTemplate()
				.find("select count(direction) as OutboundMessage from com.logica.ngph.Entity.ReportEntity as QuickStackEntity where direction='O'");
		System.out.println("*****************" + count);

		Object values = count.get(0);
		return values.toString();
	}

	
	public String getAuthorizationCount() throws SQLException {

		String str = callProcedure("call getoutboundmessages(?)");

		return str;

	}

	
	public String getTotalAmout() throws SQLException {

		String str = callProcedure("call getinboundmessages(?)");

		return str;
	}

	public String callProcedure(String procedureName) throws SQLException {
		SessionFactory fact = getHibernateTemplate().getSessionFactory();
		Session sess = fact.openSession();
		Connection con = sess.connection();
		CallableStatement st = con.prepareCall(procedureName);
		st.registerOutParameter(1, Types.VARCHAR);

		st.executeUpdate();
		String str = st.getString(1);
		sess.flush();
		sess.close();
		fact.close();
		con.close();

		return str;

	}

	
	public String getRecivedAuthorization() throws SQLException {
		List<?> count = getHibernateTemplate()
				.find("select concat(concat(channel,'='),count(channel)) from com.logica.ngph.Entity.ReportEntity as QuickStackEntity group by channel");
		//System.out.println("*****************" + count);
		String finalstring = "";
		for (int i = 0; i < count.size(); i++) {
			if (count.size() != i - 1) {
				finalstring = finalstring + count.get(i) + ",";
			} else {
				finalstring = finalstring + count.get(i);
			}
		}
		System.out.println("***********finalstring******"
				+ finalstring.substring(0, finalstring.length() - 1));
		return finalstring.substring(0, finalstring.length() - 1);
	}

	public String calculation(String currency) {
		BigDecimal totalAmount;

		return currency;

	}

	
	public int getMessageCount() throws SQLException {
		List<?> count = getHibernateTemplate()
				.find("select count(msgRef) from com.logica.ngph.Entity.ReportEntity where msgValueDate like SYSDATE ");

		Long countedValue = (Long) count.get(0);
		System.out.println(countedValue);
		return Integer.parseInt(countedValue.toString());
	}

	
	public List<EIDto> getEI_IMPSStatus() throws SQLException {
		List <EIDto> returnList = new ArrayList<EIDto>();
		@SuppressWarnings("rawtypes")
		List status = getHibernateTemplate().find("select eiCode,eiName,status from EI");
		for(int i=0;i<status.size();i++){
			EIDto eiDto = new EIDto();
		 Object[] obj = (Object[]) status.get(i);
		 eiDto.setEICode((String)obj[0]);
		 eiDto.setEIName((String)obj[1]);
		 eiDto.setEIStatus((String)obj[2]);
		 returnList.add(eiDto);
		}
		return returnList;
	}

	
	public String getInBoundChannelCount() throws SQLException {
		List<?> count = getHibernateTemplate()
				.find("select concat(concat(channel,'='),count(channel)) from com.logica.ngph.Entity.ReportEntity as QuickStackEntity where direction='I' group by channel");

		String finalstring = "";
		for (int i = 0; i < count.size(); i++) {
			if (count.size() != i - 1) {
				finalstring = finalstring + count.get(i) + ",";
			} else {
				finalstring = finalstring + count.get(i);
			}
		}

		if (count.size() > 0)
			finalstring = finalstring.substring(0, finalstring.length() - 1);
		return finalstring;
	}

	
	public String getOutBoundChannelCount() throws SQLException {
		List<?> count = getHibernateTemplate()
				.find("select concat(concat(channel,'='),count(channel)) from com.logica.ngph.Entity.ReportEntity as QuickStackEntity where direction='O' group by channel");

		String finalstring = "";
		for (int i = 0; i < count.size(); i++) {
			if (count.size() != i - 1) {
				finalstring = finalstring + count.get(i) + ",";
			} else {
				finalstring = finalstring + count.get(i);
			}
		}
		if (count.size() > 0)
			finalstring = finalstring.substring(0, finalstring.length() - 1);
		return finalstring;
	}

	
	public String getClosingBalanceBarGraph() throws SQLException {
		String count = getHibernateTemplate().find(
				"select max(businessDay) from BusinessDayM").toString();
	//	System.out.println(count.substring(1, 11));
		List<?> data = getHibernateTemplate()
				.find("select concat(concat(currency,'='),closingBalance) from Liquidity where businessDay = to_date('"
						+ count.substring(1, 11) + "','YYYY-MM-dd')");

		String finalstring = "";
		for (int i = 0; i < data.size(); i++) {
			if (data.size() != i - 1) {
				finalstring = finalstring + data.get(i) + ",";
			} else {
				finalstring = finalstring + data.get(i);
			}
		}
		if (data.size() > 0)
			finalstring = finalstring.substring(0, finalstring.length() - 1);
		return finalstring;
	}

	
	public String getClosingBalanceLineGraph() throws SQLException {
		String currencystring = "";
		String finalstring = "";
		List<?> currency = getHibernateTemplate().find(
				"select distinct(currency) from Liquidity");
		for (int i = 0; i < currency.size(); i++) {
			finalstring = finalstring
					+ getLineString(currency.get(i).toString()) + "@";
		}

		List<?> count = getHibernateTemplate().find(
				"select distinct businessDay  from Liquidity");

		for (int k = 0; k < count.size(); k++)
			currencystring = currencystring
					+ count.get(k).toString().substring(0, 11) + ",";
		if (count.size() > 0 && !currency.equals("")) {

			currencystring = currencystring.substring(0,
					currencystring.length() - 1);
			finalstring = currencystring + "#"
					+ finalstring.substring(0, finalstring.length() - 1);

		}
		//System.out.println(finalstring);
		return finalstring;
	}

	public String getLineString(String currencey) {
		/*
		 * 24-NOV-11,15-FEB-12,17-FEB-12#EUR=590,0,789@INR=590,200,590@USD=901,346
		 * ,434
		 */

		String finalstring = "";
		List<?> count = new ArrayList<Object>();
		List<?> Busnessdate = getHibernateTemplate()
				.find("select distinct businessDay  from Liquidity order by businessDay");
		for (int i = 0; i < Busnessdate.size(); i++) {
			count = getHibernateTemplate().find(
					"select closingBalance from Liquidity where currency='"
							+ currencey + "' order by businessDay");
			System.out
					.println("select closingBalance from Liquidity where businessDay = to_date('"
							+ ((String) Busnessdate.get(i)).substring(0, 11)
							+ "','YYYY-MM-dd') and currency='"
							+ currencey
							+ "'");
		}
		String createString = currencey + "=";
		for (int i = 0; i < count.size(); i++) {
			createString = createString + count.get(i) + ",";
		}

		try {
			if (count.size() > 0)
				finalstring = createString.substring(0,
						createString.length() - 1);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return finalstring;
	}

	
	public String getPartyStackGraph() throws SQLException {
		String currencystring = "";
		String finalstring = "";
		List<String> currency = new ArrayList<String>();
		currency.add("INR");
		currency.add("ERO");
		for (int i = 0; i < currency.size(); i++) {
			finalstring = finalstring
					+ getStackGraph(currency.get(i).toString()) + "@";
		}

		List<?> count = getHibernateTemplate().find(
				"select distinct partybank  from PartyLiquidy");

		for (int k = 0; k < count.size(); k++)
			currencystring = currencystring + count.get(k).toString() + ",";
		if (count.size() > 0 && !currency.equals("")) {

			currencystring = currencystring.substring(0,
					currencystring.length() - 1);
			finalstring = currencystring + "#"
					+ finalstring.substring(0, finalstring.length() - 1);

		}
		//System.out.println(finalstring);
		return finalstring;
	}

	public String getStackGraph(String currencey) {
		/*
		 * 24-NOV-11,15-FEB-12,17-FEB-12#EUR=590,0,789@INR=590,200,590@USD=901,346
		 * ,434
		 */

		String finalstring = "";
		List<?> count = new ArrayList<Object>();
		String busday = getHibernateTemplate().find(
				"select max(businessDay) as businessDay  from BusinessDayM")
				.toString();
		//System.out.println(busday);

		List<?> Busnessdate = getHibernateTemplate().find(
				"select distinct partybank  from PartyLiquidy ");
		System.out.println("select closingBalance from PartyLiquidy where currency  ='"+currencey+"' and trunc(businessDay) = (select max(businessDay) as businessDay  from BusinessDayM)");
		for (int i = 0; i < Busnessdate.size(); i++) {
			count=getHibernateTemplate().find("select closingBalance from PartyLiquidy where currency  ='"+currencey+"' and trunc(businessDay) = (select max(businessDay) as businessDay  from BusinessDayM)") ;

		}
		String createString = currencey + "=";
		for (int i = 0; i < count.size(); i++) {
			createString = createString + count.get(i) + ",";
		}

		try {
			if (count.size() > 0)
				finalstring = createString.substring(0,
						createString.length() - 1);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return finalstring;
	}

	public String dateChanger(String aNewValue) {

		String date = aNewValue.substring(1, 11);
		String currentDate = date;
		DateFormat sdf = new SimpleDateFormat("yymmdd");
		Date dt = null;
		try {
			dt = sdf.parse(currentDate);
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		sdf = new SimpleDateFormat("yyyy-MM-dd");
		String formattedDate = sdf.format(dt);

		String yy = aNewValue.substring(1, 5);
		System.out.println(yy);
		String mm = aNewValue.substring(6, 8);
		System.out.println(mm);
		String dd = aNewValue.substring(9, 11);
		System.out.println(dd);
		System.out.println(yy + "/" + mm + "/" + dd);
		String dateconverted = dd + "/" + mm + "/" + yy;
		return dateconverted;

	}

}
