package com.mmad.oauth.entity;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Getter
@Setter
@ToString
@Table(name = "roles")
public class Roles implements GrantedAuthority {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @NotBlank
    @Column(name = "name")
    private String name;

    @NotNull
    @Column(name = "enabled")
    private Boolean enabled = Boolean.TRUE;

    @Override
    public String getAuthority() {
        return this.name;
    }
}
