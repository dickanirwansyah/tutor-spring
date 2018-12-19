package com.dicka.authcloudoauth2server.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
public class VerifyToken extends BaseEntityId implements Serializable{

	private static final String STATUS_VERIFIED = "VERIFIED";
	private static final String STATUS_PENDING = "PENDING";

	private String token;
	private String status;
	
	@Column(name = "expired_token")
	private LocalDateTime expiredDateTime;
	
	@Column(name = "issue_token")
	private LocalDateTime issueDateTime;
	
	@Column(name = "confirm_token")
	private LocalDateTime confirmDateTime;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "users_id")
	private Users users;
	
	public VerifyToken(){
		
	}
	
	public Users getUsers(){
		return users;
	}
	
	public void setUsers(Users users){
		this.users = users;
	}
	
	public String getToken(){
		return token;
	}
	
	public void setToken(String token){
		this.token = token;
	}
	
	public String getStatus(){
		return status;
	}
	
	public void setStatus(String status){
		this.status = status;
	}
	
	public LocalDateTime getExpiredDateTime(){
		return expiredDateTime;
	}
	
	public void setExpiredDateTime(LocalDateTime expirDateTime){
		this.expiredDateTime = expirDateTime;
	}
	
	public LocalDateTime getIssueDateTime(){
		return issueDateTime;
	}
	
	public void setIssueDateTime(LocalDateTime issueDateTime){
		this.issueDateTime = issueDateTime;
	}
	
	public LocalDateTime getConfirmDateTime(){
		return confirmDateTime;
	}
	
	public void setConfirmDateTime(LocalDateTime confirmDateTime){
		this.confirmDateTime = confirmDateTime;
	}
}
