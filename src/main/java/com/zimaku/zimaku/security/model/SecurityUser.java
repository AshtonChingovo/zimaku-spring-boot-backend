package com.zimaku.zimaku.security.model;

import com.zimaku.zimaku.domain.user.entity.Privilege;
import com.zimaku.zimaku.domain.user.entity.Role;
import com.zimaku.zimaku.domain.user.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SecurityUser implements UserDetails {

    private final User user;

    public SecurityUser(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        List<Privilege> privileges = new ArrayList<>();
        List<String> authorities = new ArrayList<>();

        for(Role role: user.getRoles()){
            //privileges.addAll(role.getPrivileges());
            authorities.add(role.getTitle());
        }

        for(Privilege privilege: privileges){
            authorities.add(privilege.getName());
        }

        for(String authority: authorities){
            grantedAuthorities.add(new SimpleGrantedAuthority(authority));
        }

        return grantedAuthorities;

    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmail();
    }



}
