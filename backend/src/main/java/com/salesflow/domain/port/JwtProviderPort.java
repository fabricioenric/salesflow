package com.salesflow.domain.port;

/**
 * Porta de saída para geração e renovação de JWT.
 */
public interface JwtProviderPort {

    /** Gera access-token + refresh-token na autenticação inicial. */
    Tokens gerar(String username, String role);

    /**
     * Renova o access-token usando um refresh-token ainda válido.
     * Lança IllegalArgumentException (ou subclasse) se o refresh estiver expirado ou inválido.
     */
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