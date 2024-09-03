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
@ApiModel(value = "UsersModel")
public class UsersModel {

    private Long id;
    private String username;
    private String password;

    //for update password
    private String previousPassword;
}
