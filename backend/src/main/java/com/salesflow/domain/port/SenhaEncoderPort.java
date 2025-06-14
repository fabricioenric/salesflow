package com.salesflow.domain.port;

public interface SenhaEncoderPort {

    String encode(String raw);
    boolean matches(String raw, String encoded);
}
