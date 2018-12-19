package com.dicka.authcloudoauth2resource.controller;

import com.dicka.authcloudoauth2resource.model.CustomPrincipal;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ResourceController {

    @GetMapping(value = "/admin")
    @PreAuthorize("hasAuthority('role_admin')")
    public String getAdmin(){
        CustomPrincipal principal = (CustomPrincipal) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();

        return principal.getUsername() + " "+principal.getEmail();
    }

    @PreAuthorize("hasAnyAuthority('role_admin', 'role_user')")
    @GetMapping(value = "/users")
    public String getUserAdmin(){
        CustomPrincipal principal = (CustomPrincipal) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        return principal.getUsername() + " "+principal.getEmail();
    }

    @GetMapping(value = "/common")
    public String getCommon(){
        return "API CALL SUCCESFULLY FROM SPRING CLOUD.";
    }
}
