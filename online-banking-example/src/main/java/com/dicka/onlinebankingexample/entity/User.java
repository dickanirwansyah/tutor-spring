package com.dicka.onlinebankingexample.entity;

import com.dicka.onlinebankingexample.entity.secure.Authority;
import com.dicka.onlinebankingexample.entity.secure.UserRole;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "user")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User implements UserDetails{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    private String username;
    private String password;
    private String firstname;
    private String lastname;

    @Email
    @Column(unique = true, nullable = false)
    private String email;
    private String phone;
    private boolean enable=true;

    /** primary account **/
    @OneToOne
    private PrimaryAccount primaryAccount;

    /** saving accounts **/
    @OneToOne
    private SavingsAccount savingsAccount;

    /** recipient **/
    @OneToMany(mappedBy = "user",
        cascade = CascadeType.ALL,
        fetch = FetchType.LAZY)
    private List<Recipient> recipientList;

    /** appointment **/
    @OneToMany(mappedBy = "user",
        cascade = CascadeType.ALL,
        fetch = FetchType.LAZY)
    private List<Appointment> appointments;

    /** user roles **/
    @OneToMany(mappedBy = "user",
        cascade = CascadeType.ALL,
    fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<UserRole> userRoles = new HashSet<>();


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> authorities = new HashSet<>();
        userRoles.forEach(ur
                -> authorities.add(new Authority(ur.getRole().getName())));
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enable;
    }
}
