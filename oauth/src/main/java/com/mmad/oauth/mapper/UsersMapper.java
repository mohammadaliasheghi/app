package com.mmad.oauth.mapper;

import com.mmad.oauth.entity.Users;
import com.mmad.oauth.model.UsersModel;
import com.mmad.oauth.config.BaseMapperConfig;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper(nullValueMappingStrategy = NullValueMappingStrategy.RETURN_NULL
        , componentModel = "spring"
)
public interface UsersMapper extends BaseMapperConfig<Users, UsersModel> {

    static UsersMapper get() {
        return Mappers.getMapper(UsersMapper.class);
    }

    UsersModel entityToModel(Users entity);

    Users modelToEntity(UsersModel model);
}
