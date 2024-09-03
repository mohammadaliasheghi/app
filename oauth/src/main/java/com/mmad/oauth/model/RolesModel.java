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
@ApiModel(value = "RolesModel")
public class RolesModel {

    private Long id;
    private String name;
    private Boolean enabled;
}
