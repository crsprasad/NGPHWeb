/**
 * 
 */
package com.logica.ngph.dtos;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author chakkar
 *
 */
public class PasswordSecurityPolicyDto implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private int minChars;
	private int maxChars;
	private int noofDigits;
	private int noofSpecialChars;
	private int noofUpperChars;
	private int noofLowerChars;
	private int passwordExpDays;
	private int maxNoofPassChangesDay;
	private Timestamp lastPassPolicyChanges;
	public int getMinChars() {
		return minChars;
	}
	public void setMinChars(int minChars) {
		this.minChars = minChars;
	}
	public int getMaxChars() {
		return maxChars;
	}
	public void setMaxChars(int maxChars) {
		this.maxChars = maxChars;
	}
	public int getNoofDigits() {
		return noofDigits;
	}
	public void setNoofDigits(int noofDigits) {
		this.noofDigits = noofDigits;
	}
	public int getNoofSpecialChars() {
		return noofSpecialChars;
	}
	public void setNoofSpecialChars(int noofSpecialChars) {
		this.noofSpecialChars = noofSpecialChars;
	}
	public int getNoofUpperChars() {
		return noofUpperChars;
	}
	public void setNoofUpperChars(int noofUpperChars) {
		this.noofUpperChars = noofUpperChars;
	}
	public int getNoofLowerChars() {
		return noofLowerChars;
	}
	public void setNoofLowerChars(int noofLowerChars) {
		this.noofLowerChars = noofLowerChars;
	}
	public int getPasswordExpDays() {
		return passwordExpDays;
	}
	public void setPasswordExpDays(int passwordExpDays) {
		this.passwordExpDays = passwordExpDays;
	}
	public int getMaxNoofPassChangesDay() {
		return maxNoofPassChangesDay;
	}
	public void setMaxNoofPassChangesDay(int maxNoofPassChangesDay) {
		this.maxNoofPassChangesDay = maxNoofPassChangesDay;
	}
	public Timestamp getLastPassPolicyChanges() {
		return lastPassPolicyChanges;
	}
	public void setLastPassPolicyChanges(Timestamp lastPassPolicyChanges) {
		this.lastPassPolicyChanges = lastPassPolicyChanges;
	}
	
	
	
	

}
