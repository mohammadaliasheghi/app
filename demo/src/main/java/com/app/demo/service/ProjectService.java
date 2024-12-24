package com.app.demo.service;

import com.app.demo.entity.ProjectEntity;
import com.app.demo.mapper.ProjectMapper;
import com.app.demo.model.ProjectModel;
import com.app.demo.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectRepository repository;

    @Transactional(rollbackFor = Exception.class)
    public ProjectModel createProject(ProjectModel model) {
        ProjectEntity entity = ProjectMapper.get().modelToEntity(model);
        repository.save(entity);
        return ProjectMapper.get().entityToModel(entity);
    }
}
