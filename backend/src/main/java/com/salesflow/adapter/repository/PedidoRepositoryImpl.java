package com.salesflow.adapter.repository;

import com.salesflow.adapter.mapper.PedidoMapper;
import com.salesflow.adapter.repository.jpa.JpaPedidoRepository;
import com.salesflow.domain.model.Pedido;
import com.salesflow.domain.model.PedidoStatus;
import com.salesflow.domain.port.PedidoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PedidoRepositoryImpl implements PedidoRepository {

    private final JpaPedidoRepository jpaRepository;

    public PedidoRepositoryImpl(JpaPedidoRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public void salvar(Pedido pedido) {
        jpaRepository.save(PedidoMapper.toEntity(pedido));
    }

    @Override
    public Pedido findById(Long id) {
        return PedidoMapper.toDomain(jpaRepository.findById(id).orElse(null));
    }

    @Override
    public List<Pedido> findByStatus(PedidoStatus status) {
        return jpaRepository.findAllByStatus(status).stream().map(PedidoMapper::toDomain).toList();
    }

    @Override
    public List<Pedido> findByCliente(String usuario) {
        return jpaRepository.findAllByCliente(usuario).stream().map(PedidoMapper::toDomain).toList();
    }

    @Override
    public List<Pedido> findAll() {
        return jpaRepository.findAll().stream().map(PedidoMapper::toDomain).toList();
    }
}