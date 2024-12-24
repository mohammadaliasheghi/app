package com.app.demo.model;

import com.app.demo.enums.StageEnum;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProjectModel {

    private Long id;
    private String name;
    private StageEnum stage;
    private String description;
}
