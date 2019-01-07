package com.dicka.onlinebankingexample.entity.secure;

import org.springframework.security.core.GrantedAuthority;

public class Authority implements GrantedAuthority {

    private final String authority;

    public Authority(String authority){
        this.authority = authority;
    }

    @Override
    public String getAuthority() {
        return null;
    }
}
