package com.salesflow.adapter.repository;

import com.salesflow.adapter.mapper.UserMapper;
import com.salesflow.adapter.repository.jpa.JpaUserRepository;
import com.salesflow.domain.model.User;
import com.salesflow.domain.port.UserRepository;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private final JpaUserRepository jpaRepository;

    public UserRepositoryImpl(JpaUserRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public User findByUsuario(String usuario) {
        return UserMapper.toDomain(UserMapper.toEntity(jpaRepository.findByUsuario(usuario)));
    }

    @Override
    public void salvar(User user) {
        jpaRepository.save(UserMapper.toEntity(user));
    }
}