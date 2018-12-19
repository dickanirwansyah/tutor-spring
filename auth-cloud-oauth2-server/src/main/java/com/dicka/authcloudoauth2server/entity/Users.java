package com.dicka.authcloudoauth2server.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Entity
public class Users extends BaseEntityId implements UserDetails{

	private String email;
	private String username;
	private String password;
	private boolean enabled;
	
	@Column(name = "account_locked")
	private boolean accountNonLocked;
	
	@Column(name = "account_expired")
	private boolean accountNonExpired;
	
	@Column(name = "credentials_expired")
	private boolean credentialsNonExpired;
	
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name = "users_role", joinColumns = {
			@JoinColumn(name = "user_id", referencedColumnName = "id")
	}, inverseJoinColumns = {
			@JoinColumn(name = "role_id", referencedColumnName = "id")
	})
	private Set<Role> roles;
	
	@OneToOne(mappedBy = "users", cascade = CascadeType.ALL)
	private VerifyToken verifyToken;
	
	public Users(){}
	
	public VerifyToken getVerifyToken(){
		return verifyToken;
	}
	
	public void setVerifyToken(VerifyToken verifyToken){
		this.verifyToken = verifyToken;
	}
	
	public Set<Role> getRoles(){
		return roles;
	}
	
	public void setRoles(Set<Role> roles){
		this.roles = roles;
	}
	
	public String getEmail(){
		return email;
	}
	
	public void setEmail(String email){
		this.email = email;
	}
	
	public String getUsername(){
		return username;
	}
	
	public void setUsername(String username){
		this.username = username;
	}
	
	public String getPassword(){
		return password;
	}
	
	public void setPassword(String password){
		this.password = password;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
		roles.forEach(r -> {
			authorities.add(new SimpleGrantedAuthority(r.getName()));
			r.getPermissions().forEach(p -> {
				authorities.add(new SimpleGrantedAuthority(p.getName()));
			});
		});
		return authorities;
	}

	@Override
	public boolean isAccountNonExpired() {
		return !accountNonExpired;
	}

	@Override
	public boolean isAccountNonLocked() {
		return !accountNonLocked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return !credentialsNonExpired;
	}

	@Override
	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled){
		this.enabled = enabled;
	}
}
