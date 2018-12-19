package com.dicka.authcloudoauth2server.entity;

import java.io.Serializable;

import javax.persistence.Table;
import javax.persistence.Entity;

@Entity
public class Permission extends BaseEntityId implements Serializable{

	private String name;
	
	public Permission(){
		
	}
	
	public String getName(){
		return name;
	}
	
	public void setName(String name){
		this.name = name;
	}
}
