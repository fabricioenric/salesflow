package com.salesflow.security;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.Objects;

public final class JwtAuthenticationToken extends AbstractAuthenticationToken {

    private final String token;
    private final Object principal;

    public JwtAuthenticationToken(Object principal, String token, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.token = token;
        this.principal = principal;
        super.setAuthenticated(true);
    }

    public JwtAuthenticationToken(String token) {
        super(null);
        this.token = token;
        this.principal = null;
        setAuthenticated(false);
    }

    @Override
    public Object getCredentials() {
        return token;
    }

    @Override
    public Object getPrincipal() {
        return principal;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) {
        if (isAuthenticated) {
            throw new IllegalArgumentException(
                    "Use o construtor com authorities para autenticar esta instância."
            );
        }
        super.setAuthenticated(false);
    }

    @Override
    public String toString() {
        return "JwtAuthenticationToken{" +
                "principal=" + principal +
                ", authorities=" + getAuthorities() +
                ", authenticated=" + isAuthenticated() +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(token, principal);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof JwtAuthenticationToken that)) return false;
        return Objects.equals(token, that.token) &&
                Objects.equals(principal, that.principal);
    }
}