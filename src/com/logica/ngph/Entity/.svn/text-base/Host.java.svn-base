package com.logica.ngph.Entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="TA_EI")
public class Host implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3009143549823566044L;
	private String hostName;
	private String hostCode;
	private String queueType;
	private String provideName;
	
	@Column(name="EI_NAME")
	public String getHostName() {
		return hostName;
	}
	public void setHostName(String hostName) {
		this.hostName = hostName;
	}
	@Id
	@Column(name="EI_CODE")
	public String getHostCode() {
		return hostCode;
	}
	public void setHostCode(String hostCode) {
		this.hostCode = hostCode;
	}
	@Column(name="EI_TYPE")
	public String getQueueType() {
		return queueType;
	}
	public void setQueueType(String queueType) {
		this.queueType = queueType;
	}
	@Column(name="EI_ESB_PROVNAME")
	public String getProvideName() {
		return provideName;
	}
	public void setProvideName(String provideName) {
		this.provideName = provideName;
	}
	
	
}
