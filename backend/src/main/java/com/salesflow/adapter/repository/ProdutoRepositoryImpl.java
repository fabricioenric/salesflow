package com.salesflow.adapter.repository;

import com.salesflow.adapter.mapper.ProdutoMapper;
import com.salesflow.adapter.repository.jpa.JpaProdutoRepository;
import com.salesflow.domain.model.Produto;
import com.salesflow.domain.port.ProdutoRepository;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public class ProdutoRepositoryImpl implements ProdutoRepository {

    private final JpaProdutoRepository jpaRepository;

    public ProdutoRepositoryImpl(JpaProdutoRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Produto findById(Long id) {
        return ProdutoMapper.toDomain(jpaRepository.findById(id).orElse(null));
    }

    @Override
    public List<Produto> findAll() {
        return jpaRepository.findAll().stream().map(ProdutoMapper::toDomain).toList();
    }

    @Override
    public void salvar(Produto p) {
        jpaRepository.save(ProdutoMapper.toEntity(p));
    }

    @Override
    public void deletar(Long id) {
        jpaRepository.deleteById(id);
    }
}
