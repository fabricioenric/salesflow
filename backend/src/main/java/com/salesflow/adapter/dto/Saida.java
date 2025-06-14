package com.salesflow.adapter.dto;

public final class Saida {
    private final String tokenAcesso;
    private final String tokenRefresh;
    private final String papel;

    public Saida(String tokenAcesso, String tokenRefresh, String papel) {
        this.tokenAcesso = tokenAcesso;
        this.tokenRefresh = tokenRefresh;
        this.papel = papel;
    }

    public String getTokenAcesso()  {
        return tokenAcesso;
    }

    public String getTokenRefresh() {
        return tokenRefresh;
    }

    public String getPapel()         {
        return papel;
    }
}