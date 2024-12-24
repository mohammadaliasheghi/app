package com.app.demo.entity;

import com.app.demo.enums.StageEnum;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "project_entity")
@Data
public class ProjectEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "stage", nullable = false)
    private StageEnum stage;

    @Column(name = "description")
    private String description;
}
