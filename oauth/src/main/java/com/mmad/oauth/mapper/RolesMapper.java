package com.mmad.oauth.mapper;

import com.mmad.oauth.entity.Roles;
import com.mmad.oauth.model.RolesModel;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper(nullValueMappingStrategy = NullValueMappingStrategy.RETURN_NULL
        , componentModel = "spring"
)
public interface RolesMapper {

    static RolesMapper get() {
        return Mappers.getMapper(RolesMapper.class);
    }

    RolesModel entityToModel(Roles entity);

    Roles modelToEntity(RolesModel model);
}
