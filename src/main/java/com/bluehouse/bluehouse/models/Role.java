package com.bluehouse.bluehouse.models;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@ToString
@RequiredArgsConstructor
public enum Role {
    USER("user"), ADMIN("admin");

    @Getter
    private final String descricao;

    public static Role of(String descricao) {
        for (Role role : Role.values()) {
            if (role.descricao.equalsIgnoreCase(descricao)) {
                return role;
            }
        }
        throw new IllegalArgumentException("Papel inválido: " + descricao);
    }
}