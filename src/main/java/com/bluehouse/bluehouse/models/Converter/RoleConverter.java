package com.bluehouse.bluehouse.models.Converter;



import com.bluehouse.bluehouse.models.Role;

import jakarta.persistence.AttributeConverter;

public class RoleConverter implements AttributeConverter<Role, String>{

    @Override
    public String convertToDatabaseColumn(Role attribute) {
        return attribute.getDescricao();
    }

    @Override
    public Role convertToEntityAttribute(String dbData) {
        return Role.of(dbData);
    }
}
