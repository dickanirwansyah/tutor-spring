package com.dicka.authcloudoauth2server.entity;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
public class Role extends BaseEntityId{

	private String name;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "permission_role", joinColumns = {
			@JoinColumn(name = "role_id", referencedColumnName = "id")
	}, inverseJoinColumns = {
			@JoinColumn(name = "permission_id", referencedColumnName = "id")
	})
	private Set<Permission> permissions;
	
	public Role(){
		
	}
	
	public String getName(){
		return name;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public Set<Permission> getPermissions(){
		return permissions;
	}
	
	public void setPermissions(Set<Permission> permissions){
		this.permissions = permissions;
	}
}
