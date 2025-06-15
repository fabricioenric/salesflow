package com.salesflow.adapter.dto;

public class RefreshRequest {

    private String refreshToken;

    public RefreshRequest() {} // Necessário para desserialização JSON

    public RefreshRequest(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }
}