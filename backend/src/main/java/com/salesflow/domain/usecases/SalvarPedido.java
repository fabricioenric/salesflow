package com.salesflow.domain.usecases;

import com.salesflow.adapter.dto.Item;
import com.salesflow.domain.model.*;
import com.salesflow.domain.port.PedidoRepository;
import com.salesflow.domain.port.ProdutoRepository;
import com.salesflow.domain.port.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

public class SalvarPedido {
    private final UserRepository userRepository;
    private final ProdutoRepository produtoRepository;
    private final PedidoRepository pedidoRepository;

    public SalvarPedido(UserRepository userRepository, ProdutoRepository produtoRepository, PedidoRepository pedidoRepository) {
        this.userRepository = userRepository;
        this.produtoRepository = produtoRepository;
        this.pedidoRepository = pedidoRepository;
    }

    public Pedido execute(String usuario, List<Item> itens) {

        User cliente = userRepository.findByUsuario(usuario);

        List<PedidoItem> pedidoItens = itens.stream()
                .map(r -> {
                    Produto p = produtoRepository.findById(r.getProdutoId());
                    p.diminuirEstoque(r.getQuantidade());
                    produtoRepository.salvar(p);

                    return new PedidoItem(p, r.getQuantidade(), p.getPreco());
                })
                .collect(Collectors.toList());

        Pedido pedido = new Pedido(null, cliente, pedidoItens);

        pedidoRepository.salvar(pedido);

        return pedido;
    }
}