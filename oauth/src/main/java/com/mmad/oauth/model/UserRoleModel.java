package com.mmad.oauth.model;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "UserRoleModel")
public class UserRoleModel {

    private Long id;
    private UsersModel users;
    private Long usersId;
    private RolesModel roles;
    private Long rolesId;
}
