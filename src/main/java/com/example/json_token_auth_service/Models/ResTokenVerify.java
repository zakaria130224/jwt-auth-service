package com.example.json_token_auth_service.Models;

import lombok.Data;

@Data
public class ResTokenVerify extends ResponseHeader {
    ReqTokenVerify reqTokenVerify;
    boolean isValidUser, hasPermission;
}
