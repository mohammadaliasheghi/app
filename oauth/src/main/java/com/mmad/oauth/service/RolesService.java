package com.mmad.oauth.service;

import com.mmad.oauth.config.ResourceBundle;
import com.mmad.oauth.config.Constant;
import com.mmad.oauth.entity.Roles;
import com.mmad.oauth.exception.ResourceNotFoundException;
import com.mmad.oauth.mapper.RolesMapper;
import com.mmad.oauth.model.RolesModel;
import com.mmad.oauth.repository.RolesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

@Service
@RequiredArgsConstructor
public class RolesService implements Serializable {

    private final RolesRepository repository;

    @Transactional(rollbackFor = Exception.class)
    public RolesModel createRoles(RolesModel model) throws Exception {
        checkAndUpdateRoleName(model, Constant.CREATE);
        Roles entity = RolesMapper.get().modelToEntity(model);
        repository.save(entity);
        return RolesMapper.get().entityToModel(entity);
    }

    private void checkAndUpdateRoleName(RolesModel model, String type) throws Exception {
        if (type.equals(Constant.CREATE)) {
            if (model != null && model.getName() != null && !model.getName().isEmpty())
                if (repository.existsByName(model.getName()))
                    throw new Exception("DuplicatedRoleName");
                else
                    model.setName(updateRoleName(model.getName()));
        } else if (type.equals(Constant.UPDATE)) {
            if (model != null && model.getName() != null && !model.getName().isEmpty())
                if (repository.existsByNameAndIdNot(model.getName(), model.getId()))
                    throw new Exception("DuplicatedRoleName");
                else
                    model.setName(updateRoleName(model.getName()));
        } else
            throw new Exception("RecordNotFound");
    }

    private String updateRoleName(String name) {
        if (name.startsWith(Constant.DEFAULT_SECURITY_ROLE_CONSTANT))
            return name;
        else
            return Constant.DEFAULT_SECURITY_ROLE_CONSTANT + name;
    }

    @Transactional(rollbackFor = Exception.class)
    public RolesModel updateRoles(RolesModel model) throws Exception {
        checkAndUpdateRoleName(model, Constant.UPDATE);
        Roles entity = RolesMapper.get().modelToEntity(model);
        repository.save(entity);
        return RolesMapper.get().entityToModel(entity);
    }

    @Transactional(readOnly = true)
    public RolesModel getRoles(Long id) {
        return RolesMapper.get().entityToModel(repository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException(Roles.class.getSimpleName(), "id", id)));
    }

    @Transactional(rollbackFor = Exception.class)
    public String deleteRoles(Long id) throws Exception {
        //TODO add validation for delete
        if (id.compareTo(Constant.ONE_LONG) == 0 || id.compareTo(Constant.TWO_LONG) == 0)
            throw new Exception(ResourceBundle.getMessageByKey("CannotBeDeletedAdminRole"));
        repository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException(Roles.class.getSimpleName(), "id", id));
        repository.deleteById(id);
        return Constant.SUCCESS;
    }

    @Transactional(readOnly = true)
    public String getUserRoleNameByUserIdWithoutRole(Long id) {
        try {
            String name = repository.getUserRoleNameByUserId(id);
            if (name == null || name.isEmpty())
                throw new Exception("UserRoleNotFound");
            name = name.substring(Constant.DEFAULT_SECURITY_ROLE_CONSTANT.length());
            return name;
        } catch (Exception e) {
            e.fillInStackTrace();
            return HttpStatus.NOT_FOUND.name();
        }
    }
}
