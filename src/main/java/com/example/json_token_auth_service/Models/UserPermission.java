package com.example.json_token_auth_service.Models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@ToString
@Entity
@Table(name = "API_USERS_PERMISSION")
public class UserPermission extends BaseEntity {
    @Column(name ="userId")
    private int userId;

    @Column(name ="application")
    private String application;

    @Column(name ="uri")
    private String uri;
}
