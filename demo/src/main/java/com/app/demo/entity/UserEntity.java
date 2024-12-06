package com.app.demo.entity;

import com.app.demo.enums.GenderEnum;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "user_entity")
@Data
public class UserEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "full_name", nullable = false)
    private String fullName;
    @Column(name = "age", nullable = false)
    private int age;
    @Column(name = "employee", nullable = false, columnDefinition = "boolean default true")
    private Boolean employee;
    @Enumerated(EnumType.STRING)
    @Column(name = "gender", nullable = false)
    private GenderEnum gender;
}
