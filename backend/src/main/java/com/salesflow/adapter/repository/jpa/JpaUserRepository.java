package com.salesflow.adapter.repository.jpa;

import com.salesflow.adapter.repository.entity.UserEntity;
import com.salesflow.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaUserRepository extends JpaRepository<UserEntity, Long> {

    User findByUsuario(String usuario);
}
