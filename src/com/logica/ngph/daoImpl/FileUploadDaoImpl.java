package com.logica.ngph.daoImpl;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import au.com.bytecode.opencsv.CSVReader;

import com.logica.ngph.Entity.Addresses;
import com.logica.ngph.Entity.Department;
import com.logica.ngph.Entity.IMPS_Consilet;
import com.logica.ngph.Entity.Parties;
import com.logica.ngph.common.NGPHException;
import com.logica.ngph.common.dtos.GenericFilePojo;
import com.logica.ngph.dao.FileUploadDao;


public class FileUploadDaoImpl extends HibernateDaoSupport implements FileUploadDao{
	static Logger logger = Logger.getLogger(FileUploadDaoImpl.class);
	
	HibernateTemplate hibernateTemplate;
	@Transactional
	public void saveAdresses(List<Addresses> addressesList) throws NGPHException {
		 hibernateTemplate = getHibernateTemplate();
		for(Addresses addresses:addressesList){
			hibernateTemplate.saveOrUpdate(addresses);
		}
		
	}

	
	@Transactional
	public void saveParties(List<Parties> partiesList) throws NGPHException {
		 hibernateTemplate = getHibernateTemplate();
		for(Parties parties:partiesList){
			hibernateTemplate.saveOrUpdate(parties);
		}
	}

	@Transactional(propagation=Propagation.REQUIRED)
	public void saveIMPS(List<IMPS_Consilet> consiletList) throws NGPHException {
		 hibernateTemplate = getHibernateTemplate();
			for(IMPS_Consilet consilet:consiletList){
				hibernateTemplate.saveOrUpdate(consilet);
			}
		
	}
	
	public void populateFileData(ArrayList<GenericFilePojo> dataHolder, String lineData, String TableName) throws Exception
	{
		logger.info("populateFileData(...) START...");
		try
		{
			if(dataHolder!=null && !dataHolder.isEmpty() && dataHolder.size()>0 && StringUtils.isNotBlank(lineData)&& StringUtils.isNotEmpty(lineData))
			{
				int i = 0;
				Object[] valuesArray =	new Object[50];
				String query = null;
				StringBuilder columns = new StringBuilder();
				StringBuilder count = new StringBuilder();
			
				columns.append("insert into " + TableName);
				columns.append(" ");
				columns.append("(");
			
				count.append("values");
				count.append(" ");
				count.append("(");
			
				GenericFilePojo dataObj = null;
				int startIndex = 0;
				for(int j=0;j<dataHolder.size();j++)
				{
					dataObj = dataHolder.get(j);
					if(dataObj.getColoumnName() != null)
					{
						columns.append( dataObj.getColoumnName()+ ",");
						count.append("~,");
						if (dataObj.getStaticValue().equalsIgnoreCase("y"))
						{
							valuesArray[i] = dataObj.getColoumnValue();
						}
						else
						{
							valuesArray[i] = lineData.substring(startIndex, startIndex + Integer.parseInt(dataObj.getLength()));
							startIndex = startIndex + Integer.parseInt(dataObj.getLength());
						}
						i++;
					}
				}
	
				//Fetch the final String removing the last extra , value
				String colVal = columns.toString().substring(0,columns.toString().length()-1);
				String countVal = count.toString().substring(0,count.toString().length()-1);
				//Add closing braces to final String
				colVal = colVal.concat(")");
				countVal = countVal.concat(")");
				
				//Constructing the final Query Value
				query = colVal + countVal;
				logger.info("Query Constructed : " + query);
				
	
				//to avoid null extra values and to avoid invalid column type errors
				//we need to have columns used in query and values in this array should be same in count
				Object[] actualArray = null;
				if(i < valuesArray.length)
				{
					actualArray = new Object[i];
					for(int j=0; j<actualArray.length; j++)
					{
						actualArray[j] = valuesArray[j];
					}
					//its not required then after so sending to garbage collection 
					valuesArray = null;
				}
			try
			{	
				for(int k = 0 ;k<actualArray.length ;k++)
				{
					if(query.contains("~"))
							{
						query  = query.replaceFirst("~", "'"+actualArray[k].toString()+"'");
							}
				}
				
				Session session = getHibernateTemplate().getSessionFactory().openSession();
				session.beginTransaction();			
				SQLQuery  Sqlquery= session.createSQLQuery(query); 
				Sqlquery.executeUpdate();
				session.getTransaction().commit();
				session.close();
			}
			catch (Exception e)
			{
				logger.error(e,e);
				throw new Exception(e);
			}
		}
		else
		{
			//logger.warn("Either Line Data or Columns names are empty");
		}
	}
		catch (Exception e) {
			logger.error(e, e);
			throw new Exception(e);
		}
		logger.info("populateFileData(...) ENDS...");
	}

	public int updateFileData(ArrayList<GenericFilePojo> dataHolder, String lineData,String TableName) throws Exception
	{
		logger.info("updateFileData(...) START...");
		int result = 0;
		try
		{
			if(dataHolder!=null && !dataHolder.isEmpty() && dataHolder.size()>0 && StringUtils.isNotBlank(lineData)&& StringUtils.isNotEmpty(lineData))
			{
				ArrayList<Object> valuesArray =	new ArrayList<Object>();
				
				//update Part of Query
				StringBuilder query = new StringBuilder();
				query.append("UPDATE " + TableName +" SET");
				query.append(" ");
				ArrayList<Object> columnArray = new ArrayList<Object>();
				
				//Where Part of Query
				StringBuilder whereClause = new StringBuilder();
				whereClause.append("where");
				whereClause.append(" ");
				ArrayList<Object> whereClauseArray = new ArrayList<Object>();
				
				GenericFilePojo dataObj = null;
				int startIndex = 0;
				
				for(int j=0;j<dataHolder.size();j++)
				{
					dataObj = dataHolder.get(j);
					if(dataObj.getColoumnName() != null)
					{
						query.append( dataObj.getColoumnName()+ " = ~,");
						if (dataObj.getStaticValue().equalsIgnoreCase("y"))
						{
							columnArray.add(dataObj.getColoumnValue());
						}
						else
						{
							columnArray.add(lineData.substring(startIndex, startIndex + Integer.parseInt(dataObj.getLength())));
						}
						if(dataObj.getKey().equalsIgnoreCase("y"))
						{
							whereClause.append( dataObj.getColoumnName()+ " = ~");
							whereClause.append(" AND ");
							whereClauseArray.add(lineData.substring(startIndex, startIndex + Integer.parseInt(dataObj.getLength())));
						}
						if (!dataObj.getStaticValue().equalsIgnoreCase("y"))
						{
							startIndex = startIndex + Integer.parseInt(dataObj.getLength());
						}
					}
				}
				//Fetch the final String removing the last extra , value
				String stringQuery  = query.toString().substring(0,query.toString().length()-1) + " " + whereClause.toString().substring(0,whereClause.toString().length()-5);
				
				logger.info("Query===> " + stringQuery);
				
				//Populating column array Values in Values Array from Starting
				for(int k=0;k<columnArray.size();k++)
				{
					if(columnArray.get(k)!=null )//&& StringUtils.isNotBlank(columnArray.get(k).toString()) && StringUtils.isNotEmpty(columnArray.get(k).toString()))
					{
						valuesArray.add(columnArray.get(k));
					}
				}
				
				//Populating whereClause array Values in Values Array from Last
				for(int k=0;k<whereClauseArray.size();k++)
				{
					if(whereClauseArray.get(k)!=null && StringUtils.isNotBlank(whereClauseArray.get(k).toString()) && StringUtils.isNotEmpty(whereClauseArray.get(k).toString()))
					{
						valuesArray.add(columnArray.get(k)); 
					}
				}
		
				//Storing the valuesArray into Object[]
				Object[] actualArray = new Object[valuesArray.size()];
				for(int j=0;j<valuesArray.size();j++)
				{
					actualArray[j] = valuesArray.get(j);
				}
				for(int i = 0 ;i<actualArray.length ;i++)
				{
					if(stringQuery.contains("~"))
							{
								stringQuery  = stringQuery.replaceFirst("~", "'"+actualArray[i].toString()+"'");
							}
				}
				//Sending to garbage collection once not required in Memory 
				valuesArray = null;
				whereClauseArray = null;
				columnArray = null;
				
				Session session = getHibernateTemplate().getSessionFactory().openSession();
				try{
				Query update  = session.createSQLQuery(stringQuery);
				result = update.executeUpdate();
				session.close();
				}catch (Exception e) {
					logger.error("Exception Occured In File Upload Dao Impl :- "+e);
				}
				logger.info("Values Updated " + result);
			}
			else
			{
				//logger.warn("Either Line Data or Columns names are empty ");
			}

			//If no data is updated, then insert the record
			  if( result>0)
			  {
				  logger.info("Record Present and Updated");
			  }
			  else
			  {
				  logger.info("Record not Found, Hence insert into DB");
				  //insert into DB
				  populateFileData(dataHolder,lineData,TableName);
			  }
		}
		catch (Exception e)
		{
			logger.error(e,e);
			throw new Exception(e);
		}
			logger.info("updateFileData(...) ENDS...");
			return result;
	}
	
	public int checkFileData(ArrayList<GenericFilePojo> dataHolder, String lineData,String TableName) throws Exception
	{
		logger.info("checkFileData(...) START...");
		int result = 0;
		try
		{
			if(dataHolder!=null && !dataHolder.isEmpty() && dataHolder.size()>0 && StringUtils.isNotBlank(lineData)&& StringUtils.isNotEmpty(lineData))
			{
				ArrayList<Object> valuesArray =	new ArrayList<Object>();
				
				//update Part of Query
				StringBuilder query = new StringBuilder();
				query.append("select count(*) from " + TableName);
				query.append(" ");
				ArrayList<Object> columnArray = new ArrayList<Object>();
				
				//Where Part of Query
				StringBuilder whereClause = new StringBuilder();
				whereClause.append("where");
				whereClause.append(" ");
				ArrayList<Object> whereClauseArray = new ArrayList<Object>();
				
				GenericFilePojo dataObj = null;
				int startIndex = 0;
				
				for(int j=0;j<dataHolder.size();j++)
				{
					dataObj = dataHolder.get(j);
					if(dataObj.getColoumnName() != null)
					{
						//query.append( dataObj.getColoumnName()+ " = ?,");
						columnArray.add(lineData.substring(startIndex, startIndex + Integer.parseInt(dataObj.getLength())));
						if(dataObj.getKey().equalsIgnoreCase("y"))
						{
							whereClause.append( dataObj.getColoumnName()+ " = ~");
							whereClause.append(" AND ");
							whereClauseArray.add(lineData.substring(startIndex, startIndex + Integer.parseInt(dataObj.getLength())));
						}
						startIndex = startIndex + Integer.parseInt(dataObj.getLength());
					}
				}
				//Fetch the final String removing the last extra , value
				String stringQuery  = query.toString().substring(0,query.toString().length()-1) + " " + whereClause.toString().substring(0,whereClause.toString().length()-5);
				
				logger.info("Query===> " + stringQuery);
				
				//Populating column array Values in Values Array from Starting
				for(int k=0;k<columnArray.size();k++)
				{
					if(columnArray.get(k)!=null && StringUtils.isNotBlank(columnArray.get(k).toString()) && StringUtils.isNotEmpty(columnArray.get(k).toString()))
					{
						valuesArray.add(columnArray.get(k));
					}
				}
				
				//Populating whereClause array Values in Values Array from Last
				for(int k=0;k<whereClauseArray.size();k++)
				{
					if(whereClauseArray.get(k)!=null && StringUtils.isNotBlank(whereClauseArray.get(k).toString()) && StringUtils.isNotEmpty(whereClauseArray.get(k).toString()))
					{
						valuesArray.add(columnArray.get(k)); 
					}
				}
		
				//Storing the valuesArray into Object[]
				Object[] actualArray = new Object[valuesArray.size()];
				for(int j=0;j<valuesArray.size();j++)
				{
					actualArray[j] = valuesArray.get(j);
				}
				
				//Sending to garbage collection once not required in Memory 
				valuesArray = null;
				whereClauseArray = null;
				columnArray = null;
				
				for(int i = 0 ;i<actualArray.length ;i++)
				{
					if(stringQuery.contains("~"))
							{
								stringQuery  = stringQuery.replaceFirst("~", "'"+actualArray[i].toString()+"'");
							}
				}
				
				Session session = getSession();
				Query getCount = session.createSQLQuery(stringQuery);
				List count  = getCount.list();
				session.close();
				
		//List count = getHibernateTemplate().findByNamedQuery(stringQuery, actualArray);
		if(count!=null && !count.isEmpty() && count.size()>0)
		{
			result = Integer.parseInt(count.get(0).toString());
		}
				
				logger.info("No of records found are " + result);
			}
			else
			{
				//logger.warn("Either Line Data or Columns names are empty ");
			}

		}
		catch (Exception e)
		{
			logger.error(e);
			throw new Exception(e);
		}
		logger.info("checkFileData(...) ENDS...");
			return result;
	}
	public void logFileData(String fileName, String tableName, String Status)
	{
		logger.info("logFileData() Starts");
		
		String query = "insert into TA_FILE_UPLOAD_T (BUSSINESS_DATE, SYSTIME,FILE_NAME,TABLE_NAME,FILE_STATUS) values (:BUSSINESS_DATE,:SYSTIME" +
				",:FILE_NAME,:TABLE_NAME,:FILE_STATUS)";
		
		try {
			System.out.println(getbusday_Date(getInitialisedValue("DEFBRANCH")));
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println(new Date());
		System.out.println(fileName);
		System.out.println(tableName);
		System.out.println(Status);
		try
		{	
			Session session = getHibernateTemplate().getSessionFactory().openSession();
			session.beginTransaction();
			Query insert = session.createSQLQuery(query);
			insert.setString("BUSSINESS_DATE", getbusday_Date(getInitialisedValue("DEFBRANCH")));
			insert.setDate("SYSTIME", new Date());
			insert.setString("FILE_NAME", fileName);
			insert.setString("TABLE_NAME", tableName);
			insert.setString("FILE_STATUS",Status);
			insert.executeUpdate();
			
			
			session.getTransaction().commit();
			session.close();
		}
		catch (Exception e) {
			logger.error("Error Occured while inserting File Status " , e);
			
		}
		logger.info("logFileData() Ends");
		
	}
	private String getInitialisedValue(String initEntry)throws Exception
	{
		
		String initialisedValue = null;
	
		
		try{
			
			List val= getHibernateTemplate().find("select initValue from Initialisation where initEntry =?",initEntry);
			if(!val.isEmpty())
			{
				initialisedValue=val.get(0).toString();
			}
		}catch(EmptyResultDataAccessException e)
		{
			logger.error(e,e);
		}
		catch (IncorrectResultSizeDataAccessException e) {
			logger.error(e,e);
		}catch(Exception e)
		{
			logger.error(e,e);
			throw new Exception(e);
		}
		
			
		//logInfo("getInitialisedValue() End...");
		return initialisedValue;
	}
	
	private String getbusday_Date(String branchCode)throws Exception
	{
		Date busDay_Date = null;
		String date=null;
		
		String query = "select TO_DATE(businessDay) from BusinessDayM where branch=?";
		try
		{
			List busDayList = getHibernateTemplate().find(query,branchCode);
			if(busDayList!=null && !busDayList.isEmpty() && busDayList.size()>0){
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yy");
			
				date = sdf.format((Date)busDayList.get(0));
			}
		}
		catch (EmptyResultDataAccessException e) 
		{
			logger.error(e,e);
		}
		catch (IncorrectResultSizeDataAccessException e) {
			logger.error(e,e);
		}
		catch (Exception e) {
			logger.error(e,e);
			throw new Exception(e);
		}
		return date;
	}
	
	public String populateIFSCFileData(String destFilePath, String tableName, String srcFileName, String csvFileName)throws Exception
	{
		String status = "input";
		status = generateQuery(destFilePath,tableName);	
		return status;
	}
	
	
	public String generateQuery(String destFilePath, String tableName) throws Exception
	{
		
		String query = null;
		String SQL_INSERT = "INSERT INTO ${table}(${keys}) VALUES(${values})";
	    final String TABLE_REGEX = "\\$\\{table\\}";
	    final String KEYS_REGEX = "\\$\\{keys\\}";
	    final String VALUES_REGEX = "\\$\\{values\\}";
	    String[] nextLine;
        SessionFactory fact=getHibernateTemplate().getSessionFactory();
		Session sess=fact.openSession();
		sess.beginTransaction();
		Connection con=sess.connection();
        PreparedStatement ps = null;
		 CSVReader csvReader = null;
		 char cama = ',';
		 try {
             
			 csvReader = new CSVReader(new FileReader(destFilePath), cama);
			 
			 String[] headerRow = csvReader.readNext();
			 String[] new_headerRow = new String[headerRow.length];
			 
		        if (null == headerRow) {
		            throw new FileNotFoundException("No columns defined in given CSV file." +"Please check the CSV file format.");
		        }
		        
		        for(int i=0;i<headerRow.length;i++)
		        {
		        	if(headerRow[i].toString().contains("IFSC CODE"))
		        	{
		        		new_headerRow[i] = headerRow[i].toString().replace("IFSC CODE", "PARTY_CLRSYSMMBID_MMBID");
		        	}
		        	if(headerRow[i].toString().contains("IFSC TYPE"))
		        	{
		        		new_headerRow[i] = headerRow[i].toString().replace("IFSC TYPE", "IFSC_TYPE");
		        	}
		        	if(headerRow[i].toString().contains("MICR CODE"))
		        	{
		        		new_headerRow[i] = headerRow[i].toString().replace("MICR CODE", "MICR_CODE");
		        	}
		        	if(headerRow[i].toString().contains("BANK NAME"))
		        	{
		        		new_headerRow[i] = headerRow[i].toString().replace("BANK NAME", "PARTY_NM");
		        	}
		        	if(headerRow[i].toString().contains("BRANCH NAME"))
		        	{
		        		new_headerRow[i] = headerRow[i].toString().replace("BRANCH NAME", "PARTY_BRANCH_NAME");
		        	}
		        	if(headerRow[i].toString().contains("CITY NAME"))
		        	{
		        		new_headerRow[i] = headerRow[i].toString().replace("CITY NAME", "CITY_NAME");
		        	}
		        	if(headerRow[i].toString().contains("ADDRESS"))
		        	{
		        		new_headerRow[i] = headerRow[i].toString().replace("ADDRESS", "PARTY_ADDRESS");
		        	}
		        	if(headerRow[i].toString().contains("DISTRICT"))
		        	{
		        		new_headerRow[i] = headerRow[i].toString().replace("DISTRICT", "DISTRICT_NAME");
		        	}
		        	if(headerRow[i].toString().contains("STATE"))
		        	{
		        		new_headerRow[i] = headerRow[i].toString().replace("STATE", "STATE_NAME");
		        	}
		        	if(headerRow[i].toString().contains("STD CODE"))
		        	{
		        		new_headerRow[i] = headerRow[i].toString().replace("STD CODE", "STD_CODE");
		        	}
		        	if(headerRow[i].toString().contains("PHONE NO"))
		        	{
		        		new_headerRow[i] = headerRow[i].toString().replace("PHONE NO", "PHONE_NUM");
		        	}
		        	if(headerRow[i].toString().contains("NEFT ENABLED"))
		        	{
		        		new_headerRow[i] = headerRow[i].toString().replace("NEFT ENABLED", "IS_NEFT_ENABLED");
		        	}
		        	if(headerRow[i].toString().toString().contains("RTGS ENABLED"))
		        	{
		        		new_headerRow[i] = headerRow[i].toString().toString().replace("RTGS ENABLED", "IS_RTGS_ENABLED");
		        	}
		        	if(headerRow[i].toString().contains("LCS ENABLED"))
		        	{
		        		new_headerRow[i] = headerRow[i].toString().toString().replace("LCS ENABLED", "IS_LCS_ENABLED");
		        	}
		        	if(headerRow[i].toString().contains("BGS ENABLED"))
		        	{
		        		new_headerRow[i] = headerRow[i].toString().toString().replace("BGS ENABLED", "IS_BGS_ENABLED");
		        	}
		        }
		 
		        String questionmarks = StringUtils.repeat("?,", new_headerRow.length);
		        questionmarks = (String) questionmarks.subSequence(0, questionmarks
		                .length() - 1);
		        
		         query = SQL_INSERT.replaceFirst(TABLE_REGEX, tableName);
		      
		        query = query
		                .replaceFirst(KEYS_REGEX, StringUtils.join(new_headerRow, ","));
		        query = query.replaceFirst(VALUES_REGEX, questionmarks); 
		
		            System.out.println("Final Query is  "+query);
		            con.setAutoCommit(false);
		            ps = con.prepareStatement(query);
		 		          
		            final int batchSize = 1000;
		            int count = 0;
		            while ((nextLine = csvReader.readNext()) != null) {
		 
		                if (null != nextLine) {
		                    int index = 1;
		                   
		                    for (String string : nextLine) 
		                    { 
		                    	if(!string.isEmpty())
		                    	{
				                   	if(StringUtils.isNumeric(string))
					                  {
						                   long number = Long.parseLong(string);
						                   ps.setLong(index++, number);
					                  }
			                    	else if(StringUtils.isAlphanumeric(string))
			                    	{
			       					        ps.setString(index++, string);
			                    	}
			                    	else
			                    	{
			                    		  ps.setString(index++, string);
			                    	}
		                     	}
		                    	else
		                    	{
		                    		ps.setString(index++, "");		                    		
		                    	}
		 		            }
		                    ps.addBatch();
		                }
		                if (++count % batchSize == 0) {
		                    ps.executeBatch();
		                }
		            }
		            ps.executeBatch();
		            con.commit();
		            return "success";
		 		} catch (SQLIntegrityConstraintViolationException e) {
		 			con.rollback();
		            throw e;	        
				} catch (SQLException e) {
					con.rollback();
					throw e;
				} catch (Exception e) {
		            con.rollback();
		            e.printStackTrace();
		            return "input";
		        } 
	 
	        finally {
	            if (null != ps)
	                ps.close();
	            if (null != con)
	                con.close();
	 
	            csvReader.close();
	        }
	        
	}
	
	
	public void populateLogFileData(String fileName, String tableName, String Status)
	{
		logger.info("logFileData() Starts");
		String uploadedStatus = "";
		
		String query = "insert into TA_FILE_UPLOAD_T (FILE_NAME, TABLE_NAME, FILE_UPLOADED_DATE, LASTMODIFIEDTIME, FILE_UPLOADED_STATUS) values (:FILE_NAME,:TABLE_NAME" +
				",:FILE_UPLOADED_DATE,:LASTMODIFIEDTIME,:FILE_UPLOADED_STATUS)";
		
		try {
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		if(Status.equalsIgnoreCase("success"))
		{
			uploadedStatus = "YES";
		}
		else
		{
			uploadedStatus = "NO";
		}
		try
		{	
			java.sql.Timestamp currentDateTime = new java.sql.Timestamp(new java.util.Date().getTime());
			Session session = getHibernateTemplate().getSessionFactory().openSession();
			session.beginTransaction();
			Query insert = session.createSQLQuery(query);
			insert.setString("FILE_NAME", fileName);
			insert.setString("TABLE_NAME", tableName);
			insert.setTimestamp("FILE_UPLOADED_DATE", currentDateTime);
			insert.setDate("LASTMODIFIEDTIME", currentDateTime);
			insert.setString("FILE_UPLOADED_STATUS",uploadedStatus);
			insert.executeUpdate();
			
			
			session.getTransaction().commit();
			session.close();
		}
		catch (Exception e) {
			logger.error("Error Occured while inserting File Status " , e);
			
		}
		logger.info("logFileData() Ends");
		
	}

}
