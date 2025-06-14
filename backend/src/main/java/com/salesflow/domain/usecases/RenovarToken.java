package com.salesflow.domain.usecases;

import com.salesflow.domain.port.JwtProviderPort;

public class RenovarToken {
    private final JwtProviderPort jwt;

    public RenovarToken(JwtProviderPort jwt) {
        this.jwt = jwt;
    }

    public JwtProviderPort.Tokens execute(String tokenRefresh) {
        return jwt.refresh(tokenRefresh);
    }
}