package com.salesflow.adapter.mapper;

import com.salesflow.adapter.dto.PedidoDTO;
import com.salesflow.adapter.dto.ProdutoDTO;
import com.salesflow.adapter.dto.UsuarioDTO;
import com.salesflow.domain.model.*;

public final class RestMapper {
    private RestMapper() {}

    public static ProdutoDTO toDTO(Produto p){
        return new ProdutoDTO(p.getId(), p.getNome(), p.getPreco(), p.getEstoque());
    }
    public static PedidoDTO toDTO(Pedido p){
        return new PedidoDTO(p.getId(), p.getStatus().name(), p.valorTotal());
    }
    public static UsuarioDTO toDTO(User u){
        return new UsuarioDTO(u.getId(), u.getUsuario(), u.getPapel().name());
    }
}