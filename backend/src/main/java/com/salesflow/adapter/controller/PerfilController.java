package com.salesflow.adapter.controller;

import com.salesflow.adapter.dto.UsuarioDTO;
import com.salesflow.adapter.mapper.RestMapper;
import com.salesflow.domain.usecases.ConsultarMeuUsuario;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/flow/meu-usuario")
public class PerfilController {

    private final ConsultarMeuUsuario perfilUC;

    public PerfilController(ConsultarMeuUsuario p){ perfilUC=p; }

    @GetMapping
    public UsuarioDTO meuPerfil(@AuthenticationPrincipal UserDetails ud){
        return RestMapper.toDTO(perfilUC.execute(ud.getUsername()));
    }
}