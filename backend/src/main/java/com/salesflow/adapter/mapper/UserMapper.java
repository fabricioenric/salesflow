package com.salesflow.adapter.mapper;

import com.salesflow.adapter.repository.entity.UserEntity;
import com.salesflow.domain.model.User;

public class UserMapper {

    public static User toDomain(UserEntity entity) {
        return new User(entity.getId(), entity.getUsuario(), entity.getSenha(), entity.getPapel());
    }

    public static UserEntity toEntity(User domain) {
        return new UserEntity(domain.getId(), domain.getUsuario(), domain.getSenha(), domain.getPapel());
    }
}