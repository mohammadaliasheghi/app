package com.app.demo.service;

import com.app.demo.entity.UserEntity;
import com.app.demo.mapper.UserMapper;
import com.app.demo.model.UserModel;
import com.app.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;

    @Transactional(rollbackFor = Exception.class)
    public UserModel createUser(UserModel model) {
        UserEntity entity = UserMapper.get().modelToEntity(model);
        repository.save(entity);
        return UserMapper.get().entityToModel(entity);
    }

    @Transactional(readOnly = true)
    public List<UserModel> getList() {
        return UserMapper
                .get()
                .entitiesToModels(repository.findAll());
    }
}
