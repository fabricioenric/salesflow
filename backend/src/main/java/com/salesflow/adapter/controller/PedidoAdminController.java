package com.salesflow.adapter.controller;

import com.salesflow.adapter.dto.PedidoDTO;
import com.salesflow.adapter.mapper.RestMapper;
import com.salesflow.domain.model.PedidoStatus;
import com.salesflow.domain.usecases.ListarTodosPedidos;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/flow/admin/pedidos")
public class PedidoAdminController {
    private final ListarTodosPedidos listar;

    public PedidoAdminController(ListarTodosPedidos l){ listar=l; }

    @GetMapping
    public List<PedidoDTO> todos(@RequestParam(required=false) String status){
        var st = status==null ? null : PedidoStatus.valueOf(status);
        return listar.execute(st).stream().map(RestMapper::toDTO).toList();
    }
}