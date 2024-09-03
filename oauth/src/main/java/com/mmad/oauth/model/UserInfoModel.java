package com.mmad.oauth.model;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "UserInfoModel")
public class UserInfoModel {

    private Long id;
    private String firstName;
    private String lastName;
    private String nationalCode;
    private String email;
    private String phone;
    private UsersModel users;
    private Long usersId;
}
