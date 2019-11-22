/**
 * 
 */
package com.logica.ngph.Entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author chakkar
 *
 */
@Entity
@Table(name="PASSADMINACPOLICY")
public class SecurityPolicy {
	
	private static final long serialVersionUID = -1521727134634279445L;
	
	private int minChars;
	private int maxChars;
	private int noofDigits;
	private int noofSpecialChars;
	private int noofUpperChars;
	private int noofLowerChars;
	private int passwordExpDays;
	private int maxNoofPassChangesDay;
	private int warnPassExpDays;
	private Timestamp lastPolicyModifiedDateTime;
	
	
	@Id
	@Column(name="MINCHARS")
	public int getMinChars() {
		return minChars;
	}
	
	public void setMinChars(int minChars) {
		this.minChars = minChars;
	}
	
	@Column(name="MAXCHARS")
	public int getMaxChars() {
		return maxChars;
	}
	
	public void setMaxChars(int maxChars) {
		this.maxChars = maxChars;
	}
	
	@Column(name="NOOFDIGITS")
	public int getNoofDigits() {
		return noofDigits;
	}
	
	public void setNoofDigits(int noofDigits) {
		this.noofDigits = noofDigits;
	}
	
	@Column(name="NOOFSPLCHARS")
	public int getNoofSpecialChars() {
		return noofSpecialChars;
	}
	
	public void setNoofSpecialChars(int noofSpecialChars) {
		this.noofSpecialChars = noofSpecialChars;
	}
	
	@Column(name="NOOFUPPERCASE")
	public int getNoofUpperChars() {
		return noofUpperChars;
	}
	
	public void setNoofUpperChars(int noofUpperChars) {
		this.noofUpperChars = noofUpperChars;
	}
	
	@Column(name="NOOFLOWERCASE")
	public int getNoofLowerChars() {
		return noofLowerChars;
	}
	
	public void setNoofLowerChars(int noofLowerChars) {
		this.noofLowerChars = noofLowerChars;
	}
	
	@Column(name="EXPIRYDAYS")
	public int getPasswordExpDays() {
		return passwordExpDays;
	}
	
	public void setPasswordExpDays(int passwordExpDays) {
		this.passwordExpDays = passwordExpDays;
	}
	
	@Column(name="MAX_PASSWORD_CHANGES")
	public int getMaxNoofPassChangesDay() {
		return maxNoofPassChangesDay;
	}
	
	public void setMaxNoofPassChangesDay(int maxNoofPassChangesDay) {
		this.maxNoofPassChangesDay = maxNoofPassChangesDay;
	}
	
	@Column(name="WARNEXPIRYDAYS")
	public int getWarnPassExpDays() {
		return warnPassExpDays;
	}
	
	public void setWarnPassExpDays(int warnPassExpDays) {
		this.warnPassExpDays = warnPassExpDays;
	}
	
	@Column(name="LAST_PASS_POLICY_CHANGES")
	public Timestamp getLastPolicyModifiedDateTime() {
		return lastPolicyModifiedDateTime;
	}
	
	public void setLastPolicyModifiedDateTime(Timestamp lastPolicyModifiedDateTime) {
		this.lastPolicyModifiedDateTime = lastPolicyModifiedDateTime;
	}
	
	

}
