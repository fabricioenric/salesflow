package com.salesflow.adapter.controller;

import com.salesflow.adapter.dto.LoginRequest;
import com.salesflow.adapter.dto.RefreshRequest;
import com.salesflow.adapter.dto.TokenResponse;
import com.salesflow.domain.usecases.AutenticarUsuario;
import com.salesflow.domain.usecases.RenovarToken;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/flow/auth")
public class AuthController {

    private final AutenticarUsuario authUC;
    private final RenovarToken refreshUC;

    public AuthController(AutenticarUsuario a, RenovarToken r){
        this.authUC = a; this.refreshUC = r;
    }

    @PostMapping("/login")
    public TokenResponse login(@Valid @RequestBody LoginRequest body){
        var out = authUC.execute(body.getUsuario(), body.getSenha());
        return new TokenResponse(out.getTokenAcesso(), out.getTokenRefresh(), out.getPapel());
    }

    @PostMapping("/refresh")
    public TokenResponse refresh(@RequestBody RefreshRequest body){
        var t = refreshUC.execute(body.getRefreshToken());
        return new TokenResponse(t.getAccesso(), t.getRefresh(), null);
    }
}