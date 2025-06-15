package com.salesflow.adapter.controller;

import com.salesflow.adapter.dto.PedidoDTO;
import com.salesflow.adapter.mapper.RestMapper;
import com.salesflow.domain.usecases.AprovarOuRejeitarPedido;
import com.salesflow.domain.usecases.ConcluirPedido;
import com.salesflow.domain.usecases.RetornarPedidosPendentes;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/flow/vendedor/pedidos")
public class PedidoVendedorController {
    private final RetornarPedidosPendentes pendentes;
    private final AprovarOuRejeitarPedido flow;
    private final ConcluirPedido concluir;

    public PedidoVendedorController(RetornarPedidosPendentes p,
                                  AprovarOuRejeitarPedido f,
                                  ConcluirPedido c){
        pendentes=p; flow=f; concluir=c;
    }

    @GetMapping("/pendentes")
    public List<PedidoDTO> pendentes(){
        return pendentes.execute().stream().map(RestMapper::toDTO).toList();
    }

    @PostMapping("/{id}/aprovar")
    public PedidoDTO aprovar(@PathVariable Long id){
        return RestMapper.toDTO(flow.aprovar(id));
    }

    @PostMapping("/{id}/rejeitar")
    public PedidoDTO rejeitar(@PathVariable Long id){
        return RestMapper.toDTO(flow.rejeitar(id));
    }

    @PostMapping("/{id}/concluir")
    public PedidoDTO concluir(@PathVariable Long id){
        return RestMapper.toDTO(concluir.execute(id));
    }
}