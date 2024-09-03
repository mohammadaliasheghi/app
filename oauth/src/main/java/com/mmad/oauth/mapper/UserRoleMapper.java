package com.mmad.oauth.mapper;

import com.mmad.oauth.entity.UserRoleEntity;
import com.mmad.oauth.model.UserRoleModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueMappingStrategy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(nullValueMappingStrategy = NullValueMappingStrategy.RETURN_NULL
        , componentModel = "spring"
)
public interface UserRoleMapper {

    static UserRoleMapper get() {
        return Mappers.getMapper(UserRoleMapper.class);
    }

    @Mapping(target = "users", ignore = true)
    @Mapping(target = "roles", ignore = true)
    UserRoleModel entityToModel(UserRoleEntity entity);

    UserRoleEntity modelToEntity(UserRoleModel model);

    List<UserRoleModel> entitiesToModels(List<UserRoleEntity> entities);
}
