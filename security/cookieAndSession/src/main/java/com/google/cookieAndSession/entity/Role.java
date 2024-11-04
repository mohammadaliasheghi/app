package com.google.cookieAndSession.entity;

import com.google.cookieAndSession.enums.Authority;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Role {

    @Id
    @GeneratedValue
    private Long id;
    private String name;

    @ElementCollection(targetClass = Authority.class, fetch = FetchType.EAGER)
    private List<Authority> authorities;
}
