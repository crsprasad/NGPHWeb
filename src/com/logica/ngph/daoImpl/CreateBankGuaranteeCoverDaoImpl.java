/**
 * 
 */
package com.logica.ngph.daoImpl;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import com.logica.ngph.Entity.MsgPolled;
import com.logica.ngph.Entity.NgphCanonical;
import com.logica.ngph.common.ConstantUtil;
import com.logica.ngph.dao.CreateBankGuaranteeCoverDao;
import com.logica.ngph.dtos.BankGuaranteeCoverDto;
import com.logica.ngph.dtos.CreateBankGuaranteeDto;

import oracle.sql.CLOB;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
/**
 * @author chakkar
 *
 */
public class CreateBankGuaranteeCoverDaoImpl extends HibernateDaoSupport implements
		CreateBankGuaranteeCoverDao {

	private final static String propName = "System.properties";
	
	@Transactional(propagation = Propagation.REQUIRED)
	public void createBGCover(BankGuaranteeCoverDto bankGuaranteeCoverDto,
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
		final String BG_FORM_NUMBER = bankGuaranteeCoverDto.getBgFormNumber();
		final String BG_TYPE = bankGuaranteeCoverDto.getBgType();
		final BigDecimal BG_AMOUNT =  bankGuaranteeCoverDto.getBgAmount();
		final String BG_CURRENCY_CODE = bankGuaranteeCoverDto.getCurrency();
		final Timestamp BG_FROM_DT = converrtStringToTimestamp(bankGuaranteeCoverDto.getBgFromDate());
		final Timestamp BG_TO_DT = converrtStringToTimestamp(bankGuaranteeCoverDto.getBgToDate());
		final Timestamp BG_EFFECTIVE_DT = converrtStringToTimestamp(bankGuaranteeCoverDto.getBgEffectiveDate());
		final Timestamp BG_LODGMENT_END_DT = converrtStringToTimestamp(bankGuaranteeCoverDto.getBgLodgementEndDate());
		final String BG_LODGMENT_PLACE = bankGuaranteeCoverDto.getBgLodgementPalce();
		final String BG_ISSUE_BANK_IFSC =  bankGuaranteeCoverDto.getBgIssuingBankCode();
		final String BG_ISSUE_BANK_NAME_DETAILS = bankGuaranteeCoverDto.getIssunigBankNameAndAddress();
		final String BG_APPLICENT_NAME_DETAILS = bankGuaranteeCoverDto.getBgApplicentNameAndDetails();
		final String BG_BENEFICIARY_NAME_DETAILS = bankGuaranteeCoverDto.getBeneficiaryNameAndDetails();
		final String BG_BENEFICIARY_CODE =	bankGuaranteeCoverDto.getBeneficiaryBankCode();
		final String BG_BENEFICIARY_BANK_DETAILS = bankGuaranteeCoverDto.getBeneficiaryBankNameAndAddress();
		final String SENDER_TO_RECEIVER_INFO = bankGuaranteeCoverDto.getSenderToReciverInformation();
		final String BG_PURPOSE = bankGuaranteeCoverDto.getBgPurpose();
		final String BG_DESCRIPTION_CONTRACT = bankGuaranteeCoverDto.getDescriptionOfUnderlinedContract();
		final String BG_STAMP_DUTY_PAID = bankGuaranteeCoverDto.getStampDutyPaid();
		final Timestamp BG_E_STAMP_DATE_TIME =  converrtStringToTimestamp(bankGuaranteeCoverDto.getStampDateAndTime());
		final String BG_E_STAMP_CERTIFICATE_NUM = bankGuaranteeCoverDto.getStampCertificateNumber();
		final BigDecimal BG_AMOUNT_PAID = bankGuaranteeCoverDto.getBgAmountPaid();
		final String BG_STATE_CODE = bankGuaranteeCoverDto.getBgStateCode();
		final String BG_ARTICLE_NUM = bankGuaranteeCoverDto.getBgArticleNumber();
		final String BG_PAYMENT_PLACE = bankGuaranteeCoverDto.getBgPaymentPlace();
		final Timestamp BG_PAYMENT_DATE = converrtStringToTimestamp(bankGuaranteeCoverDto.getBgPaymentDate());
		final String E_BG_HELD_DEMAT_FORM = bankGuaranteeCoverDto.getBgHeldDematForm();
		final String CUSTODIAN_SERVICE_PROVIDER = bankGuaranteeCoverDto.getCustodianServiceProvider(); 
		final String DEMAT_ACC_NUM = bankGuaranteeCoverDto.getDematAccNumber();
		final int BG_STATUS = status;
		final String BG_DIRECTION = bgDirection;
		

			 
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
			  
			  
			       
			  //String sql = "INSERT INTO TA_BG_MAST(MSGS_MSGREF,BG_NUMBER,BG_DIRECTION,BG_ISSUE_DT,BG_CREATE_TYPE,BG_RULES_CODE,BG_DETAILS,BG_NARRATION,BG_ADVISINGBANK,BG_STATUS) values(?,?,?,?,?,?,?,?,?,?)";

			 /* pstmt = conn.prepareStatement(sql);
			    pstmt.setString(1,MSGS_MSGREF);
				pstmt.setString(2, BG_NUMBER);
				pstmt.setString(3, BG_DIRECTION);*/
								 
			    System.out.println(mesRef);		 
			  //pstmt.executeUpdate();
		session.saveOrUpdate(canonical);
		session.saveOrUpdate(msgPolled);
		//Update message status in message transaction table 
		String msgRef = bankGuaranteeCoverDto.getMsgRef();
		if(StringUtils.isNotBlank(bankGuaranteeCoverDto.getRepair()) && StringUtils.isNotEmpty(bankGuaranteeCoverDto.getRepair())){
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

	public BankGuaranteeCoverDto getBankGuaranteeCover(String msgRef) {
		BankGuaranteeCoverDto bankGuaranteeCoverDto = new BankGuaranteeCoverDto();
		String query = "select canonical.txnReference,canonical.bgFormNumber," +//2
		" canonical.bgType, canonical.bgAmount, canonical.bgCurrency, canonical.bgFromDate, canonical.bgToDate, canonical.bgEffectiveDate, canonical.bgLodgementEndDate, canonical.bgLodgementPalce," +//8
		" canonical.issuingBankCode, canonical.bgIssuingBankAddrDetails, canonical.bgApplicentNameAndDetails, canonical.beneficiaryNameAndDetails, canonical.beneficiaryBankCode, canonical.bgBeneficiaryBankAddr, canonical.instructionsForCrdtrAgtText, canonical.bgPurpose," +//8
		" canonical.descriptionOfUnderlinedContract, canonical.eStampDutyPaid, canonical.eStampCertificateNumber, canonical.eStampDateAndTime, canonical.bgAmountPaid, canonical.bgStateCode, canonical.bgArticleNumber, canonical.bgPaymentDate," +//8
		" canonical.bgPaymentPlace, canonical.eBgHeldDematForm,canonical.msgRef, canonical.custodianServiceProvider, canonical.dematAccNumber, canonical.seqNo, canonical.msgHost from NgphCanonical canonical  where canonical.msgRef=?";
			List list = getHibernateTemplate().find(query,msgRef.trim());			
			if(list!=null && !list.isEmpty() && list.size()>0){
				Object[] obj = (Object[]) list.get(0);
				bankGuaranteeCoverDto.setBgNumber((String)obj[0]);
				bankGuaranteeCoverDto.setBgFormNumber((String)obj[1]);
				bankGuaranteeCoverDto.setBgType((String)obj[2]);
				bankGuaranteeCoverDto.setBgAmount((BigDecimal)obj[3]);
				bankGuaranteeCoverDto.setCurrency((String)obj[4]);
				bankGuaranteeCoverDto.setBgFromDate((Date)obj[5]);
				bankGuaranteeCoverDto.setBgToDate((Date)obj[6]);
				bankGuaranteeCoverDto.setBgEffectiveDate((Date)obj[7]);
				bankGuaranteeCoverDto.setBgLodgementEndDate((Date)obj[8]);
				bankGuaranteeCoverDto.setBgLodgementPalce((String)obj[9]);
				bankGuaranteeCoverDto.setIssuingBankCode((String)obj[10]);
				bankGuaranteeCoverDto.setIssunigBankNameAndAddress((String)obj[11]);
				bankGuaranteeCoverDto.setBgApplicentNameAndDetails((String)obj[12]);
				bankGuaranteeCoverDto.setBeneficiaryNameAndDetails((String)obj[13]);
				bankGuaranteeCoverDto.setBeneficiaryBankCode((String)obj[14]);
				bankGuaranteeCoverDto.setBeneficiaryBankNameAndAddress((String)obj[15]);
				bankGuaranteeCoverDto.setSenderToReciverInformation((String)obj[16]);
				bankGuaranteeCoverDto.setBgPurpose((String)obj[17]);
				bankGuaranteeCoverDto.setDescriptionOfUnderlinedContract((String)obj[18]);
				bankGuaranteeCoverDto.setStampDutyPaid((String)obj[19]);
				bankGuaranteeCoverDto.setStampCertificateNumber((String)obj[20]);
				bankGuaranteeCoverDto.setStampDateAndTime((Date)obj[21]);
				bankGuaranteeCoverDto.setBgAmountPaid((BigDecimal)obj[22]);
				bankGuaranteeCoverDto.setBgStateCode((String)obj[23]);
				bankGuaranteeCoverDto.setBgArticleNumber((String)obj[24]);
				bankGuaranteeCoverDto.setBgPaymentDate((Date)obj[25]);
				bankGuaranteeCoverDto.setBgPaymentPlace((String)obj[26]);
				bankGuaranteeCoverDto.setBgHeldDematForm((String)obj[27]);
				bankGuaranteeCoverDto.setMsgRef((String)obj[28]);
				bankGuaranteeCoverDto.setCustodianServiceProvider((String)obj[29]);
				bankGuaranteeCoverDto.setDematAccNumber((String)obj[30]);
				bankGuaranteeCoverDto.setSeqNo((String)obj[31]);
				bankGuaranteeCoverDto.setMsgHost((String)obj[32]);
			}
		
		return bankGuaranteeCoverDto;
	}

	public void createAmendBGCover(BankGuaranteeCoverDto bankGuaranteeCoverDto,
			String mesRef, String bgNumber, String bgDirection,
			NgphCanonical canonical, MsgPolled msgPolled) throws Exception {
		
			Properties props = new Properties();		
			PreparedStatement pstmt = null;
			 Connection conn = null;
			 Session session = null;
			try{
			 session = getHibernateTemplate().getSessionFactory()
					.openSession();
			session.beginTransaction();
			final String MSGS_MSGREF = mesRef;
			final String BG_NUMBER = bgNumber;
			final String BG_RELATED_REFERENCE = bankGuaranteeCoverDto.getBgRelatedReference();
			final String BG_FURTHER_IDENTIFICATION = bankGuaranteeCoverDto.getBgFurtherIdentification();
			final Timestamp BG_AMENDMENT_DATE = converrtStringToTimestamp(bankGuaranteeCoverDto.getBgAmendmentDate());
			final BigDecimal BG_NOOF_AMENEDMENTs = bankGuaranteeCoverDto.getBgNoofAmendments();
			final Timestamp BG_ISSUE_DATE = converrtStringToTimestamp(bankGuaranteeCoverDto.getBgIssueDate());
			final String BG_AMENDMENT_DETAILS = null;
			final String BG_ISSUE_BANK_IFSC =  bankGuaranteeCoverDto.getBgIssuingBankCode();
			final String BG_ISSUE_BANK_NAME_DETAILS = bankGuaranteeCoverDto.getIssunigBankNameAndAddress();
			final String BG_APPLICENT_NAME_DETAILS = bankGuaranteeCoverDto.getBgApplicentNameAndDetails();
			final String BG_BENEFICIARY_NAME_DETAILS = bankGuaranteeCoverDto.getBeneficiaryNameAndDetails();
			final String BG_BENEFICIARY_CODE =	bankGuaranteeCoverDto.getBeneficiaryBankCode();
			final String BG_BENEFICIARY_BANK_DETAILS = bankGuaranteeCoverDto.getBeneficiaryBankNameAndAddress();
			final String SENDER_TO_RECEIVER_INFO = bankGuaranteeCoverDto.getSenderToReciverInformation();
			final String BG_PURPOSE = bankGuaranteeCoverDto.getBgPurpose();
			final String BG_DESCRIPTION_CONTRACT = bankGuaranteeCoverDto.getDescriptionOfUnderlinedContract();
			final String BG_STAMP_DUTY_PAID = bankGuaranteeCoverDto.getStampDutyPaid();
			final Timestamp BG_E_STAMP_DATE_TIME =  converrtStringToTimestamp(bankGuaranteeCoverDto.getStampDateAndTime());
			final String BG_E_STAMP_CERTIFICATE_NUM = bankGuaranteeCoverDto.getStampCertificateNumber();
			final BigDecimal BG_AMOUNT_PAID = bankGuaranteeCoverDto.getBgAmountPaid();
			final String BG_STATE_CODE = bankGuaranteeCoverDto.getBgStateCode();
			final String BG_ARTICLE_NUM = bankGuaranteeCoverDto.getBgArticleNumber();
			final String BG_PAYMENT_PLACE = bankGuaranteeCoverDto.getBgPaymentPlace();
			final Timestamp BG_PAYMENT_DATE = converrtStringToTimestamp(bankGuaranteeCoverDto.getBgPaymentDate());
			final String E_BG_HELD_DEMAT_FORM = bankGuaranteeCoverDto.getBgHeldDematForm();
			final String CUSTODIAN_SERVICE_PROVIDER = bankGuaranteeCoverDto.getCustodianServiceProvider(); 
			final String DEMAT_ACC_NUM = bankGuaranteeCoverDto.getDematAccNumber();
			final String BG_DIRECTION = bgDirection;
			

				 
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
				  
				  
				       
				  //String sql = "INSERT INTO TA_BG_MAST(MSGS_MSGREF,BG_NUMBER,BG_DIRECTION,BG_ISSUE_DT,BG_CREATE_TYPE,BG_RULES_CODE,BG_DETAILS,BG_NARRATION,BG_ADVISINGBANK,BG_STATUS) values(?,?,?,?,?,?,?,?,?,?)";

				 /* pstmt = conn.prepareStatement(sql);
				    pstmt.setString(1,MSGS_MSGREF);
					pstmt.setString(2, BG_NUMBER);
					pstmt.setString(3, BG_DIRECTION);*/
									 
				    System.out.println(mesRef);		 
				  //pstmt.executeUpdate();
			session.saveOrUpdate(canonical);
			session.saveOrUpdate(msgPolled);
			//Update message status in message transaction table 
			String msgRef = bankGuaranteeCoverDto.getMsgRef();
			if(StringUtils.isNotBlank(bankGuaranteeCoverDto.getRepair()) && StringUtils.isNotEmpty(bankGuaranteeCoverDto.getRepair())){
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

	public BankGuaranteeCoverDto getCreateAmendBGCover(String msgRef) {
		BankGuaranteeCoverDto bankGuaranteeCoverDto = new BankGuaranteeCoverDto();
		String query = "select canonical.txnReference,canonical.relReference," +//2
		" canonical.lcPrevAdvRef, canonical.lcAmndmntDt, canonical.lcAmndmntNo, canonical.lcIssueDt, canonical.lcDocsReq1, canonical.instructionsForCrdtrAgtText," +//6
		" canonical.issuingBankCode, canonical.bgIssuingBankAddrDetails, canonical.bgApplicentNameAndDetails, canonical.beneficiaryNameAndDetails, canonical.beneficiaryBankCode, canonical.bgBeneficiaryBankAddr," +//6
		" canonical.eStampDutyPaid, canonical.eStampCertificateNumber, canonical.eStampDateAndTime, canonical.bgAmountPaid, canonical.bgStateCode, canonical.bgArticleNumber, canonical.bgPaymentDate," +//7
		" canonical.bgPaymentPlace, canonical.eBgHeldDematForm,canonical.msgRef, canonical.custodianServiceProvider, canonical.dematAccNumber, canonical.seqNo, canonical.msgHost from NgphCanonical canonical  where canonical.msgRef=?"  ;//5
			
			List list = getHibernateTemplate().find(query,msgRef.trim());			
			if(list!=null && !list.isEmpty() && list.size()>0){
				Object[] obj = (Object[]) list.get(0);
				bankGuaranteeCoverDto.setBgNumber((String)obj[0]);
				bankGuaranteeCoverDto.setBgRelatedReference((String)obj[1]);
				bankGuaranteeCoverDto.setBgFurtherIdentification((String)obj[2]);
				bankGuaranteeCoverDto.setBgAmendmentDate((Date)obj[3]);
				bankGuaranteeCoverDto.setBgNoofAmendments((BigDecimal)obj[4]);
				bankGuaranteeCoverDto.setBgIssueDate((Date)obj[5]);
				bankGuaranteeCoverDto.setBgAmedmentDetails((String)obj[6]);
				bankGuaranteeCoverDto.setSenderToReciverInformation((String)obj[7]);
				bankGuaranteeCoverDto.setIssuingBankCode((String)obj[8]);
				bankGuaranteeCoverDto.setIssunigBankNameAndAddress((String)obj[9]);
				bankGuaranteeCoverDto.setBgApplicentNameAndDetails((String)obj[10]);
				bankGuaranteeCoverDto.setBeneficiaryNameAndDetails((String)obj[11]);
				bankGuaranteeCoverDto.setBeneficiaryBankCode((String)obj[12]);
				bankGuaranteeCoverDto.setBeneficiaryBankNameAndAddress((String)obj[13]);
				bankGuaranteeCoverDto.setStampDutyPaid((String)obj[14]);
				bankGuaranteeCoverDto.setStampCertificateNumber((String)obj[15]);
				bankGuaranteeCoverDto.setStampDateAndTime((Date)obj[16]);
				bankGuaranteeCoverDto.setBgAmountPaid((BigDecimal)obj[17]);
				bankGuaranteeCoverDto.setBgStateCode((String)obj[18]);
				bankGuaranteeCoverDto.setBgArticleNumber((String)obj[19]);
				bankGuaranteeCoverDto.setBgPaymentDate((Date)obj[20]);
				bankGuaranteeCoverDto.setBgPaymentPlace((String)obj[21]);
				bankGuaranteeCoverDto.setBgHeldDematForm((String)obj[22]);
				bankGuaranteeCoverDto.setMsgRef((String)obj[23]);
				bankGuaranteeCoverDto.setCustodianServiceProvider((String)obj[24]);
				bankGuaranteeCoverDto.setDematAccNumber((String)obj[25]);
				bankGuaranteeCoverDto.setSeqNo((String)obj[26]);
				bankGuaranteeCoverDto.setMsgHost((String)obj[27]);
			}
		
		return bankGuaranteeCoverDto;
	}
		
	
}
