package com.example.json_token_auth_service.Models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.MappedSuperclass;

@Getter
@Setter
@ToString
@MappedSuperclass
public class ResponseBaseEntity {
    private ResponseDetails responseStatus;
}
