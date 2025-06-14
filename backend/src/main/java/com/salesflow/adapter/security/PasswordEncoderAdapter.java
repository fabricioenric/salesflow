package com.salesflow.adapter.security;

import com.salesflow.domain.port.SenhaEncoderPort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PasswordEncoderAdapter implements SenhaEncoderPort {

    private final BCryptPasswordEncoder delegate = new BCryptPasswordEncoder();

    @Override public String encode(String raw) { return delegate.encode(raw); }

    @Override public boolean matches(String raw, String encoded) {
        return delegate.matches(raw, encoded);
    }
}