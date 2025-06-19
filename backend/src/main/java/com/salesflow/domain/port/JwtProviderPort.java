package com.salesflow.domain.port;

/**
 * Porta de saída para geração e renovação de JWT.
 */
public interface JwtProviderPort {

    Tokens gerar(String username, String role);

    Tokens refresh(String refreshToken);

    final class Tokens {
        private final String accesso;
        private final String refresh;

        public Tokens(String accesso, String refresh) {
            this.accesso = accesso;
            this.refresh = refresh;
        }
        public String getAccesso() {
            return accesso;
        }

        public String getRefresh() {
            return refresh;
        }
    }
}