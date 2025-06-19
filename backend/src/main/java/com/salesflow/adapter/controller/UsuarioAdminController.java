package com.salesflow.adapter.controller;

import com.salesflow.adapter.dto.NovoUsuarioDTO;
import com.salesflow.adapter.dto.PatchUsuarioDTO;
import com.salesflow.adapter.dto.UsuarioDTO;
import com.salesflow.adapter.mapper.RestMapper;
import com.salesflow.domain.model.Papel;
import com.salesflow.domain.usecases.AtualizarUsuario;
import com.salesflow.domain.usecases.CriarUsuario;
import com.salesflow.domain.usecases.DesativarUsuario;
import com.salesflow.domain.usecases.ListarUsuarios;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController @RequestMapping("/flow/admin/usuarios")
public class UsuarioAdminController {

    private final CriarUsuario criar;
    private final ListarUsuarios listar;
    private final AtualizarUsuario atualizar;
    private final DesativarUsuario desativar;

    public UsuarioAdminController(CriarUsuario c, ListarUsuarios l,
                                  AtualizarUsuario a, DesativarUsuario d){
        criar=c; listar=l; atualizar=a; desativar=d;
    }

    @PreAuthorize("hasRole('ADMINISTRADOR')")
    @PostMapping
    public UsuarioDTO novo(@RequestBody NovoUsuarioDTO dto){
        var u = criar.execute(dto.getUsuario(), dto.getSenha(), Papel.valueOf(dto.getPapel()));
        return RestMapper.toDTO(u);
    }

    @PreAuthorize("hasRole('ADMINISTRADOR')")
    @GetMapping
    public List<UsuarioDTO> todos(){
        return listar.execute().stream().map(RestMapper::toDTO).toList();
    }

    @PreAuthorize("hasRole('ADMINISTRADOR')")
    @PutMapping("/{id}")
    public UsuarioDTO update(@PathVariable Long id, @RequestBody PatchUsuarioDTO dto){
        var u = atualizar.execute(id, dto.getUsuario(), dto.getPapel()==null ? null : Papel.valueOf(dto.getPapel()));
        return RestMapper.toDTO(u);
    }

    @PreAuthorize("hasRole('ADMINISTRADOR')")
    @DeleteMapping("/{id}")
    public void disable(@PathVariable Long id){ desativar.execute(id); }
}