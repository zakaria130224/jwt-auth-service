package com.example.json_token_auth_service.Models;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@Entity
@Table(name = "API_USERS_PERMISSION")
public class UserPermission extends BaseEntity {
    @Column(name ="userId")
    private int userId;

    @Column(name ="application")
    private String application;

    @Column(name ="uri")
    private String uri;


    public UserPermission(int userId, String application, String uri) {
        this.userId = userId;
        this.application = application;
        this.uri = uri;
    }
}
