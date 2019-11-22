package com.logica.ngph.daoImpl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.logica.ngph.common.ConstantUtil;
import com.logica.ngph.common.NGPHException;
import com.logica.ngph.dao.SodEodTaskDao;
import com.logica.ngph.dtos.ArchivalTaskDto;
import com.logica.ngph.dtos.SodEodTaskTDto;
import com.logica.ngph.utils.ExceptionLog;
import com.logica.ngph.utils.SodEodUtil;

public class SodEodTaskDaoImpl extends HibernateDaoSupport implements SodEodTaskDao{
	static Logger logger = Logger.getLogger(SodEodTaskDaoImpl.class);
	@SuppressWarnings({ "unchecked", "rawtypes" })
	
	public Map<String,Object> getLocalBranch(String userId) {
		String branchCode = null;
		String branchQuery = "select userBranch from SecUsers where user=?";
		//String branchQuery = "select  LIST(Branches.branchCode,Branches.branchName) as branchName from  Branches join SecUsers where Branches.branchCode=SecUsers.userBranch";
		
		HibernateTemplate hibernateTemplate =  getHibernateTemplate();
		Map<String,Object> taskMap = new HashMap<String, Object>();
	List	secureuserList= hibernateTemplate.find(branchQuery, userId);
	
	if(!secureuserList.isEmpty()){
		branchCode = (String)secureuserList.get(0);
	}
	String branchName = null;
	String status = null;
	if(branchCode.equals(ConstantUtil.ALL)){
		String initEntry = "DEFBRANCH";
		List val= getHibernateTemplate().find("select initValue from Initialisation where initEntry =?",initEntry);
		if(!val.isEmpty())
		{
			branchCode=val.get(0).toString();
		}
		
	//	BusinessDayM	businessDay = (BusinessDayM)getHibernateTemplate().find("from BusinessDayM").get(0);
		//branchCode = businessDay.getBranch();
	}
	
	
		String branchNameQuery = "select branchName from Branches where branchCode=?";
		List	branchList= hibernateTemplate.find(branchNameQuery, branchCode);
		if(!branchList.isEmpty()){
			branchName = (String)branchList.get(0);
		}
		taskMap.put(ConstantUtil.ACTION_BRANCH, branchCode+"-"+branchName);
		String businessDayMQuery = "select businessDay ,businessDayStatus from BusinessDayM where branch=?";
		List businessDayList = hibernateTemplate.find(businessDayMQuery, branchCode);
		
		for(int i=0;i<businessDayList.size();i++){
			 Object[] obj = (Object[]) businessDayList.get(i);
			 status = 	(String)obj[1]; 
			 Date bday = (Date)obj[0];
			 SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yy");
			 String businessDate = sdf.format(bday);
			 taskMap.put(ConstantUtil.BUSINESSDATE, businessDate);
			 taskMap.put(ConstantUtil.STATUS, status);		 
		}
	List<String>  sodeodtaskList = null;
	if(status!=null && status.equals("B")){
		
	}else if(status!=null &&  status.equals("N")){
		
	}else{
		List<String> sodEodTransactionList = null;
		String branchTaskQuery = "select concat(taskId,'-',taskDesc) as taskName from SODEODTASKM where branch =? and taskFor = ? and deleted =?  order by taskSequence";

		 sodeodtaskList  = 	hibernateTemplate.find(branchTaskQuery,branchCode,status,0);
		String transanctionQuery = "select taskId from SODEODTASKT where branch=? and status ='2' and sodOrEod=? and userId=?";
		 sodEodTransactionList = hibernateTemplate.find(transanctionQuery,branchCode,status,userId);
		
		List<String> tempList = new ArrayList<String>();
	
			for(int i=0;i<sodeodtaskList.size();i++){
				String[] taskNameArray = sodeodtaskList.get(i).split("-");
				
				for(String transac:sodEodTransactionList){
					if(transac.equals(taskNameArray[0])){
						tempList.add(sodeodtaskList.get(i));
					}
				
				}
			
			}		
			sodeodtaskList.removeAll(tempList);
		
	}
	
	/*String taskID [] = null;
	List<String> taskNamelist = new ArrayList<String>();
	if(sodEodTransactionList != null){
		 taskID  = (String []) sodEodTransactionList.toArray (new String [sodEodTransactionList.size ()]);
	}*/
	//List<String> tempList = new ArrayList<String>();
	//tempList.addAll(sodeodtaskList);
	
	/*for(int i=0;i<tempList.size();i++){
		String[] taskNameArray = tempList.get(i).split("-");
		for(String transac:sodEodTransactionList){
			if(transac.equals(taskNameArray[0])){
				sodeodtaskList.remove(i);
			}
		}
		
	}*/
	
	taskMap.put(ConstantUtil.TASKLIST, sodeodtaskList);

	return taskMap;
	}

	

	private void saveSodEodT(SodEodTaskTDto sodEodTaskTDto) {
		
		getHibernateTemplate().saveOrUpdate(SodEodUtil.converSOdEodTDtoToEntity(sodEodTaskTDto));	
	
	}
/*private SODEODTASKT converSOdEodTDtoToEntity(SodEodTaskTDto sodEodTaskTDto){
	SODEODTASKT sodeodtaskt = new SODEODTASKT();
	sodeodtaskt.setBranch(sodEodTaskTDto.getBranch());
	sodeodtaskt.setBusinessDate(sodEodTaskTDto.getBusinessDate());
	sodeodtaskt.setSodOrEod(sodEodTaskTDto.getSodOrEod());
	sodeodtaskt.setTaskId(sodEodTaskTDto.getTaskId());
	sodeodtaskt.setUserId(sodEodTaskTDto.getUserId());
	return sodeodtaskt;
}

*//**
* This method is used to get the current timestamp
* @return Timestamp timeStampDate
*//*
private Timestamp getCurrentTime(){
	java.util.Date 	str_date = Calendar.getInstance().getTime();
	java.sql.Timestamp timeStampDate = new Timestamp(str_date.getTime());
	
	return timeStampDate;
}*/

@SuppressWarnings("rawtypes")
public void updateSodEodT(final String errorMessage,final String taskId){
	getHibernateTemplate().executeFind(new HibernateCallback() {
		
		 public Object doInHibernate(Session session) throws HibernateException, SQLException {
			 Query query ;
		
		    	  query = session.createQuery("update SODEODTASKT set error = :errorMessage ,endTime = :currentTime ,status = :status where taskId = :taskId");
	              query.setTimestamp("currentTime",SodEodUtil.getCurrentTime());
	              query.setString("errorMessage",errorMessage);
	              query.setString("status","3");
	              query.setString("taskId",taskId);
	              int i=  query.executeUpdate();
	              
	              
	              logger.debug("number of rows affected"+i);
               return null;
           }
       });
}
@SuppressWarnings("rawtypes")
public void updateSodEodTComp(final String taskId){
	getHibernateTemplate().executeFind(new HibernateCallback() {
		
		 public Object doInHibernate(Session session) throws HibernateException, SQLException {
			 Query query ;
		
		    	  query = session.createQuery("update SODEODTASKT set endTime = :currentTime ,status = :status where taskId = :taskId");
	              query.setTimestamp("currentTime",SodEodUtil.getCurrentTime());
	              query.setString("status","2");
	              query.setString("taskId",taskId);
	              int i=  query.executeUpdate();
	              if(logger.isDebugEnabled()){
	      			logger.debug("Number of Rows Affected"+i);
	      		}
	      		/**
	      		 * log the information when logger is in info mode 
	      		 * 
	      		 */
	      		if(logger.isInfoEnabled()){
	      			logger.info("Number of Rows Affected"+i);
	      		}
	      		/**
	      		 * log the information when logger is in error mode 
	      		 * 
	      		 */
	      		if(logger.isEnabledFor(Level.ERROR)){
	      			logger.error("Number of Rows Affected"+i);
	      		}
	              
               return null;
           }
       });
}
@SuppressWarnings("rawtypes")
private void updateBusinessDayM(final Timestamp businessDay,final String branch){
	getHibernateTemplate().executeFind(new HibernateCallback() {
		
		 public Object doInHibernate(Session session) throws HibernateException, SQLException {
			 Query businessDayMquery ;
		
			 businessDayMquery = session.createQuery("update BusinessDayM set businessDay = :businessDay  where branch = :branch");
			 businessDayMquery.setTimestamp("businessDay",businessDay); 
			 businessDayMquery.setString("branch",branch);
	              
	              int i=  businessDayMquery.executeUpdate();
	              
	              logger.info("number of rows affected in TA_BUSINESSDAYM"+i);
		    
        
              return null;
          }
      });
}
@SuppressWarnings("rawtypes")
private void updateEI(final int status){
	getHibernateTemplate().executeFind(new HibernateCallback() {
		
		 public Object doInHibernate(Session session) throws HibernateException, SQLException {
			 Query eiQuery ;
		
			 eiQuery = session.createQuery("update EI set feedIn = :feedIn , feedout =:feedout");
			 eiQuery.setInteger("feedIn", status);
			 eiQuery.setInteger("feedout", status);
			
	              int i=  eiQuery.executeUpdate();
	              if(logger.isDebugEnabled()){
		      			logger.debug("Number of Rows Affected in TA_EI"+i);
		      		}
		      		/**
		      		 * log the information when logger is in info mode 
		      		 * 
		      		 */
		      		if(logger.isInfoEnabled()){
		      			logger.info("Number of Rows Affected in TA_EI"+i);
		      		}
		      		/**
		      		 * log the information when logger is in error mode 
		      		 * 
		      		 */
		      		if(logger.isEnabledFor(Level.ERROR)){
		      			logger.error("Number of Rows Affected in TA_EI"+i);
		      		}
	             
        
              return null;
          }
      });
}

@SuppressWarnings({ "unchecked", "deprecation" })
public void setNextBusinessDate(SodEodTaskTDto sodEodTaskTDto) {
	String taskId = sodEodTaskTDto.getTaskId();
	
	
	//saveSodEodT(sodEodTaskTDto);
	boolean isError = false;
	try{
		String holidayMQuery = "select holiday from  HOLIDAYM where branch=?  and holiday > ? and holiday > ? order by holiday";
		String branch = sodEodTaskTDto.getBranch();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yy");
		
		
		HibernateTemplate hibernateTemplate = getHibernateTemplate();
		
		List<Object> holidayList =	hibernateTemplate.find(holidayMQuery,  branch,sodEodTaskTDto.getBusinessDate());
		
		 for(Object holiday:holidayList){
		try {
			
			
		} catch (Exception  e) {
			e.printStackTrace();
		}
			
		}
		
	}catch(Exception exception){
		ExceptionLog.logException(exception, logger);
		updateSodEodT(exception.getMessage(),taskId);	
		isError = true;
	}
	if(!isError){
		updateSodEodTComp(taskId);
	}
	
	
	
}

private Timestamp getUpdateBusinessDay(Timestamp businessDay){
	
	Calendar calendar = Calendar.getInstance();
    java.util.Date now = calendar.getTime();
    java.sql.Timestamp currentTimestamp = new java.sql.Timestamp(now.getTime());
    int result = currentTimestamp.compareTo(businessDay);
    long oneDay = 1 * 24 * 60 * 60 * 1000;	
  if(result == 0){
	  businessDay.setTime(businessDay.getTime()+oneDay);
  }else if(result > 0){
	  businessDay.setTime(currentTimestamp.getTime()+oneDay);
  }
   return  businessDay;
	
}


@SuppressWarnings("unchecked")

public void validatePaymentNonfinalityStatus(String branchCode,SodEodTaskTDto sodEodTaskTDto)
		throws NGPHException {
	saveSodEodT(sodEodTaskTDto);
	boolean isError = false;
	String taskId = sodEodTaskTDto.getTaskId();
	String nonFinalityQuery = "select initValue from Initialisation where initBranch=? and initEntry='NONFINSTATUSES'";
	List<String>	nonFinalityList= getHibernateTemplate().find(nonFinalityQuery, branchCode);
	String[] initValueArray = null;
	if(nonFinalityList !=null && !nonFinalityList.isEmpty() ){
		String initValue = (String)nonFinalityList.get(0);
	 initValueArray = initValue.split(";");
		
	}
	
	String messageTxQuery = "select msgStatus from NgphCanonical where msgBranch=?";
	List<String>	messageTxStatusList= getHibernateTemplate().find(messageTxQuery, branchCode);
	for(String msgStatus:messageTxStatusList){
		for(int i=0;i<initValueArray.length;i++){
			if(msgStatus.equals(initValueArray[i])){
				isError = true;
				updateSodEodT("Payments in Non Finality Status",taskId);	
					throw new  NGPHException("Payments in Non Finality Status");
				
				}
		}
	}
	if(!isError){
		updateSodEodTComp(taskId);
	}
}


@SuppressWarnings({ "unchecked", "rawtypes" })

public void moveFinalStatusToHistoryTable(final String branchCode,final SodEodTaskTDto sodEodTaskTDto) {
	boolean isError = false;
	String taskId = sodEodTaskTDto.getTaskId();
	try{
		getHibernateTemplate().execute(new HibernateCallback() {
	        public Object doInHibernate(Session session) throws HibernateException, SQLException
	        {
	        	
	        	saveSodEodT(sodEodTaskTDto);
	    		Connection connection=session.connection(); 
	    		
	    		CallableStatement st = connection.prepareCall("call PROCEOD()");
	    		

	    	int i =	st.executeUpdate();
	    		
	    		connection.close();
	    		 if(logger.isDebugEnabled()){
		      			logger.debug("Number of Rows Affected"+i);
		      		}
		      		
		      		if(logger.isInfoEnabled()){
		      			logger.info("Number of Rows Affected"+i);
		      		}
		      		
		      		if(logger.isEnabledFor(Level.ERROR)){
		      			logger.error("Number of Rows Affected"+i);
		      		}
	            return null;
	          
	        }
	    });
	}catch (Exception exception) {
		ExceptionLog.logException(exception, logger);
		isError = true;	
		updateSodEodT(exception.getMessage(),taskId);
	}
	if(!isError){
		updateSodEodTComp(taskId);
	}
	
}



public void openCloseInboundOutboundFeeds(SodEodTaskTDto sodEodTaskTDto,int status) {
	boolean isError = false;
	String taskId = sodEodTaskTDto.getTaskId();
	try{
		saveSodEodT(sodEodTaskTDto);
		updateEI(status);
	}catch (Exception exception) {
		
		
		isError = true;	
		updateSodEodT(exception.getMessage(),taskId);
	}
	if(!isError){
	updateSodEodTComp(taskId);	
	}
	
}


@SuppressWarnings({ "unchecked", "rawtypes" })

public void deleteHistoryDataBeyond(final String branchCode,final SodEodTaskTDto sodEodTaskTDto) {
	
	boolean isError = false;
	String taskId = sodEodTaskTDto.getTaskId();
	try{
		getHibernateTemplate().execute(new HibernateCallback() {
	        public Object doInHibernate(Session session) throws HibernateException, SQLException
	        {
	        	saveSodEodT(sodEodTaskTDto);
	    		Connection connection=session.connection(); 
	    		CallableStatement st = connection
	            .prepareCall("call ARCHTABLEMOVE(?)");
	    		st.setString(1, branchCode);

	    	int i =	st.executeUpdate();
	    		
	    		connection.close();
	           logger.info("updated rows"+i) ;
	            return null;
	          
	        }
	    });
	}catch (Exception exception) {
		isError = true;	
		updateSodEodT(exception.getMessage(),taskId);
	}
	if(!isError){
		updateSodEodTComp(taskId);
	}
}



@SuppressWarnings("rawtypes")

public void updateBusinessDayM(final String branchCode, final String businessDaystatus) {
	getHibernateTemplate().executeFind(new HibernateCallback() {
		
		 public Object doInHibernate(Session session) throws HibernateException, SQLException {
			 Query businessDayStatusquery ;
		
			 businessDayStatusquery = session.createQuery("update BusinessDayM set businessDayStatus = :businessDayStatus  where branch = :branch");
			 businessDayStatusquery.setString("businessDayStatus",businessDaystatus); 
			 businessDayStatusquery.setString("branch",branchCode);
	              
	              int i=  businessDayStatusquery.executeUpdate();
	              
	              logger.debug("number of rows affected in TA_BUSINESSDAYM"+i);
		    
       
             return null;
         }
     });
	
}




public List<Integer> getSodEodStatus(List<String> taskIdList) {
	HibernateTemplate hibernateTemplate = getHibernateTemplate();
	String taskStatusQuery = "select status from SODEODTASKT where taskId=?";
	List<Integer> taskStatusList = new ArrayList<Integer>();
	for(int i=0;i<taskIdList.size();i++){
		String taskId = taskIdList.get(i);
	@SuppressWarnings("unchecked")
	List<Integer> statusList = 	hibernateTemplate.find(taskStatusQuery, taskId);
		
	if(statusList !=null && !statusList.isEmpty() ){
		//Integer status = statusList.get(0);
		//taskIdList.remove(i);
		//taskIdList.add(i, taskId+";"+status);
		taskStatusList.add(statusList.get(0));
	}
	}
	return taskStatusList;

}

public void updateTaLimits() throws Exception
{
	logger.info("updateTaLimits STARTS..");
	Session session  = getSession();
	session.beginTransaction();
	try{
	String updateTaLimits = "update ta_limits set available_crlimit=credit_limit, available_drlimit=debit_limit";
	Query query= session.createQuery(updateTaLimits);
    int result = query.executeUpdate();
	
    logger.info("No of Rows Updated are : " + result);
    session.beginTransaction().commit();
    session.close();
	}catch (Exception e) {
		logger.error("Exception Occured In SodEodTaskDaoImpl :- "+e);
		session.beginTransaction().rollback();
		session.close();
	}
	logger.info("updateTaLimits ENDS..");
}

public List<String> getbranches()throws Exception
{
	logger.info("getbranches STARTS..");
	String branchesQuery = "select branchCode from Branches";
	@SuppressWarnings("unchecked")
	List<String> branchList = getHibernateTemplate().find(branchesQuery);
	logger.info("getbranches ENDS..");
	return branchList;
}


public Timestamp getCurBusDayforBranch (String Branch) throws Exception
{
	Timestamp ts = null;
	try
	{
		String busDayQuery = "Select businessDay FROM BusinessDayM WHERE branch = ?";
		@SuppressWarnings("unchecked")
		List<Timestamp> busDayList =  getHibernateTemplate().find(busDayQuery,Branch);
		if(!busDayList.isEmpty() && busDayList!=null && busDayList.size()>0)
		{
			ts = (Timestamp)busDayList.get(0);
		}
	}
	catch (EmptyResultDataAccessException e) 
	{
		logger.error(e,e);
	}
	catch (IncorrectResultSizeDataAccessException e) 
	{
		logger.error(e,e);
	}
	catch (Exception e) 
	{
		logger.error(e,e);
		throw new Exception(e);
	}
	return ts;
}



public List<ArchivalTaskDto> startArchivalAction() throws Exception {
	List <ArchivalTaskDto> archivalTaskDtoList = new ArrayList<ArchivalTaskDto>();
	
	final String propName = "System.properties";
	
	Connection liveDBCon = null;
	CallableStatement callableStatement = null;
	Properties props = new Properties();
	int retVal;
	String outErrorString = null;
	
		try{
			
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
			  
			  Class.forName(driver).newInstance();
			  conn = DriverManager.getConnection(url,userName,password);
			  
			  String getDBUSERByUserIdSql = "{call PROC_ARCH_LCBG(?,?)}";
			  
			  callableStatement = conn.prepareCall(getDBUSERByUserIdSql);
			  
			  callableStatement.registerOutParameter(1, java.sql.Types.NUMERIC);
			  callableStatement.registerOutParameter(2, java.sql.Types.VARCHAR);
			  
			  // execute getDBUSERByUserId store procedure
			  callableStatement.executeUpdate();
			  retVal = callableStatement.getInt(1);
			  outErrorString = callableStatement.getString(2);
			  
			  System.out.println("retVal is " +retVal);
			  System.out.println("outErrorString is " +outErrorString);
			  
			  archivalTaskDtoList =  getArchivalData();
			  
			  return archivalTaskDtoList;
			
		}
		
		finally {

			if (callableStatement != null) {
				callableStatement.close();
			}

			if (liveDBCon != null) {
				liveDBCon.close();
			}

		}


}



@Override
public String getBranchName() throws Exception {
	String branchName = null;
	try{
		String branchCode = null;
			String initEntry = "DEFBRANCH";
			List val= getHibernateTemplate().find("select initValue from Initialisation where initEntry =?",initEntry);
			if(!val.isEmpty())
			{
				 branchCode=val.get(0).toString();
			}
	
		
			String branchNameQuery = "select branchName from Branches where branchCode=?";
			List branchList= getHibernateTemplate().find(branchNameQuery, branchCode);
			if(!branchList.isEmpty()){
				branchName = (String)branchList.get(0);
			}
			return branchName;
		
	}
	catch (HibernateException e) {
		e.printStackTrace();
	}
	return branchName;
}



@Override
public java.sql.Date getBusinessDay() throws Exception {
	return null;
}


	public List <ArchivalTaskDto>  getArchivalData() throws Exception
	{
		try 
			{
				List <ArchivalTaskDto> archivalTaskDtoList = new ArrayList<ArchivalTaskDto>();
			String queryString ="";	
			queryString = "select tableName, operation, rowsAffected, status, reason, condition from ArchivalLCBGLog";
			List list = getHibernateTemplate().find(queryString);
			for(int i=0;i<list.size();i++)
			{
				ArchivalTaskDto archivalTaskDto= new ArchivalTaskDto();
				 Object[] obj = (Object[]) list.get(i);
				 archivalTaskDto.setTableName((String) obj[0]);
				 archivalTaskDto.setOperation((String) obj[1]);
				 archivalTaskDto.setRowsAffected((Integer) obj[2]);
				 archivalTaskDto.setStatus((String) obj[3]);
				 archivalTaskDto.setReason((String) obj[4]);
				 archivalTaskDto.setCondition((String)obj[5]);
				 archivalTaskDtoList.add(archivalTaskDto);
				 
			}
			return archivalTaskDtoList;
		} 
		catch (HibernateException e) {
			e.printStackTrace();
		}
		return null;
	}
}
