package com.salesflow.adapter.controller;

import com.salesflow.adapter.dto.CriarPedidoDTO;
import com.salesflow.adapter.dto.Item;
import com.salesflow.adapter.dto.PedidoDTO;
import com.salesflow.adapter.mapper.RestMapper;
import com.salesflow.domain.usecases.CancelarPedido;
import com.salesflow.domain.usecases.ListarMeusPedidos;
import com.salesflow.domain.usecases.SalvarPedido;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/flow/cliente/pedidos")
public class PedidoClienteController {
    private final SalvarPedido criar;
    private final CancelarPedido cancelar;
    private final ListarMeusPedidos listar;

    public PedidoClienteController(SalvarPedido c, CancelarPedido k, ListarMeusPedidos l){
        criar=c; cancelar=k; listar=l;
    }

    @PostMapping
    public PedidoDTO novo(@RequestBody CriarPedidoDTO body,
                          @AuthenticationPrincipal UserDetails ud){
        var itens = body.getItens().stream()
                .map(i -> new Item(i.getProdutoId(), i.getQuantidade()))
                .toList();
        return RestMapper.toDTO(criar.execute(ud.getUsername(), itens));
    }

    @GetMapping
    public List<PedidoDTO> meusPedidos(@AuthenticationPrincipal org.springframework.security.core.userdetails.UserDetails ud){
        return listar.execute(ud.getUsername()).stream().map(RestMapper::toDTO).toList();
    }

    @DeleteMapping("/{id}")
    public void cancelar(@PathVariable Long id,
                         @AuthenticationPrincipal org.springframework.security.core.userdetails.UserDetails ud){
        cancelar.execute(id, ud.getUsername());
    }
}