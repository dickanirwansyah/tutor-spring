package com.dicka.onlinebankingexample.entity.secure;

import com.dicka.onlinebankingexample.entity.User;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "user_role")
public class UserRole implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userRoleId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id")
    private Role role;

    public UserRole(){}

    public UserRole(User user, Role role){
        this.user = user;
        this.role = role;
    }

    public Long getUserRoleId(){
        return userRoleId;
    }

    public void setUserRoleId(Long userRoleId){
        this.userRoleId = userRoleId;
    }

    public User getUser(){
        return user;
    }

    public void setUser(User user){
        this.user = user;
    }

    public Role getRole(){
        return role;
    }

    public void setRole(Role role){
        this.role = role;
    }
}
