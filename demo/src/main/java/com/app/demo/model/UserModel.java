package com.app.demo.model;

import com.app.demo.enums.GenderEnum;
import lombok.Data;

@Data
public class UserModel {

    private Long id;
    private String fullName;
    private int age;
    private Boolean employee;
    private GenderEnum gender;
}
