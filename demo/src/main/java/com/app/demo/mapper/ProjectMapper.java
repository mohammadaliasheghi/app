package com.app.demo.mapper;

import com.app.demo.entity.ProjectEntity;
import com.app.demo.model.ProjectModel;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper(nullValueMappingStrategy = NullValueMappingStrategy.RETURN_NULL
        , componentModel = "spring"
)
public interface ProjectMapper {

    static ProjectMapper get() {
        return Mappers.getMapper(ProjectMapper.class);
    }

    ProjectModel entityToModel(ProjectEntity entity);

    ProjectEntity modelToEntity(ProjectModel model);
}
