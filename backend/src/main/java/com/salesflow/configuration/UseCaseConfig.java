package com.salesflow.configuration;

import com.salesflow.domain.usecases.CriarUsuario;
import com.salesflow.domain.usecases.RenovarToken;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfig {

    @Bean
    RenovarToken renovarTokenUC(JwtProviderPort jwt) {
        return new RenovarTokenUC(jwt);
    }

    @Bean
    CriarUsuario criarUsuarioUC(UserRepositoryPort r, PasswordEncoderPort e) {
        return new CriarUsuarioUC(r,e);
    }
}