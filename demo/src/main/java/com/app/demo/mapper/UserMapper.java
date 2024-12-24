package com.app.demo.mapper;

import com.app.demo.entity.UserEntity;
import com.app.demo.model.UserModel;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueMappingStrategy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(nullValueMappingStrategy = NullValueMappingStrategy.RETURN_NULL
        , componentModel = "spring"
)
public interface UserMapper {

    static UserMapper get() {
        return Mappers.getMapper(UserMapper.class);
    }

    UserModel entityToModel(UserEntity entity);

    UserEntity modelToEntity(UserModel model);

    List<UserModel> entitiesToModels(List<UserEntity> entity);
}
