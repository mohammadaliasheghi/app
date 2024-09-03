package com.mmad.oauth.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@Table(name = "users_roles")
public class UserRoleEntity {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @JsonIgnore
    @Setter(AccessLevel.NONE)
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    @OneToOne(fetch = FetchType.LAZY)
    private Users users;

    @NotNull
    @Column(name = "user_id")
    private Long usersId;

    @JsonIgnore
    @Setter(AccessLevel.NONE)
    @JoinColumn(name = "role_id", insertable = false, updatable = false)
    @OneToOne(fetch = FetchType.LAZY)
    private Roles roles;

    @NotNull
    @Column(name = "role_id")
    private Long rolesId;
}
