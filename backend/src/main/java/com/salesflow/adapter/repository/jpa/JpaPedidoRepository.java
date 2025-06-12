package com.salesflow.adapter.repository.jpa;

import com.salesflow.adapter.repository.entity.PedidoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaPedidoRepository extends JpaRepository<PedidoEntity, Long> {


}
