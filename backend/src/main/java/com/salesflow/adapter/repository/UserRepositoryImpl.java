package com.salesflow.adapter.repository;

import com.salesflow.adapter.mapper.UserMapper;
import com.salesflow.adapter.repository.entity.UserEntity;
import com.salesflow.adapter.repository.jpa.JpaUserRepository;
import com.salesflow.domain.model.User;
import com.salesflow.domain.port.UserRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private final JpaUserRepository jpaRepository;

    public UserRepositoryImpl(JpaUserRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public User findById(Long id) {
        return UserMapper.toDomain(jpaRepository.findById(id).orElse(null));
    }

    @Override
    public User findByUsuario(String usuario) {
        return UserMapper.toDomain(jpaRepository.findByUsuario(usuario));
    }

    @Override
    public List<User> findAll() {
        return jpaRepository.findAll().stream().map(UserMapper::toDomain).toList();
    }

    @Override
    public void salvar(User user) {
        jpaRepository.save(UserMapper.toEntity(user));
    }

    @Override
    public void ativar(Long id) {
        UserEntity entity = jpaRepository.findById(id).orElse(null);

        if(entity != null) {
            entity.setAtivo(true);
            jpaRepository.save(entity);
        }
    }

    @Override
    public void desativar(Long id) {
        UserEntity entity = jpaRepository.findById(id).orElse(null);

        if(entity != null) {
            entity.setAtivo(false);
            jpaRepository.save(entity);
        }
    }
}