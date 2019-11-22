/**
 * 
 */
package com.logica.ngph.dtos;

import java.io.Serializable;

/**
 * @author chakkar
 *
 */
public class CustomerDetailsDto implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String custFirstName;
	private String custLastName;
	private String custEmail;
	private int phoneNo;
	private int custLocked;
	private int custActive;
	private int custAction;
	private String custAddress;
	
	
	
	
	/**
	 * @return the custAction
	 */
	public int getCustAction() {
		return custAction;
	}
	/**
	 * @param custAction the custAction to set
	 */
	public void setCustAction(int custAction) {
		this.custAction = custAction;
	}
	/**
	 * @return the custFirstName
	 */
	public String getCustFirstName() {
		return custFirstName;
	}
	/**
	 * @param custFirstName the custFirstName to set
	 */
	public void setCustFirstName(String custFirstName) {
		this.custFirstName = custFirstName;
	}
	/**
	 * @return the custLastName
	 */
	public String getCustLastName() {
		return custLastName;
	}
	/**
	 * @param custLastName the custLastName to set
	 */
	public void setCustLastName(String custLastName) {
		this.custLastName = custLastName;
	}
	/**
	 * @return the custEmail
	 */
	public String getCustEmail() {
		return custEmail;
	}
	/**
	 * @param custEmail the custEmail to set
	 */
	public void setCustEmail(String custEmail) {
		this.custEmail = custEmail;
	}
	/**
	 * @return the phoneNo
	 */
	public int getPhoneNo() {
		return phoneNo;
	}
	/**
	 * @param phoneNo the phoneNo to set
	 */
	public void setPhoneNo(int phoneNo) {
		this.phoneNo = phoneNo;
	}
	/**
	 * @return the custLocked
	 */
	public int getCustLocked() {
		return custLocked;
	}
	/**
	 * @param custLocked the custLocked to set
	 */
	public void setCustLocked(int custLocked) {
		this.custLocked = custLocked;
	}
	/**
	 * @return the custActive
	 */
	public int getCustActive() {
		return custActive;
	}
	/**
	 * @param custActive the custActive to set
	 */
	public void setCustActive(int custActive) {
		this.custActive = custActive;
	}
	/**
	 * @return the custAddress
	 */
	public String getCustAddress() {
		return custAddress;
	}
	/**
	 * @param custAddress the custAddress to set
	 */
	public void setCustAddress(String custAddress) {
		this.custAddress = custAddress;
	} 
	
	

}
