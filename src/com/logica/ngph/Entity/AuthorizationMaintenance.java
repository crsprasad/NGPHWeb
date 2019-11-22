package com.logica.ngph.Entity;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name ="TA_AUTHSCHEMAM")
public class AuthorizationMaintenance {
	
	private String branch_Code;
	private String host_code;
	private String channel_type;
	private String msg_type;
	private String subMsg_type;
	private String curreny_type;
	private BigDecimal from_Amount;
	private BigDecimal to_Amount;
	private String groupID;
	private int groupSequence;
	private String direction;
	
	@Column (name="BRANCH")
	public String getBranch_Code() {
		return branch_Code;
	}
	public void setBranch_Code(String branch_Code) {
		this.branch_Code = branch_Code;
	}
	@Column (name="HOSTID")
	public String getHost_code() {
		return host_code;
	}
	public void setHost_code(String host_code) {
		this.host_code = host_code;
	}
	@Column (name="CHNLTYPE")
	public String getChannel_type() {
		return channel_type;
	}
	public void setChannel_type(String channel_type) {
		this.channel_type = channel_type;
	}
	@Column (name="MSGTYPE")
	public String getMsg_type() {
		return msg_type;
	}
	public void setMsg_type(String msg_type) {
		this.msg_type = msg_type;
	}
	@Column (name="SUBMSGTYPE")
	public String getSubMsg_type() {
		return subMsg_type;
	}
	
	public void setSubMsg_type(String subMsg_type) {
		this.subMsg_type = subMsg_type;
	}
	@Column (name="CURRENCY")
	public String getCurreny_type() {
		return curreny_type;
	}
	public void setCurreny_type(String curreny_type) {
		this.curreny_type = curreny_type;
	}
	@Column (name="FROM_AMOUNT")
	public BigDecimal getFrom_Amount() {
		return from_Amount;
	}
	public void setFrom_Amount(BigDecimal from_Amount) {
		this.from_Amount = from_Amount;
	}
	@Column (name="TO_AMOUNT")
	public BigDecimal getTo_Amount() {
		return to_Amount;
	}
	public void setTo_Amount(BigDecimal to_Amount) {
		this.to_Amount = to_Amount;
	}
	@Id
	@Column (name="GROUPID")
	public String getGroupID() {
		return groupID;
	}
	
	public void setGroupID(String groupID) {
		this.groupID = groupID;
	}
	
	@Column (name="GROUPSEQUENCE")
	public int getGroupSequence() {
		return groupSequence;
	}
	public void setGroupSequence(int groupSequence) {
		this.groupSequence = groupSequence;
	}
	@Column(name="DIRECTION")
	public void setDirection(String direction) {
		this.direction = direction;
	}
	public String getDirection() {
		return direction;
	}
	

}
