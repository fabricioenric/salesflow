package com.salesflow.adapter.repository.jpa;

import com.salesflow.adapter.repository.entity.ProdutoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaProdutoRepository extends JpaRepository<ProdutoEntity, Long> {

}
