package com.bbs.entity;
/**
 * 帖子回復實體類
 * @author lindy
 * @创建时间 2020年3月20日下午3:24:10
 */

import java.util.Date;

public class InvitationAns {
	private String ansId;// 回復id
	private String ansMessage;// 回復內容
	private String invitationId;// 
	private String userId;
	private Date ansDate;
	public String getAnsId() {
		return ansId;
	}
	public void setAnsId(String ansId) {
		this.ansId = ansId;
	}
	public String getAnsMessage() {
		return ansMessage;
	}
	public void setAnsMessage(String ansMessage) {
		this.ansMessage = ansMessage;
	}
	public String getInvitationId() {
		return invitationId;
	}
	public void setInvitationId(String invitationId) {
		this.invitationId = invitationId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Date getAnsDate() {
		return ansDate;
	}
	public void setAnsDate(Date ansDate) {
		this.ansDate = ansDate;
	}
	public InvitationAns(String ansId, String ansMessage, String invitationId, String userId, Date ansDate) {
		super();
		this.ansId = ansId;
		this.ansMessage = ansMessage;
		this.invitationId = invitationId;
		this.userId = userId;
		this.ansDate = ansDate;
	}
	public InvitationAns(String ansId, String ansMessage, String invitationId, String userId) {
		super();
		this.ansId = ansId;
		this.ansMessage = ansMessage;
		this.invitationId = invitationId;
		this.userId = userId;
	}
}
