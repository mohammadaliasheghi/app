package com.mmad.oauth.service;

import com.mmad.oauth.config.Constant;
import com.mmad.oauth.config.ResourceBundle;
import com.mmad.oauth.config.SecurityConfig;
import com.mmad.oauth.config.SecurityUtil;
import com.mmad.oauth.entity.Users;
import com.mmad.oauth.exception.ResourceNotFoundException;
import com.mmad.oauth.mapper.UsersMapper;
import com.mmad.oauth.model.UserRoleModel;
import com.mmad.oauth.model.UsersModel;
import com.mmad.oauth.repository.UsersRepository;
import com.mmad.oauth.util.MessageProducer;
import lombok.RequiredArgsConstructor;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsersService implements UserDetailsService {

    @Value("${security.password.secret-key}")
    private String SECRET_KEY;

    private final UsersRepository repository;
    private RolesService rolesService;
    private UserRoleService userRoleService;
    private MessageProducer messageProducer;

    @Autowired
    public void setMessageProducer(MessageProducer messageProducer) {
        this.messageProducer = messageProducer;
    }

    @Autowired
    public void setUserRoleService(UserRoleService userRoleService) {
        this.userRoleService = userRoleService;
    }

    @Autowired
    public void setRolesService(RolesService rolesService) {
        this.rolesService = rolesService;
    }

    @Transactional(readOnly = true)
    public Optional<UsersModel> findUsersByUsername(String userName) {
        Optional<Users> users = repository.findUsersByUsername(userName);
        return Optional.ofNullable(UsersMapper.get().entityToModel(users.orElse(null)));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Users> findUser = repository.findUsersByUsername(username);
        if (findUser.isEmpty())
            throw new UsernameNotFoundException(Constant.USER_INVALID);
        return org.springframework.security.core.userdetails.User
                .withUsername(username)
                .password(findUser.get().getPassword())
                .roles(rolesService.getUserRoleNameByUserIdWithoutRole(findUser.get().getId()))
                .accountExpired(!findUser.get().getAccountNonExpired())
                .accountLocked(!findUser.get().getAccountNonLocked())
                .credentialsExpired(!findUser.get().getCredentialsNonExpired())
                .disabled(false)
                .build();
    }

    @Transactional(rollbackFor = Exception.class)
    public UsersModel create(UsersModel usersModel) {
        encryptedPassword(usersModel);
        Users users = UsersMapper.get().modelToEntity(usersModel);
        checkAndSetDefaultBoolean(users);
        repository.save(users);
        UsersModel createdModel = UsersMapper.get().entityToModel(users);
        createDefaultRoleForUser(createdModel.getId());
        return createdModel;
    }

    private void createDefaultRoleForUser(Long id) {
        if (!userRoleService.existsByUsersId(id)) {
            UserRoleModel model = new UserRoleModel();
            model.setUsersId(id);
            model.setRolesId(Constant.TWO_LONG);
            userRoleService.createUserRole(model);
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public UsersModel updateUsername(UsersModel model) {
        if (model.getId() != null) {
            Optional<Users> entity = repository.findById(model.getId());
            if (entity.isPresent()) {
                entity.get().setUsername(model.getUsername());
                repository.save(entity.get());
                model = UsersMapper.get().entityToModel(entity.get());
            }
        } else
            throw new ServiceException(ResourceBundle.getMessageByKey("RecordNotFound"));
        return model;
    }

    @Transactional(rollbackFor = Exception.class)
    public UsersModel updatePassword(UsersModel model) {
        UsersModel updated = new UsersModel();
        if (model.getId() != null) {
            if (model.getPreviousPassword() == null || model.getPreviousPassword().isEmpty() ||
                    model.getPassword() == null || model.getPassword().isEmpty())
                throw new ServiceException(ResourceBundle.getMessageByKey("PreviousPasswordMustNotBeNull"));
            Users entity = repository.getById(model.getId());
            if (SecurityConfig.passwordEncoder().matches(model.getPreviousPassword(), entity.getPassword())) {
                encryptedPassword(model);
                entity.setPassword(model.getPassword());
                repository.save(entity);
                updated = UsersMapper.get().entityToModel(entity);
            } else
                throw new ServiceException(ResourceBundle.getMessageByKey("WrongPreviousPassword"));
        }
        return updated;
    }

    private void checkAndSetDefaultBoolean(Users entity) {
        entity.setEnabled(true);
        entity.setAccountNonExpired(true);
        entity.setAccountNonLocked(true);
        entity.setCredentialsNonExpired(true);
    }

    private void encryptedPassword(UsersModel usersModel) {
        if (usersModel != null && usersModel.getPassword() != null && !usersModel.getPassword().isEmpty())
            usersModel.setPassword(SecurityConfig.passwordEncoder().encode(usersModel.getPassword()));
    }

    //TODO add method for update password and encrypted password
    @Transactional(readOnly = true)
    public UsersModel get(Long id) {
        UsersModel model = UsersMapper.get().entityToModel(repository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException(Users.class.getSimpleName(), "id", id)));
        model.setPassword(null);
        try {
            messageProducer.sendMessage(Constant.OAUTH_TOPIC, model);
        } catch (Exception e) {
            e.fillInStackTrace();
            throw new ServiceException("kafka server is down");
        }
        return model;
    }

    @Transactional(rollbackFor = Exception.class)
    public String delete(Long id) {
        if (id.compareTo(Constant.ONE_LONG) == 0)
            throw new ServiceException(ResourceBundle.getMessageByKey("CannotBeDeletedAdminUsers"));
        String token = SecurityUtil.getTokenFromCurrentRequest();
        if (token != null && !token.isEmpty()) {
            Long currentId = SecurityUtil.getCurrentId(token, SECRET_KEY);
            if (id.compareTo(currentId) == 0)
                throw new ServiceException(ResourceBundle.getMessageByKey("CannotBeDeleteCurrentUser"));
        } else
            throw new ServiceException(Constant.INVALID_TOKEN);
        if (userRoleService.existsByUsersId(id))
            deleteUserRoleListByUserId(id);
        repository.deleteById(id);
        return Constant.SUCCESS;
    }

    private void deleteUserRoleListByUserId(Long userId) {
        List<UserRoleModel> modelList = userRoleService.getListByUsersId(userId);
        if (modelList != null && !modelList.isEmpty()) {
            List<Long> ids = modelList.stream().map(UserRoleModel::getId).toList();
            for (Long id : ids)
                userRoleService.deleteUserRole(id);
        }
    }

    @Transactional(readOnly = true)
    public Optional<UsersModel> findUsersByUsernameAndIdNot(UsersModel model) {
        Optional<Users> users = repository.findByUsernameAndIdNot
                (model != null && model.getUsername() != null ? model.getUsername() : "0",
                        model != null && model.getId() != null ? model.getId() : 0);
        return Optional.ofNullable(UsersMapper.get().entityToModel(users.orElse(null)));
    }
}
