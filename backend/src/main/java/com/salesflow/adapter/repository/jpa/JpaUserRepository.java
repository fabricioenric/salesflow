package com.salesflow.adapter.repository.jpa;

import com.salesflow.adapter.repository.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaUserRepository extends JpaRepository<UserEntity, Long> {

    UserEntity findByUsuario(String usuario);
}
