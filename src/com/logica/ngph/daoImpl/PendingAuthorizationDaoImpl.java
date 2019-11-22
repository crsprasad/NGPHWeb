package com.logica.ngph.daoImpl;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Writer;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Properties;

import oracle.sql.CLOB;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.logica.ngph.Entity.SecUsers;
import com.logica.ngph.dao.PendingAuthorizationDao;

public class PendingAuthorizationDaoImpl extends HibernateDaoSupport implements PendingAuthorizationDao {
	
	public String delimitedStringValue(String transRef,String sequence,
			String screenData) {
		
		Session sess=getHibernateTemplate().getSessionFactory().openSession();
		
		try{
		
			String query="insert into TXN_MULTSCREENDATA (TXNREF,SEQUENCE,SCREENDATA) values (:TXNREF,:SEQUENCE,:SCREENDATA)";
			Query q = sess.createSQLQuery(query);
			q.setString("TXNREF", transRef);
			q.setString("SEQUENCE", sequence);
			q.setString("SCREENDATA", screenData);
			int result=q.executeUpdate();
		//System.out.println("No of records updated are : " + result);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			sess.close();
		}
		finally{
			sess.close();
		}
		return "success";
	}


	
	public String getCreatedUser(String trnRef) {
		return (String)getHibernateTemplate().find("select crtdUserId from GenericManager where txnRef =?",trnRef).get(0);
	}


	
	public String getUserType(String userId) {
		return (String)getHibernateTemplate().find("select userType from SecUsers where user =?",userId).get(0);
	}
	
	private final static String propName = "System.properties";

	@SuppressWarnings("null")
	public String getTranRef(String screenData,String actionMapping,String userId) {
		
		Properties props = new Properties();
		
		String key="";
		String department = "";
		String branch = "";
		CLOB clob = null; 
		SessionFactory fact=getHibernateTemplate().getSessionFactory();
		Session sess=fact.openSession();
		
		try{
			
			if(userId.equalsIgnoreCase("sa1"))
			{
				userId = "sa1";
			}
			else if(userId.equalsIgnoreCase("sa2"))
			{
				userId = "sa2";
			}else
			{
				userId = userId;
			}
			SecUsers user = (SecUsers)sess.get(SecUsers.class, userId);
			Query seq = sess.createSQLQuery("select GENERICSCREENSEQUENCE.nextval from dual" );
		    key = seq.uniqueResult().toString();
		    department = user.getUserDept();
		    branch = user.getUserBranch();
		    sess.close();
			fact.close();
			
			
			System.out.println("Connection Open");
			  Connection conn = null;
			  props.load(Thread.currentThread().getContextClassLoader().getResourceAsStream(propName));
			  
			  //props.load(new FileInputStream((propName)));
			  String url = props.getProperty("db.url");//"jdbc:Oracle:thin:@10.14.1.35:1521:NGPH";
			  PreparedStatement pstmt = null;
			  String driver = props.getProperty("db.driverClassName");//"oracle.jdbc.driver.OracleDriver";
			  String userName = props.getProperty("db.username");
			  String password1 = props.getProperty("db.password");
			  
			  org.jasypt.encryption.pbe.config.EnvironmentStringPBEConfig obj = new org.jasypt.encryption.pbe.config.EnvironmentStringPBEConfig();
			  obj.setAlgorithm("PBEWithMD5AndDES");
			  obj.setPassword(props.getProperty("makerKey"));
			  
			  
			  org.jasypt.encryption.pbe.StandardPBEStringEncryptor obj1 = new org.jasypt.encryption.pbe.StandardPBEStringEncryptor();
			  obj1.setConfig(obj);
			  String tmppassword = password1.substring(4, password1.length()-1);
			  String password = obj1.decrypt(tmppassword);
			  
			 /* String newPassword = props.getProperty("db.newDecryptedPassword");
			  String newencrytedPassword = obj1.encrypt(newPassword);
			  System.out.println("Encrypted New Password of "+newPassword + " is "+newencrytedPassword);*/
			  
			  
			  System.out.println("Password fetched  : "  +password);
			  
			  
			  try {
			  Class.forName(driver).newInstance();
			  conn = DriverManager.getConnection(url,userName,password);
			  System.out.println("Connected to the database");
			  
			  clob = CLOB.createTemporary(conn, false, CLOB.DURATION_SESSION);  
	            clob.putString(1, screenData);  
			       
			        String sql = "insert into TXN_SCREENDATA (TXNREF,STATUS,SCREEN_ID,CRTD_USER,USER_DEPT,USER_BRANCH,SCREENDATA,CREATED_DATETIME) " +
                    "VALUES(?,?,?,?,?,?,?,?)";

       pstmt = conn.prepareStatement(sql);
       	pstmt.setString(1, key);
       	pstmt.setString(2, "P");
       	pstmt.setString(3, actionMapping);
       	pstmt.setString(4, userId);
       	pstmt.setString(5, department);
       	pstmt.setString(6, branch);
       	pstmt.setClob(7, clob);
       	pstmt.setTimestamp(8, new Timestamp(System.currentTimeMillis()));
	      int res = pstmt.executeUpdate();
	      System.out.println("No of Records Update Is : - "+res);
				  
	      conn.close();
			 
			  System.out.println("Disconnected from database");
			  } catch (SQLException sqlex) {
			        sqlex.printStackTrace();
			        conn.close();
			    } catch (Exception ex) {
			      System.out.println("Unexpected exception: " + ex.toString());
			      conn.close();
			    } 

				}
		catch(Exception e)
		{
			e.printStackTrace();
			sess.close();
			fact.close();
			
		}
		return key;
	}


	
	
	public String getScreenReturn(String trnRef) {
		Clob list  = (Clob)(getHibernateTemplate().find("select screenData from GenericManager where txnRef =?",trnRef).get(0));
		String accountNo="";
		try {
			
			
			accountNo = clobToFormattedString(list);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return accountNo;
		
	}
	public List getMulScreenData(String trnRef)
	{
		List list = getHibernateTemplate().find("select screenData from MulScreenData where trnRef = ? order by sequence",trnRef);
		return list;
	}


	
	public String changeStatus(String Status,String txnRef) {
		String returnValue="success";
		
		Session sess=getHibernateTemplate().getSessionFactory().openSession();
		try{
			String query="";
			if(Status.equals("Approve")){
				query="update TXN_SCREENDATA set STATUS=:STATUS where TXNREF = :TXNREF";
				Query q = sess.createSQLQuery(query);
				q.setString("STATUS", "A");
				q.setString("TXNREF", txnRef);
				int result=q.executeUpdate();
				System.out.println("No of records updated are : " + result);
				
			}
			else if(Status.equals("Reject")){
				query="update TXN_SCREENDATA set STATUS=:STATUS where TXNREF = :TXNREF";
				Query q = sess.createSQLQuery(query);
				q.setString("STATUS", "R");
				q.setString("TXNREF", txnRef);
				int result=q.executeUpdate();
				System.out.println("No of records updated are : " + result);
			}
			sess.close();
			}
			catch(Exception e)
			{
				returnValue ="failure";
				e.printStackTrace();
				sess.close();
				
			}
		return returnValue;
	}
	
	private String readFile(String fileName, Writer writerArg)  throws FileNotFoundException, IOException {
		BufferedReader br = new BufferedReader(new FileReader(fileName));
			String nextLine = "";
			StringBuffer sb = new StringBuffer();
			while ((nextLine = br.readLine()) != null) {
			    System.out.println("Writing: " + nextLine);
			    writerArg.write(nextLine);
			    sb.append(nextLine);
			}
			// Convert the content into to a string
			String clobData = sb.toString();
			
			// Return the data.
			return clobData;
	}
	
    /*
     * Used in the synchronous and transaction report (formatted section)
     */
    public static String clobToFormattedString(Clob cl) 
    { // Fixed TIR 10562-  Changed oracle CLOB to java.sql.Clob
      if (cl == null){
          return "";
      }
      StringBuffer strOut = new StringBuffer();
      String aux;
      try {
      
          BufferedReader br = new BufferedReader(cl.getCharacterStream());
          while ((aux = br.readLine()) != null) {
            strOut.append(aux);
            strOut.append("\n");
          }
      } catch (IOException io) {
           io.printStackTrace();
          return "";
      } catch (SQLException se) {
           se.printStackTrace();
          return "";
      }
        
        return strOut.toString();
    }

public String getTempRef(String screenData,String srcMsg, String subMsg, String tempName,String actionMapping,String userId) {
		
		Properties props = new Properties();
		
		String key="";
		String department = "";
		String branch = "";
		CLOB clob = null; 
		SessionFactory fact=getHibernateTemplate().getSessionFactory();
		Session sess=fact.openSession();
		
		try{
			
			SecUsers user = (SecUsers)sess.get(SecUsers.class, userId);
			Query seq = sess.createSQLQuery("select TEMPSCREENSEQUENCE.nextval from dual" );
		    key = seq.uniqueResult().toString();
		    department = user.getUserDept();
		    branch = user.getUserBranch();
		    sess.close();
			fact.close();
			
			
			System.out.println("Connection Open");
			  Connection conn = null;
			  props.load(Thread.currentThread().getContextClassLoader().getResourceAsStream(propName));
			  
			  //props.load(new FileInputStream((propName)));
			  String url = props.getProperty("db.url");//"jdbc:Oracle:thin:@10.14.1.35:1521:NGPH";
			  PreparedStatement pstmt = null;
			  String driver = props.getProperty("db.driverClassName");//"oracle.jdbc.driver.OracleDriver";
			  String userName = props.getProperty("db.username");
			  String password1 = props.getProperty("db.password");
			  
			  org.jasypt.encryption.pbe.config.EnvironmentStringPBEConfig obj = new org.jasypt.encryption.pbe.config.EnvironmentStringPBEConfig();
			  obj.setAlgorithm("PBEWithMD5AndDES");
			  obj.setPassword(props.getProperty("makerKey"));
			  
			  
			  org.jasypt.encryption.pbe.StandardPBEStringEncryptor obj1 = new org.jasypt.encryption.pbe.StandardPBEStringEncryptor();
			  obj1.setConfig(obj);
			  String tmppassword = password1.substring(4, password1.length()-1);
			  String password = obj1.decrypt(tmppassword);
			  
			 // System.out.println("Password fetched  : "  +password);
			  
			  
			  try {
			  Class.forName(driver).newInstance();
			  conn = DriverManager.getConnection(url,userName,password);
			  System.out.println("Connected to the database");
			  
			  clob = CLOB.createTemporary(conn, false, CLOB.DURATION_SESSION);  
	            clob.putString(1, screenData);  
			       
			        String sql = "insert into TXN_TEMPLATEDATA (TEMP_REF,TEMP_ID,CRTD_USER,USER_DEPT,USER_BRANCH,DATA,TEMP_NAME,MSGS_SRC_MSGTYPE,MSGS_SRC_MSGSUBTYPE,CREATED_DATETIME) " +
                    "VALUES(?,?,?,?,?,?,?,?,?,?)";

       pstmt = conn.prepareStatement(sql);
       	pstmt.setString(1, key);
        pstmt.setString(2, actionMapping);
       	pstmt.setString(3, userId);
       	pstmt.setString(4, department);
       	pstmt.setString(5, branch);
       	pstmt.setClob(6, clob);
       	pstmt.setString(7, tempName);
    	pstmt.setString(8, srcMsg);
    	pstmt.setString(9, subMsg);
    	pstmt.setTimestamp(10, new Timestamp(System.currentTimeMillis()));
	      int res = pstmt.executeUpdate();
	      System.out.println("No of Records Update Is : - "+res);
				  
	      conn.close();
			 
			  System.out.println("Disconnected from database");
			  } catch (SQLException sqlex) {
			        sqlex.printStackTrace();
			        conn.close();
			    } catch (Exception ex) {
			      System.out.println("Unexpected exception: " + ex.toString());
			      conn.close();
			    } 

				}
		catch(Exception e)
		{
			e.printStackTrace();
			sess.close();
			fact.close();
			
		}
		return key;
	}

public String getTemplateScreen(String tempRef) {
	Clob list  = (Clob)(getHibernateTemplate().find("select tempData from MessageTemp where tempRef =?",tempRef).get(0));
	String accountNo="";
	try {
		
		
		accountNo = clobToFormattedString(list);
	} catch (Exception e) {
		e.printStackTrace();
	}
	return accountNo;
	
}



public int updateRejectStatusToPending(String txnRef) {
	int result =0;
	Session sess=getHibernateTemplate().getSessionFactory().openSession();
	try
	{
		String query="";
		if(!txnRef.isEmpty()){
			query="update TXN_SCREENDATA set STATUS=:STATUS where TXNREF = :TXNREF";
			Query q = sess.createSQLQuery(query);
			q.setString("STATUS", "P");
			q.setString("TXNREF", txnRef);
			result=q.executeUpdate();
			System.out.println("No of records updated are : " + result);
			return result;
		}
		sess.close();
	}catch(Exception e)
	{
		e.printStackTrace();
		sess.close();
		
	}
	
	return result;
}


public String delimitedTempStringValue(String transRef,String sequence,
		String screenData) {
	
	Session sess=getHibernateTemplate().getSessionFactory().openSession();
	
	try{
	
		String query="insert into TXN_TEMP_MULTSCREENDATA (TXNREF,SEQUENCE,SCREENDATA) values (:TXNREF,:SEQUENCE,:SCREENDATA)";
		Query q = sess.createSQLQuery(query);
		q.setString("TXNREF", transRef);
		q.setString("SEQUENCE", sequence);
		q.setString("SCREENDATA", screenData);
		int result=q.executeUpdate();
	//System.out.println("No of records updated are : " + result);
	}
	catch(Exception e)
	{
		e.printStackTrace();
		sess.close();
	}
	finally{
		sess.close();
	}
	return "success";
}

public List gettempMulScreenData(String tempRef)
{
	List list = getHibernateTemplate().find("select screenData from TempMulScreenData where trnRef = ? order by sequence",tempRef);
	return list;
}

}
