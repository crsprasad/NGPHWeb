package com.logica.ngph.daoImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import oracle.sql.CLOB;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.logica.ngph.Entity.MsgPolled;
import com.logica.ngph.Entity.NgphCanonical;
import com.logica.ngph.Entity.SecUsers;
import com.logica.ngph.common.ConstantUtil;
import com.logica.ngph.common.dtos.InfoCanonical;
import com.logica.ngph.dao.CreateBankGuaranteeDao;
import com.logica.ngph.dtos.BgMastDto;
import com.logica.ngph.dtos.CreateBankGuaranteeDto;

public class CreateBankGuaranteeDaoImpl extends HibernateDaoSupport implements
		CreateBankGuaranteeDao {
	
	private final static String propName = "System.properties";
	/*//
	@Transactional(propagation = Propagation.REQUIRED)
	public void createBG(CreateBankGuaranteeDto createBankGuaranteeDto,
			int status, String mesRef, String bgNumber, String bgDirection, NgphCanonical canonical, MsgPolled msgPolled) throws SQLException {
		Session session = getHibernateTemplate().getSessionFactory()
				.openSession();
		session.beginTransaction();
		String MSGS_MSGREF = mesRef;
		String BG_NUMBER = bgNumber;
		String BG_ISSUE_DT = convertStringToTimestamp(createBankGuaranteeDto
				.getBgDate());
		String BG_CREATE_TYPE = createBankGuaranteeDto.getBgCreateType();
		String BG_RULES_CODE = createBankGuaranteeDto.getBgRuleCode();
		String BG_DETAILS = insertNewLine(createBankGuaranteeDto.getBgDetails());
		String BG_NARRATION = createBankGuaranteeDto.getBgRuleNarration();
		int BG_STATUS = status;
		String BG_DIRECTION = bgDirection;
		String BG_ADVISINGBANK = createBankGuaranteeDto.getAdvisingBank();
		
		Query query = session
				.createSQLQuery("INSERT INTO TA_BG_MAST(MSGS_MSGREF,BG_NUMBER,BG_DIRECTION,BG_ISSUE_DT,BG_CREATE_TYPE,BG_RULES_CODE,BG_DETAILS,BG_NARRATION,BG_ADVISINGBANK,BG_STATUS) values('"
						+ MSGS_MSGREF
						+ "','"
						+ BG_NUMBER
						+ "','"
						+ BG_DIRECTION
						+ "',TO_DATE('"
						+ BG_ISSUE_DT
						+ "','"
						+ "DD-Mon-YY"
						+ "'),'"
						+ BG_CREATE_TYPE
						+ "','"
						+ BG_RULES_CODE
						+ "','"
						+ BG_DETAILS
						+ "','"
						+ BG_NARRATION
						+ "','"
						+ BG_ADVISINGBANK
						+ "','"
						+ BG_STATUS
						+ "')");
		
		query.executeUpdate();	
		session.saveOrUpdate(canonical);
		session.saveOrUpdate(msgPolled);
		session.getTransaction().commit();
		session.close();
		System.out.println("Data Insertion Complete.");
	}*/

	private String convertStringToTimestamp(Date date) {
		if (date != null) {
			try {
				Format formatter = new SimpleDateFormat("dd-MMM-yy");
				String dateTime = formatter.format(date);
				return dateTime;

			} catch (Exception ex) {
				ex.printStackTrace();
				return null;
			}
		} else {
			return null;
		}
	}
	
		
	public String getDept(String userId) {
		List branchnDepartment = getHibernateTemplate().find("select userDept,userBranch from SecUsers where user =?",userId);
		List ifsc=null;
		if(branchnDepartment!=null){
			Object[] obj = (Object[]) branchnDepartment.get(0);
			String branchCode =(String) obj[1];
			if(branchCode.equals(ConstantUtil.ALL)){
				String initEntry = "DEFBRANCH";
				List val= getHibernateTemplate().find("select initValue from Initialisation where initEntry =?",initEntry);
				if(!val.isEmpty())
				{
					branchCode=val.get(0).toString();
				}
			}
			
			ifsc = getHibernateTemplate().find("select branchIFSCCode from Branches where  branchCode =?",branchCode);
			if(ifsc!=null)
			return ifsc.get(0)+"~"+obj[0]+"~"+obj[1];
		}
		return null;
	}
	
	@Transactional(propagation = Propagation.REQUIRED)
	public void createBG(CreateBankGuaranteeDto createBankGuaranteeDto,
			int status, String mesRef, String bgNumber, String bgDirection, NgphCanonical canonical, MsgPolled msgPolled) throws Exception {
		Properties props = new Properties();		
		PreparedStatement pstmt = null;
		CLOB clob = null; 
		 Connection conn = null;
		 Session session = null;
		try{
		 session = getHibernateTemplate().getSessionFactory()
				.openSession();
		session.beginTransaction();
		final String MSGS_MSGREF = mesRef;
		final String BG_NUMBER = bgNumber;
		final Timestamp BG_ISSUE_DT = converrtStringToTimestamp(createBankGuaranteeDto
				.getBgDate());
		final String BG_CREATE_TYPE = createBankGuaranteeDto.getBgCreateType();
		final String BG_RULES_CODE = createBankGuaranteeDto.getBgRuleCode();
		final String BG_DETAILS = insertNewLine(createBankGuaranteeDto.getBgDetails());
		final String BG_NARRATION = createBankGuaranteeDto.getBgRuleNarration();
		final int BG_STATUS = status;
		final String BG_DIRECTION = bgDirection;
		final String BG_ADVISINGBANK = createBankGuaranteeDto.getAdvisingBank();
		final String BG_ISSUINGBANK = createBankGuaranteeDto.getIssuingBankCode();

			 
		 props.load(Thread.currentThread().getContextClassLoader().getResourceAsStream(propName));
		  
		  //props.load(new FileInputStream((propName)));
		  String url = props.getProperty("db.url");//"jdbc:Oracle:thin:@10.14.1.35:1521:NGPH";
		 
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
			  conn.setAutoCommit(false);
			  System.out.println("Connected to the database");
			  
			  clob = CLOB.createTemporary(conn, false, CLOB.DURATION_SESSION);  
	          clob.putString(1, BG_DETAILS);  
			       
			  String sql = "INSERT INTO TA_BG_MAST(MSGS_MSGREF,BG_NUMBER,BG_DIRECTION,BG_ISSUE_DT,BG_CREATE_TYPE,BG_RULES_CODE,BG_DETAILS,BG_NARRATION,BG_ADVISINGBANK,BG_STATUS,BG_ISSUINGBANK) values(?,?,?,?,?,?,?,?,?,?,?)";

			  pstmt = conn.prepareStatement(sql);
			    pstmt.setString(1,MSGS_MSGREF);
				pstmt.setString(2, BG_NUMBER);
				pstmt.setString(3, BG_DIRECTION);
				pstmt.setTimestamp(4, BG_ISSUE_DT);
				pstmt.setString(5, BG_CREATE_TYPE);
				pstmt.setString(6, BG_RULES_CODE);				
				pstmt.setClob(7,clob);
				pstmt.setString(8, BG_NARRATION);
				pstmt.setString(9, BG_ADVISINGBANK);
				pstmt.setInt(10, BG_STATUS);	
				pstmt.setString(11, BG_ISSUINGBANK);
			  pstmt.executeUpdate();
		
		session.saveOrUpdate(canonical);
		session.saveOrUpdate(msgPolled);
				
		session.getTransaction().commit();
		conn.commit();
		session.close();
		
		 } catch (SQLException sqlex) {
			    conn.rollback();
			    session.getTransaction().rollback();
		        throw new SQLException();
		 } catch (Exception ex) {
		    	conn.rollback();
		    	session.getTransaction().rollback();
		    	throw new Exception();		      
		 } 
		finally{
			pstmt.close();
			conn.close();
		}
	}
	private Timestamp converrtStringToTimestamp(Date date) {
		if(date != null){
			try{
				return new Timestamp(date.getTime());
			}catch(Exception ex){
				ex.printStackTrace();
				return null;
			}
		}else {
			return null;
		}
	}
	
	private String insertNewLine(String str){		 
		StringBuilder sb = new StringBuilder();
		String lineSeparator = System.getProperty("line.separator");
		for (int i = 0; i < str.length(); i++) { 
			if (i > 0 && (i % 65 == 0)) { 
				sb.append(lineSeparator);     }  
			sb.append(str.charAt(i)); 
			}  
		str = sb.toString();
		return str;
	}


	public CreateBankGuaranteeDto getBankGuarantee(String msgRef) {
		String query = "select canonical.txnReference,canonical.lcPrevAdvRef," +//2
	" canonical.lcIssueDt, canonical.lcAppRulesCode, canonical.lcAppRulesDesc," +//3
	" canonical.receiverBank, canonical.instructionsForCrdtrAgtText,canonical.msgRef, canonical.lcDocsReq1, canonical.seqNo, canonical.msgRef, canonical.senderBank, canonical.msgHost from NgphCanonical canonical  where canonical.msgRef=?"  ;
		
		List list = getHibernateTemplate().find(query,msgRef.trim());
		CreateBankGuaranteeDto createBankGuaranteeDto = new CreateBankGuaranteeDto();
		
		if(list!=null && !list.isEmpty() && list.size()>0){
			Object[] obj = (Object[]) list.get(0);
			createBankGuaranteeDto.setBgNumber((String)obj[0]);//20
			createBankGuaranteeDto.setBgCreateType((String) obj[1]);//23
			createBankGuaranteeDto.setBgDate((Date) obj[2]);//30
			createBankGuaranteeDto.setBgRuleCode((String)obj[3]);//40C
			createBankGuaranteeDto.setBgRuleNarration((String)obj[4]);//40C
			createBankGuaranteeDto.setAdvisingBank((String)obj[5]);//Receiver Bank IFSC
			createBankGuaranteeDto.setSenderToReceiverInformation((String)obj[6]);//72
			createBankGuaranteeDto.setMsgRef((String)obj[7]);//Message Reference
			createBankGuaranteeDto.setBgDetails((String)obj[8]);//77C
			createBankGuaranteeDto.setSeqNo((String)obj[9]);//Sequence
			createBankGuaranteeDto.setMsgRef((String)obj[10]);//Message Reference
			createBankGuaranteeDto.setIssuingBankCode((String)obj[11]);//Sender Bank IFSC
			createBankGuaranteeDto.setMsgHost((String)obj[12]);//Host
		}
		return createBankGuaranteeDto;
	}


	public CreateBankGuaranteeDto getAmendBg(String msgRef) {
		String query = "select canonical.txnReference,canonical.lcPrevAdvRef," +//2
		" canonical.lcIssueDt, canonical.lcAppRulesCode, canonical.lcAppRulesDesc," +//3
		" canonical.receiverBank, canonical.instructionsForCrdtrAgtText,canonical.msgRef, canonical.lcDocsReq1, canonical.relReference,canonical.lcAmndmntDt,canonical.msgHost,canonical.seqNo, canonical.senderBank from NgphCanonical canonical  where canonical.msgRef=?"  ;
			
			List list = getHibernateTemplate().find(query,msgRef.trim());
			CreateBankGuaranteeDto createBankGuaranteeDto = new CreateBankGuaranteeDto();
			
			if(list!=null && !list.isEmpty() && list.size()>0){
				Object[] obj = (Object[]) list.get(0);
				createBankGuaranteeDto.setBgNumber((String)obj[0]);//20
				createBankGuaranteeDto.setBgCreateType((String) obj[1]);
				createBankGuaranteeDto.setBgIssueDate((Date) obj[2]);
				createBankGuaranteeDto.setBgRuleCode((String)obj[3]);
				createBankGuaranteeDto.setBgRuleNarration((String)obj[4]);
				createBankGuaranteeDto.setAdvisingBank((String)obj[5]);//Receiver Bank IFSC
				createBankGuaranteeDto.setSenderToReceiverInformation((String)obj[6]);//72
				createBankGuaranteeDto.setMsgRef((String)obj[7]);//Message Reference
				createBankGuaranteeDto.setBgDetails((String) obj[8]);//77C
				createBankGuaranteeDto.setRelatedReference((String)obj[9]);//21
				createBankGuaranteeDto.setBgDate((Date)obj[10]);//30
				createBankGuaranteeDto.setMsgHost((String)obj[11]);//Host
				createBankGuaranteeDto.setSeqNo((String)obj[12]);//Sequence
				createBankGuaranteeDto.setIssuingBankCode((String)obj[13]);//Sender Bank IFSC
				
			}
			return createBankGuaranteeDto;
	}


	public List<BgMastDto> getAmendBGData(String referenceNumber) {
		try{
			List<BgMastDto> searchList = new ArrayList<BgMastDto>();
			if(referenceNumber == null){
				referenceNumber = "%";
			}else{
				referenceNumber = "%"+referenceNumber+"%";
			}
			
			@SuppressWarnings("rawtypes")
			List list= getHibernateTemplate().find("select bgNumber, msgRef from BgMast where bgStatus not in (4,6,3) and bgDirection ='O' and bgNumber like ? ",referenceNumber);
			for (int i = 0; i < list.size(); i++) {
				BgMastDto bgMastDto = new BgMastDto();
	            Object[] obj = (Object[]) list.get(i);
	            bgMastDto.setBgNumber(obj[0].toString());
	            bgMastDto.setMsgRef((String) obj[1]);
	            searchList.add(bgMastDto);
	        }
		return searchList;		
		}catch (Exception e) {
			e.printStackTrace();
			
			return null;
			
		}
	}
	public CreateBankGuaranteeDto getAckBg(String msgRef) {
		String query = "select canonical.lcNo, canonical.relReference, canonical.msgCurrency," +//3
		"canonical.lcAckDt, canonical.lcAccId, canonical.msgValueDate, canonical.lcToAmtClaimed, canonical.accountWithInstitutionAcct, canonical.accountWithInstitutionAccount, canonical.accountWithInstitutionLoc, canonical.accountWithInstitutionName, " +//8
		"canonical.lcCharges, canonical.instructionsForCrdtrAgtCode, canonical.instructionsForCrdtrAgtText, canonical.receiverBank, canonical.senderBank, canonical.msgRef, canonical.seqNo, canonical.msgHost  from NgphCanonical canonical  where canonical.msgRef=?"; //8
			
			List list = getHibernateTemplate().find(query,msgRef.trim());
			CreateBankGuaranteeDto createBankGuaranteeDto = new CreateBankGuaranteeDto();
			
			if(list!=null && !list.isEmpty() && list.size()>0){
				Object[] obj = (Object[]) list.get(0);
				createBankGuaranteeDto.setBgNumber((String)obj[0]);//20
				createBankGuaranteeDto.setRelatedReference((String) obj[1]);//21
				createBankGuaranteeDto.setBgCurrency((String)obj[2]);//32
				createBankGuaranteeDto.setDateofMessageBeingAcknowledged((Date) obj[3]);//30
				createBankGuaranteeDto.setBgAccountIdentification((String)obj[4]);//25
				createBankGuaranteeDto.setBgDebitDate((Date) obj[5]);//32
				createBankGuaranteeDto.setBgChargeAmount((BigDecimal)obj[6]);//32
				createBankGuaranteeDto.setAdviseThroughBankAcc((String)obj[7]);
				createBankGuaranteeDto.setAdviseThroughBankCode((String)obj[8]);//57A
				createBankGuaranteeDto.setAccountwithPartyLocation((String)obj[9]);//57B
				createBankGuaranteeDto.setAdviseThroughBankName((String)obj[10]);//57D
				createBankGuaranteeDto.setChargesDetails((String)obj[11]);//71B
				createBankGuaranteeDto.setSenderToReceiverInformation((String)obj[12]);//72
				createBankGuaranteeDto.setSenderToReceiverInformation((String)obj[13]);//72
				createBankGuaranteeDto.setAdvisingBank((String)obj[14]);//Receiver Bank IFSC
				createBankGuaranteeDto.setIssuingBankCode((String)obj[15]);//Sender Bank IFSC
				createBankGuaranteeDto.setMsgRef((String)obj[16]);//Message Reference
				createBankGuaranteeDto.setSeqNo((String)obj[17]);//sequence
				createBankGuaranteeDto.setMsgHost((String)obj[18]);//Host
			}
			return createBankGuaranteeDto;
	}


	public List<BgMastDto> getAckBGData(String bgNumber) {
		try{
			List<BgMastDto> searchList = new ArrayList<BgMastDto>();
			if(bgNumber == null){
				bgNumber = "%";
			}else{
				bgNumber = "%"+bgNumber+"%";
			}
			
			@SuppressWarnings("rawtypes")
			List list= getHibernateTemplate().find("select bgNumber, msgRef from BgMast where bgStatus not in (4,6,3) and bgDirection ='O' and bgNumber like ? ",bgNumber);
			for (int i = 0; i < list.size(); i++) {
				BgMastDto bgMastDto = new BgMastDto();
	            Object[] obj = (Object[]) list.get(i);
	            bgMastDto.setBgNumber(obj[0].toString());
	            bgMastDto.setMsgRef((String) obj[1]);
	            searchList.add(bgMastDto);
	        }
		return searchList;		
		}catch (Exception e) {
			e.printStackTrace();
			
			return null;
			
		}
	}

	public CreateBankGuaranteeDto getAmendBGScreenData(String bgNumber) {

		try {
			CreateBankGuaranteeDto createBankGuaranteeDto = new CreateBankGuaranteeDto();
			String query = "select  bgmast.bgNumber,  bgmast.bgDetails,  bgmast.bgIssueDate,  bgmast.bgCreateType,  bgmast.bgNoOfAmntmnt,  bgmast.advisingBank, "+
					" bgmast.issuingBank, ngph.instructionsForCrdtrAgtCode, ngph.instructionsForCrdtrAgtText from BgMast bgmast, NgphCanonical ngph " +
					" where ngph.clrgSysReference =  bgmast.bgNumber and  bgmast.bgNumber = ?";
			List list = getHibernateTemplate().find(query,bgNumber);
				for (int i = 0; i < list.size(); i++) {
					Object[] obj = (Object[]) list.get(0);
				createBankGuaranteeDto.setRelatedReference((String) obj[0]);
				Clob cl = (Clob)obj[1];
				createBankGuaranteeDto.setBgDetails(clobToFormattedString(cl));
				createBankGuaranteeDto.setBgIssueDate((Date) obj[2]);
				createBankGuaranteeDto.setBgFurtherIdentification((String) obj[3]);
				if(obj[4]!=null)
				{
					createBankGuaranteeDto.setBgNoOfAmntmnt((Integer) obj[4]);
				
				}
				else
				{
					createBankGuaranteeDto.setBgNoOfAmntmnt(0);
				}
				createBankGuaranteeDto.setAdvisingBank((String) obj[5]);
				createBankGuaranteeDto.setIssuingBankCode((String) obj[6]);
				String senderToReceiverInfoCode = (String) obj[7];
				String senderToReceiverInfoText = (String) obj[8];
				if(StringUtils.isNotEmpty(senderToReceiverInfoCode) && !senderToReceiverInfoCode.isEmpty() && StringUtils.isNotEmpty(senderToReceiverInfoText) && !senderToReceiverInfoText.isEmpty() )
				{
					createBankGuaranteeDto.setSenderToReceiverInformation(senderToReceiverInfoCode+"\n"+senderToReceiverInfoText);
				}
				else
				{
					createBankGuaranteeDto.setSenderToReceiverInformation(senderToReceiverInfoText);
				}
				
			}
			return createBankGuaranteeDto;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	
	public CreateBankGuaranteeDto getAckBGScreenData(String bgNumber) {

		try {
			CreateBankGuaranteeDto createBankGuaraneeDto = null;
			String sql = " ";
			

			return createBankGuaraneeDto;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public void createAmendBG(CreateBankGuaranteeDto createBankGuaranteeDto,
			int status, String mesRef, String bgNumber, String bgDirection,
			NgphCanonical canonical, MsgPolled msgPolled) throws Exception {
		Properties props = new Properties();		
		PreparedStatement pstmt = null;
		CLOB clob = null; 
		 Connection conn = null;
		 Session session = null;
		try{
		 session = getHibernateTemplate().getSessionFactory()
				.openSession();
		session.beginTransaction();
		final String MSGS_MSGREF = mesRef;
		final String BG_NUMBER = bgNumber;
		final Timestamp BG_ISSUE_DT = converrtStringToTimestamp(createBankGuaranteeDto
				.getBgDate());
		final String BG_CREATE_TYPE = createBankGuaranteeDto.getBgCreateType();
		final String BG_RULES_CODE = createBankGuaranteeDto.getBgRuleCode();
		final String BG_DETAILS = insertNewLine(createBankGuaranteeDto.getBgDetails());
		final String BG_NARRATION = createBankGuaranteeDto.getBgRuleNarration();
		final int BG_STATUS = status;
		final String BG_DIRECTION = bgDirection;
		final String BG_ADVISINGBANK = createBankGuaranteeDto.getAdvisingBank();
		final String BG_LAST_AMEND_REF = createBankGuaranteeDto.getRelatedReference();
		final Timestamp BG_LAST_AMEND_DATE = converrtStringToTimestamp(createBankGuaranteeDto.getBgIssueDate());
		final int BG_NOOF_MSGS = createBankGuaranteeDto.getNoOfMsg();
		final int BG_NOOF_AMENDMENTS = createBankGuaranteeDto.getBgNoOfAmntmnt();

			 
		 props.load(Thread.currentThread().getContextClassLoader().getResourceAsStream(propName));
		  
		  //props.load(new FileInputStream((propName)));
		  String url = props.getProperty("db.url");//"jdbc:Oracle:thin:@10.14.1.35:1521:NGPH";
		 
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
			  conn.setAutoCommit(false);
			  System.out.println("Connected to the database");
			  
			  clob = CLOB.createTemporary(conn, false, CLOB.DURATION_SESSION);  
	          clob.putString(1, BG_DETAILS);  
			       
			  String sql = "INSERT INTO TA_BG_MAST(MSGS_MSGREF,BG_NUMBER,BG_DIRECTION,BG_ISSUE_DT,BG_CREATE_TYPE,BG_RULES_CODE,BG_DETAILS,BG_NARRATION,BG_ADVISINGBANK,BG_STATUS,BG_LAST_AMENDMENT_REF,BG_LAST_AMENDMENT_DT,BG_NOOF_MSGS,BG_NOOF_AMNDMNTS) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

			  pstmt = conn.prepareStatement(sql);
			    pstmt.setString(1,MSGS_MSGREF);
				pstmt.setString(2, BG_NUMBER);
				pstmt.setString(3, BG_DIRECTION);
				pstmt.setTimestamp(4, BG_ISSUE_DT);
				pstmt.setString(5, BG_CREATE_TYPE);
				pstmt.setString(6, BG_RULES_CODE);				
				pstmt.setClob(7,clob);
				pstmt.setString(8, BG_NARRATION);
				pstmt.setString(9, BG_ADVISINGBANK);
				pstmt.setInt(10, BG_STATUS);
				pstmt.setString(11, BG_LAST_AMEND_REF);
				pstmt.setTimestamp(12, BG_LAST_AMEND_DATE);
				pstmt.setInt(13, BG_NOOF_MSGS);
				pstmt.setInt(14, BG_NOOF_AMENDMENTS);
				pstmt.executeUpdate();
		
		session.saveOrUpdate(canonical);
		session.saveOrUpdate(msgPolled);
				
		session.getTransaction().commit();
		conn.commit();
		session.close();
		
		 } catch (SQLException sqlex) {
			    conn.rollback();
			    session.getTransaction().rollback();
		        throw new SQLException();
		 } catch (Exception ex) {
		    	conn.rollback();
		    	session.getTransaction().rollback();
		    	throw new Exception();		      
		 } 
		finally{
			pstmt.close();
			conn.close();
		}
	}
	public void createAckBG(CreateBankGuaranteeDto createBankGuaranteeDto,
			 String mesRef, String bgNumber, String bgDirection,
			NgphCanonical canonical, MsgPolled msgPolled) throws Exception {
		Properties props = new Properties();		
		PreparedStatement pstmt = null;
		CLOB clob = null; 
		 Connection conn = null;
		 Session session = null;
		try{
		 session = getHibernateTemplate().getSessionFactory().openSession();
		 session.beginTransaction();
		final String MSGS_MSGREF = mesRef;
		final String BG_NUMBER = bgNumber;
		final String BG_DIRECTION = bgDirection;
		final String BG_REL_REFERENCE = createBankGuaranteeDto.getRelatedReference();
		final String BG_ACC_IDENTIFICATION = createBankGuaranteeDto.getBgAccountIdentification();
		final Timestamp ACK_DATE= converrtStringToTimestamp(createBankGuaranteeDto.getDateofMessageBeingAcknowledged());
		final BigDecimal BG_CHARGE_AMT = createBankGuaranteeDto.getBgChargeAmount();
		final Timestamp BG_DEBIT_DATE = converrtStringToTimestamp(createBankGuaranteeDto.getBgDebitDate());
		final String BG_CHARGE_DETAILS = createBankGuaranteeDto.getChargesDetails();
		final String BG_ACK_ISSUING_BANK = createBankGuaranteeDto.getIssuingBankCode();
		final String BG_ACK_ADVISING_BANK = createBankGuaranteeDto.getAdvisingBank();
			 
		 props.load(Thread.currentThread().getContextClassLoader().getResourceAsStream(propName));
		  
		  //props.load(new FileInputStream((propName)));
		  String url = props.getProperty("db.url");//"jdbc:Oracle:thin:@10.14.1.35:1521:NGPH";
		 
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
			  conn.setAutoCommit(false);
			  System.out.println("Connected to the database");
			  
			 
			       
			  String sql = "INSERT INTO TA_BG_MAST(MSGS_MSGREF,BG_NUMBER,BG_DIRECTION,BG_REL_REFERENCE_NO,BG_ACC_IDENTIFICATION,BG_ACK_DT,BG_AMOUNT,BG_DEBIT_DATE,BG_CHARGE_DETAILS,BG_ISSUINGBANK,BG_ADVISINGBANK ) values(?,?,?,?,?,?,?,?,?,?,?)";

			  	pstmt = conn.prepareStatement(sql);
			    pstmt.setString(1,MSGS_MSGREF);
				pstmt.setString(2, BG_NUMBER);
				pstmt.setString(3, BG_DIRECTION);
				pstmt.setString(4, BG_REL_REFERENCE);
				pstmt.setString(5, BG_ACC_IDENTIFICATION);	
				pstmt.setTimestamp(6,ACK_DATE);
				pstmt.setBigDecimal(7, BG_CHARGE_AMT);
				pstmt.setTimestamp(8, BG_DEBIT_DATE);
				pstmt.setString(9, BG_CHARGE_DETAILS);
				pstmt.setString(10, BG_ACK_ISSUING_BANK);
				pstmt.setString(11, BG_ACK_ADVISING_BANK);
				
			  pstmt.executeUpdate();
		
		session.saveOrUpdate(canonical);
		session.saveOrUpdate(msgPolled);
				
		session.getTransaction().commit();
		conn.commit();
		session.close();
		
		 } catch (SQLException sqlex) {
			    conn.rollback();
			    session.getTransaction().rollback();
		        throw new SQLException();
		 } catch (Exception ex) {
		    	conn.rollback();
		    	session.getTransaction().rollback();
		    	throw new Exception();		      
		 } 
		finally{
			pstmt.close();
			conn.close();
		}
	}


	public void createReduction(CreateBankGuaranteeDto createBankGuaranteeDto,
			String mesRef, String bgNumber, String bgDirection,
			NgphCanonical canonical, MsgPolled msgPolled) throws Exception {
	
		Properties props = new Properties();		
		PreparedStatement pstmt = null;
		CLOB clob = null; 
		 Connection conn = null;
		 Session session = null;
		try{
		 session = getHibernateTemplate().getSessionFactory().openSession();
		 session.beginTransaction();
		final String MSGS_MSGREF = mesRef;
		final String BG_DIRECTION = bgDirection;
		final String BG_NUMBER = bgNumber;
		final String RELATED_REFERENCE = createBankGuaranteeDto.getRelatedReference();
		final String BG_ACC_IDENTIFICATION = createBankGuaranteeDto.getBgAccountIdentification();
		final Timestamp REDUCTION_DATE= converrtStringToTimestamp(createBankGuaranteeDto.getReductionDate());
		final String CHARGE_CURRENCY = createBankGuaranteeDto.getBgCurrency();
		final BigDecimal BG_CHARGE_AMT = createBankGuaranteeDto.getBgChargeAmount();
		final Timestamp BG_CHARGE_DATE = converrtStringToTimestamp(createBankGuaranteeDto.getChargeDate());
		final String REDUCTION_CURRENCY = createBankGuaranteeDto.getReducedCurrency();
		final BigDecimal REDUCTION_AMT = createBankGuaranteeDto.getReducedAmt();
		final String OUTSTD_CURRENCY = createBankGuaranteeDto.getOutstandingCurrency();
		final BigDecimal OUTSTD_AMT = createBankGuaranteeDto.getOutstandingAmt();
		final String AMOUNT_SPECIFICATION = createBankGuaranteeDto.getAmountSpecification();
		final String BG_CHARGE_DETAILS = createBankGuaranteeDto.getChargesDetails();

			 
		 props.load(Thread.currentThread().getContextClassLoader().getResourceAsStream(propName));
		  
		  //props.load(new FileInputStream((propName)));
		  String url = props.getProperty("db.url");//"jdbc:Oracle:thin:@10.14.1.35:1521:NGPH";
		 
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
			  conn.setAutoCommit(false);
			  System.out.println("Connected to the database");
			  
			 
			       
			 String sql = "INSERT INTO TA_BG_MAST(MSGS_MSGREF, BG_DIRECTION, BG_NUMBER, BG_REL_REFERENCE_NO, BG_ACC_IDENTIFICATION, " +
			 		"BG_REDUCTION_DATE, BG_CHARGE_CURR_CODE, BG_CHARGE_AMT, BG_REDUCTION_CURR_CODE, BG_REDUCTION_AMT, BG_OUTSTD_CURR_CODE, BG_OUTSTD_AMT, " +
			 		"BG_AMT_SPECIFICATION, BG_CHARGE_DETAILS, BG_CHARGE_DATE) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

			  pstmt = conn.prepareStatement(sql);
			    pstmt.setString(1, MSGS_MSGREF);
				pstmt.setString(2, BG_DIRECTION);
				pstmt.setString(3, BG_NUMBER);
				pstmt.setString(4, RELATED_REFERENCE);
				pstmt.setString(5, BG_ACC_IDENTIFICATION);	
				pstmt.setTimestamp(6, REDUCTION_DATE);
				pstmt.setString(7, CHARGE_CURRENCY);
				pstmt.setBigDecimal(8, BG_CHARGE_AMT);
				pstmt.setString(9, REDUCTION_CURRENCY);
				pstmt.setBigDecimal(10, REDUCTION_AMT);
				pstmt.setString(11, OUTSTD_CURRENCY);
				pstmt.setBigDecimal(12, OUTSTD_AMT);
				pstmt.setString(13, AMOUNT_SPECIFICATION);
				pstmt.setString(14, BG_CHARGE_DETAILS);
				pstmt.setTimestamp(15, BG_CHARGE_DATE);
			  pstmt.executeUpdate();
		
		session.saveOrUpdate(canonical);
		session.saveOrUpdate(msgPolled);
		String msgRef = createBankGuaranteeDto.getMsgRef();
		if(StringUtils.isNotBlank(createBankGuaranteeDto.getRepair()) && StringUtils.isNotEmpty(createBankGuaranteeDto.getRepair())){
			Query statusQuery=null;
			statusQuery = session.createSQLQuery("update  TA_MESSAGES_TX set MSGS_MSGSTS = '99' where MSGS_MSGREF ='"+msgRef+"'");
			int result=statusQuery.executeUpdate();
			System.out.println("Result : -"+result );		
		}
		session.getTransaction().commit();
		conn.commit();
		session.close();
		
		 } catch (SQLException sqlex) {
			    conn.rollback();
			    session.getTransaction().rollback();
		        throw new SQLException();
		 } catch (Exception ex) {
		    	conn.rollback();
		    	session.getTransaction().rollback();
		    	throw new Exception();		      
		 } 
		finally{
			//pstmt.close();
			conn.close();
		}
	}


	public CreateBankGuaranteeDto getAdviceReduction(String msgRef) {
		
		String query = "select canonical.txnReference,canonical.relReference, canonical.lcAccId, canonical.lcAmndmntDt, canonical.msgCurrency," +//5
		" canonical.lcToAmtClaimed, canonical.messageCurrency, canonical.lcChargesClaimed, canonical.msgValueDate, canonical.lcAdditionalCurrCode, canonical.lcAdditionalAmt, canonical.instructedCurrency," +//7
		" canonical.lcTotalAmtClaimed, canonical.lcAddlAmts, canonical.accountWithInstitution, canonical.accountWithInstitutionLoc, canonical.accountWithInstitutionName, canonical.lcCharges, canonical.instructionsForCrdtrAgtText, "+ //7
		" canonical.instructionsForCrdtrAgtCode, canonical.msgRef,canonical.seqNo,canonical.msgHost, canonical.senderBank, canonical.receiverBank  from NgphCanonical canonical where canonical.msgRef=?";
			List list = getHibernateTemplate().find(query,msgRef.trim());
			CreateBankGuaranteeDto createBankGuaranteeDto = new CreateBankGuaranteeDto();
			if(list!=null && !list.isEmpty() && list.size()>0){
				Object[] obj = (Object[]) list.get(0);
				createBankGuaranteeDto.setBgNumber((String)obj[0]);//20
				createBankGuaranteeDto.setRelatedReference((String) obj[1]);//21
				createBankGuaranteeDto.setBgAccountIdentification((String) obj[2]);//25
				createBankGuaranteeDto.setReductionDate((Date)obj[3]);//30
				String MSG_CURRENCY_CODE = (String)obj[4];
				BigDecimal TOT_CHARGE_AMT = (BigDecimal)obj[5];
				String MESSAGE_CURRENCY = (String)obj[6];
				BigDecimal CHARGE_AMT = (BigDecimal)obj[7];
				if (!StringUtils.isEmpty(MSG_CURRENCY_CODE) && !StringUtils.isBlank(MSG_CURRENCY_CODE) && StringUtils.isEmpty(MESSAGE_CURRENCY) && StringUtils.isBlank(MSG_CURRENCY_CODE))
				{
					createBankGuaranteeDto.setBgCurrency(MSG_CURRENCY_CODE);//32B
					createBankGuaranteeDto.setBgChargeAmount(TOT_CHARGE_AMT);//32B
				}
				else if (StringUtils.isEmpty(MSG_CURRENCY_CODE) && StringUtils.isBlank(MSG_CURRENCY_CODE) && !StringUtils.isEmpty(MESSAGE_CURRENCY) && !StringUtils.isBlank(MSG_CURRENCY_CODE))
				{
					createBankGuaranteeDto.setBgCurrency(MESSAGE_CURRENCY);//32D
					createBankGuaranteeDto.setBgChargeAmount(CHARGE_AMT);//32D
				}
				createBankGuaranteeDto.setChargeDate((Date)obj[8]);//32D
				createBankGuaranteeDto.setReducedCurrency((String)obj[9]);//33B
				createBankGuaranteeDto.setReducedAmt((BigDecimal)obj[10]);//33B
				createBankGuaranteeDto.setOutstandingCurrency((String)obj[11]);//34B
				createBankGuaranteeDto.setOutstandingAmt((BigDecimal)obj[12]);//34B
				createBankGuaranteeDto.setAmountSpecification((String)obj[13]);//39C
				createBankGuaranteeDto.setAuthorisedBankCode((String)obj[14]);//57A
				createBankGuaranteeDto.setAccountWithPartyLocation((String)obj[15]);//57B
				createBankGuaranteeDto.setBgAccountWithNameandAddress((String)obj[16]);//57D
				createBankGuaranteeDto.setChargesDetails((String)obj[17]);//71B
				if(StringUtils.isNotBlank((String)obj[19]) && StringUtils.isNotEmpty((String)obj[19]))
				{
					createBankGuaranteeDto.setSenderToReceiverInformation((String)obj[19].toString().concat((String)obj[18]));//72
				}
				else
				{
					createBankGuaranteeDto.setSenderToReceiverInformation((String)obj[18]);//72
				}
				createBankGuaranteeDto.setMsgRef((String)obj[20]);
				createBankGuaranteeDto.setSeqNo((String)obj[21]);
				createBankGuaranteeDto.setMsgHost((String)obj[22]);
				createBankGuaranteeDto.setIssuingBankCode((String)obj[23]);
				createBankGuaranteeDto.setAdvisingBank((String)obj[24]);
			}
			return createBankGuaranteeDto;

	}


	public void createFreeFormatPayment(
			CreateBankGuaranteeDto createBankGuaranteeDto, String msgRef,
			InfoCanonical infoCan, MsgPolled msgPolled) throws Exception {
		Properties props = new Properties();		
		PreparedStatement pstmt = null;
		CLOB clob = null; 
		 Connection conn = null;
		 Session session = null;
		try{
		 session = getHibernateTemplate().getSessionFactory().openSession();
		 session.beginTransaction();
		final String MSGS_MSGREF = msgRef;
		final String MSGS_SRC_MSGTYPE = infoCan.getSrcMsgType();
		final String MSGS_SRC_MSGSUBTYPE = infoCan.getSrcMsgSubType();
		final String MSGS_INFORMATION = infoCan.getInfo();
		final String MSGS_DIRECTION = infoCan.getDirection();
		final String MSGS_DEPT = infoCan.getDept();
		final String MSGS_BRANCH = infoCan.getBranch();
		final String BG_NUMBER = createBankGuaranteeDto.getBgNumber();
		final String BG_REL_REFERENCE = createBankGuaranteeDto.getRelatedReference();
		final String senderBankIFSC = infoCan.getInstdagt_bkcd();
		final String receiverBankIFSC = infoCan.getInstgagt_bkcd();
		java.util.Date utilDate = new java.util.Date();
		final Timestamp LAST_MODIFIED_DATE_TIME = new Timestamp(utilDate.getTime());
		final String MSGS_DST_MSGTYPE = infoCan.getDstMsgSubType();
		final String MSGS_DST_MSGSUBTYPE = infoCan.getDstMsgSubType();
		final String MSGS_DST_CHNL = infoCan.getDstChnl();
		final String MSGS_MUR = infoCan.getMsgMur();
		final String MSGS_SEQNO = infoCan.getSeqNo();
			 
		 props.load(Thread.currentThread().getContextClassLoader().getResourceAsStream(propName));
		  
		  //props.load(new FileInputStream((propName)));
		  String url = props.getProperty("db.url");//"jdbc:Oracle:thin:@10.14.1.35:1521:NGPH";
		 
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
			  conn.setAutoCommit(false);
			  System.out.println("Connected to the database");
			  
			 
			       
			  String sql = "INSERT INTO TA_MSGS_INFORMATION(MSGS_MSGREF,MSGS_SRC_MSGTYPE,MSGS_SRC_MSGSUBTYPE,MSGS_INFORMATION,MSGS_DIRECTION,MSGS_DEPT,MSGS_BRANCH,MSGS_PMTID_INSTRID,MSGS_PMTID_RELREF,MSGS_INSTGAGT_BKCD,MSGS_INSTDAGT_BKCD,MSGS_LASTMODIFIEDTIME,MSGS_DST_MSGTYPE,MSGS_DST_MSGSUBTYPE,MSGS_DST_CHNL, MSGS_MUR, MSGS_SEQNO) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

			  	pstmt = conn.prepareStatement(sql);
			    pstmt.setString(1, MSGS_MSGREF);
			    pstmt.setString(2, MSGS_SRC_MSGTYPE);
			    pstmt.setString(3, MSGS_SRC_MSGSUBTYPE);
			    pstmt.setString(4, MSGS_INFORMATION);
			    pstmt.setString(5, MSGS_DIRECTION);
			    pstmt.setString(6, MSGS_DEPT);
			    pstmt.setString(7, MSGS_BRANCH);
			    pstmt.setString(8, BG_NUMBER);
				pstmt.setString(9, BG_REL_REFERENCE);
				pstmt.setString(10, senderBankIFSC);
				pstmt.setString(11, receiverBankIFSC);
				pstmt.setTimestamp(12, LAST_MODIFIED_DATE_TIME);
				pstmt.setString(13, MSGS_DST_MSGTYPE);
				pstmt.setString(14, MSGS_DST_MSGSUBTYPE);
				pstmt.setString(15, MSGS_DST_CHNL);
				pstmt.setString(16, MSGS_MUR);
				pstmt.setString(17, MSGS_SEQNO);
			  pstmt.executeUpdate();
		
		session.saveOrUpdate(msgPolled);
				
		session.getTransaction().commit();
		conn.commit();
		session.close();
		 } catch (SQLException sqlex) {
			    conn.rollback();
			    session.getTransaction().rollback();
		        throw new SQLException();
		 } catch (Exception ex) {
		    	conn.rollback();
		    	session.getTransaction().rollback();
		    	throw new Exception();		      
		 } 
		finally{
			pstmt.close();
			conn.close();
		}
		
	}
	
public CreateBankGuaranteeDto getFreeFormatMessage(String msgRef) {
		
	String query = "select informationEntity.txnReference, informationEntity.relatedRefrence, informationEntity.information, " + //3
			"informationEntity.senderBank, informationEntity.receiverBank, informationEntity.squenceNo "+ //3
			"from Information informationEntity where informationEntity.msgRef=? ";
			List list = getHibernateTemplate().find(query,msgRef.trim());
			CreateBankGuaranteeDto createBankGuaranteeDto = new CreateBankGuaranteeDto();
			if(list!=null && !list.isEmpty() && list.size()>0){
				Object[] obj = (Object[]) list.get(0);
				createBankGuaranteeDto.setBgNumber((String)obj[0]);
				createBankGuaranteeDto.setRelatedReference((String) obj[1]);
				createBankGuaranteeDto.setNarrative((String) obj[2]);
				createBankGuaranteeDto.setIssuingBankCode((String)obj[3]);
				createBankGuaranteeDto.setAdvisingBank((String)obj[4]);
				createBankGuaranteeDto.setSeqNo((String)obj[5]);
			}
			return createBankGuaranteeDto;

	}


public String createFreeFormatSeqNo() 
{

	String seqNo=""; 
	SessionFactory fact=getHibernateTemplate().getSessionFactory();
	Session sess=fact.openSession();
	try{
		
		Query seq = sess.createSQLQuery("select LC_BG_UI_MESSAGE_SEQ.nextval from dual" );
		String uniqueSeq = seq.uniqueResult().toString();
		seqNo = getInitialisedValue("BANKCODE")+StringUtils.leftPad(String.valueOf(uniqueSeq), 5, "0");
	    sess.close();
		fact.close();
	}
	catch(Exception e)
	{
		e.printStackTrace();
		sess.close();
		fact.close();
	}
	return seqNo;
}

public String getInitialisedValue(String initEntry)throws Exception
{
	String bankCode =null;
		List val = getHibernateTemplate().find("select initValue from Initialisation where initEntry =?",initEntry);
		if (!val.isEmpty()) {
			bankCode = val.get(0).toString();
		}
		return bankCode;
}

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
}
