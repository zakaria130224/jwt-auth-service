package com.example.json_token_auth_service.Models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.MappedSuperclass;

@Data
@MappedSuperclass
public class ResponseHeader {
    private ResponseDetails responseStatus;
}
