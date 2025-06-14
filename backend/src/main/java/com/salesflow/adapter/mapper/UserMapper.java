package com.salesflow.adapter.mapper;

import com.salesflow.adapter.repository.entity.UserEntity;
import com.salesflow.domain.model.User;

public final class UserMapper {

    private UserMapper() {}

    public static User toDomain(UserEntity e) {
        if (e == null)
            return null;

        return new User(e.getId(), e.getUsuario(), e.getSenhaHash(), e.getPapel(), e.isAtivo());
    }

    public static UserEntity toEntity(User d) {
        if (d == null)
            return null;

        return new UserEntity(d.getId(), d.getUsuario(), d.getSenhaHash(), d.getPapel(), d.isAtivo());
    }
}