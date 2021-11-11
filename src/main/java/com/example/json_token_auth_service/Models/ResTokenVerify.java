package com.example.json_token_auth_service.Models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ResTokenVerify extends ResponseBaseEntity {
    ReqTokenVerify reqTokenVerify;
    boolean isValidUser, hasPermission;
}
