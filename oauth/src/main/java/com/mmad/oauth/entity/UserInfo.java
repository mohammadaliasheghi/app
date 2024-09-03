package com.mmad.oauth.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mmad.oauth.config.annotation.Email;
import com.mmad.oauth.config.annotation.Phone;
import com.sun.istack.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Getter
@Setter
@ToString
@Table(name = "user_info")
public class UserInfo {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @NotBlank
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @NotBlank
    @Column(name = "last_name", nullable = false)
    private String lastName;

    @NotBlank
    @Column(name = "national_code", nullable = false)
    private String nationalCode;

    @NotNull
    @Email
    @Column(name = "email", nullable = false
            , length = 500)
    private String email;

    @Phone
    @Column(name = "phone", length = 11)
    private String phone;

    @JsonIgnore
    @Setter(AccessLevel.NONE)
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    @OneToOne(fetch = FetchType.LAZY)
    private Users users;

    @NotNull
    @Column(name = "user_id")
    private Long usersId;
}
