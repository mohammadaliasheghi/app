package com.mmad.oauth.mapper;

import com.mmad.oauth.entity.UserInfo;
import com.mmad.oauth.model.UserInfoModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper(nullValueMappingStrategy = NullValueMappingStrategy.RETURN_NULL
        , componentModel = "spring"
)
public interface UserInfoMapper {

    static UserInfoMapper get() {
        return Mappers.getMapper(UserInfoMapper.class);
    }

    @Mapping(target = "users", ignore = true)
    UserInfoModel entityToModel(UserInfo entity);

    UserInfo modelToEntity(UserInfoModel model);
}
