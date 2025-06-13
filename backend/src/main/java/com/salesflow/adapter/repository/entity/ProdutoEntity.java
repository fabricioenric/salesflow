package com.salesflow.adapter.repository.entity;

import jakarta.persistence.*;

import java.util.Objects;

/**
 * Entidade JPA que representa um produto em estoque.
 */
@Entity
@Table(name = "produtos")
public class ProdutoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String nome;

    @Column(nullable = false)
    private Double preco;

    @Column(nullable = false)
    private Integer estoque;

    protected ProdutoEntity() {}

    public ProdutoEntity(String nome, Double preco, Integer estoque) {
        this.nome  = nome;
        this.preco = preco;
        this.estoque = estoque;
    }

    public ProdutoEntity(Long id, String nome, Double preco, Integer estoque) {
        this.id    = id;
        this.nome  = nome;
        this.preco = preco;
        this.estoque = estoque;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public Integer getEstoque() {
        return estoque;
    }

    public void setEstoque(Integer estoque) {
        this.estoque = estoque;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof ProdutoEntity that)) return false;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getNome(), that.getNome()) && Objects.equals(getPreco(), that.getPreco()) && Objects.equals(getEstoque(), that.getEstoque());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getNome(), getPreco(), getEstoque());
    }
}