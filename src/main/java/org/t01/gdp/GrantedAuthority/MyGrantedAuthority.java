package org.t01.gdp.GrantedAuthority;

import org.springframework.security.core.GrantedAuthority;

public class MyGrantedAuthority implements GrantedAuthority {
    private final String role;

    public MyGrantedAuthority(String role) {
        this.role = "ROLE_" + role;
    }

    @Override
    public String getAuthority() {
        return role;
    }
}
