package com.salesflow.adapter.repository.jpa;

import com.salesflow.adapter.repository.entity.PedidoEntity;
import com.salesflow.domain.model.PedidoStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface JpaPedidoRepository extends JpaRepository<PedidoEntity, Long> {

    @Query("SELECT p FROM PedidoEntity p WHERE p.status = ?1 ORDER BY p.dataCriacao")
    List<PedidoEntity> findAllByStatus(PedidoStatus status);

    @Query("SELECT p FROM PedidoEntity p INNER JOIN UserEntity u ON p.cliente.id = u.id WHERE u.usuario = ?1 ORDER BY p.dataCriacao")
    List<PedidoEntity> findAllByCliente(String usuario);
}
