package com.mmad.oauth.service;

import com.mmad.oauth.config.ResourceBundle;
import com.mmad.oauth.entity.UserRoleEntity;
import com.mmad.oauth.mapper.UserRoleMapper;
import com.mmad.oauth.model.UserRoleModel;
import com.mmad.oauth.repository.UserRoleRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.service.spi.ServiceException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserRoleService {

    private final UserRoleRepository repository;

    @Transactional(rollbackFor = Exception.class)
    public void createUserRole(UserRoleModel model) {
        if (repository.existsByUsersIdAndRolesIdAndIdNot(
                model.getUsersId(),
                model.getRolesId(),
                0L
        )) throw new ServiceException(ResourceBundle.getMessageByKey("DuplicatedUserRole"));
        UserRoleEntity entity = UserRoleMapper.get().modelToEntity(model);
        repository.save(entity);
    }

    @Transactional(rollbackFor = Exception.class)
    public void deleteUserRole(Long id) {
        repository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public Boolean existsByUsersId(Long userId) {
        return repository.existsByUsersId(userId);
    }

    @Transactional(readOnly = true)
    public List<UserRoleModel> getListByUsersId(Long userId) {
        return UserRoleMapper.get().entitiesToModels(repository.getByUsersId(userId));
    }
}
