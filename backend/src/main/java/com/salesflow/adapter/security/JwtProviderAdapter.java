package com.salesflow.adapter.security;

import com.salesflow.domain.port.JwtProviderPort;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.time.Duration;
import java.util.Date;

@Component
public class JwtProviderAdapter implements JwtProviderPort {

    private final Key key;
    private final Duration accessTtl;
    private final Duration refreshTtl;

    public JwtProviderAdapter(
            @Value("${security.jwt.secret}") String secret,
            @Value("${security.jwt.access-minutes:15}") long accessMinutes,
            @Value("${security.jwt.refresh-days:7}") long refreshDays) {

        this.key        = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));
        this.accessTtl  = Duration.ofMinutes(accessMinutes);
        this.refreshTtl = Duration.ofDays(refreshDays);
    }

    @Override
    public Tokens gerar(String username, String role) {
        String access  = buildToken(username, role, accessTtl);
        String refresh = buildToken(username, role, refreshTtl);
        return new Tokens(access, refresh);
    }

    @Override
    public Tokens refresh(String refreshToken) {
        Claims c = parse(refreshToken);                     // lança se inválido
        String username = c.getSubject();
        String role     = c.get("role", String.class);
        return gerar(username, role);
    }

    private String buildToken(String username, String role, Duration ttl) {
        Date now = new Date();
        Date exp = new Date(now.getTime() + ttl.toMillis());

        return Jwts.builder()
                .setSubject(username)
                .claim("role", role)
                .setIssuedAt(now)
                .setExpiration(exp)
                .signWith(key)
                .compact();
    }

    public Claims parse(String token) {
        return Jwts.parser()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}