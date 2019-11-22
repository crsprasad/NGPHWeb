package com.logica.ngph.Entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;


@Entity
@Table(name="TA_SEC_USERS")
public class SecUsers implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8266691896380176897L;
/*	APPL, 
	USR, 
	USRSNAME, 
	USRLNAME,
	USRBRANCHID,
	USRDEPTID,
	USRTYPE, 
	EMPNO, 
	USRLOCKED,
	ACTIVE,
	EXPIRYDATE, 
	EMAILID, 
	USRPSWDS,
	EFFDATEFORROLE, 
	USRDAILYLMT, 
	FIRSTLOGIN,
	MOBILENO*/
	private String appl;
	private String user;
	private String userFirstName;
	private String userLastName;
	private String userBranch;
	private String userDept;
	private String userType;
	private String empNo;
	private int userLocked;
	private int userActive;
	private Timestamp userExpiryDate;
	private String userEmailId;
	private String userPassword;
	private Timestamp effectiveDateForRole;
	private double userDailyLimit;
	private int firstlogin;
	private String mobileNumber;
	private Timestamp lastLogin;
	private Timestamp currentLogin;
	private Timestamp lastPassModDateTime;
	private String securityQuesion;
	private String securityAnswer;
	
	
	@Column(name="APPL")
	public String getAppl() {
		return appl;
	}
	public void setAppl(String appl) {
		this.appl = appl;
	}

	@Id
	@Column(name="USR")
	public String getUser() {
		return user;
	}
	
	public void setUser(String user) {
		this.user = user;
	}
	
	@Column(name="USRSNAME")
	public String getUserFirstName() {
		return userFirstName;
	}
	public void setUserFirstName(String userFirstName) {
		this.userFirstName = userFirstName;
	}
	
	@Column(name="USRLNAME")
	public String getUserLastName() {
		return userLastName;
	}
	public void setUserLastName(String userLastName) {
		this.userLastName = userLastName;
	}
	@Column(name="USRBRANCHID")
	public String getUserBranch() {
		return userBranch;
	}
	public void setUserBranch(String userBranch) {
		this.userBranch = userBranch;
	}
	@Column(name="USRDEPTID")
	public String getUserDept() {
		return userDept;
	}
	public void setUserDept(String userDept) {
		this.userDept = userDept;
	}
	
	@Column(name="USRTYPE")
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	
	@Column(name="EMPNO")
	public String getEmpNo() {
		return empNo;
	}
	public void setEmpNo(String empNo) {
		this.empNo = empNo;
	}
	
	@Column(name="USRLOCKED")
	public int getUserLocked() {
		return userLocked;
	}
	public void setUserLocked(int userLocked) {
		this.userLocked = userLocked;
	}
	
	@Column(name="ACTIVE")
	public int getUserActive() {
		return userActive;
	}
	public void setUserActive(int userActive) {
		this.userActive = userActive;
	}
	
	@Column(name="EXPIRYDATE")
	public Timestamp getUserExpiryDate() {
		return userExpiryDate;
	}
	public void setUserExpiryDate(Timestamp userExpiryDate) {
		this.userExpiryDate = userExpiryDate;
	}
	
	@Column(name="EMAILID")
	public String getUserEmailId() {
		return userEmailId;
	}
	public void setUserEmailId(String userEmailId) {
		this.userEmailId = userEmailId;
	}
	
	@Column(name="USRPSWDS")
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	
	@Column(name="EFFDATEFORROLE")
	public Timestamp getEffectiveDateForRole() {
		return effectiveDateForRole;
	}
	public void setEffectiveDateForRole(Timestamp effectiveDateForRole) {
		this.effectiveDateForRole = effectiveDateForRole;
	}
	
	@Column(name="USRDAILYLMT")
	public double getUserDailyLimit() {
		return userDailyLimit;
	}
	public void setUserDailyLimit(double userDailyLimit) {
		this.userDailyLimit = userDailyLimit;
	}
	
	@Column(name="FIRSTLOGIN")
	public int getFirstlogin() {
		return firstlogin;
	}
	public void setFirstlogin(int firstlogin) {
		this.firstlogin = firstlogin;
	}
	
	@Column(name="MOBILENO")
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	@Column(name="LASTLOGIN")
	public Timestamp getLastLogin() {
		return lastLogin;
	}
	public void setLastLogin(Timestamp lastLogin) {
		this.lastLogin = lastLogin;
	}
	@Column(name="CURRENTLOGIN")
	public Timestamp getCurrentLogin() {
		return currentLogin;
	}
	public void setCurrentLogin(Timestamp currentLogin) {
		this.currentLogin = currentLogin;
	}
	
	@Column(name="PASS_LAST_MOD_DATE_TIME")
	public Timestamp getLastPassModDateTime() {
		return lastPassModDateTime;
	}
	
	public void setLastPassModDateTime(Timestamp lastPassModDateTime) {
		this.lastPassModDateTime = lastPassModDateTime;
	}
	
	@Column(name="SECURITY_QUESTION")
	public String getSecurityQuesion() {
		return securityQuesion;
	}
	public void setSecurityQuesion(String securityQuesion) {
		this.securityQuesion = securityQuesion;
	}
	
	@Column(name="SEC_QUESTION_ANSWER")
	public String getSecurityAnswer() {
		return securityAnswer;
	}
	public void setSecurityAnswer(String securityAnswer) {
		this.securityAnswer = securityAnswer;
	}
	
	
	
}
